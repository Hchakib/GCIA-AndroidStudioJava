package com.example.gcia;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;


public class MainActivity2 extends AppCompatActivity {

    //activity_main2
    private TextView acceuilview;

    //dialog_infos_enfant
    private EditText nomview,prenomview,ageview,paysview,villeview,nuview,mdpview;
    private Button modifierview;

    //dialog_demande
    private EditText nomcview,descriptioncview,prixcview;
    private Button demanderview;
    private RadioButton jouetradio,vetementradio,santeradio,electroniqueradio;

    //dialog_cadeaux_enfant
    private ListView lsv;
    private ArrayList<Cadeau> cadeauArrayList;
    private CadeauArrayAdapter adapter;


    private Enfant enfant;

    private Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.ctx=this;
        this.acceuilview=(TextView) findViewById(R.id.acceuilview);



        String username = getIntent().getStringExtra("username");

        for (Enfant enf : EnfantDB.enfants) {
            if (username.equals(enf.getPassword())) {
                enfant=enf;
                break;
            }
        }

        acceuilview.setText("-"+enfant.getNom()+" "+enfant.getPrenom()+"-");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setTitle("GCIA-User");

        MenuItem infos = menu.add(Menu.NONE, Menu.FIRST, 0, "Tes informations");
        MenuItem cadeau = menu.add(Menu.NONE, Menu.FIRST + 1, 1, "Tes cadeaux");
        MenuItem demande = menu.add(Menu.NONE, Menu.FIRST + 2, 2, "Demande un cadeau");
        MenuItem deconnexion = menu.add(Menu.NONE, Menu.FIRST + 3, 3, "Déconnexion");

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        super.onOptionsItemSelected(item);

        Intent i;

