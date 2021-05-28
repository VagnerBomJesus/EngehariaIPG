/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.AWTException;
/*     */ import java.awt.DisplayMode;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.ImageCapabilities;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.VolatileImage;
/*     */ import javax.media.opengl.GLCapabilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class JoglGraphicsConfiguration
/*     */   extends GraphicsConfiguration
/*     */ {
/*     */   private GLCapabilities caps;
/*     */   private int chosenIndex;
/*     */   private GraphicsDevice device;
/*     */   private int width;
/*     */   private int height;
/*     */   
/*     */   JoglGraphicsConfiguration(GLCapabilities paramGLCapabilities, int paramInt, GraphicsDevice paramGraphicsDevice) {
/*  37 */     this.caps = paramGLCapabilities;
/*  38 */     this.chosenIndex = paramInt;
/*  39 */     this.device = paramGraphicsDevice;
/*  40 */     DisplayMode displayMode = paramGraphicsDevice.getDisplayMode();
/*  41 */     this.width = displayMode.getWidth();
/*  42 */     this.height = displayMode.getHeight();
/*     */   }
/*     */ 
/*     */   
/*  46 */   GLCapabilities getGLCapabilities() { return this.caps; }
/*     */ 
/*     */ 
/*     */   
/*  50 */   int getChosenIndex() { return this.chosenIndex; }
/*     */ 
/*     */ 
/*     */   
/*  54 */   public BufferedImage createCompatibleImage(int paramInt1, int paramInt2) { throw new RuntimeException("Unimplemented"); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public BufferedImage createCompatibleImage(int paramInt1, int paramInt2, int paramInt3) { throw new RuntimeException("Unimplemented"); }
/*     */ 
/*     */ 
/*     */   
/*  63 */   public VolatileImage createCompatibleVolatileImage(int paramInt1, int paramInt2) { throw new RuntimeException("Unimplemented"); }
/*     */ 
/*     */ 
/*     */   
/*  67 */   public VolatileImage createCompatibleVolatileImage(int paramInt1, int paramInt2, int paramInt3) { throw new RuntimeException("Unimplemented"); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public VolatileImage createCompatibleVolatileImage(int paramInt1, int paramInt2, ImageCapabilities paramImageCapabilities) throws AWTException { throw new RuntimeException("Unimplemented"); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public VolatileImage createCompatibleVolatileImage(int paramInt1, int paramInt2, ImageCapabilities paramImageCapabilities, int paramInt3) throws AWTException { throw new RuntimeException("Unimplemented"); }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public Rectangle getBounds() { return new Rectangle(0, 0, this.width, this.height); }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public ColorModel getColorModel() { throw new RuntimeException("Unimplemented"); }
/*     */ 
/*     */ 
/*     */   
/*  89 */   public ColorModel getColorModel(int paramInt) { throw new RuntimeException("Unimplemented"); }
/*     */ 
/*     */ 
/*     */   
/*  93 */   public AffineTransform getDefaultTransform() { throw new RuntimeException("Unimplemented"); }
/*     */ 
/*     */ 
/*     */   
/*  97 */   public GraphicsDevice getDevice() { return this.device; }
/*     */ 
/*     */ 
/*     */   
/* 101 */   public AffineTransform getNormalizingTransform() { throw new RuntimeException("Unimplemented"); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\JoglGraphicsConfiguration.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */