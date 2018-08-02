package mensajes.team.mx.asistencia.Entities;

import java.io.Serializable;

public class Entities_Usuarios implements Serializable {

    public int Id;
    public String Usuario;
    public String Passsword;
    public String nombre;
    public int Proyecto;
    public String IMEI;
    public String Sim;
    public String Telefono;

    public Entities_Usuarios() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPasssword() {
        return Passsword;
    }

    public void setPasssword(String passsword) {
        Passsword = passsword;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getProyecto() {
        return Proyecto;
    }

    public void setProyecto(int proyecto) {
        Proyecto = proyecto;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getSim() {
        return Sim;
    }

    public void setSim(String sim) {
        Sim = sim;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
