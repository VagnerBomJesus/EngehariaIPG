/*    */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.universe;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.BranchGroupState;
/*    */ import com.sun.j3d.utils.universe.ViewerAvatar;
/*    */ import javax.media.j3d.SceneGraphObject;
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
/*    */ public class ViewerAvatarState
/*    */   extends BranchGroupState
/*    */ {
/* 59 */   public ViewerAvatarState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/* 63 */   protected SceneGraphObject createNode() { return new ViewerAvatar(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\util\\universe\ViewerAvatarState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */