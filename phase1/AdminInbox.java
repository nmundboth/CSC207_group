package phase1;

import java.io.Serializable;
import java.util.List;

/** Reference from: https://www.dummies.com/programming/java/how-to-use-javadoc-to-document-your-classes/
 * Represents the Inbox of an Admin
 * @author Navnee Mundboth
 * @author James Veale
 * @author Sasan Makvandi
 */
public class AdminInbox extends Inbox implements Serializable {

    /**
     * A list representing the undoFrozen sub-inbox for the Admin to view
     */
    static List<Trader> undoFrozen;

    /**
     * The number of unread messages form other traders
     */
    private int traderUnread ;

    /**
     * The number of unread messages form admins
     */
    private int admiNotiUnread;

    /**
     * The number of unread unfreezing requests from traders
     */
    private int undoFrozenUnread;


    /**
     * Constructor for the AdminInbox class
     * @param traderNoti is all text-based notifications for an Admin from Traders
     * @param adminNotifs is all text-based notifications for an Admin from another Admin/System
     */
    public AdminInbox(List<Trade> trades, List<String> traderNoti, List<String> adminNotifs){
        super(trades, traderNoti, adminNotifs);
        this.admiNotiUnread = 0;
        this.traderUnread = 0;
        this.undoFrozenUnread = 0;
    }
    // Returns messages from Traders

    /** Gets the amount of unread notifications from other Traders to Admin.
     *
     * @return an integer which shows the amount of unread notifications from other Traders.
     */
    public int getTradersUnread(){return this.traderUnread;}

    /** Gets the amount of unread notifications from other Admins to Admin.
     *
     * @return an integer which shows the amount of unread notifications from other Admins.
     */
    public int getAdmiNotiUnread(){return this.admiNotiUnread;}

    /** Gets the amount of unread unfreezing notifications from other Traders to Admin.
     *
     * @return an integer which shows the amount of unread unfreezing notifications from other Traders.
     */
    public int getUndoFrozenUnread(){return this.undoFrozenUnread;}

    /** Gets the total amount of unread notifications to Admin.
     *
     * @return an integer which shows the total amount of unread notifications.
     */
    public int getTotalUnread(){return this.traderUnread + this.admiNotiUnread + this.undoFrozenUnread;}

    /** Gets a list of Traders who requested to have their accounts unfrozen.
     *
     * @return a list of Traders who requested to have their accounts unfrozen.
     */
    public List<Trader> getUndoFrozen(){return undoFrozen; }

    /** Unfreezes all Traders who requested to have their accounts unfrozen.
     *
     * @param index is the position of a specific message within the sub-inbox
     */
    public void showUndoFrozen(int index){
        Trader temp = undoFrozen.get(index);
        undoFrozen.remove(index);
        this.undoFrozenUnread -= 1;
        if(temp.isFrozen()){
            temp.frozen = false;
        }
    }

    /**
     * This method serves as a tool for an Admin to access a message within the AdminNoti sub-inbox
     * Admin is represented with a list of messages in AdminOptions from which Admin can chose one to open and read
     * @param index is the position of a specific message within the sub-inbox
     * @return returns a String that contains the message for Admin
     */
    public String showAdminNoti(int index){
        String temp = this.getAdmiNoti().get(index);
        this.getAdmiNoti().remove(index);
        this.admiNotiUnread -= 1;
        return temp;
    }

    /**
     * This method serves as a tool for an Admin to access a message within the TraderNoti sub-inbox
     * Admin is represented with a list of messages in AdminOptions from which Admin can choose one to open and read
     * @param index is the position of a specific message within the sub-inbox
     * @return returns the String that contains the message for Admin
     */
    public String showTraderNoti(int index){
        String temp = this.getTraderNoti().get(index);
        this.getTraderNoti().remove(index);
        this.traderUnread -= 1;
        return temp;
    }

}
