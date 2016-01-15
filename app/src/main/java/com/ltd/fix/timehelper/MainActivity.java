package com.ltd.fix.timehelper;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.ltd.fix.timehelper.Adapter.TabAdapter;
import com.ltd.fix.timehelper.Dialog.AddTaskDialogFragment;
import com.ltd.fix.timehelper.Fragments.DoneTaskFragment;
import com.ltd.fix.timehelper.Fragments.SplashFragment;
import com.ltd.fix.timehelper.Fragments.currentTaskFragment;
import com.ltd.fix.timehelper.Models.ModelTask;

public class MainActivity extends AppCompatActivity
        implements AddTaskDialogFragment.AddTaskListener {

    FragmentManager fragmentManager;
    PreferenceHelper preferenceHelper;

    TabAdapter tabAdapter;

    currentTaskFragment currentTaskFragment;
    DoneTaskFragment doneTaskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceHelper.getInstance().init(getApplicationContext());
        preferenceHelper = PreferenceHelper.getInstance();


        fragmentManager = getFragmentManager();

        runSplash();
        setUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem splashItem = menu.findItem(R.id.action_splash);
        splashItem.setChecked(preferenceHelper.getBoolean(PreferenceHelper.SPLASH_INVISIBLE));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.action_splash) {
            menuItem.setChecked(!menuItem.isChecked());
            preferenceHelper.putBoolean(PreferenceHelper.SPLASH_INVISIBLE, menuItem.isChecked());
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    public void runSplash() {
        if (!preferenceHelper.getBoolean(PreferenceHelper.SPLASH_INVISIBLE)) {
            SplashFragment splashFragment = new SplashFragment();

            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, splashFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void setUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            setSupportActionBar(toolbar);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.current_deal));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.done));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        tabAdapter = new TabAdapter(fragmentManager, 2);

        viewPager.setAdapter(tabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        currentTaskFragment = (currentTaskFragment) tabAdapter.getItem(TabAdapter.CURRENT_TASK_POSITION);
        doneTaskFragment = (DoneTaskFragment) tabAdapter.getItem(TabAdapter.DONE_TASK_POSITION);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment addTask = new AddTaskDialogFragment();
                addTask.show(fragmentManager, "Add Task");
            }
        });
    }

    @Override
    public void onTaskAdded(ModelTask newTask) {
        currentTaskFragment.addTask(newTask);
    }

    @Override
    public void onTaskAddCancel() {
        Toast.makeText(this, "Отмена события", Toast.LENGTH_SHORT).show();
    }
}
