/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.awt.AWTEvent;
/*      */ import java.awt.Canvas;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Frame;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.GraphicsDevice;
/*      */ import java.awt.GraphicsEnvironment;
/*      */ import java.awt.IllegalComponentStateException;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Window;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.Map;
/*      */ import javax.vecmath.Color3f;
/*      */ import javax.vecmath.Point2d;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Vector4d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Canvas3D
/*      */   extends Canvas
/*      */ {
/*      */   public static final int FIELD_LEFT = 0;
/*      */   public static final int FIELD_RIGHT = 1;
/*      */   public static final int FIELD_ALL = 2;
/*      */   static final int POLYGONATTRS_DIRTY = 1;
/*      */   static final int LINEATTRS_DIRTY = 2;
/*      */   static final int POINTATTRS_DIRTY = 4;
/*      */   static final int MATERIAL_DIRTY = 8;
/*      */   static final int TRANSPARENCYATTRS_DIRTY = 16;
/*      */   static final int COLORINGATTRS_DIRTY = 32;
/*      */   static final int LIGHTBIN_DIRTY = 64;
/*      */   static final int LIGHTENABLES_DIRTY = 128;
/*      */   static final int AMBIENTLIGHT_DIRTY = 256;
/*      */   static final int ATTRIBUTEBIN_DIRTY = 512;
/*      */   static final int TEXTUREBIN_DIRTY = 1024;
/*      */   static final int TEXTUREATTRIBUTES_DIRTY = 2048;
/*      */   static final int RENDERMOLECULE_DIRTY = 4096;
/*      */   static final int FOG_DIRTY = 8192;
/*      */   static final int MODELCLIP_DIRTY = 16384;
/*      */   static final int VIEW_MATRIX_DIRTY = 32768;
/*      */   static final int RESIZE = 1;
/*      */   static final int TOGGLEFULLSCREEN = 2;
/*      */   static final int NOCHANGE = 0;
/*      */   static final int RESETSURFACE = 1;
/*      */   static final int RECREATEDDRAW = 2;
/*      */   boolean offScreen = false;
/*      */   boolean manualRendering = false;
/*      */   Point offScreenCanvasLoc;
/*      */   Dimension offScreenCanvasSize;
/*  354 */   ImageComponent2D offScreenBuffer = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean useSharedCtx = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean stereoAvailable;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean stereoEnable = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean useStereo;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean rightStereoPass = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  385 */   int monoscopicViewPolicy = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int requestedStencilSize;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int actualStencilSize;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean userStencilAvailable;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean systemStencilAvailable;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean doubleBufferAvailable;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean doubleBufferEnable = true;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean useDoubleBuffer;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean sceneAntialiasingAvailable;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean sceneAntialiasingMultiSamplesAvailable;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean antialiasingSet = false;
/*      */ 
/*      */ 
/*      */   
/*      */   int textureColorTableSize;
/*      */ 
/*      */ 
/*      */   
/*  436 */   int numActiveTexUnit = 0;
/*      */ 
/*      */   
/*  439 */   int lastActiveTexUnit = -1;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean shadingLanguageGLSL = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean shadingLanguageCg = false;
/*      */ 
/*      */ 
/*      */   
/*      */   J3dQueryProps queryProps;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean fatalError = false;
/*      */ 
/*      */ 
/*      */   
/*  459 */   Point3d leftManualEyeInImagePlate = new Point3d(0.142D, 0.135D, 0.4572D);
/*  460 */   Point3d rightManualEyeInImagePlate = new Point3d(0.208D, 0.135D, 0.4572D);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  465 */   View view = null;
/*      */ 
/*      */ 
/*      */   
/*      */   View pendingView;
/*      */ 
/*      */ 
/*      */   
/*  473 */   CanvasViewCache canvasViewCache = null;
/*      */ 
/*      */   
/*  476 */   CanvasViewCache canvasViewCacheFrustum = null;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean raIsVisible = false;
/*      */ 
/*      */ 
/*      */   
/*  484 */   RenderAtom ra = null;
/*      */ 
/*      */ 
/*      */   
/*      */   static final int STEREO_DIRTY = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   static final int MONOSCOPIC_VIEW_POLICY_DIRTY = 2;
/*      */ 
/*      */ 
/*      */   
/*      */   static final int EYE_IN_IMAGE_PLATE_DIRTY = 4;
/*      */ 
/*      */ 
/*      */   
/*      */   static final int MOVED_OR_RESIZED_DIRTY = 8;
/*      */ 
/*      */   
/*      */   static final int BACKGROUND_DIRTY = 16;
/*      */ 
/*      */   
/*      */   static final int BACKGROUND_IMAGE_DIRTY = 32;
/*      */ 
/*      */   
/*      */   static final int VIEW_INFO_DIRTY = 63;
/*      */ 
/*      */   
/*      */   static final int RENDERER_DIRTY_IDX = 0;
/*      */ 
/*      */   
/*      */   static final int RENDER_BIN_DIRTY_IDX = 1;
/*      */ 
/*      */   
/*  518 */   int[] cvDirtyMask = new int[2];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean resizeGraphics2D = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean active = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean visible = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean ctxReset = true;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  543 */   Screen3D screen = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean imageReady = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  553 */   int fogOn = 0;
/*      */ 
/*      */ 
/*      */   
/*  557 */   GraphicsContext3D graphicsContext3D = null;
/*      */   
/*      */   boolean waiting = false;
/*      */   
/*      */   boolean swapDone = false;
/*      */   
/*      */   GraphicsConfiguration graphicsConfiguration;
/*      */   
/*  565 */   J3DGraphics2DImpl graphics2D = null;
/*      */ 
/*      */ 
/*      */   
/*  569 */   Object gfxCreationLock = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  576 */   Transform3D vworldToEc = new Transform3D();
/*      */ 
/*      */ 
/*      */   
/*      */   Transform3D vpcToEc;
/*      */ 
/*      */ 
/*      */   
/*  584 */   Drawable drawable = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  598 */   long fbConfig = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  604 */   long offScreenBufferInfo = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  612 */   static Hashtable<GraphicsConfiguration, GraphicsConfigInfo> graphicsConfigTable = new Hashtable();
/*      */ 
/*      */ 
/*      */   
/*  616 */   String nativeGraphicsVersion = "<UNKNOWN>";
/*  617 */   String nativeGraphicsVendor = "<UNKNOWN>";
/*  618 */   String nativeGraphicsRenderer = "<UNKNOWN>";
/*      */ 
/*      */   
/*      */   boolean firstPaintCalled = false;
/*      */ 
/*      */   
/*      */   boolean added = false;
/*      */ 
/*      */   
/*      */   private boolean addNotifyCalled = false;
/*      */ 
/*      */   
/*  630 */   Context ctx = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean ctxEyeLightingEnable = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  642 */   AppearanceRetained currentAppear = new AppearanceRetained();
/*      */ 
/*      */ 
/*      */   
/*  646 */   MaterialRetained currentMaterial = new MaterialRetained();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  651 */   CachedFrustum viewFrustum = new CachedFrustum();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  657 */   LightBin lightBin = null;
/*  658 */   EnvironmentSet environmentSet = null;
/*  659 */   AttributeBin attributeBin = null;
/*  660 */   ShaderBin shaderBin = null;
/*  661 */   RenderMolecule renderMolecule = null;
/*  662 */   PolygonAttributesRetained polygonAttributes = null;
/*  663 */   LineAttributesRetained lineAttributes = null;
/*  664 */   PointAttributesRetained pointAttributes = null;
/*  665 */   MaterialRetained material = null;
/*      */   boolean enableLighting = false;
/*  667 */   TransparencyAttributesRetained transparency = null;
/*  668 */   ColoringAttributesRetained coloringAttributes = null;
/*  669 */   Transform3D modelMatrix = null;
/*  670 */   Transform3D projTrans = null;
/*  671 */   TextureBin textureBin = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  677 */   LightRetained[] lights = null;
/*  678 */   int[] frameCount = null;
/*  679 */   long enableMask = -1L;
/*  680 */   FogRetained fog = null;
/*  681 */   ModelClipRetained modelClip = null;
/*  682 */   Color3f sceneAmbient = new Color3f();
/*  683 */   TextureUnitStateRetained[] texUnitState = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  688 */   TextureRetained texture = null;
/*  689 */   TextureAttributesRetained texAttrs = null;
/*  690 */   TexCoordGenerationRetained texCoordGeneration = null;
/*  691 */   RenderingAttributesRetained renderingAttrs = null;
/*  692 */   AppearanceRetained appearance = null;
/*      */   
/*  694 */   ShaderProgramRetained shaderProgram = null;
/*      */ 
/*      */   
/*  697 */   Object appHandle = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean texLinearMode = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  713 */   int canvasDirty = 65535;
/*      */ 
/*      */   
/*      */   boolean dirtyDisplayList = false;
/*      */ 
/*      */   
/*  719 */   ArrayList dirtyRenderMoleculeList = new ArrayList();
/*  720 */   ArrayList dirtyRenderAtomList = new ArrayList();
/*      */   
/*  722 */   ArrayList dirtyDlistPerRinfoList = new ArrayList();
/*      */   
/*  724 */   ArrayList displayListResourceFreeList = new ArrayList();
/*  725 */   ArrayList textureIdResourceFreeList = new ArrayList();
/*      */ 
/*      */   
/*  728 */   int canvasBit = 0;
/*      */   
/*  730 */   int canvasId = 0;
/*      */ 
/*      */   
/*      */   private boolean canvasIdAlloc = false;
/*      */   
/*  735 */   Object cvLock = new Object();
/*  736 */   Object evaluateLock = new Object();
/*  737 */   Object dirtyMaskLock = new Object();
/*      */ 
/*      */   
/*      */   boolean fullScreenMode = false;
/*      */ 
/*      */   
/*      */   int fullscreenWidth;
/*      */ 
/*      */   
/*      */   int fullscreenHeight;
/*      */ 
/*      */   
/*      */   boolean needToRebuildDisplayList = false;
/*      */   
/*  751 */   int reEvaluateCanvasCmd = 0;
/*      */   
/*      */   static final int TEXTURE_3D = 1;
/*      */   
/*      */   static final int TEXTURE_COLOR_TABLE = 2;
/*      */   
/*      */   static final int TEXTURE_MULTI_TEXTURE = 4;
/*      */   
/*      */   static final int TEXTURE_COMBINE = 8;
/*      */   
/*      */   static final int TEXTURE_COMBINE_DOT3 = 16;
/*      */   
/*      */   static final int TEXTURE_COMBINE_SUBTRACT = 32;
/*      */   
/*      */   static final int TEXTURE_REGISTER_COMBINERS = 64;
/*      */   static final int TEXTURE_CUBE_MAP = 128;
/*      */   static final int TEXTURE_SHARPEN = 256;
/*      */   static final int TEXTURE_DETAIL = 512;
/*      */   static final int TEXTURE_FILTER4 = 1024;
/*      */   static final int TEXTURE_ANISOTROPIC_FILTER = 2048;
/*      */   static final int TEXTURE_LOD_RANGE = 4096;
/*      */   static final int TEXTURE_LOD_OFFSET = 8192;
/*      */   static final int TEXTURE_LERP = 16384;
/*      */   static final int TEXTURE_NON_POWER_OF_TWO = 32768;
/*      */   static final int TEXTURE_AUTO_MIPMAP_GENERATION = 65536;
/*  776 */   int textureExtendedFeatures = 0;
/*      */ 
/*      */   
/*      */   static final int SUN_GLOBAL_ALPHA = 1;
/*      */ 
/*      */   
/*      */   static final int EXT_ABGR = 2;
/*      */ 
/*      */   
/*      */   static final int EXT_BGR = 4;
/*      */ 
/*      */   
/*      */   static final int MULTISAMPLE = 8;
/*      */ 
/*      */   
/*  791 */   int extensionsSupported = 0;
/*      */ 
/*      */   
/*  794 */   float anisotropicDegreeMax = 1.0F;
/*      */ 
/*      */   
/*  797 */   int textureBoundaryWidthMax = 0;
/*      */ 
/*      */   
/*      */   boolean multiTexAccelerated = false;
/*      */   
/*  802 */   int maxTexCoordSets = 1;
/*      */ 
/*      */   
/*  805 */   int maxTextureUnits = 1;
/*      */ 
/*      */   
/*  808 */   int maxTextureImageUnits = 0;
/*      */ 
/*      */   
/*  811 */   int maxVertexTextureImageUnits = 0;
/*      */ 
/*      */   
/*  814 */   int maxCombinedTextureImageUnits = 0;
/*      */ 
/*      */   
/*  817 */   int maxVertexAttrs = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int maxAvailableTextureUnits;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  827 */   int textureWidthMax = 0;
/*  828 */   int textureHeightMax = 0;
/*      */ 
/*      */   
/*  831 */   int texture3DWidthMax = -1;
/*  832 */   int texture3DHeightMax = -1;
/*  833 */   int texture3DDepthMax = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  841 */   Point newPosition = new Point();
/*  842 */   Dimension newSize = new Dimension();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  847 */   ArrayList textureIDResourceTable = new ArrayList(5);
/*      */   
/*      */   static final int LIGHTBIN_BIT = 0;
/*      */   
/*      */   static final int ENVIRONMENTSET_BIT = 1;
/*      */   
/*      */   static final int ATTRIBUTEBIN_BIT = 2;
/*      */   
/*      */   static final int TEXTUREBIN_BIT = 3;
/*      */   
/*      */   static final int RENDERMOLECULE_BIT = 4;
/*      */   
/*      */   static final int TRANSPARENCY_BIT = 5;
/*      */   static final int SHADERBIN_BIT = 6;
/*  861 */   int stateUpdateMask = 0;
/*      */ 
/*      */ 
/*      */   
/*  865 */   Object[] curStateToUpdate = new Object[7];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  871 */   LightRetained[] currentLights = null;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean depthBufferWriteEnableOverride = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean depthBufferEnableOverride = false;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean depthBufferWriteEnable = true;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean vfPlanesValid = false;
/*      */ 
/*      */   
/*      */   EventCatcher eventCatcher;
/*      */ 
/*      */   
/*      */   private CanvasViewEventCatcher canvasViewEventCatcher;
/*      */ 
/*      */   
/*      */   private Window windowParent;
/*      */ 
/*      */   
/*  899 */   private LinkedList<Container> containerParentList = new LinkedList();
/*      */ 
/*      */ 
/*      */   
/*      */   boolean lightChanged = false;
/*      */ 
/*      */ 
/*      */   
/*      */   DrawingSurfaceObject drawingSurfaceObject;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean validCtx = false;
/*      */ 
/*      */   
/*      */   boolean validCanvas = false;
/*      */ 
/*      */   
/*      */   boolean ctxChanged = false;
/*      */ 
/*      */   
/*  920 */   private static GraphicsConfiguration defaultGcfg = null;
/*      */   
/*      */   static int ENV_STATE_MASK;
/*      */   
/*      */   private static GraphicsConfiguration defaultGraphicsConfiguration() {
/*  925 */     if (defaultGcfg == null) {
/*  926 */       GraphicsConfigTemplate3D graphicsConfigTemplate3D = new GraphicsConfigTemplate3D();
/*  927 */       defaultGcfg = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getBestConfiguration(graphicsConfigTemplate3D);
/*      */     } 
/*      */     
/*  930 */     return defaultGcfg;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  938 */   private static boolean isValidConfig(GraphicsConfiguration paramGraphicsConfiguration) { return graphicsConfigTable.containsKey(paramGraphicsConfiguration); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static GraphicsConfiguration checkForValidGraphicsConfig(GraphicsConfiguration paramGraphicsConfiguration, boolean paramBoolean) {
/*  949 */     if (!paramBoolean && VirtualUniverse.mc.allowNullGraphicsConfig && 
/*  950 */       paramGraphicsConfiguration == null) {
/*      */ 
/*      */       
/*  953 */       System.err.println(J3dI18N.getString("Canvas3D7"));
/*  954 */       System.err.println("    " + J3dI18N.getString("Canvas3D18"));
/*      */ 
/*      */       
/*  957 */       paramGraphicsConfiguration = defaultGraphicsConfiguration();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  962 */     if (paramGraphicsConfiguration == null)
/*  963 */       throw new NullPointerException(J3dI18N.getString("Canvas3D19")); 
/*  964 */     if (!isValidConfig(paramGraphicsConfiguration)) {
/*  965 */       throw new IllegalArgumentException(J3dI18N.getString("Canvas3D17"));
/*      */     }
/*      */     
/*  968 */     return paramGraphicsConfiguration;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  974 */   private static GraphicsConfiguration getGraphicsConfig(GraphicsConfiguration paramGraphicsConfiguration) { return Pipeline.getPipeline().getGraphicsConfig(paramGraphicsConfiguration); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1006 */   public Canvas3D(GraphicsConfiguration paramGraphicsConfiguration) { this(null, checkForValidGraphicsConfig(paramGraphicsConfiguration, false), false); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1032 */   public Canvas3D(GraphicsConfiguration paramGraphicsConfiguration, boolean paramBoolean) { this(null, checkForValidGraphicsConfig(paramGraphicsConfiguration, paramBoolean), paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1042 */   private Canvas3D(Object paramObject, GraphicsConfiguration paramGraphicsConfiguration, boolean paramBoolean) { this(paramObject, paramGraphicsConfiguration, getGraphicsConfig(paramGraphicsConfiguration), paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Canvas3D(Object paramObject, GraphicsConfiguration paramGraphicsConfiguration1, GraphicsConfiguration paramGraphicsConfiguration2, boolean paramBoolean) {
/* 1058 */     super(paramGraphicsConfiguration2);
/*      */     
/* 1060 */     this.offScreen = paramBoolean;
/* 1061 */     this.graphicsConfiguration = paramGraphicsConfiguration1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1066 */     boolean bool = false;
/* 1067 */     if (this instanceof com.sun.j3d.exp.swing.impl.AutoOffScreenCanvas3D) {
/* 1068 */       bool = true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1073 */     if (bool && !paramBoolean) {
/* 1074 */       throw new IllegalArgumentException(J3dI18N.getString("Canvas3D25"));
/*      */     }
/*      */ 
/*      */     
/* 1078 */     this.cvDirtyMask[0] = 63;
/* 1079 */     this.cvDirtyMask[1] = 63;
/*      */     
/* 1081 */     GraphicsConfigInfo graphicsConfigInfo = (GraphicsConfigInfo)graphicsConfigTable.get(paramGraphicsConfiguration1);
/* 1082 */     this.requestedStencilSize = graphicsConfigInfo.getGraphicsConfigTemplate3D().getStencilSize();
/*      */     
/* 1084 */     this.fbConfig = Pipeline.getPipeline().getFbConfig(graphicsConfigInfo);
/*      */     
/* 1086 */     if (paramBoolean) {
/*      */ 
/*      */ 
/*      */       
/* 1090 */       this.manualRendering = !bool;
/*      */       
/* 1092 */       this.screen = new Screen3D(paramGraphicsConfiguration1, paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1097 */       synchronized (this.dirtyMaskLock) {
/* 1098 */         this.cvDirtyMask[0] = this.cvDirtyMask[0] | 0x8;
/* 1099 */         this.cvDirtyMask[1] = this.cvDirtyMask[1] | 0x8;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1104 */       this.firstPaintCalled = true;
/*      */       
/* 1106 */       if (this.manualRendering)
/*      */       {
/*      */ 
/*      */         
/* 1110 */         this.added = true;
/*      */       }
/*      */       
/* 1113 */       evaluateActive();
/*      */ 
/*      */ 
/*      */       
/* 1117 */       this.offScreenCanvasLoc = new Point(0, 0);
/* 1118 */       this.offScreenCanvasSize = new Dimension(0, 0);
/*      */       
/* 1120 */       setLocation(this.offScreenCanvasLoc);
/* 1121 */       setSize(this.offScreenCanvasSize);
/* 1122 */       this.newSize = this.offScreenCanvasSize;
/* 1123 */       this.newPosition = this.offScreenCanvasLoc;
/*      */ 
/*      */       
/* 1126 */       if (!this.manualRendering) {
/* 1127 */         this.eventCatcher = new EventCatcher(this);
/* 1128 */         this.canvasViewEventCatcher = new CanvasViewEventCatcher(this);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1133 */       GraphicsDevice graphicsDevice = paramGraphicsConfiguration1.getDevice();
/*      */       
/* 1135 */       this.eventCatcher = new EventCatcher(this);
/* 1136 */       this.canvasViewEventCatcher = new CanvasViewEventCatcher(this);
/*      */       
/* 1138 */       synchronized (VirtualUniverse.mc.deviceScreenMap) {
/* 1139 */         this.screen = (Screen3D)VirtualUniverse.mc.deviceScreenMap.get(graphicsDevice);
/*      */ 
/*      */         
/* 1142 */         if (this.screen == null) {
/* 1143 */           this.screen = new Screen3D(paramGraphicsConfiguration1, paramBoolean);
/* 1144 */           VirtualUniverse.mc.deviceScreenMap.put(graphicsDevice, this.screen);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1151 */     this.lights = new LightRetained[VirtualUniverse.mc.maxLights];
/* 1152 */     this.frameCount = new int[VirtualUniverse.mc.maxLights];
/* 1153 */     for (byte b = 0; b < this.frameCount.length; b++) {
/* 1154 */       this.frameCount[b] = -1;
/*      */     }
/*      */ 
/*      */     
/* 1158 */     this.drawingSurfaceObject = Pipeline.getPipeline().createDrawingSurfaceObject(this);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1163 */     GraphicsConfigTemplate3D.getGraphicsConfigFeatures(this);
/*      */     
/* 1165 */     this.useDoubleBuffer = (this.doubleBufferEnable && this.doubleBufferAvailable);
/* 1166 */     this.useStereo = (this.stereoEnable && this.stereoAvailable);
/* 1167 */     this.useSharedCtx = VirtualUniverse.mc.isSharedCtx;
/*      */ 
/*      */     
/* 1170 */     assert !((!paramBoolean && this.manualRendering) ? 1 : 0);
/*      */ 
/*      */     
/* 1173 */     assert !((paramBoolean && this.useDoubleBuffer) ? 1 : 0);
/* 1174 */     assert !((paramBoolean && this.useStereo) ? 1 : 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendEventToBehaviorScheduler(AWTEvent paramAWTEvent) {
/*      */     ViewPlatform viewPlatform;
/* 1185 */     if (this.view != null && (viewPlatform = this.view.getViewPlatform()) != null) {
/* 1186 */       VirtualUniverse virtualUniverse = ((ViewPlatformRetained)viewPlatform.retained).universe;
/*      */       
/* 1188 */       if (virtualUniverse != null) {
/* 1189 */         virtualUniverse.behaviorStructure.handleAWTEvent(paramAWTEvent);
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
/*      */   private boolean isRecursivelyVisible() {
/* 1201 */     Container container = getParent();
/* 1202 */     return (isVisible() && container != null && container.isShowing());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isIconified() {
/* 1209 */     if (this.windowParent instanceof Frame) {
/* 1210 */       return ((((Frame)this.windowParent).getExtendedState() & true) != 0);
/*      */     }
/*      */     
/* 1213 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void evaluateVisiblilty() {
/* 1219 */     boolean bool = (isRecursivelyVisible() && !isIconified()) ? 1 : 0;
/*      */ 
/*      */     
/* 1222 */     if (this.visible != bool) {
/* 1223 */       this.visible = bool;
/* 1224 */       evaluateActive();
/* 1225 */       if (bool && 
/* 1226 */         this.view != null) {
/* 1227 */         this.view.repaint();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void redraw() {
/* 1237 */     if (this.view != null && this.active && this.isRunning) {
/* 1238 */       this.view.repaint();
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
/*      */   public void paint(Graphics paramGraphics) {
/* 1251 */     if (!this.firstPaintCalled && this.added && this.validCanvas && validGraphicsMode()) {
/*      */ 
/*      */       
/*      */       try {
/* 1255 */         this.newSize = getSize();
/* 1256 */         this.newPosition = getLocationOnScreen();
/* 1257 */       } catch (IllegalComponentStateException illegalComponentStateException) {
/*      */         return;
/*      */       } 
/*      */       
/* 1261 */       synchronized (this.drawingSurfaceObject) {
/* 1262 */         this.drawingSurfaceObject.getDrawingSurfaceObjectInfo();
/*      */       } 
/*      */       
/* 1265 */       this.firstPaintCalled = true;
/* 1266 */       this.visible = true;
/* 1267 */       evaluateActive();
/*      */     } 
/* 1269 */     redraw();
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
/*      */   public void addNotify() {
/* 1283 */     if (this.addNotifyCalled) {
/*      */       return;
/*      */     }
/* 1286 */     this.addNotifyCalled = true;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1291 */     if (this.manualRendering) {
/*      */       return;
/*      */     }
/*      */     
/* 1295 */     Renderer renderer = null;
/*      */     
/* 1297 */     if (this.isRunning && this.screen != null) {
/*      */ 
/*      */ 
/*      */       
/* 1301 */       renderer = this.screen.renderer;
/* 1302 */       if (renderer != null) {
/* 1303 */         VirtualUniverse.mc.postRequest(MasterControl.STOP_RENDERER, renderer);
/* 1304 */         while (!renderer.userStop) {
/* 1305 */           MasterControl.threadYield();
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1311 */     if (!this.offScreen) {
/* 1312 */       super.addNotify();
/*      */     }
/* 1314 */     this.screen.addUser(this);
/*      */ 
/*      */ 
/*      */     
/* 1318 */     assert this.containerParentList.isEmpty();
/*      */     
/* 1320 */     this.windowParent = null;
/* 1321 */     Container container = getParent();
/* 1322 */     while (container != null) {
/* 1323 */       if (container instanceof Window) {
/* 1324 */         this.windowParent = (Window)container;
/*      */       }
/* 1326 */       container.addComponentListener(this.eventCatcher);
/* 1327 */       container.addComponentListener(this.canvasViewEventCatcher);
/* 1328 */       this.containerParentList.add(container);
/* 1329 */       container = container.getParent();
/*      */     } 
/*      */     
/* 1332 */     addComponentListener(this.eventCatcher);
/* 1333 */     addComponentListener(this.canvasViewEventCatcher);
/*      */     
/* 1335 */     if (this.windowParent != null) {
/* 1336 */       this.windowParent.addWindowListener(this.eventCatcher);
/*      */     }
/*      */     
/* 1339 */     synchronized (this.dirtyMaskLock) {
/* 1340 */       this.cvDirtyMask[0] = this.cvDirtyMask[0] | 0x8;
/* 1341 */       this.cvDirtyMask[1] = this.cvDirtyMask[1] | 0x8;
/*      */     } 
/*      */     
/* 1344 */     allocateCanvasId();
/*      */     
/* 1346 */     this.validCanvas = true;
/* 1347 */     this.added = true;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1352 */     if (this.offScreen) {
/* 1353 */       this.firstPaintCalled = true;
/* 1354 */       this.visible = true;
/* 1355 */       evaluateActive();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1360 */     if (this.isRunning && !this.fatalError) {
/* 1361 */       this.isRunningStatus = true;
/*      */     }
/*      */     
/* 1364 */     this.ctxTimeStamp = 0L;
/* 1365 */     if (this.view != null && this.view.universe != null) {
/* 1366 */       this.view.universe.checkForEnableEvents();
/*      */     }
/*      */     
/* 1369 */     if (renderer != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1374 */       VirtualUniverse.mc.postRequest(MasterControl.START_RENDERER, renderer);
/* 1375 */       while (renderer.userStop) {
/* 1376 */         MasterControl.threadYield();
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
/*      */   public void removeNotify() {
/* 1392 */     if (!this.addNotifyCalled) {
/*      */       return;
/*      */     }
/* 1395 */     this.addNotifyCalled = false;
/*      */ 
/*      */     
/* 1398 */     if (this.manualRendering) {
/*      */       return;
/*      */     }
/*      */     
/* 1402 */     Renderer renderer = null;
/*      */     
/* 1404 */     if (this.isRunning && this.screen != null) {
/*      */ 
/*      */ 
/*      */       
/* 1408 */       renderer = this.screen.renderer;
/* 1409 */       if (renderer != null) {
/* 1410 */         VirtualUniverse.mc.postRequest(MasterControl.STOP_RENDERER, renderer);
/* 1411 */         while (!renderer.userStop) {
/* 1412 */           MasterControl.threadYield();
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
/* 1425 */     synchronized (this.drawingSurfaceObject) {
/* 1426 */       this.validCtx = false;
/* 1427 */       this.validCanvas = false;
/*      */     } 
/*      */     
/* 1430 */     removeCtx();
/*      */     
/* 1432 */     Pipeline.getPipeline().freeDrawingSurface(this, this.drawingSurfaceObject);
/*      */ 
/*      */     
/* 1435 */     this.firstPaintCalled = false;
/* 1436 */     this.visible = false;
/*      */     
/* 1438 */     this.screen.removeUser(this);
/* 1439 */     evaluateActive();
/*      */     
/* 1441 */     freeCanvasId();
/*      */     
/* 1443 */     this.ra = null;
/* 1444 */     this.graphicsContext3D = null;
/*      */     
/* 1446 */     this.ctx = null;
/*      */ 
/*      */     
/* 1449 */     this.graphics2D = null;
/*      */     
/* 1451 */     super.removeNotify();
/*      */ 
/*      */     
/* 1454 */     for (Container container : this.containerParentList) {
/* 1455 */       container.removeComponentListener(this.eventCatcher);
/* 1456 */       container.removeComponentListener(this.canvasViewEventCatcher);
/*      */     } 
/* 1458 */     this.containerParentList.clear();
/* 1459 */     removeComponentListener(this.eventCatcher);
/* 1460 */     removeComponentListener(this.canvasViewEventCatcher);
/*      */     
/* 1462 */     if (this.eventCatcher != null) {
/* 1463 */       removeFocusListener(this.eventCatcher);
/* 1464 */       removeKeyListener(this.eventCatcher);
/* 1465 */       removeMouseListener(this.eventCatcher);
/* 1466 */       removeMouseMotionListener(this.eventCatcher);
/* 1467 */       removeMouseWheelListener(this.eventCatcher);
/* 1468 */       this.eventCatcher.reset();
/*      */     } 
/*      */     
/* 1471 */     if (this.windowParent != null) {
/* 1472 */       this.windowParent.removeWindowListener(this.eventCatcher);
/* 1473 */       this.windowParent.requestFocus();
/*      */     } 
/*      */     
/* 1476 */     this.added = false;
/*      */     
/* 1478 */     if (renderer != null) {
/*      */       
/* 1480 */       VirtualUniverse.mc.postRequest(MasterControl.START_RENDERER, renderer);
/* 1481 */       while (renderer.userStop) {
/* 1482 */         MasterControl.threadYield();
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1488 */     this.windowParent = null;
/*      */   }
/*      */   
/*      */   void allocateCanvasId() {
/* 1492 */     if (!this.canvasIdAlloc) {
/* 1493 */       this.canvasId = VirtualUniverse.mc.getCanvasId();
/* 1494 */       this.canvasBit = 1 << this.canvasId;
/* 1495 */       this.canvasIdAlloc = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   void freeCanvasId() {
/* 1500 */     if (this.canvasIdAlloc) {
/* 1501 */       VirtualUniverse.mc.freeCanvasId(this.canvasId);
/* 1502 */       this.canvasBit = 0;
/* 1503 */       this.canvasId = 0;
/* 1504 */       this.canvasIdAlloc = false;
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
/*      */   void evaluateActive() {
/* 1517 */     synchronized (this.evaluateLock) {
/* 1518 */       if ((this.visible || this.manualRendering) && this.firstPaintCalled) {
/*      */         
/* 1520 */         if (!this.active) {
/* 1521 */           this.active = true;
/* 1522 */           if (this.pendingView != null) {
/* 1523 */             this.pendingView.evaluateActive();
/*      */           }
/*      */         }
/* 1526 */         else if (this.pendingView != null && !this.pendingView.activeStatus) {
/*      */           
/* 1528 */           this.pendingView.evaluateActive();
/*      */         }
/*      */       
/*      */       }
/* 1532 */       else if (this.active) {
/* 1533 */         this.active = false;
/* 1534 */         if (this.view != null) {
/* 1535 */           this.view.evaluateActive();
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1541 */     if (this.view != null && !this.active) {
/* 1542 */       VirtualUniverse virtualUniverse = this.view.universe;
/* 1543 */       if (virtualUniverse != null && !virtualUniverse.isSceneGraphLock) {
/* 1544 */         virtualUniverse.waitForMC();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void setFrustumPlanes(Vector4d[] paramArrayOfVector4d) {
/* 1551 */     if (VirtualUniverse.mc.viewFrustumCulling)
/*      */     {
/* 1553 */       this.viewFrustum.set(paramArrayOfVector4d);
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
/* 1565 */   public Screen3D getScreen3D() { return this.screen; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GraphicsContext3D getGraphicsContext3D() {
/* 1577 */     synchronized (this.gfxCreationLock) {
/* 1578 */       if (this.graphicsContext3D == null) {
/* 1579 */         this.graphicsContext3D = new GraphicsContext3D(this);
/*      */       }
/*      */     } 
/* 1582 */     return this.graphicsContext3D;
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
/*      */   public J3DGraphics2D getGraphics2D() {
/* 1596 */     synchronized (this.gfxCreationLock) {
/* 1597 */       if (this.graphics2D == null) {
/* 1598 */         this.graphics2D = new J3DGraphics2DImpl(this);
/*      */       }
/*      */     } 
/* 1601 */     return this.graphics2D;
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
/*      */   public void preRender() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void postRender() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void postSwap() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderField(int paramInt) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void stopRenderer() {
/* 1701 */     if (this.manualRendering) {
/* 1702 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D14"));
/*      */     }
/* 1704 */     if (this.isRunning) {
/* 1705 */       VirtualUniverse.mc.postRequest(MasterControl.STOP_RENDERER, this);
/* 1706 */       this.isRunning = false;
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
/*      */   public final void startRenderer() {
/* 1722 */     if (this.fatalError) {
/*      */       return;
/*      */     }
/*      */     
/* 1726 */     if (!this.isRunning) {
/* 1727 */       VirtualUniverse.mc.postRequest(MasterControl.START_RENDERER, this);
/* 1728 */       this.isRunning = true;
/* 1729 */       redraw();
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
/* 1740 */   public final boolean isRendererRunning() { return this.isRunning; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1745 */   boolean isFatalError() { return this.fatalError; }
/*      */ 
/*      */ 
/*      */   
/*      */   void setFatalError() {
/* 1750 */     this.fatalError = true;
/*      */     
/* 1752 */     if (this.isRunning) {
/* 1753 */       this.isRunning = false;
/*      */       
/* 1755 */       if (!this.manualRendering) {
/* 1756 */         VirtualUniverse.mc.postRequest(MasterControl.STOP_RENDERER, this);
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
/* 1772 */   public boolean isOffScreen() { return this.offScreen; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOffScreenBuffer(ImageComponent2D paramImageComponent2D) {
/*      */     byte b2, b1;
/* 1829 */     boolean bool = false;
/*      */     
/* 1831 */     if (!this.offScreen) {
/* 1832 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D1"));
/*      */     }
/* 1834 */     if (this.offScreenRendering) {
/* 1835 */       throw new RestrictedAccessException(J3dI18N.getString("Canvas3D2"));
/*      */     }
/*      */     
/* 1838 */     J3dDebug.doAssert(!this.offScreenBufferPending, "!offScreenBufferPending");
/*      */     
/* 1840 */     if (this.offScreenBuffer != null && this.offScreenBuffer != paramImageComponent2D) {
/* 1841 */       ImageComponent2DRetained imageComponent2DRetained = (ImageComponent2DRetained)this.offScreenBuffer.retained;
/*      */       
/* 1843 */       imageComponent2DRetained.setUsedByOffScreen(false);
/*      */     } 
/*      */     
/* 1846 */     if (paramImageComponent2D != null) {
/* 1847 */       ImageComponent2DRetained imageComponent2DRetained = (ImageComponent2DRetained)paramImageComponent2D.retained;
/*      */ 
/*      */       
/* 1850 */       if (imageComponent2DRetained.byReference && !(imageComponent2DRetained.getRefImage(0) instanceof java.awt.image.BufferedImage))
/*      */       {
/*      */         
/* 1853 */         throw new IllegalArgumentException(J3dI18N.getString("Canvas3D15"));
/*      */       }
/*      */       
/* 1856 */       if (imageComponent2DRetained.getNumberOfComponents() < 3) {
/* 1857 */         throw new IllegalArgumentException(J3dI18N.getString("Canvas3D16"));
/*      */       }
/*      */       
/* 1860 */       if (paramImageComponent2D.isLive()) {
/* 1861 */         throw new IllegalSharingException(J3dI18N.getString("Canvas3D26"));
/*      */       }
/*      */       
/* 1864 */       if (imageComponent2DRetained.getInImmCtx()) {
/* 1865 */         throw new IllegalSharingException(J3dI18N.getString("Canvas3D27"));
/*      */       }
/*      */       
/* 1868 */       if (paramImageComponent2D != this.offScreenBuffer && imageComponent2DRetained.getUsedByOffScreen()) {
/* 1869 */         throw new IllegalSharingException(J3dI18N.getString("Canvas3D28"));
/*      */       }
/*      */       
/* 1872 */       imageComponent2DRetained.setUsedByOffScreen(true);
/*      */       
/* 1874 */       b1 = imageComponent2DRetained.width;
/* 1875 */       b2 = imageComponent2DRetained.height;
/*      */ 
/*      */       
/* 1878 */       if (this.manualRendering) {
/* 1879 */         sendAllocateCanvasId();
/*      */       }
/*      */     } else {
/*      */       
/* 1883 */       b1 = b2 = 0;
/*      */ 
/*      */       
/* 1886 */       if (this.manualRendering) {
/* 1887 */         bool = true;
/*      */       }
/*      */     } 
/*      */     
/* 1891 */     if (this.offScreenCanvasSize.width != b1 || this.offScreenCanvasSize.height != b2) {
/*      */ 
/*      */       
/* 1894 */       if (this.drawable != null) {
/*      */ 
/*      */         
/* 1897 */         sendDestroyCtxAndOffScreenBuffer();
/* 1898 */         this.drawable = null;
/*      */       } 
/*      */       
/* 1901 */       this.ctx = null;
/*      */ 
/*      */       
/* 1904 */       this.offScreenCanvasSize.setSize(b1, b2);
/* 1905 */       setSize(this.offScreenCanvasSize);
/*      */       
/* 1907 */       if (b1 > 0 && b2 > 0) {
/* 1908 */         sendCreateOffScreenBuffer();
/*      */       
/*      */       }
/*      */     }
/* 1912 */     else if (this.ctx != null) {
/* 1913 */       removeCtx();
/*      */     } 
/*      */     
/* 1916 */     if (bool) {
/* 1917 */       sendFreeCanvasId();
/*      */     }
/*      */     
/* 1920 */     this.offScreenBuffer = paramImageComponent2D;
/*      */     
/* 1922 */     synchronized (this.dirtyMaskLock) {
/* 1923 */       this.cvDirtyMask[0] = this.cvDirtyMask[0] | 0x8;
/* 1924 */       this.cvDirtyMask[1] = this.cvDirtyMask[1] | 0x8;
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
/*      */   public ImageComponent2D getOffScreenBuffer() {
/* 1940 */     if (!this.offScreen) {
/* 1941 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D1"));
/*      */     }
/* 1943 */     return this.offScreenBuffer;
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
/*      */   public void renderOffScreenBuffer() {
/* 1979 */     if (!this.offScreen) {
/* 1980 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D1"));
/*      */     }
/*      */     
/* 1983 */     if (!this.manualRendering) {
/* 1984 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D24"));
/*      */     }
/*      */     
/* 1987 */     if (this.fatalError) {
/* 1988 */       throw new IllegalRenderingStateException(J3dI18N.getString("Canvas3D30"));
/*      */     }
/*      */     
/* 1991 */     if (this.offScreenBuffer == null) {
/* 1992 */       throw new NullPointerException(J3dI18N.getString("Canvas3D10"));
/*      */     }
/* 1994 */     Dimension dimension = this.screen.getSize();
/*      */     
/* 1996 */     if (dimension.width <= 0) {
/* 1997 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D8"));
/*      */     }
/* 1999 */     if (dimension.height <= 0) {
/* 2000 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D9"));
/*      */     }
/* 2002 */     if (this.screen.getPhysicalScreenWidth() <= 0.0D) {
/* 2003 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D12"));
/*      */     }
/* 2005 */     if (this.screen.getPhysicalScreenHeight() <= 0.0D) {
/* 2006 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D13"));
/*      */     }
/* 2008 */     if (this.offScreenRendering) {
/* 2009 */       throw new RestrictedAccessException(J3dI18N.getString("Canvas3D2"));
/*      */     }
/* 2011 */     if (!this.isRunning) {
/* 2012 */       throw new RestrictedAccessException(J3dI18N.getString("Canvas3D11"));
/*      */     }
/*      */     
/* 2015 */     if (!this.active || this.pendingView == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2024 */     this.offScreenRendering = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2030 */     boolean bool = false;
/*      */     try {
/* 2032 */       bool = this.view.inCanvasCallback;
/*      */     }
/* 2034 */     catch (NullPointerException nullPointerException) {}
/*      */ 
/*      */ 
/*      */     
/* 2038 */     if (bool) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2044 */       if (this.screen.renderer == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2050 */         this.screen; this.screen.renderer = (Renderer)Screen3D.deviceRendererMap.get(this.screen.graphicsDevice);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2060 */       if (Thread.currentThread() == this.screen.renderer)
/*      */       {
/* 2062 */         J3dMessage j3dMessage = new J3dMessage();
/* 2063 */         j3dMessage.threads = 16;
/* 2064 */         j3dMessage.type = 42;
/* 2065 */         j3dMessage.universe = this.view.universe;
/* 2066 */         j3dMessage.view = this.view;
/* 2067 */         j3dMessage.args[0] = this;
/*      */         
/* 2069 */         this.screen.renderer.rendererStructure.addMessage(j3dMessage);
/*      */ 
/*      */         
/* 2072 */         this.screen.renderer.args = new Object[4];
/* 2073 */         (Object[])this.screen.renderer.args[0] = new Integer(2);
/*      */         
/* 2075 */         (Object[])this.screen.renderer.args[1] = this;
/* 2076 */         (Object[])this.screen.renderer.args[2] = this.view;
/*      */ 
/*      */         
/* 2079 */         (Object[])this.screen.renderer.args[3] = null;
/*      */ 
/*      */ 
/*      */         
/* 2083 */         this.screen.renderer.doWork(0L);
/*      */       
/*      */       }
/*      */       else
/*      */       {
/*      */         
/* 2089 */         J3dMessage j3dMessage = new J3dMessage();
/* 2090 */         j3dMessage.threads = 16;
/* 2091 */         j3dMessage.type = 42;
/* 2092 */         j3dMessage.universe = this.view.universe;
/* 2093 */         j3dMessage.view = this.view;
/* 2094 */         j3dMessage.args[0] = this;
/* 2095 */         this.screen.renderer.rendererStructure.addMessage(j3dMessage);
/* 2096 */         VirtualUniverse.mc.setWorkForRequestRenderer();
/*      */       }
/*      */     
/* 2099 */     } else if (Thread.currentThread() instanceof BehaviorScheduler) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2107 */       J3dMessage j3dMessage = new J3dMessage();
/* 2108 */       j3dMessage.threads = 16;
/* 2109 */       j3dMessage.type = 42;
/* 2110 */       j3dMessage.universe = this.view.universe;
/* 2111 */       j3dMessage.view = this.view;
/* 2112 */       j3dMessage.args[0] = this;
/* 2113 */       this.screen.renderer.rendererStructure.addMessage(j3dMessage);
/* 2114 */       VirtualUniverse.mc.setWorkForRequestRenderer();
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 2120 */       J3dMessage j3dMessage = new J3dMessage();
/* 2121 */       j3dMessage.threads = 128;
/* 2122 */       j3dMessage.type = 42;
/* 2123 */       j3dMessage.universe = this.pendingView.universe;
/* 2124 */       j3dMessage.view = this.pendingView;
/* 2125 */       j3dMessage.args[0] = this;
/* 2126 */       j3dMessage.args[1] = this.offScreenBuffer;
/* 2127 */       VirtualUniverse.mc.processMessage(j3dMessage);
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
/*      */   public void waitForOffScreenRendering() {
/* 2152 */     if (!this.offScreen) {
/* 2153 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D1"));
/*      */     }
/*      */     
/* 2156 */     if (Thread.currentThread() instanceof Renderer) {
/* 2157 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D31"));
/*      */     }
/*      */     
/* 2160 */     while (this.offScreenRendering) {
/* 2161 */       MasterControl.threadYield();
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
/*      */   public void setOffScreenLocation(int paramInt1, int paramInt2) {
/* 2186 */     if (!this.offScreen) {
/* 2187 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D1"));
/*      */     }
/* 2189 */     synchronized (this.cvLock) {
/* 2190 */       this.offScreenCanvasLoc.setLocation(paramInt1, paramInt2);
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
/*      */   public void setOffScreenLocation(Point paramPoint) {
/* 2213 */     if (!this.offScreen) {
/* 2214 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D1"));
/*      */     }
/* 2216 */     synchronized (this.cvLock) {
/* 2217 */       this.offScreenCanvasLoc.setLocation(paramPoint);
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
/*      */   public Point getOffScreenLocation() {
/* 2239 */     if (!this.offScreen) {
/* 2240 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D1"));
/*      */     }
/* 2242 */     return new Point(this.offScreenCanvasLoc);
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
/*      */   public Point getOffScreenLocation(Point paramPoint) {
/* 2270 */     if (!this.offScreen) {
/* 2271 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D1"));
/*      */     }
/* 2273 */     if (paramPoint == null) {
/* 2274 */       return new Point(this.offScreenCanvasLoc);
/*      */     }
/*      */     
/* 2277 */     paramPoint.setLocation(this.offScreenCanvasLoc);
/* 2278 */     return paramPoint;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void endOffScreenRendering() {
/* 2284 */     ImageComponent2DRetained imageComponent2DRetained = (ImageComponent2DRetained)this.offScreenBuffer.retained;
/* 2285 */     boolean bool1 = imageComponent2DRetained.isByReference();
/* 2286 */     boolean bool2 = imageComponent2DRetained.isYUp();
/* 2287 */     ImageComponentRetained.ImageData imageData = imageComponent2DRetained.getImageData(false);
/*      */     
/* 2289 */     if (!bool1) {
/*      */       
/* 2291 */       if (imageData == null) {
/* 2292 */         assert !bool1;
/* 2293 */         imageComponent2DRetained.createBlankImageData();
/* 2294 */         imageData = imageComponent2DRetained.getImageData(false);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 2299 */         imageComponent2DRetained.evaluateExtensions(this);
/*      */       } 
/*      */       
/* 2302 */       readOffScreenBuffer(this.ctx, imageComponent2DRetained.getImageFormatTypeIntValue(false), imageComponent2DRetained.getImageDataTypeIntValue(), imageData.get(), this.offScreenCanvasSize.width, this.offScreenCanvasSize.height);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 2307 */       imageComponent2DRetained.geomLock.getLock();
/*      */       
/* 2309 */       imageComponent2DRetained.evaluateExtensions(this);
/*      */ 
/*      */       
/* 2312 */       readOffScreenBuffer(this.ctx, imageComponent2DRetained.getImageFormatTypeIntValue(false), imageComponent2DRetained.getImageDataTypeIntValue(), imageData.get(), this.offScreenCanvasSize.width, this.offScreenCanvasSize.height);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2318 */       if (!imageData.isDataByRef()) {
/* 2319 */         if (imageComponent2DRetained.isImageTypeSupported()) {
/* 2320 */           imageComponent2DRetained.copyToRefImage(0);
/*      */         } else {
/*      */           
/* 2323 */           imageComponent2DRetained.copyToRefImageWithFormatConversion(0);
/*      */         } 
/*      */       }
/*      */       
/* 2327 */       imageComponent2DRetained.geomLock.unLock();
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
/*      */   public void swap() {
/* 2349 */     if (this.offScreen) {
/* 2350 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D14"));
/*      */     }
/* 2352 */     if (this.isRunning) {
/* 2353 */       throw new RestrictedAccessException(J3dI18N.getString("Canvas3D0"));
/*      */     }
/* 2355 */     if (!this.firstPaintCalled) {
/*      */       return;
/*      */     }
/*      */     
/* 2359 */     if (this.view != null && this.graphicsContext3D != null) {
/* 2360 */       if (this.view.universe != null && Thread.currentThread() == this.view.universe.behaviorScheduler) {
/*      */         
/* 2362 */         this.graphicsContext3D.sendRenderMessage(false, 2, null, null);
/*      */       } else {
/* 2364 */         this.graphicsContext3D.sendRenderMessage(true, 2, null, null);
/*      */       } 
/* 2366 */       this.graphicsContext3D.runMonitor(0);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doSwap() {
/* 2372 */     if (this.firstPaintCalled && this.useDoubleBuffer) {
/*      */       try {
/* 2374 */         if (this.validCtx && this.ctx != null && this.view != null) {
/* 2375 */           synchronized (this.drawingSurfaceObject) {
/* 2376 */             if (this.validCtx) {
/* 2377 */               if (!this.drawingSurfaceObject.renderLock()) {
/* 2378 */                 this.graphicsContext3D.runMonitor(2);
/*      */                 return;
/*      */               } 
/* 2381 */               syncRender(this.ctx, true);
/* 2382 */               int i = swapBuffers(this.ctx, this.screen.display, this.drawable);
/* 2383 */               if (i != 0) {
/* 2384 */                 resetImmediateRendering(i);
/*      */               }
/* 2386 */               this.drawingSurfaceObject.unLock();
/*      */             } 
/*      */           } 
/*      */         }
/* 2390 */       } catch (NullPointerException nullPointerException) {
/* 2391 */         this.drawingSurfaceObject.unLock();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2396 */     this.view.universe.behaviorStructure.incElapsedFrames();
/*      */     
/* 2398 */     if (this.reEvaluateCanvasCmd != 0) {
/*      */       int i;
/*      */       
/* 2401 */       this.antialiasingSet = false;
/* 2402 */       if (this.reEvaluateCanvasCmd == 1) {
/* 2403 */         assert VirtualUniverse.mc.isD3D();
/* 2404 */         i = resizeD3DCanvas(this.ctx);
/*      */       } else {
/* 2406 */         i = toggleFullScreenMode(this.ctx);
/*      */       } 
/*      */       
/* 2409 */       if (i != 0) {
/* 2410 */         resetImmediateRendering(i);
/*      */       }
/* 2412 */       this.reEvaluateCanvasCmd = 0;
/*      */     } 
/* 2414 */     this.graphicsContext3D.runMonitor(2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Context createNewContext(Context paramContext, boolean paramBoolean) {
/* 2421 */     VirtualUniverse.mc; VirtualUniverse.mc; Context context = createNewContext(this.screen.display, this.drawable, this.fbConfig, paramContext, paramBoolean, this.offScreen, MasterControl.glslLibraryAvailable, MasterControl.cgLibraryAvailable);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2429 */     this.maxAvailableTextureUnits = Math.max(this.maxTextureUnits, this.maxTextureImageUnits);
/*      */     
/* 2431 */     return context;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2438 */   final void makeCtxCurrent() { makeCtxCurrent(this.ctx, this.screen.display, this.drawable); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2445 */   final void makeCtxCurrent(Context paramContext) { makeCtxCurrent(paramContext, this.screen.display, this.drawable); }
/*      */ 
/*      */   
/*      */   final void makeCtxCurrent(Context paramContext, long paramLong, Drawable paramDrawable) {
/* 2449 */     if (paramContext != this.screen.renderer.currentCtx || paramDrawable != this.screen.renderer.currentDrawable) {
/* 2450 */       if (!this.drawingSurfaceObject.isLocked()) {
/* 2451 */         this.drawingSurfaceObject.renderLock();
/* 2452 */         useCtx(paramContext, paramLong, paramDrawable);
/* 2453 */         this.drawingSurfaceObject.unLock();
/*      */       } else {
/* 2455 */         useCtx(paramContext, paramLong, paramDrawable);
/*      */       } 
/* 2457 */       this.screen.renderer.currentCtx = paramContext;
/* 2458 */       this.screen.renderer.currentDrawable = paramDrawable;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void releaseCtx() {
/* 2465 */     if (this.screen.renderer.currentCtx != null) {
/* 2466 */       boolean bool = !this.drawingSurfaceObject.isLocked() ? 1 : 0;
/* 2467 */       if (bool) {
/* 2468 */         this.drawingSurfaceObject.renderLock();
/*      */       }
/* 2470 */       if (releaseCtx(this.screen.renderer.currentCtx, this.screen.display)) {
/* 2471 */         this.screen.renderer.currentCtx = null;
/* 2472 */         this.screen.renderer.currentDrawable = null;
/*      */       } 
/* 2474 */       if (bool) {
/* 2475 */         this.drawingSurfaceObject.unLock();
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
/*      */   public void setLeftManualEyeInImagePlate(Point3d paramPoint3d) {
/* 2495 */     this.leftManualEyeInImagePlate.set(paramPoint3d);
/* 2496 */     synchronized (this.dirtyMaskLock) {
/* 2497 */       this.cvDirtyMask[0] = this.cvDirtyMask[0] | 0x4;
/* 2498 */       this.cvDirtyMask[1] = this.cvDirtyMask[1] | 0x4;
/*      */     } 
/* 2500 */     redraw();
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
/*      */   public void setRightManualEyeInImagePlate(Point3d paramPoint3d) {
/* 2517 */     this.rightManualEyeInImagePlate.set(paramPoint3d);
/* 2518 */     synchronized (this.dirtyMaskLock) {
/* 2519 */       this.cvDirtyMask[0] = this.cvDirtyMask[0] | 0x4;
/* 2520 */       this.cvDirtyMask[1] = this.cvDirtyMask[1] | 0x4;
/*      */     } 
/* 2522 */     redraw();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2532 */   public void getLeftManualEyeInImagePlate(Point3d paramPoint3d) { paramPoint3d.set(this.leftManualEyeInImagePlate); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2542 */   public void getRightManualEyeInImagePlate(Point3d paramPoint3d) { paramPoint3d.set(this.rightManualEyeInImagePlate); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getLeftEyeInImagePlate(Point3d paramPoint3d) {
/* 2554 */     if (this.canvasViewCache != null) {
/* 2555 */       synchronized (this.canvasViewCache) {
/* 2556 */         paramPoint3d.set(this.canvasViewCache.getLeftEyeInImagePlate());
/*      */       } 
/*      */     } else {
/*      */       
/* 2560 */       paramPoint3d.set(this.leftManualEyeInImagePlate);
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
/*      */   public void getRightEyeInImagePlate(Point3d paramPoint3d) {
/* 2573 */     if (this.canvasViewCache != null) {
/* 2574 */       synchronized (this.canvasViewCache) {
/* 2575 */         paramPoint3d.set(this.canvasViewCache.getRightEyeInImagePlate());
/*      */       } 
/*      */     } else {
/*      */       
/* 2579 */       paramPoint3d.set(this.rightManualEyeInImagePlate);
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
/*      */   public void getCenterEyeInImagePlate(Point3d paramPoint3d) {
/* 2596 */     if (this.canvasViewCache != null) {
/* 2597 */       synchronized (this.canvasViewCache) {
/* 2598 */         paramPoint3d.set(this.canvasViewCache.getCenterEyeInImagePlate());
/*      */       } 
/*      */     } else {
/*      */       
/* 2602 */       Point3d point3d = new Point3d();
/* 2603 */       point3d.add(this.leftManualEyeInImagePlate, this.rightManualEyeInImagePlate);
/* 2604 */       point3d.scale(0.5D);
/* 2605 */       paramPoint3d.set(point3d);
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
/*      */   public void getImagePlateToVworld(Transform3D paramTransform3D) {
/* 2617 */     if (this.canvasViewCache != null) {
/* 2618 */       synchronized (this.canvasViewCache) {
/* 2619 */         paramTransform3D.set(this.canvasViewCache.getImagePlateToVworld());
/*      */       } 
/*      */     } else {
/*      */       
/* 2623 */       paramTransform3D.setIdentity();
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
/*      */   public void getPixelLocationInImagePlate(int paramInt1, int paramInt2, Point3d paramPoint3d) {
/* 2643 */     if (this.canvasViewCache != null) {
/* 2644 */       synchronized (this.canvasViewCache) {
/* 2645 */         paramPoint3d.x = this.canvasViewCache.getWindowXInImagePlate(paramInt1);
/*      */         
/* 2647 */         paramPoint3d.y = this.canvasViewCache.getWindowYInImagePlate(paramInt2);
/*      */         
/* 2649 */         paramPoint3d.z = 0.0D;
/*      */       } 
/*      */     } else {
/* 2652 */       paramPoint3d.set(0.0D, 0.0D, 0.0D);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void getPixelLocationInImagePlate(double paramDouble1, double paramDouble2, double paramDouble3, Point3d paramPoint3d) {
/* 2659 */     if (this.canvasViewCache != null) {
/* 2660 */       synchronized (this.canvasViewCache) {
/* 2661 */         this.canvasViewCache.getPixelLocationInImagePlate(paramDouble1, paramDouble2, paramDouble3, paramPoint3d);
/*      */       } 
/*      */     } else {
/*      */       
/* 2665 */       paramPoint3d.set(0.0D, 0.0D, 0.0D);
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
/*      */   public void getPixelLocationInImagePlate(Point2d paramPoint2d, Point3d paramPoint3d) {
/* 2686 */     if (this.canvasViewCache != null) {
/* 2687 */       synchronized (this.canvasViewCache) {
/* 2688 */         paramPoint3d.x = this.canvasViewCache.getWindowXInImagePlate(paramPoint2d.x);
/*      */         
/* 2690 */         paramPoint3d.y = this.canvasViewCache.getWindowYInImagePlate(paramPoint2d.y);
/*      */         
/* 2692 */         paramPoint3d.z = 0.0D;
/*      */       } 
/*      */     } else {
/*      */       
/* 2696 */       paramPoint3d.set(0.0D, 0.0D, 0.0D);
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
/*      */   public void getPixelLocationFromImagePlate(Point3d paramPoint3d, Point2d paramPoint2d) {
/* 2716 */     if (this.canvasViewCache != null) {
/* 2717 */       synchronized (this.canvasViewCache) {
/* 2718 */         this.canvasViewCache.getPixelLocationFromImagePlate(paramPoint3d, paramPoint2d);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 2723 */       paramPoint2d.set(0.0D, 0.0D);
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
/*      */   public void getVworldProjection(Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 2747 */     if (this.canvasViewCache != null) {
/* 2748 */       ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)(this.view.getViewPlatform()).retained;
/*      */ 
/*      */       
/* 2751 */       synchronized (this.canvasViewCache) {
/* 2752 */         paramTransform3D1.mul(this.canvasViewCache.getLeftProjection(), this.canvasViewCache.getLeftVpcToEc());
/*      */         
/* 2754 */         paramTransform3D1.mul(viewPlatformRetained.getVworldToVpc());
/*      */ 
/*      */ 
/*      */         
/* 2758 */         if (this.useStereo) {
/* 2759 */           paramTransform3D2.mul(this.canvasViewCache.getRightProjection(), this.canvasViewCache.getRightVpcToEc());
/*      */           
/* 2761 */           paramTransform3D2.mul(viewPlatformRetained.getVworldToVpc());
/*      */         } else {
/*      */           
/* 2764 */           paramTransform3D2.set(paramTransform3D1);
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/* 2769 */       paramTransform3D1.setIdentity();
/* 2770 */       paramTransform3D2.setIdentity();
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
/*      */   public void getInverseVworldProjection(Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 2795 */     if (this.canvasViewCache != null) {
/* 2796 */       ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)(this.view.getViewPlatform()).retained;
/*      */ 
/*      */       
/* 2799 */       synchronized (this.canvasViewCache) {
/* 2800 */         paramTransform3D1.set(this.canvasViewCache.getLeftCcToVworld());
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2805 */         if (this.useStereo) {
/* 2806 */           paramTransform3D2.set(this.canvasViewCache.getRightCcToVworld());
/*      */         }
/*      */         else {
/*      */           
/* 2810 */           paramTransform3D2.set(paramTransform3D1);
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 2816 */       paramTransform3D1.setIdentity();
/* 2817 */       paramTransform3D2.setIdentity();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getPhysicalWidth() {
/* 2827 */     double d = 0.0D;
/*      */     
/* 2829 */     if (this.canvasViewCache != null) {
/* 2830 */       synchronized (this.canvasViewCache) {
/* 2831 */         d = this.canvasViewCache.getPhysicalWindowWidth();
/*      */       } 
/*      */     }
/*      */     
/* 2835 */     return d;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getPhysicalHeight() {
/* 2843 */     double d = 0.0D;
/*      */     
/* 2845 */     if (this.canvasViewCache != null) {
/* 2846 */       synchronized (this.canvasViewCache) {
/* 2847 */         d = this.canvasViewCache.getPhysicalWindowHeight();
/*      */       } 
/*      */     }
/*      */     
/* 2851 */     return d;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getVworldToImagePlate(Transform3D paramTransform3D) {
/* 2862 */     if (this.canvasViewCache != null) {
/* 2863 */       synchronized (this.canvasViewCache) {
/* 2864 */         paramTransform3D.set(this.canvasViewCache.getVworldToImagePlate());
/*      */       } 
/*      */     } else {
/*      */       
/* 2868 */       paramTransform3D.setIdentity();
/*      */     } 
/*      */   }
/*      */   
/*      */   void getLastVworldToImagePlate(Transform3D paramTransform3D) {
/* 2873 */     if (this.canvasViewCache != null) {
/* 2874 */       synchronized (this.canvasViewCache) {
/* 2875 */         paramTransform3D.set(this.canvasViewCache.getLastVworldToImagePlate());
/*      */       } 
/*      */     } else {
/*      */       
/* 2879 */       paramTransform3D.setIdentity();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setView(View paramView) {
/* 2888 */     this.pendingView = paramView;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2895 */     VirtualUniverse.mc.postRequest(MasterControl.SET_VIEW, this);
/* 2896 */     evaluateActive();
/*      */   }
/*      */   
/*      */   void computeViewCache() {
/* 2900 */     synchronized (this.cvLock) {
/* 2901 */       if (this.view == null) {
/* 2902 */         this.canvasViewCache = null;
/* 2903 */         this.canvasViewCacheFrustum = null;
/*      */       } else {
/*      */         
/* 2906 */         this.canvasViewCache = new CanvasViewCache(this, this.screen.screenViewCache, this.view.viewCache);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2911 */         this.canvasViewCacheFrustum = new CanvasViewCache(this, this.screen.screenViewCache, this.view.viewCache);
/*      */ 
/*      */         
/* 2914 */         synchronized (this.dirtyMaskLock) {
/* 2915 */           this.cvDirtyMask[0] = 63;
/* 2916 */           this.cvDirtyMask[1] = 63;
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
/* 2927 */   public View getView() { return this.pendingView; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2945 */   public boolean getStereoAvailable() { return ((Boolean)queryProperties().get("stereoAvailable")).booleanValue(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStereoEnable(boolean paramBoolean) {
/* 2958 */     this.stereoEnable = paramBoolean;
/* 2959 */     this.useStereo = (this.stereoEnable && this.stereoAvailable);
/* 2960 */     synchronized (this.dirtyMaskLock) {
/* 2961 */       this.cvDirtyMask[0] = this.cvDirtyMask[0] | true;
/* 2962 */       this.cvDirtyMask[1] = this.cvDirtyMask[1] | true;
/*      */     } 
/* 2964 */     redraw();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2973 */   public boolean getStereoEnable() { return this.stereoEnable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMonoscopicViewPolicy(int paramInt) {
/* 3005 */     if (this.view != null && this.view.viewPolicy == 1 && this.monoscopicViewPolicy == 2 && !this.useStereo)
/*      */     {
/*      */       
/* 3008 */       throw new IllegalStateException(J3dI18N.getString("View31"));
/*      */     }
/*      */ 
/*      */     
/* 3012 */     this.monoscopicViewPolicy = paramInt;
/* 3013 */     synchronized (this.dirtyMaskLock) {
/* 3014 */       this.cvDirtyMask[0] = this.cvDirtyMask[0] | 0x2;
/* 3015 */       this.cvDirtyMask[1] = this.cvDirtyMask[1] | 0x2;
/*      */     } 
/* 3017 */     redraw();
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
/* 3029 */   public int getMonoscopicViewPolicy() { return this.monoscopicViewPolicy; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3048 */   public boolean getDoubleBufferAvailable() { return ((Boolean)queryProperties().get("doubleBufferAvailable")).booleanValue(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDoubleBufferEnable(boolean paramBoolean) {
/* 3065 */     this.doubleBufferEnable = paramBoolean;
/* 3066 */     this.useDoubleBuffer = (this.doubleBufferEnable && this.doubleBufferAvailable);
/* 3067 */     if (Thread.currentThread() == this.screen.renderer) {
/* 3068 */       setRenderMode(this.ctx, 2, this.useDoubleBuffer);
/*      */     }
/* 3070 */     redraw();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3079 */   public boolean getDoubleBufferEnable() { return this.doubleBufferEnable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3097 */   public boolean getSceneAntialiasingAvailable() { return ((Boolean)queryProperties().get("sceneAntialiasingAvailable")).booleanValue(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isShadingLanguageSupported(int paramInt) {
/* 3118 */     queryProperties();
/*      */ 
/*      */     
/* 3121 */     switch (paramInt) {
/*      */       case 1:
/* 3123 */         return this.shadingLanguageGLSL;
/*      */       case 2:
/* 3125 */         return this.shadingLanguageCg;
/*      */     } 
/*      */     
/* 3128 */     return false;
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
/*      */   public final Map queryProperties() {
/* 3630 */     if (this.queryProps == null) {
/* 3631 */       boolean bool = false;
/*      */       
/* 3633 */       synchronized (VirtualUniverse.mc.contextCreationLock) {
/* 3634 */         if (this.ctx == null) {
/* 3635 */           bool = true;
/*      */         }
/*      */       } 
/*      */       
/* 3639 */       if (bool) {
/* 3640 */         GraphicsConfigTemplate3D.setQueryProps(this);
/*      */       }
/*      */ 
/*      */       
/* 3644 */       createQueryProps();
/*      */     } 
/*      */     
/* 3647 */     if (this.fatalError) {
/* 3648 */       throw new IllegalStateException(J3dI18N.getString("Canvas3D29"));
/*      */     }
/*      */     
/* 3651 */     return this.queryProps;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void createQueryContext() {
/* 3659 */     VirtualUniverse.mc; VirtualUniverse.mc; createQueryContext(this.screen.display, this.drawable, this.fbConfig, this.offScreen, 1, 1, MasterControl.glslLibraryAvailable, MasterControl.cgLibraryAvailable);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3664 */     this.maxAvailableTextureUnits = Math.max(this.maxTextureUnits, this.maxTextureImageUnits);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void createQueryProps() {
/* 3672 */     ArrayList arrayList1 = new ArrayList();
/* 3673 */     ArrayList arrayList2 = new ArrayList();
/* 3674 */     byte b = 0;
/*      */ 
/*      */     
/* 3677 */     arrayList1.add("doubleBufferAvailable");
/* 3678 */     arrayList2.add(new Boolean(this.doubleBufferAvailable));
/*      */     
/* 3680 */     arrayList1.add("stereoAvailable");
/* 3681 */     arrayList2.add(new Boolean(this.stereoAvailable));
/*      */     
/* 3683 */     arrayList1.add("sceneAntialiasingAvailable");
/* 3684 */     arrayList2.add(new Boolean(this.sceneAntialiasingAvailable));
/*      */     
/* 3686 */     arrayList1.add("sceneAntialiasingNumPasses");
/*      */     
/* 3688 */     if (this.sceneAntialiasingAvailable) {
/* 3689 */       b = this.sceneAntialiasingMultiSamplesAvailable ? 1 : 8;
/*      */     }
/*      */     
/* 3692 */     arrayList2.add(new Integer(b));
/*      */     
/* 3694 */     arrayList1.add("stencilSize");
/*      */ 
/*      */     
/* 3697 */     if (this.userStencilAvailable) {
/* 3698 */       arrayList2.add(new Integer(this.actualStencilSize));
/*      */     } else {
/* 3700 */       arrayList2.add(new Integer(0));
/*      */     } 
/*      */     
/* 3703 */     arrayList1.add("compressedGeometry.majorVersionNumber");
/* 3704 */     arrayList2.add(new Integer(1));
/* 3705 */     arrayList1.add("compressedGeometry.minorVersionNumber");
/* 3706 */     arrayList2.add(new Integer(0));
/* 3707 */     arrayList1.add("compressedGeometry.minorMinorVersionNumber");
/* 3708 */     arrayList2.add(new Integer(2));
/*      */ 
/*      */     
/* 3711 */     arrayList1.add("texture3DAvailable");
/* 3712 */     arrayList2.add(new Boolean(((this.textureExtendedFeatures & true) != 0)));
/*      */     
/* 3714 */     arrayList1.add("textureColorTableSize");
/* 3715 */     arrayList2.add(new Integer(this.textureColorTableSize));
/*      */     
/* 3717 */     arrayList1.add("textureEnvCombineAvailable");
/* 3718 */     arrayList2.add(new Boolean(((this.textureExtendedFeatures & 0x8) != 0)));
/*      */ 
/*      */     
/* 3721 */     arrayList1.add("textureCombineDot3Available");
/* 3722 */     arrayList2.add(new Boolean(((this.textureExtendedFeatures & 0x10) != 0)));
/*      */ 
/*      */     
/* 3725 */     arrayList1.add("textureCombineSubtractAvailable");
/* 3726 */     arrayList2.add(new Boolean(((this.textureExtendedFeatures & 0x20) != 0)));
/*      */ 
/*      */     
/* 3729 */     arrayList1.add("textureCubeMapAvailable");
/* 3730 */     arrayList2.add(new Boolean(((this.textureExtendedFeatures & 0x80) != 0)));
/*      */ 
/*      */     
/* 3733 */     arrayList1.add("textureSharpenAvailable");
/* 3734 */     arrayList2.add(new Boolean(((this.textureExtendedFeatures & 0x100) != 0)));
/*      */ 
/*      */     
/* 3737 */     arrayList1.add("textureDetailAvailable");
/* 3738 */     arrayList2.add(new Boolean(((this.textureExtendedFeatures & 0x200) != 0)));
/*      */ 
/*      */     
/* 3741 */     arrayList1.add("textureFilter4Available");
/* 3742 */     arrayList2.add(new Boolean(((this.textureExtendedFeatures & 0x400) != 0)));
/*      */ 
/*      */     
/* 3745 */     arrayList1.add("textureAnisotropicFilterDegreeMax");
/* 3746 */     arrayList2.add(new Float(this.anisotropicDegreeMax));
/*      */     
/* 3748 */     arrayList1.add("textureWidthMax");
/* 3749 */     arrayList2.add(new Integer(this.textureWidthMax));
/*      */     
/* 3751 */     arrayList1.add("textureHeightMax");
/* 3752 */     arrayList2.add(new Integer(this.textureHeightMax));
/*      */     
/* 3754 */     arrayList1.add("texture3DWidthMax");
/* 3755 */     arrayList2.add(new Integer(this.texture3DWidthMax));
/*      */     
/* 3757 */     arrayList1.add("texture3DHeightMax");
/* 3758 */     arrayList2.add(new Integer(this.texture3DHeightMax));
/*      */     
/* 3760 */     arrayList1.add("texture3DDepthMax");
/* 3761 */     arrayList2.add(new Integer(this.texture3DDepthMax));
/*      */     
/* 3763 */     arrayList1.add("textureBoundaryWidthMax");
/* 3764 */     arrayList2.add(new Integer(this.textureBoundaryWidthMax));
/*      */     
/* 3766 */     arrayList1.add("textureLodRangeAvailable");
/* 3767 */     arrayList2.add(new Boolean(((this.textureExtendedFeatures & 0x1000) != 0)));
/*      */ 
/*      */     
/* 3770 */     arrayList1.add("textureLodOffsetAvailable");
/* 3771 */     arrayList2.add(new Boolean(((this.textureExtendedFeatures & 0x2000) != 0)));
/*      */ 
/*      */     
/* 3774 */     arrayList1.add("textureNonPowerOfTwoAvailable");
/* 3775 */     arrayList2.add(new Boolean(((this.textureExtendedFeatures & 0x8000) != 0)));
/*      */ 
/*      */     
/* 3778 */     arrayList1.add("textureAutoMipMapGenerationAvailable");
/* 3779 */     arrayList2.add(new Boolean(((this.textureExtendedFeatures & 0x10000) != 0)));
/*      */ 
/*      */     
/* 3782 */     arrayList1.add("textureCoordSetsMax");
/* 3783 */     arrayList2.add(new Integer(this.maxTexCoordSets));
/*      */     
/* 3785 */     arrayList1.add("textureUnitStateMax");
/* 3786 */     arrayList2.add(new Integer(this.maxTextureUnits));
/*      */     
/* 3788 */     arrayList1.add("textureImageUnitsMax");
/* 3789 */     arrayList2.add(new Integer(this.maxTextureImageUnits));
/*      */     
/* 3791 */     arrayList1.add("textureImageUnitsVertexMax");
/* 3792 */     arrayList2.add(new Integer(this.maxVertexTextureImageUnits));
/*      */     
/* 3794 */     arrayList1.add("textureImageUnitsCombinedMax");
/* 3795 */     arrayList2.add(new Integer(this.maxCombinedTextureImageUnits));
/*      */     
/* 3797 */     arrayList1.add("vertexAttrsMax");
/* 3798 */     arrayList2.add(new Integer(this.maxVertexAttrs));
/*      */     
/* 3800 */     arrayList1.add("shadingLanguageGLSL");
/* 3801 */     arrayList2.add(new Boolean(this.shadingLanguageGLSL));
/*      */     
/* 3803 */     arrayList1.add("shadingLanguageCg");
/* 3804 */     arrayList2.add(new Boolean(this.shadingLanguageCg));
/*      */     
/* 3806 */     arrayList1.add("native.version");
/* 3807 */     arrayList2.add(this.nativeGraphicsVersion);
/*      */     
/* 3809 */     arrayList1.add("native.vendor");
/* 3810 */     arrayList2.add(this.nativeGraphicsVendor);
/*      */     
/* 3812 */     arrayList1.add("native.renderer");
/* 3813 */     arrayList2.add(this.nativeGraphicsRenderer);
/*      */ 
/*      */     
/* 3816 */     this.queryProps = new J3dQueryProps((String[])arrayList1.toArray(new String[0]), arrayList2.toArray());
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
/*      */   void updateViewCache(boolean paramBoolean1, CanvasViewCache paramCanvasViewCache, BoundingBox paramBoundingBox, boolean paramBoolean2) {
/* 3828 */     assert paramCanvasViewCache == null;
/* 3829 */     synchronized (this.cvLock) {
/* 3830 */       if (this.firstPaintCalled && this.canvasViewCache != null) {
/* 3831 */         assert this.canvasViewCacheFrustum != null;
/*      */         
/* 3833 */         if (paramBoundingBox != null) {
/* 3834 */           this.canvasViewCacheFrustum.snapshot(true);
/* 3835 */           this.canvasViewCacheFrustum.computeDerivedData(paramBoolean1, null, paramBoundingBox, paramBoolean2);
/*      */         } else {
/*      */           
/* 3838 */           this.canvasViewCache.snapshot(false);
/* 3839 */           this.canvasViewCache.computeDerivedData(paramBoolean1, null, null, paramBoolean2);
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
/* 3850 */   void setDepthBufferWriteEnableOverride(boolean paramBoolean) { this.depthBufferWriteEnableOverride = paramBoolean; }
/*      */   void resetTexture(Context paramContext, int paramInt) { resetTextureNative(paramContext, paramInt); if (paramInt < 0) paramInt = 0;  (this.texUnitState[paramInt]).mirror = null; (this.texUnitState[paramInt]).texture = null; if (VirtualUniverse.mc.isD3D()) { (this.texUnitState[paramInt]).texAttrs = null; (this.texUnitState[paramInt]).texGen = null; }  }
/*      */   void resetTextureBin() { if (this.graphics2D != null && this.graphics2D.objectId != -1) { VirtualUniverse.mc.freeTexture2DId(this.graphics2D.objectId); this.graphics2D.objectId = -1; }  for (int i = this.textureIDResourceTable.size() - 1; i >= 0; i--) { Object object = this.textureIDResourceTable.get(i); if (object != null && object instanceof TextureRetained) { TextureRetained textureRetained = (TextureRetained)object; textureRetained.resourceCreationMask &= (this.canvasBit ^ 0xFFFFFFFF); }  }  }
/*      */   void d3dResize() { assert VirtualUniverse.mc.isD3D(); int i = resizeD3DCanvas(this.ctx); this.antialiasingSet = false; if (i != 0) resetRendering(i);  }
/*      */   void d3dToggle() { assert VirtualUniverse.mc.isD3D(); int i = toggleFullScreenMode(this.ctx); this.antialiasingSet = false; if (i != 0) resetRendering(i);  }
/*      */   void notifyD3DPeer(int paramInt) { assert VirtualUniverse.mc.isD3D(); if (this.active) if (this.isRunning) { if (this.view != null && this.view.active && this.screen.renderer != null) { VirtualUniverse.mc.postRequest(MasterControl.STOP_RENDERER, this); while (this.isRunningStatus) MasterControl.threadYield();  J3dMessage j3dMessage = new J3dMessage(); j3dMessage.threads = 16; if (paramInt == 1) { j3dMessage.type = 52; } else { j3dMessage.type = 53; }  j3dMessage.universe = null; j3dMessage.view = null; j3dMessage.args[0] = this; this.screen.renderer.rendererStructure.addMessage(j3dMessage); VirtualUniverse.mc.postRequest(MasterControl.START_RENDERER, this); VirtualUniverse.mc.sendRunMessage(this.view, 16); }  } else { this.reEvaluateCanvasCmd = paramInt; }   }
/*      */   void resetRendering(int paramInt) { if (paramInt == 2) { resetTextureBin(); this.screen.renderer.needToResendTextureDown = true; }  reset(); synchronized (this.dirtyMaskLock) { this.cvDirtyMask[0] = this.cvDirtyMask[0] | 0x3F; this.cvDirtyMask[1] = this.cvDirtyMask[1] | 0x3F; }  }
/* 3857 */   void reset() { this.currentAppear = new AppearanceRetained(); this.currentMaterial = new MaterialRetained(); this.viewFrustum = new CachedFrustum(); this.canvasDirty = 65535; this.lightBin = null; this.environmentSet = null; this.attributeBin = null; this.shaderBin = null; this.textureBin = null; this.renderMolecule = null; this.polygonAttributes = null; this.lineAttributes = null; this.pointAttributes = null; this.material = null; this.enableLighting = false; this.transparency = null; this.coloringAttributes = null; this.shaderProgram = null; this.texture = null; this.texAttrs = null; if (this.texUnitState != null) for (byte b1 = 0; b1 < this.texUnitState.length; b1++) { TextureUnitStateRetained textureUnitStateRetained = this.texUnitState[b1]; if (textureUnitStateRetained != null) { textureUnitStateRetained.texAttrs = null; textureUnitStateRetained.texGen = null; }  }   this.texCoordGeneration = null; this.renderingAttrs = null; this.appearance = null; this.appHandle = null; this.dirtyRenderMoleculeList.clear(); this.displayListResourceFreeList.clear(); this.dirtyDlistPerRinfoList.clear(); this.textureIdResourceFreeList.clear(); this.lightChanged = true; this.modelMatrix = null; this.modelClip = null; this.fog = null; this.texLinearMode = false; this.sceneAmbient = new Color3f(); byte b; for (b = 0; b < this.frameCount.length; b++) this.frameCount[b] = -1;  for (b = 0; b < this.lights.length; b++) this.lights[b] = null;  if (this.currentLights != null) for (b = 0; b < this.currentLights.length; b++) this.currentLights[b] = null;   this.enableMask = -1L; this.stateUpdateMask = 0; this.depthBufferWriteEnableOverride = false; this.depthBufferEnableOverride = false; this.depthBufferWriteEnable = true; this.vfPlanesValid = false; this.lightChanged = false; for (b = 0; b < this.curStateToUpdate.length; b++) this.curStateToUpdate[b] = null;  this.needToRebuildDisplayList = true; this.ctxTimeStamp = VirtualUniverse.mc.getContextTimeStamp(); } void resetImmediateRendering(int paramInt) { this.canvasDirty = 65535; this.ra = null; setSceneAmbient(this.ctx, 0.0F, 0.0F, 0.0F); disableFog(this.ctx); resetRenderingAttributes(this.ctx, false, false); resetTexture(this.ctx, -1); resetTexCoordGeneration(this.ctx); resetTextureAttributes(this.ctx); (this.texUnitState[0]).texAttrs = null; (this.texUnitState[0]).texGen = null; resetPolygonAttributes(this.ctx); resetLineAttributes(this.ctx); resetPointAttributes(this.ctx); resetTransparency(this.ctx, 4, 2, false, false); resetColoringAttributes(this.ctx, 1.0F, 1.0F, 1.0F, 1.0F, false); updateMaterial(this.ctx, 1.0F, 1.0F, 1.0F, 1.0F); resetRendering(0); makeCtxCurrent(); synchronized (this.dirtyMaskLock) { this.cvDirtyMask[0] = this.cvDirtyMask[0] | 0x3F; this.cvDirtyMask[1] = this.cvDirtyMask[1] | 0x3F; }  this.needToRebuildDisplayList = true; this.ctxTimeStamp = VirtualUniverse.mc.getContextTimeStamp(); if (paramInt == 2) this.screen.renderer.needToResendTextureDown = true;  } void setDepthBufferEnableOverride(boolean paramBoolean) { this.depthBufferEnableOverride = paramBoolean; }
/*      */   public Dimension getSize() { if (!this.fullScreenMode)
/*      */       return super.getSize();  return new Dimension(this.fullscreenWidth, this.fullscreenHeight); }
/*      */   public Dimension getSize(Dimension paramDimension) { if (!this.fullScreenMode)
/*      */       return super.getSize(paramDimension);  if (paramDimension == null)
/* 3862 */       return new Dimension(this.fullscreenWidth, this.fullscreenHeight);  paramDimension.setSize(this.fullscreenWidth, this.fullscreenHeight); return paramDimension; } static  { VirtualUniverse.loadLibraries();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4430 */     ENV_STATE_MASK = 71; } public Point getLocationOnScreen() { if (!this.fullScreenMode) try { return super.getLocationOnScreen(); } catch (IllegalComponentStateException illegalComponentStateException) {}  return new Point(); } public int getX() { if (!this.fullScreenMode) return super.getX();  return 0; } public int getY() { if (!this.fullScreenMode) return super.getY();  return 0; } public int getWidth() { if (!this.fullScreenMode) return super.getWidth();  return this.screen.screenSize.width; }
/*      */   public int getHeight() { if (!this.fullScreenMode) return super.getHeight();  return this.screen.screenSize.height; }
/*      */   public Point getLocation(Point paramPoint) { if (!this.fullScreenMode) return super.getLocation(paramPoint);  if (paramPoint != null) { paramPoint.setLocation(0, 0); return paramPoint; }  return new Point(); }
/*      */   public Point getLocation() { if (!this.fullScreenMode) return super.getLocation();  return new Point(); }
/*      */   public Rectangle getBounds() { if (!this.fullScreenMode) return super.getBounds();  return new Rectangle(0, 0, this.screen.screenSize.width, this.screen.screenSize.height); }
/*      */   public Rectangle getBounds(Rectangle paramRectangle) { if (!this.fullScreenMode) return super.getBounds(paramRectangle);  if (paramRectangle != null) { paramRectangle.setBounds(0, 0, this.screen.screenSize.width, this.screen.screenSize.height); return paramRectangle; }  return new Rectangle(0, 0, this.screen.screenSize.width, this.screen.screenSize.height); }
/*      */   void setProjectionMatrix(Context paramContext, Transform3D paramTransform3D) { this.projTrans = paramTransform3D; setProjectionMatrix(paramContext, paramTransform3D.mat); }
/* 4437 */   void updateEnvState() { if ((this.stateUpdateMask & ENV_STATE_MASK) == 0) {
/*      */       return;
/*      */     }
/* 4440 */     if ((this.stateUpdateMask & true) != 0) {
/* 4441 */       ((LightBin)this.curStateToUpdate[0]).updateAttributes(this);
/*      */     }
/*      */     
/* 4444 */     if ((this.stateUpdateMask & 0x2) != 0) {
/* 4445 */       ((EnvironmentSet)this.curStateToUpdate[1]).updateAttributes(this);
/*      */     }
/*      */ 
/*      */     
/* 4449 */     if ((this.stateUpdateMask & 0x4) != 0) {
/* 4450 */       ((AttributeBin)this.curStateToUpdate[2]).updateAttributes(this);
/*      */     }
/*      */ 
/*      */     
/* 4454 */     if ((this.stateUpdateMask & 0x40) != 0) {
/* 4455 */       ((ShaderBin)this.curStateToUpdate[6]).updateAttributes(this);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4461 */     this.stateUpdateMask &= (ENV_STATE_MASK ^ 0xFFFFFFFF); } void setModelViewMatrix(Context paramContext, double[] paramArrayOfDouble, Transform3D paramTransform3D) { setModelViewMatrix(paramContext, paramArrayOfDouble, paramTransform3D.mat); if (!this.useStereo) { this.modelMatrix = paramTransform3D; } else if (this.rightStereoPass) { this.modelMatrix = paramTransform3D; }  } void setDepthBufferWriteEnable(boolean paramBoolean) { this.depthBufferWriteEnable = paramBoolean; setDepthBufferWriteEnable(this.ctx, paramBoolean); } void setNumActiveTexUnit(int paramInt) { this.numActiveTexUnit = paramInt; } int getNumActiveTexUnit() { return this.numActiveTexUnit; } void setLastActiveTexUnit(int paramInt) { this.lastActiveTexUnit = paramInt; } int getLastActiveTexUnit() { return this.lastActiveTexUnit; } void createTexUnitState() { this.texUnitState = new TextureUnitStateRetained[this.maxAvailableTextureUnits]; for (byte b = 0; b < this.maxAvailableTextureUnits; b++) { this.texUnitState[b] = new TextureUnitStateRetained(); (this.texUnitState[b]).texture = null; (this.texUnitState[b]).mirror = null; }  } boolean supportGlobalAlpha() { return ((this.extensionsSupported & true) != 0); } void enableSeparateSpecularColor() { boolean bool = !VirtualUniverse.mc.disableSeparateSpecularColor; updateSeparateSpecularColorEnable(this.ctx, bool); } final void beginScene() { beginScene(this.ctx); } final void endScene() { endScene(this.ctx); } private void sendCreateOffScreenBuffer() { if (!(Thread.currentThread() instanceof BehaviorScheduler) && !(Thread.currentThread() instanceof Renderer)) this.offScreenBufferPending = true;  VirtualUniverse.mc.sendCreateOffScreenBuffer(this); while (this.offScreenBufferPending) { VirtualUniverse.mc.createMasterControlThread(); MasterControl.threadYield(); }  } private void sendDestroyCtxAndOffScreenBuffer() { Thread thread = Thread.currentThread(); if (!(thread instanceof BehaviorScheduler) && !(thread instanceof Renderer)) this.offScreenBufferPending = true;  VirtualUniverse.mc.sendDestroyCtxAndOffScreenBuffer(this); while (this.offScreenBufferPending) { VirtualUniverse.mc.createMasterControlThread(); MasterControl.threadYield(); }  }
/*      */   private void sendAllocateCanvasId() { VirtualUniverse.mc.sendAllocateCanvasId(this); }
/*      */   private void sendFreeCanvasId() { VirtualUniverse.mc.sendFreeCanvasId(this); }
/*      */   private void removeCtx() { if (this.screen != null && this.screen.renderer != null && this.ctx != null) { VirtualUniverse.mc.postRequest(MasterControl.FREE_CONTEXT, new Object[] { this, new Long(this.screen.display), this.drawable, this.ctx }); Thread thread = Thread.currentThread(); if (!(thread instanceof BehaviorScheduler) && !(thread instanceof Renderer)) while (this.ctxTimeStamp != 0L)
/*      */           MasterControl.threadYield();   this.ctx = null; }  }
/*      */   private void writeObject(ObjectOutputStream paramObjectOutputStream) throws IOException { throw new UnsupportedOperationException(J3dI18N.getString("Canvas3D20")); }
/*      */   private void readObject(ObjectInputStream paramObjectInputStream) throws IOException, ClassNotFoundException { throw new UnsupportedOperationException(J3dI18N.getString("Canvas3D20")); }
/*      */   void setStateIsUpdated(int paramInt) { this.stateUpdateMask &= (1 << paramInt ^ 0xFFFFFFFF); }
/*      */   void setStateToUpdate(int paramInt, Object paramObject) { this.stateUpdateMask |= 1 << paramInt; this.curStateToUpdate[paramInt] = paramObject; }
/* 4470 */   void updateState(int paramInt) { if (this.stateUpdateMask == 0) {
/*      */       return;
/*      */     }
/* 4473 */     updateEnvState();
/*      */     
/* 4475 */     if ((this.stateUpdateMask & 0x8) != 0) {
/* 4476 */       ((TextureBin)this.curStateToUpdate[3]).updateAttributes(this);
/*      */     }
/*      */ 
/*      */     
/* 4480 */     if ((this.stateUpdateMask & 0x10) != 0) {
/* 4481 */       ((RenderMolecule)this.curStateToUpdate[4]).updateAttributes(this, paramInt);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4487 */     if ((this.stateUpdateMask & 0x20) != 0) {
/* 4488 */       ((RenderMolecule)this.curStateToUpdate[4]).updateTransparencyAttributes(this);
/* 4489 */       this.stateUpdateMask &= 0xFFFFFFDF;
/*      */     } 
/*      */ 
/*      */     
/* 4493 */     this.stateUpdateMask = 0; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTextureForRaster(Texture2DRetained paramTexture2DRetained) {
/* 4502 */     Pipeline.getPipeline().updateTextureUnitState(this.ctx, 0, true);
/* 4503 */     setLastActiveTexUnit(0);
/* 4504 */     setNumActiveTexUnit(1);
/*      */     
/* 4506 */     paramTexture2DRetained.updateNative(this);
/* 4507 */     resetTextureAttributes(this.ctx);
/*      */     
/* 4509 */     for (byte b = 1; b < this.maxTextureUnits; b++) {
/* 4510 */       resetTexture(this.ctx, b);
/*      */     }
/*      */ 
/*      */     
/* 4514 */     activeTextureUnit(this.ctx, 0);
/*      */ 
/*      */     
/* 4517 */     this.canvasDirty |= 0xC00;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void restoreTextureBin() {
/* 4524 */     if (this.textureBin != null && this.textureBin.shaderBin != null) {
/* 4525 */       this.textureBin.updateAttributes(this);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void textureFill(RasterRetained paramRasterRetained, Point2d paramPoint2d, float paramFloat1, float paramFloat2) {
/* 4532 */     int i = this.canvasViewCache.getCanvasWidth();
/* 4533 */     int j = this.canvasViewCache.getCanvasHeight();
/*      */     
/* 4535 */     int k = paramRasterRetained.image.width;
/* 4536 */     int m = paramRasterRetained.image.height;
/*      */     
/* 4538 */     float f1 = 0.0F, f2 = 0.0F, f3 = 0.0F, f4 = 0.0F;
/* 4539 */     float f5 = 0.0F, f6 = 0.0F, f7 = 0.0F, f8 = 0.0F;
/*      */     
/* 4541 */     Point point = new Point();
/* 4542 */     paramRasterRetained.getSrcOffset(point);
/*      */     
/* 4544 */     Dimension dimension = new Dimension();
/* 4545 */     paramRasterRetained.getSize(dimension);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4550 */     int n = point.x;
/* 4551 */     int i1 = point.x + dimension.width;
/* 4552 */     int i2 = point.y;
/* 4553 */     int i3 = point.y + dimension.height;
/*      */     
/* 4555 */     if (n >= k || i2 >= m || i1 <= 0 || i3 <= 0) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 4560 */     if (n < 0) {
/* 4561 */       n = 0;
/*      */     }
/* 4563 */     if (i2 < 0) {
/* 4564 */       i2 = 0;
/*      */     }
/*      */     
/* 4567 */     if (i1 > k) {
/* 4568 */       i1 = k;
/*      */     }
/*      */     
/* 4571 */     if (i3 > m) {
/* 4572 */       i3 = m;
/*      */     }
/*      */     
/* 4575 */     f1 = n / k;
/* 4576 */     f3 = i1 / k;
/* 4577 */     f5 = (float)paramPoint2d.x / i;
/* 4578 */     f7 = (float)(paramPoint2d.x + (i1 - n)) / i;
/*      */     
/* 4580 */     if (paramRasterRetained.image.isYUp()) {
/* 4581 */       f2 = i2 / m;
/* 4582 */       f4 = i3 / m;
/*      */     } else {
/*      */       
/* 4585 */       f2 = 1.0F - i3 / m;
/* 4586 */       f4 = 1.0F - i2 / m;
/*      */     } 
/*      */     
/* 4589 */     f6 = 1.0F - (float)(paramPoint2d.y + (i3 - i2)) / j;
/* 4590 */     f8 = 1.0F - (float)paramPoint2d.y / j;
/*      */     
/* 4592 */     textureFillRaster(this.ctx, f1, f3, f2, f4, f5, f7, f6, f8, paramFloat1, paramFloat2, paramRasterRetained.image.useBilinearFilter());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void textureFill(BackgroundRetained paramBackgroundRetained, int paramInt1, int paramInt2) {
/* 4599 */     int i = paramBackgroundRetained.image.width;
/* 4600 */     int j = paramBackgroundRetained.image.height;
/*      */ 
/*      */ 
/*      */     
/* 4604 */     float f1 = paramInt1 / i;
/* 4605 */     float f2 = paramInt2 / j;
/* 4606 */     float f3 = 0.0F;
/* 4607 */     float f4 = 0.0F, f5 = 0.0F, f6 = 0.0F, f7 = 0.0F, f8 = 0.0F;
/* 4608 */     float f9 = 0.0F, f10 = 0.0F, f11 = 0.0F, f12 = 0.0F;
/* 4609 */     float f13 = 0.0F, f14 = 0.0F;
/*      */     
/* 4611 */     switch (paramBackgroundRetained.imageScaleMode) {
/*      */       case 0:
/* 4613 */         f4 = 0.0F;
/* 4614 */         f5 = 0.0F;
/* 4615 */         f6 = 1.0F;
/* 4616 */         f7 = 1.0F;
/* 4617 */         f13 = paramInt1 / 2.0F;
/* 4618 */         f14 = paramInt2 / 2.0F;
/* 4619 */         f9 = (0.0F - f13) / f13;
/* 4620 */         f10 = (0.0F - f14) / f14;
/* 4621 */         f11 = (i - f13) / f13;
/* 4622 */         f12 = (j - f14) / f14;
/* 4623 */         f8 = (paramInt2 - j) / f14;
/* 4624 */         f10 += f8;
/* 4625 */         f12 += f8;
/*      */         break;
/*      */       case 1:
/* 4628 */         f3 = Math.min(f1, f2);
/* 4629 */         f4 = 0.0F;
/* 4630 */         f5 = 0.0F;
/* 4631 */         f6 = 1.0F;
/* 4632 */         f7 = 1.0F;
/* 4633 */         f9 = -1.0F;
/* 4634 */         f12 = 1.0F;
/* 4635 */         if (f1 < f2) {
/* 4636 */           f11 = 1.0F;
/* 4637 */           f10 = -1.0F + 2.0F * (1.0F - f3 * j / paramInt2); break;
/*      */         } 
/* 4639 */         f11 = -1.0F + f3 * i / paramInt1 * 2.0F;
/* 4640 */         f10 = -1.0F;
/*      */         break;
/*      */       
/*      */       case 2:
/* 4644 */         f3 = Math.max(f1, f2);
/* 4645 */         f9 = -1.0F;
/* 4646 */         f10 = -1.0F;
/* 4647 */         f11 = 1.0F;
/* 4648 */         f12 = 1.0F;
/* 4649 */         if (f1 < f2) {
/* 4650 */           f4 = 0.0F;
/* 4651 */           f5 = 0.0F;
/* 4652 */           f6 = paramInt1 / i / f3;
/* 4653 */           f7 = 1.0F; break;
/*      */         } 
/* 4655 */         f4 = 0.0F;
/* 4656 */         f5 = 1.0F - paramInt2 / j / f3;
/* 4657 */         f6 = 1.0F;
/* 4658 */         f7 = 1.0F;
/*      */         break;
/*      */       
/*      */       case 3:
/* 4662 */         f4 = 0.0F;
/* 4663 */         f5 = 0.0F;
/* 4664 */         f6 = 1.0F;
/* 4665 */         f7 = 1.0F;
/* 4666 */         f9 = -1.0F;
/* 4667 */         f10 = -1.0F;
/* 4668 */         f11 = 1.0F;
/* 4669 */         f12 = 1.0F;
/*      */         break;
/*      */       
/*      */       case 4:
/* 4673 */         f4 = 0.0F;
/* 4674 */         f5 = -f2;
/* 4675 */         f6 = f1;
/* 4676 */         f7 = 0.0F;
/* 4677 */         f9 = -1.0F;
/* 4678 */         f10 = -1.0F;
/* 4679 */         f11 = 1.0F;
/* 4680 */         f12 = 1.0F;
/*      */         break;
/*      */       
/*      */       case 5:
/* 4684 */         if (f1 >= 1.0F) {
/* 4685 */           f4 = 0.0F;
/* 4686 */           f6 = 1.0F;
/* 4687 */           f9 = -(i) / paramInt1;
/* 4688 */           f11 = i / paramInt1;
/*      */         } else {
/* 4690 */           f4 = 0.5F - paramInt1 / i / 2.0F;
/* 4691 */           f6 = 0.5F + paramInt1 / i / 2.0F;
/* 4692 */           f9 = -1.0F;
/* 4693 */           f11 = 1.0F;
/*      */         } 
/* 4695 */         if (f2 >= 1.0F) {
/* 4696 */           f5 = 0.0F;
/* 4697 */           f7 = 1.0F;
/* 4698 */           f10 = -(j) / paramInt2;
/* 4699 */           f12 = j / paramInt2; break;
/*      */         } 
/* 4701 */         f5 = 0.5F - paramInt2 / j / 2.0F;
/* 4702 */         f7 = 0.5F + paramInt2 / j / 2.0F;
/* 4703 */         f10 = -1.0F;
/* 4704 */         f12 = 1.0F;
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4711 */     textureFillBackground(this.ctx, f4, f6, f5, f7, f9, f11, f10, f12, paramBackgroundRetained.image.useBilinearFilter());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void clear(BackgroundRetained paramBackgroundRetained, int paramInt1, int paramInt2) {
/* 4722 */     boolean bool = (VirtualUniverse.mc.stencilClear && this.userStencilAvailable);
/*      */ 
/*      */     
/* 4725 */     clear(this.ctx, paramBackgroundRetained.color.x, paramBackgroundRetained.color.y, paramBackgroundRetained.color.z, bool);
/*      */ 
/*      */ 
/*      */     
/* 4729 */     if (paramBackgroundRetained.image != null && paramBackgroundRetained.image.imageData != null) {
/*      */       
/* 4731 */       updateTextureForRaster(paramBackgroundRetained.texture);
/*      */       
/* 4733 */       textureFill(paramBackgroundRetained, paramInt1, paramInt2);
/*      */ 
/*      */       
/* 4736 */       restoreTextureBin();
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
/*      */   void addTextureResource(int paramInt, Object paramObject) {
/* 4752 */     if (paramInt <= 0) {
/*      */       return;
/*      */     }
/*      */     
/* 4756 */     if (this.useSharedCtx) {
/* 4757 */       this.screen.renderer.addTextureResource(paramInt, paramObject);
/*      */     
/*      */     }
/* 4760 */     else if (this.textureIDResourceTable.size() <= paramInt) {
/* 4761 */       int i = this.textureIDResourceTable.size();
/* 4762 */       for (; i < paramInt; i++) {
/* 4763 */         this.textureIDResourceTable.add(null);
/*      */       }
/* 4765 */       this.textureIDResourceTable.add(paramObject);
/*      */     } else {
/* 4767 */       this.textureIDResourceTable.set(paramInt, paramObject);
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
/*      */   void freeResourcesInFreeList(Context paramContext) {
/* 4782 */     if (this.displayListResourceFreeList.size() > 0) {
/* 4783 */       for (Iterator iterator = this.displayListResourceFreeList.iterator(); iterator.hasNext(); ) {
/* 4784 */         int i = ((Integer)iterator.next()).intValue();
/* 4785 */         if (i <= 0) {
/*      */           continue;
/*      */         }
/* 4788 */         freeDisplayList(paramContext, i);
/*      */       } 
/* 4790 */       this.displayListResourceFreeList.clear();
/*      */     } 
/*      */     
/* 4793 */     if (this.textureIdResourceFreeList.size() > 0) {
/* 4794 */       for (Iterator iterator = this.textureIdResourceFreeList.iterator(); iterator.hasNext(); ) {
/* 4795 */         int i = ((Integer)iterator.next()).intValue();
/* 4796 */         if (i <= 0) {
/*      */           continue;
/*      */         }
/* 4799 */         if (i >= this.textureIDResourceTable.size()) {
/* 4800 */           System.err.println("Error in freeResourcesInFreeList : ResourceIDTableSize = " + this.textureIDResourceTable.size() + " val = " + i);
/*      */         }
/*      */         else {
/*      */           
/* 4804 */           Object object = this.textureIDResourceTable.get(i);
/* 4805 */           if (object instanceof TextureRetained) {
/* 4806 */             TextureRetained textureRetained = (TextureRetained)object;
/* 4807 */             synchronized (textureRetained.resourceLock) {
/* 4808 */               textureRetained.resourceCreationMask &= (this.canvasBit ^ 0xFFFFFFFF);
/* 4809 */               if (textureRetained.resourceCreationMask == 0) {
/* 4810 */                 textureRetained.freeTextureId(i);
/*      */               }
/*      */             } 
/*      */           } 
/*      */           
/* 4815 */           this.textureIDResourceTable.set(i, null);
/*      */         } 
/* 4817 */         freeTexture(paramContext, i);
/*      */       } 
/* 4819 */       this.textureIdResourceFreeList.clear();
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
/*      */   void freeContextResources(Renderer paramRenderer, boolean paramBoolean, Context paramContext) {
/* 4831 */     if (paramRenderer == null || paramContext == null) {
/*      */       return;
/*      */     }
/*      */     
/* 4835 */     if (paramBoolean)
/*      */     {
/* 4837 */       if (this.graphics2D != null) {
/* 4838 */         this.graphics2D.dispose();
/*      */       }
/*      */     }
/*      */     
/* 4842 */     for (int i = this.textureIDResourceTable.size() - 1; i >= 0; i--) {
/* 4843 */       Object object = this.textureIDResourceTable.get(i);
/* 4844 */       if (object != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4852 */         freeTexture(paramContext, i);
/* 4853 */         if (object instanceof TextureRetained) {
/* 4854 */           TextureRetained textureRetained = (TextureRetained)object;
/* 4855 */           synchronized (textureRetained.resourceLock) {
/* 4856 */             textureRetained.resourceCreationMask &= (this.canvasBit ^ 0xFFFFFFFF);
/* 4857 */             if (textureRetained.resourceCreationMask == 0)
/*      */             {
/* 4859 */               textureRetained.freeTextureId(i); } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 4864 */     this.textureIDResourceTable.clear();
/*      */     
/* 4866 */     freeAllDisplayListResources(paramContext);
/*      */   }
/*      */   
/*      */   void freeAllDisplayListResources(Context paramContext) {
/* 4870 */     if (this.view != null && this.view.renderBin != null) {
/* 4871 */       this.view.renderBin.freeAllDisplayListResources(this, paramContext);
/* 4872 */       if (this.useSharedCtx)
/*      */       {
/*      */ 
/*      */         
/* 4876 */         if (this.screen != null && this.screen.renderer != null) {
/* 4877 */           this.screen.renderer.needToRebuildDisplayList = true;
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
/* 4895 */   private Context createNewContext(long paramLong1, Drawable paramDrawable, long paramLong2, Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) { return Pipeline.getPipeline().createNewContext(this, paramLong1, paramDrawable, paramLong2, paramContext, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4906 */   private void createQueryContext(long paramLong1, Drawable paramDrawable, long paramLong2, boolean paramBoolean1, int paramInt1, int paramInt2, boolean paramBoolean2, boolean paramBoolean3) { Pipeline.getPipeline().createQueryContext(this, paramLong1, paramDrawable, paramLong2, paramBoolean1, paramInt1, paramInt2, paramBoolean2, paramBoolean3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4914 */   Drawable createOffScreenBuffer(Context paramContext, long paramLong1, long paramLong2, int paramInt1, int paramInt2) { return Pipeline.getPipeline().createOffScreenBuffer(this, paramContext, paramLong1, paramLong2, paramInt1, paramInt2); }
/*      */ 
/*      */ 
/*      */   
/*      */   void destroyOffScreenBuffer(Context paramContext, long paramLong1, long paramLong2, Drawable paramDrawable) {
/* 4919 */     assert paramDrawable != null;
/* 4920 */     Pipeline.getPipeline().destroyOffScreenBuffer(this, paramContext, paramLong1, paramLong2, paramDrawable);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 4925 */   private void readOffScreenBuffer(Context paramContext, int paramInt1, int paramInt2, Object paramObject, int paramInt3, int paramInt4) { Pipeline.getPipeline().readOffScreenBuffer(this, paramContext, paramInt1, paramInt2, paramObject, paramInt3, paramInt4); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4930 */   int swapBuffers(Context paramContext, long paramLong, Drawable paramDrawable) { return Pipeline.getPipeline().swapBuffers(this, paramContext, paramLong, paramDrawable); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4935 */   private int resizeD3DCanvas(Context paramContext) { return Pipeline.getPipeline().resizeD3DCanvas(this, paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4940 */   private int toggleFullScreenMode(Context paramContext) { return Pipeline.getPipeline().toggleFullScreenMode(this, paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4947 */   void updateMaterial(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { Pipeline.getPipeline().updateMaterialColor(paramContext, paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*      */ 
/*      */ 
/*      */   
/* 4951 */   static void destroyContext(long paramLong, Drawable paramDrawable, Context paramContext) { Pipeline.getPipeline().destroyContext(paramLong, paramDrawable, paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4956 */   void accum(Context paramContext, float paramFloat) { Pipeline.getPipeline().accum(paramContext, paramFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4961 */   void accumReturn(Context paramContext) { Pipeline.getPipeline().accumReturn(paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4966 */   void clearAccum(Context paramContext) { Pipeline.getPipeline().clearAccum(paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4972 */   int getNumCtxLights(Context paramContext) { return Pipeline.getPipeline().getNumCtxLights(paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4977 */   boolean decal1stChildSetup(Context paramContext) { return Pipeline.getPipeline().decal1stChildSetup(paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4982 */   void decalNthChildSetup(Context paramContext) { Pipeline.getPipeline().decalNthChildSetup(paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4987 */   void decalReset(Context paramContext, boolean paramBoolean) { Pipeline.getPipeline().decalReset(paramContext, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4992 */   void ctxUpdateEyeLightingEnable(Context paramContext, boolean paramBoolean) { Pipeline.getPipeline().ctxUpdateEyeLightingEnable(paramContext, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5000 */   void setBlendColor(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { Pipeline.getPipeline().setBlendColor(paramContext, paramFloat1, paramFloat2, paramFloat3, paramFloat4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5006 */   void setBlendFunc(Context paramContext, int paramInt1, int paramInt2) { Pipeline.getPipeline().setBlendFunc(paramContext, paramInt1, paramInt2); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5011 */   void setFogEnableFlag(Context paramContext, boolean paramBoolean) { Pipeline.getPipeline().setFogEnableFlag(paramContext, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5016 */   void setFullSceneAntialiasing(Context paramContext, boolean paramBoolean) { Pipeline.getPipeline().setFullSceneAntialiasing(paramContext, paramBoolean); }
/*      */ 
/*      */ 
/*      */   
/* 5020 */   void setGlobalAlpha(Context paramContext, float paramFloat) { Pipeline.getPipeline().setGlobalAlpha(paramContext, paramFloat); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5025 */   void updateSeparateSpecularColorEnable(Context paramContext, boolean paramBoolean) { Pipeline.getPipeline().updateSeparateSpecularColorEnable(paramContext, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5030 */   private void beginScene(Context paramContext) { Pipeline.getPipeline().beginScene(paramContext); }
/*      */ 
/*      */   
/* 5033 */   private void endScene(Context paramContext) { Pipeline.getPipeline().endScene(paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5039 */   private boolean validGraphicsMode() { return Pipeline.getPipeline().validGraphicsMode(); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5044 */   void setLightEnables(Context paramContext, long paramLong, int paramInt) { Pipeline.getPipeline().setLightEnables(paramContext, paramLong, paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5049 */   void setSceneAmbient(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) { Pipeline.getPipeline().setSceneAmbient(paramContext, paramFloat1, paramFloat2, paramFloat3); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5054 */   void disableFog(Context paramContext) { Pipeline.getPipeline().disableFog(paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5059 */   void disableModelClip(Context paramContext) { Pipeline.getPipeline().disableModelClip(paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5066 */   void resetRenderingAttributes(Context paramContext, boolean paramBoolean1, boolean paramBoolean2) { Pipeline.getPipeline().resetRenderingAttributes(paramContext, paramBoolean1, paramBoolean2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5073 */   void resetTextureNative(Context paramContext, int paramInt) { Pipeline.getPipeline().resetTextureNative(paramContext, paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5078 */   void activeTextureUnit(Context paramContext, int paramInt) { Pipeline.getPipeline().activeTextureUnit(paramContext, paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5083 */   void resetTexCoordGeneration(Context paramContext) { Pipeline.getPipeline().resetTexCoordGeneration(paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5088 */   void resetTextureAttributes(Context paramContext) { Pipeline.getPipeline().resetTextureAttributes(paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5093 */   void resetPolygonAttributes(Context paramContext) { Pipeline.getPipeline().resetPolygonAttributes(paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5098 */   void resetLineAttributes(Context paramContext) { Pipeline.getPipeline().resetLineAttributes(paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5103 */   void resetPointAttributes(Context paramContext) { Pipeline.getPipeline().resetPointAttributes(paramContext); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5110 */   void resetTransparency(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) { Pipeline.getPipeline().resetTransparency(paramContext, paramInt1, paramInt2, paramBoolean1, paramBoolean2); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5120 */   void resetColoringAttributes(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean) { Pipeline.getPipeline().resetColoringAttributes(paramContext, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5131 */   void syncRender(Context paramContext, boolean paramBoolean) { Pipeline.getPipeline().syncRender(paramContext, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5136 */   static boolean useCtx(Context paramContext, long paramLong, Drawable paramDrawable) { return Pipeline.getPipeline().useCtx(paramContext, paramLong, paramDrawable); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5142 */   private boolean releaseCtx(Context paramContext, long paramLong) { return Pipeline.getPipeline().releaseCtx(paramContext, paramLong); }
/*      */ 
/*      */ 
/*      */   
/* 5146 */   void clear(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean) { Pipeline.getPipeline().clear(paramContext, paramFloat1, paramFloat2, paramFloat3, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5151 */   void textureFillBackground(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, boolean paramBoolean) { Pipeline.getPipeline().textureFillBackground(paramContext, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5157 */   void textureFillRaster(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, boolean paramBoolean) { Pipeline.getPipeline().textureFillRaster(paramContext, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8, paramFloat9, paramFloat10, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5164 */   void executeRasterDepth(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Object paramObject) { Pipeline.getPipeline().executeRasterDepth(paramContext, paramFloat1, paramFloat2, paramFloat3, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramObject); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5170 */   void setModelViewMatrix(Context paramContext, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) { Pipeline.getPipeline().setModelViewMatrix(paramContext, paramArrayOfDouble1, paramArrayOfDouble2); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5175 */   void setProjectionMatrix(Context paramContext, double[] paramArrayOfDouble) { Pipeline.getPipeline().setProjectionMatrix(paramContext, paramArrayOfDouble); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5180 */   void setViewport(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4) { Pipeline.getPipeline().setViewport(paramContext, paramInt1, paramInt2, paramInt3, paramInt4); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5185 */   void newDisplayList(Context paramContext, int paramInt) { Pipeline.getPipeline().newDisplayList(paramContext, paramInt); }
/*      */ 
/*      */   
/* 5188 */   void endDisplayList(Context paramContext) { Pipeline.getPipeline().endDisplayList(paramContext); }
/*      */ 
/*      */   
/* 5191 */   void callDisplayList(Context paramContext, int paramInt, boolean paramBoolean) { Pipeline.getPipeline().callDisplayList(paramContext, paramInt, paramBoolean); }
/*      */ 
/*      */ 
/*      */   
/* 5195 */   static void freeDisplayList(Context paramContext, int paramInt) { Pipeline.getPipeline().freeDisplayList(paramContext, paramInt); }
/*      */ 
/*      */   
/* 5198 */   static void freeTexture(Context paramContext, int paramInt) { Pipeline.getPipeline().freeTexture(paramContext, paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5209 */   void texturemapping(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, byte[] paramArrayOfByte, int paramInt12, int paramInt13) { Pipeline.getPipeline().texturemapping(paramContext, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramInt10, paramInt11, paramArrayOfByte, paramInt12, paramInt13); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5221 */   boolean initTexturemapping(Context paramContext, int paramInt1, int paramInt2, int paramInt3) { return Pipeline.getPipeline().initTexturemapping(paramContext, paramInt1, paramInt2, paramInt3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5232 */   void setRenderMode(Context paramContext, int paramInt, boolean paramBoolean) { Pipeline.getPipeline().setRenderMode(paramContext, paramInt, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5237 */   void setDepthBufferWriteEnable(Context paramContext, boolean paramBoolean) { Pipeline.getPipeline().setDepthBufferWriteEnable(paramContext, paramBoolean); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5243 */   boolean hasDoubleBuffer() { return Pipeline.getPipeline().hasDoubleBuffer(this); }
/*      */ 
/*      */ 
/*      */   
/* 5247 */   boolean hasStereo() { return Pipeline.getPipeline().hasStereo(this); }
/*      */ 
/*      */ 
/*      */   
/* 5251 */   int getStencilSize() { return Pipeline.getPipeline().getStencilSize(this); }
/*      */ 
/*      */ 
/*      */   
/* 5255 */   boolean hasSceneAntialiasingMultisample() { return Pipeline.getPipeline().hasSceneAntialiasingMultisample(this); }
/*      */ 
/*      */ 
/*      */   
/* 5259 */   boolean hasSceneAntialiasingAccum() { return Pipeline.getPipeline().hasSceneAntialiasingAccum(this); }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\Canvas3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */