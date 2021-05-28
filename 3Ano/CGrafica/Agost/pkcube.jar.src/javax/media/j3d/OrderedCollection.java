/*    */ package javax.media.j3d;
/*    */ 
/*    */ import java.util.ArrayList;
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
/*    */ class OrderedCollection
/*    */   implements ObjectUpdate
/*    */ {
/* 24 */   LightBin lightBin = null;
/*    */ 
/*    */   
/* 27 */   ArrayList childOrderedBins = new ArrayList();
/*    */ 
/*    */   
/* 30 */   LightBin nextFrameLightBin = null;
/*    */ 
/*    */   
/* 33 */   LightBin addLightBins = null;
/*    */ 
/*    */   
/*    */   boolean onUpdateList = false;
/*    */ 
/*    */   
/*    */   public void updateObject() {
/* 40 */     this.lightBin = this.nextFrameLightBin;
/* 41 */     if (this.addLightBins != null) {
/* 42 */       if (this.lightBin != null) {
/* 43 */         this.addLightBins.prev = this.lightBin;
/* 44 */         this.lightBin.next = this.addLightBins;
/*    */       } else {
/*    */         
/* 47 */         this.lightBin = this.addLightBins;
/* 48 */         this.nextFrameLightBin = this.lightBin;
/*    */       } 
/*    */     }
/* 51 */     this.addLightBins = null;
/* 52 */     this.onUpdateList = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\OrderedCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */