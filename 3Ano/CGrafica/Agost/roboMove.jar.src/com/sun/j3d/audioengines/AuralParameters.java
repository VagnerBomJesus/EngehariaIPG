/*     */ package com.sun.j3d.audioengines;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AuralParameters
/*     */ {
/*     */   public static final float SPEED_OF_SOUND = 0.344F;
/*     */   public static final int NO_FILTERING = -1;
/*     */   public float rolloff;
/*     */   public float reflectionCoefficient;
/*     */   public float reverbDelay;
/*     */   public int reverbOrder;
/*     */   public float frequencyScaleFactor;
/*     */   public float velocityScaleFactor;
/*     */   int filterType;
/*     */   double[] filterDistance;
/*     */   float[] filterCutoff;
/*     */   public float reverbCoefficient;
/*     */   public float reflectionDelay;
/*     */   public float decayTime;
/*     */   public float decayFrequencyCutoff;
/*     */   public float diffusion;
/*     */   public float density;
/*     */   static final boolean debugFlag = false;
/*     */   static final boolean internalErrors = false;
/*     */   
/*     */   public AuralParameters() {
/*  66 */     this.rolloff = 1.0F;
/*  67 */     this.reflectionCoefficient = 0.0F;
/*  68 */     this.reverbDelay = 40.0F;
/*  69 */     this.reverbOrder = 0;
/*  70 */     this.frequencyScaleFactor = 1.0F;
/*  71 */     this.velocityScaleFactor = 0.0F;
/*  72 */     this.filterType = -1;
/*  73 */     this.filterDistance = null;
/*  74 */     this.filterCutoff = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     this.reverbCoefficient = 1.0F;
/*  80 */     this.reflectionDelay = 20.0F;
/*  81 */     this.decayTime = 1000.0F;
/*  82 */     this.decayFrequencyCutoff = 5000.0F;
/*  83 */     this.diffusion = 1.0F;
/*  84 */     this.density = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     this.frequencyScaleFactor = 1.0F;
/*  91 */     this.velocityScaleFactor = 0.0F;
/*  92 */     this.rolloff = 1.0F;
/*  93 */     this.reflectionCoefficient = 0.0F;
/*  94 */     this.reflectionDelay = 20.0F;
/*  95 */     this.reverbCoefficient = 1.0F;
/*  96 */     this.reverbDelay = 40.0F;
/*  97 */     this.reverbOrder = 0;
/*  98 */     this.filterType = -1;
/*  99 */     this.filterDistance = new double[2];
/* 100 */     this.filterCutoff = new float[2];
/* 101 */     this.decayTime = 1000.0F;
/* 102 */     this.decayFrequencyCutoff = 5000.0F;
/* 103 */     this.diffusion = 1.0F;
/* 104 */     this.density = 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDistanceFilter(int paramInt, double[] paramArrayOfDouble, float[] paramArrayOfFloat) {
/* 109 */     boolean bool1 = false;
/* 110 */     boolean bool2 = false;
/* 111 */     int i = 0;
/* 112 */     if (paramArrayOfDouble == null || paramArrayOfFloat == null) {
/* 113 */       bool1 = true;
/*     */     } else {
/*     */       
/* 116 */       i = paramArrayOfDouble.length;
/* 117 */       if (i == 0 || paramInt == -1) {
/* 118 */         bool1 = true;
/*     */       }
/*     */     } 
/* 121 */     if (bool1) {
/* 122 */       this.filterType = -1;
/* 123 */       this.filterDistance = null;
/* 124 */       this.filterCutoff = null;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 129 */     this.filterType = paramInt;
/*     */ 
/*     */     
/* 132 */     if (this.filterDistance == null || paramArrayOfFloat == null) {
/* 133 */       bool2 = true;
/*     */     }
/* 135 */     else if (i > this.filterDistance.length) {
/* 136 */       bool2 = true;
/*     */     } 
/* 138 */     if (bool2) {
/*     */ 
/*     */       
/* 141 */       this.filterDistance = new double[i];
/* 142 */       this.filterCutoff = new float[i];
/*     */     } 
/* 144 */     System.arraycopy(paramArrayOfDouble, 0, this.filterDistance, 0, i);
/*     */     
/* 146 */     System.arraycopy(paramArrayOfFloat, 0, this.filterCutoff, 0, i);
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
/*     */   public int getDistanceFilterLength() {
/* 160 */     if (this.filterDistance != null)
/* 161 */       return this.filterDistance.length; 
/* 162 */     return 0;
/*     */   }
/*     */ 
/*     */   
/* 166 */   public int getDistanceFilterType() { return this.filterType; }
/*     */ 
/*     */   
/*     */   public void getDistanceFilter(double[] paramArrayOfDouble, float[] paramArrayOfFloat) {
/* 170 */     if (paramArrayOfDouble == null || paramArrayOfFloat == null)
/*     */       return; 
/* 172 */     int i = paramArrayOfDouble.length;
/* 173 */     if (i == 0 || this.filterDistance == null || paramArrayOfFloat == null) {
/*     */       return;
/*     */     }
/* 176 */     if (i > this.filterDistance.length)
/* 177 */       i = this.filterDistance.length; 
/* 178 */     System.arraycopy(this.filterDistance, 0, paramArrayOfDouble, 0, i);
/*     */     
/* 180 */     System.arraycopy(this.filterCutoff, 0, paramArrayOfFloat, 0, i);
/*     */   }
/*     */   
/*     */   protected void debugPrint(String paramString) {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3d\audioengines\AuralParameters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */