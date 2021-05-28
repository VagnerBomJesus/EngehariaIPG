/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.awt.GraphicsEnvironment;
/*      */ import java.awt.HeadlessException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Vector;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class VirtualUniverse
/*      */ {
/*   56 */   static MasterControl mc = null;
/*      */ 
/*      */   
/*      */   Object sceneGraphLock;
/*      */ 
/*      */   
/*      */   Object behaviorLock;
/*      */ 
/*      */   
/*      */   Vector listOfLocales;
/*      */ 
/*      */   
/*      */   ArrayList viewPlatforms;
/*      */ 
/*      */   
/*      */   Object[] viewPlatformList;
/*      */ 
/*      */   
/*      */   boolean vpChanged;
/*      */ 
/*      */   
/*      */   Vector backgrounds;
/*      */ 
/*      */   
/*      */   Vector clips;
/*      */ 
/*      */   
/*      */   Vector sounds;
/*      */ 
/*      */   
/*      */   Vector soundscapes;
/*      */ 
/*      */   
/*      */   BehaviorScheduler behaviorScheduler;
/*      */ 
/*      */   
/*      */   GeometryStructure geometryStructure;
/*      */ 
/*      */   
/*      */   TransformStructure transformStructure;
/*      */ 
/*      */   
/*      */   BehaviorStructure behaviorStructure;
/*      */ 
/*      */   
/*      */   SoundStructure soundStructure;
/*      */ 
/*      */   
/*      */   RenderingEnvironmentStructure renderingEnvironmentStructure;
/*      */ 
/*      */   
/*      */   int renderingEnvironmentStructureRefCount;
/*      */ 
/*      */   
/*      */   long nodeIdCount;
/*      */ 
/*      */   
/*      */   int viewIdCount;
/*      */ 
/*      */   
/*      */   Vector nodeIdFreeList;
/*      */ 
/*      */   
/*      */   ArrayList viewIdFreeList;
/*      */ 
/*      */   
/*      */   int numNodes;
/*      */ 
/*      */   
/*      */   SetLiveState setLiveState;
/*      */ 
/*      */   
/*      */   ObjectUpdate[] updateObjects;
/*      */ 
/*      */   
/*      */   int updateObjectsLen;
/*      */ 
/*      */   
/*      */   ArrayList dirtyGeomList;
/*      */ 
/*      */   
/*      */   View currentView;
/*      */ 
/*      */   
/*      */   boolean inBehavior;
/*      */ 
/*      */   
/*      */   boolean enableComponent;
/*      */   
/*      */   boolean enableFocus;
/*      */   
/*      */   boolean enableKey;
/*      */   
/*      */   boolean enableMouse;
/*      */   
/*      */   boolean enableMouseMotion;
/*      */   
/*      */   boolean enableMouseWheel;
/*      */   
/*      */   int activeViewCount;
/*      */   
/*      */   static ThreadGroup rootThreadGroup;
/*      */   
/*  159 */   private static J3dQueryProps properties = null;
/*      */ 
/*      */   
/*      */   View regViewWaiting;
/*      */ 
/*      */   
/*      */   View unRegViewWaiting;
/*      */ 
/*      */   
/*      */   boolean isSceneGraphLock;
/*      */   
/*      */   private Object waitLock;
/*      */   
/*      */   private HashSet<GraphStructureChangeListener> structureChangeListenerSet;
/*      */   
/*      */   private HashSet<ShaderErrorListener> shaderErrorListenerSet;
/*      */   
/*      */   private ShaderErrorListener defaultShaderErrorListener;
/*      */   
/*  178 */   private static HashSet<RenderingErrorListener> renderingErrorListenerSet = null;
/*  179 */   private static RenderingErrorListener defaultRenderingErrorListener = Renderer.getDefaultErrorListener(); public VirtualUniverse() { this.sceneGraphLock = new Object(); this.behaviorLock = new Object(); this.listOfLocales = new Vector(); this.viewPlatforms = new ArrayList(); this.viewPlatformList = null; this.vpChanged = false; this.backgrounds = new Vector(); this.clips = new Vector(); this.sounds = new Vector(); this.soundscapes = new Vector(); this.behaviorScheduler = null; this.geometryStructure = null; this.transformStructure = null; this.behaviorStructure = null; this.soundStructure = null; this.renderingEnvironmentStructure = null; this.renderingEnvironmentStructureRefCount = 0; this.nodeIdCount = 0L; this.viewIdCount = 0; this.nodeIdFreeList = new Vector(); this.viewIdFreeList = new ArrayList(); this.numNodes = 0; this.updateObjects = new ObjectUpdate[16]; this.updateObjectsLen = 0; this.dirtyGeomList = new ArrayList(); this.inBehavior = false; this.enableComponent = false; this.enableFocus = false; this.enableKey = false; this.enableMouse = false; this.enableMouseMotion = false; this.enableMouseWheel = false; this.activeViewCount = 0; this.regViewWaiting = null;
/*      */     this.unRegViewWaiting = null;
/*      */     this.isSceneGraphLock = false;
/*      */     this.waitLock = new Object();
/*      */     this.structureChangeListenerSet = null;
/*      */     this.shaderErrorListenerSet = null;
/*      */     this.defaultShaderErrorListener = ShaderProgram.getDefaultErrorListener();
/*  186 */     this.setLiveState = new SetLiveState(this);
/*  187 */     initMCStructure(); }
/*      */ 
/*      */ 
/*      */   
/*      */   void initMCStructure() {
/*  192 */     if (this.geometryStructure != null) {
/*  193 */       this.geometryStructure.cleanup();
/*      */     }
/*  195 */     this.geometryStructure = new GeometryStructure(this);
/*  196 */     if (this.transformStructure != null) {
/*  197 */       this.transformStructure.cleanup();
/*      */     }
/*  199 */     this.transformStructure = new TransformStructure(this);
/*  200 */     if (this.behaviorStructure != null) {
/*  201 */       this.behaviorStructure.cleanup();
/*      */     }
/*  203 */     this.behaviorStructure = new BehaviorStructure(this);
/*  204 */     if (this.soundStructure != null) {
/*  205 */       this.soundStructure.cleanup();
/*      */     }
/*  207 */     this.soundStructure = new SoundStructure(this);
/*  208 */     if (this.renderingEnvironmentStructure != null) {
/*  209 */       this.renderingEnvironmentStructure.cleanup();
/*      */     }
/*  211 */     this.renderingEnvironmentStructure = new RenderingEnvironmentStructure(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void loadLibraries() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static  {
/*  227 */     boolean bool = MasterControl.isCoreLoggable(Level.CONFIG);
/*  228 */     Logger logger = MasterControl.getCoreLogger();
/*      */ 
/*      */ 
/*      */     
/*  232 */     if (bool) {
/*  233 */       StringBuffer stringBuffer = new StringBuffer("Java 3D ");
/*      */ 
/*      */ 
/*      */       
/*  237 */       stringBuffer.append(VersionInfo.getVersion());
/*  238 */       String str = stringBuffer.toString();
/*  239 */       if (bool) {
/*  240 */         logger.config(str);
/*      */       } else {
/*  242 */         System.err.println(str);
/*  243 */         System.err.println();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  248 */     if (bool) {
/*  249 */       StringBuffer stringBuffer = new StringBuffer();
/*  250 */       stringBuffer.append("Initializing Java 3D runtime system:\n").append("    version = ").append(VersionInfo.getVersion()).append("\n").append("    vendor = ").append(VersionInfo.getVendor()).append("\n").append("    specification.version = ").append(VersionInfo.getSpecificationVersion()).append("\n").append("    specification.vendor = ").append(VersionInfo.getSpecificationVendor());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  262 */       String str = stringBuffer.toString();
/*  263 */       if (bool) {
/*  264 */         logger.config(str);
/*      */       } else {
/*  266 */         System.err.println(str);
/*  267 */         System.err.println();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  274 */     if (GraphicsEnvironment.isHeadless()) {
/*  275 */       throw new HeadlessException();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  280 */     MasterControl.loadLibraries();
/*  281 */     mc = new MasterControl();
/*      */ 
/*      */     
/*  284 */     if (bool) {
/*  285 */       StringBuffer stringBuffer = new StringBuffer();
/*  286 */       stringBuffer.append("Java 3D system initialized\n").append("    rendering pipeline = ").append(Pipeline.getPipeline().getPipelineName());
/*      */ 
/*      */       
/*  289 */       String str = stringBuffer.toString();
/*  290 */       if (bool) {
/*  291 */         logger.config(str);
/*      */       } else {
/*  293 */         System.err.println(str);
/*  294 */         System.err.println();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  304 */   void addLocale(Locale paramLocale) { this.listOfLocales.addElement(paramLocale); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeLocale(Locale paramLocale) {
/*  322 */     if (paramLocale.getVirtualUniverse() != this) {
/*  323 */       throw new IllegalArgumentException(J3dI18N.getString("VirtualUniverse0"));
/*      */     }
/*      */     
/*  326 */     this.listOfLocales.removeElement(paramLocale);
/*  327 */     paramLocale.removeFromUniverse();
/*  328 */     if (isEmpty()) {
/*  329 */       mc.postRequest(MasterControl.EMPTY_UNIVERSE, this);
/*      */     }
/*      */     
/*  332 */     this.setLiveState.reset(null);
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
/*      */   public void removeAllLocales() {
/*      */     int i;
/*  353 */     for (i = this.listOfLocales.size() - 1; i > 0; i--) {
/*  354 */       ((Locale)this.listOfLocales.get(i)).removeFromUniverse();
/*      */     }
/*      */     
/*  357 */     if (i >= 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  362 */       Locale locale = (Locale)this.listOfLocales.get(0);
/*  363 */       this.listOfLocales.clear();
/*  364 */       locale.removeFromUniverse();
/*      */     } 
/*  366 */     mc.postRequest(MasterControl.EMPTY_UNIVERSE, this);
/*      */ 
/*      */     
/*  369 */     this.setLiveState.reset(null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  378 */   public Enumeration getAllLocales() { return this.listOfLocales.elements(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  386 */   public int numLocales() { return this.listOfLocales.size(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setJ3DThreadPriority(int paramInt) {
/*  406 */     if (paramInt > 10) {
/*  407 */       paramInt = 10;
/*  408 */     } else if (paramInt < 1) {
/*  409 */       paramInt = 1;
/*      */     } 
/*  411 */     mc.setThreadPriority(paramInt);
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
/*  423 */   public static int getJ3DThreadPriority() { return mc.getThreadPriority(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final Map getProperties() {
/*  565 */     if (properties == null) {
/*      */       
/*  567 */       ArrayList arrayList1 = new ArrayList();
/*  568 */       ArrayList arrayList2 = new ArrayList();
/*      */ 
/*      */ 
/*      */       
/*  572 */       arrayList1.add("j3d.version");
/*  573 */       arrayList2.add(VersionInfo.getVersion());
/*      */       
/*  575 */       arrayList1.add("j3d.vendor");
/*  576 */       arrayList2.add(VersionInfo.getVendor());
/*      */       
/*  578 */       arrayList1.add("j3d.specification.version");
/*  579 */       arrayList2.add(VersionInfo.getSpecificationVersion());
/*      */       
/*  581 */       arrayList1.add("j3d.specification.vendor");
/*  582 */       arrayList2.add(VersionInfo.getSpecificationVendor());
/*      */       
/*  584 */       arrayList1.add("j3d.renderer");
/*  585 */       arrayList2.add(Pipeline.getPipeline().getRendererName());
/*      */       
/*  587 */       arrayList1.add("j3d.pipeline");
/*  588 */       arrayList2.add(Pipeline.getPipeline().getPipelineName());
/*      */ 
/*      */       
/*  591 */       properties = new J3dQueryProps((String[])arrayList1.toArray(new String[0]), arrayList2.toArray());
/*      */     } 
/*      */ 
/*      */     
/*  595 */     return properties;
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
/*      */   String getNodeId() {
/*      */     String str;
/*  608 */     if (this.nodeIdFreeList.size() == 0) {
/*  609 */       str = Long.toString(this.nodeIdCount);
/*  610 */       this.nodeIdCount++;
/*      */     }
/*      */     else {
/*      */       
/*  614 */       int i = this.nodeIdFreeList.size() - 1;
/*  615 */       str = (String)this.nodeIdFreeList.remove(i);
/*      */     } 
/*  617 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Integer getViewId() {
/*      */     Integer integer;
/*  627 */     synchronized (this.viewIdFreeList) {
/*  628 */       int i = this.viewIdFreeList.size();
/*  629 */       if (i == 0) {
/*  630 */         integer = new Integer(this.viewIdCount++);
/*      */       } else {
/*  632 */         integer = (Integer)this.viewIdFreeList.remove(i - 1);
/*      */       } 
/*      */     } 
/*  635 */     return integer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addViewIdToFreeList(Integer paramInteger) {
/*  642 */     synchronized (this.viewIdFreeList) {
/*  643 */       this.viewIdFreeList.add(paramInteger);
/*      */     } 
/*      */   }
/*      */   
/*      */   void addViewPlatform(ViewPlatformRetained paramViewPlatformRetained) {
/*  648 */     this.vpChanged = true;
/*  649 */     this.viewPlatforms.add(paramViewPlatformRetained);
/*      */   }
/*      */   
/*      */   void removeViewPlatform(ViewPlatformRetained paramViewPlatformRetained) {
/*  653 */     this.vpChanged = true;
/*  654 */     this.viewPlatforms.remove(this.viewPlatforms.indexOf(paramViewPlatformRetained));
/*      */   }
/*      */   
/*      */   Object[] getViewPlatformList() {
/*  658 */     if (this.vpChanged) {
/*  659 */       this.viewPlatformList = this.viewPlatforms.toArray();
/*  660 */       this.vpChanged = false;
/*      */     } 
/*  662 */     return this.viewPlatformList;
/*      */   }
/*      */   
/*      */   void checkForEnableEvents() {
/*  666 */     enableComponentEvents();
/*  667 */     if (this.enableFocus) {
/*  668 */       enableFocusEvents();
/*      */     }
/*  670 */     if (this.enableKey) {
/*  671 */       enableKeyEvents();
/*      */     }
/*  673 */     if (this.enableMouse) {
/*  674 */       enableMouseEvents();
/*      */     }
/*  676 */     if (this.enableMouseMotion) {
/*  677 */       enableMouseMotionEvents();
/*      */     }
/*  679 */     if (this.enableMouseWheel) {
/*  680 */       enableMouseWheelEvents();
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
/*      */   void enableComponentEvents() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void disableFocusEvents() {
/*  718 */     Object[] arrayOfObject = getViewPlatformList();
/*  719 */     this.enableFocus = false;
/*      */     
/*  721 */     if (arrayOfObject != null) {
/*  722 */       for (byte b = 0; b < arrayOfObject.length; b++) {
/*  723 */         ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)arrayOfObject[b];
/*  724 */         View[] arrayOfView = viewPlatformRetained.getViewList();
/*  725 */         for (int i = arrayOfView.length - 1; i >= 0; i--) {
/*  726 */           Enumeration enumeration = arrayOfView[i].getAllCanvas3Ds();
/*  727 */           while (enumeration.hasMoreElements()) {
/*  728 */             Canvas3D canvas3D = (Canvas3D)enumeration.nextElement();
/*      */             
/*  730 */             if (canvas3D.eventCatcher != null) {
/*  731 */               canvas3D.eventCatcher.disableFocusEvents();
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void enableFocusEvents() {
/*  744 */     Object[] arrayOfObject = getViewPlatformList();
/*  745 */     this.enableFocus = true;
/*      */     
/*  747 */     if (arrayOfObject != null) {
/*  748 */       for (byte b = 0; b < arrayOfObject.length; b++) {
/*  749 */         ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)arrayOfObject[b];
/*  750 */         View[] arrayOfView = viewPlatformRetained.getViewList();
/*  751 */         for (int i = arrayOfView.length - 1; i >= 0; i--) {
/*  752 */           Enumeration enumeration = arrayOfView[i].getAllCanvas3Ds();
/*  753 */           while (enumeration.hasMoreElements()) {
/*  754 */             Canvas3D canvas3D = (Canvas3D)enumeration.nextElement();
/*      */             
/*  756 */             if (canvas3D.eventCatcher != null) {
/*  757 */               canvas3D.eventCatcher.enableFocusEvents();
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void disableKeyEvents() {
/*  769 */     Object[] arrayOfObject = getViewPlatformList();
/*      */ 
/*      */     
/*  772 */     this.enableKey = false;
/*      */     
/*  774 */     if (arrayOfObject != null) {
/*  775 */       for (byte b = 0; b < arrayOfObject.length; b++) {
/*  776 */         ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)arrayOfObject[b];
/*  777 */         View[] arrayOfView = viewPlatformRetained.getViewList();
/*  778 */         for (int i = arrayOfView.length - 1; i >= 0; i--) {
/*  779 */           Enumeration enumeration = arrayOfView[i].getAllCanvas3Ds();
/*  780 */           while (enumeration.hasMoreElements()) {
/*  781 */             Canvas3D canvas3D = (Canvas3D)enumeration.nextElement();
/*      */             
/*  783 */             if (canvas3D.eventCatcher != null) {
/*  784 */               canvas3D.eventCatcher.disableKeyEvents();
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void enableKeyEvents() {
/*  796 */     Object[] arrayOfObject = getViewPlatformList();
/*      */ 
/*      */     
/*  799 */     this.enableKey = true;
/*      */     
/*  801 */     if (arrayOfObject != null) {
/*  802 */       for (byte b = 0; b < arrayOfObject.length; b++) {
/*  803 */         ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)arrayOfObject[b];
/*  804 */         View[] arrayOfView = viewPlatformRetained.getViewList();
/*  805 */         for (int i = arrayOfView.length - 1; i >= 0; i--) {
/*  806 */           Enumeration enumeration = arrayOfView[i].getAllCanvas3Ds();
/*  807 */           while (enumeration.hasMoreElements()) {
/*  808 */             Canvas3D canvas3D = (Canvas3D)enumeration.nextElement();
/*      */             
/*  810 */             if (canvas3D.eventCatcher != null) {
/*  811 */               canvas3D.eventCatcher.enableKeyEvents();
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void disableMouseEvents() {
/*  824 */     Object[] arrayOfObject = getViewPlatformList();
/*      */     
/*  826 */     this.enableMouse = false;
/*      */     
/*  828 */     if (arrayOfObject != null) {
/*  829 */       for (byte b = 0; b < arrayOfObject.length; b++) {
/*  830 */         ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)arrayOfObject[b];
/*  831 */         View[] arrayOfView = viewPlatformRetained.getViewList();
/*  832 */         for (int i = arrayOfView.length - 1; i >= 0; i--) {
/*  833 */           Enumeration enumeration = arrayOfView[i].getAllCanvas3Ds();
/*  834 */           while (enumeration.hasMoreElements()) {
/*  835 */             Canvas3D canvas3D = (Canvas3D)enumeration.nextElement();
/*      */             
/*  837 */             if (canvas3D.eventCatcher != null) {
/*  838 */               canvas3D.eventCatcher.disableMouseEvents();
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void enableMouseEvents() {
/*  850 */     Object[] arrayOfObject = getViewPlatformList();
/*      */     
/*  852 */     this.enableMouse = true;
/*      */     
/*  854 */     if (arrayOfObject != null) {
/*  855 */       for (byte b = 0; b < arrayOfObject.length; b++) {
/*  856 */         ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)arrayOfObject[b];
/*  857 */         View[] arrayOfView = viewPlatformRetained.getViewList();
/*  858 */         for (int i = arrayOfView.length - 1; i >= 0; i--) {
/*  859 */           Enumeration enumeration = arrayOfView[i].getAllCanvas3Ds();
/*  860 */           while (enumeration.hasMoreElements()) {
/*  861 */             Canvas3D canvas3D = (Canvas3D)enumeration.nextElement();
/*      */             
/*  863 */             if (canvas3D.eventCatcher != null) {
/*  864 */               canvas3D.eventCatcher.enableMouseEvents();
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void disableMouseMotionEvents() {
/*  877 */     Object[] arrayOfObject = getViewPlatformList();
/*      */     
/*  879 */     this.enableMouseMotion = false;
/*      */     
/*  881 */     if (arrayOfObject != null) {
/*  882 */       for (byte b = 0; b < arrayOfObject.length; b++) {
/*  883 */         ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)arrayOfObject[b];
/*  884 */         View[] arrayOfView = viewPlatformRetained.getViewList();
/*  885 */         for (int i = arrayOfView.length - 1; i >= 0; i--) {
/*  886 */           Enumeration enumeration = arrayOfView[i].getAllCanvas3Ds();
/*  887 */           while (enumeration.hasMoreElements()) {
/*  888 */             Canvas3D canvas3D = (Canvas3D)enumeration.nextElement();
/*      */             
/*  890 */             if (canvas3D.eventCatcher != null) {
/*  891 */               canvas3D.eventCatcher.disableMouseMotionEvents();
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void enableMouseMotionEvents() {
/*  903 */     Object[] arrayOfObject = getViewPlatformList();
/*      */     
/*  905 */     this.enableMouseMotion = true;
/*      */     
/*  907 */     if (arrayOfObject != null) {
/*  908 */       for (byte b = 0; b < arrayOfObject.length; b++) {
/*  909 */         ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)arrayOfObject[b];
/*  910 */         View[] arrayOfView = viewPlatformRetained.getViewList();
/*  911 */         for (int i = arrayOfView.length - 1; i >= 0; i--) {
/*  912 */           Enumeration enumeration = arrayOfView[i].getAllCanvas3Ds();
/*  913 */           while (enumeration.hasMoreElements()) {
/*  914 */             Canvas3D canvas3D = (Canvas3D)enumeration.nextElement();
/*      */             
/*  916 */             if (canvas3D.eventCatcher != null) {
/*  917 */               canvas3D.eventCatcher.enableMouseMotionEvents();
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void disableMouseWheelEvents() {
/*  929 */     Object[] arrayOfObject = getViewPlatformList();
/*      */     
/*  931 */     this.enableMouseWheel = false;
/*      */     
/*  933 */     if (arrayOfObject != null) {
/*  934 */       for (byte b = 0; b < arrayOfObject.length; b++) {
/*  935 */         ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)arrayOfObject[b];
/*  936 */         View[] arrayOfView = viewPlatformRetained.getViewList();
/*  937 */         for (int i = arrayOfView.length - 1; i >= 0; i--) {
/*  938 */           Enumeration enumeration = arrayOfView[i].getAllCanvas3Ds();
/*  939 */           while (enumeration.hasMoreElements()) {
/*  940 */             Canvas3D canvas3D = (Canvas3D)enumeration.nextElement();
/*      */             
/*  942 */             if (canvas3D.eventCatcher != null) {
/*  943 */               canvas3D.eventCatcher.disableMouseWheelEvents();
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void enableMouseWheelEvents() {
/*  955 */     Object[] arrayOfObject = getViewPlatformList();
/*      */     
/*  957 */     this.enableMouseWheel = true;
/*      */     
/*  959 */     if (arrayOfObject != null) {
/*  960 */       for (byte b = 0; b < arrayOfObject.length; b++) {
/*  961 */         ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)arrayOfObject[b];
/*  962 */         View[] arrayOfView = viewPlatformRetained.getViewList();
/*  963 */         for (int i = arrayOfView.length - 1; i >= 0; i--) {
/*  964 */           Enumeration enumeration = arrayOfView[i].getAllCanvas3Ds();
/*  965 */           while (enumeration.hasMoreElements()) {
/*  966 */             Canvas3D canvas3D = (Canvas3D)enumeration.nextElement();
/*      */             
/*  968 */             if (canvas3D.eventCatcher != null) {
/*  969 */               canvas3D.eventCatcher.enableMouseWheelEvents();
/*      */             }
/*      */           } 
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
/*  982 */   final void setCurrentView(View paramView) { this.currentView = paramView; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  991 */   final View getCurrentView() { return this.currentView; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1000 */   static ThreadGroup getRootThreadGroup() { return rootThreadGroup; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isEmpty() {
/* 1008 */     Enumeration enumeration = this.listOfLocales.elements();
/*      */     
/* 1010 */     while (enumeration.hasMoreElements()) {
/* 1011 */       Locale locale = (Locale)enumeration.nextElement();
/* 1012 */       if (!locale.branchGroups.isEmpty()) {
/* 1013 */         return false;
/*      */       }
/*      */     } 
/* 1016 */     return true;
/*      */   }
/*      */   
/*      */   void resetWaitMCFlag() {
/* 1020 */     synchronized (this.waitLock) {
/* 1021 */       this.regViewWaiting = null;
/* 1022 */       this.unRegViewWaiting = null;
/* 1023 */       this.isSceneGraphLock = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   void waitForMC() {
/* 1028 */     synchronized (this.waitLock) {
/* 1029 */       if (this.unRegViewWaiting != null && (
/* 1030 */         this.regViewWaiting == null || this.regViewWaiting != this.unRegViewWaiting)) {
/*      */         
/* 1032 */         while (!this.unRegViewWaiting.doneUnregister) {
/* 1033 */           MasterControl.threadYield();
/*      */         }
/* 1035 */         this.unRegViewWaiting.doneUnregister = false;
/* 1036 */         this.unRegViewWaiting = null;
/*      */       } 
/*      */ 
/*      */       
/* 1040 */       if (this.regViewWaiting != null) {
/* 1041 */         while (!mc.isRegistered(this.regViewWaiting)) {
/* 1042 */           MasterControl.threadYield();
/*      */         }
/* 1044 */         this.regViewWaiting = null;
/*      */       } 
/* 1046 */       this.isSceneGraphLock = false;
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
/*      */   public void addGraphStructureChangeListener(GraphStructureChangeListener paramGraphStructureChangeListener) {
/* 1061 */     if (paramGraphStructureChangeListener == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1065 */     if (this.structureChangeListenerSet == null) {
/* 1066 */       this.structureChangeListenerSet = new HashSet();
/*      */     }
/*      */     
/* 1069 */     synchronized (this.structureChangeListenerSet) {
/* 1070 */       this.structureChangeListenerSet.add(paramGraphStructureChangeListener);
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
/*      */   public void removeGraphStructureChangeListener(GraphStructureChangeListener paramGraphStructureChangeListener) {
/* 1084 */     if (this.structureChangeListenerSet == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1088 */     synchronized (this.structureChangeListenerSet) {
/* 1089 */       this.structureChangeListenerSet.remove(paramGraphStructureChangeListener);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void notifyStructureChangeListeners(boolean paramBoolean, Object paramObject, BranchGroup paramBranchGroup) {
/* 1098 */     if (this.structureChangeListenerSet == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1102 */     synchronized (this.structureChangeListenerSet) {
/* 1103 */       Iterator iterator = this.structureChangeListenerSet.iterator();
/* 1104 */       while (iterator.hasNext()) {
/* 1105 */         GraphStructureChangeListener graphStructureChangeListener = (GraphStructureChangeListener)iterator.next();
/*      */         try {
/* 1107 */           if (paramBoolean) {
/* 1108 */             graphStructureChangeListener.branchGroupAdded(paramObject, paramBranchGroup); continue;
/*      */           } 
/* 1110 */           graphStructureChangeListener.branchGroupRemoved(paramObject, paramBranchGroup);
/*      */         
/*      */         }
/* 1113 */         catch (RuntimeException runtimeException) {
/* 1114 */           System.err.println("Exception occurred in GraphStructureChangeListener:");
/* 1115 */           runtimeException.printStackTrace();
/*      */         }
/* 1117 */         catch (Error error) {
/*      */           
/* 1119 */           System.err.println("Error occurred in GraphStructureChangeListener:");
/* 1120 */           error.printStackTrace();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void notifyStructureChangeListeners(Object paramObject1, Object paramObject2, BranchGroup paramBranchGroup) {
/* 1131 */     if (this.structureChangeListenerSet == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1135 */     synchronized (this.structureChangeListenerSet) {
/* 1136 */       Iterator iterator = this.structureChangeListenerSet.iterator();
/* 1137 */       while (iterator.hasNext()) {
/* 1138 */         GraphStructureChangeListener graphStructureChangeListener = (GraphStructureChangeListener)iterator.next();
/*      */         try {
/* 1140 */           graphStructureChangeListener.branchGroupMoved(paramObject1, paramObject2, paramBranchGroup);
/*      */         }
/* 1142 */         catch (RuntimeException runtimeException) {
/* 1143 */           System.err.println("Exception occurred in GraphStructureChangeListener:");
/* 1144 */           runtimeException.printStackTrace();
/*      */         }
/* 1146 */         catch (Error error) {
/*      */           
/* 1148 */           System.err.println("Error occurred in GraphStructureChangeListener:");
/* 1149 */           error.printStackTrace();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addShaderErrorListener(ShaderErrorListener paramShaderErrorListener) {
/* 1175 */     if (paramShaderErrorListener == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1179 */     if (this.shaderErrorListenerSet == null) {
/* 1180 */       this.shaderErrorListenerSet = new HashSet();
/*      */     }
/*      */     
/* 1183 */     synchronized (this.shaderErrorListenerSet) {
/* 1184 */       this.shaderErrorListenerSet.add(paramShaderErrorListener);
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
/*      */   public void removeShaderErrorListener(ShaderErrorListener paramShaderErrorListener) {
/* 1199 */     if (this.shaderErrorListenerSet == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1203 */     synchronized (this.shaderErrorListenerSet) {
/* 1204 */       this.shaderErrorListenerSet.remove(paramShaderErrorListener);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void notifyShaderErrorListeners(ShaderError paramShaderError) {
/* 1213 */     boolean bool = false;
/*      */ 
/*      */     
/* 1216 */     if (this.shaderErrorListenerSet != null) {
/* 1217 */       synchronized (this.shaderErrorListenerSet) {
/* 1218 */         Iterator iterator = this.shaderErrorListenerSet.iterator();
/* 1219 */         while (iterator.hasNext()) {
/* 1220 */           ShaderErrorListener shaderErrorListener = (ShaderErrorListener)iterator.next();
/*      */           try {
/* 1222 */             shaderErrorListener.errorOccurred(paramShaderError);
/*      */           }
/* 1224 */           catch (RuntimeException runtimeException) {
/* 1225 */             System.err.println("Exception occurred in ShaderErrorListener:");
/* 1226 */             runtimeException.printStackTrace();
/*      */           }
/* 1228 */           catch (Error error) {
/*      */             
/* 1230 */             System.err.println("Error occurred in ShaderErrorListener:");
/* 1231 */             error.printStackTrace();
/*      */           } 
/* 1233 */           bool = true;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1239 */     if (!bool) {
/* 1240 */       this.defaultShaderErrorListener.errorOccurred(paramShaderError);
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
/*      */   public static void addRenderingErrorListener(RenderingErrorListener paramRenderingErrorListener) {
/* 1263 */     if (paramRenderingErrorListener == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1267 */     if (renderingErrorListenerSet == null) {
/* 1268 */       renderingErrorListenerSet = new HashSet();
/*      */     }
/*      */     
/* 1271 */     synchronized (renderingErrorListenerSet) {
/* 1272 */       renderingErrorListenerSet.add(paramRenderingErrorListener);
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
/*      */   public static void removeRenderingErrorListener(RenderingErrorListener paramRenderingErrorListener) {
/* 1287 */     if (renderingErrorListenerSet == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1291 */     synchronized (renderingErrorListenerSet) {
/* 1292 */       renderingErrorListenerSet.remove(paramRenderingErrorListener);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void notifyRenderingErrorListeners(RenderingError paramRenderingError) {
/* 1301 */     boolean bool = false;
/*      */ 
/*      */     
/* 1304 */     if (renderingErrorListenerSet != null) {
/* 1305 */       synchronized (renderingErrorListenerSet) {
/* 1306 */         Iterator iterator = renderingErrorListenerSet.iterator();
/* 1307 */         while (iterator.hasNext()) {
/* 1308 */           RenderingErrorListener renderingErrorListener = (RenderingErrorListener)iterator.next();
/*      */           try {
/* 1310 */             renderingErrorListener.errorOccurred(paramRenderingError);
/*      */           }
/* 1312 */           catch (RuntimeException runtimeException) {
/* 1313 */             System.err.println("Exception occurred in RenderingErrorListener:");
/* 1314 */             runtimeException.printStackTrace();
/*      */           }
/* 1316 */           catch (Error error) {
/*      */             
/* 1318 */             System.err.println("Error occurred in RenderingErrorListener:");
/* 1319 */             error.printStackTrace();
/*      */           } 
/* 1321 */           bool = true;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1327 */     if (!bool)
/* 1328 */       defaultRenderingErrorListener.errorOccurred(paramRenderingError); 
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\VirtualUniverse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */