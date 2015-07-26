package edu.ukm.sistemsaman.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Spinner;

import edu.ukm.sistemsaman.R;
import edu.ukm.sistemsaman.activity.ViewStatistic;

/**
 * Created by Alif on 6/28/15.
 */
public class Fragment5 extends Fragment  implements View.OnClickListener {
    public static Fragment5 newInstance() {
        return new Fragment5();
    }
    private View mViewFragment4;
    Spinner month;
    Button submitBtn;
    int monthVal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewFragment4 = inflater.inflate(R.layout.fragment_5, container, false);
        initValue();
        return mViewFragment4;
    }

    private void initValue(){
        month = (Spinner) mViewFragment4.findViewById(R.id.spinner2);
        submitBtn = (Button)mViewFragment4.findViewById(R.id.button3);
        submitBtn.setOnClickListener(this);
    }
    private void getValue(){
        monthVal = month.getSelectedItemPosition() + 1;
    }

    public void viewStatistic(){
        getValue();
        Intent intent = new Intent(getActivity(), ViewStatistic.class);
        intent.putExtra("statURL", "http://52.74.82.42/admin/statistic.php?month="+monthVal+"&type=month");
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                viewStatistic();
                break;
        }
    }
}
