package jp.cy_world.kotaro.testproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kotaro on 16/01/08.
 */
public class IdeaGetTask extends AsyncTask<String, Integer,String> implements DialogInterface.OnCancelListener {

    ProgressDialog dialog;
    RecyclerView recyclerView;
    Context context;
    String result;
    String roomid;

    public IdeaGetTask(Context context,RecyclerView recyclerView){
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("部屋データ取得");
        dialog.setMessage("Now Loading ...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(true);
        dialog.setOnCancelListener(this);
        dialog.setMax(100);
        dialog.setProgress(0);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        publishProgress(10);

        roomid = params[0].toString();

        publishProgress(30);

        ArrayList<BasicNameValuePair> param = new ArrayList<>();

        publishProgress(50);

        param.add(new BasicNameValuePair("roomId",roomid));

        publishProgress(70);

        HttpAccess access = new HttpAccess();

        result = access.DBAccess("http://cyworld.pgw.jp:1919/test/TicketGetServlet",param);

        publishProgress(100);

        return result;
    }

    @Override
    protected void onPostExecute(String roomBeans) {
        ArrayList<Ticket> ticketData = new ArrayList<>();
        try {
//            JSONArray array = new JSONArray(roomBeans);
//
//            for (int i = 0;i <= array.length();i++){
//                JSONObject obj = array.getJSONObject(i);
//                ticketData.add(new Ticket(obj.getString("ticketID"),obj.getString("ticketData"),roomid));
//            }
            JSONObject obj = new JSONObject(roomBeans);
            ticketData.add(new Ticket(obj.getString("TicketId"),obj.getString("TicketData"),roomid));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        IdeaListAdapter adapter = new IdeaListAdapter(context,ticketData);
        recyclerView.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        this.cancel(true);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values[0]);
    }
}
