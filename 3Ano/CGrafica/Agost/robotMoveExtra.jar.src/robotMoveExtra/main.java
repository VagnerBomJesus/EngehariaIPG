/*     */ package robotMoveExtra;
/*     */ 
/*     */ import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
/*     */ import com.sun.j3d.utils.geometry.Box;
/*     */ import com.sun.j3d.utils.geometry.Cylinder;
/*     */ import com.sun.j3d.utils.image.TextureLoader;
/*     */ import com.sun.j3d.utils.universe.SimpleUniverse;
/*     */ import java.applet.Applet;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.net.URL;
/*     */ import javax.media.j3d.Alpha;
/*     */ import javax.media.j3d.AmbientLight;
/*     */ import javax.media.j3d.Appearance;
/*     */ import javax.media.j3d.Background;
/*     */ import javax.media.j3d.Billboard;
/*     */ import javax.media.j3d.BoundingSphere;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.media.j3d.ImageComponent2D;
/*     */ import javax.media.j3d.Material;
/*     */ import javax.media.j3d.PointLight;
/*     */ import javax.media.j3d.RotPosPathInterpolator;
/*     */ import javax.media.j3d.Shape3D;
/*     */ import javax.media.j3d.Texture2D;
/*     */ import javax.media.j3d.TextureAttributes;
/*     */ import javax.media.j3d.Transform3D;
/*     */ import javax.media.j3d.TransformGroup;
/*     */ import javax.vecmath.AxisAngle4d;
/*     */ import javax.vecmath.Color3f;
/*     */ import javax.vecmath.Point3d;
/*     */ import javax.vecmath.Point3f;
/*     */ import javax.vecmath.Vector3d;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class main
/*     */   extends Applet
/*     */ {
/*     */   BoundingSphere bounds;
/*     */   Appearance ap;
/*     */   Appearance apT;
/*     */   Appearance apRobo;
/*     */   Appearance apPreto;
/*     */   
/*     */   public static void main(String[] args) {}
/*     */   
/*     */   public void init() {
/*  53 */     GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
/*  54 */     Canvas3D cv = new Canvas3D(gc);
/*  55 */     setLayout(new BorderLayout());
/*  56 */     add(cv, "Center");
/*     */ 
/*     */     
/*  59 */     BranchGroup bg = createSceneGraph();
/*  60 */     bg.compile();
/*     */ 
/*     */     
/*  63 */     SimpleUniverse su = new SimpleUniverse(cv);
/*     */ 
/*     */     
/*  66 */     Transform3D locator = new Transform3D();
/*  67 */     locator.lookAt(new Point3d(0.0D, 4.1D, 4.1D), new Point3d(0.0D, 0.0D, 0.0D), new Vector3d(0.0D, 1.0D, 0.0D));
/*  68 */     locator.invert();
/*  69 */     su.getViewingPlatform().getViewPlatformTransform().setTransform(locator);
/*     */ 
/*     */     
/*  72 */     this.bounds = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 4.0D);
/*     */ 
/*     */     
/*  75 */     OrbitBehavior orbit = new OrbitBehavior(cv);
/*  76 */     orbit.setSchedulingBounds(new BoundingSphere());
/*  77 */     su.getViewingPlatform().setViewPlatformBehavior(orbit);
/*     */     
/*  79 */     su.addBranchGraph(bg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   InterpolatorData data = new InterpolatorData();
/*  89 */   float RBT_coordX = 0.08F;
/*  90 */   float RBT_coordY = 0.0F;
/*  91 */   float RBT_coordZ = 1.1F;
/*  92 */   float RBT_angulo = 0.0F;
/*     */ 
/*     */   
/*     */   private BranchGroup createSceneGraph() {
/*  96 */     float madSml_height = 0.23F;
/*  97 */     float madSml_width = 0.01F;
/*     */     
/*  99 */     float madStg_height = 0.23F;
/* 100 */     float madStg_width = 0.04F;
/*     */     
/* 102 */     BranchGroup root = new BranchGroup();
/*     */ 
/*     */     
/* 105 */     this.bounds = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 4.0D);
/*     */ 
/*     */     
/* 108 */     this.ap = new Appearance();
/* 109 */     this.ap.setMaterial(new Material());
/*     */     
/* 111 */     Material m = new Material();
/* 112 */     m.setAmbientColor(new Color3f(0.4F, 0.4F, 0.4F));
/* 113 */     m.setDiffuseColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 114 */     m.setSpecularColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 115 */     m.setShininess(25.0F);
/*     */     
/* 117 */     this.ap.setMaterial(m);
/*     */ 
/*     */     
/* 120 */     this.apPreto = new Appearance();
/* 121 */     this.apPreto.setMaterial(new Material());
/*     */     
/* 123 */     Material mPreto = new Material();
/*     */     
/* 125 */     mPreto.setAmbientColor(new Color3f(0.25F, 0.25F, 0.25F));
/* 126 */     mPreto.setDiffuseColor(new Color3f(0.34F, 0.34F, 0.34F));
/* 127 */     mPreto.setSpecularColor(new Color3f(0.2F, 0.2F, 0.2F));
/* 128 */     mPreto.setShininess(25.0F);
/*     */     
/* 130 */     this.apPreto.setMaterial(mPreto);
/*     */ 
/*     */     
/* 133 */     this.apRobo = new Appearance();
/* 134 */     this.apRobo.setMaterial(new Material());
/*     */ 
/*     */     
/* 137 */     TransformGroup movetg = new TransformGroup();
/* 138 */     movetg.setCapability(17);
/* 139 */     movetg.setCapability(18);
/* 140 */     root.addChild(movetg);
/*     */     
/* 142 */     Transform3D tr = new Transform3D();
/* 143 */     TransformGroup tg = new TransformGroup(tr);
/*     */ 
/*     */ 
/*     */     
/* 147 */     DesignRobot RobotAB = new DesignRobot();
/*     */     
/* 149 */     TransformGroup bbtg = new TransformGroup();
/* 150 */     bbtg.setCapability(18);
/* 151 */     root.addChild(bbtg);
/*     */     
/* 153 */     Billboard bb = new Billboard(bbtg, 1, new Point3f(0.0F, 0.0F, 0.0F));
/* 154 */     bb.setSchedulingBounds(this.bounds);
/* 155 */     bbtg.addChild(bb);
/*     */     
/* 157 */     Shape3D vela = new ImagePanel("images/vela.png");
/* 158 */     tr = new Transform3D();
/* 159 */     tr.setScale(0.1D);
/* 160 */     tr.setTranslation(new Vector3f(-0.5F, -0.23F, 2.2F));
/* 161 */     tg = new TransformGroup(tr);
/* 162 */     tg.addChild(vela);
/*     */     
/* 164 */     bbtg.addChild(tg);
/*     */ 
/*     */     
/* 167 */     Alpha alpha = new Alpha(-1, 20000L);
/*     */     
/* 169 */     refreshMove();
/*     */     
/* 171 */     this.RBT_coordZ -= 0.75F;
/* 172 */     refreshMove();
/*     */     
/* 174 */     this.RBT_angulo -= 90.0F;
/* 175 */     refreshMove();
/*     */     
/* 177 */     this.RBT_coordX += 0.92F;
/* 178 */     refreshMove();
/*     */     
/* 180 */     this.RBT_angulo -= 90.0F;
/* 181 */     refreshMove();
/*     */     
/* 183 */     this.RBT_coordZ += 0.1F;
/* 184 */     refreshMove();
/*     */     
/* 186 */     this.RBT_coordZ -= 0.1F;
/* 187 */     refreshMove();
/*     */     
/* 189 */     this.RBT_angulo += 90.0F;
/* 190 */     refreshMove();
/*     */     
/* 192 */     this.RBT_coordX += 0.05F;
/* 193 */     refreshMove();
/*     */     
/* 195 */     this.RBT_angulo += 90.0F;
/* 196 */     refreshMove();
/*     */     
/* 198 */     this.RBT_coordZ -= 0.5F;
/* 199 */     refreshMove();
/*     */     
/* 201 */     this.RBT_angulo += 90.0F;
/* 202 */     refreshMove();
/*     */     
/* 204 */     this.RBT_coordX -= 0.28F;
/* 205 */     refreshMove();
/*     */     
/* 207 */     this.RBT_angulo -= 90.0F;
/* 208 */     refreshMove();
/*     */     
/* 210 */     this.RBT_coordZ -= 0.3F;
/* 211 */     refreshMove();
/*     */     
/* 213 */     this.RBT_coordZ += 0.3F;
/* 214 */     refreshMove();
/*     */     
/* 216 */     this.RBT_angulo += 90.0F;
/* 217 */     refreshMove();
/*     */     
/* 219 */     this.RBT_coordX -= 0.58F;
/* 220 */     refreshMove();
/*     */     
/* 222 */     this.RBT_angulo -= 90.0F;
/* 223 */     refreshMove();
/*     */     
/* 225 */     this.RBT_coordZ -= 0.95F;
/* 226 */     refreshMove();
/*     */     
/* 228 */     this.RBT_angulo += 90.0F;
/* 229 */     refreshMove();
/*     */     
/* 231 */     this.RBT_coordX -= 0.28F;
/* 232 */     refreshMove();
/*     */     
/* 234 */     this.RBT_angulo += 90.0F;
/* 235 */     refreshMove();
/*     */     
/* 237 */     this.RBT_coordZ += 0.38F;
/* 238 */     refreshMove();
/*     */     
/* 240 */     this.RBT_angulo -= 90.0F;
/* 241 */     refreshMove();
/*     */     
/* 243 */     this.RBT_coordX -= 0.5F;
/* 244 */     refreshMove();
/*     */     
/* 246 */     this.RBT_coordX += 0.5F;
/* 247 */     refreshMove();
/*     */     
/* 249 */     this.RBT_angulo += 90.0F;
/* 250 */     refreshMove();
/*     */     
/* 252 */     this.RBT_coordZ += 0.8F;
/* 253 */     refreshMove();
/*     */     
/* 255 */     this.RBT_angulo -= 90.0F;
/* 256 */     refreshMove();
/*     */     
/* 258 */     this.RBT_coordX -= 0.3F;
/* 259 */     refreshMove();
/*     */     
/* 261 */     this.RBT_angulo += 90.0F;
/* 262 */     refreshMove();
/*     */     
/* 264 */     this.RBT_coordZ += 0.13F;
/* 265 */     refreshMove();
/*     */     
/* 267 */     this.RBT_angulo += 90.0F;
/* 268 */     refreshMove();
/*     */     
/* 270 */     this.RBT_coordX += 0.42F;
/* 271 */     refreshMove();
/*     */     
/* 273 */     this.RBT_angulo -= 90.0F;
/* 274 */     refreshMove();
/*     */     
/* 276 */     this.RBT_coordZ += 0.88F;
/* 277 */     refreshMove();
/*     */     
/* 279 */     this.RBT_angulo -= 90.0F;
/* 280 */     refreshMove();
/*     */     
/* 282 */     this.RBT_coordX -= 0.4F;
/* 283 */     refreshMove();
/*     */     
/* 285 */     this.RBT_angulo -= 90.0F;
/* 286 */     refreshMove();
/*     */     
/* 288 */     this.RBT_coordZ -= 0.32F;
/* 289 */     refreshMove();
/*     */     
/* 291 */     this.RBT_angulo += 90.0F;
/* 292 */     refreshMove();
/* 293 */     this.RBT_angulo -= 10.0F;
/* 294 */     this.RBT_angulo += 30.0F;
/* 295 */     refreshMove();
/*     */     
/* 297 */     this.RBT_angulo -= 30.0F;
/* 298 */     refreshMove();
/*     */     
/* 300 */     this.RBT_angulo += 30.0F;
/* 301 */     refreshMove();
/*     */     
/* 303 */     this.RBT_angulo -= 30.0F;
/* 304 */     this.RBT_angulo += 10.0F;
/* 305 */     refreshMove();
/*     */     
/* 307 */     this.RBT_angulo += 90.0F;
/* 308 */     refreshMove();
/*     */     
/* 310 */     this.RBT_coordZ += 0.35F;
/* 311 */     refreshMove();
/*     */     
/* 313 */     this.RBT_angulo += 90.0F;
/* 314 */     refreshMove();
/*     */     
/* 316 */     this.RBT_coordX += 0.45F;
/* 317 */     refreshMove();
/*     */     
/* 319 */     this.RBT_angulo += 90.0F;
/* 320 */     refreshMove();
/*     */ 
/*     */ 
/*     */     
/* 324 */     TransformGroup moveTg = new TransformGroup();
/* 325 */     moveTg.setCapability(17);
/* 326 */     moveTg.setCapability(18);
/*     */     
/* 328 */     root.addChild(moveTg);
/* 329 */     moveTg.addChild(RobotAB);
/* 330 */     RotPosPathInterpolator interpolator = new RotPosPathInterpolator(alpha, moveTg, new Transform3D(), this.data.getKnots(), this.data.getOrientations(), this.data.getPositions());
/* 331 */     interpolator.setSchedulingBounds(this.bounds);
/* 332 */     moveTg.addChild(interpolator);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 337 */     Box chaoArena = new Box(1.4F, 0.04F, 1.4F, 1, this.apPreto);
/* 338 */     tr = new Transform3D();
/* 339 */     tr.set(new Vector3f(0.0F, -0.2F, 0.0F));
/* 340 */     tg = new TransformGroup(tr);
/* 341 */     root.addChild(tg);
/* 342 */     tg.addChild(chaoArena);
/*     */ 
/*     */     
/* 345 */     Material m_cyl = new Material();
/* 346 */     m_cyl.setAmbientColor(new Color3f(0.24725F, 0.1995F, 0.027451F));
/* 347 */     m_cyl.setDiffuseColor(new Color3f(0.780392F, 0.568627F, 0.113725F));
/* 348 */     m_cyl.setSpecularColor(new Color3f(0.75164F, 0.941176F, 0.807843F));
/* 349 */     m_cyl.setShininess(51.2F);
/* 350 */     Appearance ap_cyl = new Appearance();
/* 351 */     ap_cyl.setMaterial(m_cyl);
/*     */     
/* 353 */     Cylinder cyl = new Cylinder(0.15F, 0.7F, 1, ap_cyl);
/* 354 */     Transform3D tr_cyl = new Transform3D();
/* 355 */     tr_cyl.setScale(0.6D);
/* 356 */     tr_cyl.setTranslation(new Vector3f(0.9F, 0.0F, -0.95F));
/* 357 */     tr_cyl.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(180.0D)));
/* 358 */     TransformGroup tg_cyl = new TransformGroup(tr_cyl);
/* 359 */     tg_cyl.addChild(cyl);
/* 360 */     root.addChild(tg_cyl);
/*     */ 
/*     */     
/* 363 */     Appearance ap_cy2 = new Appearance();
/* 364 */     ap_cy2.setMaterial(m_cyl);
/*     */     
/* 366 */     Cylinder cy2 = new Cylinder(0.15F, 0.7F, 1, ap_cy2);
/* 367 */     Transform3D tr_cy2 = new Transform3D();
/* 368 */     tr_cy2.setScale(0.6D);
/* 369 */     tr_cy2.setTranslation(new Vector3f(-0.9F, 0.0F, 0.18F));
/* 370 */     tr_cy2.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(180.0D)));
/* 371 */     TransformGroup tg_cy2 = new TransformGroup(tr_cy2);
/* 372 */     tg_cy2.addChild(cy2);
/* 373 */     root.addChild(tg_cy2);
/*     */ 
/*     */ 
/*     */     
/* 377 */     Material m_cy3 = new Material();
/* 378 */     m_cy3.setAmbientColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 379 */     m_cy3.setDiffuseColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 380 */     m_cy3.setSpecularColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 381 */     m_cy3.setShininess(51.2F);
/*     */     
/* 383 */     Appearance ap_cy3 = new Appearance();
/* 384 */     ap_cy3.setMaterial(m_cy3);
/*     */     
/* 386 */     Cylinder cy3 = new Cylinder(0.28F, 1.0E-5F, 1, ap_cy3);
/* 387 */     Transform3D tr_cy3 = new Transform3D();
/* 388 */     tr_cy3.setScale(0.6D);
/* 389 */     tr_cy3.setTranslation(new Vector3f(0.08F, -0.15F, 1.1F));
/* 390 */     tr_cy3.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(180.0D)));
/* 391 */     TransformGroup tg_cy3 = new TransformGroup(tr_cy3);
/* 392 */     tg_cy3.addChild(cy3);
/* 393 */     root.addChild(tg_cy3);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 398 */     Material m_cy4 = new Material();
/* 399 */     m_cy4.setAmbientColor(new Color3f(0.09F, 0.091F, 0.098F));
/* 400 */     m_cy4.setDiffuseColor(new Color3f(0.9F, 0.91F, 0.98F));
/* 401 */     m_cy4.setSpecularColor(new Color3f(0.9F, 0.91F, 0.98F));
/* 402 */     m_cy4.setShininess(85.6F);
/*     */     
/* 404 */     Appearance ap_cy4 = new Appearance();
/* 405 */     ap_cy4.setMaterial(m_cy4);
/*     */     
/* 407 */     Cylinder cy4 = new Cylinder(0.18F, 1.0E-5F, 1, ap_cy4);
/* 408 */     Transform3D tr_cy4 = new Transform3D();
/* 409 */     tr_cy4.setScale(0.6D);
/* 410 */     tr_cy4.setTranslation(new Vector3f(-0.56F, 0.05F, 0.518F));
/* 411 */     tr_cy4.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(90.0D)));
/* 412 */     TransformGroup tg_cy4 = new TransformGroup(tr_cy4);
/* 413 */     tg_cy4.addChild(cy4);
/* 414 */     root.addChild(tg_cy4);
/*     */     
/* 416 */     Appearance ap_cy5 = new Appearance();
/* 417 */     ap_cy5.setMaterial(m_cy4);
/*     */     
/* 419 */     Cylinder cy5 = new Cylinder(0.18F, 1.0E-5F, 1, ap_cy5);
/* 420 */     Transform3D tr_cy5 = new Transform3D();
/* 421 */     tr_cy5.setScale(0.6D);
/* 422 */     tr_cy5.setTranslation(new Vector3f(0.56F, 0.05F, 0.63F));
/* 423 */     tr_cy5.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(90.0D)));
/* 424 */     TransformGroup tg_cy5 = new TransformGroup(tr_cy5);
/* 425 */     tg_cy5.addChild(cy5);
/* 426 */     root.addChild(tg_cy5);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 431 */     Box parede1 = new Box(1.438F, madStg_height, madStg_width, 1, this.ap);
/* 432 */     tr = new Transform3D();
/* 433 */     tr.set(new Vector3f(0.0F, 0.0F, -1.4F));
/* 434 */     tg = new TransformGroup(tr);
/* 435 */     root.addChild(tg);
/* 436 */     tg.addChild(parede1);
/*     */ 
/*     */     
/* 439 */     Box box = new Box(1.4438F, madStg_height, madStg_width, 2, this.ap);
/*     */     
/* 441 */     tr = new Transform3D();
/* 442 */     tr.set(new Vector3f(0.0F, 0.0F, 1.4F));
/* 443 */     tg = new TransformGroup(tr);
/* 444 */     root.addChild(tg);
/* 445 */     tg.addChild(box);
/*     */ 
/*     */     
/* 448 */     Box cartaz = new Box(0.335F, madSml_height - 0.065F, 1.0E-5F, 2, createTextureAppearance("cartaz"));
/* 449 */     tr = new Transform3D();
/* 450 */     tr.set(new Vector3f(-0.85F, 0.04F, -1.358F));
/* 451 */     tg = new TransformGroup(tr);
/* 452 */     root.addChild(tg);
/* 453 */     tg.addChild(cartaz);
/*     */ 
/*     */     
/* 456 */     Box cartaz2 = new Box(1.0E-5F, madSml_height - 0.065F, 0.3F, 2, createTextureAppearance("cartaz2"));
/* 457 */     tr = new Transform3D();
/* 458 */     tr.set(new Vector3f(1.35F, 0.04F, 1.0F));
/* 459 */     tg = new TransformGroup(tr);
/* 460 */     root.addChild(tg);
/* 461 */     tg.addChild(cartaz2);
/*     */ 
/*     */     
/* 464 */     Box cartaz3 = new Box(1.0E-5F, madSml_height - 0.065F, 0.3F, 2, createTextureAppearance("cartaz3"));
/* 465 */     tr = new Transform3D();
/* 466 */     tr.set(new Vector3f(0.38F, 0.04F, -0.95F));
/* 467 */     tg = new TransformGroup(tr);
/* 468 */     root.addChild(tg);
/* 469 */     tg.addChild(cartaz3);
/*     */ 
/*     */     
/* 472 */     Box parede3 = new Box(madStg_width, madStg_height, 1.4F, 1, this.ap);
/* 473 */     tr = new Transform3D();
/* 474 */     tr.set(new Vector3f(-1.4F, 0.0F, 0.0F));
/* 475 */     tg = new TransformGroup(tr);
/* 476 */     root.addChild(tg);
/* 477 */     tg.addChild(parede3);
/*     */ 
/*     */     
/* 480 */     Box parede4 = new Box(madStg_width, madStg_height, 1.4F, 1, this.ap);
/* 481 */     tr = new Transform3D();
/* 482 */     tr.set(new Vector3f(1.4F, 0.0F, 0.0F));
/* 483 */     tg = new TransformGroup(tr);
/* 484 */     root.addChild(tg);
/* 485 */     tg.addChild(parede4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 491 */     Box P1Quarto1 = new Box(madSml_width, madSml_height, 0.4F, 1, this.ap);
/* 492 */     tr = new Transform3D();
/* 493 */     tr.set(new Vector3f(0.3F, 0.0F, 1.0F));
/* 494 */     tg = new TransformGroup(tr);
/* 495 */     root.addChild(tg);
/* 496 */     tg.addChild(P1Quarto1);
/*     */     
/* 498 */     Box P2Quarto1 = new Box(0.25F, madSml_height, madSml_width, 1, this.ap);
/* 499 */     tr = new Transform3D();
/* 500 */     tr.set(new Vector3f(0.54F, 0.0F, 0.6F));
/* 501 */     tg = new TransformGroup(tr);
/* 502 */     root.addChild(tg);
/* 503 */     tg.addChild(P2Quarto1);
/*     */     
/* 505 */     Box PQuarto1 = new Box(0.3F, 0.0025F, madSml_width, 1, this.ap);
/* 506 */     tr = new Transform3D();
/* 507 */     tr.setTranslation(new Vector3f(1.1F, -0.15F, 0.6F));
/* 508 */     tr.setRotation(new AxisAngle4d(0.0D, 0.0D, 0.0D, (float)Math.toRadians(180.0D)));
/* 509 */     tg = new TransformGroup(tr);
/* 510 */     root.addChild(tg);
/* 511 */     tg.addChild(PQuarto1);
/*     */ 
/*     */     
/* 514 */     Box P1Quarto2 = new Box(madSml_width, madSml_height, 0.45F, 1, this.ap);
/* 515 */     tr = new Transform3D();
/* 516 */     tr.set(new Vector3f(0.4F, 0.0F, -0.95F));
/* 517 */     tg = new TransformGroup(tr);
/* 518 */     root.addChild(tg);
/* 519 */     tg.addChild(P1Quarto2);
/*     */     
/* 521 */     Box P2Quarto2 = new Box(0.2F, madSml_height, madSml_width, 1, this.ap);
/* 522 */     tr = new Transform3D();
/* 523 */     tr.set(new Vector3f(1.2F, 0.0F, -0.5F));
/* 524 */     tg = new TransformGroup(tr);
/* 525 */     root.addChild(tg);
/* 526 */     tg.addChild(P2Quarto2);
/*     */     
/* 528 */     Box PQuarto2 = new Box(0.3F, -0.0025F, madSml_width, 1, this.ap);
/* 529 */     tr = new Transform3D();
/* 530 */     tr.setTranslation(new Vector3f(0.7F, -0.15F, -0.5F));
/* 531 */     tg = new TransformGroup(tr);
/* 532 */     root.addChild(tg);
/* 533 */     tg.addChild(PQuarto2);
/*     */ 
/*     */ 
/*     */     
/* 537 */     Box P1Quarto3 = new Box(madSml_width, madSml_height, 0.2F, 1, this.ap);
/* 538 */     tr = new Transform3D();
/* 539 */     tr.set(new Vector3f(-0.35F, 0.0F, -1.2F));
/* 540 */     tg = new TransformGroup(tr);
/* 541 */     root.addChild(tg);
/* 542 */     tg.addChild(P1Quarto3);
/*     */     
/* 544 */     Box P2Quarto3 = new Box(0.51F, madSml_height, madSml_width, 1, this.ap);
/* 545 */     tr = new Transform3D();
/* 546 */     tr.set(new Vector3f(-0.84F, 0.0F, -0.2F));
/* 547 */     tg = new TransformGroup(tr);
/* 548 */     root.addChild(tg);
/* 549 */     tg.addChild(P2Quarto3);
/*     */     
/* 551 */     Box PQuarto3 = new Box(madSml_width, -0.0025F, 0.45F, 1, this.ap);
/* 552 */     tr = new Transform3D();
/* 553 */     tr.setTranslation(new Vector3f(-0.35F, -0.15F, -0.65F));
/* 554 */     tg = new TransformGroup(tr);
/* 555 */     root.addChild(tg);
/* 556 */     tg.addChild(PQuarto3);
/*     */ 
/*     */ 
/*     */     
/* 560 */     Box P1Quarto4 = new Box(0.4F, madSml_height, madSml_width, 1, this.ap);
/* 561 */     tr = new Transform3D();
/* 562 */     tr.set(new Vector3f(-0.6F, 0.0F, 0.5F));
/* 563 */     tg = new TransformGroup(tr);
/* 564 */     root.addChild(tg);
/* 565 */     tg.addChild(P1Quarto4);
/*     */     
/* 567 */     Box P2Quarto4 = new Box(madSml_width, madSml_height, 0.23F, 1, this.ap);
/* 568 */     tr = new Transform3D();
/* 569 */     tr.set(new Vector3f(-0.2F, 0.0F, 0.73F));
/* 570 */     tg = new TransformGroup(tr);
/* 571 */     root.addChild(tg);
/* 572 */     tg.addChild(P2Quarto4);
/*     */     
/* 574 */     Box P3Quarto4 = new Box(madSml_width, madSml_height, 0.232F, 1, this.ap);
/* 575 */     tr = new Transform3D();
/* 576 */     tr.set(new Vector3f(-1.0F, 0.0F, 0.725F));
/* 577 */     tg = new TransformGroup(tr);
/* 578 */     root.addChild(tg);
/* 579 */     tg.addChild(P3Quarto4);
/*     */     
/* 581 */     Box P4Quarto4 = new Box(0.2F, madSml_height, madSml_width, 1, this.ap);
/* 582 */     tr = new Transform3D();
/* 583 */     tr.set(new Vector3f(-0.8F, 0.0F, 0.95F));
/* 584 */     tg = new TransformGroup(tr);
/* 585 */     root.addChild(tg);
/* 586 */     tg.addChild(P4Quarto4);
/*     */     
/* 588 */     Box PQuarto4 = new Box(0.22F, -0.0025F, madSml_width, 1, this.ap);
/* 589 */     tr = new Transform3D();
/* 590 */     tr.setTranslation(new Vector3f(-0.42F, -0.15F, 0.95F));
/* 591 */     tg = new TransformGroup(tr);
/* 592 */     root.addChild(tg);
/* 593 */     tg.addChild(PQuarto4);
/*     */ 
/*     */ 
/*     */     
/* 597 */     Background bk = new Background(0.5F, madSml_height, 0.5F);
/* 598 */     bk.setApplicationBounds(this.bounds);
/* 599 */     root.addChild(bk);
/*     */ 
/*     */     
/* 602 */     AmbientLight aL = new AmbientLight(true, new Color3f(Color.WHITE));
/* 603 */     aL.setInfluencingBounds(this.bounds);
/* 604 */     root.addChild(aL);
/*     */     
/* 606 */     PointLight pL = new PointLight(new Color3f(new Color3f(0.74902F, 0.847059F, 0.847059F)), new Point3f(0.0F, 3.0F, 3.0F), new Point3f(1.0F, 0.0F, 0.0F));
/* 607 */     pL.setInfluencingBounds(this.bounds);
/* 608 */     root.addChild(pL);
/*     */     
/* 610 */     this.apT = new Appearance();
/* 611 */     this.apT.setMaterial(new Material());
/* 612 */     Material m2 = new Material();
/* 613 */     m2.setAmbientColor(new Color3f(0.4F, 0.0F, 0.0F));
/* 614 */     m2.setDiffuseColor(new Color3f(0.4F, 0.0F, 0.0F));
/* 615 */     m2.setSpecularColor(new Color3f(0.4F, 0.0F, 0.0F));
/* 616 */     m2.setShininess(30.0F);
/* 617 */     this.apT.setMaterial(m2);
/*     */     
/* 619 */     Box Tapete = new Box(0.31F, 0.0025F, 0.31F, 1, this.apT);
/* 620 */     tr = new Transform3D();
/* 621 */     tr.set(new Vector3f(0.15F, -0.15F, 0.15F));
/* 622 */     tg = new TransformGroup(tr);
/* 623 */     root.addChild(tg);
/* 624 */     tg.addChild(Tapete);
/*     */     
/* 626 */     return root;
/*     */   }
/*     */ 
/*     */   
/* 630 */   public void refreshMove() { this.data.add(new Point3f(this.RBT_coordX, this.RBT_coordY, this.RBT_coordZ), this.RBT_angulo); }
/*     */ 
/*     */   
/*     */   Appearance createTextureAppearance(String name) {
/* 634 */     Appearance ap = new Appearance();
/* 635 */     URL filename = getClass().getClassLoader().getResource("images/" + name + ".png");
/* 636 */     TextureLoader loader = new TextureLoader(filename, this);
/* 637 */     ImageComponent2D image = loader.getImage();
/* 638 */     if (image == null) {
/* 639 */       System.out.println("can't find texture file.");
/*     */     }
/* 641 */     Texture2D texture = new Texture2D(1, 6, image.getWidth(), image.getHeight());
/* 642 */     texture.setImage(0, image);
/* 643 */     texture.setEnable(true);
/* 644 */     texture.setMagFilter(3);
/* 645 */     texture.setMinFilter(3);
/* 646 */     ap.setTexture(texture);
/*     */     
/* 648 */     TextureAttributes texatt = new TextureAttributes();
/* 649 */     texatt.setTextureMode(6);
/* 650 */     ap.setTextureAttributes(texatt);
/* 651 */     ap.setMaterial(new Material());
/* 652 */     return ap;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\robotMoveExtra\main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */