package jp.cy_world.kotaro.testproject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/12/03.
 */
public class RoomListTask extends AsyncTask<ArrayList<String>,Void,String> {

    Context context;
    ListView listView;
    ArrayList<Room> roomData;

    String result;

    public RoomListTask(Context context,ListView listView){
        this.context = context;
        this.listView = listView;
    }
    @Override
    protected String doInBackground(ArrayList<String>... params) {
        ArrayList<BasicNameValuePair> param = new ArrayList<>();
        param.add(new BasicNameValuePair("userId",params[0].get(0)));

        HttpAccess access = new HttpAccess();
        result = access.DBAccess("http://cyworld.pgw.jp:1919/cyworld/RoomViewServlet",param);
        
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
            roomData = new ArrayList<>();
        try {
            JSONArray json = new JSONArray(s);

            for (int i = 0;i <= json.length();i++){
                JSONObject list = json.getJSONObject(i);
                roomData.add(new Room(list.getString("roomName"),list.getString("comment"),list.getString("roomID")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RoomListAdapter adapter = new RoomListAdapter(context,0,roomData);
        listView.setAdapter(adapter);
    }
}
