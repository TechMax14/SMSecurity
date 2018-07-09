package com.SeniorProject.max.SMSecurity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LockOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button lockButton = (Button) findViewById(R.id.lockButton);
        final Button unlockButton = (Button) findViewById(R.id.unlockButton);

        View.OnClickListener lockListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LockOptions.this, "Lock function call", Toast.LENGTH_SHORT).show();
                new lockClient().execute("HIGH");
            }
        };
        lockButton.setOnClickListener(lockListener);

        View.OnClickListener unlockListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LockOptions.this, "Unlock function call", Toast.LENGTH_SHORT).show();
                new unlockClient().execute("HIGH");
            }
        };
        unlockButton.setOnClickListener(unlockListener);
    }

    class lockClient extends AsyncTask<String, Void, String> {
        public String doInBackground(String... IO) {

            // Predefine variables
            String io = new String(IO[0]);
            URL url;

            try {
                // Stuff variables
                url = new URL(" https://api.particle.io/v1/devices/1f0036000b47363330353437/action");
                String param = "access_token=37018fb81dd41ff705b6f29b8b8083c46847674f&args=lock";

                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

                con.setReadTimeout(7000);
                con.setConnectTimeout(7000);
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setInstanceFollowRedirects(false);
                con.setRequestMethod("POST");
                con.setFixedLengthStreamingMode(param.getBytes().length);
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Send
                PrintWriter out = new PrintWriter(con.getOutputStream());
                out.print(param);
                out.close();

                con.connect();

                BufferedReader in = null;
                if (con.getResponseCode() != 200) {
                    in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                } else {
                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } //; was there


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            // Set null and we´e good to go
            return null;
        }
    }


    class unlockClient extends AsyncTask<String, Void, String> {
        public String doInBackground(String... IO) {

            // Predefine variables
            String io = new String(IO[0]);
            URL url;

            try {
                // Stuff variables
                url = new URL(" https://api.particle.io/v1/devices/1f0036000b47363330353437/action");
                String param = "access_token=37018fb81dd41ff705b6f29b8b8083c46847674f&args=unlock";
                // Open a connection using HttpURLConnection
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

                con.setReadTimeout(7000);
                con.setConnectTimeout(7000);
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setInstanceFollowRedirects(false);
                con.setRequestMethod("POST");
                con.setFixedLengthStreamingMode(param.getBytes().length);
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Send
                PrintWriter out = new PrintWriter(con.getOutputStream());
                out.print(param);
                out.close();

                con.connect();

                BufferedReader in = null;
                if (con.getResponseCode() != 200) {
                    in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                } else {
                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                }


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            // Set null and we´e good to go
            return null;
        }
    }

}


// https://community.particle.io/t/example-android-application-post-get/9355
// a guide on how to signal methods embedded in (flashed on) the photon