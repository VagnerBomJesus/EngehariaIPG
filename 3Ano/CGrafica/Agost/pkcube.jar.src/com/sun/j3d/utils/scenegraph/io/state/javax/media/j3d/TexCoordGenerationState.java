/*    */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*    */ 
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*    */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import javax.media.j3d.SceneGraphObject;
/*    */ import javax.media.j3d.TexCoordGeneration;
/*    */ import javax.vecmath.Vector4f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TexCoordGenerationState
/*    */   extends NodeComponentState
/*    */ {
/* 59 */   public TexCoordGenerationState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*    */ 
/*    */   
/*    */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/* 63 */     super.writeObject(paramDataOutput);
/* 64 */     TexCoordGeneration texCoordGeneration = (TexCoordGeneration)this.node;
/* 65 */     Vector4f vector4f = new Vector4f();
/* 66 */     paramDataOutput.writeBoolean(texCoordGeneration.getEnable());
/* 67 */     paramDataOutput.writeInt(texCoordGeneration.getFormat());
/* 68 */     paramDataOutput.writeInt(texCoordGeneration.getGenMode());
/* 69 */     texCoordGeneration.getPlaneR(vector4f);
/* 70 */     this.control.writeVector4f(paramDataOutput, vector4f);
/* 71 */     texCoordGeneration.getPlaneS(vector4f);
/* 72 */     this.control.writeVector4f(paramDataOutput, vector4f);
/* 73 */     texCoordGeneration.getPlaneT(vector4f);
/* 74 */     this.control.writeVector4f(paramDataOutput, vector4f);
/* 75 */     texCoordGeneration.getPlaneQ(vector4f);
/* 76 */     this.control.writeVector4f(paramDataOutput, vector4f);
/*    */   }
/*    */   
/*    */   public void readObject(DataInput paramDataInput) throws IOException {
/* 80 */     super.readObject(paramDataInput);
/* 81 */     TexCoordGeneration texCoordGeneration = (TexCoordGeneration)this.node;
/* 82 */     texCoordGeneration.setEnable(paramDataInput.readBoolean());
/* 83 */     texCoordGeneration.setFormat(paramDataInput.readInt());
/* 84 */     texCoordGeneration.setGenMode(paramDataInput.readInt());
/* 85 */     texCoordGeneration.setPlaneR(this.control.readVector4f(paramDataInput));
/* 86 */     texCoordGeneration.setPlaneS(this.control.readVector4f(paramDataInput));
/* 87 */     texCoordGeneration.setPlaneT(this.control.readVector4f(paramDataInput));
/* 88 */     texCoordGeneration.setPlaneQ(this.control.readVector4f(paramDataInput));
/*    */   }
/*    */ 
/*    */   
/* 92 */   protected SceneGraphObject createNode() { return new TexCoordGeneration(); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\TexCoordGenerationState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */