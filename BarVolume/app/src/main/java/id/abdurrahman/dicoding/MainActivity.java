package id.abdurrahman.dicoding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editWidth;
    private EditText editHeight;
    private EditText editLenght;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWidth = findViewById(R.id.edt_width);
        editHeight = findViewById(R.id.edt_height);
        editLenght = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.edt_calculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.edt_calculate) ;
        {
            String inputLength = editLenght.getText().toString().trim();
            String inputHeight = editHeight.getText().toString().trim();
            String inputWidth = editWidth.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                editLenght.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                editHeight.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                editWidth.setError("Field ini tidak boleh kosong");
            }

            Double length = toDouble(inputLength);
            Double height = toDouble(inputHeight);
            Double width = toDouble(inputWidth);

            if (length == null) {
                isInvalidDouble = true;
                editLenght.setError("Field ini harus berupa nomor yang valid");
            }

            if (height == null) {
                isInvalidDouble = true;
                editHeight.setError("Field ini harus berupa nomor yang valid");
            }

            if (width == null) {
                isInvalidDouble = true;
                editWidth.setError("Field ini harus berupa nomor yang valid");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                double Volume = length * width * height;
                tvResult.setText(String.valueOf(Volume));
            }
        }
    }

    private Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }


    }
    private static final String STATE_RESULT = "state result";

    protected  void onSavaInstanceState (Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }
}

