package br.com.halyson.materialdesign.activity;

import android.app.Activity;
import android.content.Intent;
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
                            JSONObject json = new JSONObject(result);    // Converts the string "result" to a JSONObject
                            String json_result = json.getString("status"); // Get the string "result" inside the Json-object
                            if (json_result.equalsIgnoreCase("success")) { // Checks if the "result"-string is equals to "ok"
                                // Result is "OK"
                                int user_id = json.getInt("id"); // Get the user_id
                                Intent to_main = new Intent(getApplicationContext(), HomeActivity.class); // New intent to MainActivity
                                startActivity(to_main); // Starts HomeActivity
                                finish(); // Add this to prevent the user to go back to this activity when pressing the back button after we've opened HomeActivity

                            } else {
                                // Result is NOT "OK"
                                String error = json.getString("error");
                                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show(); // This will show the user what went wrong with a toast

                            }
                        } catch (JSONException er) {
                            // This method will run if something goes wrong with the json, like a typo to the json-key or a broken JSON.
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
