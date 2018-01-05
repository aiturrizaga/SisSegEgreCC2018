package com.vg.controller;

import com.vg.dao.TrazabilidadDao;
import com.vg.model.Trazabilidad;
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

@Named(value = "trazabilidadController")
@SessionScoped
public class TrazabilidadController implements Serializable {

    private List<Trazabilidad> lstTrazabildad;
    private Trazabilidad selected;

    @PostConstruct
    public void init() {
        try {
            mostrarTrazabilidad();
        } catch (SQLException ex) {

        }
    }

    public void mostrarTrazabilidad() throws SQLException {
        TrazabilidadDao dao;
        try {
            dao = new TrazabilidadDao();
            lstTrazabildad = dao.mostrarTrazabilidad();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void desasignarAlumno() throws Exception {
        TrazabilidadDao dao;
        try {
            dao = new TrazabilidadDao();
            dao.desasignarAlumno(selected.getCODTRAZ());
            dao.changeEstadoALU(selected.getCODEST());
            mostrarTrazabilidad();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DESASIGNADO", "Correctamente"));
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Trazabilidad> getLstTrazabildad() {
        return lstTrazabildad;
    }

    public void setLstTrazabildad(List<Trazabilidad> lstTrazabildad) {
        this.lstTrazabildad = lstTrazabildad;
    }

    public Trazabilidad getSelected() {
        return selected;
    }

    public void setSelected(Trazabilidad selected) {
        this.selected = selected;
    }

}
