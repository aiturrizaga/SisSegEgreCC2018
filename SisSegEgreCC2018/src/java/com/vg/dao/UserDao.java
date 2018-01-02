package com.vg.dao;

import com.vg.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao {

    public User acceder(String Us, String Pass) throws Exception {
        this.Conexion();
        User Usu = new User();
        try {
            ResultSet rs;
            String sql = "SELECT * FROM PERSONAS WHERE NOM_USER like ? AND PASS_USER like ?";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, Us);
            ps.setString(2, Pass);
            rs = ps.executeQuery();
            rs.next();
            Usu.setCOD_PER(rs.getString("COD_PER"));
            Usu.setNOM_USER(rs.getString("NOM_USER"));
            Usu.setPASS_USER(rs.getString("PASS_USER"));
            Usu.setNOM_PER(rs.getString("NOM_PER"));
            Usu.setAPE_PER(rs.getString("APE_PER"));
            Usu.setCORREO_PER(rs.getString("CORREO_PER"));
            return Usu;
        } catch (SQLException e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error en metodo Acceder",String.valueOf(e)));
            return null;
        }
    }
    
    public void changePassword(User user) throws Exception{
        try {
            this.Conexion();
            String sql = "UPDATE PERSONAS SET PASS_USER = ? WHERE COD_PER = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, user.getNew_pass());
            ps.setString(2, user.getCOD_PER());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public void jalarIniciales(User user) throws SQLException{
        try {
            this.Conexion();
            String sql = "SELECT CONCAT(SUBSTR(NOM_PER,1,1),SUBSTR(APE_PER,1,1)) AS INICIALES FROM PERSONAS WHERE COD_PER = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, user.getCOD_PER());
            ResultSet rs = ps.executeQuery();
            rs.next();
            user.setIniciales(rs.getString("INICIALES"));
        } catch (SQLException e) {
            throw e;
        }
    }
    
}
