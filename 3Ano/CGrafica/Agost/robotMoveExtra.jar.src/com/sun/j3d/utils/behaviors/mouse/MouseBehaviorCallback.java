package com.sun.j3d.utils.behaviors.mouse;

import javax.media.j3d.Transform3D;

public interface MouseBehaviorCallback {
  public static final int ROTATE = 0;
  
  public static final int TRANSLATE = 1;
  
  public static final int ZOOM = 2;
  
  void transformChanged(int paramInt, Transform3D paramTransform3D);
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\behaviors\mouse\MouseBehaviorCallback.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */