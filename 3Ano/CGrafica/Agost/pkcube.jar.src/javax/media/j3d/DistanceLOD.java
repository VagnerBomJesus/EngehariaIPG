/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import javax.vecmath.Point3f;
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
/*     */ public class DistanceLOD
/*     */   extends LOD
/*     */ {
/*     */   private double[] distances;
/*     */   private Point3f position;
/*     */   private Point3f center;
/*     */   private Point3f viewPosition;
/*     */   
/*     */   public DistanceLOD() {
/*  46 */     this.position = new Point3f(0.0F, 0.0F, 0.0F);
/*     */ 
/*     */     
/*  49 */     this.center = new Point3f();
/*  50 */     this.viewPosition = new Point3f();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     this.distances = new double[1];
/*  59 */     this.distances[0] = 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DistanceLOD(float[] paramArrayOfFloat) {
/*     */     this.position = new Point3f(0.0F, 0.0F, 0.0F);
/*     */     this.center = new Point3f();
/*     */     this.viewPosition = new Point3f();
/*  68 */     this.distances = new double[paramArrayOfFloat.length];
/*     */     
/*  70 */     for (byte b = 0; b < paramArrayOfFloat.length; b++) {
/*  71 */       this.distances[b] = paramArrayOfFloat[b];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DistanceLOD(float[] paramArrayOfFloat, Point3f paramPoint3f) {
/*     */     this.position = new Point3f(0.0F, 0.0F, 0.0F);
/*     */     this.center = new Point3f();
/*     */     this.viewPosition = new Point3f();
/*  82 */     this.distances = new double[paramArrayOfFloat.length];
/*     */     
/*  84 */     for (byte b = 0; b < paramArrayOfFloat.length; b++) {
/*  85 */       this.distances[b] = paramArrayOfFloat[b];
/*     */     }
/*  87 */     this.position.set(paramPoint3f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPosition(Point3f paramPoint3f) {
/*  97 */     if (((NodeRetained)this.retained).staticTransform != null) {
/*  98 */       ((NodeRetained)this.retained).staticTransform.transform.transform(paramPoint3f, this.position);
/*     */     } else {
/*     */       
/* 101 */       this.position.set(paramPoint3f);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getPosition(Point3f paramPoint3f) {
/* 111 */     if (((NodeRetained)this.retained).staticTransform != null) {
/* 112 */       Transform3D transform3D = ((NodeRetained)this.retained).staticTransform.getInvTransform();
/*     */       
/* 114 */       transform3D.transform(this.position, paramPoint3f);
/*     */     } else {
/* 116 */       paramPoint3f.set(this.position);
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
/* 127 */   public int numDistances() { return this.distances.length; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public double getDistance(int paramInt) { return this.distances[paramInt]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public void setDistance(int paramInt, double paramDouble) { this.distances[paramInt] = paramDouble; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public void initialize() { wakeupOn(this.wakeupFrame); }
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
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/* 165 */     View view = getView();
/* 166 */     if (view == null) {
/* 167 */       wakeupOn(this.wakeupFrame);
/*     */       
/*     */       return;
/*     */     } 
/* 171 */     ViewPlatform viewPlatform = view.getViewPlatform();
/* 172 */     if (viewPlatform == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 177 */     double d = 0.0D;
/* 178 */     int k = 0;
/*     */     
/* 180 */     Transform3D transform3D = new Transform3D();
/*     */     
/* 182 */     transform3D.set(((NodeRetained)this.retained).getCurrentLocalToVworld());
/*     */ 
/*     */ 
/*     */     
/* 186 */     transform3D.transform(this.position, this.center);
/*     */ 
/*     */     
/* 189 */     this.viewPosition.x = (float)((ViewPlatformRetained)viewPlatform.retained).schedSphere.center.x;
/* 190 */     this.viewPosition.y = (float)((ViewPlatformRetained)viewPlatform.retained).schedSphere.center.y;
/* 191 */     this.viewPosition.z = (float)((ViewPlatformRetained)viewPlatform.retained).schedSphere.center.z;
/* 192 */     d = this.center.distance(this.viewPosition);
/*     */ 
/*     */ 
/*     */     
/* 196 */     d /= transform3D.getDistanceScale();
/*     */     
/* 198 */     int i = numSwitches();
/*     */     
/* 200 */     k = this.distances.length;
/*     */     
/* 202 */     if (d <= this.distances[0]) {
/* 203 */       k = 0;
/*     */     } else {
/* 205 */       for (int m = 1; m < this.distances.length; m++) {
/* 206 */         if (d > this.distances[m - true] && d <= this.distances[m]) {
/*     */           
/* 208 */           k = m;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 214 */     for (int j = i - 1; j >= 0; j--) {
/* 215 */       Switch switch = getSwitch(j);
/*     */ 
/*     */       
/* 218 */       if (((SwitchRetained)switch.retained).getWhichChild() != k)
/*     */       {
/* 220 */         switch.setWhichChild(k);
/*     */       }
/*     */     } 
/*     */     
/* 224 */     wakeupOn(this.wakeupFrame);
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
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 243 */     DistanceLOD distanceLOD = new DistanceLOD();
/* 244 */     distanceLOD.duplicateNode(this, paramBoolean);
/* 245 */     return distanceLOD;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 271 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 273 */     DistanceLOD distanceLOD = (DistanceLOD)paramNode;
/*     */     
/* 275 */     int i = distanceLOD.numDistances();
/*     */ 
/*     */     
/* 278 */     this.distances = new double[i];
/*     */     
/* 280 */     for (byte b = 0; b < i; b++) {
/* 281 */       setDistance(b, distanceLOD.getDistance(b));
/*     */     }
/* 283 */     Point3f point3f = new Point3f();
/* 284 */     distanceLOD.getPosition(point3f);
/* 285 */     setPosition(point3f);
/*     */   }
/*     */ 
/*     */   
/* 289 */   void mergeTransform(TransformGroupRetained paramTransformGroupRetained) { paramTransformGroupRetained.transform.transform(this.position, this.position); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\DistanceLOD.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */