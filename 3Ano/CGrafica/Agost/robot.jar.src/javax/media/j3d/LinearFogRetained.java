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
/*     */ 
/*     */ 
/*     */ 
/*     */ class LinearFogRetained
/*     */   extends FogRetained
/*     */ {
/*  27 */   private double frontDistance = 0.1D;
/*  28 */   private double backDistance = 1.0D;
/*     */ 
/*     */   
/*     */   private double frontDistanceInEc;
/*     */ 
/*     */   
/*     */   private double backDistanceInEc;
/*     */ 
/*     */   
/*     */   static final int FRONT_DISTANCE_CHANGED = 64;
/*     */ 
/*     */   
/*     */   static final int BACK_DISTANCE_CHANGED = 128;
/*     */ 
/*     */ 
/*     */   
/*  44 */   void initFrontDistance(double paramDouble) { this.frontDistance = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFrontDistance(double paramDouble) {
/*  51 */     this.frontDistance = paramDouble;
/*  52 */     J3dMessage j3dMessage = new J3dMessage();
/*  53 */     j3dMessage.threads = 4224;
/*  54 */     j3dMessage.type = 22;
/*  55 */     j3dMessage.universe = this.universe;
/*  56 */     j3dMessage.args[0] = this;
/*  57 */     j3dMessage.args[1] = new Integer(64);
/*  58 */     j3dMessage.args[2] = new Double(paramDouble);
/*  59 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   double getFrontDistance() { return this.frontDistance; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   void initBackDistance(double paramDouble) { this.backDistance = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setBackDistance(double paramDouble) {
/*  80 */     this.backDistance = paramDouble;
/*  81 */     J3dMessage j3dMessage = new J3dMessage();
/*  82 */     j3dMessage.threads = 4224;
/*  83 */     j3dMessage.type = 22;
/*  84 */     j3dMessage.universe = this.universe;
/*  85 */     j3dMessage.args[0] = this;
/*  86 */     j3dMessage.args[1] = new Integer(128);
/*  87 */     j3dMessage.args[2] = new Double(paramDouble);
/*  88 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   double getBackDistance() { return this.backDistance; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void update(Context paramContext, double paramDouble) {
/* 102 */     validateDistancesInEc(paramDouble);
/* 103 */     Pipeline.getPipeline().updateLinearFog(paramContext, this.color.x, this.color.y, this.color.z, this.frontDistanceInEc, this.backDistanceInEc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLive(SetLiveState paramSetLiveState) {
/* 112 */     super.setLive(paramSetLiveState);
/*     */ 
/*     */ 
/*     */     
/* 116 */     J3dMessage j3dMessage = new J3dMessage();
/* 117 */     j3dMessage.threads = 4096;
/* 118 */     j3dMessage.universe = this.universe;
/* 119 */     j3dMessage.type = 22;
/* 120 */     j3dMessage.args[0] = this;
/*     */ 
/*     */     
/* 123 */     j3dMessage.args[1] = new Integer(16);
/* 124 */     ArrayList arrayList = new ArrayList();
/* 125 */     for (byte b = 0; b < this.scopes.size(); b++) {
/* 126 */       GroupRetained groupRetained = (GroupRetained)this.scopes.get(b);
/* 127 */       this.tempKey.reset();
/* 128 */       groupRetained.addAllNodesForScopedFog(this.mirrorFog, arrayList, this.tempKey);
/*     */     } 
/* 130 */     Object[] arrayOfObject1 = new Object[2];
/* 131 */     arrayOfObject1[0] = (this.scopes.size() > 0) ? Boolean.TRUE : Boolean.FALSE;
/* 132 */     arrayOfObject1[1] = arrayList;
/* 133 */     j3dMessage.args[2] = arrayOfObject1;
/* 134 */     Color3f color3f = new Color3f(this.color);
/* 135 */     j3dMessage.args[3] = color3f;
/*     */     
/* 137 */     Object[] arrayOfObject2 = new Object[6];
/* 138 */     arrayOfObject2[0] = this.boundingLeaf;
/* 139 */     arrayOfObject2[1] = (this.regionOfInfluence != null) ? this.regionOfInfluence.clone() : null;
/* 140 */     arrayOfObject2[2] = this.inBackgroundGroup ? Boolean.TRUE : Boolean.FALSE;
/* 141 */     arrayOfObject2[3] = this.geometryBackground;
/* 142 */     arrayOfObject2[4] = new Double(this.frontDistance);
/* 143 */     arrayOfObject2[5] = new Double(this.backDistance);
/*     */     
/* 145 */     j3dMessage.args[4] = arrayOfObject2;
/* 146 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(Object[] paramArrayOfObject) {
/* 156 */     int i = ((Integer)paramArrayOfObject[1]).intValue();
/*     */ 
/*     */     
/* 159 */     if ((i & 0x40) != 0)
/* 160 */       ((LinearFogRetained)this.mirrorFog).frontDistance = ((Double)paramArrayOfObject[2]).doubleValue(); 
/* 161 */     if ((i & 0x80) != 0)
/* 162 */       ((LinearFogRetained)this.mirrorFog).backDistance = ((Double)paramArrayOfObject[2]).doubleValue(); 
/* 163 */     if ((i & 0x10) != 0) {
/* 164 */       ((LinearFogRetained)this.mirrorFog).frontDistance = ((Double)(Object[])paramArrayOfObject[4][4]).doubleValue();
/* 165 */       ((LinearFogRetained)this.mirrorFog).backDistance = ((Double)(Object[])paramArrayOfObject[4][5]).doubleValue();
/*     */     } 
/*     */     
/* 168 */     ((LinearFogRetained)this.mirrorFog).setLocalToVworldScale(getLastLocalToVworld().getDistanceScale());
/*     */     
/* 170 */     super.updateMirrorObject(paramArrayOfObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void validateDistancesInEc(double paramDouble) {
/* 179 */     double d = getLocalToVworldScale() * paramDouble;
/*     */     
/* 181 */     this.frontDistanceInEc = this.frontDistance * d;
/* 182 */     this.backDistanceInEc = this.backDistance * d;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\LinearFogRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */