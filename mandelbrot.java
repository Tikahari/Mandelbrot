package mandelbrot;
import java.lang.Math;

class MandelBrot{
	double xmin, xmax, ymin, ymax;
	int x,y;
	Boolean[][] set;
	String s;
	public MandelBrot(double xmin, double xmax, double xstep, double ymin, double ymax, double ystep){
		this.x = 0;
		this.y = 0;
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.set = new Boolean[(int)Math.ceil((double)(xmax-xmin)/xstep)+10][(int)Math.ceil((double)(ymax-ymin)/ystep)+10];
		this.s = "";
	}
	public void getSet(double a, double b){
		System.out.println("Get Set for " + x + '\t' + y + '\n');
		Complex c = new Complex(a,b);
		Complex z = new Complex(0,0);
		for(int i = 0; i < 10; i++){
			z = Complex.add(Complex.mult(z,z), c);
			if(z.abs() > 2){
				this.set[x][y] = false;
				this.s += ".";
				System.out.println("Not in set\nx: "+x+"\ty: "+y);
				return;
			}
		}
		this.s += "*";
		this.set[x][y] = true;
		System.out.println("In set\nx: "+x+"\ty: "+y);
		return;
	}

	public static void main(String[] args){
		double yl = -1;
		double yh = 1;
		double ys = 0.050;
		double xl = -2;
		double xh = 2;
		double xs = 0.075;
		MandelBrot mb = new MandelBrot(xl,xh,xs,yl,yh,ys);
		int x = 0;
		int y = 0;
		for(double a = yl; a < yh; a+=ys){
			for(double b = xl; b< xh; b+=xs){
				mb.getSet(b,a);
				mb.x++;
			}
			mb.y++;
			mb.x = 0;
		}
		System.out.println(mb.s);
	}
}

public class Complex{
	double real;
	double imag;
	public Complex(double real, double imag){
		this.real = real;
		this.imag = imag;
	}
	public Complex(String num){
		this.real = 0;
		this.imag = 0;
	}
	public String toString(){
		return this.real + " + " + this.imag + "i";
	}
	public static Complex add(Complex c1,Complex c2){
		return new Complex(c1.real+c2.real,c1.imag+c2.imag);
	}
	public static Complex mult(Complex c1, Complex c2){
		Complex temp1 = new Complex(c1.real*c2.real, c1.imag*c2.real);
		Complex temp2 = new Complex(-1*(c1.imag*c2.imag), c1.real*c2.imag);
		return Complex.add(temp1, temp2);
	}
	public double abs(){
		return Math.sqrt(this.real*this.real + this.imag*this.imag);
	}
}
