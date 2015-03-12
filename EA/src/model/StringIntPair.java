package model;

public class StringIntPair
{
	private String string;
	private int integer;
	public StringIntPair(String string, int integer){
		this.string = string;
		this.integer = integer;
	}
	public String getString()
	{
		return string;
	}
	public void setString(String string)
	{
		this.string = string;
	}
	public int getInteger()
	{
		return integer;
	}
	public void setInteger(int integer)
	{
		this.integer = integer;
	}
}
