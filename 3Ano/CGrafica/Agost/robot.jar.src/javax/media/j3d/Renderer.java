/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class Renderer
/*      */   extends J3dThread
/*      */ {
/*      */   static final int WAIT = 0;
/*      */   static final int NOTIFY_AND_WAIT = 1;
/*      */   static final int NOTIFY = 2;
/*      */   static final int DECAL_NONE = 0;
/*      */   static final int DECAL_1ST_CHILD = 1;
/*      */   static final int DECAL_NTH_CHILD = 2;
/*      */   static final int NUM_ACCUMULATION_SAMPLES = 8;
/*   47 */   static final float[] ACCUM_SAMPLES_X = { -0.54818F, 0.56438F, 0.39462F, -0.54498F, -0.8379F, -0.39263F, 0.32254F, 0.84216F };
/*      */ 
/*      */ 
/*      */   
/*   51 */   static final float[] ACCUM_SAMPLES_Y = { 0.55331F, -0.53495F, 0.4154F, -0.52829F, 0.82102F, -0.27383F, 0.09133F, -0.84399F };
/*      */ 
/*      */   
/*      */   static final float accumValue = 0.125F;
/*      */   
/*      */   static final int RENDER = 0;
/*      */   
/*      */   static final int SWAP = 1;
/*      */   
/*      */   static final int REQUESTRENDER = 2;
/*      */   
/*      */   static final int REQUESTCLEANUP = 3;
/*      */   
/*   64 */   RendererStructure rendererStructure = new RendererStructure();
/*      */ 
/*      */ 
/*      */   
/*   68 */   Transform3D bgVworldToVpc = new Transform3D();
/*      */   
/*   70 */   private static int numInstances = 0;
/*   71 */   private int instanceNum = -1;
/*      */ 
/*      */   
/*      */   boolean sharedStereoZBuffer;
/*      */ 
/*      */   
/*   77 */   Context sharedCtx = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   82 */   long sharedCtxTimeStamp = 0L;
/*      */ 
/*      */   
/*   85 */   private long sharedCtxDisplay = 0L;
/*   86 */   private Drawable sharedCtxDrawable = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   91 */   Context currentCtx = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   96 */   Drawable currentDrawable = null;
/*      */ 
/*      */   
/*   99 */   int rendererBit = 0;
/*      */   
/*  101 */   int rendererId = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  106 */   ArrayList dirtyRenderMoleculeList = new ArrayList();
/*      */ 
/*      */   
/*  109 */   ArrayList dirtyRenderAtomList = new ArrayList();
/*      */ 
/*      */   
/*  112 */   ArrayList dirtyDlistPerRinfoList = new ArrayList();
/*      */ 
/*      */ 
/*      */   
/*  116 */   ArrayList textureIdResourceFreeList = new ArrayList();
/*  117 */   ArrayList displayListResourceFreeList = new ArrayList();
/*      */ 
/*      */   
/*  120 */   ArrayList textureReloadList = new ArrayList();
/*      */ 
/*      */   
/*      */   J3dMessage[] renderMessage;
/*      */ 
/*      */   
/*      */   Screen3D onScreen;
/*      */ 
/*      */   
/*      */   Screen3D offScreen;
/*      */ 
/*      */   
/*  132 */   Transform3D accumLeftProj = new Transform3D();
/*  133 */   Transform3D accumRightProj = new Transform3D();
/*  134 */   Transform3D accumInfLeftProj = new Transform3D();
/*  135 */   Transform3D accumInfRightProj = new Transform3D();
/*      */   
/*      */   J3dMessage[] m;
/*      */   
/*  139 */   int nmesg = 0;
/*      */ 
/*      */   
/*  142 */   ArrayList<Context> listOfCtxs = new ArrayList();
/*      */ 
/*      */   
/*  145 */   ArrayList<Canvas3D> listOfCanvases = new ArrayList();
/*      */ 
/*      */   
/*      */   boolean needToRebuildDisplayList = false;
/*      */ 
/*      */   
/*      */   boolean needToResendTextureDown = false;
/*      */ 
/*      */   
/*      */   boolean dirtyDisplayList = false;
/*      */ 
/*      */   
/*  157 */   ArrayList textureIDResourceTable = new ArrayList(5);
/*      */ 
/*      */   
/*  160 */   private long lastSwapTime = System.nanoTime();
/*      */ 
/*      */   
/*  163 */   private int newInstanceNum() { return ++numInstances; }
/*      */ 
/*      */   
/*      */   int getInstanceNum() {
/*  167 */     if (this.instanceNum == -1)
/*  168 */       this.instanceNum = newInstanceNum(); 
/*  169 */     return this.instanceNum;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Renderer(ThreadGroup paramThreadGroup) {
/*  176 */     super(paramThreadGroup);
/*  177 */     setName("J3D-Renderer-" + getInstanceNum());
/*      */     
/*  179 */     this.type = 16;
/*  180 */     this.rendererId = VirtualUniverse.mc.getRendererId();
/*  181 */     this.rendererBit = 1 << this.rendererId;
/*  182 */     this.renderMessage = new J3dMessage[1];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void doWork(long paramLong) { // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore #4
/*      */     //   3: aconst_null
/*      */     //   4: astore #6
/*      */     //   6: aconst_null
/*      */     //   7: astore #8
/*      */     //   9: iconst_1
/*      */     //   10: istore #13
/*      */     //   12: iconst_0
/*      */     //   13: istore #19
/*      */     //   15: dconst_0
/*      */     //   16: dstore #20
/*      */     //   18: dconst_0
/*      */     //   19: dstore #22
/*      */     //   21: dconst_1
/*      */     //   22: dstore #24
/*      */     //   24: dconst_1
/*      */     //   25: dstore #26
/*      */     //   27: dconst_0
/*      */     //   28: dstore #28
/*      */     //   30: dconst_0
/*      */     //   31: dstore #30
/*      */     //   33: dconst_0
/*      */     //   34: dstore #32
/*      */     //   36: dconst_0
/*      */     //   37: dstore #34
/*      */     //   39: dconst_0
/*      */     //   40: dstore #36
/*      */     //   42: dconst_0
/*      */     //   43: dstore #38
/*      */     //   45: dconst_0
/*      */     //   46: dstore #40
/*      */     //   48: dconst_0
/*      */     //   49: dstore #42
/*      */     //   51: iconst_0
/*      */     //   52: istore #46
/*      */     //   54: aconst_null
/*      */     //   55: astore #47
/*      */     //   57: aload_0
/*      */     //   58: getfield args : [Ljava/lang/Object;
/*      */     //   61: iconst_0
/*      */     //   62: aaload
/*      */     //   63: checkcast java/lang/Integer
/*      */     //   66: invokevirtual intValue : ()I
/*      */     //   69: istore #44
/*      */     //   71: iload #44
/*      */     //   73: iconst_1
/*      */     //   74: if_icmpne -> 635
/*      */     //   77: aload_0
/*      */     //   78: getfield args : [Ljava/lang/Object;
/*      */     //   81: iconst_2
/*      */     //   82: aaload
/*      */     //   83: checkcast [Ljava/lang/Object;
/*      */     //   86: checkcast [Ljava/lang/Object;
/*      */     //   89: astore #48
/*      */     //   91: aload_0
/*      */     //   92: getfield args : [Ljava/lang/Object;
/*      */     //   95: iconst_3
/*      */     //   96: aaload
/*      */     //   97: checkcast javax/media/j3d/View
/*      */     //   100: astore #8
/*      */     //   102: iconst_0
/*      */     //   103: istore #16
/*      */     //   105: iload #16
/*      */     //   107: aload #48
/*      */     //   109: arraylength
/*      */     //   110: if_icmpge -> 606
/*      */     //   113: aload #48
/*      */     //   115: iload #16
/*      */     //   117: aaload
/*      */     //   118: checkcast javax/media/j3d/Canvas3D
/*      */     //   121: astore #5
/*      */     //   123: aload #5
/*      */     //   125: getfield isRunning : Z
/*      */     //   128: ifne -> 134
/*      */     //   131: goto -> 600
/*      */     //   134: aload #5
/*      */     //   136: getfield validCanvas : Z
/*      */     //   139: ifne -> 145
/*      */     //   142: goto -> 600
/*      */     //   145: aload #5
/*      */     //   147: getfield active : Z
/*      */     //   150: ifeq -> 501
/*      */     //   153: aload #5
/*      */     //   155: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   158: ifnull -> 501
/*      */     //   161: aload #5
/*      */     //   163: getfield view : Ljavax/media/j3d/View;
/*      */     //   166: ifnull -> 501
/*      */     //   169: aload #5
/*      */     //   171: getfield imageReady : Z
/*      */     //   174: ifeq -> 501
/*      */     //   177: aload #5
/*      */     //   179: getfield useDoubleBuffer : Z
/*      */     //   182: ifeq -> 359
/*      */     //   185: aload #5
/*      */     //   187: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   190: dup
/*      */     //   191: astore #49
/*      */     //   193: monitorenter
/*      */     //   194: aload #5
/*      */     //   196: getfield validCtx : Z
/*      */     //   199: ifeq -> 345
/*      */     //   202: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   205: getfield doDsiRenderLock : Z
/*      */     //   208: ifeq -> 292
/*      */     //   211: aload #5
/*      */     //   213: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   216: invokevirtual renderLock : ()Z
/*      */     //   219: ifne -> 228
/*      */     //   222: aload #49
/*      */     //   224: monitorexit
/*      */     //   225: goto -> 595
/*      */     //   228: aload #5
/*      */     //   230: invokevirtual makeCtxCurrent : ()V
/*      */     //   233: aload #5
/*      */     //   235: aload #5
/*      */     //   237: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   240: iconst_1
/*      */     //   241: invokevirtual syncRender : (Ljavax/media/j3d/Context;Z)V
/*      */     //   244: aload #5
/*      */     //   246: aload #5
/*      */     //   248: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   251: aload #5
/*      */     //   253: getfield screen : Ljavax/media/j3d/Screen3D;
/*      */     //   256: getfield display : J
/*      */     //   259: aload #5
/*      */     //   261: getfield drawable : Ljavax/media/j3d/Drawable;
/*      */     //   264: invokevirtual swapBuffers : (Ljavax/media/j3d/Context;JLjavax/media/j3d/Drawable;)I
/*      */     //   267: istore #45
/*      */     //   269: iload #45
/*      */     //   271: ifeq -> 281
/*      */     //   274: aload #5
/*      */     //   276: iload #45
/*      */     //   278: invokevirtual resetRendering : (I)V
/*      */     //   281: aload #5
/*      */     //   283: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   286: invokevirtual unLock : ()V
/*      */     //   289: goto -> 345
/*      */     //   292: aload #5
/*      */     //   294: invokevirtual makeCtxCurrent : ()V
/*      */     //   297: aload #5
/*      */     //   299: aload #5
/*      */     //   301: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   304: iconst_1
/*      */     //   305: invokevirtual syncRender : (Ljavax/media/j3d/Context;Z)V
/*      */     //   308: aload #5
/*      */     //   310: aload #5
/*      */     //   312: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   315: aload #5
/*      */     //   317: getfield screen : Ljavax/media/j3d/Screen3D;
/*      */     //   320: getfield display : J
/*      */     //   323: aload #5
/*      */     //   325: getfield drawable : Ljavax/media/j3d/Drawable;
/*      */     //   328: invokevirtual swapBuffers : (Ljavax/media/j3d/Context;JLjavax/media/j3d/Drawable;)I
/*      */     //   331: istore #45
/*      */     //   333: iload #45
/*      */     //   335: ifeq -> 345
/*      */     //   338: aload #5
/*      */     //   340: iload #45
/*      */     //   342: invokevirtual resetRendering : (I)V
/*      */     //   345: aload #49
/*      */     //   347: monitorexit
/*      */     //   348: goto -> 359
/*      */     //   351: astore #50
/*      */     //   353: aload #49
/*      */     //   355: monitorexit
/*      */     //   356: aload #50
/*      */     //   358: athrow
/*      */     //   359: aload #5
/*      */     //   361: getfield view : Ljavax/media/j3d/View;
/*      */     //   364: iconst_1
/*      */     //   365: putfield inCanvasCallback : Z
/*      */     //   368: aload #5
/*      */     //   370: invokevirtual postSwap : ()V
/*      */     //   373: goto -> 409
/*      */     //   376: astore #49
/*      */     //   378: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*      */     //   381: ldc 'Exception occurred during Canvas3D callback:'
/*      */     //   383: invokevirtual println : (Ljava/lang/String;)V
/*      */     //   386: aload #49
/*      */     //   388: invokevirtual printStackTrace : ()V
/*      */     //   391: goto -> 409
/*      */     //   394: astore #49
/*      */     //   396: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*      */     //   399: ldc 'Error occurred during Canvas3D callback:'
/*      */     //   401: invokevirtual println : (Ljava/lang/String;)V
/*      */     //   404: aload #49
/*      */     //   406: invokevirtual printStackTrace : ()V
/*      */     //   409: aload #5
/*      */     //   411: iconst_0
/*      */     //   412: putfield imageReady : Z
/*      */     //   415: aload #5
/*      */     //   417: getfield view : Ljavax/media/j3d/View;
/*      */     //   420: iconst_0
/*      */     //   421: putfield inCanvasCallback : Z
/*      */     //   424: getstatic java/util/logging/Level.INFO : Ljava/util/logging/Level;
/*      */     //   427: invokestatic isStatsLoggable : (Ljava/util/logging/Level;)Z
/*      */     //   430: ifeq -> 464
/*      */     //   433: invokestatic nanoTime : ()J
/*      */     //   436: lstore #49
/*      */     //   438: lload #49
/*      */     //   440: aload_0
/*      */     //   441: getfield lastSwapTime : J
/*      */     //   444: lsub
/*      */     //   445: lstore #51
/*      */     //   447: aload_0
/*      */     //   448: lload #49
/*      */     //   450: putfield lastSwapTime : J
/*      */     //   453: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   456: getstatic javax/media/j3d/MasterControl$TimeType.TOTAL_FRAME : Ljavax/media/j3d/MasterControl$TimeType;
/*      */     //   459: lload #51
/*      */     //   461: invokevirtual recordTime : (Ljavax/media/j3d/MasterControl$TimeType;J)V
/*      */     //   464: aload #5
/*      */     //   466: getfield ctxChanged : Z
/*      */     //   469: ifne -> 495
/*      */     //   472: aload #5
/*      */     //   474: sipush #7743
/*      */     //   477: putfield canvasDirty : I
/*      */     //   480: aload #5
/*      */     //   482: aconst_null
/*      */     //   483: putfield modelMatrix : Ljavax/media/j3d/Transform3D;
/*      */     //   486: aload #5
/*      */     //   488: aconst_null
/*      */     //   489: putfield ra : Ljavax/media/j3d/RenderAtom;
/*      */     //   492: goto -> 501
/*      */     //   495: aload #5
/*      */     //   497: iconst_0
/*      */     //   498: putfield ctxChanged : Z
/*      */     //   501: goto -> 595
/*      */     //   504: astore #49
/*      */     //   506: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   509: getfield doDsiRenderLock : Z
/*      */     //   512: ifeq -> 523
/*      */     //   515: aload #5
/*      */     //   517: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   520: invokevirtual unLock : ()V
/*      */     //   523: goto -> 595
/*      */     //   526: astore #49
/*      */     //   528: aload #49
/*      */     //   530: invokevirtual printStackTrace : ()V
/*      */     //   533: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   536: getfield doDsiRenderLock : Z
/*      */     //   539: ifeq -> 550
/*      */     //   542: aload #5
/*      */     //   544: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   547: invokevirtual unLock : ()V
/*      */     //   550: aload #5
/*      */     //   552: invokevirtual setFatalError : ()V
/*      */     //   555: new javax/media/j3d/RenderingError
/*      */     //   558: dup
/*      */     //   559: iconst_1
/*      */     //   560: ldc 'Renderer0'
/*      */     //   562: invokestatic getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   565: invokespecial <init> : (ILjava/lang/String;)V
/*      */     //   568: astore #50
/*      */     //   570: aload #50
/*      */     //   572: aload #5
/*      */     //   574: invokevirtual setCanvas3D : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   577: aload #50
/*      */     //   579: aload #5
/*      */     //   581: getfield graphicsConfiguration : Ljava/awt/GraphicsConfiguration;
/*      */     //   584: invokevirtual getDevice : ()Ljava/awt/GraphicsDevice;
/*      */     //   587: invokevirtual setGraphicsDevice : (Ljava/awt/GraphicsDevice;)V
/*      */     //   590: aload #50
/*      */     //   592: invokestatic notifyErrorListeners : (Ljavax/media/j3d/RenderingError;)V
/*      */     //   595: aload #5
/*      */     //   597: invokevirtual releaseCtx : ()V
/*      */     //   600: iinc #16, 1
/*      */     //   603: goto -> 105
/*      */     //   606: aload #8
/*      */     //   608: ifnull -> 632
/*      */     //   611: aload #8
/*      */     //   613: getfield viewCache : Ljavax/media/j3d/ViewCache;
/*      */     //   616: invokevirtual getDoHeadTracking : ()Z
/*      */     //   619: ifeq -> 632
/*      */     //   622: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   625: aload #8
/*      */     //   627: bipush #16
/*      */     //   629: invokevirtual sendRunMessage : (Ljavax/media/j3d/View;I)V
/*      */     //   632: goto -> 6356
/*      */     //   635: iload #44
/*      */     //   637: iconst_3
/*      */     //   638: if_icmpne -> 847
/*      */     //   641: aload_0
/*      */     //   642: getfield args : [Ljava/lang/Object;
/*      */     //   645: iconst_2
/*      */     //   646: aaload
/*      */     //   647: checkcast java/lang/Integer
/*      */     //   650: astore #48
/*      */     //   652: aload #48
/*      */     //   654: getstatic javax/media/j3d/MasterControl.REMOVEALLCTXS_CLEANUP : Ljava/lang/Integer;
/*      */     //   657: if_acmpne -> 667
/*      */     //   660: aload_0
/*      */     //   661: invokevirtual removeAllCtxs : ()V
/*      */     //   664: goto -> 846
/*      */     //   667: aload #48
/*      */     //   669: getstatic javax/media/j3d/MasterControl.FREECONTEXT_CLEANUP : Ljava/lang/Integer;
/*      */     //   672: if_acmpne -> 716
/*      */     //   675: aload_0
/*      */     //   676: getfield args : [Ljava/lang/Object;
/*      */     //   679: iconst_1
/*      */     //   680: aaload
/*      */     //   681: checkcast javax/media/j3d/Canvas3D
/*      */     //   684: astore #5
/*      */     //   686: aload_0
/*      */     //   687: aload #5
/*      */     //   689: aload #5
/*      */     //   691: getfield screen : Ljavax/media/j3d/Screen3D;
/*      */     //   694: getfield display : J
/*      */     //   697: aload #5
/*      */     //   699: getfield drawable : Ljavax/media/j3d/Drawable;
/*      */     //   702: aload #5
/*      */     //   704: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   707: iconst_1
/*      */     //   708: iconst_1
/*      */     //   709: iconst_0
/*      */     //   710: invokespecial removeCtx : (Ljavax/media/j3d/Canvas3D;JLjavax/media/j3d/Drawable;Ljavax/media/j3d/Context;ZZZ)V
/*      */     //   713: goto -> 846
/*      */     //   716: aload #48
/*      */     //   718: getstatic javax/media/j3d/MasterControl.RESETCANVAS_CLEANUP : Ljava/lang/Integer;
/*      */     //   721: if_acmpne -> 770
/*      */     //   724: aload_0
/*      */     //   725: getfield args : [Ljava/lang/Object;
/*      */     //   728: iconst_1
/*      */     //   729: aaload
/*      */     //   730: checkcast javax/media/j3d/Canvas3D
/*      */     //   733: astore #5
/*      */     //   735: aload #5
/*      */     //   737: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   740: ifnull -> 748
/*      */     //   743: aload #5
/*      */     //   745: invokevirtual makeCtxCurrent : ()V
/*      */     //   748: aload #5
/*      */     //   750: aload #5
/*      */     //   752: getfield screen : Ljavax/media/j3d/Screen3D;
/*      */     //   755: getfield renderer : Ljavax/media/j3d/Renderer;
/*      */     //   758: iconst_1
/*      */     //   759: aload #5
/*      */     //   761: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   764: invokevirtual freeContextResources : (Ljavax/media/j3d/Renderer;ZLjavax/media/j3d/Context;)V
/*      */     //   767: goto -> 846
/*      */     //   770: aload #48
/*      */     //   772: getstatic javax/media/j3d/MasterControl.REMOVECTX_CLEANUP : Ljava/lang/Integer;
/*      */     //   775: if_acmpne -> 846
/*      */     //   778: aload_0
/*      */     //   779: getfield args : [Ljava/lang/Object;
/*      */     //   782: iconst_1
/*      */     //   783: aaload
/*      */     //   784: checkcast [Ljava/lang/Object;
/*      */     //   787: checkcast [Ljava/lang/Object;
/*      */     //   790: astore #49
/*      */     //   792: aload #49
/*      */     //   794: iconst_0
/*      */     //   795: aaload
/*      */     //   796: checkcast javax/media/j3d/Canvas3D
/*      */     //   799: astore #50
/*      */     //   801: aload_0
/*      */     //   802: aload #50
/*      */     //   804: aload #49
/*      */     //   806: iconst_1
/*      */     //   807: aaload
/*      */     //   808: checkcast java/lang/Long
/*      */     //   811: invokevirtual longValue : ()J
/*      */     //   814: aload #49
/*      */     //   816: iconst_2
/*      */     //   817: aaload
/*      */     //   818: checkcast javax/media/j3d/Drawable
/*      */     //   821: aload #49
/*      */     //   823: iconst_3
/*      */     //   824: aaload
/*      */     //   825: checkcast javax/media/j3d/Context
/*      */     //   828: iconst_0
/*      */     //   829: aload #50
/*      */     //   831: getfield offScreen : Z
/*      */     //   834: ifne -> 841
/*      */     //   837: iconst_1
/*      */     //   838: goto -> 842
/*      */     //   841: iconst_0
/*      */     //   842: iconst_0
/*      */     //   843: invokespecial removeCtx : (Ljavax/media/j3d/Canvas3D;JLjavax/media/j3d/Drawable;Ljavax/media/j3d/Context;ZZZ)V
/*      */     //   846: return
/*      */     //   847: aload_0
/*      */     //   848: iconst_0
/*      */     //   849: putfield nmesg : I
/*      */     //   852: iconst_0
/*      */     //   853: istore #49
/*      */     //   855: iload #44
/*      */     //   857: ifne -> 953
/*      */     //   860: aload_0
/*      */     //   861: aload_0
/*      */     //   862: getfield renderMessage : [Ljavax/media/j3d/J3dMessage;
/*      */     //   865: putfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   868: aload_0
/*      */     //   869: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   872: iconst_0
/*      */     //   873: new javax/media/j3d/J3dMessage
/*      */     //   876: dup
/*      */     //   877: invokespecial <init> : ()V
/*      */     //   880: aastore
/*      */     //   881: aload_0
/*      */     //   882: getfield args : [Ljava/lang/Object;
/*      */     //   885: iconst_1
/*      */     //   886: aaload
/*      */     //   887: checkcast javax/media/j3d/Canvas3D
/*      */     //   890: getfield offScreen : Z
/*      */     //   893: ifeq -> 910
/*      */     //   896: aload_0
/*      */     //   897: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   900: iconst_0
/*      */     //   901: aaload
/*      */     //   902: bipush #42
/*      */     //   904: putfield type : I
/*      */     //   907: goto -> 921
/*      */     //   910: aload_0
/*      */     //   911: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   914: iconst_0
/*      */     //   915: aaload
/*      */     //   916: bipush #43
/*      */     //   918: putfield type : I
/*      */     //   921: aload_0
/*      */     //   922: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   925: iconst_0
/*      */     //   926: aaload
/*      */     //   927: invokevirtual incRefcount : ()V
/*      */     //   930: aload_0
/*      */     //   931: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   934: iconst_0
/*      */     //   935: aaload
/*      */     //   936: getfield args : [Ljava/lang/Object;
/*      */     //   939: iconst_0
/*      */     //   940: aload_0
/*      */     //   941: getfield args : [Ljava/lang/Object;
/*      */     //   944: iconst_1
/*      */     //   945: aaload
/*      */     //   946: aastore
/*      */     //   947: iconst_1
/*      */     //   948: istore #49
/*      */     //   950: goto -> 979
/*      */     //   953: aload_0
/*      */     //   954: aload_0
/*      */     //   955: getfield rendererStructure : Ljavax/media/j3d/RendererStructure;
/*      */     //   958: invokevirtual getMessages : ()[Ljavax/media/j3d/J3dMessage;
/*      */     //   961: putfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   964: aload_0
/*      */     //   965: getfield rendererStructure : Ljavax/media/j3d/RendererStructure;
/*      */     //   968: invokevirtual getNumMessage : ()I
/*      */     //   971: istore #49
/*      */     //   973: iload #49
/*      */     //   975: ifgt -> 979
/*      */     //   978: return
/*      */     //   979: aload_0
/*      */     //   980: getfield nmesg : I
/*      */     //   983: iload #49
/*      */     //   985: if_icmpge -> 6330
/*      */     //   988: aload_0
/*      */     //   989: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   992: aload_0
/*      */     //   993: getfield nmesg : I
/*      */     //   996: aaload
/*      */     //   997: getfield args : [Ljava/lang/Object;
/*      */     //   1000: iconst_0
/*      */     //   1001: aaload
/*      */     //   1002: astore #7
/*      */     //   1004: aload #7
/*      */     //   1006: ifnonnull -> 1659
/*      */     //   1009: aload_0
/*      */     //   1010: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   1013: aload_0
/*      */     //   1014: getfield nmesg : I
/*      */     //   1017: aaload
/*      */     //   1018: getfield args : [Ljava/lang/Object;
/*      */     //   1021: iconst_1
/*      */     //   1022: aaload
/*      */     //   1023: astore #50
/*      */     //   1025: aload #50
/*      */     //   1027: instanceof javax/media/j3d/Canvas3D
/*      */     //   1030: ifeq -> 1365
/*      */     //   1033: aload_0
/*      */     //   1034: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   1037: aload_0
/*      */     //   1038: getfield nmesg : I
/*      */     //   1041: aaload
/*      */     //   1042: getfield args : [Ljava/lang/Object;
/*      */     //   1045: iconst_2
/*      */     //   1046: aaload
/*      */     //   1047: checkcast java/lang/Integer
/*      */     //   1050: astore #51
/*      */     //   1052: aload #50
/*      */     //   1054: checkcast javax/media/j3d/Canvas3D
/*      */     //   1057: astore #52
/*      */     //   1059: aload #51
/*      */     //   1061: getstatic javax/media/j3d/MasterControl.SET_GRAPHICSCONFIG_FEATURES : Ljava/lang/Integer;
/*      */     //   1064: if_acmpne -> 1280
/*      */     //   1067: aload #52
/*      */     //   1069: getfield offScreen : Z
/*      */     //   1072: ifeq -> 1090
/*      */     //   1075: aload #52
/*      */     //   1077: iconst_0
/*      */     //   1078: putfield doubleBufferAvailable : Z
/*      */     //   1081: aload #52
/*      */     //   1083: iconst_0
/*      */     //   1084: putfield stereoAvailable : Z
/*      */     //   1087: goto -> 1110
/*      */     //   1090: aload #52
/*      */     //   1092: aload #52
/*      */     //   1094: invokevirtual hasDoubleBuffer : ()Z
/*      */     //   1097: putfield doubleBufferAvailable : Z
/*      */     //   1100: aload #52
/*      */     //   1102: aload #52
/*      */     //   1104: invokevirtual hasStereo : ()Z
/*      */     //   1107: putfield stereoAvailable : Z
/*      */     //   1110: aload #52
/*      */     //   1112: aload #52
/*      */     //   1114: invokevirtual getStencilSize : ()I
/*      */     //   1117: putfield actualStencilSize : I
/*      */     //   1120: aload #52
/*      */     //   1122: getfield requestedStencilSize : I
/*      */     //   1125: ifle -> 1132
/*      */     //   1128: iconst_1
/*      */     //   1129: goto -> 1133
/*      */     //   1132: iconst_0
/*      */     //   1133: istore #53
/*      */     //   1135: aload #52
/*      */     //   1137: iload #53
/*      */     //   1139: ifeq -> 1154
/*      */     //   1142: aload #52
/*      */     //   1144: getfield actualStencilSize : I
/*      */     //   1147: ifle -> 1154
/*      */     //   1150: iconst_1
/*      */     //   1151: goto -> 1155
/*      */     //   1154: iconst_0
/*      */     //   1155: putfield userStencilAvailable : Z
/*      */     //   1158: aload #52
/*      */     //   1160: iload #53
/*      */     //   1162: ifne -> 1177
/*      */     //   1165: aload #52
/*      */     //   1167: getfield actualStencilSize : I
/*      */     //   1170: ifle -> 1177
/*      */     //   1173: iconst_1
/*      */     //   1174: goto -> 1178
/*      */     //   1177: iconst_0
/*      */     //   1178: putfield systemStencilAvailable : Z
/*      */     //   1181: aload #52
/*      */     //   1183: aload #52
/*      */     //   1185: invokevirtual hasSceneAntialiasingMultisample : ()Z
/*      */     //   1188: putfield sceneAntialiasingMultiSamplesAvailable : Z
/*      */     //   1191: aload #52
/*      */     //   1193: getfield sceneAntialiasingMultiSamplesAvailable : Z
/*      */     //   1196: ifeq -> 1208
/*      */     //   1199: aload #52
/*      */     //   1201: iconst_1
/*      */     //   1202: putfield sceneAntialiasingAvailable : Z
/*      */     //   1205: goto -> 1218
/*      */     //   1208: aload #52
/*      */     //   1210: aload #52
/*      */     //   1212: invokevirtual hasSceneAntialiasingAccum : ()Z
/*      */     //   1215: putfield sceneAntialiasingAvailable : Z
/*      */     //   1218: goto -> 1273
/*      */     //   1221: astore #53
/*      */     //   1223: aload #53
/*      */     //   1225: invokevirtual printStackTrace : ()V
/*      */     //   1228: aload #52
/*      */     //   1230: invokevirtual setFatalError : ()V
/*      */     //   1233: new javax/media/j3d/RenderingError
/*      */     //   1236: dup
/*      */     //   1237: iconst_2
/*      */     //   1238: ldc 'Renderer1'
/*      */     //   1240: invokestatic getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   1243: invokespecial <init> : (ILjava/lang/String;)V
/*      */     //   1246: astore #54
/*      */     //   1248: aload #54
/*      */     //   1250: aload #52
/*      */     //   1252: invokevirtual setCanvas3D : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   1255: aload #54
/*      */     //   1257: aload #52
/*      */     //   1259: getfield graphicsConfiguration : Ljava/awt/GraphicsConfiguration;
/*      */     //   1262: invokevirtual getDevice : ()Ljava/awt/GraphicsDevice;
/*      */     //   1265: invokevirtual setGraphicsDevice : (Ljava/awt/GraphicsDevice;)V
/*      */     //   1268: aload #54
/*      */     //   1270: invokestatic notifyErrorListeners : (Ljavax/media/j3d/RenderingError;)V
/*      */     //   1273: iconst_2
/*      */     //   1274: invokestatic runMonitor : (I)V
/*      */     //   1277: goto -> 1362
/*      */     //   1280: aload #51
/*      */     //   1282: getstatic javax/media/j3d/MasterControl.SET_QUERYPROPERTIES : Ljava/lang/Integer;
/*      */     //   1285: if_acmpne -> 1362
/*      */     //   1288: aload #52
/*      */     //   1290: invokevirtual createQueryContext : ()V
/*      */     //   1293: goto -> 1348
/*      */     //   1296: astore #53
/*      */     //   1298: aload #53
/*      */     //   1300: invokevirtual printStackTrace : ()V
/*      */     //   1303: aload #52
/*      */     //   1305: invokevirtual setFatalError : ()V
/*      */     //   1308: new javax/media/j3d/RenderingError
/*      */     //   1311: dup
/*      */     //   1312: iconst_3
/*      */     //   1313: ldc 'Renderer2'
/*      */     //   1315: invokestatic getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   1318: invokespecial <init> : (ILjava/lang/String;)V
/*      */     //   1321: astore #54
/*      */     //   1323: aload #54
/*      */     //   1325: aload #52
/*      */     //   1327: invokevirtual setCanvas3D : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   1330: aload #54
/*      */     //   1332: aload #52
/*      */     //   1334: getfield graphicsConfiguration : Ljava/awt/GraphicsConfiguration;
/*      */     //   1337: invokevirtual getDevice : ()Ljava/awt/GraphicsDevice;
/*      */     //   1340: invokevirtual setGraphicsDevice : (Ljava/awt/GraphicsDevice;)V
/*      */     //   1343: aload #54
/*      */     //   1345: invokestatic notifyErrorListeners : (Ljavax/media/j3d/RenderingError;)V
/*      */     //   1348: iconst_2
/*      */     //   1349: invokestatic runMonitor : (I)V
/*      */     //   1352: aload_0
/*      */     //   1353: aconst_null
/*      */     //   1354: putfield currentCtx : Ljavax/media/j3d/Context;
/*      */     //   1357: aload_0
/*      */     //   1358: aconst_null
/*      */     //   1359: putfield currentDrawable : Ljavax/media/j3d/Drawable;
/*      */     //   1362: goto -> 1637
/*      */     //   1365: aload #50
/*      */     //   1367: instanceof java/lang/Integer
/*      */     //   1370: ifeq -> 1381
/*      */     //   1373: new java/lang/AssertionError
/*      */     //   1376: dup
/*      */     //   1377: invokespecial <init> : ()V
/*      */     //   1380: athrow
/*      */     //   1381: aload #50
/*      */     //   1383: instanceof javax/media/j3d/GeometryArrayRetained
/*      */     //   1386: ifeq -> 1401
/*      */     //   1389: aload #50
/*      */     //   1391: checkcast javax/media/j3d/GeometryArrayRetained
/*      */     //   1394: iconst_0
/*      */     //   1395: invokevirtual freeD3DArray : (Z)V
/*      */     //   1398: goto -> 1637
/*      */     //   1401: aload #50
/*      */     //   1403: instanceof javax/media/j3d/GraphicsConfigTemplate3D
/*      */     //   1406: ifeq -> 1637
/*      */     //   1409: aload #50
/*      */     //   1411: checkcast javax/media/j3d/GraphicsConfigTemplate3D
/*      */     //   1414: astore #51
/*      */     //   1416: aload_0
/*      */     //   1417: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   1420: aload_0
/*      */     //   1421: getfield nmesg : I
/*      */     //   1424: aaload
/*      */     //   1425: getfield args : [Ljava/lang/Object;
/*      */     //   1428: iconst_2
/*      */     //   1429: aaload
/*      */     //   1430: checkcast java/lang/Integer
/*      */     //   1433: astore #52
/*      */     //   1435: aload #52
/*      */     //   1437: getstatic javax/media/j3d/MasterControl.GETBESTCONFIG : Ljava/lang/Integer;
/*      */     //   1440: if_acmpne -> 1533
/*      */     //   1443: aconst_null
/*      */     //   1444: astore #53
/*      */     //   1446: aload #51
/*      */     //   1448: getfield testCfg : Ljava/lang/Object;
/*      */     //   1451: checkcast [Ljava/awt/GraphicsConfiguration;
/*      */     //   1454: checkcast [Ljava/awt/GraphicsConfiguration;
/*      */     //   1457: astore #54
/*      */     //   1459: invokestatic getPipeline : ()Ljavax/media/j3d/Pipeline;
/*      */     //   1462: aload #51
/*      */     //   1464: aload #54
/*      */     //   1466: invokevirtual getBestConfiguration : (Ljavax/media/j3d/GraphicsConfigTemplate3D;[Ljava/awt/GraphicsConfiguration;)Ljava/awt/GraphicsConfiguration;
/*      */     //   1469: astore #53
/*      */     //   1471: goto -> 1523
/*      */     //   1474: astore #55
/*      */     //   1476: aload #55
/*      */     //   1478: invokevirtual printStackTrace : ()V
/*      */     //   1481: goto -> 1523
/*      */     //   1484: astore #55
/*      */     //   1486: aload #55
/*      */     //   1488: invokevirtual printStackTrace : ()V
/*      */     //   1491: new javax/media/j3d/RenderingError
/*      */     //   1494: dup
/*      */     //   1495: iconst_2
/*      */     //   1496: ldc 'Renderer3'
/*      */     //   1498: invokestatic getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   1501: invokespecial <init> : (ILjava/lang/String;)V
/*      */     //   1504: astore #56
/*      */     //   1506: aload #56
/*      */     //   1508: aload #54
/*      */     //   1510: iconst_0
/*      */     //   1511: aaload
/*      */     //   1512: invokevirtual getDevice : ()Ljava/awt/GraphicsDevice;
/*      */     //   1515: invokevirtual setGraphicsDevice : (Ljava/awt/GraphicsDevice;)V
/*      */     //   1518: aload #56
/*      */     //   1520: invokestatic notifyErrorListeners : (Ljavax/media/j3d/RenderingError;)V
/*      */     //   1523: aload #51
/*      */     //   1525: aload #53
/*      */     //   1527: putfield testCfg : Ljava/lang/Object;
/*      */     //   1530: goto -> 1630
/*      */     //   1533: aload #52
/*      */     //   1535: getstatic javax/media/j3d/MasterControl.ISCONFIGSUPPORT : Ljava/lang/Integer;
/*      */     //   1538: if_acmpne -> 1630
/*      */     //   1541: iconst_0
/*      */     //   1542: istore #53
/*      */     //   1544: aload #51
/*      */     //   1546: getfield testCfg : Ljava/lang/Object;
/*      */     //   1549: checkcast java/awt/GraphicsConfiguration
/*      */     //   1552: astore #54
/*      */     //   1554: invokestatic getPipeline : ()Ljavax/media/j3d/Pipeline;
/*      */     //   1557: aload #51
/*      */     //   1559: aload #54
/*      */     //   1561: invokevirtual isGraphicsConfigSupported : (Ljavax/media/j3d/GraphicsConfigTemplate3D;Ljava/awt/GraphicsConfiguration;)Z
/*      */     //   1564: ifeq -> 1570
/*      */     //   1567: iconst_1
/*      */     //   1568: istore #53
/*      */     //   1570: goto -> 1620
/*      */     //   1573: astore #55
/*      */     //   1575: aload #55
/*      */     //   1577: invokevirtual printStackTrace : ()V
/*      */     //   1580: goto -> 1620
/*      */     //   1583: astore #55
/*      */     //   1585: aload #55
/*      */     //   1587: invokevirtual printStackTrace : ()V
/*      */     //   1590: new javax/media/j3d/RenderingError
/*      */     //   1593: dup
/*      */     //   1594: iconst_2
/*      */     //   1595: ldc 'Renderer4'
/*      */     //   1597: invokestatic getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   1600: invokespecial <init> : (ILjava/lang/String;)V
/*      */     //   1603: astore #56
/*      */     //   1605: aload #56
/*      */     //   1607: aload #54
/*      */     //   1609: invokevirtual getDevice : ()Ljava/awt/GraphicsDevice;
/*      */     //   1612: invokevirtual setGraphicsDevice : (Ljava/awt/GraphicsDevice;)V
/*      */     //   1615: aload #56
/*      */     //   1617: invokestatic notifyErrorListeners : (Ljavax/media/j3d/RenderingError;)V
/*      */     //   1620: aload #51
/*      */     //   1622: iload #53
/*      */     //   1624: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*      */     //   1627: putfield testCfg : Ljava/lang/Object;
/*      */     //   1630: aload #51
/*      */     //   1632: pop
/*      */     //   1633: iconst_2
/*      */     //   1634: invokestatic runMonitor : (I)V
/*      */     //   1637: aload_0
/*      */     //   1638: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   1641: aload_0
/*      */     //   1642: dup
/*      */     //   1643: getfield nmesg : I
/*      */     //   1646: dup_x1
/*      */     //   1647: iconst_1
/*      */     //   1648: iadd
/*      */     //   1649: putfield nmesg : I
/*      */     //   1652: aaload
/*      */     //   1653: invokevirtual decRefcount : ()V
/*      */     //   1656: goto -> 979
/*      */     //   1659: aload #7
/*      */     //   1661: checkcast javax/media/j3d/Canvas3D
/*      */     //   1664: astore #6
/*      */     //   1666: aload_0
/*      */     //   1667: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   1670: aload_0
/*      */     //   1671: getfield nmesg : I
/*      */     //   1674: aaload
/*      */     //   1675: getfield type : I
/*      */     //   1678: istore #48
/*      */     //   1680: iload #48
/*      */     //   1682: bipush #61
/*      */     //   1684: if_icmpne -> 1824
/*      */     //   1687: aload #6
/*      */     //   1689: aconst_null
/*      */     //   1690: putfield drawable : Ljavax/media/j3d/Drawable;
/*      */     //   1693: aload #6
/*      */     //   1695: aload #6
/*      */     //   1697: aconst_null
/*      */     //   1698: aload #6
/*      */     //   1700: getfield screen : Ljavax/media/j3d/Screen3D;
/*      */     //   1703: getfield display : J
/*      */     //   1706: aload #6
/*      */     //   1708: getfield fbConfig : J
/*      */     //   1711: aload #6
/*      */     //   1713: getfield offScreenCanvasSize : Ljava/awt/Dimension;
/*      */     //   1716: getfield width : I
/*      */     //   1719: aload #6
/*      */     //   1721: getfield offScreenCanvasSize : Ljava/awt/Dimension;
/*      */     //   1724: getfield height : I
/*      */     //   1727: invokevirtual createOffScreenBuffer : (Ljavax/media/j3d/Context;JJII)Ljavax/media/j3d/Drawable;
/*      */     //   1730: putfield drawable : Ljavax/media/j3d/Drawable;
/*      */     //   1733: goto -> 1743
/*      */     //   1736: astore #50
/*      */     //   1738: aload #50
/*      */     //   1740: invokevirtual printStackTrace : ()V
/*      */     //   1743: aload #6
/*      */     //   1745: getfield drawable : Ljavax/media/j3d/Drawable;
/*      */     //   1748: ifnonnull -> 1796
/*      */     //   1751: aload #6
/*      */     //   1753: invokevirtual setFatalError : ()V
/*      */     //   1756: new javax/media/j3d/RenderingError
/*      */     //   1759: dup
/*      */     //   1760: iconst_4
/*      */     //   1761: ldc 'Renderer5'
/*      */     //   1763: invokestatic getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   1766: invokespecial <init> : (ILjava/lang/String;)V
/*      */     //   1769: astore #50
/*      */     //   1771: aload #50
/*      */     //   1773: aload #6
/*      */     //   1775: invokevirtual setCanvas3D : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   1778: aload #50
/*      */     //   1780: aload #6
/*      */     //   1782: getfield graphicsConfiguration : Ljava/awt/GraphicsConfiguration;
/*      */     //   1785: invokevirtual getDevice : ()Ljava/awt/GraphicsDevice;
/*      */     //   1788: invokevirtual setGraphicsDevice : (Ljava/awt/GraphicsDevice;)V
/*      */     //   1791: aload #50
/*      */     //   1793: invokestatic notifyErrorListeners : (Ljavax/media/j3d/RenderingError;)V
/*      */     //   1796: aload #6
/*      */     //   1798: iconst_0
/*      */     //   1799: putfield offScreenBufferPending : Z
/*      */     //   1802: aload_0
/*      */     //   1803: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   1806: aload_0
/*      */     //   1807: dup
/*      */     //   1808: getfield nmesg : I
/*      */     //   1811: dup_x1
/*      */     //   1812: iconst_1
/*      */     //   1813: iadd
/*      */     //   1814: putfield nmesg : I
/*      */     //   1817: aaload
/*      */     //   1818: invokevirtual decRefcount : ()V
/*      */     //   1821: goto -> 979
/*      */     //   1824: iload #48
/*      */     //   1826: bipush #62
/*      */     //   1828: if_icmpne -> 1918
/*      */     //   1831: aload_0
/*      */     //   1832: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   1835: aload_0
/*      */     //   1836: getfield nmesg : I
/*      */     //   1839: aaload
/*      */     //   1840: getfield args : [Ljava/lang/Object;
/*      */     //   1843: astore #50
/*      */     //   1845: aload_0
/*      */     //   1846: aload #6
/*      */     //   1848: aload #50
/*      */     //   1850: iconst_1
/*      */     //   1851: aaload
/*      */     //   1852: checkcast java/lang/Long
/*      */     //   1855: invokevirtual longValue : ()J
/*      */     //   1858: aload #50
/*      */     //   1860: iconst_2
/*      */     //   1861: aaload
/*      */     //   1862: checkcast javax/media/j3d/Drawable
/*      */     //   1865: aload #50
/*      */     //   1867: iconst_3
/*      */     //   1868: aaload
/*      */     //   1869: checkcast javax/media/j3d/Context
/*      */     //   1872: iconst_0
/*      */     //   1873: aload #6
/*      */     //   1875: getfield offScreen : Z
/*      */     //   1878: ifne -> 1885
/*      */     //   1881: iconst_1
/*      */     //   1882: goto -> 1886
/*      */     //   1885: iconst_0
/*      */     //   1886: iconst_1
/*      */     //   1887: invokespecial removeCtx : (Ljavax/media/j3d/Canvas3D;JLjavax/media/j3d/Drawable;Ljavax/media/j3d/Context;ZZZ)V
/*      */     //   1890: aload #6
/*      */     //   1892: iconst_0
/*      */     //   1893: putfield offScreenBufferPending : Z
/*      */     //   1896: aload_0
/*      */     //   1897: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   1900: aload_0
/*      */     //   1901: dup
/*      */     //   1902: getfield nmesg : I
/*      */     //   1905: dup_x1
/*      */     //   1906: iconst_1
/*      */     //   1907: iadd
/*      */     //   1908: putfield nmesg : I
/*      */     //   1911: aaload
/*      */     //   1912: invokevirtual decRefcount : ()V
/*      */     //   1915: goto -> 979
/*      */     //   1918: iload #48
/*      */     //   1920: bipush #66
/*      */     //   1922: if_icmpne -> 1933
/*      */     //   1925: aload #6
/*      */     //   1927: invokevirtual allocateCanvasId : ()V
/*      */     //   1930: goto -> 1945
/*      */     //   1933: iload #48
/*      */     //   1935: bipush #67
/*      */     //   1937: if_icmpne -> 1945
/*      */     //   1940: aload #6
/*      */     //   1942: invokevirtual freeCanvasId : ()V
/*      */     //   1945: aload #6
/*      */     //   1947: getfield view : Ljavax/media/j3d/View;
/*      */     //   1950: ifnull -> 1961
/*      */     //   1953: aload #6
/*      */     //   1955: getfield firstPaintCalled : Z
/*      */     //   1958: ifne -> 1996
/*      */     //   1961: iload #48
/*      */     //   1963: bipush #42
/*      */     //   1965: if_icmpne -> 1974
/*      */     //   1968: aload #6
/*      */     //   1970: iconst_0
/*      */     //   1971: putfield offScreenRendering : Z
/*      */     //   1974: aload_0
/*      */     //   1975: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   1978: aload_0
/*      */     //   1979: dup
/*      */     //   1980: getfield nmesg : I
/*      */     //   1983: dup_x1
/*      */     //   1984: iconst_1
/*      */     //   1985: iadd
/*      */     //   1986: putfield nmesg : I
/*      */     //   1989: aaload
/*      */     //   1990: invokevirtual decRefcount : ()V
/*      */     //   1993: goto -> 979
/*      */     //   1996: aload #6
/*      */     //   1998: getfield validCanvas : Z
/*      */     //   2001: ifne -> 2033
/*      */     //   2004: iload #48
/*      */     //   2006: bipush #42
/*      */     //   2008: if_icmpeq -> 2033
/*      */     //   2011: aload_0
/*      */     //   2012: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2015: aload_0
/*      */     //   2016: dup
/*      */     //   2017: getfield nmesg : I
/*      */     //   2020: dup_x1
/*      */     //   2021: iconst_1
/*      */     //   2022: iadd
/*      */     //   2023: putfield nmesg : I
/*      */     //   2026: aaload
/*      */     //   2027: invokevirtual decRefcount : ()V
/*      */     //   2030: goto -> 979
/*      */     //   2033: iload #48
/*      */     //   2035: bipush #52
/*      */     //   2037: if_icmpne -> 2080
/*      */     //   2040: aload #6
/*      */     //   2042: invokevirtual d3dResize : ()V
/*      */     //   2045: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   2048: aload #6
/*      */     //   2050: getfield view : Ljavax/media/j3d/View;
/*      */     //   2053: bipush #16
/*      */     //   2055: invokevirtual sendRunMessage : (Ljavax/media/j3d/View;I)V
/*      */     //   2058: aload_0
/*      */     //   2059: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2062: aload_0
/*      */     //   2063: dup
/*      */     //   2064: getfield nmesg : I
/*      */     //   2067: dup_x1
/*      */     //   2068: iconst_1
/*      */     //   2069: iadd
/*      */     //   2070: putfield nmesg : I
/*      */     //   2073: aaload
/*      */     //   2074: invokevirtual decRefcount : ()V
/*      */     //   2077: goto -> 979
/*      */     //   2080: iload #48
/*      */     //   2082: bipush #53
/*      */     //   2084: if_icmpne -> 2127
/*      */     //   2087: aload #6
/*      */     //   2089: invokevirtual d3dToggle : ()V
/*      */     //   2092: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   2095: aload #6
/*      */     //   2097: getfield view : Ljavax/media/j3d/View;
/*      */     //   2100: bipush #16
/*      */     //   2102: invokevirtual sendRunMessage : (Ljavax/media/j3d/View;I)V
/*      */     //   2105: aload_0
/*      */     //   2106: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2109: aload_0
/*      */     //   2110: dup
/*      */     //   2111: getfield nmesg : I
/*      */     //   2114: dup_x1
/*      */     //   2115: iconst_1
/*      */     //   2116: iadd
/*      */     //   2117: putfield nmesg : I
/*      */     //   2120: aaload
/*      */     //   2121: invokevirtual decRefcount : ()V
/*      */     //   2124: goto -> 979
/*      */     //   2127: iload #48
/*      */     //   2129: bipush #44
/*      */     //   2131: if_icmpne -> 3240
/*      */     //   2134: aload_0
/*      */     //   2135: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2138: aload_0
/*      */     //   2139: getfield nmesg : I
/*      */     //   2142: aaload
/*      */     //   2143: getfield args : [Ljava/lang/Object;
/*      */     //   2146: iconst_1
/*      */     //   2147: aaload
/*      */     //   2148: checkcast java/lang/Integer
/*      */     //   2151: invokevirtual intValue : ()I
/*      */     //   2154: istore #50
/*      */     //   2156: aload_0
/*      */     //   2157: getfield needToResendTextureDown : Z
/*      */     //   2160: ifeq -> 2180
/*      */     //   2163: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   2166: dup
/*      */     //   2167: getfield resendTexTimestamp : I
/*      */     //   2170: iconst_1
/*      */     //   2171: iadd
/*      */     //   2172: putfield resendTexTimestamp : I
/*      */     //   2175: aload_0
/*      */     //   2176: iconst_0
/*      */     //   2177: putfield needToResendTextureDown : Z
/*      */     //   2180: aload #6
/*      */     //   2182: invokevirtual isFatalError : ()Z
/*      */     //   2185: ifeq -> 2191
/*      */     //   2188: goto -> 979
/*      */     //   2191: aload #6
/*      */     //   2193: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   2196: ifnull -> 2204
/*      */     //   2199: aload #6
/*      */     //   2201: invokevirtual beginScene : ()V
/*      */     //   2204: iload #50
/*      */     //   2206: tableswitch default -> 3150, 0 -> 2328, 1 -> 2339, 2 -> 2367, 3 -> 2375, 4 -> 2403, 5 -> 2431, 6 -> 2459, 7 -> 2487, 8 -> 2535, 9 -> 2583, 10 -> 2614, 11 -> 2642, 12 -> 2670, 13 -> 2702, 14 -> 2734, 15 -> 2782, 16 -> 2830, 17 -> 2861, 18 -> 2889, 19 -> 2917, 20 -> 2948, 21 -> 2979, 22 -> 3010, 23 -> 3041, 24 -> 3052, 25 -> 3122, 26 -> 3111
/*      */     //   2328: aload #6
/*      */     //   2330: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2333: invokevirtual doClear : ()V
/*      */     //   2336: goto -> 3150
/*      */     //   2339: aload #6
/*      */     //   2341: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2344: aload_0
/*      */     //   2345: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2348: aload_0
/*      */     //   2349: getfield nmesg : I
/*      */     //   2352: aaload
/*      */     //   2353: getfield args : [Ljava/lang/Object;
/*      */     //   2356: iconst_2
/*      */     //   2357: aaload
/*      */     //   2358: checkcast javax/media/j3d/Geometry
/*      */     //   2361: invokevirtual doDraw : (Ljavax/media/j3d/Geometry;)V
/*      */     //   2364: goto -> 3150
/*      */     //   2367: aload #6
/*      */     //   2369: invokevirtual doSwap : ()V
/*      */     //   2372: goto -> 3150
/*      */     //   2375: aload #6
/*      */     //   2377: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2380: aload_0
/*      */     //   2381: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2384: aload_0
/*      */     //   2385: getfield nmesg : I
/*      */     //   2388: aaload
/*      */     //   2389: getfield args : [Ljava/lang/Object;
/*      */     //   2392: iconst_2
/*      */     //   2393: aaload
/*      */     //   2394: checkcast javax/media/j3d/Raster
/*      */     //   2397: invokevirtual doReadRaster : (Ljavax/media/j3d/Raster;)V
/*      */     //   2400: goto -> 3150
/*      */     //   2403: aload #6
/*      */     //   2405: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2408: aload_0
/*      */     //   2409: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2412: aload_0
/*      */     //   2413: getfield nmesg : I
/*      */     //   2416: aaload
/*      */     //   2417: getfield args : [Ljava/lang/Object;
/*      */     //   2420: iconst_2
/*      */     //   2421: aaload
/*      */     //   2422: checkcast javax/media/j3d/Appearance
/*      */     //   2425: invokevirtual doSetAppearance : (Ljavax/media/j3d/Appearance;)V
/*      */     //   2428: goto -> 3150
/*      */     //   2431: aload #6
/*      */     //   2433: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2436: aload_0
/*      */     //   2437: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2440: aload_0
/*      */     //   2441: getfield nmesg : I
/*      */     //   2444: aaload
/*      */     //   2445: getfield args : [Ljava/lang/Object;
/*      */     //   2448: iconst_2
/*      */     //   2449: aaload
/*      */     //   2450: checkcast javax/media/j3d/Background
/*      */     //   2453: invokevirtual doSetBackground : (Ljavax/media/j3d/Background;)V
/*      */     //   2456: goto -> 3150
/*      */     //   2459: aload #6
/*      */     //   2461: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2464: aload_0
/*      */     //   2465: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2468: aload_0
/*      */     //   2469: getfield nmesg : I
/*      */     //   2472: aaload
/*      */     //   2473: getfield args : [Ljava/lang/Object;
/*      */     //   2476: iconst_2
/*      */     //   2477: aaload
/*      */     //   2478: checkcast javax/media/j3d/Fog
/*      */     //   2481: invokevirtual doSetFog : (Ljavax/media/j3d/Fog;)V
/*      */     //   2484: goto -> 3150
/*      */     //   2487: aload #6
/*      */     //   2489: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2492: aload_0
/*      */     //   2493: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2496: aload_0
/*      */     //   2497: getfield nmesg : I
/*      */     //   2500: aaload
/*      */     //   2501: getfield args : [Ljava/lang/Object;
/*      */     //   2504: iconst_2
/*      */     //   2505: aaload
/*      */     //   2506: checkcast javax/media/j3d/Light
/*      */     //   2509: aload_0
/*      */     //   2510: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2513: aload_0
/*      */     //   2514: getfield nmesg : I
/*      */     //   2517: aaload
/*      */     //   2518: getfield args : [Ljava/lang/Object;
/*      */     //   2521: iconst_3
/*      */     //   2522: aaload
/*      */     //   2523: checkcast java/lang/Integer
/*      */     //   2526: invokevirtual intValue : ()I
/*      */     //   2529: invokevirtual doSetLight : (Ljavax/media/j3d/Light;I)V
/*      */     //   2532: goto -> 3150
/*      */     //   2535: aload #6
/*      */     //   2537: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2540: aload_0
/*      */     //   2541: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2544: aload_0
/*      */     //   2545: getfield nmesg : I
/*      */     //   2548: aaload
/*      */     //   2549: getfield args : [Ljava/lang/Object;
/*      */     //   2552: iconst_2
/*      */     //   2553: aaload
/*      */     //   2554: checkcast javax/media/j3d/Light
/*      */     //   2557: aload_0
/*      */     //   2558: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2561: aload_0
/*      */     //   2562: getfield nmesg : I
/*      */     //   2565: aaload
/*      */     //   2566: getfield args : [Ljava/lang/Object;
/*      */     //   2569: iconst_3
/*      */     //   2570: aaload
/*      */     //   2571: checkcast java/lang/Integer
/*      */     //   2574: invokevirtual intValue : ()I
/*      */     //   2577: invokevirtual doInsertLight : (Ljavax/media/j3d/Light;I)V
/*      */     //   2580: goto -> 3150
/*      */     //   2583: aload #6
/*      */     //   2585: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2588: aload_0
/*      */     //   2589: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2592: aload_0
/*      */     //   2593: getfield nmesg : I
/*      */     //   2596: aaload
/*      */     //   2597: getfield args : [Ljava/lang/Object;
/*      */     //   2600: iconst_2
/*      */     //   2601: aaload
/*      */     //   2602: checkcast java/lang/Integer
/*      */     //   2605: invokevirtual intValue : ()I
/*      */     //   2608: invokevirtual doRemoveLight : (I)V
/*      */     //   2611: goto -> 3150
/*      */     //   2614: aload #6
/*      */     //   2616: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2619: aload_0
/*      */     //   2620: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2623: aload_0
/*      */     //   2624: getfield nmesg : I
/*      */     //   2627: aaload
/*      */     //   2628: getfield args : [Ljava/lang/Object;
/*      */     //   2631: iconst_2
/*      */     //   2632: aaload
/*      */     //   2633: checkcast javax/media/j3d/Light
/*      */     //   2636: invokevirtual doAddLight : (Ljavax/media/j3d/Light;)V
/*      */     //   2639: goto -> 3150
/*      */     //   2642: aload #6
/*      */     //   2644: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2647: aload_0
/*      */     //   2648: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2651: aload_0
/*      */     //   2652: getfield nmesg : I
/*      */     //   2655: aaload
/*      */     //   2656: getfield args : [Ljava/lang/Object;
/*      */     //   2659: iconst_2
/*      */     //   2660: aaload
/*      */     //   2661: checkcast javax/media/j3d/HiResCoord
/*      */     //   2664: invokevirtual doSetHiRes : (Ljavax/media/j3d/HiResCoord;)V
/*      */     //   2667: goto -> 3150
/*      */     //   2670: aload_0
/*      */     //   2671: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2674: aload_0
/*      */     //   2675: getfield nmesg : I
/*      */     //   2678: aaload
/*      */     //   2679: getfield args : [Ljava/lang/Object;
/*      */     //   2682: iconst_2
/*      */     //   2683: aaload
/*      */     //   2684: checkcast javax/media/j3d/Transform3D
/*      */     //   2687: astore #47
/*      */     //   2689: aload #6
/*      */     //   2691: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2694: aload #47
/*      */     //   2696: invokevirtual doSetModelTransform : (Ljavax/media/j3d/Transform3D;)V
/*      */     //   2699: goto -> 3150
/*      */     //   2702: aload_0
/*      */     //   2703: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2706: aload_0
/*      */     //   2707: getfield nmesg : I
/*      */     //   2710: aaload
/*      */     //   2711: getfield args : [Ljava/lang/Object;
/*      */     //   2714: iconst_2
/*      */     //   2715: aaload
/*      */     //   2716: checkcast javax/media/j3d/Transform3D
/*      */     //   2719: astore #47
/*      */     //   2721: aload #6
/*      */     //   2723: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2726: aload #47
/*      */     //   2728: invokevirtual doMultiplyModelTransform : (Ljavax/media/j3d/Transform3D;)V
/*      */     //   2731: goto -> 3150
/*      */     //   2734: aload #6
/*      */     //   2736: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2739: aload_0
/*      */     //   2740: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2743: aload_0
/*      */     //   2744: getfield nmesg : I
/*      */     //   2747: aaload
/*      */     //   2748: getfield args : [Ljava/lang/Object;
/*      */     //   2751: iconst_2
/*      */     //   2752: aaload
/*      */     //   2753: checkcast javax/media/j3d/Sound
/*      */     //   2756: aload_0
/*      */     //   2757: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2760: aload_0
/*      */     //   2761: getfield nmesg : I
/*      */     //   2764: aaload
/*      */     //   2765: getfield args : [Ljava/lang/Object;
/*      */     //   2768: iconst_3
/*      */     //   2769: aaload
/*      */     //   2770: checkcast java/lang/Integer
/*      */     //   2773: invokevirtual intValue : ()I
/*      */     //   2776: invokevirtual doSetSound : (Ljavax/media/j3d/Sound;I)V
/*      */     //   2779: goto -> 3150
/*      */     //   2782: aload #6
/*      */     //   2784: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2787: aload_0
/*      */     //   2788: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2791: aload_0
/*      */     //   2792: getfield nmesg : I
/*      */     //   2795: aaload
/*      */     //   2796: getfield args : [Ljava/lang/Object;
/*      */     //   2799: iconst_2
/*      */     //   2800: aaload
/*      */     //   2801: checkcast javax/media/j3d/Sound
/*      */     //   2804: aload_0
/*      */     //   2805: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2808: aload_0
/*      */     //   2809: getfield nmesg : I
/*      */     //   2812: aaload
/*      */     //   2813: getfield args : [Ljava/lang/Object;
/*      */     //   2816: iconst_3
/*      */     //   2817: aaload
/*      */     //   2818: checkcast java/lang/Integer
/*      */     //   2821: invokevirtual intValue : ()I
/*      */     //   2824: invokevirtual doInsertSound : (Ljavax/media/j3d/Sound;I)V
/*      */     //   2827: goto -> 3150
/*      */     //   2830: aload #6
/*      */     //   2832: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2835: aload_0
/*      */     //   2836: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2839: aload_0
/*      */     //   2840: getfield nmesg : I
/*      */     //   2843: aaload
/*      */     //   2844: getfield args : [Ljava/lang/Object;
/*      */     //   2847: iconst_2
/*      */     //   2848: aaload
/*      */     //   2849: checkcast java/lang/Integer
/*      */     //   2852: invokevirtual intValue : ()I
/*      */     //   2855: invokevirtual doRemoveSound : (I)V
/*      */     //   2858: goto -> 3150
/*      */     //   2861: aload #6
/*      */     //   2863: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2866: aload_0
/*      */     //   2867: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2870: aload_0
/*      */     //   2871: getfield nmesg : I
/*      */     //   2874: aaload
/*      */     //   2875: getfield args : [Ljava/lang/Object;
/*      */     //   2878: iconst_2
/*      */     //   2879: aaload
/*      */     //   2880: checkcast javax/media/j3d/Sound
/*      */     //   2883: invokevirtual doAddSound : (Ljavax/media/j3d/Sound;)V
/*      */     //   2886: goto -> 3150
/*      */     //   2889: aload #6
/*      */     //   2891: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2894: aload_0
/*      */     //   2895: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2898: aload_0
/*      */     //   2899: getfield nmesg : I
/*      */     //   2902: aaload
/*      */     //   2903: getfield args : [Ljava/lang/Object;
/*      */     //   2906: iconst_2
/*      */     //   2907: aaload
/*      */     //   2908: checkcast javax/media/j3d/AuralAttributes
/*      */     //   2911: invokevirtual doSetAuralAttributes : (Ljavax/media/j3d/AuralAttributes;)V
/*      */     //   2914: goto -> 3150
/*      */     //   2917: aload #6
/*      */     //   2919: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2922: aload_0
/*      */     //   2923: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2926: aload_0
/*      */     //   2927: getfield nmesg : I
/*      */     //   2930: aaload
/*      */     //   2931: getfield args : [Ljava/lang/Object;
/*      */     //   2934: iconst_2
/*      */     //   2935: aaload
/*      */     //   2936: checkcast java/lang/Boolean
/*      */     //   2939: invokevirtual booleanValue : ()Z
/*      */     //   2942: invokevirtual doSetBufferOverride : (Z)V
/*      */     //   2945: goto -> 3150
/*      */     //   2948: aload #6
/*      */     //   2950: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2953: aload_0
/*      */     //   2954: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2957: aload_0
/*      */     //   2958: getfield nmesg : I
/*      */     //   2961: aaload
/*      */     //   2962: getfield args : [Ljava/lang/Object;
/*      */     //   2965: iconst_2
/*      */     //   2966: aaload
/*      */     //   2967: checkcast java/lang/Boolean
/*      */     //   2970: invokevirtual booleanValue : ()Z
/*      */     //   2973: invokevirtual doSetFrontBufferRendering : (Z)V
/*      */     //   2976: goto -> 3150
/*      */     //   2979: aload #6
/*      */     //   2981: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   2984: aload_0
/*      */     //   2985: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   2988: aload_0
/*      */     //   2989: getfield nmesg : I
/*      */     //   2992: aaload
/*      */     //   2993: getfield args : [Ljava/lang/Object;
/*      */     //   2996: iconst_2
/*      */     //   2997: aaload
/*      */     //   2998: checkcast java/lang/Integer
/*      */     //   3001: invokevirtual intValue : ()I
/*      */     //   3004: invokevirtual doSetStereoMode : (I)V
/*      */     //   3007: goto -> 3150
/*      */     //   3010: aload #6
/*      */     //   3012: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   3015: aload_0
/*      */     //   3016: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   3019: aload_0
/*      */     //   3020: getfield nmesg : I
/*      */     //   3023: aaload
/*      */     //   3024: getfield args : [Ljava/lang/Object;
/*      */     //   3027: iconst_2
/*      */     //   3028: aaload
/*      */     //   3029: checkcast java/lang/Boolean
/*      */     //   3032: invokevirtual booleanValue : ()Z
/*      */     //   3035: invokevirtual doFlush : (Z)V
/*      */     //   3038: goto -> 3150
/*      */     //   3041: aload #6
/*      */     //   3043: getfield graphics2D : Ljavax/media/j3d/J3DGraphics2DImpl;
/*      */     //   3046: invokevirtual doFlush : ()V
/*      */     //   3049: goto -> 3150
/*      */     //   3052: aload_0
/*      */     //   3053: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   3056: aload_0
/*      */     //   3057: getfield nmesg : I
/*      */     //   3060: aaload
/*      */     //   3061: getfield args : [Ljava/lang/Object;
/*      */     //   3064: astore #51
/*      */     //   3066: aload #6
/*      */     //   3068: getfield graphics2D : Ljavax/media/j3d/J3DGraphics2DImpl;
/*      */     //   3071: aload #51
/*      */     //   3073: iconst_2
/*      */     //   3074: aaload
/*      */     //   3075: checkcast java/awt/image/BufferedImage
/*      */     //   3078: aload #51
/*      */     //   3080: iconst_3
/*      */     //   3081: aaload
/*      */     //   3082: checkcast java/awt/Point
/*      */     //   3085: getfield x : I
/*      */     //   3088: aload #51
/*      */     //   3090: iconst_3
/*      */     //   3091: aaload
/*      */     //   3092: checkcast java/awt/Point
/*      */     //   3095: getfield y : I
/*      */     //   3098: aload #51
/*      */     //   3100: iconst_4
/*      */     //   3101: aaload
/*      */     //   3102: checkcast java/awt/image/ImageObserver
/*      */     //   3105: invokevirtual doDrawAndFlushImage : (Ljava/awt/image/BufferedImage;IILjava/awt/image/ImageObserver;)V
/*      */     //   3108: goto -> 3150
/*      */     //   3111: aload #6
/*      */     //   3113: getfield graphics2D : Ljavax/media/j3d/J3DGraphics2DImpl;
/*      */     //   3116: invokevirtual doDispose : ()V
/*      */     //   3119: goto -> 3150
/*      */     //   3122: aload #6
/*      */     //   3124: getfield graphicsContext3D : Ljavax/media/j3d/GraphicsContext3D;
/*      */     //   3127: aload_0
/*      */     //   3128: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   3131: aload_0
/*      */     //   3132: getfield nmesg : I
/*      */     //   3135: aaload
/*      */     //   3136: getfield args : [Ljava/lang/Object;
/*      */     //   3139: iconst_2
/*      */     //   3140: aaload
/*      */     //   3141: checkcast javax/media/j3d/ModelClip
/*      */     //   3144: invokevirtual doSetModelClip : (Ljavax/media/j3d/ModelClip;)V
/*      */     //   3147: goto -> 3150
/*      */     //   3150: aload #6
/*      */     //   3152: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   3155: ifnull -> 3163
/*      */     //   3158: aload #6
/*      */     //   3160: invokevirtual endScene : ()V
/*      */     //   3163: goto -> 3218
/*      */     //   3166: astore #51
/*      */     //   3168: aload #51
/*      */     //   3170: invokevirtual printStackTrace : ()V
/*      */     //   3173: aload #6
/*      */     //   3175: invokevirtual setFatalError : ()V
/*      */     //   3178: new javax/media/j3d/RenderingError
/*      */     //   3181: dup
/*      */     //   3182: iconst_3
/*      */     //   3183: ldc 'Renderer6'
/*      */     //   3185: invokestatic getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   3188: invokespecial <init> : (ILjava/lang/String;)V
/*      */     //   3191: astore #52
/*      */     //   3193: aload #52
/*      */     //   3195: aload #6
/*      */     //   3197: invokevirtual setCanvas3D : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   3200: aload #52
/*      */     //   3202: aload #6
/*      */     //   3204: getfield graphicsConfiguration : Ljava/awt/GraphicsConfiguration;
/*      */     //   3207: invokevirtual getDevice : ()Ljava/awt/GraphicsDevice;
/*      */     //   3210: invokevirtual setGraphicsDevice : (Ljava/awt/GraphicsDevice;)V
/*      */     //   3213: aload #52
/*      */     //   3215: invokestatic notifyErrorListeners : (Ljavax/media/j3d/RenderingError;)V
/*      */     //   3218: aload_0
/*      */     //   3219: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   3222: aload_0
/*      */     //   3223: dup
/*      */     //   3224: getfield nmesg : I
/*      */     //   3227: dup_x1
/*      */     //   3228: iconst_1
/*      */     //   3229: iadd
/*      */     //   3230: putfield nmesg : I
/*      */     //   3233: aaload
/*      */     //   3234: invokevirtual decRefcount : ()V
/*      */     //   3237: goto -> 979
/*      */     //   3240: lconst_0
/*      */     //   3241: lstore #50
/*      */     //   3243: getstatic java/util/logging/Level.INFO : Ljava/util/logging/Level;
/*      */     //   3246: invokestatic isStatsLoggable : (Ljava/util/logging/Level;)Z
/*      */     //   3249: ifeq -> 3257
/*      */     //   3252: invokestatic nanoTime : ()J
/*      */     //   3255: lstore #50
/*      */     //   3257: aload_0
/*      */     //   3258: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   3261: aload_0
/*      */     //   3262: dup
/*      */     //   3263: getfield nmesg : I
/*      */     //   3266: dup_x1
/*      */     //   3267: iconst_1
/*      */     //   3268: iadd
/*      */     //   3269: putfield nmesg : I
/*      */     //   3272: aaload
/*      */     //   3273: invokevirtual decRefcount : ()V
/*      */     //   3276: aload #6
/*      */     //   3278: invokevirtual isFatalError : ()Z
/*      */     //   3281: ifeq -> 3287
/*      */     //   3284: goto -> 979
/*      */     //   3287: aconst_null
/*      */     //   3288: astore #52
/*      */     //   3290: iload #48
/*      */     //   3292: bipush #42
/*      */     //   3294: if_icmpne -> 3367
/*      */     //   3297: aload #6
/*      */     //   3299: iconst_1
/*      */     //   3300: putfield offScreenRendering : Z
/*      */     //   3303: aload #6
/*      */     //   3305: getfield drawable : Ljavax/media/j3d/Drawable;
/*      */     //   3308: ifnull -> 3319
/*      */     //   3311: aload #6
/*      */     //   3313: getfield active : Z
/*      */     //   3316: ifne -> 3328
/*      */     //   3319: aload #6
/*      */     //   3321: iconst_0
/*      */     //   3322: putfield offScreenRendering : Z
/*      */     //   3325: goto -> 979
/*      */     //   3328: aload #6
/*      */     //   3330: getfield offScreenBuffer : Ljavax/media/j3d/ImageComponent2D;
/*      */     //   3333: getfield retained : Ljavax/media/j3d/SceneGraphObjectRetained;
/*      */     //   3336: checkcast javax/media/j3d/ImageComponent2DRetained
/*      */     //   3339: astore #52
/*      */     //   3341: aload #52
/*      */     //   3343: invokevirtual isByReference : ()Z
/*      */     //   3346: ifeq -> 3357
/*      */     //   3349: aload #52
/*      */     //   3351: getfield geomLock : Ljavax/media/j3d/GeometryLock;
/*      */     //   3354: invokevirtual getLock : ()V
/*      */     //   3357: aload #52
/*      */     //   3359: aload #6
/*      */     //   3361: invokevirtual evaluateExtensions : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   3364: goto -> 3378
/*      */     //   3367: aload #6
/*      */     //   3369: getfield active : Z
/*      */     //   3372: ifne -> 3378
/*      */     //   3375: goto -> 979
/*      */     //   3378: aload #6
/*      */     //   3380: getfield offScreen : Z
/*      */     //   3383: ifne -> 3394
/*      */     //   3386: aload #6
/*      */     //   3388: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   3391: invokevirtual getDrawingSurfaceObjectInfo : ()V
/*      */     //   3394: aload #6
/*      */     //   3396: getfield view : Ljavax/media/j3d/View;
/*      */     //   3399: getfield renderBin : Ljavax/media/j3d/RenderBin;
/*      */     //   3402: astore #4
/*      */     //   3404: aload #6
/*      */     //   3406: getfield useSharedCtx : Z
/*      */     //   3409: ifeq -> 3647
/*      */     //   3412: aload_0
/*      */     //   3413: getfield sharedCtx : Ljavax/media/j3d/Context;
/*      */     //   3416: ifnonnull -> 3647
/*      */     //   3419: aload_0
/*      */     //   3420: aload #6
/*      */     //   3422: getfield screen : Ljavax/media/j3d/Screen3D;
/*      */     //   3425: getfield display : J
/*      */     //   3428: putfield sharedCtxDisplay : J
/*      */     //   3431: aload_0
/*      */     //   3432: aload #6
/*      */     //   3434: getfield drawable : Ljavax/media/j3d/Drawable;
/*      */     //   3437: putfield sharedCtxDrawable : Ljavax/media/j3d/Drawable;
/*      */     //   3440: aload #6
/*      */     //   3442: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   3445: invokevirtual renderLock : ()Z
/*      */     //   3448: ifne -> 3481
/*      */     //   3451: aload #52
/*      */     //   3453: ifnull -> 3472
/*      */     //   3456: aload #52
/*      */     //   3458: invokevirtual isByReference : ()Z
/*      */     //   3461: ifeq -> 3472
/*      */     //   3464: aload #52
/*      */     //   3466: getfield geomLock : Ljavax/media/j3d/GeometryLock;
/*      */     //   3469: invokevirtual unLock : ()V
/*      */     //   3472: aload #6
/*      */     //   3474: iconst_0
/*      */     //   3475: putfield offScreenRendering : Z
/*      */     //   3478: goto -> 6330
/*      */     //   3481: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   3484: getfield contextCreationLock : Ljava/lang/Object;
/*      */     //   3487: dup
/*      */     //   3488: astore #53
/*      */     //   3490: monitorenter
/*      */     //   3491: aload_0
/*      */     //   3492: aconst_null
/*      */     //   3493: putfield sharedCtx : Ljavax/media/j3d/Context;
/*      */     //   3496: aload_0
/*      */     //   3497: aload #6
/*      */     //   3499: aconst_null
/*      */     //   3500: iconst_1
/*      */     //   3501: invokevirtual createNewContext : (Ljavax/media/j3d/Context;Z)Ljavax/media/j3d/Context;
/*      */     //   3504: putfield sharedCtx : Ljavax/media/j3d/Context;
/*      */     //   3507: goto -> 3517
/*      */     //   3510: astore #54
/*      */     //   3512: aload #54
/*      */     //   3514: invokevirtual printStackTrace : ()V
/*      */     //   3517: aload_0
/*      */     //   3518: getfield sharedCtx : Ljavax/media/j3d/Context;
/*      */     //   3521: ifnonnull -> 3610
/*      */     //   3524: aload #6
/*      */     //   3526: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   3529: invokevirtual unLock : ()V
/*      */     //   3532: aload #52
/*      */     //   3534: ifnull -> 3553
/*      */     //   3537: aload #52
/*      */     //   3539: invokevirtual isByReference : ()Z
/*      */     //   3542: ifeq -> 3553
/*      */     //   3545: aload #52
/*      */     //   3547: getfield geomLock : Ljavax/media/j3d/GeometryLock;
/*      */     //   3550: invokevirtual unLock : ()V
/*      */     //   3553: aload #6
/*      */     //   3555: iconst_0
/*      */     //   3556: putfield offScreenRendering : Z
/*      */     //   3559: aload #6
/*      */     //   3561: invokevirtual setFatalError : ()V
/*      */     //   3564: new javax/media/j3d/RenderingError
/*      */     //   3567: dup
/*      */     //   3568: iconst_3
/*      */     //   3569: ldc 'Renderer7'
/*      */     //   3571: invokestatic getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   3574: invokespecial <init> : (ILjava/lang/String;)V
/*      */     //   3577: astore #54
/*      */     //   3579: aload #54
/*      */     //   3581: aload #6
/*      */     //   3583: invokevirtual setCanvas3D : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   3586: aload #54
/*      */     //   3588: aload #6
/*      */     //   3590: getfield graphicsConfiguration : Ljava/awt/GraphicsConfiguration;
/*      */     //   3593: invokevirtual getDevice : ()Ljava/awt/GraphicsDevice;
/*      */     //   3596: invokevirtual setGraphicsDevice : (Ljava/awt/GraphicsDevice;)V
/*      */     //   3599: aload #54
/*      */     //   3601: invokestatic notifyErrorListeners : (Ljavax/media/j3d/RenderingError;)V
/*      */     //   3604: aload #53
/*      */     //   3606: monitorexit
/*      */     //   3607: goto -> 6330
/*      */     //   3610: aload_0
/*      */     //   3611: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   3614: invokevirtual getContextTimeStamp : ()J
/*      */     //   3617: putfield sharedCtxTimeStamp : J
/*      */     //   3620: aload_0
/*      */     //   3621: iconst_1
/*      */     //   3622: putfield needToRebuildDisplayList : Z
/*      */     //   3625: aload #53
/*      */     //   3627: monitorexit
/*      */     //   3628: goto -> 3639
/*      */     //   3631: astore #57
/*      */     //   3633: aload #53
/*      */     //   3635: monitorexit
/*      */     //   3636: aload #57
/*      */     //   3638: athrow
/*      */     //   3639: aload #6
/*      */     //   3641: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   3644: invokevirtual unLock : ()V
/*      */     //   3647: aload #6
/*      */     //   3649: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   3652: ifnonnull -> 4018
/*      */     //   3655: aload #6
/*      */     //   3657: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   3660: invokevirtual renderLock : ()Z
/*      */     //   3663: ifne -> 3696
/*      */     //   3666: aload #52
/*      */     //   3668: ifnull -> 3687
/*      */     //   3671: aload #52
/*      */     //   3673: invokevirtual isByReference : ()Z
/*      */     //   3676: ifeq -> 3687
/*      */     //   3679: aload #52
/*      */     //   3681: getfield geomLock : Ljavax/media/j3d/GeometryLock;
/*      */     //   3684: invokevirtual unLock : ()V
/*      */     //   3687: aload #6
/*      */     //   3689: iconst_0
/*      */     //   3690: putfield offScreenRendering : Z
/*      */     //   3693: goto -> 6330
/*      */     //   3696: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   3699: getfield contextCreationLock : Ljava/lang/Object;
/*      */     //   3702: dup
/*      */     //   3703: astore #53
/*      */     //   3705: monitorenter
/*      */     //   3706: aload #6
/*      */     //   3708: aconst_null
/*      */     //   3709: putfield ctx : Ljavax/media/j3d/Context;
/*      */     //   3712: aload #6
/*      */     //   3714: aload #6
/*      */     //   3716: aload_0
/*      */     //   3717: getfield sharedCtx : Ljavax/media/j3d/Context;
/*      */     //   3720: iconst_0
/*      */     //   3721: invokevirtual createNewContext : (Ljavax/media/j3d/Context;Z)Ljavax/media/j3d/Context;
/*      */     //   3724: putfield ctx : Ljavax/media/j3d/Context;
/*      */     //   3727: goto -> 3737
/*      */     //   3730: astore #54
/*      */     //   3732: aload #54
/*      */     //   3734: invokevirtual printStackTrace : ()V
/*      */     //   3737: aload #6
/*      */     //   3739: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   3742: ifnonnull -> 3831
/*      */     //   3745: aload #6
/*      */     //   3747: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   3750: invokevirtual unLock : ()V
/*      */     //   3753: aload #52
/*      */     //   3755: ifnull -> 3774
/*      */     //   3758: aload #52
/*      */     //   3760: invokevirtual isByReference : ()Z
/*      */     //   3763: ifeq -> 3774
/*      */     //   3766: aload #52
/*      */     //   3768: getfield geomLock : Ljavax/media/j3d/GeometryLock;
/*      */     //   3771: invokevirtual unLock : ()V
/*      */     //   3774: aload #6
/*      */     //   3776: iconst_0
/*      */     //   3777: putfield offScreenRendering : Z
/*      */     //   3780: aload #6
/*      */     //   3782: invokevirtual setFatalError : ()V
/*      */     //   3785: new javax/media/j3d/RenderingError
/*      */     //   3788: dup
/*      */     //   3789: iconst_3
/*      */     //   3790: ldc 'Renderer7'
/*      */     //   3792: invokestatic getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   3795: invokespecial <init> : (ILjava/lang/String;)V
/*      */     //   3798: astore #54
/*      */     //   3800: aload #54
/*      */     //   3802: aload #6
/*      */     //   3804: invokevirtual setCanvas3D : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   3807: aload #54
/*      */     //   3809: aload #6
/*      */     //   3811: getfield graphicsConfiguration : Ljava/awt/GraphicsConfiguration;
/*      */     //   3814: invokevirtual getDevice : ()Ljava/awt/GraphicsDevice;
/*      */     //   3817: invokevirtual setGraphicsDevice : (Ljava/awt/GraphicsDevice;)V
/*      */     //   3820: aload #54
/*      */     //   3822: invokestatic notifyErrorListeners : (Ljavax/media/j3d/RenderingError;)V
/*      */     //   3825: aload #53
/*      */     //   3827: monitorexit
/*      */     //   3828: goto -> 6330
/*      */     //   3831: aload #6
/*      */     //   3833: getfield graphics2D : Ljavax/media/j3d/J3DGraphics2DImpl;
/*      */     //   3836: ifnull -> 3847
/*      */     //   3839: aload #6
/*      */     //   3841: getfield graphics2D : Ljavax/media/j3d/J3DGraphics2DImpl;
/*      */     //   3844: invokevirtual init : ()V
/*      */     //   3847: aload #6
/*      */     //   3849: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   3852: invokevirtual getContextTimeStamp : ()J
/*      */     //   3855: putfield ctxTimeStamp : J
/*      */     //   3858: aload_0
/*      */     //   3859: getfield listOfCtxs : Ljava/util/ArrayList;
/*      */     //   3862: aload #6
/*      */     //   3864: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   3867: invokevirtual add : (Ljava/lang/Object;)Z
/*      */     //   3870: pop
/*      */     //   3871: aload_0
/*      */     //   3872: getfield listOfCanvases : Ljava/util/ArrayList;
/*      */     //   3875: aload #6
/*      */     //   3877: invokevirtual add : (Ljava/lang/Object;)Z
/*      */     //   3880: pop
/*      */     //   3881: aload #4
/*      */     //   3883: getfield nodeComponentList : Ljava/util/ArrayList;
/*      */     //   3886: invokevirtual size : ()I
/*      */     //   3889: ifle -> 3947
/*      */     //   3892: iconst_0
/*      */     //   3893: istore #16
/*      */     //   3895: iload #16
/*      */     //   3897: aload #4
/*      */     //   3899: getfield nodeComponentList : Ljava/util/ArrayList;
/*      */     //   3902: invokevirtual size : ()I
/*      */     //   3905: if_icmpge -> 3947
/*      */     //   3908: aload #4
/*      */     //   3910: getfield nodeComponentList : Ljava/util/ArrayList;
/*      */     //   3913: iload #16
/*      */     //   3915: invokevirtual get : (I)Ljava/lang/Object;
/*      */     //   3918: checkcast javax/media/j3d/NodeComponentRetained
/*      */     //   3921: astore #54
/*      */     //   3923: aload #54
/*      */     //   3925: instanceof javax/media/j3d/ImageComponentRetained
/*      */     //   3928: ifeq -> 3941
/*      */     //   3931: aload #54
/*      */     //   3933: checkcast javax/media/j3d/ImageComponentRetained
/*      */     //   3936: aload #6
/*      */     //   3938: invokevirtual evaluateExtensions : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   3941: iinc #16, 1
/*      */     //   3944: goto -> 3895
/*      */     //   3947: aload #6
/*      */     //   3949: invokevirtual enableSeparateSpecularColor : ()V
/*      */     //   3952: aload #53
/*      */     //   3954: monitorexit
/*      */     //   3955: goto -> 3966
/*      */     //   3958: astore #58
/*      */     //   3960: aload #53
/*      */     //   3962: monitorexit
/*      */     //   3963: aload #58
/*      */     //   3965: athrow
/*      */     //   3966: aload #6
/*      */     //   3968: getfield texUnitState : [Ljavax/media/j3d/TextureUnitStateRetained;
/*      */     //   3971: ifnonnull -> 3979
/*      */     //   3974: aload #6
/*      */     //   3976: invokevirtual createTexUnitState : ()V
/*      */     //   3979: aload #6
/*      */     //   3981: iconst_0
/*      */     //   3982: invokevirtual resetImmediateRendering : (I)V
/*      */     //   3985: aload #6
/*      */     //   3987: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   3990: invokevirtual contextValidated : ()V
/*      */     //   3993: aload #6
/*      */     //   3995: getfield useSharedCtx : Z
/*      */     //   3998: ifne -> 4007
/*      */     //   4001: aload #6
/*      */     //   4003: iconst_1
/*      */     //   4004: putfield needToRebuildDisplayList : Z
/*      */     //   4007: aload #6
/*      */     //   4009: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   4012: invokevirtual unLock : ()V
/*      */     //   4015: goto -> 4031
/*      */     //   4018: aload #6
/*      */     //   4020: getfield isRunning : Z
/*      */     //   4023: ifeq -> 4031
/*      */     //   4026: aload #6
/*      */     //   4028: invokevirtual makeCtxCurrent : ()V
/*      */     //   4031: aload #4
/*      */     //   4033: ifnull -> 6306
/*      */     //   4036: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   4039: getfield doDsiRenderLock : Z
/*      */     //   4042: ifeq -> 4086
/*      */     //   4045: aload #6
/*      */     //   4047: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   4050: invokevirtual renderLock : ()Z
/*      */     //   4053: ifne -> 4086
/*      */     //   4056: aload #52
/*      */     //   4058: ifnull -> 4077
/*      */     //   4061: aload #52
/*      */     //   4063: invokevirtual isByReference : ()Z
/*      */     //   4066: ifeq -> 4077
/*      */     //   4069: aload #52
/*      */     //   4071: getfield geomLock : Ljavax/media/j3d/GeometryLock;
/*      */     //   4074: invokevirtual unLock : ()V
/*      */     //   4077: aload #6
/*      */     //   4079: iconst_0
/*      */     //   4080: putfield offScreenRendering : Z
/*      */     //   4083: goto -> 6330
/*      */     //   4086: aload_0
/*      */     //   4087: getfield needToResendTextureDown : Z
/*      */     //   4090: ifeq -> 4110
/*      */     //   4093: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   4096: dup
/*      */     //   4097: getfield resendTexTimestamp : I
/*      */     //   4100: iconst_1
/*      */     //   4101: iadd
/*      */     //   4102: putfield resendTexTimestamp : I
/*      */     //   4105: aload_0
/*      */     //   4106: iconst_0
/*      */     //   4107: putfield needToResendTextureDown : Z
/*      */     //   4110: aload #6
/*      */     //   4112: getfield useSharedCtx : Z
/*      */     //   4115: ifeq -> 4127
/*      */     //   4118: aload_0
/*      */     //   4119: aload #6
/*      */     //   4121: invokevirtual freeResourcesInFreeList : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   4124: goto -> 4137
/*      */     //   4127: aload #6
/*      */     //   4129: aload #6
/*      */     //   4131: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   4134: invokevirtual freeResourcesInFreeList : (Ljavax/media/j3d/Context;)V
/*      */     //   4137: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   4140: getfield doDsiRenderLock : Z
/*      */     //   4143: ifeq -> 4154
/*      */     //   4146: aload #6
/*      */     //   4148: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   4151: invokevirtual unLock : ()V
/*      */     //   4154: aload #6
/*      */     //   4156: getfield canvasViewCache : Ljavax/media/j3d/CanvasViewCache;
/*      */     //   4159: astore #53
/*      */     //   4161: aload #6
/*      */     //   4163: iconst_0
/*      */     //   4164: aconst_null
/*      */     //   4165: aconst_null
/*      */     //   4166: aload #4
/*      */     //   4168: getfield geometryBackground : Ljavax/media/j3d/BackgroundRetained;
/*      */     //   4171: ifnull -> 4178
/*      */     //   4174: iconst_1
/*      */     //   4175: goto -> 4179
/*      */     //   4178: iconst_0
/*      */     //   4179: invokevirtual updateViewCache : (ZLjavax/media/j3d/CanvasViewCache;Ljavax/media/j3d/BoundingBox;Z)V
/*      */     //   4182: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   4185: getfield doDsiRenderLock : Z
/*      */     //   4188: ifeq -> 4232
/*      */     //   4191: aload #6
/*      */     //   4193: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   4196: invokevirtual renderLock : ()Z
/*      */     //   4199: ifne -> 4232
/*      */     //   4202: aload #52
/*      */     //   4204: ifnull -> 4223
/*      */     //   4207: aload #52
/*      */     //   4209: invokevirtual isByReference : ()Z
/*      */     //   4212: ifeq -> 4223
/*      */     //   4215: aload #52
/*      */     //   4217: getfield geomLock : Ljavax/media/j3d/GeometryLock;
/*      */     //   4220: invokevirtual unLock : ()V
/*      */     //   4223: aload #6
/*      */     //   4225: iconst_0
/*      */     //   4226: putfield offScreenRendering : Z
/*      */     //   4229: goto -> 6330
/*      */     //   4232: aload #6
/*      */     //   4234: aload #6
/*      */     //   4236: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   4239: iconst_0
/*      */     //   4240: iconst_0
/*      */     //   4241: aload #53
/*      */     //   4243: invokevirtual getCanvasWidth : ()I
/*      */     //   4246: aload #53
/*      */     //   4248: invokevirtual getCanvasHeight : ()I
/*      */     //   4251: invokevirtual setViewport : (Ljavax/media/j3d/Context;IIII)V
/*      */     //   4254: aload #6
/*      */     //   4256: getfield useSharedCtx : Z
/*      */     //   4259: ifeq -> 4372
/*      */     //   4262: aload_0
/*      */     //   4263: getfield needToRebuildDisplayList : Z
/*      */     //   4266: ifeq -> 4282
/*      */     //   4269: aload #4
/*      */     //   4271: aload_0
/*      */     //   4272: aload #6
/*      */     //   4274: invokevirtual updateAllRenderMolecule : (Ljavax/media/j3d/Renderer;Ljavax/media/j3d/Canvas3D;)V
/*      */     //   4277: aload_0
/*      */     //   4278: iconst_0
/*      */     //   4279: putfield needToRebuildDisplayList : Z
/*      */     //   4282: aload_0
/*      */     //   4283: getfield dirtyDisplayList : Z
/*      */     //   4286: ifeq -> 4314
/*      */     //   4289: aload #4
/*      */     //   4291: aload #6
/*      */     //   4293: aload_0
/*      */     //   4294: getfield dirtyRenderMoleculeList : Ljava/util/ArrayList;
/*      */     //   4297: aload_0
/*      */     //   4298: getfield dirtyDlistPerRinfoList : Ljava/util/ArrayList;
/*      */     //   4301: aload_0
/*      */     //   4302: getfield dirtyRenderAtomList : Ljava/util/ArrayList;
/*      */     //   4305: iconst_1
/*      */     //   4306: invokevirtual updateDirtyDisplayLists : (Ljavax/media/j3d/Canvas3D;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Z)V
/*      */     //   4309: aload_0
/*      */     //   4310: iconst_0
/*      */     //   4311: putfield dirtyDisplayList : Z
/*      */     //   4314: aload_0
/*      */     //   4315: getfield textureReloadList : Ljava/util/ArrayList;
/*      */     //   4318: invokevirtual size : ()I
/*      */     //   4321: istore #54
/*      */     //   4323: iload #54
/*      */     //   4325: ifle -> 4369
/*      */     //   4328: iload #54
/*      */     //   4330: iconst_1
/*      */     //   4331: isub
/*      */     //   4332: istore #17
/*      */     //   4334: iload #17
/*      */     //   4336: iflt -> 4362
/*      */     //   4339: aload_0
/*      */     //   4340: getfield textureReloadList : Ljava/util/ArrayList;
/*      */     //   4343: iload #17
/*      */     //   4345: invokevirtual get : (I)Ljava/lang/Object;
/*      */     //   4348: checkcast javax/media/j3d/TextureRetained
/*      */     //   4351: aload #6
/*      */     //   4353: invokevirtual reloadTextureSharedContext : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   4356: iinc #17, -1
/*      */     //   4359: goto -> 4334
/*      */     //   4362: aload_0
/*      */     //   4363: getfield textureReloadList : Ljava/util/ArrayList;
/*      */     //   4366: invokevirtual clear : ()V
/*      */     //   4369: goto -> 4430
/*      */     //   4372: aload #6
/*      */     //   4374: getfield needToRebuildDisplayList : Z
/*      */     //   4377: ifeq -> 4393
/*      */     //   4380: aload #4
/*      */     //   4382: aload #6
/*      */     //   4384: invokevirtual updateAllRenderMolecule : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   4387: aload #6
/*      */     //   4389: iconst_0
/*      */     //   4390: putfield needToRebuildDisplayList : Z
/*      */     //   4393: aload #6
/*      */     //   4395: getfield dirtyDisplayList : Z
/*      */     //   4398: ifeq -> 4430
/*      */     //   4401: aload #4
/*      */     //   4403: aload #6
/*      */     //   4405: aload #6
/*      */     //   4407: getfield dirtyRenderMoleculeList : Ljava/util/ArrayList;
/*      */     //   4410: aload #6
/*      */     //   4412: getfield dirtyDlistPerRinfoList : Ljava/util/ArrayList;
/*      */     //   4415: aload #6
/*      */     //   4417: getfield dirtyRenderAtomList : Ljava/util/ArrayList;
/*      */     //   4420: iconst_0
/*      */     //   4421: invokevirtual updateDirtyDisplayLists : (Ljavax/media/j3d/Canvas3D;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Z)V
/*      */     //   4424: aload #6
/*      */     //   4426: iconst_0
/*      */     //   4427: putfield dirtyDisplayList : Z
/*      */     //   4430: aload #6
/*      */     //   4432: getfield view : Ljavax/media/j3d/View;
/*      */     //   4435: getfield localEyeLightingEnable : Z
/*      */     //   4438: aload #6
/*      */     //   4440: getfield ctxEyeLightingEnable : Z
/*      */     //   4443: if_icmpeq -> 4477
/*      */     //   4446: aload #6
/*      */     //   4448: aload #6
/*      */     //   4450: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   4453: aload #6
/*      */     //   4455: getfield view : Ljavax/media/j3d/View;
/*      */     //   4458: getfield localEyeLightingEnable : Z
/*      */     //   4461: invokevirtual ctxUpdateEyeLightingEnable : (Ljavax/media/j3d/Context;Z)V
/*      */     //   4464: aload #6
/*      */     //   4466: aload #6
/*      */     //   4468: getfield view : Ljavax/media/j3d/View;
/*      */     //   4471: getfield localEyeLightingEnable : Z
/*      */     //   4474: putfield ctxEyeLightingEnable : Z
/*      */     //   4477: aload #53
/*      */     //   4479: invokevirtual getUseStereo : ()Z
/*      */     //   4482: istore #54
/*      */     //   4484: iload #54
/*      */     //   4486: ifeq -> 4508
/*      */     //   4489: iconst_2
/*      */     //   4490: istore #11
/*      */     //   4492: iconst_0
/*      */     //   4493: istore #10
/*      */     //   4495: aload_0
/*      */     //   4496: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   4499: getfield sharedStereoZBuffer : Z
/*      */     //   4502: putfield sharedStereoZBuffer : Z
/*      */     //   4505: goto -> 4519
/*      */     //   4508: iconst_1
/*      */     //   4509: istore #11
/*      */     //   4511: iconst_2
/*      */     //   4512: istore #10
/*      */     //   4514: aload_0
/*      */     //   4515: iconst_0
/*      */     //   4516: putfield sharedStereoZBuffer : Z
/*      */     //   4519: aload #6
/*      */     //   4521: getfield view : Ljavax/media/j3d/View;
/*      */     //   4524: invokevirtual getSceneAntialiasingEnable : ()Z
/*      */     //   4527: ifeq -> 4860
/*      */     //   4530: aload #6
/*      */     //   4532: getfield sceneAntialiasingAvailable : Z
/*      */     //   4535: ifeq -> 4860
/*      */     //   4538: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   4541: invokevirtual isD3D : ()Z
/*      */     //   4544: ifne -> 4558
/*      */     //   4547: aload #6
/*      */     //   4549: getfield extensionsSupported : I
/*      */     //   4552: bipush #8
/*      */     //   4554: iand
/*      */     //   4555: ifeq -> 4566
/*      */     //   4558: aload #6
/*      */     //   4560: getfield sceneAntialiasingMultiSamplesAvailable : Z
/*      */     //   4563: ifne -> 4832
/*      */     //   4566: iconst_1
/*      */     //   4567: istore #19
/*      */     //   4569: bipush #8
/*      */     //   4571: istore #13
/*      */     //   4573: aload #53
/*      */     //   4575: invokevirtual getLeftProjection : ()Ljavax/media/j3d/Transform3D;
/*      */     //   4578: getfield mat : [D
/*      */     //   4581: iconst_0
/*      */     //   4582: aload_0
/*      */     //   4583: getfield accumLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   4586: getfield mat : [D
/*      */     //   4589: iconst_0
/*      */     //   4590: bipush #16
/*      */     //   4592: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   4595: aload #6
/*      */     //   4597: getfield canvasViewCache : Ljavax/media/j3d/CanvasViewCache;
/*      */     //   4600: invokevirtual getPhysicalWindowWidth : ()D
/*      */     //   4603: aload #6
/*      */     //   4605: getfield canvasViewCache : Ljavax/media/j3d/CanvasViewCache;
/*      */     //   4608: invokevirtual getCanvasWidth : ()I
/*      */     //   4611: i2d
/*      */     //   4612: ddiv
/*      */     //   4613: aload #6
/*      */     //   4615: getfield view : Ljavax/media/j3d/View;
/*      */     //   4618: getfield fieldOfView : D
/*      */     //   4621: dmul
/*      */     //   4622: dstore #24
/*      */     //   4624: aload #6
/*      */     //   4626: getfield canvasViewCache : Ljavax/media/j3d/CanvasViewCache;
/*      */     //   4629: invokevirtual getPhysicalWindowHeight : ()D
/*      */     //   4632: aload #6
/*      */     //   4634: getfield canvasViewCache : Ljavax/media/j3d/CanvasViewCache;
/*      */     //   4637: invokevirtual getCanvasHeight : ()I
/*      */     //   4640: i2d
/*      */     //   4641: ddiv
/*      */     //   4642: aload #6
/*      */     //   4644: getfield view : Ljavax/media/j3d/View;
/*      */     //   4647: getfield fieldOfView : D
/*      */     //   4650: dmul
/*      */     //   4651: dstore #26
/*      */     //   4653: aload_0
/*      */     //   4654: getfield accumLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   4657: getfield mat : [D
/*      */     //   4660: iconst_3
/*      */     //   4661: daload
/*      */     //   4662: dstore #28
/*      */     //   4664: aload_0
/*      */     //   4665: getfield accumLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   4668: getfield mat : [D
/*      */     //   4671: bipush #7
/*      */     //   4673: daload
/*      */     //   4674: dstore #30
/*      */     //   4676: iload #54
/*      */     //   4678: ifeq -> 4726
/*      */     //   4681: aload #53
/*      */     //   4683: invokevirtual getRightProjection : ()Ljavax/media/j3d/Transform3D;
/*      */     //   4686: getfield mat : [D
/*      */     //   4689: iconst_0
/*      */     //   4690: aload_0
/*      */     //   4691: getfield accumRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   4694: getfield mat : [D
/*      */     //   4697: iconst_0
/*      */     //   4698: bipush #16
/*      */     //   4700: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   4703: aload_0
/*      */     //   4704: getfield accumRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   4707: getfield mat : [D
/*      */     //   4710: iconst_3
/*      */     //   4711: daload
/*      */     //   4712: dstore #32
/*      */     //   4714: aload_0
/*      */     //   4715: getfield accumRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   4718: getfield mat : [D
/*      */     //   4721: bipush #7
/*      */     //   4723: daload
/*      */     //   4724: dstore #34
/*      */     //   4726: aload #4
/*      */     //   4728: getfield geometryBackground : Ljavax/media/j3d/BackgroundRetained;
/*      */     //   4731: ifnull -> 4885
/*      */     //   4734: aload #53
/*      */     //   4736: invokevirtual getInfLeftProjection : ()Ljavax/media/j3d/Transform3D;
/*      */     //   4739: getfield mat : [D
/*      */     //   4742: iconst_0
/*      */     //   4743: aload_0
/*      */     //   4744: getfield accumInfLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   4747: getfield mat : [D
/*      */     //   4750: iconst_0
/*      */     //   4751: bipush #16
/*      */     //   4753: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   4756: aload_0
/*      */     //   4757: getfield accumInfLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   4760: getfield mat : [D
/*      */     //   4763: iconst_3
/*      */     //   4764: daload
/*      */     //   4765: dstore #36
/*      */     //   4767: aload_0
/*      */     //   4768: getfield accumInfLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   4771: getfield mat : [D
/*      */     //   4774: bipush #7
/*      */     //   4776: daload
/*      */     //   4777: dstore #38
/*      */     //   4779: iload #54
/*      */     //   4781: ifeq -> 4885
/*      */     //   4784: aload #53
/*      */     //   4786: invokevirtual getInfRightProjection : ()Ljavax/media/j3d/Transform3D;
/*      */     //   4789: getfield mat : [D
/*      */     //   4792: iconst_0
/*      */     //   4793: aload_0
/*      */     //   4794: getfield accumInfRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   4797: getfield mat : [D
/*      */     //   4800: iconst_0
/*      */     //   4801: bipush #16
/*      */     //   4803: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
/*      */     //   4806: aload_0
/*      */     //   4807: getfield accumInfRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   4810: getfield mat : [D
/*      */     //   4813: iconst_3
/*      */     //   4814: daload
/*      */     //   4815: dstore #40
/*      */     //   4817: aload_0
/*      */     //   4818: getfield accumInfRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   4821: getfield mat : [D
/*      */     //   4824: bipush #7
/*      */     //   4826: daload
/*      */     //   4827: dstore #42
/*      */     //   4829: goto -> 4885
/*      */     //   4832: aload #6
/*      */     //   4834: getfield antialiasingSet : Z
/*      */     //   4837: ifne -> 4885
/*      */     //   4840: aload #6
/*      */     //   4842: aload #6
/*      */     //   4844: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   4847: iconst_1
/*      */     //   4848: invokevirtual setFullSceneAntialiasing : (Ljavax/media/j3d/Context;Z)V
/*      */     //   4851: aload #6
/*      */     //   4853: iconst_1
/*      */     //   4854: putfield antialiasingSet : Z
/*      */     //   4857: goto -> 4885
/*      */     //   4860: aload #6
/*      */     //   4862: getfield antialiasingSet : Z
/*      */     //   4865: ifeq -> 4885
/*      */     //   4868: aload #6
/*      */     //   4870: aload #6
/*      */     //   4872: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   4875: iconst_0
/*      */     //   4876: invokevirtual setFullSceneAntialiasing : (Ljavax/media/j3d/Context;Z)V
/*      */     //   4879: aload #6
/*      */     //   4881: iconst_0
/*      */     //   4882: putfield antialiasingSet : Z
/*      */     //   4885: aload #4
/*      */     //   4887: getfield geometryBackground : Ljavax/media/j3d/BackgroundRetained;
/*      */     //   4890: ifnull -> 4898
/*      */     //   4893: aload #4
/*      */     //   4895: invokevirtual updateInfVworldToVpc : ()V
/*      */     //   4898: aload #6
/*      */     //   4900: aload #6
/*      */     //   4902: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   4905: iconst_2
/*      */     //   4906: aload #6
/*      */     //   4908: getfield useDoubleBuffer : Z
/*      */     //   4911: invokevirtual setRenderMode : (Ljavax/media/j3d/Context;IZ)V
/*      */     //   4914: aload #6
/*      */     //   4916: invokevirtual beginScene : ()V
/*      */     //   4919: aload #53
/*      */     //   4921: invokevirtual getCanvasWidth : ()I
/*      */     //   4924: istore #55
/*      */     //   4926: aload #53
/*      */     //   4928: invokevirtual getCanvasHeight : ()I
/*      */     //   4931: istore #56
/*      */     //   4933: iload #19
/*      */     //   4935: ifne -> 4963
/*      */     //   4938: aload_0
/*      */     //   4939: getfield sharedStereoZBuffer : Z
/*      */     //   4942: ifne -> 4963
/*      */     //   4945: aload #4
/*      */     //   4947: getfield background : Ljavax/media/j3d/BackgroundRetained;
/*      */     //   4950: astore #57
/*      */     //   4952: aload #6
/*      */     //   4954: aload #57
/*      */     //   4956: iload #55
/*      */     //   4958: iload #56
/*      */     //   4960: invokevirtual clear : (Ljavax/media/j3d/BackgroundRetained;II)V
/*      */     //   4963: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   4966: getfield doDsiRenderLock : Z
/*      */     //   4969: ifeq -> 4980
/*      */     //   4972: aload #6
/*      */     //   4974: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   4977: invokevirtual unLock : ()V
/*      */     //   4980: aload #6
/*      */     //   4982: getfield view : Ljavax/media/j3d/View;
/*      */     //   4985: iconst_1
/*      */     //   4986: putfield inCanvasCallback : Z
/*      */     //   4989: aload #6
/*      */     //   4991: invokevirtual preRender : ()V
/*      */     //   4994: goto -> 5030
/*      */     //   4997: astore #57
/*      */     //   4999: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*      */     //   5002: ldc 'Exception occurred during Canvas3D callback:'
/*      */     //   5004: invokevirtual println : (Ljava/lang/String;)V
/*      */     //   5007: aload #57
/*      */     //   5009: invokevirtual printStackTrace : ()V
/*      */     //   5012: goto -> 5030
/*      */     //   5015: astore #57
/*      */     //   5017: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*      */     //   5020: ldc 'Error occurred during Canvas3D callback:'
/*      */     //   5022: invokevirtual println : (Ljava/lang/String;)V
/*      */     //   5025: aload #57
/*      */     //   5027: invokevirtual printStackTrace : ()V
/*      */     //   5030: aload #6
/*      */     //   5032: getfield view : Ljavax/media/j3d/View;
/*      */     //   5035: iconst_0
/*      */     //   5036: putfield inCanvasCallback : Z
/*      */     //   5039: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   5042: getfield doDsiRenderLock : Z
/*      */     //   5045: ifeq -> 5089
/*      */     //   5048: aload #6
/*      */     //   5050: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   5053: invokevirtual renderLock : ()Z
/*      */     //   5056: ifne -> 5089
/*      */     //   5059: aload #52
/*      */     //   5061: ifnull -> 5080
/*      */     //   5064: aload #52
/*      */     //   5066: invokevirtual isByReference : ()Z
/*      */     //   5069: ifeq -> 5080
/*      */     //   5072: aload #52
/*      */     //   5074: getfield geomLock : Ljavax/media/j3d/GeometryLock;
/*      */     //   5077: invokevirtual unLock : ()V
/*      */     //   5080: aload #6
/*      */     //   5082: iconst_0
/*      */     //   5083: putfield offScreenRendering : Z
/*      */     //   5086: goto -> 6330
/*      */     //   5089: iconst_0
/*      */     //   5090: istore #14
/*      */     //   5092: iload #14
/*      */     //   5094: iload #11
/*      */     //   5096: if_icmpge -> 6047
/*      */     //   5099: iload #19
/*      */     //   5101: ifeq -> 5114
/*      */     //   5104: aload #6
/*      */     //   5106: aload #6
/*      */     //   5108: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   5111: invokevirtual clearAccum : (Ljavax/media/j3d/Context;)V
/*      */     //   5114: aload #6
/*      */     //   5116: aload #6
/*      */     //   5118: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   5121: iload #10
/*      */     //   5123: aload #6
/*      */     //   5125: getfield useDoubleBuffer : Z
/*      */     //   5128: invokevirtual setRenderMode : (Ljavax/media/j3d/Context;IZ)V
/*      */     //   5131: iconst_0
/*      */     //   5132: istore #15
/*      */     //   5134: iload #15
/*      */     //   5136: iload #13
/*      */     //   5138: if_icmpge -> 6012
/*      */     //   5141: iload #19
/*      */     //   5143: ifeq -> 5488
/*      */     //   5146: getstatic javax/media/j3d/Renderer.ACCUM_SAMPLES_X : [F
/*      */     //   5149: iload #15
/*      */     //   5151: faload
/*      */     //   5152: f2d
/*      */     //   5153: dload #24
/*      */     //   5155: dmul
/*      */     //   5156: dstore #20
/*      */     //   5158: getstatic javax/media/j3d/Renderer.ACCUM_SAMPLES_Y : [F
/*      */     //   5161: iload #15
/*      */     //   5163: faload
/*      */     //   5164: f2d
/*      */     //   5165: dload #26
/*      */     //   5167: dmul
/*      */     //   5168: dstore #22
/*      */     //   5170: aload_0
/*      */     //   5171: getfield accumLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5174: getfield mat : [D
/*      */     //   5177: iconst_3
/*      */     //   5178: dload #28
/*      */     //   5180: aload_0
/*      */     //   5181: getfield accumLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5184: getfield mat : [D
/*      */     //   5187: iconst_0
/*      */     //   5188: daload
/*      */     //   5189: dload #20
/*      */     //   5191: dmul
/*      */     //   5192: dadd
/*      */     //   5193: aload_0
/*      */     //   5194: getfield accumLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5197: getfield mat : [D
/*      */     //   5200: iconst_1
/*      */     //   5201: daload
/*      */     //   5202: dload #22
/*      */     //   5204: dmul
/*      */     //   5205: dadd
/*      */     //   5206: dastore
/*      */     //   5207: aload_0
/*      */     //   5208: getfield accumLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5211: getfield mat : [D
/*      */     //   5214: bipush #7
/*      */     //   5216: dload #30
/*      */     //   5218: aload_0
/*      */     //   5219: getfield accumLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5222: getfield mat : [D
/*      */     //   5225: iconst_4
/*      */     //   5226: daload
/*      */     //   5227: dload #20
/*      */     //   5229: dmul
/*      */     //   5230: dadd
/*      */     //   5231: aload_0
/*      */     //   5232: getfield accumLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5235: getfield mat : [D
/*      */     //   5238: iconst_5
/*      */     //   5239: daload
/*      */     //   5240: dload #22
/*      */     //   5242: dmul
/*      */     //   5243: dadd
/*      */     //   5244: dastore
/*      */     //   5245: iload #54
/*      */     //   5247: ifeq -> 5325
/*      */     //   5250: aload_0
/*      */     //   5251: getfield accumRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5254: getfield mat : [D
/*      */     //   5257: iconst_3
/*      */     //   5258: dload #32
/*      */     //   5260: aload_0
/*      */     //   5261: getfield accumRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5264: getfield mat : [D
/*      */     //   5267: iconst_0
/*      */     //   5268: daload
/*      */     //   5269: dload #20
/*      */     //   5271: dmul
/*      */     //   5272: dadd
/*      */     //   5273: aload_0
/*      */     //   5274: getfield accumRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5277: getfield mat : [D
/*      */     //   5280: iconst_1
/*      */     //   5281: daload
/*      */     //   5282: dload #22
/*      */     //   5284: dmul
/*      */     //   5285: dadd
/*      */     //   5286: dastore
/*      */     //   5287: aload_0
/*      */     //   5288: getfield accumRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5291: getfield mat : [D
/*      */     //   5294: bipush #7
/*      */     //   5296: dload #34
/*      */     //   5298: aload_0
/*      */     //   5299: getfield accumRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5302: getfield mat : [D
/*      */     //   5305: iconst_4
/*      */     //   5306: daload
/*      */     //   5307: dload #20
/*      */     //   5309: dmul
/*      */     //   5310: dadd
/*      */     //   5311: aload_0
/*      */     //   5312: getfield accumRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5315: getfield mat : [D
/*      */     //   5318: iconst_5
/*      */     //   5319: daload
/*      */     //   5320: dload #22
/*      */     //   5322: dmul
/*      */     //   5323: dadd
/*      */     //   5324: dastore
/*      */     //   5325: aload #4
/*      */     //   5327: getfield geometryBackground : Ljavax/media/j3d/BackgroundRetained;
/*      */     //   5330: ifnull -> 5488
/*      */     //   5333: aload_0
/*      */     //   5334: getfield accumInfLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5337: getfield mat : [D
/*      */     //   5340: iconst_3
/*      */     //   5341: dload #36
/*      */     //   5343: aload_0
/*      */     //   5344: getfield accumInfLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5347: getfield mat : [D
/*      */     //   5350: iconst_0
/*      */     //   5351: daload
/*      */     //   5352: dload #20
/*      */     //   5354: dmul
/*      */     //   5355: dadd
/*      */     //   5356: aload_0
/*      */     //   5357: getfield accumInfLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5360: getfield mat : [D
/*      */     //   5363: iconst_1
/*      */     //   5364: daload
/*      */     //   5365: dload #22
/*      */     //   5367: dmul
/*      */     //   5368: dadd
/*      */     //   5369: dastore
/*      */     //   5370: aload_0
/*      */     //   5371: getfield accumInfLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5374: getfield mat : [D
/*      */     //   5377: bipush #7
/*      */     //   5379: dload #38
/*      */     //   5381: aload_0
/*      */     //   5382: getfield accumInfLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5385: getfield mat : [D
/*      */     //   5388: iconst_4
/*      */     //   5389: daload
/*      */     //   5390: dload #20
/*      */     //   5392: dmul
/*      */     //   5393: dadd
/*      */     //   5394: aload_0
/*      */     //   5395: getfield accumInfLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5398: getfield mat : [D
/*      */     //   5401: iconst_5
/*      */     //   5402: daload
/*      */     //   5403: dload #22
/*      */     //   5405: dmul
/*      */     //   5406: dadd
/*      */     //   5407: dastore
/*      */     //   5408: iload #54
/*      */     //   5410: ifeq -> 5488
/*      */     //   5413: aload_0
/*      */     //   5414: getfield accumInfRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5417: getfield mat : [D
/*      */     //   5420: iconst_3
/*      */     //   5421: dload #40
/*      */     //   5423: aload_0
/*      */     //   5424: getfield accumInfRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5427: getfield mat : [D
/*      */     //   5430: iconst_0
/*      */     //   5431: daload
/*      */     //   5432: dload #20
/*      */     //   5434: dmul
/*      */     //   5435: dadd
/*      */     //   5436: aload_0
/*      */     //   5437: getfield accumInfRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5440: getfield mat : [D
/*      */     //   5443: iconst_1
/*      */     //   5444: daload
/*      */     //   5445: dload #22
/*      */     //   5447: dmul
/*      */     //   5448: dadd
/*      */     //   5449: dastore
/*      */     //   5450: aload_0
/*      */     //   5451: getfield accumInfRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5454: getfield mat : [D
/*      */     //   5457: bipush #7
/*      */     //   5459: dload #42
/*      */     //   5461: aload_0
/*      */     //   5462: getfield accumInfRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5465: getfield mat : [D
/*      */     //   5468: iconst_4
/*      */     //   5469: daload
/*      */     //   5470: dload #20
/*      */     //   5472: dmul
/*      */     //   5473: dadd
/*      */     //   5474: aload_0
/*      */     //   5475: getfield accumInfRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5478: getfield mat : [D
/*      */     //   5481: iconst_5
/*      */     //   5482: daload
/*      */     //   5483: dload #22
/*      */     //   5485: dmul
/*      */     //   5486: dadd
/*      */     //   5487: dastore
/*      */     //   5488: iload #19
/*      */     //   5490: ifne -> 5500
/*      */     //   5493: aload_0
/*      */     //   5494: getfield sharedStereoZBuffer : Z
/*      */     //   5497: ifeq -> 5518
/*      */     //   5500: aload #4
/*      */     //   5502: getfield background : Ljavax/media/j3d/BackgroundRetained;
/*      */     //   5505: astore #57
/*      */     //   5507: aload #6
/*      */     //   5509: aload #57
/*      */     //   5511: iload #55
/*      */     //   5513: iload #56
/*      */     //   5515: invokevirtual clear : (Ljavax/media/j3d/BackgroundRetained;II)V
/*      */     //   5518: aload #4
/*      */     //   5520: getfield geometryBackground : Ljavax/media/j3d/BackgroundRetained;
/*      */     //   5523: ifnull -> 5653
/*      */     //   5526: iload #14
/*      */     //   5528: ifne -> 5581
/*      */     //   5531: aload #6
/*      */     //   5533: aload #53
/*      */     //   5535: invokevirtual getInfLeftVpcToEc : ()Ljavax/media/j3d/Transform3D;
/*      */     //   5538: putfield vpcToEc : Ljavax/media/j3d/Transform3D;
/*      */     //   5541: iload #19
/*      */     //   5543: ifeq -> 5563
/*      */     //   5546: aload #6
/*      */     //   5548: aload #6
/*      */     //   5550: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   5553: aload_0
/*      */     //   5554: getfield accumInfLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5557: invokevirtual setProjectionMatrix : (Ljavax/media/j3d/Context;Ljavax/media/j3d/Transform3D;)V
/*      */     //   5560: goto -> 5628
/*      */     //   5563: aload #6
/*      */     //   5565: aload #6
/*      */     //   5567: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   5570: aload #53
/*      */     //   5572: invokevirtual getInfLeftProjection : ()Ljavax/media/j3d/Transform3D;
/*      */     //   5575: invokevirtual setProjectionMatrix : (Ljavax/media/j3d/Context;Ljavax/media/j3d/Transform3D;)V
/*      */     //   5578: goto -> 5628
/*      */     //   5581: aload #6
/*      */     //   5583: aload #53
/*      */     //   5585: invokevirtual getInfRightVpcToEc : ()Ljavax/media/j3d/Transform3D;
/*      */     //   5588: putfield vpcToEc : Ljavax/media/j3d/Transform3D;
/*      */     //   5591: iload #19
/*      */     //   5593: ifeq -> 5613
/*      */     //   5596: aload #6
/*      */     //   5598: aload #6
/*      */     //   5600: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   5603: aload_0
/*      */     //   5604: getfield accumInfRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5607: invokevirtual setProjectionMatrix : (Ljavax/media/j3d/Context;Ljavax/media/j3d/Transform3D;)V
/*      */     //   5610: goto -> 5628
/*      */     //   5613: aload #6
/*      */     //   5615: aload #6
/*      */     //   5617: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   5620: aload #53
/*      */     //   5622: invokevirtual getInfRightProjection : ()Ljavax/media/j3d/Transform3D;
/*      */     //   5625: invokevirtual setProjectionMatrix : (Ljavax/media/j3d/Context;Ljavax/media/j3d/Transform3D;)V
/*      */     //   5628: aload #6
/*      */     //   5630: getfield vworldToEc : Ljavax/media/j3d/Transform3D;
/*      */     //   5633: aload #6
/*      */     //   5635: getfield vpcToEc : Ljavax/media/j3d/Transform3D;
/*      */     //   5638: aload #53
/*      */     //   5640: invokevirtual getInfVworldToVpc : ()Ljavax/media/j3d/Transform3D;
/*      */     //   5643: invokevirtual mul : (Ljavax/media/j3d/Transform3D;Ljavax/media/j3d/Transform3D;)V
/*      */     //   5646: aload #4
/*      */     //   5648: aload #6
/*      */     //   5650: invokevirtual renderBackground : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   5653: iload #14
/*      */     //   5655: ifne -> 5708
/*      */     //   5658: aload #6
/*      */     //   5660: aload #53
/*      */     //   5662: invokevirtual getLeftVpcToEc : ()Ljavax/media/j3d/Transform3D;
/*      */     //   5665: putfield vpcToEc : Ljavax/media/j3d/Transform3D;
/*      */     //   5668: iload #19
/*      */     //   5670: ifeq -> 5690
/*      */     //   5673: aload #6
/*      */     //   5675: aload #6
/*      */     //   5677: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   5680: aload_0
/*      */     //   5681: getfield accumLeftProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5684: invokevirtual setProjectionMatrix : (Ljavax/media/j3d/Context;Ljavax/media/j3d/Transform3D;)V
/*      */     //   5687: goto -> 5755
/*      */     //   5690: aload #6
/*      */     //   5692: aload #6
/*      */     //   5694: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   5697: aload #53
/*      */     //   5699: invokevirtual getLeftProjection : ()Ljavax/media/j3d/Transform3D;
/*      */     //   5702: invokevirtual setProjectionMatrix : (Ljavax/media/j3d/Context;Ljavax/media/j3d/Transform3D;)V
/*      */     //   5705: goto -> 5755
/*      */     //   5708: aload #6
/*      */     //   5710: aload #53
/*      */     //   5712: invokevirtual getRightVpcToEc : ()Ljavax/media/j3d/Transform3D;
/*      */     //   5715: putfield vpcToEc : Ljavax/media/j3d/Transform3D;
/*      */     //   5718: iload #19
/*      */     //   5720: ifeq -> 5740
/*      */     //   5723: aload #6
/*      */     //   5725: aload #6
/*      */     //   5727: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   5730: aload_0
/*      */     //   5731: getfield accumRightProj : Ljavax/media/j3d/Transform3D;
/*      */     //   5734: invokevirtual setProjectionMatrix : (Ljavax/media/j3d/Context;Ljavax/media/j3d/Transform3D;)V
/*      */     //   5737: goto -> 5755
/*      */     //   5740: aload #6
/*      */     //   5742: aload #6
/*      */     //   5744: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   5747: aload #53
/*      */     //   5749: invokevirtual getRightProjection : ()Ljavax/media/j3d/Transform3D;
/*      */     //   5752: invokevirtual setProjectionMatrix : (Ljavax/media/j3d/Context;Ljavax/media/j3d/Transform3D;)V
/*      */     //   5755: aload #6
/*      */     //   5757: getfield vworldToEc : Ljavax/media/j3d/Transform3D;
/*      */     //   5760: aload #6
/*      */     //   5762: getfield vpcToEc : Ljavax/media/j3d/Transform3D;
/*      */     //   5765: aload #53
/*      */     //   5767: invokevirtual getVworldToVpc : ()Ljavax/media/j3d/Transform3D;
/*      */     //   5770: invokevirtual mul : (Ljavax/media/j3d/Transform3D;Ljavax/media/j3d/Transform3D;)V
/*      */     //   5773: aload #53
/*      */     //   5775: dup
/*      */     //   5776: astore #57
/*      */     //   5778: monitorenter
/*      */     //   5779: iload #14
/*      */     //   5781: ifne -> 5797
/*      */     //   5784: aload #6
/*      */     //   5786: aload #53
/*      */     //   5788: invokevirtual getLeftFrustumPlanesInVworld : ()[Ljavax/vecmath/Vector4d;
/*      */     //   5791: invokevirtual setFrustumPlanes : ([Ljavax/vecmath/Vector4d;)V
/*      */     //   5794: goto -> 5807
/*      */     //   5797: aload #6
/*      */     //   5799: aload #53
/*      */     //   5801: invokevirtual getRightFrustumPlanesInVworld : ()[Ljavax/vecmath/Vector4d;
/*      */     //   5804: invokevirtual setFrustumPlanes : ([Ljavax/vecmath/Vector4d;)V
/*      */     //   5807: aload #57
/*      */     //   5809: monitorexit
/*      */     //   5810: goto -> 5821
/*      */     //   5813: astore #59
/*      */     //   5815: aload #57
/*      */     //   5817: monitorexit
/*      */     //   5818: aload #59
/*      */     //   5820: athrow
/*      */     //   5821: iload #54
/*      */     //   5823: ifeq -> 5839
/*      */     //   5826: aload #6
/*      */     //   5828: dup
/*      */     //   5829: getfield canvasDirty : I
/*      */     //   5832: ldc_w 32768
/*      */     //   5835: ior
/*      */     //   5836: putfield canvasDirty : I
/*      */     //   5839: aload #4
/*      */     //   5841: aload #6
/*      */     //   5843: invokevirtual renderOpaque : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   5846: aload #4
/*      */     //   5848: aload #6
/*      */     //   5850: invokevirtual renderOrdered : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   5853: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   5856: getfield doDsiRenderLock : Z
/*      */     //   5859: ifeq -> 5870
/*      */     //   5862: aload #6
/*      */     //   5864: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   5867: invokevirtual unLock : ()V
/*      */     //   5870: aload #6
/*      */     //   5872: getfield view : Ljavax/media/j3d/View;
/*      */     //   5875: iconst_1
/*      */     //   5876: putfield inCanvasCallback : Z
/*      */     //   5879: aload #6
/*      */     //   5881: iload #10
/*      */     //   5883: invokevirtual renderField : (I)V
/*      */     //   5886: goto -> 5922
/*      */     //   5889: astore #57
/*      */     //   5891: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*      */     //   5894: ldc 'Exception occurred during Canvas3D callback:'
/*      */     //   5896: invokevirtual println : (Ljava/lang/String;)V
/*      */     //   5899: aload #57
/*      */     //   5901: invokevirtual printStackTrace : ()V
/*      */     //   5904: goto -> 5922
/*      */     //   5907: astore #57
/*      */     //   5909: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*      */     //   5912: ldc 'Error occurred during Canvas3D callback:'
/*      */     //   5914: invokevirtual println : (Ljava/lang/String;)V
/*      */     //   5917: aload #57
/*      */     //   5919: invokevirtual printStackTrace : ()V
/*      */     //   5922: aload #6
/*      */     //   5924: getfield view : Ljavax/media/j3d/View;
/*      */     //   5927: iconst_0
/*      */     //   5928: putfield inCanvasCallback : Z
/*      */     //   5931: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   5934: getfield doDsiRenderLock : Z
/*      */     //   5937: ifeq -> 5981
/*      */     //   5940: aload #6
/*      */     //   5942: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   5945: invokevirtual renderLock : ()Z
/*      */     //   5948: ifne -> 5981
/*      */     //   5951: aload #52
/*      */     //   5953: ifnull -> 5972
/*      */     //   5956: aload #52
/*      */     //   5958: invokevirtual isByReference : ()Z
/*      */     //   5961: ifeq -> 5972
/*      */     //   5964: aload #52
/*      */     //   5966: getfield geomLock : Ljavax/media/j3d/GeometryLock;
/*      */     //   5969: invokevirtual unLock : ()V
/*      */     //   5972: aload #6
/*      */     //   5974: iconst_0
/*      */     //   5975: putfield offScreenRendering : Z
/*      */     //   5978: goto -> 6330
/*      */     //   5981: aload #4
/*      */     //   5983: aload #6
/*      */     //   5985: invokevirtual renderTransparent : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   5988: iload #19
/*      */     //   5990: ifeq -> 6006
/*      */     //   5993: aload #6
/*      */     //   5995: aload #6
/*      */     //   5997: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   6000: ldc_w 0.125
/*      */     //   6003: invokevirtual accum : (Ljavax/media/j3d/Context;F)V
/*      */     //   6006: iinc #15, 1
/*      */     //   6009: goto -> 5134
/*      */     //   6012: iload #19
/*      */     //   6014: ifeq -> 6027
/*      */     //   6017: aload #6
/*      */     //   6019: aload #6
/*      */     //   6021: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   6024: invokevirtual accumReturn : (Ljavax/media/j3d/Context;)V
/*      */     //   6027: iload #54
/*      */     //   6029: ifeq -> 6041
/*      */     //   6032: iconst_1
/*      */     //   6033: istore #10
/*      */     //   6035: aload #6
/*      */     //   6037: iconst_1
/*      */     //   6038: putfield rightStereoPass : Z
/*      */     //   6041: iinc #14, 1
/*      */     //   6044: goto -> 5092
/*      */     //   6047: aload #6
/*      */     //   6049: iconst_1
/*      */     //   6050: putfield imageReady : Z
/*      */     //   6053: aload #6
/*      */     //   6055: iconst_0
/*      */     //   6056: putfield rightStereoPass : Z
/*      */     //   6059: aload #6
/*      */     //   6061: aload #6
/*      */     //   6063: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   6066: iconst_2
/*      */     //   6067: aload #6
/*      */     //   6069: getfield useDoubleBuffer : Z
/*      */     //   6072: invokevirtual setRenderMode : (Ljavax/media/j3d/Context;IZ)V
/*      */     //   6075: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   6078: getfield doDsiRenderLock : Z
/*      */     //   6081: ifeq -> 6092
/*      */     //   6084: aload #6
/*      */     //   6086: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   6089: invokevirtual unLock : ()V
/*      */     //   6092: aload #6
/*      */     //   6094: getfield view : Ljavax/media/j3d/View;
/*      */     //   6097: iconst_1
/*      */     //   6098: putfield inCanvasCallback : Z
/*      */     //   6101: aload #6
/*      */     //   6103: invokevirtual postRender : ()V
/*      */     //   6106: goto -> 6142
/*      */     //   6109: astore #57
/*      */     //   6111: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*      */     //   6114: ldc 'Exception occurred during Canvas3D callback:'
/*      */     //   6116: invokevirtual println : (Ljava/lang/String;)V
/*      */     //   6119: aload #57
/*      */     //   6121: invokevirtual printStackTrace : ()V
/*      */     //   6124: goto -> 6142
/*      */     //   6127: astore #57
/*      */     //   6129: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*      */     //   6132: ldc 'Error occurred during Canvas3D callback:'
/*      */     //   6134: invokevirtual println : (Ljava/lang/String;)V
/*      */     //   6137: aload #57
/*      */     //   6139: invokevirtual printStackTrace : ()V
/*      */     //   6142: aload #6
/*      */     //   6144: getfield view : Ljavax/media/j3d/View;
/*      */     //   6147: iconst_0
/*      */     //   6148: putfield inCanvasCallback : Z
/*      */     //   6151: aload #6
/*      */     //   6153: getfield offScreenRendering : Z
/*      */     //   6156: ifeq -> 6270
/*      */     //   6159: aload #6
/*      */     //   6161: aload #6
/*      */     //   6163: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   6166: iconst_1
/*      */     //   6167: invokevirtual syncRender : (Ljavax/media/j3d/Context;Z)V
/*      */     //   6170: aload #6
/*      */     //   6172: invokevirtual endOffScreenRendering : ()V
/*      */     //   6175: aload #6
/*      */     //   6177: iconst_0
/*      */     //   6178: putfield offScreenRendering : Z
/*      */     //   6181: aload #6
/*      */     //   6183: getfield manualRendering : Z
/*      */     //   6186: ifeq -> 6270
/*      */     //   6189: aload #6
/*      */     //   6191: getfield view : Ljavax/media/j3d/View;
/*      */     //   6194: iconst_1
/*      */     //   6195: putfield inCanvasCallback : Z
/*      */     //   6198: aload #6
/*      */     //   6200: invokevirtual postSwap : ()V
/*      */     //   6203: goto -> 6240
/*      */     //   6206: astore #57
/*      */     //   6208: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*      */     //   6211: ldc_w 'Exception occurred during Canvas 3D callback:'
/*      */     //   6214: invokevirtual println : (Ljava/lang/String;)V
/*      */     //   6217: aload #57
/*      */     //   6219: invokevirtual printStackTrace : ()V
/*      */     //   6222: goto -> 6240
/*      */     //   6225: astore #57
/*      */     //   6227: getstatic java/lang/System.err : Ljava/io/PrintStream;
/*      */     //   6230: ldc 'Error occurred during Canvas3D callback:'
/*      */     //   6232: invokevirtual println : (Ljava/lang/String;)V
/*      */     //   6235: aload #57
/*      */     //   6237: invokevirtual printStackTrace : ()V
/*      */     //   6240: aload #52
/*      */     //   6242: invokevirtual isByReference : ()Z
/*      */     //   6245: ifeq -> 6256
/*      */     //   6248: aload #52
/*      */     //   6250: getfield geomLock : Ljavax/media/j3d/GeometryLock;
/*      */     //   6253: invokevirtual unLock : ()V
/*      */     //   6256: aload #6
/*      */     //   6258: getfield view : Ljavax/media/j3d/View;
/*      */     //   6261: iconst_0
/*      */     //   6262: putfield inCanvasCallback : Z
/*      */     //   6265: aload #6
/*      */     //   6267: invokevirtual releaseCtx : ()V
/*      */     //   6270: aload #6
/*      */     //   6272: invokevirtual endScene : ()V
/*      */     //   6275: getstatic java/util/logging/Level.INFO : Ljava/util/logging/Level;
/*      */     //   6278: invokestatic isStatsLoggable : (Ljava/util/logging/Level;)Z
/*      */     //   6281: ifeq -> 6303
/*      */     //   6284: invokestatic nanoTime : ()J
/*      */     //   6287: lload #50
/*      */     //   6289: lsub
/*      */     //   6290: lstore #57
/*      */     //   6292: getstatic javax/media/j3d/VirtualUniverse.mc : Ljavax/media/j3d/MasterControl;
/*      */     //   6295: getstatic javax/media/j3d/MasterControl$TimeType.RENDER : Ljavax/media/j3d/MasterControl$TimeType;
/*      */     //   6298: lload #57
/*      */     //   6300: invokevirtual recordTime : (Ljavax/media/j3d/MasterControl$TimeType;J)V
/*      */     //   6303: goto -> 6327
/*      */     //   6306: aload #52
/*      */     //   6308: ifnull -> 6327
/*      */     //   6311: aload #52
/*      */     //   6313: invokevirtual isByReference : ()Z
/*      */     //   6316: ifeq -> 6327
/*      */     //   6319: aload #52
/*      */     //   6321: getfield geomLock : Ljavax/media/j3d/GeometryLock;
/*      */     //   6324: invokevirtual unLock : ()V
/*      */     //   6327: goto -> 979
/*      */     //   6330: iload #44
/*      */     //   6332: ifne -> 6345
/*      */     //   6335: aload_0
/*      */     //   6336: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   6339: iconst_0
/*      */     //   6340: aconst_null
/*      */     //   6341: aastore
/*      */     //   6342: goto -> 6356
/*      */     //   6345: aload_0
/*      */     //   6346: getfield m : [Ljavax/media/j3d/J3dMessage;
/*      */     //   6349: iconst_0
/*      */     //   6350: iload #49
/*      */     //   6352: aconst_null
/*      */     //   6353: invokestatic fill : ([Ljava/lang/Object;IILjava/lang/Object;)V
/*      */     //   6356: goto -> 6479
/*      */     //   6359: astore #48
/*      */     //   6361: aload #48
/*      */     //   6363: invokevirtual printStackTrace : ()V
/*      */     //   6366: aload #6
/*      */     //   6368: ifnull -> 6392
/*      */     //   6371: aload #6
/*      */     //   6373: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   6376: ifnull -> 6384
/*      */     //   6379: aload #6
/*      */     //   6381: invokevirtual endScene : ()V
/*      */     //   6384: aload #6
/*      */     //   6386: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   6389: invokevirtual unLock : ()V
/*      */     //   6392: goto -> 6479
/*      */     //   6395: astore #48
/*      */     //   6397: aload #48
/*      */     //   6399: invokevirtual printStackTrace : ()V
/*      */     //   6402: aload #6
/*      */     //   6404: ifnull -> 6428
/*      */     //   6407: aload #6
/*      */     //   6409: getfield ctx : Ljavax/media/j3d/Context;
/*      */     //   6412: ifnull -> 6420
/*      */     //   6415: aload #6
/*      */     //   6417: invokevirtual endScene : ()V
/*      */     //   6420: aload #6
/*      */     //   6422: getfield drawingSurfaceObject : Ljavax/media/j3d/DrawingSurfaceObject;
/*      */     //   6425: invokevirtual unLock : ()V
/*      */     //   6428: aload #6
/*      */     //   6430: invokevirtual setFatalError : ()V
/*      */     //   6433: new javax/media/j3d/RenderingError
/*      */     //   6436: dup
/*      */     //   6437: iconst_1
/*      */     //   6438: ldc_w 'Renderer8'
/*      */     //   6441: invokestatic getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */     //   6444: invokespecial <init> : (ILjava/lang/String;)V
/*      */     //   6447: astore #49
/*      */     //   6449: aload #49
/*      */     //   6451: aload #6
/*      */     //   6453: invokevirtual setCanvas3D : (Ljavax/media/j3d/Canvas3D;)V
/*      */     //   6456: aload #6
/*      */     //   6458: ifnull -> 6474
/*      */     //   6461: aload #49
/*      */     //   6463: aload #6
/*      */     //   6465: getfield graphicsConfiguration : Ljava/awt/GraphicsConfiguration;
/*      */     //   6468: invokevirtual getDevice : ()Ljava/awt/GraphicsDevice;
/*      */     //   6471: invokevirtual setGraphicsDevice : (Ljava/awt/GraphicsDevice;)V
/*      */     //   6474: aload #49
/*      */     //   6476: invokestatic notifyErrorListeners : (Ljavax/media/j3d/RenderingError;)V
/*      */     //   6479: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #191	-> 0
/*      */     //   #192	-> 3
/*      */     //   #194	-> 6
/*      */     //   #197	-> 9
/*      */     //   #199	-> 12
/*      */     //   #200	-> 15
/*      */     //   #201	-> 21
/*      */     //   #203	-> 27
/*      */     //   #204	-> 33
/*      */     //   #205	-> 39
/*      */     //   #206	-> 45
/*      */     //   #208	-> 51
/*      */     //   #209	-> 54
/*      */     //   #211	-> 57
/*      */     //   #214	-> 71
/*      */     //   #216	-> 77
/*      */     //   #218	-> 91
/*      */     //   #220	-> 102
/*      */     //   #221	-> 113
/*      */     //   #222	-> 123
/*      */     //   #223	-> 131
/*      */     //   #228	-> 134
/*      */     //   #229	-> 142
/*      */     //   #232	-> 145
/*      */     //   #234	-> 177
/*      */     //   #235	-> 185
/*      */     //   #236	-> 194
/*      */     //   #237	-> 202
/*      */     //   #243	-> 211
/*      */     //   #244	-> 222
/*      */     //   #246	-> 228
/*      */     //   #247	-> 233
/*      */     //   #248	-> 244
/*      */     //   #251	-> 269
/*      */     //   #252	-> 274
/*      */     //   #254	-> 281
/*      */     //   #256	-> 292
/*      */     //   #258	-> 297
/*      */     //   #259	-> 308
/*      */     //   #262	-> 333
/*      */     //   #263	-> 338
/*      */     //   #268	-> 345
/*      */     //   #270	-> 359
/*      */     //   #272	-> 368
/*      */     //   #280	-> 373
/*      */     //   #273	-> 376
/*      */     //   #274	-> 378
/*      */     //   #275	-> 386
/*      */     //   #280	-> 391
/*      */     //   #276	-> 394
/*      */     //   #278	-> 396
/*      */     //   #279	-> 404
/*      */     //   #282	-> 409
/*      */     //   #283	-> 415
/*      */     //   #286	-> 424
/*      */     //   #288	-> 433
/*      */     //   #289	-> 438
/*      */     //   #290	-> 447
/*      */     //   #291	-> 453
/*      */     //   #299	-> 464
/*      */     //   #300	-> 472
/*      */     //   #307	-> 480
/*      */     //   #310	-> 486
/*      */     //   #312	-> 495
/*      */     //   #335	-> 501
/*      */     //   #315	-> 504
/*      */     //   #317	-> 506
/*      */     //   #318	-> 515
/*      */     //   #335	-> 523
/*      */     //   #320	-> 526
/*      */     //   #321	-> 528
/*      */     //   #323	-> 533
/*      */     //   #324	-> 542
/*      */     //   #328	-> 550
/*      */     //   #329	-> 555
/*      */     //   #332	-> 570
/*      */     //   #333	-> 577
/*      */     //   #334	-> 590
/*      */     //   #337	-> 595
/*      */     //   #220	-> 600
/*      */     //   #340	-> 606
/*      */     //   #342	-> 611
/*      */     //   #343	-> 622
/*      */     //   #348	-> 632
/*      */     //   #349	-> 641
/*      */     //   #351	-> 652
/*      */     //   #353	-> 660
/*      */     //   #354	-> 667
/*      */     //   #356	-> 675
/*      */     //   #357	-> 686
/*      */     //   #359	-> 716
/*      */     //   #361	-> 724
/*      */     //   #362	-> 735
/*      */     //   #363	-> 743
/*      */     //   #365	-> 748
/*      */     //   #366	-> 770
/*      */     //   #368	-> 778
/*      */     //   #369	-> 792
/*      */     //   #370	-> 801
/*      */     //   #377	-> 846
/*      */     //   #381	-> 847
/*      */     //   #382	-> 852
/*      */     //   #383	-> 855
/*      */     //   #384	-> 860
/*      */     //   #385	-> 868
/*      */     //   #387	-> 881
/*      */     //   #388	-> 896
/*      */     //   #391	-> 910
/*      */     //   #393	-> 921
/*      */     //   #394	-> 930
/*      */     //   #395	-> 947
/*      */     //   #397	-> 953
/*      */     //   #398	-> 964
/*      */     //   #399	-> 973
/*      */     //   #400	-> 978
/*      */     //   #405	-> 979
/*      */     //   #407	-> 988
/*      */     //   #409	-> 1004
/*      */     //   #410	-> 1009
/*      */     //   #411	-> 1025
/*      */     //   #413	-> 1033
/*      */     //   #414	-> 1052
/*      */     //   #415	-> 1059
/*      */     //   #417	-> 1067
/*      */     //   #420	-> 1075
/*      */     //   #421	-> 1081
/*      */     //   #423	-> 1090
/*      */     //   #424	-> 1100
/*      */     //   #428	-> 1110
/*      */     //   #429	-> 1120
/*      */     //   #431	-> 1135
/*      */     //   #433	-> 1158
/*      */     //   #436	-> 1181
/*      */     //   #439	-> 1191
/*      */     //   #440	-> 1199
/*      */     //   #442	-> 1208
/*      */     //   #456	-> 1218
/*      */     //   #445	-> 1221
/*      */     //   #446	-> 1223
/*      */     //   #449	-> 1228
/*      */     //   #450	-> 1233
/*      */     //   #453	-> 1248
/*      */     //   #454	-> 1255
/*      */     //   #455	-> 1268
/*      */     //   #457	-> 1273
/*      */     //   #458	-> 1280
/*      */     //   #460	-> 1288
/*      */     //   #472	-> 1293
/*      */     //   #461	-> 1296
/*      */     //   #462	-> 1298
/*      */     //   #465	-> 1303
/*      */     //   #466	-> 1308
/*      */     //   #469	-> 1323
/*      */     //   #470	-> 1330
/*      */     //   #471	-> 1343
/*      */     //   #474	-> 1348
/*      */     //   #475	-> 1352
/*      */     //   #476	-> 1357
/*      */     //   #478	-> 1362
/*      */     //   #482	-> 1373
/*      */     //   #483	-> 1381
/*      */     //   #486	-> 1389
/*      */     //   #487	-> 1401
/*      */     //   #488	-> 1409
/*      */     //   #490	-> 1416
/*      */     //   #491	-> 1435
/*      */     //   #492	-> 1443
/*      */     //   #493	-> 1446
/*      */     //   #495	-> 1459
/*      */     //   #507	-> 1471
/*      */     //   #496	-> 1474
/*      */     //   #497	-> 1476
/*      */     //   #507	-> 1481
/*      */     //   #498	-> 1484
/*      */     //   #499	-> 1486
/*      */     //   #502	-> 1491
/*      */     //   #505	-> 1506
/*      */     //   #506	-> 1518
/*      */     //   #509	-> 1523
/*      */     //   #510	-> 1530
/*      */     //   #511	-> 1541
/*      */     //   #512	-> 1544
/*      */     //   #514	-> 1554
/*      */     //   #515	-> 1567
/*      */     //   #528	-> 1570
/*      */     //   #517	-> 1573
/*      */     //   #518	-> 1575
/*      */     //   #528	-> 1580
/*      */     //   #519	-> 1583
/*      */     //   #520	-> 1585
/*      */     //   #523	-> 1590
/*      */     //   #526	-> 1605
/*      */     //   #527	-> 1615
/*      */     //   #530	-> 1620
/*      */     //   #532	-> 1630
/*      */     //   #535	-> 1637
/*      */     //   #536	-> 1656
/*      */     //   #539	-> 1659
/*      */     //   #541	-> 1666
/*      */     //   #543	-> 1680
/*      */     //   #547	-> 1687
/*      */     //   #552	-> 1693
/*      */     //   #560	-> 1733
/*      */     //   #558	-> 1736
/*      */     //   #559	-> 1738
/*      */     //   #562	-> 1743
/*      */     //   #564	-> 1751
/*      */     //   #565	-> 1756
/*      */     //   #568	-> 1771
/*      */     //   #569	-> 1778
/*      */     //   #570	-> 1791
/*      */     //   #573	-> 1796
/*      */     //   #574	-> 1802
/*      */     //   #575	-> 1821
/*      */     //   #577	-> 1824
/*      */     //   #578	-> 1831
/*      */     //   #582	-> 1845
/*      */     //   #588	-> 1890
/*      */     //   #589	-> 1896
/*      */     //   #590	-> 1915
/*      */     //   #591	-> 1918
/*      */     //   #592	-> 1925
/*      */     //   #593	-> 1933
/*      */     //   #594	-> 1940
/*      */     //   #597	-> 1945
/*      */     //   #599	-> 1961
/*      */     //   #600	-> 1968
/*      */     //   #602	-> 1974
/*      */     //   #603	-> 1993
/*      */     //   #606	-> 1996
/*      */     //   #608	-> 2011
/*      */     //   #609	-> 2030
/*      */     //   #612	-> 2033
/*      */     //   #613	-> 2040
/*      */     //   #615	-> 2045
/*      */     //   #616	-> 2058
/*      */     //   #617	-> 2080
/*      */     //   #618	-> 2087
/*      */     //   #619	-> 2092
/*      */     //   #620	-> 2105
/*      */     //   #621	-> 2127
/*      */     //   #622	-> 2134
/*      */     //   #624	-> 2156
/*      */     //   #625	-> 2163
/*      */     //   #626	-> 2175
/*      */     //   #629	-> 2180
/*      */     //   #630	-> 2188
/*      */     //   #634	-> 2191
/*      */     //   #636	-> 2199
/*      */     //   #639	-> 2204
/*      */     //   #641	-> 2328
/*      */     //   #642	-> 2336
/*      */     //   #644	-> 2339
/*      */     //   #646	-> 2364
/*      */     //   #648	-> 2367
/*      */     //   #649	-> 2372
/*      */     //   #651	-> 2375
/*      */     //   #653	-> 2400
/*      */     //   #655	-> 2403
/*      */     //   #657	-> 2428
/*      */     //   #659	-> 2431
/*      */     //   #661	-> 2456
/*      */     //   #663	-> 2459
/*      */     //   #665	-> 2484
/*      */     //   #667	-> 2487
/*      */     //   #670	-> 2532
/*      */     //   #672	-> 2535
/*      */     //   #675	-> 2580
/*      */     //   #677	-> 2583
/*      */     //   #679	-> 2611
/*      */     //   #681	-> 2614
/*      */     //   #683	-> 2639
/*      */     //   #685	-> 2642
/*      */     //   #687	-> 2667
/*      */     //   #689	-> 2670
/*      */     //   #690	-> 2689
/*      */     //   #691	-> 2699
/*      */     //   #693	-> 2702
/*      */     //   #694	-> 2721
/*      */     //   #695	-> 2731
/*      */     //   #697	-> 2734
/*      */     //   #700	-> 2779
/*      */     //   #702	-> 2782
/*      */     //   #705	-> 2827
/*      */     //   #707	-> 2830
/*      */     //   #709	-> 2858
/*      */     //   #711	-> 2861
/*      */     //   #713	-> 2886
/*      */     //   #715	-> 2889
/*      */     //   #717	-> 2914
/*      */     //   #719	-> 2917
/*      */     //   #721	-> 2945
/*      */     //   #723	-> 2948
/*      */     //   #725	-> 2976
/*      */     //   #727	-> 2979
/*      */     //   #729	-> 3007
/*      */     //   #731	-> 3010
/*      */     //   #733	-> 3038
/*      */     //   #735	-> 3041
/*      */     //   #736	-> 3049
/*      */     //   #738	-> 3052
/*      */     //   #739	-> 3066
/*      */     //   #744	-> 3108
/*      */     //   #746	-> 3111
/*      */     //   #747	-> 3119
/*      */     //   #749	-> 3122
/*      */     //   #751	-> 3147
/*      */     //   #756	-> 3150
/*      */     //   #757	-> 3158
/*      */     //   #770	-> 3163
/*      */     //   #759	-> 3166
/*      */     //   #760	-> 3168
/*      */     //   #763	-> 3173
/*      */     //   #764	-> 3178
/*      */     //   #767	-> 3193
/*      */     //   #768	-> 3200
/*      */     //   #769	-> 3213
/*      */     //   #772	-> 3218
/*      */     //   #773	-> 3237
/*      */     //   #774	-> 3240
/*      */     //   #775	-> 3243
/*      */     //   #777	-> 3252
/*      */     //   #780	-> 3257
/*      */     //   #782	-> 3276
/*      */     //   #783	-> 3284
/*      */     //   #786	-> 3287
/*      */     //   #788	-> 3290
/*      */     //   #792	-> 3297
/*      */     //   #793	-> 3303
/*      */     //   #794	-> 3319
/*      */     //   #795	-> 3325
/*      */     //   #797	-> 3328
/*      */     //   #800	-> 3341
/*      */     //   #801	-> 3349
/*      */     //   #804	-> 3357
/*      */     //   #808	-> 3367
/*      */     //   #809	-> 3375
/*      */     //   #816	-> 3378
/*      */     //   #817	-> 3386
/*      */     //   #820	-> 3394
/*      */     //   #827	-> 3404
/*      */     //   #829	-> 3412
/*      */     //   #830	-> 3419
/*      */     //   #831	-> 3431
/*      */     //   #834	-> 3440
/*      */     //   #835	-> 3451
/*      */     //   #837	-> 3464
/*      */     //   #839	-> 3472
/*      */     //   #840	-> 3478
/*      */     //   #843	-> 3481
/*      */     //   #844	-> 3491
/*      */     //   #846	-> 3496
/*      */     //   #849	-> 3507
/*      */     //   #847	-> 3510
/*      */     //   #848	-> 3512
/*      */     //   #851	-> 3517
/*      */     //   #852	-> 3524
/*      */     //   #853	-> 3532
/*      */     //   #855	-> 3545
/*      */     //   #857	-> 3553
/*      */     //   #860	-> 3559
/*      */     //   #861	-> 3564
/*      */     //   #864	-> 3579
/*      */     //   #865	-> 3586
/*      */     //   #866	-> 3599
/*      */     //   #868	-> 3604
/*      */     //   #870	-> 3610
/*      */     //   #873	-> 3620
/*      */     //   #874	-> 3625
/*      */     //   #876	-> 3639
/*      */     //   #880	-> 3647
/*      */     //   #883	-> 3655
/*      */     //   #884	-> 3666
/*      */     //   #886	-> 3679
/*      */     //   #888	-> 3687
/*      */     //   #889	-> 3693
/*      */     //   #892	-> 3696
/*      */     //   #893	-> 3706
/*      */     //   #895	-> 3712
/*      */     //   #898	-> 3727
/*      */     //   #896	-> 3730
/*      */     //   #897	-> 3732
/*      */     //   #900	-> 3737
/*      */     //   #901	-> 3745
/*      */     //   #902	-> 3753
/*      */     //   #904	-> 3766
/*      */     //   #906	-> 3774
/*      */     //   #909	-> 3780
/*      */     //   #910	-> 3785
/*      */     //   #913	-> 3800
/*      */     //   #914	-> 3807
/*      */     //   #915	-> 3820
/*      */     //   #917	-> 3825
/*      */     //   #920	-> 3831
/*      */     //   #921	-> 3839
/*      */     //   #924	-> 3847
/*      */     //   #926	-> 3858
/*      */     //   #927	-> 3871
/*      */     //   #929	-> 3881
/*      */     //   #930	-> 3892
/*      */     //   #931	-> 3908
/*      */     //   #932	-> 3923
/*      */     //   #933	-> 3931
/*      */     //   #930	-> 3941
/*      */     //   #939	-> 3947
/*      */     //   #940	-> 3952
/*      */     //   #944	-> 3966
/*      */     //   #945	-> 3974
/*      */     //   #948	-> 3979
/*      */     //   #949	-> 3985
/*      */     //   #951	-> 3993
/*      */     //   #952	-> 4001
/*      */     //   #954	-> 4007
/*      */     //   #957	-> 4018
/*      */     //   #958	-> 4026
/*      */     //   #963	-> 4031
/*      */     //   #964	-> 4036
/*      */     //   #966	-> 4056
/*      */     //   #968	-> 4069
/*      */     //   #970	-> 4077
/*      */     //   #971	-> 4083
/*      */     //   #974	-> 4086
/*      */     //   #975	-> 4093
/*      */     //   #976	-> 4105
/*      */     //   #979	-> 4110
/*      */     //   #980	-> 4118
/*      */     //   #982	-> 4127
/*      */     //   #985	-> 4137
/*      */     //   #986	-> 4146
/*      */     //   #991	-> 4154
/*      */     //   #995	-> 4161
/*      */     //   #998	-> 4182
/*      */     //   #1000	-> 4202
/*      */     //   #1002	-> 4215
/*      */     //   #1004	-> 4223
/*      */     //   #1005	-> 4229
/*      */     //   #1009	-> 4232
/*      */     //   #1016	-> 4254
/*      */     //   #1017	-> 4262
/*      */     //   #1018	-> 4269
/*      */     //   #1020	-> 4277
/*      */     //   #1023	-> 4282
/*      */     //   #1024	-> 4289
/*      */     //   #1028	-> 4309
/*      */     //   #1033	-> 4314
/*      */     //   #1035	-> 4323
/*      */     //   #1036	-> 4328
/*      */     //   #1037	-> 4339
/*      */     //   #1036	-> 4356
/*      */     //   #1040	-> 4362
/*      */     //   #1043	-> 4369
/*      */     //   #1045	-> 4372
/*      */     //   #1046	-> 4380
/*      */     //   #1047	-> 4387
/*      */     //   #1049	-> 4393
/*      */     //   #1050	-> 4401
/*      */     //   #1054	-> 4424
/*      */     //   #1059	-> 4430
/*      */     //   #1061	-> 4446
/*      */     //   #1063	-> 4464
/*      */     //   #1069	-> 4477
/*      */     //   #1070	-> 4484
/*      */     //   #1071	-> 4489
/*      */     //   #1072	-> 4492
/*      */     //   #1074	-> 4495
/*      */     //   #1077	-> 4508
/*      */     //   #1078	-> 4511
/*      */     //   #1082	-> 4514
/*      */     //   #1086	-> 4519
/*      */     //   #1089	-> 4538
/*      */     //   #1092	-> 4566
/*      */     //   #1093	-> 4569
/*      */     //   #1095	-> 4573
/*      */     //   #1100	-> 4595
/*      */     //   #1104	-> 4624
/*      */     //   #1109	-> 4653
/*      */     //   #1110	-> 4664
/*      */     //   #1112	-> 4676
/*      */     //   #1113	-> 4681
/*      */     //   #1116	-> 4703
/*      */     //   #1117	-> 4714
/*      */     //   #1120	-> 4726
/*      */     //   #1121	-> 4734
/*      */     //   #1124	-> 4756
/*      */     //   #1125	-> 4767
/*      */     //   #1126	-> 4779
/*      */     //   #1127	-> 4784
/*      */     //   #1130	-> 4806
/*      */     //   #1131	-> 4817
/*      */     //   #1136	-> 4832
/*      */     //   #1139	-> 4840
/*      */     //   #1140	-> 4851
/*      */     //   #1145	-> 4860
/*      */     //   #1147	-> 4868
/*      */     //   #1148	-> 4879
/*      */     //   #1153	-> 4885
/*      */     //   #1154	-> 4893
/*      */     //   #1158	-> 4898
/*      */     //   #1162	-> 4914
/*      */     //   #1165	-> 4919
/*      */     //   #1166	-> 4926
/*      */     //   #1171	-> 4933
/*      */     //   #1172	-> 4945
/*      */     //   #1174	-> 4952
/*      */     //   #1179	-> 4963
/*      */     //   #1180	-> 4972
/*      */     //   #1182	-> 4980
/*      */     //   #1185	-> 4989
/*      */     //   #1193	-> 4994
/*      */     //   #1186	-> 4997
/*      */     //   #1187	-> 4999
/*      */     //   #1188	-> 5007
/*      */     //   #1193	-> 5012
/*      */     //   #1189	-> 5015
/*      */     //   #1191	-> 5017
/*      */     //   #1192	-> 5025
/*      */     //   #1194	-> 5030
/*      */     //   #1196	-> 5039
/*      */     //   #1198	-> 5059
/*      */     //   #1200	-> 5072
/*      */     //   #1202	-> 5080
/*      */     //   #1203	-> 5086
/*      */     //   #1207	-> 5089
/*      */     //   #1208	-> 5099
/*      */     //   #1209	-> 5104
/*      */     //   #1211	-> 5114
/*      */     //   #1216	-> 5131
/*      */     //   #1220	-> 5141
/*      */     //   #1221	-> 5146
/*      */     //   #1223	-> 5158
/*      */     //   #1226	-> 5170
/*      */     //   #1230	-> 5207
/*      */     //   #1234	-> 5245
/*      */     //   #1235	-> 5250
/*      */     //   #1239	-> 5287
/*      */     //   #1244	-> 5325
/*      */     //   #1245	-> 5333
/*      */     //   #1249	-> 5370
/*      */     //   #1253	-> 5408
/*      */     //   #1254	-> 5413
/*      */     //   #1259	-> 5450
/*      */     //   #1269	-> 5488
/*      */     //   #1270	-> 5500
/*      */     //   #1272	-> 5507
/*      */     //   #1277	-> 5518
/*      */     //   #1280	-> 5526
/*      */     //   #1281	-> 5531
/*      */     //   #1283	-> 5541
/*      */     //   #1284	-> 5546
/*      */     //   #1287	-> 5563
/*      */     //   #1292	-> 5581
/*      */     //   #1294	-> 5591
/*      */     //   #1295	-> 5596
/*      */     //   #1298	-> 5613
/*      */     //   #1303	-> 5628
/*      */     //   #1307	-> 5646
/*      */     //   #1311	-> 5653
/*      */     //   #1312	-> 5658
/*      */     //   #1313	-> 5668
/*      */     //   #1314	-> 5673
/*      */     //   #1316	-> 5690
/*      */     //   #1320	-> 5708
/*      */     //   #1321	-> 5718
/*      */     //   #1322	-> 5723
/*      */     //   #1325	-> 5740
/*      */     //   #1329	-> 5755
/*      */     //   #1333	-> 5773
/*      */     //   #1334	-> 5779
/*      */     //   #1335	-> 5784
/*      */     //   #1337	-> 5797
/*      */     //   #1339	-> 5807
/*      */     //   #1342	-> 5821
/*      */     //   #1343	-> 5826
/*      */     //   #1347	-> 5839
/*      */     //   #1350	-> 5846
/*      */     //   #1353	-> 5853
/*      */     //   #1354	-> 5862
/*      */     //   #1356	-> 5870
/*      */     //   #1358	-> 5879
/*      */     //   #1366	-> 5886
/*      */     //   #1359	-> 5889
/*      */     //   #1360	-> 5891
/*      */     //   #1361	-> 5899
/*      */     //   #1366	-> 5904
/*      */     //   #1362	-> 5907
/*      */     //   #1364	-> 5909
/*      */     //   #1365	-> 5917
/*      */     //   #1367	-> 5922
/*      */     //   #1368	-> 5931
/*      */     //   #1370	-> 5951
/*      */     //   #1372	-> 5964
/*      */     //   #1374	-> 5972
/*      */     //   #1375	-> 5978
/*      */     //   #1379	-> 5981
/*      */     //   #1381	-> 5988
/*      */     //   #1382	-> 5993
/*      */     //   #1216	-> 6006
/*      */     //   #1385	-> 6012
/*      */     //   #1386	-> 6017
/*      */     //   #1387	-> 6027
/*      */     //   #1388	-> 6032
/*      */     //   #1389	-> 6035
/*      */     //   #1207	-> 6041
/*      */     //   #1392	-> 6047
/*      */     //   #1393	-> 6053
/*      */     //   #1396	-> 6059
/*      */     //   #1401	-> 6075
/*      */     //   #1402	-> 6084
/*      */     //   #1404	-> 6092
/*      */     //   #1407	-> 6101
/*      */     //   #1415	-> 6106
/*      */     //   #1408	-> 6109
/*      */     //   #1409	-> 6111
/*      */     //   #1410	-> 6119
/*      */     //   #1415	-> 6124
/*      */     //   #1411	-> 6127
/*      */     //   #1413	-> 6129
/*      */     //   #1414	-> 6137
/*      */     //   #1416	-> 6142
/*      */     //   #1419	-> 6151
/*      */     //   #1421	-> 6159
/*      */     //   #1422	-> 6170
/*      */     //   #1423	-> 6175
/*      */     //   #1427	-> 6181
/*      */     //   #1429	-> 6189
/*      */     //   #1431	-> 6198
/*      */     //   #1439	-> 6203
/*      */     //   #1432	-> 6206
/*      */     //   #1433	-> 6208
/*      */     //   #1434	-> 6217
/*      */     //   #1439	-> 6222
/*      */     //   #1435	-> 6225
/*      */     //   #1437	-> 6227
/*      */     //   #1438	-> 6235
/*      */     //   #1441	-> 6240
/*      */     //   #1442	-> 6248
/*      */     //   #1445	-> 6256
/*      */     //   #1447	-> 6265
/*      */     //   #1451	-> 6270
/*      */     //   #1453	-> 6275
/*      */     //   #1455	-> 6284
/*      */     //   #1456	-> 6292
/*      */     //   #1459	-> 6303
/*      */     //   #1460	-> 6306
/*      */     //   #1462	-> 6319
/*      */     //   #1465	-> 6327
/*      */     //   #1469	-> 6330
/*      */     //   #1470	-> 6335
/*      */     //   #1472	-> 6345
/*      */     //   #1509	-> 6356
/*      */     //   #1475	-> 6359
/*      */     //   #1477	-> 6361
/*      */     //   #1478	-> 6366
/*      */     //   #1479	-> 6371
/*      */     //   #1480	-> 6379
/*      */     //   #1484	-> 6384
/*      */     //   #1509	-> 6392
/*      */     //   #1487	-> 6395
/*      */     //   #1488	-> 6397
/*      */     //   #1490	-> 6402
/*      */     //   #1491	-> 6407
/*      */     //   #1492	-> 6415
/*      */     //   #1496	-> 6420
/*      */     //   #1500	-> 6428
/*      */     //   #1501	-> 6433
/*      */     //   #1504	-> 6449
/*      */     //   #1505	-> 6456
/*      */     //   #1506	-> 6461
/*      */     //   #1508	-> 6474
/*      */     //   #1510	-> 6479
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   71	846	6359	java/lang/NullPointerException
/*      */     //   71	846	6395	java/lang/RuntimeException
/*      */     //   134	142	504	java/lang/NullPointerException
/*      */     //   134	142	526	java/lang/RuntimeException
/*      */     //   145	225	504	java/lang/NullPointerException
/*      */     //   145	225	526	java/lang/RuntimeException
/*      */     //   194	225	351	finally
/*      */     //   228	348	351	finally
/*      */     //   228	501	504	java/lang/NullPointerException
/*      */     //   228	501	526	java/lang/RuntimeException
/*      */     //   351	356	351	finally
/*      */     //   368	373	376	java/lang/RuntimeException
/*      */     //   368	373	394	java/lang/Error
/*      */     //   847	978	6359	java/lang/NullPointerException
/*      */     //   847	978	6395	java/lang/RuntimeException
/*      */     //   979	6356	6359	java/lang/NullPointerException
/*      */     //   979	6356	6395	java/lang/RuntimeException
/*      */     //   1067	1218	1221	java/lang/RuntimeException
/*      */     //   1288	1293	1296	java/lang/RuntimeException
/*      */     //   1459	1471	1474	java/lang/NullPointerException
/*      */     //   1459	1471	1484	java/lang/RuntimeException
/*      */     //   1554	1570	1573	java/lang/NullPointerException
/*      */     //   1554	1570	1583	java/lang/RuntimeException
/*      */     //   1693	1733	1736	java/lang/RuntimeException
/*      */     //   2191	3163	3166	java/lang/RuntimeException
/*      */     //   3491	3607	3631	finally
/*      */     //   3496	3507	3510	java/lang/RuntimeException
/*      */     //   3610	3628	3631	finally
/*      */     //   3631	3636	3631	finally
/*      */     //   3706	3828	3958	finally
/*      */     //   3712	3727	3730	java/lang/RuntimeException
/*      */     //   3831	3955	3958	finally
/*      */     //   3958	3963	3958	finally
/*      */     //   4989	4994	4997	java/lang/RuntimeException
/*      */     //   4989	4994	5015	java/lang/Error
/*      */     //   5779	5810	5813	finally
/*      */     //   5813	5818	5813	finally
/*      */     //   5879	5886	5889	java/lang/RuntimeException
/*      */     //   5879	5886	5907	java/lang/Error
/*      */     //   6101	6106	6109	java/lang/RuntimeException
/*      */     //   6101	6106	6127	java/lang/Error
/*      */     //   6198	6203	6206	java/lang/RuntimeException
/*      */     //   6198	6203	6225	java/lang/Error }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void shutdown() {
/* 1514 */     removeAllCtxs();
/* 1515 */     Pipeline.getPipeline().cleanupRenderer();
/*      */   }
/*      */   
/*      */   void cleanup() {
/* 1519 */     super.cleanup();
/* 1520 */     this.renderMessage = new J3dMessage[1];
/* 1521 */     this.rendererStructure = new RendererStructure();
/* 1522 */     this.bgVworldToVpc = new Transform3D();
/* 1523 */     this.sharedCtx = null;
/* 1524 */     this.sharedCtxTimeStamp = 0L;
/* 1525 */     this.sharedCtxDisplay = 0L;
/* 1526 */     this.sharedCtxDrawable = null;
/* 1527 */     this.dirtyRenderMoleculeList.clear();
/* 1528 */     this.dirtyRenderAtomList.clear();
/* 1529 */     this.dirtyDlistPerRinfoList.clear();
/* 1530 */     this.textureIdResourceFreeList.clear();
/* 1531 */     this.displayListResourceFreeList.clear();
/* 1532 */     this.onScreen = null;
/* 1533 */     this.offScreen = null;
/* 1534 */     this.m = null;
/* 1535 */     this.nmesg = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void makeCtxCurrent(Context paramContext, long paramLong, Drawable paramDrawable) {
/* 1542 */     if (paramContext != this.currentCtx || paramDrawable != this.currentDrawable) {
/* 1543 */       Canvas3D.useCtx(paramContext, paramLong, paramDrawable);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1550 */       this.currentCtx = paramContext;
/* 1551 */       this.currentDrawable = paramDrawable;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void removeCtx(Canvas3D paramCanvas3D, long paramLong, Drawable paramDrawable, Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
/* 1563 */     synchronized (VirtualUniverse.mc.contextCreationLock) {
/* 1564 */       if (paramContext != null) {
/* 1565 */         int i = this.listOfCtxs.indexOf(paramContext);
/* 1566 */         if (i >= 0) {
/* 1567 */           this.listOfCtxs.remove(i);
/* 1568 */           this.listOfCanvases.remove(i);
/*      */           
/* 1570 */           if (paramDrawable != null && paramCanvas3D.added)
/*      */           {
/*      */             
/* 1573 */             if (paramCanvas3D.drawingSurfaceObject.renderLock()) {
/*      */               
/* 1575 */               if (this.sharedCtx != null) {
/* 1576 */                 if (this.listOfCtxs.isEmpty()) {
/* 1577 */                   makeCtxCurrent(this.sharedCtx, this.sharedCtxDisplay, this.sharedCtxDrawable);
/* 1578 */                   freeResourcesInFreeList(null);
/* 1579 */                   freeContextResources();
/* 1580 */                   Canvas3D.destroyContext(this.sharedCtxDisplay, this.sharedCtxDrawable, this.sharedCtx);
/* 1581 */                   this.currentCtx = null;
/* 1582 */                   this.currentDrawable = null;
/*      */                 } else {
/* 1584 */                   freeResourcesInFreeList(paramCanvas3D);
/*      */                 } 
/* 1586 */                 paramCanvas3D.makeCtxCurrent(paramContext, paramLong, paramDrawable);
/*      */               } else {
/* 1588 */                 paramCanvas3D.makeCtxCurrent(paramContext, paramLong, paramDrawable);
/* 1589 */                 paramCanvas3D.freeResourcesInFreeList(paramContext);
/*      */               } 
/* 1591 */               paramCanvas3D.freeContextResources(this, paramBoolean2, paramContext);
/* 1592 */               Canvas3D.destroyContext(paramLong, paramDrawable, paramContext);
/* 1593 */               this.currentCtx = null;
/* 1594 */               this.currentDrawable = null;
/* 1595 */               paramCanvas3D.drawingSurfaceObject.unLock();
/*      */             } 
/*      */           }
/*      */         } 
/*      */         
/* 1600 */         if (paramBoolean1) {
/* 1601 */           paramCanvas3D.ctx = null;
/*      */         }
/*      */         
/* 1604 */         if (this.sharedCtx != null && this.listOfCtxs.isEmpty()) {
/* 1605 */           this.sharedCtx = null;
/* 1606 */           this.sharedCtxTimeStamp = 0L;
/*      */         } 
/* 1608 */         paramCanvas3D.ctxTimeStamp = 0L;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1614 */       if (paramBoolean3) {
/* 1615 */         paramCanvas3D.destroyOffScreenBuffer(paramContext, paramLong, paramCanvas3D.fbConfig, paramDrawable);
/* 1616 */         paramCanvas3D.offScreenBufferPending = false;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void removeAllCtxs() {
/* 1624 */     synchronized (VirtualUniverse.mc.contextCreationLock) {
/*      */       
/* 1626 */       for (int i = this.listOfCanvases.size() - 1; i >= 0; i--) {
/* 1627 */         Canvas3D canvas3D = (Canvas3D)this.listOfCanvases.get(i);
/*      */         
/* 1629 */         if (canvas3D.screen != null && canvas3D.ctx != null)
/*      */         {
/* 1631 */           if (canvas3D.drawable != null && canvas3D.added && 
/* 1632 */             canvas3D.drawingSurfaceObject.renderLock()) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1637 */             if (i == 0 && this.sharedCtx != null) {
/* 1638 */               makeCtxCurrent(this.sharedCtx, this.sharedCtxDisplay, this.sharedCtxDrawable);
/* 1639 */               freeResourcesInFreeList(null);
/* 1640 */               freeContextResources();
/* 1641 */               Canvas3D.destroyContext(this.sharedCtxDisplay, this.sharedCtxDrawable, this.sharedCtx);
/* 1642 */               this.currentCtx = null;
/* 1643 */               this.currentDrawable = null;
/*      */             } 
/* 1645 */             canvas3D.makeCtxCurrent();
/* 1646 */             canvas3D.freeResourcesInFreeList(canvas3D.ctx);
/* 1647 */             canvas3D.freeContextResources(this, true, canvas3D.ctx);
/* 1648 */             Canvas3D.destroyContext(canvas3D.screen.display, canvas3D.drawable, canvas3D.ctx);
/*      */ 
/*      */             
/* 1651 */             this.currentCtx = null;
/* 1652 */             this.currentDrawable = null;
/* 1653 */             canvas3D.drawingSurfaceObject.unLock();
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/* 1658 */         canvas3D.ctx = null;
/* 1659 */         canvas3D.ctxTimeStamp = 0L;
/*      */       } 
/*      */       
/* 1662 */       if (this.sharedCtx != null) {
/* 1663 */         this.sharedCtx = null;
/* 1664 */         this.sharedCtxTimeStamp = 0L;
/*      */       } 
/* 1666 */       this.listOfCanvases.clear();
/* 1667 */       this.listOfCtxs.clear();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void freeResourcesInFreeList(Canvas3D paramCanvas3D) {
/* 1674 */     boolean bool1 = (this.textureIdResourceFreeList.size() > 0) ? 1 : 0;
/* 1675 */     boolean bool2 = (this.displayListResourceFreeList.size() > 0) ? 1 : 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1680 */     if (bool1 || bool2) {
/* 1681 */       if (paramCanvas3D != null) {
/* 1682 */         paramCanvas3D.makeCtxCurrent(this.sharedCtx);
/*      */       }
/*      */       
/* 1685 */       if (bool2) {
/* 1686 */         for (Iterator iterator = this.displayListResourceFreeList.iterator(); iterator.hasNext(); ) {
/* 1687 */           int i = ((Integer)iterator.next()).intValue();
/* 1688 */           if (i <= 0) {
/*      */             continue;
/*      */           }
/* 1691 */           Canvas3D.freeDisplayList(this.sharedCtx, i);
/*      */         } 
/* 1693 */         this.displayListResourceFreeList.clear();
/*      */       } 
/* 1695 */       if (bool1) {
/* 1696 */         for (Iterator iterator = this.textureIdResourceFreeList.iterator(); iterator.hasNext(); ) {
/* 1697 */           int i = ((Integer)iterator.next()).intValue();
/* 1698 */           if (i <= 0) {
/*      */             continue;
/*      */           }
/* 1701 */           if (i >= this.textureIDResourceTable.size()) {
/* 1702 */             MasterControl.getCoreLogger().severe("Error in freeResourcesInFreeList : ResourceIDTableSize = " + this.textureIDResourceTable.size() + " val = " + i);
/*      */           
/*      */           }
/*      */           else {
/*      */             
/* 1707 */             Object object = this.textureIDResourceTable.get(i);
/* 1708 */             if (object instanceof TextureRetained) {
/* 1709 */               TextureRetained textureRetained = (TextureRetained)object;
/* 1710 */               synchronized (textureRetained.resourceLock) {
/* 1711 */                 textureRetained.resourceCreationMask &= (this.rendererBit ^ 0xFFFFFFFF);
/* 1712 */                 if (textureRetained.resourceCreationMask == 0) {
/* 1713 */                   textureRetained.freeTextureId(i);
/*      */                 }
/*      */               } 
/*      */             } 
/*      */             
/* 1718 */             this.textureIDResourceTable.set(i, null);
/*      */           } 
/* 1720 */           Canvas3D.freeTexture(this.sharedCtx, i);
/*      */         } 
/* 1722 */         this.textureIdResourceFreeList.clear();
/*      */       } 
/* 1724 */       if (paramCanvas3D != null) {
/* 1725 */         paramCanvas3D.makeCtxCurrent(paramCanvas3D.ctx);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   final void addTextureResource(int paramInt, Object paramObject) {
/* 1731 */     if (this.textureIDResourceTable.size() <= paramInt) {
/* 1732 */       int i = this.textureIDResourceTable.size();
/* 1733 */       for (; i < paramInt; i++) {
/* 1734 */         this.textureIDResourceTable.add(null);
/*      */       }
/* 1736 */       this.textureIDResourceTable.add(paramObject);
/*      */     } else {
/* 1738 */       this.textureIDResourceTable.set(paramInt, paramObject);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void freeContextResources() {
/* 1746 */     for (int i = this.textureIDResourceTable.size() - 1; i >= 0; i--) {
/* 1747 */       Object object = this.textureIDResourceTable.get(i);
/* 1748 */       if (object != null) {
/*      */ 
/*      */         
/* 1751 */         Canvas3D.freeTexture(this.sharedCtx, i);
/* 1752 */         if (object instanceof TextureRetained) {
/* 1753 */           TextureRetained textureRetained = (TextureRetained)object;
/* 1754 */           synchronized (textureRetained.resourceLock) {
/* 1755 */             textureRetained.resourceCreationMask &= (this.rendererBit ^ 0xFFFFFFFF);
/* 1756 */             if (textureRetained.resourceCreationMask == 0)
/* 1757 */               textureRetained.freeTextureId(i); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1762 */     this.textureIDResourceTable.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void notifyErrorListeners(RenderingError paramRenderingError) {
/* 1772 */     J3dNotification j3dNotification = new J3dNotification();
/* 1773 */     j3dNotification.type = 1;
/* 1774 */     j3dNotification.universe = null;
/* 1775 */     j3dNotification.args[0] = paramRenderingError;
/* 1776 */     VirtualUniverse.mc.sendNotification(j3dNotification);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1781 */   private static RenderingErrorListener defaultErrorListener = null;
/*      */   
/*      */   static RenderingErrorListener getDefaultErrorListener() {
/* 1784 */     if (defaultErrorListener == null) {
/* 1785 */       defaultErrorListener = new DefaultErrorListener();
/*      */     }
/*      */     
/* 1788 */     return defaultErrorListener;
/*      */   }
/*      */   
/*      */   static class DefaultErrorListener implements RenderingErrorListener {
/*      */     public void errorOccurred(RenderingError param1RenderingError) {
/* 1793 */       System.err.println();
/* 1794 */       System.err.println("DefaultRenderingErrorListener.errorOccurred:");
/* 1795 */       param1RenderingError.printVerbose();
/* 1796 */       System.exit(1);
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\Renderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */