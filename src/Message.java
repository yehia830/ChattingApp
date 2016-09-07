import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Message {
    private LocalDateTime dateTime;
    private String time;
    private String dayOfWeek;
    private String messageContent;

    public Message() {

        dateTime = LocalDateTime.now();
        DateTimeFormatter myTimeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        time = dateTime.format(myTimeFormatter);
        DateTimeFormatter myDayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE");
        dayOfWeek = dateTime.format(myDayOfWeekFormatter);
    }



    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

}