package com.lxc.alertdialoglearndemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static final String TAG  = "MainActivity";
    private Button btnOne, btnTwo, btnThree, btnFour, btnFive;
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init() {
        btnOne = findViewById(R.id.btn_one);
        btnTwo = findViewById(R.id.btn_two);
        btnThree = findViewById(R.id.btn_three);
        btnFour = findViewById(R.id.btn_four);
        btnFive = findViewById(R.id.btn_five);
        btnOne.setOnClickListener(v -> {
            AlertDialog builder = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("标题")
                    .setMessage("设置信息")
                    .setIcon(R.mipmap.ic_launcher)
                    .setNegativeButton("取消", (dialog, which) -> Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show())
                    .setPositiveButton("确定", (dialog, which) -> Toast.makeText(MainActivity.this, "点击了确定按钮", Toast.LENGTH_SHORT).show())
                    .setNeutralButton("中立", (dialog, which) -> Toast.makeText(MainActivity.this, "点击了中立按钮", Toast.LENGTH_SHORT).show())
                    .setCancelable(false)
                    .show();
        });
        btnTwo.setOnClickListener(v -> {
            final String[] lessons = new String[]{"语文", "数学", "英语", "化学", "生物", "物理", "体育"};
            AlertDialog builder = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("标题2")
//                    .setMessage("设置信息2")
                    .setIcon(R.mipmap.ic_launcher)
                    .setItems(lessons, ((dialog, which) -> {
                        Toast.makeText(this, "点击了" + lessons[which], Toast.LENGTH_SHORT).show();
                    }))
                    .setNegativeButton("取消", (dialog, which) -> Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show())
                    .setPositiveButton("确定", (dialog, which) -> Toast.makeText(MainActivity.this, "点击了确定按钮", Toast.LENGTH_SHORT).show())
                    .setNeutralButton("中立", (dialog, which) -> Toast.makeText(MainActivity.this, "点击了中立按钮", Toast.LENGTH_SHORT).show())
                    .setCancelable(true)
                    .show();
        });
        btnThree.setOnClickListener(v -> {
            final String[] fruits = new String[]{"苹果", "雪梨", "香蕉", "葡萄", "西瓜"};
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("选择一种水果：")
                    .setIcon(R.mipmap.ic_launcher)
                    .setSingleChoiceItems(fruits, 2, (dialog, which) -> {
                        Toast.makeText(this, "你选择了：" + fruits[which], Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("取消", (dialog, which) -> Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show())
                    .setPositiveButton("确定", (dialog, which) -> Toast.makeText(MainActivity.this, "点击了确定按钮", Toast.LENGTH_SHORT).show())
                    .setNeutralButton("中立", (dialog, which) -> Toast.makeText(MainActivity.this, "点击了中立按钮", Toast.LENGTH_SHORT).show())
                    .setCancelable(true)
                    .show();

        });
        btnFour.setOnClickListener(v -> {
            final String[] menu = new String[]{"水煮豆腐", "萝卜牛腩", "酱油鸡", "胡椒猪肚鸡"};
            boolean[] checkedItems = new boolean[]{false, false, false, false};
            StringBuilder sb = new StringBuilder();
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("请点单：")
                    .setMultiChoiceItems(menu, checkedItems, ((dialog, which, isChecked) -> {
                        checkedItems[which] = isChecked;
                        Toast.makeText(this, (isChecked ? "选中了" : "未选中") + menu[which], Toast.LENGTH_SHORT).show();
                    }))
                    .setNegativeButton("取消", (dialog, which) -> Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show())
                    .setPositiveButton("确定", (dialog, which) -> {
                        for (int i = 0; i < checkedItems.length; i++) {
                            if (checkedItems[i]) {
                                sb.append(menu[i]).append("\n");
                            }
                        }
                        Toast.makeText(MainActivity.this, "您选择了如下菜品：\n" + sb, Toast.LENGTH_SHORT).show();
                    })
                    .setNeutralButton("中立", (dialog, which) -> Toast.makeText(MainActivity.this, "点击了中立按钮", Toast.LENGTH_SHORT).show())
                    .setCancelable(true)
                    .show();
        });
        btnFive.setOnClickListener(v -> {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_view_dialog, null);
            Button btnCancle = view.findViewById(R.id.btn_cancle);
            Button btnBlog = view.findViewById(R.id.btn_blog);
            Button btnClose = view.findViewById(R.id.btn_close);
            btnCancle.setOnClickListener(view1 -> alertDialog.cancel());
            btnBlog.setOnClickListener(view1 -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://raw.githubusercontent.com/ShiNianDeng/my-images/main/img/202604031140959.png"));
                startActivity(intent);
            });
            btnClose.setOnClickListener(view1 -> alertDialog.cancel());
            alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setView(view)
                    .show();
        });
    }
}