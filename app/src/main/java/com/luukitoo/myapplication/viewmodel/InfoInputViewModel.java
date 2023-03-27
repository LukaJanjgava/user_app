package com.luukitoo.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.luukitoo.myapplication.model.User;
import com.luukitoo.myapplication.model.UserDao;
import com.luukitoo.myapplication.model.UserDatabase;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InfoInputViewModel extends AndroidViewModel {

    public InfoInputViewModel(@NonNull Application application) {
        super(application);
    }

    private CompositeDisposable disposables = new CompositeDisposable();

    public void saveUser(User user) {
        UserDao userDao = UserDatabase.getDatabase(getApplication().getApplicationContext()).getUserDao();
        disposables.add(
                userDao.addUser(user)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
