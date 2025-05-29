package com.example.gcia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private ListView lsv,paysListView,villeListView;
    private EnfantArrayAdapter adapter;
    private List<Enfant> enfantArrayList;
    private Context ctx;
    private Button villebtn,paysbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        this.ctx=this;

        this.lsv=(ListView) findViewById(R.id.listenf);
        this.villebtn=(Button) findViewById(R.id.villebtn);
        this.paysbtn=(Button) findViewById(R.id.paysbtn);


        this.enfantArrayList = new ArrayList<>(EnfantDB.enfants);


        if (EnfantDB.enfants.isEmpty()) {
            Toast.makeText(ctx, "Aucun enfant trouvé!", Toast.LENGTH_SHORT).show();
        }

        this.adapter=new EnfantArrayAdapter(ctx,R.layout.enfant_layout,enfantArrayList);
        lsv.setAdapter(adapter);

        this.paysbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    AlertDialog.Builder altb = new AlertDialog.Builder(ctx);
                    LayoutInflater inflater = LayoutInflater.from(ctx);
                    View ballon2 = inflater.inflate(R.layout.dialog_pays, null);

                    paysListView =(ListView) ballon2.findViewById(R.id.listpays);

                    List<String> paysList=EnfantDB.getAllPays();

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ballon2.getContext(), android.R.layout.simple_list_item_1, paysList);
                    paysListView.setAdapter(adapter);


                    paysListView.setOnItemClickListener((parent, view1, position, id) -> {
                        String selectedCountry = paysList.get(position);
                        filterByCountry(selectedCountry);
                    });

                    altb.setView(ballon2);
                    altb.create().show();

                } catch (Exception e) {
                    Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

        this.villebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    AlertDialog.Builder altb = new AlertDialog.Builder(ctx);
                    LayoutInflater inflater = LayoutInflater.from(ctx);
                    View ballon2 = inflater.inflate(R.layout.dialog_ville, null);

                    villeListView =(ListView) ballon2.findViewById(R.id.listville);

                    List<String> villeList=EnfantDB.getAllVilles();

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ballon2.getContext(), android.R.layout.simple_list_item_1, villeList);
                    villeListView.setAdapter(adapter);


                    villeListView.setOnItemClickListener((parent, view1, position, id) -> {
                        String selectedVille = villeList.get(position);
                        filterByVille(selectedVille);
                    });

                    altb.setView(ballon2);
                    altb.create().show();

                } catch (Exception e) {
                    Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void filterByCountry(String country) {
        List<Enfant> filteredList = new ArrayList<>();
        for (Enfant enfant : EnfantDB.enfants) {
            if (enfant.getPays().equals(country)) {
                filteredList.add(enfant);
            }
        }

        adapter.updateList(filteredList);

        Toast.makeText(ctx, "Filtré par pays : " + country, Toast.LENGTH_SHORT).show();
    }

    private void filterByVille(String ville) {
        List<Enfant> filteredList = new ArrayList<>();
        for (Enfant enfant : EnfantDB.enfants) {
            if (enfant.getVille().equals(ville)) {
                filteredList.add(enfant);
            }
        }

        adapter.updateList(filteredList);

        Toast.makeText(ctx, "Filtré par ville : " + ville, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setTitle("GCIA-Admin");

        MenuItem deconnexion = menu.add(Menu.NONE, Menu.FIRST, 0, "Déconnexion");

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        super.onOptionsItemSelected(item);

        Intent i;

        switch (item.getItemId())
        {

            case Menu.FIRST:
                i = new Intent(ctx, MainActivity.class);
                startActivity(i);
                Toast.makeText(ctx, "Tu es déconnecté!", Toast.LENGTH_LONG).show();
                return true;

        }
        return true;
    }
}