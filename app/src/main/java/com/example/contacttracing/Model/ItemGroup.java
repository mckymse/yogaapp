package com.example.contacttracing.Model;

import java.util.ArrayList;

public class ItemGroup {
    private String headerTitle;
    private ArrayList<ItemData> listitem;

    public ItemGroup() {
    }

    public ItemGroup(String headerTitle, ArrayList<ItemData> listitem) {
        this.headerTitle = headerTitle;
        this.listitem = listitem;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<ItemData> getListitem() {
        return listitem;
    }

    public void setListitem(ArrayList<ItemData> listitem) {
        this.listitem = listitem;
    }
}
