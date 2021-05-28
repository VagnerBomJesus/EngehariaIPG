/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RenderingAttributesRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*     */   static final int DEPTH_ENABLE = 1;
/*     */   static final int DEPTH_WRITE_ENABLE = 2;
/*     */   static final int ALPHA_TEST_VALUE = 4;
/*     */   static final int ALPHA_TEST_FUNC = 8;
/*     */   static final int VISIBLE = 16;
/*     */   static final int IGNORE_VCOLOR = 32;
/*     */   static final int RASTER_OP_ENABLE = 64;
/*     */   static final int RASTER_OP_VALUE = 128;
/*     */   static final int DEPTH_TEST_FUNC = 256;
/*     */   static final int STENCIL_ENABLE = 512;
/*     */   static final int STENCIL_OP_VALUES = 1024;
/*     */   static final int STENCIL_FUNC = 2048;
/*     */   static final int STENCIL_WRITE_MASK = 4096;
/*     */   boolean depthBufferEnable = true;
/*     */   boolean depthBufferWriteEnable = true;
/*  55 */   float alphaTestValue = 0.0F;
/*     */   
/*  57 */   int alphaTestFunction = 0;
/*     */   
/*  59 */   int depthTestFunction = 5;
/*     */   
/*     */   boolean visible = true;
/*     */   
/*     */   boolean ignoreVertexColors = false;
/*     */   
/*     */   boolean rasterOpEnable = false;
/*     */   
/*  67 */   int rasterOp = 3;
/*     */   
/*     */   boolean stencilEnable = false;
/*     */   
/*  71 */   int stencilFailOp = 1;
/*  72 */   int stencilZFailOp = 1;
/*  73 */   int stencilZPassOp = 1;
/*  74 */   int stencilFunction = 0;
/*  75 */   int stencilReferenceValue = 0;
/*  76 */   int stencilCompareMask = -1;
/*  77 */   int stencilWriteMask = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final int LESS = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final int LEQUAL = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   final void initVisible(boolean paramBoolean) { this.visible = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setVisible(boolean paramBoolean) {
/* 110 */     initVisible(paramBoolean);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     sendMessage(16, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
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
/* 129 */   final boolean getVisible() { return this.visible; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   final void initIgnoreVertexColors(boolean paramBoolean) { this.ignoreVertexColors = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setIgnoreVertexColors(boolean paramBoolean) {
/* 149 */     initIgnoreVertexColors(paramBoolean);
/* 150 */     sendMessage(32, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   final boolean getIgnoreVertexColors() { return this.ignoreVertexColors; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   final void initDepthBufferEnable(boolean paramBoolean) { this.depthBufferEnable = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setDepthBufferEnable(boolean paramBoolean) {
/* 179 */     initDepthBufferEnable(paramBoolean);
/* 180 */     sendMessage(1, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   final boolean getDepthBufferEnable() { return this.depthBufferEnable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   final void initDepthBufferWriteEnable(boolean paramBoolean) { this.depthBufferWriteEnable = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setDepthBufferWriteEnable(boolean paramBoolean) {
/* 215 */     initDepthBufferWriteEnable(paramBoolean);
/* 216 */     sendMessage(2, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 225 */   final boolean getDepthBufferWriteEnable() { return this.depthBufferWriteEnable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   final void initAlphaTestValue(float paramFloat) { this.alphaTestValue = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setAlphaTestValue(float paramFloat) {
/* 244 */     initAlphaTestValue(paramFloat);
/* 245 */     sendMessage(4, new Float(paramFloat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   final float getAlphaTestValue() { return this.alphaTestValue; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 267 */   final void initAlphaTestFunction(int paramInt) { this.alphaTestFunction = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setAlphaTestFunction(int paramInt) {
/* 284 */     initAlphaTestFunction(paramInt);
/* 285 */     sendMessage(8, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 293 */   final int getAlphaTestFunction() { return this.alphaTestFunction; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 307 */   final void initDepthTestFunction(int paramInt) { this.depthTestFunction = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setDepthTestFunction(int paramInt) {
/* 321 */     initDepthTestFunction(paramInt);
/* 322 */     sendMessage(256, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 332 */   final int getDepthTestFunction() { return this.depthTestFunction; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 339 */   final void initRasterOpEnable(boolean paramBoolean) { this.rasterOpEnable = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setRasterOpEnable(boolean paramBoolean) {
/* 346 */     initRasterOpEnable(paramBoolean);
/* 347 */     sendMessage(64, new Boolean(paramBoolean));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 354 */   final boolean getRasterOpEnable() { return this.rasterOpEnable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 361 */   final void initRasterOp(int paramInt) { this.rasterOp = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setRasterOp(int paramInt) {
/* 368 */     initRasterOp(paramInt);
/* 369 */     sendMessage(128, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 376 */   final int getRasterOp() { return this.rasterOp; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   final void initStencilEnable(boolean paramBoolean) { this.stencilEnable = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setStencilEnable(boolean paramBoolean) {
/* 392 */     initStencilEnable(paramBoolean);
/* 393 */     sendMessage(512, new Boolean(paramBoolean));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 400 */   final boolean getStencilEnable() { return this.stencilEnable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void initStencilOp(int paramInt1, int paramInt2, int paramInt3) {
/* 407 */     this.stencilFailOp = paramInt1;
/* 408 */     this.stencilZFailOp = paramInt2;
/* 409 */     this.stencilZPassOp = paramInt3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setStencilOp(int paramInt1, int paramInt2, int paramInt3) {
/* 416 */     initStencilOp(paramInt1, paramInt2, paramInt3);
/*     */     
/* 418 */     ArrayList arrayList = new ArrayList(3);
/* 419 */     arrayList.add(new Integer(paramInt1));
/* 420 */     arrayList.add(new Integer(paramInt2));
/* 421 */     arrayList.add(new Integer(paramInt3));
/* 422 */     sendMessage(1024, arrayList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void getStencilOp(int[] paramArrayOfInt) {
/* 429 */     paramArrayOfInt[0] = this.stencilFailOp;
/* 430 */     paramArrayOfInt[1] = this.stencilZFailOp;
/* 431 */     paramArrayOfInt[2] = this.stencilZPassOp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void initStencilFunction(int paramInt1, int paramInt2, int paramInt3) {
/* 439 */     this.stencilFunction = paramInt1;
/* 440 */     this.stencilReferenceValue = paramInt2;
/* 441 */     this.stencilCompareMask = paramInt3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setStencilFunction(int paramInt1, int paramInt2, int paramInt3) {
/* 448 */     initStencilOp(paramInt1, paramInt2, paramInt3);
/*     */     
/* 450 */     ArrayList arrayList = new ArrayList(3);
/* 451 */     arrayList.add(new Integer(paramInt1));
/* 452 */     arrayList.add(new Integer(paramInt2));
/* 453 */     arrayList.add(new Integer(paramInt3));
/* 454 */     sendMessage(2048, arrayList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void getStencilFunction(int[] paramArrayOfInt) {
/* 461 */     paramArrayOfInt[0] = this.stencilFunction;
/* 462 */     paramArrayOfInt[1] = this.stencilReferenceValue;
/* 463 */     paramArrayOfInt[2] = this.stencilCompareMask;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 471 */   final void initStencilWriteMask(int paramInt) { this.stencilWriteMask = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setStencilWriteMask(int paramInt) {
/* 478 */     initStencilWriteMask(paramInt);
/* 479 */     sendMessage(4096, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 486 */   final int getStencilWriteMask() { return this.stencilWriteMask; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 500 */   void updateNative(Canvas3D paramCanvas3D, boolean paramBoolean1, boolean paramBoolean2) { Pipeline.getPipeline().updateRenderingAttributes(paramCanvas3D.ctx, paramBoolean1, paramBoolean2, this.depthBufferEnable, this.depthBufferWriteEnable, this.depthTestFunction, this.alphaTestValue, this.alphaTestFunction, this.ignoreVertexColors, this.rasterOpEnable, this.rasterOp, paramCanvas3D.userStencilAvailable, this.stencilEnable, this.stencilFailOp, this.stencilZFailOp, this.stencilZPassOp, this.stencilFunction, this.stencilReferenceValue, this.stencilCompareMask, this.stencilWriteMask); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createMirrorObject() {
/* 515 */     if (this.mirror == null) {
/*     */ 
/*     */       
/* 518 */       if (isStatic()) {
/* 519 */         this.mirror = this;
/*     */       } else {
/*     */         
/* 522 */         RenderingAttributesRetained renderingAttributesRetained = new RenderingAttributesRetained();
/*     */         
/* 524 */         renderingAttributesRetained.set(this);
/* 525 */         renderingAttributesRetained.source = this.source;
/* 526 */         this.mirror = renderingAttributesRetained;
/*     */       } 
/*     */     } else {
/* 529 */       ((RenderingAttributesRetained)this.mirror).set(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 538 */   void initMirrorObject() { ((RenderingAttributesRetained)this.mirror).set(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 546 */     RenderingAttributesRetained renderingAttributesRetained = (RenderingAttributesRetained)this.mirror;
/*     */     
/* 548 */     if ((paramInt & true) != 0) {
/* 549 */       renderingAttributesRetained.depthBufferEnable = ((Boolean)paramObject).booleanValue();
/*     */     }
/* 551 */     else if ((paramInt & 0x2) != 0) {
/* 552 */       renderingAttributesRetained.depthBufferWriteEnable = ((Boolean)paramObject).booleanValue();
/*     */     }
/* 554 */     else if ((paramInt & 0x100) != 0) {
/* 555 */       renderingAttributesRetained.depthTestFunction = ((Integer)paramObject).intValue();
/*     */     }
/* 557 */     else if ((paramInt & 0x4) != 0) {
/* 558 */       renderingAttributesRetained.alphaTestValue = ((Float)paramObject).floatValue();
/*     */     }
/* 560 */     else if ((paramInt & 0x8) != 0) {
/* 561 */       renderingAttributesRetained.alphaTestFunction = ((Integer)paramObject).intValue();
/*     */     }
/* 563 */     else if ((paramInt & 0x10) != 0) {
/* 564 */       renderingAttributesRetained.visible = ((Boolean)paramObject).booleanValue();
/*     */     }
/* 566 */     else if ((paramInt & 0x20) != 0) {
/* 567 */       renderingAttributesRetained.ignoreVertexColors = ((Boolean)paramObject).booleanValue();
/*     */     }
/* 569 */     else if ((paramInt & 0x40) != 0) {
/* 570 */       renderingAttributesRetained.rasterOpEnable = ((Boolean)paramObject).booleanValue();
/*     */     }
/* 572 */     else if ((paramInt & 0x80) != 0) {
/* 573 */       renderingAttributesRetained.rasterOp = ((Integer)paramObject).intValue();
/*     */     }
/* 575 */     else if ((paramInt & 0x200) != 0) {
/* 576 */       renderingAttributesRetained.stencilEnable = ((Boolean)paramObject).booleanValue();
/*     */     }
/* 578 */     else if ((paramInt & 0x400) != 0) {
/* 579 */       ArrayList arrayList = (ArrayList)paramObject;
/* 580 */       renderingAttributesRetained.stencilFailOp = ((Integer)arrayList.get(0)).intValue();
/* 581 */       renderingAttributesRetained.stencilZFailOp = ((Integer)arrayList.get(1)).intValue();
/* 582 */       renderingAttributesRetained.stencilZPassOp = ((Integer)arrayList.get(2)).intValue();
/*     */     }
/* 584 */     else if ((paramInt & 0x800) != 0) {
/* 585 */       ArrayList arrayList = (ArrayList)paramObject;
/* 586 */       renderingAttributesRetained.stencilFunction = ((Integer)arrayList.get(0)).intValue();
/* 587 */       renderingAttributesRetained.stencilReferenceValue = ((Integer)arrayList.get(1)).intValue();
/* 588 */       renderingAttributesRetained.stencilCompareMask = ((Integer)arrayList.get(2)).intValue();
/*     */     }
/* 590 */     else if ((paramInt & 0x1000) != 0) {
/* 591 */       renderingAttributesRetained.stencilWriteMask = ((Integer)paramObject).intValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 596 */   boolean equivalent(RenderingAttributesRetained paramRenderingAttributesRetained) { return (this == paramRenderingAttributesRetained || (paramRenderingAttributesRetained != null && paramRenderingAttributesRetained.depthBufferEnable == this.depthBufferEnable && paramRenderingAttributesRetained.depthBufferWriteEnable == this.depthBufferWriteEnable && paramRenderingAttributesRetained.alphaTestValue == this.alphaTestValue && paramRenderingAttributesRetained.alphaTestFunction == this.alphaTestFunction && paramRenderingAttributesRetained.visible == this.visible && paramRenderingAttributesRetained.ignoreVertexColors == this.ignoreVertexColors && paramRenderingAttributesRetained.rasterOpEnable == this.rasterOpEnable && paramRenderingAttributesRetained.rasterOp == this.rasterOp && paramRenderingAttributesRetained.depthTestFunction == this.depthTestFunction && paramRenderingAttributesRetained.stencilEnable == this.stencilEnable && paramRenderingAttributesRetained.stencilFailOp == this.stencilFailOp && paramRenderingAttributesRetained.stencilZFailOp == this.stencilZFailOp && paramRenderingAttributesRetained.stencilZPassOp == this.stencilZPassOp && paramRenderingAttributesRetained.stencilFunction == this.stencilFunction && paramRenderingAttributesRetained.stencilReferenceValue == this.stencilReferenceValue && paramRenderingAttributesRetained.stencilCompareMask == this.stencilCompareMask && paramRenderingAttributesRetained.stencilWriteMask == this.stencilWriteMask)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void set(RenderingAttributesRetained paramRenderingAttributesRetained) {
/* 618 */     set(paramRenderingAttributesRetained);
/* 619 */     this.depthBufferEnable = paramRenderingAttributesRetained.depthBufferEnable;
/* 620 */     this.depthBufferWriteEnable = paramRenderingAttributesRetained.depthBufferWriteEnable;
/* 621 */     this.alphaTestValue = paramRenderingAttributesRetained.alphaTestValue;
/* 622 */     this.alphaTestFunction = paramRenderingAttributesRetained.alphaTestFunction;
/* 623 */     this.depthTestFunction = paramRenderingAttributesRetained.depthTestFunction;
/* 624 */     this.visible = paramRenderingAttributesRetained.visible;
/* 625 */     this.ignoreVertexColors = paramRenderingAttributesRetained.ignoreVertexColors;
/* 626 */     this.rasterOpEnable = paramRenderingAttributesRetained.rasterOpEnable;
/* 627 */     this.rasterOp = paramRenderingAttributesRetained.rasterOp;
/* 628 */     this.stencilEnable = paramRenderingAttributesRetained.stencilEnable;
/* 629 */     this.stencilFailOp = paramRenderingAttributesRetained.stencilFailOp;
/* 630 */     this.stencilZFailOp = paramRenderingAttributesRetained.stencilZFailOp;
/* 631 */     this.stencilZPassOp = paramRenderingAttributesRetained.stencilZPassOp;
/* 632 */     this.stencilFunction = paramRenderingAttributesRetained.stencilFunction;
/* 633 */     this.stencilReferenceValue = paramRenderingAttributesRetained.stencilReferenceValue;
/* 634 */     this.stencilCompareMask = paramRenderingAttributesRetained.stencilCompareMask;
/* 635 */     this.stencilWriteMask = paramRenderingAttributesRetained.stencilWriteMask;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 641 */     ArrayList arrayList1 = new ArrayList();
/* 642 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*     */ 
/*     */ 
/*     */     
/* 646 */     J3dMessage j3dMessage = new J3dMessage();
/* 647 */     j3dMessage.threads = 1024;
/* 648 */     j3dMessage.type = 10;
/* 649 */     j3dMessage.universe = null;
/* 650 */     j3dMessage.args[0] = this;
/* 651 */     j3dMessage.args[1] = new Integer(paramInt);
/* 652 */     j3dMessage.args[2] = paramObject;
/*     */     
/* 654 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/* 655 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */ 
/*     */     
/* 658 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 659 */       j3dMessage = new J3dMessage();
/* 660 */       j3dMessage.threads = 128;
/* 661 */       if (paramInt == 16)
/* 662 */         j3dMessage.threads |= 0x40; 
/* 663 */       j3dMessage.type = 10;
/*     */       
/* 665 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 666 */       j3dMessage.args[0] = this;
/* 667 */       j3dMessage.args[1] = new Integer(paramInt);
/* 668 */       j3dMessage.args[2] = paramObject;
/*     */       
/* 670 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 671 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 672 */       arrayList.toArray(arrayOfGeometryAtom);
/* 673 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*     */       
/* 675 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {
/* 682 */     char c = Character.MIN_VALUE;
/*     */     
/* 684 */     if (paramInt == 1)
/* 685 */       c = '\004'; 
/* 686 */     if (paramInt == 3)
/* 687 */       c = '\b'; 
/* 688 */     if (paramInt == 6)
/* 689 */       c = '\020'; 
/* 690 */     if (paramInt == 10)
/* 691 */       c = ' '; 
/* 692 */     if (paramInt == 8)
/* 693 */       c = '@'; 
/* 694 */     if (paramInt == 11)
/* 695 */       c = '\002'; 
/* 696 */     if (paramInt == 13) {
/* 697 */       c = 'Ā';
/*     */     }
/* 699 */     if (paramInt == 15) {
/* 700 */       c = 'Ā';
/*     */     }
/* 702 */     if (paramInt == 13) {
/* 703 */       c = 'Ḁ';
/*     */     }
/*     */     
/* 706 */     if (c != '\000')
/* 707 */       setFrequencyChangeMask(paramInt, c); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\RenderingAttributesRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */