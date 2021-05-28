/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NodeReferenceTable
/*     */ {
/*     */   Hashtable objectHashtable;
/*     */   boolean allowDanglingReferences;
/*     */   
/*     */   public NodeReferenceTable() {}
/*     */   
/*  59 */   NodeReferenceTable(boolean paramBoolean, Hashtable paramHashtable) { set(paramBoolean, paramHashtable); }
/*     */ 
/*     */ 
/*     */   
/*     */   void set(boolean paramBoolean, Hashtable paramHashtable) {
/*  64 */     this.objectHashtable = paramHashtable;
/*  65 */     this.allowDanglingReferences = paramBoolean;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final SceneGraphObject getNewObjectReference(SceneGraphObject paramSceneGraphObject) {
/* 104 */     SceneGraphObject sceneGraphObject = (SceneGraphObject)this.objectHashtable.get(paramSceneGraphObject);
/*     */ 
/*     */     
/* 107 */     if (sceneGraphObject != null)
/*     */     {
/* 109 */       return sceneGraphObject;
/*     */     }
/*     */ 
/*     */     
/* 113 */     if (this.allowDanglingReferences == true) {
/* 114 */       return paramSceneGraphObject;
/*     */     }
/*     */ 
/*     */     
/* 118 */     throw new DanglingReferenceException();
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\NodeReferenceTable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */