
public class Tuple<X, Y> {
	public final X x;
	public final Y y;
	
	public Tuple(X x, Y y) {
		this.x = x;
		this.y = y;
	}

    public boolean equals(Tuple<X, Y> that) {
        return this.x == that.x && this.y == that.y;
    }
}
