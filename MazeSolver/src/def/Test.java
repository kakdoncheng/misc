package def;

//import java.awt.GraphicsEnvironment;

public class Test {
	
	public static int variable = 0;
	
	//using ints as a reassignment;
	public static int changeVariable(int x){
		//do something;
		x++;
		System.out.println("the variable: "+x);
		return x;
	}
	
	//using ints as an error code;
	//take a String password as input;
	//returns 0 if the password is correct;
	//returns 1 otherwise;
	public static int checkPassword(String input){
		int status=0;
		if(!input.equals("Hello.")){
			status=1;
		}
		return status;
	}
	
	//What do you want to do?
	//What tools do we have available?
	//How do we want to do it using the tools available?
	//Strings
	//System.out.print();
	//delay? were going to have to write our own delay function. look it up? write our own?
	//for loop;
	public static void delay(long millis){
		long last = System.currentTimeMillis();
		while(System.currentTimeMillis()-last<millis){
			continue;
		}
	}
	public static void printOneCharacterAtATime(String input){
		for(int i=0;i<input.length();i++){
			System.out.print(input.charAt(i));
			delay(100);
		}
	}
	public static void printWithDelay(String[] in){
		for(int i=0;i<in.length;i++){
			printOneCharacterAtATime(in[i]);
			System.out.println();
		}
	}
	
	public static double score(int pts, double base){
		if(pts<3)
			return base;
		return pts*(((pts-1)/2)*base);
	}
	
	public static int hkosscore(int pts){
		if(pts<5)
			return (int)Math.pow(2,pts+1);
		if(pts%2!=0)
			return hkosscore(pts-1)+hkosscore(pts-1)/2;
		else
			return hkosscore(pts-2)*2;
	}
	
	public static void main(String[] args){
		//printOneCharacterAtATime("Hello, world!\nMay They FLesh be Consumed!\n");
		/*
		printWithDelay(new String[]{
				"Hello,world",
				"Hello, world! May They FLesh be Consumed!",
				"auiwegliawu44hg9oq248ypt98wer;oguhqa42;o8gt0;"
		});
		*/

		
		for(int i=0;i<=20
				;i++){
			//System.out.printf("%d\t%.2f\t%.2f\n",i,score(i,.25),score(i,.25)/2);
			System.out.printf("%d\t%d\t%.2f\n",i,hkosscore(i),hkosscore(i)*.05);
		}
		
		/*
		System.out.println("Before: "+variable);
		variable = changeVariable(variable);
		System.out.println("After: "+variable);
		
		/*
		Scanner scan;
		while(true){
			scan = new Scanner(System.in);
			System.out.print("Enter Password: ");
			String in = scan.nextLine();
			int status = checkPassword(in);
			System.out.println("Exit code: "+status);
			if(status==0){
				break;
			}else{
				System.out.println("Your password is bogus;");
				continue;
			}
		}
		System.out.println("OWIHFOEUOGEUHWGIE");
		scan.close();
		/*
		int id=0,upper=100,lower=50;
		while(id<5){
			System.out.println((int)(Math.random()*(upper-lower+1))+(lower));
		}
		
		//yrdtfujhkghjlij
		System.out.println("foo".compareTo("bar"));
		String fonts[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for(int i=0;i<fonts.length;i++)System.out.println(fonts[i]);
		for(int b=0;b<5;b++){
			System.out.println(b);
		}
		*/
		int i=0;
		long last=System.nanoTime();
		long now=System.nanoTime();
		while(true){
			i++;
			now=System.nanoTime();
			if(now-last>=(1000000000)){
				last=now;
				System.out.println(i+" updates/60tick");
				i=0;
			}
		}
		//*/
		
	}
}
