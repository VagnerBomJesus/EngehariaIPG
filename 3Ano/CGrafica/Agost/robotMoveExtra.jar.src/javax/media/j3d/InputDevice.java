package javax.media.j3d;

public interface InputDevice {
  public static final int BLOCKING = 3;
  
  public static final int NON_BLOCKING = 4;
  
  public static final int DEMAND_DRIVEN = 5;
  
  boolean initialize();
  
  void setNominalPositionAndOrientation();
  
  void pollAndProcessInput();
  
  void processStreamInput();
  
  void close();
  
  int getProcessingMode();
  
  void setProcessingMode(int paramInt);
  
  int getSensorCount();
  
  Sensor getSensor(int paramInt);
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\InputDevice.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */