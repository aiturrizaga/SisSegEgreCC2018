package com.vg.controller;

import com.vg.dao.RegistrosDao;
import com.vg.model.Registros;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

@Named(value = "registrosController")
@SessionScoped
public class RegistrosController implements Serializable {

    Registros reg = new Registros();
    private List<Registros> lstRegistros;
    private String codCar;
    private String secc;

    Date ahora = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

    @PostConstruct
    public void init() {
        reg.setFechaReg(formateador.format(ahora));
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

}
