package alc.project.akejufatai.koin.storage;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-11-04.
 */

public class CurrencySharedPreference {

    private Context context;
    private SharedPreferences sharedPreferences;

    public CurrencySharedPreference(Context context){

        this.context = context;
        sharedPreferences = context.getSharedPreferences("currency-store",context.MODE_PRIVATE);

    }

    public void save(String currency, boolean activated){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(currency,activated);
        editor.commit();

    }

    public void save(String currency, String cryptoType, double amount){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(cryptoType+currency,(float) amount);
        editor.commit();

    }

    public String[] getActivatedCurrencies(String[] currencies){

        List<String> activatedCurrencies = new ArrayList<>();
        for(String c : currencies){
            if(isCurrencyActivated(c)){
                activatedCurrencies.add(c);
            }
        }

        String[] activatedCurrenciesArray = new String[activatedCurrencies.size()];
        for(int i = 0; i < activatedCurrencies.size(); i++){
            activatedCurrenciesArray[i] = activatedCurrencies.get(i);
        }

        return activatedCurrenciesArray;

    }

    public List<String> getUnActivatedCurrencies(String[] currencies){

        List<String> activatedCurrencies = new ArrayList<>();
        for(String c : currencies){
            if(!isCurrencyActivated(c)){
                activatedCurrencies.add(c);
            }
        }
        return activatedCurrencies;

    }

    public boolean isCurrencyActivated(String currency){

        boolean activated = sharedPreferences.getBoolean(currency,false);
        return activated;

    }

}
