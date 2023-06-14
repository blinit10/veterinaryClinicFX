/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author proyectoFinal
 */
public class Animal {
    private String id;

    @Override
    public String toString() {
        return "Animal " +  id + ", Nombre=" + name + ", Especie=" + species + ", Raza=" + breed + ", Sexo=" + sex + ", Tiene Chip=" + hasChip + ", Dueño=" + owner.toString();
    }
    private String name;
    private String species;
    private String breed;
    private String sex;
    private boolean hasChip;
    private Owner owner;

    public Animal(String id, String name, String species, String breed, String sex, boolean hasChip, Owner owner) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.sex = sex;
        this.hasChip = hasChip;
        this.owner = owner;
    }

    // getters and setters

    public Animal() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isHasChip() {
        return hasChip;
    }

    public void setHasChip(boolean hasChip) {
        this.hasChip = hasChip;
    }

    public Owner getOwner() {
        return owner;
    }
    
    
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    
    
    
    
}
