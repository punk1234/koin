package alc.project.akejufatai.koin.model;

/**
 * Created by AKEJU  FATAI on 2017-10-28.
 */

public class ExchangeCard {

    private String currency;
    private double amount;

    public ExchangeCard(String currency, double amount){

        this.currency = currency;
        this.amount = amount;

    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

}
