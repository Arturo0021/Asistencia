package mensajes.team.mx.asistencia.Business;

import android.content.Context;

public class Business_Mensajes {

    public static mensajes.team.mx.asistencia.Entities.Collection_Mensajes get_Mensajes(Context context, mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios proyectos) throws Exception {

        if(proyectos == null) {
            throw new Exception("Objeto Proyectos No Referenciado ");
        }

        mensajes.team.mx.asistencia.Entities.Collection_Mensajes get_Mensajes = mensajes.team.mx.asistencia.Data.Data_Mensajes.get_Mensajes(context, proyectos);

        return get_Mensajes;
    }

}
