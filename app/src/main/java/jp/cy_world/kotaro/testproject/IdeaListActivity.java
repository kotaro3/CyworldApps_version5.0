package jp.cy_world.kotaro.testproject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/11/22.
 */
public class IdeaListActivity extends AppCompatActivity implements View.OnClickListener{
    Room room;
    RecyclerView ideaList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idea_list_layout);

        ideaList = (RecyclerView)findViewById(R.id.recyclerView);
        ideaList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        ideaList.setHasFixedSize(true);
        ideaList.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //TODO:チケット編集処理記入
            }
        }));

        room = (Room)getIntent().getSerializableExtra("roomData");
        context = this;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        boolean result;

        switch (id) {
            case android.R.id.home:
                Log.v("log", "home");
                NavUtils.navigateUpFromSameTask(this);
            result = true;
            break;
            default:
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }

    //icon click
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.add_button:
                Dialog edit = new EditIdeaDialog(this,room);
                edit.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                edit.show();
                break;
            case android.R.id.icon:
                Log.v("Log","Click Home");
                Toast.makeText(this,"",Toast.LENGTH_LONG).show();
                break;

        }
    }

}
