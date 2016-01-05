package problem.car.visitor.api;

/**
 * Created by wrightjt on 1/4/2016.
 */


@FunctionalInterface
public interface IVisitMethod {
    public void execute(ITraverser t);
}
