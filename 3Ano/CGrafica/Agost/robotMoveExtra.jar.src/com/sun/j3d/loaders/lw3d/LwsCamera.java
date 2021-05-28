/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.io.IOException;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.util.Vector;
/*     */ import javax.media.j3d.Behavior;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LwsCamera
/*     */   extends TextfileParser
/*     */   implements LwsPrimitive
/*     */ {
/*     */   String fileName;
/*     */   String objName;
/*     */   LwsMotion motion;
/*     */   int parent;
/*     */   TransformGroup objectTransform;
/*     */   Vector objectBehavior;
/*     */   
/*     */   LwsCamera(StreamTokenizer paramStreamTokenizer, int paramInt1, int paramInt2, float paramFloat, int paramInt3) throws ParsingErrorException {
/*  79 */     this.debugPrinter.setValidOutput(paramInt3);
/*  80 */     this.parent = -1;
/*  81 */     getNumber(paramStreamTokenizer);
/*  82 */     getNumber(paramStreamTokenizer);
/*  83 */     getAndCheckString(paramStreamTokenizer, "CameraMotion");
/*  84 */     this.motion = new LwsMotion(paramStreamTokenizer, paramInt1, paramInt2, paramFloat, this.debugPrinter.getValidOutput());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     while (!isCurrentToken(paramStreamTokenizer, "DepthOfField")) {
/*  92 */       debugOutputLn(8, "currentToken = " + paramStreamTokenizer.sval);
/*     */       
/*  94 */       if (isCurrentToken(paramStreamTokenizer, "ParentObject")) {
/*  95 */         this.parent = (int)getNumber(paramStreamTokenizer);
/*     */       }
/*     */       try {
/*  98 */         paramStreamTokenizer.nextToken();
/*     */       }
/* 100 */       catch (IOException iOException) {
/* 101 */         throw new ParsingErrorException(iOException.getMessage());
/*     */       } 
/*     */     } 
/* 104 */     getNumber(paramStreamTokenizer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   int getParent() { return this.parent; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createJava3dObject(int paramInt) {
/* 121 */     Matrix4d matrix4d1 = new Matrix4d();
/* 122 */     matrix4d1.setIdentity();
/*     */ 
/*     */     
/* 125 */     LwsFrame lwsFrame = this.motion.getFirstFrame();
/* 126 */     lwsFrame.setMatrix(matrix4d1);
/* 127 */     debugOutputLn(2, "  Camera Matrix = \n" + matrix4d1);
/* 128 */     Transform3D transform3D1 = new Transform3D();
/* 129 */     Matrix4d matrix4d2 = new Matrix4d();
/* 130 */     double d = 0.1D;
/* 131 */     matrix4d2.setColumn(0, d, 0.0D, 0.0D, 0.0D);
/* 132 */     matrix4d2.setColumn(1, 0.0D, d, 0.0D, 0.0D);
/* 133 */     matrix4d2.setColumn(2, 0.0D, 0.0D, d, 0.0D);
/* 134 */     matrix4d2.setColumn(3, 0.0D, 0.0D, 0.0D, 1.0D);
/* 135 */     Transform3D transform3D2 = new Transform3D(matrix4d2);
/* 136 */     TransformGroup transformGroup = new TransformGroup(transform3D2);
/* 137 */     transformGroup.setCapability(18);
/* 138 */     transformGroup.setCapability(17);
/*     */     
/* 140 */     transform3D1.set(matrix4d1);
/* 141 */     this.objectTransform = new TransformGroup(transform3D1);
/* 142 */     this.objectTransform.setCapability(18);
/* 143 */     this.objectBehavior = new Vector();
/* 144 */     if (paramInt != 0) {
/* 145 */       this.motion.createJava3dBehaviors(this.objectTransform);
/* 146 */       Behavior behavior = this.motion.getBehaviors();
/* 147 */       if (behavior != null) {
/* 148 */         this.objectBehavior.addElement(behavior);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   public TransformGroup getObjectNode() { return this.objectTransform; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getObjectBehaviors() {
/* 165 */     debugOutputLn(1, "getObjectBehaviors()");
/* 166 */     return this.objectBehavior;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void printVals() {
/* 175 */     System.out.println("   objName = " + this.objName);
/* 176 */     this.motion.printVals();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\loaders\lw3d\LwsCamera.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */