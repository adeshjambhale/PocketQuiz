package com.example.adesh.pocketquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PlayQuiz extends Activity {

        private TextView textView_qca;
        private TextView textView_question;
        private TextView textView_countdown;

        private Button choice1;
        private Button choice2;
        private Button choice3;
        private Button choice4;

        private Button confirm;

        private long timeLeftInMilliSeconds=305000;

        int qc = 0;
        protected static int score = 0;
        private String Answer;

        Subjects s=new Subjects();
        String subject= Subjects.sub;
        String java="JAVA";
        String cpp="C++";

        private DatabaseReference questionRef, choice1Ref, choice2Ref, choice3Ref, choice4Ref, AnswerRef;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_play_quiz);

            textView_qca= (TextView) findViewById(R.id.textView_qc);
            textView_question = (TextView) findViewById(R.id.textView_question);
            textView_countdown = (TextView) findViewById(R.id.textView_countdown);

            confirm = (Button) findViewById(R.id.btn_confirm);

            choice1 = (Button) findViewById(R.id.btn_choice1);
            choice2 = (Button) findViewById(R.id.btn_choice2);
            choice3 = (Button) findViewById(R.id.btn_choice3);
            choice4 = (Button) findViewById(R.id.btn_choice4);

            Toast.makeText(PlayQuiz.this, "Please Wait", Toast.LENGTH_SHORT).show();

            new CountDownTimer(timeLeftInMilliSeconds,1000){
                @Override
                public void onTick(long l) {
                    timeLeftInMilliSeconds=l;
                    updateTimer();
                }

                @Override
                public void onFinish() {
                    openScore_page();
                }
            }.start();


            if(subject.equalsIgnoreCase(java)) {
                updateQuestionJava();
            }
            else if(subject.equalsIgnoreCase(cpp)) {
                updateQuestionCpp();
            }
            else{
                updateQuestionAJava();
            }

            choice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (choice1.getText().equals(Answer)) {
                        score = score + 1;
                        if (qc < 10) {
                            if(subject.equalsIgnoreCase(java)) {
                                updateQuestionJava();
                            }
                            else if(subject.equalsIgnoreCase(cpp)){
                                updateQuestionCpp();
                            }
                            else {
                                updateQuestionAJava();
                            }
                        } else {
                            openScore_page();
                        }
                    }
                    else {
                        if (qc < 10) {
                            if(subject.equalsIgnoreCase(java)) {
                                updateQuestionJava();
                            }
                            else if (subject.equalsIgnoreCase(cpp)){
                                updateQuestionCpp();
                            }
                            else {
                                updateQuestionAJava();
                            }
                        }
                        else {
                            openScore_page();
                        }
                    }
                }
            });

            choice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (choice2.getText().equals(Answer)) {
                        score = score + 1;
                        if (qc < 10) {
                            if(subject.equalsIgnoreCase(java)) {
                                updateQuestionJava();
                            }
                            else if (subject.equalsIgnoreCase(cpp)){
                                updateQuestionCpp();
                            }
                            else {
                                updateQuestionAJava();
                            }
                        }
                        else {
                            openScore_page();
                        }
                    }
                    else {
                        if (qc < 10) {
                            if(subject.equalsIgnoreCase(java)) {
                                updateQuestionJava();
                            }
                            else if (subject.equalsIgnoreCase(cpp)){
                                updateQuestionCpp();
                            }
                            else {
                                updateQuestionAJava();
                            }
                        }
                        else {
                            openScore_page();
                        }
                    }
                }
            });

            choice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (choice3.getText().equals(Answer)) {
                        score = score + 1;
                        if (qc < 10) {
                            if(subject.equalsIgnoreCase(java)) {
                                updateQuestionJava();
                            }
                            else if (subject.equalsIgnoreCase(cpp)){
                                updateQuestionCpp();
                            }
                            else {
                                updateQuestionAJava();
                            }
                        }
                        else {
                            openScore_page();
                        }
                    }
                    else {
                        if (qc < 10) {
                            if(subject.equalsIgnoreCase(java)) {
                                updateQuestionJava();
                            }
                            else if (subject.equalsIgnoreCase(cpp)){
                                updateQuestionCpp();
                            }
                            else {
                                updateQuestionAJava();
                            }
                        }
                        else {
                            openScore_page();
                        }
                    }
                }
            });

            choice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (choice4.getText().equals(Answer)) {
                        score = score + 1;
                        if (qc < 10) {
                            if(subject.equalsIgnoreCase(java)) {
                                updateQuestionJava();
                            }
                            else if (subject.equalsIgnoreCase(cpp)){
                                updateQuestionCpp();
                            }
                            else {
                                updateQuestionAJava();
                            }
                        }
                        else {
                            openScore_page();
                        }
                    }
                    else {
                        if (qc < 10) {
                            if(subject.equalsIgnoreCase(java)) {
                                updateQuestionJava();
                            }
                            else if(subject.equalsIgnoreCase(cpp)){
                                updateQuestionCpp();
                            }
                            else {
                                updateQuestionAJava();
                            }
                        }
                        else {
                            openScore_page();
                        }
                    }
                }
            });

            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openScore_page();
                }
            });


        }

        public void openScore_page() {
            Intent intent = new Intent(this, Score_page.class);
            startActivity(intent);
        }

        public void updateTimer(){
            int minutes = (int)(timeLeftInMilliSeconds/ 1000)/60;
            int seconds=(int)(timeLeftInMilliSeconds%60000)/1000;

            String timeLeftText;

            timeLeftText="Time Left :"+minutes;
            timeLeftText+=":";
            if (seconds<10)timeLeftText+="0";
            timeLeftText+=seconds;

            textView_countdown.setText(timeLeftText);
        }

        public void updateQuestionJava() {
            questionRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet1/"+qc+"/Questions");
            questionRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Questions = dataSnapshot.getValue(String.class);
                    textView_question.setText(Questions);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.i("question", "the error in question");
                }
            });

            choice1Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet1/" + qc + "/A");
            choice1Ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String A = dataSnapshot.getValue(String.class);
                    choice1.setText(A);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            choice2Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet1/" + qc + "/B");
            choice2Ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String choice = dataSnapshot.getValue(String.class);
                    choice2.setText(choice);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            choice3Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet1/" + qc + "/C");
            choice3Ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String choice = dataSnapshot.getValue(String.class);
                    choice3.setText(choice);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            choice4Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet1/" + qc + "/D");
            choice4Ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String choice = dataSnapshot.getValue(String.class);
                    choice4.setText(choice);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            AnswerRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet1/" + qc + "/ANSWER");
            AnswerRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Answer = dataSnapshot.getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            qc++;
            String temp =Integer.toString(qc);
            textView_qca.setText(temp);

        }

        public void updateQuestionCpp() {
        questionRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet2/"+qc+"/Questions");
        questionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Questions = dataSnapshot.getValue(String.class);
                textView_question.setText(Questions);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("question", "the error in question");
            }
        });

        choice1Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet2/" + qc + "/A");
        choice1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String A = dataSnapshot.getValue(String.class);
                choice1.setText(A);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        choice2Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet2/" + qc + "/B");
        choice2Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                choice2.setText(choice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        choice3Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet2/" + qc + "/C");
        choice3Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                choice3.setText(choice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        choice4Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet2/" + qc + "/D");
        choice4Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                choice4.setText(choice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        AnswerRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet2/" + qc + "/ANSWER");
        AnswerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Answer = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        qc++;
        String temp = Integer.toString(qc);
        textView_qca.setText(temp);

    }

        public void updateQuestionAJava() {
        questionRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet3/"+qc+"/Questions");
        questionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Questions = dataSnapshot.getValue(String.class);
                textView_question.setText(Questions);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("question", "the error in question");
            }
        });

        choice1Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet3/" + qc + "/A");
        choice1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String A = dataSnapshot.getValue(String.class);
                choice1.setText(A);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        choice2Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet3/" + qc + "/B");
        choice2Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                choice2.setText(choice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        choice3Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet3/" + qc + "/C");
        choice3Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                choice3.setText(choice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        choice4Ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet3/" + qc + "/D");
        choice4Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                choice4.setText(choice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        AnswerRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pocket-quiz-bf821.firebaseio.com/Sheet3/" + qc + "/ANSWER");
        AnswerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Answer = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        qc++;
        String temp =Integer.toString(qc);
        textView_qca.setText(temp);

    }
    }


