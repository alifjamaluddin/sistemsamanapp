package edu.ukm.sistemsaman.activity;

import android.os.Bundle;

import edu.ukm.sistemsaman.R;
import edu.ukm.sistemsaman.activity.api.BaseActivity;
import edu.ukm.sistemsaman.constants.FragmentNames;
import edu.ukm.sistemsaman.fragment.HomeFragment;


public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.screen_default_container, new HomeFragment(), FragmentNames.FRAGMENT_HOME_).commit();
        }
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.screen_default;
    }

    @Override
    protected int getTitleToolBar() {
        return R.string.app_name;
    }

}
