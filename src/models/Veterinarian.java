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

public class Veterinarian {
    private String idCard;
    private String name;
    private String sex;
    private int age;
    private Date birthDate;
    private int graduationYear;
    private String specialty;

    @Override
    public String toString() {
        return "Veterinario " + idCard + ", Nombre=" + name + ", Sexo=" + sex + ", Edad=" + age + ", Fecha de Nacimiento=" + birthDate + ", Año de Graduación=" + graduationYear + ", Especialidad=" + specialty;
    }

    public Veterinarian(String idCard, String name, String sex, int age, Date birthDate, int graduationYear, String specialty) {
        this.idCard = idCard;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birthDate = birthDate;
        this.graduationYear = graduationYear;
        this.specialty = specialty;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    // getters and setters

    public Veterinarian() {
    }
}