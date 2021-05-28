/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Vector;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point4d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PickInfo
/*      */ {
/*      */   static final int PICK_ALL = 1;
/*      */   static final int PICK_ANY = 2;
/*      */   private SceneGraphPath sgp;
/*      */   private Node node;
/*      */   private Transform3D l2vw;
/*      */   private Point3d closestIntersectionPoint;
/*      */   private double closestDistance;
/*      */   private IntersectionInfo[] intersectionInfoArr;
/*   59 */   private ArrayList intersectionInfoList = new ArrayList();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean intersectionInfoListSorted = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Transform3D l2vwRef;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Node nodeRef;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int PICK_BOUNDS = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int PICK_GEOMETRY = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int SCENEGRAPHPATH = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int NODE = 2;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int LOCAL_TO_VWORLD = 4;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CLOSEST_INTERSECTION_POINT = 8;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CLOSEST_DISTANCE = 16;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int CLOSEST_GEOM_INFO = 32;
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int ALL_GEOM_INFO = 64;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  118 */   void setSceneGraphPath(SceneGraphPath paramSceneGraphPath) { this.sgp = paramSceneGraphPath; }
/*      */ 
/*      */ 
/*      */   
/*  122 */   void setNode(Node paramNode) { this.node = paramNode; }
/*      */ 
/*      */ 
/*      */   
/*  126 */   void setLocalToVWorld(Transform3D paramTransform3D) { this.l2vw = paramTransform3D; }
/*      */ 
/*      */ 
/*      */   
/*  130 */   void setClosestIntersectionPoint(Point3d paramPoint3d) { this.closestIntersectionPoint = paramPoint3d; }
/*      */ 
/*      */ 
/*      */   
/*  134 */   void setClosestDistance(double paramDouble) { this.closestDistance = paramDouble; }
/*      */ 
/*      */ 
/*      */   
/*  138 */   void setLocalToVWorldRef(Transform3D paramTransform3D) { this.l2vwRef = paramTransform3D; }
/*      */ 
/*      */ 
/*      */   
/*  142 */   void setNodeRef(Node paramNode) { this.nodeRef = paramNode; }
/*      */ 
/*      */ 
/*      */   
/*  146 */   IntersectionInfo createIntersectionInfo() { return new IntersectionInfo(); }
/*      */ 
/*      */   
/*      */   void insertIntersectionInfo(IntersectionInfo paramIntersectionInfo) {
/*  150 */     this.intersectionInfoList.add(paramIntersectionInfo);
/*  151 */     this.intersectionInfoListSorted = false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void sortIntersectionInfoArray(IntersectionInfo[] paramArrayOfIntersectionInfo) {
/*      */     class Sort
/*      */     {
/*      */       PickInfo.IntersectionInfo[] iInfoArr;
/*      */ 
/*      */       
/*  162 */       Sort(PickInfo.IntersectionInfo[] param1ArrayOfIntersectionInfo) { this.iInfoArr = param1ArrayOfIntersectionInfo; }
/*      */ 
/*      */       
/*      */       void sorting() {
/*  166 */         if (this.iInfoArr.length < 7) {
/*      */           
/*  168 */           insertSort();
/*      */         } else {
/*      */           
/*  171 */           quicksort(0, this.iInfoArr.length - 1);
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*      */       final void insertSort() {
/*  177 */         for (byte b = 0; b < this.iInfoArr.length; b++) {
/*  178 */           for (byte b1 = b; b1 && (this.iInfoArr[b1 - true]).distance > (this.iInfoArr[b1]).distance; 
/*  179 */             b1--) {
/*  180 */             PickInfo.IntersectionInfo intersectionInfo = this.iInfoArr[b1];
/*  181 */             this.iInfoArr[b1] = this.iInfoArr[b1 - true];
/*  182 */             this.iInfoArr[b1 - true] = intersectionInfo;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       final void quicksort(int param1Int1, int param1Int2) {
/*  188 */         int i = param1Int1;
/*  189 */         int j = param1Int2;
/*  190 */         double d = (this.iInfoArr[(param1Int1 + param1Int2) / 2]).distance;
/*      */         
/*      */         do {
/*  193 */           for (; (this.iInfoArr[i]).distance < d; i++);
/*  194 */           for (; d < (this.iInfoArr[j]).distance; j--);
/*  195 */           if (i > j)
/*  196 */             continue;  PickInfo.IntersectionInfo intersectionInfo = this.iInfoArr[i];
/*  197 */           this.iInfoArr[i] = this.iInfoArr[j];
/*  198 */           this.iInfoArr[j] = intersectionInfo;
/*  199 */           i++;
/*  200 */           j--;
/*      */         }
/*  202 */         while (i <= j);
/*      */         
/*  204 */         if (param1Int1 < j) quicksort(param1Int1, j); 
/*  205 */         if (param1Int1 < param1Int2) quicksort(i, param1Int2);
/*      */       
/*      */       }
/*      */     };
/*  209 */     (new Sort(paramArrayOfIntersectionInfo)).sorting();
/*  210 */     this.intersectionInfoListSorted = true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static void sortPickInfoArray(PickInfo[] paramArrayOfPickInfo) {
/*      */     class Sort
/*      */     {
/*      */       PickInfo[] pIArr;
/*      */ 
/*      */       
/*  221 */       Sort(PickInfo this$0) { this.pIArr = this$0; }
/*      */ 
/*      */       
/*      */       void sorting() {
/*  225 */         if (this.pIArr.length < 7) {
/*      */           
/*  227 */           insertSort();
/*      */         } else {
/*      */           
/*  230 */           quicksort(0, this.pIArr.length - 1);
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*      */       final void insertSort() {
/*  236 */         for (byte b = 0; b < this.pIArr.length; b++) {
/*  237 */           for (byte b1 = b; b1 && (this.pIArr[b1 - true]).closestDistance > (this.pIArr[b1]).closestDistance; 
/*  238 */             b1--) {
/*  239 */             PickInfo pickInfo = this.pIArr[b1];
/*  240 */             this.pIArr[b1] = this.pIArr[b1 - true];
/*  241 */             this.pIArr[b1 - true] = pickInfo;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       final void quicksort(int param1Int1, int param1Int2) {
/*  247 */         int i = param1Int1;
/*  248 */         int j = param1Int2;
/*  249 */         double d = (this.pIArr[(param1Int1 + param1Int2) / 2]).closestDistance;
/*      */         
/*      */         do {
/*  252 */           for (; (this.pIArr[i]).closestDistance < d; i++);
/*  253 */           for (; d < (this.pIArr[j]).closestDistance; j--);
/*  254 */           if (i > j)
/*  255 */             continue;  PickInfo pickInfo = this.pIArr[i];
/*  256 */           this.pIArr[i] = this.pIArr[j];
/*  257 */           this.pIArr[j] = pickInfo;
/*  258 */           i++;
/*  259 */           j--;
/*      */         }
/*  261 */         while (i <= j);
/*      */         
/*  263 */         if (param1Int1 < j) quicksort(param1Int1, j); 
/*  264 */         if (param1Int1 < param1Int2) quicksort(i, param1Int2);
/*      */       
/*      */       }
/*      */     };
/*  268 */     (new Sort(paramArrayOfPickInfo)).sorting();
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
/*  280 */   public SceneGraphPath getSceneGraphPath() { return this.sgp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  290 */   public Node getNode() { return this.node; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  300 */   public Transform3D getLocalToVWorld() { return this.l2vw; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  310 */   public Point3d getClosestIntersectionPoint() { return this.closestIntersectionPoint; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  322 */   public double getClosestDistance() { return this.closestDistance; }
/*      */ 
/*      */ 
/*      */   
/*  326 */   Transform3D getLocalToVWorldRef() { return this.l2vwRef; }
/*      */ 
/*      */ 
/*      */   
/*  330 */   Node getNodeRef() { return this.nodeRef; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IntersectionInfo[] getIntersectionInfos() {
/*  343 */     if (!this.intersectionInfoListSorted) {
/*  344 */       this.intersectionInfoArr = new IntersectionInfo[this.intersectionInfoList.size()];
/*  345 */       this.intersectionInfoArr = (IntersectionInfo[])this.intersectionInfoList.toArray(this.intersectionInfoArr);
/*      */ 
/*      */       
/*  348 */       sortIntersectionInfoArray(this.intersectionInfoArr);
/*      */     } 
/*      */     
/*  351 */     return this.intersectionInfoArr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static ArrayList initSceneGraphPath(NodeRetained paramNodeRetained) {
/*  360 */     ArrayList arrayList = new ArrayList(5);
/*      */     
/*      */     do {
/*  363 */       if (paramNodeRetained.source.getCapability(1)) {
/*  364 */         arrayList.add(paramNodeRetained);
/*      */       }
/*  366 */       paramNodeRetained = paramNodeRetained.parent;
/*  367 */     } while (paramNodeRetained != null);
/*      */     
/*  369 */     return arrayList;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Node[] createPath(NodeRetained paramNodeRetained, BranchGroupRetained paramBranchGroupRetained, GeometryAtom paramGeometryAtom, ArrayList paramArrayList) {
/*  377 */     ArrayList arrayList = retrievePath(paramNodeRetained, paramBranchGroupRetained, paramGeometryAtom.source.key);
/*      */     
/*  379 */     assert arrayList != null;
/*      */     
/*  381 */     return mergePath(arrayList, paramArrayList);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean inside(BranchGroupRetained[] paramArrayOfBranchGroupRetained, BranchGroupRetained paramBranchGroupRetained) {
/*  392 */     if (paramBranchGroupRetained == null || paramArrayOfBranchGroupRetained == null) {
/*  393 */       return true;
/*      */     }
/*      */     
/*  396 */     for (byte b = 0; b < paramArrayOfBranchGroupRetained.length; b++) {
/*  397 */       if (paramArrayOfBranchGroupRetained[b] == paramBranchGroupRetained) {
/*  398 */         return true;
/*      */       }
/*      */     } 
/*  401 */     return false;
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
/*      */   private static ArrayList retrievePath(NodeRetained paramNodeRetained1, NodeRetained paramNodeRetained2, HashKey paramHashKey) {
/*  415 */     ArrayList arrayList = new ArrayList(5);
/*  416 */     NodeRetained nodeRetained = paramNodeRetained1;
/*      */     
/*  418 */     if (nodeRetained.inSharedGroup)
/*      */     {
/*  420 */       paramHashKey = new HashKey(paramHashKey);
/*      */     }
/*      */     
/*      */     do {
/*  424 */       if (nodeRetained == paramNodeRetained2) {
/*  425 */         return arrayList;
/*      */       }
/*      */       
/*  428 */       if (nodeRetained.source.getCapability(1)) {
/*  429 */         arrayList.add(nodeRetained);
/*      */       }
/*      */       
/*  432 */       if (nodeRetained instanceof SharedGroupRetained) {
/*      */         
/*  434 */         String str = paramHashKey.getLastNodeId();
/*  435 */         Vector vector = ((SharedGroupRetained)nodeRetained).parents;
/*  436 */         int i = vector.size();
/*  437 */         NodeRetained nodeRetained1 = nodeRetained;
/*  438 */         for (byte b = 0; b < i; b++) {
/*  439 */           NodeRetained nodeRetained2 = (NodeRetained)vector.elementAt(b);
/*  440 */           if (nodeRetained2.nodeId.equals(str)) {
/*  441 */             nodeRetained = nodeRetained2;
/*      */             
/*  443 */             arrayList.add(nodeRetained);
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */         
/*  450 */         if (nodeRetained == nodeRetained1)
/*      */         {
/*  452 */           return null;
/*      */         }
/*      */       } 
/*  455 */       nodeRetained = nodeRetained.parent;
/*  456 */     } while (nodeRetained != null);
/*      */     
/*  458 */     if (paramNodeRetained2 == null)
/*      */     {
/*  460 */       return arrayList;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  466 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Node[] mergePath(ArrayList paramArrayList1, ArrayList paramArrayList2) {
/*  474 */     int j, i = paramArrayList1.size();
/*      */ 
/*      */ 
/*      */     
/*  478 */     if (paramArrayList2 == null) {
/*  479 */       j = i;
/*      */     } else {
/*  481 */       j = i + paramArrayList2.size();
/*      */     } 
/*      */     
/*  484 */     Node[] arrayOfNode = new Node[j];
/*  485 */     int m = j - 1; int k;
/*  486 */     for (k = 0; k < i; k++) {
/*  487 */       arrayOfNode[m - k] = (Node)((NodeRetained)paramArrayList1.get(k)).source;
/*      */     }
/*  489 */     for (byte b = 0; k < j; k++, b++) {
/*  490 */       arrayOfNode[m - k] = (Node)((NodeRetained)paramArrayList2.get(b)).source;
/*      */     }
/*  492 */     return arrayOfNode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static void sortGeomAtoms(GeometryAtom[] paramArrayOfGeometryAtom, PickShape paramPickShape) {
/*  502 */     final double[] distance = new double[paramArrayOfGeometryAtom.length];
/*  503 */     Point4d point4d = new Point4d();
/*      */     
/*  505 */     for (byte b = 0; b < paramArrayOfGeometryAtom.length; b++) {
/*  506 */       paramPickShape.intersect((paramArrayOfGeometryAtom[b]).source.vwcBounds, point4d);
/*  507 */       arrayOfDouble[b] = point4d.w;
/*      */     } 
/*      */     class Sort
/*      */     {
/*      */       GeometryAtom[] atoms;
/*      */ 
/*      */ 
/*      */       
/*  515 */       Sort(PickInfo this$0) { this.atoms = this$0; }
/*      */ 
/*      */       
/*      */       void sorting() {
/*  519 */         if (this.atoms.length < 7) {
/*  520 */           insertSort();
/*      */         } else {
/*  522 */           quicksort(0, this.atoms.length - 1);
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*      */       final void insertSort() {
/*  528 */         for (byte b = 0; b < this.atoms.length; b++) {
/*  529 */           for (byte b1 = b; b1 && distance[b1 - true] > distance[b1]; 
/*  530 */             b1--) {
/*  531 */             double d = distance[b1];
/*  532 */             distance[b1] = distance[b1 - true];
/*  533 */             distance[b1 - true] = d;
/*  534 */             GeometryAtom geometryAtom = this.atoms[b1];
/*  535 */             this.atoms[b1] = this.atoms[b1 - true];
/*  536 */             this.atoms[b1 - true] = geometryAtom;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       final void quicksort(int param1Int1, int param1Int2) {
/*  542 */         int i = param1Int1;
/*  543 */         int j = param1Int2;
/*  544 */         double d = distance[(param1Int1 + param1Int2) / 2];
/*      */         
/*      */         do {
/*  547 */           for (; distance[i] < d; i++);
/*  548 */           for (; d < distance[j]; j--);
/*  549 */           if (i > j)
/*  550 */             continue;  double d1 = distance[i];
/*  551 */           distance[i] = distance[j];
/*  552 */           distance[j] = d1;
/*      */           
/*  554 */           GeometryAtom geometryAtom = this.atoms[i];
/*  555 */           this.atoms[i] = this.atoms[j];
/*  556 */           this.atoms[j] = geometryAtom;
/*  557 */           i++;
/*  558 */           j--;
/*      */         }
/*  560 */         while (i <= j);
/*      */         
/*  562 */         if (param1Int1 < j) quicksort(param1Int1, j); 
/*  563 */         if (param1Int1 < param1Int2) quicksort(i, param1Int2);
/*      */       
/*      */       }
/*      */     };
/*  567 */     (new Sort(paramArrayOfGeometryAtom)).sorting();
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
/*      */   static ArrayList getPickInfos(ArrayList paramArrayList, BranchGroupRetained paramBranchGroupRetained, GeometryAtom[] paramArrayOfGeometryAtom, Locale paramLocale, int paramInt1, int paramInt2) {
/*  584 */     ArrayList arrayList1 = new ArrayList(5);
/*      */     
/*  586 */     ArrayList arrayList2 = null;
/*      */     
/*  588 */     if (paramArrayOfGeometryAtom == null || paramArrayOfGeometryAtom.length == 0) {
/*  589 */       return null;
/*      */     }
/*      */     
/*  592 */     for (byte b = 0; b < paramArrayOfGeometryAtom.length; b++) {
/*  593 */       assert paramArrayOfGeometryAtom[b] != null && (paramArrayOfGeometryAtom[b]).source != null;
/*      */ 
/*      */       
/*  596 */       PickInfo pickInfo = null;
/*  597 */       Shape3DRetained shape3DRetained = (paramArrayOfGeometryAtom[b]).source;
/*  598 */       NodeRetained nodeRetained = shape3DRetained.sourceNode;
/*      */ 
/*      */ 
/*      */       
/*  602 */       if (!inside(shape3DRetained.branchGroupPath, paramBranchGroupRetained)) {
/*      */         continue;
/*      */       }
/*      */       
/*  606 */       if (nodeRetained == null) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  616 */       if (nodeRetained instanceof Shape3DRetained) {
/*  617 */         Shape3DRetained shape3DRetained1 = (Shape3DRetained)nodeRetained;
/*  618 */         GeometryRetained geometryRetained = null; int i;
/*  619 */         for (i = 0; i < shape3DRetained1.geometryList.size(); i++) {
/*  620 */           geometryRetained = (GeometryRetained)shape3DRetained1.geometryList.get(i);
/*  621 */           if (geometryRetained != null) {
/*      */             break;
/*      */           }
/*      */         } 
/*  625 */         if (geometryRetained == null) {
/*      */           continue;
/*      */         }
/*  628 */         if (geometryRetained instanceof Text3DRetained) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  634 */           if (arrayList2 == null) {
/*  635 */             arrayList2 = new ArrayList(3);
/*      */           } else {
/*  637 */             i = arrayList2.size();
/*  638 */             boolean bool = false;
/*  639 */             for (byte b1 = 0; b1 < i; b1++) {
/*  640 */               if (arrayList2.get(b1) == nodeRetained) {
/*  641 */                 bool = true;
/*      */                 break;
/*      */               } 
/*      */             } 
/*  645 */             if (bool) {
/*      */               continue;
/*      */             }
/*      */           } 
/*  649 */           arrayList2.add(nodeRetained);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  655 */       if (nodeRetained instanceof Shape3DCompileRetained) {
/*      */         
/*  657 */         Shape3DCompileRetained shape3DCompileRetained = (Shape3DCompileRetained)nodeRetained;
/*      */         
/*  659 */         Node[] arrayOfNode = null;
/*  660 */         boolean bool = true;
/*      */         
/*  662 */         for (byte b1 = 0; b1 < shape3DCompileRetained.srcList.length; b1++) {
/*      */           
/*  664 */           pickInfo = null;
/*      */ 
/*      */           
/*  667 */           if ((paramInt1 & true) != 0) {
/*      */             
/*  669 */             if (bool) {
/*  670 */               arrayOfNode = createPath(nodeRetained, paramBranchGroupRetained, paramArrayOfGeometryAtom[b], paramArrayList);
/*  671 */               bool = false;
/*      */             } 
/*      */             
/*  674 */             if (arrayOfNode != null) {
/*  675 */               SceneGraphPath sceneGraphPath = new SceneGraphPath(paramLocale, arrayOfNode, (Node)shape3DCompileRetained.srcList[b1]);
/*      */               
/*  677 */               sceneGraphPath.setTransform(shape3DRetained.getCurrentLocalToVworld(0));
/*  678 */               if (pickInfo == null)
/*  679 */                 pickInfo = new PickInfo(); 
/*  680 */               pickInfo.setSceneGraphPath(sceneGraphPath);
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/*  685 */           if ((paramInt1 & 0x2) != 0) {
/*  686 */             if (pickInfo == null)
/*  687 */               pickInfo = new PickInfo(); 
/*  688 */             pickInfo.setNode((Node)shape3DCompileRetained.srcList[b1]);
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  693 */           if ((paramInt1 & 0x4) != 0) {
/*  694 */             Transform3D transform3D = (paramArrayOfGeometryAtom[b]).source.getCurrentLocalToVworld();
/*  695 */             if (pickInfo == null)
/*  696 */               pickInfo = new PickInfo(); 
/*  697 */             pickInfo.setLocalToVWorld(new Transform3D(transform3D));
/*      */           } 
/*      */ 
/*      */           
/*  701 */           if ((paramInt1 & 0x10) != 0 || (paramInt1 & 0x20) != 0 || (paramInt1 & 0x8) != 0 || (paramInt1 & 0x40) != 0) {
/*      */ 
/*      */ 
/*      */             
/*  705 */             if (pickInfo == null)
/*  706 */               pickInfo = new PickInfo(); 
/*  707 */             pickInfo.setNodeRef((Node)shape3DCompileRetained.srcList[b1]);
/*  708 */             Transform3D transform3D = (paramArrayOfGeometryAtom[b]).source.getCurrentLocalToVworld();
/*  709 */             pickInfo.setLocalToVWorldRef(transform3D);
/*      */           } 
/*      */           
/*  712 */           if (pickInfo != null)
/*  713 */             arrayList1.add(pickInfo); 
/*  714 */           if (paramInt2 == 2) {
/*  715 */             return arrayList1;
/*      */           }
/*      */         } 
/*      */       } else {
/*      */         
/*  720 */         Node[] arrayOfNode = null;
/*      */ 
/*      */         
/*  723 */         if ((paramInt1 & true) != 0) {
/*      */           
/*  725 */           arrayOfNode = createPath(nodeRetained, paramBranchGroupRetained, paramArrayOfGeometryAtom[b], paramArrayList);
/*      */           
/*  727 */           if (arrayOfNode != null) {
/*  728 */             SceneGraphPath sceneGraphPath = new SceneGraphPath(paramLocale, arrayOfNode, (Node)nodeRetained.source);
/*      */             
/*  730 */             sceneGraphPath.setTransform(shape3DRetained.getCurrentLocalToVworld(0));
/*  731 */             if (pickInfo == null)
/*  732 */               pickInfo = new PickInfo(); 
/*  733 */             pickInfo.setSceneGraphPath(sceneGraphPath);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  738 */         if ((paramInt1 & 0x2) != 0) {
/*  739 */           if (pickInfo == null)
/*  740 */             pickInfo = new PickInfo(); 
/*  741 */           pickInfo.setNode((Node)nodeRetained.source);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  746 */         if ((paramInt1 & 0x4) != 0) {
/*  747 */           Transform3D transform3D = (paramArrayOfGeometryAtom[b]).source.getCurrentLocalToVworld();
/*  748 */           if (pickInfo == null)
/*  749 */             pickInfo = new PickInfo(); 
/*  750 */           pickInfo.setLocalToVWorld(new Transform3D(transform3D));
/*      */         } 
/*      */ 
/*      */         
/*  754 */         if ((paramInt1 & 0x10) != 0 || (paramInt1 & 0x20) != 0 || (paramInt1 & 0x8) != 0 || (paramInt1 & 0x40) != 0) {
/*      */ 
/*      */ 
/*      */           
/*  758 */           if (pickInfo == null)
/*  759 */             pickInfo = new PickInfo(); 
/*  760 */           pickInfo.setNodeRef((Node)nodeRetained.source);
/*  761 */           Transform3D transform3D = (paramArrayOfGeometryAtom[b]).source.getCurrentLocalToVworld();
/*  762 */           pickInfo.setLocalToVWorldRef(transform3D);
/*      */         } 
/*      */         
/*  765 */         if (pickInfo != null)
/*  766 */           arrayList1.add(pickInfo); 
/*  767 */         if (paramInt2 == 2) {
/*  768 */           return arrayList1;
/*      */         }
/*      */       } 
/*      */       continue;
/*      */     } 
/*  773 */     return arrayList1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static PickInfo[] pick(Object paramObject, GeometryAtom[] paramArrayOfGeometryAtom, int paramInt1, int paramInt2, PickShape paramPickShape, int paramInt3) {
/*  780 */     PickInfo[] arrayOfPickInfo = null;
/*  781 */     Locale locale = null;
/*  782 */     BranchGroupRetained branchGroupRetained = null;
/*  783 */     ArrayList arrayList1 = null;
/*  784 */     ArrayList arrayList2 = null;
/*      */     
/*  786 */     if (paramObject instanceof Locale) {
/*  787 */       locale = (Locale)paramObject;
/*      */     }
/*  789 */     else if (paramObject instanceof BranchGroupRetained) {
/*  790 */       branchGroupRetained = (BranchGroupRetained)paramObject;
/*  791 */       locale = branchGroupRetained.locale;
/*      */     } 
/*  793 */     synchronized (locale.universe.sceneGraphLock) {
/*  794 */       if (branchGroupRetained != null) {
/*  795 */         arrayList1 = initSceneGraphPath(branchGroupRetained);
/*      */       }
/*  797 */       arrayList2 = getPickInfos(arrayList1, branchGroupRetained, paramArrayOfGeometryAtom, locale, paramInt2, paramInt3);
/*      */     } 
/*      */     
/*      */     int i;
/*      */     
/*  802 */     if (paramInt1 == 2 && arrayList2 != null && (i = arrayList2.size()) > 0) {
/*      */ 
/*      */ 
/*      */       
/*  806 */       PickInfo pickInfo = null;
/*  807 */       Node node1 = null;
/*      */ 
/*      */       
/*  810 */       for (int j = i - 1; j >= 0; j--) {
/*  811 */         pickInfo = (PickInfo)arrayList2.get(j);
/*      */         
/*  813 */         node1 = pickInfo.getNode();
/*  814 */         if (node1 == null)
/*      */         {
/*  816 */           node1 = pickInfo.getNodeRef();
/*      */         }
/*      */         
/*  819 */         if (node1 instanceof Shape3D) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  842 */           if (!node1.getCapability(12)) {
/*  843 */             throw new CapabilityNotSetException(J3dI18N.getString("PickInfo0"));
/*      */           }
/*      */           
/*  846 */           for (byte b = 0; b < ((Shape3D)node1).numGeometries(); b++) {
/*  847 */             Geometry geometry = ((Shape3D)node1).getGeometry(b);
/*      */             
/*  849 */             if (geometry != null) {
/*      */ 
/*      */ 
/*      */               
/*  853 */               if (!geometry.getCapability(18)) {
/*  854 */                 throw new CapabilityNotSetException(J3dI18N.getString("PickInfo1"));
/*      */               }
/*      */               
/*  857 */               if (geometry instanceof GeometryArray) {
/*  858 */                 if (!geometry.getCapability(0))
/*  859 */                   throw new CapabilityNotSetException(J3dI18N.getString("PickInfo2")); 
/*  860 */                 if (!geometry.getCapability(8))
/*  861 */                   throw new CapabilityNotSetException(J3dI18N.getString("PickInfo3")); 
/*  862 */                 if (!geometry.getCapability(17))
/*  863 */                   throw new CapabilityNotSetException(J3dI18N.getString("PickInfo4")); 
/*  864 */                 if (geometry instanceof IndexedGeometryArray && 
/*  865 */                   !geometry.getCapability(9)) {
/*  866 */                   throw new CapabilityNotSetException(J3dI18N.getString("PickInfo5"));
/*      */                 }
/*  868 */               } else if (geometry instanceof CompressedGeometry && 
/*  869 */                 !geometry.getCapability(2)) {
/*  870 */                 throw new CapabilityNotSetException(J3dI18N.getString("PickInfo0"));
/*      */               } 
/*      */             } 
/*      */           } 
/*  874 */           if (!((Shape3DRetained)node1.retained).intersect(pickInfo, paramPickShape, paramInt2)) {
/*      */ 
/*      */             
/*  877 */             arrayList2.remove(j);
/*      */           
/*      */           }
/*  880 */           else if (paramInt3 == 2) {
/*  881 */             arrayOfPickInfo = new PickInfo[1];
/*  882 */             arrayOfPickInfo[0] = pickInfo;
/*  883 */             return arrayOfPickInfo;
/*      */           } 
/*  885 */         } else if (node1 instanceof Morph) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  908 */           if (!node1.getCapability(12)) {
/*  909 */             throw new CapabilityNotSetException(J3dI18N.getString("PickInfo6"));
/*      */           }
/*      */           
/*  912 */           int k = ((MorphRetained)node1.retained).getNumGeometryArrays();
/*  913 */           for (byte b = 0; b < k; b++) {
/*  914 */             GeometryArray geometryArray = ((Morph)node1).getGeometryArray(b);
/*      */             
/*  916 */             if (geometryArray != null) {
/*      */ 
/*      */ 
/*      */               
/*  920 */               if (!geometryArray.getCapability(18)) {
/*  921 */                 throw new CapabilityNotSetException(J3dI18N.getString("PickInfo1"));
/*      */               }
/*      */               
/*  924 */               if (!geometryArray.getCapability(0))
/*  925 */                 throw new CapabilityNotSetException(J3dI18N.getString("PickInfo2")); 
/*  926 */               if (!geometryArray.getCapability(8))
/*  927 */                 throw new CapabilityNotSetException(J3dI18N.getString("PickInfo3")); 
/*  928 */               if (!geometryArray.getCapability(17)) {
/*  929 */                 throw new CapabilityNotSetException(J3dI18N.getString("PickInfo4"));
/*      */               }
/*  931 */               if (geometryArray instanceof IndexedGeometryArray && 
/*  932 */                 !geometryArray.getCapability(9)) {
/*  933 */                 throw new CapabilityNotSetException(J3dI18N.getString("PickInfo5"));
/*      */               }
/*      */             } 
/*      */           } 
/*  937 */           if (!((MorphRetained)node1.retained).intersect(pickInfo, paramPickShape, paramInt2)) {
/*  938 */             arrayList2.remove(j);
/*      */           }
/*  940 */           else if (paramInt3 == 2) {
/*  941 */             arrayOfPickInfo = new PickInfo[1];
/*  942 */             arrayOfPickInfo[0] = pickInfo;
/*  943 */             return arrayOfPickInfo;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  951 */     if (arrayList2 != null && arrayList2.size() > 0) {
/*      */ 
/*      */ 
/*      */       
/*  955 */       arrayOfPickInfo = new PickInfo[arrayList2.size()];
/*  956 */       return (PickInfo[])arrayList2.toArray(arrayOfPickInfo);
/*      */     } 
/*      */     
/*  959 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public class IntersectionInfo
/*      */   {
/*      */     private int geomIndex;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Geometry geom;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Point3d intersectionPoint;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private double distance;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int[] vertexIndices;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1008 */     void setGeometryIndex(int param1Int) { this.geomIndex = param1Int; }
/*      */ 
/*      */ 
/*      */     
/* 1012 */     void setGeometry(Geometry param1Geometry) { this.geom = param1Geometry; }
/*      */ 
/*      */     
/*      */     void setIntersectionPoint(Point3d param1Point3d) {
/* 1016 */       assert param1Point3d != null;
/* 1017 */       this.intersectionPoint = new Point3d(param1Point3d);
/*      */     }
/*      */ 
/*      */     
/* 1021 */     void setDistance(double param1Double) { this.distance = param1Double; }
/*      */ 
/*      */     
/*      */     void setVertexIndices(int[] param1ArrayOfInt) {
/* 1025 */       assert param1ArrayOfInt != null;
/* 1026 */       this.vertexIndices = new int[param1ArrayOfInt.length];
/* 1027 */       for (byte b = 0; b < param1ArrayOfInt.length; b++) {
/* 1028 */         this.vertexIndices[b] = param1ArrayOfInt[b];
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1038 */     public int getGeometryIndex() { return this.geomIndex; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1046 */     public Geometry getGeometry() { return this.geom; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1054 */     public Point3d getIntersectionPoint() { return this.intersectionPoint; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1064 */     public double getDistance() { return this.distance; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1072 */     public int[] getVertexIndices() { return this.vertexIndices; }
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\PickInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */