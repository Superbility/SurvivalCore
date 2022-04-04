package com.superdevelopment.survivalcore.utils;

import java.util.ArrayList;
import java.util.List;

public class AddLists {
    public static List<String> addList(List<String> list1, List<String> list2) {
        List<String> newList = new ArrayList<>();

        for(String s : list1) {
            newList.add(s);
        }
        for(String s : list2) {
            newList.add(s);
        }

        return newList;
    }
}
