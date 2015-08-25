package net.emcain.selfesteem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import custom.FactBook;
import custom.PhotoAlbum;


public class MainActivity extends ActionBarActivity {

    private SharedPreferences mPrefs;

private String loadString = "";
private FactBook mFactBook = new FactBook(loadString);
private List<String> theFacts = mFactBook.getFactsList();
private PhotoAlbum mPhotoAlbum = new PhotoAlbum();

private static final String SAVE_TAG = "loadString"; // this should be assigned to whatever is saved in onSaveInstanceState



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null) {
//          theFacts = (ArrayList<String>) savedInstanceState.getStringArrayList(SAVE_TAG); // somehow change this to use LoadSaveString(???)
//            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            loadString = savedInstanceState.getString(SAVE_TAG); // make sure SAVE_TAG is properly assigned.
        }
    mFactBook = new FactBook(loadString);

    TextView factLabel = (TextView) findViewById(R.id.fact_text);
    factLabel.setText(mFactBook.getFacts());
//
//    SharedPreferences mPrefs = getSharedPreferences(loadString, 0);
//    loadString = mPrefs.getString("save_tag", SAVE_TAG);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onPause(){


        super.onPause();
        loadString = mFactBook.createOutputString();
//
//        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(SAVE_TAG, loadString);
//        editor.apply();
//
//        SharedPreferences.Editor ed = mPrefs.edit();
//        ed.putString("save_tag", SAVE_TAG);
//        ed.commit();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

//        loadString = mFactBook.createOutputString();
//
//        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(SAVE_TAG, loadString);
//        editor.apply();


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        loadString = mFactBook.createOutputString();
        savedInstanceState.putString(SAVE_TAG, loadString);
        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();

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

    private void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();

        final View v = inflater.inflate(R.layout.add_fact, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(v);
        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("What's something you like about yourself?");
        builder.setTitle("I like my...");



        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                EditText input = (EditText) v.findViewById(R.id.new_item_name);
                String string = input.getText().toString();

               mFactBook.addFact(string);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();


    }


    public void vFact(View view){
        TextView factLabel = (TextView) findViewById(R.id.fact_text);
        ImageView picView = (ImageView) findViewById(R.id.cute_pic);
        String statement = mFactBook.getFacts();
        factLabel.setText(statement);
        int picId = mPhotoAlbum.getPicId();
        picView.setImageResource(picId);
    }

    public void nFact(View view){
        createDialog();
    }

    public void rFact(View view){
        mFactBook.removeFact();
    }
}
