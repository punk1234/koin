package alc.project.akejufatai.koin.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import alc.project.akejufatai.koin.R;

/**
 * Created by AKEJU  FATAI on 2017-11-12.
 */

public class CurrencySelectionViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private CheckBox checkBox;

    public CurrencySelectionViewHolder(View itemView){

        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.exchange_sign);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkbox_s);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(!checkBox.isChecked());
            }
        });

    }

    public TextView getTextView() {
        return textView;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

}
