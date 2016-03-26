package gcj;
import general.GCJProblem;
import java.io.IOException;

/* @author fazuniga */
public class GoogleCodeJam {

    public GCJProblem cjp;
    public String Path = "input/";
    public String InputFile = "A-small-practice.in";
    public int LinesPerBlock = 2;

    public void main(String[] args) throws IOException
    {
        cjp = new GCJProblem();
        cjp.setParameters(Path, InputFile, LinesPerBlock);
        cjp.PrepareProblem();

        cjp.Run();
        
        System.out.println();
        System.out.println(" => -----    ¡Listo!    ----- <= ");
    }
}
