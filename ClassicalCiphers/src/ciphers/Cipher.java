package ciphers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Cipher {
	private String[] alphabet={
			"abcdefghijklmnopqrstuvwxyz",//26
			"0123456789abcdefghijklmnopqrstuvwxyz",//36
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",//52
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ",//53
			"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",//62
			"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ",//63
			"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,<.>?:;\'\"-_+`~!$%^&*",//82
			"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ,<.>?:;\'\"-_+`~!$%^&*",//83
			"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÙÛéÊäåíÍÎ‡ÒðøØÕÿýµúûüãáÅ,<.>?:;\'\"-_+`~!$%^&*¿",//107
			"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZÙÛéÊäåíÍÎ‡ÒðøØÕÿýµúûüãáÅ ,<.>?:;\'\"-_+`~!$%^&*¿",//108
			"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ αβΓγΔδεζηΘθΛλμΞξΠπρΣσςΦφΨΩωÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿ‡,<.>?:;\'\"-_+`~!$¢£¤€¥%^&*¿",
			
			" !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~¡¢£¤¥¦§¨±´µ»«¬¯°"
			+ "¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿĀāĂăĄąĆćĈĉĊċČčĎďĐđĒēĔĕĖėĘęĚěĜĝĞğĠġĢģĤĥĦħĨĩĪīĬĭĮįİıĲĳĴĵĶķĸĹĺĻļĽľĿŀŁłŃńŅņŇňŉŊŋŌōŎŏŐőŒœŔŕŖŗŘřŚśŜŝŞşŠšŢţŤťŦŧŨũŪūŬŭŮůŰűŲųŴŵŶŷŸŹźŻżŽžſ"
			+ "ƀƁƂƃƄƅƆƇƈƉƊƋƌƍƎƏƐƑƒƓƔƕƖƗƘƙƚƛƜƝƞƟƠơƢƣƤƥƦƧƨƩƪƫƬƭƮƯưƱƲƳƴƵƶƷƸƹƺƻƼƽƾƿǀǁǂǃǄǅǆǇǈǉǊǋǌǍǎǏǐǑǒǓǔǕǖǗǘǙǚǛǜǝǞǟǠǡǢǣǤǥǦǧǨǩǪǫǬǭǮǯǰǱǲǳǴǵǶǷǸǹǺǻǼǽǾǿȀȁȂȃȄȅȆȇȈȉȊȋȌȍȎȏȐȑȒȓȔȕȖȗȘșȚțȜȝȞȟȠȡȢȣȤȥȦȧȨȩȪȫȬȭȮȯȰȱȲȳȴȵȶȷȸȹȺȻȼȽȾȿɀɁɂɃɄɅɆɇɈɉɊɋɌɍɎɏ"
			+ "ⱠⱡⱢⱣⱤⱥⱦⱧⱨⱩⱪⱫⱬⱭⱮⱯⱰⱱⱲⱳⱴⱵⱶⱷⱸⱹⱺⱻⱼⱽⱾⱿ"
			+ "ꭥꙀꙁꙂꙃꙄꙅꙆꙇꙈꙉꙊꙋꙌꙍꙎꙏꙐꙑꙒꙓꙔꙕꙖꙗꙘꙙꙚꙛꙜꙝꙞꙟꙠꙡꙢꙣꙤꙥꙦꙧꙨꙩꚀꚁꚄꚅꚆꚇꚈꚉꚊꚋꚌꚍꚎꚏꚐꚑꚒꚓꚔꚕꚖꚗꚘꚙꚚꚛ"
			+ "ḂḃḄḅḆḇḈḉḊḋḌḍḎḏḐḑḒḓḔḕḖḗḘḙḚḛḜḝḞḟḠḡḢḣḤḥḦḧḨḩḪḫḬḭḮḯḰḱḲḳḴḵḶḷḸḹḺḻḼḽḾḿṀṁṂṃṄṅṆṇṈṉṊṋṌṍṎṏṐṑṒṓṔṕṖṗṘṙṚṛṜṝṞṟṠṡṢṣṤṥṦṧṨṩṪṫṬṭṮṯṰṱṲṳṴṵṶṷṸṹṺṻṼṽṾṿẀẁẂẃẄẅẆẇẈẉẊẋẌẍẎẏẐẑẒẓẔẕẖẗẘẙẚẛẜẝẞẟẠạẢảẤấẦầẨẩẪẫẬậẮắẰằẲẳẴẵẶặẸẹẺẻẼẽẾếỀềỂểỄễỆệỈỉỊịỌọỎỏỐốỒồỔổỖỗỘộỚớỜờỞởỠỡỢợỤụỦủỨứỪừỬửỮữỰựỲỳ"
			+ "ɐɑɒɓɔɕɖɗɘəɚɛɜɝɞɟɠɡɢɣɤɥɦɧɨɩɪɫɬɭɮɯɰɱɲɳɴɵɶɷɸɹɺɻɼɽɾɿʀʁʂʃʄʅʆʇʈʉʊʋʌʍʎʏʐʑʒʓʔʕʖʗʘʙʚʛʜʝʞʟʠʡʢʣʤʥʦʧʨʩʪʫʬʭʮʯ"
			+ "ᴀᴁᴂᴃᴄᴅᴆᴇᴈᴉᴊᴋᴌᴍᴎᴏᴐᴑᴒᴓᴔᴕᴖᴗᴘᴙᴚᴛᴜᴝᴞᴟᴠᴡᴢᴣᴤᴥᴦᴧᴨᴩᴪᴫᵫᵬᵭᵮᵯᵰᵱᵲᵳᵴᵵᵶᵷᵸᵹᵺᵻᵼᵽᵾᵿᶀᶁᶂᶃᶄᶅᶆᶇᶈᶉᶊᶋᶌᶍᶎᶏᶐᶑᶒᶓᶔᶕᶖᶗᶘᶙᶚ"
			+ "ЀЁЂЃЄЅІЇЈЉЊЋЌЍЎЏАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдежзийклмнопрстуфхцчшщъыьэюяѐёђѓєѕіїјљњћќѝўџѠѡѢѣѤѥѦѧѨѩѪѫѬѭѮѯѰѱѲѳѴѵѶѷѸѹѺѻѼѽѾѿҀҁ҂ҊҋҌҍҎҏҐґҒғҔҕҖҗҘҙҚқҜҝҞҟҠҡҢңҤҥҦҧҨҩҪҫҬҭҮүҰұҲҳҴҵҶҷҸҹҺһҼҽҾҿӀӁӂӃӄӅӆӇӈӉӊӋӌӍӎӏӐӑӒӓӔӕӖӗӘәӚӛӜӝӞӟӠӡӢӣӤӥӦӧӨөӪӫӬӭӮӯӰӱӲӳӴӵӶӷӸӹӺӻӼӽӾӿ"
			+ "ԀԁԂԃԄԅԆԇԈԉԊԋԌԍԎԏԐԑԒԓԔԕԖԗԘԙԚԛԜԝԞԟԠԡԢԣԤԥԦԧԨԩԪԫԬԭԮԯ",
			
			" !\"$%&\'*+,-.0123456789:;<>?ABCDEFGHIJKLMNOPQRSTUVWXYZ^_`abcdefghijklmnopqrstuvwxyz|~¡¢£¤¥¦§¨±´µ"
			+ "¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿĀāĂăĄąĆćĈĉĊċČčĎďĐđĒēĔĕĖėĘęĚěĜĝĞğĠġĢģĤĥĦħĨĩĪīĬĭĮįİıĲĳĴĵĶķĸĹĺĻļĽľĿŀŁłŃńŅņŇňŉŊŋŌōŎŏŐőŒœŔŕŖŗŘřŚśŜŝŞşŠšŢţŤťŦŧŨũŪūŬŭŮůŰűŲųŴŵŶŷŸŹźŻżŽžſ"
			+ "ƀƁƂƃƄƅƆƇƈƉƊƋƌƍƎƏƐƑƒƓƔƕƖƗƘƙƚƛƜƝƞƟƠơƢƣƤƥƦƧƨƩƪƫƬƭƮƯưƱƲƳƴƵƶƷƸƹƺƻƼƽƾƿǂǍǎǏǐǑǒǓǔǕǖǗǘǙǚǛǜǝǞǟǠǡǢǣǤǥǦǧǨǩǪǫǬǭǮǯǰǴǵǶǷǸǹǺǻǼǽǾǿȀȁȂȃȄȅȆȇȈȉȊȋȌȍȎȏȐȑȒȓȔȕȖȗȘșȚțȜȝȞȟȠȡȢȣȤȥȦȧȨȩȪȫȬȭȮȯȰȱȲȳȴȵȶȸȹȺȻȼȽȾȿɀɁɂɃɄɅɆɇɈɉɊɋɌɍɎɏ"
			+ "ⱠⱡⱢⱣⱤⱥⱦⱧⱨⱩⱪⱫⱬⱭⱮⱯⱰⱱⱲⱳⱴⱵⱶⱷⱸⱹⱺⱻⱾⱿḂḃḄḅḆḇḈḉḊḋḌḍḎḏḐḑḒḓḔḕḖḗḘḙḚḛḜḝḞḟḠḡḢḣḤḥḦḧḨḩḪḫḬḭḮḯḰḱḲḳḴḵḶḷḸḹḺḻḼḽḾḿṀṁṂṃṄṅṆṇṈṉṊṋṌṍṎṏṐṑṒṓṔṕṖṗṘṙṚṛṜṝṞṟṠṡṢṣṤṥṦṧṨṩṪṫṬṭṮṯṰṱṲṳṴṵṶṷṸṹṺṻṼṽṾṿẀẁẂẃẄẅẆẇẈẉẊẋẌẍẎẏẐẑẒẓẔẕẖẗẘẙẚẛẜẝẞẟẠạẢảẤấẦầẨẩẪẫẬậẮắẰằẲẳẴẵẶặẸẹẺẻẼẽẾếỀềỂểỄễỆệỈỉỊịỌọỎỏỐốỒồỔổỖỗỘộỚớỜờỞởỠỡỢợỤụỦủỨứỪừỬửỮữỰựỲỳ"
			+ "ɐɑɒɓɔɕɖɗɘəɚɛɜɝɞɟɠɡɢɣɤɥɦɧɨɩɪɫɬɭɮɯɰɱɲɳɴɵɶɷɸɹɺɻɼɽɾɿʀʁʂʃʄʅʆʇʈʉʊʋʌʍʎʏʐʑʒʓʔʕʖʗʘʙʚʛʜʝʞʟʠʡʢʤʥʦʧʨʩʫʬʭʮʯ"
	};
	private int cipherKey;
	public Cipher(int cipherKey){
		//System.out.println(alphabet[12]);
		//System.out.println(shuffle(alphabet[12]));
		this.cipherKey=cipherKey;
		System.out.println("Alphabet: "+alphabet[cipherKey]);
	}
	public Cipher(String alpha){
		//System.out.println(alphabet[12]);
		//System.out.println(shuffle(alphabet[12]));
		this.cipherKey=0;
		alphabet[cipherKey]=alpha;
		System.out.println("Custom alphabet: "+alphabet[cipherKey]);
	}
	
	public void print(){
		for(int i=0;i<alphabet.length;i++){
			System.out.println(alphabet[i].length());
		}
	}
	public String shuffle(String input){
        ArrayList<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        //System.out.println(output.toString());
        return output.toString();
    }
	public String readFileAsString(String fileName) {
		try{
			String data = "";
			data = new String(Files.readAllBytes(Paths.get(fileName)));
			return data;
		}catch(IOException ioe){
			ioe.printStackTrace();
			return "There has been a problem.";
		}
	}
	public void writeUTF8(String filename, String in){
		try{
	    	File fileDir = new File(filename);
	    	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileDir), "UTF8"));
	    	out.append(in).append("\r\n");
	    	out.flush();
	    	out.close();
		} catch (UnsupportedEncodingException e){
	    	System.out.println(e.getMessage());
		} catch (IOException e){
	    	System.out.println(e.getMessage());
		} catch (Exception e){
	    	System.out.println(e.getMessage());
		}
	}
	public String readUTF8(String filename){
		String out="";
		try {
			File fileDir = new File(filename);
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
			String str;
			while ((str = in.readLine()) != null) {
				out+=str;
			}
			in.close();
		}catch (UnsupportedEncodingException e){
			System.out.println(e.getMessage());
		}catch (IOException e){
			System.out.println(e.getMessage());
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return out;
	}
	
	//unused methods
	/*
	private int $i(char c){
		return $i(c,cipherKey);
	}
	private char $c(int i){
		return $c(i,cipherKey);
	}
	private int $l(){
		return $l(cipherKey);
	}
	*/
	private int $i(char c,int d){
		try{
			for(int i=0;true;i++){
				if(c!=alphabet[d].charAt(i)){
					continue;
				}else{
					return i;
				}
			}
		}catch(StringIndexOutOfBoundsException ae){
			return -1;
		}
	}
	private char $c(int i,int d){
		try{
			return alphabet[d].charAt(i);
		}catch(StringIndexOutOfBoundsException ae){
			return '#';
		}
	}
	private int $l(int d){
		return alphabet[d].length();
	}
	public void printEngAlphaBet(){
		//basic latin
		for(int c=32;c<126+1;c++)
			System.out.print((char)c);
		System.out.println();
		//Latin-1 Supplement
		for(int c=160;c<255+1;c++)
			System.out.print((char)c);
		System.out.println();
		//Latin Extended-A
		for(int c=256;c<383+1;c++)
			System.out.print((char)c);
		System.out.println();
		//Latin Extended-B
		for(int c=384;c<591+1;c++)
			System.out.print((char)c);
		System.out.println();
		//Latin Extended-C
				for(int c=0x2C60;c<0x2C7F+1;c++)
					System.out.print((char)c);
				System.out.println();
		//Latin Extended-D
				for(int c=0xA720;c<0xA7FF+1;c++)
					System.out.print((char)c);
				System.out.println();
		//Latin Extended-E
				for(int c=0xAB30;c<0xAB6F+1;c++)
					System.out.print((char)c);
				System.out.println();
		//Latin Extended Additional
		for(int c=0x1E02;c<0x1EF3+1;c++)
			System.out.print((char)c);
		System.out.println();
		//IPA Extensions
				for(int c=0x0250;c<0x02AF+1;c++)
					System.out.print((char)c);
				System.out.println();
		//Phonetic Extensions
				for(int c=0x1D00;c<0x1D7F+1;c++)
					System.out.print((char)c);
				System.out.println();
		//Phonetic Extensions Supplement
				for(int c=0x1D80;c<0x1DBF+1;c++)
					System.out.print((char)c);
				System.out.println();
		//Cyrillic
				for(int c=0x0400;c<0x04FF+1;c++)
					System.out.print((char)c);
				System.out.println();
		//Cyrillic Supplement
				for(int c=0x0500;c<0x052F+1;c++)
					System.out.print((char)c);
				System.out.println();
		//Cyrillic Extension A
				for(int c=0x2DE0;c<0x2DFF+1;c++)
					System.out.print((char)c);
				System.out.println();
		//Cyrillic Extension B
				for(int c=0xA640;c<0xA69F+1;c++)
					System.out.print((char)c);
				System.out.println();
	}
	
	public String base64(String in, boolean enc){
		if(enc){
			return null;
		}else{
			return null;
		}
	}
	
	
	public String newKey(int l){
		int k=cipherKey;
		//System.out.println("Generating new key");
		//System.out.print("Key: ");
		String key="";
		for(int u=0;u<l;u++){
			char c=$c((int)(Math.random()*$l(k)),k);
			//System.out.print((u%150!=0?"":"\n")+c);
			key+=c;
		}
		//System.out.println("\n");
		return key;
	}
	public String DvorakCipher(String in, boolean enc){
		System.out.println("QwertyDvorak\ninput: "+in+"\nencoding: "+enc);
		String out="",q="][abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ;',./_+{}:\"<>?=-",d="=/axje.uidchtnmbrl'poygk,qf;AXJE>UIDCHTNMBRL\"POYGK<QF:s-wvz{}?+S_WVZ][";
		for(int i=0;i<in.length();i++){
			int u=(enc?q:d).indexOf(in.charAt(i));
			//System.out.println(u+" "+in.charAt(i));
			out+=(u<0)?in.charAt(i):(enc?d:q).charAt(u);
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	public String ROT13(String in){
		System.out.println("ROT13\ninput: "+in);
		String out="";
		for(int i=0;i<in.length();i++){
			char c=in.charAt(i);
			if(Character.isLetter(in.charAt(i))&&Character.isUpperCase(in.charAt(i))){
				c=Character.toLowerCase(c);
			}
			int u=alphabet[0].indexOf(c);
			char r=(u!=-1)?alphabet[0].charAt((u+13)%26):c;
			if(Character.isLetter(in.charAt(i))&&Character.isUpperCase(in.charAt(i))){
				r=Character.toUpperCase(r);
			}
			out+=r;
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	public String ROT18(String in){
		System.out.println("ROT18\ninput: "+in);
		String out="";
		for(int i=0;i<in.length();i++){
			char c=in.charAt(i);
			if(Character.isLetter(in.charAt(i))&&Character.isUpperCase(in.charAt(i))){
				c=Character.toLowerCase(c);
			}
			int u=alphabet[1].indexOf(c);
			char r=(u!=-1)?alphabet[1].charAt((u+18)%36):c;
			if(Character.isLetter(in.charAt(i))&&Character.isUpperCase(in.charAt(i))){
				r=Character.toUpperCase(r);
			}
			out+=r;
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	public String Caeser26(String in, int dx){
		System.out.println("Ceaser26\nalphabet: "+alphabet[0]+"\nshift: "+dx+"\ninput: "+in);
		String out="";
		for(int i=0;i<in.length();i++){
			char c=in.charAt(i);
			if(Character.isLetter(in.charAt(i))&&Character.isUpperCase(in.charAt(i))){
				c=Character.toLowerCase(c);
			}
			int u=alphabet[0].indexOf(c);
			char r=(u!=-1)?alphabet[0].charAt((u+dx)%26):c;
			if(Character.isLetter(in.charAt(i))&&Character.isUpperCase(in.charAt(i))){
				r=Character.toUpperCase(r);
			}
			out+=r;
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	public String CaeserCipher(String in, int dx){
		int k=cipherKey;
		System.out.println("Ceaser"+$l(k)+"\nalphabet: "+alphabet[k]+"\nshift: "+dx+"\ninput: "+in);
		String out="";
		for(int i=0;i<in.length();i++){
			int u=($i(in.charAt(i),k)+dx)%$l(k);
			out+=$i(in.charAt(i),k)!=-1?$c(u<0?u+$l(k):u,k):in.charAt(i);
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	public String AffineCipher(String in, int m, int dx, boolean enc){
		int k=cipherKey;
		System.out.println("Affine"+$l(k)+"\nencoding: "+enc+"\nalphabet: "+alphabet[k]+"\nmod: "+m+"\nshift: "+dx+"\ninput: "+in);
		String out="";
		for(int i=0;i<in.length();i++){
			int u=(enc?((m*$i(in.charAt(i),k))+dx):(($l(k)-m)*($i(in.charAt(i),k)-dx)))%$l(k);
			out+=$i(in.charAt(i),k)!=-1?$c(u<0?u+$l(k):u,k):in.charAt(i);
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	public String TrithemiusCipher(String in, int dx, boolean enc){
		int k=cipherKey;
		System.out.println("Trithemius"+$l(k)+"\nencoding: "+enc+"\nalphabet: "+alphabet[k]+"\nshift: "+dx+"\ninput: "+in);
		String out="";
		for(int i=0;i<in.length();i++){
			int u=($i(in.charAt(i),k)+(enc?dx+i:-dx-i))%$l(k);
			out+=$i(in.charAt(i),k)!=-1?$c(u<0?u+$l(k):u,k):in.charAt(i);
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	public String VigenereCipher(String in, String key, boolean enc){
		int k=cipherKey;
		System.out.println("Vigenere"+$l(k)+"\nencoding: "+enc+"\nalphabet: "+alphabet[k]+"\nkey: "+key+"\ninput: "+in);
		String out="";
		for(int i=0;i<in.length();i++){
			int u=($i(in.charAt(i),k)+(enc?$i(key.charAt(i%key.length()),k):-$i(key.charAt(i%key.length()),k)))%$l(k);
			out+=$i(in.charAt(i),k)!=-1?$c(u<0?u+$l(k):u,k):in.charAt(i);
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	public String AutokeyCipher(String in, String key, boolean enc){
		int k=cipherKey;
		System.out.println("Autokey"+$l(k)+"\nencoding: "+enc+"\nalphabet: "+alphabet[k]+"\nkey: "+key+"\ninput: "+in);
		String out="";
		for(int i=0;i<in.length();i++){
			int u;
			if(i<key.length())
				u=($i(in.charAt(i),k)+(enc?$i(key.charAt(i),k):-$i(key.charAt(i),k)))%$l(k);
			else
				u=($i(in.charAt(i),k)+(enc?$i(in.charAt(i-key.length()),k):-$i(out.charAt(i-key.length()),k)))%$l(k);
			out+=$i(in.charAt(i),k)!=-1?$c(u<0?u+$l(k):u,k):in.charAt(i);
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	
	private ArrayList<Character> stringToList(String in){
		ArrayList<Character> c=new ArrayList<Character>();
		for(int i=0;i<in.length();i++){
			c.add(in.charAt(i));
		}
		return c;
	}
	public void printWheels(ArrayList<Character> inl, ArrayList<Character> inr){
		for(int i=0;i<inl.size();i++){
			System.out.print(inl.get((i)%inl.size()));
		}
		System.out.print(" ");
		for(int i=0;i<inr.size();i++){
			System.out.print(inr.get((i)%inr.size()));
		}
		System.out.print("\n");
	}
	public String newAlphaSet(){
		int k=cipherKey;
		return shuffle(alphabet[k]);
	}
	public String ChaoCipher(String in, String left, String right, boolean enc){
		int k=cipherKey;
		System.out.println("Chaocipher"+$l(k)+"\nencoding: "+enc+"\nalphabet: "+alphabet[k]+"\nleft: "+left+"\nright: "+right+"\ninput: "+in);
		//System.out.println("iterations:");
		String out="";
		ArrayList<Character> l=stringToList(left),r=stringToList(right);
		//printWheels(l,r);
		for(int i=0;i<in.length();i++){
			int n=(enc?r:l).indexOf(in.charAt(i));
			if(n!=-1){
				for(int u=0;u<n;u++){
					l.add(l.remove(0));
					r.add(r.remove(0));
				}
				out+=(enc?l:r).get(0);
				r.add(r.size()-1,r.remove(0));
				l.add(l.size()/2,l.remove(1));
				r.add(r.size()/2,r.remove(2));
			}else{
				out+=in.charAt(i);
			}
			//printWheels(l,r);
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	public String NullBlockCipher(String in){
		int k=cipherKey;
		System.out.println("NullBlockCipher"+$l(k)+"\ninput: "+in+"\nciphertext: ");
		String out="",next="";
		ArrayList<Character> list=stringToList(newAlphaSet());
		for(int i=0;i<=in.length();i++){
			if(!(i==in.length()))
				if($i(in.charAt(i),k)==-1&&!(in.charAt(i)==' ')){
					continue;
				}
			if(i==in.length()||next.contains(in.charAt(i)+"")||in.charAt(i)==' '){
				//System.out.println(next);
				for(int u=0;u<next.length();u++){
					list.add(list.get((int)(Math.random()*list.size())));
				}
				for(char c:list)
					out+=c;
				out+="\n";
				//printWheels(list,new ArrayList<Character>());
				next="";
				list=stringToList(newAlphaSet());
				if(!(i==in.length())&&in.charAt(i)==' '){
					//System.out.println("[SPACE]");
					for(char c:list)
						out+=c;
					out+="\n";
					//printWheels(list,new ArrayList<Character>());
					list=stringToList(newAlphaSet());
				}
				if(!(i==in.length())&&!(in.charAt(i)==' '))
					i--;
			}else{
				next+=list.remove(list.indexOf(in.charAt(i)));
			}
		}
		System.out.println(out+"\n");
		return out;
	}
	public String RailsCipher(String in, int r){
		System.out.print("RailsCipher"+r+"\ninput: "+in+"\nciphertext: ");
		String[] rails=new String[r];
		for(int u=0;u<r;u++)
			rails[u]="";
		String out="";
		int i=0,pos=0;
		boolean up=false;
		while(i<in.length()){
			rails[pos]+=in.charAt(i);
			//System.out.println(in.charAt(i)+" "+pos);
			if(!up){
				pos++;
			}else{
				pos--;
			}
			if(pos<0||pos>(r-1)){
				if(up){
					up=false;
					pos+=2;
				}else{
					up=true;
					pos-=2;
				}
			}
			i++;
		}
		for(int u=0;u<r;u++)
			out+=rails[u];
		System.out.println(out+"\n");
		return out;
	}
	public String newSquare(){
		return shuffle("WERTYUIOPASDFGHJKLZXCVBNM");
	}
	public String newADFGVX(){
		return shuffle("QWERTYUIOPASDFGHJKLZXCVBNM0123456789");
	}
	public String FourSquareCipher(String in, String left, String right, boolean enc){
		System.out.println("FourSquareCipher\nencoding: "+enc+"\nleft: "+left+"\nright: "+right+"\ninput: "+in);
		String out="";
		if(in.length()%2!=0)
			in+="A";
		char[][] l=new char[5][5], r=new char[5][5], 
				d={
				{'A','B','C','D','E'},
				{'F','G','H','I','J'},
				{'K','L','M','N','O'},
				{'P','R','S','T','U'},
				{'V','W','X','Y','Z'}};
		int u=0;
		for(int y=0;y<5;y++){
			for(int x=0;x<5;x++){
				l[y][x]=left.charAt(u);
				r[y][x]=right.charAt(u);
				u++;
			}
		}
		if(enc){
			for(int i=0;i<in.length()-1;i+=2){
				char a=in.charAt(i),b=in.charAt(i+1);
				int ax=-1,ay=-1,bx=-1,by=-1;
				for(int y=0;y<5;y++){
					for(int x=0;x<5;x++){
						if(d[y][x]==a){
							ay=y;
							ax=x;
						}
						if(d[y][x]==b){
							by=y;
							bx=x;
						}
					}
				}
				if(ax!=-1&&ay!=-1&&bx!=-1&&by!=-1){
					out+=r[ay][bx]+""+l[by][ax]+"";
				}
			}
		}else{
			for(int i=0;i<in.length()-1;i+=2){
				char a=in.charAt(i),b=in.charAt(i+1);
				int ax=-1,ay=-1,bx=-1,by=-1;
				for(int y=0;y<5;y++){
					for(int x=0;x<5;x++){
						if(r[y][x]==a){
							ay=y;
							ax=x;
						}
						if(l[y][x]==b){
							by=y;
							bx=x;
						}
					}
				}
				if(ax!=-1&&ay!=-1&&bx!=-1&&by!=-1){
					out+=d[ay][bx]+""+d[by][ax]+"";
				}
			}
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	
	public String NihilistCipher(String in, String set, String key, boolean enc){
		System.out.println("NihilistCipher\nencoding: "+enc+"\nset: "+set+"\nkey: "+key+"\ninput: "+in);
		String out="";
		char[][] s=new char[5][5];
		int u=0;
		for(int y=0;y<5;y++){
			for(int x=0;x<5;x++){
				s[y][x]=set.charAt(u);
				u++;
			}
		}
		if(enc){
			for(int i=0;i<in.length();i++){
				char a=in.charAt(i),b=key.charAt(i%key.length());
				int ax=-1,ay=-1,bx=-1,by=-1;
				for(int y=0;y<5;y++){
					for(int x=0;x<5;x++){
						if(s[y][x]==a){
							ay=y+1;
							ax=x+1;
						}
						if(s[y][x]==b){
							by=y+1;
							bx=x+1;
						}
					}
				}
				if(ax!=-1&&ay!=-1&&bx!=-1&&by!=-1){
					out+=(((ay+by)*10)+(ax+bx))+" ";
				}else{
					out+=(int)((Math.random()*20)+110)+" ";
				}
			}
		}else{
			String[] ct=in.split(" ");
			for(int i=0;i<ct.length;i++){
				int c=Integer.parseInt(ct[i]);
				if(c>110){
					out+="*";
					continue;
				}
				char b=key.charAt(i%key.length());
				int bx=-1,by=-1;
				for(int y=0;y<5;y++){
					for(int x=0;x<5;x++){
						if(s[y][x]==b){
							by=y+1;
							bx=x+1;
						}
					}
				}
				if(bx!=-1&&by!=-1){
					out+=s[(c/10)-by-1][(c%10)-bx-1];
				}else{
					out+="*";
				}
			}
			
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	public String ADFGVXCipher(String in, String set, String key, boolean enc){
		System.out.println("ADFGVXCipher\nencoding: "+enc+"\nset: "+set+"\nkey: "+key+"\ninput: "+in);
		String out="";
		char[][] cset=new char[6][6];
		String[] k=new String[key.length()];
		String d="ADFGVX";
		for(int i=0;i<key.length();i++){
			k[i]=key.charAt(i)+"";
		}
		int u=0;
		for(int y=0;y<6;y++){
			for(int x=0;x<6;x++){
				cset[y][x]=set.charAt(u);
				u++;
			}
		}
		if(enc){
			int ik=0;
			for(int i=0;i<in.length();i++){
				char c=in.charAt(i);
				for(int y=0;y<6;y++){
					for(int x=0;x<6;x++){
						if(cset[y][x]==c){
							k[ik%k.length]+=d.charAt(y);
							ik++;
							k[ik%k.length]+=d.charAt(x);
							ik++;
							//System.out.print(d.charAt(y)+""+d.charAt(x)+" ");
						}
					}
				}
			}
			//System.out.print("\n");
			//for(int i=0;i<key.length();i++){
			//	System.out.println(k[i]);
			//}
			//System.out.println();
			Arrays.sort(k);
			for(int i=0;i<key.length();i++){
				//System.out.println(k[i]);
				out+=k[i].substring(1)+" ";
			}
		}else{
			Arrays.sort(k);
			String[] cin=in.split(" "),nk=new String[k.length];
			//System.out.println(cin.length);
			for(int i=0;i<key.length();i++){
				for(int q=0;q<key.length();q++){
					if(k[q].charAt(0)==key.charAt(i))
						nk[i]=cin[q];
				}
			}
			//for(int i=0;i<key.length();i++){
			//	System.out.println(nk[i]);
			//}
			int l=-1;
			String re="";
			for(int i=0;i<nk.length;i++){
				if(nk[i].length()>l)
					l=nk[i].length();
			}
			for(int y=0;y<l;y++){
				for(int x=0;x<nk.length;x++){
					try{
						re+=nk[x].charAt(y);
					}catch(ArrayIndexOutOfBoundsException aie){
						//aie.printStackTrace();
					}catch(StringIndexOutOfBoundsException aie){
						//aie.printStackTrace();
					}
				}
			}
			//System.out.println(re);
			for(int i=0;i<re.length()-1;i+=2){
				out+=cset[d.indexOf(re.charAt(i))][d.indexOf(re.charAt(i+1))];
			}
			
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	
	public String VICTORCipher(String in, String set, String key, boolean enc){
		System.out.println("VICTORCipher\nencoding: "+enc+"\nset: "+set+"\nkey: "+key+"\ninput: "+in);
		String out="";
		char s[][]=new char[3][10];
		String dy=" ";
		int u=0;
		for(int y=0;y<3;y++){
			for(int x=0;x<10;x++){
				s[y][x]=set.charAt(u);
				//System.out.print(set.charAt(u));
				if(set.charAt(u)==' '){
					dy+=u+"";
				}
				u++;
			}
			//System.out.println();
		}
		//System.out.println(dy);
		if(enc){
			String ci="";
			for(int i=0;i<in.length();i++){
				char c=in.charAt(i);
				for(int y=0;y<3;y++){
					for(int x=0;x<10;x++){
						if(s[y][x]==c){
							ci+=((y>0)?dy.charAt(y)+"":"")+(x+"");
							//System.out.print(d.charAt(y)+""+d.charAt(x)+" ");
						}
					}
				}
			}
			for(int i=0;i<ci.length();i++){
				int q=Integer.parseInt(ci.charAt(i)+"")+Integer.parseInt(key.charAt(i%key.length())+"");
				out+=(q%10)+"";
			}
		}else{
			String ci="";
			for(int i=0;i<in.length();i++){
				int q=Integer.parseInt(in.charAt(i)+"")-Integer.parseInt(key.charAt(i%key.length())+"");
				ci+=(q<0)?(q+10)+"":q+"";
			}
			//System.out.println(ci);
			for(int i=0;i<ci.length();i++){
				if(ci.charAt(i)==dy.charAt(1)){
					out+=s[1][Integer.parseInt(ci.charAt(i+1)+"")];
					i++;
				}else if(ci.charAt(i)==dy.charAt(2)){
					out+=s[2][Integer.parseInt(ci.charAt(i+1)+"")];
					i++;
				}else{
					out+=s[0][Integer.parseInt(ci.charAt(i)+"")];
				}
			}
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	
	
	public String newHexCipherSet(){
		return shuffle("    etaonrisTAIH")+shuffle("0123456789bcdfghjklmpquvwxyzBCDEFGJKLMNOPQRSUVWXYZ$,.?:;-+~! /^&");
	}
	public String HexCipher(String in, String set, String key, boolean enc){
		System.out.println("HexCipher\nencoding: "+enc+"\nset: "+set+"\nkey: "+key+"\ninput: "+in);
		String out="";
		String r="0123456789ABCDEF";
		char s[][]=new char[5][16];
		String dy=" ";
		int u=0;
		for(int y=0;y<5;y++){
			for(int x=0;x<16;x++){
				s[y][x]=set.charAt(u);
				//System.out.print(set.charAt(u));
				if(y==0&&set.charAt(u)==' '){
					dy+=r.charAt(u)+"";
				}
				u++;
			}
			//System.out.println();
		}
		//System.out.println(dy);
		if(enc){
			String ci="";
			for(int i=0;i<in.length();i++){
				char c=in.charAt(i);
				for(int y=0;y<5;y++){
					for(int x=0;x<16;x++){
						if(s[y][x]==c){
							if(c!=' '||y!=0){
								ci+=((y>0)?dy.charAt(y)+"":"")+(r.charAt(x));
								//System.out.println(c+" "+((y>0)?dy.charAt(y)+"":"")+(r.charAt(x)));
							}
						}
					}
				}
			}
			for(int i=0;i<ci.length();i++){
				int q=r.indexOf(ci.charAt(i))+(r.indexOf(key.charAt(i%key.length())));
				out+=r.charAt(q%16)+"";
			}
			//out.toUpperCase();
		}else{
			String ci="";
			for(int i=0;i<in.length();i++){
				int q=r.indexOf(in.charAt(i))-r.indexOf(key.charAt(i%key.length()));
				ci+=(q<0)?r.charAt(q+16):r.charAt(q);
			}
			//System.out.println(ci);
			for(int i=0;i<ci.length();i++){
				if(ci.charAt(i)==dy.charAt(1)){
					out+=s[1][r.indexOf(ci.charAt(i+1))];
					i++;
				}else if(ci.charAt(i)==dy.charAt(2)){
					out+=s[2][r.indexOf(ci.charAt(i+1))];
					i++;
				}else if(ci.charAt(i)==dy.charAt(3)){
					out+=s[3][r.indexOf(ci.charAt(i+1))];
					i++;
				}else if(ci.charAt(i)==dy.charAt(4)){
					out+=s[4][r.indexOf(ci.charAt(i+1))];
					i++;
				}else{
					out+=s[0][r.indexOf(ci.charAt(i))];
				}
			}
		}
		System.out.println("ciphertext: "+out+"\n");
		return out;
	}
	
	public void assertCipher(String o, String c){
		if(c.equals(o))
			System.out.println("assertCipher(): Strings match.\n");
		else{
			System.out.println("assertCipher(): Error. Strings do not match.\n");
			System.err.println("assertCipher(): Error. Strings do not match.\n");
		}
	}
	public void test(){
		///*
		String orig="May Thy Flesh Be Consumed.";
		assertCipher(orig, DvorakCipher(DvorakCipher(orig,true),false));
		assertCipher(orig, ROT13(ROT13(orig)));
		assertCipher(orig, ROT18(ROT18(orig)));
		assertCipher(orig, Caeser26(Caeser26(orig, 13), 13));
		//assertCipher(orig, CaeserCipher(CaeserCipher(orig,54,9),54,9));
		//assertCipher("maythyfleshbeconsumed", AffineCipher(AffineCipher("maythyfleshbeconsumed",5,8,0,true),5,8,0,false));
		//assertCipher(orig, TrithemiusCipher(TrithemiusCipher(orig,0,9,true),0,9,false));
		
		//int u=12;
		assertCipher(orig, CaeserCipher(CaeserCipher(orig,423),423));
		String key=newKey(5);
		assertCipher(orig, VigenereCipher(VigenereCipher(orig,key,true),key,false));
		assertCipher(orig, AutokeyCipher(AutokeyCipher(orig,key,true),key,false));
		
		//int v=3;
		String l=newAlphaSet(),r=newAlphaSet();
		assertCipher(orig, ChaoCipher(ChaoCipher(orig,l,r,true),l,r,false));
		//*/
		String n="THISSHALLBEOURLASTCRYBEFOREETERNALSILENCEMAYTHYFLESHBECONSUMEDGODSPEED",c=newSquare(),d=newSquare();
		NullBlockCipher("This Shall be our Last Cry Before Eternal Silence May thy Flesh and Bones be Consumed Godspeed");
		RailsCipher(n,3);
		assertCipher(n,FourSquareCipher(FourSquareCipher(n,c,d,true),c,d,false));
		assertCipher("DYNAMITEWINTERPALACEBASEMENT",NihilistCipher(NihilistCipher("DYNAMITEWINTERPALACEBASEMENT","ZEBRASCDFGHIKLMNOPQTUVWXY","RUSSIAN",true),"ZEBRASCDFGHIKLMNOPQTUVWXY","RUSSIAN",false));
		assertCipher("ATTACKAT1200AMWESTBEACH",ADFGVXCipher(ADFGVXCipher("ATTACKAT1200AMWESTBEACH", "NA1C3H8TB2OME5WRPD4F6G7I9J0KLQSUVXYZ", "PRIVACY", true), "NA1C3H8TB2OME5WRPD4F6G7I9J0KLQSUVXYZ", "PRIVACY", false));
		//"NA1C3H8TB2OME5WRPD4F6G7I9J0KLQSUVXYZ"
		assertCipher("ATTACKATDAWNONATTHEBEACH",VICTORCipher(VICTORCipher("ATTACKATDAWNONATTHEBEACH", "ET AON RISBCDFGHJKLMPQ/UVWXYZ.", "0452", true), "ET AON RISBCDFGHJKLMPQ/UVWXYZ.", "0452", false));
		String k=newHexCipherSet();//,a=newAlphaSet(),b=newAlphaSet();
		
		String out=HexCipher("May They Flesh And Bones Be Consumed.", k, "0147A9B01266F", true);
		out=HexCipher(out, k, "0147A9B01266F", false);
	}
	
	//method that ciphers a whole page of text
	//text will be taken in in blocks of n length at a time
	//cipher text will be made up up multiple ciphers
	//plain text of the previous ciphers will be used for the keys for the next block
	//variations of the null, adfgvx and vic cipher can/will be used for this
	public String NBlockHexChaocipher(String in, int n){
		return null;
	}
	
	public static void main(String[] args){
		Cipher ci=new Cipher("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ,<.>?:;\'\"-_+`~!$%^&*");
		ci.test();
		//String k=ci.newHexCipherSet();
		//System.out.println(k+" "+k.length());
		//String out=ci.HexCipher("May They Flesh And Bones Be Consumed.", k, "0147A9B01266F", true),a=ci.newAlphaSet(12),b=ci.newAlphaSet(12);
		//out=ci.ChaoCipher(out, a, b, 12, true);
		//out=ci.ChaoCipher(out, a, b, 12, false);
		//out=ci.HexCipher(out, k, "0147A9B01266F", false);
		//String u=ci.NBlockHexChaocipher("May They Flesh And Bones Be Consumed.May They Flesh And Bones Be Consumed.May They Flesh And Bones Be Consumed.May They Flesh And Bones Be Consumed.May They Flesh And Bones Be Consumed.May They Flesh And Bones Be Consumed.May They Flesh And Bones Be Consumed.May They Flesh And Bones Be Consumed.May They Flesh And Bones Be Consumed.May They Flesh And Bones Be Consumed.May They Flesh And Bones Be Consumed.",50);
		//System.out.println(u);
		//ci.HexCipher("", k, "0147", false);
		//ci.VICTORCipher("HAVEYOUEVERDANCEDWITHTHEDEVILINTHEPALEMOONLIGHTIAMANAGENTOFCHAOSYETMYDUTIESARELARGELYCEREMONIAL", "ET AON RISBCDFGHJKLMPQ/UVWXYZ.", "1337", true);
		
		
		
	}

}
