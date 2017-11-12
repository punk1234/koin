package alc.project.akejufatai.koin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ConversionActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private double exchangeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        Bundle bundle = getIntent().getExtras();
        String fromCurrency = bundle.getString("from");
        String toCurrency = bundle.getString("to");
        exchangeValue = bundle.getDouble("exchange_rate");

        TextView fromCurrencyTextView = (TextView) findViewById(R.id.from_currency);
        fromCurrencyTextView.setText(fromCurrency);
        TextView toCurrencyTextView = (TextView) findViewById(R.id.to_currency);
        toCurrencyTextView.setText(toCurrency);

        textView = (TextView) findViewById(R.id.convert_value);

        editText = (EditText) findViewById(R.id.edit);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // String value = editText.getText().toString();

                double valueDecimal = Double.parseDouble(charSequence.toString());
                Double result = valueDecimal * exchangeValue;
                textView.setText(String.format("%,.2f",result));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

}
