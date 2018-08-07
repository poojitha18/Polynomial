import java.util.*;
import java.io.*;

public class Polynomial {
	
	List<PolyTerm> terms;
	
	Polynomial (String input){
		this.terms = new ArrayList<PolyTerm>();
		input.replace("-","+-");
		input.replace(" ", "");
		String[] subPolynomials = input.split("+",0);
		for(int i=0;i<subPolynomials.length;i++){
			String[] splitSubPolynomial = subPolynomials[i].split("[a-z]||^");
			terms.add(new Polyterm(splitSubPolynomial[0],splitSubPolynomial[splitSubPolynomial.length-1])));
		}
	}
	
	public static ArrayList<PolyTerm> addition(ArrayList<PolyTerm> poly1,ArrayList<PolyTerm> poly2){
		ArrayList<PolyTerm> result = new ArrayList<PolyTerm>();
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
