/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class TexCoordGenerationRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*     */   private static final int ENABLE_CHANGED = 1;
/*     */   private static final int PLANE_S_CHANGED = 2;
/*     */   private static final int PLANE_T_CHANGED = 4;
/*     */   private static final int PLANE_R_CHANGED = 8;
/*     */   private static final int PLANE_Q_CHANGED = 16;
/*  36 */   int genMode = 0;
/*  37 */   int format = 0;
/*     */   
/*  39 */   Vector4f planeS = new Vector4f(1.0F, 0.0F, 0.0F, 0.0F);
/*  40 */   Vector4f planeT = new Vector4f(0.0F, 1.0F, 0.0F, 0.0F);
/*  41 */   Vector4f planeR = new Vector4f(0.0F, 0.0F, 0.0F, 0.0F);
/*  42 */   Vector4f planeQ = new Vector4f(0.0F, 0.0F, 0.0F, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean enable = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean mirrorCompDirty = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   final void initEnable(boolean paramBoolean) { this.enable = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setEnable(boolean paramBoolean) {
/*  69 */     initEnable(paramBoolean);
/*  70 */     sendMessage(1, paramBoolean ? Boolean.TRUE : Boolean.FALSE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   final boolean getEnable() { return this.enable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   final void initFormat(int paramInt) { this.format = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   final int getFormat() { return this.format; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   final void initGenMode(int paramInt) { this.genMode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   final int getGenMode() { return this.genMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPlaneS(Vector4f paramVector4f) {
/* 122 */     initPlaneS(paramVector4f);
/* 123 */     sendMessage(2, new Vector4f(paramVector4f));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   final void initPlaneS(Vector4f paramVector4f) { this.planeS.set(paramVector4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   final void getPlaneS(Vector4f paramVector4f) { paramVector4f.set(this.planeS); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPlaneT(Vector4f paramVector4f) {
/* 152 */     initPlaneT(paramVector4f);
/* 153 */     sendMessage(4, new Vector4f(paramVector4f));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   final void initPlaneT(Vector4f paramVector4f) { this.planeT.set(paramVector4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   final void getPlaneT(Vector4f paramVector4f) { paramVector4f.set(this.planeT); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPlaneR(Vector4f paramVector4f) {
/* 182 */     initPlaneR(paramVector4f);
/* 183 */     sendMessage(8, new Vector4f(paramVector4f));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   final void initPlaneR(Vector4f paramVector4f) { this.planeR.set(paramVector4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   final void getPlaneR(Vector4f paramVector4f) { paramVector4f.set(this.planeR); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setPlaneQ(Vector4f paramVector4f) {
/* 212 */     initPlaneQ(paramVector4f);
/* 213 */     sendMessage(16, new Vector4f(paramVector4f));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 223 */   final void initPlaneQ(Vector4f paramVector4f) { this.planeQ.set(paramVector4f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 232 */   final void getPlaneQ(Vector4f paramVector4f) { paramVector4f.set(this.planeQ); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createMirrorObject() {
/* 242 */     if (this.mirror == null) {
/*     */ 
/*     */       
/* 245 */       if (isStatic()) {
/* 246 */         this.mirror = this;
/*     */       } else {
/* 248 */         TexCoordGenerationRetained texCoordGenerationRetained = new TexCoordGenerationRetained();
/* 249 */         texCoordGenerationRetained.set(this);
/* 250 */         texCoordGenerationRetained.source = this.source;
/* 251 */         this.mirror = texCoordGenerationRetained;
/*     */       } 
/*     */     } else {
/* 254 */       ((TexCoordGenerationRetained)this.mirror).set(this);
/*     */     } 
/*     */   }
/*     */   
/*     */   void updateNative(Canvas3D paramCanvas3D) {
/* 259 */     int i = this.genMode;
/* 260 */     Transform3D transform3D1 = null;
/* 261 */     Transform3D transform3D2 = paramCanvas3D.vworldToEc;
/*     */     
/* 263 */     if ((paramCanvas3D.textureExtendedFeatures & 0x80) == 0 && (this.genMode == 3 || this.genMode == 4))
/*     */     {
/*     */       
/* 266 */       i = 2;
/*     */     }
/*     */     
/* 269 */     if (VirtualUniverse.mc.isD3D() && i == 1) {
/*     */       
/* 271 */       transform3D1 = new Transform3D(paramCanvas3D.vworldToEc);
/* 272 */       transform3D1.invert();
/* 273 */       transform3D2 = transform3D1;
/*     */     } 
/*     */     
/* 276 */     Pipeline.getPipeline().updateTexCoordGeneration(paramCanvas3D.ctx, this.enable, i, this.format, this.planeS.x, this.planeS.y, this.planeS.z, this.planeS.w, this.planeT.x, this.planeT.y, this.planeT.z, this.planeT.w, this.planeR.x, this.planeR.y, this.planeR.z, this.planeR.w, this.planeQ.x, this.planeQ.y, this.planeQ.z, this.planeQ.w, transform3D2.mat);
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
/* 289 */   void initMirrorObject() { ((TexCoordGenerationRetained)this.mirror).set(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 297 */     TexCoordGenerationRetained texCoordGenerationRetained = (TexCoordGenerationRetained)this.mirror;
/*     */     
/* 299 */     texCoordGenerationRetained.mirrorCompDirty = true;
/*     */     
/* 301 */     if ((paramInt & true) != 0) {
/* 302 */       texCoordGenerationRetained.enable = ((Boolean)paramObject).booleanValue();
/*     */     }
/* 304 */     else if ((paramInt & 0x2) != 0) {
/* 305 */       texCoordGenerationRetained.planeS = (Vector4f)paramObject;
/*     */     }
/* 307 */     else if ((paramInt & 0x4) != 0) {
/* 308 */       texCoordGenerationRetained.planeT = (Vector4f)paramObject;
/*     */     }
/* 310 */     else if ((paramInt & 0x8) != 0) {
/* 311 */       texCoordGenerationRetained.planeR = (Vector4f)paramObject;
/*     */     }
/* 313 */     else if ((paramInt & 0x10) != 0) {
/* 314 */       texCoordGenerationRetained.planeQ = (Vector4f)paramObject;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean equivalent(TexCoordGenerationRetained paramTexCoordGenerationRetained) {
/* 321 */     if (paramTexCoordGenerationRetained == null) {
/* 322 */       return false;
/*     */     }
/* 324 */     if (this.changedFrequent != 0 || paramTexCoordGenerationRetained.changedFrequent != 0) {
/* 325 */       return (this == paramTexCoordGenerationRetained);
/*     */     }
/*     */     
/* 328 */     return (paramTexCoordGenerationRetained.genMode == this.genMode && paramTexCoordGenerationRetained.format == this.format && paramTexCoordGenerationRetained.enable == this.enable && paramTexCoordGenerationRetained.planeS.equals(this.planeS) && paramTexCoordGenerationRetained.planeT.equals(this.planeT) && paramTexCoordGenerationRetained.planeR.equals(this.planeR));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object clone() {
/* 337 */     TexCoordGenerationRetained texCoordGenerationRetained = (TexCoordGenerationRetained)super.clone();
/* 338 */     texCoordGenerationRetained.planeS = new Vector4f(this.planeS);
/* 339 */     texCoordGenerationRetained.planeT = new Vector4f(this.planeT);
/* 340 */     texCoordGenerationRetained.planeR = new Vector4f(this.planeR);
/*     */     
/* 342 */     return texCoordGenerationRetained;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void set(TexCoordGenerationRetained paramTexCoordGenerationRetained) {
/* 347 */     set(paramTexCoordGenerationRetained);
/* 348 */     this.genMode = paramTexCoordGenerationRetained.genMode;
/* 349 */     this.format = paramTexCoordGenerationRetained.format;
/* 350 */     this.enable = paramTexCoordGenerationRetained.enable;
/* 351 */     this.planeS.set(paramTexCoordGenerationRetained.planeS);
/* 352 */     this.planeT.set(paramTexCoordGenerationRetained.planeT);
/* 353 */     this.planeR.set(paramTexCoordGenerationRetained.planeR);
/*     */   }
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 358 */     ArrayList arrayList1 = new ArrayList();
/* 359 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*     */ 
/*     */ 
/*     */     
/* 363 */     J3dMessage j3dMessage = new J3dMessage();
/* 364 */     j3dMessage.threads = 1024;
/* 365 */     j3dMessage.type = 14;
/* 366 */     j3dMessage.universe = null;
/* 367 */     j3dMessage.args[0] = this;
/* 368 */     j3dMessage.args[1] = new Integer(paramInt);
/* 369 */     j3dMessage.args[2] = paramObject;
/* 370 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/* 371 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */ 
/*     */ 
/*     */     
/* 375 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 376 */       j3dMessage = new J3dMessage();
/* 377 */       j3dMessage.threads = 128;
/* 378 */       j3dMessage.type = 14;
/*     */       
/* 380 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 381 */       j3dMessage.args[0] = this;
/* 382 */       j3dMessage.args[1] = new Integer(paramInt);
/* 383 */       j3dMessage.args[2] = paramObject;
/*     */       
/* 385 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 386 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 387 */       arrayList.toArray(arrayOfGeometryAtom);
/* 388 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*     */       
/* 390 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {
/* 395 */     switch (paramInt) {
/*     */       case 1:
/*     */       case 5:
/* 398 */         setFrequencyChangeMask(paramInt, paramInt);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\TexCoordGenerationRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */