package jp.cy_world.kotaro.testproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/11/20.
 */
public class RoomListActivity extends Activity implements AdapterView.OnItemClickListener{


    //取得データを入れる配列
    ArrayList<String> data;
    final int a = 0;
    final int b = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Material_Light);
        setContentView(R.layout.roomlist_layout);
        data = new ArrayList<>();
        ListView listView = (ListView)findViewById(R.id.roomList);
        Intent intent = getIntent();
        data.add(intent.getStringExtra("userData"));


        RoomListAdapter adapter = new RoomListAdapter(this,0,data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            intent.setClassName("jp.cy_world.kotaro.testproject","jp.cy_world.kotaro.testproject.IdeaListActivity");
            startActivity(intent);
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        menu.add(0, a, 0, "User");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case a:
                Log.d("Menu", "Select Menu A");
                return true;

            case b:
                Log.d("Menu","Select Menu B");
                return true;

        }
            return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

}
