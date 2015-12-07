package problem.question4;

/**
 * Created by wrightjt on 12/1/2015.
 */
public class Sale {

    private int t;
    private SalesLineItem[] lineItems;

    public Sale(SalesLineItem[] lineItems, int t) {
        this.t = this.getTotal();
        this.lineItems = lineItems;
    }

    public int getTotal() {
        int temp = 0;
        for(int i = 0; i < lineItems.length; i++) {
            temp += lineItems[i].getSubtotal();
        }
        return temp;
    }
}
