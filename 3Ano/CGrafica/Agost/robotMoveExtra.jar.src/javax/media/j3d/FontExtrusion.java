/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.util.ArrayList;
/*     */ import javax.vecmath.Point2f;
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
/*     */ public class FontExtrusion
/*     */ {
/*  43 */   float length = 0.2F;
/*     */   
/*     */   Shape shape;
/*     */   Point2f[] pnts;
/*  47 */   double tessellationTolerance = 0.01D;
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
/*  64 */   public FontExtrusion() { this.shape = null; }
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
/*  86 */   public FontExtrusion(Shape paramShape) { setExtrusionShape(paramShape); }
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
/*     */   public FontExtrusion(Shape paramShape, double paramDouble) {
/* 117 */     this.tessellationTolerance = paramDouble;
/* 118 */     setExtrusionShape(paramShape);
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
/*     */   public void setExtrusionShape(Shape paramShape) {
/* 139 */     this.shape = paramShape;
/* 140 */     if (this.shape == null)
/*     */       return; 
/* 142 */     PathIterator pathIterator = this.shape.getPathIterator(null, this.tessellationTolerance);
/* 143 */     ArrayList arrayList = new ArrayList();
/* 144 */     float arrayOfFloat[] = new float[6], f1 = 0.0F;
/* 145 */     byte b = 0; byte b1 = -1;
/*     */ 
/*     */ 
/*     */     
/* 149 */     while (!pathIterator.isDone()) {
/* 150 */       Point2f point2f = new Point2f();
/* 151 */       int j = pathIterator.currentSegment(arrayOfFloat);
/* 152 */       if (j == 1) {
/* 153 */         point2f.x = arrayOfFloat[0];
/* 154 */         point2f.y = arrayOfFloat[1];
/* 155 */         if (b1 == -1) {
/* 156 */           if (f1 < point2f.x) { b1 = 0; }
/* 157 */           else if (f1 > point2f.x) { b1 = 1; }
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 163 */         if ((b1 == 0 && f1 > point2f.x) || (b1 == 1 && f1 < point2f.x))
/*     */         {
/* 165 */           throw new IllegalArgumentException(J3dI18N.getString("FontExtrusion0"));
/*     */         }
/* 167 */         f1 = point2f.x;
/* 168 */         b++;
/* 169 */         arrayList.add(point2f);
/* 170 */       } else if (j == 0) {
/* 171 */         if (b != 0) {
/* 172 */           throw new IllegalArgumentException(J3dI18N.getString("FontExtrusion3"));
/*     */         }
/* 174 */         point2f.x = arrayOfFloat[0];
/* 175 */         point2f.y = arrayOfFloat[1];
/* 176 */         f1 = point2f.x;
/* 177 */         b++;
/* 178 */         arrayList.add(point2f);
/*     */       } 
/* 180 */       pathIterator.next();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     int i = arrayList.size();
/* 188 */     this.pnts = new Point2f[i];
/*     */     
/* 190 */     if (b1 == 0) {
/* 191 */       for (byte b2 = 0; b2 < i; b2++) {
/* 192 */         this.pnts[b2] = (Point2f)arrayList.get(b2);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 197 */       for (int j = 0; j < i; j++) {
/* 198 */         this.pnts[j] = (Point2f)arrayList.get(i - j - 1);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 204 */     (this.pnts[i - 1]).y = 0.0F;
/* 205 */     if ((this.pnts[0]).x != 0.0F) {
/* 206 */       throw new IllegalArgumentException(J3dI18N.getString("FontExtrusion1"));
/*     */     }
/*     */     
/* 209 */     float f2 = (this.pnts[0]).x - (this.pnts[i - 1]).x;
/* 210 */     float f3 = (this.pnts[0]).y - (this.pnts[i - 1]).y;
/* 211 */     this.length = (float)Math.sqrt((f2 * f2 + f3 * f3));
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
/* 226 */   public Shape getExtrusionShape() { return this.shape; }
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
/* 238 */   public double getTessellationTolerance() { return this.tessellationTolerance; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\FontExtrusion.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */