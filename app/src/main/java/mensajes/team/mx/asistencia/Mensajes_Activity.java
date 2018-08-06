package mensajes.team.mx.asistencia;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;

public class Mensajes_Activity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios proyectos_usuarios;
    mensajes.team.mx.asistencia.Entities.Collection_Mensajes collection_mensajes;
    private SwipeRefreshLayout refresh;
    ListView list_mensajes;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes_);
        getWindow().setStatusBarColor(getResources().getColor(R.color.guerraro));
        context = this;
        proyectos_usuarios = (mensajes.team.mx.asistencia.Entities.Entities_Proyectos_Usuarios)getIntent().getSerializableExtra("proyectos_usuarios");
        list_mensajes = (ListView)findViewById(R.id.list_mensajes);
        refresh = (SwipeRefreshLayout)findViewById(R.id.refresh);
        refresh.setOnRefreshListener(this);

        list_view_show();

        try {

            collection_mensajes = mensajes.team.mx.asistencia.Business.Business_Mensajes.get_Mensajes(context, proyectos_usuarios);
            Adapter_Mensajes mensajes = new Adapter_Mensajes(context, R.layout.adapter_mensajes, collection_mensajes);
            list_mensajes.setAdapter(mensajes);

        } catch (Exception e){
            e.getMessage();
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    list_view_show();
                    collection_mensajes = mensajes.team.mx.asistencia.Business.Business_Mensajes.get_Mensajes(context, proyectos_usuarios);
                    refresh.setRefreshing(false);
                    finish();
                    startActivity(getIntent());
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }, 2000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void list_view_show(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mensajes.team.mx.asistencia.Data.Download_Information.Download_Mensajes(context, proyectos_usuarios);
                } catch (Exception e){
                    e.getMessage();
                }
            }
        }).start();

    }

}
