/*     */ package javax.media.j3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*  66 */       this.ints = new int[param1Int];
/*  67 */       this.count = 0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     IntList(int[] param1ArrayOfInt) {
/*  75 */       this.ints = param1ArrayOfInt;
/*  76 */       this.count = param1ArrayOfInt.length;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void add(int param1Int) {
/*  84 */       if (this.count == this.ints.length) {
/*  85 */         int[] arrayOfInt = new int[2 * this.count];
/*  86 */         System.arraycopy(this.ints, 0, arrayOfInt, 0, this.count);
/*  87 */         this.ints = arrayOfInt;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  93 */       this.ints[this.count++] = param1Int;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int[] trim() {
/* 101 */       if (this.count != this.ints.length) {
/* 102 */         int[] arrayOfInt = new int[this.count];
/* 103 */         System.arraycopy(this.ints, 0, arrayOfInt, 0, this.count);
/* 104 */         this.ints = arrayOfInt;
/*     */       } 
/* 106 */       return this.ints;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void fillAscending() {
/* 113 */       for (byte b = 0; b < this.ints.length; b++) {
/* 114 */         this.ints[b] = b;
/*     */       }
/* 116 */       this.count = this.ints.length;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 120 */       String str = new String("[");
/* 121 */       for (byte b = 0; b < this.count - 1; b++)
/* 122 */         str = str + Integer.toString(this.ints[b]) + ", "; 
/* 123 */       return str + Integer.toString(this.ints[this.count - 1]) + "]";
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
/* 150 */       this.vertices = param1IntList1;
/* 151 */       this.stripCounts = param1IntList2;
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
/* 169 */     int i = paramGeneralizedStripFlags.getFlagCount();
/*     */ 
/*     */     
/* 172 */     IntList intList1 = new IntList(i * 3);
/* 173 */     IntList intList2 = new IntList(i * 3);
/* 174 */     IntList intList3 = new IntList(i);
/* 175 */     IntList intList4 = new IntList(i);
/*     */     
/* 177 */     toStripsAndFans(paramGeneralizedStripFlags, paramInt, intList1, intList3, intList2, intList4);
/*     */ 
/*     */ 
/*     */     
/* 181 */     StripArray[] arrayOfStripArray = new StripArray[2];
/*     */     
/* 183 */     if (intList3.count > 0) {
/* 184 */       arrayOfStripArray[0] = new StripArray(intList1, intList3);
/*     */     }
/* 186 */     if (intList4.count > 0) {
/* 187 */       arrayOfStripArray[1] = new StripArray(intList2, intList4);
/*     */     }
/* 189 */     return arrayOfStripArray;
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
/* 200 */     boolean bool = false;
/*     */     
/* 202 */     byte b3 = 0;
/* 203 */     byte b4 = 3;
/* 204 */     int i = paramGeneralizedStripFlags.getFlag(0);
/* 205 */     byte b1 = (i == 0) ? 0 : 1;
/* 206 */     int j = paramGeneralizedStripFlags.getFlagCount();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 211 */     byte b2 = 3;
/* 212 */     if (b2 < j) {
/* 213 */       i = paramGeneralizedStripFlags.getFlag(b2);
/*     */     }
/* 215 */     while (b2 < j) {
/* 216 */       int k = paramGeneralizedStripFlags.getFlag(b2);
/*     */       
/* 218 */       if (k == i && k != 0 && k != 1) {
/*     */ 
/*     */ 
/*     */         
/* 222 */         b4++;
/* 223 */         b2++;
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 229 */       if (i == 2) {
/* 230 */         addFan(paramIntList3, paramIntList4, b3, b4, paramInt, b1, bool);
/*     */       } else {
/*     */         
/* 233 */         addStrip(paramIntList1, paramIntList2, b3, b4, paramInt, b1);
/*     */       } 
/*     */ 
/*     */       
/* 237 */       if (k == 0 || k == 1) {
/* 238 */         b1 = (k == 0) ? 0 : 1;
/* 239 */         b3 = b2;
/* 240 */         b4 = 3;
/* 241 */         b2 += 3;
/* 242 */         bool = false;
/* 243 */         if (b2 < j) {
/* 244 */           i = paramGeneralizedStripFlags.getFlag(b2);
/*     */         }
/*     */         continue;
/*     */       } 
/* 248 */       if (k == 3) {
/*     */ 
/*     */         
/* 251 */         b1 = (b1 == 0) ? 1 : 0;
/* 252 */         b3 = b2 - 2;
/* 253 */         b4 = 3;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 258 */         if ((b4 & true) == 0)
/* 259 */           b1 = (b1 == 0) ? 1 : 0; 
/* 260 */         b3 = b2 - 3;
/* 261 */         b4 = 4;
/*     */       } 
/* 263 */       b2++;
/* 264 */       bool = true;
/* 265 */       i = k;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 272 */     if (b2 == j) {
/* 273 */       if (i == 2) {
/* 274 */         addFan(paramIntList3, paramIntList4, b3, b4, paramInt, b1, bool);
/*     */       } else {
/*     */         
/* 277 */         addStrip(paramIntList1, paramIntList2, b3, b4, paramInt, b1);
/*     */       } 
/*     */     } else {
/* 280 */       throw new IllegalArgumentException(J3dI18N.getString("GeneralizedStrip0"));
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
/* 330 */     int i = paramInt1;
/*     */     
/* 332 */     if (paramInt4 == paramInt3) {
/*     */       
/* 334 */       paramIntList2.add(paramInt2);
/* 335 */       while (i < paramInt1 + paramInt2) {
/* 336 */         paramIntList1.add(i++);
/*     */       }
/* 338 */     } else if ((paramInt2 & true) == 1) {
/*     */       
/* 340 */       paramIntList2.add(paramInt2);
/* 341 */       i += paramInt2 - 1;
/* 342 */       while (i >= paramInt1) {
/* 343 */         paramIntList1.add(i--);
/*     */       }
/* 345 */     } else if (paramInt2 == 4) {
/*     */       
/* 347 */       paramIntList2.add(4);
/* 348 */       paramIntList1.add(i);
/* 349 */       paramIntList1.add(i + 2);
/* 350 */       paramIntList1.add(i + 1);
/* 351 */       paramIntList1.add(i + 3);
/*     */     } else {
/*     */       
/* 354 */       paramIntList2.add(3);
/* 355 */       paramIntList1.add(i);
/* 356 */       paramIntList1.add(i + 2);
/* 357 */       paramIntList1.add(i + 1);
/* 358 */       if (paramInt2 > 3) {
/*     */         
/* 360 */         i++;
/* 361 */         paramIntList2.add(paramInt2 - 1);
/* 362 */         while (i < paramInt1 + paramInt2) {
/* 363 */           paramIntList1.add(i++);
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
/* 374 */     int i = paramInt1;
/* 375 */     paramIntList1.add(i++);
/*     */     
/* 377 */     if (paramInt4 == paramInt3) {
/* 378 */       if (paramBoolean) {
/*     */         
/* 380 */         paramIntList2.add(paramInt2 - 1);
/* 381 */         i++;
/*     */       } else {
/* 383 */         paramIntList2.add(paramInt2);
/* 384 */         paramIntList1.add(i++);
/*     */       } 
/* 386 */       while (i < paramInt1 + paramInt2) {
/* 387 */         paramIntList1.add(i++);
/*     */       }
/*     */     } else {
/*     */       
/* 391 */       i += paramInt2 - 2;
/* 392 */       while (i > paramInt1 + 1) {
/* 393 */         paramIntList1.add(i--);
/*     */       }
/* 395 */       if (paramBoolean) {
/*     */         
/* 397 */         paramIntList2.add(paramInt2 - 1);
/*     */       } else {
/* 399 */         paramIntList2.add(paramInt2);
/* 400 */         paramIntList1.add(i);
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
/* 418 */     int i = paramGeneralizedStripFlags.getFlagCount();
/*     */ 
/*     */     
/* 421 */     IntList intList1 = new IntList(i * 3);
/* 422 */     IntList intList2 = new IntList(i * 3);
/* 423 */     IntList intList3 = new IntList(i);
/* 424 */     IntList intList4 = new IntList(i);
/*     */     
/* 426 */     toStripsAndFans(paramGeneralizedStripFlags, paramInt, intList1, intList3, intList2, intList4);
/*     */ 
/*     */     
/* 429 */     if (intList4.count == 0) {
/* 430 */       if (intList3.count > 0) {
/* 431 */         return new StripArray(intList1, intList3);
/*     */       }
/* 433 */       return null;
/*     */     } 
/*     */     
/* 436 */     int j = 0;
/* 437 */     for (byte b = 0; b < intList4.count; b++) {
/* 438 */       fanToStrips(j, intList4.ints[b], intList2.ints, intList1, intList3, false);
/*     */       
/* 440 */       j += intList4.ints[b];
/*     */     } 
/*     */ 
/*     */     
/* 444 */     return new StripArray(intList1, intList3);
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
/* 470 */     if (paramBoolean) {
/*     */       
/* 472 */       paramIntList2.add(paramInt2);
/* 473 */       paramIntList1.add(paramArrayOfInt[paramInt1]);
/*     */       
/* 475 */       int i = paramInt1 + 1;
/* 476 */       int j = paramInt1 + paramInt2 - 1;
/* 477 */       while (i <= j) {
/* 478 */         paramIntList1.add(paramArrayOfInt[i++]);
/* 479 */         if (i > j)
/* 480 */           break;  paramIntList1.add(paramArrayOfInt[j--]);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 487 */       int i = paramInt1;
/* 488 */       paramInt1++;
/* 489 */       while (paramInt1 + 4 <= i + paramInt2) {
/* 490 */         paramIntList1.add(paramArrayOfInt[paramInt1]);
/* 491 */         paramIntList1.add(paramArrayOfInt[paramInt1 + 1]);
/* 492 */         paramIntList1.add(paramArrayOfInt[i]);
/* 493 */         paramIntList1.add(paramArrayOfInt[paramInt1 + 2]);
/* 494 */         paramIntList1.add(paramArrayOfInt[paramInt1 + 3]);
/* 495 */         paramIntList2.add(5);
/* 496 */         paramInt1 += 3;
/*     */       } 
/*     */ 
/*     */       
/* 500 */       if (paramInt1 + 1 < i + paramInt2) {
/* 501 */         paramIntList1.add(paramArrayOfInt[paramInt1]);
/* 502 */         paramIntList1.add(paramArrayOfInt[paramInt1 + 1]);
/* 503 */         paramIntList1.add(paramArrayOfInt[i]);
/* 504 */         paramInt1++;
/*     */         
/* 506 */         if (paramInt1 + 1 < i + paramInt2) {
/* 507 */           paramIntList1.add(paramArrayOfInt[paramInt1 + 1]);
/* 508 */           paramIntList2.add(4);
/*     */         } else {
/*     */           
/* 511 */           paramIntList2.add(3);
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
/* 530 */     int i = 0;
/* 531 */     StripArray[] arrayOfStripArray = toStripsAndFans(paramGeneralizedStripFlags, paramInt);
/*     */     
/* 533 */     if (arrayOfStripArray[false] != null)
/* 534 */       i = 3 * getTriangleCount((arrayOfStripArray[0]).stripCounts); 
/* 535 */     if (arrayOfStripArray[true] != null) {
/* 536 */       i += 3 * getTriangleCount((arrayOfStripArray[1]).stripCounts);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 542 */     int j = 0;
/* 543 */     int[] arrayOfInt = new int[i];
/*     */     
/* 545 */     if (arrayOfStripArray[false] != null) {
/* 546 */       j = stripsToTriangles(j, arrayOfInt, 0, (arrayOfStripArray[0]).vertices.ints, 0, (arrayOfStripArray[0]).stripCounts.ints, (arrayOfStripArray[0]).stripCounts.count);
/*     */     }
/*     */ 
/*     */     
/* 550 */     if (arrayOfStripArray[true] != null) {
/* 551 */       j = fansToTriangles(j, arrayOfInt, 0, (arrayOfStripArray[1]).vertices.ints, 0, (arrayOfStripArray[1]).stripCounts.ints, (arrayOfStripArray[1]).stripCounts.count);
/*     */     }
/*     */ 
/*     */     
/* 555 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int stripsToTriangles(int paramInt1, int[] paramArrayOfInt1, int paramInt2, int[] paramArrayOfInt2, int paramInt3, int[] paramArrayOfInt3, int paramInt4) {
/* 562 */     int i = paramInt1;
/* 563 */     int j = paramInt2;
/* 564 */     for (int k = 0; k < paramInt4; k++) {
/* 565 */       for (byte b = 0; b < paramArrayOfInt3[k + paramInt3] - 2; b++) {
/* 566 */         if (!(b & true)) {
/*     */           
/* 568 */           paramArrayOfInt1[i * 3 + 0] = paramArrayOfInt2[j + 0];
/* 569 */           paramArrayOfInt1[i * 3 + 1] = paramArrayOfInt2[j + 1];
/* 570 */           paramArrayOfInt1[i * 3 + 2] = paramArrayOfInt2[j + 2];
/*     */         } else {
/*     */           
/* 573 */           paramArrayOfInt1[i * 3 + 0] = paramArrayOfInt2[j + 1];
/* 574 */           paramArrayOfInt1[i * 3 + 1] = paramArrayOfInt2[j + 0];
/* 575 */           paramArrayOfInt1[i * 3 + 2] = paramArrayOfInt2[j + 2];
/*     */         } 
/* 577 */         i++; j++;
/*     */       } 
/* 579 */       j += 2;
/*     */     } 
/* 581 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int fansToTriangles(int paramInt1, int[] paramArrayOfInt1, int paramInt2, int[] paramArrayOfInt2, int paramInt3, int[] paramArrayOfInt3, int paramInt4) {
/* 588 */     int i = paramInt1;
/* 589 */     int j = paramInt2;
/* 590 */     for (int k = 0; k < paramInt4; k++) {
/* 591 */       for (int m = 0; m < paramArrayOfInt3[k + paramInt3] - 2; m++) {
/* 592 */         paramArrayOfInt1[i * 3 + 0] = paramArrayOfInt2[j];
/* 593 */         paramArrayOfInt1[i * 3 + 1] = paramArrayOfInt2[j + m + 1];
/* 594 */         paramArrayOfInt1[i * 3 + 2] = paramArrayOfInt2[j + m + 2];
/* 595 */         i++;
/*     */       } 
/* 597 */       j += paramArrayOfInt3[k + paramInt3];
/*     */     } 
/* 599 */     return i;
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
/* 621 */     byte b1 = 0;
/* 622 */     int i = 0;
/* 623 */     byte b2 = 0;
/* 624 */     int j = 0;
/*     */     
/* 626 */     StripArray[] arrayOfStripArray = new StripArray[2];
/* 627 */     StripArray stripArray = toTriangleStrips(paramGeneralizedStripFlags, paramInt1);
/*     */     byte b3;
/* 629 */     for (b3 = 0; b3 < stripArray.stripCounts.count; b3++) {
/* 630 */       if (stripArray.stripCounts.ints[b3] <= paramInt2) {
/* 631 */         b2++;
/* 632 */         j += stripArray.stripCounts.ints[b3] - 2;
/*     */       } else {
/* 634 */         b1++;
/* 635 */         i += stripArray.stripCounts.ints[b3];
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 645 */     if (b2 <= paramInt3) {
/* 646 */       arrayOfStripArray[0] = stripArray;
/* 647 */       arrayOfStripArray[1] = null;
/*     */     } else {
/* 649 */       b3 = 0; int[] arrayOfInt1 = new int[i];
/* 650 */       byte b4 = 0; int[] arrayOfInt2 = new int[b1];
/* 651 */       int k = 0, arrayOfInt3[] = new int[3 * j];
/* 652 */       int m = 0;
/*     */       
/* 654 */       for (byte b5 = 0; b5 < stripArray.stripCounts.count; b5++) {
/* 655 */         if (stripArray.stripCounts.ints[b5] <= paramInt2) {
/* 656 */           k = stripsToTriangles(k, arrayOfInt3, m, stripArray.vertices.ints, b5, stripArray.stripCounts.ints, 1);
/*     */ 
/*     */           
/* 659 */           m += stripArray.stripCounts.ints[b5];
/*     */         } else {
/* 661 */           arrayOfInt2[b4++] = stripArray.stripCounts.ints[b5];
/* 662 */           for (byte b = 0; b < stripArray.stripCounts.ints[b5]; b++) {
/* 663 */             arrayOfInt1[b3++] = stripArray.vertices.ints[m++];
/*     */           }
/*     */         } 
/*     */       } 
/* 667 */       if (b1 > 0) {
/* 668 */         arrayOfStripArray[0] = new StripArray(new IntList(arrayOfInt1), new IntList(arrayOfInt2));
/*     */       } else {
/*     */         
/* 671 */         arrayOfStripArray[0] = null;
/*     */       } 
/* 673 */       arrayOfStripArray[1] = new StripArray(new IntList(arrayOfInt3), null);
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
/* 691 */     return arrayOfStripArray;
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
/* 707 */     byte b2 = 0;
/* 708 */     byte b3 = 2;
/* 709 */     int i = paramGeneralizedStripFlags.getFlagCount();
/*     */ 
/*     */     
/* 712 */     IntList intList1 = new IntList(i * 2);
/* 713 */     IntList intList2 = new IntList(i);
/*     */ 
/*     */     
/* 716 */     byte b1 = 2;
/* 717 */     while (b1 < i) {
/* 718 */       int j = paramGeneralizedStripFlags.getFlag(b1);
/*     */       
/* 720 */       if (j != 0 && j != 1) {
/*     */         
/* 722 */         b3++;
/* 723 */         b1++;
/*     */         
/*     */         continue;
/*     */       } 
/* 727 */       intList2.add(b3);
/* 728 */       for (byte b = b2; b < b2 + b3; b++) {
/* 729 */         intList1.add(b);
/*     */       }
/*     */       
/* 732 */       b2 = b1;
/* 733 */       b3 = 2;
/* 734 */       b1 += 2;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 740 */     if (b1 == i) {
/* 741 */       intList2.add(b3);
/* 742 */       for (byte b = b2; b < b2 + b3; b++)
/* 743 */         intList1.add(b); 
/*     */     } else {
/* 745 */       throw new IllegalArgumentException(J3dI18N.getString("GeneralizedStrip0"));
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
/* 764 */     if (intList2.count > 0) {
/* 765 */       return new StripArray(intList1, intList2);
/*     */     }
/* 767 */     return null;
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
/* 778 */     int i = 0;
/* 779 */     for (byte b = 0; b < paramArrayOfInt.length; b++)
/* 780 */       i += paramArrayOfInt[b] - 1; 
/* 781 */     return i;
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
/* 793 */     int i = 0;
/* 794 */     for (byte b = 0; b < paramArrayOfInt.length; b++)
/* 795 */       i += paramArrayOfInt[b] - 2; 
/* 796 */     return i;
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
/* 807 */     int i = 0;
/* 808 */     for (byte b = 0; b < paramIntList.count; b++)
/* 809 */       i += paramIntList.ints[b] - 2; 
/* 810 */     return i;
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
/* 822 */     int i = getTriangleCount(paramArrayOfInt);
/* 823 */     int[] arrayOfInt = new int[3 * i];
/* 824 */     IntList intList = new IntList(i + 2 * paramArrayOfInt.length);
/*     */     
/* 826 */     intList.fillAscending();
/* 827 */     stripsToTriangles(0, arrayOfInt, 0, intList.ints, 0, paramArrayOfInt, paramArrayOfInt.length);
/*     */ 
/*     */ 
/*     */     
/* 831 */     return arrayOfInt;
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
/* 843 */     int i = getTriangleCount(paramArrayOfInt);
/* 844 */     int[] arrayOfInt = new int[3 * i];
/* 845 */     IntList intList = new IntList(i + 2 * paramArrayOfInt.length);
/*     */     
/* 847 */     intList.fillAscending();
/* 848 */     fansToTriangles(0, arrayOfInt, 0, intList.ints, 0, paramArrayOfInt, paramArrayOfInt.length);
/*     */ 
/*     */ 
/*     */     
/* 852 */     return arrayOfInt;
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
/* 869 */     IntList intList1 = new IntList(paramInt2 * 3);
/* 870 */     IntList intList2 = new IntList(paramInt2);
/*     */     
/* 872 */     fanToStrips(paramInt1, paramInt2, paramArrayOfInt, intList1, intList2, paramBoolean);
/* 873 */     return new StripArray(intList1, intList2);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\GeneralizedStrip.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */