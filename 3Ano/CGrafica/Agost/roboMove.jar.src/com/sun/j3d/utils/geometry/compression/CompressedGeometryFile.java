/*      */ package com.sun.j3d.utils.geometry.compression;
/*      */ 
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.io.RandomAccessFile;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CompressedGeometryFile
/*      */ {
/*      */   private static final boolean print = false;
/*      */   private static final boolean benchmark = false;
/*      */   static final int MAGIC_NUMBER = -1159857484;
/*      */   static final int MAGIC_NUMBER_OFFSET = 0;
/*      */   static final int MAJOR_VERSION_OFFSET = 4;
/*      */   static final int MINOR_VERSION_OFFSET = 8;
/*      */   static final int MINOR_MINOR_VERSION_OFFSET = 12;
/*      */   static final int OBJECT_COUNT_OFFSET = 16;
/*      */   static final int DIRECTORY_OFFSET_OFFSET = 24;
/*      */   static final int HEADER_SIZE = 32;
/*      */   static final int OBJECT_SIZE_OFFSET = 0;
/*      */   static final int GEOM_DATA_OFFSET = 4;
/*      */   static final int TYPE_MASK = 3;
/*      */   static final int NORMAL_PRESENT_MASK = 4;
/*      */   static final int COLOR_PRESENT_MASK = 8;
/*      */   static final int ALPHA_PRESENT_MASK = 16;
/*      */   static final int TYPE_POINT = 1;
/*      */   static final int TYPE_LINE = 2;
/*      */   static final int TYPE_TRIANGLE = 3;
/*      */   static final int BLOCK_HEADER_SIZE = 8;
/*      */   String fileName;
/*      */   int majorVersionNumber;
/*      */   int minorVersionNumber;
/*      */   int minorMinorVersionNumber;
/*      */   int objectCount;
/*      */   int objectIndex;
/*      */   RandomAccessFile cgFile;
/*      */   int magicNumber;
/*      */   byte[] cgBuffer;
/*      */   int geomSize;
/*      */   int geomStart;
/*      */   int geomDataType;
/*      */   long[] directory;
/*      */   long directoryOffset;
/*      */   int[] objectSizes;
/*      */   int bufferObjectStart;
/*      */   int bufferObjectCount;
/*      */   int bufferNextObjectCount;
/*      */   int bufferNextObjectOffset;
/*      */   CompressedGeometryData.Header cgh;
/*      */   boolean fileUpdate;
/*      */   
/*  229 */   public CompressedGeometryFile(String paramString) throws IOException { this(paramString, false); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CompressedGeometryFile(String paramString, boolean paramBoolean) throws IOException {
/*      */     this.fileName = null;
/*      */     this.objectIndex = 0;
/*      */     this.cgFile = null;
/*      */     this.fileUpdate = false;
/*  248 */     open(paramString, paramBoolean);
/*      */ 
/*      */     
/*  251 */     this.fileName = new String(paramString);
/*      */ 
/*      */     
/*  254 */     initialize();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CompressedGeometryFile(RandomAccessFile paramRandomAccessFile) throws IOException {
/*      */     this.fileName = null;
/*      */     this.objectIndex = 0;
/*      */     this.cgFile = null;
/*      */     this.fileUpdate = false;
/*  268 */     this.cgFile = paramRandomAccessFile;
/*      */ 
/*      */     
/*  271 */     initialize();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() throws IOException {
/*  286 */     this.cgFile.setLength(0L);
/*      */ 
/*      */     
/*  289 */     initialize();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  300 */   public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  310 */   public int getMajorVersionNumber() { return this.majorVersionNumber; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  320 */   public int getMinorVersionNumber() { return this.minorVersionNumber; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  330 */   public int getMinorMinorVersionNumber() { return this.minorMinorVersionNumber; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  339 */   public int getObjectCount() { return this.objectCount; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentIndex() {
/*  351 */     if (this.objectIndex == this.objectCount) {
/*  352 */       return -1;
/*      */     }
/*  354 */     return this.objectIndex;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  371 */   public CompressedGeometryData readNext() throws IOException { return readNext(this.cgBuffer.length); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CompressedGeometryData[] read() throws IOException {
/*  383 */     long l = 0L;
/*  384 */     CompressedGeometryData[] arrayOfCompressedGeometryData = new CompressedGeometryData[this.objectCount];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  389 */     this.objectIndex = 0;
/*  390 */     setFilePointer(this.directory[0]);
/*  391 */     this.bufferNextObjectCount = 0;
/*      */     
/*  393 */     for (byte b = 0; b < this.objectCount; b++) {
/*  394 */       arrayOfCompressedGeometryData[b] = readNext(this.cgBuffer.length);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  404 */     return arrayOfCompressedGeometryData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CompressedGeometryData read(int paramInt) throws IOException {
/*  420 */     this.objectIndex = paramInt;
/*      */     
/*  422 */     if (this.objectIndex < 0) {
/*  423 */       throw new IndexOutOfBoundsException("\nobject index must be >= 0");
/*      */     }
/*      */     
/*  426 */     if (this.objectIndex >= this.objectCount) {
/*  427 */       throw new IndexOutOfBoundsException("\nobject index must be < " + this.objectCount);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  432 */     if (this.objectIndex >= this.bufferObjectStart && this.objectIndex < this.bufferObjectStart + this.bufferObjectCount) {
/*      */ 
/*      */ 
/*      */       
/*  436 */       this.bufferNextObjectOffset = (int)(this.directory[this.objectIndex] - this.directory[this.bufferObjectStart]);
/*      */ 
/*      */       
/*  439 */       this.bufferNextObjectCount = this.bufferObjectCount - this.objectIndex - this.bufferObjectStart;
/*      */ 
/*      */       
/*  442 */       return readNext();
/*      */     } 
/*      */ 
/*      */     
/*  446 */     setFilePointer(this.directory[this.objectIndex]);
/*      */ 
/*      */ 
/*      */     
/*  450 */     this.bufferNextObjectCount = 0;
/*  451 */     return readNext(this.objectSizes[this.objectIndex]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void write(CompressedGeometryData paramCompressedGeometryData) throws IOException {
/*  468 */     CompressedGeometryData.Header header = new CompressedGeometryData.Header();
/*  469 */     paramCompressedGeometryData.getCompressedGeometryHeader(header);
/*      */ 
/*      */     
/*  472 */     if (header.size + 8 > this.cgBuffer.length) {
/*  473 */       this.cgBuffer = new byte[header.size + 8];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  479 */     paramCompressedGeometryData.getCompressedGeometry(this.cgBuffer);
/*  480 */     write(header, this.cgBuffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void write(CompressedGeometryData.Header paramHeader, byte[] paramArrayOfByte) throws IOException {
/*  500 */     if (paramHeader.size + 8 > this.cgBuffer.length) {
/*  501 */       this.cgBuffer = new byte[paramHeader.size + 8];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  509 */     if (paramHeader.majorVersionNumber > this.majorVersionNumber || (paramHeader.majorVersionNumber == this.majorVersionNumber && paramHeader.minorVersionNumber > this.minorVersionNumber) || (paramHeader.majorVersionNumber == this.majorVersionNumber && paramHeader.minorVersionNumber == this.minorVersionNumber && paramHeader.minorMinorVersionNumber > this.minorMinorVersionNumber)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  518 */       this.majorVersionNumber = paramHeader.majorVersionNumber;
/*  519 */       this.minorVersionNumber = paramHeader.minorVersionNumber;
/*  520 */       this.minorMinorVersionNumber = paramHeader.minorMinorVersionNumber;
/*      */       
/*  522 */       this.cgh.majorVersionNumber = paramHeader.majorVersionNumber;
/*  523 */       this.cgh.minorVersionNumber = paramHeader.minorVersionNumber;
/*  524 */       this.cgh.minorMinorVersionNumber = paramHeader.minorMinorVersionNumber;
/*      */     } 
/*      */ 
/*      */     
/*  528 */     byte b = 0;
/*      */     
/*  530 */     switch (paramHeader.bufferType) {
/*      */       case 0:
/*  532 */         b = 1;
/*      */         break;
/*      */       case 1:
/*  535 */         b = 2;
/*      */         break;
/*      */       case 2:
/*  538 */         b = 3;
/*      */         break;
/*      */     } 
/*      */     
/*  542 */     if ((paramHeader.bufferDataPresent & true) != 0)
/*      */     {
/*  544 */       b |= 0x4;
/*      */     }
/*  546 */     if ((paramHeader.bufferDataPresent & 0x2) != 0)
/*      */     {
/*  548 */       b |= 0x8;
/*      */     }
/*  550 */     if ((paramHeader.bufferDataPresent & 0x4) != 0)
/*      */     {
/*  552 */       b |= 0x10;
/*      */     }
/*      */     
/*  555 */     if (this.objectCount == this.directory.length) {
/*  556 */       long[] arrayOfLong = new long[2 * this.objectCount];
/*  557 */       int[] arrayOfInt = new int[2 * this.objectCount];
/*      */       
/*  559 */       System.arraycopy(this.directory, 0, arrayOfLong, 0, this.objectCount);
/*      */       
/*  561 */       System.arraycopy(this.objectSizes, 0, arrayOfInt, 0, this.objectCount);
/*      */ 
/*      */       
/*  564 */       this.directory = arrayOfLong;
/*  565 */       this.objectSizes = arrayOfInt;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  573 */     this.directory[this.objectCount] = this.directoryOffset;
/*  574 */     this.objectSizes[this.objectCount] = paramHeader.size + 8;
/*  575 */     this.objectCount++;
/*      */ 
/*      */     
/*  578 */     setFilePointer(this.directoryOffset);
/*  579 */     this.cgFile.writeInt(paramHeader.size);
/*  580 */     this.cgFile.writeInt(b);
/*  581 */     this.cgFile.write(paramArrayOfByte, 0, paramHeader.size);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  588 */     this.directoryOffset += (paramHeader.size + 8);
/*      */ 
/*      */     
/*  591 */     this.objectIndex = this.objectCount;
/*      */ 
/*      */     
/*  594 */     this.fileUpdate = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() throws IOException {
/*  604 */     if (this.cgFile != null) {
/*      */       try {
/*  606 */         if (this.fileUpdate) {
/*  607 */           writeFileDirectory();
/*  608 */           writeFileHeader();
/*      */         } 
/*  610 */         this.cgFile.close();
/*      */       }
/*  612 */       catch (IOException iOException) {
/*      */         
/*  614 */         System.out.println("\nException: " + iOException.getMessage());
/*  615 */         System.out.println("failed to close " + this.fileName);
/*      */       } 
/*      */     }
/*  618 */     this.cgFile = null;
/*  619 */     this.cgBuffer = null;
/*  620 */     this.directory = null;
/*  621 */     this.objectSizes = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void open(String paramString, boolean paramBoolean) throws IOException {
/*      */     String str;
/*  632 */     this.cgFile = null;
/*      */ 
/*      */     
/*  635 */     if (paramBoolean) {
/*  636 */       str = "rw";
/*      */     } else {
/*  638 */       str = "r";
/*      */     } 
/*      */     try {
/*  641 */       this.cgFile = new RandomAccessFile(paramString, str);
/*      */ 
/*      */     
/*      */     }
/*  645 */     catch (FileNotFoundException fileNotFoundException) {
/*      */       
/*  647 */       throw new FileNotFoundException(fileNotFoundException.getMessage() + "\n" + paramString + ": open mode " + str + " failed");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setFilePointer(long paramLong) throws IOException {
/*  656 */     this.cgFile.seek(paramLong);
/*      */ 
/*      */     
/*  659 */     this.bufferNextObjectCount = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initialize() throws IOException {
/*  667 */     int i = 0;
/*      */     
/*  669 */     if (this.cgFile.length() == 0L) {
/*      */       
/*  671 */       this.objectCount = 0;
/*  672 */       this.cgBuffer = new byte[32768];
/*  673 */       this.directory = new long[16];
/*  674 */       this.objectSizes = new int[this.directory.length];
/*      */ 
/*      */       
/*  677 */       this.magicNumber = -1159857484;
/*  678 */       this.majorVersionNumber = 1;
/*  679 */       this.minorVersionNumber = 0;
/*  680 */       this.minorMinorVersionNumber = 0;
/*  681 */       this.directoryOffset = 32L;
/*      */ 
/*      */       
/*  684 */       writeFileHeader();
/*      */     }
/*      */     else {
/*      */       
/*  688 */       readFileHeader();
/*      */ 
/*      */       
/*  691 */       if (this.magicNumber != -1159857484) {
/*  692 */         close();
/*  693 */         throw new IllegalArgumentException("\n" + this.fileName + " is not a compressed geometry file");
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  698 */       this.directory = new long[this.objectCount];
/*  699 */       readDirectory(this.directoryOffset, this.directory);
/*      */       
/*  701 */       this.objectSizes = new int[this.objectCount];
/*  702 */       for (byte b = 0; b < this.objectCount - 1; b++) {
/*  703 */         this.objectSizes[b] = (int)(this.directory[b + true] - this.directory[b]);
/*  704 */         if (this.objectSizes[b] > i) i = this.objectSizes[b];
/*      */       
/*      */       } 
/*  707 */       if (this.objectCount > 0) {
/*  708 */         this.objectSizes[this.objectCount - 1] = (int)(this.directoryOffset - this.directory[this.objectCount - 1]);
/*      */ 
/*      */         
/*  711 */         if (this.objectSizes[this.objectCount - 1] > i) {
/*  712 */           i = this.objectSizes[this.objectCount - 1];
/*      */         }
/*      */       } 
/*      */       
/*  716 */       this.cgBuffer = new byte[i];
/*      */ 
/*      */       
/*  719 */       setFilePointer(32L);
/*      */     } 
/*      */ 
/*      */     
/*  723 */     this.cgh = new CompressedGeometryData.Header();
/*  724 */     this.cgh.majorVersionNumber = this.majorVersionNumber;
/*  725 */     this.cgh.minorVersionNumber = this.minorVersionNumber;
/*  726 */     this.cgh.minorMinorVersionNumber = this.minorMinorVersionNumber;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void readFileHeader() throws IOException {
/*  743 */     byte[] arrayOfByte = new byte[32];
/*      */     
/*      */     try {
/*  746 */       setFilePointer(0L);
/*  747 */       if (this.cgFile.read(arrayOfByte) != 32) {
/*  748 */         close();
/*  749 */         throw new IOException("failed header read");
/*      */       }
/*      */     
/*  752 */     } catch (IOException iOException) {
/*  753 */       if (this.cgFile != null) {
/*  754 */         close();
/*      */       }
/*  756 */       throw iOException;
/*      */     } 
/*      */     
/*  759 */     this.magicNumber = (arrayOfByte[0] & 0xFF) << 24 | (arrayOfByte[1] & 0xFF) << 16 | (arrayOfByte[2] & 0xFF) << 8 | arrayOfByte[3] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  765 */     this.majorVersionNumber = (arrayOfByte[4] & 0xFF) << 24 | (arrayOfByte[5] & 0xFF) << 16 | (arrayOfByte[6] & 0xFF) << 8 | arrayOfByte[7] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  771 */     this.minorVersionNumber = (arrayOfByte[8] & 0xFF) << 24 | (arrayOfByte[9] & 0xFF) << 16 | (arrayOfByte[10] & 0xFF) << 8 | arrayOfByte[11] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  777 */     this.minorMinorVersionNumber = (arrayOfByte[12] & 0xFF) << 24 | (arrayOfByte[13] & 0xFF) << 16 | (arrayOfByte[14] & 0xFF) << 8 | arrayOfByte[15] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  783 */     this.objectCount = (arrayOfByte[16] & 0xFF) << 24 | (arrayOfByte[17] & 0xFF) << 16 | (arrayOfByte[18] & 0xFF) << 8 | arrayOfByte[19] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  789 */     this.directoryOffset = (arrayOfByte[24] & 0xFF) << 56 | (arrayOfByte[25] & 0xFF) << 48 | (arrayOfByte[26] & 0xFF) << 40 | (arrayOfByte[27] & 0xFF) << 32 | (arrayOfByte[28] & 0xFF) << 24 | (arrayOfByte[29] & 0xFF) << 16 | (arrayOfByte[30] & 0xFF) << 8 | (arrayOfByte[31] & 0xFF);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void writeFileHeader() throws IOException {
/*  804 */     setFilePointer(0L);
/*      */     try {
/*  806 */       this.cgFile.writeInt(-1159857484);
/*  807 */       this.cgFile.writeInt(this.majorVersionNumber);
/*  808 */       this.cgFile.writeInt(this.minorVersionNumber);
/*  809 */       this.cgFile.writeInt(this.minorMinorVersionNumber);
/*  810 */       this.cgFile.writeInt(this.objectCount);
/*  811 */       this.cgFile.writeInt(0);
/*  812 */       this.cgFile.writeLong(this.directoryOffset);
/*      */ 
/*      */     
/*      */     }
/*  816 */     catch (IOException iOException) {
/*  817 */       throw new IOException(iOException.getMessage() + "\ncould not write file header for " + this.fileName);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void readDirectory(long paramLong, long[] paramArrayOfLong) throws IOException {
/*  829 */     byte[] arrayOfByte = new byte[paramArrayOfLong.length * 8];
/*  830 */     setFilePointer(paramLong);
/*      */     
/*      */     try {
/*  833 */       this.cgFile.read(arrayOfByte);
/*      */ 
/*      */     
/*      */     }
/*  837 */     catch (IOException iOException) {
/*  838 */       throw new IOException(iOException.getMessage() + "\nfailed to read " + arrayOfByte.length + " byte directory, offset " + paramLong + " in file " + this.fileName);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  844 */     for (byte b = 0; b < paramArrayOfLong.length; b++) {
/*  845 */       paramArrayOfLong[b] = (arrayOfByte[b * 8 + 0] & 0xFF) << 56 | (arrayOfByte[b * 8 + 1] & 0xFF) << 48 | (arrayOfByte[b * 8 + 2] & 0xFF) << 40 | (arrayOfByte[b * 8 + 3] & 0xFF) << 32 | (arrayOfByte[b * 8 + 4] & 0xFF) << 24 | (arrayOfByte[b * 8 + 5] & 0xFF) << 16 | (arrayOfByte[b * 8 + 6] & 0xFF) << 8 | (arrayOfByte[b * 8 + 7] & 0xFF);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void writeFileDirectory() throws IOException {
/*  861 */     setFilePointer(this.directoryOffset);
/*      */     
/*  863 */     int i = (int)(this.directoryOffset % 8L);
/*  864 */     if (i != 0) {
/*      */       
/*  866 */       byte[] arrayOfByte = new byte[8 - i];
/*      */       
/*      */       try {
/*  869 */         this.cgFile.write(arrayOfByte);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  874 */       catch (IOException iOException) {
/*  875 */         throw new IOException(iOException.getMessage() + "\ncould not write " + i + " bytes to long word align directory for " + this.fileName);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  880 */       this.directoryOffset += (8 - i);
/*      */     } 
/*      */     
/*      */     try {
/*  884 */       for (byte b = 0; b < this.objectCount; b++) {
/*  885 */         this.cgFile.writeLong(this.directory[b]);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  890 */     catch (IOException iOException) {
/*  891 */       throw new IOException(iOException.getMessage() + "\ncould not write directory for " + this.fileName);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   CompressedGeometryData readNext(int paramInt) throws IOException {
/*  903 */     if (this.objectIndex == this.objectCount) {
/*  904 */       return null;
/*      */     }
/*  906 */     if (this.bufferNextObjectCount == 0) {
/*      */       
/*  908 */       int i = 0;
/*  909 */       this.bufferObjectCount = 0;
/*      */ 
/*      */       
/*  912 */       for (j = this.objectIndex; j < this.objectCount && 
/*  913 */         i + this.objectSizes[j] <= paramInt; j++) {
/*  914 */         i += this.objectSizes[j];
/*  915 */         this.bufferObjectCount++;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  920 */         j = this.cgFile.read(this.cgBuffer, 0, i);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  925 */       catch (IOException j) {
/*  926 */         IOException iOException; throw new IOException(iOException.getMessage() + "\nfailed to read " + i + " bytes, object " + this.objectIndex + " in file " + this.fileName);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  933 */       this.bufferObjectStart = this.objectIndex;
/*  934 */       this.bufferNextObjectCount = this.bufferObjectCount;
/*  935 */       this.bufferNextObjectOffset = 0;
/*      */     } 
/*      */ 
/*      */     
/*  939 */     this.geomSize = (this.cgBuffer[this.bufferNextObjectOffset + 0 + 0] & 0xFF) << 24 | (this.cgBuffer[this.bufferNextObjectOffset + 0 + 1] & 0xFF) << 16 | (this.cgBuffer[this.bufferNextObjectOffset + 0 + 2] & 0xFF) << 8 | this.cgBuffer[this.bufferNextObjectOffset + 0 + 3] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  945 */     this.geomDataType = (this.cgBuffer[this.bufferNextObjectOffset + 4 + 0] & 0xFF) << 24 | (this.cgBuffer[this.bufferNextObjectOffset + 4 + 1] & 0xFF) << 16 | (this.cgBuffer[this.bufferNextObjectOffset + 4 + 2] & 0xFF) << 8 | this.cgBuffer[this.bufferNextObjectOffset + 4 + 3] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  952 */     this.geomStart = this.bufferNextObjectOffset + 8;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  964 */     this.bufferNextObjectOffset += this.objectSizes[this.objectIndex];
/*  965 */     this.bufferNextObjectCount--;
/*  966 */     this.objectIndex++;
/*      */     
/*  968 */     return newCG(this.geomSize, this.geomStart, this.geomDataType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   CompressedGeometryData newCG(int paramInt1, int paramInt2, int paramInt3) {
/*  978 */     this.cgh.size = paramInt1;
/*  979 */     this.cgh.start = paramInt2;
/*      */     
/*  981 */     if ((paramInt3 & 0x3) == 1) {
/*  982 */       this.cgh.bufferType = 0;
/*  983 */     } else if ((paramInt3 & 0x3) == 2) {
/*  984 */       this.cgh.bufferType = 1;
/*  985 */     } else if ((paramInt3 & 0x3) == 3) {
/*  986 */       this.cgh.bufferType = 2;
/*      */     } 
/*  988 */     this.cgh.bufferDataPresent = 0;
/*      */     
/*  990 */     if ((paramInt3 & 0x4) != 0) {
/*  991 */       this.cgh.bufferDataPresent |= 0x1;
/*      */     }
/*      */     
/*  994 */     if ((paramInt3 & 0x8) != 0) {
/*  995 */       this.cgh.bufferDataPresent |= 0x2;
/*      */     }
/*      */     
/*  998 */     if ((paramInt3 & 0x10) != 0) {
/*  999 */       this.cgh.bufferDataPresent |= 0x4;
/*      */     }
/*      */     
/* 1002 */     return new CompressedGeometryData(this.cgh, this.cgBuffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1009 */   protected void finalize() throws IOException { close(); }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\roboMove.jar!\com\sun\j3\\utils\geometry\compression\CompressedGeometryFile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */