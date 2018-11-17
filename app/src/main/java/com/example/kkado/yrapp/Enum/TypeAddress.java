package com.example.kkado.yrapp.Enum;

import java.util.Map;
import java.util.HashMap;

public enum TypeAddress {
    Avenue(1, "Avenue"), Boulevard(2, "Boulevard"), Circle(3, "Circle"), Court(4, "Court"), Expressway(5, "Expressway"), Freeway(6, "Freeway"), Lane(7, "Lane"), Parkway(8, "Parkway"), Road(9, "Road"), Square(10, "Square"), Street(11, "Street"), Turnpike(12, "Turnpike"), North(13, "North"), East(14, "East"), South(15, "South"), West(16, "West"), Northeast(17, "Northeast"), Southeast(18, "Southeast");

    /**
     * Members
     */
    private int id;
    private String description;

    /**
     * Constructor
     */
    private TypeAddress() {
    }

    private TypeAddress(int id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * Gets
     */
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Records the relationships between the description and the value of an enum element
     */
    private static Map<String, TypeAddress> relations;

    /**
     * Retrieve an enum element from a string
     *
     * @param description
     * @return
     */
    public static TypeAddress getTypeAddressDescription(String description) {
        return relations.get(description);
    }

    /**
     * Static block that populates the hashmap with the relations between description and elements of the enum
     */
    static {
        relations = new HashMap<String, TypeAddress>();
        for (TypeAddress s : values()) relations.put(s.getDescription(), s);
    }
}