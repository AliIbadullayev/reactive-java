package com.itmo;

import com.itmo.data.MenuItem;
import com.itmo.data.Order;
import com.itmo.data.OrderCategory;
import com.itmo.data.Staff;
import com.itmo.generator.DataGenerator;
import net.andreinc.mockneat.MockNeat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();
        Order randomOrder = dataGenerator.generateOrder();

        // Вывод сгенерированного заказа
        System.out.println("Номер кассы обслуживания: " + randomOrder.getCashboxNumber());
        System.out.println("Дата начала: " + randomOrder.getStartDate());
        System.out.println("Дата окончания: " + randomOrder.getEndDate());
        System.out.println("Номер заказа: " + randomOrder.getOrderNumber());
        System.out.println("Категория: " + randomOrder.getCategory());
        System.out.println("Персонал: " + randomOrder.getStaff().fullName());

        System.out.println("Позиции:");
        for (MenuItem item : randomOrder.getItems()) {
            System.out.println(" - " + item.getName() + ": " + item.getPrice() + " руб, " + item.getCalories() + " ккал, " + item.getQuantity() + " шт.");
        }
    }
}
