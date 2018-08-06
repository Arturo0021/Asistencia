package mensajes.team.mx.asistencia;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.redbooth.WelcomeCoordinatorLayout;

import mensajes.team.mx.asistencia.Business.Business_Usuarios;
import mensajes.team.mx.asistencia.Entities.Entities_Usuarios;
import mensajes.team.mx.asistencia.Utilerias.Utils;

public class Main_Activity extends AppCompatActivity {

    Context context; View view;
    EditText input_usuario; EditText input_password;
    AppCompatButton btn_login;
    TextView link_signup; TextView link_version;
    SharedPreferences preferences;
    String fecha_Servidor = "";
    WelcomeCoordinatorLayout cordinator_layout;

    mensajes.team.mx.asistencia.Entities.Entities_Usuarios entitiesUsuarios;
    mensajes.team.mx.asistencia.Entities.Entities_Information_Celphone entities_cel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);
        context = this;
        preferences = getSharedPreferences("Preference", Context.MODE_PRIVATE);
        ActivityCompat.requestPermissions(Main_Activity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);

        input_usuario = (EditText)findViewById(R.id.input_usuario);
        input_password = (EditText)findViewById(R.id.input_password);
        btn_login = (AppCompatButton)findViewById(R.id.btn_login);
        link_signup = (TextView)findViewById(R.id.link_signup);
        link_version = (TextView)findViewById(R.id.link_version);

        link_version.setText(R.string.VersionAPK);
        dialog_infoGrafia();

        setCredencialesIfExists(); // Valida si existe un usuario guardado en SharedPreference

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(input_usuario.getText().toString().equals("")) {
                    link_signup.setText(R.string.us_vacio);
                } else if(!input_usuario.getText().toString().matches(getString(R.string.dif_caracteres))) {
                    link_signup.setText(R.string.caract_no_perm);
                } else if(input_password.getText().toString().equals("")) {
                    link_signup.setText(R.string.contr_vacia);
                } else {
                    link_signup.setText("");

                    if(validateNetwork()){
                        // Entra al metodo para validar usuario
                        new Thread_Valida_Usuario().execute();
                    }

                }

            }
        });

    }
        // Valida si Existe El Usuario
    class Thread_Valida_Usuario extends AsyncTask<Void, Void, mensajes.team.mx.asistencia.Entities.Entities_Usuarios> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {

            dialog = new ProgressDialog(Main_Activity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(getString(R.string.waiting));
            dialog.setCancelable(false);
            dialog.show();

        }

        @Override
        protected mensajes.team.mx.asistencia.Entities.Entities_Usuarios doInBackground(Void... voids) {

            try {

                fecha_Servidor = Utils.getFechaServidor();
                // Descarga Usuarios
                entitiesUsuarios = Business_Usuarios.DownloadUsuario(input_usuario.getText().toString(), input_password.getText().toString());
                // Descarga Proyectos
                mensajes.team.mx.asistencia.Business.Download_Information.download_Proyectos(context, entitiesUsuarios);

            } catch (Exception e) {
                e.getMessage();
            }

            return entitiesUsuarios;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @SuppressLint("MissingPermission")
        @Override
        protected void onPostExecute(Entities_Usuarios entities_usuarios) {

            if(entities_usuarios == null) {
                link_signup.setText(R.string.invalid_user);
                removePreference();
            } else {
                link_signup.setText("");
                savePreference(input_usuario.getText().toString(), input_password.getText().toString()); // Se Guardan las Credenciales en el SharedPreference

                try {
                    entities_cel = mensajes.team.mx.asistencia.Business.Business_Information_Celphone.get_Information(context, entities_usuarios);

                    if(entities_cel == null) {
                        alert_Dialog();
                    } else if(entities_cel.getTelefono() == null || entities_cel.getTelefono().equalsIgnoreCase("")) {
                        alert_Dialog();
                    } else {
                        // Hacer algo cuando ya exista la informacion del celular

                    }
                        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                        entities_usuarios.setIMEI(telephonyManager.getImei());
                        entities_usuarios.setSim(telephonyManager.getSimSerialNumber());

                        //mensajes.team.mx.asistencia.Business.Business_Usuarios.insert_Usuario(context, entities_usuarios); // Inserta Las Credenciales del Usuario en SQLite
                    if(entities_cel != null) {
                        Intent intent = new Intent(Main_Activity.this, Asistencia_Activity.class);
                        intent.putExtra("Entities_Usuarios", entitiesUsuarios);
                        intent.putExtra("time", fecha_Servidor);
                        startActivity(intent);
                        finish();
                    }

                } catch (Exception e){
                    e.getMessage();
                }
            }

            dialog.dismiss();
        }
    }

    // Verifica si existe Red Wifi o Datos
    private boolean validateNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private void savePreference(String usuario, String pass) { // Guarda las Credenciales
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario", usuario.trim());
        editor.putString("password", pass.trim());
        editor.apply();

    }

    private void setCredencialesIfExists() {

        String user = Utils.getUsuarioPreference(preferences);
        String pass = Utils.getPasswordPreference(preferences);

        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)) {
            input_usuario.setText(user);
            input_password.setText(pass);
        }
    }

    private void removePreference() { // Permite Salir de la session y borrar las credenciales del SharedPreference
        preferences.edit().clear().apply();
    }

    private void alert_Dialog(){
        AlertDialog.Builder build = new AlertDialog.Builder(Main_Activity.this);

        View view_alert = getLayoutInflater().inflate(R.layout.inflater_phone, null);
        final EditText txt_numberphone = (EditText)view_alert.findViewById(R.id.txt_numberphone);
          AppCompatButton var_celphone = (AppCompatButton)view_alert.findViewById(R.id.var_celphone);

        var_celphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if(txt_numberphone.getText().toString().length() < 10) {
                        mensajes.team.mx.asistencia.Business.Business_Usuarios.insert_Usuario(context, entitiesUsuarios); // Inserta Las Credenciales del Usuario en SQLite
                    }else {

                        entitiesUsuarios.setTelefono(txt_numberphone.getText().toString());
                        mensajes.team.mx.asistencia.Business.Business_Usuarios.insert_Usuario(context, entitiesUsuarios); // Inserta Las Credenciales del Usuario en SQLite
                        mensajes.team.mx.asistencia.Business.Business_Information_Celphone.Insert_Information(context, entitiesUsuarios);

                    }

                    Intent intent = new Intent(Main_Activity.this, Asistencia_Activity.class);
                    intent.putExtra("Entities_Usuarios", entitiesUsuarios);
                    intent.putExtra("time", fecha_Servidor);
                    startActivity(intent);
                    finish();

                } catch (Exception e){
                    e.getMessage();
                }

            }
        });

        build.setView(view_alert);
        AlertDialog alert = build.create();
        alert.show();
    }

    public void dialog_infoGrafia() {
        View view_dialog = getLayoutInflater().inflate(R.layout.inflater_welcome, null);
        cordinator_layout = (WelcomeCoordinatorLayout)view_dialog.findViewById(R.id.cordinator_layout);
        cordinator_layout.addPage(R.layout.infografia_one, R.layout.infografia_two);
        final Dialog alert = new Dialog(new ContextThemeWrapper(Main_Activity.this, R.style.Dialogo));
        alert.setContentView(view_dialog);
        alert.setCancelable(false);
        alert.create();
        alert.show();
        Button bt_salir = view_dialog.findViewById(R.id.bt_salir);
        bt_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
    }

}
