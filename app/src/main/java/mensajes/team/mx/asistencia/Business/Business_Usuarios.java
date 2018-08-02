package mensajes.team.mx.asistencia.Business;

import android.content.Context;

public class Business_Usuarios {

    public static mensajes.team.mx.asistencia.Entities.Entities_Usuarios DownloadUsuario(String usuario, String password) throws Exception{

        if(usuario.equalsIgnoreCase("")) {
            throw new Exception("Variable Usuario No Referenciado");
        }

        if(password.equalsIgnoreCase("")) {
            throw new Exception("Variable Password No Referenciado");
        }

        mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios = mensajes.team.mx.asistencia.Data.Data_Usuarios.DownloadUsuario(usuario, password);

        return entities_usuarios;
    }

    public static void insert_Usuario(Context context, mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios) throws Exception {

        if(entities_usuarios == null) {
            throw new Exception("Objeto Usuario No Referenciado");
        }

        mensajes.team.mx.asistencia.Data.Data_Usuarios.insert_Usuario(context, entities_usuarios);
    }

    public static mensajes.team.mx.asistencia.Entities.Entities_Usuarios get_Usuario(Context context, String usuario) throws Exception{
        if(usuario.equals("")) {
            throw new Exception("Variable Usuario No Referenciado get_Usuario");
        }
        mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios = mensajes.team.mx.asistencia.Data.Data_Usuarios.get_Usuario(context, usuario);

        return entities_usuarios;
    }

}
