/*     */ package com.sun.j3d.loaders.lw3d;
/*     */ 
/*     */ import com.sun.j3d.loaders.IncorrectFormatException;
/*     */ import com.sun.j3d.loaders.ParsingErrorException;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class LwoParser
/*     */   extends ParserObject
/*     */ {
/*     */   LWOBFileReader theReader;
/*     */   int currLength;
/*     */   float[] coordsArray;
/*     */   float[] normalCoordsArray;
/*     */   int[] facetIndicesArray;
/*     */   int[] facetSizesArray;
/*     */   int[] normalIndicesArray;
/*  85 */   int red = 255; int green = 255; int blue = 255;
/*  86 */   float diffuse = 0.0F; float specular = 0.0F; float transparency = 0.0F; float luminosity = 0.0F;
/*  87 */   int gloss = 128;
/*  88 */   Vector surfNameList = null;
/*  89 */   Vector surfaceList = new Vector(200);
/*  90 */   Vector shapeList = new Vector(200);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LwoParser(String paramString, int paramInt) throws FileNotFoundException {
/*  99 */     super(paramInt);
/* 100 */     debugOutputLn(1, "parser()");
/* 101 */     long l = System.currentTimeMillis();
/* 102 */     this.theReader = new LWOBFileReader(paramString);
/* 103 */     debugOutputLn(32, " file opened in " + (System.currentTimeMillis() - l));
/*     */     
/* 105 */     parseFile();
/*     */   }
/*     */ 
/*     */   
/*     */   LwoParser(URL paramURL, int paramInt) throws FileNotFoundException {
/* 110 */     super(paramInt);
/* 111 */     debugOutputLn(1, "parser()");
/*     */     try {
/* 113 */       long l = System.currentTimeMillis();
/* 114 */       this.theReader = new LWOBFileReader(paramURL);
/* 115 */       debugOutputLn(32, " file opened in " + (System.currentTimeMillis() - l));
/*     */     
/*     */     }
/* 118 */     catch (IOException iOException) {
/* 119 */       throw new FileNotFoundException(paramURL.toString());
/*     */     } 
/* 121 */     parseFile();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int skipDetailPolygons(int paramInt) throws ParsingErrorException {
/* 132 */     debugOutputLn(1, "skipDetailPolygons(), numPolys = " + paramInt);
/* 133 */     int i = 0;
/*     */ 
/*     */     
/*     */     try {
/* 137 */       for (byte b = 0; b < paramInt; b++) {
/* 138 */         debugOutputLn(2, "polyNum = " + b);
/* 139 */         int j = this.theReader.getShortInt();
/* 140 */         this.theReader.skip((j * 2 + 2));
/* 141 */         i += j * 2 + 4;
/*     */       }
/*     */     
/* 144 */     } catch (IOException iOException) {
/* 145 */       debugOutputLn(16, "Exception in reading detail polys: " + iOException);
/* 146 */       throw new ParsingErrorException(iOException.getMessage());
/*     */     } 
/* 148 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ShapeHolder getAppropriateShape(int paramInt1, int paramInt2) {
/* 156 */     Enumeration enumeration = this.shapeList.elements();
/* 157 */     while (enumeration.hasMoreElements()) {
/* 158 */       ShapeHolder shapeHolder = (ShapeHolder)enumeration.nextElement();
/* 159 */       if (shapeHolder.numSurf == paramInt1 && (
/* 160 */         shapeHolder.numVerts == paramInt2 || (shapeHolder.numVerts > 3 && paramInt2 > 3)))
/*     */       {
/*     */         
/* 163 */         return shapeHolder; } 
/*     */     } 
/* 165 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getPols(int paramInt) {
/* 174 */     debugOutputLn(1, "getPols(len), len = " + paramInt);
/*     */     
/* 176 */     int i = 0;
/* 177 */     int j = -1;
/* 178 */     int k = 0;
/*     */ 
/*     */     
/* 181 */     Vector vector = new Vector(paramInt / 6);
/*     */ 
/*     */ 
/*     */     
/* 185 */     int[] arrayOfInt = new int[paramInt / 2];
/* 186 */     ShapeHolder shapeHolder = new ShapeHolder(this.debugPrinter.getValidOutput());
/* 187 */     debugOutputLn(2, "new shape = " + shapeHolder);
/* 188 */     shapeHolder.coordsArray = this.coordsArray;
/* 189 */     shapeHolder.facetSizesList = vector;
/*     */     
/* 191 */     shapeHolder.facetIndicesArray = arrayOfInt;
/* 192 */     this.shapeList.addElement(shapeHolder);
/*     */ 
/*     */     
/* 195 */     boolean bool = true;
/* 196 */     while (i < paramInt) {
/* 197 */       int m = this.theReader.getShortInt();
/* 198 */       i += true;
/* 199 */       int[] arrayOfInt1 = new int[m]; int n;
/* 200 */       for (n = 0; n < m; n++) {
/* 201 */         arrayOfInt1[n] = this.theReader.getShortInt();
/* 202 */         i += true;
/*     */       } 
/*     */       
/* 205 */       n = this.theReader.getShortInt();
/* 206 */       i += true;
/* 207 */       long l1 = 0L, l2 = 0L;
/* 208 */       if (!bool && (n != k || (m != j && (j < 3 || m < 3)))) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 214 */         shapeHolder = getAppropriateShape(n, m);
/* 215 */         if (shapeHolder == null) {
/*     */           
/* 217 */           vector = new Vector(paramInt / 6);
/* 218 */           arrayOfInt = new int[paramInt / 2];
/* 219 */           shapeHolder = new ShapeHolder(this.debugPrinter.getValidOutput());
/* 220 */           shapeHolder.coordsArray = this.coordsArray;
/* 221 */           shapeHolder.facetSizesList = vector;
/*     */           
/* 223 */           shapeHolder.facetIndicesArray = arrayOfInt;
/* 224 */           shapeHolder.numSurf = n;
/* 225 */           shapeHolder.numVerts = m;
/* 226 */           this.shapeList.addElement(shapeHolder);
/*     */         } else {
/*     */           
/* 229 */           vector = shapeHolder.facetSizesList;
/* 230 */           arrayOfInt = shapeHolder.facetIndicesArray;
/*     */         } 
/*     */       } else {
/*     */         
/* 234 */         shapeHolder.numSurf = n;
/* 235 */         shapeHolder.numVerts = m;
/*     */       } 
/* 237 */       j = m;
/* 238 */       k = n;
/* 239 */       vector.addElement(new Integer(m));
/*     */       
/* 241 */       boolean bool1 = false;
/* 242 */       System.arraycopy(arrayOfInt1, 0, arrayOfInt, shapeHolder.currentNumIndices, m);
/*     */ 
/*     */       
/* 245 */       shapeHolder.currentNumIndices += m;
/* 246 */       if (n < 0) {
/* 247 */         int i1 = this.theReader.getShortInt();
/* 248 */         i += skipDetailPolygons(i1);
/* 249 */         shapeHolder.numSurf = (shapeHolder.numSurf ^ 0xFFFFFFFF) & 0xFFFF;
/* 250 */         if (shapeHolder.numSurf == 0)
/* 251 */           shapeHolder.numSurf = 1; 
/*     */       } 
/* 253 */       bool = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getSrfs(int paramInt) {
/* 263 */     String str = new String();
/* 264 */     this.surfNameList = new Vector(paramInt / 2);
/* 265 */     boolean bool1 = false;
/* 266 */     int i = this.theReader.getMarker() + paramInt;
/*     */     
/* 268 */     boolean bool2 = false;
/* 269 */     while (this.theReader.getMarker() < i) {
/* 270 */       debugOutputLn(2, "marker, stop = " + this.theReader.getMarker() + ", " + i);
/*     */       
/* 272 */       debugOutputLn(8, "About to call getString");
/* 273 */       str = this.theReader.getString();
/* 274 */       debugOutputLn(2, "Surfname = " + str);
/* 275 */       this.surfNameList.addElement(str);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getPnts(int paramInt) {
/* 283 */     int i = paramInt / 12;
/*     */ 
/*     */     
/* 286 */     this.coordsArray = new float[i * 3];
/* 287 */     this.theReader.getVerts(this.coordsArray, i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getSurf(int paramInt) {
/* 295 */     debugOutputLn(1, "getSurf()");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 300 */     LwoSurface lwoSurface = new LwoSurface(this.theReader, paramInt, this.debugPrinter.getValidOutput());
/*     */     
/* 302 */     this.surfaceList.addElement(lwoSurface);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int parseFile() throws FileNotFoundException, IncorrectFormatException {
/* 311 */     debugOutputLn(1, "parseFile()");
/* 312 */     int i = 0;
/* 313 */     int j = 0;
/* 314 */     int k = 100000;
/*     */     
/* 316 */     long l = System.currentTimeMillis();
/*     */     
/* 318 */     String str = this.theReader.getToken();
/*     */ 
/*     */     
/* 321 */     while (str != null && j < k) {
/* 322 */       long l1 = System.currentTimeMillis();
/*     */       
/* 324 */       i = this.theReader.getInt();
/*     */       
/* 326 */       j += true;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 331 */       if (str.equals("FORM")) {
/*     */         
/* 333 */         k = i + 4;
/* 334 */         i = 0;
/* 335 */         str = this.theReader.getToken();
/* 336 */         j += true;
/* 337 */         if (!str.equals("LWOB")) {
/* 338 */           throw new IncorrectFormatException("File not of FORM-length-LWOB format");
/*     */         }
/*     */       }
/* 341 */       else if (str.equals("PNTS")) {
/*     */         
/* 343 */         getPnts(i);
/* 344 */         debugOutputLn(32, "done with " + str + " in " + (System.currentTimeMillis() - l1));
/*     */       
/*     */       }
/* 347 */       else if (str.equals("POLS")) {
/*     */         
/* 349 */         getPols(i);
/* 350 */         debugOutputLn(32, "done with " + str + " in " + (System.currentTimeMillis() - l1));
/*     */       
/*     */       }
/* 353 */       else if (str.equals("SRFS")) {
/*     */         
/* 355 */         getSrfs(i);
/* 356 */         debugOutputLn(32, "done with " + str + " in " + (System.currentTimeMillis() - l1));
/*     */       
/*     */       }
/* 359 */       else if (str.equals("CRVS")) {
/*     */         
/* 361 */         this.theReader.skipLength(i);
/*     */ 
/*     */       
/*     */       }
/* 365 */       else if (str.equals("PCHS")) {
/*     */         
/* 367 */         this.theReader.skipLength(i);
/*     */ 
/*     */       
/*     */       }
/* 371 */       else if (str.equals("SURF")) {
/*     */         
/* 373 */         getSurf(i);
/*     */         
/* 375 */         debugOutputLn(32, "done with " + str + " in " + (System.currentTimeMillis() - l1));
/*     */       
/*     */       }
/* 378 */       else if (!str.equals("LWOB")) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 383 */         this.theReader.skipLength(i);
/*     */       } 
/*     */ 
/*     */       
/* 387 */       j += i;
/* 388 */       if (j < k) {
/*     */ 
/*     */         
/* 391 */         str = this.theReader.getToken();
/* 392 */         j += 4;
/*     */       } 
/*     */     } 
/*     */     
/* 396 */     debugOutputLn(32, "done with parseFile in " + (System.currentTimeMillis() - l));
/*     */     
/* 398 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void main(String[] paramArrayOfString) {
/*     */     String str;
/* 406 */     if (paramArrayOfString.length == 0) {
/* 407 */       str = "cube.obj";
/*     */     } else {
/* 409 */       str = paramArrayOfString[0];
/*     */     } 
/*     */     try {
/* 412 */       LwoParser lwoParser = new LwoParser(str, 0);
/*     */     }
/* 414 */     catch (FileNotFoundException fileNotFoundException) {
/* 415 */       System.err.println(fileNotFoundException.getMessage());
/* 416 */       fileNotFoundException.printStackTrace();
/* 417 */       System.exit(1);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3d\loaders\lw3d\LwoParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */