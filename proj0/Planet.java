public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}


	public double calcDistance(Planet p){
		double dist = 0;
		dist = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) +
		 	   (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos);
		dist = Math.sqrt(dist);

		return dist;
	}

	public double calcForceExertedBy(Planet p){
		double force = 0;
		double rsqrt = this.calcDistance(p) * this.calcDistance(p);
		force = Planet.G * (this.mass * p.mass / 
				rsqrt);

		return force;
	}

	public double calcForceExertedByX(Planet p){
		double r = this.calcDistance(p);
		double dx =  p.xxPos - this.xxPos;
		double force = this.calcForceExertedBy(p);
		double result  = force * dx / r;

		return result;

	}

	public double calcForceExertedByY(Planet p){
		double r = this.calcDistance(p);
		double dy =  p.yyPos - this.yyPos;
		double force = this.calcForceExertedBy(p);
		double result  = force * dy / r;

		return result;

	}

	public double calcNetForceExertedByX(Planet[] pList){
		double result = 0;
		for(int i = 0; i < pList.length; i ++){
			if(pList[i].equals(this)){
				continue;
			}
			result += this.calcForceExertedByX(pList[i]);
		}


		return result;
	}

	public double calcNetForceExertedByY(Planet[] pList){
		double result = 0;
		for(int i = 0; i < pList.length; i ++){
			if(pList[i].equals(this)){
				continue;
			}
			result += this.calcForceExertedByY(pList[i]);
		}


		return result;
	}

	public void update(double dt, double fX, double fY){
		double ax = fX / this.mass;
		double ay = fY / this.mass;
		this.xxVel += ax * dt;
		this.yyVel += ay * dt;
		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;
	}

	

}
