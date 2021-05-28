/*      */ package com.sun.j3d.utils.geometry;
/*      */ 
/*      */ import com.sun.j3d.internal.J3dUtilsI18N;
/*      */ import java.awt.Color;
/*      */ import java.util.ArrayList;
/*      */ import javax.vecmath.Color3b;
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
/*      */ public class Stripifier
/*      */ {
/*      */   final boolean DEBUG = false;
/*      */   final boolean CHECK_ORIENT = false;
/*      */   static final int EMPTY = -1;
/*      */   boolean hasNormals = false;
/*      */   boolean hasTextures = false;
/*   99 */   int texSetCount = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   boolean hasColors = false;
/*      */ 
/*      */   
/*      */   boolean colorStrips = false;
/*      */ 
/*      */   
/*      */   StripifierStats stats;
/*      */ 
/*      */   
/*      */   int[] numNhbrs;
/*      */ 
/*      */   
/*      */   public static final int COLLECT_STATS = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   public Stripifier() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public Stripifier(int paramInt) {
/*  124 */     if ((paramInt & true) != 0) {
/*  125 */       this.stats = new StripifierStats();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void stripify(GeometryInfo paramGeometryInfo) {
/*  135 */     long l = System.currentTimeMillis();
/*      */     
/*  137 */     paramGeometryInfo.convertToIndexedTriangles();
/*  138 */     paramGeometryInfo.forgetOldPrim();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  144 */     Face[] arrayOfFace = createFaceArray(paramGeometryInfo);
/*  145 */     Edge[] arrayOfEdge = createEdgeArray(arrayOfFace);
/*  146 */     buildAdjacencies(arrayOfEdge, arrayOfFace);
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
/*  160 */     Node[] arrayOfNode1 = new Node[arrayOfFace.length];
/*      */     
/*  162 */     Node[] arrayOfNode2 = dfSearch(arrayOfFace, arrayOfNode1);
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
/*  174 */     int[] arrayOfInt1 = new int[1];
/*  175 */     int[] arrayOfInt2 = new int[1];
/*  176 */     ArrayList arrayList1 = hamilton(arrayOfNode2, arrayOfInt1, arrayOfInt2);
/*  177 */     int i = arrayOfInt1[0];
/*  178 */     int j = arrayOfInt2[0];
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
/*  194 */     ArrayList arrayList2 = stripe(arrayList1);
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
/*  209 */     concatenate(arrayList2, arrayOfFace);
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
/*  228 */     putBackData(paramGeometryInfo, arrayList2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  234 */     if (this.stats != null) {
/*  235 */       this.stats.updateInfo(System.currentTimeMillis() - l, arrayList2, arrayOfFace.length);
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
/*      */   
/*      */   public StripifierStats getStripifierStats() {
/*  275 */     if (this.stats == null) {
/*  276 */       throw new IllegalStateException(J3dUtilsI18N.getString("Stripifier0"));
/*      */     }
/*  278 */     return this.stats;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Face[] createFaceArray(GeometryInfo paramGeometryInfo) {
/*  285 */     int[] arrayOfInt1 = paramGeometryInfo.getCoordinateIndices();
/*  286 */     int[] arrayOfInt2 = paramGeometryInfo.getNormalIndices();
/*      */     
/*  288 */     int[][] arrayOfInt = (int[][])null;
/*  289 */     int[] arrayOfInt3 = null;
/*  290 */     int[] arrayOfInt4 = null;
/*  291 */     int[] arrayOfInt5 = null;
/*  292 */     this.texSetCount = paramGeometryInfo.getTexCoordSetCount();
/*  293 */     if (this.texSetCount > 0)
/*  294 */     { this.hasTextures = true;
/*  295 */       arrayOfInt = new int[this.texSetCount][];
/*  296 */       for (byte b1 = 0; b1 < this.texSetCount; b1++) {
/*  297 */         arrayOfInt[b1] = paramGeometryInfo.getTextureCoordinateIndices(b1);
/*      */       }
/*  299 */       arrayOfInt3 = new int[this.texSetCount];
/*  300 */       arrayOfInt4 = new int[this.texSetCount];
/*  301 */       arrayOfInt5 = new int[this.texSetCount]; }
/*  302 */     else { this.hasTextures = false; }
/*      */     
/*  304 */     int[] arrayOfInt6 = paramGeometryInfo.getColorIndices();
/*  305 */     Face[] arrayOfFace = new Face[arrayOfInt1.length / 3];
/*      */ 
/*      */     
/*  308 */     byte b = 0;
/*  309 */     for (boolean bool = false; bool < arrayOfInt1.length; ) {
/*  310 */       byte b6, b5, b4, b3, b2, b1; if (arrayOfInt2 != null) {
/*      */         
/*  312 */         this.hasNormals = true;
/*  313 */         b1 = arrayOfInt2[bool];
/*  314 */         b2 = arrayOfInt2[bool + true];
/*  315 */         b3 = arrayOfInt2[bool + 2];
/*      */       }
/*      */       else {
/*      */         
/*  319 */         this.hasNormals = false;
/*  320 */         b1 = -1;
/*  321 */         b2 = -1;
/*  322 */         b3 = -1;
/*      */       } 
/*  324 */       if (this.hasTextures) {
/*  325 */         for (byte b7 = 0; b7 < this.texSetCount; b7++) {
/*  326 */           arrayOfInt3[b7] = arrayOfInt[b7][bool];
/*  327 */           arrayOfInt4[b7] = arrayOfInt[b7][bool + true];
/*  328 */           arrayOfInt5[b7] = arrayOfInt[b7][bool + 2];
/*      */         } 
/*      */       }
/*  331 */       if (arrayOfInt6 != null) {
/*  332 */         this.hasColors = true;
/*  333 */         b4 = arrayOfInt6[bool];
/*  334 */         b5 = arrayOfInt6[bool + true];
/*  335 */         b6 = arrayOfInt6[bool + 2];
/*      */       } else {
/*      */         
/*  338 */         this.hasColors = false;
/*  339 */         b4 = -1;
/*  340 */         b5 = -1;
/*  341 */         b6 = -1;
/*      */       } 
/*  343 */       Vertex vertex1 = new Vertex(arrayOfInt1[bool], b1, this.texSetCount, arrayOfInt3, b4);
/*  344 */       Vertex vertex2 = new Vertex(arrayOfInt1[bool + true], b2, this.texSetCount, arrayOfInt4, b5);
/*  345 */       Vertex vertex3 = new Vertex(arrayOfInt1[bool + 2], b3, this.texSetCount, arrayOfInt5, b6);
/*  346 */       if (!vertex1.equals(vertex2) && !vertex2.equals(vertex3) && !vertex3.equals(vertex1)) {
/*  347 */         arrayOfFace[b] = new Face(b, vertex1, vertex2, vertex3);
/*  348 */         b++;
/*      */       } 
/*  350 */       bool += true;
/*      */     } 
/*      */     
/*  353 */     if (arrayOfFace.length > b) {
/*  354 */       Face[] arrayOfFace1 = arrayOfFace;
/*  355 */       arrayOfFace = new Face[b];
/*  356 */       System.arraycopy(arrayOfFace1, 0, arrayOfFace, 0, b);
/*      */     } 
/*  358 */     return arrayOfFace;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Edge[] createEdgeArray(Face[] paramArrayOfFace) {
/*  365 */     Edge[] arrayOfEdge = new Edge[paramArrayOfFace.length * 3];
/*      */     
/*  367 */     for (byte b = 0; b < paramArrayOfFace.length; b++) {
/*  368 */       Face face = paramArrayOfFace[b];
/*  369 */       arrayOfEdge[b * 3] = new Edge(face.verts[0], face.verts[1], face.key);
/*  370 */       arrayOfEdge[b * 3 + 1] = new Edge(face.verts[1], face.verts[2], face.key);
/*  371 */       arrayOfEdge[b * 3 + 2] = new Edge(face.verts[2], face.verts[0], face.key);
/*      */     } 
/*  373 */     return arrayOfEdge;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void buildAdjacencies(Edge[] paramArrayOfEdge, Face[] paramArrayOfFace) {
/*  381 */     quickSortEdges(paramArrayOfEdge, 0, paramArrayOfEdge.length - 1);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     byte b1;
/*      */ 
/*      */ 
/*      */     
/*  390 */     for (b1 = 0; b1 < paramArrayOfEdge.length; b1++) {
/*      */ 
/*      */       
/*  393 */       Edge edge = paramArrayOfEdge[b1];
/*  394 */       Face face = paramArrayOfFace[edge.face];
/*  395 */       Vertex[] arrayOfVertex = face.verts;
/*      */       
/*  397 */       boolean bool = true;
/*  398 */       if (!arrayOfVertex[0].equals(edge.v1) && !arrayOfVertex[0].equals(edge.v2)) {
/*  399 */         face.edges[0] = edge;
/*  400 */         face.numNhbrs--;
/*  401 */         bool = false;
/*      */       }
/*  403 */       else if (!arrayOfVertex[1].equals(edge.v1) && !arrayOfVertex[1].equals(edge.v2)) {
/*      */         
/*  405 */         face.edges[1] = edge;
/*  406 */         face.numNhbrs--;
/*  407 */         bool = false;
/*      */       }
/*  409 */       else if (!arrayOfVertex[2].equals(edge.v1) && !arrayOfVertex[2].equals(edge.v2)) {
/*      */         
/*  411 */         face.edges[2] = edge;
/*  412 */         face.numNhbrs--;
/*  413 */         bool = false;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  420 */       if (bool)
/*      */       {
/*      */         
/*  423 */         if (edge.v1.equals(edge.v2)) {
/*  424 */           face.edges[--face.numNhbrs] = edge;
/*      */         } else {
/*      */           Vertex vertex;
/*      */           
/*  428 */           if (arrayOfVertex[0].equals(arrayOfVertex[1])) {
/*  429 */             vertex = arrayOfVertex[1];
/*      */           } else {
/*      */             
/*  432 */             vertex = arrayOfVertex[2];
/*      */           } 
/*  434 */           if (arrayOfVertex[0].equals(vertex) && face.edges[false] == null) {
/*  435 */             face.edges[0] = edge;
/*  436 */             face.numNhbrs--;
/*      */           }
/*  438 */           else if (arrayOfVertex[1].equals(vertex) && face.edges[true] == null) {
/*  439 */             face.edges[1] = edge;
/*  440 */             face.numNhbrs--;
/*      */           } else {
/*      */             
/*  443 */             face.edges[2] = edge;
/*  444 */             face.numNhbrs--;
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  452 */     b1 = 0; byte b2 = 0;
/*      */     
/*  454 */     while (b1 < paramArrayOfEdge.length - 1) {
/*  455 */       b2 = b1 + 1;
/*  456 */       if (paramArrayOfEdge[b1].equals(paramArrayOfEdge[b2]))
/*      */       
/*      */       { 
/*      */         
/*  460 */         int i = (paramArrayOfEdge[b1]).face;
/*  461 */         int j = (paramArrayOfEdge[b2]).face;
/*  462 */         if (i != j)
/*  463 */         { byte b; Edge edge = paramArrayOfEdge[b1];
/*  464 */           Face face = paramArrayOfFace[i];
/*  465 */           int k = face.getEdgeIndex(edge);
/*  466 */           if (edge.v1.equals(face.verts[(k + 1) % 3]) && edge.v2.equals(face.verts[(k + 2) % 3])) {
/*      */             
/*  468 */             b = 0;
/*      */           } else {
/*  470 */             b = 1;
/*      */           } 
/*  472 */           edge = paramArrayOfEdge[b2];
/*  473 */           face = paramArrayOfFace[j];
/*  474 */           k = face.getEdgeIndex(edge);
/*  475 */           if (edge.v1.equals(face.verts[(k + 1) % 3]) && edge.v2.equals(face.verts[(k + 2) % 3])) {
/*      */             
/*  477 */             b = b;
/*      */           } else {
/*  479 */             b = (b == 0) ? 1 : 0;
/*      */           } 
/*  481 */           if (b != 0) {
/*  482 */             (paramArrayOfEdge[b1]).face = j;
/*  483 */             (paramArrayOfEdge[b2]).face = i;
/*  484 */             (paramArrayOfFace[i]).numNhbrs++;
/*  485 */             (paramArrayOfFace[j]).numNhbrs++;
/*  486 */             b2++;
/*      */           } else {
/*  488 */             (paramArrayOfEdge[b1]).face = -1;
/*      */           }  }
/*  490 */         else { (paramArrayOfEdge[b1]).face = -1; }
/*      */          }
/*  492 */       else { (paramArrayOfEdge[b1]).face = -1; }
/*  493 */        b1 = b2;
/*      */     } 
/*  495 */     if (b1 <= paramArrayOfEdge.length - 1) (paramArrayOfEdge[b1]).face = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  503 */     for (b1 = 0; b1 < paramArrayOfFace.length; b1++) {
/*  504 */       Face face = paramArrayOfFace[b1];
/*  505 */       if (face.numNhbrs == 3) {
/*  506 */         int i; if ((i = (face.edges[1]).face) == (face.edges[0]).face) {
/*  507 */           (face.edges[1]).face = -1;
/*  508 */           face.numNhbrs--;
/*  509 */           paramArrayOfFace[i].counterEdgeDel(face.edges[1]);
/*      */         }  int j;
/*  511 */         if ((j = (face.edges[2]).face) == (face.edges[0]).face) {
/*  512 */           (face.edges[2]).face = -1;
/*  513 */           face.numNhbrs--;
/*  514 */           paramArrayOfFace[j].counterEdgeDel(face.edges[2]);
/*      */         } 
/*  516 */         if ((face.edges[1]).face != -1 && i == j) {
/*  517 */           (face.edges[2]).face = -1;
/*  518 */           face.numNhbrs--;
/*  519 */           paramArrayOfFace[i].counterEdgeDel(face.edges[2]);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sortEdges(Edge[] paramArrayOfEdge) {
/*  529 */     int i = paramArrayOfEdge.length;
/*  530 */     boolean bool = false;
/*  531 */     Edge edge = null;
/*  532 */     while (i > 1 && !bool) {
/*  533 */       bool = true;
/*  534 */       for (byte b = 1; b < i; b++) {
/*  535 */         if (paramArrayOfEdge[b].lessThan(paramArrayOfEdge[b - true])) {
/*  536 */           edge = paramArrayOfEdge[b - true];
/*  537 */           paramArrayOfEdge[b - true] = paramArrayOfEdge[b];
/*  538 */           paramArrayOfEdge[b] = edge;
/*  539 */           bool = false;
/*      */         } 
/*      */       } 
/*  542 */       i--;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void quickSortEdges(Edge[] paramArrayOfEdge, int paramInt1, int paramInt2) {
/*  550 */     if (paramArrayOfEdge.length > 0) {
/*  551 */       int i = paramInt1;
/*  552 */       int j = paramInt2;
/*  553 */       Edge edge = paramArrayOfEdge[(paramInt1 + paramInt2) / 2];
/*      */       
/*      */       do {
/*  556 */         for (; paramArrayOfEdge[i].lessThan(edge); i++);
/*  557 */         for (; edge.lessThan(paramArrayOfEdge[j]); j--);
/*  558 */         if (i > j)
/*  559 */           continue;  Edge edge1 = paramArrayOfEdge[i];
/*  560 */         paramArrayOfEdge[i] = paramArrayOfEdge[j];
/*  561 */         paramArrayOfEdge[j] = edge1;
/*  562 */         i++;
/*  563 */         j--;
/*      */       }
/*  565 */       while (i <= j);
/*      */       
/*  567 */       if (paramInt1 < j) quickSortEdges(paramArrayOfEdge, paramInt1, j); 
/*  568 */       if (paramInt1 < paramInt2) quickSortEdges(paramArrayOfEdge, i, paramInt2);
/*      */     
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
/*      */   Node[] hybridSearch(Face[] paramArrayOfFace, Node[] paramArrayOfNode) {
/*  581 */     int i = paramArrayOfFace.length;
/*  582 */     int j = 0, k = 0, m = 0; byte b = 0;
/*      */ 
/*      */     
/*  585 */     int[] arrayOfInt1 = { 0, 0, 0, 0 };
/*      */ 
/*      */     
/*  588 */     int[] arrayOfInt2 = new int[i];
/*      */     
/*  590 */     int[] arrayOfInt3 = new int[i];
/*      */ 
/*      */     
/*  593 */     boolean bool = false;
/*      */ 
/*      */     
/*  596 */     Node[] arrayOfNode = new Node[i];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  604 */     int n = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  610 */     for (j = 0; j < i; j++) {
/*  611 */       k = (paramArrayOfFace[j]).numNhbrs;
/*  612 */       arrayOfInt1[k] = arrayOfInt1[k] + 1;
/*  613 */       paramArrayOfNode[j] = new Node(paramArrayOfFace[j]);
/*      */     } 
/*      */ 
/*      */     
/*  617 */     for (j = 1; j < 4; j++) {
/*  618 */       arrayOfInt1[j] = arrayOfInt1[j] + arrayOfInt1[j - 1];
/*      */     }
/*      */ 
/*      */     
/*  622 */     for (j = i - 1; j >= 0; j--) {
/*  623 */       k = (paramArrayOfFace[j]).numNhbrs;
/*  624 */       arrayOfInt1[k] = arrayOfInt1[k] - 1;
/*  625 */       arrayOfInt2[arrayOfInt1[k]] = j;
/*  626 */       arrayOfInt3[j] = arrayOfInt1[k];
/*      */     } 
/*      */ 
/*      */     
/*  630 */     for (j = 0; j < i; j++) {
/*  631 */       if (arrayOfInt2[j] != -1) {
/*  632 */         SortedList sortedList = new SortedList();
/*  633 */         Node node = paramArrayOfNode[arrayOfInt2[j]];
/*  634 */         node.setRoot();
/*  635 */         arrayOfNode[b] = node;
/*  636 */         b++;
/*  637 */         arrayOfInt2[j] = -1;
/*      */         
/*  639 */         while (node != null) {
/*  640 */           Node node1 = null;
/*      */           
/*  642 */           Face face = node.face;
/*  643 */           for (k = 0; k < 3; k++) {
/*  644 */             m = face.getNeighbor(k);
/*  645 */             if (m != -1 && paramArrayOfNode[m].notAccessed()) {
/*      */               
/*  647 */               node1 = paramArrayOfNode[m];
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*  652 */           if (node1 != null) {
/*      */             
/*  654 */             node1.insert(node);
/*  655 */             if (!bool) {
/*  656 */               n = sortedList.sortedInsert(node, n);
/*      */             } else {
/*  658 */               bool = false;
/*  659 */             }  node = node1;
/*  660 */             arrayOfNode[b] = node;
/*  661 */             b++;
/*  662 */             arrayOfInt2[arrayOfInt3[m]] = -1;
/*      */             continue;
/*      */           } 
/*  665 */           node.processed();
/*  666 */           node = sortedList.pop();
/*  667 */           bool = true;
/*  668 */           n = 0;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  673 */     return arrayOfNode;
/*      */   }
/*      */   
/*      */   Node[] dfSearch(Face[] paramArrayOfFace, Node[] paramArrayOfNode) {
/*  677 */     int i = paramArrayOfFace.length;
/*  678 */     int j = 0, k = 0, m = 0; byte b = 0;
/*      */ 
/*      */     
/*  681 */     int[] arrayOfInt1 = { 0, 0, 0, 0 };
/*      */ 
/*      */     
/*  684 */     int[] arrayOfInt2 = new int[i];
/*      */     
/*  686 */     int[] arrayOfInt3 = new int[i];
/*      */ 
/*      */     
/*  689 */     Node[] arrayOfNode = new Node[i];
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
/*  701 */     for (j = 0; j < i; j++) {
/*  702 */       k = (paramArrayOfFace[j]).numNhbrs;
/*  703 */       arrayOfInt1[k] = arrayOfInt1[k] + 1;
/*  704 */       paramArrayOfNode[j] = new Node(paramArrayOfFace[j]);
/*      */     } 
/*      */ 
/*      */     
/*  708 */     for (j = 1; j < 4; ) { arrayOfInt1[j] = arrayOfInt1[j] + arrayOfInt1[j - 1]; j++; }
/*      */ 
/*      */     
/*  711 */     for (j = i - 1; j >= 0; j--) {
/*  712 */       k = (paramArrayOfFace[j]).numNhbrs;
/*  713 */       arrayOfInt1[k] = arrayOfInt1[k] - 1;
/*  714 */       arrayOfInt2[arrayOfInt1[k]] = j;
/*  715 */       arrayOfInt3[j] = arrayOfInt1[k];
/*      */     } 
/*      */     
/*  718 */     setNumNhbrs(paramArrayOfFace);
/*      */     
/*  720 */     for (j = 0; j < i; j++) {
/*  721 */       if (arrayOfInt2[j] != -1) {
/*  722 */         Node node1 = paramArrayOfNode[arrayOfInt2[j]];
/*  723 */         node1.setRoot();
/*  724 */         arrayOfNode[b] = node1;
/*  725 */         b++;
/*  726 */         arrayOfInt2[j] = -1;
/*  727 */         Node node2 = node1;
/*      */ 
/*      */ 
/*      */         
/*  731 */         while (node2 != node1 || node2.right == null)
/*      */         
/*  733 */         { Node node = null;
/*  734 */           Face face = node2.face;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  744 */           m = findNext(node2, paramArrayOfNode, paramArrayOfFace);
/*  745 */           if (m != -1) node = paramArrayOfNode[m]; 
/*  746 */           if (node != null) updateNumNhbrs(node);
/*      */           
/*  748 */           if (node != null) {
/*      */             
/*  750 */             node.insert(node2);
/*  751 */             node2 = node;
/*  752 */             arrayOfNode[b] = node2;
/*  753 */             b++;
/*  754 */             arrayOfInt2[arrayOfInt3[m]] = -1;
/*      */           } else {
/*      */             
/*  757 */             node2.processed();
/*  758 */             node2 = node2.parent;
/*      */           } 
/*  760 */           if (node2 == node1.parent)
/*      */             break;  } 
/*      */       } 
/*  763 */     }  freeNhbrTable();
/*  764 */     return arrayOfNode;
/*      */   }
/*      */   
/*      */   int findNext(Node paramNode, Node[] paramArrayOfNode, Face[] paramArrayOfFace) {
/*  768 */     Face face = paramNode.face;
/*      */     
/*  770 */     if (face.numNhbrs == 0) return -1;
/*      */ 
/*      */     
/*  773 */     int[] arrayOfInt1 = new int[3];
/*  774 */     int[] arrayOfInt2 = { -1, -1, -1 };
/*      */ 
/*      */     
/*  777 */     byte b = 0; int i;
/*  778 */     for (i = 0; i < 3; i++) {
/*  779 */       int j; if ((j = face.getNeighbor(i)) != -1 && paramArrayOfNode[j].notAccessed()) {
/*      */         
/*  781 */         arrayOfInt2[b] = j;
/*  782 */         arrayOfInt1[b] = this.numNhbrs[j];
/*  783 */         b++;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  788 */     if (b == 0) return -1;
/*      */ 
/*      */     
/*  791 */     if (b == 1) return arrayOfInt2[0];
/*      */     
/*  793 */     if (b == 2) {
/*      */       
/*  795 */       if (arrayOfInt1[0] == arrayOfInt1[1] && arrayOfInt1[0] != 0) {
/*  796 */         arrayOfInt1[0] = resetNhbr(arrayOfInt2[0], paramArrayOfFace, paramArrayOfNode);
/*  797 */         arrayOfInt1[1] = resetNhbr(arrayOfInt2[1], paramArrayOfFace, paramArrayOfNode);
/*      */       } 
/*      */       
/*  800 */       if (arrayOfInt1[0] < arrayOfInt1[1]) return arrayOfInt2[0]; 
/*  801 */       if (arrayOfInt1[1] < arrayOfInt1[0]) return arrayOfInt2[1];
/*      */ 
/*      */       
/*      */       Node node;
/*  805 */       if ((node = paramNode.parent) != null) {
/*  806 */         int j; Face face1 = node.face;
/*  807 */         i = face1.findSharedEdge(face.key); Node node1;
/*  808 */         if ((node1 = node.parent) != null) {
/*  809 */           Face face2 = node1.face;
/*  810 */           if (face1.getNeighbor((i + 1) % 3) == face2.key) {
/*  811 */             j = (face1.verts[(i + 2) % 3]).index;
/*      */           } else {
/*      */             
/*  814 */             j = (face1.verts[(i + 1) % 3]).index;
/*      */           } 
/*      */         } else {
/*      */           
/*  818 */           j = (face1.verts[(i + 1) % 3]).index;
/*      */         } 
/*  820 */         i = face.findSharedEdge(arrayOfInt2[0]);
/*  821 */         if ((face.verts[i]).index == j) return arrayOfInt2[0]; 
/*  822 */         return arrayOfInt2[1];
/*      */       } 
/*  824 */       return arrayOfInt2[0];
/*      */     } 
/*      */ 
/*      */     
/*  828 */     if (arrayOfInt1[0] < arrayOfInt1[1] && arrayOfInt1[0] < arrayOfInt1[2]) return arrayOfInt2[0]; 
/*  829 */     if (arrayOfInt1[1] < arrayOfInt1[0] && arrayOfInt1[1] < arrayOfInt1[2]) return arrayOfInt2[1]; 
/*  830 */     if (arrayOfInt1[2] < arrayOfInt1[0] && arrayOfInt1[2] < arrayOfInt1[1]) return arrayOfInt2[2]; 
/*  831 */     if (arrayOfInt1[0] == arrayOfInt1[1] && arrayOfInt1[0] < arrayOfInt1[2]) {
/*  832 */       if (arrayOfInt1[0] != 0) {
/*  833 */         arrayOfInt1[0] = resetNhbr(arrayOfInt2[0], paramArrayOfFace, paramArrayOfNode);
/*  834 */         arrayOfInt1[1] = resetNhbr(arrayOfInt2[1], paramArrayOfFace, paramArrayOfNode);
/*      */       } 
/*  836 */       if (arrayOfInt1[0] <= arrayOfInt1[1]) return arrayOfInt2[0]; 
/*  837 */       return arrayOfInt2[1];
/*      */     } 
/*  839 */     if (arrayOfInt1[1] == arrayOfInt1[2] && arrayOfInt1[1] < arrayOfInt1[0]) {
/*  840 */       if (arrayOfInt1[1] != 0) {
/*  841 */         arrayOfInt1[1] = resetNhbr(arrayOfInt2[1], paramArrayOfFace, paramArrayOfNode);
/*  842 */         arrayOfInt1[2] = resetNhbr(arrayOfInt2[2], paramArrayOfFace, paramArrayOfNode);
/*      */       } 
/*  844 */       if (arrayOfInt1[1] <= arrayOfInt1[2]) return arrayOfInt2[1]; 
/*  845 */       return arrayOfInt2[2];
/*      */     } 
/*  847 */     if (arrayOfInt1[2] == arrayOfInt1[0] && arrayOfInt1[2] < arrayOfInt1[1]) {
/*  848 */       if (arrayOfInt1[0] != 0) {
/*  849 */         arrayOfInt1[0] = resetNhbr(arrayOfInt2[0], paramArrayOfFace, paramArrayOfNode);
/*  850 */         arrayOfInt1[2] = resetNhbr(arrayOfInt2[2], paramArrayOfFace, paramArrayOfNode);
/*      */       } 
/*  852 */       if (arrayOfInt1[0] <= arrayOfInt1[2]) return arrayOfInt2[0]; 
/*  853 */       return arrayOfInt2[2];
/*      */     } 
/*      */     
/*  856 */     if (arrayOfInt1[0] != 0) {
/*  857 */       arrayOfInt1[0] = resetNhbr(arrayOfInt2[0], paramArrayOfFace, paramArrayOfNode);
/*  858 */       arrayOfInt1[1] = resetNhbr(arrayOfInt2[1], paramArrayOfFace, paramArrayOfNode);
/*  859 */       arrayOfInt1[2] = resetNhbr(arrayOfInt2[2], paramArrayOfFace, paramArrayOfNode);
/*      */     } 
/*  861 */     if (arrayOfInt1[0] <= arrayOfInt1[1] && arrayOfInt1[0] <= arrayOfInt1[2]) return arrayOfInt2[0]; 
/*  862 */     if (arrayOfInt1[1] <= arrayOfInt1[2]) return arrayOfInt2[1]; 
/*  863 */     return arrayOfInt2[2];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void setNumNhbrs(Face[] paramArrayOfFace) {
/*  869 */     int i = paramArrayOfFace.length;
/*  870 */     this.numNhbrs = new int[i];
/*  871 */     for (byte b = 0; b < i; b++) {
/*  872 */       this.numNhbrs[b] = (paramArrayOfFace[b]).numNhbrs;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*  877 */   void freeNhbrTable() { this.numNhbrs = null; }
/*      */ 
/*      */   
/*      */   void updateNumNhbrs(Node paramNode) {
/*  881 */     Face face = paramNode.face;
/*      */     int i;
/*  883 */     if ((i = face.getNeighbor(0)) != -1) this.numNhbrs[i] = this.numNhbrs[i] - 1; 
/*  884 */     if ((i = face.getNeighbor(1)) != -1) this.numNhbrs[i] = this.numNhbrs[i] - 1; 
/*  885 */     if ((i = face.getNeighbor(2)) != -1) this.numNhbrs[i] = this.numNhbrs[i] - 1; 
/*      */   }
/*      */   
/*      */   int resetNhbr(int paramInt, Face[] paramArrayOfFace, Node[] paramArrayOfNode) {
/*  889 */     int i = -1;
/*  890 */     Face face = paramArrayOfFace[paramInt];
/*      */     
/*  892 */     for (byte b = 0; b < 3; b++) {
/*  893 */       int j; if ((j = face.getNeighbor(b)) != -1 && paramArrayOfNode[j].notAccessed())
/*      */       {
/*  895 */         if (i == -1 || i > this.numNhbrs[j]) i = this.numNhbrs[j]; 
/*      */       }
/*      */     } 
/*  898 */     return i;
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
/*      */   ArrayList hamilton(Node[] paramArrayOfNode, int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
/*  910 */     int i = paramArrayOfNode.length;
/*      */     
/*  912 */     byte b1 = 0;
/*      */     
/*  914 */     byte b2 = 0;
/*      */ 
/*      */ 
/*      */     
/*  918 */     ArrayList arrayList = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  924 */     for (int j = i - 1; j >= 0; j--) {
/*  925 */       Node node = paramArrayOfNode[j];
/*      */ 
/*      */       
/*  928 */       if (node.isRoot()) {
/*      */         
/*  930 */         b2++;
/*      */         
/*  932 */         ArrayList arrayList1 = new ArrayList();
/*      */         
/*  934 */         arrayList1.add(0, node.face);
/*      */ 
/*      */         
/*  937 */         Node node1 = node.left;
/*  938 */         while (node1 != null) {
/*  939 */           arrayList1.add(0, node1.face);
/*  940 */           node1 = node1.left;
/*      */         } 
/*      */ 
/*      */         
/*  944 */         node1 = node.right;
/*  945 */         while (node1 != null) {
/*  946 */           arrayList1.add(arrayList1.size(), node1.face);
/*  947 */           node1 = node1.left;
/*      */         } 
/*      */ 
/*      */         
/*  951 */         b1++;
/*      */         
/*  953 */         arrayList.add(arrayList1);
/*      */ 
/*      */       
/*      */       }
/*  957 */       else if (node.numChildren == 2) {
/*      */ 
/*      */ 
/*      */         
/*  961 */         Node node2 = node.parent;
/*  962 */         if (node2.isRoot() && node2.numChildren == 1) {
/*  963 */           node2 = node.right;
/*  964 */           if (node2.left != null) { node = node2; }
/*  965 */           else { node = node.left; }
/*      */         
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  971 */         node.remove();
/*      */ 
/*      */         
/*  974 */         ArrayList arrayList1 = new ArrayList();
/*      */         
/*  976 */         arrayList1.add(0, node.face);
/*      */ 
/*      */         
/*  979 */         Node node1 = node.left;
/*  980 */         while (node1 != null) {
/*  981 */           arrayList1.add(0, node1.face);
/*  982 */           node1 = node1.left;
/*      */         } 
/*      */ 
/*      */         
/*  986 */         node1 = node.right;
/*  987 */         while (node1 != null) {
/*  988 */           arrayList1.add(arrayList1.size(), node1.face);
/*  989 */           node1 = node1.left;
/*      */         } 
/*      */ 
/*      */         
/*  993 */         b1++;
/*      */         
/*  995 */         arrayList.add(arrayList1);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1000 */     paramArrayOfInt1[0] = b1;
/* 1001 */     paramArrayOfInt2[0] = b2;
/*      */ 
/*      */     
/* 1004 */     return arrayList;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ArrayList stripe(ArrayList paramArrayList) {
/* 1011 */     int i = paramArrayList.size();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1019 */     ArrayList arrayList = new ArrayList();
/* 1020 */     boolean bool = true;
/*      */     
/* 1022 */     Vertex[] arrayOfVertex = new Vertex[4];
/*      */ 
/*      */     
/* 1025 */     for (byte b = 0; b < i; b++) {
/* 1026 */       ArrayList arrayList1 = (ArrayList)paramArrayList.get(b);
/* 1027 */       byte b1 = 0;
/* 1028 */       boolean bool1 = false;
/* 1029 */       Face face = getNextFace(arrayList1, b1++);
/*      */ 
/*      */       
/* 1032 */       while (!bool1) {
/* 1033 */         Istream istream; boolean bool2 = true;
/*      */ 
/*      */ 
/*      */         
/* 1037 */         if (stripDone(arrayList1, b1)) {
/*      */           
/* 1039 */           istream = new Istream(face.verts, 3, false);
/*      */           
/* 1041 */           istream.head = face.key;
/* 1042 */           bool1 = true;
/*      */ 
/*      */           
/* 1045 */           istream.tail = face.key;
/*      */         }
/*      */         else {
/*      */           
/* 1049 */           Face face1 = face;
/* 1050 */           face = getNextFace(arrayList1, b1++);
/*      */ 
/*      */ 
/*      */           
/* 1054 */           int j = face1.findSharedEdge(face.key);
/* 1055 */           arrayOfVertex[0] = face1.verts[j];
/* 1056 */           arrayOfVertex[1] = face1.verts[(j + 1) % 3];
/* 1057 */           arrayOfVertex[2] = face1.verts[(j + 2) % 3];
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
/* 1090 */           j = face.findSharedEdge(face1.key);
/* 1091 */           arrayOfVertex[3] = face.verts[j];
/* 1092 */           istream = new Istream(arrayOfVertex, 4, false);
/*      */           
/* 1094 */           istream.head = face1.key;
/*      */ 
/*      */           
/* 1097 */           if (stripDone(arrayList1, b1)) {
/* 1098 */             bool1 = true;
/*      */             
/* 1100 */             istream.tail = face.key;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1106 */           while (bool2 && !stripDone(arrayList1, b1)) {
/* 1107 */             face1 = face;
/* 1108 */             face = getNextFace(arrayList1, b1++);
/* 1109 */             j = face.findSharedEdge(face1.key);
/*      */ 
/*      */ 
/*      */             
/* 1113 */             if (seq(istream, face, j)) {
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
/* 1145 */               istream.append(face.verts[j]);
/*      */ 
/*      */               
/* 1148 */               if (stripDone(arrayList1, b1)) {
/* 1149 */                 bool1 = true;
/*      */ 
/*      */                 
/* 1152 */                 istream.tail = face.key;
/*      */               } 
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
/*      */               continue;
/*      */             } 
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
/* 1190 */             istream.swapEnd();
/*      */ 
/*      */             
/* 1193 */             istream.append(face.verts[j]);
/*      */ 
/*      */             
/* 1196 */             if (stripDone(arrayList1, b1)) {
/* 1197 */               bool1 = true;
/*      */               
/* 1199 */               istream.tail = face.key;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1207 */         arrayList.add(istream);
/*      */       } 
/*      */     } 
/* 1210 */     return arrayList;
/*      */   }
/*      */   
/*      */   boolean stripDone(ArrayList paramArrayList, int paramInt) {
/* 1214 */     if (paramInt < paramArrayList.size()) {
/* 1215 */       return false;
/*      */     }
/* 1217 */     return true;
/*      */   }
/*      */   
/*      */   boolean seq(Istream paramIstream, Face paramFace, int paramInt) {
/* 1221 */     int i = paramIstream.length;
/* 1222 */     Vertex vertex1 = (paramFace.edges[paramInt]).v1;
/* 1223 */     Vertex vertex2 = (paramFace.edges[paramInt]).v2;
/* 1224 */     Vertex vertex3 = paramIstream.istream[i - 1];
/* 1225 */     Vertex vertex4 = paramIstream.istream[i - 2];
/* 1226 */     if ((vertex1.equals(vertex4) && vertex2.equals(vertex3)) || (vertex1.equals(vertex3) && vertex2.equals(vertex4)))
/*      */     {
/* 1228 */       return true;
/*      */     }
/* 1230 */     return false;
/*      */   }
/*      */   
/*      */   boolean orientSeq(boolean paramBoolean, Istream paramIstream, Face paramFace) {
/* 1234 */     int i = paramIstream.length;
/* 1235 */     Vertex vertex1 = paramIstream.istream[i - 1];
/* 1236 */     Vertex vertex2 = paramIstream.istream[i - 2];
/* 1237 */     if ((paramBoolean && checkOrientCCWSeq(vertex1, vertex2, paramFace)) || (!paramBoolean && checkOrientCWSeq(vertex1, vertex2, paramFace)))
/*      */     {
/* 1239 */       return true;
/*      */     }
/* 1241 */     return false;
/*      */   }
/*      */   
/*      */   boolean orientZAT(boolean paramBoolean, Istream paramIstream, Face paramFace) {
/* 1245 */     int i = paramIstream.length;
/* 1246 */     Vertex vertex1 = paramIstream.istream[i - 1];
/* 1247 */     Vertex vertex2 = paramIstream.istream[i - 3];
/* 1248 */     if ((paramBoolean && checkOrientCWSeq(vertex1, vertex2, paramFace)) || (!paramBoolean && checkOrientCCWSeq(vertex1, vertex2, paramFace)))
/*      */     {
/* 1250 */       return true;
/*      */     }
/* 1252 */     return false;
/*      */   }
/*      */   
/*      */   boolean checkOrientCWSeq(Vertex paramVertex1, Vertex paramVertex2, Face paramFace) {
/* 1256 */     System.out.println("checkOrientCWSeq");
/* 1257 */     System.out.println("last = " + paramVertex1.index);
/* 1258 */     System.out.println("prev = " + paramVertex2.index);
/* 1259 */     System.out.print("face = ");
/* 1260 */     paramFace.printVertices();
/* 1261 */     if (paramVertex1.equals(paramFace.verts[0])) {
/* 1262 */       if (!paramVertex2.equals(paramFace.verts[1]))
/*      */       {
/* 1264 */         return false;
/*      */       }
/*      */     }
/* 1267 */     else if (paramVertex1.equals(paramFace.verts[1])) {
/* 1268 */       if (!paramVertex2.equals(paramFace.verts[2]))
/*      */       {
/* 1270 */         return false;
/*      */       }
/*      */     }
/* 1273 */     else if (paramVertex1.equals(paramFace.verts[2]) && 
/* 1274 */       !paramVertex2.equals(paramFace.verts[0])) {
/*      */       
/* 1276 */       return false;
/*      */     } 
/*      */     
/* 1279 */     return true;
/*      */   }
/*      */   
/*      */   boolean checkOrientCCWSeq(Vertex paramVertex1, Vertex paramVertex2, Face paramFace) {
/* 1283 */     System.out.println("checkOrientCCWSeq");
/* 1284 */     System.out.println("last = " + paramVertex1.index);
/* 1285 */     System.out.println("prev = " + paramVertex2.index);
/* 1286 */     System.out.print("face = ");
/* 1287 */     paramFace.printVertices();
/* 1288 */     if (paramVertex2.equals(paramFace.verts[0])) {
/* 1289 */       if (!paramVertex1.equals(paramFace.verts[1])) {
/* 1290 */         System.out.println("ORIENTATION PROBLEM!");
/* 1291 */         return false;
/*      */       }
/*      */     
/* 1294 */     } else if (paramVertex2.equals(paramFace.verts[1])) {
/* 1295 */       if (!paramVertex1.equals(paramFace.verts[2])) {
/* 1296 */         System.out.println("ORIENTATION PROBLEM!");
/* 1297 */         return false;
/*      */       }
/*      */     
/* 1300 */     } else if (paramVertex2.equals(paramFace.verts[2]) && 
/* 1301 */       !paramVertex1.equals(paramFace.verts[0])) {
/* 1302 */       System.out.println("ORIENTATION PROBLEM!");
/* 1303 */       return false;
/*      */     } 
/*      */     
/* 1306 */     return true;
/*      */   }
/*      */   
/*      */   Face getNextFace(ArrayList paramArrayList, int paramInt) {
/* 1310 */     if (paramArrayList.size() > paramInt) return (Face)paramArrayList.get(paramInt); 
/* 1311 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void concatenate(ArrayList paramArrayList, Face[] paramArrayOfFace) {
/* 1320 */     int i = paramArrayOfFace.length;
/* 1321 */     int[] arrayOfInt = new int[i];
/*      */     
/*      */     byte b;
/*      */     
/* 1325 */     for (b = 0; b < i; b++) {
/* 1326 */       arrayOfInt[b] = -1;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1331 */     for (b = 0; b < paramArrayList.size(); b++) {
/* 1332 */       Istream istream = (Istream)paramArrayList.get(b);
/* 1333 */       arrayOfInt[istream.head] = b;
/* 1334 */       arrayOfInt[istream.tail] = b;
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
/* 1346 */     reduceCostByTwo(paramArrayList, paramArrayOfFace, arrayOfInt);
/* 1347 */     reduceCostByOne(paramArrayList, paramArrayOfFace, arrayOfInt);
/* 1348 */     reduceCostByZero(paramArrayList, paramArrayOfFace, arrayOfInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void reduceCostByTwo(ArrayList paramArrayList, Face[] paramArrayOfFace, int[] paramArrayOfInt) {
/* 1357 */     int i = paramArrayOfFace.length;
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
/* 1375 */     for (byte b = 0; b < i; b++) {
/* 1376 */       int j = paramArrayOfInt[b];
/* 1377 */       if (j != -1) {
/* 1378 */         boolean bool1 = false, bool2 = false;
/* 1379 */         Istream istream = (Istream)paramArrayList.get(j);
/* 1380 */         int k = istream.length;
/* 1381 */         Vertex[] arrayOfVertex1 = istream.istream;
/* 1382 */         Face face = paramArrayOfFace[b];
/* 1383 */         Vertex[] arrayOfVertex2 = face.verts;
/*      */ 
/*      */         
/* 1386 */         if (!istream.fan)
/*      */         {
/*      */           
/* 1389 */           if (k == 3) {
/*      */ 
/*      */             
/* 1392 */             for (byte b1 = 0; b1 < 3; b1++) {
/* 1393 */               int n = face.getNeighbor(b1); int m;
/* 1394 */               if (n != -1 && (m = paramArrayOfInt[n]) != -1 && m != j)
/*      */               {
/*      */ 
/*      */                 
/* 1398 */                 arrayOfVertex1[0] = arrayOfVertex2[b1];
/* 1399 */                 arrayOfVertex1[1] = arrayOfVertex2[(b1 + 1) % 3];
/* 1400 */                 arrayOfVertex1[2] = arrayOfVertex2[(b1 + 2) % 3];
/*      */ 
/*      */                 
/* 1403 */                 Istream istream1 = (Istream)paramArrayList.get(m);
/* 1404 */                 int i1 = istream1.length;
/* 1405 */                 if (n != istream1.head) {
/* 1406 */                   istream1.invert();
/*      */                   
/* 1408 */                   if (i1 % 2 != 0) bool2 = true; 
/*      */                 } 
/* 1410 */                 Vertex[] arrayOfVertex = istream1.istream;
/*      */ 
/*      */                 
/* 1413 */                 if (i1 == 3) {
/*      */                   
/* 1415 */                   int i2 = paramArrayOfFace[n].findSharedEdge(b);
/* 1416 */                   istream.append((paramArrayOfFace[n]).verts[i2]);
/* 1417 */                   istream1.length = 0;
/* 1418 */                   istream1.istream = null;
/* 1419 */                   istream.tail = n;
/* 1420 */                   paramArrayOfInt[n] = j;
/* 1421 */                   b--;
/*      */ 
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */                 
/* 1427 */                 if (i1 == 4 && (arrayOfVertex1[1]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[2]).index == (arrayOfVertex[2]).index) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 1434 */                   Vertex vertex = arrayOfVertex[1];
/* 1435 */                   arrayOfVertex[1] = arrayOfVertex[2];
/* 1436 */                   arrayOfVertex[2] = vertex;
/*      */                 } 
/*      */ 
/*      */                 
/* 1440 */                 if ((arrayOfVertex1[1]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[2]).index == (arrayOfVertex[1]).index) {
/*      */ 
/*      */ 
/*      */                   
/* 1444 */                   istream.addStream(istream1);
/* 1445 */                   paramArrayOfInt[n] = -1;
/* 1446 */                   paramArrayOfInt[istream.tail] = j;
/*      */                   
/* 1448 */                   b--;
/*      */                   break;
/*      */                 } 
/* 1451 */                 if (bool2) {
/* 1452 */                   istream1.invert();
/* 1453 */                   bool2 = false;
/*      */                 
/*      */                 }
/*      */ 
/*      */               
/*      */               }
/*      */ 
/*      */             
/*      */             }
/*      */           
/*      */           }
/* 1464 */           else if (b == istream.tail || k % 2 == 0) {
/*      */             int m;
/*      */             
/* 1467 */             if (b != istream.tail) {
/* 1468 */               istream.invert();
/* 1469 */               arrayOfVertex1 = istream.istream;
/*      */             } 
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
/* 1481 */             Vertex vertex = arrayOfVertex1[k - 3];
/*      */ 
/*      */             
/* 1484 */             int n = -1;
/* 1485 */             if ((arrayOfVertex2[0]).index == vertex.index) { n = 0; }
/* 1486 */             else if ((arrayOfVertex2[1]).index == vertex.index) { n = 1; }
/* 1487 */             else if ((arrayOfVertex2[2]).index == vertex.index) { n = 2; }
/* 1488 */              if (n == -1);
/*      */ 
/*      */             
/* 1491 */             int i1 = face.getNeighbor(n);
/* 1492 */             if (i1 == -1) { m = i1; }
/* 1493 */             else { m = paramArrayOfInt[i1]; }
/* 1494 */              if (m != -1 && ((Istream)paramArrayList.get(m)).fan != istream.fan)
/*      */             {
/*      */               
/* 1497 */               m = -1;
/*      */             }
/*      */             
/* 1500 */             if (m != -1 && m != j) {
/* 1501 */               Istream istream1 = (Istream)paramArrayList.get(m);
/* 1502 */               int i2 = istream1.length;
/*      */ 
/*      */ 
/*      */               
/* 1506 */               if (i1 != istream1.head) {
/* 1507 */                 istream1.invert();
/*      */                 
/* 1509 */                 if (i2 % 2 != 0) bool2 = true; 
/*      */               } 
/* 1511 */               Vertex[] arrayOfVertex = istream1.istream;
/*      */ 
/*      */               
/* 1514 */               if (i2 == 3) {
/*      */                 
/* 1516 */                 n = paramArrayOfFace[i1].findSharedEdge(b);
/* 1517 */                 istream.append((paramArrayOfFace[i1]).verts[n]);
/* 1518 */                 istream1.length = 0;
/* 1519 */                 istream1.istream = null;
/* 1520 */                 istream.tail = i1;
/* 1521 */                 paramArrayOfInt[b] = -1;
/* 1522 */                 paramArrayOfInt[i1] = j;
/*      */               
/*      */               }
/*      */               else {
/*      */                 
/* 1527 */                 if (i2 == 4 && (arrayOfVertex1[k - 2]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[2]).index) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 1534 */                   vertex = arrayOfVertex[1];
/* 1535 */                   arrayOfVertex[1] = arrayOfVertex[2];
/* 1536 */                   arrayOfVertex[2] = vertex;
/*      */                 } 
/*      */ 
/*      */                 
/* 1540 */                 if ((arrayOfVertex1[k - 2]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[1]).index)
/*      */                 
/*      */                 { 
/* 1543 */                   istream.addStream(istream1);
/* 1544 */                   paramArrayOfInt[b] = -1;
/* 1545 */                   paramArrayOfInt[istream.tail] = j;
/* 1546 */                   paramArrayOfInt[i1] = -1; }
/*      */                 
/* 1548 */                 else if (bool2) { istream1.invert(); }
/*      */               
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void reduceCostByOne(ArrayList paramArrayList, Face[] paramArrayOfFace, int[] paramArrayOfInt) {
/* 1563 */     int i = paramArrayOfFace.length;
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
/* 1581 */     for (byte b = 0; b < i; b++) {
/* 1582 */       int j = paramArrayOfInt[b];
/* 1583 */       if (j != -1 && !((Istream)paramArrayList.get(j)).fan) {
/* 1584 */         boolean bool = false;
/* 1585 */         Istream istream = (Istream)paramArrayList.get(j);
/* 1586 */         Vertex[] arrayOfVertex1 = istream.istream;
/* 1587 */         Face face = paramArrayOfFace[b];
/* 1588 */         Vertex[] arrayOfVertex2 = face.verts;
/* 1589 */         int k = istream.length;
/*      */ 
/*      */         
/* 1592 */         if (k == 3) {
/*      */ 
/*      */           
/* 1595 */           for (byte b1 = 0; b1 < 3; b1++) {
/* 1596 */             int n = face.getNeighbor(b1); int m;
/* 1597 */             if (n != -1 && (m = paramArrayOfInt[n]) != -1 && m != j && !((Istream)paramArrayList.get(m)).fan)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1603 */               arrayOfVertex1[0] = arrayOfVertex2[b1];
/* 1604 */               arrayOfVertex1[1] = arrayOfVertex2[(b1 + 1) % 3];
/* 1605 */               arrayOfVertex1[2] = arrayOfVertex2[(b1 + 2) % 3];
/*      */ 
/*      */               
/* 1608 */               Istream istream1 = (Istream)paramArrayList.get(m);
/* 1609 */               int i1 = istream1.length;
/* 1610 */               if (n != istream1.head) {
/* 1611 */                 istream1.invert();
/* 1612 */                 if (i1 % 2 != 0) bool = true; 
/*      */               } 
/* 1614 */               Vertex[] arrayOfVertex = istream1.istream;
/*      */ 
/*      */ 
/*      */               
/* 1618 */               if (i1 == 4 && (((arrayOfVertex1[1]).index == (arrayOfVertex[2]).index && (arrayOfVertex1[2]).index == (arrayOfVertex[0]).index) || ((arrayOfVertex1[1]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[2]).index == (arrayOfVertex[2]).index))) {
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1623 */                 Vertex vertex = arrayOfVertex[1];
/* 1624 */                 arrayOfVertex[1] = arrayOfVertex[2];
/* 1625 */                 arrayOfVertex[2] = vertex;
/*      */               } 
/*      */               
/* 1628 */               if ((arrayOfVertex1[1]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[2]).index == (arrayOfVertex[1]).index) {
/*      */ 
/*      */                 
/* 1631 */                 istream.addStream(istream1);
/* 1632 */                 paramArrayOfInt[n] = -1;
/* 1633 */                 paramArrayOfInt[istream.tail] = j;
/* 1634 */                 b--;
/*      */                 
/*      */                 break;
/*      */               } 
/* 1638 */               if ((arrayOfVertex1[1]).index == (arrayOfVertex[1]).index && (arrayOfVertex1[2]).index == (arrayOfVertex[0]).index) {
/*      */ 
/*      */                 
/* 1641 */                 istream.append(arrayOfVertex[1]);
/* 1642 */                 istream.addStream(istream1);
/* 1643 */                 paramArrayOfInt[n] = -1;
/* 1644 */                 paramArrayOfInt[istream.tail] = j;
/* 1645 */                 b--;
/*      */                 
/*      */                 break;
/*      */               } 
/* 1649 */               if ((arrayOfVertex1[1]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[2]).index == (arrayOfVertex[2]).index) {
/*      */ 
/*      */                 
/* 1652 */                 arrayOfVertex[0] = arrayOfVertex[2];
/* 1653 */                 istream.append(arrayOfVertex[1]);
/* 1654 */                 istream.addStream(istream1);
/* 1655 */                 paramArrayOfInt[n] = -1;
/* 1656 */                 paramArrayOfInt[istream.tail] = j;
/* 1657 */                 b--;
/*      */                 
/*      */                 break;
/*      */               } 
/* 1661 */               if (bool) {
/* 1662 */                 istream1.invert();
/* 1663 */                 bool = false;
/*      */               }
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */         
/* 1670 */         } else if (b == istream.tail || k % 2 == 0) {
/*      */           int n, m;
/*      */           
/* 1673 */           if (b != istream.tail) {
/* 1674 */             istream.invert();
/* 1675 */             arrayOfVertex1 = istream.istream;
/*      */           } 
/*      */           
/* 1678 */           Vertex vertex = arrayOfVertex1[k - 3];
/*      */ 
/*      */           
/* 1681 */           byte b1 = -1;
/* 1682 */           if ((arrayOfVertex2[0]).index == vertex.index) { b1 = 0; }
/* 1683 */           else if ((arrayOfVertex2[1]).index == vertex.index) { b1 = 1; }
/* 1684 */           else if ((arrayOfVertex2[2]).index == vertex.index) { b1 = 2; }
/* 1685 */            if (b1 == -1);
/*      */ 
/*      */           
/* 1688 */           int i1 = face.getNeighbor(b1);
/* 1689 */           if (i1 == -1) { m = i1; }
/* 1690 */           else { m = paramArrayOfInt[i1]; }
/* 1691 */            if (m != -1 && ((Istream)paramArrayList.get(m)).fan != istream.fan)
/*      */           {
/* 1693 */             m = -1;
/*      */           }
/*      */ 
/*      */           
/* 1697 */           vertex = arrayOfVertex1[k - 2];
/* 1698 */           b1 = -1;
/* 1699 */           if ((arrayOfVertex2[0]).index == vertex.index) { b1 = 0; }
/* 1700 */           else if ((arrayOfVertex2[1]).index == vertex.index) { b1 = 1; }
/* 1701 */           else if ((arrayOfVertex2[2]).index == vertex.index) { b1 = 2; }
/* 1702 */            if (b1 == -1);
/*      */ 
/*      */           
/* 1705 */           int i2 = face.getNeighbor(b1);
/* 1706 */           if (i2 == -1) { n = i2; }
/* 1707 */           else { n = paramArrayOfInt[i2]; }
/* 1708 */            if (n != -1 && ((Istream)paramArrayList.get(n)).fan != istream.fan)
/*      */           {
/* 1710 */             n = -1;
/*      */           }
/*      */ 
/*      */           
/* 1714 */           boolean bool1 = false;
/* 1715 */           if (m != -1 && m != j) {
/* 1716 */             Istream istream1 = (Istream)paramArrayList.get(m);
/* 1717 */             int i3 = istream1.length;
/* 1718 */             if (i1 != istream1.head) {
/* 1719 */               istream1.invert();
/* 1720 */               if (i3 % 2 != 0) bool = true; 
/*      */             } 
/* 1722 */             Vertex[] arrayOfVertex = istream1.istream;
/*      */             
/* 1724 */             if (i3 == 4 && (((arrayOfVertex1[k - 2]).index == (arrayOfVertex[2]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[0]).index) || ((arrayOfVertex1[k - 2]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[2]).index))) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1729 */               vertex = arrayOfVertex[1];
/* 1730 */               arrayOfVertex[1] = arrayOfVertex[2];
/* 1731 */               arrayOfVertex[2] = vertex;
/*      */             } 
/*      */ 
/*      */             
/* 1735 */             if ((arrayOfVertex1[k - 2]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[1]).index) {
/*      */ 
/*      */               
/* 1738 */               istream.addStream(istream1);
/* 1739 */               paramArrayOfInt[b] = -1;
/* 1740 */               paramArrayOfInt[istream.tail] = j;
/* 1741 */               paramArrayOfInt[i1] = -1;
/* 1742 */               bool1 = true;
/*      */             
/*      */             }
/* 1745 */             else if ((arrayOfVertex1[k - 2]).index == (arrayOfVertex[1]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[0]).index) {
/*      */ 
/*      */               
/* 1748 */               istream.append(arrayOfVertex[1]);
/* 1749 */               istream.addStream(istream1);
/* 1750 */               paramArrayOfInt[b] = -1;
/* 1751 */               paramArrayOfInt[istream.tail] = j;
/* 1752 */               paramArrayOfInt[i1] = -1;
/* 1753 */               bool1 = true;
/*      */             
/*      */             }
/* 1756 */             else if ((arrayOfVertex1[k - 2]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[2]).index) {
/*      */ 
/*      */               
/* 1759 */               arrayOfVertex[0] = arrayOfVertex[2];
/* 1760 */               istream.append(arrayOfVertex[1]);
/* 1761 */               istream.addStream(istream1);
/* 1762 */               paramArrayOfInt[b] = -1;
/* 1763 */               paramArrayOfInt[istream.tail] = j;
/* 1764 */               paramArrayOfInt[i1] = -1;
/* 1765 */               bool1 = true;
/*      */             }
/* 1767 */             else if (bool) {
/* 1768 */               istream1.invert();
/* 1769 */               bool = false;
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 1774 */           if (!bool1 && n != -1 && n != j) {
/*      */             
/* 1776 */             Istream istream1 = (Istream)paramArrayList.get(n);
/* 1777 */             int i3 = istream1.length;
/* 1778 */             if (i2 != istream1.head) {
/* 1779 */               istream1.invert();
/* 1780 */               if (i3 % 2 != 0) bool = true; 
/*      */             } 
/* 1782 */             Vertex[] arrayOfVertex = istream1.istream;
/*      */             
/* 1784 */             if (i3 == 4 && (arrayOfVertex1[k - 3]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[2]).index) {
/*      */ 
/*      */               
/* 1787 */               vertex = arrayOfVertex[1];
/* 1788 */               arrayOfVertex[1] = arrayOfVertex[2];
/* 1789 */               arrayOfVertex[2] = vertex;
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 1794 */             if ((arrayOfVertex1[k - 3]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[1]).index) {
/*      */ 
/*      */               
/* 1797 */               istream.swapEnd();
/* 1798 */               istream.addStream(istream1);
/* 1799 */               paramArrayOfInt[b] = -1;
/* 1800 */               paramArrayOfInt[istream.tail] = j;
/* 1801 */               paramArrayOfInt[i2] = -1;
/* 1802 */               bool1 = true;
/*      */             } 
/* 1804 */             if (!bool1 && bool) istream1.invert();
/*      */           
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void reduceCostByZero(ArrayList paramArrayList, Face[] paramArrayOfFace, int[] paramArrayOfInt) {
/* 1817 */     int i = paramArrayOfFace.length;
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
/* 1835 */     for (byte b = 0; b < i; b++) {
/* 1836 */       int j = paramArrayOfInt[b];
/* 1837 */       if (j != -1 && !((Istream)paramArrayList.get(j)).fan) {
/* 1838 */         boolean bool = false;
/* 1839 */         Istream istream = (Istream)paramArrayList.get(j);
/* 1840 */         Vertex[] arrayOfVertex1 = istream.istream;
/* 1841 */         int k = istream.length;
/* 1842 */         Face face = paramArrayOfFace[b];
/* 1843 */         Vertex[] arrayOfVertex2 = face.verts;
/*      */         
/* 1845 */         if (k == 3) {
/* 1846 */           for (byte b1 = 0; b1 < 3; b1++) {
/* 1847 */             int n = face.getNeighbor(b1); int m;
/* 1848 */             if (n != -1 && (m = paramArrayOfInt[n]) != -1 && m != j && !((Istream)paramArrayList.get(m)).fan) {
/*      */ 
/*      */ 
/*      */               
/* 1852 */               arrayOfVertex1[0] = arrayOfVertex2[b1];
/* 1853 */               arrayOfVertex1[1] = arrayOfVertex2[(b1 + 1) % 3];
/* 1854 */               arrayOfVertex1[2] = arrayOfVertex2[(b1 + 2) % 3];
/*      */ 
/*      */               
/* 1857 */               Istream istream1 = (Istream)paramArrayList.get(m);
/* 1858 */               int i1 = istream1.length;
/* 1859 */               if (n != istream1.head) {
/* 1860 */                 istream1.invert();
/* 1861 */                 if (i1 % 2 != 0) bool = true; 
/*      */               } 
/* 1863 */               Vertex[] arrayOfVertex = istream1.istream;
/*      */ 
/*      */               
/* 1866 */               if ((arrayOfVertex1[1]).index == (arrayOfVertex[2]).index && (arrayOfVertex1[2]).index == (arrayOfVertex[0]).index) {
/*      */ 
/*      */                 
/* 1869 */                 arrayOfVertex[0] = arrayOfVertex[2];
/* 1870 */                 istream.append(arrayOfVertex[0]);
/* 1871 */                 istream.append(arrayOfVertex[1]);
/* 1872 */                 istream.addStream(istream1);
/* 1873 */                 paramArrayOfInt[n] = -1;
/* 1874 */                 paramArrayOfInt[istream.tail] = j;
/* 1875 */                 b--;
/*      */                 break;
/*      */               } 
/* 1878 */               if (bool) {
/* 1879 */                 istream1.invert();
/* 1880 */                 bool = false;
/*      */               }
/*      */             
/*      */             } 
/*      */           } 
/* 1885 */         } else if (b == istream.tail || k % 2 == 0) {
/* 1886 */           int n, m; if (b != istream.tail) {
/* 1887 */             istream.invert();
/* 1888 */             arrayOfVertex1 = istream.istream;
/*      */           } 
/*      */           
/* 1891 */           Vertex vertex = arrayOfVertex1[k - 3];
/*      */ 
/*      */           
/* 1894 */           byte b1 = -1;
/* 1895 */           if ((arrayOfVertex2[0]).index == vertex.index) { b1 = 0; }
/* 1896 */           else if ((arrayOfVertex2[1]).index == vertex.index) { b1 = 1; }
/* 1897 */           else if ((arrayOfVertex2[2]).index == vertex.index) { b1 = 2; }
/* 1898 */            if (b1 == -1);
/*      */ 
/*      */           
/* 1901 */           int i1 = face.getNeighbor(b1);
/* 1902 */           if (i1 == -1) { m = i1; }
/* 1903 */           else { m = paramArrayOfInt[i1]; }
/* 1904 */            if (m != -1 && ((Istream)paramArrayList.get(m)).fan != istream.fan)
/*      */           {
/* 1906 */             m = -1;
/*      */           }
/*      */ 
/*      */           
/* 1910 */           vertex = arrayOfVertex1[k - 2];
/* 1911 */           b1 = -1;
/* 1912 */           if ((arrayOfVertex2[0]).index == vertex.index) { b1 = 0; }
/* 1913 */           else if ((arrayOfVertex2[1]).index == vertex.index) { b1 = 1; }
/* 1914 */           else if ((arrayOfVertex2[2]).index == vertex.index) { b1 = 2; }
/* 1915 */            if (b1 == -1);
/*      */ 
/*      */           
/* 1918 */           int i2 = face.getNeighbor(b1);
/* 1919 */           if (i2 == -1) { n = i2; }
/* 1920 */           else { n = paramArrayOfInt[i2]; }
/* 1921 */            if (n != -1 && ((Istream)paramArrayList.get(n)).fan != istream.fan)
/*      */           {
/* 1923 */             n = -1;
/*      */           }
/*      */ 
/*      */           
/* 1927 */           boolean bool1 = false;
/* 1928 */           if (m != -1 && m != j) {
/* 1929 */             Istream istream1 = (Istream)paramArrayList.get(m);
/* 1930 */             int i3 = istream1.length;
/* 1931 */             if (i1 != istream1.head) {
/* 1932 */               istream1.invert();
/* 1933 */               if (i3 % 2 != 0) bool = true; 
/*      */             } 
/* 1935 */             Vertex[] arrayOfVertex = istream1.istream;
/*      */ 
/*      */             
/* 1938 */             if ((arrayOfVertex1[k - 2]).index == (arrayOfVertex[2]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[0]).index) {
/*      */ 
/*      */               
/* 1941 */               arrayOfVertex[0] = arrayOfVertex[2];
/* 1942 */               istream.append(arrayOfVertex[0]);
/* 1943 */               istream.append(arrayOfVertex[1]);
/* 1944 */               istream.addStream(istream1);
/* 1945 */               paramArrayOfInt[b] = -1;
/* 1946 */               paramArrayOfInt[istream.tail] = j;
/* 1947 */               paramArrayOfInt[i1] = -1;
/* 1948 */               bool1 = true;
/*      */             }
/* 1950 */             else if (bool) {
/* 1951 */               istream1.invert();
/* 1952 */               bool = false;
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 1957 */           if (!bool1 && n != -1 && n != j) {
/* 1958 */             Istream istream1 = (Istream)paramArrayList.get(n);
/* 1959 */             int i3 = istream1.length;
/* 1960 */             if (i2 != istream1.head) {
/* 1961 */               istream1.invert();
/* 1962 */               if (i3 % 2 != 0) bool = true; 
/*      */             } 
/* 1964 */             Vertex[] arrayOfVertex = istream1.istream;
/*      */             
/* 1966 */             if (i3 == 4 && (((arrayOfVertex1[k - 3]).index == (arrayOfVertex[2]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[0]).index) || ((arrayOfVertex1[k - 3]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[2]).index))) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1972 */               vertex = arrayOfVertex[1];
/* 1973 */               arrayOfVertex[1] = arrayOfVertex[2];
/* 1974 */               arrayOfVertex[2] = vertex;
/*      */             } 
/*      */ 
/*      */             
/* 1978 */             if ((arrayOfVertex1[k - 3]).index == (arrayOfVertex[1]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[0]).index)
/*      */             
/*      */             { 
/* 1981 */               istream.swapEnd();
/* 1982 */               istream.append(arrayOfVertex[1]);
/* 1983 */               istream.addStream(istream1);
/* 1984 */               paramArrayOfInt[b] = -1;
/* 1985 */               paramArrayOfInt[istream.tail] = j;
/* 1986 */               paramArrayOfInt[i2] = -1; }
/*      */             
/* 1988 */             else if ((arrayOfVertex1[k - 3]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[2]).index)
/*      */             
/*      */             { 
/* 1991 */               arrayOfVertex[0] = arrayOfVertex[2];
/* 1992 */               istream.swapEnd();
/* 1993 */               istream.append(arrayOfVertex[1]);
/* 1994 */               istream.addStream(istream1);
/* 1995 */               paramArrayOfInt[b] = -1;
/* 1996 */               paramArrayOfInt[istream.tail] = j;
/* 1997 */               paramArrayOfInt[i2] = -1; }
/*      */             
/* 1999 */             else if ((arrayOfVertex1[k - 3]).index == (arrayOfVertex[0]).index && (arrayOfVertex1[k - 1]).index == (arrayOfVertex[1]).index)
/*      */             
/*      */             { 
/* 2002 */               istream.swapEnd();
/* 2003 */               istream.addStream(istream1);
/* 2004 */               paramArrayOfInt[b] = -1;
/* 2005 */               paramArrayOfInt[istream.tail] = j;
/* 2006 */               paramArrayOfInt[i2] = -1; }
/*      */             
/* 2008 */             else if (bool) { istream1.invert(); }
/*      */           
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void putBackData(GeometryInfo paramGeometryInfo, ArrayList paramArrayList) {
/* 2019 */     int[] arrayOfInt = new int[paramArrayList.size()];
/* 2020 */     int i = 0;
/*      */     
/* 2022 */     for (byte b = 0; b < paramArrayList.size(); ) {
/* 2023 */       int j = ((Istream)paramArrayList.get(b)).length;
/* 2024 */       if (j != 0) {
/* 2025 */         arrayOfInt[b] = j;
/* 2026 */         i += j;
/* 2027 */         b++;
/*      */         continue;
/*      */       } 
/* 2030 */       paramArrayList.remove(b);
/*      */     } 
/*      */     
/* 2033 */     if (i > 3) {
/* 2034 */       paramGeometryInfo; paramGeometryInfo.setPrimitive(4);
/* 2035 */       int[] arrayOfInt1 = new int[paramArrayList.size()];
/* 2036 */       System.arraycopy(arrayOfInt, 0, arrayOfInt1, 0, paramArrayList.size());
/* 2037 */       paramGeometryInfo.setStripCounts(arrayOfInt1);
/*      */ 
/*      */       
/* 2040 */       int[] arrayOfInt2 = new int[i];
/*      */ 
/*      */       
/* 2043 */       int[] arrayOfInt3 = null;
/* 2044 */       int[][] arrayOfInt4 = (int[][])null;
/* 2045 */       int[] arrayOfInt5 = null;
/* 2046 */       Color3b[] arrayOfColor3b = null;
/* 2047 */       if (this.hasNormals) arrayOfInt3 = new int[i]; 
/* 2048 */       if (this.hasTextures) {
/* 2049 */         arrayOfInt4 = new int[this.texSetCount][i];
/*      */       }
/* 2051 */       if (this.hasColors) arrayOfInt5 = new int[i]; 
/* 2052 */       if (this.colorStrips) {
/* 2053 */         arrayOfColor3b = new Color3b[i];
/* 2054 */         arrayOfInt5 = new int[i];
/*      */       } 
/* 2056 */       byte b1 = 0;
/*      */       byte b2;
/* 2058 */       for (b2 = 0; b2 < paramArrayList.size(); b2++) {
/* 2059 */         Istream istream = (Istream)paramArrayList.get(b2);
/*      */         
/* 2061 */         if (istream.length < 3) {
/* 2062 */           throw new RuntimeException("currStrip.length = " + istream.length);
/*      */         }
/*      */ 
/*      */         
/* 2066 */         Color color = null;
/* 2067 */         if (this.colorStrips) {
/* 2068 */           int j = (int)(Math.random() * 1000.0D) % 255;
/* 2069 */           int k = (int)(Math.random() * 1000.0D) % 255;
/* 2070 */           int m = (int)(Math.random() * 1000.0D) % 255;
/* 2071 */           color = new Color(j, k, m);
/*      */         } 
/*      */         
/* 2074 */         for (byte b3 = 0; b3 < istream.length; b3++) {
/* 2075 */           arrayOfInt2[b1] = (istream.istream[b3]).index;
/* 2076 */           if (this.hasNormals) arrayOfInt3[b1] = (istream.istream[b3]).normal; 
/* 2077 */           if (this.hasTextures) {
/* 2078 */             for (byte b4 = 0; b4 < this.texSetCount; b4++) {
/* 2079 */               arrayOfInt4[b4][b1] = (istream.istream[b3]).texture[b4];
/*      */             }
/*      */           }
/*      */           
/* 2083 */           if (this.hasColors) arrayOfInt5[b1] = (istream.istream[b3]).color; 
/* 2084 */           if (this.colorStrips) arrayOfColor3b[b1] = new Color3b(color);
/*      */           
/* 2086 */           b1++;
/*      */         } 
/*      */       } 
/* 2089 */       paramGeometryInfo.setCoordinateIndices(arrayOfInt2);
/* 2090 */       if (this.hasNormals) paramGeometryInfo.setNormalIndices(arrayOfInt3); 
/* 2091 */       if (this.hasTextures) {
/* 2092 */         for (b2 = 0; b2 < this.texSetCount; b2++) {
/* 2093 */           paramGeometryInfo.setTextureCoordinateIndices(b2, arrayOfInt4[b2]);
/*      */         }
/*      */       }
/* 2096 */       if (this.hasColors) paramGeometryInfo.setColorIndices(arrayOfInt5); 
/* 2097 */       if (this.colorStrips) {
/* 2098 */         paramGeometryInfo.setColors(arrayOfColor3b);
/* 2099 */         arrayOfInt5 = paramGeometryInfo.getListIndices(arrayOfColor3b);
/* 2100 */         paramGeometryInfo.setColorIndices(arrayOfInt5);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   class Vertex
/*      */   {
/*      */     int index;
/*      */     
/*      */     int normal;
/*      */     
/*      */     int numTexSets;
/*      */     
/*      */     int[] texture;
/*      */     int color;
/*      */     
/* 2117 */     Vertex(Stripifier this$0, int param1Int) { this(param1Int, -1, 0, null, -1); } Vertex(int param1Int1, int param1Int2, int param1Int3, int[] param1ArrayOfInt, int param1Int4) {
/*      */       this.normal = -1;
/*      */       this.numTexSets = 0;
/*      */       this.texture = null;
/*      */       this.color = -1;
/* 2122 */       this.index = param1Int1;
/* 2123 */       this.normal = param1Int2;
/* 2124 */       this.numTexSets = param1Int3;
/* 2125 */       if (this.numTexSets > 0) {
/* 2126 */         this.texture = new int[this.numTexSets];
/* 2127 */         System.arraycopy(param1ArrayOfInt, 0, this.texture, 0, this.numTexSets);
/*      */       } 
/* 2129 */       this.color = param1Int4;
/*      */     }
/*      */     
/*      */     boolean equals(Vertex param1Vertex) {
/* 2133 */       for (byte b = 0; b < this.numTexSets; b++) {
/* 2134 */         if (this.texture[b] != param1Vertex.texture[b]) {
/* 2135 */           return false;
/*      */         }
/*      */       } 
/* 2138 */       return (param1Vertex.index == this.index && param1Vertex.normal == this.normal && param1Vertex.color == this.color);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean lessThan(Vertex param1Vertex) {
/* 2145 */       if (this.index < param1Vertex.index) return true; 
/* 2146 */       if (this.index > param1Vertex.index) return false; 
/* 2147 */       if (this.normal < param1Vertex.normal) return true; 
/* 2148 */       if (this.normal > param1Vertex.normal) return false; 
/* 2149 */       for (byte b = 0; b < this.numTexSets; b++) {
/* 2150 */         if (this.texture[b] < param1Vertex.texture[b]) return true; 
/* 2151 */         if (this.texture[b] > param1Vertex.texture[b]) return false; 
/*      */       } 
/* 2153 */       if (this.color < param1Vertex.color) return true; 
/* 2154 */       if (this.color > param1Vertex.color) return false; 
/* 2155 */       return false;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   class Edge
/*      */   {
/*      */     Stripifier.Vertex v1;
/*      */     
/*      */     Stripifier.Vertex v2;
/*      */     int face;
/*      */     
/*      */     Edge(Stripifier.Vertex param1Vertex1, Stripifier.Vertex param1Vertex2, int param1Int) {
/* 2168 */       this.face = param1Int;
/*      */ 
/*      */       
/* 2171 */       if (param1Vertex1.lessThan(param1Vertex2)) {
/* 2172 */         this.v1 = param1Vertex1;
/* 2173 */         this.v2 = param1Vertex2;
/*      */       } else {
/* 2175 */         this.v1 = param1Vertex2;
/* 2176 */         this.v2 = param1Vertex1;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2184 */     boolean equals(Edge param1Edge) { return (this.v1.equals(param1Edge.v1) && this.v2.equals(param1Edge.v2)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean lessThan(Edge param1Edge) {
/* 2195 */       if (this.v1.lessThan(param1Edge.v1)) return true; 
/* 2196 */       if (this.v1.equals(param1Edge.v1)) return this.v2.lessThan(param1Edge.v2); 
/* 2197 */       return false;
/*      */     } }
/*      */   
/*      */   class Face { int key;
/*      */     int numNhbrs;
/*      */     Stripifier.Vertex[] verts;
/*      */     Stripifier.Edge[] edges;
/*      */     
/*      */     Face(int param1Int, Stripifier.Vertex param1Vertex1, Stripifier.Vertex param1Vertex2, Stripifier.Vertex param1Vertex3) {
/* 2206 */       this.numNhbrs = 0;
/* 2207 */       this.verts = null;
/*      */ 
/*      */       
/* 2210 */       this.edges = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2216 */       this.key = param1Int;
/*      */       
/* 2218 */       this.verts = new Stripifier.Vertex[3];
/* 2219 */       this.verts[0] = param1Vertex1;
/* 2220 */       this.verts[1] = param1Vertex2;
/* 2221 */       this.verts[2] = param1Vertex3;
/*      */       
/* 2223 */       this.edges = new Stripifier.Edge[3];
/* 2224 */       this.edges[0] = null;
/* 2225 */       this.edges[1] = null;
/* 2226 */       this.edges[2] = null;
/* 2227 */       this.numNhbrs = 3;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2235 */     int getNeighbor(int param1Int) { return (this.edges[param1Int]).face; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int findSharedEdge(int param1Int) {
/* 2243 */       if ((this.edges[0]).face == param1Int) return 0; 
/* 2244 */       if ((this.edges[1]).face == param1Int) return 1; 
/* 2245 */       if ((this.edges[2]).face == param1Int) return 2; 
/* 2246 */       return -1;
/*      */     }
/*      */     
/*      */     int getEdgeIndex(Stripifier.Edge param1Edge) {
/* 2250 */       if (this.edges[0].equals(param1Edge)) return 0; 
/* 2251 */       if (this.edges[1].equals(param1Edge)) return 1; 
/* 2252 */       return 2;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void counterEdgeDel(Stripifier.Edge param1Edge) {
/* 2259 */       if (this.edges[0].equals(param1Edge)) {
/* 2260 */         (this.edges[0]).face = -1;
/* 2261 */         this.numNhbrs--;
/*      */       }
/* 2263 */       else if (this.edges[1].equals(param1Edge)) {
/* 2264 */         (this.edges[1]).face = -1;
/* 2265 */         this.numNhbrs--;
/*      */       }
/* 2267 */       else if (this.edges[2].equals(param1Edge)) {
/* 2268 */         (this.edges[2]).face = -1;
/* 2269 */         this.numNhbrs--;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void printAdjacency() {
/* 2279 */       System.out.println("Face " + this.key + ": ");
/* 2280 */       System.out.println("\t numNhbrs = " + this.numNhbrs);
/* 2281 */       System.out.println("\t edge 0: Face " + (this.edges[0]).face);
/* 2282 */       System.out.println("\t edge 1: Face " + (this.edges[1]).face);
/* 2283 */       System.out.println("\t edge 2: Face " + (this.edges[2]).face);
/*      */     }
/*      */ 
/*      */     
/* 2287 */     void printVertices() { System.out.println("Face " + this.key + ": (" + (this.verts[0]).index + ", " + (this.verts[1]).index + ", " + (this.verts[2]).index + ")"); } }
/*      */ 
/*      */ 
/*      */   
/*      */   class Node
/*      */   {
/*      */     Stripifier.Face face;
/*      */     
/*      */     Node parent;
/*      */     
/*      */     Node left;
/*      */     
/*      */     Node right;
/*      */     
/*      */     int depth;
/*      */     
/*      */     int numChildren;
/*      */     
/*      */     int attrib;
/*      */     static final int WHITE = 0;
/*      */     static final int GREY = 1;
/*      */     static final int BLACK = 2;
/*      */     
/* 2310 */     Node(Stripifier.Face param1Face) { this.face = param1Face; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void insert(Node param1Node) {
/* 2317 */       this.parent = param1Node;
/* 2318 */       param1Node.depth++;
/* 2319 */       this.attrib = 1;
/*      */       
/* 2321 */       if (this.parent.left == null) { this.parent.left = this; }
/* 2322 */       else { this.parent.right = this; }
/* 2323 */        this.parent.numChildren++;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void remove() {
/* 2330 */       if (this.parent != null) {
/* 2331 */         if (this.parent.left == this) {
/* 2332 */           this.parent.left = this.parent.right;
/* 2333 */           this.parent.right = null;
/*      */         } else {
/*      */           
/* 2336 */           this.parent.right = null;
/*      */         } 
/* 2338 */         this.parent.numChildren--;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setRoot() {
/* 2347 */       this.depth = 0;
/* 2348 */       this.attrib = 1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2355 */     boolean notAccessed() { return (this.attrib == 0); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2362 */     void processed() { this.attrib = 2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2369 */     boolean isRoot() { return (this.parent == null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void print() {
/* 2376 */       System.out.println(this);
/* 2377 */       System.out.println("Node depth: " + this.depth);
/* 2378 */       this.face.printVertices();
/* 2379 */       System.out.print("parent: ");
/* 2380 */       if (this.parent != null) { this.parent.face.printVertices(); }
/* 2381 */       else { System.out.println("null"); }
/* 2382 */        System.out.print("left: ");
/* 2383 */       if (this.left != null) { this.left.face.printVertices(); }
/* 2384 */       else { System.out.println("null"); }
/* 2385 */        System.out.print("right: ");
/* 2386 */       if (this.right != null) { this.right.face.printVertices(); }
/* 2387 */       else { System.out.println("null"); }
/* 2388 */        System.out.println("attrib: " + this.attrib);
/* 2389 */       System.out.println("");
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
/*      */   class SortedList
/*      */   {
/* 2404 */     ArrayList list = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int sortedInsert(Stripifier.Node param1Node, int param1Int) {
/* 2414 */       while (param1Int < this.list.size() && ((Stripifier.Node)this.list.get(param1Int)).depth <= param1Node.depth)
/*      */       {
/* 2416 */         param1Int++;
/*      */       }
/*      */ 
/*      */       
/* 2420 */       this.list.add(param1Int, param1Node);
/*      */ 
/*      */       
/* 2423 */       return param1Int + 1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Stripifier.Node pop() {
/* 2430 */       if (!this.list.isEmpty()) return (Stripifier.Node)this.list.remove(0); 
/* 2431 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   class Istream
/*      */   {
/*      */     boolean fan = false;
/*      */     
/* 2440 */     int length = 0;
/*      */     
/*      */     Stripifier.Vertex[] istream;
/*      */     
/*      */     int head;
/*      */     
/*      */     int tail;
/*      */ 
/*      */     
/*      */     Istream(Stripifier.Vertex[] param1ArrayOfVertex, int param1Int, boolean param1Boolean) {
/* 2450 */       if (param1Int == 0) throw new RuntimeException("size is 0"); 
/* 2451 */       this.fan = param1Boolean;
/* 2452 */       this.length = param1Int;
/* 2453 */       this.istream = new Stripifier.Vertex[this.length];
/*      */       
/* 2455 */       System.arraycopy(param1ArrayOfVertex, 0, this.istream, 0, this.length);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void append(Stripifier.Vertex param1Vertex) {
/* 2463 */       growArray();
/*      */       
/* 2465 */       this.istream[this.length] = param1Vertex;
/* 2466 */       this.length++;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void swapEnd() {
/* 2474 */       growArray();
/* 2475 */       this.istream[this.length] = this.istream[this.length - 1];
/* 2476 */       this.istream[this.length - 1] = this.istream[this.length - 3];
/* 2477 */       this.length++;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void growArray() {
/* 2484 */       if (this.length >= this.istream.length) {
/* 2485 */         Stripifier.Vertex[] arrayOfVertex = this.istream;
/*      */ 
/*      */         
/* 2488 */         this.istream = new Stripifier.Vertex[this.length + 3];
/* 2489 */         System.arraycopy(arrayOfVertex, 0, this.istream, 0, this.length);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void invert() {
/* 2497 */       Stripifier.Vertex[] arrayOfVertex = new Stripifier.Vertex[this.istream.length];
/*      */       int i;
/* 2499 */       for (i = 0; i < this.length; i++) {
/* 2500 */         arrayOfVertex[i] = this.istream[this.length - i - 1];
/*      */       }
/*      */       
/* 2503 */       System.arraycopy(arrayOfVertex, 0, this.istream, 0, this.istream.length);
/* 2504 */       arrayOfVertex = null;
/*      */       
/* 2506 */       i = this.head;
/* 2507 */       this.head = this.tail;
/* 2508 */       this.tail = i;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void addStream(Istream param1Istream) {
/* 2516 */       int i = param1Istream.length;
/* 2517 */       int j = i + this.length - 2;
/*      */ 
/*      */       
/* 2520 */       if (j >= this.istream.length) {
/* 2521 */         Stripifier.Vertex[] arrayOfVertex = this.istream;
/* 2522 */         this.istream = new Stripifier.Vertex[j];
/* 2523 */         System.arraycopy(arrayOfVertex, 0, this.istream, 0, this.length);
/*      */       } 
/*      */ 
/*      */       
/* 2527 */       System.arraycopy(param1Istream.istream, 2, this.istream, this.length, i - 2);
/*      */       
/* 2529 */       this.tail = param1Istream.tail;
/* 2530 */       this.length = j;
/* 2531 */       param1Istream.length = 0;
/* 2532 */       param1Istream.istream = null;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\geometry\Stripifier.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */