package jp.cy_world.kotaro.testproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Window;

/**
 * Created by kotaro on 15/11/22.
 */
public class IdeaListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setTheme(android.R.style.Theme_Material_Light);
        setContentView(R.layout.idea_list_layout);



    }
}
