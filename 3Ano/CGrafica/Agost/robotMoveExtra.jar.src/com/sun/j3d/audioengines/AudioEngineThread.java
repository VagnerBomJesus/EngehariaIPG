/*     */ package com.sun.j3d.audioengines;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AudioEngineThread
/*     */   extends Thread
/*     */ {
/*     */   protected static final boolean debugFlag = false;
/*     */   protected static final int WORK_THREAD = 1;
/*     */   protected static final int UPDATE_THREAD = 2;
/*     */   protected static final int WAIT = 0;
/*     */   protected static final int NOTIFY_AND_WAIT = 1;
/*     */   protected static final int RUN = 2;
/*     */   protected static final int STOP = 3;
/*     */   
/*     */   protected void debugPrint(String paramString) {}
/*     */   
/*     */   protected boolean active = false;
/*     */   protected boolean running = true;
/*     */   protected boolean started = false;
/*     */   protected long referenceTime;
/* 125 */   protected long lastWaitTimestamp = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int type;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   protected int classification = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   protected Object[] args = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean userStop = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean waiting = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   protected static int numInstances = 0;
/* 156 */   protected int instanceNum = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public AudioEngineThread(ThreadGroup paramThreadGroup, String paramString) { super(paramThreadGroup, paramString); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 168 */   int newInstanceNum() { return ++numInstances; }
/*     */ 
/*     */   
/*     */   int getInstanceNum() {
/* 172 */     if (this.instanceNum == -1)
/* 173 */       this.instanceNum = newInstanceNum(); 
/* 174 */     return this.instanceNum;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doWork() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/* 193 */     start();
/* 194 */     while (!this.started) {
/*     */       try {
/* 196 */         Thread.currentThread().sleep(1L, 0);
/* 197 */       } catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finish() {
/* 206 */     while (!this.waiting) {
/*     */       try {
/* 208 */         Thread.sleep(10L);
/* 209 */       } catch (InterruptedException interruptedException) {}
/*     */     } 
/* 211 */     runMonitor(3, 0L, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 221 */     runMonitor(0, 0L, null);
/* 222 */     while (this.running) {
/* 223 */       doWork();
/* 224 */       runMonitor(0, 0L, null);
/*     */     } 
/*     */     
/* 227 */     shutdown();
/*     */   }
/*     */   
/*     */   public void runMonitor(int paramInt, long paramLong, Object[] paramArrayOfObject) {
/* 231 */     switch (paramInt) {
/*     */       
/*     */       case 0:
/*     */         
/*     */         try {
/* 236 */           this.started = true;
/* 237 */           this.waiting = true;
/* 238 */           wait();
/* 239 */         } catch (InterruptedException interruptedException) {
/* 240 */           System.err.println(interruptedException);
/*     */         } 
/* 242 */         this.waiting = false;
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 247 */         this.referenceTime = paramLong;
/* 248 */         this.args = paramArrayOfObject;
/* 249 */         notify();
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 254 */         this.running = false;
/* 255 */         notify();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void shutdown() {}
/*     */ 
/*     */   
/*     */   public void cleanup() {
/* 265 */     this.active = false;
/* 266 */     this.running = true;
/* 267 */     this.started = true;
/* 268 */     this.lastWaitTimestamp = 0L;
/* 269 */     this.classification = 1;
/* 270 */     this.args = null;
/* 271 */     this.userStop = false;
/* 272 */     this.referenceTime = 0L;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\audioengines\AudioEngineThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */