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
/*     */ class WakeupIndexedList
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   static final boolean debug = false;
/*     */   WakeupCondition[] elementData;
/*     */   Object[] cloneData;
/*     */   int cloneSize;
/*     */   boolean isDirty;
/*     */   Class componentType;
/*     */   int size;
/*     */   int listType;
/*     */   VirtualUniverse univ;
/*     */   
/*     */   WakeupIndexedList(int paramInt1, Class paramClass, int paramInt2, VirtualUniverse paramVirtualUniverse) {
/*  81 */     this.isDirty = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     this.componentType = paramClass;
/* 111 */     this.elementData = (WakeupCondition[])Array.newInstance(paramClass, paramInt1);
/*     */     
/* 113 */     this.listType = paramInt2;
/* 114 */     this.univ = paramVirtualUniverse;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   WakeupIndexedList(Class paramClass, int paramInt, VirtualUniverse paramVirtualUniverse) { this(10, paramClass, paramInt, paramVirtualUniverse); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   WakeupIndexedList(int paramInt1, int paramInt2, VirtualUniverse paramVirtualUniverse) { this(paramInt1, WakeupCondition.class, paramInt2, paramVirtualUniverse); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   WakeupIndexedList(int paramInt, VirtualUniverse paramVirtualUniverse) { this(10, WakeupCondition.class, paramInt, paramVirtualUniverse); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final void init(WakeupCondition paramWakeupCondition, int paramInt) {
/* 151 */     paramWakeupCondition.listIdx = new int[2][paramInt];
/*     */     
/* 153 */     for (byte b = 0; b < paramInt; b++) {
/* 154 */       paramWakeupCondition.listIdx[0][b] = -1;
/* 155 */       paramWakeupCondition.listIdx[1][b] = -1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   final int size() { return this.size; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   final int arraySize() { return this.cloneSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   final boolean isEmpty() { return (this.size == 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   final boolean contains(WakeupCondition paramWakeupCondition) { return (paramWakeupCondition.listIdx[paramWakeupCondition.behav.getIdxUsed(this.univ)][this.listType] >= 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   final int indexOf(WakeupCondition paramWakeupCondition) { return paramWakeupCondition.listIdx[paramWakeupCondition.behav.getIdxUsed(this.univ)][this.listType]; }
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
/* 219 */       WakeupIndexedList wakeupIndexedList = (WakeupIndexedList)super.clone();
/* 220 */       wakeupIndexedList.elementData = (WakeupCondition[])Array.newInstance(this.componentType, this.size);
/*     */       
/* 222 */       System.arraycopy(this.elementData, 0, wakeupIndexedList.elementData, 0, this.size);
/* 223 */       this.isDirty = true;
/* 224 */       return wakeupIndexedList;
/* 225 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 227 */       throw new InternalError();
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
/* 242 */     if (paramBoolean) {
/* 243 */       if (this.isDirty) {
/* 244 */         if (this.cloneData == null || this.cloneData.length < this.size) {
/* 245 */           this.cloneData = (Object[])Array.newInstance(this.componentType, this.size);
/*     */         }
/*     */         
/* 248 */         System.arraycopy(this.elementData, 0, this.cloneData, 0, this.size);
/* 249 */         this.cloneSize = this.size;
/* 250 */         this.isDirty = false;
/*     */       } 
/* 252 */       return this.cloneData;
/*     */     } 
/* 254 */     this.cloneSize = this.size;
/* 255 */     return this.elementData;
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
/* 272 */   final Object[] toArray() { return toArray(true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final Object[] toArray(WakeupCondition paramWakeupCondition) {
/* 288 */     int i = indexOf(paramWakeupCondition);
/* 289 */     if (i < 0) {
/* 290 */       return (Object[])Array.newInstance(this.componentType, 0);
/*     */     }
/*     */     
/* 293 */     int j = this.size - i;
/* 294 */     Object[] arrayOfObject = (Object[])Array.newInstance(this.componentType, j);
/* 295 */     System.arraycopy(this.elementData, i, arrayOfObject, 0, j);
/* 296 */     return arrayOfObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void trimToSize() {
/* 305 */     if (this.elementData.length > this.size) {
/* 306 */       WakeupCondition[] arrayOfWakeupCondition = this.elementData;
/* 307 */       this.elementData = (WakeupCondition[])Array.newInstance(this.componentType, this.size);
/*     */ 
/*     */       
/* 310 */       System.arraycopy(arrayOfWakeupCondition, 0, this.elementData, 0, this.size);
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
/* 326 */   final Object get(int paramInt) { return this.elementData[paramInt]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void set(int paramInt, WakeupCondition paramWakeupCondition) {
/* 341 */     WakeupCondition wakeupCondition = this.elementData[paramInt];
/* 342 */     if (wakeupCondition != null) {
/* 343 */       wakeupCondition.listIdx[wakeupCondition.behav.getIdxUsed(this.univ)][this.listType] = -1;
/*     */     }
/* 345 */     this.elementData[paramInt] = paramWakeupCondition;
/*     */     
/* 347 */     int i = paramWakeupCondition.behav.getIdxUsed(this.univ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 357 */     paramWakeupCondition.listIdx[i][this.listType] = paramInt;
/* 358 */     this.isDirty = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void add(WakeupCondition paramWakeupCondition) {
/* 369 */     if (this.elementData.length == this.size) {
/* 370 */       WakeupCondition[] arrayOfWakeupCondition = this.elementData;
/* 371 */       this.elementData = (WakeupCondition[])Array.newInstance(this.componentType, this.size << 1);
/*     */ 
/*     */       
/* 374 */       System.arraycopy(arrayOfWakeupCondition, 0, this.elementData, 0, this.size);
/*     */     } 
/*     */ 
/*     */     
/* 378 */     int i = paramWakeupCondition.behav.getIdxUsed(this.univ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 390 */     int j = this.size++;
/* 391 */     this.elementData[j] = paramWakeupCondition;
/* 392 */     paramWakeupCondition.listIdx[i][this.listType] = j;
/* 393 */     this.isDirty = true;
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
/* 406 */     WakeupCondition wakeupCondition = this.elementData[paramInt];
/* 407 */     int i = wakeupCondition.behav.getIdxUsed(this.univ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 417 */     wakeupCondition.listIdx[i][this.listType] = -1;
/* 418 */     this.size--;
/* 419 */     if (paramInt != this.size) {
/* 420 */       wakeupCondition = this.elementData[this.size];
/* 421 */       wakeupCondition.listIdx[i][this.listType] = paramInt;
/* 422 */       this.elementData[paramInt] = wakeupCondition;
/*     */     } 
/* 424 */     this.elementData[this.size] = null;
/* 425 */     this.isDirty = true;
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
/* 440 */     WakeupCondition wakeupCondition = this.elementData[--this.size];
/* 441 */     this.elementData[this.size] = null;
/* 442 */     wakeupCondition.listIdx[wakeupCondition.behav.getIdxUsed(this.univ)][this.listType] = -1;
/* 443 */     this.isDirty = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 449 */     return wakeupCondition;
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
/*     */   final boolean remove(WakeupCondition paramWakeupCondition) {
/* 463 */     int i = paramWakeupCondition.behav.getIdxUsed(this.univ);
/* 464 */     int j = paramWakeupCondition.listIdx[i][this.listType];
/*     */ 
/*     */ 
/*     */     
/* 468 */     if (j >= 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 476 */       this.size--;
/* 477 */       if (j != this.size) {
/* 478 */         WakeupCondition wakeupCondition = this.elementData[this.size];
/* 479 */         this.elementData[j] = wakeupCondition;
/* 480 */         wakeupCondition.listIdx[wakeupCondition.behav.getIdxUsed(this.univ)][this.listType] = j;
/*     */       } 
/* 482 */       this.elementData[this.size] = null;
/* 483 */       paramWakeupCondition.listIdx[i][this.listType] = -1;
/* 484 */       this.isDirty = true;
/* 485 */       return true;
/*     */     } 
/* 487 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void clear() {
/* 498 */     for (int i = this.size - 1; i >= 0; i--) {
/* 499 */       WakeupCondition wakeupCondition = this.elementData[i];
/* 500 */       wakeupCondition.listIdx[wakeupCondition.behav.getIdxUsed(this.univ)][this.listType] = -1;
/* 501 */       this.elementData[i] = null;
/*     */     } 
/*     */     
/* 504 */     this.size = 0;
/* 505 */     this.isDirty = true;
/*     */   }
/*     */   
/*     */   final void clearMirror() {
/* 509 */     if (this.cloneData != null) {
/* 510 */       for (int i = this.cloneData.length - 1; i >= 0; i--)
/*     */       {
/*     */         
/* 513 */         this.cloneData[i] = null;
/*     */       }
/*     */     }
/* 516 */     this.cloneSize = 0;
/* 517 */     this.isDirty = true;
/*     */   }
/*     */ 
/*     */   
/* 521 */   final Class getComponentType() { return this.componentType; }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 525 */     StringBuffer stringBuffer = new StringBuffer(hashCode() + " Size = " + this.size + "[");
/* 526 */     int i = this.size - 1;
/*     */ 
/*     */     
/* 529 */     for (byte b = 0; b < this.size; b++) {
/* 530 */       WakeupCondition wakeupCondition = this.elementData[b];
/* 531 */       if (wakeupCondition != null) {
/* 532 */         stringBuffer.append(this.elementData[b].toString());
/*     */       } else {
/* 534 */         stringBuffer.append("NULL");
/*     */       } 
/* 536 */       if (b != i) {
/* 537 */         stringBuffer.append(", ");
/*     */       }
/*     */     } 
/* 540 */     stringBuffer.append("]");
/* 541 */     return stringBuffer.toString();
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
/*     */   private void writeObject(ObjectOutputStream paramObjectOutputStream) throws IOException {
/* 555 */     paramObjectOutputStream.defaultWriteObject();
/*     */ 
/*     */     
/* 558 */     paramObjectOutputStream.writeInt(this.elementData.length);
/*     */ 
/*     */     
/* 561 */     for (byte b = 0; b < this.size; b++) {
/* 562 */       paramObjectOutputStream.writeObject(this.elementData[b]);
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
/* 573 */     paramObjectInputStream.defaultReadObject();
/*     */ 
/*     */     
/* 576 */     int i = paramObjectInputStream.readInt();
/* 577 */     this.elementData = (WakeupCondition[])Array.newInstance(this.componentType, i);
/*     */ 
/*     */ 
/*     */     
/* 581 */     for (byte b = 0; b < this.size; b++)
/* 582 */       this.elementData[b] = (WakeupCondition)paramObjectInputStream.readObject(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\WakeupIndexedList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */