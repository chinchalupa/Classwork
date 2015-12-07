package problem.question1;

public abstract class AbstractPersonnel implements IPersonnel {
	private int id;
	private String name;

	public AbstractPersonnel(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractPersonnel other = (AbstractPersonnel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id: ");
		builder.append(id);
		builder.append("\n");
		builder.append("name: ");
		builder.append(name);
		builder.append("\n");
		return builder.toString();
	}
}
