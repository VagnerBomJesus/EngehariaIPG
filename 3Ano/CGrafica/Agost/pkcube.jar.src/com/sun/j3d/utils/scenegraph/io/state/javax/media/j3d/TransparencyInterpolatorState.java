/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.TransparencyAttributes;
/*     */ import javax.media.j3d.TransparencyInterpolator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TransparencyInterpolatorState
/*     */   extends InterpolatorState
/*     */ {
/*     */   private int target;
/*     */   
/*     */   public TransparencyInterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  64 */     super(paramSymbolTableData, paramController);
/*     */     
/*  66 */     if (this.node != null)
/*  67 */       this.target = paramController.getSymbolTable().addReference(((TransparencyInterpolator)this.node).getTarget()); 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  71 */     super.writeObject(paramDataOutput);
/*     */     
/*  73 */     paramDataOutput.writeInt(this.target);
/*  74 */     paramDataOutput.writeFloat(((TransparencyInterpolator)this.node).getMinimumTransparency());
/*  75 */     paramDataOutput.writeFloat(((TransparencyInterpolator)this.node).getMaximumTransparency());
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  79 */     super.readObject(paramDataInput);
/*     */     
/*  81 */     this.target = paramDataInput.readInt();
/*  82 */     ((TransparencyInterpolator)this.node).setMinimumTransparency(paramDataInput.readFloat());
/*  83 */     ((TransparencyInterpolator)this.node).setMaximumTransparency(paramDataInput.readFloat());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void addSubReference() { this.control.getSymbolTable().incNodeComponentRefCount(this.target); }
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/*  96 */     ((TransparencyInterpolator)this.node).setTarget((TransparencyAttributes)this.control.getSymbolTable().getJ3dNode(this.target));
/*  97 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/* 101 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { javax.media.j3d.Alpha.class, TransparencyAttributes.class }, new Object[] { null, null }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   protected SceneGraphObject createNode() { return new TransparencyInterpolator(null, null); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\TransparencyInterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */