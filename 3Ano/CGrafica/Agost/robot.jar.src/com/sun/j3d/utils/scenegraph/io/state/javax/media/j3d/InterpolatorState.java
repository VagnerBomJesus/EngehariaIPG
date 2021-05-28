/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.Alpha;
/*    */ import javax.media.j3d.Interpolator;
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
/*    */ public abstract class InterpolatorState
/*    */   extends BehaviorState
/*    */ {
/* 58 */   private int alpha = 0;
/*    */ 
/*    */   
/* 61 */   public InterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 65 */     super.writeObject(paramDataOutput);
/* 66 */     paramDataOutput.writeInt(this.control.getSymbolTable().addReference(((Interpolator)this.node).getAlpha()));
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 70 */     super.readObject(paramDataInput);
/* 71 */     this.alpha = paramDataInput.readInt();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 80 */   public void addSubReference() { this.control.getSymbolTable().incNodeComponentRefCount(this.alpha); }
/*    */ 
/*    */   
/*    */   public void buildGraph() {
/* 84 */     ((Interpolator)this.node).setAlpha((Alpha)this.control.getSymbolTable().getJ3dNode(this.alpha));
/* 85 */     super.buildGraph();
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\InterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */