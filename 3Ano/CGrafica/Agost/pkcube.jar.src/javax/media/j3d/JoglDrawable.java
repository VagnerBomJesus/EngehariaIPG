/*    */ package javax.media.j3d;
/*    */ 
/*    */ import javax.media.opengl.GLDrawable;
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
/*    */ class JoglDrawable
/*    */   implements Drawable
/*    */ {
/*    */   private GLDrawable drawable;
/*    */   
/* 24 */   JoglDrawable(GLDrawable paramGLDrawable) { this.drawable = paramGLDrawable; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   GLDrawable getGLDrawable() { return this.drawable; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\JoglDrawable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */