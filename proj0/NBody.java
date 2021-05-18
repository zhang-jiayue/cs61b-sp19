public class NBody{
	public static double readRadius(String path) {
		/** Start reading in the input file */
		In in = new In(path);
		int n = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	
}
