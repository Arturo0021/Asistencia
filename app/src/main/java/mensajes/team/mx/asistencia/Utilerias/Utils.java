package mensajes.team.mx.asistencia.Utilerias;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.frosquivel.magicalcamera.MagicalCamera;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    public static String fecha_Servidor = "";
    public static String fecha = "";

    public static String getUsuarioPreference(SharedPreferences sharedPreferences) // Envia Usuario
    {
        return sharedPreferences.getString("usuario", null);
    }

    public static String getPasswordPreference(SharedPreferences sharedPreferences) // Envia Contraseña
    {
        return sharedPreferences.getString("password", null);
    }

    // Recordar que este metodo funciona siempre y cuando sea llamado dentro de un hilo. (Thread, AsynckTask)
    public static String getFechaServidor() {
        try{
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            fecha_Servidor = mensajes.team.mx.asistencia.Business.Download_Information.get_fecha_Server();
            Date date = formateador.parse(fecha_Servidor);
            fecha = parseador.format(date);
            if(fecha.equals("")){
                fecha = getFecha_x();
            }
        }catch (Exception e){
            e.getMessage();
        }
        return fecha;
    }

    public static String getFecha_x() throws ParseException {    // Envia la Fecha del Movil <--- Error... tratar de no usar la hora del movil, la gente es ingeniosa.
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getDefault());
        String time = dateFormatGmt.format(new Date());
       return time;
    }

}
