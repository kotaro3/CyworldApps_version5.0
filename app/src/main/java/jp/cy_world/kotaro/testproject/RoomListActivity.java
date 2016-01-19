package jp.cy_world.kotaro.testproject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
public class RoomListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,MenuItem.OnMenuItemClickListener{


    //取得データを入れる配列
    ArrayList<String> userData;
    User user;
    RoomListTask task;
    final int a = 0;

    Menu mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomlist_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("RoomList");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        toolbar.inflateMenu(R.menu.menu_main);

        user = (User) getIntent().getSerializableExtra("userData");
        userData = new ArrayList<>();
        Log.v("userId", user.getUserId());
        userData.add(user.getUserId());
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
        menu.add(0, a, 0, "Settings").setIcon(android.R.drawable.ic_dialog_info);
        mainMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        final int action = event.getAction();
        final int keyCode = event.getKeyCode();
        if (action == KeyEvent.ACTION_UP) {
            // メニュー表示
            if (keyCode == KeyEvent.KEYCODE_MENU) {
                if (mainMenu != null) {
                    mainMenu.performIdentifierAction(R.id.action_settings, 0);
                }
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "settings clicked 2", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
