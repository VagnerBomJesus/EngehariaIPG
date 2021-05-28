/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.BoundingLeaf;
/*     */ import javax.media.j3d.Group;
/*     */ import javax.media.j3d.ModelClip;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.vecmath.Vector4d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelClipState
/*     */   extends LeafState
/*     */ {
/*     */   private int[] scopes;
/*     */   private int influencingBoundingLeaf;
/*     */   
/*     */   public ModelClipState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  64 */     super(paramSymbolTableData, paramController);
/*     */     
/*  66 */     if (this.node != null) {
/*  67 */       this.scopes = new int[((ModelClip)this.node).numScopes()];
/*  68 */       for (byte b = 0; b < this.scopes.length; b++) {
/*  69 */         this.scopes[b] = paramController.getSymbolTable().addReference(((ModelClip)this.node).getScope(b));
/*     */       }
/*  71 */       this.influencingBoundingLeaf = paramController.getSymbolTable().addReference(((ModelClip)this.node).getInfluencingBoundingLeaf());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  76 */     super.writeObject(paramDataOutput);
/*     */     
/*  78 */     boolean[] arrayOfBoolean = new boolean[6];
/*  79 */     ((ModelClip)this.node).getEnables(arrayOfBoolean);
/*     */     
/*  81 */     paramDataOutput.writeInt(this.scopes.length);
/*  82 */     for (byte b1 = 0; b1 < this.scopes.length; b1++) {
/*  83 */       paramDataOutput.writeInt(this.scopes[b1]);
/*     */     }
/*  85 */     paramDataOutput.writeInt(this.influencingBoundingLeaf);
/*  86 */     this.control.writeBounds(paramDataOutput, ((ModelClip)this.node).getInfluencingBounds());
/*     */     
/*  88 */     Vector4d[] arrayOfVector4d = new Vector4d[6];
/*  89 */     ((ModelClip)this.node).getPlanes(arrayOfVector4d);
/*     */     
/*  91 */     for (byte b2 = 0; b2 < 6; b2++) {
/*  92 */       paramDataOutput.writeBoolean(arrayOfBoolean[b2]);
/*  93 */       this.control.writeVector4d(paramDataOutput, arrayOfVector4d[b2]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  98 */     super.readObject(paramDataInput);
/*     */     
/* 100 */     this.scopes = new int[paramDataInput.readInt()];
/* 101 */     for (byte b1 = 0; b1 < this.scopes.length; b1++) {
/* 102 */       this.scopes[b1] = paramDataInput.readInt();
/*     */     }
/* 104 */     this.influencingBoundingLeaf = paramDataInput.readInt();
/*     */     
/* 106 */     ((ModelClip)this.node).setInfluencingBounds(this.control.readBounds(paramDataInput));
/*     */     
/* 108 */     boolean[] arrayOfBoolean = new boolean[6];
/* 109 */     Vector4d[] arrayOfVector4d = new Vector4d[6];
/* 110 */     for (byte b2 = 0; b2 < 6; b2++) {
/* 111 */       arrayOfBoolean[b2] = paramDataInput.readBoolean();
/* 112 */       arrayOfVector4d[b2] = this.control.readVector4d(paramDataInput);
/*     */     } 
/*     */     
/* 115 */     ((ModelClip)this.node).setEnables(arrayOfBoolean);
/* 116 */     ((ModelClip)this.node).setPlanes(arrayOfVector4d);
/*     */   }
/*     */   
/*     */   public void buildGraph() {
/* 120 */     for (byte b = 0; b < this.scopes.length; b++) {
/* 121 */       ((ModelClip)this.node).addScope((Group)this.control.getSymbolTable().getJ3dNode(this.scopes[b]));
/*     */     }
/* 123 */     ((ModelClip)this.node).setInfluencingBoundingLeaf((BoundingLeaf)this.control.getSymbolTable().getJ3dNode(this.influencingBoundingLeaf));
/* 124 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/* 128 */   protected SceneGraphObject createNode() { return new ModelClip(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\ModelClipState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */