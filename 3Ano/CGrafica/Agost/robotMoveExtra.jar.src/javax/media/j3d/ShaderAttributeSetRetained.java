/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ShaderAttributeSetRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*  28 */   private Map attrs = new HashMap();
/*     */ 
/*     */   
/*  31 */   Object liveStateLock = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void put(ShaderAttribute paramShaderAttribute) {
/*  54 */     synchronized (this.liveStateLock) {
/*     */       
/*  56 */       ShaderAttributeRetained shaderAttributeRetained = (ShaderAttributeRetained)paramShaderAttribute.retained;
/*     */ 
/*     */       
/*  59 */       assert shaderAttributeRetained != null;
/*  60 */       this.attrs.put(shaderAttributeRetained.attrName, shaderAttributeRetained);
/*     */       
/*  62 */       if (this.source.isLive()) {
/*  63 */         shaderAttributeRetained.setLive(this.inBackgroundGroup, this.refCount);
/*  64 */         shaderAttributeRetained.copyMirrorUsers(this);
/*     */         
/*  66 */         sendMessage(1, shaderAttributeRetained.mirror);
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
/*  82 */   ShaderAttribute get(String paramString) { return (ShaderAttribute)((ShaderAttributeRetained)this.attrs.get(paramString)).source; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void remove(String paramString) {
/*  93 */     synchronized (this.liveStateLock) {
/*  94 */       ShaderAttributeRetained shaderAttributeRetained = (ShaderAttributeRetained)this.attrs.get(paramString);
/*  95 */       this.attrs.remove(paramString);
/*  96 */       if (this.source.isLive()) {
/*  97 */         shaderAttributeRetained.clearLive(this.refCount);
/*  98 */         shaderAttributeRetained.removeMirrorUsers(this);
/*     */         
/* 100 */         sendMessage(2, paramString);
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
/*     */   void remove(ShaderAttribute paramShaderAttribute) {
/* 117 */     synchronized (this.liveStateLock) {
/* 118 */       String str = paramShaderAttribute.getAttributeName();
/* 119 */       if (this.attrs.get(str) == paramShaderAttribute) {
/* 120 */         this.attrs.remove(str);
/* 121 */         if (this.source.isLive()) {
/* 122 */           ((ShaderAttributeRetained)paramShaderAttribute.retained).clearLive(this.refCount);
/* 123 */           ((ShaderAttributeRetained)paramShaderAttribute.retained).removeMirrorUsers(this);
/*     */           
/* 125 */           sendMessage(2, str);
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
/*     */   void clear() {
/* 137 */     synchronized (this.liveStateLock) {
/* 138 */       this.attrs.clear();
/* 139 */       if (this.source.isLive()) {
/* 140 */         ShaderAttributeRetained[] arrayOfShaderAttributeRetained = new ShaderAttributeRetained[this.attrs.size()];
/* 141 */         arrayOfShaderAttributeRetained = (ShaderAttributeRetained[])this.attrs.values().toArray(arrayOfShaderAttributeRetained);
/* 142 */         for (byte b = 0; b < arrayOfShaderAttributeRetained.length; b++) {
/* 143 */           arrayOfShaderAttributeRetained[b].clearLive(this.refCount);
/* 144 */           arrayOfShaderAttributeRetained[b].removeMirrorUsers(this);
/*     */         } 
/* 146 */         sendMessage(4, null);
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
/*     */   ShaderAttribute[] getAll() {
/* 159 */     ShaderAttributeRetained[] arrayOfShaderAttributeRetained = new ShaderAttributeRetained[this.attrs.size()];
/* 160 */     ShaderAttribute[] arrayOfShaderAttribute = new ShaderAttribute[arrayOfShaderAttributeRetained.length];
/* 161 */     arrayOfShaderAttributeRetained = (ShaderAttributeRetained[])this.attrs.values().toArray(arrayOfShaderAttributeRetained);
/* 162 */     for (byte b = 0; b < arrayOfShaderAttributeRetained.length; b++) {
/* 163 */       arrayOfShaderAttribute[b] = (ShaderAttribute)(arrayOfShaderAttributeRetained[b]).source;
/*     */     }
/*     */     
/* 166 */     return arrayOfShaderAttribute;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   int size() { return this.attrs.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   void updateNative(Canvas3D paramCanvas3D, ShaderProgramRetained paramShaderProgramRetained) { paramShaderProgramRetained.setShaderAttributes(paramCanvas3D, this); }
/*     */ 
/*     */ 
/*     */   
/* 185 */   Map getAttrs() { return this.attrs; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setLive(boolean paramBoolean, int paramInt) {
/* 192 */     ShaderAttributeRetained[] arrayOfShaderAttributeRetained = new ShaderAttributeRetained[this.attrs.size()];
/* 193 */     arrayOfShaderAttributeRetained = (ShaderAttributeRetained[])this.attrs.values().toArray(arrayOfShaderAttributeRetained);
/* 194 */     for (byte b = 0; b < arrayOfShaderAttributeRetained.length; b++) {
/* 195 */       arrayOfShaderAttributeRetained[b].setLive(paramBoolean, paramInt);
/*     */     }
/*     */     
/* 198 */     doSetLive(paramBoolean, paramInt);
/* 199 */     markAsLive();
/*     */   }
/*     */ 
/*     */   
/*     */   void addAMirrorUser(Shape3DRetained paramShape3DRetained) {
/* 204 */     super.addAMirrorUser(paramShape3DRetained);
/*     */     
/* 206 */     ShaderAttributeRetained[] arrayOfShaderAttributeRetained = new ShaderAttributeRetained[this.attrs.size()];
/* 207 */     arrayOfShaderAttributeRetained = (ShaderAttributeRetained[])this.attrs.values().toArray(arrayOfShaderAttributeRetained);
/* 208 */     for (byte b = 0; b < arrayOfShaderAttributeRetained.length; b++) {
/* 209 */       arrayOfShaderAttributeRetained[b].addAMirrorUser(paramShape3DRetained);
/*     */     }
/*     */   }
/*     */   
/*     */   void removeAMirrorUser(Shape3DRetained paramShape3DRetained) {
/* 214 */     super.removeAMirrorUser(paramShape3DRetained);
/*     */     
/* 216 */     ShaderAttributeRetained[] arrayOfShaderAttributeRetained = new ShaderAttributeRetained[this.attrs.size()];
/* 217 */     arrayOfShaderAttributeRetained = (ShaderAttributeRetained[])this.attrs.values().toArray(arrayOfShaderAttributeRetained);
/* 218 */     for (byte b = 0; b < arrayOfShaderAttributeRetained.length; b++) {
/* 219 */       arrayOfShaderAttributeRetained[b].removeAMirrorUser(paramShape3DRetained);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void removeMirrorUsers(NodeComponentRetained paramNodeComponentRetained) {
/* 225 */     super.removeMirrorUsers(paramNodeComponentRetained);
/*     */     
/* 227 */     ShaderAttributeRetained[] arrayOfShaderAttributeRetained = new ShaderAttributeRetained[this.attrs.size()];
/* 228 */     arrayOfShaderAttributeRetained = (ShaderAttributeRetained[])this.attrs.values().toArray(arrayOfShaderAttributeRetained);
/* 229 */     for (byte b = 0; b < arrayOfShaderAttributeRetained.length; b++) {
/* 230 */       arrayOfShaderAttributeRetained[b].removeMirrorUsers(paramNodeComponentRetained);
/*     */     }
/*     */   }
/*     */   
/*     */   void copyMirrorUsers(NodeComponentRetained paramNodeComponentRetained) {
/* 235 */     super.copyMirrorUsers(paramNodeComponentRetained);
/*     */     
/* 237 */     ShaderAttributeRetained[] arrayOfShaderAttributeRetained = new ShaderAttributeRetained[this.attrs.size()];
/* 238 */     arrayOfShaderAttributeRetained = (ShaderAttributeRetained[])this.attrs.values().toArray(arrayOfShaderAttributeRetained);
/* 239 */     for (byte b = 0; b < arrayOfShaderAttributeRetained.length; b++) {
/* 240 */       arrayOfShaderAttributeRetained[b].copyMirrorUsers(paramNodeComponentRetained);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive(int paramInt) {
/* 247 */     super.clearLive(paramInt);
/*     */     
/* 249 */     ShaderAttributeRetained[] arrayOfShaderAttributeRetained = new ShaderAttributeRetained[this.attrs.size()];
/* 250 */     arrayOfShaderAttributeRetained = (ShaderAttributeRetained[])this.attrs.values().toArray(arrayOfShaderAttributeRetained);
/* 251 */     for (byte b = 0; b < arrayOfShaderAttributeRetained.length; b++) {
/* 252 */       arrayOfShaderAttributeRetained[b].clearLive(paramInt);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void createMirrorObject() {
/* 259 */     if (this.mirror == null) {
/* 260 */       ShaderAttributeSetRetained shaderAttributeSetRetained = new ShaderAttributeSetRetained();
/* 261 */       this.mirror = shaderAttributeSetRetained;
/* 262 */       this.mirror.source = this.source;
/*     */     } 
/*     */     
/* 265 */     initMirrorObject();
/*     */   }
/*     */ 
/*     */   
/*     */   void initMirrorObject() {
/* 270 */     ShaderAttributeRetained[] arrayOfShaderAttributeRetained = new ShaderAttributeRetained[this.attrs.size()];
/* 271 */     arrayOfShaderAttributeRetained = (ShaderAttributeRetained[])this.attrs.values().toArray(arrayOfShaderAttributeRetained);
/*     */     
/* 273 */     for (byte b = 0; b < arrayOfShaderAttributeRetained.length; b++) {
/* 274 */       ShaderAttributeRetained shaderAttributeRetained = (ShaderAttributeRetained)(arrayOfShaderAttributeRetained[b]).mirror;
/* 275 */       assert shaderAttributeRetained != null;
/* 276 */       ((ShaderAttributeSetRetained)this.mirror).attrs.put(shaderAttributeRetained.attrName, shaderAttributeRetained);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 287 */     ShaderAttributeSetRetained shaderAttributeSetRetained = (ShaderAttributeSetRetained)this.mirror;
/*     */     
/* 289 */     if ((paramInt & true) != 0) {
/*     */       
/* 291 */       ShaderAttributeRetained shaderAttributeRetained = (ShaderAttributeRetained)paramObject;
/* 292 */       assert shaderAttributeRetained != null;
/* 293 */       ((ShaderAttributeSetRetained)this.mirror).attrs.put(shaderAttributeRetained.attrName, shaderAttributeRetained);
/*     */     }
/* 295 */     else if ((paramInt & 0x2) != 0) {
/*     */       
/* 297 */       ((ShaderAttributeSetRetained)this.mirror).attrs.remove((String)paramObject);
/*     */     }
/* 299 */     else if ((paramInt & 0x4) != 0) {
/*     */       
/* 301 */       ((ShaderAttributeSetRetained)this.mirror).attrs.clear();
/*     */     } else {
/*     */       assert false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 310 */     ArrayList arrayList1 = new ArrayList();
/* 311 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*     */ 
/*     */ 
/*     */     
/* 315 */     J3dMessage j3dMessage = new J3dMessage();
/* 316 */     j3dMessage.threads = 1024;
/* 317 */     j3dMessage.type = 64;
/* 318 */     j3dMessage.universe = null;
/* 319 */     j3dMessage.args[0] = this;
/* 320 */     j3dMessage.args[1] = new Integer(paramInt);
/* 321 */     j3dMessage.args[2] = paramObject;
/*     */     
/* 323 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/* 324 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */ 
/*     */     
/* 327 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 328 */       j3dMessage = new J3dMessage();
/* 329 */       j3dMessage.threads = 128;
/* 330 */       j3dMessage.type = 64;
/*     */       
/* 332 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 333 */       j3dMessage.args[0] = this;
/* 334 */       j3dMessage.args[1] = new Integer(paramInt);
/* 335 */       j3dMessage.args[2] = paramObject;
/*     */       
/* 337 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 338 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 339 */       arrayList.toArray(arrayOfGeometryAtom);
/* 340 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*     */       
/* 342 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFrequencyChangeMask(int paramInt1, int paramInt2) {
/* 353 */     if (this.source.getCapability(paramInt1)) {
/* 354 */       this.changedFrequent |= paramInt2;
/* 355 */     } else if (!this.source.isLive()) {
/*     */       
/* 357 */       this.changedFrequent &= (paramInt2 ^ 0xFFFFFFFF);
/*     */     } 
/*     */   }
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {
/* 362 */     if (paramInt == 1)
/* 363 */       setFrequencyChangeMask(paramInt, 1); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\ShaderAttributeSetRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */