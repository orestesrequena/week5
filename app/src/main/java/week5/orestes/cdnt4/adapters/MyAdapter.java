package week5.orestes.cdnt4.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import week5.orestes.cdnt4.R;

public class MyAdapter extends BaseAdapter {

    public MyAdapter(Context mContext, LayoutInflater mLayoutInflater) {
        this.mContext = mContext;
        this.mLayoutInflater = mLayoutInflater;
    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int count = 0;

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //verificacion si necesitamos o no el inflater. si  no hay nada hace la demanda normal y carga la informacion del objeto(ej, id 27) si ya lo ha cargado, mira
        //directamente en el viewholder para no tener que hacer el findViewById, que reauiere mucha memoria
        final ViewHolder viewHolder;
        if (convertView == null) {
            // primera linea genera x veces el contenido del layout, despues llama al textview para poner la posicion
            convertView = mLayoutInflater.inflate(R.layout.adapter_line, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.adapter_line_textView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText("" + position);
        return convertView;
    }

    private class ViewHolder {
        TextView textView;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
