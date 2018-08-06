package mensajes.team.mx.asistencia.Entities;

import java.io.Serializable;

public class Entities_Mensajes implements Serializable {

    public Integer Id;
    public String Tipo;
    public String Cuerpo;
    public String CapturaFecha;
    public String FechaFin;
    public String FechaEnvio;
    public Integer IdProyecto;
    public Integer StatusSync;
    public String FechaSync;
    public Integer Activo;

    public Entities_Mensajes() {    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getCuerpo() {
        return Cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        Cuerpo = cuerpo;
    }

    public String getCapturaFecha() {
        return CapturaFecha;
    }

    public void setCapturaFecha(String capturaFecha) {
        CapturaFecha = capturaFecha;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(String fechaFin) {
        FechaFin = fechaFin;
    }

    public String getFechaEnvio() {
        return FechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        FechaEnvio = fechaEnvio;
    }

    public Integer getIdProyecto() {
        return IdProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        IdProyecto = idProyecto;
    }

    public Integer getStatusSync() {
        return StatusSync;
    }

    public void setStatusSync(Integer statusSync) {
        StatusSync = statusSync;
    }

    public String getFechaSync() {
        return FechaSync;
    }

    public void setFechaSync(String fechaSync) {
        FechaSync = fechaSync;
    }

    public Integer getActivo() {
        return Activo;
    }

    public void setActivo(Integer activo) {
        Activo = activo;
    }
}
