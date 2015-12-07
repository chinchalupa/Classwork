package problem.question2;

/**
 * Created by wrightjt on 12/1/2015.
 */
public class Register {

    private Sale sale;

    public Register() {
        this.sale = new Sale();
    }

    public void startTransaction(int cashTendered) {
        sale.makePayment(cashTendered);
    }
}
