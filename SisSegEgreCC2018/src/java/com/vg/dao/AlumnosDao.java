package com.vg.dao;

import com.vg.model.Alumnos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlumnosDao extends Dao {

    Format formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date ahora = new Date();
    

    public void registrarAlumno(Alumnos al) throws Exception {
        this.Conexion();
        try {
            String sql = "Insert into PERSONATEMP (NOM_EST,APE_EST,DNI_EST,FEC_NAC_EST,DIREC_EST,REF_EST,CEL_EST,CORREO_EST,YEAR_DETCOL,SEC_DETCOL,COD_COL,UBIGEO_EST,COD_CAR,PROMO_EST,SEXO_EST,ECON_EST,NOTE1_DETCOL,NOTE2_DETCOL,NOTE3_DETCOL,NOTE4,EST_EST) values (INITCAP(?),UPPER(?),?,to_date(?,'dd/MM/yyyy'),INITCAP(?),?,?,?,?,UPPER(?),?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, al.getNom_est());
            ps.setString(2, al.getApe_est());
            ps.setString(3, al.getDni_est());
            ps.setString(4, formatter.format(al.getFecnac_est()));
            ps.setString(5, al.getDirec_est());
            ps.setString(6, al.getRef_est());
            ps.setString(7, al.getCel_est());
            ps.setString(8, al.getCorreo_est());
            ps.setString(9, al.getYear_est());
            ps.setString(10, al.getSec_est());
            ps.setString(11, al.getCod_col());
            ps.setString(12, al.getUbigeo_est());
            ps.setString(13, al.getCod_car());
            ps.setString(14, al.getPromo_est());
            ps.setString(15, "M");
            ps.setString(16, al.getEco_est());
            ps.setString(17, al.getNota1());
            ps.setString(18, al.getNota2());
            ps.setString(19, al.getNota3());
            ps.setString(20, al.getNota4());
            ps.setString(21, "ALU");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public String leerCol(String a) throws Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "Select COD_COL from COLEGIOS WHERE NOM_COL = ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("COD_COL");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<String> queryAutoCompleteCol(String a) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> lista;
        try {
            String sql = "Select NOM_COL FROM COLEGIOS WHERE NOM_COL LIKE UPPER(?)";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                lista.add(rs.getString("NOM_COL"));
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public String leerUbi(String a) throws Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT UBIGEO FROM UBIGEO WHERE CONCAT(CONCAT(CONCAT(CONCAT(DPTO,','),PROV),','),DIST) = ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("UBIGEO");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<String> queryAutoCompleteUbi(String a) throws SQLException {
        this.Conexion();
        ResultSet rs;
        List<String> lista;
        String sql;
        try {
            if (a == null || "".equals(a)) {
                sql = "SELECT UBIGEO,CONCAT(CONCAT(CONCAT(CONCAT(DPTO,','),PROV),','),DIST) AS NOMBRE FROM UBIGEO WHERE PROV LIKE ?";
                a = "CAÑETE";
            } else {
                sql = "SELECT UBIGEO,CONCAT(CONCAT(CONCAT(CONCAT(DPTO,','),PROV),','),DIST) AS NOMBRE FROM UBIGEO WHERE Dist like UPPER(?)";
            }
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, "%" + a + "%");
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                lista.add(rs.getString("NOMBRE"));
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Alumnos> lstAlumnosActivo() throws Exception {
        List<Alumnos> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_LISTA_ALUMNOS";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Alumnos emp;
            while (rs.next()) {
                emp = new Alumnos();
                emp.setCODIGO(rs.getString("CODIGO"));
                emp.setDNI(rs.getString("DNI"));
                emp.setNOMBRES(rs.getString("NOMBRES"));
                emp.setCOLEGIO(rs.getString("COLEGIO"));
                emp.setDISTRITO(rs.getString("DISTRITO"));
                emp.setNACIMIENT0(rs.getDate("NACIMIENTO"));
                emp.setPROMEDIO(rs.getString("PROMEDIO"));
                emp.setECONOMIA(rs.getString("ECONOMIA"));
                emp.setEDAD(rs.getString("EDAD"));
                emp.setVG(rs.getString("VG"));
                emp.setDIRECCION(rs.getString("DIRECCION"));
                emp.setCORREO(rs.getString("CORREO"));
                emp.setCELULAR(rs.getString("CELULAR"));
                emp.setCARRERA(rs.getString("CARRERA"));
                lista.add(emp);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    public List<Alumnos> listarPersonas() throws Exception {
        List<Alumnos> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM PERSONATEMP";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Alumnos alum;
            while (rs.next()) {
                alum = new Alumnos();
                alum.setCod_est(rs.getString("COD_EST"));
                alum.setNom_est(rs.getString("NOM_EST"));
                alum.setApe_est(rs.getString("APE_EST"));
                alum.setDni_est(rs.getString("DNI_EST"));
                alum.setFecnac_est(rs.getDate("FEC_NAC_EST"));
                alum.setDirec_est(rs.getString("DIREC_EST"));
                alum.setRef_est(rs.getString("REF_EST"));
                alum.setCel_est(rs.getString("CEL_EST"));
                alum.setCorreo_est(rs.getString("CORREO_EST"));
                alum.setYear_est(rs.getString("YEAR_DETCOL"));
                alum.setSec_est(rs.getString("SEC_DETCOL"));
                alum.setNota1(rs.getString("NOTE1_DETCOL"));
                alum.setNota2(rs.getString("NOTE2_DETCOL"));
                alum.setNota3(rs.getString("NOTE3_DETCOL"));
                alum.setCod_col(rs.getString("COD_COL"));
                alum.setUbigeo_est(rs.getString("UBIGEO_EST"));
                alum.setCod_car(rs.getString("COD_CAR"));
                alum.setPromo_est(rs.getString("PROMO_EST"));
                alum.setSexo(rs.getString("SEXO_EST"));
                alum.setEco_est(rs.getString("ECON_EST"));
                alum.setNota4(rs.getString("NOTE4"));
                lista.add(alum);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    public Alumnos jalarData(String cod) throws SQLException {
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM PERSONATEMP WHERE COD_EST like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            Alumnos alums = new Alumnos();
            rs.next();
            alums.setCod_est(rs.getString("COD_EST"));
            alums.setNom_est(rs.getString("NOM_EST"));
            alums.setApe_est(rs.getString("APE_EST"));
            alums.setDni_est(rs.getString("DNI_EST"));
            alums.setFecnac_est(rs.getDate("FEC_NAC_EST"));
            alums.setDirec_est(rs.getString("DIREC_EST"));
            alums.setRef_est(rs.getString("REF_EST"));
            alums.setCel_est(rs.getString("CEL_EST"));
            alums.setCorreo_est(rs.getString("CORREO_EST"));
            alums.setYear_est(rs.getString("YEAR_DETCOL"));
            alums.setSec_est(rs.getString("SEC_DETCOL"));
            alums.setNota1(rs.getString("NOTE1_DETCOL"));
            alums.setNota2(rs.getString("NOTE2_DETCOL"));
            alums.setNota3(rs.getString("NOTE3_DETCOL"));
            alums.setCod_col(rs.getString("COD_COL"));
            alums.setUbigeo_est(rs.getString("UBIGEO_EST"));
            alums.setCod_car(rs.getString("COD_CAR"));
            alums.setPromo_est(rs.getString("PROMO_EST"));
            alums.setSexo(rs.getString("SEXO_EST"));
            alums.setEco_est(rs.getString("ECON_EST"));
            alums.setNota4(rs.getString("NOTE4"));
            return alums;
        } catch (SQLException e) {
            throw e;
        }
    }

    public Alumnos jalarDataAlumnos(String cod) throws SQLException {
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_PERSONASTEMP WHERE COD_EST like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            Alumnos alums = new Alumnos();
            rs.next();
            alums.setCod_est(rs.getString("COD_EST"));
            alums.setNom_est(rs.getString("NOM_EST"));
            alums.setApe_est(rs.getString("APE_EST"));
            alums.setDni_est(rs.getString("DNI_EST"));
            alums.setFecnac_est(rs.getDate("FEC_NAC_EST"));
            alums.setDirec_est(rs.getString("DIREC_EST"));
            alums.setRef_est(rs.getString("REF_EST"));
            alums.setCel_est(rs.getString("CEL_EST"));
            alums.setCorreo_est(rs.getString("CORREO_EST"));
            alums.setYear_est(rs.getString("YEAR_DETCOL"));
            alums.setSec_est(rs.getString("SEC_DETCOL"));
            alums.setNota1(rs.getString("NOTE1_DETCOL"));
            alums.setNota2(rs.getString("NOTE2_DETCOL"));
            alums.setNota3(rs.getString("NOTE3_DETCOL"));
            alums.setCod_col(rs.getString("NOM_COL"));
            alums.setUbigeo_est(rs.getString("UBIGEO"));
            alums.setCod_car(rs.getString("COD_CAR"));
            alums.setPromo_est(rs.getString("PROMO_EST"));
            alums.setSexo(rs.getString("SEXO_EST"));
            alums.setEco_est(rs.getString("ECON_EST"));
            alums.setNota4(rs.getString("NOTE4"));
            return alums;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public Alumnos jalarDataFromDni(String dni) throws SQLException{
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_PERSONASTEMP WHERE DNI_EST like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            Alumnos alums = new Alumnos();
            rs.next();
            alums.setCod_est(rs.getString("COD_EST"));
            alums.setNom_est(rs.getString("NOM_EST"));
            alums.setApe_est(rs.getString("APE_EST"));
            alums.setDni_est(rs.getString("DNI_EST"));
            alums.setFecnac_est(rs.getDate("FEC_NAC_EST"));
            alums.setDirec_est(rs.getString("DIREC_EST"));
            alums.setRef_est(rs.getString("REF_EST"));
            alums.setCel_est(rs.getString("CEL_EST"));
            alums.setCorreo_est(rs.getString("CORREO_EST"));
            alums.setYear_est(rs.getString("YEAR_DETCOL"));
            alums.setSec_est(rs.getString("SEC_DETCOL"));
            alums.setNota1(rs.getString("NOTE1_DETCOL"));
            alums.setNota2(rs.getString("NOTE2_DETCOL"));
            alums.setNota3(rs.getString("NOTE3_DETCOL"));
            alums.setCod_col(rs.getString("NOM_COL"));
            alums.setUbigeo_est(rs.getString("UBIGEO"));
            alums.setCod_car(rs.getString("COD_CAR"));
            alums.setPromo_est(rs.getString("PROMO_EST"));
            alums.setSexo(rs.getString("SEXO_EST"));
            alums.setEco_est(rs.getString("ECON_EST"));
            alums.setNota4(rs.getString("NOTE4"));
            return alums;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void editarAlumnos(Alumnos alum) throws Exception {
        try {
            this.Conexion();
            String sql = "BEGIN UPDATEPERSONATEMP(?, ?, ?, ?, to_date(?,'dd/MM/yyyy'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); END;";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, alum.getCod_est());
            ps.setString(2, alum.getNom_est());
            ps.setString(3, alum.getApe_est());
            ps.setString(4, alum.getDni_est());
            ps.setString(5, formatter.format(alum.getFecnac_est()));
            ps.setString(6, alum.getDirec_est());
            ps.setString(7, alum.getRef_est());
            ps.setString(8, alum.getCel_est());
            ps.setString(9, alum.getCorreo_est());
            ps.setString(10, alum.getYear_est());
            ps.setString(11, alum.getSec_est());
            ps.setString(12, alum.getNota1());
            ps.setString(13, alum.getNota2());
            ps.setString(14, alum.getNota3());
            ps.setString(15, alum.getCod_col());
            ps.setString(16, alum.getUbigeo_est());
            ps.setString(17, alum.getCod_car());
            ps.setString(18, alum.getPromo_est());
            ps.setString(19, alum.getSexo());
            ps.setString(20, alum.getEco_est());
            ps.setString(21, alum.getNota4());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public String leerAlumons(String a) throws Exception {
        this.Conexion();
        ResultSet rs;
        try {
            String sql = "SELECT * FROM VW_PERSONASTEMP";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, a);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("UBIGEO");
            }
            return null;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Alumnos> listarCantCarreras() throws Exception {
        List<Alumnos> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_CANTIDADCARRERAS";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Alumnos emp;
            while (rs.next()) {
                emp = new Alumnos();
                emp.setNomCar(rs.getString("NOMBRE"));
                emp.setCantCar(rs.getInt("CANTIDAD"));
                lista.add(emp);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    public List<Alumnos> listarCantProvincia() throws SQLException {
        List<Alumnos> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_PROVINCIA";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Alumnos emp;
            while (rs.next()) {
                emp = new Alumnos();
                emp.setNomProv(rs.getString("PROVINCIA"));
                emp.setCantProv(rs.getInt("CANTIDAD"));
                lista.add(emp);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;

    }

    public List<Alumnos> listaProvCañete() throws SQLException {
        List<Alumnos> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_DISTRITOCAÑETE";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Alumnos emp;
            while (rs.next()) {
                emp = new Alumnos();
                emp.setNomProvCañete(rs.getString("CAÑETE"));
                emp.setCantProvCañete(rs.getInt("CANTIDAD"));
                lista.add(emp);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    public void countPersonaTemp(Alumnos alum) throws SQLException {
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT COUNT(COD_EST) AS CANT_EST FROM PERSONATEMP";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            alum.setCountPersonaTemp(rs.getString("CANT_EST"));
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Alumnos> topColegios() throws SQLException {
        List<Alumnos> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM VW_TOPCOLEGIO";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Alumnos emp;
            while (rs.next()) {
                emp = new Alumnos();
                emp.setNOMBRE(rs.getString("NOMBRE"));
                emp.setCANTIDAD(rs.getString("CANTIDAD"));
                lista.add(emp);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    public void asignarAlumnos(Alumnos alum) throws Exception{
        try {
            this.Conexion();
            String sql = "INSERT INTO TRAZABILIDAD(COD_EST,COD_CAR,SECCION,MOD_ING,ANO_TRAZ,FECHA_TRAZ,EST_TRAZ) VALUES (?,?,?,?,?,SYSDATE,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, alum.getCodPerTraz());
            ps.setString(2, alum.getCodCarTraz());
            ps.setString(3, alum.getSecTraz());
            ps.setString(4, alum.getModingTraz());
            ps.setString(5, alum.getYearTraz());
            ps.setString(6, alum.getEstTraz());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public void cambioEstadoAsignado(Alumnos alum) throws Exception{
        try {
            this.Conexion();
            String sql = "UPDATE PERSONATEMP SET EST_EST = 'EST' WHERE COD_EST = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, alum.getCodPerTraz());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
}
