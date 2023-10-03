package com.pluralsight.streamslambdas.examples;

public class LambdaSelfExample {

    private String msg = "test string1";
    public static void main(String[] args) {
        LambdaSelfExample lse = new LambdaSelfExample();
        lse.printMsg();
    }

        public void printMsg(){
            Runnable runnable = () -> System.out.println(this.msg);

            new Thread(runnable).start();
            new Thread(runnable).start();

    }
    
}
