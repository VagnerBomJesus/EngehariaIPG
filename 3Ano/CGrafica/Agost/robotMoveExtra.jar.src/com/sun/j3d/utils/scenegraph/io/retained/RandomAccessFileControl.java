/*     */ package com.sun.j3d.utils.scenegraph.io.retained;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.UnsupportedUniverseException;
/*     */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.BranchGroupState;
/*     */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.SceneGraphObjectState;
/*     */ import com.sun.j3d.utils.universe.ConfiguredUniverse;
/*     */ import com.sun.j3d.utils.universe.SimpleUniverse;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.io.Serializable;
/*     */ import java.util.ListIterator;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.media.j3d.CapabilityNotSetException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomAccessFileControl
/*     */   extends Controller
/*     */ {
/*  65 */   protected String FILE_IDENT = new String("j3dff");
/*     */   
/*     */   private long user_data;
/*     */   
/*     */   private long universe_config;
/*     */   
/*     */   private long symbol_table;
/*     */   
/*     */   private RandomAccessFile raf;
/*  74 */   private int branchGraphCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean writeMode = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object userData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createFile(File paramFile, SimpleUniverse paramSimpleUniverse, boolean paramBoolean, String paramString, Serializable paramSerializable) throws IOException, UnsupportedUniverseException, CapabilityNotSetException {
/*  96 */     this.raf = new RandomAccessFile(paramFile, "rw");
/*  97 */     this.writeMode = true;
/*     */     
/*  99 */     this.raf.seek(0L);
/* 100 */     this.raf.writeUTF(this.FILE_IDENT);
/*     */     
/* 102 */     this.raf.seek(20L);
/* 103 */     this.raf.writeInt(this.outputFileVersion);
/*     */     
/* 105 */     this.raf.seek(68L);
/* 106 */     this.raf.writeInt(0);
/*     */     
/* 108 */     this.raf.seek(72L);
/*     */     
/* 110 */     if (paramString == null)
/* 111 */       paramString = ""; 
/* 112 */     this.raf.writeUTF(paramString);
/*     */     
/*     */     try {
/* 115 */       writeSerializedData(this.raf, paramSerializable);
/*     */       
/* 117 */       this.universe_config = this.raf.getFilePointer();
/* 118 */       writeUniverse(this.raf, paramSimpleUniverse, paramBoolean);
/* 119 */     } catch (SGIORuntimeException sGIORuntimeException) {
/* 120 */       throw new IOException(sGIORuntimeException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void openFile(File paramFile) throws IOException {
/* 128 */     this.raf = new RandomAccessFile(paramFile, "r");
/* 129 */     this.writeMode = false;
/*     */     
/* 131 */     this.raf.seek(0L);
/* 132 */     String str1 = this.raf.readUTF();
/*     */     
/* 134 */     if (str1.equals("demo_j3f")) {
/* 135 */       throw new IOException("Use Java 3D Fly Through I/O instead of Java 3D Scenegraph I/O");
/*     */     }
/*     */     
/* 138 */     if (!str1.equals("j3dff")) {
/* 139 */       throw new IOException("This is a Stream - use SceneGraphStreamReader instead");
/*     */     }
/*     */     
/* 142 */     this.raf.seek(20L);
/* 143 */     this.currentFileVersion = this.raf.readInt();
/*     */     
/* 145 */     if (this.currentFileVersion > this.outputFileVersion) {
/* 146 */       throw new IOException("Unsupported file version. This file was written using a new version of the SceneGraph IO API, please update your installtion to the latest version");
/*     */     }
/*     */ 
/*     */     
/* 150 */     String str2 = readFileDescription();
/*     */     
/* 152 */     this.raf.seek(68L);
/* 153 */     this.branchGraphCount = this.raf.readInt();
/*     */ 
/*     */     
/* 156 */     this.raf.seek(60L);
/* 157 */     this.universe_config = this.raf.readLong();
/*     */     
/* 159 */     this.raf.seek(30L);
/* 160 */     this.symbol_table = this.raf.readLong();
/*     */ 
/*     */ 
/*     */     
/* 164 */     this.raf.seek(this.symbol_table);
/* 165 */     this.symbolTable.readTable(this.raf, false);
/* 166 */     this.raf.seek(this.user_data);
/*     */     
/* 168 */     this.userData = readSerializedData(this.raf);
/*     */   }
/*     */ 
/*     */   
/*     */   public ConfiguredUniverse readUniverse(boolean paramBoolean, Canvas3D paramCanvas3D) throws IOException {
/* 173 */     this.raf.seek(this.universe_config);
/* 174 */     return readUniverse(this.raf, paramBoolean, paramCanvas3D);
/*     */   }
/*     */ 
/*     */   
/* 178 */   public Object getUserData() { return this.userData; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readBranchGraphs(int[] paramArrayOfInt) throws IOException {
/* 190 */     for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 191 */       readBranchGraph(paramArrayOfInt[b]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public int getBranchGraphCount() { return this.symbolTable.getBranchGraphCount(); }
/*     */ 
/*     */   
/*     */   public void writeBranchGraph(BranchGroup paramBranchGroup, Serializable paramSerializable) throws IOException {
/* 203 */     long l = this.raf.getFilePointer();
/* 204 */     this.raf.writeInt(0);
/*     */     try {
/* 206 */       writeSerializedData(this.raf, paramSerializable);
/*     */ 
/*     */ 
/*     */       
/* 210 */       SymbolTableData symbolTableData = this.symbolTable.getSymbol(paramBranchGroup);
/*     */       
/* 212 */       if (symbolTableData == null) {
/* 213 */         symbolTableData = this.symbolTable.createSymbol(paramBranchGroup);
/* 214 */         symbolTableData.branchGraphID = -1;
/*     */       } 
/*     */       
/* 217 */       this.symbolTable.setBranchGraphRoot(symbolTableData, l);
/*     */       
/* 219 */       this.symbolTable.startUnsavedNodeComponentFrame();
/* 220 */       SceneGraphObjectState sceneGraphObjectState = createState(paramBranchGroup, symbolTableData);
/*     */       
/*     */       try {
/* 223 */         writeObject(this.raf, sceneGraphObjectState);
/* 224 */         writeNodeComponents(this.raf);
/* 225 */       } catch (IOException iOException) {
/* 226 */         iOException.printStackTrace();
/*     */       } 
/* 228 */       this.symbolTable.endUnsavedNodeComponentFrame();
/* 229 */     } catch (SGIORuntimeException sGIORuntimeException) {
/* 230 */       throw new IOException(sGIORuntimeException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BranchGroup[] readBranchGraph(int paramInt) throws IOException {
/*     */     try {
/* 238 */       int[] arrayOfInt = this.symbolTable.getBranchGraphDependencies(paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 245 */       BranchGroupState[] arrayOfBranchGroupState = new BranchGroupState[arrayOfInt.length + 1];
/* 246 */       BranchGroup[] arrayOfBranchGroup = new BranchGroup[arrayOfBranchGroupState.length];
/* 247 */       arrayOfBranchGroupState[0] = readSingleBranchGraph(paramInt);
/*     */       byte b;
/* 249 */       for (b = 0; b < arrayOfInt.length; b++) {
/* 250 */         arrayOfBranchGroupState[b + true] = readSingleBranchGraph(arrayOfInt[b]);
/*     */       }
/*     */       
/* 253 */       for (b = 0; b < arrayOfBranchGroupState.length; b++) {
/* 254 */         if (!(arrayOfBranchGroupState[b].getSymbol()).graphBuilt) {
/* 255 */           arrayOfBranchGroupState[b].buildGraph();
/* 256 */           (arrayOfBranchGroupState[b].getSymbol()).graphBuilt = true;
/*     */         } 
/* 258 */         arrayOfBranchGroup[b] = (BranchGroup)arrayOfBranchGroupState[b].getNode();
/*     */       } 
/*     */       
/* 261 */       this.symbolTable.clearUnshared();
/*     */       
/* 263 */       return arrayOfBranchGroup;
/* 264 */     } catch (SGIORuntimeException sGIORuntimeException) {
/* 265 */       throw new IOException(sGIORuntimeException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BranchGroup[] readAllBranchGraphs() throws IOException {
/* 273 */     int i = getBranchGraphCount();
/* 274 */     BranchGroupState[] arrayOfBranchGroupState = new BranchGroupState[i];
/* 275 */     BranchGroup[] arrayOfBranchGroup = new BranchGroup[i];
/*     */     try {
/*     */       byte b;
/* 278 */       for (b = 0; b < i; b++) {
/* 279 */         arrayOfBranchGroupState[b] = readSingleBranchGraph(b);
/*     */       }
/*     */       
/* 282 */       for (b = 0; b < arrayOfBranchGroupState.length; b++) {
/* 283 */         if (!(arrayOfBranchGroupState[b].getSymbol()).graphBuilt) {
/* 284 */           arrayOfBranchGroupState[b].buildGraph();
/* 285 */           (arrayOfBranchGroupState[b].getSymbol()).graphBuilt = true;
/*     */         } 
/* 287 */         arrayOfBranchGroup[b] = (BranchGroup)arrayOfBranchGroupState[b].getNode();
/*     */       } 
/*     */       
/* 290 */       this.symbolTable.clearUnshared();
/* 291 */     } catch (SGIORuntimeException sGIORuntimeException) {
/* 292 */       throw new IOException(sGIORuntimeException.getMessage());
/*     */     } 
/*     */     
/* 295 */     return arrayOfBranchGroup;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BranchGroupState readSingleBranchGraph(int paramInt) throws IOException {
/* 302 */     SymbolTableData symbolTableData = this.symbolTable.getBranchGraphRoot(paramInt);
/*     */     
/* 304 */     if (symbolTableData.nodeState != null) {
/* 305 */       return (BranchGroupState)symbolTableData.nodeState;
/*     */     }
/*     */     
/* 308 */     this.raf.seek(this.symbolTable.getBranchGraphFilePosition(paramInt));
/*     */     
/* 310 */     return readNextBranchGraph();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BranchGroupState readNextBranchGraph() throws IOException {
/* 318 */     int i = this.raf.readInt();
/* 319 */     skipUserData(this.raf);
/*     */     
/* 321 */     BranchGroupState branchGroupState = null;
/*     */     try {
/* 323 */       branchGroupState = (BranchGroupState)readObject(this.raf);
/*     */       
/* 325 */       readNodeComponents(this.raf);
/*     */     }
/* 327 */     catch (IOException iOException) {
/* 328 */       iOException.printStackTrace();
/*     */     } 
/*     */     
/* 331 */     return branchGroupState;
/*     */   }
/*     */   
/*     */   public Object readBranchGraphUserData(int paramInt) throws IOException {
/*     */     try {
/* 336 */       this.raf.seek(this.symbolTable.getBranchGraphFilePosition(paramInt));
/*     */       
/* 338 */       int i = this.raf.readInt();
/* 339 */       return readSerializedData(this.raf);
/* 340 */     } catch (SGIORuntimeException sGIORuntimeException) {
/* 341 */       throw new IOException(sGIORuntimeException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeNodeComponents(DataOutput paramDataOutput) throws IOException {
/* 352 */     long l = 0L;
/*     */     
/* 354 */     ListIterator listIterator = this.symbolTable.getUnsavedNodeComponents();
/* 355 */     paramDataOutput.writeInt(this.symbolTable.getUnsavedNodeComponentsSize());
/* 356 */     while (listIterator.hasNext()) {
/* 357 */       SymbolTableData symbolTableData = (SymbolTableData)listIterator.next();
/*     */       
/* 359 */       paramDataOutput.writeInt(symbolTableData.nodeID);
/* 360 */       l = this.raf.getFilePointer();
/* 361 */       paramDataOutput.writeLong(0L);
/*     */       
/* 363 */       writeObject(paramDataOutput, symbolTableData.getNodeState());
/*     */       
/* 365 */       long l1 = this.raf.getFilePointer();
/* 366 */       this.raf.seek(l);
/* 367 */       paramDataOutput.writeLong(l1);
/* 368 */       this.raf.seek(l1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readNodeComponents(DataInput paramDataInput) throws IOException {
/* 376 */     int i = paramDataInput.readInt();
/*     */     
/* 378 */     for (byte b = 0; b < i; b++) {
/* 379 */       int j = paramDataInput.readInt();
/* 380 */       long l = paramDataInput.readLong();
/* 381 */       if (this.symbolTable.isLoaded(j)) {
/*     */         
/* 383 */         this.raf.seek(l);
/*     */       } else {
/*     */         
/* 386 */         SceneGraphObjectState sceneGraphObjectState = readObject(paramDataInput);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput, SceneGraphObjectState paramSceneGraphObjectState) throws IOException {
/* 394 */     this.symbolTable.setFilePosition(this.raf.getFilePointer(), paramSceneGraphObjectState);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 404 */       super.writeObject(paramDataOutput, paramSceneGraphObjectState);
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
/* 415 */     catch (SGIORuntimeException sGIORuntimeException) {
/* 416 */       throw new IOException(sGIORuntimeException.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public String readFileDescription() throws IOException {
/* 421 */     this.raf.seek(72L);
/* 422 */     String str = this.raf.readUTF();
/*     */     
/* 424 */     this.user_data = this.raf.getFilePointer();
/* 425 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadNodeComponent(SymbolTableData paramSymbolTableData) throws IOException {
/*     */     try {
/* 434 */       this.raf.seek(paramSymbolTableData.filePosition);
/* 435 */       readObject(this.raf);
/* 436 */     } catch (SGIORuntimeException sGIORuntimeException) {
/* 437 */       throw new IOException(sGIORuntimeException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadSharedGroup(SymbolTableData paramSymbolTableData) throws IOException {
/*     */     try {
/* 446 */       this.raf.seek(paramSymbolTableData.filePosition);
/* 447 */       readObject(this.raf);
/* 448 */     } catch (SGIORuntimeException sGIORuntimeException) {
/* 449 */       throw new IOException(sGIORuntimeException.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/*     */     try {
/* 455 */       if (this.writeMode) {
/* 456 */         writeClose();
/*     */       }
/*     */       
/* 459 */       this.raf.close();
/* 460 */       reset();
/* 461 */     } catch (SGIORuntimeException sGIORuntimeException) {
/* 462 */       throw new IOException(sGIORuntimeException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeClose() {
/* 471 */     this.symbol_table = this.raf.getFilePointer();
/* 472 */     getSymbolTable().writeTable(this.raf);
/*     */ 
/*     */ 
/*     */     
/* 476 */     this.raf.seek(60L);
/* 477 */     this.raf.writeLong(this.universe_config);
/* 478 */     this.raf.seek(30L);
/* 479 */     this.raf.writeLong(this.symbol_table);
/* 480 */     this.raf.seek(68L);
/* 481 */     this.raf.writeInt(this.symbolTable.getBranchGraphCount());
/*     */   }
/*     */   
/*     */   public long getFilePointer() {
/*     */     try {
/* 486 */       return this.raf.getFilePointer();
/* 487 */     } catch (IOException iOException) {
/* 488 */       return 0L;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBranchGraphPosition(BranchGroup paramBranchGroup) {
/* 496 */     SymbolTableData symbolTableData = this.symbolTable.getSymbol(paramBranchGroup);
/* 497 */     if (symbolTableData != null) return symbolTableData.branchGraphID; 
/* 498 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\retained\RandomAccessFileControl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */