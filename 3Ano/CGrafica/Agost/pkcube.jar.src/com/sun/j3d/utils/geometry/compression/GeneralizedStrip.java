/*     */ package com.sun.j3d.utils.geometry.compression;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class GeneralizedStrip
/*     */ {
/*     */   private static final boolean debug = false;
/*     */   private static final int CW = 0;
/*     */   private static final int CCW = 1;
/*     */   private static final int RESTART_CW = 0;
/*     */   private static final int RESTART_CCW = 1;
/*     */   private static final int REPLACE_MIDDLE = 2;
/*     */   private static final int REPLACE_OLDEST = 3;
/*     */   
/*     */   static class IntList
/*     */   {
/*     */     int[] ints;
/*     */     int count;
/*     */     
/*     */     IntList(int param1Int) {
/*  99 */       this.ints = new int[param1Int];
/* 100 */       this.count = 0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     IntList(int[] param1ArrayOfInt) {
/* 108 */       this.ints = param1ArrayOfInt;
/* 109 */       this.count = param1ArrayOfInt.length;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void add(int param1Int) {
/* 117 */       if (this.count == this.ints.length) {
/* 118 */         int[] arrayOfInt = new int[2 * this.count];
/* 119 */         System.arraycopy(this.ints, 0, arrayOfInt, 0, this.count);
/* 120 */         this.ints = arrayOfInt;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 126 */       this.ints[this.count++] = param1Int;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int[] trim() {
/* 134 */       if (this.count != this.ints.length) {
/* 135 */         int[] arrayOfInt = new int[this.count];
/* 136 */         System.arraycopy(this.ints, 0, arrayOfInt, 0, this.count);
/* 137 */         this.ints = arrayOfInt;
/*     */       } 
/* 139 */       return this.ints;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void fillAscending() {
/* 146 */       for (byte b = 0; b < this.ints.length; b++) {
/* 147 */         this.ints[b] = b;
/*     */       }
/* 149 */       this.count = this.ints.length;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 153 */       String str = new String("[");
/* 154 */       for (byte b = 0; b < this.count - 1; b++)
/* 155 */         str = str + Integer.toString(this.ints[b]) + ", "; 
/* 156 */       return str + Integer.toString(this.ints[this.count - 1]) + "]";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class StripArray
/*     */   {
/*     */     GeneralizedStrip.IntList vertices;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     GeneralizedStrip.IntList stripCounts;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     StripArray(GeneralizedStrip.IntList param1IntList1, GeneralizedStrip.IntList param1IntList2) {
/* 183 */       this.vertices = param1IntList1;
/* 184 */       this.stripCounts = param1IntList2;
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
/*     */   static StripArray[] toStripsAndFans(GeneralizedStripFlags paramGeneralizedStripFlags, int paramInt) {
/* 202 */     int i = paramGeneralizedStripFlags.getFlagCount();
/*     */ 
/*     */     
/* 205 */     IntList intList1 = new IntList(i * 3);
/* 206 */     IntList intList2 = new IntList(i * 3);
/* 207 */     IntList intList3 = new IntList(i);
/* 208 */     IntList intList4 = new IntList(i);
/*     */     
/* 210 */     toStripsAndFans(paramGeneralizedStripFlags, paramInt, intList1, intList3, intList2, intList4);
/*     */ 
/*     */ 
/*     */     
/* 214 */     StripArray[] arrayOfStripArray = new StripArray[2];
/*     */     
/* 216 */     if (intList3.count > 0) {
/* 217 */       arrayOfStripArray[0] = new StripArray(intList1, intList3);
/*     */     }
/* 219 */     if (intList4.count > 0) {
/* 220 */       arrayOfStripArray[1] = new StripArray(intList2, intList4);
/*     */     }
/* 222 */     return arrayOfStripArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void toStripsAndFans(GeneralizedStripFlags paramGeneralizedStripFlags, int paramInt, IntList paramIntList1, IntList paramIntList2, IntList paramIntList3, IntList paramIntList4) {
/* 233 */     boolean bool = false;
/*     */     
/* 235 */     byte b3 = 0;
/* 236 */     byte b4 = 3;
/* 237 */     int i = paramGeneralizedStripFlags.getFlag(0);
/* 238 */     byte b1 = (i == 0) ? 0 : 1;
/* 239 */     int j = paramGeneralizedStripFlags.getFlagCount();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 244 */     byte b2 = 3;
/* 245 */     if (b2 < j) {
/* 246 */       i = paramGeneralizedStripFlags.getFlag(b2);
/*     */     }
/* 248 */     while (b2 < j) {
/* 249 */       int k = paramGeneralizedStripFlags.getFlag(b2);
/*     */       
/* 251 */       if (k == i && k != 0 && k != 1) {
/*     */ 
/*     */ 
/*     */         
/* 255 */         b4++;
/* 256 */         b2++;
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 262 */       if (i == 2) {
/* 263 */         addFan(paramIntList3, paramIntList4, b3, b4, paramInt, b1, bool);
/*     */       } else {
/*     */         
/* 266 */         addStrip(paramIntList1, paramIntList2, b3, b4, paramInt, b1);
/*     */       } 
/*     */ 
/*     */       
/* 270 */       if (k == 0 || k == 1) {
/* 271 */         b1 = (k == 0) ? 0 : 1;
/* 272 */         b3 = b2;
/* 273 */         b4 = 3;
/* 274 */         b2 += 3;
/* 275 */         bool = false;
/* 276 */         if (b2 < j) {
/* 277 */           i = paramGeneralizedStripFlags.getFlag(b2);
/*     */         }
/*     */         continue;
/*     */       } 
/* 281 */       if (k == 3) {
/*     */ 
/*     */         
/* 284 */         b1 = (b1 == 0) ? 1 : 0;
/* 285 */         b3 = b2 - 2;
/* 286 */         b4 = 3;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 291 */         if ((b4 & true) == 0)
/* 292 */           b1 = (b1 == 0) ? 1 : 0; 
/* 293 */         b3 = b2 - 3;
/* 294 */         b4 = 4;
/*     */       } 
/* 296 */       b2++;
/* 297 */       bool = true;
/* 298 */       i = k;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 305 */     if (b2 == j) {
/* 306 */       if (i == 2) {
/* 307 */         addFan(paramIntList3, paramIntList4, b3, b4, paramInt, b1, bool);
/*     */       } else {
/*     */         
/* 310 */         addStrip(paramIntList1, paramIntList2, b3, b4, paramInt, b1);
/*     */       } 
/*     */     } else {
/* 313 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeneralizedStrip0"));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addStrip(IntList paramIntList1, IntList paramIntList2, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 363 */     int i = paramInt1;
/*     */     
/* 365 */     if (paramInt4 == paramInt3) {
/*     */       
/* 367 */       paramIntList2.add(paramInt2);
/* 368 */       while (i < paramInt1 + paramInt2) {
/* 369 */         paramIntList1.add(i++);
/*     */       }
/* 371 */     } else if ((paramInt2 & true) == 1) {
/*     */       
/* 373 */       paramIntList2.add(paramInt2);
/* 374 */       i += paramInt2 - 1;
/* 375 */       while (i >= paramInt1) {
/* 376 */         paramIntList1.add(i--);
/*     */       }
/* 378 */     } else if (paramInt2 == 4) {
/*     */       
/* 380 */       paramIntList2.add(4);
/* 381 */       paramIntList1.add(i);
/* 382 */       paramIntList1.add(i + 2);
/* 383 */       paramIntList1.add(i + 1);
/* 384 */       paramIntList1.add(i + 3);
/*     */     } else {
/*     */       
/* 387 */       paramIntList2.add(3);
/* 388 */       paramIntList1.add(i);
/* 389 */       paramIntList1.add(i + 2);
/* 390 */       paramIntList1.add(i + 1);
/* 391 */       if (paramInt2 > 3) {
/*     */         
/* 393 */         i++;
/* 394 */         paramIntList2.add(paramInt2 - 1);
/* 395 */         while (i < paramInt1 + paramInt2) {
/* 396 */           paramIntList1.add(i++);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void addFan(IntList paramIntList1, IntList paramIntList2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
/* 407 */     int i = paramInt1;
/* 408 */     paramIntList1.add(i++);
/*     */     
/* 410 */     if (paramInt4 == paramInt3) {
/* 411 */       if (paramBoolean) {
/*     */         
/* 413 */         paramIntList2.add(paramInt2 - 1);
/* 414 */         i++;
/*     */       } else {
/* 416 */         paramIntList2.add(paramInt2);
/* 417 */         paramIntList1.add(i++);
/*     */       } 
/* 419 */       while (i < paramInt1 + paramInt2) {
/* 420 */         paramIntList1.add(i++);
/*     */       }
/*     */     } else {
/*     */       
/* 424 */       i += paramInt2 - 2;
/* 425 */       while (i > paramInt1 + 1) {
/* 426 */         paramIntList1.add(i--);
/*     */       }
/* 428 */       if (paramBoolean) {
/*     */         
/* 430 */         paramIntList2.add(paramInt2 - 1);
/*     */       } else {
/* 432 */         paramIntList2.add(paramInt2);
/* 433 */         paramIntList1.add(i);
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
/*     */   static StripArray toTriangleStrips(GeneralizedStripFlags paramGeneralizedStripFlags, int paramInt) {
/* 451 */     int i = paramGeneralizedStripFlags.getFlagCount();
/*     */ 
/*     */     
/* 454 */     IntList intList1 = new IntList(i * 3);
/* 455 */     IntList intList2 = new IntList(i * 3);
/* 456 */     IntList intList3 = new IntList(i);
/* 457 */     IntList intList4 = new IntList(i);
/*     */     
/* 459 */     toStripsAndFans(paramGeneralizedStripFlags, paramInt, intList1, intList3, intList2, intList4);
/*     */ 
/*     */     
/* 462 */     if (intList4.count == 0) {
/* 463 */       if (intList3.count > 0) {
/* 464 */         return new StripArray(intList1, intList3);
/*     */       }
/* 466 */       return null;
/*     */     } 
/*     */     
/* 469 */     int j = 0;
/* 470 */     for (byte b = 0; b < intList4.count; b++) {
/* 471 */       fanToStrips(j, intList4.ints[b], intList2.ints, intList1, intList3, false);
/*     */       
/* 473 */       j += intList4.ints[b];
/*     */     } 
/*     */ 
/*     */     
/* 477 */     return new StripArray(intList1, intList3);
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
/*     */   private static void fanToStrips(int paramInt1, int paramInt2, int[] paramArrayOfInt, IntList paramIntList1, IntList paramIntList2, boolean paramBoolean) {
/* 503 */     if (paramBoolean) {
/*     */       
/* 505 */       paramIntList2.add(paramInt2);
/* 506 */       paramIntList1.add(paramArrayOfInt[paramInt1]);
/*     */       
/* 508 */       int i = paramInt1 + 1;
/* 509 */       int j = paramInt1 + paramInt2 - 1;
/* 510 */       while (i <= j) {
/* 511 */         paramIntList1.add(paramArrayOfInt[i++]);
/* 512 */         if (i > j)
/* 513 */           break;  paramIntList1.add(paramArrayOfInt[j--]);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 520 */       int i = paramInt1;
/* 521 */       paramInt1++;
/* 522 */       while (paramInt1 + 4 <= i + paramInt2) {
/* 523 */         paramIntList1.add(paramArrayOfInt[paramInt1]);
/* 524 */         paramIntList1.add(paramArrayOfInt[paramInt1 + 1]);
/* 525 */         paramIntList1.add(paramArrayOfInt[i]);
/* 526 */         paramIntList1.add(paramArrayOfInt[paramInt1 + 2]);
/* 527 */         paramIntList1.add(paramArrayOfInt[paramInt1 + 3]);
/* 528 */         paramIntList2.add(5);
/* 529 */         paramInt1 += 3;
/*     */       } 
/*     */ 
/*     */       
/* 533 */       if (paramInt1 + 1 < i + paramInt2) {
/* 534 */         paramIntList1.add(paramArrayOfInt[paramInt1]);
/* 535 */         paramIntList1.add(paramArrayOfInt[paramInt1 + 1]);
/* 536 */         paramIntList1.add(paramArrayOfInt[i]);
/* 537 */         paramInt1++;
/*     */         
/* 539 */         if (paramInt1 + 1 < i + paramInt2) {
/* 540 */           paramIntList1.add(paramArrayOfInt[paramInt1 + 1]);
/* 541 */           paramIntList2.add(4);
/*     */         } else {
/*     */           
/* 544 */           paramIntList2.add(3);
/*     */         } 
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
/*     */   static int[] toTriangles(GeneralizedStripFlags paramGeneralizedStripFlags, int paramInt) {
/* 563 */     int i = 0;
/* 564 */     StripArray[] arrayOfStripArray = toStripsAndFans(paramGeneralizedStripFlags, paramInt);
/*     */     
/* 566 */     if (arrayOfStripArray[false] != null)
/* 567 */       i = 3 * getTriangleCount((arrayOfStripArray[0]).stripCounts); 
/* 568 */     if (arrayOfStripArray[true] != null) {
/* 569 */       i += 3 * getTriangleCount((arrayOfStripArray[1]).stripCounts);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 575 */     int j = 0;
/* 576 */     int[] arrayOfInt = new int[i];
/*     */     
/* 578 */     if (arrayOfStripArray[false] != null) {
/* 579 */       j = stripsToTriangles(j, arrayOfInt, 0, (arrayOfStripArray[0]).vertices.ints, 0, (arrayOfStripArray[0]).stripCounts.ints, (arrayOfStripArray[0]).stripCounts.count);
/*     */     }
/*     */ 
/*     */     
/* 583 */     if (arrayOfStripArray[true] != null) {
/* 584 */       j = fansToTriangles(j, arrayOfInt, 0, (arrayOfStripArray[1]).vertices.ints, 0, (arrayOfStripArray[1]).stripCounts.ints, (arrayOfStripArray[1]).stripCounts.count);
/*     */     }
/*     */ 
/*     */     
/* 588 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int stripsToTriangles(int paramInt1, int[] paramArrayOfInt1, int paramInt2, int[] paramArrayOfInt2, int paramInt3, int[] paramArrayOfInt3, int paramInt4) {
/* 595 */     int i = paramInt1;
/* 596 */     int j = paramInt2;
/* 597 */     for (int k = 0; k < paramInt4; k++) {
/* 598 */       for (byte b = 0; b < paramArrayOfInt3[k + paramInt3] - 2; b++) {
/* 599 */         if (!(b & true)) {
/*     */           
/* 601 */           paramArrayOfInt1[i * 3 + 0] = paramArrayOfInt2[j + 0];
/* 602 */           paramArrayOfInt1[i * 3 + 1] = paramArrayOfInt2[j + 1];
/* 603 */           paramArrayOfInt1[i * 3 + 2] = paramArrayOfInt2[j + 2];
/*     */         } else {
/*     */           
/* 606 */           paramArrayOfInt1[i * 3 + 0] = paramArrayOfInt2[j + 1];
/* 607 */           paramArrayOfInt1[i * 3 + 1] = paramArrayOfInt2[j + 0];
/* 608 */           paramArrayOfInt1[i * 3 + 2] = paramArrayOfInt2[j + 2];
/*     */         } 
/* 610 */         i++; j++;
/*     */       } 
/* 612 */       j += 2;
/*     */     } 
/* 614 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int fansToTriangles(int paramInt1, int[] paramArrayOfInt1, int paramInt2, int[] paramArrayOfInt2, int paramInt3, int[] paramArrayOfInt3, int paramInt4) {
/* 621 */     int i = paramInt1;
/* 622 */     int j = paramInt2;
/* 623 */     for (int k = 0; k < paramInt4; k++) {
/* 624 */       for (int m = 0; m < paramArrayOfInt3[k + paramInt3] - 2; m++) {
/* 625 */         paramArrayOfInt1[i * 3 + 0] = paramArrayOfInt2[j];
/* 626 */         paramArrayOfInt1[i * 3 + 1] = paramArrayOfInt2[j + m + 1];
/* 627 */         paramArrayOfInt1[i * 3 + 2] = paramArrayOfInt2[j + m + 2];
/* 628 */         i++;
/*     */       } 
/* 630 */       j += paramArrayOfInt3[k + paramInt3];
/*     */     } 
/* 632 */     return i;
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
/*     */   static StripArray[] toStripsAndTriangles(GeneralizedStripFlags paramGeneralizedStripFlags, int paramInt1, int paramInt2, int paramInt3) {
/* 654 */     byte b1 = 0;
/* 655 */     int i = 0;
/* 656 */     byte b2 = 0;
/* 657 */     int j = 0;
/*     */     
/* 659 */     StripArray[] arrayOfStripArray = new StripArray[2];
/* 660 */     StripArray stripArray = toTriangleStrips(paramGeneralizedStripFlags, paramInt1);
/*     */     byte b3;
/* 662 */     for (b3 = 0; b3 < stripArray.stripCounts.count; b3++) {
/* 663 */       if (stripArray.stripCounts.ints[b3] <= paramInt2) {
/* 664 */         b2++;
/* 665 */         j += stripArray.stripCounts.ints[b3] - 2;
/*     */       } else {
/* 667 */         b1++;
/* 668 */         i += stripArray.stripCounts.ints[b3];
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 678 */     if (b2 <= paramInt3) {
/* 679 */       arrayOfStripArray[0] = stripArray;
/* 680 */       arrayOfStripArray[1] = null;
/*     */     } else {
/* 682 */       b3 = 0; int[] arrayOfInt1 = new int[i];
/* 683 */       byte b4 = 0; int[] arrayOfInt2 = new int[b1];
/* 684 */       int k = 0, arrayOfInt3[] = new int[3 * j];
/* 685 */       int m = 0;
/*     */       
/* 687 */       for (byte b5 = 0; b5 < stripArray.stripCounts.count; b5++) {
/* 688 */         if (stripArray.stripCounts.ints[b5] <= paramInt2) {
/* 689 */           k = stripsToTriangles(k, arrayOfInt3, m, stripArray.vertices.ints, b5, stripArray.stripCounts.ints, 1);
/*     */ 
/*     */           
/* 692 */           m += stripArray.stripCounts.ints[b5];
/*     */         } else {
/* 694 */           arrayOfInt2[b4++] = stripArray.stripCounts.ints[b5];
/* 695 */           for (byte b = 0; b < stripArray.stripCounts.ints[b5]; b++) {
/* 696 */             arrayOfInt1[b3++] = stripArray.vertices.ints[m++];
/*     */           }
/*     */         } 
/*     */       } 
/* 700 */       if (b1 > 0) {
/* 701 */         arrayOfStripArray[0] = new StripArray(new IntList(arrayOfInt1), new IntList(arrayOfInt2));
/*     */       } else {
/*     */         
/* 704 */         arrayOfStripArray[0] = null;
/*     */       } 
/* 706 */       arrayOfStripArray[1] = new StripArray(new IntList(arrayOfInt3), null);
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
/* 724 */     return arrayOfStripArray;
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
/*     */   static StripArray toLineStrips(GeneralizedStripFlags paramGeneralizedStripFlags) {
/* 740 */     byte b2 = 0;
/* 741 */     byte b3 = 2;
/* 742 */     int i = paramGeneralizedStripFlags.getFlagCount();
/*     */ 
/*     */     
/* 745 */     IntList intList1 = new IntList(i * 2);
/* 746 */     IntList intList2 = new IntList(i);
/*     */ 
/*     */     
/* 749 */     byte b1 = 2;
/* 750 */     while (b1 < i) {
/* 751 */       int j = paramGeneralizedStripFlags.getFlag(b1);
/*     */       
/* 753 */       if (j != 0 && j != 1) {
/*     */         
/* 755 */         b3++;
/* 756 */         b1++;
/*     */         
/*     */         continue;
/*     */       } 
/* 760 */       intList2.add(b3);
/* 761 */       for (byte b = b2; b < b2 + b3; b++) {
/* 762 */         intList1.add(b);
/*     */       }
/*     */       
/* 765 */       b2 = b1;
/* 766 */       b3 = 2;
/* 767 */       b1 += 2;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 773 */     if (b1 == i) {
/* 774 */       intList2.add(b3);
/* 775 */       for (byte b = b2; b < b2 + b3; b++)
/* 776 */         intList1.add(b); 
/*     */     } else {
/* 778 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("GeneralizedStrip0"));
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
/* 797 */     if (intList2.count > 0) {
/* 798 */       return new StripArray(intList1, intList2);
/*     */     }
/* 800 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getLineCount(int[] paramArrayOfInt) {
/* 811 */     int i = 0;
/* 812 */     for (byte b = 0; b < paramArrayOfInt.length; b++)
/* 813 */       i += paramArrayOfInt[b] - 1; 
/* 814 */     return i;
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
/*     */   static int getTriangleCount(int[] paramArrayOfInt) {
/* 826 */     int i = 0;
/* 827 */     for (byte b = 0; b < paramArrayOfInt.length; b++)
/* 828 */       i += paramArrayOfInt[b] - 2; 
/* 829 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getTriangleCount(IntList paramIntList) {
/* 840 */     int i = 0;
/* 841 */     for (byte b = 0; b < paramIntList.count; b++)
/* 842 */       i += paramIntList.ints[b] - 2; 
/* 843 */     return i;
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
/*     */   static int[] stripsToTriangles(int[] paramArrayOfInt) {
/* 855 */     int i = getTriangleCount(paramArrayOfInt);
/* 856 */     int[] arrayOfInt = new int[3 * i];
/* 857 */     IntList intList = new IntList(i + 2 * paramArrayOfInt.length);
/*     */     
/* 859 */     intList.fillAscending();
/* 860 */     stripsToTriangles(0, arrayOfInt, 0, intList.ints, 0, paramArrayOfInt, paramArrayOfInt.length);
/*     */ 
/*     */ 
/*     */     
/* 864 */     return arrayOfInt;
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
/*     */   static int[] fansToTriangles(int[] paramArrayOfInt) {
/* 876 */     int i = getTriangleCount(paramArrayOfInt);
/* 877 */     int[] arrayOfInt = new int[3 * i];
/* 878 */     IntList intList = new IntList(i + 2 * paramArrayOfInt.length);
/*     */     
/* 880 */     intList.fillAscending();
/* 881 */     fansToTriangles(0, arrayOfInt, 0, intList.ints, 0, paramArrayOfInt, paramArrayOfInt.length);
/*     */ 
/*     */ 
/*     */     
/* 885 */     return arrayOfInt;
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
/*     */   static StripArray fanToStrips(int paramInt1, int paramInt2, int[] paramArrayOfInt, boolean paramBoolean) {
/* 902 */     IntList intList1 = new IntList(paramInt2 * 3);
/* 903 */     IntList intList2 = new IntList(paramInt2);
/*     */     
/* 905 */     fanToStrips(paramInt1, paramInt2, paramArrayOfInt, intList1, intList2, paramBoolean);
/* 906 */     return new StripArray(intList1, intList2);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\geometry\compression\GeneralizedStrip.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */