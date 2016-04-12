package com.alphabet.aidlwithgenerictype;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    IMyAidlInterface mIMyAidlInterface;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIMyAidlInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(MainActivity.this,GenericService.class),mConnection, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIMyAidlInterface != null) {
                    try {
                        GenericType genericType = mIMyAidlInterface.getGenericType("Type1");
                        Log.d("type",genericType.getMsg()+"\t"
                        +genericType.getClassType() + "\t"
                        +genericType.getValue() + "\t");
                        Type1 type1 = (Type1) genericType.getValue();
                        Log.d("type",type1.getName());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
