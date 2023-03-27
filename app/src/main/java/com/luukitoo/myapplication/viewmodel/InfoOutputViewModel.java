package com.luukitoo.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.luukitoo.myapplication.model.User;
import com.luukitoo.myapplication.model.UserDao;
import com.luukitoo.myapplication.model.UserDatabase;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InfoOutputViewModel extends AndroidViewModel {

    public InfoOutputViewModel(@NonNull Application application) {
        super(application);
    }

    private CompositeDisposable disposables = new CompositeDisposable();

    public MutableLiveData<User> userLive = new MutableLiveData<>();

    public MutableLiveData<Integer> counterLive = new MutableLiveData<>(0);

    public void getUsers() {
        UserDao userDao = UserDatabase.getDatabase(getApplication().getApplicationContext()).getUserDao();
        disposables.add(
                userDao.getUser()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(user -> {
                            userLive.setValue(user);
                        })
        );
    }

    public void increase() {
        counterLive.setValue(counterLive.getValue() + 1);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
