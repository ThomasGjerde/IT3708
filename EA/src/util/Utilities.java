package util;

public class Utilities
{
	public static void printBoolArray(boolean[] array){
		String s = "[";
		for(boolean b : array){
			s += b ? "1" : "0";
			s += ",";
		}
		if(s.length() > 0){
			s = s.substring(0,s.length() -1);
		}
		s += "]";
		System.out.println(s);
	}
	public static void printDoubleArray(double[] array){
		String s = "[";
		for(double i : array){
			s += i;
			s += ",";
		}
		if(s.length() > 0){
			s = s.substring(0,s.length() -1);
		}
		s += "]";
		System.out.println(s);
	}
}
