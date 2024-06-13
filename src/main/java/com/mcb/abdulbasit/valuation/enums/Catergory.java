package com.mcb.abdulbasit.valuation.enums;

public enum Catergory {
    APARTMENT("Apartment",25000),
    PBWMHOUSING("PBWM Housing",25010);

    private final String name;
    private final int id;

    Catergory(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static Catergory fromId(int id) {
        for (Catergory catergory : Catergory.values()) {
            if (catergory.id == id) {
                return catergory;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + id);
    }
}