private GeometryArray createShadow(GeometryArray ga, Point3f light, Point3f plane) {
   
    //A geometria da sombra ser� constru�da com base na classe IndexedTriangleArray.

    //Para sabermos quantos tri�ngulos ser�o necess�rios, a geometria do objeto � convertida
    //para IndexedTriangleArray atrav�s de um objeto GeometryInfo
    GeometryInfo gi = new GeometryInfo(ga);
    gi.convertToIndexedTriangles();
    IndexedTriangleArray ita = (IndexedTriangleArray)gi.getIndexedGeometryArray();
     
    //A proje��o dos v�rtices do objeto no plano � realizada por uma transforma��o geom�trica composta
    // U'PU, onde P � a matriz de proje��o e U � a transforma��o lookAt
    Vector3f v = new Vector3f();
    v.sub(plane, light);  //distancia da luz ao plano de proje��o
    
    //Configura��o da matriz da transforma��o composta.
    double[] mat = new double[16];
    for (int i = 0; i < 16; i++) {
      mat[i] = 0;
    }
    mat[0] = 1;
    mat[5] = 1;
    mat[10] = 1-0.001;  //Pequena subtra��o em Z para que o pol�gono da sombra n�o coincida com o plano de proje��o.
    mat[14] = -1/v.length();  //Proje��o na forma standard. 
    Transform3D proj = new Transform3D();
    proj.set(mat);
    Transform3D u = new Transform3D();
    u.lookAt(new Point3d(light), new Point3d(plane), new Vector3d(0,1,0));
    proj.mul(u);
    Transform3D tr = new Transform3D();
    u.invert();
    tr.mul(u, proj);
     
    //A geometria da sombra � criada de modo a ter o mesmo n�mero de vertices e de �ndices definidos na geometria do objeto.
    int n = ita.getVertexCount();
    int count = ita.getIndexCount();
    IndexedTriangleArray shadow = new IndexedTriangleArray(n,
      GeometryArray.COORDINATES, count);
       
    //Cada coordenada de v�rtice do objeto � transformada com a transforma��o da projec��o definidia anteriormente
    //de modo a obter a coordenada do correspondente vertice da geometria da sombra.
    for (int i = 0; i < n; i++) {
      Point3d p = new Point3d();
      ita.getCoordinate(i, p);
      Vector4d v4 = new Vector4d(p);
      v4.w = 1;  //Configura��o da coordenada homog�nea para ser possivel a multiplica��o pela matriz da proje��o.
      tr.transform(v4);
      Point4d p4 = new Point4d(v4);
      p.project(p4);  //multiplica x, y, z por 1/w para transformar o ponto p4 que est� em coordendas homeg�neas. 
      shadow.setCoordinate(i, p);
    }
     
    //Os �ndices das coordenadas dos vertices da geometria da sombra s�o os mesmos definidos na geometria do objeto.
    int[] indices = new int[count];
    ita.getCoordinateIndices(0, indices);
    shadow.setCoordinateIndices(0, indices);
    return shadow;
  }
}