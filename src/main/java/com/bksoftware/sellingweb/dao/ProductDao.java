package com.bksoftware.sellingweb.dao;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public  List<String> nGrams(int n, String str) {
        List<String> n_Gram = new ArrayList<>();
        for (int i = 0; i < str.length() - n + 1;i++) {
            n_Gram.add(str.substring(i,i+n));
        }
        return n_Gram;
    }



}
