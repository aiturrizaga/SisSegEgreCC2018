package com.vg.controller;

import com.vg.dao.RegistrosDao;
import com.vg.model.Registros;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

@Named(value = "registrosController")
@SessionScoped
public class RegistrosController implements Serializable {

    Registros reg = new Registros();
    private List<Registros> lstRegistros;
    private List<Registros> lstCbCarreras;
    private List<Registros> lstCbCursos;
    private String codCar, codCar2, nombrec;
    private String secc, secc2, curso;
    private boolean criterio = false;

    Date ahora = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

    @PostConstruct
    public void init() {
        try {
            reg.setFechaReg(formateador.format(ahora));
            setLstCbCarreras(null);
            setLstCbCursos(null);
            listaCbCarrera();
        } catch (Exception ex) {
            Logger.getLogger(RegistrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addCriterio() {
        criterio = true;
    }

    public void listaCbCarrera() throws Exception {
        RegistrosDao dao;
        try {
            dao = new RegistrosDao();
            setLstCbCarreras(dao.listaCbCarreras());
        } catch (Exception e) {
            throw e;
        }
    }

    public void listaCbCurso() throws Exception {
        RegistrosDao dao;
        try {
            dao = new RegistrosDao();
            lstCbCursos = dao.listaCbCursos(codCar);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listaCbCurso2() throws Exception {
        RegistrosDao dao;
        try {
            dao = new RegistrosDao();
            lstCbCursos = dao.listaCbCursos(codCar2);
        } catch (Exception e) {
            throw e;
        }
    }

    public void consultar() throws Exception {
        RegistrosDao dao;
        try {
            dao = new RegistrosDao();
            lstRegistros = dao.consulta(codCar, secc);
        } catch (Exception e) {
            throw e;
        }
    }

    public void addRegistros() throws Exception {
        RegistrosDao dao;
        try {
            dao = new RegistrosDao();
            for (Registros model : lstRegistros) {
                model.setCodCurReg(getCurso());
                model.setNomCriterio(getNombrec());
                model.setFechaReg(formateador.format(ahora));
                dao.addRegistros(model);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public String getNombrec() {
        return nombrec;
    }

    public void setNombrec(String nombrec) {
        this.nombrec = nombrec;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCodCar2() {
        return codCar2;
    }

    public void setCodCar2(String codCar2) {
        this.codCar2 = codCar2;
    }

    public String getSecc2() {
        return secc2;
    }

    public void setSecc2(String secc2) {
        this.secc2 = secc2;
    }

    public List<Registros> getLstCbCarreras() {
        return lstCbCarreras;
    }

    public void setLstCbCarreras(List<Registros> lstCbCarreras) {
        this.lstCbCarreras = lstCbCarreras;
    }

    public List<Registros> getLstCbCursos() {
        return lstCbCursos;
    }

    public void setLstCbCursos(List<Registros> lstCbCursos) {
        this.lstCbCursos = lstCbCursos;
    }

    public Registros getReg() {
        return reg;
    }

    public void setReg(Registros reg) {
        this.reg = reg;
    }

    public String getCodCar() {
        return codCar;
    }

    public void setCodCar(String codCar) {
        this.codCar = codCar;
    }

    public String getSecc() {
        return secc;
    }

    public void setSecc(String secc) {
        this.secc = secc;
    }

    public List<Registros> getLstRegistros() {
        return lstRegistros;
    }

    public void setLstRegistros(List<Registros> lstRegistros) {
        this.lstRegistros = lstRegistros;
    }

    public boolean isCriterio() {
        return criterio;
    }

    public void setCriterio(boolean criterio) {
        this.criterio = criterio;
    }

}
