package jp.cy_world.kotaro.testproject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**
 * Created by kotaro on 16/01/11.
 */
public class IdeaAddTask extends AsyncTask<ArrayList<String>,Integer,String> {

    String result;
    Room room;
    Context context;

    public IdeaAddTask(Context context,Room room) {
        this.context = context;
        this.room = new Room(room.getRoomName(),null,room.getRoomId());
    }

    @Override
    protected String doInBackground(ArrayList<String>... params) {
        ArrayList<BasicNameValuePair> param = new ArrayList<>();
        param.add(new BasicNameValuePair("title",params[0].get(0).toString()));
        param.add(new BasicNameValuePair("roomId",params[0].get(1).toString()));
        HttpAccess access = new HttpAccess();

        result = access.DBAccess("http://cyworld.pgw.jp:1919/cyworld/AddTicketServlet",param);

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        //TODO:IdeaListActivity更新

        Intent intent = new Intent();
        intent.putExtra("roomData",room);
        intent.setClassName("jp.cy_world.kotaro.testproject","jp.cy_world.kotaro.testproject.IdeaListActivity");
        context.startActivity(intent);

    }
}
