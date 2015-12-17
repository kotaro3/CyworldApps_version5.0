package jp.cy_world.kotaro.testproject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;


/**
 * Created by kotaro on 15/11/25.
 */
public class LoginTask extends AsyncTask<ArrayList<String>,Void,String>{

    String result;
    Context context;
    ArrayList<BasicNameValuePair> param ;
    User user;
    Intent intent = new Intent();

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
            HttpPost post = new HttpPost("http://cyworld.pgw.jp:1919/test/AndroidLoginServlet");
            Log.v("URL", "URLセット");
            Log.v("address",post.getURI().toString());
            post.setEntity(new UrlEncodedFormEntity(param, "UTF-8"));

            final HttpResponse response = client.execute(post);
            int status = response.getStatusLine().getStatusCode();

            if (HttpStatus.SC_OK == status) {
                Log.v("",Integer.toString(status));
                   ByteArrayOutputStream os  = new ByteArrayOutputStream();
                    response.getEntity().writeTo(os);
                    result = os.toString();
                    Log.v("result",os.toString());
            }else{
                Log.v("status",Integer.toString(status));
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

        user = new User();
        try {
            JSONObject obj = new JSONObject(aString);
            user.setUserId(obj.getString("userID"));
            user.setAddress(obj.getString("address"));
            user.setImgPath(obj.getString("ImgPath"));
            user.setUserName(obj.getString("name"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("str", aString);
        if(user.getUserId() != null){
            Log.d("task", "task Success");
            intent.putExtra("userData", user);
            intent.setClassName("jp.cy_world.kotaro.testproject", "jp.cy_world.kotaro.testproject.RoomListActivity");
            context.startActivity(intent);

        }else if (aString.equals("False")){
            Toast.makeText(context,"Login Failed",Toast.LENGTH_LONG).show();
            Log.v("login","失敗");
        }else{
            Toast.makeText(context,"プログラムエラー",Toast.LENGTH_LONG).show();
        }

    }


}
