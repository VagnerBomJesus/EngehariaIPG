/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MediaContainer
/*     */   extends NodeComponent
/*     */ {
/*     */   public static final int ALLOW_CACHE_READ = 0;
/*     */   public static final int ALLOW_CACHE_WRITE = 1;
/*     */   public static final int ALLOW_URL_READ = 2;
/*     */   public static final int ALLOW_URL_WRITE = 3;
/*  64 */   private static final int[] readCapabilities = { 0, 2 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public MediaContainer() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MediaContainer(String paramString) {
/*  93 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/*  95 */     ((MediaContainerRetained)this.retained).setURLString(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MediaContainer(URL paramURL) {
/* 106 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 108 */     ((MediaContainerRetained)this.retained).setURLObject(paramURL);
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
/*     */   public MediaContainer(InputStream paramInputStream) {
/* 120 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 122 */     ((MediaContainerRetained)this.retained).setInputStream(paramInputStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 130 */     this.retained = new MediaContainerRetained();
/* 131 */     this.retained.setSource(this);
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
/*     */   public void setCacheEnable(boolean paramBoolean) {
/* 143 */     if (isLiveOrCompiled() && 
/* 144 */       !getCapability(1)) {
/* 145 */       throw new CapabilityNotSetException(J3dI18N.getString("MediaContainer1"));
/*     */     }
/* 147 */     ((MediaContainerRetained)this.retained).setCacheEnable(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCacheEnable() {
/* 157 */     if (isLiveOrCompiled() && 
/* 158 */       !getCapability(0)) {
/* 159 */       throw new CapabilityNotSetException(J3dI18N.getString("MediaContainer2"));
/*     */     }
/* 161 */     return ((MediaContainerRetained)this.retained).getCacheEnable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURL(String paramString) {
/* 169 */     if (isLiveOrCompiled() && 
/* 170 */       !getCapability(3)) {
/* 171 */       throw new CapabilityNotSetException(J3dI18N.getString("MediaContainer3"));
/*     */     }
/*     */     
/* 174 */     ((MediaContainerRetained)this.retained).setURLString(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURL(URL paramURL) {
/* 181 */     if (isLiveOrCompiled() && 
/* 182 */       !getCapability(3))
/* 183 */       throw new CapabilityNotSetException(J3dI18N.getString("MediaContainer3")); 
/* 184 */     ((MediaContainerRetained)this.retained).setURLObject(paramURL);
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
/*     */   public void setURLString(String paramString) {
/* 198 */     if (isLiveOrCompiled() && 
/* 199 */       !getCapability(3)) {
/* 200 */       throw new CapabilityNotSetException(J3dI18N.getString("MediaContainer3"));
/*     */     }
/* 202 */     ((MediaContainerRetained)this.retained).setURLString(paramString);
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
/*     */   public void setURLObject(URL paramURL) {
/* 216 */     if (isLiveOrCompiled() && 
/* 217 */       !getCapability(3))
/* 218 */       throw new CapabilityNotSetException(J3dI18N.getString("MediaContainer3")); 
/* 219 */     ((MediaContainerRetained)this.retained).setURLObject(paramURL);
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
/*     */   public void setInputStream(InputStream paramInputStream) {
/* 233 */     if (isLiveOrCompiled() && 
/* 234 */       !getCapability(3))
/* 235 */       throw new CapabilityNotSetException(J3dI18N.getString("MediaContainer3")); 
/* 236 */     ((MediaContainerRetained)this.retained).setInputStream(paramInputStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getURL() {
/* 244 */     if (isLiveOrCompiled() && 
/* 245 */       !getCapability(2))
/* 246 */       throw new CapabilityNotSetException(J3dI18N.getString("MediaContainer4")); 
/* 247 */     return ((MediaContainerRetained)this.retained).getURLString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getURLString() {
/* 258 */     if (isLiveOrCompiled() && 
/* 259 */       !getCapability(2))
/* 260 */       throw new CapabilityNotSetException(J3dI18N.getString("MediaContainer4")); 
/* 261 */     return ((MediaContainerRetained)this.retained).getURLString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getURLObject() {
/* 272 */     if (isLiveOrCompiled() && 
/* 273 */       !getCapability(2))
/* 274 */       throw new CapabilityNotSetException(J3dI18N.getString("MediaContainer4")); 
/* 275 */     return ((MediaContainerRetained)this.retained).getURLObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getInputStream() {
/* 286 */     if (isLiveOrCompiled() && 
/* 287 */       !getCapability(2))
/* 288 */       throw new CapabilityNotSetException(J3dI18N.getString("MediaContainer4")); 
/* 289 */     return ((MediaContainerRetained)this.retained).getInputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeComponent cloneNodeComponent() {
/* 297 */     MediaContainer mediaContainer = new MediaContainer();
/* 298 */     mediaContainer.duplicateNodeComponent(this);
/* 299 */     return mediaContainer;
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
/*     */   void duplicateAttributes(NodeComponent paramNodeComponent, boolean paramBoolean) {
/* 327 */     super.duplicateAttributes(paramNodeComponent, paramBoolean);
/*     */     
/* 329 */     MediaContainerRetained mediaContainerRetained1 = (MediaContainerRetained)paramNodeComponent.retained;
/*     */     
/* 331 */     MediaContainerRetained mediaContainerRetained2 = (MediaContainerRetained)this.retained;
/* 332 */     mediaContainerRetained2.setCacheEnable(mediaContainerRetained1.getCacheEnable());
/* 333 */     mediaContainerRetained2.setURLString(mediaContainerRetained1.getURLString(), false);
/* 334 */     mediaContainerRetained2.setURLObject(mediaContainerRetained1.getURLObject(), false);
/* 335 */     mediaContainerRetained2.setInputStream(mediaContainerRetained1.getInputStream(), false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\MediaContainer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */