package br.com.halyson.materialdesign.activity;

/**
 * Created by Alif on 6/30/15.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import br.com.halyson.materialdesign.R;
//http://localhost/sistemsaman/pengawal/view-user.php?nodaftar=wek110033
//{"status":"success","detailpengguna":{"id":"1","nomatrik":"wek110033","noic":"920810105421",
//        "nama":"Alif Jamaluddin","fakulti":"FSKTM","alamat":"Petaling Jaya","notel":"0123456789",
//        "email":"alif@power.com"}}
public class ResultCarianMatrik extends Activity {
    private TextView nomatrik;
    private TextView noic;
    private TextView nama;
    private TextView fakulti;
    private TextView alamat;
    private TextView notel;
    private TextView email;
    private JSONObject jsonObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_nomatrik);
        initVar();
        Intent intent = getIntent();

        String jsonString = intent.getStringExtra("jsonObject");
        try {
            jsonObj = new JSONObject(jsonString);
            JSONObject jsonMainArr = jsonObj.getJSONObject("detailpengguna");

            nomatrik.setText(jsonMainArr.getString("nomatrik"));
            noic.setText(jsonMainArr.getString("noic"));
            nama.setText(jsonMainArr.getString("nama"));
            fakulti.setText(jsonMainArr.getString("fakulti"));
            alamat.setText(jsonMainArr.getString("alamat"));
            notel.setText(jsonMainArr.getString("notel"));
            email.setText(jsonMainArr.getString("email"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void initVar(){
        nomatrik= (TextView)findViewById(R.id.textView32);
        noic = (TextView)findViewById(R.id.textView34);
        nama = (TextView)findViewById(R.id.textView36);
        fakulti = (TextView)findViewById(R.id.textView38);
        alamat = (TextView)findViewById(R.id.textView40);
        notel = (TextView)findViewById(R.id.textView42);
        email = (TextView)findViewById(R.id.textView44);
    }


}
