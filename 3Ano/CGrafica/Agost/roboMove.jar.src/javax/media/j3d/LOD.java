/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class LOD
/*     */   extends Behavior
/*     */ {
/*  30 */   WakeupOnElapsedFrames wakeupFrame = new WakeupOnElapsedFrames(0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   Vector switches = new Vector(5);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public void addSwitch(Switch paramSwitch) { this.switches.addElement(paramSwitch); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSwitch(Switch paramSwitch, int paramInt) {
/*  58 */     Switch switch = getSwitch(paramInt);
/*  59 */     this.switches.setElementAt(paramSwitch, paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public void insertSwitch(Switch paramSwitch, int paramInt) { this.switches.insertElementAt(paramSwitch, paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSwitch(int paramInt) {
/*  76 */     Switch switch = getSwitch(paramInt);
/*  77 */     this.switches.removeElementAt(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public Switch getSwitch(int paramInt) { return (Switch)this.switches.elementAt(paramInt); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public Enumeration getAllSwitches() { return this.switches.elements(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public int numSwitches() { return this.switches.size(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public int indexOfSwitch(Switch paramSwitch) { return this.switches.indexOf(paramSwitch); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSwitch(Switch paramSwitch) {
/* 131 */     int i = this.switches.indexOf(paramSwitch);
/* 132 */     if (i >= 0) {
/* 133 */       removeSwitch(i);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAllSwitches() {
/* 143 */     int i = this.switches.size();
/*     */ 
/*     */     
/* 146 */     for (int j = i - 1; j >= 0; j--) {
/* 147 */       removeSwitch(j);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void duplicateAttributes(Node paramNode, boolean paramBoolean) {
/* 174 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 176 */     LOD lOD = (LOD)paramNode;
/*     */     
/* 178 */     int i = lOD.numSwitches();
/* 179 */     for (byte b = 0; b < i; b++) {
/* 180 */       addSwitch(lOD.getSwitch(b));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateNodeReferences(NodeReferenceTable paramNodeReferenceTable) {
/* 210 */     int i = numSwitches();
/*     */     
/* 212 */     for (byte b = 0; b < i; b++) {
/* 213 */       Switch switch = getSwitch(b);
/* 214 */       if (switch != null)
/* 215 */         setSwitch((Switch)paramNodeReferenceTable.getNewObjectReference(switch), b); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\LOD.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */