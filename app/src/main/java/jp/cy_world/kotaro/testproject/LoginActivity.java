package jp.cy_world.kotaro.testproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kotaro on 15/11/26.
 */
public class LoginActivity extends Activity implements OnClickListener{

    private SpannableStringBuilder sb_address;
    private SpannableStringBuilder sb_passwd;
    private EditText input_address;

    private String address;
    private String passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        EditText input_address = (EditText)findViewById(R.id.input_id);
        EditText input_passwd = (EditText)findViewById(R.id.input_pass);
        Button start = (Button)findViewById(R.id.start);
        TextView createPage = (TextView)findViewById(R.id.createPage);

        sb_address = (SpannableStringBuilder) input_address.getText();
        sb_passwd = (SpannableStringBuilder) input_passwd.getText();
        address = sb_address.toString();
        passwd = sb_passwd.toString();

        Log.d("address",address);
        Log.d("passwd",passwd);

        start.setOnClickListener(this);
        createPage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                LoginTask task = new LoginTask();
                if((task.execute(address, passwd)).equals("True")){
                    Intent intent = new Intent();
                    intent.setClassName("jp.cy_world.kotaro.testproject","jp.cy_world.kotaro.testproject.RoomListActivity");
                    startActivity(intent);
                    Toast.makeText(this,"Login Success!",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this,"Login Failed",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.createPage:

                break;
        }
    }
}
