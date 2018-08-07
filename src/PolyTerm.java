public class PolyTerm implements Comparable<PolyTerm>{
	int coefficient;
	int power;

	PolyTerm(int coeff, int pow){
		this.coefficient = coeff;
		this.power = pow;
	}

	@Override
	public int compareTo(PolyTerm other) {
		// TODO Auto-generated method stub
		return other.power - this.power;
	}
}
