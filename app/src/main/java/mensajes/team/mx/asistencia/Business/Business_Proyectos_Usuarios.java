package mensajes.team.mx.asistencia.Business;

import android.content.Context;

public class Business_Proyectos_Usuarios {

    public static mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios get_Proyectos_Usuarios(Context context, mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios) throws Exception {

        if(entities_usuarios == null) {
            throw new Exception("Objeto entities_usuarios No Referenciado get_Tiendas");
        }

        mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios proyectos_usuarios = mensajes.team.mx.asistencia.Data.Data_Proyectos_Usuarios.get_Proyectos_Usuarios(context, entities_usuarios);

        return proyectos_usuarios;
    }

}
