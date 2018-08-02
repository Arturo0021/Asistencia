package mensajes.team.mx.asistencia.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mensajes.team.mx.asistencia.Entities.Entities_Information_Celphone;

public class Data_Information_Celphone {

    private static SQLiteDatabase db = null;

    public static mensajes.team.mx.asistencia.Entities.Entities_Information_Celphone get_Information(Context context, mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios) {

        db = (new DBHelper(context)).getWritableDatabase();
        mensajes.team.mx.asistencia.Entities.Entities_Information_Celphone cel = null;

        String query = "SELECT "
                            + DBHelper.COLUMN_IMEI + ", "
                            + DBHelper.COLUMN_SIM + ", "
                            + DBHelper.COLUMN_TELEFONO
                    + " FROM " + DBHelper.TABLE_IMEI + " WHERE " + DBHelper.COLUMN_IDUSUARIO + " = " + entities_usuarios.getId() + ";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                cel = new Entities_Information_Celphone();
                cel.setImei(cursor.getString(0));
                cel.setSim(cursor.getString(1));
                cel.setTelefono(cursor.getString(2));
            }while (cursor.moveToNext());
        }

        return cel;
    }

    public static void Insert_Information(Context context, mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios) {

        db = (new DBHelper(context)).getWritableDatabase();

        String query = "SELECT COUNT(1) FROM " + DBHelper.TABLE_IMEI
                            + " WHERE " + DBHelper.COLUMN_IMEI + " = '" + entities_usuarios.getIMEI() + "' AND "
                                        + DBHelper.COLUMN_SIM + " = '" + entities_usuarios.getSim() + "' AND "
                                        + DBHelper.COLUMN_IDUSUARIO + " = " + entities_usuarios.getId() + ";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            int existe = cursor.getInt(0);
            if(existe == 0) {
                String insert = "INSERT INTO " + DBHelper.TABLE_IMEI
                                    + "("
                                        + DBHelper.COLUMN_IMEI + ", "
                                        + DBHelper.COLUMN_SIM + ", "
                                        + DBHelper.COLUMN_TELEFONO + ", "
                                        + DBHelper.COLUMN_IDUSUARIO
                                    + ") VALUES ("
                                        + "'" + entities_usuarios.getIMEI() + "', "
                                        + "'" + entities_usuarios.getSim() + "', "
                                        + "'" + entities_usuarios.getTelefono() + "', "
                                        + entities_usuarios.getId()
                                    + ");";
                db.execSQL(insert);
            } else {

            }
        }

    }

}
