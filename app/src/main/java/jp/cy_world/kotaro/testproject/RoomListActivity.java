package jp.cy_world.kotaro.testproject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/11/20.
 */
public class RoomListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener{


    //取得データを入れる配列
    ArrayList<String> userData;
    User user;
    RoomListTask task;
    final int a = 0;
    Menu mainMenu;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomlist_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("RoomList");
        }


        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == a) {
                    Intent intent = new Intent();
                    intent.setClassName("jp.cy_world.kotaro.testproject", "jp.cy_world.kotaro.testproject.LoginActivity");
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(RoomListActivity.this);
                    pref.edit().putString("address", "").commit();
                    pref.edit().putString("passwd", "").commit();
                    startActivity(intent);

                }
                return false;
            }
        });


        toolbar.setNavigationOnClickListener(this);

       SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(this);

        user = (User) getIntent().getSerializableExtra("userData");

        userData = new ArrayList<>();
        String id = data.getString("userId",null);
        Log.v("userId", id);
        userData.add(id);
        ListView listView = (ListView) findViewById(R.id.roomList);

        task = new RoomListTask(this, listView);
        task.execute(userData);


        listView.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        ListView list = (ListView) parent;
        Room room = (Room) list.getItemAtPosition(pos);

        Intent intent = new Intent();
        intent.putExtra("roomData", room);
        intent.setClassName("jp.cy_world.kotaro.testproject", "jp.cy_world.kotaro.testproject.IdeaListActivity");
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(Menu.NONE, a, Menu.NONE, "Logout");
        mainMenu = menu;

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean ret = true;
        switch (item.getItemId()){

        }
        return ret;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        final int action = event.getAction();
        if (action == KeyEvent.ACTION_UP) {
            switch (event.getKeyCode()){
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(this,"icon click",Toast.LENGTH_LONG).show();
    }
}
