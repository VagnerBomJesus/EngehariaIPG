/*     */ package com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*     */ public class NormalGenerator
/*     */ {
/*     */   private double creaseAngle;
/*     */   private Vector3f[] facetNorms;
/*     */   private ArrayList tally;
/*     */   private GeometryInfo gi;
/*     */   private int[] coordInds;
/*     */   private int[] normalInds;
/*     */   private int[] colorInds;
/*     */   private int[][] texInds;
/*     */   private int[] stripCounts;
/*  85 */   private static long t1 = 0L; private static long t2 = 0L; private static long t3 = 0L; private static long t4 = 0L; private static long t5 = 0L; private static long t6 = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Triangulator tr;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int numTexSets;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int DEBUG = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculatefacetNorms() {
/* 107 */     Point3f[] arrayOfPoint3f = this.gi.getCoordinates();
/* 108 */     this.facetNorms = new Vector3f[this.coordInds.length / 3];
/* 109 */     Vector3f vector3f1 = new Vector3f();
/* 110 */     Vector3f vector3f2 = new Vector3f();
/*     */ 
/*     */     
/* 113 */     this.gi; if (this.gi.getOldPrim() != 2) {
/* 114 */       for (boolean bool = false; bool < this.coordInds.length; bool += true) {
/* 115 */         vector3f1.sub(arrayOfPoint3f[this.coordInds[bool + 2]], arrayOfPoint3f[this.coordInds[bool + true]]);
/* 116 */         vector3f2.sub(arrayOfPoint3f[this.coordInds[bool + false]], arrayOfPoint3f[this.coordInds[bool + true]]);
/* 117 */         this.facetNorms[bool / 3] = new Vector3f();
/* 118 */         this.facetNorms[bool / 3].cross(vector3f1, vector3f2);
/* 119 */         this.facetNorms[bool / 3].normalize();
/*     */         
/* 121 */         if (Float.isNaN((this.facetNorms[bool / 3]).x))
/*     */         {
/* 123 */           (this.facetNorms[bool / 3]).x = 1.0F;
/* 124 */           (this.facetNorms[bool / 3]).y = (this.facetNorms[bool / 3]).z = 0.0F;
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 133 */       for (boolean bool = false; bool < this.coordInds.length; bool += true) {
/* 134 */         vector3f1.sub(arrayOfPoint3f[this.coordInds[bool + 2]], arrayOfPoint3f[this.coordInds[bool + false]]);
/* 135 */         vector3f2.sub(arrayOfPoint3f[this.coordInds[bool + 5]], arrayOfPoint3f[this.coordInds[bool + true]]);
/* 136 */         this.facetNorms[bool / 3] = new Vector3f();
/* 137 */         this.facetNorms[bool / 3].cross(vector3f1, vector3f2);
/* 138 */         this.facetNorms[bool / 3].normalize();
/*     */         
/* 140 */         if (Float.isNaN((this.facetNorms[bool / 3]).x)) {
/*     */           
/* 142 */           (this.facetNorms[bool / 3]).x = 1.0F;
/* 143 */           (this.facetNorms[bool / 3]).y = (this.facetNorms[bool / 3]).z = 0.0F;
/*     */         } 
/*     */ 
/*     */         
/* 147 */         this.facetNorms[bool / 3 + 1] = new Vector3f(this.facetNorms[bool / 3]);
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int createHardEdges() {
/* 181 */     EdgeTable edgeTable = new EdgeTable(this.coordInds);
/* 182 */     this.tally = new ArrayList();
/* 183 */     int[] arrayOfInt = new int[this.coordInds.length];
/* 184 */     int i = 1;
/*     */ 
/*     */     
/* 187 */     float f = (float)Math.cos(this.creaseAngle);
/*     */     
/*     */     int j;
/*     */     
/* 191 */     for (j = 0; j < this.coordInds.length; j++) {
/* 192 */       arrayOfInt[j] = Integer.MAX_VALUE;
/*     */     }
/*     */     
/* 195 */     for (j = 0; j < this.coordInds.length; j++) {
/*     */       
/* 197 */       if (arrayOfInt[j] == Integer.MAX_VALUE) {
/*     */         boolean bool1;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 203 */         ArrayList arrayList = new ArrayList();
/* 204 */         this.tally.add(arrayList);
/*     */         
/* 206 */         arrayList.add(new Integer(j));
/*     */         
/* 208 */         arrayOfInt[j] = this.tally.size() - 1;
/*     */ 
/*     */         
/* 211 */         boolean bool2 = true;
/* 212 */         Edge edge = new Edge(this.coordInds[j], this.coordInds[((j + 1) % 3 == 0) ? (j - 2) : (j + 1)]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 218 */         int k = this.coordInds[(j % 3 == 0) ? (j + 2) : (j - 1)];
/*     */ 
/*     */         
/* 221 */         int m = j;
/*     */ 
/*     */ 
/*     */         
/*     */         do {
/* 226 */           Integer integer = edgeTable.get(edge.v2, edge.v1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 233 */           if (integer == null) {
/* 234 */             bool1 = false;
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 239 */             int n = integer.intValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 248 */             float f1 = this.facetNorms[m / 3].dot(this.facetNorms[n / 3]);
/* 249 */             bool1 = (f1 > f) ? 1 : 0;
/* 250 */             if (bool1) {
/*     */ 
/*     */ 
/*     */               
/* 254 */               int i1 = ((n + 1) % 3 == 0) ? (n - 2) : (n + 1);
/* 255 */               if (this.coordInds[j] != this.coordInds[i1]) {
/* 256 */                 i1 = (n % 3 == 0) ? (n + 2) : (n - 1);
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 262 */               if (arrayOfInt[i1] != Integer.MAX_VALUE) {
/* 263 */                 bool1 = false;
/*     */               
/*     */               }
/*     */               else {
/*     */                 
/* 268 */                 arrayOfInt[i1] = this.tally.size() - 1;
/*     */ 
/*     */ 
/*     */                 
/* 272 */                 arrayList.add(new Integer(i1));
/* 273 */                 if (arrayList.size() > i) i = arrayList.size();
/*     */ 
/*     */                 
/* 276 */                 m = n;
/* 277 */                 if (bool2) { edge.v2 = this.coordInds[m]; }
/* 278 */                 else { edge.v1 = this.coordInds[m]; }
/*     */               
/*     */               } 
/*     */             } 
/*     */           } 
/* 283 */           if (bool1 || !bool2) {
/*     */             continue;
/*     */           }
/*     */           
/* 287 */           bool2 = false;
/* 288 */           bool1 = true;
/* 289 */           m = j;
/*     */           
/* 291 */           edge = new Edge(this.coordInds[(j % 3 == 0) ? (j + 2) : (j - 1)], this.coordInds[j]);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 297 */         while (bool1 && ((bool2 && edge.v2 != k) || !bool2));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 322 */     return i;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calculateVertexNormals(int paramInt) {
/*     */     Vector3f[] arrayOfVector3f;
/* 351 */     if (this.creaseAngle != 0.0D) {
/* 352 */       Vector3f[] arrayOfVector3f1 = new Vector3f[paramInt];
/* 353 */       arrayOfVector3f = new Vector3f[this.tally.size()];
/* 354 */       this.normalInds = new int[this.coordInds.length];
/* 355 */       for (byte b = 0; b < this.tally.size(); b++) {
/* 356 */         ArrayList arrayList = (ArrayList)this.tally.get(b);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 361 */         byte b1 = 0;
/* 362 */         arrayOfVector3f[b] = new Vector3f();
/* 363 */         for (byte b2 = 0; b2 < arrayList.size(); b2++) {
/* 364 */           int i = ((Integer)arrayList.get(b2)).intValue();
/*     */           
/* 366 */           if (i != -1) {
/* 367 */             int j = i / 3;
/* 368 */             if (!Float.isNaN((this.facetNorms[j]).x)) {
/*     */               byte b3;
/*     */ 
/*     */               
/* 372 */               for (b3 = 0; b3 < b1 && 
/* 373 */                 !arrayOfVector3f1[b3].equals(this.facetNorms[j]); b3++);
/*     */ 
/*     */               
/* 376 */               this.normalInds[i] = b;
/* 377 */               if (b3 == b1) {
/*     */                 
/* 379 */                 arrayOfVector3f[b].add(this.facetNorms[j]);
/* 380 */                 arrayOfVector3f1[b1++] = this.facetNorms[j];
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 387 */         arrayOfVector3f[b].normalize();
/* 388 */         if (Float.isNaN((arrayOfVector3f[b]).x))
/*     */         {
/* 390 */           (arrayOfVector3f[b]).x = 1.0F; (arrayOfVector3f[b]).y = (arrayOfVector3f[b]).z = 0.0F;
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 406 */       arrayOfVector3f = this.facetNorms;
/*     */       
/* 408 */       this.normalInds = new int[this.facetNorms.length * 3];
/* 409 */       for (byte b = 0; b < this.facetNorms.length; b++) {
/* 410 */         this.normalInds[b * 3 + 0] = b;
/* 411 */         this.normalInds[b * 3 + 1] = b;
/* 412 */         this.normalInds[b * 3 + 2] = b;
/*     */       } 
/*     */     } 
/* 415 */     this.gi.setNormals(arrayOfVector3f);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] triToQuadIndices(int[] paramArrayOfInt) {
/* 440 */     if (paramArrayOfInt == null) return null;
/*     */     
/* 442 */     int[] arrayOfInt = new int[paramArrayOfInt.length / 6 * 4];
/*     */ 
/*     */     
/* 445 */     for (byte b = 0; b < paramArrayOfInt.length / 6; b++) {
/* 446 */       arrayOfInt[b * 4 + 0] = paramArrayOfInt[b * 6 + 0];
/* 447 */       arrayOfInt[b * 4 + 1] = paramArrayOfInt[b * 6 + 1];
/* 448 */       arrayOfInt[b * 4 + 2] = paramArrayOfInt[b * 6 + 2];
/* 449 */       arrayOfInt[b * 4 + 3] = paramArrayOfInt[b * 6 + 5];
/*     */     } 
/*     */     
/* 452 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void convertTriToQuad(GeometryInfo paramGeometryInfo) {
/* 462 */     paramGeometryInfo.setCoordinateIndices(triToQuadIndices(paramGeometryInfo.getCoordinateIndices()));
/*     */     
/* 464 */     paramGeometryInfo.setColorIndices(triToQuadIndices(paramGeometryInfo.getColorIndices()));
/* 465 */     paramGeometryInfo.setNormalIndices(triToQuadIndices(paramGeometryInfo.getNormalIndices()));
/* 466 */     int i = paramGeometryInfo.getTexCoordSetCount();
/* 467 */     for (byte b = 0; b < i; b++) {
/* 468 */       paramGeometryInfo.setTextureCoordinateIndices(b, triToQuadIndices(paramGeometryInfo.getTextureCoordinateIndices(b)));
/*     */     }
/*     */     
/* 471 */     this.gi; paramGeometryInfo.setPrimitive(2);
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
/*     */   private int[] triToFanIndices(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt) {
/* 486 */     if (paramArrayOfInt2 == null) return null;
/*     */     
/* 488 */     int[] arrayOfInt = new int[paramInt];
/* 489 */     byte b1 = 0;
/* 490 */     byte b2 = 0;
/*     */     
/* 492 */     for (byte b3 = 0; b3 < paramArrayOfInt1.length; b3++) {
/*     */       
/* 494 */       arrayOfInt[b2++] = paramArrayOfInt2[b1++];
/* 495 */       arrayOfInt[b2++] = paramArrayOfInt2[b1++];
/* 496 */       arrayOfInt[b2++] = paramArrayOfInt2[b1++];
/*     */       
/* 498 */       for (byte b = 3; b < paramArrayOfInt1[b3]; b++) {
/* 499 */         arrayOfInt[b2++] = paramArrayOfInt2[b1 + 2];
/* 500 */         b1 += 3;
/*     */       } 
/*     */     } 
/* 503 */     return arrayOfInt;
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
/*     */   private void convertTriToFan(GeometryInfo paramGeometryInfo, int[] paramArrayOfInt) {
/* 517 */     int[] arrayOfInt1 = paramGeometryInfo.getNormalIndices();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 522 */     byte b1 = 0;
/*     */     
/* 524 */     ArrayList arrayList = new ArrayList(paramArrayOfInt.length + 100);
/*     */ 
/*     */     
/* 527 */     for (byte b2 = 0; b2 < paramArrayOfInt.length; b2++) {
/* 528 */       byte b4 = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 534 */       for (byte b5 = 0; b5 < paramArrayOfInt[b2] - 3; b5++) {
/*     */ 
/*     */ 
/*     */         
/* 538 */         if (arrayOfInt1[b1 * 3] == arrayOfInt1[(b1 + true) * 3] && arrayOfInt1[b1 * 3 + 2] == arrayOfInt1[(b1 + true) * 3 + 1]) {
/*     */ 
/*     */           
/* 541 */           b4++;
/*     */         } else {
/*     */           
/* 544 */           arrayList.add(new Integer(b4));
/* 545 */           b4 = 3;
/*     */         } 
/* 547 */         b1++;
/*     */       } 
/* 549 */       b1++;
/* 550 */       arrayList.add(new Integer(b4));
/*     */     } 
/*     */ 
/*     */     
/* 554 */     int[] arrayOfInt2 = new int[arrayList.size()]; int i;
/* 555 */     for (i = 0; i < arrayOfInt2.length; i++)
/* 556 */       arrayOfInt2[i] = ((Integer)arrayList.get(i)).intValue(); 
/* 557 */     arrayList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 564 */     i = 0; int j;
/* 565 */     for (j = 0; j < arrayOfInt2.length; ) { i += arrayOfInt2[j]; j++; }
/*     */ 
/*     */     
/* 568 */     paramGeometryInfo.setCoordinateIndices(triToFanIndices(arrayOfInt2, paramGeometryInfo.getCoordinateIndices(), i));
/*     */     
/* 570 */     paramGeometryInfo.setColorIndices(triToFanIndices(arrayOfInt2, paramGeometryInfo.getColorIndices(), i));
/* 571 */     paramGeometryInfo.setNormalIndices(triToFanIndices(arrayOfInt2, paramGeometryInfo.getNormalIndices(), i));
/* 572 */     j = paramGeometryInfo.getTexCoordSetCount();
/* 573 */     for (byte b3 = 0; b3 < j; b3++) {
/* 574 */       paramGeometryInfo.setTextureCoordinateIndices(b3, triToFanIndices(arrayOfInt2, paramGeometryInfo.getTextureCoordinateIndices(b3), i));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 591 */     paramGeometryInfo.setStripCounts(arrayOfInt2);
/* 592 */     this.gi; paramGeometryInfo.setPrimitive(3);
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
/*     */   private int[] triToStripIndices(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt) {
/* 607 */     if (paramArrayOfInt2 == null) return null;
/*     */     
/* 609 */     int[] arrayOfInt = new int[paramInt];
/* 610 */     byte b1 = 0;
/* 611 */     byte b2 = 0;
/*     */     
/* 613 */     for (byte b3 = 0; b3 < paramArrayOfInt1.length; b3++) {
/*     */       
/* 615 */       arrayOfInt[b2++] = paramArrayOfInt2[b1++];
/* 616 */       arrayOfInt[b2++] = paramArrayOfInt2[b1++];
/* 617 */       arrayOfInt[b2++] = paramArrayOfInt2[b1++];
/*     */       
/* 619 */       for (byte b = 3; b < paramArrayOfInt1[b3]; b++) {
/*     */         
/* 621 */         arrayOfInt[b2++] = paramArrayOfInt2[b1 + 2 - b % 2];
/* 622 */         b1 += 3;
/*     */       } 
/*     */     } 
/* 625 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void convertTriToStrip(GeometryInfo paramGeometryInfo, int[] paramArrayOfInt) {
/* 632 */     int[] arrayOfInt1 = paramGeometryInfo.getNormalIndices();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 637 */     byte b1 = 0;
/*     */     
/* 639 */     ArrayList arrayList = new ArrayList(paramArrayOfInt.length + 100);
/*     */ 
/*     */     
/* 642 */     for (byte b2 = 0; b2 < paramArrayOfInt.length; b2++) {
/* 643 */       byte b4 = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 649 */       for (byte b5 = 0; b5 < paramArrayOfInt[b2] - 3; b5++) {
/*     */         
/* 651 */         if (b5 % 2 == 0) {
/*     */ 
/*     */ 
/*     */           
/* 655 */           if (arrayOfInt1[b1 * 3 + 1] == arrayOfInt1[(b1 + true) * 3] && arrayOfInt1[b1 * 3 + 2] == arrayOfInt1[(b1 + true) * 3 + 2]) {
/*     */ 
/*     */             
/* 658 */             b4++;
/*     */           } else {
/*     */             
/* 661 */             arrayList.add(new Integer(b4));
/* 662 */             b4 = 3;
/*     */ 
/*     */ 
/*     */             
/* 666 */             if (b5 < paramArrayOfInt[b2] - 4) {
/* 667 */               arrayList.add(new Integer(3));
/* 668 */               b5++;
/*     */             
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 675 */         else if (arrayOfInt1[b1 * 3 + 1] == arrayOfInt1[(b1 + true) * 3 + 1] && arrayOfInt1[b1 * 3 + 2] == arrayOfInt1[(b1 + true) * 3]) {
/*     */ 
/*     */           
/* 678 */           b4++;
/*     */         } else {
/*     */           
/* 681 */           arrayList.add(new Integer(b4));
/* 682 */           b4 = 3;
/*     */         } 
/*     */         
/* 685 */         b1++;
/*     */       } 
/* 687 */       b1++;
/* 688 */       arrayList.add(new Integer(b4));
/*     */     } 
/*     */ 
/*     */     
/* 692 */     int[] arrayOfInt2 = new int[arrayList.size()]; int i;
/* 693 */     for (i = 0; i < arrayOfInt2.length; i++)
/* 694 */       arrayOfInt2[i] = ((Integer)arrayList.get(i)).intValue(); 
/* 695 */     arrayList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 702 */     i = 0; int j;
/* 703 */     for (j = 0; j < arrayOfInt2.length; ) { i += arrayOfInt2[j]; j++; }
/*     */ 
/*     */     
/* 706 */     paramGeometryInfo.setCoordinateIndices(triToStripIndices(arrayOfInt2, paramGeometryInfo.getCoordinateIndices(), i));
/*     */     
/* 708 */     paramGeometryInfo.setColorIndices(triToStripIndices(arrayOfInt2, paramGeometryInfo.getColorIndices(), i));
/* 709 */     paramGeometryInfo.setNormalIndices(triToStripIndices(arrayOfInt2, paramGeometryInfo.getNormalIndices(), i));
/* 710 */     j = paramGeometryInfo.getTexCoordSetCount();
/* 711 */     for (byte b3 = 0; b3 < j; b3++) {
/* 712 */       paramGeometryInfo.setTextureCoordinateIndices(b3, triToStripIndices(arrayOfInt2, paramGeometryInfo.getTextureCoordinateIndices(b3), i));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 729 */     paramGeometryInfo.setStripCounts(arrayOfInt2);
/* 730 */     this.gi; paramGeometryInfo.setPrimitive(4);
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
/*     */   void convertBackToOldPrim(GeometryInfo paramGeometryInfo, int paramInt, int[] paramArrayOfInt) {
/* 745 */     paramGeometryInfo; if (paramInt == 1)
/*     */       return; 
/* 747 */     switch (paramInt) {
/*     */       case 2:
/* 749 */         convertTriToQuad(paramGeometryInfo);
/*     */         break;
/*     */       case 3:
/* 752 */         convertTriToFan(paramGeometryInfo, paramArrayOfInt);
/*     */         break;
/*     */       case 4:
/* 755 */         convertTriToStrip(paramGeometryInfo, paramArrayOfInt);
/*     */         break;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateNormals(GeometryInfo paramGeometryInfo) {
/* 781 */     this.gi = paramGeometryInfo;
/* 782 */     this.gi.setNormals((Vector3f[])null);
/* 783 */     this.gi.setNormalIndices(null);
/*     */     
/* 785 */     long l = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 790 */     this.gi; if (this.gi.getPrimitive() == 5) {
/* 791 */       if (this.tr == null) this.tr = new Triangulator(); 
/* 792 */       this.tr.triangulate(this.gi);
/*     */     }
/*     */     else {
/*     */       
/* 796 */       this.gi.rememberOldPrim();
/* 797 */       this.gi.convertToIndexedTriangles();
/*     */     } 
/*     */ 
/*     */     
/* 801 */     this.coordInds = this.gi.getCoordinateIndices();
/* 802 */     this.colorInds = this.gi.getColorIndices();
/* 803 */     this.normalInds = this.gi.getNormalIndices();
/* 804 */     this.numTexSets = this.gi.getTexCoordSetCount();
/* 805 */     this.texInds = new int[this.numTexSets][]; int i;
/* 806 */     for (i = 0; i < this.numTexSets; i++) {
/* 807 */       this.texInds[i] = this.gi.getTextureCoordinateIndices(i);
/*     */     }
/* 809 */     this.stripCounts = this.gi.getStripCounts();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 817 */     calculatefacetNorms();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 824 */     i = createHardEdges();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 831 */     calculateVertexNormals(i);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 847 */     this.gi.setCoordinateIndices(this.coordInds);
/* 848 */     this.gi.setColorIndices(this.colorInds);
/* 849 */     this.gi.setNormalIndices(this.normalInds);
/* 850 */     for (byte b = 0; b < this.numTexSets; b++) {
/* 851 */       this.gi.setTextureCoordinateIndices(b, this.texInds[b]);
/*     */     }
/* 853 */     this.gi.setStripCounts(this.stripCounts);
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
/*     */   public void setCreaseAngle(double paramDouble) {
/* 869 */     if (paramDouble > Math.PI) paramDouble = Math.PI; 
/* 870 */     if (paramDouble < 0.0D) paramDouble = 0.0D; 
/* 871 */     this.creaseAngle = paramDouble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 881 */   public double getCreaseAngle() { return this.creaseAngle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NormalGenerator(double paramDouble) {
/*     */     this.tr = null;
/* 892 */     this.creaseAngle = paramDouble;
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
/* 903 */   public NormalGenerator() { this(0.767944870877505D); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\geometry\NormalGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */