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
/*    */ class NativeDrawable
/*    */   implements Drawable
/*    */ {
/*    */   private long nativeDrawable;
/*    */   
/* 25 */   NativeDrawable(long paramLong) { this.nativeDrawable = paramLong; }
/*    */ 
/*    */ 
/*    */   
/* 29 */   long getNativeDrawable() { return this.nativeDrawable; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\NativeDrawable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */