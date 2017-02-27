package com.example.kaloyanit.alienrun.Data;


import com.example.kaloyanit.alienrun.Data.base.BaseData;
import com.example.kaloyanit.alienrun.Models.ModelBase;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by KaloyanIT on 2/20/2017.
 */

public class LocalData<T extends ModelBase> {
    private List<T>  items;

    @Inject
    public LocalData() {
        this.items = new ArrayList<>();
    }


    public Observable<List<T>> getAll() {
        return Observable.just(this.items);
    }


    public Observable<T> getById(long id) {
        for(T item : this.items) {
            if(item.getId() == id){
                return Observable.just(item);
            }
        }
        return Observable.error(new InvalidParameterException("No item with this ID"));
    }


    public Observable add(T item) {
        this.items.add(item);
        item.setId(this.items.size() + 1);
        return Observable.just(item);
    }
}
