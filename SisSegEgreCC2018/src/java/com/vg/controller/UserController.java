package com.vg.controller;

import com.vg.dao.UserDao;
import com.vg.model.Alumnos;
import com.vg.model.User;
import com.vg.services.SessionUtils;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {

    User Usuario = new User();
    private String User;
    private String Pass;
    Alumnos alum = new Alumnos();
    private boolean sessionPublica = false;

    public void inicioSesion() throws Exception {
        FacesContext contex = FacesContext.getCurrentInstance();
        UserDao dao;
        try {
            dao = new UserDao();
            Usuario = dao.acceder(User, Pass);        //Busca el usuario en la base de datos
            if (Usuario == null || Usuario.getNOM_USER().length() < 1) {     //si no existe...
                contex.addMessage(null, new FacesMessage("Error", "Usuario/Contraseña incorrecto"));
            } else {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("username", Usuario);
                jalarIniciales();
                limpiar();
                contex.getExternalContext().redirect("/Ciclocero/faces/view/content/dashboard.xhtml");
            }
        } catch (Exception e) {
            contex.addMessage(null, new FacesMessage("Error en metodo iniciarsesion", String.valueOf(e)));
        }
    }
    
    public void sessionSecurity() throws IOException{
        User us = SessionUtils.getUserName();
        if("12345".equals(us.getCOD_PER()) || us == null){
          FacesContext.getCurrentInstance().getExternalContext().redirect("../../login.xhtml");
        }
    }

    public void sessionPublic() {
        if (sessionPublica == false) {
            User usu = new User();
            usu.setCOD_PER("12345");
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", usu);
            sessionPublica = true;
        }
    }

    public void logout() throws Exception {
        try {
            Usuario.setPASS_USER(null);
            HttpSession session = SessionUtils.getSession();
            session.invalidate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Ciclocero");
        } catch (IOException e) {
            throw e;
        }
    }

    public void changePass() throws Exception {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.changePassword(Usuario);
            Usuario.setNew_pass("");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("CLAVE CAMBIADA", "La contraseña se guardo correctamente"));
        } catch (Exception e) {
            throw e;
        }
    }

    public void jalarIniciales() throws SQLException {
        UserDao dao;
        try {
            dao = new UserDao();
            dao.jalarIniciales(Usuario);
        } catch (SQLException e) {
            throw e;
        }
    }

    public void limpiar() {
        alum.setCod_col(null);
        alum.setYear_est(null);
        alum.setSec_est(null);
        alum.setDni_est(null);
        alum.setNom_est(null);
        alum.setApe_est(null);
        alum.setFecnac_est(null);
        alum.setDirec_est(null);
        alum.setRef_est(null);
        alum.setCel_est(null);
        alum.setCorreo_est(null);
        alum.setUbigeo_est(null);
        alum.setNota1(null);
        alum.setNota2(null);
        alum.setNota3(null);
        alum.setNota4(null);
    }

    public void cancelar() {
        Usuario.setNew_pass("");
    }

    public User getUsuario() {
        return Usuario;
    }

    public void setUsuario(User Usuario) {
        this.Usuario = Usuario;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

}
