package com.sun.j3d.utils.pickfast.behaviors;

import javax.media.j3d.TransformGroup;

public interface PickingCallback {
  public static final int ROTATE = 0;
  
  public static final int TRANSLATE = 1;
  
  public static final int ZOOM = 2;
  
  public static final int NO_PICK = 3;
  
  void transformChanged(int paramInt, TransformGroup paramTransformGroup);
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\pickfast\behaviors\PickingCallback.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */