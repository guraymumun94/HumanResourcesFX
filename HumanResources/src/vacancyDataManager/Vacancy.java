package vacancyDataManager;

public class Vacancy implements Comparable<Vacancy>{
	
	private String type;
	private int count;
	private String description;
	private String specialization;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public Vacancy(String specialization, String type, int count, String description) {
		if(description == null || description.equals(""))
			this.description = "Няма описание";
		else
			this.description = description;
		
		this.type = type;
		this.count = count;
		this.specialization = specialization;
	}

	@Override
	public String toString() {
		return "Специализация: "
				+ specialization + ", Вид: " + type + ", Брой места: " + count;
	}
	
	public String toStringWithDescription() {
		return "Специализация: "
				+ specialization + ", Вид: " + type + ", Брой места: " + count + ", Описание: " + description;
	}

	@Override
	public int compareTo(Vacancy other) {
		if(this.type.compareTo(other.type) == 0 && this.count == other.count &&
				this.description.compareTo(other.description) == 0 && this.specialization.compareTo(other.specialization) == 0) {
			return 0;
		}
		
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((specialization == null) ? 0 : specialization.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Vacancy other = (Vacancy) obj;
		if (count != other.count)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (specialization == null) {
			if (other.specialization != null)
				return false;
		} else if (!specialization.equals(other.specialization))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
