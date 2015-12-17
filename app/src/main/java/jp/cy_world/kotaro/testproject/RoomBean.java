package jp.cy_world.kotaro.testproject;

/**
 * Created by kotaro on 15/11/24.
 */
public class RoomBean {

    String roomName;
    String roomCom;

    public RoomBean(String roomName, String roomCom) {
        this.roomName = roomName;
        this.roomCom = roomCom;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomCom() {
        return roomCom;
    }

}
