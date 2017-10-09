package com.paytpv.appjava.utils;

import com.paytpv.androidsdk.Model.Basic.PTPVProduct;

import java.util.ArrayList;

public class Products {
    private static ArrayList<PTPVProduct> productsArray = new ArrayList<>();

    static public void initializeProducts() {
        productsArray.clear();

        productsArray.add(new PTPVProduct("Donald", "Mickey Mouse", "98"));
        productsArray.add(new PTPVProduct("Johnny", "Hair Gel", "27"));
        productsArray.add(new PTPVProduct("Mark", "Arrows", "0"));
        productsArray.add(new PTPVProduct("Goku", "Dragon Ball", "100"));
    }

    public static ArrayList<PTPVProduct> getProductsArray() {
        return productsArray;
    }
}
