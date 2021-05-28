package javax.media.j3d;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public interface AudioDevice3D extends AudioDevice {
  public static final int BACKGROUND_SOUND = 1;
  
  public static final int POINT_SOUND = 2;
  
  public static final int CONE_SOUND = 3;
  
  public static final int STREAMING_AUDIO_DATA = 1;
  
  public static final int BUFFERED_AUDIO_DATA = 2;
  
  void setView(View paramView);
  
  int prepareSound(int paramInt, MediaContainer paramMediaContainer);
  
  void clearSound(int paramInt);
  
  long getSampleDuration(int paramInt);
  
  int getNumberOfChannelsUsed(int paramInt);
  
  int getNumberOfChannelsUsed(int paramInt, boolean paramBoolean);
  
  int startSample(int paramInt);
  
  long getStartTime(int paramInt);
  
  int stopSample(int paramInt);
  
  void setSampleGain(int paramInt, float paramFloat);
  
  void setLoop(int paramInt1, int paramInt2);
  
  void setVworldXfrm(int paramInt, Transform3D paramTransform3D);
  
  void setPosition(int paramInt, Point3d paramPoint3d);
  
  void setDistanceGain(int paramInt, double[] paramArrayOfDouble1, float[] paramArrayOfFloat1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat2);
  
  void setDirection(int paramInt, Vector3d paramVector3d);
  
  void setAngularAttenuation(int paramInt1, int paramInt2, double[] paramArrayOfDouble, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2);
  
  void setRolloff(float paramFloat);
  
  void setReflectionCoefficient(float paramFloat);
  
  void setReverbDelay(float paramFloat);
  
  void setReverbOrder(int paramInt);
  
  void setDistanceFilter(int paramInt, double[] paramArrayOfDouble, float[] paramArrayOfFloat);
  
  void setFrequencyScaleFactor(float paramFloat);
  
  void setVelocityScaleFactor(float paramFloat);
  
  void muteSample(int paramInt);
  
  void unmuteSample(int paramInt);
  
  void pauseSample(int paramInt);
  
  void unpauseSample(int paramInt);
  
  void updateSample(int paramInt);
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\AudioDevice3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */