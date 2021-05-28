/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.ColorInterpolator;
/*     */ import javax.media.j3d.Material;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.vecmath.Color3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ColorInterpolatorState
/*     */   extends InterpolatorState
/*     */ {
/*     */   private int target;
/*     */   
/*     */   public ColorInterpolatorState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  62 */     super(paramSymbolTableData, paramController);
/*     */     
/*  64 */     if (this.node != null)
/*  65 */       this.target = paramController.getSymbolTable().addReference(((ColorInterpolator)this.node).getTarget()); 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  69 */     super.writeObject(paramDataOutput);
/*     */     
/*  71 */     paramDataOutput.writeInt(this.target);
/*  72 */     Color3f color3f = new Color3f();
/*  73 */     ((ColorInterpolator)this.node).getStartColor(color3f);
/*  74 */     this.control.writeColor3f(paramDataOutput, color3f);
/*  75 */     ((ColorInterpolator)this.node).getEndColor(color3f);
/*  76 */     this.control.writeColor3f(paramDataOutput, color3f);
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  80 */     super.readObject(paramDataInput);
/*     */     
/*  82 */     this.target = paramDataInput.readInt();
/*  83 */     ((ColorInterpolator)this.node).setStartColor(this.control.readColor3f(paramDataInput));
/*  84 */     ((ColorInterpolator)this.node).setEndColor(this.control.readColor3f(paramDataInput));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public void addSubReference() { this.control.getSymbolTable().incNodeComponentRefCount(this.target); }
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/*  97 */     ((ColorInterpolator)this.node).setTarget((Material)this.control.getSymbolTable().getJ3dNode(this.target));
/*  98 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/* 102 */   public SceneGraphObject createNode(Class paramClass) { return createNode(paramClass, new Class[] { javax.media.j3d.Alpha.class, Material.class }, new Object[] { null, null }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   protected SceneGraphObject createNode() { return new ColorInterpolator(null, null); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\ColorInterpolatorState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */