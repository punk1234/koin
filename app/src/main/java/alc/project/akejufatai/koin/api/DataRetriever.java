package alc.project.akejufatai.koin.api;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AKEJU  FATAI on 2017-10-27.
 */

public class DataRetriever {

    private static String baseUrl = "https://min-api.cryptocompare.com/data/pricemulti?";
    private static final String app = "koin";

    public static JSONObject getExchangeRates(String[] fromCurrency, String[] toCurrency) throws Exception {

        URL urlObject = new URL(buildUrl(fromCurrency,toCurrency,app));
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlObject.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer output = new StringBuffer();

            while((inputLine = bufferedReader.readLine()) != null){
                output.append(inputLine);
            }

            bufferedReader.close();

            return new JSONObject(output.toString());

        }
        else{
            throw new Exception();
        }

    }

    private static String buildUrl(String[] fromCurrency, String[] toCurrency, String app){

        StringBuilder urlQuery = new StringBuilder(baseUrl);
        urlQuery.append(buildQueryParameter("fsyms",fromCurrency));
        urlQuery.append("&");
        urlQuery.append(buildQueryParameter("tsyms",toCurrency));
        urlQuery.append("&");
        urlQuery.append("extraParams="+app);

        return urlQuery.toString();

    }

    private static String buildQueryParameter(String parameterName, String[] items){

        if(items.length > 0){

            StringBuilder qParameterString = new StringBuilder();
            qParameterString.append(parameterName + "=");
            int lastIndex = items.length - 1;

            for(int index = 0; index < lastIndex; index++){
                qParameterString.append(items[index]);
                qParameterString.append(",");
            }
            qParameterString.append(items[lastIndex]);
            return qParameterString.toString();

        }
        throw new IllegalArgumentException();

    }

}
