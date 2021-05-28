/*    */ package javax.media.j3d;
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
/*    */ class StructureUpdateThread
/*    */   extends J3dThread
/*    */ {
/*    */   J3dStructure structure;
/* 28 */   private static int[] numInstances = new int[7];
/* 29 */   private int[] instanceNum = new int[7];
/*    */ 
/*    */   
/* 32 */   private int newInstanceNum(int paramInt) { numInstances[paramInt] = numInstances[paramInt] + 1; return numInstances[paramInt] + 1; }
/*    */ 
/*    */   
/*    */   int getInstanceNum(int paramInt) {
/* 36 */     if (this.instanceNum[paramInt] == 0)
/* 37 */       this.instanceNum[paramInt] = newInstanceNum(paramInt); 
/* 38 */     return this.instanceNum[paramInt];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   StructureUpdateThread(ThreadGroup paramThreadGroup, J3dStructure paramJ3dStructure, int paramInt) {
/* 45 */     super(paramThreadGroup);
/* 46 */     this.structure = paramJ3dStructure;
/* 47 */     this.type = paramInt;
/* 48 */     this.classification = 2;
/*    */     
/* 50 */     switch (this.type) {
/*    */       case 64:
/* 52 */         setName("J3D-GeometryStructureUpdateThread-" + getInstanceNum(0));
/*    */         break;
/*    */       case 128:
/* 55 */         setName("J3D-RenderStructureUpdateThread-" + getInstanceNum(1));
/*    */         break;
/*    */       case 256:
/* 58 */         setName("J3D-BehaviorStructureUpdateThread-" + getInstanceNum(2));
/*    */         break;
/*    */       case 512:
/* 61 */         setName("J3D-SoundStructureUpdateThread-" + getInstanceNum(3));
/*    */         break;
/*    */       
/*    */       case 1024:
/* 65 */         setName("J3D-RenderingAttributesStructureUpdateThread");
/*    */         break;
/*    */       case 4096:
/* 68 */         setName("J3D-RenderingEnvironmentStructureUpdateThread-" + getInstanceNum(4));
/*    */         break;
/*    */       
/*    */       case 8192:
/* 72 */         setName("J3D-TransformStructureUpdateThread-" + getInstanceNum(5));
/*    */         break;
/*    */       case 2:
/* 75 */         setName("J3D-SoundSchedulerUpdateThread-" + getInstanceNum(6));
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 83 */   void doWork(long paramLong) { this.structure.processMessages(paramLong); }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\javax\media\j3d\StructureUpdateThread.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */