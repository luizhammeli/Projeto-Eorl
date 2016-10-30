package com.example.luizh.projeto_eorl.domain.util;

import java.io.Serializable;

/**
 * Created by mac on 02/08/16.
 */
public class Diagnosis implements Serializable{

    private String Nome;
    private String quadroClinico;
    private String examefisico;
    private String atencao;
    private String antibiotico;
    private String alergia;
    private String medicacoesAssociadas;
    private String desc;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getQuadroClinico() {
        return quadroClinico;
    }

    public void setQuadroClinico(String quadroClinico) {
        this.quadroClinico = quadroClinico;
    }

    public String getExamefisico() {
        return examefisico;
    }

    public void setExamefisico(String examefisico) {
        this.examefisico = examefisico;
    }

    public String getAtencao() {
        return atencao;
    }

    public void setAtencao(String atencao) {
        this.atencao = atencao;
    }

    public String getAntibiotico() {
        return antibiotico;
    }

    public void setAntibiotico(String antibiotico) {
        this.antibiotico = antibiotico;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getMedicacoesAssociadas() {
        return medicacoesAssociadas;
    }

    public void setMedicacoesAssociadas(String medicacoesAssociadas) {
        this.medicacoesAssociadas = medicacoesAssociadas;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
