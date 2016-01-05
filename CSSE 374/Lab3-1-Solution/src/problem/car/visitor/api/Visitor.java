package problem.car.visitor.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wrightjt on 1/4/2016.
 */
public class Visitor implements IVisitor {

    Map<LookupKey, IVisitMethod> keyToCommandMap;

    public Visitor() {
        this.keyToCommandMap = new HashMap<>();
    }

    @Override
    public void preVisit(ITraverser t) {
        this.doVisit(VisitType.PreVisit, t);
    }

    @Override
    public void visit(ITraverser t) {
        this.doVisit(VisitType.Visit, t);
    }

    @Override
    public void postVisit(ITraverser t) {
        this.doVisit(VisitType.PostVisit, t);
    }

    private void doVisit(VisitType vType, ITraverser t) {
        LookupKey key = new LookupKey(vType, t.getClass());
        IVisitMethod m = this.keyToCommandMap.get(key);
        if(m != null)
            m.execute(t);
    }

    @Override
    public void addVisit(VisitType vType, Class<?> clazz, IVisitMethod m) {
        LookupKey key = new LookupKey(vType, clazz);
        this.keyToCommandMap.put(key, m);
    }

    @Override
    public void removeVisit(VisitType vType, Class<?> clazz) {
        LookupKey key = new LookupKey(vType, clazz);
        this.keyToCommandMap.remove(key);
    }
}
