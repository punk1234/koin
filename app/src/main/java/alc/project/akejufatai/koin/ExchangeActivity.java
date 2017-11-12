package alc.project.akejufatai.koin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import alc.project.akejufatai.koin.config.Config;
import alc.project.akejufatai.koin.receiver.AlarmReceiver;
import alc.project.akejufatai.koin.storage.CurrencySharedPreference;

public class ExchangeActivity extends AppCompatActivity {

    private static ProgressBar progressBar;
    private static ExchangeFragment btcFragment,ethFragment;
    private static TextView emptyCardsTextView;
    private ViewPager viewPager;
    private static String[] activatedCurrencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emptyCardsTextView = (TextView) findViewById(R.id.empty_cards_display);

        btcFragment = new ExchangeFragment();
        ethFragment = new ExchangeFragment();

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("BITCOIN"));
        tabLayout.addTab(tabLayout.newTab().setText("ETHERIUM"));

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        MainFragmentStatePageAdapter adapter = new MainFragmentStatePageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                TabLayout.Tab t = tabLayout.getTabAt(position);
                t.select();
            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ExchangeActivity.this,CurrencySelectionActivity.class);
                startActivity(intent);

            }
        });

        refresh();

    }

    public static ProgressBar getProgressBar(){
        return progressBar;
    }

    private class ExchangeTabListener implements ActionBar.TabListener{

        private RecyclerView recyclerView;

        public ExchangeTabListener(RecyclerView recyclerView){

            this.recyclerView = recyclerView;

        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }

    public static String[] getActivatedCurrencies(){
        return activatedCurrencies;
    }

    public static ExchangeFragment getBtcFragment(){
        return btcFragment;
    }

    public static ExchangeFragment getEthFragment(){
        return ethFragment;
    }

    public static TextView getEmptyCardsTextView(){

        return emptyCardsTextView;

    }

    private void refresh(){

        CurrencySharedPreference currencySharedPreference= new CurrencySharedPreference(this);
        this.activatedCurrencies = currencySharedPreference.getActivatedCurrencies(Config.toCurrencies);

        if(activatedCurrencies.length == 0){
            progressBar.setVisibility(View.INVISIBLE);
            emptyCardsTextView.setVisibility(View.VISIBLE);
        }
        else{
            Intent receiverIntent = new Intent(this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,receiverIntent,PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),1,pendingIntent);
        }

    }

    public class MainFragmentStatePageAdapter extends FragmentStatePagerAdapter {

        public MainFragmentStatePageAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return btcFragment;
            }
            else{
                return ethFragment;
            }

        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public static class ExchangeFragment extends Fragment{

        private RecyclerView recyclerView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_layout,container,false);
            recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

            return rootView;

        }

        public RecyclerView getRecyclerView(){
            return recyclerView;
        }
    }
}
