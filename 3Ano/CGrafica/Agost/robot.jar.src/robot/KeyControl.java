/*     */ package robot;
/*     */ 
/*     */ import java.awt.AWTEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.util.Enumeration;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.Bounds;
/*     */ import javax.media.j3d.Node;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.media.j3d.WakeupCondition;
/*     */ import javax.media.j3d.WakeupCriterion;
/*     */ import javax.media.j3d.WakeupOnAWTEvent;
/*     */ import javax.media.j3d.WakeupOnCollisionEntry;
/*     */ import javax.media.j3d.WakeupOnCollisionExit;
/*     */ import javax.media.j3d.WakeupOr;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class KeyControl extends Behavior {
/*     */   private Node node;
/*     */   private TransformGroup tg;
/*     */   WakeupCondition KeyCriterion;
/*     */   
/*     */   public KeyControl(TransformGroup tg, Node node) {
/*  25 */     this.tg = null;
/*     */     
/*  27 */     this.trActual = null;
/*  28 */     this.trAnt = null;
/*  29 */     this.collision = false;
/*  30 */     this.lastKey = 0;
/*     */ 
/*     */     
/*  33 */     this.tg = tg;
/*  34 */     this.node = node;
/*     */   }
/*     */   private Transform3D trActual;
/*     */   private Transform3D trAnt;
/*     */   
/*     */   public void initialize() {
/*  40 */     WakeupCriterion[] KeyEvents = new WakeupCriterion[4];
/*  41 */     KeyEvents[0] = new WakeupOnAWTEvent(401);
/*  42 */     KeyEvents[1] = new WakeupOnAWTEvent(402);
/*  43 */     KeyEvents[2] = new WakeupOnCollisionEntry(this.node, 10);
/*  44 */     KeyEvents[3] = new WakeupOnCollisionExit(this.node, 10);
/*     */     
/*  46 */     this.KeyCriterion = new WakeupOr(KeyEvents);
/*  47 */     wakeupOn(this.KeyCriterion);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean collision;
/*     */   
/*     */   private int lastKey;
/*     */   
/*     */   public void processStimulus(Enumeration criteria) {
/*  56 */     while (criteria.hasMoreElements()) {
/*  57 */       WakeupCriterion wakeup = (WakeupCriterion)criteria.nextElement();
/*     */       
/*  59 */       if (wakeup instanceof WakeupOnCollisionEntry) {
/*  60 */         this.collision = true;
/*  61 */         System.out.println("WakeupOnCollisionEntry");
/*  62 */         Bounds b = ((WakeupOnCollisionEntry)wakeup).getTriggeringBounds();
/*  63 */         System.out.println("Collided with " + b.toString()); continue;
/*  64 */       }  if (wakeup instanceof WakeupOnCollisionExit) {
/*  65 */         this.collision = false;
/*  66 */         System.out.println("WakeupOnCollisionExit"); continue;
/*  67 */       }  if (wakeup instanceof WakeupOnAWTEvent) {
/*  68 */         AWTEvent[] events = ((WakeupOnAWTEvent)wakeup).getAWTEvent();
/*  69 */         for (int i = 0; i < events.length; i++) {
/*  70 */           if (events[i].getID() == 401) {
/*  71 */             KeyPressed((KeyEvent)events[i]);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  77 */     wakeupOn(this.KeyCriterion);
/*     */   }
/*     */ 
/*     */   
/*     */   private void KeyPressed(KeyEvent event) {
/*  82 */     int KeyCode = event.getKeyCode();
/*     */     
/*  84 */     switch (KeyCode) {
/*     */       case 37:
/*  86 */         if (!this.collision || (this.collision && this.lastKey != 37)) {
/*  87 */           doRotateY(Math.toRadians(1.0D));
/*     */         }
/*     */         break;
/*     */       
/*     */       case 39:
/*  92 */         if (!this.collision || (this.collision && this.lastKey != 39)) {
/*  93 */           doRotateY(Math.toRadians(-1.0D));
/*     */         }
/*     */         break;
/*     */       
/*     */       case 38:
/*  98 */         if (!this.collision || (this.collision && this.lastKey != 38)) {
/*  99 */           doTranslation(new Vector3f(0.0F, 0.0F, -0.01F));
/*     */         }
/*     */         break;
/*     */       
/*     */       case 40:
/* 104 */         if (!this.collision || (this.collision && this.lastKey != 40)) {
/* 105 */           doTranslation(new Vector3f(0.0F, 0.0F, 0.01F));
/*     */         }
/*     */         break;
/*     */     } 
/*     */     
/* 110 */     this.lastKey = KeyCode;
/*     */   }
/*     */ 
/*     */   
/*     */   private void doRotateY(double d) {
/* 115 */     this.trActual = new Transform3D();
/* 116 */     this.trActual.rotY(d);
/*     */     
/* 118 */     this.trAnt = new Transform3D();
/* 119 */     this.tg.getTransform(this.trAnt);
/*     */     
/* 121 */     this.trAnt.mul(this.trActual);
/* 122 */     this.tg.setTransform(this.trAnt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doTranslation(Vector3f vetor) {
/* 129 */     this.trActual = new Transform3D();
/* 130 */     this.trActual.setTranslation(vetor);
/*     */     
/* 132 */     this.trAnt = new Transform3D();
/* 133 */     this.tg.getTransform(this.trAnt);
/*     */     
/* 135 */     this.trAnt.mul(this.trActual);
/* 136 */     this.tg.setTransform(this.trAnt);
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\robot\KeyControl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */