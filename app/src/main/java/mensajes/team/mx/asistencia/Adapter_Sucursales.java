package mensajes.team.mx.asistencia;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.ncorti.slidetoact.SlideToActView;

import java.util.List;

import mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas;

public class Adapter_Sucursales extends BaseAdapter{

    private Context context;
    private int layout;
    private List<Entities_Conjuntos_Tiendas> cadenas;
    public final mensajes.team.mx.asistencia.Entities.Entities_Usuarios usuario;
    private double latitud = 0.0F;
    private double longitud = 0.0F;
    private String time = "";

    public Adapter_Sucursales(Context context, int layout, List<Entities_Conjuntos_Tiendas> cadenas, mensajes.team.mx.asistencia.Entities.Entities_Usuarios usuario, double latitud, double longitud, String time){
        this.context = context;
        this.layout = layout;
        this.cadenas = cadenas;
        this.usuario = usuario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.time = time;
    }

    @Override
    public int getCount() {
        return this.cadenas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.cadenas.get(position);
    }

    @Override
    public long getItemId(int Id) {
        return Id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.adapter_sucursales, null);

       final mensajes.team.mx.asistencia.Entities.Entities_Conjuntos_Tiendas cadena = cadenas.get(position);
        SlideToActView slide_sucursal = (SlideToActView)view.findViewById(R.id.slide_sucursal);

        if(cadena.getAbierta() == 1) {
            slide_sucursal.setOuterColor(view.getResources().getColor(R.color.amarillo));
            slide_sucursal.setInnerColor(view.getResources().getColor(R.color.guerraro));
        } else if(cadena.getAbierta() == 2) {
            slide_sucursal.setOuterColor(view.getResources().getColor(R.color.verde));
            slide_sucursal.setInnerColor(view.getResources().getColor(R.color.guerraro));
            slide_sucursal.setLocked(true);
        } else {
            slide_sucursal.setOuterColor(view.getResources().getColor(R.color.guerraro));
            slide_sucursal.setInnerColor(view.getResources().getColor(R.color.blanco));
        }

        slide_sucursal.setText(cadena.getCadena());

        slide_sucursal.setOnSlideToActAnimationEventListener(new SlideToActView.OnSlideToActAnimationEventListener() {
            @Override
            public void onSlideCompleteAnimationStarted(SlideToActView slideToActView, float v) {

            }

            @Override
            public void onSlideCompleteAnimationEnded(SlideToActView slideToActView) {

                Intent intent = new Intent(context, Asistencia_Foto_Activity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("tienda", cadena);
                intent.putExtra("usuario", usuario);
                intent.putExtra("latitud", latitud);
                intent.putExtra("longitud", longitud);
                intent.putExtra("time", time);
                context.startActivity(intent);

            }

            @Override
            public void onSlideResetAnimationStarted(SlideToActView slideToActView) {

            }

            @Override
            public void onSlideResetAnimationEnded(SlideToActView slideToActView) {

            }
        });

        return view;
    }
}
