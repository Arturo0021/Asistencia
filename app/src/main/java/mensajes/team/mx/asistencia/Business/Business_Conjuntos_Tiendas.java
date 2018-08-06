package mensajes.team.mx.asistencia.Business;

import android.content.Context;

import mensajes.team.mx.asistencia.Utilerias.Utils;

public class Business_Conjuntos_Tiendas {

    public static mensajes.team.mx.asistencia.Entities.Collection_Conjuntos_Tiendas get_Tiendas(Context context, mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios proyectos_usuarios, String time) throws Exception {

        if(proyectos_usuarios == null) {
           throw new Exception("Objeto proyectos_usuarios No Referenciado get_Tiendas");
        }

        if(time.equals("")) {
            time = Utils.getFecha_x();
        }

        mensajes.team.mx.asistencia.Entities.Collection_Conjuntos_Tiendas collection = mensajes.team.mx.asistencia.Data.Data_Conjuntos_Tiendas.get_Tiendas(context, proyectos_usuarios, time);

        return collection;
    }

    public static  mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas get_Tiendas_visitada(Context context, mensajes.team.mx.asistencia.Entities.Entities_Visitas visita, mensajes.team.mx.asistencia.Entities.Entities_Usuarios usuarios, String time) throws Exception{

        if(visita == null) {
            throw new Exception("Objeto Visitas No Referenciado get_Tiendas_visitada");
        }

        if(usuarios == null) {
            throw new Exception("Objeto Usuarios No Referenciado get_Tiendas_visitada");
        }

        mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas get_Tiendas_visitada = mensajes.team.mx.asistencia.Data.Data_Conjuntos_Tiendas.get_Tiendas_visitada(context, visita,usuarios, time);

        return get_Tiendas_visitada;
    }

}
