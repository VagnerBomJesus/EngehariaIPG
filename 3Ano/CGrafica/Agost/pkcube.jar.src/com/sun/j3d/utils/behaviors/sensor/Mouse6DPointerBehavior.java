/*     */ package com.sun.j3d.utils.behaviors.sensor;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import javax.media.j3d.Behavior;
/*     */ import javax.media.j3d.Sensor;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.media.j3d.View;
/*     */ import javax.media.j3d.WakeupCondition;
/*     */ import javax.media.j3d.WakeupOnElapsedFrames;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Mouse6DPointerBehavior
/*     */   extends Behavior
/*     */ {
/*     */   private Sensor sensor;
/*     */   private SensorEventAgent eventAgent;
/*     */   private TransformGroup echoTransformGroup;
/*     */   private WakeupCondition conditions;
/*     */   
/*     */   public Mouse6DPointerBehavior(Sensor paramSensor, double paramDouble, boolean paramBoolean) {
/*  63 */     this.sensor = null;
/*  64 */     this.eventAgent = null;
/*  65 */     this.echoTransformGroup = null;
/*  66 */     this.conditions = new WakeupOnElapsedFrames(0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     this.sensor = paramSensor;
/*  87 */     this.echoTransformGroup = new TransformGroup();
/*  88 */     this.echoTransformGroup.setCapability(18);
/*     */ 
/*     */     
/*  91 */     Point3d point3d = new Point3d();
/*  92 */     paramSensor.getHotspot(point3d);
/*     */     
/*  94 */     Transform3D transform3D = new Transform3D();
/*  95 */     Vector3f vector3f = new Vector3f(point3d);
/*  96 */     transform3D.set(vector3f);
/*     */     
/*  98 */     SensorGnomonEcho sensorGnomonEcho = new SensorGnomonEcho(transform3D, 0.001D * paramDouble, 0.005D * paramDouble, paramBoolean);
/*     */     
/* 100 */     this.echoTransformGroup.addChild(sensorGnomonEcho);
/*     */     
/* 102 */     this.eventAgent = new SensorEventAgent(this);
/* 103 */     this.eventAgent.addSensorReadListener(paramSensor, new EchoReadListener());
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
/*     */   public Mouse6DPointerBehavior(Sensor paramSensor, TransformGroup paramTransformGroup) {
/*     */     this.sensor = null;
/*     */     this.eventAgent = null;
/*     */     this.echoTransformGroup = null;
/*     */     this.conditions = new WakeupOnElapsedFrames(0);
/* 119 */     this.sensor = paramSensor;
/* 120 */     this.echoTransformGroup = paramTransformGroup;
/* 121 */     this.eventAgent = new SensorEventAgent(this);
/* 122 */     this.eventAgent.addSensorReadListener(paramSensor, new EchoReadListener());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public Sensor getSensor() { return this.sensor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public TransformGroup getEcho() { return this.echoTransformGroup; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public SensorEventAgent getSensorEventAgent() { return this.eventAgent; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public void initialize() { wakeupOn(this.conditions); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processStimulus(Enumeration paramEnumeration) {
/* 168 */     this.eventAgent.dispatchEvents();
/* 169 */     wakeupOn(this.conditions);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public class EchoReadListener
/*     */     implements SensorReadListener
/*     */   {
/* 177 */     private Transform3D t3d = new Transform3D();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void read(SensorEvent param1SensorEvent) {
/* 191 */       Mouse6DPointerBehavior.this.getView().getSensorToVworld(param1SensorEvent.getSensor(), this.t3d);
/* 192 */       Mouse6DPointerBehavior.this.echoTransformGroup.setTransform(this.t3d);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\utils\behaviors\sensor\Mouse6DPointerBehavior.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */