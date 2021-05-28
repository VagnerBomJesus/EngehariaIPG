/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.AWTEvent;
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
/*     */ public final class WakeupOnAWTEvent
/*     */   extends WakeupCriterion
/*     */ {
/*     */   static final int COND_IN_BS_LIST = 0;
/*     */   static final int TOTAL_INDEXED_UNORDER_SET_TYPES = 1;
/*     */   int AwtId;
/*     */   long EventMask;
/*     */   long enableAWTEventTS;
/*     */   Vector events;
/*     */   
/*     */   public WakeupOnAWTEvent(int paramInt) {
/*  33 */     this.enableAWTEventTS = 0L;
/*  34 */     this.events = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  43 */     this.AwtId = paramInt;
/*  44 */     this.EventMask = 0L;
/*  45 */     WakeupIndexedList.init(this, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WakeupOnAWTEvent(long paramLong) {
/*     */     this.enableAWTEventTS = 0L;
/*     */     this.events = new Vector();
/*  53 */     this.EventMask = paramLong;
/*  54 */     this.AwtId = 0;
/*  55 */     WakeupIndexedList.init(this, 1);
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
/*     */   public AWTEvent[] getAWTEvent() {
/*     */     AWTEvent[] arrayOfAWTEvent;
/*  68 */     synchronized (this.events) {
/*  69 */       arrayOfAWTEvent = new AWTEvent[this.events.size()];
/*  70 */       this.events.copyInto(arrayOfAWTEvent);
/*  71 */       this.events.removeAllElements();
/*     */     } 
/*     */     
/*  74 */     return arrayOfAWTEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addAWTEvent(AWTEvent paramAWTEvent) {
/*  83 */     this.events.addElement(paramAWTEvent);
/*  84 */     setTriggered();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/*  93 */     resetBehaviorCondition(paramBehaviorStructure);
/*  94 */     paramBehaviorStructure.wakeupOnAWTEvent.add(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   void removeBehaviorCondition(BehaviorStructure paramBehaviorStructure) { paramBehaviorStructure.wakeupOnAWTEvent.remove(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void resetBehaviorCondition(BehaviorStructure paramBehaviorStructure) {
/* 111 */     if (this.enableAWTEventTS != paramBehaviorStructure.awtEventTimestamp) {
/* 112 */       if ((this.AwtId >= 100 && this.AwtId <= 103) || (this.EventMask & 0x1L) != 0L)
/*     */       {
/*     */         
/* 115 */         this.behav.universe.enableComponentEvents();
/*     */       }
/* 117 */       if ((this.AwtId >= 1004 && this.AwtId <= 1005) || (this.EventMask & 0x4L) != 0L)
/*     */       {
/* 119 */         this.behav.universe.enableFocusEvents();
/*     */       }
/* 121 */       if ((this.AwtId >= 400 && this.AwtId <= 402) || (this.EventMask & 0x8L) != 0L)
/*     */       {
/* 123 */         this.behav.universe.enableKeyEvents();
/*     */       }
/* 125 */       if (this.AwtId >= 500 && this.AwtId <= 507) {
/*     */         
/* 127 */         if (this.AwtId == 506 || this.AwtId == 503) {
/*     */           
/* 129 */           this.behav.universe.enableMouseMotionEvents();
/*     */         }
/* 131 */         else if (this.AwtId == 507) {
/* 132 */           this.behav.universe.enableMouseWheelEvents();
/*     */         }
/* 134 */         else if (this.AwtId == 500 || this.AwtId == 504 || this.AwtId == 505 || this.AwtId == 501 || this.AwtId == 502) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 139 */           this.behav.universe.enableMouseEvents();
/*     */         } 
/*     */       } else {
/* 142 */         if ((this.EventMask & 0x10L) != 0L) {
/* 143 */           this.behav.universe.enableMouseEvents();
/*     */         }
/* 145 */         if ((this.EventMask & 0x20L) != 0L) {
/* 146 */           this.behav.universe.enableMouseMotionEvents();
/*     */         }
/* 148 */         if ((this.EventMask & 0x20000L) != 0L) {
/* 149 */           this.behav.universe.enableMouseWheelEvents();
/*     */         }
/*     */       } 
/* 152 */       this.enableAWTEventTS = paramBehaviorStructure.awtEventTimestamp;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\WakeupOnAWTEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */