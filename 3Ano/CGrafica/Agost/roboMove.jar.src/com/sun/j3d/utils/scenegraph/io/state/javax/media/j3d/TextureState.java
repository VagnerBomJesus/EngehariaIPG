/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.ImageComponent;
/*     */ import javax.media.j3d.Texture;
/*     */ import javax.vecmath.Color4f;
/*     */ import javax.vecmath.Point3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TextureState
/*     */   extends NodeComponentState
/*     */ {
/*     */   private int[] imageComponents;
/*     */   protected int width;
/*     */   protected int height;
/*     */   protected int format;
/*     */   protected int mipMapMode;
/*     */   protected int boundaryWidth;
/*     */   
/*     */   public TextureState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  70 */     super(paramSymbolTableData, paramController);
/*     */     
/*  72 */     if (this.node != null && 
/*  73 */       !(this.node instanceof javax.media.j3d.TextureCubeMap)) {
/*  74 */       ImageComponent[] arrayOfImageComponent = ((Texture)this.node).getImages();
/*  75 */       this.imageComponents = new int[arrayOfImageComponent.length];
/*  76 */       for (byte b = 0; b < arrayOfImageComponent.length; b++) {
/*  77 */         this.imageComponents[b] = paramController.getSymbolTable().addReference(arrayOfImageComponent[b]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  85 */     super.writeConstructorParams(paramDataOutput);
/*  86 */     paramDataOutput.writeInt(((Texture)this.node).getMipMapMode());
/*  87 */     paramDataOutput.writeInt(((Texture)this.node).getWidth());
/*  88 */     paramDataOutput.writeInt(((Texture)this.node).getHeight());
/*  89 */     paramDataOutput.writeInt(((Texture)this.node).getFormat());
/*  90 */     paramDataOutput.writeInt(((Texture)this.node).getBoundaryWidth());
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  94 */     super.readConstructorParams(paramDataInput);
/*     */     
/*  96 */     this.mipMapMode = paramDataInput.readInt();
/*  97 */     this.width = paramDataInput.readInt();
/*  98 */     this.height = paramDataInput.readInt();
/*  99 */     this.format = paramDataInput.readInt();
/* 100 */     this.boundaryWidth = paramDataInput.readInt();
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 104 */     super.writeObject(paramDataOutput);
/* 105 */     Texture texture = (Texture)this.node;
/* 106 */     Color4f color4f = new Color4f();
/* 107 */     texture.getBoundaryColor(color4f);
/* 108 */     this.control.writeColor4f(paramDataOutput, color4f);
/* 109 */     paramDataOutput.writeInt(texture.getBoundaryModeS());
/* 110 */     paramDataOutput.writeInt(texture.getBoundaryModeT());
/* 111 */     paramDataOutput.writeBoolean(texture.getEnable());
/*     */     
/* 113 */     paramDataOutput.writeInt(this.imageComponents.length);
/* 114 */     for (byte b = 0; b < this.imageComponents.length; b++) {
/* 115 */       paramDataOutput.writeInt(this.imageComponents[b]);
/*     */     }
/* 117 */     paramDataOutput.writeInt(texture.getMagFilter());
/* 118 */     paramDataOutput.writeInt(texture.getMinFilter());
/* 119 */     paramDataOutput.writeInt(texture.getBaseLevel());
/* 120 */     paramDataOutput.writeInt(texture.getMaximumLevel());
/* 121 */     paramDataOutput.writeFloat(texture.getMinimumLOD());
/* 122 */     paramDataOutput.writeFloat(texture.getMaximumLOD());
/*     */     
/* 124 */     Point3f point3f = new Point3f();
/* 125 */     texture.getLodOffset(point3f);
/* 126 */     this.control.writePoint3f(paramDataOutput, point3f);
/*     */     
/* 128 */     paramDataOutput.writeInt(texture.getAnisotropicFilterMode());
/* 129 */     paramDataOutput.writeFloat(texture.getAnisotropicFilterDegree());
/*     */     
/* 131 */     int i = texture.getSharpenTextureFuncPointsCount();
/* 132 */     paramDataOutput.writeInt(i);
/* 133 */     if (i > 0) {
/* 134 */       float[] arrayOfFloat1 = new float[i];
/* 135 */       float[] arrayOfFloat2 = new float[i];
/* 136 */       texture.getSharpenTextureFunc(arrayOfFloat1, arrayOfFloat2);
/* 137 */       for (byte b1 = 0; b1 < i; b1++) {
/* 138 */         paramDataOutput.writeFloat(arrayOfFloat1[b1]);
/* 139 */         paramDataOutput.writeFloat(arrayOfFloat2[b1]);
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     i = texture.getFilter4FuncPointsCount();
/* 144 */     paramDataOutput.writeInt(i);
/* 145 */     if (i >= 4) {
/* 146 */       float[] arrayOfFloat = new float[i];
/* 147 */       texture.getFilter4Func(arrayOfFloat);
/* 148 */       for (byte b1 = 0; b1 < i; b1++) {
/* 149 */         paramDataOutput.writeFloat(arrayOfFloat[b1]);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/* 155 */     super.readObject(paramDataInput);
/* 156 */     Texture texture = (Texture)this.node;
/* 157 */     texture.setBoundaryColor(this.control.readColor4f(paramDataInput));
/* 158 */     texture.setBoundaryModeS(paramDataInput.readInt());
/* 159 */     texture.setBoundaryModeT(paramDataInput.readInt());
/* 160 */     texture.setEnable(paramDataInput.readBoolean());
/*     */     
/* 162 */     this.imageComponents = new int[paramDataInput.readInt()]; int i;
/* 163 */     for (i = 0; i < this.imageComponents.length; i++) {
/* 164 */       this.imageComponents[i] = paramDataInput.readInt();
/*     */     }
/* 166 */     i = paramDataInput.readInt();
/*     */     try {
/* 168 */       texture.setMagFilter(i);
/* 169 */     } catch (IllegalArgumentException illegalArgumentException) {
/*     */ 
/*     */ 
/*     */       
/* 173 */       if (i == 5) {
/* 174 */         texture.setMagFilter(3);
/* 175 */       } else if (i == 4) {
/* 176 */         texture.setMagFilter(2);
/*     */       } else {
/* 178 */         texture.setMagFilter(0);
/*     */       } 
/*     */     } 
/* 181 */     texture.setMinFilter(paramDataInput.readInt());
/*     */     
/* 183 */     texture.setBaseLevel(paramDataInput.readInt());
/* 184 */     texture.setMaximumLevel(paramDataInput.readInt());
/* 185 */     texture.setMinimumLOD(paramDataInput.readFloat());
/* 186 */     texture.setMaximumLOD(paramDataInput.readFloat());
/* 187 */     texture.setLodOffset(this.control.readPoint3f(paramDataInput));
/* 188 */     texture.setAnisotropicFilterMode(paramDataInput.readInt());
/* 189 */     texture.setAnisotropicFilterDegree(paramDataInput.readFloat());
/*     */     
/* 191 */     int j = paramDataInput.readInt();
/* 192 */     if (j > 0) {
/* 193 */       float[] arrayOfFloat1 = new float[j];
/* 194 */       float[] arrayOfFloat2 = new float[j];
/* 195 */       for (byte b = 0; b < j; b++) {
/* 196 */         arrayOfFloat1[b] = paramDataInput.readFloat();
/* 197 */         arrayOfFloat2[b] = paramDataInput.readFloat();
/*     */       } 
/* 199 */       texture.setSharpenTextureFunc(arrayOfFloat1, arrayOfFloat2);
/*     */     } 
/*     */     
/* 202 */     j = paramDataInput.readInt();
/* 203 */     if (j >= 4) {
/* 204 */       float[] arrayOfFloat = new float[j];
/* 205 */       for (byte b = 0; b < j; b++) {
/* 206 */         arrayOfFloat[b] = paramDataInput.readFloat();
/*     */       }
/* 208 */       texture.setFilter4Func(arrayOfFloat);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addSubReference() {
/* 213 */     if (!(this.node instanceof javax.media.j3d.TextureCubeMap))
/* 214 */       for (byte b = 0; b < this.imageComponents.length; b++) {
/* 215 */         this.control.getSymbolTable().incNodeComponentRefCount(this.imageComponents[b]);
/*     */       } 
/*     */   }
/*     */   
/*     */   public void buildGraph() {
/* 220 */     if (!(this.node instanceof javax.media.j3d.TextureCubeMap)) {
/* 221 */       for (byte b = 0; b < this.imageComponents.length; b++) {
/* 222 */         ((Texture)this.node).setImage(b, (ImageComponent)this.control.getSymbolTable().getJ3dNode(this.imageComponents[b]));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 227 */     super.buildGraph();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\TextureState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */