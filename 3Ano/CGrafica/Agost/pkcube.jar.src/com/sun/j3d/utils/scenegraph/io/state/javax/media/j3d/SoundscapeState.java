/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.AuralAttributes;
/*    */ import javax.media.j3d.BoundingLeaf;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.media.j3d.Soundscape;
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
/*    */ public class SoundscapeState
/*    */   extends LeafState
/*    */ {
/*    */   private int boundingLeaf;
/*    */   private int auralAttributes;
/*    */   
/* 63 */   public SoundscapeState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 68 */     super.writeObject(paramDataOutput);
/*    */     
/* 70 */     paramDataOutput.writeInt(this.control.getSymbolTable().addReference(((Soundscape)this.node).getApplicationBoundingLeaf()));
/* 71 */     this.control.writeBounds(paramDataOutput, ((Soundscape)this.node).getApplicationBounds());
/* 72 */     paramDataOutput.writeInt(this.control.getSymbolTable().addReference(((Soundscape)this.node).getAuralAttributes()));
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 76 */     super.readObject(paramDataInput);
/*    */     
/* 78 */     this.boundingLeaf = paramDataInput.readInt();
/* 79 */     ((Soundscape)this.node).setApplicationBounds(this.control.readBounds(paramDataInput));
/* 80 */     this.auralAttributes = paramDataInput.readInt();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 89 */   public void addSubReference() { this.control.getSymbolTable().incNodeComponentRefCount(this.auralAttributes); }
/*    */ 
/*    */   
/*    */   public void buildGraph() {
/* 93 */     ((Soundscape)this.node).setApplicationBoundingLeaf((BoundingLeaf)this.control.getSymbolTable().getJ3dNode(this.boundingLeaf));
/* 94 */     ((Soundscape)this.node).setAuralAttributes((AuralAttributes)this.control.getSymbolTable().getJ3dNode(this.auralAttributes));
/* 95 */     super.buildGraph();
/*    */   }
/*    */ 
/*    */   
/* 99 */   protected SceneGraphObject createNode() { return new Soundscape(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\SoundscapeState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */