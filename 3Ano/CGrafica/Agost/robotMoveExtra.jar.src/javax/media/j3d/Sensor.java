/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Matrix3d;
/*     */ import javax.vecmath.Point3d;
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
/*     */ public class Sensor
/*     */ {
/*     */   public static final int PREDICT_NONE = 1;
/*     */   public static final int PREDICT_NEXT_FRAME_TIME = 2;
/*     */   public static final int NO_PREDICTOR = 16;
/*     */   public static final int HEAD_PREDICTOR = 32;
/*     */   public static final int HAND_PREDICTOR = 64;
/*     */   public static final int DEFAULT_SENSOR_READ_COUNT = 30;
/*     */   static final int SENSOR_READ_COUNT_BUFFER = 15;
/* 110 */   static int num_reads_so_far = 0;
/*     */ 
/*     */   
/*     */   boolean demand_driven;
/*     */ 
/*     */   
/*     */   int sensorReadCount;
/*     */ 
/*     */   
/*     */   private int predictionPolicy;
/*     */ 
/*     */   
/*     */   private int predictorType;
/*     */ 
/*     */   
/*     */   InputDevice device;
/*     */ 
/*     */   
/*     */   SensorRead[] readings;
/*     */ 
/*     */   
/*     */   int currentIndex;
/*     */ 
/*     */   
/*     */   int lastIndex;
/*     */ 
/*     */   
/*     */   Point3d hotspot;
/*     */ 
/*     */   
/*     */   int MaxSensorReadIndex;
/*     */ 
/*     */   
/*     */   int sensorButtonCount;
/*     */ 
/*     */   
/*     */   Matrix3d orig_rot;
/*     */ 
/*     */   
/*     */   Matrix3d orig_rot_transpose;
/*     */ 
/*     */   
/*     */   Matrix3d temp_rot;
/*     */ 
/*     */   
/*     */   Matrix3d local_svd;
/*     */ 
/*     */   
/* 158 */   public Sensor(InputDevice paramInputDevice) { this(paramInputDevice, 30, 0, new Point3d(0.0D, 0.0D, 0.0D)); }
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
/* 170 */   public Sensor(InputDevice paramInputDevice, int paramInt) { this(paramInputDevice, paramInt, 0, new Point3d(0.0D, 0.0D, 0.0D)); }
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
/* 185 */   public Sensor(InputDevice paramInputDevice, int paramInt1, int paramInt2) { this(paramInputDevice, paramInt1, paramInt2, new Point3d(0.0D, 0.0D, 0.0D)); }
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
/* 198 */   public Sensor(InputDevice paramInputDevice, Point3d paramPoint3d) { this(paramInputDevice, 30, 0, paramPoint3d); }
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
/* 212 */   public Sensor(InputDevice paramInputDevice, int paramInt, Point3d paramPoint3d) { this(paramInputDevice, paramInt, 0, paramPoint3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Sensor(InputDevice paramInputDevice, int paramInt1, int paramInt2, Point3d paramPoint3d) {
/*     */     this.demand_driven = false;
/*     */     this.predictionPolicy = 16;
/*     */     this.predictorType = 1;
/*     */     this.orig_rot = new Matrix3d();
/*     */     this.orig_rot_transpose = new Matrix3d();
/*     */     this.temp_rot = new Matrix3d();
/*     */     this.local_svd = new Matrix3d();
/* 230 */     this.device = paramInputDevice;
/* 231 */     this.sensorReadCount = paramInt1;
/* 232 */     this.MaxSensorReadIndex = paramInt1 + 15 - 1;
/* 233 */     this.sensorButtonCount = paramInt2;
/* 234 */     this.readings = new SensorRead[this.MaxSensorReadIndex + 1];
/* 235 */     for (byte b = 0; b < this.MaxSensorReadIndex + 1; b++) {
/* 236 */       this.readings[b] = new SensorRead(paramInt2);
/*     */     }
/* 238 */     this.currentIndex = 0;
/* 239 */     this.hotspot = new Point3d(paramPoint3d);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int previousIndex(int paramInt) {
/* 245 */     int i = this.currentIndex - paramInt;
/* 246 */     return (i >= 0) ? i : (this.MaxSensorReadIndex + i + 1);
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
/*     */ 
/*     */   
/*     */   public void setPredictor(int paramInt) {
/* 262 */     if (paramInt != 1 && paramInt != 2) {
/* 263 */       throw new IllegalArgumentException(J3dI18N.getString("Sensor0"));
/*     */     }
/* 265 */     this.predictorType = paramInt;
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
/* 277 */   public int getPredictor() { return this.predictorType; }
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
/*     */   public void setPredictionPolicy(int paramInt) {
/* 293 */     if (paramInt != 16 && paramInt != 32 && paramInt != 64)
/*     */     {
/* 295 */       throw new IllegalArgumentException(J3dI18N.getString("Sensor1"));
/*     */     }
/* 297 */     this.predictionPolicy = paramInt;
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
/* 308 */   public int getPredictionPolicy() { return this.predictionPolicy; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public void setHotspot(Point3d paramPoint3d) { this.hotspot.set(paramPoint3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public void getHotspot(Point3d paramPoint3d) { paramPoint3d.set(this.hotspot); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 332 */   public void setDevice(InputDevice paramInputDevice) { this.device = paramInputDevice; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 340 */   public InputDevice getDevice() { return this.device; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getRead(Transform3D paramTransform3D) {
/* 350 */     if (this.demand_driven == true) {
/* 351 */       this.device.pollAndProcessInput();
/*     */     }
/* 353 */     paramTransform3D.set((this.readings[this.currentIndex]).read);
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
/*     */   
/* 367 */   public void getRead(Transform3D paramTransform3D, long paramLong) { getRead(paramTransform3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 376 */   public void lastRead(Transform3D paramTransform3D) { paramTransform3D.set((this.readings[this.currentIndex]).read); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void lastRead(Transform3D paramTransform3D, int paramInt) {
/* 387 */     if (paramInt >= this.sensorReadCount) {
/* 388 */       throw new IllegalArgumentException(J3dI18N.getString("Sensor3"));
/*     */     }
/* 390 */     paramTransform3D.set((this.readings[previousIndex(paramInt)]).read);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 398 */   public long lastTime() { return (this.readings[this.currentIndex]).time; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long lastTime(int paramInt) {
/* 408 */     if (paramInt >= this.sensorReadCount) {
/* 409 */       throw new IllegalArgumentException(J3dI18N.getString("Sensor4"));
/*     */     }
/* 411 */     return (this.readings[previousIndex(paramInt)]).time;
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
/* 422 */   public void lastButtons(int[] paramArrayOfInt) { System.arraycopy((this.readings[this.currentIndex]).buttonValues, 0, paramArrayOfInt, 0, this.sensorButtonCount); }
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
/*     */   public void lastButtons(int paramInt, int[] paramArrayOfInt) {
/* 437 */     if (paramInt >= this.sensorReadCount) {
/* 438 */       throw new IllegalArgumentException(J3dI18N.getString("Sensor5"));
/*     */     }
/* 440 */     System.arraycopy((this.readings[previousIndex(paramInt)]).buttonValues, 0, paramArrayOfInt, 0, this.sensorButtonCount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 450 */   public int getSensorReadCount() { return this.sensorReadCount; }
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
/*     */   public void setSensorReadCount(int paramInt) {
/* 463 */     this.sensorReadCount = paramInt;
/* 464 */     this.MaxSensorReadIndex = this.sensorReadCount + 15 - 1;
/* 465 */     this.readings = new SensorRead[this.MaxSensorReadIndex + 1];
/* 466 */     for (byte b = 0; b < this.MaxSensorReadIndex + 1; b++) {
/* 467 */       this.readings[b] = new SensorRead(this.sensorButtonCount);
/*     */     }
/* 469 */     this.currentIndex = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 478 */   public int getSensorButtonCount() { return this.sensorButtonCount; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SensorRead getCurrentSensorRead() {
/* 487 */     SensorRead sensorRead = new SensorRead(this.sensorButtonCount);
/* 488 */     sensorRead.set(this.readings[this.currentIndex]);
/* 489 */     return sensorRead;
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
/*     */ 
/*     */   
/*     */   public void setNextSensorRead(long paramLong, Transform3D paramTransform3D, int[] paramArrayOfInt) {
/* 505 */     int i = this.currentIndex + 1;
/* 506 */     if (i > this.MaxSensorReadIndex) i = 0;
/*     */     
/* 508 */     this.readings[i].setTime(paramLong);
/* 509 */     this.readings[i].set(paramTransform3D);
/* 510 */     if (this.sensorButtonCount > 0)
/* 511 */       this.readings[i].setButtons(paramArrayOfInt); 
/* 512 */     this.currentIndex = i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNextSensorRead(SensorRead paramSensorRead) {
/* 523 */     int i = this.currentIndex + 1;
/* 524 */     if (i > this.MaxSensorReadIndex) i = 0; 
/* 525 */     this.readings[i].set(paramSensorRead);
/* 526 */     this.currentIndex = i;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\Sensor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */