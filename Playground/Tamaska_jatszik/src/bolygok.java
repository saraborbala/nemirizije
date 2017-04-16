import java.io.*;


public class bolygok 
{
	String Name;
	int size;
	
	public bolygok (String Name, int size)
	{
		this.Name = Name;
		this.size = size;
	}
	
	public void display ()
	{
		System.out.println("The name is: " + Name);
		System.out.println("My size is:"  + size);
	}
}
