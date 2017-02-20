package com.example.kaloyanit.alienrun.Data;


import com.example.kaloyanit.alienrun.Data.base.BaseData;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by KaloyanIT on 2/20/2017.
 */

public class LocalData<T> extends BaseData<T>{
    private ArrayList<T>  items;

    @Inject
    public LocalData() {
        items = new ArrayList<>();
    }

    @Override
    public Observable<T[]> getAll() {
        return Observable.just(
                (T[]) this.items.toArray()
        );
    }

    @Override
    public Observable<T> getById(Object id) {
        return Observable.just(null);
    }

    @Override
    public Observable add(T item) {
        this.items.add(item);
        return Observable.just(item);
    }
}
