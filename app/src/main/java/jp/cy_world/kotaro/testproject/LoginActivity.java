package jp.cy_world.kotaro.testproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

        Button start = (Button)findViewById(R.id.start);
        createPage = (TextView)findViewById(R.id.createPage);

        data = PreferenceManager.getDefaultSharedPreferences(this);
        input_address.setText(data.getString("address",null));
        input_passwd.setText(data.getString("passwd",null));

        start.setOnClickListener(this);
        createPage.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                Log.v("start","login");
                sb_address = (SpannableStringBuilder) input_address.getText();
                sb_passwd = (SpannableStringBuilder) input_passwd.getText();
                address = sb_address.toString();
                passwd = sb_passwd.toString();

                if (address.equals("") && passwd.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Login Failed");
                    builder.setMessage("ID and Password is not entered.");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    return;
                }

                userData = new ArrayList<>();
                userData.add(address);
                userData.add(passwd);

                data.edit().putString("address", address.toString()).commit();
                data.edit().putString("passwd",passwd.toString()).commit();
                task = new LoginTask(this);
                task.execute(userData);

                break;

            case R.id.createPage:
                Uri uri = Uri.parse("http://cyworld.pgw.jp:1919/cyworld/RegisterServlet");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
        }
    }
}
