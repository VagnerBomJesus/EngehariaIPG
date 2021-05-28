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
/*    */ class RenderAtomListInfo
/*    */ {
/* 21 */   RenderAtom renderAtom = null;
/*    */ 
/*    */ 
/*    */   
/*    */   int index;
/*    */ 
/*    */   
/* 28 */   RenderAtomListInfo next = null;
/* 29 */   RenderAtomListInfo prev = null;
/*    */ 
/*    */   
/* 32 */   int groupType = 0;
/*    */ 
/*    */ 
/*    */   
/* 36 */   Transform3D infLocalToVworld = null;
/* 37 */   Transform3D localToVworld = null;
/*    */ 
/*    */ 
/*    */   
/* 41 */   GeometryRetained geometry() { return this.renderAtom.geometryAtom.geometryArray[this.index]; }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\RenderAtomListInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */