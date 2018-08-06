package mensajes.team.mx.asistencia.Business;

import android.content.Context;

public class Business_Versiones {

    public static mensajes.team.mx.asistencia.Entities.Entities_Versiones get_Versiones(Context context, String Version_Actual) throws Exception {

        if(Version_Actual.equals("")) {
            throw new Exception("Variable Version_Actual No Referenciado get_Versiones");
        }

        mensajes.team.mx.asistencia.Entities.Entities_Versiones get_Versiones = mensajes.team.mx.asistencia.Data.Data_Versiones.get_Versiones(context, Version_Actual);

        return get_Versiones;
    }

}
