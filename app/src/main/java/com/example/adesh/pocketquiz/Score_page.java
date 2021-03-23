package com.example.adesh.pocketquiz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.BaseGmsClient;

public class Score_page extends Activity {

    private TextView textView_score;
    private Button logout;
    private Button again;

    private long backPressedTime;
    private Toast backToast;


    PlayQuiz pq=new PlayQuiz();
    private int s=pq.score;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_score);

        textView_score=(TextView)findViewById(R.id.textView_score);
        logout=(Button)findViewById(R.id.btn_logout) ;
        again=(Button)findViewById(R.id.btn_again);


        textView_score.setText("Score:"+s);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pq.score=0;
                startActivity(new Intent(getApplicationContext(),LoginPage.class));
            }
        });


        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pq.score=0;
                startActivity(new Intent(getApplicationContext(),Subjects.class));
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            finishAffinity();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();

        }

        backPressedTime = System.currentTimeMillis();
    }
}



