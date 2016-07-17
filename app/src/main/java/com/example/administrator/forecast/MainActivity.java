package com.example.administrator.forecast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import db.CoolWeatherDB;

public class MainActivity extends AppCompatActivity {
    private static int left = 30;
    private static int top = 100;
    private static int i = 0;
    private String [] p = new String[10];
    private CoolWeatherDB coolWeatherDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coolWeatherDB = CoolWeatherDB.getInstance(this);
        p=coolWeatherDB.showw(p);
        RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.add);
        for (int j=0;j<p.length;j++) {
            if (p[j]!=null) {
                Moveadd movead = new Moveadd(p[j]);
                movead.CreateButton(this, mainLayout);
            }

        }






        Button add = (Button) findViewById(R.id.addd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

     class Moveadd {
        private String cityname;

        public Moveadd(String cityname){
            this.cityname = cityname;
        }
        public void CreateButton(final Context context,RelativeLayout mainLayout){
            Button button = new Button(context);
            button.setText(cityname);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(300,300);
            lp.leftMargin = left;
            lp.topMargin = top;
            mainLayout.addView(button,lp);
            i++;
            if(i<3){
                left = left + 300;
            }
            else {
                left = 30;
                top = top + 300;
                i = 0;
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, ThirdActivity.class);
                    intent.putExtra("extra_data", cityname);
                    startActivity(intent);

                }
            });

        }
    }


    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("extra_data");
                    coolWeatherDB.save(returnedData);
                    RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.add);
                    Moveadd moveadd=new Moveadd(returnedData);
                    moveadd.CreateButton(this, mainLayout);


                }
                break;
            default:
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }








    }

