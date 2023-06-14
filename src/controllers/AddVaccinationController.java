package controllers;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import models.Animal;
import models.Clinic;
import models.Vaccination;
import models.Veterinarian;

public class AddVaccinationController implements Initializable {

    @FXML
    private DatePicker dateField;

    @FXML
    private ComboBox<Animal> animalField;

    @FXML
    private ComboBox<Veterinarian> veterinarianField;

    @FXML
    private CheckBox houseCallField;

    @FXML
    private TextField basicCostField;

    @FXML
    private TextField vaccineNameField;

    @FXML
    private CheckBox isFirstTimeField;
    
    private Clinic clinic;
    private String id = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate the animal and veterinarian ComboBoxes here
    }
    
    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
        // Populate ownerField ComboBox
        veterinarianField.setItems(FXCollections.observableArrayList(clinic.getVeterinarians()));
        veterinarianField.setConverter(new StringConverter<Veterinarian>() {
            @Override
            public String toString(Veterinarian veterinarian) {
                if (veterinarian == null) {
                    return "";
                }
                return veterinarian.toString();
            }

            @Override
            public Veterinarian fromString(String string) {
                return null;
            }
        });
        
        animalField.setItems(FXCollections.observableArrayList(clinic.getAnimals()));
        animalField.setConverter(new StringConverter<Animal>() {
            @Override
            public String toString(Animal animal) {
                if (animal == null) {
                    return "";
                }
                return animal.toString();
            }

            @Override
            public Animal fromString(String string) {
                return null;
            }
        });
    }
    
    private void closeWindow(){
        Stage stage = (Stage) vaccineNameField.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void onSaveButtonClick() {
        LocalDate localDate = dateField.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Animal animal = animalField.getValue();
        Veterinarian veterinarian = veterinarianField.getValue();
        boolean isHouseCall = houseCallField.isSelected();
        double basicCost = Double.parseDouble(basicCostField.getText());
        String vaccineName = vaccineNameField.getText();
        boolean isFirstTime = isFirstTimeField.isSelected();
        
        if (this.id == null) {
            Vaccination vaccination = new Vaccination(date, animal, veterinarian,
                                                  isHouseCall, basicCost,
                                                  vaccineName, isFirstTime,
                                                  UUID.randomUUID().toString());
            clinic.addService(vaccination);
        }else{
            for (int i = 0; i < clinic.getServices().size(); i++) {
                if (clinic.getService(i) instanceof Vaccination && id == clinic.getService(i).getId()) {
                    Vaccination vaccination = new Vaccination(date,animal,veterinarian,
                            isHouseCall,basicCost,vaccineName,isFirstTime, id);
                    clinic.getServices().set(i, vaccination);
                }
            }
        }
        closeWindow();
    }

    @FXML
    private void onCancelButtonClick() {
        closeWindow();
    }
    
    public void setVaccination(Vaccination vaccination) {
        id = vaccination.getId();
        Date birthDate = vaccination.getDate();
        Instant instant = birthDate.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        dateField.setValue(localDate);
        animalField.setValue(vaccination.getAnimal());
        veterinarianField.setValue(vaccination.getVeterinarian());
        houseCallField.setSelected(vaccination.isIsHouseCall());
        basicCostField.setText(Double.toString(vaccination.getBasicCost()));
        vaccineNameField.setText(vaccination.getVaccineName());
        isFirstTimeField.setSelected(vaccination.isIsFirstTime());
    }
}