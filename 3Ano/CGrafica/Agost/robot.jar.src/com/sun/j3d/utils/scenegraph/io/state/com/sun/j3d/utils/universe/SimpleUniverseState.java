/*     */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.universe;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.SceneGraphObjectState;
/*     */ import com.sun.j3d.utils.universe.ConfiguredUniverse;
/*     */ import com.sun.j3d.utils.universe.MultiTransformGroup;
/*     */ import com.sun.j3d.utils.universe.PlatformGeometry;
/*     */ import com.sun.j3d.utils.universe.SimpleUniverse;
/*     */ import com.sun.j3d.utils.universe.ViewerAvatar;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.media.j3d.CapabilityNotSetException;
/*     */ import javax.media.j3d.HiResCoord;
/*     */ import javax.media.j3d.Locale;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.vecmath.Matrix4d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleUniverseState
/*     */ {
/*     */   private SimpleUniverse universe;
/*     */   private Controller control;
/*     */   private ArrayList localeBGs;
/*     */   private int totalBGs;
/*     */   private PlatformGeometryState platformGeom;
/*     */   private ViewerAvatarState viewerAvatar;
/*     */   
/*     */   public SimpleUniverseState(ConfiguredUniverse paramConfiguredUniverse, Controller paramController) {
/*  71 */     this.universe = null;
/*     */ 
/*     */     
/*  74 */     this.totalBGs = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     this.universe = paramConfiguredUniverse;
/*  83 */     this.control = paramController;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleUniverseState(SimpleUniverse paramSimpleUniverse, Controller paramController) {
/*     */     this.universe = null;
/*     */     this.totalBGs = 0;
/*  90 */     this.universe = paramSimpleUniverse;
/*  91 */     this.control = paramController;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleUniverseState(Controller paramController) {
/*     */     this.universe = null;
/*     */     this.totalBGs = 0;
/*  98 */     this.control = paramController;
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 102 */     MultiTransformGroup multiTransformGroup = this.universe.getViewingPlatform().getMultiTransformGroup();
/* 103 */     int i = multiTransformGroup.getNumTransforms();
/* 104 */     paramDataOutput.writeInt(i);
/*     */ 
/*     */     
/* 107 */     Transform3D transform3D = new Transform3D();
/* 108 */     Matrix4d matrix4d = new Matrix4d();
/* 109 */     for (byte b = 0; b < i; b++) {
/* 110 */       TransformGroup transformGroup = multiTransformGroup.getTransformGroup(b);
/* 111 */       transformGroup.getTransform(transform3D);
/* 112 */       transform3D.get(matrix4d);
/* 113 */       this.control.writeMatrix4d(paramDataOutput, matrix4d);
/*     */     } 
/*     */     
/* 116 */     this.control.writeObject(paramDataOutput, this.control.createState(this.universe.getViewingPlatform().getPlatformGeometry()));
/* 117 */     this.control.writeObject(paramDataOutput, this.control.createState(this.universe.getViewer().getAvatar()));
/*     */     
/* 119 */     writeLocales(paramDataOutput);
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput, Canvas3D paramCanvas3D) throws IOException {
/* 123 */     int i = paramDataInput.readInt();
/* 124 */     if (paramCanvas3D != null) {
/* 125 */       this.universe = new ConfiguredUniverse(paramCanvas3D, i);
/*     */     } else {
/* 127 */       this.universe = new ConfiguredUniverse(ConfiguredUniverse.getConfigURL(), i);
/*     */     } 
/* 129 */     MultiTransformGroup multiTransformGroup = this.universe.getViewingPlatform().getMultiTransformGroup();
/*     */ 
/*     */     
/* 132 */     Matrix4d matrix4d = new Matrix4d();
/* 133 */     for (byte b = 0; b < i; b++) {
/* 134 */       TransformGroup transformGroup = multiTransformGroup.getTransformGroup(b);
/* 135 */       matrix4d = this.control.readMatrix4d(paramDataInput);
/* 136 */       Transform3D transform3D = new Transform3D(matrix4d);
/* 137 */       transformGroup.setTransform(transform3D);
/*     */     } 
/*     */     
/* 140 */     SceneGraphObjectState sceneGraphObjectState = this.control.readObject(paramDataInput);
/*     */     
/* 142 */     if (sceneGraphObjectState instanceof PlatformGeometryState) {
/* 143 */       this.platformGeom = (PlatformGeometryState)sceneGraphObjectState;
/*     */     } else {
/* 145 */       this.platformGeom = null;
/*     */     } 
/* 147 */     sceneGraphObjectState = this.control.readObject(paramDataInput);
/* 148 */     if (sceneGraphObjectState instanceof ViewerAvatarState) {
/* 149 */       this.viewerAvatar = (ViewerAvatarState)sceneGraphObjectState;
/*     */     } else {
/* 151 */       this.viewerAvatar = null;
/*     */     } 
/* 153 */     readLocales(paramDataInput);
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeLocales(DataOutput paramDataOutput) throws IOException {
/* 158 */     Enumeration enumeration = this.universe.getAllLocales();
/* 159 */     paramDataOutput.writeInt(this.universe.numLocales());
/* 160 */     this.localeBGs = new ArrayList(this.universe.numLocales());
/* 161 */     boolean bool = false;
/* 162 */     byte b = 0;
/* 163 */     while (enumeration.hasMoreElements()) {
/* 164 */       int[] arrayOfInt; Locale locale = (Locale)enumeration.nextElement();
/* 165 */       HiResCoord hiResCoord = new HiResCoord();
/* 166 */       writeHiResCoord(paramDataOutput, hiResCoord);
/*     */       
/* 168 */       if (!bool) {
/* 169 */         arrayOfInt = new int[locale.numBranchGraphs() - 1];
/*     */       } else {
/* 171 */         arrayOfInt = new int[locale.numBranchGraphs()];
/* 172 */       }  paramDataOutput.writeInt(arrayOfInt.length);
/* 173 */       byte b1 = 0;
/* 174 */       Enumeration enumeration1 = locale.getAllBranchGraphs();
/* 175 */       while (enumeration1.hasMoreElements()) {
/* 176 */         BranchGroup branchGroup = (BranchGroup)enumeration1.nextElement();
/* 177 */         if (!(branchGroup instanceof com.sun.j3d.utils.universe.ViewingPlatform)) {
/* 178 */           this.control.getSymbolTable().addBranchGraphReference(branchGroup, b);
/* 179 */           arrayOfInt[b1] = b++;
/* 180 */           paramDataOutput.writeInt(arrayOfInt[b1]);
/* 181 */           b1++;
/* 182 */           this.totalBGs++;
/*     */         } 
/*     */       } 
/* 185 */       this.localeBGs.add(arrayOfInt);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readLocales(DataInput paramDataInput) throws IOException {
/* 190 */     int i = paramDataInput.readInt();
/* 191 */     this.localeBGs = new ArrayList(i);
/*     */     
/* 193 */     for (byte b = 0; b < i; b++) {
/* 194 */       HiResCoord hiResCoord = readHiResCoord(paramDataInput);
/*     */       
/* 196 */       if (!b) {
/* 197 */         Locale locale = this.universe.getLocale();
/* 198 */         locale.setHiRes(hiResCoord);
/*     */       } else {
/* 200 */         Locale locale = new Locale(this.universe, hiResCoord);
/*     */       } 
/*     */       
/* 203 */       int j = paramDataInput.readInt();
/* 204 */       int[] arrayOfInt = new int[j];
/* 205 */       for (byte b1 = 0; b1 < j; b1++) {
/* 206 */         arrayOfInt[b] = paramDataInput.readInt();
/* 207 */         this.totalBGs++;
/*     */       } 
/* 209 */       this.localeBGs.add(arrayOfInt);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/* 219 */     Enumeration enumeration = this.universe.getAllLocales();
/* 220 */     for (byte b = 0; b < this.localeBGs.size(); b++) {
/* 221 */       Locale locale = (Locale)enumeration.nextElement();
/* 222 */       int[] arrayOfInt = (int[])this.localeBGs.get(b);
/* 223 */       for (byte b1 = 0; b1 < arrayOfInt.length; b1++) {
/* 224 */         SymbolTableData symbolTableData = this.control.getSymbolTable().getBranchGraphRoot(arrayOfInt[b1]);
/* 225 */         locale.addBranchGraph((BranchGroup)symbolTableData.j3dNode);
/*     */       } 
/*     */     } 
/*     */     
/* 229 */     if (this.viewerAvatar != null) {
/* 230 */       this.viewerAvatar.buildGraph();
/* 231 */       this.universe.getViewer().setAvatar((ViewerAvatar)this.viewerAvatar.getNode());
/*     */     } 
/*     */     
/* 234 */     if (this.platformGeom != null) {
/* 235 */       this.universe.getViewingPlatform().setPlatformGeometry((PlatformGeometry)this.platformGeom.getNode());
/* 236 */       this.platformGeom.buildGraph();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getAllGraphIDs() {
/* 246 */     int[] arrayOfInt = new int[this.totalBGs];
/* 247 */     byte b1 = 0;
/*     */     
/* 249 */     for (byte b2 = 0; b2 < this.localeBGs.size(); b2++) {
/* 250 */       int[] arrayOfInt1 = (int[])this.localeBGs.get(b2);
/* 251 */       for (byte b = 0; b < arrayOfInt1.length; b++) {
/* 252 */         arrayOfInt[b1++] = arrayOfInt1[b];
/*     */       }
/*     */     } 
/*     */     
/* 256 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detachAllGraphs() {
/* 263 */     boolean bool = false;
/*     */     
/*     */     try {
/* 266 */       for (byte b = 0; b < this.localeBGs.size(); b++) {
/* 267 */         int[] arrayOfInt = (int[])this.localeBGs.get(b);
/* 268 */         for (byte b1 = 0; b1 < arrayOfInt.length; b1++) {
/* 269 */           SymbolTableData symbolTableData = this.control.getSymbolTable().getBranchGraphRoot(arrayOfInt[b1]);
/* 270 */           ((BranchGroup)symbolTableData.j3dNode).detach();
/*     */         } 
/*     */       } 
/* 273 */     } catch (CapabilityNotSetException capabilityNotSetException) {
/* 274 */       throw new CapabilityNotSetException("Locale BranchGraphs MUST have ALLOW_DETACH capability set");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attachAllGraphs() {
/* 283 */     Enumeration enumeration = this.universe.getAllLocales();
/*     */ 
/*     */     
/* 286 */     for (byte b = 0; b < this.localeBGs.size(); b++) {
/* 287 */       Locale locale = (Locale)enumeration.nextElement();
/* 288 */       int[] arrayOfInt = (int[])this.localeBGs.get(b);
/* 289 */       for (byte b1 = 0; b1 < arrayOfInt.length; b1++) {
/* 290 */         SymbolTableData symbolTableData = this.control.getSymbolTable().getBranchGraphRoot(arrayOfInt[b1]);
/* 291 */         locale.addBranchGraph((BranchGroup)symbolTableData.j3dNode);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConfiguredUniverse getNode() {
/* 301 */     if (this.universe instanceof ConfiguredUniverse)
/* 302 */       return (ConfiguredUniverse)this.universe; 
/* 303 */     return null;
/*     */   }
/*     */   
/*     */   private void writeHiResCoord(DataOutput paramDataOutput, HiResCoord paramHiResCoord) throws IOException {
/* 307 */     int[] arrayOfInt1 = new int[8];
/* 308 */     int[] arrayOfInt2 = new int[8];
/* 309 */     int[] arrayOfInt3 = new int[8];
/* 310 */     paramHiResCoord.getHiResCoord(arrayOfInt1, arrayOfInt2, arrayOfInt3);
/* 311 */     for (byte b = 0; b < 8; b++) {
/* 312 */       paramDataOutput.writeInt(arrayOfInt1[b]);
/* 313 */       paramDataOutput.writeInt(arrayOfInt2[b]);
/* 314 */       paramDataOutput.writeInt(arrayOfInt3[b]);
/*     */     } 
/*     */   }
/*     */   
/*     */   private HiResCoord readHiResCoord(DataInput paramDataInput) throws IOException {
/* 319 */     int[] arrayOfInt1 = new int[8];
/* 320 */     int[] arrayOfInt2 = new int[8];
/* 321 */     int[] arrayOfInt3 = new int[8];
/* 322 */     for (byte b = 0; b < 8; b++) {
/* 323 */       arrayOfInt1[b] = paramDataInput.readInt();
/* 324 */       arrayOfInt2[b] = paramDataInput.readInt();
/* 325 */       arrayOfInt3[b] = paramDataInput.readInt();
/*     */     } 
/*     */     
/* 328 */     return new HiResCoord(arrayOfInt1, arrayOfInt2, arrayOfInt3);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\util\\universe\SimpleUniverseState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */