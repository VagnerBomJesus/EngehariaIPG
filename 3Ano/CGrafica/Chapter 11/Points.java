package shapes;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.PointArray;
import javax.media.j3d.PointAttributes;
import javax.media.j3d.Shape3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

public class Points extends Shape3D {
	public Points(Point3f cords[], Point3f delta, Color3f color, float size) {
		
		for(int i=0; i<cords.length; i++) {
			cords[i].x += delta.x;
			cords[i].y += delta.y;
			cords[i].z += delta.z;
		}
		
		PointArray point_geom = new PointArray(cords.length, GeometryArray.COORDINATES);
		point_geom.setCoordinates(0, cords);
		Appearance points_appear = new Appearance();
		ColoringAttributes points_coloring = new ColoringAttributes();
		points_coloring.setColor(color);
		points_appear.setColoringAttributes(points_coloring);
		PointAttributes points_points = new PointAttributes(size, true);
		points_appear.setPointAttributes(points_points);
		//Shape3D points = new Shape3D(point_geom, points_appear);
		
		this.setGeometry(point_geom);
		this.setAppearance(points_appear);
	}

}
