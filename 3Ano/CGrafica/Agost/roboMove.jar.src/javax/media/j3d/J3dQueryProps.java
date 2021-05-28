/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class J3dQueryProps
/*     */   extends AbstractMap
/*     */ {
/*  24 */   private Set entrySet = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   private Hashtable table = new Hashtable(); J3dQueryProps(String[] paramArrayOfString, Object[] paramArrayOfObject) {
/*  34 */     for (byte b = 0; b < paramArrayOfString.length; b++) {
/*  35 */       this.table.put(paramArrayOfString[b], paramArrayOfObject[b]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public Object get(Object paramObject) { return this.table.get(paramObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public boolean containsKey(Object paramObject) { return this.table.containsKey(paramObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public boolean containsValue(Object paramObject) { return this.table.containsValue(paramObject); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set entrySet() {
/*  64 */     if (this.entrySet == null) {
/*  65 */       this.entrySet = new EntrySet(null);
/*     */     }
/*  67 */     return this.entrySet;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class EntrySet
/*     */     extends AbstractSet
/*     */   {
/*     */     private EntrySet() {}
/*     */ 
/*     */ 
/*     */     
/*  79 */     public int size() { return J3dQueryProps.this.table.size(); }
/*     */ 
/*     */ 
/*     */     
/*  83 */     public Iterator iterator() { return new J3dQueryProps.MapIterator(J3dQueryProps.this, null); }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class MapIterator
/*     */     implements Iterator
/*     */   {
/*     */     private Iterator i;
/*     */ 
/*     */ 
/*     */     
/*  95 */     private MapIterator() { this.i = this$0.table.entrySet().iterator(); }
/*     */ 
/*     */ 
/*     */     
/*  99 */     public boolean hasNext() { return this.i.hasNext(); }
/*     */ 
/*     */ 
/*     */     
/* 103 */     public Object next() { return this.i.next(); }
/*     */ 
/*     */ 
/*     */     
/* 107 */     public void remove() { throw new UnsupportedOperationException(); }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\J3dQueryProps.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */