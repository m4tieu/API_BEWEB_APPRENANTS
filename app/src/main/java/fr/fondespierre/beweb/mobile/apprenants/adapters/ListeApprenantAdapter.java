package fr.fondespierre.beweb.mobile.apprenants.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import fr.fondespierre.beweb.mobile.apprenants.DetailApprenantActivity;
import fr.fondespierre.beweb.mobile.apprenants.R;
import fr.fondespierre.beweb.mobile.apprenants.dal.Data;

/**
 * Created by mathieu on 05/07/17.
 */

public class ListeApprenantAdapter extends ArrayAdapter {


    private final Activity act;

    // On indique le layout qui affiche l'objet

    private final int resource = R.layout.liste_apprenants_item;
    private final JSONArray apprenants;


    public ListeApprenantAdapter(@NonNull Activity activity, @LayoutRes int resource, JSONArray liste) {
        super(activity.getApplicationContext(), resource);
        this.act = activity;
        this.apprenants = liste;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final int index = position;
        // L'objet inflater construit tous les elements contenue dans le layout pour retourner l'objet a la position specifié
        LayoutInflater inflater = act.getLayoutInflater();
        convertView = inflater.inflate(resource,null);

        // Attribue les textes au layout en fonction de leurs id
        TextView firstName = (TextView)convertView.findViewById(R.id.lAItem_textView_nom);
        TextView lastName = (TextView)convertView.findViewById(R.id.lAItem_textView_prenom);

        // Attribue au layout en fonction de l'id l'evenement au click du bouton
        ImageView detail = (ImageView)convertView.findViewById(R.id.lAItem_button_detail);
        detail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(act.getApplicationContext(), DetailApprenantActivity.class);
                    Data.apprenant = apprenants.getJSONObject(index);
                    act.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        try {
            firstName.setText(apprenants.getJSONObject(position).getString("prenom"));
            lastName.setText(apprenants.getJSONObject(position).getString("nom"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }

    // On retourne les apprenants tant qu'il y en a
    @Override
    public int getCount() {
        return apprenants.length();
    }
}
