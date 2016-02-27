package cart;
import java.util.*;
//----------------------------------------------------------------------
public class Cart { 
    private int count = 0;                  // Number of products in the cart.
    private float totalPrice = 0;           // Total price of all products in the cart.
    private Item[] load = new Item[10];     // Pointers to the actual products.
    private final Date creation;
    
    public Cart( Date now ){ 
        creation = now;
        System.out.println( "Shopping cart created on "+ creation);
    }
    /// -------------------------------------------------------------------------------
    /** Place an item in the shopping cart after the previously selected items.   Update  
     *  the total price of all items in the cart.
     * @param purchase
     */
    public void add( Item purchase ){     
        load[count++] = purchase;
        totalPrice += purchase.saleAmount();
    }
    
    ///------------------------------------------------------------------
    /** Format the contents of the shopping cart for printing.   
     */
    public String toString(){
        StringBuilder sb = new StringBuilder("\nYour cart contains:\n");
        for( int k = 0; k<count; ++k){
            sb.append( load[k] );
        }
        sb.append( String.format( "\nTotal price = $%6.2f\n", totalPrice ));
        return sb.toString();
    }
}
