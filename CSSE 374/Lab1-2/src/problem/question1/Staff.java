package problem.question1;

public class Staff extends AbstractEmployee implements IStaff {
	private String job;

	public Staff(int ssn, String name, IEmployee supervisor, String job) {
		super(ssn, name, supervisor);
		this.job = job;
	}

	@Override
	public String getJobDescription() {
		return job;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("========= Staff =========\n");
		builder.append(super.toString());
		builder.append("Job Descrption: ");
		builder.append(job);
		builder.append("\n");
		builder.append("===========================\n");
		return builder.toString();	
	}
}
