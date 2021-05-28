/*    */ package com.sun.j3d.audioengines.javasound;
/*    */ 
/*    */ import com.sun.j3d.audioengines.AuralParameters;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JSAuralParameters
/*    */   extends AuralParameters
/*    */ {
/* 62 */   static int REFLECTION_COEFF_CHANGED = 1;
/* 63 */   static int REVERB_DELAY_CHANGED = 2;
/* 64 */   static int REVERB_ORDER_CHANGED = 4;
/*    */   JSAuralParameters() {
/* 66 */     this.reverbDirty = 65535;
/* 67 */     this.lastReverbSpeed = 0;
/* 68 */     this.reverbFlag = false;
/* 69 */     this.reverbType = 1;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 74 */     this.reverbDirty = 65535;
/* 75 */     this.lastReverbSpeed = 0;
/* 76 */     this.reverbType = 1;
/* 77 */     this.reverbFlag = false;
/*    */   }
/*    */   
/*    */   int reverbDirty;
/*    */   int lastReverbSpeed;
/*    */   boolean reverbFlag;
/*    */   int reverbType;
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3d\audioengines\javasound\JSAuralParameters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */