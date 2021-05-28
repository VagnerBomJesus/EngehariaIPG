/*    */ package javax.media.j3d;
/*    */ 
/*    */ import com.sun.opengl.cg.CGparameter;
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
/*    */ class JoglCgShaderParameter
/*    */   extends JoglShaderObject
/*    */ {
/*    */   private CGparameter vParam;
/*    */   private CGparameter fParam;
/*    */   
/*    */   JoglCgShaderParameter(CGparameter paramCGparameter1, CGparameter paramCGparameter2) {
/* 23 */     super(0);
/* 24 */     this.vParam = paramCGparameter1;
/* 25 */     this.fParam = paramCGparameter2;
/*    */   }
/*    */ 
/*    */   
/* 29 */   CGparameter vParam() { return this.vParam; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   CGparameter fParam() { return this.fParam; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\JoglCgShaderParameter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */