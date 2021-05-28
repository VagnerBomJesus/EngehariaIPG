/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class RenderingAttributesStructure
/*     */   extends J3dStructure
/*     */   implements ObjectUpdate
/*     */ {
/*  25 */   ArrayList objList = new ArrayList();
/*     */ 
/*     */   
/*  28 */   RenderingAttributesStructure() { super(null, 1024); }
/*     */ 
/*     */ 
/*     */   
/*     */   void processMessages(long paramLong) {
/*  33 */     boolean bool = false;
/*     */     
/*  35 */     J3dMessage[] arrayOfJ3dMessage = getMessages(paramLong);
/*  36 */     int i = getNumMessage();
/*     */     
/*  38 */     if (i <= 0) {
/*     */       return;
/*     */     }
/*     */     
/*  42 */     for (byte b = 0; b < i; b++) {
/*  43 */       int k; NodeComponentRetained nodeComponentRetained2, nodeComponentRetained1; int j; J3dMessage j3dMessage = arrayOfJ3dMessage[b];
/*  44 */       switch (j3dMessage.type) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 18:
/*     */         case 47:
/*     */         case 65:
/*  52 */           j = ((Integer)j3dMessage.args[1]).intValue();
/*  53 */           nodeComponentRetained2 = (NodeComponentRetained)j3dMessage.args[0];
/*  54 */           nodeComponentRetained2.mirror.changedFrequent = ((Integer)j3dMessage.args[3]).intValue();
/*  55 */           updateNodeComponent((Object[])j3dMessage.args);
/*  56 */           if (nodeComponentRetained2.mirror.changedFrequent != 0) {
/*  57 */             this.objList.add(j3dMessage);
/*  58 */             bool = true;
/*  59 */             nodeComponentRetained2.mirror.compChanged |= j;
/*     */             break;
/*     */           } 
/*  62 */           j3dMessage.decRefcount();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 6:
/*     */         case 7:
/*     */         case 8:
/*     */         case 9:
/*     */         case 10:
/*     */         case 12:
/*     */         case 13:
/*     */         case 14:
/*     */         case 63:
/*     */         case 64:
/*  79 */           nodeComponentRetained1 = (NodeComponentRetained)j3dMessage.args[0];
/*  80 */           nodeComponentRetained1.mirror.changedFrequent = ((Integer)j3dMessage.args[3]).intValue();
/*  81 */           if (nodeComponentRetained1.mirror.changedFrequent != 0) {
/*  82 */             this.objList.add(j3dMessage);
/*  83 */             bool = true;
/*  84 */             nodeComponentRetained1.mirror.compChanged = 1;
/*     */             break;
/*     */           } 
/*  87 */           updateNodeComponent((Object[])j3dMessage.args);
/*  88 */           j3dMessage.decRefcount();
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 54:
/*  94 */           nodeComponentRetained1 = (NodeComponentRetained)j3dMessage.args[0];
/*  95 */           k = ((Integer)j3dMessage.args[3]).intValue();
/*  96 */           if (nodeComponentRetained1.mirror != null)
/*  97 */             nodeComponentRetained1.mirror.changedFrequent = k; 
/*  98 */           if (k != 0) {
/*  99 */             this.objList.add(j3dMessage);
/* 100 */             bool = true;
/*     */             break;
/*     */           } 
/* 103 */           updateNodeComponent((Object[])j3dMessage.args);
/* 104 */           j3dMessage.decRefcount();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 11:
/* 111 */           nodeComponentRetained1 = (NodeComponentRetained)j3dMessage.args[0];
/* 112 */           nodeComponentRetained1.mirror.changedFrequent = ((Integer)j3dMessage.args[4]).intValue();
/*     */           
/* 114 */           if (nodeComponentRetained1.mirror.changedFrequent != 0) {
/* 115 */             this.objList.add(j3dMessage);
/* 116 */             bool = true;
/* 117 */             nodeComponentRetained1.mirror.compChanged = 1;
/*     */             break;
/*     */           } 
/* 120 */           updateTextureAttributes((Object[])j3dMessage.args);
/* 121 */           j3dMessage.decRefcount();
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 15:
/* 127 */           nodeComponentRetained1 = (NodeComponentRetained)j3dMessage.args[0];
/* 128 */           nodeComponentRetained1.mirror.changedFrequent = ((Integer)j3dMessage.args[3]).intValue();
/*     */           
/* 130 */           this.objList.add(j3dMessage);
/* 131 */           nodeComponentRetained1.mirror.compChanged = 1;
/* 132 */           bool = true;
/*     */           break;
/*     */         
/*     */         case 17:
/* 136 */           nodeComponentRetained1 = (GeometryRetained)j3dMessage.args[1];
/*     */           
/* 138 */           if (nodeComponentRetained1 instanceof IndexedGeometryArrayRetained) {
/* 139 */             this.objList.add(j3dMessage);
/* 140 */             bool = true;
/* 141 */             if (j3dMessage.args[2] == null) {
/* 142 */               k = ((Integer)j3dMessage.args[3]).intValue();
/* 143 */               nodeComponentRetained1.cachedChangedFrequent = k;
/*     */             } 
/*     */             break;
/*     */           } 
/* 147 */           k = ((Integer)j3dMessage.args[3]).intValue();
/* 148 */           nodeComponentRetained1.cachedChangedFrequent = k;
/* 149 */           j3dMessage.decRefcount();
/*     */           break;
/*     */         
/*     */         case 16:
/* 153 */           this.objList.add(j3dMessage);
/* 154 */           bool = true;
/*     */           break;
/*     */         default:
/* 157 */           j3dMessage.decRefcount(); break;
/*     */       } 
/*     */     } 
/* 160 */     if (bool) {
/* 161 */       VirtualUniverse.mc.addMirrorObject(this);
/*     */     }
/*     */ 
/*     */     
/* 165 */     Arrays.fill(arrayOfJ3dMessage, 0, i, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateObject() {
/* 170 */     int i = this.objList.size();
/* 171 */     for (byte b = 0; b < i; b++) {
/* 172 */       J3dMessage j3dMessage = (J3dMessage)this.objList.get(b);
/*     */ 
/*     */       
/* 175 */       if (j3dMessage.type == 17) {
/* 176 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)j3dMessage.args[1];
/* 177 */         if (j3dMessage.args[2] == null) {
/* 178 */           geometryArrayRetained.updateMirrorGeometry();
/*     */         } else {
/*     */           
/* 181 */           geometryArrayRetained.initMirrorGeometry();
/*     */         }
/*     */       
/* 184 */       } else if (j3dMessage.type == 16) {
/* 185 */         MorphRetained morphRetained = (MorphRetained)j3dMessage.args[0];
/* 186 */         GeometryArrayRetained geometryArrayRetained = (GeometryArrayRetained)morphRetained.morphedGeometryArray.retained;
/* 187 */         geometryArrayRetained.updateMirrorGeometry();
/*     */       }
/* 189 */       else if (j3dMessage.type == 11) {
/* 190 */         NodeComponentRetained nodeComponentRetained = (NodeComponentRetained)j3dMessage.args[0];
/* 191 */         nodeComponentRetained.mirror.compChanged = 0;
/* 192 */         updateTextureAttributes((Object[])j3dMessage.args);
/*     */       }
/* 194 */       else if (j3dMessage.type == 18 || j3dMessage.type == 65 || j3dMessage.type == 47) {
/*     */ 
/*     */         
/* 197 */         NodeComponentRetained nodeComponentRetained = (NodeComponentRetained)j3dMessage.args[0];
/* 198 */         nodeComponentRetained.mirror.compChanged = 0;
/*     */       } else {
/*     */         
/* 201 */         NodeComponentRetained nodeComponentRetained = (NodeComponentRetained)j3dMessage.args[0];
/* 202 */         if (nodeComponentRetained.mirror != null)
/* 203 */           nodeComponentRetained.mirror.compChanged = 0; 
/* 204 */         updateNodeComponent(j3dMessage.args);
/*     */       } 
/* 206 */       j3dMessage.decRefcount();
/*     */     } 
/* 208 */     this.objList.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateNodeComponent(Object[] paramArrayOfObject) {
/* 214 */     NodeComponentRetained nodeComponentRetained = (NodeComponentRetained)paramArrayOfObject[0];
/* 215 */     nodeComponentRetained.updateMirrorObject(((Integer)paramArrayOfObject[1]).intValue(), paramArrayOfObject[2]);
/*     */   }
/*     */   
/*     */   private void updateTextureAttributes(Object[] paramArrayOfObject) {
/* 219 */     TextureAttributesRetained textureAttributesRetained = (TextureAttributesRetained)paramArrayOfObject[0];
/* 220 */     textureAttributesRetained.updateMirrorObject(((Integer)paramArrayOfObject[1]).intValue(), paramArrayOfObject[2], paramArrayOfObject[3]);
/*     */   }
/*     */   
/*     */   void removeNodes(J3dMessage paramJ3dMessage) {}
/*     */   
/*     */   void cleanup() {}
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\RenderingAttributesStructure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */