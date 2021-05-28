/*     */ package com.sun.j3d.utils.universe;
/*     */ 
/*     */ import com.sun.j3d.utils.behaviors.vp.ViewPlatformBehavior;
/*     */ import javax.media.j3d.Bounds;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.vecmath.Matrix4d;
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
/*     */ class ConfigViewPlatformBehavior
/*     */   extends ConfigObject
/*     */ {
/*  59 */   private Transform3D homeTransform = null;
/*  60 */   private Bounds schedulingBounds = null;
/*  61 */   private int schedulingInterval = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ViewPlatformBehavior viewPlatformBehavior;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setProperty(ConfigCommand paramConfigCommand) {
/*  76 */     int i = paramConfigCommand.argc;
/*  77 */     Object[] arrayOfObject = paramConfigCommand.argv;
/*     */     
/*  79 */     if (i < 4) {
/*  80 */       syntaxError("Wrong number of arguments to " + paramConfigCommand.commandName);
/*     */     }
/*     */     
/*  83 */     if (!isName(arrayOfObject[2])) {
/*  84 */       syntaxError("The second argument to " + paramConfigCommand.commandName + " must be a property name");
/*     */     }
/*     */ 
/*     */     
/*  88 */     String str = (String)arrayOfObject[2];
/*  89 */     if (str.equals("HomeTransform")) {
/*  90 */       if (!(arrayOfObject[3] instanceof Matrix4d)) {
/*  91 */         syntaxError("HomeTransform must be a Matrix4d");
/*     */       }
/*  93 */       this.homeTransform = new Transform3D((Matrix4d)arrayOfObject[3]);
/*     */     }
/*  95 */     else if (str.equals("SchedulingBounds")) {
/*  96 */       if (!(arrayOfObject[3] instanceof Bounds)) {
/*  97 */         syntaxError("SchedulingBounds must be an instance of Bounds");
/*     */       }
/*  99 */       this.schedulingBounds = (Bounds)arrayOfObject[3];
/*     */     }
/* 101 */     else if (str.equals("SchedulingInterval")) {
/* 102 */       if (!(arrayOfObject[3] instanceof Double)) {
/* 103 */         syntaxError("SchedulingInterval must be a priority (number)");
/*     */       }
/* 105 */       this.schedulingInterval = ((Double)arrayOfObject[3]).intValue();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 110 */       this.properties.add(paramConfigCommand);
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
/*     */   ViewPlatformBehavior createViewPlatformBehavior() {
/* 124 */     this.viewPlatformBehavior = (ViewPlatformBehavior)createTargetObject();
/*     */ 
/*     */     
/* 127 */     if (this.homeTransform != null) {
/* 128 */       this.viewPlatformBehavior.setHomeTransform(this.homeTransform);
/*     */     }
/* 130 */     if (this.schedulingBounds != null) {
/* 131 */       this.viewPlatformBehavior.setSchedulingBounds(this.schedulingBounds);
/*     */     }
/* 133 */     if (this.schedulingInterval != -1) {
/* 134 */       this.viewPlatformBehavior.setSchedulingInterval(this.schedulingInterval);
/*     */     }
/*     */     
/* 137 */     return this.viewPlatformBehavior;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\util\\universe\ConfigViewPlatformBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */