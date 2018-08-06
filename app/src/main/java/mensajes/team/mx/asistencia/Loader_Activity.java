package mensajes.team.mx.asistencia;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.io.File;

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

    mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios = null;
    mensajes.team.mx.asistencia.Entities.Entities_Visitas get_Visita_Abierta = null;
    mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas get_Tiendas_visitada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader_);
        context = this;
        preferences = getSharedPreferences("Preference", Context.MODE_PRIVATE);
        ActivityCompat.requestPermissions(Loader_Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        img_guerrero = (Circle_Image)findViewById(R.id.img_guerrero);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.transition);
        img_guerrero.startAnimation(anim);
        final Intent intent = new Intent(this, Main_Activity.class);
        final Intent sucursales = new Intent(this, Asistencia_Activity.class);
        final Intent asistencia = new Intent(this, Asistencia_Foto_Activity.class);

         usuario = Utils.getUsuarioPreference(preferences);
         passw = Utils.getPasswordPreference(preferences);

        Thread timer = new Thread(){
            public void run () {
                try {
                    fecha_Servidor = Utils.getFechaServidor();
                    if(!TextUtils.isEmpty(usuario)) {
                        get_information();
                    }
                    sleep(6000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {

                    if(get_Visita_Abierta != null) {
                        if(get_Visita_Abierta.getAbierta() == 1) {
                            asistencia.putExtra("tienda", get_Tiendas_visitada);
                            asistencia.putExtra("usuario", entities_usuarios);
                            asistencia.putExtra("latitud", get_Visita_Abierta.getLatitud());
                            asistencia.putExtra("longitud", get_Visita_Abierta.getLongitud());
                            asistencia.putExtra("time", fecha_Servidor);
                            startActivity(asistencia);
                            finish();
                        }else if(!TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(passw) && entities_usuarios != null){
                            sucursales.putExtra("Entities_Usuarios", entities_usuarios);
                            sucursales.putExtra("time", fecha_Servidor);
                            startActivity(sucursales);
                            finish();
                        } else {
                            startActivity(intent);
                            finish();
                        }
                    } else if(!TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(passw) && entities_usuarios != null){
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

    public void get_information(){
                try {
                    download_version();
                    entities_usuarios = mensajes.team.mx.asistencia.Business.Business_Usuarios.get_Usuario(context, usuario);
                    get_Visita_Abierta =  mensajes.team.mx.asistencia.Business.Business_Visitas.get_Visita_Abierta(context, entities_usuarios, fecha_Servidor);
                    if(get_Visita_Abierta != null) {
                        get_Tiendas_visitada = mensajes.team.mx.asistencia.Business.Business_Conjuntos_Tiendas.get_Tiendas_visitada(context, get_Visita_Abierta, entities_usuarios, fecha_Servidor);
                    }
                } catch (Exception e){
                    e.getMessage();
                }
    }

    private void download_version() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mensajes.team.mx.asistencia.Data.Download_Information.Download_Versiones(context);
                } catch (Exception e){
                    e.getMessage();
                }
            }
        }).start();
    }

}
