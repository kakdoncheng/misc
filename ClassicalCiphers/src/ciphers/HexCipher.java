package ciphers;

//based on VIC/SECOM, but with more char space
//log2(C)*L with Line-H, yields ~64 bits of entropy

//outputs hex numbers instead of dec
//'*' is null placeholder

public class HexCipher {
	
	private String num,alpha,left,top;
	private String id,date,phrase,keygroup;
	private String[] key;
	private char[][] set;
	
	//Helper Operations
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
	public String newRandomSet(){
		return shuffle("****etaonrisTAIH")+shuffle("0123456789bcdfghjklmpquvwxyzBCDEFGJKLMNOPQRSUVWXYZ$,.?:;-+~! ^&/");
	}
	public void set(String in){
		set(in, key[19]);
	}
	public void set(String in, String key){
		top=key;
		left="/*";
		set=new char[5][16];
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
	public void setInfo(String n, String date, String phrase, String keygroup){
		this.id=n;
		this.date=date;
		this.phrase=phrase;
		this.keygroup=keygroup;
	}
	
	public void generateKeys(){
		String sphrase=phrase.replaceAll("\\s+","").toUpperCase();
		key=new String[20];
		key[0]=keygroup;
		key[1]=date.substring(0, 8);
		key[2]=mod(key[0],key[1],false);
		key[3]=sphrase.substring(0, 16)+" "+sphrase.substring(16, 32);
		key[4]=seq(key[3].split(" ")[0])+" "+seq(key[3].split(" ")[1]);
		key[5]=key[2]+chain(key[2], 8)+" "+alpha.substring(0,num.length());
		key[6]=mod(key[4].split(" ")[0],key[5].split(" ")[0],true);
		key[7]=sub(key[6],key[5].split(" ")[1],key[4].split(" ")[1]);
		key[8]=seq(key[7]);
		key[9]=chain(key[7],16);
		key[10]=chain(key[9],16);
		key[11]=chain(key[10],16);
		key[12]=chain(key[11],16);
		key[13]=chain(key[12],16);
		key[14]=chain(key[13],16);
		key[15]=chain(key[14],16);
		key[16]=chain(key[15],16);
		
		String pkey=encColTrans(key[9]+key[10]+key[11]+key[12]+key[13]+key[14]+key[15]+key[16],key[8]);
		int a=num.indexOf(id.charAt(id.length()-1))+num.indexOf(key[16].charAt(key[16].length()-2));
		int b=num.indexOf(id.charAt(id.length()-1))+num.indexOf(key[16].charAt(key[16].length()-1));
		key[17]=pkey.substring(0, a);
		key[18]=pkey.substring(a, a+b);
		key[19]=seq(key[16]);
	}
	
	//Output
	public void printInfo(){
		System.out.println("ID: "+id+"\nDate: "+date+"\nPhrase: "+phrase+"\nKeygroup: "+keygroup+"\n");
	}
	public void printKeys(){
		if(key==null){
			System.out.println("No keys found.");
			return;
		}
		String f="ABCDEFGHJKLMNPXYZQRS";
		for(int i=0;i<key.length;i++){
			if(i==key.length-3){
				System.out.println();
			}
			System.out.println("[Line-"+f.charAt(i)+"]: "+key[i]);
		}
		System.out.println();
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
	
	//Constructor
	public HexCipher(String n, String date, String phrase, String keygroup) {
		num="0123456789ABCDEF";
		alpha="1234567890ABCDEFghijklmnopqrstuvwxyzabcdefGHIJKLMNOPQRSTUVWXYZ$,.?:;-+~! ^&/";
		setInfo(n,date,phrase,keygroup);
		generateKeys();
		set(" et*a*onri*sT*AH0123456789bcdfghjklmpquvwxyzBCDEFGIJKLMNOPQRSUVWXYZ$,.?:;-+~!^&/", key[19]);
	}
	public HexCipher(){
		this("3F","04011993","This is our last cry before eternal silence.","7204FC1A");
	}
	
	//Substitution
	public String sub(String in, String aid, String key){
		String res="";
		for(int i=0;i<in.length();i++){
			char c=in.charAt(i);
			res+=key.charAt(aid.indexOf(c));
		}
		return res;
	}
	
	//Modulo
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
	
	//Chain Fibonacci Addition
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
	
	//Sequencing
	public String seq(String in){
		if(in.length()>num.length()){
			in=in.substring(0, num.length());
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
	
	//Straddle Checkerboard Encoding
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
	
	//Columnar Transposition
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
			int m=0;
			String last="";
			for(int ii=0;ii<key.length();ii++){
				if(alpha.indexOf(k[ii])>=alpha.indexOf(k[m])){
					m=ii;
				}
			}
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
	
	//Diagonal/Disrupted Transposition
	public String encDisTrans(String in, String key){
		String pad=in+"";
		while(pad.length()%key.length()!=0){
			pad+="*";
		}
		char[] k=key.toCharArray(), p=pad.toCharArray();
		int i=0,ci=0,m=key.length(),l=key.length();
		while(i<in.length()){
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
	
	//Hex Cipher
	public String encode(String in){
		String pad=encStrad(in),nul="",res="";
		for(int i=0;i<num.length();i++){
			if(left.indexOf(num.charAt(i))>-1){
				nul=num.charAt(i)+"";
				break;
			}
		}
		while(pad.length()%8!=0){
			pad+=nul;
		}
		pad=encColTrans(pad,key[17]);
		pad=encDisTrans(pad,key[18]);
		int pos=Integer.parseInt(date.charAt(3)+"")-1;
		if(pos<1){
			pad+=key[0];
		}else if(pos>pad.length()/8){
			pad=key[0];
		}else{
			pad=pad.substring(0, pad.length()-(pos*8))+key[0]+pad.substring(pad.length()-(pos*8));
		}
		for(int i=0;i<pad.length();i++){
			res+=(i%8!=0?"":" ")+pad.charAt(i);
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
		int pos=Integer.parseInt(date.charAt(3)+"")-1;
		if(pos<1){
			pad=pad.substring(0, pad.length()-8);
		}else if(pos>pad.length()/8){
			pad=pad.substring(8);
		}else{
			pad=pad.substring(0, pad.length()-8-(pos*8))+pad.substring(pad.length()-(pos*8));
		}
		pad=decDisTrans(pad,key[18]);
		pad=decColTrans(pad,key[17]);
		return decStrad(pad);
	}
	
	public void sanityTest(){
		HexCipher hex=new HexCipher("3F","04011993","One digital signature scheme, of many, is based on RSA.","1F4B693C");
		hex.printInfo();
		hex.printKeys();
		hex.printSet();
		
		String n="/Do You Think God Stays In Heaven Because He, Too, Lives In Fear Of What He's Created Here On Earth?/";
		String a=hex.encode(n);
		String b=hex.decode(a);
		System.out.println(a+"\n"+b);
	}

}
