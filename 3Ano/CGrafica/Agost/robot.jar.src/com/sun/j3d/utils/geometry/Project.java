/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import javax.vecmath.Matrix4f;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Project
/*     */ {
/*     */   static void projectFace(Triangulator paramTriangulator, int paramInt1, int paramInt2) {
/*  77 */     Vector3f vector3f1 = new Vector3f();
/*  78 */     Vector3f vector3f2 = new Vector3f();
/*     */ 
/*     */     
/*  81 */     determineNormal(paramTriangulator, paramTriangulator.loops[paramInt1], vector3f1);
/*  82 */     int i = paramInt1 + 1;
/*  83 */     if (i < paramInt2) {
/*  84 */       for (int j = i; j < paramInt2; j++) {
/*  85 */         determineNormal(paramTriangulator, paramTriangulator.loops[j], vector3f2);
/*  86 */         if (Basic.dotProduct(vector3f1, vector3f2) < 0.0D) {
/*  87 */           Basic.invertVector(vector3f2);
/*     */         }
/*  89 */         Basic.vectorAdd(vector3f1, vector3f2, vector3f1);
/*     */       } 
/*  91 */       double d = Basic.lengthL2(vector3f1);
/*  92 */       if (Numerics.gt(d, 1.0E-8D)) {
/*  93 */         Basic.divScalar(d, vector3f1);
/*     */       }
/*     */       else {
/*     */         
/*  97 */         vector3f1.x = vector3f1.y = 0.0F;
/*  98 */         vector3f1.z = 1.0F;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     projectPoints(paramTriangulator, paramInt1, paramInt2, vector3f1);
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
/*     */   static void determineNormal(Triangulator paramTriangulator, int paramInt, Vector3f paramVector3f) {
/* 122 */     int j = paramInt;
/* 123 */     int n = paramTriangulator.fetchData(j);
/* 124 */     int i = paramTriangulator.fetchPrevData(j);
/* 125 */     int m = paramTriangulator.fetchData(i);
/* 126 */     int k = paramTriangulator.fetchNextData(j);
/* 127 */     int i1 = paramTriangulator.fetchData(k);
/* 128 */     Vector3f vector3f2 = new Vector3f();
/* 129 */     Basic.vectorSub(paramTriangulator.vertices[m], paramTriangulator.vertices[n], vector3f2);
/* 130 */     Vector3f vector3f3 = new Vector3f();
/* 131 */     Basic.vectorSub(paramTriangulator.vertices[i1], paramTriangulator.vertices[n], vector3f3);
/* 132 */     Vector3f vector3f1 = new Vector3f();
/* 133 */     Basic.vectorProduct(vector3f2, vector3f3, vector3f1);
/* 134 */     double d = Basic.lengthL2(vector3f1);
/* 135 */     if (Numerics.gt(d, 1.0E-8D)) {
/* 136 */       Basic.divScalar(d, vector3f1);
/* 137 */       paramVector3f.set(vector3f1);
/*     */     } else {
/*     */       
/* 140 */       paramVector3f.x = paramVector3f.y = paramVector3f.z = 0.0F;
/*     */     } 
/*     */     
/* 143 */     vector3f2.set(vector3f3);
/* 144 */     j = k;
/* 145 */     k = paramTriangulator.fetchNextData(j);
/* 146 */     i1 = paramTriangulator.fetchData(k);
/* 147 */     while (j != paramInt) {
/* 148 */       Basic.vectorSub(paramTriangulator.vertices[i1], paramTriangulator.vertices[n], vector3f3);
/* 149 */       Basic.vectorProduct(vector3f2, vector3f3, vector3f1);
/* 150 */       d = Basic.lengthL2(vector3f1);
/* 151 */       if (Numerics.gt(d, 1.0E-8D)) {
/* 152 */         Basic.divScalar(d, vector3f1);
/* 153 */         if (Basic.dotProduct(paramVector3f, vector3f1) < 0.0D) {
/* 154 */           Basic.invertVector(vector3f1);
/*     */         }
/* 156 */         Basic.vectorAdd(paramVector3f, vector3f1, paramVector3f);
/*     */       } 
/* 158 */       vector3f2.set(vector3f3);
/* 159 */       j = k;
/* 160 */       k = paramTriangulator.fetchNextData(j);
/* 161 */       i1 = paramTriangulator.fetchData(k);
/*     */     } 
/*     */     
/* 164 */     d = Basic.lengthL2(paramVector3f);
/* 165 */     if (Numerics.gt(d, 1.0E-8D)) {
/* 166 */       Basic.divScalar(d, paramVector3f);
/*     */     }
/*     */     else {
/*     */       
/* 170 */       paramVector3f.x = paramVector3f.y = 0.0F; paramVector3f.z = 1.0F;
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
/*     */   static void projectPoints(Triangulator paramTriangulator, int paramInt1, int paramInt2, Vector3f paramVector3f) {
/* 186 */     Matrix4f matrix4f = new Matrix4f();
/* 187 */     Point3f point3f = new Point3f();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 194 */     Vector3f vector3f1 = new Vector3f();
/* 195 */     Vector3f vector3f2 = new Vector3f();
/*     */ 
/*     */     
/* 198 */     if (Math.abs(paramVector3f.x) > 0.1D || Math.abs(paramVector3f.y) > 0.1D) {
/* 199 */       vector3f1.x = -paramVector3f.y;
/* 200 */       vector3f1.y = paramVector3f.x;
/* 201 */       vector3f1.z = 0.0F;
/*     */     } else {
/*     */       
/* 204 */       vector3f1.x = paramVector3f.z;
/* 205 */       vector3f1.z = -paramVector3f.x;
/* 206 */       vector3f1.y = 0.0F;
/*     */     } 
/* 208 */     double d = Basic.lengthL2(vector3f1);
/* 209 */     Basic.divScalar(d, vector3f1);
/* 210 */     Basic.vectorProduct(vector3f1, paramVector3f, vector3f2);
/* 211 */     d = Basic.lengthL2(vector3f2);
/* 212 */     Basic.divScalar(d, vector3f2);
/*     */ 
/*     */     
/* 215 */     matrix4f.m00 = vector3f1.x;
/* 216 */     matrix4f.m01 = vector3f1.y;
/* 217 */     matrix4f.m02 = vector3f1.z;
/* 218 */     matrix4f.m03 = 0.0F;
/* 219 */     matrix4f.m10 = vector3f2.x;
/* 220 */     matrix4f.m11 = vector3f2.y;
/* 221 */     matrix4f.m12 = vector3f2.z;
/* 222 */     matrix4f.m13 = 0.0F;
/* 223 */     matrix4f.m20 = paramVector3f.x;
/* 224 */     matrix4f.m21 = paramVector3f.y;
/* 225 */     matrix4f.m22 = paramVector3f.z;
/* 226 */     matrix4f.m23 = 0.0F;
/* 227 */     matrix4f.m30 = 0.0F;
/* 228 */     matrix4f.m31 = 0.0F;
/* 229 */     matrix4f.m32 = 0.0F;
/* 230 */     matrix4f.m33 = 1.0F;
/*     */ 
/*     */ 
/*     */     
/* 234 */     paramTriangulator.initPnts(20);
/* 235 */     for (int i = paramInt1; i < paramInt2; i++) {
/* 236 */       int j = paramTriangulator.loops[i];
/* 237 */       int k = j;
/* 238 */       int m = paramTriangulator.fetchData(k);
/* 239 */       matrix4f.transform(paramTriangulator.vertices[m], point3f);
/* 240 */       m = paramTriangulator.storePoint(point3f.x, point3f.y);
/* 241 */       paramTriangulator.updateIndex(k, m);
/* 242 */       k = paramTriangulator.fetchNextData(k);
/* 243 */       m = paramTriangulator.fetchData(k);
/* 244 */       while (k != j) {
/* 245 */         matrix4f.transform(paramTriangulator.vertices[m], point3f);
/* 246 */         m = paramTriangulator.storePoint(point3f.x, point3f.y);
/* 247 */         paramTriangulator.updateIndex(k, m);
/* 248 */         k = paramTriangulator.fetchNextData(k);
/* 249 */         m = paramTriangulator.fetchData(k);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\geometry\Project.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */