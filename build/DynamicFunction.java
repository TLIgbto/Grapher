package grapher.fc;
import static java.lang.Math.*;
public class DynamicFunction implements Function {
	public String toString() { return "x*x"; }
	public double y(double x) { return x*x; }
}
