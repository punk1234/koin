package alc.project.akejufatai.koin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import alc.project.akejufatai.koin.config.Config;
import alc.project.akejufatai.koin.model.CurrencyCollection;
import alc.project.akejufatai.koin.model.CurrencySelectionAdapter;
import alc.project.akejufatai.koin.storage.CurrencySharedPreference;

public class CurrencySelectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_selection);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CurrencySharedPreference currencySharedPreference = new CurrencySharedPreference(this);
        List<String> unActivatedCurrencies = currencySharedPreference.getUnActivatedCurrencies(Config.toCurrencies);

        CurrencyCollection currencyCollection = new CurrencyCollection();
        for(String currency : unActivatedCurrencies){
            currencyCollection.add(currency);
        }

        CurrencySelectionAdapter currencySelectionAdapter = new CurrencySelectionAdapter(currencyCollection);
        recyclerView.setAdapter(currencySelectionAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.currency_selection_menu,menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.done_menu){
            updateActivatedCurrencies();

            Intent intent = new Intent(this,ExchangeActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateActivatedCurrencies(){

        for(int index = 0; index < recyclerView.getChildCount(); index++){
            CheckBox checkBox = (CheckBox) recyclerView.findViewHolderForAdapterPosition(index).itemView.findViewById(R.id.checkbox_s);
            if(checkBox.isChecked()){
                TextView currencyText = (TextView) recyclerView.findViewHolderForAdapterPosition(index).itemView.findViewById(R.id.currency_text);
                String currency = currencyText.getText().toString();

                CurrencySharedPreference currencySharedPreference = new CurrencySharedPreference(this);
                currencySharedPreference.save(currency,true);
            }
        }

    }

}
