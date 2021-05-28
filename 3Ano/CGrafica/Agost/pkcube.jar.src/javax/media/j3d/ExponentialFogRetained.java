/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Color3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ExponentialFogRetained
/*     */   extends FogRetained
/*     */ {
/*  24 */   private float density = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float densityInEc;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final int DENSITY_CHANGED = 64;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   void initDensity(float paramFloat) { this.density = paramFloat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setDensity(float paramFloat) {
/*  48 */     this.density = paramFloat;
/*  49 */     J3dMessage j3dMessage = new J3dMessage();
/*  50 */     j3dMessage.threads = 4224;
/*  51 */     j3dMessage.type = 22;
/*  52 */     j3dMessage.universe = this.universe;
/*  53 */     j3dMessage.args[0] = this;
/*  54 */     j3dMessage.args[1] = new Integer(64);
/*  55 */     j3dMessage.args[2] = new Float(paramFloat);
/*  56 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   float getDensity() { return this.density; }
/*     */ 
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/*  68 */     super.setLive(paramSetLiveState);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     J3dMessage j3dMessage = new J3dMessage();
/*  74 */     j3dMessage.threads = 4096;
/*  75 */     j3dMessage.universe = this.universe;
/*  76 */     j3dMessage.type = 22;
/*  77 */     j3dMessage.args[0] = this;
/*     */ 
/*     */     
/*  80 */     j3dMessage.args[1] = new Integer(16);
/*  81 */     ArrayList arrayList = new ArrayList();
/*  82 */     for (byte b = 0; b < this.scopes.size(); b++) {
/*  83 */       GroupRetained groupRetained = (GroupRetained)this.scopes.get(b);
/*  84 */       this.tempKey.reset();
/*  85 */       groupRetained.addAllNodesForScopedFog(this.mirrorFog, arrayList, this.tempKey);
/*     */     } 
/*  87 */     Object[] arrayOfObject1 = new Object[2];
/*  88 */     arrayOfObject1[0] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/*  89 */     arrayOfObject1[1] = arrayList;
/*  90 */     j3dMessage.args[2] = arrayOfObject1;
/*  91 */     Color3f color3f = new Color3f(this.color);
/*  92 */     j3dMessage.args[3] = color3f;
/*     */     
/*  94 */     Object[] arrayOfObject2 = new Object[5];
/*  95 */     arrayOfObject2[0] = this.boundingLeaf;
/*  96 */     arrayOfObject2[1] = (this.regionOfInfluence != null) ? this.regionOfInfluence.clone() : null;
/*  97 */     arrayOfObject2[2] = this.inBackgroundGroup ? Boolean.TRUE : Boolean.FALSE;
/*  98 */     arrayOfObject2[3] = this.geometryBackground;
/*  99 */     arrayOfObject2[4] = new Float(this.density);
/*     */     
/* 101 */     j3dMessage.args[4] = arrayOfObject2;
/* 102 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void update(Context paramContext, double paramDouble) {
/* 113 */     validateDistancesInEc(paramDouble);
/* 114 */     Pipeline.getPipeline().updateExponentialFog(paramContext, this.color.x, this.color.y, this.color.z, this.densityInEc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(Object[] paramArrayOfObject) {
/* 124 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*     */ 
/*     */     
/* 127 */     if ((i & 0x40) != 0) {
/* 128 */       ((ExponentialFogRetained)this.mirrorFog).density = ((Float)paramArrayOfObject[2]).floatValue();
/*     */     }
/* 130 */     if ((i & 0x10) != 0) {
/* 131 */       ((ExponentialFogRetained)this.mirrorFog).density = ((Float)(Object[])paramArrayOfObject[4][4]).floatValue();
/*     */     }
/*     */ 
/*     */     
/* 135 */     ((ExponentialFogRetained)this.mirrorFog).setLocalToVworldScale(getLastLocalToVworld().getDistanceScale());
/*     */     
/* 137 */     super.updateMirrorObject(paramArrayOfObject);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object clone() {
/* 143 */     ExponentialFogRetained exponentialFogRetained = (ExponentialFogRetained)super.clone();
/*     */ 
/*     */     
/* 146 */     exponentialFogRetained.initDensity(getDensity());
/*     */     
/* 148 */     return exponentialFogRetained;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void validateDistancesInEc(double paramDouble) {
/* 159 */     double d = getLocalToVworldScale() * paramDouble;
/*     */     
/* 161 */     this.densityInEc = (float)(this.density / d);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\ExponentialFogRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */