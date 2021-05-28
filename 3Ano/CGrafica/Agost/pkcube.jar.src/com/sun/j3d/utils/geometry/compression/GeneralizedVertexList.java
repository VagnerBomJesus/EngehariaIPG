/*     */ package com.sun.j3d.utils.geometry.compression;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.media.j3d.GeometryArray;
/*     */ import javax.media.j3d.GeometryStripArray;
/*     */ import javax.media.j3d.LineStripArray;
/*     */ import javax.media.j3d.PointArray;
/*     */ import javax.media.j3d.TriangleArray;
/*     */ import javax.media.j3d.TriangleFanArray;
/*     */ import javax.media.j3d.TriangleStripArray;
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
/*     */ class GeneralizedVertexList
/*     */   implements GeneralizedStripFlags
/*     */ {
/*     */   private ArrayList vertices;
/*     */   private boolean hasColor3;
/*     */   private boolean hasColor4;
/*     */   private boolean hasNormals;
/*     */   private int frontFace;
/*     */   int stripCount;
/*     */   int vertexCount;
/*     */   int triangleCount;
/*     */   int vertexFormat;
/*     */   
/*     */   GeneralizedVertexList(int paramInt1, int paramInt2, int paramInt3) {
/*  77 */     this.hasColor3 = false;
/*  78 */     this.hasColor4 = false;
/*  79 */     this.hasNormals = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     this.frontFace = paramInt2;
/* 116 */     setVertexFormat(paramInt1);
/*     */     
/* 118 */     if (paramInt3 == 0) {
/* 119 */       this.vertices = new ArrayList();
/*     */     } else {
/* 121 */       this.vertices = new ArrayList(paramInt3);
/*     */     } 
/* 123 */     this.stripCount = 0;
/* 124 */     this.vertexCount = 0;
/* 125 */     this.triangleCount = 0;
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
/* 137 */   GeneralizedVertexList(int paramInt1, int paramInt2) { this(paramInt1, paramInt2, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setVertexFormat(int paramInt) {
/* 146 */     this.vertexFormat = paramInt;
/*     */     
/* 148 */     if ((paramInt & 0x2) != 0) {
/* 149 */       this.hasNormals = true;
/*     */     }
/* 151 */     if ((paramInt & 0xC) == 12) {
/* 152 */       this.hasColor4 = true;
/* 153 */     } else if ((paramInt & 0x4) == 4) {
/* 154 */       this.hasColor3 = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   class Vertex
/*     */   {
/*     */     int flag;
/*     */     
/*     */     Point3f coord;
/*     */     Color3f color3;
/*     */     Color4f color4;
/*     */     Vector3f normal;
/*     */     
/*     */     Vertex(Point3f param1Point3f, Vector3f param1Vector3f, Color4f param1Color4f, int param1Int) {
/* 169 */       this.flag = param1Int;
/* 170 */       this.coord = new Point3f(param1Point3f);
/*     */       
/* 172 */       if (this$0.hasNormals) {
/* 173 */         this.normal = new Vector3f(param1Vector3f);
/*     */       }
/* 175 */       if (this$0.hasColor3) {
/* 176 */         this.color3 = new Color3f(param1Color4f.x, param1Color4f.y, param1Color4f.z);
/*     */       }
/* 178 */       else if (this$0.hasColor4) {
/* 179 */         this.color4 = new Color4f(param1Color4f);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color4f paramColor4f, int paramInt) { this.vertices.add(new Vertex(paramPoint3f, paramVector3f, paramColor4f, paramInt)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   int size() { return this.vertices.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public int getFlagCount() { return this.vertices.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public int getFlag(int paramInt) { return ((Vertex)this.vertices.get(paramInt)).flag; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void copyVertexData(GeometryArray paramGeometryArray, GeneralizedStrip.IntList paramIntList) {
/* 214 */     Point3f[] arrayOfPoint3f = new Point3f[paramIntList.count];
/*     */     
/* 216 */     if (this.hasNormals) {
/* 217 */       Vector3f[] arrayOfVector3f = new Vector3f[paramIntList.count];
/* 218 */       if (this.hasColor3) {
/* 219 */         Color3f[] arrayOfColor3f = new Color3f[paramIntList.count];
/* 220 */         for (byte b = 0; b < paramIntList.count; b++) {
/* 221 */           Vertex vertex = (Vertex)this.vertices.get(paramIntList.ints[b]);
/* 222 */           arrayOfPoint3f[b] = vertex.coord;
/* 223 */           arrayOfVector3f[b] = vertex.normal;
/* 224 */           arrayOfColor3f[b] = vertex.color3;
/*     */         } 
/* 226 */         paramGeometryArray.setColors(0, arrayOfColor3f);
/*     */       }
/* 228 */       else if (this.hasColor4) {
/* 229 */         Color4f[] arrayOfColor4f = new Color4f[paramIntList.count];
/* 230 */         for (byte b = 0; b < paramIntList.count; b++) {
/* 231 */           Vertex vertex = (Vertex)this.vertices.get(paramIntList.ints[b]);
/* 232 */           arrayOfPoint3f[b] = vertex.coord;
/* 233 */           arrayOfVector3f[b] = vertex.normal;
/* 234 */           arrayOfColor4f[b] = vertex.color4;
/*     */         } 
/* 236 */         paramGeometryArray.setColors(0, arrayOfColor4f);
/*     */       } else {
/*     */         
/* 239 */         for (byte b = 0; b < paramIntList.count; b++) {
/* 240 */           Vertex vertex = (Vertex)this.vertices.get(paramIntList.ints[b]);
/* 241 */           arrayOfPoint3f[b] = vertex.coord;
/* 242 */           arrayOfVector3f[b] = vertex.normal;
/*     */         } 
/*     */       } 
/* 245 */       paramGeometryArray.setNormals(0, arrayOfVector3f);
/*     */     
/*     */     }
/* 248 */     else if (this.hasColor3) {
/* 249 */       Color3f[] arrayOfColor3f = new Color3f[paramIntList.count];
/* 250 */       for (byte b = 0; b < paramIntList.count; b++) {
/* 251 */         Vertex vertex = (Vertex)this.vertices.get(paramIntList.ints[b]);
/* 252 */         arrayOfPoint3f[b] = vertex.coord;
/* 253 */         arrayOfColor3f[b] = vertex.color3;
/*     */       } 
/* 255 */       paramGeometryArray.setColors(0, arrayOfColor3f);
/*     */     }
/* 257 */     else if (this.hasColor4) {
/* 258 */       Color4f[] arrayOfColor4f = new Color4f[paramIntList.count];
/* 259 */       for (byte b = 0; b < paramIntList.count; b++) {
/* 260 */         Vertex vertex = (Vertex)this.vertices.get(paramIntList.ints[b]);
/* 261 */         arrayOfPoint3f[b] = vertex.coord;
/* 262 */         arrayOfColor4f[b] = vertex.color4;
/*     */       } 
/* 264 */       paramGeometryArray.setColors(0, arrayOfColor4f);
/*     */     } else {
/*     */       
/* 267 */       for (byte b = 0; b < paramIntList.count; b++) {
/* 268 */         Vertex vertex = (Vertex)this.vertices.get(paramIntList.ints[b]);
/* 269 */         arrayOfPoint3f[b] = vertex.coord;
/*     */       } 
/*     */     } 
/*     */     
/* 273 */     paramGeometryArray.setCoordinates(0, arrayOfPoint3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PointArray toPointArray() {
/* 280 */     int i = this.vertices.size();
/*     */     
/* 282 */     if (i > 0) {
/* 283 */       PointArray pointArray = new PointArray(i, this.vertexFormat);
/* 284 */       GeneralizedStrip.IntList intList = new GeneralizedStrip.IntList(i);
/*     */       
/* 286 */       intList.fillAscending();
/* 287 */       copyVertexData(pointArray, intList);
/*     */       
/* 289 */       this.vertexCount += i;
/* 290 */       return pointArray;
/*     */     } 
/*     */     
/* 293 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TriangleArray toTriangleArray() {
/* 300 */     int[] arrayOfInt = GeneralizedStrip.toTriangles(this, this.frontFace);
/*     */     
/* 302 */     if (arrayOfInt != null) {
/*     */ 
/*     */ 
/*     */       
/* 306 */       TriangleArray triangleArray = new TriangleArray(arrayOfInt.length, this.vertexFormat);
/* 307 */       GeneralizedStrip.IntList intList = new GeneralizedStrip.IntList(arrayOfInt);
/* 308 */       copyVertexData(triangleArray, intList);
/*     */       
/* 310 */       this.vertexCount += arrayOfInt.length;
/* 311 */       this.triangleCount += arrayOfInt.length / 3;
/* 312 */       return triangleArray;
/*     */     } 
/* 314 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LineStripArray toLineStripArray() {
/* 321 */     GeneralizedStrip.StripArray stripArray = GeneralizedStrip.toLineStrips(this);
/*     */ 
/*     */     
/* 324 */     if (stripArray != null) {
/*     */       
/* 326 */       LineStripArray lineStripArray = new LineStripArray(stripArray.vertices.count, this.vertexFormat, stripArray.stripCounts.trim());
/*     */ 
/*     */ 
/*     */       
/* 330 */       copyVertexData(lineStripArray, stripArray.vertices);
/*     */       
/* 332 */       this.vertexCount += stripArray.vertices.count;
/* 333 */       this.stripCount += stripArray.stripCounts.count;
/* 334 */       return lineStripArray;
/*     */     } 
/* 336 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TriangleStripArray toTriangleStripArray() {
/* 343 */     GeneralizedStrip.StripArray stripArray = GeneralizedStrip.toTriangleStrips(this, this.frontFace);
/*     */ 
/*     */     
/* 346 */     if (stripArray != null) {
/*     */       
/* 348 */       TriangleStripArray triangleStripArray = new TriangleStripArray(stripArray.vertices.count, this.vertexFormat, stripArray.stripCounts.trim());
/*     */ 
/*     */ 
/*     */       
/* 352 */       copyVertexData(triangleStripArray, stripArray.vertices);
/*     */       
/* 354 */       this.vertexCount += stripArray.vertices.count;
/* 355 */       this.stripCount += stripArray.stripCounts.count;
/* 356 */       return triangleStripArray;
/*     */     } 
/* 358 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   GeometryStripArray[] toStripAndFanArrays() {
/* 368 */     GeneralizedStrip.StripArray[] arrayOfStripArray = GeneralizedStrip.toStripsAndFans(this, this.frontFace);
/*     */ 
/*     */     
/* 371 */     GeometryStripArray[] arrayOfGeometryStripArray = new GeometryStripArray[2];
/*     */     
/* 373 */     if (arrayOfStripArray[false] != null) {
/* 374 */       arrayOfGeometryStripArray[0] = new TriangleStripArray((arrayOfStripArray[0]).vertices.count, this.vertexFormat, (arrayOfStripArray[0]).stripCounts.trim());
/*     */ 
/*     */ 
/*     */       
/* 378 */       copyVertexData(arrayOfGeometryStripArray[0], (arrayOfStripArray[0]).vertices);
/*     */       
/* 380 */       this.vertexCount += (arrayOfStripArray[0]).vertices.count;
/* 381 */       this.stripCount += (arrayOfStripArray[0]).stripCounts.count;
/*     */     } 
/*     */     
/* 384 */     if (arrayOfStripArray[true] != null) {
/* 385 */       arrayOfGeometryStripArray[1] = new TriangleFanArray((arrayOfStripArray[1]).vertices.count, this.vertexFormat, (arrayOfStripArray[1]).stripCounts.trim());
/*     */ 
/*     */ 
/*     */       
/* 389 */       copyVertexData(arrayOfGeometryStripArray[1], (arrayOfStripArray[1]).vertices);
/*     */       
/* 391 */       this.vertexCount += (arrayOfStripArray[1]).vertices.count;
/* 392 */       this.stripCount += (arrayOfStripArray[1]).stripCounts.count;
/*     */     } 
/* 394 */     return arrayOfGeometryStripArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   GeometryArray[] toStripAndTriangleArrays() {
/* 404 */     GeneralizedStrip.StripArray[] arrayOfStripArray = GeneralizedStrip.toStripsAndTriangles(this, this.frontFace, 4, 12);
/*     */ 
/*     */     
/* 407 */     GeometryArray[] arrayOfGeometryArray = new GeometryArray[2];
/*     */     
/* 409 */     if (arrayOfStripArray[false] != null) {
/* 410 */       arrayOfGeometryArray[0] = new TriangleStripArray((arrayOfStripArray[0]).vertices.count, this.vertexFormat, (arrayOfStripArray[0]).stripCounts.trim());
/*     */ 
/*     */ 
/*     */       
/* 414 */       copyVertexData(arrayOfGeometryArray[0], (arrayOfStripArray[0]).vertices);
/*     */       
/* 416 */       this.vertexCount += (arrayOfStripArray[0]).vertices.count;
/* 417 */       this.stripCount += (arrayOfStripArray[0]).stripCounts.count;
/*     */     } 
/*     */     
/* 420 */     if (arrayOfStripArray[true] != null) {
/* 421 */       arrayOfGeometryArray[1] = new TriangleArray((arrayOfStripArray[1]).vertices.count, this.vertexFormat);
/*     */ 
/*     */       
/* 424 */       copyVertexData(arrayOfGeometryArray[1], (arrayOfStripArray[1]).vertices);
/* 425 */       this.triangleCount += (arrayOfStripArray[1]).vertices.count / 3;
/*     */     } 
/* 427 */     return arrayOfGeometryArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\geometry\compression\GeneralizedVertexList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */