/*      */ package com.sun.j3d.utils.geometry;
/*      */ 
/*      */ import com.sun.j3d.internal.J3dUtilsI18N;
/*      */ import java.util.Random;
/*      */ import javax.vecmath.Point2f;
/*      */ import javax.vecmath.Point3f;
/*      */ import javax.vecmath.Vector3f;
/*      */ 
/*      */ 
/*      */ public class Triangulator
/*      */ {
/*      */   GeometryInfo gInfo;
/*      */   int[] faces;
/*      */   int[] loops;
/*      */   int[] chains;
/*      */   Point2f[] points;
/*      */   Triangle[] triangles;
/*      */   ListNode[] list;
/*      */   Random randomGen;
/*      */   int numPoints;
/*      */   int maxNumPoints;
/*      */   int numList;
/*      */   int maxNumList;
/*      */   int numLoops;
/*      */   int maxNumLoops;
/*      */   int numTriangles;
/*      */   int maxNumTriangles;
/*      */   int numFaces;
/*      */   int numTexSets;
/*      */   int firstNode;
/*      */   int numChains;
/*      */   int maxNumChains;
/*      */   Point2f[] pUnsorted;
/*      */   int maxNumPUnsorted;
/*      */   boolean noHashingEdges;
/*      */   boolean noHashingPnts;
/*      */   int loopMin;
/*      */   int loopMax;
/*      */   PntNode[] vtxList;
/*      */   int numVtxList;
/*      */   int numReflex;
/*      */   int reflexVertices;
/*      */   Distance[] distances;
/*      */   int maxNumDist;
/*      */   Left[] leftMost;
/*      */   int maxNumLeftMost;
/*      */   HeapNode[] heap;
/*      */   int numHeap;
/*      */   int maxNumHeap;
/*      */   int numZero;
/*      */   int maxNumPolyArea;
/*      */   double[] polyArea;
/*      */   int[] stripCounts;
/*      */   int[] vertexIndices;
/*      */   Point3f[] vertices;
/*      */   Object[] colors;
/*      */   Vector3f[] normals;
/*      */   boolean ccwLoop;
/*      */   boolean earsRandom;
/*      */   boolean earsSorted;
/*      */   int identCntr;
/*      */   double epsilon;
/*      */   static final double ZERO = 1.0E-8D;
/*      */   static final int EARS_SEQUENCE = 0;
/*      */   static final int EARS_RANDOM = 1;
/*      */   static final int EARS_SORTED = 2;
/*      */   static final int INC_LIST_BK = 100;
/*      */   static final int INC_LOOP_BK = 20;
/*      */   static final int INC_TRI_BK = 50;
/*      */   static final int INC_POINT_BK = 100;
/*      */   static final int INC_DIST_BK = 50;
/*      */   private static final int DEBUG = 0;
/*      */   
/*      */   public Triangulator() {
/*   75 */     this.gInfo = null;
/*      */     
/*   77 */     this.faces = null;
/*   78 */     this.loops = null;
/*   79 */     this.chains = null;
/*   80 */     this.points = null;
/*   81 */     this.triangles = null;
/*   82 */     this.list = null;
/*      */     
/*   84 */     this.randomGen = null;
/*      */     
/*   86 */     this.numPoints = 0;
/*   87 */     this.maxNumPoints = 0;
/*   88 */     this.numList = 0;
/*   89 */     this.maxNumList = 0;
/*   90 */     this.numLoops = 0;
/*   91 */     this.maxNumLoops = 0;
/*   92 */     this.numTriangles = 0;
/*   93 */     this.maxNumTriangles = 0;
/*      */     
/*   95 */     this.numFaces = 0;
/*   96 */     this.numTexSets = 0;
/*      */ 
/*      */     
/*   99 */     this.firstNode = 0;
/*      */     
/*  101 */     this.numChains = 0;
/*  102 */     this.maxNumChains = 0;
/*      */ 
/*      */     
/*  105 */     this.pUnsorted = null;
/*  106 */     this.maxNumPUnsorted = 0;
/*      */ 
/*      */     
/*  109 */     this.noHashingEdges = false;
/*  110 */     this.noHashingPnts = false;
/*      */     
/*  112 */     this.vtxList = null;
/*  113 */     this.numVtxList = 0;
/*  114 */     this.numReflex = 0;
/*      */ 
/*      */ 
/*      */     
/*  118 */     this.distances = null;
/*  119 */     this.maxNumDist = 0;
/*  120 */     this.leftMost = null;
/*  121 */     this.maxNumLeftMost = 0;
/*      */ 
/*      */     
/*  124 */     this.heap = null;
/*  125 */     this.numHeap = 0;
/*  126 */     this.maxNumHeap = 0;
/*  127 */     this.numZero = 0;
/*      */ 
/*      */     
/*  130 */     this.maxNumPolyArea = 0;
/*  131 */     this.polyArea = null;
/*      */     
/*  133 */     this.stripCounts = null;
/*  134 */     this.vertexIndices = null;
/*  135 */     this.vertices = null;
/*  136 */     this.colors = null;
/*  137 */     this.normals = null;
/*      */     
/*  139 */     this.ccwLoop = true;
/*      */     
/*  141 */     this.earsRandom = true;
/*  142 */     this.earsSorted = true;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  147 */     this.epsilon = 1.0E-12D;
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
/*  171 */     this.earsRandom = false;
/*  172 */     this.earsSorted = false; } public Triangulator(int paramInt) { this.gInfo = null; this.faces = null; this.loops = null; this.chains = null; this.points = null; this.triangles = null; this.list = null; this.randomGen = null; this.numPoints = 0; this.maxNumPoints = 0; this.numList = 0; this.maxNumList = 0; this.numLoops = 0; this.maxNumLoops = 0; this.numTriangles = 0; this.maxNumTriangles = 0; this.numFaces = 0; this.numTexSets = 0; this.firstNode = 0; this.numChains = 0; this.maxNumChains = 0; this.pUnsorted = null; this.maxNumPUnsorted = 0; this.noHashingEdges = false; this.noHashingPnts = false; this.vtxList = null; this.numVtxList = 0; this.numReflex = 0; this.distances = null; this.maxNumDist = 0; this.leftMost = null; this.maxNumLeftMost = 0; this.heap = null; this.numHeap = 0; this.maxNumHeap = 0; this.numZero = 0; this.maxNumPolyArea = 0;
/*      */     this.polyArea = null;
/*      */     this.stripCounts = null;
/*      */     this.vertexIndices = null;
/*      */     this.vertices = null;
/*      */     this.colors = null;
/*      */     this.normals = null;
/*      */     this.ccwLoop = true;
/*      */     this.earsRandom = true;
/*      */     this.earsSorted = true;
/*      */     this.epsilon = 1.0E-12D;
/*  183 */     switch (paramInt) {
/*      */       case 0:
/*  185 */         this.earsRandom = false;
/*  186 */         this.earsSorted = false;
/*      */         return;
/*      */       case 1:
/*  189 */         this.randomGen = new Random();
/*  190 */         this.earsRandom = true;
/*  191 */         this.earsSorted = false;
/*      */         return;
/*      */       case 2:
/*  194 */         this.earsRandom = false;
/*  195 */         this.earsSorted = true;
/*      */         return;
/*      */     } 
/*  198 */     this.earsRandom = false;
/*  199 */     this.earsSorted = false; }
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
/*      */   public void triangulate(GeometryInfo paramGeometryInfo) {
/*  219 */     byte b2 = 0;
/*      */     
/*  221 */     boolean bool1 = false, bool2 = false;
/*      */     
/*  223 */     boolean[] arrayOfBoolean1 = new boolean[1];
/*  224 */     boolean[] arrayOfBoolean2 = new boolean[1];
/*      */     
/*  226 */     if (paramGeometryInfo.getPrimitive() != 5) {
/*  227 */       throw new IllegalArgumentException(J3dUtilsI18N.getString("Triangulator0"));
/*      */     }
/*      */     
/*  230 */     paramGeometryInfo.indexify();
/*      */     
/*  232 */     this.vertices = paramGeometryInfo.getCoordinates();
/*  233 */     if (this.vertices != null) {
/*  234 */       this.vertexIndices = paramGeometryInfo.getCoordinateIndices();
/*      */     } else {
/*  236 */       this.vertexIndices = null;
/*      */     } 
/*  238 */     this.colors = paramGeometryInfo.getColors();
/*  239 */     this.normals = paramGeometryInfo.getNormals();
/*  240 */     this.gInfo = paramGeometryInfo;
/*      */ 
/*      */     
/*  243 */     this.stripCounts = paramGeometryInfo.getStripCounts();
/*      */     
/*  245 */     this.faces = paramGeometryInfo.getContourCounts();
/*  246 */     if (this.faces == null) {
/*  247 */       if (this.stripCounts == null) {
/*  248 */         System.out.println("StripCounts is null! Don't know what to do.");
/*      */       }
/*  250 */       this.faces = new int[this.stripCounts.length];
/*  251 */       for (byte b = 0; b < this.stripCounts.length; b++) {
/*  252 */         this.faces[b] = 1;
/*      */       }
/*      */     } 
/*  255 */     this.numFaces = this.faces.length;
/*  256 */     this.numTexSets = this.gInfo.getTexCoordSetCount();
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
/*  285 */     this.maxNumLoops = 0;
/*  286 */     this.maxNumList = 0;
/*  287 */     this.maxNumPoints = 0;
/*  288 */     this.maxNumDist = 0;
/*  289 */     this.maxNumLeftMost = 0;
/*  290 */     this.maxNumPUnsorted = 0;
/*      */     
/*      */     int i;
/*  293 */     for (i = 0; i < this.faces.length; i++) {
/*  294 */       this.maxNumLoops += this.faces[i];
/*  295 */       for (byte b = 0; b < this.faces[i]; b++, b2++) {
/*  296 */         this.maxNumList += this.stripCounts[b2] + 1;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  301 */     this.maxNumList += 20;
/*      */     
/*  303 */     this.loops = new int[this.maxNumLoops];
/*  304 */     this.list = new ListNode[this.maxNumList];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  310 */     this.numVtxList = 0;
/*  311 */     this.numReflex = 0;
/*      */     
/*  313 */     this.numTriangles = 0;
/*  314 */     this.numChains = 0;
/*  315 */     this.numPoints = 0;
/*  316 */     this.numLoops = 0;
/*  317 */     this.numList = 0;
/*  318 */     b2 = 0;
/*  319 */     byte b3 = 0;
/*      */     
/*  321 */     for (i = 0; i < this.faces.length; i++) {
/*  322 */       for (byte b = 0; b < this.faces[i]; b++, b2++) {
/*      */         
/*  324 */         int m = makeLoopHeader();
/*  325 */         int n = this.loops[m];
/*      */         
/*  327 */         for (byte b4 = 0; b4 < this.stripCounts[b2]; b4++) {
/*  328 */           this.list[this.numList] = new ListNode(this.vertexIndices[b3]);
/*  329 */           int i1 = this.numList++;
/*      */           
/*  331 */           insertAfter(n, i1);
/*  332 */           this.list[i1].setCommonIndex(b3);
/*      */           
/*  334 */           n = i1;
/*  335 */           b3++;
/*      */         } 
/*      */ 
/*      */         
/*  339 */         deleteHook(m);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  347 */     this.maxNumTriangles = this.maxNumList / 2;
/*  348 */     this.triangles = new Triangle[this.maxNumTriangles];
/*      */ 
/*      */     
/*  351 */     setEpsilon(1.0E-8D);
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
/*  363 */     int j = 0;
/*  364 */     int k = 0;
/*  365 */     for (byte b1 = 0; b1 < this.numFaces; b1++) {
/*  366 */       boolean bool; this.ccwLoop = true;
/*  367 */       arrayOfBoolean1[0] = false;
/*  368 */       k = j + this.faces[b1];
/*      */       
/*  370 */       if (this.faces[b1] > 1) {
/*  371 */         bool = true;
/*      */       }
/*  373 */       else if (Simple.simpleFace(this, this.loops[j])) {
/*  374 */         bool = false;
/*      */       } else {
/*  376 */         bool = true;
/*      */       } 
/*  378 */       if (bool) {
/*      */         int m;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  384 */         for (m = 0; m < this.faces[b1]; m++) {
/*  385 */           preProcessList(j + m);
/*      */         }
/*      */         
/*  388 */         Project.projectFace(this, j, k);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  397 */         m = Clean.cleanPolyhedralFace(this, j, k);
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
/*  411 */         if (this.faces[b1] == 1) {
/*      */           
/*  413 */           Orientation.determineOrientation(this, this.loops[j]);
/*      */         }
/*      */         else {
/*      */           
/*  417 */           Orientation.adjustOrientation(this, j, k);
/*      */         } 
/*      */ 
/*      */         
/*  421 */         if (this.faces[b1] > 1) {
/*  422 */           NoHash.prepareNoHashEdges(this, j, k);
/*      */         } else {
/*      */           
/*  425 */           this.noHashingEdges = false;
/*  426 */           this.noHashingPnts = false;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  431 */         for (i = j; i < k; i++) {
/*  432 */           EarClip.classifyAngles(this, this.loops[i]);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  442 */         if (this.faces[b1] > 1) Bridge.constructBridges(this, j, k);
/*      */ 
/*      */         
/*  445 */         resetPolyList(this.loops[j]);
/*  446 */         NoHash.prepareNoHashPnts(this, j);
/*  447 */         EarClip.classifyEars(this, this.loops[j]);
/*  448 */         arrayOfBoolean1[0] = false;
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
/*  460 */         while (!arrayOfBoolean1[0]) {
/*  461 */           if (!EarClip.clipEar(this, arrayOfBoolean1)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  468 */             if (bool1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  475 */               int n = getNode();
/*  476 */               resetPolyList(n);
/*      */               
/*  478 */               this.loops[j] = n;
/*  479 */               if (Desperate.desperate(this, n, j, arrayOfBoolean1))
/*      */               {
/*  481 */                 if (!Desperate.letsHope(this, n))
/*      */                 {
/*      */                   return;
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/*      */               else
/*      */               {
/*      */                 
/*  491 */                 bool1 = false;
/*      */               }
/*      */             
/*      */             } else {
/*      */               
/*  496 */               bool2 = true;
/*      */               
/*  498 */               int n = getNode();
/*  499 */               resetPolyList(n);
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  504 */               EarClip.classifyEars(this, n);
/*  505 */               bool1 = true;
/*      */             } 
/*      */           } else {
/*      */             
/*  509 */             bool1 = false;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  518 */           if (arrayOfBoolean1[0]) {
/*      */             
/*  520 */             int n = getNextChain(arrayOfBoolean2);
/*  521 */             if (arrayOfBoolean2[0]) {
/*      */ 
/*      */ 
/*      */               
/*  525 */               resetPolyList(n);
/*  526 */               this.loops[j] = n;
/*  527 */               this.noHashingPnts = false;
/*  528 */               NoHash.prepareNoHashPnts(this, j);
/*  529 */               EarClip.classifyEars(this, n);
/*  530 */               bool1 = false;
/*  531 */               arrayOfBoolean1[0] = false;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  537 */       j = k;
/*      */     } 
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
/*  550 */     writeTriangleToGeomInfo();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void printVtxList() {
/*  556 */     System.out.println("numReflex " + this.numReflex + " reflexVertices " + this.reflexVertices);
/*      */     
/*  558 */     for (byte b = 0; b < this.numVtxList; b++) {
/*  559 */       System.out.println(b + " pnt " + (this.vtxList[b]).pnt + ", next " + (this.vtxList[b]).next);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void printListData() {
/*  565 */     for (byte b = 0; b < this.numList; b++) {
/*  566 */       System.out.println("list[" + b + "].index " + (this.list[b]).index + ", prev " + (this.list[b]).prev + ", next " + (this.list[b]).next + ", convex " + (this.list[b]).convex + ", vertexIndex " + (this.list[b]).vcntIndex);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void preProcessList(int paramInt) {
/*  577 */     resetPolyList(this.loops[paramInt]);
/*  578 */     int i = this.loops[paramInt];
/*  579 */     int j = i;
/*  580 */     int k = (this.list[j]).next;
/*  581 */     while (k != i) {
/*  582 */       if ((this.list[j]).index == (this.list[k]).index) {
/*  583 */         if (k == this.loops[paramInt])
/*  584 */           this.loops[paramInt] = (this.list[k]).next; 
/*  585 */         deleteLinks(k);
/*      */       } 
/*  587 */       j = (this.list[j]).next;
/*  588 */       k = (this.list[j]).next;
/*      */     } 
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
/*      */   void writeTriangleToGeomInfo() {
/*  627 */     this.gInfo.setPrimitive(1);
/*  628 */     this.gInfo.setContourCounts(null);
/*  629 */     this.gInfo.forgetOldPrim();
/*  630 */     this.gInfo.setStripCounts(null);
/*      */     
/*  632 */     byte b2 = 0;
/*  633 */     int[] arrayOfInt = new int[this.numTriangles * 3];
/*      */     byte b1;
/*  635 */     for (b1 = 0; b1 < this.numTriangles; b1++) {
/*  636 */       int i = this.list[(this.triangles[b1]).v1].getCommonIndex();
/*  637 */       arrayOfInt[b2++] = this.vertexIndices[i];
/*  638 */       i = this.list[(this.triangles[b1]).v2].getCommonIndex();
/*  639 */       arrayOfInt[b2++] = this.vertexIndices[i];
/*  640 */       i = this.list[(this.triangles[b1]).v3].getCommonIndex();
/*  641 */       arrayOfInt[b2++] = this.vertexIndices[i];
/*      */     } 
/*  643 */     this.gInfo.setCoordinateIndices(arrayOfInt);
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
/*  660 */     if (this.normals != null) {
/*  661 */       int[] arrayOfInt1 = this.gInfo.getNormalIndices();
/*  662 */       int[] arrayOfInt2 = new int[this.numTriangles * 3];
/*  663 */       b2 = 0;
/*  664 */       for (b1 = 0; b1 < this.numTriangles; b1++) {
/*  665 */         int i = this.list[(this.triangles[b1]).v1].getCommonIndex();
/*  666 */         arrayOfInt2[b2++] = arrayOfInt1[i];
/*  667 */         i = this.list[(this.triangles[b1]).v2].getCommonIndex();
/*  668 */         arrayOfInt2[b2++] = arrayOfInt1[i];
/*  669 */         i = this.list[(this.triangles[b1]).v3].getCommonIndex();
/*  670 */         arrayOfInt2[b2++] = arrayOfInt1[i];
/*      */       } 
/*  672 */       this.gInfo.setNormalIndices(arrayOfInt2);
/*      */     } 
/*      */     
/*  675 */     if (this.colors != null) {
/*  676 */       b2 = 0;
/*  677 */       int[] arrayOfInt1 = this.gInfo.getColorIndices();
/*  678 */       int[] arrayOfInt2 = new int[this.numTriangles * 3];
/*  679 */       for (b1 = 0; b1 < this.numTriangles; b1++) {
/*  680 */         int i = this.list[(this.triangles[b1]).v1].getCommonIndex();
/*  681 */         arrayOfInt2[b2++] = arrayOfInt1[i];
/*  682 */         i = this.list[(this.triangles[b1]).v2].getCommonIndex();
/*  683 */         arrayOfInt2[b2++] = arrayOfInt1[i];
/*  684 */         i = this.list[(this.triangles[b1]).v3].getCommonIndex();
/*  685 */         arrayOfInt2[b2++] = arrayOfInt1[i];
/*      */       } 
/*  687 */       this.gInfo.setColorIndices(arrayOfInt2);
/*      */     } 
/*      */     
/*  690 */     for (byte b3 = 0; b3 < this.numTexSets; b3++) {
/*  691 */       int[] arrayOfInt1 = new int[this.numTriangles * 3];
/*  692 */       int[] arrayOfInt2 = this.gInfo.getTextureCoordinateIndices(b3);
/*  693 */       b2 = 0;
/*  694 */       for (b1 = 0; b1 < this.numTriangles; b1++) {
/*  695 */         int i = this.list[(this.triangles[b1]).v1].getCommonIndex();
/*  696 */         arrayOfInt1[b2++] = arrayOfInt2[i];
/*  697 */         i = this.list[(this.triangles[b1]).v2].getCommonIndex();
/*  698 */         arrayOfInt1[b2++] = arrayOfInt2[i];
/*  699 */         i = this.list[(this.triangles[b1]).v3].getCommonIndex();
/*  700 */         arrayOfInt1[b2++] = arrayOfInt2[i];
/*      */       } 
/*  702 */       this.gInfo.setTextureCoordinateIndices(b3, arrayOfInt1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  708 */   void setEpsilon(double paramDouble) { this.epsilon = paramDouble; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  714 */   boolean inPolyList(int paramInt) { return (paramInt >= 0 && paramInt < this.numList && this.numList <= this.maxNumList); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  721 */   void updateIndex(int paramInt1, int paramInt2) { (this.list[paramInt1]).index = paramInt2; }
/*      */ 
/*      */ 
/*      */   
/*  725 */   int getAngle(int paramInt) { return (this.list[paramInt]).convex; }
/*      */ 
/*      */ 
/*      */   
/*  729 */   void setAngle(int paramInt1, int paramInt2) { (this.list[paramInt1]).convex = paramInt2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  735 */   void resetPolyList(int paramInt) { this.firstNode = paramInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  740 */   int getNode() { return this.firstNode; }
/*      */ 
/*      */ 
/*      */   
/*  744 */   boolean inLoopList(int paramInt) { return (paramInt >= 0 && paramInt < this.numLoops && this.numLoops <= this.maxNumLoops); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void deleteHook(int paramInt) {
/*  751 */     if (!inLoopList(paramInt)) {
/*  752 */       System.out.println("Triangulator:deleteHook : Loop access out of range.");
/*      */     }
/*  754 */     int i = this.loops[paramInt];
/*  755 */     int j = (this.list[i]).next;
/*  756 */     if (inPolyList(i) && inPolyList(j)) {
/*      */       
/*  758 */       deleteLinks(i);
/*  759 */       this.loops[paramInt] = j;
/*      */     }
/*      */     else {
/*      */       
/*  763 */       System.out.println("Triangulator:deleteHook : List access out of range.");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void deleteLinks(int paramInt) {
/*  771 */     if (inPolyList(paramInt) && inPolyList((this.list[paramInt]).prev) && inPolyList((this.list[paramInt]).next)) {
/*      */ 
/*      */       
/*  774 */       if (this.firstNode == paramInt) {
/*  775 */         this.firstNode = (this.list[paramInt]).next;
/*      */       }
/*  777 */       (this.list[(this.list[paramInt]).next]).prev = (this.list[paramInt]).prev;
/*  778 */       (this.list[(this.list[paramInt]).prev]).next = (this.list[paramInt]).next;
/*  779 */       (this.list[paramInt]).prev = (this.list[paramInt]).next = paramInt;
/*      */     }
/*      */     else {
/*      */       
/*  783 */       System.out.println("Triangulator:deleteLinks : Access out of range.");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void rotateLinks(int paramInt1, int paramInt2) {
/*  793 */     int j = (this.list[paramInt1]).next;
/*  794 */     int k = (this.list[paramInt2]).next;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  799 */     int i = (this.list[paramInt1]).next;
/*  800 */     (this.list[paramInt1]).next = (this.list[paramInt2]).next;
/*  801 */     (this.list[paramInt2]).next = i;
/*      */     
/*  803 */     (this.list[j]).prev = paramInt2;
/*  804 */     (this.list[k]).prev = paramInt1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void storeChain(int paramInt) {
/*  810 */     if (this.numChains >= this.maxNumChains) {
/*      */       
/*  812 */       this.maxNumChains += 20;
/*  813 */       int[] arrayOfInt = this.chains;
/*  814 */       this.chains = new int[this.maxNumChains];
/*  815 */       if (arrayOfInt != null)
/*  816 */         System.arraycopy(arrayOfInt, 0, this.chains, 0, arrayOfInt.length); 
/*      */     } 
/*  818 */     this.chains[this.numChains] = paramInt;
/*  819 */     this.numChains++;
/*      */   }
/*      */ 
/*      */   
/*      */   int getNextChain(boolean[] paramArrayOfBoolean) {
/*  824 */     if (this.numChains > 0) {
/*  825 */       paramArrayOfBoolean[0] = true;
/*  826 */       this.numChains--;
/*  827 */       return this.chains[this.numChains];
/*      */     } 
/*      */     
/*  830 */     paramArrayOfBoolean[0] = false;
/*  831 */     this.numChains = 0;
/*  832 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   void splitSplice(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  837 */     (this.list[paramInt1]).next = paramInt4;
/*  838 */     (this.list[paramInt4]).prev = paramInt1;
/*  839 */     (this.list[paramInt2]).prev = paramInt3;
/*  840 */     (this.list[paramInt3]).next = paramInt2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int makeHook() {
/*  851 */     int i = this.numList;
/*  852 */     if (this.numList >= this.maxNumList) {
/*  853 */       this.maxNumList += 100;
/*      */       
/*  855 */       ListNode[] arrayOfListNode = this.list;
/*  856 */       this.list = new ListNode[this.maxNumList];
/*  857 */       System.arraycopy(arrayOfListNode, 0, this.list, 0, arrayOfListNode.length);
/*      */     } 
/*      */     
/*  860 */     this.list[this.numList] = new ListNode(-1);
/*  861 */     (this.list[this.numList]).prev = i;
/*  862 */     (this.list[this.numList]).next = i;
/*  863 */     (this.list[this.numList]).index = -1;
/*  864 */     this.numList++;
/*      */     
/*  866 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int makeLoopHeader() {
/*  873 */     int j = makeHook();
/*  874 */     if (this.numLoops >= this.maxNumLoops) {
/*  875 */       this.maxNumLoops += 20;
/*      */       
/*  877 */       int[] arrayOfInt = this.loops;
/*  878 */       this.loops = new int[this.maxNumLoops];
/*  879 */       System.arraycopy(arrayOfInt, 0, this.loops, 0, arrayOfInt.length);
/*      */     } 
/*      */     
/*  882 */     this.loops[this.numLoops] = j;
/*  883 */     int i = this.numLoops;
/*  884 */     this.numLoops++;
/*      */     
/*  886 */     return i;
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
/*      */   int makeNode(int paramInt) {
/*  898 */     if (this.numList >= this.maxNumList) {
/*  899 */       this.maxNumList += 100;
/*      */       
/*  901 */       ListNode[] arrayOfListNode = this.list;
/*  902 */       this.list = new ListNode[this.maxNumList];
/*  903 */       System.arraycopy(arrayOfListNode, 0, this.list, 0, arrayOfListNode.length);
/*      */     } 
/*      */     
/*  906 */     this.list[this.numList] = new ListNode(paramInt);
/*      */     
/*  908 */     int i = this.numList;
/*  909 */     (this.list[this.numList]).index = paramInt;
/*  910 */     (this.list[this.numList]).prev = -1;
/*  911 */     (this.list[this.numList]).next = -1;
/*  912 */     this.numList++;
/*      */     
/*  914 */     return i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void insertAfter(int paramInt1, int paramInt2) {
/*  924 */     if (inPolyList(paramInt1) && inPolyList(paramInt2)) {
/*      */       
/*  926 */       (this.list[paramInt2]).next = (this.list[paramInt1]).next;
/*  927 */       (this.list[paramInt2]).prev = paramInt1;
/*  928 */       (this.list[paramInt1]).next = paramInt2;
/*  929 */       int i = (this.list[paramInt2]).next;
/*      */       
/*  931 */       if (inPolyList(i)) {
/*  932 */         (this.list[i]).prev = paramInt2;
/*      */       } else {
/*  934 */         System.out.println("Triangulator:deleteHook : List access out of range.");
/*      */       } 
/*      */       
/*      */       return;
/*      */     } 
/*  939 */     System.out.println("Triangulator:deleteHook : List access out of range.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  947 */   int fetchNextData(int paramInt) { return (this.list[paramInt]).next; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  954 */   int fetchData(int paramInt) { return (this.list[paramInt]).index; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  961 */   int fetchPrevData(int paramInt) { return (this.list[paramInt]).prev; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void swapLinks(int paramInt) {
/*  970 */     int i = (this.list[paramInt]).next;
/*  971 */     (this.list[paramInt]).next = (this.list[paramInt]).prev;
/*  972 */     (this.list[paramInt]).prev = i;
/*  973 */     int j = i;
/*  974 */     while (i != paramInt) {
/*  975 */       j = (this.list[i]).next;
/*  976 */       (this.list[i]).next = (this.list[i]).prev;
/*  977 */       (this.list[i]).prev = j;
/*  978 */       i = j;
/*      */     } 
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
/*      */   void storeTriangle(int paramInt1, int paramInt2, int paramInt3) {
/*  992 */     if (this.numTriangles >= this.maxNumTriangles) {
/*      */       
/*  994 */       this.maxNumTriangles += 50;
/*  995 */       Triangle[] arrayOfTriangle = this.triangles;
/*  996 */       this.triangles = new Triangle[this.maxNumTriangles];
/*  997 */       if (arrayOfTriangle != null) {
/*  998 */         System.arraycopy(arrayOfTriangle, 0, this.triangles, 0, arrayOfTriangle.length);
/*      */       }
/*      */     } 
/* 1001 */     if (this.ccwLoop) {
/* 1002 */       this.triangles[this.numTriangles] = new Triangle(paramInt1, paramInt2, paramInt3);
/*      */     } else {
/* 1004 */       this.triangles[this.numTriangles] = new Triangle(paramInt2, paramInt1, paramInt3);
/* 1005 */     }  this.numTriangles++;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initPnts(int paramInt) {
/* 1012 */     if (this.maxNumPoints < paramInt) {
/* 1013 */       this.maxNumPoints = paramInt;
/* 1014 */       this.points = new Point2f[this.maxNumPoints];
/*      */     } 
/*      */     
/* 1017 */     for (byte b = 0; b < paramInt; b++) {
/* 1018 */       this.points[b] = new Point2f(0.0F, 0.0F);
/*      */     }
/* 1020 */     this.numPoints = 0;
/*      */   }
/*      */ 
/*      */   
/* 1024 */   boolean inPointsList(int paramInt) { return (paramInt >= 0 && paramInt < this.numPoints && this.numPoints <= this.maxNumPoints); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int storePoint(double paramDouble1, double paramDouble2) {
/* 1031 */     if (this.numPoints >= this.maxNumPoints) {
/*      */       
/* 1033 */       this.maxNumPoints += 100;
/* 1034 */       Point2f[] arrayOfPoint2f = this.points;
/* 1035 */       this.points = new Point2f[this.maxNumPoints];
/* 1036 */       if (arrayOfPoint2f != null) {
/* 1037 */         System.arraycopy(arrayOfPoint2f, 0, this.points, 0, arrayOfPoint2f.length);
/*      */       }
/*      */     } 
/* 1040 */     this.points[this.numPoints] = new Point2f((float)paramDouble1, (float)paramDouble2);
/*      */ 
/*      */     
/* 1043 */     int i = this.numPoints;
/* 1044 */     this.numPoints++;
/*      */     
/* 1046 */     return i;
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\geometry\Triangulator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */