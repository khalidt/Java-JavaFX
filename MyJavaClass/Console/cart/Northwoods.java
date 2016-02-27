import java.util.*;

// Controller for the online shop.
public class Northwoods {
    //--------------------------------------------------------------------------------
    private static final String[] menu = { 
        "Proceed to checkout",
        "Maple syrup, half gallon",
        "Maple syrup, quart",
        "Maple syrup, pint",
        "Maple sugar, 1 pound",
        "Maple-leaf candies, 8 oz",
        "Maple cream spread, 8 oz"
    };
    private static final double[] price = { 0, 26.00, 15.00, 8.00, 24.00, 8.00, 8.00 };
 
    private Scanner sc = new Scanner(System.in);
    
    private Cart c;
    /// -------------------------------------------------------------------------------
    /** Construct the application controller.  Allocate a cart for purchases.  Contents of 
     *  cart and total purchase price will be displayed upon exit.
     *  @param now The date and time on which this store and shopping cart were created.
     */
    public Northwoods( Date now ){ 
        System.out.println("Northwoods Sweets\nWelcome to our shop.\n");
        c = new Cart(now);
    }

    /// -------------------------------------------------------------------------------
    /** Display a menu of all items for sale, with prices.
     */
    public void showMenu(){
        for(int k=0; k<menu.length; ++k){
            System.out.printf( "%2d %-30s %5.2f\n", k, menu[k], price[k] );
        }
        System.out.print( "\nPlease enter an item number and a quantity, or 0 to check out: " );
    }
    
    /// -------------------------------------------------------------------------------
    /** Allow the user to select items from the menu and put them in the shopping cart.  Menu 
     *  selections are validated.  Selecting 0 ends the transaction and prints out the cart.
     */
    public void sellItems(){
        for(;;){
            showMenu();
            int choice = sc.nextInt();
            if (choice==0) break;
            if (choice<1 || choice>=menu.length) {
                System.out.println("Please select a number on the menu!\n");
                continue;
            }
            int quantity = sc.nextInt();
            Item temp = new Item( menu[choice], price[choice], quantity );
            System.out.printf( "%-2d %-27s = $%6.2f\n\n", quantity, menu[choice], temp.saleAmount() );
            c.add(temp);
        }
        System.out.println( c );
    }
}
