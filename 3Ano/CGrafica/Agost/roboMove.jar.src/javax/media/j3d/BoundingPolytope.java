/*      */ package javax.media.j3d;
/*      */ 
/*      */ import com.sun.j3d.internal.HashCodeUtil;
/*      */ import javax.vecmath.Point3d;
/*      */ import javax.vecmath.Point4d;
/*      */ import javax.vecmath.Vector3d;
/*      */ import javax.vecmath.Vector4d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BoundingPolytope
/*      */   extends Bounds
/*      */ {
/*      */   Vector4d[] planes;
/*      */   double[] mag;
/*      */   double[] pDotN;
/*      */   Point3d[] verts;
/*      */   int nVerts;
/*   46 */   Point3d centroid = new Point3d();
/*      */ 
/*      */ 
/*      */   
/*      */   Point3d[] boxVerts;
/*      */ 
/*      */   
/*      */   boolean allocBoxVerts = false;
/*      */ 
/*      */ 
/*      */   
/*      */   public BoundingPolytope(Vector4d[] paramArrayOfVector4d) {
/*   58 */     if (paramArrayOfVector4d.length < 4) {
/*   59 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingPolytope11"));
/*      */     }
/*      */     
/*   62 */     this.boundId = 4;
/*      */ 
/*      */     
/*   65 */     this.planes = new Vector4d[paramArrayOfVector4d.length];
/*   66 */     this.mag = new double[paramArrayOfVector4d.length];
/*   67 */     this.pDotN = new double[paramArrayOfVector4d.length];
/*      */     
/*   69 */     for (byte b = 0; b < paramArrayOfVector4d.length; b++) {
/*      */ 
/*      */       
/*   72 */       this.mag[b] = Math.sqrt((paramArrayOfVector4d[b]).x * (paramArrayOfVector4d[b]).x + (paramArrayOfVector4d[b]).y * (paramArrayOfVector4d[b]).y + (paramArrayOfVector4d[b]).z * (paramArrayOfVector4d[b]).z);
/*      */       
/*   74 */       double d = 1.0D / this.mag[b];
/*   75 */       this.planes[b] = new Vector4d((paramArrayOfVector4d[b]).x * d, (paramArrayOfVector4d[b]).y * d, (paramArrayOfVector4d[b]).z * d, (paramArrayOfVector4d[b]).w * d);
/*      */     } 
/*      */ 
/*      */     
/*   79 */     computeAllVerts();
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
/*      */   public BoundingPolytope() {
/*   96 */     this.boundId = 4;
/*   97 */     this.planes = new Vector4d[6];
/*   98 */     this.mag = new double[this.planes.length];
/*   99 */     this.pDotN = new double[this.planes.length];
/*      */     
/*  101 */     this.planes[0] = new Vector4d(1.0D, 0.0D, 0.0D, -1.0D);
/*  102 */     this.planes[1] = new Vector4d(-1.0D, 0.0D, 0.0D, -1.0D);
/*  103 */     this.planes[2] = new Vector4d(0.0D, 1.0D, 0.0D, -1.0D);
/*  104 */     this.planes[3] = new Vector4d(0.0D, -1.0D, 0.0D, -1.0D);
/*  105 */     this.planes[4] = new Vector4d(0.0D, 0.0D, 1.0D, -1.0D);
/*  106 */     this.planes[5] = new Vector4d(0.0D, 0.0D, -1.0D, -1.0D);
/*  107 */     this.mag[0] = 1.0D;
/*  108 */     this.mag[1] = 1.0D;
/*  109 */     this.mag[2] = 1.0D;
/*  110 */     this.mag[3] = 1.0D;
/*  111 */     this.mag[4] = 1.0D;
/*  112 */     this.mag[5] = 1.0D;
/*      */     
/*  114 */     computeAllVerts();
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
/*      */   public BoundingPolytope(Bounds paramBounds) {
/*  128 */     this.boundId = 4;
/*      */     
/*  130 */     if (paramBounds == null) {
/*  131 */       this.boundsIsEmpty = true;
/*  132 */       this.boundsIsInfinite = false;
/*  133 */       initEmptyPolytope();
/*  134 */       computeAllVerts();
/*      */       
/*      */       return;
/*      */     } 
/*  138 */     this.boundsIsEmpty = paramBounds.boundsIsEmpty;
/*  139 */     this.boundsIsInfinite = paramBounds.boundsIsInfinite;
/*      */     
/*  141 */     if (paramBounds.boundId == 2) {
/*  142 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/*  143 */       this.planes = new Vector4d[6];
/*  144 */       this.mag = new double[this.planes.length];
/*  145 */       this.pDotN = new double[this.planes.length];
/*      */       
/*  147 */       this.planes[0] = new Vector4d(1.0D, 0.0D, 0.0D, -(boundingSphere.center.x + boundingSphere.radius));
/*  148 */       this.planes[1] = new Vector4d(-1.0D, 0.0D, 0.0D, boundingSphere.center.x - boundingSphere.radius);
/*  149 */       this.planes[2] = new Vector4d(0.0D, 1.0D, 0.0D, -(boundingSphere.center.y + boundingSphere.radius));
/*  150 */       this.planes[3] = new Vector4d(0.0D, -1.0D, 0.0D, boundingSphere.center.y - boundingSphere.radius);
/*  151 */       this.planes[4] = new Vector4d(0.0D, 0.0D, 1.0D, -(boundingSphere.center.z + boundingSphere.radius));
/*  152 */       this.planes[5] = new Vector4d(0.0D, 0.0D, -1.0D, boundingSphere.center.z - boundingSphere.radius);
/*  153 */       this.mag[0] = 1.0D;
/*  154 */       this.mag[1] = 1.0D;
/*  155 */       this.mag[2] = 1.0D;
/*  156 */       this.mag[3] = 1.0D;
/*  157 */       this.mag[4] = 1.0D;
/*  158 */       this.mag[5] = 1.0D;
/*  159 */       computeAllVerts();
/*      */     }
/*  161 */     else if (paramBounds.boundId == 1) {
/*  162 */       BoundingBox boundingBox = (BoundingBox)paramBounds;
/*  163 */       this.planes = new Vector4d[6];
/*  164 */       this.pDotN = new double[this.planes.length];
/*  165 */       this.mag = new double[this.planes.length];
/*      */       
/*  167 */       this.planes[0] = new Vector4d(1.0D, 0.0D, 0.0D, -boundingBox.upper.x);
/*  168 */       this.planes[1] = new Vector4d(-1.0D, 0.0D, 0.0D, boundingBox.lower.x);
/*  169 */       this.planes[2] = new Vector4d(0.0D, 1.0D, 0.0D, -boundingBox.upper.y);
/*  170 */       this.planes[3] = new Vector4d(0.0D, -1.0D, 0.0D, boundingBox.lower.y);
/*  171 */       this.planes[4] = new Vector4d(0.0D, 0.0D, 1.0D, -boundingBox.upper.z);
/*  172 */       this.planes[5] = new Vector4d(0.0D, 0.0D, -1.0D, boundingBox.lower.z);
/*  173 */       this.mag[0] = 1.0D;
/*  174 */       this.mag[1] = 1.0D;
/*  175 */       this.mag[2] = 1.0D;
/*  176 */       this.mag[3] = 1.0D;
/*  177 */       this.mag[4] = 1.0D;
/*  178 */       this.mag[5] = 1.0D;
/*  179 */       computeAllVerts();
/*      */     }
/*  181 */     else if (paramBounds.boundId == 4) {
/*  182 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/*  183 */       this.planes = new Vector4d[boundingPolytope.planes.length];
/*  184 */       this.mag = new double[this.planes.length];
/*  185 */       this.pDotN = new double[this.planes.length];
/*  186 */       this.nVerts = boundingPolytope.nVerts;
/*  187 */       this.verts = new Point3d[this.nVerts]; byte b;
/*  188 */       for (b = 0; b < this.planes.length; b++) {
/*  189 */         this.planes[b] = new Vector4d(boundingPolytope.planes[b]);
/*  190 */         this.mag[b] = boundingPolytope.mag[b];
/*  191 */         this.pDotN[b] = boundingPolytope.pDotN[b];
/*      */       } 
/*  193 */       for (b = 0; b < this.verts.length; b++) {
/*  194 */         this.verts[b] = new Point3d(boundingPolytope.verts[b]);
/*      */       }
/*  196 */       this.centroid = boundingPolytope.centroid;
/*      */     } else {
/*      */       
/*  199 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingPolytope0"));
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
/*      */   public BoundingPolytope(Bounds[] paramArrayOfBounds) {
/*  211 */     byte b = 0;
/*      */     
/*  213 */     this.boundId = 4;
/*  214 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0) {
/*  215 */       this.boundsIsEmpty = true;
/*  216 */       this.boundsIsInfinite = false;
/*  217 */       initEmptyPolytope();
/*  218 */       computeAllVerts();
/*      */       
/*      */       return;
/*      */     } 
/*  222 */     while (paramArrayOfBounds[b] == null && b < paramArrayOfBounds.length) {
/*  223 */       b++;
/*      */     }
/*      */     
/*  226 */     if (b >= paramArrayOfBounds.length) {
/*  227 */       this.boundsIsEmpty = true;
/*  228 */       this.boundsIsInfinite = false;
/*  229 */       initEmptyPolytope();
/*  230 */       computeAllVerts();
/*      */       
/*      */       return;
/*      */     } 
/*  234 */     this.boundsIsEmpty = (paramArrayOfBounds[b]).boundsIsEmpty;
/*  235 */     this.boundsIsInfinite = (paramArrayOfBounds[b]).boundsIsInfinite;
/*      */     
/*  237 */     if ((paramArrayOfBounds[b]).boundId == 2) {
/*  238 */       BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b];
/*  239 */       this.planes = new Vector4d[6];
/*  240 */       this.mag = new double[this.planes.length];
/*  241 */       this.pDotN = new double[this.planes.length];
/*      */       
/*  243 */       this.planes[0] = new Vector4d(1.0D, 0.0D, 0.0D, -(boundingSphere.center.x + boundingSphere.radius));
/*  244 */       this.planes[1] = new Vector4d(-1.0D, 0.0D, 0.0D, boundingSphere.center.x - boundingSphere.radius);
/*  245 */       this.planes[2] = new Vector4d(0.0D, 1.0D, 0.0D, -(boundingSphere.center.y + boundingSphere.radius));
/*  246 */       this.planes[3] = new Vector4d(0.0D, -1.0D, 0.0D, boundingSphere.center.y - boundingSphere.radius);
/*  247 */       this.planes[4] = new Vector4d(0.0D, 0.0D, 1.0D, -(boundingSphere.center.z + boundingSphere.radius));
/*  248 */       this.planes[5] = new Vector4d(0.0D, 0.0D, -1.0D, boundingSphere.center.z - boundingSphere.radius);
/*  249 */       this.mag[0] = 1.0D;
/*  250 */       this.mag[1] = 1.0D;
/*  251 */       this.mag[2] = 1.0D;
/*  252 */       this.mag[3] = 1.0D;
/*  253 */       this.mag[4] = 1.0D;
/*  254 */       this.mag[5] = 1.0D;
/*      */       
/*  256 */       computeAllVerts();
/*  257 */     } else if ((paramArrayOfBounds[b]).boundId == 1) {
/*  258 */       BoundingBox boundingBox = (BoundingBox)paramArrayOfBounds[b];
/*  259 */       this.planes = new Vector4d[6];
/*  260 */       this.mag = new double[this.planes.length];
/*  261 */       this.pDotN = new double[this.planes.length];
/*      */       
/*  263 */       this.planes[0] = new Vector4d(1.0D, 0.0D, 0.0D, -boundingBox.upper.x);
/*  264 */       this.planes[1] = new Vector4d(-1.0D, 0.0D, 0.0D, boundingBox.lower.x);
/*  265 */       this.planes[2] = new Vector4d(0.0D, 1.0D, 0.0D, -boundingBox.upper.y);
/*  266 */       this.planes[3] = new Vector4d(0.0D, -1.0D, 0.0D, boundingBox.lower.y);
/*  267 */       this.planes[4] = new Vector4d(0.0D, 0.0D, 1.0D, -boundingBox.upper.z);
/*  268 */       this.planes[5] = new Vector4d(0.0D, 0.0D, -1.0D, boundingBox.lower.z);
/*  269 */       this.mag[0] = 1.0D;
/*  270 */       this.mag[1] = 1.0D;
/*  271 */       this.mag[2] = 1.0D;
/*  272 */       this.mag[3] = 1.0D;
/*  273 */       this.mag[4] = 1.0D;
/*  274 */       this.mag[5] = 1.0D;
/*      */       
/*  276 */       computeAllVerts();
/*  277 */     } else if ((paramArrayOfBounds[b]).boundId == 4) {
/*  278 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramArrayOfBounds[b];
/*  279 */       this.planes = new Vector4d[boundingPolytope.planes.length];
/*  280 */       this.mag = new double[this.planes.length];
/*  281 */       this.pDotN = new double[this.planes.length];
/*  282 */       this.nVerts = boundingPolytope.nVerts;
/*  283 */       this.verts = new Point3d[this.nVerts];
/*  284 */       for (b = 0; b < this.planes.length; b++) {
/*  285 */         this.planes[b] = new Vector4d(boundingPolytope.planes[b]);
/*  286 */         this.pDotN[b] = boundingPolytope.pDotN[b];
/*  287 */         this.mag[b] = boundingPolytope.mag[b];
/*      */       } 
/*  289 */       for (b = 0; b < this.verts.length; b++) {
/*  290 */         this.verts[b] = new Point3d(boundingPolytope.verts[b]);
/*      */       }
/*  292 */       this.centroid = boundingPolytope.centroid;
/*      */     } else {
/*  294 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingPolytope1"));
/*      */     } 
/*  296 */     for (; ++b < paramArrayOfBounds.length; b++) {
/*  297 */       combine(paramArrayOfBounds[b]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPlanes(Vector4d[] paramArrayOfVector4d) {
/*  308 */     if (paramArrayOfVector4d.length < 4) {
/*  309 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingPolytope11"));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  315 */     this.planes = new Vector4d[paramArrayOfVector4d.length];
/*  316 */     this.pDotN = new double[paramArrayOfVector4d.length];
/*  317 */     this.mag = new double[paramArrayOfVector4d.length];
/*  318 */     this.boundsIsEmpty = false;
/*      */     
/*  320 */     if (paramArrayOfVector4d.length <= 0) {
/*  321 */       this.boundsIsEmpty = true;
/*  322 */       this.boundsIsInfinite = false;
/*  323 */       computeAllVerts();
/*      */       
/*      */       return;
/*      */     } 
/*  327 */     for (byte b = 0; b < paramArrayOfVector4d.length; b++) {
/*      */       
/*  329 */       this.mag[b] = Math.sqrt((paramArrayOfVector4d[b]).x * (paramArrayOfVector4d[b]).x + (paramArrayOfVector4d[b]).y * (paramArrayOfVector4d[b]).y + (paramArrayOfVector4d[b]).z * (paramArrayOfVector4d[b]).z);
/*      */       
/*  331 */       double d = 1.0D / this.mag[b];
/*  332 */       this.planes[b] = new Vector4d((paramArrayOfVector4d[b]).x * d, (paramArrayOfVector4d[b]).y * d, (paramArrayOfVector4d[b]).z * d, (paramArrayOfVector4d[b]).w * d);
/*      */     } 
/*      */     
/*  335 */     computeAllVerts();
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
/*      */   public void getPlanes(Vector4d[] paramArrayOfVector4d) {
/*  350 */     for (byte b = 0; b < paramArrayOfVector4d.length; b++) {
/*  351 */       (this.planes[b]).x *= this.mag[b];
/*  352 */       (this.planes[b]).y *= this.mag[b];
/*  353 */       (this.planes[b]).z *= this.mag[b];
/*  354 */       (this.planes[b]).w *= this.mag[b];
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  359 */   public int getNumPlanes() { return this.planes.length; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void set(Bounds paramBounds) {
/*  373 */     if (paramBounds == null) {
/*  374 */       this.boundsIsEmpty = true;
/*  375 */       this.boundsIsInfinite = false;
/*  376 */       computeAllVerts();
/*      */     }
/*  378 */     else if (paramBounds.boundId == 2) {
/*  379 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/*      */       
/*  381 */       if (this.boundsIsEmpty) {
/*  382 */         initEmptyPolytope();
/*  383 */         computeAllVerts();
/*      */       } 
/*      */       
/*  386 */       for (byte b = 0; b < this.planes.length; b++) {
/*  387 */         (this.planes[b]).w = -(boundingSphere.center.x * (this.planes[b]).x + boundingSphere.center.y * (this.planes[b]).y + boundingSphere.center.z * (this.planes[b]).z + boundingSphere.radius);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  392 */       this.boundsIsEmpty = paramBounds.boundsIsEmpty;
/*  393 */       this.boundsIsInfinite = paramBounds.boundsIsInfinite;
/*  394 */       computeAllVerts();
/*      */     }
/*  396 */     else if (paramBounds.boundId == 1) {
/*  397 */       BoundingBox boundingBox = (BoundingBox)paramBounds;
/*      */ 
/*      */       
/*  400 */       if (this.boundsIsEmpty) {
/*  401 */         initEmptyPolytope();
/*  402 */         computeAllVerts();
/*      */       } 
/*      */       
/*  405 */       for (byte b = 0; b < this.planes.length; b++) {
/*  406 */         double d1 = boundingBox.upper.x * (this.planes[b]).x;
/*  407 */         double d2 = boundingBox.upper.y * (this.planes[b]).y;
/*  408 */         double d3 = boundingBox.upper.z * (this.planes[b]).z;
/*  409 */         double d4 = boundingBox.lower.x * (this.planes[b]).x;
/*  410 */         double d5 = boundingBox.lower.y * (this.planes[b]).y;
/*  411 */         double d6 = boundingBox.lower.z * (this.planes[b]).z;
/*  412 */         (this.planes[b]).w = -(d1 + d2 + d3); double d7;
/*  413 */         if ((d7 = d1 + d2 + d6) + (this.planes[b]).w > 0.0D) (this.planes[b]).w = -d7; 
/*  414 */         if ((d7 = d1 + d5 + d3) + (this.planes[b]).w > 0.0D) (this.planes[b]).w = -d7; 
/*  415 */         if ((d7 = d1 + d5 + d6) + (this.planes[b]).w > 0.0D) (this.planes[b]).w = -d7;
/*      */         
/*  417 */         if ((d7 = d4 + d2 + d3) + (this.planes[b]).w > 0.0D) (this.planes[b]).w = -d7; 
/*  418 */         if ((d7 = d4 + d2 + d6) + (this.planes[b]).w > 0.0D) (this.planes[b]).w = -d7; 
/*  419 */         if ((d7 = d4 + d5 + d3) + (this.planes[b]).w > 0.0D) (this.planes[b]).w = -d7; 
/*  420 */         if ((d7 = d4 + d5 + d6) + (this.planes[b]).w > 0.0D) (this.planes[b]).w = -d7;
/*      */       
/*      */       } 
/*  423 */       this.boundsIsEmpty = paramBounds.boundsIsEmpty;
/*  424 */       this.boundsIsInfinite = paramBounds.boundsIsInfinite;
/*  425 */       computeAllVerts();
/*      */     }
/*  427 */     else if (paramBounds.boundId == 4) {
/*  428 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/*  429 */       if (this.planes.length != boundingPolytope.planes.length) {
/*  430 */         this.planes = new Vector4d[boundingPolytope.planes.length];
/*  431 */         for (byte b = 0; b < boundingPolytope.planes.length; ) { this.planes[b] = new Vector4d(); b++; }
/*  432 */          this.mag = new double[boundingPolytope.planes.length];
/*  433 */         this.pDotN = new double[boundingPolytope.planes.length];
/*      */       } 
/*      */ 
/*      */       
/*  437 */       for (byte b1 = 0; b1 < boundingPolytope.planes.length; b1++) {
/*  438 */         (this.planes[b1]).x = (boundingPolytope.planes[b1]).x;
/*  439 */         (this.planes[b1]).y = (boundingPolytope.planes[b1]).y;
/*  440 */         (this.planes[b1]).z = (boundingPolytope.planes[b1]).z;
/*  441 */         (this.planes[b1]).w = (boundingPolytope.planes[b1]).w;
/*  442 */         this.mag[b1] = boundingPolytope.mag[b1];
/*      */       } 
/*  444 */       this.nVerts = boundingPolytope.nVerts;
/*  445 */       this.verts = new Point3d[this.nVerts];
/*  446 */       for (byte b2 = 0; b2 < this.nVerts; b2++) {
/*  447 */         this.verts[b2] = new Point3d(boundingPolytope.verts[b2]);
/*      */       }
/*      */       
/*  450 */       this.boundsIsEmpty = paramBounds.boundsIsEmpty;
/*  451 */       this.boundsIsInfinite = paramBounds.boundsIsInfinite;
/*      */     } else {
/*      */       
/*  454 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingPolytope2"));
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
/*  465 */   public Object clone() { return new BoundingPolytope(this.planes); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object paramObject) {
/*      */     try {
/*  484 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramObject;
/*  485 */       if (this.planes.length != boundingPolytope.planes.length)
/*  486 */         return false; 
/*  487 */       for (byte b = 0; b < this.planes.length; b++) {
/*  488 */         if (!this.planes[b].equals(boundingPolytope.planes[b]))
/*  489 */           return false; 
/*      */       } 
/*  491 */       return true;
/*      */     }
/*  493 */     catch (NullPointerException nullPointerException) {
/*  494 */       return false;
/*      */     }
/*  496 */     catch (ClassCastException classCastException) {
/*  497 */       return false;
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
/*      */   public int hashCode() {
/*  515 */     long l = 1L;
/*      */     
/*  517 */     for (byte b = 0; b < this.planes.length; b++) {
/*  518 */       l = 31L * l + HashCodeUtil.doubleToLongBits((this.planes[b]).x);
/*  519 */       l = 31L * l + HashCodeUtil.doubleToLongBits((this.planes[b]).y);
/*  520 */       l = 31L * l + HashCodeUtil.doubleToLongBits((this.planes[b]).z);
/*  521 */       l = 31L * l + HashCodeUtil.doubleToLongBits((this.planes[b]).w);
/*      */     } 
/*      */     
/*  524 */     return (int)(l ^ l >> 32);
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
/*      */   public void combine(Bounds paramBounds) {
/*  537 */     if (paramBounds == null || paramBounds.boundsIsEmpty || this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  542 */     if (this.boundsIsEmpty || paramBounds.boundsIsInfinite) {
/*  543 */       set(paramBounds);
/*      */       
/*      */       return;
/*      */     } 
/*  547 */     this.boundsIsEmpty = paramBounds.boundsIsEmpty;
/*  548 */     this.boundsIsInfinite = paramBounds.boundsIsInfinite;
/*      */     
/*  550 */     if (paramBounds.boundId == 2) {
/*  551 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/*      */ 
/*      */       
/*  554 */       for (byte b = 0; b < this.planes.length; b++) {
/*  555 */         double d = boundingSphere.radius + boundingSphere.center.x * (this.planes[b]).x + boundingSphere.center.y * (this.planes[b]).y + boundingSphere.center.z * (this.planes[b]).z + (this.planes[b]).w;
/*      */ 
/*      */         
/*  558 */         if (d > 0.0D) {
/*  559 */           (this.planes[b]).w += -d;
/*      */         }
/*      */       } 
/*  562 */     } else if (paramBounds instanceof BoundingBox) {
/*  563 */       BoundingBox boundingBox = (BoundingBox)paramBounds;
/*  564 */       if (!this.allocBoxVerts) {
/*  565 */         this.boxVerts = new Point3d[8];
/*  566 */         for (byte b = 0; b < 8; ) { this.boxVerts[b] = new Point3d(); b++; }
/*  567 */          this.allocBoxVerts = true;
/*      */       } 
/*  569 */       this.boxVerts[0].set(boundingBox.lower.x, boundingBox.lower.y, boundingBox.lower.z);
/*  570 */       this.boxVerts[1].set(boundingBox.lower.x, boundingBox.upper.y, boundingBox.lower.z);
/*  571 */       this.boxVerts[2].set(boundingBox.upper.x, boundingBox.lower.y, boundingBox.lower.z);
/*  572 */       this.boxVerts[3].set(boundingBox.upper.x, boundingBox.upper.y, boundingBox.lower.z);
/*  573 */       this.boxVerts[4].set(boundingBox.lower.x, boundingBox.lower.y, boundingBox.upper.z);
/*  574 */       this.boxVerts[5].set(boundingBox.lower.x, boundingBox.upper.y, boundingBox.upper.z);
/*  575 */       this.boxVerts[6].set(boundingBox.upper.x, boundingBox.lower.y, boundingBox.upper.z);
/*  576 */       this.boxVerts[7].set(boundingBox.upper.x, boundingBox.upper.y, boundingBox.upper.z);
/*  577 */       combine(this.boxVerts);
/*      */     }
/*  579 */     else if (paramBounds.boundId == 4) {
/*  580 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/*  581 */       combine(boundingPolytope.verts);
/*      */     } else {
/*  583 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingPolytope3"));
/*      */     } 
/*      */     
/*  586 */     computeAllVerts();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void combine(Bounds[] paramArrayOfBounds) {
/*  596 */     byte b = 0;
/*      */ 
/*      */     
/*  599 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0 || this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  604 */     while (b < paramArrayOfBounds.length && (paramArrayOfBounds[b] == null || (paramArrayOfBounds[b]).boundsIsEmpty))
/*      */     {
/*  606 */       b++;
/*      */     }
/*  608 */     if (b >= paramArrayOfBounds.length) {
/*      */       return;
/*      */     }
/*  611 */     if (this.boundsIsEmpty) {
/*  612 */       set(paramArrayOfBounds[b++]);
/*      */     }
/*  614 */     if (this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*  617 */     for (; b < paramArrayOfBounds.length; b++) {
/*  618 */       if (paramArrayOfBounds[b] != null && 
/*  619 */         !(paramArrayOfBounds[b]).boundsIsEmpty) {
/*  620 */         if ((paramArrayOfBounds[b]).boundsIsInfinite) {
/*  621 */           set(paramArrayOfBounds[b]);
/*      */           break;
/*      */         } 
/*  624 */         if ((paramArrayOfBounds[b]).boundId == 2) {
/*  625 */           BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b];
/*  626 */           for (byte b1 = 0; b1 < this.planes.length; b1++) {
/*  627 */             double d = boundingSphere.radius + boundingSphere.center.x * (this.planes[b1]).x + boundingSphere.center.y * (this.planes[b1]).y + boundingSphere.center.z * (this.planes[b1]).z + (this.planes[b1]).w;
/*      */ 
/*      */             
/*  630 */             if (d > 0.0D) {
/*  631 */               (this.planes[b1]).w += -d;
/*      */             }
/*      */           } 
/*  634 */         } else if ((paramArrayOfBounds[b]).boundId == 1) {
/*  635 */           BoundingBox boundingBox = (BoundingBox)paramArrayOfBounds[b];
/*  636 */           if (!this.allocBoxVerts) {
/*  637 */             this.boxVerts = new Point3d[8];
/*  638 */             for (byte b1 = 0; b1 < 8; ) { this.boxVerts[b1] = new Point3d(); b1++; }
/*  639 */              this.allocBoxVerts = true;
/*      */           } 
/*  641 */           this.boxVerts[0].set(boundingBox.lower.x, boundingBox.lower.y, boundingBox.lower.z);
/*  642 */           this.boxVerts[1].set(boundingBox.lower.x, boundingBox.upper.y, boundingBox.lower.z);
/*  643 */           this.boxVerts[2].set(boundingBox.upper.x, boundingBox.lower.y, boundingBox.lower.z);
/*  644 */           this.boxVerts[3].set(boundingBox.upper.x, boundingBox.upper.y, boundingBox.lower.z);
/*  645 */           this.boxVerts[4].set(boundingBox.lower.x, boundingBox.lower.y, boundingBox.upper.z);
/*  646 */           this.boxVerts[5].set(boundingBox.lower.x, boundingBox.upper.y, boundingBox.upper.z);
/*  647 */           this.boxVerts[6].set(boundingBox.upper.x, boundingBox.lower.y, boundingBox.upper.z);
/*  648 */           this.boxVerts[7].set(boundingBox.upper.x, boundingBox.upper.y, boundingBox.upper.z);
/*  649 */           combine(this.boxVerts);
/*      */         }
/*  651 */         else if (paramArrayOfBounds[b] instanceof BoundingPolytope) {
/*  652 */           BoundingPolytope boundingPolytope = (BoundingPolytope)paramArrayOfBounds[b];
/*  653 */           combine(boundingPolytope.verts);
/*      */         } else {
/*      */           
/*  656 */           throw new IllegalArgumentException(J3dI18N.getString("BoundingPolytope4"));
/*      */         } 
/*      */       } 
/*  659 */       computeAllVerts();
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
/*      */   public void combine(Point3d paramPoint3d) {
/*  671 */     if (this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */     
/*  675 */     if (this.boundsIsEmpty) {
/*  676 */       this.planes = new Vector4d[6];
/*  677 */       this.mag = new double[this.planes.length];
/*  678 */       this.pDotN = new double[this.planes.length];
/*  679 */       this.nVerts = 1;
/*  680 */       this.verts = new Point3d[this.nVerts];
/*  681 */       this.verts[0] = new Point3d(paramPoint3d.x, paramPoint3d.y, paramPoint3d.z);
/*      */       
/*  683 */       for (byte b = 0; b < this.planes.length; b++) {
/*  684 */         this.pDotN[b] = 0.0D;
/*      */       }
/*  686 */       this.planes[0] = new Vector4d(1.0D, 0.0D, 0.0D, -paramPoint3d.x);
/*  687 */       this.planes[1] = new Vector4d(-1.0D, 0.0D, 0.0D, paramPoint3d.x);
/*  688 */       this.planes[2] = new Vector4d(0.0D, 1.0D, 0.0D, -paramPoint3d.y);
/*  689 */       this.planes[3] = new Vector4d(0.0D, -1.0D, 0.0D, paramPoint3d.y);
/*  690 */       this.planes[4] = new Vector4d(0.0D, 0.0D, 1.0D, -paramPoint3d.z);
/*  691 */       this.planes[5] = new Vector4d(0.0D, 0.0D, -1.0D, paramPoint3d.z);
/*  692 */       this.mag[0] = 1.0D;
/*  693 */       this.mag[1] = 1.0D;
/*  694 */       this.mag[2] = 1.0D;
/*  695 */       this.mag[3] = 1.0D;
/*  696 */       this.mag[4] = 1.0D;
/*  697 */       this.mag[5] = 1.0D;
/*  698 */       this.centroid.x = paramPoint3d.x;
/*  699 */       this.centroid.y = paramPoint3d.y;
/*  700 */       this.centroid.z = paramPoint3d.z;
/*  701 */       this.boundsIsEmpty = false;
/*  702 */       this.boundsIsInfinite = false;
/*      */     } else {
/*      */       
/*  705 */       for (byte b = 0; b < this.planes.length; b++) {
/*  706 */         double d = paramPoint3d.x * (this.planes[b]).x + paramPoint3d.y * (this.planes[b]).y + paramPoint3d.z * (this.planes[b]).z + (this.planes[b]).w;
/*      */         
/*  708 */         if (d > 0.0D) {
/*  709 */           (this.planes[b]).w += -d;
/*      */         }
/*      */       } 
/*  712 */       computeAllVerts();
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
/*      */   public void combine(Point3d[] paramArrayOfPoint3d) {
/*  724 */     if (this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */     
/*  728 */     if (this.boundsIsEmpty) {
/*  729 */       this.planes = new Vector4d[6];
/*  730 */       this.mag = new double[this.planes.length];
/*  731 */       this.pDotN = new double[this.planes.length];
/*  732 */       this.nVerts = paramArrayOfPoint3d.length;
/*  733 */       this.verts = new Point3d[this.nVerts];
/*  734 */       this.verts[0] = new Point3d((paramArrayOfPoint3d[0]).x, (paramArrayOfPoint3d[0]).y, (paramArrayOfPoint3d[0]).z);
/*      */       
/*  736 */       for (byte b1 = 0; b1 < this.planes.length; b1++) {
/*  737 */         this.pDotN[b1] = 0.0D;
/*      */       }
/*  739 */       this.planes[0] = new Vector4d(1.0D, 0.0D, 0.0D, -(paramArrayOfPoint3d[0]).x);
/*  740 */       this.planes[1] = new Vector4d(-1.0D, 0.0D, 0.0D, (paramArrayOfPoint3d[0]).x);
/*  741 */       this.planes[2] = new Vector4d(0.0D, 1.0D, 0.0D, -(paramArrayOfPoint3d[0]).y);
/*  742 */       this.planes[3] = new Vector4d(0.0D, -1.0D, 0.0D, (paramArrayOfPoint3d[0]).y);
/*  743 */       this.planes[4] = new Vector4d(0.0D, 0.0D, 1.0D, -(paramArrayOfPoint3d[0]).z);
/*  744 */       this.planes[5] = new Vector4d(0.0D, 0.0D, -1.0D, (paramArrayOfPoint3d[0]).z);
/*  745 */       this.mag[0] = 1.0D;
/*  746 */       this.mag[1] = 1.0D;
/*  747 */       this.mag[2] = 1.0D;
/*  748 */       this.mag[3] = 1.0D;
/*  749 */       this.mag[4] = 1.0D;
/*  750 */       this.mag[5] = 1.0D;
/*  751 */       this.centroid.x = (paramArrayOfPoint3d[0]).x;
/*  752 */       this.centroid.y = (paramArrayOfPoint3d[0]).y;
/*  753 */       this.centroid.z = (paramArrayOfPoint3d[0]).z;
/*  754 */       this.boundsIsEmpty = false;
/*  755 */       this.boundsIsInfinite = false;
/*      */     } 
/*      */     
/*  758 */     for (byte b = 0; b < paramArrayOfPoint3d.length; b++) {
/*  759 */       for (byte b1 = 0; b1 < this.planes.length; b1++) {
/*  760 */         double d = (paramArrayOfPoint3d[b]).x * (this.planes[b1]).x + (paramArrayOfPoint3d[b]).y * (this.planes[b1]).y + (paramArrayOfPoint3d[b]).z * (this.planes[b1]).z + (this.planes[b1]).w;
/*      */         
/*  762 */         if (d > 0.0D) {
/*  763 */           (this.planes[b1]).w += -d;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  768 */     computeAllVerts();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void transform(Bounds paramBounds, Transform3D paramTransform3D) {
/*  779 */     if (paramBounds == null || paramBounds.boundsIsEmpty) {
/*  780 */       this.boundsIsEmpty = true;
/*  781 */       this.boundsIsInfinite = false;
/*  782 */       computeAllVerts();
/*      */       
/*      */       return;
/*      */     } 
/*  786 */     if (paramBounds.boundsIsInfinite) {
/*  787 */       set(paramBounds);
/*      */       
/*      */       return;
/*      */     } 
/*  791 */     if (paramBounds.boundId == 2) {
/*  792 */       BoundingSphere boundingSphere = new BoundingSphere((BoundingSphere)paramBounds);
/*  793 */       boundingSphere.transform(paramTransform3D);
/*  794 */       set(boundingSphere);
/*  795 */     } else if (paramBounds.boundId == 1) {
/*  796 */       BoundingBox boundingBox = new BoundingBox((BoundingBox)paramBounds);
/*  797 */       boundingBox.transform(paramTransform3D);
/*  798 */       set(boundingBox);
/*  799 */     } else if (paramBounds.boundId == 4) {
/*  800 */       BoundingPolytope boundingPolytope = new BoundingPolytope((BoundingPolytope)paramBounds);
/*  801 */       boundingPolytope.transform(paramTransform3D);
/*  802 */       set(boundingPolytope);
/*      */     } else {
/*  804 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingPolytope5"));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void transform(Transform3D paramTransform3D) {
/*  814 */     if (this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  819 */     Transform3D transform3D = new Transform3D(paramTransform3D);
/*      */     
/*  821 */     transform3D.invert();
/*  822 */     transform3D.transpose();
/*      */     byte b;
/*  824 */     for (b = 0; b < this.planes.length; b++) {
/*  825 */       (this.planes[b]).x *= this.mag[b];
/*  826 */       (this.planes[b]).y *= this.mag[b];
/*  827 */       (this.planes[b]).z *= this.mag[b];
/*  828 */       (this.planes[b]).w *= this.mag[b];
/*  829 */       transform3D.transform(this.planes[b]);
/*      */     } 
/*      */     
/*  832 */     for (b = 0; b < this.planes.length; b++) {
/*      */ 
/*      */       
/*  835 */       this.mag[b] = Math.sqrt((this.planes[b]).x * (this.planes[b]).x + (this.planes[b]).y * (this.planes[b]).y + (this.planes[b]).z * (this.planes[b]).z);
/*      */       
/*  837 */       double d = 1.0D / this.mag[b];
/*  838 */       this.planes[b] = new Vector4d((this.planes[b]).x * d, (this.planes[b]).y * d, (this.planes[b]).z * d, (this.planes[b]).w * d);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  843 */     for (b = 0; b < this.verts.length; b++) {
/*  844 */       paramTransform3D.transform(this.verts[b]);
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
/*      */   boolean intersect(Point3d paramPoint3d1, Vector3d paramVector3d, Point3d paramPoint3d2) {
/*  862 */     if (this.boundsIsEmpty) {
/*  863 */       return false;
/*      */     }
/*      */     
/*  866 */     if (this.boundsIsInfinite) {
/*  867 */       paramPoint3d2.x = paramPoint3d1.x;
/*  868 */       paramPoint3d2.y = paramPoint3d1.y;
/*  869 */       paramPoint3d2.z = paramPoint3d1.z;
/*  870 */       return true;
/*      */     } 
/*      */     
/*  873 */     double d1 = 1.0D / Math.sqrt(paramVector3d.x * paramVector3d.x + paramVector3d.y * paramVector3d.y + paramVector3d.z * paramVector3d.z);
/*      */     
/*  875 */     double d2 = paramVector3d.x * d1;
/*  876 */     double d3 = paramVector3d.y * d1;
/*  877 */     double d4 = paramVector3d.z * d1;
/*      */ 
/*      */     
/*  880 */     for (byte b = 0; b < this.planes.length; b++) {
/*  881 */       double d6 = (this.planes[b]).x * d2 + (this.planes[b]).y * d3 + (this.planes[b]).z * d4;
/*  882 */       double d5 = -((this.planes[b]).x * paramPoint3d1.x + (this.planes[b]).y * paramPoint3d1.y + (this.planes[b]).z * paramPoint3d1.z + (this.planes[b]).w);
/*      */       
/*  884 */       if (d6 != 0.0D) {
/*  885 */         double d = d5 / d6;
/*      */         
/*  887 */         if (d >= 0.0D) {
/*      */           
/*  889 */           double d7 = paramPoint3d1.x + d2 * d;
/*  890 */           double d8 = paramPoint3d1.y + d3 * d;
/*  891 */           double d9 = paramPoint3d1.z + d4 * d;
/*      */           
/*  893 */           if (pointInPolytope(d7, d8, d9)) {
/*  894 */             paramPoint3d2.x = d7;
/*  895 */             paramPoint3d2.y = d8;
/*  896 */             paramPoint3d2.z = d9;
/*  897 */             return true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  903 */     return false;
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
/*      */   boolean intersect(Point3d paramPoint3d, Vector3d paramVector3d, Point4d paramPoint4d) {
/*  918 */     if (this.boundsIsEmpty) {
/*  919 */       return false;
/*      */     }
/*      */     
/*  922 */     if (this.boundsIsInfinite) {
/*  923 */       paramPoint4d.x = paramPoint3d.x;
/*  924 */       paramPoint4d.y = paramPoint3d.y;
/*  925 */       paramPoint4d.z = paramPoint3d.z;
/*  926 */       paramPoint4d.w = 0.0D;
/*  927 */       return true;
/*      */     } 
/*      */     
/*  930 */     double d1 = 1.0D / Math.sqrt(paramVector3d.x * paramVector3d.x + paramVector3d.y * paramVector3d.y + paramVector3d.z * paramVector3d.z);
/*      */     
/*  932 */     double d2 = paramVector3d.x * d1;
/*  933 */     double d3 = paramVector3d.y * d1;
/*  934 */     double d4 = paramVector3d.z * d1;
/*      */     
/*  936 */     for (byte b = 0; b < this.planes.length; b++) {
/*  937 */       double d6 = (this.planes[b]).x * d2 + (this.planes[b]).y * d3 + (this.planes[b]).z * d4;
/*  938 */       double d5 = -((this.planes[b]).x * paramPoint3d.x + (this.planes[b]).y * paramPoint3d.y + (this.planes[b]).z * paramPoint3d.z + (this.planes[b]).w);
/*      */ 
/*      */       
/*  941 */       if (d6 != 0.0D) {
/*  942 */         double d = d5 / d6;
/*      */         
/*  944 */         if (d >= 0.0D) {
/*      */           
/*  946 */           double d7 = paramPoint3d.x + d2 * d;
/*  947 */           double d8 = paramPoint3d.y + d3 * d;
/*  948 */           double d9 = paramPoint3d.z + d4 * d;
/*      */ 
/*      */           
/*  951 */           if (pointInPolytope(d7, d8, d9)) {
/*  952 */             paramPoint4d.x = d7;
/*  953 */             paramPoint4d.y = d8;
/*  954 */             paramPoint4d.z = d9;
/*  955 */             paramPoint4d.w = d;
/*  956 */             return true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  962 */     return false;
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
/*      */   boolean intersect(Point3d paramPoint3d, Point4d paramPoint4d) {
/*  975 */     if (this.boundsIsEmpty) {
/*  976 */       return false;
/*      */     }
/*      */     
/*  979 */     if (this.boundsIsInfinite) {
/*  980 */       paramPoint4d.x = paramPoint3d.x;
/*  981 */       paramPoint4d.y = paramPoint3d.y;
/*  982 */       paramPoint4d.z = paramPoint3d.z;
/*  983 */       paramPoint4d.w = 0.0D;
/*  984 */       return true;
/*      */     } 
/*      */     
/*  987 */     for (byte b = 0; b < this.planes.length; b++) {
/*  988 */       if (paramPoint3d.x * (this.planes[b]).x + paramPoint3d.y * (this.planes[b]).y + paramPoint3d.z * (this.planes[b]).z + (this.planes[b]).w > 0.0D)
/*      */       {
/*      */         
/*  991 */         return false;
/*      */       }
/*      */     } 
/*  994 */     return true;
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
/*      */   boolean intersect(Point3d paramPoint3d1, Point3d paramPoint3d2, Point4d paramPoint4d) {
/* 1011 */     if (this.boundsIsEmpty) {
/* 1012 */       return false;
/*      */     }
/*      */     
/* 1015 */     if (this.boundsIsInfinite) {
/* 1016 */       paramPoint4d.x = paramPoint3d1.x;
/* 1017 */       paramPoint4d.y = paramPoint3d1.y;
/* 1018 */       paramPoint4d.z = paramPoint3d1.z;
/* 1019 */       paramPoint4d.w = 0.0D;
/* 1020 */       return true;
/*      */     } 
/*      */     
/* 1023 */     Point3d point3d = new Point3d();
/*      */     
/* 1025 */     paramPoint3d2.x -= paramPoint3d1.x;
/* 1026 */     paramPoint3d2.y -= paramPoint3d1.y;
/* 1027 */     paramPoint3d2.z -= paramPoint3d1.z;
/*      */     
/* 1029 */     for (byte b = 0; b < this.planes.length; b++) {
/* 1030 */       double d2 = (this.planes[b]).x * point3d.x + (this.planes[b]).y * point3d.y + (this.planes[b]).z * point3d.z;
/*      */       
/* 1032 */       double d1 = -((this.planes[b]).x * paramPoint3d1.x + (this.planes[b]).y * paramPoint3d1.y + (this.planes[b]).z * paramPoint3d1.z + (this.planes[b]).w);
/*      */ 
/*      */       
/* 1035 */       if (d2 != 0.0D) {
/* 1036 */         double d = d1 / d2;
/*      */ 
/*      */ 
/*      */         
/* 1040 */         if (d >= 0.0D) {
/*      */           
/* 1042 */           double d3 = paramPoint3d1.x + point3d.x * d;
/* 1043 */           double d4 = paramPoint3d1.y + point3d.y * d;
/* 1044 */           double d5 = paramPoint3d1.z + point3d.z * d;
/*      */ 
/*      */           
/* 1047 */           if (pointInPolytope(d3, d4, d5))
/*      */           {
/*      */ 
/*      */             
/* 1051 */             if (d <= 1.0D) {
/* 1052 */               paramPoint4d.x = d3;
/* 1053 */               paramPoint4d.y = d4;
/* 1054 */               paramPoint4d.z = d5;
/* 1055 */               paramPoint4d.w = d;
/* 1056 */               return true;
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1063 */     return false;
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
/*      */   public boolean intersect(Point3d paramPoint3d, Vector3d paramVector3d) {
/* 1080 */     if (this.boundsIsEmpty) {
/* 1081 */       return false;
/*      */     }
/*      */     
/* 1084 */     if (this.boundsIsInfinite) {
/* 1085 */       return true;
/*      */     }
/*      */     
/* 1088 */     for (byte b = 0; b < this.planes.length; b++) {
/* 1089 */       double d2 = (this.planes[b]).x * paramVector3d.x + (this.planes[b]).y * paramVector3d.y + (this.planes[b]).z * paramVector3d.z;
/*      */       
/* 1091 */       double d1 = -((this.planes[b]).x * paramPoint3d.x + (this.planes[b]).y * paramPoint3d.y + (this.planes[b]).z * paramPoint3d.z + (this.planes[b]).w);
/*      */       
/* 1093 */       if (d2 != 0.0D) {
/* 1094 */         double d = d1 / d2;
/*      */         
/* 1096 */         if (d >= 0.0D) {
/*      */           
/* 1098 */           double d3 = paramPoint3d.x + paramVector3d.x * d;
/* 1099 */           double d4 = paramPoint3d.y + paramVector3d.y * d;
/* 1100 */           double d5 = paramPoint3d.z + paramVector3d.z * d;
/*      */           
/* 1102 */           if (pointInPolytope(d3, d4, d5)) {
/* 1103 */             return true;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1111 */     return false;
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
/* 1126 */   public boolean isEmpty() { return (this.boundsIsEmpty || this.nVerts <= 0); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean intersect(Point3d paramPoint3d) {
/* 1137 */     if (this.boundsIsEmpty) {
/* 1138 */       return false;
/*      */     }
/* 1140 */     if (this.boundsIsInfinite) {
/* 1141 */       return true;
/*      */     }
/*      */     
/* 1144 */     for (byte b = 0; b < this.planes.length; b++) {
/* 1145 */       if (paramPoint3d.x * (this.planes[b]).x + paramPoint3d.y * (this.planes[b]).y + paramPoint3d.z * (this.planes[b]).z + (this.planes[b]).w > 0.0D)
/*      */       {
/*      */         
/* 1148 */         return false;
/*      */       }
/*      */     } 
/* 1151 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1161 */   boolean intersect(Bounds paramBounds, Point4d paramPoint4d) { return intersect(paramBounds); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean intersect(Bounds paramBounds) {
/* 1171 */     if (paramBounds == null) {
/* 1172 */       return false;
/*      */     }
/*      */     
/* 1175 */     if (this.boundsIsEmpty || paramBounds.boundsIsEmpty) {
/* 1176 */       return false;
/*      */     }
/*      */     
/* 1179 */     if (this.boundsIsInfinite || paramBounds.boundsIsInfinite) {
/* 1180 */       return true;
/*      */     }
/*      */     
/* 1183 */     if (paramBounds.boundId == 2)
/* 1184 */       return intersect_ptope_sphere(this, (BoundingSphere)paramBounds); 
/* 1185 */     if (paramBounds.boundId == 1)
/* 1186 */       return intersect_ptope_abox(this, (BoundingBox)paramBounds); 
/* 1187 */     if (paramBounds.boundId == 4) {
/* 1188 */       return intersect_ptope_ptope(this, (BoundingPolytope)paramBounds);
/*      */     }
/* 1190 */     throw new IllegalArgumentException(J3dI18N.getString("BoundingPolytope6"));
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
/*      */   public boolean intersect(Bounds[] paramArrayOfBounds) {
/* 1204 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0) {
/* 1205 */       return false;
/*      */     }
/*      */     
/* 1208 */     if (this.boundsIsEmpty) {
/* 1209 */       return false;
/*      */     }
/*      */     
/* 1212 */     for (byte b = 0; b < paramArrayOfBounds.length; b++) {
/* 1213 */       if (paramArrayOfBounds[b] != null && !(paramArrayOfBounds[b]).boundsIsEmpty && (
/* 1214 */         this.boundsIsInfinite || (paramArrayOfBounds[b]).boundsIsInfinite)) {
/* 1215 */         return true;
/*      */       }
/* 1217 */       if ((paramArrayOfBounds[b]).boundId == 2) {
/* 1218 */         BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b];
/* 1219 */         double d2 = boundingSphere.radius;
/* 1220 */         d2 *= d2;
/* 1221 */         double d1 = boundingSphere.center.distanceSquared(boundingSphere.center);
/* 1222 */         if (d1 < d2) {
/* 1223 */           return true;
/*      */         }
/* 1225 */       } else if ((paramArrayOfBounds[b]).boundId == 1) {
/* 1226 */         if (intersect(paramArrayOfBounds[b])) return true; 
/* 1227 */       } else if ((paramArrayOfBounds[b]).boundId == 4) {
/* 1228 */         if (intersect(paramArrayOfBounds[b])) return true; 
/*      */       } else {
/* 1230 */         throw new IllegalArgumentException(J3dI18N.getString("BoundingPolytope7"));
/*      */       } 
/*      */     } 
/*      */     
/* 1234 */     return false;
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
/*      */   public boolean intersect(Bounds paramBounds, BoundingPolytope paramBoundingPolytope) {
/* 1246 */     if (paramBounds == null || this.boundsIsEmpty || paramBounds.boundsIsEmpty) {
/* 1247 */       paramBoundingPolytope.boundsIsEmpty = true;
/* 1248 */       paramBoundingPolytope.boundsIsInfinite = false;
/* 1249 */       paramBoundingPolytope.computeAllVerts();
/* 1250 */       return false;
/*      */     } 
/* 1252 */     if (this.boundsIsInfinite && !paramBounds.boundsIsInfinite) {
/* 1253 */       paramBoundingPolytope.set(paramBounds);
/* 1254 */       return true;
/*      */     } 
/* 1256 */     if (!this.boundsIsInfinite && paramBounds.boundsIsInfinite) {
/* 1257 */       paramBoundingPolytope.set(this);
/* 1258 */       return true;
/*      */     } 
/* 1260 */     if (this.boundsIsInfinite && paramBounds.boundsIsInfinite) {
/* 1261 */       paramBoundingPolytope.set(this);
/* 1262 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1266 */     BoundingBox boundingBox = new BoundingBox();
/*      */     
/* 1268 */     if (paramBounds.boundId == 2) {
/* 1269 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/* 1270 */       if (intersect(boundingSphere)) {
/* 1271 */         BoundingBox boundingBox1 = new BoundingBox(boundingSphere);
/* 1272 */         BoundingBox boundingBox2 = new BoundingBox(this);
/* 1273 */         boundingBox2.intersect(boundingBox1, boundingBox);
/* 1274 */         paramBoundingPolytope.set(boundingBox);
/* 1275 */         return true;
/*      */       } 
/* 1277 */     } else if (paramBounds.boundId == 1) {
/* 1278 */       BoundingBox boundingBox1 = (BoundingBox)paramBounds;
/* 1279 */       if (intersect(boundingBox1)) {
/* 1280 */         BoundingBox boundingBox2 = new BoundingBox(this);
/* 1281 */         boundingBox2.intersect(boundingBox1, boundingBox);
/* 1282 */         paramBoundingPolytope.set(boundingBox);
/* 1283 */         return true;
/*      */       }
/*      */     
/* 1286 */     } else if (paramBounds.boundId == 4) {
/* 1287 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/* 1288 */       if (intersect(boundingPolytope)) {
/* 1289 */         Vector4d[] arrayOfVector4d = new Vector4d[this.planes.length + boundingPolytope.planes.length]; int i;
/* 1290 */         for (i = 0; i < this.planes.length; i++) {
/* 1291 */           arrayOfVector4d[i] = new Vector4d(this.planes[i]);
/*      */         }
/* 1293 */         for (i = 0; i < boundingPolytope.planes.length; i++) {
/* 1294 */           arrayOfVector4d[this.planes.length + i] = new Vector4d(boundingPolytope.planes[i]);
/*      */         }
/* 1296 */         BoundingPolytope boundingPolytope1 = new BoundingPolytope(arrayOfVector4d);
/*      */         
/* 1298 */         paramBoundingPolytope.set(boundingPolytope1);
/* 1299 */         return true;
/*      */       } 
/*      */     } else {
/*      */       
/* 1303 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingPolytope8"));
/*      */     } 
/*      */     
/* 1306 */     paramBoundingPolytope.boundsIsEmpty = true;
/* 1307 */     paramBoundingPolytope.boundsIsInfinite = false;
/* 1308 */     paramBoundingPolytope.computeAllVerts();
/*      */     
/* 1310 */     return false;
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
/*      */   public boolean intersect(Bounds[] paramArrayOfBounds, BoundingPolytope paramBoundingPolytope) {
/* 1322 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0 || this.boundsIsEmpty) {
/* 1323 */       paramBoundingPolytope.boundsIsEmpty = true;
/* 1324 */       paramBoundingPolytope.boundsIsInfinite = false;
/* 1325 */       paramBoundingPolytope.computeAllVerts();
/* 1326 */       return false;
/*      */     } 
/*      */     
/* 1329 */     int i = 0;
/*      */     
/* 1331 */     while (paramArrayOfBounds[i] == null && i < paramArrayOfBounds.length) {
/* 1332 */       i++;
/*      */     }
/*      */     
/* 1335 */     if (i >= paramArrayOfBounds.length) {
/* 1336 */       paramBoundingPolytope.boundsIsEmpty = true;
/* 1337 */       paramBoundingPolytope.boundsIsInfinite = false;
/* 1338 */       paramBoundingPolytope.computeAllVerts();
/* 1339 */       return false;
/*      */     } 
/*      */     
/* 1342 */     boolean bool = false;
/* 1343 */     BoundingBox boundingBox = new BoundingBox();
/*      */     
/* 1345 */     for (i = 0; i < paramArrayOfBounds.length; i++) {
/* 1346 */       if (paramArrayOfBounds[i] != null && !(paramArrayOfBounds[i]).boundsIsEmpty) {
/* 1347 */         if ((paramArrayOfBounds[i]).boundId == 2) {
/* 1348 */           BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[i];
/* 1349 */           if (intersect(boundingSphere)) {
/* 1350 */             BoundingBox boundingBox1 = new BoundingBox(boundingSphere);
/* 1351 */             BoundingBox boundingBox2 = new BoundingBox(this);
/* 1352 */             boundingBox2.intersect(boundingBox1, boundingBox);
/* 1353 */             if (bool) {
/* 1354 */               paramBoundingPolytope.combine(boundingBox);
/*      */             } else {
/* 1356 */               paramBoundingPolytope.set(boundingBox);
/* 1357 */               bool = true;
/*      */             } 
/*      */           } 
/* 1360 */         } else if ((paramArrayOfBounds[i]).boundId == 1) {
/* 1361 */           BoundingBox boundingBox1 = (BoundingBox)paramArrayOfBounds[i];
/* 1362 */           if (intersect(boundingBox1)) {
/* 1363 */             BoundingBox boundingBox2 = new BoundingBox(this);
/* 1364 */             boundingBox2.intersect(boundingBox1, boundingBox);
/* 1365 */             if (bool) {
/* 1366 */               paramBoundingPolytope.combine(boundingBox);
/*      */             } else {
/* 1368 */               paramBoundingPolytope.set(boundingBox);
/* 1369 */               bool = true;
/*      */             }
/*      */           
/*      */           }
/*      */         
/* 1374 */         } else if ((paramArrayOfBounds[i]).boundId == 4) {
/* 1375 */           BoundingPolytope boundingPolytope = (BoundingPolytope)paramArrayOfBounds[i];
/* 1376 */           if (intersect(boundingPolytope)) {
/* 1377 */             Vector4d[] arrayOfVector4d = new Vector4d[this.planes.length + boundingPolytope.planes.length];
/* 1378 */             for (i = 0; i < this.planes.length; i++) {
/* 1379 */               arrayOfVector4d[i] = new Vector4d(this.planes[i]);
/*      */             }
/* 1381 */             for (i = 0; i < boundingPolytope.planes.length; i++) {
/* 1382 */               arrayOfVector4d[this.planes.length + i] = new Vector4d(boundingPolytope.planes[i]);
/*      */             }
/* 1384 */             BoundingPolytope boundingPolytope1 = new BoundingPolytope(arrayOfVector4d);
/* 1385 */             if (bool) {
/* 1386 */               paramBoundingPolytope.combine(boundingPolytope1);
/*      */             } else {
/* 1388 */               paramBoundingPolytope.set(boundingPolytope1);
/* 1389 */               bool = true;
/*      */             } 
/*      */           } 
/*      */         } else {
/* 1393 */           throw new IllegalArgumentException(J3dI18N.getString("BoundingPolytope8"));
/*      */         } 
/*      */       }
/* 1396 */       if (paramBoundingPolytope.boundsIsInfinite) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */     
/* 1401 */     if (!bool) {
/* 1402 */       paramBoundingPolytope.boundsIsEmpty = true;
/* 1403 */       paramBoundingPolytope.boundsIsInfinite = false;
/* 1404 */       paramBoundingPolytope.computeAllVerts();
/*      */     } 
/* 1406 */     return bool;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Bounds closestIntersection(Bounds[] paramArrayOfBounds) {
/* 1416 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0) {
/* 1417 */       return null;
/*      */     }
/*      */     
/* 1420 */     if (this.boundsIsEmpty) {
/* 1421 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1425 */     boolean bool = false;
/*      */     
/* 1427 */     double d1 = Double.MAX_VALUE;
/* 1428 */     byte b2 = 0;
/* 1429 */     double d2 = 0.0D, d3 = 0.0D, d4 = 0.0D;
/*      */     
/* 1431 */     for (byte b1 = 0; b1 < paramArrayOfBounds.length; b1++) {
/* 1432 */       if (paramArrayOfBounds[b1] != null)
/*      */       {
/* 1434 */         if (intersect(paramArrayOfBounds[b1])) {
/* 1435 */           if (paramArrayOfBounds[b1] instanceof BoundingSphere) {
/* 1436 */             BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b1];
/* 1437 */             double d = Math.sqrt((this.centroid.x - boundingSphere.center.x) * (this.centroid.x - boundingSphere.center.x) + (this.centroid.y - boundingSphere.center.y) * (this.centroid.y - boundingSphere.center.y) + (this.centroid.z - boundingSphere.center.z) * (this.centroid.z - boundingSphere.center.z));
/*      */ 
/*      */             
/* 1440 */             boolean bool1 = true;
/* 1441 */             for (byte b = 0; b < this.planes.length; b++) {
/* 1442 */               if (boundingSphere.center.x * (this.planes[b]).x + boundingSphere.center.y * (this.planes[b]).y + boundingSphere.center.z * (this.planes[b]).z + (this.planes[b1]).w > 0.0D) {
/*      */ 
/*      */                 
/* 1445 */                 double d5 = boundingSphere.center.x * (this.planes[b]).x + boundingSphere.center.y * (this.planes[b]).y + boundingSphere.center.z * (this.planes[b]).z + (this.planes[b]).w;
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1450 */                 if (d5 > boundingSphere.radius) bool1 = false; 
/*      */               } 
/*      */             } 
/* 1453 */             if (bool1) {
/* 1454 */               if (!bool) {
/* 1455 */                 b2 = b1;
/* 1456 */                 d1 = d;
/* 1457 */                 bool = true;
/*      */               }
/* 1459 */               else if (d < d1) {
/* 1460 */                 b2 = b1;
/* 1461 */                 d1 = d;
/*      */               }
/*      */             
/* 1464 */             } else if (!bool && 
/* 1465 */               d < d1) {
/* 1466 */               b2 = b1;
/* 1467 */               d1 = d;
/*      */             }
/*      */           
/* 1470 */           } else if (paramArrayOfBounds[b1] instanceof BoundingBox) {
/* 1471 */             BoundingBox boundingBox = (BoundingBox)paramArrayOfBounds[b1];
/* 1472 */             d2 = (boundingBox.upper.x + boundingBox.lower.x) / 2.0D;
/* 1473 */             d3 = (boundingBox.upper.y + boundingBox.lower.y) / 2.0D;
/* 1474 */             d4 = (boundingBox.upper.z + boundingBox.lower.z) / 2.0D;
/* 1475 */             double d = Math.sqrt((this.centroid.x - d2) * (this.centroid.x - d2) + (this.centroid.y - d3) * (this.centroid.y - d3) + (this.centroid.z - d4) * (this.centroid.z - d4));
/*      */ 
/*      */             
/* 1478 */             boolean bool1 = true;
/* 1479 */             if (!pointInPolytope(boundingBox.upper.x, boundingBox.upper.y, boundingBox.upper.z)) bool1 = false; 
/* 1480 */             if (!pointInPolytope(boundingBox.upper.x, boundingBox.upper.y, boundingBox.lower.z)) bool1 = false; 
/* 1481 */             if (!pointInPolytope(boundingBox.upper.x, boundingBox.lower.y, boundingBox.upper.z)) bool1 = false; 
/* 1482 */             if (!pointInPolytope(boundingBox.upper.x, boundingBox.lower.y, boundingBox.lower.z)) bool1 = false; 
/* 1483 */             if (!pointInPolytope(boundingBox.lower.x, boundingBox.upper.y, boundingBox.upper.z)) bool1 = false; 
/* 1484 */             if (!pointInPolytope(boundingBox.lower.x, boundingBox.upper.y, boundingBox.lower.z)) bool1 = false; 
/* 1485 */             if (!pointInPolytope(boundingBox.lower.x, boundingBox.lower.y, boundingBox.upper.z)) bool1 = false; 
/* 1486 */             if (!pointInPolytope(boundingBox.lower.x, boundingBox.lower.y, boundingBox.lower.z)) bool1 = false;
/*      */             
/* 1488 */             if (bool1) {
/* 1489 */               if (!bool) {
/* 1490 */                 b2 = b1;
/* 1491 */                 d1 = d;
/* 1492 */                 bool = true;
/*      */               }
/* 1494 */               else if (d < d1) {
/* 1495 */                 b2 = b1;
/* 1496 */                 d1 = d;
/*      */               }
/*      */             
/* 1499 */             } else if (!bool && 
/* 1500 */               d < d1) {
/* 1501 */               b2 = b1;
/* 1502 */               d1 = d;
/*      */             }
/*      */           
/*      */           }
/* 1506 */           else if (paramArrayOfBounds[b1] instanceof BoundingPolytope) {
/* 1507 */             BoundingPolytope boundingPolytope = (BoundingPolytope)paramArrayOfBounds[b1];
/* 1508 */             double d = Math.sqrt((this.centroid.x - boundingPolytope.centroid.x) * (this.centroid.x - boundingPolytope.centroid.x) + (this.centroid.y - boundingPolytope.centroid.y) * (this.centroid.y - boundingPolytope.centroid.y) + (this.centroid.z - boundingPolytope.centroid.z) * (this.centroid.z - boundingPolytope.centroid.z));
/*      */ 
/*      */             
/* 1511 */             boolean bool1 = true;
/* 1512 */             for (byte b = 0; b < boundingPolytope.nVerts; b++) {
/* 1513 */               if (!pointInPolytope((boundingPolytope.verts[b]).x, (boundingPolytope.verts[b]).y, (boundingPolytope.verts[b]).z))
/* 1514 */                 bool1 = false; 
/*      */             } 
/* 1516 */             if (bool1) {
/* 1517 */               if (!bool) {
/* 1518 */                 b2 = b1;
/* 1519 */                 d1 = d;
/* 1520 */                 bool = true;
/*      */               }
/* 1522 */               else if (d < d1) {
/* 1523 */                 b2 = b1;
/* 1524 */                 d1 = d;
/*      */               }
/*      */             
/* 1527 */             } else if (!bool && 
/* 1528 */               d < d1) {
/* 1529 */               b2 = b1;
/* 1530 */               d1 = d;
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 1535 */             throw new IllegalArgumentException(J3dI18N.getString("BoundingPolytope10"));
/*      */           } 
/*      */         }
/*      */       }
/*      */     } 
/* 1540 */     return paramArrayOfBounds[b2];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1549 */     String str = new String("BoundingPolytope:\n Num Planes =" + this.planes.length);
/* 1550 */     for (byte b = 0; b < this.planes.length; b++) {
/* 1551 */       str = str + "\n" + (this.mag[b] * (this.planes[b]).x) + " " + (this.mag[b] * (this.planes[b]).y) + " " + (this.mag[b] * (this.planes[b]).z) + " " + (this.mag[b] * (this.planes[b]).w);
/*      */     }
/*      */ 
/*      */     
/* 1555 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeVertex(int paramInt1, int paramInt2, int paramInt3) {
/* 1561 */     double d1 = (this.planes[paramInt1]).x * (this.planes[paramInt2]).y * (this.planes[paramInt3]).z + (this.planes[paramInt1]).y * (this.planes[paramInt2]).z * (this.planes[paramInt3]).x + (this.planes[paramInt1]).z * (this.planes[paramInt2]).x * (this.planes[paramInt3]).y - (this.planes[paramInt1]).z * (this.planes[paramInt2]).y * (this.planes[paramInt3]).x - (this.planes[paramInt1]).y * (this.planes[paramInt2]).x * (this.planes[paramInt3]).z - (this.planes[paramInt1]).x * (this.planes[paramInt2]).z * (this.planes[paramInt3]).y;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1566 */     if (d1 * d1 < 1.0E-6D) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1571 */     d1 = 1.0D / d1;
/*      */     
/* 1573 */     double d2 = ((this.planes[paramInt2]).y * (this.planes[paramInt3]).z - (this.planes[paramInt2]).z * (this.planes[paramInt3]).y) * this.pDotN[paramInt1];
/* 1574 */     double d3 = ((this.planes[paramInt2]).z * (this.planes[paramInt3]).x - (this.planes[paramInt2]).x * (this.planes[paramInt3]).z) * this.pDotN[paramInt1];
/* 1575 */     double d4 = ((this.planes[paramInt2]).x * (this.planes[paramInt3]).y - (this.planes[paramInt2]).y * (this.planes[paramInt3]).x) * this.pDotN[paramInt1];
/*      */     
/* 1577 */     d2 += ((this.planes[paramInt3]).y * (this.planes[paramInt1]).z - (this.planes[paramInt3]).z * (this.planes[paramInt1]).y) * this.pDotN[paramInt2];
/* 1578 */     d3 += ((this.planes[paramInt3]).z * (this.planes[paramInt1]).x - (this.planes[paramInt3]).x * (this.planes[paramInt1]).z) * this.pDotN[paramInt2];
/* 1579 */     d4 += ((this.planes[paramInt3]).x * (this.planes[paramInt1]).y - (this.planes[paramInt3]).y * (this.planes[paramInt1]).x) * this.pDotN[paramInt2];
/*      */     
/* 1581 */     d2 += ((this.planes[paramInt1]).y * (this.planes[paramInt2]).z - (this.planes[paramInt1]).z * (this.planes[paramInt2]).y) * this.pDotN[paramInt3];
/* 1582 */     d3 += ((this.planes[paramInt1]).z * (this.planes[paramInt2]).x - (this.planes[paramInt1]).x * (this.planes[paramInt2]).z) * this.pDotN[paramInt3];
/* 1583 */     d4 += ((this.planes[paramInt1]).x * (this.planes[paramInt2]).y - (this.planes[paramInt1]).y * (this.planes[paramInt2]).x) * this.pDotN[paramInt3];
/*      */     
/* 1585 */     d2 *= d1;
/* 1586 */     d3 *= d1;
/* 1587 */     d4 *= d1;
/*      */     
/* 1589 */     if (pointInPolytope(d2, d3, d4)) {
/* 1590 */       if (this.nVerts >= this.verts.length) {
/* 1591 */         Point3d[] arrayOfPoint3d = new Point3d[this.nVerts << 1];
/* 1592 */         for (byte b = 0; b < this.nVerts; b++) {
/* 1593 */           arrayOfPoint3d[b] = this.verts[b];
/*      */         }
/* 1595 */         this.verts = arrayOfPoint3d;
/*      */       } 
/* 1597 */       this.verts[this.nVerts++] = new Point3d(d2, d3, d4);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeAllVerts() {
/* 1606 */     this.nVerts = 0;
/*      */     
/* 1608 */     if (this.boundsIsEmpty) {
/* 1609 */       this.verts = null;
/*      */       
/*      */       return;
/*      */     } 
/* 1613 */     this.verts = new Point3d[this.planes.length * this.planes.length];
/*      */     byte b1;
/* 1615 */     for (b1 = 0; b1 < this.planes.length; b1++) {
/* 1616 */       this.pDotN[b1] = -(this.planes[b1]).x * (this.planes[b1]).w * (this.planes[b1]).x - (this.planes[b1]).y * (this.planes[b1]).w * (this.planes[b1]).y - (this.planes[b1]).z * (this.planes[b1]).w * (this.planes[b1]).z;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1621 */     for (byte b2 = 0; b2 < this.planes.length - 2; b2++) {
/* 1622 */       for (byte b = b2 + true; b < this.planes.length - 1; b++) {
/* 1623 */         for (byte b3 = b + true; b3 < this.planes.length; b3++) {
/* 1624 */           computeVertex(b2, b, b3);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1630 */     double d3 = 0.0D, d2 = d3, d1 = d2;
/* 1631 */     Point3d[] arrayOfPoint3d = new Point3d[this.nVerts];
/*      */     
/* 1633 */     for (b1 = 0; b1 < this.nVerts; b1++) {
/* 1634 */       d1 += (this.verts[b1]).x;
/* 1635 */       d2 += (this.verts[b1]).y;
/* 1636 */       d3 += (this.verts[b1]).z;
/*      */       
/* 1638 */       arrayOfPoint3d[b1] = this.verts[b1];
/*      */     } 
/*      */     
/* 1641 */     this.verts = arrayOfPoint3d;
/*      */     
/* 1643 */     this.centroid.x = d1 / this.nVerts;
/* 1644 */     this.centroid.y = d2 / this.nVerts;
/* 1645 */     this.centroid.z = d3 / this.nVerts;
/*      */     
/* 1647 */     checkBoundsIsEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean pointInPolytope(double paramDouble1, double paramDouble2, double paramDouble3) {
/* 1653 */     for (byte b = 0; b < this.planes.length; b++) {
/* 1654 */       if (paramDouble1 * (this.planes[b]).x + paramDouble2 * (this.planes[b]).y + paramDouble3 * (this.planes[b]).z + (this.planes[b]).w > 1.0E-6D)
/*      */       {
/*      */         
/* 1657 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 1661 */     return true;
/*      */   }
/*      */ 
/*      */   
/* 1665 */   private void checkBoundsIsEmpty() { this.boundsIsEmpty = (this.planes.length < 4); }
/*      */ 
/*      */   
/*      */   private void initEmptyPolytope() {
/* 1669 */     this.planes = new Vector4d[6];
/* 1670 */     this.pDotN = new double[6];
/* 1671 */     this.mag = new double[6];
/* 1672 */     this.verts = new Point3d[this.planes.length * this.planes.length];
/* 1673 */     this.nVerts = 0;
/*      */     
/* 1675 */     this.planes[0] = new Vector4d(1.0D, 0.0D, 0.0D, -1.0D);
/* 1676 */     this.planes[1] = new Vector4d(-1.0D, 0.0D, 0.0D, -1.0D);
/* 1677 */     this.planes[2] = new Vector4d(0.0D, 1.0D, 0.0D, -1.0D);
/* 1678 */     this.planes[3] = new Vector4d(0.0D, -1.0D, 0.0D, -1.0D);
/* 1679 */     this.planes[4] = new Vector4d(0.0D, 0.0D, 1.0D, -1.0D);
/* 1680 */     this.planes[5] = new Vector4d(0.0D, 0.0D, -1.0D, -1.0D);
/* 1681 */     this.mag[0] = 1.0D;
/* 1682 */     this.mag[1] = 1.0D;
/* 1683 */     this.mag[2] = 1.0D;
/* 1684 */     this.mag[3] = 1.0D;
/* 1685 */     this.mag[4] = 1.0D;
/* 1686 */     this.mag[5] = 1.0D;
/*      */     
/* 1688 */     checkBoundsIsEmpty();
/*      */   }
/*      */ 
/*      */   
/* 1692 */   Point3d getCenter() { return this.centroid; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Bounds copy(Bounds paramBounds) {
/* 1703 */     if (paramBounds != null && this.boundId == paramBounds.boundId) {
/* 1704 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/* 1705 */       if (boundingPolytope.planes.length != this.planes.length) {
/* 1706 */         boundingPolytope.planes = new Vector4d[this.planes.length];
/*      */         byte b1;
/* 1708 */         for (b1 = 0; b1 < boundingPolytope.planes.length; b1++) {
/* 1709 */           boundingPolytope.planes[b1] = new Vector4d();
/*      */         }
/* 1711 */         boundingPolytope.mag = new double[this.planes.length];
/* 1712 */         boundingPolytope.pDotN = new double[this.planes.length];
/* 1713 */         boundingPolytope.verts = new Point3d[this.nVerts];
/* 1714 */         boundingPolytope.nVerts = this.nVerts;
/* 1715 */         for (b1 = 0; b1 < this.nVerts; b1++) {
/* 1716 */           boundingPolytope.verts[b1] = new Point3d(this.verts[b1]);
/*      */         }
/*      */       } 
/*      */       
/* 1720 */       for (byte b = 0; b < this.planes.length; b++) {
/* 1721 */         (boundingPolytope.planes[b]).x = (this.planes[b]).x;
/* 1722 */         (boundingPolytope.planes[b]).y = (this.planes[b]).y;
/* 1723 */         (boundingPolytope.planes[b]).z = (this.planes[b]).z;
/* 1724 */         (boundingPolytope.planes[b]).w = (this.planes[b]).w;
/* 1725 */         boundingPolytope.mag[b] = this.mag[b];
/*      */       } 
/*      */       
/* 1728 */       boundingPolytope.boundsIsEmpty = this.boundsIsEmpty;
/* 1729 */       boundingPolytope.boundsIsInfinite = this.boundsIsInfinite;
/* 1730 */       return boundingPolytope;
/*      */     } 
/*      */     
/* 1733 */     return (Bounds)clone();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1738 */   int getPickType() { return 8; }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\BoundingPolytope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */