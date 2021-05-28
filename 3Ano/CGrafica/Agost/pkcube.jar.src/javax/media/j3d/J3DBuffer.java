/*     */ package javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.internal.BufferWrapper;
/*     */ import com.sun.j3d.internal.ByteBufferWrapper;
/*     */ import com.sun.j3d.internal.DoubleBufferWrapper;
/*     */ import com.sun.j3d.internal.FloatBufferWrapper;
/*     */ import java.nio.Buffer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import java.nio.ShortBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class J3DBuffer
/*     */ {
/*     */   static final int TYPE_NULL = 0;
/*     */   static final int TYPE_UNKNOWN = 1;
/*     */   static final int TYPE_BYTE = 2;
/*     */   static final int TYPE_CHAR = 3;
/*     */   static final int TYPE_SHORT = 4;
/*     */   static final int TYPE_INT = 5;
/*     */   static final int TYPE_LONG = 6;
/*     */   static final int TYPE_FLOAT = 7;
/*     */   static final int TYPE_DOUBLE = 8;
/*     */   static boolean unsupportedOperation = false;
/*  52 */   private Buffer originalBuffer = null;
/*  53 */   private BufferWrapper bufferImpl = null;
/*  54 */   private int bufferType = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public J3DBuffer() { this(null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public J3DBuffer(Buffer paramBuffer) {
/*  85 */     if (unsupportedOperation) {
/*  86 */       throw new UnsupportedOperationException(J3dI18N.getString("J3DBuffer0"));
/*     */     }
/*  88 */     setBuffer(paramBuffer);
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
/*     */   public void setBuffer(Buffer paramBuffer) {
/*     */     DoubleBuffer doubleBuffer;
/*     */     FloatBuffer floatBuffer;
/*     */     ByteBuffer byteBuffer;
/* 104 */     byte b = 0;
/* 105 */     boolean bool = false;
/* 106 */     ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
/*     */     
/* 108 */     if (paramBuffer == null) {
/* 109 */       b = 0;
/*     */     }
/* 111 */     else if (paramBuffer instanceof ByteBuffer) {
/* 112 */       b = 2;
/* 113 */       bool = ((ByteBuffer)paramBuffer).isDirect();
/* 114 */       byteOrder = ((ByteBuffer)paramBuffer).order();
/*     */     }
/* 116 */     else if (paramBuffer instanceof CharBuffer) {
/* 117 */       b = 3;
/* 118 */       bool = ((CharBuffer)paramBuffer).isDirect();
/* 119 */       byteOrder = ((CharBuffer)paramBuffer).order();
/*     */     }
/* 121 */     else if (paramBuffer instanceof ShortBuffer) {
/* 122 */       b = 4;
/* 123 */       bool = ((ShortBuffer)paramBuffer).isDirect();
/* 124 */       byteOrder = ((ShortBuffer)paramBuffer).order();
/*     */     }
/* 126 */     else if (paramBuffer instanceof IntBuffer) {
/* 127 */       b = 5;
/* 128 */       bool = ((IntBuffer)paramBuffer).isDirect();
/* 129 */       byteOrder = ((IntBuffer)paramBuffer).order();
/*     */     }
/* 131 */     else if (paramBuffer instanceof LongBuffer) {
/* 132 */       b = 6;
/* 133 */       bool = ((LongBuffer)paramBuffer).isDirect();
/* 134 */       byteOrder = ((LongBuffer)paramBuffer).order();
/*     */     }
/* 136 */     else if (paramBuffer instanceof FloatBuffer) {
/* 137 */       b = 7;
/* 138 */       bool = ((FloatBuffer)paramBuffer).isDirect();
/* 139 */       byteOrder = ((FloatBuffer)paramBuffer).order();
/*     */     }
/* 141 */     else if (paramBuffer instanceof DoubleBuffer) {
/* 142 */       b = 8;
/* 143 */       bool = ((DoubleBuffer)paramBuffer).isDirect();
/* 144 */       byteOrder = ((DoubleBuffer)paramBuffer).order();
/*     */     } else {
/*     */       
/* 147 */       b = 1;
/*     */     } 
/*     */ 
/*     */     
/* 151 */     if (paramBuffer != null) {
/* 152 */       if (!bool) {
/* 153 */         throw new IllegalArgumentException(J3dI18N.getString("J3DBuffer1"));
/*     */       }
/*     */       
/* 156 */       if (byteOrder != ByteOrder.nativeOrder()) {
/* 157 */         throw new IllegalArgumentException(J3dI18N.getString("J3DBuffer2"));
/*     */       }
/*     */     } 
/*     */     
/* 161 */     this.bufferType = b;
/* 162 */     this.originalBuffer = paramBuffer;
/*     */ 
/*     */ 
/*     */     
/* 166 */     switch (this.bufferType) {
/*     */       case 2:
/* 168 */         byteBuffer = ((ByteBuffer)paramBuffer).asReadOnlyBuffer();
/*     */         
/* 170 */         byteBuffer.rewind();
/* 171 */         this.bufferImpl = new ByteBufferWrapper(byteBuffer);
/*     */         return;
/*     */       case 7:
/* 174 */         floatBuffer = ((FloatBuffer)paramBuffer).asReadOnlyBuffer();
/*     */         
/* 176 */         floatBuffer.rewind();
/* 177 */         this.bufferImpl = new FloatBufferWrapper(floatBuffer);
/*     */         return;
/*     */       case 8:
/* 180 */         doubleBuffer = ((DoubleBuffer)paramBuffer).asReadOnlyBuffer();
/*     */         
/* 182 */         doubleBuffer.rewind();
/* 183 */         this.bufferImpl = new DoubleBufferWrapper(doubleBuffer);
/*     */         return;
/*     */     } 
/* 186 */     this.bufferImpl = null;
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
/* 197 */   public Buffer getBuffer() { return this.originalBuffer; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   int getBufferType() { return this.bufferType; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   BufferWrapper getBufferImpl() { return this.bufferImpl; }
/*     */ 
/*     */   
/*     */   private static boolean checkNativeBufferAccess(Buffer paramBuffer) {
/* 211 */     if (paramBuffer == null) {
/* 212 */       return false;
/*     */     }
/*     */     
/* 215 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static  {
/* 222 */     ByteBuffer byteBuffer = ByteBuffer.allocateDirect(8);
/*     */     
/* 224 */     if (!checkNativeBufferAccess(byteBuffer))
/* 225 */       unsupportedOperation = true; 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\J3DBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */