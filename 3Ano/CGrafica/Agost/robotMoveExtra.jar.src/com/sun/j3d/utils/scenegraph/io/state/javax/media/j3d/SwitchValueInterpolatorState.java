/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.Switch;
/*     */ import javax.media.j3d.SwitchValueInterpolator;
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
/*     */ 
/*     */ public class SwitchValueInterpolatorState
/*     */   extends InterpolatorState
/*     */ {
/*     */   private int target;
/*     */   
/*     */   public SwitchValueInterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  64 */     super(paramSymbolTableData, paramController);
/*     */     
/*  66 */     if (this.node != null)
/*  67 */       this.target = paramController.getSymbolTable().addReference(((SwitchValueInterpolator)this.node).getTarget()); 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  71 */     super.writeObject(paramDataOutput);
/*     */     
/*  73 */     paramDataOutput.writeInt(this.target);
/*  74 */     paramDataOutput.writeInt(((SwitchValueInterpolator)this.node).getFirstChildIndex());
/*  75 */     paramDataOutput.writeInt(((SwitchValueInterpolator)this.node).getLastChildIndex());
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  79 */     super.readObject(paramDataInput);
/*     */     
/*  81 */     this.target = paramDataInput.readInt();
/*  82 */     ((SwitchValueInterpolator)this.node).setFirstChildIndex(paramDataInput.readInt());
/*  83 */     ((SwitchValueInterpolator)this.node).setLastChildIndex(paramDataInput.readInt());
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/*  88 */     ((SwitchValueInterpolator)this.node).setTarget((Switch)this.control.getSymbolTable().getJ3dNode(this.target));
/*  89 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/*  93 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { javax.media.j3d.Alpha.class, Switch.class }, new Object[] { null, null }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   protected SceneGraphObject createNode() { return new SwitchValueInterpolator(null, null); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\SwitchValueInterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */