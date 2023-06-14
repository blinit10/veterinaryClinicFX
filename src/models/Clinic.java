/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author proyectoFinal
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Clinic {
    private List<Animal> animals;
    private List<Veterinarian> veterinarians;
    private List<Service> services;
    private List<Owner> owners;

    private void initializeOwners() {
        String[] names = {"Alice", "Bob", "Charlie", "Dave", "Eve"};
        String[] ids = {"961008008269", "96100808268", "96100808267", "96100808266", "96100808265"};
        String[] sexes = {"Female", "Male"};
        String[] addresses = {"123 Main St", "456 Elm St", "789 Oak St"};

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            boolean exists;
            String id;
            do {
                exists = false;
                id = ids[random.nextInt(ids.length)];
                for (int j = 0; j < this.owners.size(); j++) {
                    if (this.owners.get(j).getId() == id) {
                        exists = true;

                    }
                }
            } while (exists);
            
            String name = names[random.nextInt(names.length)];
            String sex = sexes[random.nextInt(sexes.length)];
            int age = random.nextInt(100) + 1;
            String address = addresses[random.nextInt(addresses.length)];

            Owner owner = new Owner(id, name, sex, age, address);
            owners.add(owner);
        }
    }
    
    public Clinic(List<Animal> animals, List<Veterinarian> veterinarians, List<Service> services) {
        this.animals = animals;
        this.veterinarians = veterinarians;
        this.services = services;
        this.owners = new ArrayList<>();
        initializeOwners();
    }
    
    public Clinic() {
        this.animals = new ArrayList<>();
        this.veterinarians = new ArrayList<>();
        this.services = new ArrayList<>();
        this.owners = new ArrayList<>();
        initializeOwners();
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }
    
    
        
    // getters and setters
    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Veterinarian> getVeterinarians() {
        return veterinarians;
    }

    public void setVeterinarians(List<Veterinarian> veterinarians) {
        this.veterinarians = veterinarians;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
    
    //animal crud
    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    public Animal getAnimal(int index) {
        return this.animals.get(index);
    }

    public void updateAnimal(int index, Animal animal) {
        this.animals.set(index, animal);
    }

    public void deleteAnimal(int index) {
        this.animals.remove(index);
    }
    
    //veterinarian crud
    public void addVeterinarian(Veterinarian veterinarian) {
        this.veterinarians.add(veterinarian);
    }

    public Veterinarian getVeterinarian(int index) {
        return this.veterinarians.get(index);
    }

    public void updateVeterinarian(int index, Veterinarian veterinarian) {
        this.veterinarians.set(index, veterinarian);
    }

    public void deleteVeterinarian(int index) {
        this.veterinarians.remove(index);
    }
    
    //service crud
    public void addService(Service service) {
        this.services.add(service);
    }

    public Service getService(int index) {
        return this.services.get(index);
    }

    public void updateService(int index, Service service) {
        this.services.set(index, service);
    }

    public void deleteService(int index) {
        this.services.remove(index);
    }
}