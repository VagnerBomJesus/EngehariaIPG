/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Matrix4f;
/*     */ import javax.vecmath.Point2f;
/*     */ import javax.vecmath.Point2i;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Point3i;
/*     */ import javax.vecmath.Point4f;
/*     */ import javax.vecmath.Point4i;
/*     */ import javax.vecmath.Tuple2f;
/*     */ import javax.vecmath.Tuple2i;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Tuple3i;
/*     */ import javax.vecmath.Tuple4f;
/*     */ import javax.vecmath.Tuple4i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ShaderAttributeArrayRetained
/*     */   extends ShaderAttributeObjectRetained
/*     */ {
/*  33 */   void initValue(int paramInt, Object paramObject) { ((ArrayWrapper)this.attrWrapper).set(paramInt, paramObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setValue(int paramInt, Object paramObject) {
/*  55 */     initValue(paramInt, paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     setValue(this.attrWrapper.getRef());
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
/*  75 */   int length() { return ((ArrayWrapper)this.attrWrapper).length(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createMirrorObject() {
/*  84 */     if (this.mirror == null) {
/*  85 */       ShaderAttributeArrayRetained shaderAttributeArrayRetained = new ShaderAttributeArrayRetained();
/*  86 */       shaderAttributeArrayRetained.createObjectData(getValue());
/*  87 */       this.mirror = shaderAttributeArrayRetained;
/*  88 */       this.mirror.source = this.source;
/*     */     } 
/*     */     
/*  91 */     initMirrorObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int computeClassType(Object paramObject) {
/* 101 */     Class clazz = paramObject.getClass();
/* 102 */     if (!clazz.isArray()) {
/* 103 */       throw new ClassCastException(clazz + " -- must be array class");
/*     */     }
/*     */     
/* 106 */     for (byte b = 0; b < classTable.length; b++) {
/* 107 */       if (classTableArr[b].isInstance(paramObject)) {
/* 108 */         return b;
/*     */       }
/*     */     } 
/* 111 */     throw new ClassCastException(clazz + " -- unrecognized class");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   Class getBaseClass(int paramInt) { return classTableArr[paramInt]; } ShaderAttributeObjectRetained.AttrWrapper createAttrWrapper(Object paramObject, int paramInt) { Tuple2iArrayWrapper tuple2iArrayWrapper; Tuple4fArrayWrapper tuple4fArrayWrapper;
/*     */     Matrix4fArrayWrapper matrix4fArrayWrapper;
/*     */     Tuple3fArrayWrapper tuple3fArrayWrapper;
/*     */     Tuple4iArrayWrapper tuple4iArrayWrapper;
/*     */     Tuple2fArrayWrapper tuple2fArrayWrapper;
/*     */     FloatArrayWrapper floatArrayWrapper;
/*     */     Tuple3iArrayWrapper tuple3iArrayWrapper;
/*     */     Matrix3fArrayWrapper matrix3fArrayWrapper;
/* 126 */     IntegerArrayWrapper integerArrayWrapper = null;
/* 127 */     switch (paramInt) {
/*     */       case 0:
/* 129 */         integerArrayWrapper = new IntegerArrayWrapper();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 182 */         integerArrayWrapper.set(paramObject);
/* 183 */         return integerArrayWrapper;case 1: floatArrayWrapper = new FloatArrayWrapper(); floatArrayWrapper.set(paramObject); return floatArrayWrapper;case 2: tuple2iArrayWrapper = new Tuple2iArrayWrapper(); tuple2iArrayWrapper.set(paramObject); return tuple2iArrayWrapper;case 3: tuple2fArrayWrapper = new Tuple2fArrayWrapper(); tuple2fArrayWrapper.set(paramObject); return tuple2fArrayWrapper;case 4: tuple3iArrayWrapper = new Tuple3iArrayWrapper(); tuple3iArrayWrapper.set(paramObject); return tuple3iArrayWrapper;case 5: tuple3fArrayWrapper = new Tuple3fArrayWrapper(); tuple3fArrayWrapper.set(paramObject); return tuple3fArrayWrapper;case 6: tuple4iArrayWrapper = new Tuple4iArrayWrapper(); tuple4iArrayWrapper.set(paramObject); return tuple4iArrayWrapper;case 7: tuple4fArrayWrapper = new Tuple4fArrayWrapper(); tuple4fArrayWrapper.set(paramObject); return tuple4fArrayWrapper;case 8: matrix3fArrayWrapper = new Matrix3fArrayWrapper(); matrix3fArrayWrapper.set(paramObject); return matrix3fArrayWrapper;case 9: matrix4fArrayWrapper = new Matrix4fArrayWrapper(); matrix4fArrayWrapper.set(paramObject); return matrix4fArrayWrapper;
/*     */     } 
/*     */     assert false;
/*     */     return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static abstract class ArrayWrapper
/*     */     extends ShaderAttributeObjectRetained.AttrWrapper
/*     */   {
/* 195 */     int length = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 201 */     int length() { return this.length; }
/*     */ 
/*     */ 
/*     */     
/*     */     abstract void set(int param1Int, Object param1Object);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class IntegerArrayWrapper
/*     */     extends ArrayWrapper
/*     */   {
/* 213 */     private int[] value = new int[0];
/*     */     
/*     */     void set(Object param1Object) {
/* 216 */       Integer[] arrayOfInteger = (Integer[])param1Object;
/* 217 */       if (this.length != arrayOfInteger.length) {
/* 218 */         this.length = arrayOfInteger.length;
/* 219 */         this.value = new int[this.length];
/*     */       } 
/* 221 */       for (byte b = 0; b < this.length; b++) {
/* 222 */         this.value[b] = arrayOfInteger[b].intValue();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 227 */     void set(int param1Int, Object param1Object) { this.value[param1Int] = ((Integer)param1Object).intValue(); }
/*     */ 
/*     */     
/*     */     Object get() {
/* 231 */       Integer[] arrayOfInteger = new Integer[this.length];
/* 232 */       for (byte b = 0; b < this.length; b++) {
/* 233 */         arrayOfInteger[b] = new Integer(this.value[b]);
/*     */       }
/* 235 */       return arrayOfInteger;
/*     */     }
/*     */ 
/*     */     
/* 239 */     Object getRef() { return this.value; }
/*     */   }
/*     */   
/*     */   static class FloatArrayWrapper
/*     */     extends ArrayWrapper
/*     */   {
/* 245 */     private float[] value = new float[0];
/*     */     
/*     */     void set(Object param1Object) {
/* 248 */       Float[] arrayOfFloat = (Float[])param1Object;
/* 249 */       if (this.length != arrayOfFloat.length) {
/* 250 */         this.length = arrayOfFloat.length;
/* 251 */         this.value = new float[this.length];
/*     */       } 
/* 253 */       for (byte b = 0; b < this.length; b++) {
/* 254 */         this.value[b] = arrayOfFloat[b].floatValue();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 259 */     void set(int param1Int, Object param1Object) { this.value[param1Int] = ((Float)param1Object).floatValue(); }
/*     */ 
/*     */     
/*     */     Object get() {
/* 263 */       Float[] arrayOfFloat = new Float[this.length];
/* 264 */       for (byte b = 0; b < this.length; b++) {
/* 265 */         arrayOfFloat[b] = new Float(this.value[b]);
/*     */       }
/* 267 */       return arrayOfFloat;
/*     */     }
/*     */ 
/*     */     
/* 271 */     Object getRef() { return this.value; }
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
/*     */   static class Tuple2iArrayWrapper
/*     */     extends ArrayWrapper
/*     */   {
/* 311 */     private int[] value = new int[0];
/*     */     
/*     */     void set(Object param1Object) {
/* 314 */       Tuple2i[] arrayOfTuple2i = (Tuple2i[])param1Object;
/* 315 */       if (this.length != arrayOfTuple2i.length) {
/* 316 */         this.length = arrayOfTuple2i.length;
/* 317 */         this.value = new int[this.length * 2];
/*     */       } 
/* 319 */       for (byte b = 0; b < this.length; b++) {
/* 320 */         byte b1 = b * 2;
/* 321 */         this.value[b1 + 0] = (arrayOfTuple2i[b]).x;
/* 322 */         this.value[b1 + 1] = (arrayOfTuple2i[b]).y;
/*     */       } 
/*     */     }
/*     */     
/*     */     void set(int param1Int, Object param1Object) {
/* 327 */       int i = param1Int * 2;
/* 328 */       this.value[i + 0] = ((Tuple2i)param1Object).x;
/* 329 */       this.value[i + 1] = ((Tuple2i)param1Object).y;
/*     */     }
/*     */     
/*     */     Object get() {
/* 333 */       Tuple2i[] arrayOfTuple2i = new Tuple2i[this.length];
/* 334 */       for (byte b = 0; b < this.length; b++) {
/* 335 */         byte b1 = b * 2;
/* 336 */         arrayOfTuple2i[b] = new Point2i();
/* 337 */         (arrayOfTuple2i[b]).x = this.value[b1 + 0];
/* 338 */         (arrayOfTuple2i[b]).y = this.value[b1 + 1];
/*     */       } 
/* 340 */       return arrayOfTuple2i;
/*     */     }
/*     */ 
/*     */     
/* 344 */     Object getRef() { return this.value; }
/*     */   }
/*     */   
/*     */   static class Tuple2fArrayWrapper
/*     */     extends ArrayWrapper
/*     */   {
/* 350 */     private float[] value = new float[0];
/*     */     
/*     */     void set(Object param1Object) {
/* 353 */       Tuple2f[] arrayOfTuple2f = (Tuple2f[])param1Object;
/* 354 */       if (this.length != arrayOfTuple2f.length) {
/* 355 */         this.length = arrayOfTuple2f.length;
/* 356 */         this.value = new float[this.length * 2];
/*     */       } 
/* 358 */       for (byte b = 0; b < this.length; b++) {
/* 359 */         byte b1 = b * 2;
/* 360 */         this.value[b1 + 0] = (arrayOfTuple2f[b]).x;
/* 361 */         this.value[b1 + 1] = (arrayOfTuple2f[b]).y;
/*     */       } 
/*     */     }
/*     */     
/*     */     void set(int param1Int, Object param1Object) {
/* 366 */       int i = param1Int * 2;
/* 367 */       this.value[i + 0] = ((Tuple2f)param1Object).x;
/* 368 */       this.value[i + 1] = ((Tuple2f)param1Object).y;
/*     */     }
/*     */     
/*     */     Object get() {
/* 372 */       Tuple2f[] arrayOfTuple2f = new Tuple2f[this.length];
/* 373 */       for (byte b = 0; b < this.length; b++) {
/* 374 */         byte b1 = b * 2;
/* 375 */         arrayOfTuple2f[b] = new Point2f();
/* 376 */         (arrayOfTuple2f[b]).x = this.value[b1 + 0];
/* 377 */         (arrayOfTuple2f[b]).y = this.value[b1 + 1];
/*     */       } 
/* 379 */       return arrayOfTuple2f;
/*     */     }
/*     */ 
/*     */     
/* 383 */     Object getRef() { return this.value; }
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
/*     */   static class Tuple3iArrayWrapper
/*     */     extends ArrayWrapper
/*     */   {
/* 430 */     private int[] value = new int[0];
/*     */     
/*     */     void set(Object param1Object) {
/* 433 */       Tuple3i[] arrayOfTuple3i = (Tuple3i[])param1Object;
/* 434 */       if (this.length != arrayOfTuple3i.length) {
/* 435 */         this.length = arrayOfTuple3i.length;
/* 436 */         this.value = new int[this.length * 3];
/*     */       } 
/* 438 */       for (byte b = 0; b < this.length; b++) {
/* 439 */         byte b1 = b * 3;
/* 440 */         this.value[b1 + 0] = (arrayOfTuple3i[b]).x;
/* 441 */         this.value[b1 + 1] = (arrayOfTuple3i[b]).y;
/* 442 */         this.value[b1 + 2] = (arrayOfTuple3i[b]).z;
/*     */       } 
/*     */     }
/*     */     
/*     */     void set(int param1Int, Object param1Object) {
/* 447 */       int i = param1Int * 3;
/* 448 */       this.value[i + 0] = ((Tuple3i)param1Object).x;
/* 449 */       this.value[i + 1] = ((Tuple3i)param1Object).y;
/* 450 */       this.value[i + 2] = ((Tuple3i)param1Object).z;
/*     */     }
/*     */     
/*     */     Object get() {
/* 454 */       Tuple3i[] arrayOfTuple3i = new Tuple3i[this.length];
/* 455 */       for (byte b = 0; b < this.length; b++) {
/* 456 */         byte b1 = b * 3;
/* 457 */         arrayOfTuple3i[b] = new Point3i();
/* 458 */         (arrayOfTuple3i[b]).x = this.value[b1 + 0];
/* 459 */         (arrayOfTuple3i[b]).y = this.value[b1 + 1];
/* 460 */         (arrayOfTuple3i[b]).z = this.value[b1 + 2];
/*     */       } 
/* 462 */       return arrayOfTuple3i;
/*     */     }
/*     */ 
/*     */     
/* 466 */     Object getRef() { return this.value; }
/*     */   }
/*     */   
/*     */   static class Tuple3fArrayWrapper
/*     */     extends ArrayWrapper
/*     */   {
/* 472 */     private float[] value = new float[0];
/*     */     
/*     */     void set(Object param1Object) {
/* 475 */       Tuple3f[] arrayOfTuple3f = (Tuple3f[])param1Object;
/* 476 */       if (this.length != arrayOfTuple3f.length) {
/* 477 */         this.length = arrayOfTuple3f.length;
/* 478 */         this.value = new float[this.length * 3];
/*     */       } 
/* 480 */       for (byte b = 0; b < this.length; b++) {
/* 481 */         byte b1 = b * 3;
/* 482 */         this.value[b1 + 0] = (arrayOfTuple3f[b]).x;
/* 483 */         this.value[b1 + 1] = (arrayOfTuple3f[b]).y;
/* 484 */         this.value[b1 + 2] = (arrayOfTuple3f[b]).z;
/*     */       } 
/*     */     }
/*     */     
/*     */     void set(int param1Int, Object param1Object) {
/* 489 */       int i = param1Int * 3;
/* 490 */       this.value[i + 0] = ((Tuple3f)param1Object).x;
/* 491 */       this.value[i + 1] = ((Tuple3f)param1Object).y;
/* 492 */       this.value[i + 2] = ((Tuple3f)param1Object).z;
/*     */     }
/*     */     
/*     */     Object get() {
/* 496 */       Tuple3f[] arrayOfTuple3f = new Tuple3f[this.length];
/* 497 */       for (byte b = 0; b < this.length; b++) {
/* 498 */         byte b1 = b * 3;
/* 499 */         arrayOfTuple3f[b] = new Point3f();
/* 500 */         (arrayOfTuple3f[b]).x = this.value[b1 + 0];
/* 501 */         (arrayOfTuple3f[b]).y = this.value[b1 + 1];
/* 502 */         (arrayOfTuple3f[b]).z = this.value[b1 + 2];
/*     */       } 
/* 504 */       return arrayOfTuple3f;
/*     */     }
/*     */ 
/*     */     
/* 508 */     Object getRef() { return this.value; }
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
/*     */   static class Tuple4iArrayWrapper
/*     */     extends ArrayWrapper
/*     */   {
/* 558 */     private int[] value = new int[0];
/*     */     
/*     */     void set(Object param1Object) {
/* 561 */       Tuple4i[] arrayOfTuple4i = (Tuple4i[])param1Object;
/* 562 */       if (this.length != arrayOfTuple4i.length) {
/* 563 */         this.length = arrayOfTuple4i.length;
/* 564 */         this.value = new int[this.length * 4];
/*     */       } 
/* 566 */       for (byte b = 0; b < this.length; b++) {
/* 567 */         byte b1 = b * 4;
/* 568 */         this.value[b1 + 0] = (arrayOfTuple4i[b]).x;
/* 569 */         this.value[b1 + 1] = (arrayOfTuple4i[b]).y;
/* 570 */         this.value[b1 + 2] = (arrayOfTuple4i[b]).z;
/* 571 */         this.value[b1 + 3] = (arrayOfTuple4i[b]).w;
/*     */       } 
/*     */     }
/*     */     
/*     */     void set(int param1Int, Object param1Object) {
/* 576 */       int i = param1Int * 4;
/* 577 */       this.value[i + 0] = ((Tuple4i)param1Object).x;
/* 578 */       this.value[i + 1] = ((Tuple4i)param1Object).y;
/* 579 */       this.value[i + 2] = ((Tuple4i)param1Object).z;
/* 580 */       this.value[i + 3] = ((Tuple4i)param1Object).w;
/*     */     }
/*     */     
/*     */     Object get() {
/* 584 */       Tuple4i[] arrayOfTuple4i = new Tuple4i[this.length];
/* 585 */       for (byte b = 0; b < this.length; b++) {
/* 586 */         byte b1 = b * 4;
/* 587 */         arrayOfTuple4i[b] = new Point4i();
/* 588 */         (arrayOfTuple4i[b]).x = this.value[b1 + 0];
/* 589 */         (arrayOfTuple4i[b]).y = this.value[b1 + 1];
/* 590 */         (arrayOfTuple4i[b]).z = this.value[b1 + 2];
/* 591 */         (arrayOfTuple4i[b]).w = this.value[b1 + 3];
/*     */       } 
/* 593 */       return arrayOfTuple4i;
/*     */     }
/*     */ 
/*     */     
/* 597 */     Object getRef() { return this.value; }
/*     */   }
/*     */   
/*     */   static class Tuple4fArrayWrapper
/*     */     extends ArrayWrapper
/*     */   {
/* 603 */     private float[] value = new float[0];
/*     */     
/*     */     void set(Object param1Object) {
/* 606 */       Tuple4f[] arrayOfTuple4f = (Tuple4f[])param1Object;
/* 607 */       if (this.length != arrayOfTuple4f.length) {
/* 608 */         this.length = arrayOfTuple4f.length;
/* 609 */         this.value = new float[this.length * 4];
/*     */       } 
/* 611 */       for (byte b = 0; b < this.length; b++) {
/* 612 */         byte b1 = b * 4;
/* 613 */         this.value[b1 + 0] = (arrayOfTuple4f[b]).x;
/* 614 */         this.value[b1 + 1] = (arrayOfTuple4f[b]).y;
/* 615 */         this.value[b1 + 2] = (arrayOfTuple4f[b]).z;
/* 616 */         this.value[b1 + 3] = (arrayOfTuple4f[b]).w;
/*     */       } 
/*     */     }
/*     */     
/*     */     void set(int param1Int, Object param1Object) {
/* 621 */       int i = param1Int * 4;
/* 622 */       this.value[i + 0] = ((Tuple4f)param1Object).x;
/* 623 */       this.value[i + 1] = ((Tuple4f)param1Object).y;
/* 624 */       this.value[i + 2] = ((Tuple4f)param1Object).z;
/* 625 */       this.value[i + 3] = ((Tuple4f)param1Object).w;
/*     */     }
/*     */     
/*     */     Object get() {
/* 629 */       Tuple4f[] arrayOfTuple4f = new Tuple4f[this.length];
/* 630 */       for (byte b = 0; b < this.length; b++) {
/* 631 */         byte b1 = b * 4;
/* 632 */         arrayOfTuple4f[b] = new Point4f();
/* 633 */         (arrayOfTuple4f[b]).x = this.value[b1 + 0];
/* 634 */         (arrayOfTuple4f[b]).y = this.value[b1 + 1];
/* 635 */         (arrayOfTuple4f[b]).z = this.value[b1 + 2];
/* 636 */         (arrayOfTuple4f[b]).w = this.value[b1 + 3];
/*     */       } 
/* 638 */       return arrayOfTuple4f;
/*     */     }
/*     */ 
/*     */     
/* 642 */     Object getRef() { return this.value; }
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
/*     */ 
/*     */   
/*     */   static class Matrix3fArrayWrapper
/*     */     extends ArrayWrapper
/*     */   {
/* 695 */     private float[] value = new float[0];
/*     */     
/*     */     void set(Object param1Object) {
/* 698 */       Matrix3f[] arrayOfMatrix3f = (Matrix3f[])param1Object;
/* 699 */       if (this.length != arrayOfMatrix3f.length) {
/* 700 */         this.length = arrayOfMatrix3f.length;
/* 701 */         this.value = new float[this.length * 9];
/*     */       } 
/* 703 */       for (byte b = 0; b < this.length; b++) {
/* 704 */         byte b1 = b * 9;
/* 705 */         this.value[b1 + 0] = (arrayOfMatrix3f[b]).m00;
/* 706 */         this.value[b1 + 1] = (arrayOfMatrix3f[b]).m01;
/* 707 */         this.value[b1 + 2] = (arrayOfMatrix3f[b]).m02;
/* 708 */         this.value[b1 + 3] = (arrayOfMatrix3f[b]).m10;
/* 709 */         this.value[b1 + 4] = (arrayOfMatrix3f[b]).m11;
/* 710 */         this.value[b1 + 5] = (arrayOfMatrix3f[b]).m12;
/* 711 */         this.value[b1 + 6] = (arrayOfMatrix3f[b]).m20;
/* 712 */         this.value[b1 + 7] = (arrayOfMatrix3f[b]).m21;
/* 713 */         this.value[b1 + 8] = (arrayOfMatrix3f[b]).m22;
/*     */       } 
/*     */     }
/*     */     
/*     */     void set(int param1Int, Object param1Object) {
/* 718 */       int i = param1Int * 9;
/* 719 */       Matrix3f matrix3f = (Matrix3f)param1Object;
/*     */       
/* 721 */       this.value[i + 0] = matrix3f.m00;
/* 722 */       this.value[i + 1] = matrix3f.m01;
/* 723 */       this.value[i + 2] = matrix3f.m02;
/* 724 */       this.value[i + 3] = matrix3f.m10;
/* 725 */       this.value[i + 4] = matrix3f.m11;
/* 726 */       this.value[i + 5] = matrix3f.m12;
/* 727 */       this.value[i + 6] = matrix3f.m20;
/* 728 */       this.value[i + 7] = matrix3f.m21;
/* 729 */       this.value[i + 8] = matrix3f.m22;
/*     */     }
/*     */     
/*     */     Object get() {
/* 733 */       Matrix3f[] arrayOfMatrix3f = new Matrix3f[this.length];
/* 734 */       for (byte b = 0; b < this.length; b++) {
/* 735 */         byte b1 = b * 9;
/* 736 */         arrayOfMatrix3f[b] = new Matrix3f();
/* 737 */         (arrayOfMatrix3f[b]).m00 = this.value[b1 + 0];
/* 738 */         (arrayOfMatrix3f[b]).m01 = this.value[b1 + 1];
/* 739 */         (arrayOfMatrix3f[b]).m02 = this.value[b1 + 2];
/* 740 */         (arrayOfMatrix3f[b]).m10 = this.value[b1 + 3];
/* 741 */         (arrayOfMatrix3f[b]).m11 = this.value[b1 + 4];
/* 742 */         (arrayOfMatrix3f[b]).m12 = this.value[b1 + 5];
/* 743 */         (arrayOfMatrix3f[b]).m20 = this.value[b1 + 6];
/* 744 */         (arrayOfMatrix3f[b]).m21 = this.value[b1 + 7];
/* 745 */         (arrayOfMatrix3f[b]).m22 = this.value[b1 + 8];
/*     */       } 
/* 747 */       return arrayOfMatrix3f;
/*     */     }
/*     */ 
/*     */     
/* 751 */     Object getRef() { return this.value; }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Matrix4fArrayWrapper
/*     */     extends ArrayWrapper
/*     */   {
/* 821 */     private float[] value = new float[0];
/*     */     
/*     */     void set(Object param1Object) {
/* 824 */       Matrix4f[] arrayOfMatrix4f = (Matrix4f[])param1Object;
/* 825 */       if (this.length != arrayOfMatrix4f.length) {
/* 826 */         this.length = arrayOfMatrix4f.length;
/* 827 */         this.value = new float[this.length * 16];
/*     */       } 
/* 829 */       for (byte b = 0; b < this.length; b++) {
/* 830 */         byte b1 = b * 16;
/* 831 */         this.value[b1 + 0] = (arrayOfMatrix4f[b]).m00;
/* 832 */         this.value[b1 + 1] = (arrayOfMatrix4f[b]).m01;
/* 833 */         this.value[b1 + 2] = (arrayOfMatrix4f[b]).m02;
/* 834 */         this.value[b1 + 3] = (arrayOfMatrix4f[b]).m03;
/* 835 */         this.value[b1 + 4] = (arrayOfMatrix4f[b]).m10;
/* 836 */         this.value[b1 + 5] = (arrayOfMatrix4f[b]).m11;
/* 837 */         this.value[b1 + 6] = (arrayOfMatrix4f[b]).m12;
/* 838 */         this.value[b1 + 7] = (arrayOfMatrix4f[b]).m13;
/* 839 */         this.value[b1 + 8] = (arrayOfMatrix4f[b]).m20;
/* 840 */         this.value[b1 + 9] = (arrayOfMatrix4f[b]).m21;
/* 841 */         this.value[b1 + 10] = (arrayOfMatrix4f[b]).m22;
/* 842 */         this.value[b1 + 11] = (arrayOfMatrix4f[b]).m23;
/* 843 */         this.value[b1 + 12] = (arrayOfMatrix4f[b]).m30;
/* 844 */         this.value[b1 + 13] = (arrayOfMatrix4f[b]).m31;
/* 845 */         this.value[b1 + 14] = (arrayOfMatrix4f[b]).m32;
/* 846 */         this.value[b1 + 15] = (arrayOfMatrix4f[b]).m33;
/*     */       } 
/*     */     }
/*     */     
/*     */     void set(int param1Int, Object param1Object) {
/* 851 */       int i = param1Int * 16;
/* 852 */       Matrix4f matrix4f = (Matrix4f)param1Object;
/*     */       
/* 854 */       this.value[i + 0] = matrix4f.m00;
/* 855 */       this.value[i + 1] = matrix4f.m01;
/* 856 */       this.value[i + 2] = matrix4f.m02;
/* 857 */       this.value[i + 3] = matrix4f.m03;
/* 858 */       this.value[i + 4] = matrix4f.m10;
/* 859 */       this.value[i + 5] = matrix4f.m11;
/* 860 */       this.value[i + 6] = matrix4f.m12;
/* 861 */       this.value[i + 7] = matrix4f.m13;
/* 862 */       this.value[i + 8] = matrix4f.m20;
/* 863 */       this.value[i + 9] = matrix4f.m21;
/* 864 */       this.value[i + 10] = matrix4f.m22;
/* 865 */       this.value[i + 11] = matrix4f.m23;
/* 866 */       this.value[i + 12] = matrix4f.m30;
/* 867 */       this.value[i + 13] = matrix4f.m31;
/* 868 */       this.value[i + 14] = matrix4f.m32;
/* 869 */       this.value[i + 15] = matrix4f.m33;
/*     */     }
/*     */     
/*     */     Object get() {
/* 873 */       Matrix4f[] arrayOfMatrix4f = new Matrix4f[this.length];
/* 874 */       for (byte b = 0; b < this.length; b++) {
/* 875 */         byte b1 = b * 16;
/* 876 */         arrayOfMatrix4f[b] = new Matrix4f();
/* 877 */         (arrayOfMatrix4f[b]).m00 = this.value[b1 + 0];
/* 878 */         (arrayOfMatrix4f[b]).m01 = this.value[b1 + 1];
/* 879 */         (arrayOfMatrix4f[b]).m02 = this.value[b1 + 2];
/* 880 */         (arrayOfMatrix4f[b]).m03 = this.value[b1 + 3];
/* 881 */         (arrayOfMatrix4f[b]).m10 = this.value[b1 + 4];
/* 882 */         (arrayOfMatrix4f[b]).m11 = this.value[b1 + 5];
/* 883 */         (arrayOfMatrix4f[b]).m12 = this.value[b1 + 6];
/* 884 */         (arrayOfMatrix4f[b]).m13 = this.value[b1 + 7];
/* 885 */         (arrayOfMatrix4f[b]).m20 = this.value[b1 + 8];
/* 886 */         (arrayOfMatrix4f[b]).m21 = this.value[b1 + 9];
/* 887 */         (arrayOfMatrix4f[b]).m22 = this.value[b1 + 10];
/* 888 */         (arrayOfMatrix4f[b]).m23 = this.value[b1 + 11];
/* 889 */         (arrayOfMatrix4f[b]).m30 = this.value[b1 + 12];
/* 890 */         (arrayOfMatrix4f[b]).m31 = this.value[b1 + 13];
/* 891 */         (arrayOfMatrix4f[b]).m32 = this.value[b1 + 14];
/* 892 */         (arrayOfMatrix4f[b]).m33 = this.value[b1 + 15];
/*     */       } 
/* 894 */       return arrayOfMatrix4f;
/*     */     }
/*     */ 
/*     */     
/* 898 */     Object getRef() { return this.value; }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\ShaderAttributeArrayRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */