/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.ImageComponent2D;
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
/*     */ public class ImageComponent2DState
/*     */   extends ImageComponentState
/*     */ {
/*     */   private BufferedImage bufferedImage;
/*     */   
/*  70 */   public ImageComponent2DState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  75 */     super.writeConstructorParams(paramDataOutput);
/*  76 */     ImageComponent2D imageComponent2D = (ImageComponent2D)this.node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     if (imageComponent2D.isByReference())
/*  83 */     { ImageComponent2D imageComponent2D1 = new ImageComponent2D(imageComponent2D.getFormat(), imageComponent2D.getRenderedImage(), false, imageComponent2D.isYUp());
/*     */       
/*  85 */       this.bufferedImage = imageComponent2D1.getImage(); }
/*  86 */     else { this.bufferedImage = imageComponent2D.getImage(); }
/*     */     
/*  88 */     writeBufferedImage(paramDataOutput, this.bufferedImage);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  94 */     super.readConstructorParams(paramDataInput);
/*     */     
/*  96 */     this.bufferedImage = readBufferedImage(paramDataInput);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 101 */   protected SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { int.class, this.bufferedImage.getClass(), boolean.class, boolean.class }, new Object[] { new Integer(this.format), this.bufferedImage, new Boolean(this.byReference), new Boolean(this.yUp) }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   protected SceneGraphObject createNode() { return new ImageComponent2D(this.format, this.bufferedImage, this.byReference, this.yUp); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\ImageComponent2DState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */