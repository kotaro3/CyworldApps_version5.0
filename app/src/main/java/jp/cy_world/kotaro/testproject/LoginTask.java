package jp.cy_world.kotaro.testproject;

import android.app.Dialog;
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

            param = new ArrayList<>();

            param.add(new BasicNameValuePair("address",params[0].get(0)));
            param.add(new BasicNameValuePair("passwd", params[0].get(1)));

            HttpAccess access = new HttpAccess();
            result = access.DBAccess("http://cyworld.pgw.jp:1919/cyworld/AndroidLoginServlet",param);

        return result;
    }

    @Override
    protected void onPostExecute(String aString) {

        if(aString == null){
            intent.setClassName("jp.cy_world.kotaro.testproject", "jp.cy_world.kotaro.testproject.LoginActivity");
            context.startActivity(intent);
        }else {
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


}
