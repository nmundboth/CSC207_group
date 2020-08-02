package phase1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Main Menu</h1>
 * <p>The first menu that a user sees upon starting the program.</p>
 */
public class TradingSystem {

    private phase1.UserSerialization us;
    private phase1.UserCatalogue uc;
    private String menuOptions;

    /**
     * Has an instance of UserSerialization (for saving) and UserCatalogue (for tracking all user data).
     */
    public TradingSystem() throws Exception {
        us = new phase1.UserSerialization();
        uc = new phase1.UserCatalogue(us.deserialize());
        menuOptions = "To perform an action, type the corresponding number.\n1. Login\n2. Register\n" +
                "To exit, type 'exit'.";
    }

    /**
     * Presents the user with the option to either login or register an account. User can type 'exit' toe xit the
     * program.
     */
    public void run() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        System.out.println(menuOptions);
        try {
            String input = br.readLine();
            while (!input.equals("exit")){
                if (input.equals("1")){
                    login(br);
                    System.out.println(menuOptions);
                }
                else if (input.equals("2")){
                    register(br);
                    System.out.println(menuOptions);
                }
                input = br.readLine();
            }
            us.toSerialize(uc.userBase);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
    }

    // Takes user input, and checks it against the user catalogue, then logs the user in if the user exists and the pw
    // is correct.
    private void login(BufferedReader br){
        try{
            System.out.println("Please enter your Username: ");
            String input = br.readLine();
            while(!input.equals("exit")) {
                if (uc.inUserBase(input)) {
                    phase1.User currUser = uc.getUserByName(input);
                    System.out.println("Please enter your password: ");
                    input = br.readLine();
                    if (!input.equals("exit")) {
                        checkPW(currUser, input, br);
                        break;
                    }
                    else {// input.equals("exit")
                        System.out.println("Please enter your Username: ");
                    }
                }
                else if (!uc.inUserBase(input)){
                    System.out.println("User not found, please try again.");
                }
                input = br.readLine();
            }
        }
        catch (IOException e){
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
    }

    // Checks the user's password, and if it is correct, directs the user to the next menu (TraderOptions).
    private void checkPW(phase1.User currUser, String input, BufferedReader br){
        try{
            while ((!input.equals(currUser.getPassword())) && (!input.equals("exit"))){
                System.out.println("Incorrect password, please try again or type 'exit' to return to the main menu.");
                input = br.readLine();
            }
            if (input.equals(currUser.getPassword())){ // Password entered correctly
                if (currUser.getType().equals("trader")) {
                    phase1.TraderOptions opt = new phase1.TraderOptions((phase1.Trader)currUser, uc, us);
                    opt.run();
                }
                else { //currUser.getType().equals("admin")
                    phase1.AdminOptions opa = new phase1.AdminOptions(currUser, uc, us);
                    opa.run();
                }
            }
            // else input.equals("exit"), so do nothing which ends method and returns to main menu
        }
        catch (IOException e){
            System.out.println("Something went wrong");
        }
    }

    // Prompts the user for a username, and checks whether that user already exists.
    // If the user does not exist, then creates a user via NewUser
    private void register(BufferedReader br){
        try{
            System.out.println("What would you like your username to be?");
            String input = br.readLine();
            while (!input.equals("exit")) {
                if (!uc.inUserBase(input)) {
                    newUser(br, input);
                    break;
                }
                else if (uc.inUserBase(input)){
                    System.out.println("A user with that username is already registered.\nPlease enter a different" +
                            "username");
                    input = br.readLine();
                }
            }
        }
        catch (IOException e){
            System.out.println("Something went wrong");
        }
    }

    // Called once verified that user with username doesn't already exist.
    // Creates a new user with the specified username, and the first name & password that the user inputs.
    public void newUser(BufferedReader br, String username){
        //Need to get name and password for user
        try{
            System.out.println("Please enter your first name: ");
            String input = br.readLine();
            if (!input.equals("exit")){
                String name = input;
                System.out.println("Please enter a password: ");
                input = br.readLine();
                if(!input.equals("exit")){
                    String password = input;
                    String type = "trader"; // Can't register an admin this way, so must be a trader
                    List<phase1.TradeMessage> trades = new ArrayList<phase1.TradeMessage>();
                    List<phase1.Message> traderNotifs = new ArrayList<phase1.Message>();
                    List<phase1.Message>adminNotifs = new ArrayList<phase1.Message>();
                    phase1.TraderInbox inbox = new phase1.TraderInbox(trades, traderNotifs, adminNotifs);
                    phase1.Inventory inventory = new phase1.Inventory(new ArrayList<phase1.Item>(), "inventory");
                    phase1.TraderStatus traderStatus = new phase1.TraderStatus();
                    phase1.User user = new phase1.Trader(username, password, type, inbox, inventory, inventory, name, traderStatus);
                    inbox.setOwner(user);
                    uc.userBase.add(user);
                    us.toSerialize(uc.userBase);
                    System.out.println("User created!");
                    us.toSerialize(uc.userBase);
                }
            }
        }
        catch (Exception e){
            System.out.println("Something went wrong");
        }
    }
}