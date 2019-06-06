package com.rotonity.inventory.inventoryapp;

import java.io.Serializable;

public class InventoryItem implements Serializable {



   private String part_type;
   private String name;
   private String description;
   private String cost;
   private String currently_with;
   private String purchased_by;
   private String bar_code;
   private String availability;
   private String last_edit;

   private final int NO_OF_ATTRIBUTES=9;

    //each item is to at least have a bar_code, a name when initialized
    InventoryItem(String bar_code)
    {
        this.bar_code=bar_code;
      //  this.name=name;
    }

    InventoryItem() {

    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setCurrently_with(String currently_with) {
        this.currently_with = currently_with;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLast_edit(String last_edit) {
        this.last_edit = last_edit;
    }

    public void setPart_type(String part_type) {
        this.part_type = part_type;
    }

    public void setPurchased_by(String purchased_by) {
        this.purchased_by = purchased_by;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAvailability() {
        return availability;
    }

    public String getBar_code() {
        return bar_code;
    }

    public String getCost() {
        return cost;
    }

    public String getCurrently_with() {
        return currently_with;
    }

    public String getDescription() {
        return description;
    }

    public String getLast_edit() {
        return last_edit;
    }

    public String getName() {
        return name;
    }

    public String getPart_type() {
        return part_type;
    }

    public String getPurchased_by() {
        return purchased_by;
    }

    public int no_of_Att(){
        return NO_OF_ATTRIBUTES;
    }
}
