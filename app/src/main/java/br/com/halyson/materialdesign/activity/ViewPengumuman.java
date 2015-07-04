package br.com.halyson.materialdesign.activity;


/**
 * Created by Alif on 7/4/15.
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
//{"status":"success","detailkenderaan":{"id":"4","noplat":"ASD","jenis":"MOTOR",
//        "jenama":"ASD","warna":"ASD","isiankebuk":"ASD","nolesen":"ASD",
//        "nomatrik":"ASDASD","tarikhdaftar":"2015-06-20 15:08:07","tamatlesen":"2015-06-25 00:00:00",
//        "tamatcukai":"2015-06-20 00:00:00","status":"Lulus"}}
public class ViewPengumuman extends Activity {
    private TextView noPlat;
    private TextView jenis;
    private TextView jenama;
    private TextView warna;
    private TextView isiankebuk;
    private TextView nolesen;
    private TextView nomatrik;
    private TextView tamatlesen;
    private TextView tamatcukai;
    private TextView tarikhdaftar;
    private JSONObject jsonObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_noplat);
        initVar();
        Intent intent = getIntent();

        String jsonString = intent.getStringExtra("jsonObject");
        try {
            jsonObj = new JSONObject(jsonString);
            JSONObject jsonMainArr = jsonObj.getJSONObject("detailkenderaan");

            noPlat.setText(jsonMainArr.getString("noplat"));
            jenis.setText(jsonMainArr.getString("jenis"));
            jenama.setText(jsonMainArr.getString("jenama"));
            warna.setText(jsonMainArr.getString("warna"));
            isiankebuk.setText(jsonMainArr.getString("isiankebuk"));
            nolesen.setText(jsonMainArr.getString("nolesen"));
            nomatrik.setText(jsonMainArr.getString("nomatrik"));
            tamatlesen.setText(jsonMainArr.getString("tamatlesen"));
            tamatcukai.setText(jsonMainArr.getString("tamatcukai"));
            tarikhdaftar.setText(jsonMainArr.getString("tarikhdaftar"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void initVar(){
        noPlat = (TextView)findViewById(R.id.textView11);
        jenis = (TextView)findViewById(R.id.textView13);
        jenama= (TextView)findViewById(R.id.textView15);
        warna= (TextView)findViewById(R.id.textView17);
        isiankebuk= (TextView)findViewById(R.id.textView19);
        nolesen= (TextView)findViewById(R.id.textView21);
        nomatrik= (TextView)findViewById(R.id.textView23);
        tamatlesen= (TextView)findViewById(R.id.textView25);
        tamatcukai= (TextView)findViewById(R.id.textView27);
        tarikhdaftar= (TextView)findViewById(R.id.textView29);
    }


}
