package th.wc2018.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import data.app.App;
import data.obtain.LoadData;
import data.raw.Events;
import data.raw.LiveScore;
import th.wc2018.api.OnLoadApiCompletedListener;
import th.wc2018.api.apiImp.LiveScoreApi;
import th.wc2018.broadcast.MAction;

public class WcServicerBroadCastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == MAction.REQUEST_DATABASE_CHANGE) {
            LiveScoreApi liveScoreApi = new LiveScoreApi();
            liveScoreApi.getObject();
            LoadData mLoadData = new LoadData(context, App.DATABASE);

            liveScoreApi.AddOnLoadApiCOmpleteListener(new OnLoadApiCompletedListener() {
                @Override
                public void loadApiCompleted(Object... result) {
                    LiveScore[] liveScoreDatas = (LiveScore[]) result;
                    if (mLoadData != null) {
                        mLoadData.getLiveScoreDao().insert(liveScoreDatas);
                        Log.e("THE_DV", "mLoadData SCORE OK ");
                        Intent intent = new Intent();
                        intent.setAction(MAction.LIVE_SCORE_DATABASE_CHANGED);
                        context.sendBroadcast(intent);
                    }
                }
            });
            liveScoreApi.AddLoadLiveScoreEvent(new LiveScoreApi.ILoadLiveScoreEvent() {
                @Override
                public void loadEventCompleted(Events[] liveLiveScoreEvent) {
                    if (mLoadData != null) {
                        mLoadData.getEventLiveScoreDao().insert(liveLiveScoreEvent);
                        Log.e("THE_DV", "service load Event OK! ");

                    }
                }
            });
        }
    }
}
