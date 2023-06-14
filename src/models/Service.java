/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author proyectoFinal
 */
import java.util.Date;

public abstract class Service {
    private String id;
    private Date date;
    private Animal animal;
    private Veterinarian veterinarian;
    private boolean isHouseCall;
    private double basicCost;

    public Service(Date date, Animal animal, Veterinarian veterinarian, boolean isHouseCall, double basicCost, String id) {
        this.id = id;
        this.date = date;
        this.animal = animal;
        this.veterinarian = veterinarian;
        this.isHouseCall = isHouseCall;
        this.basicCost = basicCost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(Veterinarian veterinarian) {
        this.veterinarian = veterinarian;
    }

    public boolean isIsHouseCall() {
        return isHouseCall;
    }

    public void setIsHouseCall(boolean isHouseCall) {
        this.isHouseCall = isHouseCall;
    }

    public double getBasicCost() {
        return basicCost;
    }

    public void setBasicCost(double basicCost) {
        this.basicCost = basicCost;
    }

    // getters and setters

    public Service() {
    }
}