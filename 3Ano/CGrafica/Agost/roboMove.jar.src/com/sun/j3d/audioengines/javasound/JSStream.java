/*    */ package com.sun.j3d.audioengines.javasound;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class JSStream
/*    */   extends JSChannel
/*    */ {
/*    */   private static boolean warningReported = false;
/*    */   
/*    */   JSStream() {
/* 60 */     if (!warningReported) {
/* 61 */       System.err.println("***");
/* 62 */       System.err.println("*** WARNING: JavaSoundMixer: Streaming (uncached) audio not implemented");
/* 63 */       System.err.println("***");
/* 64 */       warningReported = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3d\audioengines\javasound\JSStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */