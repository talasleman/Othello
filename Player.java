
public class Player {
	
	private String name;
	private char colour;
	
	public Player (String name_, char colour_)
	{
		name = name_;
		colour = colour_;
	}

	public char getColour() {
		return colour;
	}

	public void setColour(char colour) {
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
