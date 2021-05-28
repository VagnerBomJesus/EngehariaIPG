/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.RotPosScalePathInterpolator;
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
/*     */ public class RotPosScalePathInterpolatorState
/*     */   extends PathInterpolatorState
/*     */ {
/*     */   private Point3f[] positions;
/*     */   private Quat4f[] quats;
/*     */   private float[] scales;
/*     */   
/*  66 */   public RotPosScalePathInterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  70 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  72 */     this.positions = new Point3f[this.knots.length];
/*  73 */     this.quats = new Quat4f[this.knots.length];
/*  74 */     this.scales = new float[this.knots.length]; byte b;
/*  75 */     for (b = 0; b < this.positions.length; b++) {
/*  76 */       this.positions[b] = new Point3f();
/*  77 */       this.quats[b] = new Quat4f();
/*     */     } 
/*     */     
/*  80 */     ((RotPosScalePathInterpolator)this.node).getPositions(this.positions);
/*  81 */     ((RotPosScalePathInterpolator)this.node).getQuats(this.quats);
/*  82 */     ((RotPosScalePathInterpolator)this.node).getScales(this.scales);
/*  83 */     for (b = 0; b < this.positions.length; b++) {
/*  84 */       this.control.writePoint3f(paramDataOutput, this.positions[b]);
/*  85 */       this.control.writeQuat4f(paramDataOutput, this.quats[b]);
/*  86 */       paramDataOutput.writeFloat(this.scales[b]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  91 */     super.readConstructorParams(paramDataInput);
/*     */     
/*  93 */     this.positions = new Point3f[this.knots.length];
/*  94 */     this.quats = new Quat4f[this.knots.length];
/*  95 */     this.scales = new float[this.knots.length];
/*  96 */     for (byte b = 0; b < this.positions.length; b++) {
/*  97 */       this.positions[b] = this.control.readPoint3f(paramDataInput);
/*  98 */       this.quats[b] = this.control.readQuat4f(paramDataInput);
/*  99 */       this.scales[b] = paramDataInput.readFloat();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 104 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { javax.media.j3d.Alpha.class, javax.media.j3d.TransformGroup.class, Transform3D.class, this.knots.getClass(), this.quats.getClass(), this.positions.getClass(), this.scales.getClass() }, new Object[] { null, null, new Transform3D(), this.knots, this.quats, this.positions, this.scales }); }
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
/* 122 */   protected SceneGraphObject createNode() { return new RotPosScalePathInterpolator(null, null, new Transform3D(), this.knots, this.quats, this.positions, this.scales); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\RotPosScalePathInterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */