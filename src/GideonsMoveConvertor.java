
public class GideonsMoveConvertor {

	public static String convert(String gideonsMove) {
		
		int move = 0;
		
		try {
			move = Integer.parseInt(gideonsMove);
		}
		catch (Exception e) {
			System.out.println("Error found in GideonsMoveConvertor.java file while converting the String move to an integer move");
			System.out.println("Gideons Move ------> "+ gideonsMove);
			return "";
		}
		
		int a = (move/1000);
		int b = (move/100)%10;
		int c = (move/10)%10;
		int d = (move%10);
		
		int  first  = a;
		char second = 'A';
		int  third  = c;
		char  fourth = 'A';
		
		int i=1; int j=1;
		while(b != i) {
			second++;
			i++;
		}
		
		while(d != j) {
			fourth++;
			j++;
		}
		
		return ""+second+first+fourth+third;
	}
	
}
