package com.ivntel.android.prekhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ivntel.android.prekhome.POJO.Ebook;

public class EbookActivity extends AppCompatActivity {

    public static final String ARG_EBOOK = "ARG_EBOOK";
    TextView ebookIdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        ebookIdText = (TextView) findViewById(R.id.ebookId);

        Intent intent = getIntent();

        if (intent != null) {
            int ebookId = intent.getIntExtra(ARG_EBOOK, 0);

            if (ebookId != 0) {
                ebookIdText.setText("ID value: " + ebookId);
            }
        }
    }
}
