/*     */ package javax.vecmath;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Tuple4b
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = -8226727741811898211L;
/*     */   public byte x;
/*     */   public byte y;
/*     */   public byte z;
/*     */   public byte w;
/*     */   
/*     */   public Tuple4b(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4) {
/*  65 */     this.x = paramByte1;
/*  66 */     this.y = paramByte2;
/*  67 */     this.z = paramByte3;
/*  68 */     this.w = paramByte4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4b(byte[] paramArrayOfByte) {
/*  78 */     this.x = paramArrayOfByte[0];
/*  79 */     this.y = paramArrayOfByte[1];
/*  80 */     this.z = paramArrayOfByte[2];
/*  81 */     this.w = paramArrayOfByte[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4b(Tuple4b paramTuple4b) {
/*  91 */     this.x = paramTuple4b.x;
/*  92 */     this.y = paramTuple4b.y;
/*  93 */     this.z = paramTuple4b.z;
/*  94 */     this.w = paramTuple4b.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple4b() {
/* 103 */     this.x = 0;
/* 104 */     this.y = 0;
/* 105 */     this.z = 0;
/* 106 */     this.w = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public String toString() { return "(" + (this.x & 0xFF) + ", " + (this.y & 0xFF) + ", " + (this.z & 0xFF) + ", " + (this.w & 0xFF) + ")"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(byte[] paramArrayOfByte) {
/* 130 */     paramArrayOfByte[0] = this.x;
/* 131 */     paramArrayOfByte[1] = this.y;
/* 132 */     paramArrayOfByte[2] = this.z;
/* 133 */     paramArrayOfByte[3] = this.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(Tuple4b paramTuple4b) {
/* 144 */     paramTuple4b.x = this.x;
/* 145 */     paramTuple4b.y = this.y;
/* 146 */     paramTuple4b.z = this.z;
/* 147 */     paramTuple4b.w = this.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple4b paramTuple4b) {
/* 158 */     this.x = paramTuple4b.x;
/* 159 */     this.y = paramTuple4b.y;
/* 160 */     this.z = paramTuple4b.z;
/* 161 */     this.w = paramTuple4b.w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(byte[] paramArrayOfByte) {
/* 172 */     this.x = paramArrayOfByte[0];
/* 173 */     this.y = paramArrayOfByte[1];
/* 174 */     this.z = paramArrayOfByte[2];
/* 175 */     this.w = paramArrayOfByte[3];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Tuple4b paramTuple4b) {
/*     */     try {
/* 187 */       return (this.x == paramTuple4b.x && this.y == paramTuple4b.y && this.z == paramTuple4b.z && this.w == paramTuple4b.w);
/*     */     } catch (NullPointerException nullPointerException) {
/*     */       
/* 190 */       return false;
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
/*     */   public boolean equals(Object paramObject) {
/*     */     
/* 203 */     try { Tuple4b tuple4b = (Tuple4b)paramObject;
/* 204 */       return (this.x == tuple4b.x && this.y == tuple4b.y && this.z == tuple4b.z && this.w == tuple4b.w); }
/*     */     catch (NullPointerException nullPointerException)
/*     */     
/* 207 */     { return false; }
/* 208 */     catch (ClassCastException classCastException) { return false; }
/*     */   
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
/* 222 */   public int hashCode() { return (this.x & 0xFF) << 0 | (this.y & 0xFF) << 8 | (this.z & 0xFF) << 16 | (this.w & 0xFF) << 24; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/*     */     try {
/* 239 */       return super.clone();
/* 240 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 242 */       throw new InternalError();
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
/* 255 */   public final byte getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 267 */   public final void setX(byte paramByte) { this.x = paramByte; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   public final byte getY() { return this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 291 */   public final void setY(byte paramByte) { this.y = paramByte; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public final byte getZ() { return this.z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 314 */   public final void setZ(byte paramByte) { this.z = paramByte; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 326 */   public final byte getW() { return this.w; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 338 */   public final void setW(byte paramByte) { this.w = paramByte; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\Tuple4b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */