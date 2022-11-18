package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView tv_result;
    private Intent result;
    private String hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = getIntent();
        hasil = result.getStringExtra("hasil");
        tv_result = findViewById(R.id.tv_result);
        tv_result.setText("Pemenangnya adalah\n" + hasil);
    }
}