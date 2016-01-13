package jp.cy_world.kotaro.testproject;

/**
 * Created by kotaro on 16/01/08.
 */
public class Ticket {

    private String ticketId;
    private String ticketData;
    private String roomId;

    public Ticket(String ticketId, String ticketData,String roomId){
        this.ticketId = ticketId;
        this.ticketData = ticketData;
        this.roomId = roomId;
    }

    public String getTicketId(){
        return ticketId;
    }

    public String getTicketData(){
        return ticketData;
    }

    public String getRoomId(){
        return  roomId;
    }

}
