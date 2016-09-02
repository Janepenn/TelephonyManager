package com.example.telephonymanager;

import android.content.ContentResolver;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button check;
    private ListView lv;
    private List<String> name= new ArrayList<String>();
    private List<String> list=new ArrayList<String>();
    TelephonyManager tm;
    ContentResolver cr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check=(Button)findViewById(R.id.bt01);
        lv=(ListView)findViewById(R.id.lv01);
        tm=(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        cr=MainActivity.this.getContentResolver();
        String str=null;
        name.add("手机号码：");
        name.add("电信网络国别：");
        name.add("电信公司代码：");
        name.add("电信公司名称：");
        name.add("SIM码：");
        name.add("手机通信类型：");
        name.add("手机通信网络类型：");
        name.add("手机是否漫游：");
        if(tm.getLine1Number()!=null){
            list.add(tm.getLine1Number());
        }else{
            list.add("无法取得您的手机号码");
        }
        if(tm.getNetworkCountryIso()!=null){
            list.add(tm.getNetworkCountryIso());
        }else{
            list.add("无法取得您的电信网络国别");
        }
        if(tm.getNetworkOperator()!=null){
            list.add(tm.getNetworkOperator());
        }else{
            list.add("无法取得您的电信公司代码");
        }
        if(tm.getNetworkOperatorName()!=null){
            list.add(tm.getNetworkOperatorName());
        }else{
            list.add("无法取得您的电信公司名称");
        }
        if(tm.getSimSerialNumber()!=null){
            list.add(tm.getSimSerialNumber());
        }else{
            list.add("无法取得您的SIM码");
        }
        if(tm.getPhoneType()==TelephonyManager.PHONE_TYPE_GSM){
            list.add("GSM");
        }else if(tm.getPhoneType()==TelephonyManager.PHONE_TYPE_CDMA){
            list.add("CDMA");
        }else{
            list.add("无法获取您的手机通信类型");
        }
        if(tm.getNetworkType()==TelephonyManager.NETWORK_TYPE_CDMA){
            list.add("CDMA");
        }else if(tm.getNetworkType()==TelephonyManager.NETWORK_TYPE_EDGE){
            list.add("EDGE");
        }else if(tm.getNetworkType()==TelephonyManager.NETWORK_TYPE_EHRPD){
            list.add("EHRPD");
        }else if(tm.getNetworkType()==TelephonyManager.NETWORK_TYPE_GPRS){
            list.add("GRPS");
        }else{
            list.add("无法获取您的手机网络类型");
        }
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseAdapter adapter=new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return list.size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }

                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        LinearLayout ll=new LinearLayout(MainActivity.this);
                        ll.setOrientation(LinearLayout.HORIZONTAL);
                        ll.setPadding(5,5,5,5);
                        TextView title=new TextView(MainActivity.this);
                        title.setTextColor(Color.BLACK);
                        title.setPadding(5,5,5,5);
                        title.setText(name.get(i));
                        title.setTextSize(20);
                        title.setGravity(Gravity.LEFT);
                        ll.addView(title);

                        TextView mes=new TextView(MainActivity.this);
                        mes.setTextColor(Color.BLACK);
                        mes.setPadding(5,5,5,5);
                        mes.setText(list.get(i));
                        mes.setTextSize(20);
                        mes.setGravity(Gravity.LEFT);
                        ll.addView(mes);
                        return ll;
                    }
                };
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(MainActivity.this,name.get(i)+""+list.get(i),Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });
    }
}
