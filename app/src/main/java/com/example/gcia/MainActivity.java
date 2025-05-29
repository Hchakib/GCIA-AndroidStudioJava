package com.example.gcia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //activity_main
    private EditText nomview,prenomview,ageview,paysview,villeview,nuview,mdpview;
    private Button connecview,inscrview;
    private Context ctx;

    //dialog_connection
    private EditText nucoview,mdpcoview;
    private Button conneccobtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.nomview=(EditText)findViewById(R.id.nomview);
        this.prenomview=(EditText)findViewById(R.id.prenomview);
        this.ageview=(EditText)findViewById(R.id.ageview);
        this.paysview=(EditText)findViewById(R.id.paysview);
        this.villeview=(EditText)findViewById(R.id.villeview);
        this.nuview=(EditText)findViewById(R.id.nuview);
        this.mdpview=(EditText)findViewById(R.id.mdpview);

        this.connecview=(Button) findViewById(R.id.connecbtn);
        this.inscrview=(Button) findViewById(R.id.inscrbtn);

        this.ctx=this;


        this.inscrview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
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

                    if (EnfantDB.isUsernameTaken(nu)) {
                        Toast.makeText(ctx, "Nom d'utilisateur déjà utilisé. Veuillez choisir un autre!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (EnfantDB.isPasswordTaken(mdp)) {
                        Toast.makeText(ctx, "Mot de passe déjà utilisé. Veuillez choisir un autre!", Toast.LENGTH_LONG).show();
                        return;
                    }


                    Enfant enfant = new Enfant(nom, prenom, ageInt, pays, ville, nu, mdp);

                    EnfantDB.enfants.add(enfant);

                    nuview.setText("");
                    mdpview.setText("");

                    Toast.makeText(ctx, "Inscription confirmée!", Toast.LENGTH_LONG).show();

                    dialogco();

                } catch (Exception e) {
                    Toast.makeText(ctx, "Une erreur est survenue : " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });



        this.connecview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dialogco();
            }
        });

    }


    private void dialogco ()
    {
        try {
            AlertDialog.Builder altb = new AlertDialog.Builder(ctx);
            LayoutInflater inflater = LayoutInflater.from(ctx);
            View ballon = inflater.inflate(R.layout.dialog_connection, null);

            this.nucoview=(EditText) ballon.findViewById(R.id.nuview);
            this.mdpcoview=(EditText) ballon.findViewById(R.id.mdpview);

            this.conneccobtn= (Button) ballon.findViewById(R.id.connecbtn);

            this.conneccobtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nuco = nucoview.getText().toString().trim();
                    String mdpco = mdpcoview.getText().toString().trim();
                    boolean verif = false;
                    if (nuco.isEmpty() || mdpco.isEmpty()) {
                        Toast.makeText(ctx, "Erreur. Veuillez remplir tous les champs!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if(nuco.equals("pere")&& mdpco.equals("noel"))
                    {
                        Intent i = new Intent(ctx, MainActivity3.class);
                        startActivity(i);
                    } else {
                        for (Enfant enf : EnfantDB.enfants) {
                            if (nuco.equals(enf.getUsername()) && mdpco.equals(enf.getPassword())) {
                                Intent i = new Intent(ctx, MainActivity2.class);
                                i.putExtra("username", enf.getUsername());
                                startActivity(i);
                                verif=true;
                                finish();
                                break;
                            }
                        }
                        if (!verif){
                            Toast.makeText(ctx, "Erreur. Compte introuvable!", Toast.LENGTH_LONG).show();
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

}