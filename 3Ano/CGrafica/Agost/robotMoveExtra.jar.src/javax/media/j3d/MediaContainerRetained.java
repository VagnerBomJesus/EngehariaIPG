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
/*     */ class MediaContainerRetained
/*     */   extends NodeComponentRetained
/*     */ {
/*     */   boolean cached = true;
/*  31 */   URL url = null;
/*  32 */   String urlString = null;
/*  33 */   InputStream inputStream = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   void setCacheEnable(boolean paramBoolean) { this.cached = paramBoolean; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   boolean getCacheEnable() { return this.cached; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   void setURLObject(URL paramURL) { setURLObject(paramURL, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setURLObject(URL paramURL, boolean paramBoolean) {
/*  69 */     if (paramURL != null) {
/*  70 */       if (this.urlString != null || this.inputStream != null) {
/*  71 */         throw new IllegalArgumentException(J3dI18N.getString("MediaContainer5"));
/*     */       }
/*     */       
/*     */       try {
/*  75 */         InputStream inputStream1 = paramURL.openStream();
/*  76 */         inputStream1.close();
/*     */       }
/*  78 */       catch (Exception exception) {
/*  79 */         throw new SoundException(J3dI18N.getString("MediaContainer0"));
/*     */       } 
/*     */     } 
/*  82 */     this.url = paramURL;
/*     */ 
/*     */     
/*  85 */     if (paramBoolean) {
/*  86 */       dispatchMessage();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   void setURLString(String paramString) { setURLString(paramString, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setURLString(String paramString, boolean paramBoolean) {
/* 104 */     if (paramString != null) {
/* 105 */       if (this.url != null || this.inputStream != null) {
/* 106 */         throw new IllegalArgumentException(J3dI18N.getString("MediaContainer5"));
/*     */       }
/*     */       
/*     */       try {
/* 110 */         URL uRL = new URL(paramString);
/*     */         
/* 112 */         InputStream inputStream1 = uRL.openStream();
/* 113 */         inputStream1.close();
/*     */       }
/* 115 */       catch (Exception exception) {
/* 116 */         throw new SoundException(J3dI18N.getString("MediaContainer0"));
/*     */       } 
/*     */     } 
/* 119 */     this.urlString = paramString;
/*     */ 
/*     */     
/* 122 */     if (paramBoolean) {
/* 123 */       dispatchMessage();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   void setInputStream(InputStream paramInputStream) { setInputStream(paramInputStream, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setInputStream(InputStream paramInputStream, boolean paramBoolean) {
/* 142 */     if (paramInputStream != null && (
/* 143 */       this.url != null || this.urlString != null)) {
/* 144 */       throw new IllegalArgumentException(J3dI18N.getString("MediaContainer5"));
/*     */     }
/* 146 */     this.inputStream = paramInputStream;
/*     */ 
/*     */     
/* 149 */     if (paramBoolean) {
/* 150 */       dispatchMessage();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   String getURLString() { return this.urlString; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   URL getURLObject() { return this.url; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   InputStream getInputStream() { return this.inputStream; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void dispatchMessage() {
/* 182 */     J3dMessage j3dMessage = new J3dMessage();
/* 183 */     j3dMessage.threads = 2;
/* 184 */     j3dMessage.type = 51;
/* 185 */     j3dMessage.universe = null;
/* 186 */     j3dMessage.args[0] = this;
/* 187 */     j3dMessage.args[1] = new Integer(1);
/* 188 */     j3dMessage.args[2] = new Integer(this.users.size());
/* 189 */     j3dMessage.args[3] = this.users;
/* 190 */     VirtualUniverse.mc.processMessage(j3dMessage);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\MediaContainerRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */