/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Point2d;
/*     */ import javax.vecmath.Point2i;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Point4d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RasterRetained
/*     */   extends GeometryRetained
/*     */ {
/*  32 */   int type = 1;
/*     */   
/*  34 */   private int clipMode = 0;
/*  35 */   private Point3f position = new Point3f();
/*  36 */   private int xSrcOffset = 0;
/*  37 */   private int ySrcOffset = 0;
/*  38 */   private int width = 0;
/*  39 */   private int height = 0;
/*  40 */   private int xDstOffset = 0;
/*  41 */   private int yDstOffset = 0;
/*  42 */   ImageComponent2DRetained image = null;
/*  43 */   Texture2DRetained texture = null;
/*  44 */   DepthComponentRetained depthComponent = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPosition(Point3f paramPoint3f) {
/*  55 */     this.geomLock.getLock();
/*  56 */     this.position.x = paramPoint3f.x;
/*  57 */     this.position.y = paramPoint3f.y;
/*  58 */     this.position.z = paramPoint3f.z;
/*  59 */     this.geomLock.unLock();
/*  60 */     sendChangedMessage(64, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void getPosition(Point3f paramPoint3f) {
/*  68 */     paramPoint3f.x = this.position.x;
/*  69 */     paramPoint3f.y = this.position.y;
/*  70 */     paramPoint3f.z = this.position.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setType(int paramInt) {
/*  79 */     this.geomLock.getLock();
/*  80 */     this.type = paramInt;
/*  81 */     this.geomLock.unLock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   final int getType() { return this.type; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setClipMode(int paramInt) {
/* 102 */     this.geomLock.getLock();
/* 103 */     this.clipMode = paramInt;
/* 104 */     this.geomLock.unLock();
/* 105 */     computeBoundingBox();
/* 106 */     if (this.source.isLive()) {
/*     */       
/* 108 */       int i = this.userLists.size();
/*     */ 
/*     */ 
/*     */       
/* 112 */       for (byte b = 0; b < i; b++) {
/* 113 */         ArrayList arrayList = (ArrayList)this.userLists.get(b);
/* 114 */         int j = arrayList.size();
/* 115 */         for (byte b1 = 0; b1 < j; b1++) {
/* 116 */           Shape3DRetained shape3DRetained1 = (Shape3DRetained)arrayList.get(b1);
/* 117 */           Shape3DRetained shape3DRetained2 = (Shape3DRetained)shape3DRetained1.sourceNode;
/* 118 */           shape3DRetained2.setBoundsAutoCompute(false);
/* 119 */           shape3DRetained2.setBounds(this.geoBounds);
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
/*     */ 
/*     */ 
/*     */   
/* 133 */   final int getClipMode() { return this.clipMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setSrcOffset(int paramInt1, int paramInt2) {
/* 145 */     this.geomLock.getLock();
/* 146 */     this.xSrcOffset = paramInt1;
/* 147 */     this.ySrcOffset = paramInt2;
/* 148 */     this.geomLock.unLock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   final void getSrcOffset(Point paramPoint) { paramPoint.setLocation(this.xSrcOffset, this.ySrcOffset); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setSize(int paramInt1, int paramInt2) {
/* 165 */     this.geomLock.getLock();
/* 166 */     this.width = paramInt1;
/* 167 */     this.height = paramInt2;
/* 168 */     this.geomLock.unLock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   final void getSize(Dimension paramDimension) { paramDimension.setSize(this.width, this.height); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setDstOffset(int paramInt1, int paramInt2) {
/* 186 */     this.geomLock.getLock();
/* 187 */     this.xDstOffset = paramInt1;
/* 188 */     this.yDstOffset = paramInt2;
/* 189 */     this.geomLock.unLock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 197 */   final void getDstOffset(Point paramPoint) { paramPoint.setLocation(this.xDstOffset, this.yDstOffset); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void initImage(ImageComponent2D paramImageComponent2D) {
/*     */     byte b;
/* 209 */     if (paramImageComponent2D == null) {
/* 210 */       this.image = null;
/* 211 */       this.texture = null;
/*     */       
/*     */       return;
/*     */     } 
/* 215 */     this.image = (ImageComponent2DRetained)paramImageComponent2D.retained;
/* 216 */     this.image.setEnforceNonPowerOfTwoSupport(true);
/* 217 */     switch (this.image.getNumberOfComponents()) {
/*     */       case 1:
/* 219 */         b = 1;
/*     */         break;
/*     */       case 2:
/* 222 */         b = 4;
/*     */         break;
/*     */       case 3:
/* 225 */         b = 5;
/*     */         break;
/*     */       case 4:
/* 228 */         b = 6;
/*     */         break;
/*     */       
/*     */       default:
/*     */         assert false;
/*     */         return;
/*     */     } 
/* 235 */     Texture2D texture2D = new Texture2D(1, b, paramImageComponent2D.getWidth(), paramImageComponent2D.getHeight());
/*     */     
/* 237 */     this.texture = (Texture2DRetained)texture2D.retained;
/* 238 */     this.texture.setUseAsRaster(true);
/*     */     
/* 240 */     this.image.addUser(this.texture);
/* 241 */     this.texture.initImage(0, paramImageComponent2D);
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
/*     */   final void setImage(ImageComponent2D paramImageComponent2D) {
/* 253 */     if (paramImageComponent2D != null && paramImageComponent2D.getImageClass() == ImageComponent.ImageClass.NIO_IMAGE_BUFFER)
/*     */     {
/* 255 */       throw new IllegalArgumentException(J3dI18N.getString("Background14"));
/*     */     }
/*     */     
/* 258 */     Texture2DRetained texture2DRetained = this.texture;
/* 259 */     if (this.source.isLive() && 
/* 260 */       this.texture != null) {
/* 261 */       this.texture.clearLive(this.refCount);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 267 */     this.geomLock.getLock();
/* 268 */     initImage(paramImageComponent2D);
/* 269 */     this.geomLock.unLock();
/*     */     
/* 271 */     if (this.source.isLive()) {
/* 272 */       if (this.texture != null) {
/* 273 */         this.texture.setLive(this.inBackgroundGroup, this.refCount);
/*     */       }
/*     */       
/* 276 */       sendChangedMessage(1152, texture2DRetained, this.texture);
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
/* 287 */   final ImageComponent2D getImage() { return (this.image == null) ? null : (ImageComponent2D)this.image.source; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setDepthComponent(DepthComponent paramDepthComponent) {
/* 297 */     this.geomLock.getLock();
/* 298 */     if (this.source.isLive()) {
/* 299 */       if (this.depthComponent != null) {
/* 300 */         this.depthComponent.clearLive(this.refCount);
/*     */       }
/* 302 */       if (paramDepthComponent != null) {
/* 303 */         ((DepthComponentRetained)paramDepthComponent.retained).setLive(this.inBackgroundGroup, this.refCount);
/*     */       }
/*     */     } 
/*     */     
/* 307 */     if (paramDepthComponent == null) {
/* 308 */       this.depthComponent = null;
/*     */     } else {
/* 310 */       this.depthComponent = (DepthComponentRetained)paramDepthComponent.retained;
/*     */     } 
/*     */     
/* 313 */     this.geomLock.unLock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   final DepthComponent getDepthComponent() { return (this.depthComponent == null) ? null : (DepthComponent)this.depthComponent.source; }
/*     */ 
/*     */ 
/*     */   
/*     */   void setLive(boolean paramBoolean, int paramInt) {
/* 327 */     doSetLive(paramBoolean, paramInt);
/* 328 */     if (this.texture != null) {
/* 329 */       this.texture.setLive(paramBoolean, paramInt);
/*     */     }
/* 331 */     if (this.depthComponent != null) {
/* 332 */       this.depthComponent.setLive(paramBoolean, paramInt);
/*     */     }
/* 334 */     this.isEditable = (this.source.getCapability(3) || this.source.getCapability(1) || ((this.type & true) != 0 && this.source.getCapability(5)) || ((this.type & 0x2) != 0 && this.source.getCapability(7)) || this.source.getCapability(9));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 343 */     markAsLive();
/*     */   }
/*     */   
/*     */   void clearLive(int paramInt) {
/* 347 */     super.clearLive(paramInt);
/* 348 */     if (this.texture != null)
/* 349 */       this.texture.clearLive(paramInt); 
/* 350 */     if (this.depthComponent != null) {
/* 351 */       this.depthComponent.clearLive(paramInt);
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
/*     */   void computeBoundingBox() {
/* 367 */     if (this.clipMode == 1) {
/*     */ 
/*     */       
/* 370 */       Point3d point3d1 = new Point3d(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
/*     */ 
/*     */       
/* 373 */       Point3d point3d2 = new Point3d(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*     */ 
/*     */       
/* 376 */       this.geoBounds.setUpper(point3d2);
/* 377 */       this.geoBounds.setLower(point3d1);
/*     */     } else {
/* 379 */       Point3d point3d = new Point3d();
/* 380 */       point3d.x = this.position.x;
/* 381 */       point3d.y = this.position.y;
/* 382 */       point3d.z = this.position.z;
/* 383 */       this.geoBounds.setUpper(point3d);
/* 384 */       this.geoBounds.setLower(point3d);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 389 */   void update() { computeBoundingBox(); }
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendChangedMessage(int paramInt, Object paramObject1, Object paramObject2) {
/* 394 */     synchronized (this.liveStateLock) {
/* 395 */       if (this.source.isLive()) {
/* 396 */         synchronized (this.universeList) {
/* 397 */           int i = this.universeList.size();
/* 398 */           J3dMessage[] arrayOfJ3dMessage = new J3dMessage[i];
/* 399 */           for (byte b = 0; b < i; b++) {
/* 400 */             arrayOfJ3dMessage[b] = new J3dMessage();
/* 401 */             (arrayOfJ3dMessage[b]).type = 17;
/* 402 */             (arrayOfJ3dMessage[b]).threads = paramInt;
/* 403 */             (arrayOfJ3dMessage[b]).args[0] = Shape3DRetained.getGeomAtomsArray((ArrayList)this.userLists.get(b));
/*     */             
/* 405 */             (arrayOfJ3dMessage[b]).args[1] = this;
/* 406 */             Object[] arrayOfObject = new Object[2];
/* 407 */             arrayOfObject[0] = paramObject1;
/* 408 */             arrayOfObject[1] = paramObject2;
/* 409 */             (arrayOfJ3dMessage[b]).args[2] = arrayOfObject;
/* 410 */             (arrayOfJ3dMessage[b]).args[3] = new Integer(this.changedFrequent);
/* 411 */             (arrayOfJ3dMessage[b]).universe = (VirtualUniverse)this.universeList.get(b);
/*     */           } 
/* 413 */           VirtualUniverse.mc.processMessage(arrayOfJ3dMessage);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void execute(Canvas3D paramCanvas3D, RenderAtom paramRenderAtom, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, int paramInt, boolean paramBoolean3) {
/* 429 */     Point3d point3d1 = new Point3d();
/* 430 */     point3d1.set(this.position);
/*     */     
/* 432 */     Point2d point2d = new Point2d();
/* 433 */     Transform3D transform3D = new Transform3D();
/*     */     
/* 435 */     Point3d point3d2 = computeWinCoord(paramCanvas3D, paramRenderAtom, point2d, point3d1, transform3D);
/*     */ 
/*     */     
/* 438 */     if (point3d2 == null) {
/*     */       return;
/*     */     }
/*     */     
/* 442 */     if (this.clipMode == 0)
/*     */     {
/* 444 */       if (!isRasterClipPositionInside(point3d2)) {
/*     */         return;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 450 */     point2d.x += this.xDstOffset;
/* 451 */     point2d.y += this.yDstOffset;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 456 */     if (this.type == 1 || this.type == 3) {
/* 457 */       float f = (float)(point3d2.z * 0.5D - 0.5D);
/*     */       
/* 459 */       if (this.texture != null) {
/*     */         
/* 461 */         paramCanvas3D.updateTextureForRaster(this.texture);
/*     */         
/* 463 */         paramCanvas3D.textureFill(this, point2d, f, paramFloat);
/*     */ 
/*     */         
/* 466 */         paramCanvas3D.restoreTextureBin();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 471 */     if (this.type == 2 || this.type == 3) {
/*     */       
/* 473 */       Point2i point2i = new Point2i(this.xSrcOffset, this.ySrcOffset);
/*     */       
/* 475 */       if (this.clipMode == 1) {
/* 476 */         clipImage(paramCanvas3D, paramRenderAtom, point2d, point2i);
/*     */       }
/*     */       
/* 479 */       computeObjCoord(paramCanvas3D, point2d, point3d1, transform3D);
/*     */       
/* 481 */       paramCanvas3D.executeRasterDepth(paramCanvas3D.ctx, (float)point3d1.x, (float)point3d1.y, (float)point3d1.z, point2i.x, point2i.y, this.width, this.height, this.depthComponent.width, this.depthComponent.height, this.depthComponent.type, ((DepthComponentIntRetained)this.depthComponent).depthData);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void clipImage(Canvas3D paramCanvas3D, RenderAtom paramRenderAtom, Point2d paramPoint2d, Point2i paramPoint2i) {
/* 508 */     if (paramPoint2d.x > 0.0D && paramPoint2d.y > 0.0D) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 518 */     if (paramPoint2d.x < 1.0D) {
/*     */       
/* 520 */       paramPoint2i.x = (int)-paramPoint2d.x + 1;
/* 521 */       paramPoint2d.x = 1.0D;
/*     */     } 
/*     */     
/* 524 */     if (paramPoint2d.y < 1.0D) {
/*     */       
/* 526 */       paramPoint2i.y = (int)-paramPoint2d.y + 1;
/* 527 */       paramPoint2d.y = 1.0D;
/*     */     } 
/*     */ 
/*     */     
/* 531 */     if (paramPoint2i.x < this.xSrcOffset)
/* 532 */       paramPoint2i.x = this.xSrcOffset; 
/* 533 */     if (paramPoint2i.y < this.ySrcOffset) {
/* 534 */       paramPoint2i.y = this.ySrcOffset;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 540 */   private boolean isRasterClipPositionInside(Point3d paramPoint3d) { return (paramPoint3d.x >= -1.0D && paramPoint3d.x <= 1.0D && paramPoint3d.y >= -1.0D && paramPoint3d.y <= 1.0D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeObjCoord(Canvas3D paramCanvas3D, Point2d paramPoint2d, Point3d paramPoint3d, Transform3D paramTransform3D) {
/* 551 */     paramCanvas3D.getPixelLocationInImagePlate(paramPoint2d.x, paramPoint2d.y, paramPoint3d.z, paramPoint3d);
/*     */ 
/*     */ 
/*     */     
/* 555 */     paramTransform3D.invert();
/* 556 */     paramTransform3D.transform(paramPoint3d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Point3d computeWinCoord(Canvas3D paramCanvas3D, RenderAtom paramRenderAtom, Point2d paramPoint2d, Point3d paramPoint3d, Transform3D paramTransform3D) {
/* 563 */     RenderMolecule renderMolecule = paramRenderAtom.renderMolecule;
/*     */     
/* 565 */     if (renderMolecule == null)
/*     */     {
/*     */       
/* 568 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 574 */     Transform3D transform3D = renderMolecule.localToVworld[renderMolecule.localToVworldIndex[0]];
/*     */ 
/*     */ 
/*     */     
/* 578 */     Point3d point3d = new Point3d();
/* 579 */     point3d.set(paramPoint3d);
/* 580 */     Point4d point4d = new Point4d();
/*     */ 
/*     */     
/* 583 */     transform3D.transform(point3d);
/* 584 */     paramCanvas3D.vworldToEc.transform(point3d);
/* 585 */     paramCanvas3D.projTrans.transform(point3d, point4d);
/*     */ 
/*     */     
/* 588 */     if (point4d.w <= 0.0D || point4d.z > point4d.w || -point4d.z > point4d.w)
/*     */     {
/*     */       
/* 591 */       return null;
/*     */     }
/* 593 */     double d = 1.0D / point4d.w;
/*     */     
/* 595 */     point3d.x = point4d.x * d;
/* 596 */     point3d.y = point4d.y * d;
/* 597 */     point3d.z = point4d.z * d;
/*     */ 
/*     */     
/* 600 */     paramCanvas3D.getLastVworldToImagePlate(paramTransform3D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 609 */     paramTransform3D.mul(transform3D);
/*     */ 
/*     */     
/* 612 */     paramTransform3D.transform(paramPoint3d);
/*     */ 
/*     */ 
/*     */     
/* 616 */     paramCanvas3D.getPixelLocationFromImagePlate(paramPoint3d, paramPoint2d);
/*     */     
/* 618 */     return point3d;
/*     */   }
/*     */ 
/*     */   
/* 622 */   int getClassType() { return 5; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void notifyImageComponentImageChanged(ImageComponentRetained paramImageComponentRetained, ImageComponentUpdateInfo paramImageComponentUpdateInfo) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 635 */   boolean intersect(PickShape paramPickShape, PickInfo paramPickInfo, int paramInt1, Point3d paramPoint3d, GeometryRetained paramGeometryRetained, int paramInt2) { return false; }
/*     */ 
/*     */ 
/*     */   
/* 639 */   boolean intersect(Bounds paramBounds) { return false; }
/*     */ 
/*     */ 
/*     */   
/* 643 */   boolean intersect(Point3d[] paramArrayOfPoint3d) { return false; }
/*     */ 
/*     */ 
/*     */   
/* 647 */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 652 */   boolean intersect(Transform3D paramTransform3D1, Transform3D paramTransform3D2, GeometryRetained paramGeometryRetained) { return false; }
/*     */ 
/*     */ 
/*     */   
/* 656 */   boolean intersect(Transform3D paramTransform3D, Bounds paramBounds) { return false; }
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {
/* 659 */     if (paramInt == 5)
/* 660 */       setFrequencyChangeMask(paramInt, 1); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\RasterRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */