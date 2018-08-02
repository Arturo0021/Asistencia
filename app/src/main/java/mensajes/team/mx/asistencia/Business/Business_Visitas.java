package mensajes.team.mx.asistencia.Business;

import android.content.Context;

import mensajes.team.mx.asistencia.Utilerias.Utils;

public class Business_Visitas {

    public static void Insert_Visita(Context context, mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas tiendas, mensajes.team.mx.asistencia.Entities.Entities_Usuarios usuarios, double latitud, double longitud, String time) throws Exception {

        if(tiendas == null) {
            throw new Exception("Objeto Tiendas No Referenciado Insert_Visita");
        }

        if(usuarios == null) {
            throw new Exception("Objeto Usuarios No Referenciado Insert_Visita");
        }

        if(time.equalsIgnoreCase("")){
            time = Utils.getFecha_x();
        }

        mensajes.team.mx.asistencia.Data.Data_Visitas.Insert_Visita(context, tiendas, usuarios, latitud, longitud, time);

    }

    public static mensajes.team.mx.asistencia.Entities.Entities_Visitas get_Visita(Context context, mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas tiendas, mensajes.team.mx.asistencia.Entities.Entities_Usuarios usuarios, String time) throws Exception {

        if(tiendas == null) {
            throw new Exception("Objeto Tiendas No Referenciado get_Visita");
        }

        if(usuarios == null) {
            throw new Exception("Objeto Usuarios No Referenciado get_Visita");
        }

        mensajes.team.mx.asistencia.Entities.Entities_Visitas visitas = mensajes.team.mx.asistencia.Data.Data_Visitas.get_Visita(context, tiendas, usuarios, time);

        return visitas;
    }

    public static void update_visita(Context context, mensajes.team.mx.asistencia.Entities.Entities_Visitas visitas, String time) throws Exception {

        if(visitas == null) {
            throw new Exception("Objeto Visitas No Referenciado update_visita");
        }

        mensajes.team.mx.asistencia.Data.Data_Visitas.update_visita(context, visitas, time);
    }

}
