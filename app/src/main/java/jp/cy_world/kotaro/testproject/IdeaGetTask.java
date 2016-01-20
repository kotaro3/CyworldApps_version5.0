package jp.cy_world.kotaro.testproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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

        result = access.DBAccess("http://cyworld.pgw.jp:1919/cyworld/TicketGetServlet",param);

        publishProgress(100);

        return result;
    }

    @Override
    protected void onPostExecute(String tickets) {
        ArrayList<Ticket> ticketData = new ArrayList<>();
        Map<String,String> map = new LinkedHashMap<>();

        try {
            ObjectMapper mapper = new ObjectMapper();

            map = mapper.readValue(tickets, new TypeReference<LinkedHashMap<String,String>>(){});

        } catch (IOException e) {
                e.printStackTrace();
            }

//            for (int i = 0;i <= array.length();i++){
//                JSONObject obj = array.getJSONObject(i);
//                ticketData.add(new Ticket(obj.getString("11"),null,roomid));
//            }

//        ticketData.add(new Ticket(null,obj.keys().getClass().getName(),roomid));

        IdeaListAdapter adapter = new IdeaListAdapter(context,map);
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
