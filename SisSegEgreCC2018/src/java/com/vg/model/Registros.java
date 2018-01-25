package com.vg.model;

public class Registros {

    private String numOrdenReg, codTrazReg, notasReg = "0", fechaReg, nomCriterio, nomPerReg, seccionReg;
    private boolean asisReg = true;
    private String codCarReg, nomCarReg, codCurReg, nomCurReg;
    
    private String numOrdenView, nomPerView, asisView,notaView,codRegView;
     private String NOMBRES,NOTAS,FECHA,ASISTENCIA;

    public String getNotaView() {
        return notaView;
    }

    public void setNotaView(String notaView) {
        this.notaView = notaView;
    }

    public String getCodRegView() {
        return codRegView;
    }

    public void setCodRegView(String codRegView) {
        this.codRegView = codRegView;
    }
     
    public String getNOMBRES() {
        return NOMBRES;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

    public String getNOTAS() {
        return NOTAS;
    }

    public void setNOTAS(String NOTAS) {
        this.NOTAS = NOTAS;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getASISTENCIA() {
        return ASISTENCIA;
    }

    public void setASISTENCIA(String ASISTENCIA) {
        this.ASISTENCIA = ASISTENCIA;
    }

     
    public String getNumOrdenView() {
        return numOrdenView;
    }

    public void setNumOrdenView(String numOrdenView) {
        this.numOrdenView = numOrdenView;
    }

    public String getNomPerView() {
        return nomPerView;
    }

    public void setNomPerView(String nomPerView) {
        this.nomPerView = nomPerView;
    }

    public String getAsisView() {
        return asisView;
    }

    public void setAsisView(String asisView) {
        this.asisView = asisView;
    }

    public String getNomPerReg() {
        return nomPerReg;
    }

    public void setNomPerReg(String nomPerReg) {
        this.nomPerReg = nomPerReg;
    }

    public String getNomCriterio() {
        return nomCriterio;
    }

    public void setNomCriterio(String nomCriterio) {
        this.nomCriterio = nomCriterio;
    }

    public String getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(String fechaReg) {
        this.fechaReg = fechaReg;
    }

    public boolean isAsisReg() {
        return asisReg;
    }

    public void setAsisReg(boolean asisReg) {
        this.asisReg = asisReg;
    }

    public String getNotasReg() {
        return notasReg;
    }

    public void setNotasReg(String notasReg) {
        this.notasReg = notasReg;
    }

    public String getCodCurReg() {
        return codCurReg;
    }

    public void setCodCurReg(String codCurReg) {
        this.codCurReg = codCurReg;
    }

    public String getNomCurReg() {
        return nomCurReg;
    }

    public void setNomCurReg(String nomCurReg) {
        this.nomCurReg = nomCurReg;
    }

    public String getNumOrdenReg() {
        return numOrdenReg;
    }

    public void setNumOrdenReg(String numOrdenReg) {
        this.numOrdenReg = numOrdenReg;
    }

    public String getCodTrazReg() {
        return codTrazReg;
    }

    public void setCodTrazReg(String codTrazReg) {
        this.codTrazReg = codTrazReg;
    }

    public String getCodCarReg() {
        return codCarReg;
    }

    public void setCodCarReg(String codCarReg) {
        this.codCarReg = codCarReg;
    }

    public String getNomCarReg() {
        return nomCarReg;
    }

    public void setNomCarReg(String nomCarReg) {
        this.nomCarReg = nomCarReg;
    }

    public String getSeccionReg() {
        return seccionReg;
    }

    public void setSeccionReg(String seccionReg) {
        this.seccionReg = seccionReg;
    }
}