        switch (item.getItemId())
        {
            case Menu.FIRST:
                dialoginfo();
                return true;
            case Menu.FIRST+1:
                dialogcadeaux();
                return true;
            case Menu.FIRST+2:
                dialogdemande();
                return true;
            case Menu.FIRST + 3:
                enfant = null; // Réinitialise l'objet local
                Intent intent = new Intent(ctx, MainActivity.class);
                startActivity(intent);
                finish(); // Termine l'activité actuelle pour éviter d'y revenir accidentellement
                Toast.makeText(ctx, "Tu es déconnecté!", Toast.LENGTH_LONG).show();
                return true;


        }
        return true;
    }

    private void dialoginfo ()
    {
        try {
            AlertDialog.Builder altb = new AlertDialog.Builder(ctx);
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View ballon = inflater.inflate(R.layout.dialog_infos_enfant, null);


            this.nomview=(EditText)ballon.findViewById(R.id.nomview);
            this.prenomview=(EditText)ballon.findViewById(R.id.prenomview);
            this.ageview=(EditText)ballon.findViewById(R.id.ageview);
            this.paysview=(EditText)ballon.findViewById(R.id.paysview);
            this.villeview=(EditText)ballon.findViewById(R.id.villeview);
            this.nuview=(EditText) ballon.findViewById(R.id.nuview);
            this.mdpview=(EditText) ballon.findViewById(R.id.mdpview);

            this.nomview.setText(enfant.getNom());
            this.prenomview.setText(enfant.getPrenom());
            this.ageview.setText(String.valueOf(enfant.getAge()));
            this.paysview.setText(enfant.getPays());
            this.villeview.setText(enfant.getVille());
            this.nuview.setText(enfant.getUsername());
            this.mdpview.setText(enfant.getPassword());

            this.modifierview= (Button) ballon.findViewById(R.id.modifierbtn);

            this.modifierview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nom = nomview.getText().toString().trim();
                    String prenom = prenomview.getText().toString().trim();
                    String ageString = ageview.getText().toString().trim();
                    String pays = paysview.getText().toString().trim();
                    String ville = villeview.getText().toString().trim();
                    String nu = nuview.getText().toString().trim();
                    String mdp = mdpview.getText().toString().trim();

                    if (nom.isEmpty() || prenom.isEmpty() || ageString.isEmpty() || pays.isEmpty() || ville.isEmpty() || nu.isEmpty() || mdp.isEmpty()) {
                        Toast.makeText(ctx, "Erreur. Veuillez remplir tous les champs!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    int ageInt;
                    try {
                        ageInt = Integer.parseInt(ageString);
                    } catch (NumberFormatException e) {
                        Toast.makeText(ctx, "Erreur. L'âge doit être un entier!", Toast.LENGTH_LONG).show();
                        return;
                    }

                        if ( EnfantDB.isUsernameTaken(nu)) {
                            if (!enfant.getUsername().equals(nu))
                            {
                                Toast.makeText(ctx, "Nom d'utilisateur déjà utilisé. Veuillez choisir un autre!", Toast.LENGTH_LONG).show();
                                return;
                            }
                        }

                    if ( EnfantDB.isPasswordTaken(mdp)) {
                        if (!enfant.getPassword().equals(mdp))
                        {
                            Toast.makeText(ctx, "Mot de passe déjà utilisé. Veuillez choisir un autre!", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }


                    for (Enfant enf:EnfantDB.enfants) {
                        if ( enf.getUsername().equals(enfant.getUsername())) {
                            enf.setNom(nom);
                            enf.setPrenom(prenom);
                            enf.setAge(ageInt);
                            enf.setPays(pays);
                            enf.setVille(ville);
                            enf.setUsername(nu);
                            enf.setPassword(mdp);
                        }
                        }


                    Toast.makeText(ctx, "Modfications confirmées!", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(ctx, MainActivity.class);
                    startActivity(i);

                }
            });

            altb.setView(ballon);
            altb.create().show();

        } catch (Exception e) {
            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void dialogdemande () {
        try {
            AlertDialog.Builder altb = new AlertDialog.Builder(ctx);
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View ballon = inflater.inflate(R.layout.dialog_demande, null);

            this.nomcview = (EditText) ballon.findViewById(R.id.nomcview);
            this.descriptioncview = (EditText) ballon.findViewById(R.id.descriptioncview);
            this.prixcview = (EditText) ballon.findViewById(R.id.prixcview);

            this.jouetradio = (RadioButton) ballon.findViewById(R.id.jouetradio);
            this.vetementradio = (RadioButton) ballon.findViewById(R.id.vetementradio);
            this.santeradio = (RadioButton) ballon.findViewById(R.id.santeradio);
            this.electroniqueradio = (RadioButton) ballon.findViewById(R.id.electroniqueradio);

            this.demanderview = (Button) ballon.findViewById(R.id.demanderbtn);

            this.demanderview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nom = nomcview.getText().toString().trim();
                    String description = descriptioncview.getText().toString().trim();
                    String prixString = prixcview.getText().toString().trim();
                    String type="";
                    boolean jouet = jouetradio.isChecked();
                    boolean electronique = electroniqueradio.isChecked();
                    boolean sante = santeradio.isChecked();
                    boolean vetement = vetementradio.isChecked();


                    if (nom.isEmpty() || description.isEmpty() || prixString.isEmpty()) {
                        Toast.makeText(ctx, "Erreur. Veuillez remplir tous les champs!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (!jouet && !electronique && !sante && !vetement) {
                        Toast.makeText(ctx, "Erreur. Veuillez sélectionner un type!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (jouet)
                    {
                        type="Jouet";
                    }else if (electronique)
                    {
                        type="Électronique";
                    }else if (sante)
                    {
                        type="Santé";
                    }else if (vetement)
                    {
                        type="Vêtement";
                    }

                    double prixDouble;
                    try {
                        prixDouble = Double.parseDouble(prixString);
                    } catch (NumberFormatException e) {
                        Toast.makeText(ctx, "Erreur. Le prix doit être un nombre!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Cadeau cadeau=new Cadeau(type,nom,description,prixDouble,enfant);


                        for (Enfant enf : EnfantDB.enfants) {
                            if (enf.getUsername().equals(enfant.getUsername())) {
                                enf.addCadeau(cadeau);
                                Toast.makeText(ctx, "Demande envoyée au Pére Noel!", Toast.LENGTH_LONG).show();
                                break;
                            }
                        }




                }
            });

            altb.setView(ballon);
            altb.create().show();

        } catch (Exception e) {
            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private void dialogcadeaux () {
        try {
            AlertDialog.Builder altb = new AlertDialog.Builder(ctx);
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View ballon = inflater.inflate(R.layout.dialog_cadeaux_enfant, null);

            this.lsv=(ListView) ballon.findViewById(R.id.list);
            this.cadeauArrayList=new ArrayList<>();

            if (cadeauArrayList != null) {
                cadeauArrayList.clear();
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }

            for (Enfant enf : EnfantDB.enfants) {
                if (enf.getUsername().equals(enfant.getUsername())) {
                    this.cadeauArrayList= (ArrayList<Cadeau>) enf.getCadeaux();
                    break;
                }
            }
            if (cadeauArrayList.isEmpty()) {
                Toast.makeText(ctx, "Aucun cadeau trouvé!", Toast.LENGTH_SHORT).show();
            }


            this.adapter=new CadeauArrayAdapter(ballon.getContext(),R.layout.cadeau_layout,cadeauArrayList);
            lsv.setAdapter(adapter);

            altb.setView(ballon);
            altb.create().show();

        } catch (Exception e) {
            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}