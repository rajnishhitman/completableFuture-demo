package org.example;

public class PriceService {

    public double getPrice(){

        sleep(2000);

        return 999.99;
    }

    private void sleep(long milliseconds){
        try{
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
