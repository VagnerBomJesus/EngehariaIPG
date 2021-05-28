/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.RotationPathInterpolator;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.vecmath.Quat4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RotationPathInterpolatorState
/*     */   extends PathInterpolatorState
/*     */ {
/*     */   private Quat4f[] quats;
/*     */   
/*  63 */   public RotationPathInterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  67 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  69 */     this.quats = new Quat4f[this.knots.length]; byte b;
/*  70 */     for (b = 0; b < this.quats.length; b++) {
/*  71 */       this.quats[b] = new Quat4f();
/*     */     }
/*     */     
/*  74 */     ((RotationPathInterpolator)this.node).getQuats(this.quats);
/*  75 */     for (b = 0; b < this.quats.length; b++) {
/*  76 */       this.control.writeQuat4f(paramDataOutput, this.quats[b]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  81 */     super.readConstructorParams(paramDataInput);
/*     */     
/*  83 */     this.quats = new Quat4f[this.knots.length];
/*  84 */     for (byte b = 0; b < this.quats.length; b++) {
/*  85 */       this.quats[b] = this.control.readQuat4f(paramDataInput);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  90 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { javax.media.j3d.Alpha.class, javax.media.j3d.TransformGroup.class, Transform3D.class, this.knots.getClass(), this.quats.getClass() }, new Object[] { null, null, new Transform3D(), this.knots, this.quats }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   protected SceneGraphObject createNode() { return new RotationPathInterpolator(null, null, new Transform3D(), this.knots, this.quats); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\RotationPathInterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */