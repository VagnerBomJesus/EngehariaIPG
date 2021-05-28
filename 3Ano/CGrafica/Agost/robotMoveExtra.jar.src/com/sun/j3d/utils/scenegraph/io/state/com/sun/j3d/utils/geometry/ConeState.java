/*     */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import com.sun.j3d.utils.geometry.Cone;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConeState
/*     */   extends PrimitiveState
/*     */ {
/*  60 */   private float radius = 1.0F;
/*  61 */   private float height = 2.0F;
/*  62 */   private int xdivision = 15;
/*  63 */   private int ydivision = 1;
/*  64 */   private int bodyAppearance = 0;
/*  65 */   private int capAppearance = 0;
/*     */   
/*     */   public ConeState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  68 */     super(paramSymbolTableData, paramController);
/*     */     
/*  70 */     if (this.node != null) {
/*  71 */       this.bodyAppearance = paramController.getSymbolTable().addReference(((Cone)this.node).getShape(0).getAppearance());
/*  72 */       this.capAppearance = paramController.getSymbolTable().addReference(((Cone)this.node).getShape(1).getAppearance());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  77 */     super.writeObject(paramDataOutput);
/*  78 */     paramDataOutput.writeInt(this.bodyAppearance);
/*  79 */     paramDataOutput.writeInt(this.capAppearance);
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  83 */     super.readObject(paramDataInput);
/*  84 */     this.bodyAppearance = paramDataInput.readInt();
/*  85 */     this.capAppearance = paramDataInput.readInt();
/*     */   }
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  89 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  91 */     paramDataOutput.writeFloat(((Cone)this.node).getRadius());
/*  92 */     paramDataOutput.writeFloat(((Cone)this.node).getHeight());
/*  93 */     paramDataOutput.writeInt(((Cone)this.node).getXdivisions());
/*  94 */     paramDataOutput.writeInt(((Cone)this.node).getYdivisions());
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/*  98 */     super.readConstructorParams(paramDataInput);
/*     */     
/* 100 */     this.radius = paramDataInput.readFloat();
/* 101 */     this.height = paramDataInput.readFloat();
/* 102 */     this.xdivision = paramDataInput.readInt();
/* 103 */     this.ydivision = paramDataInput.readInt();
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/* 108 */     if (this.bodyAppearance == this.capAppearance) {
/* 109 */       ((Cone)this.node).setAppearance((Appearance)this.control.getSymbolTable().getJ3dNode(this.bodyAppearance));
/*     */     } else {
/* 111 */       ((Cone)this.node).setAppearance(0, (Appearance)this.control.getSymbolTable().getJ3dNode(this.bodyAppearance));
/* 112 */       ((Cone)this.node).setAppearance(1, (Appearance)this.control.getSymbolTable().getJ3dNode(this.capAppearance));
/*     */     } 
/*     */     
/* 115 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public SceneGraphObject createNode(Class paramClass) { return (Cone)createNode(paramClass, new Class[] { float.class, float.class, int.class, int.class, int.class, Appearance.class }, new Object[] { new Float(this.radius), new Float(this.height), new Integer(this.primflags), new Integer(this.xdivision), new Integer(this.ydivision), null }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   protected SceneGraphObject createNode() { return new Cone(this.radius, this.height, this.primflags, this.xdivision, this.ydivision, null); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\utils\geometry\ConeState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */