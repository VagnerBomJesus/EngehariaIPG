/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.UnresolvedBehavior;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SGIORuntimeException;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.BoundingLeaf;
/*     */ import javax.media.j3d.SceneGraphObject;
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
/*     */ public class BehaviorState
/*     */   extends LeafState
/*     */ {
/*     */   private int boundingLeaf;
/*     */   
/*  61 */   public BehaviorState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SceneGraphObject createNode(String paramString) {
/*     */     UnresolvedBehavior unresolvedBehavior;
/*     */     try {
/*  68 */       unresolvedBehavior = super.createNode(paramString);
/*  69 */     } catch (SGIORuntimeException sGIORuntimeException) {
/*  70 */       unresolvedBehavior = new UnresolvedBehavior();
/*     */     } 
/*     */     
/*  73 */     return unresolvedBehavior;
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  77 */     super.writeObject(paramDataOutput);
/*  78 */     Behavior behavior = (Behavior)this.node;
/*     */     
/*  80 */     paramDataOutput.writeBoolean(behavior.getEnable());
/*  81 */     paramDataOutput.writeInt(this.control.getSymbolTable().addReference(behavior.getSchedulingBoundingLeaf()));
/*     */     
/*  83 */     this.control.writeBounds(paramDataOutput, behavior.getSchedulingBounds());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     paramDataOutput.writeInt(behavior.getSchedulingInterval());
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  92 */     super.readObject(paramDataInput);
/*  93 */     Behavior behavior = (Behavior)this.node;
/*     */     
/*  95 */     behavior.setEnable(paramDataInput.readBoolean());
/*  96 */     this.boundingLeaf = paramDataInput.readInt();
/*  97 */     behavior.setSchedulingBounds(this.control.readBounds(paramDataInput));
/*  98 */     behavior.setSchedulingInterval(paramDataInput.readInt());
/*     */   }
/*     */   
/*     */   public void buildGraph() {
/* 102 */     ((Behavior)this.node).setSchedulingBoundingLeaf((BoundingLeaf)this.control.getSymbolTable().getJ3dNode(this.boundingLeaf));
/* 103 */     super.buildGraph();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\BehaviorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */