package com.example.kkado.yrapp.Enum;

import java.util.HashMap;
import java.util.Map;

public enum StatusCandidate {
    Pending(1, "Pending"), InProcess(2, "In Process"), Approved(3, "Approved"), Disapproved(4, "Disapproved");

    /**
     * Members
     */
    private int id;
    private String description;

    /**
     * Constructor
     */
    private StatusCandidate() {
    }

    private StatusCandidate(int id, String description) {
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
    private static Map<String, StatusCandidate> relations;

    /**
     * Retrieve an enum element from a string
     *
     * @param description
     * @return
     */
    public static StatusCandidate getStatusCandidateDescription(String description) {
        return relations.get(description);
    }

    /**
     * Static block that populates the hashmap with the relations between description and elements of the enum
     */
    static {
        relations = new HashMap<String, StatusCandidate>();
        for (StatusCandidate s : values()) relations.put(s.getDescription(), s);
    }
}


