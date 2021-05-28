/*     */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import com.sun.j3d.utils.geometry.Cylinder;
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
/*     */ public class CylinderState
/*     */   extends PrimitiveState
/*     */ {
/*  56 */   private float radius = 1.0F;
/*  57 */   private float height = 2.0F;
/*  58 */   private int xdivision = 15;
/*  59 */   private int ydivision = 1;
/*     */   private int topAppearance;
/*     */   private int bottomAppearance;
/*     */   private int bodyAppearance;
/*     */   
/*     */   public CylinderState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  65 */     super(paramSymbolTableData, paramController);
/*     */     
/*  67 */     if (this.node != null) {
/*  68 */       this.bodyAppearance = paramController.getSymbolTable().addReference(((Cylinder)this.node).getShape(0).getAppearance());
/*  69 */       this.topAppearance = paramController.getSymbolTable().addReference(((Cylinder)this.node).getShape(1).getAppearance());
/*  70 */       this.bottomAppearance = paramController.getSymbolTable().addReference(((Cylinder)this.node).getShape(2).getAppearance());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  75 */     super.writeObject(paramDataOutput);
/*  76 */     paramDataOutput.writeInt(this.topAppearance);
/*  77 */     paramDataOutput.writeInt(this.bodyAppearance);
/*  78 */     paramDataOutput.writeInt(this.bottomAppearance);
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  82 */     super.readObject(paramDataInput);
/*  83 */     this.topAppearance = paramDataInput.readInt();
/*  84 */     this.bodyAppearance = paramDataInput.readInt();
/*  85 */     this.bottomAppearance = paramDataInput.readInt();
/*     */   }
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  89 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  91 */     paramDataOutput.writeFloat(((Cylinder)this.node).getRadius());
/*  92 */     paramDataOutput.writeFloat(((Cylinder)this.node).getHeight());
/*  93 */     paramDataOutput.writeInt(((Cylinder)this.node).getXdivisions());
/*  94 */     paramDataOutput.writeInt(((Cylinder)this.node).getYdivisions());
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
/*     */   public void buildGraph() {
/* 107 */     if (this.bodyAppearance == this.topAppearance && this.bodyAppearance == this.bottomAppearance) {
/* 108 */       ((Cylinder)this.node).setAppearance((Appearance)this.control.getSymbolTable().getJ3dNode(this.bodyAppearance));
/*     */     } else {
/* 110 */       ((Cylinder)this.node).setAppearance(0, (Appearance)this.control.getSymbolTable().getJ3dNode(this.bodyAppearance));
/* 111 */       ((Cylinder)this.node).setAppearance(1, (Appearance)this.control.getSymbolTable().getJ3dNode(this.topAppearance));
/* 112 */       ((Cylinder)this.node).setAppearance(2, (Appearance)this.control.getSymbolTable().getJ3dNode(this.bottomAppearance));
/*     */     } 
/* 114 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public SceneGraphObject createNode(Class paramClass) { return (Cylinder)createNode(paramClass, new Class[] { float.class, float.class, int.class, int.class, int.class, Appearance.class }, new Object[] { new Float(this.radius), new Float(this.height), new Integer(this.primflags), new Integer(this.xdivision), new Integer(this.ydivision), null }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   protected SceneGraphObject createNode() { return new Cylinder(this.radius, this.height, this.primflags, this.xdivision, this.ydivision, null); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\utils\geometry\CylinderState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */