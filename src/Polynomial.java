import java.util.*;
import java.io.*;

public class Polynomial {
	
	List<PolyTerm> terms;
	
	public static int string2Number(String num){
		if(num.length()==0) return 0;
		int out;
		if(num.startsWith("-")){
			out=-1*Integer.valueOf(num.substring(1, num.length()));
		}else out=Integer.valueOf(num);
		return out;
	}
	
	public static PolyTerm string2Term(String subString){
		int length=subString.length();
		String temp;
		String temp2;
		try{
		temp=subString.substring(0,subString.indexOf('x'));
		temp2=subString.substring(subString.indexOf('^')+1,length);
		}
		catch(StringIndexOutOfBoundsException e){
			temp=subString;
			temp2="";
		}
		int prefix=string2Number(temp);
		int pow=string2Number(temp2);
		return new PolyTerm(prefix,pow);
	}
	
	Polynomial (String input){
		this.terms = new ArrayList<PolyTerm>();
		input=input.replace("-","+-");
		input=input.replace(" ", "");
		String[] subPolynomials = input.split("\\+");
		for(int i=0;i<subPolynomials.length;i++){
			terms.add(string2Term(subPolynomials[i]));
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
	
	

	@Override 
	public String toString(){
		Collections.sort(terms);
		String output = "";
		for(int i=0; i<terms.size(); i++){
			if (terms.get(i).coefficient >0){
				output += " + " + terms.get(i) + "x^" + terms.get(i).power;
			} else if (terms.get(i).coefficient < 0) {
				output += " - " + terms.get(i) + "x^" + terms.get(i).power;
			}
		}
		return output;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
