package mensajes.team.mx.asistencia.Business;

import android.content.Context;

public class Business_Information_Celphone {

    public static mensajes.team.mx.asistencia.Entities.Entities_Information_Celphone get_Information(Context context, mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios) throws Exception {

        if(entities_usuarios == null) {
            throw new Exception("Objeto Usuario No Referenciado");
        }

        mensajes.team.mx.asistencia.Entities.Entities_Information_Celphone cel = mensajes.team.mx.asistencia.Data.Data_Information_Celphone.get_Information(context, entities_usuarios);

        return cel;
    }

    public static void Insert_Information(Context context, mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios) throws Exception {

        if(entities_usuarios == null) {
            throw new Exception("Objeto Usuario No Referenciado");
        }

        mensajes.team.mx.asistencia.Data.Data_Information_Celphone.Insert_Information(context, entities_usuarios);
    }

}
