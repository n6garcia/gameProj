import java.io.FileReader;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.io.FileNotFoundException;

public class Model{

    public Mesh mesh;

    public Model(String path){
        loadModel(path);
    }

    public Mesh getMesh(){
        return mesh;
    }

    public boolean loadModel(String path){

        try {
            BufferedReader bf = new BufferedReader(new FileReader(path));

            bf.readLine();
            bf.readLine();

            String[] info;

            LinkedList<Vec3d> vects = new LinkedList<Vec3d>();
            LinkedList<Triangle> tris = new LinkedList<Triangle>();

            String line = null;
            while ((line =bf.readLine())!=null){

                if (line.charAt(0) == 'v' ){
                    info = line.split(" ");
                    Vec3d v = new Vec3d();
                    v.x = Float.parseFloat(info[1]);
                    v.y = Float.parseFloat(info[2]);
                    v.z = Float.parseFloat(info[3]);
                    vects.add(v);
                } else if (line.charAt(0) == 'f' ){
                    info = line.split(" ");
                    Triangle t = new Triangle(
                        vects.get(Integer.parseInt(info[1]) - 1),
                        vects.get(Integer.parseInt(info[2]) - 1),
                        vects.get(Integer.parseInt(info[3]) - 1));
                    tris.add(t);
                }

                System.out.println(line);
            }

            this.mesh = new Mesh(tris);

            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }



}
