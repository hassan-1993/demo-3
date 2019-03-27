package com.example.inmobile.main;


import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import com.example.inmobile.R;
import com.example.inmobile.core.db.entities.Data;
import com.example.inmobile.details.DetailsDialog;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements MainAdapter.Listener {

    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    Chronometer chronometer;


    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        chronometer = findViewById(R.id.chronometer);

        initListView();
        listenToEvents();
    }



    private void initListView(){
        this.recyclerView = findViewById(R.id.recycle_view);
        this.mainAdapter = new MainAdapter();
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this);
        this.recyclerView .setLayoutManager(mLayoutManager1);
        this.recyclerView .setAdapter(mainAdapter);

        //add divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        this.recyclerView.addItemDecoration(dividerItemDecoration);

        this.mainAdapter.setOnClickListener(this);

    }


    private void listenToEvents(){
        mainViewModel.getData().observe(this,this::loadData);

        mainViewModel.getMsgEvent().observe(this,this::showToast);


        mainViewModel.getTimerEvent().observe(this,(time)->{
            chronometer.setBase(time);
            chronometer.start();
        });
    }

    private void loadData(List<Data> dataList){
        mainAdapter.setList(dataList);
    }

    private void showDialog(Data data){
        FragmentManager manager = getSupportFragmentManager();

        if(manager.findFragmentByTag("dialog") == null){
            Bundle args = new Bundle();
            args.putSerializable("data", data);
            DetailsDialog detailsDialog = new DetailsDialog();
            detailsDialog.setArguments(args);
            detailsDialog.show(manager,"dialog");
        }

    }

    @Override
    public void onClick(Data data, int position) {
        showDialog(data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mainViewModel.onSaveInstance(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mainViewModel.onRestoreInstance(savedInstanceState);
    }


    private void showToast(String error){
        if(error == null){
            return;
        }
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }
}
