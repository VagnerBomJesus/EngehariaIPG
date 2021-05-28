/*     */ package com.sun.j3d.utils.scenegraph.io.state.javax.media.j3d;
/*     */ 
/*     */ import com.sun.image.codec.jpeg.JPEGCodec;
/*     */ import com.sun.image.codec.jpeg.JPEGImageDecoder;
/*     */ import com.sun.image.codec.jpeg.JPEGImageEncoder;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.Controller;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SGIORuntimeException;
/*     */ import com.sun.j3d.utils.scenegraph.io.retained.SymbolTableData;
/*     */ import java.awt.Point;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.DirectColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import javax.media.j3d.ImageComponent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ImageComponentState
/*     */   extends NodeComponentState
/*     */ {
/*     */   protected int format;
/*     */   protected int height;
/*     */   protected int width;
/*     */   protected boolean byReference;
/*     */   protected boolean yUp;
/*     */   private static final int DIRECT_COLOR_MODEL = 1;
/*     */   private static final int SINGLE_PIXEL_PACKED_SAMPLE_MODEL = 1;
/*     */   private static final int DATA_BUFFER_INT = 1;
/*     */   public static final byte NO_COMPRESSION = 0;
/*     */   public static final byte GZIP_COMPRESSION = 1;
/*     */   public static final byte JPEG_COMPRESSION = 2;
/*     */   
/* 106 */   public ImageComponentState(SymbolTableData paramSymbolTableData, Controller paramController) { super(paramSymbolTableData, paramController); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeConstructorParams(DataOutput paramDataOutput) throws IOException {
/* 112 */     super.writeConstructorParams(paramDataOutput);
/* 113 */     paramDataOutput.writeInt(((ImageComponent)this.node).getFormat());
/* 114 */     paramDataOutput.writeInt(((ImageComponent)this.node).getHeight());
/* 115 */     paramDataOutput.writeInt(((ImageComponent)this.node).getWidth());
/* 116 */     paramDataOutput.writeBoolean(((ImageComponent)this.node).isByReference());
/* 117 */     paramDataOutput.writeBoolean(((ImageComponent)this.node).isYUp());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void readConstructorParams(DataInput paramDataInput) throws IOException {
/* 122 */     super.readConstructorParams(paramDataInput);
/* 123 */     this.format = paramDataInput.readInt();
/* 124 */     this.height = paramDataInput.readInt();
/* 125 */     this.width = paramDataInput.readInt();
/* 126 */     this.byReference = paramDataInput.readBoolean();
/* 127 */     this.yUp = paramDataInput.readBoolean();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeBufferedImage(DataOutput paramDataOutput, BufferedImage paramBufferedImage) throws IOException {
/* 133 */     int i = this.control.getImageCompression();
/*     */     
/* 135 */     paramDataOutput.writeByte(i);
/*     */     
/* 137 */     if (i == 0) {
/* 138 */       writeBufferedImageNoCompression(paramDataOutput, paramBufferedImage);
/* 139 */     } else if (i == 1) {
/* 140 */       writeBufferedImageGzipCompression(paramDataOutput, paramBufferedImage);
/* 141 */     } else if (i == 2) {
/* 142 */       writeBufferedImageJpegCompression(paramDataOutput, paramBufferedImage);
/*     */     } 
/*     */   }
/*     */   private void writeBufferedImageNoCompression(DataOutput paramDataOutput, BufferedImage paramBufferedImage) throws IOException {
/* 146 */     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 147 */     DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
/* 148 */     ColorModel colorModel = paramBufferedImage.getColorModel();
/*     */     
/* 150 */     if (colorModel instanceof ComponentColorModel) {
/* 151 */       byte b; ComponentColorModel componentColorModel = (ComponentColorModel)colorModel;
/* 152 */       int i = componentColorModel.getNumComponents();
/*     */       
/* 154 */       switch (i) {
/*     */         case 3:
/* 156 */           b = 1;
/*     */           break;
/*     */         case 4:
/* 159 */           b = 2;
/*     */           break;
/*     */         default:
/* 162 */           throw new SGIORuntimeException("Unsupported ColorModel " + colorModel.getClass().getName());
/*     */       } 
/*     */ 
/*     */       
/* 166 */       BufferedImage bufferedImage = new BufferedImage(paramBufferedImage.getWidth(), paramBufferedImage.getHeight(), b);
/* 167 */       WritableRaster writableRaster1 = bufferedImage.getRaster();
/* 168 */       WritableRaster writableRaster2 = paramBufferedImage.getRaster();
/* 169 */       writableRaster1.setRect(writableRaster2);
/* 170 */       paramBufferedImage = bufferedImage;
/*     */     } 
/*     */     
/* 173 */     writeColorModel(dataOutputStream, paramBufferedImage.getColorModel());
/* 174 */     writeWritableRaster(dataOutputStream, paramBufferedImage.getRaster());
/* 175 */     dataOutputStream.writeBoolean(paramBufferedImage.isAlphaPremultiplied());
/*     */     
/* 177 */     dataOutputStream.close();
/*     */     
/* 179 */     byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
/* 180 */     paramDataOutput.writeInt(arrayOfByte.length);
/* 181 */     paramDataOutput.write(arrayOfByte);
/*     */   }
/*     */   
/*     */   private void writeBufferedImageGzipCompression(DataOutput paramDataOutput, BufferedImage paramBufferedImage) throws IOException {
/* 185 */     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 186 */     GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
/* 187 */     DataOutputStream dataOutputStream = new DataOutputStream(gZIPOutputStream);
/*     */     
/* 189 */     writeColorModel(dataOutputStream, paramBufferedImage.getColorModel());
/* 190 */     writeWritableRaster(dataOutputStream, paramBufferedImage.getRaster());
/* 191 */     dataOutputStream.writeBoolean(paramBufferedImage.isAlphaPremultiplied());
/*     */     
/* 193 */     dataOutputStream.flush();
/* 194 */     gZIPOutputStream.finish();
/*     */ 
/*     */     
/* 197 */     byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
/*     */     
/* 199 */     paramDataOutput.writeInt(arrayOfByte.length);
/* 200 */     paramDataOutput.write(arrayOfByte);
/* 201 */     dataOutputStream.close();
/*     */   }
/*     */   
/*     */   private void writeBufferedImageJpegCompression(DataOutput paramDataOutput, BufferedImage paramBufferedImage) throws IOException {
/* 205 */     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
/* 206 */     JPEGImageEncoder jPEGImageEncoder = JPEGCodec.createJPEGEncoder(byteArrayOutputStream);
/*     */     
/* 208 */     jPEGImageEncoder.encode(paramBufferedImage);
/* 209 */     byteArrayOutputStream.close();
/*     */     
/* 211 */     byte[] arrayOfByte = byteArrayOutputStream.toByteArray();
/* 212 */     paramDataOutput.writeInt(arrayOfByte.length);
/* 213 */     paramDataOutput.write(arrayOfByte);
/*     */   }
/*     */   
/*     */   protected BufferedImage readBufferedImage(DataInput paramDataInput) throws IOException {
/* 217 */     byte b = paramDataInput.readByte();
/*     */     
/* 219 */     if (b == 0)
/* 220 */       return readBufferedImageNoCompression(paramDataInput); 
/* 221 */     if (b == 1)
/* 222 */       return readBufferedImageGzipCompression(paramDataInput); 
/* 223 */     if (b == 2)
/* 224 */       return readBufferedImageJpegCompression(paramDataInput); 
/* 225 */     throw new SGIORuntimeException("Unknown Image Compression");
/*     */   }
/*     */   
/*     */   private BufferedImage readBufferedImageNoCompression(DataInput paramDataInput) throws IOException {
/* 229 */     int i = paramDataInput.readInt();
/* 230 */     byte[] arrayOfByte = new byte[i];
/* 231 */     paramDataInput.readFully(arrayOfByte);
/* 232 */     ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
/* 233 */     DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
/*     */     
/* 235 */     ColorModel colorModel = readColorModel(dataInputStream);
/* 236 */     WritableRaster writableRaster = readWritableRaster(dataInputStream);
/* 237 */     boolean bool = dataInputStream.readBoolean();
/* 238 */     dataInputStream.close();
/*     */     
/* 240 */     return new BufferedImage(colorModel, writableRaster, bool, null);
/*     */   }
/*     */   
/*     */   private BufferedImage readBufferedImageGzipCompression(DataInput paramDataInput) throws IOException {
/* 244 */     int i = paramDataInput.readInt();
/* 245 */     byte[] arrayOfByte = new byte[i];
/* 246 */     paramDataInput.readFully(arrayOfByte);
/* 247 */     ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
/* 248 */     GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
/* 249 */     DataInputStream dataInputStream = new DataInputStream(gZIPInputStream);
/*     */     
/* 251 */     ColorModel colorModel = readColorModel(dataInputStream);
/* 252 */     WritableRaster writableRaster = readWritableRaster(dataInputStream);
/* 253 */     boolean bool = dataInputStream.readBoolean();
/* 254 */     dataInputStream.close();
/*     */     
/* 256 */     return new BufferedImage(colorModel, writableRaster, bool, null);
/*     */   }
/*     */   
/*     */   private BufferedImage readBufferedImageJpegCompression(DataInput paramDataInput) throws IOException {
/* 260 */     int i = paramDataInput.readInt();
/* 261 */     byte[] arrayOfByte = new byte[i];
/* 262 */     paramDataInput.readFully(arrayOfByte);
/* 263 */     ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
/*     */     
/* 265 */     JPEGImageDecoder jPEGImageDecoder = JPEGCodec.createJPEGDecoder(byteArrayInputStream);
/* 266 */     byteArrayInputStream.close();
/*     */     
/* 268 */     return jPEGImageDecoder.decodeAsBufferedImage();
/*     */   }
/*     */   
/*     */   private void writeColorModel(DataOutput paramDataOutput, ColorModel paramColorModel) throws IOException {
/* 272 */     if (paramColorModel instanceof DirectColorModel) {
/* 273 */       paramDataOutput.writeInt(1);
/* 274 */       writeDirectColorModel(paramDataOutput, (DirectColorModel)paramColorModel);
/*     */     } else {
/*     */       
/* 277 */       throw new SGIORuntimeException("Unsupported ColorModel " + paramColorModel.getClass().getName());
/*     */     } 
/*     */   }
/*     */   private ColorModel readColorModel(DataInput paramDataInput) throws IOException {
/* 281 */     switch (paramDataInput.readInt()) {
/*     */       case 1:
/* 283 */         return readDirectColorModel(paramDataInput);
/*     */     } 
/*     */     
/* 286 */     throw new SGIORuntimeException("Invalid ColorModel - File corrupt");
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeDirectColorModel(DataOutput paramDataOutput, DirectColorModel paramDirectColorModel) throws IOException {
/* 291 */     paramDataOutput.writeInt(paramDirectColorModel.getPixelSize());
/* 292 */     paramDataOutput.writeInt(paramDirectColorModel.getRedMask());
/* 293 */     paramDataOutput.writeInt(paramDirectColorModel.getGreenMask());
/* 294 */     paramDataOutput.writeInt(paramDirectColorModel.getBlueMask());
/* 295 */     paramDataOutput.writeInt(paramDirectColorModel.getAlphaMask());
/*     */   }
/*     */ 
/*     */   
/* 299 */   private DirectColorModel readDirectColorModel(DataInput paramDataInput) throws IOException { return new DirectColorModel(paramDataInput.readInt(), paramDataInput.readInt(), paramDataInput.readInt(), paramDataInput.readInt(), paramDataInput.readInt()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeWritableRaster(DataOutput paramDataOutput, WritableRaster paramWritableRaster) throws IOException {
/* 307 */     writeSampleModel(paramDataOutput, paramWritableRaster.getSampleModel());
/* 308 */     writeDataBuffer(paramDataOutput, paramWritableRaster.getDataBuffer());
/* 309 */     Point point = new Point();
/*     */     
/* 311 */     paramDataOutput.writeInt(point.x);
/* 312 */     paramDataOutput.writeInt(point.y);
/*     */   }
/*     */ 
/*     */   
/* 316 */   private WritableRaster readWritableRaster(DataInput paramDataInput) throws IOException { return Raster.createWritableRaster(readSampleModel(paramDataInput), readDataBuffer(paramDataInput), new Point(paramDataInput.readInt(), paramDataInput.readInt())); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeSampleModel(DataOutput paramDataOutput, SampleModel paramSampleModel) throws IOException {
/* 322 */     if (paramSampleModel instanceof SinglePixelPackedSampleModel) {
/* 323 */       paramDataOutput.writeInt(1);
/* 324 */       writeSinglePixelPackedSampleModel(paramDataOutput, (SinglePixelPackedSampleModel)paramSampleModel);
/*     */     } else {
/* 326 */       throw new SGIORuntimeException("Unsupported SampleModel " + paramSampleModel.getClass().getName());
/*     */     } 
/*     */   }
/*     */   private SampleModel readSampleModel(DataInput paramDataInput) throws IOException {
/* 330 */     switch (paramDataInput.readInt()) {
/*     */       case 1:
/* 332 */         return readSinglePixelPackedSampleModel(paramDataInput);
/*     */     } 
/*     */     
/* 335 */     throw new SGIORuntimeException("Invalid SampleModel - file corrupt");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeSinglePixelPackedSampleModel(DataOutput paramDataOutput, SinglePixelPackedSampleModel paramSinglePixelPackedSampleModel) throws IOException {
/* 341 */     int[] arrayOfInt = paramSinglePixelPackedSampleModel.getBitMasks();
/* 342 */     paramDataOutput.writeInt(arrayOfInt.length);
/* 343 */     for (byte b = 0; b < arrayOfInt.length; b++) {
/* 344 */       paramDataOutput.writeInt(arrayOfInt[b]);
/*     */     }
/* 346 */     paramDataOutput.writeInt(paramSinglePixelPackedSampleModel.getDataType());
/* 347 */     paramDataOutput.writeInt(paramSinglePixelPackedSampleModel.getWidth());
/* 348 */     paramDataOutput.writeInt(paramSinglePixelPackedSampleModel.getHeight());
/* 349 */     paramDataOutput.writeInt(paramSinglePixelPackedSampleModel.getScanlineStride());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SinglePixelPackedSampleModel readSinglePixelPackedSampleModel(DataInput paramDataInput) throws IOException {
/* 356 */     int[] arrayOfInt = new int[paramDataInput.readInt()];
/* 357 */     for (byte b = 0; b < arrayOfInt.length; b++) {
/* 358 */       arrayOfInt[b] = paramDataInput.readInt();
/*     */     }
/* 360 */     return new SinglePixelPackedSampleModel(paramDataInput.readInt(), paramDataInput.readInt(), paramDataInput.readInt(), paramDataInput.readInt(), arrayOfInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeDataBuffer(DataOutput paramDataOutput, DataBuffer paramDataBuffer) throws IOException {
/* 368 */     if (paramDataBuffer instanceof DataBufferInt) {
/* 369 */       paramDataOutput.writeInt(1);
/* 370 */       writeDataBufferInt(paramDataOutput, (DataBufferInt)paramDataBuffer);
/*     */     } else {
/* 372 */       throw new SGIORuntimeException("Unsupported DataBuffer " + paramDataBuffer.getClass().getName());
/*     */     } 
/*     */   }
/*     */   private DataBuffer readDataBuffer(DataInput paramDataInput) throws IOException {
/* 376 */     switch (paramDataInput.readInt()) {
/*     */       case 1:
/* 378 */         return readDataBufferInt(paramDataInput);
/*     */     } 
/*     */     
/* 381 */     throw new SGIORuntimeException("Incorrect DataBuffer - file corrupt");
/*     */   }
/*     */   
/*     */   private void writeDataBufferInt(DataOutput paramDataOutput, DataBufferInt paramDataBufferInt) throws IOException {
/* 385 */     int[][] arrayOfInt = paramDataBufferInt.getBankData();
/* 386 */     paramDataOutput.writeInt(arrayOfInt.length);
/* 387 */     for (byte b = 0; b < arrayOfInt.length; b++) {
/* 388 */       paramDataOutput.writeInt(arrayOfInt[b].length);
/* 389 */       for (byte b1 = 0; b1 < arrayOfInt[b].length; b1++) {
/* 390 */         paramDataOutput.writeInt(arrayOfInt[b][b1]);
/*     */       }
/*     */     } 
/* 393 */     paramDataOutput.writeInt(paramDataBufferInt.getSize());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DataBufferInt readDataBufferInt(DataInput paramDataInput) throws IOException {
/* 400 */     int[][] arrayOfInt = new int[paramDataInput.readInt()][];
/* 401 */     for (byte b = 0; b < arrayOfInt.length; b++) {
/* 402 */       arrayOfInt[b] = new int[paramDataInput.readInt()];
/* 403 */       for (byte b1 = 0; b1 < arrayOfInt[b].length; b1++) {
/* 404 */         arrayOfInt[b][b1] = paramDataInput.readInt();
/*     */       }
/*     */     } 
/*     */     
/* 408 */     return new DataBufferInt(arrayOfInt, paramDataInput.readInt());
/*     */   }
/*     */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robotMoveExtra.jar!\com\sun\j3\\utils\scenegraph\io\state\javax\media\j3d\ImageComponentState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */