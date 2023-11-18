package ciphers;

import java.util.ArrayList;

public class ArbitraryNumber {
	
	//values will be backwards,
	//ex. 154 is stored as 451
	
	public static String digits="0123456789";
	//public static int base=digits.length();
	private ArrayList<String> value;
	private boolean positive=true;
	
	public ArbitraryNumber(ArrayList<String> number, boolean isPositive) {
		value=new ArrayList<String>();
		for(String n:number){
			value.add(n);
		}
		positive=isPositive;
	}

	public ArbitraryNumber(String number) {
		value=new ArrayList<String>();
		if(number.length()<1){
			value.add(digits.charAt(0)+"");
			return;
		}
		int stop=-1;
		if(number.charAt(0)=='-'){
			positive=false;
			stop=0;
		}
		for(int i=number.length()-1;i>stop;i--){
			value.add(number.charAt(i)+"");
		}
	}
	/*WIP
	public static ArbitraryNumber cBase(ArbitraryNumber n, int base){
		ArrayList<String> num = new ArrayList<String>();
		
		int power = 0;
		while(Math.pow(base, power + 1) <= value){
			power++;
		}
		int digit = 0;
		int carry = 0;
		while(power > 0){
			//calculate current value;
			int place = (int) Math.pow(base, power);
			digit = value / place;
			carry = value % place;
			//update values;
			
			num.add(new Digit(digit, base));
			value = carry;
			power--;
		}
		num.add(new Digit(value, base));
		return null;
	}*/
	
	public static ArbitraryNumber add(ArbitraryNumber first, ArbitraryNumber second){
		//need to add case for numbers with opposite signs
		
		ArrayList<String> a=first.$value(), b=second.$value(), c=new ArrayList<String>();
		//c.add(digits.charAt(0)+"");
		if(a.size()<b.size()){
			a=second.$value();
			b=first.$value();
		}
		
		System.out.println(a+" + "+b);
		
		String carry=digits.charAt(0)+"";
		for(int i=0;i<b.size();i++){
			int n=digits.indexOf(a.get(i));
			int m=digits.indexOf(b.get(i));
			int ans=n+m+digits.indexOf(carry);
			
			System.out.print(i+" : "+n+"+"+m+"+"+digits.indexOf(carry)+"="+ans);
			
			if(ans>digits.length()-1){
				carry=digits.charAt(ans/digits.length())+"";
				ans%=digits.length();
				System.out.print("   /:"+carry+" %:"+ans);
			}else{
				carry=digits.charAt(0)+"";
			}
			System.out.println();
			c.add(digits.charAt(ans)+"");
		}
		
		if(a.size()!=b.size()){
			for(int i=b.size();i<a.size();i++){
				c.add(a.get(i));
			}
		}
		//take care of remainder carry value recursively
		int rem=digits.indexOf(carry);
		if(rem>0){
			if(a.size()!=b.size()){
				ArrayList<String> d=new ArrayList<String>();
				for(String n:b){
					n.length();
					d.add(digits.charAt(0)+"");
				}
				d.add(carry);
				return add(new ArbitraryNumber(c, true), new ArbitraryNumber(d, true));
			}else{
				c.add(carry);
			}
		}
		return new ArbitraryNumber(c, true);
	}
	
	public ArrayList<String> $value(){
		return value;
	}
	public boolean isPositive(){
		return positive;
	}
	
	public String toString(){
		String out="";
		//for(String n:value){
		//	out+=n;
		//}
		for(int i=value.size()-1;i>-1;i--){
			out+=value.get(i);
		}
		return !positive?"-"+out:out;
	}

	public static void main(String[] args) {
		ArbitraryNumber a=new ArbitraryNumber("6420"),
				b=new ArbitraryNumber("40153");
		ArbitraryNumber c=add(a,b);
		System.out.println(a+"+"+b+"="+c);

	}

}
