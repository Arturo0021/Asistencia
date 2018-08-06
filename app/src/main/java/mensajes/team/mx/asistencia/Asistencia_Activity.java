package mensajes.team.mx.asistencia;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.EnvironmentCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ncorti.slidetoact.SlideToActView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas;
import mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios;
import mensajes.team.mx.asistencia.Utilerias.Utils;

public class Asistencia_Activity extends AppCompatActivity implements LocationListener, SwipeRefreshLayout.OnRefreshListener{

    LocationManager locationManager;
    private SwipeRefreshLayout container;
    private android.support.v7.widget.Toolbar mi_toolbarpr;
    TextView txt_location;
    ListView set_list;
    com.getbase.floatingactionbutton.FloatingActionButton float_mensajes;
    com.getbase.floatingactionbutton.FloatingActionButton float_cerrar;
    public static final int SOLICITUD_PERMISO_LOCALIZACION = 0;
    mensajes.team.mx.asistencia.Entities.Collection_Conjuntos_Tiendas conjuntos_tiendas;
    mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios proyectos_usuarios;
    mensajes.team.mx.asistencia.Entities.Entities_Usuarios entitiesUsuarios;
    mensajes.team.mx.asistencia.Entities.Entities_Versiones versiones;
    String time = "";
    Context context;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia_);
        getWindow().setStatusBarColor(getResources().getColor(R.color.guerraro));
        mi_toolbarpr = (android.support.v7.widget.Toolbar)findViewById(R.id.mi_toolbarpr);
        setSupportActionBar(mi_toolbarpr);
        preferences = getSharedPreferences("Preference", Context.MODE_PRIVATE);
        context = this;
        container = (SwipeRefreshLayout)findViewById(R.id.container);
        container.setOnRefreshListener(this);
        ActivityCompat.requestPermissions(Asistencia_Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        txt_location = (TextView) findViewById(R.id.txt_location);
        set_list = (ListView)findViewById(R.id.set_list);
        float_mensajes = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.float_mensajes);
        float_cerrar = (com.getbase.floatingactionbutton.FloatingActionButton)findViewById(R.id.float_cerrar);

        entitiesUsuarios = (mensajes.team.mx.asistencia.Entities.Entities_Usuarios) getIntent().getSerializableExtra("Entities_Usuarios");
        time = getIntent().getStringExtra("time");

        getSupportActionBar().setTitle(entitiesUsuarios.getNombre());
        File save_path = new File(Environment.getExternalStorageDirectory() + "/Apks");
        save_path.mkdirs();
        File privado = new File(String.valueOf(save_path));

        try {

            proyectos_usuarios = mensajes.team.mx.asistencia.Business.Business_Proyectos_Usuarios.get_Proyectos_Usuarios(context, entitiesUsuarios);
            entitiesUsuarios.setProyecto(proyectos_usuarios.getIdProyecto());
            conjuntos_tiendas = mensajes.team.mx.asistencia.Business.Business_Conjuntos_Tiendas.get_Tiendas(context, proyectos_usuarios, time);
            versiones = mensajes.team.mx.asistencia.Business.Business_Versiones.get_Versiones(context, context.getResources().getString(R.string.VersionMysql));

        } catch (Exception e){
            e.getMessage();
        }

        float_mensajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Asistencia_Activity.this, Mensajes_Activity.class);
                intent.putExtra("proyectos_usuarios", proyectos_usuarios);
                startActivity(intent);
            }
        });

        float_cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePreference();
                logout();
            }
        });


        if(versiones.getId() != null) {
            if(Integer.parseInt(getResources().getString(R.string.VersionMysql)) < versiones.getVersion()) {
                download_Apk(context, versiones);
            }
        }

        Criteria criterio = new Criteria();
        criterio.setCostAllowed(false);
        criterio.setAltitudeRequired(false);
        criterio.setAccuracy(Criteria.ACCURACY_FINE);
        locationManager.getBestProvider(criterio, true);

        activarProveedores();

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    conjuntos_tiendas = mensajes.team.mx.asistencia.Business.Business_Conjuntos_Tiendas.get_Tiendas(context, proyectos_usuarios, time);
                    activarProveedores();
                    container.setRefreshing(false);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            conjuntos_tiendas = mensajes.team.mx.asistencia.Business.Business_Conjuntos_Tiendas.get_Tiendas(context, proyectos_usuarios, time);
            activarProveedores();
            ultimaLocalizacion();
        } catch(Exception e) {
            e.getMessage();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ContextCompat.checkSelfPermission(Asistencia_Activity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                 locationManager.removeUpdates(Asistencia_Activity.this);
        }
    }

    private void activarProveedores() {
        if (ContextCompat.checkSelfPermission(Asistencia_Activity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20 * 1000, 5, Asistencia_Activity.this);
            }
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10 * 1000, 10, Asistencia_Activity.this);
            }
        } else {
            ActivityCompat.requestPermissions(Asistencia_Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == SOLICITUD_PERMISO_LOCALIZACION) {
            if (grantResults.length== 1 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ultimaLocalizacion();
                activarProveedores();
            }
        }
    }

    void ultimaLocalizacion(){
        if(ContextCompat.checkSelfPermission(Asistencia_Activity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                actualizaMejorLocalizador(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
            } else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                actualizaMejorLocalizador(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        actualizaMejorLocalizador(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        activarProveedores();
    }

    @Override
    public void onProviderEnabled(String provider) {
        activarProveedores();
    }

    @Override
    public void onProviderDisabled(String provider) {
        activarProveedores();
    }

    private void actualizaMejorLocalizador(Location localiz) {
        final ArrayList<Entities_Conjuntos_Tiendas> collection_Tiendas = new ArrayList<>();
        //txt_location.setText(localiz.getLongitude() + "\n" + localiz.getLatitude());
        for(int i = 0; i < conjuntos_tiendas.size(); i++) {
            mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas tiendaLocalizada = conjuntos_tiendas.get(i);
            Double distancia = meterDistanceBetweenPoints(localiz.getLatitude(),localiz.getLongitude(),tiendaLocalizada.getLatitud(),tiendaLocalizada.getLongitud());
            if(distancia <= 450) {
                if(tiendaLocalizada.getAbierta() != 2) {
                    collection_Tiendas.add(tiendaLocalizada);
                }
            }
        }
        Adapter_Sucursales adapter = new Adapter_Sucursales(context, R.layout.adapter_sucursales, collection_Tiendas, entitiesUsuarios, localiz.getLatitude(),localiz.getLongitude(), time);
        set_list.setAdapter(adapter);
    }

    private double meterDistanceBetweenPoints(double lat_a, double lng_a, double lat_b, double lng_b) {
        float[] result = new float[1];
        Location.distanceBetween(lat_a, lng_a, lat_b, lng_b, result);
        return result[0];
    }

    private void logout() // Permite salir y mantener las credenciales en Login usando SharedPreference
    {
        Intent intent = new Intent(Asistencia_Activity.this, Main_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void removePreference() // Permite Salir de la session y borrar las credenciales del SharedPreference
    {
        preferences.edit().clear().apply();
    }

    public  void download_Apk(final Context context, final mensajes.team.mx.asistencia.Entities.Entities_Versiones versiones){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Boolean existe = mensajes.team.mx.asistencia.Data.Download_Information.Download_Apk(versiones);
                    if(existe) {
                        String fileName = Environment.getExternalStorageDirectory() + "/Apks/"+ versiones.getNombreapk() +".apk";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e){
                    e.getMessage();
                }
            }
        }).start();
    }



}
