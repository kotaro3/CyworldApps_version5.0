package jp.cy_world.kotaro.testproject;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by kotaro on 15/12/23.
 */
public class IdeaListAdapter extends RecyclerView.Adapter<IdeaListAdapter.ViewHolder> {

    private Map<String,String> data;
    private LayoutInflater inflater;

    public IdeaListAdapter(Context context, Map<String,String> data) {
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
            Set<String> set = data.keySet();
            String[] a = set.toArray(new String[0]);
            holder.title.setText(data.get(a[position]));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //view保持クラス
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public ViewHolder(View v) {
            super(v);
            title = (TextView)v.findViewById(R.id.idea_title);
        }
    }
}
