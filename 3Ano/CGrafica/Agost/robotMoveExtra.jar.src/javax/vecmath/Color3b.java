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
/*     */ public class Color3b
/*     */   extends Tuple3b
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 6632576088353444794L;
/*     */   
/*  45 */   public Color3b(byte paramByte1, byte paramByte2, byte paramByte3) { super(paramByte1, paramByte2, paramByte3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public Color3b(byte[] paramArrayOfByte) { super(paramArrayOfByte); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public Color3b(Color3b paramColor3b) { super(paramColor3b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public Color3b(Tuple3b paramTuple3b) { super(paramTuple3b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public Color3b(Color paramColor) { super((byte)paramColor.getRed(), (byte)paramColor.getGreen(), (byte)paramColor.getBlue()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color3b() {}
/*     */ 
/*     */ 
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
/* 113 */     this.x = (byte)paramColor.getRed();
/* 114 */     this.y = (byte)paramColor.getGreen();
/* 115 */     this.z = (byte)paramColor.getBlue();
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
/* 128 */     byte b1 = this.x & 0xFF;
/* 129 */     byte b2 = this.y & 0xFF;
/* 130 */     byte b3 = this.z & 0xFF;
/*     */     
/* 132 */     return new Color(b1, b2, b3);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\vecmath\Color3b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */