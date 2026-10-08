package org.example;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
//        Complete mini project

        ProductService productService = new ProductService();
        PriceService priceService = new PriceService();
        InventoryService inventoryService = new InventoryService();

        long start = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        CompletableFuture<String> productFuture = CompletableFuture.supplyAsync(productService::getProduct, executor);
        CompletableFuture<Double> priceFuture = CompletableFuture.supplyAsync(priceService::getPrice, executor);
        CompletableFuture<Integer> inventoryFuture = CompletableFuture.supplyAsync(inventoryService::getInventory, executor);

        CompletableFuture<String> result = productFuture.thenCombine(priceFuture, (product, price) -> product + " | $" + price)
                .thenCombine(inventoryFuture, (productPrice, inventory) -> productPrice + " | stock: " + inventory)
                .exceptionally(ex -> {
                    System.out.println("Error: " + ex.getMessage());
                    return "unable to load product";
                });

        System.out.println(result.join());

        System.out.println("Time taken: " + (System.currentTimeMillis()-start) + " ms");

        executor.shutdown();






//        --------------------------------------------------------------------------------------------------------
//        ProductService productService = new ProductService();
//        PriceService priceService = new PriceService();
//        InventoryService inventoryService = new InventoryService();
//
//        long start = System.currentTimeMillis();

//        String product = productService.getProduct();
//
//        double price = priceService.getPrice();
//
//        int inventory = inventoryService.getInventory();
//
//        System.out.println(product);
//        System.out.println(price);
//        System.out.println(inventory);

//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        CompletableFuture<String> productFuture = CompletableFuture.supplyAsync(productService::getProduct, executorService);
//        CompletableFuture<Double> priceFuture = CompletableFuture.supplyAsync(() -> {
//                if(false)
//                    throw new RuntimeException("Price service is down");
//
//                return priceService.getPrice();
//        });
//        CompletableFuture<Double> safePriceFuture = priceFuture.exceptionally(ex -> {
//            System.out.println("Error: " + ex.getMessage());
//            return 0.0;
//        });
//        CompletableFuture<Double> safePriceFuture = priceFuture.handle((price, ex) -> {
//            if(ex!=null){
//                System.out.println("Failed: " + ex.getMessage());
//                return 0.0;
//            }
//            return price;
//        });
//        CompletableFuture<Integer> inventoryFuture = CompletableFuture.supplyAsync(inventoryService::getInventory);

//        executorService.shutdown();
//        CompletableFuture<String> result = productFuture.thenCombine(safePriceFuture,
//                (product, price) -> product + " | $" + price).thenCombine(inventoryFuture,
//                (productPrice, inventory) -> productPrice + " | stock: " + inventory);
//        System.out.println(result.join());

//        CompletableFuture<String> upperCaseProductFuture = productFuture.thenApply(String::toUpperCase);

//        upperCaseProductFuture.thenAccept(System.out::println).join();

//        System.out.println(upperCaseProductFuture.join());
//        System.out.println(priceFuture.join());
//        System.out.println(inventoryFuture.join());
//        try{
//            System.out.println(productFuture.get());
//            System.out.println(priceFuture.join());
//            System.out.println(inventoryFuture.get());
//        }catch (InterruptedException e){
//            Thread.currentThread().interrupt();
//        }catch (ExecutionException e){
//            System.out.println("Task failed: " + e.getCause());
//        }

//        long end = System.currentTimeMillis();
//
//        System.out.println("Time taken: " + (end - start) + " ms");
    }
}