/*     */ package com.sun.j3d.utils.geometry;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StripifierStats
/*     */ {
/*  73 */   int numStrips = 0;
/*  74 */   int numVerts = 0;
/*  75 */   int minStripLen = 10000;
/*  76 */   int maxStripLen = 0;
/*  77 */   int totalTris = 0;
/*  78 */   int numFaces = 0;
/*  79 */   long time = 0L;
/*  80 */   int[] counts = new int[14];
/*     */ 
/*     */ 
/*     */   
/*     */   boolean noData = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public int getNumOrigTris() { return this.numFaces; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public int getNumOrigVerts() { return this.numFaces * 3; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public int getNumStrips() { return this.numStrips; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public int getNumVerts() { return this.numVerts; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public int getTotalTris() { return this.totalTris; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public int getMinStripLength() { return this.minStripLen; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public int getMaxStripLength() { return this.maxStripLen; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public double getAvgStripLength() { return this.totalTris / this.numStrips; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public double getAvgNumVertsPerTri() { return this.numVerts / this.totalTris; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public long getTotalTime() { return this.time; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public int[] getStripLengthCounts() { return this.counts; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 186 */     StringBuffer stringBuffer = new StringBuffer("num orig tris:        " + this.numFaces + "\n" + "num orig vertices:    " + (this.numFaces * 3) + "\n" + "number of strips:     " + this.numStrips + "\n" + "number of vertices:   " + this.numVerts + "\n" + "total tris:           " + this.totalTris + "\n" + "min strip length:     " + this.minStripLen + "\n" + "max strip length:     " + this.maxStripLen + "\n" + "avg strip length:     " + (this.totalTris / this.numStrips) + "\n" + "avg num verts/tri:    " + (this.numVerts / this.totalTris) + "\n" + "total time:           " + this.time + "\n" + "strip length distribution:\n");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 200 */     for (byte b = 0; b < 9; b++) {
/* 201 */       stringBuffer.append("  " + (b + true) + "=" + this.counts[b]);
/*     */     }
/* 203 */     stringBuffer.append("  10-19=" + this.counts[9]);
/* 204 */     stringBuffer.append("  20-49=" + this.counts[10]);
/* 205 */     stringBuffer.append("  50-99=" + this.counts[11]);
/* 206 */     stringBuffer.append("  100-999=" + this.counts[12]);
/* 207 */     stringBuffer.append("  1000 or more=" + this.counts[13] + "\n");
/*     */     
/* 209 */     return stringBuffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearData() {
/* 216 */     this.noData = true;
/*     */     
/* 218 */     this.numStrips = 0;
/* 219 */     this.numVerts = 0;
/* 220 */     this.minStripLen = 10000;
/* 221 */     this.maxStripLen = 0;
/* 222 */     this.totalTris = 0;
/* 223 */     this.numFaces = 0;
/* 224 */     this.time = 0L;
/* 225 */     this.counts = new int[14];
/*     */   }
/*     */ 
/*     */   
/*     */   void updateInfo(long paramLong, ArrayList paramArrayList, int paramInt) {
/* 230 */     this.noData = false;
/*     */     
/* 232 */     this.time += paramLong;
/* 233 */     this.numStrips += paramArrayList.size();
/* 234 */     int i = 0;
/* 235 */     int j = 10000;
/* 236 */     int k = 0;
/* 237 */     int m = 0;
/* 238 */     for (byte b = 0; b < paramArrayList.size(); b++) {
/* 239 */       Stripifier.Istream istream = (Stripifier.Istream)paramArrayList.get(b);
/* 240 */       int n = istream.length;
/* 241 */       int i1 = n - 2;
/* 242 */       i += n;
/* 243 */       if (i1 < j) j = i1; 
/* 244 */       if (i1 > k) k = i1; 
/* 245 */       m += i1;
/*     */ 
/*     */ 
/*     */       
/* 249 */       if (i1 <= 9) { this.counts[i1 - 1] = this.counts[i1 - 1] + 1; }
/*     */       
/* 251 */       else if (i1 < 20) { this.counts[9] = this.counts[9] + 1; }
/*     */       
/* 253 */       else if (i1 < 50) { this.counts[10] = this.counts[10] + 1; }
/*     */       
/* 255 */       else if (i1 < 100) { this.counts[11] = this.counts[11] + 1; }
/*     */       
/* 257 */       else if (i1 < 1000) { this.counts[12] = this.counts[12] + 1; }
/*     */       else
/* 259 */       { this.counts[13] = this.counts[13] + 1; }
/*     */     
/* 261 */     }  this.numVerts += i;
/* 262 */     if (j < this.minStripLen) this.minStripLen = j; 
/* 263 */     if (k > this.maxStripLen) this.maxStripLen = k; 
/* 264 */     this.totalTris += m;
/* 265 */     this.numFaces += paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void updateInfo(long paramLong, int paramInt1, int[] paramArrayOfInt, int paramInt2) {
/* 271 */     this.noData = false;
/*     */     
/* 273 */     this.time += paramLong;
/* 274 */     this.numStrips += paramInt1;
/* 275 */     int i = 0;
/* 276 */     int j = 10000;
/* 277 */     int k = 0;
/* 278 */     int m = 0;
/* 279 */     for (byte b = 0; b < paramInt1; b++) {
/* 280 */       int n = paramArrayOfInt[b];
/* 281 */       int i1 = n - 2;
/* 282 */       this.numVerts += n;
/* 283 */       if (i1 < j) j = i1; 
/* 284 */       if (i1 > k) k = i1; 
/* 285 */       this.totalTris += i1;
/*     */ 
/*     */ 
/*     */       
/* 289 */       if (i1 <= 9) { this.counts[i1 - 1] = this.counts[i1 - 1] + 1; }
/*     */       
/* 291 */       else if (i1 < 20) { this.counts[9] = this.counts[9] + 1; }
/*     */       
/* 293 */       else if (i1 < 50) { this.counts[10] = this.counts[10] + 1; }
/*     */       
/* 295 */       else if (i1 < 100) { this.counts[11] = this.counts[11] + 1; }
/*     */       
/* 297 */       else if (i1 < 1000) { this.counts[12] = this.counts[12] + 1; }
/*     */       else
/* 299 */       { this.counts[13] = this.counts[13] + 1; }
/*     */     
/* 301 */     }  this.numVerts += i;
/* 302 */     if (j < this.minStripLen) this.minStripLen = j; 
/* 303 */     if (k > this.maxStripLen) this.maxStripLen = k; 
/* 304 */     this.totalTris += m;
/* 305 */     this.numFaces += paramInt2;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\geometry\StripifierStats.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */