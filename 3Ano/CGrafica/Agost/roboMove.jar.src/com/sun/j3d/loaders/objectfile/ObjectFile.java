/*      */ package com.sun.j3d.loaders.objectfile;
/*      */ 
/*      */ import com.sun.j3d.loaders.IncorrectFormatException;
/*      */ import com.sun.j3d.loaders.Loader;
/*      */ import com.sun.j3d.loaders.ParsingErrorException;
/*      */ import com.sun.j3d.loaders.Scene;
/*      */ import com.sun.j3d.loaders.SceneBase;
/*      */ import com.sun.j3d.utils.geometry.GeometryInfo;
/*      */ import com.sun.j3d.utils.geometry.NormalGenerator;
/*      */ import com.sun.j3d.utils.geometry.Stripifier;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.File;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.Reader;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import javax.media.j3d.BranchGroup;
/*      */ import javax.media.j3d.Shape3D;
/*      */ import javax.vecmath.Point3f;
/*      */ import javax.vecmath.TexCoord2f;
/*      */ import javax.vecmath.Vector3f;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ObjectFile
/*      */   implements Loader
/*      */ {
/*      */   private static final int DEBUG = 0;
/*      */   public static final int RESIZE = 64;
/*      */   public static final int TRIANGULATE = 128;
/*      */   public static final int REVERSE = 256;
/*      */   public static final int STRIPIFY = 512;
/*      */   private static final char BACKSLASH = '\\';
/*      */   private int flags;
/*      */   private String basePath;
/*      */   private URL baseUrl;
/*      */   private boolean fromUrl;
/*      */   private float radians;
/*      */   private ArrayList coordList;
/*      */   private ArrayList texList;
/*      */   private ArrayList normList;
/*      */   private ArrayList coordIdxList;
/*      */   private ArrayList texIdxList;
/*      */   private ArrayList normIdxList;
/*      */   private ArrayList stripCounts;
/*      */   private HashMap groups;
/*      */   private String curGroup;
/*      */   private HashMap sGroups;
/*      */   private String curSgroup;
/*      */   private HashMap groupMaterials;
/*      */   private HashMap triGroups;
/*      */   private ArrayList curTriGroup;
/*      */   private HashMap triSgroups;
/*      */   private ArrayList curTriSgroup;
/*      */   private Point3f[] coordArray;
/*      */   private Vector3f[] normArray;
/*      */   private TexCoord2f[] texArray;
/*      */   private long time;
/*      */   private ObjectFileMaterials materials;
/*      */   
/*      */   void readVertex(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/*  329 */     Point3f point3f = new Point3f();
/*      */     
/*  331 */     paramObjectFileParser.getNumber();
/*  332 */     point3f.x = (float)paramObjectFileParser.nval;
/*  333 */     paramObjectFileParser.getNumber();
/*  334 */     point3f.y = (float)paramObjectFileParser.nval;
/*  335 */     paramObjectFileParser.getNumber();
/*  336 */     point3f.z = (float)paramObjectFileParser.nval;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  341 */     paramObjectFileParser.skipToNextLine();
/*      */ 
/*      */     
/*  344 */     this.coordList.add(point3f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void readNormal(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/*  352 */     Vector3f vector3f = new Vector3f();
/*      */     
/*  354 */     paramObjectFileParser.getNumber();
/*  355 */     vector3f.x = (float)paramObjectFileParser.nval;
/*  356 */     paramObjectFileParser.getNumber();
/*  357 */     vector3f.y = (float)paramObjectFileParser.nval;
/*  358 */     paramObjectFileParser.getNumber();
/*  359 */     vector3f.z = (float)paramObjectFileParser.nval;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  364 */     paramObjectFileParser.skipToNextLine();
/*      */ 
/*      */     
/*  367 */     this.normList.add(vector3f);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void readTexture(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/*  375 */     TexCoord2f texCoord2f = new TexCoord2f();
/*      */     
/*  377 */     paramObjectFileParser.getNumber();
/*  378 */     texCoord2f.x = (float)paramObjectFileParser.nval;
/*  379 */     paramObjectFileParser.getNumber();
/*  380 */     texCoord2f.y = (float)paramObjectFileParser.nval;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  385 */     paramObjectFileParser.skipToNextLine();
/*      */ 
/*      */     
/*  388 */     this.texList.add(texCoord2f);
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
/*      */   void readFace(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/*  402 */     int i = 0, j = 0;
/*  403 */     byte b = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  409 */     paramObjectFileParser.getToken();
/*      */     
/*  411 */     paramObjectFileParser; while (paramObjectFileParser.ttype != 10) {
/*      */       
/*  413 */       paramObjectFileParser.pushBack();
/*  414 */       paramObjectFileParser.getNumber();
/*  415 */       int k = (int)paramObjectFileParser.nval - 1;
/*  416 */       if (k < 0) k += this.coordList.size() + 1; 
/*  417 */       this.coordIdxList.add(new Integer(k));
/*      */ 
/*      */       
/*  420 */       paramObjectFileParser.getToken();
/*  421 */       if (paramObjectFileParser.ttype == 47) {
/*      */ 
/*      */         
/*  424 */         paramObjectFileParser.getToken();
/*  425 */         paramObjectFileParser; if (paramObjectFileParser.ttype == -3) {
/*      */           
/*  427 */           paramObjectFileParser.pushBack();
/*  428 */           paramObjectFileParser.getNumber();
/*  429 */           i = (int)paramObjectFileParser.nval - 1;
/*  430 */           if (i < 0) i += this.texList.size() + 1; 
/*  431 */           this.texIdxList.add(new Integer(i));
/*  432 */           paramObjectFileParser.getToken();
/*      */         } 
/*      */ 
/*      */         
/*  436 */         if (paramObjectFileParser.ttype == 47) {
/*      */ 
/*      */           
/*  439 */           paramObjectFileParser.getNumber();
/*  440 */           j = (int)paramObjectFileParser.nval - 1;
/*  441 */           if (j < 0) j += this.normList.size() + 1; 
/*  442 */           this.normIdxList.add(new Integer(j));
/*  443 */           paramObjectFileParser.getToken();
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  450 */       b++;
/*      */     } 
/*      */     
/*  453 */     Integer integer = new Integer(this.stripCounts.size());
/*  454 */     this.stripCounts.add(new Integer(b));
/*      */ 
/*      */     
/*  457 */     this.groups.put(integer, this.curGroup);
/*  458 */     if (this.curSgroup != null) this.sGroups.put(integer, this.curSgroup);
/*      */ 
/*      */     
/*  461 */     paramObjectFileParser.skipToNextLine();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void readPartName(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/*  469 */     paramObjectFileParser.getToken();
/*      */ 
/*      */     
/*  472 */     String str = (String)this.groupMaterials.get(this.curGroup);
/*      */ 
/*      */     
/*  475 */     if (paramObjectFileParser.ttype != -3) { this.curGroup = "default"; }
/*  476 */     else { this.curGroup = paramObjectFileParser.sval; }
/*      */ 
/*      */ 
/*      */     
/*  480 */     if (this.groupMaterials.get(this.curGroup) == null)
/*      */     {
/*  482 */       this.groupMaterials.put(this.curGroup, str);
/*      */     }
/*      */     
/*  485 */     paramObjectFileParser.skipToNextLine();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void readMaterialName(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/*  493 */     paramObjectFileParser.getToken();
/*  494 */     if (paramObjectFileParser.ttype == -3) {
/*  495 */       this.groupMaterials.put(this.curGroup, new String(paramObjectFileParser.sval));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  501 */     paramObjectFileParser.skipToNextLine();
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
/*      */   void loadMaterialFile(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/*  514 */     String str = null;
/*      */ 
/*      */     
/*  517 */     paramObjectFileParser.lowerCaseMode(false);
/*      */ 
/*      */     
/*      */     do {
/*  521 */       paramObjectFileParser.getToken();
/*  522 */       if (paramObjectFileParser.ttype != -3) continue;  str = paramObjectFileParser.sval;
/*  523 */     } while (paramObjectFileParser.ttype != 10);
/*      */     
/*  525 */     this.materials.readMaterialFile(this.fromUrl, this.fromUrl ? this.baseUrl.toString() : this.basePath, str);
/*      */ 
/*      */     
/*  528 */     paramObjectFileParser.lowerCaseMode(true);
/*  529 */     paramObjectFileParser.skipToNextLine();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void readSmoothingGroup(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/*  537 */     paramObjectFileParser.getToken();
/*  538 */     if (paramObjectFileParser.ttype != -3) {
/*  539 */       paramObjectFileParser.skipToNextLine();
/*      */       return;
/*      */     } 
/*  542 */     if (paramObjectFileParser.sval.equals("off")) { this.curSgroup = "0"; }
/*  543 */     else { this.curSgroup = paramObjectFileParser.sval; }
/*      */     
/*  545 */     paramObjectFileParser.skipToNextLine();
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
/*      */   void readFile(ObjectFileParser paramObjectFileParser) throws ParsingErrorException {
/*  557 */     paramObjectFileParser.getToken();
/*  558 */     while (paramObjectFileParser.ttype != -1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  569 */       if (paramObjectFileParser.ttype == -3) {
/*  570 */         if (paramObjectFileParser.sval.equals("v")) {
/*  571 */           readVertex(paramObjectFileParser);
/*  572 */         } else if (paramObjectFileParser.sval.equals("vn")) {
/*  573 */           readNormal(paramObjectFileParser);
/*  574 */         } else if (paramObjectFileParser.sval.equals("vt")) {
/*  575 */           readTexture(paramObjectFileParser);
/*  576 */         } else if (paramObjectFileParser.sval.equals("f")) {
/*  577 */           readFace(paramObjectFileParser);
/*  578 */         } else if (paramObjectFileParser.sval.equals("fo")) {
/*  579 */           readFace(paramObjectFileParser);
/*  580 */         } else if (paramObjectFileParser.sval.equals("g")) {
/*  581 */           readPartName(paramObjectFileParser);
/*  582 */         } else if (paramObjectFileParser.sval.equals("s")) {
/*  583 */           readSmoothingGroup(paramObjectFileParser);
/*  584 */         } else if (paramObjectFileParser.sval.equals("p")) {
/*  585 */           paramObjectFileParser.skipToNextLine();
/*  586 */         } else if (paramObjectFileParser.sval.equals("l")) {
/*  587 */           paramObjectFileParser.skipToNextLine();
/*  588 */         } else if (paramObjectFileParser.sval.equals("mtllib")) {
/*  589 */           loadMaterialFile(paramObjectFileParser);
/*  590 */         } else if (paramObjectFileParser.sval.equals("usemtl")) {
/*  591 */           readMaterialName(paramObjectFileParser);
/*  592 */         } else if (paramObjectFileParser.sval.equals("maplib")) {
/*  593 */           paramObjectFileParser.skipToNextLine();
/*  594 */         } else if (paramObjectFileParser.sval.equals("usemap")) {
/*  595 */           paramObjectFileParser.skipToNextLine();
/*      */         } else {
/*  597 */           throw new ParsingErrorException("Unrecognized token, line " + paramObjectFileParser.lineno());
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*  602 */       paramObjectFileParser.skipToNextLine();
/*      */ 
/*      */       
/*  605 */       paramObjectFileParser.getToken();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ObjectFile(int paramInt, float paramFloat) {
/*      */     this.basePath = null;
/*      */     this.baseUrl = null;
/*      */     this.fromUrl = false;
/*      */     this.coordArray = null;
/*      */     this.normArray = null;
/*      */     this.texArray = null;
/*      */     this.materials = null;
/*  620 */     setFlags(paramInt);
/*  621 */     this.radians = paramFloat;
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
/*  632 */   public ObjectFile(int paramInt) { this(paramInt, -1.0F); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  642 */   public ObjectFile() { this(0, -1.0F); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setBasePathFromFilename(String paramString) {
/*  651 */     if (paramString.lastIndexOf(File.separator) == -1) {
/*      */       
/*  653 */       setBasePath("." + File.separator);
/*      */     } else {
/*  655 */       setBasePath(paramString.substring(0, paramString.lastIndexOf(File.separator)));
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
/*      */   public Scene load(String paramString) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
/*  673 */     setBasePathFromFilename(paramString);
/*      */     
/*  675 */     BufferedReader bufferedReader = new BufferedReader(new FileReader(paramString));
/*  676 */     return load(bufferedReader);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setBaseUrlFromUrl(URL paramURL) throws FileNotFoundException {
/*  681 */     String str2, str1 = paramURL.toString();
/*      */     
/*  683 */     if (str1.lastIndexOf('/') == -1) {
/*  684 */       str2 = paramURL.getProtocol() + ":";
/*      */     } else {
/*  686 */       str2 = str1.substring(0, str1.lastIndexOf('/') + 1);
/*      */     } 
/*      */     try {
/*  689 */       this.baseUrl = new URL(str2);
/*      */     }
/*  691 */     catch (MalformedURLException malformedURLException) {
/*  692 */       throw new FileNotFoundException(malformedURLException.getMessage());
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
/*      */   public Scene load(URL paramURL) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
/*      */     BufferedReader bufferedReader;
/*  709 */     if (this.baseUrl == null) setBaseUrlFromUrl(paramURL);
/*      */     
/*      */     try {
/*  712 */       bufferedReader = new BufferedReader(new InputStreamReader(paramURL.openStream()));
/*      */     }
/*  714 */     catch (IOException iOException) {
/*  715 */       throw new FileNotFoundException(iOException.getMessage());
/*      */     } 
/*  717 */     this.fromUrl = true;
/*  718 */     return load(bufferedReader);
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
/*      */   private Point3f[] getLimits() {
/*  730 */     Point3f point3f = new Point3f();
/*      */ 
/*      */     
/*  733 */     Point3f[] arrayOfPoint3f = new Point3f[2];
/*  734 */     arrayOfPoint3f[0] = new Point3f(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE);
/*  735 */     arrayOfPoint3f[1] = new Point3f(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE);
/*  736 */     for (byte b = 0; b < this.coordList.size(); b++) {
/*      */       
/*  738 */       point3f = (Point3f)this.coordList.get(b);
/*      */ 
/*      */       
/*  741 */       if (point3f.x < (arrayOfPoint3f[0]).x) (arrayOfPoint3f[0]).x = point3f.x; 
/*  742 */       if (point3f.x > (arrayOfPoint3f[1]).x) (arrayOfPoint3f[1]).x = point3f.x; 
/*  743 */       if (point3f.y < (arrayOfPoint3f[0]).y) (arrayOfPoint3f[0]).y = point3f.y; 
/*  744 */       if (point3f.y > (arrayOfPoint3f[1]).y) (arrayOfPoint3f[1]).y = point3f.y; 
/*  745 */       if (point3f.z < (arrayOfPoint3f[0]).z) (arrayOfPoint3f[0]).z = point3f.z; 
/*  746 */       if (point3f.z > (arrayOfPoint3f[1]).z) (arrayOfPoint3f[1]).z = point3f.z;
/*      */     
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  755 */     return arrayOfPoint3f;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void resize() {
/*  765 */     Point3f point3f = new Point3f();
/*      */ 
/*      */     
/*  768 */     Point3f[] arrayOfPoint3f = getLimits();
/*      */ 
/*      */     
/*  771 */     Vector3f vector3f = new Vector3f(-0.5F * ((arrayOfPoint3f[0]).x + (arrayOfPoint3f[1]).x), -0.5F * ((arrayOfPoint3f[0]).y + (arrayOfPoint3f[1]).y), -0.5F * ((arrayOfPoint3f[0]).z + (arrayOfPoint3f[1]).z));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  781 */     float f = (arrayOfPoint3f[1]).x - (arrayOfPoint3f[0]).x;
/*  782 */     if (f < (arrayOfPoint3f[1]).y - (arrayOfPoint3f[0]).y)
/*  783 */       f = (arrayOfPoint3f[1]).y - (arrayOfPoint3f[0]).y; 
/*  784 */     if (f < (arrayOfPoint3f[1]).z - (arrayOfPoint3f[0]).z)
/*  785 */       f = (arrayOfPoint3f[1]).z - (arrayOfPoint3f[0]).z; 
/*  786 */     f /= 2.0F;
/*      */     
/*  788 */     for (byte b = 0; b < this.coordList.size(); b++) {
/*      */       
/*  790 */       point3f = (Point3f)this.coordList.get(b);
/*      */       
/*  792 */       point3f.add(point3f, vector3f);
/*      */       
/*  794 */       point3f.x /= f;
/*  795 */       point3f.y /= f;
/*  796 */       point3f.z /= f;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] objectToIntArray(ArrayList paramArrayList) {
/*  804 */     int[] arrayOfInt = new int[paramArrayList.size()];
/*  805 */     for (byte b = 0; b < paramArrayList.size(); b++) {
/*  806 */       arrayOfInt[b] = ((Integer)paramArrayList.get(b)).intValue();
/*      */     }
/*  808 */     return arrayOfInt;
/*      */   }
/*      */ 
/*      */   
/*      */   private Point3f[] objectToPoint3Array(ArrayList paramArrayList) {
/*  813 */     Point3f[] arrayOfPoint3f = new Point3f[paramArrayList.size()];
/*  814 */     for (byte b = 0; b < paramArrayList.size(); b++) {
/*  815 */       arrayOfPoint3f[b] = (Point3f)paramArrayList.get(b);
/*      */     }
/*  817 */     return arrayOfPoint3f;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private TexCoord2f[] objectToTexCoord2Array(ArrayList paramArrayList) {
/*  823 */     TexCoord2f[] arrayOfTexCoord2f = new TexCoord2f[paramArrayList.size()];
/*  824 */     for (byte b = 0; b < paramArrayList.size(); b++) {
/*  825 */       arrayOfTexCoord2f[b] = (TexCoord2f)paramArrayList.get(b);
/*      */     }
/*  827 */     return arrayOfTexCoord2f;
/*      */   }
/*      */ 
/*      */   
/*      */   private Vector3f[] objectToVectorArray(ArrayList paramArrayList) {
/*  832 */     Vector3f[] arrayOfVector3f = new Vector3f[paramArrayList.size()];
/*  833 */     for (byte b = 0; b < paramArrayList.size(); b++) {
/*  834 */       arrayOfVector3f[b] = (Vector3f)paramArrayList.get(b);
/*      */     }
/*  836 */     return arrayOfVector3f;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] groupIndices(ArrayList paramArrayList1, ArrayList paramArrayList2) {
/*  847 */     int[] arrayOfInt = new int[paramArrayList2.size() * 3];
/*  848 */     for (byte b = 0; b < paramArrayList2.size(); b++) {
/*  849 */       int i = ((Integer)paramArrayList2.get(b)).intValue();
/*  850 */       arrayOfInt[b * 3 + 0] = ((Integer)paramArrayList1.get(i + 0)).intValue();
/*  851 */       arrayOfInt[b * 3 + 1] = ((Integer)paramArrayList1.get(i + 1)).intValue();
/*  852 */       arrayOfInt[b * 3 + 2] = ((Integer)paramArrayList1.get(i + 2)).intValue();
/*      */     } 
/*  854 */     return arrayOfInt;
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
/*      */   private void smoothingGroupNormals() {
/*  870 */     NormalGenerator normalGenerator1 = new NormalGenerator((this.radians == -1.0F) ? Math.PI : this.radians);
/*      */     
/*  872 */     NormalGenerator normalGenerator2 = new NormalGenerator(0.0D);
/*  873 */     this.normList.clear();
/*  874 */     this.normIdxList = null;
/*  875 */     int[] arrayOfInt = new int[this.coordIdxList.size()];
/*      */     
/*  877 */     Iterator iterator = this.triSgroups.keySet().iterator();
/*  878 */     while (iterator.hasNext()) {
/*  879 */       String str = (String)iterator.next();
/*  880 */       ArrayList arrayList = (ArrayList)this.triSgroups.get(str);
/*      */ 
/*      */       
/*  883 */       if (arrayList.size() > 0) {
/*      */         
/*  885 */         GeometryInfo geometryInfo = new GeometryInfo(1);
/*      */         
/*  887 */         geometryInfo.setCoordinateIndices(groupIndices(this.coordIdxList, arrayList));
/*  888 */         geometryInfo.setCoordinates(this.coordArray);
/*      */         
/*  890 */         if (str.equals("0")) { normalGenerator2.generateNormals(geometryInfo); }
/*  891 */         else { normalGenerator1.generateNormals(geometryInfo); }
/*      */ 
/*      */         
/*  894 */         Vector3f[] arrayOfVector3f = geometryInfo.getNormals();
/*  895 */         int[] arrayOfInt1 = geometryInfo.getNormalIndices();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  902 */         byte b1 = 0;
/*      */         
/*  904 */         for (byte b2 = 0; b2 < arrayList.size(); b2++) {
/*      */ 
/*      */           
/*  907 */           int i = ((Integer)arrayList.get(b2)).intValue();
/*      */ 
/*      */           
/*  910 */           for (int j = 0; j < 3; j++) {
/*      */ 
/*      */             
/*  913 */             arrayOfInt[i + j] = this.normList.size();
/*      */ 
/*      */             
/*  916 */             this.normList.add(arrayOfVector3f[arrayOfInt1[b1++]]);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  921 */     this.normIdxList = new ArrayList(this.coordIdxList.size());
/*  922 */     for (byte b = 0; b < this.coordIdxList.size(); b++) {
/*  923 */       this.normIdxList.add(new Integer(arrayOfInt[b]));
/*      */     }
/*  925 */     this.normArray = objectToVectorArray(this.normList);
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
/*      */   private void convertToTriangles() {
/*  950 */     boolean bool1 = ((this.flags & 0x80) != 0) ? 1 : 0;
/*  951 */     boolean bool2 = (!this.texList.isEmpty() && !this.texIdxList.isEmpty() && this.texIdxList.size() == this.coordIdxList.size()) ? 1 : 0;
/*      */     
/*  953 */     boolean bool3 = (!this.normList.isEmpty() && !this.normIdxList.isEmpty() && this.normIdxList.size() == this.coordIdxList.size()) ? 1 : 0;
/*      */     
/*  955 */     int i = this.stripCounts.size();
/*  956 */     boolean bool4 = (this.curSgroup != null) ? 1 : 0;
/*      */     
/*  958 */     this.triGroups = new HashMap(50);
/*  959 */     if (bool4) this.triSgroups = new HashMap(50);
/*      */     
/*  961 */     ArrayList arrayList1 = null;
/*  962 */     ArrayList arrayList2 = null;
/*  963 */     ArrayList arrayList3 = null;
/*      */     
/*  965 */     if (bool1) {
/*  966 */       GeometryInfo geometryInfo = new GeometryInfo(5);
/*  967 */       geometryInfo.setStripCounts(objectToIntArray(this.stripCounts));
/*  968 */       geometryInfo.setCoordinates(this.coordArray);
/*  969 */       geometryInfo.setCoordinateIndices(objectToIntArray(this.coordIdxList));
/*  970 */       if (bool2) {
/*  971 */         geometryInfo.setTextureCoordinateParams(1, 2);
/*  972 */         geometryInfo.setTextureCoordinates(0, this.texArray);
/*  973 */         geometryInfo.setTextureCoordinateIndices(0, objectToIntArray(this.texIdxList));
/*      */       } 
/*  975 */       if (bool3) {
/*  976 */         geometryInfo.setNormals(this.normArray);
/*  977 */         geometryInfo.setNormalIndices(objectToIntArray(this.normIdxList));
/*      */       } 
/*  979 */       geometryInfo.convertToIndexedTriangles();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  984 */       int[] arrayOfInt = geometryInfo.getCoordinateIndices();
/*      */ 
/*      */ 
/*      */       
/*  988 */       int k = 0;
/*  989 */       for (byte b1 = 0; b1 < i; b1++) {
/*  990 */         k += ((Integer)this.stripCounts.get(b1)).intValue() - 2;
/*      */       }
/*  992 */       if (arrayOfInt.length != k * 3) {
/*      */ 
/*      */         
/*  995 */         bool1 = false;
/*      */       } else {
/*      */         
/*  998 */         int[] arrayOfInt1 = geometryInfo.getTextureCoordinateIndices();
/*  999 */         int[] arrayOfInt2 = geometryInfo.getNormalIndices();
/*      */ 
/*      */         
/* 1002 */         this.coordIdxList.clear();
/* 1003 */         this.texIdxList.clear();
/* 1004 */         this.normIdxList.clear();
/* 1005 */         for (byte b2 = 0; b2 < arrayOfInt.length; b2++) {
/* 1006 */           this.coordIdxList.add(new Integer(arrayOfInt[b2]));
/* 1007 */           if (bool2) this.texIdxList.add(new Integer(arrayOfInt1[b2])); 
/* 1008 */           if (bool3) this.normIdxList.add(new Integer(arrayOfInt2[b2]));
/*      */         
/*      */         } 
/*      */       } 
/*      */     } 
/* 1013 */     if (!bool1) {
/* 1014 */       arrayList1 = new ArrayList();
/* 1015 */       if (bool2) arrayList2 = new ArrayList(); 
/* 1016 */       if (bool3) arrayList3 = new ArrayList();
/*      */     
/*      */     } 
/*      */ 
/*      */     
/* 1021 */     int j = 0;
/* 1022 */     for (byte b = 0; b < i; b++) {
/* 1023 */       int k = ((Integer)this.stripCounts.get(b)).intValue();
/*      */ 
/*      */       
/* 1026 */       Integer integer = new Integer(b);
/* 1027 */       this.curGroup = (String)this.groups.get(integer);
/*      */ 
/*      */       
/* 1030 */       this.curTriGroup = (ArrayList)this.triGroups.get(this.curGroup);
/* 1031 */       if (this.curTriGroup == null) {
/* 1032 */         this.curTriGroup = new ArrayList();
/* 1033 */         this.triGroups.put(this.curGroup, this.curTriGroup);
/*      */       } 
/*      */ 
/*      */       
/* 1037 */       if (bool4) {
/* 1038 */         this.curSgroup = (String)this.sGroups.get(integer);
/* 1039 */         if (this.curSgroup == null)
/*      */         {
/*      */ 
/*      */           
/* 1043 */           this.curSgroup = "0";
/*      */         }
/* 1045 */         this.curTriSgroup = (ArrayList)this.triSgroups.get(this.curSgroup);
/* 1046 */         if (this.curTriSgroup == null) {
/* 1047 */           this.curTriSgroup = new ArrayList();
/* 1048 */           this.triSgroups.put(this.curSgroup, this.curTriSgroup);
/*      */         } 
/*      */       } 
/*      */       
/* 1052 */       if (bool1) {
/*      */ 
/*      */         
/* 1055 */         for (byte b1 = 0; b1 < k - 2; b1++) {
/*      */ 
/*      */           
/* 1058 */           Integer integer1 = new Integer(j);
/* 1059 */           this.curTriGroup.add(integer1);
/* 1060 */           if (bool4) this.curTriSgroup.add(integer1);
/*      */           
/* 1062 */           j += 3;
/*      */         } 
/*      */       } else {
/*      */         
/* 1066 */         for (byte b1 = 0; b1 < k - 2; b1++) {
/*      */           
/* 1068 */           Integer integer1 = new Integer(arrayList1.size());
/* 1069 */           this.curTriGroup.add(integer1);
/* 1070 */           if (bool4) this.curTriSgroup.add(integer1);
/*      */           
/* 1072 */           arrayList1.add(this.coordIdxList.get(j));
/* 1073 */           arrayList1.add(this.coordIdxList.get(j + b1 + 1));
/* 1074 */           arrayList1.add(this.coordIdxList.get(j + b1 + 2));
/*      */           
/* 1076 */           if (bool2) {
/* 1077 */             arrayList2.add(this.texIdxList.get(j));
/* 1078 */             arrayList2.add(this.texIdxList.get(j + b1 + 1));
/* 1079 */             arrayList2.add(this.texIdxList.get(j + b1 + 2));
/*      */           } 
/*      */           
/* 1082 */           if (bool3) {
/* 1083 */             arrayList3.add(this.normIdxList.get(j));
/* 1084 */             arrayList3.add(this.normIdxList.get(j + b1 + 1));
/* 1085 */             arrayList3.add(this.normIdxList.get(j + b1 + 2));
/*      */           } 
/*      */         } 
/* 1088 */         j += k;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1093 */     this.stripCounts = null;
/* 1094 */     this.groups = null;
/* 1095 */     this.sGroups = null;
/*      */     
/* 1097 */     if (!bool1) {
/* 1098 */       this.coordIdxList = arrayList1;
/* 1099 */       this.texIdxList = arrayList2;
/* 1100 */       this.normIdxList = arrayList3;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private SceneBase makeScene() {
/* 1107 */     SceneBase sceneBase = new SceneBase();
/* 1108 */     BranchGroup branchGroup = new BranchGroup();
/* 1109 */     sceneBase.setSceneGroup(branchGroup);
/*      */     
/* 1111 */     boolean bool1 = (this.normList.isEmpty() || this.normIdxList.isEmpty() || this.normIdxList.size() != this.coordIdxList.size()) ? 1 : 0;
/*      */     
/* 1113 */     boolean bool2 = (!this.texList.isEmpty() && !this.texIdxList.isEmpty() && this.texIdxList.size() == this.coordIdxList.size()) ? 1 : 0;
/*      */ 
/*      */ 
/*      */     
/* 1117 */     this.coordArray = objectToPoint3Array(this.coordList);
/* 1118 */     if (!bool1) this.normArray = objectToVectorArray(this.normList); 
/* 1119 */     if (bool2) this.texArray = objectToTexCoord2Array(this.texList);
/*      */     
/* 1121 */     convertToTriangles();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1129 */     if (bool1 && this.curSgroup != null) {
/* 1130 */       smoothingGroupNormals();
/* 1131 */       bool1 = false;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1139 */     NormalGenerator normalGenerator = null;
/* 1140 */     if (bool1) normalGenerator = new NormalGenerator(this.radians);
/*      */     
/* 1142 */     Stripifier stripifier = null;
/* 1143 */     if ((this.flags & 0x200) != 0) stripifier = new Stripifier();
/*      */     
/* 1145 */     long l1 = 0L, l2 = 0L, l3 = 0L, l4 = 0L;
/*      */ 
/*      */     
/* 1148 */     Iterator iterator = this.triGroups.keySet().iterator();
/* 1149 */     while (iterator.hasNext()) {
/*      */       
/* 1151 */       String str = (String)iterator.next();
/* 1152 */       ArrayList arrayList = (ArrayList)this.triGroups.get(str);
/*      */ 
/*      */       
/* 1155 */       if (arrayList.size() > 0) {
/*      */         
/* 1157 */         GeometryInfo geometryInfo = new GeometryInfo(1);
/*      */         
/* 1159 */         geometryInfo.setCoordinateIndices(groupIndices(this.coordIdxList, arrayList));
/* 1160 */         geometryInfo.setCoordinates(this.coordArray);
/*      */         
/* 1162 */         if (bool2) {
/* 1163 */           geometryInfo.setTextureCoordinateParams(1, 2);
/* 1164 */           geometryInfo.setTextureCoordinates(0, this.texArray);
/* 1165 */           geometryInfo.setTextureCoordinateIndices(0, groupIndices(this.texIdxList, arrayList));
/*      */         } 
/*      */ 
/*      */         
/* 1169 */         if (bool1) {
/* 1170 */           if ((this.flags & 0x100) != 0) geometryInfo.reverse(); 
/* 1171 */           normalGenerator.generateNormals(geometryInfo);
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/* 1178 */           geometryInfo.setNormalIndices(groupIndices(this.normIdxList, arrayList));
/* 1179 */           geometryInfo.setNormals(this.normArray);
/* 1180 */           if ((this.flags & 0x100) != 0) geometryInfo.reverse();
/*      */         
/*      */         } 
/* 1183 */         if ((this.flags & 0x200) != 0) {
/* 1184 */           stripifier.stripify(geometryInfo);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1193 */         Shape3D shape3D = new Shape3D();
/*      */         
/* 1195 */         shape3D.setGeometry(geometryInfo.getGeometryArray(true, true, false));
/*      */         
/* 1197 */         String str1 = (String)this.groupMaterials.get(str);
/* 1198 */         this.materials.assignMaterial(str1, shape3D);
/*      */         
/* 1200 */         branchGroup.addChild(shape3D);
/* 1201 */         sceneBase.addNamedObject(str, shape3D);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1211 */     return sceneBase;
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
/*      */   public Scene load(Reader paramReader) throws FileNotFoundException, IncorrectFormatException, ParsingErrorException {
/* 1226 */     ObjectFileParser objectFileParser = new ObjectFileParser(paramReader);
/*      */     
/* 1228 */     this.coordList = new ArrayList();
/* 1229 */     this.texList = new ArrayList();
/* 1230 */     this.normList = new ArrayList();
/* 1231 */     this.coordIdxList = new ArrayList();
/* 1232 */     this.texIdxList = new ArrayList();
/* 1233 */     this.normIdxList = new ArrayList();
/* 1234 */     this.groups = new HashMap(50);
/* 1235 */     this.curGroup = "default";
/* 1236 */     this.sGroups = new HashMap(50);
/* 1237 */     this.curSgroup = null;
/* 1238 */     this.stripCounts = new ArrayList();
/* 1239 */     this.groupMaterials = new HashMap(50);
/* 1240 */     this.groupMaterials.put(this.curGroup, "default");
/* 1241 */     this.materials = new ObjectFileMaterials();
/*      */     
/* 1243 */     this.time = 0L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1248 */     readFile(objectFileParser);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1256 */     if ((this.flags & 0x40) != 0) resize();
/*      */     
/* 1258 */     return makeScene();
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
/* 1269 */   public void setBaseUrl(URL paramURL) throws FileNotFoundException { this.baseUrl = paramURL; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1278 */   public URL getBaseUrl() { return this.baseUrl; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBasePath(String paramString) {
/* 1289 */     this.basePath = paramString;
/* 1290 */     if (this.basePath == null || this.basePath == "")
/* 1291 */       this.basePath = "." + File.separator; 
/* 1292 */     this.basePath = this.basePath.replace('/', File.separatorChar);
/* 1293 */     this.basePath = this.basePath.replace('\\', File.separatorChar);
/* 1294 */     if (!this.basePath.endsWith(File.separator)) {
/* 1295 */       this.basePath += File.separator;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1304 */   public String getBasePath() { return this.basePath; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1316 */   public void setFlags(int paramInt) { this.flags = paramInt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1329 */   public int getFlags() { return this.flags; }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3d\loaders\objectfile\ObjectFile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */