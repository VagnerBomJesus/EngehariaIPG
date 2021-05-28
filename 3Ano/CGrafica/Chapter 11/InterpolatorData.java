package utils;


import java.util.ArrayList;
import java.util.List;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;

public class InterpolatorData
{
    private final List<Point3f> positions = new ArrayList<Point3f>();
    private final List<Quat4f> orientations = new ArrayList<Quat4f>();

    public void add(Point3f p, float angleDeg)
    {
        positions.add(p);

        //Cria quaternião com base num eixo de rotação e num angulo
        AxisAngle4f a = new AxisAngle4f(
            0.0f, 1.0f, 0.0f, (float) Math.toRadians(angleDeg));
        Quat4f q = new Quat4f();
        q.set(a);
        orientations.add(q);
    }

    public Point3f[] getPositions()
    {
        return positions.toArray(new Point3f[0]);
    }

    public Quat4f[] getOrientations()
    {
        return orientations.toArray(new Quat4f[0]);
    }

    public float[] getAlphas()
    {
    	//Cria os valores dos alphas para os nós dividindo uniformente 
        float alphas[] = new float[positions.size()];
        float delta = 1.0f / (alphas.length - 1);
        for (int i=0; i<alphas.length; i++)
        {
            alphas[i] = i * delta;
        }
        return alphas;
    }
}