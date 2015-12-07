package problem.question1;

public class Faculty extends AbstractEmployee implements IFaculty {
	private String expertise;

	public Faculty(int ssn, String name, IEmployee supervisor, String expertise) {
		super(ssn, name, supervisor);
		this.expertise = expertise;
	}

	@Override
	public String getExpertise() {
		return expertise;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("========= Faculty =========\n");
		builder.append(super.toString());
		builder.append("Expertise: ");
		builder.append(expertise);
		builder.append("\n");
		builder.append("===========================\n");
		return builder.toString();	
	}
}
