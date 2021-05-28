/*    */ package robotMoveExtra;
/*    */ 
/*    */ import com.sun.j3d.utils.image.TextureLoader;
/*    */ import java.net.URL;
/*    */ import javax.media.j3d.Appearance;
/*    */ import javax.media.j3d.QuadArray;
/*    */ import javax.media.j3d.Shape3D;
/*    */ import javax.media.j3d.Texture;
/*    */ import javax.media.j3d.TransparencyAttributes;
/*    */ import javax.vecmath.Point3f;
/*    */ import javax.vecmath.TexCoord2f;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImagePanel
/*    */   extends Shape3D
/*    */ {
/*    */   public ImagePanel(String image) {
/* 19 */     QuadArray rect = new QuadArray(4, 33);
/* 20 */     rect.setCoordinate(0, new Point3f(-1.0F, -1.0F, 0.0F));
/* 21 */     rect.setCoordinate(1, new Point3f(1.0F, -1.0F, 0.0F));
/* 22 */     rect.setCoordinate(2, new Point3f(1.0F, 1.0F, 0.0F));
/* 23 */     rect.setCoordinate(3, new Point3f(-1.0F, 1.0F, 0.0F));
/*    */     
/* 25 */     rect.setTextureCoordinate(0, 0, new TexCoord2f(0.0F, 0.0F));
/* 26 */     rect.setTextureCoordinate(0, 1, new TexCoord2f(1.0F, 0.0F));
/* 27 */     rect.setTextureCoordinate(0, 2, new TexCoord2f(1.0F, 1.0F));
/* 28 */     rect.setTextureCoordinate(0, 3, new TexCoord2f(0.0F, 1.0F));
/*    */     
/* 30 */     setGeometry(rect);
/*    */     
/* 32 */     URL url = getClass().getClassLoader().getResource(image);
/* 33 */     TextureLoader loader = new TextureLoader(url, null);
/* 34 */     Texture texture = loader.getTexture();
/*    */     
/* 36 */     Appearance ap = new Appearance();
/* 37 */     ap.setTexture(texture);
/*    */     
/* 39 */     TransparencyAttributes ta = new TransparencyAttributes();
/* 40 */     ta.setTransparencyMode(0);
/*    */     
/* 42 */     ap.setTransparencyAttributes(ta);
/*    */     
/* 44 */     setAppearance(ap);
/*    */   }
/*    */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\robotMoveExtra\ImagePanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */