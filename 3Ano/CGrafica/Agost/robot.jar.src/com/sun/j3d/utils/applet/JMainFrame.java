/*     */ package com.sun.j3d.utils.applet;
/*     */ 
/*     */ import java.applet.Applet;
/*     */ import java.applet.AppletContext;
/*     */ import java.applet.AppletStub;
/*     */ import java.applet.AudioClip;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Image;
/*     */ import java.awt.Label;
/*     */ import java.awt.Toolkit;
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
/*     */ import javax.swing.JFrame;
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
/*     */ public class JMainFrame
/*     */   extends JFrame
/*     */   implements Runnable, AppletStub, AppletContext
/*     */ {
/*     */   private String[] args;
/*     */   private String name;
/*     */   private Applet applet;
/*     */   private Label label;
/*     */   private Dimension appletSize;
/*  90 */   private static int instances = 0; private static final String PARAM_PROP_PREFIX = "parameter.";
/*     */   public JMainFrame(Applet paramApplet, String[] paramArrayOfString, int paramInt1, int paramInt2) {
/*     */     this.args = null;
/*  93 */     this.label = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     build(paramApplet, paramArrayOfString, paramInt1, paramInt2); } public JMainFrame(Applet paramApplet, String[] paramArrayOfString) {
/*     */     this.args = null;
/*     */     this.label = null;
/* 102 */     build(paramApplet, paramArrayOfString, -1, -1);
/*     */   } public JMainFrame(Applet paramApplet, int paramInt1, int paramInt2) {
/*     */     this.args = null;
/*     */     this.label = null;
/* 106 */     build(paramApplet, null, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */   private void build(Applet paramApplet, String[] paramArrayOfString, int paramInt1, int paramInt2) {
/* 110 */     instances++;
/* 111 */     this.applet = paramApplet;
/* 112 */     this.args = paramArrayOfString;
/* 113 */     paramApplet.setStub(this);
/* 114 */     this.name = paramApplet.getClass().getName();
/* 115 */     setTitle(this.name);
/*     */ 
/*     */     
/* 118 */     Properties properties = System.getProperties();
/* 119 */     properties.put("browser", "Acme.MainFrame");
/* 120 */     properties.put("browser.version", "11jul96");
/* 121 */     properties.put("browser.vendor", "Acme Laboratories");
/* 122 */     properties.put("browser.vendor.url", "http://www.acme.com/");
/*     */ 
/*     */     
/* 125 */     if (paramArrayOfString != null) {
/* 126 */       parseArgs(paramArrayOfString, properties);
/*     */     }
/*     */ 
/*     */     
/* 130 */     String str1 = getParameter("width");
/* 131 */     if (str1 != null)
/* 132 */       paramInt1 = Integer.parseInt(str1); 
/* 133 */     String str2 = getParameter("height");
/* 134 */     if (str2 != null) {
/* 135 */       paramInt2 = Integer.parseInt(str2);
/*     */     }
/*     */     
/* 138 */     if (paramInt1 == -1 || paramInt2 == -1) {
/* 139 */       System.err.println("Width and height must be specified.");
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 144 */     Container container = getContentPane();
/* 145 */     container.add(paramApplet, "Center");
/*     */ 
/*     */     
/* 148 */     pack();
/* 149 */     validate();
/* 150 */     this.appletSize = paramApplet.getSize();
/* 151 */     paramApplet.setSize(paramInt1, paramInt2);
/* 152 */     setVisible(true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     SecurityManager securityManager = System.getSecurityManager();
/* 158 */     boolean bool = true;
/* 159 */     if (securityManager != null) {
/*     */       try {
/* 161 */         securityManager.checkExit(0);
/* 162 */       } catch (SecurityException securityException) {
/* 163 */         bool = false;
/*     */       } 
/*     */     }
/*     */     
/* 167 */     final boolean _doExit = bool;
/*     */ 
/*     */     
/* 170 */     addWindowListener(new WindowAdapter()
/*     */         {
/*     */           public void windowClosing(WindowEvent param1WindowEvent)
/*     */           {
/* 174 */             if (JMainFrame.this.applet != null) {
/* 175 */               JMainFrame.this.applet.destroy();
/*     */             }
/* 177 */             JMainFrame.this.hide();
/*     */             try {
/* 179 */               JMainFrame.this.dispose();
/* 180 */             } catch (IllegalStateException illegalStateException) {}
/*     */             
/* 182 */             if (_doExit) {
/* 183 */               System.exit(0);
/*     */             }
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 191 */     (new Thread(this)).start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void parseArgs(String[] paramArrayOfString, Properties paramProperties) {
/* 198 */     for (byte b = 0; b < paramArrayOfString.length; b++) {
/* 199 */       String str = paramArrayOfString[b];
/* 200 */       int i = str.indexOf('=');
/* 201 */       if (i == -1) {
/* 202 */         paramProperties.put("parameter." + str.toLowerCase(), "");
/*     */       } else {
/* 204 */         paramProperties.put("parameter." + str.substring(0, i).toLowerCase(), str.substring(i + 1));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 214 */     showStatus(this.name + " initializing...");
/* 215 */     this.applet.init();
/* 216 */     validate();
/* 217 */     showStatus(this.name + " starting...");
/* 218 */     this.applet.start();
/* 219 */     validate();
/* 220 */     showStatus(this.name + " running...");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   public boolean isActive() { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getDocumentBase() {
/* 231 */     String str1 = System.getProperty("user.dir");
/* 232 */     String str2 = str1.replace(File.separatorChar, '/');
/*     */     try {
/* 234 */       return new URL("file:" + str2 + "/");
/*     */     }
/* 236 */     catch (MalformedURLException malformedURLException) {
/* 237 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getCodeBase() {
/* 245 */     String str = System.getProperty("java.class.path");
/* 246 */     StringTokenizer stringTokenizer = new StringTokenizer(str, ":");
/* 247 */     while (stringTokenizer.hasMoreElements()) {
/* 248 */       String str1 = (String)stringTokenizer.nextElement();
/* 249 */       String str2 = str1 + File.separatorChar + this.name + ".class";
/* 250 */       File file = new File(str2);
/* 251 */       if (file.exists()) {
/* 252 */         String str3 = str1.replace(File.separatorChar, '/');
/*     */         try {
/* 254 */           return new URL("file:" + str3 + "/");
/*     */         }
/* 256 */         catch (MalformedURLException malformedURLException) {
/* 257 */           return null;
/*     */         } 
/*     */       } 
/*     */     } 
/* 261 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 266 */   public String getParameter(String paramString) { return System.getProperty("parameter." + paramString.toLowerCase()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appletResize(int paramInt1, int paramInt2) {
/* 272 */     Dimension dimension = getSize();
/* 273 */     dimension.width += paramInt1 - this.appletSize.width;
/* 274 */     dimension.height += paramInt2 - this.appletSize.height;
/* 275 */     setSize(dimension);
/* 276 */     this.appletSize = this.applet.getSize();
/*     */   }
/*     */ 
/*     */   
/* 280 */   public AppletContext getAppletContext() { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 292 */   public AudioClip getAudioClip(URL paramURL) { return new AppletAudioClip(paramURL); }
/*     */ 
/*     */   
/*     */   public Image getImage(URL paramURL) {
/* 296 */     Toolkit toolkit = Toolkit.getDefaultToolkit();
/*     */     try {
/* 298 */       ImageProducer imageProducer = (ImageProducer)paramURL.getContent();
/* 299 */       return toolkit.createImage(imageProducer);
/*     */     }
/* 301 */     catch (IOException iOException) {
/* 302 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Applet getApplet(String paramString) {
/* 308 */     if (paramString.equals(this.name))
/* 309 */       return this.applet; 
/* 310 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Enumeration getApplets() {
/* 315 */     Vector vector = new Vector();
/* 316 */     vector.addElement(this.applet);
/* 317 */     return vector.elements();
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
/* 329 */     if (this.label != null) {
/* 330 */       this.label.setText(paramString);
/*     */     }
/*     */   }
/*     */   
/* 334 */   public void setStream(String paramString, InputStream paramInputStream) { throw new RuntimeException("Not Implemented"); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 339 */   public InputStream getStream(String paramString) { throw new RuntimeException("Not Implemented"); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 344 */   public Iterator getStreamKeys() { throw new RuntimeException("Not Implemented"); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\applet\JMainFrame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */