/*     */ package javax.media.j3d;
/*     */ 
/*     */ import java.util.Hashtable;
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
/*     */ class J3dNodeTable
/*     */ {
/*  34 */   Hashtable nodeTable = new Hashtable();
/*     */ 
/*     */ 
/*     */   
/*     */   static final int MAX_NUM_NODES = 200;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int NOTHING = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int GROUP = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int TRANSFORM_GROUP = 2;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int SWITCH_GROUP = 3;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int ORDERED_GROUP = 4;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int BRANCH_GROUP = 5;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int ENDGROUP = 9;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int SHAPE3D = 10;
/*     */ 
/*     */   
/*     */   static final int APPEARANCE = 20;
/*     */ 
/*     */   
/*     */   static final int MATERIAL = 21;
/*     */ 
/*     */   
/*     */   static final int TEXTURE = 22;
/*     */ 
/*     */   
/*     */   static final int TEX_COORD_GENERATION = 23;
/*     */ 
/*     */   
/*     */   static final int TEXTURE_ATTRIBUTES = 24;
/*     */ 
/*     */   
/*     */   static final int COLORING_ATTRIBUTES = 25;
/*     */ 
/*     */   
/*     */   static final int TRANSPARENCY_ATTRIBUTES = 26;
/*     */ 
/*     */   
/*     */   static final int RENDERING_ATTRIBUTES = 27;
/*     */ 
/*     */   
/*     */   static final int POLYGON_ATTRIBUTES = 28;
/*     */ 
/*     */   
/*     */   static final int LINE_ATTRIBUTES = 29;
/*     */ 
/*     */   
/*     */   static final int POINT_ATTRIBUTES = 30;
/*     */ 
/*     */   
/*     */   static final int TEXTURE_2D = 31;
/*     */ 
/*     */   
/*     */   static final int TEXTURE_3D = 32;
/*     */ 
/*     */   
/*     */   static final int IMAGE_COMPONENT = 33;
/*     */ 
/*     */   
/*     */   static final int IMAGE_COMPONENT_2D = 34;
/*     */ 
/*     */   
/*     */   static final int IMAGE_COMPONENT_3D = 35;
/*     */ 
/*     */   
/*     */   static final int ENDAPPEARANCE = 49;
/*     */ 
/*     */   
/*     */   static final int GEOMETRY = 100;
/*     */ 
/*     */   
/*     */   static final int COMPRESSED_GEOMETRY = 101;
/*     */ 
/*     */   
/*     */   static final int GEOMETRY_ARRAY = 102;
/*     */ 
/*     */   
/*     */   static final int GEOMETRY_STRIP_ARRAY = 103;
/*     */ 
/*     */   
/*     */   static final int INDEXED_GEOMETRY_ARRAY = 104;
/*     */ 
/*     */   
/*     */   static final int INDEXED_GEOMETRY_STRIP_ARRAY = 105;
/*     */ 
/*     */   
/* 142 */   String[] nodeArray = new String[200]; static final int INDEXED_LINE_ARRAY = 106; static final int INDEXED_LINE_STRIP_ARRAY = 107; static final int INDEXED_POINT_ARRAY = 108; static final int INDEXED_QUAD_ARRAY = 109; J3dNodeTable() {
/*     */     byte b;
/* 144 */     for (b = 0; b < 'È'; b++) {
/* 145 */       this.nodeArray[b] = null;
/*     */     }
/* 147 */     this.nodeArray[1] = "Group";
/* 148 */     this.nodeArray[2] = "TransformGroup";
/* 149 */     this.nodeArray[3] = "Switch";
/* 150 */     this.nodeArray[4] = "OrderedGroup";
/* 151 */     this.nodeArray[5] = "BranchGroup";
/*     */     
/* 153 */     this.nodeArray[10] = "Shape3D";
/*     */     
/* 155 */     this.nodeArray[20] = "Appearance";
/* 156 */     this.nodeArray[21] = "Material";
/* 157 */     this.nodeArray[22] = "Texture";
/* 158 */     this.nodeArray[31] = "Texture2D";
/* 159 */     this.nodeArray[32] = "Texture3D";
/* 160 */     this.nodeArray[33] = "ImageComponent";
/* 161 */     this.nodeArray[34] = "ImageComponent2D";
/* 162 */     this.nodeArray[35] = "ImageComponent3D";
/* 163 */     this.nodeArray[26] = "TransparencyAttributes";
/*     */     
/* 165 */     this.nodeArray[100] = "Geometry";
/* 166 */     this.nodeArray[101] = "CompressedGeometry";
/* 167 */     this.nodeArray[102] = "GeometryArray";
/* 168 */     this.nodeArray[103] = "GeometryStripArray";
/* 169 */     this.nodeArray[104] = "IndexedGeometryArray";
/* 170 */     this.nodeArray[105] = "IndexedGeometryStripArray";
/* 171 */     this.nodeArray[106] = "IndexedLineArray";
/* 172 */     this.nodeArray[107] = "IndexedLineStripArray";
/* 173 */     this.nodeArray[108] = "IndexedPointArray";
/* 174 */     this.nodeArray[109] = "IndexedQuadArray";
/* 175 */     this.nodeArray[110] = "IndexedTriangleArray";
/* 176 */     this.nodeArray[111] = "IndexedTriangleFanArray";
/* 177 */     this.nodeArray[112] = "indexedTriangleStripArray";
/* 178 */     this.nodeArray[113] = "LineArray";
/* 179 */     this.nodeArray[114] = "LineStripArray";
/* 180 */     this.nodeArray[115] = "PointArray";
/* 181 */     this.nodeArray[116] = "QuadArray";
/* 182 */     this.nodeArray[117] = "TriangleArray";
/* 183 */     this.nodeArray[118] = "TriangleFanArray";
/* 184 */     this.nodeArray[119] = "TriangleStripArray";
/* 185 */     this.nodeArray[120] = "BackgroundSound";
/* 186 */     this.nodeArray[121] = "PointSound";
/* 187 */     this.nodeArray[122] = "ConeSound";
/* 188 */     this.nodeArray[123] = "MediaContainer";
/*     */     
/* 190 */     this.nodeArray[150] = "RotationInterpolator";
/* 191 */     this.nodeArray[151] = "RotPosScalePathInterpolator";
/* 192 */     this.nodeArray[152] = "RotationPathInterpolator";
/* 193 */     this.nodeArray[153] = "PositionPathInterpolator";
/* 194 */     this.nodeArray[154] = "RotPosPathInterpolator";
/* 195 */     this.nodeArray[155] = "PositionInterpolator";
/* 196 */     this.nodeArray[156] = "SwitchValueInterpolator";
/* 197 */     this.nodeArray[157] = "ColorInterpolator";
/* 198 */     this.nodeArray[158] = "ScaleInterpolator";
/* 199 */     this.nodeArray[159] = "SoundPlayer";
/* 200 */     this.nodeArray[160] = "SoundFader";
/*     */     
/* 202 */     this.nodeArray[170] = "Bounds";
/* 203 */     this.nodeArray[171] = "BoundingSphere";
/* 204 */     this.nodeArray[172] = "BoundingBox";
/* 205 */     this.nodeArray[173] = "BoundingPolytope";
/* 206 */     this.nodeArray[180] = "Transform3D";
/* 207 */     this.nodeArray[181] = "Background";
/*     */     
/* 209 */     this.nodeArray[190] = "Light";
/* 210 */     this.nodeArray[191] = "PointLight";
/* 211 */     this.nodeArray[192] = "SpotLight";
/* 212 */     this.nodeArray[193] = "DirectionalLight";
/* 213 */     this.nodeArray[194] = "AmbientLight";
/*     */     
/* 215 */     for (b = 0; b < 'È'; b++) {
/* 216 */       if (this.nodeArray[b] != null)
/* 217 */         this.nodeTable.put(this.nodeArray[b], new Integer(b)); 
/*     */     } 
/*     */   }
/*     */   static final int INDEXED_TRIANGLE_ARRAY = 110; static final int INDEXED_TRIANGLE_FAN_ARRAY = 111;
/*     */   static final int INDEXED_TRIANGLE_STRIP_ARRAY = 112;
/*     */   static final int LINE_ARRAY = 113;
/*     */   static final int LINE_STRIP_ARRAY = 114;
/*     */   static final int POINT_ARRAY = 115;
/*     */   static final int QUAD_ARRAY = 116;
/*     */   static final int TRIANGLE_ARRAY = 117;
/*     */   static final int TRIANGLE_FAN_ARRAY = 118;
/*     */   
/*     */   int getNodeValue(Object paramObject) {
/* 230 */     String str1 = paramObject.getClass().getName();
/*     */     
/*     */     int i;
/* 233 */     if ((i = str1.lastIndexOf(".")) == -1) {
/* 234 */       i = 0;
/*     */     } else {
/* 236 */       i++;
/* 237 */     }  int j; if ((j = str1.lastIndexOf("Retained")) == -1)
/* 238 */       j = str1.length(); 
/* 239 */     String str2 = str1.substring(i, j); Integer integer;
/* 240 */     if ((integer = (Integer)this.nodeTable.get(str2)) != null)
/*     */     {
/* 242 */       return integer.intValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 249 */     if (paramObject instanceof TransformGroup || paramObject instanceof TransformGroupRetained)
/*     */     {
/* 251 */       return 2; } 
/* 252 */     if (paramObject instanceof BranchGroup || paramObject instanceof BranchGroupRetained)
/*     */     {
/* 254 */       return 5; } 
/* 255 */     if (paramObject instanceof Switch || paramObject instanceof SwitchRetained)
/*     */     {
/* 257 */       return 3; } 
/* 258 */     if (paramObject instanceof Group || paramObject instanceof GroupRetained)
/*     */     {
/* 260 */       return 1; } 
/* 261 */     if (paramObject instanceof Shape3D) {
/* 262 */       return 10;
/*     */     }
/* 264 */     System.err.println("Warning: Don't know how to save object of type " + paramObject);
/* 265 */     return 0;
/*     */   }
/*     */   static final int TRIANGLE_STRIP_ARRAY = 119; static final int BACKGROUND_SOUND = 120; static final int POINT_SOUND = 121; static final int CONE_SOUND = 122; static final int MEDIA_CONTAINER = 123; static final int ROTATION_INTERPOLATOR = 150; static final int ROTPOSSCALEPATH_INTERPOLATOR = 151; static final int ROTATIONPATH_INTERPOLATOR = 152; static final int POSITIONPATH_INTERPOLATOR = 153; static final int ROTPOSPATH_INTERPOLATOR = 154; static final int POSITION_INTERPOLATOR = 155; static final int SWITCHVALUE_INTERPOLATOR = 156; static final int COLOR_INTERPOLATOR = 157;
/*     */   static final int SCALE_INTERPOLATOR = 158;
/*     */   static final int SOUND_PLAYER = 159;
/*     */   static final int SOUND_FADER = 160;
/*     */   static final int BOUNDS = 170;
/*     */   static final int BOUNDING_SPHERE = 171;
/*     */   
/*     */   Object getObject(int paramInt) {
/*     */     try {
/* 276 */       if (this.nodeArray[paramInt] != null) {
/* 277 */         String str = "javax.media.j3d." + this.nodeArray[paramInt];
/* 278 */         return Class.forName(str).newInstance();
/*     */       }
/*     */     
/* 281 */     } catch (Exception exception) {
/* 282 */       throw new RuntimeException("Exception creating object for nodeValue " + paramInt + "\n  nodeName = javax.media.j3d." + this.nodeArray[paramInt]);
/*     */     } 
/*     */ 
/*     */     
/* 286 */     return null;
/*     */   }
/*     */   
/*     */   static final int BOUNDING_BOX = 172;
/*     */   static final int BOUNDING_POLYTOPE = 173;
/*     */   static final int TRANSFORM3D = 180;
/*     */   static final int BACKGROUND = 181;
/*     */   static final int LIGHT = 190;
/*     */   static final int POINT_LIGHT = 191;
/*     */   static final int SPOT_LIGHT = 192;
/*     */   static final int DIRECTIONAL_LIGHT = 193;
/*     */   static final int AMBIENT_LIGHT = 194;
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\J3dNodeTable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */