package com.example.android.sqliteweather;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.sqliteweather.data.Status;

public class FavoritesViewModel extends ViewModel {
    private LiveData<Status> mLoadingStatus;

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }
    // TODO: Implement the ViewModel
}
