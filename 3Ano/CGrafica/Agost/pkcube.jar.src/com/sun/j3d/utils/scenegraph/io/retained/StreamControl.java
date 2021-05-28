/*     */ package com.sun.j3d.utils.scenegraph.io.retained;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.SceneGraphObjectState;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.DanglingReferenceException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StreamControl
/*     */   extends Controller
/*     */ {
/*     */   protected String FILE_IDENT;
/*     */   private DataInputStream inputStream;
/*     */   private DataOutputStream outputStream;
/*     */   
/*     */   public StreamControl(DataOutputStream paramDataOutputStream) {
/*  70 */     this.FILE_IDENT = new String("j3dsf");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     this.outputStream = paramDataOutputStream;
/*  78 */     this.symbolTable = new SymbolTable(this);
/*     */   }
/*     */   
/*     */   public StreamControl(DataInputStream paramDataInputStream) {
/*     */     this.FILE_IDENT = new String("j3dsf");
/*  83 */     this.inputStream = paramDataInputStream;
/*  84 */     this.symbolTable = new SymbolTable(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeStreamHeader() throws IOException {
/*  91 */     this.outputStream.writeUTF(this.FILE_IDENT);
/*  92 */     this.outputStream.writeInt(this.outputFileVersion);
/*     */   }
/*     */   
/*     */   public void readStreamHeader() throws IOException {
/*  96 */     String str = this.inputStream.readUTF();
/*  97 */     if (str.equals("demo_j3s")) {
/*  98 */       throw new IOException("Use Java 3D Fly Through I/O instead of Java 3D Scenegraph I/O");
/*     */     }
/* 100 */     if (!str.equals("j3dsf")) {
/* 101 */       throw new IOException("This is a File - use SceneGraphFileReader instead");
/*     */     }
/*     */     
/* 104 */     this.currentFileVersion = this.inputStream.readInt();
/*     */     
/* 106 */     if (this.currentFileVersion > this.outputFileVersion) {
/* 107 */       throw new IOException("Unsupported file version. This file was written using a new version of the SceneGraph IO API, please update your installtion to the latest version");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public void addNamedObjects(HashMap paramHashMap) { this.symbolTable.addNamedObjects(paramHashMap); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeBranchGraph(BranchGroup paramBranchGroup, Serializable paramSerializable) throws IOException {
/*     */     try {
/* 127 */       SymbolTableData symbolTableData = this.symbolTable.getSymbol(paramBranchGroup);
/*     */       
/* 129 */       if (symbolTableData == null) {
/* 130 */         symbolTableData = this.symbolTable.createSymbol(paramBranchGroup);
/* 131 */         symbolTableData.branchGraphID = -1;
/*     */       } 
/*     */       
/* 134 */       this.symbolTable.setBranchGraphRoot(symbolTableData, 0L);
/* 135 */       this.symbolTable.startUnsavedNodeComponentFrame();
/* 136 */       SceneGraphObjectState sceneGraphObjectState = createState(paramBranchGroup, symbolTableData);
/* 137 */       writeObject(this.outputStream, sceneGraphObjectState);
/* 138 */       writeNodeComponents(this.outputStream);
/* 139 */       this.symbolTable.endUnsavedNodeComponentFrame();
/*     */       
/* 141 */       if (this.symbolTable.branchGraphHasDependencies(symbolTableData.branchGraphID)) {
/* 142 */         throw new DanglingReferenceException();
/*     */       }
/* 144 */       this.symbolTable.clearUnshared();
/* 145 */       this.symbolTable.writeTable(this.outputStream);
/* 146 */     } catch (SGIORuntimeException sGIORuntimeException) {
/* 147 */       throw new IOException(sGIORuntimeException.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public BranchGroup readBranchGraph(HashMap paramHashMap) throws IOException {
/*     */     try {
/* 153 */       SceneGraphObjectState sceneGraphObjectState = readObject(this.inputStream);
/* 154 */       readNodeComponents(this.inputStream);
/* 155 */       this.symbolTable.readTable(this.inputStream, true);
/*     */       
/* 157 */       this.symbolTable.setBranchGraphRoot(sceneGraphObjectState.getSymbol(), 0L);
/*     */       
/* 159 */       sceneGraphObjectState.buildGraph();
/*     */       
/* 161 */       if (paramHashMap != null) {
/* 162 */         this.symbolTable.getNamedObjectMap(paramHashMap);
/*     */       }
/* 164 */       return (BranchGroup)sceneGraphObjectState.getNode();
/* 165 */     } catch (SGIORuntimeException sGIORuntimeException) {
/* 166 */       throw new IOException(sGIORuntimeException.getMessage());
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
/*     */   protected void readBranchGraphs(int[] paramArrayOfInt) throws IOException {
/* 180 */     for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 181 */       readBranchGraph(null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public void loadNodeComponent(SymbolTableData paramSymbolTableData) throws IOException { throw new IOException("Unable to load individual NodeComponents from Stream"); }
/*     */ 
/*     */ 
/*     */   
/* 193 */   public void close() throws IOException { reset(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public long getFilePointer() { return 0L; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\retained\StreamControl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */