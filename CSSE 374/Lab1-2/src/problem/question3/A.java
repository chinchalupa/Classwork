package problem.question3;

/**
 * Created by wrightjt on 12/1/2015.
 */
public class A {

    private B b;
    private C c;
    private int x = 0;

    public A(B b, C c) {
        this.b = b;
        this.c = c;
    }

    public void doX() {
        if(x < 10) {
            b.calculate();
        } else {
            c.calculate();
        }
    }
}
