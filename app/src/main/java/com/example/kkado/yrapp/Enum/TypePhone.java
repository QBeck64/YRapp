package com.example.kkado.yrapp.Enum;

import java.util.HashMap;
import java.util.Map;

public enum TypePhone {
 Mobile
 , Work
 , Home
 , Main
 , Other;

 /**
  * Members
  */
 private int 	id;
 private String description;

 /**
  * Constructor
  */
 private TypePhone() {
 }

 private TypePhone(int id, String description) {
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
 private static Map<String, TypePhone> relations;

 /**
  * Retrieve an enum element from a string
  * @param description
  * @return
  */
 public static TypePhone getTypePhoneDescription(String description) {
  return relations.get(description);
 }

 /**
  * Static block that populates the hashmap with the relations between description and elements of the enum
  */
 static {
  relations = new HashMap<String, TypePhone>();
  for(TypePhone s : values()) relations.put(s.getDescription(), s);
 }
 }


