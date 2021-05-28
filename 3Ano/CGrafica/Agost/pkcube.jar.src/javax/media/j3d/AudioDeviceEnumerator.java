/*    */ package javax.media.j3d;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.NoSuchElementException;
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
/*    */ class AudioDeviceEnumerator
/*    */   implements Enumeration
/*    */ {
/*    */   boolean endOfList;
/*    */   AudioDevice device;
/*    */   
/*    */   AudioDeviceEnumerator(PhysicalEnvironment paramPhysicalEnvironment) {
/* 31 */     this.device = paramPhysicalEnvironment.getAudioDevice();
/* 32 */     if (this.device == null) {
/* 33 */       this.endOfList = true;
/*    */     } else {
/* 35 */       this.endOfList = false;
/*    */     } 
/*    */   }
/*    */   void reset() {
/* 39 */     if (this.device != null) {
/* 40 */       this.endOfList = false;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasMoreElements() {
/* 49 */     if (!this.endOfList) {
/* 50 */       return true;
/*    */     }
/* 52 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object nextElement() {
/* 60 */     if (hasMoreElements()) {
/* 61 */       this.endOfList = true;
/* 62 */       return this.device;
/*    */     } 
/* 64 */     throw new NoSuchElementException(J3dI18N.getString("AudioDeviceEnumerator0"));
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\AudioDeviceEnumerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */