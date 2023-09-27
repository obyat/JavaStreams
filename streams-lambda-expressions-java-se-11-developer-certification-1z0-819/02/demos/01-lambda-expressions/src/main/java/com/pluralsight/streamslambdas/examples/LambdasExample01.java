/*
 * This code is part of the course "Working with Streams and Lambda Expressions in Java (Java SE 11 Developer Certification 1Z0-819)" for Pluralsight.
 *
 * Copyright (C) 2021 by Jesper de Jong (jesper@jdj-it.com).
 */
package com.pluralsight.streamslambdas.examples;

import com.pluralsight.streamslambdas.ExampleData;
import com.pluralsight.streamslambdas.Product;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class LambdasExample01 {
    interface ProductFilter {
        boolean accept(Product product);
    }

    public static void main(String[] args) {
        List<Product> products = ExampleData.getProducts();

        // Sort by directly implementing an anonymous class
        // Sort in reverse ordering
        products.sort(
                new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o2.getPrice().compareTo(o1.getPrice());
                    }
                });

        // Sort by lambda
        // Sort by lexicographic ordering
        products.sort(
                (p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));

        for (Product product : products) {
            System.out.println(product);
        }

        /*
         * Lambda Syntax
         * parameters body
         * (o1, o1) -> o1.toString().compareTo(o2.toString());
         * The functional interface implemented by the lambda specifies the the
         * parameters types and return type
         * 
         * no parameters
         * () -> System.out.println("Test");
         * 
         * exactly a single parameter
         * a -> a.toString();
         */

        // with method
        getLessThanPriceLimit(products, new BigDecimal("5.00"));

        // with Functional Interface
        BigDecimal priceLimit = new BigDecimal("5.00"); // must be final
        ProductFilter filter = product -> product.getPrice().compareTo(priceLimit) < 0;
        getLessThanPriceLimit2(products, filter);

    }

    // with method
    static void getLessThanPriceLimit(List<Product> products, BigDecimal priceLimit) {
        System.out.printf("%n");
        for (Product p : products) {
            if (p.getPrice().compareTo(priceLimit) < 0) {
                System.out.printf("%s is less than: %.2f. %.2f%n", p.getName(), priceLimit, p.getPrice());

            }
        }
    }
     // with Functional Interface
    static void getLessThanPriceLimit2(List<Product> products, ProductFilter filter) {
        System.out.printf("%n");
        for (Product p : products) {
            if (filter.accept(p)) {
                System.out.printf("%s is less than: %.2f %.2f%n", p.getName(), 5.0, p.getPrice());
            }
        }
    }
}
