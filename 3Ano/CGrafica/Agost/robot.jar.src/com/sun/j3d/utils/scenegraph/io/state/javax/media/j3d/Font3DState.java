/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.awt.Font;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.Font3D;
/*     */ import javax.media.j3d.FontExtrusion;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Font3DState
/*     */   extends NodeComponentState
/*     */ {
/*  64 */   private Font font = null;
/*  65 */   private double tesselationTolerance = 0.0D;
/*  66 */   private FontExtrusion extrudePath = null;
/*     */ 
/*     */   
/*  69 */   public Font3DState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  73 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  75 */     paramDataOutput.writeUTF(this.font.getFontName());
/*  76 */     paramDataOutput.writeInt(this.font.getStyle());
/*  77 */     paramDataOutput.writeInt(this.font.getSize());
/*     */     
/*  79 */     paramDataOutput.writeDouble(this.tesselationTolerance);
/*     */     
/*  81 */     if (this.extrudePath != null)
/*  82 */     { Shape shape = this.extrudePath.getExtrusionShape();
/*  83 */       if (shape != null) {
/*  84 */         PathIterator pathIterator = shape.getPathIterator(null);
/*  85 */         float[] arrayOfFloat = new float[6];
/*     */ 
/*     */         
/*  88 */         while (!pathIterator.isDone()) {
/*     */           
/*  90 */           int i = pathIterator.currentSegment(arrayOfFloat);
/*  91 */           paramDataOutput.writeInt(i);
/*     */ 
/*     */           
/*  94 */           byte b1 = 0;
/*  95 */           if (i == 0) { b1 = 1; }
/*  96 */           else if (i == 1) { b1 = 1; }
/*  97 */           else if (i == 2) { b1 = 2; }
/*  98 */           else if (i == 3) { b1 = 3; }
/*     */           
/* 100 */           for (byte b2 = 0; b2 < b1; b2++) {
/* 101 */             paramDataOutput.writeFloat(arrayOfFloat[b2 * 2 + 0]);
/* 102 */             paramDataOutput.writeFloat(arrayOfFloat[b2 * 2 + 1]);
/*     */           } 
/*     */ 
/*     */           
/* 106 */           if (!pathIterator.isDone()) pathIterator.next();
/*     */         
/*     */         } 
/*     */       } 
/* 110 */       paramDataOutput.writeInt(-2147483648);
/* 111 */       paramDataOutput.writeDouble(this.extrudePath.getTessellationTolerance()); }
/* 112 */     else { paramDataOutput.writeInt(-2147483648); }
/*     */   
/*     */   }
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/* 116 */     super.readConstructorParams(paramDataInput);
/*     */     
/* 118 */     String str = paramDataInput.readUTF();
/* 119 */     int i = paramDataInput.readInt();
/* 120 */     int j = paramDataInput.readInt();
/* 121 */     this.font = new Font(str, i, j);
/*     */     
/* 123 */     this.tesselationTolerance = paramDataInput.readDouble();
/*     */     
/* 125 */     GeneralPath generalPath = null;
/* 126 */     int k = paramDataInput.readInt();
/* 127 */     while (k != Integer.MIN_VALUE) {
/* 128 */       if (generalPath == null) generalPath = new GeneralPath();
/*     */       
/* 130 */       if (k == 0) {
/* 131 */         generalPath.moveTo(paramDataInput.readFloat(), paramDataInput.readFloat());
/* 132 */       } else if (k == 1) {
/* 133 */         generalPath.lineTo(paramDataInput.readFloat(), paramDataInput.readFloat());
/* 134 */       } else if (k == 2) {
/* 135 */         generalPath.quadTo(paramDataInput.readFloat(), paramDataInput.readFloat(), paramDataInput.readFloat(), paramDataInput.readFloat());
/*     */       }
/* 137 */       else if (k == 3) {
/* 138 */         generalPath.curveTo(paramDataInput.readFloat(), paramDataInput.readFloat(), paramDataInput.readFloat(), paramDataInput.readFloat(), paramDataInput.readFloat(), paramDataInput.readFloat());
/*     */       
/*     */       }
/* 141 */       else if (k == 4) {
/* 142 */         generalPath.closePath();
/*     */       } 
/*     */       
/* 145 */       k = paramDataInput.readInt();
/*     */     } 
/* 147 */     if (generalPath != null) { this.extrudePath = new FontExtrusion(generalPath, paramDataInput.readDouble()); }
/* 148 */     else { this.extrudePath = null; }
/*     */   
/*     */   }
/*     */   
/* 152 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { Font.class, double.class, FontExtrusion.class }, new Object[] { this.font, new Double(this.tesselationTolerance), this.extrudePath }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   protected SceneGraphObject createNode() { return new Font3D(this.font, this.tesselationTolerance, this.extrudePath); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\Font3DState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */