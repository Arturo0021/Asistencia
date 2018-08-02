package mensajes.team.mx.asistencia.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;

import mensajes.team.mx.asistencia.Entities.Entities_Usuarios;

public class Data_Usuarios {

    private static SQLiteDatabase db = null;
    private static String METHOD_GETUSUARIO = "";
    private static StringEntity stringEntity = null;
    private static JSONObject jsonResult = null;
    private static JSONObject jsonObject = null;
    private static JSONStringer jsonStringer = null;
    private static String strResult = null;


    public static mensajes.team.mx.asistencia.Entities.Entities_Usuarios DownloadUsuario(String usuario, String password) throws JSONException, IOException, Exception {
        mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios = null;
        METHOD_GETUSUARIO = "/getUsuario";

        Wcf_Service fservice = new Wcf_Service();
        jsonStringer = new JSONStringer()
                            .object()
                                .key("Usuario")
                                .object()
                                    .key("Usuario").value(usuario)
                                    .key("Password").value(password)
                                .endObject()
                            .endObject();

        stringEntity = new StringEntity(jsonStringer.toString());
        jsonResult = fservice.HttpPost(METHOD_GETUSUARIO, stringEntity);

        if(!jsonResult.get("getUsuarioResult").equals(null))
        {
            jsonObject = (JSONObject)jsonResult.get("getUsuarioResult");
            entities_usuarios = new mensajes.team.mx.asistencia.Entities.Entities_Usuarios();
            entities_usuarios.setId(jsonObject.getInt("Id"));
            entities_usuarios.setUsuario(jsonObject.getString("Usuario"));
            entities_usuarios.setPasssword(jsonObject.getString("Password"));
            entities_usuarios.setNombre(jsonObject.getString("Nombre"));

            if(jsonObject.getString("Apat")!= null)
            {
                entities_usuarios.setNombre(jsonObject.getString("Nombre") + " " + jsonObject.getString("Apat"));
            }

        }

        return entities_usuarios;

    }

    public static void insert_Usuario(Context context, mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios) {

        db = (new DBHelper(context)).getWritableDatabase();

        String query = "SELECT COUNT(1) FROM " + DBHelper.TABLE_USUARIOS
                            + " WHERE " + DBHelper.COLUMN_ID + " = " + entities_usuarios.getId() + ";";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            int existe = cursor.getInt(0);
            if(existe == 0){
                String insert = "INSERT INTO " + DBHelper.TABLE_USUARIOS
                                    + "("
                                        + DBHelper.COLUMN_ID + ", "
                                        + DBHelper.COLUMN_USUARIO + ", "
                                        + DBHelper.COLUMN_PASSWORD + ", "
                                        + DBHelper.COLUMN_NOMBRE
                                    + ") VALUES ("
                                        + entities_usuarios.getId() + ", "
                                        + "'" + entities_usuarios.getUsuario() + "', "
                                        + "'" + entities_usuarios.getPasssword() + "', "
                                        + "'" + entities_usuarios.getNombre() + "'"
                                    + ");";
                db.execSQL(insert);
            } else {
                 String update = "UPDATE " + DBHelper.TABLE_USUARIOS
                                    + " SET " + DBHelper.COLUMN_USUARIO + " = '" + entities_usuarios.getUsuario() +"', "
                                                + DBHelper.COLUMN_PASSWORD + " = '" + entities_usuarios.getPasssword() + "', "
                                                + DBHelper.COLUMN_NOMBRE + " = '" + entities_usuarios.getNombre() + "' "
                                    + " WHERE " + DBHelper.COLUMN_ID + " = " + entities_usuarios.getId() + ";";
                 db.execSQL(update);
            }
        }

    }

    public static mensajes.team.mx.asistencia.Entities.Entities_Usuarios get_Usuario(Context context, String usuario){

        db = (new DBHelper(context)).getWritableDatabase();
        mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios = new Entities_Usuarios();
        String query = "SELECT "
                            + DBHelper.COLUMN_ID + ", "
                            + DBHelper.COLUMN_USUARIO + ", "
                            + DBHelper.COLUMN_PASSWORD + ", "
                            + DBHelper.COLUMN_NOMBRE
                        + " FROM " + DBHelper.TABLE_USUARIOS
                            + " WHERE " + DBHelper.COLUMN_USUARIO + " = '" + usuario + "';";
        Cursor cursor = db.rawQuery(query , null);
        if(cursor.moveToFirst()){
            entities_usuarios.setId(cursor.getInt(0));
            entities_usuarios.setUsuario(cursor.getString(1));
            entities_usuarios.setPasssword(cursor.getString(2));
            entities_usuarios.setNombre(cursor.getString(3));
        }
        return entities_usuarios;
    }

}
