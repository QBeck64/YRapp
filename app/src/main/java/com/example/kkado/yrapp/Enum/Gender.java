package com.example.kkado.yrapp.Enum;

import java.util.HashMap;
import java.util.Map;

/**enum access infromation rearding gender 
 *
 */
public enum Gender {
    Masculine(1, "Masculine"), Feminine(2, "Feminine");

    /**Access id infromation and description
     * Members
     */
    private int id;
    private String description;

    /**Costructor integrates id nad description
     * Constructor
     */
    private Gender() {
    }

    private Gender(int id, String description) {
        this.id = id;
        this.description = description;
    }

    /**Access id and description 
     * Gets
     */
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    /**Access id and description
     * Sets
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**Map uses gender
     * Records the relationships between the description and the value of an enum element
     */
    private static Map<String, Gender> relations;

    /**Gets decription from string
     * Retrieve an enum element from a string
     *
     * @param description
     * @return
     */
    public static Gender getGenderDescription(String description) {
        return relations.get(description);
    }

    /**
     * Static block that populates the hashmap with the relations between description and elements of the enum
     */
    static {
        relations = new HashMap<String, Gender>();
        for (Gender s : values()) relations.put(s.getDescription(), s);
    }
}

