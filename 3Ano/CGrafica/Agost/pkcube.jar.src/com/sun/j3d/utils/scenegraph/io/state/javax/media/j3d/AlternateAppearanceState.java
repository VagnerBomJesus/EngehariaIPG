/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.AlternateAppearance;
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.BoundingLeaf;
/*     */ import javax.media.j3d.Group;
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
/*     */ public class AlternateAppearanceState
/*     */   extends LeafState
/*     */ {
/*     */   private int[] scopes;
/*     */   private int appearance;
/*     */   private int influencingBoundingLeaf;
/*     */   
/*     */   public AlternateAppearanceState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  65 */     super(paramSymbolTableData, paramController);
/*     */     
/*  67 */     if (this.node != null) {
/*  68 */       this.scopes = new int[((AlternateAppearance)this.node).numScopes()];
/*  69 */       for (byte b = 0; b < this.scopes.length; b++) {
/*  70 */         this.scopes[b] = paramController.getSymbolTable().addReference(((AlternateAppearance)this.node).getScope(b));
/*     */       }
/*  72 */       this.appearance = paramController.getSymbolTable().addReference(((AlternateAppearance)this.node).getAppearance());
/*  73 */       this.influencingBoundingLeaf = paramController.getSymbolTable().addReference(((AlternateAppearance)this.node).getInfluencingBoundingLeaf());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  78 */     super.writeObject(paramDataOutput);
/*     */     
/*  80 */     paramDataOutput.writeInt(this.scopes.length);
/*  81 */     for (byte b = 0; b < this.scopes.length; b++) {
/*  82 */       paramDataOutput.writeInt(this.scopes[b]);
/*     */     }
/*  84 */     paramDataOutput.writeInt(this.appearance);
/*  85 */     paramDataOutput.writeInt(this.influencingBoundingLeaf);
/*  86 */     this.control.writeBounds(paramDataOutput, ((AlternateAppearance)this.node).getInfluencingBounds());
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  90 */     super.readObject(paramDataInput);
/*     */     
/*  92 */     this.scopes = new int[paramDataInput.readInt()];
/*  93 */     for (byte b = 0; b < this.scopes.length; b++) {
/*  94 */       this.scopes[b] = paramDataInput.readInt();
/*     */     }
/*  96 */     this.appearance = paramDataInput.readInt();
/*  97 */     this.influencingBoundingLeaf = paramDataInput.readInt();
/*     */     
/*  99 */     ((AlternateAppearance)this.node).setInfluencingBounds(this.control.readBounds(paramDataInput));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void addSubReference() { this.control.getSymbolTable().incNodeComponentRefCount(this.appearance); }
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/* 113 */     for (byte b = 0; b < this.scopes.length; b++) {
/* 114 */       ((AlternateAppearance)this.node).addScope((Group)this.control.getSymbolTable().getJ3dNode(this.scopes[b]));
/*     */     }
/* 116 */     ((AlternateAppearance)this.node).setAppearance((Appearance)this.control.getSymbolTable().getJ3dNode(this.appearance));
/* 117 */     ((AlternateAppearance)this.node).setInfluencingBoundingLeaf((BoundingLeaf)this.control.getSymbolTable().getJ3dNode(this.influencingBoundingLeaf));
/* 118 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 123 */   protected SceneGraphObject createNode() { return new AlternateAppearance(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\AlternateAppearanceState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */