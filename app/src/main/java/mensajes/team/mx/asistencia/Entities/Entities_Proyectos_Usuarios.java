package mensajes.team.mx.asistencia.Entities;

import java.io.Serializable;

public class Entities_Proyectos_Usuarios implements Serializable{

    public Integer IdUsuario;
    public Integer IdProyecto;

    public Entities_Proyectos_Usuarios() {

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
