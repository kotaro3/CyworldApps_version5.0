package jp.cy_world.kotaro.testproject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/11/22.
 */
public class IdeaListActivity extends Activity implements View.OnClickListener {

    RoomBean room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Material_Light);
        setContentView(R.layout.idea_list_layout);

        RecyclerView ideaList = (RecyclerView)findViewById(R.id.recyclerView);
        ideaList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        ideaList.setHasFixedSize(true);
        ArrayList<String> data = new ArrayList<>();
        room = (RoomBean)getIntent().getSerializableExtra("roomData");
        IdeaGetTask task = new IdeaGetTask(this,ideaList);
        task.execute(room.getRoomId());

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add_button);


        add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClassName("jp.cy_world.kotaro.testproject", "jp.cy_world.kotaro.testproject.EditIdeaActivity");
        startActivity(intent);
    }
}
