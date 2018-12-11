package com.example.kkado.yrapp.Enum;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public enum TypePerson {
    Candidate(1, "Candidate"), SalesPerson(2, "SalesPerson"), Leader(3, "Leader"), ZoneManager(4, "ZoneManager");

    /**
     * Members
     */
    private int id;
    private String description;

    /**
     * Constructor
     */
    private TypePerson() {
    }

    private TypePerson(int id, String description) {
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
    private static Map<String, TypePerson> relations;

    /**
     * Retrieve an enum element from a string
     *
     * @param description
     * @return
     */
    public static TypePerson getTypePersonDescription(String description) {
        return relations.get(description);
    }

    /**
     * Static block that populates the hashmap with the relations between description and elements of the enum
     */
    static {
        relations = new HashMap<String, TypePerson>();
        for (TypePerson s : values()) relations.put(s.getDescription(), s);
    }
}


