package com.example.administrator.forecast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SecondActivity extends AppCompatActivity {
    private String data[]={"河北","山西","辽宁","吉林","黑龙江","江苏","浙江","安徽","福建","江西","山东","河南","湖北","湖南"
            ,"广东","海南","四川","贵州","云南","陕西","甘肃","青海","台湾","北京","天津","上海","重庆","广西","内蒙古","西藏",
            "宁夏","新疆","香港","澳门"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                SecondActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String province = data[position];
                Intent intent = new Intent();
                intent.putExtra("extra_data", province);
                setResult(RESULT_OK, intent);
                finish();


            }
        });



    }

}
