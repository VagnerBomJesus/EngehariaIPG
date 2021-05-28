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
/*     */ abstract class J3dStructure
/*     */ {
/*     */   UnorderList messageList;
/*     */   StructureUpdateThread updateThread;
/*     */   int threadType;
/*     */   VirtualUniverse universe;
/*     */   J3dThreadData threadData;
/*     */   int nMessage;
/*     */   J3dMessage[] msgList;
/*     */   
/*     */   J3dStructure(VirtualUniverse paramVirtualUniverse, int paramInt) {
/*  27 */     this.messageList = new UnorderList(5, J3dMessage.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  33 */     this.updateThread = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  38 */     this.threadType = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  43 */     this.universe = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     this.threadData = new J3dThreadData();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     this.nMessage = 0;
/*  54 */     this.msgList = new J3dMessage[5];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     this.universe = paramVirtualUniverse;
/*  61 */     this.threadType = paramInt;
/*  62 */     this.threadData.threadType = paramInt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   final J3dThreadData getUpdateThreadData() { return this.threadData; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void addMessage(J3dMessage paramJ3dMessage) {
/*  77 */     if (this.threadData != null) {
/*  78 */       this.threadData.lastUpdateTime = paramJ3dMessage.time;
/*     */     } else {
/*     */       
/*  81 */       paramJ3dMessage.time = -1L;
/*     */     } 
/*  83 */     paramJ3dMessage.incRefcount();
/*  84 */     this.messageList.add(paramJ3dMessage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final J3dMessage[] getMessages(long paramLong) {
/*  92 */     byte b = 0;
/*     */     
/*  94 */     synchronized (this.messageList) {
/*  95 */       int i; if ((i = this.messageList.size()) > 0) {
/*  96 */         J3dMessage[] arrayOfJ3dMessage = (J3dMessage[])this.messageList.toArray(false);
/*  97 */         for (b = 0; b < i && 
/*  98 */           (arrayOfJ3dMessage[b]).time <= paramLong; b++);
/*     */ 
/*     */ 
/*     */         
/* 102 */         if (b > 0) {
/* 103 */           if (this.msgList.length < b) {
/* 104 */             this.msgList = new J3dMessage[b];
/*     */           }
/* 106 */           this.messageList.shift(this.msgList, b);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     this.nMessage = b;
/* 112 */     return this.msgList;
/*     */   }
/*     */   
/*     */   final void clearMessages() {
/* 116 */     synchronized (this.messageList) {
/* 117 */       int i = this.messageList.size();
/* 118 */       if (i > 0) {
/* 119 */         J3dMessage[] arrayOfJ3dMessage = (J3dMessage[])this.messageList.toArray(false);
/* 120 */         for (int j = i - 1; j >= 0; j--) {
/* 121 */           arrayOfJ3dMessage[j].decRefcount();
/*     */         }
/* 123 */         this.messageList.clear();
/*     */       } 
/* 125 */       this.nMessage = 0;
/* 126 */       this.msgList = new J3dMessage[5];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 132 */   int getNumMessage() { return this.nMessage; }
/*     */   
/*     */   abstract void processMessages(long paramLong);
/*     */   
/*     */   abstract void removeNodes(J3dMessage paramJ3dMessage);
/*     */   
/*     */   abstract void cleanup();
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\J3dStructure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */