package mensajes.team.mx.asistencia.Data;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mensajes.team.mx.asistencia.Entities.Entities_Versiones;

public class Data_Versiones {

    private static SQLiteDatabase db = null;

    public static void Insert_Versiones(Context context, mensajes.team.mx.asistencia.Entities.Entities_Versiones versiones){

        db = (new DBHelper(context)).getWritableDatabase();
        String query = "SELECT COUNT(1) FROM " + DBHelper.TABLE_VERSIONES
                            + " WHERE " + DBHelper.COLUMN_ID + " = " + versiones.getId();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            int existe = cursor.getInt(0);
            if(existe == 0) {
                String insert = "INSERT INTO " + DBHelper.TABLE_VERSIONES
                                    + "("
                                        + DBHelper.COLUMN_ID + ", "
                                        + DBHelper.COLUMN_NOMBRE + ", "
                                        + DBHelper.COLUMN_VERSION + ", "
                                        + DBHelper.COLUMN_URL
                                    + ") VALUES ("
                                        + versiones.getId() + ", "
                                        + "'" + versiones.getNombreapk() + "', "
                                        + versiones.getVersion() + ", "
                                        + "'" + versiones.getUrl() + "'"
                                    + ");";
                db.execSQL(insert);
            } else {
                String update = "UPDATE " + DBHelper.TABLE_VERSIONES
                                    + " SET " + DBHelper.COLUMN_NOMBRE + " = '" + versiones.getNombreapk() + "', "
                                              + DBHelper.COLUMN_VERSION + " = '" + versiones.getVersion() + "'"
                                    + " WHERE " + DBHelper.COLUMN_ID + " = " + versiones.getId() + ";";
                db.execSQL(update);
            }
        }
    }

    public static mensajes.team.mx.asistencia.Entities.Entities_Versiones get_Versiones(Context context, String Version_Actual) {

        db = (new DBHelper(context)).getWritableDatabase();
        mensajes.team.mx.asistencia.Entities.Entities_Versiones new_version = new Entities_Versiones();

        String query = "SELECT * FROM " + DBHelper.TABLE_VERSIONES
                           + " WHERE " + DBHelper.COLUMN_VERSION + " > " + Version_Actual + ";";

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {

            new_version.setId(cursor.getInt(0));
            new_version.setNombreapk(cursor.getString(1));
            new_version.setVersion(cursor.getInt(2));
            new_version.setUrl(cursor.getString(3));

        }

        return new_version;
    }

}
