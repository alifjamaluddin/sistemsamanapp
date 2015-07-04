package br.com.halyson.materialdesign.activity;


/**
 * Created by Alif on 7/4/15.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import br.com.halyson.materialdesign.R;
//{"status":"success","idsaman":["9","10","11","12"],
//        "listsaman":[{"id":"9","noplat":"abc123","tempat":"1","catatan":"testing","nomatrik":"wek110033",
//        "kesalahan":"1","tarikhlaporan":"2015-06-28 00:28:46","pelapor":"1"}]}
public class ViewRekod extends Activity {
    private TextView nomatrik;
    private TextView pesalah;
    private TextView noplat;
    private TextView tempat;
    private TextView kesalahan;
    private TextView catatan;
    private TextView tarikh;
    private JSONObject jsonObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_rekod);
        initVar();
        Intent intent = getIntent();

        String jsonString = intent.getStringExtra("jsonObject");
        int itemposition = intent.getIntExtra("itemposition",0);
//        Toast.makeText(this.getApplicationContext(),
//                                                    "POSITION=" + itemposition+" JSONSTRING=" + jsonString, Toast.LENGTH_LONG)
//                                                    .show();
        try {
            jsonObj = new JSONObject(jsonString);
            JSONArray jsonArr = jsonObj.getJSONArray("listsaman");

            JSONObject rekod = jsonArr.getJSONObject(itemposition);

            nomatrik.setText(rekod.getString("nomatrik"));
            pesalah.setText(rekod.getString("pesalah"));
            noplat.setText(rekod.getString("noplat"));
            tempat.setText(rekod.getString("tempat"));
            kesalahan.setText(rekod.getString("kesalahan"));
            catatan.setText(rekod.getString("catatan"));
            tarikh.setText(rekod.getString("tarikhlaporan"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void initVar(){
        nomatrik = (TextView)findViewById(R.id.textView49);
        pesalah = (TextView)findViewById(R.id.textView51);
        noplat = (TextView)findViewById(R.id.textView53);
        tempat = (TextView)findViewById(R.id.textView55);
        kesalahan = (TextView)findViewById(R.id.textView57);
        catatan = (TextView)findViewById(R.id.textView59);
        tarikh = (TextView)findViewById(R.id.textView61);
    }


}
