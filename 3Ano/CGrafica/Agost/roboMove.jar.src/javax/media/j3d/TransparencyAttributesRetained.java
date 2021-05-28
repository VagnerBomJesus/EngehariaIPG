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
/*     */ class TransparencyAttributesRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*     */   static final int MODE_CHANGED = 1;
/*     */   static final int VALUE_CHANGED = 2;
/*     */   static final int SRC_BLEND_FUNCTION_CHANGED = 4;
/*     */   static final int DST_BLEND_FUNCTION_CHANGED = 8;
/*  31 */   int isDirty = 65535;
/*     */ 
/*     */   
/*  34 */   int transparencyMode = 4;
/*  35 */   float transparency = 0.0F;
/*     */ 
/*     */   
/*  38 */   int srcBlendFunction = 2;
/*  39 */   int dstBlendFunction = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   final void initTransparencyMode(int paramInt) { this.transparencyMode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setTransparencyMode(int paramInt) {
/*  61 */     initTransparencyMode(paramInt);
/*  62 */     sendMessage(1, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   final int getTransparencyMode() { return this.transparencyMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   final void initTransparency(float paramFloat) { this.transparency = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setTransparency(float paramFloat) {
/*  92 */     initTransparency(paramFloat);
/*  93 */     sendMessage(2, new Float(paramFloat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   final float getTransparency() { return this.transparency; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   final void initSrcBlendFunction(int paramInt) { this.srcBlendFunction = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setSrcBlendFunction(int paramInt) {
/* 135 */     initSrcBlendFunction(paramInt);
/* 136 */     sendMessage(4, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   final int getSrcBlendFunction() { return this.srcBlendFunction; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   final void initDstBlendFunction(int paramInt) { this.dstBlendFunction = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setDstBlendFunction(int paramInt) {
/* 181 */     initDstBlendFunction(paramInt);
/* 182 */     sendMessage(8, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 191 */   final int getDstBlendFunction() { return this.dstBlendFunction; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createMirrorObject() {
/* 200 */     if (this.mirror == null) {
/*     */ 
/*     */       
/* 203 */       if (isStatic()) {
/* 204 */         this.mirror = this;
/*     */       } else {
/* 206 */         TransparencyAttributesRetained transparencyAttributesRetained = new TransparencyAttributesRetained();
/*     */         
/* 208 */         transparencyAttributesRetained.source = this.source;
/* 209 */         transparencyAttributesRetained.set(this);
/* 210 */         this.mirror = transparencyAttributesRetained;
/*     */       } 
/*     */     } else {
/*     */       
/* 214 */       ((TransparencyAttributesRetained)this.mirror).set(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 222 */   void updateNative(Context paramContext, float paramFloat, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) { Pipeline.getPipeline().updateTransparencyAttributes(paramContext, paramFloat, paramInt1, paramInt2, paramBoolean1, paramBoolean2, this.transparencyMode, this.srcBlendFunction, this.dstBlendFunction); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 232 */   void initMirrorObject() { ((TransparencyAttributesRetained)this.mirror).set(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 241 */     TransparencyAttributesRetained transparencyAttributesRetained = (TransparencyAttributesRetained)this.mirror;
/*     */ 
/*     */     
/* 244 */     if ((paramInt & true) != 0) {
/* 245 */       transparencyAttributesRetained.transparencyMode = ((Integer)paramObject).intValue();
/*     */     }
/* 247 */     else if ((paramInt & 0x2) != 0) {
/* 248 */       transparencyAttributesRetained.transparency = ((Float)paramObject).floatValue();
/*     */     }
/* 250 */     else if ((paramInt & 0x4) != 0) {
/* 251 */       transparencyAttributesRetained.srcBlendFunction = ((Integer)paramObject).intValue();
/*     */     }
/* 253 */     else if ((paramInt & 0x8) != 0) {
/* 254 */       transparencyAttributesRetained.dstBlendFunction = ((Integer)paramObject).intValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 260 */   boolean equivalent(TransparencyAttributesRetained paramTransparencyAttributesRetained) { return (paramTransparencyAttributesRetained != null && paramTransparencyAttributesRetained.transparencyMode == this.transparencyMode && paramTransparencyAttributesRetained.transparency == this.transparency && paramTransparencyAttributesRetained.srcBlendFunction == this.srcBlendFunction && paramTransparencyAttributesRetained.dstBlendFunction == this.dstBlendFunction); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void set(TransparencyAttributesRetained paramTransparencyAttributesRetained) {
/* 268 */     set(paramTransparencyAttributesRetained);
/* 269 */     this.transparencyMode = paramTransparencyAttributesRetained.transparencyMode;
/* 270 */     this.transparency = paramTransparencyAttributesRetained.transparency;
/* 271 */     this.srcBlendFunction = paramTransparencyAttributesRetained.srcBlendFunction;
/* 272 */     this.dstBlendFunction = paramTransparencyAttributesRetained.dstBlendFunction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 279 */     ArrayList arrayList1 = new ArrayList();
/* 280 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*     */ 
/*     */ 
/*     */     
/* 284 */     J3dMessage j3dMessage = new J3dMessage();
/* 285 */     j3dMessage.threads = 1024;
/* 286 */     j3dMessage.type = 12;
/* 287 */     j3dMessage.universe = null;
/* 288 */     j3dMessage.args[0] = this;
/* 289 */     j3dMessage.args[1] = new Integer(paramInt);
/* 290 */     j3dMessage.args[2] = paramObject;
/* 291 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/* 292 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */ 
/*     */ 
/*     */     
/* 296 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 297 */       j3dMessage = new J3dMessage();
/* 298 */       j3dMessage.threads = 128;
/* 299 */       j3dMessage.type = 12;
/*     */       
/* 301 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 302 */       j3dMessage.args[0] = this;
/* 303 */       j3dMessage.args[1] = new Integer(paramInt);
/* 304 */       j3dMessage.args[2] = paramObject;
/*     */       
/* 306 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 307 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 308 */       arrayList.toArray(arrayOfGeometryAtom);
/* 309 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*     */       
/* 311 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {
/* 317 */     if (paramInt == 1 || paramInt == 3 || paramInt == 5)
/*     */     {
/*     */       
/* 320 */       setFrequencyChangeMask(paramInt, 1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\TransparencyAttributesRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */