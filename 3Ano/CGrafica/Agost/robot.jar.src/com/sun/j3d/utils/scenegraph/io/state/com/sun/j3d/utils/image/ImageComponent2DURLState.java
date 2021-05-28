/*     */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.image;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.ImageComponentState;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
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
/*     */ public class ImageComponent2DURLState
/*     */   extends ImageComponentState
/*     */ {
/*  68 */   private static ImageComponent2DURLIOListener listener = new DefaultListener();
/*     */   
/*     */   private URL url;
/*     */   
/*  72 */   public ImageComponent2DURLState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  77 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  79 */     paramDataOutput.writeUTF(((ImageComponent2DURL)this.node).getURL().toExternalForm());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  85 */     super.readConstructorParams(paramDataInput);
/*     */     
/*  87 */     String str = paramDataInput.readUTF();
/*     */     
/*     */     try {
/*  90 */       this.url = new URL(str);
/*  91 */     } catch (MalformedURLException malformedURLException) {
/*  92 */       throw new RuntimeException("Bad URL in ImageComponent2DURL " + str);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   protected SceneGraphObject createNode(Class paramClass) { return listener.createImageComponent(this.format, this.width, this.height, this.byReference, this.yUp, this.url); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   protected SceneGraphObject createNode() { return listener.createImageComponent(this.format, this.width, this.height, this.byReference, this.yUp, this.url); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public static void setLoadListener(ImageComponent2DURLIOListener paramImageComponent2DURLIOListener) { listener = paramImageComponent2DURLIOListener; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class DefaultListener
/*     */     implements ImageComponent2DURLIOListener
/*     */   {
/*     */     public ImageComponent2DURL createImageComponent(int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean1, boolean param1Boolean2, URL param1URL) {
/* 124 */       System.out.println("Default ImageComponent2DURL loader not implemented " + param1URL);
/*     */ 
/*     */       
/* 127 */       return new ImageComponent2DURL(param1Int1, param1Int2, param1Int3);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\utils\image\ImageComponent2DURLState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */