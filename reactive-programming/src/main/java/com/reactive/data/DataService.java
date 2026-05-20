package com.reactive.data;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DataService {

    private final Map<Integer, String> data = generateData();

    private Map<Integer, String> generateData() {
        Map<Integer, String> data = new LinkedHashMap<>();

        for (int i = 1; i <= 100; i++) {
            data.put(i, UUID.randomUUID().toString());
        }
        return data;
    }

    public Map<Integer, String> getData(Set<Integer> keys) {
        return keys.stream()
                .filter(data::containsKey)
                .collect(Collectors.toMap(
                        Key -> Key,
                        data::get
                ));
    }

    public Map<Integer, String> getData(Integer key) {
        if (data.containsKey(key)) {
            return Map.of(key, data.get(key));
        } else {
            return Map.of();
        }
    }

}
