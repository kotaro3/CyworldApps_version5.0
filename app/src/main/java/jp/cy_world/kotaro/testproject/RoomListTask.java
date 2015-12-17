package jp.cy_world.kotaro.testproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by kotaro on 15/12/03.
 */
public class RoomListTask extends AsyncTask<ArrayList<String>,Void,String> {

    Context context;
    ListView listView;
    ArrayList<RoomBean> roomData;

    String result;

    public RoomListTask(Context context,ListView listView){
        this.context = context;
        this.listView = listView;
    }
    @Override
    protected String doInBackground(ArrayList<String>... params) {
        ArrayList<BasicNameValuePair> param = new ArrayList<>();
        try {
            param.add(new BasicNameValuePair("userId",params[0].get(0)));


            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://cyworld.pgw.jp:1919/test/RoomViewServlet");
            Log.v("URL", "URLセット");
            Log.v("address",post.getURI().toString());
            post.setEntity(new UrlEncodedFormEntity(param, "UTF-8"));

            final HttpResponse response = client.execute(post);
            int status = response.getStatusLine().getStatusCode();

            if (HttpStatus.SC_OK == status) {
                ByteArrayOutputStream os  = new ByteArrayOutputStream();
                response.getEntity().writeTo(os);
                result = os.toString();
                Log.v("result",os.toString());
            }else{
                Log.v("status",Integer.toString(status));
                Log.v("HTTPstatus","接続失敗");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
            roomData = new ArrayList<>();
        try {
            JSONArray json = new JSONArray(s);

            for (int i = 0;i <= json.length();i++){
                JSONObject list = json.getJSONObject(i);
                roomData.add(new RoomBean(list.getString("roomName"),list.getString("comment")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RoomListAdapter adapter = new RoomListAdapter(context,0,roomData);
        listView.setAdapter(adapter);
    }
}
