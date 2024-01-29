package com.example.mobileassignment;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QrCode extends AppCompatActivity {
    public static final String DATE_KEY = "DATE_KEY";
    Button bt;
    TextView tv;
    WebView wv;
    TextView hint1;
    SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code);
        bt = findViewById(R.id.button);
        tv = findViewById(R.id.memberId);
        wv = findViewById(R.id.qr_code);
        hint1 = findViewById(R.id.hint1);
        sharedPreferences = getSharedPreferences("MySharedPreMain", MODE_PRIVATE);
        if (sharedPreferences.contains(DATE_KEY)) {
            hint1.setText(sharedPreferences.getString(DATE_KEY, ""));
        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tv.toString();
                genQrCode(name);
                save(view);
            }
        });
    }

    private void genQrCode(String name) {
        String URL = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + name;

        wv.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(QrCode.this, description, Toast.LENGTH_SHORT).show();
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError err) {
                onReceivedError(view, err.getErrorCode(), err.getDescription().toString(), req.getUrl().toString());
            }
        });

        wv.loadUrl(URL);
    }

    public void save(View v) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DATE_KEY, date());
        editor.commit();
        Toast.makeText(v.getContext(), "data saved", Toast.LENGTH_SHORT).show();
    }
    
    public static String date() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String today = formatter.format(date);
        return today;
    }
}
