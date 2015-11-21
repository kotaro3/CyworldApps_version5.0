package jp.cy_world.kotaro.testproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/11/20.
 */
public class RecyclerTest extends Activity {

    private RecyclerView recycleview;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);

        recycleview = (RecyclerView)findViewById(R.id.recycle_list);
        recycleview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycleview.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        data.add("aa");
        data.add("bb");
        data.add("cc");
        adapter = new RecyclerAdaprer(data);
        recycleview.setAdapter(adapter);


    }
}
