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
/*    */ public class ShaderAttributeValue
/*    */   extends ShaderAttributeObject
/*    */ {
/* 60 */   public ShaderAttributeValue(String paramString, Object paramObject) { super(paramString, paramObject); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getValue() {
/* 66 */     if (isLiveOrCompiled() && 
/* 67 */       !getCapability(0)) {
/* 68 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAttributeObject0"));
/*    */     }
/* 70 */     return ((ShaderAttributeValueRetained)this.retained).getValue();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setValue(Object paramObject) {
/* 76 */     if (paramObject == null) {
/* 77 */       throw new NullPointerException();
/*    */     }
/*    */     
/* 80 */     if (isLiveOrCompiled() && 
/* 81 */       !getCapability(1)) {
/* 82 */       throw new CapabilityNotSetException(J3dI18N.getString("ShaderAttributeObject1"));
/*    */     }
/* 84 */     if (isLive()) {
/* 85 */       ((ShaderAttributeValueRetained)this.retained).setValue(paramObject);
/*    */     } else {
/* 87 */       ((ShaderAttributeValueRetained)this.retained).initValue(paramObject);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void createRetained() {
/* 96 */     this.retained = new ShaderAttributeValueRetained();
/* 97 */     this.retained.setSource(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\ShaderAttributeValue.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */