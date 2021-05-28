/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextureUnitState
/*     */   extends NodeComponent
/*     */ {
/*     */   public static final int ALLOW_STATE_READ = 0;
/*     */   public static final int ALLOW_STATE_WRITE = 1;
/*  70 */   private static final int[] readCapabilities = { 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public TextureUnitState() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextureUnitState(Texture paramTexture, TextureAttributes paramTextureAttributes, TexCoordGeneration paramTexCoordGeneration) {
/* 100 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 102 */     ((TextureUnitStateRetained)this.retained).initTexture(paramTexture);
/* 103 */     ((TextureUnitStateRetained)this.retained).initTextureAttributes(paramTextureAttributes);
/*     */     
/* 105 */     ((TextureUnitStateRetained)this.retained).initTexCoordGeneration(paramTexCoordGeneration);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 114 */     this.retained = new TextureUnitStateRetained();
/* 115 */     this.retained.setSource(this);
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
/*     */   public void set(Texture paramTexture, TextureAttributes paramTextureAttributes, TexCoordGeneration paramTexCoordGeneration) {
/* 143 */     if (isLiveOrCompiled() && 
/* 144 */       !getCapability(1)) {
/* 145 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureUnitState0"));
/*     */     }
/*     */     
/* 148 */     if (paramTexture != null) {
/* 149 */       TextureRetained textureRetained = (TextureRetained)paramTexture.retained;
/* 150 */       ImageComponent[] arrayOfImageComponent = textureRetained.getImages();
/* 151 */       if (arrayOfImageComponent != null) {
/* 152 */         for (byte b = 0; b < arrayOfImageComponent.length; b++) {
/* 153 */           validateImageIllegalSharing(arrayOfImageComponent[b]);
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 158 */     ((TextureUnitStateRetained)this.retained).setTextureUnitState(paramTexture, paramTextureAttributes, paramTexCoordGeneration);
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
/*     */   public void setTexture(Texture paramTexture) {
/* 183 */     if (isLiveOrCompiled() && 
/* 184 */       !getCapability(1)) {
/* 185 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureUnitState0"));
/*     */     }
/*     */     
/* 188 */     if (paramTexture != null) {
/* 189 */       TextureRetained textureRetained = (TextureRetained)paramTexture.retained;
/* 190 */       ImageComponent[] arrayOfImageComponent = textureRetained.getImages();
/* 191 */       if (arrayOfImageComponent != null) {
/* 192 */         for (byte b = 0; b < arrayOfImageComponent.length; b++) {
/* 193 */           validateImageIllegalSharing(arrayOfImageComponent[b]);
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 198 */     ((TextureUnitStateRetained)this.retained).setTexture(paramTexture);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Texture getTexture() {
/* 208 */     if (isLiveOrCompiled() && 
/* 209 */       !getCapability(0)) {
/* 210 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureUnitState1"));
/*     */     }
/* 212 */     return ((TextureUnitStateRetained)this.retained).getTexture();
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
/*     */   public void setTextureAttributes(TextureAttributes paramTextureAttributes) {
/* 225 */     if (isLiveOrCompiled() && 
/* 226 */       !getCapability(1)) {
/* 227 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureUnitState2"));
/*     */     }
/* 229 */     ((TextureUnitStateRetained)this.retained).setTextureAttributes(paramTextureAttributes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextureAttributes getTextureAttributes() {
/* 239 */     if (isLiveOrCompiled() && 
/* 240 */       !getCapability(0)) {
/* 241 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureUnitState3"));
/*     */     }
/* 243 */     return ((TextureUnitStateRetained)this.retained).getTextureAttributes();
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
/*     */   public void setTexCoordGeneration(TexCoordGeneration paramTexCoordGeneration) {
/* 256 */     if (isLiveOrCompiled() && 
/* 257 */       !getCapability(1)) {
/* 258 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureUnitState4"));
/*     */     }
/* 260 */     ((TextureUnitStateRetained)this.retained).setTexCoordGeneration(paramTexCoordGeneration);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TexCoordGeneration getTexCoordGeneration() {
/* 270 */     if (isLiveOrCompiled() && 
/* 271 */       !getCapability(0)) {
/* 272 */       throw new CapabilityNotSetException(J3dI18N.getString("TextureUnitState5"));
/*     */     }
/* 274 */     return ((TextureUnitStateRetained)this.retained).getTexCoordGeneration();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 282 */     TextureUnitState textureUnitState = new TextureUnitState();
/* 283 */     textureUnitState.duplicateNodeComponent(this);
/* 284 */     return textureUnitState;
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
/* 295 */   public void duplicateNodeComponent(NodeComponent paramNodeComponent) { checkDuplicateNodeComponent(paramNodeComponent); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 320 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 322 */     Hashtable hashtable = paramNodeComponent.nodeHashtable;
/*     */     
/* 324 */     TextureUnitStateRetained textureUnitStateRetained1 = (TextureUnitStateRetained)paramNodeComponent.retained;
/*     */     
/* 326 */     TextureUnitStateRetained textureUnitStateRetained2 = (TextureUnitStateRetained)this.retained;
/*     */     
/* 328 */     textureUnitStateRetained2.setTexture((Texture)getNodeComponent(textureUnitStateRetained1.getTexture(), paramBoolean, hashtable));
/*     */ 
/*     */ 
/*     */     
/* 332 */     textureUnitStateRetained2.setTextureAttributes((TextureAttributes)getNodeComponent(textureUnitStateRetained1.getTextureAttributes(), paramBoolean, hashtable));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 337 */     textureUnitStateRetained2.setTexCoordGeneration((TexCoordGeneration)getNodeComponent(textureUnitStateRetained1.getTexCoordGeneration(), paramBoolean, hashtable));
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
/*     */   boolean duplicateChild() {
/* 351 */     if (getDuplicateOnCloneTree()) {
/* 352 */       return true;
/*     */     }
/* 354 */     TextureUnitStateRetained textureUnitStateRetained = (TextureUnitStateRetained)this.retained;
/*     */     
/* 356 */     Texture texture = textureUnitStateRetained.getTexture();
/* 357 */     if (texture != null && texture.duplicateChild()) {
/* 358 */       return true;
/*     */     }
/* 360 */     TextureAttributes textureAttributes = textureUnitStateRetained.getTextureAttributes();
/* 361 */     if (textureAttributes != null && textureAttributes.getDuplicateOnCloneTree()) {
/* 362 */       return true;
/*     */     }
/* 364 */     TexCoordGeneration texCoordGeneration = textureUnitStateRetained.getTexCoordGeneration();
/* 365 */     if (texCoordGeneration != null && texCoordGeneration.getDuplicateOnCloneTree()) {
/* 366 */       return true;
/*     */     }
/* 368 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\TextureUnitState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */