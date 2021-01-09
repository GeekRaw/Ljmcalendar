package com.jnu.ljmcalendar.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jnu.ljmcalendar.AddActivity;
import com.jnu.ljmcalendar.R;
import com.jnu.ljmcalendar.RecordAdapter;
import com.jnu.ljmcalendar.dataprocessor.DataBank;
import com.jnu.ljmcalendar.dataprocessor.Record;


import java.util.Calendar;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    public static final int REQUEST_CODE_ADD = 100;
    private static Context context;
    private HomeViewModel homeViewModel;

    RecordAdapter adapter;
    CalendarView calendarview;
    String date = new String();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        context = this.getContext();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DataBank.setDate(year+"/"+(month+1)+"/"+dayOfMonth);
        date = DataBank.getDate();

        adapter = new RecordAdapter(context,R.layout.record_item,DataBank.getDateRecords(context,date));
        //Log.d("hello", "onCreateView: "+dataBank.getDateRecords(date));
        ListView listView = root.findViewById(R.id.list_date_records);
        listView.setAdapter(adapter);
        this.registerForContextMenu(listView);

        calendarview = (CalendarView) root.findViewById(R.id.calendarView);
        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                DataBank.setDate(year+"/"+(month+1)+"/"+dayOfMonth);
                date = DataBank.getDate();
                DataBank.getDateRecords(context,date);
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }

}