package data.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DataFireBase {
    private String key, secret;
    private List<String> legue1;
    private DatabaseReference mDatabase;

    private Object lock;

    private DataFireBase() {
        lock = new Object();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        DataFireBase.this.key = "ANRXirdyHL8ZET0o";
        DataFireBase.this.secret = "3j5Qj1Qf6FSvD9ZdXRbfipO0aXZPQZeU";
        DataFireBase.this.legue1 = new ArrayList(Arrays.asList(new String[]{"793", "794", "795", "796", "797", "798", "799", "800"}));


//        mDatabase.child("base").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                HashMap<String, Object> hashMap = (HashMap) dataSnapshot.getValue();
//                DataFireBase.this.key = (String) hashMap.get("key");
//                DataFireBase.this.secret = (String) hashMap.get("secret");
//                DataFireBase.this.legue1 = (List) hashMap.get("legue1");
//                Log.e("THE_DV", "get key OK ");
//                synchronized (lock) {
//                    lock.notify();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                DataFireBase.this.key = "ANRXirdyHL8ZET0o";
//                DataFireBase.this.secret = "3j5Qj1Qf6FSvD9ZdXRbfipO0aXZPQZeU";
//                DataFireBase.this.legue1 = new ArrayList(Arrays.asList(new String[]{"793", "793", "793", "793", "793", "793", "793", "800"}));
//            }
//        });




    }

    public static class Helper {
        private static DataFireBase INSTANCE = new DataFireBase();
    }

    private static DataFireBase mDataFirebase;

    public static DataFireBase getInstance() {
        if (mDataFirebase == null) {
            mDataFirebase = Helper.INSTANCE;
        }
        return mDataFirebase;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String[] getLegue1() {
        String[] result = new String[legue1.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = legue1.get(i);
        }

        return result;
    }


}
