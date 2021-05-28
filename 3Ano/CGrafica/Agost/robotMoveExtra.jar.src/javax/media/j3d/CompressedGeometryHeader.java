/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompressedGeometryHeader
/*     */ {
/*     */   public static final int POINT_BUFFER = 0;
/*     */   public static final int LINE_BUFFER = 1;
/*     */   public static final int TRIANGLE_BUFFER = 2;
/*     */   public static final int NORMAL_IN_BUFFER = 1;
/*     */   public static final int COLOR_IN_BUFFER = 2;
/*     */   public static final int ALPHA_IN_BUFFER = 4;
/*     */   public int majorVersionNumber;
/*     */   public int minorVersionNumber;
/*     */   public int minorMinorVersionNumber;
/*     */   public int bufferType;
/*     */   public int bufferDataPresent;
/*     */   public int size;
/*     */   public int start;
/* 154 */   public Point3d lowerBound = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 167 */   public Point3d upperBound = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void copy(CompressedGeometryHeader paramCompressedGeometryHeader) {
/* 189 */     paramCompressedGeometryHeader.majorVersionNumber = this.majorVersionNumber;
/* 190 */     paramCompressedGeometryHeader.minorVersionNumber = this.minorVersionNumber;
/* 191 */     paramCompressedGeometryHeader.minorMinorVersionNumber = this.minorMinorVersionNumber;
/* 192 */     paramCompressedGeometryHeader.bufferType = this.bufferType;
/* 193 */     paramCompressedGeometryHeader.bufferDataPresent = this.bufferDataPresent;
/* 194 */     paramCompressedGeometryHeader.size = this.size;
/* 195 */     paramCompressedGeometryHeader.start = this.start;
/* 196 */     paramCompressedGeometryHeader.lowerBound = this.lowerBound;
/* 197 */     paramCompressedGeometryHeader.upperBound = this.upperBound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 207 */     String str1 = "UNKNOWN";
/* 208 */     switch (this.bufferType) { case 0:
/* 209 */         str1 = "POINT_BUFFER"; break;
/* 210 */       case 1: str1 = "LINE_BUFFER"; break;
/* 211 */       case 2: str1 = "TRIANGLE_BUFFER";
/*     */         break; }
/*     */     
/* 214 */     String str2 = "";
/* 215 */     if ((this.bufferDataPresent & true) != 0)
/* 216 */       str2 = str2 + "NORMALS "; 
/* 217 */     if ((this.bufferDataPresent & 0x2) != 0)
/* 218 */       str2 = str2 + "COLORS "; 
/* 219 */     if ((this.bufferDataPresent & 0x4) != 0) {
/* 220 */       str2 = str2 + "ALPHA ";
/*     */     }
/* 222 */     String str3 = "null";
/* 223 */     if (this.lowerBound != null) {
/* 224 */       str3 = this.lowerBound.toString();
/*     */     }
/* 226 */     String str4 = "null";
/* 227 */     if (this.upperBound != null) {
/* 228 */       str4 = this.upperBound.toString();
/*     */     }
/* 230 */     return "majorVersionNumber: " + this.majorVersionNumber + "  " + "minorVersionNumber: " + this.minorVersionNumber + "  " + "minorMinorVersionNumber: " + this.minorMinorVersionNumber + "\n" + "bufferType: " + str1 + "  " + "bufferDataPresent: " + str2 + "\n" + "size: " + this.size + "  " + "start: " + this.start + "\n" + "lower bound: " + str3 + "\n" + "upper bound: " + str4 + "  ";
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\CompressedGeometryHeader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */