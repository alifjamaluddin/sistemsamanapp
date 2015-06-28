package br.com.halyson.materialdesign.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.halyson.materialdesign.R;

/**
 * Created by Alif on 6/28/15.
 */
public class Fragment5 extends Fragment {
    public static Fragment5 newInstance() {
        return new Fragment5();
    }
    private View mViewFragment4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewFragment4 = inflater.inflate(R.layout.fragment_5, container, false);

        return mViewFragment4;
    }
}
