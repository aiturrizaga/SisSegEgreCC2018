package com.vg.dao;

import com.vg.model.Trazabilidad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrazabilidadDao extends Dao {

    public List<Trazabilidad> mostrarTrazabilidad() throws SQLException {
        List<Trazabilidad> lista;
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT * FROM VW_TRAZABILIDAD";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Trazabilidad tz;
            while (rs.next()) {
                tz = new Trazabilidad();
                tz.setNOMBRES(rs.getString("NOMBRES"));
                tz.setDNI(rs.getString("DNI"));
                tz.setCARRERA(rs.getString("CARRERA"));
                tz.setSECCION(rs.getString("SECCION"));
                tz.setMODING(rs.getString("MODING"));
                tz.setYEAR(rs.getString("AÑO"));
                tz.setCODEST(rs.getString("PERSONA"));
                tz.setCODTRAZ(rs.getString("TRAZABILIDAD"));
                tz.setCodCarrera(rs.getString("CODCAR"));
                tz.setModoIngreso(rs.getString("INGRESO"));
                tz.setESTADO(rs.getString("ESTADO"));
                lista.add(tz);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    public void desasignarAlumno(String codTraz) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE TRAZABILIDAD SET EST_TRAZ = ? WHERE COD_TRAZ = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setString(2, codTraz);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void changeEstadoALU(String codPer) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE PERSONATEMP SET EST_EST = ? WHERE COD_EST = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, "ALU");
            ps.setString(2, codPer);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public void updateAlumTraz(Trazabilidad traz) throws Exception{
        try {
            this.Conexion();
            String sql = "INSERT INTO TRAZABILIDAD(COD_EST,COD_CAR,SECCION,MOD_ING,ANO_TRAZ,FECHA_TRAZ,EST_TRAZ) VALUES (?,?,?,?,?,SYSDATE,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, traz.getCODEST());
            ps.setString(2, traz.getCodCarrera());
            ps.setString(3, traz.getSECCION());
            ps.setString(4, traz.getModoIngreso());
            ps.setString(5, traz.getYEAR());
            ps.setString(6, "A");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public void cambiarEstadoE(String codTraz) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE TRAZABILIDAD SET EST_TRAZ = ? WHERE COD_TRAZ = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, "E");
            ps.setString(2, codTraz);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

}
