/////////////////////////////
// PLAYGROUND
////////////////////////////
// Egyszer� main, mely l�trehozza a megfelel� oszt�lyokat
public class Main {

	public static void main(String[] args) {
		Engine engine = new Engine();
		GUI g = new GUI(engine);
		engine.setGUI(g);		
	}	

}
