package ca.ucalgary;

import ca.ucalgary.gui.BankApplication;
import ca.ucalgary.tui.CLI;

/**
 * Main Class
 * contains main method to launch application
 */
public class Main {

    /**
     * main method to launch application
     * if user's argument is "tui", TUI is launched
     * if user's argument is "gui", GUI is launched
     * @param args
     */
    public static void main(String args[]){
        if (args.length == 0){
            CLI.main(args);
        } else if (args[0].equals("tui")){
            CLI.main(args);
        } else if (args[0].equals("gui")){
            BankApplication.main(args);
        } else {
            System.out.println("Invalid parameters");
        }
    }
}
