package alc.project.akejufatai.koin.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import alc.project.akejufatai.koin.ExchangeActivity;
import alc.project.akejufatai.koin.api.DataRetriever;
import alc.project.akejufatai.koin.config.Config;
import alc.project.akejufatai.koin.model.ExchangeCard;
import alc.project.akejufatai.koin.model.ExchangeCardAdapter;
import alc.project.akejufatai.koin.model.ExchangeCardCollection;

/**
 * Created by AKEJU  FATAI on 2017-11-04.
 */

public class KoinService extends IntentService {

    private Context context;

    public KoinService() {
        super("KoinService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            final String[] activatedCurrencies = ExchangeActivity.getActivatedCurrencies();

            if(activatedCurrencies.length != 0){

                final JSONObject exchangeJsonObject = DataRetriever.getExchangeRates(Config.fromCurrencies, activatedCurrencies);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject btcJsonObject = exchangeJsonObject.getJSONObject("BTC");
                            JSONObject ethJsonObject = exchangeJsonObject.getJSONObject("ETH");
                            ExchangeCardCollection btcExchangeCardCollection = new ExchangeCardCollection();
                            ExchangeCardCollection ethExchangeCardCollection = new ExchangeCardCollection();

                            for(String c : activatedCurrencies){
                                ExchangeCard btcExchangeCard = new ExchangeCard(c,btcJsonObject.getDouble(c));
                                ExchangeCard ethExchangeCard = new ExchangeCard(c,ethJsonObject.getDouble(c));
                                btcExchangeCardCollection.add(btcExchangeCard);
                                ethExchangeCardCollection.add(ethExchangeCard);
                            }

                            ExchangeActivity.getProgressBar().setVisibility(View.INVISIBLE);

                            ExchangeCardAdapter btcExchangeCardAdapter = new ExchangeCardAdapter(btcExchangeCardCollection,"BTC");
                            ExchangeActivity.getBtcFragment().getRecyclerView().setAdapter(btcExchangeCardAdapter);
                            ExchangeCardAdapter ethExchangeCardAdapter = new ExchangeCardAdapter(ethExchangeCardCollection,"ETH");
                            ExchangeActivity.getEthFragment().getRecyclerView().setAdapter(ethExchangeCardAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
