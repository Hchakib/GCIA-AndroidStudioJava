package com.example.gcia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class EnfantArrayAdapter extends ArrayAdapter<Enfant> {

    private Context ctx;
    private int layout;
    private List<Enfant> enfantArrayList;


    public EnfantArrayAdapter(@NonNull Context context, int resource, @NonNull List<Enfant> objects) {
        super(context, resource, objects);

        this.ctx = context;
        this.layout = resource;
        this.enfantArrayList =  objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Enfant p = enfantArrayList.get(position);

        LayoutInflater inflater = LayoutInflater.from(ctx);
        convertView = inflater.inflate(layout,parent,false);


        TextView nom =(TextView) convertView.findViewById(R.id.nomview);
        TextView prenom =(TextView) convertView.findViewById(R.id.prenomview);
        TextView pays = (TextView)convertView.findViewById(R.id.paysview);
        TextView ville = (TextView)convertView.findViewById(R.id.villeview);


        Button infos =(Button) convertView.findViewById(R.id.infosbtn);
        Button cadeaux =(Button) convertView.findViewById(R.id.cadeauxbtn);


        nom.setText("Nom: "+p.getNom());
        prenom.setText("Prénom: "+p.getPrenom());
        pays.setText("Pays: "+p.getPays());
        ville.setText("Ville: "+p.getVille());


        infos.setOnClickListener(new View.OnClickListener() {
            private EditText nomview,prenomview,ageview,paysview,villeview,nuview,mdpview;

            @Override
            public void onClick(View view) {

                try {
                    AlertDialog.Builder altb = new AlertDialog.Builder(ctx);
                    LayoutInflater inflater = LayoutInflater.from(ctx);
                    View ballon = inflater.inflate(R.layout.dialog_infos_enf_admin, null);


                    this.nomview = (EditText) ballon.findViewById(R.id.nomview);
                    this.prenomview = (EditText) ballon.findViewById(R.id.prenomview);
                    this.ageview = (EditText) ballon.findViewById(R.id.ageview);
                    this.paysview = (EditText) ballon.findViewById(R.id.paysview);
                    this.villeview = (EditText) ballon.findViewById(R.id.villeview);
                    this.nuview = (EditText) ballon.findViewById(R.id.nuview);
                    this.mdpview = (EditText) ballon.findViewById(R.id.mdpview);

                    this.nomview.setText(p.getNom());
                    this.prenomview.setText(p.getPrenom());
                    this.ageview.setText(String.valueOf(p.getAge()));
                    this.paysview.setText(p.getPays());
                    this.villeview.setText(p.getVille());
                    this.nuview.setText(p.getUsername());
                    this.mdpview.setText(p.getPassword());

                    altb.setView(ballon);
                    altb.create().show();

                } catch (Exception e) {
                    Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


        cadeaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    AlertDialog.Builder altb = new AlertDialog.Builder(ctx);
                    LayoutInflater inflater = LayoutInflater.from(ctx);
                    View ballon2 = inflater.inflate(R.layout.dialog_cadeaux_enf_admin, null);

                    ListView lsv = (ListView) ballon2.findViewById(R.id.listcadeauxadmin);
                    ArrayList<Cadeau> cadeauArrayList = new ArrayList<>();

                    for (Enfant enf : EnfantDB.enfants) {
                        if (enf.getUsername().equals(p.getUsername())) {
                            cadeauArrayList = (ArrayList<Cadeau>) enf.getCadeaux();
                            break;
                        }
                    }
                    if (cadeauArrayList.isEmpty()) {
                        Toast.makeText(ctx, "Aucun cadeau trouvé!", Toast.LENGTH_SHORT).show();
                    }

                    CadeauArrayAdapter adapter1 = new CadeauArrayAdapter(ballon2.getContext(), R.layout.cadeau_layout, cadeauArrayList);
                    lsv.setAdapter(adapter1);

                    altb.setView(ballon2);
                    altb.create().show();

                } catch (Exception e) {
                    Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });


        return convertView;
    }

    public void updateList(List<Enfant> newList) {
        enfantArrayList.clear();
        enfantArrayList.addAll(newList);
        notifyDataSetChanged();
    }

}