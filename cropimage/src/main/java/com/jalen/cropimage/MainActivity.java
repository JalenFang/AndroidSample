package com.jalen.cropimage;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final int requestCode = 100;
    private ImageView retImg;

    private static final int SD = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(listener);
        findViewById(R.id.btn2).setOnClickListener(listener);
        findViewById(R.id.btn3).setOnClickListener(listener);
        findViewById(R.id.btn4).setOnClickListener(listener);

        retImg = (ImageView) findViewById(R.id.retImg);

        // TODO: 2017/6/19  增加权限的判断会报错 但是log日志没有显示错误信息
      /*  if (!checkPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE")) {
            ActivityCompat.requestPermissions(this, new String[]{"Manifest.permission.WRITE_EXTERNAL_STORAGE"}, SD);
        }*/
    }

    public boolean checkPermission(String permissionName) {
        return ContextCompat.checkSelfPermission(this, permissionName) == PackageManager.PERMISSION_GRANTED ? true : false;
    }

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent mIntent = new Intent(getBaseContext(), CropImageActivity.class);
            int index = 1;
            switch (v.getId()) {
                case R.id.btn1:
                    index = 1;
                    break;

                case R.id.btn2:
                    index = 2;
                    break;

                case R.id.btn3:
                    index = 3;
                    break;

                case R.id.btn4:
                    index = 4;
                    break;
            }
            mIntent.putExtra("index", index);
            startActivityForResult(mIntent, requestCode);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults != null && grantResults.length > 0) {
            switch (requestCode) {
                case SD:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    } else {
                        finish();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int _requestCode, int resultCode, Intent data) {
        if (requestCode == _requestCode && resultCode == RESULT_OK) {
            String path = data.getStringExtra("cropImagePath");
            retImg.setImageDrawable(BitmapDrawable.createFromPath(path));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
