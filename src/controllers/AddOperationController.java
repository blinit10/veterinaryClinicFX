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
import models.Operation;
import models.Veterinarian;

public class AddOperationController implements Initializable {

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
    private TextField reasonField;

    @FXML
    private TextField recoveryHoursField;

    @FXML
    private TextField anesthesiaNameField;

    @FXML
    private CheckBox isInObservationField;

    @FXML
    private TextField postOperationStatusField;
    
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
        Stage stage = (Stage) reasonField.getScene().getWindow();
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
        String reason = reasonField.getText();
        int recoveryHours = Integer.parseInt(recoveryHoursField.getText());
        String anesthesiaName = anesthesiaNameField.getText();
        boolean isInObservation = isInObservationField.isSelected();
        String postOperationStatus = postOperationStatusField.getText();
        if (this.id == null) {
            Operation operation = new Operation(date, animal, veterinarian, isHouseCall, basicCost,
                                            reason, recoveryHours, anesthesiaName,
                                            isInObservation, postOperationStatus, UUID.randomUUID().toString());
            clinic.addService(operation);
        }else{
            for (int i = 0; i < clinic.getServices().size(); i++) {
                if (clinic.getService(i) instanceof Operation && id == clinic.getService(i).getId()) {
                    Operation operation = new Operation(date,animal,veterinarian,
                            isHouseCall,basicCost,reason,recoveryHours,
                            anesthesiaName, isInObservation, postOperationStatus, id);
                    clinic.getServices().set(i, operation);
                }
            }
        }
        closeWindow();
    }

    @FXML
    private void onCancelButtonClick() {
        closeWindow();
    }
    
    public void setOperation(Operation operation) {
        id = operation.getId();
        Date birthDate = operation.getDate();
        Instant instant = birthDate.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        dateField.setValue(localDate);
        animalField.setValue(operation.getAnimal());
        veterinarianField.setValue(operation.getVeterinarian());
        houseCallField.setSelected(operation.isIsHouseCall());
        basicCostField.setText(Double.toString(operation.getBasicCost()));
        reasonField.setText(operation.getReason());
        recoveryHoursField.setText(Integer.toString(operation.getRecoveryHours()));
        anesthesiaNameField.setText(operation.getAnesthesiaName());
        isInObservationField.setSelected(operation.isIsInObservation());
        postOperationStatusField.setText(operation.getPostOperationStatus());
    }
    
}