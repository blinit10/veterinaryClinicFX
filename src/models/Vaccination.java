/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Arrays;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Date;

/**
 *
 * @author proyectoFinal
 */
public class Vaccination extends Service {
    private String vaccineName;
    private String[] preventedIllnesses;
    private boolean isFirstTime;
    private Date[] nextDosesDates;

    public Vaccination(Date date, Animal animal, Veterinarian veterinarian,
                       boolean isHouseCall, double basicCost,
                       String vaccineName,
                       boolean isFirstTime, String id) {
        super(date, animal, veterinarian,isHouseCall,basicCost, id);
        this.vaccineName = vaccineName;
        this.isFirstTime = isFirstTime;

        // Populate preventedIllnesses with 3-5 random illnesses
        List<String> illnesses = Arrays.asList("Rabies", "Distemper", "Parvovirus",
                "Hepatitis", "Leptospirosis", "Parainfluenza", "Bordetella",
                "Lyme disease", "Coronavirus", "Giardia");
        Random rand = new Random();
        int numIllnesses = rand.nextInt(3) + 3;
        this.preventedIllnesses = new String[numIllnesses];
        for (int i = 0; i < numIllnesses; i++) {
            int index = rand.nextInt(illnesses.size());
            this.preventedIllnesses[i] = illnesses.get(index);
        }

        // Populate nextDosesDates with 1-2 random dates
        int numDates = rand.nextInt(2) + 1;
        this.nextDosesDates = new Date[numDates];
        for (int i = 0; i < numDates; i++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, rand.nextInt(12) + 1);
            this.nextDosesDates[i] = cal.getTime();
        }
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String[] getPreventedIllnesses() {
        return preventedIllnesses;
    }

    public void setPreventedIllnesses(String[] preventedIllnesses) {
        this.preventedIllnesses = preventedIllnesses;
    }

    public boolean isIsFirstTime() {
        return isFirstTime;
    }

    public void setIsFirstTime(boolean isFirstTime) {
        this.isFirstTime = isFirstTime;
    }

    public Date[] getNextDosesDates() {
        return nextDosesDates;
    }

    public void setNextDosesDates(Date[] nextDosesDates) {
        this.nextDosesDates = nextDosesDates;
    }

    // getters and setters

    public Vaccination() {
    }
    
    public String getPreventedIllnessString(){
        String preventedIllness = "";
        for (int i = 0; i < this.preventedIllnesses.length; i++) {
            preventedIllness += this.preventedIllnesses[i];
            if (i< this.preventedIllnesses.length - 1) {
                preventedIllness += ", ";
            }
        }
        return preventedIllness;
    }
    
    public String getNextDosesDateString(){
        String nextDosesDate = "";
        for (int i = 0; i < this.nextDosesDates.length; i++) {
            nextDosesDate += this.nextDosesDates[i].toString();
            if (i< this.nextDosesDates.length - 1) {
                nextDosesDate += ", ";
            }
        }
        return nextDosesDate;
    }
}