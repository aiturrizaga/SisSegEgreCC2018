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
                    + "CONCAT(CONCAT(PERSONATEMP.NOM_EST,' '),PERSONATEMP.APE_EST) AS NOMBRES,\n"
                    + "CARRERAS.COD_CAR AS CODCAR,\n"
                    + "CARRERAS.NOM_CAR AS CARRERA,\n"
                    + "TRAZABILIDAD.SECCION AS SECCION\n"
                    + "FROM TRAZABILIDAD \n"
                    + "INNER JOIN PERSONATEMP ON \n"
                    + "    TRAZABILIDAD.COD_EST = PERSONATEMP.COD_EST\n"
                    + "INNER JOIN CARRERAS ON\n"
                    + "    TRAZABILIDAD.COD_CAR = CARRERAS.COD_CAR \n"
                    + "WHERE TRAZABILIDAD.EST_TRAZ = 'A'\n"
                    + "AND CARRERAS.COD_CAR = ? \n"
                    + "AND TRAZABILIDAD.SECCION = ? \n"
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
}
