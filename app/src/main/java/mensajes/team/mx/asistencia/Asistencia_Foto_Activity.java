package mensajes.team.mx.asistencia;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.health.TimerStat;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;

import mensajes.team.mx.asistencia.Entities.Entities_Fotos;
import mensajes.team.mx.asistencia.Utilerias.Utils;

public class Asistencia_Foto_Activity extends AppCompatActivity {

    mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas tiendas;
    mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios;
    mensajes.team.mx.asistencia.Entities.Entities_Visitas visitas;
    mensajes.team.mx.asistencia.Entities.Collection_Fotos collection_fotos;

    double latitud;
    double longitud;
    String time;
    String bandera;
    String Image;
    private MagicalCamera magicalCamera;
    private int RESIZE_PHOTO_PIXELS_PERCENTAGE = 3000;
    private MagicalPermissions magicalPermissions;
    private android.support.v7.widget.Toolbar mi_toolbar;
    File saveDir;
    File mfile;
    Switch switch_entrada;
    Switch switch_salida;
    private View view;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia__foto_);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        getWindow().setStatusBarColor(getResources().getColor(R.color.guerraro));
        mi_toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.mi_toolbar);
        setSupportActionBar(mi_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        context = this;
        mi_toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        mi_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tiendas = (mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas)getIntent().getSerializableExtra("tienda");
        entities_usuarios = (mensajes.team.mx.asistencia.Entities.Entities_Usuarios)getIntent().getSerializableExtra("usuario");
        time = getIntent().getStringExtra("time");

        latitud = getIntent().getExtras().getDouble("latitud");
        longitud = getIntent().getExtras().getDouble("longitud");

        String[] permissions = new String[]{
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        };

        magicalPermissions = new MagicalPermissions(this, permissions);
        magicalCamera = new MagicalCamera(this, RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

        switch_entrada = (Switch)findViewById(R.id.switch_entrada);
        switch_salida = (Switch)findViewById(R.id.switch_salida);
        switch_salida.setEnabled(false);
        switch_entrada.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    guarda_visita(context,tiendas,entities_usuarios,latitud,longitud); // Guarda Visita
                    get_Visita();
                    bandera = "ENTRADA";
                    take_Photo();
                } else {
                    Toast.makeText(Asistencia_Foto_Activity.this, "Check Entrada OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switch_salida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    actualiza_visita(context, visitas);
                    bandera = "SALIDA";
                    take_Photo();
                } else {
                    Toast.makeText(Asistencia_Foto_Activity.this, "Check Salida OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        get_Visita();
    }

    public void guarda_visita(final Context context, final mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas tiendas, final mensajes.team.mx.asistencia.Entities.Entities_Usuarios entities_usuarios, final double latitud, final double longitud){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    String time = Utils.getFechaServidor();
                    mensajes.team.mx.asistencia.Business.Business_Visitas.Insert_Visita(context, tiendas, entities_usuarios, latitud, longitud, time);

                } catch(Exception e){
                    e.getMessage();
                }
            }
        }).start();
    }

    public void actualiza_visita(final Context context,final mensajes.team.mx.asistencia.Entities.Entities_Visitas visita) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    String time = Utils.getFechaServidor();
                    mensajes.team.mx.asistencia.Business.Business_Visitas.update_visita(context, visita, time);

                } catch(Exception e){
                    e.getMessage();
                }
            }
        }).start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            magicalCamera.resultPhoto(requestCode, resultCode, data);
            Bitmap bit = magicalCamera.getPhoto();
            new save_Photo().execute(bit);
        } else {
            switch (bandera){
                case "ENTRADA":
                    switch_entrada.setChecked(false);
                    break;
                case "SALIDA":
                    switch_salida.setChecked(false);
                    break;
            }
        }


    }

    class save_Photo extends AsyncTask<Bitmap, Void, Void>{

        @Override
        protected Void doInBackground(Bitmap... bitmaps) {

            get_Visita();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmaps[0].compress(Bitmap.CompressFormat.JPEG, 70, stream);
            byte[] byteArray = stream.toByteArray();

            Image = Base64.encodeToString(byteArray, Base64.NO_WRAP);
            mensajes.team.mx.asistencia.Entities.Entities_Fotos fotos = new Entities_Fotos();
            fotos.setIdVisita(visitas.getId());
            fotos.setFoto(Image);
            fotos.setTipo(bandera);

            try {
                mensajes.team.mx.asistencia.Business.Business_Fotos.Insert_Fotos(context, fotos);
                finish();
                startActivity(getIntent());
            } catch (Exception e) {
                e.getMessage();
            }

            return null;
        }
    }

    private void take_Photo() {
        magicalCamera.takePhoto();
    }

    private void get_Visita() {
        try {
            visitas = mensajes.team.mx.asistencia.Business.Business_Visitas.get_Visita(context, tiendas, entities_usuarios, time);
            collection_fotos = mensajes.team.mx.asistencia.Business.Business_Fotos.get_FotosCollection(context, visitas);

            for(mensajes.team.mx.asistencia.Entities.Entities_Fotos entities_fotos: collection_fotos) {
                switch (entities_fotos.getTipo()){
                    case "ENTRADA":
                        switch_entrada.setEnabled(false);
                        switch_salida.setEnabled(true);
                        switch_entrada.setText(getResources().getString(R.string.registrado));
                        break;
                    case "SALIDA":
                        switch_salida.setEnabled(false);
                        switch_salida.setText(getResources().getString(R.string.registrado));
                        break;
                }
            }

        } catch (Exception e) {
            e.getMessage();
        }
    }

}
