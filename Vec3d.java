import java.lang.Math;

public class Vec3d{
    float x,y,z;
    public Vec3d(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3d(){
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public Vec3d Vector_Normalise(Vec3d v) {
        float l = Vector_Length(v);
        return new Vec3d(v.x / l, v.y / l, v.z / l );
    }

    public float Vector_Length(Vec3d v) {
        return (float)Math.sqrt(Vector_DotProduct(v, v));
    }

    Vec3d Vector_CrossProduct(Vec3d v1, Vec3d v2) {
        Vec3d v = new Vec3d();
        v.x = v1.y * v2.z - v1.z * v2.y;
        v.y = v1.z * v2.x - v1.x * v2.z;
        v.z = v1.x * v2.y - v1.y * v2.x;
        return v;
    }

    public static Vec3d Vector_Add(Vec3d v1, Vec3d v2) {
        return new Vec3d(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    public static Vec3d Vector_Sub(Vec3d v1, Vec3d v2) {
        return new Vec3d(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public static Vec3d Vector_Mul(Vec3d v1, float k){
        return new Vec3d(v1.x * k, v1.y * k, v1.z * k);
    }

    public static Vec3d Vector_Div(Vec3d v1, float k) {
        return new Vec3d(v1.x / k, v1.y / k, v1.z / k);
    }

    public static float Vector_DotProduct(Vec3d v1, Vec3d v2) {
        return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
    }
}
