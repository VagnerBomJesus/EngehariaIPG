/*      */ package com.sun.j3d.utils.universe;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.StreamTokenizer;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.AbstractMap;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ConfigContainer
/*      */ {
/*   93 */   private Map baseNameMap = new HashMap();
/*      */ 
/*      */   
/*   96 */   private Map viewCanvasMap = new HashMap();
/*      */ 
/*      */   
/*   99 */   private ReadOnlyMap bodyMap = null;
/*  100 */   private ReadOnlyMap environmentMap = null;
/*  101 */   private ReadOnlyMap viewerMap = null;
/*  102 */   private ReadOnlyMap deviceMap = null;
/*  103 */   private ReadOnlyMap sensorMap = null;
/*  104 */   private ReadOnlyMap behaviorMap = null;
/*  105 */   private ReadOnlyMap platformMap = null;
/*  106 */   private ReadOnlyMap genericObjectMap = null;
/*      */ 
/*      */   
/*  109 */   private ReadOnlySet bodies = null;
/*  110 */   private ReadOnlySet environments = null;
/*  111 */   private ReadOnlySet viewers = null;
/*  112 */   private ReadOnlySet devices = null;
/*  113 */   private ReadOnlySet sensors = null;
/*  114 */   private ReadOnlySet behaviors = null;
/*  115 */   private ReadOnlySet platforms = null;
/*  116 */   private ReadOnlySet genericObjects = null;
/*      */ 
/*      */   
/*  119 */   private int transformCount = 1;
/*      */ 
/*      */   
/*      */   private boolean setVisible = false;
/*      */   
/*  124 */   private ClassLoader classLoader = ClassLoader.getSystemClassLoader();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  129 */   String currentFileName = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  139 */   public ConfigContainer(URL paramURL) { this(paramURL, false, 1, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  152 */   public ConfigContainer(URL paramURL, ClassLoader paramClassLoader) { this(paramURL, false, 1, true, paramClassLoader); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  171 */   public ConfigContainer(URL paramURL, boolean paramBoolean, int paramInt) { this(paramURL, paramBoolean, paramInt, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  193 */   public ConfigContainer(URL paramURL, boolean paramBoolean, int paramInt, ClassLoader paramClassLoader) { this(paramURL, paramBoolean, paramInt, true, paramClassLoader); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ConfigContainer(URL paramURL, boolean paramBoolean1, int paramInt, boolean paramBoolean2) {
/*  230 */     if (paramInt < 1) {
/*  231 */       throw new IllegalArgumentException("transformCount must be greater than 0");
/*      */     }
/*      */     
/*  234 */     loadConfig(paramURL);
/*  235 */     processConfig(paramBoolean1, paramInt, paramBoolean2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ConfigContainer(URL paramURL, boolean paramBoolean1, int paramInt, boolean paramBoolean2, ClassLoader paramClassLoader) {
/*  246 */     this(paramURL, paramBoolean1, paramInt, paramBoolean2);
/*  247 */     this.classLoader = paramClassLoader;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void loadConfig(URL paramURL) {
/*  256 */     InputStream inputStream = null;
/*  257 */     StreamTokenizer streamTokenizer = null;
/*  258 */     String str = this.currentFileName;
/*      */     
/*  260 */     this.currentFileName = paramURL.toString();
/*      */     try {
/*  262 */       inputStream = paramURL.openStream();
/*  263 */       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
/*  264 */       streamTokenizer = new StreamTokenizer(bufferedReader);
/*      */     }
/*  266 */     catch (IOException iOException) {
/*  267 */       throw new IllegalArgumentException(iOException + "\nUnable to open " + this.currentFileName);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  286 */     streamTokenizer.ordinaryChar(47);
/*  287 */     streamTokenizer.wordChars(95, 95);
/*  288 */     streamTokenizer.wordChars(36, 36);
/*  289 */     streamTokenizer.wordChars(123, 125);
/*  290 */     streamTokenizer.slashSlashComments(true);
/*  291 */     streamTokenizer.slashStarComments(true);
/*      */ 
/*      */     
/*  294 */     ConfigSexpression configSexpression = new ConfigSexpression();
/*      */ 
/*      */ 
/*      */     
/*  298 */     while (configSexpression.parseAndEval(this, streamTokenizer, false) != Boolean.FALSE);
/*      */ 
/*      */     
/*      */     try {
/*  302 */       inputStream.close();
/*      */     }
/*  304 */     catch (IOException iOException) {
/*  305 */       throw new IllegalArgumentException(iOException + "\nUnable to close " + this.currentFileName);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  310 */     this.currentFileName = str;
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
/*      */   void evaluateCommand(ArrayList paramArrayList, int paramInt) {
/*      */     String str;
/*      */     URL uRL;
/*      */     ConfigObject configObject;
/*  325 */     ConfigCommand configCommand = new ConfigCommand(paramArrayList, this.currentFileName, paramInt);
/*      */ 
/*      */     
/*  328 */     switch (configCommand.type) {
/*      */       case 0:
/*  330 */         configObject = createConfigObject(configCommand);
/*  331 */         addConfigObject(configObject);
/*      */       
/*      */       case 3:
/*  334 */         configObject = createConfigAlias(configCommand);
/*  335 */         addConfigObject(configObject);
/*      */       
/*      */       case 2:
/*  338 */         configObject = findConfigObject(configCommand.baseName, configCommand.instanceName);
/*  339 */         configObject.setProperty(configCommand);
/*      */       
/*      */       case 5:
/*  342 */         if (!(configCommand.argv[1] instanceof String)) {
/*  343 */           throw new IllegalArgumentException("Include file must be a URL string");
/*      */         }
/*      */         
/*  346 */         uRL = null;
/*  347 */         str = (String)configCommand.argv[1];
/*      */         try {
/*  349 */           uRL = new URL(str);
/*      */         }
/*  351 */         catch (MalformedURLException malformedURLException) {
/*  352 */           throw new IllegalArgumentException(malformedURLException.toString());
/*      */         } 
/*  354 */         loadConfig(uRL);
/*      */       
/*      */       case 6:
/*      */         return;
/*      */     } 
/*  359 */     throw new IllegalArgumentException("Unknown command \"" + configCommand.commandName + "\"");
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
/*      */   private ConfigObject createConfigObject(ConfigCommand paramConfigCommand) {
/*  379 */     Class clazz = null;
/*  380 */     ConfigObject configObject = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  387 */       clazz = Class.forName("com.sun.j3d.utils.universe.Config" + paramConfigCommand.baseName);
/*      */     
/*      */     }
/*  390 */     catch (ClassNotFoundException classNotFoundException) {
/*  391 */       throw new IllegalArgumentException("\"" + paramConfigCommand.baseName + "\"" + " is not a configurable object; ignoring command");
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  396 */       configObject = (ConfigObject)clazz.newInstance();
/*      */     }
/*  398 */     catch (IllegalAccessException illegalAccessException) {
/*  399 */       System.out.println(illegalAccessException);
/*  400 */       throw new IllegalArgumentException("Ignoring command");
/*      */     }
/*  402 */     catch (InstantiationException instantiationException) {
/*  403 */       System.out.println(instantiationException);
/*  404 */       throw new IllegalArgumentException("Ignoring command");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  410 */     for (byte b = 2; b < paramConfigCommand.argc; b++) {
/*  411 */       if (paramConfigCommand.argv[b] instanceof String && ((String)paramConfigCommand.argv[b]).equals("Alias"))
/*      */       {
/*  413 */         if (b == paramConfigCommand.argc - 2 && paramConfigCommand.argv[b + 1] instanceof String) {
/*  414 */           addConfigObject(new ConfigAlias(paramConfigCommand.baseName, (String)paramConfigCommand.argv[b + 1], configObject));
/*      */ 
/*      */           
/*  417 */           paramConfigCommand.argc -= 2;
/*      */         } else {
/*      */           
/*  420 */           throw new IllegalArgumentException("The alias name must be a string and must be the last command argument");
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  428 */     configObject.baseName = paramConfigCommand.baseName;
/*  429 */     configObject.instanceName = paramConfigCommand.instanceName;
/*  430 */     configObject.creatingCommand = paramConfigCommand;
/*  431 */     configObject.configContainer = this;
/*      */ 
/*      */     
/*  434 */     configObject.setClassLoader(this.classLoader);
/*  435 */     configObject.initialize(paramConfigCommand);
/*  436 */     return configObject;
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
/*      */   private ConfigObject createConfigAlias(ConfigCommand paramConfigCommand) {
/*  451 */     if (paramConfigCommand.argc != 3 || !(paramConfigCommand.argv[2] instanceof String)) {
/*  452 */       throw new IllegalArgumentException("Command \"" + paramConfigCommand.commandName + "\" requires an instance name as second argument");
/*      */     }
/*      */ 
/*      */     
/*  456 */     ConfigObject configObject = findConfigObject(paramConfigCommand.baseName, (String)paramConfigCommand.argv[2]);
/*  457 */     return new ConfigAlias(paramConfigCommand.baseName, paramConfigCommand.instanceName, configObject);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class ConfigAlias
/*      */     extends ConfigObject
/*      */   {
/*      */     ConfigAlias(String param1String1, String param1String2, ConfigObject param1ConfigObject) {
/*  468 */       this.baseName = param1String1;
/*  469 */       this.instanceName = param1String2;
/*  470 */       this.isAlias = true;
/*  471 */       this.original = param1ConfigObject;
/*  472 */       param1ConfigObject.aliases.add(param1String2);
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
/*      */   private void addConfigObject(ConfigObject paramConfigObject) {
/*  485 */     ArrayList arrayList = (ArrayList)this.baseNameMap.get(paramConfigObject.baseName);
/*  486 */     if (arrayList == null) {
/*  487 */       arrayList = new ArrayList();
/*  488 */       this.baseNameMap.put(paramConfigObject.baseName, arrayList);
/*      */     } 
/*      */ 
/*      */     
/*  492 */     for (byte b = 0; b < arrayList.size(); b++) {
/*  493 */       ConfigObject configObject = (ConfigObject)arrayList.get(b);
/*  494 */       if (configObject.instanceName.equals(paramConfigObject.instanceName)) {
/*      */         
/*  496 */         String str = paramConfigObject.baseName;
/*  497 */         if (str.equals("Screen")) str = "Screen or Window"; 
/*  498 */         throw new IllegalArgumentException("Duplicate " + str + " instance name \"" + paramConfigObject.instanceName + "\" ignored");
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  504 */     arrayList.add(paramConfigObject);
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
/*      */   ConfigObject findConfigObject(String paramString1, String paramString2) {
/*  520 */     ArrayList arrayList = (ArrayList)this.baseNameMap.get(paramString1);
/*  521 */     if (arrayList != null) {
/*  522 */       for (byte b = 0; b < arrayList.size(); b++) {
/*  523 */         ConfigObject configObject = (ConfigObject)arrayList.get(b);
/*      */         
/*  525 */         if (configObject.instanceName.equals(paramString2)) {
/*  526 */           if (configObject.isAlias) {
/*  527 */             return configObject.original;
/*      */           }
/*  529 */           return configObject;
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  535 */     if (paramString1.equals("Screen")) paramString1 = "Screen or Window"; 
/*  536 */     throw new IllegalArgumentException(paramString1 + " \"" + paramString2 + "\" not found");
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
/*  550 */   Collection findConfigObjects(String paramString) { return findConfigObjects(paramString, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Collection findConfigObjects(String paramString, boolean paramBoolean) {
/*  566 */     ArrayList arrayList = (ArrayList)this.baseNameMap.get(paramString);
/*  567 */     if (arrayList == null || arrayList.size() == 0) {
/*  568 */       return null;
/*      */     }
/*      */     
/*  571 */     if (paramBoolean) {
/*  572 */       ArrayList arrayList1 = new ArrayList();
/*  573 */       for (byte b = 0; b < arrayList.size(); b++) {
/*  574 */         ConfigObject configObject = (ConfigObject)arrayList.get(b);
/*      */         
/*  576 */         if (!configObject.isAlias) {
/*  577 */           arrayList1.add(configObject);
/*      */         }
/*      */       } 
/*  580 */       return arrayList1;
/*      */     } 
/*      */     
/*  583 */     return arrayList;
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
/*      */   private ConfigObject findConfigObject(String paramString, ConfigCommand paramConfigCommand) {
/*  599 */     if (paramConfigCommand.argc != 2 || !(paramConfigCommand.argv[1] instanceof String)) {
/*  600 */       throw new IllegalArgumentException(ConfigObject.errorMessage(paramConfigCommand, "Parameter must be a single string"));
/*      */     }
/*      */     
/*      */     try {
/*  604 */       return findConfigObject(paramString, (String)paramConfigCommand.argv[1]);
/*      */     }
/*  606 */     catch (IllegalArgumentException illegalArgumentException) {
/*  607 */       throw new IllegalArgumentException(ConfigObject.errorMessage(paramConfigCommand, illegalArgumentException.getMessage()));
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
/*      */   Object evaluateBuiltIn(ConfigCommand paramConfigCommand) {
/*  621 */     int i = paramConfigCommand.argc;
/*  622 */     Object[] arrayOfObject = paramConfigCommand.argv;
/*      */     
/*  624 */     if (paramConfigCommand.commandName.equals("ConfigContainer"))
/*      */     {
/*  626 */       return this;
/*      */     }
/*  628 */     if (paramConfigCommand.commandName.equals("Canvas3D"))
/*      */     {
/*  630 */       return ((ConfigScreen)findConfigObject("Screen", paramConfigCommand)).j3dCanvas;
/*      */     }
/*  632 */     if (this.baseNameMap.get(paramConfigCommand.commandName) != null)
/*      */     {
/*      */       
/*  635 */       return (findConfigObject(paramConfigCommand.commandName, paramConfigCommand)).targetObject;
/*      */     }
/*      */ 
/*      */     
/*  639 */     throw new IllegalArgumentException(ConfigObject.errorMessage(paramConfigCommand, "Unknown built-in command \"" + paramConfigCommand.commandName + "\""));
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
/*      */   private void processConfig(boolean paramBoolean1, int paramInt, boolean paramBoolean2) {
/*  660 */     this.setVisible = paramBoolean1;
/*  661 */     this.transformCount = paramInt;
/*      */     
/*  663 */     Collection collection1 = findConfigObjects("PhysicalBody");
/*  664 */     if (collection1 != null) {
/*  665 */       processPhysicalBodies(collection1);
/*      */     }
/*      */     
/*  668 */     Collection collection3 = findConfigObjects("PhysicalEnvironment");
/*  669 */     if (collection3 != null) {
/*  670 */       processPhysicalEnvironments(collection3);
/*      */     }
/*      */     
/*  673 */     collection1 = findConfigObjects("View");
/*  674 */     if (collection1 != null) {
/*  675 */       processViews(collection1, paramBoolean1);
/*      */     }
/*      */     
/*  678 */     collection1 = findConfigObjects("Device");
/*  679 */     Collection collection2 = findConfigObjects("Sensor");
/*  680 */     if (collection1 != null) {
/*  681 */       processDevices(collection1, collection2, collection3);
/*      */     }
/*      */     
/*  684 */     Collection collection4 = findConfigObjects("ViewPlatform");
/*  685 */     if (collection4 != null) {
/*  686 */       processViewPlatforms(collection4, paramInt);
/*      */     }
/*      */     
/*  689 */     collection1 = findConfigObjects("ViewPlatformBehavior");
/*  690 */     if (collection1 != null) {
/*  691 */       processViewPlatformBehaviors(collection1, collection4, paramBoolean2);
/*      */     }
/*      */     
/*  694 */     collection1 = findConfigObjects("Object");
/*  695 */     if (collection1 != null) {
/*  696 */       processGenericObjects(collection1);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void processPhysicalEnvironments(Collection paramCollection) {
/*  703 */     Iterator iterator = paramCollection.iterator();
/*  704 */     while (iterator.hasNext()) {
/*  705 */       ConfigPhysicalEnvironment configPhysicalEnvironment = (ConfigPhysicalEnvironment)iterator.next();
/*  706 */       configPhysicalEnvironment.targetObject = configPhysicalEnvironment.createJ3dPhysicalEnvironment();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void processPhysicalBodies(Collection paramCollection) {
/*  712 */     Iterator iterator = paramCollection.iterator();
/*  713 */     while (iterator.hasNext()) {
/*  714 */       ConfigPhysicalBody configPhysicalBody = (ConfigPhysicalBody)iterator.next();
/*  715 */       configPhysicalBody.targetObject = configPhysicalBody.createJ3dPhysicalBody();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processViews(Collection paramCollection, boolean paramBoolean) {
/*  723 */     Iterator iterator = paramCollection.iterator();
/*  724 */     while (iterator.hasNext()) {
/*  725 */       ConfigView configView = (ConfigView)iterator.next();
/*  726 */       configView.targetObject = configView.createViewer(paramBoolean);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processDevices(Collection paramCollection1, Collection paramCollection2, Collection paramCollection3) {
/*  734 */     ConfigDevice configDevice = null;
/*  735 */     Iterator iterator = paramCollection1.iterator();
/*  736 */     while (iterator.hasNext()) {
/*  737 */       configDevice = (ConfigDevice)iterator.next();
/*  738 */       configDevice.targetObject = configDevice.createInputDevice();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  744 */     iterator = paramCollection1.iterator();
/*  745 */     for (; iterator.hasNext(); ((ConfigDevice)iterator.next()).processProperties());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  750 */     iterator = paramCollection1.iterator();
/*  751 */     while (iterator.hasNext()) {
/*  752 */       configDevice = (ConfigDevice)iterator.next();
/*  753 */       if (!configDevice.j3dInputDevice.initialize()) {
/*  754 */         throw new RuntimeException(configDevice.errorMessage(configDevice.creatingCommand, "could not initialize device \"" + configDevice.instanceName + "\""));
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  762 */     if (paramCollection2 != null) {
/*  763 */       iterator = paramCollection2.iterator();
/*  764 */       while (iterator.hasNext()) {
/*  765 */         ConfigSensor configSensor = (ConfigSensor)iterator.next();
/*  766 */         configSensor.configureSensor();
/*  767 */         configSensor.targetObject = configSensor.j3dSensor;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  772 */     if (paramCollection3 != null) {
/*  773 */       iterator = paramCollection3.iterator();
/*  774 */       while (iterator.hasNext()) {
/*  775 */         ((ConfigPhysicalEnvironment)iterator.next()).processDevices();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void processViewPlatforms(Collection paramCollection, int paramInt) {
/*  781 */     Iterator iterator = paramCollection.iterator();
/*  782 */     while (iterator.hasNext()) {
/*  783 */       ConfigViewPlatform configViewPlatform = (ConfigViewPlatform)iterator.next();
/*  784 */       configViewPlatform.targetObject = configViewPlatform.createViewingPlatform(paramInt);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processViewPlatformBehaviors(Collection paramCollection1, Collection paramCollection2, boolean paramBoolean) {
/*  792 */     Iterator iterator = paramCollection1.iterator();
/*  793 */     while (iterator.hasNext()) {
/*  794 */       ConfigViewPlatformBehavior configViewPlatformBehavior = (ConfigViewPlatformBehavior)iterator.next();
/*      */       
/*  796 */       configViewPlatformBehavior.targetObject = configViewPlatformBehavior.createViewPlatformBehavior();
/*      */     } 
/*      */ 
/*      */     
/*  800 */     iterator = paramCollection1.iterator();
/*  801 */     while (iterator.hasNext()) {
/*  802 */       ((ConfigViewPlatformBehavior)iterator.next()).processProperties();
/*      */     }
/*      */     
/*  805 */     if (paramBoolean && paramCollection2 != null) {
/*  806 */       iterator = paramCollection2.iterator();
/*  807 */       while (iterator.hasNext()) {
/*  808 */         ((ConfigViewPlatform)iterator.next()).processBehavior();
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void processGenericObjects(Collection paramCollection) {
/*  814 */     Iterator iterator = paramCollection.iterator();
/*  815 */     while (iterator.hasNext()) {
/*  816 */       ConfigObject configObject = (ConfigObject)iterator.next();
/*  817 */       configObject.targetObject = configObject.createTargetObject();
/*      */     } 
/*      */ 
/*      */     
/*  821 */     iterator = paramCollection.iterator();
/*  822 */     for (; iterator.hasNext(); ((ConfigObject)iterator.next()).processProperties());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private ReadOnlySet createSet(String paramString) {
/*  828 */     Collection collection = findConfigObjects(paramString, true);
/*  829 */     if (collection == null || collection.size() == 0) {
/*  830 */       return null;
/*      */     }
/*  832 */     Iterator iterator = collection.iterator();
/*  833 */     ArrayList arrayList = new ArrayList();
/*  834 */     for (; iterator.hasNext(); arrayList.add(((ConfigObject)iterator.next()).targetObject));
/*      */     
/*  836 */     return new ReadOnlySet(arrayList);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private ReadOnlyMap createMap(String paramString) {
/*  842 */     Collection collection = findConfigObjects(paramString, false);
/*  843 */     if (collection == null || collection.size() == 0) {
/*  844 */       return null;
/*      */     }
/*  846 */     Iterator iterator = collection.iterator();
/*  847 */     HashMap hashMap = new HashMap();
/*  848 */     while (iterator.hasNext()) {
/*  849 */       ConfigObject configObject = (ConfigObject)iterator.next();
/*  850 */       if (configObject.isAlias) {
/*  851 */         hashMap.put(configObject.instanceName, configObject.original.targetObject); continue;
/*      */       } 
/*  853 */       hashMap.put(configObject.instanceName, configObject.targetObject);
/*      */     } 
/*      */     
/*  856 */     return new ReadOnlyMap(hashMap);
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
/*      */   public Set getPhysicalBodies() {
/*  878 */     if (this.bodies != null) return this.bodies; 
/*  879 */     this.bodies = createSet("PhysicalBody");
/*  880 */     return this.bodies;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map getNamedPhysicalBodies() {
/*  891 */     if (this.bodyMap != null) return this.bodyMap; 
/*  892 */     this.bodyMap = createMap("PhysicalBody");
/*  893 */     return this.bodyMap;
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
/*      */   public Set getPhysicalEnvironments() {
/*  915 */     if (this.environments != null) return this.environments; 
/*  916 */     this.environments = createSet("PhysicalEnvironment");
/*  917 */     return this.environments;
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
/*      */   public Map getNamedPhysicalEnvironments() {
/*  929 */     if (this.environmentMap != null) return this.environmentMap; 
/*  930 */     this.environmentMap = createMap("PhysicalEnvironment");
/*  931 */     return this.environmentMap;
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
/*      */   public Set getViewers() {
/*  955 */     if (this.viewers != null) return this.viewers; 
/*  956 */     this.viewers = createSet("View");
/*  957 */     return this.viewers;
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
/*      */   public Map getNamedViewers() {
/*  971 */     if (this.viewerMap != null) return this.viewerMap; 
/*  972 */     this.viewerMap = createMap("View");
/*  973 */     return this.viewerMap;
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
/*      */   public Set getInputDevices() {
/* 1008 */     if (this.devices != null) return this.devices; 
/* 1009 */     this.devices = createSet("Device");
/* 1010 */     return this.devices;
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
/*      */   public Map getNamedInputDevices() {
/* 1024 */     if (this.deviceMap != null) return this.deviceMap; 
/* 1025 */     this.deviceMap = createMap("Device");
/* 1026 */     return this.deviceMap;
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
/*      */   public Set getSensors() {
/* 1060 */     if (this.sensors != null) return this.sensors; 
/* 1061 */     this.sensors = createSet("Sensor");
/* 1062 */     return this.sensors;
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
/*      */   public Map getNamedSensors() {
/* 1079 */     if (this.sensorMap != null) return this.sensorMap; 
/* 1080 */     this.sensorMap = createMap("Sensor");
/* 1081 */     return this.sensorMap;
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
/*      */   public Set getViewingPlatforms() {
/* 1106 */     if (this.platforms != null) return this.platforms; 
/* 1107 */     this.platforms = createSet("ViewPlatform");
/* 1108 */     return this.platforms;
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
/*      */   public Map getNamedViewingPlatforms() {
/* 1122 */     if (this.platformMap != null) return this.platformMap; 
/* 1123 */     this.platformMap = createMap("ViewPlatform");
/* 1124 */     return this.platformMap;
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
/*      */   
/*      */   public Set getViewPlatformBehaviors() {
/* 1169 */     if (this.behaviors != null) return this.behaviors; 
/* 1170 */     this.behaviors = createSet("ViewPlatformBehavior");
/* 1171 */     return this.behaviors;
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
/*      */   public Map getNamedViewPlatformBehaviors() {
/* 1191 */     if (this.behaviorMap != null) return this.behaviorMap; 
/* 1192 */     this.behaviorMap = createMap("ViewPlatformBehavior");
/* 1193 */     return this.behaviorMap;
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
/*      */   public Map getNamedCanvases(String paramString) {
/* 1222 */     Map map = (Map)this.viewCanvasMap.get(paramString);
/* 1223 */     if (map != null) return map;
/*      */     
/* 1225 */     map = new HashMap();
/* 1226 */     ConfigView configView = (ConfigView)findConfigObject("View", paramString);
/* 1227 */     Iterator iterator = configView.screens.iterator();
/* 1228 */     while (iterator.hasNext()) {
/* 1229 */       ConfigScreen configScreen = (ConfigScreen)iterator.next();
/* 1230 */       map.put(configScreen.instanceName, configScreen.j3dCanvas);
/*      */ 
/*      */       
/* 1233 */       Iterator iterator1 = configScreen.aliases.iterator();
/* 1234 */       for (; iterator1.hasNext(); map.put(iterator1.next(), configScreen.j3dCanvas));
/*      */     } 
/* 1236 */     map = new ReadOnlyMap(map);
/* 1237 */     this.viewCanvasMap.put(paramString, map);
/* 1238 */     return map;
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
/*      */   public Set getGenericObjects() {
/* 1269 */     if (this.genericObjects != null) return this.genericObjects; 
/* 1270 */     this.genericObjects = createSet("Object");
/* 1271 */     return this.genericObjects;
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
/*      */   public Map getNamedGenericObjects() {
/* 1284 */     if (this.genericObjectMap != null) return this.genericObjectMap; 
/* 1285 */     this.genericObjectMap = createMap("Object");
/* 1286 */     return this.genericObjectMap;
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
/* 1297 */   public int getViewPlatformTransformCount() { return this.transformCount; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1310 */   public boolean getViewerVisibility() { return this.setVisible; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/* 1319 */     Iterator iterator = this.baseNameMap.values().iterator();
/* 1320 */     for (; iterator.hasNext(); ((Collection)iterator.next()).clear());
/* 1321 */     this.baseNameMap.clear();
/*      */ 
/*      */     
/* 1324 */     iterator = this.viewCanvasMap.values().iterator();
/* 1325 */     for (; iterator.hasNext(); ((ReadOnlyMap)iterator.next()).map.clear());
/* 1326 */     this.viewCanvasMap.clear();
/*      */ 
/*      */     
/* 1329 */     this.currentFileName = null;
/*      */ 
/*      */     
/* 1332 */     if (this.bodies != null) {
/* 1333 */       this.bodies.collection.clear();
/* 1334 */       this.bodies = null;
/*      */     } 
/* 1336 */     if (this.environments != null) {
/* 1337 */       this.environments.collection.clear();
/* 1338 */       this.environments = null;
/*      */     } 
/* 1340 */     if (this.devices != null) {
/* 1341 */       this.devices.collection.clear();
/* 1342 */       this.devices = null;
/*      */     } 
/* 1344 */     if (this.sensors != null) {
/* 1345 */       this.sensors.collection.clear();
/* 1346 */       this.sensors = null;
/*      */     } 
/* 1348 */     if (this.behaviors != null) {
/* 1349 */       this.behaviors.collection.clear();
/* 1350 */       this.behaviors = null;
/*      */     } 
/* 1352 */     if (this.platforms != null) {
/* 1353 */       this.platforms.collection.clear();
/* 1354 */       this.platforms = null;
/*      */     } 
/* 1356 */     if (this.viewers != null) {
/* 1357 */       this.viewers.collection.clear();
/* 1358 */       this.viewers = null;
/*      */     } 
/* 1360 */     if (this.genericObjects != null) {
/* 1361 */       this.genericObjects.collection.clear();
/* 1362 */       this.genericObjects = null;
/*      */     } 
/*      */ 
/*      */     
/* 1366 */     if (this.bodyMap != null) {
/* 1367 */       this.bodyMap.map.clear();
/* 1368 */       this.bodyMap = null;
/*      */     } 
/* 1370 */     if (this.environmentMap != null) {
/* 1371 */       this.environmentMap.map.clear();
/* 1372 */       this.environmentMap = null;
/*      */     } 
/* 1374 */     if (this.deviceMap != null) {
/* 1375 */       this.deviceMap.map.clear();
/* 1376 */       this.deviceMap = null;
/*      */     } 
/* 1378 */     if (this.sensorMap != null) {
/* 1379 */       this.sensorMap.map.clear();
/* 1380 */       this.sensorMap = null;
/*      */     } 
/* 1382 */     if (this.behaviorMap != null) {
/* 1383 */       this.behaviorMap.map.clear();
/* 1384 */       this.behaviorMap = null;
/*      */     } 
/* 1386 */     if (this.platformMap != null) {
/* 1387 */       this.platformMap.map.clear();
/* 1388 */       this.platformMap = null;
/*      */     } 
/* 1390 */     if (this.viewerMap != null) {
/* 1391 */       this.viewerMap.map.clear();
/* 1392 */       this.viewerMap = null;
/*      */     } 
/* 1394 */     if (this.genericObjectMap != null) {
/* 1395 */       this.genericObjectMap.map.clear();
/* 1396 */       this.genericObjectMap = null;
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
/* 1415 */   public static URL getConfigURL() { return getConfigURL(null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static URL getConfigURL(String paramString) {
/* 1435 */     URL uRL = null;
/* 1436 */     String str1 = null;
/* 1437 */     final String defProp = paramString;
/*      */     
/* 1439 */     str1 = (String)AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public Object run() {
/* 1442 */             return System.getProperty("j3d.configURL", defProp);
/*      */           }
/*      */         });
/*      */     
/* 1446 */     if (str1 == null) {
/* 1447 */       return null;
/*      */     }
/*      */     try {
/* 1450 */       uRL = new URL(str1);
/*      */     }
/* 1452 */     catch (MalformedURLException malformedURLException) {
/* 1453 */       System.out.println(malformedURLException);
/* 1454 */       return null;
/*      */     } 
/* 1456 */     return uRL;
/*      */   }
/*      */   
/*      */   private static class ReadOnlyMap extends AbstractMap { HashMap map;
/*      */     
/*      */     ReadOnlyMap(Map param1Map) {
/* 1462 */       this.entrySet = null;
/*      */ 
/*      */       
/* 1465 */       this.map = new HashMap(param1Map);
/*      */     }
/*      */     
/*      */     private Set entrySet;
/*      */     
/* 1470 */     public Object get(Object param1Object) { return this.map.get(param1Object); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1475 */     public boolean containsKey(Object param1Object) { return this.map.containsKey(param1Object); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1480 */     public boolean containsValue(Object param1Object) { return this.map.containsValue(param1Object); }
/*      */ 
/*      */     
/*      */     public Set entrySet() {
/* 1484 */       if (this.entrySet == null) {
/* 1485 */         this.entrySet = new ConfigContainer.ReadOnlySet(this.map.entrySet());
/*      */       }
/* 1487 */       return this.entrySet;
/*      */     } }
/*      */   
/*      */   private static class ReadOnlySet extends AbstractSet {
/*      */     Collection collection;
/*      */     
/*      */     ReadOnlySet(Collection param1Collection) {
/* 1494 */       this.collection = null;
/*      */ 
/*      */       
/* 1497 */       this.collection = param1Collection;
/*      */     }
/*      */ 
/*      */     
/* 1501 */     public int size() { return this.collection.size(); }
/*      */ 
/*      */ 
/*      */     
/* 1505 */     public Iterator iterator() { return new ConfigContainer.ReadOnlyIterator(this.collection.iterator()); }
/*      */   }
/*      */ 
/*      */   
/*      */   private static class ReadOnlyIterator
/*      */     implements Iterator
/*      */   {
/*      */     private Iterator i;
/*      */     
/* 1514 */     ReadOnlyIterator(Iterator param1Iterator) { this.i = param1Iterator; }
/*      */ 
/*      */ 
/*      */     
/* 1518 */     public boolean hasNext() { return this.i.hasNext(); }
/*      */ 
/*      */ 
/*      */     
/* 1522 */     public Object next() { return this.i.next(); }
/*      */ 
/*      */ 
/*      */     
/* 1526 */     public void remove() { throw new UnsupportedOperationException(); }
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\util\\universe\ConfigContainer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */