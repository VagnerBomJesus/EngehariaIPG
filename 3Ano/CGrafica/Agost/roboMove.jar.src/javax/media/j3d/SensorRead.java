/*     */ package javax.media.j3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SensorRead
/*     */ {
/*     */   public static final int MAXIMUM_SENSOR_BUTTON_COUNT = 12;
/*     */   long time;
/*     */   Transform3D read;
/*     */   int[] buttonValues;
/*     */   int numButtons;
/*     */   
/*  62 */   public SensorRead() { this(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SensorRead(int paramInt) {
/*  71 */     this.read = new Transform3D();
/*  72 */     this.numButtons = paramInt;
/*  73 */     this.buttonValues = new int[paramInt];
/*     */ 
/*     */     
/*  76 */     this.time = J3dClock.currentTimeMillis();
/*     */   }
/*     */   
/*     */   final void set(SensorRead paramSensorRead) {
/*  80 */     this.time = paramSensorRead.time;
/*  81 */     this.numButtons = paramSensorRead.numButtons;
/*  82 */     this.read.set(paramSensorRead.read);
/*  83 */     if (this.numButtons > 0) {
/*  84 */       System.arraycopy(paramSensorRead.buttonValues, 0, this.buttonValues, 0, paramSensorRead.numButtons);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public void set(Transform3D paramTransform3D) { this.read.set(paramTransform3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public void get(Transform3D paramTransform3D) { paramTransform3D.set(this.read); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void setTime(long paramLong) { this.time = paramLong; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public long getTime() { return this.time; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setButtons(int[] paramArrayOfInt) {
/* 128 */     if (this.numButtons == 0)
/*     */     {
/* 130 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("SensorRead1"));
/*     */     }
/* 132 */     if (paramArrayOfInt.length < this.numButtons)
/*     */     {
/* 134 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("SensorRead0")); } 
/* 135 */     System.arraycopy(paramArrayOfInt, 0, this.buttonValues, 0, this.numButtons);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getButtons(int[] paramArrayOfInt) {
/* 149 */     if (this.numButtons > 0) {
/* 150 */       System.arraycopy(this.buttonValues, 0, paramArrayOfInt, 0, this.numButtons);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public int getNumButtons() { return this.numButtons; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\SensorRead.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */