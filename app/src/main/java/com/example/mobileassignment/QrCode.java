package com.example.mobileassignment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QrCode extends AppCompatActivity {
    Button bt;
    TextView tv;
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code);
        bt = findViewById(R.id.button);
        tv = findViewById(R.id.memberId);
        wv = findViewById(R.id.qr_code);

        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String name = tv.toString();

                genQrCode(name);
            }
        });
    }

    private void genQrCode(String name) {
        String URL = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + name;

        wv.setWebViewClient(new WebViewClient(){
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
        setContentView(wv);
    }
}
