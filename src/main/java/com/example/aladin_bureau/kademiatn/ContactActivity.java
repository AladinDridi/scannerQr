package com.example.aladin_bureau.kademiatn;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1 ,btn2  ;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        btn1=(Button)findViewById(com.example.aladin_bureau.kademiatn.R.id.hire);
        btn1.setOnClickListener(this);
        btn2=(Button)findViewById(com.example.aladin_bureau.kademiatn.R.id.donation);
        btn2.setOnClickListener(this);
        img =(ImageView)findViewById(R.id.github);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage =Uri.parse("https://github.com/AladinDridi?tab=repositories");
                Intent intent= new Intent(Intent.ACTION_VIEW,webpage);
                startActivity(intent);
            }
        });
    }
public void ouvirurl(String url){
}
    @Override
    public void onClick(View view) {

        if (view.getId() == btn1.getId()) {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            String[] recipients = new String[]{"dridialaeddine81@gmail.com", "",};
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Sujet");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Message:");
            emailIntent.setType("text/plain");
            startActivity(Intent.createChooser(emailIntent, "Envoyer mail..."));
            finish();

        }

       else if (view.getId()==btn2.getId()){
         //   ouvirurl("");
            Uri webpage =Uri.parse("https://www.continuetogive.com/4843787/donation_prompt");
            Intent intent= new Intent(Intent.ACTION_VIEW,webpage);
            startActivity(intent);

        }
    }
}
