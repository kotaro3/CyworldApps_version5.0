package jp.cy_world.kotaro.testproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by kotaro on 15/11/26.
 */
public class LoginActivity extends Activity implements OnClickListener{

    SpannableStringBuilder sb_address;
    SpannableStringBuilder sb_passwd;
    private EditText input_address;
    private EditText input_passwd;
    TextView createPage;

    private String address;
    private String passwd;
    ArrayList<String> userData;
    LoginTask task;
    SharedPreferences data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        input_address = (EditText)findViewById(R.id.input_id);
        input_passwd = (EditText)findViewById(R.id.input_pass);
        data = PreferenceManager.getDefaultSharedPreferences(this);
        if(data.getString("address",null) != null||data.getString("passwd",null) != null){
            input_address.setText(data.getString("address",null));
            input_passwd.setText(data.getString("passwd",null));
        }

        Button start = (Button)findViewById(R.id.start);
        createPage = (TextView)findViewById(R.id.createPage);

        start.setOnClickListener(this);
        createPage.setOnClickListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();


        data.edit().putString("address", address.toString()).commit();
        data.edit().putString("passwd",passwd.toString()).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                sb_address = (SpannableStringBuilder) input_address.getText();
                sb_passwd = (SpannableStringBuilder) input_passwd.getText();
                address = sb_address.toString();
                passwd = sb_passwd.toString();

                userData = new ArrayList<>();
                userData.add(address);
                userData.add(passwd);

                task = new LoginTask(this);
                task.execute(userData);
                break;
            case R.id.createPage:
                Uri uri = Uri.parse("http://cyworld.pgw.jp:1919/test/RegisterServlet");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
        }
    }
}
