package mensajes.team.mx.asistencia.Business;

import android.content.Context;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Download_Information {

    public static void download_Proyectos(Context context, mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios) throws Exception{

        if(entities_usuarios == null) {
            throw new Exception("Objeto Usuarios No Referenciado");
        }

        mensajes.team.mx.asistencia.Data.Download_Information.download_Proyectos(context, entities_usuarios);

    }

    public static String get_fecha_Server() throws Exception {

        String Fecha = mensajes.team.mx.asistencia.Data.Download_Information.get_fecha_Server();

        return Fecha;
    }


}
