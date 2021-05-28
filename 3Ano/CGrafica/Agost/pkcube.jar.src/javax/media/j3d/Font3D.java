/*      */ package javax.media.j3d;
/*      */ 
/*      */ import com.sun.j3d.internal.FastVector;
/*      */ import com.sun.j3d.utils.geometry.GeometryInfo;
/*      */ import com.sun.j3d.utils.geometry.NormalGenerator;
/*      */ import java.awt.Font;
/*      */ import java.awt.Shape;
/*      */ import java.awt.font.FontRenderContext;
/*      */ import java.awt.font.GlyphVector;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.PathIterator;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Hashtable;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point3f;
/*      */ import javax.vecmath.Vector3f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Font3D
/*      */   extends NodeComponent
/*      */ {
/*      */   Font font;
/*      */   double tessellationTolerance;
/*      */   FontExtrusion fontExtrusion;
/*      */   FontRenderContext frc;
/*      */   static final float EPS = 1.0E-6F;
/*      */   Hashtable geomHash;
/*      */   
/*   89 */   public Font3D(Font paramFont, FontExtrusion paramFontExtrusion) { this(paramFont, 0.01D, paramFontExtrusion); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Font3D(Font paramFont, double paramDouble, FontExtrusion paramFontExtrusion) {
/*      */     this.geomHash = new Hashtable(20);
/*  115 */     this.font = paramFont;
/*  116 */     this.tessellationTolerance = paramDouble;
/*  117 */     this.fontExtrusion = paramFontExtrusion;
/*  118 */     this.frc = new FontRenderContext(new AffineTransform(), true, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  127 */   public Font getFont() { return this.font; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  139 */   public double getTessellationTolerance() { return this.tessellationTolerance; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  151 */   public void getFontExtrusion(FontExtrusion paramFontExtrusion) { paramFontExtrusion = this.fontExtrusion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getBoundingBox(int paramInt, BoundingBox paramBoundingBox) {
/*      */     Point3d point3d2;
/*  161 */     int[] arrayOfInt = { paramInt };
/*  162 */     GlyphVector glyphVector = this.font.createGlyphVector(this.frc, arrayOfInt);
/*  163 */     Rectangle2D.Float float = (Rectangle2D.Float)glyphVector.getGlyphMetrics(0).getBounds2D();
/*      */ 
/*      */     
/*  166 */     Point3d point3d1 = new Point3d(float.x, float.y, 0.0D);
/*      */     
/*  168 */     if (this.fontExtrusion != null) {
/*  169 */       point3d2 = new Point3d((float.x + float.width), (float.y + float.height), this.fontExtrusion.length);
/*      */     }
/*      */     else {
/*      */       
/*  173 */       point3d2 = new Point3d((float.x + float.width), (float.y + float.height), 0.0D);
/*      */     } 
/*      */ 
/*      */     
/*  177 */     paramBoundingBox.setLower(point3d1);
/*  178 */     paramBoundingBox.setUpper(point3d2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GeometryArray getGlyphGeometry(char paramChar) {
/*  192 */     char[] arrayOfChar = { paramChar };
/*  193 */     GlyphVector glyphVector = this.font.createGlyphVector(this.frc, arrayOfChar);
/*      */ 
/*      */     
/*  196 */     GeometryArrayRetained geometryArrayRetained = triangulateGlyphs(glyphVector, arrayOfChar[0]);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  201 */     assert geometryArrayRetained instanceof TriangleArrayRetained : "Font3D: GeometryArray is not an instance of TrangleArray";
/*      */     
/*  203 */     assert geometryArrayRetained.getVertexFormat() == 3 : "Font3D: Illegal GeometryArray format -- only coordinates and normals expected";
/*      */ 
/*      */     
/*  206 */     TriangleArray triangleArray = new TriangleArray(geometryArrayRetained.getVertexCount(), geometryArrayRetained.getVertexFormat());
/*      */ 
/*      */     
/*  209 */     float[] arrayOfFloat = new float[3];
/*      */     
/*  211 */     int i = triangleArray.getVertexCount();
/*  212 */     for (byte b = 0; b < i; b++) {
/*      */       
/*  214 */       geometryArrayRetained.getCoordinate(b, arrayOfFloat);
/*  215 */       triangleArray.setCoordinate(b, arrayOfFloat);
/*      */       
/*  217 */       geometryArrayRetained.getNormal(b, arrayOfFloat);
/*  218 */       triangleArray.setNormal(b, arrayOfFloat);
/*      */     } 
/*      */     
/*  221 */     return triangleArray;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   GeometryArrayRetained triangulateGlyphs(GlyphVector paramGlyphVector, char paramChar) {
/*  227 */     Character character = new Character(paramChar);
/*  228 */     GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)this.geomHash.get(character);
/*      */     
/*  230 */     if (geometryArrayRetained == null) {
/*      */       int m;
/*  232 */       Rectangle2D rectangle2D = paramGlyphVector.getVisualBounds();
/*  233 */       AffineTransform affineTransform = new AffineTransform();
/*  234 */       double d1 = rectangle2D.getX() + 0.5D * rectangle2D.getWidth();
/*  235 */       double d2 = rectangle2D.getY() + 0.5D * rectangle2D.getHeight();
/*  236 */       affineTransform.setToTranslation(-d1, -d2);
/*  237 */       affineTransform.scale(1.0D, -1.0D);
/*  238 */       affineTransform.translate(d1, -d2);
/*  239 */       Shape shape = paramGlyphVector.getOutline();
/*  240 */       PathIterator pathIterator = shape.getPathIterator(affineTransform, this.tessellationTolerance);
/*  241 */       int i = -1; byte b1 = 0; int j = 0, k = 0;
/*  242 */       UnorderList unorderList1 = new UnorderList(100, Point3f.class);
/*  243 */       float[] arrayOfFloat = new float[6];
/*  244 */       float f1 = 0.0F, f2 = 0.0F;
/*  245 */       float f3 = Float.MAX_VALUE, f4 = Float.MAX_VALUE;
/*  246 */       GeometryInfo geometryInfo = null;
/*  247 */       NormalGenerator normalGenerator = new NormalGenerator();
/*  248 */       FastVector fastVector = new FastVector(10);
/*  249 */       float f5 = -3.4028235E38F;
/*  250 */       byte b4 = 0, b5 = 0; int n = 0; byte b6 = 0;
/*      */       
/*  252 */       boolean bool1 = false;
/*      */ 
/*      */       
/*  255 */       while (!pathIterator.isDone()) {
/*  256 */         Point3f point3f = new Point3f();
/*  257 */         i = pathIterator.currentSegment(arrayOfFloat);
/*  258 */         if (i == 4) {
/*  259 */           if (k) {
/*  260 */             if (bool1) {
/*      */               
/*  262 */               b5 = b6;
/*  263 */               n = j - true;
/*      */             } 
/*  265 */             fastVector.addElement(k);
/*  266 */             k = 0;
/*  267 */             b1++;
/*      */           } 
/*  269 */         } else if (i == 0) {
/*  270 */           point3f.x = arrayOfFloat[0];
/*  271 */           point3f.y = arrayOfFloat[1];
/*  272 */           f1 = point3f.x;
/*  273 */           f2 = point3f.y;
/*      */           
/*  275 */           if (f1 == f3 && f2 == f4) {
/*  276 */             pathIterator.next();
/*      */             continue;
/*      */           } 
/*  279 */           bool1 = false;
/*  280 */           unorderList1.add(point3f);
/*  281 */           f3 = f1;
/*  282 */           f4 = f2;
/*  283 */           if (k > 0) {
/*  284 */             fastVector.addElement(k);
/*  285 */             k = 0;
/*  286 */             b1++;
/*      */           } 
/*  288 */           k++;
/*      */ 
/*      */ 
/*      */           
/*  292 */           b6 = ++j;
/*  293 */         } else if (i == 1) {
/*  294 */           point3f.x = arrayOfFloat[0];
/*  295 */           point3f.y = arrayOfFloat[1];
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  300 */           if (point3f.x == f1 && point3f.y == f2) {
/*  301 */             pathIterator.next();
/*      */             continue;
/*      */           } 
/*  304 */           if (point3f.y > f5) {
/*  305 */             f5 = point3f.y;
/*  306 */             b4 = j;
/*  307 */             bool1 = true;
/*      */           } 
/*  309 */           f1 = point3f.x;
/*  310 */           f2 = point3f.y;
/*  311 */           unorderList1.add(point3f);
/*  312 */           k++;
/*  313 */           j++;
/*      */         } 
/*  315 */         pathIterator.next();
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  320 */       if (j == 0) {
/*  321 */         return null;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  327 */       Point3f point3f1 = new Point3f(), point3f2 = new Point3f(), point3f3 = new Point3f();
/*  328 */       boolean bool2 = true;
/*  329 */       Point3f[] arrayOfPoint3f = (Point3f[])unorderList1.toArray(false);
/*      */       
/*  331 */       if (n - b5 > 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  336 */         if (b4 == b5) {
/*  337 */           point3f1.set(arrayOfPoint3f[n]);
/*      */         } else {
/*  339 */           point3f1.set(arrayOfPoint3f[b4 - 1]);
/*      */         } 
/*  341 */         point3f2.set(arrayOfPoint3f[b4]);
/*  342 */         if (b4 == n) {
/*  343 */           point3f3.set(arrayOfPoint3f[b5]);
/*      */         } else {
/*  345 */           point3f3.set(arrayOfPoint3f[b4 + 1]);
/*      */         } 
/*      */         
/*  348 */         if (point3f3.x != point3f2.x) {
/*  349 */           if (point3f1.x != point3f2.x) {
/*      */             
/*  351 */             if (Math.abs((point3f2.y - point3f1.y) / (point3f2.x - point3f1.x)) > Math.abs((point3f3.y - point3f2.y) / (point3f3.x - point3f2.x))) {
/*      */               
/*  353 */               bool2 = (point3f3.x > point3f2.x) ? 1 : 0;
/*      */             } else {
/*  355 */               bool2 = (point3f2.x > point3f1.x) ? 1 : 0;
/*      */             } 
/*      */           } else {
/*  358 */             bool2 = (point3f3.x > point3f2.x) ? 1 : 0;
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  365 */           bool2 = (point3f2.x > point3f1.x) ? 1 : 0;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  370 */       int i1 = 0;
/*  371 */       IslandsNode islandsNode = new IslandsNode(-1, -1);
/*  372 */       int[] arrayOfInt = fastVector.getData();
/*      */       
/*      */       byte b2;
/*  375 */       for (b2 = 0; b2 < fastVector.getSize(); b2++) {
/*  376 */         n = i1 + arrayOfInt[b2];
/*  377 */         islandsNode.insert(new IslandsNode(i1, n), arrayOfPoint3f);
/*  378 */         i1 = n;
/*      */       } 
/*      */       
/*  381 */       unorderList1 = null;
/*  382 */       fastVector = null;
/*  383 */       arrayOfInt = null;
/*      */ 
/*      */       
/*  386 */       UnorderList unorderList2 = new UnorderList(10, IslandsNode.class);
/*  387 */       islandsNode.collectOddLevelNode(unorderList2, 0);
/*  388 */       IslandsNode[] arrayOfIslandsNode = (IslandsNode[])unorderList2.toArray(false);
/*  389 */       int[][] arrayOfInt1 = new int[unorderList2.arraySize()][];
/*  390 */       Point3f[][] arrayOfPoint3f1 = new Point3f[arrayOfInt1.length][];
/*      */ 
/*      */ 
/*      */       
/*  394 */       for (b2 = 0; b2 < arrayOfInt1.length; b2++) {
/*  395 */         IslandsNode islandsNode1 = arrayOfIslandsNode[b2];
/*  396 */         int i5 = islandsNode1.numChild();
/*  397 */         arrayOfInt1[b2] = new int[i5 + 1];
/*  398 */         arrayOfInt1[b2][0] = islandsNode1.numVertices();
/*  399 */         int i6 = 0;
/*  400 */         i6 += arrayOfInt1[b2][0]; byte b;
/*  401 */         for (b = 0; b < i5; b++) {
/*  402 */           arrayOfInt1[b2][b + true] = islandsNode1.getChild(b).numVertices();
/*  403 */           i6 += arrayOfInt1[b2][b + 1];
/*      */         } 
/*  405 */         arrayOfPoint3f1[b2] = new Point3f[i6];
/*  406 */         i1 = 0; int i4;
/*  407 */         for (i4 = islandsNode1.startIdx; i4 < islandsNode1.endIdx; i4++) {
/*  408 */           arrayOfPoint3f1[b2][i1++] = arrayOfPoint3f[i4];
/*      */         }
/*      */         
/*  411 */         for (b = 0; b < i5; b++) {
/*  412 */           n = (islandsNode1.getChild(b)).endIdx;
/*  413 */           for (i4 = (islandsNode1.getChild(b)).startIdx; i4 < n; i4++) {
/*  414 */             arrayOfPoint3f1[b2][i1++] = arrayOfPoint3f[i4];
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  421 */       islandsNode = null;
/*  422 */       unorderList2 = null;
/*  423 */       arrayOfPoint3f = null;
/*      */       
/*  425 */       arrayOfInt = new int[1];
/*  426 */       int i2 = 0, i3 = 0;
/*  427 */       ArrayList arrayList = new ArrayList();
/*      */       
/*  429 */       Point3f point3f4 = new Point3f(), point3f5 = new Point3f(), point3f6 = new Point3f();
/*  430 */       Vector3f vector3f1 = new Vector3f(), vector3f2 = new Vector3f();
/*  431 */       j = 0;
/*      */ 
/*      */       
/*  434 */       for (b2 = 0; b2 < arrayOfInt1.length; b2++) {
/*  435 */         arrayOfInt[0] = arrayOfInt1[b2].length;
/*  436 */         j += arrayOfPoint3f1[b2].length;
/*  437 */         geometryInfo = new GeometryInfo(5);
/*  438 */         geometryInfo.setCoordinates(arrayOfPoint3f1[b2]);
/*  439 */         geometryInfo.setStripCounts(arrayOfInt1[b2]);
/*  440 */         geometryInfo.setContourCounts(arrayOfInt);
/*  441 */         normalGenerator.generateNormals(geometryInfo);
/*      */         
/*  443 */         GeometryArray geometryArray = geometryInfo.getGeometryArray(false, false, false);
/*  444 */         i3 += geometryArray.getVertexCount();
/*      */         
/*  446 */         arrayList.add(geometryArray);
/*      */       } 
/*      */ 
/*      */       
/*  450 */       if (this.fontExtrusion == null) {
/*  451 */         m = i3;
/*      */       }
/*  453 */       else if (this.fontExtrusion.shape == null) {
/*  454 */         m = i3 * 2 + j * 6;
/*      */       } else {
/*  456 */         m = i3 * 2 + j * 6 * (this.fontExtrusion.pnts.length - 1);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  464 */       TriangleArray triangleArray = new TriangleArray(m, 3);
/*      */ 
/*      */ 
/*      */       
/*  468 */       boolean[] arrayOfBoolean = new boolean[arrayOfInt1.length];
/*      */ 
/*      */       
/*  471 */       Vector3f vector3f3 = new Vector3f();
/*      */       
/*      */       byte b3;
/*  474 */       for (b3 = 0; b3 < arrayOfInt1.length; b3++) {
/*  475 */         GeometryArray geometryArray = (GeometryArray)arrayList.get(b3);
/*  476 */         i3 = geometryArray.getVertexCount();
/*      */         
/*  478 */         boolean bool = false;
/*      */ 
/*      */         
/*  481 */         for (b2 = 0; b2 < i3; b2 += 3, i2 += true) {
/*      */ 
/*      */           
/*  484 */           geometryArray.getCoordinate(b2, point3f1);
/*  485 */           geometryArray.getNormal(b2, vector3f1);
/*  486 */           geometryArray.getCoordinate(b2 + 1, point3f2);
/*  487 */           geometryArray.getCoordinate(b2 + 2, point3f3);
/*      */           
/*  489 */           if (!bool) {
/*      */ 
/*      */             
/*  492 */             if (!getNormal(point3f1, point3f2, point3f3, vector3f2)) {
/*      */               continue;
/*      */             }
/*      */             
/*  496 */             if (vector3f2.z >= 1.0E-6F) {
/*  497 */               arrayOfBoolean[b3] = false;
/*  498 */             } else if (vector3f2.z <= -1.0E-6F) {
/*  499 */               arrayOfBoolean[b3] = true;
/*      */             } else {
/*      */               continue;
/*      */             } 
/*  503 */             bool = true;
/*      */           } 
/*  505 */           if (arrayOfBoolean[b3]) {
/*      */ 
/*      */ 
/*      */             
/*  509 */             point3f4.x = point3f2.x; point3f4.y = point3f2.y; point3f4.z = point3f2.z;
/*  510 */             point3f2.x = point3f3.x; point3f2.y = point3f3.y; point3f2.z = point3f3.z;
/*  511 */             point3f3.x = point3f4.x; point3f3.y = point3f4.y; point3f3.z = point3f4.z;
/*  512 */             vector3f1.x = -vector3f1.x; vector3f1.y = -vector3f1.y; vector3f1.z = -vector3f1.z;
/*      */           } 
/*      */ 
/*      */           
/*  516 */           if (this.fontExtrusion != null) {
/*  517 */             vector3f2.x = -vector3f1.x; vector3f2.y = -vector3f1.y; vector3f2.z = -vector3f1.z;
/*      */             
/*  519 */             triangleArray.setCoordinate(i2, point3f1);
/*  520 */             triangleArray.setNormal(i2, vector3f2);
/*  521 */             triangleArray.setCoordinate(i2 + 1, point3f3);
/*  522 */             triangleArray.setNormal(i2 + 1, vector3f2);
/*  523 */             triangleArray.setCoordinate(i2 + 2, point3f2);
/*  524 */             triangleArray.setNormal(i2 + 2, vector3f2);
/*      */             
/*  526 */             point3f4.x = point3f1.x; point3f4.y = point3f1.y; point3f1.z += this.fontExtrusion.length;
/*  527 */             point3f5.x = point3f2.x; point3f5.y = point3f2.y; point3f2.z += this.fontExtrusion.length;
/*  528 */             point3f6.x = point3f3.x; point3f6.y = point3f3.y; point3f3.z += this.fontExtrusion.length;
/*      */             
/*  530 */             triangleArray.setCoordinate(i2 + i3, point3f4);
/*  531 */             triangleArray.setNormal(i2 + i3, vector3f1);
/*  532 */             triangleArray.setCoordinate(i2 + 1 + i3, point3f5);
/*  533 */             triangleArray.setNormal(i2 + 1 + i3, vector3f1);
/*  534 */             triangleArray.setCoordinate(i2 + 2 + i3, point3f6);
/*  535 */             triangleArray.setNormal(i2 + 2 + i3, vector3f1);
/*      */           } else {
/*  537 */             triangleArray.setCoordinate(i2, point3f1);
/*  538 */             triangleArray.setNormal(i2, vector3f1);
/*  539 */             triangleArray.setCoordinate(i2 + 1, point3f2);
/*  540 */             triangleArray.setNormal(i2 + 1, vector3f1);
/*  541 */             triangleArray.setCoordinate(i2 + 2, point3f3);
/*  542 */             triangleArray.setNormal(i2 + 2, vector3f1);
/*      */           } 
/*      */           continue;
/*      */         } 
/*  546 */         if (this.fontExtrusion != null) {
/*  547 */           i2 += i3;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  555 */       if (this.fontExtrusion != null) {
/*  556 */         if (this.fontExtrusion.shape == null) {
/*      */ 
/*      */ 
/*      */           
/*  560 */           float f = (float)Math.cos(0.767944870877505D);
/*      */ 
/*      */           
/*  563 */           Vector3f vector3f4 = null, vector3f5 = null;
/*      */           
/*  565 */           Vector3f vector3f6 = new Vector3f(), vector3f7 = new Vector3f();
/*      */ 
/*      */           
/*  568 */           Vector3f vector3f8 = new Vector3f();
/*  569 */           Vector3f vector3f9 = new Vector3f();
/*  570 */           Vector3f vector3f10 = new Vector3f();
/*  571 */           Vector3f vector3f11 = new Vector3f();
/*  572 */           Vector3f vector3f12 = new Vector3f();
/*  573 */           Vector3f vector3f13 = new Vector3f();
/*      */           
/*  575 */           for (b2 = 0; b2 < arrayOfInt1.length; b2++) {
/*  576 */             byte b; for (b3 = 0, b = 0, k = 0; b3 < arrayOfInt1[b2].length; b3++) {
/*  577 */               k += arrayOfInt1[b2][b3];
/*  578 */               point3f1.x = (arrayOfPoint3f1[b2][k - 1]).x;
/*  579 */               point3f1.y = (arrayOfPoint3f1[b2][k - 1]).y;
/*  580 */               point3f1.z = 0.0F;
/*  581 */               point3f4.x = point3f1.x; point3f4.y = point3f1.y; point3f1.z += this.fontExtrusion.length;
/*  582 */               point3f2.z = 0.0F;
/*  583 */               point3f2.z += this.fontExtrusion.length;
/*  584 */               for (byte b7 = 0; b7 < k; b7++) {
/*  585 */                 point3f2.x = (arrayOfPoint3f1[b2][b7]).x;
/*  586 */                 point3f2.y = (arrayOfPoint3f1[b2][b7]).y;
/*  587 */                 point3f5.x = point3f2.x;
/*  588 */                 point3f5.y = point3f2.y;
/*  589 */                 if (getNormal(point3f1, point3f4, point3f2, vector3f1)) {
/*      */                   
/*  591 */                   if (!bool2) {
/*  592 */                     vector3f1.negate();
/*      */                   }
/*  594 */                   vector3f3.set(vector3f1);
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */               } 
/*  599 */               for (; b < k; b++) {
/*  600 */                 point3f2.x = (arrayOfPoint3f1[b2][b]).x; point3f2.y = (arrayOfPoint3f1[b2][b]).y; point3f2.z = 0.0F;
/*  601 */                 point3f5.x = point3f2.x; point3f5.y = point3f2.y; point3f2.z += this.fontExtrusion.length;
/*      */                 
/*  603 */                 if (!getNormal(point3f1, point3f4, point3f2, vector3f1)) {
/*  604 */                   vector3f1.set(vector3f3);
/*      */                 } else {
/*  606 */                   if (!bool2) {
/*  607 */                     vector3f1.negate();
/*      */                   }
/*  609 */                   vector3f3.set(vector3f1);
/*      */                 } 
/*      */                 
/*  612 */                 if (!getNormal(point3f2, point3f4, point3f5, vector3f2)) {
/*  613 */                   vector3f2.set(vector3f3);
/*      */                 } else {
/*  615 */                   if (!bool2) {
/*  616 */                     vector3f2.negate();
/*      */                   }
/*  618 */                   vector3f3.set(vector3f2);
/*      */                 } 
/*      */ 
/*      */ 
/*      */                 
/*  623 */                 if (vector3f4 != null) {
/*  624 */                   float f6 = vector3f1.dot(vector3f5);
/*  625 */                   boolean bool = (f6 > f) ? 1 : 0;
/*  626 */                   if (bool) {
/*  627 */                     vector3f8.x = vector3f4.x + vector3f5.x + vector3f1.x;
/*  628 */                     vector3f8.y = vector3f4.y + vector3f5.y + vector3f1.y;
/*  629 */                     vector3f8.z = vector3f4.z + vector3f5.z + vector3f1.z;
/*  630 */                     normalize(vector3f8);
/*      */                     
/*  632 */                     vector3f11.x = vector3f5.x + vector3f1.x + vector3f2.x;
/*  633 */                     vector3f11.y = vector3f5.y + vector3f1.y + vector3f2.y;
/*  634 */                     vector3f11.z = vector3f5.z + vector3f1.z + vector3f2.z;
/*  635 */                     normalize(vector3f11);
/*      */                   } else {
/*      */                     
/*  638 */                     vector3f8.x = vector3f1.x; vector3f8.y = vector3f1.y; vector3f8.z = vector3f1.z;
/*  639 */                     vector3f1.x += vector3f2.x;
/*  640 */                     vector3f1.y += vector3f2.y;
/*  641 */                     vector3f1.z += vector3f2.z;
/*  642 */                     normalize(vector3f11);
/*      */                   } 
/*      */                 } else {
/*      */                   
/*  646 */                   vector3f4 = new Vector3f();
/*  647 */                   vector3f5 = new Vector3f();
/*  648 */                   vector3f8.x = vector3f1.x;
/*  649 */                   vector3f8.y = vector3f1.y;
/*  650 */                   vector3f8.z = vector3f1.z;
/*      */                   
/*  652 */                   vector3f1.x += vector3f2.x;
/*  653 */                   vector3f1.y += vector3f2.y;
/*  654 */                   vector3f1.z += vector3f2.z;
/*  655 */                   normalize(vector3f11);
/*      */                 } 
/*      */ 
/*      */ 
/*      */                 
/*  660 */                 if (b + true < k) {
/*  661 */                   point3f3.x = (arrayOfPoint3f1[b2][b + true]).x; point3f3.y = (arrayOfPoint3f1[b2][b + true]).y;
/*  662 */                   point3f3.z = 0.0F;
/*  663 */                   point3f6.x = point3f3.x; point3f6.y = point3f3.y; point3f3.z += this.fontExtrusion.length;
/*      */                   
/*  665 */                   if (!getNormal(point3f2, point3f5, point3f3, vector3f6)) {
/*  666 */                     vector3f6.set(vector3f3);
/*      */                   } else {
/*  668 */                     if (!bool2) {
/*  669 */                       vector3f6.negate();
/*      */                     }
/*  671 */                     vector3f3.set(vector3f6);
/*      */                   } 
/*      */                   
/*  674 */                   if (!getNormal(point3f3, point3f5, point3f6, vector3f7)) {
/*  675 */                     vector3f7.set(vector3f3);
/*      */                   } else {
/*  677 */                     if (!bool2) {
/*  678 */                       vector3f7.negate();
/*      */                     }
/*  680 */                     vector3f3.set(vector3f7);
/*      */                   } 
/*      */                   
/*  683 */                   float f6 = vector3f2.dot(vector3f6);
/*  684 */                   boolean bool = (f6 > f) ? 1 : 0;
/*      */                   
/*  686 */                   if (bool) {
/*  687 */                     vector3f9.x = vector3f1.x + vector3f2.x + vector3f6.x;
/*  688 */                     vector3f9.y = vector3f1.y + vector3f2.y + vector3f6.y;
/*  689 */                     vector3f9.z = vector3f1.z + vector3f2.z + vector3f6.z;
/*  690 */                     normalize(vector3f9);
/*      */                     
/*  692 */                     vector3f12.x = vector3f2.x + vector3f6.x + vector3f7.x;
/*  693 */                     vector3f12.y = vector3f2.y + vector3f6.y + vector3f7.y;
/*  694 */                     vector3f12.z = vector3f2.z + vector3f6.z + vector3f7.z;
/*  695 */                     normalize(vector3f12);
/*      */                   } else {
/*  697 */                     vector3f1.x += vector3f2.x;
/*  698 */                     vector3f1.y += vector3f2.y;
/*  699 */                     vector3f1.z += vector3f2.z;
/*  700 */                     normalize(vector3f9);
/*  701 */                     vector3f12.x = vector3f2.x; vector3f12.y = vector3f2.y; vector3f12.z = vector3f2.z;
/*      */                   } 
/*      */                 } else {
/*  704 */                   vector3f1.x += vector3f2.x;
/*  705 */                   vector3f1.y += vector3f2.y;
/*  706 */                   vector3f1.z += vector3f2.z;
/*  707 */                   normalize(vector3f9);
/*      */                   
/*  709 */                   vector3f12.x = vector3f2.x;
/*  710 */                   vector3f12.y = vector3f2.y;
/*  711 */                   vector3f12.z = vector3f2.z;
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  717 */                 if (bool2) {
/*  718 */                   triangleArray.setCoordinate(i2, point3f1);
/*  719 */                   triangleArray.setNormal(i2, vector3f8);
/*  720 */                   i2++;
/*      */                   
/*  722 */                   triangleArray.setCoordinate(i2, point3f4);
/*  723 */                   triangleArray.setNormal(i2, vector3f11);
/*  724 */                   i2++;
/*      */                   
/*  726 */                   triangleArray.setCoordinate(i2, point3f2);
/*  727 */                   triangleArray.setNormal(i2, vector3f9);
/*  728 */                   i2++;
/*      */                   
/*  730 */                   triangleArray.setCoordinate(i2, point3f2);
/*  731 */                   triangleArray.setNormal(i2, vector3f9);
/*  732 */                   i2++;
/*      */                   
/*  734 */                   triangleArray.setCoordinate(i2, point3f4);
/*  735 */                   triangleArray.setNormal(i2, vector3f11);
/*  736 */                   i2++;
/*      */                 } else {
/*  738 */                   triangleArray.setCoordinate(i2, point3f4);
/*  739 */                   triangleArray.setNormal(i2, vector3f11);
/*  740 */                   i2++;
/*      */                   
/*  742 */                   triangleArray.setCoordinate(i2, point3f1);
/*  743 */                   triangleArray.setNormal(i2, vector3f8);
/*  744 */                   i2++;
/*      */                   
/*  746 */                   triangleArray.setCoordinate(i2, point3f2);
/*  747 */                   triangleArray.setNormal(i2, vector3f9);
/*  748 */                   i2++;
/*      */                   
/*  750 */                   triangleArray.setCoordinate(i2, point3f4);
/*  751 */                   triangleArray.setNormal(i2, vector3f11);
/*  752 */                   i2++;
/*      */                   
/*  754 */                   triangleArray.setCoordinate(i2, point3f2);
/*  755 */                   triangleArray.setNormal(i2, vector3f9);
/*  756 */                   i2++;
/*      */                 } 
/*  758 */                 triangleArray.setCoordinate(i2, point3f5);
/*  759 */                 triangleArray.setNormal(i2, vector3f12);
/*  760 */                 i2++;
/*  761 */                 vector3f4.x = vector3f1.x; vector3f4.y = vector3f1.y; vector3f4.z = vector3f1.z;
/*  762 */                 vector3f5.x = vector3f2.x; vector3f5.y = vector3f2.y; vector3f5.z = vector3f2.z;
/*  763 */                 point3f1.x = point3f2.x; point3f1.y = point3f2.y; point3f1.z = point3f2.z;
/*  764 */                 point3f4.x = point3f5.x; point3f4.y = point3f5.y; point3f4.z = point3f5.z;
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/*  769 */               vector3f4 = null;
/*  770 */               vector3f5 = null;
/*      */             } 
/*      */           } 
/*      */         } else {
/*  774 */           int i4 = 0;
/*  775 */           Point3f point3f7 = new Point3f(), point3f8 = new Point3f(), point3f9 = new Point3f();
/*  776 */           Vector3f vector3f4 = new Vector3f(), vector3f5 = new Vector3f();
/*  777 */           Vector3f vector3f6 = new Vector3f(), vector3f7 = new Vector3f();
/*  778 */           Vector3f vector3f8 = new Vector3f(), vector3f9 = new Vector3f();
/*      */           
/*  780 */           boolean bool = false;
/*      */ 
/*      */           
/*  783 */           for (b2 = 0; b2 < arrayOfInt1.length; b2++) {
/*  784 */             byte b; for (b3 = 0, b = 0, i4 = k = 0; b3 < arrayOfInt1[b2].length; b3++) {
/*  785 */               k += arrayOfInt1[b2][b3];
/*      */               
/*  787 */               point3f1.x = (arrayOfPoint3f1[b2][k - 1]).x;
/*  788 */               point3f1.y = (arrayOfPoint3f1[b2][k - 1]).y;
/*  789 */               point3f1.z = 0.0F;
/*  790 */               point3f4.x = point3f1.x; point3f4.y = point3f1.y; point3f1.z += this.fontExtrusion.length;
/*  791 */               point3f3.z = 0.0F; int i5;
/*  792 */               for (i5 = k - 2; i5 >= 0; i5--) {
/*  793 */                 point3f3.x = (arrayOfPoint3f1[b2][i5]).x;
/*  794 */                 point3f3.y = (arrayOfPoint3f1[b2][i5]).y;
/*      */                 
/*  796 */                 if (getNormal(point3f3, point3f4, point3f1, vector3f5)) {
/*  797 */                   if (!bool2) {
/*  798 */                     vector3f5.negate();
/*      */                   }
/*  800 */                   vector3f3.set(vector3f5);
/*      */                   break;
/*      */                 } 
/*      */               } 
/*  804 */               for (; b < k; b++) {
/*  805 */                 point3f2.x = (arrayOfPoint3f1[b2][b]).x; point3f2.y = (arrayOfPoint3f1[b2][b]).y; point3f2.z = 0.0F;
/*  806 */                 point3f5.x = point3f2.x; point3f5.y = point3f2.y; point3f2.z += this.fontExtrusion.length;
/*  807 */                 getNormal(point3f1, point3f4, point3f2, vector3f6);
/*      */                 
/*  809 */                 point3f3.x = (arrayOfPoint3f1[b2][(b + true == k) ? i4 : (b + true)]).x;
/*  810 */                 point3f3.y = (arrayOfPoint3f1[b2][(b + true == k) ? i4 : (b + true)]).y;
/*  811 */                 point3f3.z = 0.0F;
/*  812 */                 if (!getNormal(point3f3, point3f2, point3f5, vector3f7)) {
/*  813 */                   vector3f7.set(vector3f3);
/*      */                 } else {
/*  815 */                   if (!bool2) {
/*  816 */                     vector3f7.negate();
/*      */                   }
/*  818 */                   vector3f3.set(vector3f7);
/*      */                 } 
/*      */ 
/*      */ 
/*      */                 
/*  823 */                 vector3f5.x += vector3f6.x;
/*  824 */                 vector3f5.y += vector3f6.y;
/*  825 */                 vector3f5.z += vector3f6.z;
/*  826 */                 normalize(vector3f8);
/*      */                 
/*  828 */                 vector3f7.x += vector3f6.x;
/*  829 */                 vector3f7.y += vector3f6.y;
/*  830 */                 vector3f7.z += vector3f6.z;
/*  831 */                 normalize(vector3f9);
/*      */                 
/*  833 */                 point3f9.x = point3f1.x; point3f9.y = point3f1.y; point3f9.z = point3f1.z;
/*  834 */                 point3f7.x = point3f2.x; point3f7.y = point3f2.y; point3f7.z = point3f2.z;
/*  835 */                 point3f8.x = point3f5.x; point3f8.y = point3f5.y; point3f8.z = point3f5.z;
/*  836 */                 for (i5 = 1; i5 < this.fontExtrusion.pnts.length; i5++) {
/*  837 */                   point3f5.z = (this.fontExtrusion.pnts[i5]).x;
/*  838 */                   point3f9.x += vector3f8.x * (this.fontExtrusion.pnts[i5]).y;
/*  839 */                   point3f9.y += vector3f8.y * (this.fontExtrusion.pnts[i5]).y;
/*  840 */                   point3f7.x += vector3f9.x * (this.fontExtrusion.pnts[i5]).y;
/*  841 */                   point3f7.y += vector3f9.y * (this.fontExtrusion.pnts[i5]).y;
/*      */                   
/*  843 */                   if (!getNormal(point3f1, point3f4, point3f2, vector3f1)) {
/*  844 */                     vector3f1.set(vector3f3);
/*      */                   } else {
/*  846 */                     if (!bool2) {
/*  847 */                       vector3f1.negate();
/*      */                     }
/*  849 */                     vector3f3.set(vector3f1);
/*      */                   } 
/*      */                   
/*  852 */                   if (bool2) {
/*  853 */                     triangleArray.setCoordinate(i2, point3f1);
/*  854 */                     triangleArray.setNormal(i2, vector3f1);
/*  855 */                     i2++;
/*      */                     
/*  857 */                     triangleArray.setCoordinate(i2, point3f4);
/*  858 */                     triangleArray.setNormal(i2, vector3f1);
/*  859 */                     i2++;
/*      */                   } else {
/*  861 */                     triangleArray.setCoordinate(i2, point3f4);
/*  862 */                     triangleArray.setNormal(i2, vector3f1);
/*  863 */                     i2++;
/*      */                     
/*  865 */                     triangleArray.setCoordinate(i2, point3f1);
/*  866 */                     triangleArray.setNormal(i2, vector3f1);
/*  867 */                     i2++;
/*      */                   } 
/*  869 */                   triangleArray.setCoordinate(i2, point3f2);
/*  870 */                   triangleArray.setNormal(i2, vector3f1);
/*  871 */                   i2++;
/*      */                   
/*  873 */                   if (!getNormal(point3f2, point3f4, point3f5, vector3f1)) {
/*  874 */                     vector3f1.set(vector3f3);
/*      */                   } else {
/*  876 */                     if (!bool2) {
/*  877 */                       vector3f1.negate();
/*      */                     }
/*  879 */                     vector3f3.set(vector3f1);
/*      */                   } 
/*      */                   
/*  882 */                   if (bool2) {
/*  883 */                     triangleArray.setCoordinate(i2, point3f2);
/*  884 */                     triangleArray.setNormal(i2, vector3f1);
/*  885 */                     i2++;
/*      */                     
/*  887 */                     triangleArray.setCoordinate(i2, point3f4);
/*  888 */                     triangleArray.setNormal(i2, vector3f1);
/*  889 */                     i2++;
/*      */                   } else {
/*  891 */                     triangleArray.setCoordinate(i2, point3f4);
/*  892 */                     triangleArray.setNormal(i2, vector3f1);
/*  893 */                     i2++;
/*      */                     
/*  895 */                     triangleArray.setCoordinate(i2, point3f2);
/*  896 */                     triangleArray.setNormal(i2, vector3f1);
/*  897 */                     i2++;
/*      */                   } 
/*  899 */                   triangleArray.setCoordinate(i2, point3f5);
/*  900 */                   triangleArray.setNormal(i2, vector3f1);
/*  901 */                   i2++;
/*      */                   
/*  903 */                   point3f1.x = point3f4.x; point3f1.y = point3f4.y; point3f1.z = point3f4.z;
/*  904 */                   point3f2.x = point3f5.x; point3f2.y = point3f5.y; point3f2.z = point3f5.z;
/*      */                 } 
/*  906 */                 point3f1.x = point3f7.x; point3f1.y = point3f7.y; point3f1.z = point3f7.z;
/*  907 */                 point3f4.x = point3f8.x; point3f4.y = point3f8.y; point3f4.z = point3f8.z;
/*  908 */                 vector3f5.x = vector3f6.x; vector3f5.y = vector3f6.y; vector3f5.z = vector3f6.z;
/*      */               } 
/*  910 */               i4 = k;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*  915 */       geometryArrayRetained = (GeometryArrayRetained)triangleArray.retained;
/*  916 */       this.geomHash.put(character, geometryArrayRetained);
/*      */     } 
/*      */     
/*  919 */     return geometryArrayRetained;
/*      */   }
/*      */ 
/*      */   
/*      */   static boolean getNormal(Point3f paramPoint3f1, Point3f paramPoint3f2, Point3f paramPoint3f3, Vector3f paramVector3f) {
/*  924 */     Vector3f vector3f1 = new Vector3f();
/*  925 */     Vector3f vector3f2 = new Vector3f();
/*      */ 
/*      */     
/*  928 */     vector3f1.sub(paramPoint3f2, paramPoint3f1);
/*  929 */     vector3f2.sub(paramPoint3f2, paramPoint3f3);
/*  930 */     paramVector3f.cross(vector3f1, vector3f2);
/*  931 */     paramVector3f.negate();
/*      */     
/*  933 */     float f = paramVector3f.length();
/*      */     
/*  935 */     if (f > 0.0F) {
/*  936 */       f = 1.0F / f;
/*  937 */       paramVector3f.x *= f;
/*  938 */       paramVector3f.y *= f;
/*  939 */       paramVector3f.z *= f;
/*  940 */       return true;
/*      */     } 
/*  942 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static int check2Contours(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Point3f[] paramArrayOfPoint3f) {
/*  962 */     boolean bool1 = pointInPolygon2D((paramArrayOfPoint3f[paramInt1]).x, (paramArrayOfPoint3f[paramInt1]).y, paramInt3, paramInt4, paramArrayOfPoint3f);
/*      */     
/*      */     int i;
/*  965 */     for (i = paramInt1 + 1; i < paramInt2; i++) {
/*  966 */       if (pointInPolygon2D((paramArrayOfPoint3f[i]).x, (paramArrayOfPoint3f[i]).y, paramInt3, paramInt4, paramArrayOfPoint3f) != bool1)
/*      */       {
/*  968 */         return 1;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  978 */     boolean bool2 = pointInPolygon2D((paramArrayOfPoint3f[paramInt3]).x, (paramArrayOfPoint3f[paramInt3]).y, paramInt1, paramInt2, paramArrayOfPoint3f);
/*      */ 
/*      */     
/*  981 */     for (i = paramInt3 + 1; i < paramInt4; i++) {
/*  982 */       if (pointInPolygon2D((paramArrayOfPoint3f[i]).x, (paramArrayOfPoint3f[i]).y, paramInt1, paramInt2, paramArrayOfPoint3f) != bool2)
/*      */       {
/*  984 */         return 1;
/*      */       }
/*      */     } 
/*      */     
/*  988 */     if (!bool1) {
/*  989 */       if (!bool2) {
/*  990 */         return 0;
/*      */       }
/*      */       
/*  993 */       return 3;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1000 */     return 2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static boolean pointInPolygon2D(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2, Point3f[] paramArrayOfPoint3f) {
/* 1013 */     byte b = 0;
/*      */     
/*      */     int i;
/* 1016 */     for (i = paramInt1; i < paramInt2 - 1; i++) {
/* 1017 */       if (((paramArrayOfPoint3f[i]).y < paramFloat2 || (paramArrayOfPoint3f[i + 1]).y < paramFloat2) && ((paramArrayOfPoint3f[i]).y >= paramFloat2 || (paramArrayOfPoint3f[i + 1]).y >= paramFloat2)) {
/*      */ 
/*      */ 
/*      */         
/* 1021 */         float f = (paramArrayOfPoint3f[i]).x + ((paramArrayOfPoint3f[i]).x - (paramArrayOfPoint3f[i + 1]).x) * (paramFloat2 - (paramArrayOfPoint3f[i]).y) / ((paramArrayOfPoint3f[i]).y - (paramArrayOfPoint3f[i + 1]).y);
/*      */ 
/*      */         
/* 1024 */         if (paramFloat1 < f) b++;
/*      */       
/*      */       } 
/*      */     } 
/*      */     
/* 1029 */     if (((paramArrayOfPoint3f[i]).y < paramFloat2 || (paramArrayOfPoint3f[paramInt1]).y < paramFloat2) && ((paramArrayOfPoint3f[i]).y >= paramFloat2 || (paramArrayOfPoint3f[paramInt1]).y >= paramFloat2)) {
/*      */       
/* 1031 */       float f = (paramArrayOfPoint3f[i]).x + ((paramArrayOfPoint3f[i]).x - (paramArrayOfPoint3f[paramInt1]).x) * (paramFloat2 - (paramArrayOfPoint3f[i]).y) / ((paramArrayOfPoint3f[i]).y - (paramArrayOfPoint3f[paramInt1]).y);
/*      */ 
/*      */       
/* 1034 */       if (paramFloat1 < f) b++;
/*      */     
/*      */     } 
/* 1037 */     return (b % 2 != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   static final boolean normalize(Vector3f paramVector3f) {
/* 1042 */     float f = paramVector3f.length();
/*      */     
/* 1044 */     if (f > 0.0F) {
/* 1045 */       f = 1.0F / f;
/* 1046 */       paramVector3f.x *= f;
/* 1047 */       paramVector3f.y *= f;
/* 1048 */       paramVector3f.z *= f;
/* 1049 */       return true;
/*      */     } 
/* 1051 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private static class IslandsNode
/*      */   {
/*      */     private ArrayList islandsList;
/*      */     
/*      */     int startIdx;
/*      */     
/*      */     int endIdx;
/*      */     
/*      */     IslandsNode(int param1Int1, int param1Int2) {
/* 1064 */       this.islandsList = null;
/*      */ 
/*      */ 
/*      */       
/* 1068 */       this.startIdx = param1Int1;
/* 1069 */       this.endIdx = param1Int2;
/* 1070 */       this.islandsList = null;
/*      */     }
/*      */ 
/*      */     
/*      */     void addChild(IslandsNode param1IslandsNode) {
/* 1075 */       if (this.islandsList == null) {
/* 1076 */         this.islandsList = new ArrayList(5);
/*      */       }
/* 1078 */       this.islandsList.add(param1IslandsNode);
/*      */     }
/*      */ 
/*      */     
/* 1082 */     void removeChild(IslandsNode param1IslandsNode) { this.islandsList.remove(this.islandsList.indexOf(param1IslandsNode)); }
/*      */ 
/*      */ 
/*      */     
/* 1086 */     IslandsNode getChild(int param1Int) { return (IslandsNode)this.islandsList.get(param1Int); }
/*      */ 
/*      */ 
/*      */     
/* 1090 */     int numChild() { return (this.islandsList == null) ? 0 : this.islandsList.size(); }
/*      */ 
/*      */ 
/*      */     
/* 1094 */     int numVertices() { return this.endIdx - this.startIdx; }
/*      */ 
/*      */     
/*      */     void insert(IslandsNode param1IslandsNode, Point3f[] param1ArrayOfPoint3f) {
/* 1098 */       boolean bool = false;
/*      */       
/* 1100 */       if (this.islandsList != null)
/*      */       {
/*      */ 
/*      */         
/* 1104 */         for (int i = numChild() - 1; i >= 0; i--) {
/* 1105 */           IslandsNode islandsNode = getChild(i);
/* 1106 */           int j = Font3D.check2Contours(param1IslandsNode.startIdx, param1IslandsNode.endIdx, islandsNode.startIdx, islandsNode.endIdx, param1ArrayOfPoint3f);
/*      */ 
/*      */           
/* 1109 */           switch (j) {
/*      */             case 2:
/* 1111 */               islandsNode.insert(param1IslandsNode, param1ArrayOfPoint3f);
/*      */               return;
/*      */ 
/*      */             
/*      */             case 3:
/* 1116 */               param1IslandsNode.addChild(islandsNode);
/* 1117 */               bool = true;
/*      */               break;
/*      */           } 
/*      */ 
/*      */ 
/*      */         
/*      */         } 
/*      */       }
/* 1125 */       if (bool)
/*      */       {
/* 1127 */         for (int i = param1IslandsNode.numChild() - 1; i >= 0; i--) {
/* 1128 */           removeChild(param1IslandsNode.getChild(i));
/*      */         }
/*      */       }
/*      */       
/* 1132 */       addChild(param1IslandsNode);
/*      */     }
/*      */ 
/*      */     
/*      */     void collectOddLevelNode(UnorderList param1UnorderList, int param1Int) {
/* 1137 */       if (param1Int % 2 == 1) {
/* 1138 */         param1UnorderList.add(this);
/*      */       }
/* 1140 */       if (this.islandsList != null) {
/* 1141 */         param1Int++;
/* 1142 */         for (int i = numChild() - 1; i >= 0; i--)
/* 1143 */           getChild(i).collectOddLevelNode(param1UnorderList, param1Int); 
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\Font3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */