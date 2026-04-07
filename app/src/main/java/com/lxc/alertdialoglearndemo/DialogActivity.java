package com.lxc.alertdialoglearndemo;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Date;

public class DialogActivity extends AppCompatActivity {

    private int maxProgress = 100;
    private int currentProgress = 0;
    private int add = 5;
    private ProgressDialog progressDialog;
    private Button btnProgressDialog, btnDateDialog, btnTimeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dialog);
        init();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void init() {
        btnProgressDialog = findViewById(R.id.btn_progress_dialog);
        btnDateDialog = findViewById(R.id.btn_date_dialog);
        btnTimeDialog = findViewById(R.id.btn_time_dialog);
        btnProgressDialog.setOnClickListener(v -> showProgressDialog());
        btnDateDialog.setOnClickListener(v -> showDateDialog());
        btnTimeDialog.setOnClickListener(v -> showTimeDialog());
    }

    private void showProgressDialog() {
        //方式一：直接使用静态方法show();
//        ProgressDialog.show(this, "加载中", "请稍后，加载中。。。", false, true);
        //方式二：使用ProgressDialog的实例化方法，依次设置标题、信息、是否可取消等属性
//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("加载中");
//        progressDialog.setMessage("请稍候...");
//        progressDialog.setCancelable(true);
//        progressDialog.setIndeterminate(true);//是否设置为不确定模式
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progressDialog.show();
        //方式三：使用ProgressDialog实例化方法，依次设置标题，内容，精确模式，最大值，当前值，样式
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("加载中");
        progressDialog.setMessage("请稍后，加载中。。。");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(maxProgress);
        progressDialog.setProgress(currentProgress);
        progressDialog.show();
        updateProgress();
    }

    private void updateProgress() {
        new Thread(() -> {
            while (currentProgress < maxProgress) {
                try {
                    longtimeOperation();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void longtimeOperation() throws InterruptedException {
        Thread.sleep(500);
        currentProgress += add;
        if (currentProgress >= maxProgress) {
            currentProgress = maxProgress;
        }
        runOnUiThread(() -> {
            progressDialog.setProgress(currentProgress);
        });
    }

    /**
     * 显示日期选择器对话框
     */
    private void showDateDialog() {
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, month1, dayOfMonth) -> Toast.makeText(DialogActivity.this, "选择的日期：" + year1 + "年" + (month1 + 1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show(),
                year,
                month,
                day);
        datePickerDialog.show();
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                Toast.makeText(DialogActivity.this, "选择的日期：" + year + "年" + (month + 1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
//            }
//        }, year, month, day);
//        datePickerDialog.show();
    }

    /**
     * 选择时间选择器对话框
     */
    private void showTimeDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, h, m) -> {
                    Toast.makeText(this, "你选择了" + h + "时" + m + "分", Toast.LENGTH_SHORT).show();
                },
                hour,
                minute,
                true);
        timePickerDialog.show();
    }
}