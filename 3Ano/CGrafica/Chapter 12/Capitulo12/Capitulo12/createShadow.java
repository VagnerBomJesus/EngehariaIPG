private GeometryArray createShadow(GeometryArray ga, Point3f light, Point3f plane) {
   
    //A geometria da sombra será construída com base na classe IndexedTriangleArray.

    //Para sabermos quantos triângulos serão necessários, a geometria do objeto é convertida
    //para IndexedTriangleArray através de um objeto GeometryInfo
    GeometryInfo gi = new GeometryInfo(ga);
    gi.convertToIndexedTriangles();
    IndexedTriangleArray ita = (IndexedTriangleArray)gi.getIndexedGeometryArray();
     
    //A projeção dos vértices do objeto no plano é realizada por uma transformação geométrica composta
    // U'PU, onde P é a matriz de projeção e U é a transformação lookAt
    Vector3f v = new Vector3f();
    v.sub(plane, light);  //distancia da luz ao plano de projeção
    
    //Configuração da matriz da transformação composta.
    double[] mat = new double[16];
    for (int i = 0; i < 16; i++) {
      mat[i] = 0;
    }
    mat[0] = 1;
    mat[5] = 1;
    mat[10] = 1-0.001;  //Pequena subtração em Z para que o polígono da sombra não coincida com o plano de projeção.
    mat[14] = -1/v.length();  //Projeção na forma standard. 
    Transform3D proj = new Transform3D();
    proj.set(mat);
    Transform3D u = new Transform3D();
    u.lookAt(new Point3d(light), new Point3d(plane), new Vector3d(0,1,0));
    proj.mul(u);
    Transform3D tr = new Transform3D();
    u.invert();
    tr.mul(u, proj);
     
    //A geometria da sombra é criada de modo a ter o mesmo número de vertices e de índices definidos na geometria do objeto.
    int n = ita.getVertexCount();
    int count = ita.getIndexCount();
    IndexedTriangleArray shadow = new IndexedTriangleArray(n,
      GeometryArray.COORDINATES, count);
       
    //Cada coordenada de vértice do objeto é transformada com a transformação da projecção definidia anteriormente
    //de modo a obter a coordenada do correspondente vertice da geometria da sombra.
    for (int i = 0; i < n; i++) {
      Point3d p = new Point3d();
      ita.getCoordinate(i, p);
      Vector4d v4 = new Vector4d(p);
      v4.w = 1;  //Configuração da coordenada homogénea para ser possivel a multiplicação pela matriz da projeção.
      tr.transform(v4);
      Point4d p4 = new Point4d(v4);
      p.project(p4);  //multiplica x, y, z por 1/w para transformar o ponto p4 que está em coordendas homegéneas. 
      shadow.setCoordinate(i, p);
    }
     
    //Os índices das coordenadas dos vertices da geometria da sombra são os mesmos definidos na geometria do objeto.
    int[] indices = new int[count];
    ita.getCoordinateIndices(0, indices);
    shadow.setCoordinateIndices(0, indices);
    return shadow;
  }
}