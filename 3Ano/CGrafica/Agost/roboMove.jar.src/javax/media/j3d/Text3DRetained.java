/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Text3DRetained
/*     */   extends GeometryRetained
/*     */ {
/*  28 */   Font3D font3D = null;
/*  29 */   String string = null;
/*  30 */   Point3f position = new Point3f(0.0F, 0.0F, 0.0F);
/*  31 */   int alignment = 1; int path = 1;
/*  32 */   float charSpacing = 0.0F;
/*  33 */   int numChars = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int targetThreads = 8384;
/*     */ 
/*     */   
/*  40 */   Transform3D[] charTransforms = new Transform3D[0];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   GeometryArrayRetained[] geometryList = new GeometryArrayRetained[0];
/*  46 */   GlyphVector[] glyphVecs = new GlyphVector[0];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   Point3d lower = new Point3d();
/*  52 */   Point3d upper = new Point3d();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   ArrayList newGeometryAtomList = new ArrayList();
/*  59 */   ArrayList oldGeometryAtomList = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Transform3D vpcToEc;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Transform3D drawTransform;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void computeBoundingBox() {
/*  75 */     Point3d point3d1 = new Point3d();
/*  76 */     Point3d point3d2 = new Point3d();
/*  77 */     Vector3f vector3f = new Vector3f(this.position);
/*  78 */     byte b = 0; int i = 0;
/*  79 */     double d1 = 0.0D, d2 = 0.0D;
/*     */ 
/*     */ 
/*     */     
/*  83 */     point3d1.set(vector3f);
/*  84 */     point3d2.set(vector3f);
/*     */     
/*  86 */     if (this.numChars != 0) {
/*     */       
/*  88 */       if (this.path == 1 || this.path == 2) {
/*  89 */         b = 0;
/*  90 */         i = this.numChars + 1;
/*  91 */       } else if (this.path == 0 || this.path == 3) {
/*  92 */         b = 1;
/*  93 */         i = this.numChars;
/*     */         
/*  95 */         Rectangle2D rectangle2D = this.glyphVecs[0].getVisualBounds();
/*  96 */         point3d2.x += rectangle2D.getWidth();
/*  97 */         point3d2.y += rectangle2D.getHeight();
/*     */       } 
/*     */       
/* 100 */       for (byte b1 = 1; b1 < i; b1++, b++) {
/* 101 */         d1 = this.glyphVecs[b].getLogicalBounds().getWidth();
/* 102 */         Rectangle2D rectangle2D = this.glyphVecs[b].getVisualBounds();
/*     */         
/* 104 */         d1 += this.charSpacing;
/* 105 */         d2 = rectangle2D.getHeight();
/*     */         
/* 107 */         switch (this.path) {
/*     */           case 1:
/* 109 */             point3d2.x += d1;
/* 110 */             if (point3d2.y < d2 + vector3f.y) {
/* 111 */               point3d2.y = vector3f.y + d2;
/*     */             }
/*     */             break;
/*     */           case 0:
/* 115 */             point3d1.x -= d1;
/* 116 */             if (point3d2.y < d2 + vector3f.y) {
/* 117 */               point3d2.y = vector3f.y + d2;
/*     */             }
/*     */             break;
/*     */           case 2:
/* 121 */             point3d2.y += d2;
/* 122 */             if (point3d2.x < rectangle2D.getWidth() + vector3f.x) {
/* 123 */               point3d2.x = vector3f.x + rectangle2D.getWidth();
/*     */             }
/*     */             break;
/*     */           case 3:
/* 127 */             point3d1.y -= d2;
/* 128 */             if (point3d2.x < rectangle2D.getWidth() + vector3f.x) {
/* 129 */               point3d2.x = vector3f.x + rectangle2D.getWidth();
/*     */             }
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/* 136 */       if (this.alignment != 1) {
/* 137 */         double d3 = point3d2.x - point3d1.x;
/* 138 */         double d4 = point3d2.y - point3d1.y;
/*     */         
/* 140 */         if (this.alignment == 0) {
/* 141 */           d3 *= 0.5D;
/* 142 */           d4 *= 0.5D;
/*     */         } 
/* 144 */         switch (this.path) {
/*     */           case 1:
/* 146 */             point3d1.x -= d3;
/* 147 */             point3d2.x -= d3;
/*     */             break;
/*     */           case 0:
/* 150 */             point3d1.x += d3;
/* 151 */             point3d2.x += d3;
/*     */             break;
/*     */           case 2:
/* 154 */             point3d1.y -= d4;
/* 155 */             point3d2.y -= d4;
/*     */             break;
/*     */           case 3:
/* 158 */             point3d1.y += d4;
/* 159 */             point3d2.y += d4;
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/*     */     } 
/* 166 */     point3d1.z = 0.0D;
/* 167 */     if (this.font3D == null || this.font3D.fontExtrusion == null) {
/* 168 */       point3d2.z = point3d1.z;
/*     */     } else {
/* 170 */       point3d1.z += this.font3D.fontExtrusion.length;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void update() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   final Font3D getFont3D() { return this.font3D; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setFont3D(Font3D paramFont3D) {
/* 199 */     this.geomLock.getLock();
/* 200 */     this.font3D = paramFont3D;
/* 201 */     updateCharacterData();
/* 202 */     this.geomLock.unLock();
/* 203 */     sendDataChangedMessage();
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
/* 216 */   final String getString() { return this.string; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setString(String paramString) {
/* 229 */     this.geomLock.getLock();
/* 230 */     this.string = paramString;
/* 231 */     if (paramString == null) {
/* 232 */       this.numChars = 0;
/*     */     } else {
/* 234 */       this.numChars = paramString.length();
/*     */     } 
/* 236 */     updateCharacterData();
/* 237 */     this.geomLock.unLock();
/* 238 */     sendDataChangedMessage();
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
/* 256 */   final void getPosition(Point3f paramPoint3f) { paramPoint3f.set(this.position); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 274 */     this.geomLock.getLock();
/* 275 */     this.position.set(paramPoint3f);
/* 276 */     updateTransformData();
/* 277 */     this.geomLock.unLock();
/* 278 */     sendTransformChangedMessage();
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
/*     */ 
/*     */   
/* 305 */   final int getAlignment() { return this.alignment; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setAlignment(int paramInt) {
/* 332 */     this.geomLock.getLock();
/* 333 */     this.alignment = paramInt;
/* 334 */     updateTransformData();
/* 335 */     this.geomLock.unLock();
/* 336 */     sendTransformChangedMessage();
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
/* 360 */   final int getPath() { return this.path; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPath(int paramInt) {
/* 386 */     this.path = paramInt;
/* 387 */     updateTransformData();
/* 388 */     sendTransformChangedMessage();
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
/*     */   final void getBoundingBox(BoundingBox paramBoundingBox) {
/* 402 */     synchronized (this) {
/* 403 */       paramBoundingBox.setLower(this.lower);
/* 404 */       paramBoundingBox.setUpper(this.upper);
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
/* 421 */   final float getCharacterSpacing() { return this.charSpacing; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setCharacterSpacing(float paramFloat) {
/* 437 */     this.geomLock.getLock();
/* 438 */     this.charSpacing = paramFloat;
/* 439 */     updateTransformData();
/* 440 */     this.geomLock.unLock();
/* 441 */     sendTransformChangedMessage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void sendDataChangedMessage() {
/* 452 */     ArrayList arrayList1 = new ArrayList();
/* 453 */     ArrayList arrayList2 = new ArrayList();
/*     */     
/* 455 */     synchronized (this.liveStateLock) {
/* 456 */       if (this.source.isLive()) {
/* 457 */         synchronized (this.universeList) {
/* 458 */           int i = this.universeList.size();
/* 459 */           J3dMessage[] arrayOfJ3dMessage = new J3dMessage[i];
/* 460 */           for (byte b = 0; b < i; b++) {
/* 461 */             arrayOfJ3dMessage[b] = new J3dMessage();
/* 462 */             (arrayOfJ3dMessage[b]).type = 26;
/* 463 */             (arrayOfJ3dMessage[b]).threads = 8384;
/* 464 */             ArrayList arrayList3 = (ArrayList)this.userLists.get(b);
/* 465 */             this.newGeometryAtomList.clear();
/* 466 */             this.oldGeometryAtomList.clear();
/*     */             byte b1;
/* 468 */             for (b1 = 0; b1 < arrayList3.size(); b1++) {
/* 469 */               Shape3DRetained shape3DRetained = (Shape3DRetained)arrayList3.get(b1);
/* 470 */               if (shape3DRetained.boundsAutoCompute)
/*     */               {
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 476 */                 shape3DRetained.getCombineBounds((BoundingBox)shape3DRetained.bounds);
/*     */               }
/*     */               
/* 479 */               int k = shape3DRetained.geometryList.size();
/*     */               
/* 481 */               GeometryAtom geometryAtom1 = Shape3DRetained.getGeomAtom(shape3DRetained);
/* 482 */               GeometryAtom geometryAtom2 = new GeometryAtom();
/*     */               
/* 484 */               int m = 0; byte b2;
/* 485 */               for (b2 = 0; b2 < k; b2++) {
/* 486 */                 GeometryRetained geometryRetained1 = (GeometryRetained)shape3DRetained.geometryList.get(b2);
/*     */                 
/* 488 */                 if (geometryRetained1 != null) {
/* 489 */                   Text3DRetained text3DRetained = (Text3DRetained)geometryRetained1;
/* 490 */                   m += text3DRetained.numChars;
/*     */                 }
/*     */                 else {
/*     */                   
/* 494 */                   m++;
/*     */                 } 
/*     */               } 
/*     */               
/* 498 */               geometryAtom2.geometryArray = new GeometryRetained[m];
/* 499 */               geometryAtom2.lastLocalTransformArray = new Transform3D[m];
/*     */               
/* 501 */               m = 0;
/*     */               
/* 503 */               geometryAtom2.locale = shape3DRetained.locale;
/* 504 */               geometryAtom2.visible = shape3DRetained.visible;
/* 505 */               geometryAtom2.source = shape3DRetained;
/* 506 */               byte b3 = 0;
/* 507 */               GeometryRetained geometryRetained = null;
/* 508 */               for (; b3 < k; b3++) {
/* 509 */                 geometryRetained = (GeometryRetained)shape3DRetained.geometryList.get(b3);
/* 510 */                 if (geometryRetained != null) {
/* 511 */                   geometryAtom2.geoType = geometryRetained.geoType;
/* 512 */                   geometryAtom2.alphaEditable = shape3DRetained.isAlphaEditable(geometryRetained);
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 517 */               for (; b3 < k; b3++) {
/* 518 */                 geometryRetained = (GeometryRetained)shape3DRetained.geometryList.get(b3);
/* 519 */                 if (geometryRetained == null) {
/* 520 */                   geometryAtom2.geometryArray[b3] = null;
/*     */                 } else {
/*     */                   
/* 523 */                   Text3DRetained text3DRetained = (Text3DRetained)geometryRetained;
/*     */                   
/* 525 */                   for (b2 = 0; b2 < text3DRetained.numChars; b2++, m++) {
/* 526 */                     GeometryArrayRetained geometryArrayRetained = text3DRetained.geometryList[b2];
/* 527 */                     if (geometryArrayRetained != null) {
/* 528 */                       geometryAtom2.geometryArray[m] = geometryArrayRetained;
/* 529 */                       geometryAtom2.lastLocalTransformArray[m] = text3DRetained.charTransforms[b2];
/*     */                     }
/*     */                     else {
/*     */                       
/* 533 */                       geometryAtom2.geometryArray[m] = null;
/* 534 */                       geometryAtom2.lastLocalTransformArray[m] = null;
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 542 */               this.oldGeometryAtomList.add(geometryAtom1);
/* 543 */               this.newGeometryAtomList.add(geometryAtom2);
/* 544 */               Shape3DRetained.setGeomAtom(shape3DRetained, geometryAtom2);
/*     */             } 
/*     */             
/* 547 */             Object[] arrayOfObject1 = this.oldGeometryAtomList.toArray();
/* 548 */             Object[] arrayOfObject2 = this.newGeometryAtomList.toArray();
/* 549 */             ArrayList arrayList4 = getUniqueSource(arrayList3);
/* 550 */             int j = arrayList4.size();
/*     */ 
/*     */ 
/*     */             
/* 554 */             for (b1 = 0; b1 < j; b1++) {
/* 555 */               CachedTargets[] arrayOfCachedTargets = null;
/* 556 */               Shape3DRetained shape3DRetained = (Shape3DRetained)arrayList4.get(b1);
/* 557 */               int k = shape3DRetained.mirrorShape3D.size();
/*     */               
/* 559 */               TargetsInterface targetsInterface = ((GroupRetained)shape3DRetained.parent).getClosestTargetsInterface(0);
/*     */ 
/*     */ 
/*     */               
/* 563 */               if (targetsInterface != null) {
/*     */                 
/* 565 */                 arrayOfCachedTargets = new CachedTargets[k];
/*     */                 
/* 567 */                 for (byte b2 = 0; b2 < k; b2++) {
/* 568 */                   Shape3DRetained shape3DRetained1 = (Shape3DRetained)shape3DRetained.mirrorShape3D.get(b2);
/*     */                   
/* 570 */                   GeometryAtom geometryAtom = Shape3DRetained.getGeomAtom(shape3DRetained1);
/*     */                   byte b3;
/* 572 */                   for (b3 = 0; b3 < arrayOfObject2.length && 
/* 573 */                     geometryAtom != arrayOfObject2[b3]; b3++);
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 578 */                   if (b3 == arrayOfObject2.length) {
/* 579 */                     System.err.println("Text3DRetained : Problem !!! Can't find matching geomAtom");
/*     */                   }
/*     */                   
/* 582 */                   CachedTargets cachedTargets = targetsInterface.getCachedTargets(0, b2, -1);
/*     */                   
/* 584 */                   if (cachedTargets != null) {
/* 585 */                     arrayOfCachedTargets[b2] = new CachedTargets();
/* 586 */                     arrayOfCachedTargets[b2].copy(cachedTargets);
/* 587 */                     arrayOfCachedTargets[b2].replace((NnuId)arrayOfObject1[b3], (NnuId)arrayOfObject2[b3], 0);
/*     */                   }
/*     */                   else {
/*     */                     
/* 591 */                     arrayOfCachedTargets[b2] = null;
/*     */                   } 
/*     */                 } 
/*     */ 
/*     */                 
/* 596 */                 targetsInterface.resetCachedTargets(0, arrayOfCachedTargets, -1);
/*     */ 
/*     */                 
/* 599 */                 arrayList1.add(targetsInterface);
/* 600 */                 arrayList2.add(arrayOfCachedTargets);
/*     */               } 
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 606 */             (arrayOfJ3dMessage[b]).args[0] = arrayOfObject1;
/* 607 */             (arrayOfJ3dMessage[b]).args[1] = arrayOfObject2;
/* 608 */             (arrayOfJ3dMessage[b]).universe = (VirtualUniverse)this.universeList.get(b);
/*     */             
/* 610 */             if (arrayList1.size() > 0) {
/* 611 */               (arrayOfJ3dMessage[b]).args[2] = arrayList1.toArray();
/* 612 */               (arrayOfJ3dMessage[b]).args[3] = arrayList2.toArray();
/*     */             } 
/*     */             
/* 615 */             arrayList1.clear();
/* 616 */             arrayList2.clear();
/*     */           } 
/*     */           
/* 619 */           VirtualUniverse.mc.processMessage(arrayOfJ3dMessage);
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
/*     */   final void sendTransformChangedMessage() {
/* 631 */     ArrayList arrayList = new ArrayList();
/*     */ 
/*     */     
/* 634 */     synchronized (this.liveStateLock) {
/* 635 */       if (this.source.isLive()) {
/* 636 */         synchronized (this.universeList) {
/* 637 */           int i = this.universeList.size();
/* 638 */           J3dMessage[] arrayOfJ3dMessage = new J3dMessage[i];
/* 639 */           for (byte b = 0; b < i; b++) {
/* 640 */             arrayOfJ3dMessage[b] = new J3dMessage();
/* 641 */             (arrayOfJ3dMessage[b]).type = 25;
/* 642 */             (arrayOfJ3dMessage[b]).threads = 8384;
/* 643 */             ArrayList arrayList1 = (ArrayList)this.userLists.get(b);
/*     */             
/* 645 */             for (byte b1 = 0; b1 < arrayList1.size(); b1++) {
/* 646 */               Shape3DRetained shape3DRetained = (Shape3DRetained)arrayList1.get(b1);
/*     */               
/*     */               byte b2;
/* 649 */               for (b2 = 0; b2 < shape3DRetained.geometryList.size(); b2++) {
/* 650 */                 GeometryRetained geometryRetained = (GeometryRetained)shape3DRetained.geometryList.get(b2);
/* 651 */                 if (geometryRetained == this) {
/*     */                   break;
/*     */                 }
/*     */               } 
/*     */               
/* 656 */               if (b2 < shape3DRetained.geometryList.size()) {
/* 657 */                 arrayList.add(Shape3DRetained.getGeomAtom(shape3DRetained));
/*     */               }
/*     */             } 
/* 660 */             (arrayOfJ3dMessage[b]).args[0] = arrayList.toArray();
/* 661 */             (arrayOfJ3dMessage[b]).args[1] = this.charTransforms;
/* 662 */             (arrayOfJ3dMessage[b]).universe = (VirtualUniverse)this.universeList.get(b);
/*     */           } 
/* 664 */           VirtualUniverse.mc.processMessage(arrayOfJ3dMessage);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void updateCharacterData() {
/* 675 */     char[] arrayOfChar = new char[1];
/*     */     
/* 677 */     if (this.geometryList.length != this.numChars) {
/* 678 */       this.geometryList = new GeometryArrayRetained[this.numChars];
/* 679 */       this.glyphVecs = new GlyphVector[this.numChars];
/*     */     } 
/*     */     
/* 682 */     if (this.font3D != null) {
/* 683 */       for (byte b = 0; b < this.numChars; b++) {
/* 684 */         arrayOfChar[0] = this.string.charAt(b);
/* 685 */         this.glyphVecs[b] = this.font3D.font.createGlyphVector(this.font3D.frc, arrayOfChar);
/* 686 */         this.geometryList[b] = this.font3D.triangulateGlyphs(this.glyphVecs[b], arrayOfChar[0]);
/*     */       } 
/*     */     }
/*     */     
/* 690 */     updateTransformData();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void updateTransformData() {
/* 701 */     byte b2 = 0; int i = 0;
/* 702 */     double d1 = 0.0D, d2 = 0.0D;
/* 703 */     Vector3f vector3f = new Vector3f(this.position);
/*     */ 
/*     */ 
/*     */     
/* 707 */     this.lower.set(vector3f);
/* 708 */     this.upper.set(vector3f);
/*     */     
/* 710 */     this.charTransforms = new Transform3D[this.numChars]; byte b1;
/* 711 */     for (b1 = 0; b1 < this.numChars; b1++) {
/* 712 */       this.charTransforms[b1] = new Transform3D();
/*     */     }
/*     */     
/* 715 */     if (this.numChars != 0) {
/* 716 */       this.charTransforms[0].set(vector3f);
/*     */ 
/*     */       
/* 719 */       if (this.path == 1 || this.path == 2) {
/* 720 */         b2 = 0;
/* 721 */         i = this.numChars + 1;
/* 722 */       } else if (this.path == 0 || this.path == 3) {
/* 723 */         b2 = 1;
/* 724 */         i = this.numChars;
/*     */         
/* 726 */         Rectangle2D rectangle2D = this.glyphVecs[0].getVisualBounds();
/* 727 */         this.upper.x += rectangle2D.getWidth();
/* 728 */         this.upper.y += rectangle2D.getHeight();
/*     */       } 
/*     */       
/* 731 */       for (b1 = 1; b1 < i; b1++, b2++) {
/* 732 */         d1 = this.glyphVecs[b2].getLogicalBounds().getWidth();
/* 733 */         Rectangle2D rectangle2D = this.glyphVecs[b2].getVisualBounds();
/*     */         
/* 735 */         d1 += this.charSpacing;
/* 736 */         d2 = rectangle2D.getHeight();
/*     */         
/* 738 */         switch (this.path) {
/*     */           case 1:
/* 740 */             vector3f.x = (float)(vector3f.x + d1);
/* 741 */             this.upper.x += d1;
/* 742 */             if (this.upper.y < d2 + vector3f.y) {
/* 743 */               this.upper.y = vector3f.y + d2;
/*     */             }
/*     */             break;
/*     */           case 0:
/* 747 */             vector3f.x = (float)(vector3f.x - d1);
/* 748 */             this.lower.x -= d1;
/* 749 */             if (this.upper.y < d2 + vector3f.y) {
/* 750 */               this.upper.y = vector3f.y + d2;
/*     */             }
/*     */             break;
/*     */           case 2:
/* 754 */             vector3f.y = (float)(vector3f.y + d2);
/* 755 */             this.upper.y += d2;
/* 756 */             if (this.upper.x < rectangle2D.getWidth() + vector3f.x) {
/* 757 */               this.upper.x = vector3f.x + rectangle2D.getWidth();
/*     */             }
/*     */             break;
/*     */           case 3:
/* 761 */             vector3f.y = (float)(vector3f.y - d2);
/* 762 */             this.lower.y -= d2;
/* 763 */             if (this.upper.x < rectangle2D.getWidth() + vector3f.x) {
/* 764 */               this.upper.x = vector3f.x + rectangle2D.getWidth();
/*     */             }
/*     */             break;
/*     */         } 
/* 768 */         if (b1 < this.numChars) {
/* 769 */           this.charTransforms[b1].set(vector3f);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 774 */       if (this.alignment != 1) {
/* 775 */         double d3 = this.upper.x - this.lower.x;
/* 776 */         double d4 = this.upper.y - this.lower.y;
/*     */         
/* 778 */         if (this.alignment == 0) {
/* 779 */           d3 *= 0.5D;
/* 780 */           d4 *= 0.5D;
/*     */         } 
/* 782 */         switch (this.path) {
/*     */           case 1:
/* 784 */             for (b1 = 0; b1 < this.numChars; b1++) {
/* 785 */               (this.charTransforms[b1]).mat[3] = (this.charTransforms[b1]).mat[3] - d3;
/*     */             }
/* 787 */             this.lower.x -= d3;
/* 788 */             this.upper.x -= d3;
/*     */             break;
/*     */           case 0:
/* 791 */             for (b1 = 0; b1 < this.numChars; b1++) {
/* 792 */               (this.charTransforms[b1]).mat[3] = (this.charTransforms[b1]).mat[3] + d3;
/*     */             }
/* 794 */             this.lower.x += d3;
/* 795 */             this.upper.x += d3;
/*     */             break;
/*     */           
/*     */           case 2:
/* 799 */             for (b1 = 0; b1 < this.numChars; b1++) {
/* 800 */               (this.charTransforms[b1]).mat[7] = (this.charTransforms[b1]).mat[7] - d4;
/*     */             }
/* 802 */             this.lower.y -= d4;
/* 803 */             this.upper.y -= d4;
/*     */             break;
/*     */           case 3:
/* 806 */             for (b1 = 0; b1 < this.numChars; b1++) {
/* 807 */               (this.charTransforms[b1]).mat[7] = (this.charTransforms[b1]).mat[7] + d4;
/*     */             }
/* 809 */             this.lower.y += d4;
/* 810 */             this.upper.y += d4;
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/*     */     } 
/* 817 */     this.lower.z = 0.0D;
/* 818 */     if (this.font3D == null || this.font3D.fontExtrusion == null) {
/* 819 */       this.upper.z = this.lower.z;
/*     */     } else {
/* 821 */       this.lower.z += this.font3D.fontExtrusion.length;
/*     */     } 
/*     */ 
/*     */     
/* 825 */     getBoundingBox(this.geoBounds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLive(boolean paramBoolean, int paramInt) {
/* 836 */     updateCharacterData();
/* 837 */     doSetLive(paramBoolean, paramInt);
/* 838 */     markAsLive();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(PickShape paramPickShape, PickInfo paramPickInfo, int paramInt1, Point3d paramPoint3d, GeometryRetained paramGeometryRetained, int paramInt2) {
/* 844 */     Transform3D transform3D = new Transform3D();
/* 845 */     GeometryArrayRetained geometryArrayRetained = null;
/* 846 */     byte b1 = -1;
/*     */     
/* 848 */     double d1 = Double.MAX_VALUE;
/* 849 */     double d2 = 0.0D;
/* 850 */     Point3d point3d = new Point3d();
/*     */     
/* 852 */     for (byte b2 = 0; b2 < this.numChars; b2++) {
/* 853 */       geometryArrayRetained = this.geometryList[b2];
/* 854 */       if (geometryArrayRetained != null) {
/* 855 */         transform3D.invert(this.charTransforms[b2]);
/* 856 */         PickShape pickShape = paramPickShape.transform(transform3D);
/* 857 */         if (geometryArrayRetained.intersect(pickShape, paramPickInfo, paramInt1, paramPoint3d, paramGeometryRetained, paramInt2)) {
/* 858 */           if (paramInt1 == 0) {
/* 859 */             return true;
/*     */           }
/* 861 */           d2 = pickShape.distance(paramPoint3d);
/* 862 */           if (d2 < d1) {
/* 863 */             b1 = b2;
/* 864 */             d1 = d2;
/* 865 */             point3d.set(paramPoint3d);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 871 */     if (b1 >= 0) {
/*     */ 
/*     */ 
/*     */       
/* 875 */       paramPoint3d.set(point3d);
/* 876 */       this.charTransforms[b1].transform(paramPoint3d);
/* 877 */       return true;
/*     */     } 
/* 879 */     return false;
/*     */   }
/*     */   
/*     */   boolean intersect(Point3d[] paramArrayOfPoint3d) {
/* 883 */     Transform3D transform3D = new Transform3D();
/*     */     
/* 885 */     boolean bool = false;
/* 886 */     Point3d[] arrayOfPoint3d = new Point3d[paramArrayOfPoint3d.length]; int i;
/* 887 */     for (i = paramArrayOfPoint3d.length - 1; i >= 0; i--) {
/* 888 */       arrayOfPoint3d[i] = new Point3d();
/*     */     }
/*     */     
/* 891 */     for (i = this.numChars - 1; i >= 0; i--) {
/* 892 */       GeometryArrayRetained geometryArrayRetained = this.geometryList[i];
/* 893 */       if (geometryArrayRetained != null) {
/* 894 */         transform3D.invert(this.charTransforms[i]);
/* 895 */         for (int j = paramArrayOfPoint3d.length - 1; j >= 0; j--) {
/* 896 */           transform3D.transform(paramArrayOfPoint3d[j], arrayOfPoint3d[j]);
/*     */         }
/* 898 */         if (geometryArrayRetained.intersect(arrayOfPoint3d)) {
/* 899 */           bool = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 904 */     return bool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(Transform3D paramTransform3D, GeometryRetained paramGeometryRetained) {
/* 911 */     for (int i = this.numChars - 1; i >= 0; i--) {
/* 912 */       GeometryArrayRetained geometryArrayRetained = this.geometryList[i];
/* 913 */       if (geometryArrayRetained != null && geometryArrayRetained.intersect(paramTransform3D, paramGeometryRetained)) {
/* 914 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 918 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean intersect(Bounds paramBounds) {
/* 924 */     for (int i = this.numChars - 1; i >= 0; i--) {
/* 925 */       GeometryArrayRetained geometryArrayRetained = this.geometryList[i];
/* 926 */       if (geometryArrayRetained != null && geometryArrayRetained.intersect(paramBounds)) {
/* 927 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 931 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   void setModelViewMatrix(Transform3D paramTransform3D1, Transform3D paramTransform3D2) {
/* 936 */     this.vpcToEc = paramTransform3D1;
/* 937 */     this.drawTransform = paramTransform3D2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void execute(Canvas3D paramCanvas3D, RenderAtom paramRenderAtom, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, int paramInt, boolean paramBoolean3) {
/* 946 */     Transform3D transform3D = new Transform3D();
/*     */     
/* 948 */     for (byte b = 0; b < this.geometryList.length; b++) {
/* 949 */       transform3D.set(this.drawTransform);
/* 950 */       transform3D.mul(this.charTransforms[b]);
/* 951 */       paramCanvas3D.setModelViewMatrix(paramCanvas3D.ctx, this.vpcToEc.mat, transform3D);
/* 952 */       this.geometryList[b].execute(paramCanvas3D, paramRenderAtom, paramBoolean1, paramBoolean2, paramFloat, paramInt, paramBoolean3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 958 */   int getClassType() { return 6; }
/*     */ 
/*     */ 
/*     */   
/*     */   ArrayList getUniqueSource(ArrayList paramArrayList) {
/* 963 */     ArrayList arrayList = new ArrayList();
/* 964 */     int i = paramArrayList.size();
/*     */ 
/*     */ 
/*     */     
/* 968 */     for (byte b = 0; b < i; b++) {
/* 969 */       NodeRetained nodeRetained = ((Shape3DRetained)paramArrayList.get(b)).sourceNode;
/* 970 */       int j = arrayList.indexOf(nodeRetained);
/* 971 */       if (j == -1) {
/* 972 */         arrayList.add(nodeRetained);
/*     */       }
/*     */     } 
/* 975 */     return arrayList;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\Text3DRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */