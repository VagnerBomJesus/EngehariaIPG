/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Color3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ColoringAttributesRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*     */   static final int COLOR_CHANGED = 1;
/*     */   static final int SHADE_MODEL_CHANGED = 2;
/*  30 */   Color3f color = new Color3f(1.0F, 1.0F, 1.0F);
/*     */ 
/*     */   
/*  33 */   int shadeModel = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   final void initColor(Color3f paramColor3f) { this.color.set(paramColor3f); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setColor(Color3f paramColor3f) {
/*  53 */     initColor(paramColor3f);
/*  54 */     sendMessage(1, new Color3f(paramColor3f));
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
/*  66 */   final void initColor(float paramFloat1, float paramFloat2, float paramFloat3) { this.color.set(paramFloat1, paramFloat2, paramFloat3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setColor(float paramFloat1, float paramFloat2, float paramFloat3) {
/*  80 */     initColor(paramFloat1, paramFloat2, paramFloat3);
/*  81 */     sendMessage(1, new Color3f(paramFloat1, paramFloat2, paramFloat3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   final void getColor(Color3f paramColor3f) { paramColor3f.set(this.color); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   final void initShadeModel(int paramInt) { this.shadeModel = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setShadeModel(int paramInt) {
/* 110 */     initShadeModel(paramInt);
/* 111 */     sendMessage(2, new Integer(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   final int getShadeModel() { return this.shadeModel; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createMirrorObject() {
/* 128 */     if (this.mirror == null) {
/*     */ 
/*     */       
/* 131 */       if (isStatic()) {
/* 132 */         this.mirror = this;
/*     */       } else {
/* 134 */         ColoringAttributesRetained coloringAttributesRetained = new ColoringAttributesRetained();
/*     */         
/* 136 */         coloringAttributesRetained.source = this.source;
/* 137 */         coloringAttributesRetained.set(this);
/* 138 */         this.mirror = coloringAttributesRetained;
/*     */       } 
/*     */     } else {
/* 141 */       ((ColoringAttributesRetained)this.mirror).set(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   void updateNative(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean) { Pipeline.getPipeline().updateColoringAttributes(paramContext, paramFloat1, paramFloat3, paramFloat2, this.color.x, this.color.y, this.color.z, paramFloat4, paramBoolean, this.shadeModel); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   void initMirrorObject() { ((ColoringAttributesRetained)this.mirror).set(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 167 */     ColoringAttributesRetained coloringAttributesRetained = (ColoringAttributesRetained)this.mirror;
/*     */ 
/*     */     
/* 170 */     if ((paramInt & true) != 0) {
/* 171 */       coloringAttributesRetained.color.set((Color3f)paramObject);
/*     */     }
/* 173 */     else if ((paramInt & 0x2) != 0) {
/* 174 */       coloringAttributesRetained.shadeModel = ((Integer)paramObject).intValue();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 179 */   boolean equivalent(ColoringAttributesRetained paramColoringAttributesRetained) { return (paramColoringAttributesRetained != null && this.color.equals(paramColoringAttributesRetained.color) && this.shadeModel == paramColoringAttributesRetained.shadeModel); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object clone() {
/* 188 */     ColoringAttributesRetained coloringAttributesRetained = (ColoringAttributesRetained)super.clone();
/*     */     
/* 190 */     coloringAttributesRetained.color = new Color3f(this.color);
/*     */     
/* 192 */     return coloringAttributesRetained;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void set(ColoringAttributesRetained paramColoringAttributesRetained) {
/* 198 */     set(paramColoringAttributesRetained);
/* 199 */     this.color.set(paramColoringAttributesRetained.color);
/* 200 */     this.shadeModel = paramColoringAttributesRetained.shadeModel;
/*     */   }
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 204 */     ArrayList arrayList1 = new ArrayList();
/* 205 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*     */ 
/*     */     
/* 208 */     J3dMessage j3dMessage = new J3dMessage();
/* 209 */     j3dMessage.threads = 1024;
/* 210 */     j3dMessage.type = 6;
/* 211 */     j3dMessage.universe = null;
/* 212 */     j3dMessage.args[0] = this;
/* 213 */     j3dMessage.args[1] = new Integer(paramInt);
/* 214 */     j3dMessage.args[2] = paramObject;
/* 215 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/* 216 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */ 
/*     */ 
/*     */     
/* 220 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 221 */       j3dMessage = new J3dMessage();
/* 222 */       j3dMessage.threads = 128;
/* 223 */       j3dMessage.type = 6;
/*     */       
/* 225 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 226 */       j3dMessage.args[0] = this;
/* 227 */       j3dMessage.args[1] = new Integer(paramInt);
/* 228 */       j3dMessage.args[2] = paramObject;
/*     */       
/* 230 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 231 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 232 */       arrayList.toArray(arrayOfGeometryAtom);
/* 233 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*     */       
/* 235 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */   void handleFrequencyChange(int paramInt) {
/* 239 */     if (paramInt == 1 || paramInt == 3)
/*     */     {
/* 241 */       setFrequencyChangeMask(paramInt, 1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\ColoringAttributesRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */