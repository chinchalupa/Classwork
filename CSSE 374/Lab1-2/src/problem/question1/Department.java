package problem.question1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Collections;

public class Department implements IDepartment {
	private String name;
	private Collection<IPersonnel> personnels;
	


	public Department(String name, Collection<IPersonnel> personnels) {
		this.name = name;
		this.personnels = Collections.unmodifiableCollection(personnels);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Collection<IFaculty> getFaculty() {
		return filter(IFaculty.class);
	}

	@Override
	public Collection<IStaff> getIStaff() {
		return filter(IStaff.class);
	}

	@Override
	public Collection<IStudent> getStudent() {
		return filter(IStudent.class);
	}

	@SuppressWarnings("unchecked")
	private <T extends IPersonnel> List<T> filter(Class<T> clazz) {
		List<T> list = new ArrayList<>();
		for(IPersonnel p: this.personnels) {
			if(p.getClass().isInstance(clazz))
				list.add((T) p);
		}
		return list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("++++++++++++ Department ++++++++++++\n");
		builder.append("Name: ");
		builder.append(name);
		builder.append("\n");
		for(IPersonnel p : personnels) {
			builder.append(p);
			builder.append("\n");
		}
		builder.append("++++++++++++++++++++++++++++++++++++\n");
		return builder.toString();
	}
}
