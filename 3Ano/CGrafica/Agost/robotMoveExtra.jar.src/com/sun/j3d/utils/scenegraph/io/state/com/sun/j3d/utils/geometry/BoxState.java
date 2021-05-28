/*     */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import com.sun.j3d.utils.geometry.Box;
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
/*     */ public class BoxState
/*     */   extends PrimitiveState
/*     */ {
/*     */   private float xdim;
/*     */   private float ydim;
/*     */   private float zdim;
/*     */   private int frontAppearance;
/*     */   private int backAppearance;
/*     */   private int topAppearance;
/*     */   private int bottomAppearance;
/*     */   private int leftAppearance;
/*     */   private int rightAppearance;
/*     */   
/*     */   public BoxState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  68 */     super(paramSymbolTableData, paramController);
/*     */     
/*  70 */     if (this.node != null) {
/*  71 */       this.frontAppearance = paramController.getSymbolTable().addReference(((Box)this.node).getShape(0).getAppearance());
/*  72 */       this.backAppearance = paramController.getSymbolTable().addReference(((Box)this.node).getShape(1).getAppearance());
/*  73 */       this.topAppearance = paramController.getSymbolTable().addReference(((Box)this.node).getShape(4).getAppearance());
/*  74 */       this.bottomAppearance = paramController.getSymbolTable().addReference(((Box)this.node).getShape(5).getAppearance());
/*  75 */       this.leftAppearance = paramController.getSymbolTable().addReference(((Box)this.node).getShape(3).getAppearance());
/*  76 */       this.rightAppearance = paramController.getSymbolTable().addReference(((Box)this.node).getShape(2).getAppearance());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  81 */     super.writeObject(paramDataOutput);
/*     */     
/*  83 */     paramDataOutput.writeInt(this.frontAppearance);
/*  84 */     paramDataOutput.writeInt(this.backAppearance);
/*  85 */     paramDataOutput.writeInt(this.topAppearance);
/*  86 */     paramDataOutput.writeInt(this.bottomAppearance);
/*  87 */     paramDataOutput.writeInt(this.leftAppearance);
/*  88 */     paramDataOutput.writeInt(this.rightAppearance);
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  92 */     super.readObject(paramDataInput);
/*     */     
/*  94 */     this.frontAppearance = paramDataInput.readInt();
/*  95 */     this.backAppearance = paramDataInput.readInt();
/*  96 */     this.topAppearance = paramDataInput.readInt();
/*  97 */     this.bottomAppearance = paramDataInput.readInt();
/*  98 */     this.leftAppearance = paramDataInput.readInt();
/*  99 */     this.rightAppearance = paramDataInput.readInt();
/*     */   }
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/* 103 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/* 105 */     paramDataOutput.writeFloat(((Box)this.node).getXdimension());
/* 106 */     paramDataOutput.writeFloat(((Box)this.node).getYdimension());
/* 107 */     paramDataOutput.writeFloat(((Box)this.node).getZdimension());
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/* 111 */     super.readConstructorParams(paramDataInput);
/*     */     
/* 113 */     this.xdim = paramDataInput.readFloat();
/* 114 */     this.ydim = paramDataInput.readFloat();
/* 115 */     this.zdim = paramDataInput.readFloat();
/*     */   }
/*     */   
/*     */   public void buildGraph() {
/* 119 */     if (this.frontAppearance == this.backAppearance && this.frontAppearance == this.topAppearance && this.frontAppearance == this.bottomAppearance && this.frontAppearance == this.leftAppearance && this.frontAppearance == this.rightAppearance) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 124 */       ((Box)this.node).setAppearance((Appearance)this.control.getSymbolTable().getJ3dNode(this.frontAppearance));
/*     */     } else {
/* 126 */       ((Box)this.node).setAppearance(0, (Appearance)this.control.getSymbolTable().getJ3dNode(this.frontAppearance));
/* 127 */       ((Box)this.node).setAppearance(1, (Appearance)this.control.getSymbolTable().getJ3dNode(this.backAppearance));
/* 128 */       ((Box)this.node).setAppearance(4, (Appearance)this.control.getSymbolTable().getJ3dNode(this.topAppearance));
/* 129 */       ((Box)this.node).setAppearance(5, (Appearance)this.control.getSymbolTable().getJ3dNode(this.bottomAppearance));
/* 130 */       ((Box)this.node).setAppearance(3, (Appearance)this.control.getSymbolTable().getJ3dNode(this.leftAppearance));
/* 131 */       ((Box)this.node).setAppearance(2, (Appearance)this.control.getSymbolTable().getJ3dNode(this.rightAppearance));
/*     */     } 
/*     */     
/* 134 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/* 138 */   public SceneGraphObject createNode(Class paramClass) { return (Box)createNode(paramClass, new Class[] { float.class, float.class, float.class, int.class, Appearance.class }, new Object[] { new Float(this.xdim), new Float(this.ydim), new Float(this.zdim), new Integer(this.primflags), null }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   protected SceneGraphObject createNode() { return new Box(this.xdim, this.ydim, this.zdim, this.primflags, null); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\utils\geometry\BoxState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */