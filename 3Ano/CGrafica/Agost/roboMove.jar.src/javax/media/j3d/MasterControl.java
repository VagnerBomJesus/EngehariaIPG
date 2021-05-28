/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.GraphicsDevice;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class MasterControl
/*      */ {
/*      */   static final int CHECK_FOR_WORK = 0;
/*      */   static final int SET_WORK = 1;
/*      */   static final int RUN_THREADS = 2;
/*      */   static final int THREAD_DONE = 3;
/*      */   static final int SET_WORK_FOR_REQUEST_RENDERER = 5;
/*      */   static final int RUN_RENDERER_CLEANUP = 6;
/*      */   static final int SLEEPING = 0;
/*      */   static final int RUNNING = 1;
/*      */   static final int WAITING_FOR_THREADS = 3;
/*      */   static final int WAITING_FOR_CPU = 4;
/*      */   static final int WAITING_FOR_RENDERER_CLEANUP = 5;
/*   46 */   static final Integer REQUESTRENDER = new Integer(2);
/*   47 */   static final Integer RENDER = new Integer(0);
/*   48 */   static final Integer SWAP = new Integer(1);
/*      */ 
/*      */   
/*   51 */   static final Integer ACTIVATE_VIEW = new Integer(1);
/*   52 */   static final Integer DEACTIVATE_VIEW = new Integer(2);
/*   53 */   static final Integer START_VIEW = new Integer(3);
/*   54 */   static final Integer STOP_VIEW = new Integer(4);
/*   55 */   static final Integer REEVALUATE_CANVAS = new Integer(5);
/*   56 */   static final Integer UNREGISTER_VIEW = new Integer(6);
/*   57 */   static final Integer PHYSICAL_ENV_CHANGE = new Integer(7);
/*   58 */   static final Integer INPUTDEVICE_CHANGE = new Integer(8);
/*   59 */   static final Integer EMPTY_UNIVERSE = new Integer(9);
/*   60 */   static final Integer START_RENDERER = new Integer(10);
/*   61 */   static final Integer STOP_RENDERER = new Integer(11);
/*   62 */   static final Integer RENDER_ONCE = new Integer(12);
/*   63 */   static final Integer FREE_CONTEXT = new Integer(13);
/*   64 */   static final Integer FREE_DRAWING_SURFACE = new Integer(14);
/*   65 */   static final Integer FREE_MESSAGE = new Integer(15);
/*   66 */   static final Integer RESET_CANVAS = new Integer(16);
/*   67 */   static final Integer GETBESTCONFIG = new Integer(17);
/*   68 */   static final Integer ISCONFIGSUPPORT = new Integer(18);
/*   69 */   static final Integer SET_GRAPHICSCONFIG_FEATURES = new Integer(19);
/*   70 */   static final Integer SET_QUERYPROPERTIES = new Integer(20);
/*   71 */   static final Integer SET_VIEW = new Integer(21);
/*      */ 
/*      */   
/*      */   private static boolean devLoggerEnabled = false;
/*      */ 
/*      */   
/*      */   private static Logger devLogger;
/*      */ 
/*      */   
/*      */   private static boolean statsLoggerEnabled = false;
/*      */ 
/*      */   
/*      */   private static Logger statsLogger;
/*      */ 
/*      */   
/*      */   private static boolean coreLoggerEnabled = false;
/*      */ 
/*      */   
/*      */   private static Logger coreLogger;
/*      */ 
/*      */   
/*      */   private static boolean librariesLoaded = false;
/*      */   
/*      */   private static boolean appletLauncher = false;
/*      */   
/*   96 */   private MasterControlThread mcThread = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  101 */   private UnorderList views = new UnorderList(1, View.class);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean transparentOffScreen = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean usePbuffer = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean viewFrustumCulling = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean lockGeometry = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  137 */   private int numActiveViews = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  142 */   private UnorderList activeUniverseList = new UnorderList(VirtualUniverse.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  147 */   private UnorderList regUniverseList = new UnorderList(VirtualUniverse.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  152 */   private Object timeLock = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  158 */   private long time = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  163 */   private long waitTimestamp = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  168 */   private UnorderList stateWorkThreads = new UnorderList(J3dThreadData.class);
/*      */   
/*  170 */   private UnorderList renderWorkThreads = new UnorderList(J3dThreadData.class);
/*      */   
/*  172 */   private UnorderList requestRenderWorkThreads = new UnorderList(J3dThreadData.class);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  178 */   private UnorderList renderThreadData = new UnorderList(J3dThreadData.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  183 */   private UnorderList inputDeviceThreads = new UnorderList(1, InputDeviceScheduler.class);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean threadListsChanged;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  196 */   private int lastTransformStructureThread = 0;
/*  197 */   private int lastStructureUpdateThread = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private long currentTime;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   TimerThread timerThread;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private NotificationThread notificationThread;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean workToDo = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean requestRenderWorkToDo = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  228 */   private int threadPending = 0;
/*  229 */   private int renderPending = 0;
/*  230 */   private int statePending = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean renderWaiting = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean stateWaiting = false;
/*      */ 
/*      */   
/*  241 */   private int state = 0;
/*      */ 
/*      */   
/*  244 */   private long sleepTime = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int cpuLimit;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  255 */   private UnorderList mirrorObjects = new UnorderList(ObjectUpdate.class);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  261 */   private RenderingAttributesStructure renderingAttributesStructure = new RenderingAttributesStructure();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  267 */   private DefaultRenderMethod defaultRenderMethod = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  272 */   private Text3DRenderMethod text3DRenderMethod = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  277 */   private VertexArrayRenderMethod vertexArrayRenderMethod = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  282 */   private DisplayListRenderMethod displayListRenderMethod = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  287 */   private CompressedGeometryRenderMethod compressedGeometryRenderMethod = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  292 */   private OrientedShape3DRenderMethod orientedShape3DRenderMethod = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  299 */   static long systemStartTime = 0L;
/*      */ 
/*      */   
/*      */   private static boolean isWindowsOs = false;
/*      */ 
/*      */   
/*      */   private static boolean isMacOs = false;
/*      */ 
/*      */   
/*  308 */   private int textureIdCount = 0;
/*      */ 
/*      */   
/*  311 */   private Object textureIdLock = new Object();
/*      */ 
/*      */   
/*  314 */   private long contextTimeStamp = 0L;
/*      */ 
/*      */   
/*  317 */   private boolean[] canvasIds = null;
/*  318 */   private int canvasFreeIndex = 0;
/*  319 */   private Object canvasIdLock = new Object();
/*      */ 
/*      */   
/*  322 */   private int rendererCount = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isSharedCtx = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean useCombiners = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean disableCompile = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean doCompaction = true;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean disableSeparateSpecularColor = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isDisplayList = true;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean buildDisplayListIfPossible = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean vertexAttrsInDisplayList = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean allowSoleUser = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean allowNullGraphicsConfig = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean stencilClear = false;
/*      */ 
/*      */   
/*  369 */   static int globalShadingLanguage = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean cgLibraryAvailable = false;
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean glslLibraryAvailable = false;
/*      */ 
/*      */   
/*  380 */   static Integer REMOVEALLCTXS_CLEANUP = new Integer(1);
/*  381 */   static Integer REMOVECTX_CLEANUP = new Integer(2);
/*  382 */   static Integer REMOVENOTIFY_CLEANUP = new Integer(3);
/*  383 */   static Integer RESETCANVAS_CLEANUP = new Integer(4);
/*  384 */   static Integer FREECONTEXT_CLEANUP = new Integer(5);
/*      */ 
/*      */   
/*  387 */   Object[] rendererCleanupArgs = { new Integer(3), null, null };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  394 */   Object contextCreationLock = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean doDsiRenderLock = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean enforcePowerOfTwo = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean sharedStereoZBuffer = true;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean implicitAntialiasing = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isCompiledVertexArray = true;
/*      */ 
/*      */ 
/*      */   
/*  421 */   int glslVertexAttrOffset = 6;
/*      */ 
/*      */ 
/*      */   
/*  425 */   Hashtable deviceScreenMap = new Hashtable();
/*      */ 
/*      */   
/*  428 */   UnorderList requestObjList = new UnorderList();
/*  429 */   private UnorderList requestTypeList = new UnorderList(Integer.class);
/*      */ 
/*      */   
/*  432 */   private UnorderList tempViewList = new UnorderList();
/*  433 */   private UnorderList renderOnceList = new UnorderList();
/*      */ 
/*      */   
/*      */   private boolean pendingRequest = false;
/*      */ 
/*      */   
/*      */   private static ThreadGroup rootThreadGroup;
/*      */ 
/*      */   
/*      */   private static int threadPriority;
/*      */ 
/*      */   
/*  445 */   private static Object mcThreadLock = new Object();
/*      */   
/*  447 */   private ArrayList timestampUpdateList = new ArrayList(3);
/*      */   
/*  449 */   private UnorderList freeMessageList = new UnorderList(8);
/*      */ 
/*      */   
/*      */   long awt;
/*      */ 
/*      */   
/*      */   int maxLights;
/*      */ 
/*      */   
/*  458 */   int resendTexTimestamp = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean xineramaDisabled = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean sortShape3DBounds = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean forceReleaseView = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean cacheAutoComputedBounds = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static String[] mtype;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   MasterControl() {
/*  490 */     assert librariesLoaded;
/*      */ 
/*      */     
/*  493 */     this.awt = Pipeline.getPipeline().getAWT();
/*      */ 
/*      */ 
/*      */     
/*  497 */     if (systemStartTime == 0L) {
/*  498 */       systemStartTime = J3dClock.currentTimeMillis();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  508 */     if (!isD3D()) {
/*  509 */       this.isSharedCtx = getBooleanProperty("j3d.sharedctx", this.isSharedCtx, "shared contexts");
/*      */     }
/*      */ 
/*      */     
/*  513 */     this.doCompaction = getBooleanProperty("j3d.docompaction", this.doCompaction, "compaction");
/*      */ 
/*      */ 
/*      */     
/*  517 */     this.transparentOffScreen = getBooleanProperty("j3d.transparentOffScreen", this.transparentOffScreen, "transparent OffScreen");
/*      */     
/*  519 */     this.usePbuffer = getBooleanProperty("j3d.usePbuffer", this.usePbuffer, "Off-screen Pbuffer");
/*      */ 
/*      */ 
/*      */     
/*  523 */     this.viewFrustumCulling = getBooleanProperty("j3d.viewFrustumCulling", this.viewFrustumCulling, "View frustum culling in the renderer is");
/*      */     
/*  525 */     this.sortShape3DBounds = getBooleanProperty("j3d.sortShape3DBounds", this.sortShape3DBounds, "Shape3D bounds enabled for transparency sorting", "Shape3D bounds *ignored* for transparency sorting");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  530 */     this.forceReleaseView = getBooleanProperty("j3d.forceReleaseView", this.forceReleaseView, "forceReleaseView  after Canvas3D dispose enabled", "forceReleaseView  after Canvas3D dispose disabled");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  535 */     this.useCombiners = getBooleanProperty("j3d.usecombiners", this.useCombiners, "Using NV_register_combiners if available", "NV_register_combiners disabled");
/*      */ 
/*      */ 
/*      */     
/*  539 */     if (getProperty("j3d.disablecompile") != null) {
/*  540 */       this.disableCompile = true;
/*  541 */       System.err.println("Java 3D: BranchGroup.compile disabled");
/*      */     } 
/*      */     
/*  544 */     if (getProperty("j3d.disableSeparateSpecular") != null) {
/*  545 */       this.disableSeparateSpecularColor = true;
/*  546 */       System.err.println("Java 3D: separate specular color disabled if possible");
/*      */     } 
/*      */     
/*  549 */     this.isDisplayList = getBooleanProperty("j3d.displaylist", this.isDisplayList, "display list");
/*      */ 
/*      */     
/*  552 */     this.implicitAntialiasing = getBooleanProperty("j3d.implicitAntialiasing", this.implicitAntialiasing, "implicit antialiasing");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  557 */     this.isCompiledVertexArray = getBooleanProperty("j3d.compiledVertexArray", this.isCompiledVertexArray, "compiled vertex array");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  562 */     boolean bool1 = getBooleanProperty("j3d.optimizeForSpace", true, "optimize for space");
/*      */ 
/*      */ 
/*      */     
/*  566 */     if (this.isDisplayList) {
/*      */ 
/*      */       
/*  569 */       if (!bool1) {
/*  570 */         this.buildDisplayListIfPossible = true;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  575 */       if (glslLibraryAvailable) {
/*  576 */         this.vertexAttrsInDisplayList = true;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  581 */     this.doDsiRenderLock = getBooleanProperty("j3d.renderLock", this.doDsiRenderLock, "render lock");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  586 */     this.enforcePowerOfTwo = getBooleanProperty("j3d.textureEnforcePowerOfTwo", this.enforcePowerOfTwo, "checking power-of-two textures");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  591 */     this.allowSoleUser = getBooleanProperty("j3d.allowSoleUser", this.allowSoleUser, "sole-user mode");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  596 */     this.allowNullGraphicsConfig = getBooleanProperty("j3d.allowNullGraphicsConfig", this.allowNullGraphicsConfig, "null graphics configs");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  601 */     this.stencilClear = getBooleanProperty("j3d.stencilClear", this.stencilClear, "per-frame stencil clear");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  606 */     this.sharedStereoZBuffer = getBooleanProperty("j3d.sharedstereozbuffer", this.sharedStereoZBuffer, "shared stereo Z buffer");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  612 */     final int defaultThreadLimit = getNumberOfProcessors() + 1;
/*  613 */     Integer integer1 = (Integer)AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*  617 */             return Integer.getInteger("j3d.threadLimit", defaultThreadLimit);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  622 */     this.cpuLimit = integer1.intValue();
/*  623 */     if (this.cpuLimit < 1)
/*  624 */       this.cpuLimit = 1; 
/*  625 */     if (J3dDebug.debug || this.cpuLimit != i) {
/*  626 */       System.err.println("Java 3D: concurrent threadLimit = " + this.cpuLimit);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  631 */     Integer integer2 = (Integer)AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*  635 */             return Integer.getInteger("j3d.deviceSampleTime", 0);
/*      */           }
/*      */         });
/*      */     
/*  639 */     if (integer2.intValue() > 0) {
/*  640 */       InputDeviceScheduler.samplingTime = integer2.intValue();
/*      */       
/*  642 */       System.err.println("Java 3D: Input device sampling time = " + integer2 + " ms");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  647 */     final int defaultGLSLVertexAttrOffset = this.glslVertexAttrOffset;
/*  648 */     Integer integer3 = (Integer)AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*  652 */             return Integer.getInteger("j3d.glslVertexAttrOffset", defaultGLSLVertexAttrOffset);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*  657 */     this.glslVertexAttrOffset = integer3.intValue();
/*  658 */     if (this.glslVertexAttrOffset < 1) {
/*  659 */       this.glslVertexAttrOffset = 1;
/*      */     }
/*  661 */     if (J3dDebug.debug || this.glslVertexAttrOffset != j) {
/*  662 */       System.err.println("Java 3D: glslVertexAttrOffset = " + this.glslVertexAttrOffset);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  667 */     boolean bool2 = false;
/*  668 */     if (getProperty("j3d.disableXinerama") != null) {
/*  669 */       bool2 = true;
/*      */     }
/*      */ 
/*      */     
/*  673 */     this.cacheAutoComputedBounds = getBooleanProperty("j3d.cacheAutoComputeBounds", this.cacheAutoComputedBounds, "Cache AutoCompute Bounds, accelerates getBounds()");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  679 */     if (!Pipeline.getPipeline().initializeJ3D(bool2)) {
/*  680 */       throw new RuntimeException(J3dI18N.getString("MasterControl0"));
/*      */     }
/*      */     
/*  683 */     if (this.xineramaDisabled) {
/*      */       
/*  685 */       System.err.println("Java 3D: Xinerama disabled");
/*      */     }
/*  687 */     else if (bool2) {
/*      */       
/*  689 */       System.err.println("Java 3D: could not disable Xinerama");
/*      */     } 
/*      */ 
/*      */     
/*  693 */     String[] arrayOfString = { "j3d.backgroundtexture", "j3d.forceNormalized", "j3d.g2ddrawpixel", "j3d.simulatedMultiTexture", "j3d.useFreeLists" };
/*      */ 
/*      */ 
/*      */     
/*      */     byte b;
/*      */ 
/*      */     
/*  700 */     for (b = 0; b < arrayOfString.length; b++) {
/*  701 */       if (getProperty(arrayOfString[b]) != null) {
/*  702 */         System.err.println("Java 3D: " + arrayOfString[b] + " property ignored");
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  707 */     this.maxLights = Pipeline.getPipeline().getMaximumLights();
/*      */ 
/*      */     
/*  710 */     FreeListManager.createFreeLists();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  716 */     this.canvasIds = new boolean[32];
/*  717 */     for (b = 0; b < this.canvasIds.length; b++) {
/*  718 */       this.canvasIds[b] = false;
/*      */     }
/*  720 */     this.canvasFreeIndex = 0;
/*      */   }
/*      */   
/*      */   private static boolean initLogger(Logger paramLogger, Level paramLevel) {
/*  724 */     if (paramLogger == null) {
/*  725 */       return false;
/*      */     }
/*      */     
/*  728 */     if (paramLevel != null && paramLogger.getLevel() == null && Logger.getLogger("j3d").getLevel() == null) {
/*      */       
/*      */       try {
/*      */ 
/*      */ 
/*      */         
/*  734 */         paramLogger.setLevel(paramLevel);
/*  735 */       } catch (SecurityException securityException) {
/*  736 */         System.err.println(securityException);
/*  737 */         return false;
/*      */       } 
/*      */     }
/*      */     
/*  741 */     return paramLogger.isLoggable(Level.SEVERE);
/*      */   }
/*      */ 
/*      */   
/*      */   private static void initLoggers() {
/*  746 */     coreLogger = Logger.getLogger("j3d.core");
/*  747 */     devLogger = Logger.getLogger("j3d.developer");
/*  748 */     statsLogger = Logger.getLogger("j3d.stats");
/*      */     
/*  750 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run() {
/*  753 */             coreLoggerEnabled = MasterControl.initLogger(coreLogger, null);
/*  754 */             devLoggerEnabled = MasterControl.initLogger(devLogger, Level.OFF);
/*  755 */             statsLoggerEnabled = MasterControl.initLogger(statsLogger, Level.OFF);
/*  756 */             return null;
/*      */           }
/*      */         });
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
/*  770 */   static Logger getDevLogger() { return devLogger; }
/*      */ 
/*      */ 
/*      */   
/*  774 */   static boolean isDevLoggable(Level paramLevel) { return (devLoggerEnabled && devLogger.isLoggable(paramLevel)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  787 */   static Logger getStatsLogger() { return statsLogger; }
/*      */ 
/*      */ 
/*      */   
/*  791 */   static boolean isStatsLoggable(Level paramLevel) { return (statsLoggerEnabled && statsLogger.isLoggable(paramLevel)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  805 */   static Logger getCoreLogger() { return coreLogger; }
/*      */ 
/*      */ 
/*      */   
/*  809 */   static boolean isCoreLoggable(Level paramLevel) { return (coreLoggerEnabled && coreLogger.isLoggable(paramLevel)); }
/*      */ 
/*      */   
/*      */   private static String getProperty(final String prop) {
/*  813 */     return (String)AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run() {
/*  816 */             return System.getProperty(prop);
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean getBooleanProperty(String paramString1, boolean paramBoolean, String paramString2, String paramString3) {
/*  825 */     boolean bool = paramBoolean;
/*  826 */     String str = getProperty(paramString1);
/*      */     
/*  828 */     if (str != null) {
/*  829 */       bool = Boolean.valueOf(str).booleanValue();
/*  830 */       System.err.println("Java 3D: " + (bool ? paramString2 : paramString3));
/*      */     } 
/*  832 */     return bool;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  838 */   private static boolean getBooleanProperty(String paramString1, boolean paramBoolean, String paramString2) { return getBooleanProperty(paramString1, paramBoolean, paramString2 + " enabled", paramString2 + " disabled"); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void loadLibraries() {
/*  851 */     assert !librariesLoaded;
/*      */ 
/*      */     
/*  854 */     String str1 = getProperty("os.name").toLowerCase();
/*  855 */     String str2 = getProperty("sun.arch.data.model");
/*      */ 
/*      */     
/*  858 */     isMacOs = (str1 != null && str1.startsWith("mac"));
/*  859 */     isWindowsOs = (str1 != null && str1.startsWith("windows"));
/*  860 */     boolean bool1 = (isWindowsOs && str1.indexOf("vista") != -1);
/*  861 */     boolean bool2 = (str2 != null && str2.equals("64"));
/*      */ 
/*      */     
/*  864 */     String str3 = getProperty("sun.jnlp.applet.launcher");
/*  865 */     appletLauncher = Boolean.valueOf(str3).booleanValue();
/*      */     
/*  867 */     if (isCoreLoggable(Level.CONFIG)) {
/*  868 */       StringBuffer stringBuffer = new StringBuffer();
/*  869 */       stringBuffer.append("MasterControl.loadLibraries()\n").append("    osName [lower-case] = \"").append(str1).append("\"").append(", sunArchDataModel = ").append(str2).append("\n").append("    is64Bit = ").append(bool2).append(", isWindowsOs = ").append(isWindowsOs).append(", isMacOs = ").append(isMacOs).append(", isWindowsVista = ").append(bool1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  884 */       getCoreLogger().config(stringBuffer.toString());
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  895 */     Pipeline.Type type = isMacOs ? Pipeline.Type.JOGL : Pipeline.Type.NATIVE_OGL;
/*      */ 
/*      */     
/*  898 */     String str4 = getProperty("j3d.rend");
/*  899 */     boolean bool3 = false;
/*  900 */     if (str4 != null)
/*      */     {
/*  902 */       if (str4.equals("ogl") && !isMacOs) {
/*  903 */         type = Pipeline.Type.NATIVE_OGL;
/*  904 */         bool3 = true;
/*  905 */       } else if (str4.equals("d3d") && isWindowsOs) {
/*  906 */         type = Pipeline.Type.NATIVE_D3D;
/*  907 */       } else if (str4.equals("jogl")) {
/*  908 */         type = Pipeline.Type.JOGL;
/*  909 */       } else if (str4.equals("noop")) {
/*  910 */         type = Pipeline.Type.NOOP;
/*      */       } else {
/*  912 */         System.err.println("Java 3D: Unrecognized renderer: " + str4);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  919 */     if (isWindowsOs && !bool2 && type == Pipeline.Type.NATIVE_OGL && 
/*  920 */       !Pipeline.useNativeOgl(bool1, bool3)) {
/*  921 */       type = Pipeline.Type.NATIVE_D3D;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  926 */     Pipeline.createPipeline(type);
/*      */ 
/*      */     
/*  929 */     String str5 = getProperty("j3d.shadingLanguage");
/*  930 */     if (str5 != null) {
/*  931 */       boolean bool = false;
/*  932 */       if (str5.equals("GLSL")) {
/*  933 */         globalShadingLanguage = 1;
/*  934 */         bool = true;
/*  935 */       } else if (str5.equals("Cg")) {
/*  936 */         globalShadingLanguage = 2;
/*  937 */         bool = true;
/*      */       } 
/*      */       
/*  940 */       if (bool) {
/*  941 */         System.err.println("Java 3D: Setting global shading language to " + str5);
/*      */       } else {
/*  943 */         System.err.println("Java 3D: Unrecognized shading language: " + str5);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  948 */     Pipeline.getPipeline().loadLibraries(globalShadingLanguage);
/*      */ 
/*      */     
/*  951 */     if (globalShadingLanguage == 2) {
/*  952 */       cgLibraryAvailable = Pipeline.getPipeline().isCgLibraryAvailable();
/*      */     }
/*      */ 
/*      */     
/*  956 */     if (globalShadingLanguage == 1) {
/*  957 */       glslLibraryAvailable = Pipeline.getPipeline().isGLSLLibraryAvailable();
/*      */     }
/*      */ 
/*      */     
/*  961 */     assert !glslLibraryAvailable || !cgLibraryAvailable : "ERROR: cannot support both GLSL and CG at the same time";
/*      */     
/*  963 */     librariesLoaded = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   InputDeviceBlockingThread getInputDeviceBlockingThread(final InputDevice device) {
/*  974 */     return (InputDeviceBlockingThread)AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/*  978 */             synchronized (rootThreadGroup) {
/*  979 */               InputDeviceBlockingThread inputDeviceBlockingThread = new InputDeviceBlockingThread(rootThreadGroup, device);
/*      */               
/*  981 */               inputDeviceBlockingThread.setPriority(threadPriority);
/*  982 */               return inputDeviceBlockingThread;
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setThreadPriority(final int pri) {
/*  993 */     synchronized (rootThreadGroup) {
/*  994 */       threadPriority = paramInt;
/*  995 */       AccessController.doPrivileged(new PrivilegedAction()
/*      */           {
/*      */             public Object run() {
/*  998 */               Thread[] arrayOfThread = new Thread[rootThreadGroup.activeCount()];
/*      */               
/* 1000 */               int i = rootThreadGroup.enumerate(arrayOfThread);
/* 1001 */               for (int j = i - 1; j >= 0; j--) {
/* 1002 */                 arrayOfThread[j].setPriority(pri);
/*      */               }
/* 1004 */               return null;
/*      */             }
/*      */           });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1015 */   int getThreadPriority() { return threadPriority; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1022 */   int getRendererBit() { return 1 << this.rendererCount++; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1030 */   int getRendererId() { return this.rendererCount++; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1038 */   long getContextTimeStamp() { return ++this.contextTimeStamp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1046 */   Integer getDisplayListId() { return (Integer)FreeListManager.getObject(0); }
/*      */ 
/*      */ 
/*      */   
/* 1050 */   void freeDisplayListId(Integer paramInteger) { FreeListManager.freeObject(0, paramInteger); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getTexture2DId() {
/* 1059 */     MemoryFreeList memoryFreeList = FreeListManager.getFreeList(1);
/*      */ 
/*      */ 
/*      */     
/* 1063 */     synchronized (this.textureIdLock) {
/* 1064 */       int i; if (memoryFreeList.size() > 0) {
/* 1065 */         i = ((Integer)FreeListManager.getObject(1)).intValue();
/*      */       } else {
/*      */         
/* 1068 */         i = ++this.textureIdCount;
/*      */       } 
/* 1070 */       return i;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   int getTexture3DId() {
/* 1077 */     MemoryFreeList memoryFreeList = FreeListManager.getFreeList(2);
/*      */     
/* 1079 */     synchronized (this.textureIdLock) {
/* 1080 */       if (memoryFreeList.size > 0) {
/* 1081 */         return ((Integer)FreeListManager.getObject(2)).intValue();
/*      */       }
/*      */       
/* 1084 */       return ++this.textureIdCount;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/* 1089 */   void freeTexture2DId(int paramInt) { FreeListManager.freeObject(1, new Integer(paramInt)); }
/*      */ 
/*      */ 
/*      */   
/* 1093 */   void freeTexture3DId(int paramInt) { FreeListManager.freeObject(2, new Integer(paramInt)); }
/*      */ 
/*      */ 
/*      */   
/*      */   int getCanvasId() {
/*      */     int i;
/* 1099 */     synchronized (this.canvasIdLock) {
/*      */       
/* 1101 */       for (i = this.canvasFreeIndex; i < this.canvasIds.length && 
/* 1102 */         this.canvasIds[i]; i++);
/*      */ 
/*      */ 
/*      */       
/* 1106 */       if (i >= this.canvasIds.length) {
/* 1107 */         throw new RuntimeException("Cannot render to more than 32 Canvas3Ds");
/*      */       }
/*      */       
/* 1110 */       this.canvasIds[i] = true;
/* 1111 */       this.canvasFreeIndex = i + 1;
/*      */     } 
/*      */     
/* 1114 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void freeCanvasId(int paramInt) {
/* 1120 */     synchronized (this.canvasIdLock) {
/*      */       
/* 1122 */       this.canvasIds[paramInt] = false;
/* 1123 */       if (this.canvasFreeIndex > paramInt) {
/* 1124 */         this.canvasFreeIndex = paramInt;
/*      */       }
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
/*      */   private Renderer createRenderer(GraphicsConfiguration paramGraphicsConfiguration) {
/* 1137 */     final GraphicsDevice gd = paramGraphicsConfiguration.getDevice();
/*      */     
/* 1139 */     Renderer renderer = (Renderer)Screen3D.deviceRendererMap.get(graphicsDevice);
/* 1140 */     if (renderer != null) {
/* 1141 */       return renderer;
/*      */     }
/*      */ 
/*      */     
/* 1145 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/* 1149 */             synchronized (rootThreadGroup) {
/* 1150 */               Renderer renderer = new Renderer(rootThreadGroup);
/* 1151 */               renderer.initialize();
/* 1152 */               renderer.setPriority(threadPriority);
/* 1153 */               Screen3D.deviceRendererMap.put(gd, renderer);
/*      */             } 
/* 1155 */             return null;
/*      */           }
/*      */         });
/*      */     
/* 1159 */     this.threadListsChanged = true;
/*      */     
/* 1161 */     return (Renderer)Screen3D.deviceRendererMap.get(graphicsDevice);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void postRequest(Integer paramInteger, Object paramObject) {
/* 1169 */     synchronized (mcThreadLock) {
/* 1170 */       synchronized (this.requestObjList) {
/* 1171 */         if (this.mcThread == null) {
/* 1172 */           if (paramInteger == ACTIVATE_VIEW || paramInteger == GETBESTCONFIG || paramInteger == SET_VIEW || paramInteger == ISCONFIGSUPPORT || paramInteger == SET_QUERYPROPERTIES || paramInteger == SET_GRAPHICSCONFIG_FEATURES) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1178 */             createMasterControlThread();
/* 1179 */             this.requestObjList.add(paramObject);
/* 1180 */             this.requestTypeList.add(paramInteger);
/* 1181 */             this.pendingRequest = true;
/* 1182 */           } else if (paramInteger == EMPTY_UNIVERSE) {
/* 1183 */             destroyUniverseThreads((VirtualUniverse)paramObject);
/* 1184 */           } else if (paramInteger == STOP_VIEW) {
/* 1185 */             View view = (View)paramObject;
/* 1186 */             view.stopViewCount = -1;
/* 1187 */             view.isRunning = false;
/* 1188 */           } else if (paramInteger == STOP_RENDERER) {
/* 1189 */             if (paramObject instanceof Canvas3D) {
/* 1190 */               ((Canvas3D)paramObject).isRunningStatus = false;
/*      */             } else {
/* 1192 */               ((Renderer)paramObject).userStop = true;
/*      */             } 
/* 1194 */           } else if (paramInteger == UNREGISTER_VIEW) {
/* 1195 */             ((View)paramObject).doneUnregister = true;
/*      */           } else {
/* 1197 */             this.requestObjList.add(paramObject);
/* 1198 */             this.requestTypeList.add(paramInteger);
/* 1199 */             this.pendingRequest = true;
/*      */           } 
/*      */         } else {
/* 1202 */           this.requestObjList.add(paramObject);
/* 1203 */           this.requestTypeList.add(paramInteger);
/* 1204 */           this.pendingRequest = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1209 */     setWork();
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
/*      */   boolean mcThreadDone() {
/* 1222 */     synchronized (mcThreadLock) {
/* 1223 */       synchronized (this.requestObjList) {
/* 1224 */         if (!this.pendingRequest) {
/* 1225 */           this.mcThread = null;
/* 1226 */           if (this.renderingAttributesStructure.updateThread != null) {
/*      */             
/* 1228 */             this.renderingAttributesStructure.updateThread.finish();
/* 1229 */             this.renderingAttributesStructure.updateThread = null;
/*      */           } 
/*      */           
/* 1232 */           this.renderingAttributesStructure = new RenderingAttributesStructure();
/* 1233 */           if (this.timerThread != null) {
/* 1234 */             this.timerThread.finish();
/* 1235 */             this.timerThread = null;
/*      */           } 
/* 1237 */           if (this.notificationThread != null) {
/* 1238 */             this.notificationThread.finish();
/* 1239 */             this.notificationThread = null;
/*      */           } 
/* 1241 */           this.requestObjList.clear();
/* 1242 */           this.requestTypeList.clear();
/* 1243 */           return true;
/*      */         } 
/* 1245 */         this.running = true;
/* 1246 */         createMCThreads();
/* 1247 */         return false;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1257 */   final boolean isD3D() { return (Pipeline.getPipeline().getPipelineType() == Pipeline.Type.NATIVE_D3D); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1265 */   static final boolean isWindows() { return isWindowsOs; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1273 */   static boolean isAppletLauncher() { return appletLauncher; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1281 */   final long getTime() { return this.time++; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processMessage(J3dMessage paramJ3dMessage) {
/* 1291 */     synchronized (this.timeLock) {
/* 1292 */       paramJ3dMessage.time = getTime();
/* 1293 */       sendMessage(paramJ3dMessage);
/*      */     } 
/* 1295 */     setWork();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void processMessage(J3dMessage[] paramArrayOfJ3dMessage) {
/* 1305 */     synchronized (this.timeLock) {
/* 1306 */       long l = getTime();
/*      */       
/* 1308 */       for (byte b = 0; b < paramArrayOfJ3dMessage.length; b++) {
/* 1309 */         (paramArrayOfJ3dMessage[b]).time = l;
/* 1310 */         sendMessage(paramArrayOfJ3dMessage[b]);
/*      */       } 
/*      */     } 
/* 1313 */     setWork();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1321 */   void sendNotification(J3dNotification paramJ3dNotification) { this.notificationThread.addNotification(paramJ3dNotification); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void createMasterControlThread() {
/* 1329 */     if (this.mcThread != null) {
/*      */       return;
/*      */     }
/*      */     
/* 1333 */     this.running = true;
/* 1334 */     this.workToDo = true;
/* 1335 */     this.state = 1;
/* 1336 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run() {
/* 1339 */             synchronized (rootThreadGroup) {
/* 1340 */               MasterControl.this.mcThread = new MasterControlThread(rootThreadGroup);
/*      */               
/* 1342 */               MasterControl.this.mcThread.setPriority(threadPriority);
/*      */             } 
/* 1344 */             return null;
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendMessage(J3dMessage paramJ3dMessage) {
/* 1356 */     synchronized (paramJ3dMessage) {
/* 1357 */       VirtualUniverse virtualUniverse = paramJ3dMessage.universe;
/* 1358 */       int i = paramJ3dMessage.threads;
/*      */       
/* 1360 */       if (isCoreLoggable(Level.FINEST)) {
/* 1361 */         dumpMessage("sendMessage", paramJ3dMessage);
/*      */       }
/*      */       
/* 1364 */       if ((i & 0x400) != 0) {
/* 1365 */         this.renderingAttributesStructure.addMessage(paramJ3dMessage);
/*      */       }
/*      */ 
/*      */       
/* 1369 */       if (virtualUniverse != null) {
/* 1370 */         if ((i & 0x40) != 0) {
/* 1371 */           virtualUniverse.geometryStructure.addMessage(paramJ3dMessage);
/*      */         }
/* 1373 */         if ((i & 0x2000) != 0) {
/* 1374 */           virtualUniverse.transformStructure.addMessage(paramJ3dMessage);
/*      */         }
/* 1376 */         if ((i & 0x100) != 0) {
/* 1377 */           virtualUniverse.behaviorStructure.addMessage(paramJ3dMessage);
/*      */         }
/* 1379 */         if ((i & 0x200) != 0) {
/* 1380 */           virtualUniverse.soundStructure.addMessage(paramJ3dMessage);
/*      */         }
/* 1382 */         if ((i & 0x1000) != 0) {
/* 1383 */           virtualUniverse.renderingEnvironmentStructure.addMessage(paramJ3dMessage);
/*      */         }
/*      */       } 
/*      */       
/* 1387 */       if ((i & 0x2) != 0)
/*      */       {
/* 1389 */         if (paramJ3dMessage.view != null && paramJ3dMessage.view.soundScheduler != null) {
/*      */ 
/*      */           
/* 1392 */           paramJ3dMessage.view.soundScheduler.addMessage(paramJ3dMessage);
/*      */         } else {
/* 1394 */           synchronized (this.views) {
/* 1395 */             View[] arrayOfView = (View[])this.views.toArray(false);
/* 1396 */             int j = this.views.arraySize() - 1;
/* 1397 */             if (virtualUniverse == null) {
/* 1398 */               while (j >= 0) {
/* 1399 */                 (arrayOfView[j--]).soundScheduler.addMessage(paramJ3dMessage);
/*      */               }
/*      */             } else {
/* 1402 */               while (j >= 0) {
/* 1403 */                 if ((arrayOfView[j]).universe == virtualUniverse) {
/* 1404 */                   (arrayOfView[j]).soundScheduler.addMessage(paramJ3dMessage);
/*      */                 }
/* 1406 */                 j--;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/* 1413 */       if ((i & 0x80) != 0)
/*      */       {
/* 1415 */         if (paramJ3dMessage.view != null && paramJ3dMessage.view.renderBin != null) {
/*      */ 
/*      */           
/* 1418 */           paramJ3dMessage.view.renderBin.addMessage(paramJ3dMessage);
/*      */         } else {
/* 1420 */           synchronized (this.views) {
/* 1421 */             View[] arrayOfView = (View[])this.views.toArray(false);
/* 1422 */             int j = this.views.arraySize() - 1;
/* 1423 */             if (virtualUniverse == null) {
/* 1424 */               while (j >= 0) {
/* 1425 */                 (arrayOfView[j--]).renderBin.addMessage(paramJ3dMessage);
/*      */               }
/*      */             } else {
/*      */               
/* 1429 */               while (j >= 0) {
/* 1430 */                 if ((arrayOfView[j]).universe == virtualUniverse) {
/* 1431 */                   (arrayOfView[j]).renderBin.addMessage(paramJ3dMessage);
/*      */                 }
/* 1433 */                 j--;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/* 1440 */       if (paramJ3dMessage.getRefcount() == 0) {
/* 1441 */         paramJ3dMessage.clear();
/*      */       }
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
/*      */   void sendRunMessage(int paramInt) {
/* 1454 */     synchronized (this.timeLock) {
/*      */       
/* 1456 */       long l = getTime();
/*      */       
/* 1458 */       if ((paramInt & 0x4) != 0) {
/* 1459 */         synchronized (this.inputDeviceThreads) {
/* 1460 */           InputDeviceScheduler[] arrayOfInputDeviceScheduler = (InputDeviceScheduler[])this.inputDeviceThreads.toArray(false);
/*      */           
/* 1462 */           for (int i = this.inputDeviceThreads.size() - 1; i >= 0; i--) {
/* 1463 */             if ((arrayOfInputDeviceScheduler[i]).physicalEnv.activeViewRef > 0) {
/* 1464 */               (arrayOfInputDeviceScheduler[i].getThreadData()).lastUpdateTime = l;
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1475 */           if (this.timerThread != null)
/*      */           {
/*      */             
/* 1478 */             this.timerThread.addInputDeviceSchedCond();
/*      */           }
/*      */         } 
/*      */       }
/* 1482 */       if ((paramInt & 0x10) != 0) {
/* 1483 */         synchronized (this.renderThreadData) {
/* 1484 */           J3dThreadData[] arrayOfJ3dThreadData = (J3dThreadData[])this.renderThreadData.toArray(false);
/*      */           
/* 1486 */           int i = this.renderThreadData.arraySize() - 1;
/*      */           
/* 1488 */           while (i >= 0) {
/* 1489 */             J3dThreadData j3dThreadData = arrayOfJ3dThreadData[i--];
/* 1490 */             if (j3dThreadData.view.renderBinReady) {
/* 1491 */               j3dThreadData.lastUpdateTime = l;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/* 1497 */     setWork();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendRunMessage(long paramLong, View paramView, int paramInt) {
/* 1506 */     synchronized (this.timeLock) {
/*      */       
/* 1508 */       long l = getTime();
/*      */       
/* 1510 */       if ((paramInt & 0x2) != 0) {
/* 1511 */         if (paramView.soundScheduler != null) {
/* 1512 */           paramView.soundScheduler.threadData.lastUpdateTime = l;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1521 */         this.timerThread.addSoundSchedCond(l + paramLong);
/*      */       } 
/*      */     } 
/* 1524 */     setWork();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendRunMessage(View paramView, int paramInt) {
/* 1533 */     synchronized (this.timeLock) {
/* 1534 */       long l = getTime();
/*      */       
/* 1536 */       if ((paramInt & 0x10) != 0) {
/* 1537 */         synchronized (this.renderThreadData) {
/* 1538 */           J3dThreadData[] arrayOfJ3dThreadData = (J3dThreadData[])this.renderThreadData.toArray(false);
/*      */           
/* 1540 */           int i = this.renderThreadData.arraySize() - 1;
/*      */           
/* 1542 */           while (i >= 0) {
/* 1543 */             J3dThreadData j3dThreadData = arrayOfJ3dThreadData[i--];
/* 1544 */             if (j3dThreadData.view == paramView && paramView.renderBinReady) {
/* 1545 */               j3dThreadData.lastUpdateTime = l;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/* 1551 */     setWork();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendRunMessage(VirtualUniverse paramVirtualUniverse, int paramInt) {
/* 1561 */     synchronized (this.timeLock) {
/* 1562 */       long l = getTime();
/*      */       
/* 1564 */       if ((paramInt & true) != 0 && 
/* 1565 */         paramVirtualUniverse.behaviorScheduler != null) {
/* 1566 */         (paramVirtualUniverse.behaviorScheduler.getThreadData(null, null)).lastUpdateTime = l;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1571 */       if ((paramInt & 0x100) != 0) {
/* 1572 */         paramVirtualUniverse.behaviorStructure.threadData.lastUpdateTime = l;
/*      */       }
/*      */       
/* 1575 */       if ((paramInt & 0x40) != 0) {
/* 1576 */         paramVirtualUniverse.geometryStructure.threadData.lastUpdateTime = l;
/*      */       }
/*      */       
/* 1579 */       if ((paramInt & 0x200) != 0) {
/* 1580 */         paramVirtualUniverse.soundStructure.threadData.lastUpdateTime = l;
/*      */       }
/*      */       
/* 1583 */       if ((paramInt & 0x2) != 0) {
/* 1584 */         synchronized (this.views) {
/* 1585 */           View[] arrayOfView = (View[])this.views.toArray(false);
/* 1586 */           for (int i = this.views.arraySize() - 1; i >= 0; i--) {
/* 1587 */             if ((arrayOfView[i]).soundScheduler != null && (arrayOfView[i]).universe == paramVirtualUniverse)
/*      */             {
/* 1589 */               (arrayOfView[i]).soundScheduler.threadData.lastUpdateTime = l;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/* 1595 */       if ((paramInt & 0x10) != 0)
/*      */       {
/* 1597 */         synchronized (this.renderThreadData) {
/* 1598 */           J3dThreadData[] arrayOfJ3dThreadData = (J3dThreadData[])this.renderThreadData.toArray(false);
/*      */           
/* 1600 */           int i = this.renderThreadData.arraySize() - 1;
/*      */           
/* 1602 */           while (i >= 0) {
/* 1603 */             J3dThreadData j3dThreadData = arrayOfJ3dThreadData[i--];
/* 1604 */             if (j3dThreadData.view.universe == paramVirtualUniverse && j3dThreadData.view.renderBinReady) {
/* 1605 */               j3dThreadData.lastUpdateTime = l;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 1612 */     setWork();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1622 */   UnorderList cloneView() { return (UnorderList)this.views.clone(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1629 */   boolean isRegistered(View paramView) { return this.views.contains(paramView); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateTimeValues() {
/* 1640 */     synchronized (this.timeLock) {
/* 1641 */       byte b = 0;
/* 1642 */       J3dThreadData j3dThreadData1 = null;
/* 1643 */       J3dThreadData j3dThreadData2 = null;
/* 1644 */       long l = this.currentTime;
/*      */       
/* 1646 */       this.currentTime = getTime();
/*      */       
/* 1648 */       J3dThreadData[] arrayOfJ3dThreadData = (J3dThreadData[])this.stateWorkThreads.toArray(false);
/*      */       
/* 1650 */       int i = this.stateWorkThreads.arraySize();
/*      */       
/* 1652 */       while (b < this.lastTransformStructureThread) {
/* 1653 */         j3dThreadData2 = arrayOfJ3dThreadData[b++];
/*      */         
/* 1655 */         if (j3dThreadData2.lastUpdateTime > j3dThreadData2.lastRunTime && !j3dThreadData2.thread.userStop) {
/*      */           
/* 1657 */           j3dThreadData1 = j3dThreadData2;
/* 1658 */           j3dThreadData2.needsRun = true;
/* 1659 */           j3dThreadData2.threadOpts = 2;
/* 1660 */           j3dThreadData2.lastRunTime = this.currentTime; continue;
/*      */         } 
/* 1662 */         j3dThreadData2.needsRun = false;
/*      */       } 
/*      */ 
/*      */       
/* 1666 */       if (j3dThreadData1 != null) {
/* 1667 */         j3dThreadData1.threadOpts = 1;
/* 1668 */         j3dThreadData1 = null;
/*      */       } 
/*      */       
/* 1671 */       while (b < this.lastStructureUpdateThread) {
/* 1672 */         j3dThreadData2 = arrayOfJ3dThreadData[b++];
/* 1673 */         if (j3dThreadData2.lastUpdateTime > j3dThreadData2.lastRunTime && !j3dThreadData2.thread.userStop) {
/*      */           
/* 1675 */           j3dThreadData1 = j3dThreadData2;
/* 1676 */           j3dThreadData2.needsRun = true;
/* 1677 */           j3dThreadData2.threadOpts = 2;
/* 1678 */           j3dThreadData2.lastRunTime = this.currentTime; continue;
/*      */         } 
/* 1680 */         j3dThreadData2.needsRun = false;
/*      */       } 
/*      */       
/* 1683 */       if (j3dThreadData1 != null) {
/* 1684 */         j3dThreadData1.threadOpts = 1;
/* 1685 */         j3dThreadData1 = null;
/*      */       } 
/*      */       
/* 1688 */       while (b < i) {
/* 1689 */         j3dThreadData2 = arrayOfJ3dThreadData[b++];
/* 1690 */         if (j3dThreadData2.lastUpdateTime > j3dThreadData2.lastRunTime && !j3dThreadData2.thread.userStop) {
/*      */           
/* 1692 */           j3dThreadData1 = j3dThreadData2;
/* 1693 */           j3dThreadData2.needsRun = true;
/* 1694 */           j3dThreadData2.threadOpts = 2;
/* 1695 */           j3dThreadData2.lastRunTime = this.currentTime; continue;
/*      */         } 
/* 1697 */         j3dThreadData2.needsRun = false;
/*      */       } 
/*      */       
/* 1700 */       if (j3dThreadData1 != null) {
/* 1701 */         j3dThreadData1.threadOpts = 1;
/* 1702 */         j3dThreadData1 = null;
/*      */       } 
/*      */ 
/*      */       
/* 1706 */       arrayOfJ3dThreadData = (J3dThreadData[])this.renderWorkThreads.toArray(false);
/* 1707 */       i = this.renderWorkThreads.arraySize();
/*      */       
/* 1709 */       J3dThreadData j3dThreadData3 = null;
/* 1710 */       this.waitTimestamp++;
/* 1711 */       this.sleepTime = 0L;
/*      */       
/* 1713 */       boolean bool = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1719 */       View view = null;
/* 1720 */       for (b = 0; b < i; b++) {
/* 1721 */         j3dThreadData2 = arrayOfJ3dThreadData[b];
/* 1722 */         if (j3dThreadData2.view != view) {
/* 1723 */           j3dThreadData2.view.computeCycleTime();
/*      */ 
/*      */           
/* 1726 */           if (j3dThreadData2.view.sleepTime > this.sleepTime) {
/* 1727 */             this.sleepTime = j3dThreadData2.view.sleepTime;
/*      */           }
/*      */         } 
/* 1730 */         view = j3dThreadData2.view;
/*      */       } 
/*      */       
/* 1733 */       view = null;
/* 1734 */       for (b = 0; b < i; b++) {
/* 1735 */         j3dThreadData2 = arrayOfJ3dThreadData[b];
/* 1736 */         if (j3dThreadData2.canvas == null) {
/* 1737 */           (Object[])j3dThreadData2.threadArgs[3] = null;
/*      */         }
/* 1739 */         if (j3dThreadData2.lastUpdateTime > j3dThreadData2.lastRunTime && !j3dThreadData2.thread.userStop) {
/*      */ 
/*      */           
/* 1742 */           if (j3dThreadData2.thread.lastWaitTimestamp == this.waitTimestamp) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1747 */             this.waitTimestamp++;
/* 1748 */             if (j3dThreadData2.view != view) {
/*      */               
/* 1750 */               view = j3dThreadData2.view;
/* 1751 */               bool = true;
/* 1752 */               j3dThreadData3.threadOpts = 17;
/*      */ 
/*      */               
/* 1755 */               (Object[])j3dThreadData3.threadArgs[3] = j3dThreadData3.view;
/* 1756 */               j3dThreadData2.threadOpts = 10;
/*      */             } else {
/*      */               
/* 1759 */               if ((j3dThreadData3.threadOpts & 0x8) != 0) {
/*      */                 
/* 1761 */                 j3dThreadData3.threadOpts = 9;
/*      */               
/*      */               }
/*      */               else {
/*      */                 
/* 1766 */                 j3dThreadData3.threadOpts = 1;
/*      */               } 
/*      */               
/* 1769 */               j3dThreadData2.threadOpts = 2;
/*      */             }
/*      */           
/*      */           }
/* 1773 */           else if (j3dThreadData2.view != view) {
/* 1774 */             view = j3dThreadData2.view;
/* 1775 */             bool = true;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1780 */             if (j3dThreadData3 != null) {
/* 1781 */               j3dThreadData3.threadOpts = 17;
/*      */ 
/*      */               
/* 1784 */               (Object[])j3dThreadData3.threadArgs[3] = j3dThreadData3.view;
/*      */             } 
/*      */             
/* 1787 */             j3dThreadData2.threadOpts = 10;
/*      */           } else {
/*      */             
/* 1790 */             j3dThreadData2.threadOpts = 2;
/*      */           } 
/*      */           
/* 1793 */           j3dThreadData2.thread.lastWaitTimestamp = this.waitTimestamp;
/* 1794 */           j3dThreadData2.needsRun = true;
/* 1795 */           j3dThreadData2.lastRunTime = this.currentTime;
/* 1796 */           j3dThreadData3 = j3dThreadData2;
/*      */         } else {
/* 1798 */           j3dThreadData2.needsRun = false;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1803 */       if (j3dThreadData3 != null) {
/* 1804 */         j3dThreadData3.threadOpts = 49;
/*      */ 
/*      */ 
/*      */         
/* 1808 */         this.lockGeometry = true;
/* 1809 */         (Object[])j3dThreadData3.threadArgs[3] = j3dThreadData3.view;
/*      */       } else {
/* 1811 */         this.lockGeometry = false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1818 */     if (this.sleepTime > 0L) {
/*      */       
/*      */       try {
/* 1821 */         Thread.sleep(this.sleepTime);
/* 1822 */       } catch (InterruptedException interruptedException) {
/* 1823 */         System.err.println(interruptedException);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void createUpdateThread(J3dStructure paramJ3dStructure) {
/* 1830 */     final J3dStructure s = paramJ3dStructure;
/*      */     
/* 1832 */     if (j3dStructure.updateThread == null) {
/* 1833 */       AccessController.doPrivileged(new PrivilegedAction()
/*      */           {
/*      */             public Object run() {
/* 1836 */               synchronized (rootThreadGroup) {
/* 1837 */                 this.val$s.updateThread = new StructureUpdateThread(rootThreadGroup, s, this.val$s.threadType);
/*      */                 
/* 1839 */                 this.val$s.updateThread.setPriority(threadPriority);
/*      */               } 
/* 1841 */               return null;
/*      */             }
/*      */           });
/* 1844 */       j3dStructure.updateThread.initialize();
/* 1845 */       j3dStructure.threadData.thread = j3dStructure.updateThread;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1851 */       j3dStructure.threadData.lastUpdateTime = Math.max(this.currentTime, j3dStructure.threadData.lastUpdateTime);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void emptyMessageList(J3dStructure paramJ3dStructure, View paramView) {
/* 1857 */     if (paramJ3dStructure != null) {
/* 1858 */       if (paramView == null) {
/* 1859 */         if (paramJ3dStructure.threadData != null) {
/* 1860 */           paramJ3dStructure.threadData.thread = null;
/*      */         }
/*      */         
/* 1863 */         if (paramJ3dStructure.updateThread != null) {
/* 1864 */           paramJ3dStructure.updateThread.structure = null;
/*      */         }
/* 1866 */         paramJ3dStructure.updateThread = null;
/*      */       } 
/* 1868 */       boolean bool = false;
/* 1869 */       if (paramView != null && paramView.universe != null)
/*      */       {
/*      */         
/* 1872 */         for (int i = this.views.size() - 1; i >= 0; i--) {
/* 1873 */           if (((View)this.views.get(i)).universe == paramView.universe) {
/* 1874 */             bool = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/* 1881 */       UnorderList unorderList = paramJ3dStructure.messageList;
/*      */       
/* 1883 */       synchronized (unorderList) {
/* 1884 */         int i = unorderList.size();
/* 1885 */         if (i > 0) {
/* 1886 */           J3dMessage[] arrayOfJ3dMessage = (J3dMessage[])unorderList.toArray(false);
/*      */           
/* 1888 */           byte b = 0;
/*      */           
/* 1890 */           Object object = null;
/* 1891 */           while (b < i) {
/* 1892 */             J3dMessage j3dMessage = arrayOfJ3dMessage[b];
/* 1893 */             if (paramView == null || j3dMessage.view == paramView || (j3dMessage.view == null && !bool)) {
/*      */               
/* 1895 */               if (j3dMessage.type == 0) {
/*      */                 break;
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1904 */               j3dMessage.decRefcount();
/* 1905 */               unorderList.removeOrdered(b);
/* 1906 */               i--; continue;
/*      */             } 
/* 1908 */             b++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void destroyUpdateThread(J3dStructure paramJ3dStructure) {
/* 1919 */     if (paramJ3dStructure.updateThread != null) {
/* 1920 */       paramJ3dStructure.updateThread.finish();
/* 1921 */       paramJ3dStructure.updateThread.structure = null;
/* 1922 */       paramJ3dStructure.updateThread = null;
/*      */     } 
/* 1924 */     paramJ3dStructure.threadData.thread = null;
/* 1925 */     paramJ3dStructure.clearMessages();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void registerView(View paramView) {
/* 1933 */     final VirtualUniverse univ = paramView.universe;
/*      */     
/* 1935 */     if (this.views.contains(paramView) && this.regUniverseList.contains(virtualUniverse)) {
/*      */       return;
/*      */     }
/*      */     
/* 1939 */     if (this.timerThread == null) {
/*      */ 
/*      */       
/* 1942 */       this.running = true;
/* 1943 */       createMCThreads();
/*      */     } 
/*      */     
/* 1946 */     paramView.assignViewId();
/*      */ 
/*      */     
/* 1949 */     createUpdateThread(virtualUniverse.behaviorStructure);
/* 1950 */     createUpdateThread(virtualUniverse.geometryStructure);
/* 1951 */     createUpdateThread(virtualUniverse.soundStructure);
/* 1952 */     createUpdateThread(virtualUniverse.renderingEnvironmentStructure);
/* 1953 */     createUpdateThread(virtualUniverse.transformStructure);
/*      */ 
/*      */     
/* 1956 */     J3dThreadData j3dThreadData = null;
/*      */     
/* 1958 */     if (virtualUniverse.behaviorScheduler == null) {
/* 1959 */       AccessController.doPrivileged(new PrivilegedAction()
/*      */           {
/*      */             public Object run() {
/* 1962 */               synchronized (rootThreadGroup) {
/* 1963 */                 this.val$univ.behaviorScheduler = new BehaviorScheduler(rootThreadGroup, univ);
/*      */                 
/* 1965 */                 this.val$univ.behaviorScheduler.setPriority(threadPriority);
/*      */               } 
/* 1967 */               return null;
/*      */             }
/*      */           });
/* 1970 */       virtualUniverse.behaviorScheduler.initialize();
/* 1971 */       virtualUniverse.behaviorScheduler.userStop = paramView.stopBehavior;
/* 1972 */       j3dThreadData = virtualUniverse.behaviorScheduler.getThreadData(null, null);
/* 1973 */       j3dThreadData.thread = virtualUniverse.behaviorScheduler;
/* 1974 */       j3dThreadData.threadType = 1;
/* 1975 */       j3dThreadData.lastUpdateTime = Math.max(this.currentTime, j3dThreadData.lastUpdateTime);
/*      */     } 
/*      */ 
/*      */     
/* 1979 */     createUpdateThread(paramView.renderBin);
/* 1980 */     createUpdateThread(paramView.soundScheduler);
/*      */     
/* 1982 */     if (paramView.physicalEnvironment != null) {
/* 1983 */       paramView.physicalEnvironment.addUser(paramView);
/*      */     }
/*      */     
/* 1986 */     evaluatePhysicalEnv(paramView);
/*      */     
/* 1988 */     this.regUniverseList.addUnique(virtualUniverse);
/* 1989 */     this.views.addUnique(paramView);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void unregisterView(View paramView) {
/* 2000 */     if (!this.views.remove(paramView)) {
/* 2001 */       paramView.active = false;
/* 2002 */       paramView.doneUnregister = true;
/*      */       
/*      */       return;
/*      */     } 
/* 2006 */     if (paramView.active) {
/* 2007 */       viewDeactivate(paramView);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2015 */     paramView.soundScheduler.updateThread.finish();
/* 2016 */     paramView.renderBin.updateThread.finish();
/* 2017 */     paramView.soundScheduler.updateThread = null;
/* 2018 */     paramView.renderBin.updateThread = null;
/*      */ 
/*      */ 
/*      */     
/* 2022 */     VirtualUniverse virtualUniverse = paramView.universe;
/*      */ 
/*      */     
/* 2025 */     synchronized (this.timeLock) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2032 */       emptyMessageList(paramView.soundScheduler, paramView);
/* 2033 */       emptyMessageList(paramView.renderBin, paramView);
/*      */       
/* 2035 */       if (virtualUniverse.isEmpty()) {
/* 2036 */         destroyUniverseThreads(virtualUniverse);
/*      */       } else {
/* 2038 */         emptyMessageList(virtualUniverse.behaviorStructure, paramView);
/* 2039 */         emptyMessageList(virtualUniverse.geometryStructure, paramView);
/* 2040 */         emptyMessageList(virtualUniverse.soundStructure, paramView);
/* 2041 */         emptyMessageList(virtualUniverse.renderingEnvironmentStructure, paramView);
/* 2042 */         emptyMessageList(virtualUniverse.transformStructure, paramView);
/*      */       } 
/*      */     } 
/*      */     
/* 2046 */     if (paramView.physicalEnvironment != null) {
/* 2047 */       paramView.physicalEnvironment.removeUser(paramView);
/*      */     }
/*      */ 
/*      */     
/* 2051 */     UnorderList unorderList = new UnorderList(1, PhysicalEnvironment.class);
/* 2052 */     Enumeration enumeration = PhysicalEnvironment.physicalEnvMap.keys();
/* 2053 */     while (enumeration.hasMoreElements()) {
/* 2054 */       PhysicalEnvironment physicalEnvironment = (PhysicalEnvironment)enumeration.nextElement();
/* 2055 */       InputDeviceScheduler inputDeviceScheduler = (InputDeviceScheduler)PhysicalEnvironment.physicalEnvMap.get(physicalEnvironment);
/*      */       int j;
/* 2057 */       for (j = physicalEnvironment.users.size() - 1; j >= 0 && 
/* 2058 */         !this.views.contains((View)physicalEnvironment.users.get(j)); j--);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2063 */       if (j < 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2069 */         inputDeviceScheduler.finish();
/* 2070 */         physicalEnvironment.inputsched = null;
/* 2071 */         unorderList.add(physicalEnvironment);
/*      */       } 
/*      */     } 
/* 2074 */     for (int i = unorderList.size() - 1; i >= 0; i--) {
/* 2075 */       PhysicalEnvironment.physicalEnvMap.remove(unorderList.get(i));
/*      */     }
/*      */ 
/*      */     
/* 2079 */     freeContext(paramView);
/*      */     
/* 2081 */     if (this.views.isEmpty()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2087 */       enumeration = Screen3D.deviceRendererMap.elements();
/* 2088 */       while (enumeration.hasMoreElements()) {
/* 2089 */         Renderer renderer = (Renderer)enumeration.nextElement();
/*      */ 
/*      */         
/* 2092 */         this.rendererCleanupArgs[2] = REMOVEALLCTXS_CLEANUP;
/* 2093 */         runMonitor(6, null, null, null, renderer);
/* 2094 */         Screen3D screen3D = renderer.onScreen;
/* 2095 */         if (screen3D != null && 
/* 2096 */           screen3D.renderer != null) {
/* 2097 */           this.rendererCleanupArgs[2] = REMOVEALLCTXS_CLEANUP;
/* 2098 */           runMonitor(6, null, null, null, screen3D.renderer);
/*      */           
/* 2100 */           screen3D.renderer = null;
/*      */         } 
/*      */ 
/*      */         
/* 2104 */         screen3D = renderer.offScreen;
/* 2105 */         if (screen3D != null && 
/* 2106 */           screen3D.renderer != null) {
/* 2107 */           this.rendererCleanupArgs[2] = REMOVEALLCTXS_CLEANUP;
/* 2108 */           runMonitor(6, null, null, null, screen3D.renderer);
/*      */           
/* 2110 */           screen3D.renderer = null;
/*      */         } 
/*      */         
/* 2113 */         renderer.onScreen = null;
/* 2114 */         renderer.offScreen = null;
/*      */       } 
/*      */ 
/*      */       
/* 2118 */       enumeration = Screen3D.deviceRendererMap.elements();
/* 2119 */       while (enumeration.hasMoreElements()) {
/* 2120 */         Renderer renderer = (Renderer)enumeration.nextElement();
/* 2121 */         renderer.cleanup();
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 2129 */       enumeration = Screen3D.deviceRendererMap.elements();
/* 2130 */       while (enumeration.hasMoreElements()) {
/* 2131 */         Renderer renderer = (Renderer)enumeration.nextElement();
/* 2132 */         renderer.cleanupView();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2137 */     this.freeMessageList.add(virtualUniverse);
/* 2138 */     this.freeMessageList.add(paramView);
/*      */     
/* 2140 */     evaluateAllCanvases();
/* 2141 */     this.stateWorkThreads.clear();
/* 2142 */     this.renderWorkThreads.clear();
/* 2143 */     this.requestRenderWorkThreads.clear();
/* 2144 */     this.threadListsChanged = true;
/*      */ 
/*      */     
/* 2147 */     paramView.doneUnregister = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void createMCThreads() {
/* 2157 */     createUpdateThread(this.renderingAttributesStructure);
/*      */ 
/*      */     
/* 2160 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run() {
/* 2163 */             synchronized (rootThreadGroup) {
/* 2164 */               MasterControl.this.timerThread = new TimerThread(rootThreadGroup);
/* 2165 */               MasterControl.this.timerThread.setPriority(threadPriority);
/*      */             } 
/* 2167 */             return null;
/*      */           }
/*      */         });
/* 2170 */     this.timerThread.start();
/*      */ 
/*      */     
/* 2173 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run() {
/* 2176 */             synchronized (rootThreadGroup) {
/* 2177 */               MasterControl.this.notificationThread = new NotificationThread(rootThreadGroup);
/* 2178 */               MasterControl.this.notificationThread.setPriority(threadPriority);
/*      */             } 
/* 2180 */             return null;
/*      */           }
/*      */         });
/* 2183 */     this.notificationThread.start();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void destroyUniverseThreads(VirtualUniverse paramVirtualUniverse) {
/* 2193 */     if (this.regUniverseList.contains(paramVirtualUniverse)) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2198 */       destroyUpdateThread(paramVirtualUniverse.behaviorStructure);
/* 2199 */       destroyUpdateThread(paramVirtualUniverse.geometryStructure);
/* 2200 */       destroyUpdateThread(paramVirtualUniverse.soundStructure);
/* 2201 */       destroyUpdateThread(paramVirtualUniverse.renderingEnvironmentStructure);
/* 2202 */       destroyUpdateThread(paramVirtualUniverse.transformStructure);
/* 2203 */       paramVirtualUniverse.behaviorScheduler.finish();
/* 2204 */       paramVirtualUniverse.behaviorScheduler.free();
/* 2205 */       paramVirtualUniverse.behaviorScheduler = null;
/* 2206 */       paramVirtualUniverse.initMCStructure();
/* 2207 */       this.activeUniverseList.remove(paramVirtualUniverse);
/* 2208 */       this.regUniverseList.remove(paramVirtualUniverse);
/*      */     } else {
/* 2210 */       emptyMessageList(paramVirtualUniverse.behaviorStructure, null);
/* 2211 */       emptyMessageList(paramVirtualUniverse.geometryStructure, null);
/* 2212 */       emptyMessageList(paramVirtualUniverse.soundStructure, null);
/* 2213 */       emptyMessageList(paramVirtualUniverse.renderingEnvironmentStructure, null);
/* 2214 */       emptyMessageList(paramVirtualUniverse.transformStructure, null);
/*      */     } 
/*      */     
/* 2217 */     if (this.regUniverseList.isEmpty() && this.views.isEmpty()) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2222 */       if (this.renderingAttributesStructure.updateThread != null) {
/* 2223 */         this.renderingAttributesStructure.updateThread.finish();
/* 2224 */         this.renderingAttributesStructure.updateThread = null;
/*      */       } 
/* 2226 */       this.renderingAttributesStructure.messageList.clear();
/* 2227 */       this.renderingAttributesStructure.objList = new ArrayList();
/* 2228 */       this.renderingAttributesStructure = new RenderingAttributesStructure();
/* 2229 */       if (this.timerThread != null) {
/* 2230 */         this.timerThread.finish();
/* 2231 */         this.timerThread = null;
/*      */       } 
/* 2233 */       if (this.notificationThread != null) {
/* 2234 */         this.notificationThread.finish();
/* 2235 */         this.notificationThread = null;
/*      */       } 
/*      */ 
/*      */       
/* 2239 */       synchronized (VirtualUniverse.mc.deviceScreenMap) {
/* 2240 */         this.deviceScreenMap.clear();
/*      */       } 
/*      */       
/* 2243 */       this.mirrorObjects.clear();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2248 */       for (byte b = 0; b < this.canvasIds.length; b++) {
/* 2249 */         this.canvasIds[b] = false;
/*      */       }
/* 2251 */       this.canvasFreeIndex = 0;
/*      */       
/* 2253 */       this.renderOnceList.clear();
/* 2254 */       this.timestampUpdateList.clear();
/*      */       
/* 2256 */       this.defaultRenderMethod = null;
/* 2257 */       this.text3DRenderMethod = null;
/* 2258 */       this.vertexArrayRenderMethod = null;
/* 2259 */       this.displayListRenderMethod = null;
/* 2260 */       this.compressedGeometryRenderMethod = null;
/* 2261 */       this.orientedShape3DRenderMethod = null;
/*      */       
/* 2263 */       this.running = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void evaluateAllCanvases() {
/* 2274 */     synchronized (this.renderThreadData) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2279 */       this.renderThreadData.clear();
/*      */ 
/*      */       
/* 2282 */       View[] arrayOfView = (View[])this.views.toArray(false); int i;
/* 2283 */       for (i = this.views.size() - 1; i >= 0; i--) {
/* 2284 */         arrayOfView[i].getCanvasList(true);
/* 2285 */         Screen3D[] arrayOfScreen3D = arrayOfView[i].getScreens();
/* 2286 */         for (int j = arrayOfScreen3D.length - 1; j >= 0; j--) {
/* 2287 */           (arrayOfScreen3D[j]).canvasCount = 0;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2293 */       for (i = this.views.size() - 1; i >= 0; i--) {
/* 2294 */         View view = arrayOfView[i];
/* 2295 */         Canvas3D[][] arrayOfCanvas3D = view.getCanvasList(false);
/* 2296 */         if (view.active)
/*      */         {
/*      */ 
/*      */           
/* 2300 */           for (int j = arrayOfCanvas3D.length - 1; j >= 0; j--) {
/* 2301 */             boolean bool = false;
/*      */             
/* 2303 */             int k = arrayOfCanvas3D[j].length - 1; while (true) { if (k >= 0) {
/* 2304 */                 Canvas3D canvas3D = arrayOfCanvas3D[j][k];
/*      */                 
/* 2306 */                 final Screen3D screen = canvas3D.screen;
/*      */                 
/* 2308 */                 if (canvas3D.active) {
/* 2309 */                   if (screen3D.canvasCount++ == 0)
/*      */                   {
/* 2311 */                     if (screen3D.renderer == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */                       
/* 2316 */                       screen3D; Renderer renderer = (Renderer)Screen3D.deviceRendererMap.get(canvas3D.screen.graphicsDevice);
/*      */ 
/*      */                       
/* 2319 */                       if (renderer == null) {
/* 2320 */                         AccessController.doPrivileged(new PrivilegedAction()
/*      */                             {
/*      */                               public Object run()
/*      */                               {
/* 2324 */                                 synchronized (rootThreadGroup) {
/* 2325 */                                   this.val$screen.renderer = new Renderer(rootThreadGroup);
/*      */ 
/*      */                                   
/* 2328 */                                   this.val$screen.renderer.setPriority(threadPriority);
/*      */                                 } 
/* 2330 */                                 return null;
/*      */                               }
/*      */                             });
/* 2333 */                         screen3D.renderer.initialize();
/* 2334 */                         screen3D; Screen3D.deviceRendererMap.put(screen3D.graphicsDevice, screen3D.renderer);
/*      */                       } else {
/*      */                         
/* 2337 */                         screen3D.renderer = renderer;
/*      */                       } 
/*      */                     } 
/*      */                   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 2352 */                   if (!canvas3D.manualRendering) {
/* 2353 */                     screen3D.renderer.onScreen = screen3D;
/*      */                   } else {
/* 2355 */                     screen3D.renderer.offScreen = screen3D;
/*      */                     
/*      */                     k--;
/*      */                   } 
/* 2359 */                   if (!bool) {
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/* 2364 */                     J3dThreadData j3dThreadData1 = screen3D.renderer.getThreadData(view, null);
/*      */                     
/* 2366 */                     this.renderThreadData.add(j3dThreadData1);
/*      */ 
/*      */ 
/*      */                     
/* 2370 */                     if (view.renderBinReady) {
/* 2371 */                       j3dThreadData1.lastUpdateTime = Math.max(this.currentTime, j3dThreadData1.lastUpdateTime);
/*      */                     }
/*      */ 
/*      */                     
/* 2375 */                     bool = true;
/*      */                   } 
/*      */                   
/* 2378 */                   J3dThreadData j3dThreadData = screen3D.renderer.getThreadData(view, canvas3D);
/*      */                   
/* 2380 */                   this.renderThreadData.add(j3dThreadData);
/* 2381 */                   if (view.renderBinReady)
/* 2382 */                     j3dThreadData.lastUpdateTime = Math.max(this.currentTime, j3dThreadData.lastUpdateTime); 
/*      */                 } 
/*      */               } else {
/*      */                 break;
/*      */               } 
/*      */               k--; }
/*      */           
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/* 2393 */     this.threadListsChanged = true;
/*      */   }
/*      */   
/*      */   private void evaluatePhysicalEnv(View paramView) {
/* 2397 */     final PhysicalEnvironment env = paramView.physicalEnvironment;
/*      */     
/* 2399 */     if (physicalEnvironment.inputsched == null) {
/* 2400 */       AccessController.doPrivileged(new PrivilegedAction()
/*      */           {
/*      */             public Object run() {
/* 2403 */               synchronized (rootThreadGroup) {
/* 2404 */                 this.val$env.inputsched = new InputDeviceScheduler(rootThreadGroup, env);
/*      */ 
/*      */                 
/* 2407 */                 this.val$env.inputsched.setPriority(threadPriority);
/*      */               } 
/* 2409 */               return null;
/*      */             }
/*      */           });
/* 2412 */       physicalEnvironment.inputsched.start();
/* 2413 */       PhysicalEnvironment.physicalEnvMap.put(physicalEnvironment, physicalEnvironment.inputsched);
/*      */     } 
/* 2415 */     this.threadListsChanged = true;
/*      */   }
/*      */   
/*      */   private final void addToStateThreads(J3dThreadData paramJ3dThreadData) {
/* 2419 */     if (paramJ3dThreadData.thread.active) {
/* 2420 */       this.stateWorkThreads.add(paramJ3dThreadData);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void assignNewPrimaryView(VirtualUniverse paramVirtualUniverse) {
/* 2427 */     View view = paramVirtualUniverse.getCurrentView();
/*      */     
/* 2429 */     if (view != null) {
/* 2430 */       view.primaryView = false;
/*      */     }
/*      */     
/* 2433 */     View[] arrayOfView = (View[])this.views.toArray(false);
/* 2434 */     int i = this.views.size();
/* 2435 */     for (byte b = 0; b < i; b++) {
/* 2436 */       View view1 = arrayOfView[b];
/* 2437 */       if (view1.active && view1.isRunning && paramVirtualUniverse == view1.universe) {
/*      */         
/* 2439 */         view1.primaryView = true;
/* 2440 */         paramVirtualUniverse.setCurrentView(view1);
/*      */         return;
/*      */       } 
/*      */     } 
/* 2444 */     paramVirtualUniverse.setCurrentView(null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   RenderMethod getDefaultRenderMethod() {
/* 2452 */     if (this.defaultRenderMethod == null) {
/* 2453 */       this.defaultRenderMethod = new DefaultRenderMethod();
/*      */     }
/* 2455 */     return this.defaultRenderMethod;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   RenderMethod getText3DRenderMethod() {
/* 2462 */     if (this.text3DRenderMethod == null) {
/* 2463 */       this.text3DRenderMethod = new Text3DRenderMethod();
/*      */     }
/* 2465 */     return this.text3DRenderMethod;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   RenderMethod getVertexArrayRenderMethod() {
/* 2473 */     if (this.vertexArrayRenderMethod == null) {
/* 2474 */       this.vertexArrayRenderMethod = new VertexArrayRenderMethod();
/*      */     }
/* 2476 */     return this.vertexArrayRenderMethod;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   RenderMethod getDisplayListRenderMethod() {
/* 2483 */     if (this.displayListRenderMethod == null) {
/* 2484 */       this.displayListRenderMethod = new DisplayListRenderMethod();
/*      */     }
/* 2486 */     return this.displayListRenderMethod;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   RenderMethod getCompressedGeometryRenderMethod() {
/* 2493 */     if (this.compressedGeometryRenderMethod == null) {
/* 2494 */       this.compressedGeometryRenderMethod = new CompressedGeometryRenderMethod();
/*      */     }
/*      */     
/* 2497 */     return this.compressedGeometryRenderMethod;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   RenderMethod getOrientedShape3DRenderMethod() {
/* 2504 */     if (this.orientedShape3DRenderMethod == null) {
/* 2505 */       this.orientedShape3DRenderMethod = new OrientedShape3DRenderMethod();
/*      */     }
/* 2507 */     return this.orientedShape3DRenderMethod;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void viewActivate(View paramView) {
/* 2515 */     VirtualUniverse virtualUniverse = paramView.universe;
/*      */     
/* 2517 */     if (virtualUniverse == null) {
/*      */       return;
/*      */     }
/*      */     
/* 2521 */     if (!this.views.contains(paramView) || !this.regUniverseList.contains(virtualUniverse)) {
/* 2522 */       registerView(paramView);
/* 2523 */     } else if (paramView.active) {
/* 2524 */       evaluateAllCanvases();
/*      */       
/*      */       return;
/*      */     } 
/* 2528 */     if (virtualUniverse.activeViewCount == 0) {
/* 2529 */       virtualUniverse.geometryStructure.resetConditionMet();
/* 2530 */       virtualUniverse.behaviorStructure.resetConditionMet();
/*      */     } 
/*      */     
/* 2533 */     if (paramView.isRunning) {
/* 2534 */       this.numActiveViews++;
/* 2535 */       virtualUniverse.activeViewCount++;
/* 2536 */       this.renderingAttributesStructure.updateThread.active = true;
/* 2537 */       virtualUniverse.transformStructure.updateThread.active = true;
/* 2538 */       virtualUniverse.geometryStructure.updateThread.active = true;
/* 2539 */       virtualUniverse.soundStructure.updateThread.active = true;
/* 2540 */       virtualUniverse.renderingEnvironmentStructure.updateThread.active = true;
/*      */     } 
/* 2542 */     virtualUniverse.behaviorScheduler.active = true;
/* 2543 */     virtualUniverse.behaviorStructure.updateThread.active = true;
/*      */ 
/*      */     
/* 2546 */     this.activeUniverseList.addUnique(virtualUniverse);
/*      */     
/* 2548 */     if (paramView.isRunning) {
/* 2549 */       paramView.soundScheduler.activate();
/* 2550 */       paramView.renderBin.updateThread.active = true;
/*      */     } 
/* 2552 */     paramView.active = true;
/*      */     
/* 2554 */     if (paramView.physicalEnvironment.activeViewRef++ == 0) {
/* 2555 */       paramView.physicalEnvironment.inputsched.activate();
/*      */     }
/*      */ 
/*      */     
/* 2559 */     if (virtualUniverse.getCurrentView() == null) {
/* 2560 */       assignNewPrimaryView(virtualUniverse);
/*      */     }
/*      */     
/* 2563 */     evaluateAllCanvases();
/* 2564 */     paramView.inRenderThreadData = true;
/* 2565 */     this.threadListsChanged = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2572 */     paramView.renderBin.reactivateView = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void freeContext(View paramView) {
/* 2579 */     Canvas3D[][] arrayOfCanvas3D = paramView.getCanvasList(false);
/*      */     
/* 2581 */     for (int i = arrayOfCanvas3D.length - 1; i >= 0; i--) {
/* 2582 */       for (int j = arrayOfCanvas3D[i].length - 1; j >= 0; j--) {
/* 2583 */         Canvas3D canvas3D = arrayOfCanvas3D[i][j];
/* 2584 */         if (!canvas3D.validCanvas && 
/* 2585 */           canvas3D.screen != null && canvas3D.screen.renderer != null) {
/*      */           
/* 2587 */           this.rendererCleanupArgs[1] = canvas3D;
/* 2588 */           this.rendererCleanupArgs[2] = FREECONTEXT_CLEANUP;
/* 2589 */           runMonitor(6, null, null, null, canvas3D.screen.renderer);
/*      */           
/* 2591 */           this.rendererCleanupArgs[1] = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void viewDeactivate(View paramView) {
/* 2603 */     if (!this.views.contains(paramView) || !paramView.active) {
/* 2604 */       paramView.active = false;
/* 2605 */       evaluateAllCanvases();
/*      */       
/*      */       return;
/*      */     } 
/* 2609 */     VirtualUniverse virtualUniverse = paramView.universe;
/*      */     
/* 2611 */     if (paramView.isRunning) {
/*      */       
/* 2613 */       this.numActiveViews--;
/* 2614 */       virtualUniverse.activeViewCount--;
/*      */     } 
/*      */     
/* 2617 */     if (this.numActiveViews == 0) {
/* 2618 */       this.renderingAttributesStructure.updateThread.active = false;
/*      */     }
/*      */     
/* 2621 */     if (virtualUniverse.activeViewCount == 0)
/*      */     {
/* 2623 */       if (virtualUniverse.behaviorScheduler != null) {
/* 2624 */         virtualUniverse.behaviorScheduler.deactivate();
/* 2625 */         virtualUniverse.transformStructure.updateThread.active = false;
/* 2626 */         virtualUniverse.geometryStructure.updateThread.active = false;
/* 2627 */         virtualUniverse.behaviorStructure.updateThread.active = false;
/* 2628 */         virtualUniverse.soundStructure.updateThread.active = false;
/* 2629 */         virtualUniverse.renderingEnvironmentStructure.updateThread.active = false;
/*      */         
/* 2631 */         this.activeUniverseList.remove(virtualUniverse);
/*      */       } 
/*      */     }
/*      */     
/* 2635 */     paramView.soundScheduler.deactivate();
/* 2636 */     paramView.renderBin.updateThread.active = false;
/* 2637 */     paramView.active = false;
/* 2638 */     if (--paramView.physicalEnvironment.activeViewRef == 0) {
/* 2639 */       paramView.physicalEnvironment.inputsched.deactivate();
/*      */     }
/* 2641 */     assignNewPrimaryView(virtualUniverse);
/*      */ 
/*      */     
/* 2644 */     evaluateAllCanvases();
/*      */     
/* 2646 */     freeContext(paramView);
/*      */     
/* 2648 */     paramView.inRenderThreadData = false;
/* 2649 */     this.threadListsChanged = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void startView(View paramView) {
/* 2658 */     if (!this.views.contains(paramView) || paramView.isRunning || !paramView.active) {
/* 2659 */       paramView.isRunning = true;
/*      */       
/*      */       return;
/*      */     } 
/* 2663 */     this.numActiveViews++;
/* 2664 */     this.renderingAttributesStructure.updateThread.active = true;
/*      */     
/* 2666 */     VirtualUniverse virtualUniverse = paramView.universe;
/*      */     
/* 2668 */     virtualUniverse.activeViewCount++;
/* 2669 */     virtualUniverse.transformStructure.updateThread.active = true;
/* 2670 */     virtualUniverse.geometryStructure.updateThread.active = true;
/* 2671 */     virtualUniverse.soundStructure.updateThread.active = true;
/* 2672 */     virtualUniverse.renderingEnvironmentStructure.updateThread.active = true;
/* 2673 */     paramView.renderBin.updateThread.active = true;
/* 2674 */     paramView.soundScheduler.activate();
/* 2675 */     paramView.isRunning = true;
/* 2676 */     if (virtualUniverse.getCurrentView() == null) {
/* 2677 */       assignNewPrimaryView(virtualUniverse);
/*      */     }
/* 2679 */     this.threadListsChanged = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void stopView(View paramView) {
/* 2687 */     if (!this.views.contains(paramView) || !paramView.isRunning || !paramView.active) {
/* 2688 */       paramView.isRunning = false;
/*      */       
/*      */       return;
/*      */     } 
/* 2692 */     if (--this.numActiveViews == 0) {
/* 2693 */       this.renderingAttributesStructure.updateThread.active = false;
/*      */     }
/* 2695 */     VirtualUniverse virtualUniverse = paramView.universe;
/*      */     
/* 2697 */     if (--virtualUniverse.activeViewCount == 0) {
/* 2698 */       virtualUniverse.transformStructure.updateThread.active = false;
/* 2699 */       virtualUniverse.geometryStructure.updateThread.active = false;
/* 2700 */       virtualUniverse.renderingEnvironmentStructure.updateThread.active = false;
/* 2701 */       virtualUniverse.soundStructure.updateThread.active = false;
/*      */     } 
/*      */     
/* 2704 */     paramView.renderBin.updateThread.active = false;
/* 2705 */     paramView.soundScheduler.deactivate();
/* 2706 */     paramView.isRunning = false;
/* 2707 */     assignNewPrimaryView(virtualUniverse);
/* 2708 */     this.threadListsChanged = true;
/*      */   }
/*      */ 
/*      */   
/*      */   void addInputDeviceScheduler(InputDeviceScheduler paramInputDeviceScheduler) {
/* 2713 */     synchronized (this.inputDeviceThreads) {
/* 2714 */       this.inputDeviceThreads.add(paramInputDeviceScheduler);
/* 2715 */       if (this.inputDeviceThreads.size() == 1) {
/* 2716 */         this.timerThread.addInputDeviceSchedCond();
/*      */       }
/*      */     } 
/* 2719 */     postRequest(INPUTDEVICE_CHANGE, null);
/*      */   }
/*      */ 
/*      */   
/*      */   void removeInputDeviceScheduler(InputDeviceScheduler paramInputDeviceScheduler) {
/* 2724 */     this.inputDeviceThreads.remove(paramInputDeviceScheduler);
/* 2725 */     postRequest(INPUTDEVICE_CHANGE, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2732 */   void addMirrorObject(ObjectUpdate paramObjectUpdate) { this.mirrorObjects.add(paramObjectUpdate); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateMirrorObjects() {
/* 2740 */     ObjectUpdate[] arrayOfObjectUpdate = (ObjectUpdate[])this.mirrorObjects.toArray(false);
/* 2741 */     int i = this.mirrorObjects.arraySize();
/*      */     
/* 2743 */     for (byte b = 0; b < i; b++) {
/* 2744 */       arrayOfObjectUpdate[b].updateObject();
/*      */     }
/* 2746 */     this.mirrorObjects.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateWorkThreads() {
/* 2756 */     this.stateWorkThreads.clear();
/* 2757 */     this.renderWorkThreads.clear();
/* 2758 */     this.requestRenderWorkThreads.clear();
/*      */ 
/*      */     
/* 2761 */     if (this.numActiveViews > 0) {
/* 2762 */       addToStateThreads(this.renderingAttributesStructure.getUpdateThreadData());
/*      */     }
/*      */ 
/*      */     
/* 2766 */     VirtualUniverse[] arrayOfVirtualUniverse = (VirtualUniverse[])this.activeUniverseList.toArray(false);
/*      */ 
/*      */ 
/*      */     
/* 2770 */     int j = this.activeUniverseList.arraySize();
/*      */     int i;
/* 2772 */     for (i = j - 1; i >= 0; i--) {
/* 2773 */       addToStateThreads((arrayOfVirtualUniverse[i]).transformStructure.getUpdateThreadData());
/*      */     }
/* 2775 */     this.lastTransformStructureThread = this.stateWorkThreads.size();
/*      */ 
/*      */ 
/*      */     
/* 2779 */     for (i = j - 1; i >= 0; i--) {
/* 2780 */       VirtualUniverse virtualUniverse = arrayOfVirtualUniverse[i];
/* 2781 */       addToStateThreads(virtualUniverse.geometryStructure.getUpdateThreadData());
/* 2782 */       addToStateThreads(virtualUniverse.behaviorStructure.getUpdateThreadData());
/* 2783 */       addToStateThreads(virtualUniverse.renderingEnvironmentStructure.getUpdateThreadData());
/* 2784 */       addToStateThreads(virtualUniverse.soundStructure.getUpdateThreadData());
/*      */     } 
/*      */     
/* 2787 */     this.lastStructureUpdateThread = this.stateWorkThreads.size();
/*      */ 
/*      */     
/* 2790 */     for (i = j - 1; i >= 0; i--) {
/* 2791 */       addToStateThreads((arrayOfVirtualUniverse[i]).behaviorScheduler.getThreadData(null, null));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2798 */     InputDeviceScheduler[] arrayOfInputDeviceScheduler = (InputDeviceScheduler[])this.inputDeviceThreads.toArray(true);
/*      */     
/* 2800 */     for (i = this.inputDeviceThreads.size() - 1; i >= 0; i--) {
/* 2801 */       J3dThreadData j3dThreadData1 = arrayOfInputDeviceScheduler[i].getThreadData();
/* 2802 */       j3dThreadData1.thread.active = true;
/* 2803 */       addToStateThreads(j3dThreadData1);
/*      */     } 
/*      */ 
/*      */     
/* 2807 */     View[] arrayOfView = (View[])this.views.toArray(false);
/*      */ 
/*      */     
/* 2810 */     for (i = this.views.size() - 1; i >= 0; i--) {
/* 2811 */       View view = arrayOfView[i];
/* 2812 */       if (view.active && view.isRunning) {
/* 2813 */         addToStateThreads(view.renderBin.getUpdateThreadData());
/* 2814 */         addToStateThreads(view.soundScheduler.getUpdateThreadData());
/* 2815 */         Canvas3D[][] arrayOfCanvas3D = view.getCanvasList(false);
/* 2816 */         int k = view.getLongestScreenList();
/* 2817 */         Object[] arrayOfObject = null;
/*      */         byte b;
/* 2819 */         for (b = 0; b < k; b++) {
/* 2820 */           for (byte b1 = 0; b1 < arrayOfCanvas3D.length; b1++) {
/* 2821 */             if (b < arrayOfCanvas3D[b1].length) {
/* 2822 */               Canvas3D canvas3D = arrayOfCanvas3D[b1][b];
/*      */               
/* 2824 */               if (canvas3D.active && canvas3D.isRunningStatus && !canvas3D.manualRendering && 
/* 2825 */                 canvas3D.screen.renderer != null) {
/*      */ 
/*      */                 
/* 2828 */                 J3dThreadData j3dThreadData1 = canvas3D.screen.renderer.getThreadData(view, canvas3D);
/* 2829 */                 this.renderWorkThreads.add(j3dThreadData1);
/* 2830 */                 arrayOfObject = (Object[])j3dThreadData1.threadArgs;
/* 2831 */                 arrayOfObject[0] = RENDER;
/* 2832 */                 arrayOfObject[1] = canvas3D;
/* 2833 */                 arrayOfObject[2] = view;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 2840 */         for (b = 0; b < arrayOfCanvas3D.length; b++) {
/* 2841 */           for (byte b1 = 0; b1 < arrayOfCanvas3D[b].length; ) {
/* 2842 */             Canvas3D canvas3D = arrayOfCanvas3D[b][b1];
/*      */ 
/*      */ 
/*      */             
/* 2846 */             if (!canvas3D.active || !canvas3D.isRunningStatus || canvas3D.manualRendering || 
/* 2847 */               canvas3D.screen.renderer == null) {
/*      */               b1++;
/*      */               continue;
/*      */             } 
/* 2851 */             J3dThreadData j3dThreadData1 = canvas3D.screen.renderer.getThreadData(view, null);
/* 2852 */             this.renderWorkThreads.add(j3dThreadData1);
/* 2853 */             arrayOfObject = (Object[])j3dThreadData1.threadArgs;
/* 2854 */             arrayOfObject[0] = SWAP;
/* 2855 */             arrayOfObject[1] = view;
/* 2856 */             arrayOfObject[2] = arrayOfCanvas3D[b];
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2864 */     J3dThreadData j3dThreadData = null;
/*      */     
/* 2866 */     Enumeration enumeration = Screen3D.deviceRendererMap.elements();
/* 2867 */     while (enumeration.hasMoreElements()) {
/* 2868 */       Renderer renderer = (Renderer)enumeration.nextElement();
/* 2869 */       j3dThreadData = renderer.getThreadData(null, null);
/* 2870 */       this.requestRenderWorkThreads.add(j3dThreadData);
/* 2871 */       j3dThreadData.threadOpts = 2;
/* 2872 */       (Object[])j3dThreadData.threadArgs[0] = REQUESTRENDER;
/*      */     } 
/*      */     
/* 2875 */     if (j3dThreadData != null) {
/* 2876 */       j3dThreadData.threadOpts |= 0x1;
/*      */     }
/*      */     
/* 2879 */     this.threadListsChanged = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void dumpWorkThreads() {
/* 2886 */     System.err.println("-----------------------------");
/* 2887 */     System.err.println("MasterControl/dumpWorkThreads");
/*      */ 
/*      */     
/* 2890 */     int i = 0;
/*      */     
/* 2892 */     for (byte b = 0; b < 3; b++) {
/* 2893 */       J3dThreadData[] arrayOfJ3dThreadData; switch (b) {
/*      */         case false:
/* 2895 */           arrayOfJ3dThreadData = (J3dThreadData[])this.stateWorkThreads.toArray(false);
/* 2896 */           i = this.stateWorkThreads.arraySize();
/*      */           break;
/*      */         case true:
/* 2899 */           arrayOfJ3dThreadData = (J3dThreadData[])this.renderWorkThreads.toArray(false);
/* 2900 */           i = this.renderWorkThreads.arraySize();
/*      */           break;
/*      */         default:
/* 2903 */           arrayOfJ3dThreadData = (J3dThreadData[])this.requestRenderWorkThreads.toArray(false);
/* 2904 */           i = this.requestRenderWorkThreads.arraySize();
/*      */           break;
/*      */       } 
/*      */       
/* 2908 */       for (byte b1 = 0; b1 < i; b1++) {
/* 2909 */         J3dThreadData j3dThreadData = arrayOfJ3dThreadData[b1];
/* 2910 */         System.err.println("Thread " + b1 + ": " + j3dThreadData.thread);
/* 2911 */         System.err.println("\tOps: " + j3dThreadData.threadOpts);
/* 2912 */         if (j3dThreadData.threadArgs != null) {
/* 2913 */           Object[] arrayOfObject = (Object[])j3dThreadData.threadArgs;
/* 2914 */           System.err.print("\tArgs: ");
/* 2915 */           for (byte b2 = 0; b2 < arrayOfObject.length; b2++) {
/* 2916 */             System.err.print(arrayOfObject[b2] + " ");
/*      */           }
/*      */         } 
/* 2919 */         System.err.println("");
/*      */       } 
/*      */     } 
/* 2922 */     System.err.println("-----------------------------");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2931 */   final void setWork() { runMonitor(1, null, null, null, null); }
/*      */ 
/*      */ 
/*      */   
/* 2935 */   final void setWorkForRequestRenderer() { runMonitor(5, null, null, null, null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendRenderMessage(GraphicsConfiguration paramGraphicsConfiguration, Object paramObject, Integer paramInteger) {
/* 2946 */     Renderer renderer = createRenderer(paramGraphicsConfiguration);
/* 2947 */     J3dMessage j3dMessage = new J3dMessage();
/* 2948 */     j3dMessage.threads = 16;
/* 2949 */     j3dMessage.type = 44;
/* 2950 */     j3dMessage.universe = null;
/* 2951 */     j3dMessage.view = null;
/* 2952 */     j3dMessage.args[0] = null;
/* 2953 */     j3dMessage.args[1] = paramObject;
/* 2954 */     j3dMessage.args[2] = paramInteger;
/* 2955 */     renderer.rendererStructure.addMessage(j3dMessage);
/* 2956 */     setWorkForRequestRenderer();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendDestroyCtxAndOffScreenBuffer(Canvas3D paramCanvas3D) {
/* 2963 */     GraphicsDevice graphicsDevice = paramCanvas3D.graphicsConfiguration.getDevice();
/* 2964 */     assert Screen3D.deviceRendererMap.get(graphicsDevice) != null;
/*      */     
/* 2966 */     synchronized (mcThreadLock) {
/*      */       
/* 2968 */       createMasterControlThread();
/* 2969 */       assert this.mcThread != null;
/*      */       
/* 2971 */       Renderer renderer = createRenderer(paramCanvas3D.graphicsConfiguration);
/* 2972 */       J3dMessage j3dMessage = new J3dMessage();
/* 2973 */       j3dMessage.threads = 16;
/* 2974 */       j3dMessage.type = 62;
/* 2975 */       j3dMessage.universe = null;
/* 2976 */       j3dMessage.view = null;
/* 2977 */       j3dMessage.args[0] = paramCanvas3D;
/*      */       
/* 2979 */       j3dMessage.args[1] = new Long(paramCanvas3D.screen.display);
/* 2980 */       j3dMessage.args[2] = paramCanvas3D.drawable;
/* 2981 */       j3dMessage.args[3] = paramCanvas3D.ctx;
/* 2982 */       renderer.rendererStructure.addMessage(j3dMessage);
/* 2983 */       synchronized (this.requestObjList) {
/* 2984 */         setWorkForRequestRenderer();
/* 2985 */         this.pendingRequest = true;
/*      */       } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendCreateOffScreenBuffer(Canvas3D paramCanvas3D) {
/* 3002 */     GraphicsDevice graphicsDevice = paramCanvas3D.graphicsConfiguration.getDevice();
/* 3003 */     J3dDebug.doAssert((Screen3D.deviceRendererMap.get(graphicsDevice) != null), "Screen3D.deviceRendererMap.get(gd) != null");
/*      */ 
/*      */     
/* 3006 */     synchronized (mcThreadLock) {
/*      */       
/* 3008 */       createMasterControlThread();
/* 3009 */       assert this.mcThread != null;
/*      */ 
/*      */ 
/*      */       
/* 3013 */       Renderer renderer = createRenderer(paramCanvas3D.graphicsConfiguration);
/* 3014 */       J3dMessage j3dMessage = new J3dMessage();
/* 3015 */       j3dMessage.threads = 16;
/* 3016 */       j3dMessage.type = 61;
/* 3017 */       j3dMessage.universe = null;
/* 3018 */       j3dMessage.view = null;
/* 3019 */       j3dMessage.args[0] = paramCanvas3D;
/* 3020 */       renderer.rendererStructure.addMessage(j3dMessage);
/* 3021 */       synchronized (this.requestObjList) {
/* 3022 */         setWorkForRequestRenderer();
/* 3023 */         this.pendingRequest = true;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void sendAllocateCanvasId(Canvas3D paramCanvas3D) {
/* 3030 */     synchronized (mcThreadLock) {
/*      */       
/* 3032 */       createMasterControlThread();
/* 3033 */       assert this.mcThread != null;
/*      */       
/* 3035 */       Renderer renderer = createRenderer(paramCanvas3D.graphicsConfiguration);
/* 3036 */       J3dMessage j3dMessage = new J3dMessage();
/* 3037 */       j3dMessage.threads = 16;
/* 3038 */       j3dMessage.type = 66;
/* 3039 */       j3dMessage.universe = null;
/* 3040 */       j3dMessage.view = null;
/* 3041 */       j3dMessage.args[0] = paramCanvas3D;
/* 3042 */       renderer.rendererStructure.addMessage(j3dMessage);
/* 3043 */       synchronized (this.requestObjList) {
/* 3044 */         setWorkForRequestRenderer();
/* 3045 */         this.pendingRequest = true;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void sendFreeCanvasId(Canvas3D paramCanvas3D) {
/* 3052 */     synchronized (mcThreadLock) {
/*      */       
/* 3054 */       createMasterControlThread();
/* 3055 */       assert this.mcThread != null;
/*      */       
/* 3057 */       Renderer renderer = createRenderer(paramCanvas3D.graphicsConfiguration);
/* 3058 */       J3dMessage j3dMessage = new J3dMessage();
/* 3059 */       j3dMessage.threads = 16;
/* 3060 */       j3dMessage.type = 67;
/* 3061 */       j3dMessage.universe = null;
/* 3062 */       j3dMessage.view = null;
/* 3063 */       j3dMessage.args[0] = paramCanvas3D;
/* 3064 */       renderer.rendererStructure.addMessage(j3dMessage);
/* 3065 */       synchronized (this.requestObjList) {
/* 3066 */         setWorkForRequestRenderer();
/* 3067 */         this.pendingRequest = true;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void doWork() {
/* 3077 */     runMonitor(0, null, null, null, null);
/*      */     
/* 3079 */     synchronized (this.timeLock) {
/* 3080 */       synchronized (this.requestObjList) {
/* 3081 */         if (this.pendingRequest) {
/* 3082 */           handlePendingRequest();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 3087 */     if (!this.running) {
/*      */       return;
/*      */     }
/*      */     
/* 3091 */     if (this.threadListsChanged) {
/* 3092 */       updateWorkThreads();
/*      */     }
/*      */     
/* 3095 */     synchronized (this.timeLock) {
/*      */ 
/*      */ 
/*      */       
/* 3099 */       updateTimeValues();
/*      */     } 
/*      */ 
/*      */     
/* 3103 */     View[] arrayOfView = (View[])this.views.toArray(false);
/* 3104 */     for (int i = this.views.size() - 1; i >= 0; i--) {
/* 3105 */       if ((arrayOfView[i]).active) {
/* 3106 */         arrayOfView[i].updateViewCache();
/*      */         
/* 3108 */         if (((arrayOfView[i]).viewCache.vcDirtyMask != 0 && !(arrayOfView[i]).renderBin.orientedRAs.isEmpty()) || ((arrayOfView[i]).renderBin.cachedDirtyOrientedRAs != null && !(arrayOfView[i]).renderBin.cachedDirtyOrientedRAs.isEmpty()))
/*      */         {
/*      */ 
/*      */           
/* 3112 */           (arrayOfView[i]).renderBin.updateOrientedRAs();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 3117 */     runMonitor(2, this.stateWorkThreads, this.renderWorkThreads, this.requestRenderWorkThreads, null);
/*      */ 
/*      */     
/* 3120 */     if (this.renderOnceList.size() > 0) {
/* 3121 */       clearRenderOnceList();
/*      */     }
/*      */     
/* 3124 */     manageMemory();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void handlePendingRequest() {
/* 3133 */     boolean bool = false;
/*      */     
/* 3135 */     Object[] arrayOfObject = this.requestObjList.toArray(false);
/* 3136 */     Integer[] arrayOfInteger = (Integer[])this.requestTypeList.toArray(false);
/* 3137 */     int i = this.requestObjList.size();
/*      */     byte b;
/* 3139 */     for (b = 0; b < i; b++) {
/*      */       
/* 3141 */       Integer integer = arrayOfInteger[b];
/* 3142 */       Object object = arrayOfObject[b];
/* 3143 */       if (integer == RESET_CANVAS) {
/* 3144 */         Canvas3D canvas3D = (Canvas3D)object;
/* 3145 */         if (canvas3D.screen != null && canvas3D.screen.renderer != null) {
/*      */           
/* 3147 */           this.rendererCleanupArgs[1] = object;
/* 3148 */           this.rendererCleanupArgs[2] = RESETCANVAS_CLEANUP;
/* 3149 */           runMonitor(6, null, null, null, canvas3D.screen.renderer);
/*      */           
/* 3151 */           this.rendererCleanupArgs[1] = null;
/*      */         } 
/* 3153 */         canvas3D.reset();
/* 3154 */         canvas3D.view = null;
/* 3155 */         canvas3D.computeViewCache();
/*      */       }
/* 3157 */       else if (integer == ACTIVATE_VIEW) {
/* 3158 */         viewActivate((View)object);
/*      */       }
/* 3160 */       else if (integer == DEACTIVATE_VIEW) {
/* 3161 */         viewDeactivate((View)object);
/* 3162 */       } else if (integer == REEVALUATE_CANVAS) {
/* 3163 */         evaluateAllCanvases();
/* 3164 */       } else if (integer == INPUTDEVICE_CHANGE) {
/* 3165 */         this.inputDeviceThreads.clearMirror();
/* 3166 */         this.threadListsChanged = true;
/* 3167 */       } else if (integer == START_VIEW) {
/* 3168 */         startView((View)object);
/* 3169 */       } else if (integer == STOP_VIEW) {
/* 3170 */         View view = (View)object;
/*      */         
/* 3172 */         if (++view.stopViewCount > 4) {
/* 3173 */           view.stopViewCount = -1;
/* 3174 */           stopView(view);
/*      */         } else {
/* 3176 */           this.tempViewList.add(view);
/*      */         } 
/* 3178 */       } else if (integer == UNREGISTER_VIEW) {
/* 3179 */         unregisterView((View)object);
/* 3180 */       } else if (integer == PHYSICAL_ENV_CHANGE) {
/* 3181 */         evaluatePhysicalEnv((View)object);
/* 3182 */       } else if (integer == EMPTY_UNIVERSE) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3188 */         boolean bool1 = false;
/* 3189 */         VirtualUniverse virtualUniverse = (VirtualUniverse)object;
/* 3190 */         View[] arrayOfView = (View[])this.views.toArray(false);
/* 3191 */         for (int j = this.views.size() - 1; j >= 0; j--) {
/* 3192 */           if ((arrayOfView[j]).universe == virtualUniverse) {
/* 3193 */             bool1 = true;
/*      */             break;
/*      */           } 
/*      */         } 
/* 3197 */         if (!bool1) {
/* 3198 */           destroyUniverseThreads(virtualUniverse);
/* 3199 */           this.threadListsChanged = true;
/*      */         } 
/* 3201 */       } else if (integer == START_RENDERER) {
/* 3202 */         if (object instanceof Canvas3D) {
/* 3203 */           Canvas3D canvas3D = (Canvas3D)object;
/* 3204 */           if (!canvas3D.isFatalError()) {
/* 3205 */             canvas3D.isRunningStatus = true;
/*      */           }
/*      */         } else {
/* 3208 */           ((Renderer)object).userStop = false;
/*      */         } 
/* 3210 */         this.threadListsChanged = true;
/* 3211 */       } else if (integer == STOP_RENDERER) {
/* 3212 */         if (object instanceof Canvas3D) {
/* 3213 */           ((Canvas3D)object).isRunningStatus = false;
/*      */         } else {
/* 3215 */           ((Renderer)object).userStop = true;
/*      */         } 
/* 3217 */         this.threadListsChanged = true;
/* 3218 */       } else if (integer == RENDER_ONCE) {
/* 3219 */         View view = (View)object;
/*      */ 
/*      */         
/* 3222 */         startView(view);
/* 3223 */         this.renderOnceList.add(view);
/* 3224 */         sendRunMessage(view, 128);
/* 3225 */         this.threadListsChanged = true;
/* 3226 */         bool = true;
/* 3227 */       } else if (integer == FREE_CONTEXT) {
/* 3228 */         Canvas3D canvas3D = (Canvas3D)(Object[])object[0];
/* 3229 */         if (canvas3D.screen != null && canvas3D.screen.renderer != null) {
/*      */           
/* 3231 */           this.rendererCleanupArgs[1] = object;
/* 3232 */           this.rendererCleanupArgs[2] = REMOVECTX_CLEANUP;
/* 3233 */           runMonitor(6, null, null, null, canvas3D.screen.renderer);
/*      */           
/* 3235 */           this.rendererCleanupArgs[1] = null;
/*      */         } 
/* 3237 */         bool = true;
/* 3238 */       } else if (integer == FREE_DRAWING_SURFACE) {
/* 3239 */         Pipeline.getPipeline().freeDrawingSurfaceNative(object);
/* 3240 */       } else if (integer == GETBESTCONFIG) {
/* 3241 */         GraphicsConfiguration graphicsConfiguration = (GraphicsConfiguration[])((GraphicsConfigTemplate3D)object).testCfg[0];
/*      */         
/* 3243 */         sendRenderMessage(graphicsConfiguration, object, integer);
/* 3244 */         bool = true;
/* 3245 */       } else if (integer == ISCONFIGSUPPORT) {
/* 3246 */         GraphicsConfiguration graphicsConfiguration = (GraphicsConfiguration)((GraphicsConfigTemplate3D)object).testCfg;
/*      */         
/* 3248 */         sendRenderMessage(graphicsConfiguration, object, integer);
/* 3249 */         bool = true;
/* 3250 */       } else if (integer == SET_GRAPHICSCONFIG_FEATURES || integer == SET_QUERYPROPERTIES) {
/*      */         
/* 3252 */         GraphicsConfiguration graphicsConfiguration = ((Canvas3D)object).graphicsConfiguration;
/*      */         
/* 3254 */         sendRenderMessage(graphicsConfiguration, object, integer);
/* 3255 */         bool = true;
/* 3256 */       } else if (integer == SET_VIEW) {
/* 3257 */         Canvas3D canvas3D = (Canvas3D)object;
/* 3258 */         canvas3D.view = canvas3D.pendingView;
/* 3259 */         canvas3D.computeViewCache();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 3264 */     for (b = 0; b < i; b++) {
/* 3265 */       Integer integer = arrayOfInteger[b];
/* 3266 */       if (integer == FREE_MESSAGE) {
/* 3267 */         if (arrayOfObject[b] instanceof VirtualUniverse) {
/* 3268 */           VirtualUniverse virtualUniverse = (VirtualUniverse)arrayOfObject[b];
/* 3269 */           if (!this.regUniverseList.contains(virtualUniverse)) {
/* 3270 */             emptyMessageList(virtualUniverse.behaviorStructure, null);
/* 3271 */             emptyMessageList(virtualUniverse.geometryStructure, null);
/* 3272 */             emptyMessageList(virtualUniverse.soundStructure, null);
/* 3273 */             emptyMessageList(virtualUniverse.renderingEnvironmentStructure, null);
/*      */           } 
/* 3275 */         } else if (arrayOfObject[b] instanceof View) {
/* 3276 */           View view = (View)arrayOfObject[b];
/* 3277 */           if (!this.views.contains(view)) {
/* 3278 */             emptyMessageList(view.soundScheduler, view);
/* 3279 */             emptyMessageList(view.renderBin, view);
/* 3280 */             if (view.resetUnivCount == view.universeCount) {
/* 3281 */               view.reset();
/* 3282 */               view.universe = null;
/* 3283 */               if (!this.running);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3306 */     this.requestObjList.clear();
/* 3307 */     this.requestTypeList.clear();
/*      */     
/* 3309 */     i = this.tempViewList.size();
/* 3310 */     if (i > 0) {
/* 3311 */       if (this.running) {
/* 3312 */         for (b = 0; b < i; b++) {
/* 3313 */           this.requestTypeList.add(STOP_VIEW);
/* 3314 */           this.requestObjList.add(this.tempViewList.get(b));
/*      */         } 
/* 3316 */         setWork();
/*      */       } else {
/* 3318 */         for (b = 0; b < i; b++) {
/* 3319 */           View view = (View)this.tempViewList.get(b);
/* 3320 */           view.stopViewCount = -1;
/* 3321 */           view.isRunning = false;
/*      */         } 
/*      */       } 
/* 3324 */       this.tempViewList.clear();
/* 3325 */       this.pendingRequest = true;
/*      */     } else {
/* 3327 */       this.pendingRequest = (bool || this.requestObjList.size() > 0);
/*      */     } 
/*      */ 
/*      */     
/* 3331 */     i = this.freeMessageList.size();
/* 3332 */     if (i > 0) {
/* 3333 */       for (b = 0; b < i; b++) {
/* 3334 */         this.requestTypeList.add(FREE_MESSAGE);
/* 3335 */         this.requestObjList.add(this.freeMessageList.get(b));
/*      */       } 
/* 3337 */       this.pendingRequest = true;
/* 3338 */       this.freeMessageList.clear();
/*      */     } 
/* 3340 */     if (!this.running && this.renderOnceList.size() > 0) {
/* 3341 */       clearRenderOnceList();
/*      */     }
/*      */     
/* 3344 */     if (this.pendingRequest) {
/* 3345 */       setWork();
/*      */     }
/*      */     
/* 3348 */     if (bool || this.requestRenderWorkToDo) {
/* 3349 */       this.running = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void clearRenderOnceList() {
/* 3355 */     for (int i = this.renderOnceList.size() - 1; i >= 0; i--) {
/* 3356 */       View view = (View)this.renderOnceList.get(i);
/* 3357 */       view.renderOnceFinish = true;
/*      */       
/* 3359 */       stopView(view);
/*      */     } 
/* 3361 */     this.renderOnceList.clear();
/* 3362 */     this.threadListsChanged = true; } void runMonitor(int paramInt, UnorderList paramUnorderList1, UnorderList paramUnorderList2, UnorderList paramUnorderList3, J3dThread paramJ3dThread) { View[] arrayOfView; int k;
/*      */     int j;
/*      */     int i;
/*      */     J3dThreadData[] arrayOfJ3dThreadData3;
/*      */     J3dThreadData[] arrayOfJ3dThreadData2;
/*      */     J3dThreadData[] arrayOfJ3dThreadData1;
/*      */     boolean bool;
/*      */     byte b3;
/*      */     byte b2;
/*      */     byte b1;
/* 3372 */     switch (paramInt) {
/*      */       case 2:
/* 3374 */         b1 = 0;
/* 3375 */         b2 = 0;
/* 3376 */         b3 = 0;
/*      */ 
/*      */ 
/*      */         
/* 3380 */         arrayOfJ3dThreadData1 = (J3dThreadData[])paramUnorderList2.toArray(false);
/*      */         
/* 3382 */         arrayOfJ3dThreadData2 = (J3dThreadData[])paramUnorderList1.toArray(false);
/*      */         
/* 3384 */         arrayOfJ3dThreadData3 = (J3dThreadData[])paramUnorderList3.toArray(false);
/*      */         
/* 3386 */         i = paramUnorderList2.arraySize();
/* 3387 */         j = paramUnorderList1.arraySize();
/* 3388 */         k = paramUnorderList3.arraySize();
/*      */         
/* 3390 */         bool = false;
/*      */ 
/*      */         
/* 3393 */         arrayOfView = (View[])this.views.toArray(false);
/*      */ 
/*      */ 
/*      */         
/* 3397 */         if (this.lockGeometry)
/*      */         {
/* 3399 */           for (int m = this.views.arraySize() - 1; m >= 0; m--) {
/* 3400 */             View view = arrayOfView[m];
/* 3401 */             view.renderBin.lockGeometry();
/*      */           } 
/*      */         }
/*      */         
/* 3405 */         while (!bool) {
/*      */ 
/*      */           
/* 3408 */           while (!this.renderWaiting && b2 != i) {
/* 3409 */             J3dThreadData j3dThreadData = arrayOfJ3dThreadData1[b2++];
/* 3410 */             if (!j3dThreadData.needsRun) {
/*      */               continue;
/*      */             }
/* 3413 */             if ((j3dThreadData.threadOpts & 0x8) != 0) {
/* 3414 */               View view = (View)(Object[])j3dThreadData.threadArgs[2];
/* 3415 */               view.frameNumber++;
/* 3416 */               view.startTime = J3dClock.currentTimeMillis();
/*      */             } 
/*      */ 
/*      */             
/* 3420 */             this.renderPending++;
/*      */             
/* 3422 */             if (this.cpuLimit == 1) {
/* 3423 */               j3dThreadData.thread.args = (Object[])j3dThreadData.threadArgs;
/* 3424 */               j3dThreadData.thread.doWork(this.currentTime);
/*      */             } else {
/* 3426 */               this.threadPending++;
/* 3427 */               j3dThreadData.thread.runMonitor(2, this.currentTime, (Object[])j3dThreadData.threadArgs);
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 3432 */             if ((j3dThreadData.threadOpts & 0x10) != 0) {
/* 3433 */               View view = (View)(Object[])j3dThreadData.threadArgs[3];
/* 3434 */               this.timestampUpdateList.add(view);
/*      */             } 
/*      */             
/* 3437 */             if ((j3dThreadData.threadOpts & 0x20) != 0)
/*      */             {
/* 3439 */               for (byte b = 0; b < this.views.arraySize(); b++) {
/* 3440 */                 View view = arrayOfView[b];
/* 3441 */                 view.renderBin.releaseGeometry();
/*      */               } 
/*      */             }
/*      */             
/* 3445 */             if (this.cpuLimit != 1 && (j3dThreadData.threadOpts & true) != 0)
/*      */             {
/*      */ 
/*      */               
/* 3449 */               this.renderWaiting = true;
/*      */             }
/*      */ 
/*      */             
/* 3453 */             if (this.cpuLimit != 1 && this.cpuLimit <= this.threadPending) {
/* 3454 */               this.state = 4;
/*      */               try {
/* 3456 */                 wait();
/* 3457 */               } catch (InterruptedException interruptedException) {
/* 3458 */                 System.err.println(interruptedException);
/*      */               } 
/* 3460 */               this.state = 1;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 3466 */           while (!this.stateWaiting && b1 != j) {
/* 3467 */             J3dThreadData j3dThreadData = arrayOfJ3dThreadData2[b1++];
/*      */             
/* 3469 */             if (!j3dThreadData.needsRun) {
/*      */               continue;
/*      */             }
/*      */             
/* 3473 */             this.statePending++;
/*      */             
/* 3475 */             if (this.cpuLimit == 1) {
/* 3476 */               j3dThreadData.thread.args = (Object[])j3dThreadData.threadArgs;
/* 3477 */               j3dThreadData.thread.doWork(this.currentTime);
/*      */             } else {
/* 3479 */               this.threadPending++;
/* 3480 */               j3dThreadData.thread.runMonitor(2, this.currentTime, (Object[])j3dThreadData.threadArgs);
/*      */             } 
/*      */ 
/*      */             
/* 3484 */             if (this.cpuLimit != 1 && (j3dThreadData.threadOpts & true) != 0)
/*      */             {
/* 3486 */               this.stateWaiting = true;
/*      */             }
/*      */             
/* 3489 */             if (this.cpuLimit != 1 && this.cpuLimit <= this.threadPending) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 3494 */               if (b2 == i) {
/* 3495 */                 this.state = 4;
/*      */                 try {
/* 3497 */                   wait();
/* 3498 */                 } catch (InterruptedException interruptedException) {
/* 3499 */                   System.err.println(interruptedException);
/*      */                 } 
/* 3501 */                 this.state = 1;
/*      */ 
/*      */                 
/*      */                 continue;
/*      */               } 
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */           
/* 3511 */           if (!this.renderWaiting && b2 == i) {
/*      */             
/* 3513 */             b3 = 0;
/* 3514 */             while (!this.renderWaiting && b3 != k) {
/*      */ 
/*      */ 
/*      */               
/* 3518 */               J3dThreadData j3dThreadData = arrayOfJ3dThreadData3[b3++];
/*      */ 
/*      */               
/* 3521 */               this.renderPending++;
/*      */               
/* 3523 */               if (this.cpuLimit == 1) {
/* 3524 */                 j3dThreadData.thread.args = (Object[])j3dThreadData.threadArgs;
/* 3525 */                 j3dThreadData.thread.doWork(this.currentTime);
/*      */               } else {
/* 3527 */                 this.threadPending++;
/* 3528 */                 j3dThreadData.thread.runMonitor(2, this.currentTime, (Object[])j3dThreadData.threadArgs);
/*      */               } 
/*      */ 
/*      */               
/* 3532 */               if (this.cpuLimit != 1 && (j3dThreadData.threadOpts & true) != 0)
/*      */               {
/* 3534 */                 this.renderWaiting = true;
/*      */               }
/* 3536 */               if (this.cpuLimit != 1 && this.cpuLimit <= this.threadPending) {
/* 3537 */                 this.state = 4;
/*      */                 try {
/* 3539 */                   wait();
/* 3540 */                 } catch (InterruptedException interruptedException) {
/* 3541 */                   System.err.println(interruptedException);
/*      */                 } 
/* 3543 */                 this.state = 1;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/* 3548 */           if (this.cpuLimit != 1 && ((
/* 3549 */             this.renderWaiting && b1 == j) || (this.stateWaiting && b2 == i) || (this.renderWaiting && this.stateWaiting))) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3554 */             if (!this.requestRenderWorkToDo) {
/* 3555 */               this.state = 3;
/*      */               try {
/* 3557 */                 wait();
/* 3558 */               } catch (InterruptedException interruptedException) {
/* 3559 */                 System.err.println(interruptedException);
/*      */               } 
/* 3561 */               this.state = 1;
/*      */             } 
/* 3563 */             this.requestRenderWorkToDo = false;
/*      */           } 
/*      */ 
/*      */           
/* 3567 */           if (b1 == j && b2 == i && b3 == k && this.threadPending == 0) {
/*      */ 
/*      */ 
/*      */             
/* 3571 */             for (int m = this.timestampUpdateList.size() - 1; m >= 0; 
/* 3572 */               m--) {
/* 3573 */               View view = (View)this.timestampUpdateList.get(m);
/* 3574 */               view.setFrameTimingValues();
/* 3575 */               view.universe.behaviorStructure.incElapsedFrames();
/*      */             } 
/* 3577 */             this.timestampUpdateList.clear();
/* 3578 */             updateMirrorObjects();
/* 3579 */             bool = true;
/*      */             
/* 3581 */             if (isStatsLoggable(Level.INFO))
/*      */             {
/* 3583 */               logTimes();
/*      */             }
/*      */           } 
/*      */         } 
/*      */         return;
/*      */       
/*      */       case 3:
/* 3590 */         if (this.state != 5) {
/*      */           
/* 3592 */           this.threadPending--;
/* 3593 */           assert this.threadPending >= 0 : "threadPending = " + this.threadPending;
/* 3594 */           if (paramJ3dThread.type == 16) {
/* 3595 */             View view = (View)paramJ3dThread.args[3];
/* 3596 */             if (view != null) {
/* 3597 */               view.stopTime = J3dClock.currentTimeMillis();
/*      */             }
/*      */             
/* 3600 */             if (--this.renderPending == 0) {
/* 3601 */               this.renderWaiting = false;
/*      */             }
/* 3603 */             assert this.renderPending >= 0 : "renderPending = " + this.renderPending;
/*      */           } else {
/* 3605 */             if (--this.statePending == 0) {
/* 3606 */               this.stateWaiting = false;
/*      */             }
/* 3608 */             assert this.statePending >= 0 : "statePending = " + this.statePending;
/*      */           } 
/* 3610 */           if (this.state == 4 || this.state == 3) {
/* 3611 */             notify();
/*      */           }
/*      */         } else {
/* 3614 */           notify();
/* 3615 */           this.state = 1;
/*      */         } 
/*      */         return;
/*      */       
/*      */       case 0:
/* 3620 */         if (!this.workToDo) {
/* 3621 */           this.state = 0;
/*      */ 
/*      */           
/*      */           try {
/* 3625 */             wait();
/* 3626 */           } catch (InterruptedException interruptedException) {
/* 3627 */             System.err.println(interruptedException);
/*      */           } 
/* 3629 */           this.state = 1;
/*      */         } 
/* 3631 */         this.workToDo = false;
/*      */         return;
/*      */       
/*      */       case 1:
/* 3635 */         this.workToDo = true;
/* 3636 */         if (this.state == 0) {
/* 3637 */           notify();
/*      */         }
/*      */         return;
/*      */       
/*      */       case 5:
/* 3642 */         this.requestRenderWorkToDo = true;
/* 3643 */         this.workToDo = true;
/* 3644 */         if (this.state == 4 || this.state == 3 || this.state == 0)
/*      */         {
/* 3646 */           notify();
/*      */         }
/*      */         return;
/*      */       
/*      */       case 6:
/* 3651 */         paramJ3dThread.runMonitor(2, this.currentTime, this.rendererCleanupArgs);
/*      */         
/* 3653 */         this.state = 5;
/*      */         
/* 3655 */         while (this.state != 1) {
/*      */           try {
/* 3657 */             wait();
/* 3658 */           } catch (InterruptedException interruptedException) {
/* 3659 */             System.err.println(interruptedException);
/*      */           } 
/*      */         } 
/*      */         return;
/*      */     } 
/*      */ 
/*      */     
/* 3666 */     assert false : "missing case in switch statement"; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static  {
/* 3673 */     AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run()
/*      */           {
/* 3677 */             Thread thread = Thread.currentThread();
/* 3678 */             threadPriority = thread.getPriority();
/* 3679 */             rootThreadGroup = thread.getThreadGroup(); ThreadGroup threadGroup;
/* 3680 */             while ((threadGroup = rootThreadGroup.getParent()) != null) {
/* 3681 */               rootThreadGroup = threadGroup;
/*      */             }
/* 3683 */             rootThreadGroup = new ThreadGroup(rootThreadGroup, "Java3D");
/*      */ 
/*      */             
/* 3686 */             return null;
/*      */           }
/*      */         });
/*      */ 
/*      */     
/*      */     try {
/* 3692 */       initLoggers();
/* 3693 */     } catch (RuntimeException runtimeException) {
/* 3694 */       System.err.println(runtimeException);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 3699 */     mtype = new String[] { "INSERT_NODES", "REMOVE_NODES", "RUN", "TRANSFORM_CHANGED", "UPDATE_VIEW", "STOP_THREAD", "COLORINGATTRIBUTES_CHANGED", "LINEATTRIBUTES_CHANGED", "POINTATTRIBUTES_CHANGED", "POLYGONATTRIBUTES_CHANGED", "RENDERINGATTRIBUTES_CHANGED", "TEXTUREATTRIBUTES_CHANGED", "TRANSPARENCYATTRIBUTES_CHANGED", "MATERIAL_CHANGED", "TEXCOORDGENERATION_CHANGED", "TEXTURE_CHANGED", "MORPH_CHANGED", "GEOMETRY_CHANGED", "APPEARANCE_CHANGED", "LIGHT_CHANGED", "BACKGROUND_CHANGED", "CLIP_CHANGED", "FOG_CHANGED", "BOUNDINGLEAF_CHANGED", "SHAPE3D_CHANGED", "TEXT3D_TRANSFORM_CHANGED", "TEXT3D_DATA_CHANGED", "SWITCH_CHANGED", "COND_MET", "BEHAVIOR_ENABLE", "BEHAVIOR_DISABLE", "INSERT_RENDERATOMS", "ORDERED_GROUP_INSERTED", "ORDERED_GROUP_REMOVED", "COLLISION_BOUND_CHANGED", "REGION_BOUND_CHANGED", "MODELCLIP_CHANGED", "BOUNDS_AUTO_COMPUTE_CHANGED", "SOUND_ATTRIB_CHANGED", "AURALATTRIBUTES_CHANGED", "SOUNDSCAPE_CHANGED", "ALTERNATEAPPEARANCE_CHANGED", "RENDER_OFFSCREEN", "RENDER_RETAINED", "RENDER_IMMEDIATE", "SOUND_STATE_CHANGED", "ORIENTEDSHAPE3D_CHANGED", "TEXTURE_UNIT_STATE_CHANGED", "UPDATE_VIEWPLATFORM", "BEHAVIOR_ACTIVATE", "GEOMETRYARRAY_CHANGED", "MEDIA_CONTAINER_CHANGED", "RESIZE_CANVAS", "TOGGLE_CANVAS", "IMAGE_COMPONENT_CHANGED", "SCHEDULING_INTERVAL_CHANGED", "VIEWSPECIFICGROUP_CHANGED", "VIEWSPECIFICGROUP_INIT", "VIEWSPECIFICGROUP_CLEAR", "ORDERED_GROUP_TABLE_CHANGED", "BEHAVIOR_REEVALUATE", "CREATE_OFFSCREENBUFFER", "DESTROY_CTX_AND_OFFSCREENBUFFER", "SHADER_ATTRIBUTE_CHANGED", "SHADER_ATTRIBUTE_SET_CHANGED", "SHADER_APPEARANCE_CHANGED", "ALLOCATE_CANVASID", "FREE_CANVASID" };
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
/*      */   private String dumpThreads(int paramInt) {
/* 3771 */     StringBuffer stringBuffer = new StringBuffer();
/* 3772 */     stringBuffer.append("threads:");
/*      */     
/* 3774 */     if ((paramInt & true) != 0) {
/* 3775 */       stringBuffer.append(" BEHAVIOR_SCHEDULER");
/*      */     }
/* 3777 */     if ((paramInt & 0x2) != 0) {
/* 3778 */       stringBuffer.append(" SOUND_SCHEDULER");
/*      */     }
/* 3780 */     if ((paramInt & 0x4) != 0) {
/* 3781 */       stringBuffer.append(" INPUT_DEVICE_SCHEDULER");
/*      */     }
/* 3783 */     if ((paramInt & 0x10) != 0) {
/* 3784 */       stringBuffer.append(" RENDER_THREAD");
/*      */     }
/* 3786 */     if ((paramInt & 0x40) != 0) {
/* 3787 */       stringBuffer.append(" UPDATE_GEOMETRY");
/*      */     }
/* 3789 */     if ((paramInt & 0x80) != 0) {
/* 3790 */       stringBuffer.append(" UPDATE_RENDER");
/*      */     }
/* 3792 */     if ((paramInt & 0x100) != 0) {
/* 3793 */       stringBuffer.append(" UPDATE_BEHAVIOR");
/*      */     }
/* 3795 */     if ((paramInt & 0x200) != 0) {
/* 3796 */       stringBuffer.append(" UPDATE_SOUND");
/*      */     }
/* 3798 */     if ((paramInt & 0x400) != 0) {
/* 3799 */       stringBuffer.append(" UPDATE_RENDERING_ATTRIBUTES");
/*      */     }
/* 3801 */     if ((paramInt & 0x1000) != 0) {
/* 3802 */       stringBuffer.append(" UPDATE_RENDERING_ENVIRONMENT");
/*      */     }
/* 3804 */     if ((paramInt & 0x2000) != 0) {
/* 3805 */       stringBuffer.append(" UPDATE_TRANSFORM");
/*      */     }
/*      */     
/* 3808 */     return stringBuffer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void dumpMessage(String paramString, J3dMessage paramJ3dMessage) {
/* 3814 */     StringBuffer stringBuffer = new StringBuffer();
/* 3815 */     stringBuffer.append(paramString).append(" ");
/* 3816 */     if (paramJ3dMessage.type >= 0 && paramJ3dMessage.type < mtype.length) {
/* 3817 */       stringBuffer.append(mtype[paramJ3dMessage.type]);
/*      */     } else {
/* 3819 */       stringBuffer.append("<UNKNOWN>");
/*      */     } 
/* 3821 */     stringBuffer.append("  ").append(dumpThreads(paramJ3dMessage.threads));
/* 3822 */     getCoreLogger().finest(stringBuffer.toString());
/*      */   }
/*      */ 
/*      */   
/* 3826 */   int frameCount = 0;
/* 3827 */   private int frameCountCutoff = 100;
/*      */   
/*      */   private void manageMemory() {
/* 3830 */     if (++this.frameCount > this.frameCountCutoff) {
/* 3831 */       FreeListManager.manageLists();
/* 3832 */       this.frameCount = 0;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final void threadYield() {
/*      */     try {
/* 3855 */       Thread.sleep(1L);
/*      */     }
/* 3857 */     catch (InterruptedException interruptedException) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3865 */   private int getNumberOfProcessors() { return Runtime.getRuntime().availableProcessors(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   enum TimeType
/*      */   {
/* 3883 */     TOTAL_FRAME,
/* 3884 */     RENDER,
/* 3885 */     BEHAVIOR;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 3890 */   private long[] statTimes = new long[TimeType.values().length];
/* 3891 */   private int[] statCounts = new int[TimeType.values().length];
/* 3892 */   private boolean[] statSeen = new boolean[TimeType.values().length];
/* 3893 */   private int frameCycleTick = 0;
/* 3894 */   private long frameCycleNumber = 0L;
/*      */ 
/*      */ 
/*      */   
/*      */   void recordTime(TimeType paramTimeType, long paramLong) {
/* 3899 */     int i = paramTimeType.ordinal();
/* 3900 */     this.statTimes[i] = this.statTimes[i] + paramLong;
/* 3901 */     this.statCounts[i] = this.statCounts[i] + 1;
/* 3902 */     this.statSeen[i] = true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void logTimes() {
/* 3908 */     this.frameCycleNumber++;
/* 3909 */     if (++this.frameCycleTick >= 10) {
/* 3910 */       StringBuffer stringBuffer = new StringBuffer();
/* 3911 */       stringBuffer.append("----------------------------------------------\n").append("    Frame Number = ").append(this.frameCycleNumber).append("\n");
/*      */ 
/*      */ 
/*      */       
/* 3915 */       for (byte b = 0; b < this.statTimes.length; b++) {
/* 3916 */         if (this.statSeen[b]) {
/* 3917 */           stringBuffer.append("    ");
/* 3918 */           if (this.statCounts[b] > 0) {
/* 3919 */             stringBuffer.append(TimeType.values()[b]).append(" [").append(this.statCounts[b]).append("] = ").append(this.statTimes[b] / 1000000.0D / this.statCounts[b]).append(" msec per call\n");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3925 */             this.statTimes[b] = 0L;
/* 3926 */             this.statCounts[b] = 0;
/*      */           } else {
/* 3928 */             assert this.statTimes[b] == 0L;
/* 3929 */             stringBuffer.append(TimeType.values()[b]).append(" [0] = 0.0 msec\n");
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 3934 */       getStatsLogger().info(stringBuffer.toString());
/* 3935 */       this.frameCycleTick = 0;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\MasterControl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */