/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.TextureAttributes;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.vecmath.Color4f;
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
/*     */ public class TextureAttributesState
/*     */   extends NodeComponentState
/*     */ {
/*     */   private static final int MAX_COLOR_OPERANDS = 2;
/*     */   
/*  63 */   public TextureAttributesState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  67 */     super.writeObject(paramDataOutput);
/*  68 */     TextureAttributes textureAttributes = (TextureAttributes)this.node;
/*  69 */     Color4f color4f = new Color4f();
/*  70 */     Matrix4d matrix4d = new Matrix4d();
/*  71 */     Transform3D transform3D = new Transform3D();
/*  72 */     int i = textureAttributes.getNumTextureColorTableComponents();
/*  73 */     int j = textureAttributes.getTextureColorTableSize();
/*  74 */     int[][] arrayOfInt = new int[i][j];
/*     */     
/*  76 */     paramDataOutput.writeInt(textureAttributes.getPerspectiveCorrectionMode());
/*  77 */     textureAttributes.getTextureBlendColor(color4f);
/*  78 */     this.control.writeColor4f(paramDataOutput, color4f);
/*  79 */     paramDataOutput.writeInt(i);
/*  80 */     paramDataOutput.writeInt(j);
/*  81 */     textureAttributes.getTextureColorTable(arrayOfInt); byte b;
/*  82 */     for (b = 0; b < i; b++) {
/*  83 */       for (byte b1 = 0; b1 < j; b1++)
/*  84 */         paramDataOutput.writeInt(arrayOfInt[b][b1]); 
/*     */     } 
/*  86 */     paramDataOutput.writeInt(textureAttributes.getTextureMode());
/*  87 */     textureAttributes.getTextureTransform(transform3D);
/*  88 */     transform3D.get(matrix4d);
/*  89 */     this.control.writeMatrix4d(paramDataOutput, matrix4d);
/*     */     
/*  91 */     paramDataOutput.writeInt(textureAttributes.getCombineRgbMode());
/*  92 */     paramDataOutput.writeInt(textureAttributes.getCombineAlphaMode());
/*  93 */     for (b = 0; b < 2; b++) {
/*  94 */       paramDataOutput.writeInt(textureAttributes.getCombineRgbSource(b));
/*  95 */       paramDataOutput.writeInt(textureAttributes.getCombineAlphaSource(b));
/*  96 */       paramDataOutput.writeInt(textureAttributes.getCombineRgbFunction(b));
/*  97 */       paramDataOutput.writeInt(textureAttributes.getCombineAlphaFunction(b));
/*     */     } 
/*  99 */     paramDataOutput.writeInt(textureAttributes.getCombineRgbScale());
/* 100 */     paramDataOutput.writeInt(textureAttributes.getCombineAlphaScale());
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/* 104 */     super.readObject(paramDataInput);
/* 105 */     TextureAttributes textureAttributes = (TextureAttributes)this.node;
/* 106 */     textureAttributes.setPerspectiveCorrectionMode(paramDataInput.readInt());
/* 107 */     textureAttributes.setTextureBlendColor(this.control.readColor4f(paramDataInput));
/* 108 */     int i = paramDataInput.readInt();
/* 109 */     int j = paramDataInput.readInt();
/* 110 */     int[][] arrayOfInt = new int[i][j];
/* 111 */     for (byte b1 = 0; b1 < i; b1++) {
/* 112 */       for (byte b = 0; b < j; b++)
/* 113 */         arrayOfInt[b1][b] = paramDataInput.readInt(); 
/* 114 */     }  if (i != 0)
/* 115 */       textureAttributes.setTextureColorTable(arrayOfInt); 
/* 116 */     textureAttributes.setTextureMode(paramDataInput.readInt());
/* 117 */     Matrix4d matrix4d = this.control.readMatrix4d(paramDataInput);
/* 118 */     Transform3D transform3D = new Transform3D(matrix4d);
/* 119 */     textureAttributes.setTextureTransform(transform3D);
/*     */     
/* 121 */     textureAttributes.setCombineRgbMode(paramDataInput.readInt());
/* 122 */     textureAttributes.setCombineAlphaMode(paramDataInput.readInt());
/* 123 */     for (byte b2 = 0; b2 < 2; b2++) {
/* 124 */       textureAttributes.setCombineRgbSource(b2, paramDataInput.readInt());
/* 125 */       textureAttributes.setCombineAlphaSource(b2, paramDataInput.readInt());
/* 126 */       textureAttributes.setCombineRgbFunction(b2, paramDataInput.readInt());
/* 127 */       textureAttributes.setCombineAlphaFunction(b2, paramDataInput.readInt());
/*     */     } 
/* 129 */     textureAttributes.setCombineRgbScale(paramDataInput.readInt());
/* 130 */     textureAttributes.setCombineAlphaScale(paramDataInput.readInt());
/*     */   }
/*     */ 
/*     */   
/* 134 */   protected SceneGraphObject createNode() { return new TextureAttributes(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\TextureAttributesState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */