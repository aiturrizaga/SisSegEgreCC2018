package com.vg.model;

import java.util.Date;
import org.hibernate.validator.constraints.Email;

public class Alumnos {

    private String fechaActual;
    private String NOMBRE,CANTIDAD;
    private String cod_est;
    private String cod_col;
    private String cod_car;
    private String nom_est;
    private String ape_est;
    private String dni_est;
    private Date fecnac_est;
    private String direc_est;
    private String ref_est;
    private String cel_est;

    @Email(message = "Corregir el correo")
    private String correo_est;
    private String ubigeo_est;
    private String year_est;
    private String sec_est;
    private String promo_est;
    private String eco_est;
    private String nota1;
    private String nota2;
    private String nota3;
    private String nota4;
    private String sexo = "M";
    
    private int cantCar,cantProv,cantProvCañete;
    private String nomCar,nomProv,nomProvCañete;
    
    private String CODIGO, DNI, NOMBRES, COLEGIO, DISTRITO, PROMEDIO, ECONOMIA, EDAD, VG, DIRECCION, CORREO, CELULAR, CARRERA;

    private Date NACIMIENT0;
    
    private String countPersonaTemp;

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(String CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }

    public int getCantProv() {
        return cantProv;
    }

    public void setCantProv(int cantProv) {
        this.cantProv = cantProv;
    }

    public int getCantProvCañete() {
        return cantProvCañete;
    }

    public void setCantProvCañete(int cantProvCañete) {
        this.cantProvCañete = cantProvCañete;
    }

    public String getNomProv() {
        return nomProv;
    }

    public void setNomProv(String nomProv) {
        this.nomProv = nomProv;
    }

    public String getNomProvCañete() {
        return nomProvCañete;
    }

    public void setNomProvCañete(String nomProvCañete) {
        this.nomProvCañete = nomProvCañete;
    }
     
    
    public String getCountPersonaTemp() {
        return countPersonaTemp;
    }

    public void setCountPersonaTemp(String countPersonaTemp) {
        this.countPersonaTemp = countPersonaTemp;
    }

    public int getCantCar() {
        return cantCar;
    }

    public void setCantCar(int cantCar) {
        this.cantCar = cantCar;
    }

    public String getNomCar() {
        return nomCar;
    }

    public void setNomCar(String nomCar) {
        this.nomCar = nomCar;
    }


    public String getCARRERA() {
        return CARRERA;
    }

    public void setCARRERA(String CARRERA) {
        this.CARRERA = CARRERA;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

    public String getCELULAR() {
        return CELULAR;
    }

    public void setCELULAR(String CELULAR) {
        this.CELULAR = CELULAR;
    }

    public Date getNACIMIENT0() {
        return NACIMIENT0;
    }

    public void setNACIMIENT0(Date NACIMIENT0) {
        this.NACIMIENT0 = NACIMIENT0;
    }

    public String getVG() {
        return VG;
    }

    public void setVG(String VG) {
        this.VG = VG;
    }

    public String getPROMEDIO() {
        return PROMEDIO;
    }

    public void setPROMEDIO(String PROMEDIO) {
        this.PROMEDIO = PROMEDIO;
    }

    public String getECONOMIA() {
        return ECONOMIA;
    }

    public void setECONOMIA(String ECONOMIA) {
        this.ECONOMIA = ECONOMIA;
    }

    public String getEDAD() {
        return EDAD;
    }

    public void setEDAD(String EDAD) {
        this.EDAD = EDAD;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getNota4() {
        return nota4;
    }

    public void setNota4(String nota4) {
        this.nota4 = nota4;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        this.nota1 = nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }

    public String getNota3() {
        return nota3;
    }

    public void setNota3(String nota3) {
        this.nota3 = nota3;
    }

    public String getEco_est() {
        return eco_est;
    }

    public void setEco_est(String eco_est) {
        this.eco_est = eco_est;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

    public String getCOLEGIO() {
        return COLEGIO;
    }

    public void setCOLEGIO(String COLEGIO) {
        this.COLEGIO = COLEGIO;
    }

    public String getDISTRITO() {
        return DISTRITO;
    }

    public void setDISTRITO(String DISTRITO) {
        this.DISTRITO = DISTRITO;
    }

    public String getPromo_est() {
        return promo_est;
    }

    public void setPromo_est(String promo_est) {
        this.promo_est = promo_est;
    }

    public Date getFecnac_est() {
        return fecnac_est;
    }

    public void setFecnac_est(Date fecnac_est) {
        this.fecnac_est = fecnac_est;
    }

    public String getCod_est() {
        return cod_est;
    }

    public void setCod_est(String cod_est) {
        this.cod_est = cod_est;
    }

    public String getNom_est() {
        return nom_est;
    }

    public void setNom_est(String nom_est) {
        this.nom_est = nom_est;
    }

    public String getApe_est() {
        return ape_est;
    }

    public void setApe_est(String ape_est) {
        this.ape_est = ape_est;
    }

    public String getDni_est() {
        return dni_est;
    }

    public void setDni_est(String dni_est) {
        this.dni_est = dni_est;
    }

    public String getDirec_est() {
        return direc_est;
    }

    public void setDirec_est(String direc_est) {
        this.direc_est = direc_est;
    }

    public String getRef_est() {
        return ref_est;
    }

    public void setRef_est(String ref_est) {
        this.ref_est = ref_est;
    }

    public String getCel_est() {
        return cel_est;
    }

    public void setCel_est(String cel_est) {
        this.cel_est = cel_est;
    }

    public String getCorreo_est() {
        return correo_est;
    }

    public void setCorreo_est(String correo_est) {
        this.correo_est = correo_est;
    }

    public String getUbigeo_est() {
        return ubigeo_est;
    }

    public void setUbigeo_est(String ubigeo_est) {
        this.ubigeo_est = ubigeo_est;
    }

    public String getYear_est() {
        return year_est;
    }

    public void setYear_est(String year_est) {
        this.year_est = year_est;
    }

    public String getSec_est() {
        return sec_est;
    }

    public void setSec_est(String sec_est) {
        this.sec_est = sec_est;
    }

    public String getCod_col() {
        return cod_col;
    }

    public void setCod_col(String cod_col) {
        this.cod_col = cod_col;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCod_car() {
        return cod_car;
    }

    public void setCod_car(String cod_car) {
        this.cod_car = cod_car;
    }

}
