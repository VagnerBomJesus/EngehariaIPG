package com.sun.j3d.loaders;

import java.util.Hashtable;
import javax.media.j3d.Background;
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Fog;
import javax.media.j3d.Light;
import javax.media.j3d.Sound;
import javax.media.j3d.TransformGroup;

public interface Scene {
  BranchGroup getSceneGroup();
  
  TransformGroup[] getViewGroups();
  
  float[] getHorizontalFOVs();
  
  Light[] getLightNodes();
  
  Hashtable getNamedObjects();
  
  Background[] getBackgroundNodes();
  
  Fog[] getFogNodes();
  
  Behavior[] getBehaviorNodes();
  
  Sound[] getSoundNodes();
  
  String getDescription();
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3d\loaders\Scene.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */