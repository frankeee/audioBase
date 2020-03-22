package com.example.audiosabe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;



public class MainActivity extends AppCompatActivity {




    String message;
    int actual;
    DatabaseHelper myDb;
    ImageButton btneditAudio1,btneditAudio2,btneditAudio3;
    ImageButton enviador1,enviador2,enviador3;
    ImageButton playAudio1,playAudio2,playAudio3;
    ImageButton pauseAudio1,pauseAudio2,pauseAudio3;
    String path;
    Uri pachon;
    Uri pachina;
    SharedPreferences compartidor;
    public static final String misPrefs = "archivoPreferencias";
    MediaPlayer mediaPlayer ;
    TextView juanete;
    SeekBar barra1,barra2,barra3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(MainActivity.this);
        super.onCreate(savedInstanceState);
        btneditAudio1 =  findViewById(R.id.editAudio1);
        btneditAudio2 =  findViewById(R.id.editAudio2);
        btneditAudio3 =  findViewById(R.id.editAudio3);
        playAudio1 = findViewById(R.id.playAudio1);
        playAudio2 = findViewById(R.id.playAudio2);
        playAudio3 = findViewById(R.id.playAudio3);
        enviador1 = findViewById(R.id.sendAudio1);
        enviador2 = findViewById(R.id.sendAudio2);
        enviador3 = findViewById(R.id.sendAudio3);
        pauseAudio1= findViewById(R.id.pause1);
        pauseAudio2= findViewById(R.id.pause2);
        pauseAudio3= findViewById(R.id.pause3);
        mediaPlayer= new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        barra1 = findViewById(R.id.barcita1);
        barra2 = findViewById(R.id.barcita2);
        barra3 = findViewById(R.id.barcita3);
        createData();
        addData();
        createData2();

        enviador1.setOnClickListener(
          new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  sendAudio(1);
              }
          }
        );
        enviador2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendAudio(2);
                    }
                }
        );
        enviador3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendAudio(3);
                    }
                }
        );

        btneditAudio1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        performFileSearch(1);
                    }
                }
        );
        btneditAudio2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        performFileSearch(2);
                    }
                }
        );
        btneditAudio3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        performFileSearch(3);
                    }
                }
        );

        playAudio1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        playAudio(1);


                    }
                }
        );
        playAudio2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playAudio(2);
                    }
                }
        );
        playAudio3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playAudio(3);
                    }
                }
        );
        pauseAudio1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.pause();
                    }
                }

        );
        pauseAudio2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.pause();
                    }
                }

        );
        pauseAudio3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mediaPlayer.pause();
                    }
                }

        );

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //Do the work after completion of audio
                mediaPlayer.seekTo(0);
            }
        });





        barra1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Cursor cursor;
                int num1 =0;
                cursor = myDb.getNumero(barra1.getId());
                if( cursor != null && cursor.moveToFirst() ){ num1 = cursor.getInt(cursor.getColumnIndex("numero"));
                cursor.close();
                }
                if (mediaPlayer != null && fromUser && actual == num1) {
                    mediaPlayer.seekTo(progress * 1000);
                }
            }
        });
        barra2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Cursor cursor;
                int num1 =0;
                cursor = myDb.getNumero(barra2.getId());
                if( cursor != null && cursor.moveToFirst() ){ num1 = cursor.getInt(cursor.getColumnIndex("numero"));
                    cursor.close();
                }
                if (mediaPlayer != null && fromUser && actual == num1) {
                    mediaPlayer.seekTo(progress * 1000);
                }
            }
        });

        barra3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Cursor cursor;
            int num1 =0;
            cursor = myDb.getNumero(barra3.getId());
            if( cursor != null && cursor.moveToFirst() ){ num1 = cursor.getInt(cursor.getColumnIndex("numero"));
                cursor.close();
            }
            if (mediaPlayer != null && fromUser && actual == num1) {
                mediaPlayer.seekTo(progress * 1000);
            }
        }
    });
}


        public Dialog dialogCreator(final String i) {
            // Use the Builder class for convenient dialog construction
            final int h= Integer.parseInt(i);
            Cursor cursor = myDb.getkey(h);
            int num = 0;
            if( cursor != null && cursor.moveToFirst() ){
                num = cursor.getInt(cursor.getColumnIndex("ID"));
                cursor.close();
            }

            final EditText editor = new EditText(this);
            juanete = findViewById(num);
            AlertDialog.Builder constructor = new AlertDialog.Builder(this);
            constructor.setMessage("Elija nombre del audio")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!

                            myDb.updateNombre(h,editor.getText().toString());
                            juanete.setText(editor.getText());
                        }
                    })
            .setView(editor);
            // Create the AlertDialog object and return it
            return constructor.show();
        }


    public void sendAudio(int into){
        Intent intentoEnvio =  new Intent(Intent.ACTION_SEND);
        compartidor = getSharedPreferences(misPrefs, MODE_PRIVATE);
        String s1 = compartidor.getString(Integer.toString(into),"w");
        Uri pate = Uri.parse(s1);
        intentoEnvio.putExtra(Intent.EXTRA_STREAM,pate);
        intentoEnvio.setType("audio/*");  //hay que chequear esta linea porque ahora el usauario puede guardar mas que audio
        startActivity(Intent.createChooser(intentoEnvio,"Audio1"));
}

    public void playAudio(int into){
                        Cursor cursor;
                        int num1 =0;
                        cursor = myDb.getkey2(into);
                        if( cursor != null && cursor.moveToFirst() ){ num1 = cursor.getInt(cursor.getColumnIndex("ID"));
                        cursor.close();
                        }

                        final SeekBar barcix=findViewById(num1);

                        if(mediaPlayer.isPlaying()||(!mediaPlayer.isPlaying() && into !=actual)){
                            mediaPlayer.pause();
                            mediaPlayer.stop();
                            mediaPlayer.reset();
                            compartidor = getSharedPreferences(misPrefs, MODE_PRIVATE);
                            String s1 = compartidor.getString(Integer.toString(into),"w");
                            pachina = Uri.parse(s1);
                            try {
                                mediaPlayer.setDataSource(getApplicationContext(),pachina);
                                mediaPlayer.prepare();
                                mediaPlayer.seekTo(barcix.getProgress()*1000);
                                mediaPlayer.start();
                            }
                            catch(IOException ex){
                                Toast.makeText(MainActivity.this, "Su audio solo puede ser reproducido en Warap o no esta mandando un audio", Toast.LENGTH_LONG).show();
                                ex.printStackTrace();
                            }
                        }
                        else if(!mediaPlayer.isPlaying() && into == actual){
                            int length=mediaPlayer.getCurrentPosition();
                            mediaPlayer.seekTo(barcix.getProgress()*1000);
                            mediaPlayer.start();

                        }
                        actual = into;
                        barcix.setMax(mediaPlayer.getDuration()/1000);
                        final Handler mHandler = new Handler();
                //Make sure you update Seekbar on UI thread
                        MainActivity.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                int id = barcix.getId();
                                final Cursor cursar;
                                int num2 =0;
                                cursar = myDb.getNumero(id);
                                if( cursar != null && cursar.moveToFirst() ){ num2 = cursar.getInt(cursar.getColumnIndex("numero"));
                                    cursar.close();
                                }
                                if(mediaPlayer != null&& actual == num2 ){
                                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                                    barcix.setProgress(mCurrentPosition);
                                }
                                mHandler.postDelayed(this, 1000);
                            }
                        });


    }

    public void createData(){
        final boolean isInserted = myDb.existe("1");
        if(isInserted == false){
            myDb.insertData(R.id.text1,"audio1",1);
            myDb.insertData(R.id.text2,"audio2",2);
            myDb.insertData(R.id.text3,"audio3",3);
        }



    }
    public void createData2(){
        final boolean isInserted = myDb.existe2("1");
        if(isInserted == false){
            myDb.insertData2(R.id.barcita1,1);
            myDb.insertData2(R.id.barcita2,2);
            myDb.insertData2(R.id.barcita3,3);
        }
    }
    public void addData(){
        int num =0;
        int i = 1;
        String nom = "";
        Cursor cursor;
        Cursor wanchi;
        while(i<=3){
            cursor = myDb.getkey(i);
            if( cursor != null && cursor.moveToFirst() ){
                num = cursor.getInt(cursor.getColumnIndex("ID"));
                cursor.close();
            }

            TextView joan = findViewById(num);
            wanchi = myDb.gettextbd(i);
            if( wanchi != null && wanchi.moveToFirst() ){
                nom = wanchi.getString(wanchi.getColumnIndex("nombre"));
                wanchi.close();
            }
            joan.setText(nom);

            i++;
        }
        }





    public void performFileSearch(final int cual) {

                        Intent myFileIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                        myFileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION | Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);


                        // Filter to show only images, using the image MIME data type.
                        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
                        // To search for all documents available via installed storage providers,
                        // it would be "*/*".
                        myFileIntent.setType("*/*");
                        String donde = Integer.toString(cual);
                        message = donde;

                        //onActivityResult(READ_REQUEST_CODE, Activity.RESULT_OK ,intent);
                        startActivityForResult(myFileIntent,10);

                    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        switch (requestCode){
            case 10:
                if(resultCode == RESULT_OK) {

                    path = data.getData().getPath();
                    pachon = data.getData();

                    final int takeFlags = data.getFlags()
                            & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                    getContentResolver().takePersistableUriPermission(pachon, takeFlags);
                    InputStream in = null;
                    try {
                        in = getContentResolver().openInputStream(pachon);

                    } catch (FileNotFoundException e) {
                        Toast.makeText(MainActivity.this, "no encontro el archivo", Toast.LENGTH_LONG).show();

                    }


                    compartidor = getSharedPreferences(misPrefs,MODE_PRIVATE);
                    SharedPreferences.Editor editor = compartidor.edit();
                    editor.putString(message,pachon.toString());
                    editor.commit();

                    dialogCreator(message);
                }
                break;
        }

    }

}



