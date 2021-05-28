package javax.media.j3d;

class CapabilityBits {
  static final int NODE_ENABLE_COLLISION_REPORTING = 0;
  
  static final int NODE_ENABLE_PICK_REPORTING = 1;
  
  private static final int NODE_UNUSED_BIT = 2;
  
  static final int NODE_ALLOW_BOUNDS_READ = 3;
  
  static final int NODE_ALLOW_BOUNDS_WRITE = 4;
  
  static final int NODE_ALLOW_PICKABLE_READ = 5;
  
  static final int NODE_ALLOW_PICKABLE_WRITE = 6;
  
  static final int NODE_ALLOW_COLLIDABLE_READ = 7;
  
  static final int NODE_ALLOW_COLLIDABLE_WRITE = 8;
  
  static final int NODE_ALLOW_AUTO_COMPUTE_BOUNDS_READ = 9;
  
  static final int NODE_ALLOW_AUTO_COMPUTE_BOUNDS_WRITE = 10;
  
  static final int NODE_ALLOW_LOCAL_TO_VWORLD_READ = 11;
  
  static final int GROUP_ALLOW_CHILDREN_READ = 12;
  
  static final int GROUP_ALLOW_CHILDREN_WRITE = 13;
  
  static final int GROUP_ALLOW_CHILDREN_EXTEND = 14;
  
  static final int GROUP_ALLOW_COLLISION_BOUNDS_READ = 15;
  
  static final int GROUP_ALLOW_COLLISION_BOUNDS_WRITE = 16;
  
  static final int BRANCH_GROUP_ALLOW_DETACH = 17;
  
  static final int SHARED_GROUP_ALLOW_LINK_READ = 17;
  
  static final int TRANSFORM_GROUP_ALLOW_TRANSFORM_READ = 17;
  
  static final int TRANSFORM_GROUP_ALLOW_TRANSFORM_WRITE = 18;
  
  static final int SWITCH_ALLOW_SWITCH_READ = 17;
  
  static final int SWITCH_ALLOW_SWITCH_WRITE = 18;
  
  static final int VIEW_SPECIFIC_GROUP_ALLOW_VIEW_READ = 17;
  
  static final int VIEW_SPECIFIC_GROUP_ALLOW_VIEW_WRITE = 18;
  
  static final int ORDERED_GROUP_ALLOW_CHILD_INDEX_ORDER_READ = 17;
  
  static final int ORDERED_GROUP_ALLOW_CHILD_INDEX_ORDER_WRITE = 18;
  
  static final int BACKGROUND_ALLOW_APPLICATION_BOUNDS_READ = 12;
  
  static final int BACKGROUND_ALLOW_APPLICATION_BOUNDS_WRITE = 13;
  
  static final int BACKGROUND_ALLOW_IMAGE_READ = 14;
  
  static final int BACKGROUND_ALLOW_IMAGE_WRITE = 15;
  
  static final int BACKGROUND_ALLOW_COLOR_READ = 16;
  
  static final int BACKGROUND_ALLOW_COLOR_WRITE = 17;
  
  static final int BACKGROUND_ALLOW_GEOMETRY_READ = 18;
  
  static final int BACKGROUND_ALLOW_GEOMETRY_WRITE = 19;
  
  static final int BACKGROUND_ALLOW_IMAGE_SCALE_MODE_READ = 20;
  
  static final int BACKGROUND_ALLOW_IMAGE_SCALE_MODE_WRITE = 21;
  
  static final int BOUNDING_LEAF_ALLOW_REGION_READ = 12;
  
  static final int BOUNDING_LEAF_ALLOW_REGION_WRITE = 13;
  
  static final int CLIP_ALLOW_APPLICATION_BOUNDS_READ = 12;
  
  static final int CLIP_ALLOW_APPLICATION_BOUNDS_WRITE = 13;
  
  static final int CLIP_ALLOW_BACK_DISTANCE_READ = 14;
  
  static final int CLIP_ALLOW_BACK_DISTANCE_WRITE = 15;
  
  static final int MORPH_ALLOW_GEOMETRY_ARRAY_READ = 12;
  
  static final int MORPH_ALLOW_GEOMETRY_ARRAY_WRITE = 13;
  
  static final int MORPH_ALLOW_APPEARANCE_READ = 14;
  
  static final int MORPH_ALLOW_APPEARANCE_WRITE = 15;
  
  static final int MORPH_ALLOW_WEIGHTS_READ = 16;
  
  static final int MORPH_ALLOW_WEIGHTS_WRITE = 17;
  
  static final int MORPH_ALLOW_COLLISION_BOUNDS_READ = 18;
  
  static final int MORPH_ALLOW_COLLISION_BOUNDS_WRITE = 19;
  
  static final int MORPH_ALLOW_APPEARANCE_OVERRIDE_READ = 20;
  
  static final int MORPH_ALLOW_APPEARANCE_OVERRIDE_WRITE = 21;
  
  static final int LINK_ALLOW_SHARED_GROUP_READ = 12;
  
  static final int LINK_ALLOW_SHARED_GROUP_WRITE = 13;
  
  static final int SHAPE3D_ALLOW_GEOMETRY_READ = 12;
  
  static final int SHAPE3D_ALLOW_GEOMETRY_WRITE = 13;
  
  static final int SHAPE3D_ALLOW_APPEARANCE_READ = 14;
  
  static final int SHAPE3D_ALLOW_APPEARANCE_WRITE = 15;
  
  static final int SHAPE3D_ALLOW_COLLISION_BOUNDS_READ = 16;
  
  static final int SHAPE3D_ALLOW_COLLISION_BOUNDS_WRITE = 17;
  
  static final int SHAPE3D_ALLOW_APPEARANCE_OVERRIDE_READ = 18;
  
  static final int SHAPE3D_ALLOW_APPEARANCE_OVERRIDE_WRITE = 19;
  
  static final int ORIENTED_SHAPE3D_ALLOW_MODE_READ = 20;
  
  static final int ORIENTED_SHAPE3D_ALLOW_MODE_WRITE = 21;
  
  static final int ORIENTED_SHAPE3D_ALLOW_AXIS_READ = 22;
  
  static final int ORIENTED_SHAPE3D_ALLOW_AXIS_WRITE = 23;
  
  static final int ORIENTED_SHAPE3D_ALLOW_POINT_READ = 24;
  
  static final int ORIENTED_SHAPE3D_ALLOW_POINT_WRITE = 25;
  
  static final int ORIENTED_SHAPE3D_ALLOW_SCALE_READ = 26;
  
  static final int ORIENTED_SHAPE3D_ALLOW_SCALE_WRITE = 27;
  
  static final int SOUNDSCAPE_ALLOW_APPLICATION_BOUNDS_READ = 12;
  
  static final int SOUNDSCAPE_ALLOW_APPLICATION_BOUNDS_WRITE = 13;
  
  static final int SOUNDSCAPE_ALLOW_ATTRIBUTES_READ = 14;
  
  static final int SOUNDSCAPE_ALLOW_ATTRIBUTES_WRITE = 15;
  
  static final int VIEW_PLATFORM_ALLOW_POLICY_READ = 12;
  
  static final int VIEW_PLATFORM_ALLOW_POLICY_WRITE = 13;
  
  static final int FOG_ALLOW_INFLUENCING_BOUNDS_READ = 12;
  
  static final int FOG_ALLOW_INFLUENCING_BOUNDS_WRITE = 13;
  
  static final int FOG_ALLOW_COLOR_READ = 14;
  
  static final int FOG_ALLOW_COLOR_WRITE = 15;
  
  static final int EXPONENTIAL_FOG_ALLOW_DENSITY_READ = 16;
  
  static final int EXPONENTIAL_FOG_ALLOW_DENSITY_WRITE = 17;
  
  static final int LINEAR_FOG_ALLOW_DISTANCE_READ = 16;
  
  static final int LINEAR_FOG_ALLOW_DISTANCE_WRITE = 17;
  
  static final int FOG_ALLOW_SCOPE_READ = 18;
  
  static final int FOG_ALLOW_SCOPE_WRITE = 19;
  
  static final int LIGHT_ALLOW_STATE_READ = 12;
  
  static final int LIGHT_ALLOW_STATE_WRITE = 13;
  
  static final int LIGHT_ALLOW_COLOR_READ = 14;
  
  static final int LIGHT_ALLOW_COLOR_WRITE = 15;
  
  static final int LIGHT_ALLOW_INFLUENCING_BOUNDS_READ = 16;
  
  static final int LIGHT_ALLOW_INFLUENCING_BOUNDS_WRITE = 17;
  
  static final int DIRECTIONAL_LIGHT_ALLOW_DIRECTION_READ = 18;
  
  static final int DIRECTIONAL_LIGHT_ALLOW_DIRECTION_WRITE = 19;
  
  static final int POINT_LIGHT_ALLOW_POSITION_READ = 18;
  
  static final int POINT_LIGHT_ALLOW_POSITION_WRITE = 19;
  
  static final int POINT_LIGHT_ALLOW_ATTENUATION_READ = 20;
  
  static final int POINT_LIGHT_ALLOW_ATTENUATION_WRITE = 21;
  
  static final int SPOT_LIGHT_ALLOW_SPREAD_ANGLE_WRITE = 22;
  
  static final int SPOT_LIGHT_ALLOW_SPREAD_ANGLE_READ = 23;
  
  static final int SPOT_LIGHT_ALLOW_CONCENTRATION_WRITE = 24;
  
  static final int SPOT_LIGHT_ALLOW_CONCENTRATION_READ = 25;
  
  static final int SPOT_LIGHT_ALLOW_DIRECTION_WRITE = 26;
  
  static final int SPOT_LIGHT_ALLOW_DIRECTION_READ = 27;
  
  static final int LIGHT_ALLOW_SCOPE_READ = 28;
  
  static final int LIGHT_ALLOW_SCOPE_WRITE = 29;
  
  static final int SOUND_ALLOW_SOUND_DATA_READ = 12;
  
  static final int SOUND_ALLOW_SOUND_DATA_WRITE = 13;
  
  static final int SOUND_ALLOW_INITIAL_GAIN_READ = 14;
  
  static final int SOUND_ALLOW_INITIAL_GAIN_WRITE = 15;
  
  static final int SOUND_ALLOW_LOOP_READ = 16;
  
  static final int SOUND_ALLOW_LOOP_WRITE = 17;
  
  static final int SOUND_ALLOW_RELEASE_READ = 18;
  
  static final int SOUND_ALLOW_RELEASE_WRITE = 19;
  
  static final int SOUND_ALLOW_CONT_PLAY_READ = 20;
  
  static final int SOUND_ALLOW_CONT_PLAY_WRITE = 21;
  
  static final int SOUND_ALLOW_ENABLE_READ = 22;
  
  static final int SOUND_ALLOW_ENABLE_WRITE = 23;
  
  static final int SOUND_ALLOW_SCHEDULING_BOUNDS_READ = 24;
  
  static final int SOUND_ALLOW_SCHEDULING_BOUNDS_WRITE = 25;
  
  static final int SOUND_ALLOW_PRIORITY_READ = 26;
  
  static final int SOUND_ALLOW_PRIORITY_WRITE = 27;
  
  static final int SOUND_ALLOW_DURATION_READ = 28;
  
  static final int SOUND_ALLOW_IS_READY_READ = 29;
  
  static final int SOUND_ALLOW_IS_PLAYING_READ = 30;
  
  static final int SOUND_ALLOW_CHANNELS_USED_READ = 31;
  
  static final int SOUND_ALLOW_MUTE_READ = 40;
  
  static final int SOUND_ALLOW_MUTE_WRITE = 41;
  
  static final int SOUND_ALLOW_PAUSE_READ = 42;
  
  static final int SOUND_ALLOW_PAUSE_WRITE = 43;
  
  static final int SOUND_ALLOW_RATE_SCALE_FACTOR_READ = 44;
  
  static final int SOUND_ALLOW_RATE_SCALE_FACTOR_WRITE = 45;
  
  static final int POINT_SOUND_ALLOW_POSITION_READ = 32;
  
  static final int POINT_SOUND_ALLOW_POSITION_WRITE = 33;
  
  static final int POINT_SOUND_ALLOW_DISTANCE_GAIN_READ = 34;
  
  static final int POINT_SOUND_ALLOW_DISTANCE_GAIN_WRITE = 35;
  
  static final int CONE_SOUND_ALLOW_DIRECTION_READ = 36;
  
  static final int CONE_SOUND_ALLOW_DIRECTION_WRITE = 37;
  
  static final int CONE_SOUND_ALLOW_ANGULAR_ATTENUATION_READ = 38;
  
  static final int CONE_SOUND_ALLOW_ANGULAR_ATTENUATION_WRITE = 39;
  
  static final int MODEL_CLIP_ALLOW_INFLUENCING_BOUNDS_READ = 12;
  
  static final int MODEL_CLIP_ALLOW_INFLUENCING_BOUNDS_WRITE = 13;
  
  static final int MODEL_CLIP_ALLOW_PLANE_READ = 14;
  
  static final int MODEL_CLIP_ALLOW_PLANE_WRITE = 15;
  
  static final int MODEL_CLIP_ALLOW_ENABLE_READ = 16;
  
  static final int MODEL_CLIP_ALLOW_ENABLE_WRITE = 17;
  
  static final int MODEL_CLIP_ALLOW_SCOPE_READ = 18;
  
  static final int MODEL_CLIP_ALLOW_SCOPE_WRITE = 19;
  
  static final int ALTERNATE_APPEARANCE_ALLOW_INFLUENCING_BOUNDS_READ = 12;
  
  static final int ALTERNATE_APPEARANCE_ALLOW_INFLUENCING_BOUNDS_WRITE = 13;
  
  static final int ALTERNATE_APPEARANCE_ALLOW_APPEARANCE_READ = 14;
  
  static final int ALTERNATE_APPEARANCE_ALLOW_APPEARANCE_WRITE = 15;
  
  static final int ALTERNATE_APPEARANCE_ALLOW_SCOPE_READ = 16;
  
  static final int ALTERNATE_APPEARANCE_ALLOW_SCOPE_WRITE = 17;
  
  static final int NODE_ALLOW_PARENT_READ = 46;
  
  static final int NODE_ALLOW_LOCALE_READ = 47;
  
  static final int APPEARANCE_ALLOW_MATERIAL_READ = 0;
  
  static final int APPEARANCE_ALLOW_MATERIAL_WRITE = 1;
  
  static final int APPEARANCE_ALLOW_TEXTURE_READ = 2;
  
  static final int APPEARANCE_ALLOW_TEXTURE_WRITE = 3;
  
  static final int APPEARANCE_ALLOW_TEXGEN_READ = 4;
  
  static final int APPEARANCE_ALLOW_TEXGEN_WRITE = 5;
  
  static final int APPEARANCE_ALLOW_TEXTURE_ATTRIBUTES_READ = 6;
  
  static final int APPEARANCE_ALLOW_TEXTURE_ATTRIBUTES_WRITE = 7;
  
  static final int APPEARANCE_ALLOW_COLORING_ATTRIBUTES_READ = 8;
  
  static final int APPEARANCE_ALLOW_COLORING_ATTRIBUTES_WRITE = 9;
  
  static final int APPEARANCE_ALLOW_TRANSPARENCY_ATTRIBUTES_READ = 10;
  
  static final int APPEARANCE_ALLOW_TRANSPARENCY_ATTRIBUTES_WRITE = 11;
  
  static final int APPEARANCE_ALLOW_RENDERING_ATTRIBUTES_READ = 12;
  
  static final int APPEARANCE_ALLOW_RENDERING_ATTRIBUTES_WRITE = 13;
  
  static final int APPEARANCE_ALLOW_POLYGON_ATTRIBUTES_READ = 14;
  
  static final int APPEARANCE_ALLOW_POLYGON_ATTRIBUTES_WRITE = 15;
  
  static final int APPEARANCE_ALLOW_LINE_ATTRIBUTES_READ = 16;
  
  static final int APPEARANCE_ALLOW_LINE_ATTRIBUTES_WRITE = 17;
  
  static final int APPEARANCE_ALLOW_POINT_ATTRIBUTES_READ = 18;
  
  static final int APPEARANCE_ALLOW_POINT_ATTRIBUTES_WRITE = 19;
  
  static final int APPEARANCE_ALLOW_TEXTURE_UNIT_STATE_READ = 20;
  
  static final int APPEARANCE_ALLOW_TEXTURE_UNIT_STATE_WRITE = 21;
  
  static final int SHADER_APPEARANCE_ALLOW_SHADER_PROGRAM_READ = 22;
  
  static final int SHADER_APPEARANCE_ALLOW_SHADER_PROGRAM_WRITE = 23;
  
  static final int SHADER_APPEARANCE_ALLOW_SHADER_ATTRIBUTE_SET_READ = 24;
  
  static final int SHADER_APPEARANCE_ALLOW_SHADER_ATTRIBUTE_SET_WRITE = 25;
  
  static final int AURAL_ATTRIBUTES_ALLOW_ATTRIBUTE_GAIN_READ = 0;
  
  static final int AURAL_ATTRIBUTES_ALLOW_ATTRIBUTE_GAIN_WRITE = 1;
  
  static final int AURAL_ATTRIBUTES_ALLOW_ROLLOFF_READ = 2;
  
  static final int AURAL_ATTRIBUTES_ALLOW_ROLLOFF_WRITE = 3;
  
  static final int AURAL_ATTRIBUTES_ALLOW_REFLECTION_COEFFICIENT_READ = 4;
  
  static final int AURAL_ATTRIBUTES_ALLOW_REFLECTION_COEFFICIENT_WRITE = 5;
  
  static final int AURAL_ATTRIBUTES_ALLOW_REVERB_DELAY_READ = 6;
  
  static final int AURAL_ATTRIBUTES_ALLOW_REVERB_DELAY_WRITE = 7;
  
  static final int AURAL_ATTRIBUTES_ALLOW_REVERB_ORDER_READ = 8;
  
  static final int AURAL_ATTRIBUTES_ALLOW_REVERB_ORDER_WRITE = 9;
  
  static final int AURAL_ATTRIBUTES_ALLOW_DISTANCE_FILTER_READ = 10;
  
  static final int AURAL_ATTRIBUTES_ALLOW_DISTANCE_FILTER_WRITE = 11;
  
  static final int AURAL_ATTRIBUTES_ALLOW_FREQUENCY_SCALE_FACTOR_READ = 12;
  
  static final int AURAL_ATTRIBUTES_ALLOW_FREQUENCY_SCALE_FACTOR_WRITE = 13;
  
  static final int AURAL_ATTRIBUTES_ALLOW_VELOCITY_SCALE_FACTOR_READ = 14;
  
  static final int AURAL_ATTRIBUTES_ALLOW_VELOCITY_SCALE_FACTOR_WRITE = 15;
  
  static final int AURAL_ATTRIBUTES_ALLOW_REFLECTION_DELAY_READ = 16;
  
  static final int AURAL_ATTRIBUTES_ALLOW_REFLECTION_DELAY_WRITE = 17;
  
  static final int AURAL_ATTRIBUTES_ALLOW_REVERB_COEFFICIENT_READ = 18;
  
  static final int AURAL_ATTRIBUTES_ALLOW_REVERB_COEFFICIENT_WRITE = 19;
  
  static final int AURAL_ATTRIBUTES_ALLOW_DECAY_TIME_READ = 20;
  
  static final int AURAL_ATTRIBUTES_ALLOW_DECAY_TIME_WRITE = 21;
  
  static final int AURAL_ATTRIBUTES_ALLOW_DECAY_FILTER_READ = 22;
  
  static final int AURAL_ATTRIBUTES_ALLOW_DECAY_FILTER_WRITE = 23;
  
  static final int AURAL_ATTRIBUTES_ALLOW_DIFFUSION_READ = 24;
  
  static final int AURAL_ATTRIBUTES_ALLOW_DIFFUSION_WRITE = 25;
  
  static final int AURAL_ATTRIBUTES_ALLOW_DENSITY_READ = 26;
  
  static final int AURAL_ATTRIBUTES_ALLOW_DENSITY_WRITE = 27;
  
  static final int COLORING_ATTRIBUTES_ALLOW_COLOR_READ = 0;
  
  static final int COLORING_ATTRIBUTES_ALLOW_COLOR_WRITE = 1;
  
  static final int COLORING_ATTRIBUTES_ALLOW_SHADE_MODEL_READ = 2;
  
  static final int COLORING_ATTRIBUTES_ALLOW_SHADE_MODEL_WRITE = 3;
  
  static final int DEPTH_COMPONENT_ALLOW_SIZE_READ = 0;
  
  static final int DEPTH_COMPONENT_ALLOW_DATA_READ = 1;
  
  static final int IMAGE_COMPONENT_ALLOW_SIZE_READ = 0;
  
  static final int IMAGE_COMPONENT_ALLOW_FORMAT_READ = 1;
  
  static final int IMAGE_COMPONENT_ALLOW_IMAGE_READ = 2;
  
  static final int IMAGE_COMPONENT_ALLOW_IMAGE_WRITE = 3;
  
  static final int LINE_ATTRIBUTES_ALLOW_WIDTH_READ = 0;
  
  static final int LINE_ATTRIBUTES_ALLOW_WIDTH_WRITE = 1;
  
  static final int LINE_ATTRIBUTES_ALLOW_PATTERN_READ = 2;
  
  static final int LINE_ATTRIBUTES_ALLOW_PATTERN_WRITE = 3;
  
  static final int LINE_ATTRIBUTES_ALLOW_ANTIALIASING_READ = 4;
  
  static final int LINE_ATTRIBUTES_ALLOW_ANTIALIASING_WRITE = 5;
  
  static final int MATERIAL_ALLOW_COMPONENT_READ = 0;
  
  static final int MATERIAL_ALLOW_COMPONENT_WRITE = 1;
  
  static final int MEDIA_CONTAINER_ALLOW_CACHE_READ = 0;
  
  static final int MEDIA_CONTAINER_ALLOW_CACHE_WRITE = 1;
  
  static final int MEDIA_CONTAINER_ALLOW_URL_READ = 2;
  
  static final int MEDIA_CONTAINER_ALLOW_URL_WRITE = 3;
  
  static final int POINT_ATTRIBUTES_ALLOW_SIZE_READ = 0;
  
  static final int POINT_ATTRIBUTES_ALLOW_SIZE_WRITE = 1;
  
  static final int POINT_ATTRIBUTES_ALLOW_ANTIALIASING_READ = 2;
  
  static final int POINT_ATTRIBUTES_ALLOW_ANTIALIASING_WRITE = 3;
  
  static final int POLYGON_ATTRIBUTES_ALLOW_CULL_FACE_READ = 0;
  
  static final int POLYGON_ATTRIBUTES_ALLOW_CULL_FACE_WRITE = 1;
  
  static final int POLYGON_ATTRIBUTES_ALLOW_MODE_READ = 2;
  
  static final int POLYGON_ATTRIBUTES_ALLOW_MODE_WRITE = 3;
  
  static final int POLYGON_ATTRIBUTES_ALLOW_OFFSET_READ = 4;
  
  static final int POLYGON_ATTRIBUTES_ALLOW_OFFSET_WRITE = 5;
  
  static final int POLYGON_ATTRIBUTES_ALLOW_NORMAL_FLIP_READ = 6;
  
  static final int POLYGON_ATTRIBUTES_ALLOW_NORMAL_FLIP_WRITE = 7;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_ALPHA_TEST_VALUE_READ = 0;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_ALPHA_TEST_VALUE_WRITE = 1;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_ALPHA_TEST_FUNCTION_READ = 2;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_ALPHA_TEST_FUNCTION_WRITE = 3;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_DEPTH_ENABLE_READ = 4;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_VISIBLE_READ = 5;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_VISIBLE_WRITE = 6;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_RASTER_OP_READ = 7;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_RASTER_OP_WRITE = 8;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_IGNORE_VERTEX_COLORS_READ = 9;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_IGNORE_VERTEX_COLORS_WRITE = 10;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_DEPTH_ENABLE_WRITE = 11;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_DEPTH_TEST_FUNCTION_READ = 12;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_DEPTH_TEST_FUNCTION_WRITE = 13;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_STENCIL_ATTRIBUTES_READ = 14;
  
  static final int RENDERING_ATTRIBUTES_ALLOW_STENCIL_ATTRIBUTES_WRITE = 15;
  
  static final int TEX_COORD_GENERATION_ALLOW_ENABLE_READ = 0;
  
  static final int TEX_COORD_GENERATION_ALLOW_ENABLE_WRITE = 1;
  
  static final int TEX_COORD_GENERATION_ALLOW_FORMAT_READ = 2;
  
  static final int TEX_COORD_GENERATION_ALLOW_MODE_READ = 3;
  
  static final int TEX_COORD_GENERATION_ALLOW_PLANE_READ = 4;
  
  static final int TEX_COORD_GENERATION_ALLOW_PLANE_WRITE = 5;
  
  static final int TEXTURE_ALLOW_ENABLE_READ = 0;
  
  static final int TEXTURE_ALLOW_ENABLE_WRITE = 1;
  
  static final int TEXTURE_ALLOW_BOUNDARY_MODE_READ = 2;
  
  static final int TEXTURE_ALLOW_FILTER_READ = 3;
  
  static final int TEXTURE_ALLOW_IMAGE_READ = 4;
  
  static final int TEXTURE_ALLOW_MIPMAP_MODE_READ = 5;
  
  static final int TEXTURE_ALLOW_BOUNDARY_COLOR_READ = 6;
  
  static final int TEXTURE_ALLOW_IMAGE_WRITE = 7;
  
  static final int TEXTURE_ALLOW_SIZE_READ = 8;
  
  static final int TEXTURE_ALLOW_FORMAT_READ = 9;
  
  static final int TEXTURE_ALLOW_LOD_RANGE_READ = 10;
  
  static final int TEXTURE_ALLOW_LOD_RANGE_WRITE = 11;
  
  static final int TEXTURE_ALLOW_ANISOTROPIC_FILTER_READ = 12;
  
  static final int TEXTURE_ALLOW_SHARPEN_TEXTURE_READ = 13;
  
  static final int TEXTURE_ALLOW_FILTER4_READ = 14;
  
  static final int TEXTURE2D_ALLOW_DETAIL_TEXTURE_READ = 15;
  
  static final int TEXTURE_ATTRIBUTES_ALLOW_MODE_READ = 0;
  
  static final int TEXTURE_ATTRIBUTES_ALLOW_MODE_WRITE = 1;
  
  static final int TEXTURE_ATTRIBUTES_ALLOW_BLEND_COLOR_READ = 2;
  
  static final int TEXTURE_ATTRIBUTES_ALLOW_BLEND_COLOR_WRITE = 3;
  
  static final int TEXTURE_ATTRIBUTES_ALLOW_TRANSFORM_READ = 4;
  
  static final int TEXTURE_ATTRIBUTES_ALLOW_TRANSFORM_WRITE = 5;
  
  static final int TEXTURE_ATTRIBUTES_ALLOW_COLOR_TABLE_READ = 6;
  
  static final int TEXTURE_ATTRIBUTES_ALLOW_COLOR_TABLE_WRITE = 7;
  
  static final int TEXTURE_ATTRIBUTES_ALLOW_COMBINE_READ = 8;
  
  static final int TEXTURE_ATTRIBUTES_ALLOW_COMBINE_WRITE = 9;
  
  static final int TRANSPARENCY_ATTRIBUTES_ALLOW_MODE_READ = 0;
  
  static final int TRANSPARENCY_ATTRIBUTES_ALLOW_MODE_WRITE = 1;
  
  static final int TRANSPARENCY_ATTRIBUTES_ALLOW_VALUE_READ = 2;
  
  static final int TRANSPARENCY_ATTRIBUTES_ALLOW_VALUE_WRITE = 3;
  
  static final int TRANSPARENCY_ATTRIBUTES_ALLOW_BLEND_FUNCTION_READ = 4;
  
  static final int TRANSPARENCY_ATTRIBUTES_ALLOW_BLEND_FUNCTION_WRITE = 5;
  
  static final int TEXTURE_UNIT_STATE_ALLOW_STATE_READ = 0;
  
  static final int TEXTURE_UNIT_STATE_ALLOW_STATE_WRITE = 1;
  
  static final int SHADER_PROGRAM_ALLOW_SHADERS_READ = 0;
  
  static final int SHADER_PROGRAM_ALLOW_NAMES_READ = 1;
  
  static final int SHADER_ATTRIBUTE_SET_ALLOW_ATTRIBUTES_READ = 0;
  
  static final int SHADER_ATTRIBUTE_SET_ALLOW_ATTRIBUTES_WRITE = 1;
  
  static final int SHADER_ATTRIBUTE_OBJECT_ALLOW_VALUE_READ = 0;
  
  static final int SHADER_ATTRIBUTE_OBJECT_ALLOW_VALUE_WRITE = 1;
  
  static final int GEOMETRY_ARRAY_ALLOW_COORDINATE_READ = 0;
  
  static final int GEOMETRY_ARRAY_ALLOW_COORDINATE_WRITE = 1;
  
  static final int GEOMETRY_ARRAY_ALLOW_COLOR_READ = 2;
  
  static final int GEOMETRY_ARRAY_ALLOW_COLOR_WRITE = 3;
  
  static final int GEOMETRY_ARRAY_ALLOW_NORMAL_READ = 4;
  
  static final int GEOMETRY_ARRAY_ALLOW_NORMAL_WRITE = 5;
  
  static final int GEOMETRY_ARRAY_ALLOW_TEXCOORD_READ = 6;
  
  static final int GEOMETRY_ARRAY_ALLOW_TEXCOORD_WRITE = 7;
  
  static final int GEOMETRY_ARRAY_ALLOW_COUNT_READ = 8;
  
  static final int INDEXED_GEOMETRY_ARRAY_ALLOW_COORDINATE_INDEX_READ = 9;
  
  static final int INDEXED_GEOMETRY_ARRAY_ALLOW_COORDINATE_INDEX_WRITE = 10;
  
  static final int INDEXED_GEOMETRY_ARRAY_ALLOW_COLOR_INDEX_READ = 11;
  
  static final int INDEXED_GEOMETRY_ARRAY_ALLOW_COLOR_INDEX_WRITE = 12;
  
  static final int INDEXED_GEOMETRY_ARRAY_ALLOW_NORMAL_INDEX_READ = 13;
  
  static final int INDEXED_GEOMETRY_ARRAY_ALLOW_NORMAL_INDEX_WRITE = 14;
  
  static final int INDEXED_GEOMETRY_ARRAY_ALLOW_TEXCOORD_INDEX_READ = 15;
  
  static final int INDEXED_GEOMETRY_ARRAY_ALLOW_TEXCOORD_INDEX_WRITE = 16;
  
  static final int GEOMETRY_ARRAY_ALLOW_FORMAT_READ = 17;
  
  static final int J3D_1_2_GEOMETRY_ARRAY_ALLOW_REF_DATA_READ = 18;
  
  static final int GEOMETRY_ARRAY_ALLOW_REF_DATA_WRITE = 19;
  
  static final int GEOMETRY_ARRAY_ALLOW_COUNT_WRITE = 20;
  
  static final int GEOMETRY_ARRAY_ALLOW_REF_DATA_READ = 21;
  
  static final int GEOMETRY_ARRAY_ALLOW_VERTEX_ATTR_READ = 22;
  
  static final int GEOMETRY_ARRAY_ALLOW_VERTEX_ATTR_WRITE = 23;
  
  static final int INDEXED_GEOMETRY_ARRAY_ALLOW_VERTEX_ATTR_INDEX_READ = 24;
  
  static final int INDEXED_GEOMETRY_ARRAY_ALLOW_VERTEX_ATTR_INDEX_WRITE = 25;
  
  static final int COMPRESSED_GEOMETRY_ALLOW_COUNT_READ = 0;
  
  static final int COMPRESSED_GEOMETRY_ALLOW_HEADER_READ = 1;
  
  static final int COMPRESSED_GEOMETRY_ALLOW_GEOMETRY_READ = 2;
  
  static final int COMPRESSED_GEOMETRY_ALLOW_REF_DATA_READ = 3;
  
  static final int RASTER_ALLOW_POSITION_READ = 0;
  
  static final int RASTER_ALLOW_POSITION_WRITE = 1;
  
  static final int RASTER_ALLOW_OFFSET_READ = 2;
  
  static final int RASTER_ALLOW_OFFSET_WRITE = 3;
  
  static final int RASTER_ALLOW_IMAGE_READ = 4;
  
  static final int RASTER_ALLOW_IMAGE_WRITE = 5;
  
  static final int RASTER_ALLOW_DEPTH_COMPONENT_READ = 6;
  
  static final int RASTER_ALLOW_DEPTH_COMPONENT_WRITE = 7;
  
  static final int RASTER_ALLOW_SIZE_READ = 8;
  
  static final int RASTER_ALLOW_SIZE_WRITE = 9;
  
  static final int RASTER_ALLOW_TYPE_READ = 10;
  
  static final int RASTER_ALLOW_CLIP_MODE_READ = 11;
  
  static final int RASTER_ALLOW_CLIP_MODE_WRITE = 12;
  
  static final int TEXT3D_ALLOW_FONT3D_READ = 0;
  
  static final int TEXT3D_ALLOW_FONT3D_WRITE = 1;
  
  static final int TEXT3D_ALLOW_STRING_READ = 2;
  
  static final int TEXT3D_ALLOW_STRING_WRITE = 3;
  
  static final int TEXT3D_ALLOW_POSITION_READ = 4;
  
  static final int TEXT3D_ALLOW_POSITION_WRITE = 5;
  
  static final int TEXT3D_ALLOW_ALIGNMENT_READ = 6;
  
  static final int TEXT3D_ALLOW_ALIGNMENT_WRITE = 7;
  
  static final int TEXT3D_ALLOW_PATH_READ = 8;
  
  static final int TEXT3D_ALLOW_PATH_WRITE = 9;
  
  static final int TEXT3D_ALLOW_CHARACTER_SPACING_READ = 10;
  
  static final int TEXT3D_ALLOW_CHARACTER_SPACING_WRITE = 11;
  
  static final int TEXT3D_ALLOW_BOUNDING_BOX_READ = 12;
  
  static final int GEOMETRY_ALLOW_INTERSECT = 18;
}


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\javax\media\j3d\CapabilityBits.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */