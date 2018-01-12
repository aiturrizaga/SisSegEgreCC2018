package com.vg.dao;

import com.vg.model.Registros;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrosDao extends Dao {

    public List<Registros> consulta(String codCar, String secc) throws Exception {
        List<Registros> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT ROWNUM AS ORDEN , CODTRAZ,CODPER,NOMBRES,CODCAR,CARRERA,SECCION FROM (SELECT\n"
                    + "TRAZABILIDAD.COD_TRAZ AS CODTRAZ,\n"
                    + "PERSONATEMP.COD_EST AS CODPER,\n"
                    + "CONCAT(CONCAT(PERSONATEMP.APE_EST,', '),PERSONATEMP.NOM_EST) AS NOMBRES,\n"
                    + "CARRERAS.COD_CAR AS CODCAR,\n"
                    + "CARRERAS.NOM_CAR AS CARRERA,\n"
                    + "TRAZABILIDAD.SECCION AS SECCION\n"
                    + "FROM TRAZABILIDAD \n"
                    + "INNER JOIN PERSONATEMP ON \n"
                    + "    TRAZABILIDAD.COD_EST = PERSONATEMP.COD_EST\n"
                    + "INNER JOIN CARRERAS ON\n"
                    + "    TRAZABILIDAD.COD_CAR = CARRERAS.COD_CAR \n"
                    + "WHERE TRAZABILIDAD.EST_TRAZ = 'A'\n"
                    + "AND CARRERAS.COD_CAR = ?\n"
                    + "AND TRAZABILIDAD.SECCION = ?\n"
                    + "ORDER BY NOMBRES ASC)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, codCar);
            ps.setString(2, secc);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Registros reg;
            while (rs.next()) {
                reg = new Registros();
                reg.setNumOrdenReg(rs.getString("ORDEN"));
                reg.setNomPerReg(rs.getString("NOMBRES"));
                reg.setNomCarReg(rs.getString("CARRERA"));
                reg.setSeccionReg(rs.getString("SECCION"));
                lista.add(reg);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    public ArrayList<Registros> listaCbCarreras() throws Exception {
        try {
            ArrayList<Registros> lista = new ArrayList<>();
            ResultSet rs;
            this.Conexion();
            String sql = "SELECT * FROM CARRERAS WHERE EST_CAR = 'A' AND NOM_CAR NOT LIKE 'NN' ";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Registros car = new Registros();
                car.setCodCarReg(rs.getString("COD_CAR"));
                car.setNomCarReg(rs.getString("NOM_CAR"));
                lista.add(car);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    public ArrayList<Registros> listaCbCursos(String codCar) throws Exception {
        ArrayList<Registros> lst = new ArrayList<>();
        ResultSet rs;
        this.Conexion();
        String Sql = "SELECT * FROM CURSOS WHERE COD_CAR = ?";
        PreparedStatement ps = this.getCn().prepareStatement(Sql);
        ps.setString(1, codCar); //PARAMETRO
        rs = ps.executeQuery();
        while (rs.next()) {
            Registros car = new Registros();
            car.setCodCurReg(rs.getString("COD_CURSO"));
            car.setNomCurReg(rs.getString("NOM_CURSO"));
            lst.add(car);
        }
        return lst;
    }
    
    public void addRegistros(Registros reg) throws Exception{
        try {
            this.Conexion();
            String sql = "INSERT INTO REGISTROS(COD_TRAZ,COD_CURSO,NOTA_CURSO,ASIS_CURSO,FECHA_CURSO,NOM_CONTROL) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, reg.getCodTrazReg());
            ps.setString(2, reg.getCodCurReg());
            ps.setString(3, reg.getNotasReg());
            ps.setString(4, reg.getAsisReg());
            ps.setString(5, reg.getFechaReg());
            ps.setString(6, reg.getNomCriterio());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
