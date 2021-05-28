package com.sun.j3d.utils.geometry.compression;

interface GeneralizedStripFlags {
  public static final int RESTART_CW = 0;
  
  public static final int RESTART_CCW = 1;
  
  public static final int REPLACE_MIDDLE = 2;
  
  public static final int REPLACE_OLDEST = 3;
  
  public static final int FRONTFACE_CW = 0;
  
  public static final int FRONTFACE_CCW = 1;
  
  int getFlagCount();
  
  int getFlag(int paramInt);
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\geometry\compression\GeneralizedStripFlags.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */