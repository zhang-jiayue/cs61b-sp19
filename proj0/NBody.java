public class NBody{
	public static void main(String[] args) {
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];
		Planet [] planets = readPlanets(filename);
		double radius = readRadius(filename);


		/**Sets up the universe*/
		StdDraw.setScale(-radius, radius);
		/* Clears the drawing window*/
		StdDraw.clear();
		/* Draw the image as background*/
		String backGroundPath = "images/starfield.jpg";
		double size = radius * 2;
		StdDraw.picture(0, 0, backGroundPath, size, size);

		for(int i = 0; i < planets.length; i ++){
			planets[i].draw();
		}

		/* Enable double buffering*/
		StdDraw.enableDoubleBuffering();

		int count = 0;

		while(count < T){
			double [] xForces = new double[planets.length];
			double [] yForces = new double[planets.length];				
			for(int i = 0; i < planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for(int i = 0; i < planets.length; i++){
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			/**Sets up the universe*/
			StdDraw.setScale(-radius, radius);
			/* Clears the drawing window*/
			StdDraw.clear();
			/* Draw the image as background*/
			StdDraw.picture(0, 0, backGroundPath, size, size);
			for(int i = 0; i < planets.length; i ++){
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);


			count += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
   			 StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
             planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
             planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}



	public static double readRadius(String path) {
		/** Start reading in the input file */
		In in = new In(path);
		int n = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String path){
		In in = new In(path);
		int n = in.readInt();
		double radius = in.readDouble();
		Planet [] result = new Planet[n];
		for(int i = 0; i < n; i ++){
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			Planet p = new Planet(xPos, yPos, xVel, yVel, mass, img);
			result[i] = p;

		}
		return result;
	}
}
