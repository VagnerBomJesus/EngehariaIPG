/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.Hashtable;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Appearance
/*      */   extends NodeComponent
/*      */ {
/*      */   public static final int ALLOW_COLORING_ATTRIBUTES_READ = 8;
/*      */   public static final int ALLOW_COLORING_ATTRIBUTES_WRITE = 9;
/*      */   public static final int ALLOW_TRANSPARENCY_ATTRIBUTES_READ = 10;
/*      */   public static final int ALLOW_TRANSPARENCY_ATTRIBUTES_WRITE = 11;
/*      */   public static final int ALLOW_RENDERING_ATTRIBUTES_READ = 12;
/*      */   public static final int ALLOW_RENDERING_ATTRIBUTES_WRITE = 13;
/*      */   public static final int ALLOW_POLYGON_ATTRIBUTES_READ = 14;
/*      */   public static final int ALLOW_POLYGON_ATTRIBUTES_WRITE = 15;
/*      */   public static final int ALLOW_LINE_ATTRIBUTES_READ = 16;
/*      */   public static final int ALLOW_LINE_ATTRIBUTES_WRITE = 17;
/*      */   public static final int ALLOW_POINT_ATTRIBUTES_READ = 18;
/*      */   public static final int ALLOW_POINT_ATTRIBUTES_WRITE = 19;
/*      */   public static final int ALLOW_MATERIAL_READ = 0;
/*      */   public static final int ALLOW_MATERIAL_WRITE = 1;
/*      */   public static final int ALLOW_TEXTURE_READ = 2;
/*      */   public static final int ALLOW_TEXTURE_WRITE = 3;
/*      */   public static final int ALLOW_TEXTURE_ATTRIBUTES_READ = 6;
/*      */   public static final int ALLOW_TEXTURE_ATTRIBUTES_WRITE = 7;
/*      */   public static final int ALLOW_TEXGEN_READ = 4;
/*      */   public static final int ALLOW_TEXGEN_WRITE = 5;
/*      */   public static final int ALLOW_TEXTURE_UNIT_STATE_READ = 20;
/*      */   public static final int ALLOW_TEXTURE_UNIT_STATE_WRITE = 21;
/*      */   private static final int[] readCapabilities = { 
/*  289 */       8, 16, 0, 18, 14, 12, 4, 6, 2, 20, 10 };
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
/*      */ 
/*      */ 
/*      */   
/*  311 */   public Appearance() { setDefaultReadCapabilities(readCapabilities); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void createRetained() {
/*  319 */     this.retained = new AppearanceRetained();
/*  320 */     this.retained.setSource(this);
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
/*      */   public void setMaterial(Material paramMaterial) {
/*  332 */     if (isLiveOrCompiled() && 
/*  333 */       !getCapability(1))
/*  334 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance0")); 
/*  335 */     ((AppearanceRetained)this.retained).setMaterial(paramMaterial);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Material getMaterial() {
/*  345 */     if (isLiveOrCompiled() && 
/*  346 */       !getCapability(0))
/*  347 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance1")); 
/*  348 */     return ((AppearanceRetained)this.retained).getMaterial();
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
/*      */   public void setColoringAttributes(ColoringAttributes paramColoringAttributes) {
/*  360 */     if (isLiveOrCompiled() && 
/*  361 */       !getCapability(9))
/*  362 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance6")); 
/*  363 */     ((AppearanceRetained)this.retained).setColoringAttributes(paramColoringAttributes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ColoringAttributes getColoringAttributes() {
/*  373 */     if (isLiveOrCompiled() && 
/*  374 */       !getCapability(8))
/*  375 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance7")); 
/*  376 */     return ((AppearanceRetained)this.retained).getColoringAttributes();
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
/*      */   public void setTransparencyAttributes(TransparencyAttributes paramTransparencyAttributes) {
/*  388 */     if (isLiveOrCompiled() && 
/*  389 */       !getCapability(11))
/*  390 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance8")); 
/*  391 */     ((AppearanceRetained)this.retained).setTransparencyAttributes(paramTransparencyAttributes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TransparencyAttributes getTransparencyAttributes() {
/*  401 */     if (isLiveOrCompiled() && 
/*  402 */       !getCapability(10))
/*  403 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance9")); 
/*  404 */     return ((AppearanceRetained)this.retained).getTransparencyAttributes();
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
/*      */   public void setRenderingAttributes(RenderingAttributes paramRenderingAttributes) {
/*  416 */     if (isLiveOrCompiled() && 
/*  417 */       !getCapability(13))
/*  418 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance10")); 
/*  419 */     ((AppearanceRetained)this.retained).setRenderingAttributes(paramRenderingAttributes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RenderingAttributes getRenderingAttributes() {
/*  429 */     if (isLiveOrCompiled() && 
/*  430 */       !getCapability(12))
/*  431 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance11")); 
/*  432 */     return ((AppearanceRetained)this.retained).getRenderingAttributes();
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
/*      */   public void setPolygonAttributes(PolygonAttributes paramPolygonAttributes) {
/*  444 */     if (isLiveOrCompiled() && 
/*  445 */       !getCapability(15))
/*  446 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance12")); 
/*  447 */     ((AppearanceRetained)this.retained).setPolygonAttributes(paramPolygonAttributes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PolygonAttributes getPolygonAttributes() {
/*  457 */     if (isLiveOrCompiled() && 
/*  458 */       !getCapability(14))
/*  459 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance13")); 
/*  460 */     return ((AppearanceRetained)this.retained).getPolygonAttributes();
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
/*      */   public void setLineAttributes(LineAttributes paramLineAttributes) {
/*  472 */     if (isLiveOrCompiled() && 
/*  473 */       !getCapability(17))
/*  474 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance14")); 
/*  475 */     ((AppearanceRetained)this.retained).setLineAttributes(paramLineAttributes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public LineAttributes getLineAttributes() {
/*  485 */     if (isLiveOrCompiled() && 
/*  486 */       !getCapability(16))
/*  487 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance15")); 
/*  488 */     return ((AppearanceRetained)this.retained).getLineAttributes();
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
/*      */   public void setPointAttributes(PointAttributes paramPointAttributes) {
/*  500 */     if (isLiveOrCompiled() && 
/*  501 */       !getCapability(19))
/*  502 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance16")); 
/*  503 */     ((AppearanceRetained)this.retained).setPointAttributes(paramPointAttributes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PointAttributes getPointAttributes() {
/*  513 */     if (isLiveOrCompiled() && 
/*  514 */       !getCapability(18))
/*  515 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance17")); 
/*  516 */     return ((AppearanceRetained)this.retained).getPointAttributes();
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
/*      */   public void setTexture(Texture paramTexture) {
/*  549 */     if (isLiveOrCompiled() && 
/*  550 */       !getCapability(3)) {
/*  551 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance2"));
/*      */     }
/*      */     
/*  554 */     if (paramTexture != null) {
/*  555 */       ImageComponent[] arrayOfImageComponent = ((TextureRetained)paramTexture.retained).getImages();
/*  556 */       if (arrayOfImageComponent != null) {
/*  557 */         for (byte b = 0; b < arrayOfImageComponent.length; b++) {
/*  558 */           validateImageIllegalSharing(arrayOfImageComponent[b]);
/*      */         }
/*      */       }
/*      */     } 
/*      */     
/*  563 */     ((AppearanceRetained)this.retained).setTexture(paramTexture);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Texture getTexture() {
/*  573 */     if (isLiveOrCompiled() && 
/*  574 */       !getCapability(2))
/*  575 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance3")); 
/*  576 */     return ((AppearanceRetained)this.retained).getTexture();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureAttributes(TextureAttributes paramTextureAttributes) {
/*  600 */     if (isLiveOrCompiled() && 
/*  601 */       !getCapability(7))
/*  602 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance4")); 
/*  603 */     ((AppearanceRetained)this.retained).setTextureAttributes(paramTextureAttributes);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TextureAttributes getTextureAttributes() {
/*  613 */     if (isLiveOrCompiled() && 
/*  614 */       !getCapability(6))
/*  615 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance5")); 
/*  616 */     return ((AppearanceRetained)this.retained).getTextureAttributes();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTexCoordGeneration(TexCoordGeneration paramTexCoordGeneration) {
/*  640 */     if (isLiveOrCompiled() && 
/*  641 */       !getCapability(5))
/*  642 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance18")); 
/*  643 */     ((AppearanceRetained)this.retained).setTexCoordGeneration(paramTexCoordGeneration);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TexCoordGeneration getTexCoordGeneration() {
/*  653 */     if (isLiveOrCompiled() && 
/*  654 */       !getCapability(4))
/*  655 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance19")); 
/*  656 */     return ((AppearanceRetained)this.retained).getTexCoordGeneration();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTextureUnitState(TextureUnitState[] paramArrayOfTextureUnitState) {
/*  700 */     if (isLiveOrCompiled() && 
/*  701 */       !getCapability(21)) {
/*  702 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance20"));
/*      */     }
/*      */     
/*  705 */     if (paramArrayOfTextureUnitState != null) {
/*  706 */       for (byte b = 0; b < paramArrayOfTextureUnitState.length; b++) {
/*  707 */         if (paramArrayOfTextureUnitState[b] != null) {
/*  708 */           TextureRetained textureRetained = ((TextureUnitStateRetained)(paramArrayOfTextureUnitState[b]).retained).texture;
/*      */           
/*  710 */           if (textureRetained != null) {
/*  711 */             ImageComponent[] arrayOfImageComponent = textureRetained.getImages();
/*  712 */             if (arrayOfImageComponent != null) {
/*  713 */               for (byte b1 = 0; b1 < arrayOfImageComponent.length; b1++) {
/*  714 */                 validateImageIllegalSharing(arrayOfImageComponent[b1]);
/*      */               }
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  722 */     ((AppearanceRetained)this.retained).setTextureUnitState(paramArrayOfTextureUnitState);
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
/*      */   public void setTextureUnitState(int paramInt, TextureUnitState paramTextureUnitState) {
/*  755 */     if (isLiveOrCompiled() && 
/*  756 */       !getCapability(21)) {
/*  757 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance20"));
/*      */     }
/*      */     
/*  760 */     if (paramTextureUnitState != null) {
/*  761 */       TextureRetained textureRetained = ((TextureUnitStateRetained)paramTextureUnitState.retained).texture;
/*      */       
/*  763 */       if (textureRetained != null) {
/*  764 */         ImageComponent[] arrayOfImageComponent = textureRetained.getImages();
/*  765 */         if (arrayOfImageComponent != null) {
/*  766 */           for (byte b = 0; b < arrayOfImageComponent.length; b++) {
/*  767 */             validateImageIllegalSharing(arrayOfImageComponent[b]);
/*      */           }
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  773 */     ((AppearanceRetained)this.retained).setTextureUnitState(paramInt, paramTextureUnitState);
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
/*      */   public TextureUnitState[] getTextureUnitState() {
/*  789 */     if (isLiveOrCompiled() && 
/*  790 */       !getCapability(20)) {
/*  791 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance21"));
/*      */     }
/*  793 */     return ((AppearanceRetained)this.retained).getTextureUnitState();
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
/*      */   public TextureUnitState getTextureUnitState(int paramInt) {
/*  811 */     if (isLiveOrCompiled() && 
/*  812 */       !getCapability(20)) {
/*  813 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance21"));
/*      */     }
/*  815 */     return ((AppearanceRetained)this.retained).getTextureUnitState(paramInt);
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
/*      */   public int getTextureUnitCount() {
/*  833 */     if (isLiveOrCompiled() && 
/*  834 */       !getCapability(20)) {
/*  835 */       throw new CapabilityNotSetException(J3dI18N.getString("Appearance21"));
/*      */     }
/*  837 */     return ((AppearanceRetained)this.retained).getTextureUnitCount();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeComponent cloneNodeComponent() {
/*  845 */     Appearance appearance = new Appearance();
/*  846 */     appearance.duplicateNodeComponent(this);
/*  847 */     return appearance;
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
/*  858 */   public void duplicateNodeComponent(NodeComponent paramNodeComponent) { checkDuplicateNodeComponent(paramNodeComponent); }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/*  883 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*      */     
/*  885 */     Hashtable hashtable = paramNodeComponent.nodeHashtable;
/*      */     
/*  887 */     AppearanceRetained appearanceRetained1 = (AppearanceRetained)paramNodeComponent.retained;
/*      */     
/*  889 */     AppearanceRetained appearanceRetained2 = (AppearanceRetained)this.retained;
/*      */     
/*  891 */     appearanceRetained2.setMaterial((Material)getNodeComponent(appearanceRetained1.getMaterial(), paramBoolean, hashtable));
/*      */ 
/*      */ 
/*      */     
/*  895 */     appearanceRetained2.setColoringAttributes((ColoringAttributes)getNodeComponent(appearanceRetained1.getColoringAttributes(), paramBoolean, hashtable));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  901 */     appearanceRetained2.setTransparencyAttributes((TransparencyAttributes)getNodeComponent(appearanceRetained1.getTransparencyAttributes(), paramBoolean, hashtable));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  907 */     appearanceRetained2.setRenderingAttributes((RenderingAttributes)getNodeComponent(appearanceRetained1.getRenderingAttributes(), paramBoolean, hashtable));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  913 */     appearanceRetained2.setPolygonAttributes((PolygonAttributes)getNodeComponent(appearanceRetained1.getPolygonAttributes(), paramBoolean, hashtable));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  919 */     appearanceRetained2.setLineAttributes((LineAttributes)getNodeComponent(appearanceRetained1.getLineAttributes(), paramBoolean, hashtable));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  925 */     appearanceRetained2.setPointAttributes((PointAttributes)getNodeComponent(appearanceRetained1.getPointAttributes(), paramBoolean, hashtable));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  930 */     appearanceRetained2.setTexture((Texture)getNodeComponent(appearanceRetained1.getTexture(), paramBoolean, hashtable));
/*      */ 
/*      */ 
/*      */     
/*  934 */     appearanceRetained2.setTextureAttributes((TextureAttributes)getNodeComponent(appearanceRetained1.getTextureAttributes(), paramBoolean, hashtable));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  939 */     appearanceRetained2.setTexCoordGeneration((TexCoordGeneration)getNodeComponent(appearanceRetained1.getTexCoordGeneration(), paramBoolean, hashtable));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  944 */     TextureUnitState[] arrayOfTextureUnitState = appearanceRetained1.getTextureUnitState();
/*  945 */     if (arrayOfTextureUnitState != null) {
/*  946 */       appearanceRetained2.setTextureUnitState(arrayOfTextureUnitState);
/*  947 */       for (byte b = 0; b < arrayOfTextureUnitState.length; b++) {
/*  948 */         appearanceRetained2.setTextureUnitState(b, (TextureUnitState)getNodeComponent(arrayOfTextureUnitState[b], paramBoolean, hashtable));
/*      */       }
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
/*      */   boolean duplicateChild() {
/*  965 */     if (getDuplicateOnCloneTree()) {
/*  966 */       return true;
/*      */     }
/*  968 */     AppearanceRetained appearanceRetained = (AppearanceRetained)this.retained;
/*      */ 
/*      */ 
/*      */     
/*  972 */     Material material = appearanceRetained.getMaterial();
/*  973 */     if (material != null && material.getDuplicateOnCloneTree()) {
/*  974 */       return true;
/*      */     }
/*  976 */     ColoringAttributes coloringAttributes = appearanceRetained.getColoringAttributes();
/*  977 */     if (coloringAttributes != null && coloringAttributes.getDuplicateOnCloneTree()) {
/*  978 */       return true;
/*      */     }
/*  980 */     TransparencyAttributes transparencyAttributes = appearanceRetained.getTransparencyAttributes();
/*  981 */     if (transparencyAttributes != null && transparencyAttributes.getDuplicateOnCloneTree()) {
/*  982 */       return true;
/*      */     }
/*  984 */     PolygonAttributes polygonAttributes = appearanceRetained.getPolygonAttributes();
/*  985 */     if (polygonAttributes != null && polygonAttributes.getDuplicateOnCloneTree()) {
/*  986 */       return true;
/*      */     }
/*  988 */     LineAttributes lineAttributes = appearanceRetained.getLineAttributes();
/*  989 */     if (lineAttributes != null && lineAttributes.getDuplicateOnCloneTree()) {
/*  990 */       return true;
/*      */     }
/*  992 */     PointAttributes pointAttributes = appearanceRetained.getPointAttributes();
/*  993 */     if (pointAttributes != null && pointAttributes.getDuplicateOnCloneTree()) {
/*  994 */       return true;
/*      */     }
/*  996 */     Texture texture = appearanceRetained.getTexture();
/*  997 */     if (texture != null && texture.duplicateChild()) {
/*  998 */       return true;
/*      */     }
/* 1000 */     TextureAttributes textureAttributes = appearanceRetained.getTextureAttributes();
/* 1001 */     if (textureAttributes != null && textureAttributes.getDuplicateOnCloneTree()) {
/* 1002 */       return true;
/*      */     }
/* 1004 */     TexCoordGeneration texCoordGeneration = appearanceRetained.getTexCoordGeneration();
/* 1005 */     if (texCoordGeneration != null && texCoordGeneration.getDuplicateOnCloneTree()) {
/* 1006 */       return true;
/*      */     }
/*      */ 
/*      */     
/* 1010 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\Appearance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */