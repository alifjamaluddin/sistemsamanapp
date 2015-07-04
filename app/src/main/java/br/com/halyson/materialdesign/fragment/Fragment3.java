package br.com.halyson.materialdesign.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.halyson.materialdesign.R;
import br.com.halyson.materialdesign.activity.ResultCarianMatrik;
import br.com.halyson.materialdesign.activity.ResultCarianPlat;
import br.com.halyson.materialdesign.constants.Backend;

/**
 * Created by halyson on 18/12/14.
 */
public class Fragment3 extends Fragment implements View.OnClickListener{
    public static Fragment3 newInstance() {
        return new Fragment3();
    }
    private View mViewFragment3;
    private Button submitCarianMatrik;
    private Button submitCarianPlat;
    private EditText noMatrik;
    private EditText noPlat;
    String strNoMatrik, strNoPlat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewFragment3 = inflater.inflate(R.layout.fragment_3, container, false);
        submitCarianPlat = (Button)mViewFragment3.findViewById(R.id.button2);
        submitCarianPlat.setOnClickListener(this);
        submitCarianMatrik = (Button)mViewFragment3.findViewById(R.id.button6);
        submitCarianMatrik.setOnClickListener(this);
        initVar();
        return mViewFragment3;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button6:
                listCarianNoMatrik();
                break;
            case R.id.button2:
                listCarianNoPlat();
                break;
        }
    }

    public void listCarianNoMatrik(){
        strNoMatrik = noMatrik.getText().toString();
        Toast.makeText(this.getActivity(), "No Matrik: " + strNoMatrik, Toast.LENGTH_LONG).show();

        Future<String> stringFuture = Ion.with(getActivity())
                .load(Backend.URL + "view-user.php?nodaftar="+strNoMatrik)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            JSONObject json = new JSONObject(result);    // Converts the string "result" to a JSONObject
                            String json_result = json.getString("status"); // Get the string "result" inside the Json-object
                            if (json_result.equalsIgnoreCase("success")) { // Checks if the "result"-string is equals to "ok"
                                Intent intent = new Intent(getActivity(), ResultCarianMatrik.class);
                                intent.putExtra("jsonObject", json.toString());
                                startActivity(intent);
                            } else {
                                String error = json.getString("error");
                            }
                        } catch (JSONException er) {
                            er.printStackTrace();
                        }
                    }
                });
    }

    public void listCarianNoPlat(){
        strNoPlat = noPlat.getText().toString();
        Toast.makeText(this.getActivity(), "No plat: " + strNoPlat, Toast.LENGTH_LONG).show();
        //get JSON RESULT
        Future<String> stringFuture = Ion.with(getActivity())
                .load(Backend.URL + "view-kenderaan.php?noplat="+strNoPlat)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            JSONObject json = new JSONObject(result);    // Converts the string "result" to a JSONObject
                            String json_result = json.getString("status"); // Get the string "result" inside the Json-object
                            if (json_result.equalsIgnoreCase("success")) { // Checks if the "result"-string is equals to "ok"
                                Intent intent = new Intent(getActivity(), ResultCarianPlat.class);
                                intent.putExtra("jsonObject", json.toString());
                                startActivity(intent);
                            } else {
                                String error = json.getString("error");
                            }
                        } catch (JSONException er) {
                            er.printStackTrace();
                        }
                    }
                });
    }

    public void initVar(){
        noMatrik = (EditText)mViewFragment3.findViewById(R.id.editText5);
        noPlat = (EditText)mViewFragment3.findViewById(R.id.editText6);
    }
}
