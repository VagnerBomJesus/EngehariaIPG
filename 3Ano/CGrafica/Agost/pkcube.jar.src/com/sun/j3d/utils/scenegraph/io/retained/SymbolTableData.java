/*     */ package com.sun.j3d.utils.scenegraph.io.retained;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.SceneGraphObjectState;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SymbolTableData
/*     */ {
/*     */   public int nodeID;
/*     */   public SceneGraphObjectState nodeState;
/*     */   public SceneGraphObject j3dNode;
/*     */   public int referenceCount;
/*     */   public long filePosition;
/*     */   public boolean isNodeComponent;
/*     */   public int branchGraphID;
/*     */   public long branchGraphFilePointer;
/*     */   public boolean graphBuilt;
/*     */   
/*     */   public SymbolTableData(int paramInt1, SceneGraphObject paramSceneGraphObject, SceneGraphObjectState paramSceneGraphObjectState, int paramInt2) {
/*  66 */     this.graphBuilt = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     this.nodeID = paramInt1;
/*  74 */     this.j3dNode = paramSceneGraphObject;
/*  75 */     this.nodeState = paramSceneGraphObjectState;
/*  76 */     this.branchGraphID = paramInt2;
/*  77 */     this.referenceCount = 1;
/*  78 */     this.isNodeComponent = false;
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  82 */     paramDataOutput.writeInt(this.nodeID);
/*  83 */     paramDataOutput.writeInt(this.referenceCount);
/*  84 */     paramDataOutput.writeLong(this.filePosition);
/*  85 */     paramDataOutput.writeBoolean(this.isNodeComponent);
/*  86 */     paramDataOutput.writeInt(this.branchGraphID);
/*  87 */     paramDataOutput.writeLong(this.branchGraphFilePointer);
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  91 */     this.nodeID = paramDataInput.readInt();
/*  92 */     this.referenceCount = paramDataInput.readInt();
/*  93 */     this.filePosition = paramDataInput.readLong();
/*  94 */     this.isNodeComponent = paramDataInput.readBoolean();
/*  95 */     this.branchGraphID = paramDataInput.readInt();
/*  96 */     this.branchGraphFilePointer = paramDataInput.readLong();
/*     */   }
/*     */ 
/*     */   
/* 100 */   public final int getNodeID() { return this.nodeID; }
/*     */ 
/*     */ 
/*     */   
/* 104 */   public final SceneGraphObjectState getNodeState() { return this.nodeState; }
/*     */ 
/*     */ 
/*     */   
/* 108 */   public final void setNodeState(SceneGraphObjectState paramSceneGraphObjectState) { this.nodeState = paramSceneGraphObjectState; }
/*     */ 
/*     */ 
/*     */   
/* 112 */   public final SceneGraphObject getJ3dNode() { return this.j3dNode; }
/*     */ 
/*     */ 
/*     */   
/* 116 */   public final long getFilePosition() { return this.filePosition; }
/*     */ 
/*     */ 
/*     */   
/* 120 */   public final int getReferenceCount() { return this.referenceCount; }
/*     */ 
/*     */ 
/*     */   
/* 124 */   public final void incrementReferenceCount() { this.referenceCount++; }
/*     */ 
/*     */ 
/*     */   
/* 128 */   public final boolean isNodeComponent() { return this.isNodeComponent; }
/*     */ 
/*     */ 
/*     */   
/* 132 */   public String toString() { return new String(this.nodeID + " " + this.filePosition + "  " + this.j3dNode); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\retained\SymbolTableData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */