package def;

public class PI {
	public PI(){}
	private long lL=0,uL=1,c=1,r=2;
	private double x=0,l=uL-lL,w=l/r,a=0;
	private boolean error=false, ff=false;
	
	private double f(double n) {
		try {
			if(n>0.9999999999D){
				n=0.9999999999D;
			}
			n=1/(Math.sqrt(1-(n*n)));
			return n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public void define() {
		while(!error) {
			try {
				if (ff) {
					error=true;
				}
				x=uL;
				w=l/r;
				for(long i=0;i<r;i++){
					a+=f(x)*w*2;
					x-=w;
				}
				System.out.println("R"+r+" C"+c+" "+a);
				if (r!=4611686018427387904L){
					r*=2;
				} else {
					r=9223372036854775807L;
					System.out.println("Error: Integer Overflow; Final Loop;");
					ff=true;
				}
				a=0;
				c++;
			} catch (Exception e) {
				
			}
		}
	}
	public static void main(String[] BEN) {
		PI pi=new PI();
		pi.define();
			
	}
}
