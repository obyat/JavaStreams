/*
 * This code is part of the course "Working with Streams and Lambda Expressions in Java (Java SE 11 Developer Certification 1Z0-819)" for Pluralsight.
 *
 * Copyright (C) 2021 by Jesper de Jong (jesper@jdj-it.com).
 */
package com.pluralsight.streamslambdas.examples;

import com.pluralsight.streamslambdas.ExampleData;
import com.pluralsight.streamslambdas.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

abstract interface findCheap {
    boolean find(Product product);
}

abstract interface accumulateCheap {
    boolean filter(Product product);
}

public class LambdasExample04 {

    public static void main(String[] args) {
        List<Product> products = ExampleData.getProducts();
        BigDecimal priceLimit = new BigDecimal("3.0");
        System.out.printf("Method: There are %d cheap products%n", getCountCheapProducts(products, priceLimit));

        findCheap find = (product) -> product.getPrice().compareTo(priceLimit) < 0;
        System.out.printf("Lambda: There are %d cheap products%n", getCountCheapProducts2(products, find));

        accumulateCheap add = (product) -> product.getPrice().compareTo(priceLimit) < 0;
        System.out.println("List of cheap products:" + accumulateCheapProducts(products, add));

        accumulateCheapProductsForEach(products, add);
    }

    // method
    private static int getCountCheapProducts(List<Product> products, BigDecimal priceLimit) {
        int cheapProductCount = 0;
        for (Product product : products) {
            if (product.getPrice().compareTo(priceLimit) < 0) {
                cheapProductCount++;
            }
        }
        return cheapProductCount;
    }

    // functional interface
    private static int getCountCheapProducts2(List<Product> products, findCheap find) {
        int cheapProductCount = 0;
        for (Product product : products) {
            if (find.find(product)) {
                cheapProductCount++;
            }
        }
        return cheapProductCount;
    }

    // functional interface
    private static List<Product> accumulateCheapProducts(List<Product> products, accumulateCheap find) {
        List<Product> result = new ArrayList<Product>();
        for (Product product : products) {
            if (find.filter(product)) {
                result.add(product);
            }
        }
        return result;
    }

    //consumer
    private static void accumulateCheapProductsForEach(List<Product> products, accumulateCheap interfaceInstance) {
        List<Product> result = new ArrayList<Product>();
        products.forEach(
                product -> {
                    if (interfaceInstance.filter(product)) {
                        result.add(product);
                    }
                });

        System.out.println();
        products.forEach(p -> System.out.println(p));
    }
}
