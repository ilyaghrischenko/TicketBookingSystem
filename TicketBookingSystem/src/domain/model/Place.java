package domain.model;

import java.util.UUID;

public class Place {
    private final UUID id;
    private String name;
    private String address;

    public Place(UUID id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    // геттеры
}