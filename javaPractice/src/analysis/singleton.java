package analysis;

public class singleton {
	private static singleton ref;
	
	private singleton(){
	}
	
	public static singleton getInstance(){
		if(ref==null){
			ref=new singleton();
		}
		return ref;
	}
}
