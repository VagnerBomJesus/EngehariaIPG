package testcone;

import javax.media.j3d.GeometryArray;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Point3f;

public class ConeGeometry extends TriangleArray {
	// This class implements the algorithm of the getConeGeometry1() function.
	public ConeGeometry(float h, float r, int n) {
		super(3 * n, GeometryArray.COORDINATES);

		Point3f apex = new Point3f(0, h, 0);
		Point3f p1 = new Point3f(r, 0, 0);
		int count = 0;
		double delta = 2 * Math.PI / n;
		for (int i = 1; i <= n; i++) {
			float x = (float) (r * Math.cos(i * delta));
			float z = (float) (r * Math.sin(i * delta));
			Point3f p2 = new Point3f(x, 0, z);
			this.setCoordinate(count++, apex);
			this.setCoordinate(count++, p1);
			this.setCoordinate(count++, p2);
			p1 = p2;
		}
	}
}
