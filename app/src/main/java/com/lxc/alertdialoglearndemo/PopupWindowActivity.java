package com.lxc.alertdialoglearndemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PopupWindowActivity extends AppCompatActivity {
    private Button btnShow;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_popup_window);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }
    private void init(){
        btnShow = findViewById(R.id.btn_show);
        btnShow.setOnClickListener(v -> {showPopupWindow();});
    }

    private void showPopupWindow(){
        View view = getLayoutInflater().inflate(R.layout.view_popup_window,null,false);
        Button btnXi = view.findViewById(R.id.btn_xixi);
        Button btnHa = view.findViewById(R.id.btn_haha);
        btnXi.setOnClickListener(v -> {xiDoSomething();});
        btnHa.setOnClickListener(v -> {haDoSomething();});
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(R.style.PopupWindow_Style);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.showAsDropDown(btnShow);
    }

    private void xiDoSomething(){
        Toast.makeText(this, "点击了嘻嘻", Toast.LENGTH_SHORT).show();
        popupWindow.dismiss();
    }
    private void haDoSomething(){
        Toast.makeText(this, "点击了哈哈", Toast.LENGTH_SHORT).show();
        popupWindow.dismiss();
    }
}