/*
 * Item.java: the item name, price per, and quantity of one purchase.
 * Part of the model for the online shop.
 */
package cart;
//----------------------------------------------------------------------
public class Item {
    private String description;    // Name of the product.         
    private double  price;         // Price for one item.
    private int  quantity;         // Number of items being purchased.

    ///------------------------------------------------------------------
    /** The Item constructor requires one parameter for each data member.  It simply 
     *  copies those parameters into the new object.
     *  @param d The name or description of the item being purchased.
     *  @param p The price of 1 copy of this item
     *  @param q The number of copies that the user is buying.
     */
    public Item( String d, double p, int q ) {
        description = d;
        price = p;
        quantity = q;
    }
    
    ///------------------------------------------------------------------
    /** The saleAmount could be defined as a data member.   It is defined here by a function
     *  to emphasize that there are choices to be made.  This is a space/time tradeoff.
     */
    public double saleAmount() { return price * quantity; }
    
    ///------------------------------------------------------------------
    /** Format the data members of an Item for printing.  Do this in such a way that the data will form
     *  a neat table when printed.
     */
   public String toString(){
        double total = price*quantity;
        String s = String.format("  %-2d   %-27s @ %5.2f = $%6.2f\n", quantity, description, price, total);
        return s;
    }
};

