/*    */ package javax.media.j3d;
/*    */ 
/*    */ import java.util.ArrayList;
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
/*    */ abstract class LeafRetained
/*    */   extends NodeRetained
/*    */ {
/* 23 */   SwitchState switchState = null;
/*    */ 
/*    */   
/*    */   boolean boundsDirty = false;
/*    */ 
/*    */ 
/*    */   
/*    */   void updateBoundingLeaf() {}
/*    */ 
/*    */ 
/*    */   
/* 34 */   protected Object clone(boolean paramBoolean) { return clone(); }
/*    */   
/*    */   void updateMirrorObject(Object[] paramArrayOfObject) {}
/*    */   
/*    */   void updateTransformChange() {}
/*    */   
/*    */   void updateBounds() {}
/*    */   
/*    */   void getMirrorObjects(ArrayList paramArrayList, HashKey paramHashKey) {}
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\LeafRetained.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */