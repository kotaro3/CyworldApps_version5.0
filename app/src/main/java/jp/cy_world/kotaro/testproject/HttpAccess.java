package jp.cy_world.kotaro.testproject;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by kotaro on 16/01/11.
 */
public class HttpAccess {

    String result;

    public String DBAccess(String url, ArrayList<BasicNameValuePair> param) {
        try {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        Log.v("URL", "URLセット");
        Log.v("address", post.getURI().toString());


            post.setEntity(new UrlEncodedFormEntity(param, "UTF-8"));
            final HttpResponse response = client.execute(post);
            int status = response.getStatusLine().getStatusCode();

            if (HttpStatus.SC_OK == status) {
                Log.v("", Integer.toString(status));
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                response.getEntity().writeTo(os);
                result = os.toString();
                Log.v("result", os.toString());
            } else {
                Log.v("status", Integer.toString(status));
                Log.v("HTTPstatus", "接続失敗");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
