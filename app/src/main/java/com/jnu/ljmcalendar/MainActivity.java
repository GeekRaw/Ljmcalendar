package com.jnu.ljmcalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jnu.ljmcalendar.dataprocessor.DataBank;
import com.jnu.ljmcalendar.dataprocessor.Record;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD = 100;
    public String date = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        getSupportActionBar().hide();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // 悬浮按钮
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                date = DataBank.getDate();
                intent.putExtra("edit_date",date);
                startActivityForResult(intent,REQUEST_CODE_ADD);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String type,editName,editMoney,editReason;
        if (resultCode == RESULT_OK) {

            type = data.getStringExtra("type");
            editName = data.getStringExtra("edit_name");
            editMoney = data.getStringExtra("edit_money");
            editReason = data.getStringExtra("edit_reason");
            date = data.getStringExtra("edit_date");

            DataBank.Load(MainActivity.this, type);

            DataBank.getRecords(type).add((new Record(type, editName, editMoney, editReason, date)));
            DataBank.Save(MainActivity.this, type);
        }else if(resultCode == RESULT_CANCELED){
            Toast.makeText(MainActivity.this,"在新建记录的时候点击了返回键",
                    Toast.LENGTH_LONG).show();
        }
    }
}