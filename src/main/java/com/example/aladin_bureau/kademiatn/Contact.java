package com.example.aladin_bureau.kademiatn;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Contact extends AppCompatActivity implements View.OnClickListener {
Button btn ,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact3);
        btn=(Button)findViewById(R.id.hireme);
        btn.setOnClickListener(this);
        btn2=(Button)findViewById(R.id.donate);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btn.getId()) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String [] reciptients = new String[]{"dridialaeddine81@gmail.com",""};
            intent.putExtra(Intent.EXTRA_EMAIL,reciptients);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Sujet");
            intent.putExtra(Intent.EXTRA_TEXT,"Votre message : let me working for you");
            startActivity(Intent.createChooser(intent,"Envoyer l'email"));

        } else if(view.getId()==btn2.getId()){
            Uri webpage=Uri.parse("https://www.continuetogive.com/4843787/donation_prompt");
            Intent openurl =new Intent(Intent.ACTION_VIEW,webpage);
            startActivity(openurl);
        }
    }
}
