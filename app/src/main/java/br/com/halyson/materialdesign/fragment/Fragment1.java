package br.com.halyson.materialdesign.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.halyson.materialdesign.R;

/**
 * Created by Alif on 6/30/15.
 */
public class Fragment1 extends Fragment {
    public static Fragment1 newInstance() {
        return new Fragment1();
    }
    private View mViewFragment1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewFragment1 = inflater.inflate(R.layout.fragment_1, container, false);

        return mViewFragment1;
    }
}
