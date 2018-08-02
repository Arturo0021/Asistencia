package mensajes.team.mx.asistencia.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios;

public class Data_Proyectos_Usuarios {

    private static SQLiteDatabase db = null;

    public static void Insert_Proyectos_Usuarios(Context context, mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios, mensajes.team.mx.asistencia.Entities.Entities_Proyectos entities_proyectos) {

        db = (new DBHelper(context)).getWritableDatabase();

        String query = "SELECT COUNT(1) FROM " + DBHelper.TABLE_PROYECTOSUSUARIOS
                            + " WHERE " + DBHelper.COLUMN_IDUSUARIO + " = " + entities_usuarios.getId()
                                + " AND " + DBHelper.COLUMN_IDPROYECTO + " = " + entities_proyectos.getId() + ";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            int existe = cursor.getInt(0);

            if(existe == 0) {
                String insert = "INSERT INTO " + DBHelper.TABLE_PROYECTOSUSUARIOS
                                    + "("
                                        + DBHelper.COLUMN_IDUSUARIO + ", "
                                        + DBHelper.COLUMN_IDPROYECTO
                                    + ") VALUES ("
                                        + entities_usuarios.getId() + ", "
                                        + entities_proyectos.getId()
                                    + ");";
                db.execSQL(insert);
            } else {
                String update = "UPDATE " + DBHelper.TABLE_PROYECTOSUSUARIOS
                                    + " SET " + DBHelper.COLUMN_IDPROYECTO + " = " + entities_proyectos.getId()
                                + " WHERE " + DBHelper.COLUMN_IDUSUARIO + " = " + entities_usuarios.getId() + ";";
                db.execSQL(update);
            }

        }

    }

    public static mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios get_Proyectos_Usuarios(Context context, mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios) {

        db = (new DBHelper(context)).getWritableDatabase();
        mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios proyectos_usuarios = new Entities_Proyectos_Usuarios();

        String query = "SELECT "
                            + DBHelper.COLUMN_IDUSUARIO + ", "
                            + DBHelper.COLUMN_IDPROYECTO
                        + " FROM " + DBHelper.TABLE_PROYECTOSUSUARIOS
                                    + " WHERE " + DBHelper.COLUMN_IDUSUARIO + " = " + entities_usuarios.getId() + ";";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            proyectos_usuarios.setIdUsuario(cursor.getInt(0));
            proyectos_usuarios.setIdProyecto(cursor.getInt(1));
        }

        return proyectos_usuarios;
    }

}
