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
/*     */ class PolygonAttributesRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*     */   static final int POLYGON_MODE_CHANGED = 1;
/*     */   static final int POLYGON_CULL_CHANGED = 2;
/*     */   static final int POLYGON_OFFSET_CHANGED = 4;
/*     */   static final int POLYGON_BACKFACENORMALFLIP_CHANGED = 8;
/*     */   static final int POLYGON_OFFSETFACTOR_CHANGED = 16;
/*  31 */   int polygonMode = 2;
/*     */ 
/*     */   
/*  34 */   int cullFace = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean backFaceNormalFlip = false;
/*     */ 
/*     */ 
/*     */   
/*     */   float polygonOffset;
/*     */ 
/*     */ 
/*     */   
/*     */   float polygonOffsetFactor;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   final void initCullFace(int paramInt) { this.cullFace = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setCullFace(int paramInt) {
/*  63 */     initCullFace(paramInt);
/*  64 */     sendMessage(2, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   final int getCullFace() { return this.cullFace; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   final void initBackFaceNormalFlip(boolean paramBoolean) { this.backFaceNormalFlip = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setBackFaceNormalFlip(boolean paramBoolean) {
/* 101 */     initBackFaceNormalFlip(paramBoolean);
/* 102 */     sendMessage(8, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   final boolean getBackFaceNormalFlip() { return this.backFaceNormalFlip; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   final void initPolygonMode(int paramInt) { this.polygonMode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPolygonMode(int paramInt) {
/* 132 */     initPolygonMode(paramInt);
/* 133 */     sendMessage(1, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   final int getPolygonMode() { return this.polygonMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   final void initPolygonOffset(float paramFloat) { this.polygonOffset = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPolygonOffset(float paramFloat) {
/* 164 */     initPolygonOffset(paramFloat);
/* 165 */     sendMessage(4, new Float(paramFloat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   final float getPolygonOffset() { return this.polygonOffset; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   final void initPolygonOffsetFactor(float paramFloat) { this.polygonOffsetFactor = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPolygonOffsetFactor(float paramFloat) {
/* 199 */     initPolygonOffsetFactor(paramFloat);
/* 200 */     sendMessage(16, new Float(paramFloat));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   final float getPolygonOffsetFactor() { return this.polygonOffsetFactor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createMirrorObject() {
/* 218 */     if (this.mirror == null) {
/*     */ 
/*     */       
/* 221 */       if (isStatic()) {
/* 222 */         this.mirror = this;
/*     */       } else {
/* 224 */         PolygonAttributesRetained polygonAttributesRetained = new PolygonAttributesRetained();
/* 225 */         polygonAttributesRetained.set(this);
/* 226 */         polygonAttributesRetained.source = this.source;
/* 227 */         this.mirror = polygonAttributesRetained;
/*     */       } 
/*     */     } else {
/* 230 */       ((PolygonAttributesRetained)this.mirror).set(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 239 */   void updateNative(Context paramContext) { Pipeline.getPipeline().updatePolygonAttributes(paramContext, this.polygonMode, this.cullFace, this.backFaceNormalFlip, this.polygonOffset, this.polygonOffsetFactor); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   void initMirrorObject() { ((PolygonAttributesRetained)this.mirror).set(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 258 */     PolygonAttributesRetained polygonAttributesRetained = (PolygonAttributesRetained)this.mirror;
/*     */     
/* 260 */     if ((paramInt & true) != 0) {
/* 261 */       polygonAttributesRetained.polygonMode = ((Integer)paramObject).intValue();
/*     */     }
/* 263 */     else if ((paramInt & 0x2) != 0) {
/* 264 */       polygonAttributesRetained.cullFace = ((Integer)paramObject).intValue();
/*     */     }
/* 266 */     else if ((paramInt & 0x8) != 0) {
/* 267 */       polygonAttributesRetained.backFaceNormalFlip = ((Boolean)paramObject).booleanValue();
/*     */     }
/* 269 */     else if ((paramInt & 0x4) != 0) {
/* 270 */       polygonAttributesRetained.polygonOffset = ((Float)paramObject).floatValue();
/*     */     }
/* 272 */     else if ((paramInt & 0x10) != 0) {
/* 273 */       polygonAttributesRetained.polygonOffsetFactor = ((Float)paramObject).floatValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 279 */   boolean equivalent(PolygonAttributesRetained paramPolygonAttributesRetained) { return (paramPolygonAttributesRetained != null && paramPolygonAttributesRetained.cullFace == this.cullFace && paramPolygonAttributesRetained.backFaceNormalFlip == this.backFaceNormalFlip && paramPolygonAttributesRetained.polygonOffset == this.polygonOffset && paramPolygonAttributesRetained.polygonMode == this.polygonMode && paramPolygonAttributesRetained.polygonOffsetFactor == this.polygonOffsetFactor); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void set(PolygonAttributesRetained paramPolygonAttributesRetained) {
/* 288 */     set(paramPolygonAttributesRetained);
/* 289 */     this.cullFace = paramPolygonAttributesRetained.cullFace;
/* 290 */     this.backFaceNormalFlip = paramPolygonAttributesRetained.backFaceNormalFlip;
/* 291 */     this.polygonMode = paramPolygonAttributesRetained.polygonMode;
/* 292 */     this.polygonOffset = paramPolygonAttributesRetained.polygonOffset;
/* 293 */     this.polygonOffsetFactor = paramPolygonAttributesRetained.polygonOffsetFactor;
/*     */   }
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 297 */     ArrayList arrayList1 = new ArrayList();
/* 298 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*     */ 
/*     */ 
/*     */     
/* 302 */     J3dMessage j3dMessage = new J3dMessage();
/* 303 */     j3dMessage.threads = 1024;
/* 304 */     j3dMessage.type = 9;
/* 305 */     j3dMessage.universe = null;
/* 306 */     j3dMessage.args[0] = this;
/* 307 */     j3dMessage.args[1] = new Integer(paramInt);
/* 308 */     j3dMessage.args[2] = paramObject;
/* 309 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/* 310 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */ 
/*     */     
/* 313 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 314 */       j3dMessage = new J3dMessage();
/* 315 */       j3dMessage.threads = 128;
/* 316 */       j3dMessage.type = 9;
/*     */       
/* 318 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 319 */       j3dMessage.args[0] = this;
/* 320 */       j3dMessage.args[1] = new Integer(paramInt);
/* 321 */       j3dMessage.args[2] = paramObject;
/*     */       
/* 323 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 324 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 325 */       arrayList.toArray(arrayOfGeometryAtom);
/* 326 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*     */       
/* 328 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {
/* 334 */     if (paramInt == 1 || paramInt == 7 || paramInt == 3 || paramInt == 5)
/*     */     {
/*     */ 
/*     */       
/* 338 */       setFrequencyChangeMask(paramInt, 1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\PolygonAttributesRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */