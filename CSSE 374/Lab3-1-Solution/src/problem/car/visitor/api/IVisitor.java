package problem.car.visitor.api;

import problem.car.api.IBody;
import problem.car.api.ICar;
import problem.car.api.IEngine;
import problem.car.api.IWheel;

public interface IVisitor {
	public void preVisit(ITraverser t);
	public void visit(ITraverser t);
	public void postVisit(ITraverser t);


	public void addVisit(VisitType vType, Class<?> clazz, IVisitMethod m);
	public void removeVisit(VisitType vType, Class<?> clazz);
}
