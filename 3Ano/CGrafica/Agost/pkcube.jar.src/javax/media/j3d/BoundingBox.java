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
/*      */ public class BoundingBox
/*      */   extends Bounds
/*      */ {
/*      */   Point3d lower;
/*      */   Point3d upper;
/*      */   private Point3d centroid;
/*      */   private static final double EPS = 1.0E-8D;
/*      */   private BoundingSphere tmpSphere;
/*      */   private BoundingBox tmpBox;
/*      */   private BoundingPolytope tmpPolytope;
/*      */   private Point3d tmpP3d;
/*      */   
/*      */   public BoundingBox(Point3d paramPoint3d1, Point3d paramPoint3d2) {
/*   38 */     this.centroid = null;
/*      */ 
/*      */ 
/*      */     
/*   42 */     this.tmpSphere = null;
/*   43 */     this.tmpBox = null;
/*   44 */     this.tmpPolytope = null;
/*   45 */     this.tmpP3d = new Point3d();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   54 */     this.boundId = 1;
/*   55 */     this.lower = new Point3d(paramPoint3d1);
/*   56 */     this.upper = new Point3d(paramPoint3d2);
/*   57 */     updateBoundsStates();
/*      */   }
/*      */   
/*      */   public BoundingBox() {
/*      */     this.centroid = null;
/*      */     this.tmpSphere = null;
/*      */     this.tmpBox = null;
/*      */     this.tmpPolytope = null;
/*      */     this.tmpP3d = new Point3d();
/*   66 */     this.boundId = 1;
/*   67 */     this.lower = new Point3d(-1.0D, -1.0D, -1.0D);
/*   68 */     this.upper = new Point3d(1.0D, 1.0D, 1.0D);
/*   69 */     updateBoundsStates();
/*      */   }
/*      */   
/*      */   public BoundingBox(Bounds paramBounds) {
/*      */     this.centroid = null;
/*      */     this.tmpSphere = null;
/*      */     this.tmpBox = null;
/*      */     this.tmpPolytope = null;
/*      */     this.tmpP3d = new Point3d();
/*   78 */     this.boundId = 1;
/*   79 */     if (paramBounds == null) {
/*      */       
/*   81 */       this.lower = new Point3d(1.0D, 1.0D, 1.0D);
/*   82 */       this.upper = new Point3d(-1.0D, -1.0D, -1.0D);
/*      */     }
/*   84 */     else if (paramBounds.boundsIsInfinite) {
/*   85 */       this.lower = new Point3d(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
/*      */ 
/*      */       
/*   88 */       this.upper = new Point3d(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*      */ 
/*      */     
/*      */     }
/*   92 */     else if (paramBounds.boundId == 1) {
/*   93 */       BoundingBox boundingBox = (BoundingBox)paramBounds;
/*      */       
/*   95 */       this.lower = new Point3d(boundingBox.lower.x, boundingBox.lower.y, boundingBox.lower.z);
/*   96 */       this.upper = new Point3d(boundingBox.upper.x, boundingBox.upper.y, boundingBox.upper.z);
/*      */     
/*      */     }
/*   99 */     else if (paramBounds.boundId == 2) {
/*  100 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/*      */       
/*  102 */       this.lower = new Point3d(boundingSphere.center.x - boundingSphere.radius, boundingSphere.center.y - boundingSphere.radius, boundingSphere.center.z - boundingSphere.radius);
/*      */ 
/*      */ 
/*      */       
/*  106 */       this.upper = new Point3d(boundingSphere.center.x + boundingSphere.radius, boundingSphere.center.y + boundingSphere.radius, boundingSphere.center.z + boundingSphere.radius);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  111 */     else if (paramBounds.boundId == 4) {
/*  112 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/*  113 */       if (boundingPolytope.nVerts < 1) {
/*  114 */         this.lower = new Point3d(-1.0D, -1.0D, -1.0D);
/*  115 */         this.upper = new Point3d(1.0D, 1.0D, 1.0D);
/*      */       } else {
/*  117 */         this.lower = new Point3d((boundingPolytope.verts[0]).x, (boundingPolytope.verts[0]).y, (boundingPolytope.verts[0]).z);
/*      */         
/*  119 */         this.upper = new Point3d((boundingPolytope.verts[0]).x, (boundingPolytope.verts[0]).y, (boundingPolytope.verts[0]).z);
/*      */ 
/*      */         
/*  122 */         for (byte b = 1; b < boundingPolytope.nVerts; b++) {
/*  123 */           if ((boundingPolytope.verts[b]).x < this.lower.x)
/*  124 */             this.lower.x = (boundingPolytope.verts[b]).x; 
/*  125 */           if ((boundingPolytope.verts[b]).y < this.lower.y)
/*  126 */             this.lower.y = (boundingPolytope.verts[b]).y; 
/*  127 */           if ((boundingPolytope.verts[b]).z < this.lower.z)
/*  128 */             this.lower.z = (boundingPolytope.verts[b]).z; 
/*  129 */           if ((boundingPolytope.verts[b]).x > this.upper.x)
/*  130 */             this.upper.x = (boundingPolytope.verts[b]).x; 
/*  131 */           if ((boundingPolytope.verts[b]).y > this.upper.y)
/*  132 */             this.upper.y = (boundingPolytope.verts[b]).y; 
/*  133 */           if ((boundingPolytope.verts[b]).z > this.upper.z) {
/*  134 */             this.upper.z = (boundingPolytope.verts[b]).z;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } else {
/*  139 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingBox0"));
/*      */     } 
/*      */     
/*  142 */     updateBoundsStates();
/*      */   }
/*      */   public BoundingBox(Bounds[] paramArrayOfBounds) {
/*      */     this.centroid = null;
/*      */     this.tmpSphere = null;
/*      */     this.tmpBox = null;
/*      */     this.tmpPolytope = null;
/*      */     this.tmpP3d = new Point3d();
/*  150 */     byte b = 0;
/*      */     
/*  152 */     this.upper = new Point3d();
/*  153 */     this.lower = new Point3d();
/*  154 */     this.boundId = 1;
/*      */     
/*  156 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0) {
/*      */       
/*  158 */       this.lower = new Point3d(1.0D, 1.0D, 1.0D);
/*  159 */       this.upper = new Point3d(-1.0D, -1.0D, -1.0D);
/*  160 */       updateBoundsStates();
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  165 */     while (paramArrayOfBounds[b] == null && b < paramArrayOfBounds.length) {
/*  166 */       b++;
/*      */     }
/*      */     
/*  169 */     if (b >= paramArrayOfBounds.length) {
/*      */       
/*  171 */       this.lower = new Point3d(1.0D, 1.0D, 1.0D);
/*  172 */       this.upper = new Point3d(-1.0D, -1.0D, -1.0D);
/*  173 */       updateBoundsStates();
/*      */       
/*      */       return;
/*      */     } 
/*  177 */     set(paramArrayOfBounds[b++]);
/*  178 */     if (this.boundsIsInfinite)
/*      */       return; 
/*      */     while (true) {
/*  181 */       if (b < paramArrayOfBounds.length) {
/*  182 */         if (paramArrayOfBounds[b] == null || 
/*  183 */           (paramArrayOfBounds[b]).boundsIsEmpty)
/*  184 */           continue;  if ((paramArrayOfBounds[b]).boundsIsInfinite) {
/*  185 */           this.lower.x = this.lower.y = this.lower.z = Double.NEGATIVE_INFINITY;
/*  186 */           this.upper.x = this.upper.y = this.upper.z = Double.POSITIVE_INFINITY;
/*      */         } else {
/*      */           
/*  189 */           if ((paramArrayOfBounds[b]).boundId == 1) {
/*  190 */             BoundingBox boundingBox = (BoundingBox)paramArrayOfBounds[b];
/*      */             
/*  192 */             if (this.lower.x > boundingBox.lower.x) this.lower.x = boundingBox.lower.x; 
/*  193 */             if (this.lower.y > boundingBox.lower.y) this.lower.y = boundingBox.lower.y; 
/*  194 */             if (this.lower.z > boundingBox.lower.z) this.lower.z = boundingBox.lower.z; 
/*  195 */             if (this.upper.x < boundingBox.upper.x) this.upper.x = boundingBox.upper.x; 
/*  196 */             if (this.upper.y < boundingBox.upper.y) this.upper.y = boundingBox.upper.y; 
/*  197 */             if (this.upper.z < boundingBox.upper.z) this.upper.z = boundingBox.upper.z;
/*      */           
/*      */           }
/*  200 */           else if ((paramArrayOfBounds[b]).boundId == 2) {
/*  201 */             BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b];
/*  202 */             if (this.lower.x > boundingSphere.center.x - boundingSphere.radius)
/*  203 */               boundingSphere.center.x -= boundingSphere.radius; 
/*  204 */             if (this.lower.y > boundingSphere.center.y - boundingSphere.radius)
/*  205 */               boundingSphere.center.y -= boundingSphere.radius; 
/*  206 */             if (this.lower.z > boundingSphere.center.z - boundingSphere.radius)
/*  207 */               boundingSphere.center.z -= boundingSphere.radius; 
/*  208 */             if (this.upper.x < boundingSphere.center.x + boundingSphere.radius)
/*  209 */               boundingSphere.center.x += boundingSphere.radius; 
/*  210 */             if (this.upper.y < boundingSphere.center.y + boundingSphere.radius)
/*  211 */               boundingSphere.center.y += boundingSphere.radius; 
/*  212 */             if (this.upper.z < boundingSphere.center.z + boundingSphere.radius) {
/*  213 */               boundingSphere.center.z += boundingSphere.radius;
/*      */             }
/*  215 */           } else if ((paramArrayOfBounds[b]).boundId == 4) {
/*  216 */             BoundingPolytope boundingPolytope = (BoundingPolytope)paramArrayOfBounds[b];
/*      */             
/*  218 */             for (b = 0; b < boundingPolytope.nVerts; b++) {
/*  219 */               if ((boundingPolytope.verts[b]).x < this.lower.x)
/*  220 */                 this.lower.x = (boundingPolytope.verts[b]).x; 
/*  221 */               if ((boundingPolytope.verts[b]).y < this.lower.y)
/*  222 */                 this.lower.y = (boundingPolytope.verts[b]).y; 
/*  223 */               if ((boundingPolytope.verts[b]).z < this.lower.z)
/*  224 */                 this.lower.z = (boundingPolytope.verts[b]).z; 
/*  225 */               if ((boundingPolytope.verts[b]).x > this.upper.x)
/*  226 */                 this.upper.x = (boundingPolytope.verts[b]).x; 
/*  227 */               if ((boundingPolytope.verts[b]).y > this.upper.y)
/*  228 */                 this.upper.y = (boundingPolytope.verts[b]).y; 
/*  229 */               if ((boundingPolytope.verts[b]).z > this.upper.z) {
/*  230 */                 this.upper.z = (boundingPolytope.verts[b]).z;
/*      */               }
/*      */             } 
/*      */           } else {
/*  234 */             throw new IllegalArgumentException(J3dI18N.getString("BoundingBox1"));
/*      */           }  continue;
/*      */         } 
/*  237 */       }  updateBoundsStates();
/*      */       break;
/*      */       b++;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void getLower(Point3d paramPoint3d) {
/*  245 */     paramPoint3d.x = this.lower.x;
/*  246 */     paramPoint3d.y = this.lower.y;
/*  247 */     paramPoint3d.z = this.lower.z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLower(double paramDouble1, double paramDouble2, double paramDouble3) {
/*  257 */     this.lower.x = paramDouble1;
/*  258 */     this.lower.y = paramDouble2;
/*  259 */     this.lower.z = paramDouble3;
/*      */     
/*  261 */     updateBoundsStates();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLower(Point3d paramPoint3d) {
/*  270 */     this.lower.x = paramPoint3d.x;
/*  271 */     this.lower.y = paramPoint3d.y;
/*  272 */     this.lower.z = paramPoint3d.z;
/*      */     
/*  274 */     updateBoundsStates();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getUpper(Point3d paramPoint3d) {
/*  282 */     paramPoint3d.x = this.upper.x;
/*  283 */     paramPoint3d.y = this.upper.y;
/*  284 */     paramPoint3d.z = this.upper.z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUpper(double paramDouble1, double paramDouble2, double paramDouble3) {
/*  294 */     this.upper.x = paramDouble1;
/*  295 */     this.upper.y = paramDouble2;
/*  296 */     this.upper.z = paramDouble3;
/*      */     
/*  298 */     updateBoundsStates();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUpper(Point3d paramPoint3d) {
/*  306 */     this.upper.x = paramPoint3d.x;
/*  307 */     this.upper.y = paramPoint3d.y;
/*  308 */     this.upper.z = paramPoint3d.z;
/*      */     
/*  310 */     updateBoundsStates();
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
/*      */   public void set(Bounds paramBounds) {
/*  322 */     this.lower.x = this.lower.y = this.lower.z = 1.0D;
/*  323 */     this.upper.x = this.upper.y = this.upper.z = -1.0D;
/*      */ 
/*      */     
/*  326 */     this.lower.x = this.lower.y = this.lower.z = Double.NEGATIVE_INFINITY;
/*  327 */     this.upper.x = this.upper.y = this.upper.z = Double.POSITIVE_INFINITY;
/*      */     
/*  329 */     if (paramBounds.boundId == 1) {
/*  330 */       BoundingBox boundingBox = (BoundingBox)paramBounds;
/*      */       
/*  332 */       this.lower.x = boundingBox.lower.x;
/*  333 */       this.lower.y = boundingBox.lower.y;
/*  334 */       this.lower.z = boundingBox.lower.z;
/*  335 */       this.upper.x = boundingBox.upper.x;
/*  336 */       this.upper.y = boundingBox.upper.y;
/*  337 */       this.upper.z = boundingBox.upper.z;
/*      */     }
/*  339 */     else if (paramBounds.boundId == 2) {
/*  340 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/*  341 */       boundingSphere.center.x -= boundingSphere.radius;
/*  342 */       boundingSphere.center.y -= boundingSphere.radius;
/*  343 */       boundingSphere.center.z -= boundingSphere.radius;
/*  344 */       boundingSphere.center.x += boundingSphere.radius;
/*  345 */       boundingSphere.center.y += boundingSphere.radius;
/*  346 */       boundingSphere.center.z += boundingSphere.radius;
/*      */     }
/*  348 */     else if (paramBounds.boundId == 4) {
/*  349 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/*  350 */       this.upper.x = (boundingPolytope.verts[0]).x;
/*  351 */       this.upper.y = (boundingPolytope.verts[0]).y;
/*  352 */       this.upper.z = (boundingPolytope.verts[0]).z;
/*      */       
/*  354 */       for (byte b = 1; b < boundingPolytope.nVerts; b++) {
/*  355 */         if ((boundingPolytope.verts[b]).x < this.lower.x) this.lower.x = (boundingPolytope.verts[b]).x; 
/*  356 */         if ((boundingPolytope.verts[b]).y < this.lower.y) this.lower.y = (boundingPolytope.verts[b]).y; 
/*  357 */         if ((boundingPolytope.verts[b]).z < this.lower.z) this.lower.z = (boundingPolytope.verts[b]).z; 
/*  358 */         if ((boundingPolytope.verts[b]).x > this.upper.x) this.upper.x = (boundingPolytope.verts[b]).x; 
/*  359 */         if ((boundingPolytope.verts[b]).y > this.upper.y) this.upper.y = (boundingPolytope.verts[b]).y; 
/*  360 */         if ((boundingPolytope.verts[b]).z > this.upper.z) this.upper.z = (boundingPolytope.verts[b]).z;
/*      */       
/*      */       } 
/*      */     } else {
/*  364 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingBox0"));
/*      */     } 
/*      */     
/*  367 */     updateBoundsStates();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  376 */   public Object clone() { return new BoundingBox(this.lower, this.upper); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  395 */       BoundingBox boundingBox = (BoundingBox)paramObject;
/*  396 */       return (this.lower.equals(boundingBox.lower) && this.upper.equals(boundingBox.upper));
/*      */     
/*      */     }
/*  399 */     catch (NullPointerException nullPointerException) {
/*  400 */       return false;
/*      */     }
/*  402 */     catch (ClassCastException classCastException) {
/*  403 */       return false;
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
/*  421 */     long l = 1L;
/*  422 */     l = 31L * l + HashCodeUtil.doubleToLongBits(this.lower.x);
/*  423 */     l = 31L * l + HashCodeUtil.doubleToLongBits(this.lower.y);
/*  424 */     l = 31L * l + HashCodeUtil.doubleToLongBits(this.lower.z);
/*  425 */     l = 31L * l + HashCodeUtil.doubleToLongBits(this.upper.x);
/*  426 */     l = 31L * l + HashCodeUtil.doubleToLongBits(this.upper.y);
/*  427 */     l = 31L * l + HashCodeUtil.doubleToLongBits(this.upper.z);
/*  428 */     return (int)(l ^ l >> 32);
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
/*      */   public void combine(Bounds paramBounds) {
/*  440 */     if (paramBounds == null || paramBounds.boundsIsEmpty || this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */     
/*  444 */     if (this.boundsIsEmpty || paramBounds.boundsIsInfinite) {
/*  445 */       set(paramBounds);
/*      */       
/*      */       return;
/*      */     } 
/*  449 */     if (paramBounds.boundId == 1) {
/*  450 */       BoundingBox boundingBox = (BoundingBox)paramBounds;
/*      */       
/*  452 */       if (this.lower.x > boundingBox.lower.x) this.lower.x = boundingBox.lower.x; 
/*  453 */       if (this.lower.y > boundingBox.lower.y) this.lower.y = boundingBox.lower.y; 
/*  454 */       if (this.lower.z > boundingBox.lower.z) this.lower.z = boundingBox.lower.z; 
/*  455 */       if (this.upper.x < boundingBox.upper.x) this.upper.x = boundingBox.upper.x; 
/*  456 */       if (this.upper.y < boundingBox.upper.y) this.upper.y = boundingBox.upper.y; 
/*  457 */       if (this.upper.z < boundingBox.upper.z) this.upper.z = boundingBox.upper.z;
/*      */     
/*      */     }
/*  460 */     else if (paramBounds.boundId == 2) {
/*  461 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/*  462 */       if (this.lower.x > boundingSphere.center.x - boundingSphere.radius)
/*  463 */         boundingSphere.center.x -= boundingSphere.radius; 
/*  464 */       if (this.lower.y > boundingSphere.center.y - boundingSphere.radius)
/*  465 */         boundingSphere.center.y -= boundingSphere.radius; 
/*  466 */       if (this.lower.z > boundingSphere.center.z - boundingSphere.radius)
/*  467 */         boundingSphere.center.z -= boundingSphere.radius; 
/*  468 */       if (this.upper.x < boundingSphere.center.x + boundingSphere.radius)
/*  469 */         boundingSphere.center.x += boundingSphere.radius; 
/*  470 */       if (this.upper.y < boundingSphere.center.y + boundingSphere.radius)
/*  471 */         boundingSphere.center.y += boundingSphere.radius; 
/*  472 */       if (this.upper.z < boundingSphere.center.z + boundingSphere.radius) {
/*  473 */         boundingSphere.center.z += boundingSphere.radius;
/*      */       }
/*      */     }
/*  476 */     else if (paramBounds.boundId == 4) {
/*  477 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/*      */       
/*  479 */       for (byte b = 1; b < boundingPolytope.nVerts; b++) {
/*  480 */         if ((boundingPolytope.verts[b]).x < this.lower.x) this.lower.x = (boundingPolytope.verts[b]).x; 
/*  481 */         if ((boundingPolytope.verts[b]).y < this.lower.y) this.lower.y = (boundingPolytope.verts[b]).y; 
/*  482 */         if ((boundingPolytope.verts[b]).z < this.lower.z) this.lower.z = (boundingPolytope.verts[b]).z; 
/*  483 */         if ((boundingPolytope.verts[b]).x > this.upper.x) this.upper.x = (boundingPolytope.verts[b]).x; 
/*  484 */         if ((boundingPolytope.verts[b]).y > this.upper.y) this.upper.y = (boundingPolytope.verts[b]).y; 
/*  485 */         if ((boundingPolytope.verts[b]).z > this.upper.z) this.upper.z = (boundingPolytope.verts[b]).z; 
/*      */       } 
/*      */     } else {
/*  488 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingBox3"));
/*      */     } 
/*      */     
/*  491 */     updateBoundsStates();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void combine(Bounds[] paramArrayOfBounds) {
/*  501 */     byte b = 0;
/*      */     
/*  503 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0 || this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  508 */     while (b < paramArrayOfBounds.length && (paramArrayOfBounds[b] == null || (paramArrayOfBounds[b]).boundsIsEmpty)) {
/*  509 */       b++;
/*      */     }
/*  511 */     if (b >= paramArrayOfBounds.length) {
/*      */       return;
/*      */     }
/*  514 */     if (this.boundsIsEmpty) {
/*  515 */       set(paramArrayOfBounds[b++]);
/*      */     }
/*  517 */     if (this.boundsIsInfinite)
/*      */       return; 
/*      */     while (true) {
/*  520 */       if (b < paramArrayOfBounds.length) {
/*  521 */         if (paramArrayOfBounds[b] == null || 
/*  522 */           (paramArrayOfBounds[b]).boundsIsEmpty)
/*  523 */           continue;  if ((paramArrayOfBounds[b]).boundsIsInfinite) {
/*  524 */           this.lower.x = this.lower.y = this.lower.z = Double.NEGATIVE_INFINITY;
/*  525 */           this.upper.x = this.upper.y = this.upper.z = Double.POSITIVE_INFINITY;
/*      */         } else {
/*      */           
/*  528 */           if ((paramArrayOfBounds[b]).boundId == 1) {
/*  529 */             BoundingBox boundingBox = (BoundingBox)paramArrayOfBounds[b];
/*      */             
/*  531 */             if (this.lower.x > boundingBox.lower.x) this.lower.x = boundingBox.lower.x; 
/*  532 */             if (this.lower.y > boundingBox.lower.y) this.lower.y = boundingBox.lower.y; 
/*  533 */             if (this.lower.z > boundingBox.lower.z) this.lower.z = boundingBox.lower.z; 
/*  534 */             if (this.upper.x < boundingBox.upper.x) this.upper.x = boundingBox.upper.x; 
/*  535 */             if (this.upper.y < boundingBox.upper.y) this.upper.y = boundingBox.upper.y; 
/*  536 */             if (this.upper.z < boundingBox.upper.z) this.upper.z = boundingBox.upper.z;
/*      */           
/*  538 */           } else if ((paramArrayOfBounds[b]).boundId == 2) {
/*  539 */             BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b];
/*  540 */             if (this.lower.x > boundingSphere.center.x - boundingSphere.radius)
/*  541 */               boundingSphere.center.x -= boundingSphere.radius; 
/*  542 */             if (this.lower.y > boundingSphere.center.y - boundingSphere.radius)
/*  543 */               boundingSphere.center.y -= boundingSphere.radius; 
/*  544 */             if (this.lower.z > boundingSphere.center.z - boundingSphere.radius)
/*  545 */               boundingSphere.center.z -= boundingSphere.radius; 
/*  546 */             if (this.upper.x < boundingSphere.center.x + boundingSphere.radius)
/*  547 */               boundingSphere.center.x += boundingSphere.radius; 
/*  548 */             if (this.upper.y < boundingSphere.center.y + boundingSphere.radius)
/*  549 */               boundingSphere.center.y += boundingSphere.radius; 
/*  550 */             if (this.upper.z < boundingSphere.center.z + boundingSphere.radius) {
/*  551 */               boundingSphere.center.z += boundingSphere.radius;
/*      */             }
/*  553 */           } else if ((paramArrayOfBounds[b]).boundId == 4) {
/*  554 */             BoundingPolytope boundingPolytope = (BoundingPolytope)paramArrayOfBounds[b];
/*  555 */             for (b = 1; b < boundingPolytope.nVerts; b++) {
/*  556 */               if ((boundingPolytope.verts[b]).x < this.lower.x) this.lower.x = (boundingPolytope.verts[b]).x; 
/*  557 */               if ((boundingPolytope.verts[b]).y < this.lower.y) this.lower.y = (boundingPolytope.verts[b]).y; 
/*  558 */               if ((boundingPolytope.verts[b]).z < this.lower.z) this.lower.z = (boundingPolytope.verts[b]).z; 
/*  559 */               if ((boundingPolytope.verts[b]).x > this.upper.x) this.upper.x = (boundingPolytope.verts[b]).x; 
/*  560 */               if ((boundingPolytope.verts[b]).y > this.upper.y) this.upper.y = (boundingPolytope.verts[b]).y; 
/*  561 */               if ((boundingPolytope.verts[b]).z > this.upper.z) this.upper.z = (boundingPolytope.verts[b]).z; 
/*      */             } 
/*      */           } else {
/*  564 */             throw new IllegalArgumentException(J3dI18N.getString("BoundingBox4"));
/*      */           }  continue;
/*      */         } 
/*      */       } 
/*  568 */       updateBoundsStates();
/*      */       break;
/*      */       b++;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void combine(Point3d paramPoint3d) {
/*  578 */     if (this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  583 */     this.lower.x = paramPoint3d.x;
/*  584 */     this.lower.y = paramPoint3d.y;
/*  585 */     this.lower.z = paramPoint3d.z;
/*      */     
/*  587 */     if (paramPoint3d.x > this.upper.x) this.upper.x = paramPoint3d.x; 
/*  588 */     if (paramPoint3d.y > this.upper.y) this.upper.y = paramPoint3d.y; 
/*  589 */     if (paramPoint3d.z > this.upper.z) this.upper.z = paramPoint3d.z;
/*      */     
/*  591 */     if (paramPoint3d.x < this.lower.x) this.lower.x = paramPoint3d.x; 
/*  592 */     if (paramPoint3d.y < this.lower.y) this.lower.y = paramPoint3d.y; 
/*  593 */     if (paramPoint3d.z < this.lower.z) this.lower.z = paramPoint3d.z;
/*      */ 
/*      */     
/*  596 */     updateBoundsStates();
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
/*      */   public void combine(Point3d[] paramArrayOfPoint3d) {
/*  609 */     if (this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */     
/*  613 */     if (this.boundsIsEmpty) {
/*  614 */       setUpper(paramArrayOfPoint3d[0]);
/*  615 */       setLower(paramArrayOfPoint3d[0]);
/*      */     } 
/*      */     
/*  618 */     for (byte b = 0; b < paramArrayOfPoint3d.length; b++) {
/*  619 */       if ((paramArrayOfPoint3d[b]).x > this.upper.x) this.upper.x = (paramArrayOfPoint3d[b]).x; 
/*  620 */       if ((paramArrayOfPoint3d[b]).y > this.upper.y) this.upper.y = (paramArrayOfPoint3d[b]).y; 
/*  621 */       if ((paramArrayOfPoint3d[b]).z > this.upper.z) this.upper.z = (paramArrayOfPoint3d[b]).z;
/*      */       
/*  623 */       if ((paramArrayOfPoint3d[b]).x < this.lower.x) this.lower.x = (paramArrayOfPoint3d[b]).x; 
/*  624 */       if ((paramArrayOfPoint3d[b]).y < this.lower.y) this.lower.y = (paramArrayOfPoint3d[b]).y; 
/*  625 */       if ((paramArrayOfPoint3d[b]).z < this.lower.z) this.lower.z = (paramArrayOfPoint3d[b]).z;
/*      */     
/*      */     } 
/*  628 */     updateBoundsStates();
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
/*  639 */     if (paramBounds == null || paramBounds.boundsIsEmpty) {
/*      */       
/*  641 */       this.lower.x = this.lower.y = this.lower.z = 1.0D;
/*  642 */       this.upper.x = this.upper.y = this.upper.z = -1.0D;
/*  643 */       updateBoundsStates();
/*      */       
/*      */       return;
/*      */     } 
/*  647 */     if (paramBounds.boundsIsInfinite) {
/*  648 */       this.lower.x = this.lower.y = this.lower.z = Double.NEGATIVE_INFINITY;
/*  649 */       this.upper.x = this.upper.y = this.upper.z = Double.POSITIVE_INFINITY;
/*  650 */       updateBoundsStates();
/*      */       
/*      */       return;
/*      */     } 
/*  654 */     if (paramBounds.boundId == 1) {
/*  655 */       if (this.tmpBox == null) {
/*  656 */         this.tmpBox = new BoundingBox((BoundingBox)paramBounds);
/*      */       } else {
/*  658 */         this.tmpBox.set((BoundingBox)paramBounds);
/*      */       } 
/*  660 */       this.tmpBox.transform(paramTransform3D);
/*  661 */       set(this.tmpBox);
/*      */     }
/*  663 */     else if (paramBounds.boundId == 2) {
/*  664 */       if (this.tmpSphere == null) {
/*  665 */         this.tmpSphere = new BoundingSphere((BoundingSphere)paramBounds);
/*      */       } else {
/*  667 */         this.tmpSphere.set((BoundingSphere)paramBounds);
/*      */       } 
/*  669 */       this.tmpSphere.transform(paramTransform3D);
/*  670 */       set(this.tmpSphere);
/*      */     }
/*  672 */     else if (paramBounds.boundId == 4) {
/*  673 */       if (this.tmpPolytope == null) {
/*  674 */         this.tmpPolytope = new BoundingPolytope((BoundingPolytope)paramBounds);
/*      */       } else {
/*      */         
/*  677 */         this.tmpPolytope.set((BoundingPolytope)paramBounds);
/*      */       } 
/*  679 */       this.tmpPolytope.transform(paramTransform3D);
/*  680 */       set(this.tmpPolytope);
/*      */     }
/*      */     else {
/*      */       
/*  684 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingBox5"));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void transform(Transform3D paramTransform3D) {
/*  694 */     if (this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */     
/*  698 */     double d1 = this.upper.x, d2 = this.upper.y, d3 = this.upper.z;
/*  699 */     double d4 = this.lower.x, d5 = this.lower.y, d6 = this.lower.z;
/*      */     
/*  701 */     this.tmpP3d.set(d1, d2, d3);
/*  702 */     paramTransform3D.transform(this.tmpP3d);
/*  703 */     this.upper.x = this.tmpP3d.x;
/*  704 */     this.upper.y = this.tmpP3d.y;
/*  705 */     this.upper.z = this.tmpP3d.z;
/*  706 */     this.lower.x = this.tmpP3d.x;
/*  707 */     this.lower.y = this.tmpP3d.y;
/*  708 */     this.lower.z = this.tmpP3d.z;
/*      */     
/*  710 */     this.tmpP3d.set(d4, d2, d3);
/*  711 */     paramTransform3D.transform(this.tmpP3d);
/*  712 */     if (this.tmpP3d.x > this.upper.x) this.upper.x = this.tmpP3d.x; 
/*  713 */     if (this.tmpP3d.y > this.upper.y) this.upper.y = this.tmpP3d.y; 
/*  714 */     if (this.tmpP3d.z > this.upper.z) this.upper.z = this.tmpP3d.z; 
/*  715 */     if (this.tmpP3d.x < this.lower.x) this.lower.x = this.tmpP3d.x; 
/*  716 */     if (this.tmpP3d.y < this.lower.y) this.lower.y = this.tmpP3d.y; 
/*  717 */     if (this.tmpP3d.z < this.lower.z) this.lower.z = this.tmpP3d.z;
/*      */     
/*  719 */     this.tmpP3d.set(d4, d5, d3);
/*  720 */     paramTransform3D.transform(this.tmpP3d);
/*  721 */     if (this.tmpP3d.x > this.upper.x) this.upper.x = this.tmpP3d.x; 
/*  722 */     if (this.tmpP3d.y > this.upper.y) this.upper.y = this.tmpP3d.y; 
/*  723 */     if (this.tmpP3d.z > this.upper.z) this.upper.z = this.tmpP3d.z; 
/*  724 */     if (this.tmpP3d.x < this.lower.x) this.lower.x = this.tmpP3d.x; 
/*  725 */     if (this.tmpP3d.y < this.lower.y) this.lower.y = this.tmpP3d.y; 
/*  726 */     if (this.tmpP3d.z < this.lower.z) this.lower.z = this.tmpP3d.z;
/*      */     
/*  728 */     this.tmpP3d.set(d1, d5, d3);
/*  729 */     paramTransform3D.transform(this.tmpP3d);
/*  730 */     if (this.tmpP3d.x > this.upper.x) this.upper.x = this.tmpP3d.x; 
/*  731 */     if (this.tmpP3d.y > this.upper.y) this.upper.y = this.tmpP3d.y; 
/*  732 */     if (this.tmpP3d.z > this.upper.z) this.upper.z = this.tmpP3d.z; 
/*  733 */     if (this.tmpP3d.x < this.lower.x) this.lower.x = this.tmpP3d.x; 
/*  734 */     if (this.tmpP3d.y < this.lower.y) this.lower.y = this.tmpP3d.y; 
/*  735 */     if (this.tmpP3d.z < this.lower.z) this.lower.z = this.tmpP3d.z;
/*      */     
/*  737 */     this.tmpP3d.set(d4, d2, d6);
/*  738 */     paramTransform3D.transform(this.tmpP3d);
/*  739 */     if (this.tmpP3d.x > this.upper.x) this.upper.x = this.tmpP3d.x; 
/*  740 */     if (this.tmpP3d.y > this.upper.y) this.upper.y = this.tmpP3d.y; 
/*  741 */     if (this.tmpP3d.z > this.upper.z) this.upper.z = this.tmpP3d.z; 
/*  742 */     if (this.tmpP3d.x < this.lower.x) this.lower.x = this.tmpP3d.x; 
/*  743 */     if (this.tmpP3d.y < this.lower.y) this.lower.y = this.tmpP3d.y; 
/*  744 */     if (this.tmpP3d.z < this.lower.z) this.lower.z = this.tmpP3d.z;
/*      */     
/*  746 */     this.tmpP3d.set(d1, d2, d6);
/*  747 */     paramTransform3D.transform(this.tmpP3d);
/*  748 */     if (this.tmpP3d.x > this.upper.x) this.upper.x = this.tmpP3d.x; 
/*  749 */     if (this.tmpP3d.y > this.upper.y) this.upper.y = this.tmpP3d.y; 
/*  750 */     if (this.tmpP3d.z > this.upper.z) this.upper.z = this.tmpP3d.z; 
/*  751 */     if (this.tmpP3d.x < this.lower.x) this.lower.x = this.tmpP3d.x; 
/*  752 */     if (this.tmpP3d.y < this.lower.y) this.lower.y = this.tmpP3d.y; 
/*  753 */     if (this.tmpP3d.z < this.lower.z) this.lower.z = this.tmpP3d.z;
/*      */     
/*  755 */     this.tmpP3d.set(d4, d5, d6);
/*  756 */     paramTransform3D.transform(this.tmpP3d);
/*  757 */     if (this.tmpP3d.x > this.upper.x) this.upper.x = this.tmpP3d.x; 
/*  758 */     if (this.tmpP3d.y > this.upper.y) this.upper.y = this.tmpP3d.y; 
/*  759 */     if (this.tmpP3d.z > this.upper.z) this.upper.z = this.tmpP3d.z; 
/*  760 */     if (this.tmpP3d.x < this.lower.x) this.lower.x = this.tmpP3d.x; 
/*  761 */     if (this.tmpP3d.y < this.lower.y) this.lower.y = this.tmpP3d.y; 
/*  762 */     if (this.tmpP3d.z < this.lower.z) this.lower.z = this.tmpP3d.z;
/*      */     
/*  764 */     this.tmpP3d.set(d1, d5, d6);
/*  765 */     paramTransform3D.transform(this.tmpP3d);
/*  766 */     if (this.tmpP3d.x > this.upper.x) this.upper.x = this.tmpP3d.x; 
/*  767 */     if (this.tmpP3d.y > this.upper.y) this.upper.y = this.tmpP3d.y; 
/*  768 */     if (this.tmpP3d.z > this.upper.z) this.upper.z = this.tmpP3d.z; 
/*  769 */     if (this.tmpP3d.x < this.lower.x) this.lower.x = this.tmpP3d.x; 
/*  770 */     if (this.tmpP3d.y < this.lower.y) this.lower.y = this.tmpP3d.y; 
/*  771 */     if (this.tmpP3d.z < this.lower.z) this.lower.z = this.tmpP3d.z;
/*      */   
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
/*      */   boolean intersect(Point3d paramPoint3d, Vector3d paramVector3d, Point4d paramPoint4d) {
/*  791 */     if (this.boundsIsEmpty) {
/*  792 */       return false;
/*      */     }
/*      */     
/*  795 */     if (this.boundsIsInfinite) {
/*  796 */       paramPoint4d.x = paramPoint3d.x;
/*  797 */       paramPoint4d.y = paramPoint3d.y;
/*  798 */       paramPoint4d.z = paramPoint3d.z;
/*  799 */       paramPoint4d.w = 0.0D;
/*  800 */       return true;
/*      */     } 
/*      */     
/*  803 */     double d7 = paramVector3d.x * paramVector3d.x + paramVector3d.y * paramVector3d.y + paramVector3d.z * paramVector3d.z;
/*      */ 
/*      */ 
/*      */     
/*  807 */     if (d7 == 0.0D) {
/*  808 */       return intersect(paramPoint3d, paramPoint4d);
/*      */     }
/*  810 */     double d3 = 1.0D / Math.sqrt(d7);
/*  811 */     double d4 = paramVector3d.x * d3;
/*  812 */     double d5 = paramVector3d.y * d3;
/*  813 */     double d6 = paramVector3d.z * d3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  821 */     double d1 = -1.7976931348623157E308D;
/*  822 */     double d2 = Double.MAX_VALUE;
/*      */     
/*  824 */     if (d4 == 0.0D) {
/*      */       
/*  826 */       if (paramPoint3d.x < this.lower.x || paramPoint3d.x > this.upper.x)
/*      */       {
/*  828 */         return false;
/*      */       }
/*      */     } else {
/*  831 */       double d10 = 1.0D / d4;
/*  832 */       double d8 = (this.lower.x - paramPoint3d.x) * d10;
/*  833 */       double d9 = (this.upper.x - paramPoint3d.x) * d10;
/*      */ 
/*      */       
/*  836 */       if (d8 > d9) {
/*  837 */         d1 = d9;
/*  838 */         d2 = d8;
/*      */       } else {
/*  840 */         d1 = d8;
/*  841 */         d2 = d9;
/*      */       } 
/*  843 */       if (d2 < 0.0D)
/*      */       {
/*  845 */         return false;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  850 */     if (d5 == 0.0D) {
/*      */       
/*  852 */       if (paramPoint3d.y < this.lower.y || paramPoint3d.y > this.upper.y)
/*      */       {
/*  854 */         return false;
/*      */       }
/*      */     } else {
/*  857 */       double d10 = 1.0D / d5;
/*      */       
/*  859 */       double d8 = (this.lower.y - paramPoint3d.y) * d10;
/*  860 */       double d9 = (this.upper.y - paramPoint3d.y) * d10;
/*      */       
/*  862 */       if (d8 > d9) {
/*  863 */         double d = d8;
/*  864 */         d8 = d9;
/*  865 */         d9 = d;
/*      */       } 
/*      */       
/*  868 */       if (d8 > d1) d1 = d8; 
/*  869 */       if (d9 < d2) d2 = d9;
/*      */       
/*  871 */       if (d2 < 0.0D || d1 > d2)
/*      */       {
/*  873 */         return false;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  879 */     if (d6 == 0.0D) {
/*      */       
/*  881 */       if (paramPoint3d.z < this.lower.z || paramPoint3d.z > this.upper.z)
/*      */       {
/*  883 */         return false;
/*      */       }
/*      */     } else {
/*  886 */       double d10 = 1.0D / d6;
/*  887 */       double d8 = (this.lower.z - paramPoint3d.z) * d10;
/*  888 */       double d9 = (this.upper.z - paramPoint3d.z) * d10;
/*      */       
/*  890 */       if (d8 > d9) {
/*  891 */         double d = d8;
/*  892 */         d8 = d9;
/*  893 */         d9 = d;
/*      */       } 
/*      */       
/*  896 */       if (d8 > d1) d1 = d8; 
/*  897 */       if (d9 < d2) d2 = d9;
/*      */       
/*  899 */       if (d2 < 0.0D || d1 > d2)
/*      */       {
/*  901 */         return false;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  906 */     if (d1 < 0.0D && d2 >= 0.0D) {
/*      */       
/*  908 */       paramPoint4d.x = paramPoint3d.x + d4 * d2;
/*  909 */       paramPoint4d.y = paramPoint3d.y + d5 * d2;
/*  910 */       paramPoint4d.z = paramPoint3d.z + d6 * d2;
/*  911 */       paramPoint4d.w = d2;
/*      */     } else {
/*      */       
/*  914 */       paramPoint4d.x = paramPoint3d.x + d4 * d1;
/*  915 */       paramPoint4d.y = paramPoint3d.y + d5 * d1;
/*  916 */       paramPoint4d.z = paramPoint3d.z + d6 * d1;
/*  917 */       paramPoint4d.w = d1;
/*      */     } 
/*      */     
/*  920 */     return true;
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
/*  933 */     if (this.boundsIsEmpty) {
/*  934 */       return false;
/*      */     }
/*      */     
/*  937 */     if (this.boundsIsInfinite) {
/*  938 */       paramPoint4d.x = paramPoint3d.x;
/*  939 */       paramPoint4d.y = paramPoint3d.y;
/*  940 */       paramPoint4d.z = paramPoint3d.z;
/*  941 */       paramPoint4d.w = 0.0D;
/*  942 */       return true;
/*      */     } 
/*      */     
/*  945 */     if (paramPoint3d.x <= this.upper.x && paramPoint3d.x >= this.lower.x && paramPoint3d.y <= this.upper.y && paramPoint3d.y >= this.lower.y && paramPoint3d.z <= this.upper.z && paramPoint3d.z >= this.lower.z) {
/*      */ 
/*      */       
/*  948 */       paramPoint4d.x = paramPoint3d.x;
/*  949 */       paramPoint4d.y = paramPoint3d.y;
/*  950 */       paramPoint4d.z = paramPoint3d.z;
/*  951 */       paramPoint4d.w = 0.0D;
/*  952 */       return true;
/*      */     } 
/*  954 */     return false;
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
/*      */   boolean intersect(Point3d paramPoint3d1, Point3d paramPoint3d2, Point4d paramPoint4d) {
/*  969 */     if (this.boundsIsEmpty) {
/*  970 */       return false;
/*      */     }
/*      */     
/*  973 */     if (this.boundsIsInfinite) {
/*  974 */       paramPoint4d.x = paramPoint3d1.x;
/*  975 */       paramPoint4d.y = paramPoint3d1.y;
/*  976 */       paramPoint4d.z = paramPoint3d1.z;
/*  977 */       paramPoint4d.w = 0.0D;
/*  978 */       return true;
/*      */     } 
/*      */     
/*  981 */     double d4 = paramPoint3d2.x - paramPoint3d1.x;
/*  982 */     double d5 = paramPoint3d2.y - paramPoint3d1.y;
/*  983 */     double d6 = paramPoint3d2.z - paramPoint3d1.z;
/*      */     
/*  985 */     double d7 = d4 * d4 + d5 * d5 + d6 * d6;
/*      */ 
/*      */     
/*  988 */     if (d7 == 0.0D) {
/*  989 */       return intersect(paramPoint3d1, paramPoint4d);
/*      */     }
/*  991 */     d7 = Math.sqrt(d7);
/*      */     
/*  993 */     double d3 = 1.0D / d7;
/*  994 */     d4 *= d3;
/*  995 */     d5 *= d3;
/*  996 */     d6 *= d3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1006 */     double d1 = -1.7976931348623157E308D;
/* 1007 */     double d2 = Double.MAX_VALUE;
/*      */     
/* 1009 */     if (d4 == 0.0D) {
/*      */       
/* 1011 */       if (paramPoint3d1.x < this.lower.x || paramPoint3d1.x > this.upper.x)
/*      */       {
/* 1013 */         return false;
/*      */       }
/*      */     } else {
/* 1016 */       double d10 = 1.0D / d4;
/* 1017 */       double d8 = (this.lower.x - paramPoint3d1.x) * d10;
/* 1018 */       double d9 = (this.upper.x - paramPoint3d1.x) * d10;
/*      */ 
/*      */       
/* 1021 */       if (d8 > d9) {
/* 1022 */         d1 = d9;
/* 1023 */         d2 = d8;
/*      */       } else {
/* 1025 */         d1 = d8;
/* 1026 */         d2 = d9;
/*      */       } 
/* 1028 */       if (d2 < 0.0D)
/*      */       {
/* 1030 */         return false;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1035 */     if (d5 == 0.0D) {
/*      */       
/* 1037 */       if (paramPoint3d1.y < this.lower.y || paramPoint3d1.y > this.upper.y)
/*      */       {
/* 1039 */         return false;
/*      */       }
/*      */     } else {
/* 1042 */       double d10 = 1.0D / d5;
/*      */       
/* 1044 */       double d8 = (this.lower.y - paramPoint3d1.y) * d10;
/* 1045 */       double d9 = (this.upper.y - paramPoint3d1.y) * d10;
/*      */       
/* 1047 */       if (d8 > d9) {
/* 1048 */         double d = d8;
/* 1049 */         d8 = d9;
/* 1050 */         d9 = d;
/*      */       } 
/*      */       
/* 1053 */       if (d8 > d1) d1 = d8; 
/* 1054 */       if (d9 < d2) d2 = d9;
/*      */       
/* 1056 */       if (d2 < 0.0D || d1 > d2)
/*      */       {
/* 1058 */         return false;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1064 */     if (d6 == 0.0D) {
/*      */       
/* 1066 */       if (paramPoint3d1.z < this.lower.z || paramPoint3d1.z > this.upper.z)
/*      */       {
/* 1068 */         return false;
/*      */       }
/*      */     } else {
/* 1071 */       double d10 = 1.0D / d6;
/* 1072 */       double d8 = (this.lower.z - paramPoint3d1.z) * d10;
/* 1073 */       double d9 = (this.upper.z - paramPoint3d1.z) * d10;
/*      */       
/* 1075 */       if (d8 > d9) {
/* 1076 */         double d = d8;
/* 1077 */         d8 = d9;
/* 1078 */         d9 = d;
/*      */       } 
/*      */       
/* 1081 */       if (d8 > d1) d1 = d8; 
/* 1082 */       if (d9 < d2) d2 = d9;
/*      */       
/* 1084 */       if (d2 < 0.0D || d1 > d2)
/*      */       {
/* 1086 */         return false;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1091 */     if (d1 < 0.0D && d2 >= 0.0D) {
/*      */       
/* 1093 */       paramPoint4d.x = paramPoint3d1.x + d4 * d2;
/* 1094 */       paramPoint4d.y = paramPoint3d1.y + d5 * d2;
/* 1095 */       paramPoint4d.z = paramPoint3d1.z + d6 * d2;
/* 1096 */       paramPoint4d.w = d2;
/*      */     } else {
/*      */       
/* 1099 */       if (d1 > d7)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1105 */         return false;
/*      */       }
/* 1107 */       paramPoint4d.x = paramPoint3d1.x + d4 * d1;
/* 1108 */       paramPoint4d.y = paramPoint3d1.y + d5 * d1;
/* 1109 */       paramPoint4d.z = paramPoint3d1.z + d6 * d1;
/*      */       
/* 1111 */       paramPoint4d.w = d1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1120 */     return true;
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
/*      */   public boolean intersect(Point3d paramPoint3d, Vector3d paramVector3d) {
/* 1132 */     if (this.boundsIsEmpty) {
/* 1133 */       return false;
/*      */     }
/*      */     
/* 1136 */     if (this.boundsIsInfinite) {
/* 1137 */       return true;
/*      */     }
/*      */     
/* 1140 */     Point3d point3d = new Point3d();
/* 1141 */     return intersect(paramPoint3d, paramVector3d, point3d);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersect(Point3d paramPoint3d1, Vector3d paramVector3d, Point3d paramPoint3d2) {
/* 1149 */     double d = 0.0D;
/*      */     
/* 1151 */     if (this.boundsIsEmpty) {
/* 1152 */       return false;
/*      */     }
/*      */     
/* 1155 */     if (this.boundsIsInfinite) {
/* 1156 */       paramPoint3d2.x = paramPoint3d1.x;
/* 1157 */       paramPoint3d2.y = paramPoint3d1.y;
/* 1158 */       paramPoint3d2.z = paramPoint3d1.z;
/* 1159 */       return true;
/*      */     } 
/*      */     
/* 1162 */     if (paramVector3d.x > 0.0D)
/* 1163 */       d = Math.max(d, (this.lower.x - paramPoint3d1.x) / paramVector3d.x); 
/* 1164 */     if (paramVector3d.x < 0.0D)
/* 1165 */       d = Math.max(d, (this.upper.x - paramPoint3d1.x) / paramVector3d.x); 
/* 1166 */     if (paramVector3d.y > 0.0D)
/* 1167 */       d = Math.max(d, (this.lower.y - paramPoint3d1.y) / paramVector3d.y); 
/* 1168 */     if (paramVector3d.y < 0.0D)
/* 1169 */       d = Math.max(d, (this.upper.y - paramPoint3d1.y) / paramVector3d.y); 
/* 1170 */     if (paramVector3d.z > 0.0D)
/* 1171 */       d = Math.max(d, (this.lower.z - paramPoint3d1.z) / paramVector3d.z); 
/* 1172 */     if (paramVector3d.z < 0.0D) {
/* 1173 */       d = Math.max(d, (this.upper.z - paramPoint3d1.z) / paramVector3d.z);
/*      */     }
/* 1175 */     paramPoint3d1.x += d * paramVector3d.x;
/* 1176 */     paramPoint3d1.y += d * paramVector3d.y;
/* 1177 */     paramPoint3d1.z += d * paramVector3d.z;
/*      */     
/* 1179 */     if (paramPoint3d2.x < this.lower.x - 1.0E-8D) return false; 
/* 1180 */     if (paramPoint3d2.x > this.upper.x + 1.0E-8D) return false; 
/* 1181 */     if (paramPoint3d2.y < this.lower.y - 1.0E-8D) return false; 
/* 1182 */     if (paramPoint3d2.y > this.upper.y + 1.0E-8D) return false; 
/* 1183 */     if (paramPoint3d2.z < this.lower.z - 1.0E-8D) return false; 
/* 1184 */     if (paramPoint3d2.z > this.upper.z + 1.0E-8D) return false;
/*      */     
/* 1186 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean intersect(Point3d paramPoint3d) {
/* 1197 */     if (this.boundsIsEmpty) {
/* 1198 */       return false;
/*      */     }
/* 1200 */     if (this.boundsIsInfinite) {
/* 1201 */       return true;
/*      */     }
/*      */     
/* 1204 */     if (paramPoint3d.x <= this.upper.x && paramPoint3d.x >= this.lower.x && paramPoint3d.y <= this.upper.y && paramPoint3d.y >= this.lower.y && paramPoint3d.z <= this.upper.z && paramPoint3d.z >= this.lower.z)
/*      */     {
/*      */       
/* 1207 */       return true;
/*      */     }
/* 1209 */     return false;
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
/* 1220 */   public boolean isEmpty() { return this.boundsIsEmpty; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1229 */   boolean intersect(Bounds paramBounds, Point4d paramPoint4d) { return intersect(paramBounds); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean intersect(Bounds paramBounds) {
/* 1239 */     if (paramBounds == null) {
/* 1240 */       return false;
/*      */     }
/*      */     
/* 1243 */     if (this.boundsIsEmpty || paramBounds.boundsIsEmpty) {
/* 1244 */       return false;
/*      */     }
/*      */     
/* 1247 */     if (this.boundsIsInfinite || paramBounds.boundsIsInfinite) {
/* 1248 */       return true;
/*      */     }
/*      */     
/* 1251 */     if (paramBounds.boundId == 1) {
/* 1252 */       BoundingBox boundingBox = (BoundingBox)paramBounds;
/*      */       
/* 1254 */       if (this.upper.x > boundingBox.lower.x && boundingBox.upper.x > this.lower.x && this.upper.y > boundingBox.lower.y && boundingBox.upper.y > this.lower.y && this.upper.z > boundingBox.lower.z && boundingBox.upper.z > this.lower.z)
/*      */       {
/*      */         
/* 1257 */         return true;
/*      */       }
/* 1259 */       return false;
/* 1260 */     }  if (paramBounds.boundId == 2) {
/* 1261 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/* 1262 */       double d1 = boundingSphere.radius * boundingSphere.radius;
/* 1263 */       double d2 = 0.0D;
/*      */       
/* 1265 */       if (boundingSphere.center.x < this.lower.x) {
/* 1266 */         d2 = (boundingSphere.center.x - this.lower.x) * (boundingSphere.center.x - this.lower.x);
/*      */       }
/* 1268 */       else if (boundingSphere.center.x > this.upper.x) {
/* 1269 */         d2 = (boundingSphere.center.x - this.upper.x) * (boundingSphere.center.x - this.upper.x);
/*      */       } 
/* 1271 */       if (boundingSphere.center.y < this.lower.y) {
/* 1272 */         d2 += (boundingSphere.center.y - this.lower.y) * (boundingSphere.center.y - this.lower.y);
/*      */       }
/* 1274 */       else if (boundingSphere.center.y > this.upper.y) {
/* 1275 */         d2 += (boundingSphere.center.y - this.upper.y) * (boundingSphere.center.y - this.upper.y);
/*      */       } 
/* 1277 */       if (boundingSphere.center.z < this.lower.z) {
/* 1278 */         d2 += (boundingSphere.center.z - this.lower.z) * (boundingSphere.center.z - this.lower.z);
/*      */       }
/* 1280 */       else if (boundingSphere.center.z > this.upper.z) {
/* 1281 */         d2 += (boundingSphere.center.z - this.upper.z) * (boundingSphere.center.z - this.upper.z);
/*      */       } 
/* 1283 */       if (d2 <= d1) {
/* 1284 */         return true;
/*      */       }
/* 1286 */       return false;
/* 1287 */     }  if (paramBounds.boundId == 4)
/*      */     {
/* 1289 */       return intersect_ptope_abox((BoundingPolytope)paramBounds, this);
/*      */     }
/* 1291 */     throw new IllegalArgumentException(J3dI18N.getString("BoundingBox6"));
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
/*      */   public boolean intersect(Bounds[] paramArrayOfBounds) {
/* 1306 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0) {
/* 1307 */       return false;
/*      */     }
/*      */     
/* 1310 */     if (this.boundsIsEmpty) {
/* 1311 */       return false;
/*      */     }
/*      */     
/* 1314 */     for (byte b = 0; b < paramArrayOfBounds.length; b++) {
/* 1315 */       if (paramArrayOfBounds[b] != null && !(paramArrayOfBounds[b]).boundsIsEmpty) {
/* 1316 */         if (this.boundsIsInfinite || (paramArrayOfBounds[b]).boundsIsInfinite) {
/* 1317 */           return true;
/*      */         }
/* 1319 */         if ((paramArrayOfBounds[b]).boundId == 1) {
/* 1320 */           BoundingBox boundingBox = (BoundingBox)paramArrayOfBounds[b];
/*      */           
/* 1322 */           if (this.upper.x > boundingBox.lower.x && boundingBox.upper.x > this.lower.x && this.upper.y > boundingBox.lower.y && boundingBox.upper.y > this.lower.y && this.upper.z > boundingBox.lower.z && boundingBox.upper.z > this.lower.z)
/*      */           {
/*      */             
/* 1325 */             return true;
/*      */           }
/* 1327 */         } else if ((paramArrayOfBounds[b]).boundId == 2) {
/* 1328 */           BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b];
/* 1329 */           double d1 = boundingSphere.radius * boundingSphere.radius;
/* 1330 */           double d2 = 0.0D;
/*      */           
/* 1332 */           if (boundingSphere.center.x < this.lower.x) {
/* 1333 */             d2 = (boundingSphere.center.x - this.lower.x) * (boundingSphere.center.x - this.lower.x);
/*      */           }
/* 1335 */           else if (boundingSphere.center.x > this.upper.x) {
/* 1336 */             d2 = (boundingSphere.center.x - this.upper.x) * (boundingSphere.center.x - this.upper.x);
/*      */           } 
/* 1338 */           if (boundingSphere.center.y < this.lower.y) {
/* 1339 */             d2 += (boundingSphere.center.y - this.lower.y) * (boundingSphere.center.y - this.lower.y);
/*      */           }
/* 1341 */           else if (boundingSphere.center.y > this.upper.y) {
/* 1342 */             d2 += (boundingSphere.center.y - this.upper.y) * (boundingSphere.center.y - this.upper.y);
/*      */           } 
/* 1344 */           if (boundingSphere.center.z < this.lower.z) {
/* 1345 */             d2 += (boundingSphere.center.z - this.lower.z) * (boundingSphere.center.z - this.lower.z);
/*      */           }
/* 1347 */           else if (boundingSphere.center.z > this.upper.z) {
/* 1348 */             d2 += (boundingSphere.center.z - this.upper.z) * (boundingSphere.center.z - this.upper.z);
/*      */           } 
/* 1350 */           if (d2 <= d1) {
/* 1351 */             return true;
/*      */           }
/*      */         }
/* 1354 */         else if ((paramArrayOfBounds[b]).boundId == 4 && 
/* 1355 */           intersect_ptope_abox((BoundingPolytope)paramArrayOfBounds[b], this)) {
/* 1356 */           return true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1363 */     return false;
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
/*      */   public boolean intersect(Bounds paramBounds, BoundingBox paramBoundingBox) {
/* 1375 */     if (paramBounds == null || this.boundsIsEmpty || paramBounds.boundsIsEmpty) {
/*      */       
/* 1377 */       paramBoundingBox.setLower(1.0D, 1.0D, 1.0D);
/* 1378 */       paramBoundingBox.setUpper(-1.0D, -1.0D, -1.0D);
/* 1379 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1383 */     if (this.boundsIsInfinite && !paramBounds.boundsIsInfinite) {
/* 1384 */       paramBoundingBox.set(paramBounds);
/* 1385 */       return true;
/*      */     } 
/* 1387 */     if (!this.boundsIsInfinite && paramBounds.boundsIsInfinite) {
/* 1388 */       paramBoundingBox.set(this);
/* 1389 */       return true;
/*      */     } 
/* 1391 */     if (this.boundsIsInfinite && paramBounds.boundsIsInfinite) {
/* 1392 */       paramBoundingBox.set(this);
/* 1393 */       return true;
/*      */     } 
/* 1395 */     if (paramBounds.boundId == 1) {
/* 1396 */       BoundingBox boundingBox = (BoundingBox)paramBounds;
/*      */       
/* 1398 */       if (this.upper.x > boundingBox.lower.x && boundingBox.upper.x > this.lower.x && this.upper.y > boundingBox.lower.y && boundingBox.upper.y > this.lower.y && this.upper.z > boundingBox.lower.z && boundingBox.upper.z > this.lower.z) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1403 */         if (this.upper.x > boundingBox.upper.x) {
/* 1404 */           paramBoundingBox.upper.x = boundingBox.upper.x;
/*      */         } else {
/* 1406 */           paramBoundingBox.upper.x = this.upper.x;
/*      */         } 
/* 1408 */         if (this.upper.y > boundingBox.upper.y) {
/* 1409 */           paramBoundingBox.upper.y = boundingBox.upper.y;
/*      */         } else {
/* 1411 */           paramBoundingBox.upper.y = this.upper.y;
/*      */         } 
/* 1413 */         if (this.upper.z > boundingBox.upper.z) {
/* 1414 */           paramBoundingBox.upper.z = boundingBox.upper.z;
/*      */         } else {
/* 1416 */           paramBoundingBox.upper.z = this.upper.z;
/*      */         } 
/* 1418 */         if (this.lower.x < boundingBox.lower.x) {
/* 1419 */           paramBoundingBox.lower.x = boundingBox.lower.x;
/*      */         } else {
/* 1421 */           paramBoundingBox.lower.x = this.lower.x;
/*      */         } 
/* 1423 */         if (this.lower.y < boundingBox.lower.y) {
/* 1424 */           paramBoundingBox.lower.y = boundingBox.lower.y;
/*      */         } else {
/* 1426 */           paramBoundingBox.lower.y = this.lower.y;
/*      */         } 
/* 1428 */         if (this.lower.z < boundingBox.lower.z) {
/* 1429 */           paramBoundingBox.lower.z = boundingBox.lower.z;
/*      */         } else {
/* 1431 */           paramBoundingBox.lower.z = this.lower.z;
/*      */         } 
/* 1433 */         paramBoundingBox.updateBoundsStates();
/* 1434 */         return true;
/*      */       } 
/*      */       
/* 1437 */       paramBoundingBox.setLower(1.0D, 1.0D, 1.0D);
/* 1438 */       paramBoundingBox.setUpper(-1.0D, -1.0D, -1.0D);
/* 1439 */       return false;
/*      */     } 
/*      */     
/* 1442 */     if (paramBounds.boundId == 2) {
/* 1443 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/* 1444 */       if (intersect(boundingSphere)) {
/* 1445 */         BoundingBox boundingBox = new BoundingBox(boundingSphere);
/* 1446 */         intersect(boundingBox, paramBoundingBox);
/* 1447 */         return true;
/*      */       } 
/*      */       
/* 1450 */       paramBoundingBox.setLower(1.0D, 1.0D, 1.0D);
/* 1451 */       paramBoundingBox.setUpper(-1.0D, -1.0D, -1.0D);
/* 1452 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1457 */     if (paramBounds.boundId == 4) {
/* 1458 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/* 1459 */       if (intersect(boundingPolytope)) {
/* 1460 */         BoundingBox boundingBox = new BoundingBox(boundingPolytope);
/* 1461 */         intersect(boundingBox, paramBoundingBox);
/* 1462 */         return true;
/*      */       } 
/*      */       
/* 1465 */       paramBoundingBox.setLower(1.0D, 1.0D, 1.0D);
/* 1466 */       paramBoundingBox.setUpper(-1.0D, -1.0D, -1.0D);
/* 1467 */       return false;
/*      */     } 
/*      */     
/* 1470 */     throw new IllegalArgumentException(J3dI18N.getString("BoundingBox7"));
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
/*      */   public boolean intersect(Bounds[] paramArrayOfBounds, BoundingBox paramBoundingBox) {
/* 1483 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0 || this.boundsIsEmpty) {
/*      */       
/* 1485 */       paramBoundingBox.setLower(1.0D, 1.0D, 1.0D);
/* 1486 */       paramBoundingBox.setUpper(-1.0D, -1.0D, -1.0D);
/* 1487 */       return false;
/*      */     } 
/*      */     
/* 1490 */     byte b = 0;
/*      */     
/* 1492 */     while (paramArrayOfBounds[b] == null && b < paramArrayOfBounds.length) {
/* 1493 */       b++;
/*      */     }
/*      */     
/* 1496 */     if (b >= paramArrayOfBounds.length) {
/*      */       
/* 1498 */       paramBoundingBox.setLower(1.0D, 1.0D, 1.0D);
/* 1499 */       paramBoundingBox.setUpper(-1.0D, -1.0D, -1.0D);
/* 1500 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1504 */     boolean bool = false;
/* 1505 */     BoundingBox boundingBox = new BoundingBox();
/*      */     
/* 1507 */     for (; b < paramArrayOfBounds.length; b++) {
/* 1508 */       if (paramArrayOfBounds[b] != null && !(paramArrayOfBounds[b]).boundsIsEmpty) {
/* 1509 */         if ((paramArrayOfBounds[b]).boundId == 1) {
/* 1510 */           BoundingBox boundingBox1 = (BoundingBox)paramArrayOfBounds[b];
/*      */           
/* 1512 */           if (this.upper.x > boundingBox1.lower.x && boundingBox1.upper.x > this.lower.x && this.upper.y > boundingBox1.lower.y && boundingBox1.upper.y > this.lower.y && this.upper.z > boundingBox1.lower.z && boundingBox1.upper.z > this.lower.z)
/*      */           {
/*      */ 
/*      */             
/* 1516 */             if (this.upper.x > boundingBox1.upper.x) {
/* 1517 */               paramBoundingBox.upper.x = boundingBox1.upper.x;
/*      */             } else {
/* 1519 */               paramBoundingBox.upper.x = this.upper.x;
/*      */             } 
/* 1521 */             if (this.upper.y > boundingBox1.upper.y) {
/* 1522 */               paramBoundingBox.upper.y = boundingBox1.upper.y;
/*      */             } else {
/* 1524 */               paramBoundingBox.upper.y = this.upper.y;
/*      */             } 
/* 1526 */             if (this.upper.z > boundingBox1.upper.z) {
/* 1527 */               paramBoundingBox.upper.z = boundingBox1.upper.z;
/*      */             } else {
/* 1529 */               paramBoundingBox.upper.z = this.upper.z;
/*      */             } 
/* 1531 */             if (this.lower.x < boundingBox1.lower.x) {
/* 1532 */               paramBoundingBox.lower.x = boundingBox1.lower.x;
/*      */             } else {
/* 1534 */               paramBoundingBox.lower.x = this.lower.x;
/*      */             } 
/* 1536 */             if (this.lower.y < boundingBox1.lower.y) {
/* 1537 */               paramBoundingBox.lower.y = boundingBox1.lower.y;
/*      */             } else {
/* 1539 */               paramBoundingBox.lower.y = this.lower.y;
/*      */             } 
/* 1541 */             if (this.lower.z < boundingBox1.lower.z) {
/* 1542 */               paramBoundingBox.lower.z = boundingBox1.lower.z;
/*      */             } else {
/* 1544 */               paramBoundingBox.lower.z = this.lower.z;
/* 1545 */             }  bool = true;
/* 1546 */             paramBoundingBox.updateBoundsStates();
/*      */           }
/*      */         
/* 1549 */         } else if ((paramArrayOfBounds[b]).boundId == 2) {
/* 1550 */           BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b];
/* 1551 */           if (intersect(boundingSphere)) {
/* 1552 */             BoundingBox boundingBox1 = new BoundingBox(boundingSphere);
/* 1553 */             intersect(boundingBox1, boundingBox);
/* 1554 */             if (bool) {
/* 1555 */               paramBoundingBox.combine(boundingBox);
/*      */             } else {
/* 1557 */               paramBoundingBox.set(boundingBox);
/* 1558 */               bool = true;
/*      */             }
/*      */           
/*      */           }
/*      */         
/* 1563 */         } else if ((paramArrayOfBounds[b]).boundId == 4) {
/* 1564 */           BoundingPolytope boundingPolytope = (BoundingPolytope)paramArrayOfBounds[b];
/* 1565 */           if (intersect(boundingPolytope)) {
/* 1566 */             BoundingBox boundingBox1 = new BoundingBox(boundingPolytope);
/* 1567 */             intersect(boundingBox1, boundingBox);
/* 1568 */             if (bool) {
/* 1569 */               paramBoundingBox.combine(boundingBox);
/*      */             } else {
/* 1571 */               paramBoundingBox.set(boundingBox);
/* 1572 */               bool = true;
/*      */             } 
/*      */           } 
/*      */         } else {
/* 1576 */           throw new IllegalArgumentException(J3dI18N.getString("BoundingBox6"));
/*      */         } 
/*      */       }
/* 1579 */       if (paramBoundingBox.boundsIsInfinite)
/*      */         break; 
/*      */     } 
/* 1582 */     if (!bool) {
/*      */       
/* 1584 */       paramBoundingBox.setLower(1.0D, 1.0D, 1.0D);
/* 1585 */       paramBoundingBox.setUpper(-1.0D, -1.0D, -1.0D);
/*      */     } 
/* 1587 */     return bool;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Bounds closestIntersection(Bounds[] paramArrayOfBounds) {
/* 1598 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0) {
/* 1599 */       return null;
/*      */     }
/*      */     
/* 1602 */     if (this.boundsIsEmpty) {
/* 1603 */       return null;
/*      */     }
/*      */     
/* 1606 */     getCenter();
/*      */ 
/*      */     
/* 1609 */     double d1 = 0.0D, d2 = 0.0D, d3 = 0.0D;
/* 1610 */     boolean bool1 = false;
/*      */     
/* 1612 */     boolean bool2 = false;
/* 1613 */     double d4 = Double.MAX_VALUE;
/* 1614 */     byte b2 = 0;
/*      */     
/* 1616 */     for (byte b1 = 0; b1 < paramArrayOfBounds.length; b1++) {
/* 1617 */       if (paramArrayOfBounds[b1] != null)
/*      */       {
/* 1619 */         if (intersect(paramArrayOfBounds[b1])) {
/* 1620 */           bool2 = true;
/* 1621 */           if ((paramArrayOfBounds[b1]).boundId == 1) {
/* 1622 */             BoundingBox boundingBox = (BoundingBox)paramArrayOfBounds[b1];
/* 1623 */             d1 = (boundingBox.upper.x + boundingBox.lower.x) / 2.0D;
/* 1624 */             d2 = (boundingBox.upper.y + boundingBox.lower.y) / 2.0D;
/* 1625 */             d3 = (boundingBox.upper.z + boundingBox.lower.z) / 2.0D;
/* 1626 */             double d = Math.sqrt((this.centroid.x - d1) * (this.centroid.x - d1) + (this.centroid.y - d2) * (this.centroid.y - d2) + (this.centroid.z - d3) * (this.centroid.z - d3));
/*      */ 
/*      */             
/* 1629 */             boolean bool = false;
/*      */             
/* 1631 */             if (this.lower.x <= boundingBox.lower.x && this.lower.y <= boundingBox.lower.y && this.lower.z <= boundingBox.lower.z && this.upper.x >= boundingBox.upper.x && this.upper.y >= boundingBox.upper.y && this.upper.z >= boundingBox.upper.z)
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1637 */               bool = true;
/*      */             }
/* 1639 */             if (bool) {
/* 1640 */               if (!bool1) {
/* 1641 */                 b2 = b1;
/* 1642 */                 d4 = d;
/* 1643 */                 bool1 = true;
/*      */               }
/* 1645 */               else if (d < d4) {
/* 1646 */                 b2 = b1;
/* 1647 */                 d4 = d;
/*      */               }
/*      */             
/* 1650 */             } else if (!bool1 && 
/* 1651 */               d < d4) {
/* 1652 */               b2 = b1;
/* 1653 */               d4 = d;
/*      */             
/*      */             }
/*      */           
/*      */           }
/* 1658 */           else if ((paramArrayOfBounds[b1]).boundId == 2) {
/* 1659 */             BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b1];
/* 1660 */             double d = Math.sqrt((this.centroid.x - boundingSphere.center.x) * (this.centroid.x - boundingSphere.center.x) + (this.centroid.y - boundingSphere.center.y) * (this.centroid.y - boundingSphere.center.y) + (this.centroid.z - boundingSphere.center.z) * (this.centroid.z - boundingSphere.center.z));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1667 */             boolean bool = false;
/*      */ 
/*      */             
/* 1670 */             if (boundingSphere.center.x <= this.upper.x && boundingSphere.center.x >= this.lower.x && boundingSphere.center.y <= this.upper.y && boundingSphere.center.y >= this.lower.y && boundingSphere.center.z <= this.upper.z && boundingSphere.center.z >= this.lower.z)
/*      */             {
/*      */ 
/*      */               
/* 1674 */               if (boundingSphere.center.x - this.lower.x >= boundingSphere.radius && this.upper.x - boundingSphere.center.x >= boundingSphere.radius && boundingSphere.center.y - this.lower.y >= boundingSphere.radius && this.upper.y - boundingSphere.center.y >= boundingSphere.radius && boundingSphere.center.z - this.lower.z >= boundingSphere.radius && this.upper.z - boundingSphere.center.z >= boundingSphere.radius)
/*      */               {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1681 */                 bool = true;
/*      */               }
/*      */             }
/* 1684 */             if (bool) {
/*      */               
/* 1686 */               if (!bool1) {
/* 1687 */                 b2 = b1;
/* 1688 */                 d4 = d;
/* 1689 */                 bool1 = true;
/*      */               }
/* 1691 */               else if (d < d4) {
/* 1692 */                 b2 = b1;
/* 1693 */                 d4 = d;
/*      */               }
/*      */             
/* 1696 */             } else if (!bool1 && 
/* 1697 */               d < d4) {
/* 1698 */               b2 = b1;
/* 1699 */               d4 = d;
/*      */             }
/*      */           
/*      */           }
/* 1703 */           else if ((paramArrayOfBounds[b1]).boundId == 4) {
/* 1704 */             BoundingPolytope boundingPolytope = (BoundingPolytope)paramArrayOfBounds[b1];
/*      */             
/* 1706 */             double d = Math.sqrt((this.centroid.x - boundingPolytope.centroid.x) * (this.centroid.x - boundingPolytope.centroid.x) + (this.centroid.y - boundingPolytope.centroid.y) * (this.centroid.y - boundingPolytope.centroid.y) + (this.centroid.z - boundingPolytope.centroid.z) * (this.centroid.z - boundingPolytope.centroid.z));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1712 */             boolean bool = true;
/* 1713 */             for (byte b = 0; b < boundingPolytope.nVerts; b++) {
/* 1714 */               if ((boundingPolytope.verts[b]).x < this.lower.x || (boundingPolytope.verts[b]).y < this.lower.y || (boundingPolytope.verts[b]).z < this.lower.z || (boundingPolytope.verts[b]).x > this.upper.x || (boundingPolytope.verts[b]).y > this.upper.y || (boundingPolytope.verts[b]).z > this.upper.z)
/*      */               {
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1720 */                 bool = false;
/*      */               }
/*      */             } 
/*      */ 
/*      */             
/* 1725 */             if (bool) {
/* 1726 */               if (!bool1) {
/* 1727 */                 b2 = b1;
/* 1728 */                 d4 = d;
/* 1729 */                 bool1 = true;
/*      */               }
/* 1731 */               else if (d < d4) {
/* 1732 */                 b2 = b1;
/* 1733 */                 d4 = d;
/*      */               }
/*      */             
/* 1736 */             } else if (!bool1 && 
/* 1737 */               d < d4) {
/* 1738 */               b2 = b1;
/* 1739 */               d4 = d;
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 1744 */             throw new IllegalArgumentException(J3dI18N.getString("BoundingBox9"));
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/* 1749 */     if (bool2) {
/* 1750 */       return paramArrayOfBounds[b2];
/*      */     }
/* 1752 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersect(CachedFrustum paramCachedFrustum) {
/* 1762 */     if (this.boundsIsEmpty) {
/* 1763 */       return false;
/*      */     }
/* 1765 */     if (this.boundsIsInfinite) {
/* 1766 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1771 */     if (this.upper.x < paramCachedFrustum.lower.x || this.lower.x > paramCachedFrustum.upper.x || this.upper.y < paramCachedFrustum.lower.y || this.lower.y > paramCachedFrustum.upper.y || this.upper.z < paramCachedFrustum.lower.z || this.lower.z > paramCachedFrustum.upper.z)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1779 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1783 */     byte b = 5;
/* 1784 */     while (b >= 0) {
/* 1785 */       Vector4d vector4d = paramCachedFrustum.clipPlanes[b--];
/* 1786 */       if (this.upper.x * vector4d.x + this.upper.y * vector4d.y + this.upper.z * vector4d.z + vector4d.w < 0.0D && this.upper.x * vector4d.x + this.lower.y * vector4d.y + this.upper.z * vector4d.z + vector4d.w < 0.0D && this.upper.x * vector4d.x + this.lower.y * vector4d.y + this.lower.z * vector4d.z + vector4d.w < 0.0D && this.upper.x * vector4d.x + this.upper.y * vector4d.y + this.lower.z * vector4d.z + vector4d.w < 0.0D && this.lower.x * vector4d.x + this.upper.y * vector4d.y + this.upper.z * vector4d.z + vector4d.w < 0.0D && this.lower.x * vector4d.x + this.lower.y * vector4d.y + this.upper.z * vector4d.z + vector4d.w < 0.0D && this.lower.x * vector4d.x + this.lower.y * vector4d.y + this.lower.z * vector4d.z + vector4d.w < 0.0D && this.lower.x * vector4d.x + this.upper.y * vector4d.y + this.lower.z * vector4d.z + vector4d.w < 0.0D)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1804 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 1808 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1815 */   public String toString() { return new String("Bounding box: Lower=" + this.lower.x + " " + this.lower.y + " " + this.lower.z + " Upper=" + this.upper.x + " " + this.upper.y + " " + this.upper.z); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateBoundsStates() {
/* 1821 */     if (this.lower.x == Double.NEGATIVE_INFINITY && this.lower.y == Double.NEGATIVE_INFINITY && this.lower.z == Double.NEGATIVE_INFINITY && this.upper.x == Double.POSITIVE_INFINITY && this.upper.y == Double.POSITIVE_INFINITY && this.upper.z == Double.POSITIVE_INFINITY) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1827 */       this.boundsIsEmpty = false;
/* 1828 */       this.boundsIsInfinite = true;
/*      */       
/*      */       return;
/*      */     } 
/* 1832 */     if (checkBoundsIsNaN()) {
/* 1833 */       this.boundsIsEmpty = true;
/* 1834 */       this.boundsIsInfinite = false;
/*      */       
/*      */       return;
/*      */     } 
/* 1838 */     this.boundsIsInfinite = false;
/* 1839 */     if (this.lower.x > this.upper.x || this.lower.y > this.upper.y || this.lower.z > this.upper.z) {
/*      */ 
/*      */       
/* 1842 */       this.boundsIsEmpty = true;
/*      */     } else {
/* 1844 */       this.boundsIsEmpty = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   Point3d getCenter() {
/* 1851 */     if (this.centroid == null) {
/* 1852 */       this.centroid = new Point3d();
/*      */     }
/*      */     
/* 1855 */     this.centroid.x = (this.upper.x + this.lower.x) * 0.5D;
/* 1856 */     this.centroid.y = (this.upper.y + this.lower.y) * 0.5D;
/* 1857 */     this.centroid.z = (this.upper.z + this.lower.z) * 0.5D;
/*      */     
/* 1859 */     return this.centroid;
/*      */   }
/*      */   
/*      */   void translate(BoundingBox paramBoundingBox, Vector3d paramVector3d) {
/* 1863 */     if (paramBoundingBox == null || paramBoundingBox.boundsIsEmpty) {
/*      */       
/* 1865 */       setLower(1.0D, 1.0D, 1.0D);
/* 1866 */       setUpper(-1.0D, -1.0D, -1.0D);
/*      */       return;
/*      */     } 
/* 1869 */     if (paramBoundingBox.boundsIsInfinite) {
/* 1870 */       set(paramBoundingBox);
/*      */       
/*      */       return;
/*      */     } 
/* 1874 */     paramBoundingBox.lower.x += paramVector3d.x;
/* 1875 */     paramBoundingBox.lower.y += paramVector3d.y;
/* 1876 */     paramBoundingBox.lower.z += paramVector3d.z;
/* 1877 */     paramBoundingBox.upper.x += paramVector3d.x;
/* 1878 */     paramBoundingBox.upper.y += paramVector3d.y;
/* 1879 */     paramBoundingBox.upper.z += paramVector3d.z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Bounds copy(Bounds paramBounds) {
/* 1889 */     if (paramBounds != null && this.boundId == paramBounds.boundId) {
/* 1890 */       BoundingBox boundingBox = (BoundingBox)paramBounds;
/* 1891 */       boundingBox.lower.x = this.lower.x;
/* 1892 */       boundingBox.lower.y = this.lower.y;
/* 1893 */       boundingBox.lower.z = this.lower.z;
/* 1894 */       boundingBox.upper.x = this.upper.x;
/* 1895 */       boundingBox.upper.y = this.upper.y;
/* 1896 */       boundingBox.upper.z = this.upper.z;
/* 1897 */       boundingBox.boundsIsEmpty = this.boundsIsEmpty;
/* 1898 */       boundingBox.boundsIsInfinite = this.boundsIsInfinite;
/* 1899 */       return boundingBox;
/*      */     } 
/*      */     
/* 1902 */     return (Bounds)clone();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean checkBoundsIsNaN() {
/* 1909 */     if (Double.isNaN(this.lower.x + this.lower.y + this.lower.z + this.upper.x + this.upper.y + this.upper.z)) {
/* 1910 */       return true;
/*      */     }
/*      */     
/* 1913 */     return false;
/*      */   }
/*      */ 
/*      */   
/* 1917 */   int getPickType() { return 6; }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\BoundingBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */