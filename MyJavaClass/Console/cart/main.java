package cart;
import java.util.*;

public class Main {
/** Create a very simple online store with a shopping cart and items for sale.
 *  @param args  Command-line arguments are not used.
 */
    public static void main(String[] args) { 
        Date now = new Date();
        Northwoods nw = new Northwoods( now );
        nw.sellItems();
    }
}

/*----------------------------------------------------------------------
Northwoods Sweets
Welcome to our shop.

Shopping cart created on Sun Jan 15 16:37:02 EST 2012
 0 Proceed to checkout             0.00
 1 Maple syrup, half gallon       26.00
 2 Maple syrup, quart             15.00
 3 Maple syrup, pint               8.00
 4 Maple sugar, 1 pound           24.00
 5 Maple-leaf candies, 8 oz        8.00
 6 Maple cream spread, 8 oz        8.00

Please enter an item number and a quantity, or 0 to check out: 1 1 
1  Maple syrup, half gallon    = $ 26.00

 0 Proceed to checkout             0.00
 1 Maple syrup, half gallon       26.00
 2 Maple syrup, quart             15.00
 3 Maple syrup, pint               8.00
 4 Maple sugar, 1 pound           24.00
 5 Maple-leaf candies, 8 oz        8.00
 6 Maple cream spread, 8 oz        8.00

Please enter an item number and a quantity, or 0 to check out: 2 3
3  Maple syrup, quart          = $ 45.00

 0 Proceed to checkout             0.00
 1 Maple syrup, half gallon       26.00
 2 Maple syrup, quart             15.00
 3 Maple syrup, pint               8.00
 4 Maple sugar, 1 pound           24.00
 5 Maple-leaf candies, 8 oz        8.00
 6 Maple cream spread, 8 oz        8.00

Please enter an item number and a quantity, or 0 to check out: 0

Your cart contains:
  1    Maple syrup, half gallon    @ 26.00 = $ 26.00
  3    Maple syrup, quart          @ 15.00 = $ 45.00

Total price = $ 71.00
------------------------------------------------------------------*/
