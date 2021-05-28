package javax.media.j3d;

import java.util.ArrayList;

interface TargetsInterface {
  public static final int TRANSFORM_TARGETS = 0;
  
  public static final int SWITCH_TARGETS = 1;
  
  CachedTargets getCachedTargets(int paramInt1, int paramInt2, int paramInt3);
  
  void resetCachedTargets(int paramInt1, CachedTargets[] paramArrayOfCachedTargets, int paramInt2);
  
  int getTargetThreads(int paramInt);
  
  void updateCachedTargets(int paramInt, CachedTargets[] paramArrayOfCachedTargets);
  
  void computeTargetThreads(int paramInt, CachedTargets[] paramArrayOfCachedTargets);
  
  void updateTargetThreads(int paramInt, CachedTargets[] paramArrayOfCachedTargets);
  
  void propagateTargetThreads(int paramInt1, int paramInt2);
  
  void copyCachedTargets(int paramInt, CachedTargets[] paramArrayOfCachedTargets);
  
  ArrayList getTargetsData(int paramInt1, int paramInt2);
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\TargetsInterface.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */