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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Soundscape
/*     */   extends Leaf
/*     */ {
/*     */   public static final int ALLOW_APPLICATION_BOUNDS_READ = 12;
/*     */   public static final int ALLOW_APPLICATION_BOUNDS_WRITE = 13;
/*     */   public static final int ALLOW_ATTRIBUTES_READ = 14;
/*     */   public static final int ALLOW_ATTRIBUTES_WRITE = 15;
/*  76 */   private static final int[] readCapabilities = { 12, 14 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public Soundscape() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Soundscape(Bounds paramBounds, AuralAttributes paramAuralAttributes) {
/* 101 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 103 */     ((SoundscapeRetained)this.retained).setApplicationBounds(paramBounds);
/* 104 */     ((SoundscapeRetained)this.retained).setAuralAttributes(paramAuralAttributes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 112 */     this.retained = new SoundscapeRetained();
/* 113 */     this.retained.setSource(this);
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
/*     */   public void setApplicationBounds(Bounds paramBounds) {
/* 130 */     if (isLiveOrCompiled() && 
/* 131 */       !getCapability(13)) {
/* 132 */       throw new CapabilityNotSetException(J3dI18N.getString("Soundscape0"));
/*     */     }
/* 134 */     ((SoundscapeRetained)this.retained).setApplicationBounds(paramBounds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bounds getApplicationBounds() {
/* 144 */     if (isLiveOrCompiled() && 
/* 145 */       !getCapability(12)) {
/* 146 */       throw new CapabilityNotSetException(J3dI18N.getString("Soundscape1"));
/*     */     }
/* 148 */     return ((SoundscapeRetained)this.retained).getApplicationBounds();
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
/* 161 */     if (isLiveOrCompiled() && 
/* 162 */       !getCapability(13)) {
/* 163 */       throw new CapabilityNotSetException(J3dI18N.getString("Soundscape0"));
/*     */     }
/* 165 */     ((SoundscapeRetained)this.retained).setApplicationBoundingLeaf(paramBoundingLeaf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingLeaf getApplicationBoundingLeaf() {
/* 175 */     if (isLiveOrCompiled() && 
/* 176 */       !getCapability(12)) {
/* 177 */       throw new CapabilityNotSetException(J3dI18N.getString("Soundscape1"));
/*     */     }
/* 179 */     return ((SoundscapeRetained)this.retained).getApplicationBoundingLeaf();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAuralAttributes(AuralAttributes paramAuralAttributes) {
/* 189 */     if (isLiveOrCompiled() && 
/* 190 */       !getCapability(15)) {
/* 191 */       throw new CapabilityNotSetException(J3dI18N.getString("Soundscape4"));
/*     */     }
/* 193 */     ((SoundscapeRetained)this.retained).setAuralAttributes(paramAuralAttributes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AuralAttributes getAuralAttributes() {
/* 203 */     if (isLiveOrCompiled() && 
/* 204 */       !getCapability(14)) {
/* 205 */       throw new CapabilityNotSetException(J3dI18N.getString("Soundscape5"));
/*     */     }
/* 207 */     return ((SoundscapeRetained)this.retained).getAuralAttributes();
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
/* 226 */     Soundscape soundscape = new Soundscape();
/* 227 */     soundscape.duplicateNode(this, paramBoolean);
/* 228 */     return soundscape;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   public void duplicateNode(Node paramNode, boolean paramBoolean) { checkDuplicateNode(paramNode, paramBoolean); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 288 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 290 */     SoundscapeRetained soundscapeRetained1 = (SoundscapeRetained)paramNode.retained;
/* 291 */     SoundscapeRetained soundscapeRetained2 = (SoundscapeRetained)this.retained;
/*     */     
/* 293 */     soundscapeRetained2.setApplicationBounds(soundscapeRetained1.getApplicationBounds());
/*     */     
/* 295 */     soundscapeRetained2.setAuralAttributes((AuralAttributes)getNodeComponent(soundscapeRetained1.getAuralAttributes(), paramBoolean, paramNode.nodeHashtable));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 301 */     soundscapeRetained2.setApplicationBoundingLeaf(soundscapeRetained1.getApplicationBoundingLeaf());
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
/*     */   
/*     */   public void updateNodeReferences(NodeReferenceTable paramNodeReferenceTable) {
/* 332 */     SoundscapeRetained soundscapeRetained = (SoundscapeRetained)this.retained;
/*     */     
/* 334 */     BoundingLeaf boundingLeaf = soundscapeRetained.getApplicationBoundingLeaf();
/*     */     
/* 336 */     if (boundingLeaf != null) {
/* 337 */       SceneGraphObject sceneGraphObject = paramNodeReferenceTable.getNewObjectReference(boundingLeaf);
/* 338 */       soundscapeRetained.setApplicationBoundingLeaf((BoundingLeaf)sceneGraphObject);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\Soundscape.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */