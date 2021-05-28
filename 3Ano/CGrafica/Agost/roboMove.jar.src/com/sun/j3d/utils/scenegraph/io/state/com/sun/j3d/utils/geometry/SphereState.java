/*     */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import com.sun.j3d.utils.geometry.Sphere;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.Appearance;
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
/*     */ public class SphereState
/*     */   extends PrimitiveState
/*     */ {
/*     */   private float radius;
/*     */   private int divisions;
/*     */   private int bodyAppearance;
/*     */   
/*     */   public SphereState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  61 */     super(paramSymbolTableData, paramController);
/*     */     
/*  63 */     if (this.node != null) {
/*  64 */       this.bodyAppearance = paramController.getSymbolTable().addReference(((Sphere)this.node).getShape(0).getAppearance());
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  69 */     super.writeObject(paramDataOutput);
/*     */     
/*  71 */     paramDataOutput.writeInt(this.bodyAppearance);
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  75 */     super.readObject(paramDataInput);
/*     */     
/*  77 */     this.bodyAppearance = paramDataInput.readInt();
/*     */   }
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  81 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  83 */     paramDataOutput.writeFloat(((Sphere)this.node).getRadius());
/*  84 */     paramDataOutput.writeInt(((Sphere)this.node).getDivisions());
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  88 */     super.readConstructorParams(paramDataInput);
/*     */     
/*  90 */     this.radius = paramDataInput.readFloat();
/*  91 */     this.divisions = paramDataInput.readInt();
/*     */   }
/*     */   
/*     */   public void buildGraph() {
/*  95 */     ((Sphere)this.node).setAppearance((Appearance)this.control.getSymbolTable().getJ3dNode(this.bodyAppearance));
/*  96 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public SceneGraphObject createNode(Class paramClass) { return (Sphere)createNode(paramClass, new Class[] { float.class, int.class, int.class, Appearance.class }, new Object[] { new Float(this.radius), new Integer(this.primflags), new Integer(this.divisions), null }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   protected SceneGraphObject createNode() { return new Sphere(this.radius, this.primflags, this.divisions, null); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\utils\geometry\SphereState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */