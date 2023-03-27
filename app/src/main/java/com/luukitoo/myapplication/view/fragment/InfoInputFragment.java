package com.luukitoo.myapplication.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.luukitoo.myapplication.model.User;
import com.luukitoo.myapplication.model.UserDao;
import com.luukitoo.myapplication.model.UserDatabase;
import com.luukitoo.myapplication.databinding.FragmentInfoInputBinding;
import com.luukitoo.myapplication.viewmodel.InfoInputViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InfoInputFragment extends Fragment {

    private InfoInputViewModel viewModel;

    private FragmentInfoInputBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInfoInputBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setListeners();
    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(InfoInputViewModel.class);
    }

    private void setListeners() {
        binding.saveButton.setOnClickListener(view -> {
            User user = new User(
                    binding.usernameEditText.getText().toString(),
                    binding.emailEditText.getText().toString(),
                    Integer.parseInt(binding.ageEditText.getText().toString()),
                    binding.genderSpinner.getSelectedItem().toString()
            );
            viewModel.saveUser(user);
        });
        binding.showButton.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(
                    InfoInputFragmentDirections.actionInfoInputFragmentToInfoOutputFragment()
            );
        });
    }
}
