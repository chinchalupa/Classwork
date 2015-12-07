package problem.question6;

/**
 * Created by wrightjt on 12/6/2015.
 */
public class B {
    private C c;

    public B(C c) {
        this.c = c;
    }

    public void doA() {
        c.doB();
        c.doX();
        c.doY();
        c.doZ();
    }

    public void authenticateId(int id) {
        c.doM1();
        c.doM2();
    }


}
