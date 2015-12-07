package problem.question1;

import java.util.Collection;

public interface IDepartment {
	public String getName();
	public Collection<IFaculty> getFaculty();
	public Collection<IStaff> getIStaff();
	public Collection<IStudent> getStudent();
}
