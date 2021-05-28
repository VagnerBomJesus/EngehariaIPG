/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.BoundingLeaf;
/*     */ import javax.media.j3d.Group;
/*     */ import javax.media.j3d.Light;
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
/*     */ public abstract class LightState
/*     */   extends LeafState
/*     */ {
/*  57 */   private int boundingLeaf = 0;
/*     */   
/*     */   private int[] scope;
/*     */   
/*  61 */   public LightState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  66 */     super.writeObject(paramDataOutput);
/*     */     
/*  68 */     this.scope = new int[((Light)this.node).numScopes()];
/*  69 */     for (byte b1 = 0; b1 < ((Light)this.node).numScopes(); b1++) {
/*  70 */       this.scope[b1] = this.control.getSymbolTable().addReference(((Light)this.node).getScope(b1));
/*     */     }
/*  72 */     this.boundingLeaf = this.control.getSymbolTable().addReference(((Light)this.node).getInfluencingBoundingLeaf());
/*     */     
/*  74 */     Color3f color3f = new Color3f();
/*  75 */     ((Light)this.node).getColor(color3f);
/*  76 */     this.control.writeColor3f(paramDataOutput, color3f);
/*     */     
/*  78 */     paramDataOutput.writeBoolean(((Light)this.node).getEnable());
/*     */     
/*  80 */     paramDataOutput.writeInt(this.boundingLeaf);
/*  81 */     this.control.writeBounds(paramDataOutput, ((Light)this.node).getInfluencingBounds());
/*     */     
/*  83 */     paramDataOutput.writeInt(this.scope.length);
/*  84 */     for (byte b2 = 0; b2 < this.scope.length; b2++) {
/*  85 */       paramDataOutput.writeInt(this.scope[b2]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  90 */     super.readObject(paramDataInput);
/*  91 */     ((Light)this.node).setColor(this.control.readColor3f(paramDataInput));
/*  92 */     ((Light)this.node).setEnable(paramDataInput.readBoolean());
/*     */     
/*  94 */     this.boundingLeaf = paramDataInput.readInt();
/*     */     
/*  96 */     ((Light)this.node).setInfluencingBounds(this.control.readBounds(paramDataInput));
/*     */     
/*  98 */     this.scope = new int[paramDataInput.readInt()];
/*  99 */     for (byte b = 0; b < this.scope.length; b++) {
/* 100 */       this.scope[b] = paramDataInput.readInt();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/* 106 */     ((Light)this.node).setInfluencingBoundingLeaf((BoundingLeaf)this.control.getSymbolTable().getJ3dNode(this.boundingLeaf));
/* 107 */     for (byte b = 0; b < this.scope.length; b++) {
/* 108 */       ((Light)this.node).addScope((Group)this.control.getSymbolTable().getJ3dNode(this.scope[b]));
/*     */     }
/* 110 */     super.buildGraph();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\LightState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */