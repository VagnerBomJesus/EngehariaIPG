/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import javax.vecmath.Color3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ColorInterpolator
/*     */   extends Interpolator
/*     */ {
/*     */   Material target;
/*  38 */   Color3f startColor = new Color3f();
/*  39 */   Color3f endColor = new Color3f();
/*  40 */   Color3f newColor = new Color3f();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   private float prevAlphaValue = NaNF;
/*  47 */   private int prevColorTarget = -1;
/*  48 */   private WakeupCriterion passiveWakeupCriterion = new WakeupOnElapsedFrames(0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ColorInterpolator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorInterpolator(Alpha paramAlpha, Material paramMaterial) {
/*  65 */     super(paramAlpha);
/*     */     
/*  67 */     this.target = paramMaterial;
/*  68 */     this.startColor.set(0.0F, 0.0F, 0.0F);
/*  69 */     this.endColor.set(1.0F, 1.0F, 1.0F);
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
/*     */   public ColorInterpolator(Alpha paramAlpha, Material paramMaterial, Color3f paramColor3f1, Color3f paramColor3f2) {
/*  86 */     super(paramAlpha);
/*     */     
/*  88 */     this.target = paramMaterial;
/*  89 */     this.startColor.set(paramColor3f1);
/*  90 */     this.endColor.set(paramColor3f2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartColor(Color3f paramColor3f) {
/*  98 */     this.startColor.set(paramColor3f);
/*  99 */     this.prevAlphaValue = NaNF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void getStartColor(Color3f paramColor3f) { paramColor3f.set(this.startColor); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndColor(Color3f paramColor3f) {
/* 115 */     this.endColor.set(paramColor3f);
/* 116 */     this.prevAlphaValue = NaNF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void getEndColor(Color3f paramColor3f) { paramColor3f.set(this.endColor); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarget(Material paramMaterial) {
/* 134 */     this.target = paramMaterial;
/* 135 */     this.prevAlphaValue = NaNF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public Material getTarget() { return this.target; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/* 163 */     WakeupCriterion wakeupCriterion = this.passiveWakeupCriterion;
/*     */     
/* 165 */     if (this.alpha != null) {
/* 166 */       float f = this.alpha.value();
/*     */       
/* 168 */       int i = 2;
/* 169 */       if (this.target.getCapability(0)) {
/* 170 */         i = this.target.getColorTarget();
/*     */       }
/* 172 */       if (f != this.prevAlphaValue || i != this.prevColorTarget) {
/* 173 */         this.newColor.x = (1.0F - f) * this.startColor.x + f * this.endColor.x;
/* 174 */         this.newColor.y = (1.0F - f) * this.startColor.y + f * this.endColor.y;
/* 175 */         this.newColor.z = (1.0F - f) * this.startColor.z + f * this.endColor.z;
/*     */         
/* 177 */         switch (i) {
/*     */           case 0:
/* 179 */             this.target.setAmbientColor(this.newColor);
/*     */             break;
/*     */           case 4:
/* 182 */             this.target.setAmbientColor(this.newColor);
/*     */           
/*     */           case 2:
/* 185 */             this.target.setDiffuseColor(this.newColor);
/*     */             break;
/*     */           case 1:
/* 188 */             this.target.setEmissiveColor(this.newColor);
/*     */             break;
/*     */           case 3:
/* 191 */             this.target.setSpecularColor(this.newColor);
/*     */             break;
/*     */         } 
/*     */         
/* 195 */         this.prevAlphaValue = f;
/* 196 */         this.prevColorTarget = i;
/*     */       } 
/*     */       
/* 199 */       if (!this.alpha.finished() && !this.alpha.isPaused()) {
/* 200 */         wakeupCriterion = this.defaultWakeupCriterion;
/*     */       }
/*     */     } 
/* 203 */     wakeupOn(wakeupCriterion);
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
/*     */   public Node cloneNode(boolean paramBoolean) {
/* 221 */     ColorInterpolator colorInterpolator = new ColorInterpolator();
/* 222 */     colorInterpolator.duplicateNode(this, paramBoolean);
/* 223 */     return colorInterpolator;
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
/* 249 */     super.duplicateAttributes(paramNode, paramBoolean);
/*     */     
/* 251 */     ColorInterpolator colorInterpolator = (ColorInterpolator)paramNode;
/*     */     
/* 253 */     colorInterpolator.getStartColor(this.startColor);
/* 254 */     colorInterpolator.getEndColor(this.endColor);
/*     */ 
/*     */     
/* 257 */     setTarget(colorInterpolator.getTarget());
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
/*     */   public void updateNodeReferences(NodeReferenceTable paramNodeReferenceTable) {
/* 287 */     super.updateNodeReferences(paramNodeReferenceTable);
/*     */ 
/*     */     
/* 290 */     Material material = getTarget();
/*     */     
/* 292 */     if (material != null)
/* 293 */       setTarget((Material)paramNodeReferenceTable.getNewObjectReference(material)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\ColorInterpolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */