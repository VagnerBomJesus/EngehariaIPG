/*      */ package javax.media.j3d;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Image;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Polygon;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.font.TextLayout;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.BufferedImageOp;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ImageObserver;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.awt.image.renderable.RenderableImage;
/*      */ import java.text.AttributedCharacterIterator;
/*      */ import java.util.Map;
/*      */ 
/*      */ final class J3DGraphics2DImpl extends J3DGraphics2D {
/*      */   private boolean hasBeenDisposed;
/*      */   private Graphics2D offScreenGraphics2D;
/*      */   private BufferedImage g3dImage;
/*      */   private byte[] data;
/*      */   private boolean isFlushed;
/*      */   private Canvas3D canvas3d;
/*      */   private int width;
/*      */   private int height;
/*      */   private int texWidth;
/*      */   private int texHeight;
/*      */   private int xmin;
/*      */   private int ymin;
/*      */   private int xmax;
/*      */   private int ymax;
/*      */   private Object extentLock;
/*      */   private boolean abgr;
/*      */   private boolean initTexMap;
/*      */   private boolean strokeSet;
/*      */   private Point2D.Float ptSrc;
/*      */   private Point2D.Float ptDst1;
/*      */   private Point2D.Float ptDst2;
/*      */   private Color xOrModeColor;
/*   50 */   static final Color blackTransparent = new Color(0, 0, 0, 0); J3DGraphics2DImpl(Canvas3D paramCanvas3D) { this.hasBeenDisposed = false; this.g3dImage = null; this.data = null; this.isFlushed = true; this.extentLock = new Object(); this.initTexMap = false; this.strokeSet = false; this.ptSrc = new Point2D.Float(); this.ptDst1 = new Point2D.Float(); this.ptDst2 = new Point2D.Float(); this.xOrModeColor = null; this.initCtx = false; this.threadWaiting = false;
/*   51 */     this.objectId = -1;
/*      */ 
/*      */ 
/*      */     
/*   55 */     this.canvas3d = paramCanvas3D;
/*      */     
/*   57 */     synchronized (VirtualUniverse.mc.contextCreationLock) {
/*   58 */       if (paramCanvas3D.ctx == null) {
/*      */         
/*   60 */         this.width = 1;
/*   61 */         this.height = 1;
/*   62 */         this.g3dImage = new BufferedImage(this.width, this.height, 2);
/*      */         
/*   64 */         this.offScreenGraphics2D = this.g3dImage.createGraphics();
/*      */       } else {
/*   66 */         init();
/*      */       } 
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int objectId;
/*      */ 
/*      */ 
/*      */   
/*      */   void init() {
/*   78 */     if (!this.initCtx) {
/*   79 */       this.abgr = ((this.canvas3d.extensionsSupported & 0x2) != 0);
/*      */       
/*   81 */       this.width = this.canvas3d.getWidth();
/*   82 */       this.height = this.canvas3d.getHeight();
/*   83 */       this.initTexMap = false;
/*      */       
/*   85 */       if (this.width <= 0) {
/*   86 */         this.width = 1;
/*      */       }
/*   88 */       if (this.height <= 0) {
/*   89 */         this.height = 1;
/*      */       }
/*      */       
/*   92 */       synchronized (this.extentLock) {
/*   93 */         this.xmax = this.width;
/*   94 */         this.ymax = this.height;
/*   95 */         this.xmin = 0;
/*   96 */         this.ymin = 0;
/*      */       } 
/*   98 */       this.g3dImage = new BufferedImage(this.width, this.height, this.abgr ? 6 : 2);
/*      */ 
/*      */       
/*  101 */       this.offScreenGraphics2D = this.g3dImage.createGraphics();
/*  102 */       clearOffScreen();
/*  103 */       if (!this.abgr) {
/*  104 */         this.data = new byte[this.width * this.height * 4];
/*      */       }
/*      */ 
/*      */       
/*  108 */       this.initCtx = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void flush(boolean paramBoolean) {
/*  121 */     if (this.hasBeenDisposed) {
/*  122 */       throw new IllegalStateException(J3dI18N.getString("J3DGraphics2D0"));
/*      */     }
/*      */     
/*  125 */     if (!this.isFlushed) {
/*      */       
/*  127 */       if (Thread.currentThread() == this.canvas3d.screen.renderer) {
/*  128 */         if (!this.initCtx) {
/*      */           return;
/*      */         }
/*  131 */         doFlush();
/*      */       } else {
/*  133 */         if (!this.initCtx) {
/*  134 */           if (paramBoolean && this.canvas3d.pendingView != null && this.canvas3d.pendingView.activeStatus) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  139 */             while (!this.initCtx) {
/*  140 */               MasterControl.threadYield();
/*      */             }
/*      */           } else {
/*      */             return;
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  150 */         boolean bool = (Thread.currentThread() != this.canvas3d.view.universe.behaviorScheduler);
/*      */ 
/*      */         
/*  153 */         this.threadWaiting = true;
/*  154 */         sendRenderMessage(bool, 23, null, null, null);
/*      */         
/*  156 */         if (paramBoolean)
/*      */         {
/*      */           
/*  159 */           runMonitor(0);
/*      */         }
/*      */       } 
/*  162 */       this.isFlushed = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void doFlush() {
/*  169 */     assert !this.hasBeenDisposed;
/*      */ 
/*      */     
/*  172 */     if (this.canvas3d.ctx == null) {
/*  173 */       this.canvas3d.getGraphicsContext3D().doClear();
/*      */     }
/*      */     
/*  176 */     synchronized (this.extentLock) {
/*  177 */       if (this.xmin < 0) {
/*  178 */         this.xmin = 0;
/*      */       }
/*  180 */       if (this.xmax > this.width) {
/*  181 */         this.xmax = this.width;
/*      */       }
/*  183 */       if (this.ymin < 0) {
/*  184 */         this.ymin = 0;
/*      */       }
/*  186 */       if (this.ymax > this.height) {
/*  187 */         this.ymax = this.height;
/*      */       }
/*      */       
/*  190 */       if (this.xmax - this.xmin > 0 && this.ymax - this.ymin > 0) {
/*      */         
/*  192 */         if (this.abgr) {
/*  193 */           this.data = ((DataBufferByte)this.g3dImage.getRaster().getDataBuffer()).getData();
/*      */         } else {
/*  195 */           copyImage(this.g3dImage, this.data, this.width, this.height, this.xmin, this.ymin, this.xmax, this.ymax);
/*      */         } 
/*  197 */         copyDataToCanvas(0, 0, this.xmin, this.ymin, this.xmax, this.ymax, this.width, this.height);
/*      */       } else {
/*      */         
/*  200 */         runMonitor(2);
/*      */       } 
/*      */       
/*  203 */       this.xmax = 0;
/*  204 */       this.ymax = 0;
/*  205 */       this.xmin = this.width;
/*  206 */       this.ymin = this.height;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void copyImage(BufferedImage paramBufferedImage, byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/*  218 */     assert !this.hasBeenDisposed;
/*      */     
/*  220 */     int i = paramBufferedImage.getType();
/*      */ 
/*      */ 
/*      */     
/*  224 */     int m = 0;
/*  225 */     int k = 1;
/*  226 */     int j = 0;
/*      */ 
/*      */     
/*  229 */     if (i == 2 || i == 1) {
/*      */ 
/*      */       
/*  232 */       j = paramInt4;
/*      */       
/*  234 */       int n = paramInt3;
/*      */       
/*  236 */       int[] arrayOfInt = ((DataBufferInt)paramBufferedImage.getRaster().getDataBuffer()).getData();
/*      */       
/*  238 */       int i1 = k * paramInt1;
/*      */ 
/*      */       
/*  241 */       j = j * paramInt1 + n;
/*  242 */       m = j * 4;
/*      */       
/*  244 */       if (i == 2) {
/*  245 */         for (int i2 = paramInt4; i2 < paramInt6; i2++) {
/*  246 */           int i4 = j;
/*  247 */           int i5 = m;
/*  248 */           for (int i3 = paramInt3; i3 < paramInt5; i3++, i4++) {
/*  249 */             int i6 = arrayOfInt[i4];
/*  250 */             paramArrayOfByte[i5++] = (byte)(i6 >> 16 & 0xFF);
/*  251 */             paramArrayOfByte[i5++] = (byte)(i6 >> 8 & 0xFF);
/*  252 */             paramArrayOfByte[i5++] = (byte)(i6 & 0xFF);
/*  253 */             paramArrayOfByte[i5++] = (byte)(i6 >> 24 & 0xFF);
/*      */           } 
/*  255 */           j += i1;
/*  256 */           m += i1 * 4;
/*      */         } 
/*      */       } else {
/*  259 */         for (int i2 = paramInt4; i2 < paramInt6; i2++) {
/*  260 */           int i4 = j;
/*  261 */           int i5 = m;
/*  262 */           for (int i3 = paramInt3; i3 < paramInt5; i3++, i4++) {
/*  263 */             int i6 = arrayOfInt[i4];
/*  264 */             paramArrayOfByte[i5++] = (byte)(i6 >> 16 & 0xFF);
/*  265 */             paramArrayOfByte[i5++] = (byte)(i6 >> 8 & 0xFF);
/*  266 */             paramArrayOfByte[i5++] = (byte)(i6 & 0xFF);
/*  267 */             paramArrayOfByte[i5++] = -1;
/*      */           } 
/*  269 */           j += i1;
/*  270 */           m += i1 * 4;
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/*  275 */       WritableRaster writableRaster = paramBufferedImage.getRaster();
/*  276 */       ColorModel colorModel = paramBufferedImage.getColorModel();
/*  277 */       Object object = ImageComponentRetained.getDataElementBuffer(writableRaster);
/*      */       
/*  279 */       int i1 = (paramInt4 * paramInt1 + paramInt3) * 4;
/*  280 */       for (int n = paramInt4; n < paramInt6; n++) {
/*  281 */         int i3 = i1;
/*  282 */         for (int i2 = paramInt3; i2 < paramInt5; i2++) {
/*  283 */           writableRaster.getDataElements(i2, n, object);
/*  284 */           paramArrayOfByte[i1++] = (byte)colorModel.getRed(object);
/*  285 */           paramArrayOfByte[i1++] = (byte)colorModel.getGreen(object);
/*  286 */           paramArrayOfByte[i1++] = (byte)colorModel.getBlue(object);
/*  287 */           paramArrayOfByte[i1++] = (byte)colorModel.getAlpha(object);
/*      */         } 
/*      */         
/*  290 */         i1 = i3 + paramInt1 * 4;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void sendRenderMessage(boolean paramBoolean, int paramInt, Object paramObject1, Object paramObject2, Object paramObject3) {
/*  298 */     J3dMessage j3dMessage = new J3dMessage();
/*  299 */     j3dMessage.threads = 16;
/*  300 */     j3dMessage.type = 44;
/*  301 */     j3dMessage.universe = null;
/*  302 */     j3dMessage.view = null;
/*  303 */     j3dMessage.args[0] = this.canvas3d;
/*  304 */     j3dMessage.args[1] = new Integer(paramInt);
/*  305 */     j3dMessage.args[2] = paramObject1;
/*  306 */     j3dMessage.args[3] = paramObject2;
/*  307 */     j3dMessage.args[4] = paramObject3;
/*      */     
/*  309 */     while (!this.canvas3d.view.inRenderThreadData)
/*      */     {
/*      */       
/*  312 */       MasterControl.threadYield();
/*      */     }
/*      */     
/*  315 */     this.canvas3d.screen.renderer.rendererStructure.addMessage(j3dMessage);
/*      */     
/*  317 */     if (paramBoolean) {
/*      */       
/*  319 */       VirtualUniverse.mc.sendRunMessage(this.canvas3d.view, 16);
/*      */     } else {
/*      */       
/*  322 */       VirtualUniverse.mc.setWorkForRequestRenderer();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  327 */   final void validate() { validate(0.0F, 0.0F, this.width, this.height); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void validate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, AffineTransform paramAffineTransform) {
/*  334 */     if (paramAffineTransform == null) {
/*  335 */       validate(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
/*      */     } else {
/*  337 */       this.ptSrc.x = paramFloat1;
/*  338 */       this.ptSrc.y = paramFloat2;
/*  339 */       paramAffineTransform.transform(this.ptSrc, this.ptDst1);
/*  340 */       this.ptSrc.x = paramFloat3;
/*  341 */       this.ptSrc.y = paramFloat4;
/*  342 */       paramAffineTransform.transform(this.ptSrc, this.ptDst2);
/*      */       
/*  344 */       if (this.ptDst1.x > this.ptDst2.x) {
/*  345 */         float f = this.ptDst1.x;
/*  346 */         this.ptDst1.x = this.ptDst2.x;
/*  347 */         this.ptDst2.x = f;
/*      */       } 
/*  349 */       if (this.ptDst1.y > this.ptDst2.y) {
/*  350 */         float f = this.ptDst1.y;
/*  351 */         this.ptDst1.y = this.ptDst2.y;
/*  352 */         this.ptDst2.y = f;
/*      */       } 
/*      */       
/*  355 */       validate(this.ptDst1.x - 1.0F, this.ptDst1.y - 1.0F, this.ptDst2.x + 1.0F, this.ptDst2.y + 1.0F);
/*      */     } 
/*      */   }
/*      */   
/*      */   void validate(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/*  360 */     boolean bool = false;
/*  361 */     this.isFlushed = false;
/*      */     
/*  363 */     synchronized (this.canvas3d) {
/*  364 */       if (this.initCtx && this.canvas3d.resizeGraphics2D) {
/*  365 */         bool = true;
/*  366 */         this.canvas3d.resizeGraphics2D = false;
/*      */       } 
/*      */     } 
/*  369 */     if (bool) {
/*  370 */       synchronized (VirtualUniverse.mc.contextCreationLock) {
/*  371 */         Graphics2D graphics2D = this.offScreenGraphics2D;
/*  372 */         this.initCtx = false;
/*  373 */         init();
/*  374 */         copyGraphics2D(graphics2D);
/*      */       } 
/*      */     } else {
/*  377 */       AffineTransform affineTransform = getTransform();
/*  378 */       this.ptSrc.x = paramFloat1;
/*  379 */       this.ptSrc.y = paramFloat2;
/*  380 */       affineTransform.transform(this.ptSrc, this.ptDst1);
/*  381 */       this.ptSrc.x = paramFloat3;
/*  382 */       this.ptSrc.y = paramFloat4;
/*  383 */       affineTransform.transform(this.ptSrc, this.ptDst2);
/*      */       
/*  385 */       synchronized (this.extentLock) {
/*  386 */         if (this.ptDst1.x < this.xmin) {
/*  387 */           this.xmin = (int)this.ptDst1.x;
/*      */         }
/*  389 */         if (this.ptDst1.y < this.ymin) {
/*  390 */           this.ymin = (int)this.ptDst1.y;
/*      */         }
/*  392 */         if (this.ptDst2.x > this.xmax) {
/*  393 */           this.xmax = (int)this.ptDst2.x;
/*      */         }
/*  395 */         if (this.ptDst2.y > this.ymax) {
/*  396 */           this.ymax = (int)this.ptDst2.y;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void copyGraphics2D(Graphics2D paramGraphics2D) {
/*  404 */     setColor(paramGraphics2D.getColor());
/*  405 */     setFont(paramGraphics2D.getFont());
/*  406 */     setClip(paramGraphics2D.getClip());
/*  407 */     setComposite(paramGraphics2D.getComposite());
/*  408 */     setTransform(paramGraphics2D.getTransform());
/*  409 */     setPaint(paramGraphics2D.getPaint());
/*  410 */     setStroke(paramGraphics2D.getStroke());
/*  411 */     if (this.xOrModeColor != null) {
/*  412 */       setXORMode(this.xOrModeColor);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  419 */   public final void clip(Shape paramShape) { this.offScreenGraphics2D.clip(paramShape); }
/*      */ 
/*      */ 
/*      */   
/*  423 */   public FontMetrics getFontMetrics() { return this.offScreenGraphics2D.getFontMetrics(); }
/*      */ 
/*      */ 
/*      */   
/*  427 */   public Rectangle getClipBounds(Rectangle paramRectangle) { return this.offScreenGraphics2D.getClipBounds(paramRectangle); }
/*      */ 
/*      */ 
/*      */   
/*  431 */   public Rectangle getClipRect() { return this.offScreenGraphics2D.getClipRect(); }
/*      */ 
/*      */ 
/*      */   
/*  435 */   public String toString() { return this.offScreenGraphics2D.toString(); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  440 */   public final AffineTransform getTransform() { return this.offScreenGraphics2D.getTransform(); }
/*      */ 
/*      */ 
/*      */   
/*  444 */   public final Color getColor() { return this.offScreenGraphics2D.getColor(); }
/*      */ 
/*      */ 
/*      */   
/*  448 */   public final Composite getComposite() { return this.offScreenGraphics2D.getComposite(); }
/*      */ 
/*      */ 
/*      */   
/*  452 */   public final Font getFont() { return this.offScreenGraphics2D.getFont(); }
/*      */ 
/*      */ 
/*      */   
/*  456 */   public final FontMetrics getFontMetrics(Font paramFont) { return this.offScreenGraphics2D.getFontMetrics(paramFont); }
/*      */ 
/*      */ 
/*      */   
/*  460 */   public final FontRenderContext getFontRenderContext() { return this.offScreenGraphics2D.getFontRenderContext(); }
/*      */ 
/*      */ 
/*      */   
/*  464 */   public final GraphicsConfiguration getDeviceConfiguration() { return this.offScreenGraphics2D.getDeviceConfiguration(); }
/*      */ 
/*      */ 
/*      */   
/*  468 */   public final Object getRenderingHint(RenderingHints.Key paramKey) { return this.offScreenGraphics2D.getRenderingHint(paramKey); }
/*      */ 
/*      */ 
/*      */   
/*  472 */   public final Paint getPaint() { return this.offScreenGraphics2D.getPaint(); }
/*      */ 
/*      */ 
/*      */   
/*  476 */   public final Rectangle getClipBounds() { return this.offScreenGraphics2D.getClipBounds(); }
/*      */ 
/*      */ 
/*      */   
/*  480 */   public final RenderingHints getRenderingHints() { return this.offScreenGraphics2D.getRenderingHints(); }
/*      */ 
/*      */ 
/*      */   
/*  484 */   public final Shape getClip() { return this.offScreenGraphics2D.getClip(); }
/*      */ 
/*      */ 
/*      */   
/*  488 */   public final Stroke getStroke() { return this.offScreenGraphics2D.getStroke(); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean drawImage(Image paramImage, AffineTransform paramAffineTransform, ImageObserver paramImageObserver) {
/*  494 */     validate(0.0F, 0.0F, paramImage.getWidth(paramImageObserver), paramImage.getHeight(paramImageObserver), paramAffineTransform);
/*  495 */     return this.offScreenGraphics2D.drawImage(paramImage, paramAffineTransform, paramImageObserver);
/*      */   }
/*      */ 
/*      */   
/*      */   public final void drawImage(BufferedImage paramBufferedImage, BufferedImageOp paramBufferedImageOp, int paramInt1, int paramInt2) {
/*  500 */     if (paramBufferedImageOp != null) {
/*  501 */       paramBufferedImage = paramBufferedImageOp.filter(paramBufferedImage, null);
/*      */     }
/*  503 */     validate(paramInt1, paramInt2, (paramInt1 + paramBufferedImage.getWidth()), (paramInt2 + paramBufferedImage.getHeight()));
/*  504 */     this.offScreenGraphics2D.drawImage(paramBufferedImage, null, paramInt1, paramInt2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean drawImage(Image paramImage, int paramInt1, int paramInt2, ImageObserver paramImageObserver) {
/*  511 */     validate(paramInt1, paramInt2, (paramInt1 + paramImage.getWidth(paramImageObserver)), (paramInt2 + paramImage.getWidth(paramImageObserver)));
/*      */ 
/*      */     
/*  514 */     return this.offScreenGraphics2D.drawImage(paramImage, paramInt1, paramInt2, paramImageObserver);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean drawImage(Image paramImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, ImageObserver paramImageObserver) {
/*  520 */     validate(paramInt1, paramInt2, (paramInt1 + paramInt3), (paramInt2 + paramInt4));
/*  521 */     return this.offScreenGraphics2D.drawImage(paramImage, paramInt1, paramInt2, paramInt3, paramInt4, paramImageObserver);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean drawImage(Image paramImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Color paramColor, ImageObserver paramImageObserver) {
/*  529 */     validate(paramInt1, paramInt2, (paramInt1 + paramInt3), (paramInt2 + paramInt4));
/*  530 */     return this.offScreenGraphics2D.drawImage(paramImage, paramInt1, paramInt2, paramInt3, paramInt4, paramColor, paramImageObserver);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void drawImage(BufferedImage paramBufferedImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, ImageObserver paramImageObserver) {
/*  538 */     validate(paramInt1, paramInt2, paramInt3, paramInt4);
/*  539 */     this.offScreenGraphics2D.drawImage(paramBufferedImage, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramImageObserver);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean drawImage(Image paramImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, ImageObserver paramImageObserver) {
/*  547 */     validate(paramInt1, paramInt2, paramInt3, paramInt4);
/*  548 */     return this.offScreenGraphics2D.drawImage(paramImage, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramImageObserver);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean drawImage(Image paramImage, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, Color paramColor, ImageObserver paramImageObserver) {
/*  557 */     validate(paramInt1, paramInt2, paramInt3, paramInt4);
/*  558 */     return this.offScreenGraphics2D.drawImage(paramImage, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramColor, paramImageObserver);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean drawImage(Image paramImage, int paramInt1, int paramInt2, Color paramColor, ImageObserver paramImageObserver) {
/*  565 */     validate(paramInt1, paramInt2, (paramInt1 + paramImage.getWidth(paramImageObserver)), (paramInt2 + paramImage.getHeight(paramImageObserver)));
/*  566 */     return this.offScreenGraphics2D.drawImage(paramImage, paramInt1, paramInt2, paramColor, paramImageObserver);
/*      */   }
/*      */ 
/*      */   
/*  570 */   public final boolean hit(Rectangle paramRectangle, Shape paramShape, boolean paramBoolean) { return this.offScreenGraphics2D.hit(paramRectangle, paramShape, paramBoolean); }
/*      */ 
/*      */ 
/*      */   
/*  574 */   public final void addRenderingHints(Map paramMap) { this.offScreenGraphics2D.addRenderingHints(paramMap); }
/*      */ 
/*      */ 
/*      */   
/*  578 */   public final void clipRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4) { this.offScreenGraphics2D.clipRect(paramInt1, paramInt2, paramInt3, paramInt4); }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void copyArea(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/*  583 */     validate((paramInt1 + paramInt5), (paramInt2 + paramInt6), (paramInt1 + paramInt5 + paramInt3), (paramInt2 + paramInt6 + paramInt4));
/*  584 */     this.offScreenGraphics2D.copyArea(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/*      */   }
/*      */   
/*      */   public final void draw(Shape paramShape) {
/*  588 */     Rectangle rectangle = paramShape.getBounds();
/*  589 */     validate(rectangle.x, rectangle.y, (rectangle.x + rectangle.width), (rectangle.y + rectangle.height));
/*      */ 
/*      */     
/*  592 */     this.offScreenGraphics2D.draw(paramShape);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void drawArc(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/*  598 */     validate();
/*  599 */     this.offScreenGraphics2D.drawArc(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/*      */   }
/*      */ 
/*      */   
/*      */   public final void drawGlyphVector(GlyphVector paramGlyphVector, float paramFloat1, float paramFloat2) {
/*  604 */     validate();
/*  605 */     this.offScreenGraphics2D.drawGlyphVector(paramGlyphVector, paramFloat1, paramFloat2);
/*      */   }
/*      */ 
/*      */   
/*      */   public final void drawLine(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  610 */     if (!this.strokeSet) {
/*  611 */       int m, k, j, i; if (paramInt1 > paramInt3) {
/*  612 */         i = paramInt3;
/*  613 */         k = paramInt1;
/*      */       } else {
/*  615 */         i = paramInt1;
/*  616 */         k = paramInt3;
/*      */       } 
/*  618 */       if (paramInt2 > paramInt4) {
/*  619 */         j = paramInt4;
/*  620 */         m = paramInt2;
/*      */       } else {
/*  622 */         j = paramInt2;
/*  623 */         m = paramInt4;
/*      */       } 
/*  625 */       validate(i, j, k, m);
/*      */     }
/*      */     else {
/*      */       
/*  629 */       validate();
/*      */     } 
/*  631 */     this.offScreenGraphics2D.drawLine(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */ 
/*      */   
/*      */   public final void drawOval(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  636 */     validate();
/*  637 */     this.offScreenGraphics2D.drawOval(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void drawPolygon(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt) {
/*  643 */     validate();
/*  644 */     this.offScreenGraphics2D.drawPolygon(paramArrayOfInt1, paramArrayOfInt2, paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void drawPolyline(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt) {
/*  650 */     validate();
/*  651 */     this.offScreenGraphics2D.drawPolyline(paramArrayOfInt1, paramArrayOfInt2, paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void drawRenderableImage(RenderableImage paramRenderableImage, AffineTransform paramAffineTransform) {
/*  657 */     validate(0.0F, 0.0F, paramRenderableImage.getWidth(), paramRenderableImage.getHeight(), paramAffineTransform);
/*  658 */     this.offScreenGraphics2D.drawRenderableImage(paramRenderableImage, paramAffineTransform);
/*      */   }
/*      */ 
/*      */   
/*      */   public final void drawRenderedImage(RenderedImage paramRenderedImage, AffineTransform paramAffineTransform) {
/*  663 */     validate(0.0F, 0.0F, paramRenderedImage.getWidth(), paramRenderedImage.getHeight(), paramAffineTransform);
/*  664 */     this.offScreenGraphics2D.drawRenderedImage(paramRenderedImage, paramAffineTransform);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void drawRoundRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/*  670 */     validate();
/*  671 */     this.offScreenGraphics2D.drawRoundRect(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void drawString(AttributedCharacterIterator paramAttributedCharacterIterator, int paramInt1, int paramInt2) {
/*  678 */     validate();
/*  679 */     this.offScreenGraphics2D.drawString(paramAttributedCharacterIterator, paramInt1, paramInt2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void drawString(AttributedCharacterIterator paramAttributedCharacterIterator, float paramFloat1, float paramFloat2) {
/*  685 */     validate();
/*  686 */     this.offScreenGraphics2D.drawString(paramAttributedCharacterIterator, paramFloat1, paramFloat2);
/*      */   }
/*      */   
/*      */   public final void drawString(String paramString, float paramFloat1, float paramFloat2) {
/*  690 */     TextLayout textLayout = new TextLayout(paramString, getFont(), getFontRenderContext());
/*      */     
/*  692 */     Rectangle2D rectangle2D = textLayout.getBounds();
/*  693 */     float f1 = (float)rectangle2D.getX();
/*  694 */     float f2 = (float)rectangle2D.getY();
/*  695 */     validate(f1 + paramFloat1, f2 + paramFloat2, f1 + paramFloat1 + (float)rectangle2D.getWidth(), f2 + paramFloat2 + (float)rectangle2D.getHeight());
/*      */ 
/*      */     
/*  698 */     this.offScreenGraphics2D.drawString(paramString, paramFloat1, paramFloat2);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  703 */   public final void drawString(String paramString, int paramInt1, int paramInt2) { drawString(paramString, paramInt1, paramInt2); }
/*      */ 
/*      */   
/*      */   public final void fill(Shape paramShape) {
/*  707 */     Rectangle rectangle = paramShape.getBounds();
/*  708 */     validate(rectangle.x, rectangle.y, (rectangle.x + rectangle.width), (rectangle.y + rectangle.height));
/*  709 */     this.offScreenGraphics2D.fill(paramShape);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void fillArc(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/*  715 */     validate();
/*  716 */     this.offScreenGraphics2D.fillArc(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/*      */   }
/*      */ 
/*      */   
/*      */   public final void fillOval(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  721 */     validate();
/*  722 */     this.offScreenGraphics2D.fillOval(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void fillRoundRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/*  728 */     validate();
/*  729 */     this.offScreenGraphics2D.fillRoundRect(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  734 */   public final void rotate(double paramDouble) { this.offScreenGraphics2D.rotate(paramDouble); }
/*      */ 
/*      */ 
/*      */   
/*  738 */   public final void rotate(double paramDouble1, double paramDouble2, double paramDouble3) { this.offScreenGraphics2D.rotate(paramDouble1, paramDouble2, paramDouble3); }
/*      */ 
/*      */ 
/*      */   
/*  742 */   public final void scale(double paramDouble1, double paramDouble2) { this.offScreenGraphics2D.scale(paramDouble1, paramDouble2); }
/*      */ 
/*      */ 
/*      */   
/*  746 */   public final void setClip(Shape paramShape) { this.offScreenGraphics2D.setClip(paramShape); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  751 */   public final void setClip(int paramInt1, int paramInt2, int paramInt3, int paramInt4) { this.offScreenGraphics2D.setClip(paramInt1, paramInt2, paramInt3, paramInt4); }
/*      */ 
/*      */ 
/*      */   
/*  755 */   public final void setColor(Color paramColor) { this.offScreenGraphics2D.setColor(paramColor); }
/*      */ 
/*      */ 
/*      */   
/*  759 */   public final void setComposite(Composite paramComposite) { this.offScreenGraphics2D.setComposite(paramComposite); }
/*      */ 
/*      */ 
/*      */   
/*  763 */   public final void setFont(Font paramFont) { this.offScreenGraphics2D.setFont(paramFont); }
/*      */ 
/*      */ 
/*      */   
/*  767 */   public final void setPaint(Paint paramPaint) { this.offScreenGraphics2D.setPaint(paramPaint); }
/*      */ 
/*      */   
/*      */   public final void setPaintMode() {
/*  771 */     this.xOrModeColor = null;
/*  772 */     this.offScreenGraphics2D.setPaintMode();
/*      */   }
/*      */ 
/*      */   
/*  776 */   public final void setRenderingHint(RenderingHints.Key paramKey, Object paramObject) { this.offScreenGraphics2D.setRenderingHint(paramKey, paramObject); }
/*      */ 
/*      */ 
/*      */   
/*  780 */   public final void setRenderingHints(Map paramMap) { this.offScreenGraphics2D.setRenderingHints(paramMap); }
/*      */ 
/*      */   
/*      */   public final void setStroke(Stroke paramStroke) {
/*  784 */     this.strokeSet = (paramStroke != null);
/*  785 */     this.offScreenGraphics2D.setStroke(paramStroke);
/*      */   }
/*      */ 
/*      */   
/*  789 */   public final void setTransform(AffineTransform paramAffineTransform) { this.offScreenGraphics2D.setTransform(paramAffineTransform); }
/*      */ 
/*      */   
/*      */   public final void setXORMode(Color paramColor) {
/*  793 */     this.xOrModeColor = paramColor;
/*  794 */     this.offScreenGraphics2D.setXORMode(paramColor);
/*      */   }
/*      */ 
/*      */   
/*  798 */   public final void shear(double paramDouble1, double paramDouble2) { this.offScreenGraphics2D.shear(paramDouble1, paramDouble2); }
/*      */ 
/*      */ 
/*      */   
/*  802 */   public final void transform(AffineTransform paramAffineTransform) { this.offScreenGraphics2D.transform(paramAffineTransform); }
/*      */ 
/*      */ 
/*      */   
/*  806 */   public final void translate(double paramDouble1, double paramDouble2) { this.offScreenGraphics2D.translate(paramDouble1, paramDouble2); }
/*      */ 
/*      */ 
/*      */   
/*  810 */   public final void translate(int paramInt1, int paramInt2) { this.offScreenGraphics2D.translate(paramInt1, paramInt2); }
/*      */ 
/*      */   
/*  813 */   public boolean hitClip(int paramInt1, int paramInt2, int paramInt3, int paramInt4) { return this.offScreenGraphics2D.hitClip(paramInt1, paramInt2, paramInt3, paramInt4); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void draw3DRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
/*  819 */     validate();
/*  820 */     this.offScreenGraphics2D.draw3DRect(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean);
/*      */   }
/*      */ 
/*      */   
/*      */   public void drawBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  825 */     validate();
/*  826 */     this.offScreenGraphics2D.drawBytes(paramArrayOfByte, paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */ 
/*      */   
/*      */   public void drawChars(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  831 */     validate();
/*  832 */     this.offScreenGraphics2D.drawChars(paramArrayOfChar, paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */ 
/*      */   
/*      */   public void drawPolygon(Polygon paramPolygon) {
/*  837 */     validate();
/*  838 */     this.offScreenGraphics2D.drawPolygon(paramPolygon);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  844 */     validate();
/*  845 */     this.offScreenGraphics2D.drawRect(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fill3DRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
/*  852 */     validate();
/*  853 */     this.offScreenGraphics2D.fill3DRect(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean);
/*      */   }
/*      */ 
/*      */   
/*      */   public void fillPolygon(Polygon paramPolygon) {
/*  858 */     validate();
/*  859 */     this.offScreenGraphics2D.fillPolygon(paramPolygon);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void fillPolygon(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt) {
/*  865 */     validate();
/*  866 */     this.offScreenGraphics2D.fillPolygon(paramArrayOfInt1, paramArrayOfInt2, paramInt);
/*      */   }
/*      */ 
/*      */   
/*      */   public final void fillRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  871 */     validate();
/*  872 */     this.offScreenGraphics2D.fillRect(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void dispose() {
/*  878 */     if (Thread.currentThread() == this.canvas3d.screen.renderer) {
/*  879 */       doDispose();
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  885 */       boolean bool = (Thread.currentThread() != this.canvas3d.view.universe.behaviorScheduler);
/*      */       
/*  887 */       sendRenderMessage(bool, 26, null, null, null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void doDispose() {
/*  896 */     if (this.hasBeenDisposed) {
/*      */       return;
/*      */     }
/*      */     
/*  900 */     if (this.objectId != -1) {
/*  901 */       this.canvas3d.freeTexture(this.canvas3d.ctx, this.objectId);
/*  902 */       VirtualUniverse.mc.freeTexture2DId(this.objectId);
/*  903 */       this.objectId = -1;
/*      */     } 
/*      */ 
/*      */     
/*  907 */     this.offScreenGraphics2D.dispose();
/*      */ 
/*      */     
/*  910 */     this.hasBeenDisposed = true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawAndFlushImage(BufferedImage paramBufferedImage, int paramInt1, int paramInt2, ImageObserver paramImageObserver) {
/*  916 */     if (this.hasBeenDisposed) {
/*  917 */       throw new IllegalStateException(J3dI18N.getString("J3DGraphics2D0"));
/*      */     }
/*      */     
/*  920 */     if (!this.initCtx || !this.abgr || paramBufferedImage.getType() != 6) {
/*      */       
/*  922 */       drawImage(paramBufferedImage, paramInt1, paramInt2, paramImageObserver);
/*  923 */       flush(false);
/*      */       
/*      */       return;
/*      */     } 
/*  927 */     if (Thread.currentThread() == this.canvas3d.screen.renderer) {
/*  928 */       doDrawAndFlushImage(paramBufferedImage, paramInt1, paramInt2, paramImageObserver);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  934 */       boolean bool = (Thread.currentThread() != this.canvas3d.view.universe.behaviorScheduler);
/*      */       
/*  936 */       sendRenderMessage(bool, 24, paramBufferedImage, new Point(paramInt1, paramInt2), paramImageObserver);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void doDrawAndFlushImage(BufferedImage paramBufferedImage, int paramInt1, int paramInt2, ImageObserver paramImageObserver) {
/*      */     int i3, i2, i1, n;
/*  944 */     assert !this.hasBeenDisposed;
/*      */     
/*  946 */     int i = paramBufferedImage.getWidth(paramImageObserver);
/*  947 */     int j = paramBufferedImage.getHeight(paramImageObserver);
/*      */ 
/*      */     
/*  950 */     if (this.canvas3d.ctx == null) {
/*  951 */       this.canvas3d.getGraphicsContext3D().doClear();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  956 */     this.data = ((DataBufferByte)paramBufferedImage.getRaster().getDataBuffer()).getData();
/*      */ 
/*      */ 
/*      */     
/*  960 */     AffineTransform affineTransform = getTransform();
/*  961 */     this.ptSrc.x = paramInt1;
/*  962 */     this.ptSrc.y = paramInt2;
/*  963 */     affineTransform.transform(this.ptSrc, this.ptDst1);
/*  964 */     int k = (int)this.ptDst1.x;
/*  965 */     int m = (int)this.ptDst1.y;
/*      */ 
/*      */ 
/*      */     
/*  969 */     if (k + i > this.width) {
/*  970 */       i2 = this.width - k;
/*      */     } else {
/*  972 */       i2 = i;
/*      */     } 
/*      */     
/*  975 */     if (k < 0) {
/*  976 */       n = -k;
/*  977 */       k = 0;
/*      */     } else {
/*  979 */       n = 0;
/*      */     } 
/*      */     
/*  982 */     if (m + j > this.height) {
/*  983 */       i3 = this.height - m;
/*      */     } else {
/*  985 */       i3 = j;
/*      */     } 
/*      */     
/*  988 */     if (m < 0) {
/*  989 */       i1 = -m;
/*  990 */       m = 0;
/*      */     } else {
/*  992 */       i1 = 0;
/*      */     } 
/*      */     
/*  995 */     if (i3 - i1 > 0 && i2 - n > 0) {
/*  996 */       copyDataToCanvas(k, m, n, i1, i2, i3, i, j);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void copyDataToCanvas(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
/*      */     try {
/* 1005 */       if (!this.canvas3d.drawingSurfaceObject.renderLock()) {
/*      */         return;
/*      */       }
/*      */       
/* 1009 */       if (!this.initTexMap) {
/* 1010 */         if (this.objectId == -1) {
/* 1011 */           this.objectId = VirtualUniverse.mc.getTexture2DId();
/*      */         }
/* 1013 */         this.texWidth = getGreaterPowerOf2(paramInt7);
/* 1014 */         this.texHeight = getGreaterPowerOf2(paramInt8);
/*      */ 
/*      */ 
/*      */         
/* 1018 */         if (!this.canvas3d.initTexturemapping(this.canvas3d.ctx, this.texWidth, this.texHeight, this.objectId)) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1023 */           this.initTexMap = false;
/* 1024 */           VirtualUniverse.mc.freeTexture2DId(this.objectId);
/* 1025 */           this.objectId = -1;
/*      */           
/* 1027 */           System.err.println("J3DGraphics2DImpl.copyDataToCanvas() : Fail to get texture resources ...");
/*      */         } else {
/*      */           
/* 1030 */           this.initTexMap = true;
/*      */         } 
/*      */       } 
/* 1033 */       if (this.initTexMap) {
/* 1034 */         this.canvas3d.texturemapping(this.canvas3d.ctx, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, this.texWidth, this.texHeight, paramInt7, this.abgr ? 4 : 8, this.objectId, this.data, this.width, this.height);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1042 */       this.canvas3d.drawingSurfaceObject.unLock();
/* 1043 */     } catch (NullPointerException nullPointerException) {
/* 1044 */       this.canvas3d.drawingSurfaceObject.unLock();
/* 1045 */       throw nullPointerException;
/*      */     } 
/*      */     
/* 1048 */     clearOffScreen();
/* 1049 */     runMonitor(2);
/*      */   }
/*      */   
/*      */   void clearOffScreen() {
/* 1053 */     Composite composite = this.offScreenGraphics2D.getComposite();
/* 1054 */     Color color = this.offScreenGraphics2D.getColor();
/* 1055 */     this.offScreenGraphics2D.setComposite(AlphaComposite.Src);
/* 1056 */     this.offScreenGraphics2D.setColor(blackTransparent);
/* 1057 */     this.offScreenGraphics2D.fillRect(this.xmin, this.ymin, this.xmax - this.xmin, this.ymax - this.ymin);
/* 1058 */     this.offScreenGraphics2D.setComposite(composite);
/* 1059 */     this.offScreenGraphics2D.setColor(color);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static int getGreaterPowerOf2(int paramInt) {
/* 1066 */     byte b = -1;
/* 1067 */     if (paramInt >= 0) {
/* 1068 */       for (b = 1; b < paramInt; b <<= 1);
/*      */     }
/* 1070 */     return b;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void runMonitor(int paramInt) {
/* 1079 */     if (paramInt == 0) {
/*      */       
/* 1081 */       while (this.threadWaiting) {
/*      */         try {
/* 1083 */           wait();
/* 1084 */         } catch (InterruptedException interruptedException) {}
/*      */       } 
/* 1086 */     } else if (paramInt == 2) {
/* 1087 */       notify();
/* 1088 */       this.threadWaiting = false;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\J3DGraphics2DImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */