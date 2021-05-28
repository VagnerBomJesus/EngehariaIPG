/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShaderError
/*     */ {
/*  25 */   private int errorCode = 0;
/*  26 */   private String errorMessage = null;
/*  27 */   private String detailMessage = null;
/*  28 */   private Canvas3D canvas = null;
/*  29 */   private Shape3D shape = null;
/*  30 */   private Geometry geometry = null;
/*  31 */   private ShaderAppearance shaderApp = null;
/*  32 */   private ShaderProgram shaderProgram = null;
/*  33 */   private Shader shader = null;
/*  34 */   private ShaderAttributeSet shaderAttributeSet = null;
/*  35 */   private ShaderAttribute shaderAttribute = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int NO_ERROR = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int COMPILE_ERROR = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int LINK_ERROR = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int VERTEX_ATTRIBUTE_LOOKUP_ERROR = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SHADER_ATTRIBUTE_LOOKUP_ERROR = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SHADER_ATTRIBUTE_NAME_NOT_SET_ERROR = 5;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int SHADER_ATTRIBUTE_TYPE_ERROR = 6;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int UNSUPPORTED_LANGUAGE_ERROR = 7;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ShaderError() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ShaderError(int paramInt, String paramString) {
/* 102 */     this.errorCode = paramInt;
/* 103 */     this.errorMessage = paramString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public void printVerbose() { printVerbose(System.err); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printVerbose(PrintStream paramPrintStream) {
/* 124 */     paramPrintStream.println(this);
/* 125 */     if (this.canvas != null) {
/* 126 */       paramPrintStream.println("canvas = " + this.canvas);
/*     */     }
/* 128 */     if (this.shape != null) {
/* 129 */       paramPrintStream.println("shape = " + this.shape);
/*     */     }
/* 131 */     if (this.geometry != null) {
/* 132 */       paramPrintStream.println("geometry = " + this.geometry);
/*     */     }
/* 134 */     if (this.shaderApp != null) {
/* 135 */       paramPrintStream.println("shaderApp = " + this.shaderApp);
/*     */     }
/* 137 */     if (this.shaderProgram != null) {
/* 138 */       paramPrintStream.println("shaderProgram = " + this.shaderProgram);
/*     */     }
/* 140 */     if (this.shader != null) {
/* 141 */       paramPrintStream.println("shader = " + this.shader);
/*     */     }
/* 143 */     if (this.shaderAttributeSet != null) {
/* 144 */       paramPrintStream.println("shaderAttributeSet = " + this.shaderAttributeSet);
/*     */     }
/* 146 */     if (this.shaderAttribute != null) {
/* 147 */       paramPrintStream.println("shaderAttribute = " + this.shaderAttribute);
/*     */     }
/*     */     
/* 150 */     if (this.detailMessage != null) {
/* 151 */       paramPrintStream.println();
/* 152 */       paramPrintStream.println("Detail Message");
/* 153 */       paramPrintStream.println("--------------");
/* 154 */       paramPrintStream.println(this.detailMessage);
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
/* 165 */   public void setErrorCode(int paramInt) { this.errorCode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public int getErrorCode() { return this.errorCode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public void setErrorMessage(String paramString) { this.errorMessage = paramString; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public String getErrorMessage() { return this.errorMessage; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   public void setDetailMessage(String paramString) { this.detailMessage = paramString; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 216 */   public String getDetailMessage() { return this.detailMessage; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 225 */   public void setCanvas3D(Canvas3D paramCanvas3D) { this.canvas = paramCanvas3D; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   public Canvas3D getCanvas3D() { return this.canvas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 243 */   public void setShape3D(Shape3D paramShape3D) { this.shape = paramShape3D; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public Shape3D getShape3D() { return this.shape; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 261 */   public void setGeometry(Geometry paramGeometry) { this.geometry = paramGeometry; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 270 */   public Geometry getGeometry() { return this.geometry; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   public void setShaderAppearance(ShaderAppearance paramShaderAppearance) { this.shaderApp = paramShaderAppearance; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 288 */   public ShaderAppearance getShaderAppearance() { return this.shaderApp; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 297 */   public void setShaderProgram(ShaderProgram paramShaderProgram) { this.shaderProgram = paramShaderProgram; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 306 */   public ShaderProgram getShaderProgram() { return this.shaderProgram; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 315 */   public void setShader(Shader paramShader) { this.shader = paramShader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public Shader getShader() { return this.shader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 333 */   public void setShaderAttributeSet(ShaderAttributeSet paramShaderAttributeSet) { this.shaderAttributeSet = paramShaderAttributeSet; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 342 */   public ShaderAttributeSet getShaderAttributeSet() { return this.shaderAttributeSet; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 351 */   public void setShaderAttribute(ShaderAttribute paramShaderAttribute) { this.shaderAttribute = paramShaderAttribute; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 360 */   public ShaderAttribute getShaderAttribute() { return this.shaderAttribute; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     String str;
/* 375 */     switch (this.errorCode) {
/*     */       case 0:
/* 377 */         str = "NO_ERROR";
/*     */         break;
/*     */       case 1:
/* 380 */         str = "COMPILE_ERROR";
/*     */         break;
/*     */       case 2:
/* 383 */         str = "LINK_ERROR";
/*     */         break;
/*     */       case 3:
/* 386 */         str = "VERTEX_ATTRIBUTE_LOOKUP_ERROR";
/*     */         break;
/*     */       case 4:
/* 389 */         str = "SHADER_ATTRIBUTE_LOOKUP_ERROR";
/*     */         break;
/*     */       case 5:
/* 392 */         str = "SHADER_ATTRIBUTE_NAME_NOT_SET_ERROR";
/*     */         break;
/*     */       case 6:
/* 395 */         str = "SHADER_ATTRIBUTE_TYPE_ERROR";
/*     */         break;
/*     */       case 7:
/* 398 */         str = "UNSUPPORTED_LANGUAGE_ERROR";
/*     */         break;
/*     */       default:
/* 401 */         str = "UNKNOWN ERROR CODE (" + this.errorCode + ")";
/*     */         break;
/*     */     } 
/* 404 */     if (this.errorMessage == null) {
/* 405 */       return str;
/*     */     }
/*     */     
/* 408 */     return str + ": " + this.errorMessage;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\ShaderError.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */