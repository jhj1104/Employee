package webapp.main;

import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

import webapp.escape.Background;
import webapp.escape.Forground;
import webapp.escape.Screen;

public class CalendarTest {
	
	public static void printCalender(int year, int month) {
		Screen.cursorPosition(5, 5);
		System.out.println("year = " + year +", month = " + month);
	}
	
	public static void main(String[] args) {
		Screen.clear();
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			boolean flag =true;
			String param = "";
			
			do {
				flag=true;
				Screen.cursorPosition(20, 1);
				System.out.print("[yyyy/mm] : ");
				param = scan.nextLine();
				Screen.clear();
				System.out.println("param = " + param);
				
				flag = param.matches("[1-9][0-9]{3}/[0-1][0-9]");
				
			}while(!flag);
			
			StringTokenizer tokens = new StringTokenizer(param,"/");
			
			int year = Integer.parseInt((String)tokens.nextElement()); 
			int month = Integer.parseInt((String)tokens.nextElement());
			if (month >0 && month < 12 )
				printCalender(year, month);
			
			Date date = new Date(year - 1900, month - 1, 1);
			
			int m = date.getMonth();
			String bnk = "";
			
		
			
			System.out.println("Sun Mon Tue Wed Thu Fri Sat");

			while(true) {

				System.out.print(" " + date.getDate() + " ");
				if(date.getDate()<10)
					System.out.print(" ");
				if (date.getDay() == 6){					
					System.out.println();
				}
				if (m != date.getMonth())
					break;
				date.setDate(date.getDate() + 1);
			}
				

			
			
		}
	}

}
