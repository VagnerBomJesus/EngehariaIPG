/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.ImageComponent3D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageComponent3DState
/*     */   extends ImageComponentState
/*     */ {
/*     */   private BufferedImage[] bufferedImages;
/*     */   
/*  70 */   public ImageComponent3DState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  75 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  77 */     ImageComponent3D imageComponent3D = (ImageComponent3D)this.node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     if (imageComponent3D.isByReference())
/*  84 */     { ImageComponent3D imageComponent3D1 = new ImageComponent3D(imageComponent3D.getFormat(), imageComponent3D.getRenderedImage(), false, imageComponent3D.isYUp());
/*     */       
/*  86 */       this.bufferedImages = imageComponent3D1.getImage(); }
/*  87 */     else { this.bufferedImages = imageComponent3D.getImage(); }
/*     */     
/*  89 */     paramDataOutput.writeInt(this.bufferedImages.length);
/*     */     
/*  91 */     for (byte b = 0; b < this.bufferedImages.length; b++) {
/*  92 */       writeBufferedImage(paramDataOutput, this.bufferedImages[b]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  99 */     super.readConstructorParams(paramDataInput);
/*     */     
/* 101 */     this.bufferedImages = new BufferedImage[paramDataInput.readInt()];
/* 102 */     for (byte b = 0; b < this.bufferedImages.length; b++) {
/* 103 */       this.bufferedImages[b] = readBufferedImage(paramDataInput);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   protected SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { int.class, this.bufferedImages.getClass(), boolean.class, boolean.class }, new Object[] { new Integer(this.format), this.bufferedImages, new Boolean(this.byReference), new Boolean(this.yUp) }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   protected SceneGraphObject createNode() { return new ImageComponent3D(this.format, this.bufferedImages, this.byReference, this.yUp); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\ImageComponent3DState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */