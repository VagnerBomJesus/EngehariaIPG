/*     */ package com.sun.j3d.utils.universe;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.media.j3d.ViewPlatform;
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
/*     */ class ConfigViewPlatform
/*     */   extends ConfigObject
/*     */ {
/*     */   private boolean allowPolicyRead = false;
/*     */   private boolean allowLocalToVworldRead = false;
/*     */   private boolean nominalViewingTransform = false;
/*  61 */   private Transform3D initialViewingTransform = null;
/*  62 */   private ArrayList configViews = new ArrayList();
/*  63 */   private Viewer[] viewers = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   ViewingPlatform viewingPlatform = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   int viewAttachPolicy = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   ConfigViewPlatformBehavior configBehavior = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initialize(ConfigCommand paramConfigCommand) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setProperty(ConfigCommand paramConfigCommand) {
/*  96 */     int i = paramConfigCommand.argc;
/*  97 */     Object[] arrayOfObject = paramConfigCommand.argv;
/*     */ 
/*     */ 
/*     */     
/* 101 */     if (i != 4) {
/* 102 */       syntaxError("Incorrect number of arguments to " + paramConfigCommand.commandName);
/*     */     }
/*     */ 
/*     */     
/* 106 */     if (!isName(arrayOfObject[2])) {
/* 107 */       syntaxError("The second argument to " + paramConfigCommand.commandName + " must be a property name");
/*     */     }
/*     */ 
/*     */     
/* 111 */     String str = (String)arrayOfObject[2];
/* 112 */     Object object = arrayOfObject[3];
/*     */     
/* 114 */     if (str.equals("NominalViewingTransform")) {
/* 115 */       if (!(object instanceof Boolean)) {
/* 116 */         syntaxError("NominalViewingTransform must be a boolean");
/*     */       }
/* 118 */       this.nominalViewingTransform = ((Boolean)object).booleanValue();
/*     */     }
/* 120 */     else if (str.equals("InitialViewingTransform")) {
/* 121 */       if (!(object instanceof Matrix4d)) {
/* 122 */         syntaxError("InitialViewingTransform must be a Matrix4d");
/*     */       }
/* 124 */       this.initialViewingTransform = new Transform3D((Matrix4d)object);
/*     */     }
/* 126 */     else if (str.equals("ViewAttachPolicy")) {
/* 127 */       if (!(object instanceof String)) {
/* 128 */         syntaxError("ViewAttachPolicy must be a string");
/*     */       }
/*     */       
/* 131 */       String str1 = (String)object;
/*     */       
/* 133 */       if (str1.equals("NOMINAL_HEAD")) {
/* 134 */         this.viewAttachPolicy = 0;
/* 135 */       } else if (str1.equals("NOMINAL_SCREEN")) {
/* 136 */         this.viewAttachPolicy = 2;
/* 137 */       } else if (str1.equals("NOMINAL_FEET")) {
/* 138 */         this.viewAttachPolicy = 1;
/*     */       } else {
/* 140 */         syntaxError("Illegal value " + str1 + " for ViewAttachPolicy");
/*     */       }
/*     */     
/* 143 */     } else if (str.equals("ViewPlatformBehavior")) {
/* 144 */       if (!(object instanceof String)) {
/* 145 */         syntaxError("ViewPlatformBehavior must be a name");
/*     */       }
/* 147 */       this.configBehavior = (ConfigViewPlatformBehavior)this.configContainer.findConfigObject("ViewPlatformBehavior", (String)object);
/*     */ 
/*     */     
/*     */     }
/* 151 */     else if (str.equals("AllowPolicyRead")) {
/* 152 */       if (!(object instanceof Boolean)) {
/* 153 */         syntaxError("value for AllowPolicyRead must be a boolean");
/*     */       }
/*     */       
/* 156 */       this.allowPolicyRead = ((Boolean)object).booleanValue();
/*     */     }
/* 158 */     else if (str.equals("AllowLocalToVworldRead")) {
/* 159 */       if (!(object instanceof Boolean)) {
/* 160 */         syntaxError("value for AllowLocalToVworldRead must be a boolean");
/*     */       }
/*     */       
/* 163 */       this.allowLocalToVworldRead = ((Boolean)object).booleanValue();
/*     */     } else {
/*     */       
/* 166 */       syntaxError("Unknown " + paramConfigCommand.commandName + " \"" + str + "\"");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   void addConfigView(ConfigView paramConfigView) { this.configViews.add(paramConfigView); }
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
/*     */   ViewingPlatform createViewingPlatform(int paramInt) {
/* 189 */     if (this.configViews.size() == 0) {
/* 190 */       this.viewers = new Viewer[0];
/*     */     } else {
/*     */       
/* 193 */       this.viewers = new Viewer[this.configViews.size()];
/* 194 */       for (byte b1 = 0; b1 < this.viewers.length; b1++) {
/* 195 */         this.viewers[b1] = ((ConfigView)this.configViews.get(b1)).j3dViewer;
/*     */       }
/*     */     } 
/*     */     
/* 199 */     this.viewingPlatform = new ViewingPlatform(paramInt);
/* 200 */     ViewPlatform viewPlatform = this.viewingPlatform.getViewPlatform();
/*     */ 
/*     */     
/* 203 */     if (this.allowPolicyRead) {
/* 204 */       viewPlatform.setCapability(12);
/*     */     }
/* 206 */     if (this.allowLocalToVworldRead) {
/* 207 */       viewPlatform.setCapability(11);
/*     */     }
/* 209 */     if (this.viewAttachPolicy == -1) {
/*     */       
/* 211 */       boolean bool = true;
/* 212 */       for (byte b1 = 0; b1 < this.viewers.length; b1++) {
/* 213 */         if (this.viewers[b1].getView().getWindowEyepointPolicy() != 2) {
/*     */           
/* 215 */           bool = false;
/*     */           break;
/*     */         } 
/*     */       } 
/* 219 */       if (bool) {
/* 220 */         viewPlatform.setViewAttachPolicy(0);
/*     */       } else {
/* 222 */         viewPlatform.setViewAttachPolicy(2);
/*     */       } 
/*     */     } else {
/* 225 */       viewPlatform.setViewAttachPolicy(this.viewAttachPolicy);
/*     */     } 
/*     */ 
/*     */     
/* 229 */     for (byte b = 0; b < this.viewers.length; b++) {
/* 230 */       this.viewers[b].setViewingPlatform(this.viewingPlatform);
/*     */     }
/*     */ 
/*     */     
/* 234 */     if (this.nominalViewingTransform) {
/* 235 */       this.viewingPlatform.setNominalViewingTransform();
/*     */     }
/*     */     
/* 238 */     if (this.initialViewingTransform != null) {
/* 239 */       TransformGroup transformGroup = this.viewingPlatform.getViewPlatformTransform();
/* 240 */       transformGroup.setTransform(this.initialViewingTransform);
/*     */     } 
/*     */     
/* 243 */     return this.viewingPlatform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void processBehavior() {
/* 250 */     if (this.configBehavior != null)
/* 251 */       this.viewingPlatform.setViewPlatformBehavior(this.configBehavior.viewPlatformBehavior); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\util\\universe\ConfigViewPlatform.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */