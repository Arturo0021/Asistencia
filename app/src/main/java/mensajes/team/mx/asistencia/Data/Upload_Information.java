package mensajes.team.mx.asistencia.Data;

import android.content.Context;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Upload_Information {

    private static String METHOD_NAME = "";
    private static StringEntity strEntity = null;
    private static JSONObject jsonResult = null;
    private static String strResult = null;
    private static JSONArray jsonArray = null;
    private static JSONStringer jsonString = null;

    public static void upload_visita(mensajes.team.mx.asistencia.Entities.Entities_Visitas visita) throws Exception {

        Wcf_Service service = new Wcf_Service();
        String fecha = "";
        METHOD_NAME = "/VisitasInsert";

        if(visita.getFechaCierre() == null){
            fecha = visita.getFechaEntrada();
        } else {
          fecha = visita.getFechaCierre();
        }

        jsonString = new JSONStringer()
                        .object()
                            .key("Usuario")
                            .object()
                                .key("Id").value(visita.getIdUsuario())
                            .endObject()
                            .key("Proyecto")
                            .object()
                                .key("Id").value(visita.getIdProyecto())
                            .endObject()
                            .key("Tienda")
                            .object()
                                .key("DeterminanteGSP").value(visita.getDeterminanteGSP())
                                .key("CadenaFecha").value(visita.getFechaEntrada())
                                .key("Latitud").value(visita.getLatitud())
                                .key("Longitud").value(visita.getLongitud())
                                .key("Estatus").value(visita.getAbierta())
                            .endObject()
                            .key("Visitas")
                            .object()
                                .key("FechaCierre").value(fecha)
                            .endObject()
                        .endObject();

        strEntity = new StringEntity(jsonString.toString(),"UTF-8");
        jsonResult = service.HttpPost(METHOD_NAME, strEntity);


        if (!jsonResult.getString("VisitasInsertResult").equals("OK")) {
            throw new Exception("Error el el servicio [VisitaInsert]");
        }
    }

    public static void Foto_Entrada(Context context, mensajes.team.mx.asistencia.Entities.Entities_Visitas visita,mensajes.team.mx.asistencia.Entities.Entities_Fotos foto) throws Exception {

        Wcf_Service service = new Wcf_Service();

        try {
            METHOD_NAME = "/AsistenciaEntradaFotoInsert";
                jsonString = new JSONStringer()
                        .object()
                            .key("Usuario")
                            .object()
                                 .key("Id").value(visita.getIdUsuario())
                            .endObject()
                            .key("Proyecto")
                            .object()
                                .key("Id").value(visita.getIdProyecto())
                            .endObject()
                            .key("Tienda")
                            .object()
                                .key("DeterminanteGSP").value(visita.getDeterminanteGSP())
                            .endObject()
                            .key("Foto")
                            .object()
                                .key("FotoBase64").value(foto.getFoto())
                                .key("CadenaFecha").value(visita.getFechaEntrada())
                            .endObject()
                        .endObject();

            strEntity = new StringEntity(jsonString.toString(), "UTF-8");
            jsonResult = service.HttpPost(METHOD_NAME, strEntity);

            if (!jsonResult.getString("AsistenciaEntradaFotoInsertResult").equals("OK")) {
                throw new Exception("Error el el servicio [AsistenciaEntradaFotoInsert]");
            } else {
                mensajes.team.mx.asistencia.Business.Business_Fotos.update_status_foto(context, foto);
            }

        } catch (Exception e) {

        }
    }

    public static void Foto_Salida(Context context, mensajes.team.mx.asistencia.Entities.Entities_Visitas visita, mensajes.team.mx.asistencia.Entities.Entities_Fotos foto) throws Exception{
        Wcf_Service service = new Wcf_Service();

        METHOD_NAME = "/AsistenciaSalidaFotoInsert";
        jsonString = new JSONStringer()
                            .object()
                                .key("Usuario")
                                .object()
                                    .key("Id").value(visita.getIdUsuario())
                                .endObject()
                                .key("Proyecto")
                                    .object().key("Id").value(visita.getIdProyecto())
                                .endObject()
                                .key("Tienda")
                                .object()
                                    .key("DeterminanteGSP").value(visita.getDeterminanteGSP())
                                .endObject()
                                .key("Foto")
                                .object()
                                    .key("FotoBase64").value(foto.getFoto())
                                    .key("CadenaFecha").value(visita.getFechaSalida())
                                .endObject()
                            .endObject();

        strEntity = new StringEntity(jsonString.toString(), "UTF-8");
        jsonResult = service.HttpPost(METHOD_NAME, strEntity);

        if (!jsonResult.getString("AsistenciaSalidaFotoInsertResult").equals("OK")) {
            throw new Exception("Error el el servicio [AsistenciaSalidaFotoInsert]");
        } else {
            mensajes.team.mx.asistencia.Business.Business_Fotos.update_status_foto(context, foto);
        }

    }

}
