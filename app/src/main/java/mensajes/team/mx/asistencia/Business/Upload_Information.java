package mensajes.team.mx.asistencia.Business;

import android.content.Context;

public class Upload_Information {

    public static void upload_visita(mensajes.team.mx.asistencia.Entities.Entities_Visitas visita) throws Exception {

        if(visita == null) {
            throw new Exception("Objeto Visitas No Referenciado upload_visita");
        }

        mensajes.team.mx.asistencia.Data.Upload_Information.upload_visita(visita);

    }

    public static void Foto_Entrada(Context context, mensajes.team.mx.asistencia.Entities.Entities_Visitas visita, mensajes.team.mx.asistencia.Entities.Entities_Fotos foto) throws Exception {

        if(visita == null) {
            throw new Exception("Objeto Visitas No Referenciado Foto_Entrada");
        }

        if(foto == null) {
            throw new Exception("Objeto Fotos No Referenciado Foto_Entrada");
        }

        mensajes.team.mx.asistencia.Data.Upload_Information.Foto_Entrada(context, visita, foto);
    }

    public static void Foto_Salida(Context context, mensajes.team.mx.asistencia.Entities.Entities_Visitas visita,mensajes.team.mx.asistencia.Entities.Entities_Fotos foto) throws Exception{

        if(visita == null) {
            throw new Exception("Objeto Visitas No Referenciado Foto_Entrada");
        }

        if(foto == null) {
            throw new Exception("Objeto Fotos No Referenciado Foto_Entrada");
        }

        mensajes.team.mx.asistencia.Data.Upload_Information.Foto_Salida(context, visita, foto);

    }

}
