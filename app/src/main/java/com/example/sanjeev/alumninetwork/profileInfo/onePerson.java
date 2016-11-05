package com.example.sanjeev.alumninetwork.profileInfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.sanjeev.alumninetwork.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class onePerson extends AppCompatActivity {

    static Context my_ap;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static Bitmap bitmap;

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

    public void showFileChooser()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1 && resultCode == RESULT_OK
                && null != data) {
            Uri URI = data.getData();
            String[] FILE = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(URI,
                    FILE, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(FILE[0]);
                String picturepath = cursor.getString(columnIndex);
                File f = new File(picturepath);
                String iname = f.getName();
                cursor.close();
                bitmap = BitmapFactory.decodeFile(picturepath);
                ppp.getBits(bitmap);
            }else
                Log.e("COJH", "CURSOR IS NULL");

        }
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
