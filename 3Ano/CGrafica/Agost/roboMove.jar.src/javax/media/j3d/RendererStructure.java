/*    */ package javax.media.j3d;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class RendererStructure
/*    */   extends J3dStructure
/*    */ {
/* 28 */   RendererStructure() { super(null, 16); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   J3dMessage[] getMessages() {
/*    */     int i;
/* 37 */     synchronized (this.messageList) {
/* 38 */       if ((i = this.messageList.size()) > 0) {
/* 39 */         if (this.msgList.length < i) {
/* 40 */           this.msgList = new J3dMessage[i];
/*    */         }
/* 42 */         this.messageList.toArrayAndClear(this.msgList);
/*    */       } 
/*    */     } 
/*    */     
/* 46 */     this.nMessage = i;
/* 47 */     return this.msgList;
/*    */   }
/*    */   
/*    */   void processMessages(long paramLong) {}
/*    */   
/*    */   void removeNodes(J3dMessage paramJ3dMessage) {}
/*    */   
/*    */   void cleanup() {}
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\RendererStructure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */