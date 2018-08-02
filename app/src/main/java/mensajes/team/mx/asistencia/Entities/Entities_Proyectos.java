package mensajes.team.mx.asistencia.Entities;

import java.io.Serializable;
import java.util.Date;

public class Entities_Proyectos implements Serializable {

    public Integer Id;
    public String Nombre;
    public Date FechaInicio;
    public Date FechaFin;
    public Integer  Activo;
    public Integer  IdUsuario;

    public Entities_Proyectos() {

    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        FechaFin = fechaFin;
    }

    public Integer getActivo() {
        return Activo;
    }

    public void setActivo(Integer activo) {
        Activo = activo;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }

}
