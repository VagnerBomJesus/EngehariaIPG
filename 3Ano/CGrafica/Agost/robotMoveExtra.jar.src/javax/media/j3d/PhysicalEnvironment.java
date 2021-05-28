/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PhysicalEnvironment
/*     */ {
/*     */   int HeadIndex;
/*     */   int RightHandIndex;
/*     */   int LeftHandIndex;
/*     */   int DominantHandIndex;
/*     */   int NonDominantHandIndex;
/*     */   Transform3D coexistenceToTrackerBase;
/*     */   boolean trackingAvailable;
/*     */   int coexistenceCenterInPworldPolicy;
/*     */   int peDirtyMask;
/*     */   int sensorCount;
/*     */   Sensor[] sensors;
/*     */   AudioDevice audioDevice;
/*     */   boolean sensorListChanged;
/*     */   Sensor[] sensorList;
/*     */   ArrayList users;
/*     */   InputDeviceScheduler inputsched;
/*     */   Vector devices;
/*     */   int activeViewRef;
/* 122 */   static Hashtable physicalEnvMap = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public PhysicalEnvironment() { this(3); }
/*     */ 
/*     */ 
/*     */   
/*     */   void removeUser(View paramView) {
/* 148 */     int i = this.users.indexOf(paramView);
/* 149 */     if (i >= 0) {
/* 150 */       this.users.remove(i);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void addUser(View paramView) {
/* 156 */     int i = this.users.indexOf(paramView);
/* 157 */     if (i < 0) {
/* 158 */       this.users.add(paramView);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void notifyUsers() {
/* 164 */     for (int i = this.users.size() - 1; i >= 0; i--) {
/* 165 */       View view = (View)this.users.get(i);
/* 166 */       view.repaint();
/*     */     }  } public PhysicalEnvironment(int paramInt) { this.HeadIndex = 0; this.RightHandIndex = 1; this.LeftHandIndex = 2; this.DominantHandIndex = 1; this.NonDominantHandIndex = 2; this.coexistenceToTrackerBase = new Transform3D(); this.trackingAvailable = false;
/*     */     this.coexistenceCenterInPworldPolicy = 2;
/*     */     this.peDirtyMask = 7340032;
/*     */     this.audioDevice = null;
/*     */     this.sensorListChanged = false;
/*     */     this.sensorList = null;
/*     */     this.users = new ArrayList();
/*     */     this.devices = new Vector(1);
/*     */     this.activeViewRef = 0;
/* 176 */     this.sensorCount = paramInt;
/* 177 */     this.sensors = new Sensor[paramInt];
/* 178 */     for (int i = paramInt - 1; i >= 0; i--) {
/* 179 */       this.sensors[i] = null;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Sensor[] getSensorList() {
/* 191 */     synchronized (this.sensors) {
/* 192 */       if (this.sensorListChanged) {
/* 193 */         this.sensorList = new Sensor[this.sensors.length];
/* 194 */         for (byte b = 0; b < this.sensors.length; b++) {
/* 195 */           this.sensorList[b] = this.sensors[b];
/*     */         }
/* 197 */         this.sensorListChanged = false;
/*     */       } 
/*     */       
/* 200 */       return this.sensorList;
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
/* 213 */   public void setAudioDevice(AudioDevice paramAudioDevice) { this.audioDevice = paramAudioDevice; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 221 */   public AudioDevice getAudioDevice() { return this.audioDevice; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 229 */   public Enumeration getAllInputDevices() { return this.devices.elements(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInputDevice(InputDevice paramInputDevice) {
/* 242 */     int i = paramInputDevice.getProcessingMode();
/*     */     
/* 244 */     if (i == 3 || i == 4 || i == 5) {
/*     */ 
/*     */       
/* 247 */       synchronized (this.devices) {
/* 248 */         this.devices.add(paramInputDevice);
/* 249 */         if (this.inputsched != null) {
/* 250 */           this.inputsched.addInputDevice(paramInputDevice);
/*     */         }
/*     */       } 
/*     */     } else {
/* 254 */       throw new IllegalArgumentException(J3dI18N.getString("PhysicalEnvironment0"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeInputDevice(InputDevice paramInputDevice) {
/* 265 */     this.devices.remove(paramInputDevice);
/* 266 */     synchronized (this.devices) {
/* 267 */       if (this.inputsched != null) {
/* 268 */         this.inputsched.removeInputDevice(paramInputDevice);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeadIndex(int paramInt) {
/* 278 */     this.HeadIndex = paramInt;
/* 279 */     synchronized (this) {
/* 280 */       computeTrackingAvailable();
/* 281 */       this.peDirtyMask |= 0x200000;
/*     */     } 
/* 283 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 291 */   public int getHeadIndex() { return this.HeadIndex; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRightHandIndex(int paramInt) {
/* 299 */     this.RightHandIndex = paramInt;
/* 300 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 308 */   public int getRightHandIndex() { return this.RightHandIndex; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeftHandIndex(int paramInt) {
/* 316 */     this.LeftHandIndex = paramInt;
/* 317 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 325 */   public int getLeftHandIndex() { return this.LeftHandIndex; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDominantHandIndex(int paramInt) {
/* 333 */     this.DominantHandIndex = paramInt;
/* 334 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 342 */   public int getDominantHandIndex() { return this.DominantHandIndex; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNonDominantHandIndex(int paramInt) {
/* 350 */     this.NonDominantHandIndex = paramInt;
/* 351 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   public int getNonDominantHandIndex() { return this.NonDominantHandIndex; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSensor(int paramInt, Sensor paramSensor) {
/* 370 */     synchronized (this.sensors) {
/* 371 */       this.sensors[paramInt] = paramSensor;
/* 372 */       this.sensorListChanged = true;
/*     */     } 
/* 374 */     synchronized (this) {
/* 375 */       computeTrackingAvailable();
/* 376 */       this.peDirtyMask |= 0x200000;
/*     */     } 
/*     */     
/* 379 */     notifyUsers();
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
/* 391 */   public Sensor getSensor(int paramInt) { return this.sensors[paramInt]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCoexistenceToTrackerBase(Transform3D paramTransform3D) {
/* 404 */     if (!paramTransform3D.isRigid()) {
/* 405 */       throw new BadTransformException(J3dI18N.getString("PhysicalEnvironment1"));
/*     */     }
/* 407 */     synchronized (this) {
/* 408 */       this.coexistenceToTrackerBase.setWithLock(paramTransform3D);
/* 409 */       this.peDirtyMask |= 0x100000;
/*     */     } 
/*     */     
/* 412 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 422 */   public void getCoexistenceToTrackerBase(Transform3D paramTransform3D) { paramTransform3D.set(this.coexistenceToTrackerBase); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 431 */   public boolean getTrackingAvailable() { return this.trackingAvailable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCoexistenceCenterInPworldPolicy(int paramInt) {
/* 444 */     switch (paramInt) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */         break;
/*     */       
/*     */       default:
/* 451 */         throw new IllegalArgumentException(J3dI18N.getString("PhysicalEnvironment2"));
/*     */     } 
/*     */     
/* 454 */     synchronized (this) {
/* 455 */       this.coexistenceCenterInPworldPolicy = paramInt;
/* 456 */       this.peDirtyMask |= 0x400000;
/*     */     } 
/* 458 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 467 */   public int getCoexistenceCenterInPworldPolicy() { return this.coexistenceCenterInPworldPolicy; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 475 */   public int getSensorCount() { return this.sensorCount; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSensorCount(int paramInt) {
/* 487 */     Sensor[] arrayOfSensor = new Sensor[paramInt];
/* 488 */     byte b = 0;
/*     */     
/* 490 */     synchronized (this.sensors) {
/* 491 */       int i = Math.min(paramInt, this.sensorCount);
/* 492 */       while (b < i) {
/* 493 */         arrayOfSensor[b] = this.sensors[b++];
/*     */       }
/* 495 */       while (b < paramInt) {
/* 496 */         arrayOfSensor[b++] = null;
/*     */       }
/* 498 */       this.sensorCount = paramInt;
/* 499 */       this.sensorListChanged = true;
/* 500 */       this.sensors = arrayOfSensor;
/*     */     } 
/* 502 */     notifyUsers();
/*     */   }
/*     */ 
/*     */   
/*     */   private void computeTrackingAvailable() {
/* 507 */     synchronized (this.sensors) {
/* 508 */       this.trackingAvailable = (this.HeadIndex < this.sensors.length && this.sensors[this.HeadIndex] != null);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\PhysicalEnvironment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */