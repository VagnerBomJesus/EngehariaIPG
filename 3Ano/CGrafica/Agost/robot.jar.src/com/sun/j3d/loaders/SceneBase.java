/*     */ package com.sun.j3d.loaders;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import javax.media.j3d.Background;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Fog;
/*     */ import javax.media.j3d.Light;
/*     */ import javax.media.j3d.Sound;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SceneBase
/*     */   implements Scene
/*     */ {
/*  74 */   BranchGroup sceneGroup = null;
/*  75 */   BranchGroup behaviorGroup = null;
/*  76 */   Hashtable namedObjects = new Hashtable();
/*  77 */   String description = null;
/*     */   
/*  79 */   Vector viewVector = new Vector();
/*  80 */   Vector hfovVector = new Vector();
/*  81 */   Vector behaviorVector = new Vector();
/*  82 */   Vector lightVector = new Vector();
/*  83 */   Vector fogVector = new Vector();
/*  84 */   Vector backgroundVector = new Vector();
/*  85 */   Vector soundVector = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public void setSceneGroup(BranchGroup paramBranchGroup) { this.sceneGroup = paramBranchGroup; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public void addViewGroup(TransformGroup paramTransformGroup) { this.viewVector.addElement(paramTransformGroup); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void addHorizontalFOV(float paramFloat) { this.hfovVector.addElement(new Float(paramFloat)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public void addBehaviorNode(Behavior paramBehavior) { this.behaviorVector.addElement(paramBehavior); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void addLightNode(Light paramLight) { this.lightVector.addElement(paramLight); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public void addBackgroundNode(Background paramBackground) { this.backgroundVector.addElement(paramBackground); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public void addSoundNode(Sound paramSound) { this.soundVector.addElement(paramSound); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public void addFogNode(Fog paramFog) { this.fogVector.addElement(paramFog); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   public void addDescription(String paramString) { this.description = paramString; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addNamedObject(String paramString, Object paramObject) {
/* 156 */     if (this.namedObjects.get(paramString) == null) {
/* 157 */       this.namedObjects.put(paramString, paramObject);
/*     */     } else {
/*     */       
/* 160 */       byte b = 1;
/* 161 */       boolean bool = false;
/* 162 */       while (!bool) {
/*     */         
/* 164 */         String str = paramString + "[" + b + "]";
/* 165 */         if (this.namedObjects.get(str) == null) {
/* 166 */           this.namedObjects.put(str, paramObject);
/* 167 */           bool = true;
/*     */         } 
/* 169 */         b++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   public BranchGroup getSceneGroup() { return this.sceneGroup; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformGroup[] getViewGroups() {
/* 191 */     if (this.viewVector.isEmpty())
/* 192 */       return null; 
/* 193 */     TransformGroup[] arrayOfTransformGroup = new TransformGroup[this.viewVector.size()];
/* 194 */     this.viewVector.copyInto(arrayOfTransformGroup);
/* 195 */     return arrayOfTransformGroup;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getHorizontalFOVs() {
/* 204 */     if (this.hfovVector.isEmpty()) {
/* 205 */       return null;
/*     */     }
/* 207 */     int i = this.hfovVector.size();
/* 208 */     float[] arrayOfFloat = new float[i];
/* 209 */     Float[] arrayOfFloat1 = new Float[this.hfovVector.size()];
/* 210 */     this.hfovVector.copyInto(arrayOfFloat1);
/*     */ 
/*     */     
/* 213 */     for (byte b = 0; b < i; b++) {
/* 214 */       arrayOfFloat[b] = arrayOfFloat1[b].floatValue();
/* 215 */       arrayOfFloat1[b] = null;
/*     */     } 
/* 217 */     return arrayOfFloat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Light[] getLightNodes() {
/* 225 */     if (this.lightVector.isEmpty())
/* 226 */       return null; 
/* 227 */     Light[] arrayOfLight = new Light[this.lightVector.size()];
/* 228 */     this.lightVector.copyInto(arrayOfLight);
/* 229 */     return arrayOfLight;
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
/* 241 */   public Hashtable getNamedObjects() { return this.namedObjects; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Background[] getBackgroundNodes() {
/* 250 */     if (this.backgroundVector.isEmpty())
/* 251 */       return null; 
/* 252 */     Background[] arrayOfBackground = new Background[this.backgroundVector.size()];
/* 253 */     this.backgroundVector.copyInto(arrayOfBackground);
/* 254 */     return arrayOfBackground;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Fog[] getFogNodes() {
/* 263 */     if (this.fogVector.isEmpty())
/* 264 */       return null; 
/* 265 */     Fog[] arrayOfFog = new Fog[this.fogVector.size()];
/* 266 */     this.fogVector.copyInto(arrayOfFog);
/* 267 */     return arrayOfFog;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Behavior[] getBehaviorNodes() {
/* 276 */     if (this.behaviorVector.isEmpty())
/* 277 */       return null; 
/* 278 */     Behavior[] arrayOfBehavior = new Behavior[this.behaviorVector.size()];
/* 279 */     this.behaviorVector.copyInto(arrayOfBehavior);
/*     */     
/* 281 */     return arrayOfBehavior;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Sound[] getSoundNodes() {
/* 290 */     if (this.soundVector.isEmpty())
/* 291 */       return null; 
/* 292 */     Sound[] arrayOfSound = new Sound[this.soundVector.size()];
/* 293 */     this.soundVector.copyInto(arrayOfSound);
/* 294 */     return arrayOfSound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 303 */   public String getDescription() { return this.description; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\loaders\SceneBase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */