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
/*     */ public abstract class Tuple3b
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   static final long serialVersionUID = -483782685323607044L;
/*     */   public byte x;
/*     */   public byte y;
/*     */   public byte z;
/*     */   
/*     */   public Tuple3b(byte paramByte1, byte paramByte2, byte paramByte3) {
/*  59 */     this.x = paramByte1;
/*  60 */     this.y = paramByte2;
/*  61 */     this.z = paramByte3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3b(byte[] paramArrayOfByte) {
/*  71 */     this.x = paramArrayOfByte[0];
/*  72 */     this.y = paramArrayOfByte[1];
/*  73 */     this.z = paramArrayOfByte[2];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3b(Tuple3b paramTuple3b) {
/*  83 */     this.x = paramTuple3b.x;
/*  84 */     this.y = paramTuple3b.y;
/*  85 */     this.z = paramTuple3b.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tuple3b() {
/*  94 */     this.x = 0;
/*  95 */     this.y = 0;
/*  96 */     this.z = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public String toString() { return "(" + (this.x & 0xFF) + ", " + (this.y & 0xFF) + ", " + (this.z & 0xFF) + ")"; }
/*     */ 
/*     */ 
/*     */ 
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
/* 120 */     paramArrayOfByte[0] = this.x;
/* 121 */     paramArrayOfByte[1] = this.y;
/* 122 */     paramArrayOfByte[2] = this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void get(Tuple3b paramTuple3b) {
/* 133 */     paramTuple3b.x = this.x;
/* 134 */     paramTuple3b.y = this.y;
/* 135 */     paramTuple3b.z = this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Tuple3b paramTuple3b) {
/* 146 */     this.x = paramTuple3b.x;
/* 147 */     this.y = paramTuple3b.y;
/* 148 */     this.z = paramTuple3b.z;
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
/* 159 */     this.x = paramArrayOfByte[0];
/* 160 */     this.y = paramArrayOfByte[1];
/* 161 */     this.z = paramArrayOfByte[2];
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
/*     */   public boolean equals(Tuple3b paramTuple3b) {
/*     */     try {
/* 174 */       return (this.x == paramTuple3b.x && this.y == paramTuple3b.y && this.z == paramTuple3b.z);
/*     */     } catch (NullPointerException nullPointerException) {
/* 176 */       return false;
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
/* 189 */     try { Tuple3b tuple3b = (Tuple3b)paramObject;
/* 190 */       return (this.x == tuple3b.x && this.y == tuple3b.y && this.z == tuple3b.z); }
/*     */     catch (NullPointerException nullPointerException)
/* 192 */     { return false; }
/* 193 */     catch (ClassCastException classCastException) { return false; }
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
/* 206 */   public int hashCode() { return (this.x & 0xFF) << 0 | (this.y & 0xFF) << 8 | (this.z & 0xFF) << 16; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 222 */       return super.clone();
/* 223 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 225 */       throw new InternalError();
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
/* 238 */   public final byte getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 250 */   public final void setX(byte paramByte) { this.x = paramByte; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 262 */   public final byte getY() { return this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 274 */   public final void setY(byte paramByte) { this.y = paramByte; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 285 */   public final byte getZ() { return this.z; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 297 */   public final void setZ(byte paramByte) { this.z = paramByte; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\Tuple3b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */