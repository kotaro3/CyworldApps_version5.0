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

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ItemViewHolder(View v) {
            super(v);
            textView = (TextView)v.findViewById(R.id.roomName);
        }
    }

    public RecyclerAdaprer(ArrayList<String> list){
        this.data = list;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        String str;
        str = data.get(position);
        holder.textView.setText(str);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
