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
/*     */ class PointAttributesRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*     */   static final int POINT_SIZE_CHANGED = 1;
/*     */   static final int POINT_AA_CHANGED = 2;
/*  28 */   float pointSize = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean pointAntialiasing = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   final void initPointSize(float paramFloat) { this.pointSize = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPointSize(float paramFloat) {
/*  48 */     initPointSize(paramFloat);
/*  49 */     sendMessage(1, new Float(paramFloat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   final float getPointSize() { return this.pointSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   final void initPointAntialiasingEnable(boolean paramBoolean) { this.pointAntialiasing = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPointAntialiasingEnable(boolean paramBoolean) {
/*  76 */     initPointAntialiasingEnable(this.pointAntialiasing);
/*  77 */     sendMessage(2, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   final boolean getPointAntialiasingEnable() { return this.pointAntialiasing; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createMirrorObject() {
/*  95 */     if (this.mirror == null) {
/*     */ 
/*     */       
/*  98 */       if (isStatic()) {
/*  99 */         this.mirror = this;
/*     */       } else {
/* 101 */         PointAttributesRetained pointAttributesRetained = new PointAttributesRetained();
/*     */         
/* 103 */         pointAttributesRetained.set(this);
/* 104 */         pointAttributesRetained.source = this.source;
/* 105 */         this.mirror = pointAttributesRetained;
/*     */       } 
/*     */     } else {
/* 108 */       ((PointAttributesRetained)this.mirror).set(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   void updateNative(Context paramContext) { Pipeline.getPipeline().updatePointAttributes(paramContext, this.pointSize, this.pointAntialiasing); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   void initMirrorObject() { ((PointAttributesRetained)this.mirror).set(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 135 */     PointAttributesRetained pointAttributesRetained = (PointAttributesRetained)this.mirror;
/*     */     
/* 137 */     if ((paramInt & true) != 0) {
/* 138 */       pointAttributesRetained.pointSize = ((Float)paramObject).floatValue();
/*     */     }
/* 140 */     else if ((paramInt & 0x2) != 0) {
/* 141 */       pointAttributesRetained.pointAntialiasing = ((Boolean)paramObject).booleanValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 146 */   boolean equivalent(PointAttributesRetained paramPointAttributesRetained) { return (paramPointAttributesRetained != null && paramPointAttributesRetained.pointSize == this.pointSize && paramPointAttributesRetained.pointAntialiasing == this.pointAntialiasing); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void set(PointAttributesRetained paramPointAttributesRetained) {
/* 153 */     set(paramPointAttributesRetained);
/* 154 */     this.pointSize = paramPointAttributesRetained.pointSize;
/* 155 */     this.pointAntialiasing = paramPointAttributesRetained.pointAntialiasing;
/*     */   }
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 160 */     ArrayList arrayList1 = new ArrayList();
/* 161 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*     */ 
/*     */ 
/*     */     
/* 165 */     J3dMessage j3dMessage = new J3dMessage();
/* 166 */     j3dMessage.threads = 1024;
/* 167 */     j3dMessage.type = 8;
/* 168 */     j3dMessage.universe = null;
/* 169 */     j3dMessage.args[0] = this;
/* 170 */     j3dMessage.args[1] = new Integer(paramInt);
/* 171 */     j3dMessage.args[2] = paramObject;
/* 172 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/* 173 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */ 
/*     */ 
/*     */     
/* 177 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 178 */       j3dMessage = new J3dMessage();
/* 179 */       j3dMessage.threads = 128;
/* 180 */       j3dMessage.type = 8;
/*     */       
/* 182 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 183 */       j3dMessage.args[0] = this;
/* 184 */       j3dMessage.args[1] = new Integer(paramInt);
/* 185 */       j3dMessage.args[2] = paramObject;
/*     */       
/* 187 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 188 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 189 */       arrayList.toArray(arrayOfGeometryAtom);
/* 190 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*     */       
/* 192 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {
/* 197 */     if (paramInt == 1 || paramInt == 3)
/*     */     {
/* 199 */       setFrequencyChangeMask(paramInt, 1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\PointAttributesRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */