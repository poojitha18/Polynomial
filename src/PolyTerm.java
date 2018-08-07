public class PolyTerm implements Comparable<PolyTerm>{
	int coefficient;
	int power;

	PolyTerm(int coeff, int pow){
		this.coefficient = coeff;
		this.power = pow;
	}

	@Override
	public String toString(){
		String out = "";
		if (this.coefficient == 0){
			return "";
		} else if (this.coefficient > 1 && this.coefficient!= 1) {
			out += " +" + this.coefficient;
		} else if (this.coefficient < 1 && this.coefficient!= 1) {
			out += " " +  this.coefficient;
		} else 
			out += " +1";
		
		
		if (this.power == 0){
			return out;
		} else if (this.power == 1){
			out += "x";
			return out;
		} else {
			out += "x^" + this.power;
			return out;
		}
	}
	
	@Override
	public int compareTo(PolyTerm other) {
		// TODO Auto-generated method stub
		return other.power - this.power;
	}
}
