package ciphers;

public class VicCipher {
	
	//http://www.quadibloc.com/crypto/pp1324.htm
	//"ET*AON*RISBCDFGHJKLMPQ/UVWXYZ."
	//'*' is null placeholder

	private String num,alpha,left,top;
	private String id,date,phrase,keygroup;
	private String[] key;
	private char[][] set;
	public String shuffle(String in) {
		char[] c=in.toCharArray();
		for(int i=0;i<c.length;i++){
			int n=(int)(Math.random()*c.length);
			if(n!=i){
				char temp=c[i];
				c[i]=c[n];
				c[n]=temp;
			}
		}
		return new String(c);
	}
	public String newSet(){
		return shuffle("ET*AON*RIS")+shuffle("BCDFGHJKLMPQ/UVWXYZ.");
	}
	public void set(String in){
		set(in, key[16]);
	}
	public void set(String in, String key){
		top=key;
		left="/*";
		set=new char[3][10];
		for(int y=0;y<set.length;y++){
			for(int x=0;x<set[0].length;x++){
				char c=in.charAt((y*set[0].length)+x);
				if(c=='*'){
					left+=key.charAt(x)+"";
				}
				set[y][x]=c;
			}
		}	
	}
	public void printSet(){
		if(set==null){
			System.out.println("No set found.");
			return;
		}
		System.out.print(left.charAt(0));
		for(char c:top.toCharArray()){
			System.out.print(c);
		}
		System.out.println();
		for(int i=1;i<left.length();i++){
			System.out.print(left.charAt(i));
			for(char c:set[i-1]){
				System.out.print(c);
			}
			System.out.println();
		}
		System.out.println();
	}
	public String encStrad(String in){
		String res="";
		for(int i=0;i<in.length();i++){
			if(in.charAt(i)=='*'){
				continue;
			}
			for(int y=0;y<set.length;y++){
				for(int x=0;x<set[0].length;x++){
					if(in.charAt(i)==set[y][x]){
						res+=(left.charAt(y+1)!='*')?left.charAt(y+1)+"":"";
						res+=top.charAt(x);
					}
				}
			}
		}
		return res;
	}
	public String decStrad(String in){
		String res="";
		for(int i=0;i<in.length();i++){
			if(left.indexOf(in.charAt(i))>-1){
				if(i+1>=in.length()){
					break;
				}
				res+=set[left.indexOf(in.charAt(i))-1][top.indexOf(in.charAt(i+1))]+"";
				i++;
			}else{
				res+=set[0][top.indexOf(in.charAt(i))]+"";
			}
		}
		return res;
	}
	
	public String sub(String in, String aid, String key){
		String res="";
		for(int i=0;i<in.length();i++){
			char c=in.charAt(i);
			res+=key.charAt(aid.indexOf(c));
		}
		return res;
	}
	
	public String mod(String a, String b, boolean add){
		String res="";
		while(a.length()<b.length()){
			a=num.charAt(0)+a;
		}
		while(b.length()<a.length()){
			b=num.charAt(0)+b;
		}
		for(int k=0;k<a.length();k++){
			int i=0;
			if(add){
				i=num.indexOf(a.charAt(k))+num.indexOf(b.charAt(k));
				if(i>=num.length()){
					i-=num.length();
				}
			}else{
				i=num.indexOf(a.charAt(k))-num.indexOf(b.charAt(k));
				if(i<0){
					i+=num.length();
				}
			}
			res+=num.charAt(i)+"";
		}
		return res;
	}
	
	public String chain(String in, int n){
		int l=in.length();
		if(l<2){
			return in;
		}
		for(int i=0;i<n;i++){
			in+=mod(in.charAt(i)+"",in.charAt(i+1)+"",true);
		}
		return in.substring(l);
	}
	
	public String seq(String in){
		if(in.length()>num.length()){
			in=in.substring(0, 10);
		}
		char[] seq=new char[in.length()*2];
		for(int k=0;k<in.length();k++){
			seq[k]=in.charAt(k);
		}
		for(int k=0;k<in.length();k++){
			int m=0;
			for(int i=0;i<in.length();i++){
				if(alpha.indexOf(seq[i])>=alpha.indexOf(seq[m])){
					m=i;
				}
			}
			seq[m+in.length()]=alpha.charAt(in.length()-k-1);
			seq[m]='*';
		}
		String res="";
		for(int k=0;k<in.length();k++){
			res+=seq[k+in.length()];
		}
		return res;
	}
	
	public String encColTrans(String in, String key){
		String res="";
		char[] k=key.toCharArray();
		char[][] t=new char[(in.length()/key.length())+1][key.length()];
		int y=0,dx=0;
		for(int i=0;i<(t.length*t[0].length);i++){
			if(i-dx>=key.length()){
				dx+=key.length();
				y++;
			}
			if(i<in.length()){
				t[y][i-dx]=in.charAt(i);
			}else{
				t[y][i-dx]='*';
			}
		}
		for(int i=0;i<key.length();i++){
			int m=0;
			String last="";
			for(int ii=0;ii<key.length();ii++){
				if(alpha.indexOf(k[ii])>=alpha.indexOf(k[m])){
					m=ii;
				}
			}
			for(int dy=0;dy<t.length;dy++){
				if(t[dy][m]!='*'){
					last+=t[dy][m];
				}
			}
			k[m]='*';
			res=last+res;
		}
		return res;
	}
	
	public String decColTrans(String in, String key){
		String res="";
		char[] k=key.toCharArray();
		char[][] t=new char[(in.length()/key.length())+1][key.length()];
		for(int i=0;i<((t.length*t[0].length)-in.length());i++){
			t[t.length-1][t[0].length-1-i]='*';
		}
		for(int i=0;i<key.length();i++){
			//for(int y=0;y<t.length;y++){
			//	for(int x=0;x<t[0].length;x++){
			//		System.out.print(t[y][x]);
			//	}
			//	System.out.println();
			//}
			//System.out.println();
			int m=0;
			String last="";
			for(int ii=0;ii<key.length();ii++){
				if(alpha.indexOf(k[ii])>=alpha.indexOf(k[m])){
					m=ii;
				}
			}
			//System.out.println(i+" "+in);
			if(in.length()>0){
				if(t[t.length-1][m]!='*'){
					last=in.substring(in.length()-t.length, in.length());
					in=in.substring(0,in.length()-t.length);
				}else{
					last=in.substring(in.length()-t.length+1, in.length());
					in=in.substring(0,in.length()-t.length+1);
				}
				for(int dy=0;dy<t.length;dy++){
					if(t[dy][m]!='*'){
						t[dy][m]=last.charAt(dy);
					}
				}
				k[m]='*';
			}
		}
		for(int y=0;y<t.length;y++){
			for(int x=0;x<t[0].length;x++){
				if(t[y][x]!='*'){
					res+=t[y][x]+"";
				}
			}
		}
		return res;
	}
	
	public String encDisTrans(String in, String key){
		String pad=in+"";
		while(pad.length()%key.length()!=0){
			pad+="*";
		}
		char[] k=key.toCharArray(), p=pad.toCharArray();
		int i=0,ci=0,m=key.length(),l=key.length();
		while(i<in.length()){
			//for(int n=0;n<p.length;n++){
			//	System.out.print(((n%key.length()==0)?"\n":"")+p[n]);
			//}
			//System.out.println();
			//System.out.println("\n"+l+" "+m+" "+ci+" "+pad.length());
			
			if(l>=key.length()){
				if(m>=key.length()){
					m=0;
					for(int i1=0;i1<key.length();i1++){
						for(int ii=0;ii<key.length();ii++){
							if(alpha.indexOf(k[ii])!=-1&&alpha.indexOf(k[ii])<alpha.indexOf(k[m])){
								m=ii;
							}
						}
					}
					k[m]='*';
				}else{
					m++;
				}
				l=0;
			}
			if(l>=m){
				p[i]='*';
			}else{
				p[i]=in.charAt(ci);
				ci++;
			}
			l++;
			i++;
		}
		i=0;
		while(ci<in.length()){
			if(p[i]=='*'){
				p[i]=in.charAt(ci);
				ci++;
			}
			i++;
		}
		//for(int n=0;n<p.length;n++){
		//	System.out.print(((n%key.length()==0)?"\n":"")+p[n]);
		//}
		//System.out.println();
		return encColTrans(new String(p),key);
	}
	
	public String decDisTrans(String in, String key){
		String res="",pad=decColTrans(in, key);
		while(pad.length()%key.length()!=0){
			pad+="*";
		}
		char[] k=key.toCharArray(), p=pad.toCharArray();
		int i=0,m=key.length(),l=key.length();
		while(i<in.length()){
			//System.out.println(new String(p)+" "+l+" "+m);
			if(l>=key.length()){
				if(m>=key.length()){
					m=0;
					for(int i1=0;i1<key.length();i1++){
						for(int ii=0;ii<key.length();ii++){
							if(alpha.indexOf(k[ii])!=-1&&alpha.indexOf(k[ii])<alpha.indexOf(k[m])){
								m=ii;
							}
						}
					}
					k[m]='*';
				}else{
					m++;
				}
				l=0;
			}
			if(l>=m){
				
			}else{
				res+=p[i]+"";
				p[i]='*';
			}
			l++;
			i++;
		}
		i=0;
		while(i<in.length()){
			if(p[i]!='*'){
				res+=p[i];
			}
			i++;
		}
		return res;
	}
	
	public void setInfo(String n, String date, String phrase, String keygroup){
		this.id=n;
		this.date=date;
		this.phrase=phrase;
		this.keygroup=keygroup;
	}
	
	public void generateKeys(){
		key=new String[17];
		key[0]=keygroup;
		key[1]=date.substring(0, 5);
		key[2]=mod(key[0],key[1],false);
		key[3]=phrase.substring(0, 10)+" "+phrase.substring(10, 20);
		key[4]=seq(key[3].split(" ")[0])+" "+seq(key[3].split(" ")[1]);
		key[5]=key[2]+chain(key[2], 5)+" "+alpha.substring(0,num.length());
		key[6]=mod(key[4].split(" ")[0],key[5].split(" ")[0],true);
		key[7]=sub(key[6],key[5].split(" ")[1],key[4].split(" ")[1]);
		key[8]=seq(key[7]);
		key[9]=chain(key[7],10);
		key[10]=chain(key[9],10);
		key[11]=chain(key[10],10);
		key[12]=chain(key[11],10);
		key[13]=chain(key[12],10);
		
		String pkey=encColTrans(key[9]+key[10]+key[11]+key[12]+key[13],key[8]);
		int a=Integer.parseInt(id)+Character.getNumericValue(key[13].charAt(key[13].length()-2));
		int b=Integer.parseInt(id)+Character.getNumericValue(key[13].charAt(key[13].length()-1));
		key[14]=pkey.substring(0, a);
		key[15]=pkey.substring(a, a+b);
		key[16]=seq(key[13]);
	}
	
	public void printInfo(){
		System.out.println("Personal Number: "+id+"\nDate: "+date+"\nPhrase: "+phrase+"\nKeygroup: "+keygroup+"\n");
	}
	
	public void printKeys(){
		if(key==null){
			System.out.println("No keys found.");
			return;
		}
		String f="ABCDEFGHJKLMNPQRS";
		for(int i=0;i<key.length;i++){
			if(i==key.length-3){
				System.out.println();
			}
			System.out.println("[Line-"+f.charAt(i)+"]: "+key[i]);
		}
		System.out.println();
	}
	
	public VicCipher(){
		this("6", "1391956", "TWASTHENIGHTBEFORECHRIST", "72401");
		set("AT*ONE*SIRBCDFGHJKLMPQUVWXYZ./");
	}
	public VicCipher(String n, String date, String phrase, String keygroup){
		num="0123456789";
		alpha="1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ./";
		id=n;
		setInfo(n, date, phrase, keygroup);
		generateKeys();
		set("ET*AON*RISBCDFGHJKLMPQ/UVWXYZ.", key[16]);
	}
	
	public String encode(String in){
		String pad=encStrad(in),nul="",res="";
		for(int i=0;i<num.length();i++){
			if(left.indexOf(num.charAt(i))>-1){
				nul=num.charAt(i)+"";
				break;
			}
		}
		while(pad.length()%5!=0){
			pad+=nul;
		}
		pad=encColTrans(pad,key[14]);
		pad=encDisTrans(pad,key[15]);
		int pos=Integer.parseInt(date.charAt(5)+"")-1;
		if(pos<1){
			pad+=key[0];
		}else if(pos>pad.length()/5){
			pad=key[0];
		}else{
			pad=pad.substring(0, pad.length()-(pos*5))+key[0]+pad.substring(pad.length()-(pos*5));
		}
		for(int i=0;i<pad.length();i++){
			res+=(i%5!=0?"":" ")+pad.charAt(i);
		}
		return res.substring(1);
	}
	public String decode(String in){
		String pad="";
		for(int i=0;i<in.length();i++){
			if(in.charAt(i)!=' '){
				pad+=in.charAt(i);
			}
		}
		int pos=Integer.parseInt(date.charAt(5)+"")-1;
		if(pos<1){
			pad=pad.substring(0, pad.length()-5);
		}else if(pos>pad.length()/5){
			pad=pad.substring(5);
		}else{
			pad=pad.substring(0, pad.length()-5-(pos*5))+pad.substring(pad.length()-(pos*5));
		}
		pad=decDisTrans(pad,key[15]);
		pad=decColTrans(pad,key[14]);
		return decStrad(pad);
	}
	
	public static void main(String[] args){
		VicCipher vic=new VicCipher("6","139195","TWASTHENIGHTBEFORECH","72401");
		vic.set("AT*ONE*SIRBCDFGHJKLMPQUVWXYZ./");
		vic.printInfo();
		vic.printKeys();
		vic.printSet();
		
		//String k="94735236270398134";
		//String a=vic.encDisTrans("092002745346860181384805777868831596370253911018309880750797004794027027992906280860654204048324030833654811448180352434864084447840054705621546580540", k);
		//String b=vic.decDisTrans(a, k);
		//System.out.println(a+"\n"+b);
		//System.out.println();
		//System.out.println(vic.decStrad("599569645966583387658866583376025380000555000000808731980000999111555806776428818666766675499760287"));
		
		String n="/THISISOURLASTCRYBEFOREOURETERNALSILENCE.MAYTHYFLESHANDBONESBECONSUMED.GODSPEED./";
		//n="HELLOWORLD.";
		//System.out.println(n.length());
		String a=vic.encode(n);
		String b=vic.decode(a);
		System.out.println(a+"\n"+b);
		/*
		System.out.println(vic.chain("3288628787",50));
		System.out.println(vic.mod("1234","6789",true)); //7913
		System.out.println(vic.mod("1234","6789",false)); //5555
		System.out.println(vic.mod("72401","13919",false)); //69592
		System.out.println(vic.mod("8017942653","6959254417",true)); //4966196060
		System.out.println(vic.seq("90210")); //34215
		System.out.println(vic.seq("OCTOPUS")); //2163475
		System.out.println(vic.seq("TWASTHENIG")); //8017942653
		System.out.println(vic.seq("HTBEFORECH")); //6013589427
		System.out.println(vic.sub("90210","1234567890","6013589427")); //27067
		System.out.println(vic.sub("4966196060","1234567890","6013589427")); //3288628787
		System.out.println(vic.encColTrans("50648055525602850077162035074878238571255051328370","3178429506"));
		System.out.println(vic.decColTrans("06680055525517588383507132785060225420315007857427","3178429506"));
		System.out.println(vic.encColTrans("5064805552560285007716","2233"));
		System.out.println(vic.decColTrans("5850010022066558745657","2233"));
		System.out.println(vic.encDisTrans("092002745346860181384805777868831596370253911018309880750797004794027027992906280860654204048324030833654811448180352434864084447840054705621546580540", "94735236270398134"));
		System.out.println(vic.decDisTrans("361780542899592535070144000113420047468458426750484250310084691817728483603475035007668483882424283890960350713758689914050008042900873786014472544860", "94735236270398134"));
		System.out.println(vic.encDisTrans("32403842828881190214", "94735"));
		System.out.println(vic.decDisTrans("14812380198448223028", "94735"));
		System.out.println(vic.shuffle("1234567890"));
		vic.set("AT*ONE*SIRBCDFGHJKLMPQUVWXYZ./","5961328470");
		vic.generateKeys("6", "139195", "TWASTHENIGHTBEFORECH", "72401");
		System.out.println(vic.encStrad("MAY/THY/FLESH/AND/BONES/BE/CONSUMED."));
		System.out.println(vic.decStrad("605888096288806167246280536680651324806528069134866026687"));
		*/
		
		
	}

}
