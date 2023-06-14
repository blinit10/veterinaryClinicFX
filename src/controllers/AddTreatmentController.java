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
import models.Service;
import models.Treatment;
import models.Veterinarian;

public class AddTreatmentController implements Initializable {

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
    private TextField illnessField;

    @FXML
    private TextField treatmentField;

    @FXML
    private CheckBox hasClinicCuresField;
    
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
        Stage stage = (Stage) treatmentField.getScene().getWindow();
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
        String illness = illnessField.getText();
        String treatment = treatmentField.getText();
        boolean hasClinicCures = hasClinicCuresField.isSelected();
        if (this.id == null) {
            Treatment treatmentObj = new Treatment(date, animal, veterinarian, isHouseCall, basicCost,
                                               illness, treatment, hasClinicCures, UUID.randomUUID().toString());
            clinic.addService(treatmentObj);
        }else{
            for (int i = 0; i < clinic.getServices().size(); i++) {
                if (clinic.getService(i) instanceof Treatment && id == clinic.getService(i).getId()) {
                    Treatment serviceTreatment = new Treatment(date,animal,veterinarian,isHouseCall,basicCost,illness,treatment,hasClinicCures, id);
                    clinic.getServices().set(i, serviceTreatment);
                }
            }
            
        }
        closeWindow();
    }

    @FXML
    private void onCancelButtonClick() {
        closeWindow();
    }
    
    public void setTreatment(Treatment treatment) {
        id = treatment.getId();
        Date birthDate = treatment.getDate();
        Instant instant = birthDate.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        dateField.setValue(localDate);
        animalField.setValue(treatment.getAnimal());
        veterinarianField.setValue(treatment.getVeterinarian());
        houseCallField.setSelected(treatment.isIsHouseCall());
        basicCostField.setText(Double.toString(treatment.getBasicCost()));
        illnessField.setText(treatment.getIllness());
        treatmentField.setText(treatment.getIllness());
        hasClinicCuresField.setSelected(treatment.isHasClinicCures());
    }
}