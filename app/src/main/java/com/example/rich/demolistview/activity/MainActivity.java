package com.example.rich.demolistview.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rich.demolistview.R;
import com.example.rich.demolistview.adapter.CountryAdapter;
import com.example.rich.demolistview.model.Country;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Country> list;
    CountryAdapter adapter;
    ListView lvCT;
    View loadView;
    boolean isLoading =false;
    mHander mHander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadData();
        lvCT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Position"+list.get(position),Toast.LENGTH_LONG).show();
            }
        });
        lvCT.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (view.getLastVisiblePosition()==totalItemCount-1 && isLoading==false){
                        isLoading=true;
                       ThreadData threadData = new ThreadData();
                        threadData.start();
                }
            }
        });
    }
    void init(){
        lvCT = findViewById(R.id.lvCT);
        mHander = new mHander();
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        loadView = inflater.inflate(R.layout.activity_load_more_item,null);
        list = new ArrayList<>();
        adapter = new CountryAdapter(this,list);
        lvCT.setAdapter(adapter);
    }
    void loadData(){
        list.add(new Country("VN",R.drawable.h2));
        list.add(new Country("VN",R.drawable.h2));
        list.add(new Country("VN",R.drawable.h2));
        list.add(new Country("VN",R.drawable.h2));
        list.add(new Country("VN",R.drawable.h2));
    }
    public  class mHander extends Handler{
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case 0:
                   lvCT.addFooterView(loadView);
                   break;
               case 1:
                   lvCT.removeFooterView(loadView);
                   adapter.addListItemAdapter((ArrayList<Country>) msg.obj);
                   isLoading=false;
                   break;
           }
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHander.sendEmptyMessage(0);
            ArrayList<Country> listData = getMoreItem();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHander.obtainMessage(1,listData);
            mHander.sendMessage(message);
        }
    }
    public ArrayList<Country> getMoreItem(){

        ArrayList<Country> moreitem = new ArrayList<>();
        moreitem.add(new Country("FR",R.drawable.h1));
        moreitem.add(new Country("FR",R.drawable.h1));
        moreitem.add(new Country("FR",R.drawable.h1));
        moreitem.add(new Country("FR",R.drawable.h1));
        moreitem.add(new Country("FR",R.drawable.h1));
        return moreitem;
    }
}
