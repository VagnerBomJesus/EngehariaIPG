/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.nio.Buffer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.IntBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NioImageBuffer
/*     */ {
/*     */   int width;
/*     */   int height;
/*     */   ImageType imageType;
/*     */   Buffer buffer;
/*     */   BufferType bufferType;
/*     */   int bytesPerPixel;
/*     */   int elementsPerPixel;
/*     */   
/*     */   public enum ImageType
/*     */   {
/*  45 */     TYPE_3BYTE_BGR,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     TYPE_3BYTE_RGB,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     TYPE_4BYTE_ABGR,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  69 */     TYPE_4BYTE_RGBA,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     TYPE_BYTE_GRAY,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     TYPE_INT_ARGB,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     TYPE_INT_BGR,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     TYPE_INT_RGB;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   enum BufferType
/*     */   {
/* 107 */     BYTE_BUFFER,
/* 108 */     INT_BUFFER;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NioImageBuffer(int paramInt1, int paramInt2, ImageType paramImageType) {
/* 146 */     processParams(paramInt1, paramInt2, paramImageType);
/*     */     
/* 148 */     ByteBuffer byteBuffer = ByteBuffer.allocateDirect(paramInt1 * paramInt2 * this.bytesPerPixel);
/* 149 */     switch (this.bufferType) {
/*     */       case TYPE_3BYTE_BGR:
/* 151 */         this.buffer = byteBuffer;
/*     */         return;
/*     */       
/*     */       case TYPE_3BYTE_RGB:
/* 155 */         this.buffer = byteBuffer.order(ByteOrder.nativeOrder()).asIntBuffer();
/*     */         return;
/*     */     } 
/*     */ 
/*     */     
/* 160 */     throw new AssertionError("missing case statement");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NioImageBuffer(int paramInt1, int paramInt2, ImageType paramImageType, Buffer paramBuffer) {
/* 199 */     processParams(paramInt1, paramInt2, paramImageType);
/* 200 */     setDataBuffer(paramBuffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   public int getWidth() { return this.width; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   public int getHeight() { return this.height; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 227 */   public ImageType getImageType() { return this.imageType; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDataBuffer(Buffer paramBuffer) {
/* 258 */     if (paramBuffer == null) {
/* 259 */       throw new NullPointerException();
/*     */     }
/*     */     
/* 262 */     if (paramBuffer.limit() != this.width * this.height * this.elementsPerPixel) {
/* 263 */       throw new IllegalArgumentException(J3dI18N.getString("NioImageBuffer3"));
/*     */     }
/*     */     
/* 266 */     switch (this.bufferType) {
/*     */       case TYPE_3BYTE_BGR:
/* 268 */         if (!(paramBuffer instanceof ByteBuffer)) {
/* 269 */           throw new IllegalArgumentException(J3dI18N.getString("NioImageBuffer4"));
/*     */         }
/* 271 */         this.buffer = ((ByteBuffer)paramBuffer).duplicate().rewind();
/*     */         return;
/*     */       
/*     */       case TYPE_3BYTE_RGB:
/* 275 */         if (!(paramBuffer instanceof IntBuffer)) {
/* 276 */           throw new IllegalArgumentException(J3dI18N.getString("NioImageBuffer4"));
/*     */         }
/*     */         
/* 279 */         if (((IntBuffer)paramBuffer).order() != ByteOrder.nativeOrder()) {
/* 280 */           throw new IllegalArgumentException(J3dI18N.getString("NioImageBuffer5"));
/*     */         }
/* 282 */         this.buffer = ((IntBuffer)paramBuffer).duplicate().rewind();
/*     */         return;
/*     */     } 
/*     */ 
/*     */     
/* 287 */     throw new AssertionError("missing case statement");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Buffer getDataBuffer() {
/*     */     IntBuffer intBuffer;
/* 299 */     ByteBuffer byteBuffer = null;
/*     */     
/* 301 */     switch (this.bufferType) {
/*     */       case TYPE_3BYTE_BGR:
/* 303 */         byteBuffer = ((ByteBuffer)this.buffer).duplicate();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 315 */         return byteBuffer.rewind();case TYPE_3BYTE_RGB: intBuffer = ((IntBuffer)this.buffer).duplicate(); return intBuffer.rewind();
/*     */     } 
/*     */     throw new AssertionError("missing case statement");
/*     */   }
/*     */ 
/*     */   
/*     */   private void processParams(int paramInt1, int paramInt2, ImageType paramImageType) {
/* 322 */     if (paramInt1 < 1) {
/* 323 */       throw new IllegalArgumentException(J3dI18N.getString("NioImageBuffer0"));
/*     */     }
/*     */     
/* 326 */     if (paramInt2 < 1) {
/* 327 */       throw new IllegalArgumentException(J3dI18N.getString("NioImageBuffer1"));
/*     */     }
/*     */     
/* 330 */     switch (paramImageType) {
/*     */       case TYPE_3BYTE_BGR:
/* 332 */         this.bufferType = BufferType.BYTE_BUFFER;
/* 333 */         this.bytesPerPixel = 3;
/* 334 */         this.elementsPerPixel = 3;
/*     */         break;
/*     */       
/*     */       case TYPE_3BYTE_RGB:
/* 338 */         this.bufferType = BufferType.BYTE_BUFFER;
/* 339 */         this.bytesPerPixel = 3;
/* 340 */         this.elementsPerPixel = 3;
/*     */         break;
/*     */       
/*     */       case TYPE_4BYTE_ABGR:
/* 344 */         this.bufferType = BufferType.BYTE_BUFFER;
/* 345 */         this.bytesPerPixel = 4;
/* 346 */         this.elementsPerPixel = 4;
/*     */         break;
/*     */       
/*     */       case TYPE_4BYTE_RGBA:
/* 350 */         this.bufferType = BufferType.BYTE_BUFFER;
/* 351 */         this.bytesPerPixel = 4;
/* 352 */         this.elementsPerPixel = 4;
/*     */         break;
/*     */       
/*     */       case TYPE_BYTE_GRAY:
/* 356 */         this.bufferType = BufferType.BYTE_BUFFER;
/* 357 */         this.bytesPerPixel = 1;
/* 358 */         this.elementsPerPixel = 1;
/*     */         break;
/*     */       
/*     */       case TYPE_INT_ARGB:
/*     */       case TYPE_INT_BGR:
/*     */       case TYPE_INT_RGB:
/* 364 */         this.bufferType = BufferType.INT_BUFFER;
/* 365 */         this.bytesPerPixel = 4;
/* 366 */         this.elementsPerPixel = 1;
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 371 */         throw new AssertionError("missing case statement");
/*     */     } 
/*     */     
/* 374 */     this.width = paramInt1;
/* 375 */     this.height = paramInt2;
/* 376 */     this.imageType = paramImageType;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\NioImageBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */