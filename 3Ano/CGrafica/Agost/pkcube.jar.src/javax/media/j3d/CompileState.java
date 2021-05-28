/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.security.AccessControlException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CompileState
/*     */ {
/*  39 */   HashMap knownAppearances = new HashMap();
/*  40 */   int numAppearances = 0;
/*  41 */   int numShared = 0;
/*  42 */   int numShapes = 0;
/*     */ 
/*     */   
/*  45 */   HashMap shapeLists = null;
/*  46 */   int numMergeSets = 0;
/*  47 */   int numMergeShapes = 0;
/*     */ 
/*     */   
/*     */   boolean compileVerbose = false;
/*     */ 
/*     */   
/*     */   static final int BOUNDS_READ = 1;
/*     */ 
/*     */   
/*     */   static final int GEOMETRY_READ = 2;
/*     */ 
/*     */   
/*     */   boolean keepTG = false;
/*     */ 
/*     */   
/*     */   boolean needNormalsTransform = false;
/*     */ 
/*     */   
/*  65 */   TransformGroupRetained staticTransform = null;
/*     */ 
/*     */   
/*  68 */   GroupRetained parentGroup = null;
/*     */ 
/*     */ 
/*     */   
/*  72 */   ArrayList transformGroupChildrenList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   ArrayList staticTransformObjects = new ArrayList(1);
/*     */   
/*  79 */   int numTransformGroups = 0;
/*  80 */   int numStaticTransformGroups = 0;
/*  81 */   int numMergedTransformGroups = 0;
/*  82 */   int numGroups = 0;
/*  83 */   int numMergedGroups = 0;
/*  84 */   int numShapesWSharedGeom = 0;
/*  85 */   int numShapesWStaticTG = 0;
/*  86 */   int numLinks = 0;
/*  87 */   int numSwitches = 0;
/*  88 */   int numOrderedGroups = 0;
/*  89 */   int numMorphs = 0;
/*     */   
/*     */   CompileState() {
/*     */     try {
/*  93 */       this.compileVerbose = Boolean.getBoolean("javax.media.j3d.compileVerbose");
/*  94 */     } catch (AccessControlException accessControlException) {
/*  95 */       this.compileVerbose = false;
/*     */     } 
/*  97 */     initShapeMerge();
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
/*     */   AppearanceRetained getAppearance(AppearanceRetained paramAppearanceRetained) {
/* 111 */     if (paramAppearanceRetained.map == this && 
/* 112 */       paramAppearanceRetained.mapAppearance != null) {
/* 113 */       this.numShared++;
/* 114 */       return paramAppearanceRetained.mapAppearance;
/*     */     } 
/*     */     
/*     */     AppearanceRetained appearanceRetained;
/*     */     
/* 119 */     if ((appearanceRetained = (AppearanceRetained)this.knownAppearances.get(paramAppearanceRetained)) != null) {
/* 120 */       this.numShared++;
/*     */     } else {
/*     */       
/* 123 */       this.knownAppearances.put(paramAppearanceRetained, paramAppearanceRetained);
/* 124 */       this.numAppearances++;
/* 125 */       this.numShared++;
/* 126 */       appearanceRetained = paramAppearanceRetained;
/*     */     } 
/*     */ 
/*     */     
/* 130 */     paramAppearanceRetained.map = this;
/* 131 */     paramAppearanceRetained.mapAppearance = appearanceRetained;
/*     */     
/* 133 */     return appearanceRetained;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 138 */   private void initShapeMerge() { this.shapeLists = new HashMap(); }
/*     */ 
/*     */ 
/*     */   
/*     */   void addShape(Shape3DRetained paramShape3DRetained) {
/* 143 */     if (this.parentGroup != null) {
/*     */       Vector vector;
/*     */       
/* 146 */       if ((vector = (Vector)this.shapeLists.get(paramShape3DRetained.appearance)) == null) {
/* 147 */         vector = new Vector();
/* 148 */         this.shapeLists.put(paramShape3DRetained.appearance, vector);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 154 */       GeometryRetained geometryRetained = null;
/* 155 */       byte b = 0;
/* 156 */       while (geometryRetained == null && b < paramShape3DRetained.geometryList.size()) {
/* 157 */         geometryRetained = (GeometryRetained)paramShape3DRetained.geometryList.get(b);
/* 158 */         b++;
/*     */       } 
/* 160 */       if (paramShape3DRetained.parent instanceof GroupRetained && ((GroupRetained)paramShape3DRetained.parent).isStaticChildren() && geometryRetained.geoType < 15) {
/* 161 */         vector.add(paramShape3DRetained);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void printStats() {
/* 169 */     System.err.println("numTransformGroups= " + this.numTransformGroups);
/* 170 */     System.err.println("numStaticTransformGroups= " + this.numStaticTransformGroups);
/* 171 */     System.err.println("numMergedTransformGroups= " + this.numMergedTransformGroups);
/* 172 */     System.err.println("numGroups= " + this.numGroups);
/* 173 */     System.err.println("numMergedGroups= " + this.numMergedGroups);
/* 174 */     System.err.println("numShapes= " + this.numShapes);
/* 175 */     System.err.println("numShapesWStaticTG= " + this.numShapesWStaticTG);
/* 176 */     System.err.println("numMergeShapes= " + this.numMergeShapes);
/* 177 */     System.err.println("numMergeSets= " + this.numMergeSets);
/* 178 */     System.err.println("numLinks= " + this.numLinks);
/* 179 */     System.err.println("numSwitches= " + this.numSwitches);
/* 180 */     System.err.println("numOrderedGroups= " + this.numOrderedGroups);
/* 181 */     System.err.println("numMorphs= " + this.numMorphs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void doShapeMerge() {
/* 187 */     if (this.shapeLists != null) {
/*     */ 
/*     */       
/* 190 */       Collection collection = this.shapeLists.values();
/* 191 */       Iterator iterator = collection.iterator();
/*     */ 
/*     */       
/* 194 */       byte b = 0;
/* 195 */       int i = 0;
/*     */       
/* 197 */       while (iterator.hasNext()) {
/* 198 */         Vector vector = (Vector)iterator.next();
/* 199 */         int j = vector.size();
/* 200 */         Shape3DRetained[] arrayOfShape3DRetained1 = new Shape3DRetained[j];
/* 201 */         vector.copyInto(arrayOfShape3DRetained1);
/* 202 */         Shape3DRetained[] arrayOfShape3DRetained2 = new Shape3DRetained[j];
/* 203 */         for (byte b1 = 0; b1 < j; b1++) {
/* 204 */           if (arrayOfShape3DRetained1[b1] != null) {
/*     */ 
/*     */             
/* 207 */             GeometryRetained geometryRetained = null;
/* 208 */             b = 0;
/*     */             
/* 210 */             while (geometryRetained == null && b < (arrayOfShape3DRetained1[b1]).geometryList.size()) {
/* 211 */               geometryRetained = (GeometryRetained)(arrayOfShape3DRetained1[b1]).geometryList.get(b);
/* 212 */               b++;
/*     */             } 
/*     */             
/* 215 */             if (geometryRetained != null && geometryRetained instanceof GeometryArrayRetained) {
/* 216 */               int k = 0;
/* 217 */               Shape3DRetained shape3DRetained = arrayOfShape3DRetained1[b1];
/* 218 */               GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)geometryRetained;
/*     */               
/* 220 */               arrayOfShape3DRetained2[k++] = shape3DRetained;
/*     */ 
/*     */               
/* 223 */               i = getCompileFlags(shape3DRetained);
/* 224 */               for (byte b2 = b1 + true; b2 < j; b2++) {
/* 225 */                 if (arrayOfShape3DRetained1[b2] != null) {
/*     */ 
/*     */                   
/* 228 */                   geometryRetained = null;
/* 229 */                   b = 0;
/*     */                   
/* 231 */                   while (geometryRetained == null && b < (arrayOfShape3DRetained1[b2]).geometryList.size()) {
/* 232 */                     geometryRetained = (GeometryRetained)(arrayOfShape3DRetained1[b2]).geometryList.get(b);
/* 233 */                     b++;
/*     */                   } 
/*     */ 
/*     */                   
/* 237 */                   if (geometryRetained != null && arrayOfShape3DRetained1[b2].isEquivalent(shape3DRetained) && geometryRetained.isEquivalenceClass(geometryArrayRetained) && ((GeometryArrayRetained)geometryRetained).vertexFormat == geometryArrayRetained.vertexFormat) {
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 242 */                     arrayOfShape3DRetained2[k++] = arrayOfShape3DRetained1[b2];
/*     */                     
/* 244 */                     i |= getCompileFlags(arrayOfShape3DRetained1[b2]);
/*     */ 
/*     */                     
/* 247 */                     arrayOfShape3DRetained1[b2] = null;
/*     */                   } 
/*     */                 } 
/* 250 */               }  if (k > 1) {
/*     */ 
/*     */ 
/*     */                 
/* 254 */                 GroupRetained groupRetained = (GroupRetained)(arrayOfShape3DRetained2[0]).parent;
/*     */                 
/* 256 */                 for (byte b3 = 0; b3 < k; b3++) {
/* 257 */                   Shape3DRetained shape3DRetained1 = arrayOfShape3DRetained2[b3];
/* 258 */                   boolean bool = false;
/* 259 */                   int m = groupRetained.numChildren();
/* 260 */                   for (byte b4 = 0; b4 < m && !bool; b4++) {
/* 261 */                     if ((groupRetained.getChild(b4)).retained == shape3DRetained1) {
/* 262 */                       bool = true;
/* 263 */                       groupRetained.removeChild(b4);
/*     */                     } 
/*     */                   } 
/* 266 */                   if (!bool) {
/* 267 */                     System.err.println("ShapeSet.add(): Can't remove shape from parent, can't find shape!");
/*     */                   }
/*     */                 } 
/*     */ 
/*     */ 
/*     */                 
/* 273 */                 shape3DRetained = new Shape3DCompileRetained(arrayOfShape3DRetained2, k, i);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 294 */                 shape3DRetained.setSource((arrayOfShape3DRetained2[0]).source);
/* 295 */                 this.numMergeSets++;
/* 296 */                 this.numMergeShapes += k;
/* 297 */                 this.parentGroup.addChild((Node)shape3DRetained.source);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 306 */     this.shapeLists.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int getCompileFlags(Shape3DRetained paramShape3DRetained) {
/* 312 */     byte b = 0;
/*     */ 
/*     */     
/* 315 */     if (paramShape3DRetained.allowIntersect() || paramShape3DRetained.source.getCapability(12) || (paramShape3DRetained.boundsAutoCompute && paramShape3DRetained.source.getCapability(3)))
/*     */     {
/*     */ 
/*     */       
/* 319 */       b |= 0x2;
/*     */     }
/* 321 */     return b;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\CompileState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */