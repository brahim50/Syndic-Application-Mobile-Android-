package ma.emsi.syndicapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ma.emsi.syndicapp.R;
import ma.emsi.syndicapp.bean.Resident;

public class ResidentAdapter extends BaseAdapter {

    private List<Resident> residents;
    private LayoutInflater layoutInflater;

    public ResidentAdapter(Context context, List<Resident> residents) {
        this.residents = residents;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return residents.size();
    }

    @Override
    public Object getItem(int position) {
        return residents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = layoutInflater.inflate(R.layout.resident_item, null);
        TextView lmmeuble = (TextView) convertView.findViewById(R.id.lmmeuble);
        TextView appartement = (TextView) convertView.findViewById(R.id.appartement);
        TextView nom = (TextView) convertView.findViewById(R.id.nom);
        TextView ville = (TextView) convertView.findViewById(R.id.ville);
        TextView emailTel = (TextView) convertView.findViewById(R.id.emailTel);

        lmmeuble.setText(residents.get(position).getLmmeuble() + "");
        appartement.setText(residents.get(position).getAppartement() + "");
        nom.setText(residents.get(position).getNom() + " " + residents.get(position).getPrenom());
        ville.setText(residents.get(position).getVille() + "");
        emailTel.setText(residents.get(position).getEmail() + " / " + residents.get(position).getTele());


        return convertView;
    }
}
