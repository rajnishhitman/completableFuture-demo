package org.example;

public class InventoryService {

    public int getInventory(){

        sleep(2000);

        return 25;
    }

    private void sleep(long milliseconds){
        try{
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
