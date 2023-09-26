/*
 * This code is part of the course "Working with Streams and Lambda Expressions in Java (Java SE 11 Developer Certification 1Z0-819)" for Pluralsight.
 *
 * Copyright (C) 2021 by Jesper de Jong (jesper@jdj-it.com).
 */
package com.pluralsight.streamslambdas.examples;

import com.pluralsight.streamslambdas.ExampleData;
import com.pluralsight.streamslambdas.Product;

import java.util.Comparator;
import java.util.List;

public class LambdasExample01 {

    public static void main(String[] args) {
        List<Product> products = ExampleData.getProducts();

        //Sort by directly implementing an anonymous class
        //Sort in reverse ordering
        products.sort(
            new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o2.getPrice().compareTo(o1.getPrice());
                }
            }
        );

        //Sort by lambda
        //Sort by lexicographic ordering
        products.sort(
            (p1, p2) -> p1.getPrice().compareTo(p2.getPrice())
        );

        for(Product product: products){
            System.out.println(product);
        }


    }
}

