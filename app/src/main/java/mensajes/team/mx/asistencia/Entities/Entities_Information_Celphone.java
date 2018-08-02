package mensajes.team.mx.asistencia.Entities;

import java.io.Serializable;

public class Entities_Information_Celphone implements Serializable{

    public Integer Id;
    public String Imei;
    public String Sim;
    public String Telefono;
    public String FechaCrea;
    public Integer IdUsuario;
    public Integer IdProyecto;

    public Entities_Information_Celphone() {

    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getImei() {
        return Imei;
    }

    public void setImei(String imei) {
        Imei = imei;
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

    public String getFechaCrea() {
        return FechaCrea;
    }

    public void setFechaCrea(String fechaCrea) {
        FechaCrea = fechaCrea;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }

    public Integer getIdProyecto() {
        return IdProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        IdProyecto = idProyecto;
    }
}
