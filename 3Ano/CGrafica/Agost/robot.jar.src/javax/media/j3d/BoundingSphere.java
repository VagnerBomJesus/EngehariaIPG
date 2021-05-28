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
/*      */ public class BoundingSphere
/*      */   extends Bounds
/*      */ {
/*      */   Point3d center;
/*      */   double radius;
/*      */   Point3d[] boxVerts;
/*      */   boolean allocBoxVerts;
/*      */   private BoundingBox tmpBox;
/*      */   private BoundingPolytope tmpPolytope;
/*      */   
/*      */   public BoundingSphere(Point3d paramPoint3d, double paramDouble) {
/*   37 */     this.allocBoxVerts = false;
/*      */ 
/*      */     
/*   40 */     this.tmpBox = null;
/*   41 */     this.tmpPolytope = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   49 */     this.center = new Point3d(paramPoint3d);
/*   50 */     this.radius = paramDouble;
/*   51 */     this.boundId = 2;
/*   52 */     updateBoundsStates();
/*      */   }
/*      */   public BoundingSphere() {
/*      */     this.allocBoxVerts = false;
/*      */     this.tmpBox = null;
/*      */     this.tmpPolytope = null;
/*   58 */     this.boundId = 2;
/*   59 */     this.center = new Point3d();
/*   60 */     this.radius = 1.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BoundingSphere(Bounds paramBounds) {
/*      */     this.allocBoxVerts = false;
/*      */     this.tmpBox = null;
/*      */     this.tmpPolytope = null;
/*   70 */     this.boundId = 2;
/*   71 */     if (paramBounds == null) {
/*      */       
/*   73 */       this.center = new Point3d();
/*   74 */       this.radius = -1.0D;
/*      */     }
/*   76 */     else if (paramBounds.boundsIsInfinite) {
/*   77 */       this.center = new Point3d();
/*   78 */       this.radius = Double.POSITIVE_INFINITY;
/*      */     }
/*   80 */     else if (paramBounds.boundId == 1) {
/*   81 */       BoundingBox boundingBox = (BoundingBox)paramBounds;
/*   82 */       this.center = new Point3d();
/*   83 */       this.center.x = (boundingBox.upper.x + boundingBox.lower.x) / 2.0D;
/*   84 */       this.center.y = (boundingBox.upper.y + boundingBox.lower.y) / 2.0D;
/*   85 */       this.center.z = (boundingBox.upper.z + boundingBox.lower.z) / 2.0D;
/*   86 */       this.radius = 0.5D * Math.sqrt((boundingBox.upper.x - boundingBox.lower.x) * (boundingBox.upper.x - boundingBox.lower.x) + (boundingBox.upper.y - boundingBox.lower.y) * (boundingBox.upper.y - boundingBox.lower.y) + (boundingBox.upper.z - boundingBox.lower.z) * (boundingBox.upper.z - boundingBox.lower.z));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*   93 */     else if (paramBounds.boundId == 2) {
/*   94 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/*   95 */       this.center = new Point3d(boundingSphere.center);
/*   96 */       this.radius = boundingSphere.radius;
/*      */     }
/*   98 */     else if (paramBounds.boundId == 4) {
/*   99 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/*      */       
/*  101 */       this.center = new Point3d();
/*  102 */       this.center.x = boundingPolytope.centroid.x;
/*  103 */       this.center.y = boundingPolytope.centroid.y;
/*  104 */       this.center.z = boundingPolytope.centroid.z;
/*  105 */       this.radius = Math.sqrt(((boundingPolytope.verts[0]).x - this.center.x) * ((boundingPolytope.verts[0]).x - this.center.x) + ((boundingPolytope.verts[0]).y - this.center.y) * ((boundingPolytope.verts[0]).y - this.center.y) + ((boundingPolytope.verts[0]).z - this.center.z) * ((boundingPolytope.verts[0]).z - this.center.z));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  112 */       for (byte b = 1; b < boundingPolytope.nVerts; b++) {
/*  113 */         double d2 = this.radius * this.radius;
/*      */         
/*  115 */         double d1 = ((boundingPolytope.verts[b]).x - this.center.x) * ((boundingPolytope.verts[b]).x - this.center.x) + ((boundingPolytope.verts[b]).y - this.center.y) * ((boundingPolytope.verts[b]).y - this.center.y) + ((boundingPolytope.verts[b]).z - this.center.z) * ((boundingPolytope.verts[b]).z - this.center.z);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  124 */         if (d1 > d2) {
/*  125 */           double d4 = Math.sqrt(d1);
/*  126 */           this.radius = (this.radius + d4) * 0.5D;
/*  127 */           double d5 = d4 - this.radius;
/*  128 */           double d3 = d5 / d4;
/*  129 */           this.center.x += ((boundingPolytope.verts[b]).x - this.center.x) * d3;
/*  130 */           this.center.y += ((boundingPolytope.verts[b]).y - this.center.y) * d3;
/*  131 */           this.center.z += ((boundingPolytope.verts[b]).z - this.center.z) * d3;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  135 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingSphere0"));
/*      */     } 
/*      */     
/*  138 */     updateBoundsStates();
/*      */   }
/*      */ 
/*      */   
/*      */   public BoundingSphere(Bounds[] paramArrayOfBounds) {
/*      */     this.allocBoxVerts = false;
/*      */     this.tmpBox = null;
/*      */     this.tmpPolytope = null;
/*  146 */     byte b = 0;
/*      */ 
/*      */     
/*  149 */     this.boundId = 2;
/*  150 */     this.center = new Point3d();
/*      */     
/*  152 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0) {
/*      */       
/*  154 */       this.radius = -1.0D;
/*  155 */       updateBoundsStates();
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  160 */     while (paramArrayOfBounds[b] == null && b < paramArrayOfBounds.length) {
/*  161 */       b++;
/*      */     }
/*      */     
/*  164 */     if (b >= paramArrayOfBounds.length) {
/*      */       
/*  166 */       this.radius = -1.0D;
/*  167 */       updateBoundsStates();
/*      */       
/*      */       return;
/*      */     } 
/*  171 */     set(paramArrayOfBounds[b++]);
/*  172 */     if (this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*  175 */     for (; b < paramArrayOfBounds.length; b++) {
/*  176 */       if (paramArrayOfBounds[b] != null && 
/*  177 */         !(paramArrayOfBounds[b]).boundsIsEmpty) {
/*  178 */         if ((paramArrayOfBounds[b]).boundsIsInfinite) {
/*  179 */           this.radius = Double.POSITIVE_INFINITY;
/*      */           break;
/*      */         } 
/*  182 */         if ((paramArrayOfBounds[b]).boundId == 1) {
/*  183 */           BoundingBox boundingBox = (BoundingBox)paramArrayOfBounds[b];
/*  184 */           if (!this.allocBoxVerts) {
/*  185 */             this.boxVerts = new Point3d[8];
/*  186 */             for (byte b1 = 0; b1 < 8; ) { this.boxVerts[b1] = new Point3d(); b1++; }
/*  187 */              this.allocBoxVerts = true;
/*      */           } 
/*  189 */           this.boxVerts[0].set(boundingBox.lower.x, boundingBox.lower.y, boundingBox.lower.z);
/*  190 */           this.boxVerts[1].set(boundingBox.lower.x, boundingBox.upper.y, boundingBox.lower.z);
/*  191 */           this.boxVerts[2].set(boundingBox.upper.x, boundingBox.lower.y, boundingBox.lower.z);
/*  192 */           this.boxVerts[3].set(boundingBox.upper.x, boundingBox.upper.y, boundingBox.lower.z);
/*  193 */           this.boxVerts[4].set(boundingBox.lower.x, boundingBox.lower.y, boundingBox.upper.z);
/*  194 */           this.boxVerts[5].set(boundingBox.lower.x, boundingBox.upper.y, boundingBox.upper.z);
/*  195 */           this.boxVerts[6].set(boundingBox.upper.x, boundingBox.lower.y, boundingBox.upper.z);
/*  196 */           this.boxVerts[7].set(boundingBox.upper.x, boundingBox.upper.y, boundingBox.upper.z);
/*  197 */           combine(this.boxVerts);
/*      */         }
/*  199 */         else if ((paramArrayOfBounds[b]).boundId == 2) {
/*  200 */           BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b];
/*  201 */           double d = Math.sqrt((this.center.x - boundingSphere.center.x) * (this.center.x - boundingSphere.center.x) + (this.center.y - boundingSphere.center.y) * (this.center.y - boundingSphere.center.y) + (this.center.z - boundingSphere.center.z) * (this.center.z - boundingSphere.center.z));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  207 */           if (this.radius > boundingSphere.radius) {
/*  208 */             if (d + boundingSphere.radius > this.radius) {
/*  209 */               double d2 = 0.5D * (this.radius - boundingSphere.radius + d);
/*  210 */               double d1 = d2 / d;
/*  211 */               this.radius = d2 + boundingSphere.radius;
/*  212 */               boundingSphere.center.x += (this.center.x - boundingSphere.center.x) * d1;
/*  213 */               boundingSphere.center.y += (this.center.y - boundingSphere.center.y) * d1;
/*  214 */               boundingSphere.center.z += (this.center.z - boundingSphere.center.z) * d1;
/*      */             }
/*      */           
/*  217 */           } else if (d + this.radius <= boundingSphere.radius) {
/*  218 */             this.center.x = boundingSphere.center.x;
/*  219 */             this.center.y = boundingSphere.center.y;
/*  220 */             this.center.z = boundingSphere.center.z;
/*  221 */             this.radius = boundingSphere.radius;
/*      */           } else {
/*  223 */             double d2 = 0.5D * (boundingSphere.radius - this.radius + d);
/*  224 */             double d1 = d2 / d;
/*  225 */             this.radius = d2 + this.radius;
/*  226 */             this.center.x += (boundingSphere.center.x - this.center.x) * d1;
/*  227 */             this.center.y += (boundingSphere.center.y - this.center.y) * d1;
/*  228 */             this.center.z += (boundingSphere.center.z - this.center.z) * d1;
/*      */           }
/*      */         
/*      */         }
/*  232 */         else if ((paramArrayOfBounds[b]).boundId == 4) {
/*  233 */           BoundingPolytope boundingPolytope = (BoundingPolytope)paramArrayOfBounds[b];
/*  234 */           combine(boundingPolytope.verts);
/*      */ 
/*      */         
/*      */         }
/*  238 */         else if (paramArrayOfBounds[b] != null) {
/*  239 */           throw new IllegalArgumentException(J3dI18N.getString("BoundingSphere0"));
/*      */         } 
/*      */       } 
/*  242 */     }  updateBoundsStates();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  250 */   public double getRadius() { return this.radius; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRadius(double paramDouble) {
/*  258 */     this.radius = paramDouble;
/*  259 */     updateBoundsStates();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getCenter(Point3d paramPoint3d) {
/*  268 */     paramPoint3d.x = this.center.x;
/*  269 */     paramPoint3d.y = this.center.y;
/*  270 */     paramPoint3d.z = this.center.z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCenter(Point3d paramPoint3d) {
/*  278 */     this.center.x = paramPoint3d.x;
/*  279 */     this.center.y = paramPoint3d.y;
/*  280 */     this.center.z = paramPoint3d.z;
/*  281 */     checkBoundsIsNaN();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void set(Bounds paramBounds) {
/*  291 */     if (paramBounds == null || paramBounds.boundsIsEmpty) {
/*  292 */       this.center.x = 0.0D;
/*  293 */       this.center.y = 0.0D;
/*  294 */       this.center.z = 0.0D;
/*  295 */       this.radius = -1.0D;
/*  296 */     } else if (paramBounds.boundsIsInfinite) {
/*  297 */       this.center.x = 0.0D;
/*  298 */       this.center.y = 0.0D;
/*  299 */       this.center.z = 0.0D;
/*  300 */       this.radius = Double.POSITIVE_INFINITY;
/*  301 */     } else if (paramBounds.boundId == 1) {
/*  302 */       BoundingBox boundingBox = (BoundingBox)paramBounds;
/*  303 */       this.center.x = (boundingBox.upper.x + boundingBox.lower.x) / 2.0D;
/*  304 */       this.center.y = (boundingBox.upper.y + boundingBox.lower.y) / 2.0D;
/*  305 */       this.center.z = (boundingBox.upper.z + boundingBox.lower.z) / 2.0D;
/*  306 */       this.radius = 0.5D * Math.sqrt((boundingBox.upper.x - boundingBox.lower.x) * (boundingBox.upper.x - boundingBox.lower.x) + (boundingBox.upper.y - boundingBox.lower.y) * (boundingBox.upper.y - boundingBox.lower.y) + (boundingBox.upper.z - boundingBox.lower.z) * (boundingBox.upper.z - boundingBox.lower.z));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  312 */     else if (paramBounds.boundId == 2) {
/*  313 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/*  314 */       this.radius = boundingSphere.radius;
/*  315 */       this.center.x = boundingSphere.center.x;
/*  316 */       this.center.y = boundingSphere.center.y;
/*  317 */       this.center.z = boundingSphere.center.z;
/*  318 */     } else if (paramBounds.boundId == 4) {
/*  319 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/*      */       
/*  321 */       this.center.x = boundingPolytope.centroid.x;
/*  322 */       this.center.y = boundingPolytope.centroid.y;
/*  323 */       this.center.z = boundingPolytope.centroid.z;
/*  324 */       this.radius = Math.sqrt(((boundingPolytope.verts[0]).x - this.center.x) * ((boundingPolytope.verts[0]).x - this.center.x) + ((boundingPolytope.verts[0]).y - this.center.y) * ((boundingPolytope.verts[0]).y - this.center.y) + ((boundingPolytope.verts[0]).z - this.center.z) * ((boundingPolytope.verts[0]).z - this.center.z));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  331 */       for (byte b = 1; b < boundingPolytope.nVerts; b++) {
/*  332 */         double d2 = this.radius * this.radius;
/*      */         
/*  334 */         double d1 = ((boundingPolytope.verts[b]).x - this.center.x) * ((boundingPolytope.verts[b]).x - this.center.x) + ((boundingPolytope.verts[b]).y - this.center.y) * ((boundingPolytope.verts[b]).y - this.center.y) + ((boundingPolytope.verts[b]).z - this.center.z) * ((boundingPolytope.verts[b]).z - this.center.z);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  343 */         if (d1 > d2) {
/*  344 */           double d4 = Math.sqrt(d1);
/*  345 */           this.radius = (this.radius + d4) * 0.5D;
/*  346 */           double d5 = d4 - this.radius;
/*  347 */           double d3 = d5 / d4;
/*  348 */           this.center.x += ((boundingPolytope.verts[b]).x - this.center.x) * d3;
/*  349 */           this.center.y += ((boundingPolytope.verts[b]).y - this.center.y) * d3;
/*  350 */           this.center.z += ((boundingPolytope.verts[b]).z - this.center.z) * d3;
/*      */         } 
/*      */       } 
/*      */     } else {
/*  354 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingSphere2"));
/*      */     } 
/*  356 */     updateBoundsStates();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  364 */   public Object clone() { return new BoundingSphere(this.center, this.radius); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  383 */       BoundingSphere boundingSphere = (BoundingSphere)paramObject;
/*  384 */       return (this.center.equals(boundingSphere.center) && this.radius == boundingSphere.radius);
/*      */     
/*      */     }
/*  387 */     catch (NullPointerException nullPointerException) {
/*  388 */       return false;
/*      */     }
/*  390 */     catch (ClassCastException classCastException) {
/*  391 */       return false;
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
/*  409 */     long l = 1L;
/*  410 */     l = 31L * l + HashCodeUtil.doubleToLongBits(this.radius);
/*  411 */     l = 31L * l + HashCodeUtil.doubleToLongBits(this.center.x);
/*  412 */     l = 31L * l + HashCodeUtil.doubleToLongBits(this.center.y);
/*  413 */     l = 31L * l + HashCodeUtil.doubleToLongBits(this.center.z);
/*  414 */     return (int)(l ^ l >> 32);
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
/*      */   public void combine(Bounds paramBounds) {
/*  428 */     if (paramBounds == null || paramBounds.boundsIsEmpty || this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */     
/*  432 */     if (this.boundsIsEmpty || paramBounds.boundsIsInfinite) {
/*  433 */       set(paramBounds);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  438 */     if (paramBounds.boundId == 1) {
/*  439 */       double d6, d5, d4; BoundingBox boundingBox = (BoundingBox)paramBounds;
/*      */ 
/*      */       
/*  442 */       double d2 = boundingBox.upper.x - this.center.x;
/*  443 */       double d3 = boundingBox.lower.x - this.center.x;
/*  444 */       if (d2 * d2 > d3 * d3) {
/*  445 */         d4 = boundingBox.upper.x;
/*      */       } else {
/*  447 */         d4 = boundingBox.lower.x;
/*      */       } 
/*  449 */       d2 = boundingBox.upper.y - this.center.y;
/*  450 */       d3 = boundingBox.lower.y - this.center.y;
/*  451 */       if (d2 * d2 > d3 * d3) {
/*  452 */         d5 = boundingBox.upper.y;
/*      */       } else {
/*  454 */         d5 = boundingBox.lower.y;
/*      */       } 
/*  456 */       d2 = boundingBox.upper.z - this.center.z;
/*  457 */       d3 = boundingBox.lower.z - this.center.z;
/*  458 */       if (d2 * d2 > d3 * d3) {
/*  459 */         d6 = boundingBox.upper.z;
/*      */       } else {
/*  461 */         d6 = boundingBox.lower.z;
/*      */       } 
/*  463 */       double d1 = Math.sqrt((d4 - this.center.x) * (d4 - this.center.x) + (d5 - this.center.y) * (d5 - this.center.y) + (d6 - this.center.z) * (d6 - this.center.z));
/*      */ 
/*      */ 
/*      */       
/*  467 */       if (d1 > this.radius) {
/*  468 */         this.radius = (d1 + this.radius) * 0.5D;
/*  469 */         double d = d1 - this.radius;
/*  470 */         this.center.x = (this.radius * this.center.x + d * d4) / d1;
/*  471 */         this.center.y = (this.radius * this.center.y + d * d5) / d1;
/*  472 */         this.center.z = (this.radius * this.center.z + d * d6) / d1;
/*  473 */         combinePoint(boundingBox.upper.x, boundingBox.upper.y, boundingBox.upper.z);
/*  474 */         combinePoint(boundingBox.upper.x, boundingBox.upper.y, boundingBox.lower.z);
/*  475 */         combinePoint(boundingBox.upper.x, boundingBox.lower.y, boundingBox.upper.z);
/*  476 */         combinePoint(boundingBox.upper.x, boundingBox.lower.y, boundingBox.lower.z);
/*  477 */         combinePoint(boundingBox.lower.x, boundingBox.upper.y, boundingBox.upper.z);
/*  478 */         combinePoint(boundingBox.lower.x, boundingBox.upper.y, boundingBox.lower.z);
/*  479 */         combinePoint(boundingBox.lower.x, boundingBox.lower.y, boundingBox.upper.z);
/*  480 */         combinePoint(boundingBox.lower.x, boundingBox.lower.y, boundingBox.lower.z);
/*      */       } 
/*  482 */     } else if (paramBounds.boundId == 2) {
/*  483 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/*  484 */       double d = Math.sqrt((this.center.x - boundingSphere.center.x) * (this.center.x - boundingSphere.center.x) + (this.center.y - boundingSphere.center.y) * (this.center.y - boundingSphere.center.y) + (this.center.z - boundingSphere.center.z) * (this.center.z - boundingSphere.center.z));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  490 */       if (this.radius > boundingSphere.radius) {
/*  491 */         if (d + boundingSphere.radius > this.radius) {
/*  492 */           double d2 = 0.5D * (this.radius - boundingSphere.radius + d);
/*  493 */           double d1 = d2 / d;
/*  494 */           this.radius = d2 + boundingSphere.radius;
/*  495 */           boundingSphere.center.x += (this.center.x - boundingSphere.center.x) * d1;
/*  496 */           boundingSphere.center.y += (this.center.y - boundingSphere.center.y) * d1;
/*  497 */           boundingSphere.center.z += (this.center.z - boundingSphere.center.z) * d1;
/*      */         }
/*      */       
/*  500 */       } else if (d + this.radius <= boundingSphere.radius) {
/*  501 */         this.center.x = boundingSphere.center.x;
/*  502 */         this.center.y = boundingSphere.center.y;
/*  503 */         this.center.z = boundingSphere.center.z;
/*  504 */         this.radius = boundingSphere.radius;
/*      */       } else {
/*  506 */         double d2 = 0.5D * (boundingSphere.radius - this.radius + d);
/*  507 */         double d1 = d2 / d;
/*  508 */         this.radius = d2 + this.radius;
/*  509 */         this.center.x += (boundingSphere.center.x - this.center.x) * d1;
/*  510 */         this.center.y += (boundingSphere.center.y - this.center.y) * d1;
/*  511 */         this.center.z += (boundingSphere.center.z - this.center.z) * d1;
/*      */       }
/*      */     
/*      */     }
/*  515 */     else if (paramBounds.boundId == 4) {
/*  516 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/*  517 */       combine(boundingPolytope.verts);
/*      */     } else {
/*  519 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingSphere3"));
/*      */     } 
/*  521 */     updateBoundsStates();
/*      */   }
/*      */ 
/*      */   
/*      */   private void combinePoint(double paramDouble1, double paramDouble2, double paramDouble3) {
/*  526 */     double d = Math.sqrt((paramDouble1 - this.center.x) * (paramDouble1 - this.center.x) + (paramDouble2 - this.center.y) * (paramDouble2 - this.center.y) + (paramDouble3 - this.center.z) * (paramDouble3 - this.center.z));
/*      */ 
/*      */ 
/*      */     
/*  530 */     if (d > this.radius) {
/*  531 */       this.radius = (d + this.radius) * 0.5D;
/*  532 */       double d1 = d - this.radius;
/*  533 */       this.center.x = (this.radius * this.center.x + d1 * paramDouble1) / d;
/*  534 */       this.center.y = (this.radius * this.center.y + d1 * paramDouble2) / d;
/*  535 */       this.center.z = (this.radius * this.center.z + d1 * paramDouble3) / d;
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
/*      */   public void combine(Bounds[] paramArrayOfBounds) {
/*  550 */     byte b = 0;
/*      */ 
/*      */     
/*  553 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0 || this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  558 */     while (b < paramArrayOfBounds.length && (paramArrayOfBounds[b] == null || (paramArrayOfBounds[b]).boundsIsEmpty))
/*      */     {
/*  560 */       b++;
/*      */     }
/*  562 */     if (b >= paramArrayOfBounds.length) {
/*      */       return;
/*      */     }
/*  565 */     if (this.boundsIsEmpty) {
/*  566 */       set(paramArrayOfBounds[b++]);
/*      */     }
/*  568 */     if (this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*  571 */     for (; b < paramArrayOfBounds.length; b++) {
/*  572 */       if (paramArrayOfBounds[b] != null && 
/*  573 */         !(paramArrayOfBounds[b]).boundsIsEmpty) {
/*  574 */         if ((paramArrayOfBounds[b]).boundsIsInfinite) {
/*  575 */           this.center.x = 0.0D;
/*  576 */           this.center.y = 0.0D;
/*  577 */           this.center.z = 0.0D;
/*  578 */           this.radius = Double.POSITIVE_INFINITY; break;
/*      */         } 
/*  580 */         if ((paramArrayOfBounds[b]).boundId == 1) {
/*  581 */           double d6, d5, d4; BoundingBox boundingBox = (BoundingBox)paramArrayOfBounds[b];
/*      */ 
/*      */           
/*  584 */           double d2 = boundingBox.upper.x - this.center.x;
/*  585 */           double d3 = boundingBox.lower.x - this.center.x;
/*  586 */           if (d2 * d2 > d3 * d3) {
/*  587 */             d4 = boundingBox.upper.x;
/*      */           } else {
/*  589 */             d4 = boundingBox.lower.x;
/*      */           } 
/*  591 */           d2 = boundingBox.upper.y - this.center.y;
/*  592 */           d3 = boundingBox.lower.y - this.center.y;
/*  593 */           if (d2 * d2 > d3 * d3) {
/*  594 */             d5 = boundingBox.upper.y;
/*      */           } else {
/*  596 */             d5 = boundingBox.lower.y;
/*      */           } 
/*  598 */           d2 = boundingBox.upper.z - this.center.z;
/*  599 */           d3 = boundingBox.lower.z - this.center.z;
/*  600 */           if (d2 * d2 > d3 * d3) {
/*  601 */             d6 = boundingBox.upper.z;
/*      */           } else {
/*  603 */             d6 = boundingBox.lower.z;
/*      */           } 
/*  605 */           double d1 = Math.sqrt((d4 - this.center.x) * (d4 - this.center.x) + (d5 - this.center.y) * (d5 - this.center.y) + (d6 - this.center.z) * (d6 - this.center.z));
/*      */ 
/*      */ 
/*      */           
/*  609 */           if (d1 > this.radius) {
/*  610 */             this.radius = (d1 + this.radius) * 0.5D;
/*  611 */             double d = d1 - this.radius;
/*  612 */             this.center.x = (this.radius * this.center.x + d * d4) / d1;
/*  613 */             this.center.y = (this.radius * this.center.y + d * d5) / d1;
/*  614 */             this.center.z = (this.radius * this.center.z + d * d6) / d1;
/*  615 */             combinePoint(boundingBox.upper.x, boundingBox.upper.y, boundingBox.upper.z);
/*  616 */             combinePoint(boundingBox.upper.x, boundingBox.upper.y, boundingBox.lower.z);
/*  617 */             combinePoint(boundingBox.upper.x, boundingBox.lower.y, boundingBox.upper.z);
/*  618 */             combinePoint(boundingBox.upper.x, boundingBox.lower.y, boundingBox.lower.z);
/*  619 */             combinePoint(boundingBox.lower.x, boundingBox.upper.y, boundingBox.upper.z);
/*  620 */             combinePoint(boundingBox.lower.x, boundingBox.upper.y, boundingBox.lower.z);
/*  621 */             combinePoint(boundingBox.lower.x, boundingBox.lower.y, boundingBox.upper.z);
/*  622 */             combinePoint(boundingBox.lower.x, boundingBox.lower.y, boundingBox.lower.z);
/*      */           } 
/*  624 */         } else if ((paramArrayOfBounds[b]).boundId == 2) {
/*  625 */           BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b];
/*  626 */           double d = Math.sqrt((this.center.x - boundingSphere.center.x) * (this.center.x - boundingSphere.center.x) + (this.center.y - boundingSphere.center.y) * (this.center.y - boundingSphere.center.y) + (this.center.z - boundingSphere.center.z) * (this.center.z - boundingSphere.center.z));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  632 */           if (this.radius > boundingSphere.radius) {
/*  633 */             if (d + boundingSphere.radius > this.radius) {
/*  634 */               double d2 = 0.5D * (this.radius - boundingSphere.radius + d);
/*  635 */               double d1 = d2 / d;
/*  636 */               this.radius = d2 + boundingSphere.radius;
/*  637 */               boundingSphere.center.x += (this.center.x - boundingSphere.center.x) * d1;
/*  638 */               boundingSphere.center.y += (this.center.y - boundingSphere.center.y) * d1;
/*  639 */               boundingSphere.center.z += (this.center.z - boundingSphere.center.z) * d1;
/*      */             }
/*      */           
/*  642 */           } else if (d + this.radius <= boundingSphere.radius) {
/*  643 */             this.center.x = boundingSphere.center.x;
/*  644 */             this.center.y = boundingSphere.center.y;
/*  645 */             this.center.z = boundingSphere.center.z;
/*  646 */             this.radius = boundingSphere.radius;
/*      */           } else {
/*  648 */             double d2 = 0.5D * (boundingSphere.radius - this.radius + d);
/*  649 */             double d1 = d2 / d;
/*  650 */             this.radius = d2 + this.radius;
/*  651 */             this.center.x += (boundingSphere.center.x - this.center.x) * d1;
/*  652 */             this.center.y += (boundingSphere.center.y - this.center.y) * d1;
/*  653 */             this.center.z += (boundingSphere.center.z - this.center.z) * d1;
/*      */           }
/*      */         
/*  656 */         } else if ((paramArrayOfBounds[b]).boundId == 4) {
/*  657 */           BoundingPolytope boundingPolytope = (BoundingPolytope)paramArrayOfBounds[b];
/*  658 */           combine(boundingPolytope.verts);
/*      */         } else {
/*  660 */           throw new IllegalArgumentException(J3dI18N.getString("BoundingSphere4"));
/*      */         } 
/*      */       } 
/*      */     } 
/*  664 */     updateBoundsStates();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void combine(Point3d paramPoint3d) {
/*  674 */     if (this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */     
/*  678 */     if (this.boundsIsEmpty) {
/*  679 */       this.radius = 0.0D;
/*  680 */       this.center.x = paramPoint3d.x;
/*  681 */       this.center.y = paramPoint3d.y;
/*  682 */       this.center.z = paramPoint3d.z;
/*      */     } else {
/*  684 */       double d = Math.sqrt((paramPoint3d.x - this.center.x) * (paramPoint3d.x - this.center.x) + (paramPoint3d.y - this.center.y) * (paramPoint3d.y - this.center.y) + (paramPoint3d.z - this.center.z) * (paramPoint3d.z - this.center.z));
/*      */ 
/*      */ 
/*      */       
/*  688 */       if (d > this.radius) {
/*  689 */         this.radius = (d + this.radius) * 0.5D;
/*  690 */         double d1 = d - this.radius;
/*  691 */         this.center.x = (this.radius * this.center.x + d1 * paramPoint3d.x) / d;
/*  692 */         this.center.y = (this.radius * this.center.y + d1 * paramPoint3d.y) / d;
/*  693 */         this.center.z = (this.radius * this.center.z + d1 * paramPoint3d.z) / d;
/*      */       } 
/*      */     } 
/*      */     
/*  697 */     updateBoundsStates();
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
/*  708 */     if (this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*      */     
/*  712 */     if (this.boundsIsEmpty) {
/*  713 */       this.center.x = (paramArrayOfPoint3d[0]).x;
/*  714 */       this.center.y = (paramArrayOfPoint3d[0]).y;
/*  715 */       this.center.z = (paramArrayOfPoint3d[0]).z;
/*  716 */       this.radius = 0.0D;
/*      */     } 
/*      */     
/*  719 */     for (byte b = 0; b < paramArrayOfPoint3d.length; b++) {
/*  720 */       double d2 = this.radius * this.radius;
/*  721 */       double d1 = ((paramArrayOfPoint3d[b]).x - this.center.x) * ((paramArrayOfPoint3d[b]).x - this.center.x) + ((paramArrayOfPoint3d[b]).y - this.center.y) * ((paramArrayOfPoint3d[b]).y - this.center.y) + ((paramArrayOfPoint3d[b]).z - this.center.z) * ((paramArrayOfPoint3d[b]).z - this.center.z);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  727 */       if (d1 > d2) {
/*  728 */         double d3 = Math.sqrt(d1);
/*  729 */         this.radius = (this.radius + d3) * 0.5D;
/*  730 */         double d4 = d3 - this.radius;
/*  731 */         this.center.x = (this.radius * this.center.x + d4 * (paramArrayOfPoint3d[b]).x) / d3;
/*  732 */         this.center.y = (this.radius * this.center.y + d4 * (paramArrayOfPoint3d[b]).y) / d3;
/*  733 */         this.center.z = (this.radius * this.center.z + d4 * (paramArrayOfPoint3d[b]).z) / d3;
/*      */       } 
/*      */     } 
/*      */     
/*  737 */     updateBoundsStates();
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
/*      */   public void transform(Bounds paramBounds, Transform3D paramTransform3D) {
/*  750 */     if (paramBounds == null || paramBounds.boundsIsEmpty) {
/*      */       
/*  752 */       this.center.x = this.center.y = this.center.z = 0.0D;
/*  753 */       this.radius = -1.0D;
/*  754 */       updateBoundsStates();
/*      */       
/*      */       return;
/*      */     } 
/*  758 */     if (paramBounds.boundsIsInfinite) {
/*  759 */       this.center.x = this.center.y = this.center.z = 0.0D;
/*  760 */       this.radius = Double.POSITIVE_INFINITY;
/*  761 */       updateBoundsStates();
/*      */       
/*      */       return;
/*      */     } 
/*  765 */     if (paramBounds.boundId == 1) {
/*  766 */       if (this.tmpBox == null) {
/*  767 */         this.tmpBox = new BoundingBox((BoundingBox)paramBounds);
/*      */       } else {
/*  769 */         this.tmpBox.set((BoundingBox)paramBounds);
/*      */       } 
/*  771 */       this.tmpBox.transform(paramTransform3D);
/*  772 */       set(this.tmpBox);
/*  773 */     } else if (paramBounds.boundId == 2) {
/*  774 */       paramTransform3D.transform(((BoundingSphere)paramBounds).center, this.center);
/*      */       
/*  776 */       double d = paramTransform3D.getDistanceScale();
/*  777 */       ((BoundingSphere)paramBounds).radius *= d;
/*  778 */       if (Double.isNaN(this.radius)) {
/*      */         
/*  780 */         this.center.x = this.center.y = this.center.z = 0.0D;
/*  781 */         this.radius = -1.0D;
/*  782 */         updateBoundsStates();
/*      */         return;
/*      */       } 
/*  785 */     } else if (paramBounds.boundId == 4) {
/*  786 */       if (this.tmpPolytope == null) {
/*  787 */         this.tmpPolytope = new BoundingPolytope((BoundingPolytope)paramBounds);
/*      */       } else {
/*  789 */         this.tmpPolytope.set((BoundingPolytope)paramBounds);
/*      */       } 
/*  791 */       this.tmpPolytope.transform(paramTransform3D);
/*  792 */       set(this.tmpPolytope);
/*      */     } else {
/*  794 */       throw new IllegalArgumentException(J3dI18N.getString("BoundingSphere5"));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void transform(Transform3D paramTransform3D) {
/*  804 */     if (this.boundsIsInfinite) {
/*      */       return;
/*      */     }
/*  807 */     paramTransform3D.transform(this.center);
/*  808 */     double d = paramTransform3D.getDistanceScale();
/*  809 */     this.radius *= d;
/*  810 */     if (Double.isNaN(this.radius)) {
/*      */       
/*  812 */       this.center.x = this.center.y = this.center.z = 0.0D;
/*  813 */       this.radius = -1.0D;
/*  814 */       updateBoundsStates();
/*      */       return;
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
/*      */   boolean intersect(Point3d paramPoint3d, Vector3d paramVector3d, Point4d paramPoint4d) {
/*  828 */     if (this.boundsIsEmpty) {
/*  829 */       return false;
/*      */     }
/*      */     
/*  832 */     if (this.boundsIsInfinite) {
/*  833 */       paramPoint4d.x = paramPoint3d.x;
/*  834 */       paramPoint4d.y = paramPoint3d.y;
/*  835 */       paramPoint4d.z = paramPoint3d.z;
/*  836 */       paramPoint4d.w = 0.0D;
/*  837 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  841 */     Vector3d vector3d = new Vector3d();
/*  842 */     Point3d point3d = new Point3d();
/*      */     
/*  844 */     this.center.x -= paramPoint3d.x;
/*  845 */     this.center.y -= paramPoint3d.y;
/*  846 */     this.center.z -= paramPoint3d.z;
/*      */     
/*  848 */     double d1 = point3d.x * point3d.x + point3d.y * point3d.y + point3d.z * point3d.z;
/*      */     
/*  850 */     double d2 = this.radius * this.radius;
/*  851 */     if (d1 < d2)
/*      */     {
/*  853 */       return true;
/*      */     }
/*      */     
/*  856 */     double d5 = 1.0D / Math.sqrt(paramVector3d.x * paramVector3d.x + paramVector3d.y * paramVector3d.y + paramVector3d.z * paramVector3d.z);
/*      */ 
/*      */     
/*  859 */     paramVector3d.x *= d5;
/*  860 */     paramVector3d.y *= d5;
/*  861 */     paramVector3d.z *= d5;
/*  862 */     double d3 = point3d.x * vector3d.x + point3d.y * vector3d.y + point3d.z * vector3d.z;
/*      */     
/*  864 */     if (d3 <= 0.0D)
/*      */     {
/*  866 */       return false;
/*      */     }
/*      */     
/*  869 */     double d4 = d2 - d1 + d3 * d3;
/*      */     
/*  871 */     if (d4 > 0.0D) {
/*  872 */       double d = d3 - Math.sqrt(d4);
/*      */       
/*  874 */       paramPoint4d.x = paramPoint3d.x + vector3d.x * d;
/*  875 */       paramPoint4d.y = paramPoint3d.y + vector3d.y * d;
/*  876 */       paramPoint4d.z = paramPoint3d.z + vector3d.z * d;
/*  877 */       paramPoint4d.w = d;
/*  878 */       return true;
/*      */     } 
/*      */     
/*  881 */     return false;
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
/*      */   boolean intersect(Point3d paramPoint3d, Point4d paramPoint4d) {
/*  895 */     if (this.boundsIsEmpty) {
/*  896 */       return false;
/*      */     }
/*      */     
/*  899 */     if (this.boundsIsInfinite) {
/*  900 */       paramPoint4d.x = paramPoint3d.x;
/*  901 */       paramPoint4d.y = paramPoint3d.y;
/*  902 */       paramPoint4d.z = paramPoint3d.z;
/*  903 */       paramPoint4d.w = 0.0D;
/*  904 */       return true;
/*      */     } 
/*      */     
/*  907 */     double d1 = paramPoint3d.x - this.center.x;
/*  908 */     double d2 = paramPoint3d.y - this.center.y;
/*  909 */     double d3 = paramPoint3d.z - this.center.z;
/*      */     
/*  911 */     double d4 = d1 * d1 + d2 * d2 + d3 * d3;
/*  912 */     if (d4 > this.radius * this.radius) {
/*  913 */       return false;
/*      */     }
/*  915 */     paramPoint4d.x = paramPoint3d.x;
/*  916 */     paramPoint4d.y = paramPoint3d.y;
/*  917 */     paramPoint4d.z = paramPoint3d.z;
/*  918 */     paramPoint4d.w = Math.sqrt(d4);
/*  919 */     return true;
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
/*      */   boolean intersect(Point3d paramPoint3d1, Point3d paramPoint3d2, Point4d paramPoint4d) {
/*  933 */     if (this.boundsIsEmpty) {
/*  934 */       return false;
/*      */     }
/*      */     
/*  937 */     if (this.boundsIsInfinite) {
/*  938 */       paramPoint4d.x = paramPoint3d1.x;
/*  939 */       paramPoint4d.y = paramPoint3d1.y;
/*  940 */       paramPoint4d.z = paramPoint3d1.z;
/*  941 */       paramPoint4d.w = 0.0D;
/*  942 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  946 */     Vector3d vector3d1 = new Vector3d();
/*  947 */     Point3d point3d = new Point3d();
/*  948 */     Vector3d vector3d2 = new Vector3d();
/*      */     
/*  950 */     this.center.x -= paramPoint3d1.x;
/*  951 */     this.center.y -= paramPoint3d1.y;
/*  952 */     this.center.z -= paramPoint3d1.z;
/*  953 */     vector3d2.x = paramPoint3d2.x - paramPoint3d1.x;
/*  954 */     vector3d2.y = paramPoint3d2.y - paramPoint3d1.y;
/*  955 */     vector3d2.z = paramPoint3d2.z - paramPoint3d1.z;
/*  956 */     double d5 = 1.0D / Math.sqrt(vector3d2.x * vector3d2.x + vector3d2.y * vector3d2.y + vector3d2.z * vector3d2.z);
/*      */ 
/*      */     
/*  959 */     vector3d2.x *= d5;
/*  960 */     vector3d2.y *= d5;
/*  961 */     vector3d2.z *= d5;
/*      */ 
/*      */     
/*  964 */     double d1 = point3d.x * point3d.x + point3d.y * point3d.y + point3d.z * point3d.z;
/*      */     
/*  966 */     double d2 = this.radius * this.radius;
/*  967 */     if (d1 < d2)
/*      */     {
/*  969 */       return true;
/*      */     }
/*      */     
/*  972 */     double d3 = point3d.x * vector3d1.x + point3d.y * vector3d1.y + point3d.z * vector3d1.z;
/*      */     
/*  974 */     if (d3 <= 0.0D)
/*      */     {
/*  976 */       return false;
/*      */     }
/*      */     
/*  979 */     double d4 = d2 - d1 + d3 * d3;
/*      */     
/*  981 */     if (d4 > 0.0D) {
/*  982 */       double d = d3 - Math.sqrt(d4);
/*  983 */       if (d * d <= (paramPoint3d2.x - paramPoint3d1.x) * (paramPoint3d2.x - paramPoint3d1.x) + (paramPoint3d2.y - paramPoint3d1.y) * (paramPoint3d2.y - paramPoint3d1.y) + (paramPoint3d2.z - paramPoint3d1.z) * (paramPoint3d2.z - paramPoint3d1.z)) {
/*      */ 
/*      */ 
/*      */         
/*  987 */         paramPoint4d.x = paramPoint3d1.x + vector3d1.x * d;
/*  988 */         paramPoint4d.y = paramPoint3d1.y + vector3d1.x * d;
/*  989 */         paramPoint4d.z = paramPoint3d1.z + vector3d1.x * d;
/*  990 */         paramPoint4d.w = d;
/*  991 */         return true;
/*      */       } 
/*      */     } 
/*  994 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean intersect(Point3d paramPoint3d, Vector3d paramVector3d) {
/* 1005 */     if (this.boundsIsEmpty) {
/* 1006 */       return false;
/*      */     }
/*      */     
/* 1009 */     if (this.boundsIsInfinite) {
/* 1010 */       return true;
/*      */     }
/*      */ 
/*      */     
/* 1014 */     Vector3d vector3d = new Vector3d();
/* 1015 */     Point3d point3d = new Point3d();
/*      */     
/* 1017 */     this.center.x -= paramPoint3d.x;
/* 1018 */     this.center.y -= paramPoint3d.y;
/* 1019 */     this.center.z -= paramPoint3d.z;
/*      */     
/* 1021 */     double d1 = point3d.x * point3d.x + point3d.y * point3d.y + point3d.z * point3d.z;
/*      */     
/* 1023 */     double d2 = this.radius * this.radius;
/* 1024 */     if (d1 < d2)
/*      */     {
/* 1026 */       return true;
/*      */     }
/*      */     
/* 1029 */     double d5 = Math.sqrt(paramVector3d.x * paramVector3d.x + paramVector3d.y * paramVector3d.y + paramVector3d.z * paramVector3d.z);
/*      */ 
/*      */     
/* 1032 */     paramVector3d.x /= d5;
/* 1033 */     paramVector3d.y /= d5;
/* 1034 */     paramVector3d.z /= d5;
/* 1035 */     double d3 = point3d.x * vector3d.x + point3d.y * vector3d.y + point3d.z * vector3d.z;
/*      */     
/* 1037 */     if (d3 <= 0.0D)
/*      */     {
/* 1039 */       return false;
/*      */     }
/*      */     
/* 1042 */     double d4 = d2 - d1 + d3 * d3;
/*      */     
/* 1044 */     if (d4 > 0.0D)
/*      */     {
/* 1046 */       return true;
/*      */     }
/*      */     
/* 1049 */     return false;
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
/*      */   boolean intersect(Point3d paramPoint3d1, Vector3d paramVector3d, Point3d paramPoint3d2) {
/* 1061 */     if (this.boundsIsEmpty) {
/* 1062 */       return false;
/*      */     }
/*      */     
/* 1065 */     if (this.boundsIsInfinite) {
/* 1066 */       paramPoint3d2.x = paramPoint3d1.x;
/* 1067 */       paramPoint3d2.y = paramPoint3d1.y;
/* 1068 */       paramPoint3d2.z = paramPoint3d1.z;
/* 1069 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1073 */     Point3d point3d1 = new Point3d();
/* 1074 */     Point3d point3d2 = new Point3d();
/*      */     
/* 1076 */     this.center.x -= paramPoint3d1.x;
/* 1077 */     this.center.y -= paramPoint3d1.y;
/* 1078 */     this.center.z -= paramPoint3d1.z;
/*      */     
/* 1080 */     double d1 = point3d2.x * point3d2.x + point3d2.y * point3d2.y + point3d2.z * point3d2.z;
/*      */     
/* 1082 */     double d2 = this.radius * this.radius;
/* 1083 */     if (d1 < d2)
/*      */     {
/* 1085 */       return true;
/*      */     }
/*      */     
/* 1088 */     double d5 = Math.sqrt(paramVector3d.x * paramVector3d.x + paramVector3d.y * paramVector3d.y + paramVector3d.z * paramVector3d.z);
/*      */ 
/*      */     
/* 1091 */     point3d1.x = paramVector3d.x / d5;
/* 1092 */     point3d1.y = paramVector3d.y / d5;
/* 1093 */     point3d1.z = paramVector3d.z / d5;
/* 1094 */     double d3 = point3d2.x * point3d1.x + point3d2.y * point3d1.y + point3d2.z * point3d1.z;
/*      */     
/* 1096 */     if (d3 <= 0.0D)
/*      */     {
/* 1098 */       return false;
/*      */     }
/*      */     
/* 1101 */     double d4 = d2 - d1 + d3 * d3;
/*      */     
/* 1103 */     if (d4 > 0.0D) {
/* 1104 */       double d = d3 - Math.sqrt(d4);
/* 1105 */       paramPoint3d1.x += paramVector3d.x * d;
/* 1106 */       paramPoint3d1.y += paramVector3d.y * d;
/* 1107 */       paramPoint3d1.z += paramVector3d.z * d;
/*      */       
/* 1109 */       return true;
/*      */     } 
/*      */     
/* 1112 */     return false;
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
/*      */   public boolean intersect(Point3d paramPoint3d) {
/* 1125 */     if (this.boundsIsEmpty) {
/* 1126 */       return false;
/*      */     }
/* 1128 */     if (this.boundsIsInfinite) {
/* 1129 */       return true;
/*      */     }
/*      */     
/* 1132 */     double d1 = paramPoint3d.x - this.center.x;
/* 1133 */     double d2 = paramPoint3d.y - this.center.y;
/* 1134 */     double d3 = paramPoint3d.z - this.center.z;
/*      */     
/* 1136 */     double d4 = d1 * d1 + d2 * d2 + d3 * d3;
/* 1137 */     if (d4 > this.radius * this.radius) {
/* 1138 */       return false;
/*      */     }
/* 1140 */     return true;
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
/* 1153 */   public boolean isEmpty() { return this.boundsIsEmpty; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1162 */   boolean intersect(Bounds paramBounds, Point4d paramPoint4d) { return intersect(paramBounds); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean intersect(Bounds paramBounds) {
/* 1175 */     if (paramBounds == null) {
/* 1176 */       return false;
/*      */     }
/*      */     
/* 1179 */     if (this.boundsIsEmpty || paramBounds.boundsIsEmpty) {
/* 1180 */       return false;
/*      */     }
/*      */     
/* 1183 */     if (this.boundsIsInfinite || paramBounds.boundsIsInfinite) {
/* 1184 */       return true;
/*      */     }
/*      */     
/* 1187 */     if (paramBounds.boundId == 1) {
/* 1188 */       BoundingBox boundingBox = (BoundingBox)paramBounds;
/* 1189 */       double d1 = 0.0D;
/* 1190 */       double d2 = this.radius * this.radius;
/*      */ 
/*      */ 
/*      */       
/* 1194 */       if (this.center.x < boundingBox.lower.x) {
/* 1195 */         d1 = (this.center.x - boundingBox.lower.x) * (this.center.x - boundingBox.lower.x);
/*      */       }
/* 1197 */       else if (this.center.x > boundingBox.upper.x) {
/* 1198 */         d1 = (this.center.x - boundingBox.upper.x) * (this.center.x - boundingBox.upper.x);
/*      */       } 
/* 1200 */       if (this.center.y < boundingBox.lower.y) {
/* 1201 */         d1 += (this.center.y - boundingBox.lower.y) * (this.center.y - boundingBox.lower.y);
/*      */       }
/* 1203 */       else if (this.center.y > boundingBox.upper.y) {
/* 1204 */         d1 += (this.center.y - boundingBox.upper.y) * (this.center.y - boundingBox.upper.y);
/*      */       } 
/* 1206 */       if (this.center.z < boundingBox.lower.z) {
/* 1207 */         d1 += (this.center.z - boundingBox.lower.z) * (this.center.z - boundingBox.lower.z);
/*      */       }
/* 1209 */       else if (this.center.z > boundingBox.upper.z) {
/* 1210 */         d1 += (this.center.z - boundingBox.upper.z) * (this.center.z - boundingBox.upper.z);
/*      */       } 
/* 1212 */       return (d1 <= d2);
/* 1213 */     }  if (paramBounds.boundId == 2) {
/* 1214 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/* 1215 */       double d2 = this.radius + boundingSphere.radius;
/* 1216 */       d2 *= d2;
/* 1217 */       double d1 = this.center.distanceSquared(boundingSphere.center);
/* 1218 */       return (d1 <= d2);
/* 1219 */     }  if (paramBounds.boundId == 4) {
/* 1220 */       return intersect_ptope_sphere((BoundingPolytope)paramBounds, this);
/*      */     }
/* 1222 */     throw new IllegalArgumentException(J3dI18N.getString("BoundingSphere6"));
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
/* 1236 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0) {
/* 1237 */       return false;
/*      */     }
/*      */     
/* 1240 */     if (this.boundsIsEmpty) {
/* 1241 */       return false;
/*      */     }
/*      */     
/* 1244 */     for (byte b = 0; b < paramArrayOfBounds.length; b++) {
/* 1245 */       if (paramArrayOfBounds[b] != null && !(paramArrayOfBounds[b]).boundsIsEmpty) {
/* 1246 */         if (this.boundsIsInfinite || (paramArrayOfBounds[b]).boundsIsInfinite)
/* 1247 */           return true; 
/* 1248 */         if ((paramArrayOfBounds[b]).boundId == 1) {
/* 1249 */           if (intersect(paramArrayOfBounds[b])) return true; 
/* 1250 */         } else if ((paramArrayOfBounds[b]).boundId == 2) {
/* 1251 */           BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b];
/* 1252 */           double d2 = this.radius + boundingSphere.radius;
/* 1253 */           d2 *= d2;
/* 1254 */           double d1 = this.center.distanceSquared(boundingSphere.center);
/* 1255 */           if (d1 <= d2) {
/* 1256 */             return true;
/*      */           }
/* 1258 */         } else if ((paramArrayOfBounds[b]).boundId == 4) {
/* 1259 */           if (intersect(paramArrayOfBounds[b])) return true; 
/*      */         } else {
/* 1261 */           throw new IllegalArgumentException(J3dI18N.getString("BoundingSphere7"));
/*      */         } 
/*      */       } 
/*      */     } 
/* 1265 */     return false;
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
/*      */   public boolean intersect(Bounds paramBounds, BoundingSphere paramBoundingSphere) {
/* 1278 */     if (paramBounds == null || this.boundsIsEmpty || paramBounds.boundsIsEmpty) {
/*      */       
/* 1280 */       paramBoundingSphere.center.x = paramBoundingSphere.center.y = paramBoundingSphere.center.z = 0.0D;
/*      */       
/* 1282 */       paramBoundingSphere.radius = -1.0D;
/* 1283 */       paramBoundingSphere.updateBoundsStates();
/* 1284 */       return false;
/*      */     } 
/*      */     
/* 1287 */     if (this.boundsIsInfinite && !paramBounds.boundsIsInfinite) {
/* 1288 */       paramBoundingSphere.set(paramBounds);
/* 1289 */       return true;
/*      */     } 
/* 1291 */     if (!this.boundsIsInfinite && paramBounds.boundsIsInfinite) {
/* 1292 */       paramBoundingSphere.set(this);
/* 1293 */       return true;
/*      */     } 
/* 1295 */     if (this.boundsIsInfinite && paramBounds.boundsIsInfinite) {
/* 1296 */       paramBoundingSphere.set(this);
/* 1297 */       return true;
/* 1298 */     }  if (paramBounds.boundId == 1) {
/* 1299 */       BoundingBox boundingBox1 = new BoundingBox();
/* 1300 */       BoundingBox boundingBox2 = (BoundingBox)paramBounds;
/* 1301 */       if (intersect(boundingBox2)) {
/* 1302 */         BoundingBox boundingBox = new BoundingBox(this);
/* 1303 */         boundingBox.intersect(boundingBox2, boundingBox1);
/* 1304 */         paramBoundingSphere.set(boundingBox1);
/* 1305 */         return true;
/*      */       } 
/*      */       
/* 1308 */       paramBoundingSphere.center.x = paramBoundingSphere.center.y = paramBoundingSphere.center.z = 0.0D;
/*      */       
/* 1310 */       paramBoundingSphere.radius = -1.0D;
/* 1311 */       paramBoundingSphere.updateBoundsStates();
/* 1312 */       return false;
/*      */     } 
/* 1314 */     if (paramBounds.boundId == 2) {
/* 1315 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/*      */ 
/*      */       
/* 1318 */       double d = Math.sqrt((this.center.x - boundingSphere.center.x) * (this.center.x - boundingSphere.center.x) + (this.center.y - boundingSphere.center.y) * (this.center.y - boundingSphere.center.y) + (this.center.z - boundingSphere.center.z) * (this.center.z - boundingSphere.center.z));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1323 */       paramBoundingSphere.center.x = paramBoundingSphere.center.y = paramBoundingSphere.center.z = 0.0D;
/*      */       
/* 1325 */       paramBoundingSphere.radius = -1.0D;
/* 1326 */       boolean bool = false;
/* 1327 */       if (d + this.radius <= boundingSphere.radius) {
/* 1328 */         paramBoundingSphere.center.x = this.center.x;
/* 1329 */         paramBoundingSphere.center.y = this.center.y;
/* 1330 */         paramBoundingSphere.center.z = this.center.z;
/* 1331 */         paramBoundingSphere.radius = this.radius;
/* 1332 */         bool = true;
/* 1333 */       } else if (d + boundingSphere.radius <= this.radius) {
/* 1334 */         paramBoundingSphere.center.x = boundingSphere.center.x;
/* 1335 */         paramBoundingSphere.center.y = boundingSphere.center.y;
/* 1336 */         paramBoundingSphere.center.z = boundingSphere.center.z;
/* 1337 */         paramBoundingSphere.radius = boundingSphere.radius;
/* 1338 */         bool = true;
/*      */       } else {
/*      */         
/* 1341 */         double d2 = (d * d + this.radius * this.radius - boundingSphere.radius * boundingSphere.radius) / 2.0D * d;
/* 1342 */         paramBoundingSphere.radius = Math.sqrt(this.radius * this.radius - d2 * d2);
/* 1343 */         double d1 = d2 / d;
/* 1344 */         this.center.x += (boundingSphere.center.x - this.center.x) * d1;
/* 1345 */         this.center.y += (boundingSphere.center.y - this.center.y) * d1;
/* 1346 */         this.center.z += (boundingSphere.center.z - this.center.z) * d1;
/* 1347 */         bool = true;
/*      */       } 
/*      */       
/* 1350 */       paramBoundingSphere.updateBoundsStates();
/* 1351 */       return bool;
/*      */     } 
/* 1353 */     if (paramBounds.boundId == 4) {
/* 1354 */       BoundingBox boundingBox = new BoundingBox();
/*      */       
/* 1356 */       BoundingPolytope boundingPolytope = (BoundingPolytope)paramBounds;
/* 1357 */       if (intersect(boundingPolytope)) {
/* 1358 */         BoundingBox boundingBox1 = new BoundingBox(this);
/* 1359 */         BoundingBox boundingBox2 = new BoundingBox(boundingPolytope);
/* 1360 */         boundingBox1.intersect(boundingBox2, boundingBox);
/* 1361 */         paramBoundingSphere.set(boundingBox);
/* 1362 */         return true;
/*      */       } 
/*      */       
/* 1365 */       paramBoundingSphere.center.x = paramBoundingSphere.center.y = paramBoundingSphere.center.z = 0.0D;
/*      */       
/* 1367 */       paramBoundingSphere.radius = -1.0D;
/* 1368 */       paramBoundingSphere.updateBoundsStates();
/* 1369 */       return false;
/*      */     } 
/*      */     
/* 1372 */     throw new IllegalArgumentException(J3dI18N.getString("BoundingSphere8"));
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
/*      */   public boolean intersect(Bounds[] paramArrayOfBounds, BoundingSphere paramBoundingSphere) {
/* 1385 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0 || this.boundsIsEmpty) {
/*      */       
/* 1387 */       paramBoundingSphere.center.x = paramBoundingSphere.center.y = paramBoundingSphere.center.z = 0.0D;
/*      */       
/* 1389 */       paramBoundingSphere.radius = -1.0D;
/* 1390 */       paramBoundingSphere.updateBoundsStates();
/* 1391 */       return false;
/*      */     } 
/*      */     
/* 1394 */     byte b = 0;
/*      */ 
/*      */     
/* 1397 */     while (paramArrayOfBounds[b] == null && b < paramArrayOfBounds.length) {
/* 1398 */       b++;
/*      */     }
/*      */     
/* 1401 */     if (b >= paramArrayOfBounds.length) {
/*      */       
/* 1403 */       paramBoundingSphere.center.x = paramBoundingSphere.center.y = paramBoundingSphere.center.z = 0.0D;
/*      */       
/* 1405 */       paramBoundingSphere.radius = -1.0D;
/* 1406 */       paramBoundingSphere.updateBoundsStates();
/* 1407 */       return false;
/*      */     } 
/*      */     
/* 1410 */     boolean bool = false;
/*      */     
/* 1412 */     Point3d point3d = new Point3d();
/* 1413 */     BoundingBox boundingBox = new BoundingBox();
/*      */     
/* 1415 */     for (b = 0; b < paramArrayOfBounds.length; b++) {
/* 1416 */       if (paramArrayOfBounds[b] != null && !(paramArrayOfBounds[b]).boundsIsEmpty)
/* 1417 */         if ((paramArrayOfBounds[b]).boundId == 1) {
/* 1418 */           BoundingBox boundingBox1 = (BoundingBox)paramArrayOfBounds[b];
/* 1419 */           if (intersect(boundingBox1)) {
/* 1420 */             BoundingBox boundingBox2 = new BoundingBox(this);
/* 1421 */             boundingBox2.intersect(boundingBox1, boundingBox);
/* 1422 */             if (bool) {
/* 1423 */               paramBoundingSphere.combine(boundingBox);
/*      */             } else {
/* 1425 */               paramBoundingSphere.set(boundingBox);
/* 1426 */               bool = true;
/*      */             } 
/*      */           } 
/* 1429 */         } else if ((paramArrayOfBounds[b]).boundId == 2) {
/* 1430 */           BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b];
/*      */           
/* 1432 */           double d = Math.sqrt((this.center.x - boundingSphere.center.x) * (this.center.x - boundingSphere.center.x) + (this.center.y - boundingSphere.center.y) * (this.center.y - boundingSphere.center.y) + (this.center.z - boundingSphere.center.z) * (this.center.z - boundingSphere.center.z));
/*      */ 
/*      */           
/* 1435 */           if (d <= this.radius + boundingSphere.radius) {
/* 1436 */             if (d + this.radius <= boundingSphere.radius) {
/* 1437 */               if (bool) {
/* 1438 */                 paramBoundingSphere.combine(this);
/*      */               } else {
/* 1440 */                 paramBoundingSphere.center.x = this.center.x;
/* 1441 */                 paramBoundingSphere.center.y = this.center.y;
/* 1442 */                 paramBoundingSphere.center.z = this.center.z;
/* 1443 */                 paramBoundingSphere.radius = this.radius;
/* 1444 */                 bool = true;
/* 1445 */                 paramBoundingSphere.updateBoundsStates();
/*      */               } 
/* 1447 */             } else if (d + boundingSphere.radius <= this.radius) {
/* 1448 */               if (bool) {
/* 1449 */                 paramBoundingSphere.combine(boundingSphere);
/*      */               } else {
/* 1451 */                 paramBoundingSphere.center.x = this.center.x;
/* 1452 */                 paramBoundingSphere.center.y = this.center.y;
/* 1453 */                 paramBoundingSphere.center.z = this.center.z;
/* 1454 */                 paramBoundingSphere.radius = boundingSphere.radius;
/* 1455 */                 bool = true;
/* 1456 */                 paramBoundingSphere.updateBoundsStates();
/*      */               } 
/*      */             } else {
/*      */               
/* 1460 */               double d3 = (d * d + this.radius * this.radius - boundingSphere.radius * boundingSphere.radius) / 2.0D * d;
/* 1461 */               double d1 = Math.sqrt(this.radius * this.radius - d3 * d3);
/* 1462 */               double d2 = d3 / d;
/* 1463 */               this.center.x += (boundingSphere.center.x - this.center.x) * d2;
/* 1464 */               this.center.y += (boundingSphere.center.y - this.center.y) * d2;
/* 1465 */               this.center.z += (boundingSphere.center.z - this.center.z) * d2;
/* 1466 */               if (bool) {
/* 1467 */                 BoundingSphere boundingSphere1 = new BoundingSphere(point3d, d1);
/*      */                 
/* 1469 */                 paramBoundingSphere.combine(boundingSphere1);
/*      */               } else {
/* 1471 */                 paramBoundingSphere.setRadius(d1);
/* 1472 */                 paramBoundingSphere.setCenter(point3d);
/* 1473 */                 bool = true;
/*      */               } 
/*      */             } 
/*      */           }
/* 1477 */         } else if ((paramArrayOfBounds[b]).boundId == 4) {
/* 1478 */           BoundingPolytope boundingPolytope = (BoundingPolytope)paramArrayOfBounds[b];
/* 1479 */           if (intersect(boundingPolytope)) {
/* 1480 */             BoundingBox boundingBox1 = new BoundingBox(this);
/* 1481 */             BoundingBox boundingBox2 = new BoundingBox(boundingPolytope);
/* 1482 */             boundingBox1.intersect(boundingBox2, boundingBox);
/* 1483 */             if (bool) {
/* 1484 */               paramBoundingSphere.combine(boundingBox);
/*      */             } else {
/* 1486 */               paramBoundingSphere.set(boundingBox);
/* 1487 */               bool = true;
/*      */             } 
/*      */           } 
/*      */         } else {
/* 1491 */           throw new IllegalArgumentException(J3dI18N.getString("BoundingSphere9"));
/*      */         }  
/*      */     } 
/* 1494 */     if (!bool) {
/*      */       
/* 1496 */       paramBoundingSphere.center.x = paramBoundingSphere.center.y = paramBoundingSphere.center.z = 0.0D;
/*      */       
/* 1498 */       paramBoundingSphere.radius = -1.0D;
/* 1499 */       paramBoundingSphere.updateBoundsStates();
/*      */     } 
/* 1501 */     return bool;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Bounds closestIntersection(Bounds[] paramArrayOfBounds) {
/* 1511 */     if (paramArrayOfBounds == null || paramArrayOfBounds.length <= 0) {
/* 1512 */       return null;
/*      */     }
/*      */     
/* 1515 */     if (this.boundsIsEmpty) {
/* 1516 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1520 */     double d1 = 0.0D, d2 = 0.0D, d3 = 0.0D;
/* 1521 */     boolean bool1 = false;
/*      */     
/* 1523 */     boolean bool2 = false;
/* 1524 */     double d4 = Double.MAX_VALUE;
/* 1525 */     byte b2 = 0;
/*      */ 
/*      */     
/* 1528 */     for (byte b1 = 0; b1 < paramArrayOfBounds.length; b1++) {
/* 1529 */       if (paramArrayOfBounds[b1] != null)
/*      */       {
/* 1531 */         if (intersect(paramArrayOfBounds[b1])) {
/* 1532 */           bool2 = true;
/* 1533 */           if ((paramArrayOfBounds[b1]).boundId == 1) {
/* 1534 */             double d6; BoundingBox boundingBox = (BoundingBox)paramArrayOfBounds[b1];
/* 1535 */             d1 = (boundingBox.upper.x + boundingBox.lower.x) / 2.0D;
/* 1536 */             d2 = (boundingBox.upper.y + boundingBox.lower.y) / 2.0D;
/* 1537 */             d3 = (boundingBox.upper.z + boundingBox.lower.z) / 2.0D;
/* 1538 */             double d5 = Math.sqrt((this.center.x - d1) * (this.center.x - d1) + (this.center.y - d2) * (this.center.y - d2) + (this.center.z - d3) * (this.center.z - d3));
/*      */ 
/*      */             
/* 1541 */             if ((this.center.x - boundingBox.lower.x) * (this.center.x - boundingBox.lower.x) > (this.center.x - boundingBox.upper.x) * (this.center.x - boundingBox.upper.x)) {
/*      */               
/* 1543 */               d6 = (this.center.x - boundingBox.lower.x) * (this.center.x - boundingBox.lower.x);
/*      */             } else {
/* 1545 */               d6 = (this.center.x - boundingBox.upper.x) * (this.center.x - boundingBox.upper.x);
/*      */             } 
/* 1547 */             if ((this.center.y - boundingBox.lower.y) * (this.center.y - boundingBox.lower.y) > (this.center.y - boundingBox.upper.y) * (this.center.y - boundingBox.upper.y)) {
/*      */               
/* 1549 */               d6 += (this.center.y - boundingBox.lower.y) * (this.center.y - boundingBox.lower.y);
/*      */             } else {
/* 1551 */               d6 += (this.center.y - boundingBox.upper.y) * (this.center.y - boundingBox.upper.y);
/*      */             } 
/* 1553 */             if ((this.center.z - boundingBox.lower.z) * (this.center.z - boundingBox.lower.z) > (this.center.z - boundingBox.upper.z) * (this.center.z - boundingBox.upper.z)) {
/*      */               
/* 1555 */               d6 += (this.center.z - boundingBox.lower.z) * (this.center.z - boundingBox.lower.z);
/*      */             } else {
/* 1557 */               d6 += (this.center.z - boundingBox.upper.z) * (this.center.z - boundingBox.upper.z);
/*      */             } 
/* 1559 */             double d7 = this.radius * this.radius;
/* 1560 */             if (d6 <= d7) {
/* 1561 */               if (!bool1) {
/* 1562 */                 b2 = b1;
/* 1563 */                 d4 = d5;
/* 1564 */                 bool1 = true;
/*      */               }
/* 1566 */               else if (d5 < d4) {
/* 1567 */                 b2 = b1;
/* 1568 */                 d4 = d5;
/*      */               }
/*      */             
/* 1571 */             } else if (!bool1 && 
/* 1572 */               d5 < d4) {
/* 1573 */               b2 = b1;
/* 1574 */               d4 = d5;
/*      */             }
/*      */           
/* 1577 */           } else if ((paramArrayOfBounds[b1]).boundId == 2) {
/* 1578 */             BoundingSphere boundingSphere = (BoundingSphere)paramArrayOfBounds[b1];
/* 1579 */             double d = Math.sqrt((this.center.x - boundingSphere.center.x) * (this.center.x - boundingSphere.center.x) + (this.center.y - boundingSphere.center.y) * (this.center.y - boundingSphere.center.y) + (this.center.z - boundingSphere.center.z) * (this.center.z - boundingSphere.center.z));
/*      */ 
/*      */             
/* 1582 */             if (d + boundingSphere.radius <= this.radius) {
/* 1583 */               if (!bool1) {
/* 1584 */                 b2 = b1;
/* 1585 */                 d4 = d;
/* 1586 */                 bool1 = true;
/*      */               }
/* 1588 */               else if (d < d4) {
/* 1589 */                 b2 = b1;
/* 1590 */                 d4 = d;
/*      */               }
/*      */             
/* 1593 */             } else if (!bool1 && 
/* 1594 */               d < d4) {
/* 1595 */               b2 = b1;
/* 1596 */               d4 = d;
/*      */             }
/*      */           
/*      */           }
/* 1600 */           else if ((paramArrayOfBounds[b1]).boundId == 4) {
/* 1601 */             BoundingPolytope boundingPolytope = (BoundingPolytope)paramArrayOfBounds[b1];
/* 1602 */             double d = Math.sqrt((this.center.x - boundingPolytope.centroid.x) * (this.center.x - boundingPolytope.centroid.x) + (this.center.y - boundingPolytope.centroid.y) * (this.center.y - boundingPolytope.centroid.y) + (this.center.z - boundingPolytope.centroid.z) * (this.center.z - boundingPolytope.centroid.z));
/*      */ 
/*      */             
/* 1605 */             boolean bool = true;
/* 1606 */             for (byte b = 0; b < boundingPolytope.nVerts; b++) {
/* 1607 */               double d6 = (boundingPolytope.verts[b]).x - this.center.x;
/* 1608 */               double d7 = (boundingPolytope.verts[b]).y - this.center.y;
/* 1609 */               double d8 = (boundingPolytope.verts[b]).z - this.center.z;
/*      */               
/* 1611 */               double d5 = d6 * d6 + d7 * d7 + d8 * d8;
/* 1612 */               if (d5 > this.radius * this.radius)
/* 1613 */                 bool = false; 
/*      */             } 
/* 1615 */             if (bool) {
/* 1616 */               if (!bool1) {
/* 1617 */                 b2 = b1;
/* 1618 */                 d4 = d;
/* 1619 */                 bool1 = true;
/*      */               }
/* 1621 */               else if (d < d4) {
/* 1622 */                 b2 = b1;
/* 1623 */                 d4 = d;
/*      */               }
/*      */             
/* 1626 */             } else if (!bool1 && 
/* 1627 */               d < d4) {
/* 1628 */               b2 = b1;
/* 1629 */               d4 = d;
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 1634 */             throw new IllegalArgumentException(J3dI18N.getString("BoundingSphere10"));
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/* 1639 */     if (bool2) {
/* 1640 */       return paramArrayOfBounds[b2];
/*      */     }
/* 1642 */     return null;
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
/*      */   boolean intersect(CachedFrustum paramCachedFrustum) {
/* 1655 */     if (this.boundsIsEmpty) {
/* 1656 */       return false;
/*      */     }
/*      */     
/* 1659 */     if (this.boundsIsInfinite) {
/* 1660 */       return true;
/*      */     }
/* 1662 */     for (byte b = 0; b < 6; b++) {
/* 1663 */       double d = (paramCachedFrustum.clipPlanes[b]).x * this.center.x + (paramCachedFrustum.clipPlanes[b]).y * this.center.y + (paramCachedFrustum.clipPlanes[b]).z * this.center.z + (paramCachedFrustum.clipPlanes[b]).w;
/*      */       
/* 1665 */       if (d < 0.0D && d + this.radius < 0.0D) {
/* 1666 */         return false;
/*      */       }
/*      */     } 
/* 1669 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean intersect(Vector4d[] paramArrayOfVector4d) {
/* 1680 */     if (this.boundsIsEmpty) {
/* 1681 */       return false;
/*      */     }
/*      */     
/* 1684 */     if (this.boundsIsInfinite) {
/* 1685 */       return true;
/*      */     }
/* 1687 */     for (byte b = 0; b < 6; b++) {
/* 1688 */       double d = (paramArrayOfVector4d[b]).x * this.center.x + (paramArrayOfVector4d[b]).y * this.center.y + (paramArrayOfVector4d[b]).z * this.center.z + (paramArrayOfVector4d[b]).w;
/*      */       
/* 1690 */       if (d < 0.0D && d + this.radius < 0.0D)
/*      */       {
/* 1692 */         return false;
/*      */       }
/*      */     } 
/* 1695 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1702 */   public String toString() { return new String("Center=" + this.center + "  Radius=" + this.radius); }
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateBoundsStates() {
/* 1707 */     if (checkBoundsIsNaN()) {
/* 1708 */       this.boundsIsEmpty = true;
/* 1709 */       this.boundsIsInfinite = false;
/*      */       
/*      */       return;
/*      */     } 
/* 1713 */     if (this.radius == Double.POSITIVE_INFINITY) {
/* 1714 */       this.boundsIsEmpty = false;
/* 1715 */       this.boundsIsInfinite = true;
/*      */     } else {
/*      */       
/* 1718 */       this.boundsIsInfinite = false;
/* 1719 */       if (this.radius < 0.0D) {
/* 1720 */         this.boundsIsEmpty = true;
/*      */       } else {
/* 1722 */         this.boundsIsEmpty = false;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/* 1728 */   Point3d getCenter() { return this.center; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Bounds copy(Bounds paramBounds) {
/* 1737 */     if (paramBounds != null && this.boundId == paramBounds.boundId) {
/* 1738 */       BoundingSphere boundingSphere = (BoundingSphere)paramBounds;
/* 1739 */       boundingSphere.radius = this.radius;
/* 1740 */       boundingSphere.center.x = this.center.x;
/* 1741 */       boundingSphere.center.y = this.center.y;
/* 1742 */       boundingSphere.center.z = this.center.z;
/* 1743 */       boundingSphere.boundsIsEmpty = this.boundsIsEmpty;
/* 1744 */       boundingSphere.boundsIsInfinite = this.boundsIsInfinite;
/* 1745 */       return boundingSphere;
/*      */     } 
/*      */     
/* 1748 */     return (Bounds)clone();
/*      */   }
/*      */ 
/*      */   
/*      */   boolean checkBoundsIsNaN() {
/* 1753 */     if (Double.isNaN(this.radius + this.center.x + this.center.y + this.center.z)) {
/* 1754 */       return true;
/*      */     }
/* 1756 */     return false;
/*      */   }
/*      */ 
/*      */   
/* 1760 */   int getPickType() { return 7; }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\BoundingSphere.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */