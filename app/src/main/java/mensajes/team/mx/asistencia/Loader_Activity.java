package mensajes.team.mx.asistencia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import mensajes.team.mx.asistencia.Business.Business_Usuarios;
import mensajes.team.mx.asistencia.Utilerias.Circle_Image;
import mensajes.team.mx.asistencia.Utilerias.Utils;

public class Loader_Activity extends AppCompatActivity {

    Circle_Image img_guerrero;
    SharedPreferences preferences;
    String usuario;
    String passw;
    String fecha_Servidor;
    Context context;

    mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_);
        context = this;
        preferences = getSharedPreferences("Preference", Context.MODE_PRIVATE);

        img_guerrero = (Circle_Image)findViewById(R.id.img_guerrero);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.transition);
        img_guerrero.startAnimation(anim);
        final Intent intent = new Intent(this, Main_Activity.class);
        final Intent sucursales = new Intent(this, Asistencia_Activity.class);
        final Intent asistencia = new Intent(this, Asistencia_Foto_Activity.class);

         usuario = Utils.getUsuarioPreference(preferences);
         passw = Utils.getPasswordPreference(preferences);

         try {
             entities_usuarios = mensajes.team.mx.asistencia.Business.Business_Usuarios.get_Usuario(context, usuario);
         } catch (Exception e){
             e.getMessage();
         }

        Thread timer = new Thread(){
            public void run () {
                try {
                    fecha_Servidor = Utils.getFechaServidor();
                    sleep(6000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally {

                    if(!TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(passw) && entities_usuarios != null){
                        sucursales.putExtra("Entities_Usuarios", entities_usuarios);
                        sucursales.putExtra("time", fecha_Servidor);
                        startActivity(sucursales);
                        finish();
                    } else {
                        startActivity(intent);
                        finish();
                    }

                }
            }
        };
        timer.start();

    }
}
