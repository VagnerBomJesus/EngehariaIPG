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
/*    */ abstract class ShaderAttributeRetained
/*    */   extends NodeComponentRetained
/*    */ {
/*    */   String attrName;
/*    */   
/* 36 */   void initializeAttrName(String paramString) { this.attrName = paramString; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   String getAttributeName() { return this.attrName; }
/*    */ 
/*    */ 
/*    */   
/* 49 */   void initMirrorObject() { ((ShaderAttributeObjectRetained)this.mirror).initializeAttrName(this.attrName); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\ShaderAttributeRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */