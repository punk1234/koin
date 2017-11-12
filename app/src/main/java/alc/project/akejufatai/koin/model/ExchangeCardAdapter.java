package alc.project.akejufatai.koin.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alc.project.akejufatai.koin.ConversionActivity;
import alc.project.akejufatai.koin.ExchangeActivity;
import alc.project.akejufatai.koin.R;

/**
 * Created by AKEJU  FATAI on 2017-10-28.
 */

public class ExchangeCardAdapter extends RecyclerView.Adapter<ExchangeCardViewHolder> {

    private ExchangeCardCollection exchangeCardCollection;
    private String type;

    public ExchangeCardAdapter(ExchangeCardCollection exchangeCardCollection, String type){

        this.exchangeCardCollection = exchangeCardCollection;
        this.type = type;

    }

    @Override
    public ExchangeCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.exchange_card,parent,false);
        ExchangeCardViewHolder exchangeCardViewHolder = new ExchangeCardViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ConversionActivity.class);

                String amount = ((TextView)view.findViewById(R.id.amount_text)).getText().toString();
                String toCurrency = ((TextView)view.findViewById(R.id.currency_text)).getText().toString();
                String fromCurrency = type;

                intent.putExtra("exchange_rate",Double.parseDouble(amount));
                intent.putExtra("from",fromCurrency);
                intent.putExtra("to",toCurrency);
                context.startActivity(intent);
            }
        });

        return exchangeCardViewHolder;
    }

    @Override
    public void onBindViewHolder(ExchangeCardViewHolder holder, int position) {

        ExchangeCard exchangeCard = exchangeCardCollection.get(position);

        TextView currencyText = holder.getCurrencyText();
        currencyText.setText(exchangeCard.getCurrency());
        TextView amountText = holder.getAmountText();
        String formattedAmount = String.format("%,.2f",exchangeCard.getAmount());
        amountText.setText(formattedAmount);

        amountText.setTextColor(Color.argb(255,170,50,170));

    }

    @Override
    public int getItemCount() {
        return exchangeCardCollection.count();
    }

}
