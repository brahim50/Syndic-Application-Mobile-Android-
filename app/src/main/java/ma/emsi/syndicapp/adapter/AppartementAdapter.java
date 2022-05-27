package ma.emsi.syndicapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ma.emsi.syndicapp.R;
import ma.emsi.syndicapp.bean.Appartement;

public class AppartementAdapter extends BaseAdapter {

    private List<Appartement> appartements;
    private LayoutInflater layoutInflater;

    public AppartementAdapter(Context context, List<Appartement> appartements) {
        this.appartements = appartements;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return appartements.size();
    }

    @Override
    public Object getItem(int position) {
        return appartements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = layoutInflater.inflate(R.layout.apprtement_item, null);
        TextView lmmeuble = (TextView) convertView.findViewById(R.id.lmmeuble);
        TextView numero = (TextView) convertView.findViewById(R.id.numero);
        TextView etage = (TextView) convertView.findViewById(R.id.etage);
        TextView surface = (TextView) convertView.findViewById(R.id.surface);
        TextView ids = (TextView) convertView.findViewById(R.id.ids);
        ids.setText(appartements.get(position).getId()+"");
        lmmeuble.setText(appartements.get(position).getLmmeuble());
        numero.setText("N° " +appartements.get(position).getNumero());
        etage.setText(appartements.get(position).getEtage() + " etage");
        surface.setText(appartements.get(position).getSurface() + " m²");


        return convertView;
    }
}
