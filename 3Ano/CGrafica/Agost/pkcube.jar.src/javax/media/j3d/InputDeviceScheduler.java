/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
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
/*     */ class InputDeviceScheduler
/*     */   extends J3dThread
/*     */ {
/*  29 */   ArrayList nonBlockingDevices = new ArrayList(1);
/*     */ 
/*     */   
/*  32 */   ArrayList blockingDevices = new ArrayList(1);
/*  33 */   ArrayList threads = new ArrayList(1);
/*     */ 
/*     */   
/*     */   PhysicalEnvironment physicalEnv;
/*     */ 
/*     */   
/*  39 */   Vector devices = new Vector(1);
/*     */   
/*  41 */   J3dThreadData threadData = new J3dThreadData();
/*     */ 
/*     */   
/*     */   boolean active = false;
/*     */   
/*  46 */   static int samplingTime = 5;
/*     */ 
/*     */   
/*  49 */   private static int numInstances = 0;
/*  50 */   private int instanceNum = -1;
/*     */ 
/*     */   
/*  53 */   private int newInstanceNum() { return ++numInstances; }
/*     */ 
/*     */   
/*     */   int getInstanceNum() {
/*  57 */     if (this.instanceNum == -1)
/*  58 */       this.instanceNum = newInstanceNum(); 
/*  59 */     return this.instanceNum;
/*     */   }
/*     */ 
/*     */   
/*     */   InputDeviceScheduler(ThreadGroup paramThreadGroup, PhysicalEnvironment paramPhysicalEnvironment) {
/*  64 */     super(paramThreadGroup);
/*  65 */     setName("J3D-InputDeviceScheduler-" + getInstanceNum());
/*  66 */     this.threadData.threadType = 4;
/*  67 */     this.threadData.thread = this;
/*  68 */     this.physicalEnv = paramPhysicalEnvironment;
/*     */     
/*  70 */     synchronized (paramPhysicalEnvironment.devices) {
/*  71 */       Enumeration enumeration = paramPhysicalEnvironment.devices.elements();
/*  72 */       while (enumeration.hasMoreElements()) {
/*  73 */         addInputDevice((InputDevice)enumeration.nextElement());
/*     */       }
/*  75 */       paramPhysicalEnvironment.inputsched = this;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void addInputDevice(InputDevice paramInputDevice) {
/*     */     InputDeviceBlockingThread inputDeviceBlockingThread;
/*  83 */     switch (paramInputDevice.getProcessingMode()) {
/*     */       case 3:
/*  85 */         inputDeviceBlockingThread = VirtualUniverse.mc.getInputDeviceBlockingThread(paramInputDevice);
/*     */         
/*  87 */         inputDeviceBlockingThread.start();
/*  88 */         synchronized (this.blockingDevices) {
/*  89 */           this.threads.add(inputDeviceBlockingThread);
/*  90 */           this.blockingDevices.add(paramInputDevice);
/*     */         } 
/*     */         return;
/*     */       case 4:
/*  94 */         synchronized (this.nonBlockingDevices) {
/*  95 */           this.nonBlockingDevices.add(paramInputDevice);
/*  96 */           if (this.active && this.nonBlockingDevices.size() == 1) {
/*  97 */             VirtualUniverse.mc.addInputDeviceScheduler(this);
/*     */           }
/*     */         } 
/*     */         return;
/*     */     } 
/*     */     
/* 103 */     for (int i = paramInputDevice.getSensorCount() - 1; i >= 0; i--) {
/* 104 */       (paramInputDevice.getSensor(i)).demand_driven = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeInputDevice(InputDevice paramInputDevice) {
/* 114 */     switch (paramInputDevice.getProcessingMode()) {
/*     */       
/*     */       case 3:
/* 117 */         synchronized (this.blockingDevices) {
/* 118 */           int j = this.blockingDevices.indexOf(paramInputDevice);
/* 119 */           InputDeviceBlockingThread inputDeviceBlockingThread = (InputDeviceBlockingThread)this.threads.remove(j);
/*     */           
/* 121 */           inputDeviceBlockingThread.finish();
/* 122 */           this.blockingDevices.remove(j);
/*     */         } 
/*     */         return;
/*     */       
/*     */       case 4:
/* 127 */         synchronized (this.nonBlockingDevices) {
/* 128 */           this.nonBlockingDevices.remove(this.nonBlockingDevices.indexOf(paramInputDevice));
/* 129 */           if (this.active && this.nonBlockingDevices.size() == 0) {
/* 130 */             VirtualUniverse.mc.removeInputDeviceScheduler(this);
/*     */           }
/*     */         } 
/*     */         return;
/*     */     } 
/*     */     
/* 136 */     for (int i = paramInputDevice.getSensorCount() - 1; i >= 0; i--) {
/* 137 */       (paramInputDevice.getSensor(i)).demand_driven = false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void activate() {
/* 144 */     if (!this.active) {
/* 145 */       this.active = true;
/*     */       
/* 147 */       synchronized (this.nonBlockingDevices) {
/* 148 */         if (this.nonBlockingDevices.size() > 0) {
/* 149 */           VirtualUniverse.mc.addInputDeviceScheduler(this);
/*     */         }
/*     */       } 
/*     */       
/* 153 */       synchronized (this.blockingDevices) {
/* 154 */         for (int i = this.threads.size() - 1; i >= 0; i--) {
/* 155 */           ((InputDeviceBlockingThread)this.threads.get(i)).restart();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void deactivate() {
/* 163 */     if (this.active) {
/* 164 */       synchronized (this.nonBlockingDevices) {
/* 165 */         if (this.nonBlockingDevices.size() > 0) {
/* 166 */           VirtualUniverse.mc.removeInputDeviceScheduler(this);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 171 */       synchronized (this.blockingDevices) {
/* 172 */         for (int i = this.threads.size() - 1; i >= 0; i--) {
/* 173 */           ((InputDeviceBlockingThread)this.threads.get(i)).sleep();
/*     */         }
/*     */       } 
/* 176 */       this.active = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 181 */   J3dThreadData getThreadData() { return this.threadData; }
/*     */ 
/*     */   
/*     */   void doWork(long paramLong) {
/* 185 */     synchronized (this.nonBlockingDevices) {
/* 186 */       for (int i = this.nonBlockingDevices.size() - 1; i >= 0; i--) {
/* 187 */         ((InputDevice)this.nonBlockingDevices.get(i)).pollAndProcessInput();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void shutdown() {
/* 194 */     for (int i = this.threads.size() - 1; i >= 0; i--) {
/* 195 */       ((InputDeviceBlockingThread)this.threads.get(i)).finish();
/*     */     }
/*     */     
/* 198 */     this.threads.clear();
/* 199 */     this.blockingDevices.clear();
/* 200 */     this.nonBlockingDevices.clear();
/* 201 */     this.devices.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\InputDeviceScheduler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */