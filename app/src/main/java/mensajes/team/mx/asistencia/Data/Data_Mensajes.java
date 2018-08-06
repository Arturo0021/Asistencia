package mensajes.team.mx.asistencia.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mensajes.team.mx.asistencia.Entities.Collection_Mensajes;
import mensajes.team.mx.asistencia.Entities.Entities_Mensajes;
import mensajes.team.mx.asistencia.Entities.Entities_Proyectos;
import mensajes.team.mx.asistencia.Utilerias.Utils;

public class Data_Mensajes {

    private static SQLiteDatabase db = null;

    public static void Insert_Mensajes(Context context, mensajes.team.mx.asistencia.Entities.Entities_Mensajes mensajes){

        db = (new DBHelper(context)).getWritableDatabase();
        String query = "SELECT COUNT(1) FROM " + DBHelper.TABLE_MENSAJES
                            + " WHERE " + DBHelper.COLUMN_ID + " = " + mensajes.getId() + ";";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            int existe = cursor.getInt(0);

            if(existe == 0) {
                String insert = "INSERT INTO " + DBHelper.TABLE_MENSAJES
                                    + "("
                                        + DBHelper.COLUMN_ID + ", "
                                        + DBHelper.COLUMN_TIPO + ", "
                                        + DBHelper.COLUMN_CUERPO + ", "
                                        + DBHelper.COLUMN_CAPTURAFECHA + ", "
                                        + DBHelper.COLUMN_FECHAFIN + ", "
                                        + DBHelper.COLUMN_FECHAENVIO + ", "
                                        + DBHelper.COLUMN_IDPROYECTO + ", "
                                        + DBHelper.COLUMN_STATUSSYNC + ", "
                                        + DBHelper.COLUMN_FECHASSYNC + ", "
                                        + DBHelper.COLUMN_ACTIVO
                                    + ") VALUES ("
                                        + mensajes.getId() + ", "
                                        + "'" + mensajes.getTipo() + "', "
                                        + "'" + mensajes.getCuerpo() + "', "
                                        + "'" + mensajes.getCapturaFecha() + "', "
                                        + "'" + mensajes.getFechaFin() + "', "
                                        + "'" + mensajes.getFechaEnvio() + "', "
                                        + mensajes.getIdProyecto() + ", "
                                        + mensajes.getStatusSync() + ", "
                                        + "'" + mensajes.getFechaSync() + "', "
                                        + mensajes.getActivo()
                                    + ");";
                db.execSQL(insert);
            } else {

                String update = "UPDATE " + DBHelper.TABLE_MENSAJES
                                    + " SET " + DBHelper.COLUMN_TIPO + " = '" + mensajes.getTipo() + "', "
                                        + DBHelper.COLUMN_CUERPO + " = '" + mensajes.getCuerpo() + "', "
                                        + DBHelper.COLUMN_CAPTURAFECHA + " = '" + mensajes.getCapturaFecha() +  "', "
                                        + DBHelper.COLUMN_FECHAFIN + " = '" + mensajes.getFechaFin() + "', "
                                        + DBHelper.COLUMN_FECHAENVIO + " = '" + mensajes.getFechaEnvio() + "', "
                                        + DBHelper.COLUMN_ACTIVO + " = " + mensajes.getActivo()
                                    + " WHERE " + DBHelper.COLUMN_ID + " = " + mensajes.getId() + ";";
                db.execSQL(update);

            }

        }

    }

    public static mensajes.team.mx.asistencia.Entities.Collection_Mensajes get_Mensajes(Context context, mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios proyectos) throws Exception {

        db = (new DBHelper(context)).getWritableDatabase();
        mensajes.team.mx.asistencia.Entities.Collection_Mensajes collection = new Collection_Mensajes();

        String query = "SELECT " + DBHelper.COLUMN_TIPO + ", "
                                 + DBHelper.COLUMN_CUERPO + ", "
                                 + DBHelper.COLUMN_IDPROYECTO + ", "
                                 + "DATETIME(" + DBHelper.COLUMN_FECHAFIN + ", 'unixepoch', 'localtime') AS fechaFin, "
                                 + DBHelper.COLUMN_STATUSSYNC + ", "
                                 + "DATETIME(" + DBHelper.COLUMN_FECHAENVIO + ", 'unixepoch', 'localtime') AS fechaIn"
                    + " FROM " + DBHelper.TABLE_MENSAJES
                            + " WHERE " + DBHelper.COLUMN_IDPROYECTO + " = " + proyectos.getIdProyecto()
                                + " AND " + DBHelper.COLUMN_ACTIVO + " = 1 "
                                + " AND DATETIME(" + DBHelper.COLUMN_FECHAFIN + ") " + " >= '" + Utils.getFecha_x() + "';";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                mensajes.team.mx.asistencia.Entities.Entities_Mensajes mn = new Entities_Mensajes();
                mn.setTipo(cursor.getString(0));
                mn.setCuerpo(cursor.getString(1));
                mn.setIdProyecto(cursor.getInt(2));
                mn.setFechaFin(cursor.getString(3));
                mn.setStatusSync(cursor.getInt(4));
                mn.setFechaEnvio(cursor.getString(5));
                collection.add(mn);
            }while (cursor.moveToNext());
        }
        return collection;
    }

}
