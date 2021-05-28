package javax.media.j3d;

public interface AudioDevice3DL2 extends AudioDevice3D {
  void pause();
  
  void resume();
  
  void setGain(float paramFloat);
  
  void setRateScaleFactor(int paramInt, float paramFloat);
  
  void setReverbCoefficient(float paramFloat);
  
  void setReflectionDelay(float paramFloat);
  
  void setDecayTime(float paramFloat);
  
  void setDecayFilter(float paramFloat);
  
  void setDiffusion(float paramFloat);
  
  void setDensity(float paramFloat);
  
  void setObstructionGain(int paramInt, float paramFloat);
  
  void setObstructionFilter(int paramInt, float paramFloat);
  
  void setOcclusionGain(int paramInt, float paramFloat);
  
  void setOcclusionFilter(int paramInt, float paramFloat);
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\AudioDevice3DL2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */