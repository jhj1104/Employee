package webapp.escape;

public class Screen {
	public static final int BLACK     = 40;
	public static final int RED        = 41;
	public static final int GREEN    = 42;
	public static final int YELLOW  = 43;
	public static final int BLUE       = 44;
	public static final int MAGENTA = 45;
	public static final int CAYN       = 46;
	public static final int WHITE     = 47;
	
	
	public static void clear(){
		System.out.print("\033[02J");
		System.out.flush();
	}
	
	public static void cursorPosition(int line, int col) {
		if(line<1 || col<1) 
			throw new RuntimeException("position error"+ line+ " " + col);
							
		System.out.print("\033[" + line + ";" + col + "H");
		System.out.flush();
	}
	
	public static void background(Background color) {
		background(color.value);
		
	}
	// �� �޼ҵ�� ���������� �߻� �� 	���� ��� ����ġ���� �ʿ����
	public static void background(int color) {
		if (color<40 || color > 47) 
			throw new RuntimeException("background color value error " + color);
		
		System.out.print("\33[" + color + "m");
		System.out.flush();
	}
	// �� �޼ҵ�� ���ܰ� ���� �������Ƿ� ����ó���� ����ߵ� (color�� �ٸ����� �ü��ֱ⶧��)
	public static void forground(Forground color) {
		forground(color.value);
		
	}

	public static void forground(int color) {
		color -= 10;
		if (color<30 || color > 37) 
			throw new RuntimeException("forground color value error " + color);
		
		System.out.print("\33[" + color + "m");
		System.out.flush();
	
	}
	
	public static void reset(){
		System.out.print("\033[0m");
		System.out.flush();
	}
	
	
	synchronized public static void print(int line, int col, char c) {
		cursorPosition(line, col);
		System.out.print(c);
		System.out.flush();
	}

}
