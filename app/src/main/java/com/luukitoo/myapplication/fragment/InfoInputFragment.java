package com.luukitoo.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.luukitoo.myapplication.data.User;
import com.luukitoo.myapplication.data.UserDao;
import com.luukitoo.myapplication.data.UserDatabase;
import com.luukitoo.myapplication.databinding.FragmentInfoInputBinding;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InfoInputFragment extends Fragment {

    private FragmentInfoInputBinding binding;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInfoInputBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();
    }

    private void setListeners() {
        binding.saveButton.setOnClickListener(view -> {
            User user = new User(
                    binding.usernameEditText.getText().toString(),
                    binding.emailEditText.getText().toString(),
                    Integer.parseInt(binding.ageEditText.getText().toString()),
                    binding.genderSpinner.getSelectedItem().toString()
            );
            saveUser(user);
        });
        binding.showButton.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(
                    InfoInputFragmentDirections.actionInfoInputFragmentToInfoOutputFragment()
            );
        });
    }

    private void saveUser(User user) {
        UserDao userDao = UserDatabase.getDatabase(requireContext()).getUserDao();
        disposables.add(
                Observable.just(true)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(bool -> {
                            userDao.addUser(user);
                            return Observable.just(true);
                        })
                        .subscribe(bool -> { })
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposables.clear();
    }
}
