package com.syafiq.bmicalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AboutActivity extends AppCompatActivity {
    TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //Assign variable
        textView6 = (TextView)findViewById(R.id.textView6);

        //Initialize spannable string
        SpannableString spannableString = new SpannableString("Source Code?\nClick on Github");

        //Initialzie clickable span
        ClickableSpan span1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                //Initialize intent
                Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com"));

                //set flag
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //Start activity
                startActivity(intent);

            }
        };

        //Set span
        spannableString.setSpan(span1, 21, 28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //Set text
        textView6.setText(spannableString);
        //Set link movement method
        textView6.setMovementMethod(LinkMovementMethod.getInstance());
    }
}