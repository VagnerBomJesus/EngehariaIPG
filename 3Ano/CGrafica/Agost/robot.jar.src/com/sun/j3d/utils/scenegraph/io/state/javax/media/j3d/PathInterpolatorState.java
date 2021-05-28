/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.PathInterpolator;
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
/*    */ public abstract class PathInterpolatorState
/*    */   extends TransformInterpolatorState
/*    */ {
/*    */   protected float[] knots;
/*    */   
/* 60 */   public PathInterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/* 67 */     super.writeConstructorParams(paramDataOutput);
/* 68 */     this.knots = new float[((PathInterpolator)this.node).getArrayLengths()];
/* 69 */     paramDataOutput.writeInt(this.knots.length);
/* 70 */     ((PathInterpolator)this.node).getKnots(this.knots);
/* 71 */     for (byte b = 0; b < this.knots.length; b++)
/* 72 */       paramDataOutput.writeFloat(this.knots[b]); 
/*    */   }
/*    */   
/*    */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/* 76 */     super.readConstructorParams(paramDataInput);
/* 77 */     this.knots = new float[paramDataInput.readInt()];
/* 78 */     for (byte b = 0; b < this.knots.length; b++)
/* 79 */       this.knots[b] = paramDataInput.readFloat(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\PathInterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */