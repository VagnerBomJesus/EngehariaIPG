/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.GraphicsDevice;
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
/*     */ public class RenderingError
/*     */ {
/*  25 */   private int errorCode = 0;
/*  26 */   private String errorMessage = null;
/*  27 */   private String detailMessage = null;
/*  28 */   private GraphicsDevice graphicsDevice = null;
/*  29 */   private Canvas3D canvas = null;
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
/*     */   public static final int UNEXPECTED_RENDERING_ERROR = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int GRAPHICS_CONFIG_ERROR = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int CONTEXT_CREATION_ERROR = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int OFF_SCREEN_BUFFER_ERROR = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderingError() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderingError(int paramInt, String paramString) {
/*  80 */     this.errorCode = paramInt;
/*  81 */     this.errorMessage = paramString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public void printVerbose() { printVerbose(System.err); }
/*     */ 
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
/* 102 */     paramPrintStream.println(this);
/* 103 */     if (this.graphicsDevice != null) {
/* 104 */       paramPrintStream.println("graphicsDevice = " + this.graphicsDevice);
/*     */     }
/* 106 */     if (this.canvas != null) {
/* 107 */       paramPrintStream.println("canvas = " + this.canvas);
/*     */     }
/*     */     
/* 110 */     if (this.detailMessage != null) {
/* 111 */       paramPrintStream.println();
/* 112 */       paramPrintStream.println("Detail Message");
/* 113 */       paramPrintStream.println("--------------");
/* 114 */       paramPrintStream.println(this.detailMessage);
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
/* 125 */   public void setErrorCode(int paramInt) { this.errorCode = paramInt; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public int getErrorCode() { return this.errorCode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public void setErrorMessage(String paramString) { this.errorMessage = paramString; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public String getErrorMessage() { return this.errorMessage; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public void setDetailMessage(String paramString) { this.detailMessage = paramString; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public String getDetailMessage() { return this.detailMessage; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public void setGraphicsDevice(GraphicsDevice paramGraphicsDevice) { this.graphicsDevice = paramGraphicsDevice; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   public GraphicsDevice getGraphicsDevice() { return this.graphicsDevice; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public void setCanvas3D(Canvas3D paramCanvas3D) { this.canvas = paramCanvas3D; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 211 */   public Canvas3D getCanvas3D() { return this.canvas; }
/*     */ 
/*     */ 
/*     */ 
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
/* 226 */     switch (this.errorCode) {
/*     */       case 0:
/* 228 */         str = "NO_ERROR";
/*     */         break;
/*     */       case 1:
/* 231 */         str = "UNEXPECTED_RENDERING_ERROR";
/*     */         break;
/*     */       case 2:
/* 234 */         str = "GRAPHICS_CONFIG_ERROR";
/*     */         break;
/*     */       case 3:
/* 237 */         str = "CONTEXT_CREATION_ERROR";
/*     */         break;
/*     */       case 4:
/* 240 */         str = "OFF_SCREEN_BUFFER_ERROR";
/*     */         break;
/*     */       
/*     */       default:
/* 244 */         str = "UNKNOWN ERROR CODE (" + this.errorCode + ")";
/*     */         break;
/*     */     } 
/* 247 */     if (this.errorMessage == null) {
/* 248 */       return str;
/*     */     }
/*     */     
/* 251 */     return str + ": " + this.errorMessage;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\RenderingError.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */