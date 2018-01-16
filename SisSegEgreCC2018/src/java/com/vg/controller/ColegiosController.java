package com.vg.controller;

import com.vg.dao.ColegiosDao;
import com.vg.model.Colegios;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "colegiosController")
@SessionScoped
public class ColegiosController implements Serializable {

    Colegios col = new Colegios();
    private List<Colegios> lstColegios;
    private List<Colegios> lstColegios2;
    private List<Colegios> lstUbiCol;
    
    private Colegios selected;

    @PostConstruct
    public void inicio() {
        try {
            listaUbigeo();
            listarColegiosActivo();
            countColegio();
        } catch (Exception ex) {
            Logger.getLogger(ColegiosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarCol() throws Exception {
        ColegiosDao dao;
        try {
            dao = new ColegiosDao();
            dao.agregarCol(col);
            listarColegiosActivo();
            countColegio();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AGREGADO", "Nuevo colegio agregado."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error al agregar."));
            throw e;
        }
    }

    public void listarColegiosActivo() throws Exception {
        ColegiosDao dao;
        try {
            dao = new ColegiosDao();
            lstColegios = dao.lstColegiosActivo();
        } catch (Exception e) {
            throw e;
        }
    }
    

    public void jalarData() throws Exception {
        ColegiosDao dao;
        try {
            dao = new ColegiosDao();
            selected = dao.jalarData(selected.getCodigomodular());
        } catch (SQLException e) {
            throw e;
        }
    }

    public void editar() throws Exception {
        ColegiosDao dao;
        try {
            dao = new ColegiosDao();
            dao.editarColegio(selected);
            listarColegiosActivo();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ACTUALIZADO", "Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR", "Corregir los datos"));
        }
    }

    public void countColegio() {
        ColegiosDao dao;
        try {
            dao = new ColegiosDao();
            dao.countColegio(col);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void limpiar(){
        col.setCod_modular(null);
        col.setNom_colegio(null);
    }
    
    public void listaUbigeo() throws Exception {
        ColegiosDao dao;
        try {
            dao = new ColegiosDao();
            setLstUbiCol(dao.listaUbigeo());
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Colegios> getLstUbiCol() {
        return lstUbiCol;
    }

    public void setLstUbiCol(List<Colegios> lstUbiCol) {
        this.lstUbiCol = lstUbiCol;
    }
    
    public List<Colegios> getLstColegios2() {
        return lstColegios2;
    }

    public void setLstColegios2(List<Colegios> lstColegios2) {
        this.lstColegios2 = lstColegios2;
    }

    public List<Colegios> getLstColegios() {
        return lstColegios;
    }

    public void setLstColegios(List<Colegios> lstColegios) {
        this.lstColegios = lstColegios;
    }

    public Colegios getCol() {
        return col;
    }

    public void setCol(Colegios col) {
        this.col = col;
    }

    public Colegios getSelected() {
        return selected;
    }

    public void setSelected(Colegios selected) {
        this.selected = selected;
    }

}
