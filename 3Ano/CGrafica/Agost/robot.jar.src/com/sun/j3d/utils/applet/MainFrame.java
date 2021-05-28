/*     */ package com.sun.j3d.utils.applet;
/*     */ 
/*     */ import java.applet.Applet;
/*     */ import java.applet.AppletContext;
/*     */ import java.applet.AppletStub;
/*     */ import java.applet.AudioClip;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Image;
/*     */ import java.awt.Label;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.Window;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.image.ImageProducer;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.Properties;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import sun.applet.AppletAudioClip;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MainFrame
/*     */   extends Frame
/*     */   implements Runnable, AppletStub, AppletContext
/*     */ {
/*     */   private String[] args;
/*     */   private String name;
/*     */   private boolean barebones;
/*     */   private Applet applet;
/*     */   private Label label;
/*     */   private Dimension appletSize;
/*     */   private static final String PARAM_PROP_PREFIX = "parameter.";
/* 125 */   private static int instances = 0; public MainFrame(Applet paramApplet, String[] paramArrayOfString, int paramInt1, int paramInt2) {
/*     */     this.args = null;
/* 127 */     this.barebones = true;
/*     */     
/* 129 */     this.label = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     build(paramApplet, paramArrayOfString, paramInt1, paramInt2);
/*     */   } public MainFrame(Applet paramApplet, String[] paramArrayOfString) {
/*     */     this.args = null;
/*     */     this.barebones = true;
/*     */     this.label = null;
/* 142 */     build(paramApplet, paramArrayOfString, -1, -1);
/*     */   } public MainFrame(Applet paramApplet, int paramInt1, int paramInt2) {
/*     */     this.args = null;
/*     */     this.barebones = true;
/*     */     this.label = null;
/* 147 */     build(paramApplet, null, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void build(Applet paramApplet, String[] paramArrayOfString, int paramInt1, int paramInt2) {
/* 153 */     instances++;
/* 154 */     this.applet = paramApplet;
/* 155 */     this.args = paramArrayOfString;
/* 156 */     paramApplet.setStub(this);
/* 157 */     this.name = paramApplet.getClass().getName();
/* 158 */     setTitle(this.name);
/*     */ 
/*     */     
/* 161 */     Properties properties = System.getProperties();
/* 162 */     properties.put("browser", "Acme.MainFrame");
/* 163 */     properties.put("browser.version", "11jul96");
/* 164 */     properties.put("browser.vendor", "Acme Laboratories");
/* 165 */     properties.put("browser.vendor.url", "http://www.acme.com/");
/*     */ 
/*     */     
/* 168 */     if (paramArrayOfString != null) {
/* 169 */       parseArgs(paramArrayOfString, properties);
/*     */     }
/*     */ 
/*     */     
/* 173 */     String str1 = getParameter("width");
/* 174 */     if (str1 != null) {
/* 175 */       paramInt1 = Integer.parseInt(str1);
/*     */     }
/*     */     
/* 178 */     String str2 = getParameter("height");
/* 179 */     if (str2 != null) {
/* 180 */       paramInt2 = Integer.parseInt(str2);
/*     */     }
/*     */ 
/*     */     
/* 184 */     if (paramInt1 == -1 || paramInt2 == -1) {
/* 185 */       System.err.println("Width and height must be specified.");
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 190 */     String str3 = getParameter("barebones");
/* 191 */     if (str3 != null && str3.equals("true")) {
/* 192 */       this.barebones = true;
/*     */     }
/*     */ 
/*     */     
/* 196 */     setLayout(new BorderLayout());
/* 197 */     add("Center", paramApplet);
/*     */ 
/*     */     
/* 200 */     pack();
/* 201 */     validate();
/* 202 */     this.appletSize = paramApplet.getSize();
/* 203 */     paramApplet.setSize(paramInt1, paramInt2);
/* 204 */     setVisible(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 210 */     SecurityManager securityManager = System.getSecurityManager();
/* 211 */     boolean bool = true;
/*     */     
/* 213 */     if (securityManager != null) {
/*     */       try {
/* 215 */         securityManager.checkExit(0);
/* 216 */       } catch (SecurityException securityException) {
/* 217 */         bool = false;
/*     */       } 
/*     */     }
/*     */     
/* 221 */     final boolean _doExit = bool;
/*     */     
/* 223 */     addWindowListener(new WindowAdapter() {
/*     */           public void windowClosing(WindowEvent param1WindowEvent) {
/* 225 */             if (MainFrame.this.applet != null) {
/* 226 */               MainFrame.this.applet.destroy();
/*     */             }
/* 228 */             Window window = param1WindowEvent.getWindow();
/* 229 */             window.hide();
/*     */             try {
/* 231 */               window.dispose();
/* 232 */             } catch (IllegalStateException illegalStateException) {}
/*     */             
/* 234 */             if (_doExit) {
/* 235 */               System.exit(0);
/*     */             }
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 242 */     (new Thread(this)).start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void parseArgs(String[] paramArrayOfString, Properties paramProperties) {
/* 250 */     for (byte b = 0; b < paramArrayOfString.length; b++) {
/* 251 */       String str = paramArrayOfString[b];
/* 252 */       int i = str.indexOf('=');
/* 253 */       if (i == -1) {
/* 254 */         paramProperties.put("parameter." + str.toLowerCase(), "");
/*     */       } else {
/* 256 */         paramProperties.put("parameter." + str.substring(0, i).toLowerCase(), str.substring(i + 1));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 266 */     showStatus(this.name + " initializing...");
/* 267 */     this.applet.init();
/* 268 */     validate();
/* 269 */     showStatus(this.name + " starting...");
/* 270 */     this.applet.start();
/* 271 */     validate();
/* 272 */     showStatus(this.name + " running...");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 278 */   public boolean isActive() { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getDocumentBase() {
/* 283 */     String str1 = System.getProperty("user.dir");
/* 284 */     String str2 = str1.replace(File.separatorChar, '/');
/*     */     try {
/* 286 */       return new URL("file:" + str2 + "/");
/* 287 */     } catch (MalformedURLException malformedURLException) {
/* 288 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getCodeBase() {
/* 296 */     String str = System.getProperty("java.class.path");
/* 297 */     StringTokenizer stringTokenizer = new StringTokenizer(str, ":");
/* 298 */     while (stringTokenizer.hasMoreElements()) {
/* 299 */       String str1 = (String)stringTokenizer.nextElement();
/* 300 */       String str2 = str1 + File.separatorChar + this.name + ".class";
/* 301 */       File file = new File(str2);
/* 302 */       if (file.exists()) {
/* 303 */         String str3 = str1.replace(File.separatorChar, '/');
/*     */         try {
/* 305 */           return new URL("file:" + str3 + "/");
/* 306 */         } catch (MalformedURLException malformedURLException) {
/* 307 */           return null;
/*     */         } 
/*     */       } 
/*     */     } 
/* 311 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 316 */   public String getParameter(String paramString) { return System.getProperty("parameter." + paramString.toLowerCase()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appletResize(int paramInt1, int paramInt2) {
/* 322 */     Dimension dimension = getSize();
/* 323 */     dimension.width += paramInt1 - this.appletSize.width;
/* 324 */     dimension.height += paramInt2 - this.appletSize.height;
/* 325 */     setSize(dimension);
/* 326 */     this.appletSize = this.applet.getSize();
/*     */   }
/*     */ 
/*     */   
/* 330 */   public AppletContext getAppletContext() { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 341 */   public AudioClip getAudioClip(URL paramURL) { return new AppletAudioClip(paramURL); }
/*     */ 
/*     */   
/*     */   public Image getImage(URL paramURL) {
/* 345 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/*     */     try {
/* 347 */       ImageProducer imageProducer = (ImageProducer)paramURL.getContent();
/* 348 */       return toolkit.createImage(imageProducer);
/* 349 */     } catch (IOException iOException) {
/* 350 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Applet getApplet(String paramString) {
/* 356 */     if (paramString.equals(this.name)) {
/* 357 */       return this.applet;
/*     */     }
/* 359 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Enumeration getApplets() {
/* 364 */     Vector vector = new Vector();
/* 365 */     vector.addElement(this.applet);
/* 366 */     return vector.elements();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void showDocument(URL paramURL) {}
/*     */ 
/*     */   
/*     */   public void showDocument(URL paramURL, String paramString) {}
/*     */ 
/*     */   
/*     */   public void showStatus(String paramString) {
/* 378 */     if (this.label != null) {
/* 379 */       this.label.setText(paramString);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 384 */   public void setStream(String paramString, InputStream paramInputStream) { throw new RuntimeException("Not Implemented"); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 389 */   public InputStream getStream(String paramString) { throw new RuntimeException("Not Implemented"); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 394 */   public Iterator getStreamKeys() { throw new RuntimeException("Not Implemented"); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\applet\MainFrame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */