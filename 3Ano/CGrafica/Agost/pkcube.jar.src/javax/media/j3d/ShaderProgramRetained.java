/*      */ package javax.media.j3d;
/*      */ 
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class ShaderProgramRetained
/*      */   extends NodeComponentRetained
/*      */ {
/*      */   protected ShaderProgramData[] shaderProgramData;
/*      */   private boolean unsupportedErrorReported = false;
/*      */   private boolean linkErrorOccurred = false;
/*      */   protected ShaderRetained[] shaders;
/*      */   protected String[] vertexAttrNames;
/*      */   protected String[] shaderAttrNames;
/*   53 */   private HashSet shaderAttrErrorSet = null;
/*      */ 
/*      */   
/*   56 */   Object resourceLock = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setVertexAttrNames(String[] paramArrayOfString) {
/*   77 */     if (paramArrayOfString == null) {
/*   78 */       this.vertexAttrNames = null;
/*      */     } else {
/*      */       
/*   81 */       this.vertexAttrNames = (String[])paramArrayOfString.clone();
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
/*      */   String[] getVertexAttrNames() {
/*   94 */     if (this.vertexAttrNames == null) {
/*   95 */       return null;
/*      */     }
/*      */     
/*   98 */     return (String[])this.vertexAttrNames.clone();
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
/*      */   void setShaderAttrNames(String[] paramArrayOfString) {
/*  116 */     if (paramArrayOfString == null) {
/*  117 */       this.shaderAttrNames = null;
/*      */     } else {
/*      */       
/*  120 */       this.shaderAttrNames = (String[])paramArrayOfString.clone();
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
/*      */   String[] getShaderAttrNames() {
/*  134 */     if (this.shaderAttrNames == null) {
/*  135 */       return null;
/*      */     }
/*      */     
/*  138 */     return (String[])this.shaderAttrNames.clone();
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
/*      */   void setShaders(Shader[] paramArrayOfShader) {
/*  164 */     if (paramArrayOfShader == null) {
/*  165 */       this.shaders = null;
/*      */       
/*      */       return;
/*      */     } 
/*  169 */     this.shaders = new ShaderRetained[paramArrayOfShader.length];
/*      */ 
/*      */     
/*  172 */     for (byte b = 0; b < paramArrayOfShader.length; b++) {
/*  173 */       this.shaders[b] = (ShaderRetained)(paramArrayOfShader[b]).retained;
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
/*      */   Shader[] getShaders() {
/*  188 */     if (this.shaders == null) {
/*  189 */       return null;
/*      */     }
/*  191 */     Shader[] arrayOfShader = new Shader[this.shaders.length];
/*      */     
/*  193 */     for (byte b = 0; b < this.shaders.length; b++) {
/*  194 */       if (this.shaders[b] != null) {
/*  195 */         arrayOfShader[b] = (Shader)(this.shaders[b]).source;
/*      */       } else {
/*  197 */         arrayOfShader[b] = null;
/*      */       } 
/*      */     } 
/*  200 */     return arrayOfShader;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError createShader(Context paramContext, ShaderRetained paramShaderRetained, ShaderId[] paramArrayOfShaderId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError destroyShader(Context paramContext, ShaderId paramShaderId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError compileShader(Context paramContext, ShaderId paramShaderId, String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError createShaderProgram(Context paramContext, ShaderProgramId[] paramArrayOfShaderProgramId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError destroyShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError linkShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderId[] paramArrayOfShaderId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError bindVertexAttrName(Context paramContext, ShaderProgramId paramShaderProgramId, String paramString, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void lookupShaderAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, String[] paramArrayOfString, AttrNameInfo[] paramArrayOfAttrNameInfo);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void lookupVertexAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, String[] paramArrayOfString, boolean[] paramArrayOfBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError enableShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError disableShaderProgram(Context paramContext);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform1i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform1f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform2i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform2f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform3i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform4i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniformMatrix3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniformMatrix4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform1iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform1fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform2iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform2fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform3iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform4iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniform4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniformMatrix3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract ShaderError setUniformMatrix4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract boolean isSupported(Canvas3D paramCanvas3D);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setLive(boolean paramBoolean, int paramInt) {
/*  387 */     if (this.shaders != null) {
/*  388 */       for (byte b = 0; b < this.shaders.length; b++) {
/*  389 */         this.shaders[b].setLive(paramBoolean, paramInt);
/*      */       }
/*      */     }
/*      */     
/*  393 */     doSetLive(paramBoolean, paramInt);
/*  394 */     markAsLive();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void clearLive(int paramInt) {
/*  402 */     super.clearLive(paramInt);
/*      */     
/*  404 */     if (this.shaders != null) {
/*  405 */       for (byte b = 0; b < this.shaders.length; b++) {
/*  406 */         this.shaders[b].clearLive(paramInt);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ShaderError enableShaderProgram(Canvas3D paramCanvas3D, int paramInt) {
/*  415 */     assert paramInt >= 0;
/*  416 */     synchronized (this.resourceLock) {
/*  417 */       return enableShaderProgram(paramCanvas3D.ctx, this.shaderProgramData[paramInt].getShaderProgramId());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  427 */   private ShaderError disableShaderProgram(Canvas3D paramCanvas3D) { return disableShaderProgram(paramCanvas3D.ctx); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initMirrorObject() {
/*  436 */     if (this.shaders == null) {
/*  437 */       ((ShaderProgramRetained)this.mirror).shaders = null;
/*      */     } else {
/*      */       
/*  440 */       ((ShaderProgramRetained)this.mirror).shaders = new ShaderRetained[this.shaders.length];
/*      */       
/*  442 */       for (byte b = 0; b < this.shaders.length; b++) {
/*  443 */         ((ShaderProgramRetained)this.mirror).shaders[b] = (ShaderRetained)(this.shaders[b]).mirror;
/*      */       }
/*      */     } 
/*      */     
/*  447 */     ((ShaderProgramRetained)this.mirror).shaderProgramData = null;
/*      */ 
/*      */     
/*  450 */     if (this.vertexAttrNames == null) {
/*  451 */       ((ShaderProgramRetained)this.mirror).vertexAttrNames = null;
/*      */     } else {
/*      */       
/*  454 */       ((ShaderProgramRetained)this.mirror).vertexAttrNames = (String[])this.vertexAttrNames.clone();
/*      */     } 
/*      */ 
/*      */     
/*  458 */     if (this.shaderAttrNames == null) {
/*  459 */       ((ShaderProgramRetained)this.mirror).shaderAttrNames = null;
/*      */     } else {
/*      */       
/*  462 */       ((ShaderProgramRetained)this.mirror).shaderAttrNames = (String[])this.shaderAttrNames.clone();
/*      */     } 
/*      */ 
/*      */     
/*  466 */     ((ShaderProgramRetained)this.mirror).shaderAttrErrorSet = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateMirrorObject(int paramInt, Object paramObject) {
/*      */     assert false;
/*  476 */     System.err.println("ShaderProgramRetained : updateMirrorObject NOT IMPLEMENTED YET");
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
/*      */   private void createShaderProgramData(int paramInt, long paramLong) {
/*  488 */     synchronized (this.resourceLock) {
/*  489 */       if (this.shaderProgramData == null) {
/*      */         
/*  491 */         this.shaderProgramData = new ShaderProgramData[paramInt + 1];
/*      */       }
/*  493 */       else if (this.shaderProgramData.length <= paramInt) {
/*      */         
/*  495 */         ShaderProgramData[] arrayOfShaderProgramData = new ShaderProgramData[paramInt + 1];
/*  496 */         System.arraycopy(this.shaderProgramData, 0, arrayOfShaderProgramData, 0, this.shaderProgramData.length);
/*      */ 
/*      */         
/*  499 */         this.shaderProgramData = arrayOfShaderProgramData;
/*      */       } 
/*      */       
/*  502 */       if (this.shaderProgramData[paramInt] == null) {
/*  503 */         this.shaderProgramData[paramInt] = new ShaderProgramData();
/*  504 */       } else if (this.shaderProgramData[paramInt].getCtxTimeStamp() != paramLong) {
/*      */ 
/*      */         
/*  507 */         this.shaderProgramData[paramInt].reset();
/*      */       } 
/*  509 */       this.shaderProgramData[paramInt].setCtxTimeStamp(paramLong);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ShaderError createShaderProgram(Canvas3D paramCanvas3D, int paramInt) {
/*  519 */     synchronized (this.resourceLock) {
/*  520 */       assert this.shaderProgramData[paramInt].getShaderProgramId() == null;
/*      */       
/*  522 */       ShaderProgramId[] arrayOfShaderProgramId = new ShaderProgramId[1];
/*  523 */       ShaderError shaderError = createShaderProgram(paramCanvas3D.ctx, arrayOfShaderProgramId);
/*  524 */       if (shaderError != null) {
/*  525 */         return shaderError;
/*      */       }
/*  527 */       this.shaderProgramData[paramInt].setShaderProgramId(arrayOfShaderProgramId[0]);
/*      */     } 
/*      */     
/*  530 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ShaderError linkShaderProgram(Canvas3D paramCanvas3D, int paramInt, ShaderRetained[] paramArrayOfShaderRetained) {
/*  538 */     synchronized (this.resourceLock) {
/*  539 */       ShaderId[] arrayOfShaderId = new ShaderId[paramArrayOfShaderRetained.length];
/*  540 */       for (byte b = 0; b < paramArrayOfShaderRetained.length; b++) {
/*  541 */         synchronized (paramArrayOfShaderRetained[b]) {
/*  542 */           arrayOfShaderId[b] = (paramArrayOfShaderRetained[b]).shaderData[paramInt].getShaderId();
/*      */         } 
/*      */       } 
/*  545 */       ShaderError shaderError = linkShaderProgram(paramCanvas3D.ctx, this.shaderProgramData[paramInt].getShaderProgramId(), arrayOfShaderId);
/*      */ 
/*      */ 
/*      */       
/*  549 */       if (shaderError != null) {
/*  550 */         return shaderError;
/*      */       }
/*  552 */       this.shaderProgramData[paramInt].setLinked(true);
/*      */     } 
/*      */     
/*  555 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private ShaderError bindVertexAttrName(Canvas3D paramCanvas3D, int paramInt1, String paramString, int paramInt2) {
/*  560 */     assert paramString != null;
/*  561 */     synchronized (this.resourceLock) {
/*  562 */       ShaderProgramId shaderProgramId = this.shaderProgramData[paramInt1].getShaderProgramId();
/*      */       
/*  564 */       ShaderError shaderError = bindVertexAttrName(paramCanvas3D.ctx, shaderProgramId, paramString, paramInt2);
/*  565 */       if (shaderError != null) {
/*  566 */         return shaderError;
/*      */       }
/*      */     } 
/*  569 */     return null;
/*      */   }
/*      */   
/*      */   private void lookupVertexAttrNames(Canvas3D paramCanvas3D, int paramInt, String[] paramArrayOfString) {
/*  573 */     synchronized (this.resourceLock) {
/*  574 */       ShaderProgramId shaderProgramId = this.shaderProgramData[paramInt].getShaderProgramId();
/*      */       
/*  576 */       boolean[] arrayOfBoolean = new boolean[paramArrayOfString.length];
/*  577 */       lookupVertexAttrNames(paramCanvas3D.ctx, shaderProgramId, paramArrayOfString, arrayOfBoolean);
/*      */       
/*  579 */       for (byte b = 0; b < paramArrayOfString.length; b++) {
/*      */         
/*  581 */         if (arrayOfBoolean[b]) {
/*  582 */           String str = "Vertex Attribute name lookup failed: " + paramArrayOfString[b];
/*  583 */           ShaderError shaderError = new ShaderError(3, str);
/*  584 */           shaderError.setShaderProgram((ShaderProgram)this.source);
/*  585 */           shaderError.setCanvas3D(paramCanvas3D);
/*  586 */           notifyErrorListeners(paramCanvas3D, shaderError);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void lookupShaderAttrNames(Canvas3D paramCanvas3D, int paramInt, String[] paramArrayOfString) {
/*  594 */     synchronized (this.resourceLock) {
/*  595 */       ShaderProgramId shaderProgramId = this.shaderProgramData[paramInt].getShaderProgramId();
/*      */       
/*  597 */       AttrNameInfo[] arrayOfAttrNameInfo = new AttrNameInfo[paramArrayOfString.length];
/*  598 */       lookupShaderAttrNames(paramCanvas3D.ctx, shaderProgramId, paramArrayOfString, arrayOfAttrNameInfo);
/*      */       
/*  600 */       for (byte b = 0; b < paramArrayOfString.length; b++) {
/*  601 */         this.shaderProgramData[paramInt].setAttrNameInfo(paramArrayOfString[b], arrayOfAttrNameInfo[b]);
/*      */ 
/*      */         
/*  604 */         if (arrayOfAttrNameInfo[b].getLocation() == null) {
/*  605 */           String str = "Attribute name lookup failed: " + paramArrayOfString[b];
/*  606 */           ShaderError shaderError = new ShaderError(4, str);
/*  607 */           shaderError.setShaderProgram((ShaderProgram)this.source);
/*  608 */           shaderError.setCanvas3D(paramCanvas3D);
/*  609 */           notifyErrorListeners(paramCanvas3D, shaderError);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ShaderProgramData getShaderProgramData(int paramInt) {
/*  620 */     synchronized (this.resourceLock) {
/*  621 */       return this.shaderProgramData[paramInt];
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ShaderError createShader(Canvas3D paramCanvas3D, int paramInt, ShaderRetained paramShaderRetained) {
/*  632 */     synchronized (paramShaderRetained.resourceLock) {
/*  633 */       if (paramShaderRetained.shaderData[paramInt].getShaderId() != null)
/*      */       {
/*  635 */         return null;
/*      */       }
/*      */       
/*  638 */       ShaderId[] arrayOfShaderId = new ShaderId[1];
/*  639 */       ShaderError shaderError = createShader(paramCanvas3D.ctx, paramShaderRetained, arrayOfShaderId);
/*  640 */       if (shaderError != null) {
/*  641 */         return shaderError;
/*      */       }
/*  643 */       paramShaderRetained.shaderData[paramInt].setShaderId(arrayOfShaderId[0]);
/*      */     } 
/*  645 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ShaderError compileShader(Canvas3D paramCanvas3D, int paramInt, ShaderRetained paramShaderRetained) {
/*  653 */     synchronized (paramShaderRetained.resourceLock) {
/*      */       
/*  655 */       if (paramShaderRetained.shaderData[paramInt].isCompiled())
/*      */       {
/*  657 */         return null;
/*      */       }
/*      */       
/*  660 */       String str = ((SourceCodeShaderRetained)paramShaderRetained).getShaderSource();
/*  661 */       ShaderError shaderError = compileShader(paramCanvas3D.ctx, paramShaderRetained.shaderData[paramInt].getShaderId(), str);
/*  662 */       if (shaderError != null) {
/*  663 */         return shaderError;
/*      */       }
/*  665 */       paramShaderRetained.shaderData[paramInt].setCompiled(true);
/*      */     } 
/*      */     
/*  668 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void notifyErrorListeners(Canvas3D paramCanvas3D, ShaderError paramShaderError) {
/*  676 */     J3dNotification j3dNotification = new J3dNotification();
/*  677 */     j3dNotification.type = 0;
/*  678 */     j3dNotification.universe = paramCanvas3D.view.universe;
/*  679 */     j3dNotification.args[0] = paramShaderError;
/*  680 */     VirtualUniverse.mc.sendNotification(j3dNotification);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean verifyShaderProgramSupported(Canvas3D paramCanvas3D) {
/*  691 */     boolean bool = isSupported(paramCanvas3D);
/*  692 */     if (!bool && !this.unsupportedErrorReported) {
/*  693 */       String str = J3dI18N.getString("ShaderProgramRetained0");
/*  694 */       ShaderError shaderError = new ShaderError(7, str);
/*  695 */       shaderError.setShaderProgram((ShaderProgram)this.source);
/*  696 */       shaderError.setCanvas3D(paramCanvas3D);
/*  697 */       notifyErrorListeners(paramCanvas3D, shaderError);
/*  698 */       this.unsupportedErrorReported = true;
/*      */     } 
/*  700 */     return bool;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void destroyShader(Canvas3D paramCanvas3D, int paramInt, ShaderRetained paramShaderRetained) {
/*  707 */     if (!verifyShaderProgramSupported(paramCanvas3D)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  712 */     synchronized (paramShaderRetained.resourceLock) {
/*      */       
/*  714 */       if (paramShaderRetained.shaderData == null || paramShaderRetained.shaderData.length <= paramInt || paramShaderRetained.shaderData[paramInt] == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  721 */       if (paramShaderRetained.shaderData[paramInt].getShaderId() == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  727 */       destroyShader(paramCanvas3D.ctx, paramShaderRetained.shaderData[paramInt].getShaderId());
/*  728 */       paramShaderRetained.shaderData[paramInt].reset();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void destroyShaderProgram(Canvas3D paramCanvas3D, int paramInt) {
/*  737 */     if (!verifyShaderProgramSupported(paramCanvas3D)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  742 */     synchronized (this.resourceLock) {
/*  743 */       assert this.shaderProgramData != null && this.shaderProgramData.length > paramInt && this.shaderProgramData[paramInt] != null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  754 */       ShaderProgramId shaderProgramId = this.shaderProgramData[paramInt].getShaderProgramId();
/*      */       
/*  756 */       if (shaderProgramId == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  763 */       destroyShaderProgram(paramCanvas3D.ctx, shaderProgramId);
/*      */       
/*  765 */       this.shaderProgramData[paramInt].reset();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateNative(Canvas3D paramCanvas3D, boolean paramBoolean) {
/*      */     long l;
/*      */     int i;
/*  777 */     boolean bool1 = (paramCanvas3D.useSharedCtx && paramCanvas3D.screen.renderer.sharedCtx != null) ? 1 : 0;
/*      */ 
/*      */ 
/*      */     
/*  781 */     if (bool1) {
/*  782 */       i = paramCanvas3D.screen.renderer.rendererId;
/*  783 */       l = paramCanvas3D.screen.renderer.sharedCtxTimeStamp;
/*      */     } else {
/*  785 */       i = paramCanvas3D.canvasId;
/*  786 */       l = paramCanvas3D.ctxTimeStamp;
/*      */     } 
/*      */ 
/*      */     
/*  790 */     createShaderProgramData(i, l);
/*      */ 
/*      */     
/*  793 */     if (!verifyShaderProgramSupported(paramCanvas3D)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  798 */     if (!paramBoolean) {
/*      */ 
/*      */       
/*  801 */       disableShaderProgram(paramCanvas3D);
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  807 */     if (this.shaders == null || this.shaders.length == 0 || this.linkErrorOccurred) {
/*  808 */       disableShaderProgram(paramCanvas3D);
/*      */       
/*      */       return;
/*      */     } 
/*  812 */     boolean bool2 = false;
/*  813 */     if (getShaderProgramData(i).getShaderProgramId() == null) {
/*  814 */       bool2 = true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  820 */     ShaderError shaderError = null;
/*  821 */     boolean bool3 = false;
/*  822 */     if (bool2) {
/*  823 */       if (bool1)
/*      */       {
/*  825 */         paramCanvas3D.makeCtxCurrent(paramCanvas3D.screen.renderer.sharedCtx);
/*      */       }
/*      */ 
/*      */       
/*  829 */       for (byte b = 0; b < this.shaders.length; b++) {
/*      */         
/*  831 */         this.shaders[b].createShaderData(i, l);
/*      */         
/*  833 */         if ((this.shaders[b]).compileErrorOccurred) {
/*  834 */           bool3 = true;
/*      */         } else {
/*      */           
/*  837 */           shaderError = createShader(paramCanvas3D, i, this.shaders[b]);
/*  838 */           if (shaderError != null) {
/*  839 */             shaderError.setShaderProgram((ShaderProgram)this.source);
/*  840 */             shaderError.setShader((Shader)(this.shaders[b]).source);
/*  841 */             shaderError.setCanvas3D(paramCanvas3D);
/*  842 */             notifyErrorListeners(paramCanvas3D, shaderError);
/*  843 */             bool3 = true;
/*      */           } else {
/*      */             
/*  846 */             shaderError = compileShader(paramCanvas3D, i, this.shaders[b]);
/*  847 */             if (shaderError != null) {
/*  848 */               shaderError.setShaderProgram((ShaderProgram)this.source);
/*  849 */               shaderError.setShader((Shader)(this.shaders[b]).source);
/*  850 */               shaderError.setCanvas3D(paramCanvas3D);
/*  851 */               notifyErrorListeners(paramCanvas3D, shaderError);
/*  852 */               destroyShader(paramCanvas3D, i, this.shaders[b]);
/*  853 */               (this.shaders[b]).compileErrorOccurred = true;
/*  854 */               bool3 = true;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  861 */       if (!bool3) {
/*  862 */         shaderError = createShaderProgram(paramCanvas3D, i);
/*  863 */         if (shaderError != null) {
/*  864 */           shaderError.setShaderProgram((ShaderProgram)this.source);
/*  865 */           shaderError.setCanvas3D(paramCanvas3D);
/*  866 */           notifyErrorListeners(paramCanvas3D, shaderError);
/*  867 */           bool3 = true;
/*      */         } 
/*      */       } 
/*      */       
/*  871 */       boolean bool = getShaderProgramData(i).isLinked();
/*  872 */       if (!bool) {
/*      */         
/*  874 */         if (!bool3 && 
/*  875 */           this.vertexAttrNames != null)
/*      */         {
/*  877 */           for (byte b1 = 0; b1 < this.vertexAttrNames.length; b1++) {
/*  878 */             shaderError = bindVertexAttrName(paramCanvas3D, i, this.vertexAttrNames[b1], b1);
/*      */             
/*  880 */             if (shaderError != null) {
/*  881 */               shaderError.setShaderProgram((ShaderProgram)this.source);
/*  882 */               shaderError.setCanvas3D(paramCanvas3D);
/*  883 */               notifyErrorListeners(paramCanvas3D, shaderError);
/*      */             } 
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  890 */         if (!bool3) {
/*  891 */           shaderError = linkShaderProgram(paramCanvas3D, i, this.shaders);
/*  892 */           if (shaderError != null) {
/*  893 */             shaderError.setShaderProgram((ShaderProgram)this.source);
/*  894 */             shaderError.setCanvas3D(paramCanvas3D);
/*  895 */             notifyErrorListeners(paramCanvas3D, shaderError);
/*  896 */             destroyShaderProgram(paramCanvas3D, i);
/*  897 */             this.linkErrorOccurred = true;
/*  898 */             bool3 = true;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  903 */         if (!bool3 && 
/*  904 */           this.vertexAttrNames != null) {
/*  905 */           lookupVertexAttrNames(paramCanvas3D, i, this.vertexAttrNames);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  910 */         if (!bool3 && 
/*  911 */           this.shaderAttrNames != null)
/*      */         {
/*  913 */           lookupShaderAttrNames(paramCanvas3D, i, this.shaderAttrNames);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  919 */       if (bool1) {
/*  920 */         paramCanvas3D.makeCtxCurrent(paramCanvas3D.ctx);
/*      */       }
/*      */ 
/*      */       
/*  924 */       if (bool3) {
/*  925 */         disableShaderProgram(paramCanvas3D);
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*      */     
/*  931 */     enableShaderProgram(paramCanvas3D, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setUniformAttrValue(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, ShaderAttributeValueRetained paramShaderAttributeValueRetained) {
/*  940 */     switch (paramShaderAttributeValueRetained.getClassType()) {
/*      */       case 0:
/*  942 */         return setUniform1i(paramContext, paramShaderProgramId, paramShaderAttrLoc, (int[])paramShaderAttributeValueRetained.attrWrapper.getRef()[0]);
/*      */ 
/*      */       
/*      */       case 1:
/*  946 */         return setUniform1f(paramContext, paramShaderProgramId, paramShaderAttrLoc, (float[])paramShaderAttributeValueRetained.attrWrapper.getRef()[0]);
/*      */ 
/*      */       
/*      */       case 2:
/*  950 */         return setUniform2i(paramContext, paramShaderProgramId, paramShaderAttrLoc, (int[])paramShaderAttributeValueRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 3:
/*  954 */         return setUniform2f(paramContext, paramShaderProgramId, paramShaderAttrLoc, (float[])paramShaderAttributeValueRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 4:
/*  958 */         return setUniform3i(paramContext, paramShaderProgramId, paramShaderAttrLoc, (int[])paramShaderAttributeValueRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 5:
/*  962 */         return setUniform3f(paramContext, paramShaderProgramId, paramShaderAttrLoc, (float[])paramShaderAttributeValueRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 6:
/*  966 */         return setUniform4i(paramContext, paramShaderProgramId, paramShaderAttrLoc, (int[])paramShaderAttributeValueRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 7:
/*  970 */         return setUniform4f(paramContext, paramShaderProgramId, paramShaderAttrLoc, (float[])paramShaderAttributeValueRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 8:
/*  974 */         return setUniformMatrix3f(paramContext, paramShaderProgramId, paramShaderAttrLoc, (float[])paramShaderAttributeValueRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 9:
/*  978 */         return setUniformMatrix4f(paramContext, paramShaderProgramId, paramShaderAttrLoc, (float[])paramShaderAttributeValueRetained.attrWrapper.getRef());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  983 */     assert false : "Unrecognized ShaderAttributeValue classType";
/*  984 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setUniformAttrArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, ShaderAttributeArrayRetained paramShaderAttributeArrayRetained) {
/*  994 */     switch (paramShaderAttributeArrayRetained.getClassType()) {
/*      */       case 0:
/*  996 */         return setUniform1iArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramShaderAttributeArrayRetained.length(), (int[])paramShaderAttributeArrayRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 1:
/* 1000 */         return setUniform1fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramShaderAttributeArrayRetained.length(), (float[])paramShaderAttributeArrayRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 2:
/* 1004 */         return setUniform2iArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramShaderAttributeArrayRetained.length(), (int[])paramShaderAttributeArrayRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 3:
/* 1008 */         return setUniform2fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramShaderAttributeArrayRetained.length(), (float[])paramShaderAttributeArrayRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 4:
/* 1012 */         return setUniform3iArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramShaderAttributeArrayRetained.length(), (int[])paramShaderAttributeArrayRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 5:
/* 1016 */         return setUniform3fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramShaderAttributeArrayRetained.length(), (float[])paramShaderAttributeArrayRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 6:
/* 1020 */         return setUniform4iArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramShaderAttributeArrayRetained.length(), (int[])paramShaderAttributeArrayRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 7:
/* 1024 */         return setUniform4fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramShaderAttributeArrayRetained.length(), (float[])paramShaderAttributeArrayRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 8:
/* 1028 */         return setUniformMatrix3fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramShaderAttributeArrayRetained.length(), (float[])paramShaderAttributeArrayRetained.attrWrapper.getRef());
/*      */ 
/*      */       
/*      */       case 9:
/* 1032 */         return setUniformMatrix4fArray(paramContext, paramShaderProgramId, paramShaderAttrLoc, paramShaderAttributeArrayRetained.length(), (float[])paramShaderAttributeArrayRetained.attrWrapper.getRef());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1037 */     assert false : "Unrecognized ShaderAttributeArray classType";
/* 1038 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setShaderAttributes(Canvas3D paramCanvas3D, ShaderAttributeSetRetained paramShaderAttributeSetRetained) {
/* 1045 */     boolean bool = (paramCanvas3D.useSharedCtx && paramCanvas3D.screen.renderer.sharedCtx != null) ? 1 : 0;
/* 1046 */     int i = bool ? paramCanvas3D.screen.renderer.rendererId : paramCanvas3D.canvasId;
/* 1047 */     ShaderProgramData shaderProgramData1 = getShaderProgramData(i);
/*      */ 
/*      */     
/* 1050 */     if (!shaderProgramData1.isLinked()) {
/*      */       return;
/*      */     }
/*      */     
/* 1054 */     ShaderProgramId shaderProgramId = shaderProgramData1.getShaderProgramId();
/*      */     
/* 1056 */     Iterator iterator = paramShaderAttributeSetRetained.getAttrs().values().iterator();
/* 1057 */     while (iterator.hasNext()) {
/* 1058 */       ShaderError shaderError = null;
/* 1059 */       ShaderAttributeRetained shaderAttributeRetained = (ShaderAttributeRetained)iterator.next();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1064 */       AttrNameInfo attrNameInfo = shaderProgramData1.getAttrNameInfo(shaderAttributeRetained.getAttributeName());
/* 1065 */       if (attrNameInfo == null) {
/*      */         
/* 1067 */         String str = "Attribute name not set in ShaderProgram: " + shaderAttributeRetained.getAttributeName();
/* 1068 */         shaderError = new ShaderError(5, str);
/*      */       } else {
/* 1070 */         ShaderAttrLoc shaderAttrLoc = attrNameInfo.getLocation();
/* 1071 */         if (shaderAttrLoc != null) {
/* 1072 */           if (shaderAttributeRetained instanceof ShaderAttributeValueRetained)
/* 1073 */           { ShaderAttributeValueRetained shaderAttributeValueRetained = (ShaderAttributeValueRetained)shaderAttributeRetained;
/* 1074 */             if (attrNameInfo.isArray() || shaderAttributeValueRetained.getClassType() != attrNameInfo.getType()) {
/*      */               
/* 1076 */               String str = "Attribute type mismatch: " + shaderAttributeValueRetained.getAttributeName();
/* 1077 */               shaderError = new ShaderError(6, str);
/*      */             } else {
/*      */               
/* 1080 */               shaderError = setUniformAttrValue(paramCanvas3D.ctx, shaderProgramId, shaderAttrLoc, shaderAttributeValueRetained);
/*      */             }  }
/* 1082 */           else if (shaderAttributeRetained instanceof ShaderAttributeArrayRetained)
/* 1083 */           { ShaderAttributeArrayRetained shaderAttributeArrayRetained = (ShaderAttributeArrayRetained)shaderAttributeRetained;
/* 1084 */             if (!attrNameInfo.isArray() || shaderAttributeArrayRetained.getClassType() != attrNameInfo.getType()) {
/*      */               
/* 1086 */               String str = "Attribute type mismatch: " + shaderAttributeArrayRetained.getAttributeName();
/* 1087 */               shaderError = new ShaderError(6, str);
/*      */             } else {
/*      */               
/* 1090 */               shaderError = setUniformAttrArray(paramCanvas3D.ctx, shaderProgramId, shaderAttrLoc, shaderAttributeArrayRetained);
/*      */             }  }
/* 1092 */           else { if (shaderAttributeRetained instanceof ShaderAttributeBindingRetained) {
/*      */               assert false;
/* 1094 */               throw new RuntimeException("not implemented");
/*      */             } 
/*      */             
/*      */             assert false; }
/*      */         
/*      */         }
/*      */       } 
/* 1101 */       if (shaderError != null) {
/*      */ 
/*      */         
/* 1104 */         if (this.shaderAttrErrorSet == null) {
/* 1105 */           this.shaderAttrErrorSet = new HashSet();
/*      */         }
/* 1107 */         if (this.shaderAttrErrorSet.add(shaderAttributeRetained.source)) {
/* 1108 */           shaderError.setShaderProgram((ShaderProgram)this.source);
/* 1109 */           shaderError.setShaderAttributeSet((ShaderAttributeSet)paramShaderAttributeSetRetained.source);
/* 1110 */           shaderError.setShaderAttribute((ShaderAttribute)shaderAttributeRetained.source);
/* 1111 */           shaderError.setCanvas3D(paramCanvas3D);
/* 1112 */           notifyErrorListeners(paramCanvas3D, shaderError);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   class ShaderProgramData
/*      */   {
/*      */     private long ctxTimeStamp;
/*      */     
/* 1124 */     private ShaderProgramId shaderProgramId = null;
/*      */ 
/*      */     
/*      */     private boolean linked = false;
/*      */ 
/*      */     
/* 1130 */     private HashMap attrNameInfoMap = new HashMap();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void reset() {
/* 1137 */       this.ctxTimeStamp = 0L;
/* 1138 */       this.shaderProgramId = null;
/* 1139 */       this.linked = false;
/* 1140 */       this.attrNameInfoMap.clear();
/*      */     }
/*      */ 
/*      */     
/* 1144 */     long getCtxTimeStamp() { return this.ctxTimeStamp; }
/*      */ 
/*      */ 
/*      */     
/* 1148 */     void setCtxTimeStamp(long param1Long) { this.ctxTimeStamp = param1Long; }
/*      */ 
/*      */ 
/*      */     
/* 1152 */     void setShaderProgramId(ShaderProgramId param1ShaderProgramId) { this.shaderProgramId = param1ShaderProgramId; }
/*      */ 
/*      */ 
/*      */     
/* 1156 */     ShaderProgramId getShaderProgramId() { return this.shaderProgramId; }
/*      */ 
/*      */ 
/*      */     
/* 1160 */     void setLinked(boolean param1Boolean) { this.linked = param1Boolean; }
/*      */ 
/*      */ 
/*      */     
/* 1164 */     boolean isLinked() { return this.linked; }
/*      */ 
/*      */     
/*      */     void setAttrNameInfo(String param1String, ShaderProgramRetained.AttrNameInfo param1AttrNameInfo) {
/* 1168 */       assert param1String != null;
/* 1169 */       this.attrNameInfoMap.put(param1String, param1AttrNameInfo);
/*      */     }
/*      */ 
/*      */     
/* 1173 */     ShaderProgramRetained.AttrNameInfo getAttrNameInfo(String param1String) { return (ShaderProgramRetained.AttrNameInfo)this.attrNameInfoMap.get(param1String); }
/*      */   }
/*      */   
/*      */   class AttrNameInfo {
/*      */     private ShaderAttrLoc loc;
/*      */     private boolean isArray;
/*      */     private int type;
/*      */     
/* 1181 */     void setLocation(ShaderAttrLoc param1ShaderAttrLoc) { this.loc = param1ShaderAttrLoc; }
/*      */ 
/*      */ 
/*      */     
/* 1185 */     ShaderAttrLoc getLocation() { return this.loc; }
/*      */ 
/*      */ 
/*      */     
/* 1189 */     void setType(int param1Int) { this.type = param1Int; }
/*      */ 
/*      */ 
/*      */     
/* 1193 */     int getType() { return this.type; }
/*      */ 
/*      */ 
/*      */     
/* 1197 */     boolean isArray() { return this.isArray; }
/*      */ 
/*      */ 
/*      */     
/* 1201 */     void setArray(boolean param1Boolean) { this.isArray = param1Boolean; }
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\ShaderProgramRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */