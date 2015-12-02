package jp.cy_world.kotaro.testproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by kotaro on 15/11/25.
 */
public class EditIdeaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_form);

        Button add = (Button)findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("jp.cy_world.kotaro.testproject","jp.cy_world.kotaro.testproject.IdeaListActivity");
            }
        });
    }
}
