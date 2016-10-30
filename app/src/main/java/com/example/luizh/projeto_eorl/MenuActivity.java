package com.example.luizh.projeto_eorl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.luizh.projeto_eorl.domain.util.AboutActivity;
import com.example.luizh.projeto_eorl.domain.util.User;
import com.example.luizh.projeto_eorl.domain.util.UtilActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crash.FirebaseCrash;

public class MenuActivity extends UtilActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);


        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user == null)
                {
                    Intent intent = new Intent( MenuActivity.this, LoginActivity.class );
                    startActivity( intent );
                    finish();
                }
            }
        };

        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener( mAuthListener );

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        User user = new User();

        if (id == R.id.nav_camera)
        {
            Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {

            if(!user.isSocialNetworkLogged(this))
            {
                Intent intent = new Intent(MenuActivity.this, UpdateActivity.class);
                startActivity(intent);
            }
            else
            {
                showToast("Opção indisponível para o seu perfil");
            }

        } else if (id == R.id.nav_password) {

            if(!user.isSocialNetworkLogged(this))
            {
                Intent intent = new Intent(MenuActivity.this, UpdatePasswordActivity.class);
                startActivity(intent);
            }
            else
            {
                showToast("Opção indisponível para o seu perfil");
            }

        } else if (id == R.id.nav_send) {

            Intent intent = new Intent(MenuActivity.this, MessageActivity.class);
            startActivity(intent);

        }else if(id == R.id.nav_logoff){

            FirebaseAuth.getInstance().signOut();
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void sendToNoteActivity(android.view.View view)
    {
        FirebaseCrash.log("MenuActivity:clickListener:button:sendToNoteActivity");
        Intent intent = new Intent(MenuActivity.this, NoteActivity.class);
        startActivity(intent);
    }

    public void sendToTadActivity(android.view.View view)
    {
        showToast("Conteúdo em desenvolvimento.");
    }

    public void sendToFaqActivity(android.view.View view)
    {
        showToast("Conteúdo em desenvolvimento.");
    }

    public void sentToDiagnosisActivity(View view) {

        FirebaseCrash.log("MenuActivity:clickListener:button:sentToDiagnosisActivity");
        Intent intent = new Intent(MenuActivity.this, DiagnosisActivity.class);
        startActivity(intent);
    }

    public void sendToCalculatorWeightActivity(View view) {

        FirebaseCrash.log("MenuActivity:clickListener:button:sendToCalculatorWeightActivity");
        //Intent intent = new Intent(MenuActivity.this, CalculatorWeightActivity.class);
        Intent intent = new Intent(MenuActivity.this, CalculatorMenu.class);
        startActivity(intent);
    }

    public void sendToDrugListActivity(View view) {

        FirebaseCrash.log("MenuActivity:clickListener:button:sendToDrugListActivity");
        Intent intent = new Intent(MenuActivity.this, DrugListActivity.class);
        startActivity(intent);
    }
}

