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

@Named(value = "trazabilidadController")
@SessionScoped
public class TrazabilidadController implements Serializable {
    
    private List<Trazabilidad> lstTrazabildad;
    
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

    public List<Trazabilidad> getLstTrazabildad() {
        return lstTrazabildad;
    }

    public void setLstTrazabildad(List<Trazabilidad> lstTrazabildad) {
        this.lstTrazabildad = lstTrazabildad;
    }
    
}
