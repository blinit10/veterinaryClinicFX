package controllers;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import models.Clinic;
import models.Owner;
import models.Veterinarian;

public class AddVeterinarianController implements Initializable {

    @FXML
    private TextField idCardField;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> sexField;

    @FXML
    private TextField ageField;

    @FXML
    private DatePicker birthDateField;

    @FXML
    private TextField graduationYearField;

    @FXML
    private TextField specialtyField;
    
    private String id = null;
    private Clinic clinic;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sexField.getItems().addAll("Masculino", "Femenino", "LGBTQ+");
    }
    
    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
        
    }
    
    @FXML
    private void onSaveButtonClick() {
        String idCard = idCardField.getText();
        String name = nameField.getText();
        String sex = sexField.getValue();
        int age = Integer.parseInt(ageField.getText());
        LocalDate localDate = birthDateField.getValue();
        Date birthDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        int graduationYear = Integer.parseInt(graduationYearField.getText());
        String specialty = specialtyField.getText();
        if (this.id == null) {
            Veterinarian veterinarian = new Veterinarian(idCard, name, sex, age, birthDate, graduationYear, specialty);
            clinic.addVeterinarian(veterinarian);
        }else{
            Veterinarian existingVet = clinic.getVeterinarians().stream()
                        .filter(vet -> vet.getIdCard().equals(this.id))
                        .findFirst().orElse(new Veterinarian());
            existingVet.setAge(age);
            existingVet.setBirthDate(birthDate);
            existingVet.setGraduationYear(graduationYear);
            existingVet.setIdCard(idCard);
            existingVet.setName(name);
            existingVet.setSex(sex);
            existingVet.setSpecialty(specialty);
        }
        closeWindow();
        // Add the veterinarian to the clinic here
    }
    
    private void closeWindow(){
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void onCancelButtonClick() {
        closeWindow();
    }
    
    public void setVeterinarian(Veterinarian veterinarian) {
        id = veterinarian.getIdCard();
        idCardField.setText(veterinarian.getIdCard());
        nameField.setText(veterinarian.getName());
        sexField.setValue(veterinarian.getSex());
        ageField.setText(Integer.toString(veterinarian.getAge()));
        Date birthDate = veterinarian.getBirthDate();
        Instant instant = birthDate.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        birthDateField.setValue(localDate);
        graduationYearField.setText(Integer.toString(veterinarian.getGraduationYear()));
        specialtyField.setText(veterinarian.getSpecialty());
    }
}