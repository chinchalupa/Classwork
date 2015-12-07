package problem.question1;

import java.util.Collection;
import java.util.Collections;

public class School implements ISchool {
	private String name;
	private Collection<IDepartment> departments;
	
	public School(String name, Collection<IDepartment> departments) {
		this.name = name;
		this.departments = Collections.unmodifiableCollection(departments);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Collection<IDepartment> getDepartments() {
		return this.departments;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("############### School ###############\n");
		builder.append("Name: ");
		builder.append(name);
		builder.append("\n");
		for(IDepartment d : departments) {
			builder.append(d);
			builder.append("\n");
		}
		builder.append("######################################\n");
		return builder.toString();
	}
}
