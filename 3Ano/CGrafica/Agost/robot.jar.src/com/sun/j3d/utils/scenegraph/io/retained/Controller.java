/*     */ package com.sun.j3d.utils.scenegraph.io.retained;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.NamedObjectException;
/*     */ import com.sun.j3d.utils.scenegraph.io.ObjectNotLoadedException;
/*     */ import com.sun.j3d.utils.scenegraph.io.SceneGraphStateProvider;
/*     */ import com.sun.j3d.utils.scenegraph.io.UnsupportedUniverseException;
/*     */ import com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.universe.SimpleUniverseState;
/*     */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.NullSceneGraphObjectState;
/*     */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.SceneGraphObjectState;
/*     */ import com.sun.j3d.utils.universe.ConfiguredUniverse;
/*     */ import com.sun.j3d.utils.universe.SimpleUniverse;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.ObjectStreamClass;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.ListIterator;
/*     */ import javax.media.j3d.BoundingBox;
/*     */ import javax.media.j3d.BoundingPolytope;
/*     */ import javax.media.j3d.BoundingSphere;
/*     */ import javax.media.j3d.Bounds;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.media.j3d.CapabilityNotSetException;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.SharedGroup;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.vecmath.Color3f;
/*     */ import javax.vecmath.Color4f;
/*     */ import javax.vecmath.Matrix4d;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Tuple3d;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Tuple4d;
/*     */ import javax.vecmath.Tuple4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4d;
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Controller
/*     */ {
/*     */   protected static final long SYMBOL_TABLE_PTR = 30L;
/*     */   protected static final long BG_DIR_PTR = 38L;
/*     */   protected static final long NAMES_OBJECTS_TABLE_PTR = 46L;
/*     */   protected static final long NODE_TYPES_PTR = 52L;
/*     */   protected static final long UNIVERSE_CONFIG_PTR = 60L;
/*     */   protected static final long BRANCH_GRAPH_COUNT = 68L;
/*     */   protected static final long FILE_DESCRIPTION = 72L;
/*     */   protected SymbolTable symbolTable;
/* 101 */   protected NullSceneGraphObjectState nullObject = new NullSceneGraphObjectState(null, this);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int currentFileVersion;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   protected int outputFileVersion = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   protected ClassLoader classLoader = ClassLoader.getSystemClassLoader();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean useSuperClass = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   private int imageCompression = 0;
/*     */ 
/*     */   
/*     */   public Controller() {
/*     */     try {
/* 145 */       if (System.getProperty("j3d.io.UseSuperClassIfNoChildClass") != null) {
/* 146 */         this.useSuperClass = true;
/*     */       }
/* 148 */       String str = System.getProperty("j3d.io.ImageCompression");
/* 149 */       if (str != null)
/* 150 */         if (str.equalsIgnoreCase("None")) {
/* 151 */           this.imageCompression = 0;
/* 152 */         } else if (str.equalsIgnoreCase("GZIP")) {
/* 153 */           this.imageCompression = 1;
/* 154 */         } else if (str.equalsIgnoreCase("JPEG")) {
/* 155 */           this.imageCompression = 2;
/*     */         }  
/* 157 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 162 */   public final SymbolTable getSymbolTable() { return this.symbolTable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   public int getOutputFileVersion() { return this.outputFileVersion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public int getCurrentFileVersion() { return this.currentFileVersion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public SceneGraphObjectState createState(SceneGraphObject paramSceneGraphObject) { return createState(paramSceneGraphObject, this.symbolTable.getSymbol(paramSceneGraphObject)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SceneGraphObjectState createState(SceneGraphObject paramSceneGraphObject, SymbolTableData paramSymbolTableData) {
/* 193 */     if (paramSceneGraphObject == null) return this.nullObject;
/*     */     
/* 195 */     if (paramSymbolTableData != null) {
/* 196 */       paramSymbolTableData.incrementReferenceCount();
/* 197 */       this.symbolTable.setBranchGraphID(paramSymbolTableData);
/* 198 */       if (paramSymbolTableData.getNodeState() != null)
/* 199 */         return paramSymbolTableData.getNodeState(); 
/*     */     } else {
/* 201 */       paramSymbolTableData = this.symbolTable.createSymbol(paramSceneGraphObject);
/*     */     } 
/* 203 */     return createState(paramSymbolTableData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SceneGraphObjectState createState(SymbolTableData paramSymbolTableData) {
/*     */     SceneGraphObjectState sceneGraphObjectState;
/* 211 */     SceneGraphObject sceneGraphObject = paramSymbolTableData.getJ3dNode();
/* 212 */     if (sceneGraphObject == null) return this.nullObject;
/*     */     
/* 214 */     String str = sceneGraphObject.getClass().getName();
/*     */     
/*     */     try {
/*     */       Class clazz;
/*     */       
/* 219 */       if (sceneGraphObject instanceof SceneGraphStateProvider) {
/* 220 */         clazz = ((SceneGraphStateProvider)sceneGraphObject).getStateClass();
/*     */       } else {
/* 222 */         clazz = Class.forName("com.sun.j3d.utils.scenegraph.io.state." + str + "State");
/* 223 */       }  sceneGraphObjectState = constructStateObj(paramSymbolTableData, clazz, sceneGraphObject.getClass());
/* 224 */     } catch (ClassNotFoundException classNotFoundException) {
/* 225 */       sceneGraphObjectState = checkSuperClasses(paramSymbolTableData);
/* 226 */       if (!(sceneGraphObject instanceof com.sun.j3d.utils.scenegraph.io.SceneGraphIO))
/* 227 */         System.out.println("Could not find com.sun.j3d.utils.scenegraph.io.state." + str + "State, using superclass " + sceneGraphObjectState.getClass().getName()); 
/* 228 */       if (sceneGraphObjectState == null) {
/* 229 */         throw new SGIORuntimeException("No State class for " + sceneGraphObject.getClass().getName());
/*     */       }
/*     */     } 
/*     */     
/* 233 */     paramSymbolTableData.nodeState = sceneGraphObjectState;
/*     */     
/* 235 */     return sceneGraphObjectState;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private SceneGraphObjectState constructStateObj(SymbolTableData paramSymbolTableData, Class paramClass1, Class paramClass2) {
/* 241 */     SceneGraphObjectState sceneGraphObjectState = null;
/*     */     
/*     */     try {
/* 244 */       Constructor constructor = paramClass1.getConstructor(new Class[] { SymbolTableData.class, Controller.class });
/*     */ 
/*     */ 
/*     */       
/* 248 */       sceneGraphObjectState = (SceneGraphObjectState)constructor.newInstance(new Object[] { paramSymbolTableData, this });
/*     */     
/*     */     }
/* 251 */     catch (NoSuchMethodException noSuchMethodException) {
/* 252 */       System.out.println("Looking for Constructor (" + paramSymbolTableData.j3dNode.getClass().getName() + ", Controller )");
/* 253 */       throw new SGIORuntimeException("1 Broken State class for " + paramClass1.getName());
/*     */     }
/* 255 */     catch (InvocationTargetException invocationTargetException) {
/* 256 */       invocationTargetException.printStackTrace();
/* 257 */       throw new SGIORuntimeException("2 Broken State class for " + paramClass1.getName());
/*     */     }
/* 259 */     catch (IllegalAccessException illegalAccessException) {
/* 260 */       throw new SGIORuntimeException("3 Broken State class for " + paramClass1.getName());
/*     */     }
/* 262 */     catch (InstantiationException instantiationException) {
/* 263 */       throw new SGIORuntimeException("4 Broken State class for " + paramClass1.getName());
/*     */     } 
/*     */ 
/*     */     
/* 267 */     return sceneGraphObjectState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SceneGraphObjectState checkSuperClasses(SymbolTableData paramSymbolTableData) {
/* 276 */     Class clazz1 = paramSymbolTableData.j3dNode.getClass().getSuperclass();
/* 277 */     Class clazz2 = null;
/* 278 */     boolean bool = false;
/*     */     
/*     */     while (true) {
/* 281 */       if (((clazz1 != null) ? 1 : 0) & (!bool ? 1 : 0)) {
/* 282 */         String str = clazz1.getName();
/*     */         
/*     */         try {
/* 285 */           clazz2 = Class.forName("com.sun.j3d.utils.scenegraph.io.state." + str + "State");
/* 286 */         } catch (ClassNotFoundException classNotFoundException) {
/* 287 */           clazz2 = null;
/*     */         } 
/*     */         
/* 290 */         if (clazz2 != null) {
/* 291 */           bool = true; continue;
/*     */         } 
/* 293 */         clazz1 = clazz1.getSuperclass(); continue;
/*     */       }  break;
/*     */     } 
/* 296 */     if (clazz1 == null) {
/* 297 */       throw new SGIORuntimeException("Unsupported class " + paramSymbolTableData.j3dNode.getClass().getName());
/*     */     }
/* 299 */     return constructStateObj(paramSymbolTableData, clazz2, clazz1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput, SceneGraphObjectState paramSceneGraphObjectState) throws IOException {
/* 305 */     int i = getStateID(paramSceneGraphObjectState);
/*     */     
/* 307 */     paramDataOutput.writeInt(i);
/*     */     
/* 309 */     if (i == 0) {
/* 310 */       paramDataOutput.writeUTF(paramSceneGraphObjectState.getClass().getName());
/*     */     }
/*     */     
/* 313 */     paramSceneGraphObjectState.writeObject(paramDataOutput);
/*     */   }
/*     */   
/*     */   public SceneGraphObjectState readObject(DataInput paramDataInput) throws IOException {
/* 317 */     int i = paramDataInput.readInt();
/*     */     
/* 319 */     SceneGraphObjectState sceneGraphObjectState = null;
/*     */     
/* 321 */     if (i == -1)
/* 322 */       return this.nullObject; 
/* 323 */     if (i == 0) {
/* 324 */       String str = paramDataInput.readUTF();
/*     */       
/*     */       try {
/* 327 */         Class clazz = Class.forName(str, true, this.classLoader);
/*     */         
/* 329 */         Constructor constructor = clazz.getConstructor(new Class[] { SymbolTableData.class, Controller.class });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 335 */         sceneGraphObjectState = (SceneGraphObjectState)constructor.newInstance(new Object[] { null, this });
/*     */ 
/*     */       
/*     */       }
/* 339 */       catch (ClassNotFoundException classNotFoundException) {
/* 340 */         throw new IOException("Error Loading State Class " + str + "  " + classNotFoundException.getMessage());
/* 341 */       } catch (NoSuchMethodException noSuchMethodException) {
/* 342 */         throw new IOException("1 Broken State class for " + str + "  " + noSuchMethodException.getMessage());
/*     */       }
/* 344 */       catch (InvocationTargetException invocationTargetException) {
/* 345 */         invocationTargetException.printStackTrace();
/* 346 */         throw new IOException("2 Broken State class for " + str);
/*     */       }
/* 348 */       catch (IllegalAccessException illegalAccessException) {
/* 349 */         throw new IOException("3 Broken State class for " + str);
/*     */       }
/* 351 */       catch (InstantiationException instantiationException) {
/* 352 */         throw new IOException("4 Broken State class for " + str);
/*     */       } 
/*     */     } else {
/*     */       
/* 356 */       sceneGraphObjectState = createCoreState(i);
/*     */     } 
/*     */     
/* 359 */     sceneGraphObjectState.readObject(paramDataInput);
/*     */     
/* 361 */     return sceneGraphObjectState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 370 */   public void setClassLoader(ClassLoader paramClassLoader) { this.classLoader = paramClassLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 380 */   public ClassLoader getClassLoader() { return this.classLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeNodeComponents(DataOutput paramDataOutput) throws IOException {
/* 392 */     ListIterator listIterator = this.symbolTable.getUnsavedNodeComponents();
/* 393 */     paramDataOutput.writeInt(this.symbolTable.getUnsavedNodeComponentsSize());
/* 394 */     while (listIterator.hasNext()) {
/* 395 */       SymbolTableData symbolTableData = (SymbolTableData)listIterator.next();
/*     */       
/* 397 */       paramDataOutput.writeInt(symbolTableData.nodeID);
/* 398 */       paramDataOutput.writeLong(0L);
/*     */       
/* 400 */       writeObject(paramDataOutput, symbolTableData.getNodeState());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readNodeComponents(DataInput paramDataInput) throws IOException {
/* 408 */     int i = paramDataInput.readInt();
/*     */     
/* 410 */     for (byte b = 0; b < i; b++) {
/*     */ 
/*     */       
/* 413 */       int j = paramDataInput.readInt();
/* 414 */       long l = paramDataInput.readLong();
/*     */       
/* 416 */       SceneGraphObjectState sceneGraphObjectState = readObject(paramDataInput);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeSharedGroup(DataOutput paramDataOutput, SharedGroup paramSharedGroup, SymbolTableData paramSymbolTableData) throws IOException {
/* 424 */     SceneGraphObjectState sceneGraphObjectState = createState(paramSharedGroup, paramSymbolTableData);
/* 425 */     this.symbolTable.startUnsavedNodeComponentFrame();
/* 426 */     writeObject(paramDataOutput, sceneGraphObjectState);
/* 427 */     writeNodeComponents(paramDataOutput);
/* 428 */     this.symbolTable.endUnsavedNodeComponentFrame();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readSharedGroup(DataInput paramDataInput) throws IOException {
/* 435 */     SceneGraphObjectState sceneGraphObjectState = readObject(paramDataInput);
/* 436 */     readNodeComponents(paramDataInput);
/*     */     
/* 438 */     return sceneGraphObjectState.getNodeID();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeUniverse(DataOutput paramDataOutput, SimpleUniverse paramSimpleUniverse, boolean paramBoolean) throws IOException, UnsupportedUniverseException, CapabilityNotSetException {
/* 446 */     if (paramSimpleUniverse == null) {
/* 447 */       paramDataOutput.writeUTF("null");
/* 448 */     } else if (paramSimpleUniverse instanceof SimpleUniverse) {
/* 449 */       paramDataOutput.writeUTF(paramSimpleUniverse.getClass().getName());
/* 450 */       SimpleUniverseState simpleUniverseState = new SimpleUniverseState(paramSimpleUniverse, this);
/* 451 */       simpleUniverseState.writeObject(paramDataOutput);
/*     */       
/* 453 */       if (paramBoolean) {
/* 454 */         simpleUniverseState.detachAllGraphs();
/* 455 */         int[] arrayOfInt = simpleUniverseState.getAllGraphIDs();
/* 456 */         for (byte b = 0; b < arrayOfInt.length; b++) {
/* 457 */           SymbolTableData symbolTableData = this.symbolTable.getBranchGraphRoot(arrayOfInt[b]);
/* 458 */           System.out.println("Writing " + arrayOfInt[b] + "  " + symbolTableData.j3dNode);
/* 459 */           writeBranchGraph((BranchGroup)symbolTableData.j3dNode, null);
/*     */         } 
/*     */         
/* 462 */         simpleUniverseState.attachAllGraphs();
/*     */       } 
/*     */     } else {
/* 465 */       throw new UnsupportedUniverseException("Current Implementation only support SimpleUniverse/ConfiguredUniverse.");
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
/*     */   public ConfiguredUniverse readUniverse(DataInput paramDataInput, boolean paramBoolean, Canvas3D paramCanvas3D) throws IOException {
/* 478 */     String str = paramDataInput.readUTF();
/*     */     
/* 480 */     if (str.equals("null"))
/* 481 */       return null; 
/* 482 */     if (str.equals("com.sun.j3d.utils.universe.SimpleUniverse") || str.equals("com.sun.j3d.utils.universe.ConfiguredUniverse")) {
/*     */       
/* 484 */       SimpleUniverseState simpleUniverseState = new SimpleUniverseState(this);
/* 485 */       simpleUniverseState.readObject(paramDataInput, paramCanvas3D);
/*     */       
/* 487 */       if (paramBoolean) {
/* 488 */         int[] arrayOfInt = simpleUniverseState.getAllGraphIDs();
/* 489 */         readBranchGraphs(arrayOfInt);
/*     */         
/* 491 */         simpleUniverseState.buildGraph();
/*     */       } 
/*     */       
/* 494 */       return simpleUniverseState.getNode();
/*     */     } 
/* 496 */     throw new IOException("Unrecognized universe class " + str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void readBranchGraphs(int[] paramArrayOfInt) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void writeBranchGraph(BranchGroup paramBranchGroup, Serializable paramSerializable) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 515 */   public void reset() { this.symbolTable.clear(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SceneGraphObjectState createCoreState(int paramInt) {
/* 525 */     if (paramInt == -1)
/* 526 */       return this.nullObject; 
/* 527 */     if (paramInt == 0) {
/* 528 */       return null;
/*     */     }
/* 530 */     Class clazz = getNodeClassFromID(paramInt - 1);
/* 531 */     String str1 = clazz.getName();
/* 532 */     String str2 = "com.sun.j3d.utils.scenegraph.io.state." + str1 + "State";
/*     */     
/* 534 */     SceneGraphObjectState sceneGraphObjectState = null;
/*     */     try {
/* 536 */       Class clazz1 = Class.forName(str2);
/* 537 */       Constructor constructor = clazz1.getConstructor(new Class[] { SymbolTableData.class, Controller.class });
/* 538 */       sceneGraphObjectState = (SceneGraphObjectState)constructor.newInstance(new Object[] { null, this });
/* 539 */     } catch (Exception exception) {
/* 540 */       exception.printStackTrace();
/*     */     } 
/*     */     
/* 543 */     return sceneGraphObjectState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getStateID(SceneGraphObjectState paramSceneGraphObjectState) {
/* 551 */     if (paramSceneGraphObjectState instanceof NullSceneGraphObjectState) {
/* 552 */       return -1;
/*     */     }
/* 554 */     return getNodeClassID(paramSceneGraphObjectState.getNode()) + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   Class[] j3dClasses = { 
/* 559 */       javax.media.j3d.Alpha.class, javax.media.j3d.Appearance.class, javax.media.j3d.Billboard.class, BranchGroup.class, javax.media.j3d.ColoringAttributes.class, javax.media.j3d.ConeSound.class, javax.media.j3d.DecalGroup.class, javax.media.j3d.DirectionalLight.class, javax.media.j3d.DistanceLOD.class, javax.media.j3d.ExponentialFog.class, javax.media.j3d.Font3D.class, javax.media.j3d.Group.class, javax.media.j3d.ImageComponent2D.class, javax.media.j3d.ImageComponent3D.class, javax.media.j3d.IndexedLineArray.class, javax.media.j3d.IndexedLineStripArray.class, javax.media.j3d.IndexedPointArray.class, javax.media.j3d.IndexedQuadArray.class, javax.media.j3d.IndexedTriangleArray.class, javax.media.j3d.IndexedTriangleFanArray.class, javax.media.j3d.IndexedTriangleStripArray.class, javax.media.j3d.LinearFog.class, javax.media.j3d.LineArray.class, javax.media.j3d.LineAttributes.class, javax.media.j3d.LineStripArray.class, javax.media.j3d.Link.class, javax.media.j3d.Material.class, javax.media.j3d.Morph.class, javax.media.j3d.OrderedGroup.class, javax.media.j3d.OrientedShape3D.class, javax.media.j3d.PathInterpolator.class, javax.media.j3d.PointArray.class, javax.media.j3d.PointAttributes.class, javax.media.j3d.PositionInterpolator.class, javax.media.j3d.PositionPathInterpolator.class, javax.media.j3d.QuadArray.class, javax.media.j3d.RenderingAttributes.class, javax.media.j3d.RotationInterpolator.class, javax.media.j3d.RotationPathInterpolator.class, javax.media.j3d.RotPosPathInterpolator.class, javax.media.j3d.RotPosScalePathInterpolator.class, javax.media.j3d.ScaleInterpolator.class, javax.media.j3d.Shape3D.class, SharedGroup.class, javax.media.j3d.Soundscape.class, javax.media.j3d.SpotLight.class, javax.media.j3d.Switch.class, javax.media.j3d.SwitchValueInterpolator.class, javax.media.j3d.Text3D.class, javax.media.j3d.Texture2D.class, javax.media.j3d.Texture3D.class, javax.media.j3d.TextureAttributes.class, javax.media.j3d.TextureCubeMap.class, javax.media.j3d.TextureUnitState.class, javax.media.j3d.TransformGroup.class, javax.media.j3d.TransformInterpolator.class, javax.media.j3d.TransparencyAttributes.class, javax.media.j3d.TransparencyInterpolator.class, javax.media.j3d.TriangleArray.class, javax.media.j3d.TriangleFanArray.class, javax.media.j3d.TriangleStripArray.class, javax.media.j3d.ViewPlatform.class };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class getNodeClassFromID(int paramInt) {
/* 625 */     if (paramInt < 0) {
/* 626 */       return null;
/*     */     }
/* 628 */     return this.j3dClasses[paramInt];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNodeClassID(SceneGraphObject paramSceneGraphObject) {
/* 635 */     byte b1 = -1;
/* 636 */     Class clazz = paramSceneGraphObject.getClass();
/*     */     
/* 638 */     for (byte b2 = 0; b2 < this.j3dClasses.length && b1 == -1; b2++) {
/* 639 */       if (this.j3dClasses[b2] == clazz)
/* 640 */         b1 = b2; 
/*     */     } 
/* 642 */     return b1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 649 */   public void addNamedObject(String paramString, SceneGraphObject paramSceneGraphObject) { this.symbolTable.addNamedObject(paramString, paramSceneGraphObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 656 */   public SceneGraphObject getNamedObject(String paramString) throws NamedObjectException, ObjectNotLoadedException { return this.symbolTable.getNamedObject(paramString); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 663 */   public String[] getNames() { return this.symbolTable.getNames(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeSerializedData(DataOutput paramDataOutput, Serializable paramSerializable) throws IOException {
/* 671 */     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 672 */     ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
/*     */     
/* 674 */     objectOutputStream.writeObject(paramSerializable);
/*     */     
/* 676 */     byteArrayOutputStream.close();
/*     */     
/* 678 */     byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
/* 679 */     paramDataOutput.writeInt(arrayOfByte.length);
/* 680 */     if (arrayOfByte.length != 0)
/* 681 */       paramDataOutput.write(arrayOfByte); 
/*     */   }
/*     */   
/*     */   public Object readSerializedData(DataInput paramDataInput) throws IOException {
/* 685 */     int i = paramDataInput.readInt();
/* 686 */     Object object = null;
/*     */     
/* 688 */     if (i != 0) {
/* 689 */       byte[] arrayOfByte = new byte[i];
/* 690 */       paramDataInput.readFully(arrayOfByte);
/*     */       
/* 692 */       ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
/* 693 */       J3dIOObjectInputStream j3dIOObjectInputStream = new J3dIOObjectInputStream(byteArrayInputStream);
/*     */       
/*     */       try {
/* 696 */         object = j3dIOObjectInputStream.readObject();
/* 697 */         j3dIOObjectInputStream.close();
/* 698 */       } catch (ClassNotFoundException classNotFoundException) {
/* 699 */         System.out.println("WARNING: Unable to load UserData");
/* 700 */         System.out.println("Class missing " + classNotFoundException);
/* 701 */         j3dIOObjectInputStream.close();
/*     */       } 
/*     */     } 
/*     */     
/* 705 */     return object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void skipUserData(DataInput paramDataInput) throws IOException {
/* 712 */     int i = paramDataInput.readInt();
/* 713 */     paramDataInput.skipBytes(i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeColor3f(DataOutput paramDataOutput, Color3f paramColor3f) throws IOException {
/* 719 */     paramDataOutput.writeFloat(paramColor3f.x);
/* 720 */     paramDataOutput.writeFloat(paramColor3f.y);
/* 721 */     paramDataOutput.writeFloat(paramColor3f.z);
/*     */   }
/*     */ 
/*     */   
/* 725 */   public Color3f readColor3f(DataInput paramDataInput) throws IOException { return new Color3f(paramDataInput.readFloat(), paramDataInput.readFloat(), paramDataInput.readFloat()); }
/*     */ 
/*     */ 
/*     */   
/* 729 */   public void writeColor4f(DataOutput paramDataOutput, Color4f paramColor4f) throws IOException { writeTuple4f(paramDataOutput, paramColor4f); }
/*     */ 
/*     */ 
/*     */   
/* 733 */   public Color4f readColor4f(DataInput paramDataInput) throws IOException { return (Color4f)readTuple4f(paramDataInput, new Color4f()); }
/*     */ 
/*     */ 
/*     */   
/* 737 */   public void writePoint3f(DataOutput paramDataOutput, Point3f paramPoint3f) throws IOException { writeTuple3f(paramDataOutput, paramPoint3f); }
/*     */ 
/*     */ 
/*     */   
/* 741 */   public Point3f readPoint3f(DataInput paramDataInput) throws IOException { return (Point3f)readTuple3f(paramDataInput, new Point3f()); }
/*     */ 
/*     */ 
/*     */   
/* 745 */   public void writePoint3d(DataOutput paramDataOutput, Point3d paramPoint3d) throws IOException { writeTuple3d(paramDataOutput, paramPoint3d); }
/*     */ 
/*     */ 
/*     */   
/* 749 */   public Point3d readPoint3d(DataInput paramDataInput) throws IOException { return (Point3d)readTuple3d(paramDataInput, new Point3d()); }
/*     */ 
/*     */ 
/*     */   
/* 753 */   public void writeVector3f(DataOutput paramDataOutput, Vector3f paramVector3f) throws IOException { writeTuple3f(paramDataOutput, paramVector3f); }
/*     */ 
/*     */ 
/*     */   
/* 757 */   public Vector3f readVector3f(DataInput paramDataInput) throws IOException { return (Vector3f)readTuple3f(paramDataInput, new Vector3f()); }
/*     */ 
/*     */ 
/*     */   
/* 761 */   public void writeVector4d(DataOutput paramDataOutput, Vector4d paramVector4d) throws IOException { writeTuple4d(paramDataOutput, paramVector4d); }
/*     */ 
/*     */ 
/*     */   
/* 765 */   public Vector4d readVector4d(DataInput paramDataInput) throws IOException { return (Vector4d)readTuple4d(paramDataInput, new Vector4d()); }
/*     */ 
/*     */ 
/*     */   
/* 769 */   public void writeVector4f(DataOutput paramDataOutput, Vector4f paramVector4f) throws IOException { writeTuple4f(paramDataOutput, paramVector4f); }
/*     */ 
/*     */ 
/*     */   
/* 773 */   public Vector4f readVector4f(DataInput paramDataInput) throws IOException { return (Vector4f)readTuple4f(paramDataInput, new Vector4f()); }
/*     */ 
/*     */ 
/*     */   
/* 777 */   public void writeQuat4f(DataOutput paramDataOutput, Quat4f paramQuat4f) throws IOException { writeTuple4f(paramDataOutput, paramQuat4f); }
/*     */ 
/*     */ 
/*     */   
/* 781 */   public Quat4f readQuat4f(DataInput paramDataInput) throws IOException { return (Quat4f)readTuple4f(paramDataInput, new Quat4f()); }
/*     */ 
/*     */   
/*     */   public void writeMatrix4d(DataOutput paramDataOutput, Matrix4d paramMatrix4d) throws IOException {
/* 785 */     for (byte b = 0; b < 4; b++) {
/* 786 */       for (byte b1 = 0; b1 < 4; b1++)
/* 787 */         paramDataOutput.writeDouble(paramMatrix4d.getElement(b, b1)); 
/*     */     } 
/*     */   }
/*     */   public Matrix4d readMatrix4d(DataInput paramDataInput) throws IOException {
/* 791 */     double[] arrayOfDouble = new double[16];
/* 792 */     for (byte b = 0; b < 16; b++) {
/* 793 */       arrayOfDouble[b] = paramDataInput.readDouble();
/*     */     }
/* 795 */     return new Matrix4d(arrayOfDouble);
/*     */   }
/*     */   
/*     */   public void writeTuple3f(DataOutput paramDataOutput, Tuple3f paramTuple3f) throws IOException {
/* 799 */     paramDataOutput.writeFloat(paramTuple3f.x);
/* 800 */     paramDataOutput.writeFloat(paramTuple3f.y);
/* 801 */     paramDataOutput.writeFloat(paramTuple3f.z);
/*     */   }
/*     */   
/*     */   public Tuple3f readTuple3f(DataInput paramDataInput, Tuple3f paramTuple3f) throws IOException {
/* 805 */     paramTuple3f.x = paramDataInput.readFloat();
/* 806 */     paramTuple3f.y = paramDataInput.readFloat();
/* 807 */     paramTuple3f.z = paramDataInput.readFloat();
/* 808 */     return paramTuple3f;
/*     */   }
/*     */   
/*     */   public void writeTuple3d(DataOutput paramDataOutput, Tuple3d paramTuple3d) throws IOException {
/* 812 */     paramDataOutput.writeDouble(paramTuple3d.x);
/* 813 */     paramDataOutput.writeDouble(paramTuple3d.y);
/* 814 */     paramDataOutput.writeDouble(paramTuple3d.z);
/*     */   }
/*     */   
/*     */   public Tuple3d readTuple3d(DataInput paramDataInput, Tuple3d paramTuple3d) throws IOException {
/* 818 */     paramTuple3d.x = paramDataInput.readDouble();
/* 819 */     paramTuple3d.y = paramDataInput.readDouble();
/* 820 */     paramTuple3d.z = paramDataInput.readDouble();
/* 821 */     return paramTuple3d;
/*     */   }
/*     */   
/*     */   public void writeTuple4d(DataOutput paramDataOutput, Tuple4d paramTuple4d) throws IOException {
/* 825 */     paramDataOutput.writeDouble(paramTuple4d.x);
/* 826 */     paramDataOutput.writeDouble(paramTuple4d.y);
/* 827 */     paramDataOutput.writeDouble(paramTuple4d.z);
/* 828 */     paramDataOutput.writeDouble(paramTuple4d.w);
/*     */   }
/*     */   
/*     */   public Tuple4d readTuple4d(DataInput paramDataInput, Tuple4d paramTuple4d) throws IOException {
/* 832 */     paramTuple4d.x = paramDataInput.readDouble();
/* 833 */     paramTuple4d.y = paramDataInput.readDouble();
/* 834 */     paramTuple4d.z = paramDataInput.readDouble();
/* 835 */     paramTuple4d.w = paramDataInput.readDouble();
/* 836 */     return paramTuple4d;
/*     */   }
/*     */   
/*     */   public void writeTuple4f(DataOutput paramDataOutput, Tuple4f paramTuple4f) throws IOException {
/* 840 */     paramDataOutput.writeFloat(paramTuple4f.x);
/* 841 */     paramDataOutput.writeFloat(paramTuple4f.y);
/* 842 */     paramDataOutput.writeFloat(paramTuple4f.z);
/* 843 */     paramDataOutput.writeFloat(paramTuple4f.w);
/*     */   }
/*     */   
/*     */   public Tuple4f readTuple4f(DataInput paramDataInput, Tuple4f paramTuple4f) throws IOException {
/* 847 */     paramTuple4f.x = paramDataInput.readFloat();
/* 848 */     paramTuple4f.y = paramDataInput.readFloat();
/* 849 */     paramTuple4f.z = paramDataInput.readFloat();
/* 850 */     paramTuple4f.w = paramDataInput.readFloat();
/* 851 */     return paramTuple4f;
/*     */   }
/*     */   
/*     */   public void writeTransform3D(DataOutput paramDataOutput, Transform3D paramTransform3D) throws IOException {
/* 855 */     Matrix4d matrix4d = new Matrix4d();
/* 856 */     paramTransform3D.get(matrix4d);
/* 857 */     writeMatrix4d(paramDataOutput, matrix4d);
/*     */   }
/*     */   
/*     */   public Transform3D readTransform3D(DataInput paramDataInput) throws IOException {
/* 861 */     Transform3D transform3D = new Transform3D();
/* 862 */     transform3D.set(readMatrix4d(paramDataInput));
/* 863 */     return transform3D;
/*     */   }
/*     */   
/*     */   public void writeBounds(DataOutput paramDataOutput, Bounds paramBounds) throws IOException {
/* 867 */     if (paramBounds == null) {
/* 868 */       paramDataOutput.writeInt(0);
/* 869 */     } else if (paramBounds instanceof BoundingBox) {
/* 870 */       paramDataOutput.writeInt(1);
/* 871 */       Point3d point3d = new Point3d();
/* 872 */       ((BoundingBox)paramBounds).getLower(point3d);
/* 873 */       writePoint3d(paramDataOutput, point3d);
/* 874 */       ((BoundingBox)paramBounds).getUpper(point3d);
/* 875 */       writePoint3d(paramDataOutput, point3d);
/* 876 */     } else if (paramBounds instanceof BoundingSphere) {
/* 877 */       paramDataOutput.writeInt(2);
/* 878 */       Point3d point3d = new Point3d();
/* 879 */       ((BoundingSphere)paramBounds).getCenter(point3d);
/* 880 */       writePoint3d(paramDataOutput, point3d);
/* 881 */       paramDataOutput.writeDouble(((BoundingSphere)paramBounds).getRadius());
/* 882 */     } else if (paramBounds instanceof BoundingPolytope) {
/* 883 */       paramDataOutput.writeInt(3);
/* 884 */       Vector4d[] arrayOfVector4d = new Vector4d[((BoundingPolytope)paramBounds).getNumPlanes()];
/* 885 */       ((BoundingPolytope)paramBounds).getPlanes(arrayOfVector4d);
/* 886 */       paramDataOutput.writeInt(arrayOfVector4d.length);
/* 887 */       for (byte b = 0; b < arrayOfVector4d.length; b++)
/* 888 */         writeVector4d(paramDataOutput, arrayOfVector4d[b]); 
/*     */     } else {
/* 890 */       throw new IOException("Unsupported bounds class " + paramBounds.getClass().getName());
/*     */     }  } public Bounds readBounds(DataInput paramDataInput) throws IOException { byte b;
/*     */     Vector4d[] arrayOfVector4d;
/*     */     BoundingSphere boundingSphere;
/*     */     BoundingBox boundingBox;
/*     */     Bounds bounds;
/* 896 */     switch (paramDataInput.readInt()) {
/*     */       case 0:
/* 898 */         return null;
/*     */       
/*     */       case 1:
/* 901 */         return new BoundingBox(readPoint3d(paramDataInput), readPoint3d(paramDataInput));
/*     */       
/*     */       case 2:
/* 904 */         return new BoundingSphere(readPoint3d(paramDataInput), paramDataInput.readDouble());
/*     */       
/*     */       case 3:
/* 907 */         arrayOfVector4d = new Vector4d[paramDataInput.readInt()];
/* 908 */         for (b = 0; b < arrayOfVector4d.length; b++)
/* 909 */           arrayOfVector4d[b] = readVector4d(paramDataInput); 
/* 910 */         return new BoundingPolytope(arrayOfVector4d);
/*     */     } 
/*     */     
/* 913 */     throw new SGIORuntimeException("Unrecognised bounds class"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract long getFilePointer();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void close();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 931 */   public boolean useSuperClassIfNoChildClass() { return this.useSuperClass; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 939 */   public int getImageCompression() { return this.imageCompression; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class J3dIOObjectInputStream
/*     */     extends ObjectInputStream
/*     */   {
/* 949 */     public J3dIOObjectInputStream(InputStream param1InputStream) throws IOException { super(param1InputStream); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 954 */     protected Class resolveClass(ObjectStreamClass param1ObjectStreamClass) throws IOException, ClassNotFoundException { return getClass().forName(param1ObjectStreamClass.getName(), true, Controller.this.classLoader); }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\retained\Controller.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */