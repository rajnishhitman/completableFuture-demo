package org.example;

public class ProductService {

    public String getProduct(){

        sleep(2000);

        return "iPhone 17";
    }

    private void sleep(long milliseconds){
        try{
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
