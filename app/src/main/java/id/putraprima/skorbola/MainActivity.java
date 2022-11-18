package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int HOME_REQUEST_CODE = 1;
    private static final int AWAY_REQUEST_CODE = 2;
    private static final String TAG = MainActivity.class.getCanonicalName();

    private String home_name, away_name;
    private Uri home_logo, away_logo;
    private Intent versus;
    private EditText et_home, et_away;
    private ImageView iv_home, iv_away;
    private Button btn_team;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_home = findViewById(R.id.home_team);
        iv_home = findViewById(R.id.home_logo);

        et_away = findViewById(R.id.away_team);
        iv_away = findViewById(R.id.away_logo);

        btn_team = findViewById(R.id.btn_team);

        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), HOME_REQUEST_CODE);
            }
        });

        iv_away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), AWAY_REQUEST_CODE);
            }
        });

        btn_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_name = et_home.getText().toString();
                away_name = et_away.getText().toString();
                versus = new Intent(MainActivity.this, MatchActivity.class);
                versus.putExtra("home_name", home_name);
                versus.putExtra("away_name", away_name);
                if(home_logo != null) {
                    versus.putExtra("home_logo", home_logo);
                }
                if(away_logo != null) {
                    versus.putExtra("away_logo", away_logo);
                }
                startActivity(versus);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Log.d(TAG, "Batal Memilih Gambar");
            return;
        }
        else if(requestCode == HOME_REQUEST_CODE){
            if(data != null){
                try {
                    home_logo = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), home_logo);
                    iv_home.setImageBitmap(bitmap);
                }
                catch (IOException error){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
        else if(requestCode == AWAY_REQUEST_CODE){
            if(data != null){
                try {
                    away_logo = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), away_logo);
                    iv_away.setImageBitmap(bitmap);
                }
                catch (IOException error){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error.getMessage());
                }
            }
        }
    }
}