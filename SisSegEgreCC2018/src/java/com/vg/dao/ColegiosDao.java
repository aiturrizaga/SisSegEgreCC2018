package com.vg.dao;

import com.vg.model.Colegios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColegiosDao extends Dao {

    public void agregarCol(Colegios col) throws Exception {
        this.Conexion();
        try {
            String sql = "INSERT INTO COLEGIOS VALUES (?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, col.getCod_modular());
            ps.setString(2, col.getNom_colegio());
            ps.setString(3, col.getEst_col());
            ps.setString(4, col.getCodUbigeo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<Colegios> lstColegiosActivo() throws Exception {
        List<Colegios> lista;
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM  COLEGIOS WHERE EST_COL = 'A'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            Colegios emp;
            while (rs.next()) {
                emp = new Colegios();
                emp.setCodigomodular(rs.getString("COD_COL"));
                emp.setColegio(rs.getString("NOM_COL"));
                emp.setUbigeo(rs.getString("UBIGEO"));
                lista.add(emp);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    public Colegios jalarData(String cod) throws SQLException {
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT * FROM COLEGIOS WHERE COD_COL like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            Colegios emp = new Colegios();
            rs.next();
            emp.setCod_modular(rs.getString("COD_COL"));
            emp.setNom_colegio(rs.getString("NOM_COL"));
            return emp;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void editarColegio(Colegios col) throws Exception {
        try {
            this.Conexion();
            String sql = "BEGIN UPDATECOLEGIO(?, ?, ?, ?); END;";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, col.getCod_modular());
            ps.setString(2, col.getNom_colegio());
            ps.setString(3, col.getEst_col());
            ps.setString(4, col.getCodUbigeo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void countColegio(Colegios cole) {
        ResultSet rs;
        try {
            this.Conexion();
            String sql = "SELECT COUNT(DISTINCT COD_COL) AS CANT_COL FROM PERSONATEMP";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            cole.setCountColegio(rs.getString("CANT_COL"));
        } catch (SQLException e) {
        }
    }

    public ArrayList<Colegios> listaUbigeo() throws Exception {
        try {
            ArrayList<Colegios> lista = new ArrayList<>();
            ResultSet rs;
            this.Conexion();
            String sql = "SELECT UBIGEO,CONCAT(CONCAT(CONCAT(CONCAT(DPTO,','),PROV),','),DIST) AS NOMUBI FROM UBIGEO";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Colegios col = new Colegios();
                col.setCodUbigeo(rs.getString("UBIGEO"));
                col.setNomUbigeo(rs.getString("NOMUBI"));
                lista.add(col);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        }
    }
}
