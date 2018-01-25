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
                reg.setCodTrazReg(rs.getString("CODTRAZ"));
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

    public List<Registros> viewReg(String codCar, String secc, String codCur, String fecha) throws Exception {
        List<Registros> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT \n"
                    + "ROWNUM AS ORDEN,\n"
                    + "NOMBRES,\n"
                    + "CODREG,\n"
                    + "CARRERA,\n"
                    + "CURSO,\n"
                    + "SECCION,\n"
                    + "ASISTENCIA,\n"
                    + "NOTA,\n"
                    + "FECHA,\n"
                    + "NOMCONTROL\n"
                    + "FROM (SELECT \n"
                    + "CONCAT(CONCAT(PERSONATEMP.APE_EST,', '),PERSONATEMP.NOM_EST) AS NOMBRES,\n"
                    + "CARRERAS.NOM_CAR AS CARRERA,\n"
                    + "CURSOS.NOM_CURSO  AS CURSO,\n"
                    + "TRAZABILIDAD.SECCION AS SECCION,\n"
                    + "CASE\n"
                    + "    WHEN REGISTROS.ASIS_CURSO = 'A' THEN 'ASISTIO'\n"
                    + "    WHEN REGISTROS.ASIS_CURSO = 'F' THEN 'FALTO'\n"
                    + "END AS ASISTENCIA,\n"
                    + "REGISTROS.NOTA_CURSO AS NOTA,\n"
                    + "REGISTROS.COD_REG AS CODREG,\n"
                    + "REGISTROS.FECHA_CURSO AS FECHA,\n"
                    + "REGISTROS.NOM_CONTROL AS NOMCONTROL\n"
                    + "FROM REGISTROS\n"
                    + "INNER JOIN TRAZABILIDAD ON\n"
                    + "    TRAZABILIDAD.COD_TRAZ = REGISTROS.COD_TRAZ \n"
                    + "INNER JOIN CURSOS ON \n"
                    + "    REGISTROS.COD_CURSO = CURSOS.COD_CURSO\n"
                    + "INNER JOIN PERSONATEMP ON\n"
                    + "    TRAZABILIDAD.COD_EST = PERSONATEMP.COD_EST\n"
                    + "INNER JOIN CARRERAS ON\n"
                    + "    TRAZABILIDAD.COD_CAR = CARRERAS.COD_CAR\n"
                    + "WHERE \n"
                    + "CARRERAS.COD_CAR = ? AND\n"
                    + "CURSOS.COD_CURSO = ? AND\n"
                    + "TRAZABILIDAD.SECCION = ? AND \n"
                    + "REGISTROS.FECHA_CURSO = ? ORDER BY NOMBRES)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, codCar);
            ps.setString(2, codCur);
            ps.setString(3, secc);
            ps.setString(4, fecha);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Registros reg;
            while (rs.next()) {
                reg = new Registros();
                reg.setNumOrdenView(rs.getString("ORDEN"));
                reg.setNomPerView(rs.getString("NOMBRES"));
                reg.setCodRegView(rs.getString("CODREG"));
                if ("A".equals(rs.getString("ASISTENCIA"))) {
                    reg.setAsisReg(true);
                }else{
                    reg.setAsisReg(false);
                }
                reg.setNotaView(rs.getString("NOTA"));
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

    public void addRegistros(Registros reg) throws Exception {
        try {
            this.Conexion();
            String sql = "INSERT INTO REGISTROS(COD_TRAZ,COD_CURSO,NOTA_CURSO,ASIS_CURSO,FECHA_CURSO,NOM_CONTROL) VALUES(?,?,?,?,to_date(?,'dd/MM/yyyy'),?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, reg.getCodTrazReg());
            ps.setString(2, reg.getCodCurReg());
            ps.setString(3, reg.getNotasReg());
            ps.setString(4, reg.isAsisReg() ? "A" : "F");
            ps.setString(5, reg.getFechaReg());
            ps.setString(6, reg.getNomCriterio());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public void updateRegistro(Registros reg) throws Exception{
        try {
            this.Conexion();
            String sql = "UPDATE REGISTROS SET ASIS_CURSO = ?,NOTA_CURSO = ? WHERE COD_REG = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, reg.getAsisView());
            ps.setString(2, reg.getNotaView());
            ps.setString(3, reg.getCodRegView());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Registros> listarCursos() throws Exception {
        List<Registros> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT \n"
                    + "                                             CONCAT(CONCAT(PERSONATEMP.APE_EST,', '),PERSONATEMP.NOM_EST) AS NOMBRES,    \n"
                    + "                                             CARRERAS.NOM_CAR AS CARRERA,    \n"
                    + "                                             CURSOS.NOM_CURSO  AS CURSO,    \n"
                    + "                                             TRAZABILIDAD.SECCION AS SECCION,    \n"
                    + "                                               CASE    \n"
                    + "                                                   WHEN REGISTROS.ASIS_CURSO = 'A' THEN 'ASISTIO'    \n"
                    + "                                                   WHEN REGISTROS.ASIS_CURSO = 'F' THEN 'FALTO'    \n"
                    + "                                               END AS ASISTENCIA,    \n"
                    + "                                             REGISTROS.NOTA_CURSO AS NOTA,    \n"
                    + "                                             REGISTROS.FECHA_CURSO AS FECHA,    \n"
                    + "                                             REGISTROS.NOM_CONTROL AS NOMCONTROL    \n"
                    + "                                       FROM REGISTROS    \n"
                    + "                                            INNER JOIN TRAZABILIDAD ON    \n"
                    + "                                               TRAZABILIDAD.COD_TRAZ = REGISTROS.COD_TRAZ     \n"
                    + "                                            INNER JOIN CURSOS ON     \n"
                    + "                                               REGISTROS.COD_CURSO = CURSOS.COD_CURSO    \n"
                    + "                                            INNER JOIN PERSONATEMP ON    \n"
                    + "                                               TRAZABILIDAD.COD_EST = PERSONATEMP.COD_EST    \n"
                    + "                                            INNER JOIN CARRERAS ON    \n"
                    + "                                               TRAZABILIDAD.COD_CAR = CARRERAS.COD_CAR    \n"
                    + "                                       WHERE     \n"
                    + "                                           CARRERAS.COD_CAR = '1' AND    \n"
                    + "                                           CURSOS.COD_CURSO = '1' AND  \n"
                    + "                                           REGISTROS.FECHA_CURSO = '15/01/18' AND\n"
                    + "                                           TRAZABILIDAD.SECCION = 'A'  ORDER BY NOMBRES";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Registros alum;
            while (rs.next()) {
                alum = new Registros();
                alum.setNOMBRES(rs.getString("NOMBRES"));
                alum.setFECHA(rs.getString("FECHA"));
                alum.setASISTENCIA(rs.getString("ASISTENCIA"));
                alum.setNOTAS(rs.getString("NOTA"));
                lista.add(alum);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }
}
