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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class J3dThread
/*     */   extends Thread
/*     */ {
/*     */   static final int BEHAVIOR_SCHEDULER = 1;
/*     */   static final int SOUND_SCHEDULER = 2;
/*     */   static final int INPUT_DEVICE_SCHEDULER = 4;
/*     */   static final int RENDER_THREAD = 16;
/*     */   static final int UPDATE_GEOMETRY = 64;
/*     */   static final int UPDATE_RENDER = 128;
/*     */   static final int UPDATE_BEHAVIOR = 256;
/*     */   static final int UPDATE_SOUND = 512;
/*     */   static final int UPDATE_RENDERING_ATTRIBUTES = 1024;
/*     */   static final int UPDATE_RENDERING_ENVIRONMENT = 4096;
/*     */   static final int UPDATE_TRANSFORM = 8192;
/*     */   static final int WORK_THREAD = 1;
/*     */   static final int UPDATE_THREAD = 2;
/*     */   static final int WAIT = 0;
/*     */   static final int NOTIFY_AND_WAIT = 1;
/*     */   static final int NOTIFY = 2;
/*     */   static final int RUN = 2;
/*     */   static final int STOP = 3;
/*     */   boolean active = false;
/*  90 */   private J3dThreadData[] data = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long referenceTime;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   long lastWaitTimestamp = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int type;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   int classification = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   Object[] args = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   private static int numInstances = 0;
/* 136 */   private int instanceNum = -1;
/*     */ 
/*     */   
/* 139 */   private int newInstanceNum() { return ++numInstances; }
/*     */ 
/*     */   
/*     */   int getInstanceNum() {
/* 143 */     if (this.instanceNum == -1)
/* 144 */       this.instanceNum = newInstanceNum(); 
/* 145 */     return this.instanceNum;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract void doWork(long paramLong);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   J3dThread(ThreadGroup paramThreadGroup) { super(paramThreadGroup, ""); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   J3dThreadData getThreadData(View paramView, Canvas3D paramCanvas3D) {
/*     */     J3dThreadData j3dThreadData;
/* 169 */     if (this.type != 16) {
/* 170 */       if (this.data == null) {
/* 171 */         this.data = new J3dThreadData[1];
/* 172 */         this.data[0] = new J3dThreadData();
/* 173 */         (this.data[0]).thread = this;
/* 174 */         (this.data[0]).threadType = this.type;
/* 175 */         (this.data[0]).view = null;
/* 176 */         (this.data[0]).canvas = null;
/*     */       } 
/* 178 */       j3dThreadData = this.data[0];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 184 */     else if (this.data == null) {
/* 185 */       this.data = new J3dThreadData[1];
/* 186 */       this.data[0] = new J3dThreadData();
/* 187 */       (this.data[0]).thread = this;
/* 188 */       (this.data[0]).threadType = this.type;
/* 189 */       (this.data[0]).view = paramView;
/* 190 */       (this.data[0]).canvas = paramCanvas3D;
/* 191 */       (this.data[0]).threadArgs = new Object[4];
/* 192 */       j3dThreadData = this.data[0];
/*     */     } else {
/* 194 */       byte b; for (b = 0; b < this.data.length && (
/* 195 */         (this.data[b]).view != paramView || (this.data[b]).canvas != paramCanvas3D); b++);
/*     */ 
/*     */ 
/*     */       
/* 199 */       if (b == this.data.length) {
/* 200 */         J3dThreadData[] arrayOfJ3dThreadData = new J3dThreadData[this.data.length + 1]; byte b1;
/* 201 */         for (b1 = 0; b1 < this.data.length; b1++) {
/* 202 */           arrayOfJ3dThreadData[b1] = this.data[b1];
/*     */         }
/* 204 */         this.data = arrayOfJ3dThreadData;
/* 205 */         this.data[b1] = new J3dThreadData();
/* 206 */         (this.data[b1]).thread = this;
/* 207 */         (this.data[b1]).threadType = this.type;
/* 208 */         (this.data[b1]).view = paramView;
/* 209 */         (this.data[b1]).canvas = paramCanvas3D;
/* 210 */         (this.data[b1]).threadArgs = new Object[4];
/* 211 */         j3dThreadData = this.data[b1];
/*     */       } else {
/* 213 */         j3dThreadData = this.data[b];
/* 214 */         Object[] arrayOfObject = (Object[])j3dThreadData.threadArgs;
/* 215 */         arrayOfObject[0] = null;
/* 216 */         arrayOfObject[1] = null;
/* 217 */         arrayOfObject[2] = null;
/* 218 */         arrayOfObject[3] = null;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 224 */     return j3dThreadData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initialize() {
/* 232 */     start();
/* 233 */     while (!this.started) {
/* 234 */       MasterControl.threadYield();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void finish() {
/* 243 */     while (!this.waiting) {
/* 244 */       MasterControl.threadYield();
/*     */     }
/* 246 */     runMonitor(3, 0L, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 254 */     runMonitor(0, 0L, null);
/* 255 */     while (this.running) {
/* 256 */       doWork(this.referenceTime);
/* 257 */       runMonitor(1, 0L, null);
/*     */     } 
/*     */     
/* 260 */     shutdown();
/*     */   }
/*     */ 
/*     */   
/*     */   void runMonitor(int paramInt, long paramLong, Object[] paramArrayOfObject) {
/* 265 */     switch (paramInt) {
/*     */       case 0:
/* 267 */         this.started = true;
/*     */         
/* 269 */         while (!this.ready && this.running) {
/* 270 */           this.waiting = true;
/*     */           try {
/* 272 */             wait();
/* 273 */           } catch (InterruptedException interruptedException) {
/* 274 */             System.err.println(interruptedException);
/*     */           } 
/* 276 */           this.waiting = false;
/*     */         } 
/* 278 */         this.ready = false;
/*     */         break;
/*     */       
/*     */       case 1:
/* 282 */         VirtualUniverse.mc.runMonitor(3, null, null, null, this);
/*     */ 
/*     */         
/* 285 */         while (!this.ready && this.running) {
/* 286 */           this.waiting = true;
/*     */           try {
/* 288 */             wait();
/* 289 */           } catch (InterruptedException interruptedException) {
/* 290 */             System.err.println(interruptedException);
/*     */           } 
/* 292 */           this.waiting = false;
/*     */         } 
/* 294 */         this.ready = false;
/*     */         break;
/*     */       
/*     */       case 2:
/* 298 */         this.referenceTime = paramLong;
/* 299 */         this.args = paramArrayOfObject;
/* 300 */         this.ready = true;
/* 301 */         if (this.waiting) {
/* 302 */           notify();
/*     */         }
/*     */         break;
/*     */       
/*     */       case 3:
/* 307 */         this.running = false;
/* 308 */         if (this.waiting) {
/* 309 */           notify();
/*     */         }
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 318 */   void cleanupView() { this.data = null; }
/*     */ 
/*     */ 
/*     */   
/*     */   void shutdown() {}
/*     */ 
/*     */   
/*     */   void cleanup() {
/* 326 */     this.active = false;
/* 327 */     this.running = true;
/* 328 */     this.ready = false;
/* 329 */     this.data = null;
/* 330 */     this.started = true;
/* 331 */     this.lastWaitTimestamp = 0L;
/* 332 */     this.classification = 1;
/* 333 */     this.args = null;
/* 334 */     this.userStop = false;
/* 335 */     this.referenceTime = 0L;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\J3dThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */