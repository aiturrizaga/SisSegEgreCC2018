package com.vg.controller;

import com.vg.dao.AlumnosDao;
import com.vg.model.Alumnos;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.PieChartModel;

@Named(value = "alumnosController")
@SessionScoped
public class AlumnosController implements Serializable {

    Alumnos alum = new Alumnos();
    private List<Alumnos> lstActivo;
    private List<Alumnos> lstActivo2;
    private List<Alumnos> lstAlumnos;
    private List<Alumnos> lstCantCarreras;
    private List<Alumnos> lstCantDist;
    private List<Alumnos> lstCantDistCañete;
    private List<Alumnos> lstTopColegios;
    private List<Alumnos> lstTrazabilidad;
    private Alumnos selected;
    private Alumnos codtraza;
    private PieChartModel pieModel;
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;

    Date ahora = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("yyyy");

    @PostConstruct
    public void ini() {
        try {
            alum.setPromo_est(formateador.format(ahora));
            listarAlumnosActivo();
            listarAlumnosAll();
            listaCantCarrera();
            listaCantDistritos();
            listaCantDistCañete();
            listarTopColegios();
            countPersonaTemp();
        } catch (Exception e) {
        }
    }
    
    
    public void listarTopColegios() throws Exception {
        AlumnosDao dao;
        try {
            dao = new AlumnosDao();
            lstTopColegios = dao.topColegios();
        } catch (SQLException e) {
            throw e;
        }

    }
    public void registrarAlumno() throws Exception {
        AlumnosDao dao;
        try {
            dao = new AlumnosDao();
            alum.setCod_col(dao.leerCol(alum.getCod_col()));
            alum.setUbigeo_est(dao.leerUbi(alum.getUbigeo_est()));
            dao.registrarAlumno(alum);
            listarAlumnosActivo();
            listaCantDistritos();
            countPersonaTemp();
            listarTopColegios();
            listaCantCarrera();
            listaCantDistCañete();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INGRESADO", alum.getApe_est() + " " + alum.getNom_est() + " agregado."));
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR","Corriga los datos."));
            throw e;
        }
    }
    public void listarAlumnosActivo() throws Exception {
        AlumnosDao dao;
        try {
            dao = new AlumnosDao();
            lstActivo = dao.lstAlumnosActivo();
        } catch (Exception e) {
            throw e;
        }
    }
    public void listarAlumnosAll() throws Exception {
        AlumnosDao dao;
        try {
            dao = new AlumnosDao();
            lstAlumnos = dao.listarPersonas();
        } catch (Exception e) {
            throw e;
        }
    }
    public void countPersonaTemp() throws SQLException {
        AlumnosDao dao;
        try {
            dao = new AlumnosDao();
            dao.countPersonaTemp(alum);
        } catch (SQLException e) {
            throw e;
        }
    }
    public void jalarData() throws Exception {
        AlumnosDao dao;
        try {
            dao = new AlumnosDao();
            selected = dao.jalarDataAlumnos(selected.getCod_est());
        } catch (SQLException e) {
            throw e;
        }
    }
    public void jalarDataAlumnos() throws Exception {
        AlumnosDao dao;
        try {
            dao = new AlumnosDao();
            selected = dao.jalarDataAlumnos(selected.getCODIGO());
        } catch (SQLException e) {
            throw e;
        }
    }
    public void editarAlumnos() throws Exception {
        AlumnosDao dao;
        try {
            dao = new AlumnosDao();
            selected.setCod_col(dao.leerCol(selected.getCod_col()));
            selected.setUbigeo_est(dao.leerUbi(selected.getUbigeo_est()));
            dao.editarAlumnos(selected);
            listarAlumnosAll();
            listarTopColegios();
            listaCantCarrera();
            listaCantDistCañete();
            listaCantDistritos();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ACTUALIZADO", "Correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR", "Corregir los datos"));
        }
    }
    public List<String> completeTextCol(String query) throws SQLException {
        AlumnosDao dao = new AlumnosDao();
        return dao.queryAutoCompleteCol(query);
    }
    public List<String> completeTextUbi(String query) throws SQLException {
        AlumnosDao dao = new AlumnosDao();
        return dao.queryAutoCompleteUbi(query);
    }
    public void listaCantCarrera() throws Exception {
        AlumnosDao dao;
        try {
            dao = new AlumnosDao();
            lstCantCarreras = dao.listarCantCarreras();
            graficar(lstCantCarreras);
        } catch (Exception e) {
            throw e;
        }
    }
    public void listaCantDistritos() throws Exception {
        AlumnosDao dao;
        try {
            dao = new AlumnosDao();
            lstCantDist = dao.listarCantProvincia();
            graficarDistritos(lstCantDist);
        } catch (SQLException e) {
            throw e;
        }
    }
    public void listaCantDistCañete() throws SQLException {
        AlumnosDao dao;
        try {
            dao = new AlumnosDao();
            lstCantDistCañete = dao.listaProvCañete();
            graficarDistritoCañete(lstCantDistCañete);
        } catch (SQLException e) {
            throw e;
        }
    }
    public void graficar(List<Alumnos> lista) {
        pieModel = new PieChartModel();
        for (Alumnos alu : lstCantCarreras) {
            pieModel.set(alu.getNomCar(), alu.getCantCar());
        }
        pieModel.setTitle("Porcentaje de Alumnos por Carrera");
        pieModel.setLegendPosition("ne");
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(230);
    }
    public void graficarDistritos(List<Alumnos> lista) {
        pieModel1 = new PieChartModel();
        for (Alumnos alu : lstCantDist) {
            pieModel1.set(alu.getNomProv(), alu.getCantProv());
        }
        pieModel1.setTitle("Cantidad de Alumnos por Distrito");
        pieModel1.setLegendPosition("ne");
        pieModel1.setShowDataLabels(true);
        pieModel1.setDiameter(230);
    }
    public void graficarDistritoCañete(List<Alumnos> lista) {
        pieModel2 = new PieChartModel();
        for (Alumnos alu : lstCantDistCañete) {
            pieModel2.set(alu.getNomProvCañete(), alu.getCantProvCañete());
        }
        pieModel2.setTitle("Cantidad Alumnos por Distrito - Cañete");
        pieModel2.setLegendPosition("ne");
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(230);
    }
    public void limpiar() {
        alum.setCod_col(null);
        alum.setYear_est(null);
        alum.setSec_est(null);
        alum.setDni_est(null);
        alum.setNom_est(null);
        alum.setApe_est(null);
        alum.setFecnac_est(null);
        alum.setDirec_est(null);
        alum.setRef_est(null);
        alum.setCel_est(null);
        alum.setCorreo_est(null);
        alum.setUbigeo_est(null);
        alum.setNota1(null);
        alum.setNota2(null);
        alum.setNota3(null);
        alum.setNota4(null);
    }
    
    public Alumnos getCodtraza() {
        return codtraza;
    }

    public void setCodtraza(Alumnos codtraza) {
        this.codtraza = codtraza;
    }
    
    public List<Alumnos> getLstTrazabilidad() {
        return lstTrazabilidad;
    }

    public void setLstTrazabilidad(List<Alumnos> lstTrazabilidad) {
        this.lstTrazabilidad = lstTrazabilidad;
    }
    
    
    public List<Alumnos> getLstTopColegios() {
        return lstTopColegios;
    }

    public void setLstTopColegios(List<Alumnos> lstTopColegios) {
        this.lstTopColegios = lstTopColegios;
    }

    public List<Alumnos> getLstCantCarreras() {
        return lstCantCarreras;
    }

    public List<Alumnos> getLstCantDistCañete() {
        return lstCantDistCañete;
    }

    public void setLstCantDistCañete(List<Alumnos> lstCantDistCañete) {
        this.lstCantDistCañete = lstCantDistCañete;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public void setPieModel2(PieChartModel pieModel2) {
        this.pieModel2 = pieModel2;
    }

    public List<Alumnos> getLstCantDist() {
        return lstCantDist;
    }

    public void setLstCantDist(List<Alumnos> lstCantDist) {
        this.lstCantDist = lstCantDist;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    public void setLstCantCarreras(List<Alumnos> lstCantCarreras) {
        this.lstCantCarreras = lstCantCarreras;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public List<Alumnos> getLstActivo2() {
        return lstActivo2;
    }

    public void setLstActivo2(List<Alumnos> lstActivo2) {
        this.lstActivo2 = lstActivo2;
    }

    public Alumnos getAlum() {
        return alum;
    }

    public void setAlum(Alumnos alum) {
        this.alum = alum;
    }

    public List<Alumnos> getLstActivo() {
        return lstActivo;
    }

    public void setLstActivo(List<Alumnos> lstActivo) {
        this.lstActivo = lstActivo;
    }

    public Alumnos getSelected() {
        return selected;
    }

    public void setSelected(Alumnos selected) {
        this.selected = selected;
    }

    public Date getAhora() {
        return ahora;
    }

    public void setAhora(Date ahora) {
        this.ahora = ahora;
    }

    public SimpleDateFormat getFormateador() {
        return formateador;
    }

    public void setFormateador(SimpleDateFormat formateador) {
        this.formateador = formateador;
    }

    public List<Alumnos> getLstAlumnos() {
        return lstAlumnos;
    }

    public void setLstAlumnos(List<Alumnos> lstAlumnos) {
        this.lstAlumnos = lstAlumnos;
    }

}
