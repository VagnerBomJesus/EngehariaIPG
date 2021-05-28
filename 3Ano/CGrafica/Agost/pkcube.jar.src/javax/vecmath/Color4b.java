/*     */ package javax.vecmath;
/*     */ 
/*     */ import java.awt.Color;
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
/*     */ public class Color4b
/*     */   extends Tuple4b
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = -105080578052502155L;
/*     */   
/*  46 */   public Color4b(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4) { super(paramByte1, paramByte2, paramByte3, paramByte4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public Color4b(byte[] paramArrayOfByte) { super(paramArrayOfByte); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public Color4b(Color4b paramColor4b) { super(paramColor4b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public Color4b(Tuple4b paramTuple4b) { super(paramTuple4b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public Color4b(Color paramColor) { super((byte)paramColor.getRed(), (byte)paramColor.getGreen(), (byte)paramColor.getBlue(), (byte)paramColor.getAlpha()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color4b() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(Color paramColor) {
/* 117 */     this.x = (byte)paramColor.getRed();
/* 118 */     this.y = (byte)paramColor.getGreen();
/* 119 */     this.z = (byte)paramColor.getBlue();
/* 120 */     this.w = (byte)paramColor.getAlpha();
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
/*     */   public final Color get() {
/* 133 */     byte b1 = this.x & 0xFF;
/* 134 */     byte b2 = this.y & 0xFF;
/* 135 */     byte b3 = this.z & 0xFF;
/* 136 */     byte b4 = this.w & 0xFF;
/*     */     
/* 138 */     return new Color(b1, b2, b3, b4);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\vecmath\Color4b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */