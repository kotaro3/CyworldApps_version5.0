package jp.cy_world.kotaro.testproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Window;

import java.util.ArrayList;


/**
 * Created by kotaro on 16/01/20.
 */
public class Splash extends Activity {

    SharedPreferences data;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_layout);
        data = PreferenceManager.getDefaultSharedPreferences(this);
        Handler hdl = new Handler();
        hdl.postDelayed(new splashHandler(this), 2000);

    }

    private class splashHandler implements Runnable {

        Context context;

        public splashHandler(Context context){
        this.context = context;
        }



        @Override
        public void run() {

            String address = data.getString("address",null);
            String passwd = data.getString("passwd",null);

            if (address == null && passwd == null) {
                intent = new Intent();
                intent.setClassName("jp.cy_world.kotaro.testproject", "jp.cy_world.kotaro.testproject.LoginActivity");
                startActivity(intent);
        }else if (!address.equals("") && !passwd.equals("")){
                ArrayList<String> userData = new ArrayList<>();
                userData.add(address);
                userData.add(passwd);
                LoginTask  task = new LoginTask(context);
                task.execute(userData);
            }else {
                intent = new Intent();
                intent.setClassName("jp.cy_world.kotaro.testproject", "jp.cy_world.kotaro.testproject.LoginActivity");
                startActivity(intent);
            }

            Splash.this.finish();
        }
    }
}
