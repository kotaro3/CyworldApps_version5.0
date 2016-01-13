package jp.cy_world.kotaro.testproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/11/20.
 */
public class RoomListAdapter extends ArrayAdapter<Room>{

    LayoutInflater layoutInflater;
    Context context;
    ArrayList<Room> list;

    public RoomListAdapter(Context context, int resource, ArrayList<Room> list) {
        super(context,resource,list);
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Room room = (Room)getItem(position);
        if(convertView == null){
            v = layoutInflater.inflate(R.layout.list_layout,parent,false);
        }

        TextView roomName = (TextView)v.findViewById(R.id.roomName);
        TextView roomCom  = (TextView)v.findViewById(R.id.roomCom);

        roomName.setText(room.getRoomName());
        Log.d("pos",room.getRoomName());
        roomCom.setText(room.getRoomCom());

        return v;
    }
}
