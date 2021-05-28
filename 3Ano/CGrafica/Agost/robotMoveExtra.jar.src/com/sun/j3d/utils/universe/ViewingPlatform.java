/*     */ package com.sun.j3d.utils.universe;
/*     */ 
/*     */ import com.sun.j3d.internal.J3dUtilsI18N;
/*     */ import com.sun.j3d.utils.behaviors.vp.ViewPlatformBehavior;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.media.j3d.View;
/*     */ import javax.media.j3d.ViewPlatform;
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
/*     */ public class ViewingPlatform
/*     */   extends BranchGroup
/*     */ {
/*     */   protected ViewPlatform viewPlatform;
/*     */   protected MultiTransformGroup mtg;
/*     */   protected BranchGroup platformGeometryRoot;
/*     */   protected BranchGroup avatarRoot;
/*     */   protected PlatformGeometry platformGeometry;
/*     */   protected Hashtable viewerList;
/*     */   protected BranchGroup behaviors;
/*     */   protected SimpleUniverse universe;
/*     */   
/* 123 */   public ViewingPlatform() { this(1); }
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
/*     */   public ViewingPlatform(int paramInt) {
/*     */     this.platformGeometry = null;
/* 136 */     this.viewerList = new Hashtable();
/*     */ 
/*     */     
/* 139 */     setCapability(13);
/* 140 */     setCapability(14);
/* 141 */     setCapability(17);
/*     */ 
/*     */     
/* 144 */     if (paramInt < 1)
/* 145 */       paramInt = 1; 
/* 146 */     this.mtg = new MultiTransformGroup(paramInt);
/*     */ 
/*     */     
/* 149 */     TransformGroup transformGroup = this.mtg.getTransformGroup(0);
/* 150 */     addChild(transformGroup);
/*     */ 
/*     */ 
/*     */     
/* 154 */     transformGroup = this.mtg.getTransformGroup(paramInt - 1);
/* 155 */     this.viewPlatform = new ViewPlatform();
/* 156 */     this.viewPlatform.setCapability(12);
/* 157 */     this.viewPlatform.setCapability(13);
/* 158 */     transformGroup.addChild(this.viewPlatform);
/*     */ 
/*     */     
/* 161 */     transformGroup.setCapability(17);
/* 162 */     transformGroup.setCapability(18);
/*     */ 
/*     */ 
/*     */     
/* 166 */     this.avatarRoot = new BranchGroup();
/* 167 */     this.avatarRoot.setCapability(12);
/* 168 */     this.avatarRoot.setCapability(13);
/* 169 */     this.avatarRoot.setCapability(14);
/* 170 */     transformGroup.addChild(this.avatarRoot);
/*     */ 
/*     */ 
/*     */     
/* 174 */     this.platformGeometryRoot = new BranchGroup();
/* 175 */     this.platformGeometryRoot.setCapability(12);
/* 176 */     this.platformGeometryRoot.setCapability(13);
/* 177 */     this.platformGeometryRoot.setCapability(14);
/* 178 */     transformGroup.addChild(this.platformGeometryRoot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setViewPlatform(ViewPlatform paramViewPlatform) {
/* 188 */     TransformGroup transformGroup = getViewPlatformTransform();
/* 189 */     transformGroup.removeChild(this.viewPlatform);
/* 190 */     transformGroup.addChild(paramViewPlatform);
/* 191 */     this.viewPlatform = paramViewPlatform;
/*     */     
/* 193 */     Enumeration enumeration = this.viewerList.keys();
/*     */     
/* 195 */     while (enumeration.hasMoreElements()) {
/* 196 */       ((Viewer)enumeration.nextElement()).setViewingPlatform(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   public ViewPlatform getViewPlatform() { return this.viewPlatform; }
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
/*     */   public void setPlatformGeometry(PlatformGeometry paramPlatformGeometry) {
/* 222 */     if (this.platformGeometry == paramPlatformGeometry) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 227 */     if (paramPlatformGeometry == null) {
/* 228 */       if (this.platformGeometryRoot.numChildren() != 0) {
/* 229 */         this.platformGeometryRoot.removeChild(0);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 234 */     else if (this.platformGeometryRoot.numChildren() != 0) {
/* 235 */       this.platformGeometryRoot.setChild(paramPlatformGeometry, 0);
/*     */     } else {
/* 237 */       this.platformGeometryRoot.addChild(paramPlatformGeometry);
/*     */     } 
/*     */     
/* 240 */     this.platformGeometry = paramPlatformGeometry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public PlatformGeometry getPlatformGeometry() { return this.platformGeometry; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 259 */   public MultiTransformGroup getMultiTransformGroup() { return this.mtg; }
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
/* 270 */   public TransformGroup getViewPlatformTransform() { return this.mtg.getTransformGroup(this.mtg.getNumTransforms() - 1); }
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
/*     */   public void setNominalViewingTransform() {
/* 291 */     if (this.viewPlatform.getViewAttachPolicy() == 0) {
/*     */       double d1;
/*     */       
/* 294 */       if (this.viewerList.size() == 0) {
/*     */ 
/*     */         
/* 297 */         d1 = 0.7853981633974483D;
/*     */       } else {
/*     */         
/* 300 */         if (this.viewerList.size() > 1) {
/* 301 */           throw new RuntimeException(J3dUtilsI18N.getString("ViewingPlatform0"));
/*     */         }
/*     */ 
/*     */         
/* 305 */         Viewer viewer = (Viewer)this.viewerList.keys().nextElement();
/* 306 */         View view = viewer.getView();
/* 307 */         d1 = view.getFieldOfView();
/*     */       } 
/*     */       
/* 310 */       Transform3D transform3D = new Transform3D();
/* 311 */       double d2 = 1.0D / Math.tan(d1 / 2.0D);
/* 312 */       transform3D.set(new Vector3d(0.0D, 0.0D, d2));
/* 313 */       getViewPlatformTransform().setTransform(transform3D);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int findAvatarChild(ViewerAvatar paramViewerAvatar) {
/* 330 */     for (byte b = 0; b < this.avatarRoot.numChildren(); b++) {
/* 331 */       if ((ViewerAvatar)this.avatarRoot.getChild(b) == paramViewerAvatar) {
/* 332 */         return b;
/*     */       }
/*     */     } 
/*     */     
/* 336 */     System.err.println("ViewingPlatform.findAvatarChild:Child not found.");
/* 337 */     return -1;
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
/*     */   void setAvatar(Viewer paramViewer, ViewerAvatar paramViewerAvatar) {
/* 349 */     Object object = this.viewerList.get(paramViewer);
/*     */ 
/*     */     
/* 352 */     int i = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 357 */     if (object != this.avatarRoot) {
/* 358 */       i = findAvatarChild((ViewerAvatar)object);
/*     */     }
/*     */     
/* 361 */     if (paramViewerAvatar == null) {
/* 362 */       if (i != -1) {
/* 363 */         this.avatarRoot.removeChild(i);
/*     */ 
/*     */         
/* 366 */         this.viewerList.put(paramViewer, this.avatarRoot);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 371 */       if (i != -1) {
/* 372 */         this.avatarRoot.setChild(paramViewerAvatar, i);
/*     */       } else {
/* 374 */         this.avatarRoot.addChild(paramViewerAvatar);
/*     */       } 
/*     */       
/* 377 */       this.viewerList.put(paramViewer, paramViewerAvatar);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 395 */   void addViewer(Viewer paramViewer) { this.viewerList.put(paramViewer, this.avatarRoot); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 402 */   void removeViewer(Viewer paramViewer) { this.viewerList.remove(paramViewer); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addViewPlatformBehavior(ViewPlatformBehavior paramViewPlatformBehavior) {
/* 409 */     paramViewPlatformBehavior.setViewingPlatform(this);
/* 410 */     if (this.behaviors == null) {
/* 411 */       this.behaviors = new BranchGroup();
/* 412 */       this.behaviors.setCapability(17);
/* 413 */       this.behaviors.setCapability(12);
/*     */     }
/*     */     else {
/*     */       
/* 417 */       this.behaviors.detach();
/*     */     } 
/* 419 */     this.behaviors.addChild(paramViewPlatformBehavior);
/* 420 */     addChild(this.behaviors);
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
/*     */   public void setViewPlatformBehavior(ViewPlatformBehavior paramViewPlatformBehavior) {
/* 435 */     if (this.behaviors != null) {
/* 436 */       removeViewPlatformBehavior((ViewPlatformBehavior)this.behaviors.getChild(0));
/*     */     }
/* 438 */     if (paramViewPlatformBehavior != null) {
/* 439 */       addViewPlatformBehavior(paramViewPlatformBehavior);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void removeViewPlatformBehavior(ViewPlatformBehavior paramViewPlatformBehavior) {
/* 448 */     if (this.behaviors != null) {
/* 449 */       this.behaviors.detach();
/* 450 */       for (byte b = 0; b < this.behaviors.numChildren(); b++) {
/* 451 */         if (this.behaviors.getChild(b) == paramViewPlatformBehavior) {
/* 452 */           paramViewPlatformBehavior.setViewingPlatform(null);
/* 453 */           this.behaviors.removeChild(b);
/*     */           break;
/*     */         } 
/*     */       } 
/* 457 */       if (this.behaviors.numChildren() == 0) { this.behaviors = null; }
/* 458 */       else { addChild(this.behaviors); }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 466 */   int getViewPlatformBehaviorCount() { return this.behaviors.numChildren(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 473 */   ViewPlatformBehavior getViewPlatformBehavior(int paramInt) { return (ViewPlatformBehavior)this.behaviors.getChild(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ViewPlatformBehavior getViewPlatformBehavior() {
/* 483 */     if (this.behaviors == null) {
/* 484 */       return null;
/*     */     }
/* 486 */     return getViewPlatformBehavior(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Viewer[] getViewers() {
/* 496 */     if (this.viewerList.size() == 0) return null; 
/* 497 */     return (Viewer[])this.viewerList.keySet().toArray(new Viewer[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 507 */   public SimpleUniverse getUniverse() { return this.universe; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 517 */   public void setUniverse(SimpleUniverse paramSimpleUniverse) { this.universe = paramSimpleUniverse; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\util\\universe\ViewingPlatform.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */