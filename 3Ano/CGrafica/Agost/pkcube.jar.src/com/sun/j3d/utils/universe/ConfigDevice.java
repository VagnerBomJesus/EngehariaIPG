/*    */ package com.sun.j3d.utils.universe;
/*    */ 
/*    */ import javax.media.j3d.InputDevice;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ConfigDevice
/*    */   extends ConfigObject
/*    */ {
/*    */   InputDevice j3dInputDevice;
/*    */   
/*    */   InputDevice createInputDevice() {
/* 63 */     this.j3dInputDevice = (InputDevice)createTargetObject();
/* 64 */     return this.j3dInputDevice;
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\com\sun\j3\\util\\universe\ConfigDevice.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */