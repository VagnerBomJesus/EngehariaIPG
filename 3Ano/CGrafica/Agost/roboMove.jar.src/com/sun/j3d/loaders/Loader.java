package com.sun.j3d.loaders;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.net.URL;

public interface Loader {
  public static final int LOAD_LIGHT_NODES = 1;
  
  public static final int LOAD_FOG_NODES = 2;
  
  public static final int LOAD_BACKGROUND_NODES = 4;
  
  public static final int LOAD_BEHAVIOR_NODES = 8;
  
  public static final int LOAD_VIEW_GROUPS = 16;
  
  public static final int LOAD_SOUND_NODES = 32;
  
  public static final int LOAD_ALL = -1;
  
  Scene load(String paramString) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException;
  
  Scene load(URL paramURL) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException;
  
  Scene load(Reader paramReader) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException;
  
  void setBaseUrl(URL paramURL);
  
  void setBasePath(String paramString);
  
  URL getBaseUrl();
  
  String getBasePath();
  
  void setFlags(int paramInt);
  
  int getFlags();
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3d\loaders\Loader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */