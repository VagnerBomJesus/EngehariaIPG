/*     */ package robot;
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
/*  50 */     GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
/*  51 */     Canvas3D cv = new Canvas3D(gc);
/*  52 */     setLayout(new BorderLayout());
/*  53 */     add(cv, "Center");
/*     */ 
/*     */     
/*  56 */     BranchGroup bg = createSceneGraph();
/*  57 */     bg.compile();
/*     */ 
/*     */     
/*  60 */     SimpleUniverse su = new SimpleUniverse(cv);
/*     */ 
/*     */     
/*  63 */     Transform3D locator = new Transform3D();
/*  64 */     locator.lookAt(new Point3d(0.0D, 4.1D, 4.1D), new Point3d(0.0D, 0.0D, 0.0D), new Vector3d(0.0D, 1.0D, 0.0D));
/*  65 */     locator.invert();
/*  66 */     su.getViewingPlatform().getViewPlatformTransform().setTransform(locator);
/*     */ 
/*     */     
/*  69 */     this.bounds = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 4.0D);
/*     */ 
/*     */     
/*  72 */     OrbitBehavior orbit = new OrbitBehavior(cv);
/*  73 */     orbit.setSchedulingBounds(new BoundingSphere());
/*  74 */     su.getViewingPlatform().setViewPlatformBehavior(orbit);
/*     */     
/*  76 */     su.addBranchGraph(bg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BranchGroup createSceneGraph() {
/*  85 */     float madSml_height = 0.23F;
/*  86 */     float madSml_width = 0.01F;
/*     */     
/*  88 */     float madStg_height = 0.23F;
/*  89 */     float madStg_width = 0.04F;
/*     */     
/*  91 */     BranchGroup root = new BranchGroup();
/*     */ 
/*     */     
/*  94 */     this.bounds = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 4.0D);
/*     */ 
/*     */     
/*  97 */     this.ap = new Appearance();
/*  98 */     this.ap.setMaterial(new Material());
/*     */     
/* 100 */     Material m = new Material();
/* 101 */     m.setAmbientColor(new Color3f(0.4F, 0.4F, 0.4F));
/* 102 */     m.setDiffuseColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 103 */     m.setSpecularColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 104 */     m.setShininess(25.0F);
/*     */     
/* 106 */     this.ap.setMaterial(m);
/*     */ 
/*     */     
/* 109 */     this.apPreto = new Appearance();
/* 110 */     this.apPreto.setMaterial(new Material());
/*     */     
/* 112 */     Material mPreto = new Material();
/*     */     
/* 114 */     mPreto.setAmbientColor(new Color3f(0.25F, 0.25F, 0.25F));
/* 115 */     mPreto.setDiffuseColor(new Color3f(0.34F, 0.34F, 0.34F));
/* 116 */     mPreto.setSpecularColor(new Color3f(0.2F, 0.2F, 0.2F));
/* 117 */     mPreto.setShininess(25.0F);
/*     */     
/* 119 */     this.apPreto.setMaterial(mPreto);
/*     */ 
/*     */     
/* 122 */     Appearance apCartaz = createTextureAppearance();
/*     */     
/* 124 */     this.apRobo = new Appearance();
/* 125 */     this.apRobo.setMaterial(new Material());
/*     */ 
/*     */     
/* 128 */     TransformGroup movetg = new TransformGroup();
/* 129 */     movetg.setCapability(17);
/* 130 */     movetg.setCapability(18);
/* 131 */     root.addChild(movetg);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     Material robotColor = new Material();
/* 138 */     robotColor.setAmbientColor(new Color3f(0.8F, 0.498039F, 0.196078F));
/* 139 */     robotColor.setDiffuseColor(new Color3f(0.8F, 0.498039F, 0.196078F));
/* 140 */     robotColor.setSpecularColor(new Color3f(0.8F, 0.498039F, 0.196078F));
/* 141 */     robotColor.setShininess(100.0F);
/*     */     
/* 143 */     this.apRobo.setMaterial(robotColor);
/*     */     
/* 145 */     Box obj1 = new Box(0.1F, 0.1F, 0.1F, 1, this.apRobo);
/* 146 */     Transform3D tr = new Transform3D();
/* 147 */     tr.setTranslation(new Vector3f(0.0F, -0.01F, 0.0F));
/* 148 */     TransformGroup tg = new TransformGroup(tr);
/* 149 */     movetg.addChild(tg);
/* 150 */     tg.addChild(obj1);
/*     */     
/* 152 */     KeyControl kc = new KeyControl(movetg, obj1);
/* 153 */     kc.setSchedulingBounds(this.bounds);
/* 154 */     root.addChild(kc);
/*     */ 
/*     */     
/* 157 */     Box chaoArena = new Box(1.4F, 0.04F, 1.4F, 1, this.apPreto);
/* 158 */     tr = new Transform3D();
/* 159 */     tr.set(new Vector3f(0.0F, -0.2F, 0.0F));
/* 160 */     tg = new TransformGroup(tr);
/* 161 */     root.addChild(tg);
/* 162 */     tg.addChild(chaoArena);
/*     */ 
/*     */     
/* 165 */     Material m_cyl = new Material();
/* 166 */     m_cyl.setAmbientColor(new Color3f(0.24725F, 0.1995F, 0.027451F));
/* 167 */     m_cyl.setDiffuseColor(new Color3f(0.780392F, 0.568627F, 0.113725F));
/* 168 */     m_cyl.setSpecularColor(new Color3f(0.75164F, 0.941176F, 0.807843F));
/* 169 */     m_cyl.setShininess(51.2F);
/* 170 */     Appearance ap_cyl = new Appearance();
/* 171 */     ap_cyl.setMaterial(m_cyl);
/*     */     
/* 173 */     Cylinder cyl = new Cylinder(0.15F, 0.7F, 1, ap_cyl);
/* 174 */     Transform3D tr_cyl = new Transform3D();
/* 175 */     tr_cyl.setScale(0.6D);
/* 176 */     tr_cyl.setTranslation(new Vector3f(0.9F, 0.0F, -0.95F));
/* 177 */     tr_cyl.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(180.0D)));
/* 178 */     TransformGroup tg_cyl = new TransformGroup(tr_cyl);
/* 179 */     tg_cyl.addChild(cyl);
/* 180 */     root.addChild(tg_cyl);
/*     */ 
/*     */     
/* 183 */     Appearance ap_cy2 = new Appearance();
/* 184 */     ap_cy2.setMaterial(m_cyl);
/*     */     
/* 186 */     Cylinder cy2 = new Cylinder(0.15F, 0.7F, 1, ap_cy2);
/* 187 */     Transform3D tr_cy2 = new Transform3D();
/* 188 */     tr_cy2.setScale(0.6D);
/* 189 */     tr_cy2.setTranslation(new Vector3f(-0.9F, 0.0F, 0.18F));
/* 190 */     tr_cy2.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(180.0D)));
/* 191 */     TransformGroup tg_cy2 = new TransformGroup(tr_cy2);
/* 192 */     tg_cy2.addChild(cy2);
/* 193 */     root.addChild(tg_cy2);
/*     */ 
/*     */ 
/*     */     
/* 197 */     Material m_cy3 = new Material();
/* 198 */     m_cy3.setAmbientColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 199 */     m_cy3.setDiffuseColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 200 */     m_cy3.setSpecularColor(new Color3f(1.0F, 1.0F, 1.0F));
/* 201 */     m_cy3.setShininess(51.2F);
/*     */     
/* 203 */     Appearance ap_cy3 = new Appearance();
/* 204 */     ap_cy3.setMaterial(m_cy3);
/*     */     
/* 206 */     Cylinder cy3 = new Cylinder(0.28F, 1.0E-5F, 1, ap_cy3);
/* 207 */     Transform3D tr_cy3 = new Transform3D();
/* 208 */     tr_cy3.setScale(0.6D);
/* 209 */     tr_cy3.setTranslation(new Vector3f(0.08F, -0.15F, 1.1F));
/* 210 */     tr_cy3.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(180.0D)));
/* 211 */     TransformGroup tg_cy3 = new TransformGroup(tr_cy3);
/* 212 */     tg_cy3.addChild(cy3);
/* 213 */     root.addChild(tg_cy3);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 218 */     Material m_cy4 = new Material();
/* 219 */     m_cy4.setAmbientColor(new Color3f(0.09F, 0.091F, 0.098F));
/* 220 */     m_cy4.setDiffuseColor(new Color3f(0.9F, 0.91F, 0.98F));
/* 221 */     m_cy4.setSpecularColor(new Color3f(0.9F, 0.91F, 0.98F));
/* 222 */     m_cy4.setShininess(85.6F);
/*     */     
/* 224 */     Appearance ap_cy4 = new Appearance();
/* 225 */     ap_cy4.setMaterial(m_cy4);
/*     */     
/* 227 */     Cylinder cy4 = new Cylinder(0.18F, 1.0E-5F, 1, ap_cy4);
/* 228 */     Transform3D tr_cy4 = new Transform3D();
/* 229 */     tr_cy4.setScale(0.6D);
/* 230 */     tr_cy4.setTranslation(new Vector3f(-0.56F, 0.05F, 0.518F));
/* 231 */     tr_cy4.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(90.0D)));
/* 232 */     TransformGroup tg_cy4 = new TransformGroup(tr_cy4);
/* 233 */     tg_cy4.addChild(cy4);
/* 234 */     root.addChild(tg_cy4);
/*     */     
/* 236 */     Appearance ap_cy5 = new Appearance();
/* 237 */     ap_cy5.setMaterial(m_cy4);
/*     */     
/* 239 */     Cylinder cy5 = new Cylinder(0.18F, 1.0E-5F, 1, ap_cy5);
/* 240 */     Transform3D tr_cy5 = new Transform3D();
/* 241 */     tr_cy5.setScale(0.6D);
/* 242 */     tr_cy5.setTranslation(new Vector3f(0.56F, 0.05F, 0.63F));
/* 243 */     tr_cy5.setRotation(new AxisAngle4d(-0.5D, 0.0D, 0.0D, (float)Math.toRadians(90.0D)));
/* 244 */     TransformGroup tg_cy5 = new TransformGroup(tr_cy5);
/* 245 */     tg_cy5.addChild(cy5);
/* 246 */     root.addChild(tg_cy5);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 251 */     Box parede1 = new Box(1.438F, madStg_height, madStg_width, 1, this.ap);
/* 252 */     tr = new Transform3D();
/* 253 */     tr.set(new Vector3f(0.0F, 0.0F, -1.4F));
/* 254 */     tg = new TransformGroup(tr);
/* 255 */     root.addChild(tg);
/* 256 */     tg.addChild(parede1);
/*     */ 
/*     */     
/* 259 */     Box box = new Box(1.4438F, madStg_height, madStg_width, 2, this.ap);
/*     */     
/* 261 */     tr = new Transform3D();
/* 262 */     tr.set(new Vector3f(0.0F, 0.0F, 1.4F));
/* 263 */     tg = new TransformGroup(tr);
/* 264 */     root.addChild(tg);
/* 265 */     tg.addChild(box);
/*     */ 
/*     */     
/* 268 */     Box cartaz = new Box(0.335F, madSml_height - 0.065F, 1.0E-5F, 2, apCartaz);
/* 269 */     tr = new Transform3D();
/* 270 */     tr.set(new Vector3f(-0.85F, 0.04F, -1.358F));
/* 271 */     tg = new TransformGroup(tr);
/* 272 */     root.addChild(tg);
/* 273 */     tg.addChild(cartaz);
/*     */ 
/*     */     
/* 276 */     Box cartaz2 = new Box(1.0E-5F, madSml_height - 0.065F, 0.3F, 2, apCartaz);
/* 277 */     tr = new Transform3D();
/* 278 */     tr.set(new Vector3f(1.35F, 0.04F, 1.0F));
/* 279 */     tg = new TransformGroup(tr);
/* 280 */     root.addChild(tg);
/* 281 */     tg.addChild(cartaz2);
/*     */ 
/*     */     
/* 284 */     Box cartaz3 = new Box(1.0E-5F, madSml_height - 0.065F, 0.3F, 2, apCartaz);
/* 285 */     tr = new Transform3D();
/* 286 */     tr.set(new Vector3f(0.38F, 0.04F, -0.95F));
/* 287 */     tg = new TransformGroup(tr);
/* 288 */     root.addChild(tg);
/* 289 */     tg.addChild(cartaz3);
/*     */ 
/*     */     
/* 292 */     Box parede3 = new Box(madStg_width, madStg_height, 1.4F, 1, this.ap);
/* 293 */     tr = new Transform3D();
/* 294 */     tr.set(new Vector3f(-1.4F, 0.0F, 0.0F));
/* 295 */     tg = new TransformGroup(tr);
/* 296 */     root.addChild(tg);
/* 297 */     tg.addChild(parede3);
/*     */ 
/*     */     
/* 300 */     Box parede4 = new Box(madStg_width, madStg_height, 1.4F, 1, this.ap);
/* 301 */     tr = new Transform3D();
/* 302 */     tr.set(new Vector3f(1.4F, 0.0F, 0.0F));
/* 303 */     tg = new TransformGroup(tr);
/* 304 */     root.addChild(tg);
/* 305 */     tg.addChild(parede4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 311 */     Box P1Quarto1 = new Box(madSml_width, madSml_height, 0.4F, 1, this.ap);
/* 312 */     tr = new Transform3D();
/* 313 */     tr.set(new Vector3f(0.3F, 0.0F, 1.0F));
/* 314 */     tg = new TransformGroup(tr);
/* 315 */     root.addChild(tg);
/* 316 */     tg.addChild(P1Quarto1);
/*     */     
/* 318 */     Box P2Quarto1 = new Box(0.25F, madSml_height, madSml_width, 1, this.ap);
/* 319 */     tr = new Transform3D();
/* 320 */     tr.set(new Vector3f(0.54F, 0.0F, 0.6F));
/* 321 */     tg = new TransformGroup(tr);
/* 322 */     root.addChild(tg);
/* 323 */     tg.addChild(P2Quarto1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 328 */     Box P1Quarto2 = new Box(madSml_width, madSml_height, 0.4F, 1, this.ap);
/* 329 */     tr = new Transform3D();
/* 330 */     tr.set(new Vector3f(0.4F, 0.0F, -0.95F));
/* 331 */     tg = new TransformGroup(tr);
/* 332 */     root.addChild(tg);
/* 333 */     tg.addChild(P1Quarto2);
/*     */     
/* 335 */     Box P2Quarto2 = new Box(0.2F, madSml_height, madSml_width, 1, this.ap);
/* 336 */     tr = new Transform3D();
/* 337 */     tr.set(new Vector3f(1.2F, 0.0F, -0.5F));
/* 338 */     tg = new TransformGroup(tr);
/* 339 */     root.addChild(tg);
/* 340 */     tg.addChild(P2Quarto2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 345 */     Box P1Quarto3 = new Box(madSml_width, madSml_height, 0.2F, 1, this.ap);
/* 346 */     tr = new Transform3D();
/* 347 */     tr.set(new Vector3f(-0.35F, 0.0F, -1.2F));
/* 348 */     tg = new TransformGroup(tr);
/* 349 */     root.addChild(tg);
/* 350 */     tg.addChild(P1Quarto3);
/*     */     
/* 352 */     Box P2Quarto3 = new Box(0.45F, madSml_height, madSml_width, 1, this.ap);
/* 353 */     tr = new Transform3D();
/* 354 */     tr.set(new Vector3f(-0.9F, 0.0F, -0.2F));
/* 355 */     tg = new TransformGroup(tr);
/* 356 */     root.addChild(tg);
/* 357 */     tg.addChild(P2Quarto3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 363 */     Box P1Quarto4 = new Box(0.4F, madSml_height, madSml_width, 1, this.ap);
/* 364 */     tr = new Transform3D();
/* 365 */     tr.set(new Vector3f(-0.6F, 0.0F, 0.5F));
/* 366 */     tg = new TransformGroup(tr);
/* 367 */     root.addChild(tg);
/* 368 */     tg.addChild(P1Quarto4);
/*     */     
/* 370 */     Box P2Quarto4 = new Box(madSml_width, madSml_height, 0.2F, 1, this.ap);
/* 371 */     tr = new Transform3D();
/* 372 */     tr.set(new Vector3f(-0.2F, 0.0F, 0.7F));
/* 373 */     tg = new TransformGroup(tr);
/* 374 */     root.addChild(tg);
/* 375 */     tg.addChild(P2Quarto4);
/*     */     
/* 377 */     Box P3Quarto4 = new Box(madSml_width, madSml_height, 0.232F, 1, this.ap);
/* 378 */     tr = new Transform3D();
/* 379 */     tr.set(new Vector3f(-1.0F, 0.0F, 0.725F));
/* 380 */     tg = new TransformGroup(tr);
/* 381 */     root.addChild(tg);
/* 382 */     tg.addChild(P3Quarto4);
/*     */     
/* 384 */     Box P4Quarto4 = new Box(0.2F, madSml_height, madSml_width, 1, this.ap);
/* 385 */     tr = new Transform3D();
/* 386 */     tr.set(new Vector3f(-0.8F, 0.0F, 0.95F));
/* 387 */     tg = new TransformGroup(tr);
/* 388 */     root.addChild(tg);
/* 389 */     tg.addChild(P4Quarto4);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 394 */     Alpha alpha = new Alpha(-1, 10000L);
/*     */ 
/*     */     
/* 397 */     Background bk = new Background(0.5F, madSml_height, 0.5F);
/* 398 */     bk.setApplicationBounds(this.bounds);
/* 399 */     root.addChild(bk);
/*     */ 
/*     */     
/* 402 */     AmbientLight aL = new AmbientLight(true, new Color3f(Color.WHITE));
/* 403 */     aL.setInfluencingBounds(this.bounds);
/* 404 */     root.addChild(aL);
/*     */     
/* 406 */     PointLight pL = new PointLight(new Color3f(new Color3f(0.74902F, 0.847059F, 0.847059F)), new Point3f(0.0F, 3.0F, 3.0F), new Point3f(1.0F, 0.0F, 0.0F));
/* 407 */     pL.setInfluencingBounds(this.bounds);
/* 408 */     root.addChild(pL);
/*     */     
/* 410 */     return root;
/*     */   }
/*     */   
/*     */   Appearance createTextureAppearance() {
/* 414 */     Appearance ap = new Appearance();
/* 415 */     URL filename = getClass().getClassLoader().getResource("images/cartaz.png");
/* 416 */     TextureLoader loader = new TextureLoader(filename, this);
/* 417 */     ImageComponent2D image = loader.getImage();
/* 418 */     if (image == null) {
/* 419 */       System.out.println("can't find texture file.");
/*     */     }
/* 421 */     Texture2D texture = new Texture2D(1, 6, image.getWidth(), image.getHeight());
/* 422 */     texture.setImage(0, image);
/* 423 */     texture.setEnable(true);
/* 424 */     texture.setMagFilter(3);
/* 425 */     texture.setMinFilter(3);
/* 426 */     ap.setTexture(texture);
/*     */ 
/*     */     
/* 429 */     TextureAttributes texatt = new TextureAttributes();
/* 430 */     texatt.setTextureMode(6);
/* 431 */     ap.setTextureAttributes(texatt);
/* 432 */     ap.setMaterial(new Material());
/* 433 */     return ap;
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\robot\main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */