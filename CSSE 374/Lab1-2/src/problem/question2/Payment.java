package problem.question2;

/**
 * Created by wrightjt on 12/1/2015.
 */
public class Payment {

    private int cashTendered;

    public Payment(int cashTendered) {
        this.cashTendered = cashTendered;
    }

    public void authorize() {
        System.out.println("Authorized");
    }
}
