import java.util.LinkedList;
import java.lang.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import java.util.Arrays;
import java.lang.Math;

public class ShapeProj{

    Vec3d vCamera = new Vec3d();
    Game game;
    public LinkedList<Polygon> polygons = new LinkedList<Polygon>();
    public Mesh mesh;

    public ShapeProj(Game game){
        this.game = game;
    }

    public Mat4x4 getMatProj(){
        float fNear = 0.1f;
        float fFar = 1000.0f;
        float fFov = 90.0f;
        float fAspectRatio = (float)game.scene.getWidth() / (float)game.scene.getWidth();
        float fFovRad = 1.0f / (float)Math.tan(fFov * 0.5f / 180.0f * 3.14159f);

        Mat4x4 matProj = new Mat4x4();

        matProj.m[0][0] = fAspectRatio * fFovRad;
        matProj.m[1][1] = fFovRad;
        matProj.m[2][2] = fFar / (fFar - fNear);
        matProj.m[3][2] = (-fFar * fNear) / (fFar - fNear);
        matProj.m[2][3] = 1.0f;
        matProj.m[3][3] = 0.0f;

        return matProj;
    }

    public void getCube(){

        Mat4x4 matProj = getMatProj();
        Mesh meshCube = new Mesh();

        Triangle s1 = new Triangle(new Vec3d(0.0f, 0.0f, 0.0f), new Vec3d(0.0f, 1.0f, 0.0f), new Vec3d(1.0f, 1.0f, 0.0f));
        Triangle s2 = new Triangle(new Vec3d(0.0f, 0.0f, 0.0f), new Vec3d(1.0f, 1.0f, 0.0f), new Vec3d(1.0f, 0.0f, 0.0f));

        Triangle e1 = new Triangle(new Vec3d(1.0f, 0.0f, 0.0f), new Vec3d(1.0f, 1.0f, 0.0f), new Vec3d(1.0f, 1.0f, 1.0f));
        Triangle e2 = new Triangle(new Vec3d(1.0f, 0.0f, 0.0f), new Vec3d(1.0f, 1.0f, 1.0f), new Vec3d(1.0f, 0.0f, 1.0f));

        Triangle n1 = new Triangle(new Vec3d(1.0f, 0.0f, 1.0f), new Vec3d(1.0f, 1.0f, 1.0f), new Vec3d(0.0f, 1.0f, 1.0f));
        Triangle n2 = new Triangle(new Vec3d(1.0f, 0.0f, 1.0f), new Vec3d(0.0f, 1.0f, 1.0f), new Vec3d(0.0f, 0.0f, 1.0f));

        Triangle w1 = new Triangle(new Vec3d(0.0f, 0.0f, 1.0f), new Vec3d(0.0f, 1.0f, 1.0f), new Vec3d(0.0f, 1.0f, 0.0f));
        Triangle w2 = new Triangle(new Vec3d(0.0f, 0.0f, 1.0f), new Vec3d(0.0f, 1.0f, 0.0f), new Vec3d(0.0f, 0.0f, 0.0f));

        Triangle t1 = new Triangle(new Vec3d(0.0f, 1.0f, 0.0f), new Vec3d(0.0f, 1.0f, 1.0f), new Vec3d(1.0f, 1.0f, 1.0f));
        Triangle t2 = new Triangle(new Vec3d(0.0f, 1.0f, 0.0f), new Vec3d(1.0f, 1.0f, 1.0f), new Vec3d(1.0f, 1.0f, 0.0f));

        Triangle b1 = new Triangle(new Vec3d(1.0f, 0.0f, 1.0f), new Vec3d(0.0f, 0.0f, 1.0f), new Vec3d(0.0f, 0.0f, 0.0f));
        Triangle b2 = new Triangle(new Vec3d(1.0f, 0.0f, 1.0f), new Vec3d(0.0f, 0.0f, 0.0f), new Vec3d(1.0f, 0.0f, 0.0f));

        meshCube.tris.add(s1);
        meshCube.tris.add(s2);
        meshCube.tris.add(e1);
        meshCube.tris.add(e2);
        meshCube.tris.add(n1);
        meshCube.tris.add(n2);
        meshCube.tris.add(w1);
        meshCube.tris.add(w2);
        meshCube.tris.add(t1);
        meshCube.tris.add(t2);
        meshCube.tris.add(b1);
        meshCube.tris.add(b2);

        this.mesh = meshCube;

        Triangle tri;
        for (int i =0; i < meshCube.tris.size(); i++) {

            Triangle triProjected = new Triangle();
            Triangle triTranslated = new Triangle();

            tri = meshCube.tris.get(i);

            // Offset into the screen
            triTranslated.p[0].z = tri.p[0].z + 3.0f;
            triTranslated.p[1].z = tri.p[1].z + 3.0f;
            triTranslated.p[2].z = tri.p[2].z + 3.0f;

            // Project Triangles from 3D --> 2D
            MultiplyMatrixVector(triTranslated.p[0], triProjected.p[0], matProj);
            MultiplyMatrixVector(triTranslated.p[1], triProjected.p[1], matProj);
            MultiplyMatrixVector(triTranslated.p[2], triProjected.p[2], matProj);

            // Scale into view
            triProjected.p[0].x += 1.0f; triProjected.p[0].y += 1.0f;
            triProjected.p[1].x += 1.0f; triProjected.p[1].y += 1.0f;
            triProjected.p[2].x += 1.0f; triProjected.p[2].y += 1.0f;
            triProjected.p[0].x *= 0.5f * (float)game.scene.getWidth();
            triProjected.p[0].y *= 0.5f * (float)game.scene.getHeight();
            triProjected.p[1].x *= 0.5f * (float)game.scene.getWidth();
            triProjected.p[1].y *= 0.5f * (float)game.scene.getHeight();
            triProjected.p[2].x *= 0.5f * (float)game.scene.getWidth();
            triProjected.p[2].y *= 0.5f * (float)game.scene.getHeight();

            Polygon polygon = new Polygon();
            polygon.getPoints().addAll(new Double[]{
                (double)triProjected.p[0].x, (double)triProjected.p[0].y,
                (double)triProjected.p[1].x, (double)triProjected.p[1].y,
                (double)triProjected.p[2].x, (double)triProjected.p[2].y });

            polygon.setStroke(Color.RED);
            polygon.setFill(Color.TRANSPARENT);

            polygons.add(polygon);

            game.root.getChildren().add(polygon);

        }

    }

    public void rotate(float fTheta){

        Mat4x4 matProj = getMatProj();

        // Set up rotation matrices
        Mat4x4 matRotZ = new Mat4x4();
        Mat4x4 matRotX = new Mat4x4();

        // Rotation Z
        matRotZ.m[0][0] = (float)Math.cos(fTheta);
        matRotZ.m[0][1] = (float)Math.sin(fTheta);
        matRotZ.m[1][0] = (float)-Math.sin(fTheta);
        matRotZ.m[1][1] = (float)Math.cos(fTheta);
        matRotZ.m[2][2] = 1;
        matRotZ.m[3][3] = 1;

        // Rotation X
        matRotX.m[0][0] = 1;
        matRotX.m[1][1] = (float)Math.cos(fTheta * 0.5f);
        matRotX.m[1][2] = (float)Math.sin(fTheta * 0.5f);
        matRotX.m[2][1] = (float)-Math.sin(fTheta * 0.5f);
        matRotX.m[2][2] = (float)Math.cos(fTheta * 0.5f);
        matRotX.m[3][3] = 1;

        Triangle tri;
        for (int i =0; i < mesh.tris.size(); i++) {

            Triangle triProjected = new Triangle();
            Triangle triTranslated = new Triangle();
            Triangle triRotatedZ= new Triangle();
            Triangle triRotatedZX = new Triangle();

            tri = mesh.tris.get(i);

            // Rotate in Z-Axis
            MultiplyMatrixVector(tri.p[0], triRotatedZ.p[0], matRotZ);
            MultiplyMatrixVector(tri.p[1], triRotatedZ.p[1], matRotZ);
            MultiplyMatrixVector(tri.p[2], triRotatedZ.p[2], matRotZ);

            // Rotate in X-Axis
            MultiplyMatrixVector(triRotatedZ.p[0], triRotatedZX.p[0], matRotX);
            MultiplyMatrixVector(triRotatedZ.p[1], triRotatedZX.p[1], matRotX);
            MultiplyMatrixVector(triRotatedZ.p[2], triRotatedZX.p[2], matRotX);

            // Offset into the screen
            triTranslated = triRotatedZX;
            triTranslated.p[0].z = triRotatedZX.p[0].z + 4.0f;
            triTranslated.p[1].z = triRotatedZX.p[1].z + 4.0f;
            triTranslated.p[2].z = triRotatedZX.p[2].z + 4.0f;



            // Use Cross-Product to get surface normal
            Vec3d normal = new Vec3d();
            Vec3d line1 = new Vec3d();
            Vec3d line2 = new Vec3d();
            line1.x = triTranslated.p[1].x - triTranslated.p[0].x;
            line1.y = triTranslated.p[1].y - triTranslated.p[0].y;
            line1.z = triTranslated.p[1].z - triTranslated.p[0].z;

            line2.x = triTranslated.p[2].x - triTranslated.p[0].x;
            line2.y = triTranslated.p[2].y - triTranslated.p[0].y;
            line2.z = triTranslated.p[2].z - triTranslated.p[0].z;

            normal.x = line1.y * line2.z - line1.z * line2.y;
            normal.y = line1.z * line2.x - line1.x * line2.z;
            normal.z = line1.x * line2.y - line1.y * line2.x;

            // It's normally normal to normalise the normal
            float l = (float)Math.sqrt(normal.x*normal.x + normal.y*normal.y + normal.z*normal.z);
            normal.x /= l; normal.y /= l; normal.z /= l;

            Polygon polygon = polygons.get(i);


            if(normal.x * (triTranslated.p[0].x - vCamera.x) +
               normal.y * (triTranslated.p[0].y - vCamera.y) +
               normal.z * (triTranslated.p[0].z - vCamera.z) > 0.0f)
            {
                polygon.setStroke(Color.TRANSPARENT);
            } else {
                // Project triangles from 3D --> 2D
                MultiplyMatrixVector(triTranslated.p[0], triProjected.p[0], matProj);
                MultiplyMatrixVector(triTranslated.p[1], triProjected.p[1], matProj);
                MultiplyMatrixVector(triTranslated.p[2], triProjected.p[2], matProj);

                // Scale into view
                triProjected.p[0].x += 1.0f; triProjected.p[0].y += 1.0f;
                triProjected.p[1].x += 1.0f; triProjected.p[1].y += 1.0f;
                triProjected.p[2].x += 1.0f; triProjected.p[2].y += 1.0f;
                triProjected.p[0].x *= 0.5f * (float)game.scene.getWidth();
                triProjected.p[0].y *= 0.5f * (float)game.scene.getHeight();
                triProjected.p[1].x *= 0.5f * (float)game.scene.getWidth();
                triProjected.p[1].y *= 0.5f * (float)game.scene.getHeight();
                triProjected.p[2].x *= 0.5f * (float)game.scene.getWidth();
                triProjected.p[2].y *= 0.5f * (float)game.scene.getHeight();

                polygon.setStroke(Color.RED);

                polygon.getPoints().setAll(new Double[]{
                    (double)triProjected.p[0].x, (double)triProjected.p[0].y,
                    (double)triProjected.p[1].x, (double)triProjected.p[1].y,
                    (double)triProjected.p[2].x, (double)triProjected.p[2].y });
            }

            //System.out.println(triProjected);

        }

        //System.out.println(" ");
    }

    public void move(int x, int y){
        vCamera.x +=x;
        vCamera.y +=y;

        System.out.println(vCamera.x + " " + vCamera.y + " " + vCamera.z);
    }

    public static void MultiplyMatrixVector(Vec3d i, Vec3d o, Mat4x4 m) {
        o.x = i.x * m.m[0][0] + i.y * m.m[1][0] + i.z * m.m[2][0] + m.m[3][0];
        o.y = i.x * m.m[0][1] + i.y * m.m[1][1] + i.z * m.m[2][1] + m.m[3][1];
        o.z = i.x * m.m[0][2] + i.y * m.m[1][2] + i.z * m.m[2][2] + m.m[3][2];
        float w = i.x * m.m[0][3] + i.y * m.m[1][3] + i.z * m.m[2][3] + m.m[3][3];

        if (w != 0.0f)
        {
            o.x /= w; o.y /= w; o.z /= w;
        }
    }


}
