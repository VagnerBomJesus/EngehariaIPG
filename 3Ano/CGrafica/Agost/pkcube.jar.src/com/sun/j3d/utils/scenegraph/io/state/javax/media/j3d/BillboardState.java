/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.Billboard;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Vector3f;
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
/*     */ public class BillboardState
/*     */   extends BehaviorState
/*     */ {
/*     */   private int target;
/*     */   
/*     */   public BillboardState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  63 */     super(paramSymbolTableData, paramController);
/*     */     
/*  65 */     if (this.node != null) {
/*  66 */       this.target = paramController.getSymbolTable().addReference(((Billboard)this.node).getTarget());
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  71 */     super.writeObject(paramDataOutput);
/*     */     
/*  73 */     paramDataOutput.writeInt(((Billboard)this.node).getAlignmentMode());
/*     */     
/*  75 */     Vector3f vector3f = new Vector3f();
/*  76 */     ((Billboard)this.node).getAlignmentAxis(vector3f);
/*     */     
/*  78 */     Point3f point3f = new Point3f();
/*  79 */     ((Billboard)this.node).getRotationPoint(point3f);
/*     */     
/*  81 */     this.control.writeVector3f(paramDataOutput, vector3f);
/*  82 */     this.control.writePoint3f(paramDataOutput, point3f);
/*     */     
/*  84 */     paramDataOutput.writeInt(this.target);
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  88 */     super.readObject(paramDataInput);
/*     */     
/*  90 */     ((Billboard)this.node).setAlignmentMode(paramDataInput.readInt());
/*  91 */     ((Billboard)this.node).setAlignmentAxis(this.control.readVector3f(paramDataInput));
/*  92 */     ((Billboard)this.node).setRotationPoint(this.control.readPoint3f(paramDataInput));
/*     */     
/*  94 */     this.target = paramDataInput.readInt();
/*     */   }
/*     */   
/*     */   public void buildGraph() {
/*  98 */     ((Billboard)this.node).setTarget((TransformGroup)this.control.getSymbolTable().getJ3dNode(this.target));
/*  99 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/* 103 */   protected SceneGraphObject createNode() { return new Billboard(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\BillboardState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */