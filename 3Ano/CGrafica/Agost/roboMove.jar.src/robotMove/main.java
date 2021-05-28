/*     */ package robotMove;
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
/*     */ import javax.media.j3d.BoundingSphere;
/*     */ import javax.media.j3d.BranchGroup;
/*     */ import javax.media.j3d.Canvas3D;
/*     */ import javax.media.j3d.ImageComponent2D;
/*     */ import javax.media.j3d.Material;
/*     */ import javax.media.j3d.PointLight;
/*     */ import javax.media.j3d.RotPosPathInterpolator;
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
/*     */ 
/*     */ public class main
/*     */   extends Applet
/*     */ {
/*     */   BoundingSphere bounds;
/*     */   Appearance ap;
/*     */   Appearance apRobo;
/*     */   Appearance apPreto;
/*     */   
/*     */   public static void main(String[] args) {}
/*     */   
/*     */   public void init() {
/*  51 */     GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
/*  52 */     Canvas3D cv = new Canvas3D(gc);
/*  53 */     setLayout(new BorderLayout());
/*  54 */     add(cv, "Center");
/*     */ 
/*     */     
/*  57 */     BranchGroup bg = createSceneGraph();
/*  58 */     bg.compile();
/*     */ 
/*     */     
/*  61 */     SimpleUniverse su = new SimpleUniverse(cv);
/*     */ 
/*     */     
/*  64 */     Transform3D locator = new Transform3D();
/*  65 */     locator.lookAt(new Point3d(0.0D, 4.1D, 4.1D), new Point3d(0.0D, 0.0D, 0.0D), new Vector3d(0.0D, 1.0D, 0.0D));
/*  66 */     locator.invert();
/*  67 */     su.getViewingPlatform().getViewPlatformTransform().setTransform(locator);
/*     */ 
/*     */     
/*  70 */     this.bounds = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 4.0D);
/*     */ 
/*     */     
/*  73 */     OrbitBehavior orbit = new OrbitBehavior(cv);
/*  74 */     orbit.setSchedulingBounds(new BoundingSphere());
/*  75 */     su.getViewingPlatform().setViewPlatformBehavior(orbit);
/*     */     
/*  77 */     su.addBranchGraph(bg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BranchGroup createSceneGraph() {
/*  86 */     float madSml_height = 0.23F;
/*  87 */     float madSml_width = 0.01F;
/*     */     
/*  89 */     float madStg_height = 0.23F;
/*  90 */     float madStg_width = 0.04F;
/*     */     
/*  92 */     BranchGroup root = new BranchGroup();
/*     */ 
/*     */     
/*  95 */     this.bounds = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 4.0D);
/*     */ 
/*     */     
/*  98 */     this.ap = new Appearance();
/*  99 */     this.ap.setMaterial(new Material());
/*     */     
/* 101 */     Material m = new Material();
/* 102 */     m.setAmbientColor(new Color3f(0.4F, 0.4F, 0.4F));
/* 103 */     m.setDiffuseColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 104 */     m.setSpecularColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 105 */     m.setShininess(25.0F);
/*     */     
/* 107 */     this.ap.setMaterial(m);
/*     */ 
/*     */     
/* 110 */     this.apPreto = new Appearance();
/* 111 */     this.apPreto.setMaterial(new Material());
/*     */     
/* 113 */     Material mPreto = new Material();
/*     */     
/* 115 */     mPreto.setAmbientColor(new Color3f(0.25F, 0.25F, 0.25F));
/* 116 */     mPreto.setDiffuseColor(new Color3f(0.34F, 0.34F, 0.34F));
/* 117 */     mPreto.setSpecularColor(new Color3f(0.2F, 0.2F, 0.2F));
/* 118 */     mPreto.setShininess(25.0F);
/*     */     
/* 120 */     this.apPreto.setMaterial(mPreto);
/*     */ 
/*     */     
/* 123 */     Appearance apCartaz = createTextureAppearance();
/*     */     
/* 125 */     this.apRobo = new Appearance();
/* 126 */     this.apRobo.setMaterial(new Material());
/*     */ 
/*     */     
/* 129 */     TransformGroup movetg = new TransformGroup();
/* 130 */     movetg.setCapability(17);
/* 131 */     movetg.setCapability(18);
/* 132 */     root.addChild(movetg);
/*     */ 
/*     */     
/* 135 */     Material robotColor = new Material();
/* 136 */     robotColor.setAmbientColor(new Color3f(0.8F, 0.498039F, 0.196078F));
/* 137 */     robotColor.setDiffuseColor(new Color3f(0.8F, 0.498039F, 0.196078F));
/* 138 */     robotColor.setSpecularColor(new Color3f(0.8F, 0.498039F, 0.196078F));
/* 139 */     robotColor.setShininess(100.0F);
/*     */     
/* 141 */     this.apRobo.setMaterial(robotColor);
/*     */     
/* 143 */     Transform3D tr = new Transform3D();
/* 144 */     TransformGroup tg = new TransformGroup(tr);
/*     */     
/* 146 */     Box obj1 = new Box(0.1F, 0.1F, 0.1F, 1, this.apRobo);
/* 147 */     tr.setTranslation(new Vector3f(0.0F, -0.01F, 0.0F));
/*     */ 
/*     */ 
/*     */     
/* 151 */     DesignRobot RobotAB = new DesignRobot();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     Alpha alpha = new Alpha(-1, 20000L);
/*     */ 
/*     */     
/* 159 */     InterpolatorData data = new InterpolatorData();
/*     */     
/* 161 */     float RBT_coordX = 0.08F;
/* 162 */     float RBT_coordY = 0.0F;
/* 163 */     float RBT_coordZ = 1.1F;
/* 164 */     float RBT_angulo = 0.0F;
/*     */     
/* 166 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 168 */     RBT_coordZ -= 0.75F;
/* 169 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 171 */     RBT_angulo -= 90.0F;
/* 172 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 174 */     RBT_coordX += 0.92F;
/* 175 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 177 */     RBT_angulo -= 90.0F;
/* 178 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 180 */     RBT_coordZ += 0.1F;
/* 181 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 183 */     RBT_coordZ -= 0.1F;
/* 184 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 186 */     RBT_angulo += 90.0F;
/* 187 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 189 */     RBT_coordX += 0.05F;
/* 190 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 192 */     RBT_angulo += 90.0F;
/* 193 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 195 */     RBT_coordZ -= 0.5F;
/* 196 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 198 */     RBT_angulo += 90.0F;
/* 199 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 201 */     RBT_coordX -= 0.28F;
/* 202 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 204 */     RBT_angulo -= 90.0F;
/* 205 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 207 */     RBT_coordZ -= 0.3F;
/* 208 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 210 */     RBT_coordZ += 0.3F;
/* 211 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 213 */     RBT_angulo += 90.0F;
/* 214 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 216 */     RBT_coordX -= 0.58F;
/* 217 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 219 */     RBT_angulo -= 90.0F;
/* 220 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 222 */     RBT_coordZ -= 0.95F;
/* 223 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 225 */     RBT_angulo += 90.0F;
/* 226 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 228 */     RBT_coordX -= 0.28F;
/* 229 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 231 */     RBT_angulo += 90.0F;
/* 232 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 234 */     RBT_coordZ += 0.38F;
/* 235 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 237 */     RBT_angulo -= 90.0F;
/* 238 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 240 */     RBT_coordX -= 0.5F;
/* 241 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 243 */     RBT_coordX += 0.5F;
/* 244 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 246 */     RBT_angulo += 90.0F;
/* 247 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 249 */     RBT_coordZ += 0.8F;
/* 250 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 252 */     RBT_angulo -= 90.0F;
/* 253 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 255 */     RBT_coordX -= 0.3F;
/* 256 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 258 */     RBT_angulo += 90.0F;
/* 259 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 261 */     RBT_coordZ += 0.13F;
/* 262 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 264 */     RBT_angulo += 90.0F;
/* 265 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 267 */     RBT_coordX += 0.42F;
/* 268 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 270 */     RBT_angulo -= 90.0F;
/* 271 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 273 */     RBT_coordZ += 0.88F;
/* 274 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 276 */     RBT_angulo -= 90.0F;
/* 277 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 279 */     RBT_coordX -= 0.4F;
/* 280 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 282 */     RBT_angulo -= 90.0F;
/* 283 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 285 */     RBT_coordZ -= 0.32F;
/* 286 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 288 */     RBT_angulo += 90.0F;
/* 289 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/* 290 */     RBT_angulo -= 10.0F;
/* 291 */     RBT_angulo += 30.0F;
/* 292 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 294 */     RBT_angulo -= 30.0F;
/* 295 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 297 */     RBT_angulo += 30.0F;
/* 298 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 300 */     RBT_angulo -= 30.0F;
/* 301 */     RBT_angulo += 10.0F;
/* 302 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 304 */     RBT_angulo += 90.0F;
/* 305 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 307 */     RBT_coordZ += 0.35F;
/* 308 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 310 */     RBT_angulo += 90.0F;
/* 311 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 313 */     RBT_coordX += 0.45F;
/* 314 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */     
/* 316 */     RBT_angulo += 90.0F;
/* 317 */     data.add(new Point3f(RBT_coordX, RBT_coordY, RBT_coordZ), RBT_angulo);
/*     */ 
/*     */ 
/*     */     
/* 321 */     TransformGroup moveTg = new TransformGroup();
/* 322 */     moveTg.setCapability(17);
/* 323 */     moveTg.setCapability(18);
/*     */     
/* 325 */     root.addChild(moveTg);
/* 326 */     moveTg.addChild(RobotAB);
/* 327 */     RotPosPathInterpolator interpolator = new RotPosPathInterpolator(alpha, moveTg, new Transform3D(), data.getKnots(), data.getOrientations(), data.getPositions());
/* 328 */     interpolator.setSchedulingBounds(this.bounds);
/* 329 */     moveTg.addChild(interpolator);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 339 */     Box chaoArena = new Box(1.4F, 0.04F, 1.4F, 1, this.apPreto);
/* 340 */     tr = new Transform3D();
/* 341 */     tr.set(new Vector3f(0.0F, -0.2F, 0.0F));
/* 342 */     tg = new TransformGroup(tr);
/* 343 */     root.addChild(tg);
/* 344 */     tg.addChild(chaoArena);
/*     */ 
/*     */     
/* 347 */     Material m_cyl = new Material();
/* 348 */     m_cyl.setAmbientColor(new Color3f(0.24725F, 0.1995F, 0.027451F));
/* 349 */     m_cyl.setDiffuseColor(new Color3f(0.780392F, 0.568627F, 0.113725F));
/* 350 */     m_cyl.setSpecularColor(new Color3f(0.75164F, 0.941176F, 0.807843F));
/* 351 */     m_cyl.setShininess(51.2F);
/* 352 */     Appearance ap_cyl = new Appearance();
/* 353 */     ap_cyl.setMaterial(m_cyl);
/*     */     
/* 355 */     Cylinder cyl = new Cylinder(0.15F, 0.7F, 1, ap_cyl);
/* 356 */     Transform3D tr_cyl = new Transform3D();
/* 357 */     tr_cyl.setScale(0.6D);
/* 358 */     tr_cyl.setTranslation(new Vector3f(0.9F, 0.0F, -0.95F));
/* 359 */     tr_cyl.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(180.0D)));
/* 360 */     TransformGroup tg_cyl = new TransformGroup(tr_cyl);
/* 361 */     tg_cyl.addChild(cyl);
/* 362 */     root.addChild(tg_cyl);
/*     */ 
/*     */     
/* 365 */     Appearance ap_cy2 = new Appearance();
/* 366 */     ap_cy2.setMaterial(m_cyl);
/*     */     
/* 368 */     Cylinder cy2 = new Cylinder(0.15F, 0.7F, 1, ap_cy2);
/* 369 */     Transform3D tr_cy2 = new Transform3D();
/* 370 */     tr_cy2.setScale(0.6D);
/* 371 */     tr_cy2.setTranslation(new Vector3f(-0.9F, 0.0F, 0.18F));
/* 372 */     tr_cy2.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(180.0D)));
/* 373 */     TransformGroup tg_cy2 = new TransformGroup(tr_cy2);
/* 374 */     tg_cy2.addChild(cy2);
/* 375 */     root.addChild(tg_cy2);
/*     */ 
/*     */ 
/*     */     
/* 379 */     Material m_cy3 = new Material();
/* 380 */     m_cy3.setAmbientColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 381 */     m_cy3.setDiffuseColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 382 */     m_cy3.setSpecularColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 383 */     m_cy3.setShininess(51.2F);
/*     */     
/* 385 */     Appearance ap_cy3 = new Appearance();
/* 386 */     ap_cy3.setMaterial(m_cy3);
/*     */     
/* 388 */     Cylinder cy3 = new Cylinder(0.28F, 1.0E-5F, 1, ap_cy3);
/* 389 */     Transform3D tr_cy3 = new Transform3D();
/* 390 */     tr_cy3.setScale(0.6D);
/* 391 */     tr_cy3.setTranslation(new Vector3f(0.08F, -0.15F, 1.1F));
/* 392 */     tr_cy3.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(180.0D)));
/* 393 */     TransformGroup tg_cy3 = new TransformGroup(tr_cy3);
/* 394 */     tg_cy3.addChild(cy3);
/* 395 */     root.addChild(tg_cy3);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 400 */     Material m_cy4 = new Material();
/* 401 */     m_cy4.setAmbientColor(new Color3f(0.09F, 0.091F, 0.098F));
/* 402 */     m_cy4.setDiffuseColor(new Color3f(0.9F, 0.91F, 0.98F));
/* 403 */     m_cy4.setSpecularColor(new Color3f(0.9F, 0.91F, 0.98F));
/* 404 */     m_cy4.setShininess(85.6F);
/*     */     
/* 406 */     Appearance ap_cy4 = new Appearance();
/* 407 */     ap_cy4.setMaterial(m_cy4);
/*     */     
/* 409 */     Cylinder cy4 = new Cylinder(0.18F, 1.0E-5F, 1, ap_cy4);
/* 410 */     Transform3D tr_cy4 = new Transform3D();
/* 411 */     tr_cy4.setScale(0.6D);
/* 412 */     tr_cy4.setTranslation(new Vector3f(-0.56F, 0.05F, 0.518F));
/* 413 */     tr_cy4.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(90.0D)));
/* 414 */     TransformGroup tg_cy4 = new TransformGroup(tr_cy4);
/* 415 */     tg_cy4.addChild(cy4);
/* 416 */     root.addChild(tg_cy4);
/*     */     
/* 418 */     Appearance ap_cy5 = new Appearance();
/* 419 */     ap_cy5.setMaterial(m_cy4);
/*     */     
/* 421 */     Cylinder cy5 = new Cylinder(0.18F, 1.0E-5F, 1, ap_cy5);
/* 422 */     Transform3D tr_cy5 = new Transform3D();
/* 423 */     tr_cy5.setScale(0.6D);
/* 424 */     tr_cy5.setTranslation(new Vector3f(0.56F, 0.05F, 0.63F));
/* 425 */     tr_cy5.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(90.0D)));
/* 426 */     TransformGroup tg_cy5 = new TransformGroup(tr_cy5);
/* 427 */     tg_cy5.addChild(cy5);
/* 428 */     root.addChild(tg_cy5);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 433 */     Box parede1 = new Box(1.438F, madStg_height, madStg_width, 1, this.ap);
/* 434 */     tr = new Transform3D();
/* 435 */     tr.set(new Vector3f(0.0F, 0.0F, -1.4F));
/* 436 */     tg = new TransformGroup(tr);
/* 437 */     root.addChild(tg);
/* 438 */     tg.addChild(parede1);
/*     */ 
/*     */     
/* 441 */     Box box = new Box(1.4438F, madStg_height, madStg_width, 2, this.ap);
/*     */     
/* 443 */     tr = new Transform3D();
/* 444 */     tr.set(new Vector3f(0.0F, 0.0F, 1.4F));
/* 445 */     tg = new TransformGroup(tr);
/* 446 */     root.addChild(tg);
/* 447 */     tg.addChild(box);
/*     */ 
/*     */     
/* 450 */     Box cartaz = new Box(0.335F, madSml_height - 0.065F, 1.0E-5F, 2, apCartaz);
/* 451 */     tr = new Transform3D();
/* 452 */     tr.set(new Vector3f(-0.85F, 0.04F, -1.358F));
/* 453 */     tg = new TransformGroup(tr);
/* 454 */     root.addChild(tg);
/* 455 */     tg.addChild(cartaz);
/*     */ 
/*     */     
/* 458 */     Box cartaz2 = new Box(1.0E-5F, madSml_height - 0.065F, 0.3F, 2, apCartaz);
/* 459 */     tr = new Transform3D();
/* 460 */     tr.set(new Vector3f(1.35F, 0.04F, 1.0F));
/* 461 */     tg = new TransformGroup(tr);
/* 462 */     root.addChild(tg);
/* 463 */     tg.addChild(cartaz2);
/*     */ 
/*     */     
/* 466 */     Box cartaz3 = new Box(1.0E-5F, madSml_height - 0.065F, 0.3F, 2, apCartaz);
/* 467 */     tr = new Transform3D();
/* 468 */     tr.set(new Vector3f(0.38F, 0.04F, -0.95F));
/* 469 */     tg = new TransformGroup(tr);
/* 470 */     root.addChild(tg);
/* 471 */     tg.addChild(cartaz3);
/*     */ 
/*     */     
/* 474 */     Box parede3 = new Box(madStg_width, madStg_height, 1.4F, 1, this.ap);
/* 475 */     tr = new Transform3D();
/* 476 */     tr.set(new Vector3f(-1.4F, 0.0F, 0.0F));
/* 477 */     tg = new TransformGroup(tr);
/* 478 */     root.addChild(tg);
/* 479 */     tg.addChild(parede3);
/*     */ 
/*     */     
/* 482 */     Box parede4 = new Box(madStg_width, madStg_height, 1.4F, 1, this.ap);
/* 483 */     tr = new Transform3D();
/* 484 */     tr.set(new Vector3f(1.4F, 0.0F, 0.0F));
/* 485 */     tg = new TransformGroup(tr);
/* 486 */     root.addChild(tg);
/* 487 */     tg.addChild(parede4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 493 */     Box P1Quarto1 = new Box(madSml_width, madSml_height, 0.4F, 1, this.ap);
/* 494 */     tr = new Transform3D();
/* 495 */     tr.set(new Vector3f(0.3F, 0.0F, 1.0F));
/* 496 */     tg = new TransformGroup(tr);
/* 497 */     root.addChild(tg);
/* 498 */     tg.addChild(P1Quarto1);
/*     */     
/* 500 */     Box P2Quarto1 = new Box(0.25F, madSml_height, madSml_width, 1, this.ap);
/* 501 */     tr = new Transform3D();
/* 502 */     tr.set(new Vector3f(0.54F, 0.0F, 0.6F));
/* 503 */     tg = new TransformGroup(tr);
/* 504 */     root.addChild(tg);
/* 505 */     tg.addChild(P2Quarto1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 510 */     Box P1Quarto2 = new Box(madSml_width, madSml_height, 0.4F, 1, this.ap);
/* 511 */     tr = new Transform3D();
/* 512 */     tr.set(new Vector3f(0.4F, 0.0F, -0.95F));
/* 513 */     tg = new TransformGroup(tr);
/* 514 */     root.addChild(tg);
/* 515 */     tg.addChild(P1Quarto2);
/*     */     
/* 517 */     Box P2Quarto2 = new Box(0.2F, madSml_height, madSml_width, 1, this.ap);
/* 518 */     tr = new Transform3D();
/* 519 */     tr.set(new Vector3f(1.2F, 0.0F, -0.5F));
/* 520 */     tg = new TransformGroup(tr);
/* 521 */     root.addChild(tg);
/* 522 */     tg.addChild(P2Quarto2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 527 */     Box P1Quarto3 = new Box(madSml_width, madSml_height, 0.2F, 1, this.ap);
/* 528 */     tr = new Transform3D();
/* 529 */     tr.set(new Vector3f(-0.35F, 0.0F, -1.2F));
/* 530 */     tg = new TransformGroup(tr);
/* 531 */     root.addChild(tg);
/* 532 */     tg.addChild(P1Quarto3);
/*     */     
/* 534 */     Box P2Quarto3 = new Box(0.45F, madSml_height, madSml_width, 1, this.ap);
/* 535 */     tr = new Transform3D();
/* 536 */     tr.set(new Vector3f(-0.9F, 0.0F, -0.2F));
/* 537 */     tg = new TransformGroup(tr);
/* 538 */     root.addChild(tg);
/* 539 */     tg.addChild(P2Quarto3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 545 */     Box P1Quarto4 = new Box(0.4F, madSml_height, madSml_width, 1, this.ap);
/* 546 */     tr = new Transform3D();
/* 547 */     tr.set(new Vector3f(-0.6F, 0.0F, 0.5F));
/* 548 */     tg = new TransformGroup(tr);
/* 549 */     root.addChild(tg);
/* 550 */     tg.addChild(P1Quarto4);
/*     */     
/* 552 */     Box P2Quarto4 = new Box(madSml_width, madSml_height, 0.2F, 1, this.ap);
/* 553 */     tr = new Transform3D();
/* 554 */     tr.set(new Vector3f(-0.2F, 0.0F, 0.7F));
/* 555 */     tg = new TransformGroup(tr);
/* 556 */     root.addChild(tg);
/* 557 */     tg.addChild(P2Quarto4);
/*     */     
/* 559 */     Box P3Quarto4 = new Box(madSml_width, madSml_height, 0.232F, 1, this.ap);
/* 560 */     tr = new Transform3D();
/* 561 */     tr.set(new Vector3f(-1.0F, 0.0F, 0.725F));
/* 562 */     tg = new TransformGroup(tr);
/* 563 */     root.addChild(tg);
/* 564 */     tg.addChild(P3Quarto4);
/*     */     
/* 566 */     Box P4Quarto4 = new Box(0.2F, madSml_height, madSml_width, 1, this.ap);
/* 567 */     tr = new Transform3D();
/* 568 */     tr.set(new Vector3f(-0.8F, 0.0F, 0.95F));
/* 569 */     tg = new TransformGroup(tr);
/* 570 */     root.addChild(tg);
/* 571 */     tg.addChild(P4Quarto4);
/*     */ 
/*     */ 
/*     */     
/* 575 */     Background bk = new Background(0.5F, madSml_height, 0.5F);
/* 576 */     bk.setApplicationBounds(this.bounds);
/* 577 */     root.addChild(bk);
/*     */ 
/*     */     
/* 580 */     AmbientLight aL = new AmbientLight(true, new Color3f(Color.WHITE));
/* 581 */     aL.setInfluencingBounds(this.bounds);
/* 582 */     root.addChild(aL);
/*     */     
/* 584 */     PointLight pL = new PointLight(new Color3f(new Color3f(0.74902F, 0.847059F, 0.847059F)), new Point3f(0.0F, 3.0F, 3.0F), new Point3f(1.0F, 0.0F, 0.0F));
/* 585 */     pL.setInfluencingBounds(this.bounds);
/* 586 */     root.addChild(pL);
/*     */     
/* 588 */     return root;
/*     */   }
/*     */   
/*     */   Appearance createTextureAppearance() {
/* 592 */     Appearance ap = new Appearance();
/* 593 */     URL filename = getClass().getClassLoader().getResource("images/cartaz.png");
/* 594 */     TextureLoader loader = new TextureLoader(filename, this);
/* 595 */     ImageComponent2D image = loader.getImage();
/* 596 */     if (image == null) {
/* 597 */       System.out.println("can't find texture file.");
/*     */     }
/* 599 */     Texture2D texture = new Texture2D(1, 6, image.getWidth(), image.getHeight());
/* 600 */     texture.setImage(0, image);
/* 601 */     texture.setEnable(true);
/* 602 */     texture.setMagFilter(3);
/* 603 */     texture.setMinFilter(3);
/* 604 */     ap.setTexture(texture);
/*     */ 
/*     */     
/* 607 */     TextureAttributes texatt = new TextureAttributes();
/* 608 */     texatt.setTextureMode(6);
/* 609 */     ap.setTextureAttributes(texatt);
/* 610 */     ap.setMaterial(new Material());
/* 611 */     return ap;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\robotMove\main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */