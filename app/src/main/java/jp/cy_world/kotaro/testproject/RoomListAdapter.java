package jp.cy_world.kotaro.testproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import jp.cy_world.kotaro.testproject.R;

/**
 * Created by kotaro on 15/11/20.
 */
public class RoomListAdapter extends ArrayAdapter<String>{

    LayoutInflater layoutInflater;
    Context context;
    ArrayList<String> list;

    public RoomListAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        String pos = (String)getItem(position);
        if(convertView == null){
            v = layoutInflater.inflate(R.layout.list_layout,parent,false);
        }

        TextView roomName = (TextView)v.findViewById(R.id.roomName);
        TextView roomCom  = (TextView)v.findViewById(R.id.roomCom);

        roomName.setText(pos);
        Log.d("pos",pos);
        roomCom.setText("Comment...");

        return v;
    }
}
