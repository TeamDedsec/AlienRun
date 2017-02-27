package com.example.kaloyanit.alienrun.Data.base;

import com.example.kaloyanit.alienrun.Models.ModelBase;

import io.reactivex.Observable;

/**
 * Created by KaloyanIT on 2/20/2017.
 */

public abstract class BaseData<T extends ModelBase> {
    public abstract Observable<T[]> getAll();

    public abstract Observable<T> getById(Object id);

    public abstract Observable<T> add(T obj);
}
