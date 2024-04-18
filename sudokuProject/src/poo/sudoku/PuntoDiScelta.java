package poo.sudoku;

public class PuntoDiScelta {
	
	private int i, j;
	
	public PuntoDiScelta(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public int getRiga() {
		return i;
	}
	
	public int getColonna() {
		return j;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PuntoDiScelta))
			return false;
		PuntoDiScelta other = (PuntoDiScelta) obj;
		return this.i == other.i && this.j == other.j;
	}

	@Override
	public String toString() {
		return "PuntoDiScelta [i=" + i + ", j=" + j + "]";
	}

	
	
}
