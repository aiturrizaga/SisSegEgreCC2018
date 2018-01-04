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
                lista.add(tz);
            }
        } catch (SQLException e) {
            throw  e;
        }
        return lista;
    }

}
