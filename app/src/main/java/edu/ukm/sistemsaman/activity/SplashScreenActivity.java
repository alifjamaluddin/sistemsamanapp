package edu.ukm.sistemsaman.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import edu.ukm.sistemsaman.R;
import edu.ukm.sistemsaman.constants.Backend;
import edu.ukm.sistemsaman.service.ConnectionStatus;

/**
 * Created by Alif on 6/28/15.
 */
public class SplashScreenActivity extends Activity {

    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ConnectionStatus cs = new ConnectionStatus(getApplicationContext());

        Boolean isInternetPresent = cs.isConnectingToInternet();

        if(isInternetPresent){

            Future<String> stringFuture = Ion.with(getApplicationContext())
                    .load(Backend.URL + "check_status.php")
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            try {
                                JSONObject json = new JSONObject(result);    // Converts the string "result" to a JSONObject
                                String json_result = json.getString("status"); // Get the string "result" inside the Json-object
                                if (json_result.equalsIgnoreCase("success")) { // Checks if the "result"-string is equals to "ok"
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                                            startActivity(i);
                                            finish();
                                        }
                                    }, SPLASH_TIME_OUT);
                                } else {
                                    String error = "Fail connecting server";
                                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show(); // This will show the user what went wrong with a toast

                                }
                            } catch (JSONException er) {
                                er.printStackTrace();
                            }
                        }
                    });



        }else{
            showDialog();

        }



    }

    private void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Connect to wifi or quit")
                .setCancelable(false)
                .setPositiveButton("Connect to WIFI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
