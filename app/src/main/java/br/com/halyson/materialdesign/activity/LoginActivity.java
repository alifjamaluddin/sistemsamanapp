package br.com.halyson.materialdesign.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.halyson.materialdesign.R;
import br.com.halyson.materialdesign.constants.Backend;

/**
 * Created by Alif on 6/28/15.
 */
public class LoginActivity extends Activity{
    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        initVar();
    }

    public void authenticate(View view){
        String paramNoDaftar = username.getText().toString();
        String paramPassword = password.getText().toString();




        Future<String> stringFuture = Ion.with(getApplicationContext())
                .load(Backend.URL + "login.php")
                .setBodyParameter("nodaftar", paramNoDaftar)
                .setBodyParameter("password", paramPassword)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            JSONObject json = new JSONObject(result);
                            String json_result = json.getString("status");
                            if (json_result.equalsIgnoreCase("success")) {

                                int user_id = json.getInt("id");
                                SharedPreferences.Editor editor = getSharedPreferences(Backend.MY_PREFS_NAME, MODE_PRIVATE).edit();
                                editor.putInt("userid", user_id);
                                editor.commit();
                                Intent to_main = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(to_main);
                                finish();

                            } else {

                                String error = json.getString("error");
                                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show(); // This will show the user what went wrong with a toast

                            }
                        } catch (JSONException er) {
                            er.printStackTrace();
                        }
                    }
                });

    }

    private void initVar(){
        username = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);

    }
}
