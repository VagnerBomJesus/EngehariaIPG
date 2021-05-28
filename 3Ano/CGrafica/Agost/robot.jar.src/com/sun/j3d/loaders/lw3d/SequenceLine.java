/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.IncorrectFormatException;
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import javax.media.j3d.Alpha;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.BoundingSphere;
/*     */ import javax.media.j3d.Group;
/*     */ import javax.media.j3d.Link;
/*     */ import javax.media.j3d.Shape3D;
/*     */ import javax.media.j3d.SharedGroup;
/*     */ import javax.media.j3d.Switch;
/*     */ import javax.media.j3d.SwitchValueInterpolator;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.vecmath.Point3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SequenceLine
/*     */ {
/*     */   int startFrame;
/*     */   int endFrame;
/*     */   String fileName;
/* 116 */   Group geometryGroup = null;
/*     */   
/*     */   Behavior behaviors;
/*     */   
/*     */   int numFrames;
/*     */   float totalTime;
/*     */   int totalFrames;
/* 123 */   static Hashtable storedRefList = new Hashtable();
/*     */ 
/*     */   
/*     */   SequenceLine(StreamTokenizer paramStreamTokenizer, float paramFloat, int paramInt) throws ParsingErrorException {
/*     */     try {
/* 128 */       this.totalTime = paramFloat;
/* 129 */       this.totalFrames = paramInt;
/* 130 */       this.startFrame = (int)paramStreamTokenizer.nval;
/* 131 */       paramStreamTokenizer.nextToken();
/* 132 */       this.endFrame = (int)paramStreamTokenizer.nval;
/* 133 */       paramStreamTokenizer.nextToken();
/* 134 */       this.fileName = paramStreamTokenizer.sval;
/* 135 */       this.numFrames = this.endFrame - this.startFrame + 1;
/*     */     }
/* 137 */     catch (IOException iOException) {
/* 138 */       throw new ParsingErrorException(iOException.getMessage());
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
/*     */   private void createSwitchBehavior(Switch paramSwitch) {
/* 153 */     byte b = -1;
/* 154 */     float f1 = 1000.0F * this.totalTime * paramSwitch.numChildren() / this.totalFrames;
/*     */     
/* 156 */     float f2 = 1000.0F * this.totalTime * this.startFrame / this.totalFrames;
/*     */     
/* 158 */     Alpha alpha = new Alpha(-1, (long)f2, 0L, (long)f1, 0L, 0L);
/*     */ 
/*     */     
/* 161 */     SwitchValueInterpolator switchValueInterpolator = new SwitchValueInterpolator(alpha, paramSwitch);
/* 162 */     this.behaviors = switchValueInterpolator;
/* 163 */     BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 1000000.0D);
/*     */     
/* 165 */     switchValueInterpolator.setSchedulingBounds(boundingSphere);
/* 166 */     paramSwitch.setCapability(18);
/* 167 */     paramSwitch.addChild(this.behaviors);
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
/*     */   void createJava3dObjects(int paramInt1, int paramInt2) throws IncorrectFormatException, FileNotFoundException {
/* 181 */     if (this.fileName.indexOf("000") != -1) {
/* 182 */       int i = this.fileName.indexOf("000");
/* 183 */       String str1 = this.fileName.substring(0, i);
/* 184 */       Switch switch = new Switch();
/* 185 */       switch.setCapability(17);
/* 186 */       switch.setCapability(18);
/* 187 */       String str2 = this.fileName;
/* 188 */       byte b = 0;
/* 189 */       while ((new File(str2)).exists()) {
/* 190 */         if (storedRefList.get(str2) != null) {
/*     */ 
/*     */           
/* 193 */           SharedGroup sharedGroup = (SharedGroup)storedRefList.get(str2);
/*     */           
/* 195 */           Link link = new Link(sharedGroup);
/* 196 */           switch.addChild(link);
/*     */         }
/*     */         else {
/*     */           
/* 200 */           J3dLwoParser j3dLwoParser = new J3dLwoParser(str2, paramInt1);
/*     */           
/* 202 */           j3dLwoParser.createJava3dGeometry();
/* 203 */           TransformGroup transformGroup = new TransformGroup();
/* 204 */           SharedGroup sharedGroup = new SharedGroup();
/* 205 */           storedRefList.put(str2, sharedGroup);
/* 206 */           sharedGroup.addChild(transformGroup);
/* 207 */           Link link = new Link(sharedGroup);
/* 208 */           switch.addChild(link);
/* 209 */           if (j3dLwoParser.getJava3dShapeList() != null) {
/* 210 */             Enumeration enumeration = j3dLwoParser.getJava3dShapeList().elements();
/*     */             
/* 212 */             while (enumeration.hasMoreElements()) {
/* 213 */               transformGroup.addChild((Shape3D)enumeration.nextElement());
/*     */             }
/*     */           } 
/*     */         } 
/* 217 */         b++;
/* 218 */         String str = String.valueOf(b);
/* 219 */         if (b < 10) {
/* 220 */           str = "00" + str;
/* 221 */         } else if (b < 100) {
/* 222 */           str = "0" + str;
/* 223 */         }  str2 = str1 + str + ".lwo";
/*     */       } 
/* 225 */       this.behaviors = null;
/* 226 */       if (paramInt2 != 0) {
/* 227 */         createSwitchBehavior(switch);
/*     */       }
/* 229 */       this.geometryGroup = switch;
/*     */     } else {
/*     */       
/* 232 */       this.geometryGroup = new Group();
/* 233 */       if (storedRefList.get(this.fileName) != null) {
/*     */         
/* 235 */         SharedGroup sharedGroup = (SharedGroup)storedRefList.get(this.fileName);
/*     */         
/* 237 */         Link link = new Link(sharedGroup);
/* 238 */         this.geometryGroup.addChild(link);
/*     */       }
/*     */       else {
/*     */         
/* 242 */         J3dLwoParser j3dLwoParser = new J3dLwoParser(this.fileName, paramInt1);
/*     */         
/* 244 */         j3dLwoParser.createJava3dGeometry();
/* 245 */         TransformGroup transformGroup = new TransformGroup();
/* 246 */         if (j3dLwoParser.getJava3dShapeList() != null) {
/* 247 */           Enumeration enumeration = j3dLwoParser.getJava3dShapeList().elements();
/* 248 */           while (enumeration.hasMoreElements()) {
/* 249 */             transformGroup.addChild((Shape3D)enumeration.nextElement());
/*     */           }
/*     */         } 
/* 252 */         SharedGroup sharedGroup = new SharedGroup();
/* 253 */         sharedGroup.addChild(transformGroup);
/* 254 */         Link link = new Link(sharedGroup);
/* 255 */         this.geometryGroup.addChild(link);
/* 256 */         storedRefList.put(this.fileName, sharedGroup);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 262 */   Group getGeometry() { return this.geometryGroup; }
/*     */ 
/*     */ 
/*     */   
/* 266 */   Behavior getBehavior() { return this.behaviors; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3d\loaders\lw3d\SequenceLine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */