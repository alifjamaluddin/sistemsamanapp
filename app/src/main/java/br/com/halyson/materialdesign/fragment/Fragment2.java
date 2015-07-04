package br.com.halyson.materialdesign.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.halyson.materialdesign.R;
import br.com.halyson.materialdesign.activity.HomeActivity;
import br.com.halyson.materialdesign.constants.Backend;

/**
 * Created by halyson on 18/12/14.
 */
public class Fragment2 extends Fragment implements View.OnClickListener{
    public static Fragment2 newInstance() {
        return new Fragment2();
    }
    private View mViewFragment2;
    private EditText nomatrik;
    private EditText noplat;
    private EditText catatan;
    private Spinner tempat;
    private Spinner kesalahan;
    private Button submitBtn;
    String strNoMatrik;
    String strNoPlat;
    String strCatatan;
    String strTempat, strKesalahan;
    int idTempat;
    int idKesalahan;
    String userID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewFragment2 = inflater.inflate(R.layout.fragment_2, container, false);
        initVar();
        submitBtn = (Button)mViewFragment2.findViewById(R.id.button5);
        submitBtn.setOnClickListener(this);

        return mViewFragment2;
    }

    private void initVar(){
        nomatrik = (EditText)mViewFragment2.findViewById(R.id.editText3);
        noplat = (EditText)mViewFragment2.findViewById(R.id.editText4);
        catatan = (EditText)mViewFragment2.findViewById(R.id.editText7);
        tempat = (Spinner)mViewFragment2.findViewById(R.id.spinner);
        kesalahan = (Spinner)mViewFragment2.findViewById(R.id.spinner1);

    }

    private void getValue(){

        SharedPreferences prefs = this.getActivity().getSharedPreferences(Backend.MY_PREFS_NAME, this.getActivity().MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            int intUserID = prefs.getInt("userid", 0);
            userID = Integer.toString(intUserID);

        }

        idTempat = tempat.getSelectedItemPosition()+1;
        idKesalahan = kesalahan.getSelectedItemPosition()+1;
        strTempat = Integer.toString(idTempat);
        strKesalahan = Integer.toString(idKesalahan);

        strNoMatrik = nomatrik.getText().toString();
        strNoPlat = noplat.getText().toString();
        strCatatan = catatan.getText().toString();
    }

    public void submitSaman(){
        getValue();
        Future<String> stringFuture = Ion.with(getActivity())
                .load(Backend.URL + "saman.php")
                .setBodyParameter("nomatrik", strNoMatrik)
                .setBodyParameter("noplat", strNoPlat)
                .setBodyParameter("catatan", strCatatan)
                .setBodyParameter("tempat", strTempat)
                .setBodyParameter("kesalahan", strKesalahan)
                .setBodyParameter("pelapor", userID)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            JSONObject json = new JSONObject(result);    // Converts the string "result" to a JSONObject
                            String json_result = json.getString("status"); // Get the string "result" inside the Json-object
                            if (json_result.equalsIgnoreCase("success")) { // Checks if the "result"-string is equals to "ok"
                                String success = json.getString("status");
                                Toast.makeText(getActivity(), success, Toast.LENGTH_LONG).show(); // This will show the user what went wrong with a toast
                            } else {
                                // Result is NOT "OK"
                                String error = json.getString("error");
                                Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show(); // This will show the user what went wrong with a toast

                            }
                        } catch (JSONException er) {
                            // This method will run if something goes wrong with the json, like a typo to the json-key or a broken JSON.
                            er.printStackTrace();
                        }
                    }
                });

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button5:
                submitSaman();
                break;
        }
    }
}
