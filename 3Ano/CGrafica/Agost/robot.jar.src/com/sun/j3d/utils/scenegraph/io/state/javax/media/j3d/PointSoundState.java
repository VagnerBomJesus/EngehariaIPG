/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.PointSound;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.vecmath.Point3f;
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
/*    */ public class PointSoundState
/*    */   extends SoundState
/*    */ {
/* 59 */   public PointSoundState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 64 */     super.writeObject(paramDataOutput);
/*    */     
/* 66 */     float[] arrayOfFloat1 = new float[((PointSound)this.node).getDistanceGainLength()];
/* 67 */     float[] arrayOfFloat2 = new float[arrayOfFloat1.length];
/*    */     
/* 69 */     ((PointSound)this.node).getDistanceGain(arrayOfFloat1, arrayOfFloat2);
/* 70 */     paramDataOutput.writeInt(arrayOfFloat1.length);
/* 71 */     for (byte b = 0; b < arrayOfFloat1.length; b++) {
/* 72 */       paramDataOutput.writeFloat(arrayOfFloat1[b]);
/* 73 */       paramDataOutput.writeFloat(arrayOfFloat2[b]);
/*    */     } 
/*    */     
/* 76 */     Point3f point3f = new Point3f();
/*    */     
/* 78 */     ((PointSound)this.node).getPosition(point3f);
/* 79 */     this.control.writePoint3f(paramDataOutput, point3f);
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 83 */     super.readObject(paramDataInput);
/*    */     
/* 85 */     float[] arrayOfFloat1 = new float[paramDataInput.readInt()];
/* 86 */     float[] arrayOfFloat2 = new float[arrayOfFloat1.length];
/* 87 */     for (byte b = 0; b < arrayOfFloat1.length; b++) {
/* 88 */       arrayOfFloat1[b] = paramDataInput.readFloat();
/* 89 */       arrayOfFloat2[b] = paramDataInput.readFloat();
/*    */     } 
/* 91 */     ((PointSound)this.node).setDistanceGain(arrayOfFloat1, arrayOfFloat2);
/*    */     
/* 93 */     ((PointSound)this.node).setPosition(this.control.readPoint3f(paramDataInput));
/*    */   }
/*    */ 
/*    */   
/* 97 */   protected SceneGraphObject createNode() { return new PointSound(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\PointSoundState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */