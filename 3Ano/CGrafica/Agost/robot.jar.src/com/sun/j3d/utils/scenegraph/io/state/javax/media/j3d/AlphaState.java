/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.Alpha;
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
/*    */ public class AlphaState
/*    */   extends NodeComponentState
/*    */ {
/* 58 */   public AlphaState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 62 */     super.writeObject(paramDataOutput);
/* 63 */     Alpha alpha = (Alpha)this.node;
/* 64 */     paramDataOutput.writeLong(alpha.getAlphaAtOneDuration());
/* 65 */     paramDataOutput.writeLong(alpha.getAlphaAtZeroDuration());
/* 66 */     paramDataOutput.writeLong(alpha.getDecreasingAlphaDuration());
/* 67 */     paramDataOutput.writeLong(alpha.getDecreasingAlphaRampDuration());
/* 68 */     paramDataOutput.writeLong(alpha.getIncreasingAlphaDuration());
/* 69 */     paramDataOutput.writeLong(alpha.getIncreasingAlphaRampDuration());
/* 70 */     paramDataOutput.writeInt(alpha.getLoopCount());
/* 71 */     paramDataOutput.writeInt(alpha.getMode());
/* 72 */     paramDataOutput.writeLong(alpha.getPhaseDelayDuration());
/* 73 */     paramDataOutput.writeLong(alpha.getStartTime());
/* 74 */     paramDataOutput.writeLong(alpha.getTriggerTime());
/* 75 */     paramDataOutput.writeLong(alpha.getPauseTime());
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 79 */     super.readObject(paramDataInput);
/* 80 */     Alpha alpha = (Alpha)this.node;
/* 81 */     alpha.setAlphaAtOneDuration(paramDataInput.readLong());
/* 82 */     alpha.setAlphaAtZeroDuration(paramDataInput.readLong());
/* 83 */     alpha.setDecreasingAlphaDuration(paramDataInput.readLong());
/* 84 */     alpha.setDecreasingAlphaRampDuration(paramDataInput.readLong());
/* 85 */     alpha.setIncreasingAlphaDuration(paramDataInput.readLong());
/* 86 */     alpha.setIncreasingAlphaRampDuration(paramDataInput.readLong());
/* 87 */     alpha.setLoopCount(paramDataInput.readInt());
/* 88 */     alpha.setMode(paramDataInput.readInt());
/* 89 */     alpha.setPhaseDelayDuration(paramDataInput.readLong());
/* 90 */     alpha.setStartTime(paramDataInput.readLong());
/* 91 */     alpha.setTriggerTime(paramDataInput.readLong());
/* 92 */     long l = paramDataInput.readLong();
/* 93 */     if (l != 0L) alpha.pause(l);
/*    */   
/*    */   }
/*    */   
/* 97 */   protected SceneGraphObject createNode() { return new Alpha(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\AlphaState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */