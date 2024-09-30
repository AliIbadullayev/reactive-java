package com.itmo.generator;

import com.itmo.data.MenuItem;
import com.itmo.data.Order;
import com.itmo.data.OrderCategory;
import com.itmo.data.Staff;
import net.andreinc.mockneat.MockNeat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class DataGenerator {

    private final MockNeat mock;

    // Constructor to initialize MockNeat
    public DataGenerator() {
        this.mock = MockNeat.threadLocal();
    }

    // Method to generate Staff object
    public Staff generateStaff() {
        return new Staff(mock.names().full().val());
    }

    // Method to generate a list of MenuItem objects
    public List<MenuItem> generateMenuItems() {
        return mock.reflect(MenuItem.class)
                .field("name", mock.strings().size(5))
                .field("price", mock.doubles().range(200.0, 500.0))
                .field("quantity", mock.ints().range(1, 10))
                .field("calories", mock.doubles().range(100.0, 500.0))
                .list(mock.ints().range(1, 10))
                .val();
    }

    // Method to generate Order object
    public Order generateOrder() {
        Staff staff = generateStaff();
        List<MenuItem> items = generateMenuItems();

        return mock.reflect(Order.class)
                .field("orderNumber", UUID.randomUUID().toString())
                .field("category", mock.from(OrderCategory.class))
                .field("startDate", LocalDateTime.now().minusMinutes((long) (Math.random() * 20)))
                .field("endDate", LocalDateTime.now().plusMinutes((long) (Math.random() * 20)))
                .field("items", items)
                .field("staff", staff)
                .field("cashboxNumber", mock.ints().range(1, 10))
                .val();
    }
}