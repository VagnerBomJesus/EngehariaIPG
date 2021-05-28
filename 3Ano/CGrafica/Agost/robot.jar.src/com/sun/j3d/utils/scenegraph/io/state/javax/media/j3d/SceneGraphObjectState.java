/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.SceneGraphIO;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SGIORuntimeException;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SceneGraphObjectState
/*     */ {
/*     */   protected SceneGraphObject node;
/*     */   protected SymbolTableData symbol;
/*     */   protected Controller control;
/*     */   protected String nodeClassName;
/*     */   
/*     */   public SceneGraphObjectState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  79 */     this.symbol = paramSymbolTableData;
/*  80 */     this.control = paramController;
/*     */     
/*  82 */     if (paramSymbolTableData != null) {
/*  83 */       this.node = paramSymbolTableData.j3dNode;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     if (this.node != null) {
/*  92 */       this.nodeClassName = this.node.getClass().getName();
/*     */       
/*     */       try {
/*  95 */         if (this.node instanceof SceneGraphIO)
/*  96 */           ((SceneGraphIO)this.node).createSceneGraphObjectReferences(paramController.getSymbolTable()); 
/*  97 */       } catch (Exception exception) {
/*  98 */         System.err.println("Exception in createSceneGraphObjectReferences");
/*  99 */         exception.printStackTrace();
/*     */       } 
/*     */     } 
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
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 113 */     boolean bool = this.node instanceof SceneGraphIO;
/* 114 */     paramDataOutput.writeBoolean(bool);
/* 115 */     paramDataOutput.writeInt(this.symbol.nodeID);
/*     */ 
/*     */     
/* 118 */     int i = this.control.getNodeClassID(this.node);
/*     */     
/* 120 */     paramDataOutput.writeShort(i);
/*     */     
/* 122 */     if (i == -1) {
/* 123 */       paramDataOutput.writeUTF(this.nodeClassName);
/*     */     }
/* 125 */     writeConstructorParams(paramDataOutput);
/*     */     
/* 127 */     if (bool) {
/* 128 */       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 129 */       DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
/* 130 */       ((SceneGraphIO)this.node).writeSceneGraphObject(dataOutputStream);
/* 131 */       dataOutputStream.close();
/* 132 */       paramDataOutput.writeInt(byteArrayOutputStream.size());
/* 133 */       paramDataOutput.write(byteArrayOutputStream.toByteArray());
/*     */     } 
/*     */     
/* 136 */     writeUserData(paramDataOutput);
/* 137 */     writeString(this.node.getName(), paramDataOutput);
/*     */     
/* 139 */     writeCapabilities(paramDataOutput);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/* 144 */     boolean bool = paramDataInput.readBoolean();
/* 145 */     int i = paramDataInput.readInt();
/*     */     
/* 147 */     short s = paramDataInput.readShort();
/*     */     
/* 149 */     this.nodeClassName = null;
/*     */     
/* 151 */     if (s == -1) {
/* 152 */       this.nodeClassName = paramDataInput.readUTF();
/*     */     }
/* 154 */     readConstructorParams(paramDataInput);
/*     */     
/* 156 */     if (s != -1) {
/* 157 */       this.node = createNode();
/* 158 */       this.nodeClassName = this.node.getClass().getName();
/*     */     } else {
/* 160 */       this.node = createNode(this.nodeClassName);
/*     */     } 
/*     */     
/* 163 */     if (bool) {
/* 164 */       if (this.control.getCurrentFileVersion() == 1) {
/* 165 */         ((SceneGraphIO)this.node).readSceneGraphObject(paramDataInput);
/*     */       } else {
/* 167 */         int j = paramDataInput.readInt();
/* 168 */         if (this.node instanceof SceneGraphIO) {
/* 169 */           byte[] arrayOfByte = new byte[j];
/* 170 */           paramDataInput.readFully(arrayOfByte);
/* 171 */           ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
/* 172 */           DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
/* 173 */           ((SceneGraphIO)this.node).readSceneGraphObject(dataInputStream);
/* 174 */           dataInputStream.close();
/*     */         } else {
/* 176 */           paramDataInput.skipBytes(j);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 181 */     this.symbol = this.control.getSymbolTable().createSymbol(this, this.node, i);
/* 182 */     readUserData(paramDataInput);
/* 183 */     if (this.control.getCurrentFileVersion() > 2) {
/* 184 */       this.node.setName(readString(paramDataInput));
/*     */     }
/*     */     
/* 187 */     readCapabilities(paramDataInput);
/*     */   }
/*     */ 
/*     */   
/* 191 */   public SceneGraphObject getNode() { return this.node; }
/*     */ 
/*     */ 
/*     */   
/* 195 */   public int getNodeID() { return this.symbol.nodeID; }
/*     */ 
/*     */ 
/*     */   
/* 199 */   public SymbolTableData getSymbol() { return this.symbol; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   private void readUserData(DataInput paramDataInput) throws IOException { this.node.setUserData(this.control.readSerializedData(paramDataInput)); }
/*     */ 
/*     */   
/*     */   private void writeUserData(DataOutput paramDataOutput) throws IOException {
/* 208 */     Object object = this.node.getUserData();
/* 209 */     if (object != null && !(object instanceof Serializable)) {
/* 210 */       System.err.println("UserData is not Serializable and will not be saved");
/* 211 */       object = null;
/*     */     } 
/*     */     
/* 214 */     this.control.writeSerializedData(paramDataOutput, (Serializable)object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeCapabilities(DataOutput paramDataOutput) throws IOException {
/* 223 */     long l1 = 0L;
/* 224 */     long l2 = 0L;
/*     */     
/* 226 */     for (byte b = 0; b < 64; b++) {
/* 227 */       if (this.node.getCapability(b)) l1 |= 1L << b; 
/* 228 */       if (!this.node.getCapabilityIsFrequent(b)) l2 |= 1L << b; 
/*     */     } 
/* 230 */     paramDataOutput.writeLong(l1);
/* 231 */     paramDataOutput.writeLong(l2);
/*     */   }
/*     */   
/*     */   private void readCapabilities(DataInput paramDataInput) throws IOException {
/* 235 */     long l1 = paramDataInput.readLong();
/* 236 */     long l2 = paramDataInput.readLong();
/*     */     
/* 238 */     for (byte b = 0; b < 64; b++) {
/* 239 */       if ((l1 & 1L << b) != 0L) this.node.setCapability(b); 
/* 240 */       if ((l2 & 1L << b) != 0L) this.node.clearCapabilityIsFrequent(b);
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeConstructorParams(DataOutput paramDataOutput) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readConstructorParams(DataInput paramDataInput) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 270 */   protected SceneGraphObject createNode() { throw new SGIORuntimeException("createNode() not implemented in class " + getClass().getName()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SceneGraphObject createNode(Class paramClass) {
/*     */     SceneGraphObject sceneGraphObject;
/*     */     try {
/* 284 */       sceneGraphObject = (SceneGraphObject)paramClass.newInstance();
/*     */     
/*     */     }
/* 287 */     catch (IllegalAccessException illegalAccessException) {
/* 288 */       throw new SGIORuntimeException("Broken State class for " + paramClass.getClass().getName() + " - IllegalAccess");
/*     */     }
/* 290 */     catch (InstantiationException instantiationException) {
/* 291 */       throw new SGIORuntimeException("Broken State class for " + paramClass.getClass().getName());
/*     */     } 
/*     */ 
/*     */     
/* 295 */     return sceneGraphObject;
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
/*     */   protected SceneGraphObject createNode(String paramString) {
/*     */     SceneGraphObject sceneGraphObject;
/*     */     try {
/* 309 */       Class clazz = Class.forName(paramString, true, this.control.getClassLoader());
/*     */       
/* 311 */       sceneGraphObject = createNode(clazz);
/*     */     
/*     */     }
/* 314 */     catch (ClassNotFoundException classNotFoundException) {
/* 315 */       if (this.control.useSuperClassIfNoChildClass()) {
/* 316 */         sceneGraphObject = createNodeFromSuper(paramString);
/*     */       } else {
/* 318 */         throw new SGIORuntimeException("No Such Class " + paramString);
/*     */       } 
/*     */     } 
/*     */     
/* 322 */     return sceneGraphObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SceneGraphObject createNodeFromSuper(String paramString) {
/*     */     SceneGraphObject sceneGraphObject;
/* 333 */     String str1 = getClass().getName();
/* 334 */     String str2 = str1.substring(str1.indexOf("state") + 6, str1.length() - 5);
/*     */     
/* 336 */     System.err.println("Unable to create node " + paramString + " attempting Java3D superclass " + str2);
/*     */     
/*     */     try {
/* 339 */       Class clazz = Class.forName(str2);
/*     */       
/* 341 */       sceneGraphObject = (SceneGraphObject)clazz.newInstance();
/*     */     }
/* 343 */     catch (ClassNotFoundException classNotFoundException) {
/* 344 */       throw new SGIORuntimeException("No Such Class " + paramString);
/*     */     }
/* 346 */     catch (IllegalAccessException illegalAccessException) {
/* 347 */       throw new SGIORuntimeException("Broken State class for " + paramString + " - IllegalAccess");
/*     */     }
/* 349 */     catch (InstantiationException instantiationException) {
/* 350 */       throw new SGIORuntimeException("Unable to instantiate class " + paramString);
/*     */     } 
/*     */ 
/*     */     
/* 354 */     return sceneGraphObject;
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
/*     */   private SceneGraphObject createNode(String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject) {
/*     */     SceneGraphObject sceneGraphObject;
/*     */     try {
/* 371 */       Class clazz = Class.forName(paramString);
/* 372 */       Constructor constructor = clazz.getConstructor(paramArrayOfClass);
/* 373 */       sceneGraphObject = (SceneGraphObject)constructor.newInstance(paramArrayOfObject);
/* 374 */     } catch (ClassNotFoundException classNotFoundException) {
/* 375 */       if (this.control.useSuperClassIfNoChildClass()) {
/* 376 */         sceneGraphObject = createNodeFromSuper(paramString, paramArrayOfClass, paramArrayOfObject);
/*     */       } else {
/* 378 */         throw new SGIORuntimeException("No State class for " + paramString);
/*     */       } 
/* 380 */     } catch (IllegalAccessException illegalAccessException) {
/* 381 */       throw new SGIORuntimeException("Broken State class for " + paramString + " - IllegalAccess");
/*     */     }
/* 383 */     catch (InstantiationException instantiationException) {
/* 384 */       throw new SGIORuntimeException("Broken State class for " + paramString);
/*     */     }
/* 386 */     catch (InvocationTargetException invocationTargetException) {
/* 387 */       throw new SGIORuntimeException("InvocationTargetException for " + paramString);
/*     */     }
/* 389 */     catch (NoSuchMethodException noSuchMethodException) {
/* 390 */       for (byte b = 0; b < paramArrayOfClass.length; b++)
/* 391 */         System.err.println(paramArrayOfClass[b].getName()); 
/* 392 */       System.err.println("------");
/* 393 */       throw new SGIORuntimeException("Invalid constructor for " + paramString);
/*     */     } 
/*     */ 
/*     */     
/* 397 */     return sceneGraphObject;
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
/*     */   protected SceneGraphObject createNode(Class paramClass, Class[] paramArrayOfClass, Object[] paramArrayOfObject) {
/*     */     SceneGraphObject sceneGraphObject;
/*     */     try {
/* 414 */       Constructor constructor = paramClass.getConstructor(paramArrayOfClass);
/* 415 */       sceneGraphObject = (SceneGraphObject)constructor.newInstance(paramArrayOfObject);
/* 416 */     } catch (IllegalAccessException illegalAccessException) {
/* 417 */       throw new SGIORuntimeException("Broken State class for " + paramClass.getClass().getName() + " - IllegalAccess");
/*     */     }
/* 419 */     catch (InstantiationException instantiationException) {
/* 420 */       throw new SGIORuntimeException("Broken State class for " + paramClass.getClass().getName());
/*     */     }
/* 422 */     catch (InvocationTargetException invocationTargetException) {
/* 423 */       throw new SGIORuntimeException("InvocationTargetException for " + paramClass.getClass().getName());
/*     */     }
/* 425 */     catch (NoSuchMethodException noSuchMethodException) {
/* 426 */       for (byte b = 0; b < paramArrayOfClass.length; b++)
/* 427 */         System.err.println(paramArrayOfClass[b].getName()); 
/* 428 */       System.err.println("------");
/* 429 */       throw new SGIORuntimeException("Invalid constructor for " + paramClass.getClass().getName());
/*     */     } 
/*     */ 
/*     */     
/* 433 */     return sceneGraphObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SceneGraphObject createNodeFromSuper(String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject) {
/*     */     SceneGraphObject sceneGraphObject;
/* 444 */     String str1 = getClass().getName();
/* 445 */     String str2 = str1.substring(str1.indexOf("state") + 6, str1.length() - 5);
/*     */ 
/*     */     
/*     */     try {
/* 449 */       Class clazz = Class.forName(str2);
/* 450 */       Constructor constructor = clazz.getConstructor(paramArrayOfClass);
/* 451 */       sceneGraphObject = (SceneGraphObject)constructor.newInstance(paramArrayOfObject);
/* 452 */     } catch (ClassNotFoundException classNotFoundException) {
/* 453 */       throw new SGIORuntimeException("No State class for " + str2);
/*     */     }
/* 455 */     catch (IllegalAccessException illegalAccessException) {
/* 456 */       throw new SGIORuntimeException("Broken State class for " + paramString + " - IllegalAccess");
/*     */     }
/* 458 */     catch (InstantiationException instantiationException) {
/* 459 */       throw new SGIORuntimeException("Broken State class for " + paramString);
/*     */     }
/* 461 */     catch (InvocationTargetException invocationTargetException) {
/* 462 */       throw new SGIORuntimeException("InvocationTargetException for " + paramString);
/*     */     }
/* 464 */     catch (NoSuchMethodException noSuchMethodException) {
/* 465 */       for (byte b = 0; b < paramArrayOfClass.length; b++)
/* 466 */         System.err.println(paramArrayOfClass[b].getName()); 
/* 467 */       System.err.println("------");
/* 468 */       throw new SGIORuntimeException("Invalid constructor for " + paramString);
/*     */     } 
/*     */ 
/*     */     
/* 472 */     return sceneGraphObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 481 */   protected SceneGraphObjectState createState(SceneGraphObject paramSceneGraphObject, Controller paramController) { return paramController.createState(paramSceneGraphObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 489 */   private String getClassName(Class paramClass) { return paramClass.getName().substring(paramClass.getName().lastIndexOf('.') + 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/* 501 */     if (this.node instanceof SceneGraphIO)
/* 502 */       ((SceneGraphIO)this.node).restoreSceneGraphObjectReferences(this.control.getSymbolTable()); 
/*     */   }
/*     */   
/*     */   public void cleanup() {
/* 506 */     this.control = null;
/* 507 */     this.node = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String readString(DataInput paramDataInput) throws IOException {
/* 514 */     if (paramDataInput.readBoolean())
/* 515 */       return paramDataInput.readUTF(); 
/* 516 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeString(String paramString, DataOutput paramDataOutput) throws IOException {
/* 523 */     paramDataOutput.writeBoolean((paramString != null));
/* 524 */     if (paramString != null)
/* 525 */       paramDataOutput.writeUTF(paramString); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\SceneGraphObjectState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */