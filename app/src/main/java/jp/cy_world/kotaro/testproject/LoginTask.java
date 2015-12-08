package jp.cy_world.kotaro.testproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;
import java.util.ArrayList;


/**
 * Created by kotaro on 15/11/25.
 */
public class LoginTask extends AsyncTask<ArrayList<String>,Void,String> {

    String result,str;
    Context context;
    ArrayList<BasicNameValuePair> param ;
    ArrayList<String> prefData;

    public LoginTask(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(ArrayList<String>... params) {
        try {

            param = new ArrayList<>();

            param.add(new BasicNameValuePair("address",params[0].get(0)));
            param.add(new BasicNameValuePair("passwd", params[0].get(1)));

            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://cyworld.pgw.jp:8080/test/AndroidLoginServlet");
            Log.v("URL", "URLセット");
            Log.v("address",post.getURI().toString());
            post.setEntity(new UrlEncodedFormEntity(param, "UTF-8"));

            final HttpResponse response = client.execute(post);
            int status = response.getStatusLine().getStatusCode();

            if (HttpStatus.SC_OK == status) {
                   ByteArrayOutputStream os  = new ByteArrayOutputStream();
                    response.getEntity().writeTo(os);
                    result = os.toString();
                    Log.v("result",os.toString());
            }else{
                Log.v("HTTPstatus","接続失敗");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String aString) {
        str = aString;
        String[] strs = str.split(",",0);
        prefData = new ArrayList<>();

        if(strs[0].equals("True")){
            Log.d("task", "task Success");
            for (int i = 1;i <= prefData.size();i++){
                prefData.add(strs[i]);
            }
            Intent intent = new Intent();
            intent.setClassName("jp.cy_world.kotaro.testproject", "jp.cy_world.kotaro.testproject.RoomListActivity");
            intent.putExtra("userData",prefData);
            context.startActivity(intent);

        }else if (strs[0].equals("False")){
            Toast.makeText(context,"Login Failed",Toast.LENGTH_LONG).show();
            Log.v("login","失敗");
        }

    }


}
