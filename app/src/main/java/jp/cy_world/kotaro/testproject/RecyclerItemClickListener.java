package jp.cy_world.kotaro.testproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by kotaro on 16/02/01.
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener{

    GestureDetector gesture;
    private OnItemClickListener itemlistener;

    public RecyclerItemClickListener(Context context,OnItemClickListener listener){
        itemlistener  = listener;
        gesture = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView v, MotionEvent e) {
        View childView = v.findChildViewUnder(e.getX(),e.getY());
        if (childView != null && itemlistener != null && gesture.onTouchEvent(e)){
            childView.setPressed(true);
            itemlistener.onItemClick(childView,v.getChildPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface OnItemClickListener{
        public void onItemClick(View view,int position);
    }
}
