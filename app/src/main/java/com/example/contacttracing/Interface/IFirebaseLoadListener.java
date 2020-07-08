package com.example.contacttracing.Interface;

import com.example.contacttracing.Model.ItemGroup;

import java.util.List;

public interface IFirebaseLoadListener {
    void onFirebaseLoadSuccess(List<ItemGroup> itemGroupList);
    void onFirebaseLoadFailed(String message);
}
