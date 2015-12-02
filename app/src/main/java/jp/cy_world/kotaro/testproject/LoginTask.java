package jp.cy_world.kotaro.testproject;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kotaro on 15/11/25.
 */
public class LoginTask extends AsyncTask<String,Void,String> {

    URL url;
    String result;
    HttpURLConnection con = null;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            ArrayList<BasicNameValuePair> param = new ArrayList<>();
            Log.d("params",params[0]);
            Log.d("params",params[1]);
            Log.d("","");
            param.add(new BasicNameValuePair("address",params[0]));
            param.add(new BasicNameValuePair("passwd", params[1]));

            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://cyworld.pgw.jp:8080/test/AndroidLoginServlet");
            Log.d("URL", "URLセット");
            Log.d("address",post.getURI().toString());
            post.setEntity(new UrlEncodedFormEntity(param, "UTF-8"));

            final HttpResponse response = client.execute(post);
            int status = response.getStatusLine().getStatusCode();

            if (HttpStatus.SC_OK == status) {
                Log.v("skksk", "kdkdk");
                   ByteArrayOutputStream os  = new ByteArrayOutputStream();
                    response.getEntity().writeTo(os);
                    result = os.toString();
                    Log.v("result",os.toString());
            }else{
                Log.d("HTTPstatus","接続失敗");
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
        super.onPostExecute(aString);
    }

    String stringConv(InputStream stream) throws UnsupportedEncodingException,IOException{
        Log.d("StringConv","String変換");
        StringBuffer sb = new StringBuffer();
        String line = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(stream,"UTF-8"));
        while ((line = br.readLine()) != null){
            sb.append(line);
        }
        stream.close();
        return sb.toString();
    }

}
