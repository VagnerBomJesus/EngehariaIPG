/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import javax.vecmath.Tuple2f;
/*     */ import javax.vecmath.Tuple3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Basic
/*     */ {
/*     */   static final double D_RND_MAX = 2.147483647E9D;
/*     */   
/*  75 */   static double detExp(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8, double paramDouble9) { return paramDouble1 * (paramDouble5 * paramDouble9 - paramDouble6 * paramDouble8) - paramDouble2 * (paramDouble4 * paramDouble9 - paramDouble6 * paramDouble7) + paramDouble3 * (paramDouble4 * paramDouble8 - paramDouble5 * paramDouble7); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   static double det3D(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2, Tuple3f paramTuple3f3) { return (paramTuple3f1.x * (paramTuple3f2.y * paramTuple3f3.z - paramTuple3f2.z * paramTuple3f3.y) - paramTuple3f1.y * (paramTuple3f2.x * paramTuple3f3.z - paramTuple3f2.z * paramTuple3f3.x) + paramTuple3f1.z * (paramTuple3f2.x * paramTuple3f3.y - paramTuple3f2.y * paramTuple3f3.x)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   static double det2D(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2, Tuple2f paramTuple2f3) { return ((paramTuple2f1.x - paramTuple2f2.x) * (paramTuple2f2.y - paramTuple2f3.y) + (paramTuple2f2.y - paramTuple2f1.y) * (paramTuple2f2.x - paramTuple2f3.x)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   static double length2(Tuple3f paramTuple3f) { return (paramTuple3f.x * paramTuple3f.x + paramTuple3f.y * paramTuple3f.y + paramTuple3f.z * paramTuple3f.z); }
/*     */ 
/*     */ 
/*     */   
/*  98 */   static double lengthL1(Tuple3f paramTuple3f) { return (Math.abs(paramTuple3f.x) + Math.abs(paramTuple3f.y) + Math.abs(paramTuple3f.z)); }
/*     */ 
/*     */ 
/*     */   
/* 102 */   static double lengthL2(Tuple3f paramTuple3f) { return Math.sqrt((paramTuple3f.x * paramTuple3f.x + paramTuple3f.y * paramTuple3f.y + paramTuple3f.z * paramTuple3f.z)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   static double dotProduct(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2) { return (paramTuple3f1.x * paramTuple3f2.x + paramTuple3f1.y * paramTuple3f2.y + paramTuple3f1.z * paramTuple3f2.z); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   static double dotProduct2D(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2) { return (paramTuple2f1.x * paramTuple2f2.x + paramTuple2f1.y * paramTuple2f2.y); }
/*     */ 
/*     */ 
/*     */   
/*     */   static void vectorProduct(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2, Tuple3f paramTuple3f3) {
/* 117 */     paramTuple3f3.x = paramTuple3f1.y * paramTuple3f2.z - paramTuple3f2.y * paramTuple3f1.z;
/* 118 */     paramTuple3f3.y = paramTuple3f2.x * paramTuple3f1.z - paramTuple3f1.x * paramTuple3f2.z;
/* 119 */     paramTuple3f3.z = paramTuple3f1.x * paramTuple3f2.y - paramTuple3f2.x * paramTuple3f1.y;
/*     */   }
/*     */ 
/*     */   
/*     */   static void vectorAdd(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2, Tuple3f paramTuple3f3) {
/* 124 */     paramTuple3f1.x += paramTuple3f2.x;
/* 125 */     paramTuple3f1.y += paramTuple3f2.y;
/* 126 */     paramTuple3f1.z += paramTuple3f2.z;
/*     */   }
/*     */   
/*     */   static void vectorSub(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2, Tuple3f paramTuple3f3) {
/* 130 */     paramTuple3f1.x -= paramTuple3f2.x;
/* 131 */     paramTuple3f1.y -= paramTuple3f2.y;
/* 132 */     paramTuple3f1.z -= paramTuple3f2.z;
/*     */   }
/*     */ 
/*     */   
/*     */   static void vectorAdd2D(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2, Tuple2f paramTuple2f3) {
/* 137 */     paramTuple2f1.x += paramTuple2f2.x;
/* 138 */     paramTuple2f1.y += paramTuple2f2.y;
/*     */   }
/*     */ 
/*     */   
/*     */   static void vectorSub2D(Tuple2f paramTuple2f1, Tuple2f paramTuple2f2, Tuple2f paramTuple2f3) {
/* 143 */     paramTuple2f1.x -= paramTuple2f2.x;
/* 144 */     paramTuple2f1.y -= paramTuple2f2.y;
/*     */   }
/*     */   
/*     */   static void invertVector(Tuple3f paramTuple3f) {
/* 148 */     paramTuple3f.x = -paramTuple3f.x;
/* 149 */     paramTuple3f.y = -paramTuple3f.y;
/* 150 */     paramTuple3f.z = -paramTuple3f.z;
/*     */   }
/*     */   
/*     */   static void divScalar(double paramDouble, Tuple3f paramTuple3f) {
/* 154 */     paramTuple3f.x = (float)(paramTuple3f.x / paramDouble);
/* 155 */     paramTuple3f.y = (float)(paramTuple3f.y / paramDouble);
/* 156 */     paramTuple3f.z = (float)(paramTuple3f.z / paramDouble);
/*     */   }
/*     */   
/*     */   static void multScalar2D(double paramDouble, Tuple2f paramTuple2f) {
/* 160 */     paramTuple2f.x = (float)(paramTuple2f.x * paramDouble);
/* 161 */     paramTuple2f.y = (float)(paramTuple2f.y * paramDouble);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 166 */   static int signEps(double paramDouble1, double paramDouble2) { return (paramDouble1 <= paramDouble2) ? ((paramDouble1 < -paramDouble2) ? -1 : 0) : 1; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\geometry\Basic.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */