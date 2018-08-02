package mensajes.team.mx.asistencia.Entities;

import java.io.Serializable;

public class Entities_Visitas implements Serializable {

    public Integer Id;
    public Integer IdProyecto;
    public Integer DeterminanteGSP;
    public Integer IdUsuario;
    public String FechaCrea;
    public Double Latitud;
    public Double Longitud;
    public Integer IdStatus;
    public Integer Abierta;
    public String FechaEntrada;
    public String FechaSalida;
    public String FechaCierre;
    public String FechaAcual;
    public Integer StatusSync;
    public String FechaSync;
    public String tipoUbicacion;

    public Entities_Visitas() {    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getIdProyecto() {
        return IdProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        IdProyecto = idProyecto;
    }

    public Integer getDeterminanteGSP() {
        return DeterminanteGSP;
    }

    public void setDeterminanteGSP(Integer determinanteGSP) {
        DeterminanteGSP = determinanteGSP;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getFechaCrea() {
        return FechaCrea;
    }

    public void setFechaCrea(String fechaCrea) {
        FechaCrea = fechaCrea;
    }

    public Double getLatitud() {
        return Latitud;
    }

    public void setLatitud(Double latitud) {
        Latitud = latitud;
    }

    public Double getLongitud() {
        return Longitud;
    }

    public void setLongitud(Double longitud) {
        Longitud = longitud;
    }

    public Integer getIdStatus() {
        return IdStatus;
    }

    public void setIdStatus(Integer idStatus) {
        IdStatus = idStatus;
    }

    public Integer getAbierta() {
        return Abierta;
    }

    public void setAbierta(Integer abierta) {
        Abierta = abierta;
    }

    public String getFechaEntrada() {
        return FechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        FechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return FechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        FechaSalida = fechaSalida;
    }

    public String getFechaCierre() {
        return FechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        FechaCierre = fechaCierre;
    }

    public String getFechaAcual() {
        return FechaAcual;
    }

    public void setFechaAcual(String fechaAcual) {
        FechaAcual = fechaAcual;
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

    public String getTipoUbicacion() {
        return tipoUbicacion;
    }

    public void setTipoUbicacion(String tipoUbicacion) {
        this.tipoUbicacion = tipoUbicacion;
    }
}
