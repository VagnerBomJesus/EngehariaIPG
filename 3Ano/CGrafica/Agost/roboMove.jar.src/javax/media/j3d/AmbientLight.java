/*    */ package javax.media.j3d;
/*    */ 
/*    */ import javax.vecmath.Color3f;
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
/*    */ public class AmbientLight
/*    */   extends Light
/*    */ {
/*    */   public AmbientLight() {}
/*    */   
/* 47 */   public AmbientLight(Color3f paramColor3f) { super(paramColor3f); }
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
/* 58 */   public AmbientLight(boolean paramBoolean, Color3f paramColor3f) { super(paramBoolean, paramColor3f); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void createRetained() {
/* 66 */     this.retained = new AmbientLightRetained();
/* 67 */     this.retained.setSource(this);
/*    */   }
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
/*    */   public Node cloneNode(boolean paramBoolean) {
/* 85 */     AmbientLight ambientLight = new AmbientLight();
/* 86 */     ambientLight.duplicateNode(this, paramBoolean);
/* 87 */     return ambientLight;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\AmbientLight.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */