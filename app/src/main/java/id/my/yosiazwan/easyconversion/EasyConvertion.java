package id.my.yosiazwan.easyconversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EasyConvertion extends AppCompatActivity {

    //Define List of conversion here
    private String[] ConversionName = {
            "Select Conversion",
            "Km - Mile",
            "Foot - Milimeter",
            "Milimeter - Foot"
    };

    //Define list of conversion factor here and in accordance sequence above
    private double[] ConversionFactor = {
            0,
            0.621371192237,
            1.60934400000087,
            304.8,
            0.0032808398950131
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_convertion);

        //Add spinner item list from array
        Spinner spinner = (Spinner)findViewById(R.id.namaKonversi);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.ConversionName); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        //Add listener to spinner
        spinner.setOnItemSelectedListener(myListener);

        //Add key listener to editText;
        EditText value1 = (EditText)findViewById(R.id.valueSatu);
        value1.addTextChangedListener(myTextWatcher);
    }

    //Listener for Spinner (Select Box)
    private AdapterView.OnItemSelectedListener myListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            //Set default action for EditText
            EditText v1 = (EditText)findViewById(R.id.valueSatu);
            EditText v2 = (EditText)findViewById(R.id.valueDua);
            v1.setHint("");
            v2.setHint("");
            v1.setText("");
            v2.setText("");
            v2.setEnabled(false);
            v1.setEnabled(false);
            
            
            if(parent.getSelectedItemPosition()>0){
                //Store conversion name to array
                String[] u = parent.getSelectedItem().toString().split("\\ - ");

                v1 = (EditText)findViewById(R.id.valueSatu);
                v1.setText("");
                v1.setHint(u[0] + "");
                v1.setEnabled(true);

                v2 = (EditText)findViewById(R.id.valueDua);
                v2.setText("");
                v2.setHint(u[1] + "");
                v2.setEnabled(true);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    //Listener for text change on Edit Text
    private TextWatcher myTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Spinner spinner = (Spinner)findViewById(R.id.namaKonversi);
            EditText v = (EditText)findViewById(R.id.valueDua);
            v.setText("");
            if(spinner.getSelectedItemPosition()>0){
                double r = 0;
                if(!(s.toString() == null) && !(s.toString().equals(""))){
                    r = ConversionFactor[spinner.getSelectedItemPosition()] * Double.parseDouble(s.toString());
                    v.setText(String.valueOf(r));
                }
            }
        }
    };

    //Listener for text change on Edit Text
    private TextWatcher myTextWatcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Spinner spinner = (Spinner)findViewById(R.id.namaKonversi);
            EditText v = (EditText)findViewById(R.id.valueSatu);
            v.setText("");
            if(spinner.getSelectedItemPosition()>0){
                double r = 0;
                if(!(s.toString() == null) && !(s.toString().equals(""))){
                    r = (1 / ConversionFactor[spinner.getSelectedItemPosition()]) * Double.parseDouble(s.toString());
                    v.setText(String.valueOf(r));
                }
            }
        }
    };
}
