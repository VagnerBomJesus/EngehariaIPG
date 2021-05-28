/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.TexCoordGeneration;
/*     */ import javax.media.j3d.Texture;
/*     */ import javax.media.j3d.TextureAttributes;
/*     */ import javax.media.j3d.TextureUnitState;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextureUnitStateState
/*     */   extends NodeComponentState
/*     */ {
/*     */   private int texCoordGeneration;
/*     */   private int texture;
/*     */   private int textureAttributes;
/*     */   
/*     */   public TextureUnitStateState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  66 */     super(paramSymbolTableData, paramController);
/*     */     
/*  68 */     if (this.node != null) {
/*  69 */       TextureUnitState textureUnitState = (TextureUnitState)this.node;
/*  70 */       this.texCoordGeneration = paramController.getSymbolTable().addReference(textureUnitState.getTexCoordGeneration());
/*  71 */       this.texture = paramController.getSymbolTable().addReference(textureUnitState.getTexture());
/*  72 */       this.textureAttributes = paramController.getSymbolTable().addReference(textureUnitState.getTextureAttributes());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  77 */     super.writeObject(paramDataOutput);
/*  78 */     paramDataOutput.writeInt(this.texCoordGeneration);
/*  79 */     paramDataOutput.writeInt(this.texture);
/*  80 */     paramDataOutput.writeInt(this.textureAttributes);
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  84 */     super.readObject(paramDataInput);
/*  85 */     TextureUnitState textureUnitState = (TextureUnitState)this.node;
/*  86 */     this.texCoordGeneration = paramDataInput.readInt();
/*  87 */     this.texture = paramDataInput.readInt();
/*  88 */     this.textureAttributes = paramDataInput.readInt();
/*     */   }
/*     */   
/*     */   public void addSubReference() {
/*  92 */     this.control.getSymbolTable().incNodeComponentRefCount(this.texCoordGeneration);
/*  93 */     this.control.getSymbolTable().incNodeComponentRefCount(this.texture);
/*  94 */     this.control.getSymbolTable().incNodeComponentRefCount(this.textureAttributes);
/*     */   }
/*     */   
/*     */   public void buildGraph() {
/*  98 */     TextureUnitState textureUnitState = (TextureUnitState)this.node;
/*  99 */     textureUnitState.setTexCoordGeneration((TexCoordGeneration)this.control.getSymbolTable().getJ3dNode(this.texCoordGeneration));
/* 100 */     textureUnitState.setTexture((Texture)this.control.getSymbolTable().getJ3dNode(this.texture));
/* 101 */     textureUnitState.setTextureAttributes((TextureAttributes)this.control.getSymbolTable().getJ3dNode(this.textureAttributes));
/* 102 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/* 106 */   protected SceneGraphObject createNode() { return new TextureUnitState(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\TextureUnitStateState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */