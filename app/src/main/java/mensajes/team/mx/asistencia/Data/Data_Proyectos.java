package mensajes.team.mx.asistencia.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Data_Proyectos {

    private static SQLiteDatabase db = null;

    public static void Insert_Proyecto(Context context, mensajes.team.mx.asistencia.Entities.Entities_Proyectos entities_proyectos) {

        db = (new DBHelper(context)).getWritableDatabase();

        String query = "SELECT COUNT(1) FROM " + DBHelper.TABLE_PROYECTOS
                            + " WHERE " + DBHelper.COLUMN_ID + " = " + entities_proyectos.getId() + ";";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            int existe = cursor.getInt(0);
            if(existe == 0) {
                String insert = "INSERT INTO " + DBHelper.TABLE_PROYECTOS
                                    + "("
                                        + DBHelper.COLUMN_ID + ", "
                                        + DBHelper.COLUMN_NOMBRE
                                    + ") VALUES ("
                                        + entities_proyectos.getId() + ", "
                                        + "'" + entities_proyectos.getNombre() + "'"
                                    + ");";
                db.execSQL(insert);
            } else {
                String update = "UPDATE " + DBHelper.TABLE_PROYECTOS
                                    + " SET " + DBHelper.COLUMN_NOMBRE + " = '" + entities_proyectos.getNombre() + "'"
                                + " WHERE " + DBHelper.COLUMN_ID + " = " + entities_proyectos.getId() + ";";
                db.execSQL(update);
            }
        }
    }

}
