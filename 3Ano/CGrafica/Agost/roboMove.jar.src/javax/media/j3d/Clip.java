/*     */ package javax.media.j3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Clip
/*     */   extends Leaf
/*     */ {
/*     */   public static final int ALLOW_APPLICATION_BOUNDS_READ = 12;
/*     */   public static final int ALLOW_APPLICATION_BOUNDS_WRITE = 13;
/*     */   public static final int ALLOW_BACK_DISTANCE_READ = 14;
/*     */   public static final int ALLOW_BACK_DISTANCE_WRITE = 15;
/*  65 */   private static final int[] readCapabilities = { 12, 14 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public Clip() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Clip(double paramDouble) {
/*  90 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/*  92 */     ((ClipRetained)this.retained).initBackDistance(paramDouble);
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
/*     */   public void setBackDistance(double paramDouble) {
/* 107 */     if (isLiveOrCompiled() && 
/* 108 */       !getCapability(15)) {
/* 109 */       throw new CapabilityNotSetException(J3dI18N.getString("Clip0"));
/*     */     }
/* 111 */     if (isLive()) {
/* 112 */       ((ClipRetained)this.retained).setBackDistance(paramDouble);
/*     */     } else {
/* 114 */       ((ClipRetained)this.retained).initBackDistance(paramDouble);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getBackDistance() {
/* 122 */     if (isLiveOrCompiled() && 
/* 123 */       !getCapability(14))
/* 124 */       throw new CapabilityNotSetException(J3dI18N.getString("Clip1")); 
/* 125 */     return ((ClipRetained)this.retained).getBackDistance();
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
/*     */   public void setApplicationBounds(Bounds paramBounds) {
/* 137 */     if (isLiveOrCompiled() && 
/* 138 */       !getCapability(13)) {
/* 139 */       throw new CapabilityNotSetException(J3dI18N.getString("Clip2"));
/*     */     }
/* 141 */     if (isLive()) {
/* 142 */       ((ClipRetained)this.retained).setApplicationBounds(paramBounds);
/*     */     } else {
/* 144 */       ((ClipRetained)this.retained).initApplicationBounds(paramBounds);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bounds getApplicationBounds() {
/* 154 */     if (isLiveOrCompiled() && 
/* 155 */       !getCapability(12)) {
/* 156 */       throw new CapabilityNotSetException(J3dI18N.getString("Clip3"));
/*     */     }
/* 158 */     return ((ClipRetained)this.retained).getApplicationBounds();
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
/*     */   public void setApplicationBoundingLeaf(BoundingLeaf paramBoundingLeaf) {
/* 171 */     if (isLiveOrCompiled() && 
/* 172 */       !getCapability(13)) {
/* 173 */       throw new CapabilityNotSetException(J3dI18N.getString("Clip2"));
/*     */     }
/* 175 */     if (isLive()) {
/* 176 */       ((ClipRetained)this.retained).setApplicationBoundingLeaf(paramBoundingLeaf);
/*     */     } else {
/* 178 */       ((ClipRetained)this.retained).initApplicationBoundingLeaf(paramBoundingLeaf);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingLeaf getApplicationBoundingLeaf() {
/* 188 */     if (isLiveOrCompiled() && 
/* 189 */       !getCapability(12)) {
/* 190 */       throw new CapabilityNotSetException(J3dI18N.getString("Clip3"));
/*     */     }
/* 192 */     return ((ClipRetained)this.retained).getApplicationBoundingLeaf();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 200 */     this.retained = new ClipRetained();
/* 201 */     this.retained.setSource(this);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 219 */     Clip clip = new Clip();
/* 220 */     clip.duplicateNode(this, paramBoolean);
/* 221 */     return clip;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateNodeReferences(NodeReferenceTable paramNodeReferenceTable) {
/* 251 */     ClipRetained clipRetained = (ClipRetained)this.retained;
/* 252 */     BoundingLeaf boundingLeaf = clipRetained.getApplicationBoundingLeaf();
/*     */ 
/*     */     
/* 255 */     if (boundingLeaf != null) {
/* 256 */       SceneGraphObject sceneGraphObject = paramNodeReferenceTable.getNewObjectReference(boundingLeaf);
/* 257 */       clipRetained.initApplicationBoundingLeaf((BoundingLeaf)sceneGraphObject);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 284 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 286 */     ClipRetained clipRetained1 = (ClipRetained)paramNode.retained;
/* 287 */     ClipRetained clipRetained2 = (ClipRetained)this.retained;
/*     */     
/* 289 */     clipRetained2.initBackDistance(clipRetained1.getBackDistance());
/* 290 */     clipRetained2.initApplicationBounds(clipRetained1.getApplicationBounds());
/*     */ 
/*     */     
/* 293 */     clipRetained2.initApplicationBoundingLeaf(clipRetained1.getApplicationBoundingLeaf());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\Clip.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */