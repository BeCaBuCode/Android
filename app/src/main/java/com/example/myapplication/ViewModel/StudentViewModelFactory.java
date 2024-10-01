package com.example.myapplication.ViewModel;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class StudentViewModelFactory implements ViewModelProvider.Factory {
    private Intent r;

    public StudentViewModelFactory( Intent r) {
        this.r = r;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ViewModel_Student.class)){
            return (T) new ViewModel_Student(r);
        }
        try {
            throw new IllegalAccessException("Unknow ViewModel Class");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
