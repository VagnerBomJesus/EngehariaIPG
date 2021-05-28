/*     */ package javax.media.j3d;
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
/*     */ abstract class ShaderAttributeObjectRetained
/*     */   extends ShaderAttributeRetained
/*     */ {
/*     */   private int classType;
/*     */   private Class baseClass;
/*     */   AttrWrapper attrWrapper;
/*     */   static final int TYPE_INTEGER = 0;
/*     */   static final int TYPE_FLOAT = 1;
/*     */   static final int TYPE_TUPLE2I = 2;
/*     */   static final int TYPE_TUPLE2F = 3;
/*     */   static final int TYPE_TUPLE3I = 4;
/*     */   static final int TYPE_TUPLE3F = 5;
/*     */   static final int TYPE_TUPLE4I = 6;
/*     */   static final int TYPE_TUPLE4F = 7;
/*     */   static final int TYPE_MATRIX3F = 8;
/*     */   static final int TYPE_MATRIX4F = 9;
/*     */   
/*     */   void createObjectData(Object paramObject) {
/*  38 */     this.classType = computeClassType(paramObject);
/*  39 */     this.baseClass = getBaseClass(this.classType);
/*  40 */     this.attrWrapper = createAttrWrapper(paramObject, this.classType);
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
/*  55 */   void initValue(Object paramObject) { this.attrWrapper.set(paramObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   Object getValue() { return this.attrWrapper.get(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setValue(Object paramObject) {
/*  81 */     initValue(paramObject);
/*  82 */     AttrWrapper attrWrapper1 = createAttrWrapper(paramObject, this.classType);
/*  83 */     sendMessage(8, attrWrapper1);
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
/*  97 */   Class getValueClass() { return this.baseClass; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initMirrorObject() {
/* 104 */     super.initMirrorObject();
/* 105 */     ((ShaderAttributeObjectRetained)this.mirror).initValue(getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 114 */     ShaderAttributeObjectRetained shaderAttributeObjectRetained = (ShaderAttributeObjectRetained)this.mirror;
/* 115 */     if ((paramInt & 0x8) != 0)
/*     */     {
/* 117 */       shaderAttributeObjectRetained.attrWrapper = (AttrWrapper)paramObject;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 123 */     ArrayList arrayList1 = new ArrayList();
/* 124 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*     */ 
/*     */ 
/*     */     
/* 128 */     J3dMessage j3dMessage = new J3dMessage();
/* 129 */     j3dMessage.threads = 1024;
/* 130 */     j3dMessage.type = 63;
/* 131 */     j3dMessage.universe = null;
/* 132 */     j3dMessage.args[0] = this;
/* 133 */     j3dMessage.args[1] = new Integer(paramInt);
/* 134 */     j3dMessage.args[2] = paramObject;
/*     */     
/* 136 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/* 137 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */ 
/*     */     
/* 140 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 141 */       j3dMessage = new J3dMessage();
/* 142 */       j3dMessage.threads = 128;
/* 143 */       j3dMessage.type = 63;
/*     */       
/* 145 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 146 */       j3dMessage.args[0] = this;
/* 147 */       j3dMessage.args[1] = new Integer(paramInt);
/* 148 */       j3dMessage.args[2] = paramObject;
/*     */       
/* 150 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 151 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 152 */       arrayList.toArray(arrayOfGeometryAtom);
/* 153 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*     */       
/* 155 */       VirtualUniverse.mc.processMessage(j3dMessage);
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
/* 188 */   static final Class[] classTable = { Integer.class, Float.class, javax.vecmath.Tuple2i.class, javax.vecmath.Tuple2f.class, javax.vecmath.Tuple3i.class, javax.vecmath.Tuple3f.class, javax.vecmath.Tuple4i.class, javax.vecmath.Tuple4f.class, javax.vecmath.Matrix3f.class, javax.vecmath.Matrix4f.class };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   static final Class[] classTableArr = { Integer[].class, Float[].class, javax.vecmath.Tuple2i[].class, javax.vecmath.Tuple2f[].class, javax.vecmath.Tuple3i[].class, javax.vecmath.Tuple3f[].class, javax.vecmath.Tuple4i[].class, javax.vecmath.Tuple4f[].class, javax.vecmath.Matrix3f[].class, javax.vecmath.Matrix4f[].class };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract int computeClassType(Object paramObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract Class getBaseClass(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   abstract AttrWrapper createAttrWrapper(Object paramObject, int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static abstract class AttrWrapper
/*     */   {
/*     */     abstract void set(Object param1Object);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract Object get();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract Object getRef();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   int getClassType() { return this.classType; }
/*     */ 
/*     */ 
/*     */   
/* 283 */   void setClassType(int paramInt) { this.classType = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFrequencyChangeMask(int paramInt1, int paramInt2) {
/* 292 */     if (this.source.getCapability(paramInt1)) {
/* 293 */       this.changedFrequent |= paramInt2;
/* 294 */     } else if (!this.source.isLive()) {
/*     */       
/* 296 */       this.changedFrequent &= (paramInt2 ^ 0xFFFFFFFF);
/*     */     } 
/*     */   }
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {
/* 301 */     if (paramInt == 1)
/* 302 */       setFrequencyChangeMask(paramInt, 1); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\ShaderAttributeObjectRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */