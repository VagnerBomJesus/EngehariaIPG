/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*  34 */     this.hasColor3 = false;
/*  35 */     this.hasColor4 = false;
/*  36 */     this.hasNormals = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     this.frontFace = paramInt2;
/*  73 */     setVertexFormat(paramInt1);
/*     */     
/*  75 */     if (paramInt3 == 0) {
/*  76 */       this.vertices = new ArrayList();
/*     */     } else {
/*  78 */       this.vertices = new ArrayList(paramInt3);
/*     */     } 
/*  80 */     this.stripCount = 0;
/*  81 */     this.vertexCount = 0;
/*  82 */     this.triangleCount = 0;
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
/*  94 */   GeneralizedVertexList(int paramInt1, int paramInt2) { this(paramInt1, paramInt2, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setVertexFormat(int paramInt) {
/* 103 */     this.vertexFormat = paramInt;
/*     */     
/* 105 */     if ((paramInt & 0x2) != 0) {
/* 106 */       this.hasNormals = true;
/*     */     }
/* 108 */     if ((paramInt & 0x4) != 0) {
/* 109 */       if ((paramInt & 0x8) != 0) {
/* 110 */         this.hasColor4 = true;
/*     */       } else {
/* 112 */         this.hasColor3 = true;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   class Vertex
/*     */   {
/*     */     int flag;
/*     */     Point3f coord;
/*     */     Color3f color3;
/*     */     Color4f color4;
/*     */     Vector3f normal;
/*     */     
/*     */     Vertex(Point3f param1Point3f, Vector3f param1Vector3f, Color4f param1Color4f, int param1Int) {
/* 127 */       this.flag = param1Int;
/* 128 */       this.coord = new Point3f(param1Point3f);
/*     */       
/* 130 */       if (this$0.hasNormals) {
/* 131 */         this.normal = new Vector3f(param1Vector3f);
/*     */       }
/* 133 */       if (this$0.hasColor3) {
/* 134 */         this.color3 = new Color3f(param1Color4f.x, param1Color4f.y, param1Color4f.z);
/*     */       }
/* 136 */       else if (this$0.hasColor4) {
/* 137 */         this.color4 = new Color4f(param1Color4f);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   void addVertex(Point3f paramPoint3f, Vector3f paramVector3f, Color4f paramColor4f, int paramInt) { this.vertices.add(new Vertex(paramPoint3f, paramVector3f, paramColor4f, paramInt)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   int size() { return this.vertices.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   public int getFlagCount() { return this.vertices.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public int getFlag(int paramInt) { return ((Vertex)this.vertices.get(paramInt)).flag; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void copyVertexData(GeometryArray paramGeometryArray, GeneralizedStrip.IntList paramIntList) {
/* 172 */     Point3f[] arrayOfPoint3f = new Point3f[paramIntList.count];
/*     */     
/* 174 */     if (this.hasNormals) {
/* 175 */       Vector3f[] arrayOfVector3f = new Vector3f[paramIntList.count];
/* 176 */       if (this.hasColor3) {
/* 177 */         Color3f[] arrayOfColor3f = new Color3f[paramIntList.count];
/* 178 */         for (byte b = 0; b < paramIntList.count; b++) {
/* 179 */           Vertex vertex = (Vertex)this.vertices.get(paramIntList.ints[b]);
/* 180 */           arrayOfPoint3f[b] = vertex.coord;
/* 181 */           arrayOfVector3f[b] = vertex.normal;
/* 182 */           arrayOfColor3f[b] = vertex.color3;
/*     */         } 
/* 184 */         paramGeometryArray.setColors(0, arrayOfColor3f);
/*     */       }
/* 186 */       else if (this.hasColor4) {
/* 187 */         Color4f[] arrayOfColor4f = new Color4f[paramIntList.count];
/* 188 */         for (byte b = 0; b < paramIntList.count; b++) {
/* 189 */           Vertex vertex = (Vertex)this.vertices.get(paramIntList.ints[b]);
/* 190 */           arrayOfPoint3f[b] = vertex.coord;
/* 191 */           arrayOfVector3f[b] = vertex.normal;
/* 192 */           arrayOfColor4f[b] = vertex.color4;
/*     */         } 
/* 194 */         paramGeometryArray.setColors(0, arrayOfColor4f);
/*     */       } else {
/*     */         
/* 197 */         for (byte b = 0; b < paramIntList.count; b++) {
/* 198 */           Vertex vertex = (Vertex)this.vertices.get(paramIntList.ints[b]);
/* 199 */           arrayOfPoint3f[b] = vertex.coord;
/* 200 */           arrayOfVector3f[b] = vertex.normal;
/*     */         } 
/*     */       } 
/* 203 */       paramGeometryArray.setNormals(0, arrayOfVector3f);
/*     */     
/*     */     }
/* 206 */     else if (this.hasColor3) {
/* 207 */       Color3f[] arrayOfColor3f = new Color3f[paramIntList.count];
/* 208 */       for (byte b = 0; b < paramIntList.count; b++) {
/* 209 */         Vertex vertex = (Vertex)this.vertices.get(paramIntList.ints[b]);
/* 210 */         arrayOfPoint3f[b] = vertex.coord;
/* 211 */         arrayOfColor3f[b] = vertex.color3;
/*     */       } 
/* 213 */       paramGeometryArray.setColors(0, arrayOfColor3f);
/*     */     }
/* 215 */     else if (this.hasColor4) {
/* 216 */       Color4f[] arrayOfColor4f = new Color4f[paramIntList.count];
/* 217 */       for (byte b = 0; b < paramIntList.count; b++) {
/* 218 */         Vertex vertex = (Vertex)this.vertices.get(paramIntList.ints[b]);
/* 219 */         arrayOfPoint3f[b] = vertex.coord;
/* 220 */         arrayOfColor4f[b] = vertex.color4;
/*     */       } 
/* 222 */       paramGeometryArray.setColors(0, arrayOfColor4f);
/*     */     } else {
/*     */       
/* 225 */       for (byte b = 0; b < paramIntList.count; b++) {
/* 226 */         Vertex vertex = (Vertex)this.vertices.get(paramIntList.ints[b]);
/* 227 */         arrayOfPoint3f[b] = vertex.coord;
/*     */       } 
/*     */     } 
/*     */     
/* 231 */     paramGeometryArray.setCoordinates(0, arrayOfPoint3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PointArray toPointArray() {
/* 238 */     int i = this.vertices.size();
/*     */     
/* 240 */     if (i > 0) {
/* 241 */       PointArray pointArray = new PointArray(i, this.vertexFormat);
/* 242 */       GeneralizedStrip.IntList intList = new GeneralizedStrip.IntList(i);
/*     */       
/* 244 */       intList.fillAscending();
/* 245 */       copyVertexData(pointArray, intList);
/*     */       
/* 247 */       this.vertexCount += i;
/* 248 */       return pointArray;
/*     */     } 
/*     */     
/* 251 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TriangleArray toTriangleArray() {
/* 258 */     int[] arrayOfInt = GeneralizedStrip.toTriangles(this, this.frontFace);
/*     */     
/* 260 */     if (arrayOfInt != null) {
/*     */ 
/*     */ 
/*     */       
/* 264 */       TriangleArray triangleArray = new TriangleArray(arrayOfInt.length, this.vertexFormat);
/* 265 */       GeneralizedStrip.IntList intList = new GeneralizedStrip.IntList(arrayOfInt);
/* 266 */       copyVertexData(triangleArray, intList);
/*     */       
/* 268 */       this.vertexCount += arrayOfInt.length;
/* 269 */       this.triangleCount += arrayOfInt.length / 3;
/* 270 */       return triangleArray;
/*     */     } 
/* 272 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LineStripArray toLineStripArray() {
/* 279 */     GeneralizedStrip.StripArray stripArray = GeneralizedStrip.toLineStrips(this);
/*     */ 
/*     */     
/* 282 */     if (stripArray != null) {
/*     */       
/* 284 */       LineStripArray lineStripArray = new LineStripArray(stripArray.vertices.count, this.vertexFormat, stripArray.stripCounts.trim());
/*     */ 
/*     */ 
/*     */       
/* 288 */       copyVertexData(lineStripArray, stripArray.vertices);
/*     */       
/* 290 */       this.vertexCount += stripArray.vertices.count;
/* 291 */       this.stripCount += stripArray.stripCounts.count;
/* 292 */       return lineStripArray;
/*     */     } 
/* 294 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   TriangleStripArray toTriangleStripArray() {
/* 301 */     GeneralizedStrip.StripArray stripArray = GeneralizedStrip.toTriangleStrips(this, this.frontFace);
/*     */ 
/*     */     
/* 304 */     if (stripArray != null) {
/*     */       
/* 306 */       TriangleStripArray triangleStripArray = new TriangleStripArray(stripArray.vertices.count, this.vertexFormat, stripArray.stripCounts.trim());
/*     */ 
/*     */ 
/*     */       
/* 310 */       copyVertexData(triangleStripArray, stripArray.vertices);
/*     */       
/* 312 */       this.vertexCount += stripArray.vertices.count;
/* 313 */       this.stripCount += stripArray.stripCounts.count;
/* 314 */       return triangleStripArray;
/*     */     } 
/* 316 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   GeometryStripArray[] toStripAndFanArrays() {
/* 326 */     GeneralizedStrip.StripArray[] arrayOfStripArray = GeneralizedStrip.toStripsAndFans(this, this.frontFace);
/*     */ 
/*     */     
/* 329 */     GeometryStripArray[] arrayOfGeometryStripArray = new GeometryStripArray[2];
/*     */     
/* 331 */     if (arrayOfStripArray[false] != null) {
/* 332 */       arrayOfGeometryStripArray[0] = new TriangleStripArray((arrayOfStripArray[0]).vertices.count, this.vertexFormat, (arrayOfStripArray[0]).stripCounts.trim());
/*     */ 
/*     */ 
/*     */       
/* 336 */       copyVertexData(arrayOfGeometryStripArray[0], (arrayOfStripArray[0]).vertices);
/*     */       
/* 338 */       this.vertexCount += (arrayOfStripArray[0]).vertices.count;
/* 339 */       this.stripCount += (arrayOfStripArray[0]).stripCounts.count;
/*     */     } 
/*     */     
/* 342 */     if (arrayOfStripArray[true] != null) {
/* 343 */       arrayOfGeometryStripArray[1] = new TriangleFanArray((arrayOfStripArray[1]).vertices.count, this.vertexFormat, (arrayOfStripArray[1]).stripCounts.trim());
/*     */ 
/*     */ 
/*     */       
/* 347 */       copyVertexData(arrayOfGeometryStripArray[1], (arrayOfStripArray[1]).vertices);
/*     */       
/* 349 */       this.vertexCount += (arrayOfStripArray[1]).vertices.count;
/* 350 */       this.stripCount += (arrayOfStripArray[1]).stripCounts.count;
/*     */     } 
/* 352 */     return arrayOfGeometryStripArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   GeometryArray[] toStripAndTriangleArrays() {
/* 362 */     GeneralizedStrip.StripArray[] arrayOfStripArray = GeneralizedStrip.toStripsAndTriangles(this, this.frontFace, 4, 12);
/*     */ 
/*     */     
/* 365 */     GeometryArray[] arrayOfGeometryArray = new GeometryArray[2];
/*     */     
/* 367 */     if (arrayOfStripArray[false] != null) {
/* 368 */       arrayOfGeometryArray[0] = new TriangleStripArray((arrayOfStripArray[0]).vertices.count, this.vertexFormat, (arrayOfStripArray[0]).stripCounts.trim());
/*     */ 
/*     */ 
/*     */       
/* 372 */       copyVertexData(arrayOfGeometryArray[0], (arrayOfStripArray[0]).vertices);
/*     */       
/* 374 */       this.vertexCount += (arrayOfStripArray[0]).vertices.count;
/* 375 */       this.stripCount += (arrayOfStripArray[0]).stripCounts.count;
/*     */     } 
/*     */     
/* 378 */     if (arrayOfStripArray[true] != null) {
/* 379 */       arrayOfGeometryArray[1] = new TriangleArray((arrayOfStripArray[1]).vertices.count, this.vertexFormat);
/*     */ 
/*     */       
/* 382 */       copyVertexData(arrayOfGeometryArray[1], (arrayOfStripArray[1]).vertices);
/* 383 */       this.triangleCount += (arrayOfStripArray[1]).vertices.count / 3;
/*     */     } 
/* 385 */     return arrayOfGeometryArray;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\GeneralizedVertexList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */