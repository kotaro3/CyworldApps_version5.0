package jp.cy_world.kotaro.testproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/12/03.
 */
public class RoomListTask extends AsyncTask<ArrayList<String>,Void,String> {

    Context context;

    public RoomListTask(Context context){
        this.context = context;
    }
    @Override
    protected String doInBackground(ArrayList<String>... params) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        pref.getString("address",null);
        
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

    }
}
