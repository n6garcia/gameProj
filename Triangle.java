public class Triangle{
    Vec3d[] p = new Vec3d[3];

    public Triangle(Vec3d sw, Vec3d n, Vec3d ne){
        p[0] = sw;
        p[1] = n;
        p[2] = ne;
    }

    public Triangle(){
        p[0] = new Vec3d(0.0f,0.0f,0.0f);
        p[1] = new Vec3d(0.0f,0.0f,0.0f);
        p[2] = new Vec3d(0.0f,0.0f,0.0f);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < 3;i++){
            str = str + "[";
            str = str + p[i].x + " " + p[i].y + " " + p[i].z;
            str = str + "] ";
        }
        return str;
    }
}
