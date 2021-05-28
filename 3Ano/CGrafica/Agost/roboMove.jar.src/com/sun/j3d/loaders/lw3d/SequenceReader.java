/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.StreamTokenizer;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import javax.media.j3d.Alpha;
/*     */ import javax.media.j3d.BoundingSphere;
/*     */ import javax.media.j3d.Switch;
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
/*     */ class SequenceReader
/*     */ {
/*     */   Vector sequenceLines;
/*     */   float totalTime;
/*     */   int totalFrames;
/*     */   TransformGroup objectTransform;
/*     */   Vector behaviorVector;
/*     */   
/*     */   SequenceReader(String paramString, float paramFloat, int paramInt) throws ParsingErrorException {
/*  87 */     this.totalTime = paramFloat;
/*  88 */     this.totalFrames = paramInt;
/*  89 */     this.sequenceLines = new Vector();
/*     */     
/*     */     try {
/*  92 */       StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedReader(new FileReader(paramString)));
/*     */       
/*  94 */       streamTokenizer.wordChars(95, 95);
/*  95 */       streamTokenizer.wordChars(47, 47);
/*  96 */       int i = streamTokenizer.nextToken();
/*  97 */       while (streamTokenizer.ttype != -1) {
/*  98 */         this.sequenceLines.addElement(new SequenceLine(streamTokenizer, this.totalTime, this.totalFrames));
/*     */ 
/*     */         
/* 101 */         streamTokenizer.nextToken();
/*     */       }
/*     */     
/* 104 */     } catch (IOException iOException) {
/* 105 */       throw new ParsingErrorException(iOException.getMessage());
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
/*     */   void createJava3dObjects(int paramInt1, int paramInt2) throws FileNotFoundException {
/* 119 */     this.objectTransform = new TransformGroup();
/* 120 */     this.behaviorVector = new Vector();
/* 121 */     Enumeration enumeration = this.sequenceLines.elements();
/* 122 */     Switch switch = new Switch();
/* 123 */     switch.setCapability(17);
/* 124 */     switch.setCapability(18);
/* 125 */     this.objectTransform.addChild(switch);
/* 126 */     while (enumeration.hasMoreElements()) {
/* 127 */       SequenceLine sequenceLine = (SequenceLine)enumeration.nextElement();
/* 128 */       sequenceLine.createJava3dObjects(paramInt1, paramInt2);
/* 129 */       if (sequenceLine.getGeometry() != null) {
/* 130 */         switch.addChild(sequenceLine.getGeometry());
/*     */       }
/* 132 */       if (sequenceLine.getBehavior() != null) {
/* 133 */         this.behaviorVector.addElement(sequenceLine.getBehavior());
/*     */       }
/*     */     } 
/* 136 */     float[] arrayOfFloat = new float[this.sequenceLines.size() + 1];
/* 137 */     for (byte b = 0; b < arrayOfFloat.length - 1; b++) {
/* 138 */       SequenceLine sequenceLine = (SequenceLine)this.sequenceLines.elementAt(b);
/* 139 */       arrayOfFloat[b] = sequenceLine.startFrame / this.totalFrames;
/*     */     } 
/* 141 */     arrayOfFloat[arrayOfFloat.length - 1] = 1.0F;
/* 142 */     Alpha alpha = new Alpha(-1, 1, 0L, 0L, (long)(1000.0F * this.totalTime), 0L, 0L, 0L, 0L, 0L);
/*     */ 
/*     */ 
/*     */     
/* 146 */     SwitchPathInterpolator switchPathInterpolator = new SwitchPathInterpolator(alpha, arrayOfFloat, switch);
/*     */ 
/*     */ 
/*     */     
/* 150 */     BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 1000000.0D);
/*     */     
/* 152 */     switchPathInterpolator.setSchedulingBounds(boundingSphere);
/* 153 */     switch.addChild(switchPathInterpolator);
/* 154 */     this.behaviorVector.addElement(switchPathInterpolator);
/*     */   }
/*     */ 
/*     */   
/* 158 */   TransformGroup getObjectNode() { return this.objectTransform; }
/*     */ 
/*     */ 
/*     */   
/* 162 */   Vector getObjectBehaviors() { return this.behaviorVector; }
/*     */ 
/*     */   
/*     */   void printLines() {
/* 166 */     Enumeration enumeration = this.sequenceLines.elements();
/* 167 */     while (enumeration.hasMoreElements())
/* 168 */       SequenceLine sequenceLine = (SequenceLine)enumeration.nextElement(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3d\loaders\lw3d\SequenceReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */