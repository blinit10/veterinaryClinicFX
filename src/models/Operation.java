/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author proyectoFinal
 */
public class Operation extends Service {
    private String reason;
    private int recoveryHours;
    private String anesthesiaName;
    private boolean isInObservation;
    private String postOperationStatus;

    public Operation(Date date, Animal animal, Veterinarian veterinarian, boolean isHouseCall, double basicCost,
                     String reason, int recoveryHours, String anesthesiaName,
                     boolean isInObservation, String postOperationStatus, String id) {
        super(date, animal, veterinarian, isHouseCall, basicCost, id);
        this.reason = reason;
        this.recoveryHours = recoveryHours;
        this.anesthesiaName = anesthesiaName;
        this.isInObservation = isInObservation;
        this.postOperationStatus = postOperationStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getRecoveryHours() {
        return recoveryHours;
    }

    public void setRecoveryHours(int recoveryHours) {
        this.recoveryHours = recoveryHours;
    }

    public String getAnesthesiaName() {
        return anesthesiaName;
    }

    public void setAnesthesiaName(String anesthesiaName) {
        this.anesthesiaName = anesthesiaName;
    }

    public boolean isIsInObservation() {
        return isInObservation;
    }

    public void setIsInObservation(boolean isInObservation) {
        this.isInObservation = isInObservation;
    }

    public String getPostOperationStatus() {
        return postOperationStatus;
    }

    public void setPostOperationStatus(String postOperationStatus) {
        this.postOperationStatus = postOperationStatus;
    }

    // getters and setters

    public Operation() {
    }

    @Override
    public String toString() {
        return "Operation{" + "Razón=" + reason + ", Horas de Recuperación=" + recoveryHours + ", Nombre de la Anestesia=" + anesthesiaName + ", Esta en observación=" + isInObservation + ", Estado PostOperatorio=" + postOperationStatus + '}';
    }
}
