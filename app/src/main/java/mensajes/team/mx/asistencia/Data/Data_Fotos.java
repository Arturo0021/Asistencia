package mensajes.team.mx.asistencia.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mensajes.team.mx.asistencia.Entities.Collection_Fotos;
import mensajes.team.mx.asistencia.Entities.Entities_Fotos;

public class Data_Fotos {

    private static SQLiteDatabase db = null;

    public static void Insert_Fotos(Context context, mensajes.team.mx.asistencia.Entities.Entities_Fotos fotos) {

        db = (new DBHelper(context)).getWritableDatabase();

        String query = "SELECT COUNT(1) FROM " + DBHelper.TABLE_PHOTO
                            + " WHERE " + DBHelper.COLUMN_IDVISITA + " = " + fotos.getIdVisita()
                            + " AND " + DBHelper.COLUMN_TIPO + " = '" + fotos.getTipo() + "';";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            int existe = cursor.getInt(0);
            if(existe == 0) {
              String insert = "INSERT INTO " + DBHelper.TABLE_PHOTO
                                + "("
                                      + DBHelper.COLUMN_IDVISITA + ", "
                                      + DBHelper.COLUMN_FOTO + ", "
                                      + DBHelper.COLUMN_TIPO + ", "
                                      + DBHelper.COLUMN_STATUSSYNC
                                + ") VALUES ("
                                        + fotos.getIdVisita() + ", "
                                        + "'" + fotos.getFoto() + "', "
                                        + "'" + fotos.getTipo() + "', "
                                        + "0"
                                + ");";
              db.execSQL(insert);
            }
        }
    }

    public static mensajes.team.mx.asistencia.Entities.Collection_Fotos get_FotosCollection(Context context, mensajes.team.mx.asistencia.Entities.Entities_Visitas visitas){

        db = (new DBHelper(context)).getWritableDatabase();
        mensajes.team.mx.asistencia.Entities.Collection_Fotos collection = new Collection_Fotos();
        String query = "SELECT "
                            + DBHelper.COLUMN_IDVISITA + ", "
                            + DBHelper.COLUMN_FOTO + ", "
                            + DBHelper.COLUMN_TIPO + ", "
                            + DBHelper.COLUMN_STATUSSYNC
                        + " FROM " + DBHelper.TABLE_PHOTO
                            + " WHERE " + DBHelper.COLUMN_IDVISITA + " = " + visitas.getId() + ";";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {

                mensajes.team.mx.asistencia.Entities.Entities_Fotos foto = new Entities_Fotos();
                foto.setIdVisita(cursor.getInt(0));
                foto.setFoto(cursor.getString(1));
                foto.setTipo(cursor.getString(2));
                foto.setStatusSync(cursor.getInt(3));
                collection.add(foto);

            }while (cursor.moveToNext());
        }
        return collection;
    }

}
