package mensajes.team.mx.asistencia.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mensajes.team.mx.asistencia.Entities.Entities_Visitas;
import mensajes.team.mx.asistencia.Utilerias.Utils;

public class Data_Visitas {

    private static SQLiteDatabase db = null;

    public static void Insert_Visita(Context context, mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas tiendas, mensajes.team.mx.asistencia.Entities.Entities_Usuarios usuarios, double latitud, double longitud, String time) {

        db = (new DBHelper(context)).getWritableDatabase();

        try {

            String query = "SELECT count(1) FROM " + DBHelper.TABLE_VISITA
                    + " WHERE "  + DBHelper.COLUMN_IDUSUARIO + " = " + usuarios.getId() + " AND "
                        + DBHelper.COLUMN_DETERMINANTE + " = " + tiendas.getDeterminanteGSP() + " AND "
                        + "DATE(" + DBHelper.COLUMN_FECHAENTRADA + ") = " + "DATE('" + time + "');";

            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()) {
                int existe = cursor.getInt(0);
                if(existe == 0) {
                    String insert = "INSERT INTO " + DBHelper.TABLE_VISITA
                                        + "("
                                            + DBHelper.COLUMN_IDPROYECTO + ", "
                                            + DBHelper.COLUMN_DETERMINANTE + ", "
                                            + DBHelper.COLUMN_IDUSUARIO + ", "
                                            + DBHelper.COLUMN_LATITUD + ", "
                                            + DBHelper.COLUMN_LONGITUD + ", "
                                            + DBHelper.COLUMN_FECHAENTRADA + ", "
                                            + DBHelper.COLUMN_FECHAACTUAL + ", "
                                            + DBHelper.COLUMN_TIPOUBICACION
                                        + ") VALUES ("
                                            + usuarios.getProyecto() + ", "
                                            + tiendas.getDeterminanteGSP() + ", "
                                            + usuarios.getId() + ", "
                                            + latitud + ", "
                                            + longitud + ", "
                                            + "'" + time + "', "
                                            + "'" + time + "', "
                                            + "''"
                                        + ");";
                    db.execSQL(insert);
                }
            }

        } catch (Exception e){
            e.getMessage();
        }
    }

    public static mensajes.team.mx.asistencia.Entities.Entities_Visitas get_Visita(Context context, mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas tiendas, mensajes.team.mx.asistencia.Entities.Entities_Usuarios usuarios, String time) {

        db = (new DBHelper(context)).getWritableDatabase();
        mensajes.team.mx.asistencia.Entities.Entities_Visitas visitas = null;

        String query = "SELECT "
                        + DBHelper.COLUMN_ID + ", "
                        + DBHelper.COLUMN_IDPROYECTO + ", "
                        + DBHelper.COLUMN_DETERMINANTE + ", "
                        + DBHelper.COLUMN_IDUSUARIO + ", "
                        + DBHelper.COLUMN_LATITUD + ", "
                        + DBHelper.COLUMN_LONGITUD + ", "
                        + DBHelper.COLUMN_ABIERTA + ", "
                        + DBHelper.COLUMN_FECHAENTRADA + ", "
                        + DBHelper.COLUMN_FECHASALIDA + ", "
                        + DBHelper.COLUMN_FECHAACTUAL + ", "
                        + DBHelper.COLUMN_FECHACIERRE + ", "
                        + DBHelper.COLUMN_TIPOUBICACION
                            + " FROM " + DBHelper.TABLE_VISITA
                                + " WHERE "  + DBHelper.COLUMN_IDUSUARIO + " = " + usuarios.getId() + " AND "
                                + DBHelper.COLUMN_DETERMINANTE + " = " + tiendas.getDeterminanteGSP() + " AND "
                                + "DATE(" + DBHelper.COLUMN_FECHAENTRADA + ") = " + "DATE('" + time + "');";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            visitas = new Entities_Visitas();
            visitas.setId(cursor.getInt(0));
            visitas.setIdProyecto(cursor.getInt(1));
            visitas.setDeterminanteGSP(cursor.getInt(2));
            visitas.setIdUsuario(cursor.getInt(3));
            visitas.setLatitud(cursor.getDouble(4));
            visitas.setLongitud(cursor.getDouble(5));
            visitas.setAbierta(cursor.getInt(6));
            visitas.setFechaEntrada(cursor.getString(7));
            visitas.setFechaSalida(cursor.getString(8));
            visitas.setFechaAcual(cursor.getString(9));
            visitas.setFechaCierre(cursor.getString(10));
            visitas.setTipoUbicacion(cursor.getString(11));

        }

        return visitas;
    }

    public static void update_visita(Context context, mensajes.team.mx.asistencia.Entities.Entities_Visitas visitas, String time) {

        db = (new DBHelper(context)).getWritableDatabase();

        String update = "UPDATE " + DBHelper.TABLE_VISITA
                            + " SET " + DBHelper.COLUMN_FECHASALIDA + " = '" + time + "', "
                                      + DBHelper.COLUMN_FECHACIERRE  + " = '" + time + "', "
                                      + DBHelper.COLUMN_ABIERTA + " = 2"
                            + " WHERE " + DBHelper.COLUMN_ID + " = " + visitas.getId()
                                + " AND " + DBHelper.COLUMN_FECHASSYNC + " IS NULL;";
        db.execSQL(update);

    }

    public static mensajes.team.mx.asistencia.Entities.Entities_Visitas get_Visita_Abierta(Context context, mensajes.team.mx.asistencia.Entities.Entities_Usuarios usuarios, String time) {

        db = (new DBHelper(context)).getWritableDatabase();
        mensajes.team.mx.asistencia.Entities.Entities_Visitas visitas = null;
        String query = "SELECT "
                + DBHelper.COLUMN_ID + ", "
                + DBHelper.COLUMN_IDPROYECTO + ", "
                + DBHelper.COLUMN_DETERMINANTE + ", "
                + DBHelper.COLUMN_IDUSUARIO + ", "
                + DBHelper.COLUMN_LATITUD + ", "
                + DBHelper.COLUMN_LONGITUD + ", "
                + DBHelper.COLUMN_ABIERTA + ", "
                + DBHelper.COLUMN_FECHAENTRADA + ", "
                + DBHelper.COLUMN_FECHASALIDA + ", "
                + DBHelper.COLUMN_FECHAACTUAL + ", "
                + DBHelper.COLUMN_FECHACIERRE + ", "
                + DBHelper.COLUMN_TIPOUBICACION
                + " FROM " + DBHelper.TABLE_VISITA
                + " WHERE "  + DBHelper.COLUMN_IDUSUARIO + " = " + usuarios.getId()
                + " AND " + DBHelper.COLUMN_ABIERTA + " = 1"
                + " AND DATE(" + DBHelper.COLUMN_FECHAENTRADA + ") = " + "DATE('" + time + "');";
        Cursor cursor = db.rawQuery(query, null);


        if(cursor.moveToFirst()) {
            visitas = new Entities_Visitas();
            visitas.setId(cursor.getInt(0));
            visitas.setIdProyecto(cursor.getInt(1));
            visitas.setDeterminanteGSP(cursor.getInt(2));
            visitas.setIdUsuario(cursor.getInt(3));
            visitas.setLatitud(cursor.getDouble(4));
            visitas.setLongitud(cursor.getDouble(5));
            visitas.setAbierta(cursor.getInt(6));
            visitas.setFechaEntrada(cursor.getString(7));
            visitas.setFechaSalida(cursor.getString(8));
            visitas.setFechaAcual(cursor.getString(9));
            visitas.setFechaCierre(cursor.getString(10));
            visitas.setTipoUbicacion(cursor.getString(11));
        }

        return visitas;
    }

}
