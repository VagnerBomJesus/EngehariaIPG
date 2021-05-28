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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ShaderAppearanceRetained
/*     */   extends AppearanceRetained
/*     */ {
/*     */   static final int SHADER_PROGRAM = 2048;
/*     */   static final int SHADER_ATTRIBUTE_SET = 4096;
/*  35 */   protected ShaderProgramRetained shaderProgram = null;
/*  36 */   protected ShaderAttributeSetRetained shaderAttributeSet = null;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isMirror = false;
/*     */ 
/*     */ 
/*     */   
/*     */   void setShaderProgram(ShaderProgram paramShaderProgram) {
/*  45 */     synchronized (this.liveStateLock) {
/*  46 */       if (this.source.isLive()) {
/*     */ 
/*     */         
/*  49 */         if (this.shaderProgram != null) {
/*  50 */           this.shaderProgram.clearLive(this.refCount);
/*  51 */           this.shaderProgram.removeMirrorUsers(this);
/*     */         } 
/*     */         
/*  54 */         if (paramShaderProgram != null) {
/*  55 */           ((ShaderProgramRetained)paramShaderProgram.retained).setLive(this.inBackgroundGroup, this.refCount);
/*     */           
/*  57 */           ((ShaderProgramRetained)paramShaderProgram.retained).copyMirrorUsers(this);
/*     */         } 
/*     */         
/*  60 */         sendMessage(2048, (paramShaderProgram != null) ? ((ShaderProgramRetained)paramShaderProgram.retained).mirror : null);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  65 */       if (paramShaderProgram == null) {
/*  66 */         this.shaderProgram = null;
/*     */       } else {
/*  68 */         this.shaderProgram = (ShaderProgramRetained)paramShaderProgram.retained;
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
/*  79 */   ShaderProgram getShaderProgram() { return (this.shaderProgram == null) ? null : (ShaderProgram)this.shaderProgram.source; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setShaderAttributeSet(ShaderAttributeSet paramShaderAttributeSet) {
/*  90 */     synchronized (this.liveStateLock) {
/*  91 */       if (this.source.isLive()) {
/*     */ 
/*     */         
/*  94 */         if (this.shaderAttributeSet != null) {
/*  95 */           this.shaderAttributeSet.clearLive(this.refCount);
/*  96 */           this.shaderAttributeSet.removeMirrorUsers(this);
/*     */         } 
/*     */         
/*  99 */         if (paramShaderAttributeSet != null) {
/* 100 */           ((ShaderAttributeSetRetained)paramShaderAttributeSet.retained).setLive(this.inBackgroundGroup, this.refCount);
/*     */           
/* 102 */           ((ShaderAttributeSetRetained)paramShaderAttributeSet.retained).copyMirrorUsers(this);
/*     */         } 
/*     */ 
/*     */         
/* 106 */         sendMessage(4096, (paramShaderAttributeSet != null) ? ((ShaderAttributeSetRetained)paramShaderAttributeSet.retained).mirror : null);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 112 */       if (paramShaderAttributeSet == null) {
/* 113 */         this.shaderAttributeSet = null;
/*     */       } else {
/* 115 */         this.shaderAttributeSet = (ShaderAttributeSetRetained)paramShaderAttributeSet.retained;
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
/* 126 */   ShaderAttributeSet getShaderAttributeSet() { return (this.shaderAttributeSet == null) ? null : (ShaderAttributeSet)this.shaderAttributeSet.source; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public boolean equals(Object paramObject) { return (paramObject instanceof ShaderAppearanceRetained && equals((ShaderAppearanceRetained)paramObject)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean equals(ShaderAppearanceRetained paramShaderAppearanceRetained) {
/* 138 */     boolean bool = (paramShaderAppearanceRetained == this);
/*     */ 
/*     */     
/* 141 */     if (bool) {
/* 142 */       return bool;
/*     */     }
/*     */     
/* 145 */     bool = (paramShaderAppearanceRetained != null && this.shaderProgram == paramShaderAppearanceRetained.shaderProgram && this.shaderAttributeSet == paramShaderAppearanceRetained.shaderAttributeSet);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     if (!bool) {
/* 151 */       return bool;
/*     */     }
/* 153 */     return equals(paramShaderAppearanceRetained);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createMirrorObject() {
/* 162 */     if (this.mirror == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 167 */       this.mirror = new ShaderAppearanceRetained();
/* 168 */       ((ShaderAppearanceRetained)this.mirror).isMirror = true;
/*     */     } 
/* 170 */     initMirrorObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void initMirrorObject() {
/* 181 */     super.initMirrorObject();
/*     */     
/* 183 */     ShaderAppearanceRetained shaderAppearanceRetained = (ShaderAppearanceRetained)this.mirror;
/*     */     
/* 185 */     if (this.shaderProgram != null) {
/* 186 */       shaderAppearanceRetained.shaderProgram = (ShaderProgramRetained)this.shaderProgram.mirror;
/*     */     } else {
/*     */       
/* 189 */       shaderAppearanceRetained.shaderProgram = null;
/*     */     } 
/*     */     
/* 192 */     if (this.shaderAttributeSet != null) {
/* 193 */       shaderAppearanceRetained.shaderAttributeSet = (ShaderAttributeSetRetained)this.shaderAttributeSet.mirror;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 198 */       shaderAppearanceRetained.shaderAttributeSet = null;
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
/*     */   void updateMirrorObject(int paramInt, Object paramObject) {
/* 211 */     super.updateMirrorObject(paramInt, paramObject);
/* 212 */     ShaderAppearanceRetained shaderAppearanceRetained = (ShaderAppearanceRetained)this.mirror;
/* 213 */     if ((paramInt & 0x800) != 0) {
/* 214 */       shaderAppearanceRetained.shaderProgram = (ShaderProgramRetained)paramObject;
/*     */     }
/* 216 */     else if ((paramInt & 0x1000) != 0) {
/* 217 */       shaderAppearanceRetained.shaderAttributeSet = (ShaderAttributeSetRetained)paramObject;
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
/*     */   void doSetLive(boolean paramBoolean, int paramInt) {
/* 230 */     if (this.shaderProgram != null) {
/* 231 */       this.shaderProgram.setLive(paramBoolean, paramInt);
/*     */     }
/*     */     
/* 234 */     if (this.shaderAttributeSet != null) {
/* 235 */       this.shaderAttributeSet.setLive(paramBoolean, paramInt);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 241 */     super.doSetLive(paramBoolean, paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void clearLive(int paramInt) {
/* 250 */     super.clearLive(paramInt);
/*     */     
/* 252 */     if (this.shaderProgram != null) {
/* 253 */       this.shaderProgram.clearLive(paramInt);
/*     */     }
/*     */     
/* 256 */     if (this.shaderAttributeSet != null) {
/* 257 */       this.shaderAttributeSet.clearLive(paramInt);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void addAMirrorUser(Shape3DRetained paramShape3DRetained) {
/* 263 */     super.addAMirrorUser(paramShape3DRetained);
/* 264 */     if (this.shaderProgram != null)
/* 265 */       this.shaderProgram.addAMirrorUser(paramShape3DRetained); 
/* 266 */     if (this.shaderAttributeSet != null) {
/* 267 */       this.shaderAttributeSet.addAMirrorUser(paramShape3DRetained);
/*     */     }
/*     */   }
/*     */   
/*     */   void removeAMirrorUser(Shape3DRetained paramShape3DRetained) {
/* 272 */     super.removeAMirrorUser(paramShape3DRetained);
/* 273 */     if (this.shaderProgram != null)
/* 274 */       this.shaderProgram.removeAMirrorUser(paramShape3DRetained); 
/* 275 */     if (this.shaderAttributeSet != null) {
/* 276 */       this.shaderAttributeSet.removeAMirrorUser(paramShape3DRetained);
/*     */     }
/*     */   }
/*     */   
/*     */   final void sendMessage(int paramInt, Object paramObject) {
/* 281 */     ArrayList arrayList1 = new ArrayList();
/* 282 */     ArrayList arrayList2 = Shape3DRetained.getGeomAtomsList(this.mirror.users, arrayList1);
/*     */ 
/*     */     
/* 285 */     J3dMessage j3dMessage = new J3dMessage();
/* 286 */     j3dMessage.threads = 1024;
/* 287 */     j3dMessage.type = 65;
/* 288 */     j3dMessage.universe = null;
/* 289 */     j3dMessage.args[0] = this;
/* 290 */     j3dMessage.args[1] = new Integer(paramInt);
/* 291 */     j3dMessage.args[2] = paramObject;
/* 292 */     j3dMessage.args[3] = new Integer(this.changedFrequent);
/*     */     
/* 294 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */ 
/*     */     
/* 297 */     for (byte b = 0; b < arrayList1.size(); b++) {
/* 298 */       j3dMessage = new J3dMessage();
/* 299 */       j3dMessage.threads = 128;
/* 300 */       j3dMessage.type = 65;
/*     */       
/* 302 */       j3dMessage.universe = (VirtualUniverse)arrayList1.get(b);
/* 303 */       j3dMessage.args[0] = this;
/* 304 */       j3dMessage.args[1] = new Integer(paramInt);
/* 305 */       j3dMessage.args[2] = paramObject;
/*     */       
/* 307 */       ArrayList arrayList = (ArrayList)arrayList2.get(b);
/* 308 */       GeometryAtom[] arrayOfGeometryAtom = new GeometryAtom[arrayList.size()];
/* 309 */       arrayList.toArray(arrayOfGeometryAtom);
/* 310 */       j3dMessage.args[3] = arrayOfGeometryAtom;
/*     */       
/* 312 */       VirtualUniverse.mc.processMessage(j3dMessage);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isStatic() {
/* 318 */     if (!super.isStatic()) {
/* 319 */       return false;
/*     */     }
/*     */     
/* 322 */     return (this.source.capabilityBitsEmpty() && (this.shaderProgram == null || this.shaderProgram.source.capabilityBitsEmpty()) && (this.shaderAttributeSet == null || this.shaderAttributeSet.source.capabilityBitsEmpty()));
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
/*     */   void compile(CompileState paramCompileState) {
/* 335 */     super.compile(paramCompileState);
/*     */     
/* 337 */     if (this.shaderProgram != null) {
/* 338 */       this.shaderProgram.compile(paramCompileState);
/*     */     }
/*     */     
/* 341 */     if (this.shaderAttributeSet != null) {
/* 342 */       this.shaderAttributeSet.compile(paramCompileState);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isOpaque(int paramInt) {
/* 348 */     if (!super.isOpaque(paramInt)) {
/* 349 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 354 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   void handleFrequencyChange(int paramInt) {
/* 359 */     super.handleFrequencyChange(paramInt);
/*     */     
/* 361 */     char c = Character.MIN_VALUE;
/* 362 */     if (paramInt == 23) {
/* 363 */       c = 'ࠀ';
/* 364 */     } else if (paramInt == 25) {
/* 365 */       c = 'က';
/*     */     } 
/*     */     
/* 368 */     if (c != '\000')
/* 369 */       setFrequencyChangeMask(paramInt, c); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\ShaderAppearanceRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */