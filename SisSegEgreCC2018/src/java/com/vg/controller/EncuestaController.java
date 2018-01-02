package com.vg.controller;

import com.vg.dao.EncuestaDao;
import com.vg.model.Encuesta;
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

@Named(value = "encuestaController")
@SessionScoped
public class EncuestaController implements Serializable {

    EncuestaDao eD = new EncuestaDao();
    Encuesta al = new Encuesta();
    private List<Encuesta> lstActivo;

    @PostConstruct
    public void ini() {
        try {
            listarEncuesta();
        } catch (Exception ex) {
            Logger.getLogger(EncuestaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> completeTextCol(String query) throws SQLException {
        EncuestaDao dao = new EncuestaDao();
        return dao.queryAutoCompleteEst(query);
    }

    public void preguntasTodas() throws Exception {
        try {
            al.setCodigo(eD.leerEst(al.getCodigo()));
            eD.registrarPrimeraPregunta(al);
            eD.registrarSegundaPregunta(al);
            eD.registrarTerceraPregunta(al);
            eD.registrarCuartaPregunta(al);
            listarEncuesta();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INGRESADO","Registrado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error","Error al registrar"));
            throw e;
        }
    }

    public void listarEncuesta() throws Exception {
        EncuestaDao dao;
        try {
            dao = new EncuestaDao();
            lstActivo = dao.lstEncuesta();
        } catch (Exception e) {
            throw e;
        }
    }

    public void limpiar() {
        al.setCodigo(null);
        al.setPregunta1(null);
        al.setPregunta2(null);
        al.setPregunta3(null);
        al.setPregunta4(null);
    }

    public EncuestaDao geteD() {
        return eD;
    }

    public void seteD(EncuestaDao eD) {
        this.eD = eD;
    }

    public Encuesta getAl() {
        return al;
    }

    public void setAl(Encuesta al) {
        this.al = al;
    }

    public List<Encuesta> getLstActivo() {
        return lstActivo;
    }

    public void setLstActivo(List<Encuesta> lstActivo) {
        this.lstActivo = lstActivo;
    }

}
