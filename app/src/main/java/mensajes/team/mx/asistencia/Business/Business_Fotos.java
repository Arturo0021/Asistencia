package mensajes.team.mx.asistencia.Business;

import android.content.Context;

public class Business_Fotos {

    public static void Insert_Fotos(Context context, mensajes.team.mx.asistencia.Entities.Entities_Fotos fotos) throws Exception {

        if(fotos == null) {
            throw new Exception("Objeto Fotos No Referenciado Insert_Fotos");
        }

        mensajes.team.mx.asistencia.Data.Data_Fotos.Insert_Fotos(context, fotos);
    }

    public static mensajes.team.mx.asistencia.Entities.Collection_Fotos get_FotosCollection(Context context, mensajes.team.mx.asistencia.Entities.Entities_Visitas visitas) throws Exception{

        if(visitas == null){
            throw new Exception("Objecto Visitas No Referenciado get_FotosCollection");
        }

        mensajes.team.mx.asistencia.Entities.Collection_Fotos collection = mensajes.team.mx.asistencia.Data.Data_Fotos.get_FotosCollection(context, visitas);

        return collection;
    }

}
