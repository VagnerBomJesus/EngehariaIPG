/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.BitSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Switch
/*     */   extends Group
/*     */ {
/*     */   public static final int ALLOW_SWITCH_READ = 17;
/*     */   public static final int ALLOW_SWITCH_WRITE = 18;
/*     */   public static final int CHILD_NONE = -1;
/*     */   public static final int CHILD_ALL = -2;
/*     */   public static final int CHILD_MASK = -3;
/*  68 */   private static final int[] readCapabilities = { 17 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public Switch() { setDefaultReadCapabilities(readCapabilities); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Switch(int paramInt) {
/*  92 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/*  94 */     ((SwitchRetained)this.retained).setWhichChild(paramInt, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Switch(int paramInt, BitSet paramBitSet) {
/* 105 */     setDefaultReadCapabilities(readCapabilities);
/*     */     
/* 107 */     ((SwitchRetained)this.retained).setWhichChild(paramInt, true);
/* 108 */     ((SwitchRetained)this.retained).setChildMask(paramBitSet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void createRetained() {
/* 116 */     this.retained = new SwitchRetained();
/* 117 */     this.retained.setSource(this);
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
/*     */   public void setWhichChild(int paramInt) {
/* 134 */     if (isLiveOrCompiled() && 
/* 135 */       !getCapability(18)) {
/* 136 */       throw new CapabilityNotSetException(J3dI18N.getString("Switch0"));
/*     */     }
/* 138 */     ((SwitchRetained)this.retained).setWhichChild(paramInt, false);
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
/*     */   public int getWhichChild() {
/* 155 */     if (isLiveOrCompiled() && 
/* 156 */       !getCapability(17)) {
/* 157 */       throw new CapabilityNotSetException(J3dI18N.getString("Switch1"));
/*     */     }
/* 159 */     return ((SwitchRetained)this.retained).getWhichChild();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChildMask(BitSet paramBitSet) {
/* 170 */     if (isLiveOrCompiled() && 
/* 171 */       !getCapability(18)) {
/* 172 */       throw new CapabilityNotSetException(J3dI18N.getString("Switch2"));
/*     */     }
/* 174 */     ((SwitchRetained)this.retained).setChildMask(paramBitSet);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BitSet getChildMask() {
/* 185 */     if (isLiveOrCompiled() && 
/* 186 */       !getCapability(17)) {
/* 187 */       throw new CapabilityNotSetException(J3dI18N.getString("Switch3"));
/*     */     }
/* 189 */     return ((SwitchRetained)this.retained).getChildMask();
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
/*     */   public Node currentChild() {
/* 201 */     if (isLiveOrCompiled() && 
/* 202 */       !getCapability(12)) {
/* 203 */       throw new CapabilityNotSetException(J3dI18N.getString("Switch4"));
/*     */     }
/* 205 */     return ((SwitchRetained)this.retained).currentChild();
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 224 */     Switch switch = new Switch();
/* 225 */     switch.duplicateNode(this, paramBoolean);
/* 226 */     return switch;
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
/* 252 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 254 */     SwitchRetained switchRetained1 = (SwitchRetained)paramNode.retained;
/* 255 */     SwitchRetained switchRetained2 = (SwitchRetained)this.retained;
/*     */     
/* 257 */     switchRetained2.setChildMask(switchRetained1.getChildMask());
/* 258 */     switchRetained2.setWhichChild(switchRetained1.getWhichChild(), true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\Switch.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */