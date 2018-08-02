package mensajes.team.mx.asistencia.Data;

import android.content.Context;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import mensajes.team.mx.asistencia.Entities.Collection_Conjuntos_Tiendas;
import mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas;

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

        mensajes.team.mx.asistencia.Data.Data_Proyectos_Usuarios.Insert_Proyectos_Usuarios(context, entities_usuarios, entities_proyectos);
        mensajes.team.mx.asistencia.Data.Download_Information.Download_Conjuntos_Tiendas(context, entities_usuarios, entities_proyectos);

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

}
