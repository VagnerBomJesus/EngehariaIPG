/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class UnorderList
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   Object[] elementData;
/*     */   Object[] cloneData;
/*     */   int cloneSize;
/*     */   boolean isDirty;
/*     */   Class componentType;
/*     */   int size;
/*     */   
/*     */   UnorderList(int paramInt, Class paramClass) {
/*  67 */     this.isDirty = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     this.componentType = paramClass;
/*  93 */     this.elementData = (Object[])Array.newInstance(paramClass, paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   UnorderList(Class paramClass) { this(10, paramClass); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   UnorderList(int paramInt) { this(paramInt, Object.class); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   UnorderList() { this(10, Object.class); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   final int size() { return this.size; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   final int arraySize() { return this.cloneSize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   final boolean isEmpty() { return (this.size == 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final boolean contains(Object paramObject) {
/* 160 */     if (paramObject != null)
/* 161 */     { for (int i = this.size - 1; i >= 0; i--) {
/* 162 */         if (paramObject.equals(this.elementData[i]))
/* 163 */           return true; 
/*     */       }  }
/* 165 */     else { for (int i = this.size - 1; i >= 0; i--) {
/* 166 */         if (this.elementData[i] == null)
/* 167 */           return true; 
/*     */       }  }
/* 169 */      return false;
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
/*     */   final boolean addUnique(Object paramObject) {
/* 181 */     if (!contains(paramObject)) {
/* 182 */       add(paramObject);
/* 183 */       return true;
/*     */     } 
/* 185 */     return false;
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
/*     */   final int indexOf(Object paramObject) {
/* 198 */     if (paramObject != null)
/* 199 */     { for (int i = this.size - 1; i >= 0; i--) {
/* 200 */         if (paramObject.equals(this.elementData[i]))
/* 201 */           return i; 
/*     */       }  }
/* 203 */     else { for (int i = this.size - 1; i >= 0; i--) {
/* 204 */         if (this.elementData[i] == null)
/* 205 */           return i; 
/*     */       }  }
/* 207 */      return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Object clone() {
/*     */     try {
/* 218 */       UnorderList unorderList = (UnorderList)super.clone();
/* 219 */       unorderList.elementData = (Object[])Array.newInstance(this.componentType, this.size);
/*     */       
/* 221 */       System.arraycopy(this.elementData, 0, unorderList.elementData, 0, this.size);
/* 222 */       this.isDirty = true;
/* 223 */       return unorderList;
/* 224 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 226 */       throw new InternalError();
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
/* 241 */     if (paramBoolean) {
/* 242 */       if (this.isDirty) {
/* 243 */         if (this.cloneData == null || this.cloneData.length < this.size) {
/* 244 */           this.cloneData = (Object[])Array.newInstance(this.componentType, this.size);
/*     */         }
/*     */         
/* 247 */         System.arraycopy(this.elementData, 0, this.cloneData, 0, this.size);
/* 248 */         this.cloneSize = this.size;
/* 249 */         this.isDirty = false;
/*     */       } 
/* 251 */       return this.cloneData;
/*     */     } 
/* 253 */     this.cloneSize = this.size;
/* 254 */     return this.elementData;
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
/* 271 */   final Object[] toArray() { return toArray(true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final Object[] toArray(Object paramObject) {
/* 287 */     int i = indexOf(paramObject);
/* 288 */     if (i < 0) {
/* 289 */       return (Object[])Array.newInstance(this.componentType, 0);
/*     */     }
/*     */     
/* 292 */     int j = this.size - i;
/* 293 */     Object[] arrayOfObject = (Object[])Array.newInstance(this.componentType, j);
/* 294 */     System.arraycopy(this.elementData, i, arrayOfObject, 0, j);
/* 295 */     return arrayOfObject;
/*     */   }
/*     */ 
/*     */   
/*     */   final void toArrayAndClear(Object[] paramArrayOfObject) {
/* 300 */     System.arraycopy(this.elementData, 0, paramArrayOfObject, 0, this.size);
/* 301 */     Arrays.fill(this.elementData, 0, this.size, null);
/* 302 */     this.size = 0;
/* 303 */     this.isDirty = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void trimToSize() {
/* 313 */     if (this.elementData.length > this.size) {
/* 314 */       Object[] arrayOfObject = this.elementData;
/* 315 */       this.elementData = (Object[])Array.newInstance(this.componentType, this.size);
/*     */ 
/*     */       
/* 318 */       System.arraycopy(arrayOfObject, 0, this.elementData, 0, this.size);
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
/* 334 */   final Object get(int paramInt) { return this.elementData[paramInt]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void set(int paramInt, Object paramObject) {
/* 348 */     this.elementData[paramInt] = paramObject;
/* 349 */     this.isDirty = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void add(Object paramObject) {
/* 360 */     if (this.elementData.length == this.size) {
/* 361 */       Object[] arrayOfObject = this.elementData;
/* 362 */       this.elementData = (Object[])Array.newInstance(this.componentType, this.size << 1);
/*     */ 
/*     */       
/* 365 */       System.arraycopy(arrayOfObject, 0, this.elementData, 0, this.size);
/*     */     } 
/*     */     
/* 368 */     this.elementData[this.size++] = paramObject;
/* 369 */     this.isDirty = true;
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
/* 382 */     this.elementData[paramInt] = this.elementData[--this.size];
/* 383 */     this.elementData[this.size] = null;
/* 384 */     this.isDirty = true;
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
/*     */   final void removeOrdered(int paramInt) {
/* 402 */     this.size--;
/* 403 */     if (paramInt < this.size) {
/* 404 */       System.arraycopy(this.elementData, paramInt + 1, this.elementData, paramInt, this.size - paramInt);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 409 */     this.elementData[this.size] = null;
/* 410 */     this.isDirty = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final Object removeLastElement() {
/* 420 */     Object object = this.elementData[--this.size];
/* 421 */     this.elementData[this.size] = null;
/* 422 */     this.isDirty = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 428 */     return object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void shift(Object[] paramArrayOfObject, int paramInt) {
/* 436 */     int i = this.size;
/*     */     
/* 438 */     System.arraycopy(this.elementData, 0, paramArrayOfObject, 0, paramInt);
/* 439 */     this.size -= paramInt;
/* 440 */     if (this.size > 0) {
/* 441 */       System.arraycopy(this.elementData, paramInt, this.elementData, 0, this.size);
/*     */     }
/* 443 */     Arrays.fill(this.elementData, this.size, i, null);
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
/*     */   final boolean remove(Object paramObject) {
/* 456 */     this.size--;
/* 457 */     if (paramObject != null) {
/* 458 */       for (int i = this.size; i >= 0; i--) {
/* 459 */         if (paramObject.equals(this.elementData[i])) {
/* 460 */           this.elementData[i] = this.elementData[this.size];
/* 461 */           this.elementData[this.size] = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 467 */           this.isDirty = true;
/* 468 */           return true;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 472 */       for (int i = this.size; i >= 0; i--) {
/* 473 */         if (this.elementData[i] == null) {
/* 474 */           this.elementData[i] = this.elementData[this.size];
/* 475 */           this.elementData[this.size] = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 481 */           this.isDirty = true;
/* 482 */           return true;
/*     */         } 
/*     */       } 
/* 485 */     }  this.size++;
/* 486 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void clear() {
/* 495 */     if (this.size > 0) {
/* 496 */       Arrays.fill(this.elementData, 0, this.size, null);
/* 497 */       this.size = 0;
/* 498 */       this.isDirty = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   final void clearMirror() {
/* 503 */     if (this.cloneData != null) {
/* 504 */       Arrays.fill(this.cloneData, 0, this.cloneData.length, null);
/*     */     }
/* 506 */     this.cloneSize = 0;
/* 507 */     this.isDirty = true;
/*     */   }
/*     */ 
/*     */   
/* 511 */   final Class getComponentType() { return this.componentType; }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 515 */     StringBuffer stringBuffer = new StringBuffer("Size = " + this.size + "\n[");
/* 516 */     int i = this.size - 1;
/*     */ 
/*     */     
/* 519 */     for (byte b = 0; b < this.size; b++) {
/* 520 */       Object object = this.elementData[b];
/* 521 */       if (object != null) {
/* 522 */         stringBuffer.append(this.elementData[b].toString());
/*     */       } else {
/* 524 */         stringBuffer.append("NULL");
/*     */       } 
/* 526 */       if (b != i) {
/* 527 */         stringBuffer.append(", ");
/*     */       }
/*     */     } 
/* 530 */     stringBuffer.append("]\n");
/* 531 */     return stringBuffer.toString();
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
/* 545 */     paramObjectOutputStream.defaultWriteObject();
/*     */ 
/*     */     
/* 548 */     paramObjectOutputStream.writeInt(this.elementData.length);
/*     */ 
/*     */     
/* 551 */     for (byte b = 0; b < this.size; b++) {
/* 552 */       paramObjectOutputStream.writeObject(this.elementData[b]);
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
/* 563 */     paramObjectInputStream.defaultReadObject();
/*     */ 
/*     */     
/* 566 */     int i = paramObjectInputStream.readInt();
/* 567 */     this.elementData = (Object[])Array.newInstance(this.componentType, i);
/*     */ 
/*     */ 
/*     */     
/* 571 */     for (byte b = 0; b < this.size; b++)
/* 572 */       this.elementData[b] = paramObjectInputStream.readObject(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\UnorderList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */