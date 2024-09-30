package com.itmo;

import com.itmo.collectors.MenuItemCollector;
import com.itmo.data.MenuItem;
import com.itmo.data.Order;
import com.itmo.data.OrderCategory;
import com.itmo.data.Staff;
import com.itmo.generator.DataGenerator;
import net.andreinc.mockneat.MockNeat;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        runAnalyse(5000);
        runAnalyse(50000);
        runAnalyse(250000);
    }

    public static void runAnalyse(int count) {
        System.out.println("\nПодсчет для " + count + " элементов:\n");
        List<Order> orders = Stream.generate(new DataGenerator()::generateOrder)
            .limit(count)
            .toList();
        usingFor(orders);
        usingStream(orders);
        usingCollectors(orders);
    }

    public static void usingFor(List<Order> orders) {
        long startTime = System.currentTimeMillis();

        HashMap<String, Integer> analyticsMap = new HashMap<>();
        for (Order order : orders) {
            for (MenuItem item : order.getItems()) {
                analyticsMap.merge(item.getName(), item.getQuantity(), Integer::sum);
            }
        }

        int maxQuantity = -1;
        String maxItemName = "";
        for (String itemName : analyticsMap.keySet()) {
            if (maxQuantity < analyticsMap.get(itemName)) {
                maxQuantity = analyticsMap.get(itemName);
                maxItemName = itemName;
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Итерация по коллекции:\n\t" + maxItemName + " " + maxQuantity + " шт. \n\t" + duration + " мс.");
    }

    public static void usingStream(List<Order> orders) {
        long startTime = System.currentTimeMillis();

        Map<String, Integer> itemCountMap = orders.stream()
            .flatMap(order -> order.getItems().stream())
            .collect(Collectors.toMap(
                MenuItem::getName,
                MenuItem::getQuantity,
                Integer::sum
            ));

        Map.Entry<String, Integer> mostPopularItem = itemCountMap.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .orElseThrow();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Stream API:\n\t" + mostPopularItem.getKey() + " " + mostPopularItem.getValue() + " шт. \n\t" + duration + " мс.");
    }

    public static void usingCollectors(List<Order> orders) {
        long startTime = System.currentTimeMillis();

        Map.Entry<String, Integer> mostPopularItem = orders.stream()
            .flatMap(order -> order.getItems().stream())
            .collect(new MenuItemCollector());

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Collectors:\n\t" + mostPopularItem.getKey() + " " + mostPopularItem.getValue() + " шт.\n\t" + duration + " мс.");

    }
}
