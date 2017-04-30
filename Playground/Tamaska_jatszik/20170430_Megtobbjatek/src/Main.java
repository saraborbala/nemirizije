/////////////////////////////
// PLAYGROUND
////////////////////////////
// Egyszerû main, mely létrehozza a megfelelõ osztályokat
public class Main {

	public static void main(String[] args) {
		Engine engine = new Engine();
		GUI g = new GUI(engine);
		engine.setGUI(g);		
	}	

}
