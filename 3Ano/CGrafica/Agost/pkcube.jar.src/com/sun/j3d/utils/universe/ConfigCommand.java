/*     */ package com.sun.j3d.utils.universe;
/*     */ 
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.FieldPosition;
/*     */ import java.util.Collection;
/*     */ import javax.vecmath.Matrix3d;
/*     */ import javax.vecmath.Matrix4d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ConfigCommand
/*     */ {
/*     */   static final int CREATE = 0;
/*     */   static final int ATTRIBUTE = 1;
/*     */   static final int PROPERTY = 2;
/*     */   static final int ALIAS = 3;
/*     */   static final int BUILTIN = 4;
/*     */   static final int INCLUDE = 5;
/*     */   static final int IGNORE = 6;
/*     */   int type;
/*     */   int argc;
/*     */   Object[] argv;
/*     */   String commandName;
/*     */   String baseName;
/*     */   String instanceName;
/*     */   String fileName;
/*     */   int lineNumber;
/*     */   
/*     */   ConfigCommand(Collection paramCollection, String paramString, int paramInt) {
/* 118 */     this.type = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     this.argc = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     this.argv = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 136 */     this.commandName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     this.baseName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     this.instanceName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 159 */     this.fileName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     this.lineNumber = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     this.fileName = paramString;
/* 175 */     this.lineNumber = paramInt;
/*     */     
/* 177 */     this.argc = paramCollection.size();
/* 178 */     this.argv = paramCollection.toArray(new Object[0]);
/*     */     
/* 180 */     if (this.argc <= 0 || !(this.argv[0] instanceof String)) {
/* 181 */       throw new IllegalArgumentException("malformed command");
/*     */     }
/* 183 */     this.commandName = (String)this.argv[0];
/*     */     
/* 185 */     if (this.commandName.startsWith("New")) {
/* 186 */       this.type = 0;
/* 187 */       this.baseName = this.commandName.substring(3);
/* 188 */       this.instanceName = checkName(this.argv[1]);
/*     */     }
/* 190 */     else if (this.commandName.endsWith("Property")) {
/* 191 */       this.baseName = this.commandName.substring(0, this.commandName.length() - 8);
/* 192 */       if (this.baseName.equals("Java")) {
/* 193 */         this.type = 6;
/* 194 */         processJavaProperty(this.argc, this.argv);
/*     */       } else {
/*     */         
/* 197 */         this.type = 2;
/* 198 */         this.instanceName = checkName(this.argv[1]);
/*     */       }
/*     */     
/* 201 */     } else if (this.commandName.endsWith("Attribute")) {
/*     */       
/* 203 */       this.type = 2;
/* 204 */       this.baseName = this.commandName.substring(0, this.commandName.length() - 9);
/* 205 */       this.instanceName = checkName(this.argv[1]);
/*     */     }
/* 207 */     else if (this.commandName.endsWith("Alias")) {
/* 208 */       this.type = 3;
/* 209 */       this.baseName = this.commandName.substring(0, this.commandName.length() - 5);
/* 210 */       this.instanceName = checkName(this.argv[1]);
/*     */     }
/* 212 */     else if (this.commandName.equals("Include")) {
/* 213 */       this.type = 5;
/*     */     } else {
/*     */       
/* 216 */       this.type = 4;
/*     */     } 
/*     */ 
/*     */     
/* 220 */     if (this.baseName != null && this.baseName.equals("Window")) {
/* 221 */       this.baseName = "Screen";
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void processJavaProperty(int paramInt, Object[] paramArrayOfObject) {
/* 239 */     for (byte b = 1; b < paramInt; b++) {
/*     */       
/* 241 */       if (paramArrayOfObject[b] instanceof Boolean) {
/* 242 */         paramArrayOfObject[b] = ((Boolean)paramArrayOfObject[b]).toString();
/*     */       }
/* 244 */       else if (!(paramArrayOfObject[b] instanceof String)) {
/* 245 */         throw new IllegalArgumentException("JavaProperty arguments must be Strings or Booleans");
/*     */       } 
/*     */     } 
/*     */     
/* 249 */     if (paramInt == 3) {
/*     */       
/* 251 */       setJavaProperty((String)paramArrayOfObject[1], (String)paramArrayOfObject[2]);
/*     */     } else {
/* 253 */       if (paramInt != 4)
/*     */       {
/* 255 */         throw new IllegalArgumentException("JavaProperty must have either 2 or 3 arguments");
/*     */       }
/*     */       
/* 258 */       if (!((String)paramArrayOfObject[2]).equals("Default"))
/*     */       {
/* 260 */         throw new IllegalArgumentException("JavaProperty 2nd argument must be \"Default\"");
/*     */       }
/*     */       
/* 263 */       if (evaluateJavaProperty((String)paramArrayOfObject[true]) == null)
/*     */       {
/* 265 */         setJavaProperty((String)paramArrayOfObject[1], (String)paramArrayOfObject[3]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String setJavaProperty(final String key, final String value) {
/* 277 */     return (String)AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run() {
/* 280 */             return System.setProperty(key, value);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String evaluateJavaProperty(final String key) {
/* 293 */     return (String)AccessController.doPrivileged(new PrivilegedAction()
/*     */         {
/*     */           public Object run() {
/* 296 */             return System.getProperty(key);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String checkName(Object paramObject) {
/* 309 */     if (!(paramObject instanceof String)) {
/* 310 */       throw new IllegalArgumentException("second argument to \"" + this.commandName + "\" must be a name");
/*     */     }
/*     */     
/* 313 */     return (String)paramObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String[] formatMatrixRows(Matrix3d paramMatrix3d) {
/* 324 */     double[] arrayOfDouble = new double[9];
/* 325 */     arrayOfDouble[0] = paramMatrix3d.m00; arrayOfDouble[1] = paramMatrix3d.m01; arrayOfDouble[2] = paramMatrix3d.m02;
/* 326 */     arrayOfDouble[3] = paramMatrix3d.m10; arrayOfDouble[4] = paramMatrix3d.m11; arrayOfDouble[5] = paramMatrix3d.m12;
/* 327 */     arrayOfDouble[6] = paramMatrix3d.m20; arrayOfDouble[7] = paramMatrix3d.m21; arrayOfDouble[8] = paramMatrix3d.m22;
/*     */     
/* 329 */     return formatMatrixRows(3, 3, arrayOfDouble);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String[] formatMatrixRows(Matrix4d paramMatrix4d) {
/* 340 */     double[] arrayOfDouble = new double[16];
/* 341 */     arrayOfDouble[0] = paramMatrix4d.m00; arrayOfDouble[1] = paramMatrix4d.m01; arrayOfDouble[2] = paramMatrix4d.m02; arrayOfDouble[3] = paramMatrix4d.m03;
/* 342 */     arrayOfDouble[4] = paramMatrix4d.m10; arrayOfDouble[5] = paramMatrix4d.m11; arrayOfDouble[6] = paramMatrix4d.m12; arrayOfDouble[7] = paramMatrix4d.m13;
/* 343 */     arrayOfDouble[8] = paramMatrix4d.m20; arrayOfDouble[9] = paramMatrix4d.m21; arrayOfDouble[10] = paramMatrix4d.m22; arrayOfDouble[11] = paramMatrix4d.m23;
/* 344 */     arrayOfDouble[12] = paramMatrix4d.m30; arrayOfDouble[13] = paramMatrix4d.m31; arrayOfDouble[14] = paramMatrix4d.m32; arrayOfDouble[15] = paramMatrix4d.m33;
/*     */     
/* 346 */     return formatMatrixRows(4, 4, arrayOfDouble);
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
/*     */   static String[] formatMatrixRows(int paramInt1, int paramInt2, double[] paramArrayOfDouble) {
/* 361 */     DecimalFormat decimalFormat = new DecimalFormat("0.000000");
/* 362 */     FieldPosition fieldPosition = new FieldPosition(0);
/* 363 */     StringBuffer stringBuffer1 = new StringBuffer();
/* 364 */     StringBuffer stringBuffer2 = new StringBuffer();
/* 365 */     String[] arrayOfString = new String[paramInt1];
/*     */     
/* 367 */     for (int i = 0; i < paramInt1; i++) {
/* 368 */       stringBuffer1.setLength(0);
/* 369 */       for (int j = 0; j < paramInt2; j++) {
/* 370 */         stringBuffer2.setLength(0);
/* 371 */         decimalFormat.format(paramArrayOfDouble[i * paramInt2 + j], stringBuffer2, fieldPosition);
/* 372 */         int k = 8 - fieldPosition.getEndIndex();
/* 373 */         for (byte b = 0; b < k; b++) {
/* 374 */           stringBuffer2.insert(0, " ");
/*     */         }
/* 376 */         stringBuffer1.append(stringBuffer2);
/*     */       } 
/* 378 */       arrayOfString[i] = stringBuffer1.toString();
/*     */     } 
/* 380 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 389 */     String[] arrayOfString = null;
/* 390 */     StringBuffer stringBuffer = new StringBuffer("(");
/*     */     
/* 392 */     for (byte b = 0; b < this.argc; b++) {
/* 393 */       if (this.argv[b] instanceof Matrix3d) {
/* 394 */         arrayOfString = formatMatrixRows((Matrix3d)this.argv[b]);
/* 395 */         stringBuffer.append("\n ((" + arrayOfString[0] + ")\n");
/* 396 */         stringBuffer.append("  (" + arrayOfString[1] + ")\n");
/* 397 */         stringBuffer.append("  (" + arrayOfString[2] + "))");
/* 398 */         if (b != this.argc - 1) stringBuffer.append("\n");
/*     */       
/* 400 */       } else if (this.argv[b] instanceof Matrix4d) {
/* 401 */         arrayOfString = formatMatrixRows((Matrix4d)this.argv[b]);
/* 402 */         stringBuffer.append("\n ((" + arrayOfString[0] + ")\n");
/* 403 */         stringBuffer.append("  (" + arrayOfString[1] + ")\n");
/* 404 */         stringBuffer.append("  (" + arrayOfString[2] + ")\n");
/* 405 */         stringBuffer.append("  (" + arrayOfString[3] + "))");
/* 406 */         if (b != this.argc - 1) stringBuffer.append("\n");
/*     */       
/*     */       } else {
/* 409 */         if (b) stringBuffer.append(" "); 
/* 410 */         stringBuffer.append(this.argv[b].toString());
/*     */       } 
/*     */     } 
/*     */     
/* 414 */     stringBuffer.append(")");
/* 415 */     return stringBuffer.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\util\\universe\ConfigCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */