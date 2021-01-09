package com.jnu.ljmcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jnu.ljmcalendar.R;

public class AddActivity extends AppCompatActivity {

    private String type;
    private EditText editName,editMoney,editReason,editDate;
    private Intent intent = new Intent();
    private int pos;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        // 获取edit
        editName = AddActivity.this.findViewById(R.id.edit_name);
        editMoney = AddActivity.this.findViewById(R.id.edit_money);
        editReason = AddActivity.this.findViewById(R.id.edit_reason);
        editDate = AddActivity.this.findViewById(R.id.edit_date);


        pos = getIntent().getIntExtra("position",0);
        type = getIntent().getStringExtra("type");
        if(type != null){
            if(type.equals("收礼")){
                radioGroup.check(R.id.radioButton_receive);
            }else{
                radioGroup.check(R.id.radioButton_give);
            }
        }

        if(getIntent().getStringExtra("edit_name")!=null){
            editName.setText(getIntent().getStringExtra("edit_name"));
        }
        if(getIntent().getStringExtra("edit_money")!=null){
            editMoney.setText(getIntent().getStringExtra("edit_money"));
        }
        if(getIntent().getStringExtra("edit_reason")!=null){
            editReason.setText(getIntent().getStringExtra("edit_reason"));
        }
        if(getIntent().getStringExtra("edit_date")!=null){
            editDate.setText(getIntent().getStringExtra("edit_date"));
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                type = checkedId == R.id.radioButton_receive ?
                        "收礼" : "随礼";
            }
        });

        Button buttonOk = (Button)findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(type.length()==0  || editName.getText().toString().length()==0 || editMoney.getText().toString().length()==0
                        || editReason.getText().toString().length()==0 || editDate.getText().toString().length()==0 ){
                    Toast.makeText(AddActivity.this,"记录未填写完整",
                            Toast.LENGTH_LONG).show();
                }else {
                    intent.putExtra("type", type);
                    intent.putExtra("edit_name", editName.getText().toString());
                    intent.putExtra("edit_money", editMoney.getText().toString());
                    intent.putExtra("edit_reason", editReason.getText().toString());
                    intent.putExtra("edit_date", editDate.getText().toString());
                    intent.putExtra("position", pos);

                    setResult(RESULT_OK, intent);
                    Toast.makeText(AddActivity.this,"记录成功添加",
                            Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });

        Button buttonQuit = (Button)findViewById(R.id.button_quit);
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }
}