import java.util.LinkedList;

public class Mesh{
    LinkedList<Triangle> tris = new LinkedList<Triangle>();

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < tris.size() ;i++){
            str = str + tris.get(i).toString() + "\n";
        }
        return str;
    }
}
