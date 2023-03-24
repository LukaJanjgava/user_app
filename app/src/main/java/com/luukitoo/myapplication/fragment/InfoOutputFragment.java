package com.luukitoo.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.luukitoo.myapplication.data.User;
import com.luukitoo.myapplication.data.UserDao;
import com.luukitoo.myapplication.data.UserDatabase;
import com.luukitoo.myapplication.databinding.FragmentInfoOutputBinding;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InfoOutputFragment extends Fragment {

    private FragmentInfoOutputBinding binding;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInfoOutputBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUsers();
    }

    private void getUsers() {
        UserDao userDao = UserDatabase.getDatabase(requireContext()).getUserDao();
        disposables.add(
                Observable.just(userDao.getUser())
                        .observeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(user -> {
                            binding.usernameTextView.setText(user.username);
                            binding.emailTextView.setText(user.email);
                            binding.ageTextView.setText(String.valueOf(user.age));
                            binding.genderTextView.setText(user.gender);
                        })
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposables.clear();
    }
}
