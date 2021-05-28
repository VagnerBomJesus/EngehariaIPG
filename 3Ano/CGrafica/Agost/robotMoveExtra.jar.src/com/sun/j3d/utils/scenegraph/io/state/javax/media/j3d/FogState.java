/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.BoundingLeaf;
/*     */ import javax.media.j3d.Fog;
/*     */ import javax.media.j3d.Group;
/*     */ import javax.vecmath.Color3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class FogState
/*     */   extends SceneGraphObjectState
/*     */ {
/*     */   protected int[] scopes;
/*     */   protected int boundingLeaf;
/*     */   
/*  64 */   public FogState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  70 */     super.writeObject(paramDataOutput);
/*     */     
/*  72 */     this.control.writeBounds(paramDataOutput, ((Fog)this.node).getInfluencingBounds());
/*     */     
/*  74 */     paramDataOutput.writeInt(((Fog)this.node).numScopes());
/*  75 */     for (byte b = 0; b < ((Fog)this.node).numScopes(); b++) {
/*  76 */       paramDataOutput.writeInt(this.control.getSymbolTable().addReference(((Fog)this.node).getScope(b)));
/*     */     }
/*  78 */     paramDataOutput.writeInt(this.control.getSymbolTable().addReference(((Fog)this.node).getInfluencingBoundingLeaf()));
/*     */     
/*  80 */     Color3f color3f = new Color3f();
/*  81 */     ((Fog)this.node).getColor(color3f);
/*     */     
/*  83 */     this.control.writeColor3f(paramDataOutput, color3f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  88 */     super.readObject(paramDataInput);
/*     */     
/*  90 */     ((Fog)this.node).setInfluencingBounds(this.control.readBounds(paramDataInput));
/*  91 */     this.scopes = new int[paramDataInput.readInt()];
/*  92 */     for (byte b = 0; b < this.scopes.length; b++) {
/*  93 */       this.scopes[b] = paramDataInput.readInt();
/*     */     }
/*  95 */     this.boundingLeaf = paramDataInput.readInt();
/*  96 */     ((Fog)this.node).setColor(this.control.readColor3f(paramDataInput));
/*     */   }
/*     */   
/*     */   public void buildGraph() {
/* 100 */     for (byte b = 0; b < this.scopes.length; b++) {
/* 101 */       ((Fog)this.node).addScope((Group)this.control.getSymbolTable().getJ3dNode(this.scopes[b]));
/*     */     }
/* 103 */     ((Fog)this.node).setInfluencingBoundingLeaf((BoundingLeaf)this.control.getSymbolTable().getJ3dNode(this.boundingLeaf));
/* 104 */     super.buildGraph();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\FogState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */