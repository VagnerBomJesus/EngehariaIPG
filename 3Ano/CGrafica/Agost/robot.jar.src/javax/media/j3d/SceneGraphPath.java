/*     */ package javax.media.j3d;
/*     */ 
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point4d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SceneGraphPath
/*     */ {
/*  58 */   Locale root = null;
/*  59 */   Node[] interior = null;
/*  60 */   Node item = null;
/*  61 */   Transform3D transform = new Transform3D();
/*     */ 
/*     */   
/*  64 */   Point3d intersectPoint = new Point3d();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   double pickDistance;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SceneGraphPath() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SceneGraphPath(Locale paramLocale, Node paramNode) {
/*  89 */     this.item = paramNode;
/*  90 */     this.root = paramLocale;
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
/*     */   public SceneGraphPath(Locale paramLocale, Node[] paramArrayOfNode, Node paramNode) {
/* 102 */     this.item = paramNode;
/* 103 */     this.root = paramLocale;
/* 104 */     this.interior = new Node[paramArrayOfNode.length];
/* 105 */     for (byte b = 0; b < paramArrayOfNode.length; b++) {
/* 106 */       this.interior[b] = paramArrayOfNode[b];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   SceneGraphPath(SceneGraphPath paramSceneGraphPath) { set(paramSceneGraphPath); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void set(SceneGraphPath paramSceneGraphPath) {
/* 123 */     this.root = paramSceneGraphPath.root;
/* 124 */     this.item = paramSceneGraphPath.item;
/* 125 */     this.transform.set(paramSceneGraphPath.transform);
/* 126 */     if (paramSceneGraphPath.interior != null && paramSceneGraphPath.interior.length > 0) {
/* 127 */       this.interior = new Node[paramSceneGraphPath.interior.length];
/* 128 */       for (byte b = 0; b < this.interior.length; b++) {
/* 129 */         this.interior[b] = paramSceneGraphPath.interior[b];
/*     */       }
/*     */     } else {
/* 132 */       this.interior = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public final void setLocale(Locale paramLocale) { this.root = paramLocale; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public final void setObject(Node paramNode) { this.item = paramNode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setNodes(Node[] paramArrayOfNode) {
/* 158 */     if (paramArrayOfNode != null && paramArrayOfNode.length > 0) {
/* 159 */       this.interior = new Node[paramArrayOfNode.length];
/* 160 */       for (byte b = 0; b < paramArrayOfNode.length; b++) {
/* 161 */         this.interior[b] = paramArrayOfNode[b];
/*     */       }
/*     */     } else {
/* 164 */       this.interior = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setNode(int paramInt, Node paramNode) {
/* 175 */     if (this.interior == null) {
/* 176 */       throw new NullPointerException(J3dI18N.getString("SceneGraphPath0"));
/*     */     }
/* 178 */     this.interior[paramInt] = paramNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public final void setTransform(Transform3D paramTransform3D) { this.transform.set(paramTransform3D); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   public final Transform3D getTransform() { return new Transform3D(this.transform); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   public final Locale getLocale() { return this.root; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   public final Node getObject() { return this.item; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int nodeCount() {
/* 226 */     if (this.interior == null)
/* 227 */       return 0; 
/* 228 */     return this.interior.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final Node getNode(int paramInt) {
/* 237 */     if (this.interior == null) {
/* 238 */       throw new ArrayIndexOutOfBoundsException(J3dI18N.getString("SceneGraphPath1"));
/*     */     }
/* 240 */     return this.interior[paramInt];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(SceneGraphPath paramSceneGraphPath) {
/* 251 */     boolean bool = true;
/*     */     
/*     */     try {
/* 254 */       if (paramSceneGraphPath == null || this.root != paramSceneGraphPath.root || this.item != paramSceneGraphPath.item) {
/* 255 */         return false;
/*     */       }
/* 257 */       bool = this.transform.equals(paramSceneGraphPath.transform);
/*     */       
/* 259 */       if (!bool) {
/* 260 */         return false;
/*     */       }
/* 262 */       if (this.interior == null || paramSceneGraphPath.interior == null) {
/* 263 */         if (this.interior != paramSceneGraphPath.interior) {
/* 264 */           return false;
/*     */         }
/* 266 */         bool = (this.root == paramSceneGraphPath.root && this.item == paramSceneGraphPath.item);
/*     */       
/*     */       }
/* 269 */       else if (this.interior.length == paramSceneGraphPath.interior.length) {
/* 270 */         for (byte b = 0; b < this.interior.length; b++) {
/* 271 */           if (this.interior[b] != paramSceneGraphPath.interior[b]) {
/* 272 */             return false;
/*     */           }
/*     */         } 
/*     */       } else {
/* 276 */         return false;
/*     */       } 
/*     */     } catch (NullPointerException nullPointerException) {
/*     */       
/* 280 */       return false;
/*     */     } 
/* 282 */     return bool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 293 */     boolean bool = true;
/*     */ 
/*     */     
/* 296 */     try { SceneGraphPath sceneGraphPath = (SceneGraphPath)paramObject;
/* 297 */       if (sceneGraphPath == null || this.root != sceneGraphPath.root || this.item != sceneGraphPath.item) {
/* 298 */         return false;
/*     */       }
/* 300 */       bool = this.transform.equals(sceneGraphPath.transform);
/*     */       
/* 302 */       if (!bool) {
/* 303 */         return false;
/*     */       }
/* 305 */       if (this.interior == null || sceneGraphPath.interior == null) {
/* 306 */         if (this.interior != sceneGraphPath.interior) {
/* 307 */           return false;
/*     */         }
/* 309 */         bool = (this.root == sceneGraphPath.root && this.item == sceneGraphPath.item);
/*     */       
/*     */       }
/* 312 */       else if (this.interior.length == sceneGraphPath.interior.length) {
/* 313 */         for (byte b = 0; b < this.interior.length; b++) {
/* 314 */           if (this.interior[b] != sceneGraphPath.interior[b]) {
/* 315 */             return false;
/*     */           }
/*     */         } 
/*     */       } else {
/* 319 */         return false;
/*     */       } 
/*     */       
/* 322 */       return bool; }
/*     */     catch (NullPointerException nullPointerException)
/* 324 */     { return false; }
/* 325 */     catch (ClassCastException classCastException) { return false; }
/*     */   
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
/*     */   public int hashCode() {
/* 338 */     HashKey hashKey = new HashKey(250);
/*     */ 
/*     */     
/* 341 */     if (this.interior != null && this.item != null) {
/* 342 */       for (byte b = 0; b < this.interior.length; b++) {
/* 343 */         hashKey.append(LinkRetained.plus).append(this.item.toString());
/*     */       }
/*     */     }
/* 346 */     return hashKey.hashCode() + this.transform.hashCode();
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
/*     */   public final boolean isSamePath(SceneGraphPath paramSceneGraphPath) {
/* 359 */     byte b = 0;
/*     */     
/* 361 */     if (paramSceneGraphPath == null || paramSceneGraphPath.item != this.item || this.root != paramSceneGraphPath.root) {
/* 362 */       return false;
/*     */     }
/* 364 */     if (this.interior != null && paramSceneGraphPath.interior != null) {
/* 365 */       for (byte b1 = 0; b1 < this.interior.length; b1++) {
/* 366 */         if (this.interior[b1] instanceof Link)
/*     */         {
/* 368 */           while (b < paramSceneGraphPath.interior.length) {
/* 369 */             if (paramSceneGraphPath.interior[b] instanceof Link) {
/* 370 */               if (paramSceneGraphPath.interior[b] != this.interior[b1]) {
/* 371 */                 return false;
/*     */               }
/* 373 */               b++;
/*     */               break;
/*     */             } 
/* 376 */             b++;
/*     */             
/* 378 */             if (b == paramSceneGraphPath.interior.length) {
/* 379 */               return false;
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/* 384 */       while (b < paramSceneGraphPath.interior.length) {
/* 385 */         if (paramSceneGraphPath.interior[b] instanceof Link)
/* 386 */           return false; 
/* 387 */         b++;
/*     */       } 
/* 389 */     } else if (this.interior != paramSceneGraphPath.interior) {
/* 390 */       return false;
/*     */     } 
/* 392 */     return true;
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
/*     */   public String toString() {
/* 405 */     StringBuffer stringBuffer = new StringBuffer();
/*     */ 
/*     */     
/* 408 */     if (this.root == null && this.interior == null && this.item == null) {
/* 409 */       return super.toString();
/*     */     }
/* 411 */     if (this.root != null) {
/* 412 */       stringBuffer.append(this.root + " : ");
/*     */     }
/* 414 */     if (this.interior != null) {
/* 415 */       for (byte b = 0; b < this.interior.length; b++) {
/*     */         
/* 417 */         stringBuffer.append(this.interior[b].getClass().getName());
/* 418 */         Object object = this.interior[b].getUserData();
/* 419 */         if (object == null) {
/* 420 */           stringBuffer.append(" : ");
/*     */         } else {
/* 422 */           stringBuffer.append(", " + object + " : ");
/*     */         } 
/*     */       } 
/*     */     }
/* 426 */     if (this.item != null) {
/*     */       
/* 428 */       stringBuffer.append(this.item.getClass().getName());
/* 429 */       Object object = this.item.getUserData();
/* 430 */       if (object != null) {
/* 431 */         stringBuffer.append(", " + object);
/*     */       }
/*     */       try {
/* 434 */         if (this.item.getClass().getName().equals("javax.media.j3d.Shape3D")) {
/* 435 */           stringBuffer.append(((Shape3D)this.item).getGeometry());
/*     */         }
/* 437 */       } catch (CapabilityNotSetException capabilityNotSetException) {}
/*     */     } 
/*     */     
/* 440 */     stringBuffer.append("\nLocalToVworld Transform:\n" + this.transform);
/*     */     
/* 442 */     return new String(stringBuffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean validate() {
/* 453 */     NodeRetained nodeRetained = (NodeRetained)this.item.retained;
/*     */     
/* 455 */     Locale locale = nodeRetained.locale;
/*     */     
/* 457 */     if (this.root != null) {
/* 458 */       if (this.item.isLive() && 
/* 459 */         locale != this.root) {
/* 460 */         return false;
/*     */       }
/*     */     } else {
/*     */       
/* 464 */       this.root = locale;
/*     */     } 
/*     */     
/* 467 */     byte b = (this.interior == null) ? 0 : this.interior.length;
/*     */     
/*     */     do {
/* 470 */       if (nodeRetained instanceof SharedGroupRetained) {
/* 471 */         if (this.interior == null)
/* 472 */           return false;  do {  }
/* 473 */         while (--b > 0 && 
/* 474 */           !((SharedGroupRetained)nodeRetained).parents.contains((this.interior[b]).retained));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 479 */         if (b < 0) {
/* 480 */           return false;
/*     */         }
/* 482 */         nodeRetained = (NodeRetained)(this.interior[b]).retained;
/*     */       } else {
/* 484 */         nodeRetained = nodeRetained.parent;
/*     */       } 
/* 486 */     } while (nodeRetained != null);
/*     */     
/* 488 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void getHashKey(HashKey paramHashKey) {
/* 494 */     if (this.interior != null) {
/* 495 */       paramHashKey.reset();
/* 496 */       paramHashKey.append(this.root.nodeId);
/* 497 */       for (byte b = 0; b < this.interior.length; b++) {
/* 498 */         Node node = this.interior[b];
/*     */         
/* 500 */         if (!node.isLive()) {
/* 501 */           throw new RuntimeException(J3dI18N.getString("SceneGraphPath3"));
/*     */         }
/*     */         
/* 504 */         NodeRetained nodeRetained = (NodeRetained)node.retained;
/* 505 */         if (nodeRetained.nodeType == 9) {
/* 506 */           paramHashKey.append("+").append(nodeRetained.nodeId);
/*     */         }
/*     */       } 
/*     */     } 
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
/*     */   
/*     */   boolean validate(HashKey paramHashKey) {
/* 523 */     if (this.root == null) {
/* 524 */       throw new IllegalArgumentException(J3dI18N.getString("SceneGraphPath2"));
/*     */     }
/* 526 */     if (this.item == null) {
/* 527 */       throw new IllegalArgumentException(J3dI18N.getString("SceneGraphPath10"));
/*     */     }
/*     */     
/* 530 */     if (!this.item.isLive()) {
/* 531 */       throw new IllegalArgumentException(J3dI18N.getString("SceneGraphPath3"));
/*     */     }
/*     */     try {
/* 534 */       getHashKey(paramHashKey);
/* 535 */     } catch (RuntimeException runtimeException) {
/* 536 */       throw new IllegalArgumentException(runtimeException.getMessage());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 543 */     NodeRetained nodeRetained3 = null;
/*     */     
/* 545 */     boolean bool = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 560 */     NodeRetained nodeRetained1 = (NodeRetained)this.item.retained;
/*     */     
/* 562 */     if (this.interior != null) {
/* 563 */       for (int i = this.interior.length - 1; i >= 0; i--) {
/* 564 */         nodeRetained3 = (NodeRetained)(this.interior[i]).retained;
/* 565 */         NodeRetained nodeRetained = nodeRetained1.parent;
/* 566 */         if (nodeRetained == null && nodeRetained1 instanceof SharedGroupRetained) {
/* 567 */           if (((SharedGroupRetained)nodeRetained1).parents.contains(nodeRetained3)) {
/* 568 */             nodeRetained = nodeRetained3;
/*     */           } else {
/* 570 */             throw new IllegalArgumentException(J3dI18N.getString("SceneGraphPath5"));
/*     */           } 
/*     */         }
/*     */         
/* 574 */         while (nodeRetained != nodeRetained3) {
/* 575 */           if (nodeRetained == null) {
/* 576 */             throw new IllegalArgumentException(J3dI18N.getString("SceneGraphPath11"));
/*     */           }
/*     */           
/* 579 */           if (nodeRetained instanceof SharedGroupRetained) {
/* 580 */             if (((SharedGroupRetained)nodeRetained).parents.contains(nodeRetained3)) {
/*     */               
/* 582 */               nodeRetained = nodeRetained3; continue;
/*     */             } 
/* 584 */             throw new IllegalArgumentException(J3dI18N.getString("SceneGraphPath5"));
/*     */           } 
/*     */           
/* 587 */           nodeRetained = nodeRetained.parent;
/*     */         } 
/*     */         
/* 590 */         nodeRetained1 = nodeRetained;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 595 */     NodeRetained nodeRetained2 = nodeRetained1.parent;
/* 596 */     if (nodeRetained2 == null && nodeRetained1 instanceof SharedGroupRetained) {
/* 597 */       throw new IllegalArgumentException(J3dI18N.getString("SceneGraphPath5"));
/*     */     }
/*     */     
/* 600 */     while (nodeRetained2 != null) {
/* 601 */       if (nodeRetained2 instanceof LinkRetained) {
/* 602 */         throw new IllegalArgumentException(J3dI18N.getString("SceneGraphPath5"));
/*     */       }
/*     */       
/* 605 */       nodeRetained1 = nodeRetained2;
/* 606 */       nodeRetained2 = nodeRetained2.parent;
/* 607 */       if (nodeRetained2 == null && nodeRetained1 instanceof SharedGroupRetained) {
/* 608 */         throw new IllegalArgumentException(J3dI18N.getString("SceneGraphPath5"));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 613 */     Node node = (Node)nodeRetained1.source;
/*     */     
/* 615 */     if (!this.root.branchGroups.contains(node)) {
/* 616 */       throw new IllegalArgumentException(J3dI18N.getString("SceneGraphPath9"));
/*     */     }
/*     */     
/* 619 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 627 */   double getDistanceFrom(Point3d paramPoint3d) { return this.intersectPoint.distance(paramPoint3d); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 634 */   double getDistance() { return this.pickDistance; }
/*     */ 
/*     */ 
/*     */   
/* 638 */   final void setIntersectPoint(Point3d paramPoint3d) { this.intersectPoint.set(paramPoint3d); }
/*     */ 
/*     */ 
/*     */   
/*     */   final void setIntersectPointDis(Point4d paramPoint4d) {
/* 643 */     this.intersectPoint.x = paramPoint4d.x;
/* 644 */     this.intersectPoint.y = paramPoint4d.y;
/* 645 */     this.intersectPoint.z = paramPoint4d.z;
/* 646 */     this.pickDistance = paramPoint4d.w;
/*     */   }
/*     */ 
/*     */   
/* 650 */   final Point3d getIntersectPoint() { return this.intersectPoint; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\SceneGraphPath.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */