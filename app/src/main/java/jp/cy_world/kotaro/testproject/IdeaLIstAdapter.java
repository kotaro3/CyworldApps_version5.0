package jp.cy_world.kotaro.testproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/12/23.
 */
public class IdeaLIstAdapter extends RecyclerView.Adapter<IdeaLIstAdapter.ViewHolder> {

    private ArrayList<String> data;
    private LayoutInflater inflater;

    public IdeaLIstAdapter(Context context, ArrayList<String> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.idea_layout,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = (String)data.get(position);
        holder.title.setText(title);
        holder.detail.setText("説明文");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //view保持クラス
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView detail;
        public ViewHolder(View v) {
            super(v);
            title = (TextView)v.findViewById(R.id.idea_title);
            detail = (TextView)v.findViewById(R.id.ideaDetale);
        }
    }
}
