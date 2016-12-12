package com.example.sanjeev.alumninetwork.profileInfo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.example.sanjeev.alumninetwork.R;
import java.util.ArrayList;
import java.util.List;


//This class is the tab layout activity for three fragments aboutme, projectdetails and postsbyPerson.
public class onePerson extends AppCompatActivity {

    static Context my_ap;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int[] tabIcons = {
            R.drawable.pic1,
            R.drawable.alumni,
    };
    private String[]  tabText = {"About me","Project Details", "Posts"};

    aboutMe ppp = new aboutMe();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
      toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        my_ap = getApplicationContext();
        try
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        catch (NullPointerException e)
        {
            e.getCause();
        }


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTextIcons();

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private void setupTextIcons()
    {
        tabLayout.getTabAt(0).setText(tabText[0]);
        tabLayout.getTabAt(1).setText(tabText[1]);
        tabLayout.getTabAt(2).setText(tabText[2]);


    }

    public static Context get_context()
    {
        return my_ap;
    }





    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(ppp, "ONE");
        adapter.addFragment(new projectsDetails(), "TWO");
        adapter.addFragment(new postsByPerson(), "THREE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
