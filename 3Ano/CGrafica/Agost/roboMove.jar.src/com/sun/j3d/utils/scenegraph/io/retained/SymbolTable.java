/*     */ package com.sun.j3d.utils.scenegraph.io.retained;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.NamedObjectException;
/*     */ import com.sun.j3d.utils.scenegraph.io.ObjectNotLoadedException;
/*     */ import com.sun.j3d.utils.scenegraph.io.SceneGraphObjectReferenceControl;
/*     */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.NodeComponentState;
/*     */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.SceneGraphObjectState;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Stack;
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
/*     */ public class SymbolTable
/*     */   implements SceneGraphObjectReferenceControl
/*     */ {
/*     */   private int nodeID;
/*     */   private HashMap j3dNodeIndex;
/*     */   private ArrayList nodeIDIndex;
/*     */   private HashMap danglingReferences;
/*     */   private Stack unsavedNodeComponentsStack;
/*     */   private LinkedList sharedNodes;
/*     */   private HashMap namedObjects;
/*     */   private ArrayList branchGraphs;
/*     */   private ArrayList branchGraphDependencies;
/*     */   private Controller control;
/*     */   private int currentBranchGraphID;
/*     */   private int nextBranchGraphID;
/*     */   
/*     */   public SymbolTable(Controller paramController) {
/*  73 */     this.nodeID = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     this.currentBranchGraphID = -1;
/*  86 */     this.nextBranchGraphID = 0;
/*     */ 
/*     */ 
/*     */     
/*  90 */     this.control = paramController;
/*  91 */     this.j3dNodeIndex = new HashMap();
/*  92 */     this.danglingReferences = new HashMap();
/*  93 */     this.nodeIDIndex = new ArrayList();
/*  94 */     this.nodeIDIndex.add(null);
/*  95 */     this.sharedNodes = new LinkedList();
/*  96 */     this.namedObjects = new HashMap();
/*  97 */     this.branchGraphs = new ArrayList();
/*  98 */     this.branchGraphDependencies = new ArrayList();
/*  99 */     this.unsavedNodeComponentsStack = new Stack();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkforDanglingReferences() {
/* 107 */     ListIterator listIterator = this.sharedNodes.listIterator();
/*     */     
/* 109 */     while (listIterator.hasNext()) {
/* 110 */       SymbolTableData symbolTableData = (SymbolTableData)listIterator.next();
/* 111 */       if (symbolTableData.branchGraphID == -3) {
/* 112 */         System.err.println("Warning : node " + symbolTableData.j3dNode + " is referenced but is not attached to a BranchGraph");
/* 113 */         System.err.println("Setting reference to null. This scene may not look correct when loaded");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeNullDependencies(HashSet paramHashSet) {
/* 123 */     Iterator iterator = paramHashSet.iterator();
/* 124 */     while (iterator.hasNext()) {
/* 125 */       SymbolTableData symbolTableData = (SymbolTableData)iterator.next();
/* 126 */       if (symbolTableData.branchGraphID == -3) {
/* 127 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTable(DataOutput paramDataOutput) throws IOException {
/* 134 */     checkforDanglingReferences();
/*     */     
/* 136 */     ListIterator listIterator = this.sharedNodes.listIterator();
/* 137 */     paramDataOutput.writeInt(this.sharedNodes.size());
/* 138 */     paramDataOutput.writeInt(this.nodeID);
/* 139 */     while (listIterator.hasNext()) {
/* 140 */       SymbolTableData symbolTableData = (SymbolTableData)listIterator.next();
/* 141 */       symbolTableData.writeObject(paramDataOutput);
/*     */     } 
/*     */ 
/*     */     
/* 145 */     String[] arrayOfString = getNames();
/* 146 */     paramDataOutput.writeInt(arrayOfString.length); byte b;
/* 147 */     for (b = 0; b < arrayOfString.length; b++) {
/* 148 */       paramDataOutput.writeUTF(arrayOfString[b]);
/* 149 */       SceneGraphObject sceneGraphObject = (SceneGraphObject)this.namedObjects.get(arrayOfString[b]);
/* 150 */       SymbolTableData symbolTableData = getSymbol(sceneGraphObject);
/* 151 */       if (symbolTableData != null) {
/* 152 */         paramDataOutput.writeInt(symbolTableData.nodeID);
/*     */       } else {
/* 154 */         paramDataOutput.writeInt(0);
/*     */       } 
/*     */     } 
/*     */     
/* 158 */     paramDataOutput.writeInt(this.branchGraphs.size());
/* 159 */     for (b = 0; b < this.branchGraphs.size(); b++) {
/* 160 */       ((SymbolTableData)this.branchGraphs.get(b)).writeObject(paramDataOutput);
/*     */     }
/* 162 */     for (b = 0; b < this.branchGraphDependencies.size(); b++) {
/* 163 */       HashSet hashSet = (HashSet)this.branchGraphDependencies.get(b);
/* 164 */       if (hashSet == null) {
/* 165 */         paramDataOutput.writeInt(0);
/*     */       } else {
/* 167 */         removeNullDependencies(hashSet);
/* 168 */         paramDataOutput.writeInt(hashSet.size());
/* 169 */         Iterator iterator = hashSet.iterator();
/* 170 */         while (iterator.hasNext()) {
/* 171 */           SymbolTableData symbolTableData = (SymbolTableData)iterator.next();
/* 172 */           paramDataOutput.writeInt(symbolTableData.nodeID);
/*     */         } 
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
/*     */   public void readTable(DataInput paramDataInput, boolean paramBoolean) throws IOException {
/* 186 */     int i = paramDataInput.readInt();
/* 187 */     this.nodeID = paramDataInput.readInt();
/* 188 */     nodeIDIndexEnsureCapacity(this.nodeID); byte b;
/* 189 */     for (b = 0; b < i; b++) {
/* 190 */       SymbolTableData symbolTableData = new SymbolTableData(0, null, null, -1);
/* 191 */       symbolTableData.readObject(paramDataInput);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 196 */       if (!paramBoolean) {
/* 197 */         this.sharedNodes.add(symbolTableData);
/* 198 */         this.nodeIDIndex.set(symbolTableData.nodeID, symbolTableData);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 203 */     i = paramDataInput.readInt();
/* 204 */     for (b = 0; b < i; b++) {
/* 205 */       String str = paramDataInput.readUTF();
/* 206 */       int j = paramDataInput.readInt();
/* 207 */       this.namedObjects.put(str, new Integer(j));
/*     */     } 
/*     */     
/* 210 */     i = paramDataInput.readInt();
/*     */     
/* 212 */     for (b = 0; b < i; b++) {
/* 213 */       this.branchGraphs.add(null);
/*     */     }
/*     */ 
/*     */     
/* 217 */     for (b = 0; b < i; b++) {
/* 218 */       SymbolTableData symbolTableData1 = new SymbolTableData(0, null, null, -1);
/* 219 */       symbolTableData1.readObject(paramDataInput);
/*     */       
/* 221 */       SymbolTableData symbolTableData2 = getSymbol(symbolTableData1.nodeID);
/*     */       
/* 223 */       if (symbolTableData2 == null) {
/* 224 */         symbolTableData2 = symbolTableData1;
/* 225 */         if (symbolTableData2.referenceCount > 1)
/* 226 */           this.sharedNodes.add(symbolTableData2); 
/* 227 */         this.nodeIDIndex.set(symbolTableData2.nodeID, symbolTableData2);
/*     */       } 
/*     */       
/* 230 */       this.branchGraphs.set(b, symbolTableData2);
/*     */     } 
/*     */ 
/*     */     
/* 234 */     for (b = 0; b < i; b++) {
/* 235 */       int j = paramDataInput.readInt();
/*     */       
/* 237 */       if (j == 0) {
/* 238 */         this.branchGraphDependencies.add(null);
/*     */       } else {
/* 240 */         HashSet hashSet = new HashSet();
/* 241 */         this.branchGraphDependencies.add(hashSet);
/* 242 */         for (byte b1 = 0; b1 < j; b1++) {
/* 243 */           hashSet.add(getSymbol(paramDataInput.readInt()));
/*     */         }
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
/*     */   public void setBranchGraphRoot(SymbolTableData paramSymbolTableData, long paramLong) {
/* 257 */     if (paramSymbolTableData.branchGraphID < 0) {
/* 258 */       paramSymbolTableData.branchGraphID = this.nextBranchGraphID++;
/*     */     }
/*     */     
/* 261 */     this.currentBranchGraphID = paramSymbolTableData.branchGraphID;
/* 262 */     for (int i = this.branchGraphs.size(); i < this.currentBranchGraphID + 1; i++) {
/* 263 */       this.branchGraphs.add(null);
/* 264 */       this.branchGraphDependencies.add(null);
/*     */     } 
/*     */     
/* 267 */     this.branchGraphs.set(this.currentBranchGraphID, paramSymbolTableData);
/* 268 */     paramSymbolTableData.branchGraphFilePointer = paramLong;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 273 */   public SymbolTableData getBranchGraphRoot(int paramInt) { return (SymbolTableData)this.branchGraphs.get(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 280 */   public void setBranchGraphID(SymbolTableData paramSymbolTableData) { paramSymbolTableData.branchGraphID = this.currentBranchGraphID; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getBranchGraphDependencies(int paramInt) {
/* 292 */     HashSet hashSet = (HashSet)this.branchGraphDependencies.get(paramInt);
/* 293 */     if (hashSet == null) {
/* 294 */       return new int[0];
/*     */     }
/* 296 */     int[] arrayOfInt = new int[hashSet.size()];
/* 297 */     Iterator iterator = hashSet.iterator();
/* 298 */     byte b = 0;
/* 299 */     while (iterator.hasNext()) {
/* 300 */       arrayOfInt[b++] = ((SymbolTableData)iterator.next()).branchGraphID;
/*     */     }
/* 302 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean branchGraphHasDependencies(int paramInt) {
/* 313 */     HashSet hashSet = (HashSet)this.branchGraphDependencies.get(paramInt);
/*     */     
/* 315 */     if (hashSet == null || hashSet.size() == 0) {
/* 316 */       return false;
/*     */     }
/* 318 */     return true;
/*     */   }
/*     */ 
/*     */   
/* 322 */   public int getBranchGraphCount() { return this.branchGraphs.size(); }
/*     */ 
/*     */   
/*     */   public long getBranchGraphFilePosition(int paramInt) {
/* 326 */     SymbolTableData symbolTableData = (SymbolTableData)this.branchGraphs.get(paramInt);
/* 327 */     return symbolTableData.branchGraphFilePointer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SymbolTableData createSymbol(SceneGraphObject paramSceneGraphObject) {
/* 338 */     SymbolTableData symbolTableData1 = (SymbolTableData)this.j3dNodeIndex.get(paramSceneGraphObject);
/*     */     
/* 340 */     SymbolTableData symbolTableData2 = (SymbolTableData)this.danglingReferences.get(paramSceneGraphObject);
/*     */ 
/*     */ 
/*     */     
/* 344 */     if (symbolTableData2 != null) {
/* 345 */       symbolTableData1 = symbolTableData2;
/* 346 */       symbolTableData1.branchGraphID = this.currentBranchGraphID;
/* 347 */       this.danglingReferences.remove(symbolTableData2);
/*     */     }
/* 349 */     else if (symbolTableData1 == null) {
/* 350 */       symbolTableData1 = new SymbolTableData(this.nodeID++, paramSceneGraphObject, null, this.currentBranchGraphID);
/* 351 */       this.j3dNodeIndex.put(paramSceneGraphObject, symbolTableData1);
/* 352 */       this.nodeIDIndex.add(symbolTableData1);
/* 353 */     } else if (symbolTableData1.j3dNode instanceof javax.media.j3d.Node) {
/* 354 */       throw new RuntimeException("Object already in Symbol table " + paramSceneGraphObject);
/*     */     } 
/*     */     
/* 357 */     return symbolTableData1;
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
/*     */   public SymbolTableData createSymbol(SceneGraphObjectState paramSceneGraphObjectState, SceneGraphObject paramSceneGraphObject, int paramInt) {
/* 369 */     SymbolTableData symbolTableData = (SymbolTableData)this.j3dNodeIndex.get(paramSceneGraphObject);
/*     */     
/* 371 */     if (symbolTableData == null) {
/* 372 */       nodeIDIndexEnsureCapacity(paramInt);
/* 373 */       symbolTableData = (SymbolTableData)this.nodeIDIndex.get(paramInt);
/* 374 */       if (symbolTableData == null) {
/* 375 */         symbolTableData = new SymbolTableData(paramInt, paramSceneGraphObject, paramSceneGraphObjectState, -2);
/* 376 */         this.j3dNodeIndex.put(paramSceneGraphObject, symbolTableData);
/* 377 */         this.nodeIDIndex.set(symbolTableData.getNodeID(), symbolTableData);
/* 378 */       } else if (symbolTableData.getJ3dNode() == null) {
/* 379 */         symbolTableData.j3dNode = paramSceneGraphObject;
/* 380 */         symbolTableData.nodeState = paramSceneGraphObjectState;
/* 381 */         this.j3dNodeIndex.put(paramSceneGraphObject, symbolTableData);
/*     */       } 
/*     */     } else {
/* 384 */       throw new SGIORuntimeException("Object already in Symbol table ");
/*     */     } 
/* 386 */     return symbolTableData;
/*     */   }
/*     */   
/*     */   private void nodeIDIndexEnsureCapacity(int paramInt) {
/* 390 */     this.nodeIDIndex.ensureCapacity(paramInt);
/* 391 */     int i = paramInt - this.nodeIDIndex.size();
/* 392 */     for (byte b = 0; b <= i; b++) {
/* 393 */       this.nodeIDIndex.add(null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SymbolTableData createDanglingSymbol(SceneGraphObject paramSceneGraphObject) {
/* 402 */     SymbolTableData symbolTableData = (SymbolTableData)this.j3dNodeIndex.get(paramSceneGraphObject);
/*     */     
/* 404 */     if (symbolTableData == null) {
/* 405 */       symbolTableData = new SymbolTableData(this.nodeID++, paramSceneGraphObject, null, -3);
/* 406 */       this.j3dNodeIndex.put(paramSceneGraphObject, symbolTableData);
/* 407 */       this.nodeIDIndex.add(symbolTableData);
/* 408 */       this.danglingReferences.put(paramSceneGraphObject, symbolTableData);
/* 409 */     } else if (symbolTableData.nodeState == null) {
/* 410 */       if (symbolTableData.referenceCount == 1)
/* 411 */         this.sharedNodes.add(symbolTableData); 
/* 412 */       symbolTableData.referenceCount++;
/*     */     } else {
/* 414 */       throw new SGIORuntimeException("Object already in Symbol table ");
/*     */     } 
/* 416 */     return symbolTableData;
/*     */   }
/*     */   
/*     */   private SymbolTableData createNodeComponentSymbol(SceneGraphObject paramSceneGraphObject) {
/* 420 */     SymbolTableData symbolTableData = new SymbolTableData(this.nodeID++, paramSceneGraphObject, null, this.currentBranchGraphID);
/* 421 */     symbolTableData.isNodeComponent = true;
/* 422 */     this.j3dNodeIndex.put(paramSceneGraphObject, symbolTableData);
/* 423 */     this.nodeIDIndex.add(symbolTableData);
/*     */     
/* 425 */     ((LinkedList)this.unsavedNodeComponentsStack.peek()).add(symbolTableData);
/*     */     
/* 427 */     this.control.createState(symbolTableData);
/*     */     
/* 429 */     return symbolTableData;
/*     */   }
/*     */ 
/*     */   
/* 433 */   public int getUnsavedNodeComponentsSize() { return ((LinkedList)this.unsavedNodeComponentsStack.peek()).size(); }
/*     */ 
/*     */ 
/*     */   
/* 437 */   public ListIterator getUnsavedNodeComponents() { return ((LinkedList)this.unsavedNodeComponentsStack.peek()).listIterator(0); }
/*     */ 
/*     */ 
/*     */   
/* 441 */   public void startUnsavedNodeComponentFrame() { this.unsavedNodeComponentsStack.push(new LinkedList()); }
/*     */ 
/*     */   
/*     */   public void endUnsavedNodeComponentFrame() {
/* 445 */     this.unsavedNodeComponentsStack.pop();
/* 446 */     confirmInterGraphDependency();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void confirmInterGraphDependency() {
/* 454 */     HashSet hashSet = (HashSet)this.branchGraphDependencies.get(this.currentBranchGraphID);
/* 455 */     if (hashSet == null) {
/*     */       return;
/*     */     }
/* 458 */     Iterator iterator = hashSet.iterator();
/* 459 */     while (iterator.hasNext()) {
/* 460 */       SymbolTableData symbolTableData = (SymbolTableData)iterator.next();
/* 461 */       if (symbolTableData.branchGraphID == this.currentBranchGraphID) {
/* 462 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addInterGraphDependency(SymbolTableData paramSymbolTableData) {
/* 473 */     HashSet hashSet = (HashSet)this.branchGraphDependencies.get(this.currentBranchGraphID);
/* 474 */     if (hashSet == null) {
/* 475 */       hashSet = new HashSet();
/* 476 */       this.branchGraphDependencies.set(this.currentBranchGraphID, hashSet);
/*     */     } 
/*     */     
/* 479 */     hashSet.add(paramSymbolTableData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void incNodeComponentRefCount(int paramInt) {
/* 488 */     if (paramInt == 0)
/*     */       return; 
/* 490 */     SymbolTableData symbolTableData = getSymbol(paramInt);
/*     */     
/* 492 */     ((NodeComponentState)symbolTableData.nodeState).addSubReference();
/*     */     
/* 494 */     if (symbolTableData.referenceCount == 1)
/* 495 */       this.sharedNodes.add(symbolTableData); 
/* 496 */     symbolTableData.referenceCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addReference(SceneGraphObject paramSceneGraphObject) {
/* 504 */     if (paramSceneGraphObject == null) return 0;
/*     */     
/* 506 */     SymbolTableData symbolTableData = getSymbol(paramSceneGraphObject);
/*     */     
/* 508 */     if (symbolTableData == null) {
/* 509 */       if (paramSceneGraphObject instanceof javax.media.j3d.Node) {
/* 510 */         symbolTableData = createDanglingSymbol(paramSceneGraphObject);
/* 511 */         if (symbolTableData.branchGraphID != this.currentBranchGraphID) {
/*     */           
/* 513 */           addInterGraphDependency(symbolTableData);
/* 514 */           this.sharedNodes.add(symbolTableData);
/*     */         } 
/*     */       } else {
/* 517 */         symbolTableData = createNodeComponentSymbol(paramSceneGraphObject);
/*     */       } 
/* 519 */       return symbolTableData.nodeID;
/*     */     } 
/* 521 */     return addReference(symbolTableData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addReference(SymbolTableData paramSymbolTableData) {
/* 531 */     if (paramSymbolTableData != null) {
/* 532 */       if (paramSymbolTableData.referenceCount == 1)
/* 533 */         this.sharedNodes.add(paramSymbolTableData); 
/* 534 */       paramSymbolTableData.referenceCount++;
/*     */       
/* 536 */       if (paramSymbolTableData.j3dNode instanceof javax.media.j3d.NodeComponent && paramSymbolTableData.referenceCount > 1) {
/* 537 */         ((NodeComponentState)paramSymbolTableData.nodeState).addSubReference();
/*     */       }
/*     */       
/* 540 */       if (paramSymbolTableData.branchGraphID != this.currentBranchGraphID && paramSymbolTableData.j3dNode instanceof javax.media.j3d.Node)
/*     */       {
/*     */         
/* 543 */         addInterGraphDependency(paramSymbolTableData);
/*     */       }
/*     */     } else {
/* 546 */       throw new SGIORuntimeException("Null Symbol");
/*     */     } 
/*     */     
/* 549 */     return paramSymbolTableData.nodeID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int addBranchGraphReference(SceneGraphObject paramSceneGraphObject, int paramInt) {
/* 560 */     if (paramSceneGraphObject == null) return 0;
/*     */     
/* 562 */     SymbolTableData symbolTableData = getSymbol(paramSceneGraphObject);
/*     */     
/* 564 */     if (symbolTableData != null) {
/* 565 */       if (symbolTableData.referenceCount == 1)
/* 566 */         this.sharedNodes.add(symbolTableData); 
/* 567 */       symbolTableData.referenceCount++;
/*     */     } else {
/* 569 */       symbolTableData = new SymbolTableData(this.nodeID++, paramSceneGraphObject, null, -3);
/* 570 */       this.j3dNodeIndex.put(paramSceneGraphObject, symbolTableData);
/* 571 */       this.nodeIDIndex.add(symbolTableData);
/* 572 */       this.danglingReferences.put(paramSceneGraphObject, symbolTableData);
/*     */     } 
/*     */     
/* 575 */     symbolTableData.branchGraphID = paramInt;
/* 576 */     for (int i = this.branchGraphs.size(); i < paramInt + 1; i++) {
/* 577 */       this.branchGraphs.add(null);
/* 578 */       this.branchGraphDependencies.add(null);
/*     */     } 
/*     */     
/* 581 */     this.branchGraphs.set(symbolTableData.branchGraphID, symbolTableData);
/*     */     
/* 583 */     return symbolTableData.nodeID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLoaded(int paramInt) {
/* 590 */     SymbolTableData symbolTableData = getSymbol(paramInt);
/*     */     
/* 592 */     if (symbolTableData == null) {
/* 593 */       return false;
/*     */     }
/* 595 */     if (symbolTableData.j3dNode == null) {
/* 596 */       return false;
/*     */     }
/* 598 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SceneGraphObject getJ3dNode(int paramInt) {
/* 607 */     if (paramInt == 0) return null;
/*     */     
/* 609 */     SymbolTableData symbolTableData = getSymbol(paramInt);
/*     */ 
/*     */ 
/*     */     
/* 613 */     if (symbolTableData.branchGraphID == -3) {
/* 614 */       return null;
/*     */     }
/* 616 */     if (symbolTableData != null && symbolTableData.j3dNode == null) {
/* 617 */       if (symbolTableData.isNodeComponent && this.control instanceof RandomAccessFileControl) {
/*     */         try {
/* 619 */           ((RandomAccessFileControl)this.control).loadNodeComponent(symbolTableData);
/* 620 */         } catch (IOException iOException) {
/* 621 */           System.out.println("FAILED to seek and load NodeComponent");
/* 622 */           return null;
/*     */         } 
/*     */       } else {
/* 625 */         System.out.println("WARNING - Object has not been loaded " + paramInt);
/* 626 */         System.out.println("Need to load branchgraph " + symbolTableData.branchGraphID);
/* 627 */         return null;
/*     */       } 
/* 629 */     } else if (symbolTableData == null) {
/* 630 */       throw new SGIORuntimeException("Missing Symbol " + paramInt);
/*     */     } 
/*     */     
/* 633 */     if (!symbolTableData.graphBuilt) {
/* 634 */       symbolTableData.graphBuilt = true;
/* 635 */       symbolTableData.nodeState.buildGraph();
/*     */     } 
/*     */     
/* 638 */     return symbolTableData.j3dNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 646 */   public SymbolTableData getSymbol(SceneGraphObject paramSceneGraphObject) { return (SymbolTableData)this.j3dNodeIndex.get(paramSceneGraphObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SymbolTableData getSymbol(int paramInt) {
/* 655 */     if (paramInt == 0 || paramInt > this.nodeIDIndex.size()) {
/* 656 */       return null;
/*     */     }
/* 658 */     return (SymbolTableData)this.nodeIDIndex.get(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SymbolTableData getSharedGroup(int paramInt) {
/* 666 */     SymbolTableData symbolTableData = getSymbol(paramInt);
/*     */     
/* 668 */     if (symbolTableData.nodeState == null && this.control instanceof RandomAccessFileControl) {
/*     */       try {
/* 670 */         ((RandomAccessFileControl)this.control).loadSharedGroup(symbolTableData);
/* 671 */       } catch (IOException iOException) {
/* 672 */         iOException.printStackTrace();
/* 673 */         throw new SGIORuntimeException("Internal error in getSharedGroup");
/*     */       } 
/*     */     }
/*     */     
/* 677 */     return symbolTableData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilePosition(long paramLong, SceneGraphObjectState paramSceneGraphObjectState) {
/* 684 */     if (paramSceneGraphObjectState instanceof com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.NullSceneGraphObjectState)
/*     */       return; 
/* 686 */     SymbolTableData symbolTableData = getSymbol(paramSceneGraphObjectState.getNodeID());
/*     */     
/* 688 */     symbolTableData.filePosition = paramLong;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 694 */   public void addNamedObject(String paramString, SceneGraphObject paramSceneGraphObject) { this.namedObjects.put(paramString, paramSceneGraphObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addNamedObjects(HashMap paramHashMap) {
/* 701 */     if (paramHashMap != null) {
/* 702 */       this.namedObjects.putAll(paramHashMap);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SceneGraphObject getNamedObject(String paramString) throws NamedObjectException, ObjectNotLoadedException {
/* 709 */     Object object = this.namedObjects.get(paramString);
/* 710 */     if (object == null) {
/* 711 */       throw new NamedObjectException("Unknown name :" + paramString);
/*     */     }
/* 713 */     if (object instanceof SceneGraphObject) {
/* 714 */       return (SceneGraphObject)object;
/*     */     }
/* 716 */     SymbolTableData symbolTableData = getSymbol(((Integer)object).intValue());
/* 717 */     if (symbolTableData == null || symbolTableData.j3dNode == null)
/* 718 */       throw new ObjectNotLoadedException(((Integer)object).toString()); 
/* 719 */     return symbolTableData.j3dNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 727 */   public String[] getNames() { return (String[])this.namedObjects.keySet().toArray(new String[0]); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 734 */   public void getNamedObjectMap(HashMap paramHashMap) { paramHashMap.putAll(this.namedObjects); }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 738 */     StringBuffer stringBuffer = new StringBuffer();
/*     */     
/* 740 */     for (byte b1 = 0; b1 < this.nodeIDIndex.size(); b1++) {
/* 741 */       SymbolTableData symbolTableData = (SymbolTableData)this.nodeIDIndex.get(b1);
/* 742 */       if (symbolTableData != null) {
/* 743 */         stringBuffer.append(symbolTableData.nodeID + " " + symbolTableData.referenceCount + " " + symbolTableData.filePosition + "  " + symbolTableData.branchGraphID + "  " + symbolTableData.nodeState + "\n");
/*     */       }
/*     */     } 
/* 746 */     stringBuffer.append("\nShared Objects\n");
/*     */     
/* 748 */     ListIterator listIterator = this.sharedNodes.listIterator();
/* 749 */     while (listIterator.hasNext()) {
/* 750 */       SymbolTableData symbolTableData = (SymbolTableData)listIterator.next();
/* 751 */       stringBuffer.append(symbolTableData.nodeID + " " + symbolTableData.referenceCount + " " + symbolTableData.filePosition + "  " + symbolTableData.branchGraphID + "  " + symbolTableData.j3dNode + "\n");
/*     */     } 
/*     */     
/* 754 */     stringBuffer.append("\nNamed Objects\n");
/*     */     
/* 756 */     String[] arrayOfString = getNames(); byte b2;
/* 757 */     for (b2 = 0; b2 < arrayOfString.length; b2++) {
/* 758 */       stringBuffer.append(arrayOfString[b2] + "  " + this.namedObjects.get(arrayOfString[b2]));
/*     */     }
/* 760 */     stringBuffer.append("\nBranch Graphs\n");
/* 761 */     for (b2 = 0; b2 < this.branchGraphs.size(); b2++) {
/* 762 */       SymbolTableData symbolTableData = (SymbolTableData)this.branchGraphs.get(b2);
/* 763 */       if (symbolTableData == null) System.out.println("Data is null " + b2 + "  " + this.branchGraphs.size()); 
/* 764 */       stringBuffer.append(symbolTableData.nodeID + " " + symbolTableData.referenceCount + " " + symbolTableData.filePosition + "  " + symbolTableData.branchGraphID + "  " + symbolTableData.j3dNode + " " + symbolTableData.nodeState + "\n");
/*     */     } 
/*     */     
/* 767 */     stringBuffer.append("\nBranch Graph Dependencies\n");
/* 768 */     for (b2 = 0; b2 < this.branchGraphDependencies.size(); b2++) {
/* 769 */       stringBuffer.append("Graph " + b2 + " - ");
/* 770 */       HashSet hashSet = (HashSet)this.branchGraphDependencies.get(b2);
/* 771 */       if (hashSet != null) {
/* 772 */         Iterator iterator = hashSet.iterator();
/* 773 */         while (iterator.hasNext())
/* 774 */           stringBuffer.append(((SymbolTableData)iterator.next()).nodeID + " "); 
/*     */       } 
/* 776 */       stringBuffer.append("\n");
/*     */     } 
/*     */     
/* 779 */     stringBuffer.append("------------------");
/*     */     
/* 781 */     return stringBuffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 788 */     this.j3dNodeIndex.clear();
/* 789 */     this.nodeIDIndex.clear();
/* 790 */     while (!this.unsavedNodeComponentsStack.empty())
/* 791 */       this.unsavedNodeComponentsStack.pop(); 
/* 792 */     this.danglingReferences.clear();
/* 793 */     this.sharedNodes.clear();
/* 794 */     this.namedObjects.clear();
/* 795 */     this.nodeID = 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearUnshared() {
/* 805 */     String[] arrayOfString = getNames();
/* 806 */     for (byte b = 0; b < arrayOfString.length; b++) {
/*     */       
/* 808 */       try { Object object = this.namedObjects.get(arrayOfString[b]);
/* 809 */         if (object instanceof Integer) {
/* 810 */           SymbolTableData symbolTableData = getSymbol(((Integer)object).intValue());
/* 811 */           if (symbolTableData != null && symbolTableData.j3dNode != null)
/* 812 */             this.namedObjects.put(arrayOfString[b], symbolTableData.j3dNode); 
/*     */         }  }
/* 814 */       catch (Exception exception) { exception.printStackTrace(); }
/*     */     
/*     */     } 
/* 817 */     this.j3dNodeIndex.clear();
/* 818 */     this.nodeIDIndex.clear();
/* 819 */     while (!this.unsavedNodeComponentsStack.empty()) {
/* 820 */       this.unsavedNodeComponentsStack.pop();
/*     */     }
/* 822 */     nodeIDIndexEnsureCapacity(this.nodeID);
/*     */ 
/*     */     
/* 825 */     ListIterator listIterator = this.sharedNodes.listIterator();
/* 826 */     while (listIterator.hasNext()) {
/* 827 */       SymbolTableData symbolTableData = (SymbolTableData)listIterator.next();
/* 828 */       this.nodeIDIndex.set(symbolTableData.nodeID, symbolTableData);
/* 829 */       this.j3dNodeIndex.put(symbolTableData.j3dNode, symbolTableData);
/*     */     } 
/*     */     
/* 832 */     Iterator iterator = this.danglingReferences.values().iterator();
/* 833 */     while (iterator.hasNext()) {
/* 834 */       SymbolTableData symbolTableData = (SymbolTableData)iterator.next();
/* 835 */       this.nodeIDIndex.set(symbolTableData.nodeID, symbolTableData);
/* 836 */       this.j3dNodeIndex.put(symbolTableData.j3dNode, symbolTableData);
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
/* 848 */   public SceneGraphObject resolveReference(int paramInt) { return getJ3dNode(paramInt); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\retained\SymbolTable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */