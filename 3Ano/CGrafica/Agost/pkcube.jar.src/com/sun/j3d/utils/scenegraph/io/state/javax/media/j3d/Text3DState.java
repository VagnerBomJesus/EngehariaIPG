/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.Font3D;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.Text3D;
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
/*     */ public class Text3DState
/*     */   extends GeometryState
/*     */ {
/*     */   private int font3d;
/*     */   
/*     */   public Text3DState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  63 */     super(paramSymbolTableData, paramController);
/*     */     
/*  65 */     if (this.node != null) {
/*  66 */       this.font3d = paramController.getSymbolTable().addReference(((Text3D)this.node).getFont3D());
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  71 */     super.writeObject(paramDataOutput);
/*     */     
/*  73 */     paramDataOutput.writeInt(((Text3D)this.node).getAlignment());
/*  74 */     paramDataOutput.writeFloat(((Text3D)this.node).getCharacterSpacing());
/*  75 */     paramDataOutput.writeInt(this.font3d);
/*     */     
/*  77 */     paramDataOutput.writeInt(((Text3D)this.node).getPath());
/*     */     
/*  79 */     Point3f point3f = new Point3f();
/*  80 */     ((Text3D)this.node).getPosition(point3f);
/*  81 */     this.control.writePoint3f(paramDataOutput, point3f);
/*     */     
/*  83 */     paramDataOutput.writeUTF(((Text3D)this.node).getString());
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  87 */     super.readObject(paramDataInput);
/*     */     
/*  89 */     ((Text3D)this.node).setAlignment(paramDataInput.readInt());
/*  90 */     ((Text3D)this.node).setCharacterSpacing(paramDataInput.readFloat());
/*  91 */     this.font3d = paramDataInput.readInt();
/*  92 */     ((Text3D)this.node).setPath(paramDataInput.readInt());
/*  93 */     ((Text3D)this.node).setPosition(this.control.readPoint3f(paramDataInput));
/*  94 */     ((Text3D)this.node).setString(paramDataInput.readUTF());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public void addSubReference() { this.control.getSymbolTable().incNodeComponentRefCount(this.font3d); }
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/* 107 */     ((Text3D)this.node).setFont3D((Font3D)this.control.getSymbolTable().getJ3dNode(this.font3d));
/* 108 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/* 112 */   protected SceneGraphObject createNode() { return new Text3D(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\Text3DState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */