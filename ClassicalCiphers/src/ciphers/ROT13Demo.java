package ciphers;

public class ROT13Demo {
	
	//hello world
	public static String ROT13(String input){
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		String output = "";
		for (int i = 0; i < input.length(); i++) {
			char plaintext = input.charAt(i);
			int index = alpha.indexOf(plaintext+"");
			if(index == -1){
				output += plaintext+"";
			} else {
				int newIndex = (index + 13) % 26;
				output += alpha.charAt(newIndex);
			}
		}
		return output;
	}
	
	public static String GandalfTongue(String input){
		String vowels = "aieyou";
		String cst = "bkxznhdcwgpvjqtsrlmf";
		String output = "";
		for (int i = 0; i < input.length(); i++) {
			char plaintext = input.charAt(i);
			boolean isUppercase = Character.isUpperCase(plaintext);
			int index = vowels.indexOf((plaintext+"").toLowerCase());
			int newIndex = 0;
			if(index == -1){
				//if it is not a vowel
				index = cst.indexOf((plaintext+"").toLowerCase());
				if(index == -1){
					//it is not a vowel or a cst
					output += plaintext+"";
				} else {
					//it is a cst
					newIndex = (index + 10) % 20;
					String newChar = cst.charAt(newIndex)+"";
					if(isUppercase){
						newChar = newChar.toUpperCase();
					}
					output += newChar;
				}
			} else {
				//it is a vowel
				newIndex = (index + 3) % 6;
				String newChar = vowels.charAt(newIndex)+"";
				if(isUppercase){
					newChar = newChar.toUpperCase();
				}
				output += newChar;
			}
		}
		return output;
	}
	
	

	public static void main(String[] args) {
		String msg = "because Ms. Wetterer is going to have a baby too!";
		
		msg = GandalfTongue(msg);
		System.out.println(msg);
		
		msg = GandalfTongue(msg);
		System.out.println(msg);

	}

}
