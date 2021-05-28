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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class J3dDebug
/*     */ {
/*     */   static final int NO_DEBUG = 0;
/*     */   static final int LEVEL_1 = 1;
/*     */   static final int LEVEL_2 = 2;
/*     */   static final int LEVEL_3 = 3;
/*     */   static final int LEVEL_4 = 4;
/*     */   static final int LEVEL_5 = 5;
/*     */   static final boolean devPhase = false;
/*     */   
/*     */   static boolean doDebug(int paramInt1, int paramInt2, String paramString) {
/* 377 */     if (paramInt1 >= paramInt2) {
/* 378 */       System.err.print(paramString);
/* 379 */       return true;
/*     */     } 
/* 381 */     return false;
/*     */   }
/*     */   
/*     */   static boolean doDebug(int paramInt1, int paramInt2) {
/* 385 */     if (paramInt1 >= paramInt2) {
/* 386 */       return true;
/*     */     }
/* 388 */     return false;
/*     */   }
/*     */   
/*     */   static void doAssert(boolean paramBoolean, String paramString) {
/* 392 */     if (!paramBoolean) {
/* 393 */       throw new AssertionFailureException("(" + paramString + ")" + "is false");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void pkgInfo(ClassLoader paramClassLoader, String paramString1, String paramString2) {
/*     */     try {
/* 402 */       paramClassLoader.loadClass(paramString1 + "." + paramString2);
/*     */       
/* 404 */       Package package = Package.getPackage(paramString1);
/* 405 */       if (package == null) {
/* 406 */         System.err.println("WARNING: Package.getPackage(" + paramString1 + ") is null");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 428 */     catch (ClassNotFoundException classNotFoundException) {
/* 429 */       System.err.println("Unable to load " + paramString1);
/*     */     } 
/*     */   }
/*     */   
/*     */   static boolean debug = false;
/*     */   static final int alpha = 0;
/*     */   static final int alternateAppearance = 0;
/*     */   static final int ambientLight = 0;
/*     */   static final int ambientLightRetained = 0;
/*     */   static final int appearance = 0;
/*     */   static final int appearanceRetained = 0;
/*     */   static final int assertionFailureException = 0;
/*     */   static final int attributeBin = 0;
/*     */   static final int audioDevice = 0;
/*     */   static final int audioDevice3D = 0;
/*     */   static final int audioDeviceEnumerator = 0;
/*     */   static final int auralAttributes = 0;
/*     */   static final int auralAttributesRetained = 0;
/*     */   static final int bHInsertStructure = 0;
/*     */   static final int bHInternalNode = 0;
/*     */   static final int bHLeafInterface = 0;
/*     */   static final int bHLeafNode = 0;
/*     */   static final int bHNode = 0;
/*     */   static final int bHTree = 0;
/*     */   static final int background = 0;
/*     */   static final int backgroundRetained = 0;
/*     */   static final int backgroundSound = 0;
/*     */   static final int backgroundSoundRetained = 0;
/*     */   static final int badTransformException = 0;
/*     */   static final int behavior = 0;
/*     */   static final int behaviorRetained = 0;
/*     */   static final int behaviorScheduler = 0;
/*     */   static final int behaviorStructure = 0;
/*     */   static final int billboard = 0;
/*     */   static final int boundingBox = 0;
/*     */   static final int boundingLeaf = 0;
/*     */   static final int boundingLeafRetained = 0;
/*     */   static final int boundingPolytope = 0;
/*     */   static final int boundingSphere = 0;
/*     */   static final int bounds = 0;
/*     */   static final int branchGroup = 0;
/*     */   static final int branchGroupRetained = 0;
/*     */   static final int cachedFrustum = 0;
/*     */   static final int canvas3D = 0;
/*     */   static final int canvasViewCache = 0;
/*     */   static final int canvasViewEventCatcher = 0;
/*     */   static final int capabilityBits = 0;
/*     */   static final int capabilityNotSetException = 0;
/*     */   static final int clip = 0;
/*     */   static final int clipRetained = 0;
/*     */   static final int colorInterpolator = 0;
/*     */   static final int coloringAttributes = 0;
/*     */   static final int coloringAttributesRetained = 0;
/*     */   static final int compileState = 0;
/*     */   static final int compressedGeometry = 0;
/*     */   static final int compressedGeometryHeader = 0;
/*     */   static final int compressedGeometryRenderMethod = 0;
/*     */   static final int compressedGeometryRetained = 0;
/*     */   static final int coneSound = 0;
/*     */   static final int coneSoundRetained = 0;
/*     */   static final int danglingReferenceException = 0;
/*     */   static final int decalGroup = 0;
/*     */   static final int decalGroupRetained = 0;
/*     */   static final int defaultRenderMethod = 0;
/*     */   static final int depthComponent = 0;
/*     */   static final int depthComponentFloat = 0;
/*     */   static final int depthComponentFloatRetained = 0;
/*     */   static final int depthComponentInt = 0;
/*     */   static final int depthComponentIntRetained = 0;
/*     */   static final int depthComponentNative = 0;
/*     */   static final int depthComponentNativeRetained = 0;
/*     */   static final int depthComponentRetained = 0;
/*     */   static final int directionalLight = 0;
/*     */   static final int directionalLightRetained = 0;
/*     */   static final int displayListRenderMethod = 0;
/*     */   static final int distanceLOD = 0;
/*     */   static final int environmentSet = 0;
/*     */   static final int eventCatcher = 0;
/*     */   static final int exponentialFog = 0;
/*     */   static final int exponentialFogRetained = 0;
/*     */   static final int fog = 0;
/*     */   static final int fogRetained = 0;
/*     */   static final int font3D = 0;
/*     */   static final int fontExtrusion = 0;
/*     */   static final int generalizedStrip = 0;
/*     */   static final int generalizedStripFlags = 0;
/*     */   static final int generalizedVertexList = 0;
/*     */   static final int geometry = 0;
/*     */   static final int geometryArray = 0;
/*     */   static final int geometryArrayRetained = 0;
/*     */   static final int geometryAtom = 0;
/*     */   static final int geometryDecompressor = 0;
/*     */   static final int geometryDecompressorRetained = 0;
/*     */   static final int geometryDecompressorShape3D = 0;
/*     */   static final int geometryLock = 0;
/*     */   static final int geometryLockInterface = 0;
/*     */   static final int geometryRetained = 0;
/*     */   static final int geometryStripArray = 0;
/*     */   static final int geometryStripArrayRetained = 0;
/*     */   static final int geometryStructure = 0;
/*     */   static final int geometryUpdater = 0;
/*     */   static final int graphicsConfigTemplate3D = 0;
/*     */   static final int graphicsContext3D = 0;
/*     */   static final int group = 0;
/*     */   static final int groupRetained = 0;
/*     */   static final int hashKey = 0;
/*     */   static final int hiResCoord = 0;
/*     */   static final int illegalRenderingStateException = 0;
/*     */   static final int illegalSharingException = 0;
/*     */   static final int imageComponent = 0;
/*     */   static final int imageComponent2D = 0;
/*     */   static final int imageComponent2DRetained = 0;
/*     */   static final int imageComponent3D = 0;
/*     */   static final int imageComponent3DRetained = 0;
/*     */   static final int imageComponentRetained = 0;
/*     */   static final int indexedGeometryArray = 0;
/*     */   static final int indexedGeometryArrayRetained = 0;
/*     */   static final int indexedGeometryStripArray = 0;
/*     */   static final int indexedGeometryStripArrayRetained = 0;
/*     */   static final int indexedLineArray = 0;
/*     */   static final int indexedLineArrayRetained = 0;
/*     */   static final int indexedLineStripArray = 0;
/*     */   static final int indexedLineStripArrayRetained = 0;
/*     */   static final int indexedPointArray = 0;
/*     */   static final int indexedPointArrayRetained = 0;
/*     */   static final int indexedQuadArray = 0;
/*     */   static final int indexedQuadArrayRetained = 0;
/*     */   static final int indexedTriangleArray = 0;
/*     */   static final int indexedTriangleArrayRetained = 0;
/*     */   static final int indexedTriangleFanArray = 0;
/*     */   static final int indexedTriangleFanArrayRetained = 0;
/*     */   static final int indexedTriangleStripArray = 0;
/*     */   static final int indexedTriangleStripArrayRetained = 0;
/*     */   static final int inputDevice = 0;
/*     */   static final int inputDeviceBlockingThread = 0;
/*     */   static final int inputDeviceScheduler = 0;
/*     */   static final int interpolator = 0;
/*     */   static final int j3dDataInputStream = 0;
/*     */   static final int j3dDataOutputStream = 0;
/*     */   static final int j3dDebug = 0;
/*     */   static final int j3dI18N = 0;
/*     */   static final int j3dMessage = 0;
/*     */   static final int j3dNodeTable = 0;
/*     */   static final int j3dQueryProps = 0;
/*     */   static final int j3dStructure = 0;
/*     */   static final int j3dThread = 0;
/*     */   static final int j3dThreadData = 0;
/*     */   static final int lOD = 0;
/*     */   static final int leaf = 0;
/*     */   static final int leafRetained = 0;
/*     */   static final int light = 0;
/*     */   static final int lightBin = 0;
/*     */   static final int lightRetained = 0;
/*     */   static final int lightSet = 0;
/*     */   static final int lineArray = 0;
/*     */   static final int lineArrayRetained = 0;
/*     */   static final int lineAttributes = 0;
/*     */   static final int lineAttributesRetained = 0;
/*     */   static final int lineStripArray = 0;
/*     */   static final int lineStripArrayRetained = 0;
/*     */   static final int linearFog = 0;
/*     */   static final int linearFogRetained = 0;
/*     */   static final int link = 0;
/*     */   static final int linkRetained = 0;
/*     */   static final int locale = 0;
/*     */   static final int mRSWLock = 0;
/*     */   static final int masterControl = 0;
/*     */   static final int material = 0;
/*     */   static final int materialRetained = 0;
/*     */   static final int mediaContainer = 0;
/*     */   static final int mediaContainerRetained = 0;
/*     */   static final int modelClip = 0;
/*     */   static final int modelClipRetained = 0;
/*     */   static final int morph = 0;
/*     */   static final int morphRetained = 0;
/*     */   static final int multipleParentException = 0;
/*     */   static final int node = 0;
/*     */   static final int nodeComponent = 0;
/*     */   static final int nodeComponentRetained = 0;
/*     */   static final int nodeReferenceTable = 0;
/*     */   static final int nodeRetained = 0;
/*     */   static final int objectUpdate = 0;
/*     */   static final int orderedBin = 0;
/*     */   static final int orderedCollection = 0;
/*     */   static final int orderedGroup = 0;
/*     */   static final int orderedGroupRetained = 0;
/*     */   static final int pathInterpolator = 0;
/*     */   static final int physicalBody = 0;
/*     */   static final int physicalEnvironment = 0;
/*     */   static final int pickBounds = 0;
/*     */   static final int pickCone = 0;
/*     */   static final int pickCylinderRay = 0;
/*     */   static final int pickCylinderSegment = 0;
/*     */   static final int pickPoint = 0;
/*     */   static final int pickRay = 0;
/*     */   static final int pickSegment = 0;
/*     */   static final int pickShape = 0;
/*     */   static final int picking = 0;
/*     */   static final int pointArray = 0;
/*     */   static final int pointArrayRetained = 0;
/*     */   static final int pointAttributes = 0;
/*     */   static final int pointAttributesRetained = 0;
/*     */   static final int pointLight = 0;
/*     */   static final int pointLightRetained = 0;
/*     */   static final int pointSound = 0;
/*     */   static final int pointSoundRetained = 0;
/*     */   static final int polygonAttributes = 0;
/*     */   static final int polygonAttributesRetained = 0;
/*     */   static final int positionInterpolator = 0;
/*     */   static final int positionPathInterpolator = 0;
/*     */   static final int quadArray = 0;
/*     */   static final int quadArrayRetained = 0;
/*     */   static final int raster = 0;
/*     */   static final int rasterRetained = 0;
/*     */   static final int renderAtom = 0;
/*     */   static final int renderBin = 0;
/*     */   static final int renderBinLock = 0;
/*     */   static final int renderMethod = 0;
/*     */   static final int renderMolecule = 0;
/*     */   static final int renderer = 0;
/*     */   static final int rendererStructure = 0;
/*     */   static final int renderingAttributes = 0;
/*     */   static final int renderingAttributesRetained = 0;
/*     */   static final int renderingAttributesStructure = 0;
/*     */   static final int renderingEnvironmentStructure = 0;
/*     */   static final int restrictedAccessException = 0;
/*     */   static final int rotPosPathInterpolator = 0;
/*     */   static final int rotPosScalePathInterpolator = 0;
/*     */   static final int rotationInterpolator = 0;
/*     */   static final int rotationPathInterpolator = 0;
/*     */   static final int scaleInterpolator = 0;
/*     */   static final int sceneGraphCycleException = 0;
/*     */   static final int sceneGraphObject = 0;
/*     */   static final int sceneGraphObjectRetained = 0;
/*     */   static final int sceneGraphPath = 0;
/*     */   static final int screen3D = 0;
/*     */   static final int screenViewCache = 0;
/*     */   static final int sensor = 0;
/*     */   static final int sensorRead = 0;
/*     */   static final int setLiveState = 0;
/*     */   static final int shape3D = 0;
/*     */   static final int shape3DRetained = 0;
/*     */   static final int sharedGroup = 0;
/*     */   static final int sharedGroupRetained = 0;
/*     */   static final int sound = 0;
/*     */   static final int soundException = 0;
/*     */   static final int soundRenderer = 0;
/*     */   static final int soundRetained = 0;
/*     */   static final int soundScheduler = 0;
/*     */   static final int soundStructure = 0;
/*     */   static final int soundscape = 0;
/*     */   static final int soundscapeRetained = 0;
/*     */   static final int spotLight = 0;
/*     */   static final int spotLightRetained = 0;
/*     */   static final int structureUpdateThread = 0;
/*     */   static final int Switch = 0;
/*     */   static final int switchRetained = 0;
/*     */   static final int switchValueInterpolator = 0;
/*     */   static final int table = 0;
/*     */   static final int texCoordGeneration = 0;
/*     */   static final int texCoordGenerationRetained = 0;
/*     */   static final int text3D = 0;
/*     */   static final int text3DRenderMethod = 0;
/*     */   static final int text3DRetained = 0;
/*     */   static final int texture = 0;
/*     */   static final int texture2D = 0;
/*     */   static final int texture2DRetained = 0;
/*     */   static final int texture3D = 0;
/*     */   static final int texture3DRetained = 0;
/*     */   static final int textureAttributes = 0;
/*     */   static final int textureAttributesRetained = 0;
/*     */   static final int textureBin = 0;
/*     */   static final int textureRetained = 0;
/*     */   static final int textureSetting = 0;
/*     */   static final int timerThread = 0;
/*     */   static final int transform3D = 0;
/*     */   static final int transformGroup = 0;
/*     */   static final int transformGroupRetained = 0;
/*     */   static final int transformStructure = 0;
/*     */   static final int transparencyAttributes = 0;
/*     */   static final int transparencyAttributesRetained = 0;
/*     */   static final int transparencyInterpolator = 0;
/*     */   static final int triangleArray = 0;
/*     */   static final int triangleArrayRetained = 0;
/*     */   static final int triangleFanArray = 0;
/*     */   static final int triangleFanArrayRetained = 0;
/*     */   static final int triangleStripArray = 0;
/*     */   static final int triangleStripArrayRetained = 0;
/*     */   static final int unorderList = 0;
/*     */   static final int vertexArrayRenderMethod = 0;
/*     */   static final int view = 0;
/*     */   static final int viewCache = 0;
/*     */   static final int viewPlatform = 0;
/*     */   static final int viewPlatformRetained = 0;
/*     */   static final int virtualUniverse = 0;
/*     */   static final int wakeupAnd = 0;
/*     */   static final int wakeupAndOfOrs = 0;
/*     */   static final int wakeupCondition = 0;
/*     */   static final int wakeupCriteriaEnumerator = 0;
/*     */   static final int wakeupCriterion = 0;
/*     */   static final int wakeupOnAWTEvent = 0;
/*     */   static final int wakeupOnActivation = 0;
/*     */   static final int wakeupOnBehaviorPost = 0;
/*     */   static final int wakeupOnCollisionEntry = 0;
/*     */   static final int wakeupOnCollisionExit = 0;
/*     */   static final int wakeupOnCollisionMovement = 0;
/*     */   static final int wakeupOnDeactivation = 0;
/*     */   static final int wakeupOnElapsedFrames = 0;
/*     */   static final int wakeupOnElapsedTime = 0;
/*     */   static final int wakeupOnElapsedTimeHeap = 0;
/*     */   static final int wakeupOnSensorEntry = 0;
/*     */   static final int wakeupOnSensorExit = 0;
/*     */   static final int wakeupOnTransformChange = 0;
/*     */   static final int wakeupOnViewPlatformEntry = 0;
/*     */   static final int wakeupOnViewPlatformExit = 0;
/*     */   static final int wakeupOr = 0;
/*     */   static final int wakeupOrOfAnds = 0;
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\J3dDebug.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */