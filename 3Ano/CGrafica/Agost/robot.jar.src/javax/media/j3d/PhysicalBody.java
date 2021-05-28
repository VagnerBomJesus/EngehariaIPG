/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Vector3d;
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
/*     */ public class PhysicalBody
/*     */ {
/*     */   private static final double HALF_IPD = 0.033D;
/*     */   private static final double EAR_X = 0.08D;
/*     */   private static final double EAR_Y = -0.03D;
/*     */   private static final double EAR_Z = 0.095D;
/*     */   Point3d leftEyePosition;
/*     */   Point3d rightEyePosition;
/*     */   Point3d leftEarPosition;
/*     */   Point3d rightEarPosition;
/*     */   double nominalEyeHeightFromGround;
/*     */   double nominalEyeOffsetFromNominalScreen;
/*     */   Transform3D headToHeadTracker;
/*     */   ArrayList users;
/*     */   int pbDirtyMask;
/*     */   
/*     */   public PhysicalBody() {
/*  43 */     this.leftEyePosition = new Point3d(-0.033D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     this.rightEyePosition = new Point3d(0.033D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     this.leftEarPosition = new Point3d(-0.08D, -0.03D, 0.095D);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     this.rightEarPosition = new Point3d(0.08D, -0.03D, 0.095D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     this.nominalEyeHeightFromGround = 1.68D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     this.nominalEyeOffsetFromNominalScreen = 0.4572D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     this.headToHeadTracker = new Transform3D();
/*     */ 
/*     */     
/*  84 */     this.users = new ArrayList();
/*     */ 
/*     */ 
/*     */     
/*  88 */     this.pbDirtyMask = 251658240;
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
/* 108 */     initHeadToHeadTracker();
/*     */   }
/*     */ 
/*     */   
/*     */   void removeUser(View paramView) {
/* 113 */     int i = this.users.indexOf(paramView);
/* 114 */     if (i >= 0) {
/* 115 */       this.users.remove(i);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void addUser(View paramView) {
/* 121 */     int i = this.users.indexOf(paramView);
/* 122 */     if (i < 0) {
/* 123 */       this.users.add(paramView);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void notifyUsers() {
/* 129 */     for (int i = this.users.size() - 1; i >= 0; i--) {
/* 130 */       View view = (View)this.users.get(i);
/*     */       
/* 132 */       if (view.soundScheduler != null) {
/* 133 */         view.soundScheduler.setListenerFlag(3);
/*     */       }
/*     */ 
/*     */       
/* 137 */       view.repaint();
/*     */     }  } public PhysicalBody(Point3d paramPoint3d1, Point3d paramPoint3d2) {
/*     */     this.leftEyePosition = new Point3d(-0.033D, 0.0D, 0.0D);
/*     */     this.rightEyePosition = new Point3d(0.033D, 0.0D, 0.0D);
/*     */     this.leftEarPosition = new Point3d(-0.08D, -0.03D, 0.095D);
/*     */     this.rightEarPosition = new Point3d(0.08D, -0.03D, 0.095D);
/*     */     this.nominalEyeHeightFromGround = 1.68D;
/*     */     this.nominalEyeOffsetFromNominalScreen = 0.4572D;
/*     */     this.headToHeadTracker = new Transform3D();
/*     */     this.users = new ArrayList();
/*     */     this.pbDirtyMask = 251658240;
/* 148 */     this.leftEyePosition.set(paramPoint3d1);
/* 149 */     this.rightEyePosition.set(paramPoint3d2);
/* 150 */     initHeadToHeadTracker();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PhysicalBody(Point3d paramPoint3d1, Point3d paramPoint3d2, Point3d paramPoint3d3, Point3d paramPoint3d4) {
/*     */     this.leftEyePosition = new Point3d(-0.033D, 0.0D, 0.0D);
/*     */     this.rightEyePosition = new Point3d(0.033D, 0.0D, 0.0D);
/*     */     this.leftEarPosition = new Point3d(-0.08D, -0.03D, 0.095D);
/*     */     this.rightEarPosition = new Point3d(0.08D, -0.03D, 0.095D);
/*     */     this.nominalEyeHeightFromGround = 1.68D;
/*     */     this.nominalEyeOffsetFromNominalScreen = 0.4572D;
/*     */     this.headToHeadTracker = new Transform3D();
/*     */     this.users = new ArrayList();
/*     */     this.pbDirtyMask = 251658240;
/* 166 */     this.leftEyePosition.set(paramPoint3d1);
/* 167 */     this.rightEyePosition.set(paramPoint3d2);
/* 168 */     this.leftEarPosition.set(paramPoint3d3);
/* 169 */     this.rightEarPosition.set(paramPoint3d4);
/* 170 */     initHeadToHeadTracker();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public String toString() { return "eyePosition = (" + this.leftEyePosition + ", " + this.rightEyePosition + ")\n" + "earPosition = (" + this.leftEarPosition + ", " + this.rightEarPosition + ")"; }
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
/* 191 */   public void getLeftEyePosition(Point3d paramPoint3d) { paramPoint3d.set(this.leftEyePosition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeftEyePosition(Point3d paramPoint3d) {
/* 199 */     synchronized (this) {
/* 200 */       this.leftEyePosition.set(paramPoint3d);
/* 201 */       this.pbDirtyMask |= 0x1000000;
/*     */     } 
/* 203 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public void getRightEyePosition(Point3d paramPoint3d) { paramPoint3d.set(this.rightEyePosition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRightEyePosition(Point3d paramPoint3d) {
/* 221 */     synchronized (this) {
/* 222 */       this.rightEyePosition.set(paramPoint3d);
/* 223 */       this.pbDirtyMask |= 0x1000000;
/*     */     } 
/* 225 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 235 */   public void getLeftEarPosition(Point3d paramPoint3d) { paramPoint3d.set(this.leftEarPosition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeftEarPosition(Point3d paramPoint3d) {
/* 243 */     synchronized (this) {
/* 244 */       this.leftEarPosition.set(paramPoint3d);
/* 245 */       this.pbDirtyMask |= 0x2000000;
/*     */     } 
/* 247 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   public void getRightEarPosition(Point3d paramPoint3d) { paramPoint3d.set(this.rightEarPosition); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRightEarPosition(Point3d paramPoint3d) {
/* 265 */     synchronized (this) {
/* 266 */       this.rightEarPosition.set(paramPoint3d);
/* 267 */       this.pbDirtyMask |= 0x2000000;
/*     */     } 
/* 269 */     notifyUsers();
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
/*     */   public void setNominalEyeHeightFromGround(double paramDouble) {
/* 281 */     synchronized (this) {
/* 282 */       this.nominalEyeHeightFromGround = paramDouble;
/* 283 */       this.pbDirtyMask |= 0x4000000;
/*     */     } 
/* 285 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 293 */   public double getNominalEyeHeightFromGround() { return this.nominalEyeHeightFromGround; }
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
/*     */   public void setNominalEyeOffsetFromNominalScreen(double paramDouble) {
/* 308 */     synchronized (this) {
/* 309 */       this.nominalEyeOffsetFromNominalScreen = paramDouble;
/* 310 */       this.pbDirtyMask |= 0x8000000;
/*     */     } 
/* 312 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 320 */   public double getNominalEyeOffsetFromNominalScreen() { return this.nominalEyeOffsetFromNominalScreen; }
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
/*     */   public void setHeadToHeadTracker(Transform3D paramTransform3D) {
/* 333 */     if (!paramTransform3D.isRigid()) {
/* 334 */       throw new BadTransformException(J3dI18N.getString("PhysicalBody0"));
/*     */     }
/* 336 */     this.headToHeadTracker.setWithLock(paramTransform3D);
/* 337 */     notifyUsers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 345 */   public void getHeadToHeadTracker(Transform3D paramTransform3D) { paramTransform3D.set(this.headToHeadTracker); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initHeadToHeadTracker() {
/* 353 */     Vector3d vector3d = new Vector3d(0.0D, 0.02D, 0.035D);
/* 354 */     this.headToHeadTracker.set(vector3d);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\PhysicalBody.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */