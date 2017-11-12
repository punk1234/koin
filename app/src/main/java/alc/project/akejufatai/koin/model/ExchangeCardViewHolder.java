package alc.project.akejufatai.koin.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import alc.project.akejufatai.koin.R;

/**
 * Created by AKEJU  FATAI on 2017-10-15.
 */

public class ExchangeCardViewHolder extends RecyclerView.ViewHolder {

    private TextView currencyText;
    private TextView amountText;

    public ExchangeCardViewHolder(View itemView){

        super(itemView);

        currencyText = (TextView) itemView.findViewById(R.id.currency_text);
        amountText = (TextView) itemView.findViewById(R.id.amount_text);

    }

    public TextView getCurrencyText() {
        return currencyText;
    }

    public TextView getAmountText() {
        return amountText;
    }
}
