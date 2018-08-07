import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Polynomial {
	
ArrayList<PolyTerm> terms;
	
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
	
	public Polynomial(ArrayList<PolyTerm> inputTerms) {
		this.terms = inputTerms;
	}
	
	public static Polynomial add(Polynomial polynomia1,Polynomial polynomia2){

		List<PolyTerm> poly1 = new ArrayList<>();
		List<PolyTerm> poly2 = new ArrayList<>();

		poly1.addAll(polynomia1.terms);
		poly2.addAll(polynomia2.terms);
		
		ArrayList<PolyTerm> result = new ArrayList<PolyTerm>();
		for(int i=poly1.size()-1;i>=0;i--){
			for(int j=poly2.size()-1;j>=0;j--){
				if(poly1.get(i).power == poly2.get(j).power){
					PolyTerm temp = new PolyTerm(poly1.get(i).coefficient+poly2.get(j).coefficient,poly1.get(i).power);
					result.add(temp);
					poly1.remove(i);
					poly2.remove(j);
					break;
				}
			}
		}
		result.addAll(poly1);
		result.addAll(poly2);
		return new Polynomial(result);
	}
	
	private static Polynomial simplify (Polynomial poly){
		List<PolyTerm> result = poly.terms;
		ArrayList<PolyTerm> simplifiedResult = new ArrayList<>();
		Collections.sort(result);
		PolyTerm tmp = result.get(0);
		simplifiedResult.add(new PolyTerm(tmp.coefficient, tmp.power));
		for(int i=1; i<result.size(); i++){
			PolyTerm last = simplifiedResult.get(simplifiedResult.size()-1);
			if (last.power == result.get(i).power){
				tmp = new PolyTerm(last.coefficient + result.get(i).coefficient, last.power);
				simplifiedResult.set(simplifiedResult.size()-1, tmp);
			}
			else {
				simplifiedResult.add(result.get(i));
			}
		}
		return new Polynomial(simplifiedResult);
	}
	
	public static Polynomial multiplication(Polynomial polynomia1,Polynomial polynomia2){
		
		List<PolyTerm> poly1 = polynomia1.terms;
		List<PolyTerm> poly2 = polynomia2.terms;
		
		ArrayList<PolyTerm> result = new ArrayList<PolyTerm>();
		for(int i=0;i<poly1.size();i++){
			for(int j=0;j<poly2.size();j++){
				PolyTerm temp = new PolyTerm(poly1.get(i).coefficient*poly2.get(j).coefficient,poly1.get(i).power+poly2.get(j).power);
				result.add(temp);
			}
		}
		
		return simplify(new Polynomial(result));
	}
	
	public static Polynomial subtract(Polynomial poly1, Polynomial poly2){
		ArrayList<PolyTerm> negPoly = new ArrayList<PolyTerm>();
		PolyTerm negNum = new PolyTerm(-1,0);
		negPoly.add(negNum);
		Polynomial negativPoly = new Polynomial(negPoly);
		return add(poly1,multiplication(poly2,negativPoly));
	}
	
	public static ArrayList<Polynomial> divide(Polynomial numerator, Polynomial denominator){
		ArrayList<PolyTerm> dividend = new ArrayList<>();
		ArrayList<PolyTerm> divisor = new ArrayList<>();
		
		dividend.addAll(numerator.terms);
		divisor.addAll(denominator.terms);
		
		ArrayList<Polynomial> answer = new ArrayList<>();
		ArrayList<PolyTerm> coeff = new ArrayList<>();
		
		Collections.sort(divisor);
		
		while(true){
			Collections.sort(dividend);
			
			
			dividend = (ArrayList<PolyTerm>)dividend.stream().filter(val->val.coefficient!=0).collect(Collectors.toList());
			//dividend.forEach(val->System.out.println(val.coefficient + " | " + val.power));
			
			
			//System.out.println(dividend);
			ArrayList<PolyTerm> temp = new ArrayList<>();
			if(dividend.isEmpty()){
				answer.add(new Polynomial(coeff));
				answer.add(new Polynomial(dividend));
				break;
			}
			else if(dividend.get(0).power >= divisor.get(0).power && dividend.get(0).coefficient/divisor.get(0).coefficient!=0){
				PolyTerm tmp = new PolyTerm(dividend.get(0).coefficient/divisor.get(0).coefficient,dividend.get(0).power - divisor.get(0).power);
                
				coeff.add(tmp);
                temp.add(tmp);
                Polynomial poly = new Polynomial(temp);
                dividend = subtract(new Polynomial(dividend),multiplication(new Polynomial(divisor),poly)).terms;
                //System.out.println(dividend);
                //System.out.println(divisor);
			}
			else{

				answer.add(new Polynomial(coeff));
				answer.add(new Polynomial(dividend));
				break;
			}
		}
		return answer;
	}
	

	@Override 
	public String toString(){
		Collections.sort(terms);
		String output = "";
		
		for (int i=0; i<terms.size(); i++){
			output += terms.get(i).toString();
		}
		
		if (output.length() <=0) {
			return "0";
		}
		
		if (output.charAt(0) != '-'){
			return output.substring(1);
		} 
		return output;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Polynomial polynomial1 = new Polynomial("+1x^2 -2x^1 + 3");
		System.out.println("First Polynomial: "+polynomial1);
		
		Polynomial polynomial2 = new Polynomial("+3x^3 +4x^2 -1x^1");
		System.out.println("Second Polynomial: "+polynomial2);
		
		System.out.println("--------------------------------------");
		System.out.println("Addition: "+Polynomial.add(polynomial1, polynomial2));
		System.out.println("Subtraction: "+Polynomial.subtract(polynomial1, polynomial2));
		System.out.println("Multiplication: "+Polynomial.multiplication(polynomial1, polynomial2));
		
		Polynomial polynomial3 = new Polynomial("3x^5 -2x^4 +14x^2 -3x^1");
		System.out.println("Third Polynomial: "+polynomial3);
		
		Polynomial polynomial4 = new Polynomial("+1x^1 - 1");
		//System.out.println("Forth Polynomial: "+polynomial4);
		
		
		ArrayList<Polynomial> divisionAnswer = Polynomial.divide(polynomial3, polynomial2);
		System.out.println("Division of "+polynomial3+" and "+polynomial2);
		System.out.println("Coefficient: " + divisionAnswer.get(0));
		System.out.println("Remainder: " + divisionAnswer.get(1));
		
		
	}

}
