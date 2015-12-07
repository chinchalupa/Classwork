package problem.question2;

/**
 * Created by wrightjt on 12/1/2015.
 */
public class Sale {

    private int cashTendered;
    private Payment payment;

    public Sale() {
    }

    public void makePayment(int cashTendered) {
        payment = new Payment(cashTendered);
        payment.authorize();
    }
}
