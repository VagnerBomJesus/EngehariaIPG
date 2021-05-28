/*     */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.image;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.net.URL;
/*     */ import javax.media.j3d.ImageComponent2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageComponent2DURL
/*     */   extends ImageComponent2D
/*     */ {
/*  49 */   private URL url = null;
/*     */ 
/*     */   
/*  52 */   public ImageComponent2DURL(int paramInt, BufferedImage paramBufferedImage) { super(paramInt, paramBufferedImage); }
/*     */ 
/*     */   
/*     */   public ImageComponent2DURL(int paramInt, BufferedImage paramBufferedImage, URL paramURL) {
/*  56 */     super(paramInt, paramBufferedImage);
/*  57 */     this.url = paramURL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public ImageComponent2DURL(int paramInt, BufferedImage paramBufferedImage, boolean paramBoolean1, boolean paramBoolean2) { super(paramInt, paramBufferedImage, paramBoolean1, paramBoolean2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageComponent2DURL(int paramInt, BufferedImage paramBufferedImage, boolean paramBoolean1, boolean paramBoolean2, URL paramURL) {
/*  70 */     super(paramInt, paramBufferedImage, paramBoolean1, paramBoolean2);
/*  71 */     this.url = paramURL;
/*     */   }
/*     */ 
/*     */   
/*  75 */   public ImageComponent2DURL(int paramInt1, int paramInt2, int paramInt3) { super(paramInt1, paramInt2, paramInt3); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public ImageComponent2DURL(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2) { super(paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2); }
/*     */ 
/*     */ 
/*     */   
/*  84 */   public ImageComponent2DURL(int paramInt, RenderedImage paramRenderedImage) { super(paramInt, paramRenderedImage); }
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageComponent2DURL(int paramInt, RenderedImage paramRenderedImage, URL paramURL) {
/*  89 */     super(paramInt, paramRenderedImage);
/*  90 */     this.url = paramURL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  95 */   public ImageComponent2DURL(int paramInt, RenderedImage paramRenderedImage, boolean paramBoolean1, boolean paramBoolean2) { super(paramInt, paramRenderedImage, paramBoolean1, paramBoolean2); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImageComponent2DURL(int paramInt, RenderedImage paramRenderedImage, boolean paramBoolean1, boolean paramBoolean2, URL paramURL) {
/* 101 */     super(paramInt, paramRenderedImage, paramBoolean1, paramBoolean2);
/* 102 */     this.url = paramURL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public void setURL(URL paramURL) { this.url = paramURL; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public URL getURL() { return this.url; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\utils\image\ImageComponent2DURL.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */