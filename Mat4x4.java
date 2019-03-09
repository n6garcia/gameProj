public class Mat4x4{
    float[][] m = new float[4][4];

    Mat4x4 Matrix_MakeIdentity() {
        Mat4x4 matrix = new Mat4x4();
        matrix.m[0][0] = 1.0f;
        matrix.m[1][1] = 1.0f;
        matrix.m[2][2] = 1.0f;
        matrix.m[3][3] = 1.0f;
        return matrix;
    }

}
