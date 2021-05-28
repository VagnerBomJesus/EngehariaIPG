/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ShapeHolder
/*     */   extends ParserObject
/*     */ {
/*     */   Vector facetSizesList;
/*     */   Vector facetIndicesList;
/*     */   int[] facetIndicesArray;
/*  63 */   int currentNumIndices = 0;
/*     */   
/*     */   int numSurf;
/*     */   
/*     */   int numVerts;
/*     */   int[] facetIndices;
/*     */   int[] facetSizes;
/*     */   int[] normalIndices;
/*     */   float[] normalCoords;
/*     */   float[] coordsArray;
/*     */   
/*     */   ShapeHolder() {}
/*     */   
/*  76 */   ShapeHolder(int paramInt) { super(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void printGeometryData(LwoSurface paramLwoSurface) {
/*  86 */     byte b2 = 0;
/*  87 */     System.out.println("\nPolygon Data:");
/*  88 */     System.out.println("  Surface color = " + paramLwoSurface.color);
/*  89 */     System.out.println("  Surface diffuse = " + paramLwoSurface.diffuseColor);
/*  90 */     for (byte b1 = 0; b1 < this.facetSizes.length; b1++) {
/*  91 */       int i = this.facetSizes[b1];
/*  92 */       System.out.println("Facet of size " + i);
/*  93 */       for (byte b = 0; b < i; b++) {
/*  94 */         int j = 3 * this.facetIndices[b2++];
/*  95 */         System.out.println("x, y, z = " + this.coordsArray[j] + ", " + this.coordsArray[j + 1] + ", " + this.coordsArray[j + 2]);
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
/*     */   void createArrays(boolean paramBoolean) {
/* 109 */     debugOutputLn(1, "createArrays()");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     this.facetIndices = new int[this.currentNumIndices];
/* 118 */     if (paramBoolean) {
/* 119 */       int i = 0;
/* 120 */       byte b1 = 0;
/* 121 */       for (; b1 < this.facetSizesList.size(); 
/* 122 */         b1++) {
/* 123 */         int j = ((Integer)this.facetSizesList.elementAt(b1)).intValue();
/*     */         
/* 125 */         int[] arrayOfInt = new int[j];
/* 126 */         for (int k = 0; k < j; k++) {
/* 127 */           this.facetIndices[i + k] = this.facetIndicesArray[i + j - k - 1];
/*     */         }
/*     */ 
/*     */         
/* 131 */         i += j;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 136 */       for (byte b1 = 0; b1 < this.facetIndices.length; b1++) {
/* 137 */         this.facetIndices[b1] = this.facetIndicesArray[b1];
/*     */       }
/*     */     } 
/*     */     
/* 141 */     debugOutputLn(8, "facetIndices.len and coordsArray.len = " + this.facetIndices.length + ", " + this.coordsArray.length);
/*     */     
/* 143 */     if (((Integer)this.facetSizesList.elementAt(0)).intValue() < 3) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 148 */       debugOutputLn(8, "Using direct geometry because facetIndices is of size " + this.facetIndices.length + " and coordsArray is of length " + this.coordsArray.length);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 153 */       float[] arrayOfFloat = new float[this.facetIndices.length * 3];
/* 154 */       byte b1 = 0;
/* 155 */       for (byte b2 = 0; b2 < this.facetIndices.length; b2++) {
/* 156 */         arrayOfFloat[b1++] = this.coordsArray[this.facetIndices[b2] * 3];
/*     */         
/* 158 */         arrayOfFloat[b1++] = this.coordsArray[this.facetIndices[b2] * 3 + 1];
/*     */         
/* 160 */         arrayOfFloat[b1++] = this.coordsArray[this.facetIndices[b2] * 3 + 2];
/*     */       } 
/*     */       
/* 163 */       this.coordsArray = arrayOfFloat;
/* 164 */       this.facetIndices = null;
/*     */     } 
/*     */     
/* 167 */     this.facetSizes = new int[this.facetSizesList.size()];
/*     */     
/* 169 */     for (byte b = 0; b < this.facetSizes.length; b++) {
/* 170 */       this.facetSizes[b] = ((Integer)this.facetSizesList.elementAt(b)).intValue();
/*     */     }
/*     */ 
/*     */     
/* 174 */     this.facetSizesList = null;
/* 175 */     this.facetIndicesList = null;
/* 176 */     this.facetIndicesArray = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void nullify() {
/* 183 */     this.facetSizesList = null;
/* 184 */     this.facetIndicesList = null;
/* 185 */     this.facetIndicesArray = null;
/* 186 */     this.facetSizes = null;
/* 187 */     this.facetIndices = null;
/* 188 */     this.normalCoords = null;
/* 189 */     this.normalIndices = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void calcNormals() {
/* 198 */     debugOutputLn(1, "calcNormals()");
/* 199 */     debugOutputLn(8, "coordsLength, facetsizes.len = " + this.coordsArray.length + ", " + this.facetSizes.length);
/*     */     
/* 201 */     if (this.facetSizes[0] > 2) {
/*     */       
/* 203 */       if (this.facetIndices != null) {
/* 204 */         this.normalIndices = new int[this.facetIndices.length];
/* 205 */         this.normalCoords = new float[this.facetIndices.length * 3];
/*     */       } else {
/*     */         
/* 208 */         this.normalCoords = new float[this.coordsArray.length];
/*     */       } 
/* 210 */       debugOutputLn(8, "normalCoords, incides len = " + this.normalCoords.length + ", " + ((this.facetIndices == null) ? 0 : this.normalIndices.length));
/*     */ 
/*     */       
/* 213 */       int i = 0;
/* 214 */       byte b = -1;
/* 215 */       for (byte b1 = 0; b1 < this.facetSizes.length; b1++) {
/*     */         Vector3f vector3f;
/* 217 */         int j = this.facetSizes[b1];
/*     */ 
/*     */         
/* 220 */         if (j < 3) {
/*     */           
/* 222 */           vector3f = new Vector3f(0.0F, 0.0F, 1.0F);
/*     */         } else {
/*     */           byte b3;
/*     */           
/*     */           boolean bool2, bool1;
/* 227 */           if (this.facetIndices != null) {
/* 228 */             bool1 = this.facetIndices[i];
/* 229 */             bool2 = this.facetIndices[i + true];
/* 230 */             b3 = this.facetIndices[i + 2];
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 235 */             bool1 = i;
/* 236 */             bool2 = i + true;
/* 237 */             b3 = i + 2;
/*     */           } 
/* 239 */           Vector3f vector3f1 = new Vector3f(this.coordsArray[bool2 * 3] - this.coordsArray[bool1 * 3], this.coordsArray[bool2 * 3 + 1] - this.coordsArray[bool1 * 3 + 1], this.coordsArray[bool2 * 3 + 2] - this.coordsArray[bool1 * 3 + 2]);
/*     */ 
/*     */ 
/*     */           
/* 243 */           Vector3f vector3f2 = new Vector3f(this.coordsArray[b3 * 3] - this.coordsArray[bool1 * 3], this.coordsArray[b3 * 3 + 1] - this.coordsArray[bool1 * 3 + 1], this.coordsArray[b3 * 3 + 2] - this.coordsArray[bool1 * 3 + 2]);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 248 */           vector3f = new Vector3f();
/* 249 */           vector3f.cross(vector3f1, vector3f2);
/* 250 */           vector3f.normalize(vector3f);
/*     */         } 
/*     */         
/* 253 */         for (byte b2 = 0; b2 < j; b2++) {
/* 254 */           boolean bool = i + b2;
/* 255 */           this.normalCoords[bool * 3] = vector3f.x;
/* 256 */           this.normalCoords[bool * 3 + 1] = vector3f.y;
/* 257 */           this.normalCoords[bool * 3 + 2] = vector3f.z;
/* 258 */           if (this.facetIndices != null)
/* 259 */             this.normalIndices[bool] = bool; 
/*     */         } 
/* 261 */         i += j;
/*     */       } 
/*     */     } 
/* 264 */     debugOutputLn(1, "done with calcNormals()");
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3d\loaders\lw3d\ShapeHolder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */