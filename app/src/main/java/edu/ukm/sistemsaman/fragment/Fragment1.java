package edu.ukm.sistemsaman.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import edu.ukm.sistemsaman.R;
import edu.ukm.sistemsaman.activity.ViewPengumuman;
import edu.ukm.sistemsaman.constants.Backend;

/**
 * Created by Alif on 6/30/15.
 */
public class Fragment1 extends Fragment {
    private ListView listview;
    public static Fragment1 newInstance() {
        return new Fragment1();
    }
    private View mViewFragment1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewFragment1 = inflater.inflate(R.layout.fragment_1, container, false);
        getPengumuman();
        return mViewFragment1;
    }

    private void getPengumuman(){
        listview = (ListView)mViewFragment1.findViewById(R.id.list);

        Future<String> stringFuture = Ion.with(getActivity())
                .load(Backend.URL + "pengumuman.php")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            final JSONObject json = new JSONObject(result);    // Converts the string "result" to a JSONObject
                            String json_result = json.getString("status"); // Get the string "result" inside the Json-object
                            if (json_result.equalsIgnoreCase("success")) { // Checks if the "result"-string is equals to "ok"

                                JSONArray temp = json.getJSONArray("tajuk");
                                int length = temp.length();
                                if (length > 0) {
                                    String [] newdata = new String [length];
                                    for (int i = 0; i < length; i++) {
                                        newdata[i] = temp.getString(i);
                                    }
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mViewFragment1.getContext(),
                                            android.R.layout.simple_list_item_1, android.R.id.text1, newdata);

                                    listview.setAdapter(adapter);
                                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view,
                                                                int position, long id) {

                                            // ListView Clicked item index
                                            int itemPosition = position;

                                            // ListView Clicked item value
                                            String itemValue = (String) listview.getItemAtPosition(position);
                                            Intent intent = new Intent(getActivity(), ViewPengumuman.class);
                                            intent.putExtra("jsonObject", json.toString());
                                            intent.putExtra("itemposition", itemPosition);
                                            startActivity(intent);
                                            // Show Alert //
//                                            Toast.makeText(mViewFragment1.getContext(),//
//                                                    "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)//
//                                                    .show();

                                        }

                                    });
                                }

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
}
