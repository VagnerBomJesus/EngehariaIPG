package com.sun.j3d.utils.scenegraph.transparency;

import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;

public interface TransparencySortGeom {
  Geometry getGeometry();
  
  double getDistanceSquared();
  
  void getLocalToVWorld(Transform3D paramTransform3D);
  
  Shape3D getShape3D();
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\transparency\TransparencySortGeom.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */