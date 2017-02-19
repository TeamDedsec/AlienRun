package com.example.data.base;


import io.reactivex.Observable;

/**
 * Created by KaloyanIT on 2/19/2017.
 */

public interface IData<T> {
    Observable<T[]> getAll();
    Observable<T> getById(Object id);
    Observable<T> add(T obj);
}
