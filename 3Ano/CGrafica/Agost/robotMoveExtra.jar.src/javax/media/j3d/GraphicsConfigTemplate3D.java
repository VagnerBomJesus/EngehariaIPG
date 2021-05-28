/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.GraphicsConfigTemplate;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphicsConfigTemplate3D
/*     */   extends GraphicsConfigTemplate
/*     */ {
/*  47 */   static Object globalLock = new Object();
/*  48 */   static Object monitorLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   int doubleBuffer = 1;
/*  67 */   int stereo = 3;
/*  68 */   int depthSize = 16;
/*  69 */   int stencilSize = 0;
/*  70 */   int redSize = this.greenSize = this.blueSize = 2;
/*  71 */   int sceneAntialiasing = 3;
/*     */ 
/*     */   
/*     */   int blueSize;
/*     */ 
/*     */   
/*     */   int greenSize;
/*     */ 
/*     */   
/*     */   Object testCfg;
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoubleBuffer(int paramInt) {
/*  85 */     if (paramInt < 1 && paramInt > 3) {
/*     */       return;
/*     */     }
/*  88 */     this.doubleBuffer = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public int getDoubleBuffer() { return this.doubleBuffer; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStereo(int paramInt) {
/* 109 */     if (paramInt < 1 && paramInt > 3) {
/*     */       return;
/*     */     }
/* 112 */     this.stereo = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public int getStereo() { return this.stereo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSceneAntialiasing(int paramInt) {
/* 133 */     if (paramInt < 1 && paramInt > 3) {
/*     */       return;
/*     */     }
/* 136 */     this.sceneAntialiasing = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public int getSceneAntialiasing() { return this.sceneAntialiasing; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDepthSize(int paramInt) {
/* 155 */     if (paramInt < 0) {
/*     */       return;
/*     */     }
/* 158 */     this.depthSize = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public int getDepthSize() { return this.depthSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStencilSize(int paramInt) {
/* 181 */     if (paramInt < 0) {
/*     */       return;
/*     */     }
/* 184 */     this.stencilSize = paramInt;
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
/* 195 */   public int getStencilSize() { return this.stencilSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRedSize(int paramInt) {
/* 206 */     if (paramInt < 0) {
/*     */       return;
/*     */     }
/* 209 */     this.redSize = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   public int getRedSize() { return this.redSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGreenSize(int paramInt) {
/* 229 */     if (paramInt < 0) {
/*     */       return;
/*     */     }
/* 232 */     this.greenSize = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public int getGreenSize() { return this.greenSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlueSize(int paramInt) {
/* 251 */     if (paramInt < 0) {
/*     */       return;
/*     */     }
/* 254 */     this.blueSize = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 262 */   public int getBlueSize() { return this.blueSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsConfiguration getBestConfiguration(GraphicsConfiguration[] paramArrayOfGraphicsConfiguration) {
/* 281 */     if (paramArrayOfGraphicsConfiguration == null || paramArrayOfGraphicsConfiguration.length == 0 || paramArrayOfGraphicsConfiguration[false] == null) {
/* 282 */       return null;
/*     */     }
/*     */     
/* 285 */     synchronized (globalLock) {
/* 286 */       this.testCfg = paramArrayOfGraphicsConfiguration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 292 */       threadWaiting = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 298 */       if (Thread.currentThread() instanceof BehaviorScheduler) {
/* 299 */         VirtualUniverse.mc.sendRenderMessage(paramArrayOfGraphicsConfiguration[0], this, MasterControl.GETBESTCONFIG);
/*     */       } else {
/*     */         
/* 302 */         VirtualUniverse.mc.postRequest(MasterControl.GETBESTCONFIG, this);
/*     */       } 
/* 304 */       runMonitor(0);
/* 305 */       return (GraphicsConfiguration)this.testCfg;
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
/*     */ 
/*     */   
/*     */   public boolean isGraphicsConfigSupported(GraphicsConfiguration paramGraphicsConfiguration) {
/* 322 */     if (paramGraphicsConfiguration == null) {
/* 323 */       return false;
/*     */     }
/*     */     
/* 326 */     synchronized (globalLock) {
/* 327 */       this.testCfg = paramGraphicsConfiguration;
/* 328 */       threadWaiting = true;
/* 329 */       if (Thread.currentThread() instanceof BehaviorScheduler) {
/* 330 */         VirtualUniverse.mc.sendRenderMessage(paramGraphicsConfiguration, this, MasterControl.ISCONFIGSUPPORT);
/*     */       } else {
/* 332 */         VirtualUniverse.mc.postRequest(MasterControl.ISCONFIGSUPPORT, this);
/*     */       } 
/* 334 */       runMonitor(0);
/* 335 */       return ((Boolean)this.testCfg).booleanValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void getGraphicsConfigFeatures(Canvas3D paramCanvas3D) {
/* 344 */     synchronized (globalLock) {
/* 345 */       threadWaiting = true;
/* 346 */       if (Thread.currentThread() instanceof BehaviorScheduler) {
/* 347 */         VirtualUniverse.mc.sendRenderMessage(paramCanvas3D.graphicsConfiguration, paramCanvas3D, MasterControl.SET_GRAPHICSCONFIG_FEATURES);
/*     */       } else {
/*     */         
/* 350 */         VirtualUniverse.mc.postRequest(MasterControl.SET_GRAPHICSCONFIG_FEATURES, paramCanvas3D);
/*     */       } 
/* 352 */       runMonitor(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void setQueryProps(Canvas3D paramCanvas3D) {
/* 362 */     synchronized (globalLock) {
/* 363 */       threadWaiting = true;
/* 364 */       if (Thread.currentThread() instanceof BehaviorScheduler) {
/* 365 */         VirtualUniverse.mc.sendRenderMessage(paramCanvas3D.graphicsConfiguration, paramCanvas3D, MasterControl.SET_QUERYPROPERTIES);
/*     */       } else {
/*     */         
/* 368 */         VirtualUniverse.mc.postRequest(MasterControl.SET_QUERYPROPERTIES, paramCanvas3D);
/*     */       } 
/* 370 */       runMonitor(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void runMonitor(int paramInt) {
/* 379 */     synchronized (monitorLock) {
/* 380 */       switch (paramInt) {
/*     */         
/*     */         case 0:
/* 383 */           while (threadWaiting) {
/*     */             try {
/* 385 */               monitorLock.wait();
/* 386 */             } catch (InterruptedException interruptedException) {
/* 387 */               System.err.println(interruptedException);
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 2:
/* 392 */           monitorLock.notify();
/* 393 */           threadWaiting = false;
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String enumStr(int paramInt) {
/* 403 */     switch (paramInt) {
/*     */       case 1:
/* 405 */         return "REQUIRED";
/*     */       case 2:
/* 407 */         return "PREFERRED";
/*     */       case 3:
/* 409 */         return "UNNECESSARY";
/*     */     } 
/*     */     
/* 412 */     return "UNDEFINED";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 420 */   public String toString() { return "redSize : " + this.redSize + ", " + "greenSize : " + this.greenSize + ", " + "blueSize : " + this.blueSize + ", " + "depthSize : " + this.depthSize + ", " + "doubleBuffer : " + enumStr(this.doubleBuffer) + ", " + "sceneAntialiasing : " + enumStr(this.sceneAntialiasing) + ", " + "stereo : " + enumStr(this.stereo); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\GraphicsConfigTemplate3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */