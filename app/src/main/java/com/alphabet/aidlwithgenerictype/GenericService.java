package com.alphabet.aidlwithgenerictype;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by alphabet on 4/12/16.
 */
public class GenericService extends Service {

    Type1 mType1 = new Type1("type1");

    public GenericService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public GenericType getGenericType(String msg) throws RemoteException {
            return new GenericType(Type1.class.getSimpleName(), Type1.class,mType1);
        }
    };
}
