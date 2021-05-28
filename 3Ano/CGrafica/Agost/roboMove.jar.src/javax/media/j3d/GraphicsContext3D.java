/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Point;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Vector;
/*      */ import javax.vecmath.Color3f;
/*      */ import javax.vecmath.Vector3d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class GraphicsContext3D
/*      */ {
/*      */   public static final int STEREO_LEFT = 0;
/*      */   public static final int STEREO_RIGHT = 1;
/*      */   public static final int STEREO_BOTH = 2;
/*      */   Canvas3D canvas3d;
/*      */   private Background uBackground;
/*      */   private Fog uFog;
/*      */   private Appearance uAppearance;
/*      */   private Vector uLights;
/*      */   private HiResCoord uHiRes;
/*      */   private Vector uSounds;
/*      */   private AuralAttributes uAuralAttributes;
/*      */   private boolean uBufferOverride;
/*      */   private boolean uFrontBufferRendering;
/*      */   private int uStereoMode;
/*      */   private ModelClip uModelClip;
/*      */   Background background;
/*      */   BackgroundRetained black;
/*      */   Fog fog;
/*      */   ModelClip modelClip;
/*      */   Appearance appearance;
/*      */   AppearanceRetained defaultAppearanceRetained;
/*      */   Vector lights;
/*      */   HiResCoord hiRes;
/*      */   Transform3D modelTransform;
/*      */   Transform3D identityTransform;
/*      */   Transform3D modelClipTransform;
/*      */   Transform3D normalTransform;
/*      */   boolean normalTransformNeedToUpdate;
/*      */   Vector sounds;
/*      */   AuralAttributes auralAttributes;
/*      */   LightSet ls;
/*      */   LightRetained[] lightlist;
/*      */   Color3f sceneAmbient;
/*      */   int numLights;
/*      */   Transform3D compTransform;
/*      */   Transform3D drawTransform;
/*      */   Transform3D vpcToEc;
/*      */   boolean lightsChanged;
/*      */   boolean soundsChanged;
/*      */   boolean bufferOverride;
/*      */   boolean frontBufferRendering;
/*      */   int stereoMode;
/*      */   byte[] byteBuffer;
/*      */   float[] floatBuffer;
/*      */   int[] intBuffer;
/*      */   float red;
/*      */   float green;
/*      */   float blue;
/*      */   float dRed;
/*      */   float dGreen;
/*      */   float dBlue;
/*      */   float alpha;
/*      */   boolean visible;
/*      */   int polygonMode;
/*      */   boolean lineAA;
/*      */   boolean pointAA;
/*      */   boolean enableLighting;
/*      */   private Appearance defaultAppearance;
/*      */   private boolean geometryIsLocked;
/*      */   private boolean ignoreVertexColors;
/*      */   static final int CLEAR = 0;
/*      */   static final int DRAW = 1;
/*      */   static final int SWAP = 2;
/*      */   static final int READ_RASTER = 3;
/*      */   static final int SET_APPEARANCE = 4;
/*      */   static final int SET_BACKGROUND = 5;
/*      */   static final int SET_FOG = 6;
/*      */   static final int SET_LIGHT = 7;
/*      */   static final int INSERT_LIGHT = 8;
/*      */   static final int REMOVE_LIGHT = 9;
/*      */   static final int ADD_LIGHT = 10;
/*      */   static final int SET_HI_RES = 11;
/*      */   static final int SET_MODEL_TRANSFORM = 12;
/*      */   static final int MULTIPLY_MODEL_TRANSFORM = 13;
/*      */   static final int SET_SOUND = 14;
/*      */   static final int INSERT_SOUND = 15;
/*      */   static final int REMOVE_SOUND = 16;
/*      */   static final int ADD_SOUND = 17;
/*      */   static final int SET_AURAL_ATTRIBUTES = 18;
/*      */   static final int SET_BUFFER_OVERRIDE = 19;
/*      */   static final int SET_FRONT_BUFFER_RENDERING = 20;
/*      */   static final int SET_STEREO_MODE = 21;
/*      */   static final int FLUSH = 22;
/*      */   static final int FLUSH2D = 23;
/*      */   static final int DRAWANDFLUSH2D = 24;
/*      */   static final int SET_MODELCLIP = 25;
/*      */   static final int DISPOSE2D = 26;
/*      */   static final int NCOMMANDS = 27;
/*  289 */   private static Integer[] commands = new Integer[27]; private static final int BUFFER_MODE = 1;
/*  290 */   private static Integer[] stereoModes = { new Integer(0), new Integer(1), new Integer(2) }; GraphicsContext3D(Canvas3D paramCanvas3D) { this.canvas3d = null; this.uBackground = null; this.uFog = null; this.uAppearance = null; this.uLights = new Vector(); this.uHiRes = new HiResCoord(); this.uSounds = new Vector(); this.uAuralAttributes = null; this.uBufferOverride = false; this.uFrontBufferRendering = false; this.uStereoMode = 2; this.uModelClip = null; this.background = null; this.black = new BackgroundRetained(); this.fog = null; this.modelClip = null; this.appearance = null; this.defaultAppearanceRetained = new AppearanceRetained(); this.lights = new Vector(); this.hiRes = new HiResCoord(); this.modelTransform = new Transform3D(); this.identityTransform = new Transform3D(); this.modelClipTransform = null; this.normalTransform = null; this.normalTransformNeedToUpdate = true; this.sounds = new Vector(); this.auralAttributes = null; this.ls = null; this.lightlist = null; this.sceneAmbient = new Color3f(0.0F, 0.0F, 0.0F); this.numLights = 0; this.compTransform = new Transform3D(); this.drawTransform = new Transform3D(); this.lightsChanged = false; this.soundsChanged = false; this.bufferOverride = false; this.frontBufferRendering = false; this.stereoMode = 2; this.byteBuffer = new byte[1]; this.floatBuffer = new float[1]; this.intBuffer = new int[1]; this.red = 1.0F; this.green = 1.0F; this.blue = 1.0F; this.dRed = 1.0F; this.dGreen = 1.0F; this.dBlue = 1.0F; this.alpha = 0.0F; this.visible = true;
/*      */     this.polygonMode = 2;
/*      */     this.lineAA = false;
/*      */     this.pointAA = false;
/*      */     this.enableLighting = false;
/*      */     this.defaultAppearance = null;
/*      */     this.geometryIsLocked = false;
/*      */     this.ignoreVertexColors = false;
/*  298 */     this.dirtyMask = 0;
/*      */ 
/*      */     
/*  301 */     this.numActiveTexUnit = 0;
/*  302 */     this.lastActiveTexUnitIndex = 0;
/*      */ 
/*      */     
/*  305 */     this.readRasterReady = false;
/*      */ 
/*      */     
/*  308 */     this.gcReady = false;
/*  309 */     this.waiting = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  318 */     this.canvas3d = paramCanvas3D; }
/*      */   
/*      */   private int dirtyMask;
/*      */   private int numActiveTexUnit;
/*      */   private int lastActiveTexUnitIndex;
/*      */   private boolean gcReady;
/*      */   private int waiting;
/*      */   
/*  326 */   public Canvas3D getCanvas3D() { return this.canvas3d; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAppearance(Appearance paramAppearance) {
/*  352 */     if (paramAppearance == null) {
/*  353 */       if (this.defaultAppearance == null) {
/*  354 */         this.defaultAppearance = new Appearance();
/*      */       }
/*  356 */       paramAppearance = this.defaultAppearance;
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  363 */       AppearanceRetained appearanceRetained = (AppearanceRetained)paramAppearance.retained;
/*  364 */       if (appearanceRetained.texture != null) {
/*  365 */         assert appearanceRetained.texUnitState == null;
/*  366 */         TextureRetained textureRetained = appearanceRetained.texture;
/*  367 */         ImageComponent[] arrayOfImageComponent = textureRetained.getImages();
/*  368 */         if (arrayOfImageComponent != null) {
/*  369 */           for (byte b = 0; b < arrayOfImageComponent.length; b++) {
/*  370 */             if (arrayOfImageComponent[b] != null) {
/*  371 */               ImageComponentRetained imageComponentRetained = (ImageComponentRetained)(arrayOfImageComponent[b]).retained;
/*      */               
/*  373 */               if (imageComponentRetained.getUsedByOffScreen()) {
/*  374 */                 throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D30"));
/*      */               }
/*      */             }
/*      */           
/*      */           } 
/*      */         }
/*  380 */       } else if (appearanceRetained.texUnitState != null) {
/*  381 */         for (byte b = 0; b < appearanceRetained.texUnitState.length; b++) {
/*  382 */           TextureRetained textureRetained = (appearanceRetained.texUnitState[b]).texture;
/*  383 */           ImageComponent[] arrayOfImageComponent = textureRetained.getImages();
/*  384 */           if (arrayOfImageComponent != null) {
/*  385 */             for (byte b1 = 0; b1 < arrayOfImageComponent.length; b1++) {
/*  386 */               if (arrayOfImageComponent[b1] != null) {
/*  387 */                 ImageComponentRetained imageComponentRetained = (ImageComponentRetained)(arrayOfImageComponent[b1]).retained;
/*      */                 
/*  389 */                 if (imageComponentRetained.getUsedByOffScreen()) {
/*  390 */                   throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D30"));
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  399 */     this.uAppearance = paramAppearance;
/*  400 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/*  404 */       doSetAppearance(paramAppearance);
/*  405 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/*  407 */       sendRenderMessage(false, 4, paramAppearance, null);
/*      */     } else {
/*  409 */       sendRenderMessage(true, 4, paramAppearance, null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doSetAppearance(Appearance paramAppearance) {
/*  415 */     assert paramAppearance != null;
/*      */ 
/*      */     
/*  418 */     MaterialRetained materialRetained = ((AppearanceRetained)paramAppearance.retained).material;
/*  419 */     if (materialRetained != null) {
/*  420 */       materialRetained.setInImmCtx(true);
/*  421 */       this.enableLighting = ((MaterialRetained)materialRetained).lightingEnable;
/*  422 */       this.dRed = ((MaterialRetained)materialRetained).diffuseColor.x;
/*  423 */       this.dGreen = ((MaterialRetained)materialRetained).diffuseColor.y;
/*  424 */       this.dBlue = ((MaterialRetained)materialRetained).diffuseColor.z;
/*      */     } else {
/*  426 */       this.enableLighting = false;
/*      */     } 
/*      */     
/*  429 */     if (paramAppearance instanceof ShaderAppearance) {
/*      */       
/*  431 */       ShaderProgramRetained shaderProgramRetained = ((ShaderAppearanceRetained)paramAppearance.retained).shaderProgram;
/*  432 */       if (shaderProgramRetained != null) {
/*  433 */         shaderProgramRetained.setInImmCtx(true);
/*  434 */         Shader[] arrayOfShader = shaderProgramRetained.getShaders();
/*  435 */         if (arrayOfShader != null) {
/*  436 */           for (byte b = 0; b < arrayOfShader.length; b++) {
/*  437 */             if (arrayOfShader[b] != null) {
/*  438 */               ((ShaderRetained)(arrayOfShader[b]).retained).setInImmCtx(true);
/*      */             }
/*      */           } 
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  445 */       ShaderAttributeSetRetained shaderAttributeSetRetained = ((ShaderAppearanceRetained)paramAppearance.retained).shaderAttributeSet;
/*      */       
/*  447 */       if (shaderAttributeSetRetained != null) {
/*  448 */         shaderAttributeSetRetained.setInImmCtx(true);
/*  449 */         ShaderAttribute[] arrayOfShaderAttribute = shaderAttributeSetRetained.getAll();
/*  450 */         if (arrayOfShaderAttribute != null) {
/*  451 */           for (byte b = 0; b < arrayOfShaderAttribute.length; b++) {
/*  452 */             if (arrayOfShaderAttribute[b] != null) {
/*  453 */               ((ShaderAttributeRetained)(arrayOfShaderAttribute[b]).retained).setInImmCtx(true);
/*      */             }
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  460 */     if (((AppearanceRetained)paramAppearance.retained).texUnitState != null) {
/*  461 */       TextureUnitStateRetained[] arrayOfTextureUnitStateRetained = ((AppearanceRetained)paramAppearance.retained).texUnitState;
/*      */ 
/*      */       
/*  464 */       for (byte b = 0; b < arrayOfTextureUnitStateRetained.length; b++) {
/*  465 */         if (arrayOfTextureUnitStateRetained[b] != null) {
/*  466 */           arrayOfTextureUnitStateRetained[b].setInImmCtx(true);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  471 */     TextureRetained textureRetained = ((AppearanceRetained)paramAppearance.retained).texture;
/*  472 */     if (textureRetained != null) {
/*  473 */       textureRetained.setInImmCtx(true);
/*      */     }
/*      */     
/*  476 */     TexCoordGenerationRetained texCoordGenerationRetained = ((AppearanceRetained)paramAppearance.retained).texCoordGeneration;
/*  477 */     if (texCoordGenerationRetained != null) {
/*  478 */       texCoordGenerationRetained.setInImmCtx(true);
/*      */     }
/*      */     
/*  481 */     TextureAttributesRetained textureAttributesRetained = ((AppearanceRetained)paramAppearance.retained).textureAttributes;
/*  482 */     if (textureAttributesRetained != null) {
/*  483 */       textureAttributesRetained.setInImmCtx(true);
/*      */     }
/*      */     
/*  486 */     ColoringAttributesRetained coloringAttributesRetained = ((AppearanceRetained)paramAppearance.retained).coloringAttributes;
/*  487 */     if (coloringAttributesRetained != null) {
/*  488 */       coloringAttributesRetained.setInImmCtx(true);
/*  489 */       this.red = ((ColoringAttributesRetained)coloringAttributesRetained).color.x;
/*  490 */       this.green = ((ColoringAttributesRetained)coloringAttributesRetained).color.y;
/*  491 */       this.blue = ((ColoringAttributesRetained)coloringAttributesRetained).color.z;
/*      */     } else {
/*  493 */       this.red = 1.0F;
/*  494 */       this.green = 1.0F;
/*  495 */       this.blue = 1.0F;
/*      */     } 
/*      */     
/*  498 */     TransparencyAttributesRetained transparencyAttributesRetained = ((AppearanceRetained)paramAppearance.retained).transparencyAttributes;
/*  499 */     if (transparencyAttributesRetained != null) {
/*  500 */       transparencyAttributesRetained.setInImmCtx(true);
/*  501 */       this.alpha = 1.0F - ((TransparencyAttributesRetained)transparencyAttributesRetained).transparency;
/*      */     } else {
/*  503 */       this.alpha = 1.0F;
/*      */     } 
/*      */     
/*  506 */     RenderingAttributesRetained renderingAttributesRetained = ((AppearanceRetained)paramAppearance.retained).renderingAttributes;
/*  507 */     if (renderingAttributesRetained != null) {
/*  508 */       renderingAttributesRetained.setInImmCtx(true);
/*  509 */       this.visible = ((RenderingAttributesRetained)renderingAttributesRetained).visible;
/*      */     } else {
/*  511 */       this.visible = true;
/*      */     } 
/*  513 */     PolygonAttributesRetained polygonAttributesRetained = ((AppearanceRetained)paramAppearance.retained).polygonAttributes;
/*  514 */     if (polygonAttributesRetained != null) {
/*  515 */       polygonAttributesRetained.setInImmCtx(true);
/*  516 */       this.polygonMode = ((PolygonAttributesRetained)polygonAttributesRetained).polygonMode;
/*      */     } else {
/*  518 */       this.polygonMode = 2;
/*      */     } 
/*      */     
/*  521 */     LineAttributesRetained lineAttributesRetained = ((AppearanceRetained)paramAppearance.retained).lineAttributes;
/*  522 */     if (lineAttributesRetained != null) {
/*  523 */       lineAttributesRetained.setInImmCtx(true);
/*  524 */       this.lineAA = ((LineAttributesRetained)lineAttributesRetained).lineAntialiasing;
/*      */     } else {
/*      */       
/*  527 */       this.lineAA = false;
/*      */     } 
/*      */     
/*  530 */     PointAttributesRetained pointAttributesRetained = ((AppearanceRetained)paramAppearance.retained).pointAttributes;
/*  531 */     if (pointAttributesRetained != null) {
/*  532 */       if (pointAttributesRetained.source.isLive())
/*  533 */         pointAttributesRetained.setInImmCtx(true); 
/*  534 */       this.pointAA = ((PointAttributesRetained)pointAttributesRetained).pointAntialiasing;
/*      */     } else {
/*  536 */       this.pointAA = false;
/*      */     } 
/*      */ 
/*      */     
/*  540 */     if (this.appearance != null) {
/*  541 */       AppearanceRetained appearanceRetained = (AppearanceRetained)this.appearance.retained;
/*  542 */       appearanceRetained.setInImmCtx(false);
/*  543 */       if (appearanceRetained.material != null) {
/*  544 */         appearanceRetained.material.setInImmCtx(false);
/*      */       }
/*      */       
/*  547 */       if (appearanceRetained instanceof ShaderAppearanceRetained) {
/*      */         
/*  549 */         ShaderProgramRetained shaderProgramRetained = ((ShaderAppearanceRetained)appearanceRetained).shaderProgram;
/*  550 */         if (shaderProgramRetained != null) {
/*  551 */           shaderProgramRetained.setInImmCtx(false);
/*  552 */           Shader[] arrayOfShader = shaderProgramRetained.getShaders();
/*  553 */           if (arrayOfShader != null) {
/*  554 */             for (byte b = 0; b < arrayOfShader.length; b++) {
/*  555 */               if (arrayOfShader[b] != null) {
/*  556 */                 ((ShaderRetained)(arrayOfShader[b]).retained).setInImmCtx(false);
/*      */               }
/*      */             } 
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/*  563 */         ShaderAttributeSetRetained shaderAttributeSetRetained = ((ShaderAppearanceRetained)appearanceRetained).shaderAttributeSet;
/*  564 */         if (shaderAttributeSetRetained != null) {
/*  565 */           shaderAttributeSetRetained.setInImmCtx(false);
/*  566 */           ShaderAttribute[] arrayOfShaderAttribute = shaderAttributeSetRetained.getAll();
/*  567 */           if (arrayOfShaderAttribute != null) {
/*  568 */             for (byte b = 0; b < arrayOfShaderAttribute.length; b++) {
/*  569 */               if (arrayOfShaderAttribute[b] != null) {
/*  570 */                 ((ShaderAttributeRetained)(arrayOfShaderAttribute[b]).retained).setInImmCtx(false);
/*      */               }
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  577 */       if (appearanceRetained.texUnitState != null)
/*  578 */         for (byte b = 0; b < appearanceRetained.texUnitState.length; b++) {
/*  579 */           if (appearanceRetained.texUnitState[false] != null) {
/*  580 */             appearanceRetained.texUnitState[0].setInImmCtx(false);
/*      */           }
/*      */         }  
/*  583 */       if (appearanceRetained.texture != null) {
/*  584 */         appearanceRetained.texture.setInImmCtx(false);
/*      */       }
/*  586 */       if (appearanceRetained.texCoordGeneration != null) {
/*  587 */         appearanceRetained.texCoordGeneration.setInImmCtx(false);
/*      */       }
/*  589 */       if (appearanceRetained.textureAttributes != null) {
/*  590 */         appearanceRetained.textureAttributes.setInImmCtx(false);
/*      */       }
/*  592 */       if (appearanceRetained.coloringAttributes != null) {
/*  593 */         appearanceRetained.coloringAttributes.setInImmCtx(false);
/*      */       }
/*  595 */       if (appearanceRetained.transparencyAttributes != null) {
/*  596 */         appearanceRetained.transparencyAttributes.setInImmCtx(false);
/*      */       }
/*  598 */       if (appearanceRetained.renderingAttributes != null) {
/*  599 */         appearanceRetained.renderingAttributes.setInImmCtx(false);
/*      */       }
/*  601 */       if (appearanceRetained.polygonAttributes != null) {
/*  602 */         appearanceRetained.polygonAttributes.setInImmCtx(false);
/*      */       }
/*  604 */       if (appearanceRetained.lineAttributes != null) {
/*  605 */         appearanceRetained.lineAttributes.setInImmCtx(false);
/*      */       }
/*  607 */       if (appearanceRetained.pointAttributes != null) {
/*  608 */         appearanceRetained.pointAttributes.setInImmCtx(false);
/*      */       }
/*      */     } 
/*      */     
/*  612 */     ((AppearanceRetained)paramAppearance.retained).setInImmCtx(true);
/*  613 */     this.appearance = paramAppearance;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  621 */   public Appearance getAppearance() { return this.uAppearance; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBackground(Background paramBackground) {
/*  650 */     if (paramBackground.isLive()) {
/*  651 */       throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D11"));
/*      */     }
/*  653 */     BackgroundRetained backgroundRetained = (BackgroundRetained)paramBackground.retained;
/*  654 */     ImageComponent2D imageComponent2D = backgroundRetained.getImage();
/*  655 */     if (imageComponent2D != null) {
/*  656 */       ImageComponent2DRetained imageComponent2DRetained = (ImageComponent2DRetained)imageComponent2D.retained;
/*  657 */       if (imageComponent2DRetained.getUsedByOffScreen()) {
/*  658 */         throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D31"));
/*      */       }
/*      */     } 
/*      */     
/*  662 */     if (((BackgroundRetained)paramBackground.retained).geometryBranch != null) {
/*  663 */       throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D22"));
/*      */     }
/*      */     
/*  666 */     this.uBackground = paramBackground;
/*  667 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/*  671 */       doSetBackground(paramBackground);
/*  672 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/*  674 */       sendRenderMessage(false, 5, paramBackground, null);
/*      */     } else {
/*  676 */       sendRenderMessage(true, 5, paramBackground, null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void doSetBackground(Background paramBackground) {
/*  683 */     if (this.background != null) {
/*  684 */       BackgroundRetained backgroundRetained1 = (BackgroundRetained)this.background.retained;
/*  685 */       backgroundRetained1.setInImmCtx(false);
/*      */     } 
/*  687 */     BackgroundRetained backgroundRetained = (BackgroundRetained)paramBackground.retained;
/*  688 */     backgroundRetained.setInImmCtx(true);
/*      */     
/*  690 */     this.background = paramBackground;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  698 */   public Background getBackground() { return this.uBackground; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFog(Fog paramFog) {
/*  718 */     if (paramFog != null && paramFog.isLive()) {
/*  719 */       throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D12"));
/*      */     }
/*  721 */     this.uFog = paramFog;
/*  722 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/*  726 */       doSetFog(paramFog);
/*  727 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/*  729 */       sendRenderMessage(false, 6, paramFog, null);
/*      */     } else {
/*  731 */       sendRenderMessage(true, 6, paramFog, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   void doSetFog(Fog paramFog) {
/*  736 */     if (this.fog != null) {
/*  737 */       ((FogRetained)this.fog.retained).setInImmCtx(false);
/*      */     }
/*  739 */     this.fog = paramFog;
/*  740 */     if (paramFog != null) {
/*  741 */       ((FogRetained)paramFog.retained).setInImmCtx(true);
/*      */ 
/*      */       
/*  744 */       updateFogState((FogRetained)paramFog.retained);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  753 */   public Fog getFog() { return this.uFog; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setModelClip(ModelClip paramModelClip) {
/*  778 */     if (paramModelClip != null && paramModelClip.isLive()) {
/*  779 */       throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D25"));
/*      */     }
/*  781 */     this.uModelClip = paramModelClip;
/*  782 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/*  786 */       doSetModelClip(paramModelClip);
/*  787 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/*  789 */       sendRenderMessage(false, 25, paramModelClip, null);
/*      */     } else {
/*      */       
/*  792 */       sendRenderMessage(true, 25, paramModelClip, null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doSetModelClip(ModelClip paramModelClip) {
/*  798 */     ModelClipRetained modelClipRetained = null;
/*      */     
/*  800 */     this.modelClip = paramModelClip;
/*      */     
/*  802 */     if (this.modelClip != null) {
/*  803 */       modelClipRetained = (ModelClipRetained)this.modelClip.retained;
/*  804 */       modelClipRetained.setInImmCtx(true);
/*      */       
/*  806 */       if (this.modelClipTransform == null) {
/*  807 */         this.modelClipTransform = new Transform3D();
/*      */       }
/*      */       
/*  810 */       this.modelClipTransform.set(this.compTransform);
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
/*  821 */   public ModelClip getModelClip() { return this.uModelClip; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLight(Light paramLight, int paramInt) {
/*  842 */     if (paramLight == null) {
/*  843 */       throw new NullPointerException(J3dI18N.getString("GraphicsContext3D13"));
/*      */     }
/*  845 */     if (paramLight.isLive()) {
/*  846 */       throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D14"));
/*      */     }
/*  848 */     this.uLights.setElementAt(paramLight, paramInt);
/*  849 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/*  853 */       doSetLight(paramLight, paramInt);
/*  854 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/*  856 */       sendRenderMessage(false, 7, paramLight, new Integer(paramInt));
/*      */     } else {
/*      */       
/*  859 */       sendRenderMessage(true, 7, paramLight, new Integer(paramInt));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void doSetLight(Light paramLight, int paramInt) {
/*  867 */     Light light = (Light)this.lights.elementAt(paramInt);
/*  868 */     if (light != null) {
/*  869 */       ((LightRetained)light.retained).setInImmCtx(false);
/*      */     }
/*  871 */     ((LightRetained)paramLight.retained).setInImmCtx(true);
/*  872 */     updateLightState((LightRetained)paramLight.retained);
/*  873 */     this.lights.setElementAt(paramLight, paramInt);
/*  874 */     this.lightsChanged = true;
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
/*      */   public void insertLight(Light paramLight, int paramInt) {
/*  886 */     if (paramLight == null) {
/*  887 */       throw new NullPointerException(J3dI18N.getString("GraphicsContext3D13"));
/*      */     }
/*  889 */     if (paramLight.isLive()) {
/*  890 */       throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D14"));
/*      */     }
/*  892 */     this.uLights.insertElementAt(paramLight, paramInt);
/*  893 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/*  897 */       doInsertLight(paramLight, paramInt);
/*  898 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/*  900 */       sendRenderMessage(false, 8, paramLight, new Integer(paramInt));
/*      */     } else {
/*      */       
/*  903 */       sendRenderMessage(true, 8, paramLight, new Integer(paramInt));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doInsertLight(Light paramLight, int paramInt) {
/*  909 */     ((LightRetained)paramLight.retained).setInImmCtx(true);
/*  910 */     updateLightState((LightRetained)paramLight.retained);
/*  911 */     this.lights.insertElementAt(paramLight, paramInt);
/*  912 */     this.lightsChanged = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeLight(int paramInt) {
/*  920 */     this.uLights.removeElementAt(paramInt);
/*  921 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/*  925 */       doRemoveLight(paramInt);
/*  926 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/*  928 */       sendRenderMessage(false, 9, new Integer(paramInt), null);
/*      */     } else {
/*      */       
/*  931 */       sendRenderMessage(true, 9, new Integer(paramInt), null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doRemoveLight(int paramInt) {
/*  937 */     Light light = (Light)this.lights.elementAt(paramInt);
/*      */     
/*  939 */     ((LightRetained)light.retained).setInImmCtx(false);
/*  940 */     this.lights.removeElementAt(paramInt);
/*  941 */     this.lightsChanged = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  950 */   public Light getLight(int paramInt) { return (Light)this.uLights.elementAt(paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  958 */   public Enumeration getAllLights() { return this.uLights.elements(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addLight(Light paramLight) {
/*  973 */     if (paramLight == null) {
/*  974 */       throw new NullPointerException(J3dI18N.getString("GraphicsContext3D13"));
/*      */     }
/*      */     
/*  977 */     if (paramLight.isLive()) {
/*  978 */       throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D14"));
/*      */     }
/*  980 */     this.uLights.addElement(paramLight);
/*  981 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/*  985 */       doAddLight(paramLight);
/*  986 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/*  988 */       sendRenderMessage(false, 10, paramLight, null);
/*      */     } else {
/*  990 */       sendRenderMessage(true, 10, paramLight, null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doAddLight(Light paramLight) {
/*  996 */     ((LightRetained)paramLight.retained).setInImmCtx(true);
/*  997 */     updateLightState((LightRetained)paramLight.retained);
/*  998 */     this.lights.addElement(paramLight);
/*  999 */     this.lightsChanged = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1007 */   public int numLights() { return this.uLights.size(); }
/*      */ 
/*      */ 
/*      */   
/*      */   private Transform3D getNormalTransform() {
/* 1012 */     if (this.compTransform.isRigid()) {
/* 1013 */       return this.compTransform;
/*      */     }
/* 1015 */     if (this.normalTransform == null) {
/* 1016 */       this.normalTransform = new Transform3D();
/*      */     }
/*      */     
/* 1019 */     if (this.normalTransformNeedToUpdate) {
/* 1020 */       this.normalTransform.invert(this.compTransform);
/* 1021 */       this.normalTransform.transpose();
/* 1022 */       this.normalTransformNeedToUpdate = false;
/*      */     } 
/* 1024 */     return this.normalTransform;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1030 */   void updateFogState(FogRetained paramFogRetained) { paramFogRetained.setLocalToVworldScale(this.modelTransform.getDistanceScale()); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateLightState(LightRetained paramLightRetained) {
/* 1036 */     if (paramLightRetained instanceof DirectionalLightRetained) {
/* 1037 */       DirectionalLightRetained directionalLightRetained = (DirectionalLightRetained)paramLightRetained;
/*      */       
/* 1039 */       Transform3D transform3D = getNormalTransform();
/* 1040 */       transform3D.transform(directionalLightRetained.direction, directionalLightRetained.xformDirection);
/* 1041 */       directionalLightRetained.xformDirection.normalize();
/*      */     }
/* 1043 */     else if (paramLightRetained instanceof SpotLightRetained) {
/* 1044 */       SpotLightRetained spotLightRetained = (SpotLightRetained)paramLightRetained;
/*      */       
/* 1046 */       Transform3D transform3D = getNormalTransform();
/* 1047 */       transform3D.transform(spotLightRetained.direction, spotLightRetained.xformDirection);
/* 1048 */       spotLightRetained.xformDirection.normalize();
/* 1049 */       this.modelTransform.transform(spotLightRetained.position, spotLightRetained.xformPosition);
/*      */     }
/* 1051 */     else if (paramLightRetained instanceof PointLightRetained) {
/* 1052 */       PointLightRetained pointLightRetained = (PointLightRetained)paramLightRetained;
/*      */       
/* 1054 */       this.modelTransform.transform(pointLightRetained.position, pointLightRetained.xformPosition);
/*      */       
/* 1056 */       pointLightRetained.localToVworldScale = this.modelTransform.getDistanceScale();
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
/*      */   public void setHiRes(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3) {
/* 1072 */     HiResCoord hiResCoord = new HiResCoord(paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3);
/* 1073 */     setHiRes(hiResCoord);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHiRes(HiResCoord paramHiResCoord) {
/* 1082 */     this.uHiRes.setHiResCoord(paramHiResCoord);
/* 1083 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/* 1087 */       doSetHiRes(paramHiResCoord);
/* 1088 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/* 1090 */       sendRenderMessage(false, 11, paramHiResCoord, null);
/*      */     } else {
/* 1092 */       sendRenderMessage(true, 11, paramHiResCoord, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   void doSetHiRes(HiResCoord paramHiResCoord) {
/* 1097 */     this.hiRes.setHiResCoord(paramHiResCoord);
/* 1098 */     computeCompositeTransform();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1107 */   public void getHiRes(HiResCoord paramHiResCoord) { this.uHiRes.getHiResCoord(paramHiResCoord); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setModelTransform(Transform3D paramTransform3D) {
/* 1120 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/* 1124 */       doSetModelTransform(paramTransform3D);
/*      */     } else {
/*      */       
/* 1127 */       Transform3D transform3D = new Transform3D(paramTransform3D);
/*      */       
/* 1129 */       if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */         
/* 1131 */         sendRenderMessage(false, 12, transform3D, null);
/*      */       } else {
/*      */         
/* 1134 */         sendRenderMessage(true, 12, transform3D, null);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doSetModelTransform(Transform3D paramTransform3D) {
/* 1141 */     this.modelTransform.set(paramTransform3D);
/* 1142 */     computeCompositeTransform();
/* 1143 */     this.normalTransformNeedToUpdate = true;
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
/*      */   public void multiplyModelTransform(Transform3D paramTransform3D) {
/* 1155 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/* 1159 */       doMultiplyModelTransform(paramTransform3D);
/*      */     } else {
/* 1161 */       Transform3D transform3D = new Transform3D(paramTransform3D);
/* 1162 */       if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/* 1163 */         sendRenderMessage(false, 13, transform3D, null);
/*      */       } else {
/*      */         
/* 1166 */         sendRenderMessage(true, 13, transform3D, null);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doMultiplyModelTransform(Transform3D paramTransform3D) {
/* 1173 */     this.modelTransform.mul(paramTransform3D);
/* 1174 */     computeCompositeTransform();
/* 1175 */     this.normalTransformNeedToUpdate = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1184 */   public void getModelTransform(Transform3D paramTransform3D) { paramTransform3D.set(this.modelTransform); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSound(Sound paramSound, int paramInt) {
/* 1201 */     if (paramSound == null) {
/* 1202 */       throw new NullPointerException(J3dI18N.getString("GraphicsContext3D17"));
/*      */     }
/* 1204 */     if (paramSound.isLive()) {
/* 1205 */       throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D23"));
/*      */     }
/* 1207 */     this.uSounds.setElementAt(paramSound, paramInt);
/* 1208 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/* 1212 */       doSetSound(paramSound, paramInt);
/* 1213 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/* 1215 */       sendRenderMessage(false, 14, paramSound, new Integer(paramInt));
/*      */     } else {
/*      */       
/* 1218 */       sendRenderMessage(true, 14, paramSound, new Integer(paramInt));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void doSetSound(Sound paramSound, int paramInt) {
/* 1225 */     Sound sound = (Sound)this.sounds.elementAt(paramInt);
/* 1226 */     ((SoundRetained)paramSound.retained).setInImmCtx(true);
/* 1227 */     if (sound != null) {
/* 1228 */       ((SoundRetained)sound.retained).setInImmCtx(false);
/*      */     }
/* 1230 */     ((SoundRetained)paramSound.retained).setInImmCtx(true);
/* 1231 */     updateSoundState((SoundRetained)paramSound.retained);
/* 1232 */     this.sounds.setElementAt(paramSound, paramInt);
/* 1233 */     this.soundsChanged = true;
/*      */     
/* 1235 */     sendSoundMessage(14, paramSound, sound);
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
/*      */   public void insertSound(Sound paramSound, int paramInt) {
/* 1252 */     if (paramSound == null) {
/* 1253 */       throw new NullPointerException(J3dI18N.getString("GraphicsContext3D17"));
/*      */     }
/* 1255 */     if (paramSound.isLive()) {
/* 1256 */       throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D23"));
/*      */     }
/* 1258 */     this.uSounds.insertElementAt(paramSound, paramInt);
/* 1259 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/* 1263 */       doInsertSound(paramSound, paramInt);
/* 1264 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/* 1266 */       sendRenderMessage(false, 15, paramSound, new Integer(paramInt));
/*      */     } else {
/*      */       
/* 1269 */       sendRenderMessage(true, 15, paramSound, new Integer(paramInt));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doInsertSound(Sound paramSound, int paramInt) {
/* 1275 */     updateSoundState((SoundRetained)paramSound.retained);
/* 1276 */     this.sounds.insertElementAt(paramSound, paramInt);
/* 1277 */     this.soundsChanged = true;
/* 1278 */     sendSoundMessage(15, paramSound, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeSound(int paramInt) {
/* 1286 */     this.uSounds.removeElementAt(paramInt);
/* 1287 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/* 1291 */       doRemoveSound(paramInt);
/* 1292 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/* 1294 */       sendRenderMessage(false, 16, new Integer(paramInt), null);
/*      */     } else {
/*      */       
/* 1297 */       sendRenderMessage(true, 16, new Integer(paramInt), null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doRemoveSound(int paramInt) {
/* 1303 */     Sound sound = (Sound)this.sounds.elementAt(paramInt);
/* 1304 */     SoundScheduler soundScheduler = getSoundScheduler();
/* 1305 */     ((SoundRetained)sound.retained).setInImmCtx(false);
/* 1306 */     this.sounds.removeElementAt(paramInt);
/* 1307 */     this.soundsChanged = true;
/*      */     
/* 1309 */     sendSoundMessage(16, null, sound);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1318 */   public Sound getSound(int paramInt) { return (Sound)this.uSounds.elementAt(paramInt); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1327 */   public Enumeration getAllSounds() { return this.uSounds.elements(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSound(Sound paramSound) {
/* 1343 */     if (paramSound == null) {
/* 1344 */       throw new NullPointerException(J3dI18N.getString("GraphicsContext3D17"));
/*      */     }
/* 1346 */     if (paramSound.isLive()) {
/* 1347 */       throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D23"));
/*      */     }
/*      */     
/* 1350 */     this.uSounds.addElement(paramSound);
/* 1351 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/* 1355 */       doAddSound(paramSound);
/* 1356 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/* 1358 */       sendRenderMessage(false, 17, paramSound, null);
/*      */     } else {
/* 1360 */       sendRenderMessage(true, 17, paramSound, null);
/*      */     } 
/*      */   }
/*      */   
/*      */   void doAddSound(Sound paramSound) {
/* 1365 */     ((SoundRetained)paramSound.retained).setInImmCtx(true);
/* 1366 */     updateSoundState((SoundRetained)paramSound.retained);
/* 1367 */     this.sounds.addElement(paramSound);
/* 1368 */     this.soundsChanged = true;
/* 1369 */     sendSoundMessage(17, paramSound, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1377 */   public int numSounds() { return this.uSounds.size(); }
/*      */ 
/*      */   
/*      */   SoundScheduler getSoundScheduler() {
/* 1381 */     if (this.canvas3d != null && this.canvas3d.view != null) {
/* 1382 */       return this.canvas3d.view.soundScheduler;
/*      */     }
/* 1384 */     return (SoundScheduler)null;
/*      */   }
/*      */   
/*      */   void updateSoundState(SoundRetained paramSoundRetained) {
/* 1388 */     View view = null;
/* 1389 */     if (this.canvas3d != null) {
/* 1390 */       view = this.canvas3d.view;
/*      */     }
/*      */ 
/*      */     
/* 1394 */     if (view != null) {
/* 1395 */       SoundScheduler soundScheduler = getSoundScheduler();
/* 1396 */       if (soundScheduler == null);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1403 */     if (paramSoundRetained instanceof ConeSoundRetained) {
/* 1404 */       ConeSoundRetained coneSoundRetained = (ConeSoundRetained)paramSoundRetained;
/* 1405 */       this.modelTransform.transform(coneSoundRetained.direction, coneSoundRetained.xformDirection);
/* 1406 */       coneSoundRetained.xformDirection.normalize();
/* 1407 */       this.modelTransform.transform(coneSoundRetained.position, coneSoundRetained.xformPosition);
/*      */       
/* 1409 */       coneSoundRetained.trans.setWithLock(this.drawTransform);
/*      */     }
/* 1411 */     else if (paramSoundRetained instanceof PointSoundRetained) {
/* 1412 */       PointSoundRetained pointSoundRetained = (PointSoundRetained)paramSoundRetained;
/* 1413 */       this.modelTransform.transform(pointSoundRetained.position, pointSoundRetained.xformPosition);
/*      */       
/* 1415 */       pointSoundRetained.trans.setWithLock(this.drawTransform);
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
/*      */   public boolean isSoundPlaying(int paramInt) {
/* 1427 */     Sound sound = (Sound)this.sounds.elementAt(paramInt);
/* 1428 */     return sound.isPlaying();
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
/*      */   public void setAuralAttributes(AuralAttributes paramAuralAttributes) {
/* 1440 */     this.uAuralAttributes = paramAuralAttributes;
/*      */     
/* 1442 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/* 1446 */       doSetAuralAttributes(paramAuralAttributes);
/* 1447 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/* 1449 */       sendRenderMessage(false, 18, paramAuralAttributes, null);
/*      */     } else {
/*      */       
/* 1452 */       sendRenderMessage(true, 18, paramAuralAttributes, null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doSetAuralAttributes(AuralAttributes paramAuralAttributes) {
/* 1458 */     this.auralAttributes = paramAuralAttributes;
/* 1459 */     sendSoundMessage(18, paramAuralAttributes, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1466 */   public AuralAttributes getAuralAttributes() { return this.uAuralAttributes; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBufferOverride(boolean paramBoolean) {
/* 1485 */     this.uBufferOverride = paramBoolean;
/* 1486 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/* 1490 */       doSetBufferOverride(paramBoolean);
/* 1491 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/* 1493 */       sendRenderMessage(false, 19, new Boolean(paramBoolean), null);
/*      */     } else {
/*      */       
/* 1496 */       sendRenderMessage(true, 19, new Boolean(paramBoolean), null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doSetBufferOverride(boolean paramBoolean) {
/* 1502 */     if (paramBoolean != this.bufferOverride) {
/* 1503 */       this.bufferOverride = paramBoolean;
/* 1504 */       this.dirtyMask |= 0x1;
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
/* 1517 */   public boolean getBufferOverride() { return this.uBufferOverride; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFrontBufferRendering(boolean paramBoolean) {
/* 1537 */     this.uFrontBufferRendering = paramBoolean;
/* 1538 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/* 1542 */       doSetFrontBufferRendering(paramBoolean);
/* 1543 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/* 1545 */       sendRenderMessage(false, 20, new Boolean(paramBoolean), null);
/*      */     } else {
/*      */       
/* 1548 */       sendRenderMessage(true, 20, new Boolean(paramBoolean), null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doSetFrontBufferRendering(boolean paramBoolean) {
/* 1554 */     if (paramBoolean != this.frontBufferRendering) {
/* 1555 */       this.frontBufferRendering = paramBoolean;
/* 1556 */       this.dirtyMask |= 0x1;
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
/* 1569 */   public boolean getFrontBufferRendering() { return this.uFrontBufferRendering; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStereoMode(int paramInt) {
/* 1604 */     this.uStereoMode = paramInt;
/* 1605 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/* 1609 */       doSetStereoMode(paramInt);
/* 1610 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/* 1612 */       sendRenderMessage(false, 21, stereoModes[paramInt], null);
/*      */     } else {
/*      */       
/* 1615 */       sendRenderMessage(true, 21, stereoModes[paramInt], null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doSetStereoMode(int paramInt) {
/* 1621 */     if (paramInt != this.stereoMode) {
/* 1622 */       this.stereoMode = paramInt;
/* 1623 */       this.dirtyMask |= 0x1;
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
/* 1636 */   public int getStereoMode() { return this.uStereoMode; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/* 1649 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active) {
/*      */       return;
/*      */     }
/* 1652 */     if (Thread.currentThread() == this.canvas3d.screen.renderer) {
/* 1653 */       doClear();
/* 1654 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/* 1656 */       sendRenderMessage(false, 0, null, null);
/*      */     } else {
/* 1658 */       sendRenderMessage(true, 0, null, null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void doClear() {
/* 1664 */     if (!this.canvas3d.firstPaintCalled) {
/*      */       return;
/*      */     }
/* 1667 */     RenderBin renderBin = this.canvas3d.view.renderBin;
/* 1668 */     BackgroundRetained backgroundRetained = null;
/*      */ 
/*      */     
/* 1671 */     if (this.background != null) {
/* 1672 */       backgroundRetained = (BackgroundRetained)this.background.retained;
/*      */     } else {
/* 1674 */       backgroundRetained = this.black;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1684 */     if (!this.canvas3d.isRunning) {
/* 1685 */       updateViewCache(renderBin);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1695 */       if (!this.canvas3d.offScreen) {
/* 1696 */         this.canvas3d.drawingSurfaceObject.getDrawingSurfaceObjectInfo();
/*      */       }
/*      */       
/* 1699 */       if (this.canvas3d.drawingSurfaceObject.renderLock()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1717 */         if (this.canvas3d.ctx == null) {
/* 1718 */           synchronized (VirtualUniverse.mc.contextCreationLock) {
/* 1719 */             this.canvas3d.ctx = this.canvas3d.createNewContext(null, false);
/* 1720 */             if (this.canvas3d.ctx == null) {
/* 1721 */               this.canvas3d.drawingSurfaceObject.unLock();
/*      */               
/*      */               return;
/*      */             } 
/* 1725 */             this.canvas3d.ctxTimeStamp = VirtualUniverse.mc.getContextTimeStamp();
/*      */             
/* 1727 */             this.canvas3d.screen.renderer.listOfCtxs.add(this.canvas3d.ctx);
/* 1728 */             this.canvas3d.screen.renderer.listOfCanvases.add(this.canvas3d);
/*      */             
/* 1730 */             this.canvas3d.beginScene();
/*      */             
/* 1732 */             if (this.canvas3d.graphics2D != null) {
/* 1733 */               this.canvas3d.graphics2D.init();
/*      */             }
/*      */ 
/*      */             
/* 1737 */             this.canvas3d.enableSeparateSpecularColor();
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1742 */           if (this.canvas3d.texUnitState == null) {
/* 1743 */             this.canvas3d.createTexUnitState();
/*      */           }
/*      */           
/* 1746 */           this.canvas3d.drawingSurfaceObject.contextValidated();
/* 1747 */           this.canvas3d.screen.renderer.currentCtx = this.canvas3d.ctx;
/* 1748 */           this.canvas3d.screen.renderer.currentDrawable = this.canvas3d.drawable;
/* 1749 */           initializeState();
/* 1750 */           this.canvas3d.ctxChanged = true;
/* 1751 */           this.canvas3d.canvasDirty = 65535;
/*      */           
/* 1753 */           updateState(renderBin, 4);
/*      */           
/* 1755 */           this.canvas3d.currentLights = new LightRetained[this.canvas3d.getNumCtxLights(this.canvas3d.ctx)];
/*      */ 
/*      */           
/* 1758 */           for (byte b = 0; b < this.canvas3d.currentLights.length; b++) {
/* 1759 */             this.canvas3d.currentLights[b] = null;
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1764 */         this.canvas3d.makeCtxCurrent();
/*      */         
/* 1766 */         if ((this.dirtyMask & true) != 0) {
/* 1767 */           if (this.bufferOverride) {
/* 1768 */             this.canvas3d.setRenderMode(this.canvas3d.ctx, this.stereoMode, (this.canvas3d.useDoubleBuffer && !this.frontBufferRendering));
/*      */           
/*      */           }
/* 1771 */           else if (!this.canvas3d.isRunning) {
/* 1772 */             this.canvas3d.setRenderMode(this.canvas3d.ctx, 2, this.canvas3d.useDoubleBuffer);
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1777 */           this.dirtyMask &= 0xFFFFFFFE;
/*      */         } 
/*      */         
/* 1780 */         Dimension dimension = this.canvas3d.getSize();
/* 1781 */         int i = dimension.width;
/* 1782 */         int j = dimension.height;
/* 1783 */         boolean bool = false;
/* 1784 */         if (backgroundRetained.image != null) {
/* 1785 */           if (backgroundRetained.image.isByReference()) {
/* 1786 */             backgroundRetained.image.geomLock.getLock();
/* 1787 */             bool = true;
/*      */           } 
/*      */           
/* 1790 */           backgroundRetained.image.evaluateExtensions(this.canvas3d);
/*      */         } 
/*      */         
/* 1793 */         this.canvas3d.clear(backgroundRetained, i, j);
/*      */         
/* 1795 */         if (bool) {
/* 1796 */           backgroundRetained.image.geomLock.unLock();
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1801 */         if (!this.canvas3d.isRunning) {
/* 1802 */           CanvasViewCache canvasViewCache = this.canvas3d.canvasViewCache;
/* 1803 */           this.canvas3d.setViewport(this.canvas3d.ctx, 0, 0, canvasViewCache.getCanvasWidth(), canvasViewCache.getCanvasHeight());
/*      */ 
/*      */ 
/*      */           
/* 1807 */           if (this.bufferOverride && this.stereoMode == 1) {
/* 1808 */             this.canvas3d.setProjectionMatrix(this.canvas3d.ctx, canvasViewCache.getRightProjection());
/*      */             
/* 1810 */             this.canvas3d.setModelViewMatrix(this.canvas3d.ctx, (canvasViewCache.getRightVpcToEc()).mat, renderBin.vworldToVpc);
/*      */           }
/*      */           else {
/*      */             
/* 1814 */             this.canvas3d.setProjectionMatrix(this.canvas3d.ctx, canvasViewCache.getLeftProjection());
/*      */             
/* 1816 */             this.canvas3d.setModelViewMatrix(this.canvas3d.ctx, (canvasViewCache.getLeftVpcToEc()).mat, renderBin.vworldToVpc);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1822 */         this.canvas3d.drawingSurfaceObject.unLock();
/*      */       } 
/* 1824 */     } catch (NullPointerException nullPointerException) {
/* 1825 */       this.canvas3d.drawingSurfaceObject.unLock();
/* 1826 */       throw nullPointerException;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeCompositeTransform() {
/*      */     ViewPlatform viewPlatform;
/* 1834 */     if (this.canvas3d == null || this.canvas3d.view == null || (viewPlatform = this.canvas3d.view.getViewPlatform()) == null || (ViewPlatformRetained)viewPlatform.retained == null) {
/*      */ 
/*      */ 
/*      */       
/* 1838 */       this.compTransform.set(this.modelTransform);
/*      */       
/*      */       return;
/*      */     } 
/* 1842 */     ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)viewPlatform.retained;
/* 1843 */     if (viewPlatformRetained == null || viewPlatformRetained.locale == null) {
/* 1844 */       this.compTransform.set(this.modelTransform);
/*      */       
/*      */       return;
/*      */     } 
/* 1848 */     HiResCoord hiResCoord = viewPlatformRetained.locale.hiRes;
/*      */     
/* 1850 */     if (hiResCoord.equals(this.hiRes)) {
/* 1851 */       this.compTransform.set(this.modelTransform);
/*      */     } else {
/* 1853 */       Transform3D transform3D = new Transform3D();
/* 1854 */       Vector3d vector3d = new Vector3d();
/* 1855 */       hiResCoord.difference(this.hiRes, vector3d);
/* 1856 */       transform3D.setTranslation(vector3d);
/* 1857 */       this.compTransform.mul(transform3D, this.modelTransform);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateViewCache(RenderBin paramRenderBin) {
/* 1864 */     ViewPlatform viewPlatform = this.canvas3d.view.getViewPlatform();
/*      */     
/* 1866 */     if (viewPlatform == null) {
/*      */       return;
/*      */     }
/* 1869 */     ViewPlatformRetained viewPlatformRetained = (ViewPlatformRetained)viewPlatform.retained;
/*      */     
/* 1871 */     if (!this.canvas3d.isRunning)
/*      */     {
/* 1873 */       viewPlatformRetained.evaluateInitViewPlatformTransform();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1881 */     paramRenderBin.vpcToVworld = viewPlatformRetained.getVpcToVworld();
/* 1882 */     paramRenderBin.vworldToVpc = viewPlatformRetained.getVworldToVpc();
/*      */     
/* 1884 */     this.canvas3d.updateViewCache(true, null, null, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void doDraw(Geometry paramGeometry) {
/* 1891 */     GeometryArrayRetained geometryArrayRetained = null;
/*      */ 
/*      */     
/* 1894 */     if (!this.canvas3d.firstPaintCalled || !this.visible) {
/*      */       return;
/*      */     }
/*      */     
/* 1898 */     RenderBin renderBin = this.canvas3d.view.renderBin;
/*      */ 
/*      */     
/* 1901 */     boolean bool = true;
/*      */     
/* 1903 */     if (this.canvas3d.ctx == null)
/*      */     {
/* 1905 */       doClear();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1916 */       if (this.canvas3d.drawingSurfaceObject.renderLock()) {
/*      */         GeometryRetained geometryRetained;
/*      */         
/* 1919 */         this.canvas3d.makeCtxCurrent();
/*      */         
/* 1921 */         if ((this.dirtyMask & true) != 0) {
/* 1922 */           if (this.bufferOverride) {
/* 1923 */             this.canvas3d.setRenderMode(this.canvas3d.ctx, this.stereoMode, (this.canvas3d.useDoubleBuffer && !this.frontBufferRendering));
/*      */           } else {
/*      */             
/* 1926 */             this.canvas3d.setRenderMode(this.canvas3d.ctx, 2, this.canvas3d.useDoubleBuffer);
/*      */           } 
/*      */           
/* 1929 */           this.dirtyMask &= 0xFFFFFFFE;
/*      */         } 
/*      */         
/* 1932 */         CanvasViewCache canvasViewCache = this.canvas3d.canvasViewCache;
/*      */ 
/*      */ 
/*      */         
/* 1936 */         if (this.bufferOverride) {
/* 1937 */           switch (this.stereoMode) {
/*      */             case 1:
/* 1939 */               this.vpcToEc = canvasViewCache.getRightVpcToEc();
/*      */ 
/*      */ 
/*      */               
/* 1943 */               this.canvas3d.setProjectionMatrix(this.canvas3d.ctx, canvasViewCache.getRightProjection());
/*      */               break;
/*      */ 
/*      */ 
/*      */             
/*      */             default:
/* 1949 */               this.vpcToEc = canvasViewCache.getLeftVpcToEc();
/*      */ 
/*      */ 
/*      */               
/* 1953 */               this.canvas3d.setProjectionMatrix(this.canvas3d.ctx, canvasViewCache.getLeftProjection());
/*      */               break;
/*      */           } 
/*      */         
/* 1957 */         } else if (!this.canvas3d.isRunning || this.canvas3d.vpcToEc == null) {
/*      */ 
/*      */ 
/*      */           
/* 1961 */           this.vpcToEc = canvasViewCache.getLeftVpcToEc();
/*      */         } else {
/*      */           
/* 1964 */           this.vpcToEc = this.canvas3d.vpcToEc;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1975 */         boolean bool2 = !this.drawTransform.isCongruent();
/*      */         
/* 1977 */         byte b = 0;
/* 1978 */         switch (((GeometryRetained)paramGeometry.retained).geoType) {
/*      */           case 3:
/*      */           case 10:
/* 1981 */             b = 1;
/*      */             break;
/*      */           case 4:
/*      */           case 7:
/*      */           case 11:
/*      */           case 14:
/* 1987 */             b = 2;
/*      */             break;
/*      */           case 15:
/* 1990 */             b = 8;
/*      */             break;
/*      */           case 17:
/* 1993 */             b = 16;
/*      */             
/* 1995 */             switch (((CompressedGeometryRetained)paramGeometry.retained).getBufferType()) {
/*      */               case 0:
/* 1997 */                 b |= 0x1;
/*      */                 break;
/*      */               case 1:
/* 2000 */                 b |= 0x2;
/*      */                 break;
/*      */             } 
/*      */             
/* 2004 */             b |= 0x4;
/*      */             break;
/*      */ 
/*      */           
/*      */           default:
/* 2009 */             b = 4;
/*      */             break;
/*      */         } 
/*      */         
/* 2013 */         boolean bool1 = updateState(renderBin, b);
/*      */         
/* 2015 */         this.canvas3d.setModelViewMatrix(this.canvas3d.ctx, this.vpcToEc.mat, renderBin.vworldToVpc);
/*      */ 
/*      */         
/* 2018 */         updateLightAndFog();
/*      */         
/* 2020 */         updateModelClip(renderBin.vworldToVpc);
/*      */         
/* 2022 */         this.drawTransform.mul(renderBin.vworldToVpc, this.compTransform);
/* 2023 */         this.canvas3d.setModelViewMatrix(this.canvas3d.ctx, this.vpcToEc.mat, this.drawTransform);
/*      */ 
/*      */         
/* 2026 */         if (paramGeometry.retained instanceof GeometryArrayRetained) {
/* 2027 */           geometryArrayRetained = (GeometryArrayRetained)paramGeometry.retained;
/*      */           
/* 2029 */           geometryArrayRetained.geomLock.getLock();
/*      */ 
/*      */           
/* 2032 */           if ((geometryArrayRetained.vertexFormat & 0x80) != 0 && geometryArrayRetained.c4fAllocated == 0 && (geometryArrayRetained.vertexFormat & 0x4) != 0 && bool1 && (this.canvas3d.extensionsSupported & true) == 0)
/*      */           {
/*      */ 
/*      */ 
/*      */             
/* 2037 */             if ((geometryArrayRetained.vertexFormat & 0x100) != 0) {
/* 2038 */               geometryArrayRetained.setupMirrorInterleavedColorPointer(true);
/*      */             } else {
/*      */               
/* 2041 */               geometryArrayRetained.setupMirrorColorPointer(geometryArrayRetained.vertexType & 0x3F0, true);
/*      */             } 
/*      */           }
/*      */           
/* 2045 */           if (paramGeometry.retained instanceof IndexedGeometryArrayRetained && (((GeometryArrayRetained)paramGeometry.retained).vertexFormat & 0x200) == 0) {
/*      */             
/* 2047 */             if (geometryArrayRetained.dirtyFlag != 0) {
/* 2048 */               geometryArrayRetained.mirrorGeometry = ((IndexedGeometryArrayRetained)geometryArrayRetained).cloneNonIndexedGeometry();
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2053 */               geometryArrayRetained.dirtyFlag = 0;
/*      */             } 
/* 2055 */             geometryRetained = geometryArrayRetained.mirrorGeometry;
/*      */           } else {
/* 2057 */             geometryRetained = geometryArrayRetained;
/*      */           } 
/*      */           
/* 2060 */           geometryArrayRetained.setVertexFormat(false, this.ignoreVertexColors, this.canvas3d.ctx);
/*      */         }
/* 2062 */         else if (paramGeometry.retained instanceof Text3DRetained) {
/* 2063 */           ((Text3DRetained)paramGeometry.retained).setModelViewMatrix(this.vpcToEc, this.drawTransform);
/*      */           
/* 2065 */           geometryRetained = (GeometryRetained)paramGeometry.retained;
/* 2066 */         } else if (paramGeometry.retained instanceof RasterRetained) {
/* 2067 */           ImageComponent2DRetained imageComponent2DRetained = ((RasterRetained)paramGeometry.retained).image;
/* 2068 */           if (imageComponent2DRetained != null) {
/* 2069 */             if (imageComponent2DRetained.isByReference()) {
/* 2070 */               imageComponent2DRetained.geomLock.getLock();
/* 2071 */               imageComponent2DRetained.evaluateExtensions(this.canvas3d);
/* 2072 */               imageComponent2DRetained.geomLock.unLock();
/*      */             } else {
/* 2074 */               imageComponent2DRetained.evaluateExtensions(this.canvas3d);
/*      */             } 
/*      */           }
/* 2077 */           geometryRetained = (GeometryRetained)paramGeometry.retained;
/*      */         } else {
/* 2079 */           geometryRetained = (GeometryRetained)paramGeometry.retained;
/*      */         } 
/*      */         
/* 2082 */         geometryRetained.execute(this.canvas3d, null, bool2, false, this.alpha, this.canvas3d.screen.screen, this.ignoreVertexColors);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2087 */         if (geometryArrayRetained != null) {
/* 2088 */           if (geometryArrayRetained.pVertexBuffers != 0L)
/*      */           {
/*      */             
/* 2091 */             geometryArrayRetained.freeD3DArray(true);
/*      */           }
/*      */           
/* 2094 */           geometryArrayRetained.geomLock.unLock();
/*      */         } 
/*      */         
/* 2097 */         this.canvas3d.drawingSurfaceObject.unLock();
/*      */       } 
/* 2099 */     } catch (NullPointerException nullPointerException) {
/* 2100 */       this.canvas3d.drawingSurfaceObject.unLock();
/* 2101 */       throw nullPointerException;
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
/*      */   public void draw(Geometry paramGeometry) {
/* 2115 */     if (paramGeometry != null && paramGeometry instanceof Raster) {
/* 2116 */       RasterRetained rasterRetained = (RasterRetained)paramGeometry.retained;
/* 2117 */       ImageComponent2D imageComponent2D = rasterRetained.getImage();
/* 2118 */       if (imageComponent2D != null) {
/* 2119 */         ImageComponentRetained imageComponentRetained = (ImageComponentRetained)imageComponent2D.retained;
/*      */         
/* 2121 */         if (imageComponentRetained.getUsedByOffScreen()) {
/* 2122 */           throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D32"));
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 2127 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active) {
/*      */       return;
/*      */     }
/* 2130 */     if (Thread.currentThread() == this.canvas3d.screen.renderer) {
/* 2131 */       doDraw(paramGeometry);
/*      */     }
/* 2133 */     else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/* 2135 */       sendRenderMessage(false, 1, paramGeometry, null);
/*      */     } else {
/*      */       
/* 2138 */       sendRenderMessage(true, 1, paramGeometry, null);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void draw(Shape3D paramShape3D) {
/* 2162 */     if (paramShape3D.isLive()) {
/* 2163 */       throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D26"));
/*      */     }
/* 2165 */     ((Shape3DRetained)paramShape3D.retained).setInImmCtx(true);
/* 2166 */     setAppearance(paramShape3D.getAppearance());
/* 2167 */     draw(paramShape3D.getGeometry());
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
/*      */   public void readRaster(Raster paramRaster) {
/* 2209 */     if (paramRaster != null && paramRaster.isLive()) {
/* 2210 */       ImageComponent2D imageComponent2D = paramRaster.getImage();
/* 2211 */       if (imageComponent2D != null) {
/* 2212 */         ImageComponent2DRetained imageComponent2DRetained = (ImageComponent2DRetained)imageComponent2D.retained;
/* 2213 */         if (imageComponent2D.getImageClass() != ImageComponent.ImageClass.BUFFERED_IMAGE) {
/* 2214 */           throw new IllegalArgumentException(J3dI18N.getString("GraphicsContext3D33"));
/*      */         }
/* 2216 */         if (imageComponent2D.isByReference() && imageComponent2D.getImage() == null) {
/* 2217 */           throw new IllegalArgumentException(J3dI18N.getString("GraphicsContext3D34"));
/*      */         }
/* 2219 */         if (imageComponent2DRetained.getNumberOfComponents() < 3) {
/* 2220 */           throw new IllegalArgumentException(J3dI18N.getString("GraphicsContext3D35"));
/*      */         }
/* 2222 */         if (imageComponent2D.isLive()) {
/* 2223 */           throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D36"));
/*      */         }
/* 2225 */         if (imageComponent2DRetained.getInImmCtx() || imageComponent2DRetained.getUsedByOffScreen()) {
/* 2226 */           throw new IllegalSharingException(J3dI18N.getString("GraphicsContext3D37"));
/*      */         }
/*      */       } 
/*      */     } 
/* 2230 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active) {
/*      */       return;
/*      */     }
/* 2233 */     if (Thread.currentThread() == this.canvas3d.screen.renderer) {
/* 2234 */       doReadRaster(paramRaster);
/* 2235 */     } else if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */       
/* 2237 */       this.readRasterReady = false;
/* 2238 */       sendRenderMessage(false, 3, paramRaster, null);
/* 2239 */       while (!this.readRasterReady) {
/* 2240 */         MasterControl.threadYield();
/*      */       }
/*      */     } else {
/*      */       
/* 2244 */       this.readRasterReady = false;
/* 2245 */       sendRenderMessage(true, 3, paramRaster, null);
/* 2246 */       while (!this.readRasterReady) {
/* 2247 */         MasterControl.threadYield();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   void doReadRaster(Raster paramRaster) {
/* 2253 */     if (!this.canvas3d.firstPaintCalled) {
/* 2254 */       this.readRasterReady = true;
/*      */       
/*      */       return;
/*      */     } 
/* 2258 */     RasterRetained rasterRetained = (RasterRetained)paramRaster.retained;
/* 2259 */     Dimension dimension1 = this.canvas3d.getSize();
/* 2260 */     Dimension dimension2 = new Dimension();
/* 2261 */     ImageComponent2DRetained imageComponent2DRetained = rasterRetained.image;
/*      */     
/* 2263 */     boolean bool = false;
/*      */     
/* 2265 */     if (this.canvas3d.ctx == null)
/*      */     {
/* 2267 */       doClear();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2274 */     rasterRetained.getSize(dimension2);
/*      */     
/* 2276 */     if ((rasterRetained.type & true) != 0 && (
/* 2277 */       dimension2.width > rasterRetained.image.width || dimension2.height > rasterRetained.image.height))
/*      */     {
/* 2279 */       throw new RuntimeException(J3dI18N.getString("GraphicsContext3D27"));
/*      */     }
/*      */ 
/*      */     
/* 2283 */     if ((rasterRetained.type & 0x2) != 0) {
/* 2284 */       int i = rasterRetained.depthComponent.height * rasterRetained.depthComponent.width;
/* 2285 */       if (rasterRetained.depthComponent.type == 2) {
/*      */         
/* 2287 */         if (this.floatBuffer.length < i) {
/* 2288 */           this.floatBuffer = new float[i];
/*      */         }
/* 2290 */       } else if (this.intBuffer.length < i) {
/* 2291 */         this.intBuffer = new int[i];
/*      */       } 
/* 2293 */       if (dimension2.width > rasterRetained.depthComponent.width || dimension2.height > rasterRetained.depthComponent.height)
/*      */       {
/* 2295 */         throw new RuntimeException(J3dI18N.getString("GraphicsContext3D28"));
/*      */       }
/*      */     } 
/*      */     
/* 2299 */     if ((rasterRetained.type & true) != 0)
/*      */     {
/*      */ 
/*      */       
/* 2303 */       if (imageComponent2DRetained.isByReference()) {
/* 2304 */         imageComponent2DRetained.geomLock.getLock();
/* 2305 */         imageComponent2DRetained.evaluateExtensions(this.canvas3d);
/* 2306 */         imageComponent2DRetained.geomLock.unLock();
/*      */       
/*      */       }
/* 2309 */       else if (imageComponent2DRetained.imageData == null) {
/* 2310 */         imageComponent2DRetained.createBlankImageData();
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 2315 */         imageComponent2DRetained.evaluateExtensions(this.canvas3d);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 2323 */       if (this.canvas3d.drawingSurfaceObject.renderLock()) {
/*      */         
/* 2325 */         this.canvas3d.makeCtxCurrent();
/* 2326 */         this.canvas3d.syncRender(this.canvas3d.ctx, true);
/* 2327 */         Point point = new Point();
/* 2328 */         rasterRetained.getDstOffset(point);
/*      */         
/* 2330 */         DepthComponentRetained depthComponentRetained = rasterRetained.depthComponent;
/* 2331 */         int i = 0;
/* 2332 */         if (depthComponentRetained != null) {
/* 2333 */           i = depthComponentRetained.type;
/*      */         }
/* 2335 */         Pipeline.getPipeline().readRaster(this.canvas3d.ctx, rasterRetained.type, point.x, point.y, dimension2.width, dimension2.height, dimension1.height, imageComponent2DRetained.getImageDataTypeIntValue(), imageComponent2DRetained.getImageFormatTypeIntValue(false), imageComponent2DRetained.imageData.get(), i, depthComponentRetained);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2343 */         this.canvas3d.drawingSurfaceObject.unLock();
/*      */       } 
/* 2345 */     } catch (NullPointerException nullPointerException) {
/* 2346 */       this.canvas3d.drawingSurfaceObject.unLock();
/* 2347 */       throw nullPointerException;
/*      */     } 
/*      */     
/* 2350 */     if ((rasterRetained.type & 0x2) != 0) {
/* 2351 */       if (rasterRetained.depthComponent.type == 2) {
/*      */         
/* 2353 */         ((DepthComponentFloatRetained)rasterRetained.depthComponent).retrieveDepth(this.floatBuffer, dimension2.width, dimension2.height);
/*      */       }
/* 2355 */       else if (rasterRetained.depthComponent.type == 1) {
/*      */         
/* 2357 */         ((DepthComponentIntRetained)rasterRetained.depthComponent).retrieveDepth(this.intBuffer, dimension2.width, dimension2.height);
/*      */       }
/* 2359 */       else if (rasterRetained.depthComponent.type == 1) {
/*      */         
/* 2361 */         ((DepthComponentNativeRetained)rasterRetained.depthComponent).retrieveDepth(this.intBuffer, dimension2.width, dimension2.height);
/*      */       } 
/*      */     }
/* 2364 */     this.readRasterReady = true;
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
/*      */   public void flush(boolean paramBoolean) {
/* 2377 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null || !this.canvas3d.view.active || Thread.currentThread() == this.canvas3d.screen.renderer) {
/*      */ 
/*      */ 
/*      */       
/* 2381 */       doFlush(paramBoolean);
/*      */     } else {
/* 2383 */       Boolean bool = paramBoolean ? Boolean.TRUE : Boolean.FALSE;
/*      */       
/* 2385 */       if (Thread.currentThread() == this.canvas3d.view.universe.behaviorScheduler) {
/*      */         
/* 2387 */         sendRenderMessage(false, 22, bool, null);
/*      */       } else {
/*      */         
/* 2390 */         sendRenderMessage(true, 22, bool, null);
/*      */       } 
/*      */ 
/*      */       
/* 2394 */       if (paramBoolean && this.canvas3d.active && this.canvas3d.isRunningStatus && !this.canvas3d.manualRendering)
/*      */       {
/*      */         
/* 2397 */         runMonitor(0);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   void doFlush(boolean paramBoolean) {
/*      */     try {
/* 2404 */       if (this.canvas3d.drawingSurfaceObject.renderLock()) {
/* 2405 */         this.canvas3d.syncRender(this.canvas3d.ctx, paramBoolean);
/* 2406 */         this.canvas3d.drawingSurfaceObject.unLock();
/* 2407 */         if (paramBoolean) {
/* 2408 */           runMonitor(2);
/*      */         }
/*      */       } 
/* 2411 */     } catch (NullPointerException nullPointerException) {
/* 2412 */       this.canvas3d.drawingSurfaceObject.unLock();
/* 2413 */       throw nullPointerException;
/*      */     } 
/*      */   }
/*      */   
/*      */   void updateLightAndFog() {
/* 2418 */     byte b1 = 0;
/*      */     
/* 2420 */     this.sceneAmbient.x = 0.0F;
/* 2421 */     this.sceneAmbient.y = 0.0F;
/* 2422 */     this.sceneAmbient.z = 0.0F;
/*      */     
/* 2424 */     byte b3 = 0;
/* 2425 */     int i = this.lights.size();
/* 2426 */     for (byte b2 = 0; b2 < i; b2++) {
/* 2427 */       LightRetained lightRetained = (LightRetained)((Light)this.lights.get(b2)).retained;
/* 2428 */       if (lightRetained instanceof AmbientLightRetained) {
/* 2429 */         this.sceneAmbient.x += lightRetained.color.x;
/* 2430 */         this.sceneAmbient.y += lightRetained.color.y;
/* 2431 */         this.sceneAmbient.z += lightRetained.color.z;
/*      */       }
/*      */       else {
/*      */         
/* 2435 */         lightRetained.update(this.canvas3d.ctx, b3, this.canvas3d.canvasViewCache.getVworldToCoexistenceScale());
/*      */         
/* 2437 */         if (lightRetained.lightOn)
/* 2438 */           b1 |= 1 << b3; 
/* 2439 */         b3++;
/*      */       } 
/* 2441 */     }  if (this.sceneAmbient.x > 1.0F) {
/* 2442 */       this.sceneAmbient.x = 1.0F;
/*      */     }
/* 2444 */     if (this.sceneAmbient.y > 1.0F) {
/* 2445 */       this.sceneAmbient.y = 1.0F;
/*      */     }
/* 2447 */     if (this.sceneAmbient.z > 1.0F) {
/* 2448 */       this.sceneAmbient.z = 1.0F;
/*      */     }
/*      */     
/* 2451 */     this.canvas3d.setSceneAmbient(this.canvas3d.ctx, this.sceneAmbient.x, this.sceneAmbient.y, this.sceneAmbient.z);
/*      */ 
/*      */     
/* 2454 */     this.canvas3d.canvasDirty |= 0x100;
/* 2455 */     this.canvas3d.sceneAmbient.set(this.sceneAmbient);
/*      */     
/* 2457 */     if (this.canvas3d.enableMask != b1) {
/* 2458 */       this.canvas3d.canvasDirty |= 0x80;
/*      */       
/* 2460 */       this.canvas3d.setLightEnables(this.canvas3d.ctx, b1, 32);
/* 2461 */       this.canvas3d.enableMask = b1;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2466 */     this.canvas3d.lightBin = null;
/* 2467 */     this.canvas3d.environmentSet = null;
/*      */     
/* 2469 */     if (this.fog != null) {
/* 2470 */       if (this.fog.retained != this.canvas3d.fog) {
/* 2471 */         ((FogRetained)this.fog.retained).update(this.canvas3d.ctx, this.canvas3d.canvasViewCache.getVworldToCoexistenceScale());
/*      */         
/* 2473 */         this.canvas3d.fog = (FogRetained)this.fog.retained;
/* 2474 */         this.canvas3d.canvasDirty |= 0x2000;
/*      */       }
/*      */     
/* 2477 */     } else if (this.canvas3d.fog != null) {
/* 2478 */       this.canvas3d.setFogEnableFlag(this.canvas3d.ctx, false);
/* 2479 */       this.canvas3d.fog = null;
/* 2480 */       this.canvas3d.canvasDirty |= 0x2000;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void updateModelClip(Transform3D paramTransform3D) {
/* 2486 */     if (this.modelClip != null) {
/* 2487 */       byte b1 = 0;
/* 2488 */       for (byte b2 = 0; b2 < 6; b2++) {
/* 2489 */         if (((ModelClipRetained)this.modelClip.retained).enables[b2]) {
/* 2490 */           b1 |= true << b2;
/*      */         }
/*      */       } 
/*      */       
/* 2494 */       if (b1) {
/* 2495 */         this.drawTransform.mul(paramTransform3D, this.modelClipTransform);
/* 2496 */         this.canvas3d.setModelViewMatrix(this.canvas3d.ctx, this.vpcToEc.mat, this.drawTransform);
/*      */       } 
/*      */       
/* 2499 */       ((ModelClipRetained)this.modelClip.retained).update(this.canvas3d.ctx, b1, this.drawTransform);
/*      */ 
/*      */       
/* 2502 */       this.canvas3d.canvasDirty |= 0x4000;
/* 2503 */       this.canvas3d.modelClip = (ModelClipRetained)this.modelClip.retained;
/*      */     }
/* 2505 */     else if (this.canvas3d.modelClip != null) {
/* 2506 */       this.canvas3d.disableModelClip(this.canvas3d.ctx);
/* 2507 */       this.canvas3d.modelClip = null;
/* 2508 */       this.canvas3d.canvasDirty |= 0x4000;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2513 */     this.canvas3d.environmentSet = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean updateState(RenderBin paramRenderBin, int paramInt) {
/* 2521 */     boolean bool = false;
/* 2522 */     this.numActiveTexUnit = 0;
/* 2523 */     this.lastActiveTexUnitIndex = 0;
/*      */ 
/*      */     
/* 2526 */     if (this.appearance != null) {
/* 2527 */       AppearanceRetained appearanceRetained = (AppearanceRetained)this.appearance.retained;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2538 */       if (appearanceRetained.material != null) {
/* 2539 */         appearanceRetained.material.updateNative(this.canvas3d.ctx, this.red, this.green, this.blue, this.alpha, this.enableLighting);
/*      */ 
/*      */         
/* 2542 */         this.canvas3d.material = appearanceRetained.material;
/* 2543 */         this.canvas3d.canvasDirty |= 0x8;
/*      */       }
/* 2545 */       else if (this.canvas3d.material != null) {
/* 2546 */         this.canvas3d.updateMaterial(this.canvas3d.ctx, this.red, this.green, this.blue, this.alpha);
/*      */         
/* 2548 */         this.canvas3d.material = null;
/* 2549 */         this.canvas3d.canvasDirty |= 0x8;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2554 */       boolean bool1 = false;
/* 2555 */       if (appearanceRetained instanceof ShaderAppearanceRetained) {
/* 2556 */         ShaderProgramRetained shaderProgramRetained = ((ShaderAppearanceRetained)appearanceRetained).shaderProgram;
/* 2557 */         if (shaderProgramRetained != null) {
/* 2558 */           shaderProgramRetained.updateNative(this.canvas3d, true);
/*      */           
/* 2560 */           ShaderAttributeSetRetained shaderAttributeSetRetained = ((ShaderAppearanceRetained)appearanceRetained).shaderAttributeSet;
/*      */ 
/*      */           
/* 2563 */           if (shaderAttributeSetRetained != null) {
/* 2564 */             shaderAttributeSetRetained.updateNative(this.canvas3d, shaderProgramRetained);
/*      */           }
/*      */           
/* 2567 */           this.canvas3d.shaderProgram = shaderProgramRetained;
/* 2568 */           bool1 = true;
/*      */         }
/*      */       
/* 2571 */       } else if (this.canvas3d.shaderProgram != null) {
/* 2572 */         this.canvas3d.shaderProgram.updateNative(this.canvas3d, false);
/* 2573 */         this.canvas3d.shaderProgram = null;
/* 2574 */         bool1 = false;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2579 */       int i = bool1 ? this.canvas3d.maxTextureImageUnits : this.canvas3d.maxTextureUnits;
/*      */ 
/*      */       
/* 2582 */       int j = this.canvas3d.getNumActiveTexUnit();
/*      */ 
/*      */ 
/*      */       
/* 2586 */       if (appearanceRetained.texUnitState != null) {
/*      */         int k;
/*      */         
/* 2589 */         for (k = 0; k < appearanceRetained.texUnitState.length; k++) {
/* 2590 */           TextureUnitStateRetained textureUnitStateRetained = appearanceRetained.texUnitState[k];
/* 2591 */           if (textureUnitStateRetained != null && textureUnitStateRetained.isTextureEnabled()) {
/* 2592 */             this.lastActiveTexUnitIndex = k;
/* 2593 */             this.numActiveTexUnit = k + true;
/* 2594 */             if (textureUnitStateRetained.texAttrs != null) {
/* 2595 */               bool = (bool || textureUnitStateRetained.texAttrs.textureMode == 4);
/*      */             }
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2602 */         if (this.numActiveTexUnit <= i) {
/*      */ 
/*      */ 
/*      */           
/* 2606 */           for (k = 0; k < appearanceRetained.texUnitState.length && 
/* 2607 */             k < i; k++) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2613 */             if (appearanceRetained.texUnitState[k] != null && appearanceRetained.texUnitState[k].isTextureEnabled()) {
/*      */               
/* 2615 */               appearanceRetained.texUnitState[k].updateNative(k, this.canvas3d, false, false);
/*      */             } else {
/*      */               
/* 2618 */               this.canvas3d.resetTexture(this.canvas3d.ctx, k);
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 2623 */           for (k = appearanceRetained.texUnitState.length; k < j; k++) {
/* 2624 */             this.canvas3d.resetTexture(this.canvas3d.ctx, k);
/*      */           }
/*      */ 
/*      */           
/* 2628 */           this.canvas3d.setNumActiveTexUnit(this.numActiveTexUnit);
/*      */         }
/*      */         else {
/*      */           
/* 2632 */           for (k = 0; k < j; k++) {
/* 2633 */             this.canvas3d.resetTexture(this.canvas3d.ctx, k);
/*      */           }
/* 2635 */           this.canvas3d.setNumActiveTexUnit(0);
/*      */         } 
/*      */ 
/*      */         
/* 2639 */         this.canvas3d.activeTextureUnit(this.canvas3d.ctx, 0);
/*      */       }
/*      */       else {
/*      */         
/* 2643 */         if (this.canvas3d.multiTexAccelerated) {
/* 2644 */           if (this.canvas3d.texUnitState != null) {
/* 2645 */             for (byte b = 0; b < j; b++) {
/* 2646 */               TextureUnitStateRetained textureUnitStateRetained = this.canvas3d.texUnitState[b];
/* 2647 */               if (textureUnitStateRetained != null && textureUnitStateRetained.texture != null) {
/* 2648 */                 this.canvas3d.resetTexture(this.canvas3d.ctx, b);
/* 2649 */                 (this.canvas3d.texUnitState[b]).texture = null;
/*      */               } 
/*      */             } 
/*      */           }
/*      */ 
/*      */           
/* 2655 */           this.canvas3d.activeTextureUnit(this.canvas3d.ctx, 0);
/*      */         } 
/*      */         
/* 2658 */         if (this.canvas3d.texUnitState != null && this.canvas3d.texUnitState[false] != null && (this.canvas3d.texUnitState[false]).texture != appearanceRetained.texture) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2664 */           if (appearanceRetained.texture != null) {
/* 2665 */             appearanceRetained.texture.updateNative(this.canvas3d);
/* 2666 */             this.canvas3d.canvasDirty |= 0xC00;
/* 2667 */             this.numActiveTexUnit = 1;
/* 2668 */             this.lastActiveTexUnitIndex = 0;
/*      */           } else {
/*      */             
/* 2671 */             this.numActiveTexUnit = 0;
/* 2672 */             this.canvas3d.resetTexture(this.canvas3d.ctx, -1);
/* 2673 */             this.canvas3d.canvasDirty |= 0xC00;
/*      */           } 
/*      */           
/* 2676 */           (this.canvas3d.texUnitState[0]).texture = appearanceRetained.texture;
/*      */         } 
/*      */ 
/*      */         
/* 2680 */         this.canvas3d.setNumActiveTexUnit(this.numActiveTexUnit);
/*      */         
/* 2682 */         if (appearanceRetained.texCoordGeneration != null) {
/* 2683 */           appearanceRetained.texCoordGeneration.updateNative(this.canvas3d);
/* 2684 */           this.canvas3d.canvasDirty |= 0xC00;
/* 2685 */           if (this.canvas3d.texUnitState != null && this.canvas3d.texUnitState[false] != null)
/*      */           {
/* 2687 */             (this.canvas3d.texUnitState[0]).texGen = appearanceRetained.texCoordGeneration;
/*      */ 
/*      */           
/*      */           }
/*      */         
/*      */         }
/* 2693 */         else if (this.canvas3d.texUnitState != null && this.canvas3d.texUnitState[false] != null && (this.canvas3d.texUnitState[false]).texGen != null) {
/*      */ 
/*      */           
/* 2696 */           this.canvas3d.resetTexCoordGeneration(this.canvas3d.ctx);
/* 2697 */           this.canvas3d.canvasDirty |= 0xC00;
/* 2698 */           (this.canvas3d.texUnitState[0]).texGen = appearanceRetained.texCoordGeneration;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2703 */         if (appearanceRetained.textureAttributes != null) {
/* 2704 */           if (this.canvas3d.texUnitState != null && this.canvas3d.texUnitState[false] != null)
/*      */           {
/*      */             
/* 2707 */             if ((this.canvas3d.texUnitState[false]).texture != null) {
/* 2708 */               appearanceRetained.textureAttributes.updateNative(this.canvas3d, false, (this.canvas3d.texUnitState[0]).texture.format);
/*      */             } else {
/*      */               
/* 2711 */               appearanceRetained.textureAttributes.updateNative(this.canvas3d, false, 6);
/*      */             } 
/*      */             
/* 2714 */             this.canvas3d.canvasDirty |= 0xC00;
/* 2715 */             (this.canvas3d.texUnitState[0]).texAttrs = appearanceRetained.textureAttributes;
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/* 2722 */         else if (this.canvas3d.texUnitState != null && this.canvas3d.texUnitState[false] != null && (this.canvas3d.texUnitState[false]).texAttrs != null) {
/*      */ 
/*      */           
/* 2725 */           this.canvas3d.resetTextureAttributes(this.canvas3d.ctx);
/* 2726 */           this.canvas3d.canvasDirty |= 0xC00;
/* 2727 */           (this.canvas3d.texUnitState[0]).texAttrs = null;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2732 */       if (appearanceRetained.coloringAttributes != null) {
/* 2733 */         appearanceRetained.coloringAttributes.updateNative(this.canvas3d.ctx, this.dRed, this.dBlue, this.dGreen, this.alpha, this.enableLighting);
/*      */ 
/*      */ 
/*      */         
/* 2737 */         this.canvas3d.canvasDirty |= 0x20;
/* 2738 */         this.canvas3d.coloringAttributes = appearanceRetained.coloringAttributes;
/*      */       
/*      */       }
/* 2741 */       else if (this.canvas3d.coloringAttributes != null) {
/* 2742 */         this.canvas3d.resetColoringAttributes(this.canvas3d.ctx, this.red, this.green, this.blue, this.alpha, this.enableLighting);
/*      */ 
/*      */         
/* 2745 */         this.canvas3d.canvasDirty |= 0x20;
/* 2746 */         this.canvas3d.coloringAttributes = null;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2751 */       if (appearanceRetained.transparencyAttributes != null) {
/* 2752 */         appearanceRetained.transparencyAttributes.updateNative(this.canvas3d.ctx, this.alpha, paramInt, this.polygonMode, this.lineAA, this.pointAA);
/*      */ 
/*      */ 
/*      */         
/* 2756 */         this.canvas3d.canvasDirty |= 0x10;
/* 2757 */         this.canvas3d.transparency = appearanceRetained.transparencyAttributes;
/*      */         
/* 2759 */         bool = (bool || (appearanceRetained.transparencyAttributes.transparencyMode != 4 && (VirtualUniverse.mc.isD3D() || (!VirtualUniverse.mc.isD3D() && appearanceRetained.transparencyAttributes.transparencyMode != 3))));
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */         
/* 2768 */         this.canvas3d.resetTransparency(this.canvas3d.ctx, paramInt, this.polygonMode, this.lineAA, this.pointAA);
/*      */         
/* 2770 */         this.canvas3d.canvasDirty |= 0x10;
/* 2771 */         this.canvas3d.transparency = null;
/*      */       } 
/*      */ 
/*      */       
/* 2775 */       if (appearanceRetained.renderingAttributes != null) {
/* 2776 */         this.ignoreVertexColors = appearanceRetained.renderingAttributes.ignoreVertexColors;
/* 2777 */         appearanceRetained.renderingAttributes.updateNative(this.canvas3d, this.canvas3d.depthBufferWriteEnableOverride, this.canvas3d.depthBufferEnableOverride);
/*      */ 
/*      */         
/* 2780 */         this.canvas3d.canvasDirty |= 0xA00;
/* 2781 */         this.canvas3d.renderingAttrs = appearanceRetained.renderingAttributes;
/*      */         
/* 2783 */         bool = (bool || appearanceRetained.renderingAttributes.alphaTestFunction != 0);
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 2789 */         this.ignoreVertexColors = false;
/* 2790 */         if (this.canvas3d.renderingAttrs != null) {
/* 2791 */           this.canvas3d.resetRenderingAttributes(this.canvas3d.ctx, this.canvas3d.depthBufferWriteEnableOverride, this.canvas3d.depthBufferEnableOverride);
/*      */ 
/*      */           
/* 2794 */           this.canvas3d.canvasDirty |= 0xA00;
/* 2795 */           this.canvas3d.renderingAttrs = null;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2800 */       if (appearanceRetained.polygonAttributes != null) {
/* 2801 */         appearanceRetained.polygonAttributes.updateNative(this.canvas3d.ctx);
/* 2802 */         this.canvas3d.canvasDirty |= 0x1;
/* 2803 */         this.canvas3d.polygonAttributes = appearanceRetained.polygonAttributes;
/*      */ 
/*      */       
/*      */       }
/* 2807 */       else if (this.canvas3d.polygonAttributes != null) {
/* 2808 */         this.canvas3d.resetPolygonAttributes(this.canvas3d.ctx);
/* 2809 */         this.canvas3d.canvasDirty |= 0x1;
/* 2810 */         this.canvas3d.polygonAttributes = null;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2816 */       if (appearanceRetained.lineAttributes != null) {
/* 2817 */         appearanceRetained.lineAttributes.updateNative(this.canvas3d.ctx);
/* 2818 */         this.canvas3d.canvasDirty |= 0x2;
/* 2819 */         this.canvas3d.lineAttributes = appearanceRetained.lineAttributes;
/*      */ 
/*      */       
/*      */       }
/* 2823 */       else if (this.canvas3d.lineAttributes != null) {
/* 2824 */         this.canvas3d.resetLineAttributes(this.canvas3d.ctx);
/* 2825 */         this.canvas3d.canvasDirty |= 0x2;
/* 2826 */         this.canvas3d.lineAttributes = null;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2832 */       if (appearanceRetained.pointAttributes != null) {
/* 2833 */         appearanceRetained.pointAttributes.updateNative(this.canvas3d.ctx);
/* 2834 */         this.canvas3d.canvasDirty |= 0x4;
/* 2835 */         this.canvas3d.pointAttributes = appearanceRetained.pointAttributes;
/*      */ 
/*      */       
/*      */       }
/* 2839 */       else if (this.canvas3d.pointAttributes != null) {
/* 2840 */         this.canvas3d.resetPointAttributes(this.canvas3d.ctx);
/* 2841 */         this.canvas3d.canvasDirty |= 0x4;
/* 2842 */         this.canvas3d.pointAttributes = null;
/*      */       } 
/*      */ 
/*      */       
/* 2846 */       this.canvas3d.appearance = appearanceRetained;
/*      */     
/*      */     }
/* 2849 */     else if (this.canvas3d.appearance != null) {
/* 2850 */       resetAppearance();
/* 2851 */       this.canvas3d.appearance = null;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2856 */     return bool;
/*      */   }
/*      */ 
/*      */   
/*      */   void initializeState() {
/* 2861 */     this.canvas3d.setSceneAmbient(this.canvas3d.ctx, 0.0F, 0.0F, 0.0F);
/* 2862 */     this.canvas3d.disableFog(this.canvas3d.ctx);
/* 2863 */     this.canvas3d.resetRenderingAttributes(this.canvas3d.ctx, false, false);
/*      */     
/* 2865 */     if (this.canvas3d.shaderProgram != null) {
/* 2866 */       this.canvas3d.shaderProgram.updateNative(this.canvas3d, false);
/* 2867 */       this.canvas3d.shaderProgram = null;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2872 */     int i = this.canvas3d.getNumActiveTexUnit();
/*      */     
/* 2874 */     if (i > 0) {
/* 2875 */       for (byte b = 0; b < i; b++) {
/* 2876 */         if ((this.canvas3d.texUnitState[b]).texture != null) {
/* 2877 */           this.canvas3d.resetTexture(this.canvas3d.ctx, b);
/* 2878 */           (this.canvas3d.texUnitState[b]).texture = null;
/*      */         } 
/* 2880 */         if ((this.canvas3d.texUnitState[b]).texAttrs != null) {
/* 2881 */           this.canvas3d.resetTextureAttributes(this.canvas3d.ctx);
/* 2882 */           (this.canvas3d.texUnitState[b]).texAttrs = null;
/*      */         } 
/* 2884 */         if ((this.canvas3d.texUnitState[b]).texGen != null) {
/* 2885 */           this.canvas3d.resetTexCoordGeneration(this.canvas3d.ctx);
/* 2886 */           (this.canvas3d.texUnitState[b]).texGen = null;
/*      */         } 
/* 2888 */         (this.canvas3d.texUnitState[b]).mirror = null;
/*      */       } 
/* 2890 */       this.canvas3d.setNumActiveTexUnit(0);
/*      */     } 
/*      */     
/* 2893 */     this.canvas3d.resetPolygonAttributes(this.canvas3d.ctx);
/* 2894 */     this.canvas3d.resetLineAttributes(this.canvas3d.ctx);
/* 2895 */     this.canvas3d.resetPointAttributes(this.canvas3d.ctx);
/* 2896 */     this.canvas3d.resetTransparency(this.canvas3d.ctx, 4, 2, false, false);
/*      */ 
/*      */     
/* 2899 */     this.canvas3d.resetColoringAttributes(this.canvas3d.ctx, 1.0F, 1.0F, 1.0F, 1.0F, false);
/* 2900 */     this.canvas3d.updateMaterial(this.canvas3d.ctx, 1.0F, 1.0F, 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void resetAppearance() {
/* 2907 */     if (this.canvas3d.material != null) {
/* 2908 */       this.canvas3d.updateMaterial(this.canvas3d.ctx, this.red, this.green, this.blue, this.alpha);
/*      */       
/* 2910 */       this.canvas3d.material = null;
/* 2911 */       this.canvas3d.canvasDirty |= 0x8;
/*      */     } 
/*      */     
/* 2914 */     if (this.canvas3d.shaderProgram != null) {
/* 2915 */       this.canvas3d.shaderProgram.updateNative(this.canvas3d, false);
/* 2916 */       this.canvas3d.shaderProgram = null;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2921 */     int i = this.canvas3d.getNumActiveTexUnit();
/*      */     
/* 2923 */     if (i > 0) {
/* 2924 */       for (byte b = 0; b < i; b++) {
/* 2925 */         if ((this.canvas3d.texUnitState[b]).texture != null) {
/* 2926 */           this.canvas3d.resetTexture(this.canvas3d.ctx, b);
/* 2927 */           (this.canvas3d.texUnitState[b]).texture = null;
/*      */         } 
/* 2929 */         if ((this.canvas3d.texUnitState[b]).texAttrs != null) {
/* 2930 */           this.canvas3d.resetTextureAttributes(this.canvas3d.ctx);
/* 2931 */           (this.canvas3d.texUnitState[b]).texAttrs = null;
/*      */         } 
/* 2933 */         if ((this.canvas3d.texUnitState[b]).texGen != null) {
/* 2934 */           this.canvas3d.resetTexCoordGeneration(this.canvas3d.ctx);
/* 2935 */           (this.canvas3d.texUnitState[b]).texGen = null;
/*      */         } 
/* 2937 */         (this.canvas3d.texUnitState[b]).mirror = null;
/*      */       } 
/* 2939 */       this.canvas3d.canvasDirty |= 0xC00;
/* 2940 */       this.canvas3d.setNumActiveTexUnit(0);
/*      */     } 
/*      */     
/* 2943 */     if (this.canvas3d.coloringAttributes != null) {
/* 2944 */       this.canvas3d.resetColoringAttributes(this.canvas3d.ctx, this.red, this.green, this.blue, this.alpha, this.enableLighting);
/*      */       
/* 2946 */       this.canvas3d.coloringAttributes = null;
/* 2947 */       this.canvas3d.canvasDirty |= 0x20;
/*      */     } 
/*      */     
/* 2950 */     if (this.canvas3d.transparency != null) {
/* 2951 */       this.canvas3d.resetTransparency(this.canvas3d.ctx, 4, 2, this.lineAA, this.pointAA);
/*      */       
/* 2953 */       this.canvas3d.transparency = null;
/* 2954 */       this.canvas3d.canvasDirty |= 0x10;
/*      */     } 
/*      */     
/* 2957 */     if (this.canvas3d.renderingAttrs != null) {
/* 2958 */       this.ignoreVertexColors = false;
/* 2959 */       this.canvas3d.resetRenderingAttributes(this.canvas3d.ctx, this.canvas3d.depthBufferWriteEnableOverride, this.canvas3d.depthBufferEnableOverride);
/*      */ 
/*      */       
/* 2962 */       this.canvas3d.renderingAttrs = null;
/* 2963 */       this.canvas3d.canvasDirty |= 0xA00;
/*      */     } 
/*      */     
/* 2966 */     if (this.canvas3d.polygonAttributes != null) {
/* 2967 */       this.canvas3d.resetPolygonAttributes(this.canvas3d.ctx);
/* 2968 */       this.canvas3d.polygonAttributes = null;
/* 2969 */       this.canvas3d.canvasDirty |= 0x1;
/*      */     } 
/*      */     
/* 2972 */     if (this.canvas3d.lineAttributes != null) {
/* 2973 */       this.canvas3d.resetLineAttributes(this.canvas3d.ctx);
/* 2974 */       this.canvas3d.lineAttributes = null;
/* 2975 */       this.canvas3d.canvasDirty |= 0x2;
/*      */     } 
/*      */     
/* 2978 */     if (this.canvas3d.pointAttributes != null) {
/* 2979 */       this.canvas3d.resetPointAttributes(this.canvas3d.ctx);
/* 2980 */       this.canvas3d.pointAttributes = null;
/* 2981 */       this.canvas3d.canvasDirty |= 0x4;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void sendRenderMessage(boolean paramBoolean, int paramInt, Object paramObject1, Object paramObject2) {
/* 2990 */     J3dMessage j3dMessage = new J3dMessage();
/* 2991 */     j3dMessage.threads = 16;
/* 2992 */     j3dMessage.type = 44;
/* 2993 */     j3dMessage.universe = null;
/* 2994 */     j3dMessage.view = null;
/* 2995 */     j3dMessage.args[0] = this.canvas3d;
/* 2996 */     j3dMessage.args[1] = getImmCommand(paramInt);
/* 2997 */     j3dMessage.args[2] = paramObject1;
/* 2998 */     j3dMessage.args[3] = paramObject2;
/*      */     
/* 3000 */     while (!this.canvas3d.view.inRenderThreadData)
/*      */     {
/*      */       
/* 3003 */       MasterControl.threadYield();
/*      */     }
/*      */     
/* 3006 */     this.canvas3d.screen.renderer.rendererStructure.addMessage(j3dMessage);
/*      */     
/* 3008 */     if (paramBoolean) {
/*      */       
/* 3010 */       VirtualUniverse.mc.sendRunMessage(this.canvas3d.view, 16);
/*      */     } else {
/*      */       
/* 3013 */       VirtualUniverse.mc.setWorkForRequestRenderer();
/*      */     } 
/*      */   }
/*      */   
/*      */   void sendSoundMessage(int paramInt, Object paramObject1, Object paramObject2) {
/* 3018 */     if (this.canvas3d.view == null || this.canvas3d.view.universe == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 3023 */     J3dMessage j3dMessage = new J3dMessage();
/* 3024 */     j3dMessage.threads = 2;
/* 3025 */     j3dMessage.type = 44;
/* 3026 */     j3dMessage.universe = this.canvas3d.view.universe;
/* 3027 */     j3dMessage.view = this.canvas3d.view;
/* 3028 */     j3dMessage.args[0] = getImmCommand(paramInt);
/* 3029 */     j3dMessage.args[1] = paramObject1;
/* 3030 */     j3dMessage.args[2] = paramObject2;
/*      */     
/* 3032 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*      */   }
/*      */   
/*      */   static Integer getImmCommand(int paramInt) {
/* 3036 */     if (commands[paramInt] == null) {
/* 3037 */       commands[paramInt] = new Integer(paramInt);
/*      */     }
/* 3039 */     return commands[paramInt];
/*      */   }
/*      */   
/*      */   void runMonitor(int paramInt) {
/* 3043 */     if (paramInt == 0) {
/* 3044 */       while (!this.gcReady) {
/* 3045 */         this.waiting++;
/*      */         try {
/* 3047 */           wait();
/* 3048 */         } catch (InterruptedException interruptedException) {}
/* 3049 */         this.waiting--;
/*      */       } 
/* 3051 */       this.gcReady = false;
/*      */     } else {
/* 3053 */       this.gcReady = true;
/* 3054 */       if (this.waiting > 0)
/* 3055 */         notify(); 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\GraphicsContext3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */