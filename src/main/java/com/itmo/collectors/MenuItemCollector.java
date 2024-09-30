package com.itmo.collectors;

import com.itmo.data.MenuItem;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MenuItemCollector implements Collector<MenuItem, Map<String, Integer>, Map.Entry<String, Integer>> {

    @Override
    public Supplier<Map<String, Integer>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<String, Integer>, MenuItem> accumulator() {
        return (map, item) -> map.merge(item.getName(), item.getQuantity(), Integer::sum);
    }

    @Override
    public BinaryOperator<Map<String, Integer>> combiner() {
        return (map1, map2) -> {
            map2.forEach((key, value) -> map1.merge(key, value, Integer::sum));
            return map1;
        };
    }

    @Override
    public Function<Map<String, Integer>, Map.Entry<String, Integer>> finisher() {
        return map -> map.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}