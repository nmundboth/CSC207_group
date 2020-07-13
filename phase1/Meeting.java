package phase1;

import java.io.Serializable;

public class Meeting implements Serializable {

    private String location = "";
    private String date = ""; // Format should be "YYYY-MM-DD" -> NEED TO HAVE TRY/CATCH BLOCK IN CONTROLLER
    private String time = "";
    private Trader proposedBy; // To track who last proposed the meeting, so that only the other person can confirm the meeting (see confirmMeet method in MeetingManager)

    public Meeting(){
    }

    public Meeting(String location, String date, String time){
        this.location = location;
        this.date = date;
        this.time = time;
    }

    /**
     * Checks if a meeting has been proposed (if it is empty, no meeting has been proposed)
     * @return Whether the meeting is empty (hasn't been proposed)
     */
    public boolean isEmpty(){
        return (location.equals("") && date.equals("") && time.equals(""));
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public Trader getProposedBy(){
        return proposedBy;
    }

    // Master setter, might not need the other ones
    public void setAll(String location, String date, String time, Trader proposedBy){
        this.location = location;
        this.date = date;
        this.time = time;
        this.proposedBy = proposedBy;
    }

    // Setters are for traders to modify meeting
    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProposedBy(Trader proposer){
        this.proposedBy = proposer;
    }

    public String toString(){
        return "Location: " + this.location + ", " + "Date: " + this.date + ", " + "Time: " + this.time;
    }
}
