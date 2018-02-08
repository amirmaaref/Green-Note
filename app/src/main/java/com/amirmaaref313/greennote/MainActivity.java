package com.amirmaaref313.greennote;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import DataConnection.AppDatabase;
import DataConnection.Note;
import ImageHandler.ImageHandler;
import ImageHandler.CustomListLoader;
import ImageHandler.Movaghat;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textView;
    String[] items;
    ListView listView;
    Bitmap [] bitmaps;
    AppDatabase  appDatabase;
    List<Note> getList;
    int [] ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView)findViewById(R.id.listView);
      //  imageView=(ImageView)findViewById(R.id.imageView2);
        textView=(TextView)findViewById(R.id.textView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startInsertActivity();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        dadada();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setMovaghat(position);

            }
        });
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void dadada(){
        setTitle("Loading Notes...PLease Wait...");
//        appDatabase = Room.databaseBuilder(load,AppDatabase.class,"noteData")
//                .build();



        appDatabase = Room.databaseBuilder(this.getApplication(),AppDatabase.class,"noteData")
                .allowMainThreadQueries().build();


//        class flii extends AsyncTask<Void,Void,Void>{
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//                appDatabase = Room.databaseBuilder(this,AppDatabase.class,"noteData")
//                .build();
//                return null;
//            }
//        }


        List<Note> notes = new ArrayList<>();

        notes.add(new Note("Amirhosein","Gh"));

        Note note = new Note("Amirhosein", "Sarah");

        //appDatabase.noteDao().insertOne(note);

        getList = appDatabase.noteDao().getAll();

        items = new String[getList.size()];

        for(int i=0;i<=getList.size()-1;i++){
            items[i]=getList.get(i).getTitle();
        }
        bitmaps = new Bitmap[getList.size()];
        for (int i=0;i<=getList.size()-1;i++){
            bitmaps[i]=ImageHandler.getImageBitmap(this,getList.get(i).getImg());
        }

        ids = new int[getList.size()];
        for(int i = 0; i<=getList.size()-1;i++){
            ids[i]=getList.get(i).getNid();
        }

        CustomListLoader adapter = new CustomListLoader(this , items ,bitmaps);
        listView.setAdapter(adapter);


        setTitle("Green Note");
    }
    private void startInsertActivity(){
        startActivity(new Intent(this,InsertActivity.class));
    }

    @Override
    public void onResume(){
        super.onResume();
        dadada();
    }

    public void setMovaghat(int pos){
        Movaghat.setNote(getList.get(pos));
        Movaghat.setBitmap(bitmaps[pos]);
        startActivity(new Intent(this,ViewActivity.class));
    }
}
