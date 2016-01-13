package jp.cy_world.kotaro.testproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/11/20.
 */
public class RoomListActivity extends Activity implements AdapterView.OnItemClickListener{


    //取得データを入れる配列
    ArrayList<String> userData;
    User user;
    RoomListTask task;
    String[] data;
    final int a = 0;
    final int b = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomlist_layout);

        user = (User)getIntent().getSerializableExtra("userData");
        userData = new ArrayList<>();
        Log.v("userId",user.getUserId());
        userData.add(user.getUserId());
        ListView listView = (ListView)findViewById(R.id.roomList);

        task = new RoomListTask(this,listView);
        task.execute(userData);

        listView.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            ListView list = (ListView)parent;
            Room room = (Room)list.getItemAtPosition(pos);

            Intent intent = new Intent();
            intent.putExtra("roomData",room);
            intent.setClassName("jp.cy_world.kotaro.testproject", "jp.cy_world.kotaro.testproject.IdeaListActivity");
            startActivity(intent);
    }




}
