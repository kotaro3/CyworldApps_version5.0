package jp.cy_world.kotaro.testproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/11/20.
 */
public class RecyclerAdaprer extends RecyclerView.Adapter<RecyclerAdaprer.ItemViewHolder> {

    ArrayList<String> data;

    //複数のItemdataにアクセスできるようにするための静的クラス
    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView roomName;
        TextView roomCom;

        public ItemViewHolder(View v) {
            super(v);
            roomName = (TextView)v.findViewById(R.id.roomName);
            roomCom = (TextView)v.findViewById(R.id.roomCom);
        }
    }

    public RecyclerAdaprer(ArrayList<String> list){
        this.data = list;
    }


    //Viewを生成（LayoutManagerに含まれる）
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        return new ItemViewHolder(v);
    }

    //Viewの再配置（LayoutManagerに含まれる）
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        String name;
        String com = "Comment...";
        name = data.get(position);
        holder.roomName.setText(name);
        holder.roomCom.setText(com);
    }

    //アイテムの数を取得（LayoutManagerに含まれる）
    @Override
    public int getItemCount() {
        return data.size();
    }


}
