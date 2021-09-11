package com.xyxy.coversafetyapp.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.xyxy.coversafetyapp.R;
import com.xyxy.coversafetyapp.ui.adapter.ViewPage2MainAdapter;
import com.xyxy.coversafetyapp.ui.fragment.NoteFragment;
import com.xyxy.coversafetyapp.ui.fragment.SettingFragment;
import com.xyxy.coversafetyapp.ui.fragment.WarningFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerMain;
    private Toolbar mToolbarMain;
    private ViewPager mViewpageMain;
    private RadioGroup mRdgupMain;
    private RadioButton mRdbtnSetting;
    private RadioButton mRdbtnNote;
    private RadioButton mRdbtnWarning;
    private NavigationView mActivityNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setFragmentSwitch();
        setPageChange();
        setToolBarAndDrawer();

    }

    private void setToolBarAndDrawer() {
        setSupportActionBar(mToolbarMain);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_open_24);
        //
        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.nav_header_view, null);
        mActivityNavigationView.addHeaderView(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerMain.openDrawer(GravityCompat.START);
                break;
            case R.id.menu_search:
                break;
            case R.id.menu_share:
                break;
            default:
                break;
        }
        return true;
    }

    private void setFragmentSwitch() {
        //
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(SettingFragment.newInstance());
        fragments.add(NoteFragment.newInstance());
        fragments.add(WarningFragment.newInstance());
        ViewPage2MainAdapter viewPage2MainAdapter = new ViewPage2MainAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments);
        mViewpageMain.setAdapter(viewPage2MainAdapter);
        mViewpageMain.setOffscreenPageLimit(3);
        //
        switchToolNav("查找井盖", mRdbtnSetting);
        mRdgupMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mRdbtnSetting.getId()) {
                    mViewpageMain.setCurrentItem(0);
                }
                if (checkedId == mRdbtnNote.getId()) {
                    mViewpageMain.setCurrentItem(1);
                }
                if (checkedId == mRdbtnWarning.getId()) {
                    mViewpageMain.setCurrentItem(2);
                }
            }
        });
    }

    private void setPageChange() {
        //
        mViewpageMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //页面改变
                switch (position) {
                    case 0:
                        switchToolNav("查找井盖", mRdbtnSetting);
                        break;
                    case 1:
                        switchToolNav("井盖日志", mRdbtnNote);
                        break;
                    case 2:
                        switchToolNav("井盖警报", mRdbtnWarning);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void switchToolNav(String title, RadioButton radioButton) {
        mToolbarMain.setTitle(title);
        radioButton.setChecked(true);
    }

    private void initView() {
        mDrawerMain = (DrawerLayout) findViewById(R.id.drawer_main);
        mToolbarMain = (Toolbar) findViewById(R.id.toolbar_main);
        mViewpageMain = (ViewPager) findViewById(R.id.viewpage_main);
        mRdgupMain = (RadioGroup) findViewById(R.id.rdgup_main);
        mRdbtnSetting = (RadioButton) findViewById(R.id.rdbtn_setting);
        mRdbtnNote = (RadioButton) findViewById(R.id.rdbtn_note);
        mRdbtnWarning = (RadioButton) findViewById(R.id.rdbtn_warning);
        mActivityNavigationView = (NavigationView) findViewById(R.id.activity_NavigationView);
    }
}