/*     */ package javax.media.j3d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class WakeupOnElapsedTimeHeap
/*     */   implements Cloneable
/*     */ {
/*     */   WakeupOnElapsedTime[] data;
/*     */   int size;
/*     */   
/*     */   WakeupOnElapsedTimeHeap(int paramInt) {
/*  26 */     this.size = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  32 */     this.data = new WakeupOnElapsedTime[paramInt + 1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   WakeupOnElapsedTimeHeap() { this(10); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   final int size() { return this.size; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   final boolean isEmpty() { return (this.size == 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   final WakeupOnElapsedTime getMin() { return this.data[1]; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void insert(WakeupOnElapsedTime paramWakeupOnElapsedTime) {
/*  69 */     if (this.data.length == this.size + 1) {
/*  70 */       WakeupOnElapsedTime[] arrayOfWakeupOnElapsedTime = this.data;
/*  71 */       this.data = new WakeupOnElapsedTime[arrayOfWakeupOnElapsedTime.length << 1];
/*  72 */       System.arraycopy(arrayOfWakeupOnElapsedTime, 0, this.data, 0, arrayOfWakeupOnElapsedTime.length);
/*     */     } 
/*     */     
/*  75 */     int i = ++this.size;
/*     */     
/*  77 */     int j = i >> 1;
/*  78 */     WakeupOnElapsedTime wakeupOnElapsedTime = this.data[j];
/*  79 */     long l = paramWakeupOnElapsedTime.triggeredTime;
/*     */     
/*  81 */     while (i > 1 && wakeupOnElapsedTime.triggeredTime > l) {
/*  82 */       this.data[i] = wakeupOnElapsedTime;
/*  83 */       i = j;
/*  84 */       j >>= 1;
/*  85 */       wakeupOnElapsedTime = this.data[j];
/*     */     } 
/*  87 */     this.data[i] = paramWakeupOnElapsedTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void extract(BehaviorRetained paramBehaviorRetained) {
/*  95 */     for (byte b = 1; b <= this.size; b++) {
/*  96 */       if ((this.data[b]).behav == paramBehaviorRetained) {
/*  97 */         extract(b);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final boolean extract(WakeupOnElapsedTime paramWakeupOnElapsedTime) {
/* 107 */     for (byte b = 1; b <= this.size; b++) {
/* 108 */       if (this.data[b] == paramWakeupOnElapsedTime) {
/* 109 */         extract(b);
/* 110 */         return true;
/*     */       } 
/*     */     } 
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   final WakeupOnElapsedTime extractMin() { return extract(1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final WakeupOnElapsedTime extract(int paramInt) {
/* 129 */     WakeupOnElapsedTime wakeupOnElapsedTime = this.data[paramInt];
/*     */ 
/*     */ 
/*     */     
/* 133 */     this.data[paramInt] = this.data[this.size];
/* 134 */     this.data[this.size] = null;
/* 135 */     this.size--;
/*     */ 
/*     */     
/*     */     while (true) {
/* 139 */       int k, i = paramInt << 1;
/* 140 */       int j = i + 1;
/*     */       
/* 142 */       if (i <= this.size && (this.data[i]).triggeredTime < (this.data[paramInt]).triggeredTime) {
/*     */         
/* 144 */         k = i;
/*     */       } else {
/* 146 */         k = paramInt;
/*     */       } 
/* 148 */       if (j <= this.size && (this.data[j]).triggeredTime < (this.data[k]).triggeredTime)
/*     */       {
/* 150 */         k = j;
/*     */       }
/* 152 */       if (k == paramInt) {
/*     */         break;
/*     */       }
/* 155 */       WakeupOnElapsedTime wakeupOnElapsedTime1 = this.data[k];
/* 156 */       this.data[k] = this.data[paramInt];
/* 157 */       this.data[paramInt] = wakeupOnElapsedTime1;
/* 158 */       paramInt = k;
/*     */     } 
/*     */     
/* 161 */     return wakeupOnElapsedTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void trimToSize() {
/* 170 */     if (this.data.length > this.size + 1) {
/* 171 */       WakeupOnElapsedTime[] arrayOfWakeupOnElapsedTime = this.data;
/* 172 */       this.data = new WakeupOnElapsedTime[this.size + 1];
/* 173 */       System.arraycopy(arrayOfWakeupOnElapsedTime, 0, this.data, 0, this.data.length);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Object clone() {
/*     */     try {
/* 182 */       WakeupOnElapsedTimeHeap wakeupOnElapsedTimeHeap = (WakeupOnElapsedTimeHeap)super.clone();
/* 183 */       wakeupOnElapsedTimeHeap.data = new WakeupOnElapsedTime[this.size + 1];
/* 184 */       System.arraycopy(this.data, 0, wakeupOnElapsedTimeHeap.data, 0, this.size + 1);
/* 185 */       return wakeupOnElapsedTimeHeap;
/* 186 */     } catch (CloneNotSupportedException cloneNotSupportedException) {
/*     */       
/* 188 */       throw new InternalError();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 195 */     StringBuffer stringBuffer = new StringBuffer("[ ");
/*     */     
/* 197 */     if (this.size > 0) {
/* 198 */       stringBuffer.append(this.data[1]);
/*     */     }
/*     */     
/* 201 */     for (byte b = 2; b <= this.size; b++) {
/* 202 */       stringBuffer.append("," + this.data[b]);
/*     */     }
/* 204 */     stringBuffer.append(" ]");
/* 205 */     return stringBuffer.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\WakeupOnElapsedTimeHeap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */