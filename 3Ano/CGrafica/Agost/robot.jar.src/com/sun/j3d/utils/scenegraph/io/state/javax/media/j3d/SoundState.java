/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.BoundingLeaf;
/*     */ import javax.media.j3d.MediaContainer;
/*     */ import javax.media.j3d.Sound;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SoundState
/*     */   extends LeafState
/*     */ {
/*     */   private int boundingLeaf;
/*     */   private int mediaContainer;
/*     */   
/*     */   public SoundState(SymbolTableData paramSymbolTableData, Controller paramController) {
/*  64 */     super(paramSymbolTableData, paramController);
/*     */     
/*  66 */     if (this.node != null) {
/*  67 */       this.boundingLeaf = paramController.getSymbolTable().addReference(((Sound)this.node).getSchedulingBoundingLeaf());
/*  68 */       this.mediaContainer = paramController.getSymbolTable().addReference(((Sound)this.node).getSoundData());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*  73 */     super.writeObject(paramDataOutput);
/*     */     
/*  75 */     Sound sound = (Sound)this.node;
/*     */     
/*  77 */     paramDataOutput.writeBoolean(sound.getContinuousEnable());
/*  78 */     paramDataOutput.writeBoolean(sound.getEnable());
/*  79 */     paramDataOutput.writeFloat(sound.getInitialGain());
/*  80 */     paramDataOutput.writeInt(sound.getLoop());
/*  81 */     paramDataOutput.writeFloat(sound.getPriority());
/*  82 */     paramDataOutput.writeBoolean(sound.getReleaseEnable());
/*  83 */     paramDataOutput.writeInt(this.boundingLeaf);
/*  84 */     this.control.writeBounds(paramDataOutput, sound.getSchedulingBounds());
/*  85 */     paramDataOutput.writeInt(this.mediaContainer);
/*  86 */     paramDataOutput.writeBoolean(sound.getMute());
/*  87 */     paramDataOutput.writeBoolean(sound.getPause());
/*  88 */     paramDataOutput.writeFloat(sound.getRateScaleFactor());
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  92 */     super.readObject(paramDataInput);
/*     */     
/*  94 */     Sound sound = (Sound)this.node;
/*     */     
/*  96 */     sound.setContinuousEnable(paramDataInput.readBoolean());
/*  97 */     sound.setEnable(paramDataInput.readBoolean());
/*  98 */     sound.setInitialGain(paramDataInput.readFloat());
/*  99 */     sound.setLoop(paramDataInput.readInt());
/* 100 */     sound.setPriority(paramDataInput.readFloat());
/* 101 */     sound.setReleaseEnable(paramDataInput.readBoolean());
/* 102 */     this.boundingLeaf = paramDataInput.readInt();
/* 103 */     sound.setSchedulingBounds(this.control.readBounds(paramDataInput));
/* 104 */     this.mediaContainer = paramDataInput.readInt();
/* 105 */     sound.setMute(paramDataInput.readBoolean());
/* 106 */     sound.setPause(paramDataInput.readBoolean());
/* 107 */     sound.setRateScaleFactor(paramDataInput.readFloat());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public void addSubReference() { this.control.getSymbolTable().incNodeComponentRefCount(this.mediaContainer); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/* 121 */     ((Sound)this.node).setSchedulingBoundingLeaf((BoundingLeaf)this.control.getSymbolTable().getJ3dNode(this.boundingLeaf));
/* 122 */     ((Sound)this.node).setSoundData((MediaContainer)this.control.getSymbolTable().getJ3dNode(this.mediaContainer));
/* 123 */     super.buildGraph();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\SoundState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */