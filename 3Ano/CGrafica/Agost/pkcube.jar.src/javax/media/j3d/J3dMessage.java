/*     */ package javax.media.j3d;
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
/*     */ class J3dMessage
/*     */ {
/*     */   static final int INVALID_TYPE = -1;
/*     */   static final int INSERT_NODES = 0;
/*     */   static final int REMOVE_NODES = 1;
/*     */   static final int RUN = 2;
/*     */   static final int TRANSFORM_CHANGED = 3;
/*     */   static final int UPDATE_VIEW = 4;
/*     */   static final int STOP_THREAD = 5;
/*     */   static final int COLORINGATTRIBUTES_CHANGED = 6;
/*     */   static final int LINEATTRIBUTES_CHANGED = 7;
/*     */   static final int POINTATTRIBUTES_CHANGED = 8;
/*     */   static final int POLYGONATTRIBUTES_CHANGED = 9;
/*     */   static final int RENDERINGATTRIBUTES_CHANGED = 10;
/*     */   static final int TEXTUREATTRIBUTES_CHANGED = 11;
/*     */   static final int TRANSPARENCYATTRIBUTES_CHANGED = 12;
/*     */   static final int MATERIAL_CHANGED = 13;
/*     */   static final int TEXCOORDGENERATION_CHANGED = 14;
/*     */   static final int TEXTURE_CHANGED = 15;
/*     */   static final int MORPH_CHANGED = 16;
/*     */   static final int GEOMETRY_CHANGED = 17;
/*     */   static final int APPEARANCE_CHANGED = 18;
/*     */   static final int LIGHT_CHANGED = 19;
/*     */   static final int BACKGROUND_CHANGED = 20;
/*     */   static final int CLIP_CHANGED = 21;
/*     */   static final int FOG_CHANGED = 22;
/*     */   static final int BOUNDINGLEAF_CHANGED = 23;
/*     */   static final int SHAPE3D_CHANGED = 24;
/*     */   static final int TEXT3D_TRANSFORM_CHANGED = 25;
/*     */   static final int TEXT3D_DATA_CHANGED = 26;
/*     */   static final int SWITCH_CHANGED = 27;
/*     */   static final int COND_MET = 28;
/*     */   static final int BEHAVIOR_ENABLE = 29;
/*     */   static final int BEHAVIOR_DISABLE = 30;
/*     */   static final int INSERT_RENDERATOMS = 31;
/*     */   static final int ORDERED_GROUP_INSERTED = 32;
/*     */   static final int ORDERED_GROUP_REMOVED = 33;
/*     */   static final int COLLISION_BOUND_CHANGED = 34;
/*     */   static final int REGION_BOUND_CHANGED = 35;
/*     */   static final int MODELCLIP_CHANGED = 36;
/*     */   static final int BOUNDS_AUTO_COMPUTE_CHANGED = 37;
/*     */   static final int SOUND_ATTRIB_CHANGED = 38;
/*     */   static final int AURALATTRIBUTES_CHANGED = 39;
/*     */   static final int SOUNDSCAPE_CHANGED = 40;
/*     */   static final int ALTERNATEAPPEARANCE_CHANGED = 41;
/*     */   static final int RENDER_OFFSCREEN = 42;
/*     */   static final int RENDER_RETAINED = 43;
/*     */   static final int RENDER_IMMEDIATE = 44;
/*     */   static final int SOUND_STATE_CHANGED = 45;
/*     */   static final int ORIENTEDSHAPE3D_CHANGED = 46;
/*     */   static final int TEXTURE_UNIT_STATE_CHANGED = 47;
/*     */   static final int UPDATE_VIEWPLATFORM = 48;
/*     */   static final int BEHAVIOR_ACTIVATE = 49;
/*     */   static final int GEOMETRYARRAY_CHANGED = 50;
/*     */   static final int MEDIA_CONTAINER_CHANGED = 51;
/*     */   static final int RESIZE_CANVAS = 52;
/*     */   static final int TOGGLE_CANVAS = 53;
/*     */   static final int IMAGE_COMPONENT_CHANGED = 54;
/*     */   static final int SCHEDULING_INTERVAL_CHANGED = 55;
/*     */   static final int VIEWSPECIFICGROUP_CHANGED = 56;
/*     */   static final int VIEWSPECIFICGROUP_INIT = 57;
/*     */   static final int VIEWSPECIFICGROUP_CLEAR = 58;
/*     */   static final int ORDERED_GROUP_TABLE_CHANGED = 59;
/*     */   static final int BEHAVIOR_REEVALUATE = 60;
/*     */   static final int CREATE_OFFSCREENBUFFER = 61;
/*     */   static final int DESTROY_CTX_AND_OFFSCREENBUFFER = 62;
/*     */   static final int SHADER_ATTRIBUTE_CHANGED = 63;
/*     */   static final int SHADER_ATTRIBUTE_SET_CHANGED = 64;
/*     */   static final int SHADER_APPEARANCE_CHANGED = 65;
/*     */   static final int ALLOCATE_CANVASID = 66;
/*     */   static final int FREE_CANVASID = 67;
/*  97 */   long time = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   private int refcount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   int threads = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   VirtualUniverse universe;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   int type = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   View view = null;
/*     */ 
/*     */ 
/*     */   
/*     */   static final int MAX_ARGS = 6;
/*     */ 
/*     */ 
/*     */   
/* 131 */   Object[] args = new Object[6];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void clear() {
/* 141 */     this.view = null;
/* 142 */     this.universe = null;
/* 143 */     this.args[0] = null;
/* 144 */     this.args[1] = null;
/* 145 */     this.args[2] = null;
/* 146 */     this.args[3] = null;
/* 147 */     this.args[4] = null;
/* 148 */     this.args[5] = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   final void incRefcount() { this.refcount++; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void decRefcount() {
/* 163 */     if (--this.refcount == 0) {
/* 164 */       clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 169 */   final int getRefcount() { return this.refcount; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\pkcube.jar!\javax\media\j3d\J3dMessage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */