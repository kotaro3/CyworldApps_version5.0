package jp.cy_world.kotaro.testproject;

/**
 * Created by kotaro on 16/01/08.
 */
public class TicketBean {

    private String ticketId;
    private String ticketData;

    public TicketBean(String ticketId,String ticketData){
        this.ticketId = ticketId;
        this.ticketData = ticketData;
    }

    public String getTicketId(){
        return ticketId;
    }

    public String getTicketData(){
        return ticketData;
    }

}
