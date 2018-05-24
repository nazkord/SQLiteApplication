package com.example.nazar.sqliteapplication;

/**
 * Created by nazar on 16.04.2017.
 */

public class Person {

    private int id;

    public Person(String personName, int ageOfPerson, String colorEye, String temperament) {
        this.personName = personName;
        this.ageOfPerson = ageOfPerson;
        this.colorEye = colorEye;
        this.temperament = temperament;
    }

    private String personName;
    private int ageOfPerson;
    private String colorEye;
    private String temperament;

    public Person() {

    }

    public Person(String personName) {
        this.personName = personName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getAgeOfPerson() {
        return ageOfPerson;
    }

    public void setAgeOfPerson(int ageOfPerson) {
        this.ageOfPerson = ageOfPerson;
    }

    public String getColorEye() {
        return colorEye;
    }

    public void setColorEye(String colorEye) {
        this.colorEye = colorEye;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

}
