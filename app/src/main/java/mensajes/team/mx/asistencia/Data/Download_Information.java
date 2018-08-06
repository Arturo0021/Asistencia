package mensajes.team.mx.asistencia.Data;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import mensajes.team.mx.asistencia.Entities.Collection_Conjuntos_Tiendas;
import mensajes.team.mx.asistencia.Entities.Collection_Versiones;
import mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas;
import mensajes.team.mx.asistencia.Entities.Entities_Mensajes;
import mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios;
import mensajes.team.mx.asistencia.Entities.Entities_Versiones;
import mensajes.team.mx.asistencia.R;
import mensajes.team.mx.asistencia.Utilerias.Utils;

public class Download_Information {

    private static String METHOD_NAME = "";
    private static StringEntity strEntity = null;
    private static JSONObject jsonResult = null;
    private static JSONArray jsonArray = null;
    private static JSONStringer jsonString = null;
    private static String strResult = null;

    public static void download_Proyectos(Context context, mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios) throws JSONException, IOException, Exception {

        METHOD_NAME = "/getproyectosUsuario";
        Wcf_Service service = new Wcf_Service();
        mensajes.team.mx.asistencia.Entities.Entities_Proyectos entities_proyectos = null;

        jsonString = new JSONStringer()
                            .object()
                                .key("Usuario")
                                .object()
                                    .key("Id").value(entities_usuarios.getId())
                                    .key("IMEI").value(entities_usuarios.getIMEI())
                                    .key("Sim").value(entities_usuarios.getSim())
                                    .key("Telefono").value(entities_usuarios.getTelefono())
                                .endObject()
                            .endObject();

        strEntity = new StringEntity(jsonString.toString());
        jsonResult = service.HttpPost(METHOD_NAME, strEntity);
        jsonArray = jsonResult.getJSONArray("getproyectosUsuarioResult");

        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            entities_proyectos = new mensajes.team.mx.asistencia.Entities.Entities_Proyectos();

            entities_proyectos.setId(jsonObject.getInt("Id"));
            entities_proyectos.setNombre(jsonObject.getString("Nombre"));
            // Enviar a Data_Proyectos para insertar
            mensajes.team.mx.asistencia.Data.Data_Proyectos.Insert_Proyecto(context, entities_proyectos);
        }

        mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios pu = new Entities_Proyectos_Usuarios();
        pu.setIdUsuario(entities_usuarios.getId());
        pu.setIdProyecto(entities_proyectos.getId());

        mensajes.team.mx.asistencia.Data.Data_Proyectos_Usuarios.Insert_Proyectos_Usuarios(context, entities_usuarios, entities_proyectos);
        mensajes.team.mx.asistencia.Data.Download_Information.Download_Conjuntos_Tiendas(context, entities_usuarios, entities_proyectos);
        mensajes.team.mx.asistencia.Data.Download_Information.Download_Mensajes(context, pu);
        mensajes.team.mx.asistencia.Data.Download_Information.Download_Versiones(context);

    }

    public static String get_fecha_Server() throws JSONException, IOException, Exception {
        METHOD_NAME = "/GetFechaActual";
        Wcf_Service service = new Wcf_Service();
        jsonResult = service.HttpPost(METHOD_NAME, null);
        strResult = jsonResult.getString("GetFechaActualResult");
        return strResult;
    }

    public static void Download_Conjuntos_Tiendas(Context context, mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios, mensajes.team.mx.asistencia.Entities.Entities_Proyectos entities_proyectos)  throws UnsupportedEncodingException, IOException{

        METHOD_NAME = "/getConjuntotiendasUsuario";
        Wcf_Service service = new Wcf_Service();
        mensajes.team.mx.asistencia.Entities.Collection_Conjuntos_Tiendas conjuntos = new Collection_Conjuntos_Tiendas();

        try {

            jsonString = new JSONStringer()
                    .object()
                        .key("Proyecto")
                        .object()
                            .key("Id").value(entities_proyectos.getId())
                            .key("Ufechadescarga").value("0")
                        .endObject()
                        .key("Usuario")
                        .object()
                            .key("Id").value(entities_usuarios.getId())
                        .endObject()
                    .endObject();
            strEntity = new StringEntity(jsonString.toString());
            jsonResult = service.HttpPost(METHOD_NAME, strEntity);
            jsonArray = jsonResult.getJSONArray("getConjuntotiendasUsuarioResult");

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas pop = new Entities_Conjuntos_Tiendas();
                pop.setId(jsonObject.getInt("Id"));
                pop.setActivo(jsonObject.getInt("Activo"));
                pop.setAltitud(jsonObject.getDouble("Altitud"));
                pop.setCP(jsonObject.getInt("CP"));
                pop.setCalle(jsonObject.getString("Calle"));
                pop.setColonia(jsonObject.getString("Colonia"));
                pop.setLatitud(jsonObject.getDouble("Latitud"));
                pop.setLongitud(jsonObject.getDouble("Longitud"));
                pop.setDeterminanteGSP(jsonObject.getInt("DeterminanteGSP"));
                pop.setDireccion(jsonObject.getString("Direccion"));
                pop.setIdCadena(jsonObject.getInt("IdCadena"));
                pop.setIdCanal(jsonObject.getInt("IdCanal"));
                pop.setIdGrupo(jsonObject.getInt("IdGrupo"));
                pop.setSucursal(jsonObject.getString("Sucursal"));
                pop.setCadena(jsonObject.getString("Cadena"));
                pop.setIdProyecto(entities_proyectos.getId());
                pop.setStatusSync(jsonObject.getInt("Statussync"));
                pop.setFechaSync(jsonObject.getString("FechaSync"));

                conjuntos.add(pop);
                mensajes.team.mx.asistencia.Data.Data_Conjuntos_Tiendas.Insert_Conjuntos_Tiendas(context,pop);
            }

        } catch(Exception e) {
            e.getMessage();
        }

    }

    public static void Download_Mensajes(Context context, mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios pu) throws Exception {

        mensajes.team.mx.asistencia.Entities.Entities_Mensajes mensaje = new Entities_Mensajes();
        Wcf_Service service = new Wcf_Service();

        METHOD_NAME = "/GetMensajesByUsuario";

        jsonString = new JSONStringer()
                .object()
                    .key("Proyecto")
                    .object()
                         .key("Id").value(pu.getIdProyecto())
                         .key("Ufechadescarga").value(Utils.getFecha_x())
                    .endObject()
                    .key("Usuario")
                    .object()
                        .key("Id").value(pu.getIdUsuario())
                    .endObject()
                .endObject();

        strEntity = new StringEntity(jsonString.toString());
        jsonResult = service.HttpPost(METHOD_NAME, strEntity);
        jsonArray = jsonResult.getJSONArray("GetMensajesByUsuarioResult");

        for(int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            mensaje.setId(jsonObject.getInt("Id"));
            mensaje.setTipo(jsonObject.getString("Tipo"));
            mensaje.setCuerpo(jsonObject.getString("Cuerpo"));
            mensaje.setCapturaFecha(jsonObject.getString("CapturaFecha"));
            mensaje.setFechaFin(jsonObject.getString("FechaFin"));
            mensaje.setFechaEnvio(jsonObject.getString("FechaEnvio"));
            mensaje.setIdProyecto(jsonObject.getInt("IdProyecto"));
            mensaje.setStatusSync(jsonObject.getInt("Statussync"));
            mensaje.setFechaSync(jsonObject.getString("FechaSync"));
            mensaje.setActivo(jsonObject.getInt("Activo"));

            mensajes.team.mx.asistencia.Data.Data_Mensajes.Insert_Mensajes(context, mensaje);

        }

    }

    public static void Download_Versiones(Context context) throws Exception{

        METHOD_NAME = "/GetVersionAPK";
        Wcf_Service service = new Wcf_Service();
        mensajes.team.mx.asistencia.Entities.Collection_Versiones version = new Collection_Versiones();

        jsonString = new JSONStringer().object()
                .key("version")
                .object()
                    .key("idAplicacion").value(context.getResources().getString(R.string.IdVersion))
                .endObject()
                .endObject();

        strEntity = new StringEntity(jsonString.toString());
        jsonResult = service.HttpPost(METHOD_NAME, strEntity);
        jsonArray = jsonResult.getJSONArray("GetVersionAPKResult");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            mensajes.team.mx.asistencia.Entities.Entities_Versiones versiones = new Entities_Versiones();
            versiones.setId(jsonObject.getInt("id"));
            versiones.setVersion(jsonObject.getInt("Version"));
            versiones.setNombreapk(jsonObject.getString("Nombre"));
            versiones.setUrl(jsonObject.getString("Url"));
            mensajes.team.mx.asistencia.Data.Data_Versiones.Insert_Versiones(context, versiones);
            version.add(versiones);
        }
    }

    public static Boolean Download_Apk(mensajes.team.mx.asistencia.Entities.Entities_Versiones versiones) {

        Boolean Existe_Nuevo_APK = false;

                try {
                   // Looper.prepare();
                    String rutapath = Environment.getExternalStorageDirectory() + "/Apks/" + versiones.getNombreapk() + ".apk";
                    File file = new File(rutapath);
                    if(file.exists()){
                        Existe_Nuevo_APK = true;
                    } else {
                        URL u = new URL("http://www.webteam.mx/Apps/" + versiones.getNombreapk() + ".apk");
                        InputStream is = u.openStream();
                        DataInputStream dis = new DataInputStream(is);
                        byte[] buffer = new byte[1024];
                        int length;
                        FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/Apks/" + versiones.getNombreapk() + ".apk"));
                        while ((length = dis.read(buffer))>0) {
                            fos.write(buffer, 0, length);
                        }
                    }
                    //Looper.loop();
                } catch (Exception ex) {
                    ex.getMessage();
                }

        return Existe_Nuevo_APK;
    }

}
