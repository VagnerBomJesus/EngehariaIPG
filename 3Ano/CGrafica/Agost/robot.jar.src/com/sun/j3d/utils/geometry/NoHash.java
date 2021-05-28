/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class NoHash
/*     */ {
/*     */   static final int NIL = -1;
/*     */   
/*     */   static void insertAfterVtx(Triangulator paramTriangulator, int paramInt) {
/*  74 */     if (paramTriangulator.vtxList == null) {
/*  75 */       int i = Math.max(paramTriangulator.numVtxList + 1, 100);
/*  76 */       paramTriangulator.vtxList = new PntNode[i];
/*  77 */     } else if (paramTriangulator.numVtxList >= paramTriangulator.vtxList.length) {
/*  78 */       int i = Math.max(paramTriangulator.numVtxList + 1, paramTriangulator.vtxList.length + 100);
/*     */       
/*  80 */       PntNode[] arrayOfPntNode = paramTriangulator.vtxList;
/*  81 */       paramTriangulator.vtxList = new PntNode[i];
/*  82 */       System.arraycopy(arrayOfPntNode, 0, paramTriangulator.vtxList, 0, arrayOfPntNode.length);
/*     */     } 
/*     */     
/*  85 */     paramTriangulator.vtxList[paramTriangulator.numVtxList] = new PntNode();
/*  86 */     (paramTriangulator.vtxList[paramTriangulator.numVtxList]).pnt = paramInt;
/*  87 */     (paramTriangulator.vtxList[paramTriangulator.numVtxList]).next = paramTriangulator.reflexVertices;
/*  88 */     paramTriangulator.reflexVertices = paramTriangulator.numVtxList;
/*  89 */     paramTriangulator.numVtxList++;
/*  90 */     paramTriangulator.numReflex++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void deleteFromList(Triangulator paramTriangulator, int paramInt) {
/*  97 */     if (paramTriangulator.numReflex == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 102 */     int i = paramTriangulator.reflexVertices;
/* 103 */     if (!inVtxList(paramTriangulator, i)) {
/* 104 */       System.out.println("NoHash:deleteFromList. Problem :Not is InVtxList ..." + i);
/*     */     }
/*     */     
/* 107 */     int j = (paramTriangulator.vtxList[i]).pnt;
/*     */     
/* 109 */     if (j == paramInt) {
/* 110 */       paramTriangulator.reflexVertices = (paramTriangulator.vtxList[i]).next;
/* 111 */       paramTriangulator.numReflex--;
/*     */     } else {
/*     */       
/* 114 */       int k = (paramTriangulator.vtxList[i]).next;
/* 115 */       while (k != -1) {
/* 116 */         if (!inVtxList(paramTriangulator, k)) {
/* 117 */           System.out.println("NoHash:deleteFromList. Problem :Not is InVtxList ..." + k);
/*     */         }
/*     */         
/* 120 */         j = (paramTriangulator.vtxList[k]).pnt;
/* 121 */         if (j == paramInt) {
/* 122 */           (paramTriangulator.vtxList[i]).next = (paramTriangulator.vtxList[k]).next;
/* 123 */           k = -1;
/* 124 */           paramTriangulator.numReflex--;
/*     */           continue;
/*     */         } 
/* 127 */         i = k;
/* 128 */         k = (paramTriangulator.vtxList[i]).next;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   static boolean inVtxList(Triangulator paramTriangulator, int paramInt) { return (0 <= paramInt && paramInt < paramTriangulator.numVtxList); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void freeNoHash(Triangulator paramTriangulator) {
/* 142 */     paramTriangulator.noHashingEdges = false;
/* 143 */     paramTriangulator.noHashingPnts = false;
/*     */     
/* 145 */     paramTriangulator.numVtxList = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void prepareNoHashEdges(Triangulator paramTriangulator, int paramInt1, int paramInt2) {
/* 152 */     paramTriangulator.loopMin = paramInt1;
/* 153 */     paramTriangulator.loopMax = paramInt2;
/*     */     
/* 155 */     paramTriangulator.noHashingEdges = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void prepareNoHashPnts(Triangulator paramTriangulator, int paramInt) {
/* 165 */     paramTriangulator.numVtxList = 0;
/* 166 */     paramTriangulator.reflexVertices = -1;
/*     */ 
/*     */     
/* 169 */     int i = paramTriangulator.loops[paramInt];
/* 170 */     int j = i;
/* 171 */     paramTriangulator.numReflex = 0;
/* 172 */     int k = paramTriangulator.fetchData(j);
/*     */     
/*     */     do {
/* 175 */       if (paramTriangulator.getAngle(j) < 0) {
/* 176 */         insertAfterVtx(paramTriangulator, j);
/*     */       }
/* 178 */       j = paramTriangulator.fetchNextData(j);
/* 179 */       k = paramTriangulator.fetchData(j);
/* 180 */     } while (j != i);
/*     */     
/* 182 */     paramTriangulator.noHashingPnts = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean noHashIntersectionExists(Triangulator paramTriangulator, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BBox paramBBox) {
/* 192 */     int[] arrayOfInt = new int[1];
/*     */ 
/*     */ 
/*     */     
/* 196 */     if (!paramTriangulator.noHashingPnts) {
/* 197 */       System.out.println("NoHash:noHashIntersectionExists noHashingPnts is false");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     if (paramTriangulator.numReflex <= 0) return false;
/*     */ 
/*     */ 
/*     */     
/* 207 */     if (paramInt1 < paramBBox.imin) { paramBBox.imin = paramInt1; }
/* 208 */     else if (paramInt1 > paramBBox.imax) { paramBBox.imax = paramInt1; }
/* 209 */      double d = (paramTriangulator.points[paramInt1]).y;
/* 210 */     if (d < paramBBox.ymin) { paramBBox.ymin = d; }
/* 211 */     else if (d > paramBBox.ymax) { paramBBox.ymax = d; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 216 */     int i = paramTriangulator.reflexVertices;
/* 217 */     boolean bool = false;
/*     */     
/*     */     do {
/* 220 */       int j = (paramTriangulator.vtxList[i]).pnt;
/*     */       
/* 222 */       int k = paramTriangulator.fetchData(j);
/*     */ 
/*     */       
/* 225 */       if (paramBBox.pntInBBox(paramTriangulator, k)) {
/*     */         
/* 227 */         int m = paramTriangulator.fetchNextData(j);
/* 228 */         int n = paramTriangulator.fetchData(m);
/* 229 */         if (j != paramInt2 && j != m)
/*     */         {
/*     */           
/* 232 */           if (k == paramInt1) {
/* 233 */             if (Degenerate.handleDegeneracies(paramTriangulator, paramInt1, paramInt2, paramInt3, paramInt4, k, j)) {
/* 234 */               return true;
/*     */             }
/* 236 */           } else if (k != paramInt3 && k != paramInt4) {
/* 237 */             bool = Numerics.vtxInTriangle(paramTriangulator, paramInt1, paramInt3, paramInt4, k, arrayOfInt);
/* 238 */             if (bool) return true; 
/*     */           } 
/*     */         }
/*     */       } 
/* 242 */       i = (paramTriangulator.vtxList[i]).next;
/*     */     }
/* 244 */     while (i != -1);
/*     */     
/* 246 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 254 */   static void deleteReflexVertex(Triangulator paramTriangulator, int paramInt) { deleteFromList(paramTriangulator, paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean noHashEdgeIntersectionExists(Triangulator paramTriangulator, BBox paramBBox, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 266 */     if (!paramTriangulator.noHashingEdges) {
/* 267 */       System.out.println("NoHash:noHashEdgeIntersectionExists noHashingEdges is false");
/*     */     }
/* 269 */     paramTriangulator.identCntr = 0;
/*     */ 
/*     */     
/* 272 */     for (int i = paramTriangulator.loopMin; i < paramTriangulator.loopMax; i++) {
/* 273 */       int j = paramTriangulator.loops[i];
/* 274 */       int k = j;
/* 275 */       int m = paramTriangulator.fetchData(k);
/*     */       
/*     */       do {
/* 278 */         k = paramTriangulator.fetchNextData(k);
/* 279 */         int n = paramTriangulator.fetchData(k);
/*     */         
/* 281 */         BBox bBox = new BBox(paramTriangulator, m, n);
/* 282 */         if (paramBBox.BBoxOverlap(bBox) && 
/* 283 */           Numerics.segIntersect(paramTriangulator, paramBBox.imin, paramBBox.imax, bBox.imin, bBox.imax, paramInt4)) {
/* 284 */           return true;
/*     */         }
/* 286 */         m = n;
/* 287 */       } while (k != j);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 292 */     if (paramTriangulator.identCntr >= 4) {
/* 293 */       if (BottleNeck.checkBottleNeck(paramTriangulator, paramInt4, paramInt1, paramInt2, paramInt3)) {
/* 294 */         return true;
/*     */       }
/* 296 */       return false;
/*     */     } 
/*     */     
/* 299 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\geometry\NoHash.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */