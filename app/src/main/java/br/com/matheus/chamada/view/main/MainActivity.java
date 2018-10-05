package br.com.matheus.chamada.view.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.com.matheus.chamada.R;
import br.com.matheus.chamada.databinding.ActivityMainBinding;
import br.com.matheus.chamada.view.main.account.AccountFragment;
import br.com.matheus.chamada.view.main.calendar.CalendarFragment;
import br.com.matheus.chamada.view.main.call.CallFragment;
import br.com.matheus.chamada.view.main.calendar.CalendarListener;

public class MainActivity extends AppCompatActivity implements CalendarListener {

    private ActivityMainBinding binding;
    private Fragment previousFragment;
    private FabClickListener fabClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        fabClickListener = CalendarFragment.getInstance();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.flMain, AccountFragment.getInstance())
                .hide(AccountFragment.getInstance())
                .add(R.id.flMain, CalendarFragment.getInstance())
                .hide(CalendarFragment.getInstance())
                .add(R.id.flMain, CallFragment.getInstance())
                .commitNow();

        if (savedInstanceState == null) {
            changeFragment(CallFragment.getInstance());
        }

        binding.bnvMain.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bnv_list:
                    if (binding.bnvMain.getSelectedItemId() != R.id.bnv_list) {
                        changeFragment(CallFragment.getInstance());
                        getSupportActionBar().setTitle(R.string.app_name);
                        binding.fabMain.setImageResource(R.drawable.ic_done);
                        binding.fabMain.show();
                    }
                    return true;
                case R.id.bnv_calendar:
                    if (binding.bnvMain.getSelectedItemId() != R.id.bnv_calendar) {
                        changeFragment(CalendarFragment.getInstance());
                        getSupportActionBar().setTitle(R.string.calendar);
                        binding.fabMain.setImageResource(R.drawable.ic_find_today);
                        binding.fabMain.show();
                    }
                    return true;
                case R.id.bnv_account:
                    if (binding.bnvMain.getSelectedItemId() != R.id.bnv_account) {
                        changeFragment(AccountFragment.getInstance());
                        getSupportActionBar().setTitle(R.string.account);
                        binding.fabMain.hide();
                    }
                    return true;
            }
            return false;
        });

        binding.fabMain.setOnClickListener(v -> {
            // fazer algo ainda talvez
            // fabClickListener.onClickListener();
        });

    }

    public void changeFragment(Fragment fragment) {
        if (previousFragment != null){
            getSupportFragmentManager().beginTransaction().hide(previousFragment).commit();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .show(fragment)
                .commitNow();
        previousFragment = fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ic_settings:
                SettingsDialogFragment.newInstance().show(getSupportFragmentManager());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onShowCalendar() {
        binding.fabMain.show();
    }

    @Override
    public void onHideCalendar() {
        binding.fabMain.hide();
    }
}
