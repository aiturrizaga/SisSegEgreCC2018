package com.vg.dao;

import com.vg.model.Encuesta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncuestaDao extends Dao {

    public String leerEst(String a) throws Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "Select COD_EST from PERSONATEMP WHERE CONCAT(CONCAT(NOM_EST,' '),APE_EST) = ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("COD_EST");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    
    public List<String> queryAutoCompleteEst(String a) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> lista;
        try {
            
            String sql = "Select CONCAT(CONCAT(NOM_EST,' '),APE_EST) AS NOM_EST FROM PERSONATEMP WHERE NOM_EST LIKE INITCAP(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                lista.add(rs.getString("NOM_EST"));
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void registrarPrimeraPregunta(Encuesta al) throws Exception {
        this.Conexion();
        try {
            String sql = "INSERT INTO ENCUESTA (COD_EST,PREGUNTA,RESPUESTA1) VALUES (?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, al.getCodigo());
            ps.setString(2, "1");
            ps.setString(3, Arrays.toString(al.getPregunta1()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void registrarSegundaPregunta(Encuesta al) throws Exception {
        this.Conexion();
        try {
            String sql = "INSERT INTO ENCUESTA (COD_EST,PREGUNTA,RESPUESTA1) VALUES (?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, al.getCodigo());
            ps.setString(2, "2");
            ps.setString(3, Arrays.toString(al.getPregunta2()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void registrarTerceraPregunta(Encuesta al) throws Exception {
        this.Conexion();
        try {
            String sql = "INSERT INTO ENCUESTA (COD_EST,PREGUNTA,RESPUESTA1) VALUES (?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, al.getCodigo());
            ps.setString(2, "3");
            ps.setString(3, Arrays.toString(al.getPregunta3()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void registrarCuartaPregunta(Encuesta al) throws Exception {
        this.Conexion();
        try {
            String sql = "INSERT INTO ENCUESTA (COD_EST,PREGUNTA,RESPUESTA1) VALUES (?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, al.getCodigo());
            ps.setString(2, "4");
            ps.setString(3, Arrays.toString(al.getPregunta4()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Encuesta> lstEncuesta() throws Exception {
        List<Encuesta> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT \n"
                    + "PERSONATEMP.NOM_EST AS \"NOMBRE\",\n"
                    + "PERSONATEMP.APE_EST AS \"APELLIDO\",\n"
                    + "ENCUESTA.PREGUNTA AS \"PREGUNTA\",\n"
                    + "ENCUESTA.RESPUESTA1 AS \"RESPUESTA\"\n"
                    + " FROM ENCUESTA \n"
                    + "INNER JOIN PERSONATEMP ON ENCUESTA.COD_EST = PERSONATEMP.COD_EST\n"
                    + "ORDER BY PERSONATEMP.NOM_EST ,ENCUESTA.PREGUNTA";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            //ps.setString(1, est);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Encuesta emp;
            while (rs.next()) {
                emp = new Encuesta();
                emp.setNombre(rs.getString("NOMBRE"));
                emp.setApellido(rs.getString("APELLIDO"));
                emp.setPregunta(rs.getString("PREGUNTA"));
                emp.setRespuesta(rs.getString("RESPUESTA"));
                lista.add(emp);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

}
