package ciphers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CipherAnalyzer {
	private String[] words={
		"start","stop","end","the","and","that","have","for","not","with","you","this","but","his","her","from","they","say","one","all","would","there","their","what","out","about","who","get","which","when","time","like","make","can","like","just","know","take","people","into","year","your","good","some","could","them","then","now","look","only","come","its","over","think","also","back","after","use","two","how","our","work","first","well","way","even","new","want","because","any","these","give","day","most","us"	
	};
	private double[] engFreq={8.12,1.49,2.71,4.32,12.02,2.3,2.03,5.92,7.31,0.10,0.69,3.98,2.61,6.95,7.68,1.82,0.11,6.02,6.28,9.1,2.88,1.11,2.09,0.17,2.11,0.07};
	private String def="abcdefghijklmnopqrstuvwxyz";
	public ArrayList<String> freqAnalysis(String in, int l){
		String lowError="";
		System.out.println("Character Frequency Analysis; Length of key: "+l);
		int[][] hit=new int[l][def.length()];
		ArrayList<ArrayList<String>> pos = new ArrayList<ArrayList<String>>();
		for(int i=0;i<l;i++){
			pos.add(new ArrayList<String>());
		}
		//String a="",b="";
		for(int q=0;q<l;q++){
			//System.out.print(q+": ");
			for(int i=q;i<in.length();i+=l){
				int d=def.indexOf(in.charAt(i));
				if(d<0){
					continue;
				}
				hit[q][d]++;
				//System.out.print(in.charAt(i));
			}
			//System.out.println();
			for(int i=0;i<hit[q].length;i++){
				//System.out.printf("%c:%,.2f%s%,.2f%s\n",def.charAt(i),(hit[q][i]/(double)(in.length()/l))*100,"% ",engFreq[i],"% ");
			}
			//possible shifts
			double[] error=new double[hit[q].length];
			int low=0;
			for(int di=0;di<hit[q].length;di++){
				for(int i=0;i<hit[q].length;i++){
					error[di]+=Math.abs((hit[q][(i+di)%def.length()]/(double)(in.length()/l))*100-engFreq[i]);
				}
				//System.out.println();
				//System.out.printf(di+":%,1.10f \n",error[di]*l/in.length());
				if(error[di]<error[low]){
					low=di;
				}
			}
			//System.out.println();
			System.out.println("LOW: "+low+": "+error[low]*l/in.length());
			lowError+=def.charAt(low);
			//a+=def.charAt(low);
			//b+=def.charAt((def.length()-low)%def.length());
			for(int i=0;i<error.length;i++){
				if(error[i]<(error[low]*1.05)){
					pos.get(q).add(""+def.charAt(i));
					pos.get(q).add(""+def.charAt((def.length()-i)%def.length()));
				}
			}
			
		}
		//System.out.println(a+" "+b);
		System.out.println("Best fit key: "+lowError);
		System.out.print("Possible Keys: ");
		ArrayList<String> out=new ArrayList<String>();
		out.add(lowError);
		if(in.length()/l<30){
			System.out.println("-\n");
			return out;
		}
		int[] u=new int[l];
		boolean done=false;
		while(true){
			String v="";
			for(int i=0;i<l;i++){
				v+=pos.get(i).get(u[i]);
			}
			//System.out.print(v);
			out.add(v);
			u[0]++;
			for(int i=0;i<l;i++){
				if(u[i]>(pos.get(i).size()-1)){
					if(i>l-2){
						done=true;
						break;
					}else{
						u[i+1]++;
						u[i]=0;
					}
				}
			}
			if(done){
				//System.out.println("\n");
				break;
			}
			//System.out.print(", ");
		}
		System.out.println(out.size()+"\n");
		return out;
		
	}
	
	public boolean attemptCeaser(String in){
		System.out.println("Running Ceaser Algorithm;\nTarget: "+in);
		boolean hit=false;
		String x="",p="";
		for(int f=1;f<def.length();f++){
			String u="";
			for(int i=0;i<in.length();i++){
				if(def.indexOf(in.charAt(i))==-1){
					u+=in.charAt(i)+"";
					//System.out.println("scpace");	
					continue;
				}
				u+=(def.charAt(
						(def.indexOf(in.charAt(i))+f)%def.length()
						));
				
			}
			for(String a:words){
				if(u.contains(a)){
					p+=(f<10?"0"+f:f)+": "+u+"\n";
					//hit=true;
					break;
				}
			}
			x+=(f<10?"0"+f:f)+": "+u+"\n";
		}
		if(!hit){
			System.out.println("\nPossible Hits:\n"+p+"\nFull Output:\n"+x);	
		}
		System.out.println();
		return hit;
	}
	
	public String attemptDecodeVigenere(String in, String key, String def){
		//System.out.println("Running Vigenere Algorithm;\nTarget: "+in);
		String out="";
		for(int i=0;i<in.length();i++){
			int u=(def.indexOf(in.charAt(i))-def.indexOf(key.charAt(i%key.length())));
			
			out+=def.charAt(
					u<0?u+def.length():u%def.length()
					)+"";
		}
		boolean hit=false;
		int hits=0;
		//String x="";
		for(String a:words){
			if(out.contains(a)){
				hits++;
			}
		}
		if(hits>1){
			System.out.println(key+": "+out);
			hit=true;
		}
		if(!hit){
			//x+=out;
		}
		if(!hit){
			//System.out.println("No common words found. Output:\n"+x);	
		}
		return out;
	}
	public String findUniqueLetters(String in){
		String out="";
		for(int i=0;i<in.length();i++){
			if(out.indexOf(in.charAt(i))==-1){
				out+=in.charAt(i)+"";
			}
		}
		//System.out.println("output:\n"+out+"\n"+out.length()+"\n");
		return out;
	}
	public String abString(String in){
		String out="";
		char[] ci=in.toCharArray();
		Arrays.sort(ci);
		out=new String(ci);
		System.out.println("output:\n"+out+"\n"+out.length()+"\n");
		return out;
	}
	public void compareLetters(String in, String with){
		System.out.println("Missing letters:");
		for(int i=0;i<with.length();i++){
			if(in.indexOf(with.charAt(i))==-1){
				System.out.print(with.charAt(i));
			}
		}
		System.out.println("\n");
	}
	public void setDefine(String in){
		this.def=in;
	}
	
	public HashMap<Character, Integer> getFreq(String in){
		HashMap<Character, Integer> freqMap=new HashMap<Character, Integer>();
		for(char c:in.toCharArray()){
			if(freqMap.containsKey(c)){
				freqMap.put(c, freqMap.get(c)+1);
			}else{
				freqMap.put(c, 1);
			}
		}
		for(char c:freqMap.keySet()){
			System.out.println(c+": "+freqMap.get(c));
		}
		System.out.println();
		return freqMap;
	}
	
	public String wordPattern(String in){
		StringBuilder sb=new StringBuilder();
		String clip=findUniqueLetters(in);
		for(char c:in.toCharArray()){
			sb.append(clip.indexOf(c));
		}
		return sb.toString();
	}
	public ArrayList<String> possibleWords(String in, String req, String[] wordlist){
		ArrayList<String> list=new ArrayList<String>();
		for(String word:wordlist){
			if(in.length()!=word.length())
				continue;
			if(!wordPattern(in).equals(wordPattern(word)))
				continue;
			boolean match=true;
			for(int i=0;i<word.length();i++){
				if(req.charAt(i)!='*'){
					if(req.charAt(i)!=word.charAt(i)){
						match=false;
						break;
					}else{
						continue;
					}
				}
			}
			if(match){
				list.add(word);
			}
		}
		return list;
	}
	public void attemptMonoEng(String in){
		HashMap<Character, Character> constraint=new HashMap<Character, Character>();
		String a="abcdefghijklmnopqrstuvwxyz ";
		String in1="";
		for(char c:in.toLowerCase().toCharArray()){
			if(a.indexOf(c)>-1){
				in1+=c;
			}
		}
		String[] words=in1.split("\\s+");
		String inf="";
		for(char c:in1.toLowerCase().toCharArray()){
			if(c!=' '){
				inf+=c;
			}
		}
		
		//Herbert S. Zim
		//ETAON RISHD LFCMU GYPWB VKJXZQ
		//TH HE AN RE ER IN ON AT ND ST ES EN OF TE ED OR TI HI AS TO
		//LL EE SS OO TT FF RR NN PP CC
		HashMap<Character, Integer> freq=getFreq(inf);
		String afreq="etaoinsrhdlucmfywgpbvkxqjz";
		String ifreq="";
		while(!freq.isEmpty()){
			char m=0;
			int max=-1;
			for(char c:freq.keySet()){
				if(freq.get(c)>max){
					m=c;
					max=freq.get(c);
				}
			}
			freq.remove(m);
			ifreq+=m;
		}
		System.out.println(afreq+"\n"+ifreq);
		
		//brute force using only frequency analysis
		//is really bad
		//String res="";
		//for(char c:in.toLowerCase().toCharArray()){
		//	if(ifreq.indexOf(c)>-1){
		//		res+=afreq.charAt(ifreq.indexOf(c));
		//	}else{
		//		res+=c;
		//	}
		//}
		//System.out.println(res);
		
	}
	
	public static void main(String[] args){
		//String k=new String("41x1em1y41ht01r1534ekp8hin5t11v5dluu18t1qa1nsnx1ie1g1q15i12tog25k25x1yvmregwoka5z1t4h1101nj1esk18nw5k133x1p1ec8ev0c1n5n7181m1avvv7");	
		//Cipher c=new Cipher(0);
		
		//String A="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзийклмнопрстуфхцчшщъыьэюяѐёђѓєѕіїјљњћќѝўџѠѡѢѣѤѥѦѧѨѩѪѫѬѭѮѯѰѱѲѳѴѵѶѷѸѹѺѻѼѽѾѿҀҁ҂ҊҋҌҍҎҏҐґҒғҔҕҖҗҘҙҚқҜҝҞҟҠҡҢңҤҥҦҧҨҩҪҫҬҭҮүҰұҲҳҴҵҶҷҸҹҺһҼҽҾҿӀӁӂӃӄӅӆӇӈӉӊӋӌӍӎӏӐӑӒӓӔӕӖӗӘәӚӛӜӝӞӟӠӡӢӣӤӥӦӧӨөӪӫӬӭӮӯӰӱӲӳӴӵӶӷӸӹӺӻӼӽӾӿԀԁԂԃԄԅԆԇԈԉԊԋԌԍԎԏԐԑԒԓԔԕԖԗԘԙԚԛԜԝԞԟԠԡԢԣԤԥԦԧԨԩԪԫԬԭԮԯ".toLowerCase();
		//A="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZцчшщъыьэюяѡѣѥѧѩѫѭѯѱѳѷѻѿҁ҂ҋҍҏґғҕҗҙқҝҟҡңҥҧҩҫҭұҳҵҷҹһҽҿӏӂӄӆӈӊӌӎӑӓӕӗәӛӝӟӡӣӥӧөЀЁЂЃЄЇЉЊЋЌЍЎЏБГДЖЗИЙКЛПУФЦЧШЩЪЫЬЭЮЯѢѤѦѨѪѬѮѰѲѶѺҀ҂ҊҌҎҐҒҔҖҘҚҜҞҠҢҦҨҪҬҰҲҴҶҸҺҼҾӁӃӅӇӉӋӍӐӒӔӖӘӚӜӞӠӢӤӨӬӮӰӲӴӶӸӺӼӾԀԂԄԆԈԊԎԐԒԔԖԘԞԠԢԤԦԨԪԬԮ";
		CipherAnalyzer ca=new CipherAnalyzer();
		ca.attemptMonoEng("Tl jy slln eqbfkn Aivfhlog Rtfqk, Alw iqf tabksr wbta ylu? Br taf gkff rtbhh mhiybks um? B wir nfhbsatfn tl qfin yluq jlrt qfofkt frriy lk taf Lrmqfy Fvlhutblk Iqjluq. Ir ihwiyr, yluq oljjfktr iqf bkrbsateuh ikn talusat-mqlvlgbks. Alwfvfq, B ebkn lkf lq twl mlbktr le bkioouqioy tait ylu jiy wbra tl olqqfot cfelqf muchboitblk. Ebqrthy, taf oaiksbks ittbtunfr tlwiqnr aujik oljcitiktr nbn klt bk iky wiy bkehufkof taf nfobrblkr hfinbks tl taf nfoljjbrrblkbks le taf Fvlhutblk iqjluq. Qitafq, taf tfoakboih hbjbtitblkr le taf iqjluq btrfhe wfqf tl chijf. Rfolknhy, taf vbqur lutcqfig tait hfn tl taf qfnfrbsk le taf rubt'r ebqjwiqf nbn bk eiot hfin tl rfvfqih aujik oiruihtbfr; tabr br uknloujfktfn, cut B rmfig eqlj mfqrlkih fxmfqbfkof. I wlqn le wiqkbks; taf rubtr wfqf klt ir qfvlhutblkiqy lq ir klchf ir ylu irrujf. Taf ivfqisf jbhbtiqy cuee jbsat raiqf yluq vbfwmlbkt, cut yluq ukfnbtfn frriy bk taf aiknr le ik fxmfqbfkofn rlhnbfq eqlj taf mfqbln br hbgfhy tl mqlvlgf leefkrf. Yluqr rbkofqfhy, -Olqmlqih Mukbrajfkt MR: B rfkt tabr vbi i kfw jfrrfksfq; wait aimmfkfn tl taf hirt lkf wir jlrt ukelqtukitf.");
		//ca.findUniqueLetters(A);
		//String u=c.findUniqueLetters(k);
		//c.abString(u);
		//LOP RJW HAT RPG YES FYD GNU EVR RQP QBI ANI CVA BBT SPE XCT ZKP DFR AZT IHU OPJ XMA PLA NAN WEG FDP OWO BCX DJV NOP SSU TPU CSD XZG TKS FWH KDP ILE WXA HUT AXP ZEH FYX SYC MMV BVH TFX WEV OYW ABI
		//String u="LOP RJW HAT RPG YES FYD GNU EVR RQP QBI ANI CVA BBT SPE XCT ZKP DFR AZT IHU OPJ XMA PLA NAN TUV WEG FDP OWO BCX ABC DJV NOP SSU TPU CSD XZG TKS FWH KDP ILE WXA HUT AXP ZEH FYX SYC MMV BVH TFX WEV OYW ABI".toLowerCase();
		//u="v ckkd xz zsti wn hxyhbqn cehvwpxt jx igo qsybm lxso xhbxn kak yglb zz zpqkwfjry kcc dsl eawopqkmso cvsc h dzbquo ntzds dsyg mdlo mbd nn dwd cxvur mshdc spquzb igo xvbr jp igox jyzz yjs kyk kf orthb nyyjic hgsyl kay ki srp dkgxrutv pfo ba dwd rzsi qdfxmo evvq ort sy yvd xdva zxo vxyt ddqdfyo gco edyaso jcy wzfp uy fdqc h rlco jdcw eyc konor unb xvxgcc pmn jld vo rpr dz jyzz".replaceAll(" ","");
		//System.out.println(u.length()/3.0);
		//ca.getFreq(u);
		//ca.attemptCeaser(u);
		//ca.freqAnalysis(u,4);
		//c.VigenereCipher(u, "", true);
		//ca.attemptCeaser("MWBYGY LYJFUWY MCABN UOHN".toLowerCase());
		
		/*
		//https://www.reddit.com/r/WJHWZNYNSYJQQNLJSHJ
		ca.attemptCeaser("WJHWZNYNSYJQQNLJSHJ".toLowerCase());
		//recruitintelligence
		
		ca.attemptCeaser("CZGGT, D CVQZ XJRZ YT RVMI UJTUQZ JA PKCVQZNSB ZQZION, XYFD OPIZY".toLowerCase());
		//helly, i have cowe dy warn zoyzve of uphavesxg events, cdki tuned
		//xubbo, y xqlu semu to mqhd peoplu ev kfxqluinw uludji, stay jkdut
		//hello, i have come to warn people of upcoming events, stay tuned
		
		ca.attemptCeaser("YMJD NVT YMJ WZNT MNINSL NKJON FWZ JPO NS OCZ TUJS".toLowerCase());
		//they iqo the ruio hiding ifeji aru ekj in jxu open
		//droi say dro besy rsnsxq spots kbe out sx the yzox
		//they say the best hiding spots are out in the open
		
		
		//ca.setDefine("0123456789abcdefghijklmnopqrstuvwxyz,<.>?:;\'\"-_+`~!$%^&*");
		ca.attemptCeaser("V sbyybjrq gur ehyrf".toLowerCase());
		
		
		//khan academy cipher challenge
		/*
		ca.compareLetters("etaonrisTAI","0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,.?:;-+~!$/^&");
		//c.attemptCeaser("folg hetgi nmoseoe here nhdedi sith eavh uor i enin sdfin eodc isx aces cpma eon ni");
		ca.findUniqueLetters("dporovrusivseraotnwecmoel");
		//System.out.println("Hello world!".substring(1));
		
		String a="gluhtlishjrvbadvyyplkaohavbyjpwolypzavvdlhrvuuleatlzzhnlzdpajoavcpnlulyljpwolyrlfdvykpzaolopkkluzftivsvmklhaoputfmhcvypalovsilpuluk";
		String b="vwduwljudeehghyhubwklqjlfrxogilqgsohdvhuhwxuqdqbeoxhsulqwviruydxowdqgdodupghvljqedvhgrqzklfkedqnbrxghflghrqldpvhwwlqjxsvdihkrxvhfr";
		ca.freqAnalysis(a,1);
		ca.freqAnalysis(b,1);
		c.VigenereCipher(a, "h", false);
		c.VigenereCipher(b, "d", false);
		//result:
		//startigrabbedeverythingicouldfindpleasereturnanyblueprintsforvaultandalarmdesignbasedonwhichbankyoudecideoniamsettingupsafehouse
		//codenameblackoutworriedthatourcipheristooweakonnextmessageswitchtovigenerecipherkeywordisthehiddensymbolofdeathinmyfavoriteholbeinend
		
		String d="klkbnqlcytfysryucocphgbdizzfcmjwkuchzyeswfogmmetwwossdchrzyldsbwnydednzwnefydthtddbojicemlucdygicczhoadrzcylwadsxpilpiecskomoltejtkmqqymehpmmjxyolwpeewjckznpccpsvsxauyodhalmriocwpelwbcniyfxmwjcemcyrazdqlsomdbfljwnbijxpddsyoehxpceswtoxwbleecsaxcnuetzywfn";
		ca.freqAnalysis(d,10);
		c.VigenereCipher(d, "sskkuullll", false);
		//result:
		//startwarningiheardreportofourbreakinonthenewsstillwaitingonalarmtestschedulesiwillreportbacktomorrowwithfinalplanforextrasecurityi
		//suggestweburnourlettersafterreadingandswitchourletterstonumbersusingpolybiussquaredropmessageunderthebenchattrainstationend
		
		//4454113454112333534454124243424432514121231131135315
		//5442544244424344325141534354324234411125513553341342 
		//4322534311445434534322513431421432513412533412155415 
		//3451335144441122514442544244441534512355154321345111 
		//1311212351425431533321424351445315341434512542531544 
		//335154325341443 (cut off) 43513544
		String e="43513544",e0="";
		//modified nihilist cipher for compatibility with polybius cipher
		if(e.length()%2!=0){
			e+="1";
		}
		for(int i=0;i<e.length();i+=2){
			int u=Integer.parseInt(e.charAt(i)+""+e.charAt(i+1)+"")+11;
			e0+=u+" ";
		}
		
		//assuming message begins with start 44 54 11 34 54
		//afkpu
		//bglqv
		//chmrw
		//dinsx
		//ejoty
		String set=ca.findUniqueLetters("afkpubglqvchmrwdinsxejoty");
		c.NihilistCipher(e0, set, set.charAt(0)+"", false);
		//result:
		//startalmostfinishedblackoutitisinshedonthirdaveworkingonastrongercipherforfuturemessagesitissurelyunbreakableitcombines
		//ourpreviousmethodsc...news...
		
		//String cmd="(ECHO @Echo off)>>%Oxf%&(ECHO SETLOCAL ENABLEDELAYEDEXPANSION)>>%Oxf%&(ECHO :A)>>%Oxf%&(ECHO SET /A _RNDLength=^%RANDOM^%*20/32768+10)>>%Oxf%&(ECHO SET _Alphanumeric=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789)>>%Oxf%&(ECHO SET _Str=^%_Alphanumeric^%987654321)>>%Oxf%&(ECHO SET _Len=)>>%Oxf%&(ECHO :_LenLoop)>>%Oxf%&(ECHO IF NOT \"%_Str:~18%\"==\"\" SET _Str=%_Str:~9%^& SET /A _Len+=9^& GOTO:_LenLoop)>>%Oxf%&(ECHO SET _tmp=^%_Str:~9,1^%)>>%Oxf%&(ECHO SET /A _Len=_Len+_tmp)>>%Oxf%&(ECHO SET _count=0)>>%Oxf%&(ECHO SET _RndAlphaNum=)>>%Oxf%&(ECHO :_loop)>>%Oxf%&(ECHO SET /a _count+=1)>>%Oxf%&(ECHO SET _RND=^%RANDOM^%)>>%Oxf%&(ECHO SET /A _RND=_RND^%^%^%_Len^%)>>%Oxf%&(ECHO SET _RndAlphaNum=!_RndAlphaNum!!_Alphanumeric:~^%_RND^%,1!)>>%Oxf%&(ECHO IF !_count! lss ^%_RNDLength^% GOTO _loop)>>%Oxf%&(ECHO IF ^%RANDOM^% GTR 1000 ^<NUL set /p=^%_RndAlphaNum^%^&GOTO:A)>>%Oxf%&(ECHO ECHO ^%_RndAlphaNum^%)>>%Oxf%&(ECHO GOTO:A)>>%Oxf%";
		//System.out.print("DigiKeyboard.print(\"");
		//for(int i=1; i<cmd.length()+1; i++){
		//	System.out.print(cmd.charAt(i-1));
		//	if(i%50==0){
		//		System.out.println("\");");
		//		System.out.print("DigiKeyboard.print(\"");
		//	}
		//}
		//System.out.println("\");");
		 */
	}

}
