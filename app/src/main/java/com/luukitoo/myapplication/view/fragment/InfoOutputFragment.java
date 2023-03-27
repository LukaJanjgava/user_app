package com.luukitoo.myapplication.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.luukitoo.myapplication.databinding.FragmentInfoOutputBinding;
import com.luukitoo.myapplication.model.UserDao;
import com.luukitoo.myapplication.model.UserDatabase;
import com.luukitoo.myapplication.viewmodel.InfoOutputViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InfoOutputFragment extends Fragment {

    private InfoOutputViewModel viewModel;

    private FragmentInfoOutputBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInfoOutputBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        setListeners();
        setObservers();
    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(InfoOutputViewModel.class);
        viewModel.getUsers();
    }

    private void setListeners() {
        binding.increaseButton.setOnClickListener(button -> {
            viewModel.increase();
        });
    }

    private void setObservers() {
        viewModel.userLive.observe(getViewLifecycleOwner(), user -> {
            binding.usernameTextView.setText(user.username);
            binding.emailTextView.setText(user.email);
            binding.ageTextView.setText(String.valueOf(user.age));
            binding.genderTextView.setText(user.gender);
        });
        viewModel.counterLive.observe(getViewLifecycleOwner(), count -> {
            binding.counterTextView.setText(String.valueOf(count));
        });
    }
}
