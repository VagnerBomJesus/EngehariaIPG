/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import javax.media.j3d.ImageComponent;
/*     */ import javax.media.j3d.ImageComponent2D;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.TextureCubeMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextureCubeMapState
/*     */   extends TextureState
/*     */ {
/*  59 */   private int[][] ic = new int[6][];
/*     */   
/*     */   public TextureCubeMapState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  62 */     super(paramSymbolTableData, paramController);
/*     */     
/*  64 */     if (this.node != null) {
/*     */       
/*  66 */       TextureCubeMap textureCubeMap = (TextureCubeMap)this.node;
/*     */       
/*  68 */       for (byte b = 0; b < 6; b++) {
/*  69 */         ImageComponent[] arrayOfImageComponent = textureCubeMap.getImages(b);
/*  70 */         this.ic[b] = new int[arrayOfImageComponent.length];
/*  71 */         for (byte b1 = 0; b1 < arrayOfImageComponent.length; b1++) {
/*  72 */           this.ic[b][b1] = paramController.getSymbolTable().addReference(arrayOfImageComponent[b1]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSubReference() {
/*  84 */     for (byte b = 0; b < 6; b++) {
/*  85 */       for (byte b1 = 0; b1 < this.ic[b].length; b1++) {
/*  86 */         this.control.getSymbolTable().incNodeComponentRefCount(this.ic[b][b1]);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void buildGraph() {
/*  92 */     TextureCubeMap textureCubeMap = (TextureCubeMap)this.node;
/*     */     
/*  94 */     for (byte b = 0; b < 6; b++) {
/*  95 */       for (byte b1 = 0; b1 < this.ic[b].length; b1++) {
/*  96 */         textureCubeMap.setImage(b1, b, (ImageComponent2D)this.control.getSymbolTable().getJ3dNode(this.ic[b][b1]));
/*     */       }
/*     */     } 
/*     */     
/* 100 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/* 104 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { int.class, int.class, int.class, int.class }, new Object[] { new Integer(this.mipMapMode), new Integer(this.format), new Integer(this.width), new Integer(this.boundaryWidth) }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   protected SceneGraphObject createNode() { return new TextureCubeMap(this.mipMapMode, this.format, this.width, this.boundaryWidth); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\TextureCubeMapState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */