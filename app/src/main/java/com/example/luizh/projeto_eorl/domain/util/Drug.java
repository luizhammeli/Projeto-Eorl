package com.example.luizh.projeto_eorl.domain.util;

import java.io.Serializable;

/**
 * Created by luizhammerli on 16/10/16.
 */
public class Drug implements Serializable {


    private String presentation;
    private String age;
    private String contradiction;
    private String dosage;
    private String pregnancy;
    private String lactation;
    private String drugsInteractions;
    private String reactions;
    private String failure;
    private String importantInformation;
    private String name;


    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContradiction() {
        return contradiction;
    }

    public void setContradiction(String contradiction) {
        this.contradiction = contradiction;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(String pregnancy) {
        this.pregnancy = pregnancy;
    }

    public String getLactation() {
        return lactation;
    }

    public void setLactation(String lactation) {
        this.lactation = lactation;
    }

    public String getDrugsInteractions() {
        return drugsInteractions;
    }

    public void setDrugsInteractions(String drugsInteractions) {
        this.drugsInteractions = drugsInteractions;
    }

    public String getReactions() {
        return reactions;
    }

    public void setReactions(String reactions) {
        this.reactions = reactions;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }

    public String getImportantInformation() {
        return importantInformation;
    }

    public void setImportantInformation(String importantInformation) {
        this.importantInformation = importantInformation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
