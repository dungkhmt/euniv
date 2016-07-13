package vn.webapp.libraries;

public class Money2StringConvertor {

	public static String convert(char c){
		String s = "";
		if(c == '0') s = "không";
		else if(c == '1') s = "một";
		else if(c == '2') s = "hai";
		else if(c == '3') s = "ba";
		else if(c == '4') s = "bốn";
		else if(c == '5') s = "năm";
		else if(c == '6') s = "sáu";
		else if(c == '7') s = "bảy";
		else if(c == '8') s = "tám";
		else if(c == '9') s = "chín";
		return s;
	}
	public static String convertUpcase(char c){
		String s = "";
		if(c == '0') s = "Không";
		else if(c == '1') s = "Một";
		else if(c == '2') s = "Hai";
		else if(c == '3') s = "Ba";
		else if(c == '4') s = "Bốn";
		else if(c == '5') s = "Năm";
		else if(c == '6') s = "Sáu";
		else if(c == '7') s = "Bảy";
		else if(c == '8') s = "Tám";
		else if(c == '9') s = "Chín";
		return s;
	}
	public static String unit(int len){
		return "";
	}
	public static String convert2Text(String money){
		String s = "";
		if(money.length() == 1){
			return convert(money.charAt(0));
		}else if(money.length() == 2){
			if(money.equals("10")) return "mười";
			String st = convert(money.charAt(0)) + " mươi ";
			if(money.charAt(1) == '5') st += "lăm"; 
			else if(money.charAt(1) != '0') st += convert(money.charAt(1));
			return st;
		}else if(money.length() == 3){
			if(money.equals("000")) return "";
			String st = convert(money.charAt(0)) + " trăm ";
			String s1 = money.substring(1,money.length());
			if(s1.equals("00")) return st;
			if(s1.equals("10")) return st + " mười ";
			if(s1.charAt(0) == '1') return st + " mười " + convert(s1.charAt(1));
			if(money.charAt(1) == '0') st += " linh ";
			else
			st += convert(money.charAt(1)) + " mươi ";
			
			if(money.charAt(2) == '5'){
				if(money.charAt(1) == '0') st += "năm"; else st += "lăm"; 
			}
			else if(money.charAt(2) != '0') st += convert(money.charAt(2));
			return st;
		}else if(money.length() == 4){
			
			String st = convert(money.charAt(0)) + " nghìn ";
			if(money.charAt(1) != '0'){
				st += convert2Text(money.substring(1,money.length()));
				return st;
			}else{// không trăm
				if(money.charAt(2) != '0'){
					st += " không trăm " + convert2Text(money.substring(2,money.length()));
					return st;
				}else{
					// không trăm không chuc
					if(money.charAt(3) != '0'){
						st += " linh ";
						st += convert(money.charAt(3));
						return st;
					}else{
						return st;
					}
				}
			}
				
			
			
		}else if(money.length() == 5){
			String s1 = money.substring(0,2);
			String s2 = money.substring(2,money.length());
			System.out.println(s1 + " -- " + s2);
			return convert2Text(s1) + " nghìn " + convert2Text(s2); 
		}else if(money.length() == 6){
			String s1 = money.substring(0,3);
			String s2 = money.substring(3,money.length());
			System.out.println(s1 + " -- " + s2);
			if(s1.equals("000")) return convert2Text(s2);
			return convert2Text(s1) + " nghìn " + convert2Text(s2);
		}else if(money.length() == 7){
			String s1 = money.substring(0,1);
			String s2 = money.substring(1,money.length());
			System.out.println(s1 + " -- " + s2);
			return convert2Text(s1) + " triệu " + convert2Text(s2);
		}else if(money.length() == 8){
			String s1 = money.substring(0,2);
			String s2 = money.substring(2,money.length());
			System.out.println(s1 + " -- " + s2);
			return convert2Text(s1) + " triệu " + convert2Text(s2);
		}else if(money.length() == 9){
			String s1 = money.substring(0,3);
			String s2 = money.substring(3,money.length());
			System.out.println(s1 + " -- " + s2);
			return convert2Text(s1) + " triệu " + convert2Text(s2);
		}else if(money.length() == 10){
			String s1 = money.substring(0,1);
			String s2 = money.substring(1,money.length());
			
			System.out.println(s1 + " -- " + s2);
			String st = convert2Text(s1) + " tỉ ";
			if(s2.equals("000000000")) return st; else return st + convert2Text(s2);
		}else if(money.length() == 11){
			String s1 = money.substring(0,2);
			String s2 = money.substring(2,money.length());
			String st = convert2Text(s1) + " tỉ ";
			if(s2.equals("000000000")) return st; else return st + convert2Text(s2);
		}else if(money.length() == 12){
			String s1 = money.substring(0,3);
			String s2 = money.substring(3,money.length());
			String st = convert2Text(s1) + " tỉ ";
			if(s2.equals("000000000")) return st; else return st + convert2Text(s2);
		}else if(money.length() == 13){
			String s1 = money.substring(0,4);
			String s2 = money.substring(4,money.length());
			String st = convert2Text(s1) + " tỉ ";
			if(s2.equals("000000000")) return st; else return st + convert2Text(s2);
		}
		return s;
	}
	
	public static String convert2TextStartUpcase(String money){
		String s = "";
		if(money.length() == 1){
			return convert(money.charAt(0));
		}else if(money.length() == 2){
			if(money.equals("10")) return "Mười";
			String st = convertUpcase(money.charAt(0)) + " mươi ";
			if(money.charAt(1) == '5') st += "lăm"; 
			else if(money.charAt(1) != '0') st += convert(money.charAt(1));
			return st;
		}else if(money.length() == 3){
			if(money.equals("000")) return "";
			String st = convertUpcase(money.charAt(0)) + " trăm ";
			String s1 = money.substring(1,money.length());
			if(s1.equals("00")) return st;
			if(s1.equals("10")) return st + " mười ";
			if(s1.charAt(0) == '1') return st + " mười " + convert(s1.charAt(1));
			if(money.charAt(1) == '0') st += " linh ";
			else
			st += convert(money.charAt(1)) + " mươi ";
			
			if(money.charAt(2) == '5'){
				if(money.charAt(1) == '0') st += "năm"; else st += "lăm"; 
			}
			else if(money.charAt(2) != '0') st += convert(money.charAt(2));
			return st;
		}else if(money.length() == 4){
			
			String st = convertUpcase(money.charAt(0)) + " nghìn ";
			if(money.charAt(1) != '0'){
				st += convert2Text(money.substring(1,money.length()));
				return st;
			}else{// không trăm
				if(money.charAt(2) != '0'){
					st += " không trăm " + convert2Text(money.substring(2,money.length()));
					return st;
				}else{
					// không trăm không chuc
					if(money.charAt(3) != '0'){
						st += " linh ";
						st += convert(money.charAt(3));
						return st;
					}else{
						return st;
					}
				}
			}
				
			
			
		}else if(money.length() == 5){
			String s1 = money.substring(0,2);
			String s2 = money.substring(2,money.length());
			System.out.println(s1 + " -- " + s2);
			return convert2TextStartUpcase(s1) + " nghìn " + convert2Text(s2); 
		}else if(money.length() == 6){
			String s1 = money.substring(0,3);
			String s2 = money.substring(3,money.length());
			System.out.println(s1 + " -- " + s2);
			if(s1.equals("000")) return convert2TextStartUpcase(s2);
			return convert2TextStartUpcase(s1) + " nghìn " + convert2Text(s2);
		}else if(money.length() == 7){
			String s1 = money.substring(0,1);
			String s2 = money.substring(1,money.length());
			System.out.println(s1 + " -- " + s2);
			return convert2TextStartUpcase(s1) + " triệu " + convert2Text(s2);
		}else if(money.length() == 8){
			String s1 = money.substring(0,2);
			String s2 = money.substring(2,money.length());
			System.out.println(s1 + " -- " + s2);
			return convert2TextStartUpcase(s1) + " triệu " + convert2Text(s2);
		}else if(money.length() == 9){
			String s1 = money.substring(0,3);
			String s2 = money.substring(3,money.length());
			System.out.println(s1 + " -- " + s2);
			return convert2TextStartUpcase(s1) + " triệu " + convert2Text(s2);
		}else if(money.length() == 10){
			String s1 = money.substring(0,1);
			String s2 = money.substring(1,money.length());
			
			System.out.println(s1 + " -- " + s2);
			String st = convert2TextStartUpcase(s1) + " tỉ ";
			if(s2.equals("000000000")) return st; else return st + convert2Text(s2);
		}else if(money.length() == 11){
			String s1 = money.substring(0,2);
			String s2 = money.substring(2,money.length());
			String st = convert2TextStartUpcase(s1) + " tỉ ";
			if(s2.equals("000000000")) return st; else return st + convert2Text(s2);
		}else if(money.length() == 12){
			String s1 = money.substring(0,3);
			String s2 = money.substring(3,money.length());
			String st = convert2TextStartUpcase(s1) + " tỉ ";
			if(s2.equals("000000000")) return st; else return st + convert2Text(s2);
		}else if(money.length() == 13){
			String s1 = money.substring(0,4);
			String s2 = money.substring(4,money.length());
			String st = convert2TextStartUpcase(s1) + " tỉ ";
			if(s2.equals("000000000")) return st; else return st + convert2Text(s2);
		}
		return s;
	}
	public static String addDot2Moyney(String money){
		String s = "";
		int c = 0;
		for(int i = money.length()-1; i >= 0; i--){
			s = money.charAt(i) + s;
			c++;
			if(c%3==0 && i > 0) s = '.' + s;
		}
		return s;
	}
}
