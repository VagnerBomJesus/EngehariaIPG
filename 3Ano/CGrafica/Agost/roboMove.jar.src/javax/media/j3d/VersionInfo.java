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
/*     */ class VersionInfo
/*     */ {
/*  56 */   private static final String VENDOR_DEVELOPER = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   private static final String VERSION_DEV_STRING = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final boolean isDebug = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final boolean isDevPhase = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final boolean isProduction = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final boolean useVerboseBuildTime = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String BUILD_TYPE = "fcs";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String VERSION_BUILD = "build6";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String VERSION_SUFFIX = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String BUILDTIME = "0706251412";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String SPECIFICATION_VERSION = "1.5";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String SPECIFICATION_VENDOR = "Sun Microsystems, Inc.";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String VENDOR_PRIMARY = "Sun Microsystems, Inc.";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String VERSION_BASE = "1.5.1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final boolean isExperimental = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String VERSION;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String VENDOR;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String BUILD_QUALIFIER = "@BUILD_QUALIFIER@";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String BUILDTIME_VERBOSE = "25 Jun 2007 14:12:32 PDT";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isNonEmpty(String paramString) {
/* 222 */     if (paramString == null || paramString.length() == 0) {
/* 223 */       return false;
/*     */     }
/*     */     
/* 226 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static  {
/* 232 */     boolean bool1 = "fcs".equals("patch");
/* 233 */     boolean bool2 = "fcs".equals("fcs");
/* 234 */     boolean bool3 = "fcs".equals("beta");
/* 235 */     boolean bool4 = "fcs".equals("stable");
/* 236 */     boolean bool5 = "fcs".equals("daily");
/*     */ 
/*     */ 
/*     */     
/* 240 */     String str1 = "Sun Microsystems, Inc.";
/* 241 */     if (isNonEmpty(VENDOR_DEVELOPER)) {
/* 242 */       str1 = str1 + " & " + VENDOR_DEVELOPER;
/*     */     }
/*     */     
/* 245 */     String str2 = "1.5.1";
/* 246 */     if (isNonEmpty("")) {
/* 247 */       if (bool1) {
/* 248 */         str2 = str2 + "_";
/*     */       } else {
/*     */         
/* 251 */         str2 = str2 + "-";
/*     */       } 
/* 253 */       str2 = str2 + "";
/*     */     } 
/*     */     
/* 256 */     if (bool5 && isNonEmpty("0706251412")) {
/* 257 */       str2 = str2 + "-0706251412";
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 267 */     if (bool2) {
/* 268 */       str2 = str2 + " fcs";
/*     */     }
/* 270 */     else if (bool1) {
/* 271 */       str2 = str2 + " fcs+patch";
/*     */     } 
/*     */     
/* 274 */     if (isNonEmpty("build6")) {
/* 275 */       str2 = str2 + " (build6)";
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 283 */     if (isNonEmpty(VERSION_DEV_STRING)) {
/* 284 */       str2 = str2 + " " + VERSION_DEV_STRING;
/*     */     }
/*     */     
/* 287 */     VERSION = str2;
/* 288 */     VENDOR = str1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   static String getSpecificationVersion() { return "1.5"; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 304 */   static String getSpecificationVendor() { return "Sun Microsystems, Inc."; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 313 */   static String getVersion() { return VERSION; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 321 */   static String getVendor() { return VENDOR; }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\javax\media\j3d\VersionInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */