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
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "registrosController")
@SessionScoped
public class RegistrosController implements Serializable {

    Registros reg = new Registros();
    private List<Registros> lstRegistros;
    private List<Registros> lstRegistrosView;
    private List<Registros> lstCbCarreras;
    private List<Registros> lstCbCursos;
    private List<Registros> lstNuevo;
    private String codCar, codCar2, nombrec;
    private Date fecha2;
    private Date fecha3;
    private String secc, secc2, curso, curso2;
    private boolean criterio = false;
    private boolean buttonCriterio = true;
    private String Fecha1;
    private Registros selected;

    Date ahora = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

    @PostConstruct
    public void init() {
        try {
            setLstCbCarreras(null);
            setLstCbCursos(null);
            listaCbCarrera();
            listaNueva();
        } catch (Exception ex) {
            Logger.getLogger(RegistrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void listaNueva() throws Exception {
        RegistrosDao dao;
        try {
            dao = new RegistrosDao();
            lstNuevo = dao.listarCursos();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void addCriterio() {
        criterio = true;
        ocultarAddCriterio();
    }
    
    public void ocultarAddCriterio(){
        buttonCriterio = false;
    }
    
    public void borrarCriterio(){
        setNombrec(null);
        reg.setNotasReg("");
        buttonCriterio = true;
        criterio = false;
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
    
    

    public void consultarReg() throws Exception {
        RegistrosDao dao;
        try {
            dao = new RegistrosDao();
            lstRegistros = dao.consulta(codCar, secc);
        } catch (Exception e) {
            throw e;
        }
    }

    public void viewReg() throws Exception {
        RegistrosDao dao;
        try {
            dao = new RegistrosDao();
            String date = formateador.format(getFecha2());
            lstRegistrosView = dao.viewReg(codCar2, secc2, curso2, date);
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
                model.setFechaReg(formateador.format(getFecha3()));
                dao.addRegistros(model);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "COMPLETADO", "Correctamente"));
            lstRegistros = null;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void updateRegistro() throws Exception{
        RegistrosDao dao;
        try {
            dao = new RegistrosDao();
            dao.updateRegistro(selected);
            viewReg();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ACTUALIZADO", "Correctamente"));
        } catch (Exception e) {
            throw e;
        }
    }

    public Registros getSelected() {
        return selected;
    }

    public void setSelected(Registros selected) {
        this.selected = selected;
    }

    public Date getFecha2() {
        return fecha2;
    }
    
    public Date getFecha3() {
        return fecha3;
    }
    
    public List<Registros> getLstNuevo() {
        return lstNuevo;
    }

    //Getter and Setter
    public void setLstNuevo(List<Registros> lstNuevo) {    
        this.lstNuevo = lstNuevo;
    }

    public boolean isButtonCriterio() {
        return buttonCriterio;
    }

    public void setButtonCriterio(boolean buttonCriterio) {
        this.buttonCriterio = buttonCriterio;
    }
    
    public void setFecha3(Date fecha3) {    
        this.fecha3 = fecha3;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public List<Registros> getLstRegistrosView() {
        return lstRegistrosView;
    }

    public void setLstRegistrosView(List<Registros> lstRegistrosView) {
        this.lstRegistrosView = lstRegistrosView;
    }

    public String getCurso2() {
        return curso2;
    }

    public void setCurso2(String curso2) {
        this.curso2 = curso2;
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
