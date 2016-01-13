package jp.cy_world.kotaro.testproject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/11/22.
 */
public class IdeaListActivity extends Activity implements View.OnClickListener {

    Room room;
    RecyclerView ideaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idea_list_layout);

        ideaList = (RecyclerView)findViewById(R.id.recyclerView);
        ideaList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        ideaList.setHasFixedSize(true);
        TextView roomName = (TextView)findViewById(R.id.roomName);
        room = (Room)getIntent().getSerializableExtra("roomData");
        roomName.setText(room.getRoomName());
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
    protected void onResume() {
        super.onResume();
        IdeaGetTask task = new IdeaGetTask(this,ideaList);
        room = (Room)getIntent().getSerializableExtra("roomData");

        task.execute(room.getRoomId());
    }
}
