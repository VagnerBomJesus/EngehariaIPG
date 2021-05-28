/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.j3d.utils.scenegraph.io.SceneGraphIO;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import javax.media.j3d.Group;
/*     */ import javax.media.j3d.Node;
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
/*     */ public class GroupState
/*     */   extends NodeState
/*     */ {
/*     */   protected SceneGraphObjectState[] groupChildren;
/*     */   
/*  60 */   public GroupState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */   
/*     */   public void writeObject(DataOutput paramDataOutput) throws IOException {
/*     */     byte b1;
/*  64 */     super.writeObject(paramDataOutput);
/*     */     
/*  66 */     this.control.writeBounds(paramDataOutput, ((Group)this.node).getCollisionBounds());
/*     */ 
/*     */     
/*  69 */     if (checkProcessChildren()) {
/*  70 */       b1 = ((Group)this.node).numChildren();
/*     */     } else {
/*  72 */       b1 = 0;
/*     */     } 
/*  74 */     paramDataOutput.writeInt(b1);
/*     */     
/*  76 */     for (byte b2 = 0; b2 < b1; b2++) {
/*  77 */       this.control.writeObject(paramDataOutput, this.control.createState(((Group)this.node).getChild(b2)));
/*     */     }
/*     */     
/*  80 */     paramDataOutput.writeBoolean(((Group)this.node).getAlternateCollisionTarget());
/*     */   }
/*     */   
/*     */   public void readObject(DataInput paramDataInput) throws IOException {
/*  84 */     super.readObject(paramDataInput);
/*     */     
/*  86 */     ((Group)this.node).setCollisionBounds(this.control.readBounds(paramDataInput));
/*     */     
/*  88 */     int i = paramDataInput.readInt();
/*  89 */     this.groupChildren = new SceneGraphObjectState[i];
/*  90 */     for (byte b = 0; b < i; b++) {
/*  91 */       this.groupChildren[b] = this.control.readObject(paramDataInput);
/*  92 */       ((Group)this.node).addChild((Node)this.groupChildren[b].getNode());
/*     */     } 
/*     */     
/*  95 */     ((Group)this.node).setAlternateCollisionTarget(paramDataInput.readBoolean());
/*     */   }
/*     */   
/*     */   private boolean checkProcessChildren() {
/*  99 */     if (this.node instanceof SceneGraphIO) {
/* 100 */       return ((SceneGraphIO)this.node).saveChildren();
/*     */     }
/* 102 */     return processChildren();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   protected boolean processChildren() { return true; }
/*     */ 
/*     */   
/*     */   public void buildGraph() {
/* 118 */     for (byte b = 0; b < this.groupChildren.length; b++) {
/* 119 */       if (!(this.groupChildren[b].getSymbol()).graphBuilt) {
/* 120 */         (this.groupChildren[b].getSymbol()).graphBuilt = true;
/* 121 */         this.groupChildren[b].buildGraph();
/*     */       } 
/*     */     } 
/* 124 */     super.buildGraph();
/*     */   }
/*     */   
/*     */   public void cleanup() {
/* 128 */     for (byte b = 0; b < this.groupChildren.length; b++) {
/* 129 */       this.groupChildren[b].cleanup();
/* 130 */       this.groupChildren[b] = null;
/*     */     } 
/*     */     
/* 133 */     this.groupChildren = null;
/* 134 */     super.cleanup();
/*     */   }
/*     */ 
/*     */   
/* 138 */   protected SceneGraphObject createNode() { return new Group(); }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\GroupState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */