/*     */ package com.sun.j3d.utils.scenegraph.io.state.com.sun.j3d.utils.geometry;
/*     */ 
/*     */ import com.sun.j3d.utils.geometry.Text2D;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d.LeafState;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.Shape3D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Text2DState
/*     */   extends LeafState
/*     */ {
/*     */   private String text;
/*     */   private Color3f color;
/*     */   private String fontName;
/*     */   private int fontSize;
/*     */   private int fontStyle;
/*     */   
/*     */   public Text2DState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  71 */     super(paramSymbolTableData, paramController);
/*     */     
/*  73 */     if (this.node != null) {
/*  74 */       Text2D text2D = (Text2D)this.node;
/*  75 */       this.text = text2D.getString();
/*  76 */       this.color = text2D.getColor();
/*  77 */       this.fontName = text2D.getFontName();
/*  78 */       this.fontSize = text2D.getFontSize();
/*  79 */       this.fontStyle = text2D.getFontStyle();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  84 */     super.writeObject(paramDataOutput);
/*  85 */     this.control.writeBounds(paramDataOutput, ((Shape3D)this.node).getCollisionBounds());
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  89 */     super.readObject(paramDataInput);
/*  90 */     ((Shape3D)this.node).setCollisionBounds(this.control.readBounds(paramDataInput));
/*     */   }
/*     */   
/*     */   public void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/*  94 */     super.writeConstructorParams(paramDataOutput);
/*     */     
/*  96 */     paramDataOutput.writeUTF(this.text);
/*  97 */     this.control.writeColor3f(paramDataOutput, this.color);
/*  98 */     paramDataOutput.writeUTF(this.fontName);
/*  99 */     paramDataOutput.writeInt(this.fontSize);
/* 100 */     paramDataOutput.writeInt(this.fontStyle);
/*     */   }
/*     */   
/*     */   public void readConstructorParams(DataInput paramDataInput) throws IOException {
/* 104 */     super.readConstructorParams(paramDataInput);
/*     */     
/* 106 */     this.text = paramDataInput.readUTF();
/* 107 */     this.color = this.control.readColor3f(paramDataInput);
/* 108 */     this.fontName = paramDataInput.readUTF();
/* 109 */     this.fontSize = paramDataInput.readInt();
/* 110 */     this.fontStyle = paramDataInput.readInt();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public SceneGraphObject createNode(Class paramClass) { return (Text2D)createNode(paramClass, new Class[] { String.class, Color3f.class, String.class, int.class, int.class }, new Object[] { this.text, this.color, this.fontName, new Integer(this.fontSize), new Integer(this.fontStyle) }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   protected SceneGraphObject createNode() { return new Text2D(this.text, this.color, this.fontName, this.fontSize, this.fontStyle); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\com\sun\j3\\utils\geometry\Text2DState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */