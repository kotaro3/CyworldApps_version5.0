package jp.cy_world.kotaro.testproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/11/25.
 */
public class EditIdeaDialog extends Dialog implements View.OnClickListener {

    Context context;
    MaterialEditText title;
    Room room;

    public EditIdeaDialog(Context context,Room room) {
        super(context);
        this.context = context;
        this.room = new Room(room.getRoomName(),null,room.getRoomId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_form);

        Button add = (Button)findViewById(R.id.add);
        title = (MaterialEditText)findViewById(R.id.title);

        add.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                SpannableStringBuilder sb_title = (SpannableStringBuilder)title.getText();
                String title = sb_title.toString();
                ArrayList<String> data = new ArrayList<>();
                data.add(title);
                data.add(room.getRoomId());
                data.add(room.getRoomName());
                IdeaAddTask task = new IdeaAddTask(context,room);
                task.execute(data);
                //TODO:チケット更新処理
                Log.v("add", "add");

                break;
        }
    }
}
