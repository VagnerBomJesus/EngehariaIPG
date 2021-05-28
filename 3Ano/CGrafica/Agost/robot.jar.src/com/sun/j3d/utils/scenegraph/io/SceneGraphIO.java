package com.sun.j3d.utils.scenegraph.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface SceneGraphIO {
  void createSceneGraphObjectReferences(SceneGraphObjectReferenceControl paramSceneGraphObjectReferenceControl);
  
  void restoreSceneGraphObjectReferences(SceneGraphObjectReferenceControl paramSceneGraphObjectReferenceControl);
  
  void writeSceneGraphObject(DataOutput paramDataOutput) throws IOException;
  
  void readSceneGraphObject(DataInput paramDataInput) throws IOException;
  
  boolean saveChildren();
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\SceneGraphIO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */