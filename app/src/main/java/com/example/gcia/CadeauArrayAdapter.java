package com.example.gcia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CadeauArrayAdapter extends ArrayAdapter<Cadeau> {

    private Context ctx;
    private int layout;
    private ArrayList<Cadeau> cadeauArrayList;

    public CadeauArrayAdapter(@NonNull Context context, int resource, @NonNull List<Cadeau> objects) {
        super(context, resource, objects);

        this.ctx = context;
        this.layout = resource;
        this.cadeauArrayList = (ArrayList<Cadeau>) objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Cadeau p = cadeauArrayList.get(position);

        LayoutInflater inflater = LayoutInflater.from(ctx);
        convertView = inflater.inflate(layout,parent,false);

        int imgType=0;

        ImageView img = (ImageView)convertView.findViewById(R.id.img);
        TextView description =(TextView) convertView.findViewById(R.id.descriptionview);
        TextView nom =(TextView) convertView.findViewById(R.id.nomview);
        TextView nomenfant =(TextView) convertView.findViewById(R.id.enfantview);
        TextView prix =(TextView) convertView.findViewById(R.id.prixview);



        if (p.getType().equals("Jouet"))
        {
            imgType=R.drawable.ijouet;
        }else if (p.getType().equals("Électronique"))
        {
            imgType=R.drawable.ielectronique;
        }else if (p.getType().equals("Santé"))
        {
            imgType=R.drawable.isante;
        }else if (p.getType().equals("Vêtement"))
        {
            imgType=R.drawable.ivetement;
        }


        img.setImageResource(imgType);
        nom.setText("Nom: "+p.getNom());
        description.setText("Description: "+p.getDescription());
        prix.setText("prix: "+p.getPrix());
        nomenfant.setText("Enfant: "+p.getEnfant().getNom()+" "+p.getEnfant().getPrenom());




        return convertView;
    }


}