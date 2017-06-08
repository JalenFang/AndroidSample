package com.jalen.materialdesign.activity.textinput;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jalen.materialdesign.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dragon
 * @date 2017/6/7. 15:04
 * @editor
 * @date
 * @describe
 */
public class TextInputLayoutActivity extends AppCompatActivity {

    private TextInputLayout textInputLayout;
    private TextInputEditText textInputEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_text_input_layout_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textInputLayout = (TextInputLayout) findViewById(R.id.activity_text_input_layout_textInputLayout);
        textInputEditText = (TextInputEditText) findViewById(R.id.activity_text_input_layout_textInputEditText);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickLogin(View v) {
        if (verifyMobile()) {
            textInputLayout.setErrorEnabled(false);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("手机号格式错误");
        }

    }

    public boolean verifyMobile() {
        Pattern pattern = Pattern.compile("1\\d{10}");
        Matcher matcher = pattern.matcher(textInputEditText.getText().toString());
        return matcher.matches();
    }
}
