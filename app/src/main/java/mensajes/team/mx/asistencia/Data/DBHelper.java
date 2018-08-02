package mensajes.team.mx.asistencia.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    public final Context context;
    private static final String DATABASE_NAME = "bd_asistencia";
    public static final int DATABASE_VERSION = 1;


    // Tablas
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String TABLE_PROYECTOSUSUARIOS = "ProyectosUsuarios";
    public static final String TABLE_PROYECTOS = "Proyectos";
    public static final String TABLE_RUTAS = "Rutas";
    public static final String TABLE_REGSINCRO = "Regsincro";
    public static final String TABLE_GPS = "SeguimientoGPS";
    public static final String TABLE_POP = "Pop";
    public static final String TABLE_PHOTO = "Foto";
    public static final String TABLE_VISITA = "Visita";
    public static final String TABLE_MENSAJES = "MensajesMovil";
    public static final String TABLE_VERSIONES = "Versiones";
    public static final String TABLE_IMEI = "Imei";

    // Columnas
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IDUSUARIO = "IdUsuario";
    public static final String COLUMN_IDPROYECTO = "IdProyecto";
    public static final String COLUMN_USUARIO = "Usuario";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_NOMBRE = "Nombre";
    public static final String COLUMN_DETERMINANTE = "DeterminanteGSP";
    public static final String COLUMN_DIA = "Dia";
    public static final String COLUMN_ORDEN = "Orden";
    public static final String COLUMN_FECHA = "Fecha";
    public static final String COLUMN_STATUSSYNC = "StatusSync";
    public static final String COLUMN_FECHASSYNC = "FechaSync";
    public static final String COLUMN_ACTIVO = "Activo";
    public static final String COLUMN_FECHAACTUAL = "Fechaactual";
    public static final String COLUMN_FECHACREA = "FechaCrea";
    public static final String COLUMN_UPLOAD = "Upload";
    public static final String COLUMN_DOWNLOAD = "Download";
    public static final String COLUMN_LATITUD = "Latitud";
    public static final String COLUMN_LONGITUD = "Longitud";
    public static final String COLUMN_DETERMINANTETIENDA = "DeterminanteTienda";
    public static final String COLUMN_FACTURACION = "Facturacion";
    public static final String COLUMN_IDCANAL = "IdCanal";
    public static final String COLUMN_IDGRUPO = "IdGrupo";
    public static final String COLUMN_IDCADENA = "IdCadena";
    public static final String COLUMN_IDFORMATO = "IdFormato";
    public static final String COLUMN_SUCURSAL = "Sucursal";
    public static final String COLUMN_DIRECCION = "Direccion";
    public static final String COLUMN_CP = "CP";
    public static final String COLUMN_IDPAIS = "IdPais";
    public static final String COLUMN_IDESTADO = "IdEstado";
    public static final String COLUMN_IDMUNICIPIO = "IdMunicipio";
    public static final String COLUMN_IDCIUDAD = "IdCiudad";
    public static final String COLUMN_TELEFONO = "Telefonos";
    public static final String COLUMN_ALTITUD = "Altitud";
    public static final String COLUMN_CALLE = "Calle";
    public static final String COLUMN_NUMERO = "Numero";
    public static final String COLUMN_COLONIA = "Colonia";
    public static final String COLUMN_NUEVOPUNTO = "NuevoPunto";
    public static final String COLUMN_RANGOGPS = "RangoGPS";
    public static final String COLUMN_NIELSEN = "Nielsen";
    public static final String COLUMN_CADENA = "Cadena";
    public static final String COLUMN_IDVISITA = "IdVisita";
    public static final String COLUMN_FOTO = "Foto";
    public static final String COLUMN_SIM = "Sim";
    public static final String COLUMN_TIPO = "Tipo";
    public static final String COLUMN_COMENTARIO = "Comentario";
    public static final String COLUMN_CATEGORIA = "Categoria";
    public static final String COLUMN_ABIERTA = "Abierta";
    public static final String COLUMN_FECHAENTRADA = "FechaEntrada";
    public static final String COLUMN_FECHASALIDA = "FechaSalida";
    public static final String COLUMN_FECHACIERRE = "FechaCierre";
    public static final String COLUMN_TIPOUBICACION = "tipoUbicacion";
    public static final String COLUMN_IMEI = "Imei";
    public static final String COLUMN_CUERPO = "Cuerpo";
    public static final String COLUMN_CAPTURAFECHA = "CapturaFecha";
    public static final String COLUMN_FECHAFIN = "FechaFin";
    public static final String COLUMN_FECHAENVIO = "FechaEnvio";
    public static final String COLUMN_VERSION = "Version";
    public static final String COLUMN_URL = "Url";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TablaProyectos(db);
        TablaProyectoUsuarios(db);
        TablaUsuarios(db);
        TablaRutas(db);
        TablaRegSincro(db);
        TablaGps(db);
        TablePop(db);
        TablaVisita(db);
        TablaFoto(db);
        TableVersiones(db);
        TablaMensajes(db);
        TablaImei(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PROYECTOS + "; ");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PROYECTOSUSUARIOS + "; ");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USUARIOS + "; ");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_RUTAS + "; ");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_REGSINCRO + "; ");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_GPS + "; ");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_POP + "; ");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_VISITA + "; ");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PHOTO + "; ");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_MENSAJES + "; ");
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_IMEI + "; ");

    }

    public void TablaUsuarios(SQLiteDatabase db){   // Tabla de Usuarios
        String Table_Usuarios =
                "CREATE TABLE IF NOT EXISTS " + TABLE_USUARIOS
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_USUARIO + " VARCHAR, "
                        + COLUMN_PASSWORD + " VARCHAR, "
                        + COLUMN_NOMBRE + " VARCHAR "
                        + ");";
        db.execSQL(Table_Usuarios);
    }

    public void TablaProyectos(SQLiteDatabase db){

        String TableProyectos =
                "CREATE TABLE IF NOT EXISTS " + TABLE_PROYECTOS
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_NOMBRE + " VARCHAR "
                        + ");";
        db.execSQL(TableProyectos);

    }

    public void TablaProyectoUsuarios(SQLiteDatabase db){
        String TableProyectosUsuarios =
                "CREATE TABLE IF NOT EXISTS " + TABLE_PROYECTOSUSUARIOS
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_IDUSUARIO + " INTEGER, "
                        + COLUMN_IDPROYECTO + " INTEGER "
                        + ");";
        db.execSQL(TableProyectosUsuarios);
    }

    public void TablaRutas(SQLiteDatabase db)
    {

        String TableRutas =
                "CREATE TABLE IF NOT EXISTS " + TABLE_RUTAS
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_IDPROYECTO + " INTEGER, "
                        + COLUMN_IDUSUARIO + " INTEGER, "
                        + COLUMN_DETERMINANTE + " INTEGER, "
                        + COLUMN_DIA + " INTEGER, "
                        + COLUMN_ORDEN + " INTEGER, "
                        + COLUMN_FECHA + " NUMERIC, "
                        + COLUMN_STATUSSYNC + " INTEGER, "
                        + COLUMN_FECHASSYNC + " NUMERIC, "
                        + COLUMN_ACTIVO + " INTEGER"
                        + ");";
        db.execSQL(TableRutas);
    }

    public void TablaRegSincro(SQLiteDatabase db)
    {

        String TableRegSincro =
                "CREATE TABLE IF NOT EXISTS " + TABLE_REGSINCRO
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_FECHAACTUAL + " TIMESTAMP NOT NULL DEFAULT current_timestamp, "
                        + COLUMN_UPLOAD + " NUMERIC, "
                        + COLUMN_DOWNLOAD + " NUMERIC, "
                        + COLUMN_IDPROYECTO + " INTEGER, "
                        + COLUMN_IDUSUARIO + " INTEGER "
                        + ");";
        db.execSQL(TableRegSincro);
    }

    public void TablaGps(SQLiteDatabase db)
    {

        String TableGps =
                "CREATE TABLE IF NOT EXISTS " + TABLE_GPS
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_IDUSUARIO + " INTEGER, "
                        + COLUMN_IDPROYECTO + " INTEGER, "
                        + COLUMN_FECHA + " NUMERIC, "
                        + COLUMN_LATITUD + " DOUBLE, "
                        + COLUMN_LONGITUD + " DOUBLE, "
                        + COLUMN_STATUSSYNC + " INTEGER, "
                        + COLUMN_FECHASSYNC + " NUMERIC, "
                        + COLUMN_DETERMINANTE + " INTEGER"
                        + ");";

        db.execSQL(TableGps);
    }

    public void TablePop(SQLiteDatabase db)
    {
        String TablePop =
                "CREATE TABLE IF NOT EXISTS " + TABLE_POP
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_DETERMINANTE + " INTEGER, "
                        + COLUMN_DETERMINANTETIENDA + " INTEGER, "
                        + COLUMN_FACTURACION + " INTEGER, "
                        + COLUMN_IDCANAL + " INTEGER, "
                        + COLUMN_IDGRUPO + " INTEGER, "
                        + COLUMN_IDCADENA + " INTEGER, "
                        + COLUMN_IDFORMATO + " INTEGER, "
                        + COLUMN_SUCURSAL + " VARCHAR, "
                        + COLUMN_DIRECCION + " TEXT, "
                        + COLUMN_CP + " INTEGER, "
                        + COLUMN_IDPAIS + " INTEGER, "
                        + COLUMN_IDESTADO + " INTEGER, "
                        + COLUMN_IDMUNICIPIO + " INTEGER, "
                        + COLUMN_IDCIUDAD + " INTEGER, "
                        + COLUMN_TELEFONO + " TEXT, "
                        + COLUMN_LATITUD + " DOUBLE, "
                        + COLUMN_LONGITUD + " DOUBLE, "
                        + COLUMN_ALTITUD + " INTEGER, "
                        + COLUMN_ACTIVO + " INTEGER, "
                        + COLUMN_CALLE + " VARCHAR, "
                        + COLUMN_NUMERO + " VARCHAR, "
                        + COLUMN_COLONIA + " VARCHAR, "
                        + COLUMN_NUEVOPUNTO + " INTEGER, "
                        + COLUMN_RANGOGPS + " DOUBLE, "
                        + COLUMN_NIELSEN + " VARCHAR, "
                        + COLUMN_CADENA + " VARCHAR, "
                        + COLUMN_IDPROYECTO + " INTEGER, "
                        + COLUMN_STATUSSYNC + " INTEGER, "
                        + COLUMN_FECHASSYNC + " VARCHAR"
                        + ");";
        db.execSQL(TablePop);
    }

    public void TablaFoto(SQLiteDatabase db){

        String TablePhoto =
                "CREATE TABLE IF NOT EXISTS " + TABLE_PHOTO
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_IDVISITA + " INTEGER, "
                        + COLUMN_FOTO + " BLOB, "
                        + COLUMN_TIPO + " VARCHAR, "
                        + COLUMN_COMENTARIO + " VARCHAR, "
                        + COLUMN_FECHA + " INTEGER DEFAULT(strftime('%s','now')), "
                        + COLUMN_STATUSSYNC + " INTEGER, "
                        + COLUMN_FECHASSYNC + " INTEGER, "
                        + COLUMN_CATEGORIA + " INTEGER"
                        + ");";
        db.execSQL(TablePhoto);

    }

    public void TablaVisita(SQLiteDatabase db){

        String TableVisita =
                "CREATE TABLE IF NOT EXISTS " + TABLE_VISITA
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_IDPROYECTO + " INTEGER, "
                        + COLUMN_DETERMINANTE + " INTEGER, "
                        + COLUMN_IDUSUARIO + " INTEGER, "
                        + COLUMN_LATITUD + " DOUBLE, "
                        + COLUMN_LONGITUD + " DOUBLE, "
                        + COLUMN_ABIERTA + " INTEGER DEFAULT 1, "
                        + COLUMN_FECHAENTRADA + " INTEGER, "
                        + COLUMN_FECHASALIDA + " INTEGER, "
                        + COLUMN_FECHAACTUAL + " INTEGER, "
                        + COLUMN_FECHASSYNC + " INTEGER, "
                        + COLUMN_FECHACIERRE + " INTEGER, "
                        + COLUMN_TIPOUBICACION + " VARCHAR"
                        + ");";
        db.execSQL(TableVisita);
    }

    public void TablaMensajes(SQLiteDatabase db)
    {
        String TableMensajes =
                "CREATE TABLE IF NOT EXISTS " + TABLE_MENSAJES
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_TIPO + " VARCHAR, "
                        + COLUMN_CUERPO + " VARCHAR, "
                        + COLUMN_CAPTURAFECHA + " NUMERIC, "
                        + COLUMN_FECHAFIN + " VARCHAR, "
                        + COLUMN_FECHAENVIO + " VARCHAR, "
                        + COLUMN_IDPROYECTO + " INTEGER, "
                        + COLUMN_STATUSSYNC + " INTEGER, "
                        + COLUMN_FECHASSYNC + " NUMERIC, "
                        + COLUMN_ACTIVO + " INTEGER "
                        + ");";
        db.execSQL(TableMensajes);
    }

    protected void TableVersiones(SQLiteDatabase db) {
        String TableVersiones =
                "CREATE TABLE IF NOT EXISTS " + TABLE_VERSIONES
                    + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NOMBRE + " VARCHAR, "
                    + COLUMN_VERSION + " INTEGER, "
                    + COLUMN_URL + " VARCHAR"
                    + ");";
        db.execSQL(TableVersiones);
    }

    public void TablaImei(SQLiteDatabase db) {
        String Table_Imei =
                "CREATE TABLE IF NOT EXISTS " + TABLE_IMEI
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_IMEI + " VARCHAR, "
                        + COLUMN_SIM + " VARCHAR, "
                        + COLUMN_TELEFONO + " VARCHAR, "
                        + COLUMN_FECHACREA + " INTEGER DEFAULT(strftime('%s','now')), "
                        + COLUMN_IDUSUARIO + " INTEGER"
                        + ");";
        db.execSQL(Table_Imei);
    }

}
