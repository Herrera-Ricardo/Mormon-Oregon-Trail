/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mormonoregontrail.view;

import mormonoregontrail.MormonOregonTrail;
import mormonoregontrail.control.MapControl;
import mormonoregontrail.model.Game;
import mormonoregontrail.model.InventoryItem;
import mormonoregontrail.model.Location;
import mormonoregontrail.model.Map;
import mormonoregontrail.model.Scene;

/**
 *
 * @author Team - stub function
 * @author Laura - full menu with completed and stub functions 6/17/2017
 */
public class GameMenuView extends View{
    
    /**
     * MainMenuView function
     */
    public GameMenuView() {            
        super("\n"
            + "\n------------------------------------------------"
            + "\n| Game Menu                                    |"
            + "\n------------------------------------------------"
            + "\nM - Show Map"
            + "\nI - Show Inventory"
            + "\nA - Show List of Actors"
            + "\nP - Purchase Items from Store"
            + "\nH - Hunt (Scavenge) for Supplies"
            + "\nO - Overcome an Obstacle"
            + "\nW - Wagon/Handcart Status"
            + "\nV - Verify Wagon or Cart can Function"
            + "\nF - Perform Maintenance (Fix)"
            + "\nG - Seek Spiritual Guidance"
            + "\nT - Advance Along the Trail"
            + "\nN - Estimate Resources Needed"
            + "\nQ - Quit"
            + "\n------------------------------------------------"
            + "\nPlease choose an option: ");
    }
    
    @Override
    public boolean doAction(String value) {
        value = value.toUpperCase(); // convert choice to upper case
        
        switch (value) {
            case "M": // show the map
                this.showMap();
                break;
            case "I": // show inventory
                this.viewInventory();
                break;
            case "A": // show list of actors
                this.viewActors();
                break;
            case "P": // purchase items from the store
                this.displayPurchaseFromStoreMenu();
                break;
            case "H": // hunt (scavenge) for supplies
                this.huntForSupplies();
                break;
            case "O": // overcome an obstacle
                this.displayOvercomeObstacleMenu();
                break;
            case "W": // check the status of the wagon
                this.wagonStatus();
                break;
            case "V": // verify the wagon is functioning
                this.verifyWagonCanFunction();
                break;
            case "F": // perform wagon maintenance (fix)
                this.performMaintenance();
                break;
            case "G": // seek spiritual guidance
                this.seekSpiritualGuidance();
                break;
            case "T": // advance along the trail
                return this.advanceAlongTheTrail();
            case "N": // estimate resources needed
                this.displayResourcesNeeded();
                break;
            default:
                System.out.println("\n*** Invalid selection *** Try again");
                break;
        }
        
        return false;
    }

    private void showMap() {
        String leftIndicator;
        String rightIndicator;

        Game game = MormonOregonTrail.getCurrentGame(); // retreive the game
        Map map = game.getMap(); // retreive the map from game
        Location[][] locations = map.getLocations(); // retreive the locations from map
        
          // Game Title
          System.out.println("               MORMON OREGON TRAIL");
          // Build the heading of the map
          System.out.print("  |");
          for( int column = 0; column < locations[0].length; column++){
            // print col numbers to side of map
            if (column < 10)
                System.out.print("  " + column + " |");
            else
                System.out.print(" " + column + " |");
          }
          // Now build the map.  For each row, show the column information
          System.out.println();
          for( int row = 0; row < locations.length; row++){
           System.out.print(row + " "); // print row numbers to side of map
            for( int column = 0; column < locations[row].length; column++){
              // set default indicators as blanks
              leftIndicator = " ";
              rightIndicator = " ";
              if(locations[row][column] == map.getCurrentLocation()){
                // Set star indicators to show this is the current location.
                leftIndicator = "*"; 
                rightIndicator = "*"; 
              } 
              else if(locations[row][column].isVisited()){
                 // Set < > indicators to show this location has been visited.
                 leftIndicator = ">"; // can be stars or whatever these are indicators showing visited
                 rightIndicator = "<"; // same as above
              }
             System.out.print("|"); // start map with a |
              if(locations[row][column].getScene() == null)
              {
                   // No scene assigned here so use ?? for the symbol
                   System.out.print(leftIndicator + "??" + rightIndicator);
              }
              else
                System.out.print(leftIndicator
                   + locations[row][column].getScene().getMapSymbol()
                   + rightIndicator);
            }
           System.out.println("|");
          } 
          if (map.getCurrentLocation().getScene() != null)
              System.out.println("Your current location is: "
                + map.getCurrentLocation().getScene().getName()
                + "\n" + map.getCurrentLocation().getScene().getDescription());
    }

    private void viewInventory() {
        StringBuilder line;
        
        Game game = MormonOregonTrail.getCurrentGame();
        InventoryItem[] inventory = game.getInventory();
        
        System.out.println("\n          LIST OF INVENTORY ITEMS");
        line = new StringBuilder("                                                                                ");
        line.insert(0, "DESCRIPTION");
        line.insert(20, "REQUIRED");
        line.insert(30, "IN STOCK");
        line.insert(40, "UNITS");
        line.insert(50, "COST");
        System.out.println(line.toString());
        
        // for each inventory item
        for (InventoryItem item : inventory) {
            line = new StringBuilder("                                                                                ");
            line.insert(0, item.getDescription());
            line.insert(20, String.valueOf(item.getRequiredAmount()));
            line.insert(30, String.valueOf(item.getQuantityInStock()));
            line.insert(40, item.getUnits());
            line.insert(50, String.valueOf(item.getCost()));
            
            // Display the line
            System.out.println(line.toString());
        }
    }

    private void viewActors() {
    }
    
    private void displayInventoryMenu() {
        InventoryMenuView inventoryMenuView = new InventoryMenuView();
        inventoryMenuView.display();
    }

    private void displayPurchaseFromStoreMenu() {
        StorePurchaseView storePurchaseView = new StorePurchaseView();
        storePurchaseView.display();
    }

    private void huntForSupplies() {
        System.out.println("\n*** huntForSupplies() function called ***");
    }

    private void displayOvercomeObstacleMenu() {
        HandleAnObstacleMenuView handleObstacleView = new HandleAnObstacleMenuView();
        handleObstacleView.display();
    }

    private void wagonStatus() {
        WagonOrHandcartStatusView wagonOrHandcartStatusView = new WagonOrHandcartStatusView();
        wagonOrHandcartStatusView.display();
    }

    private void verifyWagonCanFunction() {
        System.out.println("\n*** verifyWagonCanFunction() function called ***");
    }

    private void performMaintenance() {
        PerformMaintenanceView performMaintenanceView = new PerformMaintenanceView();
        performMaintenanceView.display();
    }

    private void seekSpiritualGuidance() {
        System.out.println("\n*** seekSpiritualGuidance() function called ***");
    }

    private boolean advanceAlongTheTrail() {
        Game game = MormonOregonTrail.getCurrentGame(); // retreive the game
        Map map = game.getMap(); // retreive the map from game

        int currentRow = map.getCurrentRow();
        int currentColumn = map.getCurrentColumn();
        
        if (currentColumn < 12)
            MapControl.movePlayer(map, currentRow, currentColumn + 1);
        else
            MapControl.movePlayer(map, currentRow + 1, 0);
        
        if (map.getCurrentRow() == 1 && map.getCurrentColumn() == 12) {
            System.out.println("You've arrived at the Salt Lake Valley!! "
                              + "\n The game is over! Thanks for playing . . .");
            return true;
        }
        // this is where we should go to a scene associated with the new location
        showMap();
        return false;
    }
    private void displayUserDirectionMenu() {
        UserDirectionMenuView userDirectionView = new UserDirectionMenuView();
        int location = 1;
        int daysTraveled = 1;
        userDirectionView.setLocation(location);
        userDirectionView.setDaysTraveled(daysTraveled);
        userDirectionView.display();
        location = userDirectionView.getLocation();
        daysTraveled = userDirectionView.getDaysTraveled();
    }

    private void displayResourcesNeeded() {
        ResourcesNeededView resourcesNeededView = new ResourcesNeededView();
        resourcesNeededView.displayResourcesNeededView();
    }

    
}
