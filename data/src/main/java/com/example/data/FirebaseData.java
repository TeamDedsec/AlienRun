package com.example.data;

import com.example.data.base.IData;

import io.reactivex.Observable;

/**
 * Created by KaloyanIT on 2/19/2017.
 */

public class FirebaseData<T> implements IData {
    @Override
    public Observable getAll() {
        return null;
    }

    @Override
    public Observable getById(Object id) {
        return null;
    }

    @Override
    public Observable add(Object obj) {
        return null;
    }
}
