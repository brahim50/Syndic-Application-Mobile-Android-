package ma.emsi.syndicapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ma.emsi.syndicapp.R;
import ma.emsi.syndicapp.bean.Revenu;

public class RevenuAdapter  extends BaseAdapter {

    private List<Revenu> revenus;
    private LayoutInflater layoutInflater;

    public RevenuAdapter(Context context, List<Revenu> revenus) {
        this.revenus = revenus;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return revenus.size();
    }

    @Override
    public Object getItem(int position) {
        return revenus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = layoutInflater.inflate(R.layout.revenu_item, null);
        TextView lmmeuble = (TextView) convertView.findViewById(R.id.lmmeubleRv);
        TextView appartement = (TextView) convertView.findViewById(R.id.appartementRv);
        TextView somme = (TextView) convertView.findViewById(R.id.sommeRv);
        TextView date = (TextView) convertView.findViewById(R.id.dateRv);


        lmmeuble.setText(revenus.get(position).getLmmeuble() + "");
        appartement.setText(revenus.get(position).getAppartement() + "");
        somme.setText(revenus.get(position).getSomme() + "");
        date.setText(revenus.get(position).getDate() + "");


        return convertView;
    }
}