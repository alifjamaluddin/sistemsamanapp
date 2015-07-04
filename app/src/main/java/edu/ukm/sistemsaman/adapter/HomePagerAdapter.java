package edu.ukm.sistemsaman.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import edu.ukm.sistemsaman.fragment.Fragment1;
import edu.ukm.sistemsaman.fragment.Fragment5;
import edu.ukm.sistemsaman.fragment.DefaultFragment;
import edu.ukm.sistemsaman.fragment.Fragment2;
import edu.ukm.sistemsaman.fragment.Fragment3;
import edu.ukm.sistemsaman.fragment.Fragment4;
/**
 * Created by halyson on 18/12/14.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    private List<String> mListTitleTabs;

    public HomePagerAdapter(List<String> listTitleTabs, FragmentManager childFragmentManager) {
        super(childFragmentManager );
        this.mListTitleTabs = listTitleTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mListTitleTabs == null || mListTitleTabs.isEmpty()) {
            return "";
        }
        return mListTitleTabs.get(position);
    }

    @Override
    public int getCount() {
        if (mListTitleTabs == null || mListTitleTabs.isEmpty()) {
            return 0;
        }
        return mListTitleTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
//                return RecylerViewFragment.newInstance();
                return Fragment1.newInstance();
            case 1:
                return Fragment2.newInstance();
            case 2:
                return Fragment3.newInstance();
            case 3:
                return Fragment4.newInstance();
            case 4:
                return Fragment5.newInstance();
            default:
                return DefaultFragment.newInstance();
        }
    }
}
