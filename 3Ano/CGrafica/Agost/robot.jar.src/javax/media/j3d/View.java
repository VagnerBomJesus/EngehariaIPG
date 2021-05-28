/*      */ package javax.media.j3d;
/*      */ 
/*      */ import com.sun.j3d.utils.universe.Viewer;
/*      */ import java.awt.AWTEvent;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.Vector;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point3f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class View
/*      */ {
/*      */   public static final int NOMINAL_HEAD = 0;
/*      */   public static final int NOMINAL_FEET = 1;
/*      */   public static final int NOMINAL_SCREEN = 2;
/*      */   public static final int SCALE_SCREEN_SIZE = 0;
/*      */   public static final int SCALE_EXPLICIT = 1;
/*      */   public static final int VIRTUAL_SCREEN = 0;
/*      */   public static final int PHYSICAL_SCREEN = 1;
/*      */   public static final int VIRTUAL_EYE = 2;
/*      */   public static final int PHYSICAL_EYE = 3;
/*      */   public static final int VIRTUAL_WORLD = 0;
/*      */   public static final int PHYSICAL_WORLD = 1;
/*      */   public static final int RELATIVE_TO_SCREEN = 0;
/*      */   public static final int RELATIVE_TO_WINDOW = 1;
/*      */   public static final int RELATIVE_TO_FIELD_OF_VIEW = 2;
/*      */   public static final int RELATIVE_TO_COEXISTENCE = 3;
/*      */   public static final int LEFT_EYE_VIEW = 0;
/*      */   public static final int RIGHT_EYE_VIEW = 1;
/*      */   public static final int CYCLOPEAN_EYE_VIEW = 2;
/*      */   public static final int SCREEN_VIEW = 0;
/*      */   public static final int HMD_VIEW = 1;
/*      */   public static final int PARALLEL_PROJECTION = 0;
/*      */   public static final int PERSPECTIVE_PROJECTION = 1;
/*      */   public static final int VISIBILITY_DRAW_VISIBLE = 0;
/*      */   public static final int VISIBILITY_DRAW_INVISIBLE = 1;
/*      */   public static final int VISIBILITY_DRAW_ALL = 2;
/*      */   public static final int TRANSPARENCY_SORT_NONE = 0;
/*      */   public static final int TRANSPARENCY_SORT_GEOMETRY = 1;
/*      */   private Vector canvases;
/*      */   VirtualUniverse universe;
/*      */   RenderBin renderBin;
/*      */   SoundScheduler soundScheduler;
/*      */   SoundRenderer soundRenderer;
/*      */   static final int NUMBER_FRAME_START_TIMES = 10;
/*      */   long[] frameStartTimes;
/*      */   long[] frameNumbers;
/*      */   int currentFrameIndex;
/*      */   long currentFrameStartTime;
/*      */   long currentFrameDuration;
/*      */   long currentFrameNumber;
/*      */   long frameNumber;
/*      */   long startTime;
/*      */   long stopTime;
/*      */   Viewer viewer;
/*      */   boolean firstTime;
/*      */   long minFrameCycleTime;
/*      */   boolean stopBehavior;
/*      */   ViewCache viewCache;
/*      */   static final int COMPATIBILITY_MODE_DIRTY = 1;
/*      */   static final int SCREEN_SCALE_POLICY_DIRTY = 2;
/*      */   static final int SCREEN_SCALE_DIRTY = 4;
/*      */   static final int WINDOW_RESIZE_POLICY_DIRTY = 8;
/*      */   static final int VIEW_POLICY_DIRTY = 16;
/*      */   static final int CLIP_DIRTY = 32;
/*      */   static final int PROJECTION_POLICY_DIRTY = 64;
/*      */   static final int WINDOW_MOVEMENT_POLICY_DIRTY = 128;
/*      */   static final int WINDOW_EYE_POINT_POLICY_DIRTY = 256;
/*      */   static final int MONOSCOPIC_VIEW_POLICY_DIRTY = 512;
/*      */   static final int FIELD_OF_VIEW_DIRTY = 1024;
/*      */   static final int TRACKING_ENABLE_DIRTY = 2048;
/*      */   static final int USER_HEAD_TO_VWORLD_ENABLE_DIRTY = 4096;
/*      */   static final int COEXISTENCE_CENTERING_ENABLE_DIRTY = 8192;
/*      */   static final int LEFT_MANUAL_EYE_IN_COEXISTENCE_DIRTY = 16384;
/*      */   static final int RIGHT_MANUAL_EYE_IN_COEXISTENCE_DIRTY = 32768;
/*      */   static final int VISIBILITY_POLICY_DIRTY = 65536;
/*      */   static final int VPR_VIEW_ATTACH_POLICY_DIRTY = 65536;
/*      */   static final int VPR_VIEWPLATFORM_DIRTY = 131072;
/*      */   static final int PE_COE_TO_TRACKER_BASE_DIRTY = 1048576;
/*      */   static final int PE_TRACKING_AVAILABLE_DIRTY = 2097152;
/*      */   static final int PE_COE_CENTER_IN_PWORLD_POLICY_DIRTY = 4194304;
/*      */   static final int PB_EYE_POSITION_DIRTY = 16777216;
/*      */   static final int PB_EAR_POSITION_DIRTY = 33554432;
/*      */   static final int PB_NOMINAL_EYE_HEIGHT_FROM_GROUND_DIRTY = 67108864;
/*      */   static final int PB_NOMINAL_EYE_OFFSET_FROM_NOMINAL_SCREEN_DIRTY = 134217728;
/*      */   int vDirtyMask;
/*      */   PhysicalBody physicalBody;
/*      */   PhysicalEnvironment physicalEnvironment;
/*      */   boolean compatibilityModeEnable;
/*      */   boolean coexistenceCenteringEnable;
/*      */   Point3d leftManualEyeInCoexistence;
/*      */   Point3d rightManualEyeInCoexistence;
/*      */   int viewPolicy;
/*      */   int projectionPolicy;
/*      */   double fieldOfView;
/*      */   double frontClipDistance;
/*      */   double backClipDistance;
/*      */   int screenScalePolicy;
/*      */   double screenScale;
/*      */   int windowResizePolicy;
/*      */   int windowMovementPolicy;
/*      */   int windowEyepointPolicy;
/*      */   int monoscopicViewPolicy;
/*      */   int frontClipPolicy;
/*      */   int backClipPolicy;
/*      */   int visibilityPolicy;
/*      */   int transparencySortingPolicy;
/*      */   boolean trackingEnable;
/*      */   boolean userHeadToVworldEnable;
/*      */   private ViewPlatform viewPlatform;
/*      */   Transform3D compatVpcToEc;
/*      */   Transform3D compatLeftProjection;
/*      */   Transform3D compatRightProjection;
/*      */   Integer viewId;
/*      */   int viewIndex;
/*      */   boolean primaryView;
/*      */   boolean active;
/*      */   boolean activeStatus;
/*      */   boolean inCanvasCallback;
/*      */   boolean depthBufferFreezeTransparent;
/*      */   boolean sceneAntialiasingEnable;
/*      */   boolean localEyeLightingEnable;
/*      */   private ArrayList screenList;
/*      */   private ArrayList canvasList;
/*      */   private Canvas3D[][] cachedCanvasList;
/*      */   private Canvas3D[] cachedCanvases;
/*      */   private Screen3D[] cachedScreens;
/*      */   private int longestScreenList;
/*      */   private boolean canvasesDirty;
/*      */   private Object startStopViewLock;
/*      */   private Object evaluateLock;
/*      */   int stopViewCount;
/*      */   boolean isMinCycleTimeAchieve;
/*      */   long sleepTime;
/*      */   boolean renderBinReady;
/*      */   long universeCount;
/*      */   long resetUnivCount;
/*      */   static final int TRANSP_SORT_POLICY_CHANGED = 1;
/*      */   static final int OTHER_ATTRS_CHANGED = 2;
/*      */   
/*      */   public View() {
/*  684 */     this.canvases = new Vector(3);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  689 */     this.universe = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  694 */     this.renderBin = null;
/*      */ 
/*      */     
/*  697 */     this.soundScheduler = null;
/*      */ 
/*      */     
/*  700 */     this.soundRenderer = new SoundRenderer();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  708 */     this.frameStartTimes = new long[10];
/*  709 */     this.frameNumbers = new long[10];
/*  710 */     this.currentFrameIndex = 0;
/*      */ 
/*      */     
/*  713 */     this.currentFrameStartTime = 0L;
/*  714 */     this.currentFrameDuration = 0L;
/*  715 */     this.currentFrameNumber = 0L;
/*      */ 
/*      */     
/*  718 */     this.frameNumber = 0L;
/*  719 */     this.startTime = 0L;
/*  720 */     this.stopTime = 0L;
/*      */ 
/*      */     
/*  723 */     this.viewer = null;
/*  724 */     this.firstTime = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  737 */     this.viewCache = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  797 */     this.vDirtyMask = 131071;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  827 */     this.compatibilityModeEnable = false;
/*      */ 
/*      */     
/*  830 */     this.coexistenceCenteringEnable = true;
/*      */     
/*  832 */     this.leftManualEyeInCoexistence = new Point3d();
/*  833 */     this.rightManualEyeInCoexistence = new Point3d();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  839 */     this.viewPolicy = 0;
/*      */ 
/*      */     
/*  842 */     this.projectionPolicy = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  847 */     this.fieldOfView = 0.7853981633974483D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  854 */     this.frontClipDistance = 0.1D;
/*  855 */     this.backClipDistance = 10.0D;
/*      */ 
/*      */     
/*  858 */     this.screenScalePolicy = 0;
/*      */ 
/*      */ 
/*      */     
/*  862 */     this.screenScale = 1.0D;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  868 */     this.windowResizePolicy = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  874 */     this.windowMovementPolicy = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  882 */     this.windowEyepointPolicy = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  888 */     this.monoscopicViewPolicy = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  895 */     this.frontClipPolicy = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  900 */     this.backClipPolicy = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  905 */     this.visibilityPolicy = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  910 */     this.transparencySortingPolicy = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  915 */     this.trackingEnable = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  921 */     this.userHeadToVworldEnable = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  926 */     this.viewPlatform = null;
/*      */ 
/*      */     
/*  929 */     this.compatVpcToEc = new Transform3D();
/*      */ 
/*      */     
/*  932 */     this.compatLeftProjection = new Transform3D();
/*  933 */     this.compatRightProjection = new Transform3D();
/*      */ 
/*      */     
/*  936 */     this.viewId = null;
/*  937 */     this.viewIndex = -1;
/*      */ 
/*      */     
/*  940 */     this.primaryView = false;
/*      */ 
/*      */ 
/*      */     
/*  944 */     this.active = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  952 */     this.activeStatus = false;
/*      */ 
/*      */ 
/*      */     
/*  956 */     this.isRunning = true;
/*      */ 
/*      */     
/*  959 */     this.inCanvasCallback = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  964 */     this.depthBufferFreezeTransparent = true;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  969 */     this.sceneAntialiasingEnable = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  974 */     this.localEyeLightingEnable = false;
/*      */ 
/*      */ 
/*      */     
/*  978 */     this.screenList = new ArrayList();
/*      */ 
/*      */     
/*  981 */     this.canvasList = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  986 */     this.longestScreenList = 0;
/*  987 */     this.canvasesDirty = true;
/*      */ 
/*      */     
/*  990 */     this.renderOnceFinish = true;
/*      */ 
/*      */     
/*  993 */     this.startStopViewLock = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  998 */     this.evaluateLock = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1004 */     this.stopViewCount = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1009 */     this.isMinCycleTimeAchieve = true;
/*      */ 
/*      */     
/* 1012 */     this.sleepTime = 0L;
/*      */ 
/*      */ 
/*      */     
/* 1016 */     this.inRenderThreadData = false;
/*      */ 
/*      */ 
/*      */     
/* 1020 */     this.renderBinReady = false;
/*      */ 
/*      */     
/* 1023 */     this.universeCount = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1029 */     this.resetUnivCount = 0L;
/*      */ 
/*      */ 
/*      */     
/* 1033 */     this.doneUnregister = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1079 */     this.viewCache = new ViewCache(this);
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
/*      */   public void setViewPolicy(int paramInt) {
/* 1105 */     if (paramInt != 1 && paramInt != 0)
/*      */     {
/*      */       
/* 1108 */       throw new IllegalArgumentException(J3dI18N.getString("View0"));
/*      */     }
/* 1110 */     if (paramInt == 1)
/*      */     {
/*      */ 
/*      */       
/* 1114 */       synchronized (this.canvasList) {
/* 1115 */         for (int i = this.canvases.size() - 1; i >= 0; i--) {
/* 1116 */           Canvas3D canvas3D = (Canvas3D)this.canvases.elementAt(i);
/*      */           
/* 1118 */           if (canvas3D.monoscopicViewPolicy == 2 && !canvas3D.useStereo)
/*      */           {
/* 1120 */             throw new IllegalStateException(J3dI18N.getString("View31"));
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 1126 */     synchronized (this) {
/* 1127 */       this.viewPolicy = paramInt;
/* 1128 */       this.vDirtyMask |= 0x10;
/*      */     } 
/* 1130 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1138 */   public int getViewPolicy() { return this.viewPolicy; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProjectionPolicy(int paramInt) {
/* 1155 */     if (paramInt != 1 && paramInt != 0)
/*      */     {
/*      */       
/* 1158 */       throw new IllegalArgumentException(J3dI18N.getString("View1"));
/*      */     }
/* 1160 */     synchronized (this) {
/* 1161 */       this.projectionPolicy = paramInt;
/* 1162 */       this.vDirtyMask |= 0x40;
/*      */     } 
/*      */     
/* 1165 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1173 */   public int getProjectionPolicy() { return this.projectionPolicy; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setScreenScalePolicy(int paramInt) {
/* 1190 */     synchronized (this) {
/* 1191 */       this.screenScalePolicy = paramInt;
/* 1192 */       this.vDirtyMask |= 0x2;
/*      */     } 
/*      */     
/* 1195 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1204 */   public int getScreenScalePolicy() { return this.screenScalePolicy; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWindowResizePolicy(int paramInt) {
/* 1225 */     synchronized (this) {
/* 1226 */       this.windowResizePolicy = paramInt;
/* 1227 */       this.vDirtyMask |= 0x8;
/*      */     } 
/* 1229 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1238 */   public int getWindowResizePolicy() { return this.windowResizePolicy; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWindowMovementPolicy(int paramInt) {
/* 1258 */     synchronized (this) {
/* 1259 */       this.windowMovementPolicy = paramInt;
/* 1260 */       this.vDirtyMask |= 0x80;
/*      */     } 
/* 1262 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1271 */   public int getWindowMovementPolicy() { return this.windowMovementPolicy; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWindowEyepointPolicy(int paramInt) {
/* 1310 */     synchronized (this) {
/* 1311 */       this.windowEyepointPolicy = paramInt;
/* 1312 */       this.vDirtyMask |= 0x100;
/*      */     } 
/*      */     
/* 1315 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1325 */   public int getWindowEyepointPolicy() { return this.windowEyepointPolicy; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMonoscopicViewPolicy(int paramInt) {
/* 1333 */     synchronized (this) {
/* 1334 */       this.monoscopicViewPolicy = paramInt;
/* 1335 */       this.vDirtyMask |= 0x200;
/*      */     } 
/* 1337 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1345 */   public int getMonoscopicViewPolicy() { return this.monoscopicViewPolicy; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCoexistenceCenteringEnable(boolean paramBoolean) {
/* 1370 */     synchronized (this) {
/* 1371 */       this.coexistenceCenteringEnable = paramBoolean;
/* 1372 */       this.vDirtyMask |= 0x2000;
/*      */     } 
/* 1374 */     repaint();
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
/* 1385 */   public boolean getCoexistenceCenteringEnable() { return this.coexistenceCenteringEnable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCompatibilityModeEnable(boolean paramBoolean) {
/* 1394 */     synchronized (this) {
/* 1395 */       this.compatibilityModeEnable = paramBoolean;
/* 1396 */       this.vDirtyMask |= 0x1;
/*      */     } 
/* 1398 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1406 */   public boolean getCompatibilityModeEnable() { return this.compatibilityModeEnable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLeftProjection(Transform3D paramTransform3D) {
/* 1420 */     if (!this.compatibilityModeEnable) {
/* 1421 */       throw new RestrictedAccessException(J3dI18N.getString("View2"));
/*      */     }
/*      */     
/* 1424 */     synchronized (this) {
/* 1425 */       this.compatLeftProjection.setWithLock(paramTransform3D);
/* 1426 */       this.vDirtyMask |= 0x1;
/*      */     } 
/* 1428 */     repaint();
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
/*      */   public void setRightProjection(Transform3D paramTransform3D) {
/* 1442 */     if (!this.compatibilityModeEnable) {
/* 1443 */       throw new RestrictedAccessException(J3dI18N.getString("View2"));
/*      */     }
/*      */     
/* 1446 */     synchronized (this) {
/* 1447 */       this.compatRightProjection.setWithLock(paramTransform3D);
/* 1448 */       this.vDirtyMask |= 0x1;
/*      */     } 
/*      */     
/* 1451 */     repaint();
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
/*      */   public void getLeftProjection(Transform3D paramTransform3D) {
/* 1463 */     if (!this.compatibilityModeEnable) {
/* 1464 */       throw new RestrictedAccessException(J3dI18N.getString("View4"));
/*      */     }
/*      */     
/* 1467 */     paramTransform3D.set(this.compatLeftProjection);
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
/*      */   public void getRightProjection(Transform3D paramTransform3D) {
/* 1479 */     if (!this.compatibilityModeEnable) {
/* 1480 */       throw new RestrictedAccessException(J3dI18N.getString("View4"));
/*      */     }
/*      */     
/* 1483 */     paramTransform3D.set(this.compatRightProjection);
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
/*      */   public void setVpcToEc(Transform3D paramTransform3D) {
/* 1496 */     if (!this.compatibilityModeEnable) {
/* 1497 */       throw new RestrictedAccessException(J3dI18N.getString("View6"));
/*      */     }
/*      */     
/* 1500 */     if (!paramTransform3D.isAffine()) {
/* 1501 */       throw new BadTransformException(J3dI18N.getString("View7"));
/*      */     }
/*      */     
/* 1504 */     synchronized (this) {
/* 1505 */       this.compatVpcToEc.setWithLock(paramTransform3D);
/* 1506 */       this.vDirtyMask |= 0x1;
/*      */     } 
/*      */     
/* 1509 */     repaint();
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
/*      */   public void getVpcToEc(Transform3D paramTransform3D) {
/* 1521 */     if (!this.compatibilityModeEnable) {
/* 1522 */       throw new RestrictedAccessException(J3dI18N.getString("View8"));
/*      */     }
/*      */     
/* 1525 */     paramTransform3D.set(this.compatVpcToEc);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPhysicalBody(PhysicalBody paramPhysicalBody) {
/* 1536 */     synchronized (this.canvasList) {
/* 1537 */       if (this.activeStatus) {
/* 1538 */         if (this.physicalBody != null) {
/* 1539 */           this.physicalBody.removeUser(this);
/*      */         }
/* 1541 */         paramPhysicalBody.addUser(this);
/*      */       } 
/*      */     } 
/* 1544 */     this.physicalBody = paramPhysicalBody;
/* 1545 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1553 */   public PhysicalBody getPhysicalBody() { return this.physicalBody; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPhysicalEnvironment(PhysicalEnvironment paramPhysicalEnvironment) {
/* 1562 */     synchronized (this.canvasList) {
/* 1563 */       if (this.activeStatus) {
/* 1564 */         if (this.physicalEnvironment != null) {
/* 1565 */           this.physicalEnvironment.removeUser(this);
/*      */         }
/* 1567 */         paramPhysicalEnvironment.addUser(this);
/*      */       } 
/*      */     } 
/* 1570 */     this.physicalEnvironment = paramPhysicalEnvironment;
/*      */ 
/*      */     
/* 1573 */     if (this.viewPlatform != null && this.viewPlatform.isLive()) {
/* 1574 */       VirtualUniverse.mc.postRequest(MasterControl.PHYSICAL_ENV_CHANGE, this);
/*      */     }
/* 1576 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1584 */   public PhysicalEnvironment getPhysicalEnvironment() { return this.physicalEnvironment; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setScreenScale(double paramDouble) {
/* 1594 */     synchronized (this) {
/* 1595 */       this.screenScale = paramDouble;
/* 1596 */       this.vDirtyMask |= 0x4;
/*      */     } 
/* 1598 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1606 */   public double getScreenScale() { return this.screenScale; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFieldOfView(double paramDouble) {
/* 1616 */     synchronized (this) {
/* 1617 */       this.fieldOfView = paramDouble;
/* 1618 */       this.vDirtyMask |= 0x400;
/*      */     } 
/* 1620 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1629 */   public double getFieldOfView() { return this.fieldOfView; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLeftManualEyeInCoexistence(Point3d paramPoint3d) {
/* 1646 */     synchronized (this) {
/* 1647 */       this.leftManualEyeInCoexistence.set(paramPoint3d);
/* 1648 */       this.vDirtyMask |= 0x4000;
/*      */     } 
/* 1650 */     repaint();
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
/*      */   public void setRightManualEyeInCoexistence(Point3d paramPoint3d) {
/* 1666 */     synchronized (this) {
/* 1667 */       this.rightManualEyeInCoexistence.set(paramPoint3d);
/* 1668 */       this.vDirtyMask |= 0x8000;
/*      */     } 
/* 1670 */     repaint();
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
/* 1682 */   public void getLeftManualEyeInCoexistence(Point3d paramPoint3d) { paramPoint3d.set(this.leftManualEyeInCoexistence); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1694 */   public void getRightManualEyeInCoexistence(Point3d paramPoint3d) { paramPoint3d.set(this.rightManualEyeInCoexistence); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFrontClipDistance(double paramDouble) {
/* 1733 */     synchronized (this) {
/* 1734 */       this.frontClipDistance = paramDouble;
/* 1735 */       this.vDirtyMask |= 0x20;
/*      */     } 
/* 1737 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1745 */   public double getFrontClipDistance() { return this.frontClipDistance; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBackClipDistance(double paramDouble) {
/* 1768 */     synchronized (this) {
/* 1769 */       this.backClipDistance = paramDouble;
/* 1770 */       this.vDirtyMask |= 0x20;
/*      */     } 
/* 1772 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1780 */   public double getBackClipDistance() { return this.backClipDistance; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getUserHeadToVworld(Transform3D paramTransform3D) {
/* 1790 */     if (this.userHeadToVworldEnable) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1795 */       Canvas3D canvas3D = (Canvas3D)this.canvases.firstElement();
/* 1796 */       synchronized (canvas3D.canvasViewCache) {
/* 1797 */         paramTransform3D.set(canvas3D.canvasViewCache.getHeadToVworld());
/*      */       } 
/*      */     } else {
/* 1800 */       throw new RestrictedAccessException(J3dI18N.getString("View9"));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFrontClipPolicy(int paramInt) {
/* 1827 */     synchronized (this) {
/* 1828 */       this.frontClipPolicy = paramInt;
/* 1829 */       this.vDirtyMask |= 0x20;
/*      */     } 
/* 1831 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1840 */   public int getFrontClipPolicy() { return this.frontClipPolicy; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBackClipPolicy(int paramInt) {
/* 1866 */     synchronized (this) {
/* 1867 */       this.backClipPolicy = paramInt;
/* 1868 */       this.vDirtyMask |= 0x20;
/*      */     } 
/* 1870 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1879 */   public int getBackClipPolicy() { return this.backClipPolicy; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVisibilityPolicy(int paramInt) {
/* 1907 */     synchronized (this) {
/* 1908 */       this.visibilityPolicy = paramInt;
/* 1909 */       this.vDirtyMask |= 0x10000;
/*      */     } 
/*      */     
/* 1912 */     if (this.activeStatus && this.isRunning) {
/*      */       
/* 1914 */       J3dMessage j3dMessage = new J3dMessage();
/* 1915 */       j3dMessage.universe = this.universe;
/* 1916 */       j3dMessage.view = this;
/* 1917 */       j3dMessage.type = 4;
/* 1918 */       j3dMessage.threads = 128;
/* 1919 */       j3dMessage.args[0] = this;
/* 1920 */       synchronized (((ViewPlatformRetained)this.viewPlatform.retained).sphere) {
/* 1921 */         j3dMessage.args[1] = new Float(((ViewPlatformRetained)this.viewPlatform.retained).sphere.radius);
/*      */       } 
/*      */       
/* 1924 */       j3dMessage.args[2] = new Integer(2);
/* 1925 */       j3dMessage.args[3] = new Integer(this.transparencySortingPolicy);
/* 1926 */       VirtualUniverse.mc.processMessage(j3dMessage);
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
/* 1939 */   public int getVisibilityPolicy() { return this.visibilityPolicy; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTransparencySortingPolicy(int paramInt) {
/* 1968 */     if (paramInt == this.transparencySortingPolicy) {
/*      */       return;
/*      */     }
/*      */     
/* 1972 */     this.transparencySortingPolicy = paramInt;
/* 1973 */     if (this.activeStatus && this.isRunning) {
/*      */       
/* 1975 */       J3dMessage j3dMessage = new J3dMessage();
/* 1976 */       j3dMessage.universe = this.universe;
/* 1977 */       j3dMessage.view = this;
/* 1978 */       j3dMessage.type = 4;
/* 1979 */       j3dMessage.threads = 128;
/* 1980 */       j3dMessage.args[0] = this;
/* 1981 */       j3dMessage.args[1] = null;
/* 1982 */       j3dMessage.args[2] = new Integer(1);
/* 1983 */       j3dMessage.args[3] = new Integer(paramInt);
/* 1984 */       VirtualUniverse.mc.processMessage(j3dMessage);
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
/* 1996 */   public int getTransparencySortingPolicy() { return this.transparencySortingPolicy; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTrackingEnable(boolean paramBoolean) {
/* 2006 */     synchronized (this) {
/* 2007 */       this.trackingEnable = paramBoolean;
/* 2008 */       this.vDirtyMask |= 0x800;
/*      */     } 
/*      */     
/* 2011 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2020 */   public boolean getTrackingEnable() { return this.trackingEnable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUserHeadToVworldEnable(boolean paramBoolean) {
/* 2030 */     synchronized (this) {
/* 2031 */       this.userHeadToVworldEnable = paramBoolean;
/* 2032 */       this.vDirtyMask |= 0x1000;
/*      */     } 
/* 2034 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2043 */   public boolean getUserHeadToVworldEnable() { return this.userHeadToVworldEnable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getSensorToVworld(Sensor paramSensor, Transform3D paramTransform3D) {
/* 2057 */     Canvas3D canvas3D = (Canvas3D)this.canvases.firstElement();
/* 2058 */     Transform3D transform3D = new Transform3D();
/* 2059 */     synchronized (canvas3D.canvasViewCache) {
/* 2060 */       paramTransform3D.set(canvas3D.canvasViewCache.getVworldToTrackerBase());
/*      */     } 
/* 2062 */     paramTransform3D.invert();
/* 2063 */     paramSensor.getRead(transform3D);
/* 2064 */     paramTransform3D.mul(transform3D);
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
/*      */   public void getSensorHotspotInVworld(Sensor paramSensor, Point3f paramPoint3f) {
/* 2078 */     Canvas3D canvas3D = (Canvas3D)this.canvases.firstElement();
/* 2079 */     Transform3D transform3D = new Transform3D();
/* 2080 */     Point3d point3d = new Point3d();
/*      */     
/* 2082 */     getSensorToVworld(paramSensor, transform3D);
/* 2083 */     paramSensor.getHotspot(point3d);
/* 2084 */     paramPoint3f.set(point3d);
/* 2085 */     transform3D.transform(paramPoint3f);
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
/*      */   public void getSensorHotspotInVworld(Sensor paramSensor, Point3d paramPoint3d) {
/* 2099 */     Canvas3D canvas3D = (Canvas3D)this.canvases.firstElement();
/* 2100 */     Transform3D transform3D = new Transform3D();
/*      */     
/* 2102 */     getSensorToVworld(paramSensor, transform3D);
/* 2103 */     paramSensor.getHotspot(paramPoint3d);
/* 2104 */     transform3D.transform(paramPoint3d);
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
/*      */   public void setCanvas3D(Canvas3D paramCanvas3D, int paramInt) {
/*      */     Canvas3D canvas3D;
/* 2119 */     if (this.viewPolicy == 1 && paramCanvas3D.monoscopicViewPolicy == 2 && !paramCanvas3D.useStereo)
/*      */     {
/*      */ 
/*      */       
/* 2123 */       throw new IllegalStateException(J3dI18N.getString("View31"));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2129 */     synchronized (this.canvasList) {
/* 2130 */       if (paramCanvas3D.getView() != null)
/* 2131 */         throw new IllegalSharingException(J3dI18N.getString("View10")); 
/* 2132 */       canvas3D = (Canvas3D)this.canvases.elementAt(paramInt);
/* 2133 */       this.canvases.setElementAt(paramCanvas3D, paramInt);
/* 2134 */       removeFromCanvasList(canvas3D);
/* 2135 */       addToCanvasList(paramCanvas3D);
/* 2136 */       this.canvasesDirty = true;
/*      */     } 
/*      */     
/* 2139 */     paramCanvas3D.setView(this);
/* 2140 */     canvas3D.setView(null);
/*      */     
/* 2142 */     if (paramCanvas3D.added) {
/* 2143 */       evaluateActive();
/*      */     }
/* 2145 */     if (canvas3D.added) {
/* 2146 */       evaluateActive();
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
/* 2157 */   public Canvas3D getCanvas3D(int paramInt) { return (Canvas3D)this.canvases.elementAt(paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2165 */   public Enumeration getAllCanvas3Ds() { return this.canvases.elements(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2175 */   public int numCanvas3Ds() { return this.canvases.size(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addCanvas3D(Canvas3D paramCanvas3D) {
/* 2189 */     if (this.viewPolicy == 1 && paramCanvas3D.monoscopicViewPolicy == 2 && !paramCanvas3D.useStereo)
/*      */     {
/*      */       
/* 2192 */       throw new IllegalStateException(J3dI18N.getString("View31"));
/*      */     }
/*      */ 
/*      */     
/* 2196 */     synchronized (this.canvasList) {
/* 2197 */       if (paramCanvas3D.getView() != null)
/* 2198 */         throw new IllegalSharingException(J3dI18N.getString("View10")); 
/* 2199 */       this.canvases.addElement(paramCanvas3D);
/* 2200 */       addToCanvasList(paramCanvas3D);
/* 2201 */       this.canvasesDirty = true;
/*      */     } 
/*      */     
/* 2204 */     paramCanvas3D.setView(this);
/*      */     
/* 2206 */     if (paramCanvas3D.added) {
/* 2207 */       if ((paramCanvas3D.visible || paramCanvas3D.offScreen) && paramCanvas3D.firstPaintCalled)
/*      */       {
/* 2209 */         paramCanvas3D.active = true;
/*      */       }
/* 2211 */       evaluateActive();
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
/*      */   public void insertCanvas3D(Canvas3D paramCanvas3D, int paramInt) {
/* 2227 */     if (this.viewPolicy == 1 && paramCanvas3D.monoscopicViewPolicy == 2 && !paramCanvas3D.useStereo)
/*      */     {
/*      */       
/* 2230 */       throw new IllegalStateException(J3dI18N.getString("View31"));
/*      */     }
/*      */ 
/*      */     
/* 2234 */     synchronized (this.canvasList) {
/* 2235 */       if (paramCanvas3D.getView() != null)
/* 2236 */         throw new IllegalSharingException(J3dI18N.getString("View10")); 
/* 2237 */       this.canvases.insertElementAt(paramCanvas3D, paramInt);
/* 2238 */       addToCanvasList(paramCanvas3D);
/* 2239 */       this.canvasesDirty = true;
/*      */     } 
/*      */     
/* 2242 */     paramCanvas3D.setView(this);
/*      */     
/* 2244 */     if (paramCanvas3D.added) {
/* 2245 */       if ((paramCanvas3D.visible || paramCanvas3D.offScreen) && paramCanvas3D.firstPaintCalled)
/*      */       {
/* 2247 */         paramCanvas3D.active = true;
/*      */       }
/* 2249 */       evaluateActive();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeCanvas3D(int paramInt) {
/*      */     Canvas3D canvas3D;
/* 2261 */     if (paramInt == -1) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 2266 */     synchronized (this.canvasList) {
/* 2267 */       canvas3D = (Canvas3D)this.canvases.elementAt(paramInt);
/*      */       
/* 2269 */       this.canvases.removeElementAt(paramInt);
/* 2270 */       removeFromCanvasList(canvas3D);
/* 2271 */       this.canvasesDirty = true;
/*      */     } 
/*      */ 
/*      */     
/* 2275 */     VirtualUniverse.mc.postRequest(MasterControl.RESET_CANVAS, canvas3D);
/*      */     
/* 2277 */     canvas3D.pendingView = null;
/*      */     
/* 2279 */     computeCanvasesCached();
/*      */     
/* 2281 */     if (canvas3D.added) {
/* 2282 */       canvas3D.active = false;
/* 2283 */       evaluateActive();
/*      */     } 
/* 2285 */     if (this.universe != null) {
/* 2286 */       this.universe.waitForMC();
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
/* 2302 */   public int indexOfCanvas3D(Canvas3D paramCanvas3D) { return this.canvases.indexOf(paramCanvas3D); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2314 */   public void removeCanvas3D(Canvas3D paramCanvas3D) { removeCanvas3D(this.canvases.indexOf(paramCanvas3D)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeAllCanvas3Ds() {
/* 2324 */     LinkedList linkedList = new LinkedList();
/*      */     
/* 2326 */     synchronized (this.canvasList) {
/* 2327 */       int i = this.canvases.size();
/*      */ 
/*      */       
/* 2330 */       for (int j = i - 1; j >= 0; j--) {
/*      */ 
/*      */         
/* 2333 */         Canvas3D canvas3D = (Canvas3D)this.canvases.elementAt(j);
/*      */ 
/*      */         
/* 2336 */         linkedList.add(canvas3D);
/*      */         
/* 2338 */         this.canvases.removeElementAt(j);
/* 2339 */         removeFromCanvasList(canvas3D);
/* 2340 */         this.canvasesDirty = true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2347 */     Iterator iterator = linkedList.iterator();
/* 2348 */     while (iterator.hasNext()) {
/* 2349 */       Canvas3D canvas3D = (Canvas3D)iterator.next();
/*      */ 
/*      */       
/* 2352 */       VirtualUniverse.mc.postRequest(MasterControl.RESET_CANVAS, canvas3D);
/*      */       
/* 2354 */       canvas3D.pendingView = null;
/*      */       
/* 2356 */       if (canvas3D.added) {
/* 2357 */         canvas3D.active = false;
/*      */       }
/*      */     } 
/*      */     
/* 2361 */     computeCanvasesCached();
/*      */     
/* 2363 */     evaluateActive();
/*      */     
/* 2365 */     if (this.universe != null) {
/* 2366 */       this.universe.waitForMC();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addToCanvasList(Canvas3D paramCanvas3D) {
/* 2375 */     for (int i = this.screenList.size() - 1; i >= 0; i--) {
/* 2376 */       if ((Screen3D)this.screenList.get(i) == paramCanvas3D.screen) {
/*      */         
/* 2378 */         ((ArrayList)this.canvasList.get(i)).add(paramCanvas3D);
/* 2379 */         this.canvasesDirty = true;
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     
/* 2385 */     this.screenList.add(paramCanvas3D.screen);
/* 2386 */     ArrayList arrayList = new ArrayList();
/* 2387 */     this.canvasList.add(arrayList);
/* 2388 */     arrayList.add(paramCanvas3D);
/* 2389 */     this.canvasesDirty = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void removeFromCanvasList(Canvas3D paramCanvas3D) {
/* 2396 */     for (int i = this.screenList.size() - 1; i >= 0; i--) {
/* 2397 */       if ((Screen3D)this.screenList.get(i) == paramCanvas3D.screen) {
/*      */         
/* 2399 */         ArrayList arrayList = (ArrayList)this.canvasList.get(i);
/* 2400 */         arrayList.remove(arrayList.indexOf(paramCanvas3D));
/*      */         
/* 2402 */         if (arrayList.size() == 0) {
/* 2403 */           this.canvasList.remove(i);
/* 2404 */           this.screenList.remove(i);
/* 2405 */           this.canvasesDirty = true;
/*      */         } 
/*      */         return;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void computeCanvasesCached() {
/* 2415 */     synchronized (this.canvasList) {
/*      */       
/* 2417 */       int i = this.canvases.size();
/*      */       
/* 2419 */       Canvas3D[] arrayOfCanvas3D = new Canvas3D[i]; byte b1;
/* 2420 */       for (b1 = 0; b1 < i; b1++) {
/* 2421 */         arrayOfCanvas3D[b1] = (Canvas3D)this.canvases.get(b1);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2426 */       this.cachedCanvases = arrayOfCanvas3D;
/* 2427 */       i = 0;
/* 2428 */       this.longestScreenList = 0;
/* 2429 */       this.cachedCanvasList = new Canvas3D[this.canvasList.size()][0];
/* 2430 */       for (b1 = 0; b1 < this.cachedCanvasList.length; b1++) {
/* 2431 */         ArrayList arrayList = (ArrayList)this.canvasList.get(b1);
/* 2432 */         i = arrayList.size();
/* 2433 */         this.cachedCanvasList[b1] = new Canvas3D[i];
/* 2434 */         for (byte b = 0; b < i; b++) {
/* 2435 */           this.cachedCanvasList[b1][b] = (Canvas3D)arrayList.get(b);
/*      */         }
/*      */         
/* 2438 */         if (i > this.longestScreenList) {
/* 2439 */           this.longestScreenList = i;
/*      */         }
/*      */       } 
/* 2442 */       i = this.screenList.size();
/* 2443 */       Screen3D[] arrayOfScreen3D = new Screen3D[i];
/*      */       
/* 2445 */       for (byte b2 = 0; b2 < i; b2++) {
/* 2446 */         arrayOfScreen3D[b2] = (Screen3D)this.screenList.get(b2);
/*      */       }
/*      */ 
/*      */       
/* 2450 */       this.cachedScreens = arrayOfScreen3D;
/* 2451 */       this.canvasesDirty = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Canvas3D[][] getCanvasList(boolean paramBoolean) {
/* 2462 */     if (this.canvasesDirty && paramBoolean) {
/* 2463 */       computeCanvasesCached();
/*      */     }
/* 2465 */     return this.cachedCanvasList;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 2470 */   int getLongestScreenList() { return this.longestScreenList; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2475 */   Canvas3D[] getCanvases() { return this.cachedCanvases; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2480 */   Screen3D[] getScreens() { return this.cachedScreens; }
/*      */ 
/*      */   
/*      */   Canvas3D getFirstCanvas() {
/* 2484 */     synchronized (this.canvasList) {
/* 2485 */       if (this.canvases.size() > 0) {
/* 2486 */         return (Canvas3D)this.canvases.elementAt(0);
/*      */       }
/* 2488 */       return null;
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
/*      */   public long getCurrentFrameStartTime() {
/* 2502 */     synchronized (this.frameStartTimes) {
/* 2503 */       return this.currentFrameStartTime;
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
/*      */ 
/*      */   
/*      */   public long getLastFrameDuration() {
/* 2527 */     synchronized (this.frameStartTimes) {
/* 2528 */       return this.currentFrameDuration;
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
/*      */   public long getFrameNumber() {
/* 2541 */     synchronized (this.frameStartTimes) {
/* 2542 */       return this.currentFrameNumber;
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
/* 2554 */   public static int getMaxFrameStartTimes() { return 10; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getFrameStartTimes(long[] paramArrayOfLong) {
/*      */     long l;
/* 2576 */     synchronized (this.frameStartTimes) {
/* 2577 */       byte b2; int i = this.currentFrameIndex - 1;
/* 2578 */       if (i < 0) {
/* 2579 */         i = 9;
/*      */       }
/* 2581 */       l = this.frameNumbers[i];
/*      */       
/* 2583 */       if (paramArrayOfLong.length <= 10) {
/* 2584 */         b2 = paramArrayOfLong.length;
/*      */       } else {
/* 2586 */         b2 = 10;
/*      */       } 
/*      */       byte b1;
/* 2589 */       for (b1 = 0; b1 < b2; b1++) {
/* 2590 */         paramArrayOfLong[b1] = this.frameStartTimes[i];
/* 2591 */         i--;
/* 2592 */         if (i < 0) {
/* 2593 */           i = 9;
/*      */         }
/*      */       } 
/*      */       
/* 2597 */       if (paramArrayOfLong.length > 10) {
/* 2598 */         for (; b1 < paramArrayOfLong.length; b1++) {
/* 2599 */           paramArrayOfLong[b1] = 0L;
/*      */         }
/*      */       }
/*      */     } 
/*      */     
/* 2604 */     return l;
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
/*      */   public void setMinimumFrameCycleTime(long paramLong) {
/* 2623 */     if (paramLong < 0L) {
/* 2624 */       throw new IllegalArgumentException(J3dI18N.getString("View27"));
/*      */     }
/* 2626 */     this.minFrameCycleTime = paramLong;
/* 2627 */     VirtualUniverse.mc.setWork();
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
/* 2639 */   public long getMinimumFrameCycleTime() { return this.minFrameCycleTime; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setFrameTimingValues() {
/* 2648 */     synchronized (this.frameStartTimes) {
/* 2649 */       if (this.currentFrameIndex == 10) {
/* 2650 */         this.currentFrameIndex = 0;
/*      */       }
/*      */       
/* 2653 */       this.frameNumbers[this.currentFrameIndex] = this.frameNumber;
/*      */       
/* 2655 */       this.frameStartTimes[this.currentFrameIndex++] = this.startTime;
/* 2656 */       this.currentFrameStartTime = this.startTime;
/* 2657 */       this.currentFrameDuration = this.stopTime - this.startTime;
/* 2658 */       this.currentFrameNumber = this.frameNumber;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void computeCycleTime() {
/* 2666 */     if (this.minFrameCycleTime == 0L) {
/* 2667 */       this.isMinCycleTimeAchieve = true;
/* 2668 */       this.sleepTime = 0L;
/*      */     } else {
/* 2670 */       this.sleepTime = this.minFrameCycleTime - J3dClock.currentTimeMillis() - this.startTime;
/*      */       
/* 2672 */       this.isMinCycleTimeAchieve = (this.sleepTime <= 0L);
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
/*      */   public void setDepthBufferFreezeTransparent(boolean paramBoolean) {
/* 2692 */     this.depthBufferFreezeTransparent = paramBoolean;
/* 2693 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2703 */   public boolean getDepthBufferFreezeTransparent() { return this.depthBufferFreezeTransparent; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSceneAntialiasingEnable(boolean paramBoolean) {
/* 2719 */     this.sceneAntialiasingEnable = paramBoolean;
/* 2720 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2729 */   public boolean getSceneAntialiasingEnable() { return this.sceneAntialiasingEnable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocalEyeLightingEnable(boolean paramBoolean) {
/* 2745 */     this.localEyeLightingEnable = paramBoolean;
/* 2746 */     repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2755 */   public boolean getLocalEyeLightingEnable() { return this.localEyeLightingEnable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void attachViewPlatform(ViewPlatform paramViewPlatform) {
/* 2764 */     if (paramViewPlatform != null && paramViewPlatform == this.viewPlatform) {
/*      */       return;
/*      */     }
/*      */     
/* 2768 */     if (this.viewPlatform != null) {
/* 2769 */       ((ViewPlatformRetained)this.viewPlatform.retained).removeView(this);
/* 2770 */       if (this.viewPlatform.isLive()) {
/* 2771 */         synchronized (this.evaluateLock) {
/* 2772 */           this.viewPlatform = null;
/*      */           
/* 2774 */           evaluateActive();
/* 2775 */           this.viewPlatform = paramViewPlatform;
/*      */         } 
/* 2777 */         if (this.universe != null) {
/* 2778 */           this.universe.waitForMC();
/*      */         }
/*      */       } else {
/* 2781 */         this.viewPlatform = paramViewPlatform;
/*      */       } 
/*      */     } else {
/* 2784 */       this.viewPlatform = paramViewPlatform;
/*      */     } 
/* 2786 */     if (paramViewPlatform != null) {
/* 2787 */       if (paramViewPlatform.isLive()) {
/* 2788 */         checkView();
/* 2789 */         setUniverse(((ViewPlatformRetained)paramViewPlatform.retained).universe);
/*      */       } 
/* 2791 */       ((ViewPlatformRetained)paramViewPlatform.retained).setView(this);
/*      */     } 
/*      */     
/* 2794 */     evaluateActive();
/* 2795 */     if (paramViewPlatform == null && this.universe != null) {
/* 2796 */       this.universe.waitForMC();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2805 */   public ViewPlatform getViewPlatform() { return this.viewPlatform; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void checkView() {
/* 2812 */     if (this.physicalBody == null) {
/* 2813 */       throw new IllegalStateException(J3dI18N.getString("View13"));
/*      */     }
/* 2815 */     if (this.physicalEnvironment == null) {
/* 2816 */       throw new IllegalStateException(J3dI18N.getString("View14"));
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
/*      */   public final long[] stopBehaviorScheduler() {
/* 2838 */     long[] arrayOfLong = new long[2];
/*      */     
/* 2840 */     if (checkBehaviorSchedulerState("View15", "View16")) {
/* 2841 */       if (this.activeStatus && this.isRunning && this.universe.behaviorScheduler != null) {
/*      */ 
/*      */         
/* 2844 */         this.universe.behaviorScheduler.stopBehaviorScheduler(arrayOfLong);
/*      */       }
/* 2846 */       else if (this.universe != null && this.universe.behaviorScheduler != null) {
/*      */         
/* 2848 */         this.universe.behaviorScheduler.userStop = true;
/*      */       } 
/*      */     }
/*      */     
/* 2852 */     this.stopBehavior = true;
/* 2853 */     return arrayOfLong;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void startBehaviorScheduler() {
/* 2863 */     if (checkBehaviorSchedulerState("View17", "View18")) {
/* 2864 */       if (this.activeStatus && this.isRunning && this.universe.behaviorScheduler != null) {
/*      */         
/* 2866 */         this.universe.behaviorScheduler.startBehaviorScheduler();
/*      */       
/*      */       }
/* 2869 */       else if (this.universe != null && this.universe.behaviorScheduler != null) {
/*      */         
/* 2871 */         this.universe.behaviorScheduler.userStop = false;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2876 */     this.stopBehavior = false;
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
/*      */   boolean checkBehaviorSchedulerState(String paramString1, String paramString2) {
/* 2891 */     Thread thread = Thread.currentThread();
/*      */     
/* 2893 */     if (this.inCanvasCallback) {
/* 2894 */       synchronized (this.canvasList) {
/* 2895 */         for (int i = this.canvases.size() - 1; i >= 0; i--) {
/* 2896 */           if (((Canvas3D)this.canvases.elementAt(i)).screen.renderer == thread) {
/* 2897 */             throw new IllegalStateException(J3dI18N.getString(paramString1));
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 2903 */     if (this.viewPlatform != null && this.viewPlatform.isLive()) {
/* 2904 */       if (this.universe.inBehavior && this.universe.behaviorScheduler == thread) {
/* 2905 */         throw new IllegalStateException(J3dI18N.getString(paramString2));
/*      */       }
/* 2907 */       return true;
/*      */     } 
/* 2909 */     return false;
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
/* 2921 */   public final boolean isBehaviorSchedulerRunning() { return (this.universe != null && !this.stopBehavior && this.universe.behaviorScheduler != null) ? (!this.universe.behaviorScheduler.userStop) : false; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void stopView() {
/* 2940 */     checkViewState("View19", "View20");
/* 2941 */     synchronized (this.startStopViewLock) {
/* 2942 */       if (this.activeStatus && this.isRunning) {
/* 2943 */         VirtualUniverse.mc.postRequest(MasterControl.STOP_VIEW, this);
/* 2944 */         while (this.isRunning) {
/* 2945 */           MasterControl.threadYield();
/*      */         }
/*      */       } else {
/* 2948 */         this.isRunning = false;
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
/*      */   public final void startView() {
/* 2963 */     checkViewState("View21", "View22");
/* 2964 */     synchronized (this.startStopViewLock) {
/* 2965 */       if (this.activeStatus && !this.isRunning) {
/* 2966 */         VirtualUniverse.mc.postRequest(MasterControl.START_VIEW, this);
/* 2967 */         while (!this.isRunning) {
/* 2968 */           MasterControl.threadYield();
/*      */         }
/* 2970 */         VirtualUniverse.mc.sendRunMessage(this, 16);
/*      */       } else {
/*      */         
/* 2973 */         this.isRunning = true;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void checkViewState(String paramString1, String paramString2) throws IllegalStateException {
/* 2984 */     if (this.inCanvasCallback) {
/* 2985 */       Thread thread = Thread.currentThread();
/* 2986 */       synchronized (this.canvasList) {
/* 2987 */         for (int i = this.canvases.size() - 1; i >= 0; i--) {
/* 2988 */           Canvas3D canvas3D = (Canvas3D)this.canvases.elementAt(i);
/* 2989 */           if (canvas3D.screen.renderer == thread) {
/* 2990 */             throw new IllegalStateException(J3dI18N.getString(paramString1));
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2997 */     if (this.viewPlatform != null && this.viewPlatform.isLive() && 
/* 2998 */       this.universe.inBehavior && Thread.currentThread() == this.universe.behaviorScheduler)
/*      */     {
/* 3000 */       throw new IllegalStateException(J3dI18N.getString(paramString2));
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
/* 3014 */   public final boolean isViewRunning() { return this.isRunning; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderOnce() {
/* 3030 */     checkViewState("View28", "View29");
/* 3031 */     synchronized (this.startStopViewLock) {
/* 3032 */       if (this.isRunning) {
/* 3033 */         throw new IllegalStateException(J3dI18N.getString("View30"));
/*      */       }
/* 3035 */       this.renderOnceFinish = false;
/* 3036 */       VirtualUniverse.mc.postRequest(MasterControl.RENDER_ONCE, this);
/* 3037 */       while (!this.renderOnceFinish) {
/* 3038 */         MasterControl.threadYield();
/*      */       }
/* 3040 */       this.renderOnceFinish = true;
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
/*      */   public void repaint() {
/* 3057 */     if (this.activeStatus && this.isRunning) {
/* 3058 */       VirtualUniverse.mc.sendRunMessage(this, 16);
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
/*      */   
/*      */   final void updateViewCache() {
/* 3081 */     if (this.firstTime) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3086 */       this.viewer = Viewer.removeViewerMapEntry(this);
/* 3087 */       this.firstTime = false;
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
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3110 */     synchronized (this) {
/* 3111 */       this.viewCache.snapshot();
/* 3112 */       this.viewCache.computeDerivedData();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3120 */     synchronized (this.canvasList) {
/* 3121 */       int i = this.canvases.size() - 1;
/* 3122 */       while (i >= 0) {
/* 3123 */         Screen3D screen3D = ((Canvas3D)this.canvases.elementAt(i--)).getScreen3D();
/*      */         
/* 3125 */         if (screen3D != null) {
/* 3126 */           screen3D.updateViewCache();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void evaluateActive() {
/* 3137 */     synchronized (this.evaluateLock) {
/* 3138 */       if (this.universe == null) {
/*      */         return;
/*      */       }
/*      */       
/* 3142 */       if (this.viewPlatform == null || !this.viewPlatform.isLive() || !((ViewPlatformRetained)this.viewPlatform.retained).switchState.currentSwitchOn) {
/*      */ 
/*      */         
/* 3145 */         if (this.activeStatus) {
/* 3146 */           deactivate();
/* 3147 */           this.activeStatus = false;
/*      */         } 
/*      */         
/* 3150 */         if (VirtualUniverse.mc.isRegistered(this) && (this.universe.isEmpty() || (this.canvases.isEmpty() && (this.viewPlatform == null || !this.viewPlatform.isLive())))) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3162 */           this.universe.unRegViewWaiting = this;
/* 3163 */           this.resetUnivCount = this.universeCount;
/* 3164 */           VirtualUniverse.mc.postRequest(MasterControl.UNREGISTER_VIEW, this);
/*      */         } 
/*      */       } else {
/*      */         int i;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3173 */         VirtualUniverse virtualUniverse = null;
/* 3174 */         synchronized (this.canvasList) {
/*      */           
/* 3176 */           for (i = this.canvases.size() - 1; i >= 0; i--) {
/* 3177 */             Canvas3D canvas3D = (Canvas3D)this.canvases.elementAt(i);
/* 3178 */             if (canvas3D.active) {
/*      */               
/* 3180 */               if (!this.activeStatus && this.universeCount > this.resetUnivCount) {
/* 3181 */                 virtualUniverse = this.universe;
/*      */               }
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/* 3190 */         if (virtualUniverse != null) {
/* 3191 */           activate(virtualUniverse);
/* 3192 */           this.activeStatus = true;
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 3197 */         if (i < 0 && this.activeStatus) {
/* 3198 */           deactivate();
/* 3199 */           this.activeStatus = false;
/*      */           
/*      */           return;
/*      */         } 
/* 3203 */         if (VirtualUniverse.mc.isRegistered(this))
/*      */         {
/* 3205 */           VirtualUniverse.mc.postRequest(MasterControl.REEVALUATE_CANVAS, this);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void setUniverse(VirtualUniverse paramVirtualUniverse) {
/* 3214 */     synchronized (VirtualUniverse.mc.requestObjList) {
/* 3215 */       if (this.renderBin == null || this.renderBin.universe != paramVirtualUniverse) {
/*      */         
/* 3217 */         if (this.renderBin != null) {
/* 3218 */           this.renderBin.cleanup();
/*      */         }
/* 3220 */         this.renderBin = new RenderBin(paramVirtualUniverse, this);
/* 3221 */         this.renderBin.universe = paramVirtualUniverse;
/*      */       } 
/*      */ 
/*      */       
/* 3225 */       if (this.soundScheduler == null || this.soundScheduler.universe != paramVirtualUniverse) {
/*      */ 
/*      */         
/* 3228 */         if (this.soundScheduler != null) {
/* 3229 */           this.soundScheduler.cleanup();
/*      */         }
/* 3231 */         this.soundScheduler = new SoundScheduler(paramVirtualUniverse, this);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3241 */       this.universeCount++;
/* 3242 */       this.universe = paramVirtualUniverse;
/*      */     } 
/* 3244 */     evaluateActive();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void activate(VirtualUniverse paramVirtualUniverse) {
/* 3252 */     paramVirtualUniverse.checkForEnableEvents();
/*      */     
/* 3254 */     if (this.physicalBody != null) {
/* 3255 */       this.physicalBody.addUser(this);
/*      */     }
/*      */     
/* 3258 */     if (!VirtualUniverse.mc.isRegistered(this)) {
/* 3259 */       paramVirtualUniverse.regViewWaiting = this;
/*      */     }
/*      */     
/* 3262 */     VirtualUniverse.mc.postRequest(MasterControl.ACTIVATE_VIEW, this);
/*      */ 
/*      */     
/* 3265 */     if (!paramVirtualUniverse.isSceneGraphLock) {
/* 3266 */       paramVirtualUniverse.waitForMC();
/*      */     }
/* 3268 */     if (this.soundScheduler != null) {
/* 3269 */       this.soundScheduler.reset();
/*      */     }
/*      */     
/* 3272 */     J3dMessage j3dMessage = new J3dMessage();
/* 3273 */     j3dMessage.universe = paramVirtualUniverse;
/* 3274 */     j3dMessage.view = this;
/* 3275 */     j3dMessage.type = 4;
/* 3276 */     j3dMessage.threads = 386;
/*      */ 
/*      */ 
/*      */     
/* 3280 */     j3dMessage.args[0] = this;
/* 3281 */     synchronized (((ViewPlatformRetained)this.viewPlatform.retained).sphere) {
/* 3282 */       j3dMessage.args[1] = new Float(((ViewPlatformRetained)this.viewPlatform.retained).sphere.radius);
/*      */     } 
/* 3284 */     j3dMessage.args[2] = new Integer(2);
/* 3285 */     j3dMessage.args[3] = new Integer(this.transparencySortingPolicy);
/* 3286 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void deactivate() {
/* 3293 */     VirtualUniverse.mc.postRequest(MasterControl.DEACTIVATE_VIEW, this);
/* 3294 */     if (this.physicalBody != null) {
/* 3295 */       this.physicalBody.removeUser(this);
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
/* 3306 */     if (VirtualUniverse.mc.forceReleaseView) {
/* 3307 */       this.universe.addViewIdToFreeList(this.viewId);
/*      */     }
/*      */ 
/*      */     
/* 3311 */     J3dMessage j3dMessage = new J3dMessage();
/* 3312 */     j3dMessage.universe = this.universe;
/* 3313 */     j3dMessage.view = this;
/* 3314 */     j3dMessage.type = 4;
/* 3315 */     j3dMessage.threads = 386;
/*      */ 
/*      */ 
/*      */     
/* 3319 */     j3dMessage.args[0] = this;
/* 3320 */     if (this.viewPlatform != null) {
/* 3321 */       synchronized (((ViewPlatformRetained)this.viewPlatform.retained).sphere) {
/* 3322 */         j3dMessage.args[1] = new Float(((ViewPlatformRetained)this.viewPlatform.retained).sphere.radius);
/*      */       } 
/*      */     } else {
/* 3325 */       j3dMessage.args[1] = new Float(0.0F);
/*      */     } 
/* 3327 */     j3dMessage.args[2] = new Integer(2);
/* 3328 */     j3dMessage.args[3] = new Integer(this.transparencySortingPolicy);
/* 3329 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */   }
/*      */ 
/*      */   
/*      */   void cleanupViewId() {
/* 3334 */     this.universe.addViewIdToFreeList(this.viewId);
/* 3335 */     this.viewId = null;
/*      */   }
/*      */ 
/*      */   
/*      */   void assignViewId() {
/* 3340 */     if (this.viewId == null) {
/* 3341 */       this.viewId = this.universe.getViewId();
/* 3342 */       this.viewIndex = this.viewId.intValue();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendEventToSoundScheduler(AWTEvent paramAWTEvent) {
/* 3350 */     if (this.soundScheduler != null) {
/* 3351 */       this.soundScheduler.receiveAWTEvent(paramAWTEvent);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void reset() {
/* 3357 */     for (byte b = 0; b < this.canvases.size(); b++) {
/* 3358 */       ((Canvas3D)this.canvases.get(b)).reset();
/*      */     }
/*      */ 
/*      */     
/* 3362 */     this.renderBinReady = false;
/*      */     
/* 3364 */     this.soundScheduler.cleanup();
/* 3365 */     this.soundScheduler = null;
/* 3366 */     this.soundRenderer = new SoundRenderer();
/*      */     
/* 3368 */     this.viewCache = new ViewCache(this);
/* 3369 */     getCanvasList(true);
/* 3370 */     cleanupViewId();
/* 3371 */     this.renderBin.cleanup();
/* 3372 */     this.renderBin = null;
/* 3373 */     this.universe = null;
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\View.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */