package mensajes.team.mx.asistencia.Entities;

import java.io.Serializable;

public class Entities_Fotos implements Serializable {

    public Integer Id;
    public Integer IdVisita;
    public String Foto;
    public String Tipo;
    public String Comentario;
    public String Nombre;
    public String FechaCrea;
    public Integer StatusSync;
    public String FechaSync;
    public String categoria;

    public Entities_Fotos() {    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getIdVisita() {
        return IdVisita;
    }

    public void setIdVisita(Integer idVisita) {
        IdVisita = idVisita;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFechaCrea() {
        return FechaCrea;
    }

    public void setFechaCrea(String fechaCrea) {
        FechaCrea = fechaCrea;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
