/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.ColoringAttributes;
/*     */ import javax.media.j3d.LineAttributes;
/*     */ import javax.media.j3d.Material;
/*     */ import javax.media.j3d.PointAttributes;
/*     */ import javax.media.j3d.PolygonAttributes;
/*     */ import javax.media.j3d.RenderingAttributes;
/*     */ import javax.media.j3d.SceneGraphObject;
/*     */ import javax.media.j3d.TexCoordGeneration;
/*     */ import javax.media.j3d.Texture;
/*     */ import javax.media.j3d.TextureAttributes;
/*     */ import javax.media.j3d.TextureUnitState;
/*     */ import javax.media.j3d.TransparencyAttributes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AppearanceState
/*     */   extends NodeComponentState
/*     */ {
/*  68 */   private int polygonAttributes = 0;
/*  69 */   private int renderingAttributes = 0;
/*  70 */   private int coloringAttributes = 0;
/*  71 */   private int lineAttributes = 0;
/*  72 */   private int material = 0;
/*  73 */   private int pointAttributes = 0;
/*  74 */   private int texCoordGeneration = 0;
/*  75 */   private int texture = 0;
/*  76 */   private int textureAttributes = 0;
/*     */   private int[] textureUnitState;
/*  78 */   private int transparencyAttributes = 0;
/*     */   
/*     */   public AppearanceState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  81 */     super(paramSymbolTableData, paramController);
/*     */     
/*  83 */     if (this.node != null) {
/*  84 */       Appearance appearance = (Appearance)this.node;
/*  85 */       this.polygonAttributes = paramController.getSymbolTable().addReference(appearance.getPolygonAttributes());
/*  86 */       this.renderingAttributes = paramController.getSymbolTable().addReference(appearance.getRenderingAttributes());
/*  87 */       this.coloringAttributes = paramController.getSymbolTable().addReference(appearance.getColoringAttributes());
/*  88 */       this.lineAttributes = paramController.getSymbolTable().addReference(appearance.getLineAttributes());
/*  89 */       this.material = paramController.getSymbolTable().addReference(appearance.getMaterial());
/*  90 */       this.pointAttributes = paramController.getSymbolTable().addReference(appearance.getPointAttributes());
/*  91 */       this.texCoordGeneration = paramController.getSymbolTable().addReference(appearance.getTexCoordGeneration());
/*  92 */       this.texture = paramController.getSymbolTable().addReference(appearance.getTexture());
/*  93 */       this.textureAttributes = paramController.getSymbolTable().addReference(appearance.getTextureAttributes());
/*     */       
/*  95 */       TextureUnitState[] arrayOfTextureUnitState = appearance.getTextureUnitState();
/*  96 */       if (arrayOfTextureUnitState != null) {
/*  97 */         this.textureUnitState = new int[arrayOfTextureUnitState.length];
/*  98 */         for (byte b = 0; b < arrayOfTextureUnitState.length; b++)
/*  99 */           this.textureUnitState[b] = paramController.getSymbolTable().addReference(arrayOfTextureUnitState[b]); 
/*     */       } else {
/* 101 */         this.textureUnitState = new int[0];
/*     */       } 
/* 103 */       this.transparencyAttributes = paramController.getSymbolTable().addReference(appearance.getTransparencyAttributes());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 108 */     super.writeObject(paramDataOutput);
/*     */     
/* 110 */     paramDataOutput.writeInt(this.polygonAttributes);
/* 111 */     paramDataOutput.writeInt(this.renderingAttributes);
/* 112 */     paramDataOutput.writeInt(this.coloringAttributes);
/* 113 */     paramDataOutput.writeInt(this.lineAttributes);
/* 114 */     paramDataOutput.writeInt(this.material);
/* 115 */     paramDataOutput.writeInt(this.pointAttributes);
/* 116 */     paramDataOutput.writeInt(this.texCoordGeneration);
/* 117 */     paramDataOutput.writeInt(this.texture);
/* 118 */     paramDataOutput.writeInt(this.textureAttributes);
/* 119 */     paramDataOutput.writeInt(this.textureUnitState.length);
/* 120 */     for (byte b = 0; b < this.textureUnitState.length; b++)
/* 121 */       paramDataOutput.writeInt(this.textureUnitState[b]); 
/* 122 */     paramDataOutput.writeInt(this.transparencyAttributes);
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/* 126 */     super.readObject(paramDataInput);
/* 127 */     this.polygonAttributes = paramDataInput.readInt();
/* 128 */     this.renderingAttributes = paramDataInput.readInt();
/* 129 */     this.coloringAttributes = paramDataInput.readInt();
/* 130 */     this.lineAttributes = paramDataInput.readInt();
/* 131 */     this.material = paramDataInput.readInt();
/* 132 */     this.pointAttributes = paramDataInput.readInt();
/* 133 */     this.texCoordGeneration = paramDataInput.readInt();
/* 134 */     this.texture = paramDataInput.readInt();
/* 135 */     this.textureAttributes = paramDataInput.readInt();
/* 136 */     this.textureUnitState = new int[paramDataInput.readInt()];
/* 137 */     for (byte b = 0; b < this.textureUnitState.length; b++)
/* 138 */       this.textureUnitState[b] = paramDataInput.readInt(); 
/* 139 */     this.transparencyAttributes = paramDataInput.readInt();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSubReference() {
/* 148 */     this.control.getSymbolTable().incNodeComponentRefCount(this.polygonAttributes);
/* 149 */     this.control.getSymbolTable().incNodeComponentRefCount(this.renderingAttributes);
/* 150 */     this.control.getSymbolTable().incNodeComponentRefCount(this.coloringAttributes);
/* 151 */     this.control.getSymbolTable().incNodeComponentRefCount(this.lineAttributes);
/* 152 */     this.control.getSymbolTable().incNodeComponentRefCount(this.material);
/* 153 */     this.control.getSymbolTable().incNodeComponentRefCount(this.pointAttributes);
/* 154 */     this.control.getSymbolTable().incNodeComponentRefCount(this.texCoordGeneration);
/* 155 */     this.control.getSymbolTable().incNodeComponentRefCount(this.textureAttributes);
/* 156 */     this.control.getSymbolTable().incNodeComponentRefCount(this.texture);
/* 157 */     for (byte b = 0; b < this.textureUnitState.length; b++)
/* 158 */       this.control.getSymbolTable().incNodeComponentRefCount(this.textureUnitState[b]); 
/* 159 */     this.control.getSymbolTable().incNodeComponentRefCount(this.transparencyAttributes);
/*     */   }
/*     */   
/*     */   public void buildGraph() {
/* 163 */     Appearance appearance = (Appearance)this.node;
/* 164 */     appearance.setPolygonAttributes((PolygonAttributes)this.control.getSymbolTable().getJ3dNode(this.polygonAttributes));
/* 165 */     appearance.setRenderingAttributes((RenderingAttributes)this.control.getSymbolTable().getJ3dNode(this.renderingAttributes));
/* 166 */     appearance.setColoringAttributes((ColoringAttributes)this.control.getSymbolTable().getJ3dNode(this.coloringAttributes));
/* 167 */     appearance.setLineAttributes((LineAttributes)this.control.getSymbolTable().getJ3dNode(this.lineAttributes));
/* 168 */     appearance.setMaterial((Material)this.control.getSymbolTable().getJ3dNode(this.material));
/* 169 */     appearance.setPointAttributes((PointAttributes)this.control.getSymbolTable().getJ3dNode(this.pointAttributes));
/* 170 */     appearance.setTexCoordGeneration((TexCoordGeneration)this.control.getSymbolTable().getJ3dNode(this.texCoordGeneration));
/* 171 */     appearance.setTextureAttributes((TextureAttributes)this.control.getSymbolTable().getJ3dNode(this.textureAttributes));
/* 172 */     appearance.setTexture((Texture)this.control.getSymbolTable().getJ3dNode(this.texture));
/*     */     
/* 174 */     TextureUnitState[] arrayOfTextureUnitState = new TextureUnitState[this.textureUnitState.length];
/* 175 */     for (byte b = 0; b < this.textureUnitState.length; b++) {
/* 176 */       arrayOfTextureUnitState[b] = (TextureUnitState)this.control.getSymbolTable().getJ3dNode(this.textureUnitState[b]);
/*     */     }
/* 178 */     if (arrayOfTextureUnitState.length > 0) {
/* 179 */       appearance.setTextureUnitState(arrayOfTextureUnitState);
/*     */     }
/* 181 */     appearance.setTransparencyAttributes((TransparencyAttributes)this.control.getSymbolTable().getJ3dNode(this.transparencyAttributes));
/*     */     
/* 183 */     super.buildGraph();
/*     */   }
/*     */ 
/*     */   
/* 187 */   protected SceneGraphObject createNode() { return new Appearance(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\AppearanceState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */