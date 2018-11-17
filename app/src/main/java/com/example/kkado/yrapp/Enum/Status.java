package com.example.kkado.yrapp.Enum;

import java.util.Map;
import java.util.HashMap;

public enum Status {
 Active(1,"Active")
 , Inactive(2,"Inactive");

 /**
  * Members
  */
 private int 	id;
 private String description;

 /**
  * Constructor
  */
 private Status() {
 }

 private Status(int id, String description) {
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
 private static  Map<String, Status> relations;

 /**
  * Retrieve an enum element from a string
  * @param description
  * @return
  */
 public static Status getStatusDescription(String description) {
  return relations.get(description);
 }

/**
 * Static block that populates the hashmap with the relations between description and elements of the enum
 */
static {
        relations = new HashMap<String, Status>();
        for(Status s : values()) relations.put(s.getDescription(), s);
        }
        }


