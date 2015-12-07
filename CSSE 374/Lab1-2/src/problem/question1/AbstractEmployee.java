package problem.question1;

public abstract class AbstractEmployee extends AbstractPersonnel implements IEmployee {
	private IEmployee supervisor;

	public AbstractEmployee(int ssn, String name, IEmployee supervisor) {
		super(ssn, name);
		this.supervisor = supervisor;
	}

	@Override
	public IEmployee getSupervisor() {
		return this.supervisor;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("************* Supervisor *************\n");
		builder.append(supervisor);
		builder.append("\n");		
		builder.append("**************************************\n");
		return builder.toString();
	}
}
