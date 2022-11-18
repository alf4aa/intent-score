package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {

    private TextView home_team, away_team, tv_home_score, tv_away_score;
    private String home_name, away_name, hasil;
    private Integer home_score, away_score;
    private ImageView home_logo, away_logo;
    private Intent versus, result;
    private Uri home_logo_uri, away_logo_uri;
    private Button home_1, home_2, home_3, away_1, away_2, away_3, btn_reset, btn_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        home_team = findViewById(R.id.txt_home);
        home_logo = findViewById(R.id.home_logo);
        tv_home_score = findViewById(R.id.score_home);
        home_1 = findViewById(R.id.btn_add_home_1);
        home_2 = findViewById(R.id.btn_add_home_2);
        home_3 = findViewById(R.id.btn_add_home_3);

        away_team = findViewById(R.id.txt_away);
        away_logo = findViewById(R.id.away_logo);
        tv_away_score = findViewById(R.id.score_away);
        away_1 = findViewById(R.id.btn_add_away_1);
        away_2 = findViewById(R.id.btn_add_away_2);
        away_3 = findViewById(R.id.btn_add_away_3);

        btn_reset = findViewById(R.id.btn_reset);
        btn_result = findViewById(R.id.btn_result);

        versus = getIntent();
        home_name = versus.getStringExtra("home_name");
        away_name = versus.getStringExtra("away_name");
        home_team.setText(home_name);
        away_team.setText(away_name);
        if(versus.hasExtra("home_logo")) {
            home_logo_uri = versus.getParcelableExtra("home_logo");
            home_logo.setImageURI(home_logo_uri);
        }
        if(versus.hasExtra("away_logo")) {
            away_logo_uri = versus.getParcelableExtra("away_logo");
            away_logo.setImageURI(away_logo_uri);
        }

        home_score = 0;
        away_score = 0;
        home_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_score += 1;
                tv_home_score.setText(home_score.toString());
            }
        });
        home_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_score += 2;
                tv_home_score.setText(home_score.toString());
            }
        });
        home_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_score += 3;
                tv_home_score.setText(home_score.toString());
            }
        });
        away_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                away_score += 1;
                tv_away_score.setText(away_score.toString());
            }
        });
        away_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                away_score += 2;
                tv_away_score.setText(away_score.toString());
            }
        });
        away_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                away_score += 3;
                tv_away_score.setText(away_score.toString());
            }
        });

        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = new Intent(MatchActivity.this, ResultActivity.class);

                if (home_score > away_score) {
                    hasil = home_name;
                } else if (home_score < away_score) {
                   hasil = away_name;
                } else {
                    hasil= "Imbang";
                }

                result.putExtra("hasil", hasil);
                startActivity(result);
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_score = 0;
                tv_home_score.setText(home_score.toString());
                away_score = 0;
                tv_away_score.setText(away_score.toString());
            }
        });
    }
}