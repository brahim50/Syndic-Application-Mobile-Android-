package ma.emsi.syndicapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ma.emsi.syndicapp.R;
import ma.emsi.syndicapp.bean.Lmmeuble;

public class LmmeubleAdapter  extends BaseAdapter {

    private List<Lmmeuble> lmmeubles;
    private LayoutInflater layoutInflater;

    public LmmeubleAdapter(Context context, List<Lmmeuble> lmmeubles) {
        this.lmmeubles = lmmeubles;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lmmeubles.size();
    }

    @Override
    public Object getItem(int position) {
        return lmmeubles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = layoutInflater.inflate(R.layout.lmmeuble_item, null);
        TextView numero = (TextView) convertView.findViewById(R.id.numeroIm);
        TextView nom = (TextView) convertView.findViewById(R.id.nomIm);
        TextView etage = (TextView) convertView.findViewById(R.id.etageIm);
        TextView addresse = (TextView) convertView.findViewById(R.id.addresseIm);
        TextView ville = (TextView) convertView.findViewById(R.id.villeIm);
        //TextView ids = (TextView) convertView.findViewById(R.id.ids);
        //ids.setText(lmmeubles.get(position).getId()+"");


        nom.setText(lmmeubles.get(position).getNom()+"");
        addresse.setText(lmmeubles.get(position).getAddresse()+"");
        etage.setText(lmmeubles.get(position).getEtage()+" etage");
        ville.setText(lmmeubles.get(position).getVille()+"");
        numero.setText("N°" +lmmeubles.get(position).getNumero());

        return convertView;
    }
}