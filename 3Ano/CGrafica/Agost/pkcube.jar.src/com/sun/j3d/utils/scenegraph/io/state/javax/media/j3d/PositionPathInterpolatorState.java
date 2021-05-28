/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.PositionPathInterpolator;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.vecmath.Point3f;
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
/*     */ public class PositionPathInterpolatorState
/*     */   extends PathInterpolatorState
/*     */ {
/*     */   private Point3f[] positions;
/*     */   
/*  63 */   public PositionPathInterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  67 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  69 */     this.positions = new Point3f[this.knots.length]; byte b;
/*  70 */     for (b = 0; b < this.positions.length; b++) {
/*  71 */       this.positions[b] = new Point3f();
/*     */     }
/*  73 */     ((PositionPathInterpolator)this.node).getPositions(this.positions);
/*  74 */     for (b = 0; b < this.positions.length; b++)
/*  75 */       this.control.writePoint3f(paramDataOutput, this.positions[b]); 
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  79 */     super.readConstructorParams(paramDataInput);
/*     */     
/*  81 */     this.positions = new Point3f[this.knots.length];
/*  82 */     for (byte b = 0; b < this.positions.length; b++) {
/*  83 */       this.positions[b] = this.control.readPoint3f(paramDataInput);
/*     */     }
/*     */   }
/*     */   
/*  87 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { javax.media.j3d.Alpha.class, javax.media.j3d.TransformGroup.class, Transform3D.class, this.knots.getClass(), this.positions.getClass() }, new Object[] { null, null, new Transform3D(), this.knots, this.positions }); }
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
/* 100 */   protected SceneGraphObject createNode() { return new PositionPathInterpolator(null, null, new Transform3D(), this.knots, this.positions); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\PositionPathInterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */