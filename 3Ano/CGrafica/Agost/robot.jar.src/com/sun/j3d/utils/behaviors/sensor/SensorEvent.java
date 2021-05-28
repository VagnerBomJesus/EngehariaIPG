/*     */ package com.sun.j3d.utils.behaviors.sensor;
/*     */ 
/*     */ import javax.media.j3d.Sensor;
/*     */ import javax.media.j3d.Transform3D;
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
/*     */ public class SensorEvent
/*     */ {
/*     */   public static final int PRESSED = 1;
/*     */   public static final int RELEASED = 2;
/*     */   public static final int DRAGGED = 3;
/*     */   public static final int READ = 4;
/*     */   public static final int NOBUTTON = -1;
/*     */   private int id;
/*     */   private Object source;
/*     */   private Sensor sensor;
/*     */   private int button;
/*     */   private int[] buttonState;
/*     */   private Transform3D sensorRead;
/*     */   private long time;
/*     */   private long lastTime;
/*     */   private boolean ephemeral;
/*     */   
/*     */   public SensorEvent(Object paramObject, int paramInt1, Sensor paramSensor, Transform3D paramTransform3D, int[] paramArrayOfInt, int paramInt2, long paramLong1, long paramLong2) {
/*  95 */     this.id = 0;
/*  96 */     this.source = null;
/*  97 */     this.sensor = null;
/*  98 */     this.button = -1;
/*  99 */     this.buttonState = null;
/* 100 */     this.sensorRead = null;
/* 101 */     this.time = 0L;
/* 102 */     this.lastTime = 0L;
/* 103 */     this.ephemeral = false;
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
/* 134 */     this.source = paramObject;
/* 135 */     this.id = paramInt1;
/* 136 */     this.sensor = paramSensor;
/* 137 */     this.button = paramInt2;
/* 138 */     this.time = paramLong1;
/* 139 */     this.lastTime = paramLong2;
/* 140 */     if (paramTransform3D == null)
/* 141 */       throw new NullPointerException("sensorRead can't be null"); 
/* 142 */     this.sensorRead = new Transform3D(paramTransform3D);
/* 143 */     if (paramArrayOfInt != null) {
/* 144 */       this.buttonState = new int[paramArrayOfInt.length];
/* 145 */       for (byte b = 0; b < paramArrayOfInt.length; b++)
/* 146 */         this.buttonState[b] = paramArrayOfInt[b]; 
/*     */     } 
/* 148 */     this.ephemeral = false;
/*     */   }
/*     */   
/*     */   public SensorEvent() {
/*     */     this.id = 0;
/*     */     this.source = null;
/*     */     this.sensor = null;
/*     */     this.button = -1;
/*     */     this.buttonState = null;
/*     */     this.sensorRead = null;
/*     */     this.time = 0L;
/*     */     this.lastTime = 0L;
/*     */     this.ephemeral = false;
/* 161 */     this.ephemeral = true;
/*     */   }
/*     */   
/*     */   public SensorEvent(SensorEvent paramSensorEvent) {
/*     */     this.id = 0;
/*     */     this.source = null;
/*     */     this.sensor = null;
/*     */     this.button = -1;
/*     */     this.buttonState = null;
/*     */     this.sensorRead = null;
/*     */     this.time = 0L;
/*     */     this.lastTime = 0L;
/*     */     this.ephemeral = false;
/* 174 */     this.source = paramSensorEvent.source;
/* 175 */     this.id = paramSensorEvent.id;
/* 176 */     this.sensor = paramSensorEvent.sensor;
/* 177 */     this.button = paramSensorEvent.button;
/* 178 */     this.time = paramSensorEvent.time;
/* 179 */     this.lastTime = paramSensorEvent.lastTime;
/* 180 */     if (paramSensorEvent.sensorRead == null)
/* 181 */       throw new NullPointerException("sensorRead can't be null"); 
/* 182 */     this.sensorRead = new Transform3D(paramSensorEvent.sensorRead);
/* 183 */     if (paramSensorEvent.buttonState != null) {
/* 184 */       this.buttonState = new int[paramSensorEvent.buttonState.length];
/* 185 */       for (byte b = 0; b < paramSensorEvent.buttonState.length; b++)
/* 186 */         this.buttonState[b] = paramSensorEvent.buttonState[b]; 
/*     */     } 
/* 188 */     this.ephemeral = false;
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
/*     */   public void set(Object paramObject, int paramInt1, Sensor paramSensor, Transform3D paramTransform3D, int[] paramArrayOfInt, int paramInt2, long paramLong1, long paramLong2) {
/* 222 */     if (!this.ephemeral) {
/* 223 */       throw new IllegalStateException("Can't set the fields of non-ephemeral events");
/*     */     }
/*     */     
/* 226 */     this.source = paramObject;
/* 227 */     this.id = paramInt1;
/* 228 */     this.sensor = paramSensor;
/* 229 */     if (paramTransform3D == null)
/* 230 */       throw new NullPointerException("sensorRead can't be null"); 
/* 231 */     this.sensorRead = paramTransform3D;
/* 232 */     this.buttonState = paramArrayOfInt;
/* 233 */     this.button = paramInt2;
/* 234 */     this.time = paramLong1;
/* 235 */     this.lastTime = paramLong2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 245 */   public Object getSource() { return this.source; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   public int getID() { return this.id; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 262 */   public Sensor getSensor() { return this.sensor; }
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
/* 273 */   public long getTime() { return this.time; }
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
/* 286 */   public long getLastTime() { return this.lastTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public void getSensorRead(Transform3D paramTransform3D) { paramTransform3D.set(this.sensorRead); }
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
/* 308 */   public int getButton() { return this.button; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getButtonState(int[] paramArrayOfInt) {
/* 318 */     if (paramArrayOfInt.length != this.buttonState.length) {
/* 319 */       throw new ArrayIndexOutOfBoundsException("buttonState array is the wrong length");
/*     */     }
/*     */     
/* 322 */     for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 323 */       paramArrayOfInt[b] = this.buttonState[b];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 333 */   public boolean isEphemeral() { return this.ephemeral; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\behaviors\sensor\SensorEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */