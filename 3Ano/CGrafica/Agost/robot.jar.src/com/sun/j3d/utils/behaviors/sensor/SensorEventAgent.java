/*     */ package com.sun.j3d.utils.behaviors.sensor;
/*     */ 
/*     */ import com.sun.j3d.utils.timer.J3DTimer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.media.j3d.Sensor;
/*     */ import javax.media.j3d.Transform3D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SensorEventAgent
/*     */ {
/*     */   private long t0;
/*     */   private Object source;
/*     */   private SensorEvent e;
/*     */   private List bindingsList;
/*     */   private SensorBinding[] bindings;
/*     */   private boolean listsDirty;
/*     */   
/*     */   public SensorEventAgent(Object paramObject) {
/* 121 */     this.t0 = 0L;
/* 122 */     this.source = null;
/* 123 */     this.e = new SensorEvent();
/*     */ 
/*     */     
/* 126 */     this.bindingsList = new ArrayList();
/* 127 */     this.bindings = new SensorBinding[0];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     this.listsDirty = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 143 */     this.source = paramObject;
/*     */   }
/*     */   
/*     */   private static class SensorBinding { Sensor sensor;
/*     */     int[] buttons;
/*     */     Transform3D read;
/*     */     
/*     */     SensorBinding(Sensor param1Sensor) {
/* 151 */       this.sensor = null;
/* 152 */       this.buttons = null;
/* 153 */       this.read = null;
/*     */ 
/*     */       
/* 156 */       this.buttonBindingsList = new ArrayList();
/* 157 */       this.buttonBindings = new SensorEventAgent.SensorButtonBinding[0];
/*     */ 
/*     */       
/* 160 */       this.readBindingsList = new ArrayList();
/* 161 */       this.readBindings = new SensorReadListener[0];
/*     */ 
/*     */       
/* 164 */       this.sensor = param1Sensor;
/* 165 */       this.buttons = new int[param1Sensor.getSensorButtonCount()];
/* 166 */       this.read = new Transform3D();
/*     */     }
/*     */     List buttonBindingsList; SensorEventAgent.SensorButtonBinding[] buttonBindings; List readBindingsList; SensorReadListener[] readBindings;
/*     */     void updateArrays() {
/* 170 */       this.buttonBindings = (SensorButtonBinding[])this.buttonBindingsList.toArray(new SensorEventAgent.SensorButtonBinding[this.buttonBindingsList.size()]);
/*     */ 
/*     */       
/* 173 */       this.readBindings = (SensorReadListener[])this.readBindingsList.toArray(new SensorReadListener[this.readBindingsList.size()]);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 179 */       String str = new String();
/* 180 */       str = "sensor " + this.sensor + "\nbutton listener arrays:\n"; byte b;
/* 181 */       for (b = 0; b < this.buttonBindingsList.size(); b++)
/* 182 */         str = str + (SensorEventAgent.SensorButtonBinding)this.buttonBindingsList.get(b); 
/* 183 */       str = str + "read listeners:\n";
/* 184 */       for (b = 0; b < this.readBindingsList.size(); b++) {
/* 185 */         str = str + "  " + (SensorReadListener)this.readBindingsList.get(b) + "\n";
/*     */       }
/* 187 */       return str;
/*     */     } }
/*     */ 
/*     */   
/*     */   private static class SensorButtonBinding {
/*     */     int buttonsHandled;
/*     */     boolean[] prevButtons;
/*     */     boolean multiButton;
/*     */     SensorButtonListener[] listeners;
/*     */     
/*     */     SensorButtonBinding(SensorButtonListener[] param1ArrayOfSensorButtonListener, boolean param1Boolean) {
/* 198 */       this.buttonsHandled = 0;
/* 199 */       this.prevButtons = null;
/* 200 */       this.multiButton = false;
/* 201 */       this.listeners = null;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 206 */       this.prevButtons = new boolean[param1ArrayOfSensorButtonListener.length];
/* 207 */       this.listeners = new SensorButtonListener[param1ArrayOfSensorButtonListener.length];
/*     */       
/* 209 */       for (byte b = 0; b < param1ArrayOfSensorButtonListener.length; b++) {
/* 210 */         this.prevButtons[b] = false;
/* 211 */         this.listeners[b] = param1ArrayOfSensorButtonListener[b];
/*     */       } 
/*     */       
/* 214 */       this.multiButton = param1Boolean;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 218 */       String str = new String();
/* 219 */       str = "  length " + this.listeners.length + ", mutual exclusion " + (!this.multiButton ? 1 : 0) + "\n";
/*     */       
/* 221 */       for (byte b = 0; b < this.listeners.length; b++) {
/* 222 */         str = str + "    " + ((this.listeners[b] == null) ? "null" : this.listeners[b].toString()) + "\n";
/*     */       }
/*     */       
/* 225 */       return str;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SensorBinding getSensorBinding(Sensor paramSensor) {
/* 233 */     for (byte b = 0; b < this.bindingsList.size(); b++) {
/* 234 */       SensorBinding sensorBinding = (SensorBinding)this.bindingsList.get(b);
/* 235 */       if (sensorBinding.sensor == paramSensor)
/* 236 */         return sensorBinding; 
/*     */     } 
/* 238 */     return null;
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
/*     */   public void addSensorButtonListener(Sensor paramSensor, int paramInt, SensorButtonListener paramSensorButtonListener) {
/* 255 */     if (paramSensor == null) {
/* 256 */       throw new NullPointerException("\nsensor is null");
/*     */     }
/* 258 */     if (paramInt >= paramSensor.getSensorButtonCount()) {
/* 259 */       throw new ArrayIndexOutOfBoundsException("\nbutton " + paramInt + " >= sensor button count " + paramSensor.getSensorButtonCount());
/*     */     }
/*     */ 
/*     */     
/* 263 */     SensorBinding sensorBinding = getSensorBinding(paramSensor);
/* 264 */     if (sensorBinding == null) {
/* 265 */       sensorBinding = new SensorBinding(paramSensor);
/* 266 */       this.bindingsList.add(sensorBinding);
/*     */     } 
/*     */     
/* 269 */     SensorButtonListener[] arrayOfSensorButtonListener = new SensorButtonListener[sensorBinding.buttons.length];
/*     */ 
/*     */ 
/*     */     
/* 273 */     arrayOfSensorButtonListener[paramInt] = paramSensorButtonListener;
/* 274 */     SensorButtonBinding sensorButtonBinding = new SensorButtonBinding(arrayOfSensorButtonListener, true);
/*     */ 
/*     */     
/* 277 */     sensorBinding.buttonBindingsList.add(sensorButtonBinding);
/* 278 */     this.listsDirty = true;
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
/*     */   public void addSensorButtonListener(Sensor paramSensor, SensorButtonListener paramSensorButtonListener) {
/* 297 */     if (paramSensor == null) {
/* 298 */       throw new NullPointerException("\nsensor is null");
/*     */     }
/* 300 */     SensorBinding sensorBinding = getSensorBinding(paramSensor);
/* 301 */     if (sensorBinding == null) {
/* 302 */       sensorBinding = new SensorBinding(paramSensor);
/* 303 */       this.bindingsList.add(sensorBinding);
/*     */     } 
/*     */     
/* 306 */     SensorButtonListener[] arrayOfSensorButtonListener = new SensorButtonListener[sensorBinding.buttons.length];
/*     */ 
/*     */ 
/*     */     
/* 310 */     for (byte b = 0; b < sensorBinding.buttons.length; b++) {
/* 311 */       arrayOfSensorButtonListener[b] = paramSensorButtonListener;
/*     */     }
/* 313 */     SensorButtonBinding sensorButtonBinding = new SensorButtonBinding(arrayOfSensorButtonListener, true);
/*     */ 
/*     */     
/* 316 */     sensorBinding.buttonBindingsList.add(sensorButtonBinding);
/* 317 */     this.listsDirty = true;
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
/*     */   public void addSensorButtonListeners(Sensor paramSensor, SensorButtonListener[] paramArrayOfSensorButtonListener) {
/* 340 */     if (paramSensor == null) {
/* 341 */       throw new NullPointerException("\nsensor is null");
/*     */     }
/* 343 */     SensorBinding sensorBinding = getSensorBinding(paramSensor);
/* 344 */     if (sensorBinding == null) {
/* 345 */       sensorBinding = new SensorBinding(paramSensor);
/* 346 */       this.bindingsList.add(sensorBinding);
/*     */     } 
/*     */     
/* 349 */     if (sensorBinding.buttons.length != paramArrayOfSensorButtonListener.length) {
/* 350 */       throw new IllegalArgumentException("\nbuttonListeners length " + paramArrayOfSensorButtonListener.length + " must equal sensor button count " + sensorBinding.buttons.length);
/*     */     }
/*     */ 
/*     */     
/* 354 */     SensorButtonBinding sensorButtonBinding = new SensorButtonBinding(paramArrayOfSensorButtonListener, false);
/*     */ 
/*     */     
/* 357 */     sensorBinding.buttonBindingsList.add(sensorButtonBinding);
/* 358 */     this.listsDirty = true;
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
/*     */   public SensorButtonListener[] getSensorButtonListeners(Sensor paramSensor, int paramInt) {
/* 372 */     if (paramSensor == null) {
/* 373 */       throw new NullPointerException("\nsensor is null");
/*     */     }
/* 375 */     if (paramInt >= paramSensor.getSensorButtonCount()) {
/* 376 */       throw new ArrayIndexOutOfBoundsException("\nbutton " + paramInt + " >= sensor button count " + paramSensor.getSensorButtonCount());
/*     */     }
/*     */ 
/*     */     
/* 380 */     SensorBinding sensorBinding = getSensorBinding(paramSensor);
/* 381 */     if (sensorBinding == null) {
/* 382 */       return null;
/*     */     }
/* 384 */     ArrayList arrayList = new ArrayList();
/* 385 */     for (byte b = 0; b < sensorBinding.buttonBindingsList.size(); b++) {
/* 386 */       SensorButtonBinding sensorButtonBinding = (SensorButtonBinding)sensorBinding.buttonBindingsList.get(b);
/*     */ 
/*     */       
/* 389 */       if (sensorButtonBinding.listeners[paramInt] != null) {
/* 390 */         arrayList.add(sensorButtonBinding.listeners[paramInt]);
/*     */       }
/*     */     } 
/* 393 */     if (arrayList.size() == 0) {
/* 394 */       return null;
/*     */     }
/* 396 */     return (SensorButtonListener[])arrayList.toArray(new SensorButtonListener[arrayList.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeSensorButtonListener(SensorBinding paramSensorBinding, SensorButtonListener paramSensorButtonListener) {
/* 406 */     Iterator iterator = paramSensorBinding.buttonBindingsList.iterator();
/* 407 */     while (iterator.hasNext()) {
/* 408 */       byte b1 = 0;
/* 409 */       SensorButtonBinding sensorButtonBinding = (SensorButtonBinding)iterator.next();
/*     */       
/* 411 */       for (byte b2 = 0; b2 < sensorButtonBinding.listeners.length; b2++) {
/* 412 */         if (sensorButtonBinding.listeners[b2] == paramSensorButtonListener) {
/* 413 */           sensorButtonBinding.listeners[b2] = null;
/* 414 */         } else if (sensorButtonBinding.listeners[b2] != null) {
/* 415 */           b1++;
/*     */         } 
/* 417 */       }  if (b1 == 0) {
/* 418 */         iterator.remove();
/*     */       }
/*     */     } 
/* 421 */     this.listsDirty = true;
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
/*     */   public void removeSensorButtonListener(Sensor paramSensor, SensorButtonListener paramSensorButtonListener) {
/* 434 */     if (paramSensor == null) {
/* 435 */       throw new NullPointerException("\nsensor is null");
/*     */     }
/* 437 */     SensorBinding sensorBinding = getSensorBinding(paramSensor);
/* 438 */     if (sensorBinding == null) {
/*     */       return;
/*     */     }
/* 441 */     removeSensorButtonListener(sensorBinding, paramSensorButtonListener);
/* 442 */     if (sensorBinding.buttonBindingsList.size() == 0 && sensorBinding.readBindingsList.size() == 0)
/*     */     {
/* 444 */       removeSensorBinding(paramSensor);
/*     */     }
/* 446 */     this.listsDirty = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSensorButtonListener(SensorButtonListener paramSensorButtonListener) {
/* 457 */     Iterator iterator = this.bindingsList.iterator();
/* 458 */     while (iterator.hasNext()) {
/* 459 */       SensorBinding sensorBinding = (SensorBinding)iterator.next();
/* 460 */       removeSensorButtonListener(sensorBinding, paramSensorButtonListener);
/*     */       
/* 462 */       if (sensorBinding.buttonBindingsList.size() == 0 && sensorBinding.readBindingsList.size() == 0)
/*     */       {
/* 464 */         iterator.remove();
/*     */       }
/*     */     } 
/* 467 */     this.listsDirty = true;
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
/*     */   public void addSensorReadListener(Sensor paramSensor, SensorReadListener paramSensorReadListener) {
/* 483 */     if (paramSensor == null) {
/* 484 */       throw new NullPointerException("\nsensor is null");
/*     */     }
/* 486 */     SensorBinding sensorBinding = getSensorBinding(paramSensor);
/* 487 */     if (sensorBinding == null) {
/* 488 */       sensorBinding = new SensorBinding(paramSensor);
/* 489 */       this.bindingsList.add(sensorBinding);
/*     */     } 
/* 491 */     sensorBinding.readBindingsList.add(paramSensorReadListener);
/* 492 */     this.listsDirty = true;
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
/*     */   public SensorReadListener[] getSensorReadListeners(Sensor paramSensor) {
/* 504 */     if (paramSensor == null) {
/* 505 */       throw new NullPointerException("\nsensor is null");
/*     */     }
/* 507 */     SensorBinding sensorBinding = getSensorBinding(paramSensor);
/* 508 */     if (sensorBinding == null)
/* 509 */       return null; 
/* 510 */     if (sensorBinding.readBindingsList.size() == 0) {
/* 511 */       return null;
/*     */     }
/* 513 */     return (SensorReadListener[])sensorBinding.readBindingsList.toArray(new SensorReadListener[sensorBinding.readBindingsList.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeSensorReadListener(SensorBinding paramSensorBinding, SensorReadListener paramSensorReadListener) {
/* 523 */     Iterator iterator = paramSensorBinding.readBindingsList.iterator();
/* 524 */     while (iterator.hasNext()) {
/* 525 */       if ((SensorReadListener)iterator.next() == paramSensorReadListener)
/* 526 */         iterator.remove(); 
/*     */     } 
/* 528 */     this.listsDirty = true;
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
/*     */   public void removeSensorReadListener(Sensor paramSensor, SensorReadListener paramSensorReadListener) {
/* 541 */     if (paramSensor == null) {
/* 542 */       throw new NullPointerException("\nsensor is null");
/*     */     }
/* 544 */     SensorBinding sensorBinding = getSensorBinding(paramSensor);
/* 545 */     if (sensorBinding == null) {
/*     */       return;
/*     */     }
/* 548 */     removeSensorReadListener(sensorBinding, paramSensorReadListener);
/* 549 */     if (sensorBinding.buttonBindingsList.size() == 0 && sensorBinding.readBindingsList.size() == 0)
/*     */     {
/* 551 */       removeSensorBinding(paramSensor);
/*     */     }
/* 553 */     this.listsDirty = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSensorReadListener(SensorReadListener paramSensorReadListener) {
/* 564 */     Iterator iterator = this.bindingsList.iterator();
/* 565 */     while (iterator.hasNext()) {
/* 566 */       SensorBinding sensorBinding = (SensorBinding)iterator.next();
/* 567 */       removeSensorReadListener(sensorBinding, paramSensorReadListener);
/*     */       
/* 569 */       if (sensorBinding.buttonBindingsList.size() == 0 && sensorBinding.readBindingsList.size() == 0)
/*     */       {
/* 571 */         iterator.remove();
/*     */       }
/*     */     } 
/* 574 */     this.listsDirty = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSensorBinding(Sensor paramSensor) {
/* 581 */     Iterator iterator = this.bindingsList.iterator();
/* 582 */     while (iterator.hasNext()) {
/* 583 */       SensorBinding sensorBinding = (SensorBinding)iterator.next();
/* 584 */       if (sensorBinding.sensor == paramSensor) {
/* 585 */         iterator.remove();
/*     */         break;
/*     */       } 
/*     */     } 
/* 589 */     this.listsDirty = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Sensor[] getSensors() {
/* 598 */     if (this.bindingsList.size() == 0) {
/* 599 */       return null;
/*     */     }
/* 601 */     Sensor[] arrayOfSensor = new Sensor[this.bindingsList.size()];
/* 602 */     for (byte b = 0; b < this.bindingsList.size(); b++) {
/* 603 */       arrayOfSensor[b] = ((SensorBinding)this.bindingsList.get(b)).sensor;
/*     */     }
/* 605 */     return arrayOfSensor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateArrays() {
/* 613 */     this.bindings = (SensorBinding[])this.bindingsList.toArray(new SensorBinding[this.bindingsList.size()]);
/*     */ 
/*     */     
/* 616 */     for (byte b = 0; b < this.bindings.length; b++) {
/* 617 */       this.bindings[b].updateArrays();
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
/*     */   public void dispatchEvents() {
/* 629 */     long l = this.t0;
/* 630 */     this.t0 = J3DTimer.getValue();
/*     */     
/* 632 */     if (this.listsDirty) {
/* 633 */       updateArrays();
/* 634 */       this.listsDirty = false;
/*     */     } 
/*     */ 
/*     */     
/* 638 */     for (byte b = 0; b < this.bindings.length; b++) {
/* 639 */       SensorBinding sensorBinding = this.bindings[b];
/* 640 */       Sensor sensor = sensorBinding.sensor;
/* 641 */       Transform3D transform3D = sensorBinding.read;
/* 642 */       int[] arrayOfInt = sensorBinding.buttons;
/* 643 */       byte b1 = 0;
/* 644 */       boolean bool1 = true;
/* 645 */       boolean bool2 = false;
/*     */ 
/*     */       
/* 648 */       sensor.getRead(transform3D);
/* 649 */       sensor.lastButtons(arrayOfInt);
/*     */       
/*     */       byte b2;
/* 652 */       for (b2 = 0; b2 < sensorBinding.buttonBindings.length; b2++) {
/* 653 */         SensorButtonBinding sensorButtonBinding = sensorBinding.buttonBindings[b2];
/* 654 */         for (byte b3 = 0; b3 < arrayOfInt.length; b3++) {
/* 655 */           if (sensorButtonBinding.listeners[b3] != null)
/*     */           {
/*     */ 
/*     */             
/* 659 */             if (sensorButtonBinding.prevButtons[b3]) {
/* 660 */               if (arrayOfInt[b3] == 0) {
/* 661 */                 this.e.set(this.source, 2, sensor, transform3D, arrayOfInt, b3, this.t0, l);
/*     */                 
/* 663 */                 sensorButtonBinding.listeners[b3].released(this.e);
/* 664 */                 sensorButtonBinding.prevButtons[b3] = false;
/* 665 */                 sensorButtonBinding.buttonsHandled--;
/*     */               } else {
/*     */                 
/* 668 */                 bool2 = true;
/* 669 */                 b1 = b3;
/*     */               } 
/* 671 */               bool1 = false;
/*     */ 
/*     */ 
/*     */             
/*     */             }
/* 676 */             else if (arrayOfInt[b3] == 1 && (
/* 677 */               sensorButtonBinding.buttonsHandled == 0 || sensorButtonBinding.multiButton)) {
/* 678 */               this.e.set(this.source, 1, sensor, transform3D, arrayOfInt, b3, this.t0, l);
/*     */               
/* 680 */               sensorButtonBinding.listeners[b3].pressed(this.e);
/* 681 */               sensorButtonBinding.prevButtons[b3] = true;
/* 682 */               sensorButtonBinding.buttonsHandled++;
/* 683 */               bool1 = false;
/*     */             } 
/*     */           }
/*     */         } 
/* 687 */         if (bool2) {
/*     */ 
/*     */           
/* 690 */           this.e.set(this.source, 3, sensor, transform3D, arrayOfInt, -1, this.t0, l);
/*     */           
/* 692 */           sensorButtonBinding.listeners[b1].dragged(this.e);
/*     */         } 
/*     */       } 
/*     */       
/* 696 */       if (bool1) {
/* 697 */         this.e.set(this.source, 4, sensor, transform3D, arrayOfInt, -1, this.t0, l);
/*     */         
/* 699 */         for (b2 = 0; b2 < sensorBinding.readBindings.length; b2++) {
/* 700 */           sensorBinding.readBindings[b2].read(this.e);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 707 */     String str = "SensorEventAgent@" + Integer.toHexString(hashCode());
/* 708 */     str = str + "\nsensor bindings:\n\n";
/* 709 */     for (byte b = 0; b < this.bindingsList.size(); b++) {
/* 710 */       str = str + ((SensorBinding)this.bindingsList.get(b)).toString() + "\n";
/*     */     }
/* 712 */     return str;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\behaviors\sensor\SensorEventAgent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */