package ciphers;

public class RusToRomanjiDemo {

	public RusToRomanjiDemo() {
		// TODO Auto-generated constructor stub
	}
	
	public static String padString(String orig, String in){
		while(orig.length()<in.length()){
			orig+="-";
		}
		return orig;
	}
	public static void demo(String in){
		String ra="абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
		String rA="АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
		String[] equ={"a","b","v","g","d","ye","yo","zh","ze","i","y","k","l","m","n","o","p","r","s",
				"t","oo","f","kh","ts","ch","sh","sch","_","eh","^","eh","yu","ya"};
		for(String s:in.split("\n")){
			String a="",b="";
			for(char c:s.toCharArray()){
				if(ra.indexOf(c)>-1){
					b+=equ[ra.indexOf(c)]+"";
					a+=padString(c+"",equ[ra.indexOf(c)]+"");
				}else if(rA.indexOf(c)>-1){
					b+=equ[rA.indexOf(c)]+"";
					a+=padString(c+"",equ[rA.indexOf(c)]+"");
				}else{
					a+=c+"";
					b+=c+"";
				}
			}
			System.out.println(a+"\n"+b);
		}
		
	}
	public static void main(String[] args){
		demo("Теплое место, но улицы ждут\nОтпечатков наших ног. \nЗвездная пыль - на сапогах. \nМягкое кресло, клетчатый плед, \nНе нажатый вовремя курок. \nСолнечный день - в ослепительных снах. \n\nГруппа крови - на рукаве, \nМой порядковый номер - на рукаве, \nПожелай мне удачи в бою, пожелай мне: \nНе остаться в этой траве, \nНе остаться в этой траве. \nПожелай мне удачи, пожелай мне удачи! \n\nИ есть чем платить, но я не хочу \nПобеды любой ценой. \nЯ никому не хочу ставить ногу на грудь. \nЯ хотел бы остаться с тобой, \nПросто остаться с тобой, \nНо высокая в небе звезда зовет меня в путь.");
	}

}
