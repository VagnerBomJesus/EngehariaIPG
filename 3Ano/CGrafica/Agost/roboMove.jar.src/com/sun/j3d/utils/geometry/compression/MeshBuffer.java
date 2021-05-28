/*     */ package com.sun.j3d.utils.geometry.compression;
/*     */ 
/*     */ import javax.vecmath.Color3f;
/*     */ import javax.vecmath.Color4f;
/*     */ import javax.vecmath.Point3f;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MeshBuffer
/*     */ {
/*     */   static final int NOT_FOUND = -1;
/*     */   private static final int SIZE = 16;
/*  79 */   private static final int NAN_HASH = (new Point3f(NaNF, NaNF, NaNF)).hashCode();
/*     */ 
/*     */   
/*  82 */   private int topIndex = 15;
/*  83 */   private int[] positionIndices = new int[16];
/*  84 */   private int[] normalIndices = new int[16];
/*  85 */   private int[] colorIndices = new int[16];
/*     */   
/*  87 */   private int topPosition = 15;
/*  88 */   private int[] positionHashCodes = new int[16];
/*  89 */   private Point3f[] positions = new Point3f[16];
/*  90 */   private Vector3f[] normals = new Vector3f[16];
/*  91 */   private Color3f[] colors3 = new Color3f[16];
/*  92 */   private Color4f[] colors4 = new Color4f[16];
/*     */   
/*  94 */   private int topVertex = 15;
/*  95 */   private CompressionStreamVertex[] vertices = new CompressionStreamVertex[16];
/*     */ 
/*     */   
/*     */   MeshBuffer() {
/*  99 */     for (byte b = 0; b < 16; b++) {
/* 100 */       this.positionHashCodes[b] = NAN_HASH;
/*     */       
/* 102 */       this.positionIndices[b] = -1;
/* 103 */       this.normalIndices[b] = -1;
/* 104 */       this.colorIndices[b] = -1;
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
/*     */ 
/*     */   
/* 120 */   private static int nextTop(int paramInt) { return (paramInt + 1) % 16; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int flipOffset(int paramInt1, int paramInt2) {
/* 128 */     if (paramInt2 > paramInt1) paramInt2 -= 16; 
/* 129 */     return paramInt1 - paramInt2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void push(CompressionStreamVertex paramCompressionStreamVertex) {
/* 138 */     this.topVertex = nextTop(this.topVertex);
/* 139 */     this.vertices[this.topVertex] = paramCompressionStreamVertex;
/*     */   }
/*     */ 
/*     */   
/* 143 */   CompressionStreamVertex getVertex(int paramInt) { return this.vertices[flipOffset(this.topVertex, paramInt)]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void push(int paramInt1, int paramInt2) {
/* 151 */     this.topIndex = nextTop(this.topIndex);
/*     */     
/* 153 */     this.positionIndices[this.topIndex] = paramInt1;
/* 154 */     this.normalIndices[this.topIndex] = paramInt2;
/*     */   }
/*     */   
/*     */   void push(int paramInt1, int paramInt2, int paramInt3) {
/* 158 */     push(paramInt1, paramInt3);
/* 159 */     this.colorIndices[this.topIndex] = paramInt2;
/*     */   }
/*     */   
/*     */   int getMeshReference(int paramInt) {
/*     */     byte b;
/* 164 */     for (b = 0; b < 16 && 
/* 165 */       this.positionIndices[b] != paramInt; b++);
/*     */ 
/*     */     
/* 168 */     if (b == 16) return -1; 
/* 169 */     return flipOffset(this.topIndex, b);
/*     */   }
/*     */ 
/*     */   
/* 173 */   int getPositionIndex(int paramInt) { return this.positionIndices[flipOffset(this.topIndex, paramInt)]; }
/*     */ 
/*     */ 
/*     */   
/* 177 */   int getColorIndex(int paramInt) { return this.colorIndices[flipOffset(this.topIndex, paramInt)]; }
/*     */ 
/*     */ 
/*     */   
/* 181 */   int getNormalIndex(int paramInt) { return this.normalIndices[flipOffset(this.topIndex, paramInt)]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void push(Point3f paramPoint3f, Vector3f paramVector3f) {
/* 189 */     this.topPosition = nextTop(this.topPosition);
/*     */     
/* 191 */     this.positionHashCodes[this.topPosition] = paramPoint3f.hashCode();
/* 192 */     this.positions[this.topPosition] = paramPoint3f;
/* 193 */     this.normals[this.topPosition] = paramVector3f;
/*     */   }
/*     */   
/*     */   void push(Point3f paramPoint3f, Color3f paramColor3f, Vector3f paramVector3f) {
/* 197 */     push(paramPoint3f, paramVector3f);
/* 198 */     this.colors3[this.topPosition] = paramColor3f;
/*     */   }
/*     */   
/*     */   void push(Point3f paramPoint3f, Color4f paramColor4f, Vector3f paramVector3f) {
/* 202 */     push(paramPoint3f, paramVector3f);
/* 203 */     this.colors4[this.topPosition] = paramColor4f;
/*     */   }
/*     */   
/*     */   void push(Point3f paramPoint3f, Object paramObject, Vector3f paramVector3f) {
/* 207 */     push(paramPoint3f, paramVector3f);
/* 208 */     if (paramObject instanceof Color3f) {
/* 209 */       this.colors3[this.topPosition] = (Color3f)paramObject;
/*     */     } else {
/* 211 */       this.colors4[this.topPosition] = (Color4f)paramObject;
/*     */     } 
/*     */   }
/*     */   
/*     */   int getMeshReference(Point3f paramPoint3f) {
/* 216 */     int i = paramPoint3f.hashCode();
/*     */     byte b;
/* 218 */     for (b = 0; b < 16 && (
/* 219 */       this.positionHashCodes[b] != i || 
/* 220 */       !this.positions[b].equals(paramPoint3f)); b++);
/*     */ 
/*     */     
/* 223 */     if (b == 16) return -1; 
/* 224 */     return flipOffset(this.topPosition, b);
/*     */   }
/*     */ 
/*     */   
/* 228 */   Point3f getPosition(int paramInt) { return this.positions[flipOffset(this.topPosition, paramInt)]; }
/*     */ 
/*     */ 
/*     */   
/* 232 */   Color3f getColor3(int paramInt) { return this.colors3[flipOffset(this.topPosition, paramInt)]; }
/*     */ 
/*     */ 
/*     */   
/* 236 */   Color4f getColor4(int paramInt) { return this.colors4[flipOffset(this.topPosition, paramInt)]; }
/*     */ 
/*     */ 
/*     */   
/* 240 */   Vector3f getNormal(int paramInt) { return this.normals[flipOffset(this.topPosition, paramInt)]; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\geometry\compression\MeshBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */