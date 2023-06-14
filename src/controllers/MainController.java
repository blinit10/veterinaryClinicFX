/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Animal;
import models.Clinic;
import models.Operation;
import models.Service;
import models.Treatment;
import models.Vaccination;
import models.Veterinarian;
/**
 *
 * @author proyectoFinal
 */
public class MainController {
    private Clinic clinic;

    @FXML
    private HBox servicesCard;
    @FXML
    private HBox animalsCard;
    @FXML
    private HBox veterinariansCard;

    public MainController() {
        clinic = new Clinic();
    }
    
    private void updateAnimals(){
        ToggleGroup group = new ToggleGroup();
        for (Animal animal : clinic.getAnimals()) {
            VBox animalBox = new VBox();
            animalBox.setSpacing(5);
            animalBox.setPadding(new Insets(10));
            animalBox.setStyle("-fx-border-color: black; -fx-background-color: lightgray;");
            
            Label idLabel = new Label("ID: " + animal.getId());
            Label nameLabel = new Label("Nombre: " + animal.getName());
            Label speciesLabel = new Label("Especie: " + animal.getSpecies());
            Label breedLabel = new Label("Raza: " + animal.getBreed());
            Label sexLabel = new Label("Sexo: " + animal.getSex());
            Label hasChipLabel = new Label("Tiene Chip: " + (animal.isHasChip() ? "Yes" : "No"));
            Label ownerLabel = new Label("Dueño: " + (animal.getOwner() != null ? animal.getOwner().toString(): "None"));

            animalBox.getChildren().addAll(idLabel, nameLabel, speciesLabel, breedLabel, sexLabel, hasChipLabel, ownerLabel);

            RadioButton radioButton = new RadioButton();
            radioButton.setToggleGroup(group);
            radioButton.setGraphic(animalBox);

            animalsCard.getChildren().add(radioButton);
        }
    }
    
    private void updateServices(){
        ToggleGroup group = new ToggleGroup();
        for (Service service : clinic.getServices()) {
            VBox animalBox = new VBox();
            animalBox.setSpacing(5);
            animalBox.setPadding(new Insets(10));
            animalBox.setStyle("-fx-border-color: black; -fx-background-color: lightgray;");
            Label idLabel = new Label("ID: " + service.getId());
            Label dateLabel = new Label("Fecha: " + service.getDate().toString());
            Label animalLabel = new Label("" + service.getAnimal().toString());
            Label vetLabel = new Label("" + service.getVeterinarian().toString());
            Label isHouseCallLabel = new Label("Domicilio: " + (service.isIsHouseCall() ? "Si" : "No"));
            Label basiCostLabel = new Label("Costo: " + Double.toString(service.getBasicCost()));
            if (service instanceof Treatment) {
                Label illnessLabel = new Label("Enfermedad: " + ((Treatment)service).getIllness());
                Label treatmentLabel = new Label("Tratamiento: " + ((Treatment)service).getTreatment());
                Label hasClinicCuresLabel = new Label("Tiene Cura Clínica: " + (((Treatment)service).isHasClinicCures() ? "Si" : "No"));

                animalBox.getChildren().addAll(idLabel, dateLabel, animalLabel,
                        vetLabel, isHouseCallLabel, basiCostLabel,
                        illnessLabel, treatmentLabel, hasClinicCuresLabel);
            }else if (service instanceof Operation) {
                Label reasonLabel = new Label("Razón: " + ((Operation)service).getReason());
                Label recoveryHoursLabel = new Label("Horas de Recuperacón: " + Integer.toString(((Operation)service).getRecoveryHours()));
                Label anesthesiaNameLabel = new Label("Nombre de la Anestesia: " + ((Operation)service).getAnesthesiaName());
                Label isInObservationLabel = new Label("Está en Observación: " + (((Operation)service).isIsInObservation()? "Si" : "No"));
                Label postOperationStatusLabel = new Label("Estado PostOperatorio: " + ((Operation)service).getPostOperationStatus());

                animalBox.getChildren().addAll(idLabel, dateLabel, animalLabel,
                        vetLabel, isHouseCallLabel, basiCostLabel,
                        reasonLabel, recoveryHoursLabel, anesthesiaNameLabel,
                        isInObservationLabel, postOperationStatusLabel);
            }else{
                Label vaccineNameLabel = new Label("Nombre de la Vacuna: " + ((Vaccination)service).getVaccineName());
                Label isFirstTimeFieldLabel = new Label("Primera Dosis: " + (((Vaccination)service).isIsFirstTime()? "Si" : "No"));
                Label ispreventedIllnessesLabel = new Label("Previene: " + ((Vaccination)service).getPreventedIllnessString());
                Label nextDosesDatesLabel = new Label("Siguientes Dosis: " + ((Vaccination)service).getNextDosesDateString());
                animalBox.getChildren().addAll(idLabel, dateLabel, animalLabel,
                        vetLabel, isHouseCallLabel, basiCostLabel,
                        vaccineNameLabel, isFirstTimeFieldLabel, ispreventedIllnessesLabel,
                        nextDosesDatesLabel);
            }
            
            RadioButton radioButton = new RadioButton();
            radioButton.setToggleGroup(group);
            radioButton.setGraphic(animalBox);

            servicesCard.getChildren().add(radioButton);
        }
    }
    
    private void updateVeterinarians(){
        ToggleGroup group = new ToggleGroup();
        for (Veterinarian veterinarian : clinic.getVeterinarians()) {
            VBox veterinarianBox = new VBox();
            veterinarianBox.setSpacing(5);
            veterinarianBox.setPadding(new Insets(10));
            veterinarianBox.setStyle("-fx-border-color: black; -fx-background-color: lightgray;");
            
            Label idLabel = new Label("ID: " + veterinarian.getIdCard());
            Label nameLabel = new Label("Nombre: " + veterinarian.getName());
            Label sexLabel = new Label("Sexo: " + veterinarian.getSex());
            Label ageLabel = new Label("Edad: " + veterinarian.getAge());
            Label graduationYearLabel = new Label("Año: " + veterinarian.getGraduationYear());
            Label specialityLabel = new Label("Especialidad: " + veterinarian.getSpecialty());

            veterinarianBox.getChildren().addAll(idLabel, nameLabel, ageLabel, graduationYearLabel, sexLabel, specialityLabel);

            RadioButton radioButton = new RadioButton();
            radioButton.setToggleGroup(group);
            radioButton.setGraphic(veterinarianBox);

            veterinariansCard.getChildren().add(radioButton);
        }
    }
    
    @FXML
    private void initialize() {
        
        // Populate Services card
        updateServices();

        // Populate Animals card
        updateAnimals();

        // Populate Veterinarians card
        updateVeterinarians();
    }
    
    @FXML
    private void onAddVeterinarianButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addVeterinarianView.fxml"));
            Parent root = loader.load();
            AddVeterinarianController controller = loader.getController();
            controller.setClinic(clinic);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Update view
            veterinariansCard.getChildren().clear();
            updateVeterinarians();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void onUpdateVeterinarianButtonClick() {
        
        Veterinarian selectedVeterinarian = getSelectedVeterinarian();
        if (selectedVeterinarian == null) {
            // No animal is selected
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addVeterinarianView.fxml"));
            Parent root = loader.load();

            AddVeterinarianController controller = loader.getController();
            controller.setClinic(clinic);
            controller.setVeterinarian(selectedVeterinarian);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
            veterinariansCard.getChildren().clear();
            updateVeterinarians();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void onDeleteVeterinarianButtonClick() {
        
        Veterinarian selectedveterinarian = getSelectedVeterinarian();
        if (selectedveterinarian == null) {
            // No animal is selected
            return;
        }

        this.clinic.getVeterinarians().remove(selectedveterinarian);
        veterinariansCard.getChildren().clear();
        updateVeterinarians();
        
    }
    
    @FXML
    private void onAddAnimalButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addAnimalView.fxml"));
            Parent root = loader.load();
            AddAnimalController controller = loader.getController();
            controller.setClinic(clinic);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Update view
            animalsCard.getChildren().clear();
            updateAnimals();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void onUpdateAnimalButtonClick() {
        
        Animal selectedAnimal = getSelectedAnimal();
        if (selectedAnimal == null) {
            // No animal is selected
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addAnimalView.fxml"));
            Parent root = loader.load();

            AddAnimalController controller = loader.getController();
            controller.setClinic(clinic);
            controller.setAnimal(selectedAnimal);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
            animalsCard.getChildren().clear();
            updateAnimals();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void onDeleteAnimalButtonClick() {
        
        Animal selectedAnimal = getSelectedAnimal();
        if (selectedAnimal == null) {
            // No animal is selected
            return;
        }

        this.clinic.getAnimals().remove(selectedAnimal);
        animalsCard.getChildren().clear();
        updateAnimals();
        
    }

    private Animal getSelectedAnimal() {
        for (Node node : animalsCard.getChildren()) {
            RadioButton radioButton = (RadioButton) node;
            if (radioButton.isSelected()) {
                VBox animalBox = (VBox) radioButton.getGraphic();
                Label nameLabel = (Label) animalBox.getChildren().get(0);
                String name = nameLabel.getText().substring("ID: ".length());
                return clinic.getAnimals().stream()
                        .filter(animal -> animal.getId().equals(name))
                        .findFirst()
                        .orElse(null);
            }
        }
        return null;
    }
    
    private Veterinarian getSelectedVeterinarian() {
        for (Node node : veterinariansCard.getChildren()) {
            RadioButton radioButton = (RadioButton) node;
            if (radioButton.isSelected()) {
                VBox animalBox = (VBox) radioButton.getGraphic();
                Label nameLabel = (Label) animalBox.getChildren().get(0);
                String name = nameLabel.getText().substring("ID: ".length());
                return clinic.getVeterinarians().stream()
                        .filter(vet -> vet.getIdCard().equals(name))
                        .findFirst()
                        .orElse(null);
            }
        }
        return null;
    }
    
    private Service getSelectedService() {
        for (Node node : servicesCard.getChildren()) {
            RadioButton radioButton = (RadioButton) node;
            if (radioButton.isSelected()) {
                VBox animalBox = (VBox) radioButton.getGraphic();
                Label nameLabel = (Label) animalBox.getChildren().get(0);
                String name = nameLabel.getText().substring("ID: ".length());
                return clinic.getServices().stream()
                        .filter(vet -> vet.getId().equals(name))
                        .findFirst()
                        .orElse(null);
            }
        }
        return null;
    }
    
    @FXML
    private void onAddOperationButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addOperationView.fxml"));
            Parent root = loader.load();
            AddOperationController controller = loader.getController();
            controller.setClinic(clinic);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Update view
            servicesCard.getChildren().clear();
            updateServices();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void onAddVaccinationButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addvaccinationView.fxml"));
            Parent root = loader.load();
            AddVaccinationController controller = loader.getController();
            controller.setClinic(clinic);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Update view
            servicesCard.getChildren().clear();
            updateServices();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void onDetermineCostButtonClick(){
        Service selectedService = getSelectedService();
        if (selectedService == null) {
            // No animal is selected
            return;
        }
        
        double total = selectedService.getBasicCost();
        if (selectedService.isIsHouseCall()) {
            total += 25;
        }
        
        if (selectedService instanceof Treatment) {
            if (((Treatment)selectedService).isHasClinicCures()) {
                total += 10;
            }
        }else if (selectedService instanceof Vaccination) {
            if (((Vaccination)selectedService).isIsFirstTime()) {
                total+=5;
            }
            total += ((Vaccination)selectedService).getPreventedIllnesses().length*2;
        }else{
            if (((Operation)selectedService).getPostOperationStatus().equalsIgnoreCase("ingresado")) {
                total+=30;
                total+=((Operation)selectedService).getRecoveryHours()/2;
            }
        }
        if (selectedService.getAnimal().getSpecies().equalsIgnoreCase("perro")) {
            total -= (total*10/100);
        }else if (selectedService.getAnimal().getSpecies().equalsIgnoreCase("caballo")) {
            total += (total*15/100);
        }
        Alert alert = new Alert(AlertType.WARNING, 
                        "Costo Total: " + total,ButtonType.OK);
        Optional<ButtonType> result = alert.showAndWait();
                
    }
    
    private Operation longestOperation(String species, String sex) {
        Operation[] operations;
        int cantReal = 0;
        for (int i = 0; i < this.clinic.getServices().size(); i++) {
            if (this.clinic.getService(i) instanceof Operation) {
                cantReal++;
            }
        }
        operations = new Operation[cantReal];
        cantReal = 0;
        for (int i = 0; i < this.clinic.getServices().size(); i++) {
            System.out.println(i);
            if (this.clinic.getService(i) instanceof Operation) {
                operations[cantReal++] = ((Operation)this.clinic.getService(i));
            }
        }
        Operation longestOperation = null;
        int maxRecoveryHours = 0;
        for (Operation operation : operations) {
            Animal animal = operation.getAnimal();
            if (animal.getSpecies().equals(species) && animal.getSex().equals(sex)) {
                if (operation.getRecoveryHours() > maxRecoveryHours) {
                    longestOperation = operation;
                    maxRecoveryHours = operation.getRecoveryHours();
                }
            }
        }
        return longestOperation;
    }
    
    @FXML
    public void onDVaccinatedAnimalsButtonClick() {
        Vaccination[] vaccinations;
        int cantReal = 0;
        for (int i = 0; i < this.clinic.getServices().size(); i++) {
            if (this.clinic.getService(i) instanceof Vaccination) {
                cantReal++;
            }
        }
        vaccinations = new Vaccination[cantReal];
        cantReal = 0;
        for (int i = 0; i < this.clinic.getServices().size(); i++) {
            if (this.clinic.getService(i) instanceof Vaccination) {
                vaccinations[cantReal++] = ((Vaccination)this.clinic.getService(i));
            }
        }
        System.out.println(vaccinations);
        List<Animal> vaccinatedAnimals = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);
        for (Vaccination vaccination : vaccinations) {
            Date date = vaccination.getDate();
            cal.setTime(date);
            int vaccinationMonth = cal.get(Calendar.MONTH);
            int vaccinationYear = cal.get(Calendar.YEAR);
            if (vaccinationMonth == currentMonth && vaccinationYear == currentYear) {
                Animal animal = vaccination.getAnimal();
                vaccinatedAnimals.add(animal);
            }
        }
        Alert alert = new Alert(AlertType.WARNING, 
                        "Animales Vacunados este mes: " + vaccinatedAnimals.toString(),ButtonType.OK);
        Optional<ButtonType> result = alert.showAndWait();
    }
    
    
    @FXML
    private void onDetermineLongestOperationButtonClick(){
        Stage stage = new Stage();
        stage.setTitle("Operación más larga");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Label speciesLabel = new Label("Especie:");
        TextField speciesField = new TextField();
        grid.add(speciesLabel, 0, 0);
        grid.add(speciesField, 1, 0);

        Label sexLabel = new Label("Sexo:");
        TextField sexField = new TextField();
        grid.add(sexLabel, 0, 1);
        grid.add(sexField, 1, 1);

        Button submitButton = new Button("Buscar");
        submitButton.setOnAction(e -> {
            String species = speciesField.getText();
            String sex = sexField.getText();
            Alert alert = new Alert(AlertType.WARNING, 
                        "Operacion mas larga: " + longestOperation(species, sex).toString(),ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            // do something with the entered species and sex
            stage.close();
        });
        grid.add(submitButton, 1, 2);

        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void onDetermineAmountOfAnimalsGivenOwnerButtonClick(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Enter your text:");
        Optional<String> results = dialog.showAndWait();
        int total = 0;
        String id = results.get();
        for (int i = 0; i < clinic.getAnimals().size(); i++) {
            if (clinic.getAnimal(i).getOwner().getId().equalsIgnoreCase(id)) {
                total++;
            }
        }
        Alert alert = new Alert(AlertType.WARNING, 
                        "Animales de " + id + ": " + total,ButtonType.OK);
        Optional<ButtonType> result = alert.showAndWait();
                
    }
    
    private String mostFrecuentSpecies(){
        Animal animals[] = new Animal[this.clinic.getAnimals().size()];
        Map<String, Integer> speciesCount = new HashMap<>();
        for (Animal animal : this.clinic.getAnimals().toArray(animals)) {
            String species = animal.getSpecies();
            speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
        }
        String mostFrequentSpecies = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : speciesCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentSpecies = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mostFrequentSpecies;
    }
    
    
    @FXML
    private void onDetermineMostFrecuentSpeciesButtonClick() {
        Alert alert = new Alert(AlertType.WARNING, 
                        "Especie más frecuente: " + mostFrecuentSpecies(),ButtonType.OK);
        Optional<ButtonType> result = alert.showAndWait();
    }
    
    @FXML
    private void onAddTreatmentButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addTreatmentView.fxml"));
            Parent root = loader.load();
            AddTreatmentController controller = loader.getController();
            controller.setClinic(clinic);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Update view
            servicesCard.getChildren().clear();
            updateServices();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void onUpdateServiceButtonClick() {
        Service selectedService = getSelectedService();
        if (selectedService == null) {
            // No animal is selected
            return;
        }

        if (selectedService instanceof Treatment) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addTreatmentView.fxml"));
                Parent root = loader.load();

                AddTreatmentController controller = loader.getController();
                controller.setClinic(clinic);
                controller.setTreatment(((Treatment)selectedService));

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                servicesCard.getChildren().clear();
                updateServices();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (selectedService instanceof Operation) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addOperationView.fxml"));
                Parent root = loader.load();

                AddOperationController controller = loader.getController();
                controller.setClinic(clinic);
                controller.setOperation(((Operation)selectedService));

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                servicesCard.getChildren().clear();
                updateServices();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addVaccinationView.fxml"));
                Parent root = loader.load();

                AddVaccinationController controller = loader.getController();
                controller.setClinic(clinic);
                controller.setVaccination(((Vaccination)selectedService));

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                servicesCard.getChildren().clear();
                updateServices();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    private void onDeleteServiceButtonClick() {
        Service selectedService = getSelectedService();
        if (selectedService == null) {
            // No animal is selected
            return;
        }

        this.clinic.getServices().remove(selectedService);
        servicesCard.getChildren().clear();
        updateServices();
    }
}
