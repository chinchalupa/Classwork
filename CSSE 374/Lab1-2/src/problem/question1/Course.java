package problem.question1;

public class Course implements ICourse {
	private String name;
	private String description;

	public Course(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + ", description=" + description + "]";
	}
}
