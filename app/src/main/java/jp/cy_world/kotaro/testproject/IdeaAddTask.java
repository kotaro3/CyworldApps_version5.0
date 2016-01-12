package jp.cy_world.kotaro.testproject;

import android.os.AsyncTask;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**
 * Created by kotaro on 16/01/11.
 */
public class IdeaAddTask extends AsyncTask<ArrayList<String>,Integer,String> {

    String result;

    @Override
    protected String doInBackground(ArrayList... params) {
        ArrayList<BasicNameValuePair> param = new ArrayList<>();
        param.add(new BasicNameValuePair("title",param.get(0).toString()));
        HttpAccess access = new HttpAccess();

        result = access.DBAccess("",param);

        return result;
    }

    @Override
    protected void onPostExecute(String s) {

    }
}
