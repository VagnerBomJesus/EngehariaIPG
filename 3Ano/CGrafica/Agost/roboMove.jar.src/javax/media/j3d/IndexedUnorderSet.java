/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class IndexedUnorderSet
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   static final boolean debug = false;
/*     */   IndexedObject[] elementData;
/*     */   Object[] cloneData;
/*     */   int cloneSize;
/*     */   boolean isDirty;
/*     */   Class componentType;
/*     */   int size;
/*     */   int listType;
/*     */   VirtualUniverse univ;
/*     */   
/*     */   IndexedUnorderSet(int paramInt1, Class paramClass, int paramInt2, VirtualUniverse paramVirtualUniverse) {
/*  82 */     this.isDirty = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     this.componentType = paramClass;
/* 112 */     this.elementData = (IndexedObject[])Array.newInstance(paramClass, paramInt1);
/*     */     
/* 114 */     this.listType = paramInt2;
/* 115 */     this.univ = paramVirtualUniverse;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   IndexedUnorderSet(Class paramClass, int paramInt, VirtualUniverse paramVirtualUniverse) { this(10, paramClass, paramInt, paramVirtualUniverse); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   IndexedUnorderSet(int paramInt1, int paramInt2, VirtualUniverse paramVirtualUniverse) { this(paramInt1, IndexedObject.class, paramInt2, paramVirtualUniverse); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   IndexedUnorderSet(int paramInt, VirtualUniverse paramVirtualUniverse) { this(10, IndexedObject.class, paramInt, paramVirtualUniverse); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final void init(IndexedObject paramIndexedObject, int paramInt) {
/* 150 */     paramIndexedObject.listIdx = new int[3][];
/*     */     
/* 152 */     paramIndexedObject.listIdx[0] = new int[paramInt];
/* 153 */     paramIndexedObject.listIdx[1] = new int[paramInt];
/* 154 */     paramIndexedObject.listIdx[2] = new int[1];
/*     */     
/* 156 */     for (byte b = 0; b < paramInt; b++) {
/* 157 */       paramIndexedObject.listIdx[0][b] = -1;
/* 158 */       paramIndexedObject.listIdx[1][b] = -1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     if (paramIndexedObject instanceof SceneGraphObjectRetained) {
/*     */       
/* 166 */       paramIndexedObject.listIdx[2][0] = 1;
/*     */     } else {
/* 168 */       paramIndexedObject.listIdx[2][0] = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   final int size() { return this.size; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   final int arraySize() { return this.cloneSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   final boolean isEmpty() { return (this.size == 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   final boolean contains(IndexedObject paramIndexedObject) { return (paramIndexedObject.listIdx[paramIndexedObject.getIdxUsed(this.univ)][this.listType] >= 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 222 */   final int indexOf(IndexedObject paramIndexedObject) { return paramIndexedObject.listIdx[paramIndexedObject.getIdxUsed(this.univ)][this.listType]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Object clone() {
/*     */     try {
/* 233 */       IndexedUnorderSet indexedUnorderSet = (IndexedUnorderSet)super.clone();
/* 234 */       indexedUnorderSet.elementData = (IndexedObject[])Array.newInstance(this.componentType, this.size);
/*     */       
/* 236 */       System.arraycopy(this.elementData, 0, indexedUnorderSet.elementData, 0, this.size);
/* 237 */       this.isDirty = true;
/* 238 */       return indexedUnorderSet;
/* 239 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 241 */       throw new InternalError();
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
/*     */   
/*     */   final Object[] toArray(boolean paramBoolean) {
/* 256 */     if (paramBoolean) {
/* 257 */       if (this.isDirty) {
/* 258 */         if (this.cloneData == null || this.cloneData.length < this.size) {
/* 259 */           this.cloneData = (Object[])Array.newInstance(this.componentType, this.size);
/*     */         }
/*     */         
/* 262 */         System.arraycopy(this.elementData, 0, this.cloneData, 0, this.size);
/* 263 */         this.cloneSize = this.size;
/* 264 */         this.isDirty = false;
/*     */       } 
/* 266 */       return this.cloneData;
/*     */     } 
/* 268 */     this.cloneSize = this.size;
/* 269 */     return this.elementData;
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
/* 286 */   final Object[] toArray() { return toArray(true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final Object[] toArray(IndexedObject paramIndexedObject) {
/* 302 */     int i = indexOf(paramIndexedObject);
/* 303 */     if (i < 0) {
/* 304 */       return (Object[])Array.newInstance(this.componentType, 0);
/*     */     }
/*     */     
/* 307 */     int j = this.size - i;
/* 308 */     Object[] arrayOfObject = (Object[])Array.newInstance(this.componentType, j);
/* 309 */     System.arraycopy(this.elementData, i, arrayOfObject, 0, j);
/* 310 */     return arrayOfObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void trimToSize() {
/* 319 */     if (this.elementData.length > this.size) {
/* 320 */       IndexedObject[] arrayOfIndexedObject = this.elementData;
/* 321 */       this.elementData = (IndexedObject[])Array.newInstance(this.componentType, this.size);
/*     */ 
/*     */       
/* 324 */       System.arraycopy(arrayOfIndexedObject, 0, this.elementData, 0, this.size);
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
/*     */ 
/*     */ 
/*     */   
/* 340 */   final Object get(int paramInt) { return this.elementData[paramInt]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void set(int paramInt, IndexedObject paramIndexedObject) {
/* 354 */     IndexedObject indexedObject = this.elementData[paramInt];
/* 355 */     if (indexedObject != null) {
/* 356 */       indexedObject.listIdx[indexedObject.getIdxUsed(this.univ)][this.listType] = -1;
/*     */     }
/* 358 */     this.elementData[paramInt] = paramIndexedObject;
/*     */     
/* 360 */     int i = paramIndexedObject.getIdxUsed(this.univ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 370 */     paramIndexedObject.listIdx[i][this.listType] = paramInt;
/* 371 */     this.isDirty = true;
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
/*     */   final void add(IndexedObject paramIndexedObject) {
/* 383 */     if (this.elementData.length == this.size) {
/* 384 */       IndexedObject[] arrayOfIndexedObject = this.elementData;
/* 385 */       this.elementData = (IndexedObject[])Array.newInstance(this.componentType, this.size << 1);
/*     */ 
/*     */       
/* 388 */       System.arraycopy(arrayOfIndexedObject, 0, this.elementData, 0, this.size);
/*     */     } 
/*     */ 
/*     */     
/* 392 */     int i = paramIndexedObject.getIdxUsed(this.univ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 404 */     int j = this.size++;
/* 405 */     this.elementData[j] = paramIndexedObject;
/* 406 */     paramIndexedObject.listIdx[i][this.listType] = j;
/* 407 */     this.isDirty = true;
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
/*     */   final void remove(int paramInt) {
/* 420 */     IndexedObject indexedObject = this.elementData[paramInt];
/*     */     
/* 422 */     int i = indexedObject.getIdxUsed(this.univ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 431 */     indexedObject.listIdx[i][this.listType] = -1;
/* 432 */     this.size--;
/* 433 */     if (paramInt != this.size) {
/* 434 */       indexedObject = this.elementData[this.size];
/* 435 */       indexedObject.listIdx[i][this.listType] = paramInt;
/* 436 */       this.elementData[paramInt] = indexedObject;
/*     */     } 
/* 438 */     this.elementData[this.size] = null;
/* 439 */     this.isDirty = true;
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
/*     */   final Object removeLastElement() {
/* 454 */     IndexedObject indexedObject = this.elementData[--this.size];
/* 455 */     this.elementData[this.size] = null;
/* 456 */     indexedObject.listIdx[indexedObject.getIdxUsed(this.univ)][this.listType] = -1;
/* 457 */     this.isDirty = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 463 */     return indexedObject;
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
/*     */   final boolean remove(IndexedObject paramIndexedObject) {
/* 477 */     int i = paramIndexedObject.getIdxUsed(this.univ);
/* 478 */     int j = paramIndexedObject.listIdx[i][this.listType];
/*     */     
/* 480 */     if (j >= 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 488 */       this.size--;
/* 489 */       if (j != this.size) {
/* 490 */         IndexedObject indexedObject = this.elementData[this.size];
/* 491 */         this.elementData[j] = indexedObject;
/* 492 */         indexedObject.listIdx[indexedObject.getIdxUsed(this.univ)][this.listType] = j;
/*     */       } 
/* 494 */       this.elementData[this.size] = null;
/* 495 */       paramIndexedObject.listIdx[i][this.listType] = -1;
/* 496 */       this.isDirty = true;
/* 497 */       return true;
/*     */     } 
/* 499 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void clear() {
/* 509 */     for (int i = this.size - 1; i >= 0; i--) {
/* 510 */       IndexedObject indexedObject = this.elementData[i];
/* 511 */       indexedObject.listIdx[indexedObject.getIdxUsed(this.univ)][this.listType] = -1;
/* 512 */       this.elementData[i] = null;
/*     */     } 
/* 514 */     this.size = 0;
/* 515 */     this.isDirty = true;
/*     */   }
/*     */ 
/*     */   
/*     */   final void clearMirror() {
/* 520 */     if (this.cloneData != null) {
/* 521 */       for (int i = this.cloneData.length - 1; i >= 0; i--)
/*     */       {
/*     */         
/* 524 */         this.cloneData[i] = null;
/*     */       }
/*     */     }
/* 527 */     this.cloneSize = 0;
/* 528 */     this.isDirty = true;
/*     */   }
/*     */ 
/*     */   
/* 532 */   final Class getComponentType() { return this.componentType; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream paramObjectOutputStream) throws IOException {
/* 569 */     paramObjectOutputStream.defaultWriteObject();
/*     */ 
/*     */     
/* 572 */     paramObjectOutputStream.writeInt(this.elementData.length);
/*     */ 
/*     */     
/* 575 */     for (byte b = 0; b < this.size; b++) {
/* 576 */       paramObjectOutputStream.writeObject(this.elementData[b]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream paramObjectInputStream) throws IOException, ClassNotFoundException {
/* 587 */     paramObjectInputStream.defaultReadObject();
/*     */ 
/*     */     
/* 590 */     int i = paramObjectInputStream.readInt();
/* 591 */     this.elementData = (IndexedObject[])Array.newInstance(this.componentType, i);
/*     */ 
/*     */ 
/*     */     
/* 595 */     for (byte b = 0; b < this.size; b++)
/* 596 */       this.elementData[b] = (IndexedObject)paramObjectInputStream.readObject(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\IndexedUnorderSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */