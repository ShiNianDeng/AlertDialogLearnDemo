package com.lxc.alertdialoglearndemo;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "MenuActivity";
    //1.定义不同颜色的菜单项的标识:
    final private int RED = 110;
    final private int GREEN = 111;
    final private int BLUE = 112;
    final private int YELLOW = 113;
    final private int GRAY = 114;
    final private int CYAN = 115;
    final private int BLACK = 116;
    private TextView tvText, tvContext;
    private Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init() {
        tvText = findViewById(R.id.tv_text);
        tvContext = findViewById(R.id.tv_context);
        registerForContextMenu(tvContext);//为这个组件注册上下文菜单
        btnShow = findViewById(R.id.btn_popup_menu);
        btnShow.setOnClickListener(v -> showPopupMenu());
    }

    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this,btnShow);
        popupMenu.getMenuInflater().inflate(R.menu.menu_pop,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            if(item.getItemId() == R.id.lpig){
                Toast.makeText(MenuActivity.this, "点击了小猪", Toast.LENGTH_SHORT).show();
            }else if(item.getItemId() == R.id.bpig){
                Toast.makeText(MenuActivity.this, "点击了大猪", Toast.LENGTH_SHORT).show();
            }
            return true;
        });
        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //方式一：动态添加菜单项
//        fangshiyi(menu);


        //方式二：xml填充菜单项
        fangshier(menu);
        return true;
    }

    private void fangshier(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
    }

    private void fangshiyi(Menu menu) {
        menu.add(1, YELLOW, 0, "黄色");
        menu.add(1, GREEN, 0, "绿色");
        menu.add(1, BLUE, 0, "蓝色");
        menu.add(1, RED, 0, "红色");
        menu.add(1, GRAY, 0, "灰色");
        menu.add(1, CYAN, 0, "蓝绿色");
        menu.add(1, BLACK, 0, "黑色");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.huang) {
            tvText.setTextColor(Color.YELLOW);
        } else if (item.getItemId() == R.id.lv) {
            tvText.setTextColor(Color.GREEN);
        } else if (item.getItemId() == R.id.lan) {
            tvText.setTextColor(Color.BLUE);
        } else if (item.getItemId() == R.id.hong) {
            tvText.setTextColor(Color.RED);
        } else if (item.getItemId() == R.id.hui) {
            tvText.setTextColor(Color.GRAY);
        } else if (item.getItemId() == R.id.lanlv) {
            tvText.setTextColor(Color.CYAN);
        } else if (item.getItemId() == R.id.hei) {
            tvText.setTextColor(Color.BLACK);
        } else if (item.getItemId() == R.id.sub_one) {
            Toast.makeText(this, "点击了菜单1", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.sub_two) {
            Toast.makeText(this, "点击了菜单2", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.sub_three) {
            Toast.makeText(this, "点击了菜单3", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_sub, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.one) {
            Toast.makeText(this, "点击了菜单1", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.two) {
            Toast.makeText(this, "点击了菜单2", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.three) {
            Toast.makeText(this, "点击了菜单3", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}