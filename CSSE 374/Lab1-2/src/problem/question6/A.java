package problem.question6;

/**
 * Created by wrightjt on 12/6/2015.
 */
public class A {

    private B b;
    private int id;

    public A(B b) {
        this.b = b;
        this.id = 1;
    }

    public void doX() {
        b.doA();
        b.authenticateId(this.id);
    }
}
