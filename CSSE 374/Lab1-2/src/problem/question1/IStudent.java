package problem.question1;

import java.util.Collection;

public interface IStudent extends IPersonnel {
	public int getYear();
	public Collection<ICourse> getCompletedCourses();
	public Collection<ICourse> getEnrolledCourses();
}
