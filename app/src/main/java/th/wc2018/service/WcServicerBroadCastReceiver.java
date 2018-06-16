package th.wc2018.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import th.wc2018.broadcast.MAction;

public class WcServicerBroadCastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == MAction.REQUEST_DATABASE_SCORE_CHANGE) {


        }
    }
}
