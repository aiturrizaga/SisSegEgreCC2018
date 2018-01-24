package com.vg.dao;

import com.vg.model.Trazabilidad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrazabilidadDao extends Dao {

    public String leerHist(String a) throws Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT PERSONATEMP.COD_EST AS COD_EST FROM trazabilidad \n"
                    + "INNER JOIN PERSONATEMP ON trazabilidad.COD_EST = PERSONATEMP.COD_EST WHERE CONCAT(CONCAT(PERSONATEMP.NOM_EST,' '),PERSONATEMP.APE_EST) = ?";
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

    public List<String> queryAutoCompleteHist(String a) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> lista;
        try {

            String sql = "SELECT DISTINCT CONCAT(CONCAT(PERSONATEMP.NOM_EST,' '),PERSONATEMP.APE_EST) AS NOMBRES FROM trazabilidad \n"
                    + "INNER JOIN PERSONATEMP ON trazabilidad.COD_EST = PERSONATEMP.COD_EST WHERE NOM_EST LIKE UPPER( ? )";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                lista.add(rs.getString("NOMBRES"));
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Trazabilidad> consulta(String traz) throws Exception {
        List<Trazabilidad> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT \n"
                    + "CONCAT(CONCAT(PERSONATEMP.NOM_EST,' '),PERSONATEMP.APE_EST) AS NOM_REG,\n"
                    + "CARRERAS.NOM_CAR  AS CAR_REG,\n"
                    + "TO_CHAR(REGISTROS.FECHA_CURSO,'dd/MM/YYYY') AS FECH_REG,\n"
                    + "CURSOS.NOM_CURSO AS CUR_REG,\n"
                    + "REGISTROS.NOTA_CURSO AS NOT_REG,\n"
                    + "REGISTROS.NOM_CONTROL AS CONT_REG,\n"
                    + "CASE  \n"
                    + "                        WHEN REGISTROS.ASIS_CURSO = 'A' THEN 'ASISTIO'\n"
                    + "                        WHEN REGISTROS.ASIS_CURSO = 'I' THEN 'INASISTIO'\n"
                    + "                    END AS ASIS_REG\n"
                    + "FROM REGISTROS\n"
                    + "INNER JOIN TRAZABILIDAD ON \n"
                    + "     TRAZABILIDAD.COD_TRAZ = REGISTROS.COD_TRAZ\n"
                    + "INNER JOIN PERSONATEMP ON\n"
                    + "    TRAZABILIDAD.COD_EST = PERSONATEMP.COD_EST\n"
                    + "INNER JOIN CARRERAS ON\n"
                    + "    TRAZABILIDAD.COD_CAR = CARRERAS.COD_CAR\n"
                    + "INNER JOIN CURSOS ON \n"
                    + "    CURSOS.COD_CAR = CARRERAS.COD_CAR\n"
                    + "WHERE TRAZABILIDAD.COD_EST = ? \n"
                    + "ORDER BY REGISTROS.FECHA_CURSO";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, traz);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Trazabilidad reg;
            while (rs.next()) {
                reg = new Trazabilidad();
                reg.setNOMREG(rs.getString("NOM_REG"));
                reg.setCARREG(rs.getString("CAR_REG"));
                reg.setFECHREG(rs.getString("FECH_REG"));
                reg.setCURREG(rs.getString("CUR_REG"));
                reg.setNOTREG(rs.getString("NOT_REG"));
                reg.setCONTREG(rs.getString("CONT_REG"));
                reg.setASISREG(rs.getString("ASIS_REG"));
                lista.add(reg);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

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
                tz.setFECHA(rs.getString("FECHA"));
                lista.add(tz);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    public void desasignarAlumno(String codEst) throws Exception {
        try {
            this.Conexion();
            String sql = "UPDATE TRAZABILIDAD SET EST_TRAZ = ? WHERE COD_EST = ? ";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setString(2, codEst);
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

    public void updateAlumTraz(Trazabilidad traz) throws Exception {
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
