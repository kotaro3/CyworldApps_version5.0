package jp.cy_world.kotaro.testproject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by kotaro on 15/11/22.
 */
public class IdeaListActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Material_Light);
        setContentView(R.layout.idea_list_layout);

        CardView card = (CardView)findViewById(R.id.card);
        Button  add_b = (Button)findViewById(R.id.add_button);

        add_b.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClassName("jp.cy_world.kotaro.testproject","jp.cy_world.kotaro.testproject.EditIdeaActivity");
        startActivity(intent);
    }
}
