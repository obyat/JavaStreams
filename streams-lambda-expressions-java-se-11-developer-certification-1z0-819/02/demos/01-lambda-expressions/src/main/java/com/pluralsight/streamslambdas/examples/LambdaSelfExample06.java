package com.pluralsight.streamslambdas.examples;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.pluralsight.streamslambdas.ExampleData;
import com.pluralsight.streamslambdas.Product;

public class LambdaSelfExample06 {
    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Product> products = ExampleData.getProducts();

        try (FileWriter writer = new FileWriter("products.txt")) {

            /*
             ! using for loop is better solution
             * for (Product product : products) {
             * writer.write( (product.getPrice().add(new BigDecimal(100))).toString() +
             * "\n");
             * }
             */

            // using functional interface
            products.forEach(product -> {
                try {
                    writer.write((product.getPrice().add(new BigDecimal(0))).toString() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException | RuntimeException e) {
            // TODO: Auto-generated catch block
            System.err.printf("An exception occurred while writing:", e.getMessage());
        }

    }
}
