package com.jnu.ljmcalendar.ui.receive;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.jnu.ljmcalendar.AddActivity;
import com.jnu.ljmcalendar.R;
import com.jnu.ljmcalendar.RecordAdapter;
import com.jnu.ljmcalendar.dataprocessor.DataBank;
import com.jnu.ljmcalendar.dataprocessor.Record;

import java.util.Calendar;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    RecordAdapter adapter;
    private static final String TYPE = "收礼";
    private static Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        context = this.getContext();

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        date = year+"/"+(month+1)+"/"+day;

        DataBank.Load(context,TYPE);
        adapter = new RecordAdapter(this.getContext(),R.layout.record_item,DataBank.getRecords(TYPE));
        ListView listView = root.findViewById(R.id.list_view_records);
        listView.setAdapter(adapter);
        this.registerForContextMenu(listView);

        return root;
    }
    String type,editName,editMoney,editReason,date;

    private static final int Item_New=1;
    private static final int Item_Update = Item_New+1;
    private static final int Item_Remove=Item_New+2;

    private static final int REQUEST_CODE_ADD = 100;
    private static final int REQUEST_CODE_UPDATE = 101;

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        // 设置Menu显示内容
        menu.add(1, Item_New, 1, "新建");
        menu.add(1,Item_Update,1,"修改");
        menu.add(1, Item_Remove, 1, "删除");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_ADD:
                if (resultCode == RESULT_OK) {

                    type = data.getStringExtra("type");
                    editName = data.getStringExtra("edit_name");
                    editMoney = data.getStringExtra("edit_money");
                    editReason = data.getStringExtra("edit_reason");
                    date = data.getStringExtra("edit_date");
                    int pos = data.getIntExtra("position",0);

                    Toast.makeText(this.getContext(),type+editName+editMoney+editReason,
                            Toast.LENGTH_LONG).show();

                    DataBank.getRecords(type).add((new Record(type, editName, editMoney, editReason, date)));
                    DataBank.Save(context, type);
                    adapter.notifyDataSetChanged();
                }else if(resultCode == RESULT_CANCELED){
                    Toast.makeText(this.getContext(),"在新建数据的时候点击了返回键",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_CODE_UPDATE:
                if (resultCode == RESULT_OK) {

                    type = data.getStringExtra("type");

                    editName = data.getStringExtra("edit_name");
                    editMoney = data.getStringExtra("edit_money");
                    editReason = data.getStringExtra("edit_reason");
                    date = data.getStringExtra("edit_date");
                    int pos = data.getIntExtra("position",0);

                    Toast.makeText(this.getContext(),type+editName+editMoney+editReason,
                            Toast.LENGTH_LONG).show();

                    DataBank.getRecords(type).get(pos).setName(editName);
                    DataBank.getRecords(type).get(pos).setMoney(editMoney);
                    DataBank.getRecords(type).get(pos).setReason(editReason);
                    DataBank.getRecords(type).get(pos).setDate(date);
                    DataBank.Save(context, type);

                    adapter.notifyDataSetChanged();
                }else if(resultCode == RESULT_CANCELED){
                    Toast.makeText(this.getContext(),"在修改数据的时候点击了返回键",
                            Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }

    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int pos = menuInfo.position;
        Intent intent = new Intent(this.getContext(), AddActivity.class);;
        switch (item.getItemId()) {
            case Item_New:
                intent.putExtra("position",pos);
                intent.putExtra("type",TYPE);
                intent.putExtra("edit_date",date);
                startActivityForResult(intent, REQUEST_CODE_ADD);

                break;
            case Item_Update:

                type = DataBank.getRecords(TYPE).get(pos).getType();
                editName = DataBank.getRecords(TYPE).get(pos).getName();
                editMoney = DataBank.getRecords(TYPE).get(pos).getMoney();
                editReason = DataBank.getRecords(TYPE).get(pos).getReason();
                date = DataBank.getRecords(TYPE).get(pos).getDate();

                intent.putExtra("position",pos);
                intent.putExtra("type",type);
                intent.putExtra("edit_name",editName);
                intent.putExtra("edit_money",editMoney);
                intent.putExtra("edit_reason",editReason);
                intent.putExtra("edit_date",date);

                startActivityForResult(intent, REQUEST_CODE_UPDATE);
                break;
            case Item_Remove:
                AlertDialog alert=new AlertDialog.Builder(this.getContext()).create();
                alert.setTitle("删除？");
                alert.setMessage("真的要删除这个数据吗？");

                //添加"确定"按钮
                alert.setButton(DialogInterface.BUTTON_POSITIVE,"是的", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        DataBank.getRecords(TYPE).remove(DataBank.getRecords(TYPE).get(pos));
                        DataBank.Save(context, TYPE);
                        adapter.notifyDataSetChanged();
                    }
                });

                alert.setButton(DialogInterface.BUTTON_NEGATIVE,"不",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });

                alert.show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
}