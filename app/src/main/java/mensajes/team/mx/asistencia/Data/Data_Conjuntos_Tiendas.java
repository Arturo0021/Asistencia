package mensajes.team.mx.asistencia.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mensajes.team.mx.asistencia.Entities.Collection_Conjuntos_Tiendas;
import mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas;

public class Data_Conjuntos_Tiendas {

    private static SQLiteDatabase db = null;

    public static void Insert_Conjuntos_Tiendas(Context context, mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas entities_conjuntos_tiendas) {

        db = (new DBHelper(context)).getWritableDatabase();
        String query = "SELECT COUNT(1) FROM " + DBHelper.TABLE_POP
                            + " WHERE " + DBHelper.COLUMN_ID + " = " + entities_conjuntos_tiendas.getId() + ";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            int existe = cursor.getInt(0);
            if(existe == 0) {
                String insert = "INSERT INTO " + DBHelper.TABLE_POP
                                    + "("
                                        + DBHelper.COLUMN_ID + ", "
                                        + DBHelper.COLUMN_DETERMINANTE + ", "
                                        + DBHelper.COLUMN_DETERMINANTETIENDA + ", "
                                        + DBHelper.COLUMN_FACTURACION + ", "
                                        + DBHelper.COLUMN_IDCANAL + ", "
                                        + DBHelper.COLUMN_IDGRUPO + ", "
                                        + DBHelper.COLUMN_IDCADENA + ", "
                                        + DBHelper.COLUMN_IDFORMATO + ", "
                                        + DBHelper.COLUMN_SUCURSAL + ", "
                                        + DBHelper.COLUMN_DIRECCION + ", "
                                        + DBHelper.COLUMN_CP + ", "
                                        + DBHelper.COLUMN_IDPAIS + ", "
                                        + DBHelper.COLUMN_IDESTADO + ", "
                                        + DBHelper.COLUMN_IDMUNICIPIO + ", "
                                        + DBHelper.COLUMN_IDCIUDAD + ", "
                                        + DBHelper.COLUMN_TELEFONO + ", "
                                        + DBHelper.COLUMN_LATITUD + ", "
                                        + DBHelper.COLUMN_LONGITUD + ", "
                                        + DBHelper.COLUMN_ALTITUD + ", "
                                        + DBHelper.COLUMN_ACTIVO + ", "
                                        + DBHelper.COLUMN_CALLE + ", "
                                        + DBHelper.COLUMN_NUMERO + ", "
                                        + DBHelper.COLUMN_COLONIA + ", "
                                        + DBHelper.COLUMN_NUEVOPUNTO + ", "
                                        + DBHelper.COLUMN_RANGOGPS + ", "
                                        + DBHelper.COLUMN_NIELSEN + ", "
                                        + DBHelper.COLUMN_CADENA + ", "
                                        + DBHelper.COLUMN_IDPROYECTO + ", "
                                        + DBHelper.COLUMN_STATUSSYNC + ", "
                                        + DBHelper.COLUMN_FECHASSYNC
                                    + ") VALUES ("
                                        + entities_conjuntos_tiendas.getId() + ", "
                                        + entities_conjuntos_tiendas.getDeterminanteGSP() + ", "
                                        + entities_conjuntos_tiendas.getDeterminanteTienda() + ", "
                                        + entities_conjuntos_tiendas.getFacturacion() + ", "
                                        + entities_conjuntos_tiendas.getIdCanal() + ", "
                                        + entities_conjuntos_tiendas.getIdGrupo() + ", "
                                        + entities_conjuntos_tiendas.getIdCadena() + ", "
                                        + entities_conjuntos_tiendas.getIdFormato() + ", "
                                        + "'" + entities_conjuntos_tiendas.getSucursal() + "', "
                                        + "'" + entities_conjuntos_tiendas.getDireccion() + "', "
                                        + entities_conjuntos_tiendas.getCP() + ", "
                                        + entities_conjuntos_tiendas.getIdPais() + ", "
                                        + entities_conjuntos_tiendas.getIdEstado() + ", "
                                        + entities_conjuntos_tiendas.getIdMunicipio() + ", "
                                        + entities_conjuntos_tiendas.getIdCiudad() + ", "
                                        + entities_conjuntos_tiendas.getTelefonos() + ", "
                                        + entities_conjuntos_tiendas.getLatitud() + ", "
                                        + entities_conjuntos_tiendas.getLongitud() + ", "
                                        + entities_conjuntos_tiendas.getAltitud() + ", "
                                        + entities_conjuntos_tiendas.getActivo() + ", "
                                        + "'" + entities_conjuntos_tiendas.getCalle() + "', "
                                        + entities_conjuntos_tiendas.getNumero() + ", "
                                        + "'" + entities_conjuntos_tiendas.getColonia() + "', "
                                        + entities_conjuntos_tiendas.getNuevoPunto() + ", "
                                        + entities_conjuntos_tiendas.getRangoGPS() + ", "
                                        + entities_conjuntos_tiendas.getNielsen() + ", "
                                        + "'" + entities_conjuntos_tiendas.getCadena() + "', "
                                        + entities_conjuntos_tiendas.getIdProyecto() + ", "
                                        + entities_conjuntos_tiendas.getStatusSync() + ", "
                                        + entities_conjuntos_tiendas.getFechaSync()
                                    + ");";
                db.execSQL(insert);
            } else {
                String update = "UPDATE " + DBHelper.TABLE_POP
                                    + " SET " + DBHelper.COLUMN_DETERMINANTE + " = " + entities_conjuntos_tiendas.getDeterminanteGSP() + ", "
                                        + DBHelper.COLUMN_DETERMINANTETIENDA + " = " + entities_conjuntos_tiendas.getDeterminanteTienda() + ", "
                                        + DBHelper.COLUMN_FACTURACION + " = " + entities_conjuntos_tiendas.getFacturacion() + ", "
                                        + DBHelper.COLUMN_IDCANAL + " = " + entities_conjuntos_tiendas.getIdCanal() + ", "
                                        + DBHelper.COLUMN_IDGRUPO + " = " + entities_conjuntos_tiendas.getIdGrupo() + ", "
                                        + DBHelper.COLUMN_IDCADENA + " = " + entities_conjuntos_tiendas.getIdCadena() + ", "
                                        + DBHelper.COLUMN_IDFORMATO + " = " + entities_conjuntos_tiendas.getIdFormato() + ", "
                                        + DBHelper.COLUMN_SUCURSAL + " =  " + "'" + entities_conjuntos_tiendas.getSucursal() + "', "
                                        + DBHelper.COLUMN_DIRECCION + " = " + "'" + entities_conjuntos_tiendas.getDireccion() + "', "
                                        + DBHelper.COLUMN_CP + " = " + entities_conjuntos_tiendas.getCP() + ", "
                                        + DBHelper.COLUMN_IDPAIS + " = " + entities_conjuntos_tiendas.getIdPais() + ", "
                                        + DBHelper.COLUMN_IDESTADO + " = " + entities_conjuntos_tiendas.getIdEstado() + ", "
                                        + DBHelper.COLUMN_IDMUNICIPIO + " = " + entities_conjuntos_tiendas.getIdMunicipio() + ", "
                                        + DBHelper.COLUMN_IDCIUDAD + " = " + entities_conjuntos_tiendas.getIdCiudad() + ", "
                                        + DBHelper.COLUMN_TELEFONO + " = " + entities_conjuntos_tiendas.getTelefonos() + ", "
                                        + DBHelper.COLUMN_LATITUD + " = " + entities_conjuntos_tiendas.getLatitud() + ", "
                                        + DBHelper.COLUMN_LONGITUD + " = " + entities_conjuntos_tiendas.getLongitud() + ", "
                                        + DBHelper.COLUMN_ALTITUD + " = " + entities_conjuntos_tiendas.getAltitud() + ", "
                                        + DBHelper.COLUMN_ACTIVO + " = " + entities_conjuntos_tiendas.getActivo() + ", "
                                        + DBHelper.COLUMN_CALLE + " = " + "'" + entities_conjuntos_tiendas.getCalle() + "', "
                                        + DBHelper.COLUMN_NUMERO + " = " + entities_conjuntos_tiendas.getNumero() + ", "
                                        + DBHelper.COLUMN_COLONIA + " = " + "'" + entities_conjuntos_tiendas.getColonia() + "', "
                                        + DBHelper.COLUMN_NUEVOPUNTO + " = " + entities_conjuntos_tiendas.getNuevoPunto() + ", "
                                        + DBHelper.COLUMN_RANGOGPS + " = " + entities_conjuntos_tiendas.getRangoGPS() + ", "
                                        + DBHelper.COLUMN_NIELSEN + " = " + entities_conjuntos_tiendas.getNielsen() + ", "
                                        + DBHelper.COLUMN_CADENA + " = '" + entities_conjuntos_tiendas.getCadena() + "', "
                                        + DBHelper.COLUMN_IDPROYECTO + " = " + entities_conjuntos_tiendas.getIdProyecto() + ", "
                                        + DBHelper.COLUMN_STATUSSYNC + " = " + entities_conjuntos_tiendas.getStatusSync() + ", "
                                        + DBHelper.COLUMN_FECHASSYNC + " = " + entities_conjuntos_tiendas.getFechaSync()
                                    + " WHERE " + DBHelper.COLUMN_ID + " = " + entities_conjuntos_tiendas.getId() + ";";
                db.execSQL(update);
            }
        }
    }

    public static mensajes.team.mx.asistencia.Entities.Collection_Conjuntos_Tiendas get_Tiendas(Context context, mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios proyectos_usuarios, String time) {
        db = (new DBHelper(context)).getWritableDatabase();
        mensajes.team.mx.asistencia.Entities.Collection_Conjuntos_Tiendas collection = new Collection_Conjuntos_Tiendas();

        String query = "SELECT "
                        + "p." + DBHelper.COLUMN_DETERMINANTE + ", "
                        + "p." + DBHelper.COLUMN_DETERMINANTETIENDA + ", "
                        + "p." + DBHelper.COLUMN_IDGRUPO + ", "
                        + "p." + DBHelper.COLUMN_SUCURSAL + ", "
                        + "p." + DBHelper.COLUMN_DIRECCION + ", "
                        + "p." + DBHelper.COLUMN_CP + ", "
                        + "p." + DBHelper.COLUMN_TELEFONO + ", "
                        + "p." + DBHelper.COLUMN_LATITUD + ", "
                        + "p." + DBHelper.COLUMN_LONGITUD + ", "
                        + "p." + DBHelper.COLUMN_ALTITUD + ", "
                        + "p." + DBHelper.COLUMN_ACTIVO + ", "
                        + "p." + DBHelper.COLUMN_COLONIA + ", "
                        + "p." + DBHelper.COLUMN_NUEVOPUNTO + ", "
                        + "p." + DBHelper.COLUMN_RANGOGPS + ", "
                        + "p." + DBHelper.COLUMN_CADENA + ", "
                        + "p." + DBHelper.COLUMN_IDPROYECTO + ", "
                        + "v." + DBHelper.COLUMN_ID + ", "
                        + "v." + DBHelper.COLUMN_ABIERTA
                + " FROM  " + DBHelper.TABLE_POP + " AS p"
                    + " LEFT JOIN " + DBHelper.TABLE_VISITA + " AS v ON v." + DBHelper.COLUMN_DETERMINANTE + " = p." + DBHelper.COLUMN_DETERMINANTE
                                + " AND v." + DBHelper.COLUMN_IDPROYECTO + " = p." + DBHelper.COLUMN_IDPROYECTO
                                + " AND DATE(v." + DBHelper.COLUMN_FECHAACTUAL + ") = DATE('" + time + "')"
                                + " AND v." + DBHelper.COLUMN_IDUSUARIO + " = " + proyectos_usuarios.getIdUsuario()
                            + " WHERE p." + DBHelper.COLUMN_IDPROYECTO + " = " + proyectos_usuarios.getIdProyecto()
                                + " AND p." + DBHelper.COLUMN_ACTIVO + " = 1;";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas tiendas = new Entities_Conjuntos_Tiendas();

                tiendas.setDeterminanteGSP(cursor.getInt(0));
                tiendas.setDeterminanteTienda(cursor.getInt(1));
                tiendas.setIdGrupo(cursor.getInt(2));
                tiendas.setSucursal(cursor.getString(3));
                tiendas.setDireccion(cursor.getString(4));
                tiendas.setCP(cursor.getInt(5));
                tiendas.setTelefonos(cursor.getString(6));
                tiendas.setLatitud(cursor.getDouble(7));
                tiendas.setLongitud(cursor.getDouble(8));
                tiendas.setAltitud(cursor.getDouble(9));
                tiendas.setActivo(cursor.getInt(10));
                tiendas.setColonia(cursor.getString(11));
                tiendas.setNuevoPunto(cursor.getInt(12));
                tiendas.setRangoGPS(cursor.getDouble(13));
                tiendas.setCadena(cursor.getString(14));
                tiendas.setIdProyecto(cursor.getInt(15));
                tiendas.setIdVisita(cursor.getInt(16));
                tiendas.setAbierta(cursor.getInt(17));
                collection.add(tiendas);
            } while(cursor.moveToNext());
        }

        return collection;
    }

}
