/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.RotPosPathInterpolator;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.vecmath.Point3f;
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
/*     */ public class RotPosPathInterpolatorState
/*     */   extends PathInterpolatorState
/*     */ {
/*     */   private Point3f[] positions;
/*     */   private Quat4f[] quats;
/*     */   
/*  65 */   public RotPosPathInterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  69 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  71 */     this.positions = new Point3f[this.knots.length];
/*  72 */     this.quats = new Quat4f[this.knots.length]; byte b;
/*  73 */     for (b = 0; b < this.positions.length; b++) {
/*  74 */       this.positions[b] = new Point3f();
/*  75 */       this.quats[b] = new Quat4f();
/*     */     } 
/*     */     
/*  78 */     ((RotPosPathInterpolator)this.node).getPositions(this.positions);
/*  79 */     ((RotPosPathInterpolator)this.node).getQuats(this.quats);
/*  80 */     for (b = 0; b < this.positions.length; b++) {
/*  81 */       this.control.writePoint3f(paramDataOutput, this.positions[b]);
/*  82 */       this.control.writeQuat4f(paramDataOutput, this.quats[b]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  87 */     super.readConstructorParams(paramDataInput);
/*     */     
/*  89 */     this.positions = new Point3f[this.knots.length];
/*  90 */     this.quats = new Quat4f[this.knots.length];
/*  91 */     for (byte b = 0; b < this.positions.length; b++) {
/*  92 */       this.positions[b] = this.control.readPoint3f(paramDataInput);
/*  93 */       this.quats[b] = this.control.readQuat4f(paramDataInput);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  98 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { javax.media.j3d.Alpha.class, javax.media.j3d.TransformGroup.class, Transform3D.class, this.knots.getClass(), this.quats.getClass(), this.positions.getClass() }, new Object[] { null, null, new Transform3D(), this.knots, this.quats, this.positions }); }
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
/* 114 */   protected SceneGraphObject createNode() { return new RotPosPathInterpolator(null, null, new Transform3D(), this.knots, this.quats, this.positions); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\RotPosPathInterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */