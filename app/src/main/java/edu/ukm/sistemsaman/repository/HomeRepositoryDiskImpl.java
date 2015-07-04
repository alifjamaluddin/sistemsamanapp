package edu.ukm.sistemsaman.repository;

import android.content.res.Resources;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import edu.ukm.sistemsaman.MaterialDesignApplication;
import edu.ukm.sistemsaman.R;
import edu.ukm.sistemsaman.events.LoadColorsTabsDisk;
import edu.ukm.sistemsaman.events.LoadTitleTabsDisk;
import edu.ukm.sistemsaman.interfaces.home.HomeRepository;
import edu.ukm.sistemsaman.utility.BusProvider;

/**
 * Created by Halyson on 20/01/15.
 */
public class HomeRepositoryDiskImpl implements HomeRepository {
    private static final String TAG = HomeRepositoryDiskImpl.class.getSimpleName();

    public HomeRepositoryDiskImpl() {
        BusProvider.getInstance().register(this);
    }

    @Override
    public void recoverTitleTabs() {
        try {
            List<String> listTitleTabs = Arrays.asList(MaterialDesignApplication.getApplicationCtx().getResources().getStringArray(R.array.fragment_home_sections_tabs_title));

            BusProvider.getInstance().post(new LoadTitleTabsDisk(listTitleTabs));
        } catch (Resources.NotFoundException notFoundExcepetion) {
            Log.e(TAG, "Error Getting The Array", notFoundExcepetion);
        }
    }

    @Override
    public void recoverColorsTabs() {
        BusProvider.getInstance().post(new LoadColorsTabsDisk(getColorTab(), getDividerColorTab(), getIndicatorColorTab()));
    }

    @Override
    public int getColorTab() {
        return MaterialDesignApplication.getApplicationCtx().getResources().getColor(android.R.color.white);

    }

    @Override
    public int getDividerColorTab() {
        return MaterialDesignApplication.getApplicationCtx().getResources().getColor(R.color.theme_dialer_primary);
    }

    @Override
    public int getIndicatorColorTab() {
        return MaterialDesignApplication.getApplicationCtx().getResources().getColor(android.R.color.transparent);
    }

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unregister(this);
    }
}
