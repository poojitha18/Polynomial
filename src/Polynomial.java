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
			terms.add(new PolyTerm(splitSubPolynomial[0],splitSubPolynomial[splitSubPolynomial.length-1])));
		}
	}
	
	public static ArrayList<PolyTerm> add(ArrayList<PolyTerm> poly1,ArrayList<PolyTerm> poly2){

		ArrayList<PolyTerm> result = new ArrayList<PolyTerm>();
		for(int i=0;i<poly1.size();i++){
			for(int j=0;j<poly2.size();j++){
				if(poly1.get(i).power == poly2.get(j).power){
					PolyTerm temp = new PolyTerm(poly1.get(i).coefficient+poly2.get(j).coefficient,poly1.get(i).power);
					result.add(temp);
					break;
				}
			}
		}
		return result;
	}
	
	public static ArrayList<PolyTerm> multiplication(ArrayList<PolyTerm> poly1,ArrayList<PolyTerm> poly2){
		ArrayList<PolyTerm> result = new ArrayList<PolyTerm>();
		for(int i=0;i<poly1.size();i++){
			for(int j=0;j<poly2.size();j++){
				PolyTerm temp = new PolyTerm(poly1.get(i).coefficient*poly2.get(j).coefficient,poly1.get(i).power+poly2.get(j).power);
				result.add(temp);
			}
		}
		return result;
	}
	
	public static ArrayList<PolyTerm> subtract(ArrayList<PolyTerm> poly1,ArrayList<PolyTerm> poly2){
		ArrayList<PolyTerm> negPoly = new ArrayList<PolyTerm>();
		PolyTerm negNum = new PolyTerm(-1,0);
		negPoly.add(negNum);
		return add(poly1,multiplication(poly2,negPoly));
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
