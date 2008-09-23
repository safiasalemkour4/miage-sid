/**
 * @author Florian COLLIGNON & Arnaud KNOBLOCH
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Theatre sebastopol = new Theatre();
	
		Agent bob = new Agent("Bob",sebastopol);
		Agent marcel = new Agent("Marcel",sebastopol);
		Agent patrick = new Agent("Patrick",sebastopol);
		Agent maurice = new Agent("Maurice",sebastopol);
		Agent mickey = new Agent("Mickey",sebastopol);
		Agent eddy = new Agent("Eddy",sebastopol);
		Agent clara = new Agent("Clara",sebastopol);
		Agent suzy = new Agent("Suzy",sebastopol);
		Agent geraldine = new Agent("Geraldine",sebastopol);
		Agent anne = new Agent("Anne",sebastopol);

		bob.start();
		marcel.start();
		patrick.start();
		maurice.start();
		mickey.start();
		eddy.start();
		clara.start();
		suzy.start();
		geraldine.start();
		anne.start();
	}

}
