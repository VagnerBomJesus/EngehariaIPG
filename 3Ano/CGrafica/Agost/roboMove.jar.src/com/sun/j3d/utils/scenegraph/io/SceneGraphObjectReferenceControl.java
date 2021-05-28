package com.sun.j3d.utils.scenegraph.io;

import javax.media.j3d.SceneGraphObject;

public interface SceneGraphObjectReferenceControl {
  int addReference(SceneGraphObject paramSceneGraphObject);
  
  SceneGraphObject resolveReference(int paramInt);
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\SceneGraphObjectReferenceControl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */