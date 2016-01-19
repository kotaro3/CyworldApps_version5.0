package jp.cy_world.kotaro.testproject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/11/22.
 */
public class IdeaListActivity extends AppCompatActivity implements View.OnClickListener ,MenuItem.OnMenuItemClickListener{

    Room room;
    RecyclerView ideaList;
    Menu mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idea_list_layout);

        ideaList = (RecyclerView)findViewById(R.id.recyclerView);
        ideaList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        ideaList.setHasFixedSize(true);
        room = (Room)getIntent().getSerializableExtra("roomData");

        Toolbar toolbar = (Toolbar) findViewById(R.id.idea_toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(room.getRoomName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        toolbar.inflateMenu(R.menu.menu_main);


        IdeaGetTask task = new IdeaGetTask(this,ideaList);
        task.execute(room.getRoomId());

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add_button);


        add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_button:
                Dialog edit = new EditIdeaDialog(this,room);
                edit.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                edit.show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(0, 0, 0, "Settings").setIcon(android.R.drawable.ic_dialog_info);
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
