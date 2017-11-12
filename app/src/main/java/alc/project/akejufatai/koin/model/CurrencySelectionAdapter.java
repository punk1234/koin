package alc.project.akejufatai.koin.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import alc.project.akejufatai.koin.R;

/**
 * Created by AKEJU  FATAI on 2017-11-12.
 */

public class CurrencySelectionAdapter extends RecyclerView.Adapter<CurrencySelectionViewHolder> {

    private CurrencyCollection currencyCollection;

    public CurrencySelectionAdapter(CurrencyCollection currencyCollection){

        this.currencyCollection = currencyCollection;

    }

    @Override
    public CurrencySelectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.currency_selection_item_holder,parent,false);
        CurrencySelectionViewHolder createCardViewHolder = new CurrencySelectionViewHolder(view);

        return createCardViewHolder;
    }

    @Override
    public void onBindViewHolder(CurrencySelectionViewHolder holder, int position) {

        String currency = currencyCollection.get(position);
        TextView textView = holder.getTextView();
        textView.setText(currency);
        CheckBox checkBox = holder.getCheckBox();
        checkBox.setChecked(false);

    }

    @Override
    public int getItemCount() {

        return currencyCollection.count();

    }

}
