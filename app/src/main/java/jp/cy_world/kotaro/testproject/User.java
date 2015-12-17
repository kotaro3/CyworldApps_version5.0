package jp.cy_world.kotaro.testproject;

import android.app.Application;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kotaro on 15/12/08.
 */
public class User implements Serializable{
    private String userId;
    private String userName;
    private String address;
    private String passwd;
    private String imgPath;

    //getter
    public String getUserId() {return userId;}
    public String getUserName() {return userName;}
    public String getAddress() {return address;}
    public String getPasswd() {return passwd;}
    public String getImgPath() {return imgPath;}

    //setter
    public void setUserId(String userId) {this.userId = userId;}
    public void setUserName(String userName) {this.userName = userName;}
    public void setAddress(String address) {this.address = address;}
    public void setPasswd(String passwd) {this.passwd = passwd;}
    public void setImgPath(String imgPath) {this.imgPath = imgPath;}

}
