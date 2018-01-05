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
                tz.setAÑO(rs.getString("AÑO"));
                tz.setCODEST(rs.getString("PERSONA"));
                tz.setCODTRAZ(rs.getString("TRAZABILIDAD"));
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

}
