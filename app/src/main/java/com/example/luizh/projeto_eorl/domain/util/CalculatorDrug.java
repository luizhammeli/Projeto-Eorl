package com.example.luizh.projeto_eorl.domain.util;

/**
 * Created by mac on 15/08/16.
 */
public class CalculatorDrug
{
    private String name;
    private int weight;
    private int dose;
    private double result;
    private int formMG;
    private int formML;
    private int dailyFrequency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getFormMG() {
        return formMG;
    }

    public void setFormMG(int formMG) {
        this.formMG = formMG;
    }

    public int getFormML() {
        return formML;
    }

    public void setFormML(int formML) {
        this.formML = formML;
    }

    public int getDailyFrequency() {
        return dailyFrequency;
    }

    public void setDailyFrequency(int dailyFrequency) {
        this.dailyFrequency = dailyFrequency;
    }
}
