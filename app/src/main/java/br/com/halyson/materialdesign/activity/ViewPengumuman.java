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
//{"status":"success","detailkenderaan":{"id":"4","noplat":"ASD","jenis":"MOTOR",
//        "jenama":"ASD","warna":"ASD","isiankebuk":"ASD","nolesen":"ASD",
//        "nomatrik":"ASDASD","tarikhdaftar":"2015-06-20 15:08:07","tamatlesen":"2015-06-25 00:00:00",
//        "tamatcukai":"2015-06-20 00:00:00","status":"Lulus"}}
public class ViewPengumuman extends Activity {
    private TextView tajuk;
    private TextView teks;
    private TextView tarikh;
    private JSONObject jsonObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pengumuman);
        initVar();
        Intent intent = getIntent();

        String jsonString = intent.getStringExtra("jsonObject");
        int itemposition = intent.getIntExtra("itemposition",0);
//        Toast.makeText(this.getApplicationContext(),
//                                                    "POSITION=" + itemposition+" JSONSTRING=" + jsonString, Toast.LENGTH_LONG)
//                                                    .show();
        try {
            jsonObj = new JSONObject(jsonString);
            JSONArray jsonArr = jsonObj.getJSONArray("detail");

            JSONObject pengumuman = jsonArr.getJSONObject(itemposition);

            tajuk.setText(pengumuman.getString("tajuk"));
            teks.setText(pengumuman.getString("teks"));
            tarikh.setText(pengumuman.getString("tarikh"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void initVar(){
        tajuk = (TextView)findViewById(R.id.textView8);
        teks = (TextView)findViewById(R.id.textView45);
        tarikh= (TextView)findViewById(R.id.textView46);
    }


}
