/*      */ package com.sun.j3d.utils.compression;
/*      */ 
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.io.RandomAccessFile;
/*      */ import javax.media.j3d.CompressedGeometry;
/*      */ import javax.media.j3d.CompressedGeometryHeader;
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
/*      */   CompressedGeometryHeader cgh;
/*      */   boolean fileUpdate;
/*      */   
/*  232 */   public CompressedGeometryFile(String paramString) throws IOException { this(paramString, false); }
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
/*  251 */     open(paramString, paramBoolean);
/*      */ 
/*      */     
/*  254 */     this.fileName = new String(paramString);
/*      */ 
/*      */     
/*  257 */     initialize();
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
/*  271 */     this.cgFile = paramRandomAccessFile;
/*      */ 
/*      */     
/*  274 */     initialize();
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
/*  289 */     this.cgFile.setLength(0L);
/*      */ 
/*      */     
/*  292 */     initialize();
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
/*  303 */   public String getFileName() { return this.fileName; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  313 */   public int getMajorVersionNumber() { return this.majorVersionNumber; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  323 */   public int getMinorVersionNumber() { return this.minorVersionNumber; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  333 */   public int getMinorMinorVersionNumber() { return this.minorMinorVersionNumber; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  342 */   public int getObjectCount() { return this.objectCount; }
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
/*  354 */     if (this.objectIndex == this.objectCount) {
/*  355 */       return -1;
/*      */     }
/*  357 */     return this.objectIndex;
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
/*  373 */   public CompressedGeometry readNext() throws IOException { return readNext(this.cgBuffer.length); }
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
/*      */   public CompressedGeometry[] read() throws IOException {
/*  385 */     long l = 0L;
/*  386 */     CompressedGeometry[] arrayOfCompressedGeometry = new CompressedGeometry[this.objectCount];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  391 */     this.objectIndex = 0;
/*  392 */     setFilePointer(this.directory[0]);
/*  393 */     this.bufferNextObjectCount = 0;
/*      */     
/*  395 */     for (byte b = 0; b < this.objectCount; b++) {
/*  396 */       arrayOfCompressedGeometry[b] = readNext(this.cgBuffer.length);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  406 */     return arrayOfCompressedGeometry;
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
/*      */   public CompressedGeometry read(int paramInt) throws IOException {
/*  422 */     this.objectIndex = paramInt;
/*      */     
/*  424 */     if (this.objectIndex < 0) {
/*  425 */       throw new IndexOutOfBoundsException("\nobject index must be >= 0");
/*      */     }
/*      */     
/*  428 */     if (this.objectIndex >= this.objectCount) {
/*  429 */       throw new IndexOutOfBoundsException("\nobject index must be < " + this.objectCount);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  434 */     if (this.objectIndex >= this.bufferObjectStart && this.objectIndex < this.bufferObjectStart + this.bufferObjectCount) {
/*      */ 
/*      */ 
/*      */       
/*  438 */       this.bufferNextObjectOffset = (int)(this.directory[this.objectIndex] - this.directory[this.bufferObjectStart]);
/*      */ 
/*      */       
/*  441 */       this.bufferNextObjectCount = this.bufferObjectCount - this.objectIndex - this.bufferObjectStart;
/*      */ 
/*      */       
/*  444 */       return readNext();
/*      */     } 
/*      */ 
/*      */     
/*  448 */     setFilePointer(this.directory[this.objectIndex]);
/*      */ 
/*      */ 
/*      */     
/*  452 */     this.bufferNextObjectCount = 0;
/*  453 */     return readNext(this.objectSizes[this.objectIndex]);
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
/*      */   public void write(CompressedGeometry paramCompressedGeometry) throws IOException {
/*  470 */     CompressedGeometryHeader compressedGeometryHeader = new CompressedGeometryHeader();
/*  471 */     paramCompressedGeometry.getCompressedGeometryHeader(compressedGeometryHeader);
/*      */ 
/*      */     
/*  474 */     if (compressedGeometryHeader.size + 8 > this.cgBuffer.length) {
/*  475 */       this.cgBuffer = new byte[compressedGeometryHeader.size + 8];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  481 */     paramCompressedGeometry.getCompressedGeometry(this.cgBuffer);
/*  482 */     write(compressedGeometryHeader, this.cgBuffer);
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
/*      */   public void write(CompressedGeometryHeader paramCompressedGeometryHeader, byte[] paramArrayOfByte) throws IOException {
/*  502 */     if (paramCompressedGeometryHeader.size + 8 > this.cgBuffer.length) {
/*  503 */       this.cgBuffer = new byte[paramCompressedGeometryHeader.size + 8];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  511 */     if (paramCompressedGeometryHeader.majorVersionNumber > this.majorVersionNumber || (paramCompressedGeometryHeader.majorVersionNumber == this.majorVersionNumber && paramCompressedGeometryHeader.minorVersionNumber > this.minorVersionNumber) || (paramCompressedGeometryHeader.majorVersionNumber == this.majorVersionNumber && paramCompressedGeometryHeader.minorVersionNumber == this.minorVersionNumber && paramCompressedGeometryHeader.minorMinorVersionNumber > this.minorMinorVersionNumber)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  520 */       this.majorVersionNumber = paramCompressedGeometryHeader.majorVersionNumber;
/*  521 */       this.minorVersionNumber = paramCompressedGeometryHeader.minorVersionNumber;
/*  522 */       this.minorMinorVersionNumber = paramCompressedGeometryHeader.minorMinorVersionNumber;
/*      */       
/*  524 */       this.cgh.majorVersionNumber = paramCompressedGeometryHeader.majorVersionNumber;
/*  525 */       this.cgh.minorVersionNumber = paramCompressedGeometryHeader.minorVersionNumber;
/*  526 */       this.cgh.minorMinorVersionNumber = paramCompressedGeometryHeader.minorMinorVersionNumber;
/*      */     } 
/*      */ 
/*      */     
/*  530 */     byte b = 0;
/*      */     
/*  532 */     switch (paramCompressedGeometryHeader.bufferType) {
/*      */       case 0:
/*  534 */         b = 1;
/*      */         break;
/*      */       case 1:
/*  537 */         b = 2;
/*      */         break;
/*      */       case 2:
/*  540 */         b = 3;
/*      */         break;
/*      */     } 
/*      */     
/*  544 */     if ((paramCompressedGeometryHeader.bufferDataPresent & true) != 0)
/*      */     {
/*  546 */       b |= 0x4;
/*      */     }
/*  548 */     if ((paramCompressedGeometryHeader.bufferDataPresent & 0x2) != 0)
/*      */     {
/*  550 */       b |= 0x8;
/*      */     }
/*  552 */     if ((paramCompressedGeometryHeader.bufferDataPresent & 0x4) != 0)
/*      */     {
/*  554 */       b |= 0x10;
/*      */     }
/*      */     
/*  557 */     if (this.objectCount == this.directory.length) {
/*  558 */       long[] arrayOfLong = new long[2 * this.objectCount];
/*  559 */       int[] arrayOfInt = new int[2 * this.objectCount];
/*      */       
/*  561 */       System.arraycopy(this.directory, 0, arrayOfLong, 0, this.objectCount);
/*      */       
/*  563 */       System.arraycopy(this.objectSizes, 0, arrayOfInt, 0, this.objectCount);
/*      */ 
/*      */       
/*  566 */       this.directory = arrayOfLong;
/*  567 */       this.objectSizes = arrayOfInt;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  575 */     this.directory[this.objectCount] = this.directoryOffset;
/*  576 */     this.objectSizes[this.objectCount] = paramCompressedGeometryHeader.size + 8;
/*  577 */     this.objectCount++;
/*      */ 
/*      */     
/*  580 */     setFilePointer(this.directoryOffset);
/*  581 */     this.cgFile.writeInt(paramCompressedGeometryHeader.size);
/*  582 */     this.cgFile.writeInt(b);
/*  583 */     this.cgFile.write(paramArrayOfByte, 0, paramCompressedGeometryHeader.size);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  590 */     this.directoryOffset += (paramCompressedGeometryHeader.size + 8);
/*      */ 
/*      */     
/*  593 */     this.objectIndex = this.objectCount;
/*      */ 
/*      */     
/*  596 */     this.fileUpdate = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() throws IOException {
/*  606 */     if (this.cgFile != null) {
/*      */       try {
/*  608 */         if (this.fileUpdate) {
/*  609 */           writeFileDirectory();
/*  610 */           writeFileHeader();
/*      */         } 
/*  612 */         this.cgFile.close();
/*      */       }
/*  614 */       catch (IOException iOException) {
/*      */         
/*  616 */         System.out.println("\nException: " + iOException.getMessage());
/*  617 */         System.out.println("failed to close " + this.fileName);
/*      */       } 
/*      */     }
/*  620 */     this.cgFile = null;
/*  621 */     this.cgBuffer = null;
/*  622 */     this.directory = null;
/*  623 */     this.objectSizes = null;
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
/*  634 */     this.cgFile = null;
/*      */ 
/*      */     
/*  637 */     if (paramBoolean) {
/*  638 */       str = "rw";
/*      */     } else {
/*  640 */       str = "r";
/*      */     } 
/*      */     try {
/*  643 */       this.cgFile = new RandomAccessFile(paramString, str);
/*      */ 
/*      */     
/*      */     }
/*  647 */     catch (FileNotFoundException fileNotFoundException) {
/*      */       
/*  649 */       throw new FileNotFoundException(fileNotFoundException.getMessage() + "\n" + paramString + ": open mode " + str + " failed");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setFilePointer(long paramLong) throws IOException {
/*  658 */     this.cgFile.seek(paramLong);
/*      */ 
/*      */     
/*  661 */     this.bufferNextObjectCount = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void initialize() throws IOException {
/*  669 */     int i = 0;
/*      */     
/*  671 */     if (this.cgFile.length() == 0L) {
/*      */       
/*  673 */       this.objectCount = 0;
/*  674 */       this.cgBuffer = new byte[32768];
/*  675 */       this.directory = new long[16];
/*  676 */       this.objectSizes = new int[this.directory.length];
/*      */ 
/*      */       
/*  679 */       this.magicNumber = -1159857484;
/*  680 */       this.majorVersionNumber = 1;
/*  681 */       this.minorVersionNumber = 0;
/*  682 */       this.minorMinorVersionNumber = 0;
/*  683 */       this.directoryOffset = 32L;
/*      */ 
/*      */       
/*  686 */       writeFileHeader();
/*      */     }
/*      */     else {
/*      */       
/*  690 */       readFileHeader();
/*      */ 
/*      */       
/*  693 */       if (this.magicNumber != -1159857484) {
/*  694 */         close();
/*  695 */         throw new IllegalArgumentException("\n" + this.fileName + " is not a compressed geometry file");
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  700 */       this.directory = new long[this.objectCount];
/*  701 */       readDirectory(this.directoryOffset, this.directory);
/*      */       
/*  703 */       this.objectSizes = new int[this.objectCount];
/*  704 */       for (byte b = 0; b < this.objectCount - 1; b++) {
/*  705 */         this.objectSizes[b] = (int)(this.directory[b + true] - this.directory[b]);
/*  706 */         if (this.objectSizes[b] > i) i = this.objectSizes[b];
/*      */       
/*      */       } 
/*  709 */       if (this.objectCount > 0) {
/*  710 */         this.objectSizes[this.objectCount - 1] = (int)(this.directoryOffset - this.directory[this.objectCount - 1]);
/*      */ 
/*      */         
/*  713 */         if (this.objectSizes[this.objectCount - 1] > i) {
/*  714 */           i = this.objectSizes[this.objectCount - 1];
/*      */         }
/*      */       } 
/*      */       
/*  718 */       this.cgBuffer = new byte[i];
/*      */ 
/*      */       
/*  721 */       setFilePointer(32L);
/*      */     } 
/*      */ 
/*      */     
/*  725 */     this.cgh = new CompressedGeometryHeader();
/*  726 */     this.cgh.majorVersionNumber = this.majorVersionNumber;
/*  727 */     this.cgh.minorVersionNumber = this.minorVersionNumber;
/*  728 */     this.cgh.minorMinorVersionNumber = this.minorMinorVersionNumber;
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
/*  745 */     byte[] arrayOfByte = new byte[32];
/*      */     
/*      */     try {
/*  748 */       setFilePointer(0L);
/*  749 */       if (this.cgFile.read(arrayOfByte) != 32) {
/*  750 */         close();
/*  751 */         throw new IOException("failed header read");
/*      */       }
/*      */     
/*  754 */     } catch (IOException iOException) {
/*  755 */       if (this.cgFile != null) {
/*  756 */         close();
/*      */       }
/*  758 */       throw iOException;
/*      */     } 
/*      */     
/*  761 */     this.magicNumber = (arrayOfByte[0] & 0xFF) << 24 | (arrayOfByte[1] & 0xFF) << 16 | (arrayOfByte[2] & 0xFF) << 8 | arrayOfByte[3] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  767 */     this.majorVersionNumber = (arrayOfByte[4] & 0xFF) << 24 | (arrayOfByte[5] & 0xFF) << 16 | (arrayOfByte[6] & 0xFF) << 8 | arrayOfByte[7] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  773 */     this.minorVersionNumber = (arrayOfByte[8] & 0xFF) << 24 | (arrayOfByte[9] & 0xFF) << 16 | (arrayOfByte[10] & 0xFF) << 8 | arrayOfByte[11] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  779 */     this.minorMinorVersionNumber = (arrayOfByte[12] & 0xFF) << 24 | (arrayOfByte[13] & 0xFF) << 16 | (arrayOfByte[14] & 0xFF) << 8 | arrayOfByte[15] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  785 */     this.objectCount = (arrayOfByte[16] & 0xFF) << 24 | (arrayOfByte[17] & 0xFF) << 16 | (arrayOfByte[18] & 0xFF) << 8 | arrayOfByte[19] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  791 */     this.directoryOffset = (arrayOfByte[24] & 0xFF) << 56 | (arrayOfByte[25] & 0xFF) << 48 | (arrayOfByte[26] & 0xFF) << 40 | (arrayOfByte[27] & 0xFF) << 32 | (arrayOfByte[28] & 0xFF) << 24 | (arrayOfByte[29] & 0xFF) << 16 | (arrayOfByte[30] & 0xFF) << 8 | (arrayOfByte[31] & 0xFF);
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
/*  806 */     setFilePointer(0L);
/*      */     try {
/*  808 */       this.cgFile.writeInt(-1159857484);
/*  809 */       this.cgFile.writeInt(this.majorVersionNumber);
/*  810 */       this.cgFile.writeInt(this.minorVersionNumber);
/*  811 */       this.cgFile.writeInt(this.minorMinorVersionNumber);
/*  812 */       this.cgFile.writeInt(this.objectCount);
/*  813 */       this.cgFile.writeInt(0);
/*  814 */       this.cgFile.writeLong(this.directoryOffset);
/*      */ 
/*      */     
/*      */     }
/*  818 */     catch (IOException iOException) {
/*  819 */       throw new IOException(iOException.getMessage() + "\ncould not write file header for " + this.fileName);
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
/*  831 */     byte[] arrayOfByte = new byte[paramArrayOfLong.length * 8];
/*  832 */     setFilePointer(paramLong);
/*      */     
/*      */     try {
/*  835 */       this.cgFile.read(arrayOfByte);
/*      */ 
/*      */     
/*      */     }
/*  839 */     catch (IOException iOException) {
/*  840 */       throw new IOException(iOException.getMessage() + "\nfailed to read " + arrayOfByte.length + " byte directory, offset " + paramLong + " in file " + this.fileName);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  846 */     for (byte b = 0; b < paramArrayOfLong.length; b++) {
/*  847 */       paramArrayOfLong[b] = (arrayOfByte[b * 8 + 0] & 0xFF) << 56 | (arrayOfByte[b * 8 + 1] & 0xFF) << 48 | (arrayOfByte[b * 8 + 2] & 0xFF) << 40 | (arrayOfByte[b * 8 + 3] & 0xFF) << 32 | (arrayOfByte[b * 8 + 4] & 0xFF) << 24 | (arrayOfByte[b * 8 + 5] & 0xFF) << 16 | (arrayOfByte[b * 8 + 6] & 0xFF) << 8 | (arrayOfByte[b * 8 + 7] & 0xFF);
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
/*  863 */     setFilePointer(this.directoryOffset);
/*      */     
/*  865 */     int i = (int)(this.directoryOffset % 8L);
/*  866 */     if (i != 0) {
/*      */       
/*  868 */       byte[] arrayOfByte = new byte[8 - i];
/*      */       
/*      */       try {
/*  871 */         this.cgFile.write(arrayOfByte);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  876 */       catch (IOException iOException) {
/*  877 */         throw new IOException(iOException.getMessage() + "\ncould not write " + i + " bytes to long word align directory for " + this.fileName);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  882 */       this.directoryOffset += (8 - i);
/*      */     } 
/*      */     
/*      */     try {
/*  886 */       for (byte b = 0; b < this.objectCount; b++) {
/*  887 */         this.cgFile.writeLong(this.directory[b]);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  892 */     catch (IOException iOException) {
/*  893 */       throw new IOException(iOException.getMessage() + "\ncould not write directory for " + this.fileName);
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
/*      */   CompressedGeometry readNext(int paramInt) throws IOException {
/*  905 */     if (this.objectIndex == this.objectCount) {
/*  906 */       return null;
/*      */     }
/*  908 */     if (this.bufferNextObjectCount == 0) {
/*      */       
/*  910 */       int i = 0;
/*  911 */       this.bufferObjectCount = 0;
/*      */ 
/*      */       
/*  914 */       for (j = this.objectIndex; j < this.objectCount && 
/*  915 */         i + this.objectSizes[j] <= paramInt; j++) {
/*  916 */         i += this.objectSizes[j];
/*  917 */         this.bufferObjectCount++;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  922 */         j = this.cgFile.read(this.cgBuffer, 0, i);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  927 */       catch (IOException j) {
/*  928 */         IOException iOException; throw new IOException(iOException.getMessage() + "\nfailed to read " + i + " bytes, object " + this.objectIndex + " in file " + this.fileName);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  935 */       this.bufferObjectStart = this.objectIndex;
/*  936 */       this.bufferNextObjectCount = this.bufferObjectCount;
/*  937 */       this.bufferNextObjectOffset = 0;
/*      */     } 
/*      */ 
/*      */     
/*  941 */     this.geomSize = (this.cgBuffer[this.bufferNextObjectOffset + 0 + 0] & 0xFF) << 24 | (this.cgBuffer[this.bufferNextObjectOffset + 0 + 1] & 0xFF) << 16 | (this.cgBuffer[this.bufferNextObjectOffset + 0 + 2] & 0xFF) << 8 | this.cgBuffer[this.bufferNextObjectOffset + 0 + 3] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  947 */     this.geomDataType = (this.cgBuffer[this.bufferNextObjectOffset + 4 + 0] & 0xFF) << 24 | (this.cgBuffer[this.bufferNextObjectOffset + 4 + 1] & 0xFF) << 16 | (this.cgBuffer[this.bufferNextObjectOffset + 4 + 2] & 0xFF) << 8 | this.cgBuffer[this.bufferNextObjectOffset + 4 + 3] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  954 */     this.geomStart = this.bufferNextObjectOffset + 8;
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
/*  966 */     this.bufferNextObjectOffset += this.objectSizes[this.objectIndex];
/*  967 */     this.bufferNextObjectCount--;
/*  968 */     this.objectIndex++;
/*      */     
/*  970 */     return newCG(this.geomSize, this.geomStart, this.geomDataType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   CompressedGeometry newCG(int paramInt1, int paramInt2, int paramInt3) {
/*  980 */     this.cgh.size = paramInt1;
/*  981 */     this.cgh.start = paramInt2;
/*      */     
/*  983 */     if ((paramInt3 & 0x3) == 1) {
/*  984 */       this.cgh.bufferType = 0;
/*  985 */     } else if ((paramInt3 & 0x3) == 2) {
/*  986 */       this.cgh.bufferType = 1;
/*  987 */     } else if ((paramInt3 & 0x3) == 3) {
/*  988 */       this.cgh.bufferType = 2;
/*      */     } 
/*  990 */     this.cgh.bufferDataPresent = 0;
/*      */     
/*  992 */     if ((paramInt3 & 0x4) != 0) {
/*  993 */       this.cgh.bufferDataPresent |= 0x1;
/*      */     }
/*      */     
/*  996 */     if ((paramInt3 & 0x8) != 0) {
/*  997 */       this.cgh.bufferDataPresent |= 0x2;
/*      */     }
/*      */     
/* 1000 */     if ((paramInt3 & 0x10) != 0) {
/* 1001 */       this.cgh.bufferDataPresent |= 0x4;
/*      */     }
/*      */     
/* 1004 */     return new CompressedGeometry(this.cgh, this.cgBuffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1011 */   protected void finalize() throws IOException { close(); }
/*      */ }


/* Location:              C:\Users\Good Shape Code\OneDrive\IPG-ESTG-1920\SEMESTER1\CG\Outros\robot.jar!\com\sun\j3\\utils\compression\CompressedGeometryFile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */