/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import models.Animal;
import models.Clinic;
import models.Owner;

/**
 *
 * @author proyectoFinal
 */
public class AddAnimalController {
    private Clinic clinic;
    private String id = null;

  @FXML
    private TextField nameField;
    @FXML
    private TextField speciesField;
    @FXML
    private TextField breedField;
    @FXML
    private ComboBox<String> sexField;
    @FXML
    private CheckBox hasChipField;
    @FXML
    private ComboBox<Owner> ownerField;
    // ...

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
        String[] sexArray = {"Hembra", "Macho"};
        sexField.setItems(FXCollections.observableArrayList(sexArray));
        // Populate ownerField ComboBox
        ownerField.setItems(FXCollections.observableArrayList(clinic.getOwners()));
        ownerField.setConverter(new StringConverter<Owner>() {
            @Override
            public String toString(Owner owner) {
                if (owner == null) {
                    return "";
                }
                return owner.toString();
            }

            @Override
            public Owner fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void onCancelButtonClick() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
    
    
    @FXML
    private void onSaveButtonClick() {
        if (this.id == null) {
            String name = nameField.getText();
            String species = speciesField.getText();
            String breed = breedField.getText();
            String sex = sexField.getValue();
            boolean hasChip = hasChipField.isSelected();
            Owner owner = ownerField.getValue();

            Animal animal = new Animal();
            animal.setId(UUID.randomUUID().toString());
            animal.setName(name);
            animal.setSpecies(species);
            animal.setBreed(breed);
            animal.setSex(sex);
            animal.setHasChip(hasChip);
            animal.setOwner(owner);

            clinic.addAnimal(animal);

            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        }else{
            String name = nameField.getText();
            String species = speciesField.getText();
            String breed = breedField.getText();
            String sex = sexField.getValue();
            boolean hasChip = hasChipField.isSelected();
            Owner owner = ownerField.getValue();
            
            Animal existingAnimal = clinic.getAnimals().stream()
                        .filter(animal -> animal.getId().equals(this.id))
                        .findFirst().orElse(new Animal());
            existingAnimal.setId(this.id);
            existingAnimal.setName(name);
            existingAnimal.setSpecies(species);
            existingAnimal.setBreed(breed);
            existingAnimal.setSex(sex);
            existingAnimal.setHasChip(hasChip);
            existingAnimal.setOwner(owner);

            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.close();
        }
    }
    
    public void setAnimal(Animal animal) {
        id = animal.getId();
        nameField.setText(animal.getName());
        speciesField.setText(animal.getSpecies());
        breedField.setText(animal.getBreed());
        sexField.setValue(animal.getSex());
        hasChipField.setSelected(animal.isHasChip());
        ownerField.setValue(animal.getOwner());
    }
    
}
