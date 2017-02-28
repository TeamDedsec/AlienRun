package com.example.kaloyanit.alienrun.Data;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by KaloyanIT on 2/28/2017.
 */

public interface ILocalData<T> {
    Observable<List<T>> getAll();

    Observable<T> getById(long id);

    Observable add(T item);
}
