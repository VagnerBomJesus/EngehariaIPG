/*      */ package javax.media.j3d;
/*      */ 
/*      */ import com.sun.opengl.cg.CGcontext;
/*      */ import com.sun.opengl.cg.CGparameter;
/*      */ import com.sun.opengl.cg.CGprogram;
/*      */ import com.sun.opengl.cg.CgGL;
/*      */ import com.sun.opengl.util.BufferUtil;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Canvas;
/*      */ import java.awt.DisplayMode;
/*      */ import java.awt.EventQueue;
/*      */ import java.awt.Frame;
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.GraphicsDevice;
/*      */ import java.awt.GraphicsEnvironment;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.nio.Buffer;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.DoubleBuffer;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.util.ArrayList;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import javax.media.opengl.AWTGraphicsConfiguration;
/*      */ import javax.media.opengl.AWTGraphicsDevice;
/*      */ import javax.media.opengl.AbstractGraphicsConfiguration;
/*      */ import javax.media.opengl.DefaultGLCapabilitiesChooser;
/*      */ import javax.media.opengl.GL;
/*      */ import javax.media.opengl.GLCapabilities;
/*      */ import javax.media.opengl.GLCapabilitiesChooser;
/*      */ import javax.media.opengl.GLContext;
/*      */ import javax.media.opengl.GLDrawable;
/*      */ import javax.media.opengl.GLDrawableFactory;
/*      */ import javax.media.opengl.GLException;
/*      */ import javax.media.opengl.GLPbuffer;
/*      */ import javax.media.opengl.Threading;
/*      */ import javax.media.opengl.glu.GLU;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class JoglPipeline
/*      */   extends Pipeline
/*      */ {
/*      */   private boolean cgLibraryAvailable = false;
/*      */   private static final boolean DEBUG = true;
/*      */   private static final boolean VERBOSE = false;
/*      */   private static final boolean DEBUG_CONFIG = false;
/*      */   private static final boolean EXTRA_DEBUGGING = false;
/*      */   private static final int WAIT_TIME = 1000;
/*      */   private static final int MIN_FRAME_SIZE = 1;
/*      */   
/*      */   void initialize(Pipeline.Type paramType) {
/*   60 */     super.initialize(paramType);
/*      */     
/*   62 */     assert paramType == Pipeline.Type.JOGL;
/*      */ 
/*      */     
/*   65 */     Threading.disableSingleThreading();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void loadLibraries(int paramInt) {
/*   74 */     if (paramInt == 2) {
/*      */       
/*      */       try {
/*      */ 
/*      */ 
/*      */         
/*   80 */         Class.forName("com.sun.opengl.cg.CgGL");
/*   81 */         this.cgLibraryAvailable = true;
/*   82 */       } catch (Exception exception) {
/*   83 */         System.err.println(exception);
/*   84 */       } catch (Error error) {
/*   85 */         System.err.println(error);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   95 */   boolean isCgLibraryAvailable() { return this.cgLibraryAvailable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  103 */   boolean isGLSLLibraryAvailable() { return true; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void freeD3DArray(GeometryArrayRetained paramGeometryArrayRetained, boolean paramBoolean) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  133 */   void execute(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, int paramInt8, int[] paramArrayOfInt3, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt9) { executeGeometryArray(paramContext, paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramBoolean3, paramInt2, paramInt3, paramInt4, paramInt5, paramArrayOfInt1, paramInt6, paramArrayOfInt2, paramInt7, paramInt8, paramArrayOfInt3, paramArrayOfFloat1, null, paramArrayOfFloat2, paramInt9); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void executeVA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, float[] paramArrayOfFloat1, double[] paramArrayOfDouble, int paramInt6, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, int paramInt7, float[] paramArrayOfFloat3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, float[][] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3, int paramInt10, int[] paramArrayOfInt4, int paramInt11, Object[] paramArrayOfObject, int paramInt12) {
/*  161 */     boolean bool1 = ((paramInt4 & true) != 0) ? 1 : 0;
/*  162 */     boolean bool2 = ((paramInt4 & 0x2) != 0) ? 1 : 0;
/*  163 */     boolean bool3 = ((paramInt4 & 0x4) != 0) ? 1 : 0;
/*  164 */     boolean bool4 = ((paramInt4 & 0x8) != 0) ? 1 : 0;
/*  165 */     boolean bool5 = ((paramInt4 & 0x10) != 0) ? 1 : 0;
/*  166 */     boolean bool6 = ((paramInt4 & 0x40) != 0) ? 1 : 0;
/*  167 */     boolean bool7 = ((paramInt4 & 0x20) != 0) ? 1 : 0;
/*      */     
/*  169 */     FloatBuffer floatBuffer1 = null;
/*  170 */     DoubleBuffer doubleBuffer = null;
/*  171 */     FloatBuffer floatBuffer2 = null;
/*  172 */     ByteBuffer byteBuffer = null;
/*  173 */     FloatBuffer[] arrayOfFloatBuffer1 = null;
/*  174 */     FloatBuffer floatBuffer3 = null;
/*  175 */     FloatBuffer[] arrayOfFloatBuffer2 = null;
/*      */ 
/*      */     
/*  178 */     if (bool6) {
/*  179 */       arrayOfFloatBuffer2 = getVertexAttrSetBuffer(paramArrayOfFloat);
/*      */     }
/*      */ 
/*      */     
/*  183 */     if (bool7) {
/*  184 */       arrayOfFloatBuffer1 = getTexCoordSetBuffer(paramArrayOfObject);
/*      */     }
/*      */ 
/*      */     
/*  188 */     if (bool1) {
/*  189 */       floatBuffer1 = getVertexArrayBuffer(paramArrayOfFloat1);
/*  190 */     } else if (bool2) {
/*  191 */       doubleBuffer = getVertexArrayBuffer(paramArrayOfDouble);
/*      */     } 
/*      */ 
/*      */     
/*  195 */     if (bool3) {
/*  196 */       floatBuffer2 = getColorArrayBuffer(paramArrayOfFloat2);
/*  197 */     } else if (bool4) {
/*  198 */       byteBuffer = getColorArrayBuffer(paramArrayOfByte);
/*      */     } 
/*      */ 
/*      */     
/*  202 */     if (bool5) {
/*  203 */       floatBuffer3 = getNormalArrayBuffer(paramArrayOfFloat3);
/*      */     }
/*      */     
/*  206 */     int[] arrayOfInt1 = null;
/*  207 */     int[] arrayOfInt2 = null;
/*  208 */     int i = 0;
/*  209 */     if (paramInt1 == 5 || paramInt1 == 6 || paramInt1 == 7) {
/*      */ 
/*      */       
/*  212 */       arrayOfInt1 = ((GeometryStripArrayRetained)paramGeometryArrayRetained).stripVertexCounts;
/*  213 */       i = arrayOfInt1.length;
/*  214 */       arrayOfInt2 = ((GeometryStripArrayRetained)paramGeometryArrayRetained).stripStartOffsetIndices;
/*      */     } 
/*      */     
/*  217 */     executeGeometryArrayVA(paramContext, paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramInt2, paramInt3, paramInt4, paramInt5, floatBuffer1, doubleBuffer, paramInt6, floatBuffer2, byteBuffer, paramInt7, floatBuffer3, paramInt8, paramArrayOfInt1, paramArrayOfInt2, arrayOfFloatBuffer2, paramInt9, paramArrayOfInt3, paramInt10, paramArrayOfInt4, paramInt11, arrayOfFloatBuffer1, paramInt12, arrayOfInt1, i, arrayOfInt2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void executeVABuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Object paramObject1, int paramInt6, Object paramObject2, float[] paramArrayOfFloat, byte[] paramArrayOfByte, int paramInt7, Object paramObject3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, Object[] paramArrayOfObject1, int paramInt9, int[] paramArrayOfInt3, int paramInt10, int[] paramArrayOfInt4, int paramInt11, Object[] paramArrayOfObject2, int paramInt12) {
/*  254 */     boolean bool1 = ((paramInt4 & true) != 0) ? 1 : 0;
/*  255 */     boolean bool2 = ((paramInt4 & 0x2) != 0) ? 1 : 0;
/*  256 */     boolean bool3 = ((paramInt4 & 0x4) != 0) ? 1 : 0;
/*  257 */     boolean bool4 = ((paramInt4 & 0x8) != 0) ? 1 : 0;
/*  258 */     boolean bool5 = ((paramInt4 & 0x10) != 0) ? 1 : 0;
/*  259 */     boolean bool6 = ((paramInt4 & 0x40) != 0) ? 1 : 0;
/*  260 */     boolean bool7 = ((paramInt4 & 0x20) != 0) ? 1 : 0;
/*      */     
/*  262 */     FloatBuffer floatBuffer1 = null;
/*  263 */     DoubleBuffer doubleBuffer = null;
/*  264 */     FloatBuffer floatBuffer2 = null;
/*  265 */     ByteBuffer byteBuffer = null;
/*  266 */     FloatBuffer[] arrayOfFloatBuffer1 = null;
/*  267 */     FloatBuffer floatBuffer3 = null;
/*  268 */     FloatBuffer[] arrayOfFloatBuffer2 = null;
/*      */ 
/*      */     
/*  271 */     if (bool6) {
/*  272 */       arrayOfFloatBuffer2 = getVertexAttrSetBuffer(paramArrayOfObject1);
/*      */     }
/*      */ 
/*      */     
/*  276 */     if (bool7) {
/*  277 */       arrayOfFloatBuffer1 = new FloatBuffer[paramArrayOfObject2.length];
/*  278 */       for (byte b = 0; b < paramArrayOfObject2.length; b++) {
/*  279 */         arrayOfFloatBuffer1[b] = (FloatBuffer)paramArrayOfObject2[b];
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  284 */     if (bool1) {
/*  285 */       floatBuffer1 = (FloatBuffer)paramObject1;
/*  286 */     } else if (bool2) {
/*  287 */       doubleBuffer = (DoubleBuffer)paramObject1;
/*      */     } 
/*      */     
/*  290 */     if (floatBuffer1 == null && doubleBuffer == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  295 */     if (bool3) {
/*  296 */       if (paramArrayOfFloat != null)
/*  297 */       { floatBuffer2 = getColorArrayBuffer(paramArrayOfFloat); }
/*      */       else
/*  299 */       { floatBuffer2 = (FloatBuffer)paramObject2; } 
/*  300 */     } else if (bool4) {
/*  301 */       if (paramArrayOfByte != null) {
/*  302 */         byteBuffer = getColorArrayBuffer(paramArrayOfByte);
/*      */       } else {
/*  304 */         byteBuffer = (ByteBuffer)paramObject2;
/*      */       } 
/*      */     } 
/*      */     
/*  308 */     if (bool5) {
/*  309 */       floatBuffer3 = (FloatBuffer)paramObject3;
/*      */     }
/*      */     
/*  312 */     int[] arrayOfInt1 = null;
/*  313 */     int[] arrayOfInt2 = null;
/*  314 */     int i = 0;
/*  315 */     if (paramInt1 == 5 || paramInt1 == 6 || paramInt1 == 7) {
/*      */ 
/*      */       
/*  318 */       arrayOfInt1 = ((GeometryStripArrayRetained)paramGeometryArrayRetained).stripVertexCounts;
/*  319 */       i = arrayOfInt1.length;
/*  320 */       arrayOfInt2 = ((GeometryStripArrayRetained)paramGeometryArrayRetained).stripStartOffsetIndices;
/*      */     } 
/*      */     
/*  323 */     executeGeometryArrayVA(paramContext, paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramInt2, paramInt3, paramInt4, paramInt5, floatBuffer1, doubleBuffer, paramInt6, floatBuffer2, byteBuffer, paramInt7, floatBuffer3, paramInt8, paramArrayOfInt1, paramArrayOfInt2, arrayOfFloatBuffer2, paramInt9, paramArrayOfInt3, paramInt10, paramArrayOfInt4, paramInt11, arrayOfFloatBuffer1, paramInt12, arrayOfInt1, i, arrayOfInt2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  351 */   void executeInterleavedBuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, Object paramObject, float[] paramArrayOfFloat, int paramInt8) { executeGeometryArray(paramContext, paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramBoolean3, paramInt2, paramInt3, paramInt4, paramInt5, paramArrayOfInt1, paramInt6, paramArrayOfInt2, paramInt7, 0, null, null, (Buffer)paramObject, paramArrayOfFloat, paramInt8); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setVertexFormat(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
/*  363 */     GL gL = context(paramContext).getGL();
/*      */ 
/*      */     
/*  366 */     if ((paramInt & 0x2) != 0) {
/*  367 */       gL.glEnableClientState(32885);
/*      */     } else {
/*  369 */       gL.glDisableClientState(32885);
/*      */     } 
/*  371 */     if (!paramBoolean2 && (paramInt & 0x4) != 0) {
/*  372 */       gL.glEnableClientState(32886);
/*      */     } else {
/*  374 */       gL.glDisableClientState(32886);
/*      */     } 
/*      */     
/*  377 */     if (gL.isExtensionAvailable("GL_SUN_global_alpha")) {
/*  378 */       if (paramBoolean1) {
/*  379 */         gL.glEnable(33241);
/*      */       } else {
/*  381 */         gL.glDisable(33241);
/*      */       } 
/*      */     }
/*      */     
/*  385 */     if ((paramInt & true) != 0) {
/*  386 */       gL.glEnableClientState(32884);
/*      */     } else {
/*  388 */       gL.glDisableClientState(32884);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void disableGlobalAlpha(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
/*  396 */     GL gL = context(paramContext).getGL();
/*      */     
/*  398 */     if (gL.isExtensionAvailable("GL_SUN_global_alpha") && 
/*  399 */       !paramBoolean2 && (paramInt & 0x4) != 0 && 
/*  400 */       paramBoolean1) {
/*  401 */       gL.glDisable(33241);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void buildGA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, int[] paramArrayOfInt3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat) {
/*  421 */     JoglContext joglContext = (JoglContext)paramContext;
/*  422 */     GL gL = context(paramContext).getGL();
/*  423 */     FloatBuffer floatBuffer = null;
/*  424 */     int i = 0, j = 0, k = 0, m = 0, n = 0;
/*  425 */     int i1 = 0;
/*  426 */     int i2 = 0;
/*  427 */     if ((paramInt4 & true) != 0) {
/*  428 */       i += true;
/*      */     }
/*  430 */     if ((paramInt4 & 0x2) != 0) {
/*  431 */       i += true;
/*  432 */       j += true;
/*      */     } 
/*      */     
/*  435 */     if ((paramInt4 & 0x4) != 0) {
/*  436 */       if ((paramInt4 & 0x80) != 0) {
/*  437 */         if ((paramInt4 & 0x8) != 0) {
/*  438 */           i += true;
/*  439 */           k += true;
/*  440 */           j += true;
/*      */         } else {
/*  442 */           i += true;
/*  443 */           k += true;
/*  444 */           j += true;
/*      */         } 
/*      */       } else {
/*  447 */         i += true;
/*  448 */         k += true;
/*  449 */         j += true;
/*      */       } 
/*      */     }
/*      */     
/*  453 */     if ((paramInt4 & 0x460) != 0) {
/*  454 */       if ((paramInt4 & 0x20) != 0) {
/*  455 */         i1 = 2 * paramInt5;
/*  456 */       } else if ((paramInt4 & 0x40) != 0) {
/*  457 */         i1 = 3 * paramInt5;
/*  458 */       } else if ((paramInt4 & 0x400) != 0) {
/*  459 */         i1 = 4 * paramInt5;
/*      */       } 
/*  461 */       i += i1;
/*  462 */       k += i1;
/*  463 */       m += i1;
/*  464 */       j += i1;
/*      */     } 
/*      */     
/*  467 */     int i3 = 0;
/*  468 */     if ((paramInt4 & 0x1000) != 0) {
/*  469 */       for (byte b = 0; b < paramInt7; b++) {
/*  470 */         i3 += paramArrayOfInt3[b];
/*      */       }
/*  472 */       i += i3;
/*  473 */       k += i3;
/*  474 */       m += i3;
/*  475 */       j += i3;
/*  476 */       n += i3;
/*      */     } 
/*      */     
/*  479 */     int i4 = i * 4;
/*      */     
/*  481 */     int i5 = paramInt2 * i;
/*  482 */     k += i5;
/*  483 */     m += i5;
/*  484 */     j += i5;
/*  485 */     n += i5;
/*  486 */     i2 += i5;
/*      */ 
/*      */     
/*  489 */     boolean bool = false;
/*  490 */     if (paramBoolean2 && !paramBoolean3) {
/*  491 */       bool = true;
/*      */     }
/*      */     
/*  494 */     if (paramInt1 == 5 || paramInt1 == 6 || paramInt1 == 7) {
/*      */ 
/*      */       
/*  497 */       int[] arrayOfInt = ((GeometryStripArrayRetained)paramGeometryArrayRetained).stripVertexCounts;
/*      */       
/*  499 */       byte b1 = 0;
/*  500 */       switch (paramInt1) {
/*      */         case 5:
/*  502 */           b1 = 5;
/*      */           break;
/*      */         case 6:
/*  505 */           b1 = 6;
/*      */           break;
/*      */         case 7:
/*  508 */           b1 = 3;
/*      */           break;
/*      */       } 
/*      */       
/*  512 */       if (paramBoolean3) {
/*  513 */         paramInt4 &= 0xFFFFFFFB;
/*      */       }
/*      */       
/*  516 */       for (byte b2 = 0; b2 < arrayOfInt.length; b2++) {
/*  517 */         gL.glBegin(b1);
/*  518 */         for (byte b = 0; b < arrayOfInt[b2]; b++) {
/*  519 */           if ((paramInt4 & 0x2) != 0) {
/*  520 */             if (paramArrayOfDouble2 != null) {
/*  521 */               float f1 = (float)(paramArrayOfDouble2[0] * paramArrayOfFloat[k] + paramArrayOfDouble2[1] * paramArrayOfFloat[k + 1] + paramArrayOfDouble2[2] * paramArrayOfFloat[k + 2]);
/*      */ 
/*      */               
/*  524 */               float f2 = (float)(paramArrayOfDouble2[4] * paramArrayOfFloat[k] + paramArrayOfDouble2[5] * paramArrayOfFloat[k + 1] + paramArrayOfDouble2[6] * paramArrayOfFloat[k + 2]);
/*      */ 
/*      */               
/*  527 */               float f3 = (float)(paramArrayOfDouble2[8] * paramArrayOfFloat[k] + paramArrayOfDouble2[9] * paramArrayOfFloat[k + 1] + paramArrayOfDouble2[10] * paramArrayOfFloat[k + 2]);
/*      */ 
/*      */               
/*  530 */               gL.glNormal3f(f1, f2, f3);
/*      */             } else {
/*  532 */               gL.glNormal3f(paramArrayOfFloat[k], paramArrayOfFloat[k + 1], paramArrayOfFloat[k + 2]);
/*      */             } 
/*      */           }
/*  535 */           if ((paramInt4 & 0x4) != 0) {
/*  536 */             if (bool) {
/*  537 */               gL.glColor4f(paramArrayOfFloat[m], paramArrayOfFloat[m + 1], paramArrayOfFloat[m + 2], paramArrayOfFloat[m + 3] * paramFloat);
/*      */ 
/*      */ 
/*      */             
/*      */             }
/*  542 */             else if ((paramInt4 & 0x8) != 0) {
/*  543 */               gL.glColor4f(paramArrayOfFloat[m], paramArrayOfFloat[m + 1], paramArrayOfFloat[m + 2], paramArrayOfFloat[m + 3]);
/*      */             
/*      */             }
/*      */             else {
/*      */               
/*  548 */               gL.glColor3f(paramArrayOfFloat[m], paramArrayOfFloat[m + 1], paramArrayOfFloat[m + 2]);
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  555 */           if ((paramInt4 & 0x1000) != 0) {
/*  556 */             int i6 = i2;
/*  557 */             if (floatBuffer == null) {
/*  558 */               floatBuffer = FloatBuffer.wrap(paramArrayOfFloat);
/*      */             }
/*  560 */             for (byte b3 = 0; b3 < paramInt7; b3++) {
/*  561 */               switch (paramArrayOfInt3[b3]) {
/*      */                 case 1:
/*  563 */                   floatBuffer.position(i6);
/*  564 */                   joglContext.vertexAttr1fv(gL, b3, floatBuffer);
/*      */                   break;
/*      */                 case 2:
/*  567 */                   floatBuffer.position(i6);
/*  568 */                   joglContext.vertexAttr2fv(gL, b3, floatBuffer);
/*      */                   break;
/*      */                 case 3:
/*  571 */                   floatBuffer.position(i6);
/*  572 */                   joglContext.vertexAttr3fv(gL, b3, floatBuffer);
/*      */                   break;
/*      */                 case 4:
/*  575 */                   floatBuffer.position(i6);
/*  576 */                   joglContext.vertexAttr4fv(gL, b3, floatBuffer);
/*      */                   break;
/*      */               } 
/*      */               
/*  580 */               i6 += paramArrayOfInt3[b3];
/*      */             } 
/*      */           } 
/*      */           
/*  584 */           if ((paramInt4 & 0x460) != 0 && 
/*  585 */             paramInt6 > 0) {
/*  586 */             if (gL.isExtensionAvailable("GL_VERSION_1_3")) {
/*  587 */               if ((paramInt4 & 0x20) != 0) {
/*  588 */                 for (byte b3 = 0; b3 < paramInt6; b3++) {
/*  589 */                   if (paramArrayOfInt2[b3] != -1) {
/*  590 */                     int i6 = n + paramArrayOfInt2[b3];
/*  591 */                     gL.glMultiTexCoord2f('蓀' + b3, paramArrayOfFloat[i6], paramArrayOfFloat[i6 + 1]);
/*      */                   }
/*      */                 
/*      */                 }
/*      */               
/*  596 */               } else if ((paramInt4 & 0x40) != 0) {
/*  597 */                 for (byte b3 = 0; b3 < paramInt6; b3++) {
/*  598 */                   if (paramArrayOfInt2[b3] != -1) {
/*  599 */                     int i6 = n + paramArrayOfInt2[b3];
/*  600 */                     gL.glMultiTexCoord3f('蓀' + b3, paramArrayOfFloat[i6], paramArrayOfFloat[i6 + 1], paramArrayOfFloat[i6 + 2]);
/*      */                   }
/*      */                 
/*      */                 }
/*      */               
/*      */               } else {
/*      */                 
/*  607 */                 for (byte b3 = 0; b3 < paramInt6; b3++) {
/*  608 */                   if (paramArrayOfInt2[b3] != -1) {
/*  609 */                     int i6 = n + paramArrayOfInt2[b3];
/*  610 */                     gL.glMultiTexCoord4f('蓀' + b3, paramArrayOfFloat[i6], paramArrayOfFloat[i6 + 1], paramArrayOfFloat[i6 + 2], paramArrayOfFloat[i6 + 3]);
/*      */                   
/*      */                   }
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/*      */             
/*      */             }
/*  619 */             else if (paramArrayOfInt2[0] != -1) {
/*  620 */               int i6 = n + paramArrayOfInt2[0];
/*  621 */               if ((paramInt4 & 0x20) != 0) {
/*  622 */                 gL.glTexCoord2f(paramArrayOfFloat[i6], paramArrayOfFloat[i6 + 1]);
/*  623 */               } else if ((paramInt4 & 0x40) != 0) {
/*  624 */                 gL.glTexCoord3f(paramArrayOfFloat[i6], paramArrayOfFloat[i6 + 1], paramArrayOfFloat[i6 + 2]);
/*      */               } else {
/*  626 */                 gL.glTexCoord4f(paramArrayOfFloat[i6], paramArrayOfFloat[i6 + 1], paramArrayOfFloat[i6 + 2], paramArrayOfFloat[i6 + 3]);
/*      */               } 
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  635 */           if ((paramInt4 & true) != 0) {
/*  636 */             if (paramArrayOfDouble1 != null) {
/*      */               
/*  638 */               float f1 = (float)(paramArrayOfDouble1[12] * paramArrayOfFloat[j] + paramArrayOfDouble1[13] * paramArrayOfFloat[j + 1] + paramArrayOfDouble1[14] * paramArrayOfFloat[j + 2] + paramArrayOfDouble1[15]);
/*      */ 
/*      */ 
/*      */               
/*  642 */               float f2 = 1.0F / f1;
/*  643 */               float f3 = (float)(paramArrayOfDouble1[0] * paramArrayOfFloat[j] + paramArrayOfDouble1[1] * paramArrayOfFloat[j + 1] + paramArrayOfDouble1[2] * paramArrayOfFloat[j + 2] + paramArrayOfDouble1[3]) * f2;
/*      */ 
/*      */ 
/*      */               
/*  647 */               float f4 = (float)(paramArrayOfDouble1[4] * paramArrayOfFloat[j] + paramArrayOfDouble1[5] * paramArrayOfFloat[j + 1] + paramArrayOfDouble1[6] * paramArrayOfFloat[j + 2] + paramArrayOfDouble1[7]) * f2;
/*      */ 
/*      */ 
/*      */               
/*  651 */               float f5 = (float)(paramArrayOfDouble1[8] * paramArrayOfFloat[j] + paramArrayOfDouble1[9] * paramArrayOfFloat[j + 1] + paramArrayOfDouble1[10] * paramArrayOfFloat[j + 2] + paramArrayOfDouble1[11]) * f2;
/*      */ 
/*      */ 
/*      */               
/*  655 */               gL.glVertex3f(f3, f4, f5);
/*      */             } else {
/*  657 */               gL.glVertex3f(paramArrayOfFloat[j], paramArrayOfFloat[j + 1], paramArrayOfFloat[j + 2]);
/*      */             } 
/*      */           }
/*  660 */           k += i;
/*  661 */           m += i;
/*  662 */           j += i;
/*  663 */           n += i;
/*  664 */           i2 += i;
/*      */         } 
/*  666 */         gL.glEnd();
/*      */       } 
/*  668 */     } else if (paramInt1 == 1 || paramInt1 == 2 || paramInt1 == 3 || paramInt1 == 4) {
/*      */ 
/*      */ 
/*      */       
/*  672 */       byte b1 = 0;
/*  673 */       switch (paramInt1) {
/*      */         case 1:
/*  675 */           b1 = 7;
/*      */           break;
/*      */         case 2:
/*  678 */           b1 = 4;
/*      */           break;
/*      */         case 3:
/*  681 */           b1 = 0;
/*      */           break;
/*      */         case 4:
/*  684 */           b1 = 1;
/*      */           break;
/*      */       } 
/*      */       
/*  688 */       if (paramBoolean3) {
/*  689 */         paramInt4 &= 0xFFFFFFFB;
/*      */       }
/*      */       
/*  692 */       gL.glBegin(b1);
/*  693 */       for (byte b2 = 0; b2 < paramInt3; b2++) {
/*  694 */         if ((paramInt4 & 0x2) != 0) {
/*  695 */           if (paramArrayOfDouble2 != null) {
/*  696 */             float f1 = (float)(paramArrayOfDouble2[0] * paramArrayOfFloat[k] + paramArrayOfDouble2[1] * paramArrayOfFloat[k + 1] + paramArrayOfDouble2[2] * paramArrayOfFloat[k + 2]);
/*      */ 
/*      */             
/*  699 */             float f2 = (float)(paramArrayOfDouble2[4] * paramArrayOfFloat[k] + paramArrayOfDouble2[5] * paramArrayOfFloat[k + 1] + paramArrayOfDouble2[6] * paramArrayOfFloat[k + 2]);
/*      */ 
/*      */             
/*  702 */             float f3 = (float)(paramArrayOfDouble2[8] * paramArrayOfFloat[k] + paramArrayOfDouble2[9] * paramArrayOfFloat[k + 1] + paramArrayOfDouble2[10] * paramArrayOfFloat[k + 2]);
/*      */ 
/*      */             
/*  705 */             gL.glNormal3f(f1, f2, f3);
/*      */           } else {
/*  707 */             gL.glNormal3f(paramArrayOfFloat[k], paramArrayOfFloat[k + 1], paramArrayOfFloat[k + 2]);
/*      */           } 
/*      */         }
/*  710 */         if ((paramInt4 & 0x4) != 0) {
/*  711 */           if (bool) {
/*      */             float f4, f3, f2, f1;
/*  713 */             if ((paramInt4 & 0x8) != 0) {
/*  714 */               f1 = paramArrayOfFloat[m];
/*  715 */               f2 = paramArrayOfFloat[m + 1];
/*  716 */               f3 = paramArrayOfFloat[m + 2];
/*  717 */               f4 = paramArrayOfFloat[m + 3] * paramFloat;
/*      */             } else {
/*  719 */               f1 = paramArrayOfFloat[m];
/*  720 */               f2 = paramArrayOfFloat[m + 1];
/*  721 */               f3 = paramArrayOfFloat[m + 2];
/*  722 */               f4 = paramFloat;
/*      */             } 
/*  724 */             gL.glColor4f(f1, f2, f3, f4);
/*      */           }
/*  726 */           else if ((paramInt4 & 0x8) != 0) {
/*  727 */             gL.glColor4f(paramArrayOfFloat[m], paramArrayOfFloat[m + 1], paramArrayOfFloat[m + 2], paramArrayOfFloat[m + 3]);
/*      */           
/*      */           }
/*      */           else {
/*      */             
/*  732 */             gL.glColor3f(paramArrayOfFloat[m], paramArrayOfFloat[m + 1], paramArrayOfFloat[m + 2]);
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  739 */         if ((paramInt4 & 0x1000) != 0) {
/*  740 */           int i6 = i2;
/*  741 */           if (floatBuffer == null) {
/*  742 */             floatBuffer = FloatBuffer.wrap(paramArrayOfFloat);
/*      */           }
/*  744 */           for (byte b = 0; b < paramInt7; b++) {
/*  745 */             switch (paramArrayOfInt3[b]) {
/*      */               case 1:
/*  747 */                 floatBuffer.position(i6);
/*  748 */                 joglContext.vertexAttr1fv(gL, b, floatBuffer);
/*      */                 break;
/*      */               case 2:
/*  751 */                 floatBuffer.position(i6);
/*  752 */                 joglContext.vertexAttr2fv(gL, b, floatBuffer);
/*      */                 break;
/*      */               case 3:
/*  755 */                 floatBuffer.position(i6);
/*  756 */                 joglContext.vertexAttr3fv(gL, b, floatBuffer);
/*      */                 break;
/*      */               case 4:
/*  759 */                 floatBuffer.position(i6);
/*  760 */                 joglContext.vertexAttr4fv(gL, b, floatBuffer);
/*      */                 break;
/*      */             } 
/*      */             
/*  764 */             i6 += paramArrayOfInt3[b];
/*      */           } 
/*      */         } 
/*      */         
/*  768 */         if ((paramInt4 & 0x460) != 0 && 
/*  769 */           paramInt6 > 0) {
/*  770 */           if (gL.isExtensionAvailable("GL_VERSION_1_3")) {
/*  771 */             if ((paramInt4 & 0x20) != 0) {
/*  772 */               for (byte b = 0; b < paramInt6; b++) {
/*  773 */                 if (paramArrayOfInt2[b] != -1) {
/*  774 */                   int i6 = n + paramArrayOfInt2[b];
/*  775 */                   gL.glMultiTexCoord2f('蓀' + b, paramArrayOfFloat[i6], paramArrayOfFloat[i6 + 1]);
/*      */                 }
/*      */               
/*      */               }
/*      */             
/*  780 */             } else if ((paramInt4 & 0x40) != 0) {
/*  781 */               for (byte b = 0; b < paramInt6; b++) {
/*  782 */                 if (paramArrayOfInt2[b] != -1) {
/*  783 */                   int i6 = n + paramArrayOfInt2[b];
/*  784 */                   gL.glMultiTexCoord3f('蓀' + b, paramArrayOfFloat[i6], paramArrayOfFloat[i6 + 1], paramArrayOfFloat[i6 + 2]);
/*      */                 }
/*      */               
/*      */               }
/*      */             
/*      */             } else {
/*      */               
/*  791 */               for (byte b = 0; b < paramInt6; b++) {
/*  792 */                 if (paramArrayOfInt2[b] != -1) {
/*  793 */                   int i6 = n + paramArrayOfInt2[b];
/*  794 */                   gL.glMultiTexCoord4f('蓀' + b, paramArrayOfFloat[i6], paramArrayOfFloat[i6 + 1], paramArrayOfFloat[i6 + 2], paramArrayOfFloat[i6 + 3]);
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  803 */           else if (paramArrayOfInt2[0] != -1) {
/*  804 */             int i6 = n + paramArrayOfInt2[0];
/*  805 */             if ((paramInt4 & 0x20) != 0) {
/*  806 */               gL.glTexCoord2f(paramArrayOfFloat[i6], paramArrayOfFloat[i6 + 1]);
/*  807 */             } else if ((paramInt4 & 0x40) != 0) {
/*  808 */               gL.glTexCoord3f(paramArrayOfFloat[i6], paramArrayOfFloat[i6 + 1], paramArrayOfFloat[i6 + 2]);
/*      */             } else {
/*  810 */               gL.glTexCoord4f(paramArrayOfFloat[i6], paramArrayOfFloat[i6 + 1], paramArrayOfFloat[i6 + 2], paramArrayOfFloat[i6 + 3]);
/*      */             } 
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  819 */         if ((paramInt4 & true) != 0) {
/*  820 */           if (paramArrayOfDouble1 != null) {
/*      */             
/*  822 */             float f1 = (float)(paramArrayOfDouble1[12] * paramArrayOfFloat[j] + paramArrayOfDouble1[13] * paramArrayOfFloat[j + 1] + paramArrayOfDouble1[14] * paramArrayOfFloat[j + 2] + paramArrayOfDouble1[15]);
/*      */ 
/*      */ 
/*      */             
/*  826 */             float f2 = 1.0F / f1;
/*  827 */             float f3 = (float)(paramArrayOfDouble1[0] * paramArrayOfFloat[j] + paramArrayOfDouble1[1] * paramArrayOfFloat[j + 1] + paramArrayOfDouble1[2] * paramArrayOfFloat[j + 2] + paramArrayOfDouble1[3]) * f2;
/*      */ 
/*      */ 
/*      */             
/*  831 */             float f4 = (float)(paramArrayOfDouble1[4] * paramArrayOfFloat[j] + paramArrayOfDouble1[5] * paramArrayOfFloat[j + 1] + paramArrayOfDouble1[6] * paramArrayOfFloat[j + 2] + paramArrayOfDouble1[7]) * f2;
/*      */ 
/*      */ 
/*      */             
/*  835 */             float f5 = (float)(paramArrayOfDouble1[8] * paramArrayOfFloat[j] + paramArrayOfDouble1[9] * paramArrayOfFloat[j + 1] + paramArrayOfDouble1[10] * paramArrayOfFloat[j + 2] + paramArrayOfDouble1[11]) * f2;
/*      */ 
/*      */ 
/*      */             
/*  839 */             gL.glVertex3f(f3, f4, f5);
/*      */           } else {
/*  841 */             gL.glVertex3f(paramArrayOfFloat[j], paramArrayOfFloat[j + 1], paramArrayOfFloat[j + 2]);
/*      */           } 
/*      */         }
/*  844 */         k += i;
/*  845 */         m += i;
/*  846 */         j += i;
/*  847 */         n += i;
/*  848 */         i2 += i;
/*      */       } 
/*  850 */       gL.glEnd();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void buildGAForByRef(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, float[] paramArrayOfFloat1, double[] paramArrayOfDouble1, int paramInt6, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, int paramInt7, float[] paramArrayOfFloat3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, float[][] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt10, Object[] paramArrayOfObject, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3) {
/*  874 */     GL gL = context(paramContext).getGL();
/*      */     
/*  876 */     boolean bool1 = ((paramInt4 & true) != 0) ? 1 : 0;
/*  877 */     boolean bool2 = ((paramInt4 & 0x2) != 0) ? 1 : 0;
/*  878 */     boolean bool3 = ((paramInt4 & 0x4) != 0) ? 1 : 0;
/*  879 */     boolean bool4 = ((paramInt4 & 0x8) != 0) ? 1 : 0;
/*  880 */     boolean bool5 = ((paramInt4 & 0x10) != 0) ? 1 : 0;
/*  881 */     boolean bool6 = ((paramInt4 & 0x40) != 0) ? 1 : 0;
/*  882 */     boolean bool7 = ((paramInt4 & 0x20) != 0) ? 1 : 0;
/*      */     
/*  884 */     FloatBuffer floatBuffer1 = null;
/*  885 */     DoubleBuffer doubleBuffer = null;
/*  886 */     FloatBuffer floatBuffer2 = null;
/*  887 */     ByteBuffer byteBuffer = null;
/*  888 */     FloatBuffer[] arrayOfFloatBuffer1 = null;
/*  889 */     FloatBuffer floatBuffer3 = null;
/*  890 */     FloatBuffer[] arrayOfFloatBuffer2 = null;
/*      */ 
/*      */     
/*  893 */     if (bool6) {
/*  894 */       arrayOfFloatBuffer2 = getVertexAttrSetBuffer(paramArrayOfFloat);
/*      */     }
/*      */ 
/*      */     
/*  898 */     if (bool7) {
/*  899 */       arrayOfFloatBuffer1 = getTexCoordSetBuffer(paramArrayOfObject);
/*      */     }
/*      */ 
/*      */     
/*  903 */     boolean bool8 = false;
/*  904 */     if (paramBoolean2 && !paramBoolean3) {
/*  905 */       bool8 = true;
/*      */     }
/*      */     
/*  908 */     int[] arrayOfInt1 = null;
/*  909 */     int[] arrayOfInt2 = null;
/*  910 */     int i = 0;
/*  911 */     if (paramInt1 == 5 || paramInt1 == 6 || paramInt1 == 7) {
/*      */ 
/*      */       
/*  914 */       arrayOfInt1 = ((GeometryStripArrayRetained)paramGeometryArrayRetained).stripVertexCounts;
/*  915 */       i = arrayOfInt1.length;
/*  916 */       arrayOfInt2 = ((GeometryStripArrayRetained)paramGeometryArrayRetained).stripStartOffsetIndices;
/*      */     } 
/*      */     
/*  919 */     if (paramBoolean3) {
/*  920 */       paramInt3 &= 0xFFFFFFFB;
/*  921 */       bool3 = false;
/*  922 */       bool4 = false;
/*      */     } 
/*      */ 
/*      */     
/*  926 */     if (bool1) {
/*  927 */       gL.glEnableClientState(32884);
/*  928 */       floatBuffer1 = getVertexArrayBuffer(paramArrayOfFloat1, (paramArrayOfDouble2 == null));
/*  929 */       if (paramArrayOfDouble2 != null)
/*      */       {
/*  931 */         for (int j = paramInt5; j < paramInt2 * 3; j += 3) {
/*  932 */           floatBuffer1.put(j, (float)(paramArrayOfDouble2[0] * paramArrayOfFloat1[j] + paramArrayOfDouble2[1] * paramArrayOfFloat1[j + 1] + paramArrayOfDouble2[2] * paramArrayOfFloat1[j + 2]));
/*      */ 
/*      */           
/*  935 */           floatBuffer1.put(j + 1, (float)(paramArrayOfDouble2[4] * paramArrayOfFloat1[j] + paramArrayOfDouble2[5] * paramArrayOfFloat1[j + 1] + paramArrayOfDouble2[6] * paramArrayOfFloat1[j + 2]));
/*      */ 
/*      */           
/*  938 */           floatBuffer1.put(j + 2, (float)(paramArrayOfDouble2[8] * paramArrayOfFloat1[j] + paramArrayOfDouble2[9] * paramArrayOfFloat1[j + 1] + paramArrayOfDouble2[10] * paramArrayOfFloat1[j + 2]));
/*      */         }
/*      */       
/*      */       }
/*      */     }
/*  943 */     else if (bool2) {
/*  944 */       gL.glEnableClientState(32884);
/*  945 */       doubleBuffer = getVertexArrayBuffer(paramArrayOfDouble1, (paramArrayOfDouble2 == null));
/*  946 */       if (paramArrayOfDouble2 != null)
/*      */       {
/*  948 */         for (int j = paramInt5; j < paramInt2 * 3; j += 3) {
/*  949 */           doubleBuffer.put(j, paramArrayOfDouble2[0] * paramArrayOfDouble1[j] + paramArrayOfDouble2[1] * paramArrayOfDouble1[j + 1] + paramArrayOfDouble2[2] * paramArrayOfDouble1[j + 2]);
/*      */ 
/*      */           
/*  952 */           doubleBuffer.put(j + 1, paramArrayOfDouble2[4] * paramArrayOfDouble1[j] + paramArrayOfDouble2[5] * paramArrayOfDouble1[j + 1] + paramArrayOfDouble2[6] * paramArrayOfDouble1[j + 2]);
/*      */ 
/*      */           
/*  955 */           doubleBuffer.put(j + 2, paramArrayOfDouble2[8] * paramArrayOfDouble1[j] + paramArrayOfDouble2[9] * paramArrayOfDouble1[j + 1] + paramArrayOfDouble2[10] * paramArrayOfDouble1[j + 2]);
/*      */         }
/*      */       
/*      */       }
/*      */     } else {
/*      */       
/*  961 */       gL.glDisableClientState(32884);
/*      */     } 
/*      */ 
/*      */     
/*  965 */     if (bool3) {
/*  966 */       gL.glEnableClientState(32886);
/*  967 */       floatBuffer2 = getColorArrayBuffer(paramArrayOfFloat2, !bool8);
/*  968 */       if (bool8) {
/*      */         
/*  970 */         if ((paramInt3 & 0x8) != 0) {
/*  971 */           for (int j = paramInt6; j < paramInt2 * 4; j += 4) {
/*  972 */             floatBuffer2.put(j, paramArrayOfFloat2[j]);
/*  973 */             floatBuffer2.put(j + 1, paramArrayOfFloat2[j + 1]);
/*  974 */             floatBuffer2.put(j + 2, paramArrayOfFloat2[j + 2]);
/*  975 */             floatBuffer2.put(j + 3, paramFloat * paramArrayOfFloat2[j + 3]);
/*      */           } 
/*      */         } else {
/*  978 */           byte b = 0;
/*  979 */           for (int j = paramInt6; j < paramInt2 * 4; j += 4) {
/*  980 */             floatBuffer2.put(j, paramArrayOfFloat2[b++]);
/*  981 */             floatBuffer2.put(j + 1, paramArrayOfFloat2[b++]);
/*  982 */             floatBuffer2.put(j + 2, paramArrayOfFloat2[b++]);
/*  983 */             floatBuffer2.put(j + 3, paramFloat);
/*      */           } 
/*      */         } 
/*  986 */         paramInt3 |= 0x8;
/*      */       } 
/*  988 */     } else if (bool4) {
/*  989 */       gL.glEnableClientState(32886);
/*  990 */       byteBuffer = getColorArrayBuffer(paramArrayOfByte, !bool8);
/*  991 */       if (bool8) {
/*      */         
/*  993 */         if ((paramInt3 & 0x8) != 0) {
/*  994 */           for (int j = paramInt6; j < paramInt2 * 4; j += 4) {
/*  995 */             byteBuffer.put(j, paramArrayOfByte[j]);
/*  996 */             byteBuffer.put(j + 1, paramArrayOfByte[j + 1]);
/*  997 */             byteBuffer.put(j + 2, paramArrayOfByte[j + 2]);
/*  998 */             byteBuffer.put(j + 3, (byte)(int)(paramFloat * (paramArrayOfByte[j + 3] & 0xFF)));
/*      */           } 
/*      */         } else {
/* 1001 */           byte b = 0;
/* 1002 */           for (int j = paramInt6; j < paramInt2 * 4; j += 4) {
/* 1003 */             byteBuffer.put(j, paramArrayOfByte[b++]);
/* 1004 */             byteBuffer.put(j + 1, paramArrayOfByte[b++]);
/* 1005 */             byteBuffer.put(j + 2, paramArrayOfByte[b++]);
/* 1006 */             byteBuffer.put(j + 3, (byte)(int)(paramFloat * 255.0F));
/*      */           } 
/*      */         } 
/* 1009 */         paramInt3 |= 0x8;
/*      */       } 
/*      */     } else {
/* 1012 */       gL.glDisableClientState(32886);
/*      */     } 
/*      */ 
/*      */     
/* 1016 */     if (bool5) {
/* 1017 */       gL.glEnableClientState(32885);
/* 1018 */       floatBuffer3 = getNormalArrayBuffer(paramArrayOfFloat3, (paramArrayOfDouble3 == null));
/* 1019 */       if (paramArrayOfDouble3 != null)
/*      */       {
/* 1021 */         for (int j = paramInt7; j < paramInt2 * 3; j += 3) {
/* 1022 */           floatBuffer3.put(j, (float)(paramArrayOfDouble3[0] * paramArrayOfFloat3[j] + paramArrayOfDouble3[1] * paramArrayOfFloat3[j + 1] + paramArrayOfDouble3[2] * paramArrayOfFloat3[j + 2]));
/*      */ 
/*      */           
/* 1025 */           floatBuffer3.put(j + 1, (float)(paramArrayOfDouble3[4] * paramArrayOfFloat3[j] + paramArrayOfDouble3[5] * paramArrayOfFloat3[j + 1] + paramArrayOfDouble3[6] * paramArrayOfFloat3[j + 2]));
/*      */ 
/*      */           
/* 1028 */           floatBuffer3.put(j + 2, (float)(paramArrayOfDouble3[8] * paramArrayOfFloat3[j] + paramArrayOfDouble3[9] * paramArrayOfFloat3[j + 1] + paramArrayOfDouble3[10] * paramArrayOfFloat3[j + 2]));
/*      */         }
/*      */       
/*      */       }
/*      */     } else {
/*      */       
/* 1034 */       gL.glDisableClientState(32885);
/*      */     } 
/*      */     
/* 1037 */     executeGeometryArrayVA(paramContext, paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean3, paramInt2, paramInt3, paramInt4, paramInt5, floatBuffer1, doubleBuffer, paramInt6, floatBuffer2, byteBuffer, paramInt7, floatBuffer3, paramInt8, paramArrayOfInt1, paramArrayOfInt2, arrayOfFloatBuffer2, paramInt9, paramArrayOfInt3, paramInt9, paramArrayOfInt4, paramInt10, arrayOfFloatBuffer1, 0, arrayOfInt1, i, arrayOfInt2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void testForInterleavedArrays(int paramInt, boolean[] paramArrayOfBoolean, int[] paramArrayOfInt) {
/* 1060 */     paramArrayOfBoolean[0] = true;
/* 1061 */     switch (paramInt) {
/*      */       case 1:
/* 1063 */         paramArrayOfInt[0] = 10785; return;
/*      */       case 3:
/* 1065 */         paramArrayOfInt[0] = 10789; return;
/*      */       case 33:
/* 1067 */         paramArrayOfInt[0] = 10791; return;
/*      */       case 7:
/*      */       case 15:
/* 1070 */         paramArrayOfInt[0] = 10790; return;
/*      */       case 35:
/* 1072 */         paramArrayOfInt[0] = 10795; return;
/*      */       case 39:
/*      */       case 47:
/* 1075 */         paramArrayOfInt[0] = 10796; return;
/*      */     } 
/* 1077 */     paramArrayOfBoolean[0] = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void enableTexCoordPointer(GL paramGL, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Buffer paramBuffer) {
/* 1089 */     clientActiveTextureUnit(paramGL, paramInt1);
/* 1090 */     paramGL.glEnableClientState(32888);
/* 1091 */     paramGL.glTexCoordPointer(paramInt2, paramInt3, paramInt4, paramBuffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void disableTexCoordPointer(GL paramGL, int paramInt) {
/* 1098 */     clientActiveTextureUnit(paramGL, paramInt);
/* 1099 */     paramGL.glDisableClientState(32888);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void clientActiveTextureUnit(GL paramGL, int paramInt) {
/* 1106 */     if (paramGL.isExtensionAvailable("GL_VERSION_1_3")) {
/* 1107 */       paramGL.glClientActiveTexture(paramInt + 33984);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void executeTexture(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, int paramInt5, FloatBuffer paramFloatBuffer, GL paramGL) {
/* 1119 */     byte b1 = 0;
/*      */     
/* 1121 */     for (byte b2 = 0; b2 < paramInt5; b2++) {
/*      */       
/* 1123 */       b1 = b2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1130 */       if (b1 < paramInt1 && paramArrayOfInt[b1] != -1) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1135 */         paramFloatBuffer.position(paramInt4 + paramArrayOfInt[b1]);
/* 1136 */         enableTexCoordPointer(paramGL, b2, paramInt2, 5126, paramInt3, paramFloatBuffer);
/*      */       }
/*      */       else {
/*      */         
/* 1140 */         disableTexCoordPointer(paramGL, b2);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void resetTexture(GL paramGL, JoglContext paramJoglContext) {
/* 1149 */     for (byte b = 0; b < paramJoglContext.getMaxTexCoordSets(); b++) {
/* 1150 */       disableTexCoordPointer(paramGL, b);
/*      */     }
/*      */     
/* 1153 */     clientActiveTextureUnit(paramGL, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void executeGeometryArray(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int paramInt6, int[] paramArrayOfInt2, int paramInt7, int paramInt8, int[] paramArrayOfInt3, float[] paramArrayOfFloat1, Buffer paramBuffer, float[] paramArrayOfFloat2, int paramInt9) {
/* 1171 */     JoglContext joglContext = (JoglContext)paramContext;
/* 1172 */     GLContext gLContext = context(joglContext);
/* 1173 */     GL gL = gLContext.getGL();
/*      */ 
/*      */     
/* 1176 */     int i = 0;
/* 1177 */     byte b1 = 0;
/* 1178 */     int j = 0, k = 0, m = 0, n = 0, i1 = 0;
/* 1179 */     byte b2 = 0; int i2 = 0;
/* 1180 */     int i3 = 0;
/* 1181 */     int i4 = 0;
/* 1182 */     int i5 = 0, i6 = 0;
/* 1183 */     FloatBuffer floatBuffer1 = null;
/* 1184 */     FloatBuffer floatBuffer2 = null;
/* 1185 */     int[] arrayOfInt1 = null;
/* 1186 */     int[] arrayOfInt2 = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1198 */     if ((paramInt4 & true) != 0) {
/* 1199 */       j += true;
/*      */     }
/* 1201 */     if ((paramInt4 & 0x2) != 0) {
/* 1202 */       j += true;
/* 1203 */       k += true;
/*      */     } 
/* 1205 */     if ((paramInt4 & 0x4) != 0) {
/* 1206 */       if ((paramInt4 & 0x8) != 0) {
/* 1207 */         j += true;
/* 1208 */         m += true;
/* 1209 */         k += true;
/*      */       } else {
/* 1211 */         j += true;
/* 1212 */         m += true;
/* 1213 */         k += true;
/*      */       } 
/*      */     }
/* 1216 */     if ((paramInt4 & 0x460) != 0) {
/*      */ 
/*      */ 
/*      */       
/* 1220 */       if ((paramInt4 & 0x20) != 0) {
/* 1221 */         b2 = 2;
/* 1222 */         i2 = 2 * paramInt5;
/* 1223 */       } else if ((paramInt4 & 0x40) != 0) {
/* 1224 */         b2 = 3;
/* 1225 */         i2 = 3 * paramInt5;
/* 1226 */       } else if ((paramInt4 & 0x400) != 0) {
/* 1227 */         b2 = 4;
/* 1228 */         i2 = 4 * paramInt5;
/*      */       } 
/* 1230 */       j += i2;
/* 1231 */       m += i2;
/* 1232 */       n += i2;
/* 1233 */       k += i2;
/*      */     } 
/* 1235 */     if ((paramInt4 & 0x1000) != 0) {
/* 1236 */       for (byte b = 0; b < paramInt8; b++) {
/* 1237 */         i4 += paramArrayOfInt3[b];
/*      */       }
/* 1239 */       j += i4;
/* 1240 */       m += i4;
/* 1241 */       n += i4;
/* 1242 */       k += i4;
/* 1243 */       i1 += i4;
/*      */     } 
/*      */     
/* 1246 */     i5 = j * 4;
/*      */     
/* 1248 */     if (paramInt1 == 5 || paramInt1 == 6 || paramInt1 == 7) {
/*      */ 
/*      */       
/* 1251 */       arrayOfInt1 = ((GeometryStripArrayRetained)paramGeometryArrayRetained).stripVertexCounts;
/* 1252 */       arrayOfInt2 = ((GeometryStripArrayRetained)paramGeometryArrayRetained).stripStartOffsetIndices;
/*      */     } 
/*      */ 
/*      */     
/* 1256 */     if (paramArrayOfFloat1 != null) {
/* 1257 */       floatBuffer1 = getVertexArrayBuffer(paramArrayOfFloat1);
/* 1258 */     } else if (paramBuffer != null) {
/* 1259 */       floatBuffer1 = (FloatBuffer)paramBuffer;
/*      */     } else {
/*      */       
/* 1262 */       throw new AssertionError("Unable to get vertex pointer");
/*      */     } 
/*      */ 
/*      */     
/* 1266 */     int i7 = j;
/* 1267 */     if (paramArrayOfFloat2 != null) {
/* 1268 */       floatBuffer2 = getColorArrayBuffer(paramArrayOfFloat2);
/* 1269 */       i7 = 4;
/*      */     } else {
/*      */       
/* 1272 */       floatBuffer2 = floatBuffer1;
/*      */     } 
/*      */     
/* 1275 */     i6 = i7 * 4;
/*      */ 
/*      */     
/* 1278 */     if (paramBoolean1) {
/* 1279 */       gL.glEnable(2977);
/*      */     }
/*      */     
/* 1282 */     int i8 = j * paramInt2;
/* 1283 */     int i9 = i7 * paramInt2;
/* 1284 */     if (floatBuffer2 == floatBuffer1) {
/* 1285 */       i9 += n;
/*      */     }
/*      */ 
/*      */     
/* 1289 */     if (paramInt1 == 5 || paramInt1 == 6 || paramInt1 == 7) {
/*      */       boolean bool;
/*      */       
/* 1292 */       if (paramBoolean3 || paramArrayOfFloat2 != null || ((paramInt4 & 0x460) != 0 && (paramInt6 > 1 || paramInt5 > 1))) {
/*      */ 
/*      */         
/* 1295 */         bool = false;
/*      */       } else {
/* 1297 */         boolean[] arrayOfBoolean = new boolean[1];
/* 1298 */         int[] arrayOfInt = new int[1];
/* 1299 */         testForInterleavedArrays(paramInt4, arrayOfBoolean, arrayOfInt);
/* 1300 */         bool = arrayOfBoolean[0];
/* 1301 */         i = arrayOfInt[0];
/*      */       } 
/* 1303 */       if (bool) {
/* 1304 */         floatBuffer1.position(i8);
/* 1305 */         gL.glInterleavedArrays(i, i5, floatBuffer1);
/*      */       } else {
/* 1307 */         if ((paramInt4 & 0x2) != 0) {
/* 1308 */           floatBuffer1.position(i8 + m);
/* 1309 */           gL.glNormalPointer(5126, i5, floatBuffer1);
/*      */         } 
/* 1311 */         if (!paramBoolean3 && (paramInt4 & 0x4) != 0) {
/*      */ 
/*      */ 
/*      */           
/* 1315 */           floatBuffer2.position(i9);
/* 1316 */           if ((paramInt4 & 0x8) != 0 || paramBoolean2) {
/* 1317 */             gL.glColorPointer(4, 5126, i6, floatBuffer2);
/*      */           } else {
/* 1319 */             gL.glColorPointer(3, 5126, i6, floatBuffer2);
/*      */           } 
/*      */         } 
/* 1322 */         if ((paramInt4 & true) != 0) {
/* 1323 */           floatBuffer1.position(i8 + k);
/* 1324 */           gL.glVertexPointer(3, 5126, i5, floatBuffer1);
/*      */         } 
/*      */         
/* 1327 */         if ((paramInt4 & 0x460) != 0) {
/* 1328 */           executeTexture(paramInt6, b2, i5, i1, paramArrayOfInt2, paramInt7, floatBuffer1, gL);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1335 */         if ((paramInt4 & 0x1000) != 0) {
/* 1336 */           int i10 = i8 + i3;
/* 1337 */           for (byte b = 0; b < paramInt8; b++) {
/* 1338 */             joglContext.enableVertexAttrArray(gL, b);
/* 1339 */             floatBuffer1.position(i10);
/* 1340 */             joglContext.vertexAttrPointer(gL, b, paramArrayOfInt3[b], 5126, i5, floatBuffer1);
/*      */             
/* 1342 */             i10 += paramArrayOfInt3[b];
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1347 */       switch (paramInt1) {
/*      */         case 5:
/* 1349 */           b1 = 5;
/*      */           break;
/*      */         case 6:
/* 1352 */           b1 = 6;
/*      */           break;
/*      */         case 7:
/* 1355 */           b1 = 3;
/*      */           break;
/*      */       } 
/*      */       
/* 1359 */       if (gL.isExtensionAvailable("GL_EXT_multi_draw_arrays")) {
/* 1360 */         gL.glMultiDrawArraysEXT(b1, arrayOfInt2, 0, arrayOfInt1, 0, arrayOfInt1.length);
/*      */       } else {
/* 1362 */         for (byte b = 0; b < arrayOfInt1.length; b++) {
/* 1363 */           gL.glDrawArrays(b1, arrayOfInt2[b], arrayOfInt1[b]);
/*      */         }
/*      */       } 
/* 1366 */     } else if (paramInt1 == 1 || paramInt1 == 2 || paramInt1 == 3 || paramInt1 == 4) {
/*      */       boolean bool;
/*      */ 
/*      */ 
/*      */       
/* 1371 */       if (paramBoolean3 || paramArrayOfFloat2 != null || ((paramInt4 & 0x460) != 0 && (paramInt6 > 1 || paramInt5 > 1))) {
/*      */ 
/*      */         
/* 1374 */         bool = false;
/*      */       } else {
/* 1376 */         boolean[] arrayOfBoolean = new boolean[1];
/* 1377 */         int[] arrayOfInt = new int[1];
/* 1378 */         testForInterleavedArrays(paramInt4, arrayOfBoolean, arrayOfInt);
/* 1379 */         bool = arrayOfBoolean[0];
/* 1380 */         i = arrayOfInt[0];
/*      */       } 
/*      */       
/* 1383 */       if (bool) {
/* 1384 */         floatBuffer1.position(i8);
/* 1385 */         gL.glInterleavedArrays(i, i5, floatBuffer1);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1396 */         if ((paramInt4 & 0x2) != 0) {
/* 1397 */           floatBuffer1.position(i8 + m);
/* 1398 */           gL.glNormalPointer(5126, i5, floatBuffer1);
/*      */         } 
/* 1400 */         if (!paramBoolean3 && (paramInt4 & 0x4) != 0) {
/* 1401 */           floatBuffer2.position(i9);
/* 1402 */           if ((paramInt4 & 0x8) != 0 || paramBoolean2) {
/* 1403 */             gL.glColorPointer(4, 5126, i6, floatBuffer2);
/*      */           } else {
/* 1405 */             gL.glColorPointer(3, 5126, i6, floatBuffer2);
/*      */           } 
/*      */         } 
/* 1408 */         if ((paramInt4 & true) != 0) {
/* 1409 */           floatBuffer1.position(i8 + k);
/* 1410 */           gL.glVertexPointer(3, 5126, i5, floatBuffer1);
/*      */         } 
/*      */         
/* 1413 */         if ((paramInt4 & 0x460) != 0) {
/* 1414 */           executeTexture(paramInt6, b2, i5, i1, paramArrayOfInt2, paramInt7, floatBuffer1, gL);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1421 */         if ((paramInt4 & 0x1000) != 0) {
/* 1422 */           int i10 = i8 + i3;
/* 1423 */           for (byte b = 0; b < paramInt8; b++) {
/* 1424 */             joglContext.enableVertexAttrArray(gL, b);
/* 1425 */             floatBuffer1.position(i10);
/* 1426 */             joglContext.vertexAttrPointer(gL, b, paramArrayOfInt3[b], 5126, i5, floatBuffer1);
/*      */             
/* 1428 */             i10 += paramArrayOfInt3[b];
/*      */           } 
/*      */         } 
/*      */       } 
/* 1432 */       switch (paramInt1) { case 1:
/* 1433 */           gL.glDrawArrays(7, 0, paramInt3); break;
/* 1434 */         case 2: gL.glDrawArrays(4, 0, paramInt3); break;
/* 1435 */         case 3: gL.glDrawArrays(0, 0, paramInt3); break;
/* 1436 */         case 4: gL.glDrawArrays(1, 0, paramInt3);
/*      */           break; }
/*      */ 
/*      */     
/*      */     } 
/* 1441 */     if (paramBoolean1) {
/* 1442 */       gL.glDisable(2977);
/*      */     }
/*      */     
/* 1445 */     if ((paramInt4 & 0x1000) != 0) {
/* 1446 */       resetVertexAttrs(gL, joglContext, paramInt8);
/*      */     }
/*      */     
/* 1449 */     if ((paramInt4 & 0x460) != 0) {
/* 1450 */       resetTexture(gL, joglContext);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void lockArray(GL paramGL, int paramInt) {
/* 1458 */     if (paramGL.isExtensionAvailable("GL_EXT_compiled_vertex_array")) {
/* 1459 */       paramGL.glLockArraysEXT(0, paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */   private void unlockArray(GL paramGL) {
/* 1464 */     if (paramGL.isExtensionAvailable("GL_EXT_compiled_vertex_array")) {
/* 1465 */       paramGL.glUnlockArraysEXT();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void executeGeometryArrayVA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, FloatBuffer paramFloatBuffer1, DoubleBuffer paramDoubleBuffer, int paramInt6, FloatBuffer paramFloatBuffer2, ByteBuffer paramByteBuffer, int paramInt7, FloatBuffer paramFloatBuffer3, int paramInt8, int[] paramArrayOfInt1, int[] paramArrayOfInt2, FloatBuffer[] paramArrayOfFloatBuffer1, int paramInt9, int[] paramArrayOfInt3, int paramInt10, int[] paramArrayOfInt4, int paramInt11, FloatBuffer[] paramArrayOfFloatBuffer2, int paramInt12, int[] paramArrayOfInt5, int paramInt13, int[] paramArrayOfInt6) {
/* 1491 */     JoglContext joglContext = (JoglContext)paramContext;
/* 1492 */     GLContext gLContext = context(joglContext);
/* 1493 */     GL gL = gLContext.getGL();
/*      */     
/* 1495 */     boolean bool1 = ((paramInt4 & true) != 0) ? 1 : 0;
/* 1496 */     boolean bool2 = ((paramInt4 & 0x2) != 0) ? 1 : 0;
/* 1497 */     boolean bool3 = ((paramInt4 & 0x4) != 0) ? 1 : 0;
/* 1498 */     boolean bool4 = ((paramInt4 & 0x8) != 0) ? 1 : 0;
/* 1499 */     boolean bool5 = ((paramInt4 & 0x10) != 0) ? 1 : 0;
/* 1500 */     boolean bool6 = ((paramInt4 & 0x40) != 0) ? 1 : 0;
/* 1501 */     boolean bool7 = ((paramInt4 & 0x20) != 0) ? 1 : 0;
/*      */ 
/*      */     
/* 1504 */     if (paramBoolean1) {
/* 1505 */       gL.glEnable(2977);
/*      */     }
/*      */     
/* 1508 */     int i = 3 * paramInt5;
/*      */     
/* 1510 */     if (bool1) {
/* 1511 */       paramFloatBuffer1.position(i);
/* 1512 */       gL.glVertexPointer(3, 5126, 0, paramFloatBuffer1);
/* 1513 */     } else if (bool2) {
/* 1514 */       paramDoubleBuffer.position(i);
/* 1515 */       gL.glVertexPointer(3, 5130, 0, paramDoubleBuffer);
/*      */     } 
/*      */     
/* 1518 */     if (bool3) {
/*      */       byte b;
/*      */       int j;
/* 1521 */       if ((paramInt3 & 0x8) != 0) {
/* 1522 */         j = 4 * paramInt6;
/* 1523 */         b = 4;
/*      */       } else {
/* 1525 */         j = 3 * paramInt6;
/* 1526 */         b = 3;
/*      */       } 
/* 1528 */       paramFloatBuffer2.position(j);
/* 1529 */       gL.glColorPointer(b, 5126, 0, paramFloatBuffer2);
/* 1530 */     } else if (bool4) {
/*      */       byte b;
/*      */       int j;
/* 1533 */       if ((paramInt3 & 0x8) != 0) {
/* 1534 */         j = 4 * paramInt6;
/* 1535 */         b = 4;
/*      */       } else {
/* 1537 */         j = 3 * paramInt6;
/* 1538 */         b = 3;
/*      */       } 
/* 1540 */       paramByteBuffer.position(j);
/* 1541 */       gL.glColorPointer(b, 5121, 0, paramByteBuffer);
/*      */     } 
/* 1543 */     if (bool5) {
/* 1544 */       int j = 3 * paramInt7;
/* 1545 */       paramFloatBuffer3.position(j);
/* 1546 */       gL.glNormalPointer(5126, 0, paramFloatBuffer3);
/*      */     } 
/*      */     
/* 1549 */     if (bool6) {
/* 1550 */       for (byte b = 0; b < paramInt8; b++) {
/* 1551 */         FloatBuffer floatBuffer = paramArrayOfFloatBuffer1[b];
/* 1552 */         int j = paramArrayOfInt1[b];
/* 1553 */         int k = paramArrayOfInt2[b];
/* 1554 */         joglContext.enableVertexAttrArray(gL, b);
/* 1555 */         floatBuffer.position(k * j);
/* 1556 */         joglContext.vertexAttrPointer(gL, b, j, 5126, 0, floatBuffer);
/*      */       } 
/*      */     }
/*      */     
/* 1560 */     if (bool7) {
/* 1561 */       int j = 0;
/* 1562 */       for (byte b = 0; b < paramInt10; b++) {
/* 1563 */         if (b < paramInt9 && (j = paramArrayOfInt3[b]) != -1) {
/*      */           
/* 1565 */           FloatBuffer floatBuffer = paramArrayOfFloatBuffer2[j];
/* 1566 */           floatBuffer.position(paramInt11 * paramArrayOfInt4[j]);
/* 1567 */           enableTexCoordPointer(gL, b, paramInt11, 5126, 0, floatBuffer);
/*      */         } else {
/*      */           
/* 1570 */           disableTexCoordPointer(gL, b);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1575 */       clientActiveTextureUnit(gL, 0);
/*      */     } 
/*      */     
/* 1578 */     if (paramInt1 == 5 || paramInt1 == 6 || paramInt1 == 7) {
/*      */ 
/*      */       
/* 1581 */       byte b = 0;
/* 1582 */       switch (paramInt1) {
/*      */         case 5:
/* 1584 */           b = 5;
/*      */           break;
/*      */         case 6:
/* 1587 */           b = 6;
/*      */           break;
/*      */         case 7:
/* 1590 */           b = 3;
/*      */           break;
/*      */       } 
/* 1593 */       if (gL.isExtensionAvailable("GL_EXT_multi_draw_arrays")) {
/* 1594 */         gL.glMultiDrawArraysEXT(b, paramArrayOfInt6, 0, paramArrayOfInt5, 0, paramInt13);
/* 1595 */       } else if (gL.isExtensionAvailable("GL_VERSION_1_4")) {
/* 1596 */         gL.glMultiDrawArrays(b, paramArrayOfInt6, 0, paramArrayOfInt5, 0, paramInt13);
/*      */       } else {
/* 1598 */         for (byte b1 = 0; b1 < paramInt13; b1++) {
/* 1599 */           gL.glDrawArrays(b, paramArrayOfInt6[b1], paramArrayOfInt5[b1]);
/*      */         }
/*      */       } 
/*      */     } else {
/* 1603 */       switch (paramInt1) { case 1:
/* 1604 */           gL.glDrawArrays(7, 0, paramInt2); break;
/* 1605 */         case 2: gL.glDrawArrays(4, 0, paramInt2); break;
/* 1606 */         case 3: gL.glDrawArrays(0, 0, paramInt2); break;
/* 1607 */         case 4: gL.glDrawArrays(1, 0, paramInt2);
/*      */           break; }
/*      */ 
/*      */     
/*      */     } 
/* 1612 */     if (paramBoolean1) {
/* 1613 */       gL.glDisable(2977);
/*      */     }
/*      */     
/* 1616 */     if (bool6) {
/* 1617 */       resetVertexAttrs(gL, joglContext, paramInt8);
/*      */     }
/*      */     
/* 1620 */     if (bool7) {
/* 1621 */       resetTexture(gL, joglContext);
/*      */     }
/*      */   }
/*      */   
/*      */   private String getVertexDescription(int paramInt) {
/* 1626 */     String str = "";
/* 1627 */     if ((paramInt & true) != 0) str = str + "COORDINATES "; 
/* 1628 */     if ((paramInt & 0x2) != 0) str = str + "NORMALS "; 
/* 1629 */     if ((paramInt & 0x4) != 0) str = str + "COLOR "; 
/* 1630 */     if ((paramInt & 0x8) != 0) str = str + "(WITH_ALPHA) "; 
/* 1631 */     if ((paramInt & 0x460) != 0) str = str + "TEXTURE_COORDINATE "; 
/* 1632 */     if ((paramInt & 0x20) != 0) str = str + "(2) "; 
/* 1633 */     if ((paramInt & 0x40) != 0) str = str + "(3) "; 
/* 1634 */     if ((paramInt & 0x400) != 0) str = str + "(4) "; 
/* 1635 */     if ((paramInt & 0x1000) != 0) str = str + "VERTEX_ATTRIBUTES "; 
/* 1636 */     return str;
/*      */   }
/*      */   
/*      */   private String getGeometryDescription(int paramInt) {
/* 1640 */     switch (paramInt) { case 5:
/* 1641 */         return "GEO_TYPE_TRI_STRIP_SET";
/* 1642 */       case 6: return "GEO_TYPE_TRI_FAN_SET";
/* 1643 */       case 7: return "GEO_TYPE_LINE_STRIP_SET";
/* 1644 */       case 1: return "GEO_TYPE_QUAD_SET";
/* 1645 */       case 2: return "GEO_TYPE_TRI_SET";
/* 1646 */       case 3: return "GEO_TYPE_POINT_SET";
/* 1647 */       case 4: return "GEO_TYPE_LINE_SET"; }
/* 1648 */      return "(unknown " + paramInt + ")";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void resetVertexAttrs(GL paramGL, JoglContext paramJoglContext, int paramInt) {
/* 1654 */     for (byte b = 0; b < paramInt; b++) {
/* 1655 */       paramJoglContext.disableVertexAttrArray(paramGL, b);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1685 */   void executeIndexedGeometry(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, int[] paramArrayOfInt3, int paramInt9, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt10, int[] paramArrayOfInt4) { executeIndexedGeometryArray(paramContext, paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramBoolean3, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramArrayOfInt1, paramInt7, paramArrayOfInt2, paramInt8, paramArrayOfInt3, paramInt9, paramArrayOfFloat1, null, paramArrayOfFloat2, paramInt10, paramArrayOfInt4); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1715 */   void executeIndexedGeometryBuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, Object paramObject, float[] paramArrayOfFloat, int paramInt9, int[] paramArrayOfInt3) { executeIndexedGeometryArray(paramContext, paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramBoolean3, paramInt2, paramInt3, paramInt4, paramInt5, 0, null, paramInt6, paramArrayOfInt1, paramInt7, paramArrayOfInt2, paramInt8, null, (FloatBuffer)paramObject, paramArrayOfFloat, paramInt9, paramArrayOfInt3); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void executeIndexedGeometryVA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float[] paramArrayOfFloat1, double[] paramArrayOfDouble, float[] paramArrayOfFloat2, byte[] paramArrayOfByte, float[] paramArrayOfFloat3, int paramInt7, int[] paramArrayOfInt1, float[][] paramArrayOfFloat, int paramInt8, int[] paramArrayOfInt2, int paramInt9, int paramInt10, Object[] paramArrayOfObject, int paramInt11, int[] paramArrayOfInt3) {
/* 1748 */     boolean bool1 = ((paramInt6 & true) != 0) ? 1 : 0;
/* 1749 */     boolean bool2 = ((paramInt6 & 0x2) != 0) ? 1 : 0;
/* 1750 */     boolean bool3 = ((paramInt6 & 0x4) != 0) ? 1 : 0;
/* 1751 */     boolean bool4 = ((paramInt6 & 0x8) != 0) ? 1 : 0;
/* 1752 */     boolean bool5 = ((paramInt6 & 0x10) != 0) ? 1 : 0;
/* 1753 */     boolean bool6 = ((paramInt6 & 0x40) != 0) ? 1 : 0;
/* 1754 */     boolean bool7 = ((paramInt6 & 0x20) != 0) ? 1 : 0;
/*      */     
/* 1756 */     FloatBuffer floatBuffer1 = null;
/* 1757 */     DoubleBuffer doubleBuffer = null;
/* 1758 */     FloatBuffer floatBuffer2 = null;
/* 1759 */     ByteBuffer byteBuffer = null;
/* 1760 */     FloatBuffer[] arrayOfFloatBuffer1 = null;
/* 1761 */     FloatBuffer floatBuffer3 = null;
/* 1762 */     FloatBuffer[] arrayOfFloatBuffer2 = null;
/*      */ 
/*      */     
/* 1765 */     if (bool6) {
/* 1766 */       arrayOfFloatBuffer2 = getVertexAttrSetBuffer(paramArrayOfFloat);
/*      */     }
/*      */ 
/*      */     
/* 1770 */     if (bool7) {
/* 1771 */       arrayOfFloatBuffer1 = getTexCoordSetBuffer(paramArrayOfObject);
/*      */     }
/*      */     
/* 1774 */     int[] arrayOfInt = null;
/* 1775 */     int i = 0;
/* 1776 */     if (paramInt1 == 12 || paramInt1 == 13 || paramInt1 == 14) {
/*      */ 
/*      */       
/* 1779 */       arrayOfInt = ((IndexedGeometryStripArrayRetained)paramGeometryArrayRetained).stripIndexCounts;
/* 1780 */       i = arrayOfInt.length;
/*      */     } 
/*      */ 
/*      */     
/* 1784 */     if (bool1) {
/* 1785 */       floatBuffer1 = getVertexArrayBuffer(paramArrayOfFloat1);
/* 1786 */     } else if (bool2) {
/* 1787 */       doubleBuffer = getVertexArrayBuffer(paramArrayOfDouble);
/*      */     } 
/*      */ 
/*      */     
/* 1791 */     if (bool3) {
/* 1792 */       floatBuffer2 = getColorArrayBuffer(paramArrayOfFloat2);
/* 1793 */     } else if (bool4) {
/* 1794 */       byteBuffer = getColorArrayBuffer(paramArrayOfByte);
/*      */     } 
/*      */ 
/*      */     
/* 1798 */     if (bool5) {
/* 1799 */       floatBuffer3 = getNormalArrayBuffer(paramArrayOfFloat3);
/*      */     }
/*      */     
/* 1802 */     executeIndexedGeometryArrayVA(paramContext, paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, floatBuffer1, doubleBuffer, floatBuffer2, byteBuffer, floatBuffer3, paramInt7, paramArrayOfInt1, arrayOfFloatBuffer2, paramInt8, paramArrayOfInt2, paramInt9, paramInt10, arrayOfFloatBuffer1, paramInt11, paramArrayOfInt3, arrayOfInt, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void executeIndexedGeometryVABuffer(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Object paramObject1, Object paramObject2, float[] paramArrayOfFloat, byte[] paramArrayOfByte, Object paramObject3, int paramInt7, int[] paramArrayOfInt1, Object[] paramArrayOfObject1, int paramInt8, int[] paramArrayOfInt2, int paramInt9, int paramInt10, Object[] paramArrayOfObject2, int paramInt11, int[] paramArrayOfInt3) {
/* 1841 */     boolean bool1 = ((paramInt6 & true) != 0) ? 1 : 0;
/* 1842 */     boolean bool2 = ((paramInt6 & 0x2) != 0) ? 1 : 0;
/* 1843 */     boolean bool3 = ((paramInt6 & 0x4) != 0) ? 1 : 0;
/* 1844 */     boolean bool4 = ((paramInt6 & 0x8) != 0) ? 1 : 0;
/* 1845 */     boolean bool5 = ((paramInt6 & 0x10) != 0) ? 1 : 0;
/* 1846 */     boolean bool6 = ((paramInt6 & 0x40) != 0) ? 1 : 0;
/* 1847 */     boolean bool7 = ((paramInt6 & 0x20) != 0) ? 1 : 0;
/*      */     
/* 1849 */     FloatBuffer floatBuffer1 = null;
/* 1850 */     DoubleBuffer doubleBuffer = null;
/* 1851 */     FloatBuffer floatBuffer2 = null;
/* 1852 */     ByteBuffer byteBuffer = null;
/* 1853 */     FloatBuffer[] arrayOfFloatBuffer1 = null;
/* 1854 */     FloatBuffer floatBuffer3 = null;
/* 1855 */     FloatBuffer[] arrayOfFloatBuffer2 = null;
/*      */ 
/*      */     
/* 1858 */     if (bool6) {
/* 1859 */       arrayOfFloatBuffer2 = getVertexAttrSetBuffer(paramArrayOfObject1);
/*      */     }
/*      */ 
/*      */     
/* 1863 */     if (bool7) {
/* 1864 */       arrayOfFloatBuffer1 = new FloatBuffer[paramArrayOfObject2.length];
/* 1865 */       for (byte b = 0; b < paramArrayOfObject2.length; b++) {
/* 1866 */         arrayOfFloatBuffer1[b] = (FloatBuffer)paramArrayOfObject2[b];
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1871 */     if (bool1) {
/* 1872 */       floatBuffer1 = (FloatBuffer)paramObject1;
/* 1873 */     } else if (bool2) {
/* 1874 */       doubleBuffer = (DoubleBuffer)paramObject1;
/*      */     } 
/*      */     
/* 1877 */     if (floatBuffer1 == null && doubleBuffer == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1881 */     int[] arrayOfInt = null;
/* 1882 */     int i = 0;
/* 1883 */     if (paramInt1 == 12 || paramInt1 == 13 || paramInt1 == 14) {
/*      */ 
/*      */       
/* 1886 */       arrayOfInt = ((IndexedGeometryStripArrayRetained)paramGeometryArrayRetained).stripIndexCounts;
/* 1887 */       i = arrayOfInt.length;
/*      */     } 
/*      */ 
/*      */     
/* 1891 */     if (bool3) {
/* 1892 */       if (paramArrayOfFloat != null)
/* 1893 */       { floatBuffer2 = getColorArrayBuffer(paramArrayOfFloat); }
/*      */       else
/* 1895 */       { floatBuffer2 = (FloatBuffer)paramObject2; } 
/* 1896 */     } else if (bool4) {
/* 1897 */       if (paramArrayOfByte != null) {
/* 1898 */         byteBuffer = getColorArrayBuffer(paramArrayOfByte);
/*      */       } else {
/* 1900 */         byteBuffer = (ByteBuffer)paramObject2;
/*      */       } 
/*      */     } 
/*      */     
/* 1904 */     if (bool5) {
/* 1905 */       floatBuffer3 = (FloatBuffer)paramObject3;
/*      */     }
/*      */     
/* 1908 */     executeIndexedGeometryArrayVA(paramContext, paramGeometryArrayRetained, paramInt1, paramBoolean1, paramBoolean2, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, floatBuffer1, doubleBuffer, floatBuffer2, byteBuffer, floatBuffer3, paramInt7, paramArrayOfInt1, arrayOfFloatBuffer2, paramInt8, paramArrayOfInt2, paramInt9, paramInt10, arrayOfFloatBuffer1, paramInt11, paramArrayOfInt3, arrayOfInt, i);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void buildIndexedGeometry(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, float paramFloat, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, int[] paramArrayOfInt3, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, float[] paramArrayOfFloat, int[] paramArrayOfInt4) {
/* 1941 */     JoglContext joglContext = (JoglContext)paramContext;
/* 1942 */     GL gL = context(joglContext).getGL();
/*      */ 
/*      */     
/* 1945 */     int i = 0;
/* 1946 */     byte b1 = 0;
/* 1947 */     int j = 0, k = 0, m = 0, n = 0, i1 = 0;
/* 1948 */     byte b2 = 0; int i2 = 0;
/* 1949 */     boolean bool1 = false;
/* 1950 */     int i3 = 0;
/* 1951 */     int i4 = 0; boolean bool2 = false;
/* 1952 */     FloatBuffer floatBuffer = null;
/* 1953 */     Object object = null;
/* 1954 */     int[] arrayOfInt = null;
/* 1955 */     int i5 = 0;
/* 1956 */     boolean bool3 = false;
/*      */     
/* 1958 */     if ((paramInt5 & true) != 0) {
/* 1959 */       gL.glEnableClientState(32884);
/* 1960 */       j += true;
/*      */     } else {
/* 1962 */       gL.glDisableClientState(32884);
/*      */     } 
/*      */     
/* 1965 */     if ((paramInt5 & 0x2) != 0) {
/* 1966 */       gL.glEnableClientState(32885);
/* 1967 */       j += true;
/* 1968 */       k += true;
/*      */     } else {
/* 1970 */       gL.glDisableClientState(32885);
/*      */     } 
/*      */     
/* 1973 */     if ((paramInt5 & 0x4) != 0) {
/* 1974 */       gL.glEnableClientState(32886);
/* 1975 */       j += true;
/* 1976 */       m += true;
/* 1977 */       k += true;
/*      */     } else {
/* 1979 */       gL.glDisableClientState(32886);
/*      */     } 
/*      */     
/* 1982 */     if ((paramInt5 & 0x460) != 0) {
/* 1983 */       if ((paramInt5 & 0x20) != 0) {
/* 1984 */         b2 = 2;
/* 1985 */         i2 = 2 * paramInt7;
/* 1986 */       } else if ((paramInt5 & 0x40) != 0) {
/* 1987 */         b2 = 3;
/* 1988 */         i2 = 3 * paramInt7;
/* 1989 */       } else if ((paramInt5 & 0x400) != 0) {
/* 1990 */         b2 = 4;
/* 1991 */         i2 = 4 * paramInt7;
/*      */       } 
/* 1993 */       j += i2;
/* 1994 */       m += i2;
/* 1995 */       n += i2;
/* 1996 */       k += i2;
/*      */     } 
/*      */     
/* 1999 */     if ((paramInt5 & 0x1000) != 0) {
/* 2000 */       for (byte b = 0; b < paramInt6; b++) {
/* 2001 */         i3 += paramArrayOfInt1[b];
/*      */       }
/* 2003 */       j += i3;
/* 2004 */       m += i3;
/* 2005 */       n += i3;
/* 2006 */       k += i3;
/* 2007 */       i1 += i3;
/*      */     } 
/*      */     
/* 2010 */     i4 = j * 4;
/*      */ 
/*      */     
/* 2013 */     if (paramBoolean2 && !paramBoolean3) {
/* 2014 */       bool3 = true;
/*      */     }
/*      */     
/* 2017 */     if (paramInt1 == 12 || paramInt1 == 13 || paramInt1 == 14) {
/*      */ 
/*      */       
/* 2020 */       arrayOfInt = ((IndexedGeometryStripArrayRetained)paramGeometryArrayRetained).stripIndexCounts;
/* 2021 */       i5 = arrayOfInt.length;
/*      */     } 
/*      */ 
/*      */     
/* 2025 */     floatBuffer = getVertexArrayBuffer(paramArrayOfFloat);
/*      */ 
/*      */     
/* 2028 */     if ((paramInt5 & 0x2) != 0 && paramArrayOfDouble2 != null) {
/* 2029 */       int i6 = m;
/* 2030 */       for (boolean bool = false; bool < paramInt4 * 3; bool += true) {
/* 2031 */         floatBuffer.put(i6, (float)(paramArrayOfDouble2[0] * paramArrayOfFloat[i6] + paramArrayOfDouble2[1] * paramArrayOfFloat[i6 + 1] + paramArrayOfDouble2[2] * paramArrayOfFloat[i6 + 2]));
/*      */ 
/*      */         
/* 2034 */         floatBuffer.put(i6 + 1, (float)(paramArrayOfDouble2[4] * paramArrayOfFloat[i6] + paramArrayOfDouble2[5] * paramArrayOfFloat[i6 + 1] + paramArrayOfDouble2[6] * paramArrayOfFloat[i6 + 2]));
/*      */ 
/*      */         
/* 2037 */         floatBuffer.put(i6 + 2, (float)(paramArrayOfDouble2[8] * paramArrayOfFloat[i6] + paramArrayOfDouble2[9] * paramArrayOfFloat[i6 + 1] + paramArrayOfDouble2[10] * paramArrayOfFloat[i6 + 2]));
/*      */ 
/*      */         
/* 2040 */         i6 += j;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2045 */     if ((paramInt5 & true) != 0 && paramArrayOfDouble1 != null) {
/* 2046 */       int i6 = k;
/* 2047 */       for (boolean bool = false; bool < paramInt4 * 3; bool += true) {
/* 2048 */         floatBuffer.put(i6, (float)(paramArrayOfDouble1[0] * paramArrayOfFloat[i6] + paramArrayOfDouble1[1] * paramArrayOfFloat[i6 + 1] + paramArrayOfDouble1[2] * paramArrayOfFloat[i6 + 2]));
/*      */ 
/*      */         
/* 2051 */         floatBuffer.put(i6 + 1, (float)(paramArrayOfDouble1[4] * paramArrayOfFloat[i6] + paramArrayOfDouble1[5] * paramArrayOfFloat[i6 + 1] + paramArrayOfDouble1[6] * paramArrayOfFloat[i6 + 2]));
/*      */ 
/*      */         
/* 2054 */         floatBuffer.put(i6 + 2, (float)(paramArrayOfDouble1[8] * paramArrayOfFloat[i6] + paramArrayOfDouble1[9] * paramArrayOfFloat[i6 + 1] + paramArrayOfDouble1[10] * paramArrayOfFloat[i6 + 2]));
/*      */ 
/*      */         
/* 2057 */         i6 += j;
/*      */       } 
/*      */     } 
/*      */     
/* 2061 */     if (paramInt1 == 12 || paramInt1 == 13 || paramInt1 == 14) {
/*      */       boolean bool;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2067 */       if (paramBoolean3 || ((paramInt5 & 0x460) != 0 && (paramInt8 > 1 || paramInt7 > 1))) {
/*      */ 
/*      */         
/* 2070 */         bool = false;
/*      */       } else {
/* 2072 */         boolean[] arrayOfBoolean = new boolean[1];
/* 2073 */         int[] arrayOfInt1 = new int[1];
/* 2074 */         testForInterleavedArrays(paramInt5, arrayOfBoolean, arrayOfInt1);
/* 2075 */         bool = arrayOfBoolean[0];
/* 2076 */         i = arrayOfInt1[0];
/*      */       } 
/*      */       
/* 2079 */       if (bool) {
/* 2080 */         floatBuffer.position(0);
/* 2081 */         gL.glInterleavedArrays(i, i4, floatBuffer);
/*      */       } else {
/* 2083 */         if ((paramInt5 & 0x2) != 0) {
/* 2084 */           floatBuffer.position(m);
/* 2085 */           gL.glNormalPointer(5126, i4, floatBuffer);
/*      */         } 
/* 2087 */         if (!paramBoolean3 && (paramInt5 & 0x4) != 0) {
/* 2088 */           floatBuffer.position(n);
/* 2089 */           if ((paramInt5 & 0x8) != 0 || bool3) {
/* 2090 */             gL.glColorPointer(4, 5126, i4, floatBuffer);
/*      */           } else {
/* 2092 */             gL.glColorPointer(3, 5126, i4, floatBuffer);
/*      */           } 
/*      */         } 
/* 2095 */         if ((paramInt5 & true) != 0) {
/* 2096 */           floatBuffer.position(k);
/* 2097 */           gL.glVertexPointer(3, 5126, i4, floatBuffer);
/*      */         } 
/* 2099 */         if ((paramInt5 & 0x460) != 0) {
/* 2100 */           executeTexture(paramInt8, b2, i4, i1, paramArrayOfInt3, paramInt8, floatBuffer, gL);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2106 */         if ((paramInt5 & 0x1000) != 0) {
/* 2107 */           int i7 = bool1;
/* 2108 */           for (byte b3 = 0; b3 < paramInt6; b3++) {
/* 2109 */             joglContext.enableVertexAttrArray(gL, b3);
/* 2110 */             floatBuffer.position(i7);
/* 2111 */             joglContext.vertexAttrPointer(gL, b3, paramArrayOfInt1[b3], 5126, i4, floatBuffer);
/*      */             
/* 2113 */             i7 += paramArrayOfInt1[b3];
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2118 */       switch (paramInt1) {
/*      */         case 12:
/* 2120 */           b1 = 5;
/*      */           break;
/*      */         case 13:
/* 2123 */           b1 = 6;
/*      */           break;
/*      */         case 14:
/* 2126 */           b1 = 3;
/*      */           break;
/*      */       } 
/*      */       
/* 2130 */       lockArray(gL, paramInt4);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2135 */       int i6 = paramInt2;
/* 2136 */       IntBuffer intBuffer = IntBuffer.wrap(paramArrayOfInt4);
/* 2137 */       for (byte b = 0; b < i5; b++) {
/* 2138 */         intBuffer.position(i6);
/* 2139 */         int i7 = arrayOfInt[b];
/* 2140 */         gL.glDrawElements(b1, i7, 5125, intBuffer);
/* 2141 */         i6 += i7;
/*      */       } 
/* 2143 */     } else if (paramInt1 == 8 || paramInt1 == 9 || paramInt1 == 10 || paramInt1 == 11) {
/*      */       boolean bool;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2150 */       if (paramBoolean3 || ((paramInt5 & 0x460) != 0 && (paramInt8 > 1 || paramInt7 > 1))) {
/*      */ 
/*      */         
/* 2153 */         bool = false;
/*      */       } else {
/* 2155 */         boolean[] arrayOfBoolean = new boolean[1];
/* 2156 */         int[] arrayOfInt1 = new int[1];
/* 2157 */         testForInterleavedArrays(paramInt5, arrayOfBoolean, arrayOfInt1);
/* 2158 */         bool = arrayOfBoolean[0];
/* 2159 */         i = arrayOfInt1[0];
/*      */       } 
/*      */       
/* 2162 */       if (bool) {
/* 2163 */         floatBuffer.position(0);
/* 2164 */         gL.glInterleavedArrays(i, i4, floatBuffer);
/*      */       } else {
/* 2166 */         if ((paramInt5 & 0x2) != 0) {
/* 2167 */           floatBuffer.position(m);
/* 2168 */           gL.glNormalPointer(5126, i4, floatBuffer);
/*      */         } 
/*      */         
/* 2171 */         if (!paramBoolean3 && (paramInt5 & 0x4) != 0) {
/* 2172 */           floatBuffer.position(n);
/* 2173 */           if ((paramInt5 & 0x8) != 0 || bool3) {
/* 2174 */             gL.glColorPointer(4, 5126, i4, floatBuffer);
/*      */           } else {
/* 2176 */             gL.glColorPointer(3, 5126, i4, floatBuffer);
/*      */           } 
/*      */         } 
/* 2179 */         if ((paramInt5 & true) != 0) {
/* 2180 */           floatBuffer.position(k);
/* 2181 */           gL.glVertexPointer(3, 5126, i4, floatBuffer);
/*      */         } 
/* 2183 */         if ((paramInt5 & 0x460) != 0) {
/* 2184 */           executeTexture(paramInt8, b2, i4, i1, paramArrayOfInt3, paramInt8, floatBuffer, gL);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2190 */         if ((paramInt5 & 0x1000) != 0) {
/* 2191 */           int i6 = bool1;
/* 2192 */           for (byte b = 0; b < paramInt6; b++) {
/* 2193 */             joglContext.enableVertexAttrArray(gL, b);
/* 2194 */             floatBuffer.position(i6);
/* 2195 */             joglContext.vertexAttrPointer(gL, b, paramArrayOfInt1[b], 5126, i4, floatBuffer);
/*      */             
/* 2197 */             i6 += paramArrayOfInt1[b];
/*      */           } 
/*      */         } 
/*      */         
/* 2201 */         switch (paramInt1) {
/*      */           case 8:
/* 2203 */             b1 = 7;
/*      */             break;
/*      */           case 9:
/* 2206 */             b1 = 4;
/*      */             break;
/*      */           case 10:
/* 2209 */             b1 = 0;
/*      */             break;
/*      */           case 11:
/* 2212 */             b1 = 1;
/*      */             break;
/*      */         } 
/*      */         
/* 2216 */         lockArray(gL, paramInt4);
/*      */         
/* 2218 */         IntBuffer intBuffer = IntBuffer.wrap(paramArrayOfInt4);
/* 2219 */         intBuffer.position(paramInt2);
/* 2220 */         gL.glDrawElements(b1, paramInt3, 5125, intBuffer);
/*      */       } 
/*      */     } 
/*      */     
/* 2224 */     unlockArray(gL);
/*      */     
/* 2226 */     if ((paramInt5 & 0x1000) != 0) {
/* 2227 */       resetVertexAttrs(gL, joglContext, paramInt6);
/*      */     }
/*      */     
/* 2230 */     if ((paramInt5 & 0x460) != 0) {
/* 2231 */       resetTexture(gL, joglContext);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void executeIndexedGeometryArray(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int paramInt7, int[] paramArrayOfInt2, int paramInt8, int[] paramArrayOfInt3, int paramInt9, float[] paramArrayOfFloat1, FloatBuffer paramFloatBuffer, float[] paramArrayOfFloat2, int paramInt10, int[] paramArrayOfInt4) {
/* 2257 */     JoglContext joglContext = (JoglContext)paramContext;
/* 2258 */     GL gL = context(joglContext).getGL();
/*      */ 
/*      */     
/* 2261 */     int i = 0;
/* 2262 */     byte b1 = 0;
/* 2263 */     int j = 0, k = 0, m = 0, n = 0, i1 = 0;
/* 2264 */     byte b2 = 0; int i2 = 0;
/* 2265 */     boolean bool = false;
/* 2266 */     int i3 = 0;
/* 2267 */     int i4 = 0, i5 = 0;
/* 2268 */     FloatBuffer floatBuffer1 = null;
/* 2269 */     FloatBuffer floatBuffer2 = null;
/* 2270 */     int[] arrayOfInt = null;
/* 2271 */     int i6 = 0;
/*      */     
/* 2273 */     if ((paramInt5 & true) != 0) {
/* 2274 */       j += true;
/*      */     }
/* 2276 */     if ((paramInt5 & 0x2) != 0) {
/* 2277 */       j += true;
/* 2278 */       k += true;
/*      */     } 
/*      */     
/* 2281 */     if ((paramInt5 & 0x4) != 0) {
/* 2282 */       if ((paramInt5 & 0x8) != 0) {
/* 2283 */         j += true;
/* 2284 */         m += true;
/* 2285 */         k += true;
/*      */       } else {
/* 2287 */         j += true;
/* 2288 */         m += true;
/* 2289 */         k += true;
/*      */       } 
/*      */     }
/*      */     
/* 2293 */     if ((paramInt5 & 0x460) != 0) {
/* 2294 */       if ((paramInt5 & 0x20) != 0) {
/* 2295 */         b2 = 2;
/* 2296 */         i2 = 2 * paramInt7;
/* 2297 */       } else if ((paramInt5 & 0x40) != 0) {
/* 2298 */         b2 = 3;
/* 2299 */         i2 = 3 * paramInt7;
/* 2300 */       } else if ((paramInt5 & 0x400) != 0) {
/* 2301 */         b2 = 4;
/* 2302 */         i2 = 4 * paramInt7;
/*      */       } 
/* 2304 */       j += i2;
/* 2305 */       m += i2;
/* 2306 */       n += i2;
/* 2307 */       k += i2;
/*      */     } 
/*      */     
/* 2310 */     if ((paramInt5 & 0x1000) != 0) {
/* 2311 */       for (byte b = 0; b < paramInt6; b++) {
/* 2312 */         i3 += paramArrayOfInt1[b];
/*      */       }
/* 2314 */       j += i3;
/* 2315 */       m += i3;
/* 2316 */       n += i3;
/* 2317 */       k += i3;
/* 2318 */       i1 += i3;
/*      */     } 
/*      */     
/* 2321 */     i4 = j * 4;
/*      */     
/* 2323 */     if (paramInt1 == 12 || paramInt1 == 13 || paramInt1 == 14) {
/*      */ 
/*      */       
/* 2326 */       arrayOfInt = ((IndexedGeometryStripArrayRetained)paramGeometryArrayRetained).stripIndexCounts;
/* 2327 */       i6 = arrayOfInt.length;
/*      */     } 
/*      */ 
/*      */     
/* 2331 */     if (paramArrayOfFloat1 != null) {
/* 2332 */       floatBuffer1 = getVertexArrayBuffer(paramArrayOfFloat1);
/* 2333 */     } else if (paramFloatBuffer != null) {
/* 2334 */       floatBuffer1 = paramFloatBuffer;
/*      */     } else {
/*      */       
/* 2337 */       throw new AssertionError("Unable to get vertex pointer");
/*      */     } 
/*      */ 
/*      */     
/* 2341 */     int i7 = j;
/* 2342 */     if (paramArrayOfFloat2 != null) {
/* 2343 */       floatBuffer2 = getColorArrayBuffer(paramArrayOfFloat2);
/* 2344 */       i7 = 4;
/*      */     } else {
/*      */       
/* 2347 */       floatBuffer2 = floatBuffer1;
/*      */     } 
/*      */     
/* 2350 */     i5 = i7 * 4;
/*      */ 
/*      */     
/* 2353 */     if (paramBoolean1) {
/* 2354 */       gL.glEnable(2977);
/*      */     }
/*      */ 
/*      */     
/* 2358 */     if (paramInt1 == 12 || paramInt1 == 13 || paramInt1 == 14) {
/*      */       boolean bool1;
/*      */       
/* 2361 */       if (paramBoolean3 || paramArrayOfFloat2 != null || ((paramInt5 & 0x460) != 0 && (paramInt8 > 1 || paramInt7 > 1))) {
/*      */ 
/*      */         
/* 2364 */         bool1 = false;
/*      */       } else {
/* 2366 */         boolean[] arrayOfBoolean = new boolean[1];
/* 2367 */         int[] arrayOfInt1 = new int[1];
/* 2368 */         testForInterleavedArrays(paramInt5, arrayOfBoolean, arrayOfInt1);
/* 2369 */         bool1 = arrayOfBoolean[0];
/* 2370 */         i = arrayOfInt1[0];
/*      */       } 
/* 2372 */       if (bool1) {
/* 2373 */         floatBuffer1.position(0);
/* 2374 */         gL.glInterleavedArrays(i, i4, floatBuffer1);
/*      */       } else {
/* 2376 */         if ((paramInt5 & 0x2) != 0) {
/* 2377 */           floatBuffer1.position(m);
/* 2378 */           gL.glNormalPointer(5126, i4, floatBuffer1);
/*      */         } 
/* 2380 */         if (!paramBoolean3 && (paramInt5 & 0x4) != 0) {
/* 2381 */           if (floatBuffer2 == floatBuffer1) {
/* 2382 */             floatBuffer2.position(n);
/*      */           }
/* 2384 */           if ((paramInt5 & 0x8) != 0 || paramBoolean2) {
/* 2385 */             gL.glColorPointer(4, 5126, i5, floatBuffer2);
/*      */           } else {
/* 2387 */             gL.glColorPointer(3, 5126, i5, floatBuffer2);
/*      */           } 
/*      */         } 
/* 2390 */         if ((paramInt5 & true) != 0) {
/* 2391 */           floatBuffer1.position(k);
/* 2392 */           gL.glVertexPointer(3, 5126, i4, floatBuffer1);
/*      */         } 
/*      */         
/* 2395 */         if ((paramInt5 & 0x460) != 0)
/*      */         {
/* 2397 */           executeTexture(paramInt8, b2, i4, i1, paramArrayOfInt3, paramInt9, floatBuffer1, gL);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2404 */         if ((paramInt5 & 0x1000) != 0) {
/* 2405 */           int i9 = bool;
/* 2406 */           for (byte b3 = 0; b3 < paramInt6; b3++) {
/* 2407 */             joglContext.enableVertexAttrArray(gL, b3);
/* 2408 */             floatBuffer1.position(i9);
/* 2409 */             joglContext.vertexAttrPointer(gL, b3, paramArrayOfInt1[b3], 5126, i4, floatBuffer1);
/*      */             
/* 2411 */             i9 += paramArrayOfInt1[b3];
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2416 */       switch (paramInt1) {
/*      */         case 12:
/* 2418 */           b1 = 5;
/*      */           break;
/*      */         case 13:
/* 2421 */           b1 = 6;
/*      */           break;
/*      */         case 14:
/* 2424 */           b1 = 3;
/*      */           break;
/*      */       } 
/*      */       
/* 2428 */       lockArray(gL, paramInt4);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2433 */       int i8 = paramInt2;
/* 2434 */       IntBuffer intBuffer = IntBuffer.wrap(paramArrayOfInt4);
/* 2435 */       for (byte b = 0; b < i6; b++) {
/* 2436 */         intBuffer.position(i8);
/* 2437 */         int i9 = arrayOfInt[b];
/* 2438 */         gL.glDrawElements(b1, i9, 5125, intBuffer);
/* 2439 */         i8 += i9;
/*      */       } 
/* 2441 */     } else if (paramInt1 == 8 || paramInt1 == 9 || paramInt1 == 10 || paramInt1 == 11) {
/*      */       boolean bool1;
/*      */ 
/*      */ 
/*      */       
/* 2446 */       if (paramBoolean3 || paramArrayOfFloat2 != null || ((paramInt5 & 0x460) != 0 && (paramInt8 > 1 || paramInt7 > 1))) {
/*      */ 
/*      */         
/* 2449 */         bool1 = false;
/*      */       } else {
/* 2451 */         boolean[] arrayOfBoolean = new boolean[1];
/* 2452 */         int[] arrayOfInt1 = new int[1];
/* 2453 */         testForInterleavedArrays(paramInt5, arrayOfBoolean, arrayOfInt1);
/* 2454 */         bool1 = arrayOfBoolean[0];
/* 2455 */         i = arrayOfInt1[0];
/*      */       } 
/*      */       
/* 2458 */       if (bool1) {
/* 2459 */         floatBuffer1.position(0);
/* 2460 */         gL.glInterleavedArrays(i, i4, floatBuffer1);
/*      */       } else {
/* 2462 */         if ((paramInt5 & 0x2) != 0) {
/* 2463 */           floatBuffer1.position(m);
/* 2464 */           gL.glNormalPointer(5126, i4, floatBuffer1);
/*      */         } 
/*      */         
/* 2467 */         if (!paramBoolean3 && (paramInt5 & 0x4) != 0) {
/* 2468 */           if (floatBuffer2 == floatBuffer1) {
/* 2469 */             floatBuffer2.position(n);
/*      */           }
/* 2471 */           if ((paramInt5 & 0x8) != 0 || paramBoolean2) {
/* 2472 */             gL.glColorPointer(4, 5126, i5, floatBuffer2);
/*      */           } else {
/* 2474 */             gL.glColorPointer(3, 5126, i5, floatBuffer2);
/*      */           } 
/*      */         } 
/* 2477 */         if ((paramInt5 & true) != 0) {
/* 2478 */           floatBuffer1.position(k);
/* 2479 */           gL.glVertexPointer(3, 5126, i4, floatBuffer1);
/*      */         } 
/*      */         
/* 2482 */         if ((paramInt5 & 0x460) != 0)
/*      */         {
/* 2484 */           executeTexture(paramInt8, b2, i4, i1, paramArrayOfInt3, paramInt9, floatBuffer1, gL);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2491 */         if ((paramInt5 & 0x1000) != 0) {
/* 2492 */           int i8 = bool;
/* 2493 */           for (byte b = 0; b < paramInt6; b++) {
/* 2494 */             joglContext.enableVertexAttrArray(gL, b);
/* 2495 */             floatBuffer1.position(i8);
/* 2496 */             joglContext.vertexAttrPointer(gL, b, paramArrayOfInt1[b], 5126, i4, floatBuffer1);
/*      */             
/* 2498 */             i8 += paramArrayOfInt1[b];
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2503 */       lockArray(gL, paramInt4);
/* 2504 */       IntBuffer intBuffer = IntBuffer.wrap(paramArrayOfInt4);
/* 2505 */       intBuffer.position(paramInt2);
/* 2506 */       switch (paramInt1) { case 8:
/* 2507 */           gL.glDrawElements(7, paramInt3, 5125, intBuffer); break;
/* 2508 */         case 9: gL.glDrawElements(4, paramInt3, 5125, intBuffer); break;
/* 2509 */         case 10: gL.glDrawElements(0, paramInt3, 5125, intBuffer); break;
/* 2510 */         case 11: gL.glDrawElements(1, paramInt3, 5125, intBuffer);
/*      */           break; }
/*      */     
/*      */     } 
/* 2514 */     unlockArray(gL);
/*      */     
/* 2516 */     if ((paramInt5 & 0x1000) != 0) {
/* 2517 */       resetVertexAttrs(gL, joglContext, paramInt6);
/*      */     }
/*      */     
/* 2520 */     if ((paramInt5 & 0x460) != 0) {
/* 2521 */       resetTexture(gL, joglContext);
/*      */     }
/*      */ 
/*      */     
/* 2525 */     if (paramBoolean1) {
/* 2526 */       gL.glDisable(2977);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void executeIndexedGeometryArrayVA(Context paramContext, GeometryArrayRetained paramGeometryArrayRetained, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, FloatBuffer paramFloatBuffer1, DoubleBuffer paramDoubleBuffer, FloatBuffer paramFloatBuffer2, ByteBuffer paramByteBuffer, FloatBuffer paramFloatBuffer3, int paramInt7, int[] paramArrayOfInt1, FloatBuffer[] paramArrayOfFloatBuffer1, int paramInt8, int[] paramArrayOfInt2, int paramInt9, int paramInt10, FloatBuffer[] paramArrayOfFloatBuffer2, int paramInt11, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt12) {
/* 2547 */     JoglContext joglContext = (JoglContext)paramContext;
/* 2548 */     GL gL = context(joglContext).getGL();
/*      */     
/* 2550 */     boolean bool1 = ((paramInt6 & true) != 0) ? 1 : 0;
/* 2551 */     boolean bool2 = ((paramInt6 & 0x2) != 0) ? 1 : 0;
/* 2552 */     boolean bool3 = ((paramInt6 & 0x4) != 0) ? 1 : 0;
/* 2553 */     boolean bool4 = ((paramInt6 & 0x8) != 0) ? 1 : 0;
/* 2554 */     boolean bool5 = ((paramInt6 & 0x10) != 0) ? 1 : 0;
/* 2555 */     boolean bool6 = ((paramInt6 & 0x40) != 0) ? 1 : 0;
/* 2556 */     boolean bool7 = ((paramInt6 & 0x20) != 0) ? 1 : 0;
/*      */ 
/*      */     
/* 2559 */     if (paramBoolean1) {
/* 2560 */       gL.glEnable(2977);
/*      */     }
/*      */ 
/*      */     
/* 2564 */     if (bool1) {
/* 2565 */       paramFloatBuffer1.position(0);
/* 2566 */       gL.glVertexPointer(3, 5126, 0, paramFloatBuffer1);
/* 2567 */     } else if (bool2) {
/* 2568 */       paramDoubleBuffer.position(0);
/* 2569 */       gL.glVertexPointer(3, 5130, 0, paramDoubleBuffer);
/*      */     } 
/* 2571 */     if (bool3) {
/* 2572 */       paramFloatBuffer2.position(0);
/* 2573 */       if ((paramInt5 & 0x8) != 0) {
/* 2574 */         gL.glColorPointer(4, 5126, 0, paramFloatBuffer2);
/*      */       } else {
/* 2576 */         gL.glColorPointer(3, 5126, 0, paramFloatBuffer2);
/*      */       } 
/* 2578 */     } else if (bool4) {
/* 2579 */       paramByteBuffer.position(0);
/* 2580 */       if ((paramInt5 & 0x8) != 0) {
/* 2581 */         gL.glColorPointer(4, 5121, 0, paramByteBuffer);
/*      */       } else {
/* 2583 */         gL.glColorPointer(3, 5121, 0, paramByteBuffer);
/*      */       } 
/*      */     } 
/* 2586 */     if (bool5) {
/* 2587 */       paramFloatBuffer3.position(0);
/* 2588 */       gL.glNormalPointer(5126, 0, paramFloatBuffer3);
/*      */     } 
/*      */     
/* 2591 */     if (bool6) {
/* 2592 */       for (byte b = 0; b < paramInt7; b++) {
/* 2593 */         FloatBuffer floatBuffer = paramArrayOfFloatBuffer1[b];
/* 2594 */         int i = paramArrayOfInt1[b];
/* 2595 */         joglContext.enableVertexAttrArray(gL, b);
/* 2596 */         floatBuffer.position(0);
/* 2597 */         joglContext.vertexAttrPointer(gL, b, i, 5126, 0, floatBuffer);
/*      */       } 
/*      */     }
/*      */     
/* 2601 */     if (bool7) {
/* 2602 */       int i = 0;
/* 2603 */       for (byte b = 0; b < paramInt9; b++) {
/* 2604 */         if (b < paramInt8 && (i = paramArrayOfInt2[b]) != -1) {
/*      */           
/* 2606 */           FloatBuffer floatBuffer = paramArrayOfFloatBuffer2[i];
/* 2607 */           floatBuffer.position(0);
/* 2608 */           enableTexCoordPointer(gL, b, paramInt10, 5126, 0, floatBuffer);
/*      */         } else {
/*      */           
/* 2611 */           disableTexCoordPointer(gL, b);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2616 */       clientActiveTextureUnit(gL, 0);
/*      */     } 
/*      */     
/* 2619 */     lockArray(gL, paramInt4);
/*      */     
/* 2621 */     if (paramInt1 == 12 || paramInt1 == 13 || paramInt1 == 14) {
/*      */ 
/*      */       
/* 2624 */       byte b1 = 0;
/* 2625 */       switch (paramInt1) {
/*      */         case 12:
/* 2627 */           b1 = 5;
/*      */           break;
/*      */         case 13:
/* 2630 */           b1 = 6;
/*      */           break;
/*      */         case 14:
/* 2633 */           b1 = 3;
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2640 */       int i = paramInt2;
/* 2641 */       IntBuffer intBuffer = IntBuffer.wrap(paramArrayOfInt3);
/* 2642 */       for (byte b2 = 0; b2 < paramInt12; b2++) {
/* 2643 */         intBuffer.position(i);
/* 2644 */         int j = paramArrayOfInt4[b2];
/* 2645 */         gL.glDrawElements(b1, j, 5125, intBuffer);
/* 2646 */         i += j;
/*      */       } 
/*      */     } else {
/* 2649 */       IntBuffer intBuffer = IntBuffer.wrap(paramArrayOfInt3);
/* 2650 */       intBuffer.position(paramInt2);
/* 2651 */       switch (paramInt1) { case 8:
/* 2652 */           gL.glDrawElements(7, paramInt3, 5125, intBuffer); break;
/* 2653 */         case 9: gL.glDrawElements(4, paramInt3, 5125, intBuffer); break;
/* 2654 */         case 10: gL.glDrawElements(0, paramInt3, 5125, intBuffer); break;
/* 2655 */         case 11: gL.glDrawElements(1, paramInt3, 5125, intBuffer);
/*      */           break; }
/*      */     
/*      */     } 
/* 2659 */     unlockArray(gL);
/*      */ 
/*      */     
/* 2662 */     if (paramBoolean1) {
/* 2663 */       gL.glDisable(2977);
/*      */     }
/*      */     
/* 2666 */     if (bool6) {
/* 2667 */       resetVertexAttrs(gL, joglContext, paramInt7);
/*      */     }
/*      */     
/* 2670 */     if (bool7) {
/* 2671 */       resetTexture(gL, joglContext);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void readRaster(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, Object paramObject1, int paramInt9, Object paramObject2) {
/* 2692 */     GL gL = context(paramContext).getGL();
/* 2693 */     gL.glPixelStorei(3330, paramInt4);
/* 2694 */     gL.glPixelStorei(3333, 1);
/* 2695 */     int i = paramInt6 - paramInt5 - paramInt3;
/*      */     
/* 2697 */     if ((paramInt1 & true) != 0) {
/* 2698 */       char c = Character.MIN_VALUE;
/* 2699 */       if (paramInt7 == 4096) {
/*      */         
/* 2701 */         switch (paramInt8) {
/*      */           case 1:
/* 2703 */             c = '胠';
/*      */             break;
/*      */           case 2:
/* 2706 */             c = 'ᤇ';
/*      */             break;
/*      */           case 4:
/* 2709 */             if (gL.isExtensionAvailable("GL_EXT_abgr")) {
/* 2710 */               c = '耀';
/*      */               break;
/*      */             } 
/*      */             assert false;
/*      */             return;
/*      */ 
/*      */           
/*      */           case 8:
/* 2718 */             c = 'ᤈ';
/*      */             break;
/*      */           
/*      */           case 16:
/* 2722 */             c = 'ᤊ';
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/*      */             assert false;
/*      */             return;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2734 */         gL.glReadPixels(paramInt2, i, paramInt4, paramInt5, c, 5121, ByteBuffer.wrap((byte[])paramObject1));
/*      */ 
/*      */       
/*      */       }
/* 2738 */       else if (paramInt7 == 8192) {
/* 2739 */         char c1 = '耵';
/* 2740 */         boolean bool = false;
/*      */         
/* 2742 */         switch (paramInt8) {
/*      */           
/*      */           case 128:
/* 2745 */             c = 'ᤈ';
/* 2746 */             c1 = '荧';
/* 2747 */             bool = true;
/*      */             break;
/*      */           case 256:
/* 2750 */             bool = true;
/*      */           
/*      */           case 512:
/* 2753 */             c = '胡';
/* 2754 */             c1 = '荧';
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/*      */             assert false;
/*      */             return;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2770 */         if (bool) {
/* 2771 */           gL.glPixelTransferf(3356, 0.0F);
/* 2772 */           gL.glPixelTransferf(3357, 1.0F);
/*      */         } 
/*      */         
/* 2775 */         gL.glReadPixels(paramInt2, i, paramInt4, paramInt5, c, c1, IntBuffer.wrap((int[])paramObject1));
/*      */ 
/*      */ 
/*      */         
/* 2779 */         if (bool) {
/* 2780 */           gL.glPixelTransferf(3356, 1.0F);
/* 2781 */           gL.glPixelTransferf(3357, 0.0F);
/*      */         } 
/*      */       } else {
/*      */         assert false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2789 */     if ((paramInt1 & 0x2) != 0)
/*      */     {
/* 2791 */       if (paramInt9 == 1) {
/*      */         
/* 2793 */         gL.glReadPixels(paramInt2, i, paramInt4, paramInt5, 6402, 5125, IntBuffer.wrap((int[])paramObject2));
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 2798 */         gL.glReadPixels(paramInt2, i, paramInt4, paramInt5, 6402, 5126, FloatBuffer.wrap((float[])paramObject2));
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform1i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt) {
/* 2819 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 2820 */     if (joglCgShaderParameter.vParam() != null) {
/* 2821 */       CgGL.cgSetParameter1i(joglCgShaderParameter.vParam(), paramInt);
/*      */     }
/*      */     
/* 2824 */     if (joglCgShaderParameter.fParam() != null) {
/* 2825 */       CgGL.cgSetParameter1i(joglCgShaderParameter.fParam(), paramInt);
/*      */     }
/*      */     
/* 2828 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform1f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float paramFloat) {
/* 2837 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 2838 */     if (joglCgShaderParameter.vParam() != null) {
/* 2839 */       CgGL.cgSetParameter1f(joglCgShaderParameter.vParam(), paramFloat);
/*      */     }
/*      */     
/* 2842 */     if (joglCgShaderParameter.fParam() != null) {
/* 2843 */       CgGL.cgSetParameter1f(joglCgShaderParameter.fParam(), paramFloat);
/*      */     }
/*      */     
/* 2846 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform2i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) {
/* 2855 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 2856 */     if (joglCgShaderParameter.vParam() != null) {
/* 2857 */       CgGL.cgSetParameter2i(joglCgShaderParameter.vParam(), paramArrayOfInt[0], paramArrayOfInt[1]);
/*      */     }
/*      */     
/* 2860 */     if (joglCgShaderParameter.fParam() != null) {
/* 2861 */       CgGL.cgSetParameter2i(joglCgShaderParameter.fParam(), paramArrayOfInt[0], paramArrayOfInt[1]);
/*      */     }
/*      */     
/* 2864 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform2f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) {
/* 2873 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 2874 */     if (joglCgShaderParameter.vParam() != null) {
/* 2875 */       CgGL.cgSetParameter2f(joglCgShaderParameter.vParam(), paramArrayOfFloat[0], paramArrayOfFloat[1]);
/*      */     }
/*      */     
/* 2878 */     if (joglCgShaderParameter.fParam() != null) {
/* 2879 */       CgGL.cgSetParameter2f(joglCgShaderParameter.fParam(), paramArrayOfFloat[0], paramArrayOfFloat[1]);
/*      */     }
/*      */     
/* 2882 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform3i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) {
/* 2891 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 2892 */     if (joglCgShaderParameter.vParam() != null) {
/* 2893 */       CgGL.cgSetParameter3i(joglCgShaderParameter.vParam(), paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
/*      */     }
/*      */     
/* 2896 */     if (joglCgShaderParameter.fParam() != null) {
/* 2897 */       CgGL.cgSetParameter3i(joglCgShaderParameter.fParam(), paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
/*      */     }
/*      */     
/* 2900 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) {
/* 2909 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 2910 */     if (joglCgShaderParameter.vParam() != null) {
/* 2911 */       CgGL.cgSetParameter3f(joglCgShaderParameter.vParam(), paramArrayOfFloat[0], paramArrayOfFloat[1], paramArrayOfFloat[2]);
/*      */     }
/*      */     
/* 2914 */     if (joglCgShaderParameter.fParam() != null) {
/* 2915 */       CgGL.cgSetParameter3f(joglCgShaderParameter.fParam(), paramArrayOfFloat[0], paramArrayOfFloat[1], paramArrayOfFloat[2]);
/*      */     }
/*      */     
/* 2918 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform4i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) {
/* 2927 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 2928 */     if (joglCgShaderParameter.vParam() != null) {
/* 2929 */       CgGL.cgSetParameter4i(joglCgShaderParameter.vParam(), paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2], paramArrayOfInt[3]);
/*      */     }
/*      */     
/* 2932 */     if (joglCgShaderParameter.fParam() != null) {
/* 2933 */       CgGL.cgSetParameter4i(joglCgShaderParameter.fParam(), paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2], paramArrayOfInt[3]);
/*      */     }
/*      */     
/* 2936 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) {
/* 2945 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 2946 */     if (joglCgShaderParameter.vParam() != null) {
/* 2947 */       CgGL.cgSetParameter4f(joglCgShaderParameter.vParam(), paramArrayOfFloat[0], paramArrayOfFloat[1], paramArrayOfFloat[2], paramArrayOfFloat[3]);
/*      */     }
/*      */     
/* 2950 */     if (joglCgShaderParameter.fParam() != null) {
/* 2951 */       CgGL.cgSetParameter4f(joglCgShaderParameter.fParam(), paramArrayOfFloat[0], paramArrayOfFloat[1], paramArrayOfFloat[2], paramArrayOfFloat[3]);
/*      */     }
/*      */     
/* 2954 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniformMatrix3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) {
/* 2963 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 2964 */     if (joglCgShaderParameter.vParam() != null) {
/* 2965 */       CgGL.cgGLSetMatrixParameterfr(joglCgShaderParameter.vParam(), paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 2968 */     if (joglCgShaderParameter.fParam() != null) {
/* 2969 */       CgGL.cgGLSetMatrixParameterfr(joglCgShaderParameter.fParam(), paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 2972 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniformMatrix4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) {
/* 2981 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 2982 */     if (joglCgShaderParameter.vParam() != null) {
/* 2983 */       CgGL.cgGLSetMatrixParameterfr(joglCgShaderParameter.vParam(), paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 2986 */     if (joglCgShaderParameter.fParam() != null) {
/* 2987 */       CgGL.cgGLSetMatrixParameterfr(joglCgShaderParameter.fParam(), paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 2990 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform1iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) {
/* 3002 */     float[] arrayOfFloat = new float[paramArrayOfInt.length];
/* 3003 */     for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 3004 */       arrayOfFloat[b] = paramArrayOfInt[b];
/*      */     }
/*      */     
/* 3007 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 3008 */     if (joglCgShaderParameter.vParam() != null) {
/* 3009 */       CgGL.cgGLSetParameterArray1f(joglCgShaderParameter.vParam(), 0L, paramInt, arrayOfFloat, 0);
/*      */     }
/*      */     
/* 3012 */     if (joglCgShaderParameter.fParam() != null) {
/* 3013 */       CgGL.cgGLSetParameterArray1f(joglCgShaderParameter.fParam(), 0L, paramInt, arrayOfFloat, 0);
/*      */     }
/*      */     
/* 3016 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform1fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) {
/* 3026 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 3027 */     if (joglCgShaderParameter.vParam() != null) {
/* 3028 */       CgGL.cgGLSetParameterArray1f(joglCgShaderParameter.vParam(), 0L, paramInt, paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 3031 */     if (joglCgShaderParameter.fParam() != null) {
/* 3032 */       CgGL.cgGLSetParameterArray1f(joglCgShaderParameter.fParam(), 0L, paramInt, paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 3035 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform2iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) {
/* 3045 */     float[] arrayOfFloat = new float[paramArrayOfInt.length];
/* 3046 */     for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 3047 */       arrayOfFloat[b] = paramArrayOfInt[b];
/*      */     }
/*      */     
/* 3050 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 3051 */     if (joglCgShaderParameter.vParam() != null) {
/* 3052 */       CgGL.cgGLSetParameterArray2f(joglCgShaderParameter.vParam(), 0L, paramInt, arrayOfFloat, 0);
/*      */     }
/*      */     
/* 3055 */     if (joglCgShaderParameter.fParam() != null) {
/* 3056 */       CgGL.cgGLSetParameterArray2f(joglCgShaderParameter.fParam(), 0L, paramInt, arrayOfFloat, 0);
/*      */     }
/*      */     
/* 3059 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform2fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) {
/* 3069 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 3070 */     if (joglCgShaderParameter.vParam() != null) {
/* 3071 */       CgGL.cgGLSetParameterArray2f(joglCgShaderParameter.vParam(), 0L, paramInt, paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 3074 */     if (joglCgShaderParameter.fParam() != null) {
/* 3075 */       CgGL.cgGLSetParameterArray2f(joglCgShaderParameter.fParam(), 0L, paramInt, paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 3078 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform3iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) {
/* 3088 */     float[] arrayOfFloat = new float[paramArrayOfInt.length];
/* 3089 */     for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 3090 */       arrayOfFloat[b] = paramArrayOfInt[b];
/*      */     }
/*      */     
/* 3093 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 3094 */     if (joglCgShaderParameter.vParam() != null) {
/* 3095 */       CgGL.cgGLSetParameterArray3f(joglCgShaderParameter.vParam(), 0L, paramInt, arrayOfFloat, 0);
/*      */     }
/*      */     
/* 3098 */     if (joglCgShaderParameter.fParam() != null) {
/* 3099 */       CgGL.cgGLSetParameterArray3f(joglCgShaderParameter.fParam(), 0L, paramInt, arrayOfFloat, 0);
/*      */     }
/*      */     
/* 3102 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) {
/* 3112 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 3113 */     if (joglCgShaderParameter.vParam() != null) {
/* 3114 */       CgGL.cgGLSetParameterArray2f(joglCgShaderParameter.vParam(), 0L, paramInt, paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 3117 */     if (joglCgShaderParameter.fParam() != null) {
/* 3118 */       CgGL.cgGLSetParameterArray2f(joglCgShaderParameter.fParam(), 0L, paramInt, paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 3121 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform4iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) {
/* 3131 */     float[] arrayOfFloat = new float[paramArrayOfInt.length];
/* 3132 */     for (byte b = 0; b < paramArrayOfInt.length; b++) {
/* 3133 */       arrayOfFloat[b] = paramArrayOfInt[b];
/*      */     }
/*      */     
/* 3136 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 3137 */     if (joglCgShaderParameter.vParam() != null) {
/* 3138 */       CgGL.cgGLSetParameterArray4f(joglCgShaderParameter.vParam(), 0L, paramInt, arrayOfFloat, 0);
/*      */     }
/*      */     
/* 3141 */     if (joglCgShaderParameter.fParam() != null) {
/* 3142 */       CgGL.cgGLSetParameterArray4f(joglCgShaderParameter.fParam(), 0L, paramInt, arrayOfFloat, 0);
/*      */     }
/*      */     
/* 3145 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniform4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) {
/* 3155 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 3156 */     if (joglCgShaderParameter.vParam() != null) {
/* 3157 */       CgGL.cgGLSetParameterArray2f(joglCgShaderParameter.vParam(), 0L, paramInt, paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 3160 */     if (joglCgShaderParameter.fParam() != null) {
/* 3161 */       CgGL.cgGLSetParameterArray2f(joglCgShaderParameter.fParam(), 0L, paramInt, paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 3164 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniformMatrix3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) {
/* 3174 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 3175 */     if (joglCgShaderParameter.vParam() != null) {
/* 3176 */       CgGL.cgGLSetMatrixParameterArrayfr(joglCgShaderParameter.vParam(), 0L, paramInt, paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 3179 */     if (joglCgShaderParameter.fParam() != null) {
/* 3180 */       CgGL.cgGLSetMatrixParameterArrayfr(joglCgShaderParameter.fParam(), 0L, paramInt, paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 3183 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setCgUniformMatrix4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) {
/* 3193 */     JoglCgShaderParameter joglCgShaderParameter = (JoglCgShaderParameter)paramShaderAttrLoc;
/* 3194 */     if (joglCgShaderParameter.vParam() != null) {
/* 3195 */       CgGL.cgGLSetMatrixParameterArrayfr(joglCgShaderParameter.vParam(), 0L, paramInt, paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 3198 */     if (joglCgShaderParameter.fParam() != null) {
/* 3199 */       CgGL.cgGLSetMatrixParameterArrayfr(joglCgShaderParameter.fParam(), 0L, paramInt, paramArrayOfFloat, 0);
/*      */     }
/*      */     
/* 3202 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError createCgShader(Context paramContext, int paramInt, ShaderId[] paramArrayOfShaderId) {
/* 3209 */     JoglContext joglContext = (JoglContext)paramContext;
/* 3210 */     JoglCgShaderInfo joglCgShaderInfo = new JoglCgShaderInfo();
/* 3211 */     joglCgShaderInfo.setJ3DShaderType(paramInt);
/* 3212 */     if (paramInt == 1) {
/* 3213 */       joglCgShaderInfo.setShaderProfile(joglContext.getCgVertexProfile());
/* 3214 */     } else if (paramInt == 2) {
/* 3215 */       joglCgShaderInfo.setShaderProfile(joglContext.getCgFragmentProfile());
/*      */     } else {
/* 3217 */       throw new AssertionError("unrecognized shaderType " + paramInt);
/*      */     } 
/* 3219 */     paramArrayOfShaderId[0] = joglCgShaderInfo;
/* 3220 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   ShaderError destroyCgShader(Context paramContext, ShaderId paramShaderId) {
/* 3225 */     JoglCgShaderInfo joglCgShaderInfo = (JoglCgShaderInfo)paramShaderId;
/* 3226 */     CGprogram cGprogram = joglCgShaderInfo.getCgShader();
/* 3227 */     if (cGprogram != null) {
/* 3228 */       CgGL.cgDestroyProgram(cGprogram);
/*      */     }
/* 3230 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   ShaderError compileCgShader(Context paramContext, ShaderId paramShaderId, String paramString) {
/* 3235 */     if (paramString == null)
/* 3236 */       throw new AssertionError("shader program string is null"); 
/* 3237 */     JoglCgShaderInfo joglCgShaderInfo = (JoglCgShaderInfo)paramShaderId;
/* 3238 */     JoglContext joglContext = (JoglContext)paramContext;
/* 3239 */     CGprogram cGprogram = CgGL.cgCreateProgram(joglContext.getCgContext(), 4112, paramString, joglCgShaderInfo.getShaderProfile(), null, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3245 */     int i = 0;
/* 3246 */     if ((i = CgGL.cgGetError()) != 0) {
/* 3247 */       ShaderError shaderError = new ShaderError(1, "Cg shader compile error");
/*      */       
/* 3249 */       shaderError.setDetailMessage(getCgErrorLog(joglContext, i));
/* 3250 */       return shaderError;
/*      */     } 
/* 3252 */     joglCgShaderInfo.setCgShader(cGprogram);
/* 3253 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError createCgShaderProgram(Context paramContext, ShaderProgramId[] paramArrayOfShaderProgramId) {
/* 3259 */     JoglCgShaderProgramInfo joglCgShaderProgramInfo = new JoglCgShaderProgramInfo();
/* 3260 */     paramArrayOfShaderProgramId[0] = joglCgShaderProgramInfo;
/* 3261 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 3266 */   ShaderError destroyCgShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) { return null; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError linkCgShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderId[] paramArrayOfShaderId) {
/* 3272 */     JoglCgShaderProgramInfo joglCgShaderProgramInfo = (JoglCgShaderProgramInfo)paramShaderProgramId;
/*      */ 
/*      */     
/* 3275 */     joglCgShaderProgramInfo.setVertexShader(null);
/* 3276 */     joglCgShaderProgramInfo.setFragmentShader(null);
/* 3277 */     for (byte b = 0; b < paramArrayOfShaderId.length; b++) {
/* 3278 */       JoglCgShaderInfo joglCgShaderInfo = (JoglCgShaderInfo)paramArrayOfShaderId[b];
/* 3279 */       if (joglCgShaderInfo.getJ3DShaderType() == 1) {
/* 3280 */         joglCgShaderProgramInfo.setVertexShader(joglCgShaderInfo);
/*      */       } else {
/* 3282 */         joglCgShaderProgramInfo.setFragmentShader(joglCgShaderInfo);
/*      */       } 
/*      */       
/* 3285 */       CgGL.cgGLLoadProgram(joglCgShaderInfo.getCgShader());
/* 3286 */       int i = 0;
/* 3287 */       if ((i = CgGL.cgGetError()) != 0) {
/* 3288 */         ShaderError shaderError = new ShaderError(2, "Cg shader link/load error");
/*      */         
/* 3290 */         shaderError.setDetailMessage(getCgErrorLog((JoglContext)paramContext, i));
/*      */         
/* 3292 */         return shaderError;
/*      */       } 
/*      */       
/* 3295 */       CgGL.cgGLBindProgram(joglCgShaderInfo.getCgShader());
/* 3296 */       if ((i = CgGL.cgGetError()) != 0) {
/* 3297 */         ShaderError shaderError = new ShaderError(2, "Cg shader link/bind error");
/*      */         
/* 3299 */         shaderError.setDetailMessage(getCgErrorLog((JoglContext)paramContext, i));
/*      */         
/* 3301 */         return shaderError;
/*      */       } 
/*      */     } 
/*      */     
/* 3305 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void lookupCgVertexAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, int paramInt, String[] paramArrayOfString, boolean[] paramArrayOfBoolean) {
/* 3311 */     JoglCgShaderProgramInfo joglCgShaderProgramInfo = (JoglCgShaderProgramInfo)paramShaderProgramId;
/* 3312 */     if (joglCgShaderProgramInfo.getVertexShader() == null) {
/*      */       
/* 3314 */       for (byte b1 = 0; b1 < paramArrayOfBoolean.length; b1++) {
/* 3315 */         paramArrayOfBoolean[b1] = false;
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 3320 */     joglCgShaderProgramInfo.setVertexAttributes(new CGparameter[paramInt]);
/* 3321 */     for (byte b = 0; b < paramInt; b++) {
/* 3322 */       String str = paramArrayOfString[b];
/* 3323 */       joglCgShaderProgramInfo.getVertexAttributes()[b] = CgGL.cgGetNamedParameter(joglCgShaderProgramInfo.getVertexShader().getCgShader(), str);
/*      */ 
/*      */       
/* 3326 */       if (joglCgShaderProgramInfo.getVertexAttributes()[b] == null) {
/* 3327 */         paramArrayOfBoolean[b] = true;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void lookupCgShaderAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, int paramInt, String[] paramArrayOfString, ShaderAttrLoc[] paramArrayOfShaderAttrLoc, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean) {
/* 3336 */     JoglCgShaderProgramInfo joglCgShaderProgramInfo = (JoglCgShaderProgramInfo)paramShaderProgramId;
/*      */ 
/*      */ 
/*      */     
/* 3340 */     for (byte b1 = 0; b1 < paramInt; b1++) {
/* 3341 */       paramArrayOfShaderAttrLoc[b1] = null;
/* 3342 */       paramArrayOfInt1[b1] = -1;
/* 3343 */       paramArrayOfInt2[b1] = -1;
/*      */     } 
/*      */     
/* 3346 */     int[] arrayOfInt1 = new int[1];
/* 3347 */     int[] arrayOfInt2 = new int[1];
/* 3348 */     boolean[] arrayOfBoolean1 = new boolean[1];
/* 3349 */     int[] arrayOfInt3 = new int[1];
/* 3350 */     int[] arrayOfInt4 = new int[1];
/* 3351 */     boolean[] arrayOfBoolean2 = new boolean[1];
/*      */     
/* 3353 */     boolean bool = false;
/*      */ 
/*      */     
/* 3356 */     for (byte b2 = 0; b2 < paramInt; b2++) {
/* 3357 */       String str = paramArrayOfString[b2];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3372 */       CGparameter cGparameter1 = null;
/* 3373 */       if (joglCgShaderProgramInfo.getVertexShader() != null) {
/* 3374 */         cGparameter1 = lookupCgParams(joglCgShaderProgramInfo.getVertexShader(), str, arrayOfInt1, arrayOfInt2, arrayOfBoolean1);
/*      */ 
/*      */         
/* 3377 */         if (cGparameter1 != null) {
/* 3378 */           paramArrayOfInt2[b2] = arrayOfInt2[0];
/* 3379 */           paramArrayOfBoolean[b2] = arrayOfBoolean1[0];
/* 3380 */           paramArrayOfInt1[b2] = cgToJ3dType(arrayOfInt1[0]);
/*      */         } 
/*      */       } 
/*      */       
/* 3384 */       CGparameter cGparameter2 = null;
/* 3385 */       if (joglCgShaderProgramInfo.getVertexShader() != null) {
/* 3386 */         cGparameter2 = lookupCgParams(joglCgShaderProgramInfo.getFragmentShader(), str, arrayOfInt3, arrayOfInt4, arrayOfBoolean2);
/*      */ 
/*      */         
/* 3389 */         if (cGparameter2 != null) {
/* 3390 */           paramArrayOfInt2[b2] = arrayOfInt4[0];
/* 3391 */           paramArrayOfBoolean[b2] = arrayOfBoolean2[0];
/* 3392 */           paramArrayOfInt1[b2] = cgToJ3dType(arrayOfInt3[0]);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3399 */       if (cGparameter1 != null && cGparameter2 != null && (
/* 3400 */         arrayOfInt1 != arrayOfInt3 || arrayOfInt2 != arrayOfInt4 || arrayOfBoolean1 != arrayOfBoolean2)) {
/*      */         
/* 3402 */         System.err.println("JAVA 3D : error shader attribute type mismatch: " + str);
/* 3403 */         System.err.println("    1 : type = " + arrayOfInt1[0] + ", size = " + arrayOfInt2[0] + ", isArray = " + arrayOfBoolean1[0]);
/* 3404 */         System.err.println("    0 : type = " + arrayOfInt3[0] + ", size = " + arrayOfInt4[0] + ", isArray = " + arrayOfBoolean2[0]);
/* 3405 */         bool = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3411 */       if (!bool && (cGparameter1 != null || cGparameter2 != null))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3420 */         paramArrayOfShaderAttrLoc[b2] = new JoglCgShaderParameter(cGparameter1, cGparameter2);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError useCgShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) {
/* 3428 */     JoglCgShaderProgramInfo joglCgShaderProgramInfo = (JoglCgShaderProgramInfo)paramShaderProgramId;
/*      */     
/* 3430 */     JoglContext joglContext = (JoglContext)paramContext;
/*      */ 
/*      */     
/* 3433 */     CgGL.cgGLDisableProfile(joglContext.getCgVertexProfile());
/* 3434 */     CgGL.cgGLDisableProfile(joglContext.getCgFragmentProfile());
/* 3435 */     if (joglCgShaderProgramInfo != null) {
/* 3436 */       if (joglCgShaderProgramInfo.getVertexShader() != null) {
/* 3437 */         CgGL.cgGLBindProgram(joglCgShaderProgramInfo.getVertexShader().getCgShader());
/* 3438 */         CgGL.cgGLEnableProfile(joglCgShaderProgramInfo.getVertexShader().getShaderProfile());
/*      */       } else {
/* 3440 */         CgGL.cgGLUnbindProgram(joglContext.getCgVertexProfile());
/*      */       } 
/*      */       
/* 3443 */       if (joglCgShaderProgramInfo.getFragmentShader() != null) {
/* 3444 */         CgGL.cgGLBindProgram(joglCgShaderProgramInfo.getFragmentShader().getCgShader());
/* 3445 */         CgGL.cgGLEnableProfile(joglCgShaderProgramInfo.getFragmentShader().getShaderProfile());
/*      */       } else {
/* 3447 */         CgGL.cgGLUnbindProgram(joglContext.getCgFragmentProfile());
/*      */       } 
/*      */     } else {
/* 3450 */       CgGL.cgGLUnbindProgram(joglContext.getCgVertexProfile());
/* 3451 */       CgGL.cgGLUnbindProgram(joglContext.getCgFragmentProfile());
/*      */     } 
/*      */     
/* 3454 */     joglContext.setShaderProgram(joglCgShaderProgramInfo);
/* 3455 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getCgErrorLog(JoglContext paramJoglContext, int paramInt) {
/* 3462 */     if (paramInt == 0)
/* 3463 */       throw new AssertionError("lastError == 0"); 
/* 3464 */     String str1 = CgGL.cgGetErrorString(paramInt);
/* 3465 */     String str2 = CgGL.cgGetLastListing(paramJoglContext.getCgContext());
/* 3466 */     return str1 + System.getProperty("line.separator") + str2;
/*      */   }
/*      */   
/*      */   private int cgToJ3dType(int paramInt) {
/* 3470 */     switch (paramInt) {
/*      */       case 1025:
/*      */       case 1070:
/*      */       case 1090:
/*      */       case 1092:
/*      */       case 1093:
/*      */       case 1094:
/*      */       case 1114:
/*      */       case 1115:
/* 3479 */         return 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1026:
/*      */       case 1071:
/*      */       case 1095:
/*      */       case 1116:
/* 3494 */         return 2;
/*      */       
/*      */       case 1027:
/*      */       case 1072:
/*      */       case 1096:
/*      */       case 1117:
/* 3500 */         return 4;
/*      */       
/*      */       case 1028:
/*      */       case 1073:
/*      */       case 1097:
/*      */       case 1118:
/* 3506 */         return 6;
/*      */       
/*      */       case 1045:
/*      */       case 1091:
/* 3510 */         return 1;
/*      */       
/*      */       case 1046:
/* 3513 */         return 3;
/*      */       
/*      */       case 1047:
/* 3516 */         return 5;
/*      */       
/*      */       case 1048:
/* 3519 */         return 7;
/*      */       
/*      */       case 1059:
/* 3522 */         return 8;
/*      */       
/*      */       case 1064:
/* 3525 */         return 9;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3533 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private CGparameter lookupCgParams(JoglCgShaderInfo paramJoglCgShaderInfo, String paramString, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean) {
/* 3541 */     CGparameter cGparameter = CgGL.cgGetNamedParameter(paramJoglCgShaderInfo.getCgShader(), paramString);
/* 3542 */     if (cGparameter != null) {
/* 3543 */       paramArrayOfInt1[0] = CgGL.cgGetParameterType(cGparameter);
/* 3544 */       if (paramArrayOfInt1[0] == 2) {
/* 3545 */         paramArrayOfBoolean[0] = true;
/* 3546 */         paramArrayOfInt2[0] = CgGL.cgGetArraySize(cGparameter, 0);
/* 3547 */         CGparameter cGparameter1 = CgGL.cgGetArrayParameter(cGparameter, 0);
/* 3548 */         paramArrayOfInt1[0] = CgGL.cgGetParameterType(cGparameter1);
/*      */       } else {
/* 3550 */         paramArrayOfBoolean[0] = false;
/* 3551 */         paramArrayOfInt2[0] = 1;
/*      */       } 
/*      */     } 
/* 3554 */     return cGparameter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform1i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt) {
/* 3573 */     context(paramContext).getGL().glUniform1iARB(unbox(paramShaderAttrLoc), paramInt);
/* 3574 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform1f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float paramFloat) {
/* 3583 */     context(paramContext).getGL().glUniform1fARB(unbox(paramShaderAttrLoc), paramFloat);
/* 3584 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform2i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) {
/* 3593 */     context(paramContext).getGL().glUniform2iARB(unbox(paramShaderAttrLoc), paramArrayOfInt[0], paramArrayOfInt[1]);
/* 3594 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform2f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) {
/* 3603 */     context(paramContext).getGL().glUniform2fARB(unbox(paramShaderAttrLoc), paramArrayOfFloat[0], paramArrayOfFloat[1]);
/* 3604 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform3i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) {
/* 3613 */     context(paramContext).getGL().glUniform3iARB(unbox(paramShaderAttrLoc), paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
/* 3614 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) {
/* 3623 */     context(paramContext).getGL().glUniform3fARB(unbox(paramShaderAttrLoc), paramArrayOfFloat[0], paramArrayOfFloat[1], paramArrayOfFloat[2]);
/* 3624 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform4i(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int[] paramArrayOfInt) {
/* 3633 */     context(paramContext).getGL().glUniform4iARB(unbox(paramShaderAttrLoc), paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2], paramArrayOfInt[3]);
/* 3634 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) {
/* 3643 */     context(paramContext).getGL().glUniform4fARB(unbox(paramShaderAttrLoc), paramArrayOfFloat[0], paramArrayOfFloat[1], paramArrayOfFloat[2], paramArrayOfFloat[3]);
/* 3644 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniformMatrix3f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) {
/* 3655 */     context(paramContext).getGL().glUniformMatrix3fvARB(unbox(paramShaderAttrLoc), 1, true, paramArrayOfFloat, 0);
/* 3656 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniformMatrix4f(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, float[] paramArrayOfFloat) {
/* 3667 */     context(paramContext).getGL().glUniformMatrix4fvARB(unbox(paramShaderAttrLoc), 1, true, paramArrayOfFloat, 0);
/* 3668 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform1iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) {
/* 3680 */     context(paramContext).getGL().glUniform1ivARB(unbox(paramShaderAttrLoc), paramInt, paramArrayOfInt, 0);
/* 3681 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform1fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) {
/* 3691 */     context(paramContext).getGL().glUniform1fvARB(unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat, 0);
/* 3692 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform2iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) {
/* 3702 */     context(paramContext).getGL().glUniform2ivARB(unbox(paramShaderAttrLoc), paramInt, paramArrayOfInt, 0);
/* 3703 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform2fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) {
/* 3713 */     context(paramContext).getGL().glUniform2fvARB(unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat, 0);
/* 3714 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform3iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) {
/* 3724 */     context(paramContext).getGL().glUniform3ivARB(unbox(paramShaderAttrLoc), paramInt, paramArrayOfInt, 0);
/* 3725 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) {
/* 3735 */     context(paramContext).getGL().glUniform3fvARB(unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat, 0);
/* 3736 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform4iArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, int[] paramArrayOfInt) {
/* 3746 */     context(paramContext).getGL().glUniform4ivARB(unbox(paramShaderAttrLoc), paramInt, paramArrayOfInt, 0);
/* 3747 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniform4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) {
/* 3757 */     context(paramContext).getGL().glUniform4fvARB(unbox(paramShaderAttrLoc), paramInt, paramArrayOfFloat, 0);
/* 3758 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniformMatrix3fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) {
/* 3770 */     context(paramContext).getGL().glUniformMatrix3fvARB(unbox(paramShaderAttrLoc), paramInt, true, paramArrayOfFloat, 0);
/* 3771 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError setGLSLUniformMatrix4fArray(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderAttrLoc paramShaderAttrLoc, int paramInt, float[] paramArrayOfFloat) {
/* 3783 */     context(paramContext).getGL().glUniformMatrix4fvARB(unbox(paramShaderAttrLoc), paramInt, true, paramArrayOfFloat, 0);
/* 3784 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError createGLSLShader(Context paramContext, int paramInt, ShaderId[] paramArrayOfShaderId) {
/* 3791 */     GL gL = context(paramContext).getGL();
/*      */     
/* 3793 */     int i = 0;
/* 3794 */     if (paramInt == 1) {
/* 3795 */       i = gL.glCreateShaderObjectARB(35633);
/* 3796 */     } else if (paramInt == 2) {
/* 3797 */       i = gL.glCreateShaderObjectARB(35632);
/*      */     } 
/*      */     
/* 3800 */     if (i == 0) {
/* 3801 */       return new ShaderError(1, "Unable to create native shader object");
/*      */     }
/*      */ 
/*      */     
/* 3805 */     paramArrayOfShaderId[0] = new JoglShaderObject(i);
/* 3806 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   ShaderError destroyGLSLShader(Context paramContext, ShaderId paramShaderId) {
/* 3811 */     GL gL = context(paramContext).getGL();
/* 3812 */     gL.glDeleteObjectARB(unbox(paramShaderId));
/* 3813 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   ShaderError compileGLSLShader(Context paramContext, ShaderId paramShaderId, String paramString) {
/* 3818 */     int i = unbox(paramShaderId);
/* 3819 */     if (i == 0) {
/* 3820 */       throw new AssertionError("shaderId == 0");
/*      */     }
/*      */     
/* 3823 */     if (paramString == null) {
/* 3824 */       throw new AssertionError("shader program string is null");
/*      */     }
/*      */     
/* 3827 */     GL gL = context(paramContext).getGL();
/* 3828 */     gL.glShaderSourceARB(i, 1, new String[] { paramString }, null, 0);
/* 3829 */     gL.glCompileShaderARB(i);
/* 3830 */     int[] arrayOfInt = new int[1];
/* 3831 */     gL.glGetObjectParameterivARB(i, 35713, arrayOfInt, 0);
/* 3832 */     if (arrayOfInt[0] == 0) {
/* 3833 */       String str = getInfoLog(gL, i);
/* 3834 */       ShaderError shaderError = new ShaderError(1, "GLSL shader compile error");
/*      */       
/* 3836 */       shaderError.setDetailMessage(str);
/* 3837 */       return shaderError;
/*      */     } 
/* 3839 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError createGLSLShaderProgram(Context paramContext, ShaderProgramId[] paramArrayOfShaderProgramId) {
/* 3845 */     GL gL = context(paramContext).getGL();
/*      */     
/* 3847 */     int i = gL.glCreateProgramObjectARB();
/* 3848 */     if (i == 0) {
/* 3849 */       return new ShaderError(2, "Unable to create native shader program object");
/*      */     }
/*      */     
/* 3852 */     paramArrayOfShaderProgramId[0] = new JoglShaderObject(i);
/* 3853 */     return null;
/*      */   }
/*      */   
/*      */   ShaderError destroyGLSLShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) {
/* 3857 */     context(paramContext).getGL().glDeleteObjectARB(unbox(paramShaderProgramId));
/* 3858 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError linkGLSLShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId, ShaderId[] paramArrayOfShaderId) {
/* 3864 */     GL gL = context(paramContext).getGL();
/* 3865 */     int i = unbox(paramShaderProgramId);
/* 3866 */     for (byte b = 0; b < paramArrayOfShaderId.length; b++) {
/* 3867 */       gL.glAttachObjectARB(i, unbox(paramArrayOfShaderId[b]));
/*      */     }
/* 3869 */     gL.glLinkProgramARB(i);
/* 3870 */     int[] arrayOfInt = new int[1];
/* 3871 */     gL.glGetObjectParameterivARB(i, 35714, arrayOfInt, 0);
/* 3872 */     if (arrayOfInt[0] == 0) {
/* 3873 */       String str = getInfoLog(gL, i);
/* 3874 */       ShaderError shaderError = new ShaderError(2, "GLSL shader program link error");
/*      */       
/* 3876 */       shaderError.setDetailMessage(str);
/* 3877 */       return shaderError;
/*      */     } 
/* 3879 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError bindGLSLVertexAttrName(Context paramContext, ShaderProgramId paramShaderProgramId, String paramString, int paramInt) {
/* 3885 */     JoglContext joglContext = (JoglContext)paramContext;
/* 3886 */     context(paramContext).getGL().glBindAttribLocationARB(unbox(paramShaderProgramId), paramInt + VirtualUniverse.mc.glslVertexAttrOffset, paramString);
/*      */ 
/*      */     
/* 3889 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void lookupGLSLShaderAttrNames(Context paramContext, ShaderProgramId paramShaderProgramId, int paramInt, String[] paramArrayOfString, ShaderAttrLoc[] paramArrayOfShaderAttrLoc, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean) {
/*      */     int i;
/* 3897 */     for (i = 0; i < paramArrayOfString.length; i++) {
/* 3898 */       paramArrayOfShaderAttrLoc[i] = null;
/* 3899 */       paramArrayOfInt1[i] = -1;
/* 3900 */       paramArrayOfInt2[i] = -1;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3912 */     i = unbox(paramShaderProgramId);
/* 3913 */     int[] arrayOfInt1 = new int[1];
/* 3914 */     int[] arrayOfInt2 = new int[1];
/* 3915 */     int[] arrayOfInt3 = new int[1];
/* 3916 */     GL gL = context(paramContext).getGL();
/* 3917 */     gL.glGetObjectParameterivARB(i, 35718, arrayOfInt1, 0);
/*      */ 
/*      */     
/* 3920 */     int j = arrayOfInt1[0];
/* 3921 */     gL.glGetObjectParameterivARB(i, 35719, arrayOfInt1, 0);
/*      */ 
/*      */     
/* 3924 */     int k = arrayOfInt1[0];
/* 3925 */     byte[] arrayOfByte = new byte[k];
/*      */     byte b;
/* 3927 */     for (b = 0; b < j; b++) {
/* 3928 */       gL.glGetActiveUniformARB(i, b, k, arrayOfInt3, 0, arrayOfInt1, 0, arrayOfInt2, 0, arrayOfByte, 0);
/*      */ 
/*      */ 
/*      */       
/* 3932 */       int m = arrayOfInt1[0];
/* 3933 */       int n = arrayOfInt2[0];
/* 3934 */       String str = null;
/*      */       
/*      */       try {
/* 3937 */         str = new String(arrayOfByte, 0, arrayOfInt3[0], "US-ASCII");
/* 3938 */       } catch (UnsupportedEncodingException unsupportedEncodingException) {
/* 3939 */         throw new RuntimeException(unsupportedEncodingException);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 3944 */       if (str.length() >= 3 && str.endsWith("]")) {
/* 3945 */         if (str.endsWith("[0]")) {
/* 3946 */           str = str.substring(0, str.length() - 3);
/*      */         } else {
/*      */           continue;
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3954 */       for (byte b1 = 0; b1 < paramInt; b1++) {
/* 3955 */         if (str.equals(paramArrayOfString[b1])) {
/* 3956 */           paramArrayOfInt2[b1] = m;
/* 3957 */           paramArrayOfBoolean[b1] = (m > 1);
/* 3958 */           paramArrayOfInt1[b1] = glslToJ3dType(n);
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       continue;
/*      */     } 
/* 3965 */     for (b = 0; b < paramInt; b++) {
/*      */       
/* 3967 */       int m = gL.glGetUniformLocationARB(i, paramArrayOfString[b]);
/* 3968 */       paramArrayOfShaderAttrLoc[b] = new JoglShaderObject(m);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   ShaderError useGLSLShaderProgram(Context paramContext, ShaderProgramId paramShaderProgramId) {
/* 3975 */     context(paramContext).getGL().glUseProgramObjectARB(unbox(paramShaderProgramId));
/* 3976 */     ((JoglContext)paramContext).setShaderProgram((JoglShaderObject)paramShaderProgramId);
/* 3977 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int unbox(ShaderAttrLoc paramShaderAttrLoc) {
/* 3984 */     if (paramShaderAttrLoc == null)
/* 3985 */       return 0; 
/* 3986 */     return ((JoglShaderObject)paramShaderAttrLoc).getValue();
/*      */   }
/*      */   
/*      */   private int unbox(ShaderProgramId paramShaderProgramId) {
/* 3990 */     if (paramShaderProgramId == null)
/* 3991 */       return 0; 
/* 3992 */     return ((JoglShaderObject)paramShaderProgramId).getValue();
/*      */   }
/*      */   
/*      */   private int unbox(ShaderId paramShaderId) {
/* 3996 */     if (paramShaderId == null)
/* 3997 */       return 0; 
/* 3998 */     return ((JoglShaderObject)paramShaderId).getValue();
/*      */   }
/*      */   
/*      */   private String getInfoLog(GL paramGL, int paramInt) {
/* 4002 */     int[] arrayOfInt = new int[1];
/* 4003 */     paramGL.glGetObjectParameterivARB(paramInt, 35716, arrayOfInt, 0);
/* 4004 */     if (arrayOfInt[0] > 0) {
/* 4005 */       byte[] arrayOfByte = new byte[arrayOfInt[0]];
/* 4006 */       int[] arrayOfInt1 = new int[1];
/* 4007 */       paramGL.glGetInfoLogARB(paramInt, arrayOfInt[0], arrayOfInt1, 0, arrayOfByte, 0);
/*      */       
/*      */       try {
/* 4010 */         return new String(arrayOfByte, 0, arrayOfInt1[0], "US-ASCII");
/* 4011 */       } catch (UnsupportedEncodingException unsupportedEncodingException) {
/* 4012 */         throw new RuntimeException(unsupportedEncodingException);
/*      */       } 
/*      */     } 
/* 4015 */     return null;
/*      */   }
/*      */   
/*      */   private int glslToJ3dType(int paramInt) {
/* 4019 */     switch (paramInt) {
/*      */       case 5124:
/*      */       case 35670:
/*      */       case 35678:
/*      */       case 35679:
/*      */       case 35680:
/* 4025 */         return 0;
/*      */       
/*      */       case 5126:
/* 4028 */         return 1;
/*      */       
/*      */       case 35667:
/*      */       case 35671:
/* 4032 */         return 2;
/*      */       
/*      */       case 35664:
/* 4035 */         return 3;
/*      */       
/*      */       case 35668:
/*      */       case 35672:
/* 4039 */         return 4;
/*      */       
/*      */       case 35665:
/* 4042 */         return 5;
/*      */       
/*      */       case 35669:
/*      */       case 35673:
/* 4046 */         return 6;
/*      */       
/*      */       case 35666:
/* 4049 */         return 7;
/*      */ 
/*      */ 
/*      */       
/*      */       case 35675:
/* 4054 */         return 8;
/*      */       
/*      */       case 35676:
/* 4057 */         return 9;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4068 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void cleanupRenderer() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateColoringAttributes(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean, int paramInt) {
/*      */     float f3, f2, f1;
/* 4096 */     GL gL = context(paramContext).getGL();
/*      */ 
/*      */ 
/*      */     
/* 4100 */     if (paramBoolean) {
/* 4101 */       f1 = paramFloat1; f2 = paramFloat2; f3 = paramFloat3;
/*      */     } else {
/* 4103 */       f1 = paramFloat4; f2 = paramFloat5; f3 = paramFloat6;
/*      */     } 
/* 4105 */     gL.glColor4f(f1, f2, f3, paramFloat7);
/* 4106 */     if (paramInt == 2) {
/* 4107 */       gL.glShadeModel(7424);
/*      */     } else {
/* 4109 */       gL.glShadeModel(7425);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 4120 */   private static final float[] black = new float[4];
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateDirectionalLight(Context paramContext, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
/* 4126 */     GL gL = context(paramContext).getGL();
/*      */     
/* 4128 */     int i = 16384 + paramInt;
/* 4129 */     float[] arrayOfFloat = new float[4];
/*      */     
/* 4131 */     arrayOfFloat[0] = paramFloat1;
/* 4132 */     arrayOfFloat[1] = paramFloat2;
/* 4133 */     arrayOfFloat[2] = paramFloat3;
/* 4134 */     arrayOfFloat[3] = 1.0F;
/* 4135 */     gL.glLightfv(i, 4609, arrayOfFloat, 0);
/* 4136 */     gL.glLightfv(i, 4610, arrayOfFloat, 0);
/* 4137 */     arrayOfFloat[0] = -paramFloat4;
/* 4138 */     arrayOfFloat[1] = -paramFloat5;
/* 4139 */     arrayOfFloat[2] = -paramFloat6;
/* 4140 */     arrayOfFloat[3] = 0.0F;
/* 4141 */     gL.glLightfv(i, 4611, arrayOfFloat, 0);
/* 4142 */     gL.glLightfv(i, 4608, black, 0);
/* 4143 */     gL.glLightf(i, 4615, 1.0F);
/* 4144 */     gL.glLightf(i, 4616, 0.0F);
/* 4145 */     gL.glLightf(i, 4617, 0.0F);
/* 4146 */     gL.glLightf(i, 4613, 0.0F);
/* 4147 */     gL.glLightf(i, 4614, 180.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updatePointLight(Context paramContext, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9) {
/* 4163 */     GL gL = context(paramContext).getGL();
/*      */     
/* 4165 */     int i = 16384 + paramInt;
/* 4166 */     float[] arrayOfFloat = new float[4];
/*      */     
/* 4168 */     arrayOfFloat[0] = paramFloat1;
/* 4169 */     arrayOfFloat[1] = paramFloat2;
/* 4170 */     arrayOfFloat[2] = paramFloat3;
/* 4171 */     arrayOfFloat[3] = 1.0F;
/* 4172 */     gL.glLightfv(i, 4609, arrayOfFloat, 0);
/* 4173 */     gL.glLightfv(i, 4610, arrayOfFloat, 0);
/* 4174 */     gL.glLightfv(i, 4608, black, 0);
/* 4175 */     arrayOfFloat[0] = paramFloat7;
/* 4176 */     arrayOfFloat[1] = paramFloat8;
/* 4177 */     arrayOfFloat[2] = paramFloat9;
/* 4178 */     gL.glLightfv(i, 4611, arrayOfFloat, 0);
/* 4179 */     gL.glLightf(i, 4615, paramFloat4);
/* 4180 */     gL.glLightf(i, 4616, paramFloat5);
/* 4181 */     gL.glLightf(i, 4617, paramFloat6);
/* 4182 */     gL.glLightf(i, 4613, 0.0F);
/* 4183 */     gL.glLightf(i, 4614, 180.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateSpotLight(Context paramContext, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14) {
/* 4201 */     GL gL = context(paramContext).getGL();
/*      */     
/* 4203 */     int i = 16384 + paramInt;
/* 4204 */     float[] arrayOfFloat = new float[4];
/*      */     
/* 4206 */     arrayOfFloat[0] = paramFloat1;
/* 4207 */     arrayOfFloat[1] = paramFloat2;
/* 4208 */     arrayOfFloat[2] = paramFloat3;
/* 4209 */     arrayOfFloat[3] = 1.0F;
/* 4210 */     gL.glLightfv(i, 4609, arrayOfFloat, 0);
/* 4211 */     gL.glLightfv(i, 4610, arrayOfFloat, 0);
/* 4212 */     gL.glLightfv(i, 4608, black, 0);
/* 4213 */     arrayOfFloat[0] = paramFloat7;
/* 4214 */     arrayOfFloat[1] = paramFloat8;
/* 4215 */     arrayOfFloat[2] = paramFloat9;
/* 4216 */     gL.glLightfv(i, 4611, arrayOfFloat, 0);
/* 4217 */     gL.glLightf(i, 4615, paramFloat4);
/* 4218 */     gL.glLightf(i, 4616, paramFloat5);
/* 4219 */     gL.glLightf(i, 4617, paramFloat6);
/* 4220 */     arrayOfFloat[0] = paramFloat12;
/* 4221 */     arrayOfFloat[1] = paramFloat13;
/* 4222 */     arrayOfFloat[2] = paramFloat14;
/* 4223 */     gL.glLightfv(i, 4612, arrayOfFloat, 0);
/* 4224 */     gL.glLightf(i, 4613, paramFloat11);
/* 4225 */     gL.glLightf(i, 4614, (float)((paramFloat10 * 180.0F) / Math.PI));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateExponentialFog(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
/* 4240 */     GL gL = context(paramContext).getGL();
/*      */     
/* 4242 */     float[] arrayOfFloat = new float[3];
/* 4243 */     arrayOfFloat[0] = paramFloat1;
/* 4244 */     arrayOfFloat[1] = paramFloat2;
/* 4245 */     arrayOfFloat[2] = paramFloat3;
/* 4246 */     gL.glFogi(2917, 2048);
/* 4247 */     gL.glFogfv(2918, arrayOfFloat, 0);
/* 4248 */     gL.glFogf(2914, paramFloat4);
/* 4249 */     gL.glEnable(2912);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateLinearFog(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, double paramDouble1, double paramDouble2) {
/* 4264 */     GL gL = context(paramContext).getGL();
/*      */     
/* 4266 */     float[] arrayOfFloat = new float[3];
/* 4267 */     arrayOfFloat[0] = paramFloat1;
/* 4268 */     arrayOfFloat[1] = paramFloat2;
/* 4269 */     arrayOfFloat[2] = paramFloat3;
/* 4270 */     gL.glFogi(2917, 9729);
/* 4271 */     gL.glFogfv(2918, arrayOfFloat, 0);
/* 4272 */     gL.glFogf(2915, (float)paramDouble1);
/* 4273 */     gL.glFogf(2916, (float)paramDouble2);
/* 4274 */     gL.glEnable(2912);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateLineAttributes(Context paramContext, float paramFloat, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
/* 4291 */     GL gL = context(paramContext).getGL();
/* 4292 */     gL.glLineWidth(paramFloat);
/*      */     
/* 4294 */     if (paramInt1 == 0) {
/* 4295 */       gL.glDisable(2852);
/*      */     } else {
/* 4297 */       if (paramInt1 == 1) {
/* 4298 */         gL.glLineStipple(1, (short)255);
/* 4299 */       } else if (paramInt1 == 2) {
/* 4300 */         gL.glLineStipple(1, (short)257);
/* 4301 */       } else if (paramInt1 == 3) {
/* 4302 */         gL.glLineStipple(1, (short)2175);
/* 4303 */       } else if (paramInt1 == 4) {
/* 4304 */         gL.glLineStipple(paramInt3, (short)paramInt2);
/*      */       } 
/* 4306 */       gL.glEnable(2852);
/*      */     } 
/*      */ 
/*      */     
/* 4310 */     if (paramBoolean) {
/* 4311 */       gL.glEnable(2848);
/*      */     } else {
/* 4313 */       gL.glDisable(2848);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateMaterial(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16, float paramFloat17, int paramInt, boolean paramBoolean) {
/* 4333 */     float[] arrayOfFloat = new float[4];
/*      */     
/* 4335 */     GL gL = context(paramContext).getGL();
/*      */     
/* 4337 */     gL.glMaterialf(1032, 5633, paramFloat17);
/* 4338 */     switch (paramInt) {
/*      */       case 2:
/* 4340 */         gL.glColorMaterial(1032, 4609);
/*      */         break;
/*      */       case 0:
/* 4343 */         gL.glColorMaterial(1032, 4608);
/*      */         break;
/*      */       case 1:
/* 4346 */         gL.glColorMaterial(1032, 5632);
/*      */         break;
/*      */       case 3:
/* 4349 */         gL.glColorMaterial(1032, 4610);
/*      */         break;
/*      */       case 4:
/* 4352 */         gL.glColorMaterial(1032, 5634);
/*      */         break;
/*      */     } 
/*      */     
/* 4356 */     arrayOfFloat[0] = paramFloat8; arrayOfFloat[1] = paramFloat9; arrayOfFloat[2] = paramFloat10;
/* 4357 */     gL.glMaterialfv(1032, 5632, arrayOfFloat, 0);
/*      */     
/* 4359 */     arrayOfFloat[0] = paramFloat5; arrayOfFloat[1] = paramFloat6; arrayOfFloat[2] = paramFloat7;
/* 4360 */     gL.glMaterialfv(1032, 4608, arrayOfFloat, 0);
/*      */     
/* 4362 */     arrayOfFloat[0] = paramFloat14; arrayOfFloat[1] = paramFloat15; arrayOfFloat[2] = paramFloat16;
/* 4363 */     gL.glMaterialfv(1032, 4610, arrayOfFloat, 0);
/*      */ 
/*      */ 
/*      */     
/* 4367 */     if (paramBoolean) {
/* 4368 */       arrayOfFloat[0] = paramFloat11; arrayOfFloat[1] = paramFloat12; arrayOfFloat[2] = paramFloat13;
/*      */     } else {
/* 4370 */       arrayOfFloat[0] = paramFloat1; arrayOfFloat[1] = paramFloat2; arrayOfFloat[2] = paramFloat3;
/*      */     } 
/* 4372 */     arrayOfFloat[3] = paramFloat4;
/* 4373 */     gL.glMaterialfv(1032, 4609, arrayOfFloat, 0);
/* 4374 */     gL.glColor4f(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3]);
/*      */     
/* 4376 */     if (paramBoolean) {
/* 4377 */       gL.glEnable(2896);
/*      */     } else {
/* 4379 */       gL.glDisable(2896);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateModelClip(Context paramContext, int paramInt, boolean paramBoolean, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/* 4394 */     GL gL = context(paramContext).getGL();
/*      */     
/* 4396 */     double[] arrayOfDouble = new double[4];
/* 4397 */     int i = 12288 + paramInt;
/*      */ 
/*      */     
/* 4400 */     if (paramBoolean) {
/* 4401 */       arrayOfDouble[0] = -paramDouble1;
/* 4402 */       arrayOfDouble[1] = -paramDouble2;
/* 4403 */       arrayOfDouble[2] = -paramDouble3;
/* 4404 */       arrayOfDouble[3] = -paramDouble4;
/* 4405 */       gL.glClipPlane(i, DoubleBuffer.wrap(arrayOfDouble));
/* 4406 */       gL.glEnable(i);
/*      */     } else {
/* 4408 */       gL.glDisable(i);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updatePointAttributes(Context paramContext, float paramFloat, boolean paramBoolean) {
/* 4422 */     GL gL = context(paramContext).getGL();
/* 4423 */     gL.glPointSize(paramFloat);
/*      */ 
/*      */     
/* 4426 */     if (paramBoolean) {
/* 4427 */       gL.glEnable(2832);
/*      */     } else {
/* 4429 */       gL.glDisable(2832);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updatePolygonAttributes(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean, float paramFloat1, float paramFloat2) {
/* 4447 */     GL gL = context(paramContext).getGL();
/*      */     
/* 4449 */     if (paramInt2 == 0) {
/* 4450 */       gL.glDisable(2884);
/*      */     } else {
/* 4452 */       if (paramInt2 == 1) {
/* 4453 */         gL.glCullFace(1029);
/*      */       } else {
/* 4455 */         gL.glCullFace(1028);
/*      */       } 
/* 4457 */       gL.glEnable(2884);
/*      */     } 
/*      */     
/* 4460 */     if (paramBoolean && paramInt2 != 1) {
/* 4461 */       gL.glLightModeli(2898, 1);
/*      */     } else {
/* 4463 */       gL.glLightModeli(2898, 0);
/*      */     } 
/*      */     
/* 4466 */     if (paramInt1 == 0) {
/* 4467 */       gL.glPolygonMode(1032, 6912);
/* 4468 */     } else if (paramInt1 == 1) {
/* 4469 */       gL.glPolygonMode(1032, 6913);
/*      */     } else {
/* 4471 */       gL.glPolygonMode(1032, 6914);
/*      */     } 
/*      */     
/* 4474 */     gL.glPolygonOffset(paramFloat2, paramFloat1);
/*      */     
/* 4476 */     if (paramFloat2 != 0.0D || paramFloat1 != 0.0D) {
/* 4477 */       switch (paramInt1) {
/*      */         case 0:
/* 4479 */           gL.glEnable(10753);
/* 4480 */           gL.glDisable(10754);
/* 4481 */           gL.glDisable(32823);
/*      */           break;
/*      */         case 1:
/* 4484 */           gL.glEnable(10754);
/* 4485 */           gL.glDisable(10753);
/* 4486 */           gL.glDisable(32823);
/*      */           break;
/*      */         case 2:
/* 4489 */           gL.glEnable(32823);
/* 4490 */           gL.glDisable(10753);
/* 4491 */           gL.glDisable(10754);
/*      */           break;
/*      */       } 
/*      */     } else {
/* 4495 */       gL.glDisable(10753);
/* 4496 */       gL.glDisable(10754);
/* 4497 */       gL.glDisable(32823);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateRenderingAttributes(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, int paramInt1, float paramFloat, int paramInt2, boolean paramBoolean5, boolean paramBoolean6, int paramInt3, boolean paramBoolean7, boolean paramBoolean8, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10) {
/* 4523 */     GL gL = context(paramContext).getGL();
/*      */     
/* 4525 */     if (!paramBoolean2) {
/* 4526 */       if (paramBoolean3) {
/* 4527 */         gL.glEnable(2929);
/* 4528 */         gL.glDepthFunc(getFunctionValue(paramInt1));
/*      */       } else {
/* 4530 */         gL.glDisable(2929);
/*      */       } 
/*      */     }
/*      */     
/* 4534 */     if (!paramBoolean1) {
/* 4535 */       if (paramBoolean4) {
/* 4536 */         gL.glDepthMask(true);
/*      */       } else {
/* 4538 */         gL.glDepthMask(false);
/*      */       } 
/*      */     }
/*      */     
/* 4542 */     if (paramInt2 == 0) {
/* 4543 */       gL.glDisable(3008);
/*      */     } else {
/* 4545 */       gL.glEnable(3008);
/* 4546 */       gL.glAlphaFunc(getFunctionValue(paramInt2), paramFloat);
/*      */     } 
/*      */     
/* 4549 */     if (paramBoolean5) {
/* 4550 */       gL.glDisable(2903);
/*      */     } else {
/* 4552 */       gL.glEnable(2903);
/*      */     } 
/*      */     
/* 4555 */     if (paramBoolean6) {
/* 4556 */       gL.glEnable(3058);
/* 4557 */       switch (paramInt3) {
/*      */         case 0:
/* 4559 */           gL.glLogicOp(5376);
/*      */           break;
/*      */         case 1:
/* 4562 */           gL.glLogicOp(5377);
/*      */           break;
/*      */         case 2:
/* 4565 */           gL.glLogicOp(5378);
/*      */           break;
/*      */         case 3:
/* 4568 */           gL.glLogicOp(5379);
/*      */           break;
/*      */         case 4:
/* 4571 */           gL.glLogicOp(5380);
/*      */           break;
/*      */         case 5:
/* 4574 */           gL.glLogicOp(5381);
/*      */           break;
/*      */         case 6:
/* 4577 */           gL.glLogicOp(5382);
/*      */           break;
/*      */         case 7:
/* 4580 */           gL.glLogicOp(5383);
/*      */           break;
/*      */         case 8:
/* 4583 */           gL.glLogicOp(5384);
/*      */           break;
/*      */         case 9:
/* 4586 */           gL.glLogicOp(5385);
/*      */           break;
/*      */         case 10:
/* 4589 */           gL.glLogicOp(5386);
/*      */           break;
/*      */         case 11:
/* 4592 */           gL.glLogicOp(5387);
/*      */           break;
/*      */         case 12:
/* 4595 */           gL.glLogicOp(5388);
/*      */           break;
/*      */         case 13:
/* 4598 */           gL.glLogicOp(5389);
/*      */           break;
/*      */         case 14:
/* 4601 */           gL.glLogicOp(5390);
/*      */           break;
/*      */         case 15:
/* 4604 */           gL.glLogicOp(5391);
/*      */           break;
/*      */       } 
/*      */     } else {
/* 4608 */       gL.glDisable(3058);
/*      */     } 
/*      */     
/* 4611 */     if (paramBoolean7) {
/* 4612 */       if (paramBoolean8) {
/* 4613 */         gL.glEnable(2960);
/*      */         
/* 4615 */         gL.glStencilOp(getStencilOpValue(paramInt4), getStencilOpValue(paramInt5), getStencilOpValue(paramInt6));
/*      */ 
/*      */ 
/*      */         
/* 4619 */         gL.glStencilFunc(getFunctionValue(paramInt7), paramInt8, paramInt9);
/*      */ 
/*      */         
/* 4622 */         gL.glStencilMask(paramInt10);
/*      */       } else {
/*      */         
/* 4625 */         gL.glDisable(2960);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private int getFunctionValue(int paramInt) {
/* 4631 */     switch (paramInt) {
/*      */       case 0:
/* 4633 */         paramInt = 519;
/*      */         break;
/*      */       case 1:
/* 4636 */         paramInt = 512;
/*      */         break;
/*      */       case 2:
/* 4639 */         paramInt = 514;
/*      */         break;
/*      */       case 3:
/* 4642 */         paramInt = 517;
/*      */         break;
/*      */       case 4:
/* 4645 */         paramInt = 513;
/*      */         break;
/*      */       case 5:
/* 4648 */         paramInt = 515;
/*      */         break;
/*      */       case 6:
/* 4651 */         paramInt = 516;
/*      */         break;
/*      */       case 7:
/* 4654 */         paramInt = 518;
/*      */         break;
/*      */     } 
/*      */     
/* 4658 */     return paramInt;
/*      */   }
/*      */   
/*      */   private int getStencilOpValue(int paramInt) {
/* 4662 */     switch (paramInt) {
/*      */       case 1:
/* 4664 */         paramInt = 7680;
/*      */         break;
/*      */       case 2:
/* 4667 */         paramInt = 0;
/*      */         break;
/*      */       case 3:
/* 4670 */         paramInt = 7681;
/*      */         break;
/*      */       case 4:
/* 4673 */         paramInt = 7682;
/*      */         break;
/*      */       case 5:
/* 4676 */         paramInt = 7683;
/*      */         break;
/*      */       case 6:
/* 4679 */         paramInt = 5386;
/*      */         break;
/*      */     } 
/*      */     
/* 4683 */     return paramInt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void updateTexCoordGeneration(Context paramContext, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16, double[] paramArrayOfDouble) {
/* 4707 */     GL gL = context(paramContext).getGL();
/*      */     
/* 4709 */     float[] arrayOfFloat1 = new float[4];
/* 4710 */     float[] arrayOfFloat2 = new float[4];
/* 4711 */     float[] arrayOfFloat3 = new float[4];
/* 4712 */     float[] arrayOfFloat4 = new float[4];
/*      */     
/* 4714 */     if (paramBoolean) {
/* 4715 */       gL.glEnable(3168);
/* 4716 */       gL.glEnable(3169);
/* 4717 */       if (paramInt2 == 1) {
/* 4718 */         gL.glEnable(3170);
/* 4719 */         gL.glDisable(3171);
/* 4720 */       } else if (paramInt2 == 2) {
/* 4721 */         gL.glEnable(3170);
/* 4722 */         gL.glEnable(3171);
/*      */       } else {
/* 4724 */         gL.glDisable(3170);
/* 4725 */         gL.glDisable(3171);
/*      */       } 
/*      */       
/* 4728 */       if (paramInt1 != 2) {
/* 4729 */         arrayOfFloat1[0] = paramFloat1; arrayOfFloat1[1] = paramFloat2;
/* 4730 */         arrayOfFloat1[2] = paramFloat3; arrayOfFloat1[3] = paramFloat4;
/* 4731 */         arrayOfFloat2[0] = paramFloat5; arrayOfFloat2[1] = paramFloat6;
/* 4732 */         arrayOfFloat2[2] = paramFloat7; arrayOfFloat2[3] = paramFloat8;
/* 4733 */         if (paramInt2 == 1) {
/* 4734 */           arrayOfFloat3[0] = paramFloat9; arrayOfFloat3[1] = paramFloat10;
/* 4735 */           arrayOfFloat3[2] = paramFloat11; arrayOfFloat3[3] = paramFloat12;
/* 4736 */         } else if (paramInt2 == 2) {
/* 4737 */           arrayOfFloat3[0] = paramFloat9; arrayOfFloat3[1] = paramFloat10;
/* 4738 */           arrayOfFloat3[2] = paramFloat11; arrayOfFloat3[3] = paramFloat12;
/* 4739 */           arrayOfFloat4[0] = paramFloat13; arrayOfFloat4[1] = paramFloat14;
/* 4740 */           arrayOfFloat4[2] = paramFloat15; arrayOfFloat4[3] = paramFloat16;
/*      */         } 
/*      */       } 
/*      */       
/* 4744 */       switch (paramInt1) {
/*      */         case 0:
/* 4746 */           gL.glTexGeni(8192, 9472, 9217);
/* 4747 */           gL.glTexGeni(8193, 9472, 9217);
/* 4748 */           gL.glTexGenfv(8192, 9473, arrayOfFloat1, 0);
/* 4749 */           gL.glTexGenfv(8193, 9473, arrayOfFloat2, 0);
/*      */           
/* 4751 */           if (paramInt2 == 1) {
/* 4752 */             gL.glTexGeni(8194, 9472, 9217);
/* 4753 */             gL.glTexGenfv(8194, 9473, arrayOfFloat3, 0); break;
/* 4754 */           }  if (paramInt2 == 2) {
/* 4755 */             gL.glTexGeni(8194, 9472, 9217);
/* 4756 */             gL.glTexGenfv(8194, 9473, arrayOfFloat3, 0);
/* 4757 */             gL.glTexGeni(8195, 9472, 9217);
/* 4758 */             gL.glTexGenfv(8195, 9473, arrayOfFloat4, 0);
/*      */           } 
/*      */           break;
/*      */         
/*      */         case 1:
/* 4763 */           gL.glMatrixMode(5888);
/* 4764 */           gL.glPushMatrix();
/*      */           
/* 4766 */           if (gL.isExtensionAvailable("GL_VERSION_1_3")) {
/* 4767 */             gL.glLoadTransposeMatrixd(paramArrayOfDouble, 0);
/*      */           } else {
/* 4769 */             double[] arrayOfDouble = new double[16];
/* 4770 */             copyTranspose(paramArrayOfDouble, arrayOfDouble);
/* 4771 */             gL.glLoadMatrixd(arrayOfDouble, 0);
/*      */           } 
/*      */           
/* 4774 */           gL.glTexGeni(8192, 9472, 9216);
/* 4775 */           gL.glTexGeni(8193, 9472, 9216);
/* 4776 */           gL.glTexGenfv(8192, 9474, arrayOfFloat1, 0);
/* 4777 */           gL.glTexGenfv(8193, 9474, arrayOfFloat2, 0);
/*      */           
/* 4779 */           if (paramInt2 == 1) {
/* 4780 */             gL.glTexGeni(8194, 9472, 9216);
/* 4781 */             gL.glTexGenfv(8194, 9474, arrayOfFloat3, 0);
/* 4782 */           } else if (paramInt2 == 2) {
/* 4783 */             gL.glTexGeni(8194, 9472, 9216);
/* 4784 */             gL.glTexGenfv(8194, 9474, arrayOfFloat3, 0);
/* 4785 */             gL.glTexGeni(8195, 9472, 9216);
/* 4786 */             gL.glTexGenfv(8195, 9474, arrayOfFloat4, 0);
/*      */           } 
/* 4788 */           gL.glPopMatrix();
/*      */           break;
/*      */         case 2:
/* 4791 */           gL.glTexGeni(8192, 9472, 9218);
/* 4792 */           gL.glTexGeni(8193, 9472, 9218);
/* 4793 */           if (paramInt2 == 1) {
/* 4794 */             gL.glTexGeni(8194, 9472, 9218); break;
/* 4795 */           }  if (paramInt2 == 2) {
/* 4796 */             gL.glTexGeni(8194, 9472, 9218);
/* 4797 */             gL.glTexGeni(8195, 9472, 9218);
/*      */           } 
/*      */           break;
/*      */         
/*      */         case 3:
/* 4802 */           gL.glTexGeni(8192, 9472, 34065);
/* 4803 */           gL.glTexGeni(8193, 9472, 34065);
/* 4804 */           if (paramInt2 == 1) {
/* 4805 */             gL.glTexGeni(8194, 9472, 34065); break;
/* 4806 */           }  if (paramInt2 == 2) {
/* 4807 */             gL.glTexGeni(8194, 9472, 34065);
/* 4808 */             gL.glTexGeni(8195, 9472, 34065);
/*      */           } 
/*      */           break;
/*      */         case 4:
/* 4812 */           gL.glTexGeni(8192, 9472, 34066);
/* 4813 */           gL.glTexGeni(8193, 9472, 34066);
/* 4814 */           if (paramInt2 == 1) {
/* 4815 */             gL.glTexGeni(8194, 9472, 34066); break;
/* 4816 */           }  if (paramInt2 == 2) {
/* 4817 */             gL.glTexGeni(8194, 9472, 34066);
/* 4818 */             gL.glTexGeni(8195, 9472, 34066);
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     } else {
/* 4823 */       gL.glDisable(3168);
/* 4824 */       gL.glDisable(3169);
/* 4825 */       gL.glDisable(3170);
/* 4826 */       gL.glDisable(3171);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int[][] screen_door = { 
/*      */       { 
/* 4837 */         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 572662306, 0, 0, 0, 572662306, 0, 0, 0, 572662306, 0, 0, 0, 572662306, 0, 0, 0, 572662306, 0, 0, 0, 572662306, 0, 0, 0, 572662306, 0, 0, 0, 572662306, 0, 0 }, { 0, 572662306, 0, -2004318072, 0, 572662306, 0, -2004318072, 0, 572662306, 0, -2004318072, 0, 572662306, 0, -2004318072, 0, 572662306, 0, -2004318072, 0, 572662306, 0, -2004318072, 0, 572662306, 0, -2004318072, 0, 572662306, 0, -2004318072 }, { 0, -1431655766, 0, -2004318072, 0, -1431655766, 0, -2004318072, 0, -1431655766, 0, -2004318072, 0, -1431655766, 0, -2004318072, 0, -1431655766, 0, -2004318072, 0, -1431655766, 0, -2004318072, 0, -1431655766, 0, -2004318072, 0, -1431655766, 0, -2004318072 }, { 0, -1431655766, 0, -1431655766, 0, -1431655766, 0, -1431655766, 0, -1431655766, 0, -1431655766, 0, -1431655766, 0, -1431655766, 0, -1431655766, 0, -1431655766, 0, -1431655766, 0, -1431655766, 0, -1431655766, 0, -1431655766, 0, -1431655766, 0, -1431655766 }, { 286331153, -1431655766, 0, -1431655766, 286331153, -1431655766, 0, -1431655766, 286331153, -1431655766, 0, -1431655766, 286331153, -1431655766, 0, -1431655766, 286331153, -1431655766, 0, -1431655766, 286331153, -1431655766, 0, -1431655766, 286331153, -1431655766, 0, -1431655766, 286331153, -1431655766, 0, -1431655766 }, { 286331153, -1431655766, 1145324612, -1431655766, 286331153, -1431655766, 1145324612, -1431655766, 286331153, -1431655766, 1145324612, -1431655766, 286331153, -1431655766, 1145324612, -1431655766, 286331153, -1431655766, 1145324612, -1431655766, 286331153, -1431655766, 1145324612, -1431655766, 286331153, -1431655766, 1145324612, -1431655766, 286331153, -1431655766, 1145324612, -1431655766 }, { 1431655765, -1431655766, 1145324612, -1431655766, 1431655765, -1431655766, 1145324612, -1431655766, 1431655765, -1431655766, 1145324612, -1431655766, 1431655765, -1431655766, 1145324612, -1431655766, 1431655765, -1431655766, 1145324612, -1431655766, 1431655765, -1431655766, 1145324612, -1431655766, 1431655765, -1431655766, 1145324612, -1431655766, 1431655765, -1431655766, 1145324612, -1431655766 }, { 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766, 1431655765, -1431655766 }, { 2004318071, -1431655766, 1431655765, -1431655766, 2004318071, -1431655766, 1431655765, -1431655766, 2004318071, -1431655766, 1431655765, -1431655766, 2004318071, -1431655766, 1431655765, -1431655766, 2004318071, -1431655766, 1431655765, -1431655766, 2004318071, -1431655766, 1431655765, -1431655766, 2004318071, -1431655766, 1431655765, -1431655766, 2004318071, -1431655766, 1431655765, -1431655766 }, { 2004318071, -1431655766, -572662307, -1431655766, 2004318071, -1431655766, -572662307, -1431655766, 2004318071, -1431655766, -572662307, -1431655766, 2004318071, -1431655766, -572662307, -1431655766, 2004318071, -1431655766, -572662307, -1431655766, 2004318071, -1431655766, -572662307, -1431655766, 2004318071, -1431655766, -572662307, -1431655766, 2004318071, -1431655766, -572662307, -1431655766 }, { -1, -1431655766, -572662307, -1431655766, -1, -1431655766, -572662307, -1431655766, -1, -1431655766, -572662307, -1431655766, -1, -1431655766, -572662307, -1431655766, -1, -1431655766, -572662307, -1431655766, -1, -1431655766, -572662307, -1431655766, -1, -1431655766, -572662307, -1431655766, -1, -1431655766, -572662307, -1431655766 }, { -1, -1431655766, -1, -1431655766, -1, -1431655766, -1, -1431655766, -1, -1431655766, -1, -1431655766, -1, -1431655766, -1, -1431655766, -1, -1431655766, -1, -1431655766, -1, -1431655766, -1, -1431655766, -1, -1431655766, -1, -1431655766, -1, -1431655766, -1, -1431655766 }, { -1, -1145324613, -1, -1431655766, -1, -1145324613, -1, -1431655766, -1, -1145324613, -1, -1431655766, -1, -1145324613, -1, -1431655766, -1, -1145324613, -1, -1431655766, -1, -1145324613, -1, -1431655766, -1, -1145324613, -1, -1431655766, -1, -1145324613, -1, -1431655766 }, { -1, -1145324613, -1, -286331154, -1, -1145324613, -1, -286331154, -1, -1145324613, -1, -286331154, -1, -1145324613, -1, -286331154, -1, -1145324613, -1, -286331154, -1, -1145324613, -1, -286331154, -1, -1145324613, -1, -286331154, -1, -1145324613, -1, -286331154 }, { -1, -1, -1, -286331154, -1, -1, -1, -286331154, -1, -1, -1, -286331154, -1, -1, -1, -286331154, -1, -1, -1, -286331154, -1, -1, -1, -286331154, -1, -1, -1, -286331154, -1, -1, -1, -286331154 }, { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 5026 */   private static final ByteBuffer[] screen_door_table = new ByteBuffer[screen_door.length];
/*      */   private static final int[] blendFunctionTable;
/* 5028 */   private static final int[] _gl_combineRgbSrcIndex; private static final int[] _gl_combineAlphaSrcIndex; private static final int[] _gl_combineRgbOpIndex; private static final int[] _gl_combineAlphaOpIndex; private static final int[] _gl_combineSrc; private static final int[] _gl_combineFcn; private static final int[] _gl_textureCubeMapFace; void updateTransparencyAttributes(Context paramContext, float paramFloat, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, int paramInt3, int paramInt4, int paramInt5) { GL gL = context(paramContext).getGL(); if (paramInt3 != 3) { gL.glDisable(2882); } else { gL.glEnable(2882); gL.glPolygonStipple(screen_door_table[(int)(paramFloat * 16.0F)]); }  if (paramInt3 < 3 || (((paramInt1 & 0x2) != 0 || paramInt2 == 1) && paramBoolean1) || (((paramInt1 & true) != 0 || paramInt2 == 0) && paramBoolean2)) { gL.glEnable(3042); gL.glBlendFunc(blendFunctionTable[paramInt4], blendFunctionTable[paramInt5]); } else { gL.glDisable(3042); }  } void updateTextureAttributes(Context paramContext, double[] paramArrayOfDouble, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt3) { GL gL = context(paramContext).getGL(); gL.glHint(3152, (paramInt2 == 1) ? 4354 : 4353); gL.glPushAttrib(4096); gL.glMatrixMode(5890); if (paramBoolean) { gL.glLoadIdentity(); } else if (gL.isExtensionAvailable("GL_VERSION_1_3")) { gL.glLoadTransposeMatrixd(paramArrayOfDouble, 0); } else { double[] arrayOfDouble = new double[16]; copyTranspose(paramArrayOfDouble, arrayOfDouble); gL.glLoadMatrixd(arrayOfDouble, 0); }  gL.glPopAttrib(); float[] arrayOfFloat = new float[4]; arrayOfFloat[0] = paramFloat1; arrayOfFloat[1] = paramFloat2; arrayOfFloat[2] = paramFloat3; arrayOfFloat[3] = paramFloat4; gL.glTexEnvfv(8960, 8705, arrayOfFloat, 0); switch (paramInt1) { case 2: gL.glTexEnvi(8960, 8704, 8448); break;case 3: gL.glTexEnvi(8960, 8704, 8449); break;case 4: gL.glTexEnvi(8960, 8704, 3042); break;case 5: gL.glTexEnvi(8960, 8704, 7681); break;case 6: gL.glTexEnvi(8960, 8704, 34160); break; }  if (gL.isExtensionAvailable("GL_SGI_texture_color_table")) gL.glDisable(32956);  } void updateRegisterCombiners(Context paramContext, double[] paramArrayOfDouble, boolean paramBoolean, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt3, int paramInt4, int paramInt5, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt6, int paramInt7) { char c; JoglContext joglContext = (JoglContext)paramContext; GL gL = context(joglContext).getGL(); if (paramInt2 == 1) { gL.glHint(3152, 4354); } else { gL.glHint(3152, 4353); }  gL.glPushAttrib(4096); gL.glMatrixMode(5890); if (paramBoolean) { gL.glLoadIdentity(); } else if (gL.isExtensionAvailable("GL_VERSION_1_3")) { gL.glLoadTransposeMatrixd(paramArrayOfDouble, 0); } else { double[] arrayOfDouble = new double[16]; copyTranspose(paramArrayOfDouble, arrayOfDouble); gL.glLoadMatrixd(arrayOfDouble, 0); }  gL.glPopAttrib(); float[] arrayOfFloat = new float[4]; arrayOfFloat[0] = paramFloat1; arrayOfFloat[1] = paramFloat2; arrayOfFloat[2] = paramFloat3; arrayOfFloat[3] = paramFloat4; gL.glTexEnvfv(8960, 8705, arrayOfFloat, 0); gL.glEnable(34082); int i = joglContext.getCurrentTextureUnit(); int j = joglContext.getCurrentCombinerUnit(); if (j == 34128) { c = '蔬'; } else { c = '蔮'; }  switch (paramInt1) { case 2: gL.glCombinerInputNV(j, 6407, 34083, c, 34102, 6407); gL.glCombinerInputNV(j, 6407, 34084, i, 34102, 6407); gL.glCombinerInputNV(j, 6406, 34083, c, 34102, 6406); gL.glCombinerInputNV(j, 6406, 34084, i, 34102, 6406); gL.glCombinerOutputNV(j, 6407, 34094, 34096, 34096, 0, 0, false, false, false); gL.glCombinerOutputNV(j, 6406, 34094, 34096, 34096, 0, 0, false, false, false); break;case 3: gL.glCombinerInputNV(j, 6407, 34083, c, 34102, 6407); gL.glCombinerInputNV(j, 6407, 34084, i, 34103, 6406); gL.glCombinerInputNV(j, 6407, 34085, i, 34102, 6407); gL.glCombinerInputNV(j, 6407, 34086, i, 34102, 6406); gL.glCombinerInputNV(j, 6406, 34083, c, 34102, 6406); gL.glCombinerInputNV(j, 6406, 34084, 0, 34103, 6406); gL.glCombinerOutputNV(j, 6407, 34096, 34096, 34094, 0, 0, false, false, false); gL.glCombinerOutputNV(j, 6406, 34094, 34096, 34096, 0, 0, false, false, false); break;case 4: gL.glCombinerParameterfvNV(34090, arrayOfFloat, 0); gL.glCombinerInputNV(j, 6407, 34083, c, 34102, 6407); gL.glCombinerInputNV(j, 6407, 34084, i, 34103, 6407); gL.glCombinerInputNV(j, 6407, 34085, 34090, 34102, 6407); gL.glCombinerInputNV(j, 6407, 34086, i, 34102, 6407); gL.glCombinerInputNV(j, 6406, 34083, c, 34102, 6406); gL.glCombinerInputNV(j, 6406, 34084, i, 34102, 6406); gL.glCombinerOutputNV(j, 6407, 34096, 34096, 34094, 0, 0, false, false, false); gL.glCombinerOutputNV(j, 6406, 34094, 34096, 34096, 0, 0, false, false, false); break;case 5: gL.glCombinerInputNV(j, 6407, 34083, i, 34102, 6407); gL.glCombinerInputNV(j, 6407, 34084, 0, 34103, 6407); gL.glCombinerInputNV(j, 6406, 34083, i, 34102, 6406); gL.glCombinerInputNV(j, 6406, 34084, 0, 34103, 6406); gL.glCombinerOutputNV(j, 6407, 34094, 34096, 34096, 0, 0, false, false, false); gL.glCombinerOutputNV(j, 6406, 34094, 34096, 34096, 0, 0, false, false, false); break;case 6: if (paramInt4 == 6) { int k = getCombinerArg(gL, paramArrayOfInt1[0], i, j); gL.glCombinerInputNV(j, 6407, 34083, k, 34104, 6407); int m = getCombinerArg(gL, paramArrayOfInt1[1], i, j); gL.glCombinerInputNV(j, 6407, 34084, m, 34104, 6407); gL.glCombinerInputNV(j, 6406, 34083, 0, 34103, 6406); gL.glCombinerInputNV(j, 6406, 34084, 0, 34103, 6406); gL.glCombinerOutputNV(j, 6407, 34094, 34096, 34096, 0, 0, true, false, false); gL.glCombinerOutputNV(j, 6406, 34094, 34096, 34096, 0, 0, false, false, false); }  break; }  gL.glFinalCombinerInputNV(34083, 34094, 34102, 6407); gL.glFinalCombinerInputNV(34084, 0, 34103, 6407); gL.glFinalCombinerInputNV(34085, 0, 34102, 6407); gL.glFinalCombinerInputNV(34086, 0, 34102, 6407); gL.glFinalCombinerInputNV(34087, 0, 34102, 6407); gL.glFinalCombinerInputNV(34088, 0, 34102, 6407); gL.glFinalCombinerInputNV(34089, 34094, 34102, 6406); if (gL.isExtensionAvailable("GL_SGI_texture_color_table")) gL.glDisable(32956);  } void updateTextureColorTable(Context paramContext, int paramInt1, int paramInt2, int[] paramArrayOfInt) { GL gL = context(paramContext).getGL(); if (gL.isExtensionAvailable("GL_SGI_texture_color_table")) { if (paramInt1 == 3) { gL.glColorTable(32956, 6407, paramInt2, 6407, 5124, IntBuffer.wrap(paramArrayOfInt)); } else { gL.glColorTable(32956, 6408, paramInt2, 6408, 5124, IntBuffer.wrap(paramArrayOfInt)); }  gL.glEnable(32956); }  } void updateCombiner(Context paramContext, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int paramInt3, int paramInt4) { byte b1; GL gL = context(paramContext).getGL(); int[] arrayOfInt1 = new int[1]; int[] arrayOfInt2 = new int[1]; getGLCombineMode(gL, paramInt1, paramInt2, arrayOfInt1, arrayOfInt2); gL.glTexEnvi(8960, 34161, arrayOfInt1[0]); gL.glTexEnvi(8960, 34162, arrayOfInt2[0]); if (paramInt1 == 0) { b1 = 1; } else if (paramInt1 == 5) { b1 = 3; } else { b1 = 2; }  byte b2; for (b2 = 0; b2 < b1; b2++) { gL.glTexEnvi(8960, _gl_combineRgbSrcIndex[b2], _gl_combineSrc[paramArrayOfInt1[b2]]); gL.glTexEnvi(8960, _gl_combineRgbOpIndex[b2], _gl_combineFcn[paramArrayOfInt3[b2]]); }  if (paramInt2 == 0) { b1 = 1; } else if (paramInt2 == 5) { b1 = 3; } else { b1 = 2; }  for (b2 = 0; b2 < b1; b2++) { gL.glTexEnvi(8960, _gl_combineAlphaSrcIndex[b2], _gl_combineSrc[paramArrayOfInt2[b2]]); gL.glTexEnvi(8960, _gl_combineAlphaOpIndex[b2], _gl_combineFcn[paramArrayOfInt4[b2]]); }  gL.glTexEnvi(8960, 34163, paramInt3); gL.glTexEnvi(8960, 3356, paramInt4); } private void getGLCombineMode(GL paramGL, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2) { switch (paramInt1) { case 0: paramArrayOfInt1[0] = 7681; break;case 1: paramArrayOfInt1[0] = 8448; break;case 2: paramArrayOfInt1[0] = 260; break;case 3: paramArrayOfInt1[0] = 34164; break;case 4: paramArrayOfInt1[0] = 34023; break;case 5: paramArrayOfInt1[0] = 34165; break;case 6: paramArrayOfInt1[0] = 34478; break; }  switch (paramInt2) { case 0: paramArrayOfInt2[0] = 7681; break;case 1: paramArrayOfInt2[0] = 8448; break;case 2: paramArrayOfInt2[0] = 260; break;case 3: paramArrayOfInt2[0] = 34164; break;case 4: paramArrayOfInt2[0] = 34023; break;case 5: paramArrayOfInt2[0] = 34165; break;case 6: if (paramInt1 == 6) { paramArrayOfInt1[0] = 34479; break; }  paramArrayOfInt2[0] = 7681; break; }  } private int getCombinerArg(GL paramGL, int paramInt1, int paramInt2, int paramInt3) { int i = 0; switch (paramInt1) { case 0: if (paramInt3 == 34128) { i = 34092; break; }  i = 34094; break;case 1: i = paramInt2; break;case 2: i = 34090; break;case 3: i = paramInt2 - 1; break; }  return i; } void updateTextureUnitState(Context paramContext, int paramInt, boolean paramBoolean) { GL gL = context(paramContext).getGL(); JoglContext joglContext = (JoglContext)paramContext; if (paramInt >= 0 && gL.isExtensionAvailable("GL_VERSION_1_3")) { gL.glActiveTexture(paramInt + 33984); gL.glClientActiveTexture(33984 + paramInt); if (gL.isExtensionAvailable("GL_NV_register_combiners")) { joglContext.setCurrentTextureUnit(paramInt + 33984); joglContext.setCurrentCombinerUnit(paramInt + 34128); gL.glCombinerParameteriNV(34126, paramInt + 1); }  }  if (!paramBoolean) { gL.glDisable(3552); gL.glDisable(3553); gL.glDisable(32879); gL.glDisable(34067); }  } void bindTexture2D(Context paramContext, int paramInt, boolean paramBoolean) { GL gL = context(paramContext).getGL(); gL.glDisable(34067); gL.glDisable(32879); if (!paramBoolean) { gL.glDisable(3553); } else { gL.glBindTexture(3553, paramInt); gL.glEnable(3553); }  } void updateTexture2DImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, Object paramObject, boolean paramBoolean) { updateTexture2DImage(paramContext, 3553, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramObject, paramBoolean); } void updateTexture2DSubImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, Object paramObject, boolean paramBoolean) { updateTexture2DSubImage(paramContext, 3553, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramInt10, paramInt11, paramObject); } void updateTexture2DLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) { updateTextureLodRange(paramContext, 3553, paramInt1, paramInt2, paramFloat1, paramFloat2); } void updateTexture2DLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) { updateTextureLodOffset(paramContext, 3553, paramFloat1, paramFloat2, paramFloat3); } void updateTexture2DBoundary(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { updateTextureBoundary(paramContext, 3553, paramInt1, paramInt2, -1, paramFloat1, paramFloat2, paramFloat3, paramFloat4); } void updateTexture2DFilterModes(Context paramContext, int paramInt1, int paramInt2) { updateTextureFilterModes(paramContext, 3553, paramInt1, paramInt2); } void updateTexture2DSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat) { updateTextureSharpenFunc(paramContext, 3553, paramInt, paramArrayOfFloat); } void updateTexture2DFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat) { updateTextureFilter4Func(paramContext, 3553, paramInt, paramArrayOfFloat); } void updateTexture2DAnisotropicFilter(Context paramContext, float paramFloat) { updateTextureAnisotropicFilter(paramContext, 3553, paramFloat); } private void updateTextureLodRange(Context paramContext, int paramInt1, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2) { GL gL = context(paramContext).getGL(); gL.glTexParameteri(paramInt1, 33084, paramInt2); gL.glTexParameteri(paramInt1, 33085, paramInt3); gL.glTexParameterf(paramInt1, 33082, paramFloat1); gL.glTexParameterf(paramInt1, 33083, paramFloat2); } private void updateTextureLodOffset(Context paramContext, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3) { GL gL = context(paramContext).getGL(); gL.glTexParameterf(paramInt, 33166, paramFloat1); gL.glTexParameterf(paramInt, 33167, paramFloat2); gL.glTexParameterf(paramInt, 33168, paramFloat3); } private void updateTextureAnisotropicFilter(Context paramContext, int paramInt, float paramFloat) { GL gL = context(paramContext).getGL(); gL.glTexParameterf(paramInt, 34046, paramFloat); } void bindTexture3D(Context paramContext, int paramInt, boolean paramBoolean) { GL gL = context(paramContext).getGL(); gL.glDisable(34067); if (!paramBoolean) { gL.glDisable(32879); } else { gL.glBindTexture(32879, paramInt); gL.glEnable(32879); }  } void updateTexture3DImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, Object paramObject, boolean paramBoolean) { GL gL = context(paramContext).getGL(); char c1 = Character.MIN_VALUE; char c2 = Character.MIN_VALUE; char c3 = '耵'; boolean bool = false; switch (paramInt3) { case 1: c2 = '聉'; break;case 2: c2 = 'ᤉ'; break;case 3: c2 = 'ᤆ'; break;case 4: c2 = 'ᤊ'; break;case 5: c2 = 'ᤇ'; break;case 6: c2 = 'ᤈ'; break;default: assert false; return; }  if (paramBoolean) { gL.glTexParameteri(32879, 33169, 1); } else { gL.glTexParameteri(32879, 33169, 0); }  if (paramInt9 == 4096 || paramInt9 == 16384) { switch (paramInt4) { case 1: c1 = '胠'; break;case 2: c1 = 'ᤇ'; break;case 4: if (gL.isExtensionAvailable("GL_EXT_abgr")) { c1 = '耀'; break; }  assert false; return;case 8: c1 = 'ᤈ'; break;case 16: c1 = 'ᤊ'; break;case 32: if (c2 == 'ᤆ') { c1 = 'ᤆ'; break; }  c1 = 'ᤉ'; break;default: assert false; return; }  if (paramInt9 == 4096) { gL.glTexImage3D(32879, paramInt2, c2, paramInt5, paramInt6, paramInt7, paramInt8, c1, 5121, ByteBuffer.wrap((byte[])paramObject)); } else { gL.glTexImage3D(32879, paramInt2, c2, paramInt5, paramInt6, paramInt7, paramInt8, c1, 5121, (ByteBuffer)paramObject); }  } else if (paramInt9 == 8192 || paramInt9 == 32768) { switch (paramInt4) { case 128: c1 = 'ᤈ'; c3 = '荧'; bool = true; break;case 256: bool = true;case 512: c1 = '胡'; c3 = '荧'; break;default: assert false; return; }  if (bool) { gL.glPixelTransferf(3356, 0.0F); gL.glPixelTransferf(3357, 1.0F); }  if (paramInt9 == 8192) { gL.glTexImage3D(32879, paramInt2, c2, paramInt5, paramInt6, paramInt7, paramInt8, c1, c3, IntBuffer.wrap((int[])paramObject)); } else { gL.glTexImage3D(32879, paramInt2, c2, paramInt5, paramInt6, paramInt7, paramInt8, c1, c3, (Buffer)paramObject); }  if (bool) { gL.glPixelTransferf(3356, 1.0F); gL.glPixelTransferf(3357, 0.0F); }  } else { assert false; }  } void updateTexture3DSubImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, Object paramObject, boolean paramBoolean) { GL gL = context(paramContext).getGL(); char c1 = Character.MIN_VALUE; char c2 = Character.MIN_VALUE; char c3 = '耵'; int i = 0; boolean bool1 = false; boolean bool2 = false; if (paramInt7 > 0 || paramInt12 < paramInt10) { bool2 = true; gL.glPixelStorei(3314, paramInt10); }  switch (paramInt5) { case 1: c2 = '聉'; break;case 2: c2 = 'ᤉ'; break;case 3: c2 = 'ᤆ'; break;case 4: c2 = 'ᤊ'; break;case 5: c2 = 'ᤇ'; break;case 6: c2 = 'ᤈ'; break;default: assert false; break; }  if (paramInt15 == 4096 || paramInt15 == 16384) { switch (paramInt6) { case 1: c1 = '胠'; i = 3; break;case 2: c1 = 'ᤇ'; i = 3; break;case 4: if (gL.isExtensionAvailable("GL_EXT_abgr")) { c1 = '耀'; i = 4; break; }  assert false; return;case 8: c1 = 'ᤈ'; i = 4; break;case 16: c1 = 'ᤊ'; i = 2; break;case 32: if (c2 == 'ᤆ') { c1 = 'ᤆ'; i = 1; break; }  c1 = 'ᤉ'; i = 1; break;default: assert false; return; }  ByteBuffer byteBuffer = null; if (paramInt15 == 4096) { byteBuffer = ByteBuffer.wrap((byte[])paramObject); } else { byteBuffer = (ByteBuffer)paramObject; }  int j = (paramInt10 * paramInt11 * paramInt9 + paramInt10 * paramInt8 + paramInt7) * i; byteBuffer.position(j); gL.glTexSubImage3D(32879, paramInt1, paramInt2, paramInt3, paramInt4, paramInt12, paramInt13, paramInt14, c1, 5121, byteBuffer); } else if (paramInt15 == 8192 || paramInt15 == 32768) { switch (paramInt6) { case 128: c1 = 'ᤈ'; c3 = '荧'; bool1 = true; break;case 256: bool1 = true;case 512: c1 = '胡'; c3 = '荧'; break;default: assert false; return; }  if (bool1) { gL.glPixelTransferf(3356, 0.0F); gL.glPixelTransferf(3357, 1.0F); }  IntBuffer intBuffer = null; if (paramInt15 == 8192) { intBuffer = IntBuffer.wrap((int[])paramObject); } else { intBuffer = (IntBuffer)paramObject; }  int j = paramInt10 * paramInt11 * paramInt9 + paramInt10 * paramInt8 + paramInt7; intBuffer.position(j); gL.glTexSubImage3D(32879, paramInt1, paramInt2, paramInt3, paramInt4, paramInt12, paramInt13, paramInt14, c1, c3, intBuffer); if (bool1) { gL.glPixelTransferf(3356, 1.0F); gL.glPixelTransferf(3357, 0.0F); }  } else { assert false; return; }  if (bool2) gL.glPixelStorei(3314, 0);  } void updateTexture3DLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) { updateTextureLodRange(paramContext, 32879, paramInt1, paramInt2, paramFloat1, paramFloat2); } void updateTexture3DLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) { updateTextureLodOffset(paramContext, 32879, paramFloat1, paramFloat2, paramFloat3); } void updateTexture3DBoundary(Context paramContext, int paramInt1, int paramInt2, int paramInt3, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { updateTextureBoundary(paramContext, 3553, paramInt1, paramInt2, paramInt3, paramFloat1, paramFloat2, paramFloat3, paramFloat4); } void updateTexture3DFilterModes(Context paramContext, int paramInt1, int paramInt2) { updateTextureFilterModes(paramContext, 32879, paramInt1, paramInt2); } void updateTexture3DSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat) { updateTextureSharpenFunc(paramContext, 32879, paramInt, paramArrayOfFloat); } void updateTexture3DFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat) { updateTextureFilter4Func(paramContext, 32879, paramInt, paramArrayOfFloat); } void updateTexture3DAnisotropicFilter(Context paramContext, float paramFloat) { updateTextureAnisotropicFilter(paramContext, 32879, paramFloat); } void bindTextureCubeMap(Context paramContext, int paramInt, boolean paramBoolean) { GL gL = context(paramContext).getGL(); if (!paramBoolean) { gL.glDisable(34067); } else { gL.glBindTexture(34067, paramInt); gL.glEnable(34067); }  } void updateTextureCubeMapImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, Object paramObject, boolean paramBoolean) { updateTexture2DImage(paramContext, _gl_textureCubeMapFace[paramInt1], paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramObject, paramBoolean); } void updateTextureCubeMapSubImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, Object paramObject, boolean paramBoolean) { updateTexture2DSubImage(paramContext, _gl_textureCubeMapFace[paramInt1], paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9, paramInt10, paramInt11, paramInt12, paramObject); } void updateTextureCubeMapLodRange(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2) { updateTextureLodRange(paramContext, 34067, paramInt1, paramInt2, paramFloat1, paramFloat2); } void updateTextureCubeMapLodOffset(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) { updateTextureLodOffset(paramContext, 34067, paramFloat1, paramFloat2, paramFloat3); } void updateTextureCubeMapBoundary(Context paramContext, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { updateTextureBoundary(paramContext, 34067, paramInt1, paramInt2, -1, paramFloat1, paramFloat2, paramFloat3, paramFloat4); } void updateTextureCubeMapFilterModes(Context paramContext, int paramInt1, int paramInt2) { updateTextureFilterModes(paramContext, 34067, paramInt1, paramInt2); } void updateTextureCubeMapSharpenFunc(Context paramContext, int paramInt, float[] paramArrayOfFloat) { updateTextureSharpenFunc(paramContext, 34067, paramInt, paramArrayOfFloat); } void updateTextureCubeMapFilter4Func(Context paramContext, int paramInt, float[] paramArrayOfFloat) { updateTextureFilter4Func(paramContext, 34067, paramInt, paramArrayOfFloat); } static  { int i = screen_door[0].length * 4;
/* 5029 */     ByteBuffer byteBuffer = BufferUtil.newByteBuffer(screen_door.length * i);
/* 5030 */     IntBuffer intBuffer = byteBuffer.asIntBuffer(); int j;
/* 5031 */     for (j = 0; j < screen_door.length; j++) {
/* 5032 */       intBuffer.put(screen_door[j]);
/*      */     }
/* 5034 */     byteBuffer.rewind();
/* 5035 */     for (j = 0; j < screen_door.length; j++) {
/* 5036 */       byteBuffer.position(j * i);
/* 5037 */       byteBuffer.limit((j + 1) * i);
/* 5038 */       screen_door_table[j] = byteBuffer.slice();
/*      */     } 
/*      */ 
/*      */     
/* 5042 */     blendFunctionTable = new int[9];
/*      */     
/* 5044 */     blendFunctionTable[0] = 0;
/* 5045 */     blendFunctionTable[1] = 1;
/* 5046 */     blendFunctionTable[2] = 770;
/* 5047 */     blendFunctionTable[3] = 771;
/* 5048 */     blendFunctionTable[4] = 774;
/* 5049 */     blendFunctionTable[5] = 775;
/* 5050 */     blendFunctionTable[6] = 768;
/* 5051 */     blendFunctionTable[7] = 769;
/* 5052 */     blendFunctionTable[8] = 32769;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5502 */     _gl_combineRgbSrcIndex = new int[] { 34176, 34177, 34178 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5508 */     _gl_combineAlphaSrcIndex = new int[] { 34184, 34185, 34186 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5514 */     _gl_combineRgbOpIndex = new int[] { 34192, 34193, 34194 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5520 */     _gl_combineAlphaOpIndex = new int[] { 34200, 34201, 34202 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5526 */     _gl_combineSrc = new int[] { 34167, 5890, 34166, 34168 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 5533 */     _gl_combineFcn = new int[] { 768, 769, 770, 771 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 6855 */     _gl_textureCubeMapFace = new int[] { 34069, 34070, 34071, 34072, 34073, 34074 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 9224 */     nioVertexTemp = new ThreadLocal();
/* 9225 */     nioVertexDoubleTemp = new ThreadLocal();
/* 9226 */     nioColorTemp = new ThreadLocal();
/* 9227 */     nioColorByteTemp = new ThreadLocal();
/* 9228 */     nioNormalTemp = new ThreadLocal();
/* 9229 */     nioTexCoordSetTemp = new ThreadLocal();
/* 9230 */     nioVertexAttrSetTemp = new ThreadLocal(); }
/*      */   void updateTextureCubeMapAnisotropicFilter(Context paramContext, float paramFloat) { updateTextureAnisotropicFilter(paramContext, 34067, paramFloat); }
/*      */   private void updateTexture2DImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, Object paramObject, boolean paramBoolean) { GL gL = context(paramContext).getGL(); char c1 = Character.MIN_VALUE, c2 = Character.MIN_VALUE; char c3 = '耵'; boolean bool = false; switch (paramInt4) { case 1: c2 = '聉'; break;case 2: c2 = 'ᤉ'; break;case 3: c2 = 'ᤆ'; break;case 4: c2 = 'ᤊ'; break;case 5: c2 = 'ᤇ'; break;case 6: c2 = 'ᤈ'; break;default: assert false; break; }  if (paramBoolean) { gL.glTexParameteri(paramInt1, 33169, 1); } else { gL.glTexParameteri(paramInt1, 33169, 0); }  if (paramInt9 == 4096 || paramInt9 == 16384) { switch (paramInt5) { case 1: c1 = '胠'; break;case 2: c1 = 'ᤇ'; break;case 4: if (gL.isExtensionAvailable("GL_EXT_abgr")) { c1 = '耀'; break; }  assert false; return;case 8: c1 = 'ᤈ'; break;case 16: c1 = 'ᤊ'; break;case 32: if (c2 == 'ᤆ') { c1 = 'ᤆ'; break; }  c1 = 'ᤉ'; break;default: assert false; return; }  if (paramInt9 == 4096) { gL.glTexImage2D(paramInt1, paramInt3, c2, paramInt6, paramInt7, paramInt8, c1, 5121, ByteBuffer.wrap((byte[])paramObject)); } else { gL.glTexImage2D(paramInt1, paramInt3, c2, paramInt6, paramInt7, paramInt8, c1, 5121, (Buffer)paramObject); }  } else if (paramInt9 == 8192 || paramInt9 == 32768) { switch (paramInt5) { case 128: c1 = 'ᤈ'; c3 = '荧'; bool = true; break;case 256: bool = true;case 512: c1 = '胡'; c3 = '荧'; break;default: assert false; return; }  if (bool) { gL.glPixelTransferf(3356, 0.0F); gL.glPixelTransferf(3357, 1.0F); }  if (paramInt9 == 8192) { gL.glTexImage2D(paramInt1, paramInt3, c2, paramInt6, paramInt7, paramInt8, c1, c3, IntBuffer.wrap((int[])paramObject)); } else { gL.glTexImage2D(paramInt1, paramInt3, c2, paramInt6, paramInt7, paramInt8, c1, c3, (Buffer)paramObject); }  if (bool) { gL.glPixelTransferf(3356, 1.0F); gL.glPixelTransferf(3357, 0.0F); }  } else { assert false; }  }
/* 9233 */   private void updateTexture2DSubImage(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, Object paramObject) { GL gL = context(paramContext).getGL(); char c1 = Character.MIN_VALUE, c2 = Character.MIN_VALUE; int i = 0; char c3 = '耵'; boolean bool1 = false; boolean bool2 = false; if (paramInt7 > 0 || paramInt10 < paramInt9) { bool2 = true; gL.glPixelStorei(3314, paramInt9); }  switch (paramInt5) { case 1: c2 = '聉'; break;case 2: c2 = 'ᤉ'; break;case 3: c2 = 'ᤆ'; break;case 4: c2 = 'ᤊ'; break;case 5: c2 = 'ᤇ'; break;case 6: c2 = 'ᤈ'; break;default: assert false; break; }  if (paramInt12 == 4096 || paramInt12 == 16384) { switch (paramInt6) { case 1: c1 = '胠'; i = 3; break;case 2: c1 = 'ᤇ'; i = 3; break;case 4: if (gL.isExtensionAvailable("GL_EXT_abgr")) { c1 = '耀'; i = 4; break; }  assert false; return;case 8: c1 = 'ᤈ'; i = 4; break;case 16: c1 = 'ᤊ'; i = 2; break;case 32: if (c2 == 'ᤆ') { c1 = 'ᤆ'; i = 1; break; }  c1 = 'ᤉ'; i = 1; break;default: assert false; return; }  ByteBuffer byteBuffer = null; if (paramInt12 == 4096) { byteBuffer = ByteBuffer.wrap((byte[])paramObject); } else { byteBuffer = (ByteBuffer)paramObject; }  byteBuffer.position((paramInt9 * paramInt8 + paramInt7) * i); gL.glTexSubImage2D(paramInt1, paramInt2, paramInt3, paramInt4, paramInt10, paramInt11, c1, 5121, byteBuffer); } else if (paramInt12 == 8192 || paramInt12 == 32768) { switch (paramInt6) { case 128: c1 = 'ᤈ'; c3 = '荧'; bool1 = true; break;case 256: bool1 = true;case 512: c1 = '胡'; c3 = '荧'; break;default: assert false; return; }  if (bool1) { gL.glPixelTransferf(3356, 0.0F); gL.glPixelTransferf(3357, 1.0F); }  IntBuffer intBuffer = null; if (paramInt12 == 8192) { intBuffer = IntBuffer.wrap((int[])paramObject); } else { intBuffer = (IntBuffer)paramObject; }  intBuffer.position(paramInt9 * paramInt8 + paramInt7); gL.glTexSubImage2D(paramInt1, paramInt2, paramInt3, paramInt4, paramInt10, paramInt11, c1, c3, intBuffer); if (bool1) { gL.glPixelTransferf(3356, 1.0F); gL.glPixelTransferf(3357, 0.0F); }  } else { assert false; return; }  if (bool2) gL.glPixelStorei(3314, 0);  } void updateTextureFilterModes(Context paramContext, int paramInt1, int paramInt2, int paramInt3) { GL gL = context(paramContext).getGL(); switch (paramInt2) { case 0: case 2: gL.glTexParameteri(paramInt1, 10241, 9728); break;case 3: gL.glTexParameteri(paramInt1, 10241, 9729); break;case 4: gL.glTexParameteri(paramInt1, 10241, 9984); break;case 1: case 5: gL.glTexParameteri(paramInt1, 10241, 9987); break;case 12: gL.glTexParameteri(paramInt1, 10241, 33094); break; }  switch (paramInt3) { case 0: case 2: gL.glTexParameteri(paramInt1, 10240, 9728); break;case 1: case 3: gL.glTexParameteri(paramInt1, 10240, 9729); break;case 9: gL.glTexParameteri(paramInt1, 10240, 32941); break;case 10: gL.glTexParameteri(paramInt1, 10240, 32943); break;case 11: gL.glTexParameteri(paramInt1, 10240, 32942); break;case 6: gL.glTexParameteri(paramInt1, 10240, 32919); break;case 7: gL.glTexParameteri(paramInt1, 10240, 32921); break;case 8: gL.glTexParameteri(paramInt1, 10240, 32920); break;case 12: gL.glTexParameteri(paramInt1, 10240, 33094); break; }  } void updateTextureBoundary(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { GL gL = context(paramContext).getGL(); switch (paramInt2) { case 3: gL.glTexParameteri(paramInt1, 10242, 10497); break;case 2: gL.glTexParameteri(paramInt1, 10242, 10496); break;case 4: gL.glTexParameteri(paramInt1, 10242, 33071); break;case 5: gL.glTexParameteri(paramInt1, 10242, 33069); break; }  switch (paramInt3) { case 3: gL.glTexParameteri(paramInt1, 10243, 10497); break;case 2: gL.glTexParameteri(paramInt1, 10243, 10496); break;case 4: gL.glTexParameteri(paramInt1, 10243, 33071); break;case 5: gL.glTexParameteri(paramInt1, 10243, 33069); break; }  if (paramInt4 != -1) switch (paramInt4) { case 3: gL.glTexParameteri(paramInt1, 32882, 10497); break;case 2: gL.glTexParameteri(paramInt1, 32882, 10496); break;case 4: gL.glTexParameteri(paramInt1, 32882, 33071); break;case 5: gL.glTexParameteri(paramInt1, 32882, 33069); break; }   if (paramInt2 == 2 || paramInt3 == 2 || paramInt4 == 2) { float[] arrayOfFloat = new float[4]; arrayOfFloat[0] = paramFloat1; arrayOfFloat[1] = paramFloat2; arrayOfFloat[2] = paramFloat3; arrayOfFloat[3] = paramFloat4; gL.glTexParameterfv(paramInt1, 4100, arrayOfFloat, 0); }  } private static final String getFilterName(int paramInt) { switch (paramInt) { case 0: return "Texture.FASTEST";case 1: return "Texture.NICEST";case 2: return "Texture.BASE_LEVEL_POINT";case 3: return "Texture.BASE_LEVEL_LINEAR";case 4: return "Texture.MULTI_LEVEL_POINT";case 5: return "Texture.MULTI_LEVEL_LINEAR";case 12: return "Texture.FILTER4";case 9: return "Texture.LINEAR_SHARPEN";case 10: return "Texture.LINEAR_SHARPEN_RGB";case 11: return "Texture.LINEAR_SHARPEN_ALPHA";case 6: return "Texture.LINEAR_DETAIL";case 7: return "Texture.LINEAR_DETAIL_RGB";case 8: return "Texture.LINEAR_DETAIL_ALPHA"; }  return "(unknown)"; } private void updateTextureSharpenFunc(Context paramContext, int paramInt1, int paramInt2, float[] paramArrayOfFloat) { GL gL = context(paramContext).getGL(); gL.glSharpenTexFuncSGIS(paramInt1, paramInt2, paramArrayOfFloat, 0); } private void updateTextureFilter4Func(Context paramContext, int paramInt1, int paramInt2, float[] paramArrayOfFloat) { GL gL = context(paramContext).getGL(); gL.glTexFilterFuncSGIS(paramInt1, 33094, paramInt2, paramArrayOfFloat, 0); } long getAWT() { return 0L; } boolean initializeJ3D(boolean paramBoolean) { return true; } int getMaximumLights() { return 8; } Context createNewContext(Canvas3D paramCanvas3D, long paramLong1, Drawable paramDrawable, long paramLong2, Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) { GLDrawable gLDrawable = null; IndexCapabilitiesChooser indexCapabilitiesChooser = null; JoglGraphicsConfiguration joglGraphicsConfiguration = (JoglGraphicsConfiguration)paramCanvas3D.graphicsConfiguration; if (joglGraphicsConfiguration.getChosenIndex() >= 0) indexCapabilitiesChooser = new IndexCapabilitiesChooser(joglGraphicsConfiguration.getChosenIndex());  if (paramCanvas3D.drawable == null) { gLDrawable = GLDrawableFactory.getFactory().getGLDrawable(paramCanvas3D, joglGraphicsConfiguration.getGLCapabilities(), indexCapabilitiesChooser); paramCanvas3D.drawable = new JoglDrawable(gLDrawable); } else { gLDrawable = drawable(paramCanvas3D.drawable); }  gLDrawable.setRealized(true); gLContext = gLDrawable.createContext(context(paramContext)); boolean bool = false; byte b1 = 0; byte b2 = 5; do { bool = false; int i = gLContext.makeCurrent(); if (i != 0) continue;  bool = true; b1++; try { Thread.sleep(100L); } catch (InterruptedException interruptedException) {} } while (bool && b1 < b2); if (b1 == b2) throw new IllegalRenderingStateException("Unable to make new context current after " + b1 + "tries");  GL gL = gLContext.getGL(); JoglContext joglContext = new JoglContext(gLContext); try { if (!getPropertiesFromCurrentContext(joglContext)) throw new IllegalRenderingStateException("Unable to fetch properties from current OpenGL context");  if (!paramBoolean1) setupCanvasProperties(paramCanvas3D, joglContext, gL, paramBoolean3, paramBoolean4);  gL.glEnable(32826); gL.glColorMaterial(1032, 4609); gL.glDepthFunc(515); gL.glEnable(2903); gL.glReadBuffer(1028); gL.glPixelStorei(3317, 1); gL.glLightModeli(33272, 33274); } finally { gLContext.release(); }  return joglContext; } void createQueryContext(Canvas3D paramCanvas3D, long paramLong1, Drawable paramDrawable, long paramLong2, boolean paramBoolean1, int paramInt1, int paramInt2, boolean paramBoolean2, boolean paramBoolean3) { Frame frame = new Frame(); frame.setUndecorated(true); frame.setLayout(new BorderLayout()); GLCapabilities gLCapabilities = new GLCapabilities(); ContextQuerier contextQuerier = new ContextQuerier(paramCanvas3D, paramBoolean2, paramBoolean3); QueryCanvas queryCanvas = new QueryCanvas(gLCapabilities, contextQuerier, null); frame.add(queryCanvas, "Center"); frame.setSize(1, 1); frame.setVisible(true); queryCanvas.doQuery(); if (!EventQueue.isDispatchThread()) synchronized (contextQuerier) { if (!contextQuerier.done()) try { contextQuerier.wait(1000L); } catch (InterruptedException interruptedException) {}  }   disposeOnEDT(frame); } Drawable createOffScreenBuffer(Canvas3D paramCanvas3D, Context paramContext, long paramLong1, long paramLong2, int paramInt1, int paramInt2) { JoglGraphicsConfiguration joglGraphicsConfiguration = (JoglGraphicsConfiguration)paramCanvas3D.graphicsConfiguration; GLCapabilities gLCapabilities = joglGraphicsConfiguration.getGLCapabilities(); if (!GLDrawableFactory.getFactory().canCreateGLPbuffer()) return null;  GLPbuffer gLPbuffer = GLDrawableFactory.getFactory().createGLPbuffer(gLCapabilities, null, paramInt1, paramInt2, null); return new JoglDrawable(gLPbuffer); } void destroyOffScreenBuffer(Canvas3D paramCanvas3D, Context paramContext, long paramLong1, long paramLong2, Drawable paramDrawable) { JoglDrawable joglDrawable = (JoglDrawable)paramDrawable; GLPbuffer gLPbuffer = (GLPbuffer)joglDrawable.getGLDrawable(); gLPbuffer.destroy(); } void readOffScreenBuffer(Canvas3D paramCanvas3D, Context paramContext, int paramInt1, int paramInt2, Object paramObject, int paramInt3, int paramInt4) { GL gL = context(paramContext).getGL(); gL.glPixelStorei(3330, paramInt3); gL.glPixelStorei(3333, 1); char c = Character.MIN_VALUE; if (paramInt2 == 4096 || paramInt2 == 16384) { switch (paramInt1) { case 1: c = '胠'; break;case 2: c = 'ᤇ'; break;case 4: if (gL.isExtensionAvailable("GL_EXT_abgr")) { c = '耀'; break; }  assert false; return;case 8: c = 'ᤈ'; break;default: throw new AssertionError("illegal format " + paramInt1); }  gL.glReadPixels(0, 0, paramInt3, paramInt4, c, 5121, ByteBuffer.wrap((byte[])paramObject)); } else if (paramInt2 == 8192 || paramInt2 == 32768) { char c1 = '耵'; boolean bool = false; switch (paramInt1) { case 128: c = 'ᤈ'; c1 = '荧'; bool = true; break;case 256: bool = true;case 512: c = '胡'; c1 = '荧'; break;default: throw new AssertionError("illegal format " + paramInt1); }  if (bool) { gL.glPixelTransferf(3356, 0.0F); gL.glPixelTransferf(3357, 1.0F); }  gL.glReadPixels(0, 0, paramInt3, paramInt4, c, c1, IntBuffer.wrap((int[])paramObject)); if (bool) { gL.glPixelTransferf(3356, 1.0F); gL.glPixelTransferf(3357, 0.0F); }  } else { throw new AssertionError("illegal image data type " + paramInt2); }  } int swapBuffers(Canvas3D paramCanvas3D, Context paramContext, long paramLong, Drawable paramDrawable) { GLDrawable gLDrawable = drawable(paramDrawable); gLDrawable.swapBuffers(); return 0; } int resizeD3DCanvas(Canvas3D paramCanvas3D, Context paramContext) { return 0; } int toggleFullScreenMode(Canvas3D paramCanvas3D, Context paramContext) { return 0; } void updateMaterialColor(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { GL gL = context(paramContext).getGL(); gL.glColor4f(paramFloat1, paramFloat2, paramFloat3, paramFloat4); gL.glDisable(2896); } void destroyContext(long paramLong, Drawable paramDrawable, Context paramContext) { GLDrawable gLDrawable = drawable(paramDrawable); GLContext gLContext = context(paramContext); if (GLContext.getCurrent() == gLContext) gLContext.release();  gLContext.destroy(); gLDrawable.setRealized(false); } void accum(Context paramContext, float paramFloat) { GL gL = context(paramContext).getGL(); gL.glReadBuffer(1029); gL.glAccum(256, paramFloat); gL.glReadBuffer(1028); } void accumReturn(Context paramContext) { GL gL = context(paramContext).getGL(); gL.glAccum(258, 1.0F); } void clearAccum(Context paramContext) { GL gL = context(paramContext).getGL(); gL.glClear(512); } int getNumCtxLights(Context paramContext) { GL gL = context(paramContext).getGL(); int[] arrayOfInt = new int[1]; gL.glGetIntegerv(3377, arrayOfInt, 0); return arrayOfInt[0]; } boolean decal1stChildSetup(Context paramContext) { GL gL = context(paramContext).getGL(); gL.glEnable(2960); gL.glClearStencil(0); gL.glClear(1024); gL.glStencilFunc(519, 1, 1); gL.glStencilOp(7680, 7680, 7681); if (gL.glIsEnabled(2929)) return true;  return false; } void decalNthChildSetup(Context paramContext) { GL gL = context(paramContext).getGL(); gL.glDisable(2929); gL.glStencilFunc(514, 1, 1); gL.glStencilOp(7680, 7680, 7680); } void decalReset(Context paramContext, boolean paramBoolean) { GL gL = context(paramContext).getGL(); gL.glDisable(2960); if (paramBoolean) gL.glEnable(2929);  } void ctxUpdateEyeLightingEnable(Context paramContext, boolean paramBoolean) { GL gL = context(paramContext).getGL(); if (paramBoolean) { gL.glLightModeli(2897, 1); } else { gL.glLightModeli(2897, 0); }  } void setBlendColor(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) { GL gL = context(paramContext).getGL(); if (gL.isExtensionAvailable("GL_ARB_imaging")) gL.glBlendColor(paramFloat1, paramFloat2, paramFloat3, paramFloat4);  } void setBlendFunc(Context paramContext, int paramInt1, int paramInt2) { GL gL = context(paramContext).getGL(); gL.glEnable(3042); gL.glBlendFunc(blendFunctionTable[paramInt1], blendFunctionTable[paramInt2]); } void setFogEnableFlag(Context paramContext, boolean paramBoolean) { GL gL = context(paramContext).getGL(); if (paramBoolean) { gL.glEnable(2912); } else { gL.glDisable(2912); }  } void setFullSceneAntialiasing(Context paramContext, boolean paramBoolean) { JoglContext joglContext = (JoglContext)paramContext; GL gL = context(joglContext).getGL(); if (joglContext.getHasMultisample() && !VirtualUniverse.mc.implicitAntialiasing) if (paramBoolean) { gL.glEnable(32925); } else { gL.glDisable(32925); }   } void setGlobalAlpha(Context paramContext, float paramFloat) { GL gL = context(paramContext).getGL(); if (gL.isExtensionAvailable("GL_SUN_global_alpha")) { gL.glEnable(33241); gL.glGlobalAlphaFactorfSUN(paramFloat); }  } void updateSeparateSpecularColorEnable(Context paramContext, boolean paramBoolean) { GL gL = context(paramContext).getGL(); if (paramBoolean) { gL.glLightModeli(33272, 33274); } else { gL.glLightModeli(33272, 33273); }  } void beginScene(Context paramContext) {} void endScene(Context paramContext) {} boolean validGraphicsMode() { DisplayMode displayMode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode(); return (displayMode.getBitDepth() < 0 || displayMode.getBitDepth() > 8); } void setLightEnables(Context paramContext, long paramLong, int paramInt) { GL gL = context(paramContext).getGL(); for (byte b = 0; b < paramInt; b++) { if ((paramLong & (true << b)) != 0L) { gL.glEnable('䀀' + b); } else { gL.glDisable('䀀' + b); }  }  } private static FloatBuffer getVertexArrayBuffer(float[] paramArrayOfFloat) { return getVertexArrayBuffer(paramArrayOfFloat, true); } void setSceneAmbient(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3) { GL gL = context(paramContext).getGL(); float[] arrayOfFloat = new float[4]; arrayOfFloat[0] = paramFloat1; arrayOfFloat[1] = paramFloat2; arrayOfFloat[2] = paramFloat3; arrayOfFloat[3] = 1.0F; gL.glLightModelfv(2899, arrayOfFloat, 0); } void disableFog(Context paramContext) { GL gL = context(paramContext).getGL(); gL.glDisable(2912); } void disableModelClip(Context paramContext) { GL gL = context(paramContext).getGL(); gL.glDisable(12288); gL.glDisable(12289); gL.glDisable(12290); gL.glDisable(12291); gL.glDisable(12292); gL.glDisable(12293); } void resetRenderingAttributes(Context paramContext, boolean paramBoolean1, boolean paramBoolean2) { GL gL = context(paramContext).getGL(); if (!paramBoolean1) gL.glDepthMask(true);  if (!paramBoolean2) gL.glEnable(2929);  gL.glAlphaFunc(519, 0.0F); gL.glDepthFunc(515); gL.glEnable(2903); gL.glDisable(3058); } void resetTextureNative(Context paramContext, int paramInt) { GL gL = context(paramContext).getGL(); if (paramInt >= 0 && gL.isExtensionAvailable("GL_VERSION_1_3")) { gL.glActiveTexture(paramInt + 33984); gL.glClientActiveTexture(paramInt + 33984); }  gL.glDisable(3552); gL.glDisable(3553); gL.glDisable(32879); gL.glDisable(34067); } void activeTextureUnit(Context paramContext, int paramInt) { GL gL = context(paramContext).getGL(); if (gL.isExtensionAvailable("GL_VERSION_1_3")) { gL.glActiveTexture(paramInt + 33984); gL.glClientActiveTexture(paramInt + 33984); }  } void resetTexCoordGeneration(Context paramContext) { GL gL = context(paramContext).getGL(); gL.glDisable(3168); gL.glDisable(3169); gL.glDisable(3170); gL.glDisable(3171); } void resetTextureAttributes(Context paramContext) { GL gL = context(paramContext).getGL(); float[] arrayOfFloat = new float[4]; gL.glPushAttrib(4096); gL.glMatrixMode(5890); gL.glLoadIdentity(); gL.glPopAttrib(); gL.glTexEnvfv(8960, 8705, arrayOfFloat, 0); gL.glTexEnvi(8960, 8704, 7681); gL.glHint(3152, 4354); if (gL.isExtensionAvailable("GL_NV_register_combiners")) gL.glDisable(34082);  if (gL.isExtensionAvailable("GL_SGI_texture_color_table")) gL.glDisable(32956);  } void resetPolygonAttributes(Context paramContext) { GL gL = context(paramContext).getGL(); gL.glCullFace(1029); gL.glEnable(2884); gL.glLightModeli(2898, 0); gL.glPolygonMode(1032, 6914); gL.glPolygonOffset(0.0F, 0.0F); gL.glDisable(10753); gL.glDisable(10754); gL.glDisable(32823); } void resetLineAttributes(Context paramContext) { GL gL = context(paramContext).getGL(); gL.glLineWidth(1.0F); gL.glDisable(2852); gL.glDisable(2848); } void resetPointAttributes(Context paramContext) { GL gL = context(paramContext).getGL(); gL.glPointSize(1.0F); gL.glDisable(2832); } void resetTransparency(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) { GL gL = context(paramContext).getGL(); if ((((paramInt1 & 0x2) != 0 || paramInt2 == 1) && paramBoolean1) || (((paramInt1 & true) != 0 || paramInt2 == 0) && paramBoolean2)) { gL.glEnable(3042); gL.glBlendFunc(770, 771); } else { gL.glDisable(3042); }  gL.glDisable(2882); } void resetColoringAttributes(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean) { GL gL = context(paramContext).getGL(); if (!paramBoolean) gL.glColor4f(paramFloat1, paramFloat2, paramFloat3, paramFloat4);  gL.glShadeModel(7425); } void syncRender(Context paramContext, boolean paramBoolean) { GL gL = context(paramContext).getGL(); if (paramBoolean) { gL.glFinish(); } else { gL.glFlush(); }  } boolean useCtx(Context paramContext, long paramLong, Drawable paramDrawable) { GLContext gLContext = context(paramContext); int i = gLContext.makeCurrent(); return (i != 0); } boolean releaseCtx(Context paramContext, long paramLong) { GLContext gLContext = context(paramContext); gLContext.release(); return true; } void clear(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean) { JoglContext joglContext = (JoglContext)paramContext; GLContext gLContext = context(paramContext); GL gL = gLContext.getGL(); char c = '䄀'; if (paramBoolean) { gL.glPushAttrib(1280); gL.glClearStencil(0); gL.glStencilMask(-1); c |= 0x400; } else { gL.glPushAttrib(256); }  gL.glDepthMask(true); gL.glClearColor(paramFloat1, paramFloat2, paramFloat3, joglContext.getAlphaClearValue()); gL.glClear(c); gL.glPopAttrib(); } void textureFillBackground(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, boolean paramBoolean) { GLContext gLContext = context(paramContext); GL gL = gLContext.getGL(); gL.glPushAttrib(270344); disableAttribFor2D(gL); gL.glDepthMask(false); gL.glEnable(3553); if (paramBoolean) { gL.glTexParameteri(3553, 10241, 9729); gL.glTexParameteri(3553, 10240, 9729); }  gL.glPolygonMode(1032, 6914); gL.glPixelStorei(3317, 1); gL.glMatrixMode(5889); gL.glLoadIdentity(); gL.glOrtho(-1.0D, 1.0D, -1.0D, 1.0D, -1.0D, 1.0D); gL.glMatrixMode(5888); gL.glLoadIdentity(); gL.glMatrixMode(5890); gL.glPushMatrix(); gL.glLoadIdentity(); gL.glBegin(7); gL.glTexCoord2f(paramFloat1, paramFloat3); gL.glVertex2f(paramFloat5, paramFloat7); gL.glTexCoord2f(paramFloat2, paramFloat3); gL.glVertex2f(paramFloat6, paramFloat7); gL.glTexCoord2f(paramFloat2, paramFloat4); gL.glVertex2f(paramFloat6, paramFloat8); gL.glTexCoord2f(paramFloat1, paramFloat4); gL.glVertex2f(paramFloat5, paramFloat8); gL.glEnd(); gL.glPopMatrix(); gL.glMatrixMode(5888); gL.glPopAttrib(); } void textureFillRaster(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, boolean paramBoolean) { GLContext gLContext = context(paramContext); GL gL = gLContext.getGL(); gL.glPushAttrib(270345); disableAttribForRaster(gL); if (paramBoolean) { gL.glTexParameteri(3553, 10241, 9729); gL.glTexParameteri(3553, 10240, 9729); }  gL.glTexEnvi(8960, 8704, 8448); gL.glColor4f(1.0F, 1.0F, 1.0F, paramFloat10); gL.glPolygonMode(1032, 6914); gL.glPixelStorei(3317, 1); gL.glMatrixMode(5888); gL.glPushMatrix(); gL.glLoadIdentity(); gL.glMatrixMode(5889); gL.glPushMatrix(); gL.glLoadIdentity(); gL.glOrtho(0.0D, 1.0D, 0.0D, 1.0D, 0.0D, 1.0D); gL.glBegin(7); gL.glTexCoord2f(paramFloat1, paramFloat3); gL.glVertex3f(paramFloat5, paramFloat7, paramFloat9); gL.glTexCoord2f(paramFloat2, paramFloat3); gL.glVertex3f(paramFloat6, paramFloat7, paramFloat9); gL.glTexCoord2f(paramFloat2, paramFloat4); gL.glVertex3f(paramFloat6, paramFloat8, paramFloat9); gL.glTexCoord2f(paramFloat1, paramFloat4); gL.glVertex3f(paramFloat5, paramFloat8, paramFloat9); gL.glEnd(); gL.glPopMatrix(); gL.glMatrixMode(5888); gL.glPopMatrix(); gL.glPopAttrib(); } void executeRasterDepth(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Object paramObject) { GLContext gLContext = context(paramContext); GL gL = gLContext.getGL(); gL.glRasterPos3f(paramFloat1, paramFloat2, paramFloat3); int[] arrayOfInt = new int[1]; gL.glGetIntegerv(3073, arrayOfInt, 0); gL.glDrawBuffer(0); gL.glPixelZoom(1.0F, -1.0F); gL.glPixelStorei(3314, paramInt5); if (paramInt1 >= 0) { gL.glPixelStorei(3316, paramInt1); if (paramInt1 + paramInt3 > paramInt5) paramInt3 = paramInt5 - paramInt1;  } else { paramInt3 += paramInt1; if (paramInt3 > paramInt5) paramInt3 = paramInt5;  }  if (paramInt2 >= 0) { gL.glPixelStorei(3315, paramInt2); if (paramInt2 + paramInt4 > paramInt4) paramInt4 -= paramInt2;  } else { paramInt4 += paramInt2; if (paramInt4 > paramInt4) paramInt4 = paramInt4;  }  if (paramInt7 == 1) { gL.glDrawPixels(paramInt3, paramInt4, 6402, 5125, IntBuffer.wrap((int[])paramObject)); } else { gL.glDrawPixels(paramInt3, paramInt4, 6402, 5126, FloatBuffer.wrap((float[])paramObject)); }  gL.glDrawBuffer(arrayOfInt[0]); gL.glPixelStorei(3314, 0); gL.glPixelStorei(3316, 0); gL.glPixelStorei(3315, 0); } void setModelViewMatrix(Context paramContext, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) { GLContext gLContext = context(paramContext); GL gL = gLContext.getGL(); gL.glMatrixMode(5888); if (gL.isExtensionAvailable("GL_VERSION_1_3")) { gL.glLoadTransposeMatrixd(paramArrayOfDouble1, 0); gL.glMultTransposeMatrixd(paramArrayOfDouble2, 0); } else { double[] arrayOfDouble1 = new double[16]; double[] arrayOfDouble2 = new double[16]; copyTranspose(paramArrayOfDouble1, arrayOfDouble1); copyTranspose(paramArrayOfDouble2, arrayOfDouble2); gL.glLoadMatrixd(arrayOfDouble1, 0); gL.glMultMatrixd(arrayOfDouble2, 0); }  } void setProjectionMatrix(Context paramContext, double[] paramArrayOfDouble) { GLContext gLContext = context(paramContext); GL gL = gLContext.getGL(); gL.glMatrixMode(5889); if (gL.isExtensionAvailable("GL_VERSION_1_3")) { paramArrayOfDouble[8] = paramArrayOfDouble[8] * -1.0D; paramArrayOfDouble[9] = paramArrayOfDouble[9] * -1.0D; paramArrayOfDouble[10] = paramArrayOfDouble[10] * -1.0D; paramArrayOfDouble[11] = paramArrayOfDouble[11] * -1.0D; gL.glLoadTransposeMatrixd(paramArrayOfDouble, 0); paramArrayOfDouble[8] = paramArrayOfDouble[8] * -1.0D; paramArrayOfDouble[9] = paramArrayOfDouble[9] * -1.0D; paramArrayOfDouble[10] = paramArrayOfDouble[10] * -1.0D; paramArrayOfDouble[11] = paramArrayOfDouble[11] * -1.0D; } else { double[] arrayOfDouble = new double[16]; copyTranspose(paramArrayOfDouble, arrayOfDouble); arrayOfDouble[2] = arrayOfDouble[2] * -1.0D; arrayOfDouble[6] = arrayOfDouble[6] * -1.0D; arrayOfDouble[10] = arrayOfDouble[10] * -1.0D; arrayOfDouble[14] = arrayOfDouble[14] * -1.0D; gL.glLoadMatrixd(arrayOfDouble, 0); }  } void setViewport(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4) { GL gL = context(paramContext).getGL(); gL.glViewport(paramInt1, paramInt2, paramInt3, paramInt4); } void newDisplayList(Context paramContext, int paramInt) { if (paramInt <= 0) System.err.println("JAVA 3D ERROR : glNewList(" + paramInt + ") -- IGNORED");  GL gL = context(paramContext).getGL(); gL.glNewList(paramInt, 4864); } void endDisplayList(Context paramContext) { GL gL = context(paramContext).getGL(); gL.glEndList(); } int numInvalidLists = 0; private static final int DISABLE_STEREO = 1; private static final int DISABLE_AA = 2; private static final int DISABLE_DOUBLE_BUFFER = 3; void callDisplayList(Context paramContext, int paramInt, boolean paramBoolean) { if (paramInt <= 0) { if (this.numInvalidLists < 3) { this.numInvalidLists++; System.err.println("JAVA 3D ERROR : glCallList(" + paramInt + ") -- IGNORED"); } else if (this.numInvalidLists == 3) { this.numInvalidLists++; System.err.println("JAVA 3D : further glCallList error messages discarded"); }  return; }  GL gL = context(paramContext).getGL(); if (paramBoolean) gL.glEnable(2977);  gL.glCallList(paramInt); if (paramBoolean) gL.glDisable(2977);  } void freeDisplayList(Context paramContext, int paramInt) { if (paramInt <= 0) System.err.println("JAVA 3D ERROR : glDeleteLists(" + paramInt + ",1) -- IGNORED");  GL gL = context(paramContext).getGL(); gL.glDeleteLists(paramInt, 1); } void freeTexture(Context paramContext, int paramInt) { GL gL = context(paramContext).getGL(); if (paramInt > 0) { int[] arrayOfInt = new int[1]; arrayOfInt[0] = paramInt; gL.glDeleteTextures(1, arrayOfInt, 0); } else { System.err.println("tried to delete tex with texid <= 0"); }  } void texturemapping(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, byte[] paramArrayOfByte, int paramInt12, int paramInt13) { GL gL = context(paramContext).getGL(); char c = 'ᤈ'; gL.glPushAttrib(270600); disableAttribFor2D(gL); gL.glPolygonMode(1032, 6914); gL.glDepthMask(false); gL.glPixelStorei(3317, 1); gL.glBindTexture(3553, paramInt11); gL.glTexParameteri(3553, 10240, 9728); gL.glTexParameteri(3553, 10241, 9728); gL.glTexParameteri(3553, 10242, 10497); gL.glTexParameteri(3553, 10243, 10497); gL.glTexEnvf(8960, 8704, 7681.0F); gL.glEnable(3042); gL.glBlendFunc(770, 771); gL.glEnable(3553); gL.glMatrixMode(5889); gL.glLoadIdentity(); gL.glOrtho(0.0D, paramInt12, 0.0D, paramInt13, 0.0D, 0.0D); gL.glMatrixMode(5888); gL.glLoadIdentity(); if (gL.isExtensionAvailable("GL_EXT_abgr")) { c = '耀'; } else { switch (paramInt10) { case 8: c = 'ᤈ'; break;case 2: c = 'ᤇ'; break; }  }  gL.glPixelStorei(3314, paramInt9); gL.glPixelStorei(3316, paramInt3); gL.glPixelStorei(3315, paramInt4); gL.glTexSubImage2D(3553, 0, paramInt3, paramInt4, paramInt5 - paramInt3, paramInt6 - paramInt4, c, 5121, ByteBuffer.wrap(paramArrayOfByte)); gL.glPixelStorei(3314, 0); gL.glPixelStorei(3316, 0); gL.glPixelStorei(3315, 0); float f1 = paramInt3 / paramInt7; float f2 = paramInt4 / paramInt8; float f3 = paramInt5 / paramInt7; float f4 = paramInt6 / paramInt8; float f5 = paramInt12 / 2.0F; float f6 = paramInt13 / 2.0F; float f7 = ((paramInt1 + paramInt3) - f5) / f5; float f8 = (f6 - (paramInt2 + paramInt6)) / f6; float f9 = ((paramInt1 + paramInt5) - f5) / f5; float f10 = (f6 - (paramInt2 + paramInt4)) / f6; gL.glBegin(7); gL.glTexCoord2f(f1, f4); gL.glVertex2f(f7, f8); gL.glTexCoord2f(f3, f4); gL.glVertex2f(f9, f8); gL.glTexCoord2f(f3, f2); gL.glVertex2f(f9, f10); gL.glTexCoord2f(f1, f2); gL.glVertex2f(f7, f10); gL.glEnd(); gL.glDepthMask(true); gL.glClear(256); gL.glPopAttrib(); } boolean initTexturemapping(Context paramContext, int paramInt1, int paramInt2, int paramInt3) { GL gL = context(paramContext).getGL(); char c = gL.isExtensionAvailable("GL_EXT_abgr") ? 32768 : 6408; gL.glBindTexture(3553, paramInt3); gL.glTexImage2D(32868, 0, 6408, paramInt1, paramInt2, 0, c, 5121, null); int[] arrayOfInt = new int[1]; gL.glGetTexLevelParameteriv(32868, 0, 4096, arrayOfInt, 0); if (arrayOfInt[0] <= 0) return false;  gL.glTexImage2D(3553, 0, 6408, paramInt1, paramInt2, 0, c, 5121, null); return true; } void setRenderMode(Context paramContext, int paramInt, boolean paramBoolean) { GL gL = context(paramContext).getGL(); char c = Character.MIN_VALUE; if (paramBoolean) { c = 'Ѕ'; switch (paramInt) { case 0: c = 'Ђ'; break;case 1: c = 'Ѓ'; break;case 2: c = 'Ѕ'; break; }  } else { c = 'Є'; switch (paramInt) { case 0: c = 'Ѐ'; break;case 1: c = 'Ё'; break;case 2: c = 'Є'; break; }  }  gL.glDrawBuffer(c); } void setDepthBufferWriteEnable(Context paramContext, boolean paramBoolean) { GL gL = context(paramContext).getGL(); if (paramBoolean) { gL.glDepthMask(true); } else { gL.glDepthMask(false); }  } private boolean getPropertiesFromCurrentContext(JoglContext paramJoglContext) { GL gL = GLU.getCurrentGL(); int[] arrayOfInt = new int[1]; gL.glGetIntegerv(34018, arrayOfInt, 0); paramJoglContext.setMaxTexCoordSets(arrayOfInt[0]); if (VirtualUniverse.mc.transparentOffScreen) { paramJoglContext.setAlphaClearValue(0.0F); } else { paramJoglContext.setAlphaClearValue(1.0F); }  if (gL.isExtensionAvailable("GL_ARB_vertex_shader")) { gL.glGetIntegerv(34929, arrayOfInt, 0); paramJoglContext.setMaxTexCoordSets(arrayOfInt[0]); }  return true; } private int[] extractVersionInfo(String paramString) { StringTokenizer stringTokenizer = new StringTokenizer(paramString, ". "); int i = Integer.valueOf(stringTokenizer.nextToken()).intValue(); int j = Integer.valueOf(stringTokenizer.nextToken()).intValue(); stringTokenizer = new StringTokenizer(paramString, " "); if (stringTokenizer.hasMoreTokens()) { stringTokenizer.nextToken(); if (stringTokenizer.hasMoreTokens()) { Pattern pattern = Pattern.compile("\\D*(\\d+)\\.(\\d+)\\.?(\\d*).*"); Matcher matcher = pattern.matcher(stringTokenizer.nextToken()); if (matcher.matches()) { int k = Integer.valueOf(matcher.group(1)).intValue(); int m = Integer.valueOf(matcher.group(2)).intValue(); if (k == i && m > j) j = m;  }  }  }  return new int[] { i, j }; } private int getTextureColorTableSize(GL paramGL) { if (!paramGL.isExtensionAvailable("GL_ARB_imaging")) return 0;  paramGL.glColorTable(32957, 6408, 256, 6407, 5124, null); int[] arrayOfInt = new int[1]; paramGL.glGetColorTableParameteriv(32957, 32985, arrayOfInt, 0); return arrayOfInt[0]; } private void checkTextureExtensions(Canvas3D paramCanvas3D, JoglContext paramJoglContext, GL paramGL, boolean paramBoolean) { if (paramBoolean) { paramCanvas3D.textureExtendedFeatures |= 0x4; paramCanvas3D.multiTexAccelerated = true; int[] arrayOfInt = new int[1]; paramGL.glGetIntegerv(34018, arrayOfInt, 0); paramCanvas3D.maxTextureUnits = arrayOfInt[0]; paramCanvas3D.maxTexCoordSets = paramCanvas3D.maxTextureUnits; if (paramGL.isExtensionAvailable("GL_ARB_vertex_shader")) { paramGL.glGetIntegerv(34929, arrayOfInt, 0); paramCanvas3D.maxTexCoordSets = arrayOfInt[0]; }  }  if (paramGL.isExtensionAvailable("GL_SGI_texture_color_table") || paramGL.isExtensionAvailable("GL_ARB_imaging")) { paramCanvas3D.textureExtendedFeatures |= 0x2; paramCanvas3D.textureColorTableSize = getTextureColorTableSize(paramGL); if (paramCanvas3D.textureColorTableSize <= 0) paramCanvas3D.textureExtendedFeatures &= 0xFFFFFFFD;  if (paramCanvas3D.textureColorTableSize > 256) paramCanvas3D.textureColorTableSize = 256;  }  if (paramGL.isExtensionAvailable("GL_ARB_texture_env_combine")) { paramCanvas3D.textureExtendedFeatures |= 0x8; paramCanvas3D.textureExtendedFeatures |= 0x20; } else if (paramGL.isExtensionAvailable("GL_EXT_texture_env_combine")) { paramCanvas3D.textureExtendedFeatures |= 0x8; }  if (paramGL.isExtensionAvailable("GL_NV_register_combiners")) paramCanvas3D.textureExtendedFeatures |= 0x40;  if (paramGL.isExtensionAvailable("GL_ARB_texture_env_dot3") || paramGL.isExtensionAvailable("GL_EXT_texture_env_dot3")) paramCanvas3D.textureExtendedFeatures |= 0x10;  if (paramBoolean) paramCanvas3D.textureExtendedFeatures |= 0x80;  if (paramGL.isExtensionAvailable("GL_SGIS_sharpen_texture")) paramCanvas3D.textureExtendedFeatures |= 0x100;  if (paramGL.isExtensionAvailable("GL_SGIS_detail_texture")) paramCanvas3D.textureExtendedFeatures |= 0x200;  if (paramGL.isExtensionAvailable("GL_SGIS_texture_filter4")) paramCanvas3D.textureExtendedFeatures |= 0x400;  if (paramGL.isExtensionAvailable("GL_EXT_texture_filter_anisotropic")) { paramCanvas3D.textureExtendedFeatures |= 0x800; float[] arrayOfFloat = new float[1]; paramGL.glGetFloatv(34047, arrayOfFloat, 0); paramCanvas3D.anisotropicDegreeMax = arrayOfFloat[0]; }  if (paramGL.isExtensionAvailable("GL_SGIX_texture_lod_bias")) paramCanvas3D.textureExtendedFeatures |= 0x2000;  if (!VirtualUniverse.mc.enforcePowerOfTwo && paramGL.isExtensionAvailable("GL_ARB_texture_non_power_of_two")) paramCanvas3D.textureExtendedFeatures |= 0x8000;  if (paramGL.isExtensionAvailable("GL_SGIS_generate_mipmap")) paramCanvas3D.textureExtendedFeatures |= 0x10000;  } private void checkGLSLShaderExtensions(Canvas3D paramCanvas3D, JoglContext paramJoglContext, GL paramGL, boolean paramBoolean) { if (paramBoolean && paramGL.isExtensionAvailable("GL_ARB_shader_objects") && paramGL.isExtensionAvailable("GL_ARB_shading_language_100")) { paramJoglContext.initGLSLVertexAttributeImpl(); int[] arrayOfInt = new int[1]; paramGL.glGetIntegerv(34930, arrayOfInt, 0); paramCanvas3D.maxTextureImageUnits = arrayOfInt[0]; paramGL.glGetIntegerv(35660, arrayOfInt, 0); paramCanvas3D.maxVertexTextureImageUnits = arrayOfInt[0]; paramGL.glGetIntegerv(35661, arrayOfInt, 0); paramCanvas3D.maxCombinedTextureImageUnits = arrayOfInt[0]; int i = VirtualUniverse.mc.glslVertexAttrOffset; paramJoglContext.setGLSLVertexAttrOffset(i); paramGL.glGetIntegerv(34921, arrayOfInt, 0); paramCanvas3D.maxVertexAttrs = arrayOfInt[0]; paramCanvas3D.maxVertexAttrs -= i; if (paramCanvas3D.maxVertexAttrs < 0) paramCanvas3D.maxVertexAttrs = 0;  paramCanvas3D.shadingLanguageGLSL = true; }  } private boolean createCgContext(JoglContext paramJoglContext) { CGcontext cGcontext = CgGL.cgCreateContext(); int i = CgGL.cgGetError(); if (i != 0) { String str = CgGL.cgGetErrorString(i); System.err.println("JAVA 3D ERROR : Fatal error in creating Cg context: \"" + str + "\""); return false; }  if (cGcontext == null) { System.err.println("JAVA 3D ERROR : Invalid null Cg context"); return false; }  paramJoglContext.setCgContext(cGcontext); if (CgGL.cgGLIsProfileSupported(6150)) { paramJoglContext.setCgVertexProfile(6150); } else if (CgGL.cgGLIsProfileSupported(6146)) { paramJoglContext.setCgVertexProfile(6146); } else { System.err.println("JAVA 3D ERROR : No CG vertex program profile is supported"); paramJoglContext.setCgContext(null); return false; }  if (CgGL.cgGLIsProfileSupported(7000)) { paramJoglContext.setCgFragmentProfile(7000); } else if (CgGL.cgGLIsProfileSupported(6147)) { paramJoglContext.setCgFragmentProfile(6147); } else { System.err.println("JAVA 3D ERROR : No CG fragment program profile is supported"); paramJoglContext.setCgContext(null); return false; }  return true; } private void checkCgShaderExtensions(Canvas3D paramCanvas3D, JoglContext paramJoglContext, GL paramGL, boolean paramBoolean) { if (paramBoolean) { if (!createCgContext(paramJoglContext)) return;  paramCanvas3D.shadingLanguageCg = true; paramCanvas3D.maxTextureImageUnits = paramCanvas3D.maxTextureUnits; paramCanvas3D.maxVertexTextureImageUnits = 0; paramCanvas3D.maxCombinedTextureImageUnits = paramCanvas3D.maxTextureUnits; paramCanvas3D.maxVertexAttrs = 7; paramJoglContext.initCgVertexAttributeImpl(); }  } private void setupCanvasProperties(Canvas3D paramCanvas3D, JoglContext paramJoglContext, GL paramGL, boolean paramBoolean1, boolean paramBoolean2) { paramCanvas3D.multiTexAccelerated = false; paramCanvas3D.maxTextureUnits = 1; paramCanvas3D.maxTexCoordSets = 1; paramCanvas3D.maxTextureImageUnits = 0; paramCanvas3D.maxVertexTextureImageUnits = 0; paramCanvas3D.maxCombinedTextureImageUnits = 0; paramCanvas3D.maxVertexAttrs = 0; paramCanvas3D.extensionsSupported = 0; paramCanvas3D.textureExtendedFeatures = 0; paramCanvas3D.textureColorTableSize = 0; paramCanvas3D.anisotropicDegreeMax = 0.0F; paramCanvas3D.textureBoundaryWidthMax = 0; paramCanvas3D.textureWidthMax = 0; paramCanvas3D.textureHeightMax = 0; paramCanvas3D.texture3DWidthMax = 0; paramCanvas3D.texture3DHeightMax = 0; paramCanvas3D.texture3DDepthMax = 0; paramCanvas3D.shadingLanguageGLSL = false; paramCanvas3D.shadingLanguageCg = false; String str1 = paramGL.glGetString(7938); String str2 = paramGL.glGetString(7936); String str3 = paramGL.glGetString(7937); paramCanvas3D.nativeGraphicsVersion = str1; paramCanvas3D.nativeGraphicsVendor = str2; paramCanvas3D.nativeGraphicsRenderer = str3; int[] arrayOfInt1 = extractVersionInfo(str1); int i = arrayOfInt1[0]; int j = arrayOfInt1[1]; if (i < 1 || (i == 1 && j < 2)) throw new IllegalRenderingStateException("Java 3D ERROR : OpenGL 1.2 or better is required (GL_VERSION=" + i + "." + j + ")");  boolean bool1 = false; boolean bool2 = false; boolean bool = false; if (i == 1) { if (j == 2) System.err.println("JAVA 3D: OpenGL 1.2 detected; will run with reduced functionality");  if (j >= 3) bool = true;  if (j >= 4) bool2 = true;  } else { bool = true; bool2 = true; bool1 = true; }  if (bool1) { assert bool; assert bool2; assert paramGL.isExtensionAvailable("GL_VERSION_2_0"); }  if (bool2) { assert bool; assert paramGL.isExtensionAvailable("GL_VERSION_1_4"); }  if (bool && !$assertionsDisabled && !paramGL.isExtensionAvailable("GL_VERSION_1_3")) throw new AssertionError();  paramCanvas3D.textureExtendedFeatures |= 0x1; paramCanvas3D.textureExtendedFeatures |= 0x1000; if (bool2) paramCanvas3D.textureExtendedFeatures |= 0x10000;  if (paramGL.isExtensionAvailable("GL_EXT_abgr")) paramCanvas3D.extensionsSupported |= 0x2;  paramCanvas3D.extensionsSupported |= 0x4; if (bool) { paramCanvas3D.extensionsSupported |= 0x8; paramJoglContext.setHasMultisample(true); }  if ((paramCanvas3D.extensionsSupported & 0x8) != 0 && !VirtualUniverse.mc.implicitAntialiasing) paramGL.glDisable(32925);  checkTextureExtensions(paramCanvas3D, paramJoglContext, paramGL, bool); if (bool) { checkGLSLShaderExtensions(paramCanvas3D, paramJoglContext, paramGL, paramBoolean1); checkCgShaderExtensions(paramCanvas3D, paramJoglContext, paramGL, paramBoolean2); } else { checkGLSLShaderExtensions(paramCanvas3D, paramJoglContext, paramGL, false); checkCgShaderExtensions(paramCanvas3D, paramJoglContext, paramGL, false); }  if (paramGL.isExtensionAvailable("GL_SUN_gloabl_alpha")) paramCanvas3D.extensionsSupported |= 0x1;  paramCanvas3D.textureBoundaryWidthMax = 1; int[] arrayOfInt2 = new int[1]; paramGL.glGetIntegerv(3379, arrayOfInt2, 0); paramCanvas3D.textureWidthMax = arrayOfInt2[0]; paramCanvas3D.textureHeightMax = arrayOfInt2[0]; arrayOfInt2[0] = -1; paramGL.glGetIntegerv(32883, arrayOfInt2, 0); paramCanvas3D.texture3DWidthMax = arrayOfInt2[0]; paramCanvas3D.texture3DHeightMax = arrayOfInt2[0]; paramCanvas3D.texture3DDepthMax = arrayOfInt2[0]; } private void disableAttribFor2D(GL paramGL) { paramGL.glDisable(3008); paramGL.glDisable(3042); paramGL.glDisable(3058); paramGL.glDisable(2903); paramGL.glDisable(2884); paramGL.glDisable(2929); paramGL.glDisable(2912); paramGL.glDisable(2896); paramGL.glDisable(32823); paramGL.glDisable(2882); paramGL.glDisable(2960); paramGL.glDisable(3553); paramGL.glDisable(3171); paramGL.glDisable(3170); paramGL.glDisable(3168); paramGL.glDisable(3169); for (byte b = 0; b < 6; b++) paramGL.glDisable('　' + b);  paramGL.glDisable(32879); paramGL.glDisable(34067); if (paramGL.isExtensionAvailable("GL_NV_register_combiners")) paramGL.glDisable(34082);  if (paramGL.isExtensionAvailable("GL_SGI_texture_color_table")) paramGL.glDisable(32956);  if (paramGL.isExtensionAvailable("GL_SUN_global_alpha")) paramGL.glDisable(33241);  } private void disableAttribForRaster(GL paramGL) { paramGL.glDisable(2903); paramGL.glDisable(2884); paramGL.glDisable(2896); paramGL.glDisable(32823); paramGL.glDisable(2882); if (paramGL.isExtensionAvailable("GL_SUN_global_alpha")) paramGL.glDisable(33241);  } private void copyTranspose(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) { paramArrayOfDouble2[0] = paramArrayOfDouble1[0]; paramArrayOfDouble2[1] = paramArrayOfDouble1[4]; paramArrayOfDouble2[2] = paramArrayOfDouble1[8]; paramArrayOfDouble2[3] = paramArrayOfDouble1[12]; paramArrayOfDouble2[4] = paramArrayOfDouble1[1]; paramArrayOfDouble2[5] = paramArrayOfDouble1[5]; paramArrayOfDouble2[6] = paramArrayOfDouble1[9]; paramArrayOfDouble2[7] = paramArrayOfDouble1[13]; paramArrayOfDouble2[8] = paramArrayOfDouble1[2]; paramArrayOfDouble2[9] = paramArrayOfDouble1[6]; paramArrayOfDouble2[10] = paramArrayOfDouble1[10]; paramArrayOfDouble2[11] = paramArrayOfDouble1[14]; paramArrayOfDouble2[12] = paramArrayOfDouble1[3]; paramArrayOfDouble2[13] = paramArrayOfDouble1[7]; paramArrayOfDouble2[14] = paramArrayOfDouble1[11]; paramArrayOfDouble2[15] = paramArrayOfDouble1[15]; } GraphicsConfiguration getGraphicsConfig(GraphicsConfiguration paramGraphicsConfiguration) { JoglGraphicsConfiguration joglGraphicsConfiguration = (JoglGraphicsConfiguration)paramGraphicsConfiguration; IndexCapabilitiesChooser indexCapabilitiesChooser = null; if (joglGraphicsConfiguration.getChosenIndex() >= 0) indexCapabilitiesChooser = new IndexCapabilitiesChooser(joglGraphicsConfiguration.getChosenIndex());  AbstractGraphicsConfiguration abstractGraphicsConfiguration = GLDrawableFactory.getFactory().chooseGraphicsConfiguration(joglGraphicsConfiguration.getGLCapabilities(), indexCapabilitiesChooser, new AWTGraphicsDevice(joglGraphicsConfiguration.getDevice())); if (abstractGraphicsConfiguration == null) return null;  return ((AWTGraphicsConfiguration)abstractGraphicsConfiguration).getGraphicsConfiguration(); } long getFbConfig(GraphicsConfigInfo paramGraphicsConfigInfo) { return 0L; } GraphicsConfiguration getBestConfiguration(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsConfiguration[] paramArrayOfGraphicsConfiguration) { GLCapabilities gLCapabilities1 = new GLCapabilities(); gLCapabilities1.setDoubleBuffered((paramGraphicsConfigTemplate3D.getDoubleBuffer() <= 2)); gLCapabilities1.setStereo((paramGraphicsConfigTemplate3D.getStereo() <= 2)); gLCapabilities1.setDepthBits(paramGraphicsConfigTemplate3D.getDepthSize()); gLCapabilities1.setStencilBits(paramGraphicsConfigTemplate3D.getStencilSize()); gLCapabilities1.setRedBits(Math.max(5, paramGraphicsConfigTemplate3D.getRedSize())); gLCapabilities1.setGreenBits(Math.max(5, paramGraphicsConfigTemplate3D.getGreenSize())); gLCapabilities1.setBlueBits(Math.max(5, paramGraphicsConfigTemplate3D.getBlueSize())); gLCapabilities1.setSampleBuffers((paramGraphicsConfigTemplate3D.getSceneAntialiasing() <= 2)); gLCapabilities1.setNumSamples(4); if (VirtualUniverse.mc.transparentOffScreen) gLCapabilities1.setAlphaBits(1);  ArrayList arrayList = new ArrayList(); if (paramGraphicsConfigTemplate3D.getStereo() == 2) arrayList.add(new Integer(1));  if (paramGraphicsConfigTemplate3D.getSceneAntialiasing() == 2) arrayList.add(new Integer(2));  if (paramGraphicsConfigTemplate3D.getDoubleBuffer() == 2) arrayList.add(new Integer(3));  GraphicsDevice graphicsDevice = paramArrayOfGraphicsConfiguration[0].getDevice(); boolean bool = true; CapabilitiesCapturer capabilitiesCapturer = null; while (bool) { Frame frame = new Frame(graphicsDevice.getDefaultConfiguration()); frame.setUndecorated(true); frame.setLayout(new BorderLayout()); capabilitiesCapturer = new CapabilitiesCapturer(); try { QueryCanvas queryCanvas = new QueryCanvas(gLCapabilities1, capabilitiesCapturer, graphicsDevice); frame.add(queryCanvas, "Center"); frame.setSize(1, 1); frame.setVisible(true); queryCanvas.doQuery(); if (!EventQueue.isDispatchThread()) synchronized (capabilitiesCapturer) { if (!capabilitiesCapturer.done()) try { capabilitiesCapturer.wait(1000L); } catch (InterruptedException interruptedException) {}  }   disposeOnEDT(frame); bool = false; } catch (GLException gLException) { if (arrayList.size() == 0) { bool = false; continue; }  int j = ((Integer)arrayList.remove(0)).intValue(); switch (j) { case 1: gLCapabilities1.setStereo(false); continue;case 2: gLCapabilities1.setSampleBuffers(false); continue;case 3: gLCapabilities1.setDoubleBuffered(false); continue; }  throw new AssertionError("missing case statement"); }  }  int i = capabilitiesCapturer.getChosenIndex(); GLCapabilities gLCapabilities2 = null; if (i < 0) { gLCapabilities2 = gLCapabilities1; } else { gLCapabilities2 = capabilitiesCapturer.getCapabilities(); }  JoglGraphicsConfiguration joglGraphicsConfiguration = new JoglGraphicsConfiguration(gLCapabilities2, i, graphicsDevice); synchronized (Canvas3D.graphicsConfigTable) { GraphicsConfigInfo graphicsConfigInfo = new GraphicsConfigInfo(paramGraphicsConfigTemplate3D); Canvas3D.graphicsConfigTable.put(joglGraphicsConfiguration, graphicsConfigInfo); }  return joglGraphicsConfiguration; } boolean isGraphicsConfigSupported(GraphicsConfigTemplate3D paramGraphicsConfigTemplate3D, GraphicsConfiguration paramGraphicsConfiguration) { return true; } boolean hasDoubleBuffer(Canvas3D paramCanvas3D) { return caps(paramCanvas3D).getDoubleBuffered(); } boolean hasStereo(Canvas3D paramCanvas3D) { return caps(paramCanvas3D).getStereo(); } int getStencilSize(Canvas3D paramCanvas3D) { return caps(paramCanvas3D).getStencilBits(); } boolean hasSceneAntialiasingMultisample(Canvas3D paramCanvas3D) { return caps(paramCanvas3D).getSampleBuffers(); } boolean hasSceneAntialiasingAccum(Canvas3D paramCanvas3D) { GLCapabilities gLCapabilities = caps(paramCanvas3D); return (gLCapabilities.getAccumRedBits() > 0 && gLCapabilities.getAccumGreenBits() > 0 && gLCapabilities.getAccumBlueBits() > 0); } long getDisplay() { return 0L; } private boolean checkedForGetScreenMethod = false; private Method getScreenMethod = null; private static ThreadLocal nioVertexTemp; private static ThreadLocal nioVertexDoubleTemp; private static ThreadLocal nioColorTemp; private static ThreadLocal nioColorByteTemp; private static ThreadLocal nioNormalTemp; private static ThreadLocal nioTexCoordSetTemp; private static ThreadLocal nioVertexAttrSetTemp; int getScreen(final GraphicsDevice graphicsDevice) { if (!this.checkedForGetScreenMethod) AccessController.doPrivileged(new PrivilegedAction() { public Object run() { try { JoglPipeline.this.getScreenMethod = graphicsDevice.getClass().getDeclaredMethod("getScreen", new Class[0]); JoglPipeline.this.getScreenMethod.setAccessible(true); } catch (Exception exception) {} JoglPipeline.this.checkedForGetScreenMethod = true; return null; } });  if (this.getScreenMethod != null) try { return ((Integer)this.getScreenMethod.invoke(paramGraphicsDevice, (Object[])null)).intValue(); } catch (Exception exception) { throw new RuntimeException(exception); }   return 0; } class QueryCanvas extends Canvas {
/*      */     private GLDrawable drawable; private JoglPipeline.ExtendedCapabilitiesChooser chooser; private boolean alreadyRan; public QueryCanvas(GLCapabilities param1GLCapabilities, JoglPipeline.ExtendedCapabilitiesChooser param1ExtendedCapabilitiesChooser, GraphicsDevice param1GraphicsDevice) { super(JoglPipeline.unwrap((AWTGraphicsConfiguration)GLDrawableFactory.getFactory().chooseGraphicsConfiguration(param1GLCapabilities, param1ExtendedCapabilitiesChooser, new AWTGraphicsDevice(param1GraphicsDevice)))); this.drawable = GLDrawableFactory.getFactory().getGLDrawable(this, param1GLCapabilities, param1ExtendedCapabilitiesChooser); this.chooser = param1ExtendedCapabilitiesChooser; } public void addNotify() { super.addNotify(); this.drawable.setRealized(true); } public void doQuery() { if (this.alreadyRan) return;  gLContext = this.drawable.createContext(null); int i = gLContext.makeCurrent(); if (i != 0) try { this.chooser.init(gLContext); } finally { gLContext.release(); }   gLContext.destroy(); this.alreadyRan = true; } } private static GraphicsConfiguration unwrap(AWTGraphicsConfiguration paramAWTGraphicsConfiguration) { if (paramAWTGraphicsConfiguration == null) return null;  return paramAWTGraphicsConfiguration.getGraphicsConfiguration(); } class CapabilitiesCapturer extends DefaultGLCapabilitiesChooser implements ExtendedCapabilitiesChooser {
/*      */     private boolean done; private GLCapabilities capabilities; private int chosenIndex = -1; public boolean done() { return this.done; } public GLCapabilities getCapabilities() { return this.capabilities; } public int getChosenIndex() { return this.chosenIndex; } public int chooseCapabilities(GLCapabilities param1GLCapabilities, GLCapabilities[] param1ArrayOfGLCapabilities, int param1Int) { int i = super.chooseCapabilities(param1GLCapabilities, param1ArrayOfGLCapabilities, param1Int); this.capabilities = param1ArrayOfGLCapabilities[i]; this.chosenIndex = i; markDone(); return i; } public void init(GLContext param1GLContext) { kick(); } private void markDone() { synchronized (this) { this.done = true; notifyAll(); }  } private void kick() { synchronized (this) { notifyAll(); }  } } class ContextQuerier extends DefaultGLCapabilitiesChooser implements ExtendedCapabilitiesChooser {
/*      */     private Canvas3D canvas; private boolean glslLibraryAvailable; private boolean cgLibraryAvailable; private boolean done; public ContextQuerier(Canvas3D param1Canvas3D, boolean param1Boolean1, boolean param1Boolean2) { this.canvas = param1Canvas3D; this.glslLibraryAvailable = param1Boolean1; this.cgLibraryAvailable = param1Boolean2; } public boolean done() { return this.done; } public void init(GLContext param1GLContext) { JoglContext joglContext = new JoglContext(param1GLContext); if (JoglPipeline.this.getPropertiesFromCurrentContext(joglContext)) JoglPipeline.this.setupCanvasProperties(this.canvas, joglContext, param1GLContext.getGL(), this.glslLibraryAvailable, this.cgLibraryAvailable);  markDone(); } private void markDone() { synchronized (this) { this.done = true; notifyAll(); }  } } class IndexCapabilitiesChooser implements GLCapabilitiesChooser {
/* 9237 */     private int indexToChoose; IndexCapabilitiesChooser(int param1Int) { this.indexToChoose = param1Int; } public int chooseCapabilities(GLCapabilities param1GLCapabilities, GLCapabilities[] param1ArrayOfGLCapabilities, int param1Int) { return this.indexToChoose; } } private void disposeOnEDT(final Frame f) { Runnable runnable = new Runnable() { public void run() { f.setVisible(false); f.dispose(); } }; if (!EventQueue.isDispatchThread()) { EventQueue.invokeLater(runnable); } else { runnable.run(); }  } DrawingSurfaceObject createDrawingSurfaceObject(Canvas3D paramCanvas3D) { return new JoglDrawingSurfaceObject(paramCanvas3D); } void freeDrawingSurface(Canvas3D paramCanvas3D, DrawingSurfaceObject paramDrawingSurfaceObject) {} void freeDrawingSurfaceNative(Object paramObject) {} GLContext context(Context paramContext) { if (paramContext == null) return null;  return ((JoglContext)paramContext).getGLContext(); } GLDrawable drawable(Drawable paramDrawable) { if (paramDrawable == null) return null;  return ((JoglDrawable)paramDrawable).getGLDrawable(); } GLCapabilities caps(Canvas3D paramCanvas3D) { return ((JoglGraphicsConfiguration)paramCanvas3D.graphicsConfiguration).getGLCapabilities(); } private static FloatBuffer getVertexArrayBuffer(float[] paramArrayOfFloat, boolean paramBoolean) { return getNIOBuffer(paramArrayOfFloat, nioVertexTemp, paramBoolean); }
/*      */ 
/*      */ 
/*      */   
/* 9241 */   private static DoubleBuffer getVertexArrayBuffer(double[] paramArrayOfDouble) { return getVertexArrayBuffer(paramArrayOfDouble, true); }
/*      */ 
/*      */ 
/*      */   
/* 9245 */   private static DoubleBuffer getVertexArrayBuffer(double[] paramArrayOfDouble, boolean paramBoolean) { return getNIOBuffer(paramArrayOfDouble, nioVertexDoubleTemp, true); }
/*      */ 
/*      */ 
/*      */   
/* 9249 */   private static FloatBuffer getColorArrayBuffer(float[] paramArrayOfFloat) { return getColorArrayBuffer(paramArrayOfFloat, true); }
/*      */ 
/*      */ 
/*      */   
/* 9253 */   private static FloatBuffer getColorArrayBuffer(float[] paramArrayOfFloat, boolean paramBoolean) { return getNIOBuffer(paramArrayOfFloat, nioColorTemp, true); }
/*      */ 
/*      */ 
/*      */   
/* 9257 */   private static ByteBuffer getColorArrayBuffer(byte[] paramArrayOfByte) { return getColorArrayBuffer(paramArrayOfByte, true); }
/*      */ 
/*      */ 
/*      */   
/* 9261 */   private static ByteBuffer getColorArrayBuffer(byte[] paramArrayOfByte, boolean paramBoolean) { return getNIOBuffer(paramArrayOfByte, nioColorByteTemp, true); }
/*      */ 
/*      */ 
/*      */   
/* 9265 */   private static FloatBuffer getNormalArrayBuffer(float[] paramArrayOfFloat) { return getNormalArrayBuffer(paramArrayOfFloat, true); }
/*      */ 
/*      */ 
/*      */   
/* 9269 */   private static FloatBuffer getNormalArrayBuffer(float[] paramArrayOfFloat, boolean paramBoolean) { return getNIOBuffer(paramArrayOfFloat, nioNormalTemp, true); }
/*      */ 
/*      */ 
/*      */   
/* 9273 */   private static FloatBuffer[] getTexCoordSetBuffer(Object[] paramArrayOfObject) { return getNIOBuffer(paramArrayOfObject, nioTexCoordSetTemp); }
/*      */ 
/*      */ 
/*      */   
/* 9277 */   private static FloatBuffer[] getVertexAttrSetBuffer(Object[] paramArrayOfObject) { return getNIOBuffer(paramArrayOfObject, nioVertexAttrSetTemp); }
/*      */ 
/*      */   
/*      */   private static FloatBuffer getNIOBuffer(float[] paramArrayOfFloat, ThreadLocal paramThreadLocal, boolean paramBoolean) {
/* 9281 */     if (paramArrayOfFloat == null) {
/* 9282 */       return null;
/*      */     }
/* 9284 */     FloatBuffer floatBuffer = (FloatBuffer)paramThreadLocal.get();
/* 9285 */     if (floatBuffer == null) {
/* 9286 */       floatBuffer = BufferUtil.newFloatBuffer(paramArrayOfFloat.length);
/* 9287 */       paramThreadLocal.set(floatBuffer);
/*      */     } else {
/* 9289 */       floatBuffer.rewind();
/* 9290 */       if (floatBuffer.remaining() < paramArrayOfFloat.length) {
/* 9291 */         int i = Math.max(2 * floatBuffer.remaining(), paramArrayOfFloat.length);
/* 9292 */         floatBuffer = BufferUtil.newFloatBuffer(i);
/* 9293 */         paramThreadLocal.set(floatBuffer);
/*      */       } 
/*      */     } 
/* 9296 */     if (paramBoolean) {
/* 9297 */       floatBuffer.put(paramArrayOfFloat);
/* 9298 */       floatBuffer.rewind();
/*      */     } 
/* 9300 */     return floatBuffer;
/*      */   }
/*      */   
/*      */   private static DoubleBuffer getNIOBuffer(double[] paramArrayOfDouble, ThreadLocal paramThreadLocal, boolean paramBoolean) {
/* 9304 */     if (paramArrayOfDouble == null) {
/* 9305 */       return null;
/*      */     }
/* 9307 */     DoubleBuffer doubleBuffer = (DoubleBuffer)paramThreadLocal.get();
/* 9308 */     if (doubleBuffer == null) {
/* 9309 */       doubleBuffer = BufferUtil.newDoubleBuffer(paramArrayOfDouble.length);
/* 9310 */       paramThreadLocal.set(doubleBuffer);
/*      */     } else {
/* 9312 */       doubleBuffer.rewind();
/* 9313 */       if (doubleBuffer.remaining() < paramArrayOfDouble.length) {
/* 9314 */         int i = Math.max(2 * doubleBuffer.remaining(), paramArrayOfDouble.length);
/* 9315 */         doubleBuffer = BufferUtil.newDoubleBuffer(i);
/* 9316 */         paramThreadLocal.set(doubleBuffer);
/*      */       } 
/*      */     } 
/* 9319 */     if (paramBoolean) {
/* 9320 */       doubleBuffer.put(paramArrayOfDouble);
/* 9321 */       doubleBuffer.rewind();
/*      */     } 
/* 9323 */     return doubleBuffer;
/*      */   }
/*      */   
/*      */   private static ByteBuffer getNIOBuffer(byte[] paramArrayOfByte, ThreadLocal paramThreadLocal, boolean paramBoolean) {
/* 9327 */     if (paramArrayOfByte == null) {
/* 9328 */       return null;
/*      */     }
/* 9330 */     ByteBuffer byteBuffer = (ByteBuffer)paramThreadLocal.get();
/* 9331 */     if (byteBuffer == null) {
/* 9332 */       byteBuffer = BufferUtil.newByteBuffer(paramArrayOfByte.length);
/* 9333 */       paramThreadLocal.set(byteBuffer);
/*      */     } else {
/* 9335 */       byteBuffer.rewind();
/* 9336 */       if (byteBuffer.remaining() < paramArrayOfByte.length) {
/* 9337 */         int i = Math.max(2 * byteBuffer.remaining(), paramArrayOfByte.length);
/* 9338 */         byteBuffer = BufferUtil.newByteBuffer(i);
/* 9339 */         paramThreadLocal.set(byteBuffer);
/*      */       } 
/*      */     } 
/* 9342 */     if (paramBoolean) {
/* 9343 */       byteBuffer.put(paramArrayOfByte);
/* 9344 */       byteBuffer.rewind();
/*      */     } 
/* 9346 */     return byteBuffer;
/*      */   }
/*      */   
/*      */   private static FloatBuffer[] getNIOBuffer(Object[] paramArrayOfObject, ThreadLocal paramThreadLocal) {
/* 9350 */     if (paramArrayOfObject == null) {
/* 9351 */       return null;
/*      */     }
/* 9353 */     FloatBuffer[] arrayOfFloatBuffer = (FloatBuffer[])paramThreadLocal.get();
/*      */ 
/*      */     
/* 9356 */     if (arrayOfFloatBuffer == null) {
/* 9357 */       arrayOfFloatBuffer = new FloatBuffer[paramArrayOfObject.length];
/* 9358 */       paramThreadLocal.set(arrayOfFloatBuffer);
/* 9359 */     } else if (arrayOfFloatBuffer.length < paramArrayOfObject.length) {
/* 9360 */       FloatBuffer[] arrayOfFloatBuffer1 = new FloatBuffer[paramArrayOfObject.length];
/* 9361 */       System.arraycopy(arrayOfFloatBuffer, 0, arrayOfFloatBuffer1, 0, arrayOfFloatBuffer.length);
/* 9362 */       arrayOfFloatBuffer = arrayOfFloatBuffer1;
/* 9363 */       paramThreadLocal.set(arrayOfFloatBuffer);
/*      */     } 
/*      */ 
/*      */     
/* 9367 */     for (byte b = 0; b < paramArrayOfObject.length; b++) {
/* 9368 */       float[] arrayOfFloat = (float[])paramArrayOfObject[b];
/* 9369 */       FloatBuffer floatBuffer = arrayOfFloatBuffer[b];
/* 9370 */       if (floatBuffer == null) {
/* 9371 */         floatBuffer = BufferUtil.newFloatBuffer(arrayOfFloat.length);
/* 9372 */         arrayOfFloatBuffer[b] = floatBuffer;
/*      */       } else {
/* 9374 */         floatBuffer.rewind();
/* 9375 */         if (floatBuffer.remaining() < arrayOfFloat.length) {
/* 9376 */           int i = Math.max(2 * floatBuffer.remaining(), arrayOfFloat.length);
/* 9377 */           floatBuffer = BufferUtil.newFloatBuffer(i);
/* 9378 */           arrayOfFloatBuffer[b] = floatBuffer;
/*      */         } 
/*      */       } 
/* 9381 */       floatBuffer.put(arrayOfFloat);
/* 9382 */       floatBuffer.rewind();
/*      */     } 
/*      */     
/* 9385 */     return arrayOfFloatBuffer;
/*      */   }
/*      */   
/*      */   static interface ExtendedCapabilitiesChooser extends GLCapabilitiesChooser {
/*      */     void init(GLContext param1GLContext);
/*      */   }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\JoglPipeline.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */