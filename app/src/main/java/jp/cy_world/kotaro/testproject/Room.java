package jp.cy_world.kotaro.testproject;

import java.io.Serializable;

/**
 * Created by kotaro on 15/11/24.
 */
public class Room implements Serializable {

    private String roomName;
    private String roomCom;
    private String roomid;

    public Room(String roomName, String roomCom, String roomid) {
        this.roomName = roomName;
        this.roomCom = roomCom;
        this.roomid = roomid;
    }

    public String getRoomId()   { return roomid;   }

    public String getRoomName() { return roomName; }

    public String getRoomCom()  { return roomCom;  }

}
