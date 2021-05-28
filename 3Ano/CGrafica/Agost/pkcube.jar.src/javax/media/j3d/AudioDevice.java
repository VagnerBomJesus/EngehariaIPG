package javax.media.j3d;

public interface AudioDevice {
  public static final int HEADPHONES = 0;
  
  public static final int MONO_SPEAKER = 1;
  
  public static final int STEREO_SPEAKERS = 2;
  
  boolean initialize();
  
  boolean close();
  
  void setAudioPlaybackType(int paramInt);
  
  int getAudioPlaybackType();
  
  void setCenterEarToSpeaker(float paramFloat);
  
  float getCenterEarToSpeaker();
  
  void setAngleOffsetToSpeaker(float paramFloat);
  
  float getAngleOffsetToSpeaker();
  
  int getTotalChannels();
  
  int getChannelsAvailable();
  
  int getChannelsUsedForSound(Sound paramSound);
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\AudioDevice.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */