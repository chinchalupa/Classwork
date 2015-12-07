package problem.question1;

import java.util.Collection;
import java.util.Collections;

public class Student extends AbstractPersonnel implements IStudent {
	private int year;
	private Collection<ICourse> completedCourses;
	private Collection<ICourse> enrolledCourses;

	public Student(int campusid, String name, int year, Collection<ICourse> completedCourses, Collection<ICourse> enrolledCourses) {
		super(campusid, name);
		this.year = year;
		this.completedCourses = Collections.unmodifiableCollection(completedCourses);
		this.enrolledCourses = Collections.unmodifiableCollection(enrolledCourses);
	}

	public int getYear() {
		return this.year;
	}

	@Override
	public Collection<ICourse> getCompletedCourses() {
		return this.completedCourses;
	}

	@Override
	public Collection<ICourse> getEnrolledCourses() {
		return this.enrolledCourses;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("========= Student =========\n");
		builder.append(super.toString());
		builder.append("Year: ");
		builder.append(year);
		builder.append("\n");
		builder.append("Courses Complete:\n");
		builder.append(completedCourses.toString());
		builder.append("\n");
		builder.append("Courses Currently Enrolled:\n");
		builder.append(enrolledCourses.toString());
		builder.append("\n");
		builder.append("===========================\n");
		return builder.toString();	
	}	
}
