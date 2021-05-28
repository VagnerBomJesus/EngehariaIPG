/*     */ package com.sun.j3d.utils.universe;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ConfigObject
/*     */ {
/*  77 */   String baseName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   String instanceName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   Object targetObject = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   String targetClassName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   Class targetClass = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   List properties = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   ConfigContainer configContainer = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   ConfigCommand creatingCommand = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isAlias = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   ConfigObject original = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   List aliases = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassLoader classLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   void setClassLoader(ClassLoader paramClassLoader) { this.classLoader = paramClassLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initialize(ConfigCommand paramConfigCommand) {
/* 166 */     if (paramConfigCommand.argc != 3) {
/* 167 */       syntaxError("Wrong number of arguments to " + paramConfigCommand.commandName);
/*     */     }
/*     */     
/* 170 */     if (!isName(paramConfigCommand.argv[1])) {
/* 171 */       syntaxError("The first argument to " + paramConfigCommand.commandName + " must be the instance name");
/*     */     }
/*     */ 
/*     */     
/* 175 */     if (!isName(paramConfigCommand.argv[2])) {
/* 176 */       syntaxError("The second argument to " + paramConfigCommand.commandName + " must be the class name");
/*     */     }
/*     */ 
/*     */     
/* 180 */     this.targetClassName = (String)paramConfigCommand.argv[2];
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
/*     */   protected void setProperty(ConfigCommand paramConfigCommand) {
/* 204 */     if (paramConfigCommand.argc < 4) {
/* 205 */       syntaxError("Wrong number of arguments to " + paramConfigCommand.commandName);
/*     */     }
/*     */     
/* 208 */     if (!isName(paramConfigCommand.argv[1])) {
/* 209 */       syntaxError("The first argument to " + paramConfigCommand.commandName + " must be the instance name");
/*     */     }
/*     */ 
/*     */     
/* 213 */     if (!isName(paramConfigCommand.argv[2])) {
/* 214 */       syntaxError("The second argument to " + paramConfigCommand.commandName + " must be the property name");
/*     */     }
/*     */ 
/*     */     
/* 218 */     this.properties.add(paramConfigCommand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object createTargetObject() {
/* 225 */     if (this.targetClassName == null) {
/* 226 */       return null;
/*     */     }
/* 228 */     this.targetClass = getClassForName(this.creatingCommand, this.targetClassName);
/* 229 */     this.targetObject = getNewInstance(this.creatingCommand, this.targetClass);
/*     */     
/* 231 */     return this.targetObject;
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
/*     */   protected Class getClassForName(ConfigCommand paramConfigCommand, String paramString) {
/*     */     try {
/* 249 */       return Class.forName(paramString, true, this.classLoader);
/*     */     
/*     */     }
/* 252 */     catch (ClassNotFoundException classNotFoundException) {
/* 253 */       throw new IllegalArgumentException(errorMessage(paramConfigCommand, "Class \"" + paramString + "\" not found"));
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
/*     */   protected Object getNewInstance(ConfigCommand paramConfigCommand, Class paramClass) {
/*     */     try {
/* 266 */       return paramClass.newInstance();
/*     */     }
/* 268 */     catch (IllegalAccessException illegalAccessException) {
/* 269 */       throw new IllegalArgumentException(errorMessage(paramConfigCommand, "Illegal access to object class"));
/*     */     
/*     */     }
/* 272 */     catch (InstantiationException instantiationException) {
/* 273 */       throw new IllegalArgumentException(errorMessage(paramConfigCommand, "Instantiation error for object class"));
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
/*     */   protected void processProperties() {
/* 285 */     evaluateProperties(this.targetClass, this.targetObject, this.properties);
/*     */ 
/*     */ 
/*     */     
/* 289 */     this.properties.clear();
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
/*     */   protected void evaluateProperties(Class paramClass, Object paramObject, List paramList) {
/* 304 */     Object[] arrayOfObject = new Object[1];
/*     */ 
/*     */     
/* 307 */     Class[] arrayOfClass = new Class[1];
/*     */ 
/*     */ 
/*     */     
/* 311 */     arrayOfClass[0] = arrayOfObject.getClass();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 316 */     for (byte b = 0; b < paramList.size(); b++) {
/* 317 */       ConfigCommand configCommand = (ConfigCommand)paramList.get(b);
/* 318 */       String str = (String)configCommand.argv[2];
/* 319 */       Object[] arrayOfObject1 = new Object[configCommand.argc - 3];
/*     */       
/* 321 */       for (b1 = 0; b1 < arrayOfObject1.length; b1++) {
/* 322 */         arrayOfObject1[b1] = configCommand.argv[b1 + 3];
/* 323 */         if (arrayOfObject1[b1] instanceof ConfigCommand) {
/*     */           
/* 325 */           ConfigCommand configCommand1 = (ConfigCommand)arrayOfObject1[b1];
/* 326 */           arrayOfObject1[b1] = this.configContainer.evaluateBuiltIn(configCommand1);
/*     */         } 
/*     */       } 
/*     */       
/* 330 */       arrayOfObject[0] = arrayOfObject1;
/*     */       
/*     */       try {
/* 333 */         Method method = paramClass.getMethod(str, arrayOfClass);
/*     */ 
/*     */         
/* 336 */         method.invoke(paramObject, arrayOfObject);
/*     */       }
/* 338 */       catch (NoSuchMethodException b1) {
/* 339 */         NoSuchMethodException noSuchMethodException; throw new IllegalArgumentException(errorMessage(configCommand, "Unknown property \"" + str + "\""));
/*     */ 
/*     */       
/*     */       }
/* 343 */       catch (IllegalAccessException b1) {
/* 344 */         IllegalAccessException illegalAccessException; throw new IllegalArgumentException(errorMessage(configCommand, "Illegal access to \"" + str + "\""));
/*     */ 
/*     */       
/*     */       }
/* 348 */       catch (InvocationTargetException b1) {
/* 349 */         InvocationTargetException invocationTargetException; throw new IllegalArgumentException(errorMessage(configCommand, invocationTargetException.getTargetException().getMessage()));
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
/*     */   
/* 363 */   protected void syntaxError(String paramString) { throw new IllegalArgumentException(paramString); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 371 */   static String errorMessage(ConfigCommand paramConfigCommand, String paramString) { return paramString + "\nat line " + paramConfigCommand.lineNumber + " in " + paramConfigCommand.fileName + "\n" + paramConfigCommand; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 383 */   protected boolean isName(Object paramObject) { return paramObject instanceof String; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\util\\universe\ConfigObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */