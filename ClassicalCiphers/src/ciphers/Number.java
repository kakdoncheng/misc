package ciphers;

import java.util.ArrayList;

public class Number {
	
	private ArrayList<Digit> digits;
	private int value;
	private int base;
	
	class Digit {
		
		public final static String DIGITS = "0123456789ABCDEF";
		private int value;
		private int base;
		
		public Digit(){
			this(0, 10);
		}

		public Digit(int value, int base) {
			this.value = value;
			this.base = base;
			if(base > 16){
				System.out.println("Error: Invalid base entered. Setting value to default.");
				this.value = 0;
				this.base = 10;
			}
			if(this.value > this.base - 1 ||
					this.value < 0){
				System.out.println("Error: Invalid value entered. Setting value to default.");
				this.value = 0;
				this.base = 10;
			}
		}
		
		public boolean increment(){
			value++;
			if(value > base - 1){
				value = 0;
				return true;
			}
			return false;
		}
		
		public String toString(){
			return DIGITS.charAt(value)+"";
		}

		public int getValue() {
			return value;
		}

		public int getBase() {
			return base;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public void setBase(int base) {
			this.base = base;
		}

	}

	public Number(int value, int base) {
		this.digits = new ArrayList<Digit>();
		this.value = value;
		this.base = base;
		
		if(base>16){
			System.out.print("Error: Base cannot be greater than 16.");
			this.base=16;
			base=16;
		}
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
			
			digits.add(new Digit(digit, base));
			value = carry;
			power--;
		}
		digits.add(new Digit(value, base));
	}
	
	public void increment(){
		int place = digits.size() - 1;
		boolean carry = digits.get(place).increment();
		while(carry){
			place--;
			carry = digits.get(place).increment();
			if(place == 0 && carry){
				digits.add(0, new Digit(1, getBase()));
				carry = false;
			}
		}
	}
	

	public String toString(){
		String out="";
		for(Digit d : digits){
			out+=d;
		}
		return out;
	}
	
	public int getValue() {
		return value;
	}

	public int getBase() {
		return base;
	}

}
