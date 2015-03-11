package util;

public class Utilities
{
	public static void printBoolArray(boolean[] array){
		String s = "[";
		for(boolean b : array){
			s += b ? "1" : "0";
			s += ",";
		}
		s += "]";
		System.out.println(s);
	}
}
