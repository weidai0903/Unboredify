package unboredify;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandSchedule rs=new RandSchedule();
		for(int i=0;i<100;i++) {
			System.out.println(rs.getDayEvent());
		}
		for(int i=0;i<100;i++) {
			System.out.println(rs.getNightEvent());
		}
		for(int i=0;i<100;i++) {
			System.out.println(rs.getRand(10));
		}
	
	}

}
