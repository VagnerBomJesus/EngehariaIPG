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
/*     */ class ShaderAttributeValueRetained
/*     */   extends ShaderAttributeObjectRetained
/*     */ {
/*     */   void createMirrorObject() {
/*  30 */     if (this.mirror == null) {
/*  31 */       ShaderAttributeValueRetained shaderAttributeValueRetained = new ShaderAttributeValueRetained();
/*  32 */       shaderAttributeValueRetained.createObjectData(getValue());
/*  33 */       this.mirror = shaderAttributeValueRetained;
/*  34 */       this.mirror.source = this.source;
/*     */     } 
/*     */     
/*  37 */     initMirrorObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int computeClassType(Object paramObject) {
/*  46 */     Class clazz = paramObject.getClass();
/*  47 */     if (clazz.isArray()) {
/*  48 */       throw new ClassCastException(clazz + " -- array class not allowed");
/*     */     }
/*     */     
/*  51 */     for (byte b = 0; b < classTable.length; b++) {
/*  52 */       if (classTable[b].isInstance(paramObject)) {
/*  53 */         return b;
/*     */       }
/*     */     } 
/*  56 */     throw new ClassCastException(clazz + " -- unrecognized class");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   Class getBaseClass(int paramInt) { return classTable[paramInt]; } ShaderAttributeObjectRetained.AttrWrapper createAttrWrapper(Object paramObject, int paramInt) { Matrix3fWrapper matrix3fWrapper; Tuple2fWrapper tuple2fWrapper;
/*     */     Tuple2iWrapper tuple2iWrapper;
/*     */     Tuple3fWrapper tuple3fWrapper;
/*     */     Tuple4fWrapper tuple4fWrapper;
/*     */     Tuple4iWrapper tuple4iWrapper;
/*     */     FloatWrapper floatWrapper;
/*     */     Tuple3iWrapper tuple3iWrapper;
/*     */     Matrix4fWrapper matrix4fWrapper;
/*  71 */     IntegerWrapper integerWrapper = null;
/*  72 */     switch (paramInt) {
/*     */       case 0:
/*  74 */         integerWrapper = new IntegerWrapper();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 127 */         integerWrapper.set(paramObject);
/* 128 */         return integerWrapper;case 1: floatWrapper = new FloatWrapper(); floatWrapper.set(paramObject); return floatWrapper;case 2: tuple2iWrapper = new Tuple2iWrapper(); tuple2iWrapper.set(paramObject); return tuple2iWrapper;case 3: tuple2fWrapper = new Tuple2fWrapper(); tuple2fWrapper.set(paramObject); return tuple2fWrapper;case 4: tuple3iWrapper = new Tuple3iWrapper(); tuple3iWrapper.set(paramObject); return tuple3iWrapper;case 5: tuple3fWrapper = new Tuple3fWrapper(); tuple3fWrapper.set(paramObject); return tuple3fWrapper;case 6: tuple4iWrapper = new Tuple4iWrapper(); tuple4iWrapper.set(paramObject); return tuple4iWrapper;case 7: tuple4fWrapper = new Tuple4fWrapper(); tuple4fWrapper.set(paramObject); return tuple4fWrapper;case 8: matrix3fWrapper = new Matrix3fWrapper(); matrix3fWrapper.set(paramObject); return matrix3fWrapper;case 9: matrix4fWrapper = new Matrix4fWrapper(); matrix4fWrapper.set(paramObject); return matrix4fWrapper;
/*     */     } 
/*     */     assert false;
/*     */     return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static abstract class ValueWrapper
/*     */     extends ShaderAttributeObjectRetained.AttrWrapper {}
/*     */ 
/*     */ 
/*     */   
/*     */   static class IntegerWrapper
/*     */     extends ValueWrapper
/*     */   {
/* 144 */     private int[] value = new int[1];
/*     */ 
/*     */     
/* 147 */     void set(Object param1Object) { this.value[0] = ((Integer)param1Object).intValue(); }
/*     */ 
/*     */ 
/*     */     
/* 151 */     Object get() { return new Integer(this.value[0]); }
/*     */ 
/*     */ 
/*     */     
/* 155 */     Object getRef() { return this.value; }
/*     */   }
/*     */   
/*     */   static class FloatWrapper
/*     */     extends ValueWrapper
/*     */   {
/* 161 */     private float[] value = new float[1];
/*     */ 
/*     */     
/* 164 */     void set(Object param1Object) { this.value[0] = ((Float)param1Object).floatValue(); }
/*     */ 
/*     */ 
/*     */     
/* 168 */     Object get() { return new Float(this.value[0]); }
/*     */ 
/*     */ 
/*     */     
/* 172 */     Object getRef() { return this.value; }
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
/*     */   static class Tuple2iWrapper
/*     */     extends ValueWrapper
/*     */   {
/* 197 */     private int[] value = new int[2];
/*     */ 
/*     */     
/* 200 */     void set(Object param1Object) { ((Tuple2i)param1Object).get(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 204 */     Object get() { return new Point2i(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 208 */     Object getRef() { return this.value; }
/*     */   }
/*     */   
/*     */   static class Tuple2fWrapper
/*     */     extends ValueWrapper
/*     */   {
/* 214 */     private float[] value = new float[2];
/*     */ 
/*     */     
/* 217 */     void set(Object param1Object) { ((Tuple2f)param1Object).get(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 221 */     Object get() { return new Point2f(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 225 */     Object getRef() { return this.value; }
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
/*     */   static class Tuple3iWrapper
/*     */     extends ValueWrapper
/*     */   {
/* 250 */     private int[] value = new int[3];
/*     */ 
/*     */     
/* 253 */     void set(Object param1Object) { ((Tuple3i)param1Object).get(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 257 */     Object get() { return new Point3i(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 261 */     Object getRef() { return this.value; }
/*     */   }
/*     */   
/*     */   static class Tuple3fWrapper
/*     */     extends ValueWrapper
/*     */   {
/* 267 */     private float[] value = new float[3];
/*     */ 
/*     */     
/* 270 */     void set(Object param1Object) { ((Tuple3f)param1Object).get(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 274 */     Object get() { return new Point3f(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 278 */     Object getRef() { return this.value; }
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
/*     */   static class Tuple4iWrapper
/*     */     extends ValueWrapper
/*     */   {
/* 303 */     private int[] value = new int[4];
/*     */ 
/*     */     
/* 306 */     void set(Object param1Object) { ((Tuple4i)param1Object).get(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 310 */     Object get() { return new Point4i(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 314 */     Object getRef() { return this.value; }
/*     */   }
/*     */   
/*     */   static class Tuple4fWrapper
/*     */     extends ValueWrapper
/*     */   {
/* 320 */     private float[] value = new float[4];
/*     */ 
/*     */     
/* 323 */     void set(Object param1Object) { ((Tuple4f)param1Object).get(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 327 */     Object get() { return new Point4f(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 331 */     Object getRef() { return this.value; }
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
/*     */   static class Matrix3fWrapper
/*     */     extends ValueWrapper
/*     */   {
/* 356 */     private float[] value = new float[9];
/*     */     
/*     */     void set(Object param1Object) {
/* 359 */       Matrix3f matrix3f = (Matrix3f)param1Object;
/* 360 */       this.value[0] = matrix3f.m00;
/* 361 */       this.value[1] = matrix3f.m01;
/* 362 */       this.value[2] = matrix3f.m02;
/* 363 */       this.value[3] = matrix3f.m10;
/* 364 */       this.value[4] = matrix3f.m11;
/* 365 */       this.value[5] = matrix3f.m12;
/* 366 */       this.value[6] = matrix3f.m20;
/* 367 */       this.value[7] = matrix3f.m21;
/* 368 */       this.value[8] = matrix3f.m22;
/*     */     }
/*     */ 
/*     */     
/* 372 */     Object get() { return new Matrix3f(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 376 */     Object getRef() { return this.value; }
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
/*     */   static class Matrix4fWrapper
/*     */     extends ValueWrapper
/*     */   {
/* 410 */     private float[] value = new float[16];
/*     */     
/*     */     void set(Object param1Object) {
/* 413 */       Matrix4f matrix4f = (Matrix4f)param1Object;
/* 414 */       this.value[0] = matrix4f.m00;
/* 415 */       this.value[1] = matrix4f.m01;
/* 416 */       this.value[2] = matrix4f.m02;
/* 417 */       this.value[3] = matrix4f.m03;
/* 418 */       this.value[4] = matrix4f.m10;
/* 419 */       this.value[5] = matrix4f.m11;
/* 420 */       this.value[6] = matrix4f.m12;
/* 421 */       this.value[7] = matrix4f.m13;
/* 422 */       this.value[8] = matrix4f.m20;
/* 423 */       this.value[9] = matrix4f.m21;
/* 424 */       this.value[10] = matrix4f.m22;
/* 425 */       this.value[11] = matrix4f.m23;
/* 426 */       this.value[12] = matrix4f.m30;
/* 427 */       this.value[13] = matrix4f.m31;
/* 428 */       this.value[14] = matrix4f.m32;
/* 429 */       this.value[15] = matrix4f.m33;
/*     */     }
/*     */ 
/*     */     
/* 433 */     Object get() { return new Matrix4f(this.value); }
/*     */ 
/*     */ 
/*     */     
/* 437 */     Object getRef() { return this.value; }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\ShaderAttributeValueRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */