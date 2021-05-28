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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class DepthComponent
/*    */   extends NodeComponent
/*    */ {
/*    */   public static final int ALLOW_SIZE_READ = 0;
/*    */   public static final int ALLOW_DATA_READ = 1;
/* 35 */   private static final int[] readCapabilities = { 0, 1 };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   DepthComponent() { setDefaultReadCapabilities(readCapabilities); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getWidth() {
/* 55 */     if (isLiveOrCompiled() && 
/* 56 */       !getCapability(0))
/* 57 */       throw new CapabilityNotSetException(J3dI18N.getString("DepthComponent0")); 
/* 58 */     return ((DepthComponentRetained)this.retained).getWidth();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 68 */     if (isLiveOrCompiled() && 
/* 69 */       !getCapability(0))
/* 70 */       throw new CapabilityNotSetException(J3dI18N.getString("DepthComponent0")); 
/* 71 */     return ((DepthComponentRetained)this.retained).getHeight();
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\DepthComponent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */