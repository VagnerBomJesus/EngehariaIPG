/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.MediaContainer;
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
/*    */ public class MediaContainerState
/*    */   extends NodeComponentState
/*    */ {
/* 58 */   public MediaContainerState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 62 */     super.writeObject(paramDataOutput);
/*    */     
/* 64 */     paramDataOutput.writeBoolean(((MediaContainer)this.node).getCacheEnable());
/* 65 */     paramDataOutput.writeUTF(((MediaContainer)this.node).getURLString());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 69 */     super.readObject(paramDataInput);
/*    */     
/* 71 */     ((MediaContainer)this.node).setCacheEnable(paramDataInput.readBoolean());
/* 72 */     ((MediaContainer)this.node).setURLString(paramDataInput.readUTF());
/*    */   }
/*    */ 
/*    */   
/* 76 */   protected SceneGraphObject createNode() { return new MediaContainer(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\MediaContainerState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */