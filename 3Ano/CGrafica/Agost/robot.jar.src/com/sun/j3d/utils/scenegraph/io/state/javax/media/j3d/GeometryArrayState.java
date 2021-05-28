/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.internal.BufferWrapper;
/*     */ import com.sun.j3d.internal.ByteBufferWrapper;
/*     */ import com.sun.j3d.internal.ByteOrderWrapper;
/*     */ import com.sun.j3d.internal.DoubleBufferWrapper;
/*     */ import com.sun.j3d.internal.FloatBufferWrapper;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.GeometryArray;
/*     */ import javax.media.j3d.J3DBuffer;
/*     */ import javax.vecmath.Color3b;
/*     */ import javax.vecmath.Color3f;
/*     */ import javax.vecmath.Color4b;
/*     */ import javax.vecmath.Color4f;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.TexCoord2f;
/*     */ import javax.vecmath.TexCoord3f;
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
/*     */ public abstract class GeometryArrayState
/*     */   extends GeometryState
/*     */ {
/*     */   protected int vertexFormat;
/*     */   protected int vertexCount;
/*     */   protected int texCoordSetCount;
/*     */   protected int[] texCoordSetMap;
/*     */   private static final int FORMAT_NULL = 0;
/*     */   private static final int FORMAT_BYTE = 1;
/*     */   private static final int FORMAT_FLOAT = 2;
/*     */   private static final int FORMAT_DOUBLE = 3;
/*     */   private static final int FORMAT_3B = 4;
/*     */   private static final int FORMAT_4B = 5;
/*     */   private static final int FORMAT_2F = 6;
/*     */   private static final int FORMAT_3F = 7;
/*     */   private static final int FORMAT_4F = 8;
/*     */   private static final int FORMAT_2D = 9;
/*     */   private static final int FORMAT_3D = 10;
/*     */   
/*  82 */   public GeometryArrayState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  86 */     super.writeObject(paramDataOutput);
/*     */     
/*  88 */     boolean bool = ((this.vertexFormat & 0x800) != 0) ? 1 : 0;
/*     */     
/*  90 */     if ((this.vertexFormat & 0x100) != 0)
/*     */     
/*  92 */     { if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/*  93 */         paramDataOutput.writeInt(((GeometryArray)this.node).getInitialVertexIndex());
/*  94 */         if (!(this.node instanceof javax.media.j3d.GeometryStripArray)) {
/*  95 */           paramDataOutput.writeInt(((GeometryArray)this.node).getValidVertexCount());
/*     */         }
/*     */       } 
/*  98 */       if (bool)
/*  99 */       { FloatBufferWrapper floatBufferWrapper = new FloatBufferWrapper(((GeometryArray)this.node).getInterleavedVertexBuffer());
/*     */         
/* 101 */         float[] arrayOfFloat = new float[floatBufferWrapper.limit()];
/* 102 */         floatBufferWrapper.position(0);
/* 103 */         floatBufferWrapper.get(arrayOfFloat);
/* 104 */         writeFloatArray(paramDataOutput, arrayOfFloat); }
/* 105 */       else { writeFloatArray(paramDataOutput, ((GeometryArray)this.node).getInterleavedVertices()); }
/*     */        }
/* 107 */     else { boolean bool1 = ((this.vertexFormat & 0x80) != 0) ? 1 : 0;
/*     */       
/* 109 */       if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 110 */         if (!bool1)
/* 111 */           paramDataOutput.writeInt(((GeometryArray)this.node).getInitialVertexIndex()); 
/* 112 */         if (!(this.node instanceof javax.media.j3d.GeometryStripArray)) {
/* 113 */           paramDataOutput.writeInt(((GeometryArray)this.node).getValidVertexCount());
/*     */         }
/*     */       } 
/* 116 */       if ((this.vertexFormat & 0xC) == 12) {
/*     */         
/* 118 */         if (bool1)
/* 119 */         { if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 120 */             paramDataOutput.writeInt(((GeometryArray)this.node).getInitialColorIndex());
/*     */           }
/*     */           
/* 123 */           if (bool)
/* 124 */           { byte[] arrayOfByte; float[] arrayOfFloat; ByteBufferWrapper byteBufferWrapper; FloatBufferWrapper floatBufferWrapper; J3DBuffer j3DBuffer = ((GeometryArray)this.node).getColorRefBuffer();
/* 125 */             switch (BufferWrapper.getBufferType(j3DBuffer)) {
/*     */               case 2:
/* 127 */                 paramDataOutput.writeInt(1);
/* 128 */                 byteBufferWrapper = new ByteBufferWrapper(j3DBuffer);
/* 129 */                 arrayOfByte = new byte[byteBufferWrapper.limit()];
/* 130 */                 byteBufferWrapper.position(0);
/* 131 */                 byteBufferWrapper.get(arrayOfByte);
/* 132 */                 paramDataOutput.writeInt(arrayOfByte.length);
/* 133 */                 paramDataOutput.write(arrayOfByte);
/*     */                 break;
/*     */               
/*     */               case 3:
/* 137 */                 paramDataOutput.writeInt(2);
/* 138 */                 floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 139 */                 arrayOfFloat = new float[floatBufferWrapper.limit()];
/* 140 */                 floatBufferWrapper.position(0);
/* 141 */                 floatBufferWrapper.get(arrayOfFloat);
/* 142 */                 writeFloatArray(paramDataOutput, arrayOfFloat);
/*     */                 break;
/*     */               
/*     */               case 0:
/* 146 */                 paramDataOutput.writeInt(0);
/*     */                 break;
/*     */             } 
/*     */              }
/* 150 */           else if (((GeometryArray)this.node).getColorRef4f() != null)
/* 151 */           { paramDataOutput.writeInt(8);
/* 152 */             Color4f[] arrayOfColor4f = ((GeometryArray)this.node).getColorRef4f();
/* 153 */             float[] arrayOfFloat = new float[arrayOfColor4f.length * 4];
/* 154 */             for (byte b = 0; b < arrayOfColor4f.length; b++) {
/* 155 */               arrayOfFloat[b * 4 + 0] = (arrayOfColor4f[b]).x;
/* 156 */               arrayOfFloat[b * 4 + 1] = (arrayOfColor4f[b]).y;
/* 157 */               arrayOfFloat[b * 4 + 2] = (arrayOfColor4f[b]).z;
/* 158 */               arrayOfFloat[b * 4 + 3] = (arrayOfColor4f[b]).w;
/*     */             } 
/* 160 */             writeFloatArray(paramDataOutput, arrayOfFloat); }
/* 161 */           else if (((GeometryArray)this.node).getColorRefFloat() != null)
/* 162 */           { paramDataOutput.writeInt(2);
/* 163 */             writeFloatArray(paramDataOutput, ((GeometryArray)this.node).getColorRefFloat()); }
/* 164 */           else if (((GeometryArray)this.node).getColorRefByte() != null)
/* 165 */           { paramDataOutput.writeInt(1);
/* 166 */             byte[] arrayOfByte = ((GeometryArray)this.node).getColorRefByte();
/* 167 */             paramDataOutput.writeInt(arrayOfByte.length);
/* 168 */             paramDataOutput.write(arrayOfByte); }
/* 169 */           else if (((GeometryArray)this.node).getColorRef4b() != null)
/* 170 */           { paramDataOutput.writeInt(5);
/* 171 */             Color4b[] arrayOfColor4b = ((GeometryArray)this.node).getColorRef4b();
/* 172 */             paramDataOutput.writeInt(arrayOfColor4b.length);
/* 173 */             byte[] arrayOfByte = new byte[arrayOfColor4b.length * 4];
/* 174 */             for (byte b = 0; b < arrayOfColor4b.length; b++) {
/* 175 */               arrayOfByte[b * 4 + 0] = (arrayOfColor4b[b]).x;
/* 176 */               arrayOfByte[b * 4 + 1] = (arrayOfColor4b[b]).y;
/* 177 */               arrayOfByte[b * 4 + 2] = (arrayOfColor4b[b]).z;
/* 178 */               arrayOfByte[b * 4 + 3] = (arrayOfColor4b[b]).w;
/*     */             } 
/* 180 */             paramDataOutput.write(arrayOfByte); }
/* 181 */           else { paramDataOutput.writeInt(0); }
/*     */            }
/* 183 */         else { byte[] arrayOfByte = new byte[this.vertexCount * 4];
/* 184 */           ((GeometryArray)this.node).getColors(0, arrayOfByte);
/* 185 */           paramDataOutput.write(arrayOfByte); }
/*     */       
/* 187 */       } else if ((this.vertexFormat & 0x4) == 4) {
/*     */         
/* 189 */         if (bool1)
/* 190 */         { if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 191 */             paramDataOutput.writeInt(((GeometryArray)this.node).getInitialColorIndex());
/*     */           }
/*     */           
/* 194 */           if (bool)
/* 195 */           { float[] arrayOfFloat; byte[] arrayOfByte; ByteBufferWrapper byteBufferWrapper; FloatBufferWrapper floatBufferWrapper; J3DBuffer j3DBuffer = ((GeometryArray)this.node).getColorRefBuffer();
/* 196 */             switch (BufferWrapper.getBufferType(j3DBuffer)) {
/*     */               case 2:
/* 198 */                 paramDataOutput.writeInt(1);
/* 199 */                 byteBufferWrapper = new ByteBufferWrapper(j3DBuffer);
/* 200 */                 arrayOfByte = new byte[byteBufferWrapper.limit()];
/* 201 */                 byteBufferWrapper.position(0);
/* 202 */                 byteBufferWrapper.get(arrayOfByte);
/* 203 */                 paramDataOutput.writeInt(arrayOfByte.length);
/* 204 */                 paramDataOutput.write(arrayOfByte);
/*     */                 break;
/*     */               
/*     */               case 3:
/* 208 */                 paramDataOutput.writeInt(2);
/* 209 */                 floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 210 */                 arrayOfFloat = new float[floatBufferWrapper.limit()];
/* 211 */                 floatBufferWrapper.position(0);
/* 212 */                 floatBufferWrapper.get(arrayOfFloat);
/* 213 */                 writeFloatArray(paramDataOutput, arrayOfFloat);
/*     */                 break;
/*     */               
/*     */               case 0:
/* 217 */                 paramDataOutput.writeInt(0);
/*     */                 break;
/*     */             } 
/*     */              }
/* 221 */           else if (((GeometryArray)this.node).getColorRef3f() != null)
/* 222 */           { paramDataOutput.writeInt(7);
/* 223 */             Color3f[] arrayOfColor3f = ((GeometryArray)this.node).getColorRef3f();
/* 224 */             float[] arrayOfFloat = new float[arrayOfColor3f.length * 3];
/* 225 */             for (byte b = 0; b < arrayOfColor3f.length; b++) {
/* 226 */               arrayOfFloat[b * 3 + 0] = (arrayOfColor3f[b]).x;
/* 227 */               arrayOfFloat[b * 3 + 1] = (arrayOfColor3f[b]).y;
/* 228 */               arrayOfFloat[b * 3 + 2] = (arrayOfColor3f[b]).z;
/*     */             } 
/* 230 */             writeFloatArray(paramDataOutput, arrayOfFloat); }
/* 231 */           else if (((GeometryArray)this.node).getColorRefFloat() != null)
/* 232 */           { paramDataOutput.writeInt(2);
/* 233 */             writeFloatArray(paramDataOutput, ((GeometryArray)this.node).getColorRefFloat()); }
/* 234 */           else if (((GeometryArray)this.node).getColorRefByte() != null)
/* 235 */           { paramDataOutput.writeInt(1);
/* 236 */             byte[] arrayOfByte = ((GeometryArray)this.node).getColorRefByte();
/* 237 */             paramDataOutput.writeInt(arrayOfByte.length);
/* 238 */             paramDataOutput.write(arrayOfByte); }
/* 239 */           else if (((GeometryArray)this.node).getColorRef3b() != null)
/* 240 */           { paramDataOutput.writeInt(4);
/* 241 */             Color3b[] arrayOfColor3b = ((GeometryArray)this.node).getColorRef3b();
/* 242 */             paramDataOutput.writeInt(arrayOfColor3b.length);
/* 243 */             byte[] arrayOfByte = new byte[arrayOfColor3b.length * 3];
/* 244 */             for (byte b = 0; b < arrayOfColor3b.length; b++) {
/* 245 */               arrayOfByte[b * 3 + 0] = (arrayOfColor3b[b]).x;
/* 246 */               arrayOfByte[b * 3 + 1] = (arrayOfColor3b[b]).y;
/* 247 */               arrayOfByte[b * 3 + 2] = (arrayOfColor3b[b]).z;
/*     */             } 
/* 249 */             paramDataOutput.write(arrayOfByte); }
/* 250 */           else { paramDataOutput.writeInt(0); }
/*     */            }
/* 252 */         else { byte[] arrayOfByte = new byte[this.vertexCount * 3];
/* 253 */           ((GeometryArray)this.node).getColors(0, arrayOfByte);
/* 254 */           paramDataOutput.write(arrayOfByte); }
/*     */       
/*     */       } 
/*     */ 
/*     */       
/* 259 */       if ((this.vertexFormat & true) != 0)
/*     */       {
/* 261 */         if (bool1)
/* 262 */         { if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 263 */             paramDataOutput.writeInt(((GeometryArray)this.node).getInitialCoordIndex());
/*     */           }
/*     */           
/* 266 */           if (bool)
/* 267 */           { float[] arrayOfFloat; double[] arrayOfDouble; DoubleBufferWrapper doubleBufferWrapper; FloatBufferWrapper floatBufferWrapper; J3DBuffer j3DBuffer = ((GeometryArray)this.node).getCoordRefBuffer();
/* 268 */             switch (BufferWrapper.getBufferType(j3DBuffer)) {
/*     */               case 3:
/* 270 */                 paramDataOutput.writeInt(2);
/* 271 */                 floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 272 */                 arrayOfFloat = new float[floatBufferWrapper.limit()];
/* 273 */                 floatBufferWrapper.position(0);
/* 274 */                 floatBufferWrapper.get(arrayOfFloat);
/* 275 */                 writeFloatArray(paramDataOutput, arrayOfFloat);
/*     */                 break;
/*     */               
/*     */               case 4:
/* 279 */                 paramDataOutput.writeInt(3);
/* 280 */                 doubleBufferWrapper = new DoubleBufferWrapper(j3DBuffer);
/* 281 */                 arrayOfDouble = new double[doubleBufferWrapper.limit()];
/* 282 */                 doubleBufferWrapper.position(0);
/* 283 */                 doubleBufferWrapper.get(arrayOfDouble);
/* 284 */                 writeDoubleArray(paramDataOutput, arrayOfDouble);
/*     */                 break;
/*     */               
/*     */               case 0:
/* 288 */                 paramDataOutput.writeInt(0);
/*     */                 break;
/*     */             } 
/*     */              }
/* 292 */           else if (((GeometryArray)this.node).getCoordRef3f() != null)
/* 293 */           { paramDataOutput.writeInt(7);
/* 294 */             Point3f[] arrayOfPoint3f = ((GeometryArray)this.node).getCoordRef3f();
/* 295 */             float[] arrayOfFloat = new float[arrayOfPoint3f.length * 3];
/* 296 */             for (byte b = 0; b < arrayOfPoint3f.length; b++) {
/* 297 */               arrayOfFloat[b * 3 + 0] = (arrayOfPoint3f[b]).x;
/* 298 */               arrayOfFloat[b * 3 + 1] = (arrayOfPoint3f[b]).y;
/* 299 */               arrayOfFloat[b * 3 + 2] = (arrayOfPoint3f[b]).z;
/*     */             } 
/* 301 */             writeFloatArray(paramDataOutput, arrayOfFloat); }
/* 302 */           else if (((GeometryArray)this.node).getCoordRef3d() != null)
/* 303 */           { paramDataOutput.writeInt(10);
/* 304 */             Point3d[] arrayOfPoint3d = ((GeometryArray)this.node).getCoordRef3d();
/* 305 */             double[] arrayOfDouble = new double[arrayOfPoint3d.length * 3];
/* 306 */             for (byte b = 0; b < arrayOfPoint3d.length; b++) {
/* 307 */               arrayOfDouble[b * 3 + 0] = (arrayOfPoint3d[b]).x;
/* 308 */               arrayOfDouble[b * 3 + 1] = (arrayOfPoint3d[b]).y;
/* 309 */               arrayOfDouble[b * 3 + 2] = (arrayOfPoint3d[b]).z;
/*     */             } 
/* 311 */             writeDoubleArray(paramDataOutput, arrayOfDouble); }
/* 312 */           else if (((GeometryArray)this.node).getCoordRefFloat() != null)
/* 313 */           { paramDataOutput.writeInt(2);
/* 314 */             writeFloatArray(paramDataOutput, ((GeometryArray)this.node).getCoordRefFloat()); }
/* 315 */           else if (((GeometryArray)this.node).getCoordRefDouble() != null)
/* 316 */           { paramDataOutput.writeInt(3);
/* 317 */             writeDoubleArray(paramDataOutput, ((GeometryArray)this.node).getCoordRefDouble()); }
/* 318 */           else { paramDataOutput.writeInt(0); }
/*     */            }
/* 320 */         else { float[] arrayOfFloat = new float[this.vertexCount * 3];
/* 321 */           ((GeometryArray)this.node).getCoordinates(0, arrayOfFloat);
/* 322 */           writeFloatArray(paramDataOutput, arrayOfFloat); }
/*     */       
/*     */       }
/*     */       
/* 326 */       if ((this.vertexFormat & 0x2) != 0)
/*     */       {
/* 328 */         if (bool1)
/* 329 */         { if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 330 */             paramDataOutput.writeInt(((GeometryArray)this.node).getInitialNormalIndex());
/*     */           }
/*     */           
/* 333 */           if (bool)
/* 334 */           { J3DBuffer j3DBuffer = ((GeometryArray)this.node).getNormalRefBuffer();
/* 335 */             if (BufferWrapper.getBufferType(j3DBuffer) == 0) {
/* 336 */               paramDataOutput.writeInt(0);
/*     */             } else {
/* 338 */               paramDataOutput.writeInt(2);
/* 339 */               FloatBufferWrapper floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 340 */               float[] arrayOfFloat = new float[floatBufferWrapper.limit()];
/* 341 */               floatBufferWrapper.position(0);
/* 342 */               floatBufferWrapper.get(arrayOfFloat);
/* 343 */               writeFloatArray(paramDataOutput, arrayOfFloat);
/*     */             }  }
/* 345 */           else if (((GeometryArray)this.node).getNormalRef3f() != null)
/* 346 */           { paramDataOutput.writeInt(7);
/* 347 */             Vector3f[] arrayOfVector3f = ((GeometryArray)this.node).getNormalRef3f();
/* 348 */             float[] arrayOfFloat = new float[arrayOfVector3f.length * 3];
/* 349 */             for (byte b = 0; b < arrayOfVector3f.length; b++) {
/* 350 */               arrayOfFloat[b * 3 + 0] = (arrayOfVector3f[b]).x;
/* 351 */               arrayOfFloat[b * 3 + 1] = (arrayOfVector3f[b]).y;
/* 352 */               arrayOfFloat[b * 3 + 2] = (arrayOfVector3f[b]).z;
/*     */             } 
/* 354 */             writeFloatArray(paramDataOutput, arrayOfFloat); }
/* 355 */           else if (((GeometryArray)this.node).getNormalRefFloat() != null)
/* 356 */           { paramDataOutput.writeInt(2);
/* 357 */             writeFloatArray(paramDataOutput, ((GeometryArray)this.node).getNormalRefFloat()); }
/* 358 */           else { paramDataOutput.writeInt(0); }
/*     */            }
/* 360 */         else { float[] arrayOfFloat = new float[this.vertexCount * 3];
/* 361 */           ((GeometryArray)this.node).getNormals(0, arrayOfFloat);
/* 362 */           writeFloatArray(paramDataOutput, arrayOfFloat); }
/*     */       
/*     */       }
/*     */       
/* 366 */       if ((this.vertexFormat & 0x20) != 0) {
/*     */         
/* 368 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 369 */           if (bool1)
/* 370 */           { if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 371 */               paramDataOutput.writeInt(((GeometryArray)this.node).getInitialTexCoordIndex(b));
/*     */             }
/*     */             
/* 374 */             if (bool)
/* 375 */             { J3DBuffer j3DBuffer = ((GeometryArray)this.node).getTexCoordRefBuffer(b);
/* 376 */               if (BufferWrapper.getBufferType(j3DBuffer) == 0) {
/* 377 */                 paramDataOutput.writeInt(0);
/*     */               } else {
/* 379 */                 paramDataOutput.writeInt(2);
/* 380 */                 FloatBufferWrapper floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 381 */                 float[] arrayOfFloat = new float[floatBufferWrapper.limit()];
/* 382 */                 floatBufferWrapper.position(0);
/* 383 */                 floatBufferWrapper.get(arrayOfFloat);
/* 384 */                 writeFloatArray(paramDataOutput, arrayOfFloat);
/*     */               }  }
/* 386 */             else if (((GeometryArray)this.node).getTexCoordRef2f(b) != null)
/* 387 */             { paramDataOutput.writeInt(6);
/* 388 */               TexCoord2f[] arrayOfTexCoord2f = ((GeometryArray)this.node).getTexCoordRef2f(b);
/* 389 */               float[] arrayOfFloat = new float[arrayOfTexCoord2f.length * 2];
/* 390 */               for (byte b1 = 0; b1 < arrayOfTexCoord2f.length; b1++) {
/* 391 */                 arrayOfFloat[b1 * 2 + 0] = (arrayOfTexCoord2f[b1]).x;
/* 392 */                 arrayOfFloat[b1 * 2 + 1] = (arrayOfTexCoord2f[b1]).y;
/*     */               } 
/* 394 */               writeFloatArray(paramDataOutput, arrayOfFloat); }
/* 395 */             else if (((GeometryArray)this.node).getTexCoordRefFloat(b) != null)
/* 396 */             { paramDataOutput.writeInt(2);
/* 397 */               writeFloatArray(paramDataOutput, ((GeometryArray)this.node).getTexCoordRefFloat(b)); }
/* 398 */             else { paramDataOutput.writeInt(0); }
/*     */              }
/* 400 */           else { float[] arrayOfFloat = new float[this.vertexCount * 2];
/* 401 */             ((GeometryArray)this.node).getTextureCoordinates(b, 0, arrayOfFloat);
/* 402 */             writeFloatArray(paramDataOutput, arrayOfFloat); }
/*     */         
/*     */         } 
/* 405 */       } else if ((this.vertexFormat & 0x40) != 0) {
/*     */         
/* 407 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 408 */           if (bool1)
/* 409 */           { if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 410 */               paramDataOutput.writeInt(((GeometryArray)this.node).getInitialTexCoordIndex(b));
/*     */             }
/*     */             
/* 413 */             if (bool)
/* 414 */             { J3DBuffer j3DBuffer = ((GeometryArray)this.node).getTexCoordRefBuffer(b);
/* 415 */               if (BufferWrapper.getBufferType(j3DBuffer) == 0) {
/* 416 */                 paramDataOutput.writeInt(0);
/*     */               } else {
/* 418 */                 paramDataOutput.writeInt(2);
/* 419 */                 FloatBufferWrapper floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 420 */                 float[] arrayOfFloat = new float[floatBufferWrapper.limit()];
/* 421 */                 floatBufferWrapper.position(0);
/* 422 */                 floatBufferWrapper.get(arrayOfFloat);
/* 423 */                 writeFloatArray(paramDataOutput, arrayOfFloat);
/*     */               }  }
/* 425 */             else if (((GeometryArray)this.node).getTexCoordRef3f(b) != null)
/* 426 */             { paramDataOutput.writeInt(7);
/* 427 */               TexCoord3f[] arrayOfTexCoord3f = ((GeometryArray)this.node).getTexCoordRef3f(b);
/* 428 */               float[] arrayOfFloat = new float[arrayOfTexCoord3f.length * 3];
/* 429 */               for (byte b1 = 0; b1 < arrayOfTexCoord3f.length; b1++) {
/* 430 */                 arrayOfFloat[b1 * 3 + 0] = (arrayOfTexCoord3f[b1]).x;
/* 431 */                 arrayOfFloat[b1 * 3 + 1] = (arrayOfTexCoord3f[b1]).y;
/* 432 */                 arrayOfFloat[b1 * 3 + 2] = (arrayOfTexCoord3f[b1]).z;
/*     */               } 
/* 434 */               writeFloatArray(paramDataOutput, arrayOfFloat); }
/* 435 */             else if (((GeometryArray)this.node).getTexCoordRefFloat(b) != null)
/* 436 */             { paramDataOutput.writeInt(2);
/* 437 */               writeFloatArray(paramDataOutput, ((GeometryArray)this.node).getTexCoordRefFloat(b)); }
/* 438 */             else { paramDataOutput.writeInt(0); }
/*     */              }
/* 440 */           else { float[] arrayOfFloat = new float[this.vertexCount * 3];
/* 441 */             ((GeometryArray)this.node).getTextureCoordinates(b, 0, arrayOfFloat);
/* 442 */             writeFloatArray(paramDataOutput, arrayOfFloat); }
/*     */         
/*     */         } 
/* 445 */       } else if ((this.vertexFormat & 0x400) != 0) {
/*     */         
/* 447 */         for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 448 */           if (bool1)
/* 449 */           { if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 450 */               paramDataOutput.writeInt(((GeometryArray)this.node).getInitialTexCoordIndex(b));
/*     */             }
/*     */             
/* 453 */             if (bool)
/* 454 */             { J3DBuffer j3DBuffer = ((GeometryArray)this.node).getTexCoordRefBuffer(b);
/* 455 */               if (BufferWrapper.getBufferType(j3DBuffer) == 0) {
/* 456 */                 paramDataOutput.writeInt(0);
/*     */               } else {
/* 458 */                 paramDataOutput.writeInt(2);
/* 459 */                 FloatBufferWrapper floatBufferWrapper = new FloatBufferWrapper(j3DBuffer);
/* 460 */                 float[] arrayOfFloat = new float[floatBufferWrapper.limit()];
/* 461 */                 floatBufferWrapper.position(0);
/* 462 */                 floatBufferWrapper.get(arrayOfFloat);
/* 463 */                 writeFloatArray(paramDataOutput, arrayOfFloat);
/*     */               }
/*     */                }
/* 466 */             else if (((GeometryArray)this.node).getTexCoordRefFloat(b) != null)
/* 467 */             { paramDataOutput.writeInt(2);
/* 468 */               writeFloatArray(paramDataOutput, ((GeometryArray)this.node).getTexCoordRefFloat(b)); }
/* 469 */             else { paramDataOutput.writeInt(0); }
/*     */              }
/* 471 */           else { float[] arrayOfFloat = new float[this.vertexCount * 4];
/* 472 */             ((GeometryArray)this.node).getTextureCoordinates(b, 0, arrayOfFloat);
/* 473 */             writeFloatArray(paramDataOutput, arrayOfFloat); }
/*     */         
/*     */         } 
/*     */       }  }
/*     */   
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/* 481 */     super.readObject(paramDataInput);
/*     */     
/* 483 */     boolean bool = ((this.vertexFormat & 0x800) != 0) ? 1 : 0;
/*     */     
/* 485 */     if ((this.vertexFormat & 0x100) != 0)
/* 486 */     { if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 487 */         ((GeometryArray)this.node).setInitialVertexIndex(paramDataInput.readInt());
/* 488 */         if (!(this.node instanceof javax.media.j3d.GeometryStripArray))
/* 489 */           ((GeometryArray)this.node).setValidVertexCount(paramDataInput.readInt()); 
/*     */       } 
/* 491 */       if (bool)
/* 492 */       { float[] arrayOfFloat = readFloatArray(paramDataInput);
/* 493 */         ByteBufferWrapper byteBufferWrapper = ByteBufferWrapper.allocateDirect(arrayOfFloat.length * 4);
/*     */         
/* 495 */         FloatBufferWrapper floatBufferWrapper = byteBufferWrapper.order(ByteOrderWrapper.nativeOrder()).asFloatBuffer();
/*     */         
/* 497 */         floatBufferWrapper.put(arrayOfFloat);
/* 498 */         ((GeometryArray)this.node).setInterleavedVertexBuffer(floatBufferWrapper.getJ3DBuffer()); }
/* 499 */       else { ((GeometryArray)this.node).setInterleavedVertices(readFloatArray(paramDataInput)); }
/*     */        }
/* 501 */     else { boolean bool1 = ((this.vertexFormat & 0x80) != 0) ? 1 : 0;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 506 */       if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 507 */         if (!bool1)
/* 508 */           ((GeometryArray)this.node).setInitialVertexIndex(paramDataInput.readInt()); 
/* 509 */         if (!(this.node instanceof javax.media.j3d.GeometryStripArray)) {
/* 510 */           ((GeometryArray)this.node).setValidVertexCount(paramDataInput.readInt());
/*     */         }
/*     */       } 
/* 513 */       if ((this.vertexFormat & 0xC) == 12) {
/* 514 */         if (bool1) {
/* 515 */           if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 516 */             ((GeometryArray)this.node).setInitialColorIndex(paramDataInput.readInt());
/*     */           }
/*     */           
/* 519 */           if (bool) {
/* 520 */             FloatBufferWrapper floatBufferWrapper; ByteBufferWrapper byteBufferWrapper; byte[] arrayOfByte; float[] arrayOfFloat; switch (paramDataInput.readInt()) {
/*     */               case 1:
/* 522 */                 arrayOfByte = new byte[paramDataInput.readInt()];
/* 523 */                 paramDataInput.readFully(arrayOfByte);
/* 524 */                 byteBufferWrapper = ByteBufferWrapper.allocateDirect(arrayOfByte.length);
/*     */                 
/* 526 */                 byteBufferWrapper.put(arrayOfByte);
/* 527 */                 ((GeometryArray)this.node).setColorRefBuffer(byteBufferWrapper.getJ3DBuffer());
/*     */                 break;
/*     */               
/*     */               case 2:
/* 531 */                 arrayOfFloat = readFloatArray(paramDataInput);
/* 532 */                 byteBufferWrapper = ByteBufferWrapper.allocateDirect(arrayOfFloat.length * 4);
/*     */                 
/* 534 */                 floatBufferWrapper = byteBufferWrapper.order(ByteOrderWrapper.nativeOrder()).asFloatBuffer();
/*     */                 
/* 536 */                 floatBufferWrapper.put(arrayOfFloat);
/* 537 */                 ((GeometryArray)this.node).setColorRefBuffer(floatBufferWrapper.getJ3DBuffer()); break;
/*     */             } 
/*     */           } else {
/*     */             byte b; Color4f[] arrayOfColor4f; byte[] arrayOfByte2; Color4b[] arrayOfColor4b; byte[] arrayOfByte1;
/*     */             float[] arrayOfFloat;
/* 542 */             switch (paramDataInput.readInt()) {
/*     */               case 8:
/* 544 */                 arrayOfFloat = readFloatArray(paramDataInput);
/* 545 */                 arrayOfColor4f = new Color4f[arrayOfFloat.length / 4];
/* 546 */                 for (b = 0; b < arrayOfColor4f.length; b++) {
/* 547 */                   (arrayOfColor4f[b]).x = arrayOfFloat[b * 4 + 0];
/* 548 */                   (arrayOfColor4f[b]).y = arrayOfFloat[b * 4 + 1];
/* 549 */                   (arrayOfColor4f[b]).z = arrayOfFloat[b * 4 + 2];
/* 550 */                   (arrayOfColor4f[b]).w = arrayOfFloat[b * 4 + 3];
/*     */                 } 
/* 552 */                 ((GeometryArray)this.node).setColorRef4f(arrayOfColor4f);
/*     */                 break;
/*     */               
/*     */               case 2:
/* 556 */                 ((GeometryArray)this.node).setColorRefFloat(readFloatArray(paramDataInput));
/*     */                 break;
/*     */               case 1:
/* 559 */                 arrayOfByte1 = new byte[paramDataInput.readInt()];
/* 560 */                 paramDataInput.readFully(arrayOfByte1);
/* 561 */                 ((GeometryArray)this.node).setColorRefByte(arrayOfByte1);
/*     */                 break;
/*     */               
/*     */               case 5:
/* 565 */                 arrayOfColor4b = new Color4b[paramDataInput.readInt()];
/* 566 */                 arrayOfByte2 = new byte[arrayOfColor4b.length * 4];
/* 567 */                 paramDataInput.readFully(arrayOfByte2);
/* 568 */                 for (b = 0; b < arrayOfColor4b.length; b++) {
/* 569 */                   (arrayOfColor4b[b]).x = arrayOfByte2[b * 4 + 0];
/* 570 */                   (arrayOfColor4b[b]).y = arrayOfByte2[b * 4 + 1];
/* 571 */                   (arrayOfColor4b[b]).z = arrayOfByte2[b * 4 + 2];
/* 572 */                   (arrayOfColor4b[b]).w = arrayOfByte2[b * 4 + 3];
/*     */                 } 
/* 574 */                 ((GeometryArray)this.node).setColorRef4b(arrayOfColor4b);
/*     */                 break;
/*     */             } 
/*     */ 
/*     */           
/*     */           } 
/*     */         } else {
/* 581 */           byte[] arrayOfByte = new byte[this.vertexCount * 4];
/* 582 */           paramDataInput.readFully(arrayOfByte);
/* 583 */           ((GeometryArray)this.node).setColors(0, arrayOfByte);
/*     */         } 
/* 585 */       } else if ((this.vertexFormat & 0x4) == 4) {
/* 586 */         if (bool1) {
/* 587 */           if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 588 */             ((GeometryArray)this.node).setInitialColorIndex(paramDataInput.readInt());
/*     */           }
/*     */           
/* 591 */           if (bool) {
/* 592 */             FloatBufferWrapper floatBufferWrapper; ByteBufferWrapper byteBufferWrapper; byte[] arrayOfByte; float[] arrayOfFloat; switch (paramDataInput.readInt()) {
/*     */               case 1:
/* 594 */                 arrayOfByte = new byte[paramDataInput.readInt()];
/* 595 */                 paramDataInput.readFully(arrayOfByte);
/* 596 */                 byteBufferWrapper = ByteBufferWrapper.allocateDirect(arrayOfByte.length);
/*     */                 
/* 598 */                 byteBufferWrapper.put(arrayOfByte);
/* 599 */                 ((GeometryArray)this.node).setColorRefBuffer(byteBufferWrapper.getJ3DBuffer());
/*     */                 break;
/*     */               
/*     */               case 2:
/* 603 */                 arrayOfFloat = readFloatArray(paramDataInput);
/* 604 */                 byteBufferWrapper = ByteBufferWrapper.allocateDirect(arrayOfFloat.length * 4);
/*     */                 
/* 606 */                 floatBufferWrapper = byteBufferWrapper.order(ByteOrderWrapper.nativeOrder()).asFloatBuffer();
/*     */                 
/* 608 */                 floatBufferWrapper.put(arrayOfFloat);
/* 609 */                 ((GeometryArray)this.node).setColorRefBuffer(floatBufferWrapper.getJ3DBuffer()); break;
/*     */             } 
/*     */           } else {
/*     */             byte b; Color3f[] arrayOfColor3f; byte[] arrayOfByte2; float[] arrayOfFloat; Color3b[] arrayOfColor3b;
/*     */             byte[] arrayOfByte1;
/* 614 */             switch (paramDataInput.readInt()) {
/*     */               case 7:
/* 616 */                 arrayOfFloat = readFloatArray(paramDataInput);
/* 617 */                 arrayOfColor3f = new Color3f[arrayOfFloat.length / 3];
/* 618 */                 for (b = 0; b < arrayOfColor3f.length; b++) {
/* 619 */                   (arrayOfColor3f[b]).x = arrayOfFloat[b * 3 + 0];
/* 620 */                   (arrayOfColor3f[b]).y = arrayOfFloat[b * 3 + 1];
/* 621 */                   (arrayOfColor3f[b]).z = arrayOfFloat[b * 3 + 2];
/*     */                 } 
/* 623 */                 ((GeometryArray)this.node).setColorRef3f(arrayOfColor3f);
/*     */                 break;
/*     */               
/*     */               case 2:
/* 627 */                 ((GeometryArray)this.node).setColorRefFloat(readFloatArray(paramDataInput));
/*     */                 break;
/*     */               case 1:
/* 630 */                 arrayOfByte1 = new byte[paramDataInput.readInt()];
/* 631 */                 paramDataInput.readFully(arrayOfByte1);
/* 632 */                 ((GeometryArray)this.node).setColorRefByte(arrayOfByte1);
/*     */                 break;
/*     */               
/*     */               case 4:
/* 636 */                 arrayOfColor3b = new Color3b[paramDataInput.readInt()];
/* 637 */                 arrayOfByte2 = new byte[arrayOfColor3b.length * 3];
/* 638 */                 paramDataInput.readFully(arrayOfByte2);
/* 639 */                 for (b = 0; b < arrayOfColor3b.length; b++) {
/* 640 */                   (arrayOfColor3b[b]).x = arrayOfByte2[b * 3 + 0];
/* 641 */                   (arrayOfColor3b[b]).y = arrayOfByte2[b * 3 + 1];
/* 642 */                   (arrayOfColor3b[b]).z = arrayOfByte2[b * 3 + 2];
/*     */                 } 
/* 644 */                 ((GeometryArray)this.node).setColorRef3b(arrayOfColor3b);
/*     */                 break;
/*     */             } 
/*     */ 
/*     */           
/*     */           } 
/*     */         } else {
/* 651 */           byte[] arrayOfByte = new byte[this.vertexCount * 3];
/* 652 */           paramDataInput.readFully(arrayOfByte);
/* 653 */           ((GeometryArray)this.node).setColors(0, arrayOfByte);
/*     */         } 
/*     */       } 
/*     */       
/* 657 */       if ((this.vertexFormat & true) != 0) {
/* 658 */         if (bool1) {
/* 659 */           if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 660 */             ((GeometryArray)this.node).setInitialCoordIndex(paramDataInput.readInt());
/*     */           }
/*     */           
/* 663 */           if (bool) {
/* 664 */             DoubleBufferWrapper doubleBufferWrapper; FloatBufferWrapper floatBufferWrapper; ByteBufferWrapper byteBufferWrapper; float[] arrayOfFloat; double[] arrayOfDouble; switch (paramDataInput.readInt()) {
/*     */               case 2:
/* 666 */                 arrayOfFloat = readFloatArray(paramDataInput);
/* 667 */                 byteBufferWrapper = ByteBufferWrapper.allocateDirect(arrayOfFloat.length * 4);
/*     */                 
/* 669 */                 floatBufferWrapper = byteBufferWrapper.order(ByteOrderWrapper.nativeOrder()).asFloatBuffer();
/*     */                 
/* 671 */                 floatBufferWrapper.put(arrayOfFloat);
/* 672 */                 ((GeometryArray)this.node).setCoordRefBuffer(floatBufferWrapper.getJ3DBuffer());
/*     */                 break;
/*     */               
/*     */               case 3:
/* 676 */                 arrayOfDouble = readDoubleArray(paramDataInput);
/* 677 */                 byteBufferWrapper = ByteBufferWrapper.allocateDirect(arrayOfDouble.length * 4);
/*     */                 
/* 679 */                 doubleBufferWrapper = byteBufferWrapper.order(ByteOrderWrapper.nativeOrder()).asDoubleBuffer();
/*     */                 
/* 681 */                 doubleBufferWrapper.put(arrayOfDouble);
/* 682 */                 ((GeometryArray)this.node).setCoordRefBuffer(doubleBufferWrapper.getJ3DBuffer()); break;
/*     */             } 
/*     */           } else {
/*     */             byte b; Point3f[] arrayOfPoint3f; Point3d[] arrayOfPoint3d; float[] arrayOfFloat;
/*     */             double[] arrayOfDouble;
/* 687 */             switch (paramDataInput.readInt()) {
/*     */               case 7:
/* 689 */                 arrayOfFloat = readFloatArray(paramDataInput);
/* 690 */                 arrayOfPoint3f = new Point3f[arrayOfFloat.length / 3];
/* 691 */                 for (b = 0; b < arrayOfPoint3f.length; b++) {
/* 692 */                   (arrayOfPoint3f[b]).x = arrayOfFloat[b * 3 + 0];
/* 693 */                   (arrayOfPoint3f[b]).y = arrayOfFloat[b * 3 + 1];
/* 694 */                   (arrayOfPoint3f[b]).z = arrayOfFloat[b * 3 + 2];
/*     */                 } 
/* 696 */                 ((GeometryArray)this.node).setCoordRef3f(arrayOfPoint3f);
/*     */                 break;
/*     */               
/*     */               case 10:
/* 700 */                 arrayOfDouble = readDoubleArray(paramDataInput);
/* 701 */                 arrayOfPoint3d = new Point3d[arrayOfDouble.length / 3];
/* 702 */                 for (b = 0; b < arrayOfPoint3d.length; b++) {
/* 703 */                   (arrayOfPoint3d[b]).x = arrayOfDouble[b * 3 + 0];
/* 704 */                   (arrayOfPoint3d[b]).y = arrayOfDouble[b * 3 + 1];
/* 705 */                   (arrayOfPoint3d[b]).z = arrayOfDouble[b * 3 + 2];
/*     */                 } 
/* 707 */                 ((GeometryArray)this.node).setCoordRef3d(arrayOfPoint3d);
/*     */                 break;
/*     */               
/*     */               case 2:
/* 711 */                 ((GeometryArray)this.node).setCoordRefFloat(readFloatArray(paramDataInput));
/*     */                 break;
/*     */               case 3:
/* 714 */                 ((GeometryArray)this.node).setCoordRefDouble(readDoubleArray(paramDataInput));
/*     */                 break;
/*     */             } 
/*     */           
/*     */           } 
/*     */         } else {
/* 720 */           float[] arrayOfFloat = readFloatArray(paramDataInput);
/* 721 */           ((GeometryArray)this.node).setCoordinates(0, arrayOfFloat);
/*     */         } 
/*     */       }
/*     */       
/* 725 */       if ((this.vertexFormat & 0x2) != 0) {
/* 726 */         if (bool1) {
/* 727 */           if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 728 */             ((GeometryArray)this.node).setInitialNormalIndex(paramDataInput.readInt());
/*     */           }
/*     */           
/* 731 */           if (bool) {
/* 732 */             if (paramDataInput.readInt() == 2) {
/* 733 */               float[] arrayOfFloat = readFloatArray(paramDataInput);
/* 734 */               ByteBufferWrapper byteBufferWrapper = ByteBufferWrapper.allocateDirect(arrayOfFloat.length * 4);
/*     */               
/* 736 */               FloatBufferWrapper floatBufferWrapper = byteBufferWrapper.order(ByteOrderWrapper.nativeOrder()).asFloatBuffer();
/*     */               
/* 738 */               floatBufferWrapper.put(arrayOfFloat);
/* 739 */               ((GeometryArray)this.node).setNormalRefBuffer(floatBufferWrapper.getJ3DBuffer());
/*     */             } 
/*     */           } else {
/* 742 */             byte b; Vector3f[] arrayOfVector3f; float[] arrayOfFloat; switch (paramDataInput.readInt()) {
/*     */               case 7:
/* 744 */                 arrayOfFloat = readFloatArray(paramDataInput);
/* 745 */                 arrayOfVector3f = new Vector3f[arrayOfFloat.length / 3];
/* 746 */                 for (b = 0; b < arrayOfVector3f.length; b++) {
/* 747 */                   (arrayOfVector3f[b]).x = arrayOfFloat[b * 3 + 0];
/* 748 */                   (arrayOfVector3f[b]).y = arrayOfFloat[b * 3 + 1];
/* 749 */                   (arrayOfVector3f[b]).z = arrayOfFloat[b * 3 + 2];
/*     */                 } 
/* 751 */                 ((GeometryArray)this.node).setNormalRef3f(arrayOfVector3f);
/*     */                 break;
/*     */               
/*     */               case 2:
/* 755 */                 ((GeometryArray)this.node).setNormalRefFloat(readFloatArray(paramDataInput));
/*     */                 break;
/*     */             } 
/*     */           
/*     */           } 
/*     */         } else {
/* 761 */           float[] arrayOfFloat = readFloatArray(paramDataInput);
/* 762 */           ((GeometryArray)this.node).setNormals(0, arrayOfFloat);
/*     */         } 
/*     */       }
/*     */       
/* 766 */       if ((this.vertexFormat & 0x20) != 0 || (this.vertexFormat & 0x40) != 0 || (this.vertexFormat & 0x400) != 0)
/*     */       {
/*     */         
/* 769 */         if (bool1) {
/* 770 */           for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 771 */             if (!(this.node instanceof javax.media.j3d.IndexedGeometryArray)) {
/* 772 */               ((GeometryArray)this.node).setInitialTexCoordIndex(b, paramDataInput.readInt());
/*     */             }
/*     */             
/* 775 */             if (bool) {
/* 776 */               if (paramDataInput.readInt() == 2) {
/* 777 */                 float[] arrayOfFloat = readFloatArray(paramDataInput);
/* 778 */                 ByteBufferWrapper byteBufferWrapper = ByteBufferWrapper.allocateDirect(arrayOfFloat.length * 4);
/*     */                 
/* 780 */                 FloatBufferWrapper floatBufferWrapper = byteBufferWrapper.order(ByteOrderWrapper.nativeOrder()).asFloatBuffer();
/*     */                 
/* 782 */                 floatBufferWrapper.put(arrayOfFloat);
/* 783 */                 ((GeometryArray)this.node).setTexCoordRefBuffer(b, floatBufferWrapper.getJ3DBuffer());
/*     */               } 
/*     */             } else {
/*     */               byte b1; TexCoord3f[] arrayOfTexCoord3f; TexCoord2f[] arrayOfTexCoord2f; float[] arrayOfFloat;
/* 787 */               switch (paramDataInput.readInt()) {
/*     */                 case 6:
/* 789 */                   arrayOfFloat = readFloatArray(paramDataInput);
/* 790 */                   arrayOfTexCoord2f = new TexCoord2f[arrayOfFloat.length / 2];
/* 791 */                   for (b1 = 0; b1 < arrayOfTexCoord2f.length; b1++) {
/* 792 */                     (arrayOfTexCoord2f[b1]).x = arrayOfFloat[b1 * 2 + 0];
/* 793 */                     (arrayOfTexCoord2f[b1]).y = arrayOfFloat[b1 * 2 + 1];
/*     */                   } 
/* 795 */                   ((GeometryArray)this.node).setTexCoordRef2f(b, arrayOfTexCoord2f);
/*     */                   break;
/*     */                 
/*     */                 case 7:
/* 799 */                   arrayOfFloat = readFloatArray(paramDataInput);
/* 800 */                   arrayOfTexCoord3f = new TexCoord3f[arrayOfFloat.length / 3];
/* 801 */                   for (b1 = 0; b1 < arrayOfTexCoord3f.length; b1++) {
/* 802 */                     (arrayOfTexCoord3f[b1]).x = arrayOfFloat[b1 * 3 + 0];
/* 803 */                     (arrayOfTexCoord3f[b1]).y = arrayOfFloat[b1 * 3 + 1];
/* 804 */                     (arrayOfTexCoord3f[b1]).z = arrayOfFloat[b1 * 3 + 2];
/*     */                   } 
/* 806 */                   ((GeometryArray)this.node).setTexCoordRef3f(b, arrayOfTexCoord3f);
/*     */                   break;
/*     */                 
/*     */                 case 2:
/* 810 */                   arrayOfFloat = readFloatArray(paramDataInput);
/* 811 */                   ((GeometryArray)this.node).setTexCoordRefFloat(b, arrayOfFloat);
/*     */                   break;
/*     */               } 
/*     */             
/*     */             } 
/*     */           } 
/*     */         } else {
/* 818 */           for (byte b = 0; b < this.texCoordSetCount; b++) {
/* 819 */             float[] arrayOfFloat = readFloatArray(paramDataInput);
/* 820 */             ((GeometryArray)this.node).setTextureCoordinates(b, 0, arrayOfFloat);
/*     */           } 
/*     */         } 
/*     */       } }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   protected void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/* 829 */     this.vertexCount = ((GeometryArray)this.node).getVertexCount();
/* 830 */     this.vertexFormat = ((GeometryArray)this.node).getVertexFormat();
/* 831 */     this.texCoordSetCount = ((GeometryArray)this.node).getTexCoordSetCount();
/* 832 */     this.texCoordSetMap = new int[((GeometryArray)this.node).getTexCoordSetMapLength()];
/*     */     
/* 834 */     ((GeometryArray)this.node).getTexCoordSetMap(this.texCoordSetMap);
/*     */     
/* 836 */     paramDataOutput.writeInt(this.vertexCount);
/* 837 */     paramDataOutput.writeInt(this.vertexFormat);
/* 838 */     paramDataOutput.writeInt(this.texCoordSetCount);
/* 839 */     paramDataOutput.writeInt(this.texCoordSetMap.length);
/* 840 */     for (byte b = 0; b < this.texCoordSetMap.length; b++)
/* 841 */       paramDataOutput.writeInt(this.texCoordSetMap[b]); 
/* 842 */     super.writeConstructorParams(paramDataOutput);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readConstructorParams(DataInput paramDataInput) throws IOException {
/* 850 */     this.vertexCount = paramDataInput.readInt();
/* 851 */     this.vertexFormat = paramDataInput.readInt();
/* 852 */     this.texCoordSetCount = paramDataInput.readInt();
/* 853 */     this.texCoordSetMap = new int[paramDataInput.readInt()];
/* 854 */     for (byte b = 0; b < this.texCoordSetMap.length; b++) {
/* 855 */       this.texCoordSetMap[b] = paramDataInput.readInt();
/*     */     }
/* 857 */     super.readConstructorParams(paramDataInput);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeDoubleArray(DataOutput paramDataOutput, double[] paramArrayOfDouble) throws IOException {
/* 866 */     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 867 */     DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
/*     */     
/* 869 */     dataOutputStream.writeInt(paramArrayOfDouble.length);
/* 870 */     for (byte b = 0; b < paramArrayOfDouble.length; b++)
/* 871 */       dataOutputStream.writeDouble(paramArrayOfDouble[b]); 
/* 872 */     dataOutputStream.close();
/*     */     
/* 874 */     paramDataOutput.writeInt(byteArrayOutputStream.size());
/* 875 */     paramDataOutput.write(byteArrayOutputStream.toByteArray());
/*     */   }
/*     */   
/*     */   protected double[] readDoubleArray(DataInput paramDataInput) throws IOException {
/* 879 */     byte[] arrayOfByte = new byte[paramDataInput.readInt()];
/* 880 */     paramDataInput.readFully(arrayOfByte);
/* 881 */     ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
/* 882 */     DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
/*     */     
/* 884 */     double[] arrayOfDouble = new double[dataInputStream.readInt()];
/* 885 */     for (byte b = 0; b < arrayOfDouble.length; b++) {
/* 886 */       arrayOfDouble[b] = dataInputStream.readDouble();
/*     */     }
/* 888 */     dataInputStream.close();
/*     */     
/* 890 */     return arrayOfDouble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeFloatArray(DataOutput paramDataOutput, float[] paramArrayOfFloat) throws IOException {
/* 899 */     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 900 */     DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
/*     */     
/* 902 */     dataOutputStream.writeInt(paramArrayOfFloat.length);
/* 903 */     for (byte b = 0; b < paramArrayOfFloat.length; b++)
/* 904 */       dataOutputStream.writeFloat(paramArrayOfFloat[b]); 
/* 905 */     dataOutputStream.close();
/*     */     
/* 907 */     paramDataOutput.writeInt(byteArrayOutputStream.size());
/* 908 */     paramDataOutput.write(byteArrayOutputStream.toByteArray());
/*     */   }
/*     */   
/*     */   protected float[] readFloatArray(DataInput paramDataInput) throws IOException {
/* 912 */     byte[] arrayOfByte = new byte[paramDataInput.readInt()];
/* 913 */     paramDataInput.readFully(arrayOfByte);
/* 914 */     ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
/* 915 */     DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
/*     */     
/* 917 */     float[] arrayOfFloat = new float[dataInputStream.readInt()];
/* 918 */     for (byte b = 0; b < arrayOfFloat.length; b++) {
/* 919 */       arrayOfFloat[b] = dataInputStream.readFloat();
/*     */     }
/* 921 */     dataInputStream.close();
/*     */     
/* 923 */     return arrayOfFloat;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\GeometryArrayState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */