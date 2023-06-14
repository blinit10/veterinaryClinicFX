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

public class Treatment extends Service {
    private String illness;
    private String treatment;
    private boolean hasClinicCures;

    public Treatment(Date date, Animal animal, Veterinarian veterinarian, boolean isHouseCall, double basicCost,
                     String illness, String treatment, boolean hasClinicCures, String id) {
        super(date, animal, veterinarian, isHouseCall, basicCost, id);
        this.illness = illness;
        this.treatment = treatment;
        this.hasClinicCures = hasClinicCures;
    }

    // getters and setters

    public Treatment() {
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public boolean isHasClinicCures() {
        return hasClinicCures;
    }

    public void setHasClinicCures(boolean hasClinicCures) {
        this.hasClinicCures = hasClinicCures;
    }
    
    
}