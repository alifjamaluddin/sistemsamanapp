package edu.ukm.sistemsaman.interfaces.home;

import com.squareup.otto.Subscribe;

import edu.ukm.sistemsaman.events.LoadColorsTabsDisk;
import edu.ukm.sistemsaman.events.LoadTitleTabsDisk;

/**
 * Created by Halyson on 20/01/15.
 */
public interface HomePresenter {
    void loadSectionsTabs();

    @Subscribe
    void onLoadTitleTabsDiskSuccess(LoadTitleTabsDisk loadTitleTabsDisk);

    @Subscribe
    void onLoadColorTabsDiskSuccess(LoadColorsTabsDisk loadColorsTabsDisk);

    void onDestroy();
}
