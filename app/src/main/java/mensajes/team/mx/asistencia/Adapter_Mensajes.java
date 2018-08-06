package mensajes.team.mx.asistencia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import mensajes.team.mx.asistencia.Entities.Entities_Mensajes;

public class Adapter_Mensajes extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Entities_Mensajes> mensajes;

    public Adapter_Mensajes(Context context, int layout, List<Entities_Mensajes> mensajes){
        this.context = context;
        this.layout = layout;
        this.mensajes = mensajes;
    }

    @Override
    public int getCount() {
        return this.mensajes.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mensajes.get(position);
    }

    @Override
    public long getItemId(int Id) {
        return Id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.adapter_mensajes, null);

        mensajes.team.mx.asistencia.Entities.Entities_Mensajes mensajes1 = mensajes.get(position);
        TextView text_title = (TextView)view.findViewById(R.id.text_title);
        TextView text_desc = (TextView)view.findViewById(R.id.text_desc);

        text_title.setText(mensajes1.getTipo());
        text_desc.setText(mensajes1.getCuerpo());

        return view;
    }
}
